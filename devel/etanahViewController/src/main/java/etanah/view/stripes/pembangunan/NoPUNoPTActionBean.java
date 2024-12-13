/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

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
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/noPT_noPU")
public class NoPUNoPTActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NoPUNoPTActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PembangunanService pembangunanService;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private String bilLot;
    private PermohonanPengambilan permohonanPengambilan;
    private List<PermohonanPlotPelan> senaraiPermohonanPlotPelan;
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
    private List senaraiNoPT[] = new ArrayList[5];
    private Boolean displayJana = false;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        String luas, noPlot, noPt;
        int kodBPM = Integer.parseInt(getContext().getRequest().getParameter("kodBPM"));
        int index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        noPTList = new ArrayList<NoPt>();
        noPTList = pembangunanService.getNoPTByIdPermohonan(idPermohonan, kodBPM);

        for (int i = 0; i < noPTList.size(); i++) {
            noPT = new NoPt();
            noPT = noPTList.get(i);
            luas = getContext().getRequest().getParameter("luas" + index + i);
            noPlot = getContext().getRequest().getParameter("noPlot" + index + i);
            noPt = getContext().getRequest().getParameter("noPt" + index + i);
            if (luas == null) {
                noPT.setLuasDilulus(BigDecimal.ZERO);
                LOG.info("A");
            } else {
                try {
                    DecimalFormat format = new DecimalFormat("#,##0.00");
                    double value = format.parse(luas).doubleValue();
                    noPT.setLuasDilulus(new BigDecimal(value));
                    noPT.setNoPlot(noPlot);

                    long NOPT = Long.parseLong(noPt);
                    noPT.setNoPt(NOPT);

                    LOG.info("B");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            pembangunanService.simpanNoPt(noPT);
        }
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);

        noPTList = new ArrayList<NoPt>();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

        senaraiKodBpm = new ArrayList();
        senaraiNamaBpm = new ArrayList();
        int k = 0;
        if (!hakmilikPermohonanList.isEmpty()) {
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                HakmilikPermohonan hp = new HakmilikPermohonan();
                hp = hakmilikPermohonanList.get(i);
                Integer kodBPM = hp.getHakmilik().getBandarPekanMukim().getKod();
                namaBPM = hp.getHakmilik().getBandarPekanMukim().getNama();
                if (!senaraiKodBpm.contains(kodBPM)) {
                    senaraiNamaBpm.add(namaBPM);
                    senaraiKodBpm.add(kodBPM);
                    senaraiNoPT[i] = new ArrayList<NoPt>();
                    senaraiNoPT[i] = pembangunanService.getJanaNoPTByIdPermohonanKodBpm(idPermohonan, kodBPM);
                    //k++;
                    LOG.info("TEST");
                }
            }

        }
    }

    // Generate PT based on KodBPM
    public Resolution generatePT() {

        NoPt maxNoPT = new NoPt();
        noPTList = new ArrayList<NoPt>();
        senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
        hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        int kodBPM = Integer.parseInt(getContext().getRequest().getParameter("kodBPM"));
        LOG.info("-------kodBPM-----:" + kodBPM);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());

