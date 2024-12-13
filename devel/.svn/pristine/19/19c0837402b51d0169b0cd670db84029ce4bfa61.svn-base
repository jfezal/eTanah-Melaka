/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
//import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.KodDokumen;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.dao.FolderDokumenDAO;
import etanah.service.SemakDokumenService;
import etanah.dao.KodDokumenDAO;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.report.ReportUtil;
import java.io.File;
import etanah.model.KodKlasifikasi;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.DevIntegrationService;
import etanah.service.PembangunanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;

/**
 *
 * @author nursyazwani
 */
public class KeputusanNotification implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PembangunanService devServ;
    @Inject
    DevIntegrationService dis;
    @Inject
    SBMSIntegrationFlowService sBMSIntegrationFlowService;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private static final Logger LOG = Logger.getLogger(KeputusanNotification.class);
    private String stage;
    private Pengguna pengguna;
    private FasaPermohonan fasaPermohonan;

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan p = context.getPermohonan();
        pengguna = context.getPengguna();
        KodUrusan ku = p.getKodUrusan();
        String kodNegeri = conf.getProperty("kodNegeri");

        if (p != null) {
            try {

                String gen1 = "";
                String gen2 = "";
                String code1 = "";
                String code2 = "";
                String[] params = new String[]{"p_id_mohon"};
                String[] values = new String[]{p.getIdPermohonan()};
                String path = "";
                String path2 = "";
                String dokumenPath = conf.getProperty("document.path");
                Dokumen d = null;
                KodDokumen kd = null;
                String kpsn = p.getKeputusan().getKod();

                FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());
                List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
                LOG.info("list task size: " + l.size());

                if (kodNegeri.equalsIgnoreCase("04")) {	// melaka
                    generateRptMelaka(l, ku, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath, kpsn);
                }
//                else if(kodNegeri.equalsIgnoreCase("05")) {	// ns
//                	generateRptNS(l, gen1, code1, kd, fd, d, path, p, params, values, gen2, code2, path2, dokumenPath);
//                }

            } catch (WorkflowException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }

    /**
     *
     * @param l
     * @param gen1
     * @param code1
     * @param kd
     * @param fd
     * @param d
     * @param path
     * @param p
     * @param params
     * @param values
     * @param gen2
     * @param code2
     * @param path2
     * @param dokumenPath
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private void generateRptMelaka(List<Task> l, KodUrusan ku, String gen1, String code1, KodDokumen kd, FolderDokumen fd, Dokumen d,
            String path, Permohonan p, String[] params, String[] values, String gen2, String code2, String path2,
            String dokumenPath, String kpsn) {


        for (Task t : l) {
            if (t.getSystemAttributes().getStage() != null) {
                stage = t.getSystemAttributes().getStage();
                LOG.info(stage);

                if (stage.contentEquals("sediacetaksrtkpsn") || stage.contentEquals("sediasrtkpsn")
                        || stage.contentEquals("sediasrtkpsntolak") || stage.contentEquals("cetaknilaianpremiumcetaklulus")) {
                    if (kpsn.contentEquals("L")) {
                        gen1 = "DEVSLA_MLK.rdf";
                        code1 = "SLSB";
                        kd = kodDokumenDAO.findById(code1);
                        LOG.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    } else if (kpsn.contentEquals("T")) {
                        gen1 = "DEVKpsnTSTolak_MLK.rdf";
                        code1 = "STT";
                        kd = kodDokumenDAO.findById(code1);
                        LOG.info(kd);
                        d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
                        path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                        reportUtil.generateReport(gen1, params, values, dokumenPath + path, pengguna);
                        updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                    }
                }
            } else {
                // do nothing
            }
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + kd.getNama());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        String idPermohonan = context.getPermohonan().getIdPermohonan();
        Permohonan permohonan = context.getPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");

        keputusan = context.getPermohonan().getKeputusan().getNama();

        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan JKBB");
        n.setMesej("Permohonan " + context.getPermohonan().getKodUrusan().getNama() + " telah mendapat keputusan "
                + keputusan + " daripada Jawatankuasa Belah Bahagi Tanah " + "(" + idPermohonan + ")");
        Pengguna p = context.getPengguna();
        if (!p.getKodCawangan().getKod().equalsIgnoreCase("00")) {
            n.setCawangan(p.getKodCawangan());
        } else {
            n.setCawangan(context.getPermohonan().getCawangan());
        }

        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equalsIgnoreCase("04")) {
            list.add(kodPerananDAO.findById("77"));// melaka - ptd
            list.add(kodPerananDAO.findById("70"));// melaka - pembantu tadbir
        }
        if (kodNegeri.equalsIgnoreCase("05")) {
            list.add(kodPerananDAO.findById("225"));// n9 - ptd
            list.add(kodPerananDAO.findById("80"));// n9 - pembantu tadbir
        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);

        if (!p.getKodCawangan().getKod().equalsIgnoreCase("00")) {
            notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);
        } else {
            notifikasiService.addRolesToNotifikasi(n, context.getPermohonan().getCawangan(), list);
        }

        context.addMessage(" Tugasan telah dihantar.");

        if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSKKT") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSKSN")
                || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSBSN") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSPSN") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
            //tiada integrate disini
        } else {
            LOG.info("---------------integration-----------------------start");
            initiate(context, proposedOutcome, context.getPermohonan().getKeputusan().getKod());
            LOG.info("---------------integration-----------------------end");
        }

        String outcome = "";
        if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PSMT") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PSBT")
                || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("SBMS") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSPSS")
                || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCS") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCB")
                || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PYTN") || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSKKT")
                || context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSPSN")) {
            try {
                outcome = checkstageID(context);
            } catch (WorkflowException ex) {
                LOG.error(ex.getMessage());
                return null;
            }
            if (outcome != null && !outcome.equals("")) {
                proposedOutcome = outcome;
                LOG.info("----------outcome----------:" + outcome);
            }
        }
        LOG.info("----------proposedOutcome----------:" + proposedOutcome);
        if (context.getStageName().equals("sedia_surat_dokumen") && context.getPermohonan().getKodUrusan().getKod().equals("SBMS")) {
            sBMSIntegrationFlowService.insertTugasanTable(permohonan, "TPTH", context.getPengguna().getIdPengguna());
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    void initiate(StageContext context, String proposedOutcome, String kpsn) {

        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        List<HakmilikPermohonan> senaraiHP = permohonan.getSenaraiHakmilik();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        for (int i = 0; i < senaraiHP.size(); i++) {
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(i);
            Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            senaraiHakmilik.add(h);
        }
        KodUrusan kod = new KodUrusan();
        LOG.info("----------value of kpsn----------:" + kpsn);
        if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCS")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("PSL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("PSB");
            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PPCB")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("PBL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("PBB");
            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PYTN")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("CL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("CB");
            }
        } /*else if(context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSKKT")){
         if(kpsn.equalsIgnoreCase("L")){
         kod = kodUrusanDAO.findById("TSSKL");
         }else if(kpsn.equalsIgnoreCase("T")){
         kod = kodUrusanDAO.findById("TSSKB");
         }
         }
         else if(context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSBSN")){
         if(kpsn.equalsIgnoreCase("L")){
         kod = kodUrusanDAO.findById("TSSKL");
         }else if(kpsn.equalsIgnoreCase("T")){
         kod = kodUrusanDAO.findById("TSSKB");
         }
         }
         else if(context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSPSN")){
         if(kpsn.equalsIgnoreCase("L")){
         kod = kodUrusanDAO.findById("TSSKL");
         }else if(kpsn.equalsIgnoreCase("T")){
         kod = kodUrusanDAO.findById("TSSKB");
         }
         }
         else if(context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSKSN")){
         if(kpsn.equalsIgnoreCase("L")){
         kod = kodUrusanDAO.findById("TSSKL");
         }else if(kpsn.equalsIgnoreCase("T")){
         kod = kodUrusanDAO.findById("TSSKB");
         }
         }*/ else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("SBMS")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("SBKSL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("SBKSB");
            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PSMT")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("SBTL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("SBTB");
            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("PSBT")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("SBSTL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("SBSTB");
            }
        } else if (context.getPermohonan().getKodUrusan().getKod().equalsIgnoreCase("TSPSS")) {
            if (kpsn.equalsIgnoreCase("L")) {
                kod = kodUrusanDAO.findById("SSKPL");
            } else if (kpsn.equalsIgnoreCase("T")) {
                kod = kodUrusanDAO.findById("SSKPB");
            }

        }
