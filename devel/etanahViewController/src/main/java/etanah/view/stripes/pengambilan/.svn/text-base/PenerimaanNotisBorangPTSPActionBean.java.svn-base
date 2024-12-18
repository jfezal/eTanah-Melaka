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
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.LelongService;
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
 * @author Rajesh
 */
@UrlBinding("/pengambilan/penerimaan_notis_borang_ptsp")
public class PenerimaanNotisBorangPTSPActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
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
    etanah.Configuration conf;
    private Hakmilik hakmilik;
    private List<Notis> listNotisASB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohon8JTK = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonNJT = new ArrayList<Notis>();
    private List<Notis> listNotisPB = new ArrayList<Notis>();
    private List<Notis> listNotisNBQ = new ArrayList<Notis>();
    private List<Notis> listNotis8JTK = new ArrayList<Notis>();
    private List<Notis> listNotisNJT = new ArrayList<Notis>();
    private List<Notis> listNotisNBM = new ArrayList<Notis>();
    private List<Notis> listNotisPAC = new ArrayList<Notis>();
    private List<Notis> listNotisPAD = new ArrayList<Notis>();
    private List<Notis> listNotisPAB = new ArrayList<Notis>();
    private List<Notis> listNotisPAE = new ArrayList<Notis>();
    private List<Notis> listNotisKMD = new ArrayList<Notis>();
    private List<Notis> listNotisRAB = new ArrayList<Notis>();
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Notis notis;
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanPihak permohonanPihak;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> permohonanPihakList;
    private List<KandunganFolder> listKandunganFolder;
    private List<Dokumen> listDokumen;
    private List<Notis> notisNJT;
    private List<Notis> notisASB1;
    private String idNotis;
    FileBean fileToBeUploaded;
    private long idDokumen2;
    private PenghantarNotis penghantarNotis;
    private boolean show = true;
    private boolean showPP = false;
    private boolean showHP = false;
    private boolean showPemohon = false;
