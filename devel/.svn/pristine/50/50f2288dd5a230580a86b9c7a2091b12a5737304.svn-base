/**
 * Kemasukan maklumat permohonan validator. (first stage)
 *
 * @author Mohd Hairudin Mansur
 * @version 21042011
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.service.PelupusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PihakService;
import etanah.view.etanahContextListener;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class KemasukanMaklumatPermohonanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(KemasukanMaklumatPermohonanValidator.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakService pihakService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    etanah.Configuration conf;
    private Pihak pihak;
    private Pemohon pemohon;
    private List<PihakPengarah> pihakPengarahList;
    private List<PemohonHubungan> pemohonHubunganList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private HakmilikPermohonan hakmilikPermohonan;

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
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            List<Pemohon> p = pelupusanService.findPemohonByIdPemohonList(permohonan.getIdPermohonan());
//            pemohon = pelupusanService.findPemohonByIdPemohon(permohonan.getIdPermohonan());
            List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
            List<PermohonanKelompok> listMohonKelompokChild = new ArrayList<PermohonanKelompok>();
//                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "T");
            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(permohonan.getIdPermohonan());
            listMohonKelompokChild = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
            Pengguna peng = (Pengguna) context.getPengguna();
            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            for (Pemohon pem : p) {
                if (listMohonKelompok.isEmpty()) {
                    if (pem == null) {
                        context.addMessage("Sila Masukkan Maklumat Pemohon");
                        return null;
                    } else if (hakmilikPermohonanList.size() > 0) {
                        hakmilikPermohonan = hakmilikPermohonanList.get(0);
                        if (hakmilikPermohonan != null && hakmilikPermohonan.getHakmilik() == null) {
                            if (hakmilikPermohonan == null || hakmilikPermohonan.getKategoriTanahBaru() == null || hakmilikPermohonan.getBandarPekanMukimBaru() == null) {
                                context.addMessage("Sila Masukkan Maklumat Tanah Yang Dipohon");
                                return null;
                            }
                            if (hakmilikPermohonan.getLuasBaru() == null){
                                context.addMessage("Sila Masukkan Luas Terlebih Dahulu Sebelum di Hantar ke Peringkat Seterusnya. Terima Kasih");
                            }
                        } else if (hakmilikPermohonan == null) {
                            if (hakmilikPermohonan.getKategoriTanahBaru() == null || hakmilikPermohonan.getBandarPekanMukimBaru() == null) {
                                context.addMessage("Sila Masukkan Maklumat Tanah Yang Dipohon");
                                return null;
                            }
                        }
                    } else {
                        Pihak pihak = pem.getPihak();
                        if (pihak == null) {
                            context.addMessage("Sila Masukkan Maklumat Pemohon");
                            return null;
                        } else {

                            if (pihak.getJenisPengenalan().getKod().equals("U") || pihak.getJenisPengenalan().getKod().equals("D")
                                    || pihak.getJenisPengenalan().getKod().equals("S")) {
                                pihakPengarahList = pihakService.findPengarahByIDPihak(pihak.getIdPihak());
                                if (pihakPengarahList.isEmpty()) {
                                    context.addMessage("Sila Masukkan Maklumat Ahli Lembaga Pengarah");
                                    return null;
                                }
                            }

                            if (pihak.getJenisPengenalan().getKod().equals("B") || pihak.getJenisPengenalan().getKod().equals("L")
                                    || pihak.getJenisPengenalan().getKod().equals("P") || pihak.getJenisPengenalan().getKod().equals("T")
                                    || pihak.getJenisPengenalan().getKod().equals("I") || pihak.getJenisPengenalan().getKod().equals("O")
                                    || pihak.getJenisPengenalan().getKod().equals("N") || pihak.getJenisPengenalan().getKod().equals("F")) {

                                pemohonHubunganList = pelupusanService.findHubunganByIDPemohon(pem.getIdPemohon());
                                if (pemohonHubunganList.isEmpty()) {
                                    if (pem.getStatusKahwin().trim().equals("Berkahwin")) {
                                        context.addMessage("Sila Masukkan Maklumat Suami/Isteri");
                                        return null;
                                    }
                                }
//                        pemohonHubunganList1 = pelupusanService.findHubunganByIDANAK(pemohon.getIdPemohon());
//                        if (pemohonHubunganList1.isEmpty()) {
//                            context.addMessage("Sila Masukkan Maklumat Anak");
//                            return null;
//                        }
                            }

                        }

                    }
                }

            }
            //Check kelompok


            if (listMohonKelompokChild.size() > 0) {
                //Child exist
                LOG.info("Child Exist");
                context.addMessage("Permohonan ini hanya boleh diteruskan menggunakan permohonan berkelompok :" + listMohonKelompokChild.get(0).getPermohonanInduk().getIdPermohonan());
                return null;
            }

            if (listMohonKelompok.size() > 0) {
                LOG.info("SDP All Child");
                //Sdp all child
                for (PermohonanKelompok pk : listMohonKelompok) {
                    Permohonan pr = pk.getPermohonan();
                    InfoAudit ia = new InfoAudit();
                    ia = pr.getInfoAudit();
                    pr.setStatus("SD");
                    pr.setInfoAudit(ia);
                    pelupusanService.simpanPermohonan(pr);
                }
            }

            if (!senaraiHakmilik.isEmpty()) {
            //Need To generate before Noting
            LOG.info("buat urusan tukar ganti");
            for (Hakmilik h : senaraiHakmilik) {
                if (h.getNoVersiDhde() == 0) {
                    prosesTukarGanti(peng, senaraiHakmilik);

                }
            }
        }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }

        return proposedOutcome;
//        return null;
    }

    private void prosesTukarGanti(Pengguna pengguna, List<Hakmilik> senaraiHakmilik) {

        //urusan tukar ganti
        ProsesTukarGanti tukarGanti = etanahContextListener.newInstance(ProsesTukarGanti.class);
        String kodNegeri = conf.getProperty("kodNegeri");
        KodCawangan caw = null;
        if (!senaraiHakmilik.isEmpty() && ((senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("GRN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("PN") || senaraiHakmilik.get(0).getKodHakmilik().getKod().equals("HSD")))) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }

        if (!senaraiHakmilik.isEmpty()) {

            List<Permohonan> senaraiPermohonanTukarGanti = tukarGanti.doTukarGanti(kodNegeri, pengguna, senaraiHakmilik);
            if (!senaraiPermohonanTukarGanti.isEmpty()) {
                for (Permohonan p : senaraiPermohonanTukarGanti) {
                    KodUrusan ku = p.getKodUrusan();
                    try {
                        WorkFlowService.initiateTask(ku.getIdWorkflow(),
                                p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
                                p.getKodUrusan().getNama());

                        //fikri suruh pakai getidworkflow yang biasa
//                        WorkFlowService.initiateTask(ku.getIdWorkflowIntegration(),
//                            p.getIdPermohonan(), caw.getKod(), pengguna.getIdPengguna(),
//                            p.getKodUrusan().getNama());

                    } catch (Exception e) {
                        LOG.error(e);
                    }
                }
            }
        }
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

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
