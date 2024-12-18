/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan.pelan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.PermohonanPlotPelan;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.service.dev.pelan.PelanB2Service;
import etanah.util.FileUtil;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
@UrlBinding("/pembangunan/melaka/muat_naik_pelan")
public class MuatNaikPelanB2ActionBean extends AbleActionBean {

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
    KodCawanganDAO kodCawanganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PelanB2Service pelanB2Service;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    private List<PermohonanPlotPelan> senaraiPlotPelan;
    private List<SenaraiPlotPT> senaraiMuatNaik;
    private String idPlot;
    private String daerah;
    private String mukim;
    private String seksyen;
    private String cawangan;
    private Boolean view;
    private String kodHakmilikSementara;
    private String kodHakmilikTetap;
    private Integer jumPajakan;

    private List<KodDaerah> listKodDaerah;
    private List<KodBandarPekanMukim> listBPM;
    private List<KodSeksyen> listKodSeksyen;
    private List<SelectCustom> listSekatan;
    private List<SelectCustom> listSyaratNyata;
    private String syaratNyataPlot;
    private String sekatanPlot;
    private String bumiPlot;
    private String[] syaratNyata;
    private String[] sekatan;
    private String[] bumi;
    private String[] idPt;
    private String nopt;
    private BigDecimal luas;
    private String nopelanakui;
    private String pelantif;
    private String unitukur;
    private FileBean file;
    private String jenispelan;

