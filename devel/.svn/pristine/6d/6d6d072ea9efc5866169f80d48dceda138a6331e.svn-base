/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.etapp.EtappPermohonan;
import etanah.model.etapp.TBLINTPPTDERAFMMK;
import etanah.model.etapp.TBLPPTINTDERAFMMKTAJUK;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/pengambilan/mmkn_etapp")
public class MMKNeTappActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MMKNeTappActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PengambilanMMKService ambilService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    Configuration conf;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private String idPermohonan;
    private KodCawangan cawangan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasTahun;
    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertasKandungan tarikhmesyuarat;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan permohonanKertasKandungan8;
    private PermohonanKertasKandungan permohonanKertasKandungan9;
    private PermohonanKertasKandungan permohonanKertasKandungan7;
    private PermohonanKertasKandungan permohonanKertasKandungan11;
    private PermohonanKertasKandungan permohonanKertasKandungan12;
    private PermohonanKertasKandungan pKKUlaspt;
    private PermohonanKertasKandungan pKKakuanpt;
    private PermohonanKertasKandungan noRujukanMMKN;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan ulasan1;
    private PermohonanKertasKandungan tujuanObj;
    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan8;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan9;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan11;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan12;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan21;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan22;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan23;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan24;
//    private List<PermohonanKertasKandungan> sKKUlaspt;
//    private List<PermohonanKertasKandungan> sKKakuanpt;
    private List senaraiPerakuanPengarah[] = new ArrayList[20];
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Hakmilik hakmilik;
    private String heading;
    private String tujuan;
    private int count5 = 0;
    private int count8 = 0;
    private int count4 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private List senaraiSyorPentadbir[] = new ArrayList[5];
    private List senaraiSyorPengarah[] = new ArrayList[5];
//    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
    private String tajuk;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private BigDecimal totalLuas = new BigDecimal(0.00);
    StringBuffer noLotList;
    private String lokasi;
//    String daerah;
//    private String daerah;
    private String kodUrusan;
    private String namaSeksyen;
    private EtappPermohonan e;
    private TBLPPTINTDERAFMMKTAJUK mmkTjk;
    private List<PermohonanKertasKandungan> kandList;
    private List<TBLINTPPTDERAFMMK> tujuanList;
    private List<TBLINTPPTDERAFMMK> permohonanList;
    private List<TBLINTPPTDERAFMMK> tanahList;
    private List<TBLINTPPTDERAFMMK> pemohonList;
    private List<TBLINTPPTDERAFMMK> ulasanList;
    private List<TBLINTPPTDERAFMMK> YBList;
    private List<TBLINTPPTDERAFMMK> pandanganPT;
    private List<TBLINTPPTDERAFMMK> PerakuanPT;
    private List<TBLINTPPTDERAFMMK> PerakuanPTG;
    private List<TBLINTPPTDERAFMMK> pampasanList;
    private List<TBLINTPPTDERAFMMK> pandanganPT2;
    private List<TBLINTPPTDERAFMMK> perakuanPT2;
    private List<PermohonanKertasKandungan> tujuanList1;
    private List<PermohonanKertasKandungan> permohonanList1;
    private List<PermohonanKertasKandungan> tanahList1;
    private List<PermohonanKertasKandungan> pemohonList1;
    private List<PermohonanKertasKandungan> ulasanList1;
    private List<PermohonanKertasKandungan> YBList1;
//    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> perakuanPT1;
    private List<PermohonanKertasKandungan> perakuanPTG1;
    private List<PermohonanKertasKandungan> pampasanList1;
    private PermohonanKertasKandungan pandanganPT21;
    private List<PermohonanKertasKandungan> pandanganPT1;
    private PermohonanKertasKandungan perakuanPT21;
    private PermohonanKertasKandungan PKKTujuan;
    private PermohonanKertasKandungan PKKPermohonan;
    private PermohonanKertasKandungan PKKTanah;
    private PermohonanKertasKandungan PKKPemohon;
    private PermohonanKertasKandungan PKKPampasan;
    private PermohonanKertasKandungan PKKUTeknikal;
    private PermohonanKertasKandungan PKKPandanganYB;
    private PermohonanKertasKandungan PKKPandanganPT;
    private PermohonanKertasKandungan PKKPerakuanPT;
    private PermohonanKertasKandungan PKKPerakuanPTG;
    private long idKertas;
    private String ulaspt;
    private String akuanpt;
    private String totalLot;
    private String pejTanah;
    private String Kppt;
    private String Pt;
    private String searchTanah;
    private String searchAnggaran;
    private String searchPT;
    private String searchPermohonan;
    private String uom;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        logger.info("kodNegeri : " + conf.getKodNegeri());
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("ape masalahnye ni");

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        HakmilikPermohonan hp3 = new HakmilikPermohonan();
        noLotList = new StringBuffer();
        kodUrusan = permohonan.getKodUrusan().getKod();
//        if (kodUrusan.equalsIgnoreCase("SEK4")) {
//            namaSeksyen = "DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
//            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
//        } else if (kodUrusan.equalsIgnoreCase("831A")) {
//            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960.";
//            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(a) NEGERI MELAKA";
//        } else if (kodUrusan.equalsIgnoreCase("831B")) {
//            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(b) AKTA PENGAMBILAN TANAH 1960.";
//            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(b) NEGERI MELAKA";
//        } else if (kodUrusan.equalsIgnoreCase("831C")) {
//            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(c) AKTA PENGAMBILAN TANAH 1960.";
//            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(c) NEGERI MELAKA";
//        } //shida-- modified for urusan etapp 
        if (kodUrusan.equalsIgnoreCase("ESK4") || kodUrusan.equalsIgnoreCase("ESK8")) {
            namaSeksyen = "DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
//            tajukKer = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
        } else if (kodUrusan.equalsIgnoreCase("ESK8")) {
            namaSeksyen = "DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960.";
//           tajukKer = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 8 NEGERI MELAKA";
        }
        for (HakmilikPermohonan hp1 : hakmilikPermohonanList) {
            if (hakmilikPermohonanList.size() < 11) {
//            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ").append(hp1.getHakmilik().getBandarPekanMukim().getNama()).append(", ");
                noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ");
            } else {
                totalLot = hakmilikPermohonanList.size() + " Lot";
            }
        }

        totalLuas = BigDecimal.ZERO;
        BigDecimal luas = BigDecimal.ZERO;

        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            luas = BigDecimal.ZERO;
            luas = hakmilikPermohonan.getLuasTerlibat();
            if (hakmilikPermohonan.getKodUnitLuas() != null) {
                String kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();

                if (kodUOM.equals("Hektar")) {
                    totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
                    uom = "Hektar";
                } else if (kodUOM.equals("Meter Persegi")) {
                    totalLuas = totalLuas.add(luas);
                    uom = "Meter Persegi";
                }
            } else if ((hakmilikPermohonan.getKodUnitLuas() == null)) {
                totalLuas = null;
                uom = null;
            }
        }

