/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikWarisDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikWaris;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.KodPemilikan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.service.EnforceService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;

/**
 *
 * @author nurshahida.radzi
 * modified : latifah.iskak
 * modify by sitifariza.hanim (12042011)
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/maklumat_tanah")
public class MaklumatTanahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatTanahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private int size = 0;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList1;
    private KodPemilikan pemilikan;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pengguna pguna;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private List<HakmilikWaris> hakmilikWarisList;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private HakmilikWaris hakmilikWaris;
    private List<HakmilikPermohonan> mohonHakmilikList;

    public HakmilikWaris getHakmilikWaris() {
        return hakmilikWaris;
    }

    public void setHakmilikWaris(HakmilikWaris hakmilikWaris) {
        this.hakmilikWaris = hakmilikWaris;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihak() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihak(HakmilikPihakBerkepentingan hakmilikPihak) {
        this.hakmilikPihakBerkepentingan = hakmilikPihak;
    }

    public List<HakmilikWaris> getHakmilikWarisList() {
        return hakmilikWarisList;
    }

    public void setHakmilikWarisList(List<HakmilikWaris> hakmilikWarisList) {
        this.hakmilikWarisList = hakmilikWarisList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
//    private String[] atasTanah;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
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

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList1() {
        return pihakKepentinganList1;
    }

    public void setPihakKepentinganList1(List<HakmilikPihakBerkepentingan> pihakKepentinganList1) {
        this.pihakKepentinganList1 = pihakKepentinganList1;
    }
    
    public List<HakmilikPermohonan> getMohonHakmilikList() {
        return mohonHakmilikList;
    }

    public void setMohonHakmilikList(List<HakmilikPermohonan> mohonHakmilikList) {
        this.mohonHakmilikList = mohonHakmilikList;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_tanah.jsp").addParameter("tab", "true");
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
            permohonan = permohonanDAO.findById(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            if (hakmilikPermohonanList.size() != 0) {
                for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                    if (hakmilikPermohonanList.get(i).getHakmilik() != null) {
                        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
                            hakmilikPermohonan = hp;
                            hakmilik = hp.getHakmilik();
                        }

                        String[] v1 = {"hakmilik"};
                        Object[] n1 = {hakmilik};
                        pihakKepentinganList = hakmilikPihakBerkepentinganDAO.findByEqualCriterias(v1, n1, null);

                        pihakKepentinganList1 = new ArrayList<HakmilikPihakBerkepentingan>();
                        pihakKepentinganList1 = enforceService.findSenaraiPihak(hakmilikPermohonan.getHakmilik().getIdHakmilik());

                        hakmilikWarisList = new ArrayList<HakmilikWaris>();
                        hakmilikWarisList = enforceService.findByIdHp(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                    }
                }

            }
        }
    }

    public Resolution simpan() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        System.out.println("hakmilik ==== " + hakmilik);

        if (hakmilik == null) {
            System.out.println("hak  milik null");
            addSimpleError("Maklumat hakmilik tidak dijumpai");
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("penguatkuasaan/maklumat_tanah.jsp").addParameter("tab", "true");

        } else {
            InfoAudit ia = new InfoAudit();
            hakmilikPermohonan = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);
            if (hakmilikPermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = hakmilikPermohonan.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            hakmilikPermohonan.setHakmilik(hakmilik);
            hakmilikPermohonan.setPermohonan(permohonan);
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikPermohonan.setLuasTerlibat(hakmilik.getLuas());
            hakmilikPermohonan.setNoLot(hakmilik.getNoLot());
            hakmilikPermohonan.setKodUnitLuas(hakmilik.getKodUnitLuas());
            enforceService.simpanhakmilikPermohonan(hakmilikPermohonan);
            addSimpleMessage("Maklumat telah berjaya dijumpai.");
        }
//        return new JSP("penguatkuasaan/maklumat_tanah.jsp").addParameter("tab","true");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatTanahActionBean.class, "locate");

    }
}
