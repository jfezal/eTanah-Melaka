/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.report.ReportUtil;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Dokumen;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.service.HakmilikService;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.KodStatusLelongan;
import etanah.model.KodUrusan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Transaksi;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.service.common.DokumenService;
import etanah.service.SemakDokumenService;
import etanah.service.common.KandunganFolderService;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import java.io.File;
import java.io.Serializable;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/utiliti_kemasukan_perintah_mahkamah1")
public class UtilitiKemasukanPerintahMahkamahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiKemasukanPerintahMahkamahActionBean.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanRujukanLuarDAO mohonRujukLuarDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enkuiriService;
      @Inject
    ReportUtil reportUtil;
    @Inject
    EnkuiriService ES;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    KodKeputusanDAO kodkeputusanDAO;
     @Inject
    KandunganFolderDAO kandunganFolderDAO;
      @Inject
    DokumenService dokumenService;
      @Inject
    KodDokumenDAO kodDokumenDAO;
       @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    CalenderManager manager;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    HakmilikService hakmilikService;
    Permohonan permohonan;
    HakmilikUrusan hakmilikUrusan;
    private List<Pengguna> senaraiPT;
//    private Permohonan permohonan;

    private PermohonanRujukanLuar mohonRujukLuar;
    private Hakmilik hakmilik;
    private Enkuiri enkuiri;
    private Enkuiri enkuiri1;
    private Lelongan lelong;
    private Pengguna pengguna;
    private Dokumen dokumen;
    private String idPengguna;
    private Dokumen dokumen2;
    private List<HakmilikPermohonan> listMohonHakmilik = new ArrayList<HakmilikPermohonan>();
    private List<HakmilikPermohonan> listMohonHakmilik1 = new ArrayList<HakmilikPermohonan>();
    private List<HakmilikPermohonan> listHakmilikMohon;
    private List<PermohonanRujukanLuar> listMohonRujukLuar;
    private List<PermohonanRujukanLuar> listMohonRujukLuar1;
    private List<HakmilikUrusan> listHakmilikUrusan;
    private List<Permohonan> listMohon;
    private List<Lelongan> listLelongHakmilik;
    private List<Lelongan> listLelong;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private List<CalenderLelong> listCalender3;
    private List<CalenderLelong> listCalender4;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiriTA;
    private List<FasaPermohonan> fasa;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> senaraiLelongan4;
    private List<Lelongan> senaraiLelongan5;
    private List<Lelongan> sejarahLelongan;
    private List<Enkuiri> sejarahEnkuiri;
    private List<Enkuiri> sejarahEnkuiri2;
    private List<Lelongan> listLel2 = new ArrayList<Lelongan>();
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    private List<BigDecimal> listHarga = new ArrayList<BigDecimal>();
    private List<BigDecimal> listDeposit = new ArrayList<BigDecimal>();
    private List<Enkuiri> listEnkuiri = new ArrayList<Enkuiri>();
    private BigDecimal baki;
    private String idPermohonan;
    private String tarikhPerintah;
    private String idHakmilik;
    private Long idLelong;
    private String[] idHak;
    private String idHak1;
    private String tarikhEnkuiri;
    private String idPerserahan;
    private String idPerserahan2;
    private String idPerserahan3;
    private String jam;
    private String minit;
    private String ampm;
    private String jam1;
    private String minit1;
    private String ampm1;
    private String hari;
    private String kpsn;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tempat;
    private String perkara;
    private String tarikhPermohonan;
    private String stage;
    private String participant;
    IWorkflowContext ctxOnBehalf;
    private String nextStage;
    private String taskId;
    private String stageId;
    private String tarikhLelong;
    private String tarikhAkhirBayaran;
    private String ulasan;
    private BigDecimal amaunTunggakan;
    private BigDecimal hargaRizab;
    private BigDecimal deposit;
    private String tarikhLelongTerdahulu;
    private String tarikhEnkuiriTerdahulu;
    private String mohonrl;
    private String tempoh;
    private long idDokumen16H;
