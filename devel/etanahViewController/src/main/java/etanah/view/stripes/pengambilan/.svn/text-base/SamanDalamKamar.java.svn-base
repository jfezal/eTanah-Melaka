/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCaraBayaran;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.KodCawangan;
import etanah.model.BorangQ;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.daftar.PembetulanService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/samanDalamKamar")
public class SamanDalamKamar extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private static final Logger logger = Logger.getLogger(DerafPerintahPtspActionBean.class);
    private String idPermohonan;
    private String idSblm;
    private String idHakmilik;
    private long idPihak;
    private long idPermohonanPihak;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private BorangQ borangQ;
    private Penilaian penilaian;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private KodCawangan cawangan;
    private String namaPenolongKananPendaftar;
    private String samanPemulaBil;
    private String tarikhSaman;
    private String lokasiSaman;
    private String tarikhIkrar;
    private String tarikhTerimaPerintah;
    private String show;
    private String hide = "FALSE";
    private String jam;
    private String minit;
    private String pagiPetang;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    //added
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> senaraihakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<PerbicaraanKehadiran> senaraiHadir;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<PerbicaraanKehadiran> senaraiNOTPM;
    private List<PerbicaraanKehadiran> senaraiBantah;
    private List<PerbicaraanKehadiran> senaraiTBantah;
    private List<PerbicaraanKehadiran> senaraiPM;
    private List<PermohonanPihak> mohonPihakList;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianTanahList;
    private List<String> status = new ArrayList<String>();
    private List<String> catatan = new ArrayList<String>();
    private List<String> status1 = new ArrayList<String>();
    private List<String> catatan1 = new ArrayList<String>();
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private AmbilPampasan ambilPampasan;
    private BigDecimal amnt = BigDecimal.ZERO;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private BigDecimal jumlah;
    private BigDecimal tawaranPampasan;
    private int size = 0;
    private int sizeTB = 0;

    @DefaultHandler
    public Resolution showForm1() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
//        show = "Edit";
        getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        show = "Details";
        System.out.println("masuk showForm2");

        getContext().getRequest().setAttribute("showSaman", Boolean.TRUE);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

//    public Resolution showForm4() {
////        show = "Details";
//        System.out.println("masuk showForm4");
//
//        getContext().getRequest().setAttribute("showDetailsNOTPM", Boolean.TRUE);
//        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
//    }

    public Resolution showForm3() {
        logger.info("::: masuk showEdit :::");
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            if (permohonan != null) {
//                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
//            }
//        }
//        show = "Edit";
//        hide = "TRUE";
        getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        }
    }

    public Resolution hakmilikDetails() {
        logger.info("hakmilikDetails");
//        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.info("idHakmilik " + idHakmilik);
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTSP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPT")) {
            try {
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                logger.info("idPermohonan " + idPermohonan);
                permohonan = permohonanDAO.findById(idPermohonan);

                if (permohonan != null) {
                    logger.info("permohonan hakmilikDetails != null");

                    hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

                    long idMH = hakmilikPermohonan.getId();
                    System.out.println("idMH " + idMH);

                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
                    System.out.println("-------hakmilikDetails.hakmilikPerbicaraan-----------" + hakmilikPerbicaraan);
                    logger.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());

                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
                    logger.info("idPermohonanPihak : " + permohonanPihak.getIdPermohonanPihak());
                }

//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                logger.info("perbicaraanKehadiranList = " + perbicaraanKehadiranList.size());
                for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                    pk.getIdKehadiran();
                    logger.info("DAPAT ID HADIR " + pk.getIdKehadiran());
                }
            } catch (Exception h) {
            }
        } else {
            try {
                idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                logger.info("idPermohonan " + idPermohonan);
                permohonan = permohonanDAO.findById(idPermohonan);
                idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

                if (permohonan != null) {
                    logger.info("permohonan hakmilikDetails != null");

                    hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);

                    long idMH = hakmilikPermohonan.getId();
                    System.out.println("idMH " + idMH);

                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
                    System.out.println("-------hakmilikDetails.hakmilikPerbicaraan-----------" + hakmilikPerbicaraan);
                    logger.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());

//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//                    idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
//                    logger.info("idPermohonanPihak : " + permohonanPihak.getIdPermohonanPihak());
                }

