/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import com.thoughtworks.xstream.converters.Converter;
import etanah.dao.AkaunDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikDAO;
//import etanah.dao.HakmilikPihakBerkepentingan;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PenggunaPerananDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import java.math.BigDecimal;
import java.math.MathContext;
import org.hibernate.Transaction;
import java.text.ParseException;
import java.util.Iterator;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.validator.Length;
import etanah.service.common.PermohonanPihakService;
import java.util.LinkedList;

@UrlBinding("/pengambilan/pampasan")
public class PampasanActionBean  extends AbleActionBean {

    private static Logger logger = Logger.getLogger(perbicaraanKosGantiRugiActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PenggunaPerananDAO penggunaPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    public PampasanActionBean(AkaunDAO akaunDAO, HakmilikDAO hakmilikDAO) {
        this.akaunDAO = akaunDAO;
        this.hakmilikDAO = hakmilikDAO;
    }
    private boolean flag = false;
//    private List<Akaun> akaunList;
    private List<Pemohon> pemohonList;
    private Permohonan permohonan;
    
    private List<HakmilikPermohonan> hakmilikPermohonanList;


   

    @DefaultHandler
    public Resolution showForm() {
//        showMaklumatPemilik = false;
          //  getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/pampasan.jsp").addParameter("tab", "true");
    }

    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
           
        }
    }

  
   
     public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }




}

