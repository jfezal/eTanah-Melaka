/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodAgensiDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.KodAgensi;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/common/hasilSokongan")
public class SokonganActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(SokonganActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    PermohonanRujukanLuarDAO permohonanUlasanDAO;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    private KodAgensi kodAgensi;
    @Inject
    RemisyenManager manager;
    private String display;
    private String dateRujukan;
    private String agensi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    @Inject
    private etanah.Configuration conf;
    @Inject
    etanah.kodHasilConfig khconf;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/sokongan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("hasil/sokongan.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanRujukanLuar> getSenaraiPermohonanRujukanLuar() {
        return senaraiPermohonanRujukanLuar;
    }

    public void setSenaraiPermohonanRujukanLuar(List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar) {
        this.senaraiPermohonanRujukanLuar = senaraiPermohonanRujukanLuar;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDateRujukan() {
        return dateRujukan;
    }

    public void setDateRujukan(String dateRujukan) {
        this.dateRujukan = dateRujukan;
    }

    public String getAgensi() {
        return agensi;
    }

    public void setAgensi(String agensi) {
        this.agensi = agensi;
    }
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public List getSenaraiKodAgensi() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodAgensi u where u.kod in (:kod1,:kod2,:kod3,:kod4,:kod5)");
        if ("05".equals(conf.getProperty("kodNegeri"))) { //for Negeri Sembilan
//            q.setString("kod1", "0153");
            q.setString("kod1",khconf.getProperty("jabatan.jpns"));
        }else if ("04".equals(conf.getProperty("kodNegeri"))) { //for Negeri Melaka
//            q.setString("kod1", "0154");
            q.setString("kod1", khconf.getProperty("jabatan.jpnm"));
        }
//        q.setString("kod2", "2402");
//        q.setString("kod3", "2403");
        q.setString("kod2", khconf.getProperty("jabatan.risda"));
        q.setString("kod3", khconf.getProperty("jabatan.felcra"));
        q.setString("kod4", khconf.getProperty("jabatan.felda"));
        q.setString("kod5", khconf.getProperty("jabatan.kementerian"));
        return q.list();
    }

//    @HandlesEvent("save")
    public Resolution saveOrUpdate() {
        LOG.info("saveOrUpdate:start");
        String result = null;
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodRujukan kr = new KodRujukan();
        kr.setKod("FL"); //FIXME
        KodAgensi ka = new KodAgensi();
        permohonan = permohonanDAO.findById((String) getContext().getRequest().getSession().getAttribute("idPermohonan"));

        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
        permohonanRujukanLuar.setKodRujukan(kr);

        try {
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(dateRujukan));
        } catch (ParseException ex) {
            LOG.error("permohonanRujukanLuar.setTarikhRujukan(sdf.parse(dateRujukan)) ex :" + ex);
        }
        ka.setKod(agensi);
        permohonanRujukanLuar.setAgensi(ka);

        result = manager.saveOrUpdate(permohonanRujukanLuar, peng);
        if ("save".equals(result)) {
            addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
            LOG.debug("Maklumat Telah Berjaya Disimpan.." + permohonanRujukanLuar.getIdRujukan());
        } else if ("update".equals(result)) {
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
            LOG.debug("Maklumat Telah Berjaya Dikemaskini..");
        } else {
            addSimpleError("Sistem Tergendala Sementara. Harap Maklum..");
            LOG.error("Sistem Tergendala Sementara. Harap Maklum..");
        }

        LOG.info("saveOrUpdate:finish");
        return new JSP("hasil/sokongan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            senaraiPermohonanRujukanLuar = permohonanUlasanDAO.findByEqualCriterias(tname, model, null);
            LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());
            if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
                permohonanRujukanLuar = senaraiPermohonanRujukanLuar.get(0);
                this.setDateRujukan(sdf.format(permohonanRujukanLuar.getTarikhRujukan()));
                this.setAgensi(permohonanRujukanLuar.getAgensi().getKod());
            }
        }
        LOG.info("rehydrate:finish");
    }
//    //not sure by fikri
//    @ValidationMethod(on = "saveOrUpdate")
//    public void validateField(ValidationErrors errors) {
//        if ((permohonanRujukanLuar == null) ||( (permohonanRujukanLuar.getAgensi() == null) && (permohonanRujukanLuar.getNoRujukan() == null) && (this.dateRujukan == null) ))
//            errors.add(" ", new SimpleError("Sila Isi Pada Ruangan Bertanda ( </font color=\"red\">*</font> )"));
//    }
}
