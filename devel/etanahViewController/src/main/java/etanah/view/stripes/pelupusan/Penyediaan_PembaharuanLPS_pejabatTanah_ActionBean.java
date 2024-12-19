package etanah.view.stripes.pelupusan;


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.KodNegeri;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@UrlBinding("/pelupusan/sedia_lesenLPS")
public class Penyediaan_PembaharuanLPS_pejabatTanah_ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;

    private  String idPermohonan ;
    private Permohonan permohonan ;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/sedia_pembaharuanLPS_pjbtTnh.jsp").addParameter("tab", "true") ;
    }

    public Resolution simpanPembaharuanLesen(){
        //simpan Maklumat Bayaran
        addSimpleMessage("Maklumat Telah Disimpan") ;
        return new JSP("pelupusan/sedia_pembaharuanLPS_pjbtTnh.jsp").addParameter("tab","true") ;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       permohonan = permohonanDAO.findById(idPermohonan);
    }
}