//        if (hakmilikPermohonan.getKodUnitLuas() != null) {
//            String uom = "Meter Persegi";
//            if (totalLuas.compareTo(new BigDecimal(10000)) == 1) {
//                totalLuas = totalLuas.divide(new BigDecimal(10000));
//                uom = "Hektar";
//            } else if (totalLuas.compareTo(new BigDecimal(10000)) == 0) {
//                totalLuas = totalLuas.divide(new BigDecimal(10000));
//                uom = "";
//            }
//        }

        if (!hakmilikPermohonanList.isEmpty()) {
            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp3 = hakmilikPermohonanList.get(w);
                if (hp3.getHakmilik() != null) {
                    hakmilik = hp3.getHakmilik();
                    if (w == 0) {
                        lokasi = hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ", "
                                + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + ", ";
                    }

                    if (w > 0) {
                        if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                            lokasi = lokasi + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ", "
                                    + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + ", ";
                        } else if (w == (hakmilikPermohonanList.size() - 1)) {
                            lokasi = lokasi + " dan " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ","
                                    + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + ", ";
                        }
                    }
//
//                            tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Yang Amat Berhormat Menteri Besar, Negeri Sembilan ke atas permohonan pengambilan tanah "
//                                    + "Seksyen 3(1)(a) Akta Pengambilan Tanah 1960, Projek " + WordUtils.capitalizeFully(permohonan.getSebab()) + " daripada " + WordUtils.capitalizeFully(pem.toLowerCase()) + " " + noLotList + ",Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas lebih kurang " + totalLuas + "" + uom + ". "
//                                    + "Kawasan yang dimaksudkan adalah seperti bertanda merah dalam pelan di bil.(11A) dlm.PTR 362/101/2010/04.";
//                    if (kodUrusan.equalsIgnoreCase("ESK4") || kodUrusan.equalsIgnoreCase("ESK8")) {
////                        tujuan = ;
//                    } else {
//                        tujuan = "Tujuan kertas ini dikemukakan adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat "
//                                + "Kerajaan Negeri Melaka bagi permohonan pengambilan tanah di atas "
//                                + noLotList + " seluas " + totalLuas + " " + uom + ",di "
//                                + hakmilik.getBandarPekanMukim().getNama() + "," + " Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + ", Melaka untuk tujuan "
//                                + permohonan.getSebab().toLowerCase() + " " + WordUtils.capitalizeFully(namaSeksyen);
////                                    + WordUtils.capitalizeFully(permohonan.getSebab().toLowerCase())+
//                    }


                }
            }
        }


        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hp.getHakmilik();

