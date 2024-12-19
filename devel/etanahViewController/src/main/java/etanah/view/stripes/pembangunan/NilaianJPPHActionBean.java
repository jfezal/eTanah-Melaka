package etanah.view.stripes.pembangunan;
/**
 *
 * @author NageswaraRao
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
//import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
//import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.daftar.PembetulanService;



import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.EnforceService;
import etanah.service.common.HakmilikPermohonanService;
import java.math.BigDecimal;
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pembangunan/melaka/nilaianJPPH")
public class NilaianJPPHActionBean extends AbleActionBean{
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    EnforceService enforceService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    private PembetulanService pembetulanService;

    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private String kelulusan;
    private String penyediaan;
    private String pentadbir;
    private String penyediaan2;
    private String penyediaan3;
    private String penyediaan4;
    private String penyediaan5;
    private String pentadbir2;
    private String pentadbir3;
    private String pentadbir4;
    private String pentadbir5;

    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    private boolean btn = true;

    //testcode
    private HakmilikPermohonan hakmilikPermohonan;



    @DefaultHandler
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pembangunan/melaka/nilaian_jpph.jsp").addParameter("tab", "true");

       // return new JSP("pembangunan/melaka/test_page.jsp").addParameter("tab", "true");
    }


//    public void hakmilikDetails() {
//         String idPermohonan ="0505ACQ2010007043";
//         String idHakmilik ="050540HSD00008737";
//        System.out.println("idPermohonan:-----------"+idPermohonan);
//        System.out.println("idHakmilik:-----------"+idHakmilik);
//
//        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
//        System.out.println("hpPermohonan:-------"+hakmilikPermohonan);
//
//        System.out.println("hakmilikPermohonan.getId()---------------"+hakmilikPermohonan.getId());
//
//
//
//         hakmilikPerbicaraan = hakmilikPermohonanService.findByIdHakmilikPermohonan(hakmilikPermohonan.getId());
//
//        System.out.println("--------- hakmilikPerbicaraan ----------"+hakmilikPerbicaraan);
//
//        if(hakmilikPerbicaraan != null){
//            System.out.println("--------- hakmilikPerbicaraan ----------"+hakmilikPerbicaraan.getIdPerbicaraan());
//            System.out.println("--------- hakmilikPerbicaraan ----------"+hakmilikPerbicaraan.getTarikhBicara());
//        }
//        //return new ForwardResolution("/WEB-INF/jsp/pengambilan/penerimaan_borang.jsp").addParameter("tab", "true");
//        return new JSP("pengambilan/penerimaan_borang.jsp").addParameter("tab", "true");
//    }


    public Resolution showForm2() {
        btn = false;
         return new JSP("pembangunan/melaka/nilaian_jpph.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         return new JSP("pembangunan/melaka/nilaian_jpph.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

//      //testing
//        Hakmilik hakmilik = new Hakmilik();
//        String idHakmilik ="";
//        for(int i=0;i<hakmilikPermohonanList.size();i++){
//            hakmilik = hakmilikPermohonanList.get(i).getHakmilik();
//            if(idHakmilik.equals(hakmilik.getIdHakmilik()))
//
//        }

//        hp = permohonan.getSenaraiHakmilik().get(0);
//        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        //wani
        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        

    }

   public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

//            PermohonanKertas permohonanKertas = new PermohonanKertas();
//
//            if (kertasK != null) {
//                permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
//                infoAudit = permohonanKertas.getInfoAudit();
//                infoAudit.setDiKemaskiniOleh(pengguna);
//                infoAudit.setTarikhKemaskini(new java.util.Date());
//
//            } else {
//                infoAudit.setDimasukOleh(pengguna);
//                infoAudit.setTarikhMasuk(new java.util.Date());
//
//            }

        permohonan = permohonanDAO.findById(idPermohonan);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        if(!hakmilikPermohonanList.isEmpty()){
            for(int i=0;i<hakmilikPermohonanList.size();i++){
                HakmilikPermohonan hp1 = new HakmilikPermohonan();
                hp1=hakmilikPermohonanList.get(i);
                String nilaianJpph = (String) getContext().getRequest().getParameter("nilaianJpph"+i);
                System.out.println("----------nilaianJpph--------"+nilaianJpph);
                if(nilaianJpph != null){
                    infoAudit = hp1.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    hp1.setInfoAudit(infoAudit);
                    System.out.println("----------save--------"+nilaianJpph);
                    hp1.setNilaianJpph(new BigDecimal(nilaianJpph));
                    enforceService.simpanhakmilikPermohonan(hp1);
                }
            }
        }

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
 
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/nilaian_jpph.jsp").addParameter("tab", "true");
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

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getKelulusan() {
        return kelulusan;
    }

    public void setKelulusan(String kelulusan) {
        this.kelulusan = kelulusan;
    }

    public String getPentadbir() {
        return pentadbir;
    }

    public void setPentadbir(String pentadbir) {
        this.pentadbir = pentadbir;
    }

    public String getPentadbir2() {
        return pentadbir2;
    }

    public void setPentadbir2(String pentadbir2) {
        this.pentadbir2 = pentadbir2;
    }

    public String getPentadbir3() {
        return pentadbir3;
    }

    public void setPentadbir3(String pentadbir3) {
        this.pentadbir3 = pentadbir3;
    }

    public String getPentadbir4() {
        return pentadbir4;
    }

    public void setPentadbir4(String pentadbir4) {
        this.pentadbir4 = pentadbir4;
    }

    public String getPentadbir5() {
        return pentadbir5;
    }

    public void setPentadbir5(String pentadbir5) {
        this.pentadbir5 = pentadbir5;
    }

    public String getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(String penyediaan) {
        this.penyediaan = penyediaan;
    }

    public String getPenyediaan2() {
        return penyediaan2;
    }

    public void setPenyediaan2(String penyediaan2) {
        this.penyediaan2 = penyediaan2;
    }

    public String getPenyediaan3() {
        return penyediaan3;
    }

    public void setPenyediaan3(String penyediaan3) {
        this.penyediaan3 = penyediaan3;
    }

    public String getPenyediaan4() {
        return penyediaan4;
    }

    public void setPenyediaan4(String penyediaan4) {
        this.penyediaan4 = penyediaan4;
    }

    public String getPenyediaan5() {
        return penyediaan5;
    }

    public void setPenyediaan5(String penyediaan5) {
        this.penyediaan5 = penyediaan5;
    }


    // newly added code

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }





}