//    private String showHP = "false";
    private String isPemohonPihak = "false";
    private static final Logger LOG = Logger.getLogger(PenerimaanNotisBorangPTSPActionBean.class);

    //Notis for only Hakmilik Pihak
    @DefaultHandler
    //Notis for jpph
    public Resolution showFormJpph() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }

            int count = 0;
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                count += hp.getHakmilik().getSenaraiPihakBerkepentingan().size();
            }

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "21RekodSampaiTampalPC");
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampalPR");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "06RekodSampaiTampalPR");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampal");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "09KMRekodSampaiTampal");
            }

            listNotisNBQ = new ArrayList<Notis>();
            listNotisNJT = new ArrayList<Notis>();
            listNotisPAC = new ArrayList<Notis>();
            listNotisPAD = new ArrayList<Notis>();
            listNotisPAB = new ArrayList<Notis>();
            listNotisPAE = new ArrayList<Notis>();
            listNotisNBM = new ArrayList<Notis>();
            listNotisKMD = new ArrayList<Notis>();
            listNotisRAB = new ArrayList<Notis>();

            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("21RekodSampaiTampalPC")) {
                    showHP = true;
//                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisRAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "RAB");
                            if (notisRAB != null) {
                                listNotisRAB.add(notisRAB);
                            }
                        }
                    }
                    if (listNotisRAB.size() != count) {
                        LOG.info("Belum ade lg RAB...");
                        simpanNotis();
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampalPR")) {
                    showHP = true;
//                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            Notis notisKMD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
                            if (notisKMD != null) {
                                listNotisKMD.add(notisKMD);
                            }
                        }
                    }
                    if (listNotisKMD.size() != count) {
                        LOG.info("Belum ade lg KMD...");
                        simpanNotis();
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("06RekodSampaiTampalPR")) {
                    showHP = true;
//                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                            LOG.info("hakmilik pihak : " + hp.getHakmilik().getSenaraiPihakBerkepentingan().size());
                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                            permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                            for (PermohonanPihak pp : permohonanPihakList) {
                            Notis notisPAC = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAC");
                            Notis notisPAD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAD");
                            Notis notisPAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAB");
                            Notis notisPAE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAE");
                            Notis notisNBM = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBM");
                            if (notisPAC != null) {
                                listNotisPAC.add(notisPAC);
                            }
                            if (notisPAD != null) {
                                listNotisPAD.add(notisPAD);
                            }
                            if (notisPAB != null) {
                                listNotisPAB.add(notisPAB);
                            }
                            if (notisPAE != null) {
                                listNotisPAE.add(notisPAE);
                            }
                            if (notisNBM != null) {
                                listNotisNBM.add(notisNBM);
                            }
//                            }
                        }
                    }
                    if (listNotisPAC.size() != count || listNotisPAD.size() != count || listNotisPAB.size() != count || listNotisPAE.size() != count || listNotisNBM.size() != count) {
                        LOG.info("Belum ade lg PAC, PAD, PAB, PAE, NBM...");
                        simpanNotis();
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampal")) {
                    showHP = true;
//                    showPP = true;
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                            permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                            for (PermohonanPihak pp : permohonanPihakList) {
                                Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pp.getIdPermohonanPihak(), "NBQ");
                                if (notisNBQ != null) {
                                    listNotisNBQ.add(notisNBQ);
                                }
                            }
                        }
                    }
                    if (listNotisNBQ.size() != count) {
                        LOG.info("Belum ade lg NBQ...");
                        simpanNotis();
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("09KMRekodSampaiTampal")) {
                    showPP = true;
                    notisNJT = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
//                    for (int i = 0; i < notisNJT.size(); i++) {
//                        if (notisNJT.get(i) != null) {
//                            notisNJT.get(i).getPenghantarNotis();
//                            notisNJT.get(i).getKodStatusTerima();
//                            notisNJT.get(i).getKodCaraPenghantaran();
//                            notisNJT.get(i).getTarikhHantar();
//                            notisNJT.get(i).getTarikhTerima();
//                            notisNJT.get(i).getCatatanPenerimaan();
//                        }
//                    }
//                    if (notisNJT != null) {
//                        listNotisNJT.add(notis);
//                    }
                    if (listNotisNJT.size() != count) {
                        LOG.info("Belum ade lg NJT...");
                        simpanNotisASB();
                    }
//                    pemohonNotis();

                }
            }
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang_ptsp.jsp").addParameter("tab", "true");
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

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19RekodSampaiTampalPC");

            if (fasaPermohonan != null) {
                showPemohon = true;
                pemohonNotis();
            }
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang_ptsp.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19RekodSampaiTampalPC");
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                    LOG.info("fasaPermohonan == 19RekodSampaiTampalPC");
                    showPemohon = true;
                    notisASB1 = notisPenerimaanService.getNotisByIdMohonMPkodNotis(idPermohonan, "ASB");
                }
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "06RekodSampaiTampalPR");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampal");
            }
            if (fasaPermohonan == null) {
                fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "09KMRekodSampaiTampal");
            }
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("09KMRekodSampaiTampal")) {
                    LOG.info("fasaPermohonan == 09KMRekodSampaiTampal");
                    showPP = true;
                    notisNJT = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
                }
            }
        }
    }

    public Resolution hakmilikDetails() {
        LOG.info(":: hakmilikDetails ::");
        showHP = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        int count = hakmilik.getSenaraiPihakBerkepentingan().size();
        LOG.info("count : " + hakmilik.getSenaraiPihakBerkepentingan().size());

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "21RekodSampaiTampalPC");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampalPR");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "06RekodSampaiTampalPR");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "09KMRekodSampaiTampal");
        }


        listNotisNJT = new ArrayList<Notis>();
        listNotisNBQ = new ArrayList<Notis>();
        listNotisPAC = new ArrayList<Notis>();
        listNotisPAD = new ArrayList<Notis>();
        listNotisPAB = new ArrayList<Notis>();
        listNotisPAE = new ArrayList<Notis>();
        listNotisNBM = new ArrayList<Notis>();
        listNotisKMD = new ArrayList<Notis>();
        listNotisRAB = new ArrayList<Notis>();

        if (fasaPermohonan != null) {
            LOG.info("fasaPermohonan != null");
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("21RekodSampaiTampalPC")) {
                LOG.info("21RekodSampaiTampalPC");
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    for (PermohonanPihak pp : permohonanPihakList) {
                    Notis notisRAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "RAB");
                    if (notisRAB != null) {
                        LOG.info("notisRAB 1 != null");
                        listNotisRAB.add(notisRAB);
                    }
//                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampalPR")) {
                LOG.info("16RekodSampaiTampal");
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    for (PermohonanPihak pp : permohonanPihakList) {
                    Notis notisKMD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
                    if (notisKMD != null) {
                        LOG.info("notisKMD 1 != null");
                        listNotisKMD.add(notisKMD);
                    }
//                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("06RekodSampaiTampalPR")) {
                LOG.info("06RekodSampaiTampalPR");
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                    LOG.info("hakmilik pihak hakmilikdetails : " + hakmilik.getSenaraiPihakBerkepentingan().size());
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                    for (PermohonanPihak pp : permohonanPihakList) {
                    Notis notisPAC = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAC");
                    Notis notisPAD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAD");
                    Notis notisPAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAB");
                    Notis notisPAE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAE");
                    Notis notisNBM = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBM");
                    if (notisPAC != null) {
                        listNotisPAC.add(notisPAC);
                    }
                    if (notisPAD != null) {
                        listNotisPAD.add(notisPAD);
                    }
                    if (notisPAB != null) {
                        listNotisPAB.add(notisPAB);
                    }
                    if (notisPAE != null) {
                        listNotisPAE.add(notisPAE);
                    }
                    if (notisNBM != null) {
                        listNotisNBM.add(notisNBM);
                    }
//                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampal")) {
                LOG.info("16RekodSampaiTampal");
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
//                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilikPihak.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    for (PermohonanPihak pp : permohonanPihakList) {
                        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pp.getIdPermohonanPihak(), "NBQ");
                        if (notisNBQ != null) {
                            LOG.info("notisNBQ 1 != null");
                            listNotisNBQ.add(notisNBQ);
                        }
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("09KMRekodSampaiTampal")) {
                LOG.info("09KMRekodSampaiTampal");
                for (HakmilikPihakBerkepentingan hakmilikPihak : hakmilik.getSenaraiPihakBerkepentingan()) {
                    LOG.info("for hakmilikPihak");
                    permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                    LOG.info("permohonanPihak : " + permohonanPihak);
                    Notis notisNJT = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                    LOG.info("idPermohonan : " + idPermohonan);
                    LOG.info("id mp : " + permohonanPihak.getIdPermohonanPihak());
                    LOG.info("notisNJT service : " + notisNJT);
                    if (notisNJT != null) {
                        LOG.info("notisNJT 1 != null");
                        listNotisNJT.add(notisNJT);
                    }
                }
            }
        }

        if (listNotisRAB.size() == count) {
            LOG.info("listNotisRAB.size() == count");
            for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                for (PermohonanPihak pp : permohonanPihakList) {
                Notis notisRAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "RAB");
                if (notisRAB != null) {
                    LOG.info("notisKMD 2 != null");
                    if (notisRAB.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisRAB.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisRAB.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisRAB.getKodStatusTerima().getKod());
                    }
                    if (notisRAB.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisRAB.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisRAB.getCatatanPenerimaan());
                    if (notisRAB.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisRAB.getTarikhHantar()));
                    }
                    if (notisRAB.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisRAB.getTarikhTerima()));
                    }
                    if (notisRAB.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisRAB.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
//                }
            }
        } else if (listNotisKMD.size() == count) {
            LOG.info("listNotisNBQ.size() == count");
            for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                for (PermohonanPihak pp : permohonanPihakList) {
                Notis notisKMD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
                if (notisKMD != null) {
                    LOG.info("notisKMD 2 != null");
                    if (notisKMD.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisKMD.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisKMD.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisKMD.getKodStatusTerima().getKod());
                    }
                    if (notisKMD.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisKMD.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisKMD.getCatatanPenerimaan());
                    if (notisKMD.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisKMD.getTarikhHantar()));
                    }
                    if (notisKMD.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisKMD.getTarikhTerima()));
                    }
                    if (notisKMD.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisKMD.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
//                }
            }
        } else if (listNotisPAC.size() == count && listNotisPAD.size() == count && listNotisPAB.size() == count && listNotisPAE.size() == count && listNotisNBM.size() == count) {
            LOG.info("listNotis.size() == count");
            LOG.info("listNotisPAC : " + listNotisPAC.size());
            LOG.info("listNotisPAD : " + listNotisPAD.size());
            LOG.info("listNotisPAB : " + listNotisPAB.size());
            LOG.info("listNotisPAE : " + listNotisPAE.size());
            LOG.info("listNotisNBM : " + listNotisNBM.size());
            for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
//                for (PermohonanPihak pp : permohonanPihakList) {
                Notis notisPAC = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAC");
                if (notisPAC != null) {
                    LOG.info("notisPAC 2 != null");
                    if (notisPAC.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisPAC.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisPAC.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisPAC.getKodStatusTerima().getKod());
                    }
                    if (notisPAC.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisPAC.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisPAC.getCatatanPenerimaan());
                    if (notisPAC.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisPAC.getTarikhHantar()));
                    }
                    if (notisPAC.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisPAC.getTarikhTerima()));
                    }
                    if (notisPAC.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisPAC.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
                Notis notisPAD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAD");
                if (notisPAD != null) {
                    LOG.info("notisPAD 2 != null");
                    if (notisPAD.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisPAD.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisPAD.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisPAD.getKodStatusTerima().getKod());
                    }
                    if (notisPAD.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisPAD.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisPAD.getCatatanPenerimaan());
                    if (notisPAD.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisPAD.getTarikhHantar()));
                    }
                    if (notisPAD.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisPAD.getTarikhTerima()));
                    }
                    if (notisPAD.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisPAD.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
                Notis notisPAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAB");
                if (notisPAB != null) {
                    LOG.info("notisPAD 2 != null");
                    if (notisPAB.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisPAB.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisPAB.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisPAB.getKodStatusTerima().getKod());
                    }
                    if (notisPAB.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisPAB.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisPAB.getCatatanPenerimaan());
                    if (notisPAB.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisPAB.getTarikhHantar()));
                    }
                    if (notisPAB.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisPAB.getTarikhTerima()));
                    }
                    if (notisPAB.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisPAB.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
                Notis notisPAE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAE");
                if (notisPAE != null) {
                    LOG.info("notisPAE 2 != null");
                    if (notisPAE.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisPAE.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisPAE.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisPAE.getKodStatusTerima().getKod());
                    }
                    if (notisPAE.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisPAE.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisPAE.getCatatanPenerimaan());
                    if (notisPAE.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisPAE.getTarikhHantar()));
                    }
                    if (notisPAE.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisPAE.getTarikhTerima()));
                    }
                    if (notisPAE.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisPAE.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
                Notis notisNBM = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBM");
                if (notisNBM != null) {
                    LOG.info("notisNBM 2 != null");
                    if (notisNBM.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisNBM.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisNBM.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisNBM.getKodStatusTerima().getKod());
                    }
                    if (notisNBM.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisNBM.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisNBM.getCatatanPenerimaan());
                    if (notisNBM.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisNBM.getTarikhHantar()));
                    }
                    if (notisNBM.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisNBM.getTarikhTerima()));
                    }
                    if (notisNBM.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisNBM.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
//                }
            }
        } else if (listNotisNBQ.size() == count) {
            LOG.info("listNotisNBQ.size() == count");
            for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
//                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                for (PermohonanPihak pp : permohonanPihakList) {
                    Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pp.getIdPermohonanPihak(), "NBQ");
                    if (notisNBQ != null) {
                        LOG.info("notisNBQ 2 != null");
                        if (notisNBQ.getPenghantarNotis() != null) {
                            namaPengahantar.add(notisNBQ.getPenghantarNotis().getIdPenghantarNotis());
                        }
                        if (notisNBQ.getKodStatusTerima() != null) {
                            kodStatusTerima.add(notisNBQ.getKodStatusTerima().getKod());
                        }
                        if (notisNBQ.getKodCaraPenghantaran() != null) {
                            kodPenghantaran.add(notisNBQ.getKodCaraPenghantaran().getKod());
                        }
                        catatanPenerimaan.add(notisNBQ.getCatatanPenerimaan());
                        if (notisNBQ.getTarikhHantar() != null) {
                            tarikhHantar.add(sdf.format(notisNBQ.getTarikhHantar()));
                        }
                        if (notisNBQ.getTarikhTerima() != null) {
                            tarikhTerima.add(sdf.format(notisNBQ.getTarikhTerima()));
                        }
                        if (notisNBQ.getBuktiPenerimaan() == null) {
                            idDokumenList.add("");
                        } else {
                            idDokumenList.add(String.valueOf(notisNBQ.getBuktiPenerimaan().getIdDokumen()));
                        }
                    }
                }
            }
        } else if (listNotisNJT.size() == count) {
            LOG.info("listNotisNJT.size() == count");
            for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                LOG.info("masuk list for");
                permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                Notis notisNJT = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NJT");
                if (notisNJT != null) {
                    LOG.info("notisNJT 2 != null");
                    if (notisNJT.getPenghantarNotis() != null) {
                        namaPengahantar.add(notisNJT.getPenghantarNotis().getIdPenghantarNotis());
                    }
                    if (notisNJT.getKodStatusTerima() != null) {
                        kodStatusTerima.add(notisNJT.getKodStatusTerima().getKod());
                    }
                    if (notisNJT.getKodCaraPenghantaran() != null) {
                        kodPenghantaran.add(notisNJT.getKodCaraPenghantaran().getKod());
                    }
                    catatanPenerimaan.add(notisNJT.getCatatanPenerimaan());
                    if (notisNJT.getTarikhHantar() != null) {
                        tarikhHantar.add(sdf.format(notisNJT.getTarikhHantar()));
                    }
                    if (notisNJT.getTarikhTerima() != null) {
                        tarikhTerima.add(sdf.format(notisNJT.getTarikhTerima()));
                    }
                    if (notisNJT.getBuktiPenerimaan() == null) {
                        idDokumenList.add("");
                    } else {
                        idDokumenList.add(String.valueOf(notisNJT.getBuktiPenerimaan().getIdDokumen()));
                    }
                }
            }
        } else {
            LOG.info("else");
            show = false;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP != null && PP.equalsIgnoreCase("true")) {
            showPP = true;
            pemohonNotis();
        }
        return new JSP("pengambilan/Penerimaan_Notis_Borang_ptsp.jsp").addParameter("tab", "true");
    }

    public void pemohonNotis() {
        LOG.info(":: pemohonNotis ::");
        listNotisPemohonASB = new ArrayList<Notis>();

        int count1 = 0;
        if (permohonan.getSenaraiPemohon() != null) {
            count1 = permohonan.getSenaraiPemohon().size();
            LOG.info("count1 : " + count1);
        }

        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
            if (permohonanPihak != null) {
                Notis notisPemohonASB = null;
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                    LOG.info("19RekodSampaiTampalPC 1");
                    notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                }
                if (notisPemohonASB != null) {
                    LOG.info("notisPemohonASB 1 != null");
                    listNotisPemohonASB.add(notisPemohonASB);
                }
            }
        }
        if (listNotisPemohonASB.size() != count1) {
            LOG.info("Belum ade lg listNotisPemohonASB...");
            simpanNotisPemohonASB();
            listNotisPemohonASB = new ArrayList<Notis>();
            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonASB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                        LOG.info("19RekodSampaiTampalPC 2");
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                    }
                    if (notisPemohonASB != null) {
                        listNotisPemohonASB.add(notisPemohonASB);
                    }
                }
            }
        }

        if (listNotisPemohonASB.size() == count1) {
            LOG.info("listNotisPemohonASB.size() == count1");

            for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                if (permohonanPihak != null) {
                    Notis notisPemohonASB = null;
                    if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                        notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
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
                }
            }
        } else {
            LOG.info("else");
            show = false;
            addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
        }

    }

    public void simpanNotisPemohonASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Dokumen dokumenSEAP = null;

        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                dokumenSEAP = notisPenerimaanService.getDokumenBykod(idPermohonan, "SEAP");
                if (dokumenSEAP != null) {

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
                        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, pemohon.getPihak().getIdPihak());
                        if (permohonanPihak == null) {
                            permohonanPihak = notisPenerimaanService.simpanMohonPihak(permohonan, peng, pemohon.getPihak());
                        }
                        if (permohonanPihak != null) {
                            Notis notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
                            if (notisPemohonASB == null) {
                                Notis notis1 = new Notis();
                                notis1.setInfoAudit(info);
                                notis1.setPermohonan(permohonan);
                                notis1.setTarikhNotis(new java.util.Date());
                                notis1.setKodNotis(kodNotisDAO.findById("ASB"));
                                notis1.setPihak(permohonanPihak);
                                notis1.setDokumenNotis(dokumenSEAP);
                                notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis1);
                            }
                        }
                    }
                }
            }
        }
    }

    public void simpanNotis() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumenQ = null;
        Dokumen dokumen8MAN = null;
        Dokumen dokumenSDKM = null;
        Dokumen dokumen8MAS = null;
        Dokumen dokumen8MAJ = null;
        Dokumen dokumenM = null;
        Dokumen dokumenKMD = null;
        Dokumen dokumenSBPC = null;

        if (fasaPermohonan != null) {
            dokumenSBPC = notisPenerimaanService.getDokumenBykod(idPermohonan, "SBPC");
            if (dokumenSBPC != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        for (PermohonanPihak pp : permohonanPihakList) {
                        Notis notisRAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "RAB");
                        if (notisRAB == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("RAB"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumenSBPC);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
//                        }
                    }
                }
            }
            dokumenKMD = notisPenerimaanService.getDokumenBykod(idPermohonan, "KMD");
            if (dokumenKMD != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        for (PermohonanPihak pp : permohonanPihakList) {
                        Notis notisKMD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
                        if (notisKMD == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("KMD"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumenKMD);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
//                        }
                    }
                }
            }
            dokumen8MAN = notisPenerimaanService.getDokumenBykod(idPermohonan, "8MAN");
            dokumenSDKM = notisPenerimaanService.getDokumenBykod(idPermohonan, "SDKM");
            dokumen8MAS = notisPenerimaanService.getDokumenBykod(idPermohonan, "8MAS");
            dokumen8MAJ = notisPenerimaanService.getDokumenBykod(idPermohonan, "8MAJ");
            dokumenM = notisPenerimaanService.getDokumenBykod(idPermohonan, "M");

            if (dokumen8MAN != null && dokumenSDKM != null && dokumen8MAS != null && dokumen8MAJ != null && dokumenM != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
//                        for (PermohonanPihak pp : permohonanPihakList) {
                        Notis notisPAC = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAC");
                        if (notisPAC == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("PAC"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumen8MAN);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                        Notis notisPAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAB");
                        if (notisPAB == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("PAB"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumenSDKM);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                        Notis notisPAD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAD");
                        if (notisPAD == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("PAD"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumen8MAS);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                        Notis notisPAE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAE");
                        if (notisPAE == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("PAE"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumen8MAJ);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
                        Notis notisNBM = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBM");
                        if (notisNBM == null) {
                            Notis notis2 = new Notis();
                            notis2.setInfoAudit(info);
                            notis2.setPermohonan(p);
                            notis2.setTarikhNotis(new java.util.Date());
                            notis2.setKodNotis(kodNotisDAO.findById("NBM"));
                            notis2.setPihak(permohonanPihak);
                            notis2.setDokumenNotis(dokumenM);
                            notis2.setJabatan(p.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis2);
                        }
//                        }
                    }
                }
            }
            dokumenQ = notisPenerimaanService.getDokumenBykod(idPermohonan, "Q");
            if (dokumenQ != null) {

                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                for (HakmilikPermohonan hp : p.getSenaraiHakmilik()) {
                    for (HakmilikPihakBerkepentingan hakmilikPihak : hp.getHakmilik().getSenaraiPihakBerkepentingan()) {
//                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                        permohonanPihakList = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hp.getHakmilik().getIdHakmilik(), hakmilikPihak.getPihak().getIdPihak());
                        for (PermohonanPihak pp : permohonanPihakList) {
                            Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pp.getIdPermohonanPihak(), "NBQ");
                            if (notisNBQ == null) {
                                Notis notis2 = new Notis();
                                notis2.setInfoAudit(info);
                                notis2.setPermohonan(p);
                                notis2.setTarikhNotis(new java.util.Date());
                                notis2.setKodNotis(kodNotisDAO.findById("NBQ"));
                                notis2.setPihak(pp);
                                notis2.setDokumenNotis(dokumenQ);
                                notis2.setJabatan(p.getKodUrusan().getJabatan());
                                notisPenerimaanService.saveOrUpdateNotis(notis2);
                            }
                        }
                    }
                }
            }

        }
    }

    public void simpanNotisASB() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan p = permohonanDAO.findById(idPermohonan);

        Dokumen dokumen8JTK = null;

        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("09KMRekodSampaiTampal")) {
                dokumen8JTK = notisPenerimaanService.getDokumenBykod(idPermohonan, "8JTK");

                if (dokumen8JTK != null) {
                    LOG.info("dokumen8JTK != null");
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    notisNJT = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
                    LOG.info("notisNJT : " + notisNJT.size());
                    if (notisNJT.size() == 0) {
                        LOG.info("notisNJT == null");
                        permohonanRujukanLuarList = notisPenerimaanService.getMohonRujukLuarByIdMohon(idPermohonan);
                        for (PermohonanRujukanLuar pr : permohonanRujukanLuarList) {
                            LOG.info("list mohon rujuk luar : " + permohonanRujukanLuarList.size());
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(p);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("NJT"));
//                            notis1.setPihak(permohonanPihak);
                            notis1.setDokumenNotis(dokumen8JTK);
                            notis1.setJabatan(p.getKodUrusan().getJabatan());
                            notis1.setPenerimaNotis(pr.getNamaAgensi());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }
                    }
                }
            }
            rehydrate();
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
            showHP = true;
        }

        penghantarNotis = new PenghantarNotis();
        return new JSP("pengambilan/PenghantarNotis_popup.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(PenerimaanNotisBorangPTSPActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        InfoAudit info;

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "21RekodSampaiTampalPC");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "19RekodSampaiTampalPC");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampalPR");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "06RekodSampaiTampalPR");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "16RekodSampaiTampal");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "09KMRekodSampaiTampal");
        }

        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            showHP = true;
            hakmilik = hakmilikDAO.findById(idHakmilik);
            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("21RekodSampaiTampalPC")) {
                    for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                        Notis notisRAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "RAB");
                        if (notisRAB != null) {
                            info = notisRAB.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisRAB.setInfoAudit(info);
                            notisRAB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisRAB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisRAB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisRAB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisRAB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisRAB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisRAB);
                        }
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampalPR")) {
                    for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                        Notis notisKMD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "KMD");
                        if (notisKMD != null) {
                            info = notisKMD.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisKMD.setInfoAudit(info);
                            notisKMD.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisKMD.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisKMD.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisKMD.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisKMD.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisKMD.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisKMD);
                        }
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("06RekodSampaiTampalPR")) {
                    for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                        Notis notisPAB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAB");
                        Notis notisPAC = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAC");
                        Notis notisPAD = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAD");
                        Notis notisPAE = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PAE");
                        Notis notisNBM = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBM");
                        if (notisPAB != null) {
                            info = notisPAB.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisPAB.setInfoAudit(info);
                            notisPAB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisPAB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisPAB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisPAB.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisPAB.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisPAB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisPAB);
                        }
                        if (notisPAC != null) {
                            info = notisPAC.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisPAC.setInfoAudit(info);
                            notisPAC.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisPAC.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisPAC.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisPAC.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisPAC.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisPAC.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisPAC);
                        }
                        if (notisPAD != null) {
                            info = notisPAD.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisPAD.setInfoAudit(info);
                            notisPAD.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisPAD.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisPAD.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisPAD.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisPAD.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisPAD.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisPAD);
                        }
                        if (notisPAE != null) {
                            info = notisPAE.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisPAE.setInfoAudit(info);
                            notisPAE.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisPAE.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisPAE.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisPAE.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisPAE.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisPAE.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisPAE);
                        }
                        if (notisNBM != null) {
                            info = notisNBM.getInfoAudit();
                            info.setDiKemaskiniOleh(peng);
                            info.setTarikhKemaskini(new java.util.Date());
                            notisNBM.setInfoAudit(info);
                            notisNBM.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantar.get(i)));
                            notisNBM.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerima.get(i)));
                            notisNBM.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaran.get(i)));
                            notisNBM.setTarikhHantar(sdf.parse(tarikhHantar.get(i)));
                            notisNBM.setTarikhTerima(sdf.parse(tarikhTerima.get(i)));
                            notisNBM.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisNBM);
                        }
                    }

                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("16RekodSampaiTampal")) {
                    for (int i = 0; i < hakmilik.getSenaraiPihakBerkepentingan().size(); i++) {
                        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hakmilik.getSenaraiPihakBerkepentingan().get(i).getPihak().getIdPihak());
                        Notis notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");
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
                            notisASB.setCatatanPenerimaan(catatanPenerimaan.get(i));
                            notisPenerimaanService.saveOrUpdateNotis(notisASB);
                        }
                    }

                }
            }
        } else {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("09KMRekodSampaiTampal")) {
                showPP = true;
                notisNJT = notisPenerimaanService.getNotisByIdMohonkodNotis(idPermohonan, "NJT");
                for (int i = 0; i < notisNJT.size(); i++) {
                    if (notisNJT.get(i) != null) {
                        info = notisNJT.get(i).getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisNJT.get(i).setInfoAudit(info);
                        notisNJT.get(i).setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisNJT.get(i).setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisNJT.get(i).setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisNJT.get(i).setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisNJT.get(i).setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        notisNJT.get(i).setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisNJT.get(i));
                    }
                }
            } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("19RekodSampaiTampalPC")) {
                LOG.info(":: simpan 19RekodSampaiTampalPC :: ");
                showPemohon = true;
                notisASB1 = notisPenerimaanService.getNotisByIdMohonMPkodNotis(idPermohonan, "ASB");
                for (int i = 0; i < notisASB1.size(); i++) {
                    if (notisASB1.get(i) != null) {
                        info = notisASB1.get(i).getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                        notisASB1.get(i).setInfoAudit(info);
                        notisASB1.get(i).setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(i)));
                        notisASB1.get(i).setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(i)));
                        notisASB1.get(i).setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(i)));
                        notisASB1.get(i).setTarikhHantar(sdf.parse(tarikhHantarP.get(i)));
                        notisASB1.get(i).setTarikhTerima(sdf.parse(tarikhTerimaP.get(i)));
                        notisASB1.get(i).setCatatanPenerimaan(catatanPenerimaanP.get(i));
                        notisPenerimaanService.saveOrUpdateNotis(notisASB1.get(i));
                    }
                }
            }
        }

