/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCaraPenghantaranDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Notis;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.PenghantarNotis;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodAgensi;
import etanah.model.Pemohon;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.PelupusanService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.LelongService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.File;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/penerimaan_notis_borang_ns831a")
public class PenerimaanNotisBorang831ANSActionBean extends AbleActionBean {

    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodStatusTerimaDAO KodStatusTerimaDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    KodCaraPenghantaranDAO kodCaraPenghantaranDAO;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    etanah.Configuration conf;
    private Hakmilik hakmilik;
    private Notis notisMahkamahRPD;
    List<PermohonanMahkamah> senaraiPM = new ArrayList<PermohonanMahkamah>();
    private List<Notis> listNotisASB = new ArrayList<Notis>();
    private List<Notis> listNotisASBG = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASBG = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonJPPH = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonSIIE = new ArrayList<Notis>();
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
    private String kodNegeri;

    public List<Notis> getListNotisPemohonASBG() {
        return listNotisPemohonASBG;
    }

    public void setListNotisPemohonASBG(List<Notis> listNotisPemohonASBG) {
        this.listNotisPemohonASBG = listNotisPemohonASBG;
    }
    private List<Notis> listNotisPB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonPB = new ArrayList<Notis>();
    private List<Notis> listNotisNBQ = new ArrayList<Notis>();
    private List<Notis> listNotisNPJ = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonNBQ = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonNPJ = new ArrayList<Notis>();
    private List<Notis> listNotisMahkamahRPD = new ArrayList<Notis>();
    private List<Integer> namaPengahantar = new ArrayList<Integer>();
    private List<String> kodStatusTerima = new ArrayList<String>();
    private List<String> kodPenghantaran = new ArrayList<String>();
    private List<String> catatanPenerimaan = new ArrayList<String>();
    private List<String> tarikhHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> idDokumenList = new ArrayList<String>();
    private List<Integer> namaPengahantarP = new ArrayList<Integer>();
    private List<String> kodStatusTerimaP = new ArrayList<String>();
    private List<String> kodPenghantaranP = new ArrayList<String>();
    private List<String> catatanPenerimaanP = new ArrayList<String>();
    private List<String> tarikhHantarP = new ArrayList<String>();
    private List<String> tarikhTerimaP = new ArrayList<String>();
    private List<String> idDokumenListP = new ArrayList<String>();
    private Integer namaPengahantarM = new Integer(0);
    private String kodStatusTerimaM;
    private String kodPenghantaranM;
    private String catatanPenerimaanM;
    private String tarikhHantarM;
    private String tarikhTerimaM;
    private String idDokumenM;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Notis notis;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanPihak permohonanPihak;
    private PermohonanMahkamah permohonanMahkamah;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> pPihakList;

    public List<PermohonanPihak> getpPihakList() {
        return pPihakList;
    }

    public void setpPihakList(List<PermohonanPihak> pPihakList) {
        this.pPihakList = pPihakList;
    }
    private List<KandunganFolder> listKandunganFolder;
    private List<Dokumen> listDokumen;
    private String idNotis;
    FileBean fileToBeUploaded;
    private long idDokumen2;
    private PenghantarNotis penghantarNotis;
    private boolean show = true;
    private boolean showJ = false;
    private boolean showPP = false;
    private boolean showMM = false;
    private boolean showJpph = false;
    private String showHP = "false";
    private String isPemohonPihak = "false";
    private String isMahkamah = "false";
    private static final Logger LOG = Logger.getLogger(PenerimaanNotisBorang831ANSActionBean.class);
    private List<PermohonanPihakTidakBerkepentingan> senaraipbt;
    private PermohonanPihakTidakBerkepentingan pbt;
    private List<Notis> listNotisPBT = new ArrayList<Notis>();
    private List<Notis> notisNPJ;
    private List<KodAgensi> kodAgensis = new ArrayList<KodAgensi>();
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;

    @DefaultHandler
    public Resolution showFormPP() {
//        showHP = "true";
        System.out.println("1");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            System.out.println("no permohonan : " + permohonan.getIdPermohonan());
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            int count = 0;
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                count += hp.getHakmilik().getSenaraiPihakBerkepentingan().size();
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "48RekodBuktiSampai");
            if (fasaPermohonan == null) {
                LOG.info("2");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43RekodBuktiSampai");
                if (fasaPermohonan == null) {
                    fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "49_4RekodBuktiSampai2");
                }
                if (fasaPermohonan == null) {
                    fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43_4RekodBuktiSampai");
                }
            }
            if (fasaPermohonan == null) {
                LOG.info("3");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai2");
            }
            if (fasaPermohonan == null) {
                LOG.info("4");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai");
            }
            if (fasaPermohonan == null) {
                LOG.info("5");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodSampaiTampal");
            }
            if (fasaPermohonan == null) {
                LOG.info("6");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "134RekodBuktiSampaiMemo5");
            }
//            wazer

            // fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
            if (fasaPermohonan == null) {
                LOG.info("7");
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
            }
            //    System.out.println("Fasa Permhonan last tgk : " + fasaPermohonan.getIdAliran());
            listNotisASB = new ArrayList<Notis>();
            listNotisNBQ = new ArrayList<Notis>();
            listNotisPB = new ArrayList<Notis>();
            listNotisPemohonJPPH = new ArrayList<Notis>();
            listNotisPemohonSIIE = new ArrayList<Notis>();

            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                    showHP = "true";
                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBG");
                                Notis notisASBH = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBH");
                                if (notisASBH != null) {
                                    listNotisASB.add(notisASBH);
                                }
                                if (notisASB != null) {
                                    listNotisASBG.add(notisASB);
                                }
                            }
                        }
                    }
                    if (listNotisASB.size() != count && listNotisASBG.size() != count) {
                        LOG.info("48RekodBuktiSampai : Belum ade lg...");
                        simpanNotisASB();
                    }
//                    pemohonNotis();

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                    FasaPermohonan mf = new FasaPermohonan();
                    mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan");
                    if (mf == null) {
                        mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "29SediaBrgI");
                    }
                    if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                        LOG.info("43RekodBuktiSampai : Ada Kedesakan...");
                        showPP = true;
                        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                            LOG.info("Loop HakmilikPermohonan...");
                            for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                                LOG.info("Loop hakmilikPihak...");
//                                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                                if (notisPB != null) {
//                                    listNotisPB.add(notisPB);
//                                }
//                                if (notisNBQ != null) {
//                                    listNotisNBQ.add(notisNBQ);
//                                }

                                pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                                for (PermohonanPihak p : pPihakList) {
                                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBI");
                                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");
                                    if (notisPB != null) {
                                        listNotisPB.add(notisPB);
                                    }
                                    if (notisNBQ != null) {
                                        listNotisNBQ.add(notisNBQ);
                                    }
                                }
                            }
                        }
                        if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                            LOG.info("Belum ade lg...");
                            simpanNotisK();
                        }
                        pemohonNotis();
                    } else {
                        showHP = "true";
                        showPP = true;
                        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                            for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
////                                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                                if (notisPB != null) {
//                                    listNotisPB.add(notisPB);
//                                }

                                pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                                for (PermohonanPihak p : pPihakList) {
                                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");

                                    if (notisPB != null) {
                                        listNotisPB.add(notisPB);
                                    }
                                }
                            }
                        }
                        if (listNotisPB.size() != count) {
                            LOG.info("Belum ade lg...");
                            simpanNotisK();
                        }
                        pemohonNotis();

                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    showHP = "true";
                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBTE");
//                            if (notisPB != null) {
//                                listNotisPB.add(notisPB);
//                            }

                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBTE");

                                if (notisPB != null) {
                                    listNotisPB.add(notisPB);
                                }
                            }
                        }
                    }
                    if (listNotisPB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotis();
                    }
                    pemohonNotis();

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
//                    showHP = "true";
                    showPP = true;
                    idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
                    if (idPermohonan != null) {
                        permohonan = permohonanDAO.findById(idPermohonan);
                    }
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
//                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
//                            if (notisPB != null) {
//                                listNotisPB.add(notisPB);
//                            }
//                            if (notisNBQ != null) {
//                                listNotisNBQ.add(notisNBQ);
//                            }

                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBF");
                                if (notisPB != null) {
                                    listNotisPB.add(notisPB);
                                }
                                if (notisNBQ != null) {
                                    listNotisNBQ.add(notisNBQ);
                                }
                            }
                        }
                    }
                    if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
//                        simpanNotis();
                        simpanNotisASB();
                    }
                    pemohonNotis();

                } //                wazer
                else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                    showHP = "true";
                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
//                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
//                            if (notisPB != null) {
//                                listNotisPB.add(notisPB);
//                            }
//                            if (notisNBQ != null) {
//                                listNotisNBQ.add(notisNBQ);
//                            }
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBF");
                                if (notisPB != null) {
                                    listNotisPB.add(notisPB);
                                }
                                if (notisNBQ != null) {
                                    listNotisNBQ.add(notisNBQ);
                                }
                            }
                        }
                    }
                    if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotis();
                    }
                    pemohonNotis();

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                    showHP = "true";
                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            LOG.info("hakmilikPihak.getJenis().getKod() : " + hakmilikPihak.getJenis().getKod());
//comment temp                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihakKodPB(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");

                            if (notisNBQ != null) {
                                listNotisNBQ.add(notisNBQ);
                            }
                        }
                    }
                    if (listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotis5();
                    }
                    pemohonNotis();

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    showJpph = true;
//                    kodAgensis = pengambilanService.getSenaraikodAgensi();
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                notisNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
                            }
                        }
                    }
                    if (listNotisASBG.size() != count) {
                        LOG.info("mane kau masuk ni B05RekodBuktiSampai...1");
                        simpanNotisASB();
                    }
