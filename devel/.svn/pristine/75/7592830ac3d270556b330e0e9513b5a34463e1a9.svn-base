/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import java.text.ParseException;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.strata.*;
import org.apache.commons.lang.StringUtils;
import etanah.dao.*;
import java.math.BigDecimal;
import etanah.workflow.WorkFlowService;
import etanah.report.ReportUtil;
import etanah.service.common.PermohonanService;
import org.hibernate.Session;
import org.hibernate.Query;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.manager.TabManager;
import etanah.model.*;
import etanah.service.*;
import etanah.view.*;
import etanah.view.strata.*;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.model.KodStatusHakmilik;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.service.daftar.PembetulanService;
import java.text.SimpleDateFormat;
import java.io.*;
import net.sourceforge.stripes.action.*;
import org.hibernate.Transaction;
import java.io.IOException;
import etanah.util.FileUtil;
import java.util.*;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/strataConversion")
public class strataConversion extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(strataConversion.class);
    private static boolean isDebug = logger.isDebugEnabled();
    String negeri;
    String kodDaerah;
    private String kodNegeri;
    private String caw;
    private String noHakmilik;
    private String petakMula;
    private String namaPembayar;
    private String noPengenalanP;
    private String kodHakmilik;
    private String bandarPekanMukim;
    private String seksyen;
    private String lot;
    private String kodLot;
    private String noLot;
    private String kodLuas;
    private String noLuas;
    private String tarikhDaftar;
    private String noFailRujukan;
    private String noSijil;
    private String namaPerbadananPengurusan;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String kodnegeri;
    private String alamatSurat1;
    private String alamatSurat2;
    private String alamatSurat3;
    private String alamatSurat4;
    private String poskodSurat;
    private String negeriSurat;
    private String noBukuStrata;
    private String noSyit;
    private String jumsyer;
    private String luas;
    private String noBukuStrataAsal;
    private String noPetakMula;
    private String noPetakAkhir;
    private String noBangunan;
    private String noPetak;
    private String noTingkat;
    private String idhakmilikbaru;
    private String noBangunanbaru;
    private String noFolio;
    private String syerPetak;
    private String kodKegunaanBangunan;
    private String idHakmilikNull;
    private String selectedTabs;
    private String idHakmilikInduk;
    private String idHakmilikEdit;
    private String versi;
    private String stsHakmilik;
    String namaBPM;
    int kodBPM;
    String daerah;
    String jenisHakmilik;
    String jenisLot;
    private Hakmilik hakmilik;
    private Hakmilik hakmilikEdit;
    private Pengguna pengguna;
    private Permohonan mohon;
    private Akaun akaunKredit;
    private FileBean file;
    private List<FileBean> files;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<HakmilikPetakAksesori> senaraiPetakAksesori;
    private List<HakmilikPetakAksesori> senaraiPetakAksesori2;
    private List<HakmilikPetakAksesori> senaraiPelanTambahan;
    private List<FileBean> senaraiPelan = new ArrayList();
    private List<String> senaraiPelanUpload = new ArrayList();
    private List<String> senaraiPelanString = new ArrayList();
    @Inject
    etanah.Configuration etanahConf;
    @Inject
    etanah.kodHasilConfig khconf;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    RegService regService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodAkaunDAO kodAkaunDAO;
    @Inject
    KodStatusAkaunDAO kodStatusAkaunDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    KodLotDAO kodLotDAO;
    @Inject
    KodKategoriBangunanDAO kodKategoriBangunanDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    @Inject
    KodKegunaanPetakAksesoriDAO kodKegunaanPetakAksesoriDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    HakmilikLamaDAO hakmilikLamaDAO;
    @Inject
    HakmilikPihakKepentinganService hpkService;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    SejarahTransaksiDAO sejarahTransaksiDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    WorkFlowService WorkFlowService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPihakBerkepentinganDAO hpDAO;
    @Inject
    TabManager tabManager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    StrataPtService strataPtService;
    @Inject
    PembetulanService pService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    String idHakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sd = new SimpleDateFormat("dd/MM");
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    etanahActionBeanContext ctx;
    private List<Hakmilik> senaraiHakmilik = new ArrayList();
    private List<Hakmilik> hm2 = new ArrayList();
    private List<Hakmilik> senaraiHakmilikStrata = new ArrayList();
    private List<Hakmilik> senaraiHakmilikStrataProv = new ArrayList();
    private List<Hakmilik> senaraiHakmilikStrataTemp = new ArrayList();
    private List<Hakmilik> senaraiHakmilikStrataDaftar = new ArrayList();
    private List<KodSeksyen> senaraiKodSeksyen;
    private List<KodStatusHakmilik> listKodStatusHakmilik;
    private boolean flag = false;
    private List<KodBandarPekanMukim> senaraiBPM;
    private static final Logger LOG = Logger.getLogger(strataConversion.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("details", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution kembali() {
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution refresh() {
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution search() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");

        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bandarPekanMukim));
        String bpm1 = bpm.getbandarPekanMukim();
        String negeri2 = etanahConf.getProperty("kodNegeri");
        String noHakmilikBaru = String.format("%08d", Integer.parseInt(noHakmilik));
        String bandarPekanMukimBaru = String.format("%02d", Integer.parseInt(bpm1));
        String daerahBaru = String.format("%02d", Integer.parseInt(daerah));
        idHakmilikNull = negeri2 + daerahBaru + bandarPekanMukimBaru + kodHakmilik + noHakmilikBaru + "NULL";

        LOG.info("daerah " + daerah + "bandarPekanMukim " + bandarPekanMukim + "kodHakmilik " + kodHakmilik + "noHakmilik " + noHakmilik);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        penyukatanBPM();
        if (daerah != null && bandarPekanMukim != null && kodHakmilik != null && noHakmilik != null) {
            senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
            senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
            senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);

            if (!senaraiHakmilik.isEmpty()) {
//                senaraiPelan.add(new FileBean());
                senaraiPelanUpload.clear();
//                senaraiPelan = strataPtService.findNoPelan(senaraiHakmilik.get(0).getIdHakmilik());
                List<String> senaraiPelanHm = strataPtService.findNoPelanHmString(senaraiHakmilik.get(0).getIdHakmilik());
                List<String> senaraiPelanHmAksr = strataPtService.findNoPelanAksrString(senaraiHakmilik.get(0).getIdHakmilik());
                for (String pelanhm : senaraiPelanHm) {
                    senaraiPelanString.add(pelanhm);
                }
                for (String pelanaksr : senaraiPelanHmAksr) {
                    senaraiPelanString.add(pelanaksr);
                }
                Collections.sort(senaraiPelanString);
                for (int i = 0; i < senaraiPelanString.size(); i++) {
                    viewPelan(senaraiPelanString.get(i).toString());

                }
                String idhakmilik1 = senaraiHakmilik.get(0).getIdHakmilik();
                senaraiPetakAksesori = strataPtService.findHakmilikPetakAksesori(idhakmilik1);
                if (!senaraiHakmilikStrata.isEmpty()) {
                    senaraiHakmilikStrataDaftar = strataPtService.findHakmilibyNotInProv(senaraiHakmilikStrata.get(0).getIdHakmilikInduk());
                    List<String> nobgnn = strataPtService.findNoBangunanProv(senaraiHakmilikStrata.get(0).getIdHakmilikInduk());

                    for (int i = 0; i < nobgnn.size(); i++) {
                        String nobgn = nobgnn.get(i);
                        List<Hakmilik> hmbngn = strataPtService.findHm(senaraiHakmilikStrata.get(0).getIdHakmilikInduk(), nobgn);
                        if (hmbngn != null) {
                            senaraiHakmilikStrataProv.add(hmbngn.get(0));
                        }
                    }
                }
            }
            LOG.info("senaraiHakmilik " + senaraiHakmilik);
            LOG.info("senaraiHakmilikStrata " + senaraiHakmilikStrata);

            if (!senaraiHakmilik.isEmpty()) {
                kodLot = senaraiHakmilik.get(0).getLot().getKod();
                noLot = senaraiHakmilik.get(0).getNoLot();
                kodLuas = senaraiHakmilik.get(0).getKodUnitLuas().getKod();
                noLuas = senaraiHakmilik.get(0).getLuas().toString();

                if (senaraiHakmilikStrata.size() > 0) {
                    if (senaraiHakmilikStrata.get(0).getTarikhDaftar() != null) {
                        tarikhDaftar = sdf.format(senaraiHakmilikStrata.get(0).getTarikhDaftar());
                    }
                    noFailRujukan = senaraiHakmilikStrata.get(0).getNoFail();
                    noSijil = senaraiHakmilikStrata.get(0).getNoSkim();
                    noBukuStrata = senaraiHakmilikStrata.get(0).getNoBukuDaftarStrata();
                    noSyit = senaraiHakmilikStrata.get(0).getNoPu();
                    noBukuStrataAsal = senaraiHakmilikStrata.get(0).getNoBukuDaftarStrata();
                    jumsyer = senaraiHakmilikStrata.get(0).getJumlahUnitSyer().toString();
                    stsHakmilik = senaraiHakmilikStrata.get(0).getKodStatusHakmilik().getNama();
                    
                    if (senaraiHakmilikStrata.get(0).getBadanPengurusan() != null) {
                        BadanPengurusan bdnUrus = strataPtService.findBdnByIdBdn(senaraiHakmilikStrata.get(0).getBadanPengurusan().getIdBadan());
                        Pihak phk = strataPtService.findByIdPihak(bdnUrus.getPihak().getIdPihak());
                        if (phk != null) {

                            namaPerbadananPengurusan = phk.getNama();
                            alamat1 = phk.getAlamat1();
                            if (phk.getAlamat2() != null) {
                                alamat2 = phk.getAlamat2();
                            }
                            if (phk.getAlamat3() != null) {
                                alamat3 = phk.getAlamat3();
                            }
                            if (phk.getAlamat4() != null) {
                                alamat4 = phk.getAlamat4();
                            }
                            if (phk.getPoskod() != null) {
                                poskod = phk.getPoskod();
                            }
                            if (phk.getNegeri() != null) {
                                kodnegeri = phk.getNegeri().getKod();
                            }
                            alamatSurat1 = phk.getSuratAlamat1();
                            if (phk.getSuratAlamat2() != null) {
                                alamatSurat2 = phk.getSuratAlamat2();
                            }
                            if (phk.getSuratAlamat3() != null) {
                                alamatSurat3 = phk.getSuratAlamat3();
                            }
                            if (phk.getSuratAlamat4() != null) {
                                alamatSurat4 = phk.getSuratAlamat4();
                            }
                            if (phk.getSuratPoskod() != null) {
                                poskodSurat = phk.getSuratPoskod();
                            }
                            if (phk.getSuratNegeri() != null) {
                                negeriSurat = phk.getSuratNegeri().getKod();
                            }
                        }

                    }
                } else {
                    addSimpleError("Hakmilik ini belum memohon Strata. Sila masukkan Hakmilik Strata yang baru.");

                    //senaraiHakmilik.get(0).getIdHakmilik()
//                Hakmilik induk = strataPtService.findInfoByIdHakmilik(senaraiHakmilik.get(0).getIdHakmilik());
                    tarikhDaftar = null;
                    noFailRujukan = null;
                    noSijil = null;
                    noBukuStrata = null;
                    noBukuStrataAsal = null;
                    noSyit = null;

                    HakmilikPermohonan hpHTB = strataPtService.findIdHakmilikHTB(senaraiHakmilik.get(0).getIdHakmilik());
                    if (hpHTB != null) {
                        PermohonanPihak mohonPihak = strataPtService.findPihakHTB(hpHTB.getPermohonan().getIdPermohonan());

                        if (mohonPihak != null) {
                            Pihak pihak = strataPtService.findByIdPihak(mohonPihak.getPihak().getIdPihak());

                            namaPerbadananPengurusan = pihak.getNama();
                            alamat1 = pihak.getAlamat1();
                            alamat2 = pihak.getAlamat2();
                            alamat3 = pihak.getAlamat3();
                            alamat4 = pihak.getAlamat4();
                            poskod = pihak.getPoskod();
                            if (pihak.getNegeri() != null) {
                                kodnegeri = pihak.getNegeri().getKod();
                            }

                            alamatSurat1 = pihak.getSuratAlamat1();
                            alamatSurat2 = pihak.getSuratAlamat2();
                            alamatSurat3 = pihak.getSuratAlamat3();
                            alamatSurat4 = pihak.getSuratAlamat4();
                            poskodSurat = pihak.getSuratPoskod();
                            if (pihak.getSuratNegeri() != null) {
                                negeriSurat = pihak.getSuratNegeri().getKod();
                            }

                            BadanPengurusan bdnUrus = strataPtService.findBdnbyIdPihak(pihak.getIdPihak());
                            if (bdnUrus == null) {
                                bdnUrus = new BadanPengurusan();
                                if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
                                    bdnUrus.setCawangan(kodCawanganDAO.findById("00"));
                                } else {
                                    bdnUrus.setCawangan(kodCawanganDAO.findById(daerahBaru));
                                }
                                bdnUrus.setInfoAudit(ia);
                                bdnUrus.setPihak(pihak);
                            }
                        }
                    }

                    kodKegunaanBangunan = null;
                    noFolio = null;
                    noBangunan = null;
                    noTingkat = null;
                    noPetakMula = null;
                    noPetakAkhir = null;
                    syerPetak = null;
                    noSyit = senaraiHakmilik.get(0).getNoLitho();
                    jumsyer = null;
                }
                getContext().getRequest().setAttribute("details", Boolean.TRUE);
            } else {
                addSimpleError("Hakmilik ini tidak dijumpai. Sila masukkan hakmilik yang betul.");
                kodLot = "";
                noLot = null;
                kodLuas = "";
                noLuas = null;
                tarikhDaftar = null;
                noFailRujukan = null;
                noSijil = null;
                noBukuStrata = null;
                noBukuStrataAsal = null;
                noSyit = null;
                namaPerbadananPengurusan = null;
                alamat1 = null;
                alamat2 = null;
                alamat3 = null;
                alamat4 = null;
                poskod = null;
                kodnegeri = null;
                alamatSurat1 = null;
                alamatSurat2 = null;
                alamatSurat3 = null;
                alamatSurat4 = null;
                poskodSurat = null;
                negeriSurat = null;

                kodKegunaanBangunan = null;
                noFolio = null;
                noBangunan = null;
                noTingkat = null;
                noPetakMula = null;
                noPetakAkhir = null;
                syerPetak = null;
                jumsyer = null;
            }
        } else {
            addSimpleError("Sila masukkan maklumat yang bertanda (*)");
            kodLot = "";
            noLot = null;
            kodLuas = "";
            noLuas = null;
            tarikhDaftar = null;
            noFailRujukan = null;
            noSijil = null;
            noBukuStrata = null;
            noBukuStrataAsal = null;
            noSyit = null;
            namaPerbadananPengurusan = null;
            alamat1 = null;
            alamat2 = null;
            alamat3 = null;
            alamat4 = null;
            poskod = null;
            kodnegeri = null;
            alamatSurat1 = null;
            alamatSurat2 = null;
            alamatSurat3 = null;
            alamatSurat4 = null;
            poskodSurat = null;
            negeriSurat = null;

            kodKegunaanBangunan = null;
            noFolio = null;
            noBangunan = null;
            noTingkat = null;
            noPetakMula = null;
            noPetakAkhir = null;
            syerPetak = null;
            jumsyer = null;
        }

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

