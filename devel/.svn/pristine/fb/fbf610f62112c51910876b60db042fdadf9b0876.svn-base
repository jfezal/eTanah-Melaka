/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
//import etanah.model.FasaPermohonanUlasan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/ulasan_jabatan_teknikal")
public class UlasanJabatanTeknikalActionBean extends AbleActionBean{
     @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    private Permohonan permohonan;
//    private FasaPermohonanUlasan fasaPermohonanUlasan;
    private KodAgensi kodAgensi;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pengambilan/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

  @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpan"})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (!(permohonan.getSenaraiFasa().isEmpty())) {

                for (int i = 0; i < permohonan.getSenaraiFasa().size(); i++) {
                    FasaPermohonan fasaP = new FasaPermohonan();
                    fasaP = permohonan.getSenaraiFasa().get(i);

                    if (fasaP.getIdPengguna().equals("jkmns1") || fasaP.getIdPengguna().equals("jtkns1")) {

//                        if (!(fasaP.getSenaraiUlasan().isEmpty())) {
//                            fasaPermohonanUlasan = fasaP.getSenaraiUlasan().get(0);
//
//                            if (fasaP.getIdPengguna().equals("jkmns1")) {
//
//                                kodAgensi = kodAgensiDAO.findById("0909");
//                            }
//
//                            else if (fasaP.getIdPengguna().equals("jtkns1")) {
//
//                                kodAgensi = kodAgensiDAO.findById("2117");
//                            }
//                        }
                    }
                }
            }

        }
    }

//    public Resolution simpan() {
//
//        Pengguna pengguna = new Pengguna();
//
//        if (kodAgensi.getKod().equals("0909")) {
//            pengguna.setIdPengguna("jkmns1");
//        } else if (kodAgensi.getKod().equals("2117")) {
//            pengguna.setIdPengguna("jtkns1");
//        }
//
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(pengguna);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//
//        FasaPermohonan fasaPermohonan = new FasaPermohonan();
//
//        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
//
//        fasaPermohonan.setPermohonan(permohonan);
//        fasaPermohonan.setCawangan(permohonan.getCawangan());
//        fasaPermohonan.setInfoAudit(infoAudit);
//
//        if (kodAgensi.getKod().equals("0909")) {
//            fasaPermohonan.setIdPengguna("jkmns1");
//        } else if (kodAgensi.getKod().equals("2117")) {
//            fasaPermohonan.setIdPengguna("jtkns1");
//        }
//
//        conService.simpanFasaPermohonan(fasaPermohonan);
//
//        fasaPermohonanUlasan.setFasa(fasaPermohonan);
//        fasaPermohonanUlasan.setInfoAudit(infoAudit);
//
//        conService.simpanFasaPermohonanUlasan(fasaPermohonanUlasan);
//
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//
//        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_jabatan_teknikal.jsp").addParameter("tab", "true");
//    }

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

}
