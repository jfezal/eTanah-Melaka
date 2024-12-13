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
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.PermohonanMahkamah;
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.FasaPermohonanService;
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
@UrlBinding("/pengambilan/penerimaan_notis_borang_aduan")
public class PenerimaanNotisBorangAduanActionBean extends AbleActionBean {

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
    PengambilanService pengambilanService;
    @Inject
    etanah.Configuration conf;
    @Inject
    PengambilanAduanService aduanService;
    private Hakmilik hakmilik;
    private Notis notisMahkamahRPD;
    List<PermohonanMahkamah> senaraiPM = new ArrayList<PermohonanMahkamah>();
    //----------------------------------------------------------------//
    private List<Notis> listNotisPengadu = new ArrayList<Notis>();
    private List<Notis> listNotisPemohon = new ArrayList<Notis>();
    private List<Notis> listNotisJPPH = new ArrayList<Notis>();
    //-----------------------------Pemohon-----------------------------------//
    private List<Integer> namaPengahantarP = new ArrayList<Integer>();
    private List<String> kodStatusTerimaP = new ArrayList<String>();
    private List<String> kodPenghantaranP = new ArrayList<String>();
    private List<String> catatanPenerimaanP = new ArrayList<String>();
    private List<String> tarikhHantarP = new ArrayList<String>();
    private List<String> tarikhTerimaP = new ArrayList<String>();
    private List<String> idDokumenListP = new ArrayList<String>();
    //--------------------------Pengadu---------------------------------------//
    private List<Integer> namaPengahantarA = new ArrayList<Integer>();
    private List<String> kodStatusTerimaA = new ArrayList<String>();
    private List<String> kodPenghantaranA = new ArrayList<String>();
    private List<String> catatanPenerimaanA = new ArrayList<String>();
    private List<String> tarikhHantarA = new ArrayList<String>();
    private List<String> tarikhTerimaA = new ArrayList<String>();
    private List<String> idDokumenListA = new ArrayList<String>();
    //---------------------------JPPH---------------------------------------//
    private List<Integer> namaPengahantarJ = new ArrayList<Integer>();
    private List<String> kodStatusTerimaJ = new ArrayList<String>();
    private List<String> kodPenghantaranJ = new ArrayList<String>();
    private List<String> catatanPenerimaanJ = new ArrayList<String>();
    private List<String> tarikhHantarJ = new ArrayList<String>();
    private List<String> tarikhTerimaJ = new ArrayList<String>();
    private List<String> idDokumenListJ = new ArrayList<String>();
    //-------------------------------------------------------------------//
    private List<Notis> listNotisASB = new ArrayList<Notis>();
    private List<Notis> listNotisASBG = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonASBG = new ArrayList<Notis>();
    private List<Notis> listNotisPB = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonPB = new ArrayList<Notis>();
    private List<Notis> listNotisNBQ = new ArrayList<Notis>();
    private List<Notis> listNotisPemohonNBQ = new ArrayList<Notis>();
    private List<Notis> listNotisMahkamahRPD = new ArrayList<Notis>();
    private List<Integer> namaPengahantar = new ArrayList<Integer>();
    private List<String> kodStatusTerima = new ArrayList<String>();
    private List<String> kodPenghantaran = new ArrayList<String>();
    private List<String> catatanPenerimaan = new ArrayList<String>();
    private List<String> tarikhHantar = new ArrayList<String>();
    private List<String> tarikhTerima = new ArrayList<String>();
    private List<String> idDokumenList = new ArrayList<String>();
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
    private List<KandunganFolder> listKandunganFolder;
    private List<Dokumen> listDokumen;
    private String idNotis;
    FileBean fileToBeUploaded;
    private long idDokumen2;
    private PenghantarNotis penghantarNotis;
    private boolean show = true;
    private boolean showPP = false;
    private boolean showMM = false;
    private String showHP = "false";
    private String isPemohonPihak = "false";
    private String isMahkamah = "false";
    private String isJPPH = "false";
    private static final Logger LOG = Logger.getLogger(PenerimaanNotisBorangAduanActionBean.class);
    private List<PermohonanPihakTidakBerkepentingan> senaraipbt;
    private PermohonanPihakTidakBerkepentingan pbt;
    private List<Notis> listNotisPBT = new ArrayList<Notis>();
    private HakmilikPermohonan hakmilikPermohonan;
    private Pemohon pemohon;
    private Pemohon pemohonSebelum;
    private Permohonan pSebelum;
    private PermohonanPihak mpPemohon;
    private PermohonanPihak mpPengadu;

