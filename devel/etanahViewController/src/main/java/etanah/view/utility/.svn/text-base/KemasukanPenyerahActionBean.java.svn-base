/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganJabatanDAO;
import etanah.dao.KodKementerianDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PeguamDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.BadanBerkanun;
import etanah.model.JuruRancangBerlesen;
import etanah.model.PerbadananPengurusan;
import etanah.model.JabatanKerajaan;
import etanah.model.JUBL;
import etanah.model.JuruLelong;
import etanah.model.KodAgensi;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.Peguam;
import etanah.model.Pengguna;
import etanah.model.Penyerah;
import etanah.model.Pihak;
import etanah.service.KodService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import etanah.view.kaunter.RequestAgensiInfo;
import etanah.view.kaunter.RequestBdnKanunInfo;
import etanah.view.kaunter.RequestBdnUrusInfo;
import etanah.view.kaunter.RequestJLBInfo;
import etanah.view.kaunter.RequestJUBLInfo;
import etanah.view.kaunter.RequestJbtnKerajaanInfo;
import etanah.view.kaunter.RequestJuRncgInfo;
import etanah.view.kaunter.RequestPeguamInfo;
import etanah.view.kaunter.RequestPenyerahInfoForm;
import etanah.view.kaunter.RequestUnitDalamInfo;
import etanah.view.kaunter.RequestUnitDalamanInfo;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author afham
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/kemasukanPenyerah")
public class KemasukanPenyerahActionBean extends AbleActionBean {

    private List<Penyerah> senaraiPenyerah;
    private List<Pihak> senaraiPihak;
    private String jenisPenyerah;
    private String namaPenyerah;
    @Inject
    private RequestPeguamInfo pi;
    @Inject
    private RequestJUBLInfo ji;
    @Inject
    private RequestJLBInfo jlb;
    @Inject
    private RequestUnitDalamInfo ud;
    @Inject
    private RequestAgensiInfo ai;
    @Inject
    private RequestJuRncgInfo juRncg;
    @Inject
    private RequestBdnUrusInfo bdnUrus;
    @Inject
    private RequestJbtnKerajaanInfo jk;
    @Inject
    private RequestBdnKanunInfo bk;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PihakService pihakService;
    @Inject
    KodCawanganJabatanDAO kodCawanganJabatanDAO;
    @Inject
    KodKementerianDAO kodKementerianDAO;
    @Inject
    KodService service;
    private String idPenyerah;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNegeri;
    private String penyerahNoPengenalan;
    private String penyerahJenisPengenalan;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    private String penyerahStatus;
    private String kodPenyerah;
    private String kodKementerian;
    private String jenisPengenalanPenyerah;
    private String noPengenalanPenyerah;
    private String temp;
    Logger LOG = Logger.getLogger(KemasukanPenyerahActionBean.class);
    private boolean form = false;
    private boolean pihakOnly = false;
    private boolean tambah = true;
    private Pengguna pengguna;
    private String kod;
    private boolean melaka = false;
    private int id_rekod_agensi;

    @DefaultHandler
    public Resolution showForm() {
        idPenyerah = "";
        findKodNegeri();
        return new JSP("utiliti/penyerah/kemasukan_penyerah.jsp");
    }

    public Resolution showFormEditPenyerah() {
        findKodNegeri();
        return new JSP("utiliti/penyerah/kemasukan_penyerah.jsp");
    }

    public Resolution searchPenyerahForEdit() {
        findKodNegeri();
        findPenyerah();
        return showFormEditPenyerah();
    }

    public Resolution searchPenyerah() {
        LOG.info("::start cari penyerah::");
        findKodNegeri();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        findPenyerah();
        return showForm();
    }

