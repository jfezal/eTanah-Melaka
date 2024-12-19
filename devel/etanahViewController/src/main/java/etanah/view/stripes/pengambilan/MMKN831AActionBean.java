/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import etanah.dao.*;
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
import etanah.model.PermohonanPihak;

import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import etanah.service.PendudukanSementaraMMKNService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Locale;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.commons.lang.WordUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/mmkn831a")
public class MMKN831AActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MMKN831AActionBean.class);
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
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private PermohonanKertas permohonanKertas;
    private String kertasBil;
    private String kertasTahun;
    private String tempat;
    private String tarikhmesyuarat;
    private PermohonanKertasKandungan jawatanKuasa;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanKertasKandungan ringkasanPermohonan;
    private PermohonanKertasKandungan syorPengarah;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanPihak penerima;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan7;
    private Hakmilik hakmilik;
    private String tujuan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    SimpleDateFormat sdfx = new SimpleDateFormat("dd/MM/yyyy");
    private int count4 = 0;
    private int count2 = 0;
    private int count5 = 0;
    private int count5a = 0;
    private int count6 = 0;
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String strB[] = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"};
    private List senaraiPerakuanPengarah[] = new ArrayList[10];
    private List senaraiSyorPengarah[] = new ArrayList[10];
    private List senarailatar[] = new ArrayList[10];
    private List senaraiPentadbir[] = new ArrayList[10];
    private List senaraiPentadbir3[] = new ArrayList[10];
    private List senaraiPentadbir6[] = new ArrayList[10];
    private List senaraiNilaianTanah[] = new ArrayList[10];
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String heading2;
    private String lokasi;
    private String mesyuaratBil;
    private String noRujukan;
    private String MesyKhas = "T";
    String namaProjek;
    BigDecimal totalLuas = new BigDecimal(0.00);
    private List<PermohonanPihak> listPP;
    private String namaPengadu;
    private String tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 831A";
    String lokasi1;
    String lokasi2;
    private String NamaDaerah;
    private String kodUrusan;
    Hakmilik hakmilik1 = new Hakmilik();
    private String urusan;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        MesyKhas = "T";
        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        MesyKhas = "T";
        tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b)";
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        MesyKhas = "T";
        tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b)";
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        System.out.println(" Khas >>>>>>>>> ");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        MesyKhas = "Y";
        tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b) - Jawatankuasa Khas";
        return new JSP("pengambilan/negerisembilan/seksyen831bc/mmkn_831bc_N9.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831bc/mesyKerajaan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = "";
        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        System.out.println(" MEsyt Khas >>>>>>>>>>>>> " + MesyKhas);
        if (idSblm != null) {
            Permohonan p = permohonanDAO.findById(idSblm);
            permohonan = permohonanDAO.findById(idPermohonan);

            for (int k = 1; k <= count4; k++) {
                senaraiPerakuanPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
            }
            count4 = 0;

            String pem = "";
            StringBuffer noLotList = new StringBuffer();
            totalLuas = BigDecimal.ZERO;
            BigDecimal luas = BigDecimal.ZERO;
            String uom = "Meter Persegi";

            if (permohonan != null) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                HakmilikPermohonan hp2 = new HakmilikPermohonan();


                listPemohon = permohonan.getSenaraiPemohon();
                if (listPemohon != null && listPemohon.size() > 0 && listPemohon.get(0).getPihak() != null) {
                    pem = listPemohon.get(0).getPihak().getNama();
                }
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                noLotList = new StringBuffer();
                for (HakmilikPermohonan hp1 : hakmilikPermohonanList) {
                    noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ");
                }


                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    luas = BigDecimal.ZERO;
                    luas = hakmilikPermohonan.getLuasTerlibat();
                    String kodUOM = "";
                    if (hakmilikPermohonan.getKodUnitLuas() != null) {
                        kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();
                    }
                    if (kodUOM.equals("Hektar")) {
                        totalLuas = totalLuas.add(luas);
                        uom = "Hektar";
                    } else if (kodUOM.equals("Meter Persegi")) {
                        totalLuas = totalLuas.add(luas);
                    }
                }

                if (totalLuas.compareTo(new BigDecimal(10000)) == 1) {
                    totalLuas = totalLuas.divide(new BigDecimal(10000));
                    uom = "Hektar";
                }


                List<PermohonanPihak> listPenerima;
                listPenerima = permohonan.getSenaraiPihak();

                if (!(listPenerima.isEmpty())) {
                    penerima = listPenerima.get(0);
                }
                String daerah = "";
                if (!hakmilikPermohonanList.isEmpty()) {
                    for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                        hp = hakmilikPermohonanList.get(w);
                        if (hp.getHakmilik() != null) {
                            hakmilik = hp.getHakmilik();
//                        StringBuilder sb = new StringBuilder();

                            if (w == 0) {
                                lokasi = hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ", "
                                        + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + " ";
                            }

                            if (w > 0) {
                                if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                                    lokasi = lokasi + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ", "
                                            + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + " ";
                                } else if (w == (hakmilikPermohonanList.size() - 1)) {
                                    lokasi = lokasi + " dan " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getLuas() + ","
                                            + hakmilik.getBandarPekanMukim().getNama() + ", DAERAH " + hakmilik.getDaerah().getNama() + ", ";
                                }
                            }
                            daerah = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama()) + " " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama());
                            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A")) {
                                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Yang Amat Berhormat Menteri Besar, Negeri Sembilan ke atas permohonan pengambilan tanah "
                                        + "Seksyen 3(1)(a) Akta Pengambilan Tanah 1960, Projek " + WordUtils.capitalizeFully(permohonan.getSebab()) + " daripada " + WordUtils.capitalizeFully(pem.toLowerCase()) + " " + noLotList + "Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas lebih kurang " + " " + totalLuas + " " + uom + ". "
                                        + "Kawasan yang dimaksudkan adalah seperti bertanda merah dalam pelan di bil.(11A) dlm.PTS 362/101/2010/04.";
                            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan, Negeri Sembilan ke atas permohonan pengambilan tanah "
                                        + "Seksyen 3(1)(b) Akta Pengambilan Tanah 1960, Projek " + WordUtils.capitalizeFully(permohonan.getSebab()) + " daripada " + WordUtils.capitalizeFully(pem.toLowerCase()) + " " + noLotList + "Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas lebih kurang " + " " + totalLuas + " " + uom + ". "
                                        + "Kawasan yang dimaksudkan adalah seperti bertanda merah dalam pelan di bil.(11A) dlm.PTS 362/101/2010/04.";
                            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan ke atas permohonan pengambilan tanah "
                                        + "Seksyen 3(1)(c) Akta Pengambilan Tanah 1960, Projek " + WordUtils.capitalizeFully(permohonan.getSebab()) + " daripada " + WordUtils.capitalizeFully(pem.toLowerCase()) + " " + noLotList + "Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas lebih kurang " + " " + totalLuas + " " + uom + ". "
                                        + "Kawasan yang dimaksudkan adalah seperti bertanda merah dalam pelan di bil.(11A) dlm.PTS 362/101/2010/04.";
                            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ESK8")) {
                                tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Majlis Mesyuarat Kerajaan Negeri, Negeri Sembilan ke atas permohonan pengambilan tanah "
                                        + "Seksyen 8 Akta Pengambilan Tanah 1960, Projek " + WordUtils.capitalizeFully(permohonan.getSebab()) + " daripada " + WordUtils.capitalizeFully(pem.toLowerCase()) + " " + noLotList + "Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + " seluas lebih kurang " + " " + totalLuas + " " + uom + ". "
                                        + "Kawasan yang dimaksudkan adalah seperti bertanda merah dalam pelan di bil.(11A) dlm.PTS 362/101/2010/04.";
                            }
                        }
                    }

                    if (hakmilikPermohonanList.size() <= 3) {
                        for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                            hp = permohonan.getSenaraiHakmilik().get(w);
                            if (hp.getHakmilik() != null) {
                                if (hakmilikPermohonanList.size() > (w + 1)) {
                                    hp2 = permohonan.getSenaraiHakmilik().get(w + 1);
                                    hakmilik1 = hakmilikDAO.findById(hp2.getHakmilik().getIdHakmilik());
                                }
                                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                                hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                                if (permohonan != null) {

                                    if (permohonan.getPermohonanSebelum() != null) {
                                        kodUrusan = permohonan.getPermohonanSebelum().getKodUrusan().getKod();
                                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831A")) {
                                            urusan = "3(1)(a)";
                                        }
                                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                                            urusan = "3(1)(b)";
                                        }
                                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                                            urusan = "3(1)(c)";
                                        }
                                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("ESK8")) {
                                            urusan = "Pengambilan Tanah Di bawah Seksyen 8";
                                        }
                                        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("SEK4")) {
                                            urusan = "Pengambilan Tanah Di bawah Seksyen 4";
                                        }
                                    }
                                }

                                NamaDaerah = WordUtils.capitalizeFully(hakmilik.getDaerah().getNama());

                                if (w == 0) {

                                    lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

                                    if (hakmilikPermohonanList.size() > (w + 1)) {
                                        if (!hakmilik.getBandarPekanMukim().getNama().equals(hakmilik1.getBandarPekanMukim().getNama())) {
                                            String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                            lokasi = lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                        } else {
                                            lokasi = lokasi1;
                                        }

                                    } else {
                                        String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                        lokasi = lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                    }
                                }
                                if (w > 0) {
                                    if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                                        lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

                                        if (hakmilikPermohonanList.size() > (w + 1)) {
                                            if (!hakmilik.getBandarPekanMukim().getNama().equals(hakmilik1.getBandarPekanMukim().getNama())) {
                                                String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                                lokasi = lokasi + ",  " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                            } else {
                                                lokasi = lokasi + ", " + lokasi1 + " ";
                                            }
                                        } else {
                                            String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                            lokasi = lokasi + ", " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                        }

                                    } else if (w == (hakmilikPermohonanList.size() - 1)) {
                                        lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + " " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

                                        if (hakmilikPermohonanList.size() > (w + 1)) {
                                            if (!hakmilik.getBandarPekanMukim().getNama().equals(hakmilik1.getBandarPekanMukim().getNama())) {
                                                String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                                lokasi = lokasi + " dan " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                            } else {
                                                lokasi = lokasi + " dan " + lokasi1;
                                            }
                                        } else {
                                            String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                            lokasi = lokasi + " dan " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        lokasi = " seperti di lampiran " + daerah;
                    }
                }
            }
            String noLotList2 = "";
            if (lokasi != null) {
                noLotList2 = " DI ";
            }
            System.out.println("noLotList : " + noLotList);
            String temp = "PERMOHONAN PENGAMBILAN TANAH DARIPADA " + pem + " SELUAS LEBIH KURANG " + totalLuas + " " + uom + " " + noLotList2 + lokasi + " BAGI TUJUAN " + permohonan.getSebab();

