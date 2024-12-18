/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tstr
 */
@UrlBinding("/daftar/mohonRujLuar")
public class MohonRujLuar extends AbleActionBean{
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider; 
    private PermohonanUrusan urusan;
    private Permohonan permohonan;
    public  PermohonanRujukanLuar permohonanRujukanLuar;
    private List<etanah.model.PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    public String noFail;
    private static final Logger LOGGER = Logger.getLogger(MaklumatUrusniagaActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    @DefaultHandler
    public Resolution showForm() {
        
        return new JSP("daftar/form_permohonanRujukanLuar.jsp").addParameter("tab", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOGGER.info("rehydrate");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            
            List<PermohonanRujukanLuar> mohonList = permohonan.getSenaraiRujukanLuar();
            if (mohonList.size() > 0) {
                permohonanRujukanLuar = mohonList.get(mohonList.size() -1);
                if (permohonanRujukanLuar != null ) {
                    if(permohonanRujukanLuar.getNoFail() != null)
                    {noFail = permohonanRujukanLuar.getNoFail();}
                }
                
            }
        }
    }
    
    @HandlesEvent("save")
    public Resolution saveOrUpdate() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
            
            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
            senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            for (int i = 0; i < senaraiHakmilik.size(); i++) {
                HakmilikPermohonan hakmilikPermohonan = senaraiHakmilik.get(i);
/*Enter New Data*/  if (senaraiPermohonanRujukanLuar.isEmpty()) {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                        LOGGER.debug("Hakmilik permohonan :" + hakmilikPermohonan.getHakmilik());
                        permohonanRujukanLuar.setHakmilik(hakmilikPermohonan.getHakmilik());
                        permohonanRujukanLuar.setPermohonan(permohonan);
                        permohonanRujukanLuar.setInfoAudit(info);
                        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujukanLuar.setNoFail(noFail);
                        permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
                        permohonanRujukanLuarService.saveOrUpdateByConn(permohonanRujukanLuar);
/*Update Data*/    } else {
                        permohonanRujukanLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hakmilikPermohonan.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
                        info = permohonanRujukanLuar.getInfoAudit();
                        info.setTarikhKemaskini(new java.util.Date());
                        info.setDiKemaskiniOleh(peng);
                        permohonanRujukanLuar.setInfoAudit(info);
                        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujukanLuar.setNoFail(noFail);
                        permohonanRujukanLuarService.saveOrUpdateByConn(permohonanRujukanLuar);
                        }
            }
            tx.commit();
            addSimpleMessage("Kemasukan Data Berjaya");
        } catch (Exception ex) {
            tx.rollback();
            ex.printStackTrace();
            addSimpleError("Kemasukan Data Gagal.");
        }        
        return new JSP("daftar/form_permohonanRujukanLuar.jsp").addParameter("tab", "true");
    }
}
