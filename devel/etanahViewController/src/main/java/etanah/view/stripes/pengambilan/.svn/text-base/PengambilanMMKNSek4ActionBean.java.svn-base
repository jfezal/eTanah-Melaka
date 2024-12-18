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
import java.util.Date;

import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;

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
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/borang_mmkn_sek4")
public class PengambilanMMKNSek4ActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PengambilanMMKNSek4NSActionBean.class);
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
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan ulasan1;
    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan8;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan21;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan22;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan23;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan24;
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
    private String kodUrusan;
    private String namaSeksyen;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("ape masalahnye ni");

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        HakmilikPermohonan hp3 = new HakmilikPermohonan();
        noLotList = new StringBuffer();
        kodUrusan = permohonan.getKodUrusan().getKod();
        if (kodUrusan.equalsIgnoreCase("SEK4")) {
            namaSeksyen = "DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
        } else if (kodUrusan.equalsIgnoreCase("831A")) {
            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(a) AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(a) NEGERI MELAKA";
        } else if (kodUrusan.equalsIgnoreCase("831B")) {
            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(b) AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(b) NEGERI MELAKA";
        } else if (kodUrusan.equalsIgnoreCase("831C")) {
            namaSeksyen = "DI BAWAH SEKSYEN 3(1)(c) AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 3(1)(c) NEGERI MELAKA";
        } //shida-- modified for urusan etapp
        else if (kodUrusan.equalsIgnoreCase("ESK4")) {
            namaSeksyen = "DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
        } else if (kodUrusan.equalsIgnoreCase("ESK8")) {
            namaSeksyen = "DI BAWAH SEKSYEN 8 AKTA PENGAMBILAN TANAH 1960.";
            tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 8 NEGERI MELAKA";
        }
        for (HakmilikPermohonan hp1 : hakmilikPermohonanList) {
            if (hp1.getHakmilik() != null) {
                noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ");
            }
//            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ").append(hp1.getHakmilik().getBandarPekanMukim().getNama()).append(", ");
        }

        totalLuas = BigDecimal.ZERO;
        BigDecimal luas = BigDecimal.ZERO;

        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            luas = BigDecimal.ZERO;
            luas = hakmilikPermohonan.getLuasTerlibat();
            String kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();
            if (kodUOM.equals("Hektar")) {
                totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
            } else if (kodUOM.equals("Meter Persegi")) {
                totalLuas = totalLuas.add(luas);
            }
        }
        String uom = "Meter Persegi";
        if (totalLuas.compareTo(new BigDecimal(10000)) == 1) {
            totalLuas = totalLuas.divide(new BigDecimal(10000));
            uom = "Hektar";
        }

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
                    tujuan = "Tujuan kertas ini dikemukakan adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat "
                            + "Kerajaan Negeri Melaka bagi permohonan pengambilan tanah di atas "
                            + noLotList + " seluas " + totalLuas + " " + uom + ",di "
                            + hakmilik.getBandarPekanMukim().getNama() + "," + " Daerah " + WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + ", Melaka untuk tujuan "
                            + permohonan.getSebab().toLowerCase() + " " + WordUtils.capitalizeFully(namaSeksyen);
