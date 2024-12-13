/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.FasaPermohonan;
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
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.common.FasaPermohonanService;
import etanah.service.dev.integrationflow.SBMSIntegrationFlowService;
import etanah.view.stripes.pembangunan.tugasan.TugasanActionBean;
import org.apache.log4j.Logger;


@UrlBinding("/pembangunan/melaka/keputusan_mesyuarat_JKBB")
public class KeputusanMesyuaratJKBBActionBean extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(KeputusanMesyuaratJKBBActionBean.class);
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
    SBMSIntegrationFlowService sBMSIntegrationFlowService;
    @Inject
    private FasaPermohonanService mohonFasaService;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
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
    private List<PermohonanKertasKandungan> senaraiKandungan;
    private int rowCount;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn = true;
    private HakmilikPermohonan hakmilikPermohonan;
    private String tugasanUtil = Boolean.FALSE.toString();

    @DefaultHandler
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/keputusan_mesyuarat_JKBB.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        return new JSP("pembangunan/melaka/keputusan_mesyuarat_JKBB.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/keputusan_mesyuarat_JKBB.jsp").addParameter("tab", "true");
    }

    public Resolution selesaiTugasan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                String stageId = (String) getContext().getRequest().getSession().getAttribute("stageId");

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        boolean statusSelesai = false;
        List<FasaPermohonan> listMohonFasa = mohonFasaService.findByIdMohonAndStageId(idPermohonan, stageId);
        if (!listMohonFasa.isEmpty()) {
            statusSelesai = !listMohonFasa.get(0).getKeputusan().getKod().equals("TG");
            if (statusSelesai) {
                if (sBMSIntegrationFlowService.initiateSrtKeputusan(permohonan)) {
                    permohonan.setKeputusan(listMohonFasa.get(0).getKeputusan());
                    devService.simpanPermohonan(permohonan);
                    sBMSIntegrationFlowService.deleteTugasanTable(permohonan);
//            sBMSIntegrationFlowService.insertTugasanTable(permohonanDAO.findById("0401DEV2017000171"), "JKBB", peng.getIdPengguna());
                }
            }
        }

        return new RedirectResolution(TugasanActionBean.class);
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------------rehydrate-------------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        boolean flag = true;
        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                 if (kertasP.getKodDokumen().getKod().equals("KMJBB")) {
                    senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(kertasP.getIdKertas());
                    rowCount = senaraiKandungan.size();
                    flag = false;                    
                 }
            }
        }

        if((permohonan.getSenaraiKertas().isEmpty()) || flag){
            senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
            PermohonanKertasKandungan kertasK2 = new PermohonanKertasKandungan();
            // Get Keputusan Jkbb data from Ringkasan Permohonan
            List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
             senaraiKertas = devService.findSenaraiKertasByKod(idPermohonan, "RKSP");

             if(senaraiKertas.size()>0){
                PermohonanKertas kertasP1 = new PermohonanKertas();
                 kertasP1 = senaraiKertas.get(0);                 
                 if (kertasP1 != null) {
                     for (int j = 0; j < kertasP1.getSenaraiKandungan().size(); j++) {                         
                        PermohonanKertasKandungan kertasK1 = new PermohonanKertasKandungan();
                        kertasK1 = kertasP1.getSenaraiKandungan().get(j);
                        if (kertasK1.getBil() == 3) {
                            kertasK2.setKandungan(kertasK1.getKandungan());
                            senaraiKandungan.add(kertasK2);
                         }
                     }
                 }
             }else{
                kertasK2.setKandungan("");
                senaraiKandungan.add(kertasK2);
             }
         }//if
         rowCount = senaraiKandungan.size();
         if (sBMSIntegrationFlowService.findTugasan(permohonan)) {
                tugasanUtil = Boolean.TRUE.toString();
            }
  }

   public Resolution simpan() {

       LOG.info("------------simpan-------------");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("KMJBB");

        PermohonanKertas permohonanKertas = null;
        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();

        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                 if (kertasP.getKodDokumen().getKod().equals("KMJBB")) {
                    permohonanKertas = new PermohonanKertas();
                    permohonanKertas = permohonan.getSenaraiKertas().get(i);
                 }
            }
        }

        if (permohonanKertas != null) {                
                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonanKertasDAO.findById(permohonanKertas.getIdKertas());
                senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(permohonanKertas.getIdKertas());
                infoAudit = permohonanKertas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                kertasP.setInfoAudit(infoAudit);                
                devService.simpanPermohonanKertas(kertasP);
            } else {                
                permohonanKertas = new PermohonanKertas();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KEPUTUSAN MESYUARAT JKBB");
                permohonanKertas.setKodDokumen(kd);
                devService.simpanPermohonanKertas(permohonanKertas);
            }

        
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            kertasK=new PermohonanKertasKandungan();
            if (senaraiKandungan.size() != 0 && i <= senaraiKandungan.size()) {
                Long id = senaraiKandungan.get(i - 1).getIdKandungan();
                if (id != null) {
                    kertasK = permohonanKertasKandunganDAO.findById(id);
                    iaP = kertasK.getInfoAudit();
                    iaP.setTarikhKemaskini(new java.util.Date());
                    iaP.setDiKemaskiniOleh(pengguna);
                }
            } else {                
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
        senaraiKandungan = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan = devService.findSenaraiKertasKandunganByIdKertas(permohonanKertas.getIdKertas());
        rowCount = senaraiKandungan.size();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/keputusan_mesyuarat_JKBB.jsp").addParameter("tab", "true");
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
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/keputusan_mesyuarat_JKBB.jsp").addParameter("tab", "true");
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

    public String getTugasanUtil() {
        return tugasanUtil;
    }

    public void setTugasanUtil(String tugasanUtil) {
        this.tugasanUtil = tugasanUtil;
    }



    
}

