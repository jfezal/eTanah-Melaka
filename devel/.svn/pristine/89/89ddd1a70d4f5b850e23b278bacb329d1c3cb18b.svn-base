/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rohan
 */
@UrlBinding("/pelupusan/ulasanYBADUN")
public class Ulasan_YB_Adun extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanRujukanLuarService rujukanLuarService;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PelupusanService pelupusanService ;
    private KodCawangan cawangan;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/ulasanYBADUN.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
      pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         permohonan = permohonanService.findById(idPermohonan);
     permohonanRujukanLuar = pelupusanService.findMohonRujukLuarIdPermohonanNamaPenyedia(idPermohonan, "YB ADUN");
//        if (permohonan != null) {
//            idPermohonan = permohonan.getIdPermohonan();
//
//        }
    }


    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan=permohonanDAO.findById(idPermohonan);
        cawangan=permohonan.getCawangan();

        PermohonanRujukanLuar permohonanRujukanLuarTemp = pelupusanService.findMohonRujukLuarIdPermohonanNamaPenyedia(idPermohonan, "YB ADUN");

        InfoAudit info = new InfoAudit();
        if (permohonanRujukanLuarTemp == null) {
            permohonanRujukanLuarTemp = new PermohonanRujukanLuar();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuarTemp.setInfoAudit(info);
            permohonanRujukanLuarTemp.setPermohonan(permohonan);
            permohonanRujukanLuarTemp.setCawangan(cawangan);

            KodRujukan kodRujukan = kodRujukanDAO.findById("FL");

            permohonanRujukanLuarTemp.setKodRujukan(kodRujukan);
            permohonanRujukanLuarTemp.setNamaPenyedia("YB ADUN");
            permohonanRujukanLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());
            permohonanRujukanLuarTemp.setTarikhRujukan(permohonanRujukanLuar.getTarikhRujukan());
            permohonanRujukanLuarTemp.setNoRujukan(permohonanRujukanLuar.getNoRujukan());
            permohonanRujukanLuarTemp.setUlasan(permohonanRujukanLuar.getUlasan());
        }
        permohonanRujukanLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());
        permohonanRujukanLuarTemp.setTarikhRujukan(permohonanRujukanLuar.getTarikhRujukan());
        permohonanRujukanLuarTemp.setNoRujukan(permohonanRujukanLuar.getNoRujukan());
        permohonanRujukanLuarTemp.setUlasan(permohonanRujukanLuar.getUlasan());
        rujukanLuarService.saveOrUpdate(permohonanRujukanLuarTemp);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/ulasanYBADUN.jsp").addParameter("tab", "true");

    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
}
