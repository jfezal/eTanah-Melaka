/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
//import etanah.dao.FasaPermohonanUlasanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.view.stripes.consent.JabatanTeknikal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/common/ulasan_jabatan_teknikal")
public class UlasanJabatanTeknikalActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
//    FasaPermohonanUlasanDAO fasaPermohonanUlasanDAO;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
//    private FasaPermohonanUlasan fasaPermohonanUlasan;
    private KodAgensi kodAgensi;

    private List listUlasan = new ArrayList();
    private List listUlasan2 = new ArrayList();
    private String[] jabatanArray;
    private String[] ulasanArray;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        JabatanTeknikal jt = new JabatanTeknikal();
        listUlasan2.add(jt);

//        if (!(permohonan.getSenaraiFasa().isEmpty())) {
//
//            listUlasan2.clear();
//
//            for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {
//
//                FasaPermohonan fasaP = new FasaPermohonan();
//                fasaP = permohonan.getSenaraiFasa().get(i);
//
//                for (int j = 0; j < fasaP.getSenaraiUlasan().size(); j++) {
//
//                    FasaPermohonanUlasan fasaU = new FasaPermohonanUlasan();
//                    fasaU = fasaP.getSenaraiUlasan().get(j);
//                    //listUlasan2.add(fasaPermohonanUlasan);
//
//                    if (fasaP.getIdPengguna().equals("jkmns1")) {
//                        kodAgensi = kodAgensiDAO.findById("0909");
//                    } else if (fasaP.getIdPengguna().equals("jtkns1")) {
//                        kodAgensi = kodAgensiDAO.findById("2117");
//                    }
//
//                    jt = new JabatanTeknikal();
//                    jt.setAgensi(kodAgensi);
//                    jt.setUlasan(fasaU.getUlasan());
//
//                    listUlasan2.add(jt);
//                }
//            }
//        }
    }

    public Resolution simpan() {

//        for (int i = 0; i < jabatanArray.length; i++) {
//
//            Pengguna pengguna = new Pengguna();
//
//            if (jabatanArray[i].equals("0909")) {
//                pengguna.setIdPengguna("jkmns1");
//            } else if (jabatanArray[i].equals("2117")) {
//                pengguna.setIdPengguna("jtkns1");
//            }
//
//            InfoAudit infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//
//            permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
//
//            FasaPermohonan fasaPermohonan = new FasaPermohonan();
//            fasaPermohonan.setPermohonan(permohonan);
//            fasaPermohonan.setCawangan(permohonan.getCawangan());
//            fasaPermohonan.setInfoAudit(infoAudit);
//
//
//            if (jabatanArray[i].equals("0909")) {
//                fasaPermohonan.setIdPengguna("jkmns1");
//            } else if (jabatanArray[i].equals("2117")) {
//                fasaPermohonan.setIdPengguna("jtkns1");
//            }
//
//            FasaPermohonanUlasan fasaUlasan = new FasaPermohonanUlasan();
//            fasaUlasan.setUlasan(ulasanArray[i]);
//            fasaUlasan.setFasa(fasaPermohonan);
//            fasaUlasan.setInfoAudit(infoAudit);
//
//            conService.simpanFasa(fasaPermohonan, fasaUlasan);
//        }

        JabatanTeknikal jt = new JabatanTeknikal();

        listUlasan2.clear();

        jabatanArray = null;
        ulasanArray = null;

        listUlasan2.add(jt);

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

//    public FasaPermohonanUlasan getFasaPermohonanUlasan() {
//        return fasaPermohonanUlasan;
//    }
//
//    public void setFasaPermohonanUlasan(FasaPermohonanUlasan fasaPermohonanUlasan) {
//        this.fasaPermohonanUlasan = fasaPermohonanUlasan;
//    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public List getListUlasan() {
        return listUlasan;
    }

    public void setListUlasan(List listUlasan) {
        this.listUlasan = listUlasan;
    }

    public String[] getUlasanArray() {
        return ulasanArray;
    }

    public void setUlasanArray(String[] ulasanArray) {
        this.ulasanArray = ulasanArray;
    }

    public String[] getJabatanArray() {
        return jabatanArray;
    }

    public void setJabatanArray(String[] jabatanArray) {
        this.jabatanArray = jabatanArray;
    }

    public List getListUlasan2() {
        return listUlasan2;
    }

    public void setListUlasan2(List listUlasan2) {
        this.listUlasan2 = listUlasan2;
    }
}

