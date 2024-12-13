/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.BadanPengurusan;
import etanah.service.common.PihakService;
import etanah.service.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.*;
import etanah.view.*;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Mudd
 */
@UrlBinding("/utiliti/datacollection")
public class UtilityDataCollectionActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakService pihakService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    BangunanTingkatDAO bangunanTingkatDAO;
    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    KodKategoriBangunanDAO kodKategoriBangunanDAO;
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
//    private PermohonanBangunanDAO permohonanBangunanDAO;
    private KodUrusanDAO kodUrusanDAO;
    private BangunanPetakDAO bangunanPetakDAO;
//    private BangunanTingkatDAO bangunanTingkatDAO;
    private PermohonanBangunan permohonanBangunan;
//    private KodKategoriBangunanDAO kodKategoriBangunanDAO;
//    private KodKegunaanPetakDAO kodKegunaanPetakDAO;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPihakBerkepentingan bndUrus;
    private Hakmilik hakmilik;
    private PermohonanSkim permohonanSkim;
    private KodCawangan kodcaw;
//    private KodKategoriBangunan kodKategoriBangunan;
    private String nama;
    private String idPermohonan;
    private String noSkim;
    private String idPihak;
    private String kodKegunaanPetak;
//    private KodKegunaanPetak kodKegunaanPetak;
//    private KodUrusan kodUrusan;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    StrataPtService strataPtService;
    private List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
    private List<Hakmilik> senaraiNoBangunan;
    private List<Hakmilik> senaraiKatgBangunan = new ArrayList<Hakmilik>();
    private List<HakmilikPetakAksesori> petakAks = new ArrayList<HakmilikPetakAksesori>();
    private List<PermohonanBangunan> senaraipermohonanBangunan = new ArrayList<PermohonanBangunan>();
    private List<Hakmilik> spetaktgkt = new ArrayList<Hakmilik>();
    private List<Hakmilik> snamamax = new ArrayList<Hakmilik>();
    private List<Pihak> senaraiPihak;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
//    private List<PermohonanBangunan> senaraiPermohonanBangunan = new ArrayList<PermohonanBangunan>();
    private List<BangunanTingkat> senaraiBangunanTingkat = new ArrayList<BangunanTingkat>();
    private static final Logger LOG = Logger.getLogger(UtilityDataCollectionActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        noSkim = null;
        nama = null;
        kodKegunaanPetak = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        showForm();
    }

//    public Resolution search() {
//
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        noSkim = (String) getContext().getRequest().getParameter("noSkim");
//
//        LOG.debug("--- Search START ---");
//        if (noSkim == null) {
//            LOG.debug("======== noSkim" + noSkim);
//            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
//        }
//        LOG.debug("======== noSkim :" + noSkim);
//        String kodUrusan = "PBBS";
//        LOG.debug("======== kodUrusan :" + kodUrusan);
//        KodUrusan kodUrusan1 = strataPtService.getUrusan(kodUrusan);
////        noSkim = hakmilik.getNoSkim();
//        LOG.debug("======== kodUrusan" + kodUrusan1);
////        hakmilik = hakmilikDAO.findById(noSkim);
//        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
//
//        if (senaraiHakmilik == null) {
//            addSimpleError("No Skim " + noSkim + " tidak dijumpai.");
//            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
//        } else {
//            if (senaraiHakmilik != null) {
//                senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
//                LOG.debug("--- senarai hakmilik ---" + senaraiHakmilik.size());
//                LOG.debug("--- initiate id mohon ---");
////                permohonan = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow5(kodUrusan1, pengguna, senaraiHakmilik, noSkim);
//            }
//        }
//        addSimpleMessage("ID Permohonan Telah Berjaya Dijana.");
//        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
//    }
    public Resolution mhnbngn() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        kodUrusan = (KodUrusan) getContext().getRequest().getParameter("kodUrusan");
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");

