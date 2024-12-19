package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodUrusan;
import etanah.model.AduanLokasi;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPengenalan;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodNegeri;
import etanah.model.Alamat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodPemilikan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import etanah.service.AduanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.lang.StringUtils;
import etanah.model.KodCawangan;
import etanah.model.KodLot;
import etanah.model.Kompaun;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.BPelService;
import java.util.Date;
import etanah.workflow.WorkFlowService;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.text.DateFormat;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.PegawaiPenyiasat;
import etanah.model.PermohonanNota;

@UrlBinding("/penguatkuasaan/senarai_aduan")
public class SenaraiAduanActionBean extends AbleActionBean {

    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanService aduanService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    private KompaunDAO kompaunDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KodLotDAO kodLotDAO;
    @Inject
    private KodBandarPekanMukimDAO kodbandarPekanMukimDAO;
    @Inject
    private KodCaraPermohonanDAO kodCaraPermohonanDAO;
    @Inject
    private KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private KodPemilikanDAO kodPemilikanDAO;
    private static final Logger log = Logger.getLogger(SenaraiAduanActionBean.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm ss");
    private SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
    //public List senaraiAduan = new ArrayList();
    private String beanFlag = "senarai_aduan";
    private String id;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private String idPermohonan;
    private KodJenisPengenalan penyerahJenisPengenalan;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private KodNegeri penyerahNegeri;
    private String penyerahEmail;
    private String penyerahNoTelefon1;
    private AduanLokasi aduanLokasi;
    private String idOrangKenaSyak;
    private KodCawangan cawangan;
    private AduanOrangKenaSyak aduanOrangKenaSyak;
    private KodCaraPermohonan caraPermohonan;
    private KodUrusan kodUrusan;
    private List<Permohonan> senaraiAduan;
    private List<KodUrusan> senaraiUrusan;
    private List<AduanLokasi> senaraiAduanLokasi;
    private List<AduanOrangKenaSyak> senaraiOrangKenaSyak;
    private List<KodCaraPermohonan> senaraiKodCaraPermohonan;
    private List<KodBandarPekanMukim> senaraiKodBandarPekanMukim;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private String sebab;
    private Date fromDate;
    public String untilDate;
    public String idInsert = new String();
    private String nama;
    private Alamat alamat;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeriO;
    private String noPengenalan;
    private String noTelefon1;
    private KodPemilikan pemilikan;
    private KodBandarPekanMukim bandarPekanMukim;
    private String noLot;
    private String lokasi;
    private String idAduanLokasi;
    DateFormat formatter;
    private String stageId;
    private String keputusan;
    private String kodNegeri;
    private List<Kompaun> senaraiKompaun;
    private PegawaiPenyiasat pegawaiSiasat;
    private String idPengguna;
    private PermohonanNota permohonanNota;
    private String taskId;
    private KodLot kodLot;
    private String statusJanaDokumen;
    private String statusJanaDokumenRAP;
    private PermohonanNota currentPermohonanNota;
    private Boolean perihalSeksyen = Boolean.FALSE;
    private Boolean editPerihalSeksyen = Boolean.FALSE;
    private String catatanSeksyen;
    private String editSeksyen;
    private String specificSection;
    private Boolean editRingkasanAduan = Boolean.FALSE;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private boolean hideTab; //Hafifi : for N9 only
    private String penyerahNoPengenalanBaru;
    private String penyerahNoPengenalanLain;
    private String caraMohon;
    private String jenisPengenalanPenyerah;
    private String kodNegeriPenyerah;
    private String kodBpm;
    private String jenisMilik;
    private String kodLotTanah;

    public Resolution showForm() {
        return new JSP("penguatkuasaan/kemasukan_aduan_pt.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_pengadu_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        kodNegeri = conf.getProperty("kodNegeri");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("task id : " + taskId);

        if (!pguna.getKodCawangan().getKod().matches("00")) {
            String kodDaerah = pguna.getKodCawangan().getDaerah().getKod();
            getContext().getRequest().setAttribute("kodDaerah", kodDaerah);
            String daerah = pguna.getKodCawangan().getDaerah().getNama();
            getContext().getRequest().setAttribute("daerah", daerah);
        }
        senaraiKodCaraPermohonan = aduanService.getSenaraiKodCaraPermohonan();
        senaraiKodBandarPekanMukim = aduanService.getSenaraiKodBandarPekanMukim();
        senaraiAduan = aduanService.getSenaraiPermohonanByKodUrusan();

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            } else {
                sebab = permohonan.getSebab();
                if (permohonan.getCaraPermohonan() != null) {
                    caraMohon = permohonan.getCaraPermohonan().getKod();
                }

                penyerahNama = permohonan.getPenyerahNama();
                if (permohonan.getPenyerahJenisPengenalan() != null) {
                    jenisPengenalanPenyerah = permohonan.getPenyerahJenisPengenalan().getKod();

                    if (permohonan.getPenyerahJenisPengenalan().getKod().equalsIgnoreCase("B")) {
                        penyerahNoPengenalanBaru = permohonan.getPenyerahNoPengenalan();
                    } else {
                        penyerahNoPengenalanLain = permohonan.getPenyerahNoPengenalan();
                    }
                }

                if (permohonan.getPenyerahNegeri() != null) {
                    kodNegeriPenyerah = permohonan.getPenyerahNegeri().getKod();
                }
                penyerahAlamat1 = permohonan.getPenyerahAlamat1();
                penyerahAlamat2 = permohonan.getPenyerahAlamat2();
                penyerahAlamat3 = permohonan.getPenyerahAlamat3();
                penyerahAlamat4 = permohonan.getPenyerahAlamat4();
                penyerahPoskod = permohonan.getPenyerahPoskod();
                penyerahEmail = permohonan.getPenyerahEmail();
                penyerahNoTelefon1 = permohonan.getPenyerahNoTelefon1();
            }

            if (aduanLokasi != null) {

                if (aduanLokasi.getBandarPekanMukim() != null) {
                    kodBpm = Integer.toString(aduanLokasi.getBandarPekanMukim().getKod());
                }
                if (aduanLokasi.getPemilikan() != null) {
                    jenisMilik = Character.toString(aduanLokasi.getPemilikan().getKod());
                }
                if (aduanLokasi.getKodLot() != null) {
                    kodLotTanah = aduanLokasi.getKodLot().getKod();
                }
                noLot = aduanLokasi.getNoLot();
                lokasi = aduanLokasi.getLokasi();
            }
        }
        if (!pguna.getKodCawangan().getKod().matches("00")) {
            cawangan = pguna.getKodCawangan();
            String bandarPekanMukim1 = cawangan.getDaerah().getKod();
            if (StringUtils.isNotBlank(bandarPekanMukim1)) {
                setListBandarPekanMukim(enforceService.getSenaraiKodBPMByCawangan(bandarPekanMukim1));
            }
        }

        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task task = null;
            task = service.getTaskFromBPel(taskId, pguna);
            if (task != null) {
                stageId = task.getSystemAttributes().getStage();
                log.info("--------------stage Id BPEL ON--------------- : " + stageId);
            }
        } else {
            stageId = "tindakan_simpan_barang";
            log.info("--------------stage Id BPEL OFF--------------- : " + stageId);
        }