//                    pemohonNotis();
                }
            }
        }
        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    //Notis for only Pemohon
    public Resolution showFormPemohon() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");

            if (fasaPermohonan != null) {
                showPP = true;
                pemohonNotis();
            }
        }
        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    //Notis for only Mahkamah
    public Resolution showFormMahkamah() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19SediaSuratAfidavit");

            if (fasaPermohonan != null) {
                senaraiPM = new ArrayList<PermohonanMahkamah>();
                senaraiPM = notisPenerimaanService.getPermohonanMahkamahListByidMohon(idPermohonan);
                if (senaraiPM != null && senaraiPM.size() > 0) {
                    permohonanMahkamah = senaraiPM.get(0);
                    notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");
                    if (notisMahkamahRPD == null) {
                        Dokumen dokumenRPDM = notisPenerimaanService.getDokumenBykod(idPermohonan, "RPDM");
                        if (dokumenRPDM != null) {
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(pengguna);
                            info.setTarikhMasuk(new java.util.Date());
                            if (notisMahkamahRPD == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("RPD"));
                                notis1.setDokumenNotis(dokumenRPDM);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                        notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");
                    }
                    if (notisMahkamahRPD != null) {
                        if (notisMahkamahRPD.getPenghantarNotis() != null) {
                            namaPengahantarM = notisMahkamahRPD.getPenghantarNotis().getIdPenghantarNotis();
                        }
                        if (notisMahkamahRPD.getKodStatusTerima() != null) {
                            kodStatusTerimaM = notisMahkamahRPD.getKodStatusTerima().getKod();
                        }
                        if (notisMahkamahRPD.getKodCaraPenghantaran() != null) {
                            kodPenghantaranM = notisMahkamahRPD.getKodCaraPenghantaran().getKod();
                        }
                        catatanPenerimaanM = notisMahkamahRPD.getCatatanPenerimaan();
                        if (notisMahkamahRPD.getTarikhHantar() != null) {
                            tarikhHantarM = sdf.format(notisMahkamahRPD.getTarikhHantar());
                        }
                        if (notisMahkamahRPD.getTarikhTerima() != null) {
                            tarikhTerimaM = sdf.format(notisMahkamahRPD.getTarikhTerima());
                        }
                        if (notisMahkamahRPD.getBuktiPenerimaan() == null) {
                            idDokumenM = "";
                        } else {
                            idDokumenM = String.valueOf(notisMahkamahRPD.getBuktiPenerimaan().getIdDokumen());
                        }

                    }
                }
            }
        }
        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//        }
        if (idPermohonan != null) {
            FasaPermohonanService fasaPermohonanService = null;
            Permohonan p = permohonanDAO.findById(idPermohonan);
            // Permohonan HAkmilik Kedesakan
            FasaPermohonan fasaPermohonan = null;
            try {
                // bila split kedesakan
                fasaPermohonan = fasaPermohonanService.getKodKedesakan(idPermohonan, "DE");
                // listkan ade kedesakan
                hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
            } catch (Exception h) {

                try {
                    /// semua desak
                    hakmilikPermohonanList = pengambilanService.findbyIdHakmilikAdaKedesakan(idPermohonan);
                    if (hakmilikPermohonanList.size() == 0) {
                        /// permohonan hakmilik tiada kedesakan
                        hakmilikPermohonanList = pengambilanService.findbyIdHakmilikTiadaKedesakan(idPermohonan);
                    }
                } catch (Exception x) {
                    System.out.println("Error fasa Permohonan " + h);
                }
            }
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    LOG.info("fasaPermohonan == B05RekodBuktiSampai");
                    showJpph = true;
//                    kodAgensis = pengambilanService.getSenaraikodAgensi();
                    Notis notisNJT = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "NJT");
                    notisNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");

                    if (notisNJT != null) {
                        if (notisNJT.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisNJT.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisNJT.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisNJT.getKodStatusTerima().getKod());
                        }
                        if (notisNJT.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisNJT.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisNJT.getCatatanPenerimaan());
                        if (notisNJT.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisNJT.getTarikhHantar()));
                        }
                        if (notisNJT.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisNJT.getTarikhTerima()));
                        }
                    }
                }
            }
        }
    }

    public Resolution hakmilikDetails() {
        showHP = "true";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("id hakmilik >> " + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        try {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan == null) {

                HakmilikPerbicaraan hakmilikPerbicaraanw = new HakmilikPerbicaraan();
                hakmilikPerbicaraanw.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraanw.setCawangan(permohonan.getCawangan());
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanw.setInfoAudit(info);
                notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanw);

                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            }
            senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
        } catch (Exception j) {
        }
        try {
            if (senaraiPermohonanPihak.size() == 0) {
                System.out.println("snaraiPermohnanPihak size >> " + senaraiPermohonanPihak.size());
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihakBicara(idHakmilik, idPermohonan);
                for (PermohonanPihak m : senaraiPermohonanPihak) {
                    InfoAudit ia = new InfoAudit();
                    Date now = new Date();
                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());
                    KodCawangan cawangan = new KodCawangan();
                    cawangan = pengguna.getKodCawangan();
                    PerbicaraanKehadiran perbicaraan = new PerbicaraanKehadiran();
                    perbicaraan.setPihak(m);
                    perbicaraan.setCawangan(cawangan);
                    perbicaraan.setInfoAudit(ia);
                    perbicaraan.setPerbicaraan(hakmilikPerbicaraan);
//                perbicaraan.set
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraan);
                    // perbicaraan.setPenilaiTarikh(now);
                }
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
            }
        } catch (Exception j) {
        }
        System.out.println("hakmilik details, saiz senaraipermohonan Pihak >> " + senaraiPermohonanPihak.size());
        int count = hakmilik.getSenaraiPihakBerkepentingan().size();
        System.out.println("count");
        permohonan = permohonanDAO.findById(idPermohonan);
        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "48RekodBuktiSampai");
        if (fasaPermohonan == null) {
            System.out.println("43");

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43RekodBuktiSampai");
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "49_4RekodBuktiSampai2");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43_4RekodBuktiSampai");
            }
        }
        if (fasaPermohonan == null) {
            System.out.println("27A");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai2");
        }
        if (fasaPermohonan == null) {
            System.out.println("27");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            System.out.println("27");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            System.out.println("134RekodBuktiSampaiMemo5");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "134RekodBuktiSampaiMemo5");
        }
        if (fasaPermohonan == null) {
            System.out.println("B12RekodBuktiSampai");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B12RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            System.out.println("B05RekodBuktiSampai");
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
            LOG.info("mane kau masuk ni B05RekodBuktiSampai...hakmilik");
        }


        listNotisASB = new ArrayList<Notis>();
        listNotisASBG = new ArrayList<Notis>();
        listNotisPB = new ArrayList<Notis>();
        listNotisNBQ = new ArrayList<Notis>();
        listNotisNPJ = new ArrayList<Notis>();


        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                System.out.println("stage 48");
                System.out.println("hakmilik size " + hakmilik.getSenaraiPihakBerkepentingan().size());
//                 for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
//                    Notis notisASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
//                    if (notisASB != null) {
//                        listNotisASB.add(notisASB);
//                    }
//                    if (notisASBG != null) {
//                        listNotisASBG.add(notisASBG);
//                    }

                    pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                    for (PermohonanPihak p : pPihakList) {
                        Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBH");
                        Notis notisASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBG");
                        if (notisASB != null) {
                            listNotisASB.add(notisASB);
                        }
                        if (notisASBG != null) {
                            listNotisASBG.add(notisASBG);
                        }



                    }
                }
//                    }

                if (listNotisASB.size() != count && listNotisASBG.size() != count) {
                    LOG.info("Belum ade lg...");
                    simpanNotisGH();
                }
//                 }
                //    pemohonNotis();
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                System.out.println("stage 43");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
//               for(HakmilikPihakBerkepentingan hakmilikPihak :hakmilik.getSenaraiPihakBerkepentingan()){
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                            if(notisPB != null){
//                                listNotisPB.add(notisPB);
//                            }
//                            if(notisNBQ != null){
//                                listNotisNBQ.add(notisNBQ);
//                            }
//                        }
////               }
//
//                 if (listNotisPB.size() != count) {
//                        LOG.info("Belum ade lg...");
//                        simpanNotisASB();
//                    }
//               System.out.println(count);
//               
//                 wazer
                FasaPermohonan mf1 = new FasaPermohonan();
                mf1 = notisPenerimaanService.getFasaPermohonan(idPermohonan, "5rekodbuktisampai");
                if (mf1 != null) {
                    if (mf1.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                        showPP = true;
//                        for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik())
//                        {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                        if (notisPB != null) {
//                            listNotisPB.add(notisPB);
//                        }
//                        if (notisNBQ != null) {
//                            listNotisNBQ.add(notisNBQ);
//                        }

                            LOG.info(" idHakmilik :: " + idHakmilik);
                            LOG.info(" hakmilikPihak.getPihak().getIdPihak() :: " + hakmilikPihak.getPihak().getIdPihak());
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                            LOG.info(" pPihakList :: " + pPihakList.size());
                            for (PermohonanPihak p : pPihakList) {
                                LOG.info(" p.getIdPermohonanPihak() :: " + p.getIdPermohonanPihak());
                                Notis notisSIIE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");
                                LOG.info(" notisSIIE) :: " + notisSIIE.getPihak().getIdPermohonanPihak());
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBI");
                                LOG.info(" notisNBQ :: " + notisNBQ.getPihak().getIdPermohonanPihak());
                                LOG.info(" p.getIdPermohonanPihak() :: " + p.getIdPermohonanPihak());
                                Notis notisJPPH = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "JPPH");
                                LOG.info(" notisJPPH) :: " + notisJPPH.getPihak().getIdPermohonanPihak());

                                if (notisJPPH != null) {
                                    listNotisPB.add(notisJPPH);
                                }
                                if (notisSIIE != null) {
                                    listNotisPB.add(notisSIIE);
                                }
                                if (notisNBQ != null) {
                                    listNotisNBQ.add(notisNBQ);
                                }
                            }
                        }
                    }
                }
//                
                FasaPermohonan mf = new FasaPermohonan();
                mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan");
                if (mf1 != null) {
                    if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                        showPP = true;
//                        for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik())
//                        {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                        if (notisPB != null) {
//                            listNotisPB.add(notisPB);
//                        }
//                        if (notisNBQ != null) {
//                            listNotisNBQ.add(notisNBQ);
//                        }

                            LOG.info(" idHakmilik :: " + idHakmilik);
                            LOG.info(" hakmilikPihak.getPihak().getIdPihak() :: " + hakmilikPihak.getPihak().getIdPihak());
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                            LOG.info(" pPihakList :: " + pPihakList.size());
                            for (PermohonanPihak p : pPihakList) {
                                LOG.info(" p.getIdPermohonanPihak() :: " + p.getIdPermohonanPihak());
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");
                                //  LOG.info(" notisPB) :: " + notisPB.getPihak().getIdPermohonanPihak());
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBI");
                                //  LOG.info(" notisNBQ :: " + notisNBQ.getPihak().getIdPermohonanPihak());
                                //  LOG.info(" p.getIdPermohonanPihak() :: " + p.getIdPermohonanPihak());
                                Notis notisJPPH = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "JPPH");
                                //  LOG.info(" notisPB) :: " + notisPB.getPihak().getIdPermohonanPihak());

                                if (notisJPPH != null) {
                                    listNotisPB.add(notisJPPH);
                                }
                                if (notisPB != null) {
                                    listNotisPB.add(notisPB);
                                }
                                if (notisNBQ != null) {
                                    listNotisNBQ.add(notisNBQ);
                                }



                            }

                        }

                        LOG.info(" count :: " + count);
                        LOG.info(" listNotisPB.size() :: " + listNotisPB.size());
                        LOG.info(" listNotisNBQ.size() :: " + listNotisNBQ.size());
                        //                        }
                        if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                            LOG.info("Belum ade lg...");
                            LOG.info(" simpanNotisK() ade kedesakan;");
                            simpanNotisK();
                        }
                        pemohonNotis();
                    } else {

                        showPP = true;
//                            for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik())
//                            {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
////                                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                        if (notisPB != null) {
//                            listNotisPB.add(notisPB);
//                        }
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak p : pPihakList) {
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");
                                if (notisPB != null) {
                                    listNotisPB.add(notisPB);
                                }
                            }
                        }
//                            }
                        if (listNotisPB.size() != count) {
                            LOG.info("Belum ade lg...");
//                        simpanNotisASB();
                            simpanNotisK();
                        }
                        pemohonNotis();

                    }
                }
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    System.out.println("stage 27A");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NTBE");
                        if (notisPB != null) {
                            listNotisPB.add(notisPB);
                        }
                    }
//                    }
                    if (listNotisPB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    System.out.println("stage 27");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                        if (notisPB != null) {
                            listNotisPB.add(notisPB);
                        }
                        if (notisNBQ != null) {
                            listNotisNBQ.add(notisNBQ);
                        }
                    }
//                    }
                    if (listNotisPB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                    System.out.println("stage 27--27RekodSampaiTampal");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                        for (PermohonanPihak p : pPihakList) {
                            LOG.info("pPihakList" + p.getIdPermohonanPihak());
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBF");
                            if (notisPB != null) {
                                listNotisPB.add(notisPB);
                            }
                            if (notisNBQ != null) {
                                listNotisNBQ.add(notisNBQ);
                            }

                        }

                    }
                    LOG.info("listNotisPB.size()" + listNotisPB.size());
                    LOG.info("listNotisNBQ.size()" + listNotisNBQ.size());
                    LOG.info("count" + count);
                    if (listNotisPB.size() != count && listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();

//                        simpanNotis();
                    }
