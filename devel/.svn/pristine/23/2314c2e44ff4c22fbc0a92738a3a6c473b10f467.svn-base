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

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/borang_mmkn_sek4_ns")
public class PengambilanMMKNSek4NSActionBean extends AbleActionBean{

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
    private PermohonanKertasKandungan permohonanKertasKandungan3;
//    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan4;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan permohonanKertasKandungan7;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan headingObj;

    private int bil = 0;
    private String kandungan;
    private String idKandungan;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan3;
//    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan4;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan7;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Hakmilik hakmilik;
    private String tujuan;
    private String heading;
    private int count5=0;
    private int count7=0;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private List senaraiSyorPentadbir[] = new ArrayList [ 10 ];
    private List senaraiSyorPengarah[] = new ArrayList [ 10 ];
    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN TANAH SEK 4 NEGERI SEMBILAN";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private BigDecimal totalLuas = new BigDecimal(0.00);
    StringBuffer noLotList;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/pengambilan_MMKN_Sek4.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/pengambilan_MMKN_Sek4.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.debug(idPermohonan + "ni id mohon dier");
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        noLotList = new StringBuffer();
        for(HakmilikPermohonan hp1:hakmilikPermohonanList){
            noLotList = noLotList.append("Lot ").append(hp1.getHakmilik().getNoLot()).append(", ").append(hp1.getHakmilik().getBandarPekanMukim().getNama()).append(", ");
        }

//        for(HakmilikPermohonan hakmilikPermohonan:hakmilikPermohonanList){
//            BigDecimal luas = hakmilikPermohonan.getHakmilik().getLuas();
//            String kodUOM = hakmilikPermohonan.getHakmilik().getKodUnitLuas().getNama();
//            if(kodUOM.equals("Hektar")){
//                totalLuas = totalLuas.add(luas.multiply(new BigDecimal(10000)));
//            }else if(kodUOM.equals("Meter Persegi")){
//                totalLuas = totalLuas.add(luas);
//            }
//        }

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

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hp.getHakmilik();
        tujuan = "Kertas ini bertujuan untuk mendapatkan pertimbangan yang amat berhormat Menteri Besar Negeri Sembilan Darul Khusus ke atas "
                + "permohonan pengambilan tanah daripada "+ permohonan.getSenaraiPemohon().get(0).getPihak().getNama()+ " seluas lebih kurang "
                + totalLuas + " " + uom + " untuk " + permohonan.getSebab() +" "+ noLotList + " Daerah " + hakmilik.getDaerah().getNama()
                +", seperti di Borang A mengikut seksyen 4 Akta Pengambilan Tanah 1960 dibawah perwakilan kuasa seperti yang "
                + "disytiharkan dalam warta kerajaan Negeri Sembilan No:148 bertarikh 26.3.1987. Kawasan yang dimaksudkan adalah "
                + "seperti yang bertanda merah dalam pelan di lampiran A.";


        String temp = "PERMOHONAN PENGAMBILAN TANAH DARIPADA "+ permohonan.getSenaraiPemohon().get(0).getPihak().getNama()+" SELUAS LEBIH KURANG "
                + totalLuas + " " + uom + " UNTUK " + permohonan.getSebab() + " DI "
                + noLotList + " DAERAH " + hakmilik.getDaerah().getNama() + " DI BAWAH SEKSYEN 4 AKTA PENGAMBILAN TANAH 1960.";

        heading = temp.toUpperCase();

        if (idPermohonan != null) {
            permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);

            if (permohonanKertas != null) {//
                kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 8);
                kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
//                tarikhmesyuarat = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
                headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
                tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 2);
                if(tujuanObj != null)
                    tujuan = tujuanObj.getKandungan();
                senaraiKertasKandungan3 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);