        List<FasaPermohonan> senaraiFasa = permohonan.getSenaraiFasa();
        for (FasaPermohonan fp : senaraiFasa) {
            if (fp.getIdAliran().equalsIgnoreCase(stageId)) {
                if (fp.getKeputusan() != null) {
                    keputusan = fp.getKeputusan().getKod();
                    log.info("--------------keputusan untuk stage " + stageId + "--------------- : " + keputusan);
                }

            }
        }

        if (StringUtils.isNotBlank(stageId)) {
            //find current permohonan nota
            currentPermohonanNota = enforcementService.findCurrentNotaMinit(permohonan.getIdPermohonan(), stageId);

            log.info("status current nota/minit :" + currentPermohonanNota);

            if (currentPermohonanNota == null) {
                log.info("::::: Create new mohon nota with satus A = aktif");
                InfoAudit ia = new InfoAudit();
                permohonanNota = new PermohonanNota();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());

                permohonanNota.setPermohonan(permohonan);
                permohonanNota.setInfoAudit(ia);
                permohonanNota.setCawangan(pguna.getKodCawangan());
                permohonanNota.setIdAliran(stageId);
                permohonanNota.setStatusNota('A');
                enforceService.simpanNota(permohonanNota);
            }

        }

        if ("05".equals(conf.getProperty("kodNegeri"))) {
            if (stageId.equalsIgnoreCase("imbas_aduan")) {
                editRingkasanAduan = true;
            } else {
                editRingkasanAduan = false;
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
                if (StringUtils.isNotBlank(stageId)) {
                    if (stageId.equalsIgnoreCase("maklum_aduan")) {
                        editPerihalSeksyen = true;
                    } else {
                        editPerihalSeksyen = false;
                    }
                }
            } else {
                if (StringUtils.isNotBlank(stageId)) {
                    if (stageId.equalsIgnoreCase("sedia_laporan1")) {
                        editPerihalSeksyen = true;
                    } else {
                        editPerihalSeksyen = false;
                    }
                }

            }

            perihalSeksyen = true;

            catatanSeksyen = permohonan.getCatatan();
            sebab = permohonan.getSebab();

            if (permohonan.getRujukanUndang2() == null && !permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
                editSeksyen = permohonan.getKodUrusan().getNama();
            } else {
                editSeksyen = permohonan.getRujukanUndang2();
                specificSection = permohonan.getRujukanUndang2();
            }


            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("422") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("423") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("424") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("427") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("428")) {
                log.info("--------------keputusan--------------- : " + keputusan);
                if (StringUtils.isNotBlank(keputusan)) {
                    if (!keputusan.equalsIgnoreCase("C")) {

                        Session s = sessionProvider.get();
                        Transaction tx = s.beginTransaction();

                        //clear value in amounKompaunSyor : table aduan_oks
                        senaraiOrangKenaSyak = enforceService.findByID(idPermohonan);
                        for (int i = 0; i < senaraiOrangKenaSyak.size(); i++) {
                            AduanOrangKenaSyak oks = senaraiOrangKenaSyak.get(i);
                            if (oks.getAmaunKompaunSyor() != null) {
                                oks.setAmaunKompaunSyor(null);
                                enforceService.editOrangKenaSyak(oks);
                            }
                        }

                        //delete record in  : table mohon_tuntut_kos then table kompaun
                        senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);
                        for (int i = 0; i < senaraiKompaun.size(); i++) {
                            Kompaun k = senaraiKompaun.get(i);
                            if (k.getPermohonanTuntutanKos() != null) {
                                PermohonanTuntutanKos ptk = permohonanTuntutanKosDAO.findById(k.getPermohonanTuntutanKos().getIdKos());
                                if (ptk != null) {
                                    permohonanTuntutanKosDAO.delete(ptk);
                                    kompaunDAO.delete(k);
                                }
                            }
                        }

                        //delete report
                        if (permohonan.getFolderDokumen() != null) {
                            senaraiKandungan = enforceService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
                            if (senaraiKandungan.size() != 0) {
                                for (KandunganFolder kf : senaraiKandungan) {
                                    Dokumen dokumen = kf.getDokumen();
                                    if (dokumen.getKodDokumen() != null) {
                                        if (dokumen.getKodDokumen().getKod().equalsIgnoreCase("SID")) {
                                            log.info("------------id dokumen----------------- : " + dokumen.getIdDokumen());
                                            dokumenDAO.delete(dokumen);

                                        }
                                    }
                                }
                            }
                        }

                        tx.commit();

                    }
                }


            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
                if (operasiPenguatkuasaan != null) {
                    if (operasiPenguatkuasaan.getTarikhBerkumpul() != null) {
                        if (operasiPenguatkuasaan.getTarikhBerkumpul().after(new Date())) {
                            hideTab = true;
                        } else {
                            hideTab = false;
                        }
                    } else {
                        hideTab = false;
                    }

                    System.out.println(hideTab);
                }
            }
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425")) {

                pegawaiSiasat = enforcementService.findPengguna(permohonan.getIdPermohonan());
                if (pegawaiSiasat != null) {
                    idPengguna = pegawaiSiasat.getNamaJabatan();
                    log.info("id Pengguna for agihan tugasan : " + idPengguna);

                    permohonanNota = enforceService.findNotaByIdMohon(idPermohonan, stageId);
                    log.info("------------permohonanNota-------------- : " + permohonanNota);
                }
            }

            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(permohonan.getFolderDokumen().getFolderId(), "LPOP");
                for (int i = 0; i < listDokumen.size(); i++) {
                    Dokumen d = listDokumen.get(i).getDokumen();
                    if (d != null) {
                        statusJanaDokumen = d.getPerihal();
                        log.info("------------statusJanaDokumen-------------- : " + statusJanaDokumen);

                    }
                }

            }


            List<KandunganFolder> listDokumen = enforcementService.getSenaraiDokumen(permohonan.getFolderDokumen().getFolderId(), "RAP");
            for (int i = 0; i < listDokumen.size(); i++) {
                Dokumen d = listDokumen.get(i).getDokumen();
                if (d != null) {
                    statusJanaDokumenRAP = d.getPerihal();
                    log.info("------------statusJanaDokumen RAP -------------- : " + statusJanaDokumenRAP);

                }
            }


