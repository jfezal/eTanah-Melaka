/**
 * Jabatan Teknikal Validator. (first stage)
 *
 * @author Shazwan
 * @version 14092011
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.dao.KodStatusUlasanJabatanTeknikalDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusUlasanJabatanTeknikal;
import etanah.model.KonfigurasiSistem;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.PortalPengguna;
import etanah.model.UlasanJabatanTeknikal;
import etanah.service.JTeknikalService;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.service.uam.UamService;
import etanah.service.PengambilanService;
import etanah.util.StringUtils;
import etanah.view.uam.MailService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class JTeknikalValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(JTeknikalValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    JTeknikalService jTeknikalService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    KodStatusUlasanJabatanTeknikalDAO kodStatusUlasanJabatanTeknikalDAO;
    @Inject
    private etanah.Configuration conf;
    private static Pengguna pengguna;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        try {
            Permohonan permohonan = context.getPermohonan();
            pengguna = context.getPengguna();
            KonfigurasiSistem ks = new KonfigurasiSistem();
            ks = jTeknikalService.findKonfigSistemByNama("ulasan_jtek_portal");
            if (ks != null) {
                if (ks.getNilai().equalsIgnoreCase("Y")) {
                    List<PermohonanRujukanLuar> listmohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
                    List<UlasanJabatanTeknikal> listujt = new ArrayList<UlasanJabatanTeknikal>();
                    if (permohonan.getKodUrusan().getKod().equals("PTSP")) {
                        listmohonRujLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(permohonan.getIdPermohonan());
                    } else {
                        listmohonRujLuar =  pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(permohonan.getIdPermohonan());
                    }
                    listujt = jTeknikalService.findUlasanJabTeknikalbyIdMohon(permohonan.getIdPermohonan());

                    for (PermohonanRujukanLuar prl : listmohonRujLuar) {
                        if (listujt.isEmpty() || listujt.size() <= 0) {
                            if (prl.getAgensi().getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
                                InfoAudit ia = new InfoAudit();
                                UlasanJabatanTeknikal ujt = new UlasanJabatanTeknikal();
                                ia.setDimasukOleh(pengguna);
                                ia.setTarikhMasuk(new java.util.Date());
                                ujt.setInfoAudit(ia);
                                ujt.setKodCawangan(permohonan.getCawangan());
                                ujt.setPermohonan(permohonan);
                                ujt.setKodStatusUlasanJabatanTeknikal(kodStatusUlasanJabatanTeknikalDAO.findById("BAR"));
                                ujt.setKodAgensi(prl.getAgensi());
//                                ujt.setNoRuj(!StringUtils.isBlank(prl.getNoRujukan())?prl.getNoRujukan():new String());
//                                ujt.setUlasan(!StringUtils.isBlank(prl.getUlasan())?prl.getUlasan():new String());
                                String kodNegeri = conf.getKodNegeri();
                                MailService mail = new MailService();
                                String[] email = new String[prl.getAgensi().getSenaraiPortalPengguna().size()];
                                int a = 0;
                                for (PortalPengguna pp : prl.getAgensi().getSenaraiPortalPengguna()) {
                                    email[a] = pp.getEmail();
                                    a++;
                                }
                                String subject = "Notifikasi ulasan Jabatan Teknikal";
                                Thread.sleep(2000);
                                mail.sendEmailHTML(email, subject, mail.HTMLJtek(permohonan), kodNegeri);
                                try {
                                    jTeknikalService.saveOrUpdateUlasanJTek(ujt);
                                } catch (Exception e) {
                                    LOG.error(e.getMessage());
                                    return null;
                                }
                            }
                        } else {
                            for (UlasanJabatanTeknikal ujt : listujt) {
                                if (ujt.getKodAgensi().getKod().equalsIgnoreCase(prl.getAgensi().getKod()) && prl.getAgensi().getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
                                    InfoAudit ia = new InfoAudit();
                                    ia = ujt.getInfoAudit();
                                    ia.setDiKemaskiniOleh(pengguna);
                                    ia.setTarikhKemaskini(new java.util.Date());
                                    ujt.setInfoAudit(ia);
//                                    ujt.setUlasan(prl.getUlasan());

                                    try {

                                        jTeknikalService.saveOrUpdateUlasanJTek(ujt);

                                    } catch (Exception e) {
                                        LOG.error(e.getMessage());
                                        return proposedOutcome;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
       
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