//                senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);
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
//                            for(int k=1;k<=kandList5.size();k++){
                                senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "5."+k;
                                senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),5,subtajuk1);
                            }
                    }
                }//if

                List<PermohonanKertasKandungan> kandList7 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand7 = new PermohonanKertasKandungan();
                kandList7 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),7);
                if(kandList7!=null && !kandList7.isEmpty()){
                    maxKertasKand7 = kandList7.get(0);
                    if(maxKertasKand7!=null){
                            String subtajuk = maxKertasKand7.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count7 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "7."+k;
                                senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk1);
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

//        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);
//
//            int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
//            for (int k = 1; k <= kira4; k++) {
//                if (senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
//                Long id = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan4.setKertas(permohonanKertas);
//                    permohonanKertasKandungan4.setBil(4);
//                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
//                    permohonanKertasKandungan4.setKandungan(kandungan);
//                    cawangan = permohonan.getCawangan();
//                    permohonanKertasKandungan4.setCawangan(cawangan);
//                    permohonanKertasKandungan4.setSubtajuk("4." + k);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
//            }


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

            count7 =  Integer.parseInt(getContext().getRequest().getParameter("count7"));

            for(int i=1;i<=count7;i++){
                String subtajuk = "7."+i;
                senaraiKertasKandungan7 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk);
                int rowCount7 =  Integer.parseInt(getContext().getRequest().getParameter("count7"+i));
                for (int k = 1; k <= rowCount7; k++) {
                    if (senaraiKertasKandungan7!=null && senaraiKertasKandungan7.size() != 0 && k <= senaraiKertasKandungan7.size()) {
                        Long id3 = senaraiKertasKandungan7.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan7 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan7 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan7.setKertas(permohonanKertas);
                    permohonanKertasKandungan7.setBil(7);
                    kandungan = getContext().getRequest().getParameter("syorPengarah"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan7.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan7.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan7.setCawangan(cawangan);
                    permohonanKertasKandungan7.setSubtajuk("7."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan7.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan7);
                }
            }//if table count


            if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
                if (kertasBil == null) {
                    kertasBil = new PermohonanKertasKandungan();

                }
                kertasBil.setKertas(permohonanKertas);
                kertasBil.setBil(8);
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
                kertasTahun.setBil(9);
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
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new RedirectResolution(PengambilanMMKNSek4NSActionBean.class, "locate");
    }

    public Resolution simpan2() {
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

//        senaraiKertasKandungan4 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),4);
//
//            int kira4 = Integer.parseInt(getContext().getRequest().getParameter("rowCount4"));
//            for (int k = 1; k <= kira4; k++) {
//                if (senaraiKertasKandungan4.size() != 0 && k <= senaraiKertasKandungan4.size()) {
//                Long id = senaraiKertasKandungan4.get(k - 1).getIdKandungan();
//                if (id != null) {
//                    permohonanKertasKandungan4 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
//                }
//                } else {
//                    permohonanKertasKandungan4 = new PermohonanKertasKandungan();
//                }
//                    permohonanKertasKandungan4.setKertas(permohonanKertas);
//                    permohonanKertasKandungan4.setBil(4);
//                    kandungan = getContext().getRequest().getParameter("kandungan4" + k);
//                    permohonanKertasKandungan4.setKandungan(kandungan);
//                    cawangan = permohonan.getCawangan();
//                    permohonanKertasKandungan4.setCawangan(cawangan);
//                    permohonanKertasKandungan4.setSubtajuk("4." + k);
//                    InfoAudit iaP = new InfoAudit();
//                    iaP.setTarikhMasuk(new Date());
//                    iaP.setDimasukOleh(peng);
//                    permohonanKertasKandungan4.setInfoAudit(iaP);
//                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan4);
//            }


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

            count7 =  Integer.parseInt(getContext().getRequest().getParameter("count7"));

            for(int i=1;i<=count7;i++){
                String subtajuk = "7."+i;
                senaraiKertasKandungan7 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk);
                int rowCount7 =  Integer.parseInt(getContext().getRequest().getParameter("count7"+i));
                for (int k = 1; k <= rowCount7; k++) {
                    if (senaraiKertasKandungan7!=null && senaraiKertasKandungan7.size() != 0 && k <= senaraiKertasKandungan7.size()) {
                        Long id3 = senaraiKertasKandungan7.get(k - 1).getIdKandungan();
                        if (id3 != null) {
                            permohonanKertasKandungan7 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        }
                    } else {
                        permohonanKertasKandungan7 = new PermohonanKertasKandungan();
                    }

                    permohonanKertasKandungan7.setKertas(permohonanKertas);
                    permohonanKertasKandungan7.setBil(7);
                    kandungan = getContext().getRequest().getParameter("syorPengarah"+i+ k);
                    if(kandungan==null){
                        permohonanKertasKandungan7.setKandungan("Tiada");
                    }else{
                        permohonanKertasKandungan7.setKandungan(kandungan);
                    }
                    cawangan = permohonan.getCawangan();
                    permohonanKertasKandungan7.setCawangan(cawangan);
                    permohonanKertasKandungan7.setSubtajuk("7."+ i+"."+str[k-1]);
                    InfoAudit iaP = new InfoAudit();
                    iaP.setTarikhMasuk(new Date());
                    iaP.setDimasukOleh(peng);
                    permohonanKertasKandungan7.setInfoAudit(iaP);
                    permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan7);
                }
            }//if table count


            if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
                if (kertasBil == null) {
                    kertasBil = new PermohonanKertasKandungan();

                }
                kertasBil.setKertas(permohonanKertas);
                kertasBil.setBil(8);
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
                kertasTahun.setBil(9);
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
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new RedirectResolution(PengambilanMMKNSek4NSActionBean.class, "locate");
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
        return new RedirectResolution(PengambilanMMKNSek4NSActionBean.class, "locate");
    }

    public Resolution deleteJabatanTeknikal() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String idKandungan1 = getContext().getRequest().getParameter("idKandungan1");
        try{
        permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan1));
        }catch(Exception e){
            logger.debug("Hapus empty record");

        }
        if (permohonanKertasKandungan1 != null) {
            pendudukanSementaraMMKNService.deleteKertasKandungan(permohonanKertasKandungan1);
        }


        String idKandungan2 = getContext().getRequest().getParameter("idKandungan2");
        try{
        permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(Long.parseLong(idKandungan2));
        }catch(Exception e){
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

//    public PermohonanKertasKandungan getPermohonanKertasKandungan4() {
//        return permohonanKertasKandungan4;
//    }
//
//    public void setPermohonanKertasKandungan4(PermohonanKertasKandungan permohonanKertasKandungan4) {
//        this.permohonanKertasKandungan4 = permohonanKertasKandungan4;
//    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan5() {
        return permohonanKertasKandungan5;
    }

    public void setPermohonanKertasKandungan5(PermohonanKertasKandungan permohonanKertasKandungan5) {
        this.permohonanKertasKandungan5 = permohonanKertasKandungan5;
    }

//    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan4() {
//        return senaraiKertasKandungan4;
//    }
//
//    public void setSenaraiKertasKandungan4(List<PermohonanKertasKandungan> senaraiKertasKandungan4) {
//        this.senaraiKertasKandungan4 = senaraiKertasKandungan4;
//    }

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

    public int getCount7() {
        return count7;
    }

    public void setCount7(int count7) {
        this.count7 = count7;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan7() {
        return permohonanKertasKandungan7;
    }

    public void setPermohonanKertasKandungan7(PermohonanKertasKandungan permohonanKertasKandungan7) {
        this.permohonanKertasKandungan7 = permohonanKertasKandungan7;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan7() {
        return senaraiKertasKandungan7;
    }

    public void setSenaraiKertasKandungan7(List<PermohonanKertasKandungan> senaraiKertasKandungan7) {
        this.senaraiKertasKandungan7 = senaraiKertasKandungan7;
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

}