//    public Resolution back() {
//        return new JSP("common/strata_conversion.jsp");
//    }
    public Resolution penyukatanBPM() {
        String kodDaerah = getDaerah();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (kodDaerah == null || kodDaerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", kodDaerah);
        }
        senaraiBPM = q.list();
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution simpan() throws ParseException {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        daerah = (String) getContext().getRequest().getParameter("daerah");
        kodLot = (String) getContext().getRequest().getParameter("kodLot");
        noLot = (String) getContext().getRequest().getParameter("noLot");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        tarikhDaftar = (String) getContext().getRequest().getParameter("tarikhDaftar");
        noFailRujukan = (String) getContext().getRequest().getParameter("noFailRujukan");
        noSijil = (String) getContext().getRequest().getParameter("noSijil");
        namaPerbadananPengurusan = (String) getContext().getRequest().getParameter("namaPerbadananPengurusan");
        alamat1 = (String) getContext().getRequest().getParameter("alamat1");
        alamat2 = (String) getContext().getRequest().getParameter("alamat2");
        alamat3 = (String) getContext().getRequest().getParameter("alamat3");
        alamat4 = (String) getContext().getRequest().getParameter("alamat4");
        poskod = (String) getContext().getRequest().getParameter("poskod");
        kodnegeri = (String) getContext().getRequest().getParameter("kodnegeri");
        alamatSurat1 = (String) getContext().getRequest().getParameter("alamatSurat1");
        alamatSurat2 = (String) getContext().getRequest().getParameter("alamatSurat2");
        alamatSurat3 = (String) getContext().getRequest().getParameter("alamatSurat3");
        alamatSurat4 = (String) getContext().getRequest().getParameter("alamatSurat4");
        poskodSurat = (String) getContext().getRequest().getParameter("poskodSurat");
        negeriSurat = (String) getContext().getRequest().getParameter("negeriSurat");
        noBukuStrata = (String) getContext().getRequest().getParameter("noBukuStrata");
        noSyit = (String) getContext().getRequest().getParameter("noSyit");
        jumsyer = (String) getContext().getRequest().getParameter("jumsyer");
        stsHakmilik = (String) getContext().getRequest().getParameter("stsHakmilik");

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        InfoAudit ia2 = new InfoAudit();
        ia2.setDiKemaskiniOleh(pengguna);
        ia2.setTarikhKemaskini(new java.util.Date());

        penyukatanBPM();

        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        KodBandarPekanMukim bpm = kodBandarPekanMukimDAO.findById(Integer.parseInt(bandarPekanMukim));
        String bpm1 = bpm.getbandarPekanMukim();
        String negeri2 = etanahConf.getProperty("kodNegeri");
        String noHakmilikBaru = String.format("%08d", Integer.parseInt(noHakmilik));
        String bandarPekanMukimBaru = String.format("%02d", Integer.parseInt(bpm1));
        String daerahBaru = String.format("%02d", Integer.parseInt(daerah));
        LOG.info("idhakmilik-->" + negeri2 + daerahBaru + bandarPekanMukimBaru + kodHakmilik + noHakmilikBaru);

        LOG.info(senaraiHakmilik);
        String idhakmilik = senaraiHakmilik.get(0).getIdHakmilik();
        String idhakmilikTemp = senaraiHakmilik.get(0).getIdHakmilik() + "0M100100001";
        if (senaraiHakmilikStrata.isEmpty()) {
            Hakmilik hm = strataPtService.findInfoByIdHakmilik(idhakmilikTemp);
            if (hm == null) {
                Hakmilik hakmilikbr = new Hakmilik();
                HakmilikPermohonan hpHTB = strataPtService.findIdHakmilikHTB(senaraiHakmilik.get(0).getIdHakmilik());
                if (hpHTB != null) {
                    PermohonanPihak mohonPihak = strataPtService.findPihakHTB(hpHTB.getPermohonan().getIdPermohonan());

                    if (mohonPihak != null) {
                        Pihak pihak = strataPtService.findByIdPihak(mohonPihak.getPihak().getIdPihak());

                        BadanPengurusan bdnUrus = strataPtService.findBdnbyIdPihak(pihak.getIdPihak());
                        if (bdnUrus == null) {
                            bdnUrus = new BadanPengurusan();
                            if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
                                bdnUrus.setCawangan(kodCawanganDAO.findById("00"));
                            } else {
                                bdnUrus.setCawangan(kodCawanganDAO.findById(daerahBaru));
                            }
                            bdnUrus.setInfoAudit(ia);
                            bdnUrus.setPihak(pihak);
                            strataPtService.simpanMaklumatBangunan(bdnUrus);

                        }

                        hakmilikbr.setBadanPengurusan(bdnUrus);
                    }
                } else if (senaraiHakmilik.get(0).getBadanPengurusan() == null) {
                    Pihak phk = new Pihak();
                    phk.setNama(namaPerbadananPengurusan);
                    phk.setAlamat1(alamat1);
                    phk.setAlamat2(alamat2);
                    phk.setAlamat3(alamat3);
                    phk.setAlamat4(alamat4);
                    phk.setPoskod(poskod);
                    KodNegeri kodNegeri1 = new KodNegeri();
                    if (!kodnegeri.isEmpty()) {
                        kodNegeri1.setKod(kodnegeri);
                        phk.setNegeri(kodNegeri1);
                    }
                    phk.setSuratAlamat1(alamatSurat1);
                    phk.setSuratAlamat2(alamatSurat2);
                    phk.setSuratAlamat3(alamatSurat3);
                    phk.setSuratAlamat4(alamatSurat4);
                    phk.setSuratPoskod(poskodSurat);
                    KodNegeri kodNegeri2 = new KodNegeri();
                    if (!negeriSurat.isEmpty()) {
                        kodNegeri2.setKod(negeriSurat);
                        phk.setSuratNegeri(kodNegeri2);
                    }
                    phk.setInfoAudit(ia2);
                    strataPtService.savePihak(phk);

                    BadanPengurusan bdnUrus = new BadanPengurusan();
                    if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
                        bdnUrus.setCawangan(kodCawanganDAO.findById("00"));
                    } else {
                        bdnUrus.setCawangan(kodCawanganDAO.findById(daerahBaru));
                    }
                    bdnUrus.setInfoAudit(ia);
                    bdnUrus.setPihak(phk);
                    strataPtService.simpanMaklumatBangunan(bdnUrus);

                    hakmilikbr.setBadanPengurusan(bdnUrus);
                }

                hakmilikbr.setIdHakmilik(idhakmilikTemp);
                if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
                    hakmilikbr.setCawangan(kodCawanganDAO.findById("00"));
                } else {
                    hakmilikbr.setCawangan(kodCawanganDAO.findById(daerahBaru));
                }
                hakmilikbr.setDaerah(kodDaerahDAO.findById(daerahBaru));
                hakmilikbr.setBandarPekanMukim(kodBandarPekanMukimDAO.findById(Integer.parseInt(bandarPekanMukim)));
                hakmilikbr.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
                hakmilikbr.setLot(kodLotDAO.findById(kodLot));
                hakmilikbr.setNoLot(noLot);
                hakmilikbr.setJumlahUnitSyer(Integer.parseInt(jumsyer));
                hakmilikbr.setNoBangunan("M1");
                hakmilikbr.setNoPetak("1");
                hakmilikbr.setNoTingkat("1");
                hakmilikbr.setNoHakmilik(noHakmilikBaru);
                hakmilikbr.setKategoriTanah(senaraiHakmilik.get(0).getKategoriTanah());
                if (senaraiHakmilik.get(0).getSeksyen() != null) {
                    hakmilikbr.setSeksyen(senaraiHakmilik.get(0).getSeksyen());
                }
                if (senaraiHakmilik.get(0).getLokasi() != null) {
                    hakmilikbr.setLokasi(senaraiHakmilik.get(0).getLokasi());
                }
                hakmilikbr.setIdHakmilikInduk(idhakmilik);
                hakmilikbr.setKumpulan(0);
                hakmilikbr.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("B"));
                hakmilikbr.setKegunaanTanah(senaraiHakmilik.get(0).getKegunaanTanah());
                hakmilikbr.setSyaratNyata(senaraiHakmilik.get(0).getSyaratNyata());
                hakmilikbr.setSekatanKepentingan(senaraiHakmilik.get(0).getSekatanKepentingan());
                if (!tarikhDaftar.isEmpty()) {
                    hakmilikbr.setTarikhDaftar(sdf.parse(tarikhDaftar));
                }
                if (noFailRujukan != null) {
                    hakmilikbr.setNoFail(noFailRujukan);
                }
                if (noSijil != null) {
                    hakmilikbr.setNoSkim(noSijil);
                }
                if (noBukuStrata != null) {
                    hakmilikbr.setNoBukuDaftarStrata(noBukuStrata);
                }
                if (noSyit != null) {
                    hakmilikbr.setNoPu(noSyit);
                }
                hakmilikbr.setKodStatusHakmilik(kodStatusHakmilikDAO.findById("T"));
                hakmilikbr.setPegangan(senaraiHakmilik.get(0).getPegangan());
                hakmilikbr.setTempohPegangan(senaraiHakmilik.get(0).getTempohPegangan());
                hakmilikbr.setTarikhLuput(senaraiHakmilik.get(0).getTarikhLuput());
