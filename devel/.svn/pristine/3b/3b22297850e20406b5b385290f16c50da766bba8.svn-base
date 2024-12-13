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
import etanah.model.PegawaiPenyiasat;

/**
 *
 * @author Murali
 */
public class PenguatkuasaValidation implements StageListener {

    @Inject
    StrataPtService sservice;
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
    PegawaiPenyiasat pegawaiSiasat;
    
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
        String idPermohonan = mohon.getIdPermohonan();
        
        mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan");
        KodUrusan kod = new KodUrusan();
        permohonan = permohonanDAO.findById(idPermohonan);

        if (mohon.getKodUrusan().getKod().equals("P8") || mohon.getKodUrusan().getKod().equals("P14A")
                || mohon.getKodUrusan().getKod().equals("P22A") || mohon.getKodUrusan().getKod().equals("P22B")) {

            if (context.getStageName().equals("semaklaporan")) {
                if (mohonFasa.getKeputusan() != null) {                    
                    if (mohonFasa.getKeputusan().getKod().equalsIgnoreCase("AK")) {
                        pegawaiSiasat = sservice.findPegawaiSiasatByIdPermohonan(idPermohonan);
                        
                        if(pegawaiSiasat == null){
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan maklumat Pegawai Penyiasat. ");
                            return null;
                        }
                    } else if (mohonFasa.getKeputusan().getKod().equalsIgnoreCase("TK")) {
                        if(mohonFasa.getUlasan() == null){
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan ulasan keputusan. ");
                            return null;
                        }else if(mohonFasa.getUlasan().length() == 0){
                            context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan ulasan keputusan. ");
                            return null;
                        }
                    }
                    
                    permohonan.setKeputusan(mohonFasa.getKeputusan());
                    Pengguna peng = (Pengguna) context.getPengguna();
                    permohonan.setKeputusanOleh(peng);
                    permohonan.setTarikhKeputusan(new Date());
                    mohonFasa.setTarikhKeputusan(new Date());
                    strService.updateMohon(permohonan);
                    LOG.debug("----Update Keputusan Success----:");
                } else {
                    context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan keputusan siasatan. ");
                    return null;
                }

                /*
                 permohonan = context.getPermohonan();

                 if (mohonFasa.getKeputusan() != null) {
                 permohonan.setKeputusan(mohonFasa.getKeputusan());
                 Pengguna peng = (Pengguna) context.getPengguna();
                 permohonan.setKeputusanOleh(peng);
                 permohonan.setTarikhKeputusan(new Date());
                 mohonFasa.setTarikhKeputusan(new Date());
                 strService.updateMohon(permohonan);
                 LOG.debug("----Update Keputusan Success----:");
                 } else {
                 context.addMessage(permohonan.getIdPermohonan() + " - Sila masukkan keputusan siasatan. ");
                 return null;
                 }
                 * */
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
        return true;
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