//      private String idKandungan;
    private Long idDokumen;
    private long idDokumenWarta;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private boolean view = false;
    private boolean viewSiasatan = false;
    private boolean showSelesai = false;
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    private List<Permohonan> senaraiPermohonan;
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<PermohonanRujukanLuar> listHakMilikUrusan;
    private List<PermohonanRujukanLuar> addListHakMilikUrusan = new ArrayList<PermohonanRujukanLuar>();
    private List<HakmilikUrusan> addCheckUrusan = new ArrayList<HakmilikUrusan>();
    private List<HakmilikUrusan> addCheckUrusanMahkamah = new ArrayList<HakmilikUrusan>();
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar;
    private List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan;
    private List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2;
    private List<HakmilikPermohonan> getIdMohon;
    private List<HakmilikPermohonan> getIdMohon2;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private List<Permohonan> tarikhPerserahan;
    private boolean showForm = false;
    private boolean lelongForm = false;
    private boolean enkuiriForm = false;
    private boolean showEnkuiri = false;
    private boolean showLelongan = false;
    private boolean show16H = false;
    private boolean showWarta = false;
    private boolean negeri = false;

    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //search id mohon by willcard
    //added by nur.aqilah
    public Resolution checkPermohonan() {
        LOG.info("search id mohon by willcard");
        LOG.info("idPermohonan : " + idPermohonan);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (isIS_DEBUG()) {
            LOG.debug("======= page: " + page);
        }
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
            LOG.debug("======= StringUtils is not blank ");
        }

        if (StringUtils.isBlank(idPermohonan)) {
            LOG.debug("======= idPemohon is EMPTY: ");

            setListT(hakmilikService.findMohon(getContext().getRequest().getParameterMap()));
            for (Transaksi t : getListT()) {

                if (t.getPermohonan() != null) {
                    String idMohon = t.getPermohonan().getIdPermohonan();
                    idPermohonan = idMohon;
                } else {
                    addSimpleError("Tiada Maklumat dijumpai.");
                }
            }
        }

        if (isIS_DEBUG()) {
            LOG.debug("====== idPermohonan: " + idPermohonan);
        }

        if ((StringUtils.isNotBlank(idPermohonan))) {

            if (idPermohonan != null) {
                LOG.info("MASUK idPermohonan!=null");

                permohonan = permohonanDAO.findById(idPermohonan);

                LOG.debug("Permohonan " + permohonan);

                if (permohonan == null) {

                    permohonan = null;

                    LOG.info("MASUK permohonan==null");

                    senaraiPermohonan = lelongService.getSenaraiPermohonan(idPermohonan, pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records());

                    LOG.info("senaraiPermohonan " + senaraiPermohonan.size());
                    set__pg_total_records(lelongService.getTotalRecordFolderAction(idPermohonan, pengguna.getKodCawangan().getKod()).intValue());

                    //find in carian
                    if (senaraiPermohonan.isEmpty()) {
                        setSenaraiPermohonanCarian(lelongService.getSenaraiPermohonanCarian(idPermohonan,
                                pengguna.getKodCawangan().getKod(), get__pg_start(), get__pg_max_records()));

                        LOG.info("senaraiPermohonanCarian " + getSenaraiPermohonanCarian().size());
                    }

                    if (senaraiPermohonan.isEmpty()
                            && getSenaraiPermohonanCarian().isEmpty()) {
                        LOG.error("senaraiPermohonan tiada");
                        addSimpleError("Tiada Maklumat dijumpai.");
                        showForm = false;
                    } else {
                        showForm = true;
                    }
                } else {
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        //added by nur.aqilah
                        //search idmohon by kod cawangan
                        permohonan = lelongService.searchIdMohonByKodCawangan(idPermohonan, pengguna.getKodCawangan().getKod());

                        if (permohonan != null) {
                            LOG.debug("MASUK ID !=NULL");
                            LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
                            try {
                                carian();
                            } catch (WorkflowException ex) {
                                java.util.logging.Logger.getLogger(UtilitiKemasukanPerintahMahkamahActionBean.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        } else {
                            LOG.debug("MASUK ID ==NULL");
                            addSimpleError("Id Permohonan tidak wujud");
                            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
                        }
                    }
                    return new JSP("/lelong/UtilitiKemasukanPerintahMahkamah.jsp");

                }
            }
        }

        LOG.info("Rehydrate - finish");
        return new JSP("/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //check id hak milik by id mohon
    //added by nur.aqilah
    public Resolution carian() throws WorkflowException {
        LOG.info("masuk carian");

        LOG.info("idPermohonan " + getContext().getRequest().getParameter("idPermohonan"));
        permohonan = permohonanDAO.findById(getContext().getRequest().getParameter("idPermohonan"));
        permohonan = permohonanDAO.findById(idPermohonan);
        
//        if ("04".equals(conf.getProperty("kodNegeri"))){
//            negeri = false;
//        }else if("05".equals(conf.getProperty("kodNegeri"))){
//            negeri = true;
//        }

        enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
        if (enkuiri != null) {
            List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
            for (Task t : l) {
                stage = t.getSystemAttributes().getStage();
                participant = t.getSystemAttributes().getAcquiredBy();
            }
            LOG.info("Permohonan Ini Diperingkat: " + stage);
        }


        enkuiri = lelongService.findEnkuiri(idPermohonan);
        enkuiri1 = lelongService.getEnkuiriTA1(idPermohonan);
//        lelong =lelongService.findLelong(idPermohonan);
        listLelong = lelongService.findListLelong(idPermohonan);

        if (enkuiri != null) {
            if (enkuiri.getCaraLelong() != null) {
                if (enkuiri.getCaraLelong().equals("A")) {
                    LOG.info("cara lelong asing");
                    getContext().getRequest().setAttribute("same", Boolean.FALSE);
                    amaunTunggakan = enkuiri.getAmaunTunggakan();
                    
                } else {
                    //bersama
                    if (enkuiri.getCaraLelong().equals("B")) {
                        LOG.info("cara lelong bersama");
                        getContext().getRequest().setAttribute("same", Boolean.TRUE);
                        amaunTunggakan = enkuiri.getAmaunTunggakan();

                    }
                }
            }
        }

        if (enkuiri1 != null) {
            if (enkuiri1.getCaraLelong() != null) {
                if (enkuiri1.getCaraLelong().equals("A")) {
                    getContext().getRequest().setAttribute("same", Boolean.FALSE);
                    amaunTunggakan = enkuiri1.getAmaunTunggakan();
                } else {
                    if (enkuiri1.getCaraLelong().equals("B")) {
                        getContext().getRequest().setAttribute("same", Boolean.TRUE);
                        amaunTunggakan = enkuiri1.getAmaunTunggakan();
                        senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                            LOG.info("senaraiPT .size :" + senaraiPT.size());
                            if (enkuiri1.getPengguna() != null) {
                                LOG.info("enkuiri pengguna not null");
                                idPengguna = enkuiri1.getPengguna().getIdPengguna();
                            }
                    }
                }
            }
        }

        senaraiLelongan = lelongService.listLelonganAK(idPermohonan);

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                lelong = ll;
                break;
            }
        }
        listLelongan.add(lelong);

        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            LOG.info("Id Hak Milik " + pp.getHakmilik().getIdHakmilik());
            List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());

            for (HakmilikUrusan hu : checkUrusan) {
                LOG.info("Id Hak Milik " + hu.getHakmilik().getIdHakmilik() + " Kod Urusan " + hu.getKodUrusan().getKod());
                getAddCheckUrusan().add(hu);
            }
        }
        LOG.info("addCheckUrusan.size()" + getAddCheckUrusan().size());
        if (!addCheckUrusan.isEmpty()) {
            for (HakmilikUrusan hu : addCheckUrusan) {
                listHakMilikUrusan = lelongService.findMohonRujukHakMilikUrusanList(hu.getHakmilik().getIdHakmilik());
            }
            LOG.info("listHakMilikUrusan.size()" + listHakMilikUrusan.size());
            for (PermohonanRujukanLuar pr : listHakMilikUrusan) {
                addListHakMilikUrusan.add(pr);
            }

            if (permohonan.getKodUrusan().getKod().equals("PMHUK") || (permohonan.getKodUrusan().getKod().equals("PMHUN"))) {
                if (!listHakMilikUrusan.isEmpty()) {
                    PermohonanRujukanLuar prl = listHakMilikUrusan.get(0);
                    int tahun = prl.getTempohTahun();
                    int bulan = prl.getTempohBulan();
                    int harii = prl.getTempohHari();
                    StringBuilder s = new StringBuilder();
                    if (tahun != 0) {
                        s.append(tahun).append(" Tahun ");
                    }
                    if (bulan != 0) {
                        s.append(bulan).append(" Bulan ");
                    }
                    if (harii != 0) {
                        s.append(harii).append(" Hari ");
                    }

                    tempoh = s.toString();
                    LOG.info("tempohGadaian : " + tempoh);
                }
            }
            LOG.info("addListHakMilikUrusan.size()" + addListHakMilikUrusan.size());
        }

        if (permohonan != null) {

            if (permohonan.getStatus().equals("MK")) {
                addSimpleError("Permohonan Telah Dibatalkan Atas Perintah Mahkamah");

            } else if (permohonan.getStatus().equals("RM")) {
                addSimpleError("Permohonan Dirujuk Ke Mahkamah");

            } else if (permohonan.getStatus().equals("SL")) {
                addSimpleError("Permohonan Telah Selesai");

            } else if (permohonan.getStatus().equals("BP")) {
                addSimpleError("Permohonan Telah Dibatalkan");

            }

            //keluarkan mesej jika ada perintah mahkamah
            //CONDITION 1//
            //check idmohon ada perintah mahkamah ke tak
            for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
                List<HakmilikUrusan> checkUrusanMahkamah = lelongService.checkPMKMH(pp.getHakmilik().getIdHakmilik());

                for (HakmilikUrusan hu : checkUrusanMahkamah) {
                    LOG.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                    addCheckUrusanMahkamah.add(hu);
                }
            }

            StringBuilder sb = new StringBuilder();

            //id perserahan utk CONDITION 1
            if (!addCheckUrusanMahkamah.isEmpty()) {
                String cekIdHmlk = "";
//                String cekIdHmlk2 = "";
                for (HakmilikUrusan acu : addCheckUrusanMahkamah) {
                    //find tarikh perserahan 
                    tarikhPerserahan = lelongService.checkTarikhPerserahan(acu.getIdPerserahan());
                    if (acu == null) {
                        continue;
                    }
                    LOG.info("==== acu.getIdperserahan = " + acu.getIdPerserahan());
                    LOG.info("++++cekIdHmlk = " + cekIdHmlk);
//                    LOG.info("++++cekIdHmlk2 = " + cekIdHmlk2);
//                    cekIdHmlk2 = acu.getIdPerserahan();
                    
                    if(!acu.getIdPerserahan().equals(cekIdHmlk)){
                        LOG.info("==== masuk if compare ====");
                        if (sb.length() > 0) {
                            sb.append(" , ");
                        }
                        sb.append(acu.getIdPerserahan());

                        cekIdHmlk = acu.getIdPerserahan();
                    }
                    LOG.info("++++cekIdHmlk = " + cekIdHmlk);
//                    LOG.info("++++cekIdHmlk2 = " + cekIdHmlk2);
                }
                setIdPerserahan(sb.toString());
                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + getIdPerserahan());
            }

            //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
            //check perintah mahkamah dah daftar ke belum
            for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

                //check id hakmilik ada ke tak dlm table mohon rujuk luar
                List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
                setAddCheckPermohonanRujukanLuar(new ArrayList<PermohonanRujukanLuar>());

                for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                    getAddCheckPermohonanRujukanLuar().add(prl);
                }
                LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar().size());

                if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                    for (PermohonanRujukanLuar prl2 : getAddCheckPermohonanRujukanLuar()) {
                        LOG.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                        //check id hakmilik ada ke tak dlm table hakmilik urusan
                        List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                        setAddCheckDaftarHakMilikUrusan(new ArrayList<HakmilikUrusan>());
                        for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                            getAddCheckDaftarHakMilikUrusan().add(hu);
                        }

                        //check ada urusan perintah mahkamah ke takk
                        if (getAddCheckDaftarHakMilikUrusan().isEmpty()) {

                            //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                            setGetIdMohon(lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik()));
                            LOG.info("belum di daftarkn: " + getGetIdMohon().size());

                        }
                    }
                }
            }

            StringBuilder sb2 = new StringBuilder();

            //id perserahan utk CONDITION 2
            if (!addCheckPermohonanRujukanLuar.isEmpty()
                    && getAddCheckDaftarHakMilikUrusan().isEmpty() && !getIdMohon.isEmpty()) {
                LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                for (HakmilikPermohonan p2 : getGetIdMohon()) {
                    if (p2 == null) {
                        continue;
                    }

                    if (sb2.length() > 0) {
                        sb2.append(" , ");
                    }
                    sb2.append(p2.getPermohonan().getIdPermohonan());
                }
                setIdPerserahan2(sb2.toString());

                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + getIdPerserahan2() + " - belum didaftarkan");
            }

            //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
            //check perintah mahkamah dah daftar ke belum

            //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)
            for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {

                //check id hakmilik ada ke tak dlm table mohon rujuk luar
                List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(pp.getHakmilik().getIdHakmilik());
                setAddCheckPermohonanRujukanLuar2(new ArrayList<PermohonanRujukanLuar>());

                for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                    getAddCheckPermohonanRujukanLuar2().add(prl);
                }
                LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar2().size());
                if (getAddCheckPermohonanRujukanLuar2().isEmpty()) {

                    //check id hakmilik ada ke tak dlm hak milik urusan
                    List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(pp.getHakmilik().getIdHakmilik());
                    setAddCheckDaftarHakMilikUrusan2(new ArrayList<HakmilikUrusan>());

                    for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                        getAddCheckDaftarHakMilikUrusan2().add(hu);
                    }

                    if (getAddCheckDaftarHakMilikUrusan2().isEmpty()) {
                        //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                        setGetIdMohon2(lelongService.findPermohonanIdHakmilikMahkamah(pp.getHakmilik().getIdHakmilik()));

                        LOG.info("belum di daftarkan " + getGetIdMohon2().size());

                    }
                }
            }

            StringBuilder sb3 = new StringBuilder();

            //id perserahan utk CONDITION 3
            if (getAddCheckPermohonanRujukanLuar2().isEmpty()
                    && getAddCheckDaftarHakMilikUrusan2().isEmpty() && !getIdMohon2.isEmpty()) {
                LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                for (HakmilikPermohonan p2 : getGetIdMohon2()) {
                    if (p2 == null) {
                        continue;
                    }

                    if (sb3.length() > 0) {
                        sb3.append(" , ");
                    }
                    sb3.append(p2.getPermohonan().getIdPermohonan());
                }
                setIdPerserahan3(sb3.toString());

                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + getIdPerserahan3() + " - belum didaftarkan");
            }
        }
        StringBuilder sb4 = new StringBuilder();
        if (permohonan != null) {
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb4.length() > 0) {
                    sb4.append(" , ");
                }
                sb4.append(h.getIdHakmilik());
                LOG.info("hak milik " + h.getIdHakmilik());
            }
        }
        idHakmilik = sb4.toString();
        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //check id hakmilik by id hakmilik
    //added by nur.aqilah
    public Resolution checkIdHakMilik() {
        view = true;
        idHakmilik = getContext().getRequest().getParameter("idHakMilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik != null) {
            LOG.info("Id Hak Milik " + idHakmilik);

            StringBuilder sb2 = new StringBuilder();
            //keluarkan mesej jika ada perintah mahkamah
            //CONDITION 1//
            //check idmohon ada perintah mahkamah ke tak

            List<HakmilikUrusan> checkUrusanMahkamah = lelongService.checkPMKMH(idHakmilik);

            for (HakmilikUrusan hu : checkUrusanMahkamah) {
                LOG.info("" + hu.getIdUrusan() + "" + hu.getKodUrusan().getKod());
                addCheckUrusanMahkamah.add(hu);
            }


            //id perserahan utk CONDITION 1
            if (!addCheckUrusanMahkamah.isEmpty()) {

                for (HakmilikUrusan acu : addCheckUrusanMahkamah) {
                    if (acu == null) {
                        continue;
                    }
                    tarikhPerserahan = lelongService.checkTarikhPerserahan(acu.getIdPerserahan());

                    if (sb2.length() > 0) {
                        sb2.append(" , ");
                    }
                    sb2.append(acu.getIdPerserahan());
                }
                setIdPerserahan(sb2.toString());

                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan " + getIdPerserahan());
            }

            //CONDITION 2// (ada kt mhl, xda kt hu, ada urusan mahkamah)
            //check perintah mahkamah dah daftar ke belum

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar = lelongService.checkPermohonanRujukanLuar(idHakmilik);
            setAddCheckPermohonanRujukanLuar(new ArrayList<PermohonanRujukanLuar>());

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar) {
                getAddCheckPermohonanRujukanLuar().add(prl);
            }
            LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar().size());

            if (!addCheckPermohonanRujukanLuar.isEmpty()) {
                for (PermohonanRujukanLuar prl2 : getAddCheckPermohonanRujukanLuar()) {
                    LOG.info("Id Hakmilik " + prl2.getHakmilik().getIdHakmilik());

                    //check id hakmilik ada ke tak dlm table hakmilik urusan
                    List<HakmilikUrusan> checkDaftarHakMilikUrusan = lelongService.checkDaftarHakMilikUrusan(prl2.getHakmilik().getIdHakmilik());
                    setAddCheckDaftarHakMilikUrusan(new ArrayList<HakmilikUrusan>());
                    for (HakmilikUrusan hu : checkDaftarHakMilikUrusan) {
                        getAddCheckDaftarHakMilikUrusan().add(hu);
                    }

                    //check ada urusan perintah mahkamah ke takk
                    if (getAddCheckDaftarHakMilikUrusan().isEmpty()) {

                        //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                        setGetIdMohon(lelongService.findPermohonanIdHakmilikMahkamah(idHakmilik));
                        LOG.info("belum di daftarkn: " + getGetIdMohon().size());

                    }
                }
            }


            //id perserahan utk CONDITION 2
            if (!addCheckPermohonanRujukanLuar.isEmpty()
                    && getAddCheckDaftarHakMilikUrusan().isEmpty() && !getIdMohon.isEmpty()) {
                LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                for (HakmilikPermohonan p2 : getGetIdMohon()) {
                    if (p2 == null) {
                        continue;
                    }

                    if (sb2.length() > 0) {
                        sb2.append(" , ");
                    }
                    sb2.append(p2.getPermohonan().getIdPermohonan());
                }
                setIdPerserahan2(sb2.toString());

                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + getIdPerserahan2() + " - belum didaftarkan");
                return new JSP("/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
            }

            //CONDITION 3// (xda kt mhl, xda kt hu, ada urusan mahkamah)
            //check perintah mahkamah dah daftar ke belum

            //check id mohon (xda kt mohon rujuk luar & xda kt hak milik urusan)

            //check id hakmilik ada ke tak dlm table mohon rujuk luar
            List<PermohonanRujukanLuar> checkPermohonanRujukanLuar2 = lelongService.checkPermohonanRujukanLuar(idHakmilik);
            setAddCheckPermohonanRujukanLuar2(new ArrayList<PermohonanRujukanLuar>());

            for (PermohonanRujukanLuar prl : checkPermohonanRujukanLuar2) {
                getAddCheckPermohonanRujukanLuar2().add(prl);
            }
            LOG.info("table mohon rujuk luar: " + getAddCheckPermohonanRujukanLuar2().size());
            if (getAddCheckPermohonanRujukanLuar2().isEmpty()) {

                //check id hakmilik ada ke tak dlm hak milik urusan
                List<HakmilikUrusan> checkHakMilikUrusan2 = lelongService.checkDaftarHakMilikUrusan(idHakmilik);
                setAddCheckDaftarHakMilikUrusan2(new ArrayList<HakmilikUrusan>());

                for (HakmilikUrusan hu : checkHakMilikUrusan2) {
                    getAddCheckDaftarHakMilikUrusan2().add(hu);
                }

                if (getAddCheckDaftarHakMilikUrusan2().isEmpty()) {
                    //cari senarai id mohon yg ada perintah mahkamah bg id hakmilik 
                    setGetIdMohon2(lelongService.findPermohonanIdHakmilikMahkamah(idHakmilik));

                    LOG.info("belum di daftarkan " + getGetIdMohon2().size());

                }
            }

            //id perserahan utk CONDITION 3
            if (getAddCheckPermohonanRujukanLuar2().isEmpty()
                    && getAddCheckDaftarHakMilikUrusan2().isEmpty() && !getIdMohon2.isEmpty()) {
                LOG.info("Ada Perintah Mahkamah Tp Belum Di Daftarkan");

                for (HakmilikPermohonan p2 : getGetIdMohon2()) {
                    if (p2 == null) {
                        continue;
                    }

                    if (sb2.length() > 0) {
                        sb2.append(" , ");
                    }
                    sb2.append(p2.getPermohonan().getIdPermohonan());
                }
                setIdPerserahan3(sb2.toString());

                addSimpleError("Hakmilik Tersebut Mempunyai Perintah Mahkamah / Perintah Larangan. Sila Rujuk Perserahan  " + getIdPerserahan3() + " - belum didaftarkan");
                return new JSP("/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
            }

            List<HakmilikUrusan> checkUrusan = lelongService.checkPMKMH(idHakmilik);

            if (!checkUrusan.isEmpty()) {
                hakmilikUrusan = checkUrusan.get(0);
                LOG.info("id perserahan :" + hakmilikUrusan.getIdPerserahan());

                for (HakmilikUrusan hu : checkUrusan) {
                    LOG.info("Id Hak Milik " + hu.getHakmilik().getIdHakmilik() + " Kod Urusan " + hu.getKodUrusan().getKod());
                    getAddCheckUrusan().add(hu);
                    //find idPermohonan PPJP by idHakmilik from 5 perintah mahkamah
                    List<HakmilikPermohonan> findPermohonanByIdHakmilik = lelongService.findPermohonanByIdHakmilik(hu.getHakmilik().getIdHakmilik());
                    for (HakmilikPermohonan hp : findPermohonanByIdHakmilik) {
                        idPermohonan = hp.getPermohonan().getIdPermohonan();
                        LOG.info("Id Permohonan PPJP : " + idPermohonan);
                    }
                }

                List<PermohonanRujukanLuar> pl = lelongService.findMohonRujukAUC(idHakmilik);
//            mohonRujukLuar = lelongService.findMohonRujukAUC1(idHakmilik);
                for (PermohonanRujukanLuar pr : pl) {
                    mohonrl = pr.getPermohonan().getIdPermohonan();
                    LOG.info("id AUC :" + mohonrl);
                }

                StringBuilder s = new StringBuilder();
                if (!addCheckUrusan.isEmpty()) {
                    for (HakmilikUrusan hu : addCheckUrusan) {
                        listHakMilikUrusan = lelongService.findMohonRujukHakMilikUrusanList(hu.getHakmilik().getIdHakmilik());
                        if (hu.getKodUrusan().getKod().equals("PMHUK") || (hu.getKodUrusan().getKod().equals("PMHUN"))) {
                            if (!listHakMilikUrusan.isEmpty()) {
                                PermohonanRujukanLuar prl = listHakMilikUrusan.get(0);
                                if (prl.getTempohTahun() != null) {
                                    int tahun = prl.getTempohTahun();
                                    if (tahun != 0) {
                                        s.append(tahun).append(" Tahun ");
                                    }
                                }
                                if (prl.getTempohBulan() != null) {
                                    int bulan = prl.getTempohBulan();
                                    if (bulan != 0) {
                                        s.append(bulan).append(" Bulan ");
                                    }
                                }
                                if (prl.getTempohHari() != null) {
                                    int harii = prl.getTempohHari();
                                    if (harii != 0) {
                                        s.append(harii).append(" Hari ");
                                    }
                                }

                                tempoh = s.toString();
                                LOG.info("tempohGadaian : " + tempoh);
                            }
                        }
                        LOG.info("addListHakMilikUrusan.size()" + addListHakMilikUrusan.size());
                    }
                }
                LOG.info("listHakMilikUrusan.size()" + listHakMilikUrusan.size());
                for (PermohonanRujukanLuar pr : listHakMilikUrusan) {
                    addListHakMilikUrusan.add(pr);
                }
            }

            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br/>");
                    }
                    sb.append(h.getIdHakmilik());
                    LOG.info("hak milik " + h.getIdHakmilik());
                }
                idHakmilik = sb.toString();
            } else {
                hakmilik = hakmilikDAO.findById(idHakmilik);
                idHakmilik = hakmilik.getIdHakmilik();
            }
            view = true;

        } else {
            addSimpleError("Id Hak Milik Tidak Wujud");
            view = false;
        }


        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //Jana Laporan 16H
    public Resolution genReport() {
        permohonan = permohonanDAO.findById(getContext().getRequest().getParameter("idPermohonan"));
        LOG.info("id permohonan" + permohonan.getIdPermohonan());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        String gen = "";
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//            //melaka
//            gen = "LLGBorang16HPenyerah_MLK.rdf";
//        }
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//           // melaka
//            gen = "LLGBorang16HPenyerahMkmh_MLK.rdf";
//        }
//        else
//            gen = "LLGBorang16HPenyerahMkmh_NS.rdf";
//        String code = "16H1";
                  // generate report

                    String[] params = new String[]{"p_id_mohon"};
                    String[] values = new String[]{permohonan.getIdPermohonan()};
                    String path = "";
                    String dokumenPath = conf.getProperty("document.path");
                    Dokumen d = null;
                    KodDokumen kd = null;

                    FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
                    String reportName = "";

                    kd = kodDokumenDAO.findById("16H1");
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        reportName = "LLGBorang16HPenyerahMkmh_MLK.rdf";
                    } else {
                        reportName = "LLGBorang16HPenyerahMkmh_NS.rdf";
                    }
                    d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                     d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermoh);
                    path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                    reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
                    updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen());
                    idDokumen = d.getIdDokumen();
