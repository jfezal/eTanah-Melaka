/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/utiliti/ringkasan")
public class RingkasanPendaftaranAction extends AbleActionBean{


    private String idPermohonan;

    private Permohonan permohonan;

    @Inject PermohonanDAO permohonanDAO;

    @DefaultHandler
    public Resolution showForm(){
        return new JSP("daftar/utiliti/ringkasan_pendaftaran.jsp");
    }

    public Resolution search() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan == null) {
                addSimpleError("Perserahan tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila Masukan ID Perserahan.");
        }
        return new JSP("daftar/utiliti/ringkasan_pendaftaran.jsp");
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

}