//        tujuan = "Tujuan kertas ini dikemukakan adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat "
//                + "Kerajaan Negeri Melaka bagi permohonan pengambilan tanah di atas "
//                + noLotList + " ,seluas " + totalLuas +" " + uom + ",di "
//                + hakmilik.getBandarPekanMukim().getNama() + "," + " Daerah " + hakmilik.getDaerah().getNama() + ", Melaka untuk tujuan "
//                + WordUtils.capitalizeFully(permohonan.getSebab())+ " di bawah Seksyen 4, Akta Pengambilan Tanah 1960.";



        if (hakmilikPermohonanList.size() < 11) {
            String temp = "PERMOHONAN PENGAMBILAN TANAH SELUAS LEBIH KURANG "
                    + totalLuas + " " + uom + " UNTUK " + permohonan.getSebab() + " DI "
                    + noLotList + " DAERAH " + hakmilik.getDaerah().getNama() + " " + namaSeksyen;
            heading = temp.toUpperCase();
        } else {
            String temp = "PERMOHONAN PENGAMBILAN TANAH DARIPADA SELUAS LEBIH KURANG "
                    + totalLuas + " " + uom + " UNTUK " + permohonan.getSebab() + " DI "
                    + totalLot + " DAERAH " + hakmilik.getDaerah().getNama() + " " + namaSeksyen;
            heading = temp.toUpperCase();
        }


        if (idPermohonan != null) {
            permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
            if (permohonanKertas != null) {

                permohonanKertas = ambilService.findMohonKertasByIdMohon(permohonan.getIdPermohonan(), "MMKN");
                logger.info("idKertas : " + permohonanKertas.getIdKertas());
            } else {
                System.out.println("simpan kertas");
                permohonan = permohonanDAO.findById(idPermohonan);
                logger.info("permohonan : " + permohonan.getIdPermohonan());

                permohonanKertas = new PermohonanKertas();

                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setCawangan(permohonan.getCawangan());
                permohonanKertas.setKodDokumen(kodDokumenDAO.findById("MMKN"));
                logger.info("Tajuk : " + heading);
                permohonanKertas.setTajuk(heading);
                permohonanKertas.setInfoAudit(setInfoAudit());
                permohonanKertas = ambilService.savePermohonanKertas(permohonanKertas);

//                e = ambilService.getNofail(permohonan.getIdPermohonan());
//                if (e == null) {
//
//                    logger.info("e adalah null");
//
//                } else {
//                    logger.info("noFail : " + e.getNoFail());
//                    mmkTjk = ambilService.findTajuk(e.getNoFail());
//
//                    if (mmkTjk != null) {
//                        if (mmkTjk.getTajuk() != null) {
//                            permohonanKertas.setTajuk(mmkTjk.getTajuk());
//                            permohonanKertas.setTarikhSidang(mmkTjk.getTarikhSidang());
//                            permohonanKertas.setTempatSidang(mmkTjk.getTempatSidang());
//                        } else {
//                            if (kodUrusan.equalsIgnoreCase("ESK4")) {
//                                tajuk = "PERMOHONAN PENGAMBILAN TANAH DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
//                            } else if (kodUrusan.equalsIgnoreCase("ESK8")) {
//                                tajuk = "PERMOHONAN PENGAMBILAN TANAH SEKSYEN 8 NEGERI MELAKA DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960.";
//                            }
//                        }
//                    } else {
//                        if (kodUrusan.equalsIgnoreCase("ESK4")) {
//                            tajuk = "PERMOHONAN PENGAMBILAN TANAH DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
//                        } else if (kodUrusan.equalsIgnoreCase("ESK8")) {
//                            tajuk = "PERMOHONAN PENGAMBILAN TANAH SEKSYEN 8 NEGERI MELAKA DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960.";
//                        }
//
//                        permohonanKertas.setTajuk(tajuk);
//                    }
//
//                    permohonanKertas.setInfoAudit(setInfoAudit());
//                    permohonanKertas = ambilService.savePermohonanKertas(permohonanKertas);
//                }
            }
            permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");

            //find Kertas Kandungan
            kandList = ambilService.findListByIdkertas(permohonanKertas.getIdKertas());
            logger.info("kandList : " + kandList.size());
            if (kandList == null || kandList.size() == 0) {

                EtappPermohonan e = ambilService.getNofail(permohonanKertas.getPermohonan().getIdPermohonan());
                logger.info("permohonan : " + permohonanKertas.getPermohonan().getIdPermohonan());


                pejTanah = permohonan.getCawangan().getDaerah().getNama();

//                List Daerah = ambilService.FindDaerah(e.getNoFail());
//                logger.info("daerah : " + Daerah.size());
//                for (int i = 0; i < Daerah.size(); i++) {
//
//                    String d = String.valueOf(Daerah.get(i));
//                    logger.info("d : " + d);
//                    KodDaerah dae = kodDaerahDAO.findById(d);
//                    logger.info("d : " + dae.getNama());
//                    daerah = new StringBuffer();
//                    daerah = daerah.append("Daerah ").append(dae.getNama()).append(", ");
//                }

                List<TBLINTPPTDERAFMMK> noRujUPT = ambilService.findMMKdrafKand1(e.getNoFail(), "RUJUKAN");
                if (noRujUPT != null) {
                    for (int u = 0; u < noRujUPT.size(); u++) {
                        if (noRujUPT.get(u).getKeteranganMMK() != null) {

                            noRujukanMMKN = new PermohonanKertasKandungan();
                            noRujukanMMKN.setKandungan(noRujUPT.get(u).getKeteranganMMK());
                            noRujukanMMKN.setKertas(permohonanKertas);
                            noRujukanMMKN.setCawangan(permohonan.getCawangan());
                            noRujukanMMKN.setBil(0);
                            noRujukanMMKN.setSubtajuk("0");
                            noRujukanMMKN.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(noRujukanMMKN);
                        }
                    }
                }

                List<TBLINTPPTDERAFMMK> mmk = ambilService.findMMKdraf(e.getNoFail());
                logger.info("noFail : " + e.getNoFail());

                if (mmk != null) {
                    tujuanList = ambilService.findMMKdrafKand(e.getNoFail(), "TUJUAN");
                    if (tujuanList != null) {
                        logger.info("Simpan tujuan.. Ade : " + tujuanList.size());
                        for (int i = 0; i < tujuanList.size(); i++) {

                            logger.info("Simpan tujuan.. Ade : " + tujuanList.get(i).getKeteranganMMK());

                            PKKTujuan = new PermohonanKertasKandungan();
                            PKKTujuan.setKertas(permohonanKertas);
                            PKKTujuan.setCawangan(permohonan.getCawangan());
                            PKKTujuan.setBil(1);
                            PKKTujuan.setSubtajuk("1." + (i + 1));
                            PKKTujuan.setKandungan(tujuanList.get(i).getKeteranganMMK());
                            PKKTujuan.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKTujuan);
//                        }
                        }
                    }
                    if (conf.getKodNegeri() == "04") {
                        searchPermohonan = "PERIHALPERMOHONAN";
                    } else if (conf.getKodNegeri() == "05") {
                        searchPermohonan = "KEPUTUSAN";
                    }

                    permohonanList = ambilService.findMMKdrafKand(e.getNoFail(), searchPermohonan);
                    if (permohonanList != null) {
                        logger.info("simpan perihal permohonan.. Ade : " + permohonanList.size());


                        for (int i = 0; i < permohonanList.size(); i++) {
//                        for (TBLINTPPTDERAFMMK permohonanLIST : permohonanList) {

                            TBLINTPPTDERAFMMK b = permohonanList.get(i);
                            logger.info("Simpan permohonan: " + i + b.getKeteranganMMK());
                            PKKPermohonan = new PermohonanKertasKandungan();
                            PKKPermohonan.setKertas(permohonanKertas);
                            PKKPermohonan.setCawangan(permohonan.getCawangan());
                            PKKPermohonan.setBil(21);
                            PKKPermohonan.setSubtajuk("2.1." + (i + 1));
                            PKKPermohonan.setKandungan(b.getKeteranganMMK());
                            PKKPermohonan.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKPermohonan);
                        }
                    }
                    if (conf.getKodNegeri() == "04") {
                        searchTanah = "TANAH";
                    } else if (conf.getKodNegeri() == "05") {
                        searchTanah = "PERIHALTANAH";
                    }
                    tanahList = ambilService.findMMKdrafKand1(e.getNoFail(), searchTanah);
                    if (tanahList != null) {
                        logger.info("simpan perihal tanah.. Ade : " + tanahList.size());

                        for (int i = 0; i < tanahList.size(); i++) {
//
                            logger.info("Simpan tanah.. Ade : " + tanahList.get(i).getKeteranganMMK());
                            PKKTanah = new PermohonanKertasKandungan();
                            PKKTanah.setKertas(permohonanKertas);
                            PKKTanah.setCawangan(permohonan.getCawangan());
                            PKKTanah.setBil(22);
                            PKKTanah.setSubtajuk("2.2." + (i + 1));
                            PKKTanah.setKandungan(tanahList.get(i).getKeteranganMMK());
                            PKKTanah.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKTanah);
                        }
                    }
                    pemohonList = ambilService.findMMKdrafKand(e.getNoFail(), "PERIHALPEMOHON");
                    if (pemohonList != null) {

                        logger.info("simpan perihal pemohon.. Ade : " + pemohonList.size());
//                
                        for (int i = 0; i < pemohonList.size(); i++) {

                            logger.info("Simpan pemohon.. Ade : " + pemohonList.get(i).getKeteranganMMK());

                            PKKPemohon = new PermohonanKertasKandungan();
                            PKKPemohon.setKertas(permohonanKertas);
                            PKKPemohon.setCawangan(permohonan.getCawangan());
                            PKKPemohon.setBil(23);
                            PKKPemohon.setSubtajuk("2.3." + (i + 1));
                            PKKPemohon.setKandungan(pemohonList.get(i).getKeteranganMMK());
                            PKKPemohon.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKPemohon);
                        }
                    }
                    if (conf.getKodNegeri() == "04") {
                        searchAnggaran = "ANGGARANPAMPASAN";
                    } else if (conf.getKodNegeri() == "05") {
                        searchAnggaran = "NILAITANAH";
                    }
                    pampasanList = ambilService.findMMKdrafKand(e.getNoFail(), searchAnggaran);
                    if (pampasanList != null) {
                        logger.info("simpan perihal pampasan.. Ade : " + pampasanList.size());

                        for (int i = 0; i < pampasanList.size(); i++) {

                            PKKPampasan = new PermohonanKertasKandungan();
                            PKKPampasan.setKertas(permohonanKertas);
                            PKKPampasan.setCawangan(permohonan.getCawangan());
                            PKKPampasan.setBil(24);
                            PKKPampasan.setSubtajuk("2.4." + (i + 1));
                            PKKPampasan.setInfoAudit(setInfoAudit());
                            PKKPampasan.setKandungan(pampasanList.get(i).getKeteranganMMK());
                            ambilService.simpanPermohonanKertasKandungan(PKKPampasan);
                        }
                    }
                    ulasanList = ambilService.findMMKdrafKand1(e.getNoFail(), "TEKNIKAL");
                    if (ulasanList != null) {
                        logger.info("simpan perihal ulasan.. Ade : " + ulasanList.size());

                        for (int i = 0; i < ulasanList.size(); i++) {

                            PKKUTeknikal = new PermohonanKertasKandungan();
                            PKKUTeknikal.setKertas(permohonanKertas);
                            PKKUTeknikal.setCawangan(permohonan.getCawangan());
                            PKKUTeknikal.setBil(3);
                            PKKUTeknikal.setSubtajuk("3." + (i + 1));
                            PKKUTeknikal.setKandungan(ulasanList.get(i).getKeteranganMMK());
                            PKKUTeknikal.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKUTeknikal);
                        }
                    }
                    YBList = ambilService.findMMKdrafKand(e.getNoFail(), "PANDANGANYB");
                    if (YBList != null) {
                        logger.info("simpan perihal yb list.. Ade : " + YBList.size());

                        for (int i = 0; i < YBList.size(); i++) {

                            PKKPandanganYB = new PermohonanKertasKandungan();
                            PKKPandanganYB.setKertas(permohonanKertas);
                            PKKPandanganYB.setCawangan(permohonan.getCawangan());
                            PKKPandanganYB.setBil(4);
                            PKKPandanganYB.setSubtajuk("4." + (i + 1));
                            PKKPandanganYB.setInfoAudit(setInfoAudit());
                            PKKPandanganYB.setKandungan(YBList.get(i).getKeteranganMMK());
                            ambilService.simpanPermohonanKertasKandungan(PKKPandanganYB);
                        }
                    }
                    if (conf.getKodNegeri() == "04") {
                        searchPT = "PANDANGANPT";

                        pandanganPT = ambilService.findMMKdrafKand(e.getNoFail(), searchPT);

                        if (pandanganPT != null) {
                            System.out.println("simpan ulasan PT 1");
                            PermohonanKertasKandungan PKKPandanganPT2 = new PermohonanKertasKandungan();
                            PKKPandanganPT2.setKertas(permohonanKertas);
                            PKKPandanganPT2.setCawangan(permohonan.getCawangan());
                            PKKPandanganPT2.setBil(5);
                            PKKPandanganPT2.setSubtajuk("5.1");
                            PKKPandanganPT2.setInfoAudit(setInfoAudit());

                            PKKPandanganPT2.setKandungan("Pentadbir Tanah " + pejTanah + ", setelah meneliti permohonan ini berpendapat bahawa pengambilan tanah untuk tapak yang dimaksudkan bolehlah diteruskan, memandangkan kepada faktor-faktor berikut:"); //TGK BLIK UTK MELAKA
                            ambilService.simpanPermohonanKertasKandungan(PKKPandanganPT2);
                            logger.info("simpan perihal pandangan PT.. Ade : " + pandanganPT.size());

                            for (int i = 0; i < pandanganPT.size(); i++) {

                                PKKPandanganPT = new PermohonanKertasKandungan();
                                PKKPandanganPT.setKertas(permohonanKertas);
                                PKKPandanganPT.setCawangan(permohonan.getCawangan());
                                PKKPandanganPT.setBil(51);
                                PKKPandanganPT.setSubtajuk("5.1." + (i + 1));
                                PKKPandanganPT.setInfoAudit(setInfoAudit());
                                PKKPandanganPT.setKandungan(pandanganPT.get(i).getKeteranganMMK());
                                ambilService.simpanPermohonanKertasKandungan(PKKPandanganPT);

                            }
                        }
                    } else if (conf.getKodNegeri() == "05") {
                        searchPT = "SYOR";
                        pandanganPT = ambilService.findMMKdrafKand(e.getNoFail(), searchPT);
                        if (pandanganPT != null) {
                            System.out.println("simpan ulasan PT 1");
                            PermohonanKertasKandungan PKKPandanganPT2 = new PermohonanKertasKandungan();
                            PKKPandanganPT2.setKertas(permohonanKertas);
                            PKKPandanganPT2.setCawangan(permohonan.getCawangan());
                            PKKPandanganPT2.setBil(5);
                            PKKPandanganPT2.setSubtajuk("5.1");
                            PKKPandanganPT2.setInfoAudit(setInfoAudit());

                            PKKPandanganPT2.setKandungan("Pentadbir Tanah " + pejTanah + ", mengesyorkan supaya :");
                            ambilService.simpanPermohonanKertasKandungan(PKKPandanganPT2);
                            logger.info("simpan perihal pandangan PT.. Ade : " + pandanganPT.size());

                            for (int i = 0; i < pandanganPT.size(); i++) {

                                PKKPandanganPT = new PermohonanKertasKandungan();
                                PKKPandanganPT.setKertas(permohonanKertas);
                                PKKPandanganPT.setCawangan(permohonan.getCawangan());
                                PKKPandanganPT.setBil(51);
                                PKKPandanganPT.setSubtajuk("5.1." + (i + 1));
                                PKKPandanganPT.setInfoAudit(setInfoAudit());
                                PKKPandanganPT.setKandungan(pandanganPT.get(i).getKeteranganMMK());
                                ambilService.simpanPermohonanKertasKandungan(PKKPandanganPT);

                            }
                        }
                    }

                    PerakuanPT = ambilService.findMMKdrafKand(e.getNoFail(), "PERAKUANPT");
                    if (PerakuanPT != null) {
                        logger.info("simpan perihal perakuan PT.. Ade : " + PerakuanPT.size());
                        PermohonanKertasKandungan PKKPerakuanPT2 = new PermohonanKertasKandungan();
                        PKKPerakuanPT2.setKertas(permohonanKertas);
                        PKKPerakuanPT2.setCawangan(permohonan.getCawangan());
                        PKKPerakuanPT2.setBil(6);
                        PKKPerakuanPT2.setSubtajuk("6.1");
                        PKKPerakuanPT2.setKandungan("Pentadbir Tanah " + pejTanah + ", dengan ini memperakukan supaya:"); //NNT EDIT BLIK UTK MELAKA
                        PKKPerakuanPT2.setInfoAudit(setInfoAudit());
                        ambilService.simpanPermohonanKertasKandungan(PKKPerakuanPT2);

                        for (int i = 0; i < PerakuanPT.size(); i++) {

                            PKKPerakuanPT = new PermohonanKertasKandungan();
                            PKKPerakuanPT.setKertas(permohonanKertas);
                            PKKPerakuanPT.setCawangan(permohonan.getCawangan());
                            PKKPerakuanPT.setBil(61);
                            PKKPerakuanPT.setSubtajuk("6.1." + (i + 1));
                            PKKPerakuanPT.setKandungan(PerakuanPT.get(i).getKeteranganMMK());
                            PKKPerakuanPT.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKPerakuanPT);
                        }
                    }
                    PerakuanPTG = ambilService.findMMKdrafKand(e.getNoFail(), "ULASANPENGARAH");
                    if (PerakuanPTG != null) {
                        logger.info("simpan perihal perakuan ptg.. Ade : " + PerakuanPTG.size());

                        for (int i = 0; i < PerakuanPTG.size(); i++) {

                            PKKPerakuanPTG = new PermohonanKertasKandungan();
                            PKKPerakuanPTG.setKertas(permohonanKertas);
                            PKKPerakuanPTG.setCawangan(permohonan.getCawangan());
                            PKKPerakuanPTG.setBil(7);
                            PKKPerakuanPTG.setSubtajuk("7." + (i + 1));
                            PKKPerakuanPTG.setKandungan(PerakuanPTG.get(i).getKeteranganMMK());
                            PKKPerakuanPTG.setInfoAudit(setInfoAudit());
                            ambilService.simpanPermohonanKertasKandungan(PKKPerakuanPTG);
                        }
                    }
