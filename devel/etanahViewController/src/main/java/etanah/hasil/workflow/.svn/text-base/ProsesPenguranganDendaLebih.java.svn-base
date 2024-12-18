/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

/**
 *
 * @author abu.mansur
 */

import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Transaksi;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

public class ProsesPenguranganDendaLebih implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesPenguranganDendaLebih.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();


    @Inject
    AkaunDAO akaunDAO;

    @Inject
    RemisyenManager manager;

    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject
    etanah.kodHasilConfig khconf;

    @Override
    public boolean beforeStart(StageContext cxt) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.info("ProsesPenguranganDenda::beforeComplete()");
        Pengguna pengguna = ctx.getPengguna();
//        String kodTransDenda = "76152";
        String kodTransDenda = khconf.getProperty("dendaLewat");
        String Lulus = "L";
        String rtnMsg = "";
        Permohonan permohonan = ctx.getPermohonan();
        if(Lulus.equals(proposedOutcome)){
            KodUrusan u = permohonan.getKodUrusan();
            if("PKDL".equals(u.getKod()) && permohonan.getNilaiKeputusan() == null){
                ctx.addMessage("Sila masukkan maklumat pada Keputusan MMK : "+permohonan.getIdPermohonan());
                return null;
            }else{
                Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
                Akaun akaunAC = h.getAkaunCukai();
                Akaun akaunADL = null;
                String[] tname = {"kodAkaun.kod"};
                Object[] tvalue = {khconf.getProperty("akaunDendaLewat")}; // for akaun ADL
//                for (Akaun ak : akaunDAO.findAll()) {
                for (Akaun ak : akaunDAO.findByEqualCriterias(tname, tvalue, null)) {
                    if(ak.getKodAkaun().getKod().equals(khconf.getProperty("akaunDendaLewat")) && ak.getCawangan().getKod().equals(h.getCawangan().getKod())){
                        akaunADL = ak;
                    }
                }

                Transaksi t = new Transaksi();
                KodTransaksi kt = new KodTransaksi();
                KodCawangan kc = new KodCawangan();
                kc.setKod("05"); // manual : FIXME
                KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');

                // determine the amount of KurangDenda
                BigDecimal kurangDenda = new BigDecimal(0.0);
                BigDecimal denda = new BigDecimal(0.0);
                for(int i = 0; i<akaunAC.getSemuaTransaksi().size(); i++){
                    Transaksi transaksiDenda = akaunAC.getSemuaTransaksi().get(i);
                    if (transaksiDenda.getKodTransaksi().getKod().equals(kodTransDenda)){
                        denda = denda.add(transaksiDenda.getAmaun());
                    }
                }
                kurangDenda = denda.subtract(permohonan.getNilaiKeputusan());
                kt.setKod(kodTransDenda); //new change for same code
                t.setKodTransaksi(kt);

                //to update record in object "Akaun" for account "ADL"
                BigDecimal rounded_down = akaunADL.getBaki().add(permohonan.getNilaiKeputusan());
//                    rounded_down = rounded_down.setScale(0, BigDecimal.ROUND_DOWN);
                akaunADL.setBaki(rounded_down);

                //to update record for "AC" in object "Akaun"
                BigDecimal rounded_up = akaunAC.getBaki().subtract(permohonan.getNilaiKeputusan());
//                    rounded = rounded.setScale(0, BigDecimal.ROUND_UP);
                akaunAC.setBaki(rounded_up);

                t.setAmaun(permohonan.getNilaiKeputusan());
                t.setCawangan(h.getCawangan());
                t.setPermohonan(permohonan);
                t.setStatus(status);
                t.setAkaunDebit(akaunADL);
                t.setAkaunKredit(akaunAC);
                t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
                t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));

                manager.saveAndUpdate(t,akaunADL,null,null,null,akaunAC,pengguna);
                ctx.addMessage("Pengurangan denda sebanyak RM " + permohonan.getNilaiKeputusan() + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                rtnMsg = "Pengurangan denda sebanyak RM " + permohonan.getNilaiKeputusan() + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun();
                LOG.info("Pengurangan denda sebanyak RM " + permohonan.getNilaiKeputusan() + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
            }
        }else{
            LOG.debug("Permohonan ditolak. Tidak memerlukan amaun pengurangan.");
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
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
