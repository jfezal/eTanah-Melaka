/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Dokumen;
import etanah.model.Notis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanMahkamah;
import etanah.model.Pihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.common.NotisService;
import etanah.service.daftar.PembetulanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
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
 * @author Hazirah
 */
@UrlBinding("/pengambilan/perintahMahkamah")
public class PerintahMahkamahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    private FasaPermohonanService fasaPermohonanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    private NotisService notisService;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    private PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
//    @Inject
//    private HakmilikPermohonanService hakmilikPermohonanService;
    private Hakmilik hakmilik;
    private PermohonanKertas permohonanKertas;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private String pihak;
    private String idPermohonan;
    private String idPermohonanSblm;
    private String idHakmilik;
//    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private String namaPenolongKananPendaftar;
    private String tarikhTerimaPerintah;
    private String alasanBantah;
    private String ringkasanBantah;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Penilaian penilaian;
    private FasaPermohonan kedesakan;
    private Notis borangNotis;
    private Dokumen idDokumenH;
    private Dokumen idDokumenI;
    private String kodKedesakan;
    private String tanahItem;
    private String bngnItem;
    private String lainItem;
    private long dokumenH;
    private long dokumenI;
    private long idPihak;
    private Date tarikhTerimaH;
    private Date tarikhTerimaI;
    private String tempohH;
    private String tempohI;
    private int tempohImonth;
    private int tempohIyear;
    private BigDecimal tanahAmaun;
    private BigDecimal bngnAmaun;
    private BigDecimal lainAmaun;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private BigDecimal jumlah;
    private BigDecimal amaun;
    private BigDecimal am;
    private String sum;
    private Date calendar;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private List<Penilaian> penilaianListHadir;
    private List<Notis> borangNotisList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PerbicaraanKehadiran> senaraiHadir;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Penilaian> penilaianList;
    private List<String> status = new ArrayList<String>();
    private List<String> catatan = new ArrayList<String>();
    private List<String> status1 = new ArrayList<String>();
    private List<String> catatan1 = new ArrayList<String>();
    private List<Pengguna> senaraiPengguna;
    private long idPermohonanPihak;
    private static final Logger LOG = Logger.getLogger(PerintahMahkamahActionBean.class);
//    private List<PerbicaraanKehadiran> hakmilikPerbicaraanList;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        return new JSP("pengambilan/Deraf_Perintah_Mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        System.out.println("masuk showForm2");

        getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        return new JSP("pengambilan/Deraf_Perintah_Mahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiPengguna = notisPenerimaanService.getPenggunaMahkamah();

        if (permohonan != null) {
            LOG.info("LALU permohonan != null");
            if (permohonan.getPermohonanSebelum() != null) {
                Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                LOG.info("permohonanSblm = " + permohonanSblm);

                if (permohonanSblm != null) {
                    LOG.info("LALU permohonanSblm != null");
//                    if (permohonanSblm.getPermohonanSebelum() != null) {
//                        LOG.info("permohonanSblm.getPermohonanSebelum() != null");
                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
                        hakmilikPermohonanList = notisPenerimaanService.getMHMahkamah(idPermohonanSblm);
                        LOG.info("Hakmilik Permohonan List = " + hakmilikPermohonanList.size());
//                    }
                }

//                if (permohonanSblm != null) {
//                    LOG.info("LALU permohonanSblm != null");
//                    if (permohonanSblm.getPermohonanSebelum() != null) {
//                        LOG.info("permohonanSblm.getPermohonanSebelum() != null");
//                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//                        hakmilikPermohonanList = notisPenerimaanService.getMHMahkamah(idPermohonanSblm);
//                        LOG.info("Hakmilik Permohonan List = " + hakmilikPermohonanList.size());
//                    }
//                }

            }
        }
    }

    public Resolution hakmilikDetails() {
        LOG.info("hakmilikDetails");
//        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        LOG.info("idHakmilik " + idHakmilik);
        String display1 = (String) getContext().getRequest().getParameter("display");
        LOG.debug("display value is: " + display1);

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        try {
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            LOG.info("idPermohonan " + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                LOG.info("permohonan hakmilikDetails != null");
                Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());

                if (permohonanSblm != null) {
                    if (permohonanSblm.getPermohonanSebelum() != null) {
                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

                        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonanSblm, idHakmilik);

                        long idMH = hakmilikPermohonan.getId();
                        System.out.println("idMH " + idMH);

                        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
                        System.out.println("-------hakmilikDetails.hakmilikPerbicaraan-----------" + hakmilikPerbicaraan);
                        LOG.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());

                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonanSblm, hakmilik.getIdHakmilik());
                        idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
                        LOG.info("idPermohonanPihak : " + permohonanPihak.getIdPermohonanPihak());
                    }
                }
            }

