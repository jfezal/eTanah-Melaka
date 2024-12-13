/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.service.AnsuranService;
import etanah.view.etanahActionBeanContext;
import etanah.view.jtek.ws.InfoDok;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */
public class AnsuranLulusN9 implements StageListener {

    private static final Logger LOG = Logger.getLogger(AnsuranLulusN9.class);

    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;

    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    @Inject
    KutipanHasilManager manager;

    @Inject
    RemisyenManager mangr;
    
    @Inject
    AnsuranService ansuranService;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Date now = new Date();
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

        String query = "select mf from etanah.model.FasaPermohonan mf where mf.permohonan.idPermohonan = :idPermohonan AND mf.keputusan is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", permohonan.getIdPermohonan());
        List<FasaPermohonan> senaraiFasaPermohonan = q.list();
        LOG.info("senaraiFasaPermohonan.size() : " + senaraiFasaPermohonan.size());

        Pengguna pguna = new Pengguna();
        for (FasaPermohonan fp : senaraiFasaPermohonan) {
            if (fp.getKeputusan().getKod().equals("L")) {
                flag = true;
                pguna = penggunaDAO.findById(fp.getIdPengguna());
            }
        }
        Akaun akaun = hakmilik.getAkaunCukai();
        LOG.info("flag : " + flag + " akaun.getNoAkaun() : " + akaun.getNoAkaun());
        if (flag) {
            akaun.setAnsuran('Y');
            akaun.setAmaunMatang(akaun.getBaki());

            manager.saveOrUpdate(akaun);

            permohonan.setTarikhKeputusan(now);
            permohonan.setKeputusan(kodKeputusanDAO.findById("L"));
            mangr.save(permohonan, pguna);

            addTransaksiDenda(permohonan, akaun, pguna);
        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    public void addTransaksiDenda(Permohonan p, Akaun a, Pengguna pguna) {
        int month = p.getTarikhMula().getMonth();
        int tahun = p.getTarikhMula().getYear() + 1900;
        boolean flg = false;
        boolean flgDenda = false;
        Date now = new Date();
        PermohonanAnsuran ansuran = ansuranService.findByIdPermohonan(p);
        List<Transaksi> lt = a.getSenaraiTransaksiDebit();

        for (int i = 0; i < p.getTempohBulan(); i++) {
            int bln = month + i;
            flg = bln >= 5;
        }

        for (Transaksi t : lt) {
            if ((t.getKodTransaksi().getKod().equals("76152")) && (t.getUntukTahun() == tahun)) {
                flgDenda = true;
            }
        }

        if ((flg)&&(flgDenda == false)) {
            Transaksi t = new Transaksi();
            InfoAudit ia = new InfoAudit();
            
            t.setCawangan(a.getCawangan());
            t.setKodTransaksi(kodTransaksiDAO.findById("76152"));
            t.setUntukTahun(tahun);
            t.setAkaunDebit(a);
            t.setAmaun(ansuran.getDenda());
            t.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(now);
                t.setInfoAudit(ia);
            t.setTahunKewangan(tahun);
            t.setBayaranAgensi("N");
            
            mangr.saveTransaksi(t);
            BigDecimal bakiasal = a.getBaki();
            a.setAmaunMatang(bakiasal.add(ansuran.getDenda()));
            a.setBaki(bakiasal.add(ansuran.getDenda()));
            manager.saveOrUpdate(a);
        }
    }
    
    public BigDecimal calculateDenda(Akaun ak){
        BigDecimal d = new BigDecimal(0);
        BigDecimal x = new BigDecimal(0);
        
        if(ak.getBaki().intValue() > 100){
            x = new BigDecimal(0.10);    
            double fine = (ak.getBaki().multiply(x).doubleValue());
            d = new BigDecimal(ak.getBaki().multiply(x).doubleValue());
        }else if(ak.getBaki().intValue() > 50){
            x = new BigDecimal(10.0); 
            d = x;
        }else if(ak.getBaki().intValue() > 20){
            x = new BigDecimal(5.0);     
            d = x;       
        }else if(ak.getBaki().intValue() >= 5){
            x = new BigDecimal(1.5);    
            d = x;        
        }else if(ak.getBaki().intValue() > 0){
            x = new BigDecimal(1.0);
            d = x;
        }
        
        LOG.info("denda : "+d);
        return d;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
