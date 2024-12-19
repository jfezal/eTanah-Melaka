/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.InfoAudit;
import etanah.model.OperasiAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanSaksi;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/surat_panggilan_saksi")
public class SuratPanggilanSaksiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(SuratPanggilanSaksiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private Pengguna pguna;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Long idOpBasedOnIdIP;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private Date currentDate;
    private String alamatSidang1;
    private String alamatSidang2;
    private String alamatSidang3;
    private String poskodSidang;
    private String negeriSidang;
    private String wakilSidang;
    private String noFaks;
    private String tajuk;
    private String noRujukan;
    private String penyediaSidang;
    private String tempatKandungan;
    private String tarikhKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private List<PermohonanKertasKandungan> senaraiKertas;
    private String noLaporan;
    private String tarikhSidang;
    private List<OperasiAgensi> senaraiOperasiAgensi;
    private String kandungan;
    private Date tarikhKand;
    private List<PermohonanKertas> senaraiSuratSaksi = new ArrayList<PermohonanKertas>();
    private String idKertas;
    String tajukSurat[], surat;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm aaa");
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private List<Pengguna> senaraiPengguna;
    private String statusKertas;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("/penguatkuasaan/surat_panggilan_saksi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/surat_panggilan_saksi.jsp").addParameter("tab", "true");
    }

    public Resolution addRecord() {
        logger.info(":::addRecord");
        alamatSidang1 = "Majistret";
        alamatSidang2 = "Mahkamah Majistret";
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_surat.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            ArrayList kumpulanBpel = new ArrayList<String>();
            //'PPUU','PUU','PTD'
            if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                kumpulanBpel.add("puuptg"); // PUU
                kumpulanBpel.add("ppuuptg"); // PPUU
            } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                kumpulanBpel.add("ppuuptd"); // PPUU
                kumpulanBpel.add("ptd"); // PTD
            }

            senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());

            currentDate = new java.util.Date();

            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(permohonan.getIdPermohonan());
            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

            if (!senaraiOksIp.isEmpty()) {
                idOpBasedOnIdIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                logger.info("id operasi : " + idOpBasedOnIdIP);

                if (StringUtils.isNotBlank(idOpBasedOnIdIP.toString())) {
                    /*to get from List Laporan Polis*/
                    senaraiOperasiAgensi = enforceService.getSenaraiOperasiAgensi(idOpBasedOnIdIP);
                }

            }

            senaraiSuratSaksi = enforcementService.findListSuratSaksi(permohonan.getIdPermohonan(), "SAPSI");
            logger.info("------------size senaraiSuratSaksi-------------- : " + senaraiSuratSaksi.size());
        }
    }

    public Resolution editRecord() {
        logger.info(":::editRecord");
        idKertas = getContext().getRequest().getParameter("idKertas");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                alamatSidang1 = kertas.getSidangAlamat1();
                alamatSidang2 = kertas.getSidangAlamat2();
                alamatSidang3 = kertas.getSidangAlamat3();
                poskodSidang = kertas.getSidangPoskod();
                if (kertas.getSidangNegeri() != null) {
                    negeriSidang = kertas.getSidangNegeri().getKod();
                }
                wakilSidang = kertas.getWakilSidang();
                noFaks = kertas.getNoFaks();
                noRujukan = kertas.getNomborRujukan();
                penyediaSidang = kertas.getPenyediaSidang();
                noLaporan = kertas.getNoLaporan();
                if (kertas.getTarikhSidang() != null) {
                    tarikhSidang = sdf.format(kertas.getTarikhSidang());
                }

                surat = kertas.getTajuk();
                tajukSurat = surat.split(" ");
                logger.info("length tajuk surat: " + tajukSurat.length);
                if (tajukSurat.length > 1) {
                    logger.info("tajukSurat[2] : " + tajukSurat[2]);
                    tajuk = tajukSurat[2];
                }

                Integer lastIndex = tajukSurat.length - 1;
                logger.info("lastIndex : " + lastIndex);

                for (int i = 3; i < lastIndex; i++) {
                    logger.info("tajukSurat loop [" + i + "] : " + tajukSurat[i]);
                    tajuk += " " + tajukSurat[i];
                }
                logger.info("tajuk : " + tajuk.trim());
                if (kertas != null) {
                    senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());
                    permohonanKertasKandungan = senaraiKertas.get(0);
                    if (permohonanKertasKandungan != null) {
                        tarikhKandungan = sdf.format(permohonanKertasKandungan.getTarikhButiran());
                        jam = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(11, 13);
                        if (jam.startsWith("0")) {
                            jam = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(12, 13);
                        }
                        minit = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(14, 16);
                        System.out.println("minit trh_operasi : " + minit);
                        ampm = sdf1.format(permohonanKertasKandungan.getTarikhButiran()).substring(17, 19);
                    }
                    tempatKandungan = permohonanKertasKandungan.getSubtajuk();

                    if (StringUtils.isNotBlank(kertas.getNilaiDalaman1())) {
                        mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNilaiDalaman1()));
                        if (mohonTandatanganDokumen != null) {
                            diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
            }

        }



        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_surat.jsp").addParameter("popup", "true");
    }

    public Resolution viewRecord() {
        logger.info(":::viewRecord");
        idKertas = getContext().getRequest().getParameter("idKertas");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());

                if (StringUtils.isNotBlank(kertas.getNilaiDalaman1())) {
                    mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNilaiDalaman1()));
                    if (mohonTandatanganDokumen != null) {
                        diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                    }
                }
            }

        }
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_surat.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(SuratPanggilanSaksiActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        logger.info("---------simpan---------");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        idKertas = getContext().getRequest().getParameter("idKertas");

        if (StringUtils.isNotBlank(idKertas)) {
            kertas = permohonanKertasDAO.findById(Long.parseLong(idKertas));
            if (kertas != null) {
                senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());
            }

        }

        if (StringUtils.isNotBlank(tajuk)) {
            tajuk = "MAHKAMAH MAJISTRET " + tajuk + " MELAKA";
        }

        if (kertas == null) {
            kertas = new PermohonanKertas();
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setCawangan(pguna.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("SAPSI"));
        kertas.setSidangAlamat1(alamatSidang1);
        kertas.setSidangAlamat2(alamatSidang2);
        kertas.setSidangAlamat3(alamatSidang3);
        kertas.setSidangPoskod(poskodSidang);
        kertas.setSidangNegeri(kodNegeriDAO.findById(negeriSidang));
        kertas.setWakilSidang(wakilSidang);
        kertas.setNoFaks(noFaks);
        kertas.setNomborRujukan(noRujukan);
        kertas.setPenyediaSidang(penyediaSidang);
//        if (StringUtils.isNotBlank(penyediaSidang)) {
//            aduanOrangKenaSyak = aduanOrangKenaSyakDAO.findById(Long.parseLong(penyediaSidang));
//            if (aduanOrangKenaSyak != null) {
//                kertas.setPenyediaSidang(aduanOrangKenaSyak.getNama());
//            }
//        }
        kertas.setNoLaporan(noLaporan);
        if (StringUtils.isNotBlank(tarikhSidang)) {
            kertas.setTarikhSidang(sdf.parse(tarikhSidang));
        }

        if (StringUtils.isNotBlank(tarikhKandungan)) {
            tarikhKandungan = tarikhKandungan + " " + jam + ":" + minit + " " + ampm;
            tarikhKand = sdf1.parse(tarikhKandungan);
            System.out.println("tarikh kand :" + sdf1.format(tarikhKand));
        }
        enforceService.simpanPermohonanKertas(kertas);

        kandungan = "Pohon pihak tuan mengeluarkan panggilan saksi kepada saksi-saksi sepertimana di bawah ini untuk hadir di Mahkamah Majistret " + tempatKandungan + " Melaka pada " + sdf.format(tarikhKand) + " jam " + sdf2.format(tarikhKand) + ".";


        if (kertas != null) {
            senaraiKertas = enforceService.findByIdKertas2(kertas.getIdKertas());
            System.out.println("senarai kertas :" + senaraiKertas.size());
            if (senaraiKertas.size() != 0) {
                permohonanKertasKandungan = senaraiKertas.get(0);
            }

            if (permohonanKertasKandungan != null) {
                System.out.println("id permohonanKertasKandungan : " + permohonanKertasKandungan.getIdKandungan());
                ia = permohonanKertasKandungan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanKertasKandungan = new PermohonanKertasKandungan();
            }
        }
        permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
        permohonanKertasKandungan.setKertas(kertas);
        permohonanKertasKandungan.setKandungan(kandungan);
        permohonanKertasKandungan.setInfoAudit(ia);
        permohonanKertasKandungan.setBil(2);
        permohonanKertasKandungan.setTarikhButiran(sdf1.parse(tarikhKandungan));
        permohonanKertasKandungan.setSubtajuk(tempatKandungan);
        enforceService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);

        if (StringUtils.isNotBlank(kertas.getNilaiDalaman1())) {
            mohonTandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.parseLong(kertas.getNilaiDalaman1()));
            if (mohonTandatanganDokumen != null) {
                ia = mohonTandatanganDokumen.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
        } else {
            mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
        }


        mohonTandatanganDokumen.setPermohonan(permohonan);
        mohonTandatanganDokumen.setInfoAudit(ia);
        mohonTandatanganDokumen.setCawangan(pguna.getKodCawangan());
        mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
        mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("SAPSI"));
        mohonTandatanganDokumen.setTrhTt(new java.util.Date());
        enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);

        kertas.setNilaiDalaman1(Long.toString(mohonTandatanganDokumen.getIdDokTt()));
        enforceService.simpanPermohonanKertas(kertas);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new RedirectResolution(SuratPanggilanSaksiActionBean.class, "locate");
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
        return new RedirectResolution(SuratPanggilanSaksiActionBean.class, "locate");
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Long getIdOpBasedOnIdIP() {
        return idOpBasedOnIdIP;
    }

    public void setIdOpBasedOnIdIP(Long idOpBasedOnIdIP) {
        this.idOpBasedOnIdIP = idOpBasedOnIdIP;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getAlamatSidang1() {
        return alamatSidang1;
    }

    public void setAlamatSidang1(String alamatSidang1) {
        this.alamatSidang1 = alamatSidang1;
    }

    public String getAlamatSidang2() {
        return alamatSidang2;
    }

    public void setAlamatSidang2(String alamatSidang2) {
        this.alamatSidang2 = alamatSidang2;
    }

    public String getAlamatSidang3() {
        return alamatSidang3;
    }

    public void setAlamatSidang3(String alamatSidang3) {
        this.alamatSidang3 = alamatSidang3;
    }

    public String getPoskodSidang() {
        return poskodSidang;
    }

    public void setPoskodSidang(String poskodSidang) {
        this.poskodSidang = poskodSidang;
    }

    public String getNegeriSidang() {
        return negeriSidang;
    }

    public void setNegeriSidang(String negeriSidang) {
        this.negeriSidang = negeriSidang;
    }

    public String getWakilSidang() {
        return wakilSidang;
    }

    public void setWakilSidang(String wakilSidang) {
        this.wakilSidang = wakilSidang;
    }

    public String getNoFaks() {
        return noFaks;
    }

    public void setNoFaks(String noFaks) {
        this.noFaks = noFaks;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getPenyediaSidang() {
        return penyediaSidang;
    }

    public void setPenyediaSidang(String penyediaSidang) {
        this.penyediaSidang = penyediaSidang;
    }

    public String getTempatKandungan() {
        return tempatKandungan;
    }

    public void setTempatKandungan(String tempatKandungan) {
        this.tempatKandungan = tempatKandungan;
    }

    public String getTarikhKandungan() {
        return tarikhKandungan;
    }

    public void setTarikhKandungan(String tarikhKandungan) {
        this.tarikhKandungan = tarikhKandungan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }

    public String getNoLaporan() {
        return noLaporan;
    }

    public void setNoLaporan(String noLaporan) {
        this.noLaporan = noLaporan;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }

    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public List<PermohonanKertas> getSenaraiSuratSaksi() {
        return senaraiSuratSaksi;
    }

    public void setSenaraiSuratSaksi(List<PermohonanKertas> senaraiSuratSaksi) {
        this.senaraiSuratSaksi = senaraiSuratSaksi;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public Date getTarikhKand() {
        return tarikhKand;
    }

    public void setTarikhKand(Date tarikhKand) {
        this.tarikhKand = tarikhKand;
    }

    public String getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(String idKertas) {
        this.idKertas = idKertas;
    }

    public String[] getTajukSurat() {
        return tajukSurat;
    }

    public void setTajukSurat(String[] tajukSurat) {
        this.tajukSurat = tajukSurat;
    }

    public String getSurat() {
        return surat;
    }

    public void setSurat(String surat) {
        this.surat = surat;
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

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
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

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getStatusKertas() {
        return statusKertas;
    }

    public void setStatusKertas(String statusKertas) {
        this.statusKertas = statusKertas;
    }
}
