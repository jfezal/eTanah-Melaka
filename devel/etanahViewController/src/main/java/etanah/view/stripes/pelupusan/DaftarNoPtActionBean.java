/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.PembangunanService;
import etanah.model.Permohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodPemilikan;
import etanah.model.NoPt;
import etanah.model.SiriNoPt;
import etanah.model.Pengguna;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanUkur;
import etanah.service.BPelService;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author rohan
 */
@UrlBinding("/pelupusan/daftar_no_pt")
public class DaftarNoPtActionBean  extends AbleActionBean{
    private static final Logger LOG = Logger.getLogger(DaftarNoPtActionBean .class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;


    private Permohonan permohonan;
//    private Hakmilik hakmilik;
    private String bilLot;
    private PermohonanPengambilan permohonanPengambilan;
//    private List<PermohonanPlotPelan> senaraiPermohonanPlotPelan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private NoPt noPT;
    private String idPermohonan;
    private List<NoPt> noPTList;
    private String namaBPM;
    private PermohonanUkur permohonanUkur;
    private Pengguna pguna;
    private String stageId;
    private List senaraiKodBpm;
    private List senaraiNamaBpm;
    private List senaraiNoPT[] = new ArrayList [ 5 ];


    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/daftar_no_pt.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2(){
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pelupusan/daftar_no_pt.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3(){
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        return new JSP("pelupusan/daftar_no_pt.jsp").addParameter("tab", "true");
    }


    public Resolution simpan(){
        String luas;
        int kodBPM = Integer.parseInt(getContext().getRequest().getParameter("kodBPM"));
        int index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        noPTList = new ArrayList<NoPt>();
        noPTList = pelupusanService.getNoPTByIdPermohonan(idPermohonan,kodBPM);

        for(int i=0;i<noPTList.size();i++){
            noPT = new NoPt();
            noPT = noPTList.get(i);
            luas = getContext().getRequest().getParameter("luas"+index+i);
            if(luas == null){
                noPT.setLuasDilulus(BigDecimal.ZERO);
            }else{
                try{
		    DecimalFormat format = new DecimalFormat("#,##0.00");
                    double value =format.parse(luas).doubleValue();
                    noPT.setLuasDilulus(new BigDecimal(value));
                }catch(ParseException e){
                    e.printStackTrace();
                }
            }
           pelupusanService.simpanNoPt(noPT);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/daftar_no_pt.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna =  (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);

        noPTList = new ArrayList<NoPt>();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        

        senaraiKodBpm = new ArrayList();
        senaraiNamaBpm = new ArrayList();
        int k=0;
      if(!hakmilikPermohonanList.isEmpty()){
          for(int i=0;i<hakmilikPermohonanList.size();i++){
            HakmilikPermohonan hp = new HakmilikPermohonan();
            hp = hakmilikPermohonanList.get(i);
            Integer kodBPM = hp.getBandarPekanMukimBaru()!=null?hp.getBandarPekanMukimBaru().getKod():hp.getHakmilik().getBandarPekanMukim().getKod();
            namaBPM = hp.getBandarPekanMukimBaru()!=null?hp.getBandarPekanMukimBaru().getNama():hp.getHakmilik().getBandarPekanMukim().getNama();
            if(!senaraiKodBpm.contains(kodBPM)){
                senaraiNamaBpm.add(namaBPM);
                senaraiKodBpm.add(kodBPM);
                senaraiNoPT[k]= new ArrayList<NoPt>();
                senaraiNoPT[k] = pelupusanService.getNoPTByIdPermohonan(idPermohonan,kodBPM);
                 k++;
                LOG.info("---------kodBPM---------:"+kodBPM);
                LOG.info("---------namaBPM---------:"+namaBPM);
                LOG.info("---------senaraiNoPT---------:"+senaraiNoPT[k]);

          }
        }
      }
  }

    // Generate PT based on KodBPM
       // Generate PT based on KodBPM
    public Resolution generatePT(){

        NoPt maxNoPT = new NoPt();
        noPTList = new ArrayList<NoPt>();
//        senaraiPermohonanPlotPelan  = new ArrayList<PermohonanPlotPelan>();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        int kodBPM = Integer.parseInt(getContext().getRequest().getParameter("kodBPM"));
//        LOG.info("-------kodBPM-----:"+kodBPM);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());


//        senaraiPermohonanPlotPelan = pembangunanService.senaraiPermohonanPlotPelanByKodBPM(idPermohonan, kodBPM);
        if(hakmilikPermohonanList.isEmpty()){
           addSimpleError("Sila masukkan maklumat plot dahulu sebelum menjana No PT.");
           getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/daftar_no_pt.jsp").addParameter("tab", "true");
        }

            // Check for records already exists based on IdPermohonan and kodBPM
            noPTList = pelupusanService.getNoPTByIdPermohonan(idPermohonan,kodBPM);
            // If the list is Empty
                if(noPTList.isEmpty()){
                      long maxNoPtVal = 0L;
                      // Check for KodBpm exist in SiriNoPt
                      SiriNoPt siriNopt = pelupusanService.findSiriNoPtByKodBpm(kodBPM);
                      LOG.info("---------siriNopt-------------"+siriNopt);
                      if(siriNopt!=null){
                         maxNoPtVal =  siriNopt.getNoPt();
                         siriNopt.setNoPt(siriNopt.getNoPt()+1);
                      }else{
                          siriNopt=new SiriNoPt();
                          siriNopt.setCawangan(permohonan.getCawangan());
                          siriNopt.setKodBandarPekanMukim(kodBandarPekanMukimDAO.findById(kodBPM));
                          siriNopt.setInfoAudit(info);
                      }

                      LOG.info("-------maxNoPT---------"+maxNoPtVal);
                      maxNoPtVal = maxNoPtVal+1;
//                      for(HakmilikPermohonan hp:hakmilikPermohonanList)
                    for(int j=0;j<hakmilikPermohonanList.size();j++){
                          HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                            noPT = pelupusanService.findNoPTByIdMH(hp.getId());
                            if(noPT != null){
                               info =  noPT.getInfoAudit();
                               info.setTarikhKemaskini(new java.util.Date());
                               info.setDiKemaskiniOleh(peng);
                            }else{
                                noPT = new NoPt();
                            }
                            
                            noPT.setKodBpm(kodBandarPekanMukimDAO.findById(kodBPM));
//                            noPT.setLuasDilulus(hp.getLuasTerlibat());
                            LOG.info("maxNoPtVal : "+maxNoPtVal);
                            noPT.setNoPt(maxNoPtVal);
                            noPT.setInfoAudit(info);
                            noPT.setHakmilikPermohonan(hp);
                            pelupusanService.simpanNoPt(noPT);
                            maxNoPtVal = maxNoPtVal+1;
                    }// for j
                      siriNopt.setNoPt((int)(maxNoPtVal-1));
                      pelupusanService.simpanSiriNoPt(siriNopt);
                 }
        addSimpleMessage("No PT telah berjaya dijana.");
        return refreshpage();
    }


    public Resolution refreshpage() {
    rehydrate();
    return new RedirectResolution(DaftarNoPtActionBean.class, "locate");
    }



    public String stageId(String taskId) {
        BPelService service = new BPelService();
        stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getBilLot() {
        return bilLot;
    }

    public void setBilLot(String bilLot) {
        this.bilLot = bilLot;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public NoPt getNoPT() {
        return noPT;
    }

    public void setNoPT(NoPt noPT) {
        this.noPT = noPT;
    }

    public List<NoPt> getNoPTList() {
        return noPTList;
    }

    public void setNoPTList(List<NoPt> noPTList) {
        this.noPTList = noPTList;
    }

    public String getNamaBPM() {
        return namaBPM;
    }

    public void setNamaBPM(String namaBPM) {
        this.namaBPM = namaBPM;
    }

    public PermohonanUkur getPermohonanUkur() {
        return permohonanUkur;
    }

    public void setPermohonanUkur(PermohonanUkur permohonanUkur) {
        this.permohonanUkur = permohonanUkur;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List[] getSenaraiNoPT() {
        return senaraiNoPT;
    }

    public void setSenaraiNoPT(List[] senaraiNoPT) {
        this.senaraiNoPT = senaraiNoPT;
    }

    public List getSenaraiKodBpm() {
        return senaraiKodBpm;
    }

    public void setSenaraiKodBpm(List senaraiKodBpm) {
        this.senaraiKodBpm = senaraiKodBpm;
    }

    public List getSenaraiNamaBpm() {
        return senaraiNamaBpm;
    }

    public void setSenaraiNamaBpm(List senaraiNamaBpm) {
        this.senaraiNamaBpm = senaraiNamaBpm;
    }

}
