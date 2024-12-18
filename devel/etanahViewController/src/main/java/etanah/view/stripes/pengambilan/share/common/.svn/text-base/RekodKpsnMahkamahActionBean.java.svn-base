/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import etanah.view.stripes.pengambilan.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.Document;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanMahkamahDAO;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.service.ambil.PengambilanAPTService;
import etanah.service.common.PermohonanService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;

@UrlBinding("/pengambilan/rekodKpsn_Mahkamah")
public class RekodKpsnMahkamahActionBean extends AbleActionBean {

    private List<Document> xmlList;
    private static final Logger LOG = Logger.getLogger(LaporanMaklumatUrusanMis.class);
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    PermohonanMahkamahDAO permohonanMahkamahDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanService permohonanService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    AduanService aduanService;

    private Pengguna peng;
    private String idPermohonan;
    private Permohonan p;
    private List<PermohonanMahkamah> listpermohonanMahkamah;
    private PermohonanMahkamah permohonanMahkamah;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String idMohonMahkamah;
    String tarikhBicara;
    String tarikhKeputusan;
    String ulasanMahkamah;

    @DefaultHandler
    public Resolution showForm() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/APT/rekodKpsn_Mahkamah.jsp");
    }

    public Resolution searchIdMohon() {
        idPermohonan = getContext().getRequest().getParameter("p.idPermohonan");

        p = permohonanService.findById(idPermohonan);
        listpermohonanMahkamah = pengambilanAPTService.findMahkamahByIdMohon(idPermohonan);

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/APT/rekodKpsn_Mahkamah.jsp");
    }

    public Resolution hakMilikPopup() {
        LOG.info(idMohonMahkamah);
        permohonanMahkamah = aduanService.findByidPermohonanMahkamah(Long.parseLong(idMohonMahkamah));
        if (permohonanMahkamah != null) {
            tarikhBicara = permohonanMahkamah.getTarikh_bicara() + "";
            tarikhKeputusan = permohonanMahkamah.getTarikhTerimaPerintah() + "";
            ulasanMahkamah = permohonanMahkamah.getUlasan_mahkamah();
        }

        return new JSP("pengambilan/APT/kemasukan_kpsn_mahkamah.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution simpanMahkamah() throws ParseException {
//        idMohonMahkamah = (String) getContext().getRequest().getSession().getAttribute("idmm");
//        ulasanMahkamah = (String) getContext().getRequest().getSession().getAttribute("ulasanMahkamah");
//        tarikhKeputusan = (String) getContext().getRequest().getSession().getAttribute("tarikhKeputusan");
//        tarikhBicara = (String) getContext().getRequest().getSession().getAttribute("tarikhBicara");
        permohonanMahkamah = aduanService.findByidPermohonanMahkamah(Long.parseLong(idMohonMahkamah));
        if (permohonanMahkamah != null) {
            permohonanMahkamah.setTarikh_bicara(sdf.parse(tarikhBicara));
            permohonanMahkamah.setTarikhTerimaPerintah(sdf.parse(tarikhKeputusan));
            permohonanMahkamah.setUlasan_mahkamah(ulasanMahkamah);
        }
        aduanService.savePermohonanMahkamah(permohonanMahkamah);

        return new JSP("pengambilan/APT/kemasukan_kpsn_mahkamah.jsp").addParameter("popup", "true").addParameter("showForm", "false");
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

    public List<PermohonanMahkamah> getListpermohonanMahkamah() {
        return listpermohonanMahkamah;
    }

    public void setListpermohonanMahkamah(List<PermohonanMahkamah> listpermohonanMahkamah) {
        this.listpermohonanMahkamah = listpermohonanMahkamah;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getIdMohonMahkamah() {
        return idMohonMahkamah;
    }

    public void setIdMohonMahkamah(String idMohonMahkamah) {
        this.idMohonMahkamah = idMohonMahkamah;
    }

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public String getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(String tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public String getUlasanMahkamah() {
        return ulasanMahkamah;
    }

    public void setUlasanMahkamah(String ulasanMahkamah) {
        this.ulasanMahkamah = ulasanMahkamah;
    }
    

}
