/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PihakDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.service.common.PemohonService;
import etanah.model.*;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
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
import org.hibernate.Transaction;

@UrlBinding("/pengambilan/pendaftaranSuratKuasaWakil")
public class pendaftaran_SuratKuasa_WakilActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(pendaftaran_SuratKuasa_WakilActionBean.class);
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonService pemohonService;
    
    private List<Pemohon> pemohonList;
    private Permohonan permohonan;
    private Dokumen dokumen;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private String idsblm;
    private String borangK;
    private String kodDokumen;
    private Pemohon pemohon;
    private Pihak pihak;
    private List<PermohonanTuntutanKos> senaraiKosgantiRugi;
    private BigDecimal jumCaraBayar = (BigDecimal.ZERO);
    private boolean showTable;
    private boolean showTambahPermintaan;
    String nama;
    private String idPermohonan;


    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {      
        return new JSP("pengambilan/BorangI.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("pengambilan/keputusan_mesyuarat_jkkpt.jsp").addParameter("tab", "true");
    }

    public Resolution semakIdSebelum()
    {
        //String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       idsblm = getContext().getRequest().getParameter("idsblm");
       //kodDokumen = getContext().getRequest().getParameter("kodDokumen");
//     String idsblm = "0505ACQ2010007027";

        if (StringUtils.isNotBlank(idsblm))

            permohonanRujukanLuar = pengambilanService.findByIdPermohonan(idsblm);
            permohonan = pengambilanService.findByIdSblm(idsblm);
            if( permohonan!=null)
            getContext().getRequest().setAttribute("detail", Boolean.TRUE);
//            JOptionPane.showMessageDialog(null, permohonanRujukanLuar.getNoFail());
//            JOptionPane.showMessageDialog(null, permohonan.getSebab());

        return new JSP("pengambilan/permohonan_penarikanbalik1.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
      idsblm = getContext().getRequest().getParameter("idsblm");
      String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
      Permohonan p = permohonanDAO.findById(idPermohonan);
      senaraiKosgantiRugi = pengambilanService.findByIDMohonKos(idPermohonan);
           if (StringUtils.isNotBlank(idsblm)) {
            permohonanRujukanLuar = pengambilanService.findByIdPermohonan(idsblm);
            permohonan = permohonanDAO.findById(idsblm);
           // JOptionPane.showMessageDialog(null, permohonanRujukanLuar.getNoFail());
        }
//
//        if (idPermohonan != null) {
//            Permohonan p = permohonanDAO.findById(idPermohonan);
//
//            AmaunDituntut = new String[senaraiKosgantiRugi.size()];
//
//            for (int i = 0; i < senaraiKosgantiRugi.size(); i++) {
//                PermohonanTuntutanKos h = (PermohonanTuntutanKos)senaraiKosgantiRugi.get(i);
////                AmaunDituntut[i] = h.getAmaunTuntutan();
//            }
//    }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getIdsblm() {
        return idsblm;
    }

    public void setIdsblm(String idsblm) {
        this.idsblm = idsblm;
    }
}
