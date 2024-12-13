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
import etanah.model.KodBank;
import etanah.model.KodJenisPengenalan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
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
 * @author User
 */
@UrlBinding("/pengambilan/depositMahkamah")
public class PengambilanPendepositActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(PengambilanPendepositActionBean.class);
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
    PelupusanService pelupusanService;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    PihakDAO pihakDAO;
    private etanah.Configuration conf;
    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private KodJenisPengenalan kodJenisPengenalan;
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
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList2;
    private List<PermohonanPihak> permohonanPihakList;
    private List<PermohonanPihak> permohonanPemohonList;
    private List<Pemohon> listPemohon;
    private Pihak pihak;
    private Hakmilik hakmilik;
    private KodBank kodBank;
    etanahActionBeanContext ctx;
//    private String kodBank;
    private String noAkaun;
    private String amaun;
    private String catatan;
    private String jenisBank;
    private String caraBayaran;
    private Akaun akaun;
    private String kodNegeri;

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

        return new JSP("pengambilan/pengambilan_deposit_klik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        if(idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            if(permohonan != null) {
//                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
//            }
//        }

        return new JSP("pengambilan/pengambilan_deposit_klik2.jsp").addParameter("tab", "true");
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

    public Resolution showFormPapar() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        if (idPermohonan != null) {
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), pemohon.getPihak().getIdPihak());
                        if (senaraiPermohonanPihak.size() > 0) {
                            permohonanPihak = senaraiPermohonanPihak.get(0);
                            logger.debug("permohonanPihak " + permohonanPihak.getIdPermohonanPihak());
                        }
                    }
                }
            }

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
//            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
            permohonanPihakList = pengambilanService.getPermohonanPihakByidMohonList1(idPermohonan);
            for (PermohonanPihak pp : permohonanPihakList) {
                permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, pp.getPihak().getIdPihak());
                logger.info("id mohon : " + idPermohonan);
                logger.info("id pihak : " + pp.getPihak().getIdPihak());
                if (permohonanPihakPendeposit == null) {
                    logger.debug("permohonanPihakPendeposit null");
                } else {
                    logger.info("mohon deposit x null");
                    pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                    noAkaun = permohonanPihakPendeposit.getNoAkaun();
                    amaun = permohonanPihakPendeposit.getAmaun().toString();
                    catatan = permohonanPihakPendeposit.getCatatan();
                    caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                    kodBank = permohonanPihakPendeposit.getBank();
                }
                if (pihak == null) {
                } else {
                    kodJenisPengenalan = pihak.getJenisPengenalan();
                }
            }
        }

        return new JSP("pengambilan/pengambilan_deposit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");


        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            listPemohon = pengambilanService.findListPemohon(permohonan.getIdPermohonan());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
            permohonanPihakPendepositList = pelupusanService.findListPermohonanPihakPendepositByIdPermohonan(idPermohonan);
//            permohonanPihakPendeposit = pengambilanService.findByPendeposit(idPermohonan);
            permohonanPihakList = pengambilanService.getPermohonanPihakByidMohonList1(idPermohonan);
            if (kodNegeri.equals("04")) {
                if (permohonan.getPermohonanSebelum() != null) {
                    permohonanPemohonList = pengambilanService.getPermohonanPihakPemohon(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    permohonanPemohonList = pengambilanService.getPermohonanPihakPemohon(permohonan.getIdPermohonan());
                }
                System.out.println("Saiz pemohon >> " + permohonanPemohonList.size());
            } else {
                permohonanPemohonList = pengambilanService.getPermohonanPihakPemohon(permohonan.getIdPermohonan());
//                System.out.println("Saiz pemohon >> " + permohonanPemohonList.size());
            }
            for (PermohonanPihak pp : permohonanPihakList) {
                permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, pp.getPihak().getIdPihak());
                logger.info("id mohon : " + idPermohonan);
                logger.info("id pihak : " + pp.getPihak().getIdPihak());
                if (permohonanPihakPendeposit == null) {
                    logger.debug("permohonanPihakPendeposit null");
                } else {
                    logger.info("mohon deposit x null");
                }
            }

            for (PermohonanPihak pp : permohonanPemohonList) {
                permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, pp.getPihak().getIdPihak());
                logger.info("id mohon : " + idPermohonan);
                logger.info("id pihak : " + pp.getPihak().getIdPihak());
                if (permohonanPihakPendeposit == null) {
                    logger.debug("permohonanPihakPendeposit null");
                } else {
                    logger.info("mohon deposit x null");
                }
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
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            if (permohonanPihakPendeposit == null) {
                logger.info("permohonanPihakPendeposit is null");

            } else {
                pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                noAkaun = permohonanPihakPendeposit.getNoAkaun();
                amaun = permohonanPihakPendeposit.getAmaun().toString();
                catatan = permohonanPihakPendeposit.getCatatan();
                caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                kodBank = permohonanPihakPendeposit.getBank();
//                if (permohonanPihakPendeposit.getBank() != null) {
//                    jenisBank = permohonanPihakPendeposit.getBank().getKod();
//                }
            }
            if (pihak == null) {
            } else {
                kodJenisPengenalan = pihak.getJenisPengenalan();
            }
        }