//                    }
//                if (listNotisPB.size() != count) {
//                    LOG.info("Belum ade lg...");
//                    simpanNotisASB();
//                }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                    System.out.println("stage 27--27RekodBuktiSampai");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
//                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
//                    if (notisPB != null) {
//                        listNotisPB.add(notisPB);
//                    }
//                    if (notisNBQ != null) {
//                        listNotisNBQ.add(notisNBQ);
//                    }

                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                        for (PermohonanPihak p : pPihakList) {
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBF");
                            if (notisPB != null) {
                                listNotisPB.add(notisPB);
                            }
                            if (notisNBQ != null) {
                                listNotisNBQ.add(notisNBQ);
                            }



                        }
                    }
//                    }
                    if (listNotisPB.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                    System.out.println("stage 134RekodBuktiSampaiMemo5");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");

                        if (notisNBQ != null) {
                            listNotisNBQ.add(notisNBQ);
                        }
                    }
//                    }
                    if (listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg...");
                        simpanNotisASB();
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                System.out.println("stage 48");
                System.out.println("hakmilik size " + hakmilik.getSenaraiPihakBerkepentingan().size());
//                 for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
//                    Notis notisASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
//                    if (notisASB != null) {
//                        listNotisASB.add(notisASB);
//                    }
//                    if (notisASBG != null) {
//                        listNotisASBG.add(notisASBG);
//                    }

                    pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                    for (PermohonanPihak p : pPihakList) {
                        Notis notisNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NJT");

                        if (notisNPJ != null) {
                            listNotisNPJ.add(notisNPJ);
                        }
                        if (notisNPJ != null) {
                            listNotisNPJ.add(notisNPJ);
                        }



                    }
                }
//                    }

//                if (listNotisASB.size() != count && listNotisASBG.size() != count) {
//                    LOG.info("Belum ade lg...");
//                    simpanNotisGH();
//                }
//                 }
                //    pemohonNotis();
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B12RekodBuktiSampai")) {
                System.out.println("stage 48");
                System.out.println("hakmilik size " + hakmilik.getSenaraiPihakBerkepentingan().size());
//                 for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
//                    Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
//                    Notis notisASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
//                    if (notisASB != null) {
//                        listNotisASB.add(notisASB);
//                    }
//                    if (notisASBG != null) {
//                        listNotisASBG.add(notisASBG);
//                    }

                    pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                    for (PermohonanPihak p : pPihakList) {
                        Notis notisNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NJT");

                        if (notisNPJ != null) {
                            listNotisNPJ.add(notisNPJ);
                        }
                        if (notisNPJ != null) {
                            listNotisNPJ.add(notisNPJ);
                        }



                    }
                }
//                    }

