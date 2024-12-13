/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.LanjutanTempoh;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.KodCawangan;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.NotifikasiService;
import java.util.*;
import java.text.*;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodCawangan;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

/**
 *
 * @author Murali
 */
public class RBHSValidator implements StageListener {

    @Inject
    private etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodCawanganDAO kodCawDao;
    private LanjutanTempoh lanjutTempoh;
    private String stageId;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(RBHSValidator.class);
    private String catatan;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private FasaPermohonan checker;
    private HakmilikPermohonan mohonHakmilik;
    private KodCawangan kodCaw;

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
        Permohonan permohonan = context.getPermohonan();
        System.out.println("------permohonan.getSenaraiHakmilik().get(0).getId()----:" + permohonan.getSenaraiHakmilik().get(0).getId());
        lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());
        checker = strService.checkLulusTolakRBHS(permohonan.getIdPermohonan(), "keputusan");
        LOG.info("----stageId-----" + context.getStageName());
        if (context.getStageName().equals("kemasukan")) {
            if (lanjutTempoh == null || lanjutTempoh.getTarikhAkhirDipohon() == null) {
                context.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Lanjutan Yang Dipohon");
                return null;
            }
        }

        if (context.getStageName().equals("keputusan")) {

            permohonan = context.getPermohonan();
            FasaPermohonan mohonFasa = new FasaPermohonan();

            if (mohonFasa != null) {
                permohonan.setKeputusan(mohonFasa.getKeputusan());
                Pengguna peng = (Pengguna) context.getPengguna();
                permohonan.setKeputusanOleh(peng);
                permohonan.setTarikhKeputusan(new Date());
                strService.updateMohon(permohonan);
                LOG.debug("----Update Keputusan Success----:");
            }
            if (checker.getKeputusan().getKod().equals("L")) {
                if (lanjutTempoh == null || lanjutTempoh.getTarikhAkhirDilulus() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Lanjutan Yang Diluluskan");
                    return null;
                }
            } else if (checker.getKeputusan().getKod().equals("T")) {
                try {
                    LanjutanTempoh lanjutTempoh2 = new LanjutanTempoh();
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    InfoAudit infoAudit = new InfoAudit();
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.add(Calendar.DAY_OF_MONTH, -10);
                    Date tarikhLulus = cal.getTime();
                    String newDate = dateFormat.format(tarikhLulus);
                    System.out.println("------tarikhLulus-------:" + newDate);
                    Date newDate2 = dateFormat.parse(newDate);
                    System.out.println("------tarikhLulus2-------:" + newDate2);
                    mohonHakmilik = strService.findMohonHakmilik(permohonan.getIdPermohonan());
                    System.out.println("------penggunaCawangan-------::" + pengguna.getKodCawangan().getKod());
                    kodCaw = strService.findByKodCaw(pengguna.getKodCawangan().getKod());
                    if (lanjutTempoh == null) {
                        lanjutTempoh2.setCawangan(kodCaw);
                        lanjutTempoh2.setTarikhAkhirDilulus(newDate2);
                        lanjutTempoh2.setHakmilikPermohonan(mohonHakmilik);
                        lanjutTempoh2.setInfoAudit(infoAudit);
                        lanjutTempoh2 = strService.saveLanjutTempoh(lanjutTempoh2);
                    } else {
                        lanjutTempoh.setCawangan(kodCaw);
                        lanjutTempoh.setTarikhAkhirDilulus(newDate2);
                        lanjutTempoh.setHakmilikPermohonan(mohonHakmilik);
                        lanjutTempoh.setInfoAudit(infoAudit);
                        lanjutTempoh = strService.saveLanjutTempoh(lanjutTempoh);
                    }
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(RBHSValidator.class.getName()).log(Level.SEVERE, null, ex);
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
        boolean result = false;
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "semakkertasptg");
            if (mohonFasa.getKeputusan() != null && mohonFasa.getKeputusan().getKod().equals("DI")) {

                lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());

                if (lanjutTempoh == null) {
                    result = false;
                } else {
                    if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                        result = false;
                    } else {
                        result = true;
                    }
                }

                if (!result) {
                    ctx.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Kelulusan");
                }
            }else{
                result = true;
            }
        } else {
            lanjutTempoh = strService.findMohonLanjutTempoh(permohonan.getSenaraiHakmilik().get(0).getId());

            if (lanjutTempoh == null) {
                result = false;
            } else {
                if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
                    result = false;
                } else {
                    result = true;
                }
            }

            if (!result) {
                ctx.addMessage(permohonan.getIdPermohonan() + " Sila Masukkan Tarikh Kelulusan");
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

    /**
     * @return the checker
     */
    public FasaPermohonan getChecker() {
        return checker;
    }

    /**
     * @param checker the checker to set
     */
    public void setChecker(FasaPermohonan checker) {
        this.checker = checker;
    }

    /**
     * @return the mohonHakmilik
     */
    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    /**
     * @param mohonHakmilik the mohonHakmilik to set
     */
    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    /**
     * @return the kodCaw
     */
    public KodCawangan getKodCaw() {
        return kodCaw;
    }

    /**
     * @param kodCaw the kodCaw to set
     */
    public void setKodCaw(KodCawangan kodCaw) {
        this.kodCaw = kodCaw;
    }
}
