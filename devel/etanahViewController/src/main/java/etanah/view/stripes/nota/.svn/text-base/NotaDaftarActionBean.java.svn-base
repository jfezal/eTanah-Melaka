/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * *updated 25012010
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.BadanPengurusanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.model.strata.BadanPengurusan;
import etanah.service.CalcTax;
import etanah.service.NotaService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import etanah.model.Alamat;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodPerintah;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.ListUtil;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/nota/nota_daftar")
public class NotaDaftarActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO kjPengenalanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    NotaService notaService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodUOMDAO koduomDAO;
    @Inject
    KodKategoriTanahDAO kateTanahDAO;
    @Inject
    KodSyaratNyataDAO kodsyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodsekatanKepentinganDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    RegService regService;
    @Inject
    BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPihakKepentinganService hakmikPihakService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kPBDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    CalcTax calcTax;
    @Inject
    KodPerintah kP;
    @Inject
    PelupusanService pelupusanService;
    private static final Logger LOG = Logger.getLogger(NotaDaftarActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    private InfoAudit info;
    private Alamat alamat;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private String tarikhSidang;
    private String tarikhRujukan;
    private String nama;
    private String noPengenalan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private KodNegeri kN;
    //Start 2013-04-15
    private String namaSidang;
    private String noSidang;
    private int tempohTahun;
    private int tempohBulan;
    private int tempohHari;
    //End 2013-04-15
//    add 21/09/ n6a
    private String tarikhDisampai;
    //added 06102010
    private String tarikhKelulusan;
    private String tarikhKKuasa;
    private String tarikhTamat;
    private Date trhKelulusan;
    private Date trhKuatKuasa;
    private Date trhTamat;
    private Date trhDisampai;
    private Date trhSidang;
    private Date trhRujukan;
    private String syerPembilang;
    private String syerPenyebut;
    private KodKategoriTanah kateTanah;
    private KodSekatanKepentingan kodsekatanKepentingan;
    private KodSyaratNyata kodsyaratNyata;
    private PermohonanPihak mpTemp;
    private PermohonanPihak mp;
    private PermohonanPihak mpSalinan;
    private KodUOM koduom;
    private String kodUOM;
    private Hakmilik hakmilik;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private List<HakmilikPihakBerkepentingan> hakmilikPihakList;
    private List<PermohonanPihak> mohonPihakList;
    private List<PermohonanPihak> permohonanPihakList;
    private List<PermohonanAtasPerserahan> mohonAtasSerahList;
    private List<KodKegunaanTanah> senaraiKodKegunaanTanah = new ArrayList<KodKegunaanTanah>();
    private String kodSekatan;
    private String kodSyaratNyata;
    private String urusan;
    private String sekatan;
    private String id;
    private String cukaiBaru;
    private String kodLuas;
    private String katTanah;
    private String luas;
    private String idMohonPihak;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String idH;
    private String[] chkbox;
    private String kodNegeri;
    private KodRujukan kodRujukan;
//    private List<KodKadarCukai> kadarCukaiList;
    private String jenisNotis;
//    private List<Hakmilik> hakmilikList;
    private List<HakmilikPihakBerkepentingan> pbHakmilikList;
    private List<HakmilikPihakBerkepentingan> pbHakmilikTempList;
    private BadanPengurusan bdnUrus;
    private String idPihak;
    private String idHakmilik;
    private Pihak p;
    private String checkP;
    private BigDecimal bakiluas = new BigDecimal(0);
    BigDecimal total = BigDecimal.ZERO;
    private String[] strKodUOM;
    private String[] strKodSyaratNyata;
    private String[] strKodSekatan;
    private String[] strKodKategori;
    private String[] strKodGunaTanah;
    public static final String DELIM = "__^$__";
    @Inject
    StrataPtService strService;

    @Inject
    PermohonanRujukanLuarService permohonanRujukanLuarService;

    private static final String[] KOD_PERINTAH_MAHKAMAH = {"ADMRL", "N8AB"};

    @DefaultHandler
    public Resolution restructureFORM() {
        //TODO: LIST all urusan yang ada perserahan terlibat
        if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("CL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("CB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSSKL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSSKB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBTL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBTB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABTB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABT-K"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABTKB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HLTEB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HLLB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MCLL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HMVB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBBB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSTSL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSTSB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBSTB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBSTL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBKSL"))
                //                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBKBG"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SBKSB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSKB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSKL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSKSB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PSKSL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SSKPL"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("SSKPB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("N6AB"))
                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("N8AB"))) {
            mohonAtasSerahList = permohonan.getSenaraiPermohonanAtasPerserahan();

            LOG.info("Trace MAU " + mohonAtasSerahList.size());
            if (mohonAtasSerahList.size() == 0) {
                LOG.info("Trace Masuk Filter");
                addSimpleError("Tiada Perserahan Terlibat Dipilih");
            } else {
//        String msg = "Peserahan Terlibat Yang Telah Dipilih :<br/> ";
                StringBuilder sb = new StringBuilder("Peserahan Terlibat Yang Telah Dipilih :<br/>");
                for (PermohonanAtasPerserahan mau : mohonAtasSerahList) {
                    sb.append(mau.getUrusan().getIdPerserahan());
                    if (mau.getUrusan() != null) {
                        Hakmilik hm = mau.getUrusan().getHakmilik();
                        sb.append("(").append(hm.getIdHakmilik()).append(")");
                    }
                    sb.append("<br/>");
//          msg += String.valueOf(mau.getUrusan().getIdPerserahan()) + "<br/>";
                }
                addSimpleMessage(sb.toString());
            }
        }
        // Start Azmi: To show Maklumat Pihak For Urusan HTB
        if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB"))) {
            for (PermohonanPihak pp : notaService.listPermohonanPihak(idPermohonan)) {
                setP(pihakDAO.findById(pp.getPihak().getIdPihak()));
            }
            getP();
        }//End Azmi: To show Maklumat Pihak For Urusan HTB

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB"))) {
                LOG.debug("----- ID Permohonan " + idPermohonan);
                bdnUrus = strService.findBdn(permohonan.getPermohonanSebelum().getIdPermohonan());
                noPengenalan = bdnUrus.getPengurusanNoSijil();
            } else {
                if (p != null && StringUtils.isNotBlank(p.getNoPengenalan())) {
                    noPengenalan = p.getNoPengenalan();
                }
            }
        } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB")) {
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new Date());
            senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            if (senaraiPermohonanRujukanLuar.size() <= 0) {
                if (permohonan.getPermohonanSebelum() != null) {
                    bdnUrus = strService.findBdn(permohonan.getPermohonanSebelum().getIdPermohonan());
                    int nobuku;
                    if (bdnUrus != null) {
                        nobuku = strService.maxNoBuku(String.valueOf(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getMilikDaerah()));
                        nobuku = nobuku + 1;
                        PermohonanRujukanLuar permohonanRujukanLuar = strService.permohonanRujukanLuar(String.valueOf(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodHakmilik().getMilikDaerah()),
                                String.valueOf(nobuku));

                        if (permohonanRujukanLuar != null) {
                            addSimpleError("Sila pastikan no buku daftar strata adalah betul! ");
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                        permohonanRujLuar = new PermohonanRujukanLuar();
                        permohonanRujLuar.setNoRujukan(String.valueOf(nobuku));
                        permohonanRujLuar.setTarikhRujukan(permohonan.getInfoAudit().getTarikhMasuk());
                        tarikhRujukan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
                        permohonanRujLuar.setNoFail(bdnUrus.getPengurusanNoRujukan());
                        permohonanRujLuar.setInfoAudit(info);

                        p = new Pihak();
                        p = pihakDAO.findById(bdnUrus.getPihak().getIdPihak());

                    }
                }
            }
        }

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