//            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
//                if (StringUtils.isNotBlank(permohonan.getRujukanUndang2())) {
//                    if (permohonan.getRujukanUndang2().equalsIgnoreCase("127") || permohonan.getRujukanUndang2().equalsIgnoreCase("425")
//                            || permohonan.getRujukanUndang2().equalsIgnoreCase("425A") || permohonan.getRujukanUndang2().equalsIgnoreCase("426")) {
//                        if (stageId.equalsIgnoreCase("maklum_aduan1")) {
//                            log.info("------------set rujukan undang2 to null--------------");
//                            InfoAudit ia = permohonan.getInfoAudit();
//                            ia.setDiKemaskiniOleh(pengguna);
//                            ia.setTarikhKemaskini(new java.util.Date());
//
//                            permohonan.setRujukanUndang2(null);
//                            permohonan.setInfoAudit(ia);
//                            enforceService.savePermohonan(permohonan);
//                        }
//                    }
//                }
//
//            }

        }


    }

    public Resolution refreshpage() {
        return new RedirectResolution(SenaraiAduanActionBean.class, "locate");
    }

    @DefaultHandler
    public Resolution senaraiAduan() {
        log.debug("senaraiAduan");
//        senaraiAduan = aduanService.getSenaraiPermohonanByKodUrusan();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_aduan.jsp");
    }

    public Resolution peruntukanSeksyen() {
        log.debug("peruntukanSeksyen");

        permohonan = permohonanDAO.findById(idPermohonan);
        sebab = permohonan.getSebab();
        penyerahNama = permohonan.getPenyerahNama();
        penyerahNoPengenalan = permohonan.getPenyerahNoPengenalan();
        penyerahAlamat1 = permohonan.getPenyerahAlamat1();
        penyerahAlamat2 = permohonan.getPenyerahAlamat2();
        penyerahAlamat3 = permohonan.getPenyerahAlamat3();
        penyerahAlamat4 = permohonan.getPenyerahAlamat4();
        penyerahPoskod = permohonan.getPenyerahPoskod();
        penyerahNoTelefon1 = permohonan.getPenyerahNoTelefon1();
        penyerahEmail = permohonan.getPenyerahEmail();
        if (permohonan.getFolderDokumen() != null) {
            senaraiKandungan = permohonan.getFolderDokumen().getSenaraiKandungan();
        }

        senaraiUrusan = aduanService.getSenaraiUrusan();
        senaraiAduanLokasi = aduanService.findSenaraiLokasiByID(idPermohonan);
        senaraiOrangKenaSyak = aduanService.findSenaraiOKSByID(idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/peruntukan_seksyen.jsp");
    }

    public Resolution searchAllPermohonan() throws ParseException {

        log.debug("searchAllPermohonan :" + getFromDate());
        System.out.println(" FromDate---------" + fromDate);
        System.out.println(" idInsert---------" + idInsert);
        if (idInsert != null && fromDate == null) {
            senaraiAduan = aduanService.getSenaraiPermohonanByIdPermohonan(idInsert, fromDate);
        } else if (idInsert == null && fromDate != null) {
            formatter = new SimpleDateFormat("dd/mm/yyyy");
            formatter.format(fromDate);
            senaraiAduan = aduanService.getSenaraiPermohonanByIdPermohonan(idInsert, fromDate);
        }
        if (idInsert != null && fromDate != null) {
            addSimpleError("Sila masukkan samada ID Aduan ATAU Tarikh Dari");

        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_aduan.jsp");
    }

    public Resolution updateAduan() {
        // log.debug("updateAduan");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        try {
            permohonan = permohonanDAO.findById(idPermohonan);

            permohonan.setSebab(sebab);
            permohonan.setCaraPermohonan(caraPermohonan);
            permohonan.setPenyerahNama(penyerahNama);
            permohonan.setPenyerahJenisPengenalan(penyerahJenisPengenalan);
//            permohonan.setPenyerahNoPengenalan(penyerahNoPengenalan);
            if (penyerahJenisPengenalan != null) {
                if (penyerahJenisPengenalan.getKod().equalsIgnoreCase("B")) {
                    permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanBaru);
                } else {
                    permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
                }
            } else {
                permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
            }
            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);
            permohonan.setPenyerahNegeri(penyerahNegeri);
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon1);
            permohonan.setPenyerahEmail(penyerahEmail);
            permohonan.setKodUrusan(kodUrusan);
            InfoAudit iaPermohonan = new InfoAudit();
            iaPermohonan = permohonan.getInfoAudit();

            iaPermohonan.setTarikhKemaskini(new Date());
            iaPermohonan.setDiKemaskiniOleh(pengguna);

            permohonan.setInfoAudit(iaPermohonan);
            if (permohonan.getFolderDokumen() == null) {
                FolderDokumen fd = new FolderDokumen();
                fd.setTajuk(idPermohonan);
                fd.setInfoAudit(iaPermohonan);
                folderDokumenDAO.save(fd);
                permohonan.setFolderDokumen(fd);
            }
            permohonanDAO.saveOrUpdate(permohonan);

            KodUrusan ku = kodUrusanDAO.findById(kodUrusan.getKod());
            if (ku.getKePTG() == 'Y') {
                WorkFlowService.initiateTask(ku.getIdWorkflow(),
                        idPermohonan, pengguna.getKodCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        ku.getNama());
            } else if (ku.getKePTG() == 'T') {
                WorkFlowService.initiateTask(ku.getIdWorkflow(),
                        idPermohonan, pengguna.getKodCawangan().getKod(), pengguna.getIdPengguna(),
                        ku.getNama());
            }
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            Throwable t = e;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            t.printStackTrace();
            log.error(t);
        }
        senaraiAduan = aduanService.getSenaraiPermohonanByKodUrusan();
        kodUrusan = kodUrusanDAO.findById(kodUrusan.getKod());
        addSimpleMessage("Urusan telah berjaya didaftarkan.\n\n<br> " + kodUrusan.getNama());
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/senarai_aduan.jsp");
    }

    public Resolution oksPopup() {
        log.debug("oksPopup");
        return new JSP("penguatkuasaan/oksPopupSave.jsp").addParameter("popup", "true");
    }

    public Resolution oksSave() {
        log.debug("oksSave");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        AduanOrangKenaSyak aoks = new AduanOrangKenaSyak();
        aoks.setPermohonan(permohonan);
        aoks.setCawangan(pengguna.getKodCawangan());
        aoks.setNama(nama);
        aoks.setNoPengenalan(noPengenalan);
        aoks.setNoTelefon1(noTelefon1);
        Alamat al = new Alamat();
        al.setAlamat1(alamat1);
        al.setAlamat2(alamat2);
        al.setAlamat3(alamat3);
        al.setAlamat4(alamat4);
        al.setPoskod(poskod);
        if (negeriO != null) {
            KodNegeri kn = new KodNegeri();
            kn = kodNegeriDAO.findById(negeriO);
            al.setNegeri(kn);
        }
        aoks.setAlamat(al);

        InfoAudit iaOKS = new InfoAudit();
        iaOKS.setTarikhMasuk(new Date());
        iaOKS.setDimasukOleh(pengguna);
        aoks.setInfoAudit(iaOKS);

        aduanOrangKenaSyakDAO.saveOrUpdate(aoks);
        tx.commit();

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/oksPopupSave.jsp").addParameter("popup", "true");
    }
