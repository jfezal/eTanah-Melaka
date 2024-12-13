/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.validator;

import com.google.inject.Inject;
import etanah.JabatanConstants;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Kompaun;
import etanah.model.LaporanTanah;
import etanah.model.Notifikasi;
import etanah.model.Notis;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanNota;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.gis.PelanGIS;
import etanah.report.ReportUtil;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.service.EnforceService;
import etanah.service.NotifikasiService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.TaskDebugService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.ProsesTukarGanti;
import etanah.view.stripes.penguatkuasaan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import etanah.view.etanahContextListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author sitifariza.hanim
 */
public class GenerateKePTG implements StageListener {

    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    private KodRujukanDAO kodRujDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    private GeneratorIdPermohonan idGenMohon;
    @Inject
    private TaskDebugService tds;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private PermohonanNota permohonanNota;
    private String stageId;
    private static final Logger LOG = Logger.getLogger(GenerateKePTG.class);
    private String keputusan;
    private List<HakmilikPermohonan> hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiTanahMilik;
    private List<AduanOrangKenaSyak> senaraiDakwaOks;
    private List<BarangRampasan> senaraiDakwaBarangRampasan;
    private List<OperasiPenguatkuasaan> listOp;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private List<PermohonanRujukanLuar> mohonRujLuar;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private boolean statusDakwa = Boolean.FALSE;
    private List<HakmilikPermohonan> senaraiTanahMilikKerajaan;
    private List<HakmilikPermohonan> senaraiTanahMilikPersendirian;
    private Boolean tanahMilik = Boolean.FALSE;
    private Boolean tanahKerajaan = Boolean.FALSE;
    String senaraiReport[], nama, kod;
    ArrayList reportName = new ArrayList<String>();
    private String kodUrusan;
    private char Y;
    private char T;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    @Override
    public String beforeComplete(StageContext context, String proposedOutcome, String kodUrusan) {
        Permohonan permohonan = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();

//        stageId = context.getStageName();
        stageId = "semak_kertas_mmkn3";
        LOG.info("--------------stage id------------- : " + stageId);

        if (conf.getProperty("kodNegeri").equals("04")) {
            if (stageId.equalsIgnoreCase("semak3_mmkn1")) {//semak3_mmkn1
                LOG.debug("kod urusan : " + kodUrusan + " ----:");
                KodUrusan kod_urusan = kodUrusanDAO.findById(kodUrusan);
                kod_urusan.setKePTG(Y);
            } else if (stageId.equalsIgnoreCase("maklum_kpsn_mmkn4")) {
                LOG.debug("kod urusan : " + kodUrusan + " ----:");
                KodUrusan kod_urusan = kodUrusanDAO.findById(kodUrusan);
                kod_urusan.setKePTG(T);
            }  else if (stageId.equalsIgnoreCase("semak_kertas_mmkn3")) {
                LOG.debug("kod urusan : " + kodUrusan + " ----:");
                KodUrusan kod_urusan = kodUrusanDAO.findById(kodUrusan);
                kod_urusan.setKePTG(Y);
            }   else if (stageId.equalsIgnoreCase("maklum_kpsn_mmkn")) {
                LOG.debug("kod urusan : " + kodUrusan + " ----:");
                KodUrusan kod_urusan = kodUrusanDAO.findById(kodUrusan);
                kod_urusan.setKePTG(T);
            }
        }

//        return proposedOutcome;
          return null;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public void onGenerateReports(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
