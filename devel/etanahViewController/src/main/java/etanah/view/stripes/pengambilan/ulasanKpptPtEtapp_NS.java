/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/pengambilan/ulasanKpptPt_NS")
public class ulasanKpptPtEtapp_NS extends AbleActionBean {

    private static Logger logger = Logger.getLogger(ulasanKpptPtEtapp_NS.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PengambilanMMKService ambilService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private String idPermohonan;
    private Long idKertas;
    private PermohonanKertas permohonanKertas;
    private PermohonanKertasKandungan ulasanKppt;
    private PermohonanKertasKandungan ulasanPt;
    private Permohonan permohonan;
    private String kppt;
    private String pt;
    private PermohonanKertasKandungan KpptKer;
    private PermohonanKertasKandungan PtKer;

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public Resolution show() {
        getContext().getRequest().setAttribute("show", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }
    
    public Resolution edit() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws NamingException {
        logger.info("------------rehydrate--------------");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MB");
        if (permohonanKertas != null) {
            idKertas = permohonanKertas.getIdKertas();
            ulasanKppt = ambilService.findMMKdrafKandNew1(idKertas, 9);
            ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

            if (ulasanKppt != null) {
                kppt = ulasanKppt.getKandungan();
            } else {
                kppt = "";
            }
            if (ulasanPt != null) {
                pt = ulasanPt.getKandungan();
            } else {
                pt = "";
            }

            logger.info(" Kppt kandungan = " + kppt);
            logger.info(" Pt kandungan = " + pt);
        } else {
        }

//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public Resolution ulasanKppt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MB");
        if (permohonanKertas != null) {
            idKertas = permohonanKertas.getIdKertas();
            ulasanKppt = ambilService.findMMKdrafKandNew1(idKertas, 9);
//             ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

            if (ulasanKppt != null) {
                kppt = ulasanKppt.getKandungan();
            } else {
                kppt = "test";
            }
            logger.info(" Kppt kandungan = " + kppt);

        }
        getContext().getRequest().setAttribute("kppt", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public Resolution ulasanPt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MB");
        if (permohonanKertas != null) {
            idKertas = permohonanKertas.getIdKertas();
            ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);
//             ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

            if (ulasanPt != null) {
                pt = ulasanKppt.getKandungan();
            } else {
                pt = "test";
            }
            logger.info(" Pt kandungan = " + pt);

        }
        getContext().getRequest().setAttribute("pt", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws NamingException {
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        permohonan = permohonanDAO.findById(idPermohonan);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MB");
//        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKertas = permohonanKertas.getIdKertas();
        logger.info(" idKertas = " + idKertas);

        ulasanKppt = ambilService.findMMKdrafKandNew1(idKertas, 9);
//        logger.info(" ulasanKppt = " + ulasanKppt.getIdKandungan());
        if (ulasanKppt != null) {
            KpptKer = permohonanKertasKandunganDAO.findById(ulasanKppt.getIdKandungan());
            InfoAudit iaP = KpptKer.getInfoAudit();
            iaP.setTarikhKemaskini(new Date());
            iaP.setDiKemaskiniOleh(peng);
            KpptKer.setInfoAudit(iaP);
        } else {
            KpptKer = new PermohonanKertasKandungan();
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            KpptKer.setInfoAudit(iaP);
        }
        
        if (kppt == null || kppt.equals("")){
           KpptKer.setKandungan(" - "); 
        } else {
            KpptKer.setKandungan(kppt);
        }

        KpptKer.setBil(9);
        KpptKer.setCawangan(permohonan.getCawangan());
        KpptKer.setKertas(permohonanKertas);
        logger.info(" simpan = " + kppt);
        ambilService.simpanPermohonanKertasKandungan(KpptKer);
//        permohonanKertasKandunganDAO.saveOrUpdate(KpptKer);

        //simpan PT
        ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);
//        logger.info(" ulasanPt = " + ulasanPt.getIdKandungan());
        if (ulasanPt != null) {
            PtKer = permohonanKertasKandunganDAO.findById(ulasanPt.getIdKandungan());
            InfoAudit iaP = PtKer.getInfoAudit();
            iaP.setTarikhKemaskini(new Date());
            iaP.setDiKemaskiniOleh(peng);
            PtKer.setInfoAudit(iaP);
        } else {
            PtKer = new PermohonanKertasKandungan();
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            PtKer.setInfoAudit(iaP);
        }
        
         if (pt == null || pt.equals("")){
           PtKer.setKandungan(" - "); 
        } else {
            PtKer.setKandungan(pt);
        }
         
        PtKer.setBil(10);
        PtKer.setCawangan(permohonan.getCawangan());
        PtKer.setKertas(permohonanKertas);
        logger.info(" simpan = " + pt);
        ambilService.simpanPermohonanKertasKandungan(PtKer);

//        logger.info(" check id bru = ");
//        PermohonanKertasKandungan ulasanKppt2 = ambilService.findMMKdrafKandNew1(idKertas, 9);
//        logger.info(" ulasanKppt = " + ulasanKppt2.getIdKandungan());
//        PermohonanKertasKandungan ulasanPt2 = ambilService.findMMKdrafKandNew1(idKertas, 10);
//        logger.info(" ulasanPt2 = " + ulasanPt2.getIdKandungan());
//
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPt() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanKertas = ambilService.findMohonKertasByIdMohon(idPermohonan, "MB");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        idKertas = permohonanKertas.getIdKertas();
        PermohonanKertasKandungan ulasanPt = ambilService.findMMKdrafKandNew1(idKertas, 10);

        if (ulasanPt != null) {
            PtKer = permohonanKertasKandunganDAO.findById(ulasanPt.getIdKandungan());
            InfoAudit iaP = PtKer.getInfoAudit();
            iaP.setTarikhKemaskini(new Date());
            iaP.setDiKemaskiniOleh(peng);
            PtKer.setInfoAudit(iaP);
        } else {
            PtKer = new PermohonanKertasKandungan();
            InfoAudit iaP = new InfoAudit();
            iaP.setTarikhMasuk(new Date());
            iaP.setDimasukOleh(peng);
            PtKer.setInfoAudit(iaP);
        }

        PtKer.setKandungan(pt);
        PtKer.setBil(10);
        PtKer.setCawangan(permohonan.getCawangan());
        PtKer.setKertas(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(PtKer);

        getContext().getRequest().setAttribute("pt", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_Kppt_Pt_NS.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(Long idKertas) {
        this.idKertas = idKertas;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasKandungan getUlasanKppt() {
        return ulasanKppt;
    }

    public void setUlasanKppt(PermohonanKertasKandungan ulasanKppt) {
        this.ulasanKppt = ulasanKppt;
    }

    public PermohonanKertasKandungan getUlasanPt() {
        return ulasanPt;
    }

    public void setUlasanPt(PermohonanKertasKandungan ulasanPt) {
        this.ulasanPt = ulasanPt;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKppt() {
        return kppt;
    }

    public void setKppt(String kppt) {
        this.kppt = kppt;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public PermohonanKertasKandungan getKpptKer() {
        return KpptKer;
    }

    public void setKpptKer(PermohonanKertasKandungan KpptKer) {
        this.KpptKer = KpptKer;
    }

    public PermohonanKertasKandungan getPtKer() {
        return PtKer;
    }

    public void setPtKer(PermohonanKertasKandungan PtKer) {
        this.PtKer = PtKer;
    }
}
