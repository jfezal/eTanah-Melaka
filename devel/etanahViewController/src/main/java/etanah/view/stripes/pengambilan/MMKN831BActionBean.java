


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
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/mmkn31b")
public class MMKN831BActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MMKN831BActionBean.class);
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
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanPihak penerima;

//    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan21;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan22;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan23;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan24;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
//    private String tajuk;
    private Hakmilik hakmilik;
    private String tujuan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private int count4=0;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private List senaraiPerakuanPengarah[] = new ArrayList [ 5 ];
    private List<Pemohon> listPemohon;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String masa;
    private String subtajuk;
    private String heading;
    private String lokasi;
    String namaProjek;
    BigDecimal totalLuas = new BigDecimal(0.00);
    private String tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH 3(1)(b)";

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831bc/pengambilan_MMKN_31b_new.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        for(int k=1;k<=count4;k++){
            senaraiPerakuanPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count4=0;

        String pem = "";
        StringBuffer noLotList = new StringBuffer();
        totalLuas = BigDecimal.ZERO;
        BigDecimal luas = BigDecimal.ZERO;
        String uom = "Meter Persegi";

        if(permohonan != null){
            HakmilikPermohonan hp = new HakmilikPermohonan();


            listPemohon = permohonan.getSenaraiPemohon();
            if(listPemohon != null && listPemohon.size() > 0 && listPemohon.get(0).getPihak() != null)
                pem = listPemohon.get(0).getPihak().getNama();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            noLotList = new StringBuffer();
            for(HakmilikPermohonan hp1:hakmilikPermohonanList){
                noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ");
            }


            for(HakmilikPermohonan hakmilikPermohonan:hakmilikPermohonanList){
                luas = BigDecimal.ZERO;
                luas = hakmilikPermohonan.getLuasTerlibat();
                String kodUOM = "";
                if(hakmilikPermohonan.getKodUnitLuas() != null)
                    kodUOM = hakmilikPermohonan.getKodUnitLuas().getNama();
                if(kodUOM.equals("Hektar")){
                    totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
                }else if(kodUOM.equals("Meter Persegi")){
                    totalLuas = totalLuas.add(luas);
                }
            }

            if (totalLuas.compareTo(new BigDecimal(10000)) == 1){
                totalLuas = totalLuas.divide(new BigDecimal(10000));
                uom = "Hektar";
            }

            List<PermohonanPihak> listPenerima;
            listPenerima = permohonan.getSenaraiPihak();

            if (!(listPenerima.isEmpty())){
                penerima = listPenerima.get(0);
            }

            if(!hakmilikPermohonanList.isEmpty()){
                for (int w = 0; w < hakmilikPermohonanList.size(); w++){
                    hp = hakmilikPermohonanList.get(w);
                    if (hp.getHakmilik() !=null){
                        hakmilik = hp.getHakmilik();
//                        StringBuilder sb = new StringBuilder();

                        if(w==0){
                            lokasi = hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+", " +
                                    hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                        }

                        if(w > 0){
                            if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                                lokasi = lokasi + hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+", " +
                                        hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                            } else if (w == (hakmilikPermohonanList.size() - 1)) {
                                lokasi = lokasi + " dan " + hakmilik.getNoLot()+", "+hakmilik.getKodHakmilik().getKod()+ hakmilik.getLuas()+"," +
                                        hakmilik.getBandarPekanMukim().getNama()+", DAERAH " + hakmilik.getDaerah().getNama()+", ";
                            }
                        }

                    tujuan = "Tujuan kertas ini ialah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri mengenai permohonan pengambilan tanah "
                            + "di bawah Seksyen 3(1)(b) Akta Pengambilan Tanah 1960, di atas sebahagian "+ noLotList + " seluas "
                            + totalLuas + " " + uom + " di " + hakmilik.getBandarPekanMukim().getNama() +", Daerah " + hakmilik.getDaerah().getNama()
                            +" untuk tujuan "+ permohonan.getSebab() +" oleh " + pem + ".";
                    }
                }
            }
        }



        String temp = "PERMOHONAN PENGAMBILAN TANAH DI ATAS SEBAHAGIAN "+ noLotList +" SELUAS " + totalLuas + " " + uom + " DI "
                + hakmilik.getBandarPekanMukim().getNama() + " DAERAH " + hakmilik.getDaerah().getNama() + " UNTUK TUJUAN " + permohonan.getSebab() +" OLEH " + pem + ".";

