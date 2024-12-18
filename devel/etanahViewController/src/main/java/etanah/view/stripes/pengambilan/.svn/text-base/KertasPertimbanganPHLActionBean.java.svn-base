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
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Rajesh
 * Modified By Murali
 */
@UrlBinding("/pengambilan/mmk_PHL")
public class KertasPertimbanganPHLActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PengambilanService pengambilanService;
    private static Logger logger = Logger.getLogger(KertasPertimbanganPHLActionBean.class);
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Hakmilik hakmilik;
    private PermohonanKertas permohonanKertas;
    private String idPermohonan;
    private String heading1;
    private String heading2;
    private String heading3;
    private String tujuan;
    private String pemohon41;
    private String kandungan;
    private String idKandungan;
    private String kertasBil;
    private String kertasTahun;

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
//    private PermohonanKertasKandungan kertasTahun;
//    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertas tajukObj;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan headingObj2;
    private PermohonanKertasKandungan pemohon41Obj;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan permohonanKertasKandungan7;
    private PermohonanKertasKandungan permohonanKertasKandungan8;
    private PermohonanKertasKandungan permohonanKertasKandungan9;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan7;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan8;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan9;
    private final String tajuk = "DOKUMEN INI ADALAH HAKMILIK KERAJAAN";
    private int count5 = 0;
    private int count7 = 0;
    private List senaraiRayuanTuan[] = new ArrayList[10];
    private List senaraiSyorPentadbir[] = new ArrayList[10];
    String str[] = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private KodCawangan cawangan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPermohonan> hakPSebelumList;
    private String idSblm;
    Hakmilik hakmilik1 = new Hakmilik();
    private String lokasi;
    String lokasi1;
    String lokasi2;
    private String NamaDaerah;
    private String kodUrusan;
    private String noRujukan;
    private int bil1=0;
    private int bil2=0;
    private Permohonan pSebelum;

    public Permohonan getpSebelum() {
        return pSebelum;
    }

    public void setpSebelum(Permohonan pSebelum) {
        this.pSebelum = pSebelum;
    }

    public int getBil1() {
        return bil1;
    }

    public void setBil1(int bil1) {
        this.bil1 = bil1;
    }

    public int getBil2() {
        return bil2;
    }

    public void setBil2(int bil2) {
        this.bil2 = bil2;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    @DefaultHandler
    public Resolution showForm() {
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
        if (idSblm != null) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        } else {
            addSimpleError("Haraf maaf sila masukkan id Permohonan Terdahulu");
            getContext().getRequest().setAttribute("form1", Boolean.FALSE);
            getContext().getRequest().setAttribute("formPtg", Boolean.FALSE);
        }
//        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/KertasPertimbanganPHL.jsp").addParameter("tab", "true");
    }

    //For PTG
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/KertasPertimbanganPHL.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        HakmilikPermohonan hp1 = new HakmilikPermohonan();

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