//                    logger.info("-------id dokumen : --------" + idDokumen16H);

//        try {
//            LOG.info("genReportFromXML");
//            lelongService.reportGen(permohonan, gen, code, pengguna);
//        } catch (Exception ex) {
//            addSimpleError("Borang 16 H Tidak Dapat Dijana." + ex.getMessage());
//            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
//        }
        LOG.info("genReport :: finish");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }
     private void updatePathDokumen(String namaFizikal, Long idDokumen) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
       LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getKod() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    //Jana Laporan Warta
//    public Resolution genReport2() {
//        permohonan = permohonanDAO.findById(getContext().getRequest().getParameter("idPermohonan"));
//        LOG.info("id permohonan" + permohonan.getIdPermohonan());
//
//        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//
//        LOG.info("genReport :: start");
//        LOG.info("generate report start.");
//        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//        String gen = "";
//        if ("04".equals(conf.getProperty("kodNegeri"))) {
//            //melaka
//            gen = "LLGBorang16H_MLK.rdf";
//        }
//        String code = "SRW";
//
//        try {
//            LOG.info("genReportFromXML");
//            lelongService.reportGen(permohonan, gen, code, pengguna);
//        } catch (Exception ex) {
//            addSimpleError("Laporan Warta Tidak Dapat Dijana." + ex.getMessage());
//            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
//        }
//        LOG.info("genReport :: finish");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
//    }

    public Resolution test() {

        LOG.info("---void test----");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                senaraiLelongan4 = lelongService.getLeloganTA(idPermohonan);
                senaraiLelongan3 = lelongService.getTG(idPermohonan);
                senaraiLelongan5 = lelongService.listLelonganAK1(idPermohonan);
                senaraiEnkuiri = lelongService.getEnkuiriList(idPermohonan);
                sejarahEnkuiri2 = lelongService.findEnkuiriTG(idPermohonan);

                Lelongan lelo = senaraiLelongan4.get(0);
                enkuiri = lelongService.findEnkuiri(idPermohonan);


                //update the status from AK to TG
                kpsn = getContext().getRequest().getParameter("kpsn");
                LOG.info("kpsn :" + kpsn);

                //create row lelong
                if (kpsn.equals("LM")) {

                    //ni nk delete klu tukar2 dropdown
                    if (!senaraiEnkuiri.isEmpty()) {
                        for (Enkuiri eee : senaraiEnkuiri) {
                            lelongService.deleteEnkuiri(eee);
                        }

                        for (Enkuiri enk1 : sejarahEnkuiri2) {
                            enk1.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                            enkuiriService.saveEnkuiri(enk1);
                        }
                    }
                        senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                        LOG.info("senaraiPT .size :" + senaraiPT.size());
                        if (enkuiri.getPengguna() != null) {
                            LOG.info("enkuiri not null");
                            idPengguna = enkuiri.getPengguna().getIdPengguna();
                        }

                    for (Lelongan l : senaraiLelongan4) {
                        LOG.info("Lelongan Id " + l.getIdLelong());
                        l.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
                        enkuiriService.simpan(l);

                    }

                    //create new lelongan and set the information
                    for (Lelongan ll : senaraiLelongan4) {
                        InfoAudit ial = new InfoAudit();
                        ial.setDimasukOleh(pengguna);
                        ial.setTarikhMasuk(new java.util.Date());
                        Lelongan lel = new Lelongan();
                        lel.setEnkuiri(enkuiri);
                        lel.setBil(lelo.getBil());
                        lel.setInfoAudit(ial);
                        lel.setPermohonan(permohonan);
                        lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                        if (ll.getPerihalTanah() != null) {
                            lel.setPerihalTanah(ll.getPerihalTanah());
                        }
                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                        }
                        if (ll.getJurulelong() != null) {
                            lel.setJurulelong(ll.getJurulelong());
                        }

                        lelongService.saveOrUpdate(lel);

                        enkuiri.setAmaunTunggakan(enkuiri.getAmaunTunggakan());
                        lelongService.saveOrUpdate(enkuiri);
                    }

                }

                //create row enkuiri
                if (kpsn.equals("EM")) {
                    for (Enkuiri enkuiri2 : senaraiEnkuiri) {
                        if (enkuiri2.getStatus().getKod().equals("AK")) {
                            enkuiri = enkuiri2;
                            enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("TG"));
                            enkuiriService.save(enkuiri2);
                        }
                    }

                    //ni nk delete klu tukar2 dropdown
                    if (!senaraiLelongan5.isEmpty()) {
                        for (Lelongan lelol : senaraiLelongan5) {
                            lelongService.delete(lelol);
                        }
                        for (Lelongan lll : senaraiLelongan3) {
                            lll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TA"));
                            enkuiriService.simpan(lll);
                        }
                    }

                    //create new enkuiri and set the information
                    enkuiri = lelongService.findEnkuiri(idPermohonan);
                    for (Enkuiri ee : senaraiEnkuiri) {
                        InfoAudit ial = new InfoAudit();
                        ial.setDimasukOleh(pengguna);
                        ial.setTarikhMasuk(new java.util.Date());
                        Enkuiri enk = new Enkuiri();
                        if (StringUtils.isNotEmpty(ee.getTujuanGadaian())) {
                            enk.setTujuanGadaian(ee.getTujuanGadaian());
                        }
                        if (ee.getAmaunTunggakan() != null) {
                            enk.setAmaunTunggakan(ee.getAmaunTunggakan());
                        }
                        enk.setInfoAudit(ial);
                        enk.setPermohonan(permohonan);
                        enk.setCawangan(pengguna.getKodCawangan());
                        enk.setBilanganKes(ee.getBilanganKes());
                        enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                        lelongService.saveOrUpdate(enk);
                    }
                }

                if (kpsn.equals("C")) {
                    if (!senaraiEnkuiri.isEmpty()) {
                        senaraiEnkuiri.clear();
                        for (Enkuiri enk1 : sejarahEnkuiri2) {
                            enk1.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                            enkuiriService.saveEnkuiri(enk1);
                        }
                    }

                    if (!senaraiLelongan5.isEmpty()) {
                        senaraiLelongan5.clear();
                        for (Lelongan lll : senaraiLelongan3) {
                            lll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TA"));
                            enkuiriService.simpan(lll);
                        }
                    }
                }

            }
        }
        rowNew();
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
//        return new JSP("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //nk create row baru dlm lelong
    public Resolution rowNew() {
        LOG.info("---new row----");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
//                enkuiri = lelongService.findEnkuiri(idPermohonan);
                enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
//                lelong =lelongService.findLelong(idPermohonan);
                senaraiEnkuiriTA = lelongService.getEnkuiriTA(idPermohonan);
                 senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                                LOG.info("senaraiPT .size :" + senaraiPT.size());
                                if (enkuiri.getPengguna() != null) {
                                    LOG.info("enkuiri not null");
                                    idPengguna = enkuiri.getPengguna().getIdPengguna();
                                }
//                                  LOG.info("senaraiPT .size :" + senaraiPT);
                                 
//                                 idPengguna = enkuiri.getPengguna().getIdPengguna();
//                               LOG.info("lelong :" + lelong);
//                                    idPengguna = lelong.getPengguna().getIdPengguna();
//                                 LOG.info("idlelong :" + lelong);
//                                  LOG.info("idPengguna :" + idPengguna);

                if (StringUtils.isNotBlank(kpsn)) {
                    getContext().getRequest().setAttribute("kpsn", kpsn);
                }

//                if (kpsn.equals("LM")) {
//                    for (Enkuiri enkuiri2 : senaraiEnkuiriTA) {
//                        if (enkuiri2.getStatus().getKod().equals("TA")) {
//                            enkuiri = enkuiri2;
//                            enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("AK"));
//                            enkuiriService.save(enkuiri2);
//                        }
//                    }
//                }
                //sejarah lelongan                    
                sejarahLelongan = new ArrayList<Lelongan>();
                sejarahLelongan = lelongService.getAllSejarah(permohonan.getIdPermohonan());
                for (Lelongan sej : sejarahLelongan) {
                    LOG.debug("test ID:" + sej.getIdLelong() + ", kod=" + sej.getKodStatusLelongan().getKod());
                }
                listLel = lelongService.getLeloganTA(idPermohonan);

                listLelongan.add(lelong);
                LOG.debug("listLelongan.size :" + listLelongan.size());
                StringBuilder sb = new StringBuilder();
                if (permohonan != null) {
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        Hakmilik h = hp.getHakmilik();
                        if (sb.length() > 0) {
                            sb.append("<br>");
                        }
                        sb.append(h.getIdHakmilik());
                    }
                }
                idHak1 = sb.toString();

                senaraiEnkuiri = lelongService.getEnkuiri(idPermohonan);


                enkuiri = lelongService.findEnkuiri(idPermohonan);
                enkuiri1 = lelongService.getEnkuiriTA1(idPermohonan);
                
                if (enkuiri != null) {
                    if (enkuiri.getCaraLelong() != null) {
                        if (enkuiri.getCaraLelong().equals("A")) {
                            getContext().getRequest().setAttribute("same", Boolean.FALSE);
                            amaunTunggakan = enkuiri.getAmaunTunggakan();
                        } else {
                            //bersama
                            if (enkuiri.getCaraLelong().equals("B")) {
                                getContext().getRequest().setAttribute("same", Boolean.TRUE);
                                amaunTunggakan = enkuiri.getAmaunTunggakan();
                            }
                        }
                    }
                }

                if (enkuiri1 != null) {
                    if (enkuiri1.getCaraLelong() != null) {
                        if (enkuiri1.getCaraLelong().equals("A")) {
                            getContext().getRequest().setAttribute("same", Boolean.FALSE);
                            amaunTunggakan = enkuiri1.getAmaunTunggakan();
                        } else {
                            if (enkuiri1.getCaraLelong().equals("B")) {
                                getContext().getRequest().setAttribute("same", Boolean.TRUE);
                                amaunTunggakan = enkuiri1.getAmaunTunggakan();
                                senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                                LOG.info("senaraiPT .size :" + senaraiPT.size());
                                if (enkuiri1.getPengguna() != null) {
                                    idPengguna = enkuiri1.getPengguna().getIdPengguna();
                                }
                            }
                        }
                    }
                }
                //sejarah enkuiri
                sejarahEnkuiri = lelongService.getSejarahSiasatan(permohonan.getIdPermohonan());
                kpsn = getContext().getRequest().getParameter("kpsn");
                LOG.info("kpsn :" + kpsn);
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
//        return new JSP("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //Aktif Button (Update Table Mohon dr AK to SD)(lelong-TA) (enkuri - TA)
    public Resolution aktif() throws ParseException, WorkflowException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());

                LOG.debug("permohonan11:" + permohonan.getIdPermohonan());
                permohonan.setInfoAudit(ia);
                permohonan.setStatus("SD");
                lelongService.saveOrUpdate(permohonan);

                senaraiLelongan = lelongService.listLelonganAK1(idPermohonan);
                senaraiEnkuiri = lelongService.getEnkuiri(idPermohonan);

                //update the status from AK to TA
                for (Lelongan l : senaraiLelongan) {
                    LOG.info("Lelongan Id " + l.getIdLelong());
                    l.setKodStatusLelongan(kodStatusLelonganDAO.findById("TA"));
                    lelongService.saveOrUpdatee(l);
                }

                //update the status from AK to TA
                for (Enkuiri e : senaraiEnkuiri) {
                    LOG.info("enkuiri Id " + e.getIdEnkuiri());
                    e.setStatus(kodStatusEnkuiriDAO.findById("TA"));
                    lelongService.saveOrUpdate(e);
                }
            }
        }
        view = true;
        listLel2 = lelongService.listLelonganAK1(idPermohonan);
        checkPermohonan();
        addSimpleMessage("Permohonan Dalam Tindakan Mahkamah.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    @SuppressWarnings("static-access")
    //ni tuk simpan Batalkan Permohonan
    public Resolution simpanButtonBatal() throws WorkflowException, StaleObjectException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

//                enkuiri = lelongService.findEnkuiri(idPermohonan);
                enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
                String result = null;
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());

                LOG.debug("permohonan11:" + permohonan.getIdPermohonan());
                listMohon = lelongService.getMohonSDtoAK(idPermohonan);

                for (Permohonan mohon2 : listMohon) {
                    LOG.info("" + mohon2.getStatus());
                    if (mohon2.getStatus().equals("SD")) {
                        permohonan = mohon2;
                        mohon2.setStatus("MK");
                        lelongService.saveOrUpdate(mohon2);
                    }
                }

                enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
                enkuiri.setStatus(kodStatusEnkuiriDAO.findById("BM"));
                lelongService.saveOrUpdate(enkuiri);

                listLelong = lelongService.getLeloganTA(idPermohonan);
                if (!listLelong.isEmpty()) {
                    for (Lelongan lel : listLelong) {
                        lelong = lel;
                        idLelong = lelong.getIdLelong();
                        idHakmilik = lelong.getHakmilikPermohonan().getHakmilik().getIdHakmilik();

                        lelong.setKodStatusLelongan(kodStatusLelonganDAO.findById("BM"));
                        lelongService.saveOrUpdate(lelong);

                    }
                }

                ulasan = getContext().getRequest().getParameter("permohonan.catatan");
                LOG.info("ulasan : " + ulasan);
                if (StringUtils.isBlank(ulasan)) {
                    addSimpleError("Sila Masukkan 'Ulasan PTD'");
                } else {
                    permohonan.setCatatan(ulasan);
                    lelongService.saveOrUpdate(permohonan);
                }
            }

            listMohonRujukLuar = lelongService.findMohonRujukList(idPermohonan);
            if (listMohonRujukLuar != null) {
                try {
                    tarikhPerintah = sdf.format(mohonRujukLuar.getTarikhSidang()).substring(0, 10);
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }

            view = true;
//        listMohonHakmilik = lelongService.getHakmilikY(permohonan.getIdPermohonan());
            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHakmilik = sb.toString();


            listMohonHakmilik = new ArrayList<HakmilikPermohonan>();
            List<HakmilikPermohonan> ll = lelongService.getHakmilikY(permohonan.getIdPermohonan());
            LOG.info("PermohonPihak : " + ll.size());
            listMohonHakmilik.add(ll.get(0));
        }

        for (PermohonanRujukanLuar mohonRujLuar1 : listMohonRujukLuar) {
            mohonRujukLuar = mohonRujLuar1;
        }
        if (mohonRujukLuar != null) {
            tarikhPerintah = sdf.format(mohonRujukLuar.getTarikhSidang()).substring(0, 10);
        }


        String idMohonBaru = null;
        List<Hakmilik> hh = new ArrayList<Hakmilik>();

        StringBuilder sb = new StringBuilder();
        if (permohonan != null) {
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(h.getIdHakmilik());
            }
        }

        //withdraw id mohon
        withdrawIdPermohonan(permohonan);
        checkPermohonan();
        addSimpleMessage("Permohonan Telah Di Batalkan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");

    }

    //Asing
    @SuppressWarnings("static-access")
    //ni tuk simpan Batalkan Permohonan
    public Resolution simpanButtonBatalAsing() throws WorkflowException, StaleObjectException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

                enkuiri = lelongService.findEnkuiri(idPermohonan);
                String result = null;
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());

                LOG.debug("permohonan11:" + permohonan.getIdPermohonan());
                listMohon = lelongService.getMohonSDtoAK(idPermohonan);

                for (Permohonan mohon2 : listMohon) {
                    LOG.info("" + mohon2.getStatus());
                    if (mohon2.getStatus().equals("SD")) {
                        permohonan = mohon2;
                        mohon2.setStatus("MK");
                        lelongService.saveOrUpdate(mohon2);
                    }
                }

                enkuiri = lelongService.findEnkuiri(idPermohonan);
                enkuiri.setStatus(kodStatusEnkuiriDAO.findById("BP"));
                lelongService.saveOrUpdate(enkuiri);



                String[] idHak2 = getContext().getRequest().getParameterValues("idHakmilik");
                for (String idhak : idHak2) {
                    LOG.info("----------hakmilik yg dipilih---- :" + idhak);
                    LOG.info("hakmilik id : " + idhak);

                    Lelongan lelongg = enkuiriService.findLelong(idPermohonan, idhak);
                    idLelong = lelongg.getIdLelong();
                    LOG.info("id Lelong" + idLelong);
                    lelongg.setKodStatusLelongan(kodStatusLelonganDAO.findById("BP"));
                    lelongService.saveOrUpdate(lelongg);

                }

                ulasan = getContext().getRequest().getParameter("permohonan.catatan");
                LOG.info("ulasan : " + ulasan);
                permohonan.setCatatan(ulasan);

                permohonan.setInfoAudit(info);
                lelongService.saveOrUpdate(permohonan);
            }

            listMohonRujukLuar = lelongService.findMohonRujukList(idPermohonan);
            if (listMohonRujukLuar != null) {
                try {
                    tarikhPerintah = sdf.format(mohonRujukLuar.getTarikhSidang()).substring(0, 10);
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }

            view = true;
//        listMohonHakmilik = lelongService.getHakmilikY(permohonan.getIdPermohonan());
            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("<br>");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHakmilik = sb.toString();


            listMohonHakmilik = new ArrayList<HakmilikPermohonan>();
            List<HakmilikPermohonan> ll = lelongService.getHakmilikY(permohonan.getIdPermohonan());
            LOG.info("PermohonPihak : " + ll.size());
            listMohonHakmilik.add(ll.get(0));
        }

        for (PermohonanRujukanLuar mohonRujLuar1 : listMohonRujukLuar) {
            mohonRujukLuar = mohonRujLuar1;
        }
        if (mohonRujukLuar != null) {
            tarikhPerintah = sdf.format(mohonRujukLuar.getTarikhSidang()).substring(0, 10);
        }


        String idMohonBaru = null;
        List<Hakmilik> hh = new ArrayList<Hakmilik>();



        //withdraw id mohon
        withdrawIdPermohonan(permohonan);

        StringBuilder sb = new StringBuilder();
        if (permohonan != null) {
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(h.getIdHakmilik());
            }
        }

        idHakmilik = sb.toString();

        listMohonHakmilik = new ArrayList<HakmilikPermohonan>();
        List<HakmilikPermohonan> ll = lelongService.getHakmilikY(permohonan.getIdPermohonan());
        LOG.info("PermohonPihak : " + ll.size());
        listMohonHakmilik.add(ll.get(0));
    checkPermohonan();
        addSimpleMessage("Permohonan Telah Di Batalkan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");

    }

    //save mohon_catatan tuk siasatan semula & tangguh lelongan
    public Resolution saveMohonCatatan() {
        LOG.info("----------mohon catatan----------");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

                enkuiri = lelongService.findEnkuiri(idPermohonan);
                Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());

                ulasan = getContext().getRequest().getParameter("permohonan.catatan");
                LOG.info("ulasan : " + ulasan);
                if (StringUtils.isBlank(ulasan)) {
                    addSimpleError("Sila Masukkan 'Ulasan PTD'");
                } else {
                    permohonan.setCatatan(ulasan);

                    permohonan.setInfoAudit(info);
                    lelongService.saveOrUpdate(permohonan);
                }
