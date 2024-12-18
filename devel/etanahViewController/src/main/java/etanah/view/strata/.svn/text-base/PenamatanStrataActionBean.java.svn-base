package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/permohonanPNSS")
public class PenamatanStrataActionBean extends AbleActionBean {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BadanPengurusanDAO badanUrusDAO;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private Pemohon pemohon;
    private BadanPengurusan badanUrus;
    private static final Logger LOG = Logger.getLogger(PenamatanStrataActionBean.class);
    private String kn;
    private boolean test = false;
    private String idBadanP = null;
    private String namaPengurusan;
    private String noPengenalan;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        String idBadan = getContext().getRequest().getParameter("idBadan");
        LOG.info("idBadan :" + idBadan);
        if (StringUtils.isNotEmpty(idBadan)) {
//        badanUrus = badanUrusDAO.findById(Long.parseLong(idBadan));
            LOG.info("idBadan : " + Long.parseLong(idBadan));
        }

    }

    @DefaultHandler
    public Resolution showForm1() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = strService.findById(idPermohonan);
        return new JSP("strata/skim/kemasukan_maklumat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("strata/skim/maklumatBadanPengurusan.jsp").addParameter("popup", "true");
    }

    public Resolution copyPenyerah() {
        LOG.info("Start : copy");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pemohon = strService.findById(idPermohonan);

        if (pemohon == null) {
            pemohon = new Pemohon();
        }
        Pihak pihak = new Pihak();
        InfoAudit ia = strService.getInfo(pengguna);
        pihak.setNama(permohonan.getPenyerahNama());
        pihak.setAlamat1(permohonan.getPenyerahAlamat1());
        pihak.setAlamat2(permohonan.getPenyerahAlamat2());
        pihak.setAlamat3(permohonan.getPenyerahAlamat3());
        pihak.setAlamat4(permohonan.getPenyerahAlamat4());
        pihak.setPoskod(permohonan.getPenyerahPoskod());
        pihak.setNegeri(permohonan.getPenyerahNegeri());
        pihak.setNoTelefon1(permohonan.getPenyerahNoTelefon1());
        pihak.setJenisPengenalan(permohonan.getPenyerahJenisPengenalan());
        pihak.setNoPengenalan(permohonan.getPenyerahNoPengenalan());
        pihak.setInfoAudit(ia);
        pihak = strService.savePihak(pihak);

        pemohon.setPihak(pihak);
        pemohon.setPermohonan(permohonan);
        pemohon.setInfoAudit(ia);
        pemohon.setCawangan(permohonan.getCawangan());
        strService.savePemohon(pemohon);
        return new JSP("strata/skim/kemasukan_maklumat.jsp").addParameter("tab", "true");

    }

    public Resolution reload() {
        LOG.info("RELOAD : ");
        return new RedirectResolution(PenamatanStrataActionBean.class, "showForm1");
    }

    public Resolution cariBadanUrus() {
        LOG.info("POPUP :");

//        String idBadan = getContext().getRequest().getParameter("idBadan");
//        if(idBadan != null){
//             badanUrus = badanUrusDAO.findById(Long.parseLong(idBadan));
//        }


        LOG.info("badanUrus cariBadanUrus : :" + badanUrus);
        return new JSP("strata/skim/maklumatBadanPengurusan.jsp").addParameter("popup", "true");
    }

    public Resolution updateBadan() {
        String idBadan = getContext().getRequest().getParameter("idBadan");
        LOG.info("update idBadan urus ::" + idBadanP);
        String error = null;

        if (idBadanP.equals("tiada")) {
            error = "Sila Pilih Nama";
            LOG.info("Nama bangunan :" + idBadanP);
            addSimpleError(error);
        } else {

            LOG.info("Nama bangunan :" + idBadanP);
            badanUrus = badanUrusDAO.findById(Long.parseLong(idBadanP));

//            LOG.info("jenis pengenalan : " + badanUrus.getPengurusanNama());
//            namaPengurusan = badanUrus.getPengurusanNama();
//            noPengenalan = badanUrus.getPengurusanNoPengenalan();
        }


        return new JSP("strata/skim/maklumatBadanPengurusan.jsp").addParameter("popup", "true");
//        test = true ;
//        return new JSP("strata/skim/maklumatBadanPengurusan.jsp").addParameter("popup", "true") ;
    }

    public Resolution simpanBadanPihak() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idBadan = getContext().getRequest().getParameter("idBadan");
        pemohon = strService.findById(idPermohonan);
badanUrus = badanUrusDAO.findById(Long.valueOf(idBadan));
        if (pemohon == null) {
            pemohon = new Pemohon();
        }
        Pihak pihak = new Pihak();
        InfoAudit ia = strService.getInfo(pengguna);
//        pihak.setNama(badanUrus.getPengurusanNama());
//        pihak.setAlamat1(badanUrus.getPengurusanAlamat1());
//        pihak.setAlamat2(badanUrus.getPengurusanAlamat2());
//        pihak.setAlamat3(badanUrus.getPengurusanAlamat3());
//        pihak.setAlamat4(badanUrus.getPengurusanAlamat4());
//        pihak.setPoskod(badanUrus.getPengurusanPoskod());
//        pihak.setNegeri(badanUrus.getPengurusanKodNegeri());
//        pihak.setNoPengenalan(badanUrus.getPengurusanNoPengenalan());
//        pihak.setJenisPengenalan(badanUrus.getPengurusanJenisPengenalan());
        pihak.setInfoAudit(ia);
        pihak = strService.savePihak(pihak);

        pemohon.setPihak(pihak);
        pemohon.setPermohonan(permohonan);
        pemohon.setInfoAudit(ia);
        pemohon.setCawangan(permohonan.getCawangan());
        strService.savePemohon(pemohon);
        return new JSP("strata/skim/kemasukan_maklumat.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public BadanPengurusan getBadanUrus() {
        return badanUrus;
    }

    public void setBadanUrus(BadanPengurusan badanUrus) {
        this.badanUrus = badanUrus;
    }

    public String getKn() {
        return kn;
    }

    public void setKn(String kn) {
        this.kn = kn;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public String getIdBadanP() {
        return idBadanP;
    }

    public void setIdBadanP(String idBadanP) {
        this.idBadanP = idBadanP;
    }

    public String getNamaPengurusan() {
        return namaPengurusan;
    }

    public void setNamaPengurusan(String namaPengurusan) {
        this.namaPengurusan = namaPengurusan;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }
}
