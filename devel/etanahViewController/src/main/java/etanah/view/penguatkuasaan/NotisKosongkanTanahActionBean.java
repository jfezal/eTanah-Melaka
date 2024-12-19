package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
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
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.OperasiPenguatkuasaan;
//import etanah.model.KodPeranan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

//import net.sourceforge.stripes.action.StreamingResolution;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/notis_kosongkan_tanah")
public class NotisKosongkanTanahActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
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
    private etanah.Configuration conf;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    private static final Logger LOG = Logger.getLogger(NotisKosongkanTanahActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kandungan;
    private String tajuk;
    private Pengguna pengguna;
    private List<Pengguna> senaraiPengguna;
    private List<PermohonanKertasKandungan> senaraiKertas = new ArrayList<PermohonanKertasKandungan>();
    private String rowCount;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String kodNegeri;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private Date currentDate;
    private PermohonanKertasKandungan maklumanUlasan;
    private String makluman;
    private List<OperasiPenguatkuasaan> listOp;
    private String idOp;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private Boolean opFlag = false;
    private AduanOrangKenaSyak oks;
    private String idOks;
    private PermohonanKertasKandungan kandunganPermulaan;
    private String kandunganAwalan;
    private Boolean addNotisFlag = Boolean.FALSE;
    private PermohonanKertas bilanganNotis;
    private int bil = 0;
    private List<PermohonanKertas> senaraiKertasOks;
    private String idKertas;
    private List<PermohonanKertas> bilMaxNotis;
    private List<PermohonanKertas> senaraiMaxBilEachOks;
    private String bilNotis;
    private String noLaporan; // will save id for mohon_dok_tt at column noLaporan t mohon_kertas

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/notis_kosongkan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/notis_kosongkan_tanah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--------------rehydrate--------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");

        currentDate = new java.util.Date();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            ArrayList kumpulanBpel = new ArrayList<String>();
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpel.add("pptd"); //PPTD
                kumpulanBpel.add("ptd"); //PTD
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                kumpulanBpel.add("pptd"); //PPTD
                kumpulanBpel.add("ptd"); //PTD
            }

            senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());


            listOp = enforceService.findListLaporanOperasi(idPermohonan);
            if (listOp.size() != 0) {
                opFlag = true;
            } else {
                if (permohonan.getPermohonanSebelum() != null) {
                    listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                    opFlag = true;

                    senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);

                    if (senaraiOksIp.size() != 0) {
                        Long idOpIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                        listOp = enforcementService.findListLaporanOperasi(idOpIP);
                        statusIP = true;

                    }


                }
            }

            //get max number of noSurat for each OKS
            senaraiKertasOks = enforceService.findAllNotisOks(permohonan.getIdPermohonan());
            senaraiMaxBilEachOks = new ArrayList<PermohonanKertas>();

            for (OperasiPenguatkuasaan op : listOp) {
                for (AduanOrangKenaSyak a : op.getSenaraiAduanOrangKenaSyak()) {
                    PermohonanKertas p = enforceService.findMaxBil(permohonan.getIdPermohonan(), "PKT", a.getIdOrangKenaSyak());
                    if (p != null) {
                        LOG.info("--------------max bil for oks (" + a.getNama() + ") is " + p.getNoKertas());
                        senaraiMaxBilEachOks.add(p);
                    }
                }
            }