//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
//                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
                logger.info("perbicaraanKehadiranList = " + perbicaraanKehadiranList.size());
                senaraiPM = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiNOTPM = notisPenerimaanService.getPerbicaraanKehadiranNOTPM(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiBantah = notisPenerimaanService.getPerbicaraanKehadiranBantah(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiTBantah = notisPenerimaanService.getPerbicaraanKehadiranTBantah(hakmilikPerbicaraan.getIdPerbicaraan());
                if (senaraiTBantah.isEmpty()) {
                    sizeTB = senaraiTBantah.size();
                    logger.info("bantah 1 size empty : " + senaraiTBantah.size());
                } else {

                    sizeTB = senaraiTBantah.size();
                    logger.info("bantah 1 size x empty : " + senaraiTBantah.size());

                }
                if (senaraiBantah.isEmpty()) {
                    size = senaraiBantah.size();
                    logger.info("bantah 2 size empty : " + senaraiBantah.size());
                } else {

                    size = senaraiBantah.size();
                    logger.info("bantah 2 size x empty : " + senaraiBantah.size());

                }
            } catch (Exception h) {
            }
        }


        if (display1.equalsIgnoreCase("true")) {
            logger.debug("display true::view");
            getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        } else if (display1.equalsIgnoreCase("false")) {
            logger.debug("display false::saman");
            getContext().getRequest().setAttribute("showSaman", Boolean.TRUE);
        } else if (display1.equalsIgnoreCase("equal")) {
            logger.debug("display equal::edit");
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() throws ParseException {

        logger.info("pihak Details");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        char c = idPermohonan.charAt(0);
        System.out.println("ID Permohonan " + idPermohonan);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("-------pihakDetails.idPihak-----------" + idPihak);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTSP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTPT")) {
            if (permohonan != null) {
                logger.info("permohonan pihakDetails != null");

//            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
//            if (permohonanPihak != null) {
                logger.info("permohonan pihak != null");
                hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

                long idMH = hakmilikPermohonan.getId();
                System.out.println("idMH " + idMH);

                penilaian = new Penilaian();
                itemNilaianTanah = BigDecimal.ZERO;
                itemNilaianBngn = BigDecimal.ZERO;
                itemNilaianLain = BigDecimal.ZERO;
                jumlah = BigDecimal.ZERO;
                if (hakmilikPermohonan != null) {
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                    hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
                    if (!hakbicaraList.isEmpty()) {
                        int index = hakbicaraList.size() - 1;
                        if (hakbicaraList.size() > 1) {
                            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
                        } else {
                            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
                        }
                    } else {

                        senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());

                    }
                    senaraiHadir = notisPenerimaanService.getbicarahadir(hakmilikPerbicaraan.getIdPerbicaraan());

                    System.out.println("senaraiPerbicaraanKehadiran" + senaraiPerbicaraanKehadiran.size());
                    for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                        status.add("Terima");
                        catatan.add("");
                        penilaianTanahList = notisPenerimaanService.getPenilaianByidHadir(senaraiPerbicaraanKehadiran.get(i).getIdKehadiran());
                        for (int y = 0; y < penilaianTanahList.size(); y++) {
                            itemNilaianTanah = penilaianTanahList.get(y).getAmaun();
                            jumlah = jumlah.add(itemNilaianTanah);

                        }

                    }
                    for (int i = 0; i < senaraiHadir.size(); i++) {
                        status1.add("Terima");
                        catatan1.add("");

                    }
                    for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                        perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                        penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                        if (perbicaraanKehadiran.getKeterangan() != null) {
                            status.set(i, perbicaraanKehadiran.getKeterangan());
                        }
                        if (perbicaraanKehadiran.getCatatan() != null) {
                            catatan.set(i, perbicaraanKehadiran.getCatatan());
                        }
                    }
                    for (int y = 0; y < senaraiHadir.size(); y++) {
                        perbicaraanKehadiran = senaraiHadir.get(y);
                        penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                        if (perbicaraanKehadiran.getKeterangan() != null) {
                            status1.set(y, perbicaraanKehadiran.getKeterangan());
                        }
                        if (perbicaraanKehadiran.getCatatan() != null) {
                            catatan1.set(y, perbicaraanKehadiran.getCatatan());
                        }
                    }
                }



                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);

                if (hakmilikPerbicaraan == null) {
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan.setInfoAudit(infoAudit);
                    hakmilikPerbicaraan.setBatalRizab('T');
                    hakmilikPerbicaraan.setKawasanPBT('T');
                    hakmilikPerbicaraan.setPelanPembangunan('T');
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }

                idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
                System.out.println("------pihakDetails.idPihak---------" + idPihak);
                System.out.println("------pihakDetails.hakmilik.getIdHakmilik()---------" + hakmilik.getIdHakmilik());

                // permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonanSblm, hakmilik.getIdHakmilik(), idPihak);
