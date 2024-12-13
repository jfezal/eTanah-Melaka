/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanPihakTidakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.KodBank;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.util.bean.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/maklumatpbttidakberdaftar")
public class maklumatpbttidakberdaftarActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(maklumatpbttidakberdaftarActionBean.class);
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    PermohonanPihakTidakBerkepentinganDAO pptbDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPerbicaraan hakmilikPerbicaraan2;
    private HakmilikPermohonan hakmilikPermohonan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PerbicaraanKehadiran> senaraiPbtTidakBerdaftar;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Pengguna> senaraiPengguna;
    public String noRujukan;
    private Date tarikhPemilikan;
    private String caraPemilikan;
    private BigDecimal hargaPembelian;
    private String lokasi;
    private BigDecimal jarakKeBandar;
    private KodUOM kodUOM;
    private String keadaanTanah;
    private String bangunan;
    private String tanaman;
    private BigDecimal amaunDituntut;
    private String alasanTuntutan;
    private String pemohonUlasan;
    private String penilaiNoRujukan;
    private String penilaiTarikh;
    private String penilaiAmaun;
    private String penilaiUlasan;
    private String catatan;
    private Date tarikhMasuk;
    private String diMasuk;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private List<BigDecimal> itemNilaianTanahList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianBngnList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianLainList = new ArrayList<BigDecimal>();
    private List<Character> hadir = new ArrayList<Character>();
    private Penilaian penilaian;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private long idKehadiran;
    private long idPermohonanPhkTdkBerptg;
    private PermohonanPihakTidakBerkepentingan pptb;
    private List<PermohonanPihakTidakBerkepentingan> senaraiPPTB;
    private KodBank kodBank;
    private String kodbank;
    private String kodnegeri;
    private boolean display = false;