//        hakmilik = hakmilikDAO.findById(hakmilik.getNoSkim());
        kodcaw = pengguna.getKodCawangan();
        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
        senaraiHakmilikPermohonan = strataPtService.findIdPBBSByIdHakmilik(senaraiHakmilik.get(1).getIdHakmilikInduk());

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
//        long idInduk = Long.parseLong(senaraiHakmilik.get(1).getIdHakmilikInduk());

        senaraiNoBangunan = strataPtService.findNoBangunan(senaraiHakmilik.get(1).getIdHakmilikInduk());
        senaraiKatgBangunan = strataPtService.findKatgBangunan(senaraiHakmilik.get(1).getIdHakmilikInduk());
        LOG.debug("--- senaraiNoBangunan ---" + senaraiNoBangunan);
        LOG.debug("--- senaraiKatgBangunan ---" + senaraiKatgBangunan);
        for (int i = 0; i < senaraiNoBangunan.size(); i++) {
            LOG.debug("--- saving to mohon bangunan ---");
            String senaraiNoBangunan2 = String.valueOf(senaraiNoBangunan.get(i));
            String senaraiKatgBangunan2 = String.valueOf(senaraiKatgBangunan.get(0));
            LOG.debug("--- senaraiNoBangunan ---" + senaraiNoBangunan2);
//            LOG.debug("--- senaraiNoBangunan ---" + senaraiNoBangunan.get(i).toString());
            LOG.debug("--- senaraiKatgBangunan ---" + senaraiKatgBangunan2);

            PermohonanBangunan pb = new PermohonanBangunan();
            Long idbgnl = strataPtService.findMohonBngnMax();
            if (idbgnl == null) {
                pb.setIdBangunan(1);
            } else {
                LOG.info("PermohonanBangunan max==" + idbgnl);
                Integer idbgn = Integer.parseInt(String.valueOf(idbgnl));
                idbgn = idbgn + 1;
                pb.setIdBangunan(idbgn);
            }
            pb.setInfoAudit(ia);
            pb.setCawangan(kodcaw);
            pb.setNama(senaraiNoBangunan2);

            Integer tingkat = strataPtService.findTgktMax(senaraiNoBangunan2, noSkim);
            LOG.info("Stingkat -- " + tingkat);
            Long petaks = strataPtService.findCountPetak(senaraiNoBangunan2, noSkim);
            LOG.info("Spetaks -- " + petaks);
            BigDecimal syerbloks = strataPtService.findSumSyerTingkat(senaraiNoBangunan2, noSkim);
            LOG.info("Ssyerbloks -- " + syerbloks);

//            Integer petak = Integer.valueOf(spetak);
//            Integer tingkat = Integer.parseInt(Stingkat);
//            String petak = String.valueOf(spetak);
            Integer syerblok = syerbloks.intValue();
            Integer petak = Integer.parseInt(String.valueOf(petaks));


            KodKategoriBangunan kodKategoriBangunan = new KodKategoriBangunan();
            if (senaraiNoBangunan2.contains("M")) {
                kodKategoriBangunan = kodKategoriBangunanDAO.findById("B");
                LOG.debug("--- kodKategoriBangunann M---" + kodKategoriBangunan.getKod());
                pb.setKodKategoriBangunan(kodKategoriBangunan);
                pb.setBilanganTingkat(tingkat);
            }
            if (senaraiNoBangunan2.contains("L")) {
                kodKategoriBangunan = kodKategoriBangunanDAO.findById("L");
                LOG.debug("--- kodKategoriBangunann L---" + kodKategoriBangunan.getKod());
                pb.setKodKategoriBangunan(kodKategoriBangunan);
                pb.setBilanganTingkat(1);
            }
            if (senaraiNoBangunan2.contains("P")) {
                kodKategoriBangunan = kodKategoriBangunanDAO.findById("P");
                LOG.debug("--- kodKategoriBangunann P---" + kodKategoriBangunan.getKod());
                pb.setKodKategoriBangunan(kodKategoriBangunan);
                pb.setBilanganTingkat(tingkat);
            }
            permohonanSkim = strataPtService.findbyIdMh(senaraiHakmilik.get(1).getIdHakmilikInduk());
            HakmilikPermohonan hp2 = strataPtService.findIdHakmilik(senaraiHakmilik.get(0).getIdHakmilikInduk());
            if (hp2 != null) {
                pb.setPermohonan(hp2.getPermohonan());
                pb.setKodKegunaanBangunan(senaraiHakmilik.get(0).getKodKegunaanBangunan());
            }
//            LOG.debug("--- mohon bangunan ---" + senaraiNoBangunan.toString() + tingkat + petak + syerblok);
            pb.setSyerBlok(syerblok);
            pb.setBilanganPetak(petak);
            pb.setPermohonanSkim(permohonanSkim);
            LOG.info(hp2.getPermohonan() + " " + kodcaw + " " + senaraiNoBangunan2 + " " + tingkat + " " + syerblok + " " + petak + " " + permohonanSkim.getNoSkim() + " " + senaraiHakmilik.get(0).getKodKegunaanBangunan());
            strataPtService.save(pb);
        }
        dlmbngn();
        addSimpleMessage("Data Mohon Bangunan Telah Berjaya Dikemaskini");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
    }

    public Resolution StrbdnUrus() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kc = peng.getKodCawangan().getKod();
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        LOG.info("noSkim : " + noSkim);
        LOG.info("kodKegunaanPetak : " + kodKegunaanPetak);
        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);

        if (nama != null) {
            senaraiPihak = searchBdnUrus(nama);
            LOG.info("senaraiPihak.size : " + senaraiPihak.size());
            if (senaraiPihak.size() < 1) {
                addSimpleError("Perbadanan Pengurusan Tidak Dijumpai");
            } else {
                if (senaraiHakmilik.size() == 0) {
                    addSimpleError("No Skim " + noSkim + " Tidak Dijumpai.");
                    return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
                } else {
                    HakmilikPermohonan hp = strataPtService.findIdHakmilik(senaraiHakmilik.get(0).getIdHakmilikInduk());
                    if (hp == null) {
                        LOG.info("senaraiHakmilik.size : " + senaraiHakmilik.size());
                        String induk = senaraiHakmilik.get(0).getIdHakmilikInduk();
                        bndUrus = strataPtService.findPihakPengurusan(induk);
                    } else {
                        senaraipermohonanBangunan = strataPtService.checkMhnBangunanExist(hp.getPermohonan().getIdPermohonan());
                        addSimpleError("Id Permohonan " + hp.getPermohonan().getIdPermohonan() + " Bagi No Skim " + noSkim + " Telah Dijana.");
                        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
                    }
                }
            }
        }