//            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                mohonPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                for (PermohonanPihak pp : mohonPihakList) {
                    logger.info("idPermohonanPihak : " + pp.getIdPermohonanPihak());

                    if (pp == null) {

                        addSimpleError("Tiada Data");
                    } else {
                        permohonanPihak = pp;
                        idPermohonanPihak = pp.getIdPermohonanPihak();
                        System.out.println("------pihakDetails.idPermohonanPihak---------" + idPermohonanPihak);
                        System.out.println("------pihakDetails.hakmilikPerbicaraan.getIdPerbicaraan()---------" + hakmilikPerbicaraan.getIdPerbicaraan());
                        perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                        for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                            pk.getIdKehadiran();
                            logger.info("DAPAT ID HADIR " + pk.getIdKehadiran());

//                            penilaianListHadir = notisPenerimaanService.getPenilaianByidHadir1(pk.getIdKehadiran());
//                            LOG.info("::PENILAIAN:: " + penilaian);
//
//                            if (penilaianListHadir != null) {
//                                LOG.info("::penilaianListHadir XNULL:: " + penilaianListHadir.size());
//
//                                BigDecimal am1 = BigDecimal.ZERO;
//                                for (Penilaian pl : notisPenerimaanService.sumPenilaian(pk.getIdKehadiran())) {
//                                    System.out.println(":: " + pl.getAmaun());
//                                    am = am1.add(pl.getAmaun());
//                                }
//
//                                System.out.println("am : " + am);
//                            }


                            permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(pp.getIdPermohonanPihak());

                            if (permohonanMahkamah != null) {
                                if (permohonanMahkamah.getTarikhTerimaPerintah() != null) {
                                    tarikhTerimaPerintah = sdf1.format(permohonanMahkamah.getTarikhTerimaPerintah());
                                }
                                if (permohonanMahkamah.getTarikhSaman() != null) {
                                    tarikhSaman = sdf1.format(permohonanMahkamah.getTarikhSaman());
                                }
                                if (permohonanMahkamah.getSamanPemulaBil() != null) {
                                    samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
                                }

                            }
                            borangQ = pendudukanSementaraMMKNService.findPampasanByIdMohon(idPermohonan);

                            if (borangQ != null) {
                                logger.info("borang Q x null");
                                logger.info("pampasan : " + borangQ.getTawaranPampasan());
                                if (borangQ.getTawaranPampasan() != null) {
                                    tawaranPampasan = borangQ.getTawaranPampasan();
                                }
                            }
                        }
                    }
                }
