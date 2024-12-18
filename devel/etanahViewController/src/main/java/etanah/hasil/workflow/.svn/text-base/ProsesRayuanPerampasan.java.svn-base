/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

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

/**
 *
 * @author abu.mansur
 */
public class ProsesRayuanPerampasan implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesRayuanPerampasan.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();


    @Inject
    AkaunDAO akaunDAO;

    @Inject
    RemisyenManager manager;

    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Inject
    private  etanah.kodHasilConfig khconf;

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
        LOG.info("ProsesRayuanPerampasan::beforeComplete()");
        Pengguna pengguna = ctx.getPengguna();
//        String notis = "72199";
        String notis = khconf.getProperty("dendaLewat"); // rayuanDenda = Denda Lewat : 76152
        String Lulus = "L";
        String rtnMsg = "";
        Permohonan permohonan = ctx.getPermohonan();
        if(Lulus.equals(proposedOutcome)){
            KodUrusan u = permohonan.getKodUrusan();
            if("PRPP".equals(u.getKod()) && permohonan.getNilaiKeputusan() == null){
                ctx.addMessage("Sila masukkan maklumat pada Keputusan MMK : "+permohonan.getIdPermohonan());
                return null;
            }else{
                Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
                Akaun akaunAC = h.getAkaunCukai();

                Transaksi t = new Transaksi();
                KodTransaksi kt = new KodTransaksi();
                KodCawangan kc = new KodCawangan();
                kc.setKod("05"); // manual : FIXME
                KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');

                // determine the amount of Nilai Rayuan
                BigDecimal nilaiRayuan = permohonan.getNilaiKeputusan();
                BigDecimal akaunACBaki = akaunAC.getBaki();
                kt.setKod(notis); //kod before, 61018 = Notis 6A Tanah
                t.setKodTransaksi(kt);

                //to update record for "AC" in object "Akaun"
                BigDecimal bakiNilaiRayuan = nilaiRayuan.subtract(akaunACBaki);
                akaunAC.setBaki(nilaiRayuan);

                t.setAmaun(bakiNilaiRayuan);
                t.setCawangan(h.getCawangan());
                t.setPermohonan(permohonan);
                t.setStatus(status);
                t.setAkaunDebit(akaunAC);
                t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
                t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));

                manager.saveAndUpdate(t,null,null,null,null,akaunAC,pengguna);
                ctx.addMessage("Jumlah yang perlu dibayar adalah sebanyak RM " + permohonan.getNilaiKeputusan());
                rtnMsg = "Jumlah yang perlu dibayar adalah sebanyak RM " + permohonan.getNilaiKeputusan();
                LOG.info("Jumlah yang perlu dibayar adalah sebanyak RM " + permohonan.getNilaiKeputusan());
    //                }
    //            }
            }
        }else{
            LOG.debug("Keputusan :"+proposedOutcome);
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