//        addSimpleMessage("Data Strata Badan Urus Telah Berjaya Dikemaskini");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
    }

    public Resolution simpanPihak() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kc = peng.getKodCawangan().getKod();
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        LOG.info("noSkim : " + noSkim);
        LOG.info("kodKegunaanPetak : " + kodKegunaanPetak);
        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
        idPihak = getContext().getRequest().getParameter("idPihak");
        LOG.info("== CHECK IDPIHAK : " + idPihak);

        String kodUrusan = "PBBS";
        LOG.debug("======== kodUrusan :" + kodUrusan);
        KodUrusan kodUrusan1 = strataPtService.getUrusan(kodUrusan);

        LOG.debug("--- initiate id mohon ---");
        permohonan = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow5(kodUrusan1, peng, senaraiHakmilik, noSkim, idPihak);
        LOG.debug("--- id mohon generated ---");

        for (int x = 0; x < senaraiHakmilik.size(); x++) {
            LOG.info("updated id bdn hakmilik");
//                Permohonan idPermohonan = strataPtService.findPermohonanSblm(permohonan.getIdPermohonan());
            BadanPengurusan sbu = strataPtService.findBdn(permohonan.getIdPermohonan());
            Long idBadan = sbu.getIdBadan();
            BadanPengurusan idbdn = strataPtService.findBdnByIdBdn(idBadan);
            Hakmilik hk2 = strataPtService.findInfoByIdHakmilik(senaraiHakmilik.get(x).getIdHakmilik());

            strataPtService.UpdatedIdBdn(idBadan, senaraiHakmilik.get(x).getIdHakmilikInduk());
//                hk2.setBadanPengurusan(idbdn);
//                hk2.setLuas(BigDecimal.ZERO);
////                hk2.setKodUnitLuas(kodUnitLuasDAO.);
//                hk2.setTempohPegangan(0);
//                hk2.setInfoAudit(ia);
//                hakmilikDAO.saveOrUpdate(hk2);
            LOG.info("id badan " + idbdn.getIdBadan());
            x = senaraiHakmilik.size();
        }
        LOG.debug("--- simpan id bdn ---");
//        mhnbngn();
//        addSimpleError("Perbadanan Pengurusan Tidak Dijumpai");
        addSimpleMessage("ID Permohonan Telah Berjaya Dijana.");
//        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
    }

    public Resolution simpanPihak1() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        idPihak = getContext().getRequest().getParameter("idPihak");
        LOG.info("== CHECK IDPIHAK : " + idPihak);

        LOG.debug("--- Search START ---");
        if (noSkim == null) {
            LOG.debug("======== noSkim" + noSkim);
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
        }
        LOG.debug("======== noSkim :" + noSkim);
        String kodUrusan = "PBBS";
        LOG.debug("======== kodUrusan :" + kodUrusan);
        KodUrusan kodUrusan1 = strataPtService.getUrusan(kodUrusan);