//        for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {
//            mp = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hPermohonan.getHakmilik().getIdHakmilik());}
        rehydrate();
        return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
    }

    public Resolution notaluascukaiSyarat() {
        return new JSP("daftar/nota/nota_daftar_luascukaisyarat.jsp").addParameter("tab", "true");
    }

    public Resolution pctMelaka() {//special untuk melaka
        return new JSP("daftar/nota/nota_daftar_luascukaisyarat_MELAKA.jsp").addParameter("tab", "true");
    }

    public Resolution showForm() {
        return new JSP("daftar/nota/nota_daftar_default.jsp").addParameter("tab", "true");
    }

    public Resolution notaPengambilan() {//LUAS (HLTE, HLLS, HLLA)
        return new JSP("daftar/nota/nota_daftar_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution notaluasdanCukai() {
        return new JSP("daftar/nota/nota_daftar_luasdancukai.jsp").addParameter("tab", "true");
    }

    public Resolution notaNofail() {//URUSAN PEMBATALAN
        return new JSP("daftar/nota/nota_daftar_nofail.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatUrusaniaga() {
        return new JSP("daftar/nota/nota_daftar_maklumaturusniaga.jsp").addParameter("tab", "true");
    }

    public Resolution maklumatBaru() {
        return new JSP("daftar/nota/nota_daftar_syaratnyata.jsp").addParameter("tab", "true");
    }

    public Resolution urusanTerlibat() {
        return new JSP("daftar/nota/nota_daftar_urusanterlibat.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {

        return new JSP("daftar/nota/popup_kodsyaratnyata.jsp").addParameter("popup", "true");
    }

    public Resolution popupMaklumatKait() {
        if (StringUtils.isNotBlank(id)) {
            LOG.info(id);
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(id));
        }
        return new JSP("daftar/nota/popupMaklumatKait.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSekatan() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            if (hm != null) {
                listKodSekatan = notaService.cariSekatanKepentingan(null, null, hm.getCawangan().getKod());
            } else {
                listKodSekatan = kodsekatanKepentinganDAO.findAll();
            }
        } else {
            listKodSekatan = kodsekatanKepentinganDAO.findAll();
        }

        return new JSP("daftar/nota/carianNsekatan.jsp").addParameter("popup", "true");
    }

    public Resolution showFormCariKodSyaratNyata() {
        listKodSyaratNyata = kodsyaratNyataDAO.findAll();
        return new JSP("daftar/nota/carianNsyarat.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!saveOrUpdate"})
    public void rehydrate() {

        LOG.info("---rehydrate:start---");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri"); //get kod negeri
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idH = getContext().getRequest().getParameter("idH");

        if (idH != null) {
            hakmilik = hakmilikDAO.findById(idH);
        }
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR"))) {
                senaraiPermohonanRujukanLuar = strService.findPermohonanList(idPermohonan, "FL");
            } else {
                senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            }
            LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
            pbHakmilikTempList = new ArrayList<HakmilikPihakBerkepentingan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("MCLL")) {
                if (hakmilikPermohonanList.size() > 0) {
                    calculateCukaiMCLL();
                }
            }
            for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {
                pbHakmilikList = hakmikPihakService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hPermohonan.getHakmilik());
                for (HakmilikPihakBerkepentingan hpb : pbHakmilikList) {
                    LOG.debug("hpb =" + hpb.getHakmilik().getIdHakmilik());
                    //List<HakmilikPihakBerkepentingan> pbHakmilikList = new List;
                    //pbHakmilikTempList = new ArrayList<HakmilikPihakBerkepentingan>();
                    pbHakmilikTempList.add(hpb);
//                mpSalinan = notaService.findByidPermohonanAndHakmilikidPihak(permohonan.getIdPermohonan(), hPermohonan.getHakmilik().getIdHakmilik(), String.valueOf(hpb.getPihak().getIdPihak()));
                }

                mohonPihakList = notaService.listPermohonanPihak(idPermohonan);
            }

            LOG.info("check pihak");
            LOG.info("Kod Urusan" + permohonan.getKodUrusan().getKod());
            if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR"))) {
                for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {
                    mp = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hPermohonan.getHakmilik().getIdHakmilik());
                    if (mp != null) {
                        LOG.info("enter mp check" + mp.getPihak().getNama());
                        p = mp.getPihak();
                        nama = p.getNama();
                        noPengenalan = p.getNoPengenalan();
                        alamat1 = p.getAlamat1();
                        alamat2 = p.getAlamat2();
                        alamat3 = p.getAlamat3();
                        alamat4 = p.getAlamat4();
                        if (p.getPoskod() != null) {
                            poskod = p.getPoskod();
                        }
                        if (p.getNegeri() != null) {
                            negeri = p.getNegeri().getKod();
                        }
                    } else {
                        LOG.info("enter new pihak");
                        p = new Pihak();
                    }
                }
            }

