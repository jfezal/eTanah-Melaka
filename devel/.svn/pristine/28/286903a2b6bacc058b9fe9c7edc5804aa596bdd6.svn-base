/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean ;
import able.stripes.JSP ;
import com.google.inject.Inject ;
import etanah.dao.HakmilikDAO;
import etanah.service.PelupusanService ;
import etanah.dao.PermohonanDAO ;
import etanah.dao.HakmilikPermohonanDAO ;
import etanah.model.Hakmilik;
import etanah.model.Permohonan ;
import etanah.model.Pengguna ;
import etanah.model.HakmilikPermohonan ;
import etanah.model.InfoAudit;
import etanah.view.etanahActionBeanContext ;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before ;
import net.sourceforge.stripes.action.DefaultHandler ;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.UrlBinding ;
import net.sourceforge.stripes.action.Resolution ;
import net.sourceforge.stripes.controller.LifecycleStage ;



/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/laporanMasuk_MCL")
public class LaporanMasukTanahMCL_ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO ;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    PelupusanService pelupusanService ;
    @Inject
    HakmilikDAO hakmilikDAO ;
    private Permohonan permohonan ;
    private Hakmilik hakmilik ;
    private HakmilikPermohonan hakmilikPermohonan ;
     private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    //private Pengguna pengguna ;
    String idPermohonan ;




    @DefaultHandler
    public Resolution showForm(){
        return new JSP("pelupusan/laporan_masuk_tanah_MCL.jsp").addParameter("tab", "true") ;
    }

    @Before(stages={LifecycleStage.BindingAndValidation})
    public void rehydrate(){

        //pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;


        if(idPermohonan != null){
            permohonan = permohonanDAO.findById(idPermohonan) ;
            HakmilikPermohonan hmTemp = new HakmilikPermohonan();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik() ;
            if(!hakmilikPermohonanList.isEmpty()){
                hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
            }
            
            String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
                if(idhakmilikpermohonan == null){
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                    hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
                }
                else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
                    hakmilikPermohonan = new HakmilikPermohonan();
                    hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
                    hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
                }
         

        }
    }
    
    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_masuk_tanah_MCL.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        //pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER) ;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik()) ;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit() ;
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk());
        if(hakmilikPermohonan != null){
        //InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        hakmilikPermohonan.setInfoAudit(infoAudit) ;
//        hakmilikPermohonan.setKegunaanTanah(hakmilik.getKegunaanTanah());
        hakmilikPermohonan.setSyaratNyata(hakmilik.getSyaratNyata());
        
        }
//        hakmilikPermohonan.setTempohHakmilik("Kekal");
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        addSimpleMessage("Maklumat Telah Disimpan") ;

        return new JSP("pelupusan/laporan_masuk_tanah_MCL.jsp").addParameter("tab", "true");
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    
}
