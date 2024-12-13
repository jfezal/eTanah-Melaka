/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodPerananRujukanLuarDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodDokumen;
import etanah.model.KodPerananRujukanLuar;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanRujukanLuarPeranan;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import etanah.view.penguatkuasaan.MahkamahActionBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author MohammadHafifi
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/utiliti_keputusan_mahkamah")
public class UtilitiKeputusanMahkamah extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiKeputusanMahkamah.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodPerananRujukanLuarDAO kodPerananRujukanLuarDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    private Pengguna pengguna;
    private String idRujukan;
    private String idPermohonan;
    private String tarikhSidang;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan;
    private Permohonan permohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhRujukan;
    private String kategoriMahkamah;
    private String catatan;
    private String noRujukan;
    private String namaSidang;
    private String recordCount;

    @DefaultHandler
    public Resolution findKeputusanMahkamah() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_keputusan_mahkamah.jsp");
    }

    public Resolution popupAddRecord() {
        System.out.println("popupAddRecord");
        return new JSP("penguatkuasaan/utiliti/popup_tambah_mahkamah.jsp").addParameter("popup", "true");
    }

    public Resolution popupViewRecord() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
        }
        return new JSP("penguatkuasaan/utiliti/popup_view_mahkamah.jsp").addParameter("popup", "true");
    }
    
    public Resolution refreshpage() {
        System.out.println("refresh");
        return new RedirectResolution(UtilitiKeputusanMahkamah.class, "searchNoSerahan");
    }

    public Resolution popupEditRecord() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        
        if (permohonanRujukanLuar != null) {
            idPermohonan = permohonanRujukanLuar.getPermohonan().getIdPermohonan();
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
        return new JSP("penguatkuasaan/utiliti/popup_edit_mahkamah.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
    }

    public Resolution searchNoSerahan() {
        System.out.println(idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        if (permohonan == null) {
            addSimpleError("Id Permohonan " + idPermohonan + " tidak dijumpai.");
        } else {
            addSimpleMessage("Id Permohonan " + idPermohonan + " dijumpai. Anda boleh menambah keputusan mahkamah bagi id permohonan ini.");
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_keputusan_mahkamah.jsp");
    }

    public Resolution simpanRecord() throws ParseException {
        logger.info("--------------simpanRecord--------------");

        permohonan = permohonanDAO.findById(idPermohonan);
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

            if (recordCount != null) {
                int rowCount = Integer.parseInt(recordCount);
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
            }
        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/utiliti/popup_tambah_mahkamah.jsp");
    }

    public Resolution editRecord() throws ParseException {
        logger.info("-------edit record-----------");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonan = permohonanDAO.findById(idPermohonan);

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
        return new JSP("penguatkuasaan/utiliti/popup_tambah_mahkamah.jsp").addParameter("message", "Maklumat telah berjaya dikemaskini.");
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
        return new RedirectResolution(UtilitiKeputusanMahkamah.class, "locate");
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
        return new RedirectResolution(UtilitiKeputusanMahkamah.class, "locate");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public PermohonanRujukanLuarPeranan getPermohonanRujukanLuarPeranan() {
        return permohonanRujukanLuarPeranan;
    }

    public void setPermohonanRujukanLuarPeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        this.permohonanRujukanLuarPeranan = permohonanRujukanLuarPeranan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getKategoriMahkamah() {
        return kategoriMahkamah;
    }

    public void setKategoriMahkamah(String kategoriMahkamah) {
        this.kategoriMahkamah = kategoriMahkamah;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNamaSidang() {
        return namaSidang;
    }

    public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public List<PermohonanRujukanLuarPeranan> getSenaraipermohonanRujukanLuarPeranan() {
        return senaraipermohonanRujukanLuarPeranan;
    }

    public void setSenaraipermohonanRujukanLuarPeranan(List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan) {
        this.senaraipermohonanRujukanLuarPeranan = senaraipermohonanRujukanLuarPeranan;
    }
}
