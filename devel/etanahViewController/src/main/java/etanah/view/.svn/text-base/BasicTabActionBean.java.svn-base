/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.JabatanConstants;
import etanah.manager.TabBean;
import etanah.manager.TabManager;
import etanah.model.Pengguna;
import etanah.service.SemakDokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.view.dokumen.FolderAction;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author md.nurfikri
 */
public class BasicTabActionBean extends AbleActionBean {

    /*
     * Tab purposes
     */
    @Inject
    TabManager tabManager;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
    boolean mainTab = false;
    boolean secondTab = false;
    public String txnCode = "";
    public TabBean tabBean;
    public String pageId = "1";
    public String classId = "1";
    public Integer mainId = 1;
    public String stageId = "";
    public String idPermohonan = "";
    public String taskUtil="";
    public String selectedTab = "0";
    public String selectedMainTab = "0";
    public String idWorkFlow = "";
    public boolean report = Boolean.FALSE; //to make Jana Dokumen button to appear on screen
    public boolean documentGenerated = Boolean.TRUE; //to make Hantar Button enabled if document already generate
    public boolean isPushBack = Boolean.FALSE;
    public boolean keputusan = Boolean.FALSE;

    private static final Logger LOGGER = Logger.getLogger(BasicTabActionBean.class);
    private static boolean isDebug = LOGGER.isDebugEnabled();

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTaskUtil() {
        return taskUtil;
    }

