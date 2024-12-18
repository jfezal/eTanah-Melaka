/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.LanjutanTempoh;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.NotifikasiService;
import java.util.Date;
import etanah.model.FasaPermohonan;
import etanah.model.KodUrusan;

/**
 *
 * @author Murali
 */
public class LanjutanTempohValidation implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private LanjutanTempoh lanjutTempoh;
    private String stageId;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(RBHSValidator.class);
    private String catatan;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @Override
    public boolean beforeStart(StageContext context) {
        LOG.info("--------------------beforeStart--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Pengguna pengguna = context.getPengguna();
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
        KodUrusan kod = new KodUrusan();

        if (mohon.getKodUrusan().getKod().equals("RTHS") || mohon.getKodUrusan().getKod().equals("RTPS")) {

            if (context.getStageName().equals("kemasukan")) {

                permohonan = context.getPermohonan();
                System.out.println("---------idMH2---------" + permohonan.getSenaraiHakmilik().get(0).getId());
                lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
                if (lanjutTempoh == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan tarikh pohon lanjutan.");
                    return null;
                } else if (lanjutTempoh.getTarikhAkhirDipohon() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " Sila masukkan tarikh pohon lanjutan.");
                    return null;

                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("RTHS") || mohon.getKodUrusan().getKod().equals("RTPS")) {

            if (context.getStageName().equals("keputusan")) {
                if (mohonFasa.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    permohonan = context.getPermohonan();
                    System.out.println("---------idMH2 Keputusan---------" + permohonan.getSenaraiHakmilik().get(0).getId());

                    lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
                    if (lanjutTempoh == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan tarikh kelulusan.");
                        return null;
                    } else if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                        context.addMessage(permohonan.getIdPermohonan() + " Sila masukkan tarikh kelulusan.");
                        return null;

                    }
                    if (mohonFasa != null) {
                        permohonan.setKeputusan(mohonFasa.getKeputusan());
                        Pengguna peng = (Pengguna) context.getPengguna();
                        permohonan.setKeputusanOleh(peng);
                        permohonan.setTarikhKeputusan(new Date());
                        strService.updateMohon(permohonan);
                        LOG.debug("----Update Keputusan Success----:");
                    }
                } else {
                    return proposedOutcome;
                }

            }
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        System.out.println("--------------------afterComplete--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        System.out.println("--------------------beforeGenerateReports--------------------");
        Pengguna pengguna = ctx.getPengguna();
        Permohonan permohonan = ctx.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");
        KodUrusan kod = new KodUrusan();


        boolean result = false;
        if (permohonan.getKodUrusan().getKod().equals("RTHS") || permohonan.getKodUrusan().getKod().equals("RTPS")) {
            if (ctx.getStageName().equals("keputusan")) {
                if (mohonFasa.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());

                    if (lanjutTempoh == null) {
                        ctx.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Kelulusan");
                        result = false;
                    } else {
                        if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                            ctx.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Kelulusan");
                            result = false;
                        } else {
                            result = true;
                        }
                    }
                } else {
                    result = true;
                }

            }

        }
        return result;
    }

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
