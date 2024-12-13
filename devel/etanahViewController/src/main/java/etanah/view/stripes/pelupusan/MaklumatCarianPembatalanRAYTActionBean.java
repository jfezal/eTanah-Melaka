package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/maklumat_carian_pembatalan_rayt")
public class MaklumatCarianPembatalanRAYTActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatCarianPembatalanRAYTActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pemohon> pemohonList;
    private String idPermohonanSebelum;
    private String idPermohonan;
    private Pengguna pengguna;
    private Pemohon pemohon;
    private Pihak pihak;
    boolean status = false;
    boolean simpan = false;
    boolean carisemula=false;
    private String stageId;
    private String tujuan;
    private Date tarikhPermohonan;
    private String sebabPembatalan;
    private String luasDrpTrizab;
//    private ActionBeanContext context;

    public String getLuasDrpTrizab() {
        return luasDrpTrizab;
    }

    public void setLuasDrpTrizab(String luasDrpTrizab) {
        this.luasDrpTrizab = luasDrpTrizab;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

     public boolean isCarisemula() {
        return carisemula;
    }

    public void setCarisemula(boolean carisemula) {
        this.carisemula = carisemula;
    }
    
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/maklumat_carian_pembatalan_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        status = true;
        return new JSP("pelupusan/maklumat_carian_pembatalan_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution refreshCari() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonan.setPermohonanSebelum(null);
        pelupusanService.simpanPermohonan(permohonan);
        idPermohonanSebelum = "";
        rehydrate();
        addSimpleError("Rekod permohonan sebelum telah dihapuskan.");
        return new JSP("pelupusan/maklumat_carian_pembatalan_rayt.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        if (permohonan != null) {
            if (permohonan.getPermohonanSebelum() != null) {
                permohonanSebelum = permohonan.getPermohonanSebelum();
                pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan());
                hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
                
//                stageId="001_Kemasukan";
                LOG.info("stageId : "+stageId);
                if(stageId.equals("001_Kemasukan")){
                    List<HakmilikPermohonan> hakmilikPermohonan= pelupusanService.getHakmilikPermohonan(idPermohonan);
                    if(hakmilikPermohonan==null){
                        getContext().getRequest().setAttribute("status", Boolean.TRUE);
                        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
                        getContext().getRequest().setAttribute("carisemula", Boolean.TRUE);
                    }else{
                        getContext().getRequest().setAttribute("status", Boolean.TRUE);
                        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                        getContext().getRequest().setAttribute("carisemula", Boolean.FALSE);
                    }
                    
                }else{
                    getContext().getRequest().setAttribute("status", Boolean.TRUE);
                    getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
                    getContext().getRequest().setAttribute("carisemula", Boolean.FALSE);
                }
               

                TanahRizabPermohonan mohonTrizab = pelupusanService.findTanahRizabByIdPermohonan(permohonanSebelum.getIdPermohonan());
                
                
                if (mohonTrizab != null) {
                    if (mohonTrizab.getRizab() != null) {
                        luasDrpTrizab = String.valueOf(mohonTrizab.getLuasTerlibat());
                    }
                }
            }
        }
        //idPermohonanSebelum = permohonanDAO.findById(idPermohonan);




        if (permohonan != null) {
         
            pemohonList = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        }
    }

    public Resolution searchId() {
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        // idPermohonanSebelum = a;
        LOG.info("idPermohonanSebelum :" + idPermohonanSebelum);

        permohonanSebelum = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonanSebelum);
        if (permohonanSebelum != null) {
//            
            pemohon = pelupusanService.findPemohonByIdPemohon(permohonanSebelum.getIdPermohonan());
            hakmilikPermohonanList = permohonanSebelum.getSenaraiHakmilik();
            if (pemohon != null) {
                if (pemohon.getPihak() != null) {
                    pihak = pemohon.getPihak();
                }
            } else {

                addSimpleMessage("Pemohon tidak dijumpai ");
            }
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
            status = false;
            addSimpleMessage("Maklumat dijumpai");

        } else {

            getContext().getRequest().setAttribute("status", Boolean.FALSE);
            addSimpleError("Maklumat tidak dijumpai");
        }

        return new JSP("pelupusan/maklumat_carian_pembatalan_rayt.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("Id Permohonan dalam simpan() method:" + idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        idPermohonanSebelum = getContext().getRequest().getParameter("idPermohonanSebelum");
        permohonanSebelum = permohonanDAO.findById(idPermohonanSebelum);
        LOG.info("Id Permohonan Sebelum dalam simpan() method:" + permohonanSebelum);
        
        permohonan.setPermohonanSebelum(permohonanSebelum);

        pelupusanService.simpanPermohonan(permohonan);


        addSimpleMessage("Maklumat Telah Disimpan");
        getContext().getRequest().setAttribute("status", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
        status = false;
        //simnpan dalam hakmilikPermohonan

        //get MohonHakmilik based on  idPermohonanSebelum 
        List<HakmilikPermohonan> hakmilikPermohonanTerdahulu = pelupusanService.getHakmilikPermohonan(idPermohonanSebelum);
        for(HakmilikPermohonan hp : hakmilikPermohonanTerdahulu){
            LOG.info("id hakmilik : "+hp.getPermohonan().getIdPermohonan());
            Hakmilik hm = hp.getHakmilik();
            Hakmilik hms = new Hakmilik();
            HakmilikPermohonan hakmilikSekarang = new HakmilikPermohonan();
            hakmilikSekarang = disLaporanTanahService.getLaporanTanahService().findByIdHakmilikIdPermohonan(idPermohonan,hms.getIdHakmilik() );
            if(hakmilikSekarang==null){               
                hakmilikSekarang = new HakmilikPermohonan();
                hakmilikSekarang.setInfoAudit(disLaporanTanahService.findAudit(hakmilikSekarang, "new", pguna));
                hakmilikSekarang.setPermohonan(permohonan);
                hakmilikSekarang.setHakmilik(hm);
                hakmilikSekarang.setLuasTerlibat(hp.getLuasTerlibat());
                hakmilikSekarang.setLuasDiluluskan(hp.getLuasDiluluskan());
                hakmilikSekarang.setLuasUkurHalus(hp.getLuasUkurHalus());
                hakmilikSekarang.setLuasPelanB1(hp.getLuasPelanB1());
                hakmilikSekarang.setKodUnitLuas(hp.getKodUnitLuas());
                hakmilikSekarang.setLuasUkurHalusUom(hp.getLuasUkurHalusUom());
                hakmilikSekarang.setLuasLulusUom(hp.getLuasLulusUom());
                hakmilikSekarang.setLuasPelanB1Uom(hp.getLuasPelanB1Uom());
                hakmilikSekarang.setKodSeksyen(hp.getKodSeksyen());
                hakmilikSekarang.setBandarPekanMukimBaru(hp.getBandarPekanMukimBaru());
                hakmilikSekarang.setKodHakmilik(hp.getKodHakmilik());
                hakmilikSekarang.setLot(hp.getLot());
                hakmilikSekarang.setNoLot(hp.getNoLot());
                hakmilikSekarang.setNoPajakan(hp.getNoPajakan());
                hakmilikSekarang.setKategoriTanahBaru(hp.getKategoriTanahBaru());
                hakmilikSekarang.setSyaratNyataBaru(hp.getSyaratNyataBaru());
                hakmilikSekarang.setSekatanKepentinganBaru(hp.getSekatanKepentinganBaru());
                hakmilikSekarang.setKadarCukaiBaru(hp.getKadarCukaiBaru());
                hakmilikSekarang.setCukaiBaru(hp.getCukaiBaru());
                hakmilikSekarang.setLokasi(hp.getLokasi());
                hakmilikSekarang.setJarak(hp.getJarak());
                hakmilikSekarang.setUnitJarak(hp.getUnitJarak());
                hakmilikSekarang.setJarakDari(hp.getJarakDari());
                hakmilikSekarang.setJarakDariNama(hp.getJarakDariNama());
                hakmilikSekarang.setNomborRujukan(hp.getNomborRujukan());
                hakmilikSekarang.setKodMilik(hp.getKodMilik());
                hakmilikSekarang.setTarikhAwalDaftarGeran(hp.getTarikhAwalDaftarGeran());
                hakmilikSekarang.setTempohPajakan(hp.getTempohPajakan());
                hakmilikSekarang.setKodHakmilikTetap(hp.getKodHakmilikTetap());
                hakmilikSekarang.setKodHakmilikSementara(hp.getKodHakmilikSementara());
                hakmilikSekarang.setTempohHakmilik(hp.getTempohHakmilik());
                hakmilikSekarang.setCukaiPerMeterPersegi(hp.getCukaiPerMeterPersegi());
                hakmilikSekarang.setCukaiPerLot(hp.getCukaiPerLot());
                hakmilikSekarang.setKadarPremium(hp.getKadarPremium());
                hakmilikSekarang.setDendaPremium(hp.getDendaPremium());
                hakmilikSekarang.setJenisPenggunaanTanah(hp.getJenisPenggunaanTanah());
                hakmilikSekarang.setSyaratNyata(hp.getSyaratNyata());
                hakmilikSekarang.setSekatanKepentingan(hp.getSekatanKepentingan());
                hakmilikSekarang.setNilaianJpph(hp.getNilaianJpph());
                hakmilikSekarang.setCatatan(hp.getCatatan());
                hakmilikSekarang.setHubunganHakmilik(hp.getHubunganHakmilik());
                hakmilikSekarang.setDokumen1(hp.getDokumen1());
                hakmilikSekarang.setDokumen2(hp.getDokumen2());
                hakmilikSekarang.setDokumen3(hp.getDokumen3());
                hakmilikSekarang.setDokumen4(hp.getDokumen4());
                hakmilikSekarang.setDokumen5(hp.getDokumen5());
                hakmilikSekarang.setDokumen6(hp.getDokumen6());
                hakmilikSekarang.setKosInfra(hp.getKosInfra());
                hakmilikSekarang.setTanahDiambil(hp.getTanahDiambil());
                hakmilikSekarang.setKeteranganInfra(hp.getKeteranganInfra());
                hakmilikSekarang.setKeteranganCukaiBaru(hp.getKeteranganCukaiBaru());
                hakmilikSekarang.setKeteranganKadarPremium(hp.getKeteranganKadarPremium());
                hakmilikSekarang.setKodKegunaanTanah(hp.getKodKegunaanTanah());
                hakmilikSekarang.setKeteranganKadarUkur(hp.getKeteranganKadarUkur());
                hakmilikSekarang.setKeteranganKadarDaftar(hp.getKeteranganKadarDaftar());
                hakmilikSekarang.setJarakDariKediaman(hp.getJarakDariKediaman());
                hakmilikSekarang.setJarakDariKediamanUom(hp.getJarakDariKediamanUom());
                hakmilikSekarang.setAgensiUpahUkur(hp.getAgensiUpahUkur());
                hakmilikSekarang.setStatusLuasDiluluskan(hp.getStatusLuasDiluluskan());
                hakmilikSekarang.setPenjenisan(hp.getPenjenisan());
                hakmilikSekarang.setNoUnitPetak(hp.getNoUnitPetak());
                hakmilikSekarang.setPegangan(hp.getPegangan());
                hakmilikSekarang.setKodDUN(hp.getKodDUN());
                hakmilikSekarang.setKodKawasanParlimen(hp.getKodKawasanParlimen());
                hakmilikSekarang.setTempohPengosongan(hp.getTempohPengosongan());
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikSekarang);
            }
           
                LOG.info("Maklumat berjaya disimpan.");
                
        }
        List<HakmilikPermohonan> hakmilikPermohonanSekarang = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanService.saveHakmilikPermohonan(hakmilikPermohonanSekarang);
        
        LOG.info("hakmilikPermohonanTerdahulu.size():" + hakmilikPermohonanTerdahulu.size());

        //simpan dlam mohon hakmilik pakai id sekarang
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTerdahulu.get(0));


        rehydrate();
        return new JSP("pelupusan/maklumat_carian_pembatalan_rayt.jsp").addParameter("tab", "true");
    }

    public String getSebabPembatalan() {
        return sebabPembatalan;
    }

    public void setSebabPembatalan(String sebabPembatalan) {
        this.sebabPembatalan = sebabPembatalan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
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

    public String getIdPermohonanSebelum() {
        return idPermohonanSebelum;
    }

    public void setIdPermohonanSebelum(String idPermohonanSebelum) {
        this.idPermohonanSebelum = idPermohonanSebelum;
    }

    public HakmilikDAO getHakmilikDAO() {
        return hakmilikDAO;
    }

    public void setHakmilikDAO(HakmilikDAO hakmilikDAO) {
        this.hakmilikDAO = hakmilikDAO;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Date getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(Date tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