//        senaraiPermohonanPlotPelan = pembangunanService.senaraiPermohonanPlotPelanByKodBPM(idPermohonan);
        if (!permohonan.getKodUrusan().getKod().equals("PYTN")) {
            senaraiPermohonanPlotPelan = pembangunanService.senaraiPermohonanPlotPelanByKodBPM(idPermohonan, kodBPM);
        } else {
            //this is because PYTN will only have 1 cantum hakmilik/plot with same kodBpm
            senaraiPermohonanPlotPelan = pembangunanService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
        }

        if (senaraiPermohonanPlotPelan.isEmpty()) {
            addSimpleError("Sila masukkan maklumat plot dahulu sebelum menjana No PT.");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
        }

        // Check for records already exists based on IdPermohonan and kodBPM
        noPTList = pembangunanService.getNoPTByIdPermohonan(idPermohonan, kodBPM);

        // If the list is Empty
        if (noPTList.isEmpty()) {
            LOG.info("---------list is Empty-------------");
            long maxNoPtVal = 0L;
            // Check for KodBpm exist in SiriNoPt
            SiriNoPt siriNopt = pembangunanService.findSiriNoPtByKodBpm(kodBPM);
            LOG.info("---------siriNopt-------------" + siriNopt);
            if (siriNopt != null) {
                maxNoPtVal = siriNopt.getNoPt();
                siriNopt.setNoPt(siriNopt.getNoPt() + 1);
            } else {
                siriNopt = new SiriNoPt();
                siriNopt.setCawangan(permohonan.getCawangan());
                siriNopt.setKodBandarPekanMukim(kodBandarPekanMukimDAO.findById(kodBPM));
                siriNopt.setInfoAudit(info);
            }
            LOG.info("-------maxNoPT---------" + maxNoPtVal);
            maxNoPtVal = maxNoPtVal + 1;
            for (int j = 0; j < senaraiPermohonanPlotPelan.size(); j++) {
                PermohonanPlotPelan plotPelan = senaraiPermohonanPlotPelan.get(j);
                for (int k = 0; k < plotPelan.getBilanganPlot(); k++) {
                    noPT = pembangunanService.findNoPTByIdPlotPelanAndKodBpm(plotPelan.getIdPlot(), kodBPM);
                    if (noPT != null) {
                        info = noPT.getInfoAudit();
                        info.setTarikhKemaskini(new java.util.Date());
                        info.setDiKemaskiniOleh(peng);
                    } else {
                        noPT = new NoPt();
                        noPT.setPermohonanPlotPelan(senaraiPermohonanPlotPelan.get(j));
                        noPT.setLuasDilulus(plotPelan.getLuas());
                    }
                    noPT.setInfoAudit(info);
                    noPT.setKodBpm(kodBandarPekanMukimDAO.findById(kodBPM));
                    noPT.setNoPt(maxNoPtVal);
                    pembangunanService.simpanNoPt(noPT);
                    maxNoPtVal = maxNoPtVal + 1;
                }// for k
            }// for j
            siriNopt.setNoPt((int) (maxNoPtVal - 1));
            pembangunanService.simpanSiriNoPt(siriNopt);
        } else {
            // If the list is not Empty
            LOG.info("---------list is not Empty-------------");
            long maxNoPtVal = 0L;
            // Check for KodBpm exist in SiriNoPt
            SiriNoPt siriNopt = pembangunanService.findSiriNoPtByKodBpm(kodBPM);
            LOG.info("---------siriNopt-------------" + siriNopt);
            if (siriNopt != null) {
                maxNoPtVal = siriNopt.getNoPt();
                siriNopt.setNoPt(siriNopt.getNoPt() + 1);
            } else {
                siriNopt = new SiriNoPt();
                siriNopt.setCawangan(permohonan.getCawangan());
                siriNopt.setKodBandarPekanMukim(kodBandarPekanMukimDAO.findById(kodBPM));
                siriNopt.setInfoAudit(info);
            }
            LOG.info("-------maxNoPT---------" + maxNoPtVal);
            maxNoPtVal = maxNoPtVal + 1;
            for (int j = 0; j < senaraiPermohonanPlotPelan.size(); j++) {
                PermohonanPlotPelan plotPelan = senaraiPermohonanPlotPelan.get(j);
                List<NoPt> noPtList = pembangunanService.findNoPTListByIdPlotPelanAndKodBpm(plotPelan.getIdPlot(), kodBPM);
                if (!noPtList.isEmpty()) {
                    for (int p = 0; p < noPtList.size(); p++) {
                        noPT = noPtList.get(p);
                        if (noPT != null) {
                            info = noPT.getInfoAudit();
                            info.setTarikhKemaskini(new java.util.Date());
                            info.setDiKemaskiniOleh(peng);
                        } else {
                            noPT = new NoPt();
                            noPT.setPermohonanPlotPelan(senaraiPermohonanPlotPelan.get(j));
                            noPT.setLuasDilulus(plotPelan.getLuas());
                        }
                        noPT.setInfoAudit(info);
                        noPT.setKodBpm(kodBandarPekanMukimDAO.findById(kodBPM));
                        noPT.setNoPt(maxNoPtVal);
                        pembangunanService.simpanNoPt(noPT);
                        maxNoPtVal = maxNoPtVal + 1;
                    }//for
                }//if                   
            }// for j
            siriNopt.setNoPt((int) (maxNoPtVal - 1));
            pembangunanService.simpanSiriNoPt(siriNopt);
        }


        addSimpleMessage("No PT telah berjaya dijana.");
        return refreshpage();
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(NoPUNoPTActionBean.class, "locate");
    }

    // Old code
