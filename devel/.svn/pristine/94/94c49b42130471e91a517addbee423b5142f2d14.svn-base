/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.KodJabatanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.KodJabatan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import java.text.ParseException;

/**
 *
 * @author nurul.hazirah
 */
@UrlBinding("/pelupusan/semak_dokumen")
public class SemakanDokumenActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService pbservice;
    @Inject
    KodJabatanDAO kodjabatanDAO;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private String idPermohonan;
    private String kodurus;
    private String jabatan;
    List<Permohonan> permohonanList = new ArrayList<Permohonan>();
    List<KodUrusan> ku = new ArrayList<KodUrusan>();
    private List<KodJabatan> kodjab;
    private static final Logger LOG = Logger.getLogger(SemakanDokumenActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        kodjab = kodjabatanDAO.findAll();
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_dokumen.jsp");
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

    public Resolution findKod() {
        String kodu = getContext().getRequest().getParameter("kod");
        LOG.info("---kodu---:" + kodu);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        ku = pbservice.findkodurusan(kodu);
        LOG.info("---ku---:" + ku.size());
        showForm();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_dokumen.jsp");
    }

    public Resolution search() throws ParseException {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (permohonan == null && jabatan == null) {
            addSimpleError("Sila Masukkan ID Permohonan, atau Unit.");
        } else {

            if (permohonan != null) {
                LOG.info("---CARIAN BY ID PERMOHONAN " + permohonan.getIdPermohonan());
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                if (permohonan != null) {
                    permohonanList.add(permohonan);
                }
            } else if (kodurus != null) {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                //permohonanList = pbservice.findPermohonanByKodU(kodurus);
                List<Permohonan> permohonans = new ArrayList<Permohonan>();
                permohonans = pbservice.findPermohonanByKodU(kodurus);
                for (Permohonan pm : permohonans) {
                    if (pm.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                        permohonanList.add(pm);
                    }
                }
            } else if (jabatan != null && kodurus == null) {
                Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                KodJabatan kjb = new KodJabatan();
                kjb = kodjabatanDAO.findById(jabatan);
                if (kjb != null && kjb.getKod() != null) {
                    //permohonanList = pbservice.findPermohonanByKodUrusan(kjb.getAkronim());
                    List<Permohonan> permohonans = new ArrayList<Permohonan>();
                    permohonans = pbservice.findPermohonanByKodUrusan(kjb.getAkronim());
                    for (Permohonan pm : permohonans) {
                        if (pm.getCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                            permohonanList.add(pm);
                        }
                    }
                }
            }

            if (permohonanList.isEmpty()) {
                addSimpleError("Carian maklumat permohonan tidak dijumpai");
            } else {
                addSimpleMessage("Carian maklumat permohonan berjaya.");
            }
        }

        getContext().getRequest().setAttribute("permohonan", Boolean.TRUE);
        showForm();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_dokumen.jsp");
    }

    public Resolution viewStatus() {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodurus = getContext().getRequest().getParameter("kodU");
        permohonan = permohonanDAO.findById(idPermohonan);
//        kodurus = permohonan.getKodUrusan().getKod();
        LOG.info("idPermohonan:" + idPermohonan);
        LOG.info("permohonan:" + permohonan);
        LOG.info("kodurus:" + kodurus);

        getContext().getRequest().setAttribute("dokumen", Boolean.TRUE);
        getContext().getRequest().setAttribute("permohonan", Boolean.FALSE);

//        showForm();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_dokumen.jsp");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        jabatan = null;
        kodjab = kodjabatanDAO.findAll();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/semak_dokumen.jsp");
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

    public List<KodUrusan> getKu() {
        return ku;
    }

    public void setKu(List<KodUrusan> ku) {
        this.ku = ku;
    }

    public List<KodJabatan> getKodjab() {
        return kodjab;
    }

    public void setKodjab(List<KodJabatan> kodjab) {
        this.kodjab = kodjab;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getKodurus() {
        return kodurus;
    }

    public void setKodurus(String kodurus) {
        this.kodurus = kodurus;
    }

    public List<Permohonan> getPermohonanList() {
        return permohonanList;
    }

    public void setPermohonanList(List<Permohonan> permohonanList) {
        this.permohonanList = permohonanList;
    }
}
