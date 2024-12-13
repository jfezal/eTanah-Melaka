///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusUlasanJabatanTeknikalDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.KodDokumenAgensi;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKementerian;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.KodUrusanAgensi;
import etanah.model.KonfigurasiSistem;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PortalPengguna;
import etanah.model.SuratRujukanLuar;
import etanah.model.UlasanJabatanTeknikal;
import etanah.report.ReportUtil;
import etanah.service.JTeknikalService;
import etanah.service.PengambilanService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.PermohonanService;
import etanah.view.ListUtil;
import etanah.view.penguatkuasaan.JabatanTeknikal2ActionBean;
import etanah.view.uam.MailService;
import java.util.Calendar;
import java.util.Vector;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author w.fairul
 * @edited by wazer
 */
@UrlBinding("/penguatkuasaan/sedia_jabatan")
public class JabatanTeknikalActionBean extends AbleActionBean {

    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    RegService regService;
    @Inject
    NotaService notaService;
    @Inject
    ListUtil listUtil;
    @Inject
    PermohonanRujukanLuarSalinan senaraiRujukanLuarDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    PermohonanRujukanLuarSalinanDAO permohonanRujukanLuarSalinanDAO;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanService permohonanService;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    @Inject
    JTeknikalService jTeknikalService;
    @Inject
    KodStatusUlasanJabatanTeknikalDAO kodStatusUlasanJabatanTeknikalDAO;
    @Inject
    PengambilanService pengambilanService;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String idPermohonan;
    private String tarikhPermohonan;
    private String kodMohon_ruj_luar;
    private Permohonan permohonan;
    private List<KodAgensi> listKodAgensi;
    private List<KodAgensi> listKodAgensiAdun;
    private List<FolderDokumen> listFolderDokumen;
    private List<KandunganFolder> listKandunganFolder;
    // hidden fields
    private String kod;
    private String kodAgensiNama;
    // New kodAgensi details
    private KodAgensi kodAgensi;
    private KodNegeri kodNegeri;
    private String radio_;
    private String index;
    private String recordCount;
    private List<PermohonanRujukanLuarSalinan> prs;
    private List<PermohonanRujukanLuarDokumen> prsd;
    private PermohonanRujukanLuarSalinan mohonRujukLuarSalinan;
    private PermohonanRujukanLuarDokumen pdok;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanRujukanLuarDokumen> senaraiRujukanDok;
    private List<KodUrusanAgensi> kodUrusanAgensi;
    private List<KodAgensi> kodAgensis = new ArrayList<KodAgensi>();
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuar mohonRujLuar;
    private PermohonanLaporanPelan permohonanLaporPelan;
    private FasaPermohonan fasaPermohonan;
    private String rowCount1;
    private String kategoriAgensi;
    private String Penyelerasan;
    private String syor;
    private String kNegeri;
    private String namaJabatan;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna pguna;
    private String stageId;
    private FolderDokumen folderDokumen;
    private String workflowId;
    @Inject
    private ReportUtil reportUtil;
    private String idFolder;
    private String idRujukan;
    private String idSalinan;
    private String idDokumen;
    private String mandatori;
    private String idMohonRujLuar;
    private List<KodKementerian> senaraiKodKementerian;
    private String kodKatgAgensi;
    private String catatan;
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private String ditundatangan;
    private String ditundatangan1;
    private List<SuratRujukanLuar> suratRujukanLuartemp;
    private SuratRujukanLuar suratRujukanLuar;
    private static int day;
    private Date tarikhHantar;
    private Date tarikhJangkaTerima;
    private int jangkaMasa_hari;
    private int i;
    private PermohonanRujukanLuar pprl;
    private String kodRujukan;
    private int sizeKod;
    private List<Pengguna> penggunaList;
    private List<KodDUN> listAdun = new ArrayList<KodDUN>();
    private Vector namaKawasan = new Vector();
    private String[] kodAgensiDefined ;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<KodUrusanAgensi> listKUA = pelupusanService.findKodUrusanAgensiByKodUrusan(permohonan.getKodUrusan().getKod());
        if (listKUA.size() > 0) {
            settingDefaultAgensi(listKUA, permohonan);
        }
        if (idPermohonan != null) {
            senaraiRujukanLuar = pengambilanService.getSenaraiRujLuarByIDPermohonanAgensi1(idPermohonan);
            if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                    for (KodUrusanAgensi kua : listKUA) {
                        if (kua.getKodAgensi().getKod().equals(prl.getAgensi().getKod())) {
                            settingDokumen(prl, kua.getKodAgensi().getKod());
                            break;
                        }
                    }
                }
            }
        }
        return new JSP("/penguatkuasaan/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution showFormViewOnly() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<KodUrusanAgensi> listKUA = pelupusanService.findKodUrusanAgensiByKodUrusan(permohonan.getKodUrusan().getKod());
        if (listKUA.size() > 0) {
            settingDefaultAgensi(listKUA, permohonan);
        }
        if (idPermohonan != null) {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
            if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                    for (KodUrusanAgensi kua : listKUA) {
                        if (kua.getKodAgensi().getKod().equals(prl.getAgensi().getKod())) {
                            settingDokumen(prl, kua.getKodAgensi().getKod());
                            break;
                        }
                    }
                }
            }
        }
        return new JSP("/penguatkuasaan/papar_jabatan_teknikal.jsp").addParameter("tab", "true");
    }
    
    public Resolution showFormPHLP() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<KodUrusanAgensi> listKUA = pelupusanService.findKodUrusanAgensiByKodUrusan(permohonan.getKodUrusan().getKod());
        if (listKUA.size() > 0) {
            settingDefaultAgensi(listKUA, permohonan);
        }
        if (idPermohonan != null) {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
            if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                    for (KodUrusanAgensi kua : listKUA) {
                        if (kua.getKodAgensi().getKod().equals(prl.getAgensi().getKod())) {
                            settingDokumen(prl, kua.getKodAgensi().getKod());
                            break;
                        }
                    }
                }
            }
        }
        return new JSP("/penguatkuasaan/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public void settingDefaultAgensi(List<KodUrusanAgensi> listKUA, Permohonan permohonan) {
        for (KodUrusanAgensi kua : listKUA) {
            //String stringAgensi = new String("6006");
            String stringAgensi = kua.getKodAgensi().getKod();
            permohonanRujukanLuar = pelupusanService.findIDPermohonanRujByIdPermohonanNKodAgensi(permohonan.getIdPermohonan(), stringAgensi);
            InfoAudit info = new InfoAudit();
            if (permohonanRujukanLuar != null) {
                info = permohonanRujukanLuar.getInfoAudit();
                info.setDiKemaskiniOleh(pguna);
                info.setTarikhKemaskini(new java.util.Date());

            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                info = new InfoAudit();
                info.setDimasukOleh(pguna);
                info.setTarikhMasuk(new java.util.Date());
                permohonanRujukanLuar.setTempohHari(14);
                KodRujukan kodRuj = new KodRujukan();
                kodRuj = kodRujukanDAO.findById("FL");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                permohonanRujukanLuar.setTarikhDisampai(new java.util.Date());
                permohonanRujukanLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                permohonanRujukanLuar.setUlasanMandatori("T");
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                KodAgensi kodAgensi = new KodAgensi();
                kodAgensi = kodAgensiDAO.findById(stringAgensi);
                permohonanRujukanLuar.setAgensi(kodAgensi);
                permohonanRujukanLuar.setNamaAgensi(kodAgensi.getNama());
                if (permohonan.getKodUrusan().getKod().equals("PHLA")) {
                    permohonanRujukanLuar.setNamaSidang("Nilaian");
                }
            }
            permohonanRujukanLuar.setInfoAudit(info);
            pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);
        }
    }

    public void settingDokumen(PermohonanRujukanLuar mohonRujLuar, String kodAgensi) {
        listKandunganFolder = new ArrayList<KandunganFolder>();
        List<PermohonanRujukanLuarDokumen> listmohonRujLuarDokTemp = new ArrayList<PermohonanRujukanLuarDokumen>();
        listmohonRujLuarDokTemp = pelupusanService.findByIdMohonForMRLDok(mohonRujLuar.getIdRujukan());
        folderDokumen = permohonan.getFolderDokumen();
        List<KandunganFolder> listKandunganFolderTemp = folderDokumen.getSenaraiKandungan();
        if (folderDokumen != null) {
            if (listKandunganFolderTemp.size() > 0) {
                if (!listmohonRujLuarDokTemp.isEmpty()) {
                    for (KandunganFolder kf : listKandunganFolderTemp) {
                        boolean checkExist = false;
                        for (PermohonanRujukanLuarDokumen prld : listmohonRujLuarDokTemp) {
                            LOG.info(prld.getDokumen().getKodDokumen().getKod());
                            LOG.info(kf.getDokumen().getKodDokumen().getKod());
                            if (prld.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kf.getDokumen().getKodDokumen().getKod())) {
                                checkExist = true;
                                break;
                            }
                        }
                        LOG.info(checkExist);
                        if (!checkExist) {
                            listKandunganFolder.add(kf);
                        }
                    }

                } else {
                    listKandunganFolder = folderDokumen.getSenaraiKandungan();
                }
            }
        }
        List<KodDokumenAgensi> listKodDokAgensi = new ArrayList<KodDokumenAgensi>();
        listKodDokAgensi = pelupusanService.findKodDokAgensiByKodUrusanNAgensi(permohonan.getKodUrusan().getKod(), kodAgensi);
        for (KodDokumenAgensi kda : listKodDokAgensi) {
            for (KandunganFolder fd : listKandunganFolder) {
                if (kda.getKodDokumen().getKod().equals(fd.getDokumen().getKodDokumen().getKod())) {
                    simpanDokumen(fd, mohonRujLuar);
                    break;
                }
            }
        }

    }

    public void simpanDokumen(KandunganFolder fd, PermohonanRujukanLuar mohonRujLuar) {
        PermohonanRujukanLuarDokumen mohonRujLuarDok = new PermohonanRujukanLuarDokumen();
        mohonRujLuarDok = pelupusanService.findDokumen(String.valueOf(fd.getDokumen().getIdDokumen()), String.valueOf(mohonRujLuar.getIdRujukan()));
        InfoAudit ia = new InfoAudit();
        if (mohonRujLuarDok != null) {
            ia = mohonRujLuarDok.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            mohonRujLuarDok = new PermohonanRujukanLuarDokumen();
            ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());
            mohonRujLuarDok.setCawangan(permohonan.getCawangan());
            LOG.info("THIS IS CAWANGAN =" + permohonan.getCawangan());
            mohonRujLuarDok.setPermohonanRujukanLuar(mohonRujLuar);
            mohonRujLuarDok.setDokumen(fd.getDokumen());
            mohonRujLuarDok.setHaluan("H");
        }
        mohonRujLuarDok.setInfoAudit(ia);
        pelupusanService.simpanPermohonanRujLuarDokumen(mohonRujLuarDok);
    }

    public Resolution refreshPage() {
        rehydrate();
        return showForm();
    }

    public Resolution showForm2() {
        return new JSP("/penguatkuasaan/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution edit() {
        if (kod == null) {
            kod = getContext().getRequest().getParameter("kod");
        }
        LOG.info(kod);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        LOG.info("Id Rujukan : " + idRujukan);
//        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
//        LOG.info("idPermohonan :" + idPermohonan + " KodAgensi:" + kod +"idRujukan"+idRujukan) ;
        Long test = Long.parseLong(idRujukan);
        pprl = permohonanRujukanLuarDAO.findById(test);
//        Long test = Long.parseLong(kod);
//        prs = new ArrayList<PermohonanRujukanLuarSalinan>();
        prs = pelupusanService.findByIdMohon3(pprl.getIdRujukan());
        prsd = pelupusanService.findByIdMohonForMRLDok(pprl.getIdRujukan());
//        prs = pelupusanService.findSalinan(idRujukan);
//        prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));


        if (pprl != null) {
            setTarikhHantar(pprl.getTarikhDisampai());
            setTarikhJangkaTerima(pprl.getTarikhJangkaTerima());
            setJangkaMasa_hari(pprl.getTempohHari());
            setCatatan(pprl.getCatatan());
            setMandatori(pprl.getUlasanMandatori());

        }




        return new JSP("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
//        return new JSP("pelupusan/common/edit_jabatan_teknikal.jsp").addParameter("tab", "true");
//               return new ForwardResolution("/WEB-INF/jsp/pelupusan/common/edit_jabatan_teknikal.jsp").addParameter("tab", "true");
//        return new RedirectResolution("/WEB-INF/jsp/pelupusan/edit_test1.jsp") ;
    }

    public Resolution simpanKodSalinan() {
        String kodMohonRujLuar = getContext().getRequest().getParameter("radio");
        kodRujukan = getContext().getRequest().getParameter("kod");
        LOG.info("kod :" + kodMohonRujLuar);
        LOG.info("kodRujukan: " + kodRujukan);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

//            kod = getContext().getRequest().getParameter("Kod");

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());


//            kodAgensi.setInfoAudit(info);
            if (StringUtils.isNotBlank(kodMohonRujLuar)) {
                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinanByIdRujukanAndKodAgensi(idRujukan, kodMohonRujLuar);

                if (prs != null) {
                    prs.setCawangan(permohonan.getCawangan());
                    prs.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(kodMohonRujLuar);
                    Long a = Long.parseLong(idRujukan);
                    prs.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(a));
                    pelupusanService.saveOrUpdate(prs);

                } else {
                    PermohonanRujukanLuarSalinan prs2 = new PermohonanRujukanLuarSalinan();
                    prs2.setCawangan(permohonan.getCawangan());
                    prs2.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(kodMohonRujLuar);
                    prs2.setKodAgensi(ks);
                    Long a = Long.parseLong(idRujukan);
                    prs2.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(a));
                    pelupusanService.saveOrUpdate(prs2);
                }
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        kod = getContext().getRequest().getParameter("kodAsal");
        return new JSP("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");

    }

    public Resolution editTest() {

        kod = getContext().getRequest().getParameter("kod");
//        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
        LOG.info("idPermohonan :" + idPermohonan + " KodAgensi:" + kod);
        pprl = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan, kod);

        if (pprl != null) {
            setTarikhHantar(pprl.getTarikhDisampai());
            setTarikhJangkaTerima(pprl.getTarikhJangkaTerima());
//            setJangkaMasa_hari(pprl.getTempohHari
            setCatatan(pprl.getCatatan());
        }
        return new JSP("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("tab", "true");


    }

    public Resolution searchData1() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        senaraiRujukLuar =pelupusanService.
        for (int i = 0; i < senaraiRujukanLuar.size(); i++) {
        }

        return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
    }
    // click on edit button

    public Resolution showEditJabatanTeknikal() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        mohonRujLuar = new PermohonanRujukanLuar();
        if (idRujukan != null) {
            mohonRujLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        }
        return new JSP("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        kNegeri = conf.getProperty("kodNegeri");
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("MLCRG")) {
                penggunaList = getSenaraiPenggunaPTG(pguna.getKodCawangan());
            }
            else if(permohonan.getKodUrusan().getKod().equals("MPJLB") || permohonan.getKodUrusan().getKod().equals("PJLB") || permohonan.getKodUrusan().getKod().equals("PCRG") || permohonan.getKodUrusan().getKod().equals("MPCRG"))
            {
                penggunaList = getSenaraiPenggunaPJLBPTG(pguna.getKodCawangan());
            }
            else {
                penggunaList = getSenaraiPengguna(permohonan.getCawangan());
            }

        }

        List<PermohonanTandatanganDokumen> ttDokList = pelupusanService.findPermohonanDokTTByIdPermohonanrehy(idPermohonan);
        LOG.info("----------------ttDokList-------------------" + ttDokList.size());
        if (ttDokList.size() > 0) {
            for (PermohonanTandatanganDokumen mdtt : ttDokList) {
//                       tandatanganDokumen = ttDokList.get(0);
                if (mdtt.getKodDokumen().getKod().equals("SUP") || mdtt.getKodDokumen().getKod().equals("SUT") || mdtt.getKodDokumen().getKod().equals("SUA")) {
                    ditundatangan = mdtt.getDiTandatangan();
                }
                LOG.info("----------------ditundatangan-------------------" + ditundatangan);
            }

        }
        LOG.info("----------------tandatanganDokumen-------------------" + tandatanganDokumen);
        kodUrusanAgensi = pelupusanService.findKodUrusanAgensiByKodUrusan(permohonan.getKodUrusan().getKod());
        if(kodUrusanAgensi.size() > 0){
            kodAgensiDefined = new String[kodUrusanAgensi.size()];
            for(int u = 0 ; u < kodUrusanAgensi.size() ; u++){
                kodAgensiDefined[u] = kodUrusanAgensi.get(u).getKodAgensi().getKod();
            }
             LOG.info("----------------KodAgensiDefined------------------- :" + kodAgensiDefined.length);
        }
       
    }

    /**
     * Get the date java.util.Date object for days after current date
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        day = Calendar.DAY_OF_WEEK;
        if (day == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 18);
        } else if (day == Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 19);
        } else {
            cal.add(Calendar.DATE, 20);// +days
        }
        return cal.getTime();
    }

    // Click on Hapus button in HomePage
    public Resolution deleteRujukan() {
        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        String idDokTt;
//        idDokTt = getContext().getRequest().getParameter("idDokTt");
        try {
            if (idRujukan != null) {
                permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
                List<PermohonanRujukanLuarDokumen> listMohonRujLuarDok = new ArrayList<PermohonanRujukanLuarDokumen>();
                List<PermohonanRujukanLuarSalinan> listMohonRujLuarSal = new ArrayList<PermohonanRujukanLuarSalinan>();
                listMohonRujLuarDok = pelupusanService.findListDokumenRujukan(idRujukan);
                listMohonRujLuarSal = pelupusanService.findListSalinanRujukan(idRujukan);
                for (PermohonanRujukanLuarDokumen mohRujLuarDok : listMohonRujLuarDok) {
                    pelupusanService.deleteDokumen(mohRujLuarDok);
                }
                for (PermohonanRujukanLuarSalinan mohRujLuarSal : listMohonRujLuarSal) {
                    pelupusanService.deleteSalinan(mohRujLuarSal);
                }
//                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                /*
                 * DISABLED BY SHAZWAN SINCE MOHON_RUJ_LUARDOK AND MOHON_RUJ_LUAR_SALINAN CAN HAVE MANY DOC
                 * @Shazwan
                 */