//                hakmilikbr.setNoLitho(senaraiHakmilik.get(0).getNoLitho());
                hakmilikbr.setLuas(BigDecimal.ZERO);
                hakmilikbr.setKodUnitLuas(kodUOMDAO.findById("M"));
                hakmilikbr.setInfoAudit(ia);
//                hakmilikDAO.save(hakmilikbr);

                strataPtService.simpanhakmilik(hakmilikbr);
                LOG.info(hakmilikbr.getIdHakmilik());
                Hakmilik hk = strataPtService.findIdHakmilikequalIdInduk(idhakmilik);
                Hakmilik hk2 = strataPtService.findIdHakmilikTemp(idhakmilikTemp);
                LOG.info("hakmilik " + hk);
                LOG.info("hakmilik ada null -> " + hk2.getIdHakmilik());

                if (hk2 == null) {
                    LOG.info("AAH NULL DOO");
                }
            }
        } else {
            if (senaraiHakmilikStrata.get(0).getBadanPengurusan() != null) {
                BadanPengurusan bdnUrus = strataPtService.findBdnByIdBdn(senaraiHakmilikStrata.get(0).getBadanPengurusan().getIdBadan());
                Pihak phk = strataPtService.findByIdPihak(bdnUrus.getPihak().getIdPihak());
                if (phk != null) {
                    phk.setNama(namaPerbadananPengurusan);
                    phk.setAlamat1(alamat1);
                    phk.setAlamat2(alamat2);
                    phk.setAlamat3(alamat3);
                    phk.setAlamat4(alamat4);
                    phk.setPoskod(poskod);
                    KodNegeri kodNegeri1 = new KodNegeri();
                    if (!kodnegeri.isEmpty()) {
                        kodNegeri1.setKod(kodnegeri);
                        phk.setNegeri(kodNegeri1);
                    }
                    phk.setSuratAlamat1(alamatSurat1);
                    phk.setSuratAlamat2(alamatSurat2);
                    phk.setSuratAlamat3(alamatSurat3);
                    phk.setSuratAlamat4(alamatSurat4);
                    phk.setPoskod(poskodSurat);
                    KodNegeri kodNegeri2 = new KodNegeri();
                    if (!negeriSurat.isEmpty()) {
                        kodNegeri2.setKod(negeriSurat);
                        phk.setSuratNegeri(kodNegeri2);
                    }
                    phk.setInfoAudit(ia2);
                    strataPtService.savePihak(phk);
                }
            }

            for (Hakmilik hmS : senaraiHakmilikStrata) {

               if (!tarikhDaftar.isEmpty()) {
                   hmS.setTarikhDaftar(sdf.parse(tarikhDaftar));
                }
                if (noFailRujukan != null) {
                    hmS.setNoFail(noFailRujukan);
                }
                if (noSijil != null) {
                    hmS.setNoSkim(noSijil);
                }
                if (noBukuStrata != null) {
                    LOG.info(noBukuStrata);
                    hmS.setNoBukuDaftarStrata(noBukuStrata);
                    hmS.setNoPu(noSyit);
                    hmS.setJumlahUnitSyer(Integer.parseInt(jumsyer));
                }
                if (stsHakmilik != null) {
                    hmS.setKodStatusHakmilik(kodStatusHakmilikDAO.findById(stsHakmilik));
                }
                strataPtService.simpanhakmilik(hmS);
            }
        }
        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution petak() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        noPetakMula = (String) getContext().getRequest().getParameter("noPetakMula");
        noPetakAkhir = (String) getContext().getRequest().getParameter("noPetakAkhir");
        kodKegunaanBangunan = (String) getContext().getRequest().getParameter("kodKegunaanBangunan");
        noFolio = (String) getContext().getRequest().getParameter("noFolio");
        noBangunan = (String) getContext().getRequest().getParameter("noBangunan");
        noTingkat = (String) getContext().getRequest().getParameter("noTingkat");
        luas = (String) getContext().getRequest().getParameter("luas");
        syerPetak = (String) getContext().getRequest().getParameter("syerPetak");
        String menara = (String) getContext().getRequest().getParameter("menara");
        String mezanin = (String) getContext().getRequest().getParameter("mezanin");
        LOG.info("menara---" + menara + "mezanin--" + mezanin);
        LOG.info("noPetakMula---" + noPetakMula);

        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