//                                    + WordUtils.capitalizeFully(permohonan.getSebab().toLowerCase())+

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
        if (!permohonan.getSenaraiPemohon().isEmpty()) {
            String temp = "PERMOHONAN PENGAMBILAN TANAH DARIPADA " + permohonan.getSenaraiPemohon().get(0).getPihak().getNama() + " SELUAS LEBIH KURANG "
                    + totalLuas + " " + uom + " UNTUK " + permohonan.getSebab() + " DI "
                    + noLotList + " DAERAH " + hakmilik.getDaerah().getNama() + " " + namaSeksyen;

            heading = temp.toUpperCase();
        }

        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan, tajuk);

            if (permohonanKertas != null) {//
                kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 11);
                kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
                if (headingObj != null) {
                    heading = headingObj.getKandungan();
                }
                if (tujuanObj != null) {
                    tujuan = tujuanObj.getKandungan();
                }
                senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);
                senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 21);
                senaraiKertasKandungan22 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 22);
                senaraiKertasKandungan23 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 23);
                senaraiKertasKandungan24 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 24);

                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 5);
                if (kandList5 != null && !kandList5.isEmpty()) {
                    logger.debug("kandList5 x empty");
                    maxKertasKand5 = kandList5.get(0);
                    if (maxKertasKand5 != null) {
                        logger.debug("maxKertasKand5");
                        String subtajuk = maxKertasKand5.getSubtajuk();
                        logger.debug("subtajuk " + subtajuk);
                        String str = subtajuk.substring(2, 3);
                        logger.debug("str " + str);
                        int tableCount = Integer.parseInt(str);
                        logger.debug("tableCount " + tableCount);
                        count4 = tableCount;
                        for (int k = 1; k <= tableCount; k++) {
                            senaraiPerakuanPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                            String subtajuk1 = "5." + k;
                            senaraiPerakuanPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk1);
                        }
                    }
                }

            }
        }

    }

    public Resolution simpan() {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        cawangan = permohonan.getCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        iaPermohonan.setTarikhMasuk(new Date());
        iaPermohonan.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(iaPermohonan);
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);

        if (getContext().getRequest().getParameter("heading") != null) {
            InfoAudit iaP = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                iaP.setDimasukOleh(peng);
                iaP.setTarikhMasuk(new java.util.Date());
            } else {
                iaP = headingObj.getInfoAudit();
                iaP.setDiKemaskiniOleh(peng);
                iaP.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(6);
            kandungan = getContext().getRequest().getParameter("heading");
//            if(kandungan==null){
//                    headingObj.setKandungan("Tiada");
//                }else{
            headingObj.setKandungan(kandungan);
//            }
            headingObj.setCawangan(permohonan.getCawangan());
            headingObj.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

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
        if (getContext().getRequest().getParameter("tujuan") != null) {
            InfoAudit iaP = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                iaP.setDimasukOleh(peng);
                iaP.setTarikhMasuk(new java.util.Date());
            } else {
                iaP = tujuanObj.getInfoAudit();
                iaP.setDiKemaskiniOleh(peng);
                iaP.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
//            if(kandungan==null){
//                    tujuanObj.setKandungan("Tiada");
//                }else{
            tujuanObj.setKandungan(kandungan);
//            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

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
        senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 21);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        logger.debug("kira 1 " + kira1);
        for (int i = 1; i <= kira1; i++) {
            if (senaraiKertasKandungan21.size() != 0 && i <= senaraiKertasKandungan21.size()) {
                Long id = senaraiKertasKandungan21.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
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
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
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
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(23);
            kandungan = getContext().getRequest().getParameter("kandungan3" + z);
            logger.debug("kandungan 3 " + kandungan);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("2.3." + z);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(24);
            kandungan = getContext().getRequest().getParameter("kandungan4" + x);
            logger.debug("kandungan 4 " + kandungan);
            permohonanKertasKandungan4.setKandungan(kandungan);
            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan4.setSubtajuk("2.4." + x);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan4.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        for (int w = 1; w <= kira; w++) {
            if (senaraiKertasKandungan3.size() != 0 && w <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(w - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan5" + w);
            logger.debug("kandungan 5 " + kandungan);
            permohonanKertasKandungan5.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan5.setCawangan(cawangan);
            permohonanKertasKandungan5.setSubtajuk("3." + w);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
        }

        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(4);
            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
            logger.debug("kandungan 6 " + kandungan);
            permohonanKertasKandungan6.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan6.setCawangan(cawangan);
            permohonanKertasKandungan6.setSubtajuk("4." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan6.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
        }

        count4 = Integer.parseInt(getContext().getRequest().getParameter("count4"));

        for (int b = 1; b <= count4; b++) {
            String subtajuk = "5." + b;
            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk);
            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("count4" + b));
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
                permohonanKertasKandungan8.setBil(5);
                kandungan = getContext().getRequest().getParameter("perakuanPengarah" + b + k);
                if (kandungan == null) {
                    permohonanKertasKandungan8.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan8.setKandungan(kandungan);
                }
                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan8.setCawangan(cawangan);
                permohonanKertasKandungan8.setSubtajuk("5." + b + "." + str[k - 1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan8.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
            }
        }

        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
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

        senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 21);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        logger.debug("kira 1 " + kira1);
        for (int i = 1; i <= kira1; i++) {
            if (senaraiKertasKandungan21.size() != 0 && i <= senaraiKertasKandungan21.size()) {
                Long id = senaraiKertasKandungan21.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
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
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
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
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(23);
            kandungan = getContext().getRequest().getParameter("kandungan3" + z);
            logger.debug("kandungan 3 " + kandungan);
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("2.3." + z);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
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
                }
            } else {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(24);
            kandungan = getContext().getRequest().getParameter("kandungan4" + x);
            logger.debug("kandungan 4 " + kandungan);
            permohonanKertasKandungan4.setKandungan(kandungan);
            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan4.setSubtajuk("2.4." + x);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan4.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        for (int w = 1; w <= kira; w++) {
            if (senaraiKertasKandungan3.size() != 0 && w <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(w - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan5" + w);
            logger.debug("kandungan 5 " + kandungan);
            permohonanKertasKandungan5.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan5.setCawangan(cawangan);
            permohonanKertasKandungan5.setSubtajuk("3." + w);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan5.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
        }

        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
        for (int j = 1; j <= kira5; j++) {
            if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(4);
            kandungan = getContext().getRequest().getParameter("kandungan6" + j);
            logger.debug("kandungan 6 " + kandungan);
            permohonanKertasKandungan6.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan6.setCawangan(cawangan);
            permohonanKertasKandungan6.setSubtajuk("4." + j);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan6.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
        }

        count4 = Integer.parseInt(getContext().getRequest().getParameter("count4"));

        for (int i = 1; i <= count4; i++) {
            String subtajuk = "5." + i;
            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk);
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
                permohonanKertasKandungan8.setBil(5);
                kandungan = getContext().getRequest().getParameter("perakuanPengarah" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan8.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan8.setKandungan(kandungan);
                }
                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan8.setCawangan(cawangan);
                permohonanKertasKandungan8.setSubtajuk("5." + i + "." + str[k - 1]);
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
        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
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
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(PengambilanMMKNSek4ActionBean.class, "locate");
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
        return new RedirectResolution(PengambilanMMKNSek4NSActionBean.class, "locate");
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
        return new JSP("pengambilan/melaka/seksyen4/MMKNsek4.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(PengambilanMMKNSek4NSActionBean.class, "locate");

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
}