//
//                String kpsn = getContext().getRequest().getParameter("kpsn");
//                if (kpsn != null) {
//                    LOG.info("kpsn ape :" + kpsn);
//                    if (kpsn.equals("LM")) {
//                        List<Lelongan> ll = lelongService.listLelonganA(permohonan.getIdPermohonan());
//                        for (Lelongan lelongan : ll) {
//                            lelongan.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
//                            lelongService.saveOrUpdate(lelongan);
////                        }
//                            Lelongan lelo = new Lelongan();
//                            lelo.setEnkuiri(enkuiri);
//                            lelo.setPermohonan(permohonan);
//                            lelo.setInfoAudit(info);
//                            lelo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
//                            if (lelongan.getPerihalTanah() != null) {
//                                lelo.setPerihalTanah(lelongan.getPerihalTanah());
//                            }
//                            if (lelongan.getPerihalTanahBahasaInggeris() != null) {
//                                lelo.setPerihalTanahBahasaInggeris(lelongan.getPerihalTanahBahasaInggeris());
//                            }
//                            lelo.setHakmilikPermohonan(lelongan.getHakmilikPermohonan());
//                            lelo.setBil(lelongan.getBil());
//                            lelongService.saveOrUpdate(lelo);
//                            tarikhAkhirBayaran = null;
//                            tarikhLelong = null;
//                            jam = null;
//                            minit = null;
//                            ampm = null;
//                        }
//                    }
//                }
//            }
//            senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
//            for (Lelongan ll : senaraiLelongan) {
//                if (ll.getKodStatusLelongan().getKod().equals("AK")) {
//                    lelong = ll;
//                    break;
//                }
//            }
//
//            try {
//                if (!senaraiLelongan.isEmpty()) {
//                    tarikhLelong = sdf.format(lelong.getTarikhLelong());
//                    tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
//                }
//                if (lelong != null && lelong.getTarikhLelong() != null) {
//                    tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
//                    jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
//                    minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
//                    ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
//                }
//
//            } catch (Exception ex) {
//                LOG.error(ex);
//            }
//
//            listMohonRujukLuar = lelongService.findMohonRujukList(idPermohonan);
                if (listMohonRujukLuar != null) {
                    try {
                        tarikhPerintah = sdf.format(mohonRujukLuar.getTarikhSidang()).substring(0, 10);
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }

                view = true;
//        listMohonHakmilik = lelongService.getHakmilikY(permohonan.getIdPermohonan());
                StringBuilder sb = new StringBuilder();
                if (permohonan != null) {
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        Hakmilik h = hp.getHakmilik();
                        if (sb.length() > 0) {
                            sb.append("<br>");
                        }
                        sb.append(h.getIdHakmilik());
                    }
                }
                idHakmilik = sb.toString();


                listMohonHakmilik = new ArrayList<HakmilikPermohonan>();
                List<HakmilikPermohonan> ll = lelongService.getHakmilikY(permohonan.getIdPermohonan());
                LOG.info("PermohonPihak : " + ll.size());
                listMohonHakmilik.add(ll.get(0));
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    // calendar enkuri baru
    public Resolution showForm1() {
        //calender
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx.getKodCawangan().getKod());
        return new JSP("lelong/calendar_lelong11.jsp").addParameter("popup", "true");
    }

    //create id enkuiri baru
    public Resolution simpanEnkuiri() throws WorkflowException {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
            LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

            senaraiEnkuiriTA = lelongService.getEnkuiriTA(idPermohonan);
//            enkuiri = lelongService.findEnkuiri(idPermohonan);
            enkuiri = lelongService.getEnkuiriTA1(idPermohonan);

            if (StringUtils.isNotBlank(kpsn)) {
                getContext().getRequest().setAttribute("kpsn", kpsn);
            }


            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

//            for (Enkuiri enkuiri2 : senaraiEnkuiri) {
//                if (enkuiri2.getStatus().getKod().equals("AK")) {
//                    enkuiri = enkuiri2;
//                    enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("TG"));
//                    enkuiriService.save(enkuiri2);
//                }
//            }

            Enkuiri enk = new Enkuiri();
            if (StringUtils.isNotEmpty(enkuiri.getTujuanGadaian())) {
                enk.setTujuanGadaian(enkuiri.getTujuanGadaian());
            }
            if (enkuiri.getAmaunTunggakan() != null) {
                enk.setAmaunTunggakan(enkuiri.getAmaunTunggakan());
            }
            enk.setPermohonan(permohonan);
            enk.setCawangan(peng.getKodCawangan());
            enk.setInfoAudit(ia);
            enk.setBilanganKes(enkuiri.getBilanganKes());
            tarikhEnkuiri = tarikhEnkuiri + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

            try {
                enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
            } catch (Exception ex) {
                LOG.error(ex);
            }

            enk.setTempat(tempat);
            enk.setPerkara(perkara);
            enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
            enkuiriService.save(enk);

            ulasan = getContext().getRequest().getParameter("permohonan.catatan");
            LOG.info("ulasan : " + ulasan);
            if (!StringUtils.isBlank(ulasan)) {
                permohonan.setCatatan(ulasan);
            }

            LOG.debug("SD - AK:" + permohonan.getIdPermohonan());
            permohonan.setInfoAudit(ia);
            permohonan.setStatus("AK");
            lelongService.saveOrUpdate(permohonan);

            try {
                tarikhEnkuiri = sdf.format(enk.getTarikhEnkuiri()).substring(0, 10);
                jam = sdf.format(enk.getTarikhEnkuiri()).substring(11, 13);
                minit = sdf.format(enk.getTarikhEnkuiri()).substring(14, 16);
                ampm = sdf.format(enk.getTarikhEnkuiri()).substring(17, 19);
                tempat = enk.getTempat();
                perkara = enk.getPerkara();
            } catch (Exception ex) {
                LOG.error(ex);
            }

            KodKeputusan kod = kodkeputusanDAO.findById("EM");

            enkuiri = lelongService.findEnkuiri(idPermohonan);
            if (enkuiri != null) {
                try {
                    List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                    for (Task t : l) {
                        stage = t.getSystemAttributes().getStage();
                        participant = t.getSystemAttributes().getParticipantName();
                        LOG.info("Permohonan Ini Diperingkat1: " + stage);
                    }
                } catch (Exception e) {
                    LOG.error("error ::" + e.getMessage());
                }
            }

            LOG.info("Permohonan Ini Diperingkat2: " + stage);

            List<FasaPermohonan> senaraiFasa = lelongService.findFasa(idPermohonan);
            for (FasaPermohonan fp : senaraiFasa) {
                LOG.info("stage mn dah :" + fp.getIdAliran());

                fp.setKeputusan(kod);
                lelongService.saveUpdate2(fp);
                break;

            }

            senaraiEnkuiri = lelongService.getEnkuiriNotAK(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);

            //sejarah enkuiri
            sejarahEnkuiri = lelongService.getSejarahSiasatan(permohonan.getIdPermohonan());

            showSelesai = true;
            showEnkuiri = true;
            checkPermohonan();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //next stage CetakSuratSiasatan for EM && stage 16H for LM
    public Resolution selesai() throws WorkflowException, StaleObjectException {
        LOG.info("------------- NEXT STAGE ---------------");
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
            LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());

            pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            LOG.info("Id Pengguna" + pengguna.getIdPengguna());

            kpsn = getContext().getRequest().getParameter("kpsn");
            LOG.info("kpsn :" + kpsn);

            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());

            LOG.debug("SD - AK:" + permohonan.getIdPermohonan());
            permohonan.setInfoAudit(ia);
            permohonan.setStatus("AK");
            lelongService.saveOrUpdate(permohonan);

            ctxOnBehalf = WorkFlowService.authenticate(pengguna.getIdPengguna());
            if (ctxOnBehalf != null) {
                LOG.info("ctxOnBehalf : " + ctxOnBehalf);
                LOG.info("id mohon : " + permohonan.getIdPermohonan());

                List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                LOG.info("1) Task FOund(size)::" + l.size());
                if (l.isEmpty()) {
                    LOG.info("l.isEmpty()");
                    try {
                        Thread.sleep(1000);
                        l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                    } catch (Exception ex) {
                        LOG.error(ex);
                    }
                }
                LOG.info("2) Task FOund (size)::" + l.size());
                for (Task t : l) {
                    LOG.info("Masuk For list l");
                    stageId = t.getSystemAttributes().getStage();
                    taskId = t.getSystemAttributes().getTaskId();
                    LOG.debug("Task Id::" + taskId);
                    LOG.debug("Acquire by::" + t.getSystemAttributes().getAcquiredBy());
                    if (t.getSystemAttributes().getAcquiredBy() == null) {
                        ctxOnBehalf = WorkFlowService.authenticate("ptptdlelong1");
                        WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                    } else {
                        ctxOnBehalf = WorkFlowService.authenticate(t.getSystemAttributes().getAcquiredBy());

                    }
                    LOG.info("GROUP " + t.getSystemAttributes().getAssigneeGroups());

                    //
                    LOG.debug("Claim Found Task::" + taskId);
                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, kpsn); //

                    LOG.debug("stage id ::::::::::::::::" + stageId);
                    LOG.debug("next stage ::::::::::::::::" + nextStage);
                    LOG.debug("Tugasan dihantar ke : " + nextStage);
                }
            }
        }
        checkPermohonan();
        addSimpleMessage("Permohonan telah diteruskan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //calendar lelong baru
    public Resolution showForm2() {
        //calender
        etanahActionBeanContext ctx1 = new etanahActionBeanContext();
        ctx1 = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx1.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx1.getKodCawangan().getKod());
        return new JSP("lelong/calendar_lelong12.jsp").addParameter("popup", "true");
    }

    //create lelong baru
    //bersama
    public Resolution saveBersama() throws ParseException, WorkflowException {
        LOG.info("----bersama------");
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        enkuiri = lelongService.findEnkuiri(idPermohonan);


        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());


        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
        LOG.info("senaraiLelongan1 sama.size : " + senaraiLelongan.size());
        listLel = lelongService.getLeloganASC(idPermohonan);
        senaraiLelongan4 = lelongService.getLeloganTA(idPermohonan);
        senaraiEnkuiriTA = lelongService.getEnkuiriTA(idPermohonan);
