/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import etanah.view.stripes.*;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBangsa;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodNegeri;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakCawangan;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahContextListener;
import org.hibernate.Transaction;

/**
 *
 * @author Sreenivasa Reddy Munagala.
 */
@UrlBinding("/strata/rthsmaklumat_Pemohon")
public class RTHSMaklumatPemohonActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    StrataPtService strService;
    @Inject
    PembangunanService devService;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pihak pihak1;
    private Pemohon pemohon;
    boolean tambahCawFlag;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private List<PihakCawangan> cawanganList;
    private Pengguna pguna;
    private List<KodBangsa> senaraiKodBangsa;
    private List<PermohonanPihak> mohonPihakList;
    private static final Logger LOG = Logger.getLogger(RTHSMaklumatPemohonActionBean.class);
    private Permohonan p;
    private String suratNegeri;
    private String idPermohonanTerdahulu;
    private HakmilikPermohonan hakmilikpermohanan;
    private String kod;
    private String idHakmilikStrata;

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");

        if (getContext().getRequest().getSession().getAttribute("error") != null) {
            addSimpleError("Sila masukkan ID Permohonan terdahulu");
        }

        if (getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu") != null) {
            idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
            permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
            if (permohonan == null) {
                addSimpleError("Id Permohonan ini tiada di dalam pangkalan data");
            }
        }

        if (getContext().getRequest().getSession().getAttribute("idHakmilikPermohonan") != null) {
            String idPermohonan1 = (String) getContext().getRequest().getSession().getAttribute("idHakmilikPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan1);
        }
        /* code for IdHakmilik Search -- End -- */

        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }

        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
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
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("ID PERMOHONAN : " + idPermohonan);
        Permohonan permohonanBaru = new Permohonan();
        permohonanBaru = permohonanDAO.findById(idPermohonan);
        LOG.info("PERMOHONAN SBLM: " + permohonanBaru.getPermohonanSebelum().getIdPermohonan());
        this.permohonan = permohonanDAO.findById(permohonanBaru.getPermohonanSebelum().getIdPermohonan());
        if (this.permohonan != null) {
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};
            List<Pemohon> listPemohon;
            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
            if (!(listPemohon.isEmpty())) {
                pemohon = listPemohon.get(0);
                pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                LOG.info("PIHAK : " + pihak.getNama());
                if (pihak.getJenisPengenalan() != null) {
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                }
            }
        }
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonanTerdahulu = "";
        stageId = "keputusan";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonanB = permohonanDAO.findById(idPermohonan);
        if (permohonanB.getPermohonanSebelum() != null) {
            idPermohonanTerdahulu = permohonanB.getPermohonanSebelum().getIdPermohonan();
        } else {
            idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");
        }
        FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonanTerdahulu, stageId);
        if (idPermohonanTerdahulu != null && fp == null) {
            permohonan = permohonanDAO.findById(idPermohonanTerdahulu);
            if (permohonan != null) {
                String[] tname = {"permohonan"};
                Object[] model = {permohonan};
                List<Pemohon> listPemohon;
                listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
                if (!(listPemohon.isEmpty())) {
                    pemohon = listPemohon.get(0);
                    pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                    if (pihak.getJenisPengenalan() != null) {
                        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    }
                }
            }
        } else {
            pihak = null;
        }
    }

    public Resolution pemohonPopup() {

        getContext().getRequest().setAttribute("pemohon", Boolean.TRUE);
        getContext().getRequest().setAttribute("update", Boolean.TRUE);
        return new JSP("strata/pbbm/kemasukan_maklumatpemohon.jsp").addParameter("popup", "true");
    }

    public Resolution editpemohonPopup() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        kod = "";
        if (pihak.getSuratNegeri().getKod() != null) {
            kod = pihak.getSuratNegeri().getKod();
        }
        return new JSP("strata/Lanjutan_Tempo_Mohon/edit_pemohon.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihak() {
        String tmp = getContext().getRequest().getParameter("tuanTanah");
        if (StringUtils.isNotBlank(tmp)) {
            getContext().getRequest().setAttribute("tuanTanah", Boolean.TRUE);
        }
        String jenisPihak = getContext().getRequest().getParameter("jenisPihak");
        if (StringUtils.isNotBlank(jenisPihak)) {
            KodJenisPihakBerkepentingan kod = kodJenisPihakBerkepentinganDAO.findById(jenisPihak);
            if (permohonanPihak == null) {
                permohonanPihak = new PermohonanPihak();
            }
            permohonanPihak.setJenis(kod);
            getContext().getRequest().setAttribute("jenis", jenisPihak);
        }
        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null) {
                Pihak pihakSearch = new Pihak();
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                boolean duplicateFlag = false;
                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        getContext().getRequest().setAttribute("update", Boolean.TRUE);
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");

                        cariFlag = true;
                        tiadaDataFlag = true;
                    }
                    if (pihak.getJenisPengenalan() != null) {
                        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                    }


                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else {

                if (pihak.getJenisPengenalan() == null) {
                    addSimpleError("Sila Pilih Jenis Pengenalan.");
                    getContext().getRequest().setAttribute("update", Boolean.TRUE);
                } else if (pihak.getNoPengenalan() == null) {
                    addSimpleError("Sila Masukkan  Nombor Pengenalan.");
                    getContext().getRequest().setAttribute("update", Boolean.TRUE);
                }
            }
        } else {

            getContext().getRequest().setAttribute("update", Boolean.TRUE);
            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan.");
        }

        return new JSP("strata/pbbm/kemasukan_maklumatpemohon.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPemohonPopup() {

        Permohonan permohonan = null;

        idPermohonanTerdahulu = (String) getContext().getRequest().getSession().getAttribute("idPermohonanTerdahulu");

        if (idPermohonanTerdahulu != null) {
            permohonan = permohonanService.findById(idPermohonanTerdahulu);
        }

        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihak.getBangsa() != null) {
            if (pihak.getBangsa().getKod().toString().length() > 4) {
                pihak.setBangsa(null);
            }
        }
        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

        }

        pihakTemp.setInfoAudit(info);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihakTemp);

        pmohon.setInfoAudit(info);
        if (pmohon != null) {
            pmohon.setCawangan(permohonan.getCawangan());

            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);
            rehydrate();
            getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
            getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
        }
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution updatePihak() {
        System.out.println("--------updatePihak-------");

        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pg);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        infoAudit.setTarikhMasuk(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());

        System.out.println("-------Changed--------" + kod);

        KodNegeri kn = kodNegeriDAO.findById(kod);

        System.out.println("-------Old--------" + pihakTemp.getSuratNegeri().getKod());
        System.out.println("-------Old--------" + pihakTemp.getSuratNegeri().getNama());


        System.out.println("-------kn--------" + kn.getKod());
        System.out.println("-------kn--------" + kn.getNama());
        pihakTemp.setSuratNegeri(kn);
        // pihakTemp.getSuratNegeri().setKod(pihak.getSuratNegeri().getKod());

        System.out.println("-------New--------" + pihakTemp.getSuratNegeri().getKod());
        System.out.println("-------New--------" + pihakTemp.getSuratNegeri().getNama());


        pihakTemp.setInfoAudit(infoAudit);

        pihakService.saveOrUpdate(pihakTemp);

        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution IsiSemula() {
        context.getRequest().getSession().removeAttribute("idPermohonanTerdahulu");
        idPermohonanTerdahulu = "";
        rehydrate();
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit1", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/Lanjutan_Tempo_Mohon/rthsmaklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikpermohanan() {
        return hakmilikpermohanan;
    }

    public void setHakmilikpermohanan(HakmilikPermohonan hakmilikpermohanan) {
        this.hakmilikpermohanan = hakmilikpermohanan;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public String getSuratNegeri() {
        return suratNegeri;
    }

    public void setSuratNegeri(String suratNegeri) {
        this.suratNegeri = suratNegeri;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public List<PihakCawangan> getCawanganList() {
        return cawanganList;
    }

    public void setCawanganList(List<PihakCawangan> cawanganList) {
        this.cawanganList = cawanganList;
    }

    public PihakDAO getPihakDAO() {
        return pihakDAO;
    }

    public void setPihakDAO(PihakDAO pihakDAO) {
        this.pihakDAO = pihakDAO;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public boolean isTambahCawFlag() {
        return tambahCawFlag;
    }

    public void setTambahCawFlag(boolean tambahCawFlag) {
        this.tambahCawFlag = tambahCawFlag;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PemohonService getPemohonService() {
        return pemohonService;
    }

    public void setPemohonService(PemohonService pemohonService) {
        this.pemohonService = pemohonService;
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

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public Pihak getPihak1() {
        return pihak1;
    }

    public void setPihak1(Pihak pihak1) {
        this.pihak1 = pihak1;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getIdHakmilikStrata() {
        return idHakmilikStrata;
    }

    public void setIdHakmilikStrata(String idHakmilikStrata) {
        this.idHakmilikStrata = idHakmilikStrata;
    }
}
