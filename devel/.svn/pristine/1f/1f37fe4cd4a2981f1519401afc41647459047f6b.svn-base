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
import etanah.model.LaporanTanah;
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
import etanah.service.RegService;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import etanah.service.PengambilanAduanService;
import org.apache.commons.lang.WordUtils;


@UrlBinding("/pengambilan/sek4ammkn")
public class MMKNSEK4AduanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MMKNSEK4AduanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PengambilanAduanService aduanService;

    IWorkflowContext ctx = null;
    private Permohonan permohonanSebelum;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private LaporanTanah laporanTanah;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan2;
//    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan kertasTahun;
    private PermohonanKertasKandungan kertasBilObj;
    private PermohonanKertasKandungan masaObj;
    private PermohonanKertasKandungan tarikhMesyuaratObj;
    private PermohonanKertasKandungan tempatObj;
    private PermohonanKertasKandungan syorPtgObj;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private int bil = 0;
//    private String masa;
    private String kandungan;
    private String idKandungan;
    private String tempat;
    private String syorPtg;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan2;
//    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiMasa;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> listPemohon;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private Hakmilik hakmilik;
    private String tujuan;
    private String tarikhMesyuarat;
    String tarikhDaftar;
    String namaProjek;
    private String heading;
//    private Boolean opFlag = false;
//    private final String tajuk = "KERTAS PERMOHONAN ADUAN KEROSAKAN PENGAMBILAN TANAH SEKSYEN 4 NEGERI MELAKA";
    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";
    private final String tajuk2 = "KERTAS PERMOHONAN ADUAN KEROSAKAN PENGAMBILAN TANAH SEK 4 NEGERI MELAKA";

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private String uom;
    private String masa;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String lokasi;
    private BigDecimal totalLuas = new BigDecimal(0.00);
    StringBuffer noLotList;
    //---------------------//
    private PermohonanKertasKandungan permohonanKertasKandungan3;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan permohonanKertasKandungan8;
    private PermohonanKertasKandungan ulasan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan8;
    private int count5=0;
    private int count8=0;
    private List<PermohonanPihak> listPP;
    private String namaPengadu;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }
    private List senaraiSyorPentadbir[] = new ArrayList [ 5 ];
    private List senaraiSyorPengarah[] = new ArrayList [ 5 ];
    //---------------------//




    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKN_SEK4A.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKN_SEK4A.jsp").addParameter("tab", "true");
    }




    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("ape masalahnye ni");
         String idSblm = "";
        if(permohonan.getPermohonanSebelum() != null)
        idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        if(idSblm!=null){
        Permohonan p= permohonanDAO.findById(idSblm);
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        noLotList = new StringBuffer();
        listPP=aduanService.findPihakByIdMohonList(idPermohonan);
//        for(HakmilikPermohonan hp1:hakmilikPermohonanList){
//            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ").append(hp1.getHakmilik().getBandarPekanMukim().getNama());
//        }

//        for(HakmilikPermohonan hakmilikPermohonan:hakmilikPermohonanList){
//            BigDecimal luas = hakmilikPermohonan.getLuasTerlibat();
//            String kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();
//            if(kodUOM.equals("Hektar")){
//                totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
//            }else if(kodUOM.equals("Meter Persegi")){
//                totalLuas = totalLuas.add(luas);
//            }
//        }
//
//        String uom = "Meter Persegi";
//        if (totalLuas.compareTo(new BigDecimal(10000)) == 1){
//            totalLuas = totalLuas.divide(new BigDecimal(10000));
//            uom = "Hektar";
//        }
//        for(PermohonanPihak pp:listPP){
//        namaPengadu=pp.getPihak().getNama();
//
//        }



//        HakmilikPermohonan hp = new HakmilikPermohonan();
//
//        hp = permohonan.getSenaraiHakmilik().get(0);
//        hakmilik = hp.getHakmilik();
//
//        tujuan = "Tujuan kertas ini dikemukakan adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat "
//                + "Kerajaan Negeri Melaka bagi permohonan aduan pengambilan tanah di atas "
//                + noLotList + " seluas " + totalLuas +" " + uom + ",di "
//                + hakmilik.getBandarPekanMukim().getNama() + "," + " Daerah " + hakmilik.getDaerah().getNama() + ", Melaka untuk tujuan "
//                + p.getSebab()+ " di bawah Seksyen 4, Akta Pengambilan Tanah 1960.";


      





        //----------------------------------------------MMKN ADUAN------------------------------------------------------------------//
        HakmilikPermohonan hp3 = new HakmilikPermohonan();
          for(HakmilikPermohonan hp1:hakmilikPermohonanList){
//            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ").append(hp1.getHakmilik().getBandarPekanMukim().getNama()).append(", ");
            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ");
        }

        totalLuas = BigDecimal.ZERO;
        BigDecimal luas = BigDecimal.ZERO;

        for(HakmilikPermohonan hakmilikPermohonan:hakmilikPermohonanList){
            luas = BigDecimal.ZERO;
            luas = hakmilikPermohonan.getLuasTerlibat();
            String kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();
            if(kodUOM.equals("Hektar")){
                totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
            }else if(kodUOM.equals("Meter Persegi")){
                totalLuas = totalLuas.add(luas);
            }
        }
        String uom = "Meter Persegi";
        if (totalLuas.compareTo(new BigDecimal(10000)) == 1){
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
                                    + "Kerajaan Negeri Melaka bagi permohonan aduan pengambilan tanah di atas "
                                    + noLotList + " Daerah "+ WordUtils.capitalizeFully(hakmilik.getDaerah().getNama()) + ", Melaka seluas " + totalLuas +" " + uom + " "
                                    + " untuk tujuan "
                                    + permohonan.getPermohonanSebelum().getSebab().toLowerCase()+ " di bawah Seksyen 4, Akta Pengambilan Tanah 1960.";
//                                    + WordUtils.capitalizeFully(permohonan.getSebab().toLowerCase())+



                        }
                    }
                }


        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hp.getHakmilik();


   
        String temp = "PERMOHONAN ADUAN KEROSAKAN PENGAMBILAN TANAH SEKSYEN 4 DARIPADA "+ permohonan.getPermohonanSebelum().getSenaraiPemohon().get(0).getPihak().getNama() +" SELUAS LEBIH KURANG "
               + totalLuas + " " + uom + " OLEH "+ p.getSenaraiPemohon().get(0).getPihak().getNama() + " UNTUK " + p.getSebab().toUpperCase() + " DI "
                + noLotList + " DAERAH " + hakmilik.getDaerah().getNama() + " DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";

        heading = temp.toUpperCase();


        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);

            if (permohonanKertas != null) {//
                kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 11);
                kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
