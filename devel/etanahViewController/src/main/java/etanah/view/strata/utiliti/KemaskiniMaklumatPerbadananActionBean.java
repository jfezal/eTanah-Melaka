/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/strata/kemaskini_maklumat_perbadanan")
public class KemaskiniMaklumatPerbadananActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    private Permohonan permohonan;
    private BadanPengurusan mc;
    private List<PermohonanBangunan> pBangunanL;
    private String idPermohonan;
    private String pengurusanNama;
    private String pengurusanAlamat1;
    private String pengurusanAlamat2;
    private String pengurusanAlamat3;
    private String pengurusanAlamat4;
    private String pengurusanPoskod;
    private String pengurusanJenisPengenalan;
    private String pengurusanKodNegeri;
    private String pengurusanNamaNegeri;
    private String pengurusanTarikhRujukan;
    private String pengurusanTarikhSijil;
    private String pengurusanNoRujukan;
    private String pengurusanTelefon;
    private String pengurusanTelefon2;
    private String idPihak;
    private static final Logger LOG = Logger.getLogger(KemaskiniMaklumatPerbadananActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================== idPermohonanKemaskiniPerbadanan : " + idPermohonan);
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        //return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_data_permohonan_strata.jsp");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_maklumat_perbadanan.jsp");
    }

    public Resolution viewMaklumatPerbadanan() {
        String idMohon = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idMohon);
        LOG.debug("================= Permohonan : " + permohonan);
        return new JSP("strata/utiliti/kemaskini_maklumat_perbadanan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================= idPermohonan : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getPermohonanSebelum() != null) {
                idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
            }
            mc = strService.findBdn(idPermohonan);
            LOG.info("---------mc---Id-----" + mc);
            if (mc != null) {
                if (mc.getPihak() != null) {

                    if (mc.getPihak().getNama() != null) {
                        pengurusanNama = mc.getPihak().getNama();
                    }
                    if (mc.getPihak().getAlamat1() != null) {
                        pengurusanAlamat1 = mc.getPihak().getAlamat1();
                    }
                    if (mc.getPihak().getAlamat2() != null) {
                        pengurusanAlamat2 = mc.getPihak().getAlamat2();
                    }
                    if (mc.getPihak().getAlamat3() != null) {
                        pengurusanAlamat3 = mc.getPihak().getAlamat3();
                    }
                    if (mc.getPihak().getAlamat4() != null) {
                        pengurusanAlamat4 = mc.getPihak().getAlamat4();
                    }
                    if (mc.getPihak().getPoskod() != null) {
                        pengurusanPoskod = mc.getPihak().getPoskod();
                    }
                    if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getNama() != null)) {
                        pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
                    }
                    //pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                    if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getKod() != null)) {
                        pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
                        //pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
                        LOG.info("---------pengurusanNamaNegeri--------" + pengurusanNamaNegeri);
                    }
                    if (mc.getPengurusanTarikhSijil() != null) {
                        pengurusanTarikhSijil = strService.formatSDF(mc.getPengurusanTarikhSijil());
                    }
                    pengurusanNoRujukan = mc.getPengurusanNoRujukan();
                    if (mc.getPengurusanTarikhRujukan() != null) {
                        pengurusanTarikhRujukan = strService.formatSDF(mc.getPengurusanTarikhRujukan());
                    }

                }
            }

        }
    }

    public Resolution simpanMaklumatBadan() {
        Pihak pihak = null;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        pBangunanL = strService.findByIDPermohonan(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        LOG.info("-----dokumen-----" + d);
        if (d != null) {
            if (d.getNamaFizikal() != null) {
                if (pBangunanL.size() > 0) {
                    mc = strService.findBdn(idPermohonan);
                    if (mc != null) {
                        if (mc.getPihak() != null) {
                            pihak = mc.getPihak();
                            infoAudit.setDiKemaskiniOleh(peng);
                            infoAudit.setTarikhKemaskini(new Date());
                            infoAudit.setDimasukOleh(pihak.getInfoAudit().getDimasukOleh());
                            infoAudit.setTarikhMasuk(pihak.getInfoAudit().getTarikhMasuk());
                            pihak.setInfoAudit(infoAudit);
                            pihak = setValuePihak(pihak);
                            mc.setInfoAudit(infoAudit);
                        } else {
                            pihak = new Pihak();
                            pihak = setValuePihak(pihak);
                            pihak.setInfoAudit(infoAudit);

                        }
                    } else {
                        mc = new BadanPengurusan();
                        mc.setInfoAudit(infoAudit);
                        pihak = new Pihak();
                        pihak = setValuePihak(pihak);
                        pihak.setInfoAudit(infoAudit);
                    }
                    pihak = strService.savePihak(pihak);
                    mc.setPihak(pihak);
                    mc.setPermohonan(permohonan);
                    mc.setCawangan(permohonan.getCawangan());
                    strService.simpanMaklumatBangunan(mc);
                    msg = "Maklumat telah berjaya disimpan.";

                } else {
                    error = "Sila klik tab 'Dokumen' untuk memuatnaik fail xml Jadual Petak (JPP).";
                    return new RedirectResolution(KemaskiniMaklumatPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
                }
            } else if (d.getNamaFizikal() == null) {
                error = "Sila klik tab 'Dokumen' untuk memuatnaik fail xml Jadual Petak (JPP).";
                return new RedirectResolution(KemaskiniMaklumatPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
            }
        } else if (d == null) {
            error = "Sila klik tab 'Dokumen' untuk memuatnaik fail xml Jadual Petak (JPP).";
            return new RedirectResolution(KemaskiniMaklumatPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
        }
        return new RedirectResolution(KemaskiniMaklumatPerbadananActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution updateMaklumatBadan() {
        LOG.debug("---------masuk update--------");
        //idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        Pihak pihak = new Pihak();
        String error = "";
        String msg = "";
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setTarikhMasuk(new java.util.Date());
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        mc = strService.findBdn(idPermohonan);
        if (mc != null) {
            if (mc.getPihak() != null) {
                pihak = mc.getPihak();
                infoAudit.setDiKemaskiniOleh(peng);
                infoAudit.setTarikhKemaskini(new Date());
                infoAudit.setDimasukOleh(pihak.getInfoAudit().getDimasukOleh());
                infoAudit.setTarikhMasuk(pihak.getInfoAudit().getTarikhMasuk());
                pihak.setInfoAudit(infoAudit);
                pihak = setValuePihak(pihak);
                mc.setInfoAudit(infoAudit);
            } else {
                pihak = new Pihak();
                pihak = setValuePihak(pihak);
                pihak.setInfoAudit(infoAudit);

            }
        } else {
            mc = new BadanPengurusan();
            mc.setInfoAudit(infoAudit);
            pihak = new Pihak();
            pihak = setValuePihak(pihak);
            pihak.setInfoAudit(infoAudit);
        }
        LOG.info("ss --> " + pihak.getAlamat1());
        pihak = strService.savePihak(pihak);
        mc.setPihak(pihak);
        mc.setPermohonan(permohonan);
        mc.setCawangan(permohonan.getCawangan());
        mc.setInfoAudit(infoAudit);
        strService.updateMaklumatBangunan(mc);
        msg = "Maklumat perbadanan pengurusan telah berjaya dikemaskini.";
        return new RedirectResolution(KemaskiniDataPermohonanStrataActionBean.class, "showForm&idPermohonan=" + idPermohonan).addParameter("error", error).addParameter("message", msg);
    }

    public Resolution resetForm() {
        pengurusanNama = "";
        pengurusanAlamat1 = "";
        pengurusanAlamat2 = "";
        pengurusanAlamat3 = "";
        pengurusanAlamat4 = "";
        pengurusanPoskod = "";
        pengurusanKodNegeri = "";
        return new JSP("strata/utiliti/kemaskini_maklumat_perbadanan.jsp").addParameter("tab", "true");
    }

    public BadanPengurusan getMc() {
        return mc;
    }

    public void setMc(BadanPengurusan mc) {
        this.mc = mc;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public void getValuePihak(Pihak pihak) {
        LOG.info("1234 : " + pihak.getNama());
        this.idPihak = String.valueOf(pihak.getIdPihak());
        this.pengurusanNama = pihak.getNama();
        this.pengurusanAlamat1 = pihak.getAlamat1();
        this.pengurusanAlamat2 = pihak.getAlamat2();
        this.pengurusanAlamat3 = pihak.getAlamat3();
        this.pengurusanAlamat4 = pihak.getAlamat4();
        this.pengurusanPoskod = pihak.getPoskod();
        if (pihak.getNegeri() != null) {
            this.pengurusanKodNegeri = pihak.getNegeri().getKod();
            this.pengurusanNamaNegeri = pihak.getNegeri().getNama();
        }
        this.pengurusanTelefon = pihak.getNoTelefon1();
        this.pengurusanTelefon2 = pihak.getNoTelefon2();
    }

    public Pihak setValuePihak(Pihak pihak) {
        pihak.setNama(pengurusanNama);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat2(pengurusanAlamat2);
        pihak.setAlamat3(pengurusanAlamat3);
        pihak.setAlamat4(pengurusanAlamat4);
        pihak.setPoskod(pengurusanPoskod);
        //pihak.setNoPengenalan(pengurusan);
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

        pihak.setNoTelefon1(pengurusanTelefon);
        pihak.setNoTelefon2(pengurusanTelefon2);
        return pihak;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getPengurusanNama() {
        return pengurusanNama;
    }

    public void setPengurusanNama(String pengurusanNama) {
        this.pengurusanNama = pengurusanNama;
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

    public String getPengurusanPoskod() {
        return pengurusanPoskod;
    }

    public void setPengurusanPoskod(String pengurusanPoskod) {
        this.pengurusanPoskod = pengurusanPoskod;
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

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
    }

    public String getPengurusanTelefon() {
        return pengurusanTelefon;
    }

    public void setPengurusanTelefon(String pengurusanTelefon) {
        this.pengurusanTelefon = pengurusanTelefon;
    }

    public String getPengurusanTelefon2() {
        return pengurusanTelefon2;
    }

    public void setPengurusanTelefon2(String pengurusanTelefon2) {
        this.pengurusanTelefon2 = pengurusanTelefon2;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }
}
