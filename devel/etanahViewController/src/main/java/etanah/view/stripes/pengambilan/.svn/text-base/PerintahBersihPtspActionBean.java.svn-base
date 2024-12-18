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
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
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
@UrlBinding("/pengambilan/PerintahBersihPtsp")
public class PerintahBersihPtspActionBean extends AbleActionBean {

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
    private PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private static final Logger logger = Logger.getLogger(DerafPerintahPtspActionBean.class);
    private String idPermohonan;
    private String idHakmilik;
    private long idPihak;
    private long idPermohonanPihak;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
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
        return new JSP("pengambilan/PerintahBersih_ptsp.jsp").addParameter("tab", "true");
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

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

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

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/PerintahBersih_ptsp.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() throws ParseException {

        logger.info("pihak Details");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

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
            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
            logger.info("idPermohonanPihak : " + permohonanPihak.getIdPermohonanPihak());

            if (permohonanPihak == null) {

                addSimpleError("Tiada Data");
            } else {

                idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
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


                    permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());

                    if (permohonanMahkamah != null) {
                        namaPenolongKananPendaftar = permohonanMahkamah.getNamaPenolongKananPendaftar();
                        if (namaPenolongKananPendaftar != null) {
                            logger.info("namaPenolongKananPendaftar != null");
                            permohonanMahkamah.getNamaPenolongKananPendaftar();
                        }
                        if (permohonanMahkamah.getSamanPemulaBil() != null) {
                            samanPemulaBil = permohonanMahkamah.getSamanPemulaBil();
                        }

//                                alasanBantah = permohonanMahkamah.getAlasanBantah();
//                                if (alasanBantah != null) {
//                                    LOG.info("alasanBantah != null");
//                                    permohonanMahkamah.getAlasanBantah();
//                                }
//
//                                ringkasanBantah = permohonanMahkamah.getRingkasanBantah();
//                                if (ringkasanBantah != null) {
//                                    LOG.info("ringkasanBantah != null");
//                                    permohonanMahkamah.getRingkasanBantah();
//                                }

                        if (permohonanMahkamah.getTarikhTerimaPerintah() != null) {
                            tarikhTerimaPerintah = sdf1.format(permohonanMahkamah.getTarikhTerimaPerintah());
                        }
                        if (permohonanMahkamah.getTarikhSaman() != null) {
                            tarikhSaman = sdf1.format(permohonanMahkamah.getTarikhSaman());
                        }
                        if (permohonanMahkamah.getTarikhIkrar() != null) {
                            tarikhIkrar = sdf1.format(permohonanMahkamah.getTarikhIkrar());
                        }
                    }
                }
            }
//            }

        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/PerintahBersih_ptsp.jsp").addParameter("tab", "true");
    }

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
                logger.info("LALU permohonan mahkamah == null");
                permohonanMahkamah = new PermohonanMahkamah();
                InfoAudit infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }
//                permohonanMahkamah.setIdMm(idMm);
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setNamaPenolongKananPendaftar(namaPenolongKananPendaftar);
            permohonanMahkamah.setSamanPemulaBil(samanPemulaBil);
            String tarikhIkrar1 = getContext().getRequest().getParameter("tarikhTerimaPerintah");
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhTerimaPerintah"))) {
                permohonanMahkamah.setTarikhTerimaPerintah(sdf1.parse(tarikhIkrar1));
            }
            String tarikhIkrar2 = getContext().getRequest().getParameter("tarikhSaman");
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhSaman"))) {
                permohonanMahkamah.setTarikhSaman(sdf1.parse(tarikhIkrar2));
            }
            String tarikhIkrar3 = getContext().getRequest().getParameter("tarikhIkrar");
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("tarikhIkrar"))) {
                permohonanMahkamah.setTarikhIkrar(sdf1.parse(tarikhIkrar3));
            }

            pendudukanSementaraMMKNService.simpanPermohonanMahkamah(permohonanMahkamah);

            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                logger.info("perbicaraanKehadiran == null");
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
//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idPermohonanPihak", idPermohonanPihak);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/PerintahBersih_ptsp.jsp").addParameter("tab", "true");
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
}