//        hakmilikDetails();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pengambilan/Penerimaan_Notis_Borang_ptsp.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");
        }


        Notis notisASB = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");


        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            fname = String.valueOf(notisASB.getIdNotis());
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
            fname = String.valueOf(notisPB.getIdNotis());
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
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
                    ia = notisASB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisASB.setInfoAudit(ia);
                    lelongService.updateNotis(notisASB);
                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
                    ia = notisPB.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                    notisPB.setInfoAudit(ia);
                    lelongService.updateNotis(notisPB);
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
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
    }

    public Resolution processUploadDocPemohon() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");
        }

        Notis notisPemohonASB = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        fname = String.valueOf(notisPemohonASB.getIdNotis());
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
                ia = notisPemohonASB.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                notisPemohonASB.setInfoAudit(ia);
                lelongService.updateNotis(notisPemohonASB);

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
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
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
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "12DrafSuratBorangQ");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");
        }

        Notis notisASB = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")) {
            notisASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }
        Notis notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PB");
        Notis notisNBQ = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "NBQ");

        InfoAudit ia = new InfoAudit();
        String fname = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
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
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan") || fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar") || fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat") || fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
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
        return new JSP("pengambilan/scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution popupScanPemohon() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        if (idHakmilik != null && !idHakmilik.isEmpty()) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        permohonanPihak = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, Long.parseLong(idPihak));

        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "24SediaSuratKeputusan");
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "15DrafSuratBayar");
        }
        if (fasaPermohonan == null) {
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "14SediaSurat");
        }

        Notis notisPemohonASB = null;
        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "SAC");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("15DrafSuratBayar")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "ASB");
        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("14SediaSurat")) {
            notisPemohonASB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, permohonanPihak.getIdPermohonanPihak(), "PBG");
        }

        InfoAudit ia = new InfoAudit();
        String fname = null;
        fname = String.valueOf(notisPemohonASB.getIdNotis());
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
                ia = notisPemohonASB.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisPemohonASB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                notisPemohonASB.setInfoAudit(ia);
                lelongService.updateNotis(notisPemohonASB);

            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/scan_doc.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/scan_doc.jsp").addParameter("tab", "true");
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
            showHP = true;
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