//        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrataTemp(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (!noPetakMula.isEmpty()) {

            Integer petakMula = Integer.parseInt(noPetakMula);
            Integer petakAkhir = Integer.parseInt(noPetakAkhir);

            for (int i = petakMula; i <= petakAkhir; i++) {

                String idhakmilikInduk = senaraiHakmilik.get(0).getIdHakmilik();

                if (!senaraiHakmilik.isEmpty()) {
                    if (!senaraiHakmilikStrata.isEmpty()) {
                        Hakmilik hakmilikbr = new Hakmilik();

                        if (!noBangunan.isEmpty()) {
                            String noTingkatbaru = String.format("%03d", Integer.parseInt(noTingkat));
                            String noPetakbaru = String.format("%05d", i);
                            String petak = String.valueOf(i);
//            String noBangunanBaru = String.format("%03d", Integer.parseInt(noBangunan));
                            String noBangunanBaru = StringUtils.leftPad(noBangunan, 3, '0');
                            idhakmilikbaru = senaraiHakmilik.get(0).getIdHakmilik() + noBangunanBaru + noTingkatbaru + noPetakbaru;
                            hakmilikbr.setNoPetak(petak);

                        } else {
                            String petakLanded = "L" + i;
                            String noPetakbaru = StringUtils.leftPad(petakLanded, 5, '0');
                            idhakmilikbaru = senaraiHakmilik.get(0).getIdHakmilik() + noPetakbaru;
                            String petak = String.valueOf(i);
                            hakmilikbr.setNoPetak(petakLanded);
                            hakmilikbr.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("L"));
                        }
                        Hakmilik hm = strataPtService.findInfoByIdHakmilik(idhakmilikbaru);
                        if (hm == null) {

                            hakmilikbr.setIdHakmilik(idhakmilikbaru);
                            if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
                                hakmilikbr.setCawangan(kodCawanganDAO.findById("00"));
                            } else {
                                hakmilikbr.setCawangan(kodCawanganDAO.findById(senaraiHakmilik.get(0).getCawangan().getKod()));
                            }
                            hakmilikbr.setDaerah(kodDaerahDAO.findById(senaraiHakmilik.get(0).getDaerah().getKod()));
                            hakmilikbr.setBandarPekanMukim(kodBandarPekanMukimDAO.findById(Integer.parseInt(bandarPekanMukim)));
                            hakmilikbr.setKodHakmilik(kodHakmilikDAO.findById(kodHakmilik));
                            hakmilikbr.setLot(senaraiHakmilikStrata.get(0).getLot());
                            hakmilikbr.setNoLot(senaraiHakmilikStrata.get(0).getNoLot());
                            hakmilikbr.setKodKategoriBangunan(senaraiHakmilikStrata.get(0).getKodKategoriBangunan());
                            hakmilikbr.setKategoriTanah(senaraiHakmilikStrata.get(0).getKategoriTanah());
                            hakmilikbr.setNoHakmilik(senaraiHakmilikStrata.get(0).getNoHakmilik());
                            if (senaraiHakmilik.get(0).getSeksyen() != null) {
                                hakmilikbr.setSeksyen(senaraiHakmilikStrata.get(0).getSeksyen());
                            }
                            if (senaraiHakmilik.get(0).getLokasi() != null) {
                                hakmilikbr.setLokasi(senaraiHakmilikStrata.get(0).getLokasi());
                            }
                            hakmilikbr.setIdHakmilikInduk(idhakmilikInduk);
                            hakmilikbr.setKumpulan(0);
                            hakmilikbr.setSyaratNyata(senaraiHakmilikStrata.get(0).getSyaratNyata());
                            hakmilikbr.setSekatanKepentingan(senaraiHakmilikStrata.get(0).getSekatanKepentingan());
                            if (senaraiHakmilikStrata.get(0).getKegunaanTanah() != null) {
                                hakmilikbr.setKegunaanTanah(senaraiHakmilikStrata.get(0).getKegunaanTanah());
                            }
                            if (senaraiHakmilikStrata.get(0).getLotBumiputera() != null) {
                                hakmilikbr.setLotBumiputera(senaraiHakmilikStrata.get(0).getLotBumiputera());
                            }
                            if (senaraiHakmilikStrata.get(0).getMilikPersekutuan() != null) {
                                hakmilikbr.setMilikPersekutuan(senaraiHakmilikStrata.get(0).getMilikPersekutuan());
                            }
                            if (senaraiHakmilikStrata.get(0).getTarikhDaftar() != null) {
                                hakmilikbr.setTarikhDaftar(senaraiHakmilikStrata.get(0).getTarikhDaftar());
                            }
                            if (senaraiHakmilikStrata.get(0).getNoFail() != null) {
                                hakmilikbr.setNoFail(senaraiHakmilikStrata.get(0).getNoFail());
                            }
                            if (senaraiHakmilikStrata.get(0).getNoPu() != null) {
                                hakmilikbr.setNoPu(senaraiHakmilikStrata.get(0).getNoPu());
                            }
                            if (senaraiHakmilikStrata.get(0).getNoSkim() != null) {
                                hakmilikbr.setNoSkim(senaraiHakmilikStrata.get(0).getNoSkim());
                            }
                            if (senaraiHakmilikStrata.get(0).getNoBukuDaftarStrata() != null) {
                                hakmilikbr.setNoBukuDaftarStrata(senaraiHakmilikStrata.get(0).getNoBukuDaftarStrata());
                            }
                            hakmilikbr.setKodStatusHakmilik(kodStatusHakmilikDAO.findById("T"));
                            hakmilikbr.setPegangan(senaraiHakmilikStrata.get(0).getPegangan());
                            hakmilikbr.setTempohPegangan(senaraiHakmilikStrata.get(0).getTempohPegangan());
                            hakmilikbr.setTarikhLuput(senaraiHakmilikStrata.get(0).getTarikhLuput());
                            if (!luas.isEmpty()) {
                                hakmilikbr.setLuas(BigDecimal.valueOf(Long.parseLong(luas)));
                            }
                            hakmilikbr.setKodUnitLuas(kodUOMDAO.findById("M"));
                            hakmilikbr.setJumlahUnitSyer(senaraiHakmilikStrata.get(0).getJumlahUnitSyer());
                            hakmilikbr.setNoBangunan(noBangunan);
                            hakmilikbr.setNoTingkat(noTingkat);

                            hakmilikbr.setNoPelan(noFolio);
                            hakmilikbr.setCukai(BigDecimal.ZERO);
                            hakmilikbr.setCukaiSebenar(BigDecimal.ZERO);
                            hakmilikbr.setKodSumber("MI");
                            hakmilikbr.setNoVersiDhde(0);
                            hakmilikbr.setNoVersiDhke(0);
                            hakmilikbr.setInfoAudit(ia);
                            if (!menara.isEmpty()) {
                                hakmilikbr.setMenara(menara);
                            }
                            if (!menara.isEmpty()) {
                                hakmilikbr.setMezanin(mezanin);
                            }
                            if (!syerPetak.isEmpty()) {
                                hakmilikbr.setUnitSyer(BigDecimal.valueOf(Integer.parseInt(syerPetak)));
                            }
                            hakmilikbr.setBadanPengurusan(senaraiHakmilikStrata.get(0).getBadanPengurusan());
                            if (noBangunan.contains("M")) {
                                hakmilikbr.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("B"));
                            } else if (noBangunan.contains("P")) {
                                hakmilikbr.setKodKategoriBangunan(kodKategoriBangunanDAO.findById("P"));
                            }
                            hakmilikbr.setKodKegunaanBangunan(kodKegunaanBangunanDAO.findById(kodKegunaanBangunan));
                            strataPtService.simpanhakmilik(hakmilikbr);

                        } else {
                            hm.setNoPelan(noFolio);
                            hm.setCukai(BigDecimal.ZERO);
                            hm.setCukaiSebenar(BigDecimal.ZERO);
                            hm.setNoVersiDhde(0);
                            hm.setNoVersiDhke(0);
                            if (!luas.isEmpty()) {
                                hm.setLuas(BigDecimal.valueOf(Long.parseLong(luas)));
                            }
                            if (!syerPetak.isEmpty()) {
                                hm.setUnitSyer(BigDecimal.valueOf(Integer.parseInt(syerPetak)));
                            }
                            hm.setKodKegunaanBangunan(kodKegunaanBangunanDAO.findById(kodKegunaanBangunan));
                            if (!menara.isEmpty()) {
                                hm.setMenara(menara);
                            }
                            if (!mezanin.isEmpty()) {
                                hm.setMezanin(mezanin);
                            }
                            strataPtService.simpanhakmilik(hm);

                        }
                    }
                }
            }
        } else {
            HakmilikPetakAksesori hpa = new HakmilikPetakAksesori();
            hpa.setCawangan(senaraiHakmilik.get(0).getCawangan());
            hpa.setHakmilik(senaraiHakmilik.get(0));
            hpa.setLokasi(noTingkat);
            hpa.setInfoAudit(ia);
            hpa.setNoPelan(noFolio);
            strataPtService.simpanhakmilikPetakAks(hpa);
        }

        if (senaraiHakmilikStrata.isEmpty()) {
            addSimpleError("Sila klik 'Simpan' di tab Indeks Strata");
        } else {
            addSimpleMessage("Maklumat petak berjaya disimpan.");

            List<String> senaraiPelanHm = strataPtService.findNoPelanHmString(senaraiHakmilik.get(0).getIdHakmilik());
            List<String> senaraiPelanHmAksr = strataPtService.findNoPelanAksrString(senaraiHakmilik.get(0).getIdHakmilik());
            for (String pelanhm : senaraiPelanHm) {
                senaraiPelanString.add(pelanhm);
            }
            for (String pelanaksr : senaraiPelanHmAksr) {
                senaraiPelanString.add(pelanaksr);
            }
            Collections.sort(senaraiPelanString);
            for (int i = 0; i < senaraiPelanString.size(); i++) {
                viewPelan(senaraiPelanString.get(i).toString());

            }
        }

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        penyukatanBPM();
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution hapusSingle() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idHakmilikHapus = (String) getContext().getRequest().getParameter("idHakmilikHapus");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        LOG.info("idHakmilikHapus-->" + idHakmilikHapus);

        strataPtService.deleteHakmilikAksr(idHakmilikHapus);
        strataPtService.deleteHakmilikTemp(idHakmilikHapus);

        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        LOG.info("senaraiHakmilikStrataTemp----" + senaraiHakmilikStrataTemp);

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");

        penyukatanBPM();
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution editPetak() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilikEdit = (String) getContext().getRequest().getParameter("idHakmilik");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");

        hakmilikEdit = strataPtService.findInfoByIdHakmilik(idHakmilikEdit);

        LOG.info("idHakmilikEdit----" + idHakmilikEdit);
        LOG.info("hakmilikEdit----" + hakmilikEdit);
        senaraiPetakAksesori = strataPtService.findHakmilikPetakAksesori(idHakmilikEdit);

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");

        penyukatanBPM();
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        getContext().getRequest().setAttribute("editPetak", Boolean.TRUE);
//        addSimpleMessage("Maklumat berjaya dikemaskini.");
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion2.jsp").addParameter("popup", "true");
    }

    public Resolution hapusAks() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idHakmilikPetakAks = (String) getContext().getRequest().getParameter("idHakmilikPetakAks");
        String idAksesori = (String) getContext().getRequest().getParameter("idAksesori");
        idHakmilikInduk = (String) getContext().getRequest().getParameter("idHakmilikInduk");

        strataPtService.deleteHakmilikAksr(idHakmilikPetakAks, idAksesori);

        senaraiPetakAksesori2 = strataPtService.findHakmilikPetakAksesori(idHakmilikInduk);
        senaraiPelanTambahan = strataPtService.findHakmilikPelanTambah(idHakmilikInduk);

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");

        penyukatanBPM();
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new ForwardResolution("/WEB-INF/jsp/common/petakStrata.jsp").addParameter("popup", "true");
    }

    public Resolution petakAksesoriPopup() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        idHakmilikEdit = (String) getContext().getRequest().getParameter("idHakmilikEdit");

        hakmilikEdit = strataPtService.findInfoByIdHakmilik(idHakmilikEdit);
        senaraiPetakAksesori = strataPtService.findHakmilikPetakAksesori(idHakmilikEdit);
        LOG.info("senaraiPetakAksesori-->" + senaraiPetakAksesori);

        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion2.jsp").addParameter("popup", "true");
    }

    public Resolution petakAksesoriPopupView() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilikInduk = (String) getContext().getRequest().getParameter("idHakmilikInduk");

        senaraiPetakAksesori2 = strataPtService.findHakmilikPetakAksesori(idHakmilikInduk);
        senaraiPelanTambahan = strataPtService.findHakmilikPelanTambah(idHakmilikInduk);

        return new ForwardResolution("/WEB-INF/jsp/common/petakStrata.jsp").addParameter("popup", "true");
    }

    public Resolution daftarH() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");

        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        LOG.info("senaraiHakmilikStrata-->" + senaraiHakmilikStrata);

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        try {
//        senaraiPelan = strataPtService.findNoPelan(senaraiHakmilik.get(0).getIdHakmilik());
            String idhakmilik1 = senaraiHakmilik.get(0).getIdHakmilik();
            strataPtService.daftarStrata(idhakmilik1);

            InfoAudit ia = new InfoAudit();
            Date now = new Date();
            String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());

            for (Hakmilik hm : senaraiHakmilikStrata) {
                List<HakmilikPihakBerkepentingan> hp = strataPtService.findHakmilikPihakByIDHakmilik(hm.getIdHakmilik());
                List<HakmilikPihakBerkepentingan> hpPMInduk = strataPtService.findHPBeforeHTB(senaraiHakmilik.get(0).getIdHakmilik());

                if (hpPMInduk.isEmpty()) {
                    hpPMInduk = strataPtService.findHPAfterHTB(senaraiHakmilik.get(0).getIdHakmilik());
                }

                if (hpPMInduk.isEmpty()) {
                    addSimpleError("Rekod ketuanpunyaan tidak dijumpai. Sila hubungi pentadbir sistem!");
                    penyukatanBPM();
                    getContext().getRequest().setAttribute("details", Boolean.TRUE);
                    tx.rollback();
                    return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
                }

                if (hp.isEmpty()) {
                    if (hm.getKodStatusHakmilik().getKod().equals("T")) {
                        for (HakmilikPihakBerkepentingan hp1 : hpPMInduk) {
                            HakmilikPihakBerkepentingan hp2 = new HakmilikPihakBerkepentingan();
                            hp2.setCawangan(senaraiHakmilik.get(0).getCawangan());
                            hp2.setHakmilik(hm);
                            hp2.setPihak(hp1.getPihak());
                            hp2.setJenis(kodJenisPihakBerkepentinganDAO.findById("PM"));
                            hp2.setSyerPembilang(hp1.getSyerPembilang());
                            hp2.setSyerPenyebut(hp1.getSyerPenyebut());
                            hp2.setKepadaPihak(hp1.getKepadaPihak());
                            hp2.setAktif('Y');
                            hp2.setKaveatAktif(hp1.getKaveatAktif());
                            hp2.setNama(hp1.getNama());
                            hp2.setAlamat1(hp1.getAlamat1());
                            hp2.setAlamat2(hp1.getAlamat2());
                            hp2.setAlamat3(hp1.getAlamat3());
                            hp2.setAlamat4(hp1.getAlamat4());
                            hp2.setPoskod(hp1.getPoskod());
                            hp2.setNegeri(hp1.getNegeri());
                            hp2.setNoPengenalan(hp1.getNoPengenalan());
                            hp2.setJenisPengenalan(hp1.getJenisPengenalan());
                            hp2.setInfoAudit(ia);
                            hp2.setNoSalinan(hp1.getNoSalinan());
                            hp2.setPihakKongsiBersama(hp1.getPihakKongsiBersama());
                            hp2.setWargaNegara(hp1.getWargaNegara());
                            hp2.setPenubuhanSyarikat(hp1.getPenubuhanSyarikat());
                            hp2.setJumlahPembilang(hp1.getJumlahPembilang());
                            hp2.setJumlahPenyebut(hp1.getJumlahPenyebut());
                            hp2.setAlamatSurat(hp1.getAlamatSurat());
                            hp2.setNoPengenalanLama(hp1.getNoPengenalanLama());
                            strataPtService.save(hp2);
                        }
                    }
                }

                Akaun ak = strataPtService.findAkaunHM(hm.getIdHakmilik());
                if (ak == null) {
                    Akaun kewAkaun = new Akaun();
                    kewAkaun.setNoAkaun(hm.getIdHakmilik());
                    kewAkaun.setHakmilik(hm);
                    if (hp.size() > 0) {
                        kewAkaun.setPemegang(hp.get(0).getPihak());
                    }

//                    KodAkaunDAO kodAkaunDAO = new KodAkaunDAO();
//                    KodStatusAkaunDAO kodStatusAkaunDAO = new KodStatusAkaunDAO();
                    kewAkaun.setKodAkaun(kodAkaunDAO.findById("AC"));
                    kewAkaun.setCawangan(hm.getCawangan());
                    kewAkaun.setBaki(BigDecimal.ZERO);
                    kewAkaun.setAmaunMatang(BigDecimal.ZERO);
                    kewAkaun.setBilCetakPenyata(0);
                    kewAkaun.setStatus(kodStatusAkaunDAO.findById("A"));
                    kewAkaun.setInfoAudit(ia);
                    kewAkaun.setHantarBil('T');
                    akaunDAO.save(kewAkaun);
                }
            }

            senaraiPetakAksesori2 = strataPtService.findHakmilikPetakAksesori(idhakmilik1);

            tx.commit();
            addSimpleMessage("Hakmilik Strata berjaya didaftarkan.");
        } catch (Exception e) {
            tx.rollback();
            addSimpleError("Sila hubungi pentadbir sistem.");
        }
        senaraiHakmilikStrataDaftar = strataPtService.findHakmilibyNotInProv(senaraiHakmilikStrata.get(0).getIdHakmilikInduk());
        List<String> nobgnn = strataPtService.findNoBangunanProv(senaraiHakmilikStrata.get(0).getIdHakmilikInduk());

        for (int i = 0; i < nobgnn.size(); i++) {
            String nobgn = nobgnn.get(i);
            List<Hakmilik> hmbngn = strataPtService.findHmDaftar(senaraiHakmilikStrata.get(0).getIdHakmilikInduk(), nobgn);
            if (hmbngn != null) {
                senaraiHakmilikStrataProv.add(hmbngn.get(0));
            }
        }

        List<String> senaraiPelanHm = strataPtService.findNoPelanHmString(senaraiHakmilik.get(0).getIdHakmilik());
        List<String> senaraiPelanHmAksr = strataPtService.findNoPelanAksrString(senaraiHakmilik.get(0).getIdHakmilik());
        for (String pelanhm : senaraiPelanHm) {
            senaraiPelanString.add(pelanhm);
        }
        for (String pelanaksr : senaraiPelanHmAksr) {
            senaraiPelanString.add(pelanaksr);
        }
        Collections.sort(senaraiPelanString);
        for (int i = 0; i < senaraiPelanString.size(); i++) {
            viewPelan(senaraiPelanString.get(i).toString());

        }

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }

        penyukatanBPM();
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution simpanPetakAk() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String namaAks = (String) getContext().getRequest().getParameter("namaAks");
        String LokasiAks = (String) getContext().getRequest().getParameter("LokasiAks");
        String noPelanAks = (String) getContext().getRequest().getParameter("noPelanAks");
        String noPelanAks1 = (String) getContext().getRequest().getParameter("noPelanAks1");
        String pSangkutAks = (String) getContext().getRequest().getParameter("pSangkutAks");
        String kodKegunaanPetakAks = (String) getContext().getRequest().getParameter("kodKegunaanPetakAks");
        String luasAks = (String) getContext().getRequest().getParameter("luasAks");
        idHakmilikEdit = (String) getContext().getRequest().getParameter("idHakmilikEdit");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        LOG.info("noPelanAks1--" + noPelanAks1);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        InfoAudit ia1 = new InfoAudit();
        ia1.setDimasukOleh(pengguna);
        ia1.setTarikhMasuk(new java.util.Date());

        HakmilikPetakAksesori hakmilikPetakAksesori = new HakmilikPetakAksesori();
        if (kodHakmilik.equals("GRN") || kodHakmilik.equals("PN") || kodHakmilik.equals("HSD")) {
            hakmilikPetakAksesori.setCawangan(kodCawanganDAO.findById("00"));
        } else {
            daerah = (String) getContext().getRequest().getParameter("daerah");
            String daerahBaru = String.format("%02d", Integer.parseInt(daerah));
            hakmilikPetakAksesori.setCawangan(kodCawanganDAO.findById(daerahBaru));
        }
        hakmilikPetakAksesori.setHakmilik(hakmilikDAO.findById(idHakmilikEdit));
        hakmilikPetakAksesori.setNama(namaAks);
        hakmilikPetakAksesori.setLokasi(LokasiAks);
        if (!noPelanAks1.isEmpty()) {
            hakmilikPetakAksesori.setNoPelan(noPelanAks1);
        } else {
            hakmilikPetakAksesori.setNoPelan(noPelanAks);
        }
        hakmilikPetakAksesori.setPetakSangkut(pSangkutAks);
        hakmilikPetakAksesori.setKegunaaanPetak(kodKegunaanPetakAksesoriDAO.findById(kodKegunaanPetakAks));
        hakmilikPetakAksesori.setInfoAudit(ia);
        hakmilikPetakAksesori.setStatusHakmilik(kodStatusHakmilikDAO.findById("D"));
        if (noPelanAks1.isEmpty()) {
            hakmilikPetakAksesori.setLuas(BigDecimal.valueOf(Long.parseLong(luasAks)));
        }  
        strataPtService.simpanhakmilikPetakAks(hakmilikPetakAksesori);

        senaraiPetakAksesori = strataPtService.findHakmilikPetakAksesori(idHakmilikEdit);
        LOG.info("senaraiPetakAksesori-->" + senaraiPetakAksesori);

        hakmilikEdit = strataPtService.findInfoByIdHakmilik(idHakmilikEdit);

        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        LOG.info("senaraiHakmilikStrata-->" + senaraiHakmilikStrata);

        String idhakmilik1 = senaraiHakmilik.get(0).getIdHakmilik();
        senaraiPetakAksesori2 = strataPtService.findHakmilikPetakAksesori(idhakmilik1);

        addSimpleMessage("Maklumat Petak Aksesori Berjaya Disimpan");

        List<String> senaraiPelanHm = strataPtService.findNoPelanHmString(senaraiHakmilik.get(0).getIdHakmilik());
        List<String> senaraiPelanHmAksr = strataPtService.findNoPelanAksrString(senaraiHakmilik.get(0).getIdHakmilik());
        for (String pelanhm : senaraiPelanHm) {
            senaraiPelanString.add(pelanhm);
        }
        for (String pelanaksr : senaraiPelanHmAksr) {
            senaraiPelanString.add(pelanaksr);
        }
        Collections.sort(senaraiPelanString);
        for (int i = 0; i < senaraiPelanString.size(); i++) {
            viewPelan(senaraiPelanString.get(i).toString());

        }

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }

        rehydrate();