//
//            for (PermohonanKertas pk : senaraiKertasOks) {
//                if (StringUtils.isNotBlank(pk.getNomborRujukan())) {
//                    PermohonanKertas p = enforceService.findMaxBil(permohonan.getIdPermohonan(), "PKT", Long.parseLong(pk.getNomborRujukan()));
//                    AduanOrangKenaSyak a = aduanOrangKenaSyakDAO.findById(Long.parseLong(pk.getNomborRujukan()));
//                    if (p != null) {
//                        LOG.info("--------------max bil for oks (" + a.getNama() + ") is " + p.getNoKertas());
//                        senaraiMaxBilEachOks.add(p);
//                    }
//                }
//            }
            LOG.info("::::: size senaraiMaxBilEachOks: " + senaraiMaxBilEachOks.size());

            bilMaxNotis = enforceService.findMaxBilList(permohonan.getIdPermohonan(), "PKT");
            for (PermohonanKertas k : bilMaxNotis) {
                LOG.info("bil max for oks: " + k.getNomborRujukan() + " :: " + k.getNoKertas());
//                getInformation(aduanOrangKenaSyakDAO.findById(Long.parseLong(k.getNomborRujukan())));
            }



        }


    }

    public Resolution popupTambahNotis() {
        idOks = getContext().getRequest().getParameter("idOks");
        bilNotis = getContext().getRequest().getParameter("bil");
        String status = getContext().getRequest().getParameter("statusInsert");
        if (StringUtils.isNotBlank(idOks)) {
            oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
//            if (oks != null) {
//                getInformation(oks);
//            }
        }
        if (StringUtils.isNotBlank(status)) {
            addNotisFlag = true;

        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupEditNotis() {
        idKertas = getContext().getRequest().getParameter("idKertas");
        bilNotis = getContext().getRequest().getParameter("bil");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                tajuk = kertas.getTajuk();

                if (StringUtils.isNotBlank(kertas.getNomborRujukan())) {
                    oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(kertas.getNomborRujukan()));

                }

                maklumanUlasan = enforceService.findByIdBil(kertas.getIdKertas(), 1);
                if (maklumanUlasan != null) {
                    makluman = maklumanUlasan.getKandungan();
                }

                kandunganPermulaan = enforceService.findByIdBil(kertas.getIdKertas(), 2);
                if (kandunganPermulaan != null) {
                    kandunganAwalan = kandunganPermulaan.getKandungan();
                }

                ArrayList intList = new ArrayList<String>();
                intList.add("1");
                intList.add("2");
                senaraiKertas = enforceService.findAllKeputusanAduan(kertas.getIdKertas(), intList);
                rowCount = String.valueOf(senaraiKertas.size());
                LOG.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());



                if (StringUtils.isNotBlank(kertas.getNoLaporan())) {
                    mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNoLaporan()));
                    if (mohonTandatanganDokumen != null) {
                        diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                    }
                }
            }
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupViewNotis() {
        idKertas = getContext().getRequest().getParameter("idKertas");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                tajuk = kertas.getTajuk();

                if (StringUtils.isNotBlank(kertas.getNomborRujukan())) {
                    oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(kertas.getNomborRujukan()));

                }

                maklumanUlasan = enforceService.findByIdBil(kertas.getIdKertas(), 1);
                if (maklumanUlasan != null) {
                    makluman = maklumanUlasan.getKandungan();
                }

                kandunganPermulaan = enforceService.findByIdBil(kertas.getIdKertas(), 2);
                if (kandunganPermulaan != null) {
                    kandunganAwalan = kandunganPermulaan.getKandungan();
                }

                ArrayList intList = new ArrayList<String>();
                intList.add("1");
                intList.add("2");
                senaraiKertas = enforceService.findAllKeputusanAduan(kertas.getIdKertas(), intList);
                rowCount = String.valueOf(senaraiKertas.size());

                if (StringUtils.isNotBlank(kertas.getNoLaporan())) {
                    mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNoLaporan()));
                    if (mohonTandatanganDokumen != null) {
                        diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                    }
                }


            }
        }

        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_notis.jsp").addParameter("popup", "true");
    }

    public final void getInformation(AduanOrangKenaSyak oks) {
        try {

            ArrayList intList = new ArrayList<String>();
            intList.add("1");
            intList.add("2");



            bilanganNotis = enforceService.findMaxBil(permohonan.getIdPermohonan(), "PKT", oks.getIdOrangKenaSyak());
            if (bilanganNotis != null) {
                if (StringUtils.isNotEmpty(bilanganNotis.getNoKertas().toString())) {
                    bil = bilanganNotis.getNoKertas();
                }

                if (bil < 3 && StringUtils.isNotEmpty(bilanganNotis.getStatusKertas())) {
                    if (bilanganNotis.getStatusKertas().equalsIgnoreCase("0")) {
                        addNotisFlag = true;
                    }
                }

            } else {
//                kertas = new PermohonanKertas();
            }

            kertas = bilanganNotis;

            if (kertas != null) {
                tajuk = kertas.getTajuk();

                maklumanUlasan = enforceService.findByIdBil(kertas.getIdKertas(), 1);
                if (maklumanUlasan != null) {
                    makluman = maklumanUlasan.getKandungan();
                }

                kandunganPermulaan = enforceService.findByIdBil(kertas.getIdKertas(), 2);
                if (kandunganPermulaan != null) {
                    kandunganAwalan = kandunganPermulaan.getKandungan();
                }
                senaraiKertas = enforceService.findAllKeputusanAduan(kertas.getIdKertas(), intList);
                LOG.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());


            }


        } catch (Exception ex) {

            LOG.error(ex);
            ex.printStackTrace();
        }
    }

    public Resolution simpan() throws ParseException {
        LOG.info("--------------simpan--------------");
        idKertas = getContext().getRequest().getParameter("idKertas");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            ArrayList intList = new ArrayList<String>();
            intList.add("1");
            intList.add("2");
            senaraiKertas = enforceService.findAllKeputusanAduan(kertas.getIdKertas(), intList);

            maklumanUlasan = enforceService.findByIdBil(kertas.getIdKertas(), 1);
            if (maklumanUlasan != null) {
                makluman = maklumanUlasan.getKandungan();
            }

            kandunganPermulaan = enforceService.findByIdBil(kertas.getIdKertas(), 2);
            if (kandunganPermulaan != null) {
                kandunganAwalan = kandunganPermulaan.getKandungan();
            }
            LOG.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());
        } else {
//            idOks = getContext().getRequest().getParameter("idOks");
//            if (StringUtils.isNotBlank(idOks)) {
//                oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOks));
//                if (oks != null) {
//                    getInformation(oks);
//                }
//            }
        }

        InfoAudit ia = new InfoAudit();


        LOG.info("--------------addNotisFlag--------------" + addNotisFlag);
        if (addNotisFlag) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            if (kertas != null) {
                ia = kertas.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                kertas = new PermohonanKertas();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());

            }

        }


