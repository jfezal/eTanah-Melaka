/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 * @modified by haqqiem
 * 
 */
@UrlBinding("/kiosk/status_permohonan")
public class PertanyaanStatusPermohonanActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    String idPermohonan;
    String keterangan_ringkas;
    String date;
    String urusan;
    Permohonan permohonan = new Permohonan();
    
    @Inject
    PermohonanDAO mohonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("utiliti/pertanyaan_status_permohonan.jsp");
    }

    public Resolution cari() {
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = mohonDAO.findById(idMohon);
        if (mohon != null) {
            date = sdf.format(mohon.getInfoAudit().getTarikhMasuk());
            urusan = mohon.getKodUrusan().getNama();
            permohonan = mohon;
            if (mohon.getKeputusan() == null) {
                keterangan_ringkas = "Dalam Proses";
            } else {
                if (mohon.getKeputusan().getKod().equals("L")) {
                    keterangan_ringkas = "Lulus";
                } else if (mohon.getKeputusan().getKod().equals("T")) {
                    keterangan_ringkas = "Tolak";
                } else if (mohon.getKeputusan().getKod().equals("G")) {
                    keterangan_ringkas = "Gantung";
                } else if (mohon.getKeputusan().getKod().equals("D")) {
                    keterangan_ringkas = "Daftar";
                } else if (mohon.getKeputusan().getKod().equals("T")) {
                    keterangan_ringkas = "Tolak";
                } else if (mohon.getKeputusan().getKod().equals("SL")) {
                    keterangan_ringkas = "Selesai";
                } else {
                    keterangan_ringkas = "Dalam Proses";
                }
            }
            return new JSP("utiliti/paparan_status_permohonan.jsp");
        } else {
            keterangan_ringkas = "Maklumat tidak dijumpai. Sila isi semula.";
            return new JSP("utiliti/paparan_status_permohonan.jsp");
        }

    }

    public Resolution caribyic() {
        String idMohon = getContext().getRequest().getParameter("idPermohonan");
        Permohonan mohon = mohonDAO.findById(idMohon);
        if (mohon != null) {
            if (mohon.getKeputusan() == null) {
                keterangan_ringkas = "Dalam Proses";
            } else {
                if (mohon.getKeputusan().getKod().equals("L")) {
                    keterangan_ringkas = "Lulus";
                } else if (mohon.getKeputusan().getKod().equals("T")) {
                    keterangan_ringkas = "Tolak";
                } else if (mohon.getKeputusan().getKod().equals("G")) {
                    keterangan_ringkas = "Gantung";
                } else if (mohon.getKeputusan().getKod().equals("D")) {
                    keterangan_ringkas = "Daftar";
                } else if (mohon.getKeputusan().getKod().equals("T")) {
                    keterangan_ringkas = "Tolak";
                } else if (mohon.getKeputusan().getKod().equals("SL")) {
                    keterangan_ringkas = "Selesai";
                } else {

                }
            }
            return new JSP("utiliti/paparan_status_permohonan.jsp");
        } else {
            return showForm();
        }

    }

    public Resolution kembali() {
        return new RedirectResolution(PertanyaanStatusPermohonanActionBean.class);
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKeterangan_ringkas() {
        return keterangan_ringkas;
    }

    public void setKeterangan_ringkas(String keterangan_ringkas) {
        this.keterangan_ringkas = keterangan_ringkas;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

}