//        return new JSP("common/strata_conversion2.jsp");
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion2.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPetakEdit() throws Exception {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilikEdit = (String) getContext().getRequest().getParameter("idHakmilikEdit");
        String noTingkatEdit = (String) getContext().getRequest().getParameter("noTingkatEdit");
        String noPelanEdit = (String) getContext().getRequest().getParameter("noPelanEdit");
        String unitSyerEdit = (String) getContext().getRequest().getParameter("unitSyerEdit");
        String luasEdit = (String) getContext().getRequest().getParameter("luasEdit");
        String menara = (String) getContext().getRequest().getParameter("menaraEdit");
        String mezanin = (String) getContext().getRequest().getParameter("mezaninEdit");
        LOG.info("noTingkatEdit-->" + noTingkatEdit);
        LOG.info("noPelanEdit-->" + noPelanEdit);
        LOG.info("unitSyerEdit-->" + unitSyerEdit);
        LOG.info("luasEdit-->" + luasEdit);
        LOG.info("menara-->" + menara);
        LOG.info("mezanin-->" + mezanin);

        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        Hakmilik hakmilik2 = strataPtService.findInfoByIdHakmilik(idHakmilikEdit);
        if (hakmilik2 != null) {
            hakmilik2.setNoTingkat(noTingkatEdit);
            hakmilik2.setNoPelan(noPelanEdit);
            if (!menara.isEmpty()) {
                hakmilik2.setMenara(menara);
            }
            if (!mezanin.isEmpty()) {
                hakmilik2.setMezanin(mezanin);
            }
            if (unitSyerEdit != null) {
                hakmilik2.setUnitSyer(BigDecimal.valueOf(Integer.parseInt(unitSyerEdit)));
            }
            if (luasEdit != null) {
                hakmilik2.setLuas(BigDecimal.valueOf(Integer.parseInt(luasEdit)));
            }
            strataPtService.simpanhakmilik(hakmilik2);
            addSimpleMessage("Maklumat Petak Berjaya Dikemaskini");
        }

        hakmilikEdit = strataPtService.findInfoByIdHakmilik(idHakmilikEdit);
        LOG.info("hakmilikEdit-->" + hakmilikEdit);
        senaraiPetakAksesori = strataPtService.findHakmilikPetakAksesori(idHakmilikEdit);
        String bpm = String.valueOf(hakmilikEdit.getBandarPekanMukim().getKod());
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(hakmilikEdit.getDaerah().getKod(), bpm, hakmilikEdit.getKodHakmilik().getKod(), hakmilikEdit.getNoHakmilik());
        LOG.info("senaraiHakmilikStrataTemp-->" + senaraiHakmilikStrataTemp);

        selectedTabs = (String) getContext().getRequest().getParameter("selectedTabs");
        LOG.info("selectedTabs-->" + selectedTabs);
        penyukatanBPM();
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

    public Resolution reload() {
        LOG.info("------reload----");
        return new RedirectResolution(strataConversion.class, "search");
    }

    public Resolution simpanPelan() throws IOException, Exception {
        daerah = (String) getContext().getRequest().getParameter("daerah");
        bandarPekanMukim = (String) getContext().getRequest().getParameter("bandarPekanMukim");
        kodHakmilik = (String) getContext().getRequest().getParameter("kodHakmilik");
        noHakmilik = (String) getContext().getRequest().getParameter("noHakmilik");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        LOG.info(daerah + bandarPekanMukim + kodHakmilik + noHakmilik);

        senaraiHakmilik = strataPtService.findHakmilik2(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
//        senaraiPelan = strataPtService.findNoPelan(senaraiHakmilik.get(0).getIdHakmilik());
        String loc = conf.getPelanPath() + File.separator + "B4" + File.separator + "PA(B)";

        FileUtil fileUtil = new FileUtil(loc);
        fileUtil.saveFile("PA(B)" + file.getFileName().substring(0, file.getFileName().lastIndexOf('.')) + ".TIF", file.getInputStream());
        tx.commit();

        senaraiHakmilikStrata = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        senaraiHakmilikStrataTemp = strataPtService.findHakmilikStrata(daerah, bandarPekanMukim, kodHakmilik, noHakmilik);
        selectedTabs = "upload_pelan";

        List<String> senaraiPelanHm = strataPtService.findNoPelanHmString(senaraiHakmilik.get(0).getIdHakmilik());
        List<String> senaraiPelanHmAksr = strataPtService.findNoPelanAksrString(senaraiHakmilik.get(0).getIdHakmilik());
        for (String pelanhm : senaraiPelanHm) {
            senaraiPelanString.add(pelanhm);
        }
        for (String pelanaksr : senaraiPelanHmAksr) {
            senaraiPelanString.add(pelanaksr);
        }
        Collections.sort(senaraiPelanString);
        for (int i = 0; i < senaraiPelanString.size(); i++) {
            viewPelan(senaraiPelanString.get(i).toString());

        }

        if (senaraiHakmilikStrata.size() > 1) {
            if (senaraiHakmilikStrata.get(0).getNoVersiDhde() < 1) {
                versi = "0";
            } else {
                versi = "1";
            }
        } else {
            versi = "0";
        }

        penyukatanBPM();
        senaraiHakmilikStrataDaftar = strataPtService.findHakmilibyNotInProv(senaraiHakmilikStrata.get(0).getIdHakmilikInduk());
        getContext().getRequest().setAttribute("details", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/common/strata_conversion.jsp");
    }

//     public Resolution viewPelan() throws FileNotFoundException {
//
//        String nopelan = (String) getContext().getRequest().getParameter("nopelan");
//
//        String path = conf.getPelanPath() + "B4/PA(B)" + nopelan + ".TIF";
//
//        File viewFile = new File(path);
//        FileInputStream fis = null;
//        if (viewFile.exists()) {
//            fis = new FileInputStream(viewFile);
//        }
//
//        return new StreamingResolution("image/tiff", fis).setFilename(viewFile.getName());
//    }
    public void viewPelan(String nopelan) {
        LOG.info("MASUK VIEW");

        LOG.info("nopelan" + nopelan);

        String docPath = conf.getPelanPath() + "B4/PA(B)/PA(B)" + nopelan + ".TIF";

        LOG.info(docPath);
        InputStream fis = null;
        File f = new File(docPath);

        boolean flag = false;

//            fis = new FileInputStream(f);
        getContext().getResponse().setContentType("application/octet-stream");

        String filename = null;

        File viewFile = new File(docPath);
        FileInputStream fis2 = null;
        if (viewFile.exists()) {
//            fis2 = new FileInputStream(viewFile);
            senaraiPelanUpload.add(nopelan);
        }
        LOG.info("viewFile.getName()---" + viewFile.getName());
        LOG.info("senaraiPelanUpload---" + senaraiPelanUpload.size());
//        int sep = d.getNamaFizikal().lastIndexOf(File.separator);
//        if (sep >= 0) d.getNamaFizikal().substring(sep);
//        else filename = d.getNamaFizikal();//x

//        return new StreamingResolution(d.getFormat(), fis).setFilename(filename);
//        return new StreamingResolution("image/tiff", fis2).setFilename(viewFile.getName());
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        negeri = etanahConf.getProperty("kodNegeri");
        if ("04".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";

        }
        if ("05".equals(etanahConf.getProperty("kodNegeri"))) {
            kodNegeri = "negeri";

        }
        listBandarPekanMukim = regService.getSenaraiKodBPMByDaerah(daerah);
        senaraiPelanString.clear();

    }

    public String getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(String bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public int getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(int kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(String kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public String getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(String kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<KodBandarPekanMukim> getSenaraiBPM() {
        return senaraiBPM;
    }

    public void setSenaraiBPM(List<KodBandarPekanMukim> senaraiBPM) {
        this.senaraiBPM = senaraiBPM;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getPetakMula() {
        return petakMula;
    }

    public void setPetakMula(String petakMula) {
        this.petakMula = petakMula;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
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

    public String getAlamatSurat1() {
        return alamatSurat1;
    }

    public void setAlamatSurat1(String alamatSurat1) {
        this.alamatSurat1 = alamatSurat1;
    }

    public String getAlamatSurat2() {
        return alamatSurat2;
    }

    public void setAlamatSurat2(String alamatSurat2) {
        this.alamatSurat2 = alamatSurat2;
    }

    public String getAlamatSurat3() {
        return alamatSurat3;
    }

    public void setAlamatSurat3(String alamatSurat3) {
        this.alamatSurat3 = alamatSurat3;
    }

    public String getAlamatSurat4() {
        return alamatSurat4;
    }

    public void setAlamatSurat4(String alamatSurat4) {
        this.alamatSurat4 = alamatSurat4;
    }

    public String getKodLot() {
        return kodLot;
    }

    public void setKodLot(String kodLot) {
        this.kodLot = kodLot;
    }

    public String getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String kodLuas) {
        this.kodLuas = kodLuas;
    }

    public String getKodnegeri() {
        return kodnegeri;
    }

    public void setKodnegeri(String kodnegeri) {
        this.kodnegeri = kodnegeri;
    }

    public String getNamaPerbadananPengurusan() {
        return namaPerbadananPengurusan;
    }

    public void setNamaPerbadananPengurusan(String namaPerbadananPengurusan) {
        this.namaPerbadananPengurusan = namaPerbadananPengurusan;
    }

    public String getNegeriSurat() {
        return negeriSurat;
    }

    public void setNegeriSurat(String negeriSurat) {
        this.negeriSurat = negeriSurat;
    }

    public String getNoBukuStrata() {
        return noBukuStrata;
    }

    public void setNoBukuStrata(String noBukuStrata) {
        this.noBukuStrata = noBukuStrata;
    }

    public String getNoBukuStrataAsal() {
        return noBukuStrataAsal;
    }

    public void setNoBukuStrataAsal(String noBukuStrataAsal) {
        this.noBukuStrataAsal = noBukuStrataAsal;
    }

    public String getNoFailRujukan() {
        return noFailRujukan;
    }

    public void setNoFailRujukan(String noFailRujukan) {
        this.noFailRujukan = noFailRujukan;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoLuas() {
        return noLuas;
    }

    public void setNoLuas(String noLuas) {
        this.noLuas = noLuas;
    }

    public String getNoSijil() {
        return noSijil;
    }

    public void setNoSijil(String noSijil) {
        this.noSijil = noSijil;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getPoskodSurat() {
        return poskodSurat;
    }

    public void setPoskodSurat(String poskodSurat) {
        this.poskodSurat = poskodSurat;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public List<Hakmilik> getSenaraiHakmilikStrata() {
        return senaraiHakmilikStrata;
    }

    public void setSenaraiHakmilikStrata(List<Hakmilik> senaraiHakmilikStrata) {
        this.senaraiHakmilikStrata = senaraiHakmilikStrata;
    }

    public String getNoPetakAkhir() {
        return noPetakAkhir;
    }

    public void setNoPetakAkhir(String noPetakAkhir) {
        this.noPetakAkhir = noPetakAkhir;
    }

    public String getNoPetakMula() {
        return noPetakMula;
    }

    public void setNoPetakMula(String noPetakMula) {
        this.noPetakMula = noPetakMula;
    }

    public List<Hakmilik> getHm2() {
        return hm2;
    }

    public void setHm2(List<Hakmilik> hm2) {
        this.hm2 = hm2;
    }

    public String getNoBangunan() {
        return noBangunan;
    }

    public void setNoBangunan(String noBangunan) {
        this.noBangunan = noBangunan;
    }

    public String getNoPetak() {
        return noPetak;
    }

    public void setNoPetak(String noPetak) {
        this.noPetak = noPetak;
    }

    public String getNoTingkat() {
        return noTingkat;
    }

    public void setNoTingkat(String noTingkat) {
        this.noTingkat = noTingkat;
    }

    public String getIdhakmilikbaru() {
        return idhakmilikbaru;
    }

    public void setIdhakmilikbaru(String idhakmilikbaru) {
        this.idhakmilikbaru = idhakmilikbaru;
    }

    public String getNoBangunanbaru() {
        return noBangunanbaru;
    }

    public void setNoBangunanbaru(String noBangunanbaru) {
        this.noBangunanbaru = noBangunanbaru;
    }

    public String getNoFolio() {
        return noFolio;
    }

    public void setNoFolio(String noFolio) {
        this.noFolio = noFolio;
    }

    public String getSyerPetak() {
        return syerPetak;
    }

    public void setSyerPetak(String syerPetak) {
        this.syerPetak = syerPetak;
    }

    public String getKodKegunaanBangunan() {
        return kodKegunaanBangunan;
    }

    public void setKodKegunaanBangunan(String kodKegunaanBangunan) {
        this.kodKegunaanBangunan = kodKegunaanBangunan;
    }

    public List<HakmilikPetakAksesori> getSenaraiPetakAksesori() {
        return senaraiPetakAksesori;
    }

    public void setSenaraiPetakAksesori(List<HakmilikPetakAksesori> senaraiPetakAksesori) {
        this.senaraiPetakAksesori = senaraiPetakAksesori;
    }

    public List<HakmilikPetakAksesori> getSenaraiPetakAksesori2() {
        return senaraiPetakAksesori2;
    }

    public void setSenaraiPetakAksesori2(List<HakmilikPetakAksesori> senaraiPetakAksesori2) {
        this.senaraiPetakAksesori2 = senaraiPetakAksesori2;
    }

    public String getIdHakmilikNull() {
        return idHakmilikNull;
    }

    public void setIdHakmilikNull(String idHakmilikNull) {
        this.idHakmilikNull = idHakmilikNull;
    }

    public List<Hakmilik> getSenaraiHakmilikStrataTemp() {
        return senaraiHakmilikStrataTemp;
    }

    public void setSenaraiHakmilikStrataTemp(List<Hakmilik> senaraiHakmilikStrataTemp) {
        this.senaraiHakmilikStrataTemp = senaraiHakmilikStrataTemp;
    }

    public String getSelectedTabs() {
        return selectedTabs;
    }

    public void setSelectedTabs(String selectedTabs) {
        this.selectedTabs = selectedTabs;
    }

    public String getIdHakmilikInduk() {
        return idHakmilikInduk;
    }

    public void setIdHakmilikInduk(String idHakmilikInduk) {
        this.idHakmilikInduk = idHakmilikInduk;
    }

    public String getNoSyit() {
        return noSyit;
    }

    public void setNoSyit(String noSyit) {
        this.noSyit = noSyit;
    }

    public String getJumsyer() {
        return jumsyer;
    }

    public void setJumsyer(String jumsyer) {
        this.jumsyer = jumsyer;
    }

    public String getIdHakmilikEdit() {
        return idHakmilikEdit;
    }

    public void setIdHakmilikEdit(String idHakmilikEdit) {
        this.idHakmilikEdit = idHakmilikEdit;
    }

    public Hakmilik getHakmilikEdit() {
        return hakmilikEdit;
    }

    public void setHakmilikEdit(Hakmilik hakmilikEdit) {
        this.hakmilikEdit = hakmilikEdit;
    }

    public List<HakmilikPetakAksesori> getSenaraiPelanTambahan() {
        return senaraiPelanTambahan;
    }

    public void setSenaraiPelanTambahan(List<HakmilikPetakAksesori> senaraiPelanTambahan) {
        this.senaraiPelanTambahan = senaraiPelanTambahan;
    }

    public List<Hakmilik> getSenaraiHakmilikStrataDaftar() {
        return senaraiHakmilikStrataDaftar;
    }

    public void setSenaraiHakmilikStrataDaftar(List<Hakmilik> senaraiHakmilikStrataDaftar) {
        this.senaraiHakmilikStrataDaftar = senaraiHakmilikStrataDaftar;
    }

    public List<Hakmilik> getSenaraiHakmilikStrataProv() {
        return senaraiHakmilikStrataProv;
    }

    public void setSenaraiHakmilikStrataProv(List<Hakmilik> senaraiHakmilikStrataProv) {
        this.senaraiHakmilikStrataProv = senaraiHakmilikStrataProv;
    }

    public List<FileBean> getSenaraiPelan() {
        return senaraiPelan;
    }

    public void setSenaraiPelan(List<FileBean> senaraiPelan) {
        this.senaraiPelan = senaraiPelan;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

    public List<FileBean> getFiles() {
        return files;
    }

    public void setFiles(List<FileBean> files) {
        this.files = files;
    }

    public List<String> getSenaraiPelanUpload() {
        return senaraiPelanUpload;
    }

    public void setSenaraiPelanUpload(List<String> senaraiPelanUpload) {
        this.senaraiPelanUpload = senaraiPelanUpload;
    }

    public List<String> getSenaraiPelanString() {
        return senaraiPelanString;
    }

    public void setSenaraiPelanString(List<String> senaraiPelanString) {
        this.senaraiPelanString = senaraiPelanString;
    }

    public String getVersi() {
        return versi;
    }

    public void setVersi(String versi) {
        this.versi = versi;
    }

    public List<KodStatusHakmilik> getListKodStatusHakmilik() {
        return listKodStatusHakmilik;
    }

    public void setListKodStatusHakmilik(List<KodStatusHakmilik> listKodStatusHakmilik) {
        this.listKodStatusHakmilik = listKodStatusHakmilik;
    }

    public String getStsHakmilik() {
        return stsHakmilik;
    }

    public void setStsHakmilik(String stsHakmilik) {
        this.stsHakmilik = stsHakmilik;
    }
    
}