    public void findPenyerah() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(jenisPenyerah)) {
            LOG.info("--- kod penyerah " + jenisPenyerah);
            findKodNegeri();

            if (melaka = false) {
                if (jenisPenyerah.equals("01")) { //Peguam
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = pi.findAll();
                    } else {
                        senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("02")) { //Jurukur Berlesen
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = ji.findAll();
                    } else {
                        senaraiPenyerah = ji.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("04")) { //JuruLelong
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = jlb.findAll();
                    } else {
                        senaraiPenyerah = jlb.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("00")) { //Unit Dalaman
                    etanahActionBeanContext ctx = new etanahActionBeanContext();
                    ctx = (etanahActionBeanContext) getContext();
                    ud.setCawangan(ctx.getKodCawangan());

                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = ud.findAll();
                    } else {
                        //add by azri 9/7/2013 : get all PTG and PTD 'Unit Dalaman' if PTG    
                        if ("05".equals(conf.getProperty("kodNegeri")) && "00".equals(pguna.getKodCawangan().getKod())) {
                            //senaraiPenyerah = ud.findPenyerahByParamAllKodCaw(getContext().getRequest().getParameterMap());
                            senaraiPenyerah = ud.findAll();
                        } else {
                            senaraiPenyerah = ud.findPenyerahByParam(getContext().getRequest().getParameterMap());
                        }
                    }
                } else if (jenisPenyerah.equals("08")) {
                    if (StringUtils.isNotBlank(jenisPengenalanPenyerah)) {
                        senaraiPihak = findPihakByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = juRncg.findAll();
                    } else {
                        senaraiPenyerah = juRncg.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("05")) { //Perbadanan Pengurusan
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = bdnUrus.findAll();
                    } else {
                        senaraiPenyerah = bdnUrus.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("06")) { //Jabatan Kerajaan
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = jk.findAll();
                    } else {
                        senaraiPenyerah = jk.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("07")) { //Badan Berkanun
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = bk.findAll();
                    } else {
                        senaraiPenyerah = bk.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                }
            } else {
                if (jenisPenyerah.equals("01")) { //Peguam
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = pi.findAll();
                    } else {
                        senaraiPenyerah = pi.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("02")) { //Jurukur Berlesen
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = ji.findAll();
                    } else {
                        senaraiPenyerah = ji.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("04")) { //JuruLelong
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = jlb.findAll();
                    } else {
                        senaraiPenyerah = jlb.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("00")) { //Unit Dalaman
                    etanahActionBeanContext ctx = new etanahActionBeanContext();
                    ctx = (etanahActionBeanContext) getContext();
                    ud.setCawangan(ctx.getKodCawangan());

                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = ud.findAll();
                    } else {
                        //add by azri 9/7/2013 : get all PTG and PTD 'Unit Dalaman' if PTG    
                        if ("05".equals(conf.getProperty("kodNegeri")) && "00".equals(pguna.getKodCawangan().getKod())) {
                            //senaraiPenyerah = ud.findPenyerahByParamAllKodCaw(getContext().getRequest().getParameterMap());
                            senaraiPenyerah = ud.findAll();
                        } else {
                            senaraiPenyerah = ud.findPenyerahByParam(getContext().getRequest().getParameterMap());
                        }
                    }
                } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = juRncg.findAll();
                    } else {
                        senaraiPenyerah = juRncg.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("07")) { //Perbadanan Pengurusan
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = bdnUrus.findAll();
                    } else {
                        senaraiPenyerah = bdnUrus.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("09")) { //Jabatan Kerajaan
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = jk.findAll();
                    } else {
                        senaraiPenyerah = jk.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("10")) { //Badan Berkanun
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = bk.findAll();
                    } else {
                        senaraiPenyerah = bk.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                } else if (jenisPenyerah.equals("06")) { // IND/ SYKT
                    if (StringUtils.isBlank(namaPenyerah) && StringUtils.isBlank(idPenyerah)) {
                        senaraiPenyerah = ai.findAll();
                    } else {
                        senaraiPenyerah = ai.findPenyerahByParam(getContext().getRequest().getParameterMap());
                    }
                }
            }
        }
        if (senaraiPenyerah != null) {
            if (senaraiPenyerah.isEmpty()) {
                form = true;
            }
            pihakOnly = false;
            tambah = false;
        }
        if (senaraiPihak != null) {
            if (senaraiPihak.isEmpty()) {
                form = true;
            }
            pihakOnly = true;
            tambah = false;
        }
    }

    public Resolution tambah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        findKodNegeri();
        if (melaka = false) {
            if (jenisPenyerah.equals("01")) {
                Peguam penyerah = new Peguam();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setAktif('Y');
                penyerah.setInfoAudit(ia);
                penyerah.setKodSumber("MI");
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                //penyerah.setNegeri(kodNegeriDAO.findById(penyerahNegeri));
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                pi.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("02")) {

                JUBL penyerah = new JUBL();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                ji.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("04")) {

                JuruLelong penyerah = new JuruLelong();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                jlb.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("08")) {
                Pihak p = pihakService.findPihak(penyerahJenisPengenalan, penyerahNoPengenalan);
                if (p == null) {
                    Pihak penyerah = new Pihak();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new Date());
                    LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                    penyerah.setAlamat1(penyerahAlamat1);
                    penyerah.setAlamat2(penyerahAlamat2);
                    penyerah.setAlamat3(penyerahAlamat3);
                    penyerah.setAlamat4(penyerahAlamat4);
                    penyerah.setPoskod(penyerahPoskod);
                    penyerah.setNoTelefon1(penyerahNoTelefon);
                    penyerah.setEmail(penyerahEmail);
                    penyerah.setNama(penyerahNama);
                    penyerah.setNoPengenalan(penyerahNoPengenalan);
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                    penyerah.setInfoAudit(ia);
                    KodNegeri kodNeg = new KodNegeri();
                    kodNeg.setKod(penyerahNegeri);
                    penyerah.setNegeri(kodNeg);
                    pihakService.saveOrUpdatePihak(penyerah);
                    addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");
                } else {
                    addSimpleMessage("Data telah Wujud");
                }
                jenisPengenalanPenyerah = penyerahJenisPengenalan;
                noPengenalanPenyerah = penyerahNoPengenalan;

            } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen

                JuruRancangBerlesen penyerah = new JuruRancangBerlesen();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                juRncg.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("05")) { //Perbadanan Pengurusan

                PerbadananPengurusan penyerah = new PerbadananPengurusan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                bdnUrus.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("06")) { //Jabatan Kerajaan

                JabatanKerajaan penyerah = new JabatanKerajaan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                jk.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("07")) { //Badan Berkanun

                BadanBerkanun penyerah = new BadanBerkanun();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                bk.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");
            }
        } else {
            if (jenisPenyerah.equals("01")) {
                Peguam penyerah = new Peguam();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setAktif('Y');
                penyerah.setInfoAudit(ia);
                penyerah.setKodSumber("MI");
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                //penyerah.setNegeri(kodNegeriDAO.findById(penyerahNegeri));
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                pi.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("02")) {

                JUBL penyerah = new JUBL();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                ji.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("04")) {

                JuruLelong penyerah = new JuruLelong();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                jlb.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen

                JuruRancangBerlesen penyerah = new JuruRancangBerlesen();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                juRncg.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("07")) { //Perbadanan Pengurusan

                PerbadananPengurusan penyerah = new PerbadananPengurusan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                bdnUrus.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("09")) { //Jabatan Kerajaan

                JabatanKerajaan penyerah = new JabatanKerajaan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                jk.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("10")) { //Badan Berkanun

                BadanBerkanun penyerah = new BadanBerkanun();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setCawangan(pengguna.getKodCawangan());
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                penyerah.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setNegeri(kodNeg);
                if (!penyerahJenisPengenalan.equals("0")) {
                    KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                    penyerah.setJenisPengenalan(kjp);
                }
                bk.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");

            } else if (jenisPenyerah.equals("06")) { // IDV/SYRKT

                id_rekod_agensi = service.countKodAgensi();
                id_rekod_agensi++;
                int kod = id_rekod_agensi + 10000;
                // +10000 bcoz value kod in kod_agensi is too much crazy and not sync =.='
                
                String id_rek_agensi = String.valueOf(kod);
                LOG.debug("id_rekod_agensi : " + id_rek_agensi);
                
                KodAgensi penyerah = new KodAgensi();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
                penyerah.setKod(id_rek_agensi);
                LOG.debug("penyerahAlamat1 : " + penyerahAlamat1);
                penyerah.setAlamat1(penyerahAlamat1);
                penyerah.setAlamat2(penyerahAlamat2);
                penyerah.setAlamat3(penyerahAlamat3);
                penyerah.setAlamat4(penyerahAlamat4);
                penyerah.setPoskod(penyerahPoskod);
                penyerah.setNoTelefon1(penyerahNoTelefon);
                penyerah.setEmel(penyerahEmail);
                penyerah.setNama(penyerahNama);
                penyerah.setInfoAudit(ia);
                penyerah.setAktif('Y');
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                penyerah.setKodNegeri(kodNeg);
                ai.updateOrSave(penyerah);
                idPenyerah = "" + penyerah.getIdPenyerah();
                addSimpleMessage("Penambahan Data Berjaya. Sila Isikan Nama Diruangan Nama Penyerah Untuk Lakukan Carian.");
            }
        }

        //findPenyerah(); 
        return showForm();
    }

    public Resolution update() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.debug("jenisPenyerah = " + jenisPenyerah);
        findKodNegeri();
        if (melaka = false) {
            if (jenisPenyerah.equals("01")) {
                Peguam p = pi.findById(Long.parseLong(idPenyerah));
                InfoAudit ia = p.getInfoAudit();
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                ia.setDiKemaskiniOleh(null);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                p.setInfoAudit(ia);
                pi.updateOrSave(p);
                LOG.debug("--------ID PENYERAH---- : " + idPenyerah);
                LOG.debug("===========Save==============");

            } else if (jenisPenyerah.equals("02")) {
                JUBL p = ji.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);;
                p.setInfoAudit(ia);
                ji.updateOrSave(p);

            } else if (jenisPenyerah.equals("04")) {
                JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                p.setInfoAudit(ia);
                jlb.updateOrSave(p);

            } else if (jenisPenyerah.equals("00")) {
                //KodCawanganJabatan p = ud.findById(Long.parseLong(idPenyerah));            
                KodCawanganJabatan p = ud.findKod(idPenyerah);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                ud.updateOrSave(p);

            } else if (jenisPenyerah.equals("08")) {
                Pihak p = pihakDAO.findById(Long.parseLong(idPenyerah));
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmail(penyerahEmail);
                p.setNama(penyerahNama);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                pihakDAO.saveOrUpdate(p);

            } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen
                JuruRancangBerlesen p = juRncg.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                juRncg.updateOrSave(p);

            } else if (jenisPenyerah.equals("05")) { //Perbadanan Pengurusan
                PerbadananPengurusan p = bdnUrus.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                bdnUrus.updateOrSave(p);

            } else if (jenisPenyerah.equals("06")) { //Jabatan Kerajaan
                JabatanKerajaan p = jk.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                jk.updateOrSave(p);

            } else if (jenisPenyerah.equals("07")) { //Badan Berkanun
                BadanBerkanun p = bk.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                bk.updateOrSave(p);

            }
        } else {
            if (jenisPenyerah.equals("01")) {
                Peguam p = pi.findById(Long.parseLong(idPenyerah));
                InfoAudit ia = p.getInfoAudit();
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                ia.setDiKemaskiniOleh(null);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                p.setInfoAudit(ia);
                pi.updateOrSave(p);
                LOG.debug("--------ID PENYERAH---- : " + idPenyerah);
                LOG.debug("===========Save==============");

            } else if (jenisPenyerah.equals("02")) {
                JUBL p = ji.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);;
                p.setInfoAudit(ia);
                ji.updateOrSave(p);

            } else if (jenisPenyerah.equals("04")) {
                JuruLelong p = jlb.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                p.setInfoAudit(ia);
                jlb.updateOrSave(p);

            } else if (jenisPenyerah.equals("00")) {
                //KodCawanganJabatan p = ud.findById(Long.parseLong(idPenyerah));            
                KodCawanganJabatan p = ud.findKod(idPenyerah);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                ud.updateOrSave(p);

            } else if (jenisPenyerah.equals("03")) { //Jururancang Berlesen
                JuruRancangBerlesen p = juRncg.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                juRncg.updateOrSave(p);

            } else if (jenisPenyerah.equals("07")) { //Perbadanan Pengurusan
                PerbadananPengurusan p = bdnUrus.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                bdnUrus.updateOrSave(p);

            } else if (jenisPenyerah.equals("09")) { //Jabatan Kerajaan
                JabatanKerajaan p = jk.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                jk.updateOrSave(p);

            } else if (jenisPenyerah.equals("10")) { //Badan Berkanun
                BadanBerkanun p = bk.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                p.setNoPengenalan(penyerahNoPengenalan);
                p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setNegeri(kodNeg);
                p.setInfoAudit(ia);
                bk.updateOrSave(p);

            } else if (jenisPenyerah.equals("06")) { // IND/SYKT
                KodAgensi p = ai.findById(Long.parseLong(idPenyerah));
                KodJenisPengenalan kjp = new KodJenisPengenalan(penyerahJenisPengenalan);
                InfoAudit ia = p.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
                p.setKodKementerian(Integer.parseInt("35"));
                p.setAlamat1(penyerahAlamat1);
                p.setAlamat2(penyerahAlamat2);
                p.setAlamat3(penyerahAlamat3);
                p.setAlamat4(penyerahAlamat4);
                p.setPoskod(penyerahPoskod);
                p.setNoTelefon1(penyerahNoTelefon);
                p.setEmel(penyerahEmail);
                p.setNama(penyerahNama);
                //p.setNoPengenalan(penyerahNoPengenalan);
                //p.setJenisPengenalan(kjp);
                p.setAktif(penyerahStatus.charAt(0));
                KodNegeri kodNeg = new KodNegeri();
                kodNeg.setKod(penyerahNegeri);
                p.setKodNegeri(kodNeg);
                p.setInfoAudit(ia);
                ai.updateOrSave(p);
            }
        }


        addSimpleMessage(
                "Kemaskini Data Berjaya");
        getContext()
                .getRequest().setAttribute("edit", "true");
        return searchPenyerahForEdit();
    }

    public List<Pihak> findPihakByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.Pihak p where 1=1");

        if (etanah.util.StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and upper(p.nama) like :nama");
        }
        if (etanah.util.StringUtils.isNotBlank(map.get("jenisPengenalanPenyerah"))) {
            query.append(" and p.jenisPengenalan.kod like :jenis");
        }
        if (etanah.util.StringUtils.isNotBlank(map.get("noPengenalanPenyerah"))) {
            query.append(" and p.noPengenalan like :noPengenalan");
        }

        Query q = sessionProvider.get().createQuery(query.toString());

        if (etanah.util.StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
        }
        if (etanah.util.StringUtils.isNotBlank(map.get("jenisPengenalanPenyerah"))) {
            q.setString("jenis", map.get("jenisPengenalanPenyerah")[0] + "%");
        }
        if (etanah.util.StringUtils.isNotBlank(map.get("noPengenalanPenyerah"))) {
            q.setString("noPengenalan", map.get("noPengenalanPenyerah")[0] + "%");
        }

        return q.list();
    }

    public Resolution doKemaskiniPihak() {
        String idPihak = (String) getContext().getRequest().getParameter("idPihak");
        String DELIM = "__^$__";
        List l = sessionProvider.get().createQuery(
                "select p from Pihak p left join fetch p.negeri n "
                + "where "
                + "p.idPihak = :idPihak").setLong("idPihak", Long.valueOf(idPihak))
                .list();
        String results = "";

        if (l.size() > 0) {
            if (l.size() > 1) {
                LOG.warn("There are more than 1 result");
            }
            Pihak p = (Pihak) l.get(0);
            LOG.debug("Pihak found! idPihak=" + p.getIdPihak());
            StringBuilder s = new StringBuilder();
            s.append(p.getJenisPengenalan() != null
                    ? p.getJenisPengenalan().getKod() : "").append(DELIM)
                    .append(p.getNoPengenalan() != null ? p.getNoPengenalan() : "").append(DELIM)
                    .append(p.getNama() != null ? p.getNama() : "").append(DELIM)
                    .append(p.getAlamat1() != null ? p.getAlamat1() : "").append(DELIM)
                    .append(p.getAlamat2() != null ? p.getAlamat2() : "").append(DELIM)
                    .append(p.getAlamat3() != null ? p.getAlamat3() : "").append(DELIM)
                    .append(p.getAlamat4() != null ? p.getAlamat4() : "").append(DELIM)
                    .append(p.getPoskod() != null ? p.getPoskod() : "").append(DELIM)
                    .append(p.getNegeri() != null ? p.getNegeri().getKod() : "").append(DELIM)
                    .append(p.getNoTelefon1() != null ? p.getNoTelefon1() : "").append(DELIM)
                    .append(p.getEmail() != null ? p.getEmail() : "");
            results = s.toString();
            idPenyerah = idPihak;
            LOG.debug(s);
        } else {
            LOG.debug("Pihak not found");
        }

        return new StreamingResolution("text/plain", results);
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public String getKodPenyerah() {
        return kodPenyerah;
    }

    public void setKodPenyerah(String kodPenyerah) {
        this.kodPenyerah = kodPenyerah;
    }

    public List<Penyerah> getSenaraiPenyerah() {
        return senaraiPenyerah;
    }

    public void setSenaraiPenyerah(List<Penyerah> senaraiPenyerah) {
        this.senaraiPenyerah = senaraiPenyerah;
    }

    public String getJenisPenyerah() {
        return jenisPenyerah;
    }

    public void setJenisPenyerah(String jenisPenyerah) {
        this.jenisPenyerah = jenisPenyerah;
    }

    public String getNamaPenyerah() {
        return namaPenyerah;
    }

    public void setNamaPenyerah(String namaPenyerah) {
        this.namaPenyerah = namaPenyerah;
    }

    public String getIdPenyerah() {
        return idPenyerah;
    }

    public void setIdPenyerah(String idPenyerah) {
        this.idPenyerah = idPenyerah;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(String penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public Pengguna getPengguna() {

        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;

    }

    public String getPenyerahStatus() {
        return penyerahStatus;
    }

    public void setPenyerahStatus(String penyerahStatus) {
        this.penyerahStatus = penyerahStatus;
    }

    public String getJenisPengenalanPenyerah() {
        return jenisPengenalanPenyerah;
    }

    public void setJenisPengenalanPenyerah(String jenisPengenalanPenyerah) {
        this.jenisPengenalanPenyerah = jenisPengenalanPenyerah;
    }

    public String getNoPengenalanPenyerah() {
        return noPengenalanPenyerah;
    }

    public void setNoPengenalanPenyerah(String noPengenalanPenyerah) {
        this.noPengenalanPenyerah = noPengenalanPenyerah;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
    }

    public boolean isPihakOnly() {
        return pihakOnly;
    }

    public void setPihakOnly(boolean pihakOnly) {
        this.pihakOnly = pihakOnly;
    }

    public boolean isTambah() {
        return tambah;
    }

    public void setTambah(boolean tambah) {
        this.tambah = tambah;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public boolean isMelaka() {
        return melaka;
    }

    public void setMelaka(boolean melaka) {
        this.melaka = melaka;
    }

    public int getId_rekod_agensi() {
        return id_rekod_agensi;
    }

    public void setId_rekod_agensi(int id_rekod_agensi) {
        this.id_rekod_agensi = id_rekod_agensi;
    }

    public void findKodNegeri() {
        if (conf.getProperty("kodNegeri").equals("04")) {
            melaka = true;
        } else {
            melaka = false;
        }
    }
}