//        if (showHP.equalsIgnoreCase(true)) {
//            if (idHakmilik != null && !idHakmilik.isEmpty()) {
//                hakmilikDetails();
//            } else {
//                if (showPP == false) {
////                    showForm();
//                } else {
////                    showFormPP();
//                }
//            }
//        }
//        else {
////            showFormPemohon();
//        }
        addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
        return new JSP("pengambilan/Penerimaan_Notis_Borang.jsp").addParameter("tab", "true");
    }

//     public Resolution simpan() {
//
//        InfoAudit info = pengguna.getInfoAudit();
//        info.setDiKemaskiniOleh(pengguna);
//        info.setTarikhKemaskini(new java.util.Date());
//        for (int i = 0; i < listNotis.size(); i++) {
//            Notis nn = listNotis.get(i);
//            KodStatusTerima kodterime = new KodStatusTerima();
//            KodCaraPenghantaran kodHanta = new KodCaraPenghantaran();
//            kodterime.setKod(getContext().getRequest().getParameter("kodPenyampaian" + i));
//            kodHanta.setKod(getContext().getRequest().getParameter("kodPenghantaran" + i));
//            PenghantarNotis peng = penghantarNotisDAO.findById(Integer.parseInt(getContext().getRequest().getParameter("namaPengahantar" + i)));
//            nn.setPenghantarNotis(peng);
//            nn.setKodCaraPenghantaran(kodHanta);
//            nn.setKodStatusTerima(kodterime);
//            nn.setInfoAudit(info);
//            lelongService.saveOrUpdate(nn);
//        }
//        addSimpleMessage("Makluamt Telah Berjaya Disimpan..");
//        return new JSP("lelong/rekod_penghantaran_16H.jsp").addParameter("tab", "true");
//    }
    public Resolution popupUpload() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        isPemohonPihak = (String) getContext().getRequest().getParameter("isPemohonPihak");
        String PP = (String) getContext().getRequest().getParameter("showPP");
        if (PP != null && PP.equalsIgnoreCase("true")) {
            showPP = true;
        }
        String HP = (String) getContext().getRequest().getParameter("showHP");
        if (HP != null && HP.equalsIgnoreCase("true")) {
            showHP = true;
        }
