/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

//import able.stripes.JSP;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.strata.BadanPengurusan;
import etanah.service.common.*;
import etanah.service.*;
import java.util.ArrayList;
import org.hibernate.Query;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.*;
import etanah.report.ReportUtil;
import etanah.view.*;
import java.math.BigDecimal;
import org.hibernate.Session;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.StreamingResolution;

@UrlBinding("/JadualPetakManual")
public class JadualPetakManual extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodUOMDAO kodUOMDAO;
    @Inject
    private KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    private BadanPengurusanDAO badanPengurusanDAO;
    @Inject
    private KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    private KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    private KodCawanganDAO kodCawanganDAO;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    private PermohonanBangunanDAO permohonanBangunanDAO;
    @Inject
    private KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    LelongService lelongService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    private KodCawangan kodcaw;
    private PermohonanBangunan mhnbgn;
    private PermohonanBangunan mhnbgnTanah;
    private PermohonanBangunan cekLanded;
    private BangunanPetak bgnpetak;
    private BangunanTingkat bgnTingkat;
    private BangunanPetak bangunanPetak;
    private BangunanTingkat bngntgkt;
    private BangunanPetak bngnPetak;
    private Permohonan mohon;
    private List<BangunanPetak> bngnnPetak = new ArrayList<BangunanPetak>();
    private List<PermohonanBangunan> senaraiPermohonanBangunan = new ArrayList<PermohonanBangunan>();
    private List<BangunanPetakAksesori> bngnPetakaks = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetak> senaraibngnPetak = new ArrayList<BangunanPetak>();
    private List<BangunanPetakAksesori> senaraiPetakAksesori = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetakAksesori> senaraiPetakAksesoriTanah = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetakAksesori> senaraiPetakAksesoriTanahbyIdTgkt = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanPetakAksesori> senaraiPetakAksesoribyIdTgkt = new ArrayList<BangunanPetakAksesori>();
    private List<BangunanTingkat> senaraiTgktbyBgnn = new ArrayList<BangunanTingkat>();
    private List<BangunanPetak> senaraiPetakByAllTgkt = new ArrayList<BangunanPetak>();
    private List<BangunanPetakAksesori> senaraiPetakAksesoribyAllbgnn = new ArrayList<BangunanPetakAksesori>();
    private String bilBangunan;
    private String bilTanah;
    private String bilTingkat;
    private String bilPetak;
    private String tingkat;
    private String petak;
    private String petakTanah;
    private String petakMula;
    private String bangunan;
    private String petakAks;
    private String petakAksTanah;
    private String pickjenis;
    private String bilTanah2;
    private String stageId;
    private String taskId;
    private String kodnegeri;
    @Inject
    BPelService service;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    StrataPtService strataPtService;
    private List<PermohonanBangunan> cekMhnBngn = new ArrayList<PermohonanBangunan>();
    private static final Logger LOG = Logger.getLogger(JadualPetakManual.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution genReportJP() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
        String code = "";

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            gen = "STRJadualPetak_MLK.rdf";

        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            gen = "STRJadualPetak_NS.rdf";
        }
        code = "JPP";
        try {
            LOG.info("genReportFromXML");
            strataPtService.reportGen(p, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReportK :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution kemaskini() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        bilBangunan = (String) getContext().getRequest().getParameter("bilBangunan");
        bilTanah = (String) getContext().getRequest().getParameter("bilTanah");
        bilTanah2 = (String) getContext().getRequest().getParameter("bilTanah2");
        pickjenis = (String) getContext().getRequest().getParameter("pickjenis");
        String syer = (String) getContext().getRequest().getParameter("syer");
        String syer2 = (String) getContext().getRequest().getParameter("syer2");
        LOG.info("pickjenis:--------" + pickjenis);
        LOG.info("bilTanah:--------" + bilTanah);
        LOG.info("bilTanah2:--------" + bilTanah2);
        LOG.info("bilBangunan -- " + bilBangunan);

        LOG.info("idPermohonan -- " + idPermohonan);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        cekMhnBngn = strataPtService.checkMhnBangunanExist(idPermohonan);
        if (mohon.getKodUrusan().getKod().equals("PBBD")) {
            if (cekMhnBngn.size() == 0) {
                if (pickjenis.equals("bngnTanah")) {
                    Integer bilBangunani = Integer.parseInt(bilBangunan);
                    for (int i = 1; i <= bilBangunani; i++) {
                        PermohonanBangunan mhnbngn = new PermohonanBangunan();
                        KodKategoriBangunan kodKategoriBangunan = new KodKategoriBangunan();
                        if (mohon.getKodUrusan().getKod().equals("PBBD")) {
                            if (i == 1) {
                                PermohonanBangunan mhnbngn1 = new PermohonanBangunan();
                                mhnbngn1.setNama("L1");
                                kodKategoriBangunan = kodKategoriBangunanDAO.findById("L");
                                mhnbngn1.setKodKategoriBangunan(kodKategoriBangunan);
                                mhnbngn1.setNamaLain("manual");
                                mhnbngn1.setInfoAudit(ia);
                                mhnbngn1.setCawangan(pengguna.getKodCawangan());
                                mhnbngn1.setPermohonan(mohon);
                                mhnbngn1.setBilanganTingkat(1);
                                mhnbngn1.setBilanganPetak(Integer.parseInt(bilTanah2));
                                mhnbngn1.setSyerBlok(Integer.parseInt(syer2));
                                strataPtService.save(mhnbngn1);

                                BangunanTingkat bangunanTingkat = new BangunanTingkat();
                                bangunanTingkat.setBangunan(mhnbngn1);
                                bangunanTingkat.setBilanganPetak(Integer.parseInt(bilTanah2));
                                bangunanTingkat.setBilanganPetakAksesori(0);
                                bangunanTingkat.setNama("1");
                                bangunanTingkat.setTingkat(1);
                                bangunanTingkat.setBilanganPetakAksesori(0);
                                bangunanTingkat.setInfoAudit(ia);
                                strataPtService.save(bangunanTingkat);

                                Integer bilTnh = Integer.parseInt(bilTanah2);
                                for (int b = 1; b <= bilTnh; b++) {
                                    String nama = String.valueOf(b);
                                    BangunanPetak bangunanPetak = new BangunanPetak();
                                    bangunanPetak.setNama("L" + nama);
                                    bangunanPetak.setInfoAudit(ia);
                                    bangunanPetak.setTingkat(bangunanTingkat);
                                    strataPtService.save(bangunanPetak);
                                }
                            }

                            mhnbngn.setNama("M" + i);
                            kodKategoriBangunan = kodKategoriBangunanDAO.findById("B");
                            mhnbngn.setKodKategoriBangunan(kodKategoriBangunan);
                        }
                        mhnbngn.setInfoAudit(ia);
                        mhnbngn.setCawangan(pengguna.getKodCawangan());
                        mhnbngn.setPermohonan(mohon);
                        mhnbngn.setNamaLain("manual");
                        mhnbngn.setBilanganTingkat(0);
                        mhnbngn.setBilanganPetak(0);
                        mhnbngn.setSyerBlok(0);
                        strataPtService.save(mhnbngn);
                    }
                }
                if (pickjenis.equals("tanah")) {
                    PermohonanBangunan mhnbngn1 = new PermohonanBangunan();
                    KodKategoriBangunan kodKategoriBangunan = new KodKategoriBangunan();
                    mhnbngn1.setNama("L1");
                    kodKategoriBangunan = kodKategoriBangunanDAO.findById("L");
                    mhnbngn1.setKodKategoriBangunan(kodKategoriBangunan);
                    mhnbngn1.setInfoAudit(ia);
                    mhnbngn1.setCawangan(pengguna.getKodCawangan());
                    mhnbngn1.setPermohonan(mohon);
                    mhnbngn1.setBilanganTingkat(1);
                    mhnbngn1.setNamaLain("manual");
                    mhnbngn1.setBilanganPetak(Integer.parseInt(bilTanah));
                    mhnbngn1.setSyerBlok(Integer.parseInt(syer));
                    strataPtService.save(mhnbngn1);


                    BangunanTingkat bangunanTingkat = new BangunanTingkat();
                    bangunanTingkat.setBangunan(mhnbngn1);
                    bangunanTingkat.setBilanganPetak(Integer.parseInt(bilTanah));
                    bangunanTingkat.setNama("1");
                    bangunanTingkat.setTingkat(1);
                    bangunanTingkat.setBilanganPetakAksesori(0);
                    bangunanTingkat.setInfoAudit(ia);
                    strataPtService.save(bangunanTingkat);

                    Integer bilTnh = Integer.parseInt(bilTanah);
                    for (int b = 1; b <= bilTnh; b++) {
                        String nama = String.valueOf(b);
                        BangunanPetak bangunanPetak = new BangunanPetak();
                        bangunanPetak.setNama("L" + nama);
                        bangunanPetak.setInfoAudit(ia);
                        bangunanPetak.setTingkat(bangunanTingkat);
                        strataPtService.save(bangunanPetak);
                    }
                }
            }
            getContext().getRequest().setAttribute("landed1", Boolean.TRUE);
            cekLanded = strataPtService.findLanded(idPermohonan);
        }

        if (mohon.getKodUrusan().getKod().equals("PBBS") && bilBangunan != null) {
            Integer bilBangunani = Integer.parseInt(bilBangunan);
            if (cekMhnBngn.size() == 0) {
                for (int i = 1; i <= bilBangunani; i++) {
                    PermohonanBangunan mhnbngn = new PermohonanBangunan();
                    KodKategoriBangunan kodKategoriBangunan = new KodKategoriBangunan();
                    mhnbngn.setNama(String.valueOf(i));
                    kodKategoriBangunan = kodKategoriBangunanDAO.findById("B");
                    mhnbngn.setKodKategoriBangunan(kodKategoriBangunan);
                    mhnbngn.setInfoAudit(ia);
                    mhnbngn.setCawangan(pengguna.getKodCawangan());
                    mhnbngn.setPermohonan(mohon);
                    mhnbngn.setNamaLain("manual");
                    mhnbngn.setBilanganTingkat(0);
                    mhnbngn.setBilanganPetak(0);
                    mhnbngn.setSyerBlok(0);
                    strataPtService.save(mhnbngn);
                }
                bilBangunan = bilBangunani.toString();
            }
        } else {
            Integer IntbilBangunan = cekMhnBngn.size();
            bilBangunan = IntbilBangunan.toString();
        }

        rehydrate();
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini1() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String jenis = (String) getContext().getRequest().getParameter("kekal");
        String kodKegunaanBangunan = (String) getContext().getRequest().getParameter("kodKegunaanBangunan");
        String menara = (String) getContext().getRequest().getParameter("menara");
        String unitSyer = (String) getContext().getRequest().getParameter("unitSyer");
        String biltingkat = (String) getContext().getRequest().getParameter("biltingkat");
        String bangunan = (String) getContext().getRequest().getParameter("idbngn");
        String namamenara = (String) getContext().getRequest().getParameter("namamenara");
        String bilmenara = (String) getContext().getRequest().getParameter("bilmenara");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("Jenis:--------" + jenis);
        LOG.info("kodKegunaanBangunan -- " + kodKegunaanBangunan);
        LOG.info("menara -- " + menara);
        LOG.info("unitSyer -- " + unitSyer);
        LOG.info("biltingkat -- " + biltingkat);
        LOG.info("idPermohonan -- " + idPermohonan);
        LOG.info("namamenara -- " + namamenara);
        LOG.info("bilmenara -- " + bilmenara);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        PermohonanBangunan mb = strataPtService.findBangunanNama(Long.parseLong(bangunan));
        mhnbgn = strataPtService.findByNama(mb.getNama(), idPermohonan);
        if (mhnbgn != null) {
            mhnbgn.setBilanganTingkat(0);
            mhnbgn.setSyerBlok(Integer.parseInt(unitSyer));
            if (jenis.equals("kekal")) {
                mhnbgn.setKekal('Y');
                if (!mhnbgn.getNama().contains("M")) {
                    mhnbgn.setNama("M" + mhnbgn.getNama());
                }
            } else {
                mhnbgn.setKekal('T');
                if (!mhnbgn.getNama().contains("P")) {
                    mhnbgn.setNama("P" + mhnbgn.getNama());
                }
            }
            if (menara.equals("y")) {
                mhnbgn.setBilanganMenara(Integer.parseInt(bilmenara));
                mhnbgn.setLain(namamenara);
            } else {
                mhnbgn.setBilanganMenara(0);
            }
            KodKegunaanBangunan KodKegunaanBangunan = new KodKegunaanBangunan();
            if (kodKegunaanBangunan != null) {
                KodKegunaanBangunan = kodKegunaanBangunanDAO.findById(kodKegunaanBangunan);
                mhnbgn.setKodKegunaanBangunan(KodKegunaanBangunan);
            } else {
                mhnbgn.setKodKegunaanBangunan(KodKegunaanBangunan);
            }
            strataPtService.simpanBangunan(mhnbgn);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(mhnbgn.getIdBangunan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution tambahTingkat() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("idPermohonan -- " + idPermohonan);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

//        if (mohon.getKodUrusan().getKod().equals("PBBS")) {
            String bgn = "M" + bangunan;
            mhnbgn = strataPtService.findByNama(bgn, idPermohonan);
            if (mhnbgn == null) {
                String bgn2 = "P" + bangunan;
                mhnbgn = strataPtService.findByNama(bgn2, idPermohonan);
            }
//        }
        senaraiTgktbyBgnn = strataPtService.findByIDBangunanTingkat(mhnbgn.getIdBangunan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPetak() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        String petakTgkt = (String) getContext().getRequest().getParameter("petakTgktTambah");
        String petakTgktMula = (String) getContext().getRequest().getParameter("petakTgktMulaTambah");
        String kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        String petakUnitSyer = (String) getContext().getRequest().getParameter("petakUnitSyerTambah");
        String petakLuas = (String) getContext().getRequest().getParameter("petakLuasTambah");
        String idtgkt = (String) getContext().getRequest().getParameter("idtgkt");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("petakTgkt -- " + petakTgkt);
        LOG.info("petakTgktMula -- " + petakTgktMula);
        LOG.info("kodKegunaanPetak -- " + kodKegunaanPetak);
        LOG.info("petakUnitSyer -- " + petakUnitSyer);
        LOG.info("petakLuas -- " + petakLuas);
        LOG.info("idPermohonan -- " + idPermohonan);
        LOG.info("idtgkt -- " + idtgkt);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (mohon.getKodUrusan().getKod().equals("PBBS")) {
            String bgn = "M" + bangunan;
            mhnbgn = strataPtService.findByNama(bgn, idPermohonan);
            if (mhnbgn == null) {
                String bgn2 = "P" + bangunan;
                mhnbgn = strataPtService.findByNama(bgn2, idPermohonan);
            }
        }

        BangunanTingkat bt = strataPtService.findByPetak(idtgkt);
        for (int w = 0; w < Integer.parseInt(petakTgkt); w++) {
            Integer petakmula = Integer.parseInt(petakTgktMula) + w;
            BangunanPetak bp = new BangunanPetak();
            bp.setInfoAudit(ia);
            bp.setNama(petakmula.toString());
            bp.setTingkat(bt);
            bp.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
            bp.setLuas(BigDecimal.valueOf(Integer.parseInt(petakLuas)));
            bp.setLuasUom(kodUOMDAO.findById("M"));
            bp.setSyer(Integer.parseInt(petakUnitSyer));
            strataPtService.save(bp);

//            petakmula = petakmula + 1;
            LOG.info("petakmula -- " + petakmula);
        }
        Integer petakbr = bt.getBilanganPetak() + Integer.parseInt(petakTgkt);
        bt.setBilanganPetak(petakbr);
        strataPtService.save(bt);

        Integer petakmhnbgnn = mhnbgn.getBilanganPetak() + Integer.parseInt(petakTgkt);
        mhnbgn.setBilanganPetak(petakmhnbgnn);
        strataPtService.save(mhnbgn);

        bngnnPetak = strataPtService.findByIDPetak2(Long.parseLong(idtgkt));
        senaraiTgktbyBgnn = strataPtService.findByIDBangunanTingkat(mhnbgn.getIdBangunan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.FALSE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambahPetak", Boolean.FALSE);
        getContext().getRequest().setAttribute("petak", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution tambahPetak() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        String idtgkt = (String) getContext().getRequest().getParameter("idtgkt");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("idPermohonan -- " + idPermohonan);
        LOG.info("idtgkt -- " + idtgkt);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (mohon.getKodUrusan().getKod().equals("PBBS")) {
            String bgn = "M" + bangunan;
            mhnbgn = strataPtService.findByNama(bgn, idPermohonan);
            if (mhnbgn == null) {
                String bgn2 = "P" + bangunan;
                mhnbgn = strataPtService.findByNama(bgn2, idPermohonan);
            }
        }
        senaraiTgktbyBgnn = strataPtService.findByIDBangunanTingkat(mhnbgn.getIdBangunan());
        bngnnPetak = strataPtService.findByIDPetak2(Long.parseLong(idtgkt));
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.FALSE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambahPetak", Boolean.TRUE);
        getContext().getRequest().setAttribute("petak", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution simpanTingkat() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String idbngn = (String) getContext().getRequest().getParameter("idbngn");
        String namaTgkt = (String) getContext().getRequest().getParameter("namaTgkt");
        String petakTgkt = (String) getContext().getRequest().getParameter("petakTgkt");
        String tgkt = (String) getContext().getRequest().getParameter("tgkt");
        String kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        String petakUnitSyer = (String) getContext().getRequest().getParameter("petakUnitSyer");
        String petakLuas = (String) getContext().getRequest().getParameter("petakLuas");
        String petakTgktMula = (String) getContext().getRequest().getParameter("petakTgktMula");
        String kongsiTgkt = (String) getContext().getRequest().getParameter("kongsiTgkt");
        String bawahTanah = (String) getContext().getRequest().getParameter("bawahTanah");
        String jenisMezanin = (String) getContext().getRequest().getParameter("jenisMezanin");
        String bilPelanByk = (String) getContext().getRequest().getParameter("bilPelanByk");
        String pelanByk = (String) getContext().getRequest().getParameter("pelanByk");
        String bilPelan = (String) getContext().getRequest().getParameter("bilPelan");
        String petakTerlibat = (String) getContext().getRequest().getParameter("petakTerlibat");
        String pelanPetak = (String) getContext().getRequest().getParameter("pelanPetak");
        String mezanin = (String) getContext().getRequest().getParameter("mezanin");
        String menaraTgkt = (String) getContext().getRequest().getParameter("menaraTgkt");
        LOG.info("idbngn -- " + idbngn);
        LOG.info("idPermohonan -- " + idPermohonan);
        LOG.info("tgkt -- " + tgkt);
        LOG.info("namaTgkt -- " + namaTgkt);
        LOG.info("petakTgkt -- " + petakTgkt);
        LOG.info("petakTgktMula -- " + petakTgktMula);
        LOG.info("kodKegunaanPetak -- " + kodKegunaanPetak);
        LOG.info("petakUnitSyer -- " + petakUnitSyer);
        LOG.info("petakLuas -- " + petakLuas);
        LOG.info("kongsiTgkt -- " + kongsiTgkt);
        LOG.info("bawahTanah -- " + bawahTanah);
        LOG.info("jenisMezanin -- " + jenisMezanin);
        LOG.info("mezanin -- " + mezanin);
        LOG.info("bilPelanByk -- " + bilPelanByk);
        LOG.info("bilPelan -- " + bilPelan);
        LOG.info("petakTerlibat -- " + petakTerlibat);
        LOG.info("pelanPetak -- " + pelanPetak);
        LOG.info("menaraTgkt -- " + menaraTgkt);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        PermohonanBangunan mhbgn = strataPtService.findBangunanNama(Long.valueOf(idbngn));
        mhnbgn = strataPtService.findByNama(mhbgn.getNama(), idPermohonan);

        if (mhnbgn != null) {
            Integer biltgkt = mhnbgn.getBilanganTingkat() + 1;
            mhnbgn.setBilanganTingkat(biltgkt);
            mhnbgn.setBilanganPetak(Integer.parseInt(petakTgkt));
            strataPtService.save(mhnbgn);


            BangunanTingkat bt = new BangunanTingkat();
            bt.setBangunan(mhnbgn);
            bt.setBilanganPetak(Integer.parseInt(petakTgkt));
            bt.setInfoAudit(ia);
            bt.setBilanganPetakAksesori(0);
            if (bawahTanah.equals("xbawahTanah")) {
                bt.setNama(namaTgkt);
                if (kongsiTgkt.equals("y")) {
                    bt.setTingkat(Integer.parseInt(tgkt));
                } else {
                    bt.setTingkat(Integer.parseInt(namaTgkt));
                }
            } else {
                bt.setNama("B" + namaTgkt);
                bt.setTingkat(Integer.parseInt(namaTgkt));
            }
            if (jenisMezanin.equals("y")) {
                bt.setMezanin(mezanin);
            }
            if(menaraTgkt != null){
                bt.setLain(menaraTgkt);
            }

            strataPtService.save(bt);

            if (petakTgktMula != null) {
//                petakTgktMula = Integer.parseInt(petakTgktMula);
                Integer petakTgktAkhir = Integer.parseInt(petakTgktMula) + Integer.parseInt(petakTgkt);
                petakTgktAkhir = petakTgktAkhir - 1;
                for (int i = Integer.parseInt(petakTgktMula); i <= petakTgktAkhir; i++) {
                    BangunanPetak bp = new BangunanPetak();
                    bp.setInfoAudit(ia);
                    bp.setNama(String.valueOf(i));
                    bp.setTingkat(bt);
                    bp.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
                    bp.setLuas(BigDecimal.valueOf(Integer.parseInt(petakLuas)));
                    bp.setLuasUom(kodUOMDAO.findById("M"));
                    bp.setSyer(Integer.parseInt(petakUnitSyer));
//                    bp.setPabPetak(pelanPetak);
//                    if (pelanByk.equals("y")) {
//                        if (!petakTerlibat.isEmpty() && Integer.parseInt(petakTerlibat) == i) {
//                            for (int z = 1; z < Integer.parseInt(bilPelan); z++) {
//                                BangunanPetak bp2 = new BangunanPetak();
//                                bp2.setInfoAudit(ia);
//                                bp2.setNama(String.valueOf(i));
//                                bp2.setTingkat(bt);
//                                bp2.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
//                                bp2.setLuas(BigDecimal.valueOf(Integer.parseInt(petakLuas)));
//                                bp2.setLuasUom(kodUOMDAO.findById("M"));
//                                bp2.setSyer(Integer.parseInt(petakUnitSyer));
//                                bp2.setPabPetak(pelanPetak);
//                                strataPtService.save(bp2);
//                            }
//                        }
//                    }
                    strataPtService.save(bp);
                }
                addSimpleMessage("Maklumat Berjaya Disimpan.");
            } else {
                addSimpleError("Sila Masukan Maklumat Pada Ruangan yang Bertanda (*)");
            }
        }
        rehydrate();
        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(mhnbgn.getIdBangunan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.FALSE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPetak() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("idPermohonan -- " + idPermohonan);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        mhnbgn = strataPtService.findByNama(bangunan, idPermohonan);

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini5() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String idbngn = (String) getContext().getRequest().getParameter("idbngn");
        String idtgkt = (String) getContext().getRequest().getParameter("idtgkt");
        String syerEdit = (String) getContext().getRequest().getParameter("syerEdit");
        String luasEdit = (String) getContext().getRequest().getParameter("luasEdit");
        String kodKegunaanPetak = (String) getContext().getRequest().getParameter("kodKegunaanPetak");
        String petakEdit = (String) getContext().getRequest().getParameter("petak");
        String idpetak = (String) getContext().getRequest().getParameter("idpetak");
        String pabPetakEdit = (String) getContext().getRequest().getParameter("pabPetakEdit");
        LOG.info("idbngn:--------" + idbngn);
        LOG.info("idtgkt:--------" + idtgkt);
        LOG.info("idpetak:--------" + idpetak);
        LOG.info("petakEdit:--------" + petakEdit);
        LOG.info("syerEdit:--------" + syerEdit);
        LOG.info("luasEdit:--------" + luasEdit);
        LOG.info("kodKegunaanPetak:--------" + kodKegunaanPetak);
        LOG.info("pabPetakEdit:--------" + pabPetakEdit);
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        mhnbgn = strataPtService.findBangunanNama(Long.parseLong(idbngn));
        if (!idpetak.isEmpty()) {
            BangunanPetak bangunanPetak = strataPtService.getSenaraiPetak2(Long.parseLong(idpetak), petakEdit);
            if (bangunanPetak != null) {
                bangunanPetak.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetak));
                bangunanPetak.setLuas(BigDecimal.valueOf(Integer.parseInt(luasEdit)));
                bangunanPetak.setSyer(Integer.parseInt(syerEdit));
                bangunanPetak.setPabPetak(pabPetakEdit);
                strataPtService.save(bangunanPetak);
                addSimpleMessage("Maklumat Petak Berjaya Disimpan");
            }
        }

//        kemaskini3();

        bngnnPetak = strataPtService.findByIDPetak2(Long.parseLong(idtgkt));

        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(Long.parseLong(idbngn));

        getContext().getRequest().setAttribute("petak", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);

        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
//        return new ForwardResolution("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniPetakTanah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String petakTanah = (String) getContext().getRequest().getParameter("bil");
        String unit_SyerTanah = (String) getContext().getRequest().getParameter("unit_SyerTanah");
        String luasTanah = (String) getContext().getRequest().getParameter("luasTanah");
        String kodKegunaanPetakTanah = (String) getContext().getRequest().getParameter("kodKegunaanPetakTanah");
//        String petakAksesoriTanah = (String) getContext().getRequest().getParameter("petakAksesoriTanah");
        LOG.info("petakTanah:--------" + petakTanah);
        LOG.info("unit_SyerTanah:--------" + unit_SyerTanah);
        LOG.info("luasTanah:--------" + luasTanah);
        LOG.info("kodKegunaanPetakTanah:--------" + kodKegunaanPetakTanah);
//        LOG.info("petakAksesoriTanah:--------" + petakAksesoriTanah);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia1 = new InfoAudit();
        ia1.setDiKemaskiniOleh(pengguna);
        ia1.setTarikhKemaskini(new java.util.Date());

        cekLanded = strataPtService.findLanded(idPermohonan);

        bngntgkt = strataPtService.findTinketByIdBngnNama2(cekLanded.getIdBangunan());
        bngnPetak = strataPtService.findPetakByIdBngnIdTinketNama(bngntgkt.getIdTingkat(), petakTanah);

        if (bngntgkt != null) {
            if (bngnPetak == null) {
                BangunanPetak bngunanPetak = new BangunanPetak();
                bngunanPetak.setInfoAudit(ia);
                bngunanPetak.setTingkat(bngntgkt);
                bngunanPetak.setNama(petakTanah);
                bngunanPetak.setLuas(BigDecimal.valueOf(Integer.parseInt(luasTanah)));
                bngunanPetak.setSyer(Integer.parseInt(unit_SyerTanah));
                bngunanPetak.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetakTanah));
                strataPtService.save(bngunanPetak);
            } else {
                bngnPetak.setInfoAudit(ia1);
                bngnPetak.setTingkat(bngntgkt);
                bngnPetak.setNama(petakTanah);
                bngnPetak.setLuas(BigDecimal.valueOf(Integer.parseInt(luasTanah)));
                bngnPetak.setSyer(Integer.parseInt(unit_SyerTanah));
                bngnPetak.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetakTanah));
                strataPtService.save(bngnPetak);
            }
        }
        addSimpleMessage("Maklumat Petak Berjaya Disimpan");
        return new ForwardResolution("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        LOG.info("bangunan -- " + bangunan);

//        if (mohon.getKodUrusan().getKod().equals("PBBS")) {
            String bgn = "M" + bangunan;
            mhnbgn = strataPtService.findByNama(bgn, idPermohonan);
            if (mhnbgn == null) {
                mhnbgn = strataPtService.findByNama(bangunan, idPermohonan);
            } else if (mhnbgn == null) {
                String bgn2 = "P" + bangunan;
                mhnbgn = strataPtService.findByNama(bgn2, idPermohonan);
            }

            senaraiTgktbyBgnn = strataPtService.findByIDBangunan(mhnbgn.getIdBangunan());
            LOG.info("senaraiTgktbyBgnn==" + senaraiTgktbyBgnn);
//        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniL() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        String unit_SyerTanah = (String) getContext().getRequest().getParameter("unit_SyerTanah");
        String luasTanah = (String) getContext().getRequest().getParameter("luasTanah");
        String kodKegunaanPetakTanah = (String) getContext().getRequest().getParameter("kodKegunaanPetakTanah");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("unit_SyerTanah -- " + unit_SyerTanah);
        LOG.info("luasTanah -- " + luasTanah);
        LOG.info("kodKegunaanPetakTanah -- " + kodKegunaanPetakTanah);
        mhnbgn = strataPtService.findByNama(bangunan, idPermohonan);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());


        List<PermohonanBangunan> mohonBangunan = strataPtService.checkMhnBangunanExist(idPermohonan);


        for (PermohonanBangunan check : mohonBangunan) {
            if (check.getNama().contains("L1")) {
                BangunanTingkat bangunanTingkat = strataPtService.findByIDBangunan2(check.getIdBangunan());
                List<BangunanPetak> bangunanPetak = strataPtService.findPetakByIdTingKat(bangunanTingkat.getIdTingkat());



                for (BangunanPetak bp : bangunanPetak) {
                    bp.setLuas(BigDecimal.valueOf(Integer.valueOf(luasTanah)));
                    bp.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetakTanah));
                    bp.setLuasUom(kodUOMDAO.findById("M"));
                    bp.setSyer(Integer.parseInt(unit_SyerTanah));
                    bp.setInfoAudit(ia);
                    strataPtService.save(bp);


                }
            }
        }
        getContext().getRequest().setAttribute("landed", Boolean.TRUE);


        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");


    }

    public Resolution kemaskini3() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        tingkat = (String) getContext().getRequest().getParameter("tingkat");
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        String bawahTanah = (String) getContext().getRequest().getParameter("bawahTanah");
        petakMula = (String) getContext().getRequest().getParameter("petakMula");
        petak = (String) getContext().getRequest().getParameter("petak");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("tingkat -- " + tingkat);
        LOG.info("bawahTanah -- " + bawahTanah);
        LOG.info("bilPetak -- " + petak);
        LOG.info("petakMula -- " + petakMula);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia1 = new InfoAudit();
        ia1.setDiKemaskiniOleh(pengguna);
        ia1.setTarikhKemaskini(new java.util.Date());

        mhnbgn = strataPtService.findByNama(bangunan, idPermohonan);
        if (petakMula.isEmpty()) {
            addSimpleError("Sila Masukkan Petak Mula");
        } else {
            if (mhnbgn != null) {
                if (tingkat != null) {
                    bgnTingkat = strataPtService.findTinketByIdBngnNama(mhnbgn.getIdBangunan(), tingkat);
                    LOG.info("bgnTingkat---" + bgnTingkat);
                    getContext().getRequest().setAttribute("petak", Boolean.TRUE);

                    if (bawahTanah != null) {
                        if (bgnTingkat == null) {
                            BangunanTingkat bangunanTingkat = new BangunanTingkat();
                            bangunanTingkat.setBangunan(mhnbgn);
                            bangunanTingkat.setBilanganPetak(Integer.parseInt(petak));
                            if (bawahTanah.equals("bawahTanah")) {
                                bangunanTingkat.setNama("B" + tingkat);
                            } else {
                                bangunanTingkat.setNama(tingkat);
                            }
                            bangunanTingkat.setTingkat(Integer.parseInt(tingkat));
                            bangunanTingkat.setInfoAudit(ia);
                            bangunanTingkat.setBilanganPetakAksesori(0);
                            strataPtService.save(bangunanTingkat);
                        } else {
                            bgnTingkat.setBilanganPetak(Integer.parseInt(petak));
                            if (bawahTanah.equals("bawahTanah")) {
                                bgnTingkat.setNama("B" + tingkat);
                            } else {
                                bgnTingkat.setNama(tingkat);
                            }
                            bgnTingkat.setTingkat(Integer.parseInt(tingkat));
                            bgnTingkat.setInfoAudit(ia1);
                            strataPtService.save(bgnTingkat);
                        }
                    }
                }
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution petakAksesoriPopup() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        tingkat = (String) getContext().getRequest().getParameter("tingkat");
        bangunan = (String) getContext().getRequest().getParameter("bangunan");
        petakAks = (String) getContext().getRequest().getParameter("petakAks");
        String idpetak = (String) getContext().getRequest().getParameter("idpetak");
        LOG.info("bangunan -- " + bangunan);
        LOG.info("tingkat -- " + tingkat);
        LOG.info("petakAks -- " + petakAks);
        LOG.info("idpetak -- " + idpetak);
        PermohonanBangunan mb = strataPtService.findBangunanNama(Long.parseLong(bangunan));
        BangunanTingkat bt = strataPtService.findByPetak(tingkat);
        mhnbgn = strataPtService.findByNama(mb.getNama(), idPermohonan);
        if (mhnbgn != null) {
            bgnTingkat = strataPtService.findTinketByIdBngnNama(mhnbgn.getIdBangunan(), bt.getNama());
            if (bgnTingkat != null) {
                bgnpetak = strataPtService.getSenaraiPetak2(Long.parseLong(idpetak), petakAks);
                if (bgnpetak != null) {
                    senaraiPetakAksesori = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
                }
                bangunan = mb.getNama();
                tingkat = bt.getNama();
            }
        }

        return new JSP("strata/jadualmanual.jsp").addParameter("popup", "true");
    }

    public Resolution petakAksesoriTanahPopup() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        petakAksTanah = (String) getContext().getRequest().getParameter("petakAksTanah");
        LOG.info("petakAksTanah -- " + petakAksTanah);
        senaraiPermohonanBangunan = strataPtService.checkMhnBangunanExist(idPermohonan);
        mhnbgnTanah = strataPtService.findLanded(idPermohonan);

        for (PermohonanBangunan mhnBngn : senaraiPermohonanBangunan) {
            bgnTingkat = strataPtService.findTinketByIdBngnNama2(mhnBngn.getIdBangunan());

            if (bgnTingkat != null) {
                bgnpetak = strataPtService.findPetakByIdBngnIdTinketNama(bgnTingkat.getIdTingkat(), petakAksTanah);
                if (bgnpetak != null) {
                    senaraiPetakAksesoriTanah = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
                    LOG.info("xxx" + senaraiPetakAksesoriTanah);
                }
            }
        }
        return new JSP("strata/jadualmanual2.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskini4() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String tingkat = (String) getContext().getRequest().getParameter("tingkat");
        String idbngn = (String) getContext().getRequest().getParameter("idbngn");
        LOG.info("tingkat" + tingkat);
        LOG.info("idbngn" + idbngn);

        BangunanTingkat bngnTgkt = strataPtService.findTinketByIdBngnNama(Long.parseLong(idbngn), tingkat);
        bngnnPetak = strataPtService.findByIDPetak2(bngnTgkt.getIdTingkat());
        mhnbgn = strataPtService.findBangunanNama(Long.parseLong(idbngn));

        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(Long.parseLong(idbngn));

        getContext().getRequest().setAttribute("petak", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPetakAk() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String namaAks = (String) getContext().getRequest().getParameter("namaAks");
        String LokasiAks = (String) getContext().getRequest().getParameter("LokasiAks");
        String kodKegunaanPetakAks = (String) getContext().getRequest().getParameter("kodKegunaanPetakAks");
        String bangunan = (String) getContext().getRequest().getParameter("bangunan");
        String tingkat = (String) getContext().getRequest().getParameter("tingkat");
        String petakAks = (String) getContext().getRequest().getParameter("petakAks");
        String idPetak = (String) getContext().getRequest().getParameter("idPetak");
        String petakBersangkutan = (String) getContext().getRequest().getParameter("petakBersangkutan");
        String pabAksesori = (String) getContext().getRequest().getParameter("pabAksesori");


        LOG.info("bangunan -- " + bangunan);
        LOG.info("tingkat -- " + tingkat);
        LOG.info("petakAks -- " + petakAks);
        LOG.info("idPetak -- " + idPetak);
        LOG.info("namaAks:--------" + namaAks);
        LOG.info("LokasiAks:--------" + LokasiAks);
        LOG.info("kodKegunaanPetakAks:--------" + kodKegunaanPetakAks);
        LOG.info("petakBersangkutan:--------" + petakBersangkutan);
        LOG.info("pabAksesori:--------" + pabAksesori);


        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia1 = new InfoAudit();
        ia1.setDimasukOleh(pengguna);
        ia1.setTarikhMasuk(new java.util.Date());



        mhnbgn = strataPtService.findByNama(bangunan, idPermohonan);

        if (mhnbgn != null) {
            bgnTingkat = strataPtService.findTinketByIdBngnNama(mhnbgn.getIdBangunan(), tingkat);
            if (bgnTingkat != null) {
                bgnpetak = strataPtService.getSenaraiPetak2(Long.parseLong(idPetak), petakAks);
                if (bgnpetak != null) {
                    bngnPetakaks = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
                    if (bngnPetakaks.isEmpty()) {
                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                        BangunanPetakAksesori bangunanPetakAksesori = new BangunanPetakAksesori();
                        bangunanPetakAksesori.setBangunan(mhnbgn);
                        bangunanPetakAksesori.setTingkat(bgnTingkat);
                        bangunanPetakAksesori.setPetak(bgnpetak);
                        bangunanPetakAksesori.setNama(namaAks);
                        bangunanPetakAksesori.setLokasi(LokasiAks);
                        bangunanPetakAksesori.setKegunaan(kodKegunaanPetakAksesori);
                        bangunanPetakAksesori.setInfoAudit(ia);
                        bangunanPetakAksesori.setCawangan(pengguna.getKodCawangan());
                        bangunanPetakAksesori.setPetakSangkut(petakBersangkutan);
                        bangunanPetakAksesori.setPabAksesori(pabAksesori);
                        strataPtService.save(bangunanPetakAksesori);
                        bgnTingkat.setBilanganPetakAksesori(bgnTingkat.getBilanganPetakAksesori() + 1);
                        strataPtService.save(bgnTingkat);
                    } else {
                        BangunanPetakAksesori bngnPetakakss = strataPtService.findByIDPetakAksr(bgnpetak.getIdPetak(), namaAks);
                        if (bngnPetakakss != null) {
                            KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                            kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                            bngnPetakakss.setNama(namaAks);
                            bngnPetakakss.setLokasi(LokasiAks);
                            bngnPetakakss.setKegunaan(kodKegunaanPetakAksesori);
                            bngnPetakakss.setInfoAudit(ia1);
                            bngnPetakakss.setCawangan(pengguna.getKodCawangan());
                            bngnPetakakss.setPetakSangkut(petakBersangkutan);
                            bngnPetakakss.setPabAksesori(pabAksesori);
                            strataPtService.save(bngnPetakakss);
                        } else {
                            KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                            kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                            BangunanPetakAksesori bangunanPetakAksesori = new BangunanPetakAksesori();
                            bangunanPetakAksesori.setBangunan(mhnbgn);
                            bangunanPetakAksesori.setTingkat(bgnTingkat);
                            bangunanPetakAksesori.setPetak(bgnpetak);
                            bangunanPetakAksesori.setNama(namaAks);
                            bangunanPetakAksesori.setLokasi(LokasiAks);
                            bangunanPetakAksesori.setKegunaan(kodKegunaanPetakAksesori);
                            bangunanPetakAksesori.setInfoAudit(ia);
                            bangunanPetakAksesori.setCawangan(pengguna.getKodCawangan());
                            bangunanPetakAksesori.setPetakSangkut(petakBersangkutan);
                            bangunanPetakAksesori.setPabAksesori(pabAksesori);
                            strataPtService.save(bangunanPetakAksesori);
                            bgnTingkat.setBilanganPetakAksesori(bgnTingkat.getBilanganPetakAksesori() + 1);
                            strataPtService.save(bgnTingkat);
                        }

                    }
                    addSimpleMessage("Maklumat Petak Aksesori Berjaya Disimpan");


                    senaraiPetakAksesori = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
                    LOG.info("xxx" + senaraiPetakAksesori);
                }
            }
        }
//        LOG.info("Jenis:--------" + Jenis);
//        mhnbgn = strataPtService.findByNama(nama, idPermohonan);
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/jadualmanual.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPetakAkTanah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String namaAks = (String) getContext().getRequest().getParameter("namaAks");
        String LokasiAks = (String) getContext().getRequest().getParameter("LokasiAks");
        String kodKegunaanPetakAks = (String) getContext().getRequest().getParameter("kodKegunaanPetakAks");
        String PelanAks = (String) getContext().getRequest().getParameter("PelanAks");
        String idbngn = (String) getContext().getRequest().getParameter("idbngn");


        LOG.info("namaAks:--------" + namaAks);
        LOG.info("LokasiAks:--------" + LokasiAks);
        LOG.info("petakAksTanah:--------" + petakAksTanah);
        LOG.info("kodKegunaanPetakAks:--------" + kodKegunaanPetakAks);
        LOG.info("PelanAks:--------" + PelanAks);
        LOG.info("idbngn:--------" + idbngn);


        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia1 = new InfoAudit();
        ia1.setDimasukOleh(pengguna);
        ia1.setTarikhMasuk(new java.util.Date());

        PermohonanBangunan mb = strataPtService.findBangunanNama(Long.valueOf(idbngn));
        bgnTingkat = strataPtService.findTinketByIdBngnNama2(Long.valueOf(idbngn));

        if (bgnTingkat != null) {
            bgnpetak = strataPtService.findPetakByIdBngnIdTinketNama(bgnTingkat.getIdTingkat(), petakAksTanah);
            if (bgnpetak != null) {
                bngnPetakaks = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
                if (bngnPetakaks.isEmpty()) {
                    KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                    kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                    BangunanPetakAksesori bangunanPetakAksesori = new BangunanPetakAksesori();
                    bangunanPetakAksesori.setBangunan(mb);
                    bangunanPetakAksesori.setTingkat(bgnTingkat);
                    bangunanPetakAksesori.setPetak(bgnpetak);
                    bangunanPetakAksesori.setNama(namaAks);
                    bangunanPetakAksesori.setLokasi(LokasiAks);
                    bangunanPetakAksesori.setKegunaan(kodKegunaanPetakAksesori);
                    bangunanPetakAksesori.setInfoAudit(ia);
                    bangunanPetakAksesori.setPabAksesori(PelanAks);
                    bangunanPetakAksesori.setCawangan(pengguna.getKodCawangan());
                    strataPtService.save(bangunanPetakAksesori);
                    addSimpleMessage("Maklumat Petak Aksesori Berjaya Disimpan");
                    bgnTingkat.setBilanganPetakAksesori(bgnTingkat.getBilanganPetakAksesori() + 1);
                    strataPtService.save(bgnTingkat);

                } else {
                    BangunanPetakAksesori bngnPetakakss = strataPtService.findByIDPetakAksr(bgnpetak.getIdPetak(), namaAks);
                    if (bngnPetakakss != null) {
                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                        bngnPetakakss.setNama(namaAks);
                        bngnPetakakss.setLokasi(LokasiAks);
                        bngnPetakakss.setKegunaan(kodKegunaanPetakAksesori);
                        bngnPetakakss.setInfoAudit(ia1);
                        bngnPetakakss.setCawangan(pengguna.getKodCawangan());
                        bngnPetakakss.setPabAksesori(PelanAks);
                        strataPtService.save(bngnPetakakss);
                        addSimpleMessage("Maklumat Petak Aksesori Berjaya Disimpan");
                    } else {
                        KodKegunaanPetakAksesori kodKegunaanPetakAksesori = new KodKegunaanPetakAksesori();
                        kodKegunaanPetakAksesori = kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks);
                        BangunanPetakAksesori bangunanPetakAksesori = new BangunanPetakAksesori();
                        bangunanPetakAksesori.setBangunan(mb);
                        bangunanPetakAksesori.setTingkat(bgnTingkat);
                        bangunanPetakAksesori.setPetak(bgnpetak);
                        bangunanPetakAksesori.setNama(namaAks);
                        bangunanPetakAksesori.setLokasi(LokasiAks);
                        bangunanPetakAksesori.setKegunaan(kodKegunaanPetakAksesori);
                        bangunanPetakAksesori.setInfoAudit(ia);
                        bangunanPetakAksesori.setPabAksesori(PelanAks);
                        bangunanPetakAksesori.setCawangan(pengguna.getKodCawangan());
                        strataPtService.save(bangunanPetakAksesori);
                        addSimpleMessage("Maklumat Petak Aksesori Berjaya Disimpan");
                        bgnTingkat.setBilanganPetakAksesori(bgnTingkat.getBilanganPetakAksesori() + 1);
                        strataPtService.save(bgnTingkat);
                    }
                }
                senaraiPetakAksesoriTanah = strataPtService.findPtkAksrByIDPetak(bgnpetak.getIdPetak());
            } else {
                addSimpleError("Maaf.Maklumat Petak Aksesori Tidak Berjaya Disimpan. Sila hubungi Pentadbir Sistem.");
            }
        } else {
            addSimpleError("Maaf.Maklumat Petak Aksesori Tidak Berjaya Disimpan. Sila hubungi Pentadbir Sistem.");
        }

        return new JSP("strata/jadualmanual2.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPetakTanah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String petak = (String) getContext().getRequest().getParameter("petak");
        LOG.info("petak" + petak);
        String kodKegunaanPetakTanah = (String) getContext().getRequest().getParameter("kodKegunaanPetakTanah");
        LOG.info("kodKegunaanPetakTanah" + kodKegunaanPetakTanah);
        String syerEdit = (String) getContext().getRequest().getParameter("syerEdit");
        LOG.info("syerEdit" + syerEdit);
        String luasEdit = (String) getContext().getRequest().getParameter("luasEdit");
        LOG.info("luasEdit" + luasEdit);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        PermohonanBangunan mb = strataPtService.findALLID(idPermohonan);
        BangunanTingkat bt = strataPtService.findbyBngnTgkt(mb.getIdBangunan());
        BangunanPetak bp = strataPtService.findBangunanPetak(petak, bt.getIdTingkat());

        if (bp != null) {
            bp.setLuas(BigDecimal.valueOf(Integer.valueOf(luasEdit)));
            bp.setKegunaan(kodKegunaanPetakDAO.findById(kodKegunaanPetakTanah));
            bp.setLuasUom(kodUOMDAO.findById("M"));
            bp.setSyer(Integer.parseInt(syerEdit));
            bp.setInfoAudit(ia);
            strataPtService.save(bp);
            addSimpleMessage("Maklumat berjaya disimpan.");
        }
        rehydrate();

        return new RedirectResolution(JadualPetakManual.class);
//        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution hapusPetakTanah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String petakTanahHapus = (String) getContext().getRequest().getParameter("petakTanahHapus");
        LOG.info("petakTanahHapus" + petakTanahHapus);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        PermohonanBangunan mb = strataPtService.findALLID(idPermohonan);
        BangunanTingkat bt = strataPtService.findbyBngnTgkt(mb.getIdBangunan());
        BangunanPetak bp = strataPtService.findBangunanPetak(petakTanahHapus, bt.getIdTingkat());
        List<BangunanPetakAksesori> bpa = strataPtService.findByIDPetakAksr2(bp.getIdPetak());

        if (bp != null) {
            Integer bpasize = bpa.size();
            bt.setBilanganPetakAksesori(bt.getBilanganPetakAksesori() - bpasize);
            strataPtService.save(bt);

            strataPtService.deleteBngnPetakAksbyTingkatidBngn(mb.getIdBangunan(), bt.getIdTingkat());
            strataPtService.deleteBngnPetakbyidPetak(bp.getIdPetak());

            Integer bilPetak = mb.getBilanganPetak();
            bilPetak = bilPetak - 1;
            mb.setBilanganPetak(bilPetak);
            strataPtService.save(mb);
            Integer bilPetak2 = bt.getBilanganPetak();
            bilPetak2 = bilPetak2 - 1;
            bt.setBilanganPetak(bilPetak2);
            strataPtService.save(bt);
            cekLanded = strataPtService.findLanded(idPermohonan);

            addSimpleMessage("Maklumat berjaya dihapus.");
        }
        rehydrate();

        return new RedirectResolution(JadualPetakManual.class);
    }

    public Resolution hapusPetak() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String petakHapus = (String) getContext().getRequest().getParameter("petakHapus");
        String idPetak = (String) getContext().getRequest().getParameter("idPetak");
        LOG.info("petakHapus" + petakHapus);
        LOG.info("idPetak" + idPetak);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        BangunanPetak bp = strataPtService.getSenaraiPetak2(Long.parseLong(idPetak), petakHapus);
        BangunanTingkat bt = strataPtService.findByPetak(String.valueOf(bp.getTingkat().getIdTingkat()));
        PermohonanBangunan mb = strataPtService.findBangunanNama(bt.getBangunan().getIdBangunan());
        List<BangunanPetakAksesori> bpa = strataPtService.findByIDPetakAksr2(bp.getIdPetak());

        if (bp != null) {
            Integer bpasize = bpa.size();
            bt.setBilanganPetakAksesori(bt.getBilanganPetakAksesori() - bpasize);
            strataPtService.save(bt);
            strataPtService.deleteBngnPetakAksbyTingkatidBngn(mb.getIdBangunan(), bt.getIdTingkat());
            strataPtService.deleteBngnPetakbyidPetak(bp.getIdPetak());

            Integer bilPetak = mb.getBilanganPetak();
            bilPetak = bilPetak - 1;
            mb.setBilanganPetak(bilPetak);
            strataPtService.save(mb);
            Integer bilPetak2 = bt.getBilanganPetak();
            bilPetak2 = bilPetak2 - 1;
            bt.setBilanganPetak(bilPetak2);
            strataPtService.save(bt);
            cekMhnBngn = strataPtService.checkMhnBangunanExist2(idPermohonan);
            mhnbgn = strataPtService.findByNama(mb.getNama(), idPermohonan);
            addSimpleMessage("Maklumat berjaya dihapus.");
        }

        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(bt.getBangunan().getIdBangunan());

        bngnnPetak = strataPtService.findByIDPetak2(bt.getIdTingkat());

        getContext().getRequest().setAttribute("petak", Boolean.TRUE);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution viewJadualPetak() {
        return new JSP("strata/utiliti/jadualPetakManualview.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--rehydrate--");
//        stageId = getBPLStageId();
//        bilBangunan = null;
//        tingkat = null;
//        bilPetak = null;
//        petak = null;
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = permohonanDAO.findById(idPermohonan);

        kodnegeri = conf.getProperty("kodNegeri");

//        senaraiTgktbyBgnn = strataPtService.findTgktAllIdBgnn(idPermohonan);
//        senaraiPetakByAllTgkt = strataPtService.findPetakAllTgkt(idPermohonan);
//        senaraiPetakAksesoribyAllbgnn = strataPtService.findPetakAksrAllBgnn(idPermohonan);
        Dokumen d = strataPtService.findDok(mohon.getIdPermohonan(), "JPP");

        if (d != null && d.getNamaFizikal() == null) {
            senaraiPermohonanBangunan = strataPtService.checkMhnBangunanExist(idPermohonan);

            if (!senaraiPermohonanBangunan.isEmpty()) {
                String x = senaraiPermohonanBangunan.get(0).getNamaLain();

                if (x != null) {
                    int i = 1;

                    for (PermohonanBangunan permohonanBangunan : senaraiPermohonanBangunan) {
                        if (permohonanBangunan.getNamaLain() != null && permohonanBangunan.getKekal() == 'T') {
                            permohonanBangunan.setNama("M" + i);
                            permohonanBangunan.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("B"));
                            strataPtService.simpanBangunan(permohonanBangunan);
                        }
                        i++;
                    }


                    mohon = strataPtService.findPermohonanSblm(idPermohonan);
                    cekMhnBngn = strataPtService.checkMhnBangunanExist2(idPermohonan);
                    cekLanded = strataPtService.findLanded(idPermohonan);

                    HakmilikPermohonan hp = strataPtService.findMohonHakmilik(idPermohonan);
                    PermohonanSkim mhnSkim = strataPtService.findIDSkimByIDMH(hp.getId());
                    LOG.info("--cekMhnBngn--" + cekMhnBngn);

                    if (mhnSkim != null) {
                        List<PermohonanBangunan> mhnBngn = strataPtService.checkMhnBangunanExist(idPermohonan);
                        if (!mhnBngn.isEmpty()) {
                            int jumPetak = 0;
                            int aks = 0;
                            int syer = 0;
                            for (PermohonanBangunan mhnBangunan : mhnBngn) {
                                jumPetak = jumPetak + mhnBangunan.getBilanganPetak();
                                syer = syer + mhnBangunan.getSyerBlok();
                                List<BangunanPetakAksesori> PetakAKS = strataPtService.findByIDBgnn(Long.toString(mhnBangunan.getIdBangunan()));
                                aks = aks + PetakAKS.size();
                            }
                            mhnSkim.setJumlahUnitSyer(Long.valueOf(syer));
                            mhnSkim.setBilAksr(Long.valueOf(aks));
                            mhnSkim.setBilPetak(Long.valueOf(jumPetak));
                            strataPtService.saveSkim(mhnSkim);
                        }
                    }

                    if (cekLanded != null) {
                        bngntgkt = strataPtService.findTinketByIdBngnNama2(cekLanded.getIdBangunan());
                        senaraibngnPetak = strataPtService.findByPetakByIDTingkat(bngntgkt.getIdTingkat());
                        senaraiPetakAksesoriTanahbyIdTgkt = strataPtService.findPtkAksrByIdTgkt(bngntgkt.getIdTingkat());
                        LOG.info("senaraiPetakAksesoriTanahbyIdTgkt---" + senaraiPetakAksesoriTanahbyIdTgkt);
                        getContext().getRequest().setAttribute("jadual", Boolean.TRUE);
//                        getContext().getRequest().setAttribute("xml", Boolean.TRUE);
                    }

                    LOG.info("--cekLanded--" + cekLanded);
                    if (!cekMhnBngn.isEmpty()) {
                        Integer IntbilBangunan = cekMhnBngn.size();
                        bilBangunan = IntbilBangunan.toString();

                        for (PermohonanBangunan permohonanBangunan : cekMhnBngn) {
                            List<BangunanTingkat> bngntgkt = strataPtService.findByIDBangunanList(permohonanBangunan.getIdBangunan());
                            if (bngntgkt != null) {
                                Integer jumPetak = 0;
                                for (BangunanTingkat bangunanTingkat : bngntgkt) {
                                    int Petak = bangunanTingkat.getBilanganPetak();
                                    jumPetak = Petak + jumPetak;
                                    LOG.info("--jumlah petak --" + jumPetak);
                                    PermohonanBangunan mohonBngn = strataPtService.findBangunanNama(permohonanBangunan.getIdBangunan());
                                    mohonBngn.setBilanganPetak(jumPetak);
                                    strataPtService.save(mohonBngn);
                                }
                            }
                        }
                        getContext().getRequest().setAttribute("jadual", Boolean.TRUE);
                    }
                }
            }
        } else {
            getContext().getRequest().setAttribute("xml", Boolean.FALSE);

            senaraiPermohonanBangunan = strataPtService.checkMhnBangunanExist(idPermohonan);

            if (!senaraiPermohonanBangunan.isEmpty()) {
                String x = senaraiPermohonanBangunan.get(0).getNamaLain();
                if (x != null) {
                    int i = 1;

                    for (PermohonanBangunan permohonanBangunan : senaraiPermohonanBangunan) {
                        if (permohonanBangunan.getNamaLain() != null && permohonanBangunan.getKekal() == 'T') {
                            permohonanBangunan.setNama("M" + i);
                            permohonanBangunan.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("B"));
                            strataPtService.simpanBangunan(permohonanBangunan);
                        }
                        i++;
                    }


                    mohon = strataPtService.findPermohonanSblm(idPermohonan);
                    cekMhnBngn = strataPtService.checkMhnBangunanExist2(idPermohonan);
                    cekLanded = strataPtService.findLanded(idPermohonan);

                    HakmilikPermohonan hp = strataPtService.findMohonHakmilik(idPermohonan);
                    PermohonanSkim mhnSkim = strataPtService.findIDSkimByIDMH(hp.getId());

                    if (mhnSkim != null) {
                        List<PermohonanBangunan> mhnBngn = strataPtService.checkMhnBangunanExist(idPermohonan);
                        if (!mhnBngn.isEmpty()) {
                            int jumPetak = 0;
                            int aks = 0;
                            int syer = 0;
                            for (PermohonanBangunan mhnBangunan : mhnBngn) {
                                jumPetak = jumPetak + mhnBangunan.getBilanganPetak();
                                syer = syer + mhnBangunan.getSyerBlok();
                                List<BangunanPetakAksesori> PetakAKS = strataPtService.findByIDBgnn(Long.toString(mhnBangunan.getIdBangunan()));
                                aks = aks + PetakAKS.size();
                            }
                            mhnSkim.setJumlahUnitSyer(Long.valueOf(syer));
                            mhnSkim.setBilAksr(Long.valueOf(aks));
                            mhnSkim.setBilPetak(Long.valueOf(jumPetak));
                            strataPtService.saveSkim(mhnSkim);
                        }
                    }

                    if (cekLanded != null) {
                        bngntgkt = strataPtService.findTinketByIdBngnNama2(cekLanded.getIdBangunan());
                        senaraibngnPetak = strataPtService.findByPetakByIDTingkat(bngntgkt.getIdTingkat());
                        senaraiPetakAksesoriTanahbyIdTgkt = strataPtService.findPtkAksrByIdTgkt(bngntgkt.getIdTingkat());
                        LOG.info("senaraiPetakAksesoriTanahbyIdTgkt---" + senaraiPetakAksesoriTanahbyIdTgkt);
                        getContext().getRequest().setAttribute("jadual", Boolean.TRUE);
                    }

                    LOG.info("--cekLanded--" + cekLanded);
                    if (cekMhnBngn != null) {
                        Integer IntbilBangunan = cekMhnBngn.size();
                        bilBangunan = IntbilBangunan.toString();

                        for (PermohonanBangunan permohonanBangunan : cekMhnBngn) {
                            List<BangunanTingkat> bngntgkt = strataPtService.findByIDBangunanList(permohonanBangunan.getIdBangunan());
                            if (bngntgkt != null) {
                                Integer jumPetak = 0;
                                for (BangunanTingkat bangunanTingkat : bngntgkt) {
                                    int Petak = bangunanTingkat.getBilanganPetak();
                                    jumPetak = Petak + jumPetak;
                                    LOG.info("--jumlah petak --" + jumPetak);
                                    PermohonanBangunan mohonBngn = strataPtService.findBangunanNama(permohonanBangunan.getIdBangunan());
                                    mohonBngn.setBilanganPetak(jumPetak);
                                    strataPtService.save(mohonBngn);
                                }
                            }
                        }
                        getContext().getRequest().setAttribute("jadual", Boolean.TRUE);
                    }
                }
                else{
                    getContext().getRequest().setAttribute("xml", Boolean.TRUE);
                }
            }
        }

        showForm();
    }

    public Resolution deleteTingkat() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        String tgkt = (String) getContext().getRequest().getParameter("tingkat");
        String idbngn = (String) getContext().getRequest().getParameter("idbngn");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        cekMhnBngn = strataPtService.checkMhnBangunanExist(idPermohonan);
        LOG.info("tgkt" + tgkt);
        LOG.info("idbngn -- " + idbngn);
        Long idbngnn = Long.parseLong(idbngn);
        InfoAudit ia1 = new InfoAudit();
        ia1.setDiKemaskiniOleh(pengguna);
        ia1.setTarikhKemaskini(new java.util.Date());

        BangunanTingkat bt = strataPtService.findTinketByIdBngnNama(idbngnn, tgkt);
        mhnbgn = strataPtService.findBangunanNama(idbngnn);
        mhnbgn.setBilanganTingkat(mhnbgn.getBilanganTingkat() - 1);
        mhnbgn.setBilanganPetak(mhnbgn.getBilanganPetak() - bt.getBilanganPetak());
        strataPtService.save(mhnbgn);


        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(bt.getBangunan().getIdBangunan());
        LOG.info(bt.getIdTingkat());
        strataPtService.deleteBngnPetakAksbyTingkatidBngn(idbngnn, bt.getIdTingkat());
        strataPtService.deleteBngnPetak(bt.getIdTingkat());
        strataPtService.deleteBngnTgktNama(idbngnn, tgkt);

        cekMhnBngn = strataPtService.checkMhnBangunanExist2(idPermohonan);
        addSimpleMessage("Maklumat Tingkat " + tingkat + " Berjaya Dihapus");


        mhnbgn = strataPtService.findBangunanNama(idbngnn);


        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(bt.getBangunan().getIdBangunan());


        rehydrate();
        senaraiTgktbyBgnn = strataPtService.findByIDBangunan(mhnbgn.getIdBangunan());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tambhTgkt", Boolean.FALSE);
        getContext().getRequest().setAttribute("senaraitgkat", Boolean.TRUE);
        return new JSP("strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public Resolution delete() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = strataPtService.findPermohonanSblm(idPermohonan);
        cekMhnBngn = strataPtService.checkMhnBangunanExist2(idPermohonan);
        cekLanded = strataPtService.findLanded(idPermohonan);

        if (cekLanded != null) {//mohon_bngn-landed
            bngntgkt = strataPtService.findTinketByIdBngnNama2(cekLanded.getIdBangunan());
            if (bngntgkt != null) {//bngn_tgkt
//                BangunanPetak bgnPetak = strataPtService.findPetakByIdBngnIdTinketNama2(bngntgkt.getIdTingkat());
//                if (bgnPetak != null) {//bngn_petak
                strataPtService.deleteBngnPetakAks(cekLanded.getIdBangunan());
                strataPtService.deleteBngnPetak(bngntgkt.getIdTingkat());
//                }
                strataPtService.deleteBngnTgkt(cekLanded.getIdBangunan());
            }
            strataPtService.deleteMhnBngn(idPermohonan);
            LOG.info("--Landed hapus done--");
        }


        if (cekMhnBngn != null) {//mohon_bngn-M
            for (PermohonanBangunan permohonanBangunan : cekMhnBngn) {
                List<BangunanTingkat> bngntgkt = strataPtService.findByIDBangunanList(permohonanBangunan.getIdBangunan());
                if (bngntgkt != null) {//bngn_tgkt
                    for (BangunanTingkat bangunanTingkat : bngntgkt) {//bngn_petak
                        strataPtService.deleteBngnPetakAks(permohonanBangunan.getIdBangunan());
                        strataPtService.deleteBngnPetak(bangunanTingkat.getIdTingkat());
                    }
                }
                strataPtService.deleteBngnTgkt(permohonanBangunan.getIdBangunan());
            }
            strataPtService.deleteMhnBngn(idPermohonan);
            LOG.info("--Bangunan hapus done--");
        }


        addSimpleMessage("Maklumat Jadual Petak Berjaya Dihapus");
        rehydrate();

        return new RedirectResolution(JadualPetakManual.class);
//       return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/jadualPetakManual.jsp").addParameter("tab", "true");
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public String getBilTingkat() {
        return bilTingkat;
    }

    public void setBilTingkat(String bilTingkat) {
        this.bilTingkat = bilTingkat;
    }

    public String getBilPetak() {
        return bilPetak;
    }

    public void setBilPetak(String bilPetak) {
        this.bilPetak = bilPetak;
    }

    public PermohonanBangunan getMhnbgn() {
        return mhnbgn;
    }

    public void setMhnbgn(PermohonanBangunan mhnbgn) {
        this.mhnbgn = mhnbgn;
    }

    public List<PermohonanBangunan> getCekMhnBngn() {
        return cekMhnBngn;
    }

    public void setCekMhnBngn(List<PermohonanBangunan> cekMhnBngn) {
        this.cekMhnBngn = cekMhnBngn;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public BangunanTingkat getBgnTingkat() {
        return bgnTingkat;
    }

    public void setBgnTingkat(BangunanTingkat bgnTingkat) {
        this.bgnTingkat = bgnTingkat;
    }

    public String getPetak() {
        return petak;
    }

    public void setPetak(String petak) {
        this.petak = petak;
    }

    public String getPetakMula() {
        return petakMula;
    }

    public void setPetakMula(String petakMula) {
        this.petakMula = petakMula;
    }

    public List<BangunanPetak> getBngnnPetak() {
        return bngnnPetak;
    }

    public void setBngnnPetak(List<BangunanPetak> bngnnPetak) {
        this.bngnnPetak = bngnnPetak;
    }

    public String getBangunan() {
        return bangunan;
    }

    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }

    public String getPetakAks() {
        return petakAks;
    }

    public void setPetakAks(String petakAks) {
        this.petakAks = petakAks;
    }

    public List<BangunanPetakAksesori> getSenaraiPetakAksesori() {
        return senaraiPetakAksesori;
    }

    public void setSenaraiPetakAksesori(List<BangunanPetakAksesori> senaraiPetakAksesori) {
        this.senaraiPetakAksesori = senaraiPetakAksesori;
    }

    public List<BangunanPetakAksesori> getSenaraiPetakAksesoribyIdTgkt() {
        return senaraiPetakAksesoribyIdTgkt;
    }

    public void setSenaraiPetakAksesoribyIdTgkt(List<BangunanPetakAksesori> senaraiPetakAksesoribyIdTgkt) {
        this.senaraiPetakAksesoribyIdTgkt = senaraiPetakAksesoribyIdTgkt;
    }

    public BangunanPetak getBangunanPetak() {
        return bangunanPetak;
    }

    public void setBangunanPetak(BangunanPetak bangunanPetak) {
        this.bangunanPetak = bangunanPetak;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getBilTanah() {
        return bilTanah;
    }

    public void setBilTanah(String bilTanah) {
        this.bilTanah = bilTanah;
    }

    public String getBilTanah2() {
        return bilTanah2;
    }

    public void setBilTanah2(String bilTanah2) {
        this.bilTanah2 = bilTanah2;
    }

    public String getPickjenis() {
        return pickjenis;
    }

    public void setPickjenis(String pickjenis) {
        this.pickjenis = pickjenis;
    }

    public PermohonanBangunan getCekLanded() {
        return cekLanded;
    }

    public void setCekLanded(PermohonanBangunan cekLanded) {
        this.cekLanded = cekLanded;
    }

    public BangunanPetak getBngnPetak() {
        return bngnPetak;
    }

    public void setBngnPetak(BangunanPetak bngnPetak) {
        this.bngnPetak = bngnPetak;
    }

    public List<BangunanPetak> getSenaraibngnPetak() {
        return senaraibngnPetak;
    }

    public void setSenaraibngnPetak(List<BangunanPetak> senaraibngnPetak) {
        this.senaraibngnPetak = senaraibngnPetak;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<BangunanPetakAksesori> getBngnPetakaks() {
        return bngnPetakaks;
    }

    public void setBngnPetakaks(List<BangunanPetakAksesori> bngnPetakaks) {
        this.bngnPetakaks = bngnPetakaks;
    }

    public List<PermohonanBangunan> getSenaraiPermohonanBangunan() {
        return senaraiPermohonanBangunan;
    }

    public void setSenaraiPermohonanBangunan(List<PermohonanBangunan> senaraiPermohonanBangunan) {
        this.senaraiPermohonanBangunan = senaraiPermohonanBangunan;
    }

    public List<BangunanPetakAksesori> getSenaraiPetakAksesoriTanah() {
        return senaraiPetakAksesoriTanah;
    }

    public void setSenaraiPetakAksesoriTanah(List<BangunanPetakAksesori> senaraiPetakAksesoriTanah) {
        this.senaraiPetakAksesoriTanah = senaraiPetakAksesoriTanah;
    }

    public String getPetakAksTanah() {
        return petakAksTanah;
    }

    public void setPetakAksTanah(String petakAksTanah) {
        this.petakAksTanah = petakAksTanah;
    }

    public String getPetakTanah() {
        return petakTanah;
    }

    public void setPetakTanah(String petakTanah) {
        this.petakTanah = petakTanah;
    }

    public PermohonanBangunan getMhnbgnTanah() {
        return mhnbgnTanah;
    }

    public void setMhnbgnTanah(PermohonanBangunan mhnbgnTanah) {
        this.mhnbgnTanah = mhnbgnTanah;
    }

    public List<BangunanPetakAksesori> getSenaraiPetakAksesoriTanahbyIdTgkt() {
        return senaraiPetakAksesoriTanahbyIdTgkt;
    }

    public void setSenaraiPetakAksesoriTanahbyIdTgkt(List<BangunanPetakAksesori> senaraiPetakAksesoriTanahbyIdTgkt) {
        this.senaraiPetakAksesoriTanahbyIdTgkt = senaraiPetakAksesoriTanahbyIdTgkt;
    }

    public List<BangunanPetakAksesori> getSenaraiPetakAksesoribyAllbgnn() {
        return senaraiPetakAksesoribyAllbgnn;
    }

    public void setSenaraiPetakAksesoribyAllbgnn(List<BangunanPetakAksesori> senaraiPetakAksesoribyAllbgnn) {
        this.senaraiPetakAksesoribyAllbgnn = senaraiPetakAksesoribyAllbgnn;
    }

    public List<BangunanPetak> getSenaraiPetakByAllTgkt() {
        return senaraiPetakByAllTgkt;
    }

    public void setSenaraiPetakByAllTgkt(List<BangunanPetak> senaraiPetakByAllTgkt) {
        this.senaraiPetakByAllTgkt = senaraiPetakByAllTgkt;
    }

    public List<BangunanTingkat> getSenaraiTgktbyBgnn() {
        return senaraiTgktbyBgnn;
    }

    public void setSenaraiTgktbyBgnn(List<BangunanTingkat> senaraiTgktbyBgnn) {
        this.senaraiTgktbyBgnn = senaraiTgktbyBgnn;
    }

    public BangunanPetak getBgnpetak() {
        return bgnpetak;
    }

    public void setBgnpetak(BangunanPetak bgnpetak) {
        this.bgnpetak = bgnpetak;
    }

    public String getKodnegeri() {
        return kodnegeri;
    }

    public void setKodnegeri(String kodnegeri) {
        this.kodnegeri = kodnegeri;
    }
}
