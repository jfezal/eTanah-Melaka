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
import etanah.dao.KodDokumenDAO;
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
import etanah.model.KodDokumen;
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
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.BPelService;
import etanah.service.common.HakmilikPermohonanService;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;


@UrlBinding("/pembangunan/melaka/ulasanDanKeputusan")
public class UlasanDanKeputusanPTDActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(UlasanDanKeputusanPTDActionBean.class);
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
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
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
    private KodDokumen kd;
    private String syorMemoPerakuan;
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private int rowCount;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private Hakmilik hakmilikSingle;
    private String nolot;
    private String nohakmilik;
    private String nama;
    private String lokasi;
    private String sbb="";
    private String pemilik="";
    private String penyerah="";

    private boolean btn = true;

    //testcode
    private HakmilikPermohonan hakmilikPermohonan;
    private String taskId;
    private Task task = null;
    private BPelService service;



    @DefaultHandler
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//         if(kertasK != null){
//            btn = false;
//        }
        return new JSP("pembangunan/melaka/ulasan_dan_keputusan_PTD.jsp").addParameter("tab", "true");

       // return new JSP("pembangunan/melaka/test_page.jsp").addParameter("tab", "true");
    }


    public Resolution showForm2() {
        btn = false;
        return new JSP("pembangunan/melaka/ulasan_dan_keputusan_PTD.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/ulasan_dan_keputusan_PTD.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        if(!(hakmilikPermohonanList.isEmpty())){
            hp = hakmilikPermohonanList.get(0);
            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            nolot = hakmilikSingle.getLot().getNama() + " " + hakmilikSingle.getNoLot();
            nohakmilik = hakmilikSingle.getKodHakmilik().getKod() + " " + hakmilikSingle.getNoHakmilik();
        }

        if(permohonan.getSebab()!=null){
            sbb = permohonan.getSebab();
        }
        if(permohonan.getPenyerahNama()!=null){
            penyerah = permohonan.getPenyerahNama();
        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
            List<HakmilikPihakBerkepentingan> hpList=new ArrayList<HakmilikPihakBerkepentingan>();
            hpList =hakmilik.getSenaraiPihakBerkepentingan();
             if(!hpList.isEmpty()){
                 for(int k=0;k<hpList.size();k++){
                     HakmilikPihakBerkepentingan hp1=new HakmilikPihakBerkepentingan();
                     hp1 = hpList.get(k);
                     if(hp1.getJenis()!=null){
                        pemilik = pemilik + hp1.getJenis().getNama()+",";
                     }
                 }
             }


            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", "
                        + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()+" Untuk Tujuan "+sbb+" Oleh "+pemilik+" Melalui "+penyerah;
//                nolot = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot();
//                nohakmilik = hakmilik.getKodHakmilik().getKod()+" " + hakmilik.getNoHakmilik();
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", "
                        + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
//                    nolot = nolot + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", ";
//                    nohakmilik = nohakmilik + ", " + hakmilik.getKodHakmilik().getKod() +" " + hakmilik.getNoHakmilik() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getKodHakmilik().getKod() + hakmilik.getNoHakmilik() + ", seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", "
                        + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()+" Untuk Tujuan "+sbb+" Oleh "+pemilik+" Melalui "+penyerah;
//                    nolot = nolot + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot();
//                    nohakmilik = nohakmilik + " dan " + hakmilik.getKodHakmilik().getKod() +" " + hakmilik.getNoHakmilik();
                }
            }
        }


        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        PermohonanKertas kertasP = new PermohonanKertas();
        kertasP = devService.findKertasByKod(idPermohonan, "UMINB");
        if(kertasP != null){
            senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
            senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(kertasP.getIdKertas());
            if(senaraiKandungan!=null){
                kertasK = senaraiKandungan.get(0);
            }
        }else{
            senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
            PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
            kertasK1.setKandungan("");
            senaraiKandungan.add(kertasK1);
        }
        rowCount = senaraiKandungan.size();

    }

   public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("UMINB");

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        if (kertasK != null) {
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas.setTajuk("ULASAN DAN KEPUTUSAN PTD");
            kertasK.setInfoAudit(infoAudit);
            devService.simpanPermohonanKertas(permohonanKertas);
        } else {
            kertasK = new PermohonanKertasKandungan();
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTajuk("ULASAN DAN KEPUTUSAN PTD");
            permohonanKertas.setKodDokumen(kd);
            devService.simpanPermohonanKertas(permohonanKertas);
        }

        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(permohonanKertas.getIdKertas());
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            kertasK=new PermohonanKertasKandungan();
            if (senaraiKandungan!=null && senaraiKandungan.size() != 0 && i <=senaraiKandungan.size()) {
                Long id = senaraiKandungan.get(i - 1).getIdKandungan();
                if (id != null) {
                    kertasK = permohonanKertasKandunganDAO.findById(id);
                    iaP = kertasK.getInfoAudit();
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {
                kertasK = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new java.util.Date());
                iaP.setDimasukOleh(pengguna);
            }
            kertasK.setKertas(permohonanKertas);
            kertasK.setBil(i);
            String kandungan = getContext().getRequest().getParameter("penyediaan" + i);
            if(kandungan == null || kandungan.equals("")){
                kandungan = "TIADA DATA.";
            }
            kertasK.setKandungan(kandungan);
            kertasK.setCawangan(permohonan.getCawangan());
            kertasK.setInfoAudit(iaP);
            devService.simpanPermohonanKertasKandungan(kertasK);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/penyediaan_Minit_Bebas.jsp").addParameter("tab", "true");
        return new RedirectResolution(UlasanDanKeputusanPTDActionBean.class, "showForm");
    }


   public Resolution deleteSingle() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
        } catch (Exception e) {
            LOG.debug("Hapus empty record");
            e.printStackTrace();
        }
        if (permohonanKertasKandungan1 != null) {
            devService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("Record deleted Successfully...");
        }
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        rehydrate();

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/ulasan_dan_keputusan_PTD.jsp").addParameter("tab", "true");
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

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<PermohonanKertasKandungan> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getSyorMemoPerakuan() {
        return syorMemoPerakuan;
    }

    public void setSyorMemoPerakuan(String syorMemoPerakuan) {
        this.syorMemoPerakuan = syorMemoPerakuan;
    }

}