//      if (hakmilikPermohonanList.size() > 0) {  // add by azri 16/7/2013 : for urusan abt-a... 
//        for (HakmilikPermohonan hMohon : hakmilikPermohonanList) {
//          BigDecimal luasTerlibat = hMohon.getLuasTerlibat();
//          BigDecimal luasAsal = hMohon.getHakmilik().getLuas();
//          if (luasTerlibat != null && luasAsal != null) {
//            bakiluas = luasAsal.subtract(luasTerlibat);
//          }
//        }
//      }
        }

        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);
            if (permohonanRujLuar.getTarikhSidang() != null) {
                tarikhSidang = dateFormat.format(permohonanRujLuar.getTarikhSidang()).substring(0, 10);
            }
            if (permohonanRujLuar.getTarikhDisampai() != null) {
                tarikhDisampai = dateFormat.format(permohonanRujLuar.getTarikhDisampai()).substring(0, 10);
            }

            if (permohonanRujLuar.getTarikhRujukan() != null) {
                tarikhRujukan = dateFormat.format(permohonanRujLuar.getTarikhRujukan()).substring(0, 10);
            }
            if (permohonanRujLuar.getTarikhLulus() != null) {
                tarikhKelulusan = dateFormat.format(permohonanRujLuar.getTarikhLulus()).substring(0, 10);
            }
            if (permohonanRujLuar.getTarikhKuatkuasa() != null) {
                tarikhKKuasa = dateFormat.format(permohonanRujLuar.getTarikhKuatkuasa()).substring(0, 10);
            }
            if (permohonanRujLuar.getTarikhTamat() != null) {
                tarikhTamat = dateFormat.format(permohonanRujLuar.getTarikhTamat()).substring(0, 10);
            }

        }
        strKodUOM = new String[hakmilikPermohonanList.size()];
        strKodSyaratNyata = new String[hakmilikPermohonanList.size()];
        strKodSekatan = new String[hakmilikPermohonanList.size()];
        strKodKategori = new String[hakmilikPermohonanList.size()];
        strKodGunaTanah = new String[hakmilikPermohonanList.size()];

        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("TSSKL")
                || permohonan.getKodUrusan().getKod().equalsIgnoreCase("SSKPL")) {
            int i = 0;
            for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {

                if (hPermohonan.getSyaratNyataBaru() != null) {
//                    if (hPermohonan.getSyaratNyataBaru().getKod().length() > 7) {
//                        strKodSyaratNyata[i] = hPermohonan.getSyaratNyataBaru().getKod().substring(2);
//                    } else {
//                        strKodSyaratNyata[i] = hPermohonan.getSyaratNyataBaru().getKod();
//                    }
                    strKodSyaratNyata[i] = hPermohonan.getSyaratNyataBaru().getKod().substring(2);

                }
                if (hPermohonan.getSekatanKepentinganBaru() != null) {
//                    if (hPermohonan.getSekatanKepentinganBaru().getKod().length() > 7) {
//                        strKodSekatan[i] = hPermohonan.getSekatanKepentinganBaru().getKod().substring(2);
//                    } else {
//                        strKodSekatan[i] = hPermohonan.getSekatanKepentinganBaru().getKod();
//                    }
                    strKodSekatan[i] = hPermohonan.getSekatanKepentinganBaru().getKod().substring(2);
                }
                i++;

            }
        }

        LOG.info("rehydrate:finish");
    }

    public void calculateCukaiMCLL() //Kira cukai untuk MCLL nak manual lak dammmmmmmm
    {
        LOG.info("Entering Calculate Cukai MCLL METHOD");
        //TODO: Update at Kelulusan LEVEL
        String kod_urus = permohonan.getKodUrusan().getKod();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        //calculate cukai MCLL (1/2 from Cukai Asal) add if < 6.00 set 6.00
        if (StringUtils.equalsIgnoreCase(kod_urus, "MCLL")) {
            for (HakmilikPermohonan hmP : hakmilikPermohonanList) {
                Integer plus2 = 1;

                info.setTarikhMasuk(hmP.getInfoAudit().getTarikhMasuk());
                BigDecimal compareTO = new BigDecimal(6);
                BigDecimal testValue = hmP.getHakmilik().getCukai().divide(BigDecimal.valueOf(2));
                if (testValue.compareTo(compareTO) < 0) {
                    LOG.info("Test");
                    hmP.setCukaiBaru(compareTO);
                } else {

                    hmP.setCukaiBaru(hmP.getHakmilik().getCukai().divide(BigDecimal.valueOf(2)).setScale(0, RoundingMode.UP));
                    LOG.info("Value" + plus2 + "::" + hmP.getHakmilik().getCukai().divide(BigDecimal.valueOf(2)));
                }
                plus2++;
                hmP.setInfoAudit(info);
                hmP.setPermohonan(permohonan);

                notaService.simpanSingle(hmP);

            }

        }

    }

    public void savePihakPengurusan() //Kira cukai untuk MCLL
    {
        LOG.info("Start Save PihakPengurusan");

        //TODO: Update at Kelulusan LEVEL
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        KodJenisPihakBerkepentingan jPB = new KodJenisPihakBerkepentingan();
        String kodPihak = getContext().getRequest().getParameter("mp.jenis.kod");
        LOG.info("--kod pihak--:" + kodPihak);

        jPB = kPBDAO.findById(kodPihak); // will change to BP
        KodJenisPengenalan jPengenalan = new KodJenisPengenalan();
        jPengenalan = kjPengenalanDAO.findById("S");
//        JOptionPane.showMessageDialog(null, p.getNama());
        if (pihakDAO.findById(p.getIdPihak()) == null) {
            p.setInfoAudit(info);
            p.setJenisPengenalan(jPengenalan);
            notaService.simpanPihak(p);//End Save Pihak

            for (HakmilikPermohonan hmP : hakmilikPermohonanList) {
                //mp = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hmP.getHakmilik().getIdHakmilik());

                LOG.info("--Start Save MohonPihak--");
                mp = new PermohonanPihak();
                mp.setPihak(p);
                mp.setInfoAudit(info);
                mp.setPermohonan(permohonan);
                mp.setHakmilik(hmP.getHakmilik());
                mp.setCawangan(peng.getKodCawangan());
                mp.setJenis(jPB);
                mp.setSyerPembilang(1);
                mp.setSyerPenyebut(1);
                mp.setNama(p.getNama());
                mp.setJenisPengenalan(jPengenalan);
                mp.setNoPengenalan(p.getNoPengenalan());
                alamat = new Alamat();
                alamat.setAlamat1(p.getAlamat1());
                alamat.setAlamat2(p.getAlamat2());
                alamat.setAlamat3(p.getAlamat3());
                alamat.setAlamat4(p.getAlamat4());
                alamat.setPoskod(p.getPoskod());
                alamat.setNegeri(p.getNegeri());
                mp.setAlamat(alamat);
                notaService.simpanMohon(mp);
                LOG.info("--End Save MohonPihak--");
            }
        } else {
            Pihak pUpdate = pihakDAO.findById(p.getIdPihak());

            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            pUpdate.setInfoAudit(info);
            pUpdate.setNama(p.getNama());
            pUpdate.setNoPengenalan(p.getNoPengenalan());
            pUpdate.setAlamat1(p.getAlamat1());
            pUpdate.setAlamat2(p.getAlamat2());
            pUpdate.setAlamat3(p.getAlamat3());
            pUpdate.setAlamat4(p.getAlamat4());
            pUpdate.setPoskod(p.getPoskod());
            pUpdate.setNegeri(p.getNegeri());
            notaService.simpanPihak(pUpdate);

            for (HakmilikPermohonan hmP : hakmilikPermohonanList) {
                LOG.info("--Start Update MohonPihak--");
                mp = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hmP.getHakmilik().getIdHakmilik());
                if (mp == null) {
                    mp = new PermohonanPihak();
                }
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                mp.setInfoAudit(info);
                mp.setNama(p.getNama());
                mp.setJenis(jPB);
                mp.setJenisPengenalan(jPengenalan);
                mp.setNoPengenalan(p.getNoPengenalan());
                mp.setCawangan(permohonan.getCawangan());
                mp.setSyerPembilang(1);
                mp.setSyerPenyebut(1);
                mp.setJumlahPembilang(1);
                mp.setJumlahPenyebut(1);
                mp.setPermohonan(permohonan);
                mp.setHakmilik(permohonan.getSenaraiHakmilik().get(0).getHakmilik());
                alamat = new Alamat();
                alamat.setAlamat1(p.getAlamat1());
                alamat.setAlamat2(p.getAlamat2());
                alamat.setAlamat3(p.getAlamat3());
                alamat.setAlamat4(p.getAlamat4());
                alamat.setPoskod(p.getPoskod());
                alamat.setNegeri(p.getNegeri());
                mp.setAlamat(alamat);
                mp.setPihak(p);
                notaService.simpanMohon(mp);
                permohonanPihakDAO.saveOrUpdate(mp);
                permohonanPihakDAO.save(mp);
                LOG.info("--End Update MohonPihak--");
            }
        }
        LOG.info("--End Save PihakPengurusan--");
    }
//
//                info.setTarikhMasuk(hmP.getInfoAudit().getTarikhMasuk());
//                BigDecimal compareTO = new BigDecimal(6);
//                BigDecimal testValue = hmP.getHakmilik().getCukai().divide(BigDecimal.valueOf(2));
//                if (testValue.compareTo(compareTO) < 0) {
//                    LOG.info("Test");
//                    hmP.setCukaiBaru(compareTO);
//                } else {
//                    hmP.setCukaiBaru(hmP.getHakmilik().getCukai().divide(BigDecimal.valueOf(2)));//
//                }
//                hmP.setInfoAudit(info);
//                hmP.setPermohonan(permohonan);
//                notaService.simpanSingle(hmP);
////            }

