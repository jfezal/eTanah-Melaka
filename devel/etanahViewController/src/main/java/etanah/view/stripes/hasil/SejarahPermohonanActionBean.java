/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

/**
 *
 * @author abu.mansur
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

@UrlBinding("/hasil/sejarah")
public class SejarahPermohonanActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(SejarahPermohonanActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();

    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    private List<FasaPermohonan> senaraiFP;
    private List<FasaPermohonan> senaraiFasaPermohonan;

    @Inject
    HakmilikDAO hakmilikDAO;
    private Hakmilik hakmilik;
    private String idHakmilik;

    @Inject
    private etanah.Configuration conf;
    private String negeri;

    @DefaultHandler
    public Resolution showForm(){
        if("04".equals(conf.getProperty("kodNegeri"))){
            negeri = "melaka";
        }
        return new JSP("hasil/sejarah_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){
        LOG.info("rehydrate:start");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("idPermohonan :"+idPermohonan);
        senaraiFasaPermohonan = new ArrayList<FasaPermohonan>();

        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);
            String[] nama = {"hakmilik"};
            Object[] value = {hakmilik};
            senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(nama, value, null);
            LOG.debug("senaraiHakmilikPermohonan.size() :"+senaraiHakmilikPermohonan.size());
            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                String[] nama2 = {"permohonan"};
                Object[] value2 = {hp.getPermohonan()};
                senaraiFP = fasaPermohonanDAO.findByEqualCriterias(nama2, value2, null);
                LOG.debug("senaraiFP.size() :"+senaraiFP.size());
                for (FasaPermohonan fp : senaraiFP) {
                    try{
                        if(fp.getKeputusan().getKod().equals("L") || fp.getKeputusan().getKod().equals("T"))
                            senaraiFasaPermohonan.add(fp);
                    }catch(NullPointerException npe){
                        LOG.error("FasaPermohonan.getKeputusan().getKod() = null || npe: "+npe);
                    }
                }
            }
        }
        LOG.info("rehydrate:finish");
    }
    
    public Resolution reloadEdit() {
        LOG.info("..:: INSIDE reloadEdit ::..");
        String id = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
            
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(id);
            String[] nama = {"hakmilik"};
            Object[] value = {hakmilik};
            senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(nama, value, null);
            LOG.debug("senaraiHakmilikPermohonan.size() :"+senaraiHakmilikPermohonan.size());
            for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                String[] nama2 = {"permohonan"};
                Object[] value2 = {hp.getPermohonan()};
                senaraiFP = fasaPermohonanDAO.findByEqualCriterias(nama2, value2, null);
                LOG.debug("senaraiFP.size() :"+senaraiFP.size());
                for (FasaPermohonan fp : senaraiFP) {
                    try{
                        if(fp.getKeputusan().getKod().equals("L") || fp.getKeputusan().getKod().equals("T"))
                            senaraiFasaPermohonan.add(fp);
                    }catch(NullPointerException npe){
                        LOG.error("FasaPermohonan.getKeputusan().getKod() = null || npe: "+npe);
                    }
                }
            }
        }
        return showForm();
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public List<FasaPermohonan> getSenaraiFP() {
        return senaraiFP;
    }

    public void setSenaraiFP(List<FasaPermohonan> senaraiFP) {
        this.senaraiFP = senaraiFP;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

}
