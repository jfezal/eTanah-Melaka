/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import etanah.view.stripes.pengambilan.*;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import electric.xml.Document;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/draf_NotaSiasatan")
public class Laporan_DrafNotaSiasatan extends AbleActionBean {

    private List<Document> xmlList;
    private static final Logger LOG = Logger.getLogger(LaporanMaklumatUrusanMis.class);
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanService permohonanService;
    
    private String notaSiasatan;

    private Pengguna peng;
    private String kodNegeri;
    private String idPermohonan;
    private Permohonan p;

    @Inject
    KodCawanganDAO kodCawanganDAO;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("showForm");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "n9";
        }
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        showReports();
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/APT/cetak_NotaSiasatan.jsp");

    }

    public Resolution searchIdMohon() {
        idPermohonan = getContext().getRequest().getParameter("p.idPermohonan");
        
        p = permohonanService.findById(idPermohonan);

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/APT/cetak_NotaSiasatan.jsp");
    }

    private void showReports() {
        LOG.info("showReport:start");

        notaSiasatan = "ACQBrgSiasatKosong_MLK.rdf";

    }

    public String getNotaSiasatan() {
        return notaSiasatan;
    }

    public void setNotaSiasatan(String notaSiasatan) {
        this.notaSiasatan = notaSiasatan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getP() {
        return p;
    }

    public void setP(Permohonan p) {
        this.p = p;
    }
    
    

}