//    public Resolution kiraCukai() {
//        String kodTanah = (String) getContext().getRequest().getParameter("kodTanah");
//        String kodBpm = (String) getContext().getRequest().getParameter("kodBpm");
//        String unit = (String) getContext().getRequest().getParameter("kodUnitLuas");//hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod();//hakmilik.getKodUnitLuas().getKod();
//        LOG.debug("kodBpm : " + kodBpm);
//        LOG.debug("kodTanah : " + kodTanah);
//
//        String result = "0";
//        if (StringUtils.isNotBlank(kodBpm) && StringUtils.isNotBlank(kodTanah)) {
//            kadarCukaiList = regService.searchKadarCukai(kodBpm, kodTanah);
//            double q = 0;
//            BigDecimal conv = BigDecimal.ZERO;
//            BigDecimal totalLuasByM = BigDecimal.ZERO;
//            BigDecimal kC = BigDecimal.ZERO;
//            if (kadarCukaiList.size() > 0) {
//                kC = kadarCukaiList.get(0).getKadarMeterPersegi();
//
//                //unit conversion taken from http://en.wikipedia.org/wiki/Conversion_of_units
//                //ekar
//                LOG.debug("kod_kat_tanah : " + hakmilik.getKategoriTanah().getKod());
//                if (unit.equals("D") || unit.equals("E") || unit.equals("P")) {
//                    q = 4046.8564224;
//                    conv = BigDecimal.valueOf(q);
//                    totalLuasByM = hakmilik.getLuas().multiply(conv);
//                    total = kC.multiply(totalLuasByM);
//                    //kaki persegi
//                } else if (unit.equals("K")) {
//                    q = 0.09290304;
//                    conv = BigDecimal.valueOf(q);
//                    totalLuasByM = hakmilik.getLuas().multiply(conv);
//                    total = kC.multiply(totalLuasByM);
//                    //hektar
//                } else if (unit.equals("H")) {
//                    q = 10000;
//                    conv = BigDecimal.valueOf(q);
//                    totalLuasByM = hakmilik.getLuas().multiply(conv);
//                    total = kC.multiply(totalLuasByM);
//                } else {
//                    total = kC.multiply(hakmilik.getLuas());
//                }
//                //if not pertanian get Kadar Minimum
//                if (!hakmilik.getKategoriTanah().getKod().equals("3")) {
//                    BigDecimal minima = kadarCukaiList.get(0).getKadarMinimum();
//                    total = total.max(minima);
//                }
//                //round up the total
//                total = total.setScale(0, RoundingMode.UP);
//            } else {
//                total = BigDecimal.ZERO;
//            }
//            result = String.valueOf(total);
//            LOG.debug("result :" + result);
//        }
//        return new StreamingResolution("text/plain", result);
//    }
    public Resolution simpanSingle() { //Mohon Hakmilik
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(id));
            info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk());
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            LOG.debug("Luas ::" + luas);
            LOG.debug("Cukai Baru ::" + cukaiBaru);
            if (luas != null) {
                if (!luas.equals("undefined")) {
                    LOG.debug(luas);
                    if (kodLuas.equals("0"));
                    {
                        koduom = koduomDAO.findById(kodLuas);
                    }
                    BigDecimal luasTerlibat = new BigDecimal(luas);
                    hakmilikPermohonan.setLuasTerlibat(luasTerlibat);
                    hakmilikPermohonan.setKodUnitLuas(koduom);
                }
                if (!cukaiBaru.equals("undefined")) {
                    BigDecimal cukai = new BigDecimal(cukaiBaru);
                    hakmilikPermohonan.setCukaiBaru(cukai);
                }
                if (StringUtils.isNotBlank(katTanah)) {
                    kateTanah = kateTanahDAO.findById(katTanah);
                    hakmilikPermohonan.setKategoriTanahBaru(kateTanah);
                }
                if (StringUtils.isNotBlank(kodSyaratNyata)) {
                    kodsyaratNyata = kodsyaratNyataDAO.findById(kodSyaratNyata);
                    hakmilikPermohonan.setSyaratNyataBaru(kodsyaratNyata);

                }
                if (StringUtils.isNotBlank(kodSekatan)) {
                    kodsekatanKepentingan = kodsekatanKepentinganDAO.findById(kodSekatan);
                    hakmilikPermohonan.setSekatanKepentinganBaru(kodsekatanKepentingan);
                }

                hakmilikPermohonan.setInfoAudit(info);
                hakmilikPermohonan.setPermohonan(permohonan);
                notaService.simpanSingle(hakmilikPermohonan);
                addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");

            }
        }

        return new RedirectResolution(NotaDaftarActionBean.class);

    }

    public Resolution simpanPukal() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
//            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(id));

            List<HakmilikPermohonan> hPermohonanList = permohonan.getSenaraiHakmilik();

            info = peng.getInfoAudit();
            info.setDimasukOleh(peng);
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            LOG.debug("kateTanah ::" + kateTanah);
            LOG.debug("kodSyarat::" + kodSyaratNyata);

            for (HakmilikPermohonan hp : hPermohonanList) {
                info.setTarikhMasuk(hp.getInfoAudit().getTarikhMasuk());

                if (StringUtils.isNotBlank(katTanah)) {
                    kateTanah = kateTanahDAO.findById(katTanah);
                    hp.setKategoriTanahBaru(kateTanah);
                }
                if (StringUtils.isNotBlank(kodSyaratNyata)) {
                    kodsyaratNyata = kodsyaratNyataDAO.findById(kodSyaratNyata);
                    hp.setSyaratNyataBaru(kodsyaratNyata);

                }
                if (StringUtils.isNotBlank(kodSekatan)) {
                    kodsekatanKepentingan = kodsekatanKepentinganDAO.findById(kodSekatan);
                    hp.setSekatanKepentinganBaru(kodsekatanKepentingan);
                }

                hp.setInfoAudit(info);
                hp.setPermohonan(permohonan);
                notaService.simpanSingle(hp);

            }
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
        }
        return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKodSekatanBaru() {
        id = getContext().getRequest().getParameter("idHp");
        if (StringUtils.isNotBlank(id)) {
            HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(id));
            if (hp != null) {
                hp.setSekatanKepentinganBaru(null);
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            }
        }
        return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
    }

    public Resolution deleteKodSyaratNyata() {
        id = getContext().getRequest().getParameter("idHp");
        if (StringUtils.isNotBlank(id)) {
            HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(Long.parseLong(id));
            if (hp != null) {
                hp.setSyaratNyataBaru(null);
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            }
        }
        return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBerkelompok() {
        int counter = 0;
        boolean err = true;
        boolean err1 = true;
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
            Hakmilik hm = hakmilikPermohonan.getHakmilik();
            LOG.debug("hakmilikPermohonanList Size:" + hakmilikPermohonanList.size() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            //LOG.debug("hakmilikPermohonanList koduom updated:" + hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod() + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            LOG.debug("strKodKategori[counter]):" + strKodKategori[counter]);
            LOG.debug("strKodGunaTanah[counter]:" + strKodGunaTanah[counter]);

            if (StringUtils.isNotBlank(strKodKategori[counter])) {
                KodKategoriTanah kkt = kateTanahDAO.findById(strKodKategori[counter]);
                if (kkt != null) {
                    hakmilikPermohonan.setKategoriTanahBaru(kkt);
                    hakmilikPermohonan.setJenisPenggunaanTanah(hakmilikPermohonan.getHakmilik().getKategoriTanah());
                }
            }

            if (StringUtils.isNotBlank(strKodGunaTanah[counter])) {
                senaraiKodKegunaanTanah = pelupusanService.getSenaraiKegunaanTanahByKodGunaTanah(strKodGunaTanah[counter]);
                LOG.debug("senaraiKodKegunaanTanah.size() =" + senaraiKodKegunaanTanah.size());
                if (senaraiKodKegunaanTanah.size() > 0) {
                    hakmilikPermohonan.setKodKegunaanTanah(senaraiKodKegunaanTanah.get(0));
                }
            }

            if (StringUtils.isNotBlank(strKodUOM[counter])) {
                LOG.debug("hakmilikPermohonanList kod:" + strKodUOM[counter] + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                KodUOM kuom = kodUOMDAO.findById(strKodUOM[counter]);
                if (kuom != null) {
                    hakmilikPermohonan.setKodUnitLuas(kuom);
                }
            }

            if (StringUtils.isNotBlank(strKodSyaratNyata[counter])) {
                LOG.debug("hakmilikPermohonanListsyarat:" + strKodSyaratNyata[counter] + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                String kodSyaratNyata = new String();
                if (strKodSyaratNyata[counter].length() == 7) {
                    LOG.debug("masuk sini syarat");
//                    kodSyaratNyata = hm.getCawangan().getKod() + strKodSyaratNyata[counter];
                    kodSyaratNyata = strKodSyaratNyata[counter];
                } else {
                    kodSyaratNyata = strKodSyaratNyata[counter];
                }
                LOG.debug("kodSyaratNyata ================= " + hm.getCawangan().getKod() + strKodSyaratNyata[counter]);
                KodSyaratNyata ksn = regService.searchKodSyaratByCaw(kodSyaratNyata, hm.getCawangan().getKod());
                LOG.debug("kod ================= " + ksn);
                if (ksn != null) {
                    err = true;
                    hakmilikPermohonan.setSyaratNyataBaru(ksn);
                    hakmilikPermohonan.setSyaratNyata(hakmilikPermohonan.getHakmilik().getSyaratNyata());
                } else {
                    err = false;
                    addSimpleError("Kod Syarat Nyata tidak dijumpai");
                }
            }

            if (StringUtils.isNotBlank(strKodSekatan[counter])) {
                LOG.debug("hakmilikPermohonanList sekatan:" + strKodSekatan[counter] + " : idhakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                String kodSekatan = new String();
                if (strKodSekatan[counter].length() == 7) {

                    if (("04".equals(conf.getProperty("kodNegeri")))) {

                        LOG.debug("masuk sini sekatan");
                        kodSekatan = strKodSekatan[counter];

                    } else {

//                        kodSekatan =  hm.getCawangan().getKod() + strKodSekatan[counter];
                        kodSekatan = strKodSekatan[counter];
                    }

                } else {

                    kodSekatan = strKodSekatan[counter];
                }

                KodSekatanKepentingan ksk = regService.searchKodSekatanByCaw(kodSekatan, hm.getCawangan().getKod());
                LOG.debug("kod sekatan ================= " + ksk);
                if (ksk != null) {
                    err1 = true;
                    hakmilikPermohonan.setSekatanKepentinganBaru(ksk);
                    hakmilikPermohonan.setSekatanKepentingan(hakmilikPermohonan.getHakmilik().getSekatanKepentingan());
                } else {
                    err1 = false;
                    addSimpleError("Kod Sekatan tidak dijumpai");
                }
            }
            counter = counter + 1;
        }

        if (hakmilikPermohonanList.size() > 0) {
            hakmilikPermohonanService.saveHakmilikPermohonan(hakmilikPermohonanList);
            if ((err) && (err1)) {
                addSimpleMessage("Kemaskini Data Berjaya");
            }

        }

//        return new JSP("common/paparan_maklumat_hakmilik_permohonan_terperinci.jsp").addParameter("tab", "true");
        return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
    }

    public Resolution padam() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(id));
        info = peng.getInfoAudit();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk());
        info.setDiKemaskiniOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        //
        hakmilikPermohonan.setLuasTerlibat(null);
        hakmilikPermohonan.setKodUnitLuas(null);
        hakmilikPermohonan.setCukaiBaru(null);
        hakmilikPermohonan.setInfoAudit(info);

        notaService.simpanSingle(hakmilikPermohonan);
        addSimpleMessage("Maklumat Telah Dikosongkan..");

        return new RedirectResolution(NotaDaftarActionBean.class);
    }

    public Resolution simpanMaklumatluascukaisyarat() throws ParseException {

        LOG.info("--simpanMaklumatluascukaisyarat--");

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));
        permohonanRujLuar.setPermohonan(permohonan);
        tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
        tarikhRujukan = getContext().getRequest().getParameter("tarikhRujukan");
        LOG.debug("----- 1 no fail " + getContext().getRequest().getParameter("permohonanRujLuar.noRujukan"));