//                PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumenRujukan(idRujukan) 
//               
//                if (pdok != null) {
//                    pelupusanService.deleteDokumen(pdok);
//                }

//                if (prs != null) {
//                    pelupusanService.deleteSalinan(prs);
//                }
                /*
                 * END
                 */

                enforcementService.deleteMesy(permohonanRujukanLuar);
                addSimpleMessage("Rekod dihapuskan berjaya");
            }
//            if (idDokTt != null) {
//                tandatanganDokumen = permohonanTandatanganDokumenDAO.findById(Long.valueOf(idDokTt));
//                System.out.println("-----deleteing tandatanganDokumen----" + tandatanganDokumen.getIdDokTt());
//                pelupusanService.deletePermohonanDokTT(tandatanganDokumen);
//                addSimpleMessage("dihapuskan berjaya");
//            }


        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/common/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
        return showForm();
    }

    public Resolution deleteDok() {

        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        LOG.info(idRujukan);
        if (idDokumen != null && idRujukan != null) {
            PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumen(idDokumen, idRujukan);
            LOG.info(pdok);
            if (pdok != null) {
                pelupusanService.deleteDokumen(pdok);
            }

        }
        rehydrate();
        return new RedirectResolution(JabatanTeknikalActionBean.class, "addDokumen");
    }

    // Click on Cari button in Home Page to display SearchPage
    public Resolution kodAgensiPopup() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
        return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution kodAgensiPopup2() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("/penguatkuasaan/pilih_salinan_agensi.jsp").addParameter("popup", "true");
    }
    /*
     * TO POP UP MOHONRUJLUARDOK
     */

    public Resolution kodAgensiPopup3() {
        index = getContext().getRequest().getParameter("index");
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        getContext().getRequest().setAttribute("kodMohon_ruj_luar", index);
        idMohonRujLuar = index;
        return new JSP("/penguatkuasaan/pilih_dokumen_serta.jsp").addParameter("popup", "true");
    }

    public Resolution addDokumen() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        if (idRujukan != null) {
            senaraiRujukanDok = pelupusanService.findListDokumenRujukan(idRujukan);
            senaraiKandungan = new ArrayList<KandunganFolder>();
            folderDokumen = permohonan.getFolderDokumen();
            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                if (kf == null || kf.getDokumen() == null) {
                    continue;
                }
                senaraiKandungan.add(kf);
            }
        }
        return new JSP("/penguatkuasaan/tambah_dokumen.jsp").addParameter("popup", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariKodAgensi() {
        listKodAgensi = new ArrayList<KodAgensi>();

//code changed by rohan
        if (kod != null) {
            listKodAgensi = pelupusanService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");
                //addSimpleError("Kod Agensi Tidak Sah");
            } else {
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
            }
        } else {
            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            sizeKod = listKodAgensi.size();
            LOG.debug("size agensi list: " + sizeKod);
//            if (listKodAgensi.size() < 1) {
//                kodAgensi = new KodAgensi();
//                kodAgensi.setKod(kod);
            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
        }



//        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
//        return new JSP("pelupusan/common/tambah_agensi.jsp").addParameter("popup", "true");
    }

    // Click on Cari button in SearchPage to search based on criteria
    public Resolution cariKodAgensiJTK() {
        listKodAgensi = new ArrayList<KodAgensi>();
        List<KodAgensi> listKodAgensiTemp = new ArrayList<KodAgensi>();
        List<PermohonanRujukanLuar> listMohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kNegeri = conf.getProperty("kodNegeri");
        if (!StringUtils.isBlank(idPermohonan)) {
            listMohonRujLuar = pelupusanService.findPermohonanRujLuarByIdPermohonanNADUN(idPermohonan, "JTK", kNegeri);
        }
//code changed by rohan
        if (kod != null) {
            listKodAgensiTemp = pelupusanService.searchKodAgensiJTK(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
                                checkExist = false;
                                break;
                            }
                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                }
            } else {
                if (listKodAgensiTemp.size() < 1) {
                    kodAgensi = new KodAgensi();
                    kodAgensi.setKod(kod);
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");
                    //addSimpleError("Kod Agensi Tidak Sah");
                } else {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        listKodAgensi.add(ka);
                    }
                    getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                    kodAgensiNama = null;
                    return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
                }
            }

        } else {
            listKodAgensiTemp = pelupusanService.searchKodAgensiJTK("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            if (listMohonRujLuar.size() > 0) {
                if (listKodAgensiTemp.size() > 0) {
                    for (KodAgensi ka : listKodAgensiTemp) {
                        boolean checkExist = true;
                        for (PermohonanRujukanLuar prl : listMohonRujLuar) {
                            if (ka.getKod().equalsIgnoreCase(prl.getAgensi().getKod())) {
                                checkExist = false;
                                break;
                            }
                        }
                        if (checkExist) {
                            listKodAgensi.add(ka);
                        }
                    }
                }
            } else {
                for (KodAgensi ka : listKodAgensiTemp) {
                    listKodAgensi.add(ka);
                }
            }

            System.out.println("listKodSyaratNyata.size : " + listKodAgensiTemp.size());

            sizeKod = listKodAgensi.size();
            LOG.debug("size agensi list: " + sizeKod);
//            if (listKodAgensi.size() < 1) {
//                kodAgensi = new KodAgensi();
//                kodAgensi.setKod(kod);
            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            kodAgensiNama = null;
            return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
        }
        return null;