//        lelong = lelongService.findLelong(idPermohonan);

//        try {
        listLel = lelongService.getLeloganTA(idPermohonan);
        enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
//        enkuiri = lelongService.findEnkuiri(idPermohonan);
        for (int i = 0; i < listLel.size(); i++) {
            LOG.info("loop :" + i);
            Lelongan lelo = new Lelongan();
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());

//            for (Lelongan lell : senaraiLelongan4) {
//                if (lell.getKodStatusLelongan().getKod().equals("TA")) {
//                    lelong = lell;
//                    lell.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
//                    enkuiriService.simpan(lell);
//                }
//            }

            lelo.setEnkuiri(enkuiri);
            lelo.setBil(lel.getBil());
            lelo.setInfoAudit(ia);
            lelo.setPermohonan(permohonan);
            lelo.setHakmilikPermohonan(lel.getHakmilikPermohonan());
            if (lel.getPerihalTanah() != null) {
                lelo.setPerihalTanah(lel.getPerihalTanah());
            }
            if (lel.getPerihalTanahBahasaInggeris() != null) {
                lelo.setPerihalTanahBahasaInggeris(lel.getPerihalTanahBahasaInggeris());
            }
            if (lel.getJurulelong() != null) {
                lelo.setJurulelong(lel.getJurulelong());
            }


            tarikhLelong = tarikhLelong + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
            LOG.info("tarikhLelong :" + tarikhLelong);
            try {
                lelo.setTarikhLelong(sdf.parse(tarikhLelong));
            } catch (Exception ex) {
                LOG.error("tarikhLelong-780 : " + ex);
            }
            try {
                lelo.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error("tarikhAkhirBayaran-785 : " + ex);
            }

            try {

                if (lelong != null && lelong.getTarikhLelong() != null) {
                    tarikhLelong = sdf.format(lelo.getTarikhLelong()).substring(0, 10);
                    tarikhAkhirBayaran = sdf1.format(lelo.getTarikhAkhirBayaran());
                    jam1 = sdf.format(lelo.getTarikhLelong()).substring(11, 13);
                    minit1 = sdf.format(lelo.getTarikhLelong()).substring(14, 16);
                    ampm1 = sdf.format(lelo.getTarikhLelong()).substring(17, 19);
                    tempat = lelo.getTempat().toString();

                    String harga = getContext().getRequest().getParameter("hargaRizab");
                    LOG.info("harga :" + harga);
                    BigDecimal hargaRizab = new BigDecimal((harga.trim()).replaceAll(",", ""));
                    LOG.info("hargaRizab :" + hargaRizab);

                    String depo = getContext().getRequest().getParameter("deposit");
                    LOG.info("depo :" + depo);
                    BigDecimal deposit = new BigDecimal((depo.trim()).replaceAll(",", ""));
                    LOG.info("deposit :" + deposit);
//                    hargaRizab = lelo.getHargaRizab();
//                    deposit = lelo.getDeposit();
                }

            } catch (Exception ex) {
                LOG.error(ex);
            }


            try {
                lelo.setTempat(getContext().getRequest().getParameter("tempat1"));
                lelo.setHargaRizab(hargaRizab);
                lelo.setDeposit(deposit);
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                lelo.setPengguna(peng);
                lelo.setPermohonan(permohonan);
                lelo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
                lelo.setInfoAudit(ia);
                enkuiriService.simpan(lelo);
            } catch (Exception ex) {
                LOG.error("lelo : " + ex);
            }

            enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            enkuiri.setDeposit(deposit);
            enkuiri.setHargaRizab(hargaRizab);
            enkuiri.setAmaunTunggakan(amaunTunggakan);
