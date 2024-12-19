/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.StrataPtService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@UrlBinding("/strata/janaGeranNB")
public class JanaGeranBerperingkatNBActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(JanaGeranBerperingkatNBActionBean.class);
    private static final Logger syslog = Logger.getLogger("SYSLOG");
    String idPermohonan;
    private Permohonan permohonan;
    private List<Hakmilik> senaraiHakmilikTerlibat;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    etanah.Configuration conf;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/pbbm/janaGeranPeringkatNB.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        HakmilikPermohonan hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

        String idHakmilik = null;

        if (hakmilik.getIdHakmilikInduk() != null) {
            idHakmilik = hakmilik.getIdHakmilikInduk();
        } else {
            idHakmilik = hakmilik.getIdHakmilik();
        }

        senaraiHakmilikTerlibat = strService.findHakmilikStrataByHakmilikInduk(idHakmilik);
        for (Hakmilik hakmilikTerlibat : senaraiHakmilikTerlibat) {
            HakmilikPermohonan Hakmilik1 = hakmilikPermohonanService.findHakmilikPermohonan(hakmilikTerlibat.getIdHakmilik(), idPermohonan);
            if (Hakmilik1 == null) {
                InfoAudit ia = new InfoAudit();
                HakmilikPermohonan hp1 = new HakmilikPermohonan();
                hp1.setInfoAudit(ia);
                hp1.setHakmilik(hakmilikTerlibat);
                hp1.setPermohonan(permohonan);
                strService.saveHakmilikPermohonan(hp1);
            }
        }

    }

    public Resolution janaGeran() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String msg = getContext().getRequest().getParameter("message");

        String idFolder = "";

        String dokumenPath = conf.getProperty("document.path");

        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));

        String param = getContext().getRequest().getParameter("param");

        String[] splitChar = param.split("[,\\;]");
        String[] values = null;
        String kodDoc = "";
        String[] params = null;
        Dokumen b = null;
        KodDokumen kd5 = new KodDokumen();
        String path = "";

        for (int i = 0; i < splitChar.length; i++) {
            if (!splitChar[i].equalsIgnoreCase("")) {
                LOG.debug("Str[" + i + "]:" + splitChar[i]);
                //1.==================================================================
                LOG.info("--Generating 4KDHD--::");

                String idHakmilikStrata = splitChar[i];
                String idPermohonan = permohonan.getIdPermohonan();
                values = new String[]{idPermohonan.trim(), idHakmilikStrata};
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                kodDoc = "REGB4KDHDKBET_MLK.rdf";
                kd5.setKod("DHDK");

                b = saveOrUpdateDokumen(fd, kd5, idHakmilikStrata);
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                LOG.info("::Path To save :: " + path);
                LOG.info("::Report Name ::" + kodDoc);
                syslog.info(pengguna.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilikStrata + " and saving it to:" + path);
                reportUtil.generateReport(kodDoc, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                LOG.info("--Generated 4KDHD--::");

                //2.==================================================================
                LOG.info("--Generating 4KDHK--::");

                values = new String[]{idPermohonan.trim(), idHakmilikStrata};
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                kodDoc = "REGB4KDHKKBET_MLK.rdf";
                kd5.setKod("DHKK");

                b = saveOrUpdateDokumen(fd, kd5, idHakmilikStrata);
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                LOG.info("::Path To save :: " + path);
                LOG.info("::Report Name ::" + kodDoc);
                syslog.info(pengguna.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilikStrata + " and saving it to:" + path);
                reportUtil.generateReport(kodDoc, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                LOG.info("--Generated 4KDHK--::");

                //3.==================================================================
                LOG.info("--Generating BSKDK--::");

                values = new String[]{idPermohonan.trim(), idHakmilikStrata};
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                kodDoc = "REGBSKDHDKBET_MLK.rdf";
                kd5.setKod("BSKDK");

                b = saveOrUpdateDokumen(fd, kd5, idHakmilikStrata);
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                LOG.info("::Path To save :: " + path);
                LOG.info("::Report Name ::" + kodDoc);
                syslog.info(pengguna.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilikStrata + " and saving it to:" + path);
                reportUtil.generateReport(kodDoc, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                LOG.info("--Generated BSKDK--::");

                //4.==================================================================
                LOG.info("--Generating BSKKK--::");

                values = new String[]{idPermohonan.trim(), idHakmilikStrata};
                params = new String[]{"p_id_mohon", "p_id_hakmilik"};
                kodDoc = "REGBSKDHKKBET_MLK.rdf";
                kd5.setKod("BSKKK");

                b = saveOrUpdateDokumen(fd, kd5, idHakmilikStrata);
                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
                LOG.info("::Path To save :: " + path);
                LOG.info("::Report Name ::" + kodDoc);
                syslog.info(pengguna.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + idHakmilikStrata + " and saving it to:" + path);
                reportUtil.generateReport(kodDoc, params, values, dokumenPath + path, pengguna);
                updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
                LOG.info("--Generated BSKKK--::");

            }
        }

        msg = "Geran Telah Dijana";
        addSimpleMessage(msg);

        return new RedirectResolution(JanaGeranBerperingkatNBActionBean.class, "showForm");
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            ia = doc.getInfoAudit();
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod() + "(" + id + ")");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_SULIT);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Hakmilik> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<Hakmilik> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