//        JOptionPane.showMessageDialog(null, tarikhKKuasa);
        InfoAudit in = permohonan.getInfoAudit();
        if (idPermohonan != null) {
//     savePihakPengurusan();
            List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
            //Added by Aizuddin so mohon rujuk luar save on all hakmilik
//            for (int i = 0; i < senaraiHakmilik.size(); i++) {
//                HakmilikPermohonan hakmilikPermohonan1 = senaraiHakmilik.get(i);

//                for (HakmilikPermohonan hp : senaraiHakmilik) {
//                }
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                permohonanRujLuar = permohonanRujukanLuarService.findMohonRujukLuarIdHakmilikIdPermohonan(hp.getHakmilik().getIdHakmilik(), idPermohonan);

//                LOG.debug("------------- hp.gethakmilik :" + hp.getHakmilik().getIdHakmilik());
//                if ((senaraiPermohonanRujukanLuar.isEmpty() || prl == null)) {
                if (permohonanRujLuar == null) {
//                in.setDimasukOleh(peng);
//                in.setTarikhMasuk(new java.util.Date());
                    permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(info);
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("N6A")) {//N6A
                        if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
                            kodRujukan = kodRujukanDAO.findById("WT");
                        } else {
                            kodRujukan = kodRujukanDAO.findById("FL");
                        }
                    } else {
                        kodRujukan = kodRujukanDAO.findById("FL");
                    }

                    if (StringUtils.isNotBlank(tarikhDisampai)) {
                        trhDisampai = dateFormat.parse(tarikhDisampai);
                        permohonanRujLuar.setTarikhDisampai(trhDisampai);
                    } else {
                        permohonanRujLuar.setTarikhDisampai(null);
                    }
                    if (StringUtils.isNotBlank(tarikhRujukan)) {
                        trhRujukan = dateFormat.parse(tarikhRujukan);
                        permohonanRujLuar.setTarikhRujukan(trhRujukan);
                    } else {
                        permohonanRujLuar.setTarikhRujukan(null);
                    }
                    if (StringUtils.isNotBlank(tarikhSidang)) {
                        trhSidang = dateFormat.parse(tarikhSidang);
                        permohonanRujLuar.setTarikhSidang(trhSidang);
                    } else {
                        permohonanRujLuar.setTarikhSidang(null);
                    }
                    //added 06102010
                    if (StringUtils.isNotBlank(tarikhKelulusan)) {
                        trhKelulusan = dateFormat.parse(tarikhKelulusan);
                        permohonanRujLuar.setTarikhLulus(trhKelulusan);
                    }

                    if (StringUtils.isNotBlank(tarikhKKuasa)) {
                        trhKuatKuasa = dateFormat.parse(tarikhKKuasa);
                        permohonanRujLuar.setTarikhKuatkuasa(trhKuatKuasa);
                    }
                    if (StringUtils.isNotBlank(tarikhTamat)) {
                        trhTamat = dateFormat.parse(tarikhTamat);
                        permohonanRujLuar.setTarikhTamat(trhTamat);
                    }

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR")) {
                        savePihakPengurusan();
//                    JOptionPane.showMessageDialog(null,permohonan.getKodUrusan().getKod());
                    }

                    if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB"))
                            || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTT"))
                            || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTBT"))) {
                        LOG.info("--Enter sava pihak pengurusan--");
                        savePihakPengurusan();
//                    JOptionPane.showMessageDialog(null,permohonan.getKodUrusan().getKod());
                    }

// --- add by azri: fix problem Nofail and noRuj can't save  
                    String noFail = getContext().getRequest().getParameter("permohonanRujLuar.noFail");
                    String noWarta = getContext().getRequest().getParameter("permohonanRujLuar.noRujukan");
                    String item = getContext().getRequest().getParameter("permohonanRujLuar.item");
                    permohonanRujLuar.setNoFail(noFail);
                    if (StringUtils.isNotBlank(noWarta)) {
                        permohonanRujLuar.setNoRujukan(noWarta);
                    } else {
                        permohonanRujLuar.setNoRujukan(null);
                    }
                    if (StringUtils.isNotBlank(item)) {
                        permohonanRujLuar.setItem(item);
                    }
                    if (StringUtils.isNotBlank(getContext().getRequest().getParameter("permohonanRujLuar.catatan"))) {
                        permohonanRujLuar.setCatatan(getContext().getRequest().getParameter("permohonanRujLuar.catatan"));
                    }