//        LOG.info(kod.getNama());
//        LOG.info(permohonan.getFolderDokumen());
//         generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
        LOG.info(" ***************** pengguna ------------:" + peng);
        //dis.intRegKelulusan(kod, peng, senaraiHakmilik, permohonan, "6", "T");
        dis.intRegPermohonan(kod, peng, dis.setListHakmilikByMohonHakmilik(permohonan.getSenaraiHakmilik()), permohonan, "6", "T", context.getStageName());

    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPCS") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPCB")
                    || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PYTN")) {
                if (stage.contentEquals("cetaksuratkpsnjkbbrekodtkhtt") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    //value = "_WORKFLOW_DIRECTIVE_WITHDRAW";  
                    value = "T";
                    return value;
                }
            }
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSKKT") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSN")) {
                if (stage.contentEquals("cetaksuratkpsnjkbbrekodtkhtt") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    LOG.info("------TSKKT--------Tolak----------");
                    value = "T";
                    return value;
                }
            }
            // added for SBMS
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBMS") && stage.contentEquals("cetaksrtjkbbrekodtkhtt") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                LOG.info("------SBMS--------Tolak----------");
                value = "T";
                return value;
            }
            // added for TSPSS
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS") && stage.contentEquals("cetaksuratkpsnjkbbrekodtkhtt") && permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                LOG.info("------TSPSS--------Tolak----------");
                value = "T";
                return value;
            }
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSPSS") && stage.contentEquals("cetaksuratkpsnjkbbrekodtkhtt")) {
                value = "PH";
                return value;
            }
            if (stage.contentEquals("g_charting_lulus") || stage.contentEquals("g_charting_tolak")) {
                if (permohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
                    value = "CL";
                } else if (permohonan.getKeputusan().getKod().equalsIgnoreCase("T")) {
                    value = "CT";
                }
                return value;
            }
            if (stage.contentEquals("perakuanjkbbptg")) {
                fasaPermohonan = devServ.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), stage);
                if (fasaPermohonan.getKeputusan().equals(null)) {
                    value = null;
                }
                return value;
            }
        }
        return value;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}
