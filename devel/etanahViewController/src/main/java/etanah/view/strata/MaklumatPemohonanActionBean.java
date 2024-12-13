    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
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
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Transaction;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/maklumat_pemohon")
public class MaklumatPemohonanActionBean extends BasicTabActionBean {

    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
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
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private Pihak pihak;
    private Pemohon pemohon;
    boolean tambahCawFlag;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    private List<PihakCawangan> cawanganList;
    private Pengguna pguna;
    private List<KodBangsa> senaraiKodBangsa;
    private List<PermohonanPihak> mohonPihakList;
    private static final Logger LOG = Logger.getLogger(MaklumatPemohonActionBean.class);
    private Permohonan p;
    private String suratNegeri;
    private String jenis;
    private String negeri;
    private String checkAlamat;

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
        return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
        //   return new JSP("strata/Ruang_Udara/maklumat_pemohon.jsp").addParameter("tab", "true");
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
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (!permohonan.getKodUrusan().getKod().equals("PNB")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonan = permohonan.getPermohonanSebelum();
                }
            }
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<Pemohon> listPemohon;

            listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);


            if (!(listPemohon.isEmpty())) {
                pemohon = listPemohon.get(0);
                pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                if (pihak.getJenisPengenalan() != null) {
                    jenis = pihak.getJenisPengenalan().getKod();
                }
                if (pihak.getNegeri() != null) {
                    negeri = pihak.getNegeri().getKod();
                }
                if (pihak.getSuratNegeri() != null) {
                    suratNegeri = pihak.getSuratNegeri().getKod();
                }
                if (pihak.getJenisPengenalan() != null) {
                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                }
            }

//            Pihak phk = strService.findPemohonAlamatSuratEqualAlamat(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
//            LOG.info("---- FIND Pihak Alamat = Alamat_Surat ----");
//            if (phk != null) {
//                LOG.info("=== ALAMAT 1 : " + phk.getAlamat1());
//                checkAlamat = "Yes";
//            } else {
//                LOG.info("=== ELSE : TIADA DATA DIJUMPAI BAGI ALAMAT = ALAMAT_SURAT");
//                checkAlamat = "No";
//            }

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
        if (pihak != null) {
            LOG.info("EDIT PIHAK. ID : " + pihak.getIdPihak());

            Pihak phk = strService.findPemohonAlamatSuratEqualAlamat(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
            LOG.info("---- CARI Pihak Alamat = Alamat_Surat ----");
            if (phk != null) {
                LOG.info("=== ALAMAT 1 : " + phk.getAlamat1());
                checkAlamat = "Yes";
            } else {
                LOG.info("=== ELSE : TIADA DATA DIJUMPAI BAGI ALAMAT = ALAMAT_SURAT");
                checkAlamat = "No";
            }
        }
        return new JSP("strata/pbbm/edit_pemohon.jsp").addParameter("popup", "true");
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
                Pihak pihakSearch = null;
                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;



                if (duplicateFlag == false) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cawanganList = pihak.getSenaraiCawangan();
                        getContext().getRequest().setAttribute("update", Boolean.TRUE);
                        cariFlag = true;
                        tiadaDataFlag = false;

                        Pihak phk = strService.findPemohonAlamatSuratEqualAlamat(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
                        LOG.info("---- CARI Pihak Alamat = Alamat_Surat ----");
                        if (phk != null) {
                            LOG.info("=== ALAMAT 1 : " + phk.getAlamat1());
                            checkAlamat = "Yes";
                        } else {
                            LOG.info("=== ELSE : TIADA DATA DIJUMPAI BAGI ALAMAT = ALAMAT_SURAT");
                            checkAlamat = "No";
                        }

                    } else {
//                        addSimpleMessage("Tiada Data. Sila Masukkan Maklumat Baru.");
                        addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");

                        cariFlag = true;
                        tiadaDataFlag = true;

                    }

//                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());


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
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info(idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
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

        } else {
            info = pihakTemp.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(pihak.getJenisPengenalan().getKod()));
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(kodNegeriDAO.findById(pihak.getSuratNegeri().getKod()));

        }
