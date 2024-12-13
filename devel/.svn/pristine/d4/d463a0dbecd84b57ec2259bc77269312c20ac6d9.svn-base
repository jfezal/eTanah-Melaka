/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.KodUOM;
import etanah.dao.KodUOMDAO;
import etanah.dao.HakmilikDAO;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.PengambilanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.AmbilPampasan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.ambil.Penilaian;
import etanah.model.FasaPermohonan;
import etanah.model.Dokumen;
import etanah.model.KandunganFolder;
import etanah.model.Notis;
import etanah.model.PermohonanPihak;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import etanah.service.CalcTax;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.NotisService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/rekodTuntut")
public class rekodTuntutanLebihanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    Permohonan permohonan;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanservice;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PengambilanService service;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    String idHakmilik;
    @Inject
    Hakmilik hakmilik;
    @Inject
    KodUOM KodOUM;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    private FasaPermohonanService fasaPermohonanService;
    @Inject
    private NotisService notisService;
    private List<Hakmilik> list;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> luasLebihKecil;
    private List<HakmilikPermohonan> luasLebihBesar;
    private List<HakmilikPermohonan> luasSamaDengan;
    private Notis borangNotis;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private List<AmbilPampasan> ambilpampasanList;
    private List<String> luasTerlibat = new ArrayList<String>();
    private List<String> luasPelanB1 = new ArrayList<String>();
    private List<String> noLotBaru = new ArrayList<String>();
    private List<String> nilaiTanah = new ArrayList<String>();
    private List<String> ukurHalus = new ArrayList<String>();
    private List<String> kodUnitLuas = new ArrayList<String>();
    private List<String> balance = new ArrayList<String>();
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private FasaPermohonan fasaPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<Penilaian> penilaianList;
    private List<KandunganFolder> listKandunganFolder;
    private KandunganFolder kandFolder;
    private int size = 0;
    private FasaPermohonan kedesakan;
    private String idDokumen;
    private Dokumen idDokumenH;
    private String kodKedesakan;
    private Penilaian penilaian;
    private long dokumenH;
    private Date tarikhTerimaH;
    private String tempohH;
    private double tempohHmonth;
    private double tempohHyear;
    private String tiadaTempoh;
    private Date calendar;
    private List<Notis> borangNotisList;
    private String[] listluasTerlibat;
    private String noHakmilik;
    private String nilaiTanah2;
    private BigDecimal convLuas;
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal amountMH = new BigDecimal(0);
    private BigDecimal convH = new BigDecimal(0);
    private BigDecimal totalLuas = new BigDecimal(0);
    private BigDecimal faedah = new BigDecimal(0);
    private BigDecimal jumlahNilai = new BigDecimal(0);
    private BigDecimal jumlahFaedah = new BigDecimal(0);
    //private Penilaian penilaian;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOGGER = Logger.getLogger(rekodTuntutanLebihanActionBean.class);
    private BigDecimal jumlahPampasan;
    private Dokumen idDokumenK;
    private long dokumenK;
    private Date tarikhTerimaK;
    private String tempohK;
    private double tempohKmonth;
    private double tempohKyear;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }
    //@HttpCache(allow=false)

    public Resolution showForm2() {
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("pengambilan/maklumat_tanah_rizab.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/rekodTuntutanLebihanPHLLP.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP("pengambilan/kemasukan_hakmilik.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution popup() {
        return new JSP("pengambilan/maklumat_asas_tanah_pengambilan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = hakmilikpermohonanservice.findIDMHByIDMohonSebahagian(idPermohonan);//p.getSenaraiHakmilik();

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                try {
                    BigDecimal luasambil = hakmilikPermohonanList.get(i).getLuasTerlibat();
                    luasTerlibat.add(luasambil.toString());
                } catch (Exception e) {
                }
                try {
                    BigDecimal luaspelan = hakmilikPermohonanList.get(i).getLuasPelanB1();
                    luasPelanB1.add(luaspelan.toString());
                } catch (Exception e) {
                }
                try {
                    BigDecimal nilaiT = hakmilikPermohonanList.get(i).getNilaianJpph();
                    nilaiTanah.add(nilaiT.toString());
                } catch (Exception e) {
                }
                try {
                    String name = hakmilikPermohonanList.get(i).getKodUnitLuas().getKod();
                    kodUnitLuas.add(name);
                } catch (Exception e) {
                    kodUnitLuas.add("");
                }
                try {
                    String lotBaru = hakmilikPermohonanList.get(i).getLokaliti();
                    noLotBaru.add(lotBaru);
                } catch (Exception e) {
                    noLotBaru.add("");
                }

            }
        }
    }

    public Resolution pihakDetails() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
//        hakmilikPermohonanList =permohonan.getSenaraiHakmilik();
        pihakKepentinganList = hakmilik.getSenaraiPihakBerkepentingan();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        System.out.println("idMH " + idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        LOGGER.info("idPermohonan" + idPermohonan);
        LOGGER.info("idHakmilik" + idHakmilik);

//        for(HakmilikPihakBerkepentingan hp:pihakKepentinganList){
//        long idPihak = hp.getPihak().getIdPihak();
//        }
        simpanFaedahTambahan();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails2() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
//       hakmilikPermohonanList =permohonan.getSenaraiHakmilik();
        pihakKepentinganList = hakmilik.getSenaraiPihakBerkepentingan();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        LOGGER.info("idPermohonan" + idPermohonan);
        LOGGER.info("idHakmilik" + idHakmilik);

        long idMH = hakmilikPermohonan.getId();
        System.out.println("idMH " + idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
        System.out.println("senarai permohonan pihak >> " + senaraiPermohonanPihak.size());
//        for(HakmilikPihakBerkepentingan hp:pihakKepentinganList){
//        long idPihak = hp.getPihak().getIdPihak();
//        }

        /*String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
         if (idHakmilik != null) {
         hakmilik = hakmilikDAO.findById(idHakmilik);
         hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
         if (hakmilikPermohonan != null) {
         hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
         if (hakmilikPerbicaraan != null) {
         senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
         }
         }
         }*/
        simpanFaedahTambahan();

        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kod = null;
//        JOptionPane.showConfirmDialog(null, hakmilikPermohonanList.size());
        if (luasTerlibat.isEmpty()) {
            addSimpleError("Sila Masukkan Luas yang diambil");

        } else if (luasPelanB1.isEmpty()) {
            addSimpleError("Sila Masukkan Luas Pelan B1");
        } else {

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                System.out.println("hakmilikPermohonanList.size():" + hakmilikPermohonanList.size());
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                try {
                    String name = hakmilikPermohonanList.get(i).getHakmilik().getKodUnitLuas().getKod();
                    BigDecimal luasQuarter = BigDecimal.valueOf(0.0000);

                    if (hakmilikPermohonanList.get(i).getLuasTerlibat() != null) {

                        if (i < luasTerlibat.size()) {
                            if (luasTerlibat.get(i) != null) {
                                hmp.setLuasTerlibat(new BigDecimal(luasTerlibat.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setKodUnitLuas(kodUOM);

                        }
                        if (i < luasPelanB1.size()) {
                            if (luasPelanB1.get(i) != null) {
                                hmp.setLuasPelanB1(new BigDecimal(luasPelanB1.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setLuasPelanB1Uom(kodUOM);
                        }
                        LOGGER.info("noLotBaru.size() >>> " + noLotBaru.size());
                        if (i < noLotBaru.size()) {
                            if (noLotBaru.get(i) != null) {
                                hmp.setLokaliti(noLotBaru.get(i));
                            }
                        }
                        if (i < nilaiTanah.size()) {
                            if (nilaiTanah.get(i) != null) {
                                hmp.setNilaianJpph(new BigDecimal(nilaiTanah.get(i)));

                            }
                        }
                        System.out.println("unit measure " + hmp.getHakmilik().getKodUnitLuas().getKod());
                        if (hmp.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                            luasQuarter = new BigDecimal(2500);
                        } else if (hmp.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                            luasQuarter = new BigDecimal(0.25);
                        }
                        idHakmilik = hakmilikPermohonanList.get(i).getHakmilik().getIdHakmilik();
                        BigDecimal luas = hakmilikPermohonanList.get(i).getHakmilik().getLuas();
                        System.out.println("luas Asal" + luas);
                        BigDecimal baki = BigDecimal.valueOf(0.0000);
                        baki = luas.subtract(new BigDecimal(luasTerlibat.get(i)));
                        System.out.println("baki" + baki);
                        BigDecimal uHalus;
                        uHalus = baki.subtract(new BigDecimal(luasPelanB1.get(i)));
                        // if(uHalus < )
                        balance.add(uHalus.toString());

                        BigDecimal luasTambahan = BigDecimal.valueOf(0.0000);
                        // luas.multiply(new BigDecimal(0.24));
//                        luasTambahan = luas.add(uHalus);
                        System.out.println("Luas Halus " + uHalus);
                        luasTambahan = (new BigDecimal(luasTerlibat.get(i)).add(uHalus));
                        if (i < balance.size()) {
                            if (balance.get(i) != null) {
                                hmp.setLuasUkurHalus(new BigDecimal(balance.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setKodUnitLuas(kodUOM);
                        }
                        System.out.println("Luas Quarter " + luasQuarter.doubleValue());
                        System.out.println("Luas Tambahan " + luasTambahan.doubleValue());
                        System.out.println("Luas Ukur Haklus " + uHalus.doubleValue());
                        //if (luasQuarter.longValue() < luasTambahan.longValue()) {
                        if (luasQuarter.doubleValue() < uHalus.doubleValue()) {
                            hmp.setNomborRujukan("L4");
                            LOGGER.info("Lebih besar");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasLebihKecil = hakmilikpermohonanservice.findByIdHakmilikLebihKecil(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasLebihKecil " + luasLebihKecil.size());
                            // } else if (luasQuarter.longValue() > luasTambahan.longValue()) {
                        } else if (luasQuarter.doubleValue() > uHalus.doubleValue() && uHalus.doubleValue() != 0) {
                            hmp.setNomborRujukan("K4");
                            LOGGER.info("Lebih kecil");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasLebihBesar = hakmilikpermohonanservice.findByIdHakmilikLebihBesar(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasLebihBesar " + luasLebihBesar.size());
                            //  } else if (luasQuarter.longValue() == luasTambahan.longValue()) {
                        } else if (uHalus.doubleValue() == 0.0) {
                            hmp.setNomborRujukan("X4");
                            LOGGER.info("Sama Dengan");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasSamaDengan = hakmilikpermohonanservice.findByIdHakmilikSamaDengan(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasSamaDengan " + luasSamaDengan.size());
                        }

//                        if (baki.longValue() > new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("L4");
//                        } else if (baki.longValue() < new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("K4");
//                        } else if (baki.longValue() == new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("X4");
//                        }
                    }
                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
                hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hmp);
            }
            //hakmilikService.savehakmilikpermohonan(hakmilikPermohonanList);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilik2() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String kod = null;
//        JOptionPane.showConfirmDialog(null, hakmilikPermohonanList.size());
        if (luasTerlibat.isEmpty()) {
            addSimpleError("Sila Masukkan Luas yang diambil");

        } else if (luasPelanB1.isEmpty()) {
            addSimpleError("Sila Masukkan Luas Pelan B1");
        } else {

            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

                System.out.println("hakmilikPermohonanList.size():" + hakmilikPermohonanList.size());
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                try {
                    String name = hakmilikPermohonanList.get(i).getHakmilik().getKodUnitLuas().getKod();
                    BigDecimal luasQuarter = BigDecimal.valueOf(0.0000);

                    if (hakmilikPermohonanList.get(i).getLuasTerlibat() != null) {

                        if (i < luasTerlibat.size()) {
                            if (luasTerlibat.get(i) != null) {
                                hmp.setLuasTerlibat(new BigDecimal(luasTerlibat.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setKodUnitLuas(kodUOM);

                        }
                        if (i < luasPelanB1.size()) {
                            if (luasPelanB1.get(i) != null) {
                                hmp.setLuasPelanB1(new BigDecimal(luasPelanB1.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setLuasPelanB1Uom(kodUOM);
                        }
                        LOGGER.info("noLotBaru.size() >>> " + noLotBaru.size());
                        if (i < noLotBaru.size()) {
                            if (noLotBaru.get(i) != null) {
                                hmp.setLokaliti(noLotBaru.get(i));
                            }
                        }
                        if (i < nilaiTanah.size()) {
                            if (nilaiTanah.get(i) != null) {
                                hmp.setNilaianJpph(new BigDecimal(nilaiTanah.get(i)));

                            }
                        }
                        System.out.println("unit measure " + hmp.getHakmilik().getKodUnitLuas().getKod());
                        if (hmp.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                            luasQuarter = new BigDecimal(2500);
                        } else if (hmp.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                            luasQuarter = new BigDecimal(0.25);
                        }
                        idHakmilik = hakmilikPermohonanList.get(i).getHakmilik().getIdHakmilik();
                        BigDecimal luas = hakmilikPermohonanList.get(i).getHakmilik().getLuas();
                        System.out.println("luas Asal" + luas);
                        BigDecimal baki = BigDecimal.valueOf(0.0000);
                        baki = luas.subtract(new BigDecimal(luasTerlibat.get(i)));
                        System.out.println("baki" + baki);
                        BigDecimal uHalus;
                        uHalus = baki.subtract(new BigDecimal(luasPelanB1.get(i)));
                        // if(uHalus < )
                        balance.add(uHalus.toString());

                        BigDecimal luasTambahan = BigDecimal.valueOf(0.0000);
                        // luas.multiply(new BigDecimal(0.24));
//                        luasTambahan = luas.add(uHalus);
                        System.out.println("Luas Halus " + uHalus);
                        luasTambahan = (new BigDecimal(luasTerlibat.get(i)).add(uHalus));
                        if (i < balance.size()) {
                            if (balance.get(i) != null) {
                                hmp.setLuasUkurHalus(new BigDecimal(balance.get(i)));

                            }
                            KodUOM kodUOM = hmp.getHakmilik().getKodUnitLuas();
                            hmp.setKodUnitLuas(kodUOM);
                        }
                        System.out.println("Luas Quarter " + luasQuarter.doubleValue());
                        System.out.println("Luas Tambahan " + luasTambahan.doubleValue());
                        System.out.println("Luas Ukur Haklus " + uHalus.doubleValue());
                        //if (luasQuarter.longValue() < luasTambahan.longValue()) {
                        if (luasQuarter.doubleValue() < uHalus.doubleValue()) {
                            hmp.setNomborRujukan("L4");
                            LOGGER.info("Lebih besar");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasLebihKecil = hakmilikpermohonanservice.findByIdHakmilikLebihKecil(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasLebihKecil " + luasLebihKecil.size());
                            // } else if (luasQuarter.longValue() > luasTambahan.longValue()) {
                        } else if (luasQuarter.doubleValue() > uHalus.doubleValue() && uHalus.doubleValue() != 0) {
                            hmp.setNomborRujukan("K4");
                            LOGGER.info("Lebih kecil");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasLebihBesar = hakmilikpermohonanservice.findByIdHakmilikLebihBesar(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasLebihBesar " + luasLebihBesar.size());
                            //  } else if (luasQuarter.longValue() == luasTambahan.longValue()) {
                        } else if (uHalus.doubleValue() == 0.0) {
                            hmp.setNomborRujukan("X4");
                            LOGGER.info("Sama Dengan");
                            LOGGER.info("=== Luas Sebenar === " + hakmilikPermohonanList.get(i).getHakmilik().getLuas());
                            LOGGER.info("luasQuarter " + luasQuarter);
                            LOGGER.info("luasTambahan " + luasTambahan);

                            luasSamaDengan = hakmilikpermohonanservice.findByIdHakmilikSamaDengan(idHakmilik);
                            LOGGER.info("=== ID Hakmilik === " + idHakmilik);
                            LOGGER.info("List luasSamaDengan " + luasSamaDengan.size());
                        }

//                        if (baki.longValue() > new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("L4");
//                        } else if (baki.longValue() < new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("K4");
//                        } else if (baki.longValue() == new BigDecimal(luasPelanB1.get(i)).longValue()) {
//                            hmp.setNomborRujukan("X4");
//                        }
                    }
                } catch (Exception e) {
                    addSimpleError("Invalid Data");
                }
                hakmilikpermohonanservice.saveSingleHakmilikPermohonan(hmp);
            }
            //hakmilikService.savehakmilikpermohonan(hakmilikPermohonanList);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/rekodTuntutanLebihanPHLLP.jsp").addParameter("tab", "true");
    }

    public Resolution simpanFaedahTambahan() {
        DecimalFormat format = new DecimalFormat("###,###,###,##0.00");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        System.out.println("idhakmilikclasslain: " + idHakmilik);
        InfoAudit ia = new InfoAudit();

        Permohonan p = permohonanDAO.findById(idPermohonan);
        System.out.println("p" + p);
        System.out.println("id permohonansimpan:" + idPermohonan);

        Calendar cal = Calendar.getInstance();
        calendar = cal.getTime();
        double currentYear = cal.get(Calendar.YEAR);

        System.out.println("--CurrentYear" + currentYear);
        double currentMonth = cal.get(Calendar.MONTH);
        System.out.println("--CurrentMonth--" + currentMonth);

        kedesakan = fasaPermohonanService.getKodKedesakan(idPermohonan, "XK");
        if (kedesakan != null) {
            if (kedesakan.getKeputusan().getKod().equalsIgnoreCase("XK")) {
                kodKedesakan = kedesakan.getKeputusan().getKod();
                borangNotisList = notisService.getNotisBorangH(idPermohonan);
                System.out.println("saiz borangNotisList >> " + borangNotisList.size());
                for (int i = 0; i < borangNotisList.size(); i++) {
                    borangNotis = borangNotisList.get(i);
                    Calendar cal2 = Calendar.getInstance();
                    try {
                        if (borangNotis.getKodNotis().getKod().equalsIgnoreCase("NBH")) {
                            idDokumenH = notisService.getNotisBorangByidDokumen(borangNotis.getDokumenNotis().getIdDokumen());
                            System.out.println("--------idDokumenH-------" + idDokumenH.getIdDokumen());
                            dokumenH = idDokumenH.getIdDokumen();
                            System.out.println("--------dokumenH-------" + dokumenH);
                            System.out.println("--------tarikhTerimaH---------" + borangNotis.getTarikhTerima());
                            tarikhTerimaH = borangNotis.getTarikhTerima();
                            cal2.setTime(borangNotis.getTarikhTerima());
                            System.out.println(cal2);
                            double lastMonth = cal2.get(Calendar.MONTH);
                            System.out.println("--lastMonth--" + lastMonth);
                            System.out.println("--currentMonth--" + currentMonth);
                            double lastYear = cal2.get(Calendar.YEAR);
                            System.out.println("--lastYear--" + lastYear);
                            System.out.println("--lastYear--" + currentYear);

                            if (lastMonth > currentMonth) {
                                int year = (int) (currentYear - lastYear);
                                int month = (int) (lastMonth - currentMonth);
                                System.out.println("year" + year);
                                System.out.println("month" + month);

                                if (month != 0) {
                                    tempohH = month + " bulan " + year + " tahun";
                                    tempohHmonth = month;
                                    tempohHyear = year;
                                    System.out.println("tempohHmonth1:" + tempohHmonth);
                                    System.out.println("tempohHyear1:" + tempohHyear);

                                } else {
                                    tempohH = year + " tahun";
                                    tempohHyear = year;
                                    System.out.println("tempohHyear2:" + tempohHyear);
                                }
                            } else if (currentMonth > lastMonth) {
                                int year = (int) (currentYear - lastYear);
                                int month = (int) (currentMonth - lastMonth);
                                System.out.println("year" + year);
                                System.out.println("month" + month);

                                if (month != 0) {
                                    tempohH = month + " bulan " + year + " tahun";
                                    tempohHmonth = month;
                                    tempohHyear = year;
                                    System.out.println("tempohHmonth3:" + tempohHmonth);
                                    System.out.println("tempohHyear3:" + tempohHyear);
                                } else {
                                    tempohH = year + " tahun";
                                    tempohHyear = year;
                                    System.out.println("tempohHyear4:" + tempohHyear);

                                }
                            }
                        }
                    } catch (Exception jk) {
                    }
                }
            } //  System.out.println("--------Tarikh Terima H-----------" + tarikhTerimaH);
            //   jumlahFaedah = BigDecimal.valueOf(tempohHmonth).add(BigDecimal.valueOf(tempohHyear));
            //  System.out.println("jumlahFaedah:" + jumlahFaedah);



            // idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");    
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            // for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            LOGGER.info("hp:" + idHakmilik);
            System.out.println("hp id:" + hakmilikPermohonan.getId());
            hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hakmilikPermohonan.getId());
            LOGGER.info("hpID:" + hakmilikPermohonan.getId());
            senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("senarai permohonan pihak XK >> " + senaraiPermohonanPihak.size());
            if (hakmilikPerbicaraan != null) {
                perbicaraanKehadiranList = service.getPerbicaraanKehadiranByidMHnIdPermohonanPampasan(hakmilikPerbicaraan.getIdPerbicaraan());
                LOGGER.info("hakmilikPerbicaraanID:" + hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiranList != null) {
                    for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                        LOGGER.info("Perbicaraan:" + pk);
                        System.out.println("perbicaraankehadiransenarai:" + perbicaraanKehadiranList.size());
                        for (HakmilikPihakBerkepentingan pb : hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            LOGGER.info("HakmilikPihakBerkepentingan" + hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan());

                            String idhakmilik = idHakmilik;
                            System.out.println("idhakmilikhp:" + idhakmilik);
                            if (pb.getPihak().getIdPihak() == pk.getPihak().getPihak().getIdPihak()) {
                                if (pb.getAktif() == 'Y') {
                                    System.out.println("pbpihak:" + pb.getPihak().getIdPihak());
                                    System.out.println("pkpihak:" + pk.getPihak().getPihak().getIdPihak());
                                    BigDecimal ukur = hakmilikPermohonan.getLuasUkurHalus();
                                    System.out.println("ukur:" + ukur);
                                    BigDecimal jpph = hakmilikPermohonan.getNilaianJpph();
                                    System.out.println("jpph:" + jpph);
                                    jumlahNilai = ukur.multiply(jpph);
                                    double kadarfaedah = jumlahNilai.doubleValue() * 0.08 * tempohHyear;
                                    double kadarfaedahsebulan = (jumlahNilai.doubleValue() / 12 * tempohHmonth) * 0.08;
                                    double jumBesar = jumlahNilai.doubleValue() + kadarfaedah + kadarfaedahsebulan;
                                    System.out.println("tempohHyear" + tempohHyear);
                                    System.out.println("tempohHmonth" + tempohHmonth);
                                    System.out.println("jumlahNilai:" + jumlahNilai);
                                    System.out.println("kadarfaedah:" + kadarfaedah);
                                    System.out.println("kadarfaedahsebulan:" + kadarfaedahsebulan);
                                    System.out.println("jumBesar:" + jumBesar);
                                    BigDecimal penyebut = new BigDecimal(pb.getSyerPenyebut());
                                    System.out.println("penyebut" + penyebut);
                                    BigDecimal pembilang = new BigDecimal(pb.getSyerPembilang());
                                    System.out.println("pembilang:" + pembilang);
                                    double syer = pembilang.doubleValue() / penyebut.doubleValue();
                                    System.out.println("syer:" + syer);
                                    BigDecimal syer2 = new BigDecimal(syer);
                                    //double jumpampas = jumBesar * syer;
                                    BigDecimal jumlahBesar = new BigDecimal(jumBesar);
                                    System.out.println("jumlahBesar >> " + jumlahBesar);
                                    BigDecimal jumpampas = jumlahBesar.multiply(syer2);
                                    System.out.println("jumpampas" + jumpampas);
                                    if (jumpampas.longValue() < 0) {
                                        jumpampas = jumpampas.multiply(new BigDecimal(-1));
                                    }
                                    BigDecimal b = jumpampas;
                                    if (perbicaraanKehadiranList != null) {

                                        System.out.println("pk.getIdKehadiran() " + pk.getIdKehadiran());
                                        penilaian = pengambilanService.findPenilaian(pk.getIdKehadiran());
                                        if (penilaian == null) {
                                            penilaian = new Penilaian();
                                        }
                                        ia = peng.getInfoAudit();
                                        ia.setDimasukOleh(peng);
                                        ia.setTarikhMasuk(new java.util.Date());
                                        penilaian.setInfoAudit(ia);
                                        penilaian.setCawangan(peng.getKodCawangan());
                                        penilaian.setItem("Pampasan Tambahan");
                                        penilaian.setJenis('P');
                                        penilaian.setAmaun(b);
                                        penilaian.setKehadiran(pk);
                                        notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
                                        System.out.println("Simpan Berjaya !");
                                        //   }
                                    }
                                }
                            }
                        }
                    }
                }
            }



        } else {
            kedesakan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
            System.out.println("Ada Desakan");
            kodKedesakan = kedesakan.getKeputusan().getKod();
            borangNotisList = notisService.getNotisBorangK(idPermohonan);
            for (int j = 0; j < borangNotisList.size(); j++) {
                borangNotis = borangNotisList.get(j);
                Calendar cal2 = Calendar.getInstance();
                try {
                    if (borangNotis.getKodNotis().getKod().equalsIgnoreCase("NBK")) {
                        idDokumenK = notisService.getNotisBorangByidDokumen(borangNotis.getDokumenNotis().getIdDokumen());
                        System.out.println("--------idDokumenK-------" + idDokumenK.getIdDokumen());
                        dokumenK = idDokumenK.getIdDokumen();
                        System.out.println("--------dokumenK-------" + dokumenK);
                        System.out.println("--------tarikhTerimaK---------" + borangNotis.getTarikhTerima());
                        tarikhTerimaK = borangNotis.getTarikhTerima();
                        cal2.setTime(borangNotis.getTarikhTerima());
                        System.out.println(cal2);
                        double lastMonth = cal2.get(Calendar.MONTH);
                        System.out.println("--lastMonth--" + lastMonth);
                        System.out.println("--currentMonth--" + currentMonth);
                        double lastYear = cal2.get(Calendar.YEAR);
                        System.out.println("--lastYear--" + lastYear);
                        System.out.println("--lastYear--" + currentYear);
                        if (lastMonth > currentMonth) {
                            int year = (int) (currentYear - lastYear);
                            int month = (int) (lastMonth - currentMonth);
                            System.out.println("year" + year);
                            System.out.println("month" + month);
                            if (month != 0) {
                                tempohK = month + " bulan " + year + " tahun";
                                tempohKmonth = month;
                                tempohKyear = year;
                                System.out.println("tempohKmonth1:" + tempohKmonth);
                                System.out.println("tempohKyear1:" + tempohKyear);

                            } else {
                                tempohK = year + " tahun";
                                tempohKyear = year;
                                System.out.println("tempohKyear2:" + tempohKyear);
                            }

                        } else if (currentMonth > lastMonth) {
                            int year = (int) (currentYear - lastYear);
                            int month = (int) (currentMonth - lastMonth);
                            System.out.println("year" + year);
                            System.out.println("month" + month);
                            if (month != 0) {
                                tempohK = month + " bulan " + year + " tahun";
                                tempohKmonth = month;
                                tempohKyear = year;
                                System.out.println("tempohKmonth3:" + tempohKmonth);
                                System.out.println("tempohKyear3:" + tempohKyear);

                            } else {
                                tempohK = year + " tahun";
                                tempohKyear = year;
                                System.out.println("tempohKyear4:" + tempohKyear);

                            }

                        }

                    }
                } catch (Exception jkl) {
                }
                // idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");    
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
                // for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                LOGGER.info("hpK:" + idHakmilik);
                System.out.println("hp idK:" + hakmilikPermohonan.getId());
                hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hakmilikPermohonan.getId());
                LOGGER.info("hpIDK:" + hakmilikPermohonan.getId());
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
                System.out.println("senarai permohonan pihak DE >> " + senaraiPermohonanPihak.size());
                if (hakmilikPerbicaraan != null) {
                    perbicaraanKehadiranList = service.getPerbicaraanKehadiranByidMHnIdPermohonanPampasan(hakmilikPerbicaraan.getIdPerbicaraan());
                    LOGGER.info("hakmilikPerbicaraanIDK:" + hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiranList != null) {
                        for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                            LOGGER.info("Perbicaraan:" + pk);
                            System.out.println("perbicaraankehadiransenarai:" + perbicaraanKehadiranList.size());
                            for (HakmilikPihakBerkepentingan pb : hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan()) {
                                LOGGER.info("HakmilikPihakBerkepentingan" + hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan());

                                String idhakmilik = idHakmilik;
                                System.out.println("idhakmilikhp:" + idhakmilik);
                                if (pb.getPihak().getIdPihak() == pk.getPihak().getPihak().getIdPihak()) {
                                    if (pb.getAktif() == 'Y') {
                                        System.out.println("pbpihak:" + pb.getPihak().getIdPihak());
                                        System.out.println("pkpihak:" + pk.getPihak().getPihak().getIdPihak());
                                        BigDecimal ukur = hakmilikPermohonan.getLuasUkurHalus();
                                        System.out.println("ukur:" + ukur);
                                        BigDecimal jpph = hakmilikPermohonan.getNilaianJpph();
                                        System.out.println("jpph:" + jpph);
                                        jumlahNilai = ukur.multiply(jpph);
                                        double kadarfaedah = jumlahNilai.doubleValue() * 0.08 * tempohHyear;
                                        double kadarfaedahsebulan = (jumlahNilai.doubleValue() / 12 * tempohHmonth) * 0.08;
                                        double jumBesar = jumlahNilai.doubleValue() + kadarfaedah + kadarfaedahsebulan;
                                        System.out.println("jumlahNilai:" + jumlahNilai);
                                        System.out.println("kadarfaedah:" + kadarfaedah);
                                        System.out.println("kadarfaedahsebulan:" + kadarfaedahsebulan);
                                        System.out.println("jumBesar:" + jumBesar);
                                        BigDecimal penyebut = new BigDecimal(pb.getSyerPenyebut());
                                        System.out.println("penyebut" + penyebut);
                                        BigDecimal pembilang = new BigDecimal(pb.getSyerPembilang());
                                        System.out.println("pembilang:" + pembilang);
                                        double syer = pembilang.doubleValue() / penyebut.doubleValue();
                                        System.out.println("syer:" + syer);
                                        //double jumpampas = jumBesar * syer;
                                        BigDecimal jumlahBesar = new BigDecimal(jumBesar);
                                        BigDecimal syer2 = new BigDecimal(syer);
                                        System.out.println("jumlahBesar >> " + jumlahBesar);
                                        BigDecimal jumpampas = jumlahBesar.multiply(syer2);
                                        System.out.println("jumpampas" + jumpampas);
//                                
                                        System.out.println("syer:" + syer);
                                        System.out.println("jumpampas" + jumpampas);
                                        if (jumpampas.longValue() < 0) {
                                            jumpampas = jumpampas.multiply(new BigDecimal(-1));
                                        }
//                                    if (jumpampas < 0) {
//                                        jumpampas = (jumpampas * -1);
//                                    }
                                        BigDecimal b = jumpampas;
                                        if (perbicaraanKehadiranList != null) {

                                            System.out.println("pk.getIdKehadiran() " + pk.getIdKehadiran());
                                            penilaian = pengambilanService.findPenilaian(pk.getIdKehadiran());
                                            if (penilaian == null) {
                                                penilaian = new Penilaian();
                                            }
                                            ia = peng.getInfoAudit();
                                            ia.setDimasukOleh(peng);
                                            ia.setTarikhMasuk(new java.util.Date());
                                            penilaian.setInfoAudit(ia);
                                            penilaian.setCawangan(peng.getKodCawangan());
                                            penilaian.setItem("Pampasan Tambahan");
                                            penilaian.setJenis('P');
                                            penilaian.setAmaun(b);
                                            penilaian.setKehadiran(pk);
                                            notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
                                            System.out.println("Simpan Berjaya !");
                                            //   }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }






        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanFaedahTambahan2() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "32RekodSampaiTampalDesakan");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan().getKod().equals("DE"))//----Kedesakan:Count Borang K
            {

                for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                    hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hp.getId());
                    if (hakmilikPerbicaraan != null) {
                        perbicaraanKehadiranList = service.getPerbicaraanKehadiranByidMHnIdPermohonanPampasan(hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiranList != null) {
                            for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                                for (HakmilikPihakBerkepentingan pb : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                                    BigDecimal nilai = hp.getNilaianJpph();
                                    BigDecimal penyebut = new BigDecimal(pb.getSyerPenyebut());
                                    BigDecimal pembilang = new BigDecimal(pb.getSyerPembilang());
                                    BigDecimal syer = penyebut.divide(pembilang);
                                    BigDecimal jumlahPampasan = nilai.multiply(syer);

                                    penilaianList = service.getPenilaianByidHadirPampasan(pk.getIdKehadiran());
                                    System.out.println("penilaianList " + penilaianList.size());
                                    kandFolder = notisPenerimaanService.getDokumenK(permohonan.getFolderDokumen().getFolderId());
                                    System.out.println("kandungan folder " + kandFolder);
                                    Date now = new Date();


                                    if (kandFolder != null) {
                                        System.out.println("kandungan folder" + kandFolder);

                                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                                        int year = Integer.parseInt(sdf2.format(new java.util.Date()).toString());
                                        System.out.println("year " + year);
                                        GregorianCalendar calLeap = new GregorianCalendar();
                                        BigDecimal charge = BigDecimal.valueOf(0.06);
                                        long countDay = (now.getTime() - kandFolder.getInfoAudit().getTarikhMasuk().getTime()) / 1000 / 60 / 60 / 24;
                                        BigDecimal bilDay;
                                        BigDecimal amount;
                                        BigDecimal afterCharge;
                                        if (calLeap.isLeapYear(year)) {
                                            System.out.println("1");
                                            BigDecimal DefaultValue = BigDecimal.valueOf(366);

                                            afterCharge = jumlahPampasan.multiply(charge);
                                            bilDay = new BigDecimal(countDay).divide(DefaultValue);
                                            amount = afterCharge.multiply(bilDay);

                                        } else {
                                            System.out.println("2");
                                            BigDecimal DefaultValue = BigDecimal.valueOf(365);
                                            afterCharge = jumlahPampasan.multiply(charge);
                                            bilDay = new BigDecimal(countDay).divide(DefaultValue);
                                            amount = afterCharge.multiply(bilDay);


                                        }
                                        if (penilaianList != null) {
                                            Penilaian penilaian = new Penilaian();
                                            ia = peng.getInfoAudit();
                                            ia.setDimasukOleh(peng);
                                            ia.setTarikhMasuk(new java.util.Date());
                                            penilaian.setInfoAudit(ia);
                                            penilaian.setKehadiran(pk);
                                            penilaian.setCawangan(peng.getKodCawangan());
                                            penilaian.setItem("Pampasan Jupem");
                                            penilaian.setAmaun(amount);
                                            service.simpanPampasan(penilaian);
                                        }
                                    }



                                }




                            }


                        }



                    }


                }
                //--------------------------------------------------------------//

            } else//Tidak Desakan
            {
                //-------------------//

                for (int x = 0; x < hakmilikPermohonanList.size(); x++) {
                    HakmilikPermohonan hp = hakmilikPermohonanList.get(x);
                    hakmilikPerbicaraan = service.getHakmilikPerbicaraanPampasan(hp.getId());
                    if (hakmilikPerbicaraan != null) {
                        perbicaraanKehadiranList = service.getPerbicaraanKehadiranByidMHnIdPermohonanPampasan(hakmilikPerbicaraan.getIdPerbicaraan());
                        kandFolder = notisPenerimaanService.getDokumenH(permohonan.getFolderDokumen().getFolderId());
                        BigDecimal nilai = hp.getNilaianJpph();
                        BigDecimal penyebut = new BigDecimal(hp.getHakmilik().getSenaraiPihakBerkepentingan().get(x).getSyerPenyebut());
                        System.out.println("penyebut " + penyebut);
                        BigDecimal pembilang = new BigDecimal(hp.getHakmilik().getSenaraiPihakBerkepentingan().get(x).getSyerPembilang());
                        System.out.println("pembilang " + pembilang);
                        BigDecimal syer = penyebut.divide(pembilang);
                        System.out.println("syer" + syer);
                        BigDecimal t = BigDecimal.valueOf(1000);
                        BigDecimal jumlahPampasan = nilai.divide(t).multiply(hp.getLuasTerlibat());
                        BigDecimal pampasan = jumlahPampasan.multiply(syer);
                        BigDecimal total = BigDecimal.valueOf(0.00);
                        System.out.println("jumlah pampasan " + pampasan);
                        Date now = new Date();


                        if (kandFolder != null) {

                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                            int year = Integer.parseInt(sdf2.format(new java.util.Date()).toString());
                            GregorianCalendar calLeap = new GregorianCalendar();
                            BigDecimal charge = BigDecimal.valueOf(0.06);
                            long countDay = (now.getTime() - kandFolder.getInfoAudit().getTarikhMasuk().getTime()) / 1000 / 60 / 60 / 24;
                            System.out.println("brg H" + kandFolder.getInfoAudit().getTarikhMasuk().getTime());
                            System.out.println("Tarikh Today" + now.getTime());
                            System.out.println("countDay" + countDay);
                            BigDecimal bilDay = BigDecimal.valueOf(0.00);
                            BigDecimal amount;
                            BigDecimal afterCharge;
                            if (calLeap.isLeapYear(year)) {
                                BigDecimal DefaultValue = BigDecimal.valueOf(366);

                                afterCharge = jumlahPampasan.multiply(charge);
                                bilDay = new BigDecimal(countDay).divide(DefaultValue, RoundingMode.HALF_UP);
                                amount = afterCharge.multiply(bilDay);
                                total = amount.multiply(syer);

                            } else {
                                BigDecimal DefaultValue = BigDecimal.valueOf(365);
                                afterCharge = jumlahPampasan.multiply(charge);
                                System.out.println("afterCharge" + afterCharge);
                                bilDay = new BigDecimal(countDay).divide(DefaultValue, RoundingMode.HALF_UP);
                                System.out.println("bilangan hari " + bilDay);
                                amount = afterCharge.multiply(bilDay);
                                System.out.println("amount " + amount);
                                total = amount.multiply(syer);


                            }
                        }


                        if (perbicaraanKehadiranList != null) {
                            for (int i = 0; i < perbicaraanKehadiranList.size(); i++) {
                                penilaianList = service.getPenilaianByidHadirPampasan(perbicaraanKehadiranList.get(i).getIdKehadiran());
                                if (penilaianList != null) {
                                    Penilaian penilaian = new Penilaian();
                                    ia = peng.getInfoAudit();
                                    ia.setDimasukOleh(peng);
                                    ia.setTarikhMasuk(new java.util.Date());
                                    penilaian.setInfoAudit(ia);
                                    penilaian.setKehadiran(perbicaraanKehadiranList.get(i));
                                    penilaian.setCawangan(peng.getKodCawangan());
                                    penilaian.setItem("Faedah");
                                    penilaian.setJenis('F');
                                    penilaian.setAmaun(total);
                                    service.simpanPampasan(penilaian);
                                    simpanFaedahTambahan2();


                                }

                            }


                        }



                    }


                }
                //-------------------//



            }
        }



        return new ForwardResolution("/WEB-INF/jsp/pengambilan/rekodTuntutanLebihan.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSingle() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        HakmilikPermohonan hmp = new HakmilikPermohonan();
        String id = getContext().getRequest().getParameter("id");
        hmp = hakmilikService.findHakmilikByIdHakmilik(Long.parseLong(id));

        if (hmp != null) {
//                JOptionPane.showMessageDialog(null, id);


            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hmp.setInfoAudit(ia);
            hmp.setHakmilik(hakmilik);
            hmp.setPermohonan(permohonan);
            hakmilikPermohonanList.remove(hmp);
            hakmilikService.deletehakmilikpermohonan(hakmilikPermohonanList);
        }
        return new RedirectResolution(MaklumatHakmilikActionBean.class);
    }

    public Resolution searchHakmilik() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idH = getContext().getRequest().getParameter("idH");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilik = hakmilikService.findById(idH);
//        JOptionPane.showMessageDialog(null, hakmilik.getNoHakmilik());
        if (idPermohonan != null) {
            InfoAudit info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            HakmilikPermohonan hmp = new HakmilikPermohonan();

            hmp.setInfoAudit(info);
            hmp.setPermohonan(permohonan);
            hmp.setHakmilik(hakmilik);
            hakmilikPermohonanList.add(hmp);
            hakmilikService.saveOrUpdatehakmilikpermohonan(hmp);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");


    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(rekodTuntutanLebihanActionBean.class, "locate");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp.getHakmilik();
            hakmilikService.simpanAmbil(hakmilik, p);
            addSimpleMessage("Maklumat telah berjaya disimpan.");

        }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
//        hakmilikService.simpanAmbil(hakmilik);
//
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }

    public List<Hakmilik> getList() {
        return list;
    }

    public void setList(List<Hakmilik> list) {
        this.list = list;
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

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    public String[] getAtasTanah() {
//        return luasTerlibat;
//    }
//
//    public void setAtasTanah(String[] luasTerlibat) {
//        this.luasTerlibat = luasTerlibat;
//    }
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getNoHakmilik() {
        return noHakmilik;
    }

    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<String> getLuasTerlibat() {
        return luasTerlibat;
    }

    public void setLuasTerlibat(List<String> luasTerlibat) {
        this.luasTerlibat = luasTerlibat;
    }

    public String[] getListluasTerlibat() {
        return listluasTerlibat;
    }

    public void setListluasTerlibat(String[] listluasTerlibat) {
        this.listluasTerlibat = listluasTerlibat;
    }

    public List<String> getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(List<String> kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public KodUOM getKodOUM() {
        return KodOUM;
    }

    public void setKodOUM(KodUOM KodOUM) {
        this.KodOUM = KodOUM;
    }

    public List<String> getLuasPelanB1() {
        return luasPelanB1;
    }

    public void setLuasPelanB1(List<String> luasPelanB1) {
        this.luasPelanB1 = luasPelanB1;
    }

    public List<String> getNilaiTanah() {
        return nilaiTanah;
    }

    public void setNilaiTanah(List<String> nilaiTanah) {
        this.nilaiTanah = nilaiTanah;
    }

    public String getNilaiTanah2() {
        return nilaiTanah2;
    }

    public void setNilaiTanah2(String nilaiTanah2) {
        this.nilaiTanah2 = nilaiTanah2;
    }

    public List<AmbilPampasan> getAmbilpampasanList() {
        return ambilpampasanList;
    }

    public void setAmbilpampasanList(List<AmbilPampasan> ambilpampasanList) {
        this.ambilpampasanList = ambilpampasanList;
    }

    public List<String> getUkurHalus() {
        return ukurHalus;
    }

    public void setUkurHalus(List<String> ukurHalus) {
        this.ukurHalus = ukurHalus;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public KandunganFolder getKandFolder() {
        return kandFolder;
    }

    public void setKandFolder(KandunganFolder kandFolder) {
        this.kandFolder = kandFolder;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<String> getBalance() {
        return balance;
    }

    public void setBalance(List<String> balance) {
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountMH() {
        return amountMH;
    }

    public void setAmountMH(BigDecimal amountMH) {
        this.amountMH = amountMH;
    }

    public BigDecimal getConvH() {
        return convH;
    }

    public void setConvH(BigDecimal convH) {
        this.convH = convH;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public BigDecimal getConvLuas() {
        return convLuas;
    }

    public void setConvLuas(BigDecimal convLuas) {
        this.convLuas = convLuas;
    }

    public List<HakmilikPermohonan> getLuasLebihBesar() {
        return luasLebihBesar;
    }

    public void setLuasLebihBesar(List<HakmilikPermohonan> luasLebihBesar) {
        this.luasLebihBesar = luasLebihBesar;
    }

    public List<HakmilikPermohonan> getLuasLebihKecil() {
        return luasLebihKecil;
    }

    public void setLuasLebihKecil(List<HakmilikPermohonan> luasLebihKecil) {
        this.luasLebihKecil = luasLebihKecil;
    }

    public List<HakmilikPermohonan> getLuasSamaDengan() {
        return luasSamaDengan;
    }

    public void setLuasSamaDengan(List<HakmilikPermohonan> luasSamaDengan) {
        this.luasSamaDengan = luasSamaDengan;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public FasaPermohonan getKedesakan() {
        return kedesakan;
    }

    public void setKedesakan(FasaPermohonan kedesakan) {
        this.kedesakan = kedesakan;
    }

    public String getKodKedesakan() {
        return kodKedesakan;
    }

    public void setKodKedesakan(String kodKedesakan) {
        this.kodKedesakan = kodKedesakan;
    }

    public long getDokumenH() {
        return dokumenH;
    }

    public void setDokumenH(long dokumenH) {
        this.dokumenH = dokumenH;
    }

    public long getDokumenK() {
        return dokumenK;
    }

    public void setDokumenK(long dokumenK) {
        this.dokumenK = dokumenK;
    }

    public Date getTarikhTerimaH() {
        return tarikhTerimaH;
    }

    public void setTarikhTerimaH(Date tarikhTerimaH) {
        this.tarikhTerimaH = tarikhTerimaH;
    }

    public Date getTarikhTerimaK() {
        return tarikhTerimaK;
    }

    public void setTarikhTerimaK(Date tarikhTerimaK) {
        this.tarikhTerimaK = tarikhTerimaK;
    }

    public Notis getBorangNotis() {
        return borangNotis;
    }

    public double getTempohHmonth() {
        return tempohHmonth;
    }

    public void setTempohHmonth(int tempohHmonth) {
        this.tempohHmonth = tempohHmonth;

    }

    public double getTempohHyear() {
        return tempohHyear;
    }

    public void setTempohHyear(int tempohHyear) {
        this.tempohHyear = tempohHyear;

    }

    public double getTempohKmonth() {
        return tempohKmonth;
    }

    public void setTempohKmonth(int tempohKmonth) {
        this.tempohKmonth = tempohKmonth;
    }

    public double getTempohKyear() {
        return tempohKyear;
    }

    public void setTempohKyear(int tempohKyear) {
        this.tempohKyear = tempohKyear;
    }

    public void setBorangNotis(Notis borangNotis) {
        this.borangNotis = borangNotis;
    }

    public List<Notis> getBorangNotisList() {
        return borangNotisList;
    }

    public void setBorangNotisList(List<Notis> borangNotisList) {
        this.borangNotisList = borangNotisList;
    }

    public Date getCalendar() {
        return calendar;
    }

    /**
     * @param calendar the calendar to set
     */
    public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }

    public String getTempohH() {
        return tempohH;
    }

    /**
     * @param TempohH the TempohH to set
     */
    public void setTempohH(String tempohH) {
        this.tempohH = tempohH;
    }

    public String getTempohK() {
        return tempohK;
    }

    public void setTempohK(String tempohK) {
        this.tempohK = tempohK;
    }

    public BigDecimal getFaedah() {
        return faedah;
    }

    public void setFaedah(BigDecimal faedah) {
        this.faedah = faedah;
    }

    public BigDecimal getjumlahPampasan() {
        return jumlahPampasan;
    }

    public void setjumlahPampasan(BigDecimal jumlahPampasan) {
        this.jumlahPampasan = jumlahPampasan;
    }

    public List<String> getNoLotBaru() {
        return noLotBaru;
    }

    public void setNoLotBaru(List<String> noLotBaru) {
        this.noLotBaru = noLotBaru;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }
}
