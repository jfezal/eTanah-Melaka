/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.cukaipetak;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodKegunaanBangunanDAO;
import etanah.dao.KodKelasTanahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.SkimStrataDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodKegunaanBangunan;
import etanah.model.KodKelasTanah;
import etanah.model.Pengguna;
import etanah.model.SkimStrata;
import etanah.service.strata.cukaipetak.CukaiPetakService;
import etanah.service.strata.cukaipetak.StrataParam;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
@UrlBinding("/strata/kemaskiniskim")
public class KemaskiniSkimStrataActionBean extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    GISPelanDAO pelanGISDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;

    @Inject
    CukaiPetakService cukaiPetakService;
    @Inject
    SkimStrataDAO skimStrataDAO;
    @Inject
    KodKelasTanahDAO kodKelasTanahDAO;
    @Inject
    KodKegunaanBangunanDAO kodKegunaanBangunanDAO;
    private static final Logger LOG = Logger.getLogger(KemaskiniSkimStrataActionBean.class);
    Pengguna pengguna;
    Integer totalSkim;
    Integer totalKemaskini;
    private List<MaklumatSkim> senaraiSkim = new ArrayList<MaklumatSkim>();
    String[] id;
    String[] syaratNyata;
    String[] kelasBangun;
    String daerah;
    String idHakmilik;
    String bpm;
    private List<KodDaerah> listKodDaerah;
    private List<KodBandarPekanMukim> listBPM;
    String[] kosRendah;

    @DefaultHandler
    public Resolution showForm() {

        LOG.info("showForm");
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();
        StrataParam param = new StrataParam();
        daerah = (String) getContext().getRequest().getSession().getAttribute("skimdaerah");
        bpm = (String) getContext().getRequest().getSession().getAttribute("skimbpm");

        param.setDaerah(daerah);
        param.setBpm(bpm);

        int a = get__pg_start();
        int b = get__pg_max_records();
        senaraiSkim = cukaiPetakService.findSkimStrata(param, a, b);
        setPage(cukaiPetakService.findCountSkimStrata(param).intValue());

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/kemaskiniskim.jsp");
    }

    public Resolution filterMukim() {
        daerah = (String) getContext().getRequest().getParameter("daerah");
//        reportName = getContext().getRequest().getParameter("namaReport");
//        report = getContext().getRequest().getParameter("report");
//        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listKodDaerah = kodDaerahDAO.findAll();
        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = q.list();
//        listKodSeksyen = kodSeksyenDAO.findAll();
        StrataParam param = new StrataParam();
        daerah = (String) getContext().getRequest().getSession().getAttribute("skimdaerah");
        bpm = (String) getContext().getRequest().getSession().getAttribute("skimbpm");

        param.setDaerah(daerah);
        param.setBpm(bpm);
        int a = get__pg_start();
        int b = get__pg_max_records();
        senaraiSkim = cukaiPetakService.findSkimStrata(param, a, b);
        setPage(cukaiPetakService.findCountSkimStrata(param).intValue());
        return new JSP("strata/cukaipetak/kemaskiniskim.jsp");

    }

    public Resolution cari() {

        daerah = getContext().getRequest().getParameter("daerah");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        bpm = getContext().getRequest().getParameter("bpm");

        getContext().getRequest().getSession().setAttribute("skimdaerah", daerah);
        getContext().getRequest().getSession().setAttribute("skimbpm", bpm);

        StrataParam param = new StrataParam();
//        daerah = (String) getContext().getRequest().getSession().getAttribute("skimdaerah");
        param.setDaerah(daerah);
        param.setIdHakmilik(idHakmilik);
        param.setBpm(bpm);

        syaratNyata = new String[20];
        kelasBangun = new String[20];
        kosRendah = new String[20];
        int a = get__pg_start();
        int b = get__pg_max_records();
        senaraiSkim = cukaiPetakService.findSkimStrata(param, a, b);
        setPage(cukaiPetakService.findCountSkimStrata(param).intValue());
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/kemaskiniskim.jsp");
    }

    public Resolution simpanSenarai() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        id = getContext().getRequest().getParameterValues("id");
        syaratNyata = getContext().getRequest().getParameterValues("syaratNyata");
        kelasBangun = getContext().getRequest().getParameterValues("kelasBangun");
        kosRendah = getContext().getRequest().getParameterValues("kosRendah");
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        for (int i = 0; i < id.length; i++) {
            SkimStrata skim = cukaiPetakService.findBySkimId(id[i]);
            if (skim != null) {
                if (StringUtils.isNotBlank(kelasBangun[i])) {
                    KodKelasTanah kelas = kodKelasTanahDAO.findById(kelasBangun[i]);
                    skim.setKelasTanah(kelas);
                }
                if (StringUtils.isNotBlank(syaratNyata[i])) {
                    KodKegunaanBangunan guna = kodKegunaanBangunanDAO.findById(syaratNyata[i]);
                    skim.setKategoriBangunan(guna);
                }

                if (StringUtils.isNotBlank(kosRendah[i])) {
                    skim.setKosRendah(kosRendah[i]);
                } else {
                    skim.setKosRendah("T");
                }
                skim.setFlagCukai("T");
                skim.setFlagJana("T");
                skim.setPegKemaskiniSkim(pengguna);
                skim.setTarikhKemaskiniSkim(new Date());
                cukaiPetakService.saveSkimStrata(skim);
            }
        }
        tx.commit();
        StrataParam param = new StrataParam();
        daerah = (String) getContext().getRequest().getSession().getAttribute("skimdaerah");
        param.setDaerah(daerah);

        syaratNyata = new String[20];
        kelasBangun = new String[20];
        int a = get__pg_start();
        int b = get__pg_max_records();
        senaraiSkim = cukaiPetakService.findSkimStrata(param, a, b);
        setPage(cukaiPetakService.findCountSkimStrata(param).intValue());
        listKodDaerah = kodDaerahDAO.findAll();
        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/kemaskiniskim.jsp");

    }

    public Resolution parameter() {
        LOG.info("showForm");

        setPage(senaraiSkim.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new ForwardResolution("/WEB-INF/jsp/strata/cukaipetak/kemaskiniskim.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        cukaiPetakService.validateSkim();

    }

    private void setPage(int senarai) {

        ParamEncoder encoder = new ParamEncoder("line");

        String page = getContext().getRequest().getParameter((encoder.encodeParameterName(TableTagParameters.PARAMETER_PAGE)));

        set__pg_total_records(senarai);
        if (StringUtils.isNotBlank(page)) {
            set__pg_start((Integer.parseInt(page) - 1) * get__pg_max_records());
            set__pg_max_records(get__pg_start() + get__pg_max_records());
        }

        if (get__pg_max_records() > get__pg_total_records()) {
            set__pg_max_records(get__pg_total_records());
        }

    }

    public Integer getTotalSkim() {
        return totalSkim;
    }

    public void setTotalSkim(Integer totalSkim) {
        this.totalSkim = totalSkim;
    }

    public Integer getTotalKemaskini() {
        return totalKemaskini;
    }

    public void setTotalKemaskini(Integer totalKemaskini) {
        this.totalKemaskini = totalKemaskini;
    }

    public List<MaklumatSkim> getSenaraiSkim() {
        return senaraiSkim;
    }

    public void setSenaraiSkim(List<MaklumatSkim> senaraiSkim) {
        this.senaraiSkim = senaraiSkim;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public String[] getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String[] syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String[] getKelasBangun() {
        return kelasBangun;
    }

    public void setKelasBangun(String[] kelasBangun) {
        this.kelasBangun = kelasBangun;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public List<KodDaerah> getListKodDaerah() {
        return listKodDaerah;
    }

    public void setListKodDaerah(List<KodDaerah> listKodDaerah) {
        this.listKodDaerah = listKodDaerah;
    }

    public List<KodBandarPekanMukim> getListBPM() {
        return listBPM;
    }

    public void setListBPM(List<KodBandarPekanMukim> listBPM) {
        this.listBPM = listBPM;
    }

}