LOG.info("ID PIHAK "+pihakTemp.getIdPihak());
        pihakTemp.setInfoAudit(info);
//        pihakTemp = strService.savePihak(pihakTemp);
        Pemohon pmohon = new Pemohon();
        pmohon.setPermohonan(permohonan);
        pmohon.setPihak(pihak);

        pmohon.setInfoAudit(info);
        if (pmohon != null) {
            pmohon.setCawangan(permohonan.getCawangan());
            pmohon = strService.savePemohon(pmohon);
//            pemohonService.simpanPihakPemohon(pihakTemp, pmohon);

        }
        rehydrate();
        return new RedirectResolution(MaklumatPemohonanActionBean.class, "showForm");
    }

    public Resolution updatePihak() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            InfoAudit infoAudit = new InfoAudit();
            pihak = pihakDAO.findById(pihak.getIdPihak());
            KodNegeri kn = new KodNegeri();
            kn.setKod(suratNegeri);
            pihak.setSuratNegeri(kn);
            infoAudit = pihak.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            pihak.setInfoAudit(infoAudit);

            strService.updatePihak(pihak);


        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
        }
        tx.commit();
        return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPemohonPopup1() {

        Permohonan permohonan = null;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info(idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }
        System.out.println("id permohonan :" + permohonan.getIdPermohonan());
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        System.out.println("IdPihak:" + pihak.getIdPihak());
        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());


        if (pihakTemp == null) {
            pihakTemp = new Pihak();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
        } else {
            info = pihakTemp.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
        }
        System.out.println("Pihak Name:" + pihak.getNama());
        pihakTemp.setNama(pihak.getNama());
        pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById(jenis));
        pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
        pihakTemp.setKodJantina(pihak.getKodJantina());
        pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
        pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
        pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        pihakTemp.setAlamat1(pihak.getAlamat1());
        pihakTemp.setAlamat2(pihak.getAlamat2());
        pihakTemp.setAlamat3(pihak.getAlamat3());
        pihakTemp.setAlamat4(pihak.getAlamat4());
        pihakTemp.setPoskod(pihak.getPoskod());
        if (negeri != null && !negeri.equals("")) {
            pihakTemp.setNegeri(kodNegeriDAO.findById(negeri));
        } else {
            pihakTemp.setNegeri(null);
        }
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());

        if (suratNegeri != null && !suratNegeri.equals("")) {
            pihakTemp.setSuratNegeri(kodNegeriDAO.findById(suratNegeri));
        } else {
            pihakTemp.setSuratNegeri(null);
        }
        pihakTemp.setInfoAudit(info);


        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        InfoAudit info1 = new InfoAudit();
        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
            info1 = pemohon.getInfoAudit();
            info1.setDiKemaskiniOleh(peng);
            info1.setTarikhKemaskini(new java.util.Date());
        } else {
            pemohon = new Pemohon();
            info1.setDimasukOleh(peng);
            info1.setTarikhMasuk(new java.util.Date());
        }
        pemohon.setPermohonan(permohonan);
        pemohon.setPihak(pihakTemp);
        pemohon.setInfoAudit(info);

        if (pemohon != null) {
            pemohon.setCawangan(permohonan.getCawangan());
            pemohonService.simpanPihak(pihak);
            pemohonService.saveOrUpdate(pemohon);
            rehydrate();

            addSimpleMessage("Maklumat Pemohon telah disimpan");
            return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
        }

        //    return new JSP("strata/pbbm/edit_pemohon.jsp").addParameter("popup", "true");
        return new JSP("strata/pbbm/maklumat_pemohon.jsp").addParameter("tab", "true");
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getCheckAlamat() {
        return checkAlamat;
    }

    public void setCheckAlamat(String checkAlamat) {
        this.checkAlamat = checkAlamat;
    }
}
