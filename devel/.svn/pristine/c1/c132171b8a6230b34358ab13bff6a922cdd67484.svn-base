/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

//import etanah.model.HakmilikPermohonan;
import org.apache.commons.lang.StringUtils;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanLokasiDAO;
import etanah.model.AduanLokasi;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;

import etanah.model.KodPemilikan;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_lokasi")
public class MaklumatLokasiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatLokasiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    @Inject
    EnforceService enforceService;
    private Permohonan permohonan;
    private AduanLokasi aduanLokasi;
    private KodPemilikan pemilikan;
    private KodCawangan cawangan;
    private KodBandarPekanMukim bandarPekanMukim;
    private String idPermohonan;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<AduanLokasi> senaraiAduanLokasi;
    private String noLot;
    private String lokasi;
    private String idAduanLokasi;

    @DefaultHandler
    public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/lokasi_pencerobohan.jsp").addParameter("tab", "true");
    }
    public Resolution showForm4() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/lokasi_pencerobohan.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan_terpeinci.jsp").addParameter("popup", "true");
    }
    public Resolution popupedit() {
        idAduanLokasi = getContext().getRequest().getParameter("idAduanLokasi");
        aduanLokasi =  aduanLokasiDAO.findById(Long.parseLong(idAduanLokasi));
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan_edit.jsp").addParameter("popup", "true");
    }
     public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(MaklumatLokasiActionBean.class, "locate");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

          idAduanLokasi = getContext().getRequest().getParameter("idAduanLokasi");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
              cawangan = permohonan.getCawangan();
             String bandarPekanMukim1= cawangan.getDaerah().getKod();
              if (StringUtils.isNotBlank(bandarPekanMukim1)) {
                setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
        }
            senaraiAduanLokasi = enforceService.findByIDs(idPermohonan);
        }
        
    }
   public Resolution simpanPopup() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        aduanLokasi = new AduanLokasi();
        aduanLokasi.setPermohonan(permohonan);
        aduanLokasi.setPemilikan(pemilikan);
        aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
        aduanLokasi.setCawangan(cawangan);
        noLot = getContext().getRequest().getParameter("noLot");
        aduanLokasi.setNoLot(noLot);
        lokasi = getContext().getRequest().getParameter("lokasi");
        aduanLokasi.setLokasi(lokasi);
       Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = aduanLokasi.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanLokasi.setInfoAudit(ia);
        }
        else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforceService.simpanAduanLokasi(aduanLokasi);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanLokasi.getIdAduanLokasi());
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/lokasi_pencerobohan.jsp").addParameter("tab", "true");

    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idAduanLokasi = getContext().getRequest().getParameter("idAduanLokasi");
        aduanLokasi = enforceService.findAduanLokasiByIdAduan(Long.parseLong(idAduanLokasi));

        if (aduanLokasi != null) {

            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            aduanLokasi.setInfoAudit(ia);
           aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
            aduanLokasi.setCawangan(cawangan);
            enforceService.deleteAllLokasi(aduanLokasi);
            
        }
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatLokasiActionBean.class, "locate");
    }
    public Resolution simpanSingle() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idAduanLokasi = getContext().getRequest().getParameter("idAduanLokasi");
        logger.info(idAduanLokasi);
        aduanLokasi = enforceService.findAduanLokasiByIdAduan(Long.parseLong(idAduanLokasi));

        // KodCawangan caw = new KodCawangan();
        //caw.setKod("05");
        cawangan = permohonan.getCawangan();
       if (aduanLokasi == null) {
            aduanLokasi = new AduanLokasi();
        }
        aduanLokasi.setPermohonan(permohonan);
        aduanLokasi.setPemilikan(pemilikan);
        aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
        aduanLokasi.setCawangan(cawangan);
       
       if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanLokasi.setInfoAudit(ia);
        }
        else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforceService.simpanAduanLokasi(aduanLokasi);
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanLokasi.getIdAduanLokasi());
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/lokasi_pencerobohan.jsp").addParameter("tab", "true");

    }

   public String getIdAduanLokasi() {
        return idAduanLokasi;
    }

    public void setIdAduanLokasi(String idAduanLokasi) {
        this.idAduanLokasi = idAduanLokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public List<AduanLokasi> getSenaraiAduanLokasi() {
        return senaraiAduanLokasi;
    }

    public void setSenaraiAduanLokasi(List<AduanLokasi> senaraiAduanLokasi) {
        this.senaraiAduanLokasi = senaraiAduanLokasi;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }
}