//     @HandlesEvent("refreshPage")

    public Resolution refreshPage() {
        log.debug("refreshPage");
        return peruntukanSeksyen();
    }

    public Resolution removeOKS() {
        log.debug("removeOKS");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        AduanOrangKenaSyak aoks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));
        aduanOrangKenaSyakDAO.delete(aoks);
        tx.commit();

        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return peruntukanSeksyen();
    }

    public Resolution oksPopup2() {
        log.debug("oksPopup2");
        AduanOrangKenaSyak aoks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));
        nama = aoks.getNama();
        noPengenalan = aoks.getNoPengenalan();
        noTelefon1 = aoks.getNoTelefon1();
        alamat1 = aoks.getAlamat().getAlamat1();
        alamat2 = aoks.getAlamat().getAlamat2();
        alamat3 = aoks.getAlamat().getAlamat3();
        alamat4 = aoks.getAlamat().getAlamat4();
        poskod = aoks.getAlamat().getPoskod();
        negeriO = aoks.getAlamat().getNegeri().getKod();
        return new JSP("penguatkuasaan/oksPopupEdit.jsp").addParameter("popup", "true");
    }

    public Resolution editOKS() {
        log.debug("editOKS");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();

        AduanOrangKenaSyak aoks = aduanOrangKenaSyakDAO.findById(Long.parseLong(idOrangKenaSyak));
        aoks.setNama(nama);
        aoks.setNoPengenalan(noPengenalan);
        aoks.setNoTelefon1(noTelefon1);
        Alamat al = new Alamat();
        al.setAlamat1(alamat1);
        al.setAlamat2(alamat2);
        al.setAlamat3(alamat3);
        al.setAlamat4(alamat4);
        al.setPoskod(poskod);
        if (negeriO != null) {
            KodNegeri kn = new KodNegeri();
            kn = kodNegeriDAO.findById(negeriO);
            al.setNegeri(kn);
        }
        aoks.setAlamat(al);

        InfoAudit iaOKS = aoks.getInfoAudit();
        // need to set the exact date for Permohonan
        iaOKS.setTarikhKemaskini(new Date());
        iaOKS.setDiKemaskiniOleh(pengguna);
        aoks.setInfoAudit(iaOKS);

        aduanOrangKenaSyakDAO.saveOrUpdate(aoks);
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new JSP("penguatkuasaan/oksPopupEdit.jsp").addParameter("popup", "true");
    }

    public Resolution lokasiPopup() {
        log.debug("lokasiPopup");
        return new JSP("penguatkuasaan/lokasiPopupSave.jsp").addParameter("popup", "true");
    }

    public Resolution lokasiSave() {
        log.debug("lokasiSave");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        permohonan = permohonanDAO.findById(idPermohonan);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        AduanLokasi al = new AduanLokasi();
        al.setPermohonan(permohonan);
        al.setLokasi(lokasi);
        al.setKodLot(kodLot);
        al.setNoLot(noLot);
        al.setCawangan(pengguna.getKodCawangan());
        al.setBandarPekanMukim(bandarPekanMukim);
        al.setPemilikan(pemilikan);
        InfoAudit iaLokasi = new InfoAudit();
        iaLokasi.setTarikhMasuk(new Date());
        iaLokasi.setDimasukOleh(pengguna);
        al.setInfoAudit(iaLokasi);
        aduanLokasiDAO.save(al);
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/peruntukan_seksyen.jsp");
    }

    public Resolution removeLokasi() {
        log.debug("removeLokasi");

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        AduanLokasi al = aduanLokasiDAO.findById(Long.parseLong(idAduanLokasi));
        aduanLokasiDAO.delete(al);
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya dihapus.");
        return peruntukanSeksyen();

    }

    public Resolution lokasiPopup2() {
        log.debug("lokasiPopup2");
        AduanLokasi al = aduanLokasiDAO.findById(Long.parseLong(idAduanLokasi));
        lokasi = al.getLokasi();
        noLot = al.getNoLot();
        kodLot = al.getKodLot();
        pemilikan = al.getPemilikan();
        bandarPekanMukim = al.getBandarPekanMukim();
        return new JSP("penguatkuasaan/lokasiPopupEdit.jsp").addParameter("popup", "true");
    }

    public Resolution editLokasi() {
        log.debug("editLokasi");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        AduanLokasi al = aduanLokasiDAO.findById(Long.parseLong(idAduanLokasi));
        al.setLokasi(lokasi);
        al.setNoLot(noLot);
        al.setKodLot(kodLot);
        al.setBandarPekanMukim(bandarPekanMukim);
        al.setPemilikan(pemilikan);
        InfoAudit iaLokasi = al.getInfoAudit();
        iaLokasi.setTarikhKemaskini(new Date());
        iaLokasi.setDiKemaskiniOleh(pengguna);
        al.setInfoAudit(iaLokasi);
        aduanLokasiDAO.saveOrUpdate(al);
        tx.commit();
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        return new JSP("penguatkuasaan/peruntukan_seksyen.jsp");
    }

    public Resolution updatePerihalSeksyen() {
        log.info("::::: updatePerihalSeksyen");

        InfoAudit ia = new InfoAudit();
        if (permohonan != null) {
            ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonan.setCatatan(catatanSeksyen);
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
            log.info("::: specific section : " + specificSection);
            permohonan.setRujukanUndang2(specificSection);
        } else {
            permohonan.setRujukanUndang2(editSeksyen);
        }

        if (sebab != null) {
            permohonan.setSebab(sebab);
        }

        permohonan.setInfoAudit(ia);
        enforceService.savePermohonan(permohonan);

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_pengadu_view.jsp").addParameter("tab", "true");
    }

    public Resolution saveMohon() {
        log.debug("saveMohon");

        InfoAudit ia = new InfoAudit();
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        if (permohonan != null) {
            ia = permohonan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());


            if (StringUtils.isNotBlank(caraMohon)) {
                permohonan.setCaraPermohonan(kodCaraPermohonanDAO.findById(caraMohon));
            }

            if (StringUtils.isNotBlank(jenisPengenalanPenyerah)) {
                permohonan.setPenyerahJenisPengenalan(kodJenisPengenalanDAO.findById(jenisPengenalanPenyerah));
            }

            if (StringUtils.isNotBlank(kodNegeriPenyerah)) {
                permohonan.setPenyerahNegeri(kodNegeriDAO.findById(kodNegeriPenyerah));
            }

            permohonan.setInfoAudit(ia);
            permohonan.setCawangan(pguna.getKodCawangan());
            permohonan.setSebab(sebab);
            permohonan.setPenyerahNama(penyerahNama);
            if (jenisPengenalanPenyerah != null) {
                if (jenisPengenalanPenyerah.equalsIgnoreCase("B")) {
                    permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanBaru);
                    log.debug("saveMohon baru" + penyerahNoPengenalanBaru );
                } else {
                    permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
                    log.debug("saveMohon lain 1 " + penyerahNoPengenalanLain );
                }
            } else {
                permohonan.setPenyerahNoPengenalan(penyerahNoPengenalanLain);
                log.debug("saveMohon lain 2 " + penyerahNoPengenalanLain );

            }

            permohonan.setPenyerahAlamat1(penyerahAlamat1);
            permohonan.setPenyerahAlamat2(penyerahAlamat2);
            permohonan.setPenyerahAlamat3(penyerahAlamat3);
            permohonan.setPenyerahAlamat4(penyerahAlamat4);
            permohonan.setPenyerahPoskod(penyerahPoskod);
            permohonan.setPenyerahNoTelefon1(penyerahNoTelefon1);
            permohonan.setPenyerahEmail(penyerahEmail);
            permohonan.setInfoAudit(ia);
            enforceService.savePermohonan(permohonan);
        }

        if (aduanLokasi != null) {
            ia = aduanLokasi.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());

            aduanLokasi.setInfoAudit(ia);



            if (StringUtils.isNotBlank(kodBpm)) {
                aduanLokasi.setBandarPekanMukim(kodbandarPekanMukimDAO.findById(Integer.parseInt(kodBpm)));
            } else {
                String[] tname = {"bandarPekanMukim"};
                Object[] tvalue = {"00"};
                for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                    aduanLokasi.setBandarPekanMukim(kbpm);
                }
            }

            if (StringUtils.isNotBlank(jenisMilik)) {
                aduanLokasi.setPemilikan(kodPemilikanDAO.findById(jenisMilik.charAt(0)));
            }

            if (StringUtils.isNotBlank(kodLotTanah)) {
                aduanLokasi.setKodLot(kodLotDAO.findById(kodLotTanah));
            }

            aduanLokasi.setCawangan(pguna.getKodCawangan());
            aduanLokasi.setNoLot(noLot);
            aduanLokasi.setLokasi(lokasi);
            enforceService.simpanAduanLokasi(aduanLokasi);
        } else {
            AduanLokasi aduanbr = new AduanLokasi();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());

            aduanbr.setInfoAudit(ia);



            if (StringUtils.isNotBlank(kodBpm)) {
                aduanbr.setBandarPekanMukim(kodbandarPekanMukimDAO.findById(Integer.parseInt(kodBpm)));
            } else {
                String[] tname = {"bandarPekanMukim"};
                Object[] tvalue = {"00"};
                for (KodBandarPekanMukim kbpm : kodbandarPekanMukimDAO.findByEqualCriterias(tname, tvalue, null)) {
                    aduanbr.setBandarPekanMukim(kbpm);
                }
            }

            if (StringUtils.isNotBlank(jenisMilik)) {
                aduanbr.setPemilikan(kodPemilikanDAO.findById(jenisMilik.charAt(0)));
            }

            if (StringUtils.isNotBlank(kodLotTanah)) {
                aduanbr.setKodLot(kodLotDAO.findById(kodLotTanah));
            }

            aduanbr.setCawangan(pguna.getKodCawangan());
            aduanbr.setNoLot(noLot);
            aduanbr.setLokasi(lokasi);
            aduanbr.setPermohonan(permohonan);
            enforceService.simpanAduanLokasi(aduanbr);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/kemasukan_aduan_pt.jsp").addParameter("tab", "true");
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return senaraiKodBandarPekanMukim;
    }

    public void setSenaraiKodBandarPekanMukim(List<KodBandarPekanMukim> senaraiKodBandarPekanMukim) {
        this.senaraiKodBandarPekanMukim = senaraiKodBandarPekanMukim;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getIdAduanLokasi() {
        return idAduanLokasi;
    }

    public void setIdAduanLokasi(String idAduanLokasi) {
        this.idAduanLokasi = idAduanLokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public List<KodCaraPermohonan> getSenaraiKodCaraPermohonan() {
        return senaraiKodCaraPermohonan;
    }

    public void setSenaraiKodCaraPermohonan(List<KodCaraPermohonan> senaraiKodCaraPermohonan) {
        this.senaraiKodCaraPermohonan = senaraiKodCaraPermohonan;
    }

    public Alamat getAlamat() {
        return alamat;
    }

    public void setAlamat(Alamat alamat) {
        this.alamat = alamat;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeriO() {
        return negeriO;
    }

    public void setNegeriO(String negeriO) {
        this.negeriO = negeriO;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public String getNoTelefon1() {
        return noTelefon1;
    }

    public void setNoTelefon1(String noTelefon1) {
        this.noTelefon1 = noTelefon1;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public List<KodUrusan> getSenaraiUrusan() {
        return senaraiUrusan;
    }

    public void setSenaraiUrusan(List<KodUrusan> senaraiUrusan) {
        this.senaraiUrusan = senaraiUrusan;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<Permohonan> getSenaraiAduan() {
        return senaraiAduan;
    }

    public void setSenaraiAduan(List<Permohonan> senaraiAduan) {
        this.senaraiAduan = senaraiAduan;
    }

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodJenisPengenalan getPenyerahJenisPengenalan() {
        return penyerahJenisPengenalan;
    }

    public void setPenyerahJenisPengenalan(
            KodJenisPengenalan penyerahJenisPengenalan) {
        this.penyerahJenisPengenalan = penyerahJenisPengenalan;
    }

    public String getPenyerahNoPengenalan() {
        return penyerahNoPengenalan;
    }

    public void setPenyerahNoPengenalan(String penyerahNoPengenalan) {
        this.penyerahNoPengenalan = penyerahNoPengenalan;
    }

    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String nama) {
        this.penyerahNama = nama;
    }

    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public KodNegeri getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(KodNegeri penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public void setPenyerahNoTelefon1(String penyerahNoTelefon1) {
        this.penyerahNoTelefon1 = penyerahNoTelefon1;
    }

    public String getPenyerahNoTelefon1() {
        return penyerahNoTelefon1;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String email) {
        this.penyerahEmail = email;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getIdInsert() {
        return idInsert;
    }

    public void setIdInsert(String idInsert) {
        this.idInsert = idInsert;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public List<AduanLokasi> getSenaraiAduanLokasi() {
        return senaraiAduanLokasi;
    }

    public void setSenaraiAduanLokasi(List<AduanLokasi> senaraiAduanLokasi) {
        this.senaraiAduanLokasi = senaraiAduanLokasi;
    }

    public List<AduanOrangKenaSyak> getSenaraiOrangKenaSyak() {
        return senaraiOrangKenaSyak;
    }

    public void setSenaraiOrangKenaSyak(List<AduanOrangKenaSyak> senaraiOrangKenaSyak) {
        this.senaraiOrangKenaSyak = senaraiOrangKenaSyak;
    }

    public String getIdOrangKenaSyak() {
        return idOrangKenaSyak;
    }

    public void setIdOrangKenaSyak(String idOrangKenaSyak) {
        this.idOrangKenaSyak = idOrangKenaSyak;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public AduanOrangKenaSyak getAduanOrangKenaSyak() {
        return aduanOrangKenaSyak;
    }

    public void setAduanOrangKenaSyak(AduanOrangKenaSyak aduanOrangKenaSyak) {
        this.aduanOrangKenaSyak = aduanOrangKenaSyak;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public PermohonanNota getPermohonanNota() {
        return permohonanNota;
    }

    public void setPermohonanNota(PermohonanNota permohonanNota) {
        this.permohonanNota = permohonanNota;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public String getStatusJanaDokumen() {
        return statusJanaDokumen;
    }

    public void setStatusJanaDokumen(String statusJanaDokumen) {
        this.statusJanaDokumen = statusJanaDokumen;
    }

    public String getBeanFlag() {
        return beanFlag;
    }

    public void setBeanFlag(String beanFlag) {
        this.beanFlag = beanFlag;
    }

    public String getStatusJanaDokumenRAP() {
        return statusJanaDokumenRAP;
    }

    public void setStatusJanaDokumenRAP(String statusJanaDokumenRAP) {
        this.statusJanaDokumenRAP = statusJanaDokumenRAP;
    }

    public PermohonanNota getCurrentPermohonanNota() {
        return currentPermohonanNota;
    }

    public void setCurrentPermohonanNota(PermohonanNota currentPermohonanNota) {
        this.currentPermohonanNota = currentPermohonanNota;
    }

    public Boolean getPerihalSeksyen() {
        return perihalSeksyen;
    }

    public void setPerihalSeksyen(Boolean perihalSeksyen) {
        this.perihalSeksyen = perihalSeksyen;
    }

    public Boolean getEditPerihalSeksyen() {
        return editPerihalSeksyen;
    }

    public void setEditPerihalSeksyen(Boolean editPerihalSeksyen) {
        this.editPerihalSeksyen = editPerihalSeksyen;
    }

    public String getCatatanSeksyen() {
        return catatanSeksyen;
    }

    public void setCatatanSeksyen(String catatanSeksyen) {
        this.catatanSeksyen = catatanSeksyen;
    }

    public String getEditSeksyen() {
        return editSeksyen;
    }

    public void setEditSeksyen(String editSeksyen) {
        this.editSeksyen = editSeksyen;
    }

    public String getSpecificSection() {
        return specificSection;
    }

    public void setSpecificSection(String specificSection) {
        this.specificSection = specificSection;
    }

    public Boolean getEditRingkasanAduan() {
        return editRingkasanAduan;
    }

    public void setEditRingkasanAduan(Boolean editRingkasanAduan) {
        this.editRingkasanAduan = editRingkasanAduan;
    }

    public boolean isHideTab() {
        return hideTab;
    }

    public void setHideTab(boolean hideTab) {
        this.hideTab = hideTab;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getPenyerahNoPengenalanBaru() {
        return penyerahNoPengenalanBaru;
    }

    public void setPenyerahNoPengenalanBaru(String penyerahNoPengenalanBaru) {
        this.penyerahNoPengenalanBaru = penyerahNoPengenalanBaru;
    }

    public String getPenyerahNoPengenalanLain() {
        return penyerahNoPengenalanLain;
    }

    public void setPenyerahNoPengenalanLain(String penyerahNoPengenalanLain) {
        this.penyerahNoPengenalanLain = penyerahNoPengenalanLain;
    }

    public String getCaraMohon() {
        return caraMohon;
    }

    public void setCaraMohon(String caraMohon) {
        this.caraMohon = caraMohon;
    }

    public String getJenisPengenalanPenyerah() {
        return jenisPengenalanPenyerah;
    }

    public void setJenisPengenalanPenyerah(String jenisPengenalanPenyerah) {
        this.jenisPengenalanPenyerah = jenisPengenalanPenyerah;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public String getJenisMilik() {
        return jenisMilik;
    }

    public void setJenisMilik(String jenisMilik) {
        this.jenisMilik = jenisMilik;
    }

    public String getKodLotTanah() {
        return kodLotTanah;
    }

    public void setKodLotTanah(String kodLotTanah) {
        this.kodLotTanah = kodLotTanah;
    }

    public String getKodNegeriPenyerah() {
        return kodNegeriPenyerah;
    }

    public void setKodNegeriPenyerah(String kodNegeriPenyerah) {
        this.kodNegeriPenyerah = kodNegeriPenyerah;
    }
}