// -- END 
                    //Start 2013-04-15
                    namaSidang = getContext().getRequest().getParameter("permohonanRujLuar.namaSidang");
                    noSidang = getContext().getRequest().getParameter("permohonanRujLuar.noSidang");
                    String tempohT = getContext().getRequest().getParameter("permohonanRujLuar.tempohTahun");
                    String tempohB = getContext().getRequest().getParameter("permohonanRujLuar.tempohBulan");
                    String tempohH = getContext().getRequest().getParameter("permohonanRujLuar.tempohHari");

                    if (StringUtils.isNotBlank(tempohT)) {
                        tempohTahun = Integer.parseInt(tempohT);
                    }
                    if (StringUtils.isNotBlank(tempohB)) {
                        tempohBulan = Integer.parseInt(tempohB);
                    }
                    if (StringUtils.isNotBlank(tempohB)) {
                        tempohHari = Integer.parseInt(tempohH);
                    }
                    //SET KOD PERINTAH MAHKAMAH 
                    if (ArrayUtils.contains(KOD_PERINTAH_MAHKAMAH, permohonan.getKodUrusan().getKod())) {
                        kP = new KodPerintah();
                        kP.setKod("M");
                        permohonanRujLuar.setKodPerintah(kP);
                    }

                    permohonanRujLuar.setNamaSidang(namaSidang);
                    permohonanRujLuar.setNoSidang(noSidang);
                    permohonanRujLuar.setTempohTahun(tempohTahun);
                    permohonanRujLuar.setTempohBulan(tempohBulan);
                    permohonanRujLuar.setTempohHari(tempohHari);
                    //permohonanRujLuar.setTarikhTamat(trhTamat););
                    //End   2013-04-15

                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    permohonanRujLuar.setPermohonan(permohonan);
                    permohonanRujLuar.setHakmilik(hp.getHakmilik());
                    permohonanRujLuar.setInfoAudit(in);
                    notaService.simpanPermohonanRujLuar(permohonanRujLuar);
                }
                //addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
            } 
            if (permohonanRujLuar != null) { 
                    LOG.info("--Enter Update record--");
//                    LOG.debug("------------- hp.gethakmilik2 :" + hp.getHakmilik().getIdHakmilik());
//                    List<PermohonanRujukanLuar> senaraiRujukLuar = senaraiPermohonanRujukanLuar;

                    
                    for (PermohonanRujukanLuar mrl : senaraiPermohonanRujukanLuar){
//                    for (int y = 0; y < senaraiRujukLuar.size(); y++) {
//                        permohonanRujLuar = senaraiRujukLuar.get(y);
                        permohonanRujLuar = mrl;
                        LOG.debug("---> ID Permohon ruj luar : " + permohonanRujLuar.getIdRujukan());
                        in.setDimasukOleh(peng);
                        in.setTarikhMasuk(permohonan.getInfoAudit().getTarikhMasuk());
                        in.setDiKemaskiniOleh(peng);
                        in.setTarikhKemaskini(new java.util.Date());

                        permohonanRujLuar.setNoFail(getContext().getRequest().getParameter("permohonanRujLuar.noFail"));
                        permohonanRujLuar.setNoRujukan(getContext().getRequest().getParameter("permohonanRujLuar.noRujukan"));
                        permohonanRujLuar.setItem(getContext().getRequest().getParameter("permohonanRujLuar.item"));
                        permohonanRujLuar.setNamaSidang(getContext().getRequest().getParameter("permohonanRujLuar.namaSidang"));
                        permohonanRujLuar.setNoSidang(getContext().getRequest().getParameter("permohonanRujLuar.noSidang"));
                        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("permohonanRujLuar.catatan"))) {
                            permohonanRujLuar.setCatatan(getContext().getRequest().getParameter("permohonanRujLuar.catatan"));
                        }
                        //SET KOD PERINTAH MAHKAMAH 
                        if (ArrayUtils.contains(KOD_PERINTAH_MAHKAMAH, permohonan.getKodUrusan().getKod())) 
                        {
                            kP = new KodPerintah();
                            kP.setKod("M");
                            permohonanRujLuar.setKodPerintah(kP);
                        }
                        
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("N6A")) {//N6A
                            if (StringUtils.isNotBlank(permohonanRujLuar.getNoRujukan())) {
                                kodRujukan = kodRujukanDAO.findById("WT");
                            } else {
                                kodRujukan = kodRujukanDAO.findById("FL");
                            }
                        } else {
                            kodRujukan = kodRujukanDAO.findById("FL");
                        }
                        if (StringUtils.isNotBlank(tarikhDisampai)) {
                            trhDisampai = dateFormat.parse(tarikhDisampai);
                            permohonanRujLuar.setTarikhDisampai(trhDisampai);
                        } else {
                            permohonanRujLuar.setTarikhDisampai(null);
                        }

                        if (StringUtils.isNotBlank(tarikhSidang)) {
                            trhSidang = dateFormat.parse(tarikhSidang);
                            permohonanRujLuar.setTarikhSidang(trhSidang);
                        } else {
                            permohonanRujLuar.setTarikhSidang(null);
                        }

                        if (StringUtils.isNotBlank(tarikhRujukan)) {
                            trhRujukan = dateFormat.parse(tarikhRujukan);
                            permohonanRujLuar.setTarikhRujukan(trhRujukan);
                        } else {
                            permohonanRujLuar.setTarikhRujukan(null);
                        }

                        if (StringUtils.isNotBlank(tarikhKelulusan)) {
                            trhKelulusan = dateFormat.parse(tarikhKelulusan);
                            permohonanRujLuar.setTarikhLulus(trhKelulusan);
                        } else {
                            permohonanRujLuar.setTarikhLulus(null);
                        }

                        if (StringUtils.isNotBlank(tarikhKKuasa)) {
                            trhKuatKuasa = dateFormat.parse(tarikhKKuasa);
                            permohonanRujLuar.setTarikhKuatkuasa(trhKuatKuasa);
                        } else {
                            permohonanRujLuar.setTarikhKuatkuasa(null);
                        }

                        if (StringUtils.isNotBlank(tarikhTamat)) {
                            trhTamat = dateFormat.parse(tarikhTamat);
                            permohonanRujLuar.setTarikhTamat(trhTamat);
                        } else {
                            permohonanRujLuar.setTarikhTamat(null);
                        }

                        permohonanRujLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujLuar.setKodRujukan(kodRujukan);
                        permohonanRujLuar.setInfoAudit(in);
//          permohonanRujLuar.setHakmilik(hakmilikPermohonan1.getHakmilik());
                        notaService.simpanPermohonanRujLuar(permohonanRujLuar);
                        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR")) {
                            savePihakPengurusan();
                        }
                        if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTB"))
                                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTT"))
                                || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("HTBT"))) {
                            savePihakPengurusan();
                        }
                    }
                }
//            }
            addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        }

        if (permohonan.getKodUrusan()
                .getKod().equalsIgnoreCase("TSSKB")) { //    added by azri: URUSAN TSSKB only
            return new JSP("daftar/nota/nota_daftar_luascukaisyarat.jsp").addParameter("tab", "true");
        } else {
            return new JSP("daftar/nota/nota_daftar_recreate.jsp").addParameter("tab", "true");
        }
    }

    public Resolution simpanNofail() { //urusan Batal

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());
        kodRujukan = kodRujukanDAO.findById("FL");
        if (idPermohonan != null) {
            permohonanRujLuar.setCawangan(peng.getKodCawangan());
            permohonanRujLuar.setPermohonan(permohonan);
            permohonanRujLuar.setKodRujukan(kodRujukan);
            notaService.saveOrUpdate(permohonanRujLuar, peng, permohonan);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");

        }

        return new JSP("daftar/nota/nota_daftar_nofail.jsp").addParameter("tab", "true");

    }

