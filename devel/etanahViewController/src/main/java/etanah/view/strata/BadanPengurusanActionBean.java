/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.Dokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/badanPengurusan")
public class BadanPengurusanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanSkimDAO permohonanSkimDAO;
    @Inject
    StrataPtService strService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private Pihak pihak;
    private BadanPengurusan mc;
    private String idPihak;
    private String pengurusanNama;
    private String pengurusanAlamat1;
    private String pengurusanAlamat2;
    private String pengurusanAlamat3;
    private String pengurusanAlamat4;
    private String pengurusanPoskod;
    private String pengurusanJenisPengenalan;
    private String pengurusanKodNegeri;
    private String pengurusanNamaNegeri;
    private String negeri;
    private String cawangan;
    private String idPermohonan;
    private static final Logger LOG = Logger.getLogger(BadanPengurusanActionBean.class);
    private String pengurusanTarikhSijil;
    private String pengurusanNoRujukan;
    private String pengurusanTarikhRujukan;
    private String pengurusanTelefon;
    private String pengurusanTelefon2;
    private List<PermohonanBangunan> pBangunanL;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private boolean readOnly = false;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            mc = strService.findBdn(idPermohonan);

            if (mc == null && permohonan.getPermohonanSebelum() != null) {
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
                    if (mc.getPihak().getNoTelefon1() != null) {
                        pengurusanTelefon = mc.getPihak().getNoTelefon1();
                    }
                    if (mc.getPihak().getNoTelefon2() != null) {
                        pengurusanTelefon2 = mc.getPihak().getNoTelefon2();
                    }
                    if (mc.getPihak().getPoskod() != null) {
                        pengurusanPoskod = mc.getPihak().getPoskod();
                    }
                    if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getNama() != null)) {
                        pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
                    }
