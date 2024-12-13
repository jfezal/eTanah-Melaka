/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanCarianDAO;
import etanah.dao.TransaksiDAO;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import etanah.model.DokumenKewangan;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.Transaksi;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import etanah.service.HakmilikService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nurfaizati
 */
@Wizard(startEvents = {"selectTransaction"})
@UrlBinding("/hasil/bayaran")
public class BayaranActionBean extends AbleActionBean {

    private Transaksi transaksi = new Transaksi();
    private List<Transaksi> list = new ArrayList<Transaksi>();
    private List<Transaksi>listT = new ArrayList<Transaksi>();
    private List<DokumenKewanganBayaran> dkbList = new ArrayList<DokumenKewanganBayaran>();
    private Permohonan permohonan = new Permohonan();
    private DokumenKewangan dokumenKewangan =new DokumenKewangan();
    private String idPermohonan = null;
    private String resitManual = null;
    private String idDokumenKewangan = null;
    private BigDecimal jumlahCaj = new BigDecimal(0.00);
    private BigDecimal baki = new BigDecimal(0);
    private BigDecimal amaun;
    private boolean flag = false;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private PermohonanCarian permohonanCarian;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    PermohonanCarianDAO permohonanCarianDAO;
    @Inject
    TransaksiDAO transaksiDAO;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran.jsp");
    }
       @ValidationMethod(on = "search")
    public void validateHakmilik(ValidationErrors errors) {
        if ((permohonan== null)&&(dokumenKewangan== null)) {
            errors.add("a", new SimpleError("Sila Masukkan ID Perserahan atau No Resit"));
        }
    }

    public Resolution search() {
      
        if(resitManual !=null){
            String query = "SELECT dk FROM etanah.model.DokumenKewangan dk where dk.noRujukanManual = :resit ";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("resit", resitManual);
            DokumenKewangan dk = (DokumenKewangan) q.uniqueResult();
            String queryTrans = "SELECT t FROM etanah.model.Transaksi t where t.dokumenKewangan.idDokumenKewangan = :dk";
            Query qTrans = sessionProvider.get().createQuery(queryTrans);
            qTrans.setString("dk", dk.getIdDokumenKewangan());
            listT = qTrans.list();
        }
        else {
            listT = hakmilikService.findMohon(getContext().getRequest().getParameterMap());
            //Start : Sekiranya ListT == 0 , Cari Menggunakan ID Carian.
            if (listT.isEmpty()) {
                
                permohonanCarian = permohonanCarianDAO.findById(permohonan.getIdPermohonan());
                if (permohonanCarian != null) {
                    idDokumenKewangan = permohonanCarian.getTrans().getDokumenKewangan().getIdDokumenKewangan();
                    String query = "SELECT dkb FROM etanah.model.DokumenKewanganBayaran dkb where dkb.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan";
                    Query q = sessionProvider.get().createQuery(query);
                    q.setString("idDokumenKewangan", idDokumenKewangan);
                    dkbList = q.list();
                    String id = Long.toString(permohonanCarian.getTrans().getIdTransaksi());

                    String queryTrans = "SELECT t FROM etanah.model.Transaksi t where t.idTransaksi = :id";
                    Query qTrans = sessionProvider.get().createQuery(queryTrans);
                    qTrans.setString("id", id);
                    listT = qTrans.list();
                }
            }
        }

        if (listT.size() > 0) {
            Transaksi trans = new Transaksi();
            trans = listT.get(0);
            if(trans.getAkaunKredit()!=null){
                baki = trans.getAkaunKredit().getBaki();
            }
            amaun = trans.getAmaun();
//            jumlahCaj = jumlahCaj.add(amaun);

        }
        BigDecimal debit = new BigDecimal(0.00);
        BigDecimal kredit = new BigDecimal(0.00);
        String idMohon = "";
        for (Transaksi t : listT) {
            idDokumenKewangan = t.getDokumenKewangan().getIdDokumenKewangan();
            if(t.getPermohonan() != null){
                if(!t.getPermohonan().getIdPermohonan().equals(idMohon)){
                    list.add(t);
                    idMohon = t.getPermohonan().getIdPermohonan();
                }
            }
            if(t.getAkaunDebit()!=null){
                if(t.getAkaunDebit().getKodAkaun().getKod().equals("AKH")){
                    debit = debit.add(t.getAmaun());}
            }
            if(t.getAkaunKredit()!=null){
                if(t.getAkaunKredit().getKodAkaun().getKod().equals("AKH")){
                    kredit = kredit.add(t.getAmaun());}
            }
        }
        jumlahCaj = debit.subtract(kredit);
        setFlag(true);

        String query = "SELECT dkb FROM etanah.model.DokumenKewanganBayaran dkb where dkb.dokumenKewangan.idDokumenKewangan = :resit ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("resit", idDokumenKewangan);
        dkbList = q.list();
        return new ForwardResolution("/WEB-INF/jsp/hasil/bayaran.jsp");
    }
    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public List<Transaksi> getList() {
        return list;
    }

    public void setList(List<Transaksi> list) {
        this.list = list;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

      public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdDokumenKewangan() {
        return idDokumenKewangan;
    }

    public void setIdDokumenKewangan(String idDokumenKewangan) {
        this.idDokumenKewangan = idDokumenKewangan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public BigDecimal getJumlahCaj() {
        return jumlahCaj;
    }

    public void setJumlahCaj(BigDecimal jumlahCaj) {
        this.jumlahCaj = jumlahCaj;
    }

    /**
     * @return the senaraiHakmilik
     */
    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    /**
     * @param senaraiHakmilik the senaraiHakmilik to set
     */
    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    /**
     * @return the amaun
     */
    public BigDecimal getAmaun() {
        return amaun;
    }

    /**
     * @param amaun the amaun to set
     */
    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public List<DokumenKewanganBayaran> getDkbList() {
        return dkbList;
    }

    public void setDkbList(List<DokumenKewanganBayaran> dkbList) {
        this.dkbList = dkbList;
    }

    public String getResitManual() {
        return resitManual;
    }

    public void setResitManual(String resitManual) {
        this.resitManual = resitManual;
    }
}
