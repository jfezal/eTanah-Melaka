/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.KodStatusAkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Akaun;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodAkaun;
import etanah.model.KodBangsa;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusAkaun;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.StrataPtService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.common.PihakService;
import etanah.view.BasicTabActionBean;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/mlk/depositBngnSemantera")
public class DepositBngnSemanteraActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(DepositBngnSemanteraActionBean.class);
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
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
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private String ringkasanBantah;
    private String butiranTanah;
    private String notis;
    private String pernyataan;
    private BigDecimal amaunProv;
    private BigDecimal amaunProv1;
    private String alasanAmnPampasan;
    private String lampiran;
    private PermohonanPihakPendeposit permohonanPihakPendeposit;
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList;
    private Pihak pihak;
    private Pemohon pemohon;
    private Hakmilik hakmilik;
    etanahActionBeanContext ctx;
    private String kodBank;
    private String noAkaun;
    private String amaun;
    private String catatan;
    private String jenisBank;
    private String caraBayaran;
    private Akaun akaun;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pemohon = strService.findById(idPermohonan);

                Akaun ak = strService.findIDMohonDeposit(idPermohonan);
                if (ak != null) {
                    amaunProv1 = ak.getBaki();
                } else {
                    amaunProv1 = new BigDecimal(0);
                }
                amaunProv = new BigDecimal(-1);
                amaunProv1 = amaunProv1.multiply(amaunProv);
            }
        }
        return new JSP("strata/pbbm/deposit_bngn_semantera.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
            permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
            permohonanPihakPendeposit = strService.findByPendeposit(idPermohonan);
            if (permohonanPihakPendeposit == null) {
                logger.debug("permohonanPihakPendeposit null");
                pemohon = strService.findById(idPermohonan);
            } else {
                pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                noAkaun = permohonanPihakPendeposit.getNoAkaun();
                amaun = permohonanPihakPendeposit.getAmaun().toString();
                catatan = permohonanPihakPendeposit.getCatatan();
                caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                if (permohonanPihakPendeposit.getBank() != null) {
                    jenisBank = permohonanPihakPendeposit.getBank().getKod();
                }
            }

            Akaun ak = strService.findIDMohonDeposit(idPermohonan);
            if (ak != null) {
                amaunProv1 = ak.getBaki();
            } else {
                amaunProv1 = new BigDecimal(0);
            }

            amaunProv = new BigDecimal(-1);
            amaunProv1 = amaunProv1.multiply(amaunProv);
        }
    }

    public Resolution resetForm() {
        catatan = null;
        noAkaun = null;
        jenisBank = null;
        caraBayaran = null;      
        addSimpleMessage("Maklumat telah berjaya dikemaskini ");
        return new RedirectResolution(DepositBngnSemanteraActionBean.class, "showForm");
    }

    public Resolution simpanKandunganPendeposit() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);
        permohonanPihakPendeposit = strService.findByPendeposit(idPermohonan);
        akaun = strService.findIDMohonDeposit(idPermohonan);
        InfoAudit audit = new InfoAudit();
        audit.setDimasukOleh(pguna);
        audit.setDiKemaskiniOleh(pguna);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
        if (permohonanPihakPendeposit != null) {
            logger.debug("permohonanPihakPendeposit x empty::update");
            pihak = permohonanPihakPendeposit.getPihak();
            permohonanPihakPendeposit.setInfoAudit(audit);
            permohonanPihakPendeposit.setPihak(pihak);
            permohonanPihakPendeposit.setPermohonan(permohonan);
            permohonanPihakPendeposit.setInfoAudit(audit);
            permohonanPihakPendeposit.setNoAkaun(noAkaun);
            permohonanPihakPendeposit.setAmaun(amaunProv1);
            permohonanPihakPendeposit.setBank(kodBankDAO.findById(jenisBank));
            permohonanPihakPendeposit.setCaraBayaran(caraBayaran);
            if (catatan != null) {
                permohonanPihakPendeposit.setCatatan(catatan);
            }
            permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
            pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);
        }
        if (akaun != null) {
            if (akaun.getPemegang() == null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());

                akaun.setPemegang(pihak);
                akaun.setInfoAudit(info);
                pengambilanService.simpanAkaun(akaun);

            }
        }
        addSimpleMessage("Maklumat telah berjaya dikemaskini");
        return new JSP("strata/strata_deposit_papar.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPendeposit() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);

        pemohon = strService.findById(idPermohonan);
        logger.debug("pemohon >> " + pemohon.getNama());
        InfoAudit audit = peng.getInfoAudit();//new InfoAudit();
        audit.setDimasukOleh(peng);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
        if (permohonanPihakPendeposit == null) {
            permohonanPihakPendeposit = new PermohonanPihakPendeposit();
        } else {
            permohonanPihakPendeposit = strService.findByPendeposit(idPermohonan);
        }
        permohonanPihakPendeposit.setPihak(pemohon.getPihak());
        permohonanPihakPendeposit.setPermohonan(permohonan);
        permohonanPihakPendeposit.setInfoAudit(audit);
        permohonanPihakPendeposit.setNoAkaun(noAkaun);
        permohonanPihakPendeposit.setAmaun(amaunProv1);
        permohonanPihakPendeposit.setBank(kodBankDAO.findById(jenisBank));
        permohonanPihakPendeposit.setCaraBayaran(caraBayaran);
        if (catatan != null) {
            permohonanPihakPendeposit.setCatatan(catatan);
        }
        permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);

        akaun = strService.findIDMohonDeposit(idPermohonan);
        if (akaun != null) {
            InfoAudit info = new InfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            akaun.setNoAkaun(idPermohonan);
            akaun.setCawangan(permohonan.getCawangan());
            akaun.setPemegang(pemohon.getPihak());
            KodAkaun kodAkaun = new KodAkaun();
            kodAkaun = pengambilanService.findKodAkaun("AD");
            akaun.setKodAkaun(kodAkaun);
            KodStatusAkaun kodsts = kodStatusAkaunDAO.findById("B");
            akaun.setStatus(kodsts);
            BigDecimal baki = new BigDecimal(0);
            akaun.setBaki(baki);
            akaun.setInfoAudit(info);
            strService.simpanAkaun(akaun);
            addSimpleMessage("Maklumat telah berjaya disimpan ");
        }
        rehydrate();
        return new JSP("strata/pbbm/deposit_bngn_semantera.jsp").addParameter("tab", "true");
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }
    @Inject
    PelupusanService pelupusanService;
    private String kodNegeri;

    public String getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(String caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public String getAmaun() {
        return amaun;
    }

    public void setAmaun(String amaun) {
        this.amaun = amaun;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getJenisBank() {
        return jenisBank;
    }

    public void setJenisBank(String jenisBank) {
        this.jenisBank = jenisBank;
    }

    public String getNoAkaun() {
        return noAkaun;
    }

    public void setNoAkaun(String noAkaun) {
        this.noAkaun = noAkaun;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    private HakmilikPermohonan hakmilikPermohonan;

    public etanahActionBeanContext getCtx() {
        return ctx;
    }

    public void setCtx(etanahActionBeanContext ctx) {
        this.ctx = ctx;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanPihakPendeposit getPermohonanPihakPendeposit() {
        return permohonanPihakPendeposit;
    }

    public void setPermohonanPihakPendeposit(PermohonanPihakPendeposit permohonanPihakPendeposit) {
        this.permohonanPihakPendeposit = permohonanPihakPendeposit;
    }

    public List<PermohonanPihakPendeposit> getPermohonanPihakPendepositList() {
        return permohonanPihakPendepositList;
    }

    public void setPermohonanPihakPendepositList(List<PermohonanPihakPendeposit> permohonanPihakPendepositList) {
        this.permohonanPihakPendepositList = permohonanPihakPendepositList;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public BigDecimal getAmaunProv() {
        return amaunProv;
    }

    public void setAmaunProv(BigDecimal amaunProv) {
        this.amaunProv = amaunProv;
    }

    public BigDecimal getAmaunProv1() {
        return amaunProv1;
    }

    public void setAmaunProv1(BigDecimal amaunProv1) {
        this.amaunProv1 = amaunProv1;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
}