//        if(getContext().getRequest().getParameter("simpanLaporan").equalsIgnoreCase("false")){
//            simpanLaporan = Boolean.FALSE;
//        }
//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/pengambilan_deposit_details.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        String idHakmilik = (getContext().getRequest().getParameter("idHakmilik"));
        logger.debug("idHakmilik ::" + idHakmilik);



        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);

        }


        return new JSP("pengambilan/pengambilan_deposit_pemohon_details.jsp").addParameter("tab", "true");
    }

    public Resolution bankDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);



        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        logger.debug("idPihak ::" + idPihak);

        if (idPermohonan != null) {
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            pihak = pihakDAO.findById(idPihak);
            permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
            if (permohonanPihakPendeposit == null) {
                logger.info("permohonanPihakPendeposit is null");

            } else {
                pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                noAkaun = permohonanPihakPendeposit.getNoAkaun();
                amaun = permohonanPihakPendeposit.getAmaun().toString();
                catatan = permohonanPihakPendeposit.getCatatan();
                caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                kodBank = permohonanPihakPendeposit.getBank();

            }
            if (pihak == null) {
            } else {
                kodJenisPengenalan = pihak.getJenisPengenalan();
            }
        }


        return new JSP("pengambilan/pengambilan_deposit_pemohon_details.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

//        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null) {
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), pemohon.getPihak().getIdPihak());
                        if (senaraiPermohonanPihak.size() > 0) {
                            permohonanPihak = senaraiPermohonanPihak.get(0);
                            logger.debug("permohonanPihak " + permohonanPihak.getIdPermohonanPihak());
                        }
                    }
                }
            }

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            permohonanPihakList = pengambilanService.getPermohonanPihakByidMohonList1(idPermohonan);

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            pihak = pihakDAO.findById(idPihak);

            for (PermohonanPihak pp : permohonanPihakList) {
                permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
                logger.info("id mohon : " + idPermohonan);
                logger.info("id pihak : " + pp.getPihak().getIdPihak());
                if (permohonanPihakPendeposit == null) {
                    logger.info("permohonanPihakPendeposit is null");

                } else {
                    logger.info("mohon deposit xnull");
                    pihak = pihakDAO.findById(permohonanPihakPendeposit.getPihak().getIdPihak());
                    noAkaun = permohonanPihakPendeposit.getNoAkaun();
                    amaun = permohonanPihakPendeposit.getAmaun().toString();
                    catatan = permohonanPihakPendeposit.getCatatan();
                    caraBayaran = permohonanPihakPendeposit.getCaraBayaran();
                    kodBank = permohonanPihakPendeposit.getBank();

                }
                if (pihak == null) {
                } else {
                    kodJenisPengenalan = pihak.getJenisPengenalan();
                }

            }
        }

        return new JSP("pengambilan/pengambilan_deposit_pemohon_details.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKandunganPendeposit() {

//        ctx = (etanahActionBeanContext) getContext();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        permohonan = permohonanDAO.findById(idPermohonan);
        pihak = pihakDAO.findById(idPihak);
        permohonanPihakPendepositList2 = pengambilanService.findByPendepositList(idPermohonan);
        permohonanPihakPendeposit = pengambilanService.findByIdMohonPendeposit(idPermohonan, idPihak);
        akaun = pengambilanService.findAkaunBynoAkaun(idPermohonan);
        logger.debug("id Pihak" + idPihak);

        Akaun akaun = new Akaun();
        akaun = pengambilanService.findAkaunBynoAkaun(idPermohonan);

        BigDecimal jumlahDeposit = new BigDecimal(0);
        BigDecimal jumlahDeposit2 = new BigDecimal(0);
        if (permohonan.getKodUrusan().getKod().equals("BMAHK")) {
            logger.info("urusan BMAHK Melaka");
        } else {
            try {
                BigDecimal jumlahDepositSbnr = akaun.getBaki().multiply(new BigDecimal(-1));
                logger.info("permohonanPihakPendepositList2.size() >> " + permohonanPihakPendepositList2.size());
                if (permohonanPihakPendepositList2.size() > 0) {
                    for (PermohonanPihakPendeposit pendeposit : permohonanPihakPendepositList2) {
                        if (pendeposit.getPihak().getIdPihak() != idPihak) {
                            jumlahDeposit2 = jumlahDeposit.add(pendeposit.getAmaun());
                        }
                    }
                }
                logger.info("amaun >> " + amaun);
                BigDecimal jumDep = jumlahDeposit.add(new BigDecimal(amaun)).add(jumlahDeposit2);
                logger.info("jumlah Deposit " + jumDep);
                logger.info("jumlah jumlahDepositSbnr " + jumlahDepositSbnr);
                if (jumDep.compareTo(jumlahDepositSbnr) > 0) {
                    rehydrate();
                    addSimpleError("Jumlah Amaun Deposit Tidak boleh melebihi daripada baki deposit iaitu sebanyak RM " + jumlahDepositSbnr);
                    return new JSP("pengambilan/pengambilan_deposit_details.jsp").addParameter("tab", "true");
                }
            } catch (Exception j) {
            }
        }

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
        permohonanPihakPendeposit.setAmaun(new BigDecimal(amaun));
        permohonanPihakPendeposit.setBank(kodBank);
        permohonanPihakPendeposit.setCaraBayaran(getContext().getRequest().getParameter("caraBayaran"));
        if (catatan != null) {
            permohonanPihakPendeposit.setCatatan(catatan);
        }
        permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
        permohonanPihakPendeposit.setInfoAudit(audit);
        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);

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
//            kodNegeri = conf.getProperty("kodNegeri");
//            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
//            pihak.setNegeri(kodN);
            pihak.setNegeri(kodNegeriDAO.findById("04"));
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
//            kodNegeri = conf.getProperty("kodNegeri");
//            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
//            pihak.setNegeri(kodN);
            pihak.setNegeri(kodNegeriDAO.findById("04"));
