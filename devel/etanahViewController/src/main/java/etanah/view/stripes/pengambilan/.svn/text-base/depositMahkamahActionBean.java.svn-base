/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PermohonanPihakPendeposit;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PelupusanService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.text.ParseException;
import org.apache.log4j.Logger;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.model.KodStatusAkaun;
import etanah.model.Pemohon;

/**
 *
 * @author Admin
 */
@UrlBinding("/pengambilan/depositMahkamahedit")
public class depositMahkamahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(depositMahkamahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
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
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
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
    private BigDecimal amnTawarRosak;
    private BigDecimal amnTawarPampasan;
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
    private String kodDepositSpeks;
    private Akaun akaun;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("pengambilan/pengambilan_deposit_papar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
                pihak = pengambilanService.findByIdPihak(permohonanPihakPendeposit.getPihak().getIdPihak());
            }
        }

        return new JSP("pengambilan/pengambilan_deposit_papar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                //permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
                //pihak = pengambilanService.findByIdPihak(permohonanPihakPendeposit.getPihak().getIdPihak());
                pemohon = pengambilanService.findPemohon(idPermohonan);
            }
        }

        return new JSP("pengambilan/pengambilan_deposit_simpan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormStrata() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                //permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
                //pihak = pengambilanService.findByIdPihak(permohonanPihakPendeposit.getPihak().getIdPihak());
                pemohon = pengambilanService.findPemohon(idPermohonan);
            }
        }

        return new JSP("strata/strata_deposit_simpan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            //PermohonanPihakPendepositList
            permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
            permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
            try {
//                permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
                long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
                permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
                if (permohonanPihakPendeposit == null) {
                    logger.debug("permohonanPihakPendeposit null");
                    pemohon = pengambilanService.findPemohon(idPermohonan);
                } else {
                    pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                    noAkaun = permohonanPihakPendeposit.getNoAkaun();
                    amaun = permohonanPihakPendeposit.getAmaun().toString();
                    catatan = permohonanPihakPendeposit.getCatatan();
                    caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                    if (permohonanPihakPendeposit.getBank().getKod() != null) {
                        jenisBank = permohonanPihakPendeposit.getBank().getKod();
                    }
                }
            } catch (Exception l) {
            }
        }
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        logger.info("id pihak >> " + idPihak);
        if (idPermohonan != null) {
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
//            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
            pihak = pihakDAO.findById(idPihak);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            logger.info("maklumat pihak >> " + pihak.getNama());
            logger.info("no mohon >> " + idPermohonan);
            if (permohonanPihakPendeposit == null) {
                logger.info("permohonanPihakPendeposit is null");

            } else {
                logger.info("permohonnan pihak >> " + permohonanPihakPendeposit.getNoAkaun());
                // pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                noAkaun = permohonanPihakPendeposit.getNoAkaun();
                amaun = permohonanPihakPendeposit.getAmaun().toString();
                catatan = permohonanPihakPendeposit.getCatatan();
                caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                kodDepositSpeks = permohonanPihakPendeposit.getKodDepositSpeks();
                try {
                    if (permohonanPihakPendeposit.getBank().getKod() != null) {
                        jenisBank = permohonanPihakPendeposit.getBank().getKod();
                    }
                } catch (Exception j) {
                }
            }
            if (pihak == null) {
            } else {
//                kodJenisPengenalan = pihak.getJenisPengenalan();
            }
        }
        //rehydrate();
        return new JSP("pengambilan/pengambilan_deposit_papar.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandunganPendeposit() {

//        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        long idPihak2 = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);
//        pihak=pihakDAO.findById(idPihak2);
        permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak2);
        akaun = pengambilanService.findAkaunBynoAkaun(idPermohonan);
//        logger.debug("id Pihak "+idPihak2);

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
            permohonanPihakPendeposit.setAmaun(new BigDecimal(amaun));
            permohonanPihakPendeposit.setBank(kodBankDAO.findById(jenisBank));
            permohonanPihakPendeposit.setKodDepositSpeks(kodDepositSpeks);
            permohonanPihakPendeposit.setCaraBayaran(caraBayaran);
            if (catatan != null) {
                permohonanPihakPendeposit.setCatatan(catatan);
            }
            permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
            pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);
//        pelupusanService.kemaskiniPermohonanPihakPendeposit(permohonanPihakPendeposit);
//         if (pihak != null) {
//            logger.debug("pihak x empty::update");
//
//            pihak.setNama(pihak.getNama());
//            pihak.setJenisPengenalan(pihak.getJenisPengenalan());
//            pihak.setNoPengenalan(pihak.getNoPengenalan());
//            pihak.setAlamat1(pihak.getAlamat1());
//            pihak.setAlamat2(pihak.getAlamat2());
//            pihak.setAlamat3(pihak.getAlamat3());
//            pihak.setAlamat4(pihak.getAlamat4());
//            pihak.setPoskod(pihak.getPoskod());
//            pihak.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
//            pihak.setNoTelefon1(pihak.getNoTelefon1());
//            pihak.setEmail(pihak.getEmail());
//            pihak.setInfoAudit(audit);
//            pelupusanService.saveOrUpdate(pihak);
//        }
        }