//                if (listNotisASB.size() != count && listNotisASBG.size() != count) {
//                    LOG.info("Belum ade lg...");
//                    simpanNotisGH();
//                }
//                 }
                //    pemohonNotis();
            }

            System.out.println("listNotisASB.size() > " + listNotisASB.size());
            LOG.info("listNotisNBQ >> " + listNotisNBQ.size());
            System.out.println("count > " + count);

            if (listNotisASB.size() == count) {
                System.out.println("listNotisASB.size() == count");
                for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisASB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                        notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                    }
                    if (notisASB != null) {
                        if (notisASB.getPenghantarNotis() != null) {
                            namaPengahantar.add(notisASB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisASB.getKodStatusTerima() != null) {
                            kodStatusTerima.add(notisASB.getKodStatusTerima().getKod());
                        }
                        if (notisASB.getKodCaraPenghantaran() != null) {
                            kodPenghantaran.add(notisASB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaan.add(notisASB.getCatatanPenerimaan());
                        if (notisASB.getTarikhHantar() != null) {
                            tarikhHantar.add(sdf.format(notisASB.getTarikhHantar()));
                        }
                        if (notisASB.getTarikhTerima() != null) {
                            tarikhTerima.add(sdf.format(notisASB.getTarikhTerima()));
                        }
                        if (notisASB.getBuktiPenerimaan() == null) {
                            idDokumenList.add("");
                        } else {
                            idDokumenList.add(String.valueOf(notisASB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            } else if (listNotisPB.size() == count && listNotisNBQ.size() == count) {
                System.out.println("listNotisPB.size() == count && listNotisNBQ.size() == count");
                for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
//                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    for (PermohonanPihak p : pPihakList) {
                        Notis notisPB = new Notis();
                        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                            notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBK");
                        }
                        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                            notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                        }
                        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                            notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                        }
                        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                            notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NBE");
                        }
                        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                            notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, p.getIdPermohonanPihak(), "NB5");
                        }


                        //                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        if (notisPB != null) {
                            if (notisPB.getPenghantarNotis() != null) {
                                namaPengahantar.add(notisPB.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPB.getKodStatusTerima() != null) {
                                kodStatusTerima.add(notisPB.getKodStatusTerima().getKod());
                            }
                            if (notisPB.getKodCaraPenghantaran() != null) {
                                kodPenghantaran.add(notisPB.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaan.add(notisPB.getCatatanPenerimaan());
                            if (notisPB.getTarikhHantar() != null) {
                                tarikhHantar.add(sdf.format(notisPB.getTarikhHantar()));
                            }
                            if (notisPB.getTarikhTerima() != null) {
                                tarikhTerima.add(sdf.format(notisPB.getTarikhTerima()));
                            }
                            if (notisPB.getBuktiPenerimaan() == null) {
                                idDokumenList.add("");
                            } else {
                                idDokumenList.add(String.valueOf(notisPB.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }




                    }

                }
            } else if (listNotisPB.size() == count) {
                System.out.println("listNotisPB.size() == count");
                for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                    if (notisPB != null) {
                        if (notisPB.getPenghantarNotis() != null) {
                            namaPengahantar.add(notisPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPB.getKodStatusTerima() != null) {
                            kodStatusTerima.add(notisPB.getKodStatusTerima().getKod());
                        }
                        if (notisPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaran.add(notisPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaan.add(notisPB.getCatatanPenerimaan());
                        if (notisPB.getTarikhHantar() != null) {
                            tarikhHantar.add(sdf.format(notisPB.getTarikhHantar()));
                        }
                        if (notisPB.getTarikhTerima() != null) {
                            tarikhTerima.add(sdf.format(notisPB.getTarikhTerima()));
                        }
                        if (notisPB.getBuktiPenerimaan() == null) {
                            idDokumenList.add("");
                        } else {
                            idDokumenList.add(String.valueOf(notisPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            } else if (listNotisNBQ.size() == count) {
                System.out.println("listNotisNBQ.size() == count");
                for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                    if (notisPB != null) {
                        if (notisPB.getPenghantarNotis() != null) {
                            namaPengahantar.add(notisPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPB.getKodStatusTerima() != null) {
                            kodStatusTerima.add(notisPB.getKodStatusTerima().getKod());
                        }
                        if (notisPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaran.add(notisPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaan.add(notisPB.getCatatanPenerimaan());
                        if (notisPB.getTarikhHantar() != null) {
                            tarikhHantar.add(sdf.format(notisPB.getTarikhHantar()));
                        }
                        if (notisPB.getTarikhTerima() != null) {
                            tarikhTerima.add(sdf.format(notisPB.getTarikhTerima()));
                        }
                        if (notisPB.getBuktiPenerimaan() == null) {
                            idDokumenList.add("");
                        } else {
                            idDokumenList.add(String.valueOf(notisPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            } else if (listNotisNPJ.size() != count) {
                System.out.println("listNotisPB.size() == count");
                for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                    if (hakmilik.getSenaraiPihakBerkepentingan().get(i).getAktif() == 'Y') {
                        Notis notisNPJ = null;
                        try {
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                            System.out.println("permohonanPihak.getIdPermohonanPihak() >> " + permohonanPihak.getIdPermohonanPihak());
                            notisNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        } catch (Exception j) {
                        }
                        if (notisNPJ != null) {
                            if (notisNPJ.getPenghantarNotis() != null) {
                                namaPengahantar.add(notisNPJ.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisNPJ.getKodStatusTerima() != null) {
                                kodStatusTerima.add(notisNPJ.getKodStatusTerima().getKod());
                            }
                            if (notisNPJ.getKodCaraPenghantaran() != null) {
                                kodPenghantaran.add(notisNPJ.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaan.add(notisNPJ.getCatatanPenerimaan());
                            if (notisNPJ.getTarikhHantar() != null) {
                                tarikhHantar.add(sdf.format(notisNPJ.getTarikhHantar()));
                            }
                            if (notisNPJ.getTarikhTerima() != null) {
                                tarikhTerima.add(sdf.format(notisNPJ.getTarikhTerima()));
                            }
                            if (notisNPJ.getBuktiPenerimaan() == null) {
                                idDokumenList.add("");
                            } else {
                                idDokumenList.add(String.valueOf(notisNPJ.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }
                    }
                }
            } else {
                // show = true;
                LOG.info("Hakmilik Details");
                show = false;
                addSimpleError("HakmilikDetails-Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
            }
            String PP = (String) getContext().getRequest().getParameter("showPP");
            if (PP != null && PP.equalsIgnoreCase("true")) {
                showPP = true;
                pemohonNotis();
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                System.out.println("B05RekodBuktiSampai");
                LOG.info("mane kau masuk ni B05RekodBuktiSampai...hakmilik 2");
//                for(HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()){
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilikPihak.getPihak().getIdPihak());
                    Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                    if (notisPB != null) {
                        listNotisPB.add(notisPB);
                    }
                }
//                    }
                if (listNotisPB.size() != count) {
                    LOG.info("Belum ade lg...");
                    simpanNotisASB();
                }
            }

        }




        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    public void pemohonNotis() {
        System.out.println("Pemohon Notis");
        listNotisPemohonASB = new ArrayList<Notis>();
        listNotisPemohonPB = new ArrayList<Notis>();
        listNotisPemohonNBQ = new ArrayList<Notis>();
        listNotisPemohonNPJ = new ArrayList<Notis>();


        int count1 = 0;
        if (permohonan.getSenaraiPemohon() != null) {
            count1 = permohonan.getSenaraiPemohon().size();
        }

        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
            if (permohonanPihak != null) {
                Notis notisPemohonASB = null;
                Notis notisPemohonASBG = null;
                Notis notisPemohonPB = null;
                Notis notisPemohonQ = null;
                Notis notisPemohonNPJ = null;
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                    try {
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                    } catch (Exception h) {
                    }
                    if (notisPemohonASB != null) {
                        listNotisPemohonASB.add(notisPemohonASB);
                    }
                    try {
                        notisPemohonASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
                    } catch (Exception h) {
                    }
                    if (notisPemohonASBG != null) {
                        listNotisPemohonPB.add(notisPemohonASBG);
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                    FasaPermohonan mf = new FasaPermohonan();
                    mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan");
//                    if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
//                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                        if (notisPemohonQ != null) {
//                            listNotisPemohonNBQ.add(notisPemohonQ);
//                        }
//                    }
                    notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                    if (notisPemohonPB != null) {
                        listNotisPemohonPB.add(notisPemohonPB);
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                    notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    if (notisPemohonPB != null) {
                        listNotisPemohonPB.add(notisPemohonPB);
                    }
                    if (notisPemohonQ != null) {
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                    notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                    notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    if (notisPemohonPB != null) {
                        listNotisPemohonPB.add(notisPemohonPB);
                    }
                    if (notisPemohonQ != null) {
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    }


                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                    notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                    notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    if (notisPemohonPB != null) {
                        listNotisPemohonPB.add(notisPemohonPB);
                    }
                    if (notisPemohonQ != null) {
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                    //   notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                    try {
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                    } catch (Exception hjk) {
                    }
                    if (notisPemohonQ != null) {
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    //   notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                    notisPemohonNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");

                    if (notisPemohonNPJ != null) {
                        listNotisPemohonNPJ.add(notisPemohonNPJ);
                    }
                }

            }
        }
        System.out.println("listNotisPemohonASB.size() " + listNotisPemohonASB.size());
        System.out.println("listNotisPemohonPB.size() " + listNotisPemohonPB.size());
        System.out.println("listNotisPemohonNBQ.size() " + listNotisPemohonNBQ.size());
        System.out.println("count1 " + count1);

        if (listNotisPemohonASB.size() != count1 || listNotisPemohonPB.size() != count1 || listNotisPemohonNBQ.size() != count1 || listNotisPemohonNPJ.size() != count1) {
            LOG.info("listNotisPemohonASB.size() != count1 || (listNotisPemohonPB.size() != count1 && listNotisPemohonNBQ.size() != count1");
            LOG.info("Belum ade lg...");
            simpanNotisPemohonASB();
            listNotisPemohonASB = new ArrayList<Notis>();
            listNotisPemohonASBG = new ArrayList<Notis>();
            listNotisPemohonPB = new ArrayList<Notis>();
            listNotisPemohonNBQ = new ArrayList<Notis>();
            listNotisPemohonNPJ = new ArrayList<Notis>();

            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonASB = null;
                    Notis notisPemohonASBG = null;
                    Notis notisPemohonPB = null;
                    Notis notisPemohonQ = null;
                    Notis notisPemohonNPJ = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                        try {
                            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                        } catch (Exception f) {
                        }
                        if (notisPemohonASB != null) {
                            listNotisPemohonASB.add(notisPemohonASB);
                        }
                        try {
                            notisPemohonASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
                        } catch (Exception f) {
                        }
                        if (notisPemohonASBG != null) {
                            listNotisPemohonASBG.add(notisPemohonASBG);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                        FasaPermohonan mf = new FasaPermohonan();
                        mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan");//29SediaBrgI
                        if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                            notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
                            if (notisPemohonQ != null) {
                                listNotisPemohonNBQ.add(notisPemohonQ);
                            }
                        }
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                        if (notisPemohonPB != null) {
                            listNotisPemohonPB.add(notisPemohonPB);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                        if (notisPemohonPB != null) {
                            listNotisPemohonPB.add(notisPemohonPB);
                        }
                        if (notisPemohonQ != null) {
                            listNotisPemohonNBQ.add(notisPemohonQ);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                        if (notisPemohonPB != null) {
                            listNotisPemohonPB.add(notisPemohonPB);
                        }
                        if (notisPemohonQ != null) {
                            listNotisPemohonNBQ.add(notisPemohonQ);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                        if (notisPemohonPB != null) {
                            listNotisPemohonPB.add(notisPemohonPB);
                        }
                        if (notisPemohonQ != null) {
                            listNotisPemohonNBQ.add(notisPemohonQ);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
//                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
//                        if (notisPemohonPB != null) {
//                            listNotisPemohonPB.add(notisPemohonPB);
//                        }
                        if (notisPemohonQ != null) {
                            listNotisPemohonNBQ.add(notisPemohonQ);
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
//                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                        if (notisPemohonNPJ != null) {
                            listNotisPemohonNPJ.add(notisPemohonNPJ);
                        }
                    }

                }
            }
        }
        LOG.info("listNotisPemohonASB.size() " + listNotisPemohonASB.size());
        LOG.info("listNotisPemohonASBG.size() " + listNotisPemohonASBG.size());
        LOG.info("listNotisPemohonPB.size() " + listNotisPemohonPB.size());
        LOG.info("listNotisPemohonNBQ.size() " + listNotisPemohonNBQ.size());
        LOG.info("listNotisPemohonNPJ.size() " + listNotisPemohonNPJ.size());
        LOG.info("count1 " + count1);
        if (listNotisPemohonASB.size() == count1 || listNotisPemohonASBG.size() == count1) {
            LOG.info("listNotisPemohonASB.size() == count1 || listNotisPemohonASBG.size() == count1");
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonASB = null;
                    Notis notisPemohonASBG = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                        try {
                            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                        } catch (Exception j) {
                            System.out.println("Error notisPemohonASB " + j);
                        }
                        try {
                            notisPemohonASBG = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
                        } catch (Exception j) {
                            System.out.println("Error notisPemohonASBG " + j);
                        }
                    }
                    if (notisPemohonASB != null) {
                        if (notisPemohonASB.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonASB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonASB.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonASB.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonASB.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonASB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonASB.getCatatanPenerimaan());
                        if (notisPemohonASB.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonASB.getTarikhHantar()));
                        }
                        if (notisPemohonASB.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonASB.getTarikhTerima()));
                        }
                        if (notisPemohonASB.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonASB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }

                    if (notisPemohonASBG != null) {
                        if (notisPemohonASBG.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonASBG.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonASBG.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonASBG.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonASBG.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonASBG.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonASBG.getCatatanPenerimaan());
                        if (notisPemohonASBG.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonASBG.getTarikhHantar()));
                        }
                        if (notisPemohonASBG.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonASBG.getTarikhTerima()));
                        }
                        if (notisPemohonASBG.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonASB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisPemohonPB.size() == count1 && listNotisPemohonNBQ.size() == count1) {
            LOG.info("listNotisPemohonPB.size() == count1 && listNotisPemohonNBQ.size() == count1 ");
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                LOG.info("Pemohon pemohon : permohonan.getSenaraiPemohon()");
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    LOG.info("permohonanPihak != null");
                    Notis notisPemohonPB = null;
                    Notis notisPemohonQ = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                        LOG.info("fasaPermohonan.getIdAliran().B05RekodBuktiSampai");
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
//                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                        LOG.info("fasaPermohonan.getIdAliran().27RekodSampaiTampal");
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                    }
                    if (notisPemohonPB != null) {
                        LOG.info("notisPemohonPB != null");
                        if (notisPemohonPB.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonPB.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonPB.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonPB.getCatatanPenerimaan());
                        if (notisPemohonPB.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonPB.getTarikhHantar()));
                        }
                        if (notisPemohonPB.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonPB.getTarikhTerima()));
                        }
                        if (notisPemohonPB.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                    if (notisPemohonQ != null) {
                        LOG.info("notisPemohonQ != null");
                        if (notisPemohonQ.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonQ.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonQ.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonQ.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonQ.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonQ.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonQ.getCatatanPenerimaan());
                        if (notisPemohonQ.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonQ.getTarikhHantar()));
                        }
                        if (notisPemohonQ.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonQ.getTarikhTerima()));
                        }
                        if (notisPemohonQ.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonQ.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisPemohonPB.size() == count1) {
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonPB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("49_4RekodBuktiSampai2") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43_4RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                    }
                    if (notisPemohonPB != null) {
                        if (notisPemohonPB.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonPB.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonPB.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonPB.getCatatanPenerimaan());
                        if (notisPemohonPB.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonPB.getTarikhHantar()));
                        }
                        if (notisPemohonPB.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonPB.getTarikhTerima()));
                        }
                        if (notisPemohonPB.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisPemohonNBQ.size() == count1) {
            LOG.info("listNotisPemohonNBQ == count1");
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonPB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                    }
                    if (notisPemohonPB != null) {
                        if (notisPemohonPB.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonPB.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonPB.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonPB.getCatatanPenerimaan());
                        if (notisPemohonPB.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonPB.getTarikhHantar()));
                        }
                        if (notisPemohonPB.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonPB.getTarikhTerima()));
                        }
                        if (notisPemohonPB.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisPemohonNBQ.size() == count1) {
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonPB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                        LOG.info("mane kau masuk ni B05RekodBuktiSampai...");
                    }

                    if (notisPemohonPB != null) {
                        if (notisPemohonPB.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonPB.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonPB.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonPB.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonPB.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonPB.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonPB.getCatatanPenerimaan());
                        if (notisPemohonPB.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonPB.getTarikhHantar()));
                        }
                        if (notisPemohonPB.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonPB.getTarikhTerima()));
                        }
                        if (notisPemohonPB.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonPB.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisPemohonNPJ.size() == count1) {
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonNPJ = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("05RekodBuktiSampai")) {
                        notisPemohonNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                    }
                    if (notisPemohonNPJ != null) {
                        if (notisPemohonNPJ.getPenghantarNotis() != null) {
                            namaPengahantarP.add(notisPemohonNPJ.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisPemohonNPJ.getKodStatusTerima() != null) {
                            kodStatusTerimaP.add(notisPemohonNPJ.getKodStatusTerima().getKod());
                        }
                        if (notisPemohonNPJ.getKodCaraPenghantaran() != null) {
                            kodPenghantaranP.add(notisPemohonNPJ.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaanP.add(notisPemohonNPJ.getCatatanPenerimaan());
                        if (notisPemohonNPJ.getTarikhHantar() != null) {
                            tarikhHantarP.add(sdf.format(notisPemohonNPJ.getTarikhHantar()));
                        }
                        if (notisPemohonNPJ.getTarikhTerima() != null) {
                            tarikhTerimaP.add(sdf.format(notisPemohonNPJ.getTarikhTerima()));
                        }
                        if (notisPemohonNPJ.getBuktiPenerimaan() == null) {
                            idDokumenListP.add("");
                        } else {
                            idDokumenListP.add(String.valueOf(notisPemohonNPJ.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else {
            show = false;
            LOG.info("Dokumen pemohon");
            addSimpleError("Pemohon----Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }

    }

    public void simpanNotis() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenE = null;
        Dokumen dokumenF = null;

        if (fasaPermohonan != null) {
            dokumenE = notisPenerimaanService.getDokumenBykod(idPermohonan, "E");
            dokumenF = notisPenerimaanService.getDokumenBykod(idPermohonan, "F");
            if (dokumenE != null && dokumenF != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        Notis notisE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                        for (PermohonanPihak pk : pPihakList) {
                            Notis notisE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBE");
                            if (notisE == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(p);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBE"));
                                notis1.setPihak(pk);
                                notis1.setDokumenNotis(dokumenE);
                                notis1.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                            Notis notisF = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBF");
                            if (notisF == null) {
                                Notis notis2 = new Notis();
                                notis2.setInfoAudit(info);
                                notis2.setPermohonan(p);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotisDAO.findById("NBF"));
                                notis2.setPihak(pk);
                                notis2.setDokumenNotis(dokumenF);
                                notis2.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis2);
                            }



                        }
//                        if (notisE == null) {
//                            Notis notis1 = new Notis();
//                            notis1.setInfoAudit(info);
//                            notis1.setPermohonan(p);
//                            notis1.setTarikhNotis(new java.util.Date());
//                            notis1.setKodNotis(kodNotisDAO.findById("NBE"));
//                            notis1.setPihak(permohonanPihak);
//                            notis1.setDokumenNotis(dokumenE);
//                            notis1.setJabatan(p.getKodUrusan().getJabatan());
//                            notisPenerimaanService.saveOrUpdateNotis(notis1);
//                        }
//                        Notis notisF = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
//                        if (notisF == null) {
//                            Notis notis2 = new Notis();
//                            notis2.setInfoAudit(info);
//                            notis2.setPermohonan(p);
//                            notis2.setTarikhNotis(new java.util.Date());
//                            notis2.setKodNotis(kodNotisDAO.findById("NBF"));
//                            notis2.setPihak(permohonanPihak);
//                            notis2.setDokumenNotis(dokumenF);
//                            notis2.setJabatan(p.getKodUrusan().getJabatan());
//                            notisPenerimaanService.saveOrUpdateNotis(notis2);
//                        }
                    }
                }
            }

        }
    }

    public void simpanNotis5() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        System.out.println("Simpan notis borang5");
        Dokumen dokumen5 = null;

        if (fasaPermohonan != null) {
            dokumen5 = notisPenerimaanService.getDokumenBykod(idPermohonan, "5");

            if (dokumen5 != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());


                        Notis notis5 = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                        if (notis5 == null) {
                            System.out.println("simpan brg 5");
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("NB5"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumen5);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                    }
                }
            }

        }
    }

    public void simpanNotisK() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);


        Dokumen dokumenI = null;
        Dokumen dokumenK = null;
        Dokumen dokumen8K = null;

        if (fasaPermohonan != null) {
            LOG.info("Simpan Notis K : fasaPermohonan x sama ngan null");
            FasaPermohonan mf = new FasaPermohonan();
            mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan"); //29SediaBrgI
            if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                Permohonan p = permohonanDAO.findById(idPermohonan);

//                Dokumen dokumenI = null;
//                Dokumen dokumenK= null;
                LOG.info("Ada kedesakan");
                dokumenI = notisPenerimaanService.getDokumenBykod3(idPermohonan, "I");
                dokumenK = notisPenerimaanService.getDokumenBykod3(idPermohonan, "K");
                dokumen8K = notisPenerimaanService.getDokumenBykod3(idPermohonan, "8K"); // K utk keseluruhan dan 8K sebahagian
                if (dokumenI != null && (dokumenK != null || dokumen8K != null)) {
                    LOG.info("dokumen i ngn k x sama null");
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            LOG.info("saving loop...");

//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());

                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak pk : pPihakList) {
                                Notis notisI = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBI");
                                if (notisI == null) {
                                    LOG.info("save notis Borang I...");
                                    Notis notis1 = new Notis();
                                    notis1.setInfoAudit(info);
                                    notis1.setPermohonan(p);
                                    notis1.setTarikhNotis(new java.util.Date());
                                    notis1.setKodNotis(kodNotisDAO.findById("NBI"));
                                    notis1.setPihak(pk);
                                    notis1.setDokumenNotis(dokumenI);
                                    notis1.setJabatan(p.getKodUrusan().getJabatan());
                                    notisPenerimaanService.saveOrUpdateNotis(notis1);
                                }
                                Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBK");
                                if (notisK == null) {
                                    LOG.info("saving notis Borang K...");
                                    Notis notis2 = new Notis();
                                    notis2.setInfoAudit(info);
                                    notis2.setPermohonan(p);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotisDAO.findById("NBK"));
                                    notis2.setPihak(pk);
                                    if (dokumenK != null) {
                                        notis2.setDokumenNotis(dokumenK);
                                    } else if (dokumen8K != null) {
                                        notis2.setDokumenNotis(dokumen8K);
                                    }
                                    notis2.setJabatan(p.getKodUrusan().getJabatan());
                                    notisPenerimaanService.saveOrUpdateNotis(notis2);
                                }













                            }
//                            Notis notisI = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                            if (notisI == null) {
//                                LOG.info("save notis Borang I...");
//                                Notis notis1 = new Notis();
//                                notis1.setInfoAudit(info);
//                                notis1.setPermohonan(p);
//                                notis1.setTarikhNotis(new java.util.Date());
//                                notis1.setKodNotis(kodNotisDAO.findById("NBI"));
//                                notis1.setPihak(permohonanPihak);
//                                notis1.setDokumenNotis(dokumenI);
//                                notis1.setJabatan(p.getKodUrusan().getJabatan());
//                                notisPenerimaanService.saveOrUpdateNotis(notis1);
//                            }
//                            Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                            if (notisK == null) {
//                                LOG.info("saving notis Borang K...");
//                                Notis notis2 = new Notis();
//                                notis2.setInfoAudit(info);
//                                notis2.setPermohonan(p);
//                                notis2.setTarikhNotis(new java.util.Date());
//                                notis2.setKodNotis(kodNotisDAO.findById("NBK"));
//                                notis2.setPihak(permohonanPihak);
//                                if (dokumenK != null) {
//                                    notis2.setDokumenNotis(dokumenK);
//                                } else if (dokumen8K != null) {
//                                    notis2.setDokumenNotis(dokumen8K);
//                                }
//                                notis2.setJabatan(p.getKodUrusan().getJabatan());
//                                notisPenerimaanService.saveOrUpdateNotis(notis2);
//                            }
                        }
                    }
                }



            } else {
                Permohonan p = permohonanDAO.findById(idPermohonan);

//                        Dokumen dokumenI = null;
//                        Dokumen dokumenK= null;
                dokumenK = notisPenerimaanService.getDokumenBykod(idPermohonan, "K");
                if (dokumenK != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {

                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak pk : pPihakList) {
                                Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBK");
                                if (notisK == null) {
                                    Notis notis2 = new Notis();
                                    notis2.setInfoAudit(info);
                                    notis2.setPermohonan(p);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotisDAO.findById("NBK"));
                                    notis2.setPihak(pk);
                                    notis2.setDokumenNotis(dokumenK);
                                    notis2.setJabatan(p.getKodUrusan().getJabatan());
                                    notisPenerimaanService.saveOrUpdateNotis(notis2);
                                }

                            }




//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//
//                            Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                            if (notisK == null) {
//                                Notis notis2 = new Notis();
//                                notis2.setInfoAudit(info);
//                                notis2.setPermohonan(p);
//                                notis2.setTarikhNotis(new java.util.Date());
//                                notis2.setKodNotis(kodNotisDAO.findById("NBK"));
//                                notis2.setPihak(permohonanPihak);
//                                notis2.setDokumenNotis(dokumenK);
//                                notis2.setJabatan(p.getKodUrusan().getJabatan());
//                                notisPenerimaanService.saveOrUpdateNotis(notis2);
//                            }
                        }
                    }
                }
            }


        }
    }

    public void simpanNotisGH() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);


        Dokumen dokumenASB = null;
        Dokumen dokumenPB = null;

        if (fasaPermohonan != null) {
            LOG.info("Simpan Notis GH : fasaPermohonan x sama ngan null");
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                Permohonan p = permohonanDAO.findById(idPermohonan);
                LOG.info("48RekodBuktiSampai");
                dokumenASB = notisPenerimaanService.getDokumenBykod3(idPermohonan, "H");
                dokumenPB = notisPenerimaanService.getDokumenBykod3(idPermohonan, "G");
                if (dokumenASB != null && (dokumenPB != null)) {
                    LOG.info("dokumen G ngn H x sama null");
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            LOG.info("saving loop...");
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak pk : pPihakList) {
                                Notis notisI = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBH");
                                if (notisI == null) {
                                    LOG.info("save notis Borang H...");
                                    Notis notis1 = new Notis();
                                    notis1.setInfoAudit(info);
                                    notis1.setPermohonan(p);
                                    notis1.setTarikhNotis(new java.util.Date());
                                    notis1.setKodNotis(kodNotisDAO.findById("NBH"));
                                    notis1.setPihak(pk);
                                    notis1.setDokumenNotis(dokumenASB);
                                    notis1.setJabatan(p.getKodUrusan().getJabatan());
                                    notisPenerimaanService.saveOrUpdateNotis(notis1);
                                }
                                Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBG");
                                if (notisK == null) {
                                    LOG.info("saving notis Borang G...");
                                    Notis notis2 = new Notis();
                                    notis2.setInfoAudit(info);
                                    notis2.setPermohonan(p);
                                    notis2.setTarikhNotis(new java.util.Date());
                                    notis2.setKodNotis(kodNotisDAO.findById("NBG"));
                                    notis2.setPihak(pk);
                                    notis2.setDokumenNotis(dokumenPB);
                                    notis2.setJabatan(p.getKodUrusan().getJabatan());
                                    notisPenerimaanService.saveOrUpdateNotis(notis2);
                                }



                            }







//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());

//                            Notis notisI = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
//                            if (notisI == null) {
//                                LOG.info("save notis Borang H...");
//                                Notis notis1 = new Notis();
//                                notis1.setInfoAudit(info);
//                                notis1.setPermohonan(p);
//                                notis1.setTarikhNotis(new java.util.Date());
//                                notis1.setKodNotis(kodNotisDAO.findById("NBH"));
//                                notis1.setPihak(permohonanPihak);
//                                notis1.setDokumenNotis(dokumenASB);
//                                notis1.setJabatan(p.getKodUrusan().getJabatan());
//                                notisPenerimaanService.saveOrUpdateNotis(notis1);
//                            }
//                            Notis notisK = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
//                            if (notisK == null) {
//                                LOG.info("saving notis Borang G...");
//                                Notis notis2 = new Notis();
//                                notis2.setInfoAudit(info);
//                                notis2.setPermohonan(p);
//                                notis2.setTarikhNotis(new java.util.Date());
//                                notis2.setKodNotis(kodNotisDAO.findById("NBG"));
//                                notis2.setPihak(permohonanPihak);
//                                notis2.setDokumenNotis(dokumenPB);
//                                notis2.setJabatan(p.getKodUrusan().getJabatan());
//                                notisPenerimaanService.saveOrUpdateNotis(notis2);
//                            }
                        }
                    }
                }



            }

        }
    }

    public void simpanNotisASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenASB = null;
        Dokumen dokumenPB = null;
        Dokumen dokumenQ = null;
        Dokumen dokumenJPPH = null;
        System.out.println("Simpan Notis ASB");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {

                dokumenASB = notisPenerimaanService.getDokumenBykod(idPermohonan, "H");
                if (dokumenASB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBH"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenASB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "G");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBG"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                dokumenASB = notisPenerimaanService.getDokumenBykod(idPermohonan, "K");
                if (dokumenASB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = null;
                            try {
                                notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                            } catch (Exception j) {
                            }
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBK"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenASB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "BTE");
                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NTBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                LOG.debug("27RekodSampaiTampal-----1");
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "E");
                if (dokumenPB != null) {
                    LOG.debug("27RekodSampaiTampal-----2");
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        LOG.debug("27RekodSampaiTampal-----3");
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
//                        LOG.debug("permohonanPihak " + permohonanPihak.getIdPermohonanPihak());
                        if (permohonanPihak == null) {
                            LOG.debug("27RekodSampaiTampal-----4");
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            LOG.debug("27RekodSampaiTampal-----5");
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            if (notisPemohonASB == null) {
                                LOG.debug("27RekodSampaiTampal-----6");
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "F");
                if (dokumenQ != null) {
                    LOG.debug("27RekodSampaiTampal-----7");
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        LOG.debug("27RekodSampaiTampal-----8");
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            LOG.debug("27RekodSampaiTampal-----9");
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            LOG.debug("27RekodSampaiTampal-----10");
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPemohonASB == null) {
                                LOG.debug("27RekodSampaiTampal-----11");
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBF"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "E");
                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "F");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBF"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "5");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NB5"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {

                dokumenJPPH = notisPenerimaanService.getDokumenBykod(idPermohonan, "JPPHA");
                if (dokumenJPPH != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    notisNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
                    if (notisNPJ.isEmpty()) {
                        Notis notis1 = new Notis();
                        notis1.setInfoAudit(info);
                        notis1.setPermohonan(permohonan);
                        notis1.setTarikhNotis(new java.util.Date());
                        notis1.setKodNotis(kodNotisDAO.findById("NJT"));
//                            notis1.setPihak(permohonanPihak);
                        notis1.setDokumenNotis(dokumenJPPH);
                        notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                        notis1.setPenerimaNotis(kodAgensiDAO.findById("1306").getNama());
                        notisPenerimaanService.saveOrUpdateNotis(notis1);
                    }
                    rehydrate();
                }
            }

        }
    }

    public void simpanNotisPemohonASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Dokumen dokumenASB = null;
        Dokumen dokumenASB8K = null;
        Dokumen dokumenPB = null;
        Dokumen dokumenQ = null;
        System.out.println("Simpan notis Pemohon ASB");
        System.out.println("fasa Permohonan " + fasaPermohonan.getIdAliran());

        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                dokumenASB = notisPenerimaanService.getDokumenBykod(idPermohonan, "G");
                if (dokumenASB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    int i = 0;
                    System.out.println("permohonan.getSenaraiPemohon() size : " + permohonan.getSenaraiPemohon().size());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        //   for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                        //   for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                        i++;
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = null;
                            try {
                                notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
                            } catch (Exception h) {
                            }
                            //   System.out.println("permohonan pihak notis Pemohon ASB : " + notisPemohonASB.getPihak().getIdPermohonanPihak());
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBG"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenASB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                                System.out.println("Bil i NBG " + i);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                System.out.println("simpanNotisPemohonASB : 43RekodBuktiSampai");
//                dokumenASB = notisPenerimaanService.getDokumenBykod(idPermohonan, "K");
                dokumenASB = notisPenerimaanService.getDokumenBykod3(idPermohonan, "K");
                dokumenASB8K = notisPenerimaanService.getDokumenBykod3(idPermohonan, "8K");
                if (dokumenASB != null || dokumenASB8K != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBK"));
                                notis1.setPihak(permohonanPihak);
                                if (dokumenASB != null) {
                                    notis1.setDokumenNotis(dokumenASB);
                                } else {
                                    notis1.setDokumenNotis(dokumenASB8K);
                                }
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "BTE");
                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NTBE");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NTBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                System.out.println("27RekodSampaiTampal ");
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "E");
                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            System.out.println("27_1");
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            System.out.println("27_2");
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "F");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBF"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "E");
                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBE"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "F");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NBF"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {

                dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "5");
                if (dokumenQ != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NB5"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenQ);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("05RekodBuktiSampai")) {
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "JPPH");

                if (dokumenPB != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("NJT"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenPB);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution popupPenghantarNotis() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP != null && PP.equalsIgnoreCase("true")) {
            showPP = true;
        }
        String HP = (String) getContext().getRequest().getParameter("showHP");
        if (HP != null && HP.equalsIgnoreCase("true")) {
            showHP = "true";
        }
        isMahkamah = (String) getContext().getRequest().getParameter("isMahkamah");

        penghantarNotis = new PenghantarNotis();
        return new JSP("pengambilan/negerisembilan/PenghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();




        return new RedirectResolution(PenerimaanNotisBorangN9ActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOG.info("id mohon : " + idPermohonan);
        LOG.info("id hakmilik : " + idHakmilik);

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
//        if (!fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
        if (fasaPermohonan == null) {
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
//            LOG.info("id mh : " + hakmilikPermohonan.getId());
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraan == null) {

                    HakmilikPerbicaraan hakmilikPerbicaraanw = new HakmilikPerbicaraan();
                    hakmilikPerbicaraanw.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraanw.setCawangan(permohonan.getCawangan());
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraanw.setInfoAudit(info);
                    notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanw);

                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                }
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiTuanTanahMohonPihak(hakmilikPerbicaraan.getIdPerbicaraan());
            }
        }

        InfoAudit info;

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "48RekodBuktiSampai");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai2");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "134RekodBuktiSampaiMemo5");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
        }


        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            showHP = "true";
            hakmilik = hakmilikDAO.findById(idHakmilik);
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                    LOG.debug("senaraiPermohonanPihak.size() >> " + senaraiPermohonanPihak.size());
                    for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                        LOG.debug("pPihakList >> " + pPihakList.size());
                        for (PermohonanPihak pk : pPihakList) {
                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBG");
                            Notis notisASBH = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBH");
                            if (notisASB != null) {
                                info = notisASB.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisASB.setInfoAudit(info);
                                notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception f) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisASB);
                            }


                            if (notisASBH != null) {
                                info = notisASBH.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisASBH.setInfoAudit(info);
                                notisASBH.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisASBH.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisASBH.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                try {
                                    notisASBH.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                    notisASBH.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                } catch (Exception h) {
                                }
                                try {
                                    notisASBH.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception f) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisASBH);
                            }
                        }
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                        Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
//                        Notis notisASBH = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
//                        if (notisASB != null) {
//                            info = notisASB.getInfoAudit();
//                            info.setDiKemaskiniOleh(peng);
//                            info.setTarikhKemaskini(new java.util.Date());
//                            notisASB.setInfoAudit(info);
//                            notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                            notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                            notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                            notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                            notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                            try {
//                                notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                            } catch (Exception f) {
//                            }
//                            notisPenerimaanService.saveOrUpdateNotis(notisASB);
//                        }

