/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import org.apache.commons.lang.StringUtils;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanBangunanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PihakDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanTingkat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodHartaBersama;
import etanah.model.KodKeputusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanHartaBersama;
import etanah.model.Pihak;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanStrata;
import etanah.service.BPelService;
import org.apache.log4j.Logger;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Syafiq Azizi
 */
@UrlBinding("/strata/kertasPBBD")
public class KertasPertimbanganPBBDActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    StrataPtService strService;
    @Inject
    private Permohonan permohonan;
    @Inject
    PermohonanBangunanDAO mohonBgnDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    CommonService comm;
    @Inject
    BPelService service;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private PermohonanBangunan mohonbngn;
    private List<PermohonanBangunan> pBangunanL;
    private List<HakmilikUrusan> hakmilikUrusanL;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private Pemohon pemohon;
    private Pihak pihak;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private int bil_petak = 0;
    private int bil_tgkt;
    private int bil_bgnn;
    private int bil_ptkAksr;
    private int jumlah_syer = 0;
    private int jumlah = 0;
    private PermohonanKertasKandungan kertasKandungan;
    String tujuan;
    String ulasanJabatanUkurdanPemetaan;
    String asaspertimbangan;
    String syorTP;
    private static final Logger LOG = Logger.getLogger(KertasPertimbanganPBBDActionBean.class);
    private Pengguna pengguna;
    private String taskId;
    boolean rayuan = false;
    private PermohonanStrata permohonanStrata;
    private Date tarikhMasuk = new Date();
    private String tarikhMohon;
    private List<PermohonanKertasKandungan> senaraiKandungan2;
    private List<PermohonanKertasKandungan> senaraiKandungan1;
    private List<PermohonanKertasKandungan> senaraiKandungan3;
    private int rowCount1;
    private int rowCount2;
    private int rowCount3;
    private String butiranhakmilik;
    private String harta;
    private String syorTPTG;
    private String kptg1 = "KPTG";
    private String noruj;
    private String noRuj2;
    private int yr;
    private int totalPetak;
    private int bilB;
    private int bilP;
    private int bilL;
    private String nmBngn;
    private String nmBngnP;
    private int totalP;
    private int totalB;
    private int jumP;
    private int jumB;
    @Inject
    private etanah.Configuration conf;
    private HakmilikPermohonan hp;
    private int lpps;
    private int blokPetak;
    List<PermohonanBangunan> lps;
    List<PermohonanBangunan> block;
    List<PermohonanBangunan> blockP;
    private int totalpetakblock;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private List<PermohonanBangunan> Pb;
    //private List<PermohonanHartaBersama> phb;
    private List<KodHartaBersama> phb;
    private String nLot;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        tarikhMohon = dateFormatter.format(permohonan.getInfoAudit().getTarikhMasuk());
        tarikhMasuk = permohonan.getInfoAudit().getTarikhMasuk();
        permohonanStrata = permohonan.getSenaraiStrata().get(0);

        hp = strService.findMohonHakmilik(idPermohonan);
        LOG.info("--Mohon Hakmilik--::" + hp);
        LOG.info("--Hakmilik--::" + hp.getHakmilik().getIdHakmilik());

        butiranhakmilik = "";
        StringBuffer butirhakmilikTemp = new StringBuffer();

        if (hp != null) {
            if (hp.getHakmilik() != null) {

                if (conf.getProperty("kodNegeri").equals("05")) {
                    Integer noHakmilik = Integer.parseInt(hp.getHakmilik().getNoHakmilik());
                    Integer noLot = Integer.parseInt(hp.getHakmilik().getNoLot());

                    butirhakmilikTemp.append(hp.getHakmilik().getKodHakmilik().getNama() + " " + noHakmilik + ", " + " Lot " + noLot
                            + ", " + hp.getHakmilik().getBandarPekanMukim().getNama() + ", " + hp.getHakmilik().getDaerah().getNama());
                } else {
                    butirhakmilikTemp.append(hp.getHakmilik().getKodHakmilik().getNama() + " " + hp.getHakmilik().getNoHakmilik() + ", " + " Lot " + hp.getHakmilik().getNoLot()
                            + ", " + hp.getHakmilik().getBandarPekanMukim().getNama() + ", " + hp.getHakmilik().getDaerah().getNama());
                }

            }
        }

        butiranhakmilik = butirhakmilikTemp.toString();
        if (butiranhakmilik.endsWith(", ")) {
            butiranhakmilik = butiranhakmilik.substring(0, butiranhakmilik.length() - 2);
            LOG.info("--butiranhakmilik--::" + butiranhakmilik);
        }

        /*
         if (permohonanStrata != null) {
         harta = "";
         StringBuffer butirhakmilikTemp1 = new StringBuffer();
         if (permohonanStrata.getLaporanKemudahan1() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan1() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan2() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan2() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan3() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan3() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan4() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan4() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan5() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan5() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan6() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan6() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan7() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan7() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan8() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan8() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan9() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan9() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan10() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan10() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan10() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan10() + " - ");
         }
         if (permohonanStrata.getLaporanKemudahan11() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan11() + ", ");
         }
         if (permohonanStrata.getLaporanKemudahan12() != null) {
         butirhakmilikTemp1.append(permohonanStrata.getLaporanKemudahan12());
         } */


        Pb = strService.findByIDPermohonan(permohonan.getIdPermohonan());
        LOG.info("-------Pb to Display HartaBersama----------::" + Pb);
        PermohonanBangunan mohonbng = new PermohonanBangunan();
        StringBuffer butirhakmilikTemp1 = new StringBuffer();
        if (!Pb.isEmpty()) {
            mohonbng = Pb.get(0);
            LOG.info("-------mohonbng Id_skim----------::" + mohonbng.getPermohonanSkim());
            //phb = strService.findHartaBersamaByidSkim(mohonbng.getPermohonanSkim().getIdSkim());
            phb = strService.findDistinctHartaBersamaByidSkim(mohonbng.getPermohonanSkim().getIdSkim());
            LOG.info("-------phb----------::" + phb);
            if (!phb.isEmpty()) {
                for (KodHartaBersama phb2 : phb) {
                    LOG.info("-------phb2----------::" + phb2.getNama());
                    butirhakmilikTemp1.append(phb2.getNama() + ", ");
                    harta = butirhakmilikTemp1.toString();
                    if (harta.endsWith(", ")) {
                        harta = harta.substring(0, harta.length() - 2);
                        LOG.info("--harta--::" + harta);
                    }
                }
            }
        }

        List<BangunanTingkat> btkList = new ArrayList<BangunanTingkat>();
        int sum = 0;
        if (checkUrusanRayuan(permohonan)) {
            LOG.info("-------if---checkUrusanRayuan(permohonan)----------::" + checkUrusanRayuan(permohonan));
            pBangunanL = strService.findByIDPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            String[] name = {"permohonan"};
            Object[] object = {permohonan.getPermohonanSebelum()};
            List<PermohonanBangunan> listMohonbgn = strService.findByIDPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("-------listMohonbgn----------::" + listMohonbgn);
            if (!listMohonbgn.isEmpty()) {
                mohonbngn = listMohonbgn.get(0);
                LOG.info("-------mohonbngn.getIdBangunan()----------::" + mohonbngn.getIdBangunan());
                btkList = strService.countTingkatPetakAksri(mohonbngn.getIdBangunan());
                LOG.info("-------btkList----------::" + btkList.size());
                if (!btkList.isEmpty()) {
                    Iterator it = btkList.iterator();
                    while (it.hasNext()) {
                        BangunanTingkat bt = new BangunanTingkat();
                        bt = (BangunanTingkat) it.next();
                        if (bt.getBilanganPetakAksesori() != null) {
                            sum = sum + bt.getBilanganPetakAksesori();
                            LOG.info("-------bt----------::" + bt.getIdTingkat());
                            LOG.info("-------sum----------::" + sum);
                        }
                    }
                }
            }
            bil_ptkAksr = sum;

            idHakmilik = permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikUrusanL = strService.findByID(idHakmilik);
            for (PermohonanBangunan bngn : pBangunanL) {
                bil_tgkt = bil_tgkt + bngn.getSenaraiTingkat().size();
                LOG.info("-------bil_tgkt----------::" + bil_tgkt);
            }
            bil_bgnn = strService.CountBangunan(permohonan.getPermohonanSebelum().getIdPermohonan());

            List<PermohonanBangunan> pb1 = strService.findByIDPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("-------listMohonbgn----------::" + pb1);
            for (PermohonanBangunan pb2 : pb1) {
                List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
                LOG.info("BANGUNAN:: " + pb2.getNama());
                for (BangunanTingkat bt2 : bt1) {
                    bil_petak = bil_petak + bt2.getBilanganPetak();
                    List<BangunanPetak> bp = bt2.getSenaraiPetak();
                    for (BangunanPetak bp2 : bp) {
                        if (bp2.getSyer() != null) {
                            LOG.info("OK je");
                            LOG.info("bp2 : " + bp2.getSyer());
                            jumlah_syer = bp2.getSyer() != null ? bp2.getSyer() : 0;
                            jumlah = jumlah + jumlah_syer;
                            LOG.info("Jumlah : " + jumlah);
                        }

                    }
                }
            }

            if (!(hakmilik.getSenaraiPihakBerkepentingan().isEmpty())) {
                hakmilikPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan().get(0);
            }

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<Pemohon> listPemohon;
            listPemohon = strService.findByPermohonan(permohonan.getIdPermohonan());
            LOG.info("-----------listPemohon----------" + listPemohon);
            if (!(listPemohon.isEmpty())) {
                pemohon = listPemohon.get(0);
                pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                LOG.info("-----------pihak----------" + pihak);
                tujuan = " Tujuan kertas ini disediakan bagi mempertimbangkan permohonan Pecah Bahagi Bangunan daripada " + pihak.getNama();
            }

            LOG.info("---------1--List From Rehydrate()----------");
            if (!(permohonan.getSenaraiKertas().isEmpty())) {
                for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                    PermohonanKertas perKertas = new PermohonanKertas();
                    perKertas = permohonan.getSenaraiKertas().get(i);
                    for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {
                        kertasKandungan = perKertas.getSenaraiKandungan().get(j);
                        if (kertasKandungan.getBil() == 1) {
                            tujuan = kertasKandungan.getKandungan();
                            ulasanJabatanUkurdanPemetaan = kertasKandungan.getKandungan();
                        }
                        if (kertasKandungan.getBil() == 2) {
                            asaspertimbangan = kertasKandungan.getKandungan();
                        }
                    }
                    senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan2 = strService.findByIdLapor(perKertas.getIdKertas(), 3);
                    LOG.info("--------senaraiKandungan2---List From Rehydrate()----------" + senaraiKandungan2);
                    senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan1 = strService.findByIdLapor(perKertas.getIdKertas(), 4);
                    LOG.info("--------senaraiKandungan1---List From Rehydrate()----------" + senaraiKandungan1);
                    senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                    senaraiKandungan3 = strService.findByIdLapor(perKertas.getIdKertas(), 5);
                    LOG.info("--------senaraiKandungan3---List From Rehydrate()----------" + senaraiKandungan3);
                    rowCount1 = senaraiKandungan1.size();
                    LOG.info("--------rowCount1---From Rehydrate()----------" + rowCount1);
                    rowCount2 = senaraiKandungan2.size();
                    LOG.info("--------rowCount2---From Rehydrate()----------" + rowCount2);
                    rowCount3 = senaraiKandungan3.size();
                    LOG.info("--------rowCount3---From Rehydrate()----------" + rowCount3);
                }

            } else {
                FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_terimapelan");
                if (mohonFasa != null) {
                    ulasanJabatanUkurdanPemetaan = mohonFasa.getUlasan();
                }
            }
        } else {
            LOG.info("-------if---checkUrusanRayuan(permohonan)----------::" + checkUrusanRayuan(permohonan));
            if (idPermohonan != null) {
                pBangunanL = strService.findByIDPermohonan(idPermohonan);
                String[] name = {"permohonan"};
                Object[] object = {permohonan};
                List<PermohonanBangunan> listMohonbgn = strService.findByIDPermohonan(permohonan.getIdPermohonan());
                LOG.info("-------listMohonbgn----------::" + listMohonbgn);
                if (!listMohonbgn.isEmpty()) {
                    mohonbngn = listMohonbgn.get(0);
                    LOG.info("-------mohonbngn.getIdBangunan()----------::" + mohonbngn.getIdBangunan());
                    btkList = strService.countTingkatPetakAksri(mohonbngn.getIdBangunan());
                    LOG.info("-------btkList----------::" + btkList.size());
                    if (!btkList.isEmpty()) {
                        Iterator it = btkList.iterator();
                        while (it.hasNext()) {
                            BangunanTingkat bt = new BangunanTingkat();
                            bt = (BangunanTingkat) it.next();
                            sum = sum + bt.getBilanganPetakAksesori();
                            LOG.info("-------bt----------::" + bt.getIdTingkat());
                            LOG.info("-------sum----------::" + sum);
                        }
                    }
                }
                bil_ptkAksr = sum;
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);
                nLot = hakmilik.getNoLot().replaceAll("^0*", "");
                hakmilikUrusanL = strService.findByID(idHakmilik);
                for (PermohonanBangunan bngn : pBangunanL) {
                    bil_tgkt = bil_tgkt + bngn.getSenaraiTingkat().size();
                    LOG.info("-------bil_tgkt----------::" + bil_tgkt);
                }
                bil_bgnn = strService.CountBangunan(idPermohonan);

                List<PermohonanBangunan> pb1 = strService.findByIDPermohonan(permohonan.getIdPermohonan());
                if (!pb1.isEmpty()) {
                    for (PermohonanBangunan pb2 : pb1) {
                        List<BangunanTingkat> bt1 = pb2.getSenaraiTingkat();
                        LOG.info("BANGUNAN:: " + pb2.getNama());
                        for (BangunanTingkat bt2 : bt1) {
                            bil_petak = bil_petak + bt2.getBilanganPetak();
                            List<BangunanPetak> bp = bt2.getSenaraiPetak();
                            for (BangunanPetak bp2 : bp) {
                                if (bp2.getSyer() != null) {
                                    LOG.info("OK je");
                                }
                                jumlah_syer = bp2.getSyer() != null ? bp2.getSyer() : 0;
                                jumlah = jumlah + jumlah_syer;
                                LOG.info(jumlah);
                            }
                        }
                    }
                }

                if (!(hakmilik.getSenaraiPihakBerkepentingan().isEmpty())) {
                    hakmilikPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan().get(0);
                }

                String[] tname = {"permohonan"};
                Object[] model = {permohonan};

                List<Pemohon> listPemohon;
                listPemohon = strService.findByPermohonan(permohonan.getIdPermohonan());
                LOG.info("-----------listPemohon----------" + listPemohon);
                if (!(listPemohon.isEmpty())) {
                    pemohon = listPemohon.get(0);
                    pihak = pihakDAO.findById(pemohon.getPihak().getIdPihak());
                    LOG.info("-----------pihak----------" + pihak);
                    tujuan = " Tujuan kertas ini disediakan bagi mempertimbangkan permohonan Pecah Bahagi Bangunan daripada " + pihak.getNama();
                }
                LOG.info("----------2-List From Rehydrate()----------");
                if (!(permohonan.getSenaraiKertas().isEmpty())) {
                    for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
                        PermohonanKertas perKertas = new PermohonanKertas();
                        perKertas = permohonan.getSenaraiKertas().get(i);
                        for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {
                            kertasKandungan = perKertas.getSenaraiKandungan().get(j);
                            if (kertasKandungan.getBil() == 1) {
                                tujuan = kertasKandungan.getKandungan();
                                ulasanJabatanUkurdanPemetaan = kertasKandungan.getKandungan();
                            }
                            if (kertasKandungan.getBil() == 2) {
                                asaspertimbangan = kertasKandungan.getKandungan();
                            }
                        }
                        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
                        senaraiKandungan2 = strService.findByIdLapor(perKertas.getIdKertas(), 3);
                        LOG.info("--------senaraiKandungan2---List From Rehydrate()----------" + senaraiKandungan2);
                        senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
                        senaraiKandungan1 = strService.findByIdLapor(perKertas.getIdKertas(), 4);
                        LOG.info("--------senaraiKandungan1---List From Rehydrate()----------" + senaraiKandungan1);
                        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
                        senaraiKandungan3 = strService.findByIdLapor(perKertas.getIdKertas(), 5);
                        LOG.info("--------senaraiKandungan3---List From Rehydrate()----------" + senaraiKandungan3);
                        rowCount1 = senaraiKandungan1.size();
                        LOG.info("--------rowCount1---From Rehydrate()----------" + rowCount1);
                        rowCount2 = senaraiKandungan2.size();
                        LOG.info("--------rowCount2---From Rehydrate()----------" + rowCount2);
                        rowCount3 = senaraiKandungan3.size();
                        LOG.info("--------rowCount3---From Rehydrate()----------" + rowCount3);
                    }

                } else {
                    FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "g_terimapelanjupem");
                    if (mohonFasa != null) {
                        ulasanJabatanUkurdanPemetaan = mohonFasa.getUlasan();
                    }
                }
            }
        }

        //added for kertas_PertimbanganNS.jsp to get data from mohonbngn @NS 18-07-2012
        LOG.info("--permohonan--:" + permohonan.getIdPermohonan());
        List<PermohonanBangunan> mohonbngn1 = strService.findByIDPermohonan(permohonan.getIdPermohonan());
        LOG.info("--mohonbngn1--:" + mohonbngn1.size());
        if (mohonbngn1.size() > 0) {
            mohonbngn = mohonbngn1.get(0);
        }
        LOG.info("--mohonbngn--:" + mohonbngn);

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("----kodNegeri--------::" + kodNegeri);
        if (kodNegeri.equals("05")) {
            FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "perakuan");
            LOG.debug("------mohonFasa-------::" + mohonFasa);
            if ((mohonFasa != null) && (mohonFasa.getKeputusan() != null)) {
                syorTPTG = mohonFasa.getKeputusan().getKod();
                LOG.debug("------syorTPTG----rehydrate()---::" + syorTPTG);
            }

            Calendar cal = new GregorianCalendar();
            yr = cal.get(Calendar.YEAR);
            LOG.debug("------yr----::" + yr);

            PermohonanKertas pkerts = strService.findKertas(idPermohonan);
            if ((pkerts != null) && (pkerts.getNomborRujukan() != null)) {
                noruj = pkerts.getNomborRujukan();
                if (noruj != null) {
                    LOG.debug("------noruj--with date--::" + noruj);
                    String[] str1 = noruj.split("/");
                    LOG.debug("------str[0]--::" + str1[0]);
                    noruj = str1[0].toString();
                    LOG.debug("------noruj-withoud date---::" + noruj);
                }
            }
        }

        //added to get No of LandParcels to display at kertas_PertimbanganNS
        lps = strService.findByIDPermohonanByLandparcel(permohonan.getIdPermohonan());
        LOG.info("--Lmohonbngn--:" + lps.size());
        lpps = 0;
        totalPetak = 0;
        blokPetak = 0;
        totalP = 0;
        int count = 0;

        block = strService.findByIDPermohonanByBlock(permohonan.getIdPermohonan());
        nmBngn = "";
        jumB = block.size();
        if (block.size() != 0) {
            for (PermohonanBangunan blok : block) {
                blokPetak = blokPetak + blok.getBilanganPetak();
                if (nmBngn != "") {
                    nmBngn = nmBngn + ", ";
                }
                nmBngn = nmBngn + blok.getNama();
            }
            totalPetak = totalPetak + blokPetak;
            bilB = count + 1;
            count = count + 1;
        }
        
        blockP = strService.findByIDPermohonanByBlockP(permohonan.getIdPermohonan());
        nmBngnP = "";
        jumP = blockP.size();
        if (blockP.size() != 0) {
            for (PermohonanBangunan blokP : blockP) {
                totalP = totalP + blokP.getBilanganPetak();
                if (nmBngnP != "") {
                    nmBngnP = nmBngnP + ", ";
                }
                nmBngnP = nmBngnP + blokP.getNama();              
            }
            totalPetak = totalPetak + totalP;
            bilP = count+1;
            count = count+1;
        }

        if (lps.size() != 0) {
            for (PermohonanBangunan lppsss : lps) {
                lpps = lppsss.getBilanganPetak();
                totalPetak = totalPetak + lpps;
                bilL = count + 1;
                count = count+1;
            }
        }

        stageId = getBPLStageId();
        LOG.info("--stageId--:" + stageId);

        senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        //senaraiHakmilikPermohonan = strService.findIdHakmilikSort(idPermohonan);
        LOG.info("----------HakmilikPermohonan List size--------------: " + senaraiHakmilikPermohonan.size());
    }

    public String getBPLStageId() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        service = new BPelService();
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("--stageId--:" + stageId);
        }
        return stageId;
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
        getContext().getRequest().setAttribute("edit3", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        return new JSP("strata/pbbm/kertas_pertimbangan.jsp").addParameter("tab", "true");
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit4", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit5", Boolean.TRUE);
        return new JSP("strata/pbbm/kertas_pertimbangan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormReadOnly() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit3", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit4", Boolean.FALSE);
        getContext().getRequest().setAttribute("edit5", Boolean.FALSE);
        return new JSP("strata/pbbm/kertas_pertimbangan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
            PermohonanKertas pkerts = strService.findKertas(idPermohonan);
            if (pkerts != null) {
                String pkertas = String.valueOf(pkerts.getIdKertas());
                if (pkertas.equalsIgnoreCase("") || pkertas == null) {
                    PermohonanKertas mhnKertas = strService.findMaxIDKertas();
                    Long idKertas = mhnKertas.getIdKertas() + 1;
                    noruj = String.valueOf(idKertas);
                }
            } else {
                PermohonanKertas mhnKertas = strService.findMaxIDKertas();
                Long idKertas = mhnKertas.getIdKertas() + 1;
                noruj = String.valueOf(idKertas);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/pbbm/kertas_pertimbanganPBBD_NS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/pbbm/kertas_pertimbanganPBBD_NS.jsp").addParameter("tab", "true");
    }

    public boolean checkUrusanRayuan(Permohonan permohonan) {
        rayuan = false;
        if (permohonan.getKodUrusan().getKod().equals("RBHS")
                || permohonan.getKodUrusan().getKod().equals("RMH1A")
                || permohonan.getKodUrusan().getKod().equals("RMHS1")
                || permohonan.getKodUrusan().getKod().equals("RMHS5")
                || permohonan.getKodUrusan().getKod().equals("RMHS6")
                || permohonan.getKodUrusan().getKod().equals("RMHS7")
                || permohonan.getKodUrusan().getKod().equals("RPHS")
                || permohonan.getKodUrusan().getKod().equals("RTHS")
                || permohonan.getKodUrusan().getKod().equals("RTHS1")
                || permohonan.getKodUrusan().getKod().equals("RTHS5")
                || permohonan.getKodUrusan().getKod().equals("RTMC")
                || permohonan.getKodUrusan().getKod().equals("RTPS")) {
            rayuan = true;
        } else {
            rayuan = false;
        }
        return rayuan;
    }

    public Resolution simpan() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN");
        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();


        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listSubtajuk.add("");
        listBill.add(new Integer(1));

        if (kertasKandungan != null) {

            if (!kertasKandungan.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tujuan);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(bil);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setSubtajuk(subtajuk);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }
        LOG.info("--------Saving in 3-------:");
        senaraiKandungan2 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan2 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 3);
        LOG.info("--------senaraiKandungan2-------:" + senaraiKandungan2);
        LOG.info("--------senaraiKandungan2---size----:" + senaraiKandungan2.size());
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount2"));
        LOG.info("------senaraiKandungan2--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan2.size() != 0 && i <= senaraiKandungan2.size()) {
                Long id = senaraiKandungan2.get(i - 1).getIdKandungan();
                if (id != null) {
                    LOG.info("--------senaraiKandungan2--if-----:");
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                LOG.info("--------senaraiKandungan2--else-----:");
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(3);
            String kandungan = getContext().getRequest().getParameter("ulasan" + i);
            LOG.info("--------kandungan-------:" + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("3." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        msg = "Maklumat telah berjaya disimpan.";

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new RedirectResolution(KertasPertimbanganPBBDActionBean.class, "showForm").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution simpanSemakKertasptg() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String error = "";
        String msg = "";

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN");

        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();
        ArrayList listBill = new ArrayList();

        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listSubtajuk.add("");
        listBill.add(new Integer(1));

        if (kertasKandungan != null) {

            if (!kertasKandungan.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tujuan);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
        } else {

            for (int i = 0; i < listUlasan.size(); i++) {
                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                Integer bil = (Integer) listBill.get(i);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(bil);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setSubtajuk(subtajuk);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        LOG.info("--------Saving in 4-------:");
        senaraiKandungan1 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan1 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 4);
        LOG.info("--------senaraiKandungan1-------:" + senaraiKandungan1);
        LOG.info("--------senaraiKandungan1---size----:" + senaraiKandungan1.size());
        int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount1"));
        LOG.info("------senaraiKandungan1--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan1.size() != 0 && i <= senaraiKandungan1.size()) {
                Long id = senaraiKandungan1.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(4);
            String kandungan = getContext().getRequest().getParameter("asaspertimbangan" + i);
            LOG.info("--------kandungan-------:" + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("4." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        LOG.info("--------Saving in 5-------:");
        senaraiKandungan3 = new ArrayList<PermohonanKertasKandungan>();
        senaraiKandungan3 = strService.findByIdLapor(permohonanKertas.getIdKertas(), 5);
        LOG.info("--------senaraiKandungan3-------:" + senaraiKandungan3);
        LOG.info("--------senaraiKandungan3---size----:" + senaraiKandungan3.size());
        kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount3"));
        LOG.info("------senaraiKandungan3--kira-------:" + kira);
        for (int i = 1; i <= kira; i++) {
            InfoAudit iaP = new InfoAudit();
            PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
            if (senaraiKandungan3.size() != 0 && i <= senaraiKandungan3.size()) {
                Long id = senaraiKandungan3.get(i - 1).getIdKandungan();
                if (id != null) {
                    permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(id);
                    iaP = permohonanKertasKandungan1.getInfoAudit();
                    iaP.setTarikhKemaskini(new Date());
                    iaP.setDiKemaskiniOleh(peng);
                }
            } else {
                permohonanKertasKandungan1 = new PermohonanKertasKandungan();
                iaP.setTarikhMasuk(new Date());
                iaP.setDimasukOleh(peng);
            }
            permohonanKertasKandungan1.setKertas(permohonanKertas);
            permohonanKertasKandungan1.setBil(5);
            String kandungan = getContext().getRequest().getParameter("syorTP" + i);
            LOG.info("--------kandungan-------:" + kandungan);
            if (kandungan == null || kandungan.equals("")) {
                kandungan = "TIADA DATA.";
            }
            permohonanKertasKandungan1.setKandungan(kandungan);
            permohonanKertasKandungan1.setCawangan(permohonan.getCawangan());
            permohonanKertasKandungan1.setSubtajuk("5." + i);
            permohonanKertasKandungan1.setInfoAudit(iaP);
            strService.simpanPermohonanKertasKandungan(permohonanKertasKandungan1);
        }

        msg = "Maklumat telah berjaya disimpan.";

        getContext().getRequest().setAttribute("display", Boolean.TRUE);

        return new RedirectResolution(KertasPertimbanganPBBDActionBean.class, "showForm2").addParameter("error", error).addParameter("message", msg);
    }

    public Resolution deleteSingle() {

        LOG.info("--------Deleting Rekod----------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------idKandungan----------" + idKandungan);
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            LOG.debug("--------Deleting Record--------" + permohonanKertasKandungan1);
        } catch (Exception e) {
            LOG.debug("--------Hapus empty record--------");
        }
        if (permohonanKertasKandungan1 != null) {
            strService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("---------Record deleted Successfully---------");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasPertimbanganPBBDActionBean.class, "locate");
    }

    public Resolution deleteSingleSemakKertasptg() {

        LOG.info("--------Deleting Rekod----------");
        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        LOG.info("--------idKandungan----------" + idKandungan);
        PermohonanKertasKandungan permohonanKertasKandungan1 = new PermohonanKertasKandungan();
        try {
            permohonanKertasKandungan1 = permohonanKertasKandunganDAO.findById(Long.parseLong(idKandungan));
            LOG.debug("--------Deleting Record--------" + permohonanKertasKandungan1);
        } catch (Exception e) {
            LOG.debug("--------Hapus empty record--------");
        }
        if (permohonanKertasKandungan1 != null) {
            strService.deleteKertasKandungan(permohonanKertasKandungan1);
            LOG.debug("---------Record deleted Successfully---------");
        }
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return new RedirectResolution(KertasPertimbanganPBBDActionBean.class, "showForm2");
    }

    public Resolution simpan1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String error = "";
        String msg = "";


        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit.setDiKemaskiniOleh(peng);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        if (kptg1 != null) {
            KodDokumen kodc = kodDokumenDAO.findById(kptg1);
            LOG.debug("------kodc----::" + kodc);
            permohonanKertas.setKodDokumen(kodc);
        }
        if (noruj != null) {
            Calendar cal = new GregorianCalendar();
            int yr1 = cal.get(Calendar.YEAR);
            LOG.debug("------yr----::" + yr1);
            String noruj1 = noruj + "/" + yr1;
            LOG.debug("------noruj1----::" + noruj1);
            permohonanKertas.setNomborRujukan(noruj1);
        }
        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PERMOHONAN PECAH BAHAGI BANGUNAN");


        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();

        if (ulasanJabatanUkurdanPemetaan == null || ulasanJabatanUkurdanPemetaan.equals("")) {
            ulasanJabatanUkurdanPemetaan = "TIADA DATA";
        }
        if (asaspertimbangan == null || asaspertimbangan.equals("")) {
            asaspertimbangan = "TIADA DATA";
        }
        if (syorTP == null || syorTP.equals("")) {
            syorTP = "TIADA DATA";
        }


        listUlasan.add(ulasanJabatanUkurdanPemetaan);
        listUlasan.add(asaspertimbangan);
        listUlasan.add(syorTP);

        if (kertasKandungan != null) {

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(ulasanJabatanUkurdanPemetaan);
                    } else if (kertasKdgn.getBil() == 2) {
                        kertasKdgn.setKandungan(asaspertimbangan);
                    } else if (kertasKdgn.getBil() == 3) {
                        kertasKdgn.setKandungan(syorTP);
                    }

                    kertasKdgn.setInfoAudit(infoAudit);
                    strService.simpanPermohonanKertasKandungan(kertasKdgn);

                }
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);

                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(i + 1);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                strService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        //added code for Syor PPTG(Pd) saving in mohon_fasa
        FasaPermohonan mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "perakuan");
        LOG.debug("------mohonFasa-------::" + mohonFasa);
        if (mohonFasa == null) {
            LOG.debug("------mohonFasa---is null----::");
            mohonFasa = new FasaPermohonan();
        }
        LOG.debug("------mohonFasa---not null----::");
        mohonFasa.setInfoAudit(infoAudit);
        mohonFasa.setPermohonan(permohonan);
        mohonFasa.setCawangan(permohonan.getCawangan());
        mohonFasa.setIdAliran("perakuan");
        mohonFasa.setIdPengguna("tptg1");
        mohonFasa.setTarikhKeputusan(new Date());


        if (syorTPTG != null) {
            KodKeputusan kpsn = kodKeputusanDAO.findById(syorTPTG);
            String ulasan = asaspertimbangan;
            mohonFasa.setUlasan(ulasan);
            LOG.debug("------kpsn----::" + kpsn);
            mohonFasa.setKeputusan(kpsn);
        }

        mohonFasa = strService.saveFasaMohon(mohonFasa);


        msg = "Maklumat telah berjaya disimpan.";
        addSimpleMessage(msg);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new RedirectResolution(KertasPertimbanganPBBDActionBean.class, "showForm3").addParameter("error", error).addParameter("message", msg);

    }

    public Resolution cetakPelan() {

        return new JSP("strata/pbbm/cetak_Pelan.jsp").addParameter("tab", "true");
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getJumlah_syer() {
        return jumlah_syer;
    }

    public void setJumlah_syer(int jumlah_syer) {
        this.jumlah_syer = jumlah_syer;
    }

    public int getBil_ptkAksr() {
        return bil_ptkAksr;
    }

    public void setBil_ptkAksr(int bil_ptkAksr) {
        this.bil_ptkAksr = bil_ptkAksr;
    }

    public PermohonanBangunan getMohonbngn() {
        return mohonbngn;
    }

    public void setMohonbngn(PermohonanBangunan mohonbngn) {
        this.mohonbngn = mohonbngn;
    }

    public List<HakmilikUrusan> getHakmilikUrusanL() {
        return hakmilikUrusanL;
    }

    public void setHakmilikUrusanL(List<HakmilikUrusan> hakmilikUrusanL) {
        this.hakmilikUrusanL = hakmilikUrusanL;
    }

    public String getAsaspertimbangan() {
        return asaspertimbangan;
    }

    public void setAsaspertimbangan(String asaspertimbangan) {
        this.asaspertimbangan = asaspertimbangan;
    }

    public String getSyorTP() {
        return syorTP;
    }

    public void setSyorTP(String syorTP) {
        this.syorTP = syorTP;
    }

    public String getUlasanJabatanUkurdanPemetaan() {
        return ulasanJabatanUkurdanPemetaan;
    }

    public void setUlasanJabatanUkurdanPemetaan(String ulasanJabatanUkurdanPemetaan) {
        this.ulasanJabatanUkurdanPemetaan = ulasanJabatanUkurdanPemetaan;
    }

    public int getBil_bgnn() {
        return bil_bgnn;
    }

    public void setBil_bgnn(int bil_bgnn) {
        this.bil_bgnn = bil_bgnn;
    }

    public int getBil_petak() {
        return bil_petak;
    }

    public void setBil_petak(int bil_petak) {
        this.bil_petak = bil_petak;
    }

    public int getBil_tgkt() {
        return bil_tgkt;
    }

    public void setBil_tgkt(int bil_tgkt) {
        this.bil_tgkt = bil_tgkt;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public boolean isRayuan() {
        return rayuan;
    }

    public void setRayuan(boolean rayuan) {
        this.rayuan = rayuan;
    }

    public PermohonanStrata getPermohonanStrata() {
        return permohonanStrata;
    }

    public void setPermohonanStrata(PermohonanStrata permohonanStrata) {
        this.permohonanStrata = permohonanStrata;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan2() {
        return senaraiKandungan2;
    }

    public void setSenaraiKandungan2(List<PermohonanKertasKandungan> senaraiKandungan2) {
        this.senaraiKandungan2 = senaraiKandungan2;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan1() {
        return senaraiKandungan1;
    }

    public void setSenaraiKandungan1(List<PermohonanKertasKandungan> senaraiKandungan1) {
        this.senaraiKandungan1 = senaraiKandungan1;
    }

    public List<PermohonanKertasKandungan> getSenaraiKandungan3() {
        return senaraiKandungan3;
    }

    public void setSenaraiKandungan3(List<PermohonanKertasKandungan> senaraiKandungan3) {
        this.senaraiKandungan3 = senaraiKandungan3;
    }

    public int getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(int rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public int getRowCount2() {
        return rowCount2;
    }

    public void setRowCount2(int rowCount2) {
        this.rowCount2 = rowCount2;
    }

    public int getRowCount3() {
        return rowCount3;
    }

    public void setRowCount3(int rowCount3) {
        this.rowCount3 = rowCount3;
    }

    public String getButiranhakmilik() {
        return butiranhakmilik;
    }

    public void setButiranhakmilik(String butiranhakmilik) {
        this.butiranhakmilik = butiranhakmilik;
    }

    public String getHarta() {
        return harta;
    }

    public void setHarta(String harta) {
        this.harta = harta;
    }

    public String getSyorTPTG() {
        return syorTPTG;
    }

    public void setSyorTPTG(String syorTPTG) {
        this.syorTPTG = syorTPTG;
    }

    public int getBilB() {
        return bilB;
    }

    public void setBilB(int bilB) {
        this.bilB = bilB;
    }

    public int getBilP() {
        return bilP;
    }

    public void setBilP(int bilP) {
        this.bilP = bilP;
    }

    public int getBilL() {
        return bilL;
    }

    public void setBilL(int bilL) {
        this.bilL = bilL;
    }

    public int getJumP() {
        return jumP;
    }

    public void setJumP(int jumP) {
        this.jumP = jumP;
    }

    public int getJumB() {
        return jumB;
    }

    public void setJumB(int jumB) {
        this.jumB = jumB;
    }

    public int getTotalP() {
        return totalP;
    }

    public List<PermohonanBangunan> getBlockP() {
        return blockP;
    }

    public void setBlockP(List<PermohonanBangunan> blockP) {
        this.blockP = blockP;
    }

    public void setTotalP(int totalP) {
        this.totalP = totalP;
    }

    public int getTotalB() {
        return totalB;
    }

    public void setTotalB(int totalB) {
        this.totalB = totalB;
    }

    public String getNmBngn() {
        return nmBngn;
    }

    public void setNmBngn(String nmBngn) {
        this.nmBngn = nmBngn;
    }

    public int getBlokPetak() {
        return blokPetak;
    }

    public void setBlokPetak(int blokPetak) {
        this.blokPetak = blokPetak;
    }

    public List<PermohonanBangunan> getBlock() {
        return block;
    }

    public void setBlock(List<PermohonanBangunan> block) {
        this.block = block;
    }

    public int getTotalpetakblock() {
        return totalpetakblock;
    }

    public void setTotalpetakblock(int totalpetakblock) {
        this.totalpetakblock = totalpetakblock;
    }

    public String getKptg1() {
        return kptg1;
    }

    public void setKptg1(String kptg1) {
        this.kptg1 = kptg1;
    }

    public String getNoruj() {
        return noruj;
    }

    public void setNoruj(String noruj) {
        this.noruj = noruj;
    }

    public int getYr() {
        return yr;
    }

    public void setYr(int yr) {
        this.yr = yr;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public int getLpps() {
        return lpps;
    }

    public void setLpps(int lpps) {
        this.lpps = lpps;
    }

    public List<PermohonanBangunan> getLps() {
        return lps;
    }

    public void setLps(List<PermohonanBangunan> lps) {
        this.lps = lps;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getnLot() {
        return nLot;
    }

    public void setnLot(String nLot) {
        this.nLot = nLot;
    }

    /**
     * @return the noRuj2
     */
    public String getNoRuj2() {
        return noRuj2;
    }

    /**
     * @param noRuj2 the noRuj2 to set
     */
    public void setNoRuj2(String noRuj2) {
        this.noRuj2 = noRuj2;
    }

    /**
     * @return the totalPetak
     */
    public int getTotalPetak() {
        return totalPetak;
    }

    /**
     * @param totalPetak the totalPetak to set
     */
    public void setTotalPetak(int totalPetak) {
        this.totalPetak = totalPetak;
    }

    /**
     * @return the tarikhMohon
     */
    public String getTarikhMohon() {
        return tarikhMohon;
    }

    /**
     * @param tarikhMohon the tarikhMohon to set
     */
    public void setTarikhMohon(String tarikhMohon) {
        this.tarikhMohon = tarikhMohon;
    }
}
