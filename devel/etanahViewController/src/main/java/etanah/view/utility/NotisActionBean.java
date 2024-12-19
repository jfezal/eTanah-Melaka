/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.report.ReportUtil;
import etanah.service.common.NotisService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/utility/janaNotis")
public class NotisActionBean extends AbleActionBean {
    @Inject
    private KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    CommonService comm;
    @Inject
    NotisService notisService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private KandunganFolderDAO kandunganFolderDAO;
    private static final Logger LOG = Logger.getLogger(NotisActionBean.class);
@Inject NotisDAO d;
    private Notis notis;
    private Pengguna pengguna;
    private List<Notis> listNotis = new ArrayList();

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("utiliti/JanaNotis.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listNotis = comm.findNotisByKod("N14" ,"",null);
    }

    public Resolution janaNotis() {
        LOG.info("start:");
        for (Notis n : listNotis) {
            Dokumen dok = new Dokumen();
            if (n.getKodNotis().getKod().equals("N14")) {
                dok = generateFile(n, "STRSPeringatan14Hari_NS.rdf","N14", "Notis Peringatan Pembayaran 14hari");
            }
            if (n.getKodNotis().getKod().equals("N30")) {
                dok = generateFile(n, "STRSPeringatan14Hari_NS.rdf","N30","Notis Peringatan Pembayaran 30hari");
            }

            n.setDokumenNotis(dok);
            n.setJabatan(n.getPermohonan().getKodUrusan().getJabatan());

            notisService.saveOrUpdate(n);
        }
        LOG.info("End:");
        return new ForwardResolution(NotisActionBean.class, "showForm");
    }

    public Dokumen generateFile(Notis n, String reportName, String kodD, String tajuk) {
        LOG.info("generateFile:");
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());
        Dokumen doc = new Dokumen();
                doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        doc.setKlasifikasi(klasifikasiAm);
        KodDokumen koddoc = kodDokumenDAO.findById(kodD);
        doc.setKodDokumen(koddoc);
        doc.setNoVersi("1.0");
        LOG.info(tajuk);
        doc.setTajuk(tajuk);
        doc = dokumenDAO.saveOrUpdate(doc);
        String documentPath = conf.getProperty("document.path");
        String path = documentPath + (documentPath.endsWith(File.separator) ? "" : File.separator);
        String path2 = n.getPermohonan().getFolderDokumen().getFolderId() + File.separator + String.valueOf(doc.getIdDokumen());
        n.setDokumenNotis(doc);
//        n.setDicetakOleh(pengguna.getIdPengguna());
//        n.setTarikhCetak(new Date());
        reportUtil.generateReport(reportName,
                new String[]{"p_id_mohon"},
                new String[]{n.getPermohonan().getIdPermohonan()},
                path + path2, null);
        doc.setNamaFizikal(reportUtil.getDMSPath());
        dokumenDAO.update(doc);
        KandunganFolder kf = new KandunganFolder();
        kf.setDokumen(doc);
        kf.setFolder(folderDokumenDAO.findById(n.getPermohonan().getFolderDokumen().getFolderId()));
        kf.setInfoAudit(ia);
        kf.setCatatan("");
        kandunganFolderDAO.save(kf);

        return doc;
    }

    public void printerSpool(){

    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public List<Notis> getListNotis() {
        return listNotis;
    }

    public void setListNotis(List<Notis> listNotis) {
        this.listNotis = listNotis;
    }
}
