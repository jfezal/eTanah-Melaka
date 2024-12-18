package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Massita
 */

@UrlBinding("/pengambilan/mmk_LaluLetrik")
public class Mmk_IzinLaluAktaBekalanLetrikActionBean extends AbleActionBean{
    private static Logger logger = Logger.getLogger(Mmk_IzinLaluAktaBekalanLetrikActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private KodCawangan cawangan;
    private String stageId;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;

    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String lokasi;
    private String kmno;

    private PermohonanKertasKandungan kertasTahun;
    private PermohonanKertasKandungan kertasBil;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan tujuanObj;
    private PermohonanKertasKandungan huraianPtgObj;
    private PermohonanKertasKandungan tarikhMesyuaratObj;
    private PermohonanKertasKandungan permohonanKertasKandungan1;
    private PermohonanKertasKandungan permohonanKertasKandungan5;
    private PermohonanKertasKandungan permohonanKertasKandungan6;
    private PermohonanKertasKandungan permohonanKertasKandungan8;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan1;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan5;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan6;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan8;
    private String subtajuk;
    private String idKandungan;
    private String kandungan;
    private String heading;
    private int count6=0;
    private int count8=0;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private List senaraiSyorPentadbir[] = new ArrayList [ 5 ];
    private List senaraiSyorPengarah[] = new ArrayList [ 5 ];
    private final String tajuk = "KERTAS PERMOHONAN IZIN LALU DI BAWAH BEKALAN ELETRIK NEGERI SEMBILAN";

    //ptg
    @DefaultHandler
    public Resolution showForm1() {
        getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/mmk_IzinLaluAktaBekalanLetrik.jsp").addParameter("tab", "true");
    }

    //ptPengambilanPtg
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/mmk_IzinLaluAktaBekalanLetrik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        for(int k=1;k<=count6;k++){
            senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count6=0;
        for(int k=1;k<=count8;k++){
            senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
        }
        count8=0;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null) ;
        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        String pemohonPihakNama = " ";
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
            pemohonPihakNama = pemohon.getPihak().getNama();
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        if(!hakmilikPermohonanList.isEmpty()){

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            if(hp.getHakmilik() != null){
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                        }
                    }
                }
            }
        }
        System.out.println("lokasi------------------"+lokasi);

        tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Majlis Mesyuarat Negeri Sembilan Darul Khusus, terhadap permohonan " + permohonan.getKodUrusan().getNama() + " di " + lokasi + permohonan.getSebab() + ".";

        String gunaTanah = "";
        for(HakmilikPermohonan hakmilikPermohonan :hakmilikPermohonanList) {
            if(hakmilikPermohonan.getHakmilik().getKegunaanTanah() != null)
            gunaTanah = gunaTanah + " " +hakmilikPermohonan.getHakmilik().getKegunaanTanah().getNama();
        }
        

        String temp = "PERMOHONAN DARIPADA "+ pemohonPihakNama+" UNTUK "+ permohonan.getKodUrusan().getNama()+" BAGI "
                +lokasi+" UNTUK TUJUAN "+permohonan.getSebab()+ " .";
        heading = temp.toUpperCase();

       if (idPermohonan != null) {
           System.out.println("----rehydrate-----");

           permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);
           System.out.println("--------permohonanKertas======"+permohonanKertas);

           if (permohonanKertas != null) {//
                
               kertasBil = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 1);
               kertasTahun = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 10);
               tarikhMesyuaratObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
               headingObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 8);
               tujuanObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 5);
               senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 2);
               senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(), 3);
               senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),4,subtajuk);
               huraianPtgObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 9);
               senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),6,subtajuk);

                if(tujuanObj != null)
                    tujuan = tujuanObj.getKandungan().toLowerCase();
                if(huraianPtgObj != null)
                    huraianPtg = huraianPtgObj.getKandungan().toLowerCase();
                if(tarikhMesyuaratObj != null)
                    tarikhMesyuarat = tarikhMesyuaratObj.getKandungan().toLowerCase();

                List<PermohonanKertasKandungan> kandList6 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand6 = new PermohonanKertasKandungan();
                kandList6 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),4);
                if(kandList6!=null && !kandList6.isEmpty()){
                    maxKertasKand6 = kandList6.get(0);
                    if(maxKertasKand6!=null){
                            String subtajuk = maxKertasKand6.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count6 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPentadbir[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "4."+k;
                                senaraiSyorPentadbir[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),4,subtajuk1);
                            }
                    }
                }//if

                List<PermohonanKertasKandungan> kandList8 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand8 = new PermohonanKertasKandungan();
                kandList8 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),6);
                if(kandList8!=null && !kandList8.isEmpty()){
                    maxKertasKand8 = kandList8.get(0);
                    if(maxKertasKand8!=null){
                            String subtajuk = maxKertasKand8.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count8 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "6."+k;
                                senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),6,subtajuk1);
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

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        
        InfoAudit infoAudit = new InfoAudit();
        
        if (permohonanKertas == null) {
            permohonanKertas = new PermohonanKertas();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }else {
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }
        
        KodDokumen doc = new KodDokumen();
        doc.setKod("MMKN");
        KodCawangan cawangan = permohonan.getCawangan();
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setTajuk(tajuk);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(cawangan);
        permohonanKertas.setKodDokumen(doc);