//                        if (notisASBH != null) {
//                            info = notisASBH.getInfoAudit();
//                            info.setDiKemaskiniOleh(peng);
//                            info.setTarikhKemaskini(new java.util.Date());
//                            notisASBH.setInfoAudit(info);
//                            notisASBH.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                            notisASBH.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                            notisASBH.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                            try {
//                                notisASBH.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                                notisASBH.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                            } catch (Exception h) {
//                            }
//                            try {
//                                notisASBH.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                            } catch (Exception f) {
//                            }
//                            notisPenerimaanService.saveOrUpdateNotis(notisASBH);
//                        }
                    }
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                    FasaPermohonan mf = new FasaPermohonan();
                    mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "29SediaBrgI"); //28AdaKedesakan
                    if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                        for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                            for (PermohonanPihak pk : pPihakList) {
                                Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBK");
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBI");
                                if (notisPB != null) {
                                    info = notisPB.getInfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    notisPB.setInfoAudit(info);
                                    notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                    notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                    notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                    notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                    notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                    try {
                                        notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                    } catch (Exception f) {
                                    }
                                    notisPenerimaanService.saveOrUpdateNotis(notisPB);
                                }
                                if (notisNBQ != null) {
                                    info = notisNBQ.getInfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    notisNBQ.setInfoAudit(info);
                                    notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                    notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                    notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                    notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                    notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                    try {
                                        notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                    } catch (Exception f) {
                                    }
                                    notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
                                }

                            }



                        }