    public void setTaskUtil(String taskUtil) {
        this.taskUtil = taskUtil;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public boolean isMainTab() {
        return mainTab;
    }

    public void setMainTab(boolean mainTab) {
        this.mainTab = mainTab;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public boolean isSecondTab() {
        return secondTab;
    }

    public void setSecondTab(boolean secondTab) {
        this.secondTab = secondTab;
    }

    public String getSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(String selectedTab) {
        this.selectedTab = selectedTab;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTxnCode() {
        return txnCode;
    }

    public void setTxnCode(String txnCode) {
        this.txnCode = txnCode;
    }

    public TabBean getTabBean() {
        return tabBean;
    }

    public void setTabBean(TabBean tabBean) {
        this.tabBean = tabBean;
    }

    public String getSelectedMainTab() {
        return selectedMainTab;
    }

    public void setSelectedMainTab(String selectedMainTab) {
        this.selectedMainTab = selectedMainTab;
    }

    public String getIdWorkFlow() {
        return idWorkFlow;
    }

    public void setIdWorkFlow(String idWorkFlow) {
        this.idWorkFlow = idWorkFlow;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean isDocumentGenerated() {
        return documentGenerated;
    }

    public void setDocumentGenerated(boolean documentGenerated) {
        this.documentGenerated = documentGenerated;
    }

    public boolean isIsPushBack() {
        return isPushBack;
//        return tabManager.isPushBack(idWorkFlow, stageId);
    }

    public void setIsPushBack(boolean isPushBack) {
        this.isPushBack = isPushBack;
    }

    public void checkPushBack() {
        isPushBack = tabManager.isPushBack(idWorkFlow, stageId);
    }

    public boolean isKeputusan() {
        return keputusan;
    }

    public void setKeputusan(boolean keputusan) {
        this.keputusan = keputusan;
    }
  
    public boolean checkReport(String id, String jabatan, String keputusan) {

        //no need to check dokumen if keputusan is tolak @ gantung
        if (StringUtils.isNotBlank(keputusan) ) {
            if (jabatan.equalsIgnoreCase(JabatanConstants.PENDAFTARAN) && (!keputusan.equals("D"))) {
                report = Boolean.TRUE;

                if (keputusan.equals("T")) {
                    String code = "ST";
                    boolean flag = semakDokumen(id, code);
                    if (!flag)
                        setDocumentGenerated(Boolean.FALSE);
                    else setDocumentGenerated(Boolean.TRUE);
                    return isDocumentGenerated();
                }

                if (keputusan.equals("G")) {
                    String code = "SGT";
                    boolean flag = semakDokumen(id, code);
                    if (!flag)
                        setDocumentGenerated(Boolean.FALSE);
                    else setDocumentGenerated(Boolean.TRUE);
                    return isDocumentGenerated();
                }

                
//                return true;
            }
        }
        

        if(isDebug){
            LOGGER.debug("id :" + id);
            LOGGER.debug("jabatan :" + jabatan);
            LOGGER.debug("stageId :" + stageId);
            LOGGER.debug("idWorkFlow :" + idWorkFlow);
            LOGGER.debug("txnCode :" + txnCode);
        }
        List<Map<String, String>> l = tabManager.getReports(idWorkFlow, stageId, txnCode);
        LOGGER.debug("report size = " + l.size());
        if (l.size() > 0) {
            report = Boolean.TRUE;
        }        
        
        if(!report) {
            //for HSBM case, no <reports> in XML
            //if use listener, will effect other workflow that doesnt need report to generate
            //but still using listener to validate something
            report = tabManager.reportGenerate(idWorkFlow, stageId);
            if (report) {
                if (stageId.equals("kemasukan")) {
                    boolean flag = semakDokumen(id,"DDHDK");
                    if(!flag) setDocumentGenerated(Boolean.FALSE);
                    else  setDocumentGenerated(Boolean.TRUE);
                }
            }
        }
        
        if(jabatan.equalsIgnoreCase(JabatanConstants.PENDAFTARAN)
                && stageId.equals("keputusan")) {
            report = Boolean.TRUE;
            boolean flag = semakDokumen(id,"DHDE");
//            LOGGER.debug("flag = " + flag);
            if(!flag)
                setDocumentGenerated(Boolean.FALSE);
            else
                setDocumentGenerated(Boolean.TRUE);
            
            flag = semakDokumen(id,"DHKE");
//            LOGGER.debug("flag = " + flag);
            if(!flag)
                setDocumentGenerated(Boolean.FALSE);
            else
                setDocumentGenerated(Boolean.TRUE);
            
            if (!flag) {
                flag = semakDokumen(id,"DHKK");
                if(!flag)
                    setDocumentGenerated(Boolean.FALSE);
                else
                    setDocumentGenerated(Boolean.TRUE);

                flag = semakDokumen(id,"DHDK");
                if(!flag)
                    setDocumentGenerated(Boolean.FALSE);
                else
                    setDocumentGenerated(Boolean.TRUE);
                }
            
        }

        for (Map<String, String> map : l) {
            String code = map.get("code");
            boolean flag = semakDokumen(id, code);
//            LOGGER.debug("flag = " + flag);
            if (!flag) {
                setDocumentGenerated(Boolean.FALSE);
            } else
                setDocumentGenerated(Boolean.TRUE);
        }

//        if(!report) setDocumentGenerated(Boolean.FALSE);

        if(isDebug) LOGGER.debug("is Document Generated = " + isDocumentGenerated());
        return isDocumentGenerated();
    }

    public boolean semakDokumen(String idPermohonan, String kodDokumen) {
        return semakDokumenService.semakDok(idPermohonan, kodDokumen);
    }

    public void semakKeputusan(String idPermohonan, Pengguna p, String stageId) {
        setKeputusan(fasaPermohonanService.checkKeputusan(idPermohonan, p.getIdPengguna(), null, stageId));
    }

    public boolean isFinalStage() {
        return tabManager.isFinalStage(idWorkFlow, stageId);
    }

    public boolean doTabbing() {
        setMainTab(Boolean.TRUE);
        tabBean = tabManager.getTabFlow(txnCode, stageId, idWorkFlow);
        if (tabBean != null) {
            setSecondTab(Boolean.TRUE);
            return Boolean.TRUE;
        } else {
            setMainTab(Boolean.FALSE);
            return Boolean.FALSE;
        }
    }

    public boolean isDistribute() {
        return tabManager.isDistribute(idWorkFlow, stageId);
    }

    public boolean doValidateViewRoles(Pengguna pengguna) {
        List<String> roles = tabManager.getViewRoles(idWorkFlow, stageId);
        for (String str : roles) {
            System.out.println("str " + str);
            System.out.println("pengguna.getPerananUtama().getNama(): " + pengguna.getPerananUtama().getNama());
            if (str.equalsIgnoreCase(pengguna.getPerananUtama().getNama())) {
                return true;
            }
        }
        return false;
    }

    public Resolution tab() {
        doTabbing();
        return new ForwardResolution("/WEB-INF/jsp/" + tabManager.getPage()).addParameter("tab", "true");
    }

    public Resolution mainTab() {
        String mainPageTab = "";
        if (mainId != null) {
            switch (mainId) {
                case 1:
                    return tab();
                case 2:
//                    mainPageTab = "fail_permohonan.jsp";
                    classId = "2";
                    return new RedirectResolution(FolderAction.class).addParameter("permohonan.idPermohonan", idPermohonan)
                            .addParameter("maintab", "true");
                case 3:
                    mainPageTab = "keputusan.jsp";
                    String pendaftar = getContext().getRequest().getParameter("pendaftar");
                    if (pendaftar != null && pendaftar.equalsIgnoreCase("true")) {
                        getContext().getRequest().setAttribute("pendaftar", Boolean.TRUE);
                    }
                    doTabbing();
                    classId = "3";
                    break;
                default:
                    return tab();
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/" + mainPageTab).addParameter("maintab", "true");
    }
}