//                    pengurusanJenisPengenalan = mc.getPihak().getJenisPengenalan().getKod();
                    if ((mc.getPihak().getNegeri() != null) && (mc.getPihak().getNegeri().getKod() != null)) {
                        pengurusanKodNegeri = mc.getPihak().getNegeri().getKod();
//                        pengurusanNamaNegeri = mc.getPihak().getNegeri().getNama();
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

            if ((permohonan.getKodUrusan().getKod().equals("PHPP") || permohonan.getKodUrusan().getKod().equals("PHPC")) && conf.getProperty("kodNegeri").equals("04")) {
                senaraiHakmilikPermohonan = strService.findIdHakmilikSortAsc(idPermohonan);
                BadanPengurusan bdnUrus1 = strService.findBdn(idPermohonan);

                if (bdnUrus1 == null) {
                    if (!senaraiHakmilikPermohonan.isEmpty()) {
                        String hm = senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
                        List<HakmilikPermohonan> hp = strService.findIdPBBSByIdHakmilik(hm);
                        if (!hp.isEmpty()) {
                            for (HakmilikPermohonan hp2 : hp) {
                                String idmohon = hp2.getPermohonan().getIdPermohonan();
                                Permohonan mohon = strService.findPermohonanSblm(idmohon);
                                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")) {
                                    BadanPengurusan bdnUrus = strService.findBdn(mohon.getIdPermohonan());
                                    if (bdnUrus != null) {
                                        Pihak phk = strService.findByIdPihak(bdnUrus.getPihak().getIdPihak());
                                        if (phk != null) {
                                            pengurusanNama = phk.getNama();
                                            pengurusanAlamat1 = phk.getAlamat1();
                                            pengurusanAlamat2 = phk.getAlamat2();
                                            pengurusanAlamat3 = phk.getAlamat3();
                                            pengurusanAlamat4 = phk.getAlamat4();
                                            pengurusanPoskod = phk.getPoskod();
                                            if (phk.getNegeri() != null) {
                                                pengurusanKodNegeri = phk.getNegeri().getKod();
                                            }
                                            pengurusanTelefon = phk.getNoTelefon1();
                                            pengurusanTelefon2 = phk.getNoTelefon2();

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        addSimpleError("Sila pastikan maklumat hakmilik petak adalah betul.");
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
        InfoAudit infoAudit1 = new InfoAudit();
        infoAudit1.setDiKemaskiniOleh(peng);
        infoAudit1.setTarikhKemaskini(new java.util.Date());
        pBangunanL = strService.findByIDPermohonan(idPermohonan);
        Dokumen d = strService.findDok(idPermohonan, "JPP");
        LOG.info("-----dokumen-----" + d);
        List<PermohonanBangunan> mhnBngn = strService.checkMhnBangunanExist(idPermohonan);
        HakmilikPermohonan hp = strService.findMohonHakmilik(idPermohonan);
        PermohonanStrata mhnStrata = strService.findID(idPermohonan);
        PermohonanSkim mhnSkim = strService.findIDSkimByIDMH(hp.getId());
        mc = strService.findBdn(idPermohonan);

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (!mhnBngn.isEmpty()) {
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
                    pihak = setValuePihak2(pihak);
                    pihak.setInfoAudit(infoAudit);
                }
                pihak = strService.savePihak(pihak);
                mc.setPihak(pihak);
                mc.setPermohonan(permohonan);
                mc.setCawangan(permohonan.getCawangan());
                mc.setPengurusanTarikhRujukan(new Date());
                mc.setPengurusanTarikhSijil(new Date());
                mc.setPengurusanNoRujukan(pengurusanNoRujukan);
                strService.simpanMaklumatBangunan(mc);

                if (mhnSkim != null) {
                    mhnSkim.setBadanPengurusan(mc);
                    mhnSkim.setNamaPemaju(pihak.getNama());
                    mhnSkim.setInfoAudit(infoAudit1);
                    mhnSkim = strService.saveSkim(mhnSkim);
//                permohonanSkimDAO.saveOrUpdate(mhnSkim);
                }

                msg = "Maklumat telah berjaya disimpan.";
            } else {
                error = "Sila isikan maklumat jadual petak di tab Jadual Petak XML atau Jadual Petak Manual";
                return new RedirectResolution(BadanPengurusanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
            }
        } else {
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
                    mc.setPengurusanTarikhRujukan(new Date());
                    mc.setPengurusanTarikhSijil(new Date());
                    mc.setPengurusanNoRujukan(pengurusanNoRujukan);
                } else {
                    pihak = new Pihak();
                    pihak = setValuePihak(pihak);
                    pihak.setInfoAudit(infoAudit);

                }
            } else {
                mc = new BadanPengurusan();
                mc.setInfoAudit(infoAudit);
                mc.setPengurusanTarikhRujukan(new Date());
                mc.setPengurusanTarikhSijil(new Date());
                mc.setPengurusanNoRujukan(pengurusanNoRujukan);
                pihak = new Pihak();
                pihak = setValuePihak2(pihak);
                pihak.setInfoAudit(infoAudit);
            }
            pihak = strService.savePihak(pihak);
            mc.setPihak(pihak);
            mc.setPermohonan(permohonan);
            mc.setCawangan(permohonan.getCawangan());
            mc.setPengurusanTarikhRujukan(new Date());
            mc.setPengurusanTarikhSijil(new Date());
            mc.setPengurusanNoRujukan(pengurusanNoRujukan);
            strService.simpanMaklumatBangunan(mc);

            if (mhnSkim != null) {
                mhnSkim.setBadanPengurusan(mc);
                mhnSkim.setNamaPemaju(pihak.getNama());
                mhnSkim.setInfoAudit(infoAudit1);
                mhnSkim = strService.saveSkim(mhnSkim);
//                permohonanSkimDAO.saveOrUpdate(mhnSkim);
            }

            msg = "Maklumat telah berjaya disimpan.";
        }

        return new RedirectResolution(BadanPengurusanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution updateMaklumatBadan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
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
        if (mc == null && permohonan.getPermohonanSebelum() != null) {
            idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
        }
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
                mc.setPengurusanTarikhRujukan(new Date());
                mc.setPengurusanTarikhSijil(new Date());
                mc.setPengurusanNoRujukan(pengurusanNoRujukan);
            } else {
                pihak = new Pihak();
                pihak = setValuePihak(pihak);
                pihak.setInfoAudit(infoAudit);

            }
        } else {
            mc = new BadanPengurusan();
            mc.setInfoAudit(infoAudit);
            mc.setPengurusanTarikhRujukan(new Date());
            mc.setPengurusanTarikhSijil(new Date());
            mc.setPengurusanNoRujukan(pengurusanNoRujukan);
            pihak = new Pihak();
            pihak = setValuePihak(pihak);
            pihak.setInfoAudit(infoAudit);
        }
        LOG.info("ss" + pihak.getAlamat1());
        pihak = strService.savePihak(pihak);
        mc.setPihak(pihak);
        mc.setPermohonan(permohonan);
        mc.setCawangan(permohonan.getCawangan());
        mc.setInfoAudit(infoAudit);
        mc.setPengurusanTarikhRujukan(new Date());
        mc.setPengurusanTarikhSijil(new Date());
        mc.setPengurusanNoRujukan(pengurusanNoRujukan);
        strService.updateMaklumatBangunan(mc);
        msg = "Maklumat telah berjaya dikemaskini.";
        return new RedirectResolution(BadanPengurusanActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_bangunan.jsp").addParameter("tab", "true");
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
        return new JSP("strata/pbbm/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    public Resolution badanPengurusan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (!permohonan.getSenaraiHakmilik().isEmpty()) {

            if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null) {
                LOG.info("id hakmilik induk : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
                HakmilikPihakBerkepentingan hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk(), "PM");
                LOG.info("ss : " + hpk.getPihak().getNama());
                getValuePihak(hpk.getPihak());
            } else {
                HakmilikPihakBerkepentingan hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PA");
                LOG.info("ss : " + hpk.getPihak().getNama());
                getValuePihak(hpk.getPihak());

            }

        }
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        return new JSP("strata/common/badanPengurusan.jsp").addParameter("tab", "true");
    }

    public Resolution badanPengurusanReadOnly() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("== id permohonan : " + idPermohonan + " ==");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (!permohonan.getSenaraiHakmilik().isEmpty()) {
            if (conf.getProperty("kodNegeri").equals("05")) {
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk() != null) {
                    LOG.info("id hakmilik induk : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk());
                    HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
                    hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk(), "PA");
                    if (hpk != null) {
                        LOG.info("----hpk.getPihak().getNama()----:" + hpk.getPihak().getNama());
                        getValuePihak(hpk.getPihak());
                    } else {
                        hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilikInduk(), "PM");
                        LOG.info("----hpk.getPihak().getNama()----:" + hpk.getPihak().getNama());
                        getValuePihak(hpk.getPihak());
                    }
                } else {
                    HakmilikPihakBerkepentingan hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PA");
                    LOG.info("ss : " + hpk.getPihak().getNama());
                    getValuePihak(hpk.getPihak());

                }
            }

            if (conf.getProperty("kodNegeri").equals("04")) {
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik() != null) {
                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBadanPengurusan() != null) {
                        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBadanPengurusan().getPihak() != null) {
                            getValuePihak(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBadanPengurusan().getPihak());
                        }
                    }
                }
            }
        }
        readOnly = true;
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        return new JSP("strata/common/badanPengurusan.jsp").addParameter("tab", "true");
    }

    public Resolution badanPengurusanMaster() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info("== id permohonan : " + idPermohonan + " ==");
        if (!permohonan.getSenaraiHakmilik().isEmpty()) {
            LOG.info("id hakmilik : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            HakmilikPihakBerkepentingan hpk = strService.findPihakByIdHakmilikNKod(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik(), "PA");
            if (hpk != null) {
                LOG.info("ss : " + hpk.getPihak().getNama());
                getValuePihak(hpk.getPihak());
            } else {
                LOG.error("Error : tiada hakmilik pihak");
            }
        }
        readOnly = true;
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        return new JSP("strata/common/badanPengurusan.jsp").addParameter("tab", "true");
    }

    public Pihak setValuePihak(Pihak pihak) {

        pihak.setNama(pengurusanNama);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat2(pengurusanAlamat2);
        pihak.setAlamat3(pengurusanAlamat3);
        pihak.setAlamat4(pengurusanAlamat4);
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

        pihak.setNoTelefon1(pengurusanTelefon);
        pihak.setNoTelefon2(pengurusanTelefon2);
        return pihak;
    }

    public Pihak setValuePihak2(Pihak pihak) {
        Pihak phk = new Pihak();
        pihak.setNama(pengurusanNama);
        pihak.setAlamat1(pengurusanAlamat1);
        pihak.setAlamat2(pengurusanAlamat2);
        pihak.setAlamat3(pengurusanAlamat3);
        pihak.setAlamat4(pengurusanAlamat4);
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

        pihak.setNoTelefon1(pengurusanTelefon);
        pihak.setNoTelefon2(pengurusanTelefon2);
        return pihak;
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

    public Resolution simpan() {
        Pihak pihak = new Pihak();
        if (StringUtils.isNotBlank(idPihak)) {
            pihak = pihakDAO.findById(Long.valueOf(idPihak));
            pihak = setValuePihak(pihak);
            strService.savePihak(pihak);
        }
        addSimpleMessage("Maklumat berjaya dikemaskini");
        return new JSP("strata/common/badanPengurusan.jsp").addParameter("tab", "true");
    }

    public Resolution resetForm() {
        pengurusanNama = "";
        pengurusanAlamat1 = "";
        pengurusanAlamat2 = "";
        pengurusanAlamat3 = "";
        pengurusanAlamat4 = "";
        pengurusanPoskod = "";
        pengurusanKodNegeri = "";

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    public String getPengurusanTelefon2() {
        return pengurusanTelefon2;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public void setPengurusanTelefon2(String pengurusanTelefon2) {
        this.pengurusanTelefon2 = pengurusanTelefon2;
    }

    public String getPengurusanTelefon() {
        return pengurusanTelefon;
    }

    public void setPengurusanTelefon(String pengurusanTelefon) {
        this.pengurusanTelefon = pengurusanTelefon;
    }

    public BadanPengurusan getMc() {
        return mc;
    }

    public void setMc(BadanPengurusan mc) {
        this.mc = mc;
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getPengurusanPoskod() {
        return pengurusanPoskod;
    }

    public void setPengurusanPoskod(String pengurusanPoskod) {
        this.pengurusanPoskod = pengurusanPoskod;
    }

    public String getPengurusanNoRujukan() {
        return pengurusanNoRujukan;
    }

    public void setPengurusanNoRujukan(String pengurusanNoRujukan) {
        this.pengurusanNoRujukan = pengurusanNoRujukan;
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

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getPengurusanNamaNegeri() {
        return pengurusanNamaNegeri;
    }

    public void setPengurusanNamaNegeri(String pengurusanNamaNegeri) {
        this.pengurusanNamaNegeri = pengurusanNamaNegeri;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PermohonanBangunan> getpBangunanL() {
        return pBangunanL;
    }

    public void setpBangunanL(List<PermohonanBangunan> pBangunanL) {
        this.pBangunanL = pBangunanL;
    }
}
