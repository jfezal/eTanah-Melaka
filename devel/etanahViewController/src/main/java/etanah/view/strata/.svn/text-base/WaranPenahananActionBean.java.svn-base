/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanWaranItemDAO;
import etanah.dao.StorRampasanDAO;
import etanah.dao.WaranDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.logging.Level;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodJabatan;
import etanah.model.KodNotis;
import etanah.model.Notis;
import etanah.model.NotisButiran;
import etanah.model.Permohonan;
import etanah.model.PermohonanWaranItem;
import etanah.model.Pihak;
import etanah.model.StorRampasan;
import etanah.model.Waran;
import etanah.model.WaranPihak;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.view.ListUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/waran")
public class WaranPenahananActionBean extends AbleActionBean {

    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StorRampasanDAO storRampasanDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    WaranDAO waranDAO;
    @Inject
    PermohonanWaranItemDAO permohonanWaranItemDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    ListUtil listUtil;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodNotisDAO kodNotisDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    etanah.Configuration conf;
    Permohonan permohonan;
    StorRampasan storRampasan = new StorRampasan();
    private static final Logger LOG = Logger.getLogger(WaranPenahananActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<ListHakmilikPihak> listListHakmilikPihak = new ArrayList();
    List<PermohonanWaranItem> listWaranItem = new ArrayList<PermohonanWaranItem>();
    List<Waran> listWaran = new ArrayList<Waran>();
    private String storAlamat1;
    private String storAlamat2;
    private String storAlamat3;
    private String storAlamat4;
    private String storPoskod;
    private String storNegeri;
    private String idStor;
    private String perihal;
    private BigDecimal amaun = BigDecimal.ZERO;
    private BigDecimal jumlah = BigDecimal.ZERO;
    private Date tarikhSumpah;
    private Long idWaran;
    String idMh;
    private boolean display = false;
    private boolean readOnly = false;
    private Pengguna pengguna;
    private String stageId = "";
    private String namaNegeri;
    private StorRampasan storRampasan1;
    private String kodNegeri;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        kodNegeri = conf.getProperty("kodNegeri");
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("STAGE ID : " + stageId);
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        this.listListHakmilikPihak.clear();
        for (HakmilikPermohonan mh : permohonan.getSenaraiHakmilik()) {
            ListHakmilikPihak lhp = new ListHakmilikPihak();
            lhp.setMohonHakmilik(mh);
            lhp.setHakmilik(strService.getUnitPetakByIdHakmilik(mh.getHakmilik().getIdHakmilik()));
            lhp.setListPihak(strService.getPihakPM(mh.getHakmilik().getIdHakmilik()));
            //Hakmilik hm = new Hakmilik();
            //  hm = strService.getUnitPetakByIdHakmilik(mh.getHakmilik().getIdHakmilik());
            Notis notis1 = new Notis();
            Notis notis2 = new Notis();
            NotisButiran notisButiran1 = new NotisButiran();
            NotisButiran notisButiran2 = new NotisButiran();
            if (!comm.findNotisByKod("NPS", idPermohonan, mh.getId()).isEmpty()) {
                notis1 = comm.findNotisByKod("NPS", idPermohonan, mh.getId()).get(0);
                if (comm.findNotiButiranByNotis(notis1) != null) {
                    notisButiran1 = comm.findNotiButiranByNotis(notis1);
                } else {
                    notisButiran1 = new NotisButiran();
                }
            } else {
                notis1 = new Notis();
                notisButiran1 = new NotisButiran();
                notis1.setTarikhNotis(null);
                notisButiran1.setAmaun(BigDecimal.ZERO);
            }

            if (!comm.findNotisByKod("NKS", idPermohonan, mh.getId()).isEmpty()) {
                notis2 = comm.findNotisByKod("NKS", idPermohonan, mh.getId()).get(0);
                if (comm.findNotiButiranByNotis(notis2) != null) {
                    notisButiran2 = comm.findNotiButiranByNotis(notis2);
                } else {
                    notisButiran2 = new NotisButiran();
                }
            } else {
                notis2 = new Notis();
                notisButiran2 = new NotisButiran();
                notis2.setTarikhNotis(null);
                notisButiran2.setAmaun(BigDecimal.ZERO);
            }

            LOG.info("LHP size: " + lhp.getListPihak().size());
            lhp.getListNotisWaran().setTarikhNotis(notis1.getTarikhNotis());
            lhp.getListNotisWaran().setAmaun(notisButiran1.getAmaun());
            lhp.getListNotisWaran2().setTarikhNotis(notis2.getTarikhNotis());
            lhp.getListNotisWaran2().setAmaun(notisButiran2.getAmaun());
            this.listListHakmilikPihak.add(lhp);
            LOG.info("size list" + listListHakmilikPihak.size());

        }
    }