//        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = pengambilanService.getHakmilikPermohonanList(idPermohonan);
        hakPSebelumList=pengambilanService.getHakmilikPermohonanList(permohonan.getPermohonanSebelum().getIdPermohonan());
        bil1=hakmilikPermohonanList.size();
        bil2=hakPSebelumList.size();
        pSebelum=permohonan.getPermohonanSebelum();

        String sbb = "";
        String urusan = "";
        String apt = " Di Bawah Seksyen 35(1) Akta Pengambilan Tanah 1960 Bagi Pengambilan Balik Mengikut Seksyen 3(1)(a) Akta Pengambilan Tanah 1960";

        if (!hakmilikPermohonanList.isEmpty()) {

            for (int w = 0; w < hakmilikPermohonanList.size(); w++) {
                hp = permohonan.getSenaraiHakmilik().get(w);
                if (hp.getHakmilik() != null) {
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
                    if (permohonan.getPermohonanSebelum() != null) {
                        sbb = permohonan.getPermohonanSebelum().getSebab();
                    }

                    if (hakmilikPermohonanList.size() > (w + 1)) {
                        hp1 = permohonan.getSenaraiHakmilik().get(w + 1);
                        hakmilik1 = hakmilikDAO.findById(hp1.getHakmilik().getIdHakmilik());
                    }
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

                    if (permohonan != null) {

                        if (permohonan.getPermohonanSebelum() != null) {
                             kodUrusan=permohonan.getPermohonanSebelum().getKodUrusan().getKod();
                            if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831A")) {
                                urusan = "3(1)(a)";
                            }
                            if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831B")) {
                                urusan = "3(1)(b)";
                            }
                            if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("831C")) {
                                urusan = "3(1)(c)";
                            }
                            if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equalsIgnoreCase("SEK4")) {
                                urusan = "Pengambilan Tanah Di bawah Seksyen 4";
                            }
                        }
                    }

                    NamaDaerah = WordUtils.capitalizeFully(hakmilik.getDaerah().getNama());

                    if (w == 0) {

                        lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + "," + " " + "Hakmilik " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

                        if (hakmilikPermohonanList.size() > (w + 1)) {
                            if (!hakmilik.getBandarPekanMukim().getNama().equals(hakmilik1.getBandarPekanMukim().getNama())) {
                                String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                lokasi = lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                            } else {
                                lokasi = lokasi1;
                            }

                        } else {
                            String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                            lokasi = lokasi1 + BandarPekanMukim +", Daerah " + NamaDaerah;
                        }
                    }
                    if (w > 0) {
                        if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                            lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + "," + " " + "Hakmilik " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

                            if (hakmilikPermohonanList.size() > (w + 1)) {
                                if (!hakmilik.getBandarPekanMukim().getNama().equals(hakmilik1.getBandarPekanMukim().getNama())) {
                                    String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                    lokasi = lokasi + ",  " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                                } else {
                                    lokasi = lokasi + ", " + lokasi1 + " , ";
                                }
                            } else {
                                String BandarPekanMukim = WordUtils.capitalizeFully(hakmilik.getBandarPekanMukim().getNama());
                                lokasi = lokasi + ", " + lokasi1 + BandarPekanMukim + ", Daerah " + NamaDaerah;
                            }

                        } else if (w == (hakmilikPermohonanList.size() - 1)) {
                            lokasi1 = hakmilik.getLot().getNama().toUpperCase() + " " + hakmilik.getNoLot() + "," + " " + "Hakmilik " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", ";

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

            tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Pentadbir Tanah Daerah "+NamaDaerah+" ke atas permohonan dari pemilik tanah "+lokasi+", "+NamaDaerah+" untuk membatalkan kewujudan "
                    + pSebelum.getKodUrusan().getNama() + " di atas tanahnya kerana pengguna "+pSebelum.getKodUrusan().getNama()+" untuk ke Lot "+lokasi+" "+NamaDaerah+" ini telah tidak mematuhi syarat-syarat "+pSebelum.getKodUrusan().getNama();

            String temp1 = "PERMOHONAN UNTUK MEMBATALKAN "+pSebelum.getKodUrusan().getNama().toUpperCase()+" ";

            String temp2 = "KE " + lokasi;
            heading1 = temp1;
            heading3 = temp2.toUpperCase().replace("(A)", "(a)");


            if (idPermohonan != null) {
                permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan2(idPermohonan);
                if (permohonanKertas != null) {
                    noRujukan = permohonanKertas.getTempatSidang();
                        if(noRujukan !=null){
                        if(noRujukan.length()>0){
                            kertasBil = noRujukan.substring(0, noRujukan.length()-5);
                            logger.info("kertasBil "+kertasBil);
//                            kertasTahun = noRujukan.substring(noRujukan.length()-4, noRujukan.length());
                        }
                        }

                    headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 11);
                    headingObj2 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
//                    kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
//                    kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 8);
                    tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                    senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);
                    pemohon41Obj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 41);
//                    senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 4);
                    senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
                    senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