//            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
//            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();

//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            perbicaraanKehadiranList = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
            LOG.info("perbicaraanKehadiranList = " + perbicaraanKehadiranList.size());
            for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                pk.getIdKehadiran();
                LOG.info("DAPAT ID HADIR " + pk.getIdKehadiran());
            }
        } catch (Exception h) {
        }

        if (display1.equalsIgnoreCase("true")) {
            LOG.debug("display true::view");
            getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        } else {
            LOG.debug("display true::edit");
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/Deraf_Perintah_Mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() throws ParseException {

        LOG.info("pihak Details");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        String display1 = (String) getContext().getRequest().getParameter("display");
        LOG.debug("display value is: " + display1);

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

        if (permohonan != null) {
            LOG.info("permohonan pihakDetails != null");
            Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());

            if (permohonanSblm != null) {
//                if (permohonanSblm.getPermohonanSebelum() != null) {
                    idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
                    LOG.info("idPermohonanSblm : " + idPermohonanSblm);

                    hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonanSblm, idHakmilik);

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
                    System.out.println("-------hakmilikDetails.hakmilikPerbicaraan-----------" + hakmilikPerbicaraan);
                    LOG.info("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());

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
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                    LOG.info("idPermohonanPihak : " + permohonanPihak.getIdPermohonanPihak());

                    if (permohonanPihak == null) {

                        addSimpleError("Tiada Data");
                    } else {

                        idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
                        System.out.println("------pihakDetails.idPermohonanPihak---------" + idPermohonanPihak);
                        System.out.println("------pihakDetails.hakmilikPerbicaraan.getIdPerbicaraan()---------" + hakmilikPerbicaraan.getIdPerbicaraan());
                        perbicaraanKehadiranList = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
                        for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                            pk.getIdKehadiran();
                            LOG.info("DAPAT ID HADIR " + pk.getIdKehadiran());

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
                            permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());

                            if (permohonanMahkamah != null) {
                                namaPenolongKananPendaftar = permohonanMahkamah.getNamaPenolongKananPendaftar();
                                if (namaPenolongKananPendaftar != null) {
                                    LOG.info("namaPenolongKananPendaftar != null");
                                    permohonanMahkamah.getNamaPenolongKananPendaftar();
                                }

                                alasanBantah = permohonanMahkamah.getAlasanBantah();
                                if (alasanBantah != null) {
                                    LOG.info("alasanBantah != null");
                                    permohonanMahkamah.getAlasanBantah();
                                }

                                ringkasanBantah = permohonanMahkamah.getRingkasanBantah();
                                if (ringkasanBantah != null) {
                                    LOG.info("ringkasanBantah != null");
                                    permohonanMahkamah.getRingkasanBantah();
                                }
                                
                                if (permohonanMahkamah.getTarikhTerimaPerintah() != null) {
                                    tarikhTerimaPerintah = sdf1.format(permohonanMahkamah.getTarikhTerimaPerintah());
                                }
                            }
                        }
                    }
//                }
            }
        }

        if (display1.equalsIgnoreCase("true")) {
            LOG.debug("display true::view");
            getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        } else {
            LOG.debug("display true::edit");
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/Deraf_Perintah_Mahkamah.jsp").addParameter("tab", "true");
    }

//    public Resolution refreshpage() {
//        rehydrate();
//        return new RedirectResolution(RekodPenerimaanBayaranPampasan31aActionBean.class, "locate");
//    }
    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        long idMH = hakmilikPermohonan.getId();
        System.out.println("idMH " + idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);

        if (permohonanPihak != null) {

            permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah == null) {
                LOG.info("LALU permohonan mahkamah == null");
                permohonanMahkamah = new PermohonanMahkamah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }
//                permohonanMahkamah.setIdMm(idMm);
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setNamaPenolongKananPendaftar(namaPenolongKananPendaftar);
            permohonanMahkamah.setAlasanBantah(alasanBantah);
            permohonanMahkamah.setRingkasanBantah(ringkasanBantah);
            String tarikhIkrar1 = getContext().getRequest().getParameter("tarikhTerimaPerintah");
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhTerimaPerintah"))) {
                permohonanMahkamah.setTarikhTerimaPerintah(sdf1.parse(tarikhIkrar1));
            }

            pendudukanSementaraMMKNService.simpanPermohonanMahkamah(permohonanMahkamah);

            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                LOG.info("perbicaraanKehadiran == null");
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPihak(permohonanPihak);
                pendudukanSementaraMMKNService.simpanPerbicaraanKehadiran(perbicaraanKehadiran);
            }

            rehydrate();
            pihakDetails();

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Deraf_Perintah_Mahkamah.jsp").addParameter("tab", "true");
//        return new RedirectResolution(AffidavitActionBean.class, "locate");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
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

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public BigDecimal getTanahAmaun() {
        return tanahAmaun;
    }

    public void setTanahAmaun(BigDecimal tanahAmaun) {
        this.tanahAmaun = tanahAmaun;
    }

    public String getTanahItem() {
        return tanahItem;
    }

    public void setTanahItem(String tanahItem) {
        this.tanahItem = tanahItem;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public BigDecimal getBngnAmaun() {
        return bngnAmaun;
    }

    public void setBngnAmaun(BigDecimal bngnAmaun) {
        this.bngnAmaun = bngnAmaun;
    }

    public String getBngnItem() {
        return bngnItem;
    }

    public void setBngnItem(String bngnItem) {
        this.bngnItem = bngnItem;
    }

    public BigDecimal getLainAmaun() {
        return lainAmaun;
    }

    public void setLainAmaun(BigDecimal lainAmaun) {
        this.lainAmaun = lainAmaun;
    }

    public String getLainItem() {
        return lainItem;
    }

    public void setLainItem(String lainItem) {
        this.lainItem = lainItem;
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

    public long getDokumenI() {
        return dokumenI;
    }

    public void setDokumenI(long dokumenI) {
        this.dokumenI = dokumenI;
    }

    public Date getTarikhTerimaH() {
        return tarikhTerimaH;
    }

    public void setTarikhTerimaH(Date tarikhTerimaH) {
        this.tarikhTerimaH = tarikhTerimaH;
    }

    public Date getTarikhTerimaI() {
        return tarikhTerimaI;
    }

    public void setTarikhTerimaI(Date tarikhTerimaI) {
        this.tarikhTerimaI = tarikhTerimaI;
    }

    public Notis getBorangNotis() {
        return borangNotis;
    }

    public int getTempohImonth() {
        return tempohImonth;
    }

    public void setTempohImonth(int tempohImonth) {
        this.tempohImonth = tempohImonth;

    }

    public int getTempohIyear() {
        return tempohIyear;
    }

    public void setTempohIyear(int tempohIyear) {
        this.tempohIyear = tempohIyear;

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

    /**
     * @return the calendar
     */
    public Date getCalendar() {
        return calendar;
    }

    /**
     * @param calendar the calendar to set
     */
    public void setCalendar(Date calendar) {
        this.calendar = calendar;
    }

    /**
     * @return the TempohH
     */
    public String getTempohH() {
        return tempohH;
    }

    /**
     * @param TempohH the TempohH to set
     */
    public void setTempohH(String tempohH) {
        this.tempohH = tempohH;
    }

    /**
     * @return the tempohI
     */
    public String getTempohI() {
        return tempohI;
    }

    /**
     * @param tempohI the tempohI to set
     */
    public void setTempohI(String tempohI) {
        this.tempohI = tempohI;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public List<Penilaian> getPenilaianListHadir() {
        return penilaianListHadir;
    }

    public void setPenilaianListHadir(List<Penilaian> penilaianListHadir) {
        this.penilaianListHadir = penilaianListHadir;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public BigDecimal getAm() {
        return am;
    }

    public void setAm(BigDecimal am) {
        this.am = am;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getIdPermohonanSblm() {
        return idPermohonanSblm;
    }

    public void setIdPermohonanSblm(String idPermohonanSblm) {
        this.idPermohonanSblm = idPermohonanSblm;
    }

    public String getNamaPenolongKananPendaftar() {
        return namaPenolongKananPendaftar;
    }

    public void setNamaPenolongKananPendaftar(String namaPenolongKananPendaftar) {
        this.namaPenolongKananPendaftar = namaPenolongKananPendaftar;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public String getTarikhTerimaPerintah() {
        return tarikhTerimaPerintah;
    }

    public void setTarikhTerimaPerintah(String tarikhTerimaPerintah) {
        this.tarikhTerimaPerintah = tarikhTerimaPerintah;
    }

    public String getAlasanBantah() {
        return alasanBantah;
    }

    public void setAlasanBantah(String alasanBantah) {
        this.alasanBantah = alasanBantah;
    }

    public String getPihak() {
        return pihak;
    }

    public void setPihak(String pihak) {
        this.pihak = pihak;
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

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
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

    public List<String> getStatus1() {
        return status1;
    }

    public void setStatus1(List<String> status1) {
        this.status1 = status1;
    }

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public String getRingkasanBantah() {
        return ringkasanBantah;
    }

    public void setRingkasanBantah(String ringkasanBantah) {
        this.ringkasanBantah = ringkasanBantah;
    }
    
}
