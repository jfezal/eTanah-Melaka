/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.KodBangsa;
import etanah.model.KodFlagPihak;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.service.common.PermohonanPihakService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.view.ListUtil;
import java.util.ArrayList;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kemasukan_waris")
public class KemasukanWarisActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(KemasukanWarisActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    ListUtil listUtil;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    private List<KodBangsa> senaraiKodBangsa;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private List<PermohonanPihak> warisList;
    private List<Pihak> pihakByNameList;
    private String idPermohonan;
    private Permohonan permohonan;
    boolean cariFlag;
    boolean tiadaDataFlag = false;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/consent/kemasukan_senarai_waris.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new ForwardResolution("/WEB-INF/jsp/consent/kemasukan_senarai_waris.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            warisList = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "WAR");
        }
    }

    public Resolution warisPopup() {
        String urusan = getContext().getRequest().getParameter("urusan");
        if (StringUtils.isNotBlank(urusan)) {
            getContext().getRequest().setAttribute("urusan", urusan);
        }

        return new JSP("consent/kemasukan_maklumat_waris.jsp").addParameter("popup", "true");
    }

    public Resolution cariPihak() {

        if (pihak != null) {

            if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() == null) {

                Pihak pihakSearch = new Pihak();
//                pihakSearch = pihakService.findPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());
//                pihakSearch = pihakService.findPihakByDimasuk(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan(), "MIG");
                pihakSearch = pihakService.findPihakByFlagPihak(pihak.getJenisPengenalan().getKod(), pihak.getNoPengenalan());

                boolean duplicateFlag = false;

                if (pihakSearch != null) {
                    if (!(warisList.isEmpty())) {
                        for (PermohonanPihak pem : warisList) {
                            if (pem.getPihak().getIdPihak() == pihakSearch.getIdPihak()) {
                                duplicateFlag = true;
                                break;
                            }
                        }
                    }
                }
                senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());

                if (!duplicateFlag) {
                    if (pihakSearch != null) {
                        pihak = pihakSearch;
                        cariFlag = true;
                        tiadaDataFlag = false;
                    } else {
                        addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                        cariFlag = true;
                        tiadaDataFlag = true;
                    }

                } else {
                    pihak = new Pihak();
                    addSimpleError("Maklumat Ini Telah Dipilih.");
                }
            } else if (pihak.getNama() != null && pihak.getNoPengenalan() == null) {
                pihakByNameList = new ArrayList<Pihak>();
                if (pihak.getJenisPengenalan() != null && pihak.getNama() != null) {
//                    pihakByNameList = pihakService.findPihakByKodKpAndName(pihak.getJenisPengenalan().getKod(), pihak.getNama());
//                    pihakByNameList = pihakService.findPihakByKodKpAndNameAndDimasuk(pihak.getJenisPengenalan().getKod(), pihak.getNama(), "MIG");
                    pihakByNameList = pihakService.findPihakByKodKpAndNameAndFlagPihak(pihak.getJenisPengenalan().getKod(), pihak.getNama());

                } else if (pihak.getJenisPengenalan() == null && pihak.getNama() != null) {
//                    pihakByNameList = pihakService.findPihakByNama(pihak.getNama());
//                    pihakByNameList = pihakService.findPihakByNamaByDimasuk(pihak.getNama(), "MIG");
                    pihakByNameList = pihakService.findPihakByNamaByFlagPihak(pihak.getNama());


                }

                if (pihakByNameList.isEmpty()) {
                    addSimpleError("Tiada Data. Sila Masukkan Maklumat Baru.");
                    cariFlag = true;
                    tiadaDataFlag = true;
//                    senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
                }
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() == null && pihak.getNama() == null) {
                addSimpleError("Sila Masukkan Nombor Pengenalan Atau Nama.");
            } else if (pihak.getJenisPengenalan() == null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Masukkan Jenis Pengenalan.");
            } else if (pihak.getJenisPengenalan() != null && pihak.getNoPengenalan() != null && pihak.getNama() != null) {
                addSimpleError("Sila Pastikan Carian Berdasarkan Nombor Pengenalan Atau Nama.");
            }

        } else {

            addSimpleError("Sila Masukkan Jenis Pengenalan Dan Nombor Pengenalan Atau Nama.");
        }

        return new JSP("consent/kemasukan_maklumat_waris.jsp").addParameter("popup", "true");
    }

    public Resolution selectName() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        pihak = pihakService.findById(idPihak);
        cariFlag = true;
        tiadaDataFlag = false;
