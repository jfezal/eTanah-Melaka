/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusDokumenCapaiDAO;
import etanah.dao.KodStsPembidaDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.dao.WakilPihakDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.DokumenKewangan;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodKeputusan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStsPembida;
import etanah.model.Lelongan;
import etanah.model.Pembida;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanCarian;
import etanah.model.Pihak;
import etanah.model.Transaksi;
import etanah.model.WakilPihak;
import etanah.service.HakmilikService;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.service.common.PihakService;
import etanah.util.DateUtil;
import etanah.util.FileUtil;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.StreamingResolution;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.metadata.routingslip.model.ParticipantsType;
import oracle.bpel.services.workflow.task.model.Task;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;

/**
 *
 * @author mazurahayati.yusop, nur.aqilah
 */
@UrlBinding("/lelong/senaraipembidaJLB")
public class UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean extends AbleActionBean {

    @Inject
    HakmilikService hakmilikService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private LelonganDAO lelonganDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    @Inject
    private PembidaDAO pembidaDAO;
    @Inject
    private KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private KodStsPembidaDAO kodStsPembidaDAO;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService enkuiriService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(UtilitiSenaraiPermohonanLelonganPembidaJLBActionBean.class);
    private String status;
    private String participant;
    private String stage;
    FileBean fileToBeUploaded;
    private static boolean isDebug = LOG.isDebugEnabled();
    private static boolean IS_DEBUG = LOG.isDebugEnabled();
    private Pengguna pengguna;
    private long idDokumen;
    private String idPermohonan;
    private String idHakmilik;
    private String idLelong;
    private String idPembida;
    private String idPihak;
    private String kodJenis;
    private String noRujukan;
    private String kodsts;
    private String tarikhMula;
    private String tarikhTamat;
    public String noPengenalan;
    private String noic;
    private String idMohonSebelum;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<Permohonan> senaraiPermohonan;
    private List<DokumenKewangan> checkBayaranPerintah;
    private List<Permohonan> checkIdMohonSebelum;
    private List<Lelongan> listLel;
    private List<Lelongan> listLel1;
    private List<Lelongan> listLel2;
    private List<Pembida> list = new ArrayList<Pembida>();
    private List<Pembida> list2 = new ArrayList<Pembida>();
    private List<Pembida> listPembida;
    private List listBida = new ArrayList();
    private List<PermohonanCarian> senaraiPermohonanCarian;
    private List<Transaksi> listT = new ArrayList<Transaksi>();
    private Permohonan permohonan;
    private Lelongan lelong;
    private Pembida pembida;
    private Pihak pihak;
    private Enkuiri enkuiri;
    private Enkuiri enkuiri1;
    private FasaPermohonan fasaPermohonan;
    private boolean view = false;
    private boolean showForm;
    private List<Lelongan> listLelongHakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    PihakService pihakService;
    @Inject
    private WakilPihakDAO wakilPihakDAO;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("default handler");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    //added by nur.aqilah
    //search id mohon by willcard
    public Resolution checkPermohonan() {
        LOG.info("rehydrate - start");
        LOG.info("idPermohonan : " + idPermohonan);

        ParamEncoder encoder = new ParamEncoder("line");
        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

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

                permohonan = getPermohonanDAO().findById(idPermohonan);

                if (permohonan == null) {
                    setShowForm(true);
                } else {
                    setShowForm(false);
                }

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
                    }
                } else {

                    idPermohonan = getContext().getRequest().getParameter("idPermohonan");

                    if (StringUtils.isNotBlank(idPermohonan)) {
                        permohonan = getPermohonanDAO().findById(idPermohonan);

                        if (permohonan != null) {
                            LOG.debug("MASUK ID !=NULL");
                            LOG.info("IdPermohonan :" + permohonan.getIdPermohonan());
                            getsPembida();


                        } else {
                            LOG.debug("MASUK ID ==NULL");
                            addSimpleError("Id Permohonan tidak wujud");
                            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
                        }
                    }
                    return new JSP("/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");

                }
            }


        }

        LOG.info("Rehydrate - finish");
        return new JSP("/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    public Resolution deletePembida() {
        String results = "0";
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        idPembida = getContext().getRequest().getParameter("idPembida");

        permohonan = permohonanDAO.findById(idPermohonan);
        pembida = pembidaDAO.findById(Long.parseLong(idPembida));
        if (pembida.getWakilPihak() != null) {
            WakilPihak wk = pembida.getWakilPihak();
            lelongService.deleteWakilPihak(wk);
        }
        lelongService.deletePihak(pembida);

        return checkPermohonan();
//        return new StreamingResolution("text/plain", results);

    }

    public Resolution simpanWakil() {
        LOG.info("--------ni untuk kes lelong sama-------");

        String results = "0";
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();
        String nama = getContext().getRequest().getParameter("nama");
        String jeniskp = getContext().getRequest().getParameter("jeniskp");
        String nokp = getContext().getRequest().getParameter("nokp");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String kodWarganegara = getContext().getRequest().getParameter("kodWarganegara");
        String namaLama = getContext().getRequest().getParameter("namaLama");
        String jeniskpLama = getContext().getRequest().getParameter("jeniskpLama");
        String nokpLama = getContext().getRequest().getParameter("nokpLama");
        String kodWarganegaraLama = getContext().getRequest().getParameter("kodWarganegaraLama");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPembida = getContext().getRequest().getParameter("idPembida");
        String noBank = getContext().getRequest().getParameter("noBank");
        String noHP = getContext().getRequest().getParameter("noHP");
//        LOG.info("simpanWakil " + idWakil);
        getContext().getRequest().getParameter(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        pembida = pembidaDAO.findById(Long.parseLong(idPembida));

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(jeniskp);

        if (pembida.getWakilPihak() == null) {
            Pihak pihakWakil = new Pihak();
            pihakWakil.setNama(nama);
            pihakWakil.setJenisPengenalan(kj);
            pihakWakil.setNoPengenalan(nokp);
            pihakWakil.setInfoAudit(ia);
            pihakWakil.setNoTelefonBimbit(noHP);
            pihakService.saveOrUpdatePihak(pihakWakil);

            WakilPihak wakilP = new WakilPihak();
            wakilP.setNama(nama);
            wakilP.setNoPengenalan(nokp);
            wakilP.setJenisPengenalan(kj);
            wakilP.setPermohonan(permohonan);
            wakilP.setPihak(pihakWakil);
            wakilP.setInfoAudit(ia);
            wakilPihakDAO.saveOrUpdate(wakilP);
            pembida.setWakilPihak(wakilP);
            lelongService.updatePembida(pembida);
        } else {
            Pihak pihakWakil = pembida.getWakilPihak().getPihak();
            pihakWakil.setNama(nama);
            pihakWakil.setJenisPengenalan(kj);
            pihakWakil.setNoPengenalan(nokp);
            pihakWakil.setInfoAudit(ia);
            pihakWakil.setNoTelefonBimbit(noHP);
            pihakService.saveOrUpdatePihak(pihakWakil);

            WakilPihak wakilPLama = pembida.getWakilPihak();
            wakilPLama.setNama(nama);
            wakilPLama.setNoPengenalan(nokp);
            wakilPLama.setJenisPengenalan(kj);
            wakilPLama.setPermohonan(permohonan);
            wakilPLama.setPihak(pembida.getWakilPihak().getPihak());
            wakilPLama.setInfoAudit(ia);
            wakilPihakDAO.saveOrUpdate(wakilPLama);
            pembida.setWakilPihak(wakilPLama);
            lelongService.updatePembida(pembida);
        }

        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    public Resolution saveTukar() {

        String results = "0";

        String nama = getContext().getRequest().getParameter("nama");
        String jeniskp = getContext().getRequest().getParameter("jeniskp");
        String nokp = getContext().getRequest().getParameter("nokp");
        String jenisPB = getContext().getRequest().getParameter("jenisPB");
        String kodWarganegara = getContext().getRequest().getParameter("kodWarganegara");
        String namaLama = getContext().getRequest().getParameter("namaLama");
        String jeniskpLama = getContext().getRequest().getParameter("jeniskpLama");
        String nokpLama = getContext().getRequest().getParameter("nokpLama");
        String kodWarganegaraLama = getContext().getRequest().getParameter("kodWarganegaraLama");
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPembida = getContext().getRequest().getParameter("idPembida");
        String noBank = getContext().getRequest().getParameter("noBank");
        String noHP = getContext().getRequest().getParameter("noHP");
//        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        if (idPembida != null) {
            Pembida kemaskiniPembida = pembidaDAO.findById(Long.parseLong(idPembida));
            KodJenisPengenalan kj = kodJenisPengenalanDAO.findById(jeniskp);
            if (kemaskiniPembida != null) {
                Pihak pihakSave = kemaskiniPembida.getPihak();
                if (!nama.equals(null) || !nama.equals("") || nama.isEmpty() || nama.equalsIgnoreCase("") || nama == null) {
                    pihakSave.setNama(nama);
                } else {
                    pihakSave.setNama(pihakSave.getNama());
                }
                if (!noHP.equals(null) || !noHP.equals("") || noHP.isEmpty() || noHP.equalsIgnoreCase("") || noHP == null) {
                    pihakSave.setNoTelefonBimbit(noHP);
                } else {
                    pihakSave.setNoTelefonBimbit(pihakSave.getNoTelefonBimbit());
                }
                if (!nokp.equals(null) || !nokp.equals("") || nokp.isEmpty() || nokp.equalsIgnoreCase("") || nokp == null) {
                    pihakSave.setNoPengenalan(nokp);
                } else {
                    pihakSave.setNoPengenalan(pihakSave.getNoPengenalan());
                }
                if (kj != null) {
                    pihakSave.setJenisPengenalan(kj);
                } else {
                    pihakSave.setJenisPengenalan(pihakSave.getJenisPengenalan());
                }
                if (!noBank.equals(null) || !noBank.equals("") || noBank.isEmpty() || noBank.equalsIgnoreCase("") || noBank == null) {
                    kemaskiniPembida.setNoRujukan(noBank);
                } else {
                    kemaskiniPembida.setNoRujukan(kemaskiniPembida.getNoRujukan());
                }

                pihakService.saveOrUpdatePihak(pihakSave);
                pembidaDAO.saveOrUpdate(kemaskiniPembida);
                idPermohonan = kemaskiniPembida.getHakmilikPermohonan().getPermohonan().getIdPermohonan();
            }
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/lelong/tambahPembidaBaruJLB.jsp").addParameter("popup", "true");
    }

    public Resolution kemaskiniPembida() throws WorkflowException {
        LOG.info("nk tambah pembida laaa");

        String idPembida = getContext().getRequest().getParameter("idPembida");
        if (idPermohonan == null) {
            idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        }
        LOG.info("idPermohonan = " + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (idPembida != null) {
            pembida = pembidaDAO.findById(Long.parseLong(idPembida));
        }
        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/kemaskini_pembidaJLB.jsp").addParameter("popup", "true");
    }

    public Resolution tambahWakil() {

        LOG.info("nk tambah pembida laaa");

        getContext().getRequest().getParameter(idPermohonan);
        getContext().getRequest().getParameter(idPembida);
        pembida = pembidaDAO.findById(Long.parseLong(idPembida));

//        if (wakilPembidaString.equals("wakilPembida")){
//            wakilPembida = true;
//        }else {
//            wakilPembida = false;
//        }

        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri1 = lelongService.findEnkuiri(idPermohonan);
        } else {
            enkuiri1 = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        if (enkuiri1.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
        }
        LOG.info("cara lelong :" + enkuiri1.getCaraLelong());

        if (enkuiri1.getCaraLelong().equals("B")) {
            getContext().getRequest().getParameter(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);

            listLel1 = lelongService.getLeloganALLDESC2(idPermohonan);
            for (Lelongan lelo : listLel1) {
                lelong = lelo;
                Long idLelong1 = lelo.getIdLelong();
                LOG.info("idLelong :" + idLelong1);
                list.addAll(enkuiriService.getListPembida(idLelong1));
                LOG.info("senaraiPembida.size :" + list.size());

                idHakmilik = lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                enkuiri = lelo.getEnkuiri();
                LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("idhakmilik :" + lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                LOG.info("cara lelong " + enkuiri.getCaraLelong());

                for (Pembida pembidalaa : list) {
                    if (pembidalaa != null) {
                        LOG.info("id Pihak: " + pembidalaa.getPihak().getIdPihak());
                        LOG.info("Nama pihak: " + pembidalaa.getPihak().getNama());
                        LOG.info("no Ic: " + pembidalaa.getPihak().getNoPengenalan());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("   ");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHakmilik = sb.toString();

        } else if (enkuiri1.getCaraLelong().equals("A")) {
            listLelongHakmilik = enkuiriService.getLeloganbyMohonA(idPermohonan);
        }
        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/tambahWakilPembidaJLB.jsp").addParameter("popup", "true");
//        return new ForwardResolution("/WEB-INF/jsp/lelong/kemaskini_pembidaJLB.jsp").addParameter("popup", "true");
    }

    //added by nur.aqilah
    //search id mohon
    public Resolution search() {

        System.out.print("checkPermohonan");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        try {
            permohonan = permohonanDAO.findById(idPermohonan);
        } catch (Exception e) {

            LOG.error("Error message " + e);
        }


        if (permohonan == null) {
            LOG.info("id Permohonan tidak wujud");

            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            view = false;

            return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");

        } else {
            LOG.info("id Permohonan wujud");

            getsPembida();
        }

        return new JSP("/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    //editted by nur.aqilah
    //find pembida
    public Resolution getsPembida() {
        LOG.info("--list pembida--");
        getContext().getRequest().getParameter(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        //check id mohon ada tak id mohon sebelum
        setCheckIdMohonSebelum(lelongService.checkIdMohonSebelum(idPermohonan));
        LOG.info("ada ke tak id mohon sebelum " + getCheckIdMohonSebelum().size());

        //ada id mohon sebelum
        if (!checkIdMohonSebelum.isEmpty()) {
            LOG.info("Ada Id Mohon Sebelum");
            setIdMohonSebelum(getCheckIdMohonSebelum().get(0).getPermohonanSebelum().getIdPermohonan());
            LOG.info("id mohon sebelum " + getIdMohonSebelum());

            //added by nur.aqilah
            //check status bayaran perintah (MELAKA ONLY) 
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                LOG.info("Melaka Sahaja check status bayaran perintah");

                checkBayaranPerintah = lelongService.checkBayarPerintah(idMohonSebelum);
                if (checkBayaranPerintah.isEmpty()) {
                    LOG.info("Bayaran Pelbagai Belum di Jelaskan");
                    addSimpleError("Sila Jelaskan Bayaran Perintah RM80 Dikaunter SPOC Terlebih Dahulu Untuk Id Mohon Terdahulu " + idMohonSebelum);
                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
                }
            }
            List<Task> l;
            try {
                l = WorkFlowService.queryTasksByAdmin(getIdMohonSebelum());

                for (Task t : l) {
                    setStage(t.getSystemAttributes().getStage());
                    setParticipant(t.getSystemAttributes().getAcquiredBy());
                }
            } catch (WorkflowException ex) {
                LOG.error(ex);
            }

            listLel1 = lelongService.getLelonganForFindPembida(getIdMohonSebelum());
            LOG.info("size listLel1: " + listLel1.size());

            boolean looping = false;
            StringBuilder sb = new StringBuilder();
            String bidaic = "";
            for (Lelongan lelo : listLel1) {
                LOG.info("MASUK LIST 1");
                lelong = lelo;
                Long idLelong1 = lelo.getIdLelong();
                LOG.info("idLelong :" + idLelong1);

                idHakmilik = lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                enkuiri = lelo.getEnkuiri();
                LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("idhakmilik :" + lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                LOG.info("cara lelong " + enkuiri.getCaraLelong());

                if (enkuiri.getCaraLelong().equals("A")) {
                    list.addAll(enkuiriService.getListPembida(idLelong1));
                    LOG.info("size pembida lelongan A " + list.size());

                } else {
                    List<Pembida> listbida = enkuiriService.getListPembida(idLelong1);
                    for (Pembida pembidalaa : listbida) {
                        LOG.info("getNoPengenalan " + pembidalaa.getPihak().getNoPengenalan() + " , bidaic =" + bidaic);
                        Boolean loopbida = false;
                        for (Pembida lt : list) {
                            if (pembidalaa.getPihak().getNoPengenalan().equals(lt.getPihak().getNoPengenalan())) {
                                loopbida = true;
                                break;
                            }
                        }
                        if (!loopbida) {
                            list.add(pembidalaa);
                            LOG.info("size pembida lelongan B " + list.size());
                        }
                    }
                }

                if (enkuiri.getCaraLelong().equals("B")) {
                    if (sb.length() > 0) {
                        sb.append(" <br> ");
                    }
                    sb.append(lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                }

                for (Pembida pembidalaa : list) {
                    Date now = new Date();
                    if (pembidalaa != null) {
                        LOG.info("tarikh Akhir: " + pembidalaa.getTarikhAkhir());
                        if (pembidalaa.getTarikhAkhir() != null) {
                            LOG.info("---------------Tarikh Akhir not null--------");
                            if (now.after(pembidalaa.getTarikhAkhir())) {
                                if (pembidalaa.getKodStsPembida().getKod().equals("SH")) {
                                    LOG.info("---------------ni SH--------");
                                    pembidalaa.setKodStsPembida(kodStsPembidaDAO.findById("TB"));
                                    LOG.info("--------ni TB------------");
                                    enkuiriService.saveOrUpdate(pembidalaa);
                                }
                            }
                        }

                    }
                }
            }

            idHakmilik = sb.toString();

            //tak ada id mohon sebelum
        } else {
            LOG.info("Tak Ada Id Mohon Sebelum");
            //check status bayaran perintah        

            //added by nur.aqilah
            //check status bayaran perintah (MELAKA ONLY)  
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                LOG.info("Melaka Sahaja check status bayaran perintah");
                checkBayaranPerintah = lelongService.checkBayarPerintah(permohonan.getIdPermohonan());
                if (checkBayaranPerintah.isEmpty()) {
                    LOG.info("Bayaran Pelbagai Belum di Jelaskan");
                    addSimpleError("Sila Jelaskan Bayaran Perintah RM80 Dikaunter SPOC Terlebih Dahulu");
                    return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
                }
            }
            List<Task> l;
            try {
                l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

                for (Task t : l) {
                    setStage(t.getSystemAttributes().getStage());
                    setParticipant(t.getSystemAttributes().getAcquiredBy());
                }
            } catch (WorkflowException ex) {
                LOG.error(ex);
            }

            listLel1 = lelongService.getLelonganForFindPembida(idPermohonan);
            LOG.info("size listLel1: " + listLel1.size());

            boolean looping = false;
            StringBuilder sb = new StringBuilder();
            String bidaic = "";
            for (Lelongan lelo : listLel1) {
                LOG.info("MASUK LIST 1");
                lelong = lelo;
                Long idLelong1 = lelo.getIdLelong();
                LOG.info("idLelong :" + idLelong1);

                idHakmilik = lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                enkuiri = lelo.getEnkuiri();
                LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("idhakmilik :" + lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                LOG.info("cara lelong " + enkuiri.getCaraLelong());

                if (enkuiri.getCaraLelong().equals("A")) {
                    list.addAll(enkuiriService.getListPembida(idLelong1));
                    LOG.info("size pembida lelongan A " + list.size());

                } else {
                    List<Pembida> listbida = enkuiriService.getListPembida(idLelong1);
                    for (Pembida pembidalaa : listbida) {
                        LOG.info("getNoPengenalan " + pembidalaa.getPihak().getNoPengenalan() + " , bidaic =" + bidaic);
                        Boolean loopbida = false;
                        for (Pembida lt : list) {
                            if (pembidalaa.getPihak().getNoPengenalan() != null) {
                                if (pembidalaa.getPihak().getNoPengenalan().equals(lt.getPihak().getNoPengenalan())) {
                                    loopbida = true;
                                    break;
                                }
                            }
                        }
                        if (!loopbida) {
                            list.add(pembidalaa);
                            LOG.info("size pembida lelongan B " + list.size());
                        }
                    }
                }

                if (enkuiri.getCaraLelong().equals("B")) {
                    if (sb.length() > 0) {
                        sb.append(" <br> ");
                    }
                    sb.append(lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                }

                for (Pembida pembidalaa : list) {
                    Date now = new Date();
                    if (pembidalaa != null) {
                        LOG.info("tarikh Akhir: " + pembidalaa.getTarikhAkhir());
                        if (pembidalaa.getTarikhAkhir() != null) {
                            LOG.info("---------------Tarikh Akhir not null--------");
                            if (now.after(pembidalaa.getTarikhAkhir())) {
                                if (pembidalaa.getKodStsPembida().getKod().equals("SH")) {
                                    LOG.info("---------------ni SH--------");
                                    pembidalaa.setKodStsPembida(kodStsPembidaDAO.findById("TB"));
                                    LOG.info("--------ni TB------------");
                                    enkuiriService.saveOrUpdate(pembidalaa);
                                }
                            }
                        }

                    }
                }
            }

            idHakmilik = sb.toString();
        }

        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    //upload popup
    public Resolution popupUpload() {
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        LOG.info("idPembida :" + idPembida);
        return new JSP("lelong/upload_dok_pembida_jlb.jsp").addParameter("popup", "true");
    }

    //upload dokumen
    public Resolution processUploadDoc() {
        LOG.info("---muat naik---");
        LOG.info("fileToBeUploaded : " + fileToBeUploaded);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("KP"); // KP Salinan Kad Pengenalan/Pasport
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idPembida;
        LOG.info("idPembida : " + fname);
        DateUtil du = new DateUtil();
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
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

                Pembida pembida = pembidaDAO.findById(Long.parseLong(fname));
                if (pembida.getDokumen() != null) {
                    doc = pembida.getDokumen();
                    ia = pembida.getDokumen().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Kad Pengenalan/Pasport-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                LOG.info("idDoc :" + idDoc);

                ia = pembida.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                pembida.setDokumen(dokumenDAO.findById(idDoc));
                pembida.setInfoAudit(ia);
                lelongService.updatePembida(pembida);
                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                LOG.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }

        return new JSP("lelong/upload_dok_pembida_jlb.jsp").addParameter("popup", "true");
    }

    //editted by nur.aqilah
    //upload dokumen (Lelongan Bersama)
    public Resolution processUploadDocB() {
        LOG.info("---muat naik untuk lelongan bersama---");
        LOG.info("fileToBeUploaded : " + fileToBeUploaded);
        getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan" + idPermohonan);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("KP"); // KP Salinan Kad Pengenalan/Pasport
        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = idPembida;
        String result = "0";

        LOG.info("idPembida : " + fname);
        //find id pihak
        pembida = lelongService.getPembidaByIdPembida(Long.parseLong(idPembida));
        LOG.info("pembida " + pembida);
        pihak = pihakDAO.findById(pembida.getPihak().getIdPihak());
        LOG.info("id pihak : " + pihak.getIdPihak());

        //find pembida yg ada id pihak yg sama 
        //lelongan bersama

        List<Pembida> pembidaByIdPihak = lelongService.getListPembidaByIdPihak(pihak.getIdPihak());
        LOG.info("Size list pembida by id pihak : " + pihak.getIdPihak() + "= " + pembidaByIdPihak.size());

        for (Pembida listpembida : pembidaByIdPihak) {

            DateUtil du = new DateUtil();
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
            Pengguna p = ctx.getUser();
            try {
                if (p != null && listpembida.getIdPembida() != null && fileToBeUploaded != null) {
                    String kodCawangan = p.getKodCawangan().getKod();
                    DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                            + du.getDateName(String.valueOf(du.getMonth() + 1))
                            + File.separator + du.getDay();
                    DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                    LOG.debug("DIR to created = " + DMS_PATH);
                    FileUtil fileUtil = new FileUtil(DMS_PATH);
                    String namaFail = fileUtil.saveFile(String.valueOf(listpembida.getIdPembida()), fileToBeUploaded.getInputStream());
                    LOG.info("namaFail :" + namaFail);
                    Dokumen doc = new Dokumen();

                    Pembida pembida = pembidaDAO.findById(listpembida.getIdPembida());
                    if (pembida.getDokumen() != null) {
                        doc = pembida.getDokumen();
                        ia = pembida.getDokumen().getInfoAudit();
                        ia.setDiKemaskiniOleh(p);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        ia.setDimasukOleh(p);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + String.valueOf(listpembida.getIdPembida());
                    doc.setKodDokumen(kd);
                    doc.setTajuk("Salinan Kad Pengenalan/Pasport-" + String.valueOf(listpembida.getIdPembida()));
                    doc.setNoVersi("1.0");
                    doc.setKlasifikasi(kk);
                    doc.setFormat(fileToBeUploaded.getContentType());
                    doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                    doc.setInfoAudit(ia);
                    Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                    LOG.info("idDoc :" + idDoc);

                    ia = pembida.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    pembida.setDokumen(dokumenDAO.findById(idDoc));
                    pembida.setInfoAudit(ia);
                    lelongService.updatePembida(pembida);
                    result = "1";
                } else {
                    LOG.error("parameter tidak mencukupi.");
                    result = "2";
                }
            } catch (Exception ex) {
                LOG.error("Fatal protocol violation: " + ex.getMessage());
                result = "3";

            }
        }

        if (result.equals("1")) {
            addSimpleMessage("Muat naik fail berjaya.");
        } else if (result.equals("2")) {
            addSimpleError("Muat naik fail tidak berjaya.");
        } else if (result.equals("3")) {
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("lelong/upload_dok_pembida_jlb.jsp").addParameter("popup", "true");
    }

    //scan dokumen
    public Resolution popupScan() {
        LOG.info("idPembida :" + idPembida);
        String id = getContext().getRequest().getParameter("id");
        getContext().getRequest().setAttribute("id", id);
        KodKlasifikasi kk = kodKlasDAO.findById(1); //for tahap sekuriti = umum
        KodDokumen kd = kodDokumenDAO.findById("KP"); // KP Salinan Kad Pengenalan/Pasport
        InfoAudit ia = new InfoAudit();
        String fname = idPembida;
        LOG.info("idPembida : " + fname);
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                Pembida pembida = pembidaDAO.findById(Long.parseLong(fname));
                if (pembida.getDokumen() != null) {
                    doc = pembida.getDokumen();
                    ia = pembida.getDokumen().getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                doc.setKodDokumen(kd);
                doc.setTajuk("Salinan Kad Pengenalan/Pasport-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kk);
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                setIdDokumen(lelongService.simpanOrUpdateDokumen(doc));
                LOG.info("idDoc :" + getIdDokumen());
                // update at DasarTuntutanNotis
                ia = pembida.getInfoAudit();
                ia.setDiKemaskiniOleh(p);
                ia.setTarikhKemaskini(new java.util.Date());
                pembida.setDokumen(dokumenDAO.findById(getIdDokumen()));
                pembida.setInfoAudit(ia);
                lelongService.updatePembida(pembida);
            } else {
                LOG.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            LOG.error("Fatal protocol violation: " + ex.getMessage());
        }
        return new JSP("lelong/scan_dok_pembida_jlb.jsp").addParameter("popup", "true");
    }

    //view dokumen
    public Resolution view() {

        LOG.info("idPembida : " + idPembida);

        List<Pembida> listPembida = lelongService.getListPembida(Long.parseLong(idPembida));
        LOG.info("listPembida : " + listPembida.size());
        if (listPembida.get(0).getDokumen() != null) {
            idDokumen = listPembida.get(0).getDokumen().getIdDokumen();
        }
        LOG.info("idDokumen : " + idDokumen);
        if (idDokumen == 0) {
            return new ErrorResolution(500, "Dokumen tidak dinyatakan");
        }

        Dokumen d = dokumenDAO.findById(idDokumen);
        if (d == null || d.getKodDokumen() == null) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null
                && p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
            return new ErrorResolution(401, "Pengguna tidak boleh mencapai dokumen ini.");
        }

        String docPath = conf.getProperty("document.path");
        if (docPath == null) {
            return new ErrorResolution(500,
                    "Konfigurasi \"document.path\" tidak dijumpai");
        }
        // log the view
        DokumenCapaian dc = new DokumenCapaian();
        dc.setDokumen(d);
        dc.setAktiviti(kodStatusDokumenCapaiDAO.findById("PD"));
        dc.setAlasan("Paparan Dokumen");
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        if (d.getBaru() == 'Y') {
            ia = d.getInfoAudit();
            ia.setTarikhKemaskini(new Date());
            ia.setDiKemaskiniOleh(getPengguna());
            d.setInfoAudit(ia);
            d.setBaru('T');
            dokumenDAO.update(d);
        }
        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            if (f != null && d.getKodDokumen().getKawalCapaian() == 'Y') {
                if (isIsDebug()) {
                    LOG.debug("creating watermark..");
                }
                boolean createWatermark = true;

                if (d.getKodDokumen().getKod().equalsIgnoreCase("DHKE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("DHDE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB1DE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2KE")
                        || d.getKodDokumen().getKod().equalsIgnoreCase("PB2DE")) {
                    File signFile = new File(path + ".sig");
                    if (signFile.exists()) {
                        createWatermark = false;
                    }
                }

                if (createWatermark) {
                    final InputStream is = fis;
                    final String format = d.getFormat();

                    ByteArrayOutputStream bous = FileUtil.createWaterMark(is);

                    InputStream inputStream = new ByteArrayInputStream(bous.toByteArray());

                    return new StreamingResolution(d.getFormat(), inputStream);
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }
        getContext().getResponse().setContentType("application/octet-stream");
        return new StreamingResolution(d.getFormat(), fis);
    }

    //tambah pembida
    public Resolution tambahPembida() {
        LOG.info("nk tambah pembida laaa");

        getContext().getRequest().getParameter(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri1 = lelongService.findEnkuiri(idPermohonan);
        } else {
            enkuiri1 = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        if (enkuiri1.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
        }
        LOG.info("cara lelong :" + enkuiri1.getCaraLelong());


        if (enkuiri1.getCaraLelong().equals("B")) {
            getContext().getRequest().getParameter(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);

            listLel1 = lelongService.getLeloganALLDESC2(idPermohonan);
            for (Lelongan lelo : listLel1) {
                lelong = lelo;
                Long idLelong1 = lelo.getIdLelong();
                LOG.info("idLelong :" + idLelong1);
                list.addAll(enkuiriService.getListPembida(idLelong1));
                LOG.info("senaraiPembida.size :" + list.size());

                idHakmilik = lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
                enkuiri = lelo.getEnkuiri();
                LOG.info("id enkuiri: " + lelo.getEnkuiri().getIdEnkuiri());
                LOG.info("idhakmilik :" + lelo.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
                LOG.info("cara lelong " + enkuiri.getCaraLelong());


                for (Pembida pembidalaa : list) {
                    if (pembidalaa != null) {
                        LOG.info("id Pihak: " + pembidalaa.getPihak().getIdPihak());
                        LOG.info("Nama pihak: " + pembidalaa.getPihak().getNama());
                        LOG.info("no Ic: " + pembidalaa.getPihak().getNoPengenalan());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            if (permohonan != null) {
                for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                    Hakmilik h = hp.getHakmilik();
                    if (sb.length() > 0) {
                        sb.append("   ");
                    }
                    sb.append(h.getIdHakmilik());
                }
            }
            idHakmilik = sb.toString();

        } else {
            if (enkuiri1.getCaraLelong().equals("A")) {
                listLelongHakmilik = enkuiriService.getLeloganbyMohonA(idPermohonan);
            }
        }
        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/tambahPembidaBaruJLB.jsp").addParameter("popup", "true");
    }

    //editted by nur.aqilah
    // check duplicate ic
    public Resolution doCheckIC() {
        LOG.info("------check duplicate ic---------");

        String noPengenalan = getContext().getRequest().getParameter("icno");
        LOG.info("*****AjaxValidateIC.doCheckIC :" + getContext().getRequest().getParameter("icno"));
        String results = "0";

        String idPermohonan1 = getContext().getRequest().getParameter("idPermohonan");
        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        LOG.info("idPermohonan : " + idPermohonan1);

        permohonan = permohonanDAO.findById(idPermohonan1);
        //check senarai hitam

        List<Pembida> CheckTarikhSenaraiHitam = lelongService.checkPembida(noPengenalan);
        LOG.info("Ada Tak Senarai Hitam Yg Dh Tamat Tempoh " + CheckTarikhSenaraiHitam.size());

        if (!CheckTarikhSenaraiHitam.isEmpty()) {
            LOG.info("Tarikh Senarai Hitam Telah Tamat");
            for (Pembida checkSenaraiHitam : CheckTarikhSenaraiHitam) {
                LOG.info("id pihak: " + checkSenaraiHitam.getPihak().getIdPihak());
                LOG.info("no ic: " + checkSenaraiHitam.getPihak().getNoPengenalan());

                //update pembida status dari SH ke TB
                pembida = pembidaDAO.findById(checkSenaraiHitam.getIdPembida());
                KodStsPembida kodStsPembida = kodStsPembidaDAO.findById("TB");
                LOG.info(kodStsPembida);
                pembida.setKodStsPembida(kodStsPembida);
                lelongService.updatePembida(pembida);
            }
        } else {
            LOG.info("Masih Dalam Tempoh Senarai Hitam");
            List<Pembida> GetAllPembidaByIC = lelongService.getListPihakIC(noPengenalan);
            LOG.info("GetAllPembidaByIC " + GetAllPembidaByIC.size());

            for (Pembida checkSenaraiHitam : GetAllPembidaByIC) {
                if (GetAllPembidaByIC != null) {

                    LOG.info("id pihak: " + checkSenaraiHitam.getPihak().getIdPihak());
                    LOG.info("no ic: " + checkSenaraiHitam.getPihak().getNoPengenalan());
                    results = "1";

                }
            }
        }

        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    //simpan pembida (Lelongan Berasingan)
    public Resolution simpanTambahPembida() {
        LOG.info("--------ni untuk kes lelong asingg-------");
        String results = "0";
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        listLelongHakmilik = enkuiriService.getLeloganbyMohonA(idPermohonan);

        LOG.info("tambah pembida baru");

        getContext().getRequest().getParameter(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        String idMohon = getContext().getRequest().getParameter("idMohon");
        getContext().getRequest().setAttribute("idMohon", idMohon);
        LOG.info("idMohon : " + idMohon);

        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri1 = lelongService.findEnkuiri(idPermohonan);
        } else {
            enkuiri1 = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        if (enkuiri1.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
        }

        LOG.info("cara lelong :" + enkuiri1.getCaraLelong());
        noPengenalan = getContext().getRequest().getParameter("pihak.noPengenalan");
        LOG.info("no pengenalan :" + noPengenalan);

        Lelongan lelog = enkuiriService.findLelong(idPermohonan, idHakmilik);
        idHakmilik = lelog.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
        LOG.info("id Hakmilik :" + lelog.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
        hakmilikPermohonan = lelog.getHakmilikPermohonan();

        LOG.info("tambah dalam table pihak");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        pihak.setInfoAudit(ia);
        KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
        pihak.setJenisPengenalan(jenis);
        pihak.setNoPengenalan(noPengenalan);

        LOG.info("tambah dalam table pembida");

        InfoAudit info = new InfoAudit();
        Pembida pem = new Pembida();
        info.setTarikhMasuk(new java.util.Date());
        info.setDimasukOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        info.setDiKemaskiniOleh(pengguna);
        pem.setInfoAudit(info);
        pem.setHakmilikPermohonan(hakmilikPermohonan);
        pem.setLelong(lelog);
        pem.setPihak(pihak);
        pem.setNoRujukan(getContext().getRequest().getParameter("pembida.noRujukan"));
        KodStsPembida kodStatus = new KodStsPembida();
        kodStatus.setKod("TB");
        pem.setKodStsPembida(kodStatus);
        enkuiriService.savePihakPembida(pihak, pem);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        results = "1";

        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    //simpan pembida (Lelongan Bersama)
    public Resolution simpanTambahPembidaBersama() {
        LOG.info("--------ni untuk kes lelong sama-------");

        String results = "0";
        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        Pengguna pengguna = ctx.getUser();

        listLelongHakmilik = enkuiriService.getLeloganbyMohonA(idPermohonan);

        LOG.info("tambah pembida baru");

        getContext().getRequest().getParameter(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        String idMohon = getContext().getRequest().getParameter("idMohon");
        getContext().getRequest().setAttribute("idMohon", idMohon);
        LOG.info("idMohon : " + idMohon);

        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri1 = lelongService.findEnkuiri(idPermohonan);
        } else {
            enkuiri1 = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        if (enkuiri1.getCaraLelong().equals("A")) {
            getContext().getRequest().setAttribute("same", Boolean.FALSE);
        } else {
            getContext().getRequest().setAttribute("same", Boolean.TRUE);
        }

        LOG.info("cara lelong :" + enkuiri1.getCaraLelong());
        noPengenalan = getContext().getRequest().getParameter("pihak.noPengenalan");
        LOG.info("no pengenalan :" + noPengenalan);

        listLel2 = lelongService.getLeloganALLDESC2(idPermohonan);
        for (Lelongan lelog : listLel2) {
            lelong = lelog;
            idHakmilik = lelog.getHakmilikPermohonan().getHakmilik().getIdHakmilik();
            LOG.info("id Hakmilik :" + lelog.getHakmilikPermohonan().getHakmilik().getIdHakmilik());
            hakmilikPermohonan = lelog.getHakmilikPermohonan();
            LOG.info("id lelong " + lelog.getIdLelong());

            LOG.info("tambah dalam table pihak");

            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            pihak.setInfoAudit(ia);
            KodJenisPengenalan jenis = kodJenisPengenalanDAO.findById(kodJenis);
            pihak.setJenisPengenalan(jenis);
            pihak.setNoPengenalan(noPengenalan);

            LOG.info("tambah dalam table pembida");

            InfoAudit info = new InfoAudit();
            Pembida pem = new Pembida();
            info.setTarikhMasuk(new java.util.Date());
            info.setDimasukOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            info.setDiKemaskiniOleh(pengguna);
            pem.setInfoAudit(info);
            pem.setHakmilikPermohonan(hakmilikPermohonan);
            pem.setLelong(lelog);
            pem.setPihak(pihak);
            pem.setNoRujukan(getContext().getRequest().getParameter("pembida.noRujukan"));
            KodStsPembida kodStatus = new KodStsPembida();
            kodStatus.setKod("TB");
            pem.setKodStsPembida(kodStatus);
            enkuiriService.savePihakPembida(pihak, pem);
            results = "1";

        }

        LOG.debug("results : " + results);
        return new StreamingResolution("text/plain", results);
    }

    //refresh
    public Resolution refresh() {

        LOG.info("--------ni untuk REFRESHHHH-------");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan : " + idPermohonan);

        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            listLel1 = lelongService.getLeloganALLDESC2(idPermohonan);

            getsPembida();
        }


        view = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    //edit status pembida popup
    public Resolution tambahStatus() {
        LOG.info("edit status dlm pembida");

        String idPihak = getContext().getRequest().getParameter("nama");
        LOG.info(idPihak);
        pihak = lelongService.getPihakID(idPihak);

        String idPembidas = getContext().getRequest().getParameter("idPembida");
        LOG.info(idPembidas);
        pembida = enkuiriService.getPembidaID(idPembidas);

        String idLelong1 = getContext().getRequest().getParameter("idLelong");
        LOG.info(idLelong1);

        return new JSP("lelong/editPembidaBaru1.jsp").addParameter("popup", "true");
    }

    //simpan edit status pembida
    public Resolution simpanEditPembida() {
        LOG.info("-------simpan edit dlm status pembida-------");

        permohonan = permohonanDAO.findById(idPermohonan);
        getContext().getRequest().getParameter(idPermohonan);
        Enkuiri enkuiri1 = lelongService.findEnkuiri(idPermohonan);


        if (enkuiri1.getCaraLelong().equals("A")) {
            Pembida pem = pembidaDAO.findById(Long.valueOf(getContext().getRequest().getParameter("idPembida")));
            KodStsPembida kodstspembida = kodStsPembidaDAO.findById(getContext().getRequest().getParameter("kodStatus"));
            pem.setKodStsPembida(kodstspembida);
            enkuiriService.saveOrUpdate(pem);

            idLelong = getContext().getRequest().getParameter("idLelong");
            LOG.info("------idLelong-----" + idLelong);
            Lelongan lelong2 = enkuiriService.getLelongJeee(Long.parseLong(idLelong));

            if (pem.getKodStsPembida().getKod().equals("BJ")) {
                LOG.info("------BJ-----");
                pihak = pem.getPihak();
                LOG.info("idPihak " + pem.getPihak());
                enkuiriService.simpanLelong(lelong2);

                permohonan = lelong2.getPermohonan();
                idPermohonan = lelong2.getPermohonan().getIdPermohonan();
                LOG.info("idmohon : " + lelong2.getPermohonan().getIdPermohonan());

                List<FasaPermohonan> stage1 = lelongService.findFasa(idPermohonan);
                for (int a = 0; a < stage1.size(); a++) {
                    FasaPermohonan fasa = stage1.get(a);
                    if ("rekodBidaan".equals(fasa.getIdAliran())) {
                        KodKeputusan kod = kodKeputusanDAO.findById("AP");
                        fasa.setKeputusan(kod);
                        enkuiriService.saveFasaMohon(fasa);
                    }
                }
            }
        } else {

            list2 = enkuiriService.getListPembidaPihak(Long.valueOf(getContext().getRequest().getParameter("idPihak")));
            for (Pembida bida : list2) {
                KodStsPembida kodstspembida = kodStsPembidaDAO.findById(getContext().getRequest().getParameter("kodStatus"));
                bida.setKodStsPembida(kodstspembida);
                enkuiriService.saveOrUpdate(bida);

                idLelong = getContext().getRequest().getParameter("idLelong");
                LOG.info("------idLelong-----" + idLelong);
                Lelongan lelong2 = enkuiriService.getLelongJeee(Long.parseLong(idLelong));

                if (bida.getKodStsPembida().getKod().equals("BJ")) {
                    LOG.info("------BJ-----");
                    pihak = bida.getPihak();
                    LOG.info("idPihak " + bida.getPihak());
                    enkuiriService.simpanLelong(lelong2);

                    permohonan = lelong2.getPermohonan();
                    idPermohonan = lelong2.getPermohonan().getIdPermohonan();
                    LOG.info("idmohon : " + lelong2.getPermohonan().getIdPermohonan());

                    List<FasaPermohonan> stage1 = lelongService.findFasa(idPermohonan);
                    for (int a = 0; a < stage1.size(); a++) {
                        FasaPermohonan fasa = stage1.get(a);
                        if ("rekodBidaan".equals(fasa.getIdAliran())) {
                            KodKeputusan kod = kodKeputusanDAO.findById("AP");
                            fasa.setKeputusan(kod);
                            enkuiriService.saveFasaMohon(fasa);
                        }
                    }
                }
            }
        }

        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiSenaraiPermohonanLelonganJLB.jsp");
    }

    //tambah tarikh senarai hitam popup
    public Resolution tambahTarikhSenaraiHitam() {
        LOG.info("-------nak tambah Tarikh Hitam-------");

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        LOG.info("idPermohonan " + idPermohonan);
        Pembida pem = pembidaDAO.findById(Long.valueOf(getContext().getRequest().getParameter("idPembida")));
        LOG.info("id Pembida:" + pem.getIdPembida());
        pihak = pem.getPihak();
        Long idPihak1 = pem.getPihak().getIdPihak();
        LOG.info("id Pihak laaa: " + idPihak1);

        if (pem != null) {
            try {
                tarikhMula = sdf.format(pem.getTarikhMula()).substring(0, 10);
                tarikhTamat = sdf.format(pem.getTarikhAkhir()).substring(0, 10);
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
        return new JSP("lelong/tambahTarikhSenaraiHitamPembidaJLB.jsp").addParameter("popup", "true");
    }

    //simpan tarikh senarai hitam
    public Resolution simpanTarikhSenaraiHitam() throws ParseException {
        LOG.info("-------simpan Tarikh Hitam-------");

        Pembida pem = enkuiriService.getPembidaID(idPembida);
        LOG.info("id Pembida:" + pem.getIdPembida());
        pihak = pem.getPihak();
        Long idPihak1 = pem.getPihak().getIdPihak();
        LOG.info("id Pihak laaa: " + idPihak1);

        try {
            pem.setTarikhMula(sdf.parse(tarikhMula));
        } catch (Exception ex) {
            LOG.error(ex);
        }
        LOG.info("tarikh mula :" + tarikhMula);

        try {
            tarikhMula = sdf.format(pem.getTarikhMula()).substring(0, 10);
        } catch (Exception ex) {
            LOG.error(ex);
        }

        pem.setTarikhMula(sdf.parse(getContext().getRequest().getParameter("tarikhMula")));

        try {
            pem.setTarikhAkhir(sdf.parse(tarikhTamat));
        } catch (Exception ex) {
            LOG.error(ex);
        }
        LOG.info("tarikh Tamat :" + tarikhTamat);

        try {
            tarikhTamat = sdf.format(pem.getTarikhAkhir()).substring(0, 10);
        } catch (Exception ex) {
            LOG.error(ex);
        }
        pem.setTarikhAkhir(sdf.parse(getContext().getRequest().getParameter("tarikhTamat")));

        enkuiriService.saveOrUpdate(pem);

        addSimpleMessage("Maklumat Telah Berjaya DiSimpan...");
        return new JSP("lelong/tambahTarikhSenaraiHitamPembidaJLB.jsp").addParameter("popup", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public String getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(String idLelong) {
        this.idLelong = idLelong;
    }

    public String getKodJenis() {
        return kodJenis;
    }

    public void setKodJenis(String kodJenis) {
        this.kodJenis = kodJenis;
    }

    public List<Pembida> getList() {
        return list;
    }

    public void setList(List<Pembida> list) {
        this.list = list;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<Lelongan> getListLel1() {
        return listLel1;
    }

    public void setListLel1(List<Lelongan> listLel1) {
        this.listLel1 = listLel1;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getKodsts() {
        return kodsts;
    }

    public void setKodsts(String kodsts) {
        this.kodsts = kodsts;
    }

    public String getIdPembida() {
        return idPembida;
    }

    public void setIdPembida(String idPembida) {
        this.idPembida = idPembida;
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

    public List<Lelongan> getListLelongHakmilik() {
        return listLelongHakmilik;
    }

    public void setListLelongHakmilik(List<Lelongan> listLelongHakmilik) {
        this.listLelongHakmilik = listLelongHakmilik;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<Lelongan> getListLel2() {
        return listLel2;
    }

    public void setListLel2(List<Lelongan> listLel2) {
        this.listLel2 = listLel2;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhTamat() {
        return tarikhTamat;
    }

    public void setTarikhTamat(String tarikhTamat) {
        this.tarikhTamat = tarikhTamat;
    }

    public String getNoPengenalan() {
        return noPengenalan;
    }

    public void setNoPengenalan(String noPengenalan) {
        this.noPengenalan = noPengenalan;
    }

    public List<Pembida> getListPembida() {
        return listPembida;
    }

    public void setListPembida(List<Pembida> listPembida) {
        this.listPembida = listPembida;
    }

    public List<Pembida> getList2() {
        return list2;
    }

    public void setList2(List<Pembida> list2) {
        this.list2 = list2;
    }

    public Enkuiri getEnkuiri1() {
        return enkuiri1;
    }

    public void setEnkuiri1(Enkuiri enkuiri1) {
        this.enkuiri1 = enkuiri1;
    }

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public static boolean isIsDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean aIsDebug) {
        isDebug = aIsDebug;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public List<DokumenKewangan> getCheckBayaranPerintah() {
        return checkBayaranPerintah;
    }

    public void setCheckBayaranPerintah(List<DokumenKewangan> checkBayaranPerintah) {
        this.checkBayaranPerintah = checkBayaranPerintah;
    }

    public List<Permohonan> getCheckIdMohonSebelum() {
        return checkIdMohonSebelum;
    }

    public void setCheckIdMohonSebelum(List<Permohonan> checkIdMohonSebelum) {
        this.checkIdMohonSebelum = checkIdMohonSebelum;
    }

    public String getIdMohonSebelum() {
        return idMohonSebelum;
    }

    public void setIdMohonSebelum(String idMohonSebelum) {
        this.idMohonSebelum = idMohonSebelum;
    }

    public static boolean isIS_DEBUG() {
        return IS_DEBUG;
    }

    public static void setIS_DEBUG(boolean aIS_DEBUG) {
        IS_DEBUG = aIS_DEBUG;
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

    public List<PermohonanCarian> getSenaraiPermohonanCarian() {
        return senaraiPermohonanCarian;
    }

    public void setSenaraiPermohonanCarian(List<PermohonanCarian> senaraiPermohonanCarian) {
        this.senaraiPermohonanCarian = senaraiPermohonanCarian;
    }
}