//            }

            }
        } else {
            if (permohonan != null) {
                logger.info("permohonan pihakDetails != null");
                idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();


//            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
//            if (permohonanPihak != null) {
                logger.info("permohonan pihak != null");
                hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);

                long idMH = hakmilikPermohonan.getId();
                System.out.println("idMH " + idMH);

                penilaian = new Penilaian();
                itemNilaianTanah = BigDecimal.ZERO;
                itemNilaianBngn = BigDecimal.ZERO;
                itemNilaianLain = BigDecimal.ZERO;
                jumlah = BigDecimal.ZERO;
                if (hakmilikPermohonan != null) {
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                    hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
                    if (!hakbicaraList.isEmpty()) {
                        int index = hakbicaraList.size() - 1;
                        if (hakbicaraList.size() > 1) {
                            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
                        } else {
                            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
                        }
                    } else {

                        senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());

                    }
                    senaraiHadir = notisPenerimaanService.getbicarahadir(hakmilikPerbicaraan.getIdPerbicaraan());

                    System.out.println("senaraiPerbicaraanKehadiran" + senaraiPerbicaraanKehadiran.size());
                    for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                        status.add("Terima");
                        catatan.add("");
                        penilaianTanahList = notisPenerimaanService.getPenilaianByidHadir(senaraiPerbicaraanKehadiran.get(i).getIdKehadiran());
                        for (int y = 0; y < penilaianTanahList.size(); y++) {
                            itemNilaianTanah = penilaianTanahList.get(y).getAmaun();
                            jumlah = jumlah.add(itemNilaianTanah);

                        }

                    }
                    for (int i = 0; i < senaraiHadir.size(); i++) {
                        status1.add("Terima");
                        catatan1.add("");

                    }
                    for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                        perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                        penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                        if (perbicaraanKehadiran.getKeterangan() != null) {
                            status.set(i, perbicaraanKehadiran.getKeterangan());
                        }
                        if (perbicaraanKehadiran.getCatatan() != null) {
                            catatan.set(i, perbicaraanKehadiran.getCatatan());
                        }
                    }
                    for (int y = 0; y < senaraiHadir.size(); y++) {
                        perbicaraanKehadiran = senaraiHadir.get(y);
                        penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                        if (perbicaraanKehadiran.getKeterangan() != null) {
                            status1.set(y, perbicaraanKehadiran.getKeterangan());
                        }
                        if (perbicaraanKehadiran.getCatatan() != null) {
                            catatan1.set(y, perbicaraanKehadiran.getCatatan());
                        }
                    }
                }



                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);

                if (hakmilikPerbicaraan == null) {
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan.setInfoAudit(infoAudit);
                    hakmilikPerbicaraan.setBatalRizab('T');
                    hakmilikPerbicaraan.setKawasanPBT('T');
                    hakmilikPerbicaraan.setPelanPembangunan('T');
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }

                System.out.println("------pihakDetails.idPihak---------" + idPihak);

                // permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonanSblm, hakmilik.getIdHakmilik(), idPihak);
//            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                mohonPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                for (PermohonanPihak pp : mohonPihakList) {
                    logger.info("idPermohonanPihak : " + pp.getIdPermohonanPihak());

                    if (pp == null) {

                        addSimpleError("Tiada Data");
                    } else {
                        permohonanPihak = pp;
                        idPermohonanPihak = pp.getIdPermohonanPihak();
                        System.out.println("------pihakDetails.idPermohonanPihak---------" + idPermohonanPihak);
                        System.out.println("------pihakDetails.hakmilikPerbicaraan.getIdPerbicaraan()---------" + hakmilikPerbicaraan.getIdPerbicaraan());
//                        perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                        perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
                        for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                            pk.getIdKehadiran();
                            logger.info("DAPAT ID HADIR " + pk.getIdKehadiran());

//                            penilaianListHadir = notisPenerimaanService.getPenilaianByidHadir1(pk.getIdKehadiran());
//                            LOG.info("::PENILAIAN:: " + penilaian);
//
//                            if (penilaianListHadir != null) {
//                                LOG.info("::penilaianListHadir XNULL:: " + penilaianListHadir.size());
//
//                                BigDecimal am1 = BigDecimal.ZERO;
//                                for (Penilaian pl : notisPenerimaanService.sumPenilaian(pk.getIdKehadiran())) {
//                                    System.out.println(":: " + pl.getAmaun());
//                                    am = am1.add(pl.getAmaun());
//                                }
//
//                                System.out.println("am : " + am);
//                            }


                            permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(pp.getIdPermohonanPihak());

                            if (permohonanMahkamah != null) {
                                if (permohonanMahkamah.getTarikhTerimaPerintah() != null) {
                                    tarikhTerimaPerintah = sdf1.format(permohonanMahkamah.getTarikhTerimaPerintah());
                                }
                                if (permohonanMahkamah.getTarikhSaman() != null) {
                                    tarikhSaman = sdf1.format(permohonanMahkamah.getTarikhSaman());
                                }
                                if (permohonanMahkamah.getSamanPemulaBil() != null) {
                                    samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
                                }

                            }
                            borangQ = pendudukanSementaraMMKNService.findPampasanByIdMohon(idPermohonan);

                            if (borangQ != null) {
                                logger.info("borang Q x null");
                                logger.info("pampasan : " + borangQ.getTawaranPampasan());
                                if (borangQ.getTawaranPampasan() != null) {
                                    tawaranPampasan = borangQ.getTawaranPampasan();
                                }
                            }
                        }
                    }
                }