//        noSkim = hakmilik.getNoSkim();
        LOG.debug("======== kodUrusan" + kodUrusan1);
//        hakmilik = hakmilikDAO.findById(noSkim);
        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);

        LOG.debug("--- initiate id mohon ---");
        permohonan = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow5(kodUrusan1, pengguna, senaraiHakmilik, noSkim, idPihak);
        LOG.debug("--- id mohon generated ---");
//                for (int x = 0; x < senaraiHakmilik.size(); x++) {
//                    LOG.info("updated id bdn hakmilik");
////                Permohonan idPermohonan = strataPtService.findPermohonanSblm(permohonan.getIdPermohonan());
//                    BadanPengurusan sbu = strataPtService.findBdn(permohonan.getIdPermohonan());
//                    Long idBadan = sbu.getIdBadan();
//                    BadanPengurusan idbdn = strataPtService.findBdnByIdBdn(idBadan);
//                    Hakmilik hk2 = strataPtService.findInfoByIdHakmilik(senaraiHakmilik.get(x).getIdHakmilik());
//
//                    strataPtService.UpdatedIdBdn(idBadan, senaraiHakmilik.get(x).getIdHakmilik());
////                hk2.setBadanPengurusan(idbdn);
////                hk2.setLuas(BigDecimal.ZERO);
//////                hk2.setKodUnitLuas(kodUnitLuasDAO.);
////                hk2.setTempohPegangan(0);
////                hk2.setInfoAudit(ia);
////                hakmilikDAO.saveOrUpdate(hk2);
//                    LOG.info("id badan " + idbdn.getIdBadan());
//                }


//        showForm();
        addSimpleMessage("ID Permohonan Telah Berjaya Dijana.");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata2.jsp");
    }

    public List<Pihak> searchBdnUrus(String nama) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.Pihak u where ";

            if (nama != null) {
                query += "lower(u.nama) LIKE :nama ";
            }
            Query q = sessionProvider.get().createQuery(query);

            if (nama != null) {
                q.setString("nama", "%" + nama.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public Resolution StrbdnUruspage() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
    }

    public Resolution StrbdnUruspage2() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp").addParameter("popup", "true");
    }

    public Resolution dlmbngn() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noSkim = (String) getContext().getRequest().getParameter("noSkim");
        kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
        HakmilikPermohonan hp3 = strataPtService.findIdHakmilik(senaraiHakmilik.get(0).getIdHakmilikInduk());

//        hakmilik = hakmilikDAO.findById(hakmilik.getNoSkim());
//        senaraiHakmilikPermohonan = strataPtService.findIdPBBSByIdHakmilik(hakmilik.getIdHakmilikInduk());
//        kodcaw = senaraiHakmilikPermohonan.get(0).getCawangan();
//        senaraiHakmilik = strataPtService.findByNoSkim(noSkim);
        permohonanSkim = strataPtService.findbyIdMh(senaraiHakmilik.get(0).getIdHakmilikInduk());
        idPermohonan = hp3.getPermohonan().getIdPermohonan();
        LOG.debug("--- idPermohonan dlmbngn ---" + idPermohonan);
        LOG.debug("--- noSkim ---" + noSkim);
        LOG.debug("--- kodKegunaanPetak ---" + kodKegunaanPetak);
        senaraipermohonanBangunan = strataPtService.checkMhnBangunanExist(idPermohonan);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        LOG.debug("--- bngn_tgkt ---");
