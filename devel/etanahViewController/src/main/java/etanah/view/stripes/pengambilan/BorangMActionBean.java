/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/borang_m")
public class BorangMActionBean extends AbleActionBean {
    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    
    @Inject
    PengambilanService pengambilanService;
    
    private static final Logger LOG = Logger.getLogger(BorangMActionBean.class);
    private String idPermohonan;
    private String idHakmilik;
    private String ulasanPemohon;
    private String ulasanPenilai;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private Pengguna pguna;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakList;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Borang_M.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
    }

    public Resolution hakmilikDetails() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null && idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan= pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            hakmilikPihakList= pengambilanService.getHakmilikPihakListbyIdHakmilik(idHakmilik);
            if(hakmilikPermohonan != null){
                hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if(hakmilikPerbicaraan != null) {
                    ulasanPemohon = hakmilikPerbicaraan.getPemohonUlasan();
                    ulasanPenilai = hakmilikPerbicaraan.getUlasanPenilai();
                }
            }
        }

        return new JSP("pengambilan/Borang_M.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        hakmilikPihakList= pengambilanService.getHakmilikPihakListbyIdHakmilik(idHakmilik);

        InfoAudit info = new InfoAudit();

        if(hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan=new HakmilikPerbicaraan();
            info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setBatalRizab('T');
            hakmilikPerbicaraan.setKawasanPBT('T');
            hakmilikPerbicaraan.setPelanPembangunan('T');
        }else{
            info = hakmilikPerbicaraan.getInfoAudit();
            info.setDiKemaskiniOleh(pguna);
            info.setTarikhKemaskini(new java.util.Date());
        }
        hakmilikPerbicaraan.setInfoAudit(info);
        hakmilikPerbicaraan.setPemohonUlasan(ulasanPemohon);
        hakmilikPerbicaraan.setUlasanPenilai(ulasanPenilai);
        hakmilikPerbicaraan = pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/Borang_M.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getUlasanPemohon() {
        return ulasanPemohon;
    }

    public void setUlasanPemohon(String ulasanPemohon) {
        this.ulasanPemohon = ulasanPemohon;
    }

    public String getUlasanPenilai() {
        return ulasanPenilai;
    }

    public void setUlasanPenilai(String ulasanPenilai) {
        this.ulasanPenilai = ulasanPenilai;
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakList() {
        return hakmilikPihakList;
    }

    public void setHakmilikPihakList(List<HakmilikPihakBerkepentingan> hakmilikPihakList) {
        this.hakmilikPihakList = hakmilikPihakList;
    }

    

}
