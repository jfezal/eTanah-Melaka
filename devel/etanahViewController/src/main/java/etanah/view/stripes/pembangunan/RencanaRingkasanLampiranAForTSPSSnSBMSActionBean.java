/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

/**
 *
 * @author khairul.hazwan
 */
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanPlotSekatanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodHakmilik;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodNegeri;
import etanah.model.KodPemilikan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTuntut;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanPlotSekatan;
import etanah.model.PermohonanPlotSyaratNyata;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.RegService;
import etanah.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

@UrlBinding("/pembangunan/melaka/rencanaRingkasanLampiranAForTPSSSnSBMS")
public class RencanaRingkasanLampiranAForTSPSSnSBMSActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RencanaRingkasanLampiranAForTSPSSnSBMSActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    RegService regService;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hp;
    private PermohonanKertasKandungan kertasK;
    private String tujuan;
    private KodDokumen kd;
    private List<PermohonanPlotPelan> senaraiPlotPelan;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private String lokasi;
    private String lokasi1;
    private HakmilikPermohonan hpTemp;
    private String premium;
    private String hasil;
    private String sumbanganSaliranDesc;
    private String sumbanganSaliran;
    private String bayaranUkur;
    private List<HakmilikPermohonan> hpList;
    private BigDecimal kadarPremium;
    private BigDecimal cukaiBaru;
    private BigDecimal kosInfra;
    private PermohonanPlotPelan mpp;
    private List<PermohonanPlotPelan> listplotpelan;
    private int bilplotHakmiliktbl = 0;
    private BigDecimal jumluasHakmilik = new BigDecimal(0.00);
    private int bilplotHakmiliktemp = 0;
    private List<PermohonanPlotPelan> listHakmilik;
    private String idPlot;
    private String foredit;
    private String hakmilikSementara;
    private Character pegangan;
    private String premiumDesc;
    private String hasilDesc;
    private String tempoh;
    private String kodKegunaanTanah;
    private String kodKategoriTanah;
    private String jenisHakmilik;
    private String kodSyaratNyataBaru;
    private String kodSekatanKepentinganBaru;
    private Character kodPemilikan;
    private List<PermohonanPlotPelan> permohonanPlotPelan;
    private BigDecimal jumlahPremium = new BigDecimal(0.00);
    private BigDecimal jumlahHasil = new BigDecimal(0.00);
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private String kodSekatan;
    private String kodSyaratNyata;
    private String syaratNyata;
    private String sekatan;
    private String index;
    private String kodKatTanah;
    private String syarat;
    private String jenisGuna;
    private String penjenisan;
    private String kodHakmilik;
    private FasaPermohonan fp;

    private List<PermohonanPlotSyaratNyata> listSyaratNyata;
    private List<PermohonanPlotSekatan> listSekatan;
    private String kodUrusan;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("----------showForm----------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA_TSPSS_SBMS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("----------showForm2----------");
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA_TSPSS_SBMS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------rehydrate----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonan.getKodUrusan().getKod();
        //hideLampiranATitle
        fp = devService.findFasaPermohonanByIdAliran(idPermohonan, "rekodkpsnyabkdrjpphtkhkpsn");
        if (fp != null) {
            LOG.info("----------Hide Lampiran A Title----------");
        } else {
            LOG.info("----------Not Hide Lampiran A Title----------");
        }

        bilplotHakmiliktemp = 0;
        bilplotHakmiliktbl = 0;
        jumluasHakmilik = new BigDecimal(0.00);
        jumlahHasil = new BigDecimal(0.00);
        jumlahPremium = new BigDecimal(0.00);
        listHakmilik = new ArrayList<PermohonanPlotPelan>();
        permohonanPlotPelan = new ArrayList<PermohonanPlotPelan>();

        hpList = new ArrayList<HakmilikPermohonan>();
        hpList = permohonan.getSenaraiHakmilik();
        LOG.info("----------Permohonan----------:" + permohonan);
        hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        if (hp != null) {
            //premium = hp.getKeteranganKadarPremium();
            //hasil = hp.getKeteranganCukaiBaru();
            sumbanganSaliranDesc = hp.getKeteranganInfra();
            if (hp.getKosInfra() != null) {
                sumbanganSaliran = hp.getKosInfra().toString();
            }
            bayaranUkur = hp.getKeteranganKadarUkur();

            if (bayaranUkur == null || bayaranUkur.equals("")) {
                bayaranUkur = "Mengikut P.U(A)438";
            }
        }

        //List Plot H
        LOG.info("plot pelan list.");
        listplotpelan = new ArrayList<PermohonanPlotPelan>();
        listplotpelan = devService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);

        LOG.info("---------listplotpelan---------list:" + listplotpelan);
        if (!(listplotpelan.isEmpty())) {

            for (int a = 0; a < listplotpelan.size(); a++) {
                mpp = new PermohonanPlotPelan();
                mpp = listplotpelan.get(a);

                if (mpp.getPemilikan().getKod() == 'H' || mpp.getPemilikan().getKod() == 'K') {
                    LOG.info("plot pelan hakmilik list.");
                    bilplotHakmiliktbl = mpp.getBilanganPlot();
                    BigDecimal luasH = mpp.getLuas();
                    luasH = luasH.multiply(new BigDecimal(mpp.getBilanganPlot()));
                    System.out.println("****** luasH ******* = " + luasH);
                    jumluasHakmilik = jumluasHakmilik.add(luasH);
                    bilplotHakmiliktemp = bilplotHakmiliktemp + bilplotHakmiliktbl;
                    System.out.println("plot hakmilik = " + bilplotHakmiliktemp);
                    listHakmilik.add(mpp);
                }
            }

            //jumlahPremium = new BigDecimal(0.00);
            //jumlahHasil = new BigDecimal (0.00);
            //premium & hasil calculation
            for (PermohonanPlotPelan mpp1 : listHakmilik) {
                if (mpp1.getPlotPremium() != null) {
                    BigDecimal luasH1 = mpp1.getPlotPremium();
                    //luasH1 = luasH1.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                    //luasH1 = luasH1.multiply((mpp1.getPlotPremium()));
                    jumlahPremium = jumlahPremium.add(luasH1);
                } else if (mpp1.getPlotHasil() != null) {
                    BigDecimal luasH2 = mpp1.getPlotHasil();
                    //luasH2 = luasH2.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                    //luasH2 = luasH2.multiply((mpp1.getPlotHasil()));
                    jumlahHasil = jumlahHasil.add(luasH2);
                } else {
                    LOG.debug("NO DATA");
                }
            }
        }

        idPlot = getContext().getRequest().getParameter("idPlot");
        if (idPlot != null) {
            mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));

            if (mpp.getKategoriTanah() != null) {
                penjenisan = mpp.getKategoriTanah().getNama();
            }
            if (mpp.getKodHakmilikTetap() != null) {
                kodHakmilik = mpp.getKodHakmilikTetap().getNama();
            }
            if (mpp.getKegunaanTanah() != null) {
                jenisGuna = mpp.getKegunaanTanah().getNama();
            }
            if (mpp.getTempohPegangan() != null) {
                tempoh = mpp.getTempohPegangan().toString();
            }
            if (mpp.getKeteranganKadarPremium() != null) {
                premiumDesc = mpp.getKeteranganKadarPremium();
            }
            if (mpp.getPlotPremium() != null) {
                premium = mpp.getPlotPremium().toString();
            }
            if (mpp.getKeteranganCukaiBaru() != null) {
                hasilDesc = mpp.getKeteranganCukaiBaru();
            }
            if (mpp.getPlotHasil() != null) {
                hasil = mpp.getPlotHasil().toString();
            }
            if (mpp.getKodSyaratNyata() != null) {
                kodSyaratNyataBaru = mpp.getKodSyaratNyata().getSyarat();
            }
            if (mpp.getKodSekatanKepentingan() != null) {
                kodSekatanKepentinganBaru = mpp.getKodSekatanKepentingan().getSekatan();
            }
        } else {
            LOG.debug("---NO DATA---");
        }
    }

    public Resolution editPopup() {
        LOG.debug("editPopup");
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        String kodUrusan = getContext().getRequest().getParameter("kodUrusan");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        
            return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA_TSPSS_SBMS_PopupForPenjenisan.jsp").addParameter("popup", "true");
        
    }

    public Resolution simpanSBMS() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        hp = permohonan.getSenaraiHakmilik().get(0);
        BigDecimal premium1 = BigDecimal.ZERO;
        BigDecimal hasil1 = BigDecimal.ZERO;
        BigDecimal sumbanganSaliran1 = BigDecimal.ZERO;

        for (PermohonanPlotPelan mpp1 : listHakmilik) {
            if (mpp1.getPlotPremium() != null) {
                BigDecimal luasH1 = mpp1.getPlotPremium();
                //luasH1 = luasH1.multiply(new BigDecimal(mpp1.getBilanganPlot()));
                //luasH1 = luasH1.add((mpp1.getPlotPremium()));
                premium1 = premium1.add(luasH1);

                BigDecimal luasH2 = mpp1.getPlotHasil();
                //luasH2 = luasH2.multiply((mpp1.getPlotHasil()));
                hasil1 = hasil1.add(luasH2);
            } else {
                LOG.debug("NO DATA");
            }
        }

        //Sumbangan Saliran & Bayaran Ukur
        List<HakmilikPermohonan> hpList1 = new ArrayList<HakmilikPermohonan>();
        hpList1 = devService.findHakmilikPermohonanByIdPermohonan(idPermohonan);
        hp = hpList1.get(0);
        hp.setKeteranganInfra(sumbanganSaliranDesc);
        if (sumbanganSaliran != null && !sumbanganSaliran.equals("")) {
            hp.setKosInfra(new BigDecimal(sumbanganSaliran));
            sumbanganSaliran1 = hp.getKosInfra();
        } else {
            hp.setKosInfra(null);
        }
        hp.setKeteranganKadarUkur(bayaranUkur);
        devService.simpanHakmilikPermohonan(hp);

        // Added code to save MohonTuntutKos
        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = devService.findTuntutByIdMohon(idPermohonan);

        if (!(listtuntutankos.isEmpty())) {
            LOG.debug("-----------if----------");
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                    permohonantuntutkos.setAmaunTuntutan(premium1);
                    //permohonantuntutkos.setAmaunTuntutan(jumlahPremium);
                } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")) {
                    //permohonantuntutkos.setAmaunTuntutan(jumlahHasil);
                    permohonantuntutkos.setAmaunTuntutan(hasil1);
                } else if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")) {
                    permohonantuntutkos.setAmaunTuntutan(sumbanganSaliran1);
                }
                ia = permohonantuntutkos.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        } else {
            LOG.debug("-----------else----------");
            String[] itemList = {"DEV04", "DEV11", "DEV07"};
            BigDecimal[] amaunTuntutanList = {premium1, hasil1, sumbanganSaliran1};
            for (int i = 0; i < itemList.length; i++) {
                KodTuntut kodTuntut = new KodTuntut();
                LOG.debug("---------kodTuntut---------:" + itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
                devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/rencana_Ringkasan_LampiranA_TSPSS_SBMS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPopup() {
        LOG.info("kemaskini mohon plot pelan start.");
        idPlot = getContext().getRequest().getParameter("idPlot");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        //kodKategoriTanah
        if (kodKategoriTanah != null) {
            mpp.setKategoriTanah(kodKategoriTanahDAO.findById(kodKategoriTanah));
        }

        //hakmilikTetap
        if (jenisHakmilik != null) {
            mpp.setKodHakmilikTetap(kodHakmilikDAO.findById(jenisHakmilik));
        }

        //hakmilikSementara
        if (mpp.getKodHakmilikTetap() != null) {
            if (mpp.getKodHakmilikTetap().getKod().equalsIgnoreCase("HSD")
                    || mpp.getKodHakmilikTetap().getKod().equalsIgnoreCase("GRN")
                    || mpp.getKodHakmilikTetap().getKod().equalsIgnoreCase("PN")) {

                hakmilikSementara = "HSD";
            } else {
                hakmilikSementara = "HSM";
            }
            mpp.setKodHakmilikSementara(kodHakmilikDAO.findById(hakmilikSementara));
        }

        //tempohPegang
        if (tempoh != null) {
            Integer t = new Integer(tempoh);
            mpp.setTempohPegangan(t);
        }
        //pegangan
        if (mpp.getTempohPegangan() != null) {
            if (mpp.getTempohPegangan().equals(0)) {
                pegangan = 'S';
            } else {
                pegangan = 'P';
            }
        } else {
            pegangan = 'S';
        }
        mpp.setPegangan(pegangan);

        //premium
       // if (premiumDesc != null) {
            mpp.setKeteranganKadarPremium(premiumDesc);
      //  }
        if (premium != null) {
            BigDecimal p = new BigDecimal(premium);
            mpp.setPlotPremium(p);
        }else{
             BigDecimal p = new BigDecimal(0.00);
            mpp.setPlotPremium(p);
        }

        //hasil
       // if (hasilDesc != null) {
            mpp.setKeteranganCukaiBaru(hasilDesc);
        //}
        if (hasil != null) {
            BigDecimal h = new BigDecimal(hasil);
            mpp.setPlotHasil(h);
        }else{
            BigDecimal h = new BigDecimal(0.00);
            mpp.setPlotHasil(h);
        }

        //jenisPenggunaanTanah
        if (kodKegunaanTanah != null) {
            mpp.setKegunaanTanah(kodKegunaanTanahDAO.findById(kodKegunaanTanah));
        }

        //syaratNyata & syaratKepentingan
        if (kodSyaratNyataBaru != null) {
            mpp.setKodSyaratNyata(kodSyaratNyataDAO.findById(kodSyaratNyataBaru));
        }
        if (kodSekatanKepentinganBaru != null) {
            mpp.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(kodSekatanKepentinganBaru));
        }

        devService.simpanPlotPelan(mpp);
        addSimpleMessage("Kemaskini telah berjaya.");
        LOG.info("kemaskini mohon plot pelan finish.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA_TSPSS_SBMS_PopupForPenjenisan.jsp").addParameter("popup", "true");
    }

    //Find Sekatan And Syarat Nyata
    public Resolution editNyata() {
        LOG.debug("editNyata");
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        listSyaratNyata = mpp.getSenaraiPermohonanPlotSyaratNyata();
        return new JSP("pembangunan/melaka/LampiranA_popupNyata_SBMS_TSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution editSekatan() {
        LOG.debug("editSekatan");
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        listSekatan = mpp.getSenaraiPermohonanPlotSekatan();
        return new JSP("pembangunan/melaka/LampiranA_popupNyata_SBMS_TSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        //cariKodSekatan();
        return new JSP("pembangunan/melaka/dev_carian_kodSyaratNyata_sbms_tspss.jsp").addParameter("tab", "true");
    }

    public Resolution showFormCariKodSyaratNyata() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        return new JSP("pembangunan/melaka/dev_carian_kodSyaratNyata_sbms_tspss.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSekatan() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        LOG.info(idPlot + "+" + foredit);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSekatan != null) {
            listKodSekatan = devService.searchKodSekatan(kodSekatan, kc, sekatan);
            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = devService.searchKodSekatan("%", kc, sekatan);
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pembangunan/melaka/dev_carian_kodSyaratNyata_sbms_tspss.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSyaratNyata2() {
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        LOG.info(idPlot + "+" + foredit);

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
        if (kodSyaratNyata != null) {
            LOG.info("---cariKodSyaratNyata2---kodKatTanah-----:" + kodKatTanah);
            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata, kodKatTanah);
            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata, kodKatTanah);
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }

        return new JSP("pembangunan/melaka/dev_carian_kodSyaratNyata_sbms_tspss.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKodSyaratNyata() {
        LOG.info("--Save Code--");
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
        LOG.info(idPlot + "+" + foredit);

        mpp = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia = mpp.getInfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        //syaratNyata & syaratKepentingan      
        String kodSyaratNyata1 = getContext().getRequest().getParameter("selectedKod");
        LOG.info("---kodSyaratNyata" + kodSyaratNyata1);
        if (kodSyaratNyata1 != null) {
            mpp.setKodSyaratNyata(kodSyaratNyataDAO.findById(kodSyaratNyata1));
            PermohonanPlotSyaratNyata ppsn = new PermohonanPlotSyaratNyata();
            ppsn.setKodSyaratNyata(kodSyaratNyataDAO.findById(kodSyaratNyata1));
            ppsn.setPermohonanPlotPelan(mpp);
            devService.simpanPermohonanPlotSyaratNyata(ppsn);
        }

        String kodSyaratSekatan1 = getContext().getRequest().getParameter("selectedKod1");
        LOG.info("----kodSyaratSekatan" + kodSyaratSekatan1);
        if (kodSyaratSekatan1 != null) {
            mpp.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(kodSyaratSekatan1));
            PermohonanPlotSekatan pps = new PermohonanPlotSekatan();
            pps.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(kodSyaratSekatan1));
            pps.setPermohonanPlotPelan(mpp);
            devService.simpanPermohonanPlotSekatan(pps);
        }

        devService.simpanPlotPelan(mpp);
        addSimpleMessage("Kod Telah Ditambah");
        getContext().getRequest().setAttribute("idPlot", Boolean.TRUE);
        getContext().getRequest().setAttribute("foredit", Boolean.TRUE);
        LOG.info("---CODE FINISH----.");
        rehydrate();
        listSekatan = mpp.getSenaraiPermohonanPlotSekatan();
        listSyaratNyata = mpp.getSenaraiPermohonanPlotSyaratNyata();
        return new JSP("pembangunan/melaka/LampiranA_popupNyata_SBMS_TSPSS.jsp").addParameter("popup", "true");
    }

    public Resolution hapusSyaratNyata() {
        String idMppSyarat = getContext().getRequest().getParameter("idMppSyarat");
        devService.deletePermohonanPlotSyaratNyata(idMppSyarat);
        getContext().getRequest().setAttribute("idPlot", Boolean.TRUE);
        getContext().getRequest().setAttribute("foredit", Boolean.TRUE);
        idPlot = getContext().getRequest().getParameter("idPlot");
        foredit = getContext().getRequest().getParameter("foredit");
//        return new JSP("pembangunan/melaka/LampiranA_popupNyata_SBMS_TSPSS.jsp").addParameter("popup", "true");
        addSimpleMessage("Kod Telah Dihapus");

        return new RedirectResolution(RencanaRingkasanLampiranAForTSPSSnSBMSActionBean.class, "editNyata").addParameter("idPlot", idPlot).addParameter("foredit", foredit);
    }

    public Resolution hapusSekatanKepentingan() {
        String idMppSekatan = getContext().getRequest().getParameter("idMppSekatan");
        devService.deletePermohonanPlotSekatan(idMppSekatan);
        getContext().getRequest().setAttribute("idPlot", Boolean.TRUE);
        getContext().getRequest().setAttribute("foredit", Boolean.TRUE);
        addSimpleMessage("Kod Telah Dihapus");

        return editSekatan();
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PermohonanPlotPelan> getSenaraiPlotPelan() {
        return senaraiPlotPelan;
    }

    public void setSenaraiPlotPelan(List<PermohonanPlotPelan> senaraiPlotPelan) {
        this.senaraiPlotPelan = senaraiPlotPelan;
    }

    public HakmilikPermohonan getHpTemp() {
        return hpTemp;
    }

    public void setHpTemp(HakmilikPermohonan hpTemp) {
        this.hpTemp = hpTemp;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getSumbanganSaliran() {
        return sumbanganSaliran;
    }

    public void setSumbanganSaliran(String sumbanganSaliran) {
        this.sumbanganSaliran = sumbanganSaliran;
    }

    public List<HakmilikPermohonan> getHpList() {
        return hpList;
    }

    public void setHpList(List<HakmilikPermohonan> hpList) {
        this.hpList = hpList;
    }

    public String getBayaranUkur() {
        return bayaranUkur;
    }

    public void setBayaranUkur(String bayaranUkur) {
        this.bayaranUkur = bayaranUkur;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public BigDecimal getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(BigDecimal kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public BigDecimal getKosInfra() {
        return kosInfra;
    }

    public void setKosInfra(BigDecimal kosInfra) {
        this.kosInfra = kosInfra;
    }

    public String getSumbanganSaliranDesc() {
        return sumbanganSaliranDesc;
    }

    public void setSumbanganSaliranDesc(String sumbanganSaliranDesc) {
        this.sumbanganSaliranDesc = sumbanganSaliranDesc;
    }

    public PermohonanPlotPelan getMpp() {
        return mpp;
    }

    public void setMpp(PermohonanPlotPelan mpp) {
        this.mpp = mpp;
    }

    public List<PermohonanPlotPelan> getListplotpelan() {
        return listplotpelan;
    }

    public void setListplotpelan(List<PermohonanPlotPelan> listplotpelan) {
        this.listplotpelan = listplotpelan;
    }

    public int getBilplotHakmiliktbl() {
        return bilplotHakmiliktbl;
    }

    public void setBilplotHakmiliktbl(int bilplotHakmiliktbl) {
        this.bilplotHakmiliktbl = bilplotHakmiliktbl;
    }

    public BigDecimal getJumluasHakmilik() {
        return jumluasHakmilik;
    }

    public void setJumluasHakmilik(BigDecimal jumluasHakmilik) {
        this.jumluasHakmilik = jumluasHakmilik;
    }

    public int getBilplotHakmiliktemp() {
        return bilplotHakmiliktemp;
    }

    public void setBilplotHakmiliktemp(int bilplotHakmiliktemp) {
        this.bilplotHakmiliktemp = bilplotHakmiliktemp;
    }

    public List<PermohonanPlotPelan> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<PermohonanPlotPelan> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }

    public String getForedit() {
        return foredit;
    }

    public void setForedit(String foredit) {
        this.foredit = foredit;
    }

    public String getHakmilikSementara() {
        return hakmilikSementara;
    }

    public void setHakmilikSementara(String hakmilikSementara) {
        this.hakmilikSementara = hakmilikSementara;
    }

    public Character getPegangan() {
        return pegangan;
    }

    public void setPegangan(Character pegangan) {
        this.pegangan = pegangan;
    }

    public String getPremiumDesc() {
        return premiumDesc;
    }

    public void setPremiumDesc(String premiumDesc) {
        this.premiumDesc = premiumDesc;
    }

    public String getHasilDesc() {
        return hasilDesc;
    }

    public void setHasilDesc(String hasilDesc) {
        this.hasilDesc = hasilDesc;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public String getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(String kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(String kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public String getKodSyaratNyataBaru() {
        return kodSyaratNyataBaru;
    }

    public void setKodSyaratNyataBaru(String kodSyaratNyataBaru) {
        this.kodSyaratNyataBaru = kodSyaratNyataBaru;
    }

    public String getKodSekatanKepentinganBaru() {
        return kodSekatanKepentinganBaru;
    }

    public void setKodSekatanKepentinganBaru(String kodSekatanKepentinganBaru) {
        this.kodSekatanKepentinganBaru = kodSekatanKepentinganBaru;
    }

    public Character getKodPemilikan() {
        return kodPemilikan;
    }

    public void setKodPemilikan(Character kodPemilikan) {
        this.kodPemilikan = kodPemilikan;
    }

    public List<PermohonanPlotPelan> getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(List<PermohonanPlotPelan> permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }

    public BigDecimal getJumlahPremium() {
        return jumlahPremium;
    }

    public void setJumlahPremium(BigDecimal jumlahPremium) {
        this.jumlahPremium = jumlahPremium;
    }

    public BigDecimal getJumlahHasil() {
        return jumlahHasil;
    }

    public void setJumlahHasil(BigDecimal jumlahHasil) {
        this.jumlahHasil = jumlahHasil;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKodKatTanah() {
        return kodKatTanah;
    }

    public void setKodKatTanah(String kodKatTanah) {
        this.kodKatTanah = kodKatTanah;
    }

    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getJenisGuna() {
        return jenisGuna;
    }

    public void setJenisGuna(String jenisGuna) {
        this.jenisGuna = jenisGuna;
    }

    public String getPenjenisan() {
        return penjenisan;
    }

    public void setPenjenisan(String penjenisan) {
        this.penjenisan = penjenisan;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
    }

    public List<PermohonanPlotSyaratNyata> getListSyaratNyata() {
        return listSyaratNyata;
    }

    public void setListSyaratNyata(List<PermohonanPlotSyaratNyata> listSyaratNyata) {
        this.listSyaratNyata = listSyaratNyata;
    }

    public List<PermohonanPlotSekatan> getListSekatan() {
        return listSekatan;
    }

    public void setListSekatan(List<PermohonanPlotSekatan> listSekatan) {
        this.listSekatan = listSekatan;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

}
