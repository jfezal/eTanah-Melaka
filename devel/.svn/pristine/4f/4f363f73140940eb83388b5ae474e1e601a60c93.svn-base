/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriPenguatkuasaanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.EnkuiriPenguatkuasaanKehadiranDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakDAO;
import etanah.model.Alamat;
import etanah.model.EnkuiriPenguatkuasaan;
import etanah.model.EnkuiriPenguatkuasaanKehadiran;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.service.BPelService;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.jenisKuasaActionBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author latifah.iskak
 *
 */
@UrlBinding("/penguatkuasaan/maklumat_enkuiri")
public class EnkuiriKehadiranActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(EnkuiriKehadiranActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    CalendarEnkuiriManager manager;
    @Inject
    EnkuiriPenguatkuasaanKehadiranDAO enkuiriPenguatkuasaanKehadiranDAO;
    @Inject
    EnkuiriPenguatkuasaanDAO enkuiriPenguatkuasaanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    private Permohonan permohonan;
    private EnkuiriPenguatkuasaan enkuiri;
    private String tarikhMula;
    private String tujuan;
    private String jam;
    private String minit;
    private String ampm;
    private String hari;
    private String idPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
    SimpleDateFormat sdfToday = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat sdfdisplay = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String tempat;
    private String idPermohonanPihak;
    private List<HakmilikPermohonan> hakmilikpermohonan = new ArrayList<HakmilikPermohonan>();
    private Hakmilik hakmilik;
    private List<HakmilikPihakBerkepentingan> senaraiKehadiran;
    private List<CalendarEnkuiri> listCalendar;
    private EnkuiriPenguatkuasaanKehadiran enkuiriKehadiran;
    private List<EnkuiriPenguatkuasaan> senaraiEnkuiri;
    private List<EnkuiriPenguatkuasaanKehadiran> detailsKehadiran;
    private List<EnkuiriPenguatkuasaanKehadiran> senaraiPihakHadir;
    private Pengguna pguna;
    private String stageId;
    private String catatan;
    private String ulasan;
    private String recordCountSenaraiKehadiran;
    private String idEnkuiri;
    private String nama;
    private String noPengenalan;
    private String statusKehadiran;
    private String noRujukan;
    private String idKehadiran;
    private String pilihPihak;
    private String kodJenisEnkuiri;
    private Boolean maklumatTanahSek49 = Boolean.FALSE;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idMH;
    ArrayList listIdPermohonan = new ArrayList<String>();
    String senaraiIdPermohonan[], idPertama, idKedua;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String kodBangsa;
    private String namaPb;
    private String noPengenalanPbBaru;
    private String noPengenalanPbLain;
    private String kodPengenalanPb;
    private String hubunganPb;
    private Boolean statusPB = Boolean.FALSE;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/maklumat_kehadiran_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_kehadiran_enkuiri_view.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("penguatkuasaan/calendar_enkuiri.jsp").addParameter("popup", "true");
    }

    public Resolution viewKehadiranDetail() {
        String id = getContext().getRequest().getParameter("idEnkuiri");
        detailsKehadiran = enforceService.findDetailsKehadiran(Long.parseLong(id));
        LOG.info("details size : " + detailsKehadiran.size());
        return new JSP("penguatkuasaan/view_kehadiran_enkuiri.jsp").addParameter("popup", "true");
    }

    public Resolution addKehadiran() {
        idEnkuiri = getContext().getRequest().getParameter("idEnkuiri");
        return new JSP("penguatkuasaan/popup_tambah_kehadiran.jsp").addParameter("popup", "true");
    }

    public Resolution editKehadiran() {
        idEnkuiri = getContext().getRequest().getParameter("idEnkuiri");
        idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        if (StringUtils.isNotBlank(idKehadiran)) {
            enkuiriKehadiran = enkuiriPenguatkuasaanKehadiranDAO.findById(Long.parseLong(idKehadiran));
            if (enkuiriKehadiran != null) {
                nama = enkuiriKehadiran.getNama();
                noPengenalan = enkuiriKehadiran.getNoPengenalan();
                statusKehadiran = enkuiriKehadiran.getHadir().toString();
                noRujukan = enkuiriKehadiran.getNoRujukan();
                pilihPihak = enkuiriKehadiran.getNoRujukan();
                if (enkuiriKehadiran.getAlamat() != null) {
                    alamat1 = enkuiriKehadiran.getAlamat().getAlamat1();
                    alamat2 = enkuiriKehadiran.getAlamat().getAlamat2();
                    alamat3 = enkuiriKehadiran.getAlamat().getAlamat3();
                    alamat4 = enkuiriKehadiran.getAlamat().getAlamat4();
                    poskod = enkuiriKehadiran.getAlamat().getPoskod();
                    if (enkuiriKehadiran.getAlamat().getNegeri() != null) {
                        negeri = enkuiriKehadiran.getAlamat().getNegeri().getKod();
                    }

                }

                if (StringUtils.isNotBlank(noRujukan)) {
                    if (!noRujukan.equalsIgnoreCase("L")) { // not equal to L means its for id pihak
                        Pihak pihak = pihakDAO.findById(Long.parseLong(noRujukan));
                        if (pihak != null) {
                            if (pihak.getBangsa() != null) {
                                if (!pihak.getBangsa().getKod().equalsIgnoreCase("MEL")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("CIN")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("IND")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("SIM")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("ASL")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("PTT")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("PLN")
                                        && !pihak.getBangsa().getKod().equalsIgnoreCase("LN")) {
                                    statusPB = true;
                                }
                            }
                        }
                    }
                    if (statusPB == true) {
                        namaPb = enkuiriKehadiran.getNamaPihakBerkepentingan();
                        hubunganPb = enkuiriKehadiran.getHubunganPihakBerkepentingan();
                        if (enkuiriKehadiran.getJenisPengenalanPihakBerkepentingan() != null) {
                            kodPengenalanPb = enkuiriKehadiran.getJenisPengenalanPihakBerkepentingan().getKod();
                        }
                        if (StringUtils.isNotBlank(kodPengenalanPb)) {
                            if (kodPengenalanPb.equalsIgnoreCase("B")) {
                                noPengenalanPbBaru = enkuiriKehadiran.getNoPengenalanPihakBerkepentingan();
                            } else {
                                noPengenalanPbLain = enkuiriKehadiran.getNoPengenalanPihakBerkepentingan();
                            }
                        }
                    }
                }
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_kehadiran.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("------------rehydrate--------------");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            //calendar
            //listCalendar = manager.getALLEnkuiri();

            try {
                senaraiKehadiran = new ArrayList<HakmilikPihakBerkepentingan>();

                if (conf.getProperty("kodNegeri").equalsIgnoreCase("04")) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
                        }
                        maklumatTanahSek49 = true;
                    }
                }

                if (maklumatTanahSek49 == true) {
                    Long id = null;
                    senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(idPermohonan);
                    System.out.println("senaraiHakmilikPermohonan before looping :" + senaraiHakmilikPermohonan.size());
                    if (!senaraiHakmilikPermohonan.isEmpty()) {
                        for (int j = 0; j < senaraiHakmilikPermohonan.size(); j++) {
                            if (senaraiHakmilikPermohonan.get(j).getNomborRujukan() != null) {
                                System.out.println("::::::::::: value j :" + j);
                                HakmilikPermohonan hp = senaraiHakmilikPermohonan.get(j);
                                listIdPermohonan.add(hp.getNomborRujukan());

                                ArrayList<String> data = listIdPermohonan;


                                for (String a : data) {
                                    senaraiIdPermohonan = a.split(",");
                                    LOG.info("length senaraiIdPermohonan : " + senaraiIdPermohonan.length);
                                    if (senaraiIdPermohonan.length > 1) {
                                        idPertama = senaraiIdPermohonan[0];
                                        idKedua = senaraiIdPermohonan[1];

                                    }
                                }
                                LOG.info("::: idPertama : " + idPertama);
                                LOG.info("::: idKedua : " + idKedua);

                                String idMohon = "";
                                if (StringUtils.isNotEmpty(idPertama) && StringUtils.isNotEmpty(idKedua)) {
                                    if (idPertama.equalsIgnoreCase(permohonan.getIdPermohonan())) {
                                        idMohon = idPertama;
                                        id = senaraiHakmilikPermohonan.get(j).getId();
                                        System.out.println("id MH (1): " + id);
                                    } else if (idKedua.equalsIgnoreCase(permohonan.getIdPermohonan())) {
                                        idMohon = idKedua;
                                        id = senaraiHakmilikPermohonan.get(j).getId();
                                        System.out.println("id MH (2): " + id);
                                    }
                                }

                                listIdPermohonan.clear();
                                idPertama = "";
                                idKedua = "";
                            }
                        }

                    }



                    System.out.println("::: id : " + id);

                    if (id != null) {
                        LOG.info("::: using id MH");
                        senaraiHakmilikPermohonan = enforceService.findListMohonHakmilikById(id);
                    } else {
                        LOG.info("::: using id idPermohonan");
                        senaraiHakmilikPermohonan = enforceService.findListMohonHakmilik(idPermohonan, permohonan.getIdPermohonan());
                    }

                    LOG.info("size hakmilik permohonan for seksyen 49 : " + senaraiHakmilikPermohonan.size());
                    String idHakmilik = null;

                    for (int j = 0; j < senaraiHakmilikPermohonan.size(); j++) {
                        HakmilikPermohonan h = senaraiHakmilikPermohonan.get(j);
                        if (h.getHakmilik() != null) {
                            idHakmilik = h.getHakmilik().getIdHakmilik();
                        }
                    }

                    senaraiKehadiran = enforceService.findSenaraiPihak(idHakmilik);

                    LOG.info("size senarai kehadiran : " + senaraiKehadiran.size());
                } else {
                    LOG.info("::: For other than sek 49 MLK");
                    hakmilikpermohonan = enforceService.findSenaraiMohonHakmilik(idPermohonan);

                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                        ArrayList senaraiHakmilik = new ArrayList<String>();
                        if (!hakmilikpermohonan.isEmpty()) {
                            for (int j = 0; j < hakmilikpermohonan.size(); j++) {
                                HakmilikPermohonan hp = hakmilikpermohonan.get(j);
                                if (hp.getHakmilik() != null) {
                                    senaraiHakmilik.add(hp.getHakmilik().getIdHakmilik());
                                }
                            }

                            System.out.println("size senarai hakmilik : " + senaraiHakmilik.size());
                            if (!senaraiHakmilik.isEmpty()) {
                                senaraiKehadiran = enforceService.findPihakTerlibat(senaraiHakmilik);
                                LOG.info("size senarai kehadiran : " + senaraiKehadiran.size());
                            }


                        }

                    } else {
                        if (hakmilikpermohonan != null) {

                            if ("05".equalsIgnoreCase(conf.getProperty("kodNegeri"))) {
                                senaraiKehadiran = enforceService.findSenaraiPihakAktif(hakmilikpermohonan.get(0).getHakmilik().getIdHakmilik());
                            } else {
                                senaraiKehadiran = enforceService.findSenaraiPihak(hakmilikpermohonan.get(0).getHakmilik().getIdHakmilik());
                            }
                            //senaraiKehadiran = enforceService.findSenaraiPihak(hakmilikpermohonan.get(0).getHakmilik().getIdHakmilik());
                            LOG.info("size senarai kehadiran : " + senaraiKehadiran.size());

                        }
                    }

                }


                senaraiEnkuiri = enforceService.getSenaraiEnkuiri(permohonan.getIdPermohonan());

            } catch (Exception ex) {
                LOG.error(ex);
                ex.printStackTrace();
            }

            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pguna);
                stageId = t.getSystemAttributes().getStage();
                LOG.info("-------------stageId--" + stageId);
            } else {
                stageId = "jalan_enkuiri_7b";
            }
            System.out.println("stage id :" + stageId);

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    if (stageId.equalsIgnoreCase("sedia_siasat_tuantnh")) {
                        kodJenisEnkuiri = "2A";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    kodJenisEnkuiri = "7E";
                }


                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    if (stageId.equalsIgnoreCase("peraku_b2ab") || stageId.equalsIgnoreCase("sedia_warta_23ab") || stageId.equalsIgnoreCase("keputusan_enkuiri_2ab")
                            || stageId.equalsIgnoreCase("arah_endorsan") || stageId.equalsIgnoreCase("sahkan_warta_23ab") || stageId.equalsIgnoreCase("bantahan_1tahun")
                            || stageId.equalsIgnoreCase("kemasukan_bantahan") || stageId.equalsIgnoreCase("arah_siasatan")) {

                        kodJenisEnkuiri = "2A";
                    } else if (stageId.equalsIgnoreCase("keputusan_enkuiri_2ab2")) {
                        kodJenisEnkuiri = "2A2";
                    }

                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    if (stageId.equalsIgnoreCase("peraku_b2ab") || stageId.equalsIgnoreCase("sedia_warta_23ab") || stageId.equalsIgnoreCase("keputusan_enkuiri_2ab")
                            || stageId.equalsIgnoreCase("arah_endorsan") || stageId.equalsIgnoreCase("sahkan_warta_23ab") || stageId.equalsIgnoreCase("bantahan_1tahun")
                            || stageId.equalsIgnoreCase("kemasukan_bantahan") || stageId.equalsIgnoreCase("arah_siasatan")) {
                        kodJenisEnkuiri = "2B";
                    } else if (stageId.equalsIgnoreCase("keputusan_enkuiri_2ab2")) {
                        kodJenisEnkuiri = "2B2";
                    }
                }
            } else {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("127")) {
                    if (stageId.equalsIgnoreCase("sedia_borang_gantian7e") || stageId.equalsIgnoreCase("semak_gantian_borang7e") || stageId.equalsIgnoreCase("sedia_borang7e") || stageId.equalsIgnoreCase("syor_kpsn_enkuiri_b7e") || stageId.equalsIgnoreCase("kpsn_enkuiri_b7e")) {
                        kodJenisEnkuiri = "7E";
                    } else if (stageId.equalsIgnoreCase("jalan_enkuiri_7b") || stageId.equalsIgnoreCase("sedia_borang7b") || stageId.equalsIgnoreCase("peraku_borang7b") || stageId.equalsIgnoreCase("hantar_borang7b") || stageId.equalsIgnoreCase("sedia_borang_gantian7b") || stageId.equalsIgnoreCase("semak_gantian_borang7b") || stageId.equalsIgnoreCase("bukti_penyampaian_gantian_b7b") || stageId.equalsIgnoreCase("peraku_kertas_mmk")) {
                        kodJenisEnkuiri = "7B";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("351")) {
                    if (stageId.equalsIgnoreCase("semak2_laporan2") || stageId.equalsIgnoreCase("syor_arah_endorsan2")) {
                        kodJenisEnkuiri = "WA";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("352")) {
                    if (stageId.equalsIgnoreCase("semak2_laporan2") || stageId.equalsIgnoreCase("syor_arah_endorsan2")) {
                        kodJenisEnkuiri = "WB";
                    }
                } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("49")) {
                    kodJenisEnkuiri = "2A";
                }
            }


            try {
                String i = "";
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    i = ">";
                } else {
                    i = "<";
                }
                System.out.println(kodJenisEnkuiri);
                enkuiri = enforceService.getSenaraiPermohonanByTarikhEnkuiri(permohonan.getIdPermohonan(), sdf.parse(sdfToday.format(new Date()) + " 11:59 PM"), kodJenisEnkuiri, i);