//    SHSK
    public Resolution savePemegangSalinan() {
        LOG.info("Enter Save PemegangsaSalainan");
//       mp = notaService.findByidPermohonanAndHakmilik(permohonan.getIdPermohonan(), hPermohonan.getHakmilik().getIdHakmilik());
//         mpSalinan = notaService.
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        KodJenisPihakBerkepentingan kp = kPBDAO.findById("TB");
        HakmilikPihakBerkepentingan hakmilikPihak;
        LOG.info("Get idPihak" + idPihak);
        LOG.info("Get idHakmilik" + idHakmilik);
        Hakmilik h = hakmilikDAO.findById(idHakmilik);

        HakmilikPermohonan mh = notaService.findByidPermohonanAndHakmilikMohonHakmilik(permohonan.getIdPermohonan(), idHakmilik);
        Pihak Ph = pihakDAO.findById(Long.valueOf(idPihak));
        LOG.info("Check Current MP");
        LOG.info("Check PihakKepentingan");
        hakmilikPihak = hakmikPihakService.findHakmilikPihakByPihak(Long.valueOf(idPihak), h);
        mpSalinan = notaService.findByidPermohonanAndHakmilikidPihak(permohonan.getIdPermohonan(), idHakmilik, idPihak);

        if (mpSalinan == null) {
            LOG.info("Save New Mohon Pihak");
            mpSalinan = new PermohonanPihak();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            mpSalinan.setInfoAudit(ia);
            mpSalinan.setHakmilik(h);
            mpSalinan.setPermohonan(permohonan);
            mpSalinan.setPihak(Ph);
            mpSalinan.setCawangan(peng.getKodCawangan());
            mpSalinan.setJenis(kp);
            mpSalinan.setSyerPembilang(Integer.parseInt(syerPembilang));
            mpSalinan.setSyerPenyebut(Integer.parseInt(syerPenyebut));

            if (hakmilikPihak != null) {
                LOG.info("Strt set no salinan lama ");

                mpSalinan.setDalamanNilai1(String.valueOf(hakmilikPihak.getNoSalinan() + 1));
            }
            if (mh != null) {
                mh.setLokasi(String.valueOf(h.getNoVersiDhde() + 1)); // DHde sementara
                mh.setNoPajakan(String.valueOf(h.getNoVersiDhke() + 1)); // DHke sementara
            }

            notaService.simpanMohon(mpSalinan);
            notaService.simpanSingle(mh);
            checkP = idPihak;
        } else {
            LOG.info("Save Existing Mohon Pihak");
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(mpSalinan.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            mpSalinan.setInfoAudit(ia);
            mpSalinan.setHakmilik(h);
            mpSalinan.setPihak(Ph);
            if (hakmilikPihak != null) {
                LOG.info("Strt set no salinan lama");
                mpSalinan.setDalamanNilai1(String.valueOf(hakmilikPihak.getNoSalinan() + 1));
            }

            if (mh != null) {
                mh.setLokasi(String.valueOf(h.getNoVersiDhde() + 1)); // DHde sementara
                mh.setNoPajakan(String.valueOf(h.getNoVersiDhke() + 1)); // DHke sementara
            }
            notaService.simpanMohon(mpSalinan);
            notaService.simpanSingle(mh);
            checkP = idPihak;

        }

        return new RedirectResolution(NotaDaftarActionBean.class);

    }

    public Resolution deletePemohon() {
        LOG.info("Enter Delete Mohon Pihak");
        LOG.info("idMohonPihak ::" + idMohonPihak);

        PermohonanPihak mPihakDelete = new PermohonanPihak();
        mPihakDelete = permohonanPihakDAO.findById(Long.valueOf(idMohonPihak));
        LOG.debug(mPihakDelete);
        notaService.deleteMohon(mPihakDelete);

        return new RedirectResolution(NotaDaftarActionBean.class);
    }

    //need to modify
    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
//        if (kodSekatan != null) {
//
//            listKodSekatan = regService.searchKodSekatan(kodSekatan, "%", null);
//
//            LOG.debug("listKodSekatan.size :" + listKodSekatan.size());
//            if (listKodSekatan.size() < 1) {
//                addSimpleError("Kod Sekatan Tidak Sah");
//            }
//        } else {
//            listKodSekatan = regService.searchKodSekatan("%", "%", null);
//            LOG.info(listKodSekatan.size());
//        }
//
//        if (listKodSekatan.isEmpty()) {
//            addSimpleError("Kod Sekatan Tidak Sah");
//        }
//    hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());
//    listKodSekatan = notaService.cariSekatanKepentingan(sekatan, kodSekatan, hakmilikPermohonan.getHakmilik().getCawangan().getKod());
//    LOG.info("Count Kod Sekatan ::" + listKodSekatan.size());
//
//        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        if (StringUtils.isNotBlank(idHakmilik)) {
//            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
//            if (hm != null) {
//                listKodSekatan = notaService.cariSekatanKepentingan(null, null, hm.getCawangan().getKod());
//            } else {
//                listKodSekatan = kodsekatanKepentinganDAO.findAll();
//            }
//        } else {
//            listKodSekatan = kodsekatanKepentinganDAO.findAll();
//        }
//        

        hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());

        listKodSekatan = notaService.cariSekatanKepentingan(sekatan, kodSekatan, hakmilikPermohonan.getHakmilik().getCawangan().getKod());
        LOG.info("Count Code Sekatan::" + listKodSekatan.size());

        return new JSP("daftar/nota/carianNsekatan.jsp").addParameter("tab", "true");
    }

    public Resolution cariKodSyaratNyata() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