//    private String kodNegeri;
    private String bandar;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pbttidakberdaftar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        display = true;
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pbttidakberdaftar.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();

        hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        System.out.println("hakmilik pihak berkepentingan" + hpList.size());

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (hakmilikPermohonan != null) {
            senaraiPPTB = notisPenerimaanService.getHakmilikPPTBList(hakmilikPermohonan.getId());
            if (senaraiPPTB.isEmpty()) {
//                pptb=new PermohonanPihakTidakBerkepentingan();
//                InfoAudit info = new InfoAudit();
//                info.setDimasukOleh(peng);
//                info.setTarikhMasuk(new java.util.Date());
//                pptb.setInfoAudit(info);
//                pptb.setCawangan(permohonan.getCawangan());
//                pptb.setHakmilikPermohonan(hakmilikPermohonan);
//                
//                pptb = notisPenerimaanService.saveOrUpdatePPTB(pptb);
            } else {

                senaraiPPTB = notisPenerimaanService.getHakmilikPPTBList(hakmilikPermohonan.getId());
                if (!senaraiPPTB.isEmpty()) {
                    System.out.println("senarai is x null");
                }

            }


        }

        if (display1.equalsIgnoreCase("true")) {
            logger.debug("display true::x edit");
            getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        } else {
            logger.debug("display true::edit");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        }
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/maklumat_pbttidakberdaftar.jsp").addParameter("tab", "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            for (HakmilikPermohonan h : hakmilikPermohonanList) {
                senaraiPPTB = notisPenerimaanService.getHakmilikPPTBList(h.getId());
                if (!senaraiPPTB.isEmpty()) {
                    for (PermohonanPihakTidakBerkepentingan t : senaraiPPTB) {
                        PermohonanPihakTidakBerkepentingan pptb = notisPenerimaanService.getPBT(h.getId(), t.getIdPermohonanPhkTdkBerptg());


                    }


                }

            }


        }

        senaraiPengguna = notisPenerimaanService.getPengguna();

    }

    public Resolution showEditTuanTanah() {
        long idPermohonanPhkTdkBerptg = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPhkTdkBerptg"));
        pptb = pptbDAO.findById(idPermohonanPhkTdkBerptg);
        System.out.println(pptb);

        return new JSP("pengambilan/maklumat_pbttidakberdaftar_edit.jsp").addParameter("popup", "true");

    }

    public Resolution showTuanTanahPopup() {
        return new JSP("pengambilan/maklumat_pbttidakberdaftar_popup.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKehadiran() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        HakmilikPermohonan hp = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        senaraiPPTB = notisPenerimaanService.getHakmilikPPTBList(hp.getId());
        if (senaraiPPTB != null) {
            logger.info("value kodBank " + kodbank);
            pptb = new PermohonanPihakTidakBerkepentingan();
            if (getContext().getRequest().getParameter("typesender").equalsIgnoreCase("phtbedit")) {
                long idPermohonanPhkTdkBerptg = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPhkTdkBerptg"));
                pptb = pptbDAO.findById(idPermohonanPhkTdkBerptg);
            }
            KodBank kodB = new KodBank();
            kodB.setKod(kodbank);
            KodNegeri kodN = new KodNegeri();
            kodN.setKod(kodnegeri);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDimasukOleh(peng);
            pptb.setInfoAudit(infoAudit);
            pptb.setCawangan(permohonan.getCawangan());
            pptb.setHakmilikPermohonan(hp);
            if (getContext().getRequest().getParameter("typesender").equalsIgnoreCase("phtbadd")) {
                pptb.setKodBank(kodB);
                pptb.setKodNegeri(kodN);
                pptb.setNomborTelefon1(getContext().getRequest().getParameter("pptb.nomborTelefon"));
                pptb.setNomborAkaunBank(getContext().getRequest().getParameter("pptb.nomborAkaun"));
            } else if (getContext().getRequest().getParameter("typesender").equalsIgnoreCase("phtbedit")) {
                KodBank kodB2 = new KodBank();
                KodNegeri kodN2 = new KodNegeri();
                kodB2.setKod(getContext().getRequest().getParameter("pptb.kodBank.kod"));
                kodN2.setKod(getContext().getRequest().getParameter("pptb.kodNegeri.kod"));
                pptb.setKodBank(kodB2);
                pptb.setKodNegeri(kodN2);
                pptb.setNomborTelefon1(getContext().getRequest().getParameter("pptb.nomborTelefon1"));
                pptb.setNomborAkaunBank(getContext().getRequest().getParameter("pptb.nomborAkaunBank"));
            }
            pptb.setIdPermohonanPhkTdkBerptg(idPermohonanPhkTdkBerptg);
            pptb.setNama(getContext().getRequest().getParameter("pptb.nama"));
            pptb.setNomborPengenalan(getContext().getRequest().getParameter("pptb.nomborPengenalan"));
            pptb.setAlamat1(getContext().getRequest().getParameter("pptb.alamat1"));
            pptb.setAlamat2(getContext().getRequest().getParameter("pptb.alamat2"));
            pptb.setAlamat3(getContext().getRequest().getParameter("pptb.alamat3"));
            pptb.setAlamat4(getContext().getRequest().getParameter("pptb.alamat4"));
            pptb.setPoskod(getContext().getRequest().getParameter("pptb.poskod"));
            try {
                notisPenerimaanService.saveOrUpdatePPTB(pptb);
            } catch (Exception o) {
            }
        }

        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        rehydrate();
        return new JSP("pengambilan/maklumat_pbttidakberdaftar.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        rehydrate();
        return new RedirectResolution(maklumatpbttidakberdaftarActionBean.class, "locate");
    }

    public Resolution deletePbt() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        perbicaraanKehadiran = new PerbicaraanKehadiran();
        long idPermohonanPhkTdkBerptg = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPhkTdkBerptg"));
        pptb = pptbDAO.findById(idPermohonanPhkTdkBerptg);
        System.out.println("idPermohonanPhkTdkBerptg" + idPermohonanPhkTdkBerptg);
        if (pptb != null) {
            notisPenerimaanService.deleteAllPPTB(pptb);
        }
//        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/maklumat_pbttidakberdaftar.jsp").addParameter("tab", "true");
    }

    public List<PerbicaraanKehadiran> getSenaraiPbtTidakBerdaftar() {
        return senaraiPbtTidakBerdaftar;
    }

    public void setSenaraiPbtTidakBerdaftar(List<PerbicaraanKehadiran> senaraiPbtTidakBerdaftar) {
        this.senaraiPbtTidakBerdaftar = senaraiPbtTidakBerdaftar;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getAlasanTuntutan() {
        return alasanTuntutan;
    }

    public void setAlasanTuntutan(String alasanTuntutan) {
        this.alasanTuntutan = alasanTuntutan;
    }

    public String getCaraPemilikan() {
        return caraPemilikan;
    }

    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public KodUOM getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPemohonUlasan() {
        return pemohonUlasan;
    }

    public void setPemohonUlasan(String pemohonUlasan) {
        this.pemohonUlasan = pemohonUlasan;
    }

    public String getPenilaiAmaun() {
        return penilaiAmaun;
    }

    public void setPenilaiAmaun(String penilaiAmaun) {
        this.penilaiAmaun = penilaiAmaun;
    }

    public String getPenilaiNoRujukan() {
        return penilaiNoRujukan;
    }

    public void setPenilaiNoRujukan(String penilaiNoRujukan) {
        this.penilaiNoRujukan = penilaiNoRujukan;
    }

    public String getPenilaiTarikh() {
        return penilaiTarikh;
    }

    public void setPenilaiTarikh(String penilaiTarikh) {
        this.penilaiTarikh = penilaiTarikh;
    }

    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }

    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }

    public String getTanaman() {
        return tanaman;
    }

    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }

    public BigDecimal getAmaunDituntut() {
        return amaunDituntut;
    }

    public void setAmaunDituntut(BigDecimal amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public BigDecimal getHargaPembelian() {
        return hargaPembelian;
    }

    public void setHargaPembelian(BigDecimal hargaPembelian) {
        this.hargaPembelian = hargaPembelian;
    }

    public BigDecimal getJarakKeBandar() {
        return jarakKeBandar;
    }

    public void setJarakKeBandar(BigDecimal jarakKeBandar) {
        this.jarakKeBandar = jarakKeBandar;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public Date getTarikhPemilikan() {
        return tarikhPemilikan;
    }

    public void setTarikhPemilikan(Date tarikhPemilikan) {
        this.tarikhPemilikan = tarikhPemilikan;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getDiMasuk() {
        return diMasuk;
    }

    public void setDiMasuk(String diMasuk) {
        this.diMasuk = diMasuk;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public List<BigDecimal> getItemNilaianBngnList() {
        return itemNilaianBngnList;
    }

    public void setItemNilaianBngnList(List<BigDecimal> itemNilaianBngnList) {
        this.itemNilaianBngnList = itemNilaianBngnList;
    }

    public List<BigDecimal> getItemNilaianLainList() {
        return itemNilaianLainList;
    }

    public void setItemNilaianLainList(List<BigDecimal> itemNilaianLainList) {
        this.itemNilaianLainList = itemNilaianLainList;
    }

    public List<BigDecimal> getItemNilaianTanahList() {
        return itemNilaianTanahList;
    }

    public void setItemNilaianTanahList(List<BigDecimal> itemNilaianTanahList) {
        this.itemNilaianTanahList = itemNilaianTanahList;
    }

    public List<Character> getHadir() {
        return hadir;
    }

    public void setHadir(List<Character> hadir) {
        this.hadir = hadir;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan2() {
        return hakmilikPerbicaraan2;
    }

    public void setHakmilikPerbicaraan2(HakmilikPerbicaraan hakmilikPerbicaraan2) {
        this.hakmilikPerbicaraan2 = hakmilikPerbicaraan2;
    }

    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }

    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }

    public long getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(long idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public PermohonanPihakTidakBerkepentingan getPptb() {
        return pptb;
    }

    public void setPptb(PermohonanPihakTidakBerkepentingan pptb) {
        this.pptb = pptb;
    }

    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTB() {
        return senaraiPPTB;
    }

    public void setSenaraiPPTB(List<PermohonanPihakTidakBerkepentingan> senaraiPPTB) {
        this.senaraiPPTB = senaraiPPTB;
    }

    public String getBandar() {
        return bandar;
    }

    public void setBandar(String bandar) {
        this.bandar = bandar;
    }

    public String getKodnegeri() {
        return kodnegeri;
    }

    public void setKodnegeri(String kodnegeri) {
        this.kodnegeri = kodnegeri;
    }

    public String getKodbank() {
        return kodbank;
    }

    public void setKodbank(String kodbank) {
        this.kodbank = kodbank;
    }

    public long getIdPermohonanPhkTdkBerptg() {
        return idPermohonanPhkTdkBerptg;
    }

    public void setIdPermohonanPhkTdkBerptg(long idPermohonanPhkTdkBerptg) {
        this.idPermohonanPhkTdkBerptg = idPermohonanPhkTdkBerptg;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