//        String temp = "PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT "+ lokasi + " BAGI " + permohonan.getSebab() + ".";
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831A")) {
                heading = temp.toUpperCase() + " DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960";
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                heading = temp.toUpperCase() + " DI BAWAH SEKSYEN 3(1)(b) AKTA PENGAMBILAN TANAH 1960";
                if (MesyKhas.equalsIgnoreCase("T")) {
                    tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b)";
                } else if (MesyKhas.equalsIgnoreCase("Y")) {
                    tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(b) - Jawatankuasa Khas";
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                heading = temp.toUpperCase() + " DI BAWAH SEKSYEN 3(1)(c) AKTA PENGAMBILAN TANAH 1960";
                if (MesyKhas.equalsIgnoreCase("T")) {
                    tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(c)";
                } else if (MesyKhas.equalsIgnoreCase("Y")) {
                    tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEKSYEN 3(1)(c) - Jawatankuasa Khas";
                }
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ESK8")) {
                heading = temp.toUpperCase() + " DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960";
            }
            if (idPermohonan != null) {
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan, tajuk);

                if (permohonanKertas != null) {
                    System.out.println("Tajuk Permohoann Kertas >>>>>>>>>>>>>> " + permohonanKertas.getTajuk());
                    tajuk = permohonanKertas.getTajuk();
                    noRujukan = permohonanKertas.getNomborRujukan();
                    if (noRujukan != null) {
                        if (noRujukan.length() > 0) {
                            kertasBil = noRujukan.substring(0, noRujukan.length() - 5);
                            kertasTahun = noRujukan.substring(noRujukan.length() - 4, noRujukan.length());
                        }
                    }
                    if (permohonanKertas.getTarikhSidang() != null) {
                        tarikhmesyuarat = sdfx.format(permohonanKertas.getTarikhSidang());
                    }
//                if(permohonanKertas.getTarikhSidang() != null) {
//                    String tarikhSidang = dateFormat.format(permohonanKertas.getTarikhSidang());
//                    tarikhmesyuarat = tarikhSidang.substring(0, 10);
//                    jam = tarikhSidang.substring(11, 13);
//                    minit = tarikhSidang.substring(14, 16);
//                    String ampm = tarikhSidang.substring(20, 22);
//                    if(ampm.equalsIgnoreCase("AM")){
//                        pagiPetang = "PAGI";
//                    }else if(ampm.equalsIgnoreCase("PM")){
//                        pagiPetang = "PETANG";
//                    }
//                }
//                tempat = permohonanKertas.getTempatSidang();

                    headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
                    tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                    if (tujuanObj != null) {
                        tujuan = tujuanObj.getKandungan().toString();
                    }

                    senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
                    senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);
                    //     senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 3, subtajuk);
                    senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
                    senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
                    senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);
                    // senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk);


                    List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
                    PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
                    kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 4);
                    if (kandList5 != null && !kandList5.isEmpty()) {
                        maxKertasKand5 = kandList5.get(0);
                        if (maxKertasKand5 != null) {
                            String subtajuk = maxKertasKand5.getSubtajuk();
                            String str = subtajuk.substring(2, 3);
                            int tableCount = Integer.parseInt(str);
                            count5 = tableCount;
                            for (int k = 1; k <= tableCount; k++) {
//                            for(int k=1;k<=kandList5.size();k++){
                                senaraiPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "4." + k;
                                senaraiPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk1);
                            }
                        }
                    }
                    /////////////  kandungan no3 
                    List<PermohonanKertasKandungan> kandList5a = new ArrayList<PermohonanKertasKandungan>();
                    PermohonanKertasKandungan maxKertasKand5a = new PermohonanKertasKandungan();
                    kandList5a = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 3);
                    logger.debug("kandList5a size >>>> " + kandList5a.size());
                    if (kandList5a != null && !kandList5a.isEmpty()) {
                        maxKertasKand5a = kandList5a.get(0);
                        if (maxKertasKand5a != null) {
                            String subtajuk = maxKertasKand5a.getSubtajuk();
                            String str = subtajuk.substring(2, 3);
                            int tableCount = Integer.parseInt(str);
                            count5a = tableCount;
                            for (int k = 1; k <= tableCount; k++) {
//                            for(int k=1;k<=kandList5.size();k++){
                                senaraiPentadbir3[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "3." + k;
                                senaraiPentadbir3[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 3, subtajuk1);
                            }
                        }
                    }
                    // new 6.1.1
                    List<PermohonanKertasKandungan> kandList6 = new ArrayList<PermohonanKertasKandungan>();
                    PermohonanKertasKandungan maxKertasKand6 = new PermohonanKertasKandungan();
                    kandList6 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 6);
                    if (kandList6 != null && !kandList6.isEmpty()) {
                        maxKertasKand6 = kandList6.get(0);
                        if (maxKertasKand6 != null) {
                            String subtajuk = maxKertasKand6.getSubtajuk();
                            String str = subtajuk.substring(2, 3);
                            int tableCount = Integer.parseInt(str);
                            count6 = tableCount;
                            for (int k = 1; k <= tableCount; k++) {
//                            for(int k=1;k<=kandList5.size();k++){
                                senaraiPentadbir6[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "6." + k;
                                senaraiPentadbir6[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk1);
                            }
                        }
                    }

                }
                if (kertasTahun == null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    kertasTahun = sdf.format(new java.util.Date()).toString();
                }
            }

        }
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan2);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MB");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);
        permohonanKertas.setTajuk(tajuk);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if (kertasBil == null) {
            kertasBil = "";
        }
        permohonanKertas.setNomborRujukan(kertasBil + "/" + kertasTahun);

        tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
        if (tarikhmesyuarat != null) {
            if (tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length() > 0) {
                String ampm = "";
                if (pagiPetang.equalsIgnoreCase("PAGI")) {
                    ampm = "AM";
                } else if (pagiPetang.equalsIgnoreCase("PETANG")) {
                    ampm = "PM";
                }
                String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
                Date tarikhSidang = dateFormat.parse(strMasa);
                permohonanKertas.setTarikhSidang(tarikhSidang);
            }
        }
        if (tempat == null) {
            tempat = "";
        }
        permohonanKertas.setTempatSidang(tempat);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                System.out.println("1");
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                System.out.println("2");
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(9);
            kandungan = getContext().getRequest().getParameter("heading");
            System.out.println("kandungan " + kandungan);
            if (kandungan == null) {
                System.out.println("3");
                headingObj.setKandungan("Tiada");
            } else {
                System.out.println("4");
                headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            System.out.println("caw" + permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if (kandungan == null) {
                tujuanObj.setKandungan("Tiada");
            } else {
                tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);

        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        System.out.println("kira2" + kira2);
        for (int j = 1; j <= kira2; j++) {
            if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
                if (id != null) {
                    System.out.println("senaraiKertasKandungan 21");
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                System.out.println("senaraiKertasKandungan 22");
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan2" + j);
            System.out.println("kandungan 2" + kandungan);
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
            //permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        for (int k = 1; k <= kira3; k++) {
            if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan3" + k);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("3." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            // permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }


        count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));

        for (int i = 1; i <= count5; i++) {
            String subtajuk = "4." + i;
            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan4 != null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan4.setKertas(permohonanKertas);
                permohonanKertasKandungan4.setBil(4);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan4.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan4.setKandungan(kandungan);
                }
                permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan4.setSubtajuk("4." + i + "." + str[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }
        }//if table count


//            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),5);
//
//            int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
//            for (int j = 1; j <= kira5; j++) {
//                if (senaraiKertasKandungan5.size() != 0 && j <= senaraiKertasKandungan5.size()) {
//                Long id = senaraiKertasKandungan5.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan5.setKertas(permohonanKertas);
//                    permohonanKertasKandungan5.setBil(5);
//                    kandungan = getContext().getRequest().getParameter("kandungan5" + j);
//                    permohonanKertasKandungan5.setKandungan(kandungan);
//                    permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan5.setSubtajuk("5." + j);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan5.setInfoAudit(iaP);
////                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
//            }
//            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
//
//            int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//            for (int j = 1; j <= kira6; j++) {
//                if (senaraiKertasKandungan6.size() != 0 && j <= senaraiKertasKandungan6.size()) {
//                Long id = senaraiKertasKandungan6.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan6.setKertas(permohonanKertas);
//                    permohonanKertasKandungan6.setBil(6);
//                    kandungan = getContext().getRequest().getParameter("kandungan6" + j);
//                    permohonanKertasKandungan6.setKandungan(kandungan);
//                    permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan6.setSubtajuk("6." + j);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan6.setInfoAudit(iaP);
////                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
//            }



        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan dan sila jana Dokumen kembali.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution simpanb() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan2);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKNS");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);
        permohonanKertas.setTajuk(tajuk);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if (kertasBil == null) {
            kertasBil = "";
        }
        permohonanKertas.setNomborRujukan(kertasBil + "/" + kertasTahun);

        tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
        jam = getContext().getRequest().getParameter("jam");
        minit = getContext().getRequest().getParameter("minit");
        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
        if (tarikhmesyuarat != null) {
            if (tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length() > 0) {
                String ampm = "";
                if (pagiPetang.equalsIgnoreCase("PAGI")) {
                    ampm = "AM";
                } else if (pagiPetang.equalsIgnoreCase("PETANG")) {
                    ampm = "PM";
                }
                String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
                Date tarikhSidang = dateFormat.parse(strMasa);
                permohonanKertas.setTarikhSidang(tarikhSidang);
            }
        }
        if (tempat == null) {
            tempat = "";
        }
        permohonanKertas.setTempatSidang(tempat);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                System.out.println("1");
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                System.out.println("2");
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(9);
            kandungan = getContext().getRequest().getParameter("heading");
            System.out.println("kandungan " + kandungan);
            if (kandungan == null) {
                System.out.println("3");
                headingObj.setKandungan("Tiada");
            } else {
                System.out.println("4");
                headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            System.out.println("caw" + permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if (kandungan == null) {
                tujuanObj.setKandungan("Tiada");
            } else {
                tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);

        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        System.out.println("kira2" + kira2);
        for (int j = 1; j <= kira2; j++) {
            if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
                if (id != null) {
                    System.out.println("senaraiKertasKandungan 21");
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                System.out.println("senaraiKertasKandungan 22");
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan2" + j);
            System.out.println("kandungan 2" + kandungan);
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
            //permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        for (int k = 1; k <= kira3; k++) {
            if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan3" + k);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("3." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            // permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }


        count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));

        for (int i = 1; i <= count5; i++) {
            String subtajuk = "4." + i;
            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan4 != null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan4.setKertas(permohonanKertas);
                permohonanKertasKandungan4.setBil(4);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan4.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan4.setKandungan(kandungan);
                }
                permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan4.setSubtajuk("4." + i + "." + str[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }
        }//if table count


        senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan5.size() != 0 && j <= senaraiKertasKandungan5.size()) {
                Long id = senaraiKertasKandungan5.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(5);
            kandungan = getContext().getRequest().getParameter("kandungan5" + j);
            System.out.println("Kandungan >>>>>>>>>> " + kandungan);
            if (kandungan == null) {
                permohonanKertasKandungan5.setKandungan("Tiada");
            } else {
                permohonanKertasKandungan5.setKandungan(kandungan);
            }
            permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan5.setSubtajuk("5." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
        }
//            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
//
//            int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//            for (int j = 1; j <= kira6; j++) {
//                if (senaraiKertasKandungan6.size() != 0 && j <= senaraiKertasKandungan6.size()) {
//                Long id = senaraiKertasKandungan6.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan6.setKertas(permohonanKertas);
//                    permohonanKertasKandungan6.setBil(6);
//                    kandungan = getContext().getRequest().getParameter("kandungan6" + j);
//                    permohonanKertasKandungan6.setKandungan(kandungan);
//                    permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan6.setSubtajuk("6." + j);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan6.setInfoAudit(iaP);
////                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
//            }



        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan dan sila jana Dokumen kembali.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831bc/mmkn_831bc_N9.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPTG() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan2);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MB");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);
        permohonanKertas.setTajuk(tajuk);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if (kertasBil == null) {
            kertasBil = "";
        }
        permohonanKertas.setNomborRujukan(kertasBil + "/" + kertasTahun);

//        tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
//        jam = getContext().getRequest().getParameter("jam");
//        minit = getContext().getRequest().getParameter("minit");
//        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
//        if(tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
//            String ampm = "";
//            if(pagiPetang.equalsIgnoreCase("PAGI")){
//                ampm = "AM";
//            }else if(pagiPetang.equalsIgnoreCase("PETANG")){
//                ampm = "PM";
//            }
//            String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
//            Date tarikhSidang = dateFormat.parse(strMasa);
//            permohonanKertas.setTarikhSidang(tarikhSidang);
//        }
//
//        if(tempat == null)
//            tempat = "";
//        permohonanKertas.setTempatSidang(tempat);
//        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                System.out.println("1");
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                System.out.println("2");
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(9);
            kandungan = getContext().getRequest().getParameter("heading");
            System.out.println("kandungan " + kandungan);
            if (kandungan == null) {
                System.out.println("3");
                headingObj.setKandungan("Tiada");
            } else {
                System.out.println("4");
                headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            System.out.println("caw" + permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if (kandungan == null) {
                tujuanObj.setKandungan("Tiada");
            } else {
                tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

//         senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),2);
//
//            int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
//            System.out.println("kira2" + kira2);
//            for (int j = 1; j <= kira2; j++) {
//                if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
//                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    System.out.println("senaraiKertasKandungan 21");
//                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    System.out.println("senaraiKertasKandungan 22");
//                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan2.setKertas(permohonanKertas);
//                    permohonanKertasKandungan2.setBil(2);
//                    kandungan = getContext().getRequest().getParameter("kandungan2" + j);
//                    System.out.println("kandungan 2"+kandungan);
//                    permohonanKertasKandungan2.setKandungan(kandungan);
//                    permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan2.setSubtajuk("2." + j);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new java.util.Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan2.setInfoAudit(iaP);
//                    //permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
//            }
//
//            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);
//
//            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//            for (int k = 1; k <= kira3; k++) {
//                if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
//                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan3.setKertas(permohonanKertas);
//                    permohonanKertasKandungan3.setBil(3);
//                    kandungan = getContext().getRequest().getParameter("kandungan3" + k);
//                    permohonanKertasKandungan3.setKandungan(kandungan);
//                    permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan3.setSubtajuk("3." + k);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan3.setInfoAudit(iaP);
//                   // permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
//            }
//
//
//        count5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"));
//
//            for(int i=1;i<=count5;i++){
//                String subtajuk = "4."+i;
//                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),4,subtajuk);
//                int rowCount5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"+i));
//                for (int k = 1; k <= rowCount5; k++) {
//                    if (senaraiKertasKandungan4!=null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
//                        Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
//                        if (id3 != null) {
//                            permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                        }
//                    } else {
//                        permohonanKertasKandungan4 = new PermohonanKertasKandungan();
//                    }
//
//                    permohonanKertasKandungan4.setKertas(permohonanKertas);
//                    permohonanKertasKandungan4.setBil(4);
//                    kandungan = getContext().getRequest().getParameter("senaraiPentadbir"+i+ k);
//                    if(kandungan==null){
//                        permohonanKertasKandungan4.setKandungan("Tiada");
//                    }else{
//                        permohonanKertasKandungan4.setKandungan(kandungan);
//                    }
//                    permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
//                    permohonanKertasKandungan4.setSubtajuk("4."+ i+"."+str[k-1]);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan4.setInfoAudit(iaP);
////                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
//                }
//            }//if table count

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);

        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        System.out.println("kira2" + kira2);
        for (int j = 1; j <= kira2; j++) {
            if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
                if (id != null) {
                    System.out.println("senaraiKertasKandungan 21");
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                System.out.println("senaraiKertasKandungan 22");
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan2" + j);
            System.out.println("kandungan 2" + kandungan);
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
            //permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        for (int k = 1; k <= kira3; k++) {
            if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan3" + k);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("3." + k);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            // permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }


        count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));

        for (int i = 1; i <= count5; i++) {
            String subtajuk = "4." + i;
            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan4 != null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan4.setKertas(permohonanKertas);
                permohonanKertasKandungan4.setBil(4);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan4.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan4.setKandungan(kandungan);
                }
                permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan4.setSubtajuk("4." + i + "." + str[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }
        }//if table count

        senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan5.size() != 0 && j <= senaraiKertasKandungan5.size()) {
                Long id = senaraiKertasKandungan5.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(5);
            kandungan = getContext().getRequest().getParameter("kandungan5" + j);
            permohonanKertasKandungan5.setKandungan(kandungan);
            permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan5.setSubtajuk("5." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
        }
        senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);

        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        for (int j = 1; j <= kira6; j++) {
            if (senaraiKertasKandungan6.size() != 0 && j <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(6);
            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
            permohonanKertasKandungan6.setKandungan(kandungan);
            permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan6.setSubtajuk("6." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan6.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
        }



        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan dan sila jana Dokumen kembali.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPTGb() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan2 = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan2);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);
        permohonanKertas.setTajuk(tajuk);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if (kertasBil == null) {
            kertasBil = "";
        }
        permohonanKertas.setNomborRujukan(kertasBil + "/" + kertasTahun);
        try {
            tarikhmesyuarat = getContext().getRequest().getParameter("tarikhmesyuarat");
            permohonanKertas.setTarikhSidang(sdfx.parse(tarikhmesyuarat));
        } catch (Exception h) {
        }
//        jam = getContext().getRequest().getParameter("jam");
//        minit = getContext().getRequest().getParameter("minit");
//        pagiPetang = getContext().getRequest().getParameter("pagiPetang");
//        if(tarikhmesyuarat.length() > 0 && jam.length() > 0 && minit.length() > 0 && pagiPetang.length()>0){
//            String ampm = "";
//            if(pagiPetang.equalsIgnoreCase("PAGI")){
//                ampm = "AM";
//            }else if(pagiPetang.equalsIgnoreCase("PETANG")){
//                ampm = "PM";
//            }
//            String strMasa = tarikhmesyuarat + " " + jam + ":" + minit + ":00 " + ampm;
//            Date tarikhSidang = dateFormat.parse(strMasa);
//            permohonanKertas.setTarikhSidang(tarikhSidang);
//        }
//
//        if(tempat == null)
//            tempat = "";
//        permohonanKertas.setTempatSidang(tempat);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                System.out.println("1");
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                System.out.println("2");
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(9);
            kandungan = getContext().getRequest().getParameter("heading");
            System.out.println("kandungan " + kandungan);
            if (kandungan == null) {
                System.out.println("3");
                headingObj.setKandungan("Tiada");
            } else {
                System.out.println("4");
                headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            System.out.println("caw" + permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if (kandungan == null) {
                tujuanObj.setKandungan("Tiada");
            } else {
                tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

        senaraiKertasKandungan2 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);

        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        System.out.println("kira2" + kira2);
        for (int j = 1; j <= kira2; j++) {
            if (senaraiKertasKandungan2.size() != 0 && j <= senaraiKertasKandungan2.size()) {
                Long id = senaraiKertasKandungan2.get(j - 1).getIdKandungan();
                if (id != null) {
                    System.out.println("senaraiKertasKandungan 21");
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                System.out.println("senaraiKertasKandungan 22");
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan2" + j);
            System.out.println("kandungan 2" + kandungan);
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
            //permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan2);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        //   senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

//        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
//        for (int k = 1; k <= kira3; k++) {
//            if (senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
//                Long id = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan3.setKertas(permohonanKertas);
//            permohonanKertasKandungan3.setBil(3);
//            kandungan = getContext().getRequest().getParameter("kandungan3" + k);
//            permohonanKertasKandungan3.setKandungan(kandungan);
//            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan3.setSubtajuk("3." + k);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan3.setInfoAudit(iaP);
//            // permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
//        }

        count5a = Integer.parseInt(getContext().getRequest().getParameter("count5a"));
        logger.debug("count5a >>>>>>>>> " + count5a);
        for (int i = 1; i <= count5a; i++) {

            String subtajuk = "3." + i;
            senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 3, subtajuk);
            int rowCount5a = Integer.parseInt(getContext().getRequest().getParameter("count5a" + i));
            logger.debug("rowCount5a >>>>>>>>> " + rowCount5a);
            for (int k = 1; k <= rowCount5a; k++) {
                if (senaraiKertasKandungan3 != null && senaraiKertasKandungan3.size() != 0 && k <= senaraiKertasKandungan3.size()) {
                    Long id3 = senaraiKertasKandungan3.get(k - 1).getIdKandungan();
                    logger.info("id3 >>>> " + id3);
                    if (id3 != null) {
                        permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan3 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan3.setKertas(permohonanKertas);
                permohonanKertasKandungan3.setBil(3);
                logger.debug("kandungan no 3 i dan k >>>>>>>>> " + i + k);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir3" + i + k);
                logger.debug("kandungan no 3 >>>>>>>>> " + kandungan);
                if (kandungan == null) {
                    permohonanKertasKandungan3.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan3.setKandungan(kandungan);
                }
                permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan3.setSubtajuk("3." + i + "." + strB[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan3.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
            }
        }//if table count kandungan 3


        count5 = Integer.parseInt(getContext().getRequest().getParameter("count5"));

        for (int i = 1; i <= count5; i++) {
            String subtajuk = "4." + i;
            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count5" + i));
            for (int k = 1; k <= rowCount5; k++) {
                if (senaraiKertasKandungan4 != null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan4.setKertas(permohonanKertas);
                permohonanKertasKandungan4.setBil(4);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan4.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan4.setKandungan(kandungan);
                }
                permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan4.setSubtajuk("4." + i + "." + strB[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }
        }//if table count
        ///// new 6.1
        count6 = Integer.parseInt(getContext().getRequest().getParameter("count6"));
        logger.debug("new tambah 6.1.1");
        for (int i = 1; i <= count6; i++) {
            String subtajuk = "6." + i;
            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 6, subtajuk);
            int rowCount6 = Integer.parseInt(getContext().getRequest().getParameter("count6" + i));
            for (int k = 1; k <= rowCount6; k++) {
                if (senaraiKertasKandungan6 != null && senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
                    Long id3 = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                    }
                } else {
                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                }

                permohonanKertasKandungan6.setKertas(permohonanKertas);
                permohonanKertasKandungan6.setBil(6);
                kandungan = getContext().getRequest().getParameter("senaraiPentadbir6" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan6.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan6.setKandungan(kandungan);
                }
                permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan6.setSubtajuk("6." + i + "." + strB[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan6.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
            }
        }//if table count


        senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan5.size() != 0 && j <= senaraiKertasKandungan5.size()) {
                Long id = senaraiKertasKandungan5.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(5);
            kandungan = getContext().getRequest().getParameter("kandungan5" + j);
            permohonanKertasKandungan5.setKandungan(kandungan);
            permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan5.setSubtajuk("5." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
        }
//        senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);
//
//        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
//        for (int j = 1; j <= kira6; j++) {
//            if (senaraiKertasKandungan6.size() != 0 && j <= senaraiKertasKandungan6.size()) {
//                Long id = senaraiKertasKandungan6.get(j - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan6.setKertas(permohonanKertas);
//            permohonanKertasKandungan6.setBil(6);
//            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
//            permohonanKertasKandungan6.setKandungan(kandungan);
//            permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan6.setSubtajuk("6." + j);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan6.setInfoAudit(iaP);
////                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
//        }



        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan dan sila jana Dokumen kembali.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");
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
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingleB() {
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
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingleBKhas() {
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
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/seksyen831bc/mmkn_831b_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSinglePTG() {
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
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSinglePTGB() {
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
        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));
        System.out.println("bil" + bil);

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
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/seksyen831a/mmkn_831a_N9.jsp").addParameter("tab", "true");

    }

    public Resolution deleteTableB() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));
        System.out.println("bil" + bil);

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
        getContext().getRequest().setAttribute("ptgform1", Boolean.TRUE);

        return new JSP("pengambilan/negerisembilan/mmkn_831b_N9.jsp").addParameter("tab", "true");

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

//    public int getBil() {
//        return bil;
//    }
//
//    public void setBil(int bil) {
//        this.bil = bil;
//    }
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

    public String[] getStrB() {
        return strB;
    }

    public void setStrB(String[] strB) {
        this.strB = strB;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
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

    public int getCount4() {
        return count4;
    }

    public void setCount4(int count4) {
        this.count4 = count4;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getRingkasanPermohonan() {
        return ringkasanPermohonan;
    }

    public void setRingkasanPermohonan(PermohonanKertasKandungan ringkasanPermohonan) {
        this.ringkasanPermohonan = ringkasanPermohonan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public List[] getSenaraiPerakuanPengarah() {
        return senaraiPerakuanPengarah;
    }

    public void setSenaraiPerakuanPengarah(List[] senaraiPerakuanPengarah) {
        this.senaraiPerakuanPengarah = senaraiPerakuanPengarah;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanKertasKandungan getSyorPengarah() {
        return syorPengarah;
    }

    public void setSyorPengarah(PermohonanKertasKandungan syorPengarah) {
        this.syorPengarah = syorPengarah;
    }

    public PermohonanKertasKandungan getJawatanKuasa() {
        return jawatanKuasa;
    }

    public void setJawatanKuasa(PermohonanKertasKandungan jawatanKuasa) {
        this.jawatanKuasa = jawatanKuasa;
    }

    public List[] getSenarailatar() {
        return senarailatar;
    }

    public void setSenarailatar(List[] senarailatar) {
        this.senarailatar = senarailatar;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public List[] getSenaraiSyorPengarah() {
        return senaraiSyorPengarah;
    }

    public void setSenaraiSyorPengarah(List[] senaraiSyorPengarah) {
        this.senaraiSyorPengarah = senaraiSyorPengarah;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public int getCount5a() {
        return count5a;
    }

    public void setCount5a(int count5a) {
        this.count5a = count5a;
    }

    public List[] getSenaraiNilaianTanah() {
        return senaraiNilaianTanah;
    }

    public void setSenaraiNilaianTanah(List[] senaraiNilaianTanah) {
        this.senaraiNilaianTanah = senaraiNilaianTanah;
    }

    public List[] getSenaraiPentadbir() {
        return senaraiPentadbir;
    }

    public void setSenaraiPentadbir(List[] senaraiPentadbir) {
        this.senaraiPentadbir = senaraiPentadbir;
    }

    public List[] getSenaraiPentadbir3() {
        return senaraiPentadbir3;
    }

    public void setSenaraiPentadbir3(List[] senaraiPentadbir3) {
        this.senaraiPentadbir3 = senaraiPentadbir3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public List[] getSenaraiPentadbir6() {
        return senaraiPentadbir6;
    }

    public void setSenaraiPentadbir6(List[] senaraiPentadbir6) {
        this.senaraiPentadbir6 = senaraiPentadbir6;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan7() {
        return senaraiKertasKandungan7;
    }

    public void setSenaraiKertasKandungan7(List<PermohonanKertasKandungan> senaraiKertasKandungan7) {
        this.senaraiKertasKandungan7 = senaraiKertasKandungan7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan2() {
        return senaraiKertasKandungan2;
    }

    public void setSenaraiKertasKandungan2(List<PermohonanKertasKandungan> senaraiKertasKandungan2) {
        this.senaraiKertasKandungan2 = senaraiKertasKandungan2;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan6() {
        return permohonanKertasKandungan6;
    }

    public void setPermohonanKertasKandungan6(PermohonanKertasKandungan permohonanKertasKandungan6) {
        this.permohonanKertasKandungan6 = permohonanKertasKandungan6;
    }

    public String getMesyuaratBil() {
        return mesyuaratBil;
    }

    public void setMesyuaratBil(String mesyuaratBil) {
        this.mesyuaratBil = mesyuaratBil;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<PermohonanPihak> getListPP() {
        return listPP;
    }

    public void setListPP(List<PermohonanPihak> listPP) {
        this.listPP = listPP;
    }

    public String getNamaPengadu() {
        return namaPengadu;
    }

    public void setNamaPengadu(String namaPengadu) {
        this.namaPengadu = namaPengadu;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public String getNamaDaerah() {
        return NamaDaerah;
    }

    public void setNamaDaerah(String NamaDaerah) {
        this.NamaDaerah = NamaDaerah;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public Hakmilik getHakmilik1() {
        return hakmilik1;
    }

    public void setHakmilik1(Hakmilik hakmilik1) {
        this.hakmilik1 = hakmilik1;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getMesyKhas() {
        return MesyKhas;
    }

    public void setMesyKhas(String MesyKhas) {
        this.MesyKhas = MesyKhas;
    }

    public int getCount6() {
        return count6;
    }

    public void setCount6(int count6) {
        this.count6 = count6;
    }
}
