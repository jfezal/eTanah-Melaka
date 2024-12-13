/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author  Mohd faidzal .
 */
@UrlBinding("/strata/rmhsmaklumat_Pemohon")
public class RMHSMaklumatPemohonActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PembangunanService devService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan = new Permohonan();
    private Pemohon pemohon = new Pemohon();
    private Pihak pihak = new Pihak();
    private static final Logger LOG = Logger.getLogger(RMHSMaklumatPemohonActionBean.class);
    private String stageId;
    private String idPermohonanTerdahulu;
    private String namaPemohon;
    private String jenisPemohon;
    private String noPengPemohon;
    private String alamatPemohon1;
    private String alamatPemohon2;
    private String alamatPemohon3;
    private String alamatPemohon4;
    private String poskodPemohon;
    private String negeriPemohon;

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu") != null) {
            idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        }
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution terus() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        stageId = "keputusanPTG";
        if (idPermohonanTerdahulu == null) {
            pihak = null;
            clearPihak();
        }
        getContext().getRequest().getSession().removeAttribute("idPermohonanTerdahulu");

        if (idPermohonanTerdahulu != null) {
            Permohonan permohonanL = permohonanDAO.findById(idPermohonanTerdahulu);
            LOG.info(permohonanL.getIdPermohonan());
            getContext().getRequest().getSession().setAttribute("idPermohonanTerdahulu", idPermohonanTerdahulu);
            FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu, stageId);
            if (fp != null) {
                addSimpleError("IDPermohonan ini telah diproses");
                return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemohon.jsp").addParameter("tab", "true");
            }
            if (permohonan != null && fp == null) {
                getContext().getRequest().getSession().setAttribute("idPermohonanTerdahulu", idPermohonanTerdahulu);
                idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
                pemohon = strService.findById(idPermohonanTerdahulu);
                pihak = pemohon.getPihak();
                setPihak(pihak);
                permohonan.setPermohonanSebelum(permohonanL);
                strService.savePermohonan(permohonan);
                //rehydrate();
                return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemohon.jsp").addParameter("tab", "true");
            } else {
                addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
                return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemohon.jsp").addParameter("tab", "true");
            }
        } else {
            addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
            return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemohon.jsp").addParameter("tab", "true");
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            Permohonan permohonanL = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            Pemohon pemohon = strService.findById(permohonanL.getIdPermohonan());
            if (pemohon != null) {
                setPihak(pemohon.getPihak());
            }
        }
    }

    public void setPihak(Pihak pihak) {
        namaPemohon = pihak.getNama();
        if (pihak.getJenisPengenalan() != null) {
            jenisPemohon = pihak.getJenisPengenalan().getNama();
        }
        noPengPemohon = pihak.getNoPengenalan();
        alamatPemohon1 = pihak.getAlamat1();
        alamatPemohon2 = pihak.getAlamat2();
        alamatPemohon3 = pihak.getAlamat3();
        alamatPemohon4 = pihak.getAlamat4();
        poskodPemohon = pihak.getPoskod();
        if (pihak.getNegeri() != null) {
            negeriPemohon = pihak.getNegeri().getNama();
        }
//        return pihak;
    }

    public void clearPihak() {
        namaPemohon = "";
        jenisPemohon = "";
        noPengPemohon = "";
        alamatPemohon1 = "";
        alamatPemohon2 = "";
        alamatPemohon3 = "";
        alamatPemohon4 = "";
        poskodPemohon = "";
        negeriPemohon = "";
    }

    public String getAlamatPemohon1() {
        return alamatPemohon1;
    }

    public void setAlamatPemohon1(String alamatPemohon1) {
        this.alamatPemohon1 = alamatPemohon1;
    }

    public String getAlamatPemohon2() {
        return alamatPemohon2;
    }

    public void setAlamatPemohon2(String alamatPemohon2) {
        this.alamatPemohon2 = alamatPemohon2;
    }

    public String getAlamatPemohon3() {
        return alamatPemohon3;
    }

    public void setAlamatPemohon3(String alamatPemohon3) {
        this.alamatPemohon3 = alamatPemohon3;
    }

    public String getAlamatPemohon4() {
        return alamatPemohon4;
    }

    public void setAlamatPemohon4(String alamatPemohon4) {
        this.alamatPemohon4 = alamatPemohon4;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public String getJenisPemohon() {
        return jenisPemohon;
    }

    public void setJenisPemohon(String jenisPemohon) {
        this.jenisPemohon = jenisPemohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getNegeriPemohon() {
        return negeriPemohon;
    }

    public void setNegeriPemohon(String negeriPemohon) {
        this.negeriPemohon = negeriPemohon;
    }

    public String getNoPengPemohon() {
        return noPengPemohon;
    }

    public void setNoPengPemohon(String noPengPemohon) {
        this.noPengPemohon = noPengPemohon;
    }

    public String getPoskodPemohon() {
        return poskodPemohon;
    }

    public void setPoskodPemohon(String poskodPemohon) {
        this.poskodPemohon = poskodPemohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
}
