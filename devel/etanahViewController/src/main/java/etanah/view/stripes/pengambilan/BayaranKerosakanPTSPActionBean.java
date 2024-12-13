/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
//import etanah.applet.util.FileUtil;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.Notis;
import etanah.service.common.PermohonanPihakService;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService1;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.FileBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Murali
 */
@UrlBinding("/pengambilan/BayaranKerosakanPTSP")
public class BayaranKerosakanPTSPActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    LelongService lelongService;
    private Hakmilik hakmilik;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PengambilanService1 pengambilanService1;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal jumTerimaPampasan;
    private BigDecimal jumPerlubayar;
    private PermohonanPihak pp;
//    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
//    private KodBank kodBank;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
     private List<HakmilikPihakBerkepentingan> pihakBerkepentinganList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<String> idDokumenList = new ArrayList<String>();
    private List<Notis> listNotisIMB = new ArrayList<Notis>();
    private LaporanPemulihanTanah lpt;
    private FasaPermohonan fasaPermohonan;
    private String adaKerosakanTanah;
    private String keteranganKerosakanTanah;
    private BigDecimal kosKerosakanTanah;
    private String adaKerosakanBangunan;
    private String keteranganKerosakanBangunan;
    private BigDecimal kosKerosakanBangunan;
    private String adaKerosakanPokok;
    private String keteranganKerosakanPokok;
    private BigDecimal kosKerosakanPokok;
    private String adaKerosakanInfra;
    private String keteranganKerosakanInfra;
    private BigDecimal kosKerosakanInfra;
    private String adaKerosakanLain;
    private String keteranganKerosakanLain;
    private BigDecimal kosKerosakanLain;
    private String adaKecacatanTanah;
    private String keteranganKecacatanTanah;
    private BigDecimal kosKecacatanTanah;
    private BigDecimal jumlah;
    private String kodCaraBayaran;
    private String kodBank;
    private KodBank kodBank2;
    private String nD;
    private boolean tunai = false;
    private boolean xtunai = false;
    private long idDokumen2;
    private String showHP = "false";
    private String isPemohonPihak = "false";
    private boolean showPP = false;
    private boolean show = true;
    private static final Logger LOG = Logger.getLogger(BayaranKerosakanPTSPActionBean.class);
    private BigDecimal amountsebenar;
    FileBean fileToBeUpload;
    private String dokumenKod;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/BayaranKerosakanPtSp.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        System.out.println("----------pihakDetails-----------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        long idPihak2 = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        showHP = "true";

        if (idPermohonan != null && idHakmilik != null) {
            hakmilikPermohonan = pengambilanService1.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            permohonanPihak = pengambilanService1.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak2);
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            lpt = pengambilanService.getLaporanPemulihanTanahByidMHidMP2(hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak(), idPermohonan);
            hakmilikPerbicaraanList = pengambilanService.getListHakmilikbicara(hakmilikPermohonan.getId());
            if (lpt != null) {
                if (lpt.getKosKecacatanTanah() != null) {
                    kosKecacatanTanah = lpt.getKosKecacatanTanah();
                } else {
                    kosKecacatanTanah = BigDecimal.ZERO;
                }
                if (lpt.getKosKerosakanBangunan() != null) {
                    kosKerosakanBangunan = lpt.getKosKerosakanBangunan();
                } else {
                    kosKerosakanBangunan = BigDecimal.ZERO;
                }
                if (lpt.getKosKerosakanInfra() != null) {
                    kosKerosakanInfra = lpt.getKosKerosakanInfra();
                } else {
                    kosKerosakanInfra = BigDecimal.ZERO;
                }
                if (lpt.getKosKerosakanLain() != null) {
                    kosKerosakanLain = lpt.getKosKerosakanLain();
                } else {
                    kosKerosakanLain = BigDecimal.ZERO;
                }
                if (lpt.getKosKerosakanPokok() != null) {
                    kosKerosakanPokok = lpt.getKosKerosakanPokok();
                } else {
                    kosKerosakanPokok = BigDecimal.ZERO;
                }
                if (lpt.getKosKerosakanTanah() != null) {
                    kosKerosakanTanah = lpt.getKosKerosakanTanah();
                } else {
                    kosKerosakanTanah = BigDecimal.ZERO;
                }
                amountsebenar = BigDecimal.ZERO;
                amountsebenar = kosKecacatanTanah.add(kosKerosakanBangunan).add(kosKerosakanInfra).add(kosKerosakanLain).add(kosKerosakanPokok).add(kosKerosakanTanah);
                System.out.println("----------amountsebenar-----------::" + amountsebenar);
            }


            int i = hakmilikPerbicaraanList.size();
            int count = i - 1;
            if (hakmilikPerbicaraanList.size() > 0) {
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraanList.get(count).getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran().getKod();
                        if (kodCaraBayaran.equals("T")) {
                            tunai = true;
                            xtunai = false;
                        } else {
                            tunai = false;
                        }
                        noDok = ambilPampasan.getNoDok();
                        tarikhDok = ambilPampasan.getTarikhDok();
                        if (noDok != null) {
                            if (ambilPampasan.getKodBank() != null) {
                                kodBank = ambilPampasan.getKodBank().getKod();
                            }
                        }

                        ambilPampasanList.add(ambilPampasan);
                    }
                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/BayaranKerosakanPtSp.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("----------rehydrate-----------::");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonan = permohonanDAO.findById(idPermohonan);
        ambilPampasanList = new ArrayList<AmbilPampasan>();
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
//                List<HakmilikPermohonan> j = permohonan.getSenaraiHakmilik();
//                hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
//                pihakBerkepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
//                int i=0;
//                for (HakmilikPermohonan s : j) {
//                    i++;
//                    char aktif=s.getHakmilik().getSenaraiPihakBerkepentingan().get(i).getAktif();
//                    LOG.debug("aktif >> "+aktif);
//                    LOG.debug("no hakmilik >> "+s.getHakmilik().getNoHakmilik());
//                    if (aktif=='Y') {
//                       // continue;
//                        pihakBerkepentinganList.add(s.getHakmilik().getSenaraiPihakBerkepentingan().get(i));
//                    }
//                    
//                }
//                LOG.debug("size >> "+hakmilikPermohonanList.size());
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakP : hakmilikPermohonanList) {
                    Hakmilik hakmilik = hakP.getHakmilik();
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                        long idPihak = hp.getPihak().getIdPihak();
                        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                        lpt = pengambilanService.getLaporanPemulihanTanahByidMHidMP2(hakP.getId(), hp.getIdHakmilikPihakBerkepentingan(), idPermohonan);
                        hakmilikPerbicaraanList = pengambilanService.getListHakmilikbicara(hakP.getId());
                        if (lpt != null) {
                            if (lpt.getKosKecacatanTanah() != null) {
                                kosKecacatanTanah = lpt.getKosKecacatanTanah();
                            } else {
                                kosKecacatanTanah = BigDecimal.ZERO;
                            }
                            if (lpt.getKosKerosakanBangunan() != null) {
                                kosKerosakanBangunan = lpt.getKosKerosakanBangunan();
                            } else {
                                kosKerosakanBangunan = BigDecimal.ZERO;
                            }
                            if (lpt.getKosKerosakanInfra() != null) {
                                kosKerosakanInfra = lpt.getKosKerosakanInfra();
                            } else {
                                kosKerosakanInfra = BigDecimal.ZERO;
                            }
                            if (lpt.getKosKerosakanLain() != null) {
                                kosKerosakanLain = lpt.getKosKerosakanLain();
                            } else {
                                kosKerosakanLain = BigDecimal.ZERO;
                            }
                            if (lpt.getKosKerosakanPokok() != null) {
                                kosKerosakanPokok = lpt.getKosKerosakanPokok();
                            } else {
                                kosKerosakanPokok = BigDecimal.ZERO;
                            }
                            if (lpt.getKosKerosakanTanah() != null) {
                                kosKerosakanTanah = lpt.getKosKerosakanTanah();
                            } else {
                                kosKerosakanTanah = BigDecimal.ZERO;
                            }
                            amountsebenar = BigDecimal.ZERO;
                            amountsebenar = kosKecacatanTanah.add(kosKerosakanBangunan).add(kosKerosakanInfra).add(kosKerosakanLain).add(kosKerosakanPokok).add(kosKerosakanTanah);
                            System.out.println("----------amountsebenar-----------::" + amountsebenar);
                        }
                        if (hakmilikPerbicaraan != null) {
                            perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                            if (perbicaraanKehadiran != null) {
                                List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                    jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                                    kodCaraBayaran = ambilPampasan.getKodCaraBayaran().getKod();
                                    if (kodCaraBayaran.equals("T")) {
                                        tunai = true;
                                        xtunai = false;
                                        noDok = "";
                                        tarikhDok = ambilPampasan.getTarikhDok();
                                    } else {
                                        tunai = false;
                                        noDok = ambilPampasan.getNoDok();
                                        tarikhDok = ambilPampasan.getTarikhDok();
                                        if (noDok != null) {
                                            kodBank = ambilPampasan.getKodBank().getKod();
                                        }
                                    }
                                    ambilPampasanList.add(ambilPampasan);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {
        System.out.println("----------simpan-----------::");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodcara = (String) getContext().getRequest().getParameter("kodCaraBayaran");
        long idPihak2 = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        permohonanPihakList = aduanService.findPihakByIdMohonListBasedOnIdhakmilikandIdpihak(idPermohonan);
        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak2);

        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        System.out.println("----------hakmilikPermohonan-----------::" + hakmilikPermohonan);

        if (hakmilikPermohonan != null) {
            System.out.println("----------hakmilikPermohonan not null-----------::");
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            System.out.println("----------hakmilikPerbicaraan-----------::" + hakmilikPerbicaraan);
            if (hakmilikPerbicaraan == null) {
                System.out.println("----------hakmilikPerbicaraan is null-----------::");
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            System.out.println("----------hakmilikPerbicaraan-----------::" + hakmilikPerbicaraan);
            if (hakmilikPerbicaraan != null) {
                System.out.println("----------hakmilikPerbicaraan not null-----------::");
                if (pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
                    System.out.println("----------perbicaraanKehadiran is null-----------::");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(cawangan);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                    pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                System.out.println("----------perbicaraanKehadiran-----------::" + perbicaraanKehadiran);
                if (perbicaraanKehadiran != null) {
                    ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                    if (ambilPampasan == null) {
                        ambilPampasan = new AmbilPampasan();
                        ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        ambilPampasan.setInfoAudit(info);
                    } else {
                        ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                        InfoAudit ia = ambilPampasan.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                        ambilPampasan.setInfoAudit(ia);
                    }
                    if (kodcara.equalsIgnoreCase("T")) {
                        System.out.println("Tunai");
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaranDAO.findById(kodCaraBayaran));
                        tunai = true;
                        xtunai = false;

                        nD = "";
//                            tarikhDok = null;
                        ambilPampasan.setNoDok(nD);
                        ambilPampasan.setKodBank(kodBank2);
                        ambilPampasan.setTarikhDok(tarikhDok);
                        pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                    } else {
                        tunai = false;
                        System.out.println("Bukan Tunai");
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaranDAO.findById(kodCaraBayaran));
                        ambilPampasan.setNoDok(noDok);
                        ambilPampasan.setKodBank(kodBankDAO.findById(kodBank));
                        ambilPampasan.setTarikhDok(tarikhDok);
                        pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                    }

                }
            }
            pihakDetails();
        }
//        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/negerisembilan/pendudukansementara/BayaranKerosakanPtSp.jsp").addParameter("tab", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/negerisembilan/pendudukansementara/scan_doc.jsp").addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        LOG.info("---------Upload window--------::");
        return new JSP("pengambilan/negerisembilan/pendudukansementara/upload_file.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDoc() {
        LOG.info("---------Uploading process--------::");
//        Logger.getLogger(UploadAction1.class).info("simpanMuatNaik::start");
        dokumenKod = getContext().getRequest().getParameter("dokumenKod");
        LOG.info("---------dokumenKod--------::" + dokumenKod);
        String folderId = getContext().getRequest().getParameter("folderId");
        LOG.info("---------folderId--------::" + folderId);
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("---------idPermohonan--------::" + idPermohonan);
        String dokumenPath = conf.getProperty("document.path");
        LOG.info("---------dokumenPath1--------::" + dokumenPath);
        String catatan = getContext().getRequest().getParameter("catatan");

//        if (fileToBeUpload != null) {
//            LOG.info("---------fileToBeUpload--not ull------::");
//            try {
//                System.out.println("----uploaded file content type----::" + fileToBeUpload.getContentType());
//                dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + folderId;
//                LOG.info("---------dokumenPath--------::" + dokumenPath);
//                FileUtil fileUtil = new FileUtil(dokumenPath);
//                LOG.info("---------Dok Saving--------::");
//                fileUtil.saveFile(dokumenId, fileToBeUpload.getInputStream());
//                String fizikalPath = folderId + File.separator + dokumenId;
//                LOG.info("---------fizikalPath--------::" + fizikalPath);
//                LOG.info("------calling---updatePathDokumen----start----::");
//                updatePathDokumen(fizikalPath, Long.parseLong(dokumenId), fileToBeUpload.getContentType(), catatan);
//            } catch (Exception ex) {
//                Logger.getLogger(BayaranKerosakanPTSPActionBean.class).error(ex);
//            }
//        }else{
//            LOG.info("---------fileToBeUpload--is ull------::");
//        }
        addSimpleMessage("Muat naik fail berjaya.");
        return new JSP("pengambilan/negerisembilan/pendudukansementara/upload_file.jsp").addParameter("popup", "true");
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format, String catatan) {
        LOG.info("------called---updatePathDokumen----start----::");
        Dokumen d = dokumenDAO.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        if (catatan != null && StringUtils.isNotBlank(catatan)) {
            d.setTajuk(catatan);
        }
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        d = dokumenDAO.saveOrUpdate(d);
        LOG.info("--------saved in document--------::");
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    public Resolution popupScan() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        idPihak = (String) getContext().getRequest().getParameter("idPihak");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
//        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        }

        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
            fname = String.valueOf(notisPB.getIdNotis());
        }
        LOG.info("idNotis : " + fname);
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Notis notis = notisDAO.findById(Long.parseLong(fname));
                if (notis.getBuktiPenerimaan() != null) {
                    doc = notis.getBuktiPenerimaan();
                    ia = notis.getBuktiPenerimaan().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen2 = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDokumen2);
                // update at DasarTuntutanNotis
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
                }

            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/scan_doc.jsp").addParameter("popup", "true");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public BigDecimal getJumTerimaPampasan() {
        return jumTerimaPampasan;
    }

    public void setJumTerimaPampasan(BigDecimal jumTerimaPampasan) {
        this.jumTerimaPampasan = jumTerimaPampasan;
    }

//    public KodBank getKodBank() {
//        return kodBank;
//    }
//
//    public void setKodBank(KodBank kodBank) {
//        this.kodBank = kodBank;
//    }
//    public KodCaraBayaran getKodCaraBayaran() {
//        return kodCaraBayaran;
//    }
//
//    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
//        this.kodCaraBayaran = kodCaraBayaran;
//    }
    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Date getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(Date tarikhDok) {
        this.tarikhDok = tarikhDok;
    }

    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
    }

    public List<AmbilPampasan> getSenaraiAmbilPampasan() {
        return senaraiAmbilPampasan;
    }

    public void setSenaraiAmbilPampasan(List<AmbilPampasan> senaraiAmbilPampasan) {
        this.senaraiAmbilPampasan = senaraiAmbilPampasan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public LaporanPemulihanTanah getLpt() {
        return lpt;
    }

    public void setLpt(LaporanPemulihanTanah lpt) {
        this.lpt = lpt;
    }

    public String getAdaKecacatanTanah() {
        return adaKecacatanTanah;
    }

    public void setAdaKecacatanTanah(String adaKecacatanTanah) {
        this.adaKecacatanTanah = adaKecacatanTanah;
    }

    public String getAdaKerosakanBangunan() {
        return adaKerosakanBangunan;
    }

    public void setAdaKerosakanBangunan(String adaKerosakanBangunan) {
        this.adaKerosakanBangunan = adaKerosakanBangunan;
    }

    public String getAdaKerosakanInfra() {
        return adaKerosakanInfra;
    }

    public void setAdaKerosakanInfra(String adaKerosakanInfra) {
        this.adaKerosakanInfra = adaKerosakanInfra;
    }

    public String getAdaKerosakanLain() {
        return adaKerosakanLain;
    }

    public void setAdaKerosakanLain(String adaKerosakanLain) {
        this.adaKerosakanLain = adaKerosakanLain;
    }

    public String getAdaKerosakanPokok() {
        return adaKerosakanPokok;
    }

    public void setAdaKerosakanPokok(String adaKerosakanPokok) {
        this.adaKerosakanPokok = adaKerosakanPokok;
    }

    public String getAdaKerosakanTanah() {
        return adaKerosakanTanah;
    }

    public void setAdaKerosakanTanah(String adaKerosakanTanah) {
        this.adaKerosakanTanah = adaKerosakanTanah;
    }

    public String getKeteranganKecacatanTanah() {
        return keteranganKecacatanTanah;
    }

    public void setKeteranganKecacatanTanah(String keteranganKecacatanTanah) {
        this.keteranganKecacatanTanah = keteranganKecacatanTanah;
    }

    public String getKeteranganKerosakanBangunan() {
        return keteranganKerosakanBangunan;
    }

    public void setKeteranganKerosakanBangunan(String keteranganKerosakanBangunan) {
        this.keteranganKerosakanBangunan = keteranganKerosakanBangunan;
    }

    public String getKeteranganKerosakanInfra() {
        return keteranganKerosakanInfra;
    }

    public void setKeteranganKerosakanInfra(String keteranganKerosakanInfra) {
        this.keteranganKerosakanInfra = keteranganKerosakanInfra;
    }

    public String getKeteranganKerosakanLain() {
        return keteranganKerosakanLain;
    }

    public void setKeteranganKerosakanLain(String keteranganKerosakanLain) {
        this.keteranganKerosakanLain = keteranganKerosakanLain;
    }

    public String getKeteranganKerosakanPokok() {
        return keteranganKerosakanPokok;
    }

    public void setKeteranganKerosakanPokok(String keteranganKerosakanPokok) {
        this.keteranganKerosakanPokok = keteranganKerosakanPokok;
    }

    public String getKeteranganKerosakanTanah() {
        return keteranganKerosakanTanah;
    }

    public void setKeteranganKerosakanTanah(String keteranganKerosakanTanah) {
        this.keteranganKerosakanTanah = keteranganKerosakanTanah;
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

    public BigDecimal getJumPerlubayar() {
        return jumPerlubayar;
    }

    public void setJumPerlubayar(BigDecimal jumPerlubayar) {
        this.jumPerlubayar = jumPerlubayar;
    }

    public PermohonanPihak getPp() {
        return pp;
    }

    public void setPp(PermohonanPihak pp) {
        this.pp = pp;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(String kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public boolean isTunai() {
        return tunai;
    }

    public void setTunai(boolean tunai) {
        this.tunai = tunai;
    }

    public boolean isXtunai() {
        return xtunai;
    }

    public void setXtunai(boolean xtunai) {
        this.xtunai = xtunai;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public long getIdDokumen2() {
        return idDokumen2;
    }

    public void setIdDokumen2(long idDokumen2) {
        this.idDokumen2 = idDokumen2;
    }

    public List<HakmilikPerbicaraan> getHakmilikPerbicaraanList() {
        return hakmilikPerbicaraanList;
    }

    public void setHakmilikPerbicaraanList(List<HakmilikPerbicaraan> hakmilikPerbicaraanList) {
        this.hakmilikPerbicaraanList = hakmilikPerbicaraanList;
    }

    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public List<Notis> getListNotisIMB() {
        return listNotisIMB;
    }

    public void setListNotisIMB(List<Notis> listNotisIMB) {
        this.listNotisIMB = listNotisIMB;
    }

    public String getShowHP() {
        return showHP;
    }

    public void setShowHP(String showHP) {
        this.showHP = showHP;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public KodBank getKodBank2() {
        return kodBank2;
    }

    public void setKodBank2(KodBank kodBank2) {
        this.kodBank2 = kodBank2;
    }

    public boolean isShowPP() {
        return showPP;
    }

    public void setShowPP(boolean showPP) {
        this.showPP = showPP;
    }

    public String getIsPemohonPihak() {
        return isPemohonPihak;
    }

    public void setIsPemohonPihak(String isPemohonPihak) {
        this.isPemohonPihak = isPemohonPihak;
    }

    public BigDecimal getAmountsebenar() {
        return amountsebenar;
    }

    public void setAmountsebenar(BigDecimal amountsebenar) {
        this.amountsebenar = amountsebenar;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getDokumenKod() {
        return dokumenKod;
    }

    public void setDokumenKod(String dokumenKod) {
        this.dokumenKod = dokumenKod;
    }

    public List<HakmilikPihakBerkepentingan> getPihakBerkepentinganList() {
        return pihakBerkepentinganList;
    }

    public void setPihakBerkepentinganList(List<HakmilikPihakBerkepentingan> pihakBerkepentinganList) {
        this.pihakBerkepentinganList = pihakBerkepentinganList;
    }
}