//        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        return new JSP("consent/kemasukan_maklumat_waris.jsp").addParameter("popup", "true");
    }

    public Resolution cariSemula() {
        pihak = new Pihak();
        return new JSP("consent/kemasukan_maklumat_waris.jsp").addParameter("popup", "true");
    }

    public Resolution simpanWaris() {

        permohonan = null;

        if (idPermohonan != null) {
            permohonan = permohonanService.findById(idPermohonan);
        }

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

        if (pihakTemp == null) {
            pihakTemp = new Pihak();
        }

        pihakTemp.setNama(pihak.getNama());
        pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
        pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
        pihakTemp.setKodJantina(pihak.getKodJantina());
        pihakTemp.setBangsa(pihak.getBangsa());
        pihakTemp.setSuku(pihak.getSuku());
        pihakTemp.setTempatSuku(pihak.getTempatSuku());
        pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
        pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
        pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
        pihakTemp.setAlamat1(pihak.getAlamat1());
        pihakTemp.setAlamat2(pihak.getAlamat2());
        pihakTemp.setAlamat3(pihak.getAlamat3());
        pihakTemp.setAlamat4(pihak.getAlamat4());
        pihakTemp.setPoskod(pihak.getPoskod());
        pihakTemp.setNegeri(pihak.getNegeri());
        pihakTemp.setInfoAudit(infoAudit);
        pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
        pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
        pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
        pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
        pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
        pihakTemp.setSuratNegeri(pihak.getSuratNegeri());

        KodFlagPihak kodFlagPihak = new KodFlagPihak();
        kodFlagPihak.setKod("AK");
        pihakTemp.setKodFlagPihak(kodFlagPihak);

        if (permohonanPihak == null) {
            permohonanPihak = new PermohonanPihak();
        }
        permohonanPihak.setPermohonan(permohonan);
        permohonanPihak.setPihak(pihakTemp);
        permohonanPihak.setInfoAudit(infoAudit);
        permohonanPihak.setSyerPembilang(0);
        permohonanPihak.setSyerPenyebut(0);

        KodJenisPihakBerkepentingan kodPB = new KodJenisPihakBerkepentingan();
        kodPB.setKod("WAR");
        permohonanPihak.setJenis(kodPB);
        permohonanPihak.setCawangan(permohonan.getCawangan());

        pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihak);

        if (permohonanPihak != null) {
            return new RedirectResolution("/consent/kemasukan_waris?getSenaraiWaris").addParameter("tab", "true");
        }

        return new StreamingResolution("text/plain", "1");

    }

    public Resolution getSenaraiWaris() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        if (idPermohonan != null) {
        }
        return new JSP("consent/kemasukan_senarai_waris.jsp").addParameter("tab", "true");
    }

    public Resolution showEditWaris() {
        String idPihak = getContext().getRequest().getParameter("idPihak");

        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        senaraiKodBangsa = listUtil.getSenaraiKodBangsaByJenisPengenalan(pihak.getJenisPengenalan().getKod());
        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(permohonan.getIdPermohonan(), pihak.getIdPihak());

        return new JSP("consent/edit_waris.jsp").addParameter("popup", "true");
    }

    public Resolution simpanEditWaris() {
        Transaction tx = sessionProvider.get().beginTransaction();
        tx.begin();
        try {
            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());

            if (pihakTemp == null) {
                pihakTemp = new Pihak();
            }

            InfoAudit infoAudit = new InfoAudit();
            infoAudit = pihakTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pg);
            infoAudit.setTarikhKemaskini(new java.util.Date());

            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
            pihakTemp.setKodJantina(pihak.getKodJantina());
            pihakTemp.setBangsa(pihak.getBangsa());
            pihakTemp.setSuku(pihak.getSuku());
            pihakTemp.setTempatSuku(pihak.getTempatSuku());
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setNoTelefon2(pihak.getNoTelefon2());
            pihakTemp.setNoTelefonBimbit(pihak.getNoTelefonBimbit());
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(pihak.getNegeri());
            pihakTemp.setInfoAudit(infoAudit);
            pihakTemp.setSuratAlamat1(pihak.getSuratAlamat1());
            pihakTemp.setSuratAlamat2(pihak.getSuratAlamat2());
            pihakTemp.setSuratAlamat3(pihak.getSuratAlamat3());
            pihakTemp.setSuratAlamat4(pihak.getSuratAlamat4());
            pihakTemp.setSuratPoskod(pihak.getSuratPoskod());
            pihakTemp.setSuratNegeri(pihak.getSuratNegeri());
            PermohonanPihak permohonanPihakTemp = new PermohonanPihak();
            permohonanPihakTemp = permohonanPihakService.findById(String.valueOf(permohonanPihak.getIdPermohonanPihak()));
            permohonanPihakTemp.setKaitan(permohonanPihak.getKaitan());
            permohonanPihakTemp.setUmur(permohonanPihak.getUmur());

            pihakService.saveOrUpdatePihakKepentinganPihak(pihakTemp, permohonanPihakTemp);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            return new JSP("consent/kemasukan_senarai_waris.jsp").addParameter("tab", "true");
        }

        tx.commit();

        return new JSP("consent/kemasukan_senarai_waris.jsp").addParameter("tab", "true");

    }

    public Resolution viewPihakDetail() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        pihak = pihakService.findById(idPihak);

        if (idHakmilik != null) {
            permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(Long.parseLong(idPihak), idHakmilik, permohonan.getIdPermohonan());
        } else {
            permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(permohonan.getIdPermohonan(), Long.parseLong(idPihak));
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/paparan_pihak.jsp").addParameter("popup", "true");
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<PermohonanPihak> getWarisList() {
        return warisList;
    }

    public void setWarisList(List<PermohonanPihak> warisList) {
        this.warisList = warisList;
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

    public List<KodBangsa> getSenaraiKodBangsa() {
        return senaraiKodBangsa;
    }

    public void setSenaraiKodBangsa(List<KodBangsa> senaraiKodBangsa) {
        this.senaraiKodBangsa = senaraiKodBangsa;
    }

    public List<Pihak> getPihakByNameList() {
        return pihakByNameList;
    }

    public void setPihakByNameList(List<Pihak> pihakByNameList) {
        this.pihakByNameList = pihakByNameList;
    }
}
