
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import etanah.model.LanjutanTempoh;

/**
 *
 * @author Murali
 */
public class PerakuanValidation implements StageListener {

    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    Pengguna pengguna;
    FolderDokumen fd;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private static final Logger LOG = Logger.getLogger(PerakuanValidation.class);
    private String idHakmilik;
    private String idHakmilik1;
    private Hakmilik hakmilik;
    private Date tarikhLulus;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
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

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");

                String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Semakan Semula");
        if (kodNegeri.equals("04")) {
             n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar semula kepada Penolong Pegawai Tadbir / Penolong Pengarah Tanah Galian untuk semakan");
            LOG.info("++++++++++++++afterPushBack++++++++++");
        } else {
            n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " Makluman kepada Timbalan Pengarah Tanah dan Galian");
        }
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equals("04")) {
            list.add(kodPerananDAO.findById("22"));
            list.add(kodPerananDAO.findById("23"));
        } else {
            list.add(kodPerananDAO.findById("233"));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }

    private void updateNewPetak(StageContext sc) {
        for (PermohonanBangunan pb : sc.getPermohonan().getSenaraiBangunan()) {
            pb.setPermohonan(sc.getPermohonan().getPermohonanSebelum());
            strService.saveMohonBangunan(pb);
        }

    }

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

    public String getIdHakmilik1() {
        return idHakmilik1;
    }

    public void setIdHakmilik1(String idHakmilik1) {
        this.idHakmilik1 = idHakmilik1;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }
}
