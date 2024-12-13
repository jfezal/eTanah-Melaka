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
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodNegeri;
import etanah.model.KodCawangan;
import etanah.model.KodJenisPengenalan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.LaporanPemulihanTanah;
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

/**
 *
 * @author User
 */
@UrlBinding("/pengambilan/depositPemulihan")
public class PengambilanPemulihanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PengambilanPemulihanActionBean.class);
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
    private etanah.Configuration conf;
    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private KodBank kodBank;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private KodJenisPengenalan kodJenisPengenalan;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private String ringkasanBantah;
    private String butiranTanah;
    private String notis;
    private String pernyataan;
    private BigDecimal sum;
    private BigDecimal kosKerosakanTanah;
    private BigDecimal kosKerosakanBangunan;
    private BigDecimal kosKerosakanPokok;
    private BigDecimal kosKerosakanInfra;
    private BigDecimal kosKerosakanLain;
    private BigDecimal kosKecacatanTanah;
    private BigDecimal amnTawarRosak;
    private BigDecimal amnTawarPampasan;
    private String alasanAmnPampasan;
    private String lampiran;
    private PermohonanPihakPendeposit permohonanPihakPendeposit;
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList;
    private Pihak pihak;
    private Hakmilik hakmilik;
    etanahActionBeanContext ctx;
//    private String kodBank;
    private String noAkaun;
    private BigDecimal amaun;
    private String catatan;
    private String jenisBank;
    private String caraBayaran;
    private String penyerahNegeri;
    private Akaun akaun;
    private boolean showPulih = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if(idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            if(permohonan != null) {
//                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
//            }
//        }

        return new JSP("pengambilan/pengambilan_pulih.jsp").addParameter("tab", "true");
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

        return new JSP("pengambilan/pengambilan_pulih_papar.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            //PermohonanPihakPendepositList
            permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
            permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            if (permohonanPihakPendeposit == null) {
                logger.debug("permohonanPihakPendeposit null");
            }
        }
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);


//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null) {
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
//            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
            pihak = pihakDAO.findById(idPihak);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);

            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdPihak(idPermohonan, idPihak);
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            logger.info("id pihak : " + idPihak);
            logger.info("id mp : " + permohonanPihak.getIdPermohonanPihak());
//            logger.info("id pihak mohon pendeposit : " + permohonanPihakPendeposit.getPihak().getIdPihak());

            if (permohonanPihakPendeposit == null) {
                logger.info("permohonanPihakPendeposit is null");
            } else {
                pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                noAkaun = permohonanPihakPendeposit.getNoAkaun();
                amaun = permohonanPihakPendeposit.getAmaun();
                catatan = permohonanPihakPendeposit.getCatatan();
                caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                kodBank = permohonanPihakPendeposit.getBank();
            }
            if (pihak == null) {
            } else {
                kodJenisPengenalan = pihak.getJenisPengenalan();

                if (permohonanPihak != null) {
                    laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahIdMP(idPermohonan, permohonanPihak.getIdPermohonanPihak());

                    if (laporanPemulihanTanah != null) {
                        logger.debug("laporanPemulihanTanah tidak null disini " + laporanPemulihanTanah);

                        kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                        logger.debug("kosKerosakanTanah tidak null disini " + kosKerosakanTanah);
                        if (kosKerosakanTanah == null) {
                            logger.debug("kosKerosakanTanah null disini " + kosKerosakanTanah);
                            kosKerosakanTanah = BigDecimal.ZERO;
                        }

                        kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                        logger.debug("kosKerosakanBangunan tidak null disini " + kosKerosakanBangunan);
                        if (kosKerosakanBangunan == null) {
                            logger.debug("kosKerosakanBangunan null disini " + kosKerosakanBangunan);
                            kosKerosakanBangunan = BigDecimal.ZERO;
                        }

                        kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                        logger.debug("kosKerosakanPokok tidak null disini " + kosKerosakanPokok);
                        if (kosKerosakanPokok == null) {
                            logger.debug("kosKerosakanPokok null disini " + kosKerosakanPokok);
                            kosKerosakanPokok = BigDecimal.ZERO;
                        }

                        kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                        logger.debug("kosKerosakanInfra tidak null disini " + kosKerosakanInfra);
                        if (kosKerosakanInfra == null) {
                            logger.debug("kosKerosakanInfra null disini " + kosKerosakanInfra);
                            kosKerosakanInfra = BigDecimal.ZERO;
                        }

                        kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                        logger.debug("kosKerosakanLain tidak null disini " + kosKerosakanLain);
                        if (kosKerosakanLain == null) {
                            logger.debug("kosKerosakanLain null disini " + kosKerosakanLain);
                            kosKerosakanLain = BigDecimal.ZERO;
                        }

                        kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                        logger.debug("kosKecacatanTanah tidak null disini " + kosKecacatanTanah);
                        if (kosKecacatanTanah == null) {
                            logger.debug("kosKecacatanTanah null disini " + kosKecacatanTanah);
                            kosKecacatanTanah = BigDecimal.ZERO;
                        }

                        sum = kosKerosakanTanah.add(kosKerosakanBangunan.add(kosKerosakanPokok.add(kosKerosakanInfra.add(kosKerosakanLain.add(kosKecacatanTanah)))));
                        logger.debug("sum total " + sum);
                    }
                }
            }
        }