//            pihakTemp.setNegeri(kodNegeriDAO.findById(pihak.getNegeri().getKod()));
            pihakTemp.setNoTelefon1(pihak.getNoTelefon1());
            pihakTemp.setEmail(pihak.getEmail());
            pihakTemp.setInfoAudit(audit);
            pelupusanService.saveOrUpdate(pihakTemp);
        }

        if (akaun != null) {
            System.out.println("akaun tak null");
            if (akaun.getPemegang() == null) {
                InfoAudit info = new InfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());

                akaun.setPemegang(pihak);
                akaun.setInfoAudit(info);
                pengambilanService.simpanAkaun(akaun);

            }
        } else {
            Akaun akaun2 = new Akaun();
            System.out.println("akaun null : simpan");
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(pguna);
            info.setTarikhMasuk(new java.util.Date());
            akaun2.setPermohonan(permohonan);
            akaun2.setNoAkaun(idPermohonan);
            KodAkaun akaun1 = pengambilanService.findKodAkaun("AD");
            KodStatusAkaun kodStatusAkaun = pengambilanService.findKodStatusAkaun("A");
            akaun2.setCawangan(permohonan.getCawangan());
            akaun2.setStatus(kodStatusAkaun);
            akaun2.setKodAkaun(akaun1);
            akaun2.setBaki(new BigDecimal(amaun));
            akaun2.setPemegang(pihak);
            akaun2.setInfoAudit(info);
            pengambilanService.simpanAkaun(akaun2);
        }
//        pihakDetails();
        rehydrate();
        addSimpleMessage("Maklumat telah Berjaya disimpan");

        return new JSP("pengambilan/pengambilan_deposit_details.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPendeposit() {

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
        permohonanPihakPendeposit.setAmaun(new BigDecimal(amaun));
        permohonanPihakPendeposit.setBank(kodBank);
        permohonanPihakPendeposit.setCaraBayaran(getContext().getRequest().getParameter("caraBayaran"));
        if (catatan != null) {
            permohonanPihakPendeposit.setCatatan(catatan);
        }
        permohonanPihakPendeposit.setCawangan(permohonan.getCawangan());
        permohonanPihakPendeposit.setInfoAudit(audit);
        pelupusanService.simpanPermohonanPihakPendeposit(permohonanPihakPendeposit);

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
//            kodNegeri = conf.getProperty("kodNegeri");
//            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
//            pihak.setNegeri(kodN);
            pihak.setNegeri(kodNegeriDAO.findById("04"));
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
//            kodNegeri = conf.getProperty("kodNegeri");
//            KodNegeri kodN = kodNegeriDAO.findById(kodNegeri);
//            pihak.setNegeri(kodN);
            pihak.setNegeri(kodNegeriDAO.findById("04"));
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
        String idHakmilikTuanTanah = (getContext().getRequest().getParameter("idHakmilikTuanTanah"));
        logger.debug("idHakmilikTuanTanah ::" + idHakmilikTuanTanah);



        if (idHakmilikTuanTanah != null) {
            hakmilik = hakmilikDAO.findById(idHakmilikTuanTanah);

        }
        addSimpleMessage("Maklumat telah Berjaya disimpan");

        return new JSP("pengambilan/pengambilan_deposit_pemohon_details.jsp").addParameter("tab", "true");
    }

    public Akaun getAkaun() {
        return akaun;
    }

    public void setAkaun(Akaun akaun) {
        this.akaun = akaun;
    }

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

    public KodJenisPengenalan getKodJenisPengenalan() {
        return kodJenisPengenalan;
    }

    public void setKodJenisPengenalan(KodJenisPengenalan kodJenisPengenalan) {
        this.kodJenisPengenalan = kodJenisPengenalan;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public List<PermohonanPihak> getPermohonanPemohonList() {
        return permohonanPemohonList;
    }

    public void setPermohonanPemohonList(List<PermohonanPihak> permohonanPemohonList) {
        this.permohonanPemohonList = permohonanPemohonList;
    }
}