//        if (addNotisFlag == true || bil == 0) {
//            bil = bil + 1;
//        }

        LOG.info("--------------bil--------------" + bil);
        kertas.setStatusKertas("1");
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setCawangan(pengguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("PKT"));//Surat Peringatan Notis Kosongkan Tanah
        kertas.setNomborRujukan(idOks);
        kertas.setNoKertas(bil);
        enforceService.simpanPermohonanKertas(kertas);

        makluman = getContext().getRequest().getParameter("makluman");
        //to save makluman (bil 1)
        if (maklumanUlasan != null) {
            ia = maklumanUlasan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            maklumanUlasan = new PermohonanKertasKandungan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        maklumanUlasan.setKertas(kertas);
        maklumanUlasan.setBil(1);
        maklumanUlasan.setKandungan(makluman);
        maklumanUlasan.setCawangan(permohonan.getCawangan());
        maklumanUlasan.setInfoAudit(ia);
        enforceService.simpanPermohonanKertasKandungan(maklumanUlasan);


        kandunganAwalan = getContext().getRequest().getParameter("kandunganAwalan");
        //to save makluman (bil 2)
        if (kandunganPermulaan != null) {
            ia = kandunganPermulaan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            kandunganPermulaan = new PermohonanKertasKandungan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }
        kandunganPermulaan.setKertas(kertas);
        kandunganPermulaan.setBil(2);
        kandunganPermulaan.setKandungan(kandunganAwalan);
        kandunganPermulaan.setCawangan(permohonan.getCawangan());
        kandunganPermulaan.setInfoAudit(ia);
        enforceService.simpanPermohonanKertasKandungan(kandunganPermulaan);

        //to save list makluman

        System.out.println("rowCount save: " + rowCount);
        int k = 3;
        if (StringUtils.isNotBlank(rowCount)) {
            for (int i = 1; i <= Integer.parseInt(rowCount); i++) {
                if (senaraiKertas.size() != 0 && i <= senaraiKertas.size()) {
                    Long id = senaraiKertas.get(i - 1).getIdKandungan();
                    if (id != null) {
                        permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(id);
                        ia = permohonanKertasKandungan.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan = new PermohonanKertasKandungan();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                permohonanKertasKandungan.setKertas(kertas);
                permohonanKertasKandungan.setBil(k);
                String kandungan = getContext().getRequest().getParameter("kandungan" + i);
                permohonanKertasKandungan.setKandungan(kandungan);
                permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan.setInfoAudit(ia);
                enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                k++;
            }
        }

        System.out.println("kertas.getNoLaporan() ::" + kertas.getNoLaporan());
        if (StringUtils.isNotBlank(kertas.getNoLaporan())) {
            mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNoLaporan()));
            if (mohonTandatanganDokumen != null) {
                ia = mohonTandatanganDokumen.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
        } else {
            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }



        mohonTandatanganDokumen.setPermohonan(permohonan);
        mohonTandatanganDokumen.setInfoAudit(ia);
        mohonTandatanganDokumen.setCawangan(pengguna.getKodCawangan());
        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("PKT"));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);

        kertas.setNoLaporan(Long.toString(mohonTandatanganDokumen.getIdDokTt()));
        enforceService.simpanPermohonanKertas(kertas);;

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/notis_kosongkan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRecord() {
        idKertas = getContext().getRequest().getParameter("idKertas");
        System.out.println("idKertas:" + idKertas);

        try {
            if (StringUtils.isNotBlank(idKertas)) {
                kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));

                if (kertas != null) {
                    senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());

                    for (int i = 0; i < senaraiKertas.size(); i++) {
                        permohonanKertasKandungan = senaraiKertas.get(i);
                        enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
                    }

                    enforcementService.deleteKertas(kertas);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new RedirectResolution(NotisKosongkanTanahActionBean.class, "locate");
    }

    public Resolution deleteUlasan() {
        LOG.info("--------------deleteUlasan--------------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------------idKandungan : --------------" + idKandungan);
        permohonanKertasKandungan = new PermohonanKertasKandungan();

        try {
            if (idKandungan != null) {
                permohonanKertasKandungan = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            }
            enforceService.deleteDiariSiasatan(permohonanKertasKandungan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Rekod telah berjaya dihapuskan");
        rehydrate();
        return new JSP("penguatkuasaan/popup_tambah_notis.jsp").addParameter("popup", "true");
//        return new RedirectResolution(NotisKosongkanTanahActionBean.class, "locate");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(NotisKosongkanTanahActionBean.class, "locate");
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public PermohonanKertasKandungan getMaklumanUlasan() {
        return maklumanUlasan;
    }

    public void setMaklumanUlasan(PermohonanKertasKandungan maklumanUlasan) {
        this.maklumanUlasan = maklumanUlasan;
    }

    public String getMakluman() {
        return makluman;
    }

    public void setMakluman(String makluman) {
        this.makluman = makluman;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Boolean getStatusIP() {
        return statusIP;
    }

    public void setStatusIP(Boolean statusIP) {
        this.statusIP = statusIP;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public PermohonanKertasKandungan getKandunganPermulaan() {
        return kandunganPermulaan;
    }

    public void setKandunganPermulaan(PermohonanKertasKandungan kandunganPermulaan) {
        this.kandunganPermulaan = kandunganPermulaan;
    }

    public String getKandunganAwalan() {
        return kandunganAwalan;
    }

    public void setKandunganAwalan(String kandunganAwalan) {
        this.kandunganAwalan = kandunganAwalan;
    }

    public Boolean getAddNotisFlag() {
        return addNotisFlag;
    }

    public void setAddNotisFlag(Boolean addNotisFlag) {
        this.addNotisFlag = addNotisFlag;
    }

    public PermohonanKertas getBilanganNotis() {
        return bilanganNotis;
    }

    public void setBilanganNotis(PermohonanKertas bilanganNotis) {
        this.bilanganNotis = bilanganNotis;
    }

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public List<PermohonanKertas> getSenaraiKertasOks() {
        return senaraiKertasOks;
    }

    public void setSenaraiKertasOks(List<PermohonanKertas> senaraiKertasOks) {
        this.senaraiKertasOks = senaraiKertasOks;
    }

    public String getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(String idKertas) {
        this.idKertas = idKertas;
    }

    public List<PermohonanKertas> getBilMaxNotis() {
        return bilMaxNotis;
    }

    public void setBilMaxNotis(List<PermohonanKertas> bilMaxNotis) {
        this.bilMaxNotis = bilMaxNotis;
    }

    public List<PermohonanKertas> getSenaraiMaxBilEachOks() {
        return senaraiMaxBilEachOks;
    }

    public void setSenaraiMaxBilEachOks(List<PermohonanKertas> senaraiMaxBilEachOks) {
        this.senaraiMaxBilEachOks = senaraiMaxBilEachOks;
    }

    public String getBilNotis() {
        return bilNotis;
    }

    public void setBilNotis(String bilNotis) {
        this.bilNotis = bilNotis;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }
}