//        if(getContext().getRequest().getParameter("simpanLaporan").equalsIgnoreCase("false")){
//            simpanLaporan = Boolean.FALSE;
//        }
//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/pengambilan_pulih_details.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandunganPendeposit() {

//        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);
        pihak = pihakDAO.findById(idPihak);
//        permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
        permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
        akaun = pengambilanService.findAkaunBynoAkaun(idPermohonan);
        logger.debug("id Pihak" + idPihak);

        InfoAudit audit = new InfoAudit();
        if (permohonanPihakPendeposit == null) {
            audit.setDimasukOleh(pguna);
            audit.setTarikhMasuk(new java.util.Date());
            audit.setDiKemaskiniOleh(pguna);
            permohonanPihakPendeposit = new PermohonanPihakPendeposit();
        } else {
            audit = permohonanPihakPendeposit.getInfoAudit();
            audit.setDiKemaskiniOleh(pguna);
            audit.setTarikhKemaskini(new java.util.Date());
        }
        permohonanPihakPendeposit.setPihak(pihak);
        permohonanPihakPendeposit.setPermohonan(permohonan);
        permohonanPihakPendeposit.setNoAkaun(noAkaun);
        permohonanPihakPendeposit.setAmaun(sum);
//            permohonanPihakPendepositTemp.setAmaun(new BigDecimal(amaun));
        permohonanPihakPendeposit.setBank(kodBank);
//            permohonanPihakPendepositTemp.setBank(kodBankDAO.findById(getContext().getRequest().getParameter("jenisBank")));
        permohonanPihakPendeposit.setCaraBayaran(getContext().getRequest().getParameter("caraBayaran"));
        if (catatan != null) {
            permohonanPihakPendeposit.setCatatan(catatan);
        }
        permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
        permohonanPihakPendeposit.setInfoAudit(audit);
        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);