//                    senaraiKertasKandungan9 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);

                    if (headingObj2 != null) {
                        heading2 = headingObj2.getKandungan();
                    }
                    if (tujuanObj != null) {
                        tujuan = tujuanObj.getKandungan();
                    }
                    if (pemohon41Obj != null) {
                        pemohon41 = pemohon41Obj.getKandungan();
                    }


//                    List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
//                    PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
//                    kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 5);
//                    if (kandList5 != null && !kandList5.isEmpty()) {
//                        maxKertasKand5 = kandList5.get(0);
//                        if (maxKertasKand5 != null) {
//                            String subtajuk = maxKertasKand5.getSubtajuk();
//                            String str = subtajuk.substring(2, 3);
//                            int tableCount = Integer.parseInt(str);
//                            count5 = tableCount;
//                            for (int k = 1; k <= tableCount; k++) {
//                                senaraiRayuanTuan[k] = new ArrayList<PermohonanKertasKandungan>();
//                                String subtajuk1 = "5." + k;
//                                senaraiRayuanTuan[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk1);
//                            }
//                        }
//                    }//if

                    List<PermohonanKertasKandungan> kandList7 = new ArrayList<PermohonanKertasKandungan>();
                    PermohonanKertasKandungan maxKertasKand7 = new PermohonanKertasKandungan();
                    kandList7 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(), 4);
                    if (kandList7 != null && !kandList7.isEmpty()) {
                        maxKertasKand7 = kandList7.get(0);
                        if (maxKertasKand7 != null) {
                            String subtajuk = maxKertasKand7.getSubtajuk();
                            String str = subtajuk.substring(2, 3);
                            int tableCount = Integer.parseInt(str);
                            count7 = tableCount;
                            for (int k = 1; k <= tableCount; k++) {
                                senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "4." + k;
                                senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk1);
                            }
                        }
                    }//if
                }
                if(kertasTahun == null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                kertasTahun = sdf.format(new java.util.Date()).toString();
            }
            }
        }
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
        }
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        if(kertasBil == null)
            kertasBil = "";
        permohonanKertas.setTempatSidang(kertasBil+"/"+kertasTahun);
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDimasukOleh(peng);
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setKodDokumen(doc);
        permohonanKertas.setTajuk(heading1);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);
        
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


        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

        for (int i = 1; i <= kira3; i++) {
            if (senaraiKertasKandungan3.size() != 0 && i <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            if (kandungan == null) {
                kandungan = "Tiada Data";
            }
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }
//
//        if (getContext().getRequest().getParameter("pemohon41") != null) {
//            infoAudit = new InfoAudit();
//            if (pemohon41Obj == null) {
//                pemohon41Obj = new PermohonanKertasKandungan();
//                infoAudit.setDimasukOleh(peng);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//            } else {
//                infoAudit = pemohon41Obj.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(peng);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//            }
//            pemohon41Obj.setKertas(permohonanKertas);
//            pemohon41Obj.setBil(41);
//            kandungan = getContext().getRequest().getParameter("pemohon41");
//            if (kandungan == null) {
//                pemohon41Obj.setKandungan("Tiada");
//            } else {
//                pemohon41Obj.setKandungan(kandungan);
//            }
//            pemohon41Obj.setCawangan(permohonan.getCawangan());
//            pemohon41Obj.setInfoAudit(infoAudit);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(pemohon41Obj);
//        }

//        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
//        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));
//
//        for (int i = 1; i <= kira4; i++) {
//            if (senaraiKertasKandungan4.size() != 0 && i <= senaraiKertasKandungan4.size()) {
//                Long id = senaraiKertasKandungan4.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan4.setKertas(permohonanKertas);
//            permohonanKertasKandungan4.setBil(4);
//            kandungan = getContext().getRequest().getParameter("kandungan4" + i);
//            if (kandungan == null) {
//                kandungan = "Tiada Data";
//            }
//            permohonanKertasKandungan4.setKandungan(kandungan);
//            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan4.setSubtajuk("4.1." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new java.util.Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan4.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
//        }



//        count5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
//        for (int i = 1; i <= count5; i++) {
//            String subtajuk = "5." + i;
//            senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 5, subtajuk);
//            int rowCount5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9" + i));
//            for (int k = 1; k <= rowCount5; k++) {
//                if (senaraiKertasKandungan5 != null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
//                    Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
//                    if (id3 != null) {
//                        permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
//                        infoAudit = permohonanKertasKandungan5.getInfoAudit();
//                        infoAudit.setDiKemaskiniOleh(peng);
//                        infoAudit.setTarikhKemaskini(new java.util.Date());
//                    }
//                } else {
//                    permohonanKertasKandungan5 = new PermohonanKertasKandungan();
//                    infoAudit = new InfoAudit();
//                    infoAudit.setDimasukOleh(peng);
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                }
//
//                permohonanKertasKandungan5.setKertas(permohonanKertas);
//                permohonanKertasKandungan5.setBil(5);
//                kandungan = getContext().getRequest().getParameter("rayuanTuan" + i + k);
//                if (kandungan == null) {
//                    permohonanKertasKandungan5.setKandungan("Tiada");
//                } else {
//                    permohonanKertasKandungan5.setKandungan(kandungan);
//                }
//                permohonanKertasKandungan5.setCawangan(permohonan.getCawangan());
//                String subT = "";
//                if (k == 1) {
//                    subT = "5." + i;
//                }
//                if (k > 1) {
//                    subT = "5." + i + "." + (k - 1);
//                }
//                permohonanKertasKandungan5.setSubtajuk(subT);
////                permohonanKertasKandungan5.setSubtajuk("5."+ i+"."+str[k-1]);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new java.util.Date());
//                iaP.setDimasukOleh(peng);
//                permohonanKertasKandungan5.setInfoAudit(iaP);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
//            }
//        }//if table count

        senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
        int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));

        for (int i = 1; i <= kira6; i++) {
            if (senaraiKertasKandungan6.size() != 0 && i <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan6 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan6.setKertas(permohonanKertas);
            permohonanKertasKandungan6.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan6" + i);
            if (kandungan == null) {
                kandungan = "Tiada Data";
            }
            permohonanKertasKandungan6.setKandungan(kandungan);
            permohonanKertasKandungan6.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan6.setSubtajuk("3." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan6.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
        }

        count7 = Integer.parseInt(getContext().getRequest().getParameter("count7"));
        for (int i = 1; i <= count7; i++) {
            String subtajuk = "4." + i;
            senaraiKertasKandungan7 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(), 4, subtajuk);
            int rowCount7 = Integer.parseInt(getContext().getRequest().getParameter("count7" + i));
            for (int k = 1; k <= rowCount7; k++) {
                if (senaraiKertasKandungan7 != null && senaraiKertasKandungan7.size() != 0 && k <= senaraiKertasKandungan7.size()) {
                    Long id3 = senaraiKertasKandungan7.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan7 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan7.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan7 = new PermohonanKertasKandungan();
                    infoAudit = new InfoAudit();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                permohonanKertasKandungan7.setKertas(permohonanKertas);
                permohonanKertasKandungan7.setBil(4);
                kandungan = getContext().getRequest().getParameter("syorPentadbir" + i + k);
                if (kandungan == null) {
                    permohonanKertasKandungan7.setKandungan("Tiada");
                } else {
                    permohonanKertasKandungan7.setKandungan(kandungan);
                }
                permohonanKertasKandungan7.setCawangan(permohonan.getCawangan());
                String subT = "";
                if (k == 1) {
                    subT = "4." + i;
                }
                if (k > 1) {
                    subT = "4." + i + "." + (k - 1);
                }
                permohonanKertasKandungan7.setSubtajuk(subT);
//                 permohonanKertasKandungan7.setSubtajuk("7."+ i+"."+str[k-1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new java.util.Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan7.setInfoAudit(iaP);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan7);
            }
        }//if table count

        senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
        int kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));

        for (int i = 1; i <= kira8; i++) {
            if (senaraiKertasKandungan8.size() != 0 && i <= senaraiKertasKandungan8.size()) {
                Long id = senaraiKertasKandungan8.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan8 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan8.setKertas(permohonanKertas);
            permohonanKertasKandungan8.setBil(5);
            kandungan = getContext().getRequest().getParameter("kandungan8" + i);
            if (kandungan == null) {
                kandungan = "Tiada Data";
            }
            permohonanKertasKandungan8.setKandungan(kandungan);
            permohonanKertasKandungan8.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan8.setSubtajuk("5." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan8.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan8);
        }

//        senaraiKertasKandungan9 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);
//        int kira9 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));
//
//        for (int i = 1; i <= kira9; i++) {
//            if (senaraiKertasKandungan9.size() != 0 && i <= senaraiKertasKandungan9.size()) {
//                Long id = senaraiKertasKandungan9.get(i - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan9 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//            } else {
//                permohonanKertasKandungan9 = new PermohonanKertasKandungan();
//            }
//            permohonanKertasKandungan9.setKertas(permohonanKertas);
//            permohonanKertasKandungan9.setBil(6);
//            kandungan = getContext().getRequest().getParameter("kandungan9" + i);
//            if (kandungan == null) {
//                kandungan = "Tiada Data";
//            }
//            permohonanKertasKandungan9.setKandungan(kandungan);
//            permohonanKertasKandungan9.setCawangan(permohonan.getCawangan());
//            permohonanKertasKandungan9.setSubtajuk("6." + i);
//            InfoAudit iaP = new InfoAudit();
//            iaP.setTarikhMasuk(new java.util.Date());
//            iaP.setDimasukOleh(peng);
//            permohonanKertasKandungan9.setInfoAudit(iaP);
//            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan9);
//        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new RedirectResolution(KertasPertimbanganPHLActionBean.class, "locate");
    }

    public Resolution simpanPtg() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
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
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
        kertasBil = getContext().getRequest().getParameter("kertasBil");
        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
        permohonanKertas.setTempatSidang(kertasBil+"/"+kertasTahun);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

        senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 5);
        int kira8 = Integer.parseInt(getContext().getRequest().getParameter("rowCount8"));

        for (int i = 1; i <= kira8; i++) {
            if (senaraiKertasKandungan8.size() != 0 && i <= senaraiKertasKandungan8.size()) {
                Long id = senaraiKertasKandungan8.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan8 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan8.setKertas(permohonanKertas);
            permohonanKertasKandungan8.setBil(5);
            kandungan = getContext().getRequest().getParameter("kandungan8" + i);
            if (kandungan == null) {
                kandungan = "Tiada Data";
            }
            permohonanKertasKandungan8.setKandungan(kandungan);
            permohonanKertasKandungan8.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan8.setSubtajuk("5." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan8.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan8);
        }

        senaraiKertasKandungan9 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 6);
        int kira9 = Integer.parseInt(getContext().getRequest().getParameter("rowCount9"));

        for (int i = 1; i <= kira9; i++) {
            if (senaraiKertasKandungan9.size() != 0 && i <= senaraiKertasKandungan9.size()) {
                Long id = senaraiKertasKandungan9.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan9 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan9 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan9.setKertas(permohonanKertas);
            permohonanKertasKandungan9.setBil(6);
            kandungan = getContext().getRequest().getParameter("kandungan9" + i);
            if (kandungan == null) {
                kandungan = "Tiada Data";
            }
            permohonanKertasKandungan9.setKandungan(kandungan);
            permohonanKertasKandungan9.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan9.setSubtajuk("6." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new java.util.Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan9.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan9);
        }
        
//           if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
//                if (kertasBil == null) {
//                    kertasBil = new PermohonanKertasKandungan();
//
//                }
//                kertasBil.setKertas(permohonanKertas);
//                kertasBil.setBil(9);
//                kandungan = getContext().getRequest().getParameter("kertasBil.kandungan");
//                kertasBil.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasBil.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasBil.setInfoAudit(iaP);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasBil);
////                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
//             }
//
//            if (getContext().getRequest().getParameter("kertasTahun.kandungan") != null) {
//                if (kertasTahun == null) {
//                    kertasTahun = new PermohonanKertasKandungan();
//
//                }
//                kertasTahun.setKertas(permohonanKertas);
//                kertasTahun.setBil(8);
//                kandungan = getContext().getRequest().getParameter("kertasTahun.kandungan");
//                kertasTahun.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                kertasTahun.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                kertasTahun.setInfoAudit(iaP);
//                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasTahun);
//             }

        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
//        return new RedirectResolution(PenarikanBalikMMKNNSActionBean.class, "locate");
        return new JSP("pengambilan/KertasPertimbanganPHL.jsp").addParameter("tab", "true");
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
            List<PermohonanKertasKandungan> senaraiKertasKandungan = new ArrayList<PermohonanKertasKandungan>();
            int bil = 0;
            bil = permohonanKertasKandungan.getBil();


            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanKertasKandungan.setInfoAudit(ia);
            permohonanKertasKandungan.setCawangan(permohonan.getCawangan());
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan);

            //Reset SubTajuk
            if (bil == 2 || bil == 3 || bil == 5 || bil == 6) {
                senaraiKertasKandungan = pendudukanSementaraMMKNService.findByKertas(permohonanKertasKandungan.getKertas().getIdKertas(), bil);
                for (int i = 1; i <= senaraiKertasKandungan.size(); i++) {
                    permohonanKertasKandungan = senaraiKertasKandungan.get(i - 1);
                    permohonanKertasKandungan.setSubtajuk(bil + "." + i);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
                }
            }