//        long idInduk = Long.parseLong(senaraiHakmilik.get(1).getIdHakmilikInduk());
        senaraiNoBangunan = strataPtService.findNoBangunan(senaraiHakmilik.get(1).getIdHakmilikInduk());

        for (PermohonanBangunan mohonBangunan : senaraipermohonanBangunan) {
            for (int x = 1; x <= mohonBangunan.getBilanganTingkat(); x++) {
                String biltingkat = String.valueOf(x);

                Long petaktgkt = strataPtService.findCountPetakbyTingkat(noSkim, biltingkat, mohonBangunan.getNama());
//                Integer ipetaktgkt = Integer.parseInt(petaktgkt);
                if (petaktgkt != null) {
                    Integer ipetaktgkt = Integer.parseInt(String.valueOf(petaktgkt));
                    LOG.debug("--- petaktgktt ---" + petaktgkt);

                    BangunanTingkat bt = new BangunanTingkat();
                    Long idTingkatl = strataPtService.findBngnPetakMax();
                    if (idTingkatl == null) {
                        bt.setIdTingkat(1);
                    } else {
                        LOG.info("BangunanTingkat max==" + idTingkatl);
                        Integer idPetak = Integer.parseInt(String.valueOf(idTingkatl));
                        idPetak = idPetak + 1;
                        bt.setIdTingkat(idPetak);
                    }

                    String nama = Integer.toString(x);

                    bt.setInfoAudit(ia);
                    bt.setBangunan(mohonBangunan);
                    bt.setTingkat(x);
                    bt.setNama(biltingkat);
                    bt.setBilanganPetak(ipetaktgkt);
                    LOG.info(mohonBangunan.getIdBangunan() + " " + x + " " + biltingkat + " " + ipetaktgkt);
                    strataPtService.save(bt);

                    String petak = Integer.toString(ipetaktgkt);

                    LOG.debug("--- bngn_petak ---");
                    senaraiHakmilik = strataPtService.findIdHakmilikByIdHakmilikInduk(senaraiHakmilik.get(1).getIdHakmilikInduk());
//                        KodKegunaanBangunan kodKegunaanBangunan2 = senaraiHakmilik.get(1).getKodKegunaanBangunan();
//                        String gunabngn = kodKegunaanBangunan2.getKod();
                    KodKegunaanPetak kodKegunaanPetak2 = new KodKegunaanPetak();
                    kodKegunaanPetak2 = kodKegunaanPetakDAO.findById(kodKegunaanPetak);
                    LOG.info("kod guna petak " + kodKegunaanPetak2);
                    for (int k = 1; k <= ipetaktgkt; k++) {
                        String petak1 = String.valueOf(k);

                        BangunanPetak bp = new BangunanPetak();
                        Long idPetakl = strataPtService.findBngnPetakMax();
                        if (idPetakl == null) {
                            bp.setIdPetak(1);
                        } else {
                            LOG.info("BangunanPetak max==" + idPetakl);
                            Integer idPetak = Integer.parseInt(String.valueOf(idPetakl));
                            idPetak = idPetak + 1;
                            bp.setIdPetak(idPetak);
                        }
                        if (x == 1) {
                            Hakmilik petakInfo = strataPtService.findNoPetak(noSkim, mohonBangunan.getNama(), nama, petak1);
                            if (petakInfo != null) {
                                bp.setNama(petakInfo.getNoPetak());
                                bp.setLuas(petakInfo.getLuas());
                                bp.setSyer(petakInfo.getUnitSyer().intValue());
                                bp.setInfoAudit(ia);
                                bp.setTingkat(bt);
                                bp.setKegunaan(kodKegunaanPetak2);
                                strataPtService.save(bp);

                                petakAks = strataPtService.findBngnAks(petakInfo.getIdHakmilik());
                                if (petakAks.size() > 0) {
                                    for (HakmilikPetakAksesori hpa : petakAks) {
                                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById("01");
                                        BangunanPetakAksesori pAks = new BangunanPetakAksesori();
                                        pAks.setBangunan(mohonBangunan);
                                        pAks.setTingkat(bt);
                                        pAks.setPetak(bp);
                                        pAks.setNama(hpa.getNama());
                                        if (kodKegunaanPetakAksesori != null) {
                                            pAks.setKegunaan(kodKegunaanPetakAksesori);
                                        }
                                        else {
                                            pAks.setKegunaan(null);
                                        }
                                        pAks.setLokasi(hpa.getLokasi());
                                        pAks.setCawangan(pengguna.getKodCawangan());
                                        pAks.setInfoAudit(ia);
                                        strataPtService.save(pAks);
                                    }
                                }
                            } else {
                                Integer petakMax = strataPtService.findPetakMaxBngnPetak(mohonBangunan.getPermohonan().getIdPermohonan());
                                petakMax = petakMax + 1;
                                String petaks = String.valueOf(petakMax);
                                LOG.info("petak max untuk selain m1==" + noSkim + mohonBangunan.getNama() + nama + petaks);
                                Hakmilik petakInfo1 = strataPtService.findNoPetak(noSkim, mohonBangunan.getNama(), nama, petaks);
                                bp.setNama(petakInfo1.getNoPetak());
                                bp.setLuas(petakInfo1.getLuas());
                                bp.setSyer(petakInfo1.getUnitSyer().intValue());
                                bp.setInfoAudit(ia);
                                bp.setTingkat(bt);
                                bp.setKegunaan(kodKegunaanPetak2);
                                strataPtService.save(bp);

                                petakAks = strataPtService.findBngnAks(petakInfo1.getIdHakmilik());
                                if (petakAks.size() > 0) {
                                    for (HakmilikPetakAksesori hpa : petakAks) {
                                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById("01");
                                        BangunanPetakAksesori pAks = new BangunanPetakAksesori();
                                        pAks.setBangunan(mohonBangunan);
                                        pAks.setTingkat(bt);
                                        pAks.setPetak(bp);
                                        pAks.setNama(hpa.getNama());
                                        if (kodKegunaanPetakAksesori != null) {
                                            pAks.setKegunaan(kodKegunaanPetakAksesori);
                                        }
                                        else {
                                            pAks.setKegunaan(null);
                                        }
                                        pAks.setLokasi(hpa.getLokasi());
                                        pAks.setCawangan(pengguna.getKodCawangan());
                                        strataPtService.save(pAks);
                                    }
                                }
                            }
                        } else {
                            Integer petakMax = strataPtService.findPetakMaxBngnPetak(mohonBangunan.getPermohonan().getIdPermohonan());
                            if (petakMax == null) {
                                petakMax = 1;
                            } else {
                                petakMax = petakMax + 1;
                            }
                            String petakBarus = String.valueOf(petakMax);
                            Hakmilik petakInfo1 = strataPtService.findNoPetak(noSkim, mohonBangunan.getNama(), nama, petakBarus);
                            bp.setNama(petakInfo1.getNoPetak());
                            bp.setLuas(petakInfo1.getLuas());
                            bp.setSyer(petakInfo1.getUnitSyer().intValue());
                            bp.setInfoAudit(ia);
                            bp.setTingkat(bt);
                            bp.setKegunaan(kodKegunaanPetak2);
                            strataPtService.save(bp);


                            petakAks = strataPtService.findBngnAks(petakInfo1.getIdHakmilik());
                            if (petakAks.size() > 0) {
                                for (HakmilikPetakAksesori hpa : petakAks) {
                                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById("01");
                                        BangunanPetakAksesori pAks = new BangunanPetakAksesori();
                                        pAks.setBangunan(mohonBangunan);
                                        pAks.setTingkat(bt);
                                        pAks.setPetak(bp);
                                        pAks.setNama(hpa.getNama());
                                        if (kodKegunaanPetakAksesori != null) {
                                            pAks.setKegunaan(kodKegunaanPetakAksesori);
                                        }
                                        else {
                                            pAks.setKegunaan(null);
                                        }
                                        pAks.setLokasi(hpa.getLokasi());
                                        pAks.setCawangan(pengguna.getKodCawangan());
                                        strataPtService.save(pAks);
                                    }
                            }
                        }
                    }
                }
            }
        }
        addSimpleMessage("Data Dalam Bangunan Telah Berjaya Dikemaskini. Sila klik isi semula untuk kemasukan baru");
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
    }

    public Resolution reset() {
        noSkim = null;
        idPihak = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiDCStrata.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Pihak> getSenaraiPihak() {
        return senaraiPihak;
    }

    public void setSenaraiPihak(List<Pihak> senaraiPihak) {
        this.senaraiPihak = senaraiPihak;
    }

    public String getNoSkim() {
        return noSkim;
    }

    public void setNoSkim(String noSkim) {
        this.noSkim = noSkim;
    }

    public HakmilikPihakBerkepentingan getBndUrus() {
        return bndUrus;
    }

    public void setBndUrus(HakmilikPihakBerkepentingan bndUrus) {
        this.bndUrus = bndUrus;
    }

    public String getKodKegunaanPetak() {
        return kodKegunaanPetak;
    }

    public void setKodKegunaanPetak(String kodKegunaanPetak) {
        this.kodKegunaanPetak = kodKegunaanPetak;
    }

    public List<PermohonanBangunan> getSenaraipermohonanBangunan() {
        return senaraipermohonanBangunan;
    }

    public void setSenaraipermohonanBangunan(List<PermohonanBangunan> senaraipermohonanBangunan) {
        this.senaraipermohonanBangunan = senaraipermohonanBangunan;
    }
}