    @DefaultHandler
    public Resolution showFormPP() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            System.out.println("no permohonan : " + permohonan.getIdPermohonan());

            int count = 1;

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);

            LOG.info("permohonanSebelum" + permohonan.getPermohonanSebelum().getIdPermohonan());
            pemohonSebelum = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "10RekodSampaiTampal");
            listNotisPengadu = new ArrayList<Notis>();
            listNotisPemohon = new ArrayList<Notis>();
            listNotisJPPH = new ArrayList<Notis>();

            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("10RekodSampaiTampal")) {
//                    showPP = true;
                    //Pemohon Permohonan Sebelum

                    LOG.info("masuk sini");
                    LOG.info("pemohonSebelum" + pemohonSebelum.getPihak().getIdPihak());
                    LOG.info("permohonan.getIdPermohonan()" + permohonan.getIdPermohonan());
                    mpPemohon = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(permohonan.getIdPermohonan(), pemohonSebelum.getPihak().getIdPihak());
                    if (mpPemohon == null) {
                        mpPemohon = notisPenerimaanService.simpanMohonPihak(permohonan, pengguna, pemohonSebelum.getPihak());
                    }
                    Notis notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(permohonan.getIdPermohonan(), mpPemohon.getIdPermohonanPihak(), "PB");

                    if (notisPemohon != null) {
                        LOG.info("notisPemohon : dah ade");
                        listNotisPemohon.add(notisPemohon);
                    }

                    //Pengadu
                    Pemohon pmhn = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
//                    mpPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik(), pemohon.getPihak().getIdPihak());
                    mpPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pmhn.getPihak().getIdPihak());
                    Notis notisPengadu = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPengadu.getIdPermohonanPihak(), "PB");
                    if (notisPengadu != null) {
                        LOG.info("notisPengadu : dah ade");
                        listNotisPengadu.add(notisPengadu);
                    }

                    //JPPH
                    Notis notisJPPH = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");
                    if (notisJPPH != null) {
                        LOG.info("notisJPPH : dah ade");
                        listNotisJPPH.add(notisJPPH);
                    }


                    if (listNotisPemohon.size() != count && listNotisPengadu.size() != count && listNotisJPPH.size() != count) {
                        LOG.info("listNotisPemohon : Belum ade lg...");
                        simpanNotisPemohon();
                    }

                    if (listNotisPemohon.size() == count || listNotisPengadu.size() == count || listNotisJPPH.size() == count) {
                        Notis notisPBpemohon = new Notis();
                        Notis notisPBpengadu = new Notis();
                        Notis notisPBjpph = new Notis();
                        LOG.info("pemohonSebelum.getPihak().getIdPihak()" + pemohonSebelum.getPihak().getIdPihak());
                        notisPBpemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPemohon.getIdPermohonanPihak(), "PB");
                        notisPBpengadu = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPengadu.getIdPermohonanPihak(), "PB");
                        notisPBjpph = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");


                        if (notisPBpemohon != null) {
                            LOG.info("notisPBpemohon");
                            if (notisPBpemohon.getPenghantarNotis() != null) {
                                namaPengahantarP.add(notisPBpemohon.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBpemohon.getKodStatusTerima() != null) {
                                kodStatusTerimaP.add(notisPBpemohon.getKodStatusTerima().getKod());
                            }
                            if (notisPBpemohon.getKodCaraPenghantaran() != null) {
                                kodPenghantaranP.add(notisPBpemohon.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanP.add(notisPBpemohon.getCatatanPenerimaan());
                            if (notisPBpemohon.getTarikhHantar() != null) {
                                tarikhHantarP.add(sdf.format(notisPBpemohon.getTarikhHantar()));
                            }
                            if (notisPBpemohon.getTarikhTerima() != null) {
                                tarikhTerimaP.add(sdf.format(notisPBpemohon.getTarikhTerima()));
                            }
                            if (notisPBpemohon.getBuktiPenerimaan() == null) {
                                idDokumenListP.add("");
                            } else {
                                idDokumenListP.add(String.valueOf(notisPBpemohon.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }
                        if (notisPBpengadu != null) {
                            if (notisPBpengadu.getPenghantarNotis() != null) {
                                namaPengahantarA.add(notisPBpengadu.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBpengadu.getKodStatusTerima() != null) {
                                kodStatusTerimaA.add(notisPBpengadu.getKodStatusTerima().getKod());
                            }
                            if (notisPBpengadu.getKodCaraPenghantaran() != null) {
                                kodPenghantaranA.add(notisPBpengadu.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanA.add(notisPBpengadu.getCatatanPenerimaan());
                            if (notisPBpengadu.getTarikhHantar() != null) {
                                tarikhHantarA.add(sdf.format(notisPBpengadu.getTarikhHantar()));
                            }
                            if (notisPBpengadu.getTarikhTerima() != null) {
                                tarikhTerimaA.add(sdf.format(notisPBpengadu.getTarikhTerima()));
                            }
                            if (notisPBpengadu.getBuktiPenerimaan() == null) {
                                idDokumenListA.add("");
                            } else {
                                idDokumenListA.add(String.valueOf(notisPBpengadu.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }
                        if (notisPBjpph != null) {
                            if (notisPBjpph.getPenghantarNotis() != null) {
                                namaPengahantarJ.add(notisPBjpph.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBjpph.getKodStatusTerima() != null) {
                                kodStatusTerimaJ.add(notisPBjpph.getKodStatusTerima().getKod());
                            }
                            if (notisPBjpph.getKodCaraPenghantaran() != null) {
                                kodPenghantaranJ.add(notisPBjpph.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanJ.add(notisPBjpph.getCatatanPenerimaan());
                            if (notisPBjpph.getTarikhHantar() != null) {
                                tarikhHantarJ.add(sdf.format(notisPBjpph.getTarikhHantar()));
                            }
                            if (notisPBjpph.getTarikhTerima() != null) {
                                tarikhTerimaJ.add(sdf.format(notisPBjpph.getTarikhTerima()));
                            }
                            if (notisPBjpph.getBuktiPenerimaan() == null) {
                                idDokumenListJ.add("");
                            } else {
                                idDokumenListJ.add(String.valueOf(notisPBjpph.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }

                    } else {
                        addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
                    }

                }
            }
        }
        return new JSP("pengambilan/notis_aduan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//        }
        showHP = "true";
        System.out.println("1");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            System.out.println("no permohonan : " + permohonan.getIdPermohonan());

            int count = 1;

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);

            LOG.info("permohonanSebelum" + permohonan.getPermohonanSebelum().getIdPermohonan());
            pemohonSebelum = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
            fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "10RekodSampaiTampal");
            listNotisPengadu = new ArrayList<Notis>();
            listNotisPemohon = new ArrayList<Notis>();
            listNotisJPPH = new ArrayList<Notis>();

            if (fasaPermohonan != null) {
                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("10RekodSampaiTampal")) {
//                    showPP = true;
                    //Pemohon Permohonan Sebelum

                    LOG.info("masuk sini");
                    LOG.info("pemohonSebelum" + pemohonSebelum.getPihak().getIdPihak());
                    LOG.info("permohonan.getIdPermohonan()" + permohonan.getIdPermohonan());
                    mpPemohon = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(permohonan.getIdPermohonan(), pemohonSebelum.getPihak().getIdPihak());
                    if (mpPemohon == null) {
                        mpPemohon = notisPenerimaanService.simpanMohonPihak(permohonan, pengguna, pemohonSebelum.getPihak());
                    }
                    Notis notisPemohon = notisPenerimaanService.getNotisByidMPkodNotis(permohonan.getIdPermohonan(), mpPemohon.getIdPermohonanPihak(), "PB");

                    if (notisPemohon != null) {
                        LOG.info("notisPemohon : dah ade");
                        listNotisPemohon.add(notisPemohon);
                    }

                    //Pengadu
//                    mpPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik(), pemohonSebelum.getPihak().getIdPihak());
                    mpPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pemohonSebelum.getPihak().getIdPihak());
                    LOG.info("mpPengadu - " + mpPengadu);
                    Notis notisPengadu = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPengadu.getIdPermohonanPihak(), "PB");
                    if (notisPengadu != null) {
                        LOG.info("notisPengadu : dah ade");
                        listNotisPengadu.add(notisPengadu);
                    }

                    //JPPH
                    Notis notisJPPH = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");
                    if (notisJPPH != null) {
                        LOG.info("notisJPPH : dah ade");
                        listNotisJPPH.add(notisJPPH);
                    }


                    if (listNotisPemohon.size() != count && listNotisPengadu.size() != count && listNotisJPPH.size() != count) {
                        LOG.info("listNotisPemohon : Belum ade lg...");
                        simpanNotisPemohon();
                    }

                    if (listNotisPemohon.size() == count && listNotisPengadu.size() == count && listNotisJPPH.size() == count) {
                        Notis notisPBpemohon = new Notis();
                        Notis notisPBpengadu = new Notis();
                        Notis notisPBjpph = new Notis();
                        LOG.info("pemohonSebelum.getPihak().getIdPihak()" + pemohonSebelum.getPihak().getIdPihak());
                        notisPBpemohon = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPemohon.getIdPermohonanPihak(), "PB");
                        notisPBpengadu = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPengadu.getIdPermohonanPihak(), "PB");
                        notisPBjpph = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");


                        if (notisPBpemohon != null) {
                            LOG.info("notisPBpemohon");
                            if (notisPBpemohon.getPenghantarNotis() != null) {
                                namaPengahantarP.add(notisPBpemohon.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBpemohon.getKodStatusTerima() != null) {
                                kodStatusTerimaP.add(notisPBpemohon.getKodStatusTerima().getKod());
                            }
                            if (notisPBpemohon.getKodCaraPenghantaran() != null) {
                                kodPenghantaranP.add(notisPBpemohon.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanP.add(notisPBpemohon.getCatatanPenerimaan());
                            if (notisPBpemohon.getTarikhHantar() != null) {
                                tarikhHantarP.add(sdf.format(notisPBpemohon.getTarikhHantar()));
                            }
                            if (notisPBpemohon.getTarikhTerima() != null) {
                                tarikhTerimaP.add(sdf.format(notisPBpemohon.getTarikhTerima()));
                            }
                            if (notisPBpemohon.getBuktiPenerimaan() == null) {
                                idDokumenListP.add("");
                            } else {
                                idDokumenListP.add(String.valueOf(notisPBpemohon.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }
                        if (notisPBpengadu != null) {
                            if (notisPBpengadu.getPenghantarNotis() != null) {
                                namaPengahantarA.add(notisPBpengadu.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBpengadu.getKodStatusTerima() != null) {
                                kodStatusTerimaA.add(notisPBpengadu.getKodStatusTerima().getKod());
                            }
                            if (notisPBpengadu.getKodCaraPenghantaran() != null) {
                                kodPenghantaranA.add(notisPBpengadu.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanA.add(notisPBpengadu.getCatatanPenerimaan());
                            if (notisPBpengadu.getTarikhHantar() != null) {
                                tarikhHantarA.add(sdf.format(notisPBpengadu.getTarikhHantar()));
                            }
                            if (notisPBpengadu.getTarikhTerima() != null) {
                                tarikhTerimaA.add(sdf.format(notisPBpengadu.getTarikhTerima()));
                            }
                            if (notisPBpengadu.getBuktiPenerimaan() == null) {
                                idDokumenListA.add("");
                            } else {
                                idDokumenListA.add(String.valueOf(notisPBpengadu.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }
                        if (notisPBjpph != null) {
                            if (notisPBjpph.getPenghantarNotis() != null) {
                                namaPengahantarJ.add(notisPBjpph.getPenghantarNotis().getIdPenghantarNotis());
                            }
                            if (notisPBjpph.getKodStatusTerima() != null) {
                                kodStatusTerimaJ.add(notisPBjpph.getKodStatusTerima().getKod());
                            }
                            if (notisPBjpph.getKodCaraPenghantaran() != null) {
                                kodPenghantaranJ.add(notisPBjpph.getKodCaraPenghantaran().getKod());
                            }
                            catatanPenerimaanJ.add(notisPBjpph.getCatatanPenerimaan());
                            if (notisPBjpph.getTarikhHantar() != null) {
                                tarikhHantarJ.add(sdf.format(notisPBjpph.getTarikhHantar()));
                            }
                            if (notisPBjpph.getTarikhTerima() != null) {
                                tarikhTerimaJ.add(sdf.format(notisPBjpph.getTarikhTerima()));
                            }
                            if (notisPBjpph.getBuktiPenerimaan() == null) {
                                idDokumenListJ.add("");
                            } else {
                                idDokumenListJ.add(String.valueOf(notisPBjpph.getBuktiPenerimaan().getIdDokumen()));
                            }
                        }

                    } else {
                        addSimpleError("Dokumen Belum Dijana.Sila Jana Dokumen Terlebih Dahulu..");
                    }

                }
            }
        }
    }

    //Aduan Punya Simpan Pemohon Sebelum
    public void simpanNotisPemohon() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan p = permohonanDAO.findById(idPermohonan);


        Dokumen dokumenPB = null;
        System.out.println("Simpan Notis Panggil Bicara");
        if (fasaPermohonan != null) {

            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("10RekodSampaiTampal")) {
                System.out.println("1");
                dokumenPB = notisPenerimaanService.getDokumenBykod(idPermohonan, "PB");
                if (dokumenPB != null) {
                    System.out.println("2");
                    Notis notisPemohonPB = null;
                    Notis notisPB = null;
                    Notis notisE = null;

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());

                    Pemohon pmhn = aduanService.findPemohonByIdMohon(p.getPermohonanSebelum().getIdPermohonan());
                    PermohonanPihak pPemohon = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, p.getPermohonanSebelum().getSenaraiPemohon().get(0).getPihak().getIdPihak());
//                        PermohonanPihak pPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik(), pemohon.getPihak().getIdPihak());
                    PermohonanPihak pPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pmhn.getPihak().getIdPihak());
                    if (pPemohon != null) {
                        System.out.println("3");
                        notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pPemohon.getIdPermohonanPihak(), "PB");

                        if (notisPemohonPB == null) {
                            System.out.println("4");
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(permohonan);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("PB"));
                            notis1.setPihak(pPemohon);
                            notis1.setDokumenNotis(dokumenPB);
                            notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }
                    }
                    if (pPengadu != null) {
                        System.out.println("5");
                        notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pPengadu.getIdPermohonanPihak(), "PB");
                        notisE = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");

                        if (notisPB == null) {
                            System.out.println("6");
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(permohonan);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("PB"));
                            notis1.setPihak(pPengadu);
                            notis1.setDokumenNotis(dokumenPB);
                            notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }

                        if (notisE == null) {
                            System.out.println("7");
                            Notis notis1 = new Notis();
                            notis1.setInfoAudit(info);
                            notis1.setPermohonan(permohonan);
                            notis1.setTarikhNotis(new java.util.Date());
                            notis1.setKodNotis(kodNotisDAO.findById("PB"));
                            notis1.setDokumenNotis(dokumenPB);
                            notis1.setPenerimaNotis("JPPH");
                            notis1.setJabatan(permohonan.getKodUrusan().getJabatan());
                            notisPenerimaanService.saveOrUpdateNotis(notis1);
                        }
                    }

                }
                showFormPP();
            }

        }
    }

    public Resolution refreshpage() {
        rehydrate();

        return new RedirectResolution(PenerimaanNotisBorangAduanActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        InfoAudit info;

        pemohonSebelum = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
        fasaPermohonan = notisPenerimaanService.getFasaPermohonan(idPermohonan, "10RekodSampaiTampal");
        Notis notisPemohonPB = null;
        Notis notisPB = null;
        Notis notisE = null;
        listNotisPengadu = new ArrayList<Notis>();
        listNotisPemohon = new ArrayList<Notis>();
        listNotisJPPH = new ArrayList<Notis>();

        if (fasaPermohonan != null) {
            if (fasaPermohonan.getIdAliran().equalsIgnoreCase("10RekodSampaiTampal")) {

                Pemohon pmhn = aduanService.findPemohonByIdMohon(permohonan.getPermohonanSebelum().getIdPermohonan());
                PermohonanPihak pPemohon = notisPenerimaanService.getMohonPihakByIdMohonIdPihak(idPermohonan, permohonan.getPermohonanSebelum().getSenaraiPemohon().get(0).getPihak().getIdPihak());
//                PermohonanPihak pPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik(), pemohonSebelum.getPihak().getIdPihak());
                mpPengadu = notisPenerimaanService.getSenaraiPmohonPihakByIdPihak(idPermohonan, pmhn.getPihak().getIdPihak());
                notisPemohonPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, pPemohon.getIdPermohonanPihak(), "PB");
                notisPB = notisPenerimaanService.getNotisByidMPkodNotis(idPermohonan, mpPengadu.getIdPermohonanPihak(), "PB");
                notisE = notisPenerimaanService.getNotisByidMPJPPH(idPermohonan, "PB");

                if (notisPemohonPB != null) {
                    info = notisPemohonPB.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    notisPemohonPB.setInfoAudit(info);
                    notisPemohonPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarP.get(0)));
                    notisPemohonPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaP.get(0)));
                    notisPemohonPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranP.get(0)));
                    notisPemohonPB.setTarikhHantar(sdf.parse(tarikhHantarP.get(0)));
                    notisPemohonPB.setTarikhTerima(sdf.parse(tarikhTerimaP.get(0)));
                    try {
                        notisPemohonPB.setCatatanPenerimaan(catatanPenerimaanP.get(0));
                    } catch (Exception f) {
                    }
                    notisPenerimaanService.saveOrUpdateNotis(notisPemohonPB);
                    listNotisPengadu.add(notisPemohonPB);
                }

                if (notisPB != null) {
                    info = notisPB.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    notisPB.setInfoAudit(info);
                    notisPB.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarA.get(0)));
                    notisPB.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaA.get(0)));
                    notisPB.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranA.get(0)));
                    try {
                        notisPB.setTarikhHantar(sdf.parse(tarikhHantarA.get(0)));
                        notisPB.setTarikhTerima(sdf.parse(tarikhTerimaA.get(0)));
                    } catch (Exception h) {
                    }
                    try {
                        notisPB.setCatatanPenerimaan(catatanPenerimaanA.get(0));
                    } catch (Exception f) {
                    }
                    notisPenerimaanService.saveOrUpdateNotis(notisPB);
                    listNotisPengadu.add(notisPB);
                }
                if (notisE != null) {
                    info = notisE.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    notisE.setInfoAudit(info);
                    notisE.setPenghantarNotis(penghantarNotisDAO.findById(namaPengahantarJ.get(0)));
                    notisE.setKodStatusTerima(KodStatusTerimaDAO.findById(kodStatusTerimaJ.get(0)));
                    notisE.setKodCaraPenghantaran(kodCaraPenghantaranDAO.findById(kodPenghantaranJ.get(0)));
                    try {
                        notisE.setTarikhHantar(sdf.parse(tarikhHantarJ.get(0)));
                        notisE.setTarikhTerima(sdf.parse(tarikhTerimaJ.get(0)));
                    } catch (Exception h) {
                    }
                    try {
                        notisE.setCatatanPenerimaan(catatanPenerimaanJ.get(0));
                    } catch (Exception f) {
                    }
                    notisPenerimaanService.saveOrUpdateNotis(notisE);
                    listNotisJPPH.add(notisE);
                }


            }
        }
        rehydrate();
        return new JSP("pengambilan/notis_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idNotis = (String) getContext().getRequest().getParameter("idNotis");


        Notis notisAduan = notisPenerimaanService.getNotisByidNotisAduan(Long.parseLong(idNotis));
        LOG.debug("id Notis" + idNotis);
//        LOG.debug("notisAduan"+notisAduan.getPihak().getIdPermohonanPihak());

        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
        fname = idNotis;
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
//                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("48RekodBuktiSampai")) {
                ia = notisAduan.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisAduan.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
                notisAduan.setInfoAudit(ia);
                lelongService.updateNotis(notisAduan);
//                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("43RekodBuktiSampai")) {
//                    ia = notisPB.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
//                    notisPB.setInfoAudit(ia);
//                    lelongService.updateNotis(notisPB);
//                    ia = notisPB.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
//                    notisPB.setInfoAudit(ia);
//                    lelongService.updateNotis(notisNBQ);
//                } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("27RekodBuktiSampai")) {
//                    ia = notisNBQ.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
//                    notisNBQ.setInfoAudit(ia);
//                    lelongService.updateNotis(notisNBQ);
//                    ia = notisNBQ.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDoc));
//                    notisNBQ.setInfoAudit(ia);
//                    lelongService.updateNotis(notisNBQ);
//                }
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
        return new JSP("pengambilan/Aduan/uploadFile_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupScan() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idNotis = (String) getContext().getRequest().getParameter("idNotis");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, Long.parseLong(idPihak));


        Notis notisAduan = notisPenerimaanService.getNotisByidNotisAduan(Long.parseLong(idNotis));

        InfoAudit ia = new InfoAudit();
        String fname = null;
        fname = idNotis;
//        if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
//            fname = String.valueOf(notisAduan.getIdNotis());
//        } else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
//            fname = String.valueOf(notisPB.getIdNotis());
//        }
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
//                if (fasaPermohonan.getIdAliran().equalsIgnoreCase("24SediaSuratKeputusan")) {
                ia = notisAduan.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                notisAduan.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
                notisAduan.setInfoAudit(ia);
                lelongService.updateNotis(notisAduan);
//                }
//                else if (fasaPermohonan.getIdAliran().equalsIgnoreCase("12DrafSuratBorangQ")) {
//                    ia = notisPB.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisPB.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
//                    notisPB.setInfoAudit(ia);
//                    lelongService.updateNotis(notisPB);
//                    ia = notisNBQ.getInfoAudit();
//                    ia.setDiKemaskiniOleh(p);
//                    ia.setTarikhKemaskini(new java.util.Date());
//                    notisNBQ.setBuktiPenerimaan(dokumenDAO.findById(idDokumen2));
//                    notisNBQ.setInfoAudit(ia);
//                    lelongService.updateNotis(notisNBQ);
//                }

            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/Aduan/scan_doc_notis.jsp").addParameter("popup", "true");
    }

    public Resolution popupUpload() {
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = (String) getContext().getRequest().getParameter("idPihak");
        idNotis = (String) getContext().getRequest().getParameter("idNotis");
        isPemohonPihak = (String) getContext().getRequest().getParameter("isPemohonPihak");
        isJPPH = (String) getContext().getRequest().getParameter("isJPPH");

//        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/Aduan/uploadFile_notis.jsp").addParameter("popup", "true");
    }

    public Resolution reload() {
        rehydrate();
        return new JSP("pengambilan/Aduan/scan_doc_notis.jsp").addParameter("tab", "true");
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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getpSebelum() {
        return pSebelum;
    }

    public void setpSebelum(Permohonan pSebelum) {
        this.pSebelum = pSebelum;
    }

    public List<Notis> getListNotisPemohon() {
        return listNotisPemohon;
    }

    public void setListNotisPemohon(List<Notis> listNotisPemohon) {
        this.listNotisPemohon = listNotisPemohon;
    }

    public List<Notis> getListNotisPengadu() {
        return listNotisPengadu;
    }

    public void setListNotisPengadu(List<Notis> listNotisPengadu) {
        this.listNotisPengadu = listNotisPengadu;
    }

    public List<Notis> getListNotisJPPH() {
        return listNotisJPPH;
    }

    public void setListNotisJPPH(List<Notis> listNotisJPPH) {
        this.listNotisJPPH = listNotisJPPH;
    }

    public PermohonanPihak getMpPemohon() {
        return mpPemohon;
    }

    public void setMpPemohon(PermohonanPihak mpPemohon) {
        this.mpPemohon = mpPemohon;
    }

    public PermohonanPihak getMpPengadu() {
        return mpPengadu;
    }

    public void setMpPengadu(PermohonanPihak mpPengadu) {
        this.mpPengadu = mpPengadu;
    }

    public Pemohon getPemohonSebelum() {
        return pemohonSebelum;
    }

    public void setPemohonSebelum(Pemohon pemohonSebelum) {
        this.pemohonSebelum = pemohonSebelum;
    }

    public List<String> getCatatanPenerimaanA() {
        return catatanPenerimaanA;
    }

    public void setCatatanPenerimaanA(List<String> catatanPenerimaanA) {
        this.catatanPenerimaanA = catatanPenerimaanA;
    }

    public List<String> getCatatanPenerimaanJ() {
        return catatanPenerimaanJ;
    }

    public void setCatatanPenerimaanJ(List<String> catatanPenerimaanJ) {
        this.catatanPenerimaanJ = catatanPenerimaanJ;
    }

    public List<String> getIdDokumenListA() {
        return idDokumenListA;
    }

    public void setIdDokumenListA(List<String> idDokumenListA) {
        this.idDokumenListA = idDokumenListA;
    }

    public List<String> getIdDokumenListJ() {
        return idDokumenListJ;
    }

    public void setIdDokumenListJ(List<String> idDokumenListJ) {
        this.idDokumenListJ = idDokumenListJ;
    }

    public List<String> getKodPenghantaranA() {
        return kodPenghantaranA;
    }

    public void setKodPenghantaranA(List<String> kodPenghantaranA) {
        this.kodPenghantaranA = kodPenghantaranA;
    }

    public List<String> getKodPenghantaranJ() {
        return kodPenghantaranJ;
    }

    public void setKodPenghantaranJ(List<String> kodPenghantaranJ) {
        this.kodPenghantaranJ = kodPenghantaranJ;
    }

    public List<String> getKodStatusTerimaA() {
        return kodStatusTerimaA;
    }

    public void setKodStatusTerimaA(List<String> kodStatusTerimaA) {
        this.kodStatusTerimaA = kodStatusTerimaA;
    }

    public List<String> getKodStatusTerimaJ() {
        return kodStatusTerimaJ;
    }

    public void setKodStatusTerimaJ(List<String> kodStatusTerimaJ) {
        this.kodStatusTerimaJ = kodStatusTerimaJ;
    }

    public List<Integer> getNamaPengahantarA() {
        return namaPengahantarA;
    }

    public void setNamaPengahantarA(List<Integer> namaPengahantarA) {
        this.namaPengahantarA = namaPengahantarA;
    }

    public List<Integer> getNamaPengahantarJ() {
        return namaPengahantarJ;
    }

    public void setNamaPengahantarJ(List<Integer> namaPengahantarJ) {
        this.namaPengahantarJ = namaPengahantarJ;
    }

    public List<String> getTarikhHantarA() {
        return tarikhHantarA;
    }

    public void setTarikhHantarA(List<String> tarikhHantarA) {
        this.tarikhHantarA = tarikhHantarA;
    }

    public List<String> getTarikhHantarJ() {
        return tarikhHantarJ;
    }

    public void setTarikhHantarJ(List<String> tarikhHantarJ) {
        this.tarikhHantarJ = tarikhHantarJ;
    }

    public List<String> getTarikhTerimaA() {
        return tarikhTerimaA;
    }

    public void setTarikhTerimaA(List<String> tarikhTerimaA) {
        this.tarikhTerimaA = tarikhTerimaA;
    }

    public List<String> getTarikhTerimaJ() {
        return tarikhTerimaJ;
    }

    public void setTarikhTerimaJ(List<String> tarikhTerimaJ) {
        this.tarikhTerimaJ = tarikhTerimaJ;
    }

    public String getIsJPPH() {
        return isJPPH;
    }

    public void setIsJPPH(String isJPPH) {
        this.isJPPH = isJPPH;
    }
}
