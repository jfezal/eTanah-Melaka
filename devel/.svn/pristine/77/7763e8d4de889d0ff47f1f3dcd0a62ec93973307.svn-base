/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;

import etanah.model.Pengguna;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_mesyuarat")
public class LucuthakActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LucuthakActionBean.class);
     @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    EnforcementService enforcementService;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodCawangan cawangan;
    private KodDokumen doc;
    private KodRujukan kr;
    private String idRujukan;
    private String catatan;
    private String tarikhSidang;
    private String tarikhSidang1;
    private String jam;
    private String minit;
    private String saat;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
    private List<PermohonanRujukanLuar> senaraiMesyuarat;
    
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/minit_arahan_lucuthak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/minit_arahan_lucuthak.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("penguatkuasaan/minit_arahan_popup.jsp").addParameter("popup", "true");
    }

    public Resolution popupedit() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        return new JSP("penguatkuasaan/minit_arahan_edit.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
       return new RedirectResolution(LucuthakActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         idRujukan = getContext().getRequest().getParameter("idRujukan");
       if (idPermohonan != null) {
            senaraiMesyuarat = enforcementService.findMesyByIDPermohonan(idPermohonan);
            if(idRujukan !=null){
                      permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
            if ( permohonanRujukanLuar.getTarikhSidang()!= null) {
                jam = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                minit = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                saat = "00";
                ampm = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
            }
            }
        }

      

    }

    public Resolution simpanPopup() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        catatan = getContext().getRequest().getParameter("catatan");
        permohonanRujukanLuar = new PermohonanRujukanLuar();
        cawangan = permohonan.getCawangan();
          doc = new KodDokumen();
          doc.setKod("SMMR");

        kr = new KodRujukan();
        kr.setKod("NF");

        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(cawangan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        permohonanRujukanLuar.setCatatan(catatan);

        tarikhSidang = tarikhSidang + " " + jam + ":" + minit + ":00 " + ampm;

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
        logger.debug("tess1 :" + permohonanRujukanLuar.getIdRujukan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + permohonanRujukanLuar.getIdRujukan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/minit_arahan_lucuthak.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

        if (permohonanRujukanLuar != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setCawangan(cawangan);
            //  aduanPemantauan.setDipantauOleh(peng);
            enforcementService.deleteMesy(permohonanRujukanLuar);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LucuthakActionBean.class, "locate");
    }

    public Resolution simpanSingle() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         permohonan = permohonanDAO.findById(idPermohonan);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        tarikhSidang1 =getContext().getRequest().getParameter("permohonanRujukanLuar.tarikhSidang");
        catatan =getContext().getRequest().getParameter("permohonanRujukanLuar.catatan");
        logger.info(idRujukan);
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
          doc = new KodDokumen();
         doc.setKod("SMMR");
         kr = new KodRujukan();
        kr.setKod("NF");

         cawangan = permohonan.getCawangan();

        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(cawangan);
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        permohonanRujukanLuar.setCatatan(catatan);
        tarikhSidang = tarikhSidang1 + " " + jam + ":" + minit + ":00 " + ampm;

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
        logger.debug("tess1 :" + permohonanRujukanLuar.getIdRujukan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/minit_arahan_lucuthak.jsp").addParameter("tab", "true");
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
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

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public List<PermohonanRujukanLuar> getSenaraiMesyuarat() {
        return senaraiMesyuarat;
    }

    public void setSenaraiMesyuarat(List<PermohonanRujukanLuar> senaraiMesyuarat) {
        this.senaraiMesyuarat = senaraiMesyuarat;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public KodDokumen getDoc() {
        return doc;
    }

    public void setDoc(KodDokumen doc) {
        this.doc = doc;
    }

    public KodRujukan getKr() {
        return kr;
    }

    public void setKr(KodRujukan kr) {
        this.kr = kr;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getTarikhSidang1() {
        return tarikhSidang1;
    }

    public void setTarikhSidang1(String tarikhSidang1) {
        this.tarikhSidang1 = tarikhSidang1;
    }

    
}
