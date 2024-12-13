package etanah.view.stripes.consent;

import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/semak_dokumen")
public class SemakDokumenActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private static final Logger LOG = Logger.getLogger(SemakDokumenActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/consent/semak_dokumen.jsp");
    }

    public Resolution find() {
        LOG.info("---CARIAN BY ID PERMOHONAN " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan == null) {
                addSimpleError("Maklumat Tidak Dijumpai");
            } else {
                addSimpleMessage("Carian Dokumen Berjaya");
            }
        } else {
            addSimpleError("Sila Masukkan ID Permohonan");
        }

        return new ForwardResolution("/WEB-INF/jsp/consent/semak_dokumen.jsp");
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonan() {
        return this.permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

}
