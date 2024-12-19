/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pihak;
import etanah.dao.PihakDAO;
import etanah.service.PihakPengambilanService;
import java.text.SimpleDateFormat;
import etanah.service.common.pihakberkepentinganPengambilanService;
import etanah.model.HakmilikPihakBerkepentingan;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;


/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/pihakberkepentinganpengambilan")
public class pihakberkepentinganPengambilanActionBean extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(pihakberkepentinganPengambilanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hHamilikPermohonanDAO;
    private Permohonan permohonan;
//    private PermohonanPengambilan permohonanPengambilan;
    PihakPengambilanService service;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<Pihak>pihaklist;
    private HakmilikPermohonan hpermohonan;
    private String counthp;
    private Pihak pihak;
    private List<HakmilikPihakBerkepentingan> hakmilikHPList;

     @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/maklumat_pihakberkepentingan.jsp").addParameter("tab", "true");
    }

     
       @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//           for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//            hakmilikHPList =  hakmilikPermohonan.getHakmilik().getSenaraiPihakBerkepentingan();
//               System.out.println("getSenaraiPihakBerkepentingan().size():"+ hakmilikHPList.size());
//
//           }
////        String[] tname = {"permohonan"};
//        Object[] model = {permohonan};
//        List<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentingan;
//
//
//        listHakmilikPihakBerkepentingan = hHamilikPermohonanDAO.findByEqualCriterias(tname, model, null);
//
//        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan :listHakmilikPihakBerkepentingan){
//            hakmilikHPList= hakmilikPihakBerkepentingan.getHakmilik().getSenaraiPihakBerkepentingan();
//        }
//        if (permohonan != null) {
////            counthp = service.findByidP(idPermohonan);
//                hakmilikPermohonanList = service.findByidP(idPermohonan);
//                for(int i = 0; i < hakmilikPermohonanList.size(); i++){
//
//                }
//
//            }
            }
        
       
    

        public Permohonan getPermohonan(){
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

     public List<Pihak> getPihakList() {
        return pihaklist;
    }

    public void setPihakList(List<Pihak> pihaklist) {
        this.pihaklist = pihaklist;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public List<HakmilikPihakBerkepentingan> gethakmilikHPList() {
        return hakmilikHPList;
    }

    public void sethakmilikHPList(List<HakmilikPihakBerkepentingan> hakmilikHPList) {
        this.hakmilikHPList = hakmilikHPList;
    }

   
    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

  
    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Pihak getPihak() {
        return pihak;
    }
}
