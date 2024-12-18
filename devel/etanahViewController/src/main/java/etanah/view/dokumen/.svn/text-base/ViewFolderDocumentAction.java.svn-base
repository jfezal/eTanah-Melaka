package etanah.view.dokumen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;

import etanah.dao.DokumenCapaianDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenCapaian;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;

import able.stripes.AbleActionBean;
import etanah.dao.KodStatusDokumenCapaiDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Class for viewing document given a Kod Dokumen in folder Permohonan. This request MUST NOT be decorated by Sitemesh.
 * @author hishammk
 *
 */
@UrlBinding("/dokumen/{idPermohonan}/{kodDokumen}")
public class ViewFolderDocumentAction extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    DokumenCapaianDAO dokumenCapaianDAO;
    @Inject KodStatusDokumenCapaiDAO kodStatusDokumenCapaiDAO;
    @Inject
    private etanah.Configuration conf;
    private String idPermohonan;
    private String kodDokumen;

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String id) {
        this.idPermohonan = id;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    @DefaultHandler
    public Resolution view() {
        
        if (idPermohonan == null) {
            return new ErrorResolution(500, "ID Permohonan/Perserahan tidak dinyatakan");
        }

        Session session = sessionProvider.get();
        List<Dokumen> list = session.createQuery(
                "select d from Permohonan p, " +
                "KandunganFolder k, Dokumen d " +
                "where p.idPermohonan = :idPermohonan and p.folderDokumen.id = k.folder.id " +
                "and k.dokumen.id = d.idDokumen and d.kodDokumen.id = :kodDokumen " +
                "order by d.infoAudit.tarikhMasuk desc"
                ).setString("idPermohonan", idPermohonan).setString("kodDokumen", kodDokumen).list();
        if (list.size() == 0) {
            return new ErrorResolution(500, "Dokumen tidak dijumpai");
        }
        // show the most recent document only
        // TODO if many document with the same kod, show a page for user to select
        Dokumen d = list.get(0);

        // classification checking
        Pengguna p = ((etanahActionBeanContext) getContext()).getUser();
        if (d.getKlasifikasi() != null &&
                p.getTahapSekuriti().getKod() < d.getKlasifikasi().getKod()) {
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
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new java.util.Date());
        dc.setInfoAudit(ia);
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        dokumenCapaianDAO.save(dc);
        tx.commit();

        File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + d.getNamaFizikal());
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ErrorResolution(500,
                    "Fail " + docPath + d.getNamaFizikal() + " tidak dijumpai");
        }
        final FileInputStream in = fis;
        return new StreamingResolution(d.getFormat(), fis);
//        return new StreamingResolution(d.getFormat()) {
//
//            @Override
//            public void stream(HttpServletResponse response) throws Exception {
//                ServletOutputStream out = response.getOutputStream();
//
//                byte[] buffer = new byte[1024];
//
//                int len = 0;
//                while (true) {
//                    len = in.read(buffer);
//                    if (len < 0) {
//                        break;
//                    }
//                    out.write(buffer, 0, len);
//                }
//                in.close();
//                out.flush();
//                out.close();
//            }
//        }.setFilename(f.getName());
    }
}