//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
//                            if (notisPB != null) {
//                                info = notisPB.getInfoAudit();
//                                info.setDiKemaskiniOleh(peng);
//                                info.setTarikhKemaskini(new java.util.Date());
//                                notisPB.setInfoAudit(info);
//                                notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                                notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                                notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                                notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                                notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                                try {
//                                    notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                                } catch (Exception f) {
//                                }
//                                notisPenerimaanService.saveOrUpdateNotis(notisPB);
//                            }
//                            if (notisNBQ != null) {
//                                info = notisNBQ.getInfoAudit();
//                                info.setDiKemaskiniOleh(peng);
//                                info.setTarikhKemaskini(new java.util.Date());
//                                notisNBQ.setInfoAudit(info);
//                                notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                                notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                                notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                                notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                                notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                                try {
//                                    notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                                } catch (Exception f) {
//                                }
//                                notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
//                            }

//                        }


                    } else {
                        for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
                            pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                            for (PermohonanPihak pk : pPihakList) {
                                Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBK");
                                if (notisASB != null) {
                                    info = notisASB.getInfoAudit();
                                    info.setDiKemaskiniOleh(peng);
                                    info.setTarikhKemaskini(new java.util.Date());
                                    notisASB.setInfoAudit(info);
                                    notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                    notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                    notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                    notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                    notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                    try {
                                        notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                    } catch (Exception f) {
                                    }
                                    notisPenerimaanService.saveOrUpdateNotis(notisASB);
                                }


                            }




//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                            Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
//                            if (notisASB != null) {
//                                info = notisASB.getInfoAudit();
//                                info.setDiKemaskiniOleh(peng);
//                                info.setTarikhKemaskini(new java.util.Date());
//                                notisASB.setInfoAudit(info);
//                                notisASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                                notisASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                                notisASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                                notisASB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                                notisASB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                                try {
//                                    notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                                } catch (Exception f) {
//                                }
//                                notisPenerimaanService.saveOrUpdateNotis(notisASB);
//                            }
                        }
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {

                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                        for (PermohonanPihak pk : pPihakList) {
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NTBE");
                            if (notisPB != null) {
                                info = notisPB.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisPB.setInfoAudit(info);
                                notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception h) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisPB);
                            }



                        }





//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NTBE");
//                        if (notisPB != null) {
//                            info = notisPB.getInfoAudit();
//                            info.setDiKemaskiniOleh(peng);
//                            info.setTarikhKemaskini(new java.util.Date());
//                            notisPB.setInfoAudit(info);
//                            notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                            notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                            notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                            notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                            notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                            try {
//                                notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                            } catch (Exception h) {
//                            }
//                            notisPenerimaanService.saveOrUpdateNotis(notisPB);
//                        }

                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                    for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
//                            for (PermohonanPihak p : pPihakList) {
                            LOG.info("p : " + permohonanPihak.getIdPermohonanPihak());
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                            if (notisPB != null) {
                                info = notisPB.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisPB.setInfoAudit(info);
                                notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception h) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisPB);
                            }
                            if (notisNBQ != null) {
                                info = notisNBQ.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisNBQ.setInfoAudit(info);
                                notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception h) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
                            }

