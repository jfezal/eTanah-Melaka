package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.service.BPelService;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_laporan_awal")
public class MaklumatLaporanAwalActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(MaklumatLaporanAwalActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private Hakmilik hakmilik;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kandungan;
    private String tajuk;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy hh:mm aaa");
    SimpleDateFormat minitFormat = new SimpleDateFormat("mm");
    SimpleDateFormat jamFormat = new SimpleDateFormat("hh");
    SimpleDateFormat ampmFormat = new SimpleDateFormat("aaa");
    private String recordCount;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private int rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private Pengguna pengguna;
    private String tarikhLaporan;
    private List<PermohonanKertas> listKertas;
    private PermohonanKertas mohonKertas;
    private Long idKertas;
    private String idKertasTesting;
    private String tajukTesting;
    SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

    public Resolution showFromEditRecord() {
        String idKertas = getContext().getRequest().getParameter("idKertas");
        idKertasTesting = idKertas;
        //get id kertas from parameter & find data for mohon_kertas & mohon_kertas_kand using id kertas
        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());
                tajukTesting = kertas.getTajuk();
                if (senaraiKertas != null) {
                    recordCount = String.valueOf(senaraiKertas.size());
                }

                if (kertas.getTarikhSidang() != null) {
                    tarikhLaporan = sdf0.format(kertas.getTarikhSidang());
                    tarikhLaporan = sdf2.format(kertas.getTarikhSidang()).substring(0, 10);
                    LOG.info("::::: tarikh laporan :" + tarikhLaporan);
                }
            }
        }
        getContext().getRequest().setAttribute("EditRecord", Boolean.TRUE);
        return new JSP("penguatkuasaan/kemaskini_popup_laporan_tanah_susulan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

//    public Resolution refreshPage() {
//        rehydrate();
//        getContext().getRequest().setAttribute("view", Boolean.TRUE);
//        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
//    }
    public Resolution refreshPage() {
        LOG.info(":::::: refreshPage main");
        rehydrate();
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

//    public Resolution refreshPage2() {
//        rehydrate();
//        getContext().getRequest().setAttribute("Tambah", Boolean.TRUE);
//        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
//    }
    public Resolution popupMaklumatLaporan() {
        getContext().getRequest().setAttribute("Tambah", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_maklumat_laporan.jsp").addParameter("popup", "true");
    }

    public Resolution popupTambahLaporan() {
        getContext().getRequest().setAttribute("tambahLaporan", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_maklumat_laporan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        getContext().getRequest().setAttribute("viewonly", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("Laporan", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
//        for (PermohonanKertas kertas2 : listKertas) {
//            senaraiKertas = enforceService.findListLaporanSusulan(kertas2.getIdKertas());
//        }

        String idKertas = getContext().getRequest().getParameter("idKertas");
        //get id kertas from parameter & find data for mohon_kertas & mohon_kertas_kand using id kertas
        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());
            }
        }
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
        if (idPermohonan != null) {
            try {
                permohonan = permohonanDAO.findById(idPermohonan);

                listKertas = enforceService.findByIdKrtsList(permohonan.getIdPermohonan(), "LAWAL");

                String idPermohonanAsal = "";
                if (permohonan.getPermohonanSebelum() != null) {
                    Permohonan p = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    idPermohonanAsal = p.getIdPermohonan();
                    LOG.info("p :::" + p.getIdPermohonan());
                    if (p != null) {
                        if (p.getPermohonanSebelum() != null) {
                            Permohonan p1 = permohonanDAO.findById(p.getPermohonanSebelum().getIdPermohonan());
                            if (p1 != null) {
                                LOG.info("p1 :::" + p1.getIdPermohonan());
                                idPermohonanAsal = p1.getIdPermohonan();
                            }
                        }
                    }
                }
                LOG.info("idPermohonanAsal :::" + idPermohonanAsal);
                if (listKertas.isEmpty()) {
                    listKertas = enforceService.findByIdKrtsList(idPermohonanAsal, "LAWAL");
                }

            } catch (Exception ex) {

                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpanEdit() {


        LOG.info("tajukTesting : " + tajukTesting);
        InfoAudit ia = new InfoAudit();
        kertas = new PermohonanKertas();

        if (StringUtils.isNotBlank(idKertasTesting)) {
            if (idKertasTesting != null) {

                kertas = permohonanKertasDAO.findById(Long.parseLong(idKertasTesting));
                if (kertas != null) {
                    senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());

                    pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                    ia.setTarikhMasuk(new Date());
                    ia.setDimasukOleh(pengguna);
                    ia = kertas.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    kertas.setTajuk(tajukTesting);
                    kertas.setPermohonan(permohonan);
                    kertas.setInfoAudit(ia);
                    kertas.setCawangan(pengguna.getKodCawangan());
                    kertas.setKodDokumen(kodDokumenDAO.findById("LAWAL"));

                    try {

                        if (StringUtils.isNotBlank(tarikhLaporan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                            tarikhLaporan = tarikhLaporan + " " + jam + ":" + minit + " " + ampm;
                            LOG.info("tarikhLaporan :" + tarikhLaporan);
                        }

                        if (StringUtils.isNotBlank(tarikhLaporan)) {
                            kertas.setTarikhSidang(sdf2.parse(tarikhLaporan));
                        }

                    } catch (Exception e) {
                        LOG.error(e);
                    }

                    enforceService.simpanPermohonanKertas(kertas);


                    if (StringUtils.isNotBlank(getContext().getRequest().getParameter("recordCount"))) {
                        LOG.info("recordCount : " + recordCount);
                        int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
                        int j = 1;

                        LOG.info("::::: value j:" + j);
                        LOG.info("::::: size senaraiKertas:" + senaraiKertas.size());

                        for (int i = 0; i < row; i++) {
                            if (senaraiKertas.size() != 0 && i < senaraiKertas.size()) {
                                Long id = senaraiKertas.get(i).getIdKandungan();

                                if (id != null) {

                                    permohonanKertasKandungan = enforceService.findByIdKandungan(id);
                                }
                            } else {
                                permohonanKertasKandungan = new PermohonanKertasKandungan();
                            }
                            kandungan = getContext().getRequest().getParameter("kandungan" + i);
                            LOG.info("kandungan : " + kandungan);
                            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                            permohonanKertasKandungan.setKertas(kertas);
                            permohonanKertasKandungan.setKandungan(kandungan);
                            permohonanKertasKandungan.setInfoAudit(ia);
                            permohonanKertasKandungan.setBil(j);
                            j++;

                            enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);


                        }
                    }
                }



                boolean flag = true;

                if (flag) {
                    addSimpleMessage("Maklumat telah berjaya disimpan.");
                }

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
//        return new RedirectResolution(MaklumatLaporanAwalActionBean.class, "showForm2");
            }
            LOG.info("masuk simpan : -- edit");

        }
//        rehydrate();
//        getContext().getRequest().setAttribute("simpanEdit111", Boolean.TRUE);
//        return showFromEditRecord();
//         LOG.info("masuk simpan : -- 2");
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("popup", "true");
    }

    // nie kalau x saya buat untuk penambahan laporan pada tab laporan tanah susulan...
    public Resolution simpanTambah() throws ParseException {

        InfoAudit ia = new InfoAudit();


        if (kertas == null) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("LAWAL"));
        try {

            if (StringUtils.isNotBlank(tarikhLaporan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                tarikhLaporan = tarikhLaporan + " " + jam + ":" + minit + " " + ampm;
                LOG.info("tarikhLaporan :" + tarikhLaporan);
            }

            if (StringUtils.isNotBlank(tarikhLaporan)) {
                kertas.setTarikhSidang(sdf2.parse(tarikhLaporan));
            }

        } catch (Exception e) {
            LOG.error(e);
        }

        enforceService.simpanPermohonanKertas(kertas);

        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("recordCount"))) {
            LOG.info("recordCount : " + recordCount);
            int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            int j = 1;

//            if (String idKertas = getContext().getRequest().getParameter("idKertas")){
            String idKertas = getContext().getRequest().getParameter("idKertas");
            for (int i = 0; i < row; i++) {
//                Long id = senaraiKertas.get(i).getIdKandungan();
//                if (senaraiKertas.size() == 0 && i > senaraiKertas.size()) {
//                    
//                    if (id != null) {
//                        
//                        permohonanKertasKandungan = enforceService.findByIdKandungan(id);
//                    }
//                } else 
//                {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
//                }
                kandungan = getContext().getRequest().getParameter("kandungan" + i);
                LOG.info("kandungan : " + kandungan);
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setKertas(kertas);
                permohonanKertasKandungan.setKandungan(kandungan);
                permohonanKertasKandungan.setInfoAudit(ia);
                permohonanKertasKandungan.setBil(j);
                j++;

                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);



            }
        }



        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
//        return new RedirectResolution(MaklumatLaporanAwalActionBean.class, "showForm2");
        LOG.info("masuk simpan : -- 2");
        return new JSP("penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();


        if (kertas == null) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("LAWAL"));
        try {

            if (StringUtils.isNotBlank(tarikhLaporan) && StringUtils.isNotBlank(jam) && StringUtils.isNotBlank(minit) && StringUtils.isNotBlank(ampm)) {
                tarikhLaporan = tarikhLaporan + " " + jam + ":" + minit + " " + ampm;
                LOG.info("tarikhLaporan :" + tarikhLaporan);
            }

            if (StringUtils.isNotBlank(tarikhLaporan)) {
                kertas.setTarikhSidang(sdf1.parse(tarikhLaporan));
            }

        } catch (Exception e) {
            LOG.error(e);
        }

        enforceService.simpanPermohonanKertas(kertas);

        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("recordCount"))) {
            LOG.info("recordCount : " + recordCount);
            int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            int j = 1;
            for (int i = 0; i < row; i++) {
                if (senaraiKertas.size() != 0 && i < senaraiKertas.size()) {
                    Long id = senaraiKertas.get(i).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan = enforceService.findByIdKandungan(id);
                    }
                } else {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                }
                kandungan = getContext().getRequest().getParameter("kandungan" + i);
                LOG.info("kandungan : " + kandungan);
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setKertas(kertas);
                permohonanKertasKandungan.setKandungan(kandungan);
                permohonanKertasKandungan.setInfoAudit(ia);
                permohonanKertasKandungan.setBil(j);
                j++;

                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

            }
        }



        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("tab", "true");
    }

    public Resolution deletePenguatkuasaanPasukan() {
        String idKertas = getContext().getRequest().getParameter("idKertas");
        //get id kertas from parameter & find data for mohon_kertas & mohon_kertas_kand using id kertas
        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                try {
                    if (kertas != null) {
//                        mohonKertas = enforceService.findByIdKrts(idPermohonan, idKertas);
//                        mohonKertas = enforceService.findByIdKrtsKand(idPermohonan, idKertas);
                        List<PermohonanKertasKandungan> senaraiKertasKand = new ArrayList<PermohonanKertasKandungan>();
                        senaraiKertasKand = kertas.getSenaraiKandungan();
                        for (PermohonanKertasKandungan p : senaraiKertasKand) {
                            enforceService.deleteDiariSiasatan(p);
                        }

                        enforceService.deleteMohonKertas(kertas);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    addSimpleError("kesalahan dalam menghapuskan rekod");
                }
                rehydrate();
                addSimpleMessage("Maklumat berjaya dihapuskan.");
//                getContext().getRequest().setAttribute("viewonly", Boolean.TRUE);
//                return new RedirectResolution(MaklumatLaporanAwalActionBean.class, "showForm2");

            }
        }
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_laporan_awal.jsp").addParameter("popup", "true");
        return new RedirectResolution(MaklumatLaporanAwalActionBean.class, "showForm2");
    }

    // Click on Hapus button in HomePage
    public Resolution deleteLaporan() {
        //idKandungan = getContext().getRequest().getParameter("idKandungan");
        //System.out.println("idKertas:" + idKandungan);
        String item = getContext().getRequest().getParameter("item");
        idKertasTesting = getContext().getRequest().getParameter("idKertasTesting");
        String[] listIdKandungan = item.split(",");
        LOG.info("Size :" + listIdKandungan.length);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        try {
            for (int i = 0; i < listIdKandungan.length; i++) {

                if (!listIdKandungan[i].equals("T")) {
                    permohonanKertasKandungan = enforceService.findByIdKKand(listIdKandungan[i]);
                    if (permohonanKertasKandungan != null) {
                        enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        rehydrate();
        if (StringUtils.isNotBlank(idKertasTesting)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertasTesting));
            if (kertas != null) {
                senaraiKertas = enforceService.findListLaporanSusulan(kertas.getIdKertas());
                tajukTesting = kertas.getTajuk();
                if (senaraiKertas != null) {
                    recordCount = String.valueOf(senaraiKertas.size());
                }
            }
        }
        getContext().getRequest().setAttribute("EditRecord", Boolean.TRUE);
        return new JSP("penguatkuasaan/kemaskini_popup_laporan_tanah_susulan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSelected() {
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));



        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);


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
        return new RedirectResolution(MaklumatLaporanAwalActionBean.class, "locate");
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getTarikhLaporan() {
        return tarikhLaporan;
    }

    public void setTarikhLaporan(String tarikhLaporan) {
        this.tarikhLaporan = tarikhLaporan;
    }

    public List<PermohonanKertas> getListKertas() {
        return listKertas;
    }

    public void setListKertas(List<PermohonanKertas> listKertas) {
        this.listKertas = listKertas;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public Long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(Long idKertas) {
        this.idKertas = idKertas;
    }

    public String getIdKertasTesting() {
        return idKertasTesting;
    }

    public void setIdKertasTesting(String idKertasTesting) {
        this.idKertasTesting = idKertasTesting;
    }

    public String getTajukTesting() {
        return tajukTesting;
    }

    public void setTajukTesting(String tajukTesting) {
        this.tajukTesting = tajukTesting;
    }
}
//kat sini