//    public Resolution janaPT(){
//
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//
//        int bilLotNo = permohonanPengambilan.getBilanganLot();
//        NumberFormat df = new DecimalFormat("0000");
//
//        senaraiPermohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();
//
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        System.out.println("----------------idPermohonan-----------------"+idPermohonan);
//
//        senaraiPermohonanPlotPelan = pembangunanService.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);
//        System.out.println("----------------senaraiPermohonanPlotPelan-----------------"+senaraiPermohonanPlotPelan);
//        if(senaraiPermohonanPlotPelan.isEmpty()){
//            if(bilLotNo > 0){
//                PermohonanPlotPelan permohonanPlotPelan2 = pembangunanService.getMaxNoPTofPermohonanPlotPelan();
//                System.out.println("----------------permohonanPlotPelan1-----------------"+permohonanPlotPelan2);
//
////                System.out.println("----------------permohonanPlotPelan1-----------------"+maxPTNo);
//
//                if(permohonanPlotPelan2 == null){
//                      for(int i=1;i<=bilLotNo;i++){
//                          PermohonanPlotPelan permohonanPlotPelan1 = new PermohonanPlotPelan();
//                          permohonanPlotPelan1.setPermohonan(permohonan);
//                          permohonanPlotPelan1.setCawangan(permohonan.getCawangan());
//                          permohonanPlotPelan1.setNoDari(1);
//                          permohonanPlotPelan1.setNoKe(1);
//                          String noPT="PT"+df.format(i);
//                          System.out.println("----------------Seq-----------------"+noPT);
//                          permohonanPlotPelan1.setNoPt(noPT) ;
//                          KodPemilikan KodPemilikan = kodPemilikanDAO.findById('H');
//                          permohonanPlotPelan1.setPemilikan(KodPemilikan);
//
//                          InfoAudit info = new InfoAudit();
//                          info.setDimasukOleh(peng);
//                          info.setTarikhMasuk(new java.util.Date());
//                          permohonanPlotPelan1.setInfoAudit(info);
//
//                          pembangunanService.simpanPermohonanPlotPelan(permohonanPlotPelan1);
//                      }
//                }else{
//                     System.out.println("----------------permohonanPlotPelan.getNoPt()-----------------"+permohonanPlotPelan2);
//                   if(permohonanPlotPelan2 != null){
//                       String noPT = permohonanPlotPelan2.getNoPt();
//                       String subNoPT1 = noPT.substring(2);
//                       String subNoPT2 = noPT.substring(subNoPT1.lastIndexOf("0"));
//                       int val1 = Integer.parseInt(subNoPT2);
//                       val1 = val1+1;
//                       for(int i=1;i<=bilLotNo;i++){
//                          PermohonanPlotPelan permohonanPlotPelan1 = new PermohonanPlotPelan();
//                          permohonanPlotPelan1.setPermohonan(permohonan);
//                          permohonanPlotPelan1.setCawangan(permohonan.getCawangan());
//                          permohonanPlotPelan1.setNoDari(1);
//                          permohonanPlotPelan1.setNoKe(1);
//                          String noPT1="PT"+df.format(val1);
//                          System.out.println("----------------Seq-----------------"+noPT1);
//                          permohonanPlotPelan1.setNoPt(noPT1) ;
//                           KodPemilikan KodPemilikan = kodPemilikanDAO.findById('H');
//                          permohonanPlotPelan1.setPemilikan(KodPemilikan);
//                          InfoAudit info = new InfoAudit();
//                          info.setDimasukOleh(peng);
//                          info.setTarikhMasuk(new java.util.Date());
//                          permohonanPlotPelan1.setInfoAudit(info);
//                          pembangunanService.simpanPermohonanPlotPelan(permohonanPlotPelan1);
//                          val1 = val1+1;
//                      }
//                   }
//                }
//            }else{
//                addSimpleMessage("Tiada Bilangan Lot.");
//            }
//            senaraiPermohonanPlotPelan = pembangunanService.senaraiPermohonanPlotPelanByIdPermohonan(idPermohonan);
//        }else{
//            addSimpleMessage("No. dijana.");
//        }
//
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        return new JSP("pembangunan/pecahSempadan/dev_sedia_PT_PU.jsp").addParameter("tab", "true");
//    }
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

    public List<PermohonanPlotPelan> getSenaraiPermohonanPlotPelan() {
        return senaraiPermohonanPlotPelan;
    }

    public void setSenaraiPermohonanPlotPelan(List<PermohonanPlotPelan> senaraiPermohonanPlotPelan) {
        this.senaraiPermohonanPlotPelan = senaraiPermohonanPlotPelan;
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

    public void setDisplayJana(Boolean displayJana) {
        this.displayJana = displayJana;
    }

    public Boolean getDisplayJana() {
        return displayJana;
    }
}