    public Resolution tambahItemWaran() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String message = "oi";

        if (StringUtils.isBlank(perihal) || perihal == null) {
            addSimpleError("Sila Masukkan Perihal Wang Yang Berhutang");

        } else if ((amaun == null) || (amaun.compareTo(BigDecimal.ZERO) == 0)) {
            LOG.info("amaun::-------" + amaun);
            addSimpleError("Sila Masukkan Jumlah Wang Yang Berhutang");

        } else {

            HakmilikPermohonan mh = null;
            PermohonanWaranItem mohonWaranItem = new PermohonanWaranItem();
            if (StringUtils.isNotBlank(idMh)) {
                mh = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                mohonWaranItem.setHakmilikPermohonan(mh);
            }
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            mohonWaranItem.setAmaun(amaun);
            mohonWaranItem.setIdPermohonan(permohonan);
            mohonWaranItem.setKeterangan(perihal);
            mohonWaranItem.setCawangan(pguna.getKodCawangan());
            mohonWaranItem.setInfoAudit(ia);
            mohonWaranItem = comm.saveMohonWaranItem(mohonWaranItem);
            message = "Maklumat berjaya disimpan";
            addSimpleMessage(message);
        }

        perihal = "";
        amaun = BigDecimal.ZERO;

        listWaranItem = new ArrayList<PermohonanWaranItem>();
        idMh = getContext().getRequest().getParameter("idMh");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }
        return new JSP("strata/waran/detailMaklumatHutang.jsp").addParameter("popup", true);
    }

    public Resolution displayItemWaran() {
        listWaranItem.clear();
        idMh = getContext().getRequest().getParameter("idMh");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }
        display = true;
        return new JSP("strata/waran/detailMaklumatHutang.jsp").addParameter("popup", true);
    }

    public Resolution returnPage() {
        return new JSP("strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true");
    }

    public Resolution close() {
        if (kodNegeri.equals("05")) {
            if (stageId.equals("sediakertasptg")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        } else {
            if (stageId.equals("kemasukan")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        }
        return new JSP("strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true");
    }

    public Resolution kemaskiniWaranItem() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        LOG.info("-------Waran Item Upadting Started--------");
        String idMohonWaranItem = getContext().getRequest().getParameter("idMohonWaranItem");
        LOG.info("-------WaranItem Id--------::" + idMohonWaranItem);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idMh = getContext().getRequest().getParameter("idMh");
        if (StringUtils.isNotEmpty(idMohonWaranItem)) {
            PermohonanWaranItem mohonWaranItem = new PermohonanWaranItem();
            mohonWaranItem = permohonanWaranItemDAO.findById(Long.parseLong(idMohonWaranItem));
            if (mohonWaranItem != null) {
                HakmilikPermohonan mh = hakmilikPermohonanDAO.findById(Long.parseLong(idMh));
                mohonWaranItem.setHakmilikPermohonan(mh);
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new Date());
                mohonWaranItem.setAmaun(amaun);
                mohonWaranItem.setIdPermohonan(permohonan);
                mohonWaranItem.setKeterangan(perihal);
                mohonWaranItem.setCawangan(pguna.getKodCawangan());
                mohonWaranItem.setInfoAudit(ia);
                mohonWaranItem = comm.saveMohonWaranItem(mohonWaranItem);
                addSimpleMessage("Maklumat berjaya dikemaskini");

            } else {
                addSimpleError("Terdapat masalah teknikal.");
                LOG.info("mohonWaranItem is null");
            }

        }
        LOG.info("-------Waran Item Saved--------");

        listWaranItem = new ArrayList<PermohonanWaranItem>();
        idMh = getContext().getRequest().getParameter("idMh");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }
        return new RedirectResolution(WaranPenahananActionBean.class, "paparSenaraiHutang").addParameter("popup", true).addParameter("idMh", idMh);

    }

    public Resolution hapusWaranItem() {
        LOG.info("-------Waran Item Deleting Started--------");
        String idMohonWaranItem = getContext().getRequest().getParameter("idMohonWaranItem");
        LOG.info("-------WaranItem Id--------::" + idMohonWaranItem);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idMh = getContext().getRequest().getParameter("idMh");
        if (StringUtils.isNotEmpty(idMohonWaranItem)) {
            PermohonanWaranItem mohonWaranItem = new PermohonanWaranItem();
            mohonWaranItem = permohonanWaranItemDAO.findById(Long.parseLong(idMohonWaranItem));
            strService.deleteWaranItem(mohonWaranItem);
            addSimpleMessage("Maklumat berjaya dipadam.");
        }
        LOG.info("-------Waran Item Deleted--------");

        listWaranItem = new ArrayList<PermohonanWaranItem>();
        idMh = getContext().getRequest().getParameter("idMh");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }
        return new RedirectResolution(WaranPenahananActionBean.class, "paparSenaraiHutang").addParameter("tab", true).addParameter("idMh", idMh);
    }

    public Resolution popupItemWaran() {

        listWaranItem.clear();
        idMh = getContext().getRequest().getParameter("idMh");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }
        return new JSP("strata/waran/detailMaklumatHutang.jsp").addParameter("popup", true);
    }

    public Resolution popupKemaskiniWaran() {

        String idMohonWaranItem = getContext().getRequest().getParameter("idMohonWaranItem");
        idMh = getContext().getRequest().getParameter("idMh");
        if (StringUtils.isNotEmpty(idMohonWaranItem)) {
            PermohonanWaranItem mohonWaranItem = permohonanWaranItemDAO.findById(Long.parseLong(idMohonWaranItem));
            if (mohonWaranItem != null) {
                amaun = mohonWaranItem.getAmaun();
                perihal = mohonWaranItem.getKeterangan();
            }
        }

        return new JSP("strata/waran/kemaskini_waran_item.jsp").addParameter("popup", true);
    }

    public Resolution pemilikPetakBerhutang() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        if (kodNegeri.equals("05")) {
            if (stageId.equals("sediakertasptg")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        } else {
            if (stageId.equals("kemasukan")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        }
        return new JSP("strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true");
    }
    // added by zadirul

    public Resolution paparSenaraiHutang() {
        listWaranItem.clear();
        getContext().getRequest().setAttribute("paparHutang", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        LOG.info("----stageId----:" + stageId);

        if (kodNegeri.equals("05")) {
            if (stageId.equals("sediakertasptg")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        } else {
            if (stageId.equals("kemasukan")) {
                readOnly = false;
            } else {
                readOnly = true;
            }
        }

        idMh = getContext().getRequest().getParameter("idMh");
        LOG.info("idMH =" + idMh);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (StringUtils.isNotBlank(idMh)) {
            listWaranItem = comm.findSenaraiItemWaran(idPermohonan, Long.parseLong(idMh));
        }
        for (PermohonanWaranItem waranItem : listWaranItem) {
            jumlah = jumlah.add(waranItem.getAmaun());
        }

        return new JSP("strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true");
    }
    // added by zadirul

    public Resolution reload() {

        return new RedirectResolution(WaranPenahananActionBean.class, "paparSenaraiHutang").addParameter("tab", "true");
    }

    public Resolution pemilikPetakBerhutangReadOnly() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }

        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        readOnly = true;
        return new JSP("strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true");
    }

    public Resolution saveList() throws ParseException {
        boolean check = false;
        String error = "";
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodNotis kodNotis;
        KodJabatan kodJabatan = kodJabatanDAO.findById("20");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        for (ListHakmilikPihak lhp : this.listListHakmilikPihak) {
            Date date = new Date();
            String today = sdf.format(date);
            Date notis1 = new Date();
            Date notis2 = new Date();
            if (lhp.getListNotisWaran().getTarikhNotis() != null && lhp.getListNotisWaran2().getTarikhNotis() != null) {
                String notisP = sdf.format(lhp.getListNotisWaran().getTarikhNotis());
                notis1 = sdf.parse(notisP);
                String notisK = sdf.format(lhp.getListNotisWaran2().getTarikhNotis());
                notis2 = sdf.parse(notisK);
                date = sdf.parse(today);
                if (notis1.after(date)) {
                    error = "Tarikh yang dimasukkan perlu kurang dari tarikh hari ini";
                    check = false;
                    break;
                } else {
                    check = true;
                }
                if (notis1.equals(date)) {
                    error = "Tarikh yang dimasukkan sama dengan tarikh hari ini";
                    check = false;
                    break;
                } else {
                    check = true;
                }
                if (notis1.after(notis2)) {
                    error = "Tarikh notis pertama yang dimasukkan perlu kurang dari tarikh notis kedua";
                    check = false;
                    break;
                } else {
                    check = true;
                }
                if (notis2.equals(date)) {
                    error = "Tarikh notis pertama yang dimasukkan perlu kurang dari tarikh notis kedua";
                    check = false;
                    break;
                } else {
                    check = true;
                }
                if (notis2.after(date)) {
                    check = false;
                    error = "Tarikh yang dimasukkan tidak boleh selepas dari tarikh hari ini";
                    break;
                } else {
                    check = true;
                }
            }

        }

        if (check) {
            for (ListHakmilikPihak lhp : this.listListHakmilikPihak) {
                LOG.info("size :::" + this.listListHakmilikPihak.size());
                Dokumen dokumen1 = new Dokumen();
                dokumen1.setInfoAudit(ia);
                dokumen1.setKodDokumen(kodDokumenDAO.findById("NPS"));
                dokumen1.setTajuk(dokumen1.getKodDokumen().getNama());
                dokumen1.setNoVersi("1");
                dokumen1 = strService.saveDokumen(dokumen1);
                Notis notis1 = null;
                NotisButiran notisButiran1 = null;
                kodNotis = kodNotisDAO.findById("NPS");
                if (!comm.findNotisByKod(kodNotis.getKod(), permohonan.getIdPermohonan(), lhp.getMohonHakmilik().getId()).isEmpty()) {
                    notis1 = comm.findNotisByKod(kodNotis.getKod(), permohonan.getIdPermohonan(), lhp.getMohonHakmilik().getId()).get(0);
                } else {
                    notis1 = new Notis();
                }
                notis1.setKodNotis(kodNotis);
                notis1.setPermohonan(permohonan);
                notis1.setDokumenNotis(dokumen1);
                notis1.setJabatan(kodJabatan);
                notis1.setTarikhNotis(lhp.getListNotisWaran().getTarikhNotis());
                notis1.setInfoAudit(ia);
                notis1.setHakmilikPermohonan(lhp.getMohonHakmilik());
                notis1.setTempohHari(14);
                notis1 = comm.saveNotis(notis1);
                if (comm.findNotiButiranByNotis(notis1) != null) {
                    notisButiran1 = comm.findNotiButiranByNotis(notis1);
                } else {
                    notisButiran1 = new NotisButiran();
                }
                notisButiran1.setNotis(notis1);
                notisButiran1.setAmaun(lhp.getListNotisWaran().getAmaun());
                notisButiran1.setInfoAudit(ia);
                notisButiran1 = comm.saveNotisButiran(notisButiran1);

                Dokumen dokumen2 = new Dokumen();
                dokumen2.setInfoAudit(ia);
                dokumen2.setKodDokumen(kodDokumenDAO.findById("NKS"));
                dokumen2.setTajuk(dokumen2.getKodDokumen().getNama());
                dokumen2.setNoVersi("1");
                dokumen2 = strService.saveDokumen(dokumen2);
                Notis notis2 = new Notis();
                kodNotis = kodNotisDAO.findById("NKS");
                if (!comm.findNotisByKod(kodNotis.getKod(), permohonan.getIdPermohonan(), lhp.getMohonHakmilik().getId()).isEmpty()) {
                    notis2 = comm.findNotisByKod(kodNotis.getKod(), permohonan.getIdPermohonan(), lhp.getMohonHakmilik().getId()).get(0);
                } else {
                    notis2 = new Notis();
                }
                NotisButiran notisButiran2 = new NotisButiran();
                notis2.setKodNotis(kodNotis);
                notis2.setPermohonan(permohonan);
                notis2.setDokumenNotis(dokumen2);
                notis2.setJabatan(kodJabatan);
                notis2.setTarikhNotis(lhp.getListNotisWaran2().getTarikhNotis());
                notis2.setInfoAudit(ia);
                notis2.setTempohHari(14);
                notis2.setHakmilikPermohonan(lhp.getMohonHakmilik());
                notis2 = comm.saveNotis(notis2);
                if (comm.findNotiButiranByNotis(notis2) != null) {
                    notisButiran2 = comm.findNotiButiranByNotis(notis2);
                } else {
                    notisButiran2 = new NotisButiran();
                }
                notisButiran2.setNotis(notis2);
                notisButiran2.setAmaun(lhp.getListNotisWaran2().getAmaun());
                notisButiran2.setInfoAudit(ia);
                notisButiran2 = comm.saveNotisButiran(notisButiran2);
                strService.saveHakmilikPermohonan(lhp.getMohonHakmilik());

            }
            getContext().getRequest().setAttribute("msg", "Maklumat berjaya disimpan");
            addSimpleMessage("Maklumat berjaya disimpan");
        } else {
            addSimpleError(error);
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/waran/maklumat_pemilik_petak_berhutang.jsp").addParameter("tab", "true").addParameter("error", error);
    }

    public Resolution simpan() {
        for (ListHakmilikPihak lhp : this.listListHakmilikPihak) {
            BigDecimal b = lhp.getListNotisWaran().amaun;
        }
        return new RedirectResolution(WaranPenahananActionBean.class, "pemilikPetakBerhutang");
    }

    public Resolution storWaran() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        readOnly = false;

        storRampasan = new StorRampasan();
        storRampasan = strService.findStor(idPermohonan);
        Dokumen dokumen = strService.findDok(idPermohonan, "SKAB");
        if (storRampasan != null) {
            idStor = String.valueOf(storRampasan.getIdPelaksanaWaran());
            storAlamat1 = storRampasan.getAlamat1();
            storAlamat2 = storRampasan.getAlamat2();
            storAlamat3 = storRampasan.getAlamat3();
            storAlamat4 = storRampasan.getAlamat4();
            storPoskod = storRampasan.getPoskod();
            if (storRampasan.getNegeri() != null) {
                storNegeri = storRampasan.getNegeri().getKod();
                namaNegeri = storRampasan.getNegeri().getNama();
            }
            idStor = String.valueOf(storRampasan.getIdPelaksanaWaran());
            if (dokumen != null) {
                tarikhSumpah = dokumen.getTarikhDokumen();
            }
        }
        return new JSP("strata/waran/maklumatstor.jsp").addParameter("tab", "true");
    }

    public Resolution storWaranReadOnly() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        storRampasan = new StorRampasan();
        storRampasan = strService.findStor(idPermohonan);
        if (storRampasan != null) {
            idStor = String.valueOf(storRampasan.getIdPelaksanaWaran());
            storAlamat1 = storRampasan.getAlamat1();
            storAlamat2 = storRampasan.getAlamat2();
            storAlamat3 = storRampasan.getAlamat3();
            storAlamat4 = storRampasan.getAlamat4();
            storPoskod = storRampasan.getPoskod();
            if (storRampasan.getNegeri() != null) {
                storNegeri = storRampasan.getNegeri().getKod();
                namaNegeri = storRampasan.getNegeri().getNama();
            }
            idStor = String.valueOf(storRampasan.getIdPelaksanaWaran());
        }
        readOnly = true;
        return new JSP("strata/waran/maklumatstor.jsp").addParameter("tab", "true");
    }

    public Resolution saveStor() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        permohonan = permohonanDAO.findById(idPermohonan);
        StorRampasan storRampas = strService.findStor(idPermohonan);
        if (storRampas != null) {
            storRampasan = storRampasanDAO.findById(storRampas.getIdPelaksanaWaran());
            ia.setDimasukOleh(storRampasan.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(storRampasan.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new Date());
            storRampasan.setInfoAudit(ia);
        } else {
            LOG.info("New stor Rampasan");
            storRampasan = new StorRampasan();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new Date());
            storRampasan.setCawangan(pguna.getKodCawangan());
            storRampasan.setInfoAudit(ia);
        }
        storRampasan.setPermohonan(permohonan);
        storRampasan = setValueStor(storRampasan);
        storRampasan = strService.saveStorRampasan(storRampasan);
        if (tarikhSumpah != null) {
            Dokumen dokumen = strService.findDok(permohonan.getIdPermohonan(), "SKAB");
            if (dokumen != null) {
                dokumen.setTarikhDokumen(tarikhSumpah);
                strService.saveDokumen(dokumen);
            } else {
                dokumen.setTarikhDokumen(tarikhSumpah);
            }
        }
        addSimpleMessage("Maklumat telah disimpan");
        return new JSP("strata/waran/maklumatstor.jsp").addParameter("tab", "true");
    }

    public Resolution deleteStor() {
        LOG.info("--------Deleting Stor----------");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        storRampasan1 = strService.findStor(idPermohonan);
        LOG.info("--------StorRampasan---found-------" + storRampasan1);

        if (storRampasan1 != null) {
            LOG.info("--------Deleting storRampasan-------");
            strService.deleteStorRampasan(storRampasan1);
            LOG.info("--------Deleted storRampasan-------");
            addSimpleMessage("Maklumat berjaya dihapuskan.");
        }
        rehydrate();
        return new RedirectResolution(WaranPenahananActionBean.class, "storWaran");
    }

    private StorRampasan setValueStor(StorRampasan storRampasan) {
        LOG.info("Alamat 1: " + storAlamat1);
        LOG.info("storNegeri : " + this.storNegeri);
        storRampasan.setAlamat1(this.storAlamat1);
        storRampasan.setAlamat2(this.storAlamat2);
        storRampasan.setAlamat3(this.storAlamat3);
        storRampasan.setAlamat4(this.storAlamat4);
        storRampasan.setPoskod(this.storPoskod);
        LOG.info("Negeri" + this.getStorNegeri());
        if (!StringUtils.isBlank(this.storNegeri)) {
            storRampasan.setNegeri(kodNegeriDAO.findById(this.storNegeri));

        }
        return storRampasan;
    }

    public String getIdStor() {
        return idStor;
    }

    public void setIdStor(String idStor) {
        this.idStor = idStor;
    }

    public List<ListHakmilikPihak> getListListHakmilikPihak() {
        return listListHakmilikPihak;
    }

    public void setListListHakmilikPihak(List<ListHakmilikPihak> listListHakmilikPihak) {
        this.listListHakmilikPihak = listListHakmilikPihak;
    }

    public String getStorAlamat1() {
        return storAlamat1;
    }

    public void setStorAlamat1(String storAlamat1) {
        this.storAlamat1 = storAlamat1;
    }

    public String getStorAlamat2() {
        return storAlamat2;
    }

    public void setStorAlamat2(String storAlamat2) {
        this.storAlamat2 = storAlamat2;
    }

    public String getStorAlamat3() {
        return storAlamat3;
    }

    public void setStorAlamat3(String storAlamat3) {
        this.storAlamat3 = storAlamat3;
    }

    public String getStorAlamat4() {
        return storAlamat4;
    }

    public void setStorAlamat4(String storAlamat4) {
        this.storAlamat4 = storAlamat4;
    }

    public String getStorNegeri() {
        return storNegeri;
    }

    public void setStorNegeri(String storNegeri) {
        this.storNegeri = storNegeri;
    }

    public String getStorPoskod() {
        return storPoskod;
    }

    public void setStorPoskod(String storPoskod) {
        this.storPoskod = storPoskod;
    }

    public StorRampasan getStorRampasan() {
        return storRampasan;
    }

    public void setStorRampasan(StorRampasan storRampasan) {
        this.storRampasan = storRampasan;
    }

    public Long getIdWaran() {
        return idWaran;
    }

    public void setIdWaran(Long idWaran) {
        this.idWaran = idWaran;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public List<Waran> getListWaran() {
        return listWaran;
    }

    public void setListWaran(List<Waran> listWaran) {
        this.listWaran = listWaran;
    }

    public List<PermohonanWaranItem> getListWaranItem() {
        return listWaranItem;
    }

    public void setListWaranItem(List<PermohonanWaranItem> listWaranItem) {
        this.listWaranItem = listWaranItem;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public BigDecimal getAmaun() {
        return amaun;
    }

    public void setAmaun(BigDecimal amaun) {
        this.amaun = amaun;
    }

    public String getIdMh() {
        return idMh;
    }

    public void setIdMh(String idMh) {
        this.idMh = idMh;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Date getTarikhSumpah() {
        return tarikhSumpah;
    }

    public void setTarikhSumpah(Date tarikhSumpah) {
        this.tarikhSumpah = tarikhSumpah;
    }

    public StorRampasan getStorRampasan1() {
        return storRampasan1;
    }

    public void setStorRampasan1(StorRampasan storRampasan1) {
        this.storRampasan1 = storRampasan1;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }
}