//        pelupusanService.kemaskiniPermohonanPihakPendeposit(permohonanPihakPendeposit);


        if (pihak != null) {
            logger.debug("pihak x empty::update");

            pihak.setNama(pihak.getNama());
            pihak.setJenisPengenalan(kodJenisPengenalan);
            pihak.setNoPengenalan(getContext().getRequest().getParameter("pihak.noPengenalan"));
            pihak.setAlamat1(getContext().getRequest().getParameter("pihak.alamat1"));
            pihak.setAlamat2(getContext().getRequest().getParameter("pihak.alamat2"));
            pihak.setAlamat3(getContext().getRequest().getParameter("pihak.alamat3"));
            pihak.setAlamat4(getContext().getRequest().getParameter("pihak.alamat4"));
            pihak.setPoskod(getContext().getRequest().getParameter("pihak.poskod"));
            kodNegeri = conf.getProperty("kodNegeri");
            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
            pihak.setNegeri(kodN);
//            pihak.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
//            pihak.setNoTelefon1(pihak.getNoTelefon1());
            pihak.setNoTelefon1(getContext().getRequest().getParameter("pihak.noTelefon1"));
            pihak.setEmail(getContext().getRequest().getParameter("pihak.email"));
            pihak.setInfoAudit(audit);
            pelupusanService.saveOrUpdate(pihak);
        } else {
            logger.debug("pihak empty::save new");
            Pihak pihakTemp = new Pihak();
            pihakTemp.setNama(pihak.getNama());
            pihakTemp.setJenisPengenalan(kodJenisPengenalan);
            pihakTemp.setNoPengenalan(getContext().getRequest().getParameter("pihak.noPengenalan"));
            pihakTemp.setAlamat1(getContext().getRequest().getParameter("pihak.alamat1"));
            pihakTemp.setAlamat2(getContext().getRequest().getParameter("pihak.alamat2"));
            pihakTemp.setAlamat3(getContext().getRequest().getParameter("pihak.alamat3"));
            pihakTemp.setAlamat4(getContext().getRequest().getParameter("pihak.alamat4"));
            pihakTemp.setPoskod(getContext().getRequest().getParameter("pihak.poskod"));
            kodNegeri = conf.getProperty("kodNegeri");
            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
            pihak.setNegeri(kodN);
//            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setInfoAudit(audit);
            pelupusanService.saveOrUpdate(pihakTemp);
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
//        pihakDetails();
        rehydrate();
        addSimpleMessage("Maklumat telah Berjaya disimpan");

        showPulih = true;
        return new JSP("pengambilan/pengambilan_pulih_details.jsp").addParameter("tab", "true");
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

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
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

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

//    public String getKodBank() {
//        return kodBank;
//    }
//
//    public void setKodBank(String kodBank) {
//        this.kodBank = kodBank;
//    }
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

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public boolean isShowPulih() {
        return showPulih;
    }

    public void setShowPulih(boolean showPulih) {
        this.showPulih = showPulih;
    }

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public BigDecimal getKosKecacatanTanah() {
        return kosKecacatanTanah;
    }

    public void setKosKecacatanTanah(BigDecimal kosKecacatanTanah) {
        this.kosKecacatanTanah = kosKecacatanTanah;
    }

    public BigDecimal getKosKerosakanBangunan() {
        return kosKerosakanBangunan;
    }

    public void setKosKerosakanBangunan(BigDecimal kosKerosakanBangunan) {
        this.kosKerosakanBangunan = kosKerosakanBangunan;
    }

    public BigDecimal getKosKerosakanInfra() {
        return kosKerosakanInfra;
    }

    public void setKosKerosakanInfra(BigDecimal kosKerosakanInfra) {
        this.kosKerosakanInfra = kosKerosakanInfra;
    }

    public BigDecimal getKosKerosakanLain() {
        return kosKerosakanLain;
    }

    public void setKosKerosakanLain(BigDecimal kosKerosakanLain) {
        this.kosKerosakanLain = kosKerosakanLain;
    }

    public BigDecimal getKosKerosakanPokok() {
        return kosKerosakanPokok;
    }

    public void setKosKerosakanPokok(BigDecimal kosKerosakanPokok) {
        this.kosKerosakanPokok = kosKerosakanPokok;
    }

    public BigDecimal getKosKerosakanTanah() {
        return kosKerosakanTanah;
    }

    public void setKosKerosakanTanah(BigDecimal kosKerosakanTanah) {
        this.kosKerosakanTanah = kosKerosakanTanah;
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanah() {
        return laporanPemulihanTanah;
    }

    public void setLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah) {
        this.laporanPemulihanTanah = laporanPemulihanTanah;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
