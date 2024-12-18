/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BangunanTingkatDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Sreenivasa Reddy Munagala.
 */
@UrlBinding("/strata/RMHSBadanPengurusan")
public class RMHSBadanPengurusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    PembangunanService devService;
    private Permohonan permohonan;
    private BadanPengurusan mc;
    private String pengurusanNama;
    private String pengurusanAlamat1;
    private String pengurusanAlamat2;
    private String pengurusanAlamat3;
    private String pengurusanAlamat4;
    private String pengurusanPoskod;
    private String pengurusanJenisPengenalan;
    private String pengurusanNoPengenalan;
    private String pengurusanKodNegeri;
    private String pengurusanNoSijil;
    private String pengurusanTarikhSijil;
    private String pengurusanNoRujukan;
    private String pengurusanTarikhRujukan;
    private String negeri;
    private String cawangan;
    private String idPermohonan;
    private static final Logger LOG = Logger.getLogger(RMHSBadanPengurusanActionBean.class);
    private String idPermohonanTerdahulu;

    public String getPengurusanNoPengenalan() {
        return pengurusanNoPengenalan;
    }

    public void setPengurusanNoPengenalan(String pengurusanNoPengenalan) {
        this.pengurusanNoPengenalan = pengurusanNoPengenalan;
    }

    public String getPengurusanNoSijil() {
        return pengurusanNoSijil;
    }

    public void setPengurusanNoSijil(String pengurusanNoSijil) {
        this.pengurusanNoSijil = pengurusanNoSijil;
    }

    public String getPengurusanAlamat1() {
        return pengurusanAlamat1;
    }

    public void setPengurusanAlamat1(String pengurusanAlamat1) {
        this.pengurusanAlamat1 = pengurusanAlamat1;
    }

    public String getPengurusanAlamat2() {
        return pengurusanAlamat2;
    }

    public void setPengurusanAlamat2(String pengurusanAlamat2) {
        this.pengurusanAlamat2 = pengurusanAlamat2;
    }

    public String getPengurusanAlamat3() {
        return pengurusanAlamat3;
    }

    public void setPengurusanAlamat3(String pengurusanAlamat3) {
        this.pengurusanAlamat3 = pengurusanAlamat3;
    }

    public String getPengurusanAlamat4() {
        return pengurusanAlamat4;
    }

    public void setPengurusanAlamat4(String pengurusanAlamat4) {
        this.pengurusanAlamat4 = pengurusanAlamat4;
    }

    public String getPengurusanNama() {
        return pengurusanNama;
    }

    public void setPengurusanNama(String pengurusanNama) {
        this.pengurusanNama = pengurusanNama;
    }

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
    }

    public String getPengurusanPoskod() {
        return pengurusanPoskod;
    }

    public void setPengurusanPoskod(String pengurusanPoskod) {
        this.pengurusanPoskod = pengurusanPoskod;
    }

    public String getPengurusanTarikhRujukan() {
        return pengurusanTarikhRujukan;
    }

    public void setPengurusanTarikhRujukan(String pengurusanTarikhRujukan) {
        this.pengurusanTarikhRujukan = pengurusanTarikhRujukan;
    }

    public String getPengurusanTarikhSijil() {
        return pengurusanTarikhSijil;
    }

    public void setPengurusanTarikhSijil(String pengurusanTarikhSijil) {
        this.pengurusanTarikhSijil = pengurusanTarikhSijil;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPengurusanJenisPengenalan() {
        return pengurusanJenisPengenalan;
    }

    public void setPengurusanJenisPengenalan(String pengurusanJenisPengenalan) {
        this.pengurusanJenisPengenalan = pengurusanJenisPengenalan;
    }

    public String getPengurusanKodNegeri() {
        return pengurusanKodNegeri;
    }

    public void setPengurusanKodNegeri(String pengurusanKodNegeri) {
        this.pengurusanKodNegeri = pengurusanKodNegeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        String stageId = "";

// added by User.
        stageId = "keputusanPTG";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {

            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(permohonan.getPermohonanSebelum().getIdPermohonan(), stageId);
            LOG.info("FASA PERMOHONAN" + fp);
            if (fp != null) {
                addSimpleError("IDPermohonan ini telah diproses");
                return new JSP("strata/pinda_permohanan/bangunan.jsp").addParameter("tab", "true");
            }
            if (StringUtils.isNotBlank(msg)) {
                addSimpleMessage(msg);
            }
            if (StringUtils.isNotBlank(error)) {
                addSimpleError(error);
            }
            return new JSP("strata/pinda_permohanan/rmhsmaklumat_bangunan.jsp").addParameter("tab", "true");
        } else {

            addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
            return new JSP("strata/pinda_permohanan/bangunan.jsp").addParameter("tab", "true");
        }

    }

    public Resolution showForm2() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        return new JSP("strata/rmhsmaklumat_bangunan/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
//            permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
            mc = strService.findBdn(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (mc != null) {

                pengurusanNama = mc.getPihak().getNama();
                pengurusanAlamat1 = mc.getPihak().getAlamat1();
                pengurusanAlamat2 = mc.getPihak().getAlamat2();
                pengurusanAlamat3 = mc.getPihak().getAlamat3();
                pengurusanAlamat4 = mc.getPihak().getAlamat4();
                pengurusanPoskod = mc.getPihak().getPoskod();
//                pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                if (mc.getPihak().getNegeri() != null) {
                    pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                }
                if (mc.getPengurusanTarikhSijil() != null) {
                }
                pengurusanTarikhSijil = strService.formatSDF(mc.getPengurusanTarikhSijil());
                pengurusanNoRujukan = mc.getPengurusanNoRujukan();
                if (mc.getPengurusanTarikhRujukan() != null) {
                }
                pengurusanTarikhRujukan = strService.formatSDF(mc.getPengurusanTarikhRujukan());

            }
        }
    }

    public Resolution simpanMaklumatBangunan() {
        Pihak pihak = null;
        idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        KodJenisPengenalan kjp = new KodJenisPengenalan();
        kjp.setKod(pengurusanJenisPengenalan);
        KodNegeri kodNegeri = new KodNegeri();
        kodNegeri.setKod(pengurusanKodNegeri);
        permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
        mc = strService.findBdn(idPermohonanTerdahulu);
        if (mc != null) {
        } else {
            mc.setPermohonan(permohonan);
        }

        mc.setCawangan(permohonan.getCawangan());

        if (mc.getPihak() != null) {
            pihak = mc.getPihak();
            infoAudit.setDiKemaskiniOleh(peng);

            pihak.setInfoAudit(infoAudit);
            pihak = setPihak(pihak);
        } else {
            pihak = new Pihak();
            pihak.setJenisPengenalan(kjp);
            pihak = setPihak(pihak);
        }
        mc.setInfoAudit(infoAudit);
        strService.simpanMaklumatBangunan(mc);
        msg = "Maklumat telah berjaya disimpan.";
        return new RedirectResolution(RMHSBadanPengurusanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Pihak setPihak(Pihak pihak) {
        pihak.setNama(pengurusanNama);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setPoskod(pengurusanPoskod);
//        pihak.setNoPengenalan(pengurusan);
        if (StringUtils.isEmpty(pengurusanJenisPengenalan)) {
        } else {
            KodJenisPengenalan kjp = new KodJenisPengenalan();
            kjp.setKod(pengurusanJenisPengenalan);
            pihak.setJenisPengenalan(kjp);
        }
        if (StringUtils.isEmpty(pengurusanKodNegeri)) {
        } else {
            KodNegeri kodNegeri = new KodNegeri();
            kodNegeri.setKod(pengurusanKodNegeri);
            pihak.setNegeri(kodNegeri);
        }
        return pihak;
    }

    public Resolution updateMaklumatBangunan() {

        // idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        Pihak pihak = null;
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        KodJenisPengenalan kjp = new KodJenisPengenalan();
        kjp.setKod(pengurusanJenisPengenalan);

        mc = strService.findBdn(idPermohonanTerdahulu);


        permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
        mc = strService.findBdn(idPermohonanTerdahulu);
        if (mc != null) {
        } else {
            mc.setPermohonan(permohonan);
        }

        mc.setCawangan(permohonan.getCawangan());

        if (mc.getPihak() != null) {
            pihak = mc.getPihak();
            infoAudit.setDiKemaskiniOleh(peng);

            pihak.setInfoAudit(infoAudit);
            pihak = setPihak(pihak);
        } else {
            pihak = new Pihak();
            pihak.setJenisPengenalan(kjp);
            pihak = setPihak(pihak);
        }
        msg = "Maklumat telah berjaya dikemaskini.";
        return new RedirectResolution(RMHSBadanPengurusanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }
}