//            lelong.setPengguna(pengguna);
//            lelongService.saveOrUpdate(lelong);

            ulasan = getContext().getRequest().getParameter("permohonan.catatan");
            LOG.info("ulasan : " + ulasan);
            if (!StringUtils.isBlank(ulasan)) {
                permohonan.setCatatan(ulasan);
            }

            LOG.debug("SD - AK:" + permohonan.getIdPermohonan());
            permohonan.setInfoAudit(ia);
            permohonan.setStatus("AK");
            lelongService.saveOrUpdate(permohonan);

            //create enkuiri TA-AK
            for (Enkuiri enkuiri2 : senaraiEnkuiriTA) {
                if (enkuiri2.getStatus().getKod().equals("TA")) {
                    enkuiri = enkuiri2;
                    enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                    enkuiriService.save(enkuiri2);
                }
            }
        }


        KodKeputusan kod = kodkeputusanDAO.findById("LM");

        enkuiri = lelongService.findEnkuiri(idPermohonan);
        if (enkuiri != null) {
            try {
                List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                for (Task t : l) {
                    stage = t.getSystemAttributes().getStage();
                    participant = t.getSystemAttributes().getParticipantName();
                    LOG.info("Permohonan Ini Diperingkat1: " + stage);
                }
            } catch (Exception e) {
                LOG.error("error ::" + e.getMessage());
            }
        }

        LOG.info("Permohonan Ini Diperingkat2: " + stage);

        List<FasaPermohonan> senaraiFasa = lelongService.findFasa(idPermohonan);
        for (FasaPermohonan fp : senaraiFasa) {
            LOG.info("stage mn dah :" + fp.getIdAliran());

            fp.setKeputusan(kod);
            lelongService.saveUpdate2(fp);
            break;

        }

        //sejarah lelongan                    
        sejarahLelongan = new ArrayList<Lelongan>();
        sejarahLelongan = lelongService.getAllSejarah(idPermohonan);

        listLel = lelongService.getLeloganTA(idPermohonan);

        StringBuilder sb = new StringBuilder();
        if (permohonan != null) {
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(h.getIdHakmilik());
            }
        }
        idHak1 = sb.toString();

        showSelesai = true;
        showLelongan = true;

        genReport();
