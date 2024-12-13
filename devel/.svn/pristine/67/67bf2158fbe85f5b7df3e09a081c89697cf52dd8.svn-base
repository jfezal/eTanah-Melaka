/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PemohonDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import java.math.BigDecimal;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.OnwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/carian_permohonan")
public class CarianPermohonanActionBean extends AbleActionBean {

//    @ValidateNestedProperties({
//        @Validate(field="id" , required=true, minvalue=17, on="cariPermohonan")
//    })
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PelupusanService plpservice;
    List<Permohonan> permohonanList;
    List<HakmilikPermohonan> hmpList;
    List<HakmilikPermohonan> hmpList1;
    List<PermohonanTuntutanKos> mohonTuntutList;
    List<PermohonanTuntutanKos> mohonTuntutList1;
    HakmilikPermohonan hakmilikPermohonan;
    PermohonanTuntutanKos permohonanTuntutKos;
    Permohonan permohonan;
    Permohonan p;
    Pemohon pemohon;
    String id;
    String tarikhKpsn;
    String statusKpsn;
    String kpsnOleh;
    boolean status = false;
    boolean simpan = false;
    String idPermohonan;
    String id2;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/carian_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution cariSemula() {
        getContext().getRequest().setAttribute("status", Boolean.FALSE);
        return new JSP("pelupusan/carian_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan pm = permohonanDAO.findById(idPermohonan);


        if (pm.getPermohonanSebelum() != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
            permohonan = permohonanDAO.findById(pm.getPermohonanSebelum().getIdPermohonan());
//            pemohon = plpservice.findPemohonByIdPemohon(permohonan.getIdPermohonan()); // DISABLED SINCE PEMOHON ALREADY DIFINE AT ITS OWN TAB
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            hmpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
//             statusKpsn = permohonan.getKeputusan().getNama() ;
//            tarikhKpsn = permohonan.getTarikhKeputusan().toString() ;
//                kpsnOleh = permohonan.getKeputusanOleh().getNama() ;

        }

    }

    public Resolution cariPermohonan() {
        permohonan = permohonanDAO.findById(id);

        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan") ;
        //p = permohonanDAO.findById(idPermohonan) ;
        if (permohonan != null) {
            getContext().getRequest().setAttribute("status", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpan", Boolean.FALSE);
            pemohon = plpservice.findPemohonByIdPemohon(permohonan.getIdPermohonan());
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            hmpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            addSimpleMessage("Maklumat dijumpai");
//        statusKpsn = permohonan.getKeputusan().getNama() ;
//       tarikhKpsn = permohonan.getTarikhKeputusan().toString() ;
//        kpsnOleh = permohonan.getKeputusanOleh().getNama() ;
            id2 = id;
            //p.setPermohonanSebelum(permohonan);
            // plpservice.simpanPermohonan(p);
        } else {
            addSimpleError("Maklumat tidak dijumpai");
            getContext().getRequest().setAttribute("status", Boolean.FALSE);
        }

        return new JSP("pelupusan/carian_permohonan.jsp").addParameter("tab", "true");
        //return new ForwardResolution("/WEB-INF/jsp/pelupusan/carian_permohonanCatitTanahMCL.jsp");

    }

    public Resolution simpanPermohonanSblm() {
        permohonan = permohonanDAO.findById(id2);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        p = permohonanDAO.findById(idPermohonan);
        p.setPermohonanSebelum(permohonan);
        plpservice.simpanPermohonan(p);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
//          String[] tname1 = {"p"};
//          Object[] model2 = {p};

        hmpList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
        //  hmpList1 = hakmilikPermohonanDAO.findByEqualCriterias(tname1, model2, null) ;
        BigDecimal cukaiBaru;
        if (!hmpList.isEmpty()) {
            hakmilikPermohonan = hmpList.get(0);
            cukaiBaru = hakmilikPermohonan.getCukaiBaru();
            HakmilikPermohonan hmp = plpservice.findByIdPermohonan(idPermohonan);
            hmp.setCukaiBaru(cukaiBaru);
            plpservice.simpanHakmilikPermohonan2(hmp);
        }


        getContext().getRequest().setAttribute("simpan", Boolean.TRUE);
        rehydrate() ;

        addSimpleMessage("Maklumat Telah Disimpan");

        return new JSP("pelupusan/carian_permohonan.jsp").addParameter("tab", "true");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarikhKpsn() {
        return tarikhKpsn;
    }

    public void setTarikhKpsn(String tarikhKpsn) {
        this.tarikhKpsn = tarikhKpsn;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKpsnOleh() {
        return kpsnOleh;
    }

    public void setKpsnOleh(String kpsnOleh) {
        this.kpsnOleh = kpsnOleh;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getStatusKpsn() {
        return statusKpsn;
    }

    public void setStatusKpsn(String statusKpsn) {
        this.statusKpsn = statusKpsn;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public List<HakmilikPermohonan> getHmpList() {
        return hmpList;
    }

    public void setHmpList(List<HakmilikPermohonan> hmpList) {
        this.hmpList = hmpList;
    }

    public List<HakmilikPermohonan> getHmpList1() {
        return hmpList1;
    }

    public void setHmpList1(List<HakmilikPermohonan> hmpList1) {
        this.hmpList1 = hmpList1;
    }

    public boolean isSimpan() {
        return simpan;
    }

    public void setSimpan(boolean simpan) {
        this.simpan = simpan;
    }
}