//        else
//        {
//        logger.debug("permohonanPihakPendeposit empty::save new");
//        logger.debug("pihak :"+pihak.getNama());
//        PermohonanPihakPendeposit permohonanPihakPendepositTemp = new PermohonanPihakPendeposit();
//
//        permohonanPihakPendepositTemp.setPihak(pihak);
//        permohonanPihakPendepositTemp.setPermohonan(permohonan);
//        permohonanPihakPendepositTemp.setInfoAudit(audit);
//        permohonanPihakPendepositTemp.setNoAkaun(noAkaun);
//        permohonanPihakPendepositTemp.setAmaun(new BigDecimal(amaun));
//        permohonanPihakPendepositTemp.setBank(kodBankDAO.findById(jenisBank));
//        permohonanPihakPendepositTemp.setCaraBayaran(caraBayaran);
//        if(catatan != null){
//            permohonanPihakPendepositTemp.setCatatan(catatan);
//        }
//        permohonanPihakPendepositTemp.setCawangan(permohonan.getCawangan());
//        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendepositTemp);
//        }


//        } else {
//            logger.debug("pihak empty::save new");
//            Pihak pihakTemp = new Pihak();
//            pihakTemp.setNama(pihak.getNama());
//            pihakTemp.setJenisPengenalan(pihak.getJenisPengenalan());
//            pihakTemp.setNoPengenalan(pihak.getNoPengenalan());
//            pihakTemp.setAlamat1(pihak.getAlamat1());
//            pihakTemp.setAlamat2(pihak.getAlamat2());
//            pihakTemp.setAlamat3(pihak.getAlamat3());
//            pihakTemp.setAlamat4(pihak.getAlamat4());
//            pihakTemp.setPoskod(pihak.getPoskod());
//            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
//            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
//            pihakTemp.setEmail(pihak.getEmail());
//            pihakTemp.setInfoAudit(audit);
//            pelupusanService.saveOrUpdate(pihakTemp);
//        }

        if (akaun != null) {
            if (akaun.getPemegang() == null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());
                akaun.setPemegang(pihak);
                akaun.setInfoAudit(info);
                pengambilanService.simpanAkaun(akaun);

            } else {
                KodStatusAkaun kodStatusAkaun = new KodStatusAkaun();
                kodStatusAkaun = pengambilanService.findKodStatusAkaun("B");
                akaun.setStatus(kodStatusAkaun);
                BigDecimal baki = new BigDecimal(0);
                akaun.setBaki(baki);
                pengambilanService.simpanAkaun(akaun);
            }
        }

        rehydrate();
        addSimpleMessage("Maklumat telah Berjaya dikemaskini");

        return new JSP("pengambilan/pengambilan_deposit_papar.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPendeposit() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);

        pemohon = pengambilanService.findPemohon(idPermohonan);
        logger.debug("pemohon >> " + pemohon.getNama());
        InfoAudit audit = peng.getInfoAudit();//new InfoAudit();
        audit.setDimasukOleh(peng);
        audit.setTarikhMasuk(new java.util.Date());
        audit.setTarikhKemaskini(new java.util.Date());
        if (permohonanPihakPendeposit == null) {
            permohonanPihakPendeposit = new PermohonanPihakPendeposit();
        } else {
            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
        }
        permohonanPihakPendeposit.setPihak(pemohon.getPihak());
        permohonanPihakPendeposit.setPermohonan(permohonan);
        permohonanPihakPendeposit.setInfoAudit(audit);
        permohonanPihakPendeposit.setNoAkaun(noAkaun);
        permohonanPihakPendeposit.setAmaun(new BigDecimal(amaun));
        permohonanPihakPendeposit.setBank(kodBankDAO.findById(jenisBank));
        permohonanPihakPendeposit.setCaraBayaran(caraBayaran);
        if (catatan != null) {
            permohonanPihakPendeposit.setCatatan(catatan);
        }
        permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);
//        
        if (akaun == null) {
            akaun = new Akaun();
        } else {
            akaun = pengambilanService.findAkaunBynoAkaun(idPermohonan);
        }
        InfoAudit info = new InfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        akaun.setNoAkaun(idPermohonan);
        akaun.setCawangan(permohonan.getCawangan());
        akaun.setPemegang(pemohon.getPihak());
        KodAkaun kodAkaun = new KodAkaun();
        kodAkaun = pengambilanService.findKodAkaun("AD");
        akaun.setKodAkaun(kodAkaun);
        akaun.setInfoAudit(info);

        pengambilanService.simpanAkaun(akaun);
        rehydrate();
        addSimpleMessage("Maklumat telah Berjaya disimpan");
        return new JSP("pengambilan/pengambilan_deposit_simpan.jsp").addParameter("tab", "true");
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

    public String getKodDepositSpeks() {
        return kodDepositSpeks;
    }

    public void setKodDepositSpeks(String kodDepositSpeks) {
        this.kodDepositSpeks = kodDepositSpeks;
    }

    
}