    public Resolution filterSeksyen() {
        seksyen = (String) getContext().getRequest().getParameter("seksyen");
        daerah = (String) getContext().getRequest().getParameter("daerah");
        mukim = (String) getContext().getRequest().getParameter("mukim");
        listKodDaerah = kodDaerahDAO.findAll();

        String sql = null;
        Session s = sessionProvider.get();
        Query q = null;
        if (mukim == null || mukim.equals("")) {
            sql = "select seks from KodSeksyen seks ";
            q = s.createQuery(sql);
        } else {
            sql = "select seks from KodSeksyen seks where seks.kodBandarPekanMukim.kod = :kod order by seks.kod asc ";
            q = s.createQuery(sql);
            q.setString("kod", mukim);
        }
        listKodSeksyen = q.list();
        daerah = (String) getContext().getRequest().getParameter("daerah");
        if (daerah == null || daerah.equals("")) {
            sql = "select bpm from KodBandarPekanMukim bpm ";
            q = s.createQuery(sql);
        } else {
            sql = "select bpm from KodBandarPekanMukim bpm where bpm.daerah.kod = :kod ";
            q = s.createQuery(sql);
            q.setString("kod", daerah);
        }
        listBPM = q.list();
        return new JSP("pembangunan/sedia_b2_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution filterMukim() {
        daerah = (String) getContext().getRequest().getParameter("daerah");
        listKodDaerah = kodDaerahDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();
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
        listKodSeksyen = kodSeksyenDAO.findAll();
        return new JSP("pembangunan/sedia_b2_pelan.jsp").addParameter("tab", "true");
    }

    @DefaultHandler
    public Resolution showForm() {

        return new JSP("pembangunan/sedia_b2_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution viewOnly() {
        view = Boolean.TRUE;
        return new JSP("pembangunan/sedia_b2_pelan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPlotPelan = pelanB2Service.findSenaraiPlotByIdPermohonan(idPermohonan);
        PermohonanPlotKpsn plotKpsn = senaraiPlotPelan.get(0).getPermohonanPlotKpsn();
        if (plotKpsn != null) {
            cawangan = plotKpsn.getCawangan() != null ? plotKpsn.getCawangan().getKod() : "";
            daerah = plotKpsn.getDaerah() != null ? plotKpsn.getDaerah().getKod() : "";
            mukim = String.valueOf(plotKpsn.getBpm() != null ? plotKpsn.getBpm().getKod() : "");
            seksyen = String.valueOf(plotKpsn.getSeksyen() != null ? plotKpsn.getSeksyen().getKod() : "");
            jumPajakan = plotKpsn.getTempohPajakan();
            kodHakmilikSementara = plotKpsn.getKodHakmilikSementara() != null ? plotKpsn.getKodHakmilikSementara().getKod() : "";
            kodHakmilikTetap = plotKpsn.getKodHakmilikTetap() != null ? plotKpsn.getKodHakmilikTetap().getKod() : "";
        }
        listKodDaerah = kodDaerahDAO.findAll();
        listBPM = kodBandarPekanMukimDAO.findAll();
        listKodSeksyen = kodSeksyenDAO.findAll();
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

    public Resolution papar() {
        senaraiMuatNaik = pelanB2Service.findSenaraiByidPlot(Long.parseLong(idPlot));
        listSekatan = pelanB2Service.findCustomSekatanByIdPlot(Long.parseLong(idPlot));
        listSyaratNyata = pelanB2Service.findCustomSyaratNyataByIdPlot(Long.parseLong(idPlot));

        setPage(senaraiMuatNaik.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new JSP("pembangunan/common/sedia_b2_pelan_kemaskini.jsp").addParameter("popup", "true");

    }

    public Resolution muatNaik() {

        return new JSP("pembangunan/common/muat_naik_b2.jsp").addParameter("popup", "true");

    }

    public Resolution simpanSenarai() {
        idPt = getContext().getRequest().getParameterValues("idPt");
        syaratNyata = getContext().getRequest().getParameterValues("syaratNyata");
        bumi = getContext().getRequest().getParameterValues("bumi");
        sekatan = getContext().getRequest().getParameterValues("sekatan");
        Map m = getContext().getRequest().getParameterMap();

        for (int i = 0; i < idPt.length; i++) {
            NoPt pt = noPtDAO.findById(Long.parseLong(idPt[i]));
            pt.setBumi(bumi[i]);
            pt.setKodSekatanKepentingan(kodSekatanKepentinganDAO.findById(sekatan[i]));
            pt.setKodSyaratNyata(kodSyaratNyataDAO.findById(syaratNyata[i]));
            pelanB2Service.saveNoPt(pt);

        }
        return new JSP("pembangunan/common/sedia_b2_pelan_kemaskini.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKelompok() {
        sekatanPlot = getContext().getRequest().getParameter("sekatanPlot");
        syaratNyataPlot = getContext().getRequest().getParameter("syaratNyataPlot");
        bumiPlot = getContext().getRequest().getParameter("bumiPlot");
        idPlot = getContext().getRequest().getParameter("idPlot");
        KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(sekatanPlot);
        KodSyaratNyata nyata = kodSyaratNyataDAO.findById(syaratNyataPlot);
        senaraiMuatNaik = pelanB2Service.findSenaraiByidPlot(Long.parseLong(idPlot));
        for (SenaraiPlotPT ppt : senaraiMuatNaik) {
            ppt.setBumi(bumiPlot);
            ppt.setSekatan(sekatanPlot);
            ppt.setSyaratNyata(syaratNyataPlot);
            NoPt pt = noPtDAO.findById(ppt.getIdPt());
            pt.setBumi(bumiPlot);
            pt.setKodSekatanKepentingan(sekatan);
            pt.setKodSyaratNyata(nyata);
            pelanB2Service.saveNoPt(pt);
        }
        return new JSP("pembangunan/common/sedia_b2_pelan_kemaskini.jsp").addParameter("popup", "true");
    }

    public Resolution simpanDataHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPlotPelan = pelanB2Service.findSenaraiPlotByIdPermohonan(idPermohonan);

        PermohonanPlotKpsn plotKpsn = senaraiPlotPelan.get(0).getPermohonanPlotKpsn();
        if (plotKpsn != null) {
        } else {
            plotKpsn = new PermohonanPlotKpsn();
        }
        plotKpsn.setBpm(kodBandarPekanMukimDAO.findById(Integer.parseInt(mukim)));
        plotKpsn.setCawangan(kodCawanganDAO.findById(cawangan));
        plotKpsn.setDaerah(kodDaerahDAO.findById(daerah));
        if (StringUtils.isNotBlank(seksyen)) {
            plotKpsn.setSeksyen(kodSeksyenDAO.findById(Integer.parseInt(seksyen)));
        }
        plotKpsn.setKodHakmilikSementara(kodHakmilikDAO.findById(kodHakmilikSementara));
        plotKpsn.setKodHakmilikTetap(kodHakmilikDAO.findById(kodHakmilikTetap));
        plotKpsn.setTempohPajakan(jumPajakan);
        pelanB2Service.save(plotKpsn);
        for (PermohonanPlotPelan p : senaraiPlotPelan) {
            p.setPermohonanPlotKpsn(plotKpsn);
            pelanB2Service.savePermohonanPlotPelan(p);
        }
        rehydrate();
        return new JSP("pembangunan/sedia_b2_pelan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws IOException, Exception {
        PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot)).getPermohonanPlotKpsn();

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        GISPelan pelan = new GISPelan();
        GISPelanPK pk = new GISPelanPK();
        KodNegeri kn = kodNegeriDAO.findById(conf.getKodNegeri());
        KodDaerah kd = kpsn.getDaerah();
        KodBandarPekanMukim bpm = kpsn.getBpm();
        KodSeksyen seksyen1 = kpsn.getSeksyen();
        if (seksyen1 != null) {
            pk.setKodSeksyen(String.valueOf(seksyen1.getSeksyen()));
        } else {
            pk.setKodSeksyen("000");

        }
        pk.setKodNegeri(kn);
        pk.setKodDaerah(kd);
        pk.setKodMukim(bpm.getbandarPekanMukim());

        pk.setNoLot(nopt);
        pk.setJenisPelan("B2");
        pelan.setPelanGISPK(pk);

        pelan.setLuas(luas);
        pelan.setUnitUkur(unitukur);
        pelan.setTrhKemaskini(new Date());
        pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (StringUtils.isNotBlank(seksyen) ? File.separator + seksyen1.getSeksyen() : "");
        FileUtil fileUtil = new FileUtil(loc);
        System.out.print("sssss" + file.getFileName().toLowerCase());
        fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());
        tx.commit();

        return new JSP("pembangunan/common/muat_naik_b2.jsp").addParameter("popup", "true");
    }

    public List<PermohonanPlotPelan> getSenaraiPlotPelan() {
        return senaraiPlotPelan;
    }

    public void setSenaraiPlotPelan(List<PermohonanPlotPelan> senaraiPlotPelan) {
        this.senaraiPlotPelan = senaraiPlotPelan;
    }

    public List<SenaraiPlotPT> getSenaraiMuatNaik() {
        return senaraiMuatNaik;
    }

    public void setSenaraiMuatNaik(List<SenaraiPlotPT> senaraiMuatNaik) {
        this.senaraiMuatNaik = senaraiMuatNaik;
    }

    public String getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(String idPlot) {
        this.idPlot = idPlot;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public Boolean getView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public KodDaerahDAO getKodDaerahDAO() {
        return kodDaerahDAO;
    }

    public void setKodDaerahDAO(KodDaerahDAO kodDaerahDAO) {
        this.kodDaerahDAO = kodDaerahDAO;
    }

    public KodNegeriDAO getKodNegeriDAO() {
        return kodNegeriDAO;
    }

    public void setKodNegeriDAO(KodNegeriDAO kodNegeriDAO) {
        this.kodNegeriDAO = kodNegeriDAO;
    }

    public KodSeksyenDAO getKodSeksyenDAO() {
        return kodSeksyenDAO;
    }

    public void setKodSeksyenDAO(KodSeksyenDAO kodSeksyenDAO) {
        this.kodSeksyenDAO = kodSeksyenDAO;
    }

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PelanB2Service getPelanB2Service() {
        return pelanB2Service;
    }

    public void setPelanB2Service(PelanB2Service pelanB2Service) {
        this.pelanB2Service = pelanB2Service;
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

    public List<KodSeksyen> getListKodSeksyen() {
        return listKodSeksyen;
    }

    public void setListKodSeksyen(List<KodSeksyen> listKodSeksyen) {
        this.listKodSeksyen = listKodSeksyen;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getKodHakmilikSementara() {
        return kodHakmilikSementara;
    }

    public void setKodHakmilikSementara(String kodHakmilikSementara) {
        this.kodHakmilikSementara = kodHakmilikSementara;
    }

    public String getKodHakmilikTetap() {
        return kodHakmilikTetap;
    }

    public void setKodHakmilikTetap(String kodHakmilikTetap) {
        this.kodHakmilikTetap = kodHakmilikTetap;
    }

    public Integer getJumPajakan() {
        return jumPajakan;
    }

    public void setJumPajakan(Integer jumPajakan) {
        this.jumPajakan = jumPajakan;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public KodHakmilikDAO getKodHakmilikDAO() {
        return kodHakmilikDAO;
    }

    public void setKodHakmilikDAO(KodHakmilikDAO kodHakmilikDAO) {
        this.kodHakmilikDAO = kodHakmilikDAO;
    }

    public List<SelectCustom> getListSekatan() {
        return listSekatan;
    }

    public void setListSekatan(List<SelectCustom> listSekatan) {
        this.listSekatan = listSekatan;
    }

    public List<SelectCustom> getListSyaratNyata() {
        return listSyaratNyata;
    }

    public void setListSyaratNyata(List<SelectCustom> listSyaratNyata) {
        this.listSyaratNyata = listSyaratNyata;
    }

    public String[] getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String[] syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public String[] getSekatan() {
        return sekatan;
    }

    public void setSekatan(String[] sekatan) {
        this.sekatan = sekatan;
    }

    public String[] getBumi() {
        return bumi;
    }

    public void setBumi(String[] bumi) {
        this.bumi = bumi;
    }

    public String[] getIdPt() {
        return idPt;
    }

    public void setIdPt(String[] idPt) {
        this.idPt = idPt;
    }

    public String getSyaratNyataPlot() {
        return syaratNyataPlot;
    }

    public void setSyaratNyataPlot(String syaratNyataPlot) {
        this.syaratNyataPlot = syaratNyataPlot;
    }

    public String getSekatanPlot() {
        return sekatanPlot;
    }

    public void setSekatanPlot(String sekatanPlot) {
        this.sekatanPlot = sekatanPlot;
    }

    public String getBumiPlot() {
        return bumiPlot;
    }

    public void setBumiPlot(String bumiPlot) {
        this.bumiPlot = bumiPlot;
    }

    public String getNopt() {
        return nopt;
    }

    public void setNopt(String nopt) {
        this.nopt = nopt;
    }

    public String getNopelanakui() {
        return nopelanakui;
    }

    public void setNopelanakui(String nopelanakui) {
        this.nopelanakui = nopelanakui;
    }

    public String getPelantif() {
        return pelantif;
    }

    public void setPelantif(String pelantif) {
        this.pelantif = pelantif;
    }

    public String getJenispelan() {
        return jenispelan;
    }

    public void setJenispelan(String jenispelan) {
        this.jenispelan = jenispelan;
    }

    public BigDecimal getLuas() {
        return luas;
    }

    public void setLuas(BigDecimal luas) {
        this.luas = luas;
    }

    public String getUnitukur() {
        return unitukur;
    }

    public void setUnitukur(String unitukur) {
        this.unitukur = unitukur;
    }

    public FileBean getFile() {
        return file;
    }

    public void setFile(FileBean file) {
        this.file = file;
    }

}