//            }

            }
        }

        if (display1.equalsIgnoreCase("true")) {
            logger.debug("display true::view");
            getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        } else if (display1.equalsIgnoreCase("false")) {
            logger.debug("display false::saman");
            getContext().getRequest().setAttribute("showSaman", Boolean.TRUE);
        } else if (display1.equalsIgnoreCase("equal")) {
            logger.debug("display equal::edit");
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetailsnotpm() throws ParseException {

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
            logger.info("kod urusan == 831A");
            logger.info("pihak Details notpm");
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
            hakmilik = hakmilikDAO.findById(idHakmilik);
            getContext().getRequest().getSession().removeAttribute("permohonanPihak");

            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            char c = idPermohonan.charAt(0);
            System.out.println("ID Permohonan " + idPermohonan);
            String display1 = (String) getContext().getRequest().getParameter("display");
            logger.debug("display value is: " + display1);

            idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
//        long idMP = Long.parseLong(getContext().getRequest().getParameter("idMP"));

            System.out.println("-------pihakDetails.idPihak-----------" + idPihak);
            if (idPermohonan != null) {
                permohonan = permohonanDAO.findById(idPermohonan);
                cawangan = permohonan.getCawangan();
            }

            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
            long idMH = hakmilikPermohonan.getId();

            penilaian = new Penilaian();
            itemNilaianTanah = BigDecimal.ZERO;
            itemNilaianBngn = BigDecimal.ZERO;
            itemNilaianLain = BigDecimal.ZERO;
            jumlah = BigDecimal.ZERO;

            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
                hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
                if (!hakbicaraList.isEmpty()) {
                    int index = hakbicaraList.size() - 1;
                    if (hakbicaraList.size() > 1) {
                        senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
                    } else {
                        senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
                    }
                } else {

                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());

                }
                senaraiHadir = notisPenerimaanService.getbicarahadir(hakmilikPerbicaraan.getIdPerbicaraan());

                System.out.println("senaraiPerbicaraanKehadiran" + senaraiPerbicaraanKehadiran.size());
                for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                    status.add("Terima");
                    catatan.add("");
                    penilaianTanahList = notisPenerimaanService.getPenilaianByidHadir(senaraiPerbicaraanKehadiran.get(i).getIdKehadiran());
                    for (int y = 0; y < penilaianTanahList.size(); y++) {
                        itemNilaianTanah = penilaianTanahList.get(y).getAmaun();
                        jumlah = jumlah.add(itemNilaianTanah);

                    }

                }
                for (int i = 0; i < senaraiHadir.size(); i++) {
                    status1.add("Terima");
                    catatan1.add("");

                }
                for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                    perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                    penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                    if (perbicaraanKehadiran.getKeterangan() != null) {
                        status.set(i, perbicaraanKehadiran.getKeterangan());
                    }
                    if (perbicaraanKehadiran.getCatatan() != null) {
                        catatan.set(i, perbicaraanKehadiran.getCatatan());
                    }
                }
                for (int y = 0; y < senaraiHadir.size(); y++) {
                    perbicaraanKehadiran = senaraiHadir.get(y);
                    penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                    if (perbicaraanKehadiran.getKeterangan() != null) {
                        status1.set(y, perbicaraanKehadiran.getKeterangan());
                    }
                    if (perbicaraanKehadiran.getCatatan() != null) {
                        catatan1.set(y, perbicaraanKehadiran.getCatatan());
                    }
                }
            }

            System.out.println("-------pihakDetails.idMH-----------" + idMH);
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);

            senaraiPM = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiNOTPM = notisPenerimaanService.getPerbicaraanKehadiranNOTPM(hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiBantah = notisPenerimaanService.getPerbicaraanKehadiranBantah(hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiTBantah = notisPenerimaanService.getPerbicaraanKehadiranTBantah(hakmilikPerbicaraan.getIdPerbicaraan());

            if (hakmilikPerbicaraan == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraan.setInfoAudit(infoAudit);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');
                hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }

//        List<PermohonanPihak> permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), idPihak);

//        permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik2(idPihak, idHakmilik, idPermohonan, idMP);
            permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);


            if (permohonanPihak == null) {

                addSimpleError("Tiada Data");
            } else {

                idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
                logger.info("id mp : " + idPermohonanPihak);
                logger.info("id bicara : " + hakmilikPerbicaraan.getIdPerbicaraan());
                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranPM(hakmilikPerbicaraan.getIdPerbicaraan());
//                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                for (PerbicaraanKehadiran pkl : senaraiNOTPM) {
                    if (pkl != null) {
//                    logger.info("id hadir : " + perbicaraanKehadiran.getIdKehadiran());
                        permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(idPermohonanPihak);

                        if (permohonanMahkamah != null) {
                            if (permohonanMahkamah.getTarikhTerimaPerintah() != null) {
                                tarikhTerimaPerintah = sdf1.format(permohonanMahkamah.getTarikhTerimaPerintah());
                            }
                            if (permohonanMahkamah.getTarikhSaman() != null) {
                                tarikhSaman = sdf1.format(permohonanMahkamah.getTarikhSaman());
                            }
                            if (permohonanMahkamah.getSamanPemulaBil() != null) {
                                samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
                            }

                        }

                        penilaian = notisPenerimaanService.getPenilaianBngnByidNilaianP(pkl.getIdKehadiran());

                        borangQ = pendudukanSementaraMMKNService.findPampasanByIdMohon(idPermohonan);

                        if (borangQ != null) {
                            logger.info("borang Q x null");
                            logger.info("pampasan : " + borangQ.getTawaranPampasan());
                            if (borangQ.getTawaranPampasan() != null) {
                                tawaranPampasan = borangQ.getTawaranPampasan();
                            }
                        }
                    }
                }
                // }
            }

            if (display1.equalsIgnoreCase("true")) {
                logger.debug("display true::view");
                getContext().getRequest().setAttribute("showView", Boolean.TRUE);
            } else if (display1.equalsIgnoreCase("false")) {
                logger.debug("display false::saman");
                getContext().getRequest().setAttribute("showSaman", Boolean.TRUE);
            } else if (display1.equalsIgnoreCase("equal")) {
                logger.debug("display equal::edit");
                getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
            }
        }


        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetailsNOTPM", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        mohonPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        long idMH = hakmilikPermohonan.getId();
        System.out.println("idMH " + idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        for (PermohonanPihak pp : mohonPihakList) {
            if (pp != null) {

                permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(pp.getIdPermohonanPihak());
                if (permohonanMahkamah == null) {
                    logger.info("LALU permohonan mahkamah == null");
                    permohonanMahkamah = new PermohonanMahkamah();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohonanMahkamah.setInfoAudit(infoAudit);
                }
//                permohonanMahkamah.setIdMm(idMm);
                permohonanMahkamah.setPermohonanPihak(pp);
                String tarikhIkrar1 = getContext().getRequest().getParameter("tarikhTerimaPerintah");
                if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhTerimaPerintah"))) {
                    permohonanMahkamah.setTarikhTerimaPerintah(sdf1.parse(tarikhIkrar1));
                }
                String tarikhIkrar2 = getContext().getRequest().getParameter("tarikhSaman");
                if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhSaman"))) {
                    permohonanMahkamah.setTarikhSaman(sdf1.parse(tarikhIkrar2));
                }

                pendudukanSementaraMMKNService.simpanPermohonanMahkamah(permohonanMahkamah);

                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran == null) {
                    logger.info("perbicaraanKehadiran == null");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPihak(pp);
                    pendudukanSementaraMMKNService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                }

                rehydrate();
                pihakDetails();

            }
        }

