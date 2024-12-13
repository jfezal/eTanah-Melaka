/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.*;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PenggunaDAO;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import java.text.ParseException;
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.DrafKertasMMKNService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.daftar.PembetulanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author massita
 * @modified hazirah
 */
@UrlBinding("/pengambilan/affidavitMahkamah")
public class AffidavitMahkamahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AffidavitMahkamahActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idPermohonanSblm;
    private KodCawangan cawangan;
    private PermohonanKertas permohonanKertas;
    private String kertasBil;
    private String kertasTahun;
    private PermohonanKertasKandungan masaObj;
    private String tempat;
    private String tarikhmesyuarat;
    private String samanPemulaBil;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanKertasKandungan perakuan;
    private PermohonanKertasKandungan perakuan1;
    private PermohonanKertasKandungan diIkrarOleh;
    private PermohonanKertasKandungan tarikhIkrar;
    private PermohonanKertasKandungan samanPemulaBilObj;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan headingObj2;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan pesuruhJayaSumpah;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanMahkamah permohonanMahkamah;
    private PermohonanPihak permohonanPihak;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
//    private int bil = 0;
    private String kandungan;
    private String kandungan1;
    private String kandungan2;
    private String kandungan3;
    private String idKandungan;
    private String noRujukan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<Pengguna> senaraiPengguna;
    private String tajuk = "AFFIDAVIT";
    private Hakmilik hakmilik;
    private String tujuan;
    private String keputusan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private int count = 0;
    private int count5 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List senaraiSyor[] = new ArrayList[5];
    private List senaraiSyorPentadbir[] = new ArrayList[5];
    private List senaraiPentadbir[] = new ArrayList[10];
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    private String idHakmilik;
    private String penyediaSidang;
    private String penyediaSidang1;
    private String wakilSidang;
    private long idPihak;
    private long idPermohonanPihak;
    private long idMm;
    private String show;
    private String kod;
    String namaProjek;
    private boolean display = false;
    private boolean flag = false;
    private static final Logger LOG = Logger.getLogger(AffidavitMahkamahActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        System.out.println("MASUK FORM 2");

        getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        senaraiPengguna = notisPenerimaanService.getPenggunaMahkamah();

        if (permohonan != null) {
            logger.info("LALU permohonan != null");
            if (permohonan.getPermohonanSebelum() != null) {
                Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                logger.info("permohonanSblm = " + permohonanSblm);

                if (permohonanSblm != null) {
                    logger.info("LALU permohonanSblm != null");
//                    if (permohonanSblm.getPermohonanSebelum() != null) {
//                        logger.info("permohonanSblm.getPermohonanSebelum() != null");
                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
                        hakmilikPermohonanList = notisPenerimaanService.getMHMahkamah(idPermohonanSblm);
                        logger.info("Hakmilik Permohonan List = " + hakmilikPermohonanList.size());
//                    }
                }

//                if (permohonanSblm != null) {
//                    logger.info("LALU permohonanSblm != null");
//                    if (permohonanSblm.getPermohonanSebelum() != null) {
//                        logger.info("permohonanSblm.getPermohonanSebelum() != null");
//                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//                        hakmilikPermohonanList = notisPenerimaanService.getMHMahkamah(idPermohonanSblm);
//                        logger.info("Hakmilik Permohonan List = " + hakmilikPermohonanList.size());
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
        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        logger.info("idPermohonanSblm = " + idPermohonanSblm);
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);

        hakmilik = hakmilikDAO.findById(idHakmilik);
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

//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            perbicaraanKehadiranList = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
            LOG.info("perbicaraanKehadiranList = " + perbicaraanKehadiranList.size());
            for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                pk.getIdKehadiran();
                LOG.info("DAPAT ID HADIR " + pk.getIdKehadiran());
            }
//            System.out.println("ID hadir Perbicaraan " + perbicaraanKehadiran.getIdKehadiran());

        } catch (Exception h) {
        }

        if (display1.equalsIgnoreCase("true")) {
            logger.debug("display true::view");
            getContext().getRequest().setAttribute("showView", Boolean.TRUE);
        } else {
            logger.debug("display true::edit");
            getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() throws ParseException {

        LOG.info("pihak Details");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        char c = idPermohonan.charAt(0);
        System.out.println("ID Permohonan " + idPermohonan);
//        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("-------pihakDetails.idPihak-----------" + idPihak);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        if (permohonan != null) {
            logger.info("LALU permohonan != null");
            if (permohonan.getPermohonanSebelum() != null) {
                Permohonan permohonanSblm = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                logger.info("permohonanSblm = " + permohonanSblm);

                if (permohonanSblm != null) {
                    logger.info("LALU permohonanSblm != null");
                    if (permohonanSblm != null) {
                        logger.info("permohonanSblm.getPermohonanSebelum() != null");
                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

                        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonanSblm, idHakmilik);
                        long idMH = hakmilikPermohonan.getId();
                        System.out.println("-------pihakDetails.idMH-----------" + idMH);
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

                        if (permohonanPihak == null) {

                            addSimpleError("Tiada Data");
                        } else {

                            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
                            System.out.println("------pihakDetails.idPermohonanPihak---------" + idPermohonanPihak);
                            System.out.println("------pihakDetails.hakmilikPerbicaraan.getIdPerbicaraan()---------" + hakmilikPerbicaraan.getIdPerbicaraan());
//            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
                            perbicaraanKehadiranList = pengambilanService.getPerbicaraanKehadiranByKetarangan(hakmilikPerbicaraan.getIdPerbicaraan(), "Mahkamah");
                            System.out.println("------pihakDetails.perbicaraanKehadiranList---------" + perbicaraanKehadiranList.size());

                            for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                                logger.info("MASUK for perbicaraanKehadiranList");
                                pk.getIdKehadiran();
                                LOG.info("DAPAT ID HADIR " + pk.getIdKehadiran());

                                permohonanKertas = pendudukanSementaraMMKNService.findAffidavit(idPermohonan, idPermohonanPihak, tajuk);
                                logger.info("permohonanKertas = " + permohonanKertas);

                                if (permohonanKertas == null) {
                                    System.out.println("::permohonan kertas == null::");

                                    permohonanKertas = new PermohonanKertas();
                                    InfoAudit infoAudit = new InfoAudit();
                                    infoAudit.setDimasukOleh(pengguna);
                                    infoAudit.setTarikhMasuk(new java.util.Date());
                                    permohonanKertas.setNomborRujukan(noRujukan);
                                    permohonanKertas.setPermohonan(permohonan);
                                    permohonanKertas.setPermohonanPihak(permohonanPihak);
                                    permohonanKertas.setInfoAudit(infoAudit);
                                    permohonanKertas.setTajuk(tajuk);
                                    permohonanKertas.setCawangan(permohonan.getCawangan());
                                    KodDokumen doc = new KodDokumen();
                                    doc.setKod("ADF");
                                    permohonanKertas.setKodDokumen(doc);
                                    permohonanKertas = notisPenerimaanService.saveOrUpdatePermohonanKertas(permohonanKertas);
                                }

                                System.out.println("::permohonan kertas X null::");

                                tajuk = permohonanKertas.getTajuk();
                                logger.info("tajuk = " + tajuk);
                                penyediaSidang1 = permohonanKertas.getPenyediaSidang();
                                logger.info("penyediaSidang : " + penyediaSidang1);

                                permohonanKertas = permohonanSupayaBantahanService.findPermohonanKertasByidMP(permohonanPihak.getIdPermohonanPihak());
                                penyediaSidang1 = permohonanKertas.getPenyediaSidang();
                                if (penyediaSidang1 != null) {
                                    logger.info("LALU penyediaSidang != null");
                                    permohonanKertas.getPenyediaSidang();
                                }

                                wakilSidang = permohonanKertas.getWakilSidang();
                                if (wakilSidang != null) {
                                    logger.info("LALU wakilSidang != null");
                                    permohonanKertas.getWakilSidang();
                                }

                                perakuan1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
                                senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 1, subtajuk);
                                logger.info("senaraiKertasKandungan1 = " + senaraiKertasKandungan1.size());
                                permohonanMahkamah = pendudukanSementaraMMKNService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());


                                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
                                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
                                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 1);
                                logger.info("LALU kandList5 = " + kandList5.size());
                                if (kandList5 != null && !kandList5.isEmpty()) {
                                    logger.info("LALU kandList5 != null && !kandList5.isEmpty() ");
                                    maxKertasKand5 = kandList5.get(0);
                                    logger.info("LALU maxKertasKand5 = " + maxKertasKand5);
                                    if (maxKertasKand5 != null) {
                                        logger.info("LALU maxKertasKand5 != null ");
                                        String subtajukP = maxKertasKand5.getSubtajuk();
                                        logger.info("LALU subtajukP = " + subtajukP);
                                        String str = subtajukP.substring(2, 3);
                                        int tableCount = Integer.parseInt(str);
                                        count5 = tableCount;
                                        for (int k = 1; k <= tableCount; k++) {
//                            for(int k=1;k<=kandList5.size();k++){
                                            senaraiPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                            String subtajuk1 = "1." + k;
                                            senaraiPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 1, subtajuk1);
                                        }
                                    }
                                }
                                if (display1.equalsIgnoreCase("true")) {
                                    logger.debug("display true::view");
                                    getContext().getRequest().setAttribute("showView", Boolean.TRUE);
                                } else {
                                    logger.debug("display true::edit");
                                    getContext().getRequest().setAttribute("showEdit", Boolean.TRUE);
                                }
                            }
                        }
                    }
                }
            }
        }

        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);

        LOG.info("id mohon : " + idPermohonan);
        LOG.info("Permohonan : " + permohonan.getIdPermohonan());
        if (permohonanPihak != null) {
            permohonanKertas = permohonanSupayaBantahanService.findPermohonanKertasByidMP2(permohonanPihak.getIdPermohonanPihak(), idPermohonan);
            InfoAudit iaPermohonan = new InfoAudit();
            if (permohonanKertas == null) {
                permohonanKertas = new PermohonanKertas();
                iaPermohonan.setDimasukOleh(peng);
                iaPermohonan.setTarikhMasuk(new java.util.Date());
            } else {
                iaPermohonan = permohonanKertas.getInfoAudit();
                iaPermohonan.setDiKemaskiniOleh(peng);
                iaPermohonan.setTarikhKemaskini(new java.util.Date());
            }

            penyediaSidang1 = getContext().getRequest().getParameter("permohonanKertas.penyediaSidang");
            logger.info("penyediaSidang1 : " + penyediaSidang1);
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("permohonanKertas.penyediaSidang"))) {
                logger.info("penyediaSidang1 : " + penyediaSidang1);
                permohonanKertas.setPenyediaSidang(penyediaSidang1);
            }
            wakilSidang = getContext().getRequest().getParameter("wakilSidang");
            permohonanKertas.setWakilSidang(wakilSidang);
            permohonanKertas.setNomborRujukan(noRujukan);
            permohonanKertas.setTajuk(tajuk);
            permohonanKertas.setPermohonanPihak(permohonanPihak);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setHakmilik(hakmilik);
            permohonanKertas.setInfoAudit(iaPermohonan);
            permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


            perakuan1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
            if (perakuan1 == null) {
                perakuan1 = new PermohonanKertasKandungan();
            }
            perakuan1.setKertas(permohonanKertas);
            perakuan1.setBil(9);
            kandungan = getContext().getRequest().getParameter("perakuan1.kandungan");
            if (kandungan == null) {
                perakuan1.setKandungan("Tiada");
            } else {
                perakuan1.setKandungan(kandungan);
            }
            perakuan1.setCawangan(permohonan.getCawangan());
            InfoAudit ia = new InfoAudit();
            ia.setTarikhMasuk(new Date());
            ia.setDimasukOleh(peng);
            perakuan1.setInfoAudit(ia);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(perakuan1);


            count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));

            for (int i = 1; i <= count5; i++) {
                String subtajuk = "1." + i;
                senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 1, subtajuk);
                int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
                for (int k = 1; k <= rowCount5; k++) {
                    if (senaraiKertasKandungan1 != null && senaraiKertasKandungan1.size() != 0 && k <= senaraiKertasKandungan1.size()) {
                        Long id3 = senaraiKertasKandungan1.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan4.setKertas(permohonanKertas);
                    permohonanKertasKandungan4.setBil(1);
                    kandungan = getContext().getRequest().getParameter("senaraiPentadbir" + i + k);
                    if (kandungan == null) {
                        permohonanKertasKandungan4.setKandungan("Tiada");
                    } else {
                        permohonanKertasKandungan4.setKandungan(kandungan);
                    }
                    permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                    permohonanKertasKandungan4.setSubtajuk("1." + i + "." + str[k - 1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
                }
            }

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

            String tarikhIkrar1 = getContext().getRequest().getParameter("permohonanMahkamah.tarikhIkrar");
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("permohonanMahkamah.tarikhIkrar"))) {
                permohonanMahkamah.setTarikhIkrar(sdf1.parse(tarikhIkrar1));
            }
            pendudukanSementaraMMKNService.simpanPermohonanMahkamah(permohonanMahkamah);

            rehydrate();
            pihakDetails();

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
//        return new RedirectResolution(AffidavitActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try {
            permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(cawangan);
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
//        String form1 = getContext().getRequest().getParameter("form1");
//        if(form1.equals("true"))
//        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), bil);

        if (kandList != null && !kandList.isEmpty()) {
            PermohonanKertasKandungan maxKertasKand = kandList.get(0);
            if (maxKertasKand != null) {
                String subtajuk = maxKertasKand.getSubtajuk();
                String str = subtajuk.substring(2, 3);
                String subtajuk1 = bil + "." + str;
                List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), bil, subtajuk1);
                for (PermohonanKertasKandungan kand : kandList1) {
                    pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                }
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String form1 = getContext().getRequest().getParameter("form1");
        if (form1.equals("true")) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        }
        return new JSP("pengambilan/affidavit_mahkamah.jsp").addParameter("tab", "true");

    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
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

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
        return permohonanKertasKandungan3;
    }

    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;

    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;

    }

    public String getKandungan() {
        return kandungan;

    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan2() {
        return senaraiKertasKandungan2;
    }

    public void setSenaraiKertasKandungan2(List<PermohonanKertasKandungan> senaraiKertasKandungan2) {
        this.senaraiKertasKandungan2 = senaraiKertasKandungan2;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public PermohonanKertasKandungan getMasaObj() {
        return masaObj;
    }

    public void setMasaObj(PermohonanKertasKandungan masaObj) {
        this.masaObj = masaObj;
    }

    public String getKandungan1() {
        return kandungan1;
    }

    public void setKandungan1(String kandungan1) {
        this.kandungan1 = kandungan1;
    }

    public String getKandungan2() {
        return kandungan2;
    }

    public void setKandungan2(String kandungan2) {
        this.kandungan2 = kandungan2;
    }

    public String getKandungan3() {
        return kandungan3;
    }

    public void setKandungan3(String kandungan3) {
        this.kandungan3 = kandungan3;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanKertasKandungan getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(PermohonanKertasKandungan perakuan) {
        this.perakuan = perakuan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }

    public PermohonanKertasKandungan getDiIkrarOleh() {
        return diIkrarOleh;
    }

    public void setDiIkrarOleh(PermohonanKertasKandungan diIkrarOleh) {
        this.diIkrarOleh = diIkrarOleh;
    }

    public PermohonanKertasKandungan getPesuruhJayaSumpah() {
        return pesuruhJayaSumpah;
    }

    public void setPesuruhJayaSumpah(PermohonanKertasKandungan pesuruhJayaSumpah) {
        this.pesuruhJayaSumpah = pesuruhJayaSumpah;
    }

    public PermohonanKertasKandungan getTarikhIkrar() {
        return tarikhIkrar;
    }

    public void setTarikhIkrar(PermohonanKertasKandungan tarikhIkrar) {
        this.tarikhIkrar = tarikhIkrar;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public List[] getSenaraiSyor() {
        return senaraiSyor;
    }

    public void setSenaraiSyor(List[] senaraiSyor) {
        this.senaraiSyor = senaraiSyor;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
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

    public String getPagiPetang() {
        return pagiPetang;
    }

    public void setPagiPetang(String pagiPetang) {
        this.pagiPetang = pagiPetang;
    }

    public String getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(String kertasBil) {
        this.kertasBil = kertasBil;
    }

    public String getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(String kertasTahun) {
        this.kertasTahun = kertasTahun;
    }

    public String getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(String tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public String getSamanPemulaBil() {
        return samanPemulaBil;
    }

    public void setSamanPemulaBil(String samanPemulaBil) {
        this.samanPemulaBil = samanPemulaBil;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanSupayaBantahanService getPermohonanSupayaBantahanService() {
        return permohonanSupayaBantahanService;
    }

    public void setPermohonanSupayaBantahanService(PermohonanSupayaBantahanService permohonanSupayaBantahanService) {
        this.permohonanSupayaBantahanService = permohonanSupayaBantahanService;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public PermohonanKertasKandungan getHeadingObj2() {
        return headingObj2;
    }

    public void setHeadingObj2(PermohonanKertasKandungan headingObj2) {
        this.headingObj2 = headingObj2;
    }

    public PermohonanKertasKandungan getPerakuan1() {
        return perakuan1;
    }

    public void setPerakuan1(PermohonanKertasKandungan perakuan1) {
        this.perakuan1 = perakuan1;
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

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
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

    public List[] getSenaraiPentadbir() {
        return senaraiPentadbir;
    }

    public void setSenaraiPentadbir(List[] senaraiPentadbir) {
        this.senaraiPentadbir = senaraiPentadbir;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan6() {
        return senaraiKertasKandungan6;
    }

    public void setSenaraiKertasKandungan6(List<PermohonanKertasKandungan> senaraiKertasKandungan6) {
        this.senaraiKertasKandungan6 = senaraiKertasKandungan6;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getPenyediaSidang() {
        return penyediaSidang;
    }

    public void setPenyediaSidang(String penyediaSidang) {
        this.penyediaSidang = penyediaSidang;
    }

    public String getWakilSidang() {
        return wakilSidang;
    }

    public void setWakilSidang(String wakilSidang) {
        this.wakilSidang = wakilSidang;
    }

    public long getIdMm() {
        return idMm;
    }

    public void setIdMm(long idMm) {
        this.idMm = idMm;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }

    public String getIdPermohonanSblm() {
        return idPermohonanSblm;
    }

    public void setIdPermohonanSblm(String idPermohonanSblm) {
        this.idPermohonanSblm = idPermohonanSblm;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public String getPenyediaSidang1() {
        return penyediaSidang1;
    }

    public void setPenyediaSidang1(String penyediaSidang1) {
        this.penyediaSidang1 = penyediaSidang1;
    }
}