//                tarikhmesyuarat = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                if(tujuanObj != null)
                    tujuan = tujuanObj.getKandungan();
                ulasan1 = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);
                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);
                senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);

                List<PermohonanKertasKandungan> kandList5 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand5 = new PermohonanKertasKandungan();
                kandList5 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),5);
                if(kandList5!=null && !kandList5.isEmpty()){
                    maxKertasKand5 = kandList5.get(0);
                    if(maxKertasKand5!=null){
                            String subtajuk = maxKertasKand5.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count5 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "5."+k;
                                senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk1);
                            }
                    }
                }//if

                List<PermohonanKertasKandungan> kandList8 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand8 = new PermohonanKertasKandungan();
                kandList8 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),8);
                if(kandList8!=null && !kandList8.isEmpty()){
                    maxKertasKand8 = kandList8.get(0);
                    if(maxKertasKand8!=null){
                            String subtajuk = maxKertasKand8.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count8 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "8."+k;
                                senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),8,subtajuk1);
                            }
                    }
                }//if

            }
            if(kertasTahun == null) {
                kertasTahun = new PermohonanKertasKandungan();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                kertasTahun.setKandungan((sdf.format(new java.util.Date())).toString());
            }
        }

    }
    }
  public Resolution simpan() {
//        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        permohonan = permohonanDAO.findById(idPermohonan);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
        System.out.println("1");
        if (permohonanKertas == null) {
            System.out.println("2");
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
//        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

        if (getContext().getRequest().getParameter("heading") != null) {
                if (headingObj == null) {
                    headingObj = new PermohonanKertasKandungan();

                }
                headingObj.setKertas(permohonanKertas);
                headingObj.setBil(1);
                kandungan = getContext().getRequest().getParameter("heading");
                headingObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                headingObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                headingObj.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(2);
            kandungan = getContext().getRequest().getParameter("tujuan");
            tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tujuanObj.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan3.size() != 0 && i <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            permohonanKertasKandungan3.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan3.setCawangan(cawangan);
            permohonanKertasKandungan3.setSubtajuk("3.1." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);

            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int j = 1; j <= kira3; j++) {
                if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan4.setKertas(permohonanKertas);
                    permohonanKertasKandungan4.setBil(4);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + j);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("4." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
            }


            count5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"));

            for(int i=1;i<=count5;i++){
                String subtajuk = "5."+i;
                senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
                int rowCount5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"+i));
                for (int k = 1; k <= rowCount5; k++) {
                    if (senaraiKertasKandungan5!=null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
                        Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan5.setKertas(permohonanKertas);
                    permohonanKertasKandungan5.setBil(5);
                    kandungan = getContext().getRequest().getParameter("syorPentadbir"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan5.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan5.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan5.setCawangan(cawangan);
                    permohonanKertasKandungan5.setSubtajuk("5."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan5.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan5);
                }
            }//if table count

            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
            int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
            for (int k = 1; k <= kira6; k++) {
                if (senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan6.setKertas(permohonanKertas);
                    permohonanKertasKandungan6.setBil(6);
                    kandungan = getContext().getRequest().getParameter("kandungan6" + k);
                    permohonanKertasKandungan6.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan6.setCawangan(cawangan);
                    permohonanKertasKandungan6.setSubtajuk("6." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan6.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan6);
            }

            if (getContext().getRequest().getParameter("ulasan1.kandungan") != null) {
            if (ulasan1 == null) {
                ulasan1 = new PermohonanKertasKandungan();
            }
            ulasan1.setKertas(permohonanKertas);
            ulasan1.setBil(7);
            kandungan = getContext().getRequest().getParameter("ulasan1.kandungan");
            ulasan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            ulasan1.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            ulasan1.setInfoAudit(iaP);
//            permohonanKertasKandunganDAO.saveOrUpdate(ulasan1);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasan1);
        }else
            {
            if (ulasan1 == null) {
                    ulasan1 = new PermohonanKertasKandungan();
                }
                ulasan1.setKertas(permohonanKertas);
                ulasan1.setBil(7);
                kandungan = "TIADA DATA";
                ulasan1.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasan1.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasan1.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(ulasan1);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ulasan1);
            }

            count8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"));

            for(int i=1;i<=count8;i++){
                String subtajuk = "8."+i;
                senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),8,subtajuk);
                int rowCount8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"+i));
                for (int k = 1; k <= rowCount8; k++) {
                    if (senaraiKertasKandungan8!=null && senaraiKertasKandungan8.size() != 0 && k <= senaraiKertasKandungan8.size()) {
                        Long id3 = senaraiKertasKandungan8.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan8 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan8.setKertas(permohonanKertas);
                    permohonanKertasKandungan8.setBil(8);
                    kandungan = getContext().getRequest().getParameter("syorPengarah"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan8.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan8.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan8.setCawangan(cawangan);
                    permohonanKertasKandungan8.setSubtajuk("8."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan8.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
                    pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan8);
                }
            }//if table count


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
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasBil);
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
//                permohonanKertasKandunganDAO.saveOrUpdate(kertasTahun);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(kertasTahun);
             }

//             if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//                if (tarikhmesyuarat == null) {
//                    tarikhmesyuarat = new PermohonanKertasKandungan();
//
//                }
//                tarikhmesyuarat.setKertas(permohonanKertas);
//                tarikhmesyuarat.setBil(10);
//                kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//                tarikhmesyuarat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tarikhmesyuarat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tarikhmesyuarat.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//            }




//        tx.commit();
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/MMKN_SEK4A.jsp").addParameter("tab", "true");
    }


    public Resolution simpanPtg() {
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
                if (headingObj == null) {
                    headingObj = new PermohonanKertasKandungan();

                }
                headingObj.setKertas(permohonanKertas);
                headingObj.setBil(1);
                kandungan = getContext().getRequest().getParameter("heading");
                headingObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                headingObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                headingObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(2);
            kandungan = getContext().getRequest().getParameter("tujuan");
            tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            tujuanObj.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
        }

        senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan3.size() != 0 && i <= senaraiKertasKandungan3.size()) {
                Long id = senaraiKertasKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            permohonanKertasKandungan3.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan3.setCawangan(cawangan);
            permohonanKertasKandungan3.setSubtajuk("3.1." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan3);
        }

            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);

            int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
            for (int j = 1; j <= kira3; j++) {
                if (senaraiKertasKandungan4.size() != 0 && j <= senaraiKertasKandungan4.size()) {
                Long id = senaraiKertasKandungan4.get(j - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan4.setKertas(permohonanKertas);
                    permohonanKertasKandungan4.setBil(4);
                    kandungan = getContext().getRequest().getParameter("kandungan4" + j);
                    permohonanKertasKandungan4.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan4.setCawangan(cawangan);
                    permohonanKertasKandungan4.setSubtajuk("4." + j);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan4.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
            }


            count5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"));

            for(int i=1;i<=count5;i++){
                String subtajuk = "5."+i;
                senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
                int rowCount5 =  Integer.parseInt(getContext().getRequest().getParameter("count5"+i));
                for (int k = 1; k <= rowCount5; k++) {
                    if (senaraiKertasKandungan5!=null && senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
                        Long id3 = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan5.setKertas(permohonanKertas);
                    permohonanKertasKandungan5.setBil(5);
                    kandungan = getContext().getRequest().getParameter("syorPentadbir"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan5.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan5.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan5.setCawangan(cawangan);
                    permohonanKertasKandungan5.setSubtajuk("5."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan5.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
                }
            }//if table count

            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),6);
            int kira6 = Integer.parseInt(getContext().getRequest().getParameter("rowCount6"));
            for (int k = 1; k <= kira6; k++) {
                if (senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
                Long id = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
                } else {
                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                }
                    permohonanKertasKandungan6.setKertas(permohonanKertas);
                    permohonanKertasKandungan6.setBil(6);
                    kandungan = getContext().getRequest().getParameter("kandungan6" + k);
                    permohonanKertasKandungan6.setKandungan(kandungan);
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan6.setCawangan(cawangan);
                    permohonanKertasKandungan6.setSubtajuk("6." + k);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan6.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
            }

            if (getContext().getRequest().getParameter("ulasan1.kandungan") != null) {
            if (ulasan1 == null) {
                ulasan1 = new PermohonanKertasKandungan();
            }
            ulasan1.setKertas(permohonanKertas);
            ulasan1.setBil(7);
            kandungan = getContext().getRequest().getParameter("ulasan1.kandungan");
            ulasan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            ulasan1.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            ulasan1.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(ulasan1);
        }else
            {
            if (ulasan1 == null) {
                    ulasan1 = new PermohonanKertasKandungan();
                }
                ulasan1.setKertas(permohonanKertas);
                ulasan1.setBil(7);
                kandungan = "TIADA DATA";
                ulasan1.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                ulasan1.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                ulasan1.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(ulasan1);
            }

            count8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"));

            for(int i=1;i<=count8;i++){
                String subtajuk = "8."+i;
                senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),8,subtajuk);
                int rowCount8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"+i));
                for (int k = 1; k <= rowCount8; k++) {
                    if (senaraiKertasKandungan8!=null && senaraiKertasKandungan8.size() != 0 && k <= senaraiKertasKandungan8.size()) {
                        Long id3 = senaraiKertasKandungan8.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan8 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan8.setKertas(permohonanKertas);
                    permohonanKertasKandungan8.setBil(8);
                    kandungan = getContext().getRequest().getParameter("syorPengarah"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan8.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan8.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan8.setCawangan(cawangan);
                    permohonanKertasKandungan8.setSubtajuk("8."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan8.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
                }
            }//if table count


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

//             if (getContext().getRequest().getParameter("tarikhmesyuarat.kandungan") != null) {
//                if (tarikhmesyuarat == null) {
//                    tarikhmesyuarat = new PermohonanKertasKandungan();
//
//                }
//                tarikhmesyuarat.setKertas(permohonanKertas);
//                tarikhmesyuarat.setBil(10);
//                kandungan = getContext().getRequest().getParameter("tarikhmesyuarat.kandungan");
//                tarikhmesyuarat.setKandungan(kandungan);
//                cawangan = permohonan.getCawangan();
//                tarikhmesyuarat.setCawangan(cawangan);
//                InfoAudit iaP = new InfoAudit();
//                iaP.setTarikhMasuk(new Date());
//                iaP.setDimasukOleh(peng);
//                tarikhmesyuarat.setInfoAudit(iaP);
//                permohonanKertasKandunganDAO.saveOrUpdate(tarikhmesyuarat);
//            }

        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
//        return new RedirectResolution(MMKNSEK4AduanActionBean.class, "locate");
        return new JSP("pengambilan/melaka/seksyen4/MMKN_SEK4A.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try{
        permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        }catch(Exception e){
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
        return new RedirectResolution(MMKNSEK4AduanActionBean.class, "locate");
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

    public PermohonanKertasKandungan getPermohonanKertasKandungan2() {
        return permohonanKertasKandungan2;
    }

    public void setPermohonanKertasKandungan2(PermohonanKertasKandungan permohonanKertasKandungan2) {
        this.permohonanKertasKandungan2 = permohonanKertasKandungan2;
    }

//    public PermohonanKertasKandungan getPermohonanKertasKandungan3() {
//        return permohonanKertasKandungan3;
//    }
//
//    public void setPermohonanKertasKandungan3(PermohonanKertasKandungan permohonanKertasKandungan3) {
//        this.permohonanKertasKandungan3 = permohonanKertasKandungan3;
//    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
        return permohonanKertasKandungan4;
    }

    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
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

//    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan3() {
//        return senaraiKertasKandungan3;
//    }
//
//    public void setSenaraiKertasKandungan3(List<PermohonanKertasKandungan> senaraiKertasKandungan3) {
//        this.senaraiKertasKandungan3 = senaraiKertasKandungan3;
//    }

    
    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan1() {
        return permohonanKertasKandungan1;
    }

    public void setPermohonanKertasKandungan1(PermohonanKertasKandungan permohonanKertasKandungan1) {
        this.permohonanKertasKandungan1 = permohonanKertasKandungan1;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
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

    public PermohonanKertasKandungan getKertasBilObj() {
        return kertasBilObj;
    }

    public void setKertasBilObj(PermohonanKertasKandungan kertasBilObj) {
        this.kertasBilObj = kertasBilObj;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }


//    public String getMasa() {
//        return masa;
//    }
//
//    public void setMasa(String masa) {
//        this.masa = masa;
//    }

    public PermohonanKertasKandungan getMasaObj() {
        return masaObj;
    }

    public void setMasaObj(PermohonanKertasKandungan masaObj) {
        this.masaObj = masaObj;
    }

    public PermohonanKertasKandungan getTarikhmesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhmesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public PermohonanKertasKandungan getTempatObj() {
        return tempatObj;
    }

    public void setTempatObj(PermohonanKertasKandungan tempatObj) {
        this.tempatObj = tempatObj;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public PermohonanKertasKandungan getSyorPtgObj() {
        return syorPtgObj;
    }

    public void setSyorPtgObj(PermohonanKertasKandungan syorPtgObj) {
        this.syorPtgObj = syorPtgObj;
    }

    public PermohonanKertasKandungan getKertasTahun() {
        return kertasTahun;
    }

    public void setKertasTahun(PermohonanKertasKandungan kertasTahun) {
        this.kertasTahun = kertasTahun;
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

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public List<PermohonanKertasKandungan> getSenaraiMasa() {
        return senaraiMasa;
    }

    public void setSenaraiMasa(List<PermohonanKertasKandungan> senaraiMasa) {
        this.senaraiMasa = senaraiMasa;
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

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public PermohonanKertasKandungan getTarikhMesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhMesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }
  public StringBuffer getNoLotList() {
        return noLotList;
    }

    public void setNoLotList(StringBuffer noLotList) {
        this.noLotList = noLotList;
    }

    public BigDecimal getTotalLuas() {
        return totalLuas;
    }

    public void setTotalLuas(BigDecimal totalLuas) {
        this.totalLuas = totalLuas;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    private PermohonanKertasKandungan tarikhmesyuarat;

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

    public PermohonanKertasKandungan getTarikhmesyuarat() {
        return tarikhmesyuarat;
    }

    public void setTarikhmesyuarat(PermohonanKertasKandungan tarikhmesyuarat) {
        this.tarikhmesyuarat = tarikhmesyuarat;
    }

    public PermohonanKertasKandungan getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(PermohonanKertasKandungan ulasan1) {
        this.ulasan1 = ulasan1;
    }
    public PermohonanKertasKandungan getKertasBil() {
        return kertasBil;
    }

    public void setKertasBil(PermohonanKertasKandungan kertasBil) {
        this.kertasBil = kertasBil;
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
}