//        genReport2();

        //VIEW 16 H
        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumen16H1(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("16H")) {
                    setDokumen(ff.getDokumen());
                    break;
                }
            }
        }
        if (getDokumen() != null && getDokumen().getNamaFizikal() != null) {
            LOG.info("16H di jana");
            setIdDokumen16H(getDokumen().getIdDokumen());
            LOG.info("Id 16H " + getIdDokumen16H());
        }

        //VIEW Warta
        List<KandunganFolder> listKandunganFolder2 = lelongService.getListDokumenWarta(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder2.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder2) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("SRW")) {
                    setDokumen2(ff.getDokumen());
                    break;
                }
            }
        }
        if (getDokumen2() != null && getDokumen2().getNamaFizikal() != null) {
            LOG.info("Warta di jana");
            setIdDokumenWarta(getDokumen2().getIdDokumen());
            LOG.info("Id Warta " + getIdDokumenWarta());
        }

        senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
        LOG.info("senaraiPT .size :" + senaraiPT.size());
        
        checkPermohonan();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    //berasingan
    public Resolution simpanLelong() throws WorkflowException {
        LOG.info(getContext().getRequest().getParameter("idPermohonan"));
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("Pengguna " + pengguna);
        InfoAudit ia = new InfoAudit();

        listLel = lelongService.getLeloganTA(idPermohonan);
        senaraiLelongan4 = lelongService.getLeloganTA(idPermohonan);
        enkuiri = lelongService.getEnkuiriTA1(idPermohonan);
        senaraiEnkuiriTA = lelongService.getEnkuiriTA(idPermohonan);

        try {
            for (int i = 0; i < listLel.size(); i++) {
                LOG.info("loop :" + i);
                Lelongan leloo = new Lelongan();
                Lelongan lel = listLel.get(i);
                LOG.info("Lelong : " + lel.getIdLelong());

//                for (Lelongan lell : senaraiLelongan4) {
//                    if (lell.getKodStatusLelongan().getKod().equals("TA")) {
//                        lelong = lell;
//                        lell.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
//                        enkuiriService.simpan(lell);
//                    }
//                }

                leloo.setEnkuiri(enkuiri);
                leloo.setBil(lel.getBil());
                leloo.setInfoAudit(ia);
                leloo.setPermohonan(permohonan);
                leloo.setHakmilikPermohonan(lel.getHakmilikPermohonan());
                if (lel.getPerihalTanah() != null) {
                    leloo.setPerihalTanah(lel.getPerihalTanah());
                }
                if (lel.getPerihalTanahBahasaInggeris() != null) {
                    leloo.setPerihalTanahBahasaInggeris(lel.getPerihalTanahBahasaInggeris());
                }
                if (lel.getJurulelong() != null) {
                    leloo.setJurulelong(lel.getJurulelong());
                }

                tarikhLelong = tarikhLelong + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
                LOG.info("tarikhLelong :" + tarikhLelong);
                try {
                    leloo.setTarikhLelong(sdf.parse(tarikhLelong));
                } catch (Exception ex) {
                    LOG.error("tarikhLelong-780 : " + ex);
                }
                try {
                    leloo.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
                } catch (Exception ex) {
                    LOG.error("tarikhAkhirBayaran-785 : " + ex);
                }

                leloo.setTempat(getContext().getRequest().getParameter("tempat1"));

                String harga = getContext().getRequest().getParameter("hargaRizab" + i);
                LOG.info("harga :" + harga);
                BigDecimal hargaRizab = new BigDecimal((harga.trim()).replaceAll(",", ""));
                LOG.info("hargaRizab :" + hargaRizab);
                leloo.setHargaRizab(hargaRizab);

                String depo = getContext().getRequest().getParameter("deposit" + i);
                LOG.info("depo :" + depo);
                BigDecimal deposit = new BigDecimal((depo.trim()).replaceAll(",", ""));
                LOG.info("deposit :" + deposit);
                leloo.setDeposit(deposit);

                leloo.setPermohonan(permohonan);
                leloo.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                leloo.setInfoAudit(ia);
                enkuiriService.simpan(leloo);
                lelong = leloo;
            }

        } catch (Exception e) {
            LOG.error("error " + e);
        }

        //create enkuiri TA-AK
        for (Enkuiri enkuiri2 : senaraiEnkuiriTA) {
            if (enkuiri2.getStatus().getKod().equals("TA")) {
                enkuiri = enkuiri2;
                enkuiri2.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                enkuiriService.save(enkuiri2);
            }
        }


        ulasan = getContext().getRequest().getParameter("permohonan.catatan");
        LOG.info("ulasan : " + ulasan);
        if (!StringUtils.isBlank(ulasan)) {
            permohonan.setCatatan(ulasan);
        }

        LOG.debug("SD - AK:" + permohonan.getIdPermohonan());
        permohonan.setInfoAudit(ia);
        permohonan.setStatus("AK");
        lelongService.saveOrUpdate(permohonan);

        KodKeputusan kod = kodkeputusanDAO.findById("LM");

        enkuiri = lelongService.findEnkuiri(idPermohonan);
        if (enkuiri != null) {
            try {
                List<Task> l = WorkFlowService.queryTasksByAdmin(idPermohonan);
                for (Task t : l) {
                    stage = t.getSystemAttributes().getStage();
                    participant = t.getSystemAttributes().getParticipantName();
                    LOG.info("Permohonan Ini Diperingkat1: " + stage);
                }
            } catch (Exception e) {
                LOG.error("error ::" + e.getMessage());
            }
        }

        LOG.info("Permohonan Ini Diperingkat2: " + stage);

        List<FasaPermohonan> senaraiFasa = lelongService.findFasa(idPermohonan);
        for (FasaPermohonan fp : senaraiFasa) {
            LOG.info("stage mn dah :" + fp.getIdAliran());

            fp.setKeputusan(kod);
            lelongService.saveUpdate2(fp);
            break;

        }

        //sejarah lelongan                    
        sejarahLelongan = new ArrayList<Lelongan>();
        sejarahLelongan = lelongService.getAllSejarah(idPermohonan);

        listLel = lelongService.getLeloganTA(idPermohonan);

        StringBuilder sb = new StringBuilder();
        if (permohonan != null) {
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                Hakmilik h = hp.getHakmilik();
                if (sb.length() > 0) {
                    sb.append("<br>");
                }
                sb.append(h.getIdHakmilik());
            }
        }
        idHak1 = sb.toString();

        showSelesai = true;
        showLelongan = true;

        genReport();