//                            }

                        }
                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                    LOG.debug("senarai Permohonan pihak size >>> " + senaraiPermohonanPihak.size());
                    for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
                        pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                        for (PermohonanPihak pk : pPihakList) {
                            Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBE");
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NBF");
                            LOG.debug("notisPB getNamaTampal >> " + notisPB.getNamaTampal());
                            if (notisPB != null) {
                                info = notisPB.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisPB.setInfoAudit(info);
                                notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception h) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisPB);
                            }
                            if (notisNBQ != null) {
                                info = notisNBQ.getInfoAudit();
                                info.setDiKemaskiniOleh(peng);
                                info.setTarikhKemaskini(new java.util.Date());
                                notisNBQ.setInfoAudit(info);
                                notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                                notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                                notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                                notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                                notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                                try {
                                    notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
                                } catch (Exception h) {
                                }
                                notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
                            }


                        }
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
//                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
//                        if (notisPB != null) {
//                            info = notisPB.getInfoAudit();
//                            info.setDiKemaskiniOleh(peng);
//                            info.setTarikhKemaskini(new java.util.Date());
//                            notisPB.setInfoAudit(info);
//                            notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                            notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                            notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                            notisPB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                            notisPB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                            try {
//                                notisPB.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                            } catch (Exception h) {
//                            }
//                            notisPenerimaanService.saveOrUpdateNotis(notisPB);
//                        }
//                        if (notisNBQ != null) {
//                            info = notisNBQ.getInfoAudit();
//                            info.setDiKemaskiniOleh(peng);
//                            info.setTarikhKemaskini(new java.util.Date());
//                            notisNBQ.setInfoAudit(info);
//                            notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
//                            notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
//                            notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
//                            notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
//                            notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
//                            try {
//                                notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
//                            } catch (Exception h) {
//                            }
//                            notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
//                        }

                    }
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                    for (int i = 0; i < senaraiPermohonanPihak.size(); i++) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, senaraiPermohonanPihak.get(i).getPihak().getIdPihak());
                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");

                        if (notisNBQ != null) {
                            info = notisNBQ.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisNBQ.setInfoAudit(info);
                            notisNBQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisNBQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisNBQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisNBQ.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisNBQ.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            try {
                                notisNBQ.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            } catch (Exception h) {
                            }
                            notisPenerimaanService.saveOrUpdateNotis(notisNBQ);
                        }

                    }
                }
            }
        }
        listNotisPemohonASB = new ArrayList<Notis>();
        listNotisPemohonPB = new ArrayList<Notis>();
        listNotisPemohonNBQ = new ArrayList<Notis>();
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP.equalsIgnoreCase("true")) {
            showPP = true;
            for (int i = 0; i < permohonan.getSenaraiPemohon().size(); i++) {
                Pemohon pemohon = permohonan.getSenaraiPemohon().get(i);
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonASB = null;
                    Notis notisPemohonPB = null;
                    Notis notisPemohonQ = null;

                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                        FasaPermohonan mf = new FasaPermohonan();
                        mf = notisPenerimaanService.getFasaPermohonan(idPermohonan, "28AdaKedesakan");
                        if (mf.getKeputusan().getKod().equalsIgnoreCase("DE")) {
                            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                        } else {
                            notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
                            notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBI");
                        }
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
                    } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
                        notisPemohonQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
                    }
                    if (notisPemohonASB != null) {
                        info = notisPemohonASB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohonASB.setInfoAudit(info);
                        notisPemohonASB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisPemohonASB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisPemohonASB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisPemohonASB.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisPemohonASB.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        /// catatan 
                        try {
                            notisPemohonASB.setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        } catch (Exception f) {
                        }
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonASB);
                        listNotisPemohonASB.add(notisPemohonASB);
                    } else if (notisPemohonPB != null && notisPemohonQ != null) {
                        info = notisPemohonPB.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohonPB.setInfoAudit(info);
                        notisPemohonPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisPemohonPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisPemohonPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisPemohonPB.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisPemohonPB.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        try {
                            notisPemohonPB.setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        } catch (Exception f) {
                        }
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonPB);
                        listNotisPemohonPB.add(notisPemohonPB);

                        info = notisPemohonQ.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohonQ.setInfoAudit(info);
                        notisPemohonQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisPemohonQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisPemohonQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisPemohonQ.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisPemohonQ.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        try {
                            notisPemohonQ.setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        } catch (Exception f) {
                        }
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonQ);
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    } else if (notisPemohonQ != null) {
                        info = notisPemohonQ.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisPemohonQ.setInfoAudit(info);
                        try {
                            notisPemohonQ.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                            notisPemohonQ.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                            notisPemohonQ.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                            notisPemohonQ.setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                            notisPemohonQ.setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        } catch (Exception jh) {
                        }
                        try {
                            notisPemohonQ.setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        } catch (Exception f) {
                        }
                        notisPenerimaanService.saveOrUpdateNotis(notisPemohonQ);
                        listNotisPemohonNBQ.add(notisPemohonQ);
                    }
                    addSimpleMessage("Maklumat Berjaya Disimpan");
                }
            }
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            notisNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
            for (int i = 0; i < notisNPJ.size(); i++) {
//                    LOG.info("penghantar notis " : penghantarNotisDAO.findById(namaPengahantarP.get(i)));
//                pPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                Notis notisNPJ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pk.getIdPermohonanPihak(), "NPJ");
                if (notisNPJ != null) {
                    info = notisNPJ.get(i).getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    notisNPJ.get(i).setInfoAudit(info);
                    notisNPJ.get(i).setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                    notisNPJ.get(i).setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                    notisNPJ.get(i).setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                    notisNPJ.get(i).setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                    notisNPJ.get(i).setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                    try {
                        notisNPJ.get(i).setCatatanPenerimaan(catatanPenerimaanP.get(i));
                    } catch (Exception h) {
                    }
                    notisPenerimaanService.saveOrUpdateNotis(notisNPJ.get(i));
                }
            }
            addSimpleMessage("Maklumat Berjaya Disimpan");
            rehydrate();
        }

//        hakmilikDetails();

        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMahkamah() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19SediaSuratAfidavit");

        if (fasaPermohonan != null) {
            senaraiPM = new ArrayList<PermohonanMahkamah>();
            senaraiPM = notisPenerimaanService.getPermohonanMahkamahListByidMohon(idPermohonan);
            if (senaraiPM != null && senaraiPM.size() > 0) {
                permohonanMahkamah = senaraiPM.get(0);
                notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");
                if (notisMahkamahRPD != null) {
                    InfoAudit info = notisMahkamahRPD.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    notisMahkamahRPD.setInfoAudit(info);
                    notisMahkamahRPD.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarM));
                    notisMahkamahRPD.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaM));
                    notisMahkamahRPD.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranM));
                    notisMahkamahRPD.setTarikhHantar(sdf.parse(tarikhHantarM));
                    notisMahkamahRPD.setTarikhTerima(sdf.parse(tarikhTerimaM));
                    notisMahkamahRPD.setCatatanPenerimaan(catatanPenerimaanM);
                    notisPenerimaanService.saveOrUpdateNotis(notisMahkamahRPD);
                }
                notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");
                if (notisMahkamahRPD != null) {
                    if (notisMahkamahRPD.getPenghantarNotis() != null) {
                        namaPengahantarM = notisMahkamahRPD.getPenghantarNotis().getIdPenghantarNotis();
                    }
                    if (notisMahkamahRPD.getKodStatusTerima() != null) {
                        kodStatusTerimaM = notisMahkamahRPD.getKodStatusTerima().getKod();
                    }
                    if (notisMahkamahRPD.getKodCaraPenghantaran() != null) {
                        kodPenghantaranM = notisMahkamahRPD.getKodCaraPenghantaran().getKod();
                    }
                    catatanPenerimaanM = notisMahkamahRPD.getCatatanPenerimaan();
                    if (notisMahkamahRPD.getTarikhHantar() != null) {
                        tarikhHantarM = sdf.format(notisMahkamahRPD.getTarikhHantar());
                    }
                    if (notisMahkamahRPD.getTarikhTerima() != null) {
                        tarikhTerimaM = sdf.format(notisMahkamahRPD.getTarikhTerima());
                    }
                    if (notisMahkamahRPD.getBuktiPenerimaan() == null) {
                        idDokumenM = "";
                    } else {
                        idDokumenM = String.valueOf(notisMahkamahRPD.getBuktiPenerimaan().getIdDokumen());
                    }

                }
            }
        }

//        hakmilikDetails();

        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "48RekodBuktiSampai");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai2");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "134RekodBuktiSampaiMemo5");
        }

        Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBG");
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");


        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
            notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NB5");
        }

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
            fname = String.valueOf(notisASB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
            fname = String.valueOf(notisPB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
            fname = String.valueOf(notisNBQ.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
            fname = String.valueOf(notisNBQ.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
            fname = String.valueOf(notisNBQ.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("134RekodBuktiSampaiMemo5")) {
            fname = String.valueOf(notisNBQ.getIdNotis());
        }
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
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
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                    ia = notisASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisASB);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai2")) {
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                }
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/negerisembilan/upload_fileN9.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDocPemohon() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        if (!idPihak.isEmpty()) {
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));
        }
//        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "48RekodBuktiSampai");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "43RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai2");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "27RekodBuktiSampai");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
        }

        Notis notisPemohonASB = null;
        Notis notisPemohonPB = null;
        Notis notisPemohonNBQ = null;
        List<Notis> notisPemohonNPJ = null;

        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBH");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBK");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
            notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
            notisPemohonNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
            notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBE");
            notisPemohonNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBF");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            notisPemohonNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
        }


        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
            fname = String.valueOf(notisPemohonASB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
            fname = String.valueOf(notisPemohonPB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                fname = String.valueOf(notisPemohonNPJ.get(i).getIdNotis());
            }
        }

        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
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
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
                    ia = notisPemohonASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPemohonASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonASB);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai") || fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodSampaiTampal")) {
                    ia = notisPemohonPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPemohonPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonPB);
                    ia = notisPemohonNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPemohonNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonNBQ);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                        ia = notisPemohonNPJ.get(i).getInfoAudit();
                        ia.setDiKemaskiniOleh(p);
                        ia.setTarikhKemaskini(new java.util.Date());
                        notisPemohonNPJ.get(i).setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                        notisPemohonNPJ.get(i).setInfoAudit(ia);
                        lelongService.updateNotis(notisPemohonNPJ.get(i));
                    }
                }

                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/negerisembilan/upload_fileN9.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDocMahkamah() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19SediaSuratAfidavit");

        notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        fname = String.valueOf(notisMahkamahRPD.getIdNotis());
        LOG.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                LOG.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                LOG.info("namaFail :" + namaFail);
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
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kodDokumenDAO.findById("BPN"));
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);
                // update at DasarTuntutanNotis
                ia = notisMahkamahRPD.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisMahkamahRPD.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                notisMahkamahRPD.setInfoAudit(ia);
                lelongService.updateNotis(notisMahkamahRPD);

                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/negerisembilan/upload_file.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        }

        Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            fname = String.valueOf(notisASB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
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
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
                    ia = notisASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisASB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisASB);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
                    ia = notisNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisNBQ);
                }

            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScanPemohon() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        LOG.info("idpihak : " + idPihak);
        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        if (!idPihak.isEmpty()) {
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));
        }

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
        }

        Notis notisPemohonASB = null;
        Notis notisPemohonPB = null;
        Notis notisPemohonNBQ = null;
        List<Notis> notisPemohonNPJ = null;

        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
            notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
            notisPemohonNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            notisPemohonNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
        }

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            fname = String.valueOf(notisPemohonASB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
            fname = String.valueOf(notisPemohonPB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                fname = String.valueOf(notisPemohonNPJ.get(i).getIdNotis());
            }
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
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
                    ia = notisPemohonASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPemohonASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonASB);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
                    ia = notisPemohonPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonPB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPemohonPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonPB);
                    ia = notisPemohonNBQ.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPemohonNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                    notisPemohonNBQ.setInfoAudit(ia);
                    lelongService.updateNotis(notisPemohonNBQ);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                        ia = notisPemohonNPJ.get(i).getInfoAudit();
                        ia.setDiKemaskiniOleh(p);
                        ia.setTarikhKemaskini(new java.util.Date());
                        notisPemohonNPJ.get(i).setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                        notisPemohonNPJ.get(i).setInfoAudit(ia);
                        lelongService.updateNotis(notisPemohonNPJ.get(i));
                    }
                }
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScanNJT() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Long idNotis = Long.parseLong(getContext().getRequest().getParameter("idNotis"));
        LOG.info("idNotis : " + idNotis);
        idPihak = (String) getContext().getRequest().getParameter("idPihak");

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "B05RekodBuktiSampai");
        }

        List<Notis> notisPemohonNPJ = null;

        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            notisPemohonNPJ = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
        }

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
            for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                fname = String.valueOf(notisPemohonNPJ.get(i).getIdNotis());
            }
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
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("B05RekodBuktiSampai")) {
                    for (int i = 0; i < notisPemohonNPJ.size(); i++) {
                        ia = notisPemohonNPJ.get(i).getInfoAudit();
                        ia.setDiKemaskiniOleh(p);
                        ia.setTarikhKemaskini(new java.util.Date());
                        notisPemohonNPJ.get(i).setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                        notisPemohonNPJ.get(i).setInfoAudit(ia);
                        lelongService.updateNotis(notisPemohonNPJ.get(i));
                    }
                }
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/scan_doc_831.jsp").addParameter("popup", "true");
    }

    public Resolution popupScanMahkamah() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19SediaSuratAfidavit");

        notisMahkamahRPD = notisPenerimaanService.getNotisBykodNotis(idPermohonan, "RPD");

        InfoAudit ia = new InfoAudit();
        String fname = null;
        fname = String.valueOf(notisMahkamahRPD.getIdNotis());
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
                ia = notisMahkamahRPD.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisMahkamahRPD.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                notisMahkamahRPD.setInfoAudit(ia);
                lelongService.updateNotis(notisMahkamahRPD);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/negerisembilan/scan_doc.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenghantarNotis() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP != null && PP.equalsIgnoreCase("true")) {
            showPP = true;
        }
        String HP = (String) getContext().getRequest().getParameter("showHP");
        if (HP != null && HP.equalsIgnoreCase("true")) {
            showHP = "true";
        }

        InfoAudit ia = penghantarNotis.getInfoAudit();
        KodCawangan caw = peng.getKodCawangan();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            penghantarNotis.setCawangan(caw);
            penghantarNotis.setAktif('Y');
            penghantarNotis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        lelongService.saveOrUpdate(penghantarNotis);


        if (showHP.equalsIgnoreCase("true")) {
            if (idHakmilik != null && !idHakmilik.isEmpty()) {
                hakmilikDetails();
            } else {
                if (showPP != false) {
                    showFormPP();
                }
            }
        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPenghantarNotisM() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        InfoAudit ia = penghantarNotis.getInfoAudit();
        KodCawangan caw = peng.getKodCawangan();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            penghantarNotis.setCawangan(caw);
            penghantarNotis.setAktif('Y');
            penghantarNotis.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        lelongService.saveOrUpdate(penghantarNotis);

        showFormMahkamah();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/negerisembilan/Penerimaan_Notis_Borang_831ANS.jsp").addParameter("tab", "true");
    }

    public Resolution popupUpload() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        isPemohonPihak = (String) getContext().getRequest().getParameter("isPemohonPihak");
        isMahkamah = (String) getContext().getRequest().getParameter("isMahkamah");
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP != null && PP.equalsIgnoreCase("true")) {
            showPP = true;
        }
        String HP = (String) getContext().getRequest().getParameter("showHP");
        if (HP != null && HP.equalsIgnoreCase("true")) {
            showHP = "true";
        }
