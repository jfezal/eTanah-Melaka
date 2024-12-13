/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import etanah.dao.KompaunDAO;
import etanah.model.AduanOrangKenaSyak;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;

import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.Kompaun;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.report.ReportUtil;

import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_status_kompaun")
public class UtilitiStatusKompaunActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiStatusKompaunActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    DokumenDAO dokumenDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pengguna;
    private Long idDokumen;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private List<Kompaun> senaraiKompaun;
    private List<Kompaun> senaraiKompaunUpdate = new ArrayList<Kompaun>();
    private String amaunBaru;
    private Kompaun kompaun;
    private String idOks;
    private AduanOrangKenaSyak oks;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanTuntutanKos ptk;
    private PermohonanTuntutan pt;
    private PermohonanTuntutanButiran ptb;
    private String tempohHari;
    private boolean newAmaunUpdated = Boolean.FALSE;
    private String idKompaun;
    private List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;

    class KompaunCache implements Serializable {

        Permohonan permohonan;
        Kompaun kompaun;
    }

    @DefaultHandler
    public Resolution findKompaun() {
        logger.info("------------findKompaun-------------");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_status_kompaun.jsp");
    }

    public Resolution cariSemula() {
        logger.info("------------cariSemula-------------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        idPermohonan = "";
        senaraiKompaun.clear();
        senaraiKompaunUpdate.clear();

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_status_kompaun.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKompaun = new ArrayList<Kompaun>();
        getUrusanfromSession();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaun(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun masa rehydrate: " + senaraiKompaun.size());

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
        System.out.println("idPermohonan1 rehydrate : " + idPermohonan);
    }

    public Resolution searchKompaun() {
        logger.info("------------searchKompaun-------------");
        System.out.println("idPermohonan : " + idPermohonan);
        //clear data in session before search new data
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        ctx.removeWorkdata();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaunByID(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun : " + senaraiKompaun.size());

                senaraiMohonTuntutBayar = enforceService.getSenaraiPtb(idPermohonan);
                logger.info("size list senarai mohon tuntut bayar : " + senaraiMohonTuntutBayar.size());
                saveToSession(ctx);

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
                senaraiKompaun.clear();
            }

            if (senaraiKompaun.isEmpty()) {
                addSimpleMessage("Permohonan " + idPermohonan + " tidak mempunyai rekod kompaun.");
            } else {
                addSimpleMessage("Permohonan " + idPermohonan + " mempunyai " + senaraiKompaun.size() + " rekod kompaun.");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_status_kompaun.jsp");
    }

    public final void getUrusanfromSession() {
        logger.debug("get id permohonan from session");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        try {
            KompaunCache u = (KompaunCache) ctx.getWorkData();
            if (u != null) {
                permohonan = u.permohonan;
                kompaun = u.kompaun;
                if (permohonan != null) {
                    idPermohonan = permohonan.getIdPermohonan();
                    System.out.println("idPermohonan getUrusanfromSession : " + idPermohonan);
                }
            } else {
                logger.debug("no data in session");
            }
        } catch (Exception ex) {
            logger.error(ex);
            ctx.removeWorkdata();
        }

    }

    public final void saveToSession(etanahActionBeanContext ctx) {
        logger.debug("saveToSession");
        KompaunCache u = (KompaunCache) ctx.getWorkData();
        if (u == null) {
            u = new KompaunCache();
        }
        u.permohonan = permohonan;
        System.out.println("saveToSession u.permohonan : " + u.permohonan.getIdPermohonan());

        ctx.setWorkData(u);
    }

    public Resolution refreshpage() {
        logger.info("------------refreshpage--------------");
        rehydrate();
        return new RedirectResolution(UtilitiStatusKompaunActionBean.class, "locate");
    }

    public Resolution reload() {
        logger.info("------------reload-------------");
//        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/carian_notis_penyampaian.jsp");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        System.out.println("reload id mohon : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                senaraiKompaun = enforcementService.findKompaun(permohonan.getIdPermohonan());
                logger.info("size senaraiKompaun masa reload: " + senaraiKompaun.size());
            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        }
//        return new RedirectResolution(UtilitiNotisPenyampaianActionBean.class, "locate");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_status_kompaun.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public List<Kompaun> getSenaraiKompaun() {
        return senaraiKompaun;
    }

    public void setSenaraiKompaun(List<Kompaun> senaraiKompaun) {
        this.senaraiKompaun = senaraiKompaun;
    }

    public String getAmaunBaru() {
        return amaunBaru;
    }

    public void setAmaunBaru(String amaunBaru) {
        this.amaunBaru = amaunBaru;
    }

    public String getIdOks() {
        return idOks;
    }

    public void setIdOks(String idOks) {
        this.idOks = idOks;
    }

    public Kompaun getKompaun() {
        return kompaun;
    }

    public void setKompaun(Kompaun kompaun) {
        this.kompaun = kompaun;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public PermohonanTuntutan getPt() {
        return pt;
    }

    public void setPt(PermohonanTuntutan pt) {
        this.pt = pt;
    }

    public PermohonanTuntutanButiran getPtb() {
        return ptb;
    }

    public void setPtb(PermohonanTuntutanButiran ptb) {
        this.ptb = ptb;
    }

    public PermohonanTuntutanKos getPtk() {
        return ptk;
    }

    public void setPtk(PermohonanTuntutanKos ptk) {
        this.ptk = ptk;
    }

    public String getTempohHari() {
        return tempohHari;
    }

    public void setTempohHari(String tempohHari) {
        this.tempohHari = tempohHari;
    }

    public boolean isNewAmaunUpdated() {
        return newAmaunUpdated;
    }

    public void setNewAmaunUpdated(boolean newAmaunUpdated) {
        this.newAmaunUpdated = newAmaunUpdated;
    }

    public String getIdKompaun() {
        return idKompaun;
    }

    public void setIdKompaun(String idKompaun) {
        this.idKompaun = idKompaun;
    }

    public List<Kompaun> getSenaraiKompaunUpdate() {
        return senaraiKompaunUpdate;
    }

    public void setSenaraiKompaunUpdate(List<Kompaun> senaraiKompaunUpdate) {
        this.senaraiKompaunUpdate = senaraiKompaunUpdate;
    }

    public List<PermohonanTuntutanBayar> getSenaraiMohonTuntutBayar() {
        return senaraiMohonTuntutBayar;
    }

    public void setSenaraiMohonTuntutBayar(List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar) {
        this.senaraiMohonTuntutBayar = senaraiMohonTuntutBayar;
    }
}