//                }
//            }

                }
            } else {

                permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
                logger.info("idKertas : " + permohonanKertas.getIdKertas());
                tajuk = permohonanKertas.getTajuk();
//        logger.info("id mohon : " + idPermohonan);
//        PermohonanKertas pk = ambilService.findMohonKertasByIdMohon(idPermohonan);
                idKertas = permohonanKertas.getIdKertas();
                logger.info("id kertas : " + idKertas);
                tujuanList1 = ambilService.findMMKdrafKandNew(idKertas, 1);
                senaraiKertasKandungan21 = ambilService.findMMKdrafKandNew(idKertas, 21);
                senaraiKertasKandungan22 = ambilService.findMMKdrafKandNew(idKertas, 22);
                senaraiKertasKandungan23 = ambilService.findMMKdrafKandNew(idKertas, 23);
                senaraiKertasKandungan24 = ambilService.findMMKdrafKandNew(idKertas, 24);
                senaraiKertasKandungan9 = ambilService.findMMKdrafKandNew(idKertas, 3);
                senaraiKertasKandungan3 = ambilService.findMMKdrafKandNew(idKertas, 4);
                pandanganPT21 = ambilService.findMMKdrafKandNew1(idKertas, 5);
                ulaspt = pandanganPT21.getKandungan();
                senaraiKertasKandungan11 = ambilService.findMMKdrafKandNew(idKertas, 51);
//        senaraiKertasKandungan4 = ambilService.findMMKdrafKandNew(idKertas, 51);
                perakuanPT21 = ambilService.findMMKdrafKandNew1(idKertas, 6);
                akuanpt = perakuanPT21.getKandungan();
                //        PerakuanPT1 = ambilService.findMMKdrafKandNew(idKertas, 61);
                senaraiKertasKandungan4 = ambilService.findMMKdrafKandNew(idKertas, 61);
//        PerakuanPTG1 = ambilService.findMMKdrafKandNew(idKertas, 7);
                senaraiKertasKandungan5 = ambilService.findMMKdrafKandNew(idKertas, 7);




//                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
//                if(headingObj != null){
//                heading = headingObj.getKandungan();
//                }
//                if(tujuanObj != null){
//                tujuan = tujuanObj.getKandungan();
//                }

//
//                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
//                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 7);
//                if (kandList5 != null && !kandList5.isEmpty()) {
//                    logger.debug("kandList5 x empty");
//                    maxKertasKand5 = kandList5.get(0);
//                    if (maxKertasKand5 != null) {
//                        logger.debug("maxKertasKand5");
//                        String subtajuk = maxKertasKand5.getSubtajuk();
//                        logger.debug("subtajuk " + subtajuk);
//                        String str = subtajuk.substring(2, 3);
//                        logger.debug("str " + str);
//                        int tableCount = Integer.parseInt(str);
//                        logger.debug("tableCount " + tableCount);
//                        count4 = tableCount;
//                        for (int k = 1; k <= tableCount; k++) {
//                            senaraiPerakuanPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
//                            String subtajuk1 = "7." + k;
//                            senaraiPerakuanPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 7, subtajuk1);
//                        }
//                    }
//                }
            }
            permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
            logger.info("idKertas : " + permohonanKertas.getIdKertas());
            tajuk = permohonanKertas.getTajuk();