//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idPermohonanPihak", idPermohonanPihak);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
    }

    public Resolution simpanNOTPM() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        mohonPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        long idMH = hakmilikPermohonan.getId();
        System.out.println("idMH " + idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        for (PermohonanPihak pp : mohonPihakList) {
            if (pp != null) {

                permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(pp.getIdPermohonanPihak());
                if (permohonanMahkamah == null) {
                    logger.info("LALU permohonan mahkamah == null");
                    permohonanMahkamah = new PermohonanMahkamah();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    permohonanMahkamah.setInfoAudit(infoAudit);
                }
//                permohonanMahkamah.setIdMm(idMm);
                permohonanMahkamah.setPermohonanPihak(pp);
                String tarikhIkrar1 = getContext().getRequest().getParameter("tarikhTerimaPerintah");
                if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhTerimaPerintah"))) {
                    permohonanMahkamah.setTarikhTerimaPerintah(sdf1.parse(tarikhIkrar1));
                }
                String tarikhIkrar2 = getContext().getRequest().getParameter("tarikhSaman");
                if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhSaman"))) {
                    permohonanMahkamah.setTarikhSaman(sdf1.parse(tarikhIkrar2));
                }

                pendudukanSementaraMMKNService.simpanPermohonanMahkamah(permohonanMahkamah);

                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran == null) {
                    logger.info("perbicaraanKehadiran == null");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPihak(pp);
                    pendudukanSementaraMMKNService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
                }

                rehydrate();
                pihakDetailsnotpm();

            }
        }

