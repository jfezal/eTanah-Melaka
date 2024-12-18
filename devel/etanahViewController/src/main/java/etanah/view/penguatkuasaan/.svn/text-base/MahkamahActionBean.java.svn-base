/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.EnforceService;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodPerananRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarPerananDAO;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.Dokumen;
import etanah.model.KodAgensi;
import etanah.model.KodPerananRujukanLuar;

import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuarPeranan;
import etanah.service.BPelService;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/mahkamah")
public class MahkamahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MahkamahActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanRujukanLuarPerananDAO permohonanRujukanLuarPerananDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodPerananRujukanLuarDAO kodPerananRujukanLuarDAO;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private KodCawangan cawangan;
    private KodDokumen koddokumen;
    private KodRujukan kodRujukan;
    private String idRujukan;
    private String tarikhSidang;
    private String tarikhRujukan;
    private String tarikhSidangMH;
    private String catatan;
    private String noRujukan;
    private String namaSidang;
    private KodNegeri kodNegeri;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan;
    private PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan;
    private String idPermohonan;
    private String kategoriMahkamah;
    private String recordCount;
    private String stageId;
    private Pengguna pguna;
    private String kodNegeriPermohonan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("penguatkuasaan/keputusan_mahkamah_popup.jsp").addParameter("popup", "true");
    }

    public Resolution popupAddRecord() {
        return new JSP("penguatkuasaan/popup_tambah_mahkamah.jsp").addParameter("popup", "true");
    }

    public Resolution popupEditRecord() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
            if (permohonanRujukanLuar.getAgensi() != null) {
                kategoriMahkamah = permohonanRujukanLuar.getAgensi().getKod();
            }
            namaSidang = permohonanRujukanLuar.getNamaSidang();
            noRujukan = permohonanRujukanLuar.getNoRujukan();
            catatan = permohonanRujukanLuar.getCatatan();
            if (permohonanRujukanLuar.getTarikhSidang() != null) {
                tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
            }
            if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
            }


        }
        return new JSP("penguatkuasaan/popup_edit_mahkamah.jsp").addParameter("popup", "true");
    }

    public Resolution popupViewRecord() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
        }
        return new JSP("penguatkuasaan/popup_view_mahkamah.jsp").addParameter("popup", "true");
    }

    public Resolution deletePeranan() {
        logger.info("--------------deletePeranan--------------");
        String idPasukan = getContext().getRequest().getParameter("idPasukan");
        try {
            if (idPasukan != null) {
                permohonanRujukanLuarPeranan = permohonanRujukanLuarPerananDAO.findById(Long.parseLong(idPasukan));
            }
            enforcementService.deletePeranan(permohonanRujukanLuarPeranan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new JSP("penguatkuasaan/popup_edit_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution popupedit() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        return new JSP("penguatkuasaan/keputusan_mahkamah_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(MahkamahActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        kodNegeriPermohonan = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
        }
        String kodN = ctx.getKodNegeri();
        kodNegeri = new KodNegeri();
        kodNegeri.setKod(kodN);
        kodNegeri = kodNegeriDAO.findById(kodN);

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }

    }

    public Resolution simpanPopup() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();
        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");

        permohonanRujukanLuar = new PermohonanRujukanLuar();
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(cawangan);
        if (getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            permohonanRujukanLuar.setTarikhSidang(null);
        }
        if (!getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        if (getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
            permohonanRujukanLuar.setTarikhRujukan(null);
        }
        if (!getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
            tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        }
        catatan = getContext().getRequest().getParameter("catatan");
        permohonanRujukanLuar.setCatatan(catatan);
        namaSidang = getContext().getRequest().getParameter("namaSidang");
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        permohonanRujukanLuar.setNoRujukan(noRujukan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanRecord() throws ParseException {
        logger.info("--------------simpanRecord--------------");

        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");

        permohonanRujukanLuar = new PermohonanRujukanLuar();
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        if (getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            permohonanRujukanLuar.setTarikhSidang(null);
        }
        if (!getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        if (getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
            permohonanRujukanLuar.setTarikhRujukan(null);
        }
        if (!getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
            tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        }
        if (!kategoriMahkamah.isEmpty()) {
            KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
            permohonanRujukanLuar.setAgensi(agensi);
        }
        catatan = getContext().getRequest().getParameter("catatan");
        permohonanRujukanLuar.setCatatan(catatan);
        namaSidang = getContext().getRequest().getParameter("namaSidang");
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        permohonanRujukanLuar.setNoRujukan(noRujukan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanRujukan(permohonanRujukanLuar);


        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");

            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {

                permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();

                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);

                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);

                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanRecordSek422() throws ParseException {
        logger.info("--------------simpanRecordSek422--------------");

        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");

        permohonanRujukanLuar = new PermohonanRujukanLuar();
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());

        if (stageId.equalsIgnoreCase("rekod_trh_sebutan")) {
            if (!kategoriMahkamah.isEmpty()) {
                KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
                permohonanRujukanLuar.setAgensi(agensi);
            }
            if (getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
                permohonanRujukanLuar.setTarikhSidang(null);
            }
            if (!getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
                tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
            }
            namaSidang = getContext().getRequest().getParameter("namaSidang");
            permohonanRujukanLuar.setNamaSidang(namaSidang);
            noRujukan = getContext().getRequest().getParameter("noRujukan");
            permohonanRujukanLuar.setNoRujukan(noRujukan);

        }


        if (stageId.equalsIgnoreCase("kemaskini_keputusan") || stageId.equalsIgnoreCase("kemaskini_rekod_kpsn")) {
            if (getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
                permohonanRujukanLuar.setTarikhRujukan(null);
            }
            if (!getContext().getRequest().getParameter("tarikhRujukan").isEmpty()) {
                tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
            }
            catatan = getContext().getRequest().getParameter("catatan");
            permohonanRujukanLuar.setCatatan(catatan);
        }

        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanRujukan(permohonanRujukanLuar);


        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");

            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {

                permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();

                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);

                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);

                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution editRecord() throws ParseException {
        logger.info("-------edit record-----------");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");

        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");

        InfoAudit ia = new InfoAudit();

        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        if (tarikhSidang != null) {
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        if (tarikhRujukan != null) {
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        }
        permohonanRujukanLuar.setCatatan(catatan);
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        permohonanRujukanLuar.setNoRujukan(noRujukan);
        permohonanRujukanLuar.setInfoAudit(ia);

        if (!kategoriMahkamah.isEmpty()) {
            KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
            permohonanRujukanLuar.setAgensi(agensi);
        }

        enforcementService.simpanRujukan(permohonanRujukanLuar);

        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");

            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {

                if (permohonanRujukanLuar != null) {
                    senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
                }

                if (senaraipermohonanRujukanLuarPeranan.size() != 0 && i < senaraipermohonanRujukanLuarPeranan.size()) {
                    Long id = senaraipermohonanRujukanLuarPeranan.get(i).getIdPeranan();
                    if (id != null) {
                        permohonanRujukanLuarPeranan = enforcementService.findPeranan(id);
                    }
                } else {
                    permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();
                }

                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);

                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);

                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution editRecordSek422() throws ParseException {
        logger.info("-------editRecordSek422-----------");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");

        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");

        InfoAudit ia = new InfoAudit();

        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        if (stageId.equalsIgnoreCase("rekod_trh_sebutan")) {
            if (tarikhSidang != null) {
                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
            }

            permohonanRujukanLuar.setNamaSidang(namaSidang);
            permohonanRujukanLuar.setNoRujukan(noRujukan);
            permohonanRujukanLuar.setInfoAudit(ia);

            if (!kategoriMahkamah.isEmpty()) {
                KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
                permohonanRujukanLuar.setAgensi(agensi);
            }
        }
        if (stageId.equalsIgnoreCase("kemaskini_keputusan") || stageId.equalsIgnoreCase("kemaskini_rekod_kpsn")) {
            if (tarikhRujukan != null) {
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
            } else {
                permohonanRujukanLuar.setTarikhRujukan(null);
            }
            permohonanRujukanLuar.setCatatan(catatan);
        }


        enforcementService.simpanRujukan(permohonanRujukanLuar);

        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");

            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {

                if (permohonanRujukanLuar != null) {
                    senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
                }

                if (senaraipermohonanRujukanLuarPeranan.size() != 0 && i < senaraipermohonanRujukanLuarPeranan.size()) {
                    Long id = senaraipermohonanRujukanLuarPeranan.get(i).getIdPeranan();
                    if (id != null) {
                        permohonanRujukanLuarPeranan = enforcementService.findPeranan(id);
                    }
                } else {
                    permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();
                }

                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);

                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);

                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSingle() {
        logger.info("--------------delete record--------------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

        if (permohonanRujukanLuar != null) {

            //delete child first : permohonanRujukanLuarPeranan (in list)
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            for (int i = 0; i < senaraipermohonanRujukanLuarPeranan.size(); i++) {
                permohonanRujukanLuarPeranan = enforcementService.findPeranan(senaraipermohonanRujukanLuarPeranan.get(i).getIdPeranan());
                enforcementService.deleteRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

            //Then, delete parent : permohonanRujukanLuar
            enforcementService.deleteMesy(permohonanRujukanLuar);
        }
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(MahkamahActionBean.class, "locate");
    }

    public Resolution simpanSingle() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        logger.info(idRujukan);
        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

        cawangan = permohonan.getCawangan();

        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(cawangan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        tarikhSidang = getContext().getRequest().getParameter("permohonanRujukanLuar.tarikhSidang");
        permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        tarikhRujukan = getContext().getRequest().getParameter("permohonanRujukanLuar.tarikhRujukan");
        permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        catatan = getContext().getRequest().getParameter("permohonanRujukanLuar.catatan");
        permohonanRujukanLuar.setCatatan(catatan);
        namaSidang = getContext().getRequest().getParameter("permohonanRujukanLuar.namaSidang");
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        noRujukan = getContext().getRequest().getParameter("permohonanRujukanLuar.noRujukan");
        permohonanRujukanLuar.setNoRujukan(noRujukan);

        //  aduanPemantauan.setDipantauOleh(peng);
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        enforcementService.simpanRujukan(permohonanRujukanLuar);
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/keputusan_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            permohonanRujukanLuar.setDokumen(null);
            InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            enforceService.simpanRujukan(permohonanRujukanLuar);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(MahkamahActionBean.class, "locate");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public KodDokumen getKoddokumen() {
        return koddokumen;
    }

    public void setKoddokumen(KodDokumen koddokumen) {
        this.koddokumen = koddokumen;
    }

    public String getTarikhSidangMH() {
        return tarikhSidangMH;
    }

    public void setTarikhSidangMH(String tarikhSidangMH) {
        this.tarikhSidangMH = tarikhSidangMH;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNamaSidang() {
        return namaSidang;
    }

    public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public PermohonanRujukanLuarPeranan getPermohonanRujukanLuarPeranan() {
        return permohonanRujukanLuarPeranan;
    }

    public void setPermohonanRujukanLuarPeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        this.permohonanRujukanLuarPeranan = permohonanRujukanLuarPeranan;
    }

    public List<PermohonanRujukanLuarPeranan> getSenaraipermohonanRujukanLuarPeranan() {
        return senaraipermohonanRujukanLuarPeranan;
    }

    public void setSenaraipermohonanRujukanLuarPeranan(List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan) {
        this.senaraipermohonanRujukanLuarPeranan = senaraipermohonanRujukanLuarPeranan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKategoriMahkamah() {
        return kategoriMahkamah;
    }

    public void setKategoriMahkamah(String kategoriMahkamah) {
        this.kategoriMahkamah = kategoriMahkamah;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getKodNegeriPermohonan() {
        return kodNegeriPermohonan;
    }

    public void setKodNegeriPermohonan(String kodNegeriPermohonan) {
        this.kodNegeriPermohonan = kodNegeriPermohonan;
    }
}