//        logger.info("id mohon : " + idPermohonan);
//        PermohonanKertas pk = ambilService.findMohonKertasByIdMohon(idPermohonan);
            idKertas = permohonanKertas.getIdKertas();
            logger.info("id kertas : " + idKertas);
            tujuanList1 = ambilService.findMMKdrafKandNew(idKertas, 1);
            senaraiKertasKandungan21 = ambilService.findMMKdrafKandNew(idKertas, 21);
            senaraiKertasKandungan22 = ambilService.findMMKdrafKandNew(idKertas, 22);
            senaraiKertasKandungan23 = ambilService.findMMKdrafKandNew(idKertas, 23);
            senaraiKertasKandungan24 = ambilService.findMMKdrafKandNew(idKertas, 24);
            senaraiKertasKandungan9 = ambilService.findMMKdrafKandNew(idKertas, 3);
            senaraiKertasKandungan3 = ambilService.findMMKdrafKandNew(idKertas, 4);
            pandanganPT21 = ambilService.findMMKdrafKandNew1(idKertas, 5);
            ulaspt = pandanganPT21.getKandungan();
            senaraiKertasKandungan11 = ambilService.findMMKdrafKandNew(idKertas, 51);
//        senaraiKertasKandungan4 = ambilService.findMMKdrafKandNew(idKertas, 51);
            perakuanPT21 = ambilService.findMMKdrafKandNew1(idKertas, 6);
            akuanpt = perakuanPT21.getKandungan();
            //        PerakuanPT1 = ambilService.findMMKdrafKandNew(idKertas, 61);
            senaraiKertasKandungan4 = ambilService.findMMKdrafKandNew(idKertas, 61);
//        PerakuanPTG1 = ambilService.findMMKdrafKandNew(idKertas, 7);
            senaraiKertasKandungan5 = ambilService.findMMKdrafKandNew(idKertas, 7);

        }

    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
        System.out.println("simpan kertas");
        logger.info("permohonanKertas : " + permohonanKertas.getIdKertas());
        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            permohonanKertas.setInfoAudit(setInfoAudit());

        } else {
            InfoAudit iaPermohonan = permohonanKertas.getInfoAudit();
            iaPermohonan.setTarikhKemaskini(new Date());
            iaPermohonan.setDiKemaskiniOleh(peng);
        }
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(kodDokumenDAO.findById("MMKN"));
        logger.info("Tajuk : " + tajuk);
        permohonanKertas.setTajuk(tajuk);

        permohonanKertas = ambilService.savePermohonanKertas(permohonanKertas);

        System.out.println("simpan kertas kandungan plak ye");
//        if (getContext().getRequest().getParameter("heading") != null) {
//                if (headingObj == null) {
//                    headingObj = new PermohonanKertasKandungan();
//
//                }
//                headingObj.setKertas(permohonanKertas);
//                headingObj.setBil(1);
//                kandungan = getContext().getRequest().getParameter("heading");
//                headingObj.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                headingObj.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                headingObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
//        }

//        if (getContext().getRequest().getParameter("tujuan") != null) {
//            InfoAudit iaP = new InfoAudit();
//            if (tujuanObj == null) {
//                tujuanObj = new PermohonanKertasKandungan();
//                iaP.setDimasukOleh(peng);
//                iaP.setTarikhMasuk(new java.util.Date());
//            } else {
//                iaP = tujuanObj.getInfoAudit();
//                iaP.setDiKemaskiniOleh(peng);
//                iaP.setTarikhKemaskini(new java.util.Date());
//            }
//            tujuanObj.setKertas(permohonanKertas);
//            tujuanObj.setBil(1);
//            kandungan = getContext().getRequest().getParameter("tujuan");
////            if(kandungan==null){
////                    tujuanObj.setKandungan("Tiada");
////                }else{
//            tujuanObj.setKandungan(kandungan);
////            }
//            tujuanObj.setCawangan(permohonan.getCawangan());
//            tujuanObj.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
//        }