//        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/upload_file.jsp").addParameter("popup", "true");
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

    public boolean isShowHP() {
        return showHP;
    }

    public void setShowHP(boolean showHP) {
        this.showHP = showHP;
    }

//    public String getShowHP() {
//        return showHP;
//    }
//
//    public void setShowHP(String showHP) {
//        this.showHP = showHP;
//    }
    public String getIsPemohonPihak() {
        return isPemohonPihak;
    }

    public void setIsPemohonPihak(String isPemohonPihak) {
        this.isPemohonPihak = isPemohonPihak;
    }

    public List<Notis> getListNotis8JTK() {
        return listNotis8JTK;
    }

    public void setListNotis8JTK(List<Notis> listNotis8JTK) {
        this.listNotis8JTK = listNotis8JTK;
    }

    public List<Notis> getListNotisPemohon8JTK() {
        return listNotisPemohon8JTK;
    }

    public void setListNotisPemohon8JTK(List<Notis> listNotisPemohon8JTK) {
        this.listNotisPemohon8JTK = listNotisPemohon8JTK;
    }

    public List<Notis> getListNotisPemohonNJT() {
        return listNotisPemohonNJT;
    }

    public void setListNotisPemohonNJT(List<Notis> listNotisPemohonNJT) {
        this.listNotisPemohonNJT = listNotisPemohonNJT;
    }

    public List<Notis> getListNotisNJT() {
        return listNotisNJT;
    }

    public void setListNotisNJT(List<Notis> listNotisNJT) {
        this.listNotisNJT = listNotisNJT;
    }

    public List<Notis> getListNotisPAB() {
        return listNotisPAB;
    }

    public void setListNotisPAB(List<Notis> listNotisPAB) {
        this.listNotisPAB = listNotisPAB;
    }

    public List<Notis> getListNotisPAC() {
        return listNotisPAC;
    }

    public void setListNotisPAC(List<Notis> listNotisPAC) {
        this.listNotisPAC = listNotisPAC;
    }

    public List<Notis> getListNotisPAD() {
        return listNotisPAD;
    }

    public void setListNotisPAD(List<Notis> listNotisPAD) {
        this.listNotisPAD = listNotisPAD;
    }

    public List<Notis> getListNotisPAE() {
        return listNotisPAE;
    }

    public void setListNotisPAE(List<Notis> listNotisPAE) {
        this.listNotisPAE = listNotisPAE;
    }

    public List<Notis> getListNotisNBM() {
        return listNotisNBM;
    }

    public void setListNotisNBM(List<Notis> listNotisNBM) {
        this.listNotisNBM = listNotisNBM;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }

    public List<Notis> getNotisNJT() {
        return notisNJT;
    }

    public void setNotisNJT(List<Notis> notisNJT) {
        this.notisNJT = notisNJT;
    }

    public List<Notis> getListNotisKMD() {
        return listNotisKMD;
    }

    public void setListNotisKMD(List<Notis> listNotisKMD) {
        this.listNotisKMD = listNotisKMD;
    }

    public boolean isShowPemohon() {
        return showPemohon;
    }

    public void setShowPemohon(boolean showPemohon) {
        this.showPemohon = showPemohon;
    }

    public List<Notis> getNotisASB1() {
        return notisASB1;
    }

    public void setNotisASB1(List<Notis> notisASB1) {
        this.notisASB1 = notisASB1;
    }

    public List<Notis> getListNotisRAB() {
        return listNotisRAB;
    }

    public void setListNotisRAB(List<Notis> listNotisRAB) {
        this.listNotisRAB = listNotisRAB;
    }
}