//        String temp = "PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT "+ lokasi + " BAGI " + permohonan.getSebab() + ".";
        heading = temp.toUpperCase();

        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);

            if (permohonanKertas != null) {

                tajuk = permohonanKertas.getTajuk();
//                if(tajuk.length()>0){
//                    kertasBil = tajuk.substring(0, tajuk.length()-5);
//                    kertasTahun = tajuk.substring(tajuk.length()-4, tajuk.length());
//                }

                if(permohonanKertas.getTarikhSidang() != null) {
                    String tarikhSidang = dateFormat.format(permohonanKertas.getTarikhSidang());
                    tarikhmesyuarat = tarikhSidang.substring(0, 10);
                    jam = tarikhSidang.substring(11, 13);
                    minit = tarikhSidang.substring(14, 16);
                    String ampm = tarikhSidang.substring(20, 22);
                    if(ampm.equalsIgnoreCase("AM")){
                        pagiPetang = "PAGI";
                    }else if(ampm.equalsIgnoreCase("PM")){
                        pagiPetang = "PETANG";
                    }
                }
                tempat = permohonanKertas.getTempatSidang();

                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 6);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),21);
                senaraiKertasKandungan22 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),22);
                senaraiKertasKandungan23 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),23);
                senaraiKertasKandungan24 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),24);
                ringkasanPermohonan = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(),25);
                syorPengarah = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 3);
                jawatanKuasa=pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 4);


                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);


                List<PermohonanKertasKandungan> kandList4 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand4 = new PermohonanKertasKandungan();
                kandList4 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),5);
                if(kandList4!=null && !kandList4.isEmpty()){
                    maxKertasKand4 = kandList4.get(0);
                    if(maxKertasKand4!=null){
                            String subtajuk = maxKertasKand4.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count4 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiPerakuanPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "5."+k;
                                senaraiPerakuanPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk1);
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

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

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

//        kertasBil = getContext().getRequest().getParameter("kertasBil");
//        kertasTahun = getContext().getRequest().getParameter("kertasTahun");
//        if(kertasBil == null)
//            kertasBil = "Tiada Data";
        permohonanKertas.setTajuk(tajuk);
//
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
//            tempat = "Tiada Data";
//        permohonanKertas.setTempatSidang(tempat);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);


        if (getContext().getRequest().getParameter("heading") != null) {
            infoAudit = new InfoAudit();
            if (headingObj == null) {
                headingObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = headingObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            headingObj.setKertas(permohonanKertas);
            headingObj.setBil(6);
            kandungan = getContext().getRequest().getParameter("heading");
            if(kandungan==null){
                    headingObj.setKandungan("Tiada");
                }else{
                    headingObj.setKandungan(kandungan);
            }
            headingObj.setCawangan(permohonan.getCawangan());
            headingObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(headingObj);
        }

        if (getContext().getRequest().getParameter("tujuan") != null) {
            infoAudit = new InfoAudit();
            if (tujuanObj == null) {
                tujuanObj = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }else {
                infoAudit = tujuanObj.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            tujuanObj.setKertas(permohonanKertas);
            tujuanObj.setBil(1);
            kandungan = getContext().getRequest().getParameter("tujuan");
            if(kandungan==null){
                    tujuanObj.setKandungan("Tiada");
                }else{
                    tujuanObj.setKandungan(kandungan);
            }
            tujuanObj.setCawangan(permohonan.getCawangan());
            tujuanObj.setInfoAudit(infoAudit);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(tujuanObj);
        }


        senaraiKertasKandungan21 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),21);
        int kira1 = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));

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
            if(kandungan==null)
                kandungan = "TIADA DATA";
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        senaraiKertasKandungan22 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),22);
        int kira2 = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));

        for (int i = 1; i <= kira2; i++) {
            if (senaraiKertasKandungan22.size() != 0 && i <= senaraiKertasKandungan22.size()) {
                Long id = senaraiKertasKandungan22.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan2 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan2.setKertas(permohonanKertas);
            permohonanKertasKandungan2.setBil(22);
            kandungan = getContext().getRequest().getParameter("kandungan2" + i);
            if(kandungan==null)
                kandungan = "TIADA DATA";
            permohonanKertasKandungan2.setKandungan(kandungan);
            permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan2.setSubtajuk("2.2." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan2.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
        }

        senaraiKertasKandungan23 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),23);
        int kira3 = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));

        for (int i = 1; i <= kira3; i++) {
            if (senaraiKertasKandungan23.size() != 0 && i <= senaraiKertasKandungan23.size()) {
                Long id = senaraiKertasKandungan23.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan3 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan3 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan3.setKertas(permohonanKertas);
            permohonanKertasKandungan3.setBil(23);
            kandungan = getContext().getRequest().getParameter("kandungan3" + i);
            if(kandungan==null)
                kandungan = "TIADA DATA";
            permohonanKertasKandungan3.setKandungan(kandungan);
            permohonanKertasKandungan3.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan3.setSubtajuk("2.3." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan3.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan3);
        }

        senaraiKertasKandungan24 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),24);
        int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));

        for (int i = 1; i <= kira4; i++) {
            if (senaraiKertasKandungan24.size() != 0 && i <= senaraiKertasKandungan24.size()) {
                Long id = senaraiKertasKandungan24.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan4 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan4.setKertas(permohonanKertas);
            permohonanKertasKandungan4.setBil(24);
            kandungan = getContext().getRequest().getParameter("kandungan4" + i);
            if(kandungan==null)
                kandungan = "TIADA DATA";
            permohonanKertasKandungan4.setKandungan(kandungan);
            permohonanKertasKandungan4.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan4.setSubtajuk("2.4." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan4.setInfoAudit(iaP);
            pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan4);
        }

        if (ringkasanPermohonan == null) {
            ringkasanPermohonan = new PermohonanKertasKandungan();
        }
        ringkasanPermohonan.setKertas(permohonanKertas);
        ringkasanPermohonan.setBil(25);
        kandungan = getContext().getRequest().getParameter("ringkasanPermohonan.kandungan");
        if(kandungan==null)
            kandungan = "TIADA DATA";
        ringkasanPermohonan.setKandungan(kandungan);
        ringkasanPermohonan.setCawangan(permohonan.getCawangan());
        InfoAudit iaP = new InfoAudit();
        iaP.setTarikhMasuk(new Date());
        iaP.setDimasukOleh(peng);
        ringkasanPermohonan.setInfoAudit(iaP);
        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(ringkasanPermohonan);

        if (syorPengarah == null) {
            syorPengarah = new PermohonanKertasKandungan();
        }
        syorPengarah.setKertas(permohonanKertas);
        syorPengarah.setBil(3);
        kandungan = getContext().getRequest().getParameter("syorPengarah.kandungan");
        if(kandungan==null)
            kandungan = "TIADA DATA";
        syorPengarah.setKandungan(kandungan);
        syorPengarah.setCawangan(permohonan.getCawangan());
        InfoAudit iaSP = new InfoAudit();
        iaSP.setTarikhMasuk(new Date());
        iaSP.setDimasukOleh(peng);
        syorPengarah.setInfoAudit(iaSP);
        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(syorPengarah);


        jawatanKuasa.setKertas(permohonanKertas);
        jawatanKuasa.setBil(4);
        kandungan=getContext().getRequest().getParameter("jawatanKuasa.kandungan");
        if(kandungan==null)
        kandungan = "TIADA DATA";
        jawatanKuasa.setKandungan(kandungan);
        jawatanKuasa.setCawangan(permohonan.getCawangan());
        InfoAudit iaJ = new InfoAudit();
        iaJ.setTarikhMasuk(new Date());
        iaJ.setDimasukOleh(peng);
        jawatanKuasa.setInfoAudit(iaJ);
        pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(jawatanKuasa);


        count4 =  Integer.parseInt(getContext().getRequest().getParameter("count4"));
        for(int i=1;i<=count4;i++){
            String subtajuk = "5."+i;
            senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk);
            int rowCount4 =  Integer.parseInt(getContext().getRequest().getParameter("count4"+i));
            for (int k = 1; k <= rowCount4; k++) {
                if (senaraiKertasKandungan4!=null && senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
                    Long id3 = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan2 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan2.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan2 = new PermohonanKertasKandungan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                permohonanKertasKandungan2.setKertas(permohonanKertas);
                permohonanKertasKandungan2.setBil(5);
                kandungan = getContext().getRequest().getParameter("perakuanPengarah"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan2.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan2.setKandungan(kandungan);
                }

                permohonanKertasKandungan2.setCawangan(permohonan.getCawangan());
                permohonanKertasKandungan2.setSubtajuk("5."+ i+"."+str[k-1]);
                InfoAudit iaPP = new InfoAudit();
                iaPP.setTarikhMasuk(new Date());
                iaPP.setDimasukOleh(peng);
                permohonanKertasKandungan2.setInfoAudit(iaPP);
                pendudukanSementaraMMKNService.simpanPermohonanKertasKandungan(permohonanKertasKandungan2);
            }
        }//if table count


        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new RedirectResolution(MMKN831BActionBean.class, "locate");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKandungan = getContext().getRequest().getParameter("idKandungan");
        try{
        permohonanKertasKandungan = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan));
        }catch(Exception e){
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

      return new JSP("pengambilan/Kertas_Rencana_MMKN.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTable() {

        int bil = Integer.parseInt(getContext().getRequest().getParameter("bil"));

        List<PermohonanKertasKandungan> kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),bil);

        if(kandList!=null && !kandList.isEmpty()){
             PermohonanKertasKandungan maxKertasKand = kandList.get(0);
             if(maxKertasKand!=null){
                 String subtajuk = maxKertasKand.getSubtajuk();
                 String str = subtajuk.substring(2,3);
                 String subtajuk1 = bil+"."+str;
                 List<PermohonanKertasKandungan> kandList1 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),bil,subtajuk1);
                 for(PermohonanKertasKandungan kand : kandList1){
                     pendudukanSementaraMMKNService.deleteKertasKandungan(kand);
                 }
             }
         }

       rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);

      return new JSP("pengambilan/Kertas_Rencana_MMKN.jsp").addParameter("tab", "true");

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
    

}