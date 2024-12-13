 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermitDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Hakmilik;
import etanah.model.Permit;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.ValidationService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisReport;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Shazwan 1 January 2012
 */
public class ReportV2Validator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    SyerValidationService syerService;
    @Inject
    ValidationService validationService;
    @Inject
    TabManager tabManager;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    NotisService notisService;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PelupusanService plpservice;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermitDAO permitDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permit permit;
    private Permohonan permohonan;
    public final static String PERMIT_SEQ = "NOPERMIT_SEQ";
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private static final Logger LOGGER = Logger.getLogger(ReportValidator.class);
    private static Pengguna pengguna;
    private String stage;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Permohonan permohonan = context.getPermohonan();
        pengguna = context.getPengguna();
        stage = context.getStageName();
        KodUrusan kodUrusan = permohonan.getKodUrusan();

        String kodNegeri = conf.getProperty("kodNegeri");

        if (permohonan != null) {

            String gen1 = "";
            String gen2 = "";
            String code1 = "";
            String code2 = "";
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String path2 = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen dokumen = null;
            KodDokumen kodDok = null;
            hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
            if (hakmilikPermohonanList.size() > 1) {
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
            } else {
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
            }
            FolderDokumen folderDok = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            //                List<Task> task = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

            int numUrusan = this.getListUrusan(permohonan);
            if (numUrusan > 0) {
                generateReport(stage, numUrusan, kodNegeri, kodUrusan, gen1, code1, folderDok, dokumen, path, permohonan, hakmilikPermohonan, params, values, gen2, code2, path2, dokumenPath);
            }
        }
    }

    /**
     *
     * @author Shazwan
     * @version 1.0 date 11/1/2012
     */
    private int getListUrusan(Permohonan permohonan) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PBMT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 2
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 3
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 5
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 8
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 9
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 12
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 13
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 14
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 15
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 18
                : permohonan.getKodUrusan().getKod().equals("RAYA") ? 19
                : permohonan.getKodUrusan().getKod().equals("RAYL") ? 20
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 21
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 22
                : permohonan.getKodUrusan().getKod().equals("JMRE") ? 23
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 24
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 25
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 26
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 27
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 28
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 29
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 30
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 31
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 32
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 33
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 34
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 35
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 36
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 37
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 38
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 39
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 40
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 41
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 42
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 43
                : permohonan.getKodUrusan().getKod().equals("LPJH") ? 44
                : 0;
        return numUrusan;

    }

    private void generateReport(String stage, int numUrusan, String kodNegeri, KodUrusan kodUrusan, String gen1, String code1, FolderDokumen folderDok, Dokumen dokumen,
            String path, Permohonan p, HakmilikPermohonan hp, String[] params, String[] values, String gen2, String code2, String path2, String dokumenPath) {

        DisReport disReport = new DisReport();
        HashMap reportMap = new HashMap(disReport.getReportMap(numUrusan, stage, kodNegeri, p, hp));

        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :" + reportMap.size() / 2);

        for (int i = 1; i <= reportMap.size() / 2; i++) {
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("reportKod" + String.valueOf(i)).toString()), p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMap.get("reportName" + String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen(), reportUtil.getContentType());

//               public Resolution generatePermit() {
//            kodUrusan = permohonan.getKodUrusan();
            String kodCawangan = p.getCawangan().getKod();

            String kodPerserahan = kodUrusan.getKod();
            if (kodPerserahan.equals("PBPTG") || kodPerserahan.equals("PBPTD")) {
                permit = getPermitByIdMohon(p.getIdPermohonan());
                 LOGGER.info("TEST1");
                String noPermitLama = permit.getNoPermitBaru();
                LOGGER.info("TEST");
                if (noPermitLama == null) {
                    Long id = (getSerialNo(PERMIT_SEQ));
                    String noPermit = permit.getKodJenisPermit().getKod() + "04" + kodCawangan + id;

//            log.info("id Permit = " + id);
                    permit.setNoPermitBaru(noPermit);
                    permitDAO.saveOrUpdate(permit);
//            onGenerateReports();
//            return showForm2();
                }
            }
        }
    }

    public Permit getPermitByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Permit m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (Permit) q.uniqueResult();
    }

    protected long getSerialNo(String sequenceName) {
        Connection c = sessionProvider.get().connection();
        Statement s = null;
        ResultSet rs = null;
        try {
            s = c.createStatement();
            // TODO remove Oracle specific
            rs = s.executeQuery("select " + sequenceName + ".nextval from dual");
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage() + " Ensure SEQUENCE " + sequenceName
                    + "  exists!");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                }
            }
            // if (c != null) try { c.close(); } catch (Exception e) {}
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            if (!doc.getKodDokumen().getKod().equals("RENC")) {
                ia.setTarikhKemaskini(new java.util.Date());
            }
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama() + "-" + id);
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
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }
}
