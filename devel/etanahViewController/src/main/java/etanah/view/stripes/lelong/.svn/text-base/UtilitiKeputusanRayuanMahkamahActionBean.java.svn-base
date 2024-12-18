/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PihakDAO;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
//import org.apache.derby.impl.sql.compile.ParseException;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/UtilitiKeputusanRayuanMahkamah")
public class UtilitiKeputusanRayuanMahkamahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiKeputusanRayuanMahkamahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanrujukanluarDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PermohonanPihakDAO permohonanpihakDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikpermohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService ES;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Enkuiri enkuiri;
    private PermohonanRujukanLuar rujukanluar;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private List<HakmilikPermohonan> listHakmilikPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private String tarikhSidang;
    private String idPermohonan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String namaSidang;
    private String noSidang;
    private String noFail;
    private String noRujukan;
    private String ulasan;
    private String catatan;
    private String idhakmilik;
    private List<String> idHakmilik = new ArrayList<String>();
    private boolean flag = false;

    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKeputusanRayuanMahkamah.jsp");
    }

    public Resolution find() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());
                senaraiPermohonanRujukanLuar = ES.getPermohonanRujukanLuar(permohonan.getIdPermohonan());
            }
        }
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKeputusanRayuanMahkamah.jsp");
    }

    public Resolution simpanRujukan() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                LOG.debug("permohonan.getIdPermohonan() :" + permohonan.getIdPermohonan());
                
                    HakmilikPermohonan kk = lelongService.findHakmilikPermohonan(permohonan.getIdPermohonan());
                    LOG.info("idHakmilik : " + kk.getHakmilik().getIdHakmilik());
                    hakmilik = kk.getHakmilik();
             }

            String result = null;
            Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            KodCawangan kc = peng.getKodCawangan();

            System.out.println("permohonan :" + permohonan.getIdPermohonan());
            rujukanluar.setPermohonan(permohonan);
            rujukanluar.setHakmilik(hakmilik);
            rujukanluar.setCawangan(kc);
            rujukanluar.setInfoAudit(ia);

            KodRujukan kd = new KodRujukan();
            kd.setKod("FL");
            rujukanluar.setKodRujukan(kd);

            try {
                rujukanluar.setTarikhSidang(sdf.parse(tarikhSidang));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            enService.saveRujukanLuar(rujukanluar);
            tarikhSidang = sdf.format(rujukanluar.getTarikhSidang());

        }
        find();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiKeputusanRayuanMahkamah.jsp");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }

    public PermohonanRujukanLuar getRujukanluar() {
        return rujukanluar;
    }

    public void setRujukanluar(PermohonanRujukanLuar rujukanluar) {
        this.rujukanluar = rujukanluar;
    }

    public String getNamaSidang() {
        return namaSidang;
    }

    public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public String getNoSidang() {
        return noSidang;
    }

    public void setNoSidang(String noSidang) {
        this.noSidang = noSidang;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<String> getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(List<String> idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getListHakmilikPermohonan() {
        return listHakmilikPermohonan;
    }

    public void setListHakmilikPermohonan(List<HakmilikPermohonan> listHakmilikPermohonan) {
        this.listHakmilikPermohonan = listHakmilikPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdhakmilik() {
        return idhakmilik;
    }

    public void setIdhakmilik(String idhakmilik) {
        this.idhakmilik = idhakmilik;
    }
}