//        genReport2();

        //VIEW 16 H
        List<KandunganFolder> listKandunganFolder = lelongService.getListDokumen16H(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("16H1")) {
                    setDokumen(ff.getDokumen());
                    break;
                }
            }
        }
        if (getDokumen() != null && getDokumen().getNamaFizikal() != null) {
            LOG.info("16H di jana");
            setIdDokumen16H(getDokumen().getIdDokumen());
            LOG.info("Id 16H " + getIdDokumen());
        }

        //VIEW Warta
        List<KandunganFolder> listKandunganFolder2 = lelongService.getListDokumenWarta(permohonan.getFolderDokumen().getFolderId());
        if (!listKandunganFolder2.isEmpty()) {
            for (KandunganFolder ff : listKandunganFolder2) {
                if (ff.getDokumen().getKodDokumen().getKod().equals("SRW")) {
                    setDokumen2(ff.getDokumen());
                    break;
                }
            }
        }
        if (getDokumen2() != null && getDokumen2().getNamaFizikal() != null) {
            LOG.info("Warta di jana");
            setIdDokumenWarta(getDokumen2().getIdDokumen());
            LOG.info("Id Warta " + getIdDokumenWarta());
        }


        checkPermohonan();
        addSimpleMessage("Maklumat Telah Berjaya Disimpan");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    @SuppressWarnings("static-access")
    public void withdrawIdPermohonan(Permohonan permohonan) throws WorkflowException, StaleObjectException {

        LOG.info("IDMohon : " + permohonan.getIdPermohonan());
        idPermohonan = permohonan.getIdPermohonan();
        List senaraiTask = WorkFlowService.queryTasksByAdmin(idPermohonan);
        LOG.info("senaraiTask : " + senaraiTask.size());
        if (senaraiTask.isEmpty()) {
            LOG.info("-----idPermohonan tidak di jumpai-----");
        } else {
            Task task = (Task) senaraiTask.get(0);
            if (task != null) {
                LOG.info("-----idPermohonan di jumpai-----");
                LOG.info(task);
                String taskId = task.getSystemAttributes().getTaskId();
                WorkFlowService.withdrawTask(taskId);
                LOG.info("------withdrawTask Taskid sucessfull!!!----- ");
                LOG.info("------Finish------");
            }
        }
    }

    public Resolution reset() {
        LOG.info("---nk reset---");
        permohonan = new Permohonan();
        idPermohonan = null;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKemasukanPerintahMahkamah.jsp");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getMohonRujukLuar() {
        return mohonRujukLuar;
    }

    public void setMohonRujukLuar(PermohonanRujukanLuar mohonRujukLuar) {
        this.mohonRujukLuar = mohonRujukLuar;
    }

    public List<HakmilikPermohonan> getListMohonHakmilik() {
        return listMohonHakmilik;
    }

    public void setListMohonHakmilik(List<HakmilikPermohonan> listMohonHakmilik) {
        this.listMohonHakmilik = listMohonHakmilik;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getTarikhPerintah() {
        return tarikhPerintah;
    }

    public void setTarikhPerintah(String tarikhPerintah) {
        this.tarikhPerintah = tarikhPerintah;
    }

    public List<Permohonan> getListMohon() {
        return listMohon;
    }

    public void setListMohon(List<Permohonan> listMohon) {
        this.listMohon = listMohon;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getListHakmilikMohon() {
        return listHakmilikMohon;
    }

    public void setListHakmilikMohon(List<HakmilikPermohonan> listHakmilikMohon) {
        this.listHakmilikMohon = listHakmilikMohon;
    }

    public List<PermohonanRujukanLuar> getListMohonRujukLuar() {
        return listMohonRujukLuar;
    }

    public void setListMohonRujukLuar(List<PermohonanRujukanLuar> listMohonRujukLuar) {
        this.listMohonRujukLuar = listMohonRujukLuar;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public List<Lelongan> getListLelongHakmilik() {
        return listLelongHakmilik;
    }

    public void setListLelongHakmilik(List<Lelongan> listLelongHakmilik) {
        this.listLelongHakmilik = listLelongHakmilik;
    }

    public List<Lelongan> getListLelong() {
        return listLelong;
    }

    public void setListLelong(List<Lelongan> listLelong) {
        this.listLelong = listLelong;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public Long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(Long idLelong) {
        this.idLelong = idLelong;
    }

    public String[] getIdHak() {
        return idHak;
    }

    public void setIdHak(String[] idHak) {
        this.idHak = idHak;
    }

    public String getIdHak1() {
        return idHak1;
    }

    public void setIdHak1(String idHak1) {
        this.idHak1 = idHak1;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
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

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public List<FasaPermohonan> getFasa() {
        return fasa;
    }

    public void setFasa(List<FasaPermohonan> fasa) {
        this.fasa = fasa;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public boolean isViewSiasatan() {
        return viewSiasatan;
    }

    public void setViewSiasatan(boolean viewSiasatan) {
        this.viewSiasatan = viewSiasatan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public LelongService getLelongService() {
        return lelongService;
    }

    public void setLelongService(LelongService lelongService) {
        this.lelongService = lelongService;
    }

    public EnkuiriService getEnkuiriService() {
        return enkuiriService;
    }

    public void setEnkuiriService(EnkuiriService enkuiriService) {
        this.enkuiriService = enkuiriService;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public List<BigDecimal> getListHarga() {
        return listHarga;
    }

    public void setListHarga(List<BigDecimal> listHarga) {
        this.listHarga = listHarga;
    }

    public List<BigDecimal> getListDeposit() {
        return listDeposit;
    }

    public void setListDeposit(List<BigDecimal> listDeposit) {
        this.listDeposit = listDeposit;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public List<Lelongan> getSenaraiLelongan4() {
        return senaraiLelongan4;
    }

    public void setSenaraiLelongan4(List<Lelongan> senaraiLelongan4) {
        this.senaraiLelongan4 = senaraiLelongan4;
    }

    public BigDecimal getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(BigDecimal hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public List<HakmilikPermohonan> getListMohonHakmilik1() {
        return listMohonHakmilik1;
    }

    public void setListMohonHakmilik1(List<HakmilikPermohonan> listMohonHakmilik1) {
        this.listMohonHakmilik1 = listMohonHakmilik1;
    }

    public List<PermohonanRujukanLuar> getListMohonRujukLuar1() {
        return listMohonRujukLuar1;
    }

    public void setListMohonRujukLuar1(List<PermohonanRujukanLuar> listMohonRujukLuar1) {
        this.listMohonRujukLuar1 = listMohonRujukLuar1;
    }

    public List<HakmilikUrusan> getListHakmilikUrusan() {
        return listHakmilikUrusan;
    }

    public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
        this.listHakmilikUrusan = listHakmilikUrusan;
    }

    public List<CalenderLelong> getListCalender3() {
        return listCalender3;
    }

    public void setListCalender3(List<CalenderLelong> listCalender3) {
        this.listCalender3 = listCalender3;
    }

    public List<CalenderLelong> getListCalender4() {
        return listCalender4;
    }

    public void setListCalender4(List<CalenderLelong> listCalender4) {
        this.listCalender4 = listCalender4;
    }

    public List<Transaksi> getListT() {
        return listT;
    }

    public void setListT(List<Transaksi> listT) {
        this.listT = listT;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
    }

    public List<PermohonanRujukanLuar> getListHakMilikUrusan() {
        return listHakMilikUrusan;
    }

    public void setListHakMilikUrusan(List<PermohonanRujukanLuar> listHakMilikUrusan) {
        this.listHakMilikUrusan = listHakMilikUrusan;
    }

    public List<HakmilikUrusan> getAddCheckUrusan() {
        return addCheckUrusan;
    }

    public void setAddCheckUrusan(List<HakmilikUrusan> addCheckUrusan) {
        this.addCheckUrusan = addCheckUrusan;
    }

    public List<PermohonanRujukanLuar> getAddListHakMilikUrusan() {
        return addListHakMilikUrusan;
    }

    public void setAddListHakMilikUrusan(List<PermohonanRujukanLuar> addListHakMilikUrusan) {
        this.addListHakMilikUrusan = addListHakMilikUrusan;
    }

    public String getJam1() {
        return jam1;
    }

    public void setJam1(String jam1) {
        this.jam1 = jam1;
    }

    public String getMinit1() {
        return minit1;
    }

    public void setMinit1(String minit1) {
        this.minit1 = minit1;
    }

    public String getAmpm1() {
        return ampm1;
    }

    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }

    public List<Lelongan> getSejarahLelongan() {
        return sejarahLelongan;
    }

    public void setSejarahLelongan(List<Lelongan> sejarahLelongan) {
        this.sejarahLelongan = sejarahLelongan;
    }

    public List<Enkuiri> getSejarahEnkuiri() {
        return sejarahEnkuiri;
    }

    public void setSejarahEnkuiri(List<Enkuiri> sejarahEnkuiri) {
        this.sejarahEnkuiri = sejarahEnkuiri;
    }

    public List<Lelongan> getListLel2() {
        return listLel2;
    }

    public void setListLel2(List<Lelongan> listLel2) {
        this.listLel2 = listLel2;
    }

    public String getTarikhLelongTerdahulu() {
        return tarikhLelongTerdahulu;
    }

    public void setTarikhLelongTerdahulu(String tarikhLelongTerdahulu) {
        this.tarikhLelongTerdahulu = tarikhLelongTerdahulu;
    }

    public String getTarikhEnkuiriTerdahulu() {
        return tarikhEnkuiriTerdahulu;
    }

    public void setTarikhEnkuiriTerdahulu(String tarikhEnkuiriTerdahulu) {
        this.tarikhEnkuiriTerdahulu = tarikhEnkuiriTerdahulu;
    }

    public List<Enkuiri> getListEnkuiri() {
        return listEnkuiri;
    }

    public void setListEnkuiri(List<Enkuiri> listEnkuiri) {
        this.listEnkuiri = listEnkuiri;
    }

    public List<Enkuiri> getSejarahEnkuiri2() {
        return sejarahEnkuiri2;
    }

    public void setSejarahEnkuiri2(List<Enkuiri> sejarahEnkuiri2) {
        this.sejarahEnkuiri2 = sejarahEnkuiri2;
    }

    public List<Lelongan> getSenaraiLelongan5() {
        return senaraiLelongan5;
    }

    public void setSenaraiLelongan5(List<Lelongan> senaraiLelongan5) {
        this.senaraiLelongan5 = senaraiLelongan5;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getTempoh() {
        return tempoh;
    }

    public void setTempoh(String tempoh) {
        this.tempoh = tempoh;
    }

    public List<Enkuiri> getSenaraiEnkuiriTA() {
        return senaraiEnkuiriTA;
    }

    public void setSenaraiEnkuiriTA(List<Enkuiri> senaraiEnkuiriTA) {
        this.senaraiEnkuiriTA = senaraiEnkuiriTA;
    }

    public boolean isLelongForm() {
        return lelongForm;
    }

    public void setLelongForm(boolean lelongForm) {
        this.lelongForm = lelongForm;
    }

    public boolean isEnkuiriForm() {
        return enkuiriForm;
    }

    public void setEnkuiriForm(boolean enkuiriForm) {
        this.enkuiriForm = enkuiriForm;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public String getMohonrl() {
        return mohonrl;
    }

    public void setMohonrl(String mohonrl) {
        this.mohonrl = mohonrl;
    }

    public List<HakmilikUrusan> getAddCheckUrusanMahkamah() {
        return addCheckUrusanMahkamah;
    }

    public void setAddCheckUrusanMahkamah(List<HakmilikUrusan> addCheckUrusanMahkamah) {
        this.addCheckUrusanMahkamah = addCheckUrusanMahkamah;
    }

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public String getIdPerserahan2() {
        return idPerserahan2;
    }

    public void setIdPerserahan2(String idPerserahan2) {
        this.idPerserahan2 = idPerserahan2;
    }

    public String getIdPerserahan3() {
        return idPerserahan3;
    }

    public void setIdPerserahan3(String idPerserahan3) {
        this.idPerserahan3 = idPerserahan3;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar() {
        return addCheckPermohonanRujukanLuar;
    }

    public void setAddCheckPermohonanRujukanLuar(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar) {
        this.addCheckPermohonanRujukanLuar = addCheckPermohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getAddCheckPermohonanRujukanLuar2() {
        return addCheckPermohonanRujukanLuar2;
    }

    public void setAddCheckPermohonanRujukanLuar2(List<PermohonanRujukanLuar> addCheckPermohonanRujukanLuar2) {
        this.addCheckPermohonanRujukanLuar2 = addCheckPermohonanRujukanLuar2;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan() {
        return addCheckDaftarHakMilikUrusan;
    }

    public void setAddCheckDaftarHakMilikUrusan(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan) {
        this.addCheckDaftarHakMilikUrusan = addCheckDaftarHakMilikUrusan;
    }

    public List<HakmilikUrusan> getAddCheckDaftarHakMilikUrusan2() {
        return addCheckDaftarHakMilikUrusan2;
    }

    public void setAddCheckDaftarHakMilikUrusan2(List<HakmilikUrusan> addCheckDaftarHakMilikUrusan2) {
        this.addCheckDaftarHakMilikUrusan2 = addCheckDaftarHakMilikUrusan2;
    }

    public List<HakmilikPermohonan> getGetIdMohon() {
        return getIdMohon;
    }

    public void setGetIdMohon(List<HakmilikPermohonan> getIdMohon) {
        this.getIdMohon = getIdMohon;
    }

    public List<HakmilikPermohonan> getGetIdMohon2() {
        return getIdMohon2;
    }

    public void setGetIdMohon2(List<HakmilikPermohonan> getIdMohon2) {
        this.getIdMohon2 = getIdMohon2;
    }

    public List<Permohonan> getTarikhPerserahan() {
        return tarikhPerserahan;
    }

    public void setTarikhPerserahan(List<Permohonan> tarikhPerserahan) {
        this.tarikhPerserahan = tarikhPerserahan;
    }

    public boolean isShowSelesai() {
        return showSelesai;
    }

    public void setShowSelesai(boolean showSelesai) {
        this.showSelesai = showSelesai;
    }

    public boolean isShowEnkuiri() {
        return showEnkuiri;
    }

    public void setShowEnkuiri(boolean showEnkuiri) {
        this.showEnkuiri = showEnkuiri;
    }

    public boolean isShowLelongan() {
        return showLelongan;
    }

    public void setShowLelongan(boolean showLelongan) {
        this.showLelongan = showLelongan;
    }

    public boolean isShow16H() {
        return show16H;
    }

    public void setShow16H(boolean show16H) {
        this.show16H = show16H;
    }

    public boolean isShowWarta() {
        return showWarta;
    }

    public void setShowWarta(boolean showWarta) {
        this.showWarta = showWarta;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public long getIdDokumen16H() {
        return idDokumen16H;
    }

    public void setIdDokumen16H(long idDokumen16H) {
        this.idDokumen16H = idDokumen16H;
    }

    public long getIdDokumenWarta() {
        return idDokumenWarta;
    }

    public void setIdDokumenWarta(long idDokumenWarta) {
        this.idDokumenWarta = idDokumenWarta;
    }

    public Dokumen getDokumen2() {
        return dokumen2;
    }

    public void setDokumen2(Dokumen dokumen2) {
        this.dokumen2 = dokumen2;
    }

    /**
     * @return the senaraiPT
     */
    public List<Pengguna> getSenaraiPT() {
        return senaraiPT;
    }

    /**
     * @param senaraiPT the senaraiPT to set
     */
    public void setSenaraiPT(List<Pengguna> senaraiPT) {
        this.senaraiPT = senaraiPT;
    }

    /**
     * @return the idPengguna
     */
    public String getIdPengguna() {
        return idPengguna;
    }

    /**
     * @param idPengguna the idPengguna to set
     */
    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    /**
     * @return the idDokumen
     */
    public Long getIdDokumen() {
        return idDokumen;
    }

    /**
     * @param idDokumen the idDokumen to set
     */
    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }
}