//        if (getContext().getRequest().getParameter("tujuan") != null) {
//            if (tujuanObj == null) {
//                tujuanObj = new PermohonanKertasKandungan();
//            }
//            tujuanObj.setKertas(permohonanKertas);
//            tujuanObj.setBil(2);
//            kandungan = getContext().getRequest().getParameter("tujuan");
//            tujuanObj.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            tujuanObj.setCawangan(cawangan);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            tujuanObj.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
//        }
        senaraiKertasKandungan12 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 1);
        int kira12 = Integer.parseInt(getContext().getRequest().getParameter("rowCount12"));
        logger.debug("kira 12 " + kira12);
        for (int i = 1; i <= kira12; i++) {
            if (senaraiKertasKandungan12.size() != 0 && i <= senaraiKertasKandungan12.size()) {
                Long id = senaraiKertasKandungan12.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan12 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan12.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan12 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan12.setInfoAudit(iaP);
            }
            permohonanKertasKandungan12.setKertas(permohonanKertas);
            permohonanKertasKandungan12.setBil(1);
            kandungan = getContext().getRequest().getParameter("kandungan12" + i);
            logger.debug("kandungan 12 " + kandungan);
//            if(kandungan==null)
//                kandungan = "TIADA DATA";
            permohonanKertasKandungan12.setKandungan(kandungan);
            permohonanKertasKandungan12.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan12.setSubtajuk("1." + i);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan12);
        }

        senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 21);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        logger.debug("kira 1 " + kira1);
        for (int i = 1; i <= kira1; i++) {
            if (senaraiKertasKandungan21.size() != 0 && i <= senaraiKertasKandungan21.size()) {
                Long id = senaraiKertasKandungan21.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan1.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan1.setInfoAudit(iaP);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(21);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            logger.debug("kandungan 1 " + kandungan);
//            if(kandungan==null)
//                kandungan = "TIADA DATA";
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);

            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKertasKandungan22 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 22);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        logger.debug("kira 2 " + kira2);
        for (int y = 1; y <= kira2; y++) {
            if (senaraiKertasKandungan22.size() != 0 && y <= senaraiKertasKandungan22.size()) {
                Long id = senaraiKertasKandungan22.get(y - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan2.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan2.setInfoAudit(iaP);
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(22);
            kandungan = getContext().getRequest().getParameter("kandungan2" + y);
            logger.debug("kandungan 2 " + kandungan);
//            if(kandungan==null)
//                kandungan = "TIADA DATA";
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2.2." + y);

            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        senaraiKertasKandungan23 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 23);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        logger.debug("kira 3 " + kira3);
        for (int z = 1; z <= kira3; z++) {
            if (senaraiKertasKandungan23.size() != 0 && z <= senaraiKertasKandungan23.size()) {
                Long id = senaraiKertasKandungan23.get(z - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan3.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan3.setInfoAudit(iaP);
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(23);
            kandungan = getContext().getRequest().getParameter("kandungan3" + z);
            logger.debug("kandungan 3 " + kandungan);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("2.3." + z);

            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }

        senaraiKertasKandungan24 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 24);
        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
        logger.debug("kira 4 " + kira4);
        for (int x = 1; x <= kira4; x++) {
            if (senaraiKertasKandungan24.size() != 0 && x <= senaraiKertasKandungan24.size()) {
                Long id = senaraiKertasKandungan24.get(x - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan4.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan4.setInfoAudit(iaP);
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(24);
            kandungan = getContext().getRequest().getParameter("kandungan4" + x);
            logger.debug("kandungan 4 " + kandungan);
            permohonanKertasKandungan4.setKandungan(kandungan);
            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan4.setSubtajuk("2.4." + x);

            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
        }

        senaraiKertasKandungan9 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira9 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
        for (int w = 1; w <= kira9; w++) {
            if (senaraiKertasKandungan9.size() != 0 && w <= senaraiKertasKandungan9.size()) {
                Long id = senaraiKertasKandungan9.get(w - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan9 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan9.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan9 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan9.setInfoAudit(iaP);
            }
            permohonanKertasKandungan9.setKertas(permohonanKertas);
            permohonanKertasKandungan9.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan9" + w);
            logger.debug("kandungan 9 " + kandungan);
            permohonanKertasKandungan9.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan9.setCawangan(cawangan);
            permohonanKertasKandungan9.setSubtajuk("3." + w);

            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan9);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        for (int w = 1; w <= kira; w++) {
            if (senaraiKertasKandungan3.size() != 0 && w <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(w - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan5.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan5.setInfoAudit(iaP);
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(4);
            kandungan = getContext().getRequest().getParameter("kandungan5" + w);
            logger.debug("kandungan 5 " + kandungan);
            permohonanKertasKandungan5.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan5.setCawangan(cawangan);
            permohonanKertasKandungan5.setSubtajuk("4." + w);

            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
        }

        senaraiKertasKandungan11 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 51);

        int kira11 = Integer.parseInt(getContext().getRequest().getParameter("rowCount11"));
        for (int j = 1; j <= kira11; j++) {
            if (senaraiKertasKandungan11.size() != 0 && j <= senaraiKertasKandungan11.size()) {
                Long id = senaraiKertasKandungan11.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan11 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan11.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan11 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan11.setInfoAudit(iaP);
            }
            permohonanKertasKandungan11.setKertas(permohonanKertas);
            permohonanKertasKandungan11.setBil(51);
            kandungan = getContext().getRequest().getParameter("kandungan11" + j);
            logger.debug("kandungan 11 " + kandungan);
            permohonanKertasKandungan11.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan11.setCawangan(cawangan);
            permohonanKertasKandungan11.setSubtajuk("5.1." + j);

            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan11);
        }

        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 7);
        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount13"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan6.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan6.setInfoAudit(iaP);
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(7);
            kandungan = getContext().getRequest().getParameter("kandungan13" + j);
            logger.debug("kandungan 13 " + kandungan);
            permohonanKertasKandungan6.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan6.setCawangan(cawangan);
            permohonanKertasKandungan6.setSubtajuk("7." + j);

            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
        }

        senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 61);

        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        for (int j = 1; j <= kira6; j++) {
            if (senaraiKertasKandungan6.size() != 0 && j <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan7 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    InfoAudit iap = permohonanKertasKandungan7.getInfoAudit();
                    iap.setDiKemaskiniOleh(peng);
                    iap.setTarikhKemaskini(new Date());
                }
            } else {
                permohonanKertasKandungan7 = new PermohonanKertasKandungan();
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan7.setInfoAudit(iaP);
            }
            permohonanKertasKandungan7.setKertas(permohonanKertas);
            permohonanKertasKandungan7.setBil(61);
            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
            logger.debug("kandungan 7 " + kandungan);
            permohonanKertasKandungan7.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan7.setCawangan(cawangan);
            permohonanKertasKandungan7.setSubtajuk("6.1." + j);

            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan7);
        }

        if (ulaspt != null) {
            pKKUlaspt = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
            if (pKKUlaspt != null) {
                pKKUlaspt.setBil(5);
                pKKUlaspt.setKandungan(ulaspt);
                InfoAudit iap = pKKUlaspt.getInfoAudit();
                iap.setDiKemaskiniOleh(peng);
                iap.setTarikhKemaskini(new Date());
                permohonanKertasKandunganDAO.saveOrUpdate(pKKUlaspt);
            }
        }
        if (akuanpt != null) {

            pKKakuanpt = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
            if (pKKakuanpt != null) {


                pKKakuanpt.setKandungan(akuanpt);
                InfoAudit iap = pKKakuanpt.getInfoAudit();
                iap.setDiKemaskiniOleh(peng);
                iap.setTarikhKemaskini(new Date());
                permohonanKertasKandunganDAO.saveOrUpdate(pKKakuanpt);
            }
        }
//senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 61);
//
//        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//        for (int j = 1; j <= kira5; j++) {
//            if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
//                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan6.setKertas(permohonanKertas);
//            permohonanKertasKandungan6.setBil(61);
//            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
//            logger.debug("kandungan 6 " + kandungan);
//            permohonanKertasKandungan6.setKandungan(kandungan);
//            cawangan = permohonan.getCawangan();
//            permohonanKertasKandungan6.setCawangan(cawangan);
//            permohonanKertasKandungan6.setSubtajuk("6.1." + j);
//            Info
//
//        count4 = Integer.parseInt(getContext().getRequest().getParameter("count4"));
//
//        for (int b = 1; b <= count4; b++) {
//            String subtajuk = "7." + b;
//            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 7, subtajuk);
//            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count4" + b));
//            for (int k = 1; k <= rowCount5; k++) {
//                if (senaraiKertasKandungan5 != null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
//                    Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                    }
//                } else {
//                    permohonanKertasKandungan8 = new PermohonanKertasKandungan();
//                }
//
//                permohonanKertasKandungan8.setKertas(permohonanKertas);
//                permohonanKertasKandungan8.setBil(5);
//                kandungan = getContext().getRequest().getParameter("perakuanPengarah" + b + k);
//                if (kandungan == null) {
//                    permohonanKertasKandungan8.setKandungan("Tiada");
//                } else {
//                    permohonanKertasKandungan8.setKandungan(kandungan);
//                }
//                cawangan = permohonan.getCawangan();
//                permohonanKertasKandungan8.setCawangan(cawangan);
//                permohonanKertasKandungan8.setSubtajuk("7." + b + "." + str[k - 1]);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                permohonanKertasKandungan8.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
//            }
//        }




//        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
    }

    public InfoAudit setInfoAudit() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = penggunaDAO.findById("portal");
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        return ia;
    }

    public Resolution simpanPTG() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }


        count4 = Integer.parseInt(getContext().getRequest().getParameter("count4"));

        for (int i = 1; i <= count4; i++) {
            String subtajuk = "7." + i;
            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 7, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count4" + i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan5 != null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
                    Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan8 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan8.setKertas(permohonanKertas);
                permohonanKertasKandungan8.setBil(7);
                kandungan = getContext().getRequest().getParameter("perakuanPengarah" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan8.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan8.setKandungan(kandungan);
                }
                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan8.setCawangan(cawangan);
                permohonanKertasKandungan8.setSubtajuk("7." + i + "." + str[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan8.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
            }
        }


        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
            if (kertasBil == null) {
                kertasBil = new PermohonanKertasKandungan();

            }
            kertasBil.setKertas(permohonanKertas);
            kertasBil.setBil(11);
            kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
            kertasBil.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            kertasBil.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kertasBil.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
        }

        if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
            if (kertasTahun == null) {
                kertasTahun = new PermohonanKertasKandungan();

            }
            kertasTahun.setKertas(permohonanKertas);
            kertasTahun.setBil(10);
            kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
            kertasTahun.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            kertasTahun.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kertasTahun.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
        }



        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
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
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);
            logger.debug("=====deleted=======");
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String form1 = getContext().getRequest().getParameter("form1");
        if (form1.equals("true")) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        }
        String formPtg = getContext().getRequest().getParameter("formPtg");
        if (formPtg.equals("true")) {
            getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        }

        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle2() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try {
            permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        } catch (Exception e) {
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan1 != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan1.setInfoAudit(ia);
            permohonanKertasKandungan1.setCawangan(cawangan);
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(etanah.view.stripes.pengambilan.MMKNeTappActionBean.class, "locate");
    }

    public Resolution deleteJabatanTeknikal() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idKandungan1 = getContext().getRequest().getParameter("idKandungan1");
        try {
            permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan1));
        } catch (Exception e) {
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan1 != null) {
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }


        String idKandungan2 = getContext().getRequest().getParameter("idKandungan2");
        try {
            permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan2));
        } catch (Exception e) {
            logger.debug("Hapus empty record");
        }
        if (permohonanKertasKandungan1 != null) {
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }



        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(MMKNeTappActionBean.class, "locate");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));
        logger.debug(":: masuk delete table :: ");
        logger.debug("bil " + bil);
        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), bil);
        logger.debug(":: kandList :: " + kandList.size());

        if (kandList != null && !kandList.isEmpty()) {
            logger.debug(":: kandList x null:: ");
            PermohonanKertasKandungan maxKertasKand = kandList.get(0);
            if (maxKertasKand != null) {
                logger.debug(":: maxKertasKand x null:: ");
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
//       getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        String form1 = getContext().getRequest().getParameter("form1");
        if (form1.equals("true")) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        }
        String formPtg = getContext().getRequest().getParameter("formPtg");
        if (formPtg.equals("true")) {
            getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        }
        return new JSP("pengambilan/MMKNMyeTapp.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable2() {

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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(MMKNeTappActionBean.class, "locate");

    }

    public Resolution ulasanKpptPt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");

        idKertas = permohonanKertas.getIdKertas();
        PermohonanKertasKandungan ulasanKppt = ambilService.findMMKdrafKandNew1(idKertas, 9);
        PermohonanKertasKandungan ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

        if (ulasanKppt != null) {
            Kppt = ulasanKppt.getKandungan();
        } else {
            Kppt = "";
        }

        if (ulasanPt != null) {
            Pt = ulasanPt.getKandungan();
        } else {
            Pt = "";
        }

        return new JSP("pengambilan/ulasan_Kppt_Pt.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKppt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKertas = permohonanKertas.getIdKertas();
        PermohonanKertasKandungan ulasanKppt = ambilService.findMMKdrafKandNew1(idKertas, 9);

        PermohonanKertasKandungan kppt;
        if (ulasanKppt != null) {
            kppt = permohonanKertasKandunganDAO.findById(ulasanKppt.getIdKandungan());
            InfoAudit iaP = kppt.getInfoAudit();
            iaP.setTarikhKemaskini(new Date());
            iaP.setDiKemaskiniOleh(peng);
            kppt.setInfoAudit(iaP);
        } else {
            kppt = new PermohonanKertasKandungan();
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            kppt.setInfoAudit(iaP);
        }

        kppt.setKandungan(Kppt);
        kppt.setBil(9);
        kppt.setCawangan(permohonan.getCawangan());
        kppt.setKertas(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(kppt);

        return ulasanKpptPt();
    }

    public Resolution simpanPt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MMKN");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKertas = permohonanKertas.getIdKertas();
        PermohonanKertasKandungan ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

        PermohonanKertasKandungan pt;
        if (ulasanPt != null) {
            pt = permohonanKertasKandunganDAO.findById(ulasanPt.getIdKandungan());
            InfoAudit iaP = pt.getInfoAudit();
            iaP.setTarikhKemaskini(new Date());
            iaP.setDiKemaskiniOleh(peng);
            pt.setInfoAudit(iaP);
        } else {
            pt = new PermohonanKertasKandungan();
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            pt.setInfoAudit(iaP);
        }

        pt.setKandungan(Pt);
        pt.setBil(10);
        pt.setCawangan(permohonan.getCawangan());
        pt.setKertas(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(pt);

        return ulasanKpptPt();
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

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
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

    public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List[] getSenaraiSyorPengarah() {
        return senaraiSyorPengarah;
    }

    public void setSenaraiSyorPengarah(List[] senaraiSyorPengarah) {
        this.senaraiSyorPengarah = senaraiSyorPengarah;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan6() {
        return permohonanKertasKandungan6;
    }

    public void setPermohonanKertasKandungan6(PermohonanKertasKandungan permohonanKertasKandungan6) {
        this.permohonanKertasKandungan6 = permohonanKertasKandungan6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan6() {
        return senaraiKertasKandungan6;
    }

    public void setSenaraiKertasKandungan6(List<PermohonanKertasKandungan> senaraiKertasKandungan6) {
        this.senaraiKertasKandungan6 = senaraiKertasKandungan6;
    }

    public PermohonanKertasKandungan getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(PermohonanKertasKandungan kertasBil) {
        this.kertasBil = kertasBil;
    }

    public PermohonanKertasKandungan getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(PermohonanKertasKandungan kertasTahun) {
        this.kertasTahun = kertasTahun;
    }

    public PermohonanKertasKandungan getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(PermohonanKertasKandungan tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public int getCount8() {
        return count8;
    }

    public void setCount8(int count8) {
        this.count8 = count8;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan8() {
        return permohonanKertasKandungan8;
    }

    public void setPermohonanKertasKandungan8(PermohonanKertasKandungan permohonanKertasKandungan8) {
        this.permohonanKertasKandungan8 = permohonanKertasKandungan8;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan8() {
        return senaraiKertasKandungan8;
    }

    public void setSenaraiKertasKandungan8(List<PermohonanKertasKandungan> senaraiKertasKandungan8) {
        this.senaraiKertasKandungan8 = senaraiKertasKandungan8;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public StringBuffer getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(StringBuffer noLotList) {
        this.noLotList = noLotList;
    }

    public PermohonanKertasKandungan getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(PermohonanKertasKandungan ulasan1) {
        this.ulasan1 = ulasan1;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getNamaSeksyen() {
        return namaSeksyen;
    }

    public void setNamaSeksyen(String namaSeksyen) {
        this.namaSeksyen = namaSeksyen;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan21() {
        return senaraiKertasKandungan21;
    }

    public void setSenaraiKertasKandungan21(List<PermohonanKertasKandungan> senaraiKertasKandungan21) {
        this.senaraiKertasKandungan21 = senaraiKertasKandungan21;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan22() {
        return senaraiKertasKandungan22;
    }

    public void setSenaraiKertasKandungan22(List<PermohonanKertasKandungan> senaraiKertasKandungan22) {
        this.senaraiKertasKandungan22 = senaraiKertasKandungan22;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan23() {
        return senaraiKertasKandungan23;
    }

    public void setSenaraiKertasKandungan23(List<PermohonanKertasKandungan> senaraiKertasKandungan23) {
        this.senaraiKertasKandungan23 = senaraiKertasKandungan23;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan24() {
        return senaraiKertasKandungan24;
    }

    public void setSenaraiKertasKandungan24(List<PermohonanKertasKandungan> senaraiKertasKandungan24) {
        this.senaraiKertasKandungan24 = senaraiKertasKandungan24;
    }

    public List[] getSenaraiPerakuanPengarah() {
        return senaraiPerakuanPengarah;
    }

    public void setSenaraiPerakuanPengarah(List[] senaraiPerakuanPengarah) {
        this.senaraiPerakuanPengarah = senaraiPerakuanPengarah;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PengambilanMMKService getAmbilService() {
        return ambilService;
    }

    public void setAmbilService(PengambilanMMKService ambilService) {
        this.ambilService = ambilService;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan9() {
        return permohonanKertasKandungan9;
    }

    public void setPermohonanKertasKandungan9(PermohonanKertasKandungan permohonanKertasKandungan9) {
        this.permohonanKertasKandungan9 = permohonanKertasKandungan9;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan11() {
        return permohonanKertasKandungan11;
    }

    public void setPermohonanKertasKandungan11(PermohonanKertasKandungan permohonanKertasKandungan11) {
        this.permohonanKertasKandungan11 = permohonanKertasKandungan11;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan12() {
        return permohonanKertasKandungan12;
    }

    public void setPermohonanKertasKandungan12(PermohonanKertasKandungan permohonanKertasKandungan12) {
        this.permohonanKertasKandungan12 = permohonanKertasKandungan12;
    }

    public PermohonanKertasKandungan getNoRujukanMMKN() {
        return noRujukanMMKN;
    }

    public void setNoRujukanMMKN(PermohonanKertasKandungan noRujukanMMKN) {
        this.noRujukanMMKN = noRujukanMMKN;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan9() {
        return senaraiKertasKandungan9;
    }

    public void setSenaraiKertasKandungan9(List<PermohonanKertasKandungan> senaraiKertasKandungan9) {
        this.senaraiKertasKandungan9 = senaraiKertasKandungan9;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan11() {
        return senaraiKertasKandungan11;
    }

    public void setSenaraiKertasKandungan11(List<PermohonanKertasKandungan> senaraiKertasKandungan11) {
        this.senaraiKertasKandungan11 = senaraiKertasKandungan11;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan12() {
        return senaraiKertasKandungan12;
    }

    public void setSenaraiKertasKandungan12(List<PermohonanKertasKandungan> senaraiKertasKandungan12) {
        this.senaraiKertasKandungan12 = senaraiKertasKandungan12;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

//    public StringBuffer getDaerah() {
//        return daerah;
//    }
//
//    public void setDaerah(StringBuffer daerah) {
//        this.daerah = daerah;
//    }
    public EtappPermohonan getE() {
        return e;
    }

    public void setE(EtappPermohonan e) {
        this.e = e;
    }

    public TBLPPTINTDERAFMMKTAJUK getMmkTjk() {
        return mmkTjk;
    }

    public void setMmkTjk(TBLPPTINTDERAFMMKTAJUK mmkTjk) {
        this.mmkTjk = mmkTjk;
    }

    public List<PermohonanKertasKandungan> getKandList() {
        return kandList;
    }

    public void setKandList(List<PermohonanKertasKandungan> kandList) {
        this.kandList = kandList;
    }

    public List<TBLINTPPTDERAFMMK> getTujuanList() {
        return tujuanList;
    }

    public void setTujuanList(List<TBLINTPPTDERAFMMK> tujuanList) {
        this.tujuanList = tujuanList;
    }

    public List<TBLINTPPTDERAFMMK> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<TBLINTPPTDERAFMMK> permohonanList) {
        this.permohonanList = permohonanList;
    }

    public List<TBLINTPPTDERAFMMK> getTanahList() {
        return tanahList;
    }

    public void setTanahList(List<TBLINTPPTDERAFMMK> tanahList) {
        this.tanahList = tanahList;
    }

    public List<TBLINTPPTDERAFMMK> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<TBLINTPPTDERAFMMK> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public List<TBLINTPPTDERAFMMK> getUlasanList() {
        return ulasanList;
    }

    public void setUlasanList(List<TBLINTPPTDERAFMMK> ulasanList) {
        this.ulasanList = ulasanList;
    }

    public List<TBLINTPPTDERAFMMK> getYBList() {
        return YBList;
    }

    public void setYBList(List<TBLINTPPTDERAFMMK> YBList) {
        this.YBList = YBList;
    }

    public List<TBLINTPPTDERAFMMK> getPandanganPT() {
        return pandanganPT;
    }

    public void setPandanganPT(List<TBLINTPPTDERAFMMK> pandanganPT) {
        this.pandanganPT = pandanganPT;
    }

    public List<TBLINTPPTDERAFMMK> getPerakuanPT() {
        return PerakuanPT;
    }

    public void setPerakuanPT(List<TBLINTPPTDERAFMMK> PerakuanPT) {
        this.PerakuanPT = PerakuanPT;
    }

    public List<TBLINTPPTDERAFMMK> getPerakuanPTG() {
        return PerakuanPTG;
    }

    public void setPerakuanPTG(List<TBLINTPPTDERAFMMK> PerakuanPTG) {
        this.PerakuanPTG = PerakuanPTG;
    }

    public List<TBLINTPPTDERAFMMK> getPampasanList() {
        return pampasanList;
    }

    public void setPampasanList(List<TBLINTPPTDERAFMMK> pampasanList) {
        this.pampasanList = pampasanList;
    }

    public List<TBLINTPPTDERAFMMK> getPandanganPT2() {
        return pandanganPT2;
    }

    public void setPandanganPT2(List<TBLINTPPTDERAFMMK> pandanganPT2) {
        this.pandanganPT2 = pandanganPT2;
    }

    public List<TBLINTPPTDERAFMMK> getPerakuanPT2() {
        return perakuanPT2;
    }

    public void setPerakuanPT2(List<TBLINTPPTDERAFMMK> perakuanPT2) {
        this.perakuanPT2 = perakuanPT2;
    }

    public List<PermohonanKertasKandungan> getTujuanList1() {
        return tujuanList1;
    }

    public void setTujuanList1(List<PermohonanKertasKandungan> tujuanList1) {
        this.tujuanList1 = tujuanList1;
    }

    public List<PermohonanKertasKandungan> getPermohonanList1() {
        return permohonanList1;
    }

    public void setPermohonanList1(List<PermohonanKertasKandungan> permohonanList1) {
        this.permohonanList1 = permohonanList1;
    }

    public List<PermohonanKertasKandungan> getTanahList1() {
        return tanahList1;
    }

    public void setTanahList1(List<PermohonanKertasKandungan> tanahList1) {
        this.tanahList1 = tanahList1;
    }

    public List<PermohonanKertasKandungan> getPemohonList1() {
        return pemohonList1;
    }

    public void setPemohonList1(List<PermohonanKertasKandungan> pemohonList1) {
        this.pemohonList1 = pemohonList1;
    }

    public List<PermohonanKertasKandungan> getUlasanList1() {
        return ulasanList1;
    }

    public void setUlasanList1(List<PermohonanKertasKandungan> ulasanList1) {
        this.ulasanList1 = ulasanList1;
    }

    public List<PermohonanKertasKandungan> getYBList1() {
        return YBList1;
    }

    public void setYBList1(List<PermohonanKertasKandungan> YBList1) {
        this.YBList1 = YBList1;
    }

    public List<PermohonanKertasKandungan> getPerakuanPT1() {
        return perakuanPT1;
    }

    public void setPerakuanPT1(List<PermohonanKertasKandungan> perakuanPT1) {
        this.perakuanPT1 = perakuanPT1;
    }

    public List<PermohonanKertasKandungan> getPerakuanPTG1() {
        return perakuanPTG1;
    }

    public void setPerakuanPTG1(List<PermohonanKertasKandungan> perakuanPTG1) {
        this.perakuanPTG1 = perakuanPTG1;
    }

    public List<PermohonanKertasKandungan> getPampasanList1() {
        return pampasanList1;
    }

    public void setPampasanList1(List<PermohonanKertasKandungan> pampasanList1) {
        this.pampasanList1 = pampasanList1;
    }

    public List<PermohonanKertasKandungan> getPandanganPT1() {
        return pandanganPT1;
    }

    public void setPandanganPT1(List<PermohonanKertasKandungan> pandanganPT1) {
        this.pandanganPT1 = pandanganPT1;
    }

    public PermohonanKertasKandungan getPandanganPT21() {
        return pandanganPT21;
    }

    public void setPandanganPT21(PermohonanKertasKandungan pandanganPT21) {
        this.pandanganPT21 = pandanganPT21;
    }

    public PermohonanKertasKandungan getPerakuanPT21() {
        return perakuanPT21;
    }

    public void setPerakuanPT21(PermohonanKertasKandungan perakuanPT21) {
        this.perakuanPT21 = perakuanPT21;
    }

    public long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(long idKertas) {
        this.idKertas = idKertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan7() {
        return permohonanKertasKandungan7;
    }

    public void setPermohonanKertasKandungan7(PermohonanKertasKandungan permohonanKertasKandungan7) {
        this.permohonanKertasKandungan7 = permohonanKertasKandungan7;
    }

    public PermohonanKertasKandungan getpKKUlaspt() {
        return pKKUlaspt;
    }

    public void setpKKUlaspt(PermohonanKertasKandungan pKKUlaspt) {
        this.pKKUlaspt = pKKUlaspt;
    }

    public PermohonanKertasKandungan getpKKakuanpt() {
        return pKKakuanpt;
    }

    public void setpKKakuanpt(PermohonanKertasKandungan pKKakuanpt) {
        this.pKKakuanpt = pKKakuanpt;
    }

    public String getUlaspt() {
        return ulaspt;
    }

    public void setUlaspt(String ulaspt) {
        this.ulaspt = ulaspt;
    }

    public String getAkuanpt() {
        return akuanpt;
    }

    public void setAkuanpt(String akuanpt) {
        this.akuanpt = akuanpt;
    }

    public String getTotalLot() {
        return totalLot;
    }

    public void setTotalLot(String totalLot) {
        this.totalLot = totalLot;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public String getKppt() {
        return Kppt;
    }

    public void setKppt(String Kppt) {
        this.Kppt = Kppt;
    }

    public String getPt() {
        return Pt;
    }

    public void setPt(String Pt) {
        this.Pt = Pt;
    }
}
