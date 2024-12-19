/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.service.common.HakmilikService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author afham
 */
@HttpCache(allow = false)
@UrlBinding("/pelupusan/pemilikTanah")
public class MilikTanahMCL_ActionBean extends AbleActionBean  {


     @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    HakmilikDAO hakmilikDAO ;
    @Inject
    PelupusanService plpservice ;
    private HakmilikPermohonan hakmilikPermohonan ;
    private Hakmilik hakmilik ;
    private Permohonan permohonan ;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/milik_tanah_mcl.jsp").addParameter("tab", "true");
    }

    @HttpCache(allow = false)
    public Resolution showForm2() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparanHakmilik() {
        return new JSP("consent/paparan_maklumat_hakmilik.jsp").addParameter("tab", "true");
    }

    public Resolution senaraiHakmilik() {
        return new JSP("daftar/senarai_hakmilik_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        permohonan = permohonanDAO.findById(idPermohonan) ;

        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
            HakmilikPermohonan hmp = new HakmilikPermohonan();
            hmp = hakmilikPermohonanList.get(i);

            hakmilikService.saveHakmilik(hmp.getHakmilik());
        }
         String[] tname = {"permohonan"} ;
        Object[] tvalue = {permohonan} ;
        List senaraiHakMilik = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        InfoAudit infoAudit = new InfoAudit() ;
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        if(senaraiHakMilik != null){
            hakmilikPermohonan = (HakmilikPermohonan) senaraiHakMilik.get(0) ;
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
            infoAudit.setTarikhKemaskini(new java.util.Date()) ;
            infoAudit.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk()) ;
             hakmilikPermohonan.setInfoAudit(infoAudit);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setJenisPenggunaanTanah(hakmilik.getKategoriTanah()) ;
            hakmilikPermohonan.setLot(hakmilik.getLot());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot()) ;
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            hakmilikPermohonan.setSyaratNyata(hakmilik.getSyaratNyata()) ;
            hakmilikPermohonan.setSekatanKepentingan(hakmilik.getSekatanKepentingan()) ;
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }




        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/milik_tanah_mcl.jsp").addParameter("tab", "true");
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    
}