//            if (bil == 4) {
//                senaraiKertasKandungan = pendudukanSementaraMMKNService.findByKertas(permohonanKertasKandungan.getKertas().getIdKertas(), bil);
//                for (int i = 1; i <= senaraiKertasKandungan.size(); i++) {
//                    permohonanKertasKandungan = senaraiKertasKandungan.get(i - 1);
//                    permohonanKertasKandungan.setSubtajuk("4.1." + i);
//                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan);
//                }
//            }

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

        return new JSP("pengambilan/KertasPertimbanganPHL.jsp").addParameter("tab", "true");
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
//       getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        String form1 = getContext().getRequest().getParameter("form1");
        if (form1.equals("true")) {
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        }
        String formPtg = getContext().getRequest().getParameter("formPtg");
        if (formPtg.equals("true")) {
            getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        }
        return new JSP("pengambilan/KertasPertimbanganPHL.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getHeading1() {
        return heading1;
    }

    public void setHeading1(String heading1) {
        this.heading1 = heading1;
    }

    public String getHeading2() {
        return heading2;
    }

    public void setHeading2(String heading2) {
        this.heading2 = heading2;
    }

    public String getHeading3() {
        return heading3;
    }

    public void setHeading3(String heading3) {
        this.heading3 = heading3;
    }

    public PermohonanKertasKandungan getHeadingObj2() {
        return headingObj2;
    }

    public void setHeadingObj2(PermohonanKertasKandungan headingObj2) {
        this.headingObj2 = headingObj2;
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public PermohonanKertasKandungan getHeadingObj() {
        return headingObj;
    }

    public void setHeadingObj(PermohonanKertasKandungan headingObj) {
        this.headingObj = headingObj;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
        return permohonanKertasKandungan3;
    }

    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan6() {
        return permohonanKertasKandungan6;
    }

    public void setPermohonanKertasKandungan6(PermohonanKertasKandungan permohonanKertasKandungan6) {
        this.permohonanKertasKandungan6 = permohonanKertasKandungan6;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan8() {
        return permohonanKertasKandungan8;
    }

    public void setPermohonanKertasKandungan8(PermohonanKertasKandungan permohonanKertasKandungan8) {
        this.permohonanKertasKandungan8 = permohonanKertasKandungan8;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
        return senaraiKertasKandungan3;
    }

    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan6() {
        return senaraiKertasKandungan6;
    }

    public void setSenaraiKertasKandungan6(List<PermohonanKertasKandungan> senaraiKertasKandungan6) {
        this.senaraiKertasKandungan6 = senaraiKertasKandungan6;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan8() {
        return senaraiKertasKandungan8;
    }

    public void setSenaraiKertasKandungan8(List<PermohonanKertasKandungan> senaraiKertasKandungan8) {
        this.senaraiKertasKandungan8 = senaraiKertasKandungan8;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
    }

    public int getCount5() {
        return count5;
    }

    public void setCount5(int count5) {
        this.count5 = count5;
    }

    public int getCount7() {
        return count7;
    }

    public void setCount7(int count7) {
        this.count7 = count7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan9() {
        return senaraiKertasKandungan9;
    }

    public void setSenaraiKertasKandungan9(List<PermohonanKertasKandungan> senaraiKertasKandungan9) {
        this.senaraiKertasKandungan9 = senaraiKertasKandungan9;
    }

    public List[] getSenaraiRayuanTuan() {
        return senaraiRayuanTuan;
    }

    public void setSenaraiRayuanTuan(List[] senaraiRayuanTuan) {
        this.senaraiRayuanTuan = senaraiRayuanTuan;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan7() {
        return permohonanKertasKandungan7;
    }

    public void setPermohonanKertasKandungan7(PermohonanKertasKandungan permohonanKertasKandungan7) {
        this.permohonanKertasKandungan7 = permohonanKertasKandungan7;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan9() {
        return permohonanKertasKandungan9;
    }

    public void setPermohonanKertasKandungan9(PermohonanKertasKandungan permohonanKertasKandungan9) {
        this.permohonanKertasKandungan9 = permohonanKertasKandungan9;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan5() {
        return senaraiKertasKandungan5;
    }

    public void setSenaraiKertasKandungan5(List<PermohonanKertasKandungan> senaraiKertasKandungan5) {
        this.senaraiKertasKandungan5 = senaraiKertasKandungan5;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan7() {
        return senaraiKertasKandungan7;
    }

    public void setSenaraiKertasKandungan7(List<PermohonanKertasKandungan> senaraiKertasKandungan7) {
        this.senaraiKertasKandungan7 = senaraiKertasKandungan7;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public String getPemohon41() {
        return pemohon41;
    }

    public void setPemohon41(String pemohon41) {
        this.pemohon41 = pemohon41;
    }

    public PermohonanKertasKandungan getPemohon41Obj() {
        return pemohon41Obj;
    }

    public void setPemohon41Obj(PermohonanKertasKandungan pemohon41Obj) {
        this.pemohon41Obj = pemohon41Obj;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
        return senaraiKertasKandungan4;
    }

    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public Hakmilik getHakmilik1() {
        return hakmilik1;
    }

    public void setHakmilik1(Hakmilik hakmilik1) {
        this.hakmilik1 = hakmilik1;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public String getLokasi2() {
        return lokasi2;
    }

    public void setLokasi2(String lokasi2) {
        this.lokasi2 = lokasi2;
    }

    public String getNamaDaerah() {
        return NamaDaerah;
    }

    public void setNamaDaerah(String NamaDaerah) {
        this.NamaDaerah = NamaDaerah;
    }
    
    public List<HakmilikPermohonan> getHakPSebelumList() {
        return hakPSebelumList;
    }

    public void setHakPSebelumList(List<HakmilikPermohonan> hakPSebelumList) {
        this.hakPSebelumList = hakPSebelumList;
    }
   
//    public PermohonanKertasKandungan getKertasBil() {
//        return kertasBil;
//    }
//
//    public void setKertasBil(PermohonanKertasKandungan kertasBil) {
//        this.kertasBil = kertasBil;
//    }
//
//    public PermohonanKertasKandungan getKertasTahun() {
//        return kertasTahun;
//    }
//
//    public void setKertasTahun(PermohonanKertasKandungan kertasTahun) {
//        this.kertasTahun = kertasTahun;
//    }
}