//        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertas = pendudukanSementaraMMKNService.simpanPermohonanKertas(permohonanKertas);

        senaraiKertasKandungan1 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),2);

        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));

        for (int i = 1; i <= kira; i++) {
            if (senaraiKertasKandungan1.size() != 0 && i <= senaraiKertasKandungan1.size()) {
                Long id = senaraiKertasKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(2);
            kandungan = getContext().getRequest().getParameter("kandungan1" + i);
            permohonanKertasKandungan1.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            permohonanKertasKandungan1.setCawangan(cawangan);
            permohonanKertasKandungan1.setSubtajuk("2.1." + i);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan1);
//                }
        }

        senaraiKertasKandungan5 = pendudukanSementaraMMKNService.findByKertas(permohonanKertas.getIdKertas(),3);

        int kira5 = Integer.parseInt(getContext().getRequest().getParameter("rowCount5"));
        for (int k = 1; k <= kira5; k++) {
            infoAudit = new InfoAudit();
            if (senaraiKertasKandungan5.size() != 0 && k <= senaraiKertasKandungan5.size()) {
                Long id = senaraiKertasKandungan5.get(k - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan5 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id);
                    infoAudit = permohonanKertasKandungan5.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(peng);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                }
            }else {
                permohonanKertasKandungan5 = new PermohonanKertasKandungan();
                infoAudit.setDimasukOleh(peng);
                infoAudit.setTarikhMasuk(new java.util.Date());
            }
            permohonanKertasKandungan5.setKertas(permohonanKertas);
            permohonanKertasKandungan5.setBil(3);
            kandungan = getContext().getRequest().getParameter("kandungan5" + k);
            permohonanKertasKandungan5.setKandungan(kandungan);
            permohonanKertasKandungan5.setCawangan(cawangan);
            permohonanKertasKandungan5.setSubtajuk("3." + k);
            permohonanKertasKandungan5.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan5);
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
            tujuanObj.setBil(5);
            kandungan = getContext().getRequest().getParameter("tujuan");
            tujuanObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            tujuanObj.setCawangan(cawangan);
            tujuanObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(tujuanObj);
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

        if (getContext().getRequest().getParameter("kertasBil.kandungan") != null) {
                if (kertasBil == null) {
                    kertasBil = new PermohonanKertasKandungan();
                }
                kertasBil.setKertas(permohonanKertas);
                kertasBil.setBil(1);
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
            else
            {
            if (kertasBil == null) {
                    kertasBil = new PermohonanKertasKandungan();
                }
                kertasBil.setKertas(permohonanKertas);
                kertasBil.setBil(1);
                kandungan = "tiada data";
                kertasBil.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                kertasBil.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                kertasBil.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(kertasBil);
            }

        if (getContext().getRequest().getParameter("tarikhMesyuarat") != null) {
                if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(7);
                kandungan = getContext().getRequest().getParameter("tarikhMesyuarat");
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);
            }
            else
            {
            if (tarikhMesyuaratObj == null) {
                    tarikhMesyuaratObj = new PermohonanKertasKandungan();
                }
                tarikhMesyuaratObj.setKertas(permohonanKertas);
                tarikhMesyuaratObj.setBil(7);
                kandungan = "TIADA DATA";
                tarikhMesyuaratObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                tarikhMesyuaratObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                tarikhMesyuaratObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(tarikhMesyuaratObj);
            }

        
        
        count6 =  Integer.parseInt(getContext().getRequest().getParameter("count6"));
        for(int i=1;i<=count6;i++){
            String subtajuk = "4."+i;
            senaraiKertasKandungan6 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),4,subtajuk);
            int rowCount6 =  Integer.parseInt(getContext().getRequest().getParameter("count6"+i));
            for (int k = 1; k <= rowCount6; k++) {
                infoAudit = new InfoAudit();
                if (senaraiKertasKandungan6!=null && senaraiKertasKandungan6.size() != 0 && k <= senaraiKertasKandungan6.size()) {
                    Long id3 = senaraiKertasKandungan6.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan6 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan6.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan6 = new PermohonanKertasKandungan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                
                permohonanKertasKandungan6.setKertas(permohonanKertas);
                permohonanKertasKandungan6.setBil(4);
                kandungan = getContext().getRequest().getParameter("syorPentadbir"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan6.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan6.setKandungan(kandungan);
                }
//                permohonanKertasKandungan6.setCawangan(cawangan);
//                permohonanKertasKandungan6.setSubtajuk("4."+ i+"."+str[k-1]);
//                permohonanKertasKandungan6.setInfoAudit(infoAudit);
//                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);

                cawangan = permohonan.getCawangan();
                permohonanKertasKandungan6.setCawangan(cawangan);
                permohonanKertasKandungan6.setSubtajuk("4."+ i+"."+str[k-1]);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                permohonanKertasKandungan6.setInfoAudit(infoAudit);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan6);
            }
        }//if table count

        if (getContext().getRequest().getParameter("huraianPtg") != null) {
            infoAudit = new InfoAudit();
            if (huraianPtgObj == null) {
                huraianPtgObj = new PermohonanKertasKandungan();
            }
            huraianPtgObj.setKertas(permohonanKertas);
            huraianPtgObj.setBil(9);
            kandungan = getContext().getRequest().getParameter("huraianPtg");
            huraianPtgObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            huraianPtgObj.setCawangan(cawangan);
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            huraianPtgObj.setInfoAudit(iaP);
            permohonanKertasKandunganDAO.saveOrUpdate(huraianPtgObj);
        }else
            {
            if (huraianPtgObj == null) {
                    huraianPtgObj = new PermohonanKertasKandungan();
                }
                huraianPtgObj.setKertas(permohonanKertas);
                huraianPtgObj.setBil(9);
                kandungan = "tiada data";
                huraianPtgObj.setKandungan(kandungan);
                cawangan = permohonan.getCawangan();
                huraianPtgObj.setCawangan(cawangan);
                InfoAudit iaP = new InfoAudit();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
                huraianPtgObj.setInfoAudit(iaP);
                permohonanKertasKandunganDAO.saveOrUpdate(huraianPtgObj);
            }

        count8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"));
        for(int i=1;i<=count8;i++){
            String subtajuk = "6."+i;
            senaraiKertasKandungan8 = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),6,subtajuk);
            int rowCount8 =  Integer.parseInt(getContext().getRequest().getParameter("count8"+i));
            for (int k = 1; k <= rowCount8; k++) {
                if (senaraiKertasKandungan8!=null && senaraiKertasKandungan8.size() != 0 && k <= senaraiKertasKandungan8.size()) {
                    Long id3 = senaraiKertasKandungan8.get(k - 1).getIdKandungan();
                    if (id3 != null) {
                        permohonanKertasKandungan8 = pendudukanSementaraMMKNService.findkandunganByIdKandungan(id3);
                        infoAudit = permohonanKertasKandungan8.getInfoAudit();
                        infoAudit.setDiKemaskiniOleh(peng);
                        infoAudit.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    permohonanKertasKandungan8 = new PermohonanKertasKandungan();
                    infoAudit.setDimasukOleh(peng);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }

                permohonanKertasKandungan8.setKertas(permohonanKertas);
                permohonanKertasKandungan8.setBil(6);
                kandungan = getContext().getRequest().getParameter("syorPengarah"+i+ k);
                if(kandungan==null){
                    permohonanKertasKandungan8.setKandungan("Tiada");
                }else{
                    permohonanKertasKandungan8.setKandungan(kandungan);
                }

                permohonanKertasKandungan8.setCawangan(cawangan);
                permohonanKertasKandungan8.setSubtajuk("6."+ i+"."+str[k-1]);
                permohonanKertasKandungan8.setInfoAudit(infoAudit);
                permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan8);
            }
        }//if table count

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
            headingObj.setBil(8);
            kandungan = getContext().getRequest().getParameter("heading");
            headingObj.setKandungan(kandungan);
            cawangan = permohonan.getCawangan();
            headingObj.setCawangan(cawangan);
            headingObj.setInfoAudit(infoAudit);
            permohonanKertasKandunganDAO.saveOrUpdate(headingObj);
        }

        tx.commit();
        System.out.println("-----------------before rehydrate------");
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        String ptg = getContext().getRequest().getParameter("ptg");
        if(ptg.equals("true"))
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if(ptPengambilanPtg.equals("true"))
        getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/mmk_IzinLaluAktaBekalanLetrik.jsp").addParameter("tab", "true");


