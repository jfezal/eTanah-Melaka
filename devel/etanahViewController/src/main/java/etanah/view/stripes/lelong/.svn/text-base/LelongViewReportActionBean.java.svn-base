package etanah.view.stripes.lelong;

import com.google.inject.Inject;
import etanah.dao.DokumenCapaianDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.KodStatusDokumenCapaiDAO;
import java.io.*;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author ${md.izzat}
 */
@HttpCache(allow = true)
@UrlBinding("/lelong/view/{idDokumen}")
public class LelongViewReportActionBean extends AbleActionBean {

    private static final Logger LOGGER = Logger.getLogger(LelongViewReportActionBean.class);
    private long idDokumen;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;

    @DefaultHandler
    public Resolution view() {
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
        dokumenCapaianDAO.save(dc);
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
//        if (d.getBaru() == 'Y') {
//            ia = d.getInfoAudit();
//            ia.setTarikhKemaskini(new Date());
//            ia.setDiKemaskiniOleh(pengguna);
//            d.setInfoAudit(ia);
//            d.setBaru('T');
//            dokumenDAO.update(d);
//        }
//        tx.commit();

        String path = docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal();
        File f = new File(path);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (Exception e) {
            LOGGER.error(e);
            return new ErrorResolution(500,
                    "Fail " + path + " tidak dijumpai");
        }

        getContext().getResponse().setContentType("application/octet-stream");

        String filename = null;
        int sep = d.getNamaFizikal().lastIndexOf(File.separator);
        if (sep >= 0) {
            d.getNamaFizikal().substring(sep);
        } else {
            filename = d.getNamaFizikal();
        }
        return new StreamingResolution(d.getFormat(), fis).setFilename(filename);

    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }
    
}