//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idPermohonanPihak", idPermohonanPihak);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/SamanDalamKamar.jsp").addParameter("tab", "true");
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

    public String getLokasiSaman() {
        return lokasiSaman;
    }

    public void setLokasiSaman(String lokasiSaman) {
        this.lokasiSaman = lokasiSaman;
    }

    public String getNamaPenolongKananPendaftar() {
        return namaPenolongKananPendaftar;
    }

    public void setNamaPenolongKananPendaftar(String namaPenolongKananPendaftar) {
        this.namaPenolongKananPendaftar = namaPenolongKananPendaftar;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getSamanPemulaBil() {
        return samanPemulaBil;
    }

    public void setSamanPemulaBil(String samanPemulaBil) {
        this.samanPemulaBil = samanPemulaBil;
    }

    public String getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(String tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }

//    public Date getTarikhIkrar() {
//        return tarikhIkrar;
//    }
//
//    public void setTarikhIkrar(Date tarikhIkrar) {
//        this.tarikhIkrar = tarikhIkrar;
//    }
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

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getTarikhSaman() {
        return tarikhSaman;
    }

    public void setTarikhSaman(String tarikhSaman) {
        this.tarikhSaman = tarikhSaman;
    }

    public String getTarikhTerimaPerintah() {
        return tarikhTerimaPerintah;
    }

    public void setTarikhTerimaPerintah(String tarikhTerimaPerintah) {
        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
    }

//    public Date getTarikhTerimaPerintah() {
//        return tarikhTerimaPerintah;
//    }
//
//    public void setTarikhTerimaPerintah(Date tarikhTerimaPerintah) {
//        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
//    }
    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public BigDecimal getAmnt() {
        return amnt;
    }

    public void setAmnt(BigDecimal amnt) {
        this.amnt = amnt;
    }

    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }

    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public List<PerbicaraanKehadiran> getSenaraiHadir() {
        return senaraiHadir;
    }

    public void setSenaraiHadir(List<PerbicaraanKehadiran> senaraiHadir) {
        this.senaraiHadir = senaraiHadir;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
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

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public List<String> getCatatan() {
        return catatan;
    }

    public void setCatatan(List<String> catatan) {
        this.catatan = catatan;
    }

    public List<String> getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(List<String> catatan1) {
        this.catatan1 = catatan1;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getStatus1() {
        return status1;
    }

    public void setStatus1(List<String> status1) {
        this.status1 = status1;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public BorangQ getBorangQ() {
        return borangQ;
    }

    public void setBorangQ(BorangQ borangQ) {
        this.borangQ = borangQ;
    }

    public BigDecimal getTawaranPampasan() {
        return tawaranPampasan;
    }

    public void setTawaranPampasan(BigDecimal tawaranPampasan) {
        this.tawaranPampasan = tawaranPampasan;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public List<PerbicaraanKehadiran> getSenaraiBantah() {
        return senaraiBantah;
    }

    public void setSenaraiBantah(List<PerbicaraanKehadiran> senaraiBantah) {
        this.senaraiBantah = senaraiBantah;
    }

    public List<PerbicaraanKehadiran> getSenaraiNOTPM() {
        return senaraiNOTPM;
    }

    public void setSenaraiNOTPM(List<PerbicaraanKehadiran> senaraiNOTPM) {
        this.senaraiNOTPM = senaraiNOTPM;
    }

    public List<PerbicaraanKehadiran> getSenaraiPM() {
        return senaraiPM;
    }

    public void setSenaraiPM(List<PerbicaraanKehadiran> senaraiPM) {
        this.senaraiPM = senaraiPM;
    }

    public List<PerbicaraanKehadiran> getSenaraiTBantah() {
        return senaraiTBantah;
    }

    public void setSenaraiTBantah(List<PerbicaraanKehadiran> senaraiTBantah) {
        this.senaraiTBantah = senaraiTBantah;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSizeTB() {
        return sizeTB;
    }

    public void setSizeTB(int sizeTB) {
        this.sizeTB = sizeTB;
    }
}