//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new RedirectResolution(UlasanMMKNSPendudukanSementaraActionBean.class, "locate");
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

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        String ptg = getContext().getRequest().getParameter("ptg");
        if(ptg.equals("true"))
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if(ptPengambilanPtg.equals("true"))
        getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/mmk_IzinLaluAktaBekalanLetrik.jsp").addParameter("tab", "true");
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
        String ptg = getContext().getRequest().getParameter("ptg");
        if(ptg.equals("true"))
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        String ptPengambilanPtg = getContext().getRequest().getParameter("ptPengambilanPtg");
        if(ptPengambilanPtg.equals("true"))
        getContext().getRequest().setAttribute("ptPengambilanPtg", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/mmk_IzinLaluAktaBekalanLetrik.jsp").addParameter("tab", "true");

    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

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

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan(){
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan){
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh(){
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh){
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh(){
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh){
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman(){
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman){
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan(){
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan){
        this.bilBangunan = bilBangunan;
    }


    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }


    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }


    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }


    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }


    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getPembangunanDicadang() {
        return pembangunanDicadang;
    }

    public void setPembangunanDicadang(String pembangunanDicadang) {
        this.pembangunanDicadang = pembangunanDicadang;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

     public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKmno() {
        return kmno;
    }

    public void setKmno(String kmno) {
        this.kmno = kmno;
    }

    public int getCount8() {
        return count8;
    }

    public void setCount8(int count8) {
        this.count8 = count8;
    }

    public List[] getSenaraiSyorPengarah() {
        return senaraiSyorPengarah;
    }

    public void setSenaraiSyorPengarah(List[] senaraiSyorPengarah) {
        this.senaraiSyorPengarah = senaraiSyorPengarah;
    }

    public int getCount6() {
        return count6;
    }

    public void setCount6(int count6) {
        this.count6 = count6;
    }

    public List[] getSenaraiSyorPentadbir() {
        return senaraiSyorPentadbir;
    }

    public void setSenaraiSyorPentadbir(List[] senaraiSyorPentadbir) {
        this.senaraiSyorPentadbir = senaraiSyorPentadbir;
    }

    public PermohonanKertasKandungan getHuraianPtgObj() {
        return huraianPtgObj;
    }

    public void setHuraianPtgObj(PermohonanKertasKandungan huraianPtgObj) {
        this.huraianPtgObj = huraianPtgObj;
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

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
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

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getSubtajuk() {
        return subtajuk;
    }

    public void setSubtajuk(String subtajuk) {
        this.subtajuk = subtajuk;
    }

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan8() {
        return senaraiKertasKandungan8;
    }

    public void setSenaraiKertasKandungan8(List<PermohonanKertasKandungan> senaraiKertasKandungan8) {
        this.senaraiKertasKandungan8 = senaraiKertasKandungan8;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public PermohonanKertasKandungan getTarikhMesyuaratObj() {
        return tarikhMesyuaratObj;
    }

    public void setTarikhMesyuaratObj(PermohonanKertasKandungan tarikhMesyuaratObj) {
        this.tarikhMesyuaratObj = tarikhMesyuaratObj;
    }

    public PermohonanKertasKandungan getTujuanObj() {
        return tujuanObj;
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

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public void setTujuanObj(PermohonanKertasKandungan tujuanObj) {
        this.tujuanObj = tujuanObj;
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

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan1() {
        return senaraiKertasKandungan1;
    }

    public void setSenaraiKertasKandungan1(List<PermohonanKertasKandungan> senaraiKertasKandungan1) {
        this.senaraiKertasKandungan1 = senaraiKertasKandungan1;
    }
}