//        if (kodSyaratNyata != null) {
//
//            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, "%", null);
//
//            LOG.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
//            if (listKodSyaratNyata.size() < 1) {
//                addSimpleError("Kod Syarat Nyata Tidak Sah");
//            }
//        } else {
//            listKodSyaratNyata = regService.searchKodSyaratNyata("%", "%", null);
//        }
//
//        if (listKodSyaratNyata.isEmpty()) {
//            addSimpleError("Kod Syarat Nyata Tidak Sah");
        //        }
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());

        listKodSyaratNyata = notaService.cariSyarat(urusan, kodSyaratNyata, hakmilikPermohonan.getHakmilik().getCawangan().getKod());
        LOG.info("Count Code SyaratNyata::" + listKodSyaratNyata.size());
        return new JSP("daftar/nota/carianNsyarat.jsp").addParameter("tab", "true");
    }

    //experiment....  test
    public Resolution kiraCukaiKelompok() {
        String result = "";
        idH = (String) getContext().getRequest().getParameter("idHakmilik");
        kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
        luas = (String) getContext().getRequest().getParameter("luas");
        String kodRizab;
        Hakmilik h = hakmilikDAO.findById(idH);
//        MathContext mc = new MathContext(0);
//        mc = mc.DECIMAL32;
        LOG.info(":: Start :: KiraCukaiKelompok");
        LOG.info(">idhakmilik: " + idH);
        LOG.info(">luas yang terlibat : " + luas);
        LOG.info(">kodUOM luas terlibat : " + kodUOM);
//    LOG.info("kod Guna Tanah hakmilik : " + h.getKegunaanTanah().getKod());
        LOG.info("kod BPM hakmilik : " + h.getBandarPekanMukim().getKod());
        LOG.info("Luas Asal hakmilik : " + h.getLuas());

        BigDecimal luasDiambil = new BigDecimal(luas); // convert input type 'luas' to bigDecimal        
        BigDecimal luasTinggal = new BigDecimal(0);
        BigDecimal luasConverted = calcTax.kiraUnitLuas(kodUOM, h.getKodUnitLuas().toString(), h.getLuas()); // LUAS CONVERTER FUNCTION
        LOG.info("Luas yang Ditukar : " + luasConverted);

        // check pertanian and kodUOM hakmilik
        LOG.info(" /* Kategori Tanah = " + h.getKategoriTanah().getKod() + " , Unit Luas = " + h.getKodUnitLuas().getNama() + " */ ");
        if ("1".equals(h.getKategoriTanah().getKod()) && "H".equals(h.getKodUnitLuas().getKod())
                || "2".equals(h.getKategoriTanah().getKod()) && "M".equals(h.getKodUnitLuas().getKod())
                || "3".equals(h.getKategoriTanah().getKod()) && "M".equals(h.getKodUnitLuas().getKod())) {
            luasTinggal = h.getLuas().subtract(luasDiambil);
        } else {
            luasTinggal = luasConverted.subtract(luasDiambil);
        }
        LOG.debug(":Luas Tinggal: " + luasTinggal);

        if (h.getRizab() != null) {
            LOG.debug("kodRizab : " + h.getRizab().getKod());
            kodRizab = String.valueOf(h.getRizab().getKod());
        } else {
            kodRizab = null;
        }

        result = String.valueOf(calcTax.calculate(h.getKegunaanTanah().getKod(), String.valueOf(h.getBandarPekanMukim().getKod()), kodUOM, luasTinggal, h, kodRizab));

        return new StreamingResolution("text/plain", result);
    }

    public Resolution validateLuas() {
        String result = "";
        String idH = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodUOM = (String) getContext().getRequest().getParameter("kodUOM");
        String luasDiambil = (String) getContext().getRequest().getParameter("luas");
        String namaOUM = "";

        Hakmilik hl = hakmilikDAO.findById(idH);
        if (kodUOM != null) {
            KodUOM kUOM = kodUOMDAO.findById(kodUOM);
            namaOUM = kUOM.getNama();
        }
        LOG.debug("Kod OUM dari Page::" + kodUOM);
        LOG.debug("Luas Diambil" + luasDiambil);
        LOG.debug("idHakmilik" + idH);
        BigDecimal luasConverted = calcTax.kiraUnitLuas(kodUOM, hl.getKodUnitLuas().getKod(), hl.getLuas());
        Integer compareFlag = 0;
        BigDecimal luasAmbil = new BigDecimal(luasDiambil);
        BigDecimal luasTinggal = new BigDecimal(0);
        boolean msgFlag = true;
        compareFlag = luasAmbil.compareTo(luasConverted);

        if (compareFlag > 0) {
            msgFlag = false;
            LOG.debug("Luas Ditukar" + luasConverted);
        } else {
            msgFlag = true;
            luasTinggal = luasConverted.subtract(luasAmbil);
            LOG.debug("Luas Ditukar" + luasConverted.setScale(4, RoundingMode.UP));
            LOG.debug("Luas Ditukar" + luasTinggal.setScale(4, RoundingMode.UP));
        }

        StringBuilder s = new StringBuilder();
        s.append(msgFlag).append(DELIM).append(luasConverted.setScale(4, RoundingMode.UP)).append(DELIM).append(luasTinggal.setScale(4, RoundingMode.UP)).append(DELIM).append(namaOUM).append(DELIM);

        LOG.debug(s);
        result = s.toString();
        return new StreamingResolution("text/plain", result);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public InfoAudit getInfo() {
        return info;
    }

    public void setInfo(InfoAudit info) {
        this.info = info;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getLuas() {
        return luas;
    }

    public String getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(String cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public void setLuas(String luas) {
        this.luas = luas;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
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

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(String kodSekatan) {
        this.kodSekatan = kodSekatan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKatTanah() {
        return katTanah;
    }

    public void setKatTanah(String katTanah) {
        this.katTanah = katTanah;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public KodSekatanKepentingan getKodsekatanKepentingan() {
        return kodsekatanKepentingan;
    }

    public void setKodsekatanKepentingan(KodSekatanKepentingan kodsekatanKepentingan) {
        this.kodsekatanKepentingan = kodsekatanKepentingan;
    }

    public KodSyaratNyata getKodsyaratNyata() {
        return kodsyaratNyata;
    }

    public void setKodsyaratNyata(KodSyaratNyata kodsyaratNyata) {
        this.kodsyaratNyata = kodsyaratNyata;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getJenisNotis() {
        return jenisNotis;
    }

    public void setJenisNotis(String jenisNotis) {
        this.jenisNotis = jenisNotis;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getSekatan() {
        return sekatan;
    }

    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public List<HakmilikPihakBerkepentingan> getPbHakmilikList() {
        return pbHakmilikList;
    }

    public void setPbHakmilikList(List<HakmilikPihakBerkepentingan> pbHakmilikList) {
        this.pbHakmilikList = pbHakmilikList;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getTarikhDisampai() {
        return tarikhDisampai;
    }

    public void setTarikhDisampai(String tarikhDisampai) {
        this.tarikhDisampai = tarikhDisampai;
    }

    public Pihak getP() {
        return p;
    }

    public void setP(Pihak p) {
        this.p = p;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTarikhKelulusan() {
        return tarikhKelulusan;
    }

    public void setTarikhKelulusan(String tarikhKelulusan) {
        this.tarikhKelulusan = tarikhKelulusan;
    }

    public String getTarikhKKuasa() {
        return tarikhKKuasa;
    }

    public void setTarikhKKuasa(String tarikhKKuasa) {
        this.tarikhKKuasa = tarikhKKuasa;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getCheckP() {
        return checkP;
    }

    public void setCheckP(String checkP) {
        this.checkP = checkP;
    }

    public PermohonanPihak getMpSalinan() {
        return mpSalinan;
    }

    public void setMpSalinan(PermohonanPihak mpSalinan) {
        this.mpSalinan = mpSalinan;
    }

    public String getSyerPembilang() {
        return syerPembilang;
    }

    public void setSyerPembilang(String syerPembilang) {
        this.syerPembilang = syerPembilang;
    }

    public String getSyerPenyebut() {
        return syerPenyebut;
    }

    public void setSyerPenyebut(String syerPenyebut) {
        this.syerPenyebut = syerPenyebut;
    }

    public List<PermohonanPihak> getMohonPihakList() {
        return mohonPihakList;
    }

    public void setMohonPihakList(List<PermohonanPihak> mohonPihakList) {
        this.mohonPihakList = mohonPihakList;
    }

    public String getIdMohonPihak() {
        return idMohonPihak;
    }

    public void setIdMohonPihak(String idMohonPihak) {
        this.idMohonPihak = idMohonPihak;
    }

    public List<PermohonanAtasPerserahan> getMohonAtasSerahList() {
        return mohonAtasSerahList;
    }

    public void setMohonAtasSerahList(List<PermohonanAtasPerserahan> mohonAtasSerahList) {
        this.mohonAtasSerahList = mohonAtasSerahList;
    }

    public KodUOM getKoduom() {
        return koduom;
    }

    public void setKoduom(KodUOM koduom) {
        this.koduom = koduom;
    }

    public KodUOMDAO getKoduomDAO() {
        return koduomDAO;
    }

    public void setKoduomDAO(KodUOMDAO koduomDAO) {
        this.koduomDAO = koduomDAO;
    }

    public String[] getStrKodSekatan() {
        return strKodSekatan;
    }

    public void setStrKodSekatan(String[] strKodSekatan) {
        this.strKodSekatan = strKodSekatan;
    }

    public String[] getStrKodSyaratNyata() {
        return strKodSyaratNyata;
    }

    public void setStrKodSyaratNyata(String[] strKodSyaratNyata) {
        this.strKodSyaratNyata = strKodSyaratNyata;
    }

    public String[] getStrKodUOM() {
        return strKodUOM;
    }

    public void setStrKodUOM(String[] strKodUOM) {
        this.strKodUOM = strKodUOM;
    }

    public String[] getStrKodKategori() {
        return strKodKategori;
    }

    public void setStrKodKategori(String[] strKodKategori) {
        this.strKodKategori = strKodKategori;
    }

    public String[] getStrKodGunaTanah() {
        return strKodGunaTanah;
    }

    public void setStrKodGunaTanah(String[] strKodGunaTanah) {
        this.strKodGunaTanah = strKodGunaTanah;
    }

    public PermohonanPihak getMp() {
        return mp;
    }

    public void setMp(PermohonanPihak mp) {
        this.mp = mp;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
    }

    public KodNegeri getkN() {
        return kN;
    }

    public void setkN(KodNegeri kN) {
        this.kN = kN;
    }

    public PermohonanPihak getMpTemp() {
        return mpTemp;
    }

    public void setMpTemp(PermohonanPihak mpTemp) {
        this.mpTemp = mpTemp;
    }

    public List<HakmilikPihakBerkepentingan> getPbHakmilikTempList() {
        return pbHakmilikTempList;
    }

    public void setPbHakmilikTempList(List<HakmilikPihakBerkepentingan> pbHakmilikTempList) {
        this.pbHakmilikTempList = pbHakmilikTempList;
    }

    public BigDecimal getBakiluas() {
        return bakiluas;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public void setBakiluas(BigDecimal bakiluas) {
        this.bakiluas = bakiluas;
    }

    public List<KodKegunaanTanah> getSenaraiKodKegunaanTanah() {
        return senaraiKodKegunaanTanah;
    }

    public void setSenaraiKodKegunaanTanah(List<KodKegunaanTanah> senaraiKodKegunaanTanah) {
        this.senaraiKodKegunaanTanah = senaraiKodKegunaanTanah;
    }
}
