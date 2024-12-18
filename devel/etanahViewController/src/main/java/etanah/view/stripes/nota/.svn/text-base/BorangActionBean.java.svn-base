/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikPihakKepentinganService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.manager.TabManager;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author noName
 */
@UrlBinding("/daftar/nota/info_borang")
public class BorangActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    RegService regService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PenggunaDAO penggunaDao;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(BorangActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujLuar;
    private HakmilikPermohonan hakmilikPermohonan;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private List<KodSekatanKepentingan> listKodSekatan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar;
    private String idH;
    private KodRujukan kodRujukan;
    private String idHakmilik;
    private String kodUrusan;
    @Inject
    StrataPtService strService;

    @DefaultHandler
    public Resolution restructureFORM() {
        kodUrusan = permohonan.getKodUrusan().getKod();

        rehydrate();
        return new JSP("daftar/nota/borang_fail.jsp").addParameter("tab", "true");
    }

    public Resolution showForm() {
        return new JSP("daftar/nota/borang_fail.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!saveOrUpdate"})
    public void rehydrate() {

        LOG.info("---rehydrate:start---");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idH = getContext().getRequest().getParameter("idH");

        if (idH != null) {
            hakmilik = hakmilikDAO.findById(idH);
        }
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR"))) {
                senaraiPermohonanRujukanLuar = strService.findPermohonanList(idPermohonan, "FL");
            } else {
                senaraiPermohonanRujukanLuar = permohonan.getSenaraiRujukanLuar();
            }
            LOG.debug("senaraiPermohonanRujukanLuar.size =" + senaraiPermohonanRujukanLuar.size());

            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

            for (HakmilikPermohonan hPermohonan : hakmilikPermohonanList) {

            kodUrusan = permohonan.getKodUrusan().getKod();
            LOG.info("check pihak");
            LOG.info("Kod Urusan" + permohonan.getKodUrusan().getKod());
            if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("PPKR"))) {
            }
        }
        if (!(senaraiPermohonanRujukanLuar.isEmpty())) {
            permohonanRujLuar = senaraiPermohonanRujukanLuar.get(0);


        }

        LOG.info("rehydrate:finish");
        }

    }

    public Resolution simpanNofail() { //urusan Batal

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(permohonan.getSenaraiHakmilik().get(0).getId());
        kodRujukan = kodRujukanDAO.findById("FL");
        if (idPermohonan != null) {
            permohonanRujLuar.setCawangan(peng.getKodCawangan());
            permohonanRujLuar.setPermohonan(permohonan);
            permohonanRujLuar.setKodRujukan(kodRujukan);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan.");

        }

        return new JSP("daftar/nota/borang_fail.jsp").addParameter("tab", "true");


    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujLuar() {
        return permohonanRujLuar;
    }

    public void setPermohonanRujLuar(PermohonanRujukanLuar permohonanRujLuar) {
        this.permohonanRujLuar = permohonanRujLuar;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdH() {
        return idH;
    }

    public void setIdH(String idH) {
        this.idH = idH;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }   
       
}