//                System.out.println("enkuiri : " + enkuiri.getIdEnkuiri());
                if (enkuiri != null) {
                    tempat = new String(enkuiri.getTempat());
//                    catatan = new String(enkuiri.getCatatan());

                    if (enkuiri.getTarikhMula() != null) {
                        tarikhMula = sdfdisplay.format(enkuiri.getTarikhMula()).substring(0, 10);
                        jam = sdf.format(enkuiri.getTarikhMula()).substring(11, 13);
                        minit = sdf.format(enkuiri.getTarikhMula()).substring(14, 16);
                        ampm = sdf.format(enkuiri.getTarikhMula()).substring(17, 19);
                    }

                    senaraiPihakHadir = enforceService.findDetailsKehadiran(enkuiri.getIdEnkuiri());
                    recordCountSenaraiKehadiran = String.valueOf(senaraiPihakHadir.size());
                    LOG.info("size senarai pihak yang hadir : " + senaraiPihakHadir.size());
                    LOG.info("recordCountSenaraiKehadiran:   " + recordCountSenaraiKehadiran);

                    if (enkuiri.getUlasan() != null) {
                        ulasan = new String(enkuiri.getUlasan());
                        LOG.info("ulasan : " + ulasan);
                    }
                }
            } catch (Exception ex) {
                LOG.error(ex);
                ex.printStackTrace();
            }
        }
    }

    public Resolution simpan() {
        LOG.info("------------simpan Enkuiri : maklumat kehadiran --------------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        try {
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());

            String idEnkuiri = getContext().getRequest().getParameter("enkuiri.idEnkuiri");
            LOG.info("id enkuiri : " + idEnkuiri);
            enkuiri = enkuiriPenguatkuasaanDAO.findById(Long.parseLong(idEnkuiri));
            enkuiri.setUlasan(ulasan);
            enforceService.simpanEnkuiriPenguatkuasaan(enkuiri);

            senaraiPihakHadir = enforceService.findDetailsKehadiran(Long.parseLong(idEnkuiri));

            if (senaraiPihakHadir.size() == 0) {
                if (!(senaraiKehadiran.isEmpty())) {
                    for (int i = 0; i < senaraiKehadiran.size(); i++) {
                        LOG.info("simpan based on senarai kehadiran");
//                    String catatanKehadiran = getContext().getRequest().getParameter("catatan" + i);
                        String kehadiran = getContext().getRequest().getParameter("kehadiran" + i);
                        String namaPihak = getContext().getRequest().getParameter("nama" + i);
                        String noKp = getContext().getRequest().getParameter("noPengenalan" + i);
                        Character hadir = kehadiran.charAt(0);

                        enkuiriKehadiran = new EnkuiriPenguatkuasaanKehadiran();
                        enkuiriKehadiran.setInfoAudit(ia);
                        enkuiriKehadiran.setCawangan(peng.getKodCawangan());
                        enkuiriKehadiran.setEnkuiri(enkuiri);
                        enkuiriKehadiran.setNama(namaPihak);
                        enkuiriKehadiran.setNoPengenalan(noKp);
//                    enkuiriKehadiran.setCatatan(catatanKehadiran);
                        enkuiriKehadiran.setHadir(hadir);
                        enforceService.simpanEnkuiriKehadiran(enkuiriKehadiran);
                    }
                }

            } else {
                for (int j = 0; j < senaraiPihakHadir.size(); j++) {
                    LOG.info("simpan based on senarai kehadiran kkuasa_hadir");
//                    String catatanKehadiran = getContext().getRequest().getParameter("catatan" + j);
                    String kehadiran = getContext().getRequest().getParameter("kehadiran" + j);
                    Character hadir = kehadiran.charAt(0);

                    enkuiriKehadiran = senaraiPihakHadir.get(j);
                    enkuiriKehadiran.setInfoAudit(ia);
                    enkuiriKehadiran.setCawangan(peng.getKodCawangan());
                    enkuiriKehadiran.setEnkuiri(enkuiri);
//                    enkuiriKehadiran.setCatatan(catatanKehadiran);
                    enkuiriKehadiran.setHadir(hadir);
                    enforceService.simpanEnkuiriKehadiran(enkuiriKehadiran);

                }

            }


        } catch (Exception ex) {
            LOG.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("penguatkuasaan/maklumat_kehadiran_enkuiri.jsp").addParameter("tab", "true");
        return new RedirectResolution(EnkuiriKehadiranActionBean.class, "locate");
    }

    public Resolution simpanEnkuiri() {
        LOG.info("------------simpan Enkuiri--------------");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        System.out.println("stage id : " + stageId);

        try {

            enkuiri = new EnkuiriPenguatkuasaan();
            tarikhMula = tarikhMula + " " + jam + ":" + minit + " " + ampm;
            enkuiri.setTarikhMula(sdfdisplay.parse(tarikhMula));
            enkuiri.setPermohonan(permohonan);
            enkuiri.setInfoAudit(ia);
            enkuiri.setCawangan(peng.getKodCawangan());
            enkuiri.setTujuan("ENKUIRI");
            enkuiri.setTempat(tempat);
            enkuiri.setUlasan(ulasan);
            enkuiri.setStatus("A");
            enforceService.simpanEnkuiriPenguatkuasaan(enkuiri);

            for (int i = 0; i < senaraiKehadiran.size(); i++) {
                System.out.println("details senarai kehadiran");
//                String catatan = getContext().getRequest().getParameter("catatan" + i);
                String kehadiran = getContext().getRequest().getParameter("kehadiran" + i);
                String namaPihak = getContext().getRequest().getParameter("nama" + i);
                String noKp = getContext().getRequest().getParameter("noPengenalan" + i);
                Character hadir = kehadiran.charAt(0);
                enkuiriKehadiran = new EnkuiriPenguatkuasaanKehadiran();
                enkuiriKehadiran.setInfoAudit(ia);
                enkuiriKehadiran.setCawangan(peng.getKodCawangan());
                enkuiriKehadiran.setEnkuiri(enkuiri);
                enkuiriKehadiran.setNama(namaPihak);
                enkuiriKehadiran.setNoPengenalan(noKp);
//                enkuiriKehadiran.setCatatan(catatan);
                enkuiriKehadiran.setHadir(hadir);
                enforceService.simpanEnkuiriKehadiran(enkuiriKehadiran);
            }
        } catch (Exception ex) {
            LOG.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        return new JSP("penguatkuasaan/maklumat_kehadiran_enkuiri.jsp").addParameter("tab", "true");
        return new RedirectResolution(EnkuiriKehadiranActionBean.class, "locate");
    }

    public Resolution simpanKehadiran() {
        LOG.info("------------simpanKehadiran--------------");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        if (StringUtils.isNotBlank(idKehadiran)) {
            enkuiriKehadiran = enkuiriPenguatkuasaanKehadiranDAO.findById(Long.parseLong(idKehadiran));
        }
        System.out.println("stage id : " + stageId);

        try {
            if (enkuiriKehadiran == null) {
                System.out.println("new kehadiran");
                enkuiriKehadiran = new EnkuiriPenguatkuasaanKehadiran();
            } else {
                System.out.println("edit kehadiran");
                ia = enkuiriKehadiran.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            Character hadir = statusKehadiran.charAt(0);
            enkuiriKehadiran.setInfoAudit(ia);
            enkuiriKehadiran.setCawangan(pguna.getKodCawangan());
            enkuiriKehadiran.setEnkuiri(enkuiri);
            enkuiriKehadiran.setNama(nama);
            enkuiriKehadiran.setNoPengenalan(noPengenalan);
            enkuiriKehadiran.setHadir(hadir);
            enkuiriKehadiran.setNoRujukan(pilihPihak);

            Alamat a = new Alamat();

            if (enkuiriKehadiran.getAlamat() != null) {
                a = enkuiriKehadiran.getAlamat();
            }

            a.setAlamat1(alamat1);
            a.setAlamat2(alamat2);
            a.setAlamat3(alamat3);
            a.setAlamat4(alamat4);
            a.setPoskod(poskod);

            if (StringUtils.isNotBlank(negeri)) {
                a.setNegeri(kodNegeriDAO.findById(negeri));

            }

            enkuiriKehadiran.setAlamat(a);

            if (statusPB == true) {
                enkuiriKehadiran.setNamaPihakBerkepentingan(namaPb);
                enkuiriKehadiran.setHubunganPihakBerkepentingan(hubunganPb);
                enkuiriKehadiran.setJenisPengenalanPihakBerkepentingan(kodJenisPengenalanDAO.findById(kodPengenalanPb));
                if (StringUtils.isNotBlank(kodPengenalanPb)) {
                    if (kodPengenalanPb.equalsIgnoreCase("B")) {
                        enkuiriKehadiran.setNoPengenalanPihakBerkepentingan(noPengenalanPbBaru);
                    } else {
                        enkuiriKehadiran.setNoPengenalanPihakBerkepentingan(noPengenalanPbLain);
                    }
                }
            }
            enforceService.simpanEnkuiriKehadiran(enkuiriKehadiran);
        } catch (Exception ex) {
            LOG.error(ex);
            ex.printStackTrace();
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/maklumat_kehadiran_enkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRecorKehadiran() {
        LOG.info("::deleteRecorKehadiran");

        idKehadiran = getContext().getRequest().getParameter("idKehadiran");
        if (StringUtils.isNotBlank(idKehadiran)) {
            enkuiriKehadiran = enkuiriPenguatkuasaanKehadiranDAO.findById(Long.parseLong(idKehadiran));
            enforceService.deleteKehadiran(enkuiriKehadiran);

        }
        return new RedirectResolution(EnkuiriKehadiranActionBean.class, "locate");
    }

    public Resolution findPihak() {
        LOG.info(":::::::find Pihak");
        String id = getContext().getRequest().getParameter("id");

        if (StringUtils.isNotBlank(id)) {
            Pihak pihak = pihakDAO.findById(Long.parseLong(id));

            if (pihak != null) {
                nama = pihak.getNama();
                noPengenalan = pihak.getNoPengenalan();
                alamat1 = pihak.getAlamat1();
                alamat2 = pihak.getAlamat2();
                alamat3 = pihak.getAlamat3();
                alamat4 = pihak.getAlamat4();
                poskod = pihak.getPoskod();
                if (pihak.getNegeri() != null) {
                    negeri = pihak.getNegeri().getKod();
                }

                if (pihak.getBangsa() != null) {
                    if (!pihak.getBangsa().getKod().equalsIgnoreCase("MEL")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("CIN")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("IND")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("SIM")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("ASL")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("PTT")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("PLN")
                            && !pihak.getBangsa().getKod().equalsIgnoreCase("LN")) {
                        statusPB = true;
                    }
                }


                LOG.info("namaCarian : " + nama);
                LOG.info("noPengenalanCarian : " + noPengenalan);
                LOG.info("alamat1 : " + alamat1);
                LOG.info("alamat2 : " + alamat2);
                LOG.info("alamat3 : " + alamat3);
                LOG.info("alamat4 : " + alamat4);
                LOG.info("poskod : " + poskod);
                LOG.info("negeri : " + negeri);
                LOG.info("statusPB : " + statusPB);


            }
        }


        return new JSP("penguatkuasaan/popup_tambah_kehadiran.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(EnkuiriKehadiranActionBean.class, "locate");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public EnkuiriPenguatkuasaan getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(EnkuiriPenguatkuasaan enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(String idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public List<HakmilikPermohonan> getHakmilikpermohonan() {
        return hakmilikpermohonan;
    }

    public void setHakmilikpermohonan(List<HakmilikPermohonan> hakmilikpermohonan) {
        this.hakmilikpermohonan = hakmilikpermohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiKehadiran() {
        return senaraiKehadiran;
    }

    public void setSenaraiKehadiran(List<HakmilikPihakBerkepentingan> senaraiKehadiran) {
        this.senaraiKehadiran = senaraiKehadiran;
    }

    public EnkuiriPenguatkuasaanKehadiran getEnkuiriKehadiran() {
        return enkuiriKehadiran;
    }

    public void setEnkuiriKehadiran(EnkuiriPenguatkuasaanKehadiran enkuiriKehadiran) {
        this.enkuiriKehadiran = enkuiriKehadiran;
    }

    public List<EnkuiriPenguatkuasaan> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<EnkuiriPenguatkuasaan> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<EnkuiriPenguatkuasaanKehadiran> getDetailsKehadiran() {
        return detailsKehadiran;
    }

    public void setDetailsKehadiran(List<EnkuiriPenguatkuasaanKehadiran> detailsKehadiran) {
        this.detailsKehadiran = detailsKehadiran;
    }

    public List<CalendarEnkuiri> getListCalendar() {
        return listCalendar;
    }

    public void setListCalendar(List<CalendarEnkuiri> listCalendar) {
        this.listCalendar = listCalendar;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<EnkuiriPenguatkuasaanKehadiran> getSenaraiPihakHadir() {
        return senaraiPihakHadir;
    }

    public void setSenaraiPihakHadir(List<EnkuiriPenguatkuasaanKehadiran> senaraiPihakHadir) {
        this.senaraiPihakHadir = senaraiPihakHadir;
    }

    public String getRecordCountSenaraiKehadiran() {
        return recordCountSenaraiKehadiran;
    }

    public void setRecordCountSenaraiKehadiran(String recordCountSenaraiKehadiran) {
        this.recordCountSenaraiKehadiran = recordCountSenaraiKehadiran;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(String idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getStatusKehadiran() {
        return statusKehadiran;
    }

    public void setStatusKehadiran(String statusKehadiran) {
        this.statusKehadiran = statusKehadiran;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getIdKehadiran() {
        return idKehadiran;
    }

    public void setIdKehadiran(String idKehadiran) {
        this.idKehadiran = idKehadiran;
    }

    public String getPilihPihak() {
        return pilihPihak;
    }

    public void setPilihPihak(String pilihPihak) {
        this.pilihPihak = pilihPihak;
    }

    public String getKodJenisEnkuiri() {
        return kodJenisEnkuiri;
    }

    public void setKodJenisEnkuiri(String kodJenisEnkuiri) {
        this.kodJenisEnkuiri = kodJenisEnkuiri;
    }

    public Boolean getMaklumatTanahSek49() {
        return maklumatTanahSek49;
    }

    public void setMaklumatTanahSek49(Boolean maklumatTanahSek49) {
        this.maklumatTanahSek49 = maklumatTanahSek49;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public ArrayList getListIdPermohonan() {
        return listIdPermohonan;
    }

    public void setListIdPermohonan(ArrayList listIdPermohonan) {
        this.listIdPermohonan = listIdPermohonan;
    }

    public String[] getSenaraiIdPermohonan() {
        return senaraiIdPermohonan;
    }

    public void setSenaraiIdPermohonan(String[] senaraiIdPermohonan) {
        this.senaraiIdPermohonan = senaraiIdPermohonan;
    }

    public String getIdPertama() {
        return idPertama;
    }

    public void setIdPertama(String idPertama) {
        this.idPertama = idPertama;
    }

    public String getIdKedua() {
        return idKedua;
    }

    public void setIdKedua(String idKedua) {
        this.idKedua = idKedua;
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

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getKodBangsa() {
        return kodBangsa;
    }

    public void setKodBangsa(String kodBangsa) {
        this.kodBangsa = kodBangsa;
    }

    public String getNamaPb() {
        return namaPb;
    }

    public void setNamaPb(String namaPb) {
        this.namaPb = namaPb;
    }

    public String getNoPengenalanPbBaru() {
        return noPengenalanPbBaru;
    }

    public void setNoPengenalanPbBaru(String noPengenalanPbBaru) {
        this.noPengenalanPbBaru = noPengenalanPbBaru;
    }

    public String getNoPengenalanPbLain() {
        return noPengenalanPbLain;
    }

    public void setNoPengenalanPbLain(String noPengenalanPbLain) {
        this.noPengenalanPbLain = noPengenalanPbLain;
    }

    public String getKodPengenalanPb() {
        return kodPengenalanPb;
    }

    public void setKodPengenalanPb(String kodPengenalanPb) {
        this.kodPengenalanPb = kodPengenalanPb;
    }

    public String getHubunganPb() {
        return hubunganPb;
    }

    public void setHubunganPb(String hubunganPb) {
        this.hubunganPb = hubunganPb;
    }

    public Boolean getStatusPB() {
        return statusPB;
    }

    public void setStatusPB(Boolean statusPB) {
        this.statusPB = statusPB;
    }
}