//        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
//        return new JSP("pelupusan/common/tambah_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodAgensiADN() {
        listKodAgensiAdun = new ArrayList<KodAgensi>();

//code changed by rohan
        if (kod != null) {
            listKodAgensiAdun = pelupusanService.searchKodAgensiADN(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensiAdun.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");
                //addSimpleError("Kod Agensi Tidak Sah");
            } else {
                getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
                for (KodAgensi kod : listKodAgensiAdun) {
                    KodDUN kod1 = pelupusanService.findKodDUNByAgensi(kod.getKod());
                    listAdun.add(kod1);
                    namaKawasan.add(kod1.getNama());
                }
                kodAgensiNama = null;
                return new JSP("/penguatkuasaan/common/pilih_agensi.jsp").addParameter("popup", "true");
            }
        } else {
            listKodAgensiAdun = pelupusanService.searchKodAgensiADN("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensiAdun.size());
            sizeKod = listKodAgensiAdun.size();
            LOG.debug("size agensi list: " + sizeKod);
//            if (listKodAgensi.size() < 1) {
//                kodAgensi = new KodAgensi();
//                kodAgensi.setKod(kod);

            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            for (KodAgensi kod : listKodAgensiAdun) {
                KodDUN kod1 = pelupusanService.findKodDUNByAgensi(kod.getKod());
                if (kod1 != null) {
                    listAdun.add(kod1);
                    namaKawasan.add(kod1.getNama());
                }
            }
            kodAgensiNama = null;
            return new JSP("/penguatkuasaan/pilih_agensi.jsp").addParameter("popup", "true");
        }



//        getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
//        return new JSP("pelupusan/common/tambah_agensi.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodAgensi2() {
        listKodAgensi = new ArrayList<KodAgensi>();


        if (kod != null) {
            listKodAgensi = regService.searchKodAgensiLupus(kod, kodAgensiNama, conf.getProperty("kodNegeri")); //modify by m.fairulfaisal........................... filter by kod_katg_agensi
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");
                //addSimpleError("Kod Agensi Tidak Sah");
            }
        } else {
            listKodAgensi = regService.searchKodAgensiLupus("%", kodAgensiNama, conf.getProperty("kodNegeri"));
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            if (listKodAgensi.size() < 1) {
                kodAgensi = new KodAgensi();
                kodAgensi.setKod(kod);
                return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");
                // addSimpleError("Kod Agensi Nama Tidak Sah");
            }
        }

        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("/penguatkuasaan/pilih_salinan_agensi.jsp").addParameter("popup", "true");
    }
    /*
     * CARIAN POPUP DOKUMEN
     */

    public Resolution cariKodAgensi3() {
        listKandunganFolder = new ArrayList<KandunganFolder>();
        List<PermohonanRujukanLuarDokumen> listmohonRujLuarDokTemp = new ArrayList<PermohonanRujukanLuarDokumen>();
        listmohonRujLuarDokTemp = !StringUtils.isBlank(idMohonRujLuar) ? pelupusanService.findByIdMohonForMRLDok(Long.valueOf(idMohonRujLuar)) : new ArrayList<PermohonanRujukanLuarDokumen>();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        List<KandunganFolder> listKandunganFolderTemp = folderDokumen.getSenaraiKandungan();

        if (kod != null) {

            if (idPermohonan != null) {
                if (folderDokumen == null) {
                    addSimpleError("Tiada folder di dalam permohonan ini");
                } else {
                    if (listKandunganFolderTemp.size() > 0) {
                        if (!listmohonRujLuarDokTemp.isEmpty()) {
                            for (KandunganFolder kf : listKandunganFolderTemp) {
                                boolean checkExist = false;
                                for (PermohonanRujukanLuarDokumen prld : listmohonRujLuarDokTemp) {
                                    LOG.info(prld.getDokumen().getKodDokumen().getKod());
                                    LOG.info(kf.getDokumen().getKodDokumen().getKod());
                                    if (prld.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kf.getDokumen().getKodDokumen().getKod())) {
                                        checkExist = true;
                                        break;
                                    }
                                }
                                LOG.info(checkExist);
                                if (!checkExist) {
                                    listKandunganFolder.add(kf);
                                }
                            }
                        } else {
                            addSimpleMessage("Tiada Maklumat");
                        }
                    } else {
                        addSimpleMessage("Tiada Maklumat");
                    }
//                                   
                }
            }
        } else {

            if (idPermohonan != null) {
                if (folderDokumen == null) {
                    addSimpleError("Tiada folder di dalam permohonan ini");
                } else {

                    if (listKandunganFolderTemp.size() > 0) {
                        if (!listmohonRujLuarDokTemp.isEmpty()) {
                            for (KandunganFolder kf : listKandunganFolderTemp) {
                                boolean checkExist = false;
                                for (PermohonanRujukanLuarDokumen prld : listmohonRujLuarDokTemp) {
                                    LOG.info(prld.getDokumen().getKodDokumen().getKod());
                                    LOG.info(kf.getDokumen().getKodDokumen().getKod());
                                    if (prld.getDokumen().getKodDokumen().getKod().equalsIgnoreCase(kf.getDokumen().getKodDokumen().getKod())) {
                                        checkExist = true;
                                        break;
                                    }
                                }
                                LOG.info(checkExist);
                                if (!checkExist) {
                                    listKandunganFolder.add(kf);
                                }
                            }
                        } else {
                            listKandunganFolder = folderDokumen.getSenaraiKandungan();
                        }
                    } else {
                        addSimpleMessage("Tiada Maklumat");
                    }
                }
            }
        }
        sizeKod = listKandunganFolder.size();
        getContext().getRequest().setAttribute("salinan", Boolean.TRUE);
        return new JSP("/penguatkuasaan/pilih_dokumen_serta.jsp").addParameter("popup", "true");
    }
    /*
     * END OF CARIAN POPUP DOKUMEN
     */
    // Save KodAgensi details

    public Resolution simpanKodAgensi() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        if (idPermohonan != null) {
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            kodAgensi.setInfoAudit(info);
//            kodAgensi.setKodKementerian(1); //TODO: list kod_kementerian
            kodAgensi.setKategoriAgensi(kodKategoriAgensiDAO.findById(kodKatgAgensi));
            kodAgensi.setAktif('Y');
            LOG.info("---------kodAgensi--------" + kodAgensi);
            regService.simpanKodAgensi(kodAgensi);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan");

        return new JSP("/penguatkuasaan/tambah_agensi.jsp").addParameter("popup", "true");

    }

    public Resolution simpanEdit() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        InfoAudit ia = new InfoAudit();


        return new RedirectResolution(JabatanTeknikalActionBean.class, "showForm");
    }

    public Resolution simpanAgensi() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listAgensi = item.split(",");
        LOG.info("Size :" + listAgensi.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listAgensi.length; i++) {

            if (!listAgensi[i].equals("T")) {
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setPermohonan(permohonan);
                LOG.info(listAgensi[i]);
                KodAgensi agen = kodAgensiDAO.findById(listAgensi[i]);
                KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
                mohonRujLuar.setAgensi(agen);
                mohonRujLuar.setNamaAgensi(agen.getNama());
                mohonRujLuar.setKodRujukan(kodRujukan);
                mohonRujLuar.setTempohHari(14);
                mohonRujLuar.setTarikhRujukan(new java.util.Date());
                mohonRujLuar.setTarikhDisampai(new java.util.Date());
                mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                mohonRujLuar.setUlasanMandatori("T");
                pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
            }
        }
        return new ForwardResolution("/penguatkuasaan/common/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMohonRujLuarDokumen() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String idMohRujLuar = getContext().getRequest().getParameter("idMohRujLuar");
        String[] listDok = item.split(",");
        LOG.info("Size :" + listDok.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listDok.length; i++) {

            if (!listDok[i].equals("T")) {
                /*
                 * CHECKING dokumen at Folder_Dok table
                 */
                KandunganFolder fd = new KandunganFolder();
                if (!StringUtils.isBlank(listDok[i])) {
                    fd = kandunganFolderDAO.findById(Long.valueOf(listDok[i]));
                }
                if (fd != null) {
                    PermohonanRujukanLuarDokumen mohonRujLuarDok = new PermohonanRujukanLuarDokumen();
                    mohonRujLuarDok.setInfoAudit(ia);
                    mohonRujLuarDok.setCawangan(permohonan.getCawangan());
                    LOG.info("THIS IS CAWANGAN =" + permohonan.getCawangan());
                    mohonRujLuarDok.setPermohonanRujukanLuar(permohonanRujukanLuarDAO.findById(Long.valueOf(idMohRujLuar)));
                    LOG.info("THIS IS MOHON RUJUKAN LUAR =" + permohonanRujukanLuarDAO.findById(Long.valueOf(idMohRujLuar)));
                    mohonRujLuarDok.setDokumen(fd.getDokumen());
                    mohonRujLuarDok.setHaluan("H");
                    LOG.info("THIS IS DOKUMEN =" + fd.getDokumen().getKodDokumen().getKod());
                    pelupusanService.simpanPermohonanRujLuarDokumen(mohonRujLuarDok);
                }
                /*
                 * END
                 */

            }
        }
//      addSimpleMessage("Maklumat Berjaya Disimpan");
//       return null;
        addSimpleMessage("Maklumat telah berjaya disimpan");
        kod = getContext().getRequest().getParameter("kodAsal");
        return new JSP("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
    }

    public Resolution simpanAdun() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String item = getContext().getRequest().getParameter("item");
        String[] listAgensi = item.split(",");
        LOG.info("Size :" + listAgensi.length);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());
        for (int i = 0; i < listAgensi.length; i++) {

            if (!listAgensi[i].equals("T")) {
                PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setPermohonan(permohonan);
                LOG.info(listAgensi[i]);
                KodAgensi agen = kodAgensiDAO.findById(listAgensi[i]);
                KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
                mohonRujLuar.setAgensi(agen);
                mohonRujLuar.setNamaAgensi(agen.getNama());
                mohonRujLuar.setKodRujukan(kodRujukan);
                mohonRujLuar.setTempohHari(14);
                mohonRujLuar.setTarikhRujukan(new java.util.Date());
                mohonRujLuar.setTarikhDisampai(new java.util.Date());
                mohonRujLuar.setTarikhJangkaTerima(getDateAfterDays(14));
                mohonRujLuar.setUlasanMandatori("T");
                pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
            }
        }
        return new ForwardResolution("/penguatkuasaan/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskini() {
//         String mandatori = getContext().getRequest().getParameter("mandatori");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        Long id = Long.parseLong(idRujukan);
        LOG.info("mandatori : " + mandatori);
        PermohonanRujukanLuar permohonanRujukanLuar = pelupusanService.findByIdRujukan(id);
        if (permohonanRujukanLuar != null) {
            permohonanRujukanLuar.setCatatan(catatan);
            permohonanRujukanLuar.setTarikhDisampai(tarikhHantar);
//                     permohonanRujukanLuar.setTarikhJangkaTerima(tarikhJangkaTerima);
            permohonanRujukanLuar.setTempohHari(jangkaMasa_hari);
            if (mandatori != null) {
                permohonanRujukanLuar.setUlasanMandatori("Y");
            } else {
                permohonanRujukanLuar.setUlasanMandatori("T");
            }
        }
//                  permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar) ;
        pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);
        addSimpleMessage("Rekod telah dikemaskini");
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/common/edit_jabatan_teknikal.jsp").addParameter("popup", "true");
        return edit();
    }
    // Save PermohonanRujukanLuar details in Home Page

    public Resolution simpan() throws ParseException {
        String kod1;
        String namaJabatan1;
        String jangkamasa1;
        String jangTerima1;
        String tarikhHantar1;
        String salinanKepada1;
        String salinanKod1;
        String mandatori1;
        String catatan;
//        String ditundatangan;


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        kod = (String) getContext().getRequest().getSession().getAttribute("kod");
        permohonan = permohonanDAO.findById(idPermohonan);
//        pprl = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan, kod);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        InfoAudit ia = new InfoAudit();
//        pprl = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan, kod);
//        info.setDimasukOleh(peng);
//        info.setTarikhMasuk(new java.util.Date());
        senaraiRujukanLuar = new ArrayList<PermohonanRujukanLuar>();
        senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensi(idPermohonan);
//        tandatanganDokumenTemp = new ArrayList<PermohonanTandatanganDokumen>();
//        tandatanganDokumenTemp = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan);
        int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
        boolean flag = true;

        for (int i = 0; i < rowCount; i++) {
            if (senaraiRujukanLuar.size() != 0 && i < senaraiRujukanLuar.size()) {
                Long id = senaraiRujukanLuar.get(i).getIdRujukan();
                LOG.info("------------id ---------" + id);
                if (id != null) {
                    permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(id);
                    LOG.info("------------permohonanRujukanLuar ---------" + permohonanRujukanLuar);
                    info = permohonanRujukanLuar.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                }
            } else {
                LOG.info("-------permohonanRujukanLuar-----New ---------");
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());

            }

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(info);
            permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
            permohonanRujukanLuar.setTarikhDisampai(new java.util.Date());

            kod1 = getContext().getRequest().getParameter("kod" + i);
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            catatan = getContext().getRequest().getParameter("catatan" + i);
            ditundatangan = getContext().getRequest().getParameter("ditundatangan");
            namaJabatan1 = getContext().getRequest().getParameter("namaJabatan" + i);
            tarikhHantar1 = getContext().getRequest().getParameter("tarikhHantar" + i);
            jangkamasa1 = getContext().getRequest().getParameter("jangkamasa" + i);
            jangTerima1 = getContext().getRequest().getParameter("jangTerima" + i);
            salinanKepada1 = getContext().getRequest().getParameter("salinanKepada" + i);
            salinanKod1 = getContext().getRequest().getParameter("salinanKod" + i);
            mandatori1 = getContext().getRequest().getParameter("mandatori" + i);
            LOG.info("------------------Mandatori" + mandatori1);

//            LOG.info("-------ditundatangan----simpan ---------" + ditundatangan);
//
//            ia = new InfoAudit();
//            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, ditundatangan);
//            LOG.info("-------tandatanganDokumen----- ---------" + tandatanganDokumen);
//            if (tandatanganDokumen != null) {
//                ia = tandatanganDokumen.getInfoAudit();
////                ia.setDiKemaskiniOleh(peng);
////                ia.setTarikhKemaskini(new java.util.Date());
//            } else {
//                LOG.info("-------tandatanganDokumen-----New ---------"+tandatanganDokumen);
//                tandatanganDokumen = new PermohonanTandatanganDokumen();
//                tandatanganDokumen.setCawangan(permohonan.getCawangan());
//                tandatanganDokumen.setPermohonan(permohonan);
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//
//            }
//            tandatanganDokumen.setInfoAudit(ia);
//            LOG.info("---------kodAgensi----out--------------------" + kod1);
//            KodAgensi kodAgensi = kodAgensiDAO.findById(kod1);
//            LOG.info("---------kodAgensi---------in---------------" + kodAgensi);
//            String kod22 = "SUA";
//            String kod23 = "SUT";
//
////            if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("ADN")) {
////                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
////            }
////            else if (kodAgensi.getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
////                tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
////            }
////            if(tandatanganDokumen.getDiTandatangan().equals("225")){
//                if(ditundatangan.equals("225")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
//            }else if(ditundatangan.equals("223")){
////                if(tandatanganDokumen.getDiTandatangan().equals("223")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
//            }
//
////            String ditundatangan1 = getContext().getRequest().getParameter("ditundatangan");
//            if (StringUtils.isNotBlank(ditundatangan)) {
//                tandatanganDokumen.setDiTandatangan(ditundatangan);
//            }
//            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen);

            if ((namaJabatan1 == null) || (jangkamasa1 == null) || (jangTerima1 == null) || (tarikhHantar1 == null)
                    || (namaJabatan1.trim().equals("")) || (jangkamasa1.trim().equals(""))
                    || (jangTerima1.trim().equals("")) || (tarikhHantar1.trim().equals(""))) {
                LOG.info("-------Test Fail ---------");
                addSimpleError("Semua medan wajib");
                flag = false;
                continue;
            }
            LOG.info("---------------end-----");
            KodRujukan kodRujukan = kodRujukanDAO.findById("FL");
            KodAgensi kod = kodAgensiDAO.findById(kod1);
            permohonanRujukanLuar.setAgensi(kod);
            permohonanRujukanLuar.setKodRujukan(kodRujukan);
            permohonanRujukanLuar.setNamaAgensi(namaJabatan1);
            permohonanRujukanLuar.setCatatan(catatan);
            permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhHantar1));
            permohonanRujukanLuar.setTarikhRujukan(new Date());
            LOG.info("---------Mandatori----------" + mandatori1);
            if (mandatori1 != null) {
                permohonanRujukanLuar.setUlasanMandatori("Y");
            } else {
                permohonanRujukanLuar.setUlasanMandatori("T");
            }


            try {
                StringTokenizer tokenizer = new StringTokenizer(jangkamasa1);
                permohonanRujukanLuar.setTempohHari(Integer.parseInt((String) tokenizer.nextElement()));
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError("Semua medan wajib");
            }
            permohonanRujukanLuar.setTarikhJangkaTerima(sdf.parse(jangTerima1));
            LOG.info("------Save---Mandatori----------" + permohonanRujukanLuar.getUlasanMandatori());
            pelupusanService.simpanPermohonanRujLuar(permohonanRujukanLuar);
            if (StringUtils.isNotBlank(salinanKod1)) {
                PermohonanRujukanLuarSalinan prs = pelupusanService.findSalinan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                if (prs != null) {
                    prs.setCawangan(permohonan.getCawangan());
                    prs.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs.setKodAgensi(ks);
                    prs.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs);

                } else {
                    PermohonanRujukanLuarSalinan prs2 = new PermohonanRujukanLuarSalinan();
                    prs2.setCawangan(permohonan.getCawangan());
                    prs2.setInfoAudit(info);
                    KodAgensi ks = kodAgensiDAO.findById(salinanKod1);
                    prs2.setKodAgensi(ks);
                    prs2.setPermohonanRujukanLuar(permohonanRujukanLuar);
                    pelupusanService.saveOrUpdate(prs2);
                }
            }
        }
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("-------ditundatangan----simpan ---------" + ditundatangan);
        ia = new InfoAudit();

        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUA");
        PermohonanTandatanganDokumen tandatanganDokumen2 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUT");

        if (tandatanganDokumen1 != null) {
            ia = tandatanganDokumen1.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("-------tandatanganDokumen1-------------" + tandatanganDokumen1);
            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
            tandatanganDokumen1.setPermohonan(permohonan);
            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUA"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen1.setInfoAudit(ia);
        LOG.info("-------ditundatangan-------------" + ditundatangan);
        tandatanganDokumen1.setDiTandatangan(ditundatangan);
        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

        if (tandatanganDokumen2 != null) {
            ia = tandatanganDokumen2.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            LOG.info("-------tandatanganDokumen2----- ---------" + tandatanganDokumen2);
            tandatanganDokumen2 = new PermohonanTandatanganDokumen();
            tandatanganDokumen2.setCawangan(permohonan.getCawangan());
            tandatanganDokumen2.setPermohonan(permohonan);
            tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("SUT"));
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        }
        tandatanganDokumen2.setInfoAudit(ia);
        tandatanganDokumen2.setDiTandatangan(ditundatangan);
        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen2);

        if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
            PermohonanTandatanganDokumen tandatanganDokumen3 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUP");
            if (tandatanganDokumen3 != null) {
                ia = tandatanganDokumen3.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                LOG.info("-------tandatanganDokumen2----- ---------" + tandatanganDokumen3);
                tandatanganDokumen3 = new PermohonanTandatanganDokumen();
                tandatanganDokumen3.setCawangan(permohonan.getCawangan());
                tandatanganDokumen3.setPermohonan(permohonan);
                tandatanganDokumen3.setKodDokumen(kodDokumenDAO.findById("SUP"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen3.setInfoAudit(ia);
            tandatanganDokumen3.setDiTandatangan(ditundatangan);
            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen3);
        }

//            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, ditundatangan);
//            LOG.info("-------tandatanganDokumen----- ---------" + tandatanganDokumen);
//            if (tandatanganDokumen != null) {
//                ia = tandatanganDokumen.getInfoAudit();
//                ia.setDiKemaskiniOleh(peng);
//                ia.setTarikhKemaskini(new java.util.Date());
//            } else {
//                LOG.info("-------tandatanganDokumen-----New ---------"+tandatanganDokumen);
//                tandatanganDokumen = new PermohonanTandatanganDokumen();
//                tandatanganDokumen.setCawangan(permohonan.getCawangan());
//                tandatanganDokumen.setPermohonan(permohonan);
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//
//            }
//            tandatanganDokumen.setInfoAudit(ia);
//            String kod22 = "SUA";
//            String kod23 = "SUT";
//
//                if(ditundatangan.equals("225")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod22));
//            }else if(ditundatangan.equals("223")){
//             tandatanganDokumen.setKodDokumen(kodDokumenDAO.findById(kod23));
//            }
//            if (StringUtils.isNotBlank(ditundatangan)) {
//                tandatanganDokumen.setDiTandatangan(ditundatangan);
//            }
//            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen);
        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new RedirectResolution(JabatanTeknikalActionBean.class, "showForm");
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUA");
        PermohonanTandatanganDokumen tandatanganDokumen2 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUT");
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. " + ditundatangan);
        if (ditundatangan != null) {
            if (kNegeri.equals("04")) { //Filter for Melaka Only since Adun use in Melaka
                if (tandatanganDokumen1 != null) {
                    ia = tandatanganDokumen1.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                    tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                    tandatanganDokumen1.setPermohonan(permohonan);
                    tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUA"));
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                tandatanganDokumen1.setInfoAudit(ia);
                tandatanganDokumen1.setDiTandatangan(ditundatangan);
                pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
            }


            if (tandatanganDokumen2 != null) {
                ia = tandatanganDokumen2.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
            } else {
                tandatanganDokumen2 = new PermohonanTandatanganDokumen();
                tandatanganDokumen2.setCawangan(permohonan.getCawangan());
                tandatanganDokumen2.setPermohonan(permohonan);
                tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("SUT"));
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
            }
            tandatanganDokumen2.setInfoAudit(ia);
            tandatanganDokumen2.setDiTandatangan(ditundatangan);
            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen2);

            if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                PermohonanTandatanganDokumen tandatanganDokumen3 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUP");
                if (tandatanganDokumen3 != null) {
                    ia = tandatanganDokumen3.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    tandatanganDokumen3 = new PermohonanTandatanganDokumen();
                    tandatanganDokumen3.setCawangan(permohonan.getCawangan());
                    tandatanganDokumen3.setPermohonan(permohonan);
                    tandatanganDokumen3.setKodDokumen(kodDokumenDAO.findById("SUP"));
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                tandatanganDokumen3.setInfoAudit(ia);
                tandatanganDokumen3.setDiTandatangan(ditundatangan);
                pelupusanService.simpanPermohonanDokTT(tandatanganDokumen3);
            }

        }


        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        return new RedirectResolution(JabatanTeknikalActionBean.class, "showForm");

    }

    public Resolution deleteRujukanSalinan() {

        kod = getContext().getRequest().getParameter("idRujukan"); //Kod agensi
        idSalinan = getContext().getRequest().getParameter("idSalinan"); // Kod mohon ruj salinan
        LOG.info(idRujukan + " & " + idSalinan);
//        mohonRujukLuarSalinan = pelupusanService.findByIdSalinanIdRujukan(idSalinan);
        Long id = Long.parseLong(idSalinan);
        mohonRujukLuarSalinan = permohonanRujukanLuarSalinanDAO.findById(id);
        LOG.info(mohonRujukLuarSalinan.getKodAgensi().getKod());
//        mohonRujukLuarSalinan = pelupusanService.findSalinan(idRujukan);
        try {
            if (mohonRujukLuarSalinan != null) {
                pelupusanService.deleteSalinan(mohonRujukLuarSalinan);

            }


        } //        rehydrate();
        catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }

//      edit();
        addSimpleMessage("Rekod berjaya dihapuskan");
        return new ForwardResolution("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");

        //    return new RedirectResolution(JabatanTeknikalActionBean.class, "edit");
    }

    /*
     * MOHON RUJUK LUAR DOKUMEN
     */
    public Resolution deleteRujukanDokumen() {

        //kod = getContext().getRequest().getParameter("idRujukan"); //Kod agensi
        idDokumen = getContext().getRequest().getParameter("idDokumen"); // Kod mohon ruj salinan
        Long id = Long.parseLong(idDokumen);
        pdok = permohonanRujukanLuarDokumenDAO.findById(id);
        try {
            if (pdok != null) {
                pelupusanService.deleteDokumen(pdok);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        return new ForwardResolution("/penguatkuasaan/edit_jabatan_teknikal.jsp").addParameter("popup", "true");

        //    return new RedirectResolution(JabatanTeknikalActionBean.class, "edit");
    }
    /*
     * END OF MOHON RUJUK LUAR DOKUMEN
     */
    // Save PermohonanRujukanLuar details in Home Page
//

    public Resolution simpanDokumen() {

        String[] param = getContext().getRequest().getParameterValues("idDokumen");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        for (String idDokumen : param) {

            LOG.info(idDokumen);
            InfoAudit ia = new InfoAudit();
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonanRujukanLuar = pelupusanService.findByIdRujukan(Long.valueOf(idRujukan));
            PermohonanRujukanLuarDokumen pdok = pelupusanService.findDokumen(idDokumen, idRujukan);
            if (pdok == null) {
                PermohonanRujukanLuarDokumen pe = new PermohonanRujukanLuarDokumen();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
                pe.setInfoAudit(ia);
                pe.setCawangan(peng.getKodCawangan());
                Dokumen kd = new Dokumen();
                kd.setIdDokumen(Long.valueOf(idDokumen));
                pe.setDokumen(kd);
                LOG.info(kd.getIdDokumen());
                pe.setHaluan("H");
                pe.setPermohonanRujukanLuar(permohonanRujukanLuar);
                pelupusanService.simpanDokumen(pe);


            } else {
                addSimpleError("Dokumen  ini telah diHantar");
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new RedirectResolution(JabatanTeknikal2ActionBean.class, "addDokumen");
    }

    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('223','77') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPTG(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('12','28','240') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }
    
    public List<Pengguna> getSenaraiPenggunaPJLBPTG(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('234','91','54') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }
    
    public Resolution hantarEmelKePortal() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        try {
            KonfigurasiSistem ks = new KonfigurasiSistem();
            ks = jTeknikalService.findKonfigSistemByNama("ulasan_jtek_portal");
            if (ks != null) {
                if (ks.getNilai().equalsIgnoreCase("Y")) {
                    List<PermohonanRujukanLuar> listmohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
                    List<UlasanJabatanTeknikal> listujt = new ArrayList<UlasanJabatanTeknikal>();
                    listmohonRujLuar = jTeknikalService.findMohonRujLuar(permohonan.getIdPermohonan());
                    listujt = jTeknikalService.findUlasanJabTeknikalbyIdMohon(permohonan.getIdPermohonan());

                    for (PermohonanRujukanLuar prl : listmohonRujLuar) {
                        if (listujt.isEmpty() || listujt.size() <= 0) {
                            if (prl.getAgensi().getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
                                InfoAudit ia = new InfoAudit();
                                UlasanJabatanTeknikal ujt = new UlasanJabatanTeknikal();
                                ia.setDimasukOleh(pguna);
                                ia.setTarikhMasuk(new java.util.Date());
                                ujt.setInfoAudit(ia);
                                ujt.setKodCawangan(permohonan.getCawangan());
                                ujt.setPermohonan(permohonan);
                                ujt.setKodStatusUlasanJabatanTeknikal(kodStatusUlasanJabatanTeknikalDAO.findById("BAR"));
                                ujt.setKodAgensi(prl.getAgensi());
//                                ujt.setNoRuj(!StringUtils.isBlank(prl.getNoRujukan())?prl.getNoRujukan():new String());
//                                ujt.setUlasan(!StringUtils.isBlank(prl.getUlasan())?prl.getUlasan():new String());
                                String kodNegeri = conf.getKodNegeri();
                                MailService mail = new MailService();
                                String[] email = new String[prl.getAgensi().getSenaraiPortalPengguna().size()];
                                int a = 0;
                                for (PortalPengguna pp : prl.getAgensi().getSenaraiPortalPengguna()) {
                                    email[a] = pp.getEmail();
                                    a++;
                                }
                                String subject = "Notifikasi ulasan Jabatan Teknikal";
                                String msg = "Ulasan dari pihak tuan diperlukan bagi permohonan " + permohonan.getKodUrusan().getNama() + " untuk id permohonan " + permohonan.getIdPermohonan() + ".\n"
                                        + "Sila layari http://etanah.melaka.gov.my/jtek/ untuk memberi ulasan.\n"
                                        + "Terima Kasih.";

                                mail.sendEmailHTML(email, subject, mail.HTMLJtek(permohonan), kodNegeri);
                                try {
                                    jTeknikalService.saveOrUpdateUlasanJTek(ujt);
                                } catch (Exception e) {
                                    LOG.error(e.getMessage());
                                    return null;
                                }
                                addSimpleMessage("Maklumat telah berjaya dihantar.");
                            }
                        } else {
                            for (UlasanJabatanTeknikal ujt : listujt) {
                                if (ujt.getKodAgensi().getKod().equalsIgnoreCase(prl.getAgensi().getKod()) && prl.getAgensi().getKategoriAgensi().getKod().equalsIgnoreCase("JTK")) {
                                    InfoAudit ia = new InfoAudit();
                                    ia = ujt.getInfoAudit();
                                    ia.setDiKemaskiniOleh(pguna);
                                    ia.setTarikhKemaskini(new java.util.Date());
                                    ujt.setInfoAudit(ia);
//                                    ujt.setUlasan(prl.getUlasan());

                                    try {

                                        jTeknikalService.saveOrUpdateUlasanJTek(ujt);

                                    } catch (Exception e) {
                                        LOG.error(e.getMessage());
                                        return null;
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
            
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return new JSP("/penguatkuasaan/sedia_jabatan_teknikal.jsp").addParameter("tab", "true");
    }

    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getRadio_() {
        return radio_;
    }

    public void setRadio_(String radio_) {
        this.radio_ = radio_;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(String rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public String getPenyelerasan() {
        return Penyelerasan;
    }

    public void setPenyelerasan(String Penyelerasan) {
        this.Penyelerasan = Penyelerasan;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public String getKategoriAgensi() {
        return kategoriAgensi;
    }

    public void setKategoriAgensi(String kategoriAgensi) {
        this.kategoriAgensi = kategoriAgensi;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohoanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getkNegeri() {
        return kNegeri;
    }

    public void setkNegeri(String kNegeri) {
        this.kNegeri = kNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiRujukanDok() {
        return senaraiRujukanDok;
    }

    public void setSenaraiRujukanDok(List<PermohonanRujukanLuarDokumen> senaraiRujukanDok) {
        this.senaraiRujukanDok = senaraiRujukanDok;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String getIdSalinan() {
        return idSalinan;
    }

    public void setIdSalinan(String idSalinan) {
        this.idSalinan = idSalinan;
    }

    public String getMandatori() {
        return mandatori;
    }

    public void setMandatori(String mandatori) {
        this.mandatori = mandatori;
    }

    public List<KodKementerian> getSenaraiKodKementerian() {
        return senaraiKodKementerian;
    }

    public void setSenaraiKodKementerian(List<KodKementerian> senaraiKodKementerian) {
        this.senaraiKodKementerian = senaraiKodKementerian;
    }

    public String getKodKatgAgensi() {
        return kodKatgAgensi;
    }

    public void setKodKatgAgensi(String kodKatgAgensi) {
        this.kodKatgAgensi = kodKatgAgensi;
    }

    public List<KodAgensi> getKodAgensis() {
        return kodAgensis;
    }

    public void setKodAgensis(List<KodAgensi> kodAgensis) {
        this.kodAgensis = kodAgensis;
    }

    public PermohonanTandatanganDokumen getTandatanganDokumen() {
        return tandatanganDokumen;
    }

    public void setTandatanganDokumen(PermohonanTandatanganDokumen tandatanganDokumen) {
        this.tandatanganDokumen = tandatanganDokumen;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public String getDitundatangan1() {
        return ditundatangan1;
    }

    public void setDitundatangan1(String ditundatangan1) {
        this.ditundatangan1 = ditundatangan1;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public static int getDay() {
        return day;
    }

    public static void setDay(int day) {
        JabatanTeknikalActionBean.day = day;
    }

    public int getJangkaMasa_hari() {
        return jangkaMasa_hari;
    }

    public void setJangkaMasa_hari(int jangkaMasa_hari) {
        this.jangkaMasa_hari = jangkaMasa_hari;
    }

    public Date getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(Date tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public Date getTarikhJangkaTerima() {
        return tarikhJangkaTerima;
    }

    public void setTarikhJangkaTerima(Date tarikhJangkaTerima) {
        this.tarikhJangkaTerima = tarikhJangkaTerima;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public PermohonanRujukanLuarDokumen getPdok() {
        return pdok;
    }

    public void setPdok(PermohonanRujukanLuarDokumen pdok) {
        this.pdok = pdok;
    }

    public List<PermohonanRujukanLuarSalinan> getPrs() {
        return prs;
    }

    public void setPrs(List<PermohonanRujukanLuarSalinan> prs) {
        this.prs = prs;
    }

//    public PermohonanRujukanLuarSalinan getPrs() {
//        return prs;
//    }
//
//    public void setPrs(PermohonanRujukanLuarSalinan prs) {
//        this.prs = prs;
//    }
    public PermohonanRujukanLuar getPprl() {
        return pprl;
    }

    public void setPprl(PermohonanRujukanLuar pprl) {
        this.pprl = pprl;
    }

    public String getKodMohon_ruj_luar() {
        return kodMohon_ruj_luar;
    }

    public void setKodMohon_ruj_luar(String kodMohon_ruj_luar) {
        this.kodMohon_ruj_luar = kodMohon_ruj_luar;
    }

    public String getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(String kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public List<KodDUN> getListAdun() {
        return listAdun;
    }

    public void setListAdun(List<KodDUN> listAdun) {
        this.listAdun = listAdun;
    }

    public Vector getNamaKawasan() {
        return namaKawasan;
    }

    public void setNamaKawasan(Vector namaKawasan) {
        this.namaKawasan = namaKawasan;
    }

    public List<KodAgensi> getListKodAgensiAdun() {
        return listKodAgensiAdun;
    }

    public void setListKodAgensiAdun(List<KodAgensi> listKodAgensiAdun) {
        this.listKodAgensiAdun = listKodAgensiAdun;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public List<FolderDokumen> getListFolderDokumen() {
        return listFolderDokumen;
    }

    public void setListFolderDokumen(List<FolderDokumen> listFolderDokumen) {
        this.listFolderDokumen = listFolderDokumen;
    }

    public List<PermohonanRujukanLuarDokumen> getPrsd() {
        return prsd;
    }

    public void setPrsd(List<PermohonanRujukanLuarDokumen> prsd) {
        this.prsd = prsd;
    }

    public List<KandunganFolder> getListKandunganFolder() {
        return listKandunganFolder;
    }

    public void setListKandunganFolder(List<KandunganFolder> listKandunganFolder) {
        this.listKandunganFolder = listKandunganFolder;
    }

    public String getIdMohonRujLuar() {
        return idMohonRujLuar;
    }

    public void setIdMohonRujLuar(String idMohonRujLuar) {
        this.idMohonRujLuar = idMohonRujLuar;
    }

    public List<KodUrusanAgensi> getKodUrusanAgensi() {
        return kodUrusanAgensi;
    }

    public void setKodUrusanAgensi(List<KodUrusanAgensi> kodUrusanAgensi) {
        this.kodUrusanAgensi = kodUrusanAgensi;
    }

    public String[] getKodAgensiDefined() {
        return kodAgensiDefined;
    }

    public void setKodAgensiDefined(String[] kodAgensiDefined) {
        this.kodAgensiDefined = kodAgensiDefined;
    }
    
    
}