//        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/negerisembilan/upload_fileN9.jsp").addParameter("popup", "true");
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

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public List<Dokumen> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<Dokumen> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public PenghantarNotis getPenghantarNotis() {
        return penghantarNotis;
    }

    public void setPenghantarNotis(PenghantarNotis penghantarNotis) {
        this.penghantarNotis = penghantarNotis;
    }

    public long getIdDokumen2() {
        return idDokumen2;
    }

    public void setIdDokumen2(long idDokumen2) {
        this.idDokumen2 = idDokumen2;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public String getIdNotis() {
        return idNotis;
    }

    public void setIdNotis(String idNotis) {
        this.idNotis = idNotis;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Notis> getListNotisNBQ() {
        return listNotisNBQ;
    }

    public void setListNotisNBQ(List<Notis> listNotisNBQ) {
        this.listNotisNBQ = listNotisNBQ;
    }

    public List<Notis> getListNotisPB() {
        return listNotisPB;
    }

    public void setListNotisPB(List<Notis> listNotisPB) {
        this.listNotisPB = listNotisPB;
    }

    public List<Integer> getNamaPengahantar() {
        return namaPengahantar;
    }

    public void setNamaPengahantar(List<Integer> namaPengahantar) {
        this.namaPengahantar = namaPengahantar;
    }

    public List<String> getCatatanPenerimaan() {
        return catatanPenerimaan;
    }

    public void setCatatanPenerimaan(List<String> catatanPenerimaan) {
        this.catatanPenerimaan = catatanPenerimaan;
    }

    public List<String> getKodPenghantaran() {
        return kodPenghantaran;
    }

    public void setKodPenghantaran(List<String> kodPenghantaran) {
        this.kodPenghantaran = kodPenghantaran;
    }

    public List<String> getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(List<String> kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public List<String> getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(List<String> tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public List<String> getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(List<String> tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public List<Notis> getListNotisASB() {
        return listNotisASB;
    }

    public void setListNotisASB(List<Notis> listNotisASB) {
        this.listNotisASB = listNotisASB;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public List<String> getCatatanPenerimaanP() {
        return catatanPenerimaanP;
    }

    public void setCatatanPenerimaanP(List<String> catatanPenerimaanP) {
        this.catatanPenerimaanP = catatanPenerimaanP;
    }

    public List<String> getIdDokumenListP() {
        return idDokumenListP;
    }

    public void setIdDokumenListP(List<String> idDokumenListP) {
        this.idDokumenListP = idDokumenListP;
    }

    public List<String> getKodPenghantaranP() {
        return kodPenghantaranP;
    }

    public void setKodPenghantaranP(List<String> kodPenghantaranP) {
        this.kodPenghantaranP = kodPenghantaranP;
    }

    public List<String> getKodStatusTerimaP() {
        return kodStatusTerimaP;
    }

    public void setKodStatusTerimaP(List<String> kodStatusTerimaP) {
        this.kodStatusTerimaP = kodStatusTerimaP;
    }

    public List<Notis> getListNotisPemohonASB() {
        return listNotisPemohonASB;
    }

    public void setListNotisPemohonASB(List<Notis> listNotisPemohonASB) {
        this.listNotisPemohonASB = listNotisPemohonASB;
    }

    public List<Integer> getNamaPengahantarP() {
        return namaPengahantarP;
    }

    public void setNamaPengahantarP(List<Integer> namaPengahantarP) {
        this.namaPengahantarP = namaPengahantarP;
    }

    public boolean isShowPP() {
        return showPP;
    }

    public void setShowPP(boolean showPP) {
        this.showPP = showPP;
    }

    public List<String> getTarikhHantarP() {
        return tarikhHantarP;
    }

    public void setTarikhHantarP(List<String> tarikhHantarP) {
        this.tarikhHantarP = tarikhHantarP;
    }

    public List<String> getTarikhTerimaP() {
        return tarikhTerimaP;
    }

    public void setTarikhTerimaP(List<String> tarikhTerimaP) {
        this.tarikhTerimaP = tarikhTerimaP;
    }

    public String getShowHP() {
        return showHP;
    }

    public void setShowHP(String showHP) {
        this.showHP = showHP;
    }

    public String getIsPemohonPihak() {
        return isPemohonPihak;
    }

    public void setIsPemohonPihak(String isPemohonPihak) {
        this.isPemohonPihak = isPemohonPihak;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public String getCatatanPenerimaanM() {
        return catatanPenerimaanM;
    }

    public void setCatatanPenerimaanM(String catatanPenerimaanM) {
        this.catatanPenerimaanM = catatanPenerimaanM;
    }

    public String getIdDokumenM() {
        return idDokumenM;
    }

    public void setIdDokumenM(String idDokumenM) {
        this.idDokumenM = idDokumenM;
    }

    public String getIsMahkamah() {
        return isMahkamah;
    }

    public void setIsMahkamah(String isMahkamah) {
        this.isMahkamah = isMahkamah;
    }

    public String getKodPenghantaranM() {
        return kodPenghantaranM;
    }

    public void setKodPenghantaranM(String kodPenghantaranM) {
        this.kodPenghantaranM = kodPenghantaranM;
    }

    public String getKodStatusTerimaM() {
        return kodStatusTerimaM;
    }

    public void setKodStatusTerimaM(String kodStatusTerimaM) {
        this.kodStatusTerimaM = kodStatusTerimaM;
    }

    public List<Notis> getListNotisMahkamahRPD() {
        return listNotisMahkamahRPD;
    }

    public void setListNotisMahkamahRPD(List<Notis> listNotisMahkamahRPD) {
        this.listNotisMahkamahRPD = listNotisMahkamahRPD;
    }

    public List<Notis> getListNotisPemohonNBQ() {
        return listNotisPemohonNBQ;
    }

    public void setListNotisPemohonNBQ(List<Notis> listNotisPemohonNBQ) {
        this.listNotisPemohonNBQ = listNotisPemohonNBQ;
    }

    public List<Notis> getListNotisPemohonPB() {
        return listNotisPemohonPB;
    }

    public void setListNotisPemohonPB(List<Notis> listNotisPemohonPB) {
        this.listNotisPemohonPB = listNotisPemohonPB;
    }

    public Integer getNamaPengahantarM() {
        return namaPengahantarM;
    }

    public void setNamaPengahantarM(Integer namaPengahantarM) {
        this.namaPengahantarM = namaPengahantarM;
    }

    public Notis getNotisMahkamahRPD() {
        return notisMahkamahRPD;
    }

    public void setNotisMahkamahRPD(Notis notisMahkamahRPD) {
        this.notisMahkamahRPD = notisMahkamahRPD;
    }

    public NotisPenerimaanService getNotisPenerimaanService() {
        return notisPenerimaanService;
    }

    public void setNotisPenerimaanService(NotisPenerimaanService notisPenerimaanService) {
        this.notisPenerimaanService = notisPenerimaanService;
    }

    public PermohonanSupayaBantahanService getPermohonanSupayaBantahanService() {
        return permohonanSupayaBantahanService;
    }

    public void setPermohonanSupayaBantahanService(PermohonanSupayaBantahanService permohonanSupayaBantahanService) {
        this.permohonanSupayaBantahanService = permohonanSupayaBantahanService;
    }

    public List<PermohonanMahkamah> getSenaraiPM() {
        return senaraiPM;
    }

    public void setSenaraiPM(List<PermohonanMahkamah> senaraiPM) {
        this.senaraiPM = senaraiPM;
    }

    public boolean isShowMM() {
        return showMM;
    }

    public void setShowMM(boolean showMM) {
        this.showMM = showMM;
    }

    public String getTarikhHantarM() {
        return tarikhHantarM;
    }

    public void setTarikhHantarM(String tarikhHantarM) {
        this.tarikhHantarM = tarikhHantarM;
    }

    public String getTarikhTerimaM() {
        return tarikhTerimaM;
    }

    public void setTarikhTerimaM(String tarikhTerimaM) {
        this.tarikhTerimaM = tarikhTerimaM;
    }

    public List<PermohonanPihakTidakBerkepentingan> getSenaraipbt() {
        return senaraipbt;
    }

    public void setSenaraipbt(List<PermohonanPihakTidakBerkepentingan> senaraipbt) {
        this.senaraipbt = senaraipbt;
    }

    public PermohonanPihakTidakBerkepentingan getPbt() {
        return pbt;
    }

    public void setPbt(PermohonanPihakTidakBerkepentingan pbt) {
        this.pbt = pbt;
    }

    public List<Notis> getListNotisPBT() {
        return listNotisPBT;
    }

    public void setListNotisPBT(List<Notis> listNotisPBT) {
        this.listNotisPBT = listNotisPBT;
    }

    public List<Notis> getListNotisPemohonNPJ() {
        return listNotisPemohonNPJ;
    }

    public void setListNotisPemohonNPJ(List<Notis> listNotisPemohonNPJ) {
        this.listNotisPemohonNPJ = listNotisPemohonNPJ;
    }

    public List<Notis> getListNotisNPJ() {
        return listNotisNPJ;
    }

    public void setListNotisNPJ(List<Notis> listNotisNPJ) {
        this.listNotisNPJ = listNotisNPJ;
    }

    public List<Notis> getNotisNPJ() {
        return notisNPJ;
    }

    public void setNotisNPJ(List<Notis> notisNPJ) {
        this.notisNPJ = notisNPJ;
    }

    public boolean isShowJ() {
        return showJ;
    }

    public void setShowJ(boolean showJ) {
        this.showJ = showJ;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isShowJpph() {
        return showJpph;
    }

    public void setShowJpph(boolean showJpph) {
        this.showJpph = showJpph;
    }

    public List<KodAgensi> getKodAgensis() {
        return kodAgensis;
    }

    public void setKodAgensis(List<KodAgensi> kodAgensis) {
        this.kodAgensis = kodAgensis;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }
}
