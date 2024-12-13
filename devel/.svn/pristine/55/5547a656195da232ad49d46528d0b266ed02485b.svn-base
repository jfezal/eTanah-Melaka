/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common.b1;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanB1DAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.gis.GISPelanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonanB1;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotKpsn;
import etanah.model.PermohonanPlotPelan;
import etanah.model.gis.GISPelan;
import etanah.model.gis.GISPelanPK;
import etanah.service.common.PelanB1Service;
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
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author john
 */
@UrlBinding("/common/b1")
public class PaparanMaklumatB1ActionBean extends AbleActionBean {

    @Inject
    PelanB1Service pelanB1Service;
    @Inject
    HakmilikPermohonanB1DAO hakmilikPermohonanB1DAO;
    @Inject
    KodUOMDAO kodUOMDAO;
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
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    private String nolot;
    private BigDecimal luass;
    private String nopelanakui;
    private String unitukur;
    private FileBean file;
    private String jenispelan;
    private List<SenaraiHakmilikB1> senaraiHakmilikQt;
    private List<Hakmilik> senaraiHakmilikQT;
    private String idPermohonan;
//    private String idPlot;
    String[] idMhB1;
    String[] noLot;
    String[] luas;
    String[] kodLuas;
    String[] noPelanAkui;
    Long idPlot;
    String idHakmilikQT;
private String jenisHakmilik;
    private String daerah;
    private String bpm;
    private String seksyen;
    private Long hakmilikQT;
    private Long pelanMuatNaik;
    private Long balPelan;

    private static final Logger LOG = Logger.getLogger(PaparanMaklumatB1ActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start");
        return new JSP("common/b1/paparan_maklumat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        PermohonanPlotPelan plot = pelanB1Service.findPlotPelanBIdMohon(idPermohonan);
        senaraiHakmilikQT = pelanB1Service.findListQT(idPermohonan);
        idPlot = plot.getIdPlot();
        PermohonanPlotKpsn kpsn = permohonanPlotPelanDAO.findById(Long.valueOf(idPlot)).getPermohonanPlotKpsn();
        jenisHakmilik = kpsn.getKodHakmilikTetap().getNama();
        daerah = kpsn.getDaerah().getNama();
        bpm = kpsn.getBpm().getNama();
        seksyen = kpsn.getSeksyen() != null ? kpsn.getSeksyen().getNama() : "Tiada";
        
        pelanMuatNaik = pelanB1Service.countPelanMuatNaik(idPermohonan);
        hakmilikQT = pelanB1Service.countRowHakmilikB1(idPermohonan);
        balPelan = pelanB1Service.countTiadaPelan(idPermohonan);

    }

    public Resolution papar() {
        senaraiHakmilikQt = pelanB1Service.findSenaraiByidPermohonan(idPermohonan);

        setPage(senaraiHakmilikQt.size());
        int a = get__pg_start();
        int b = get__pg_max_records();

        return new JSP("common/b1/sedia_b1_pelan_kemaskini.jsp").addParameter("popup", "true");

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

    public Resolution simpanSenarai() {
        idMhB1 = getContext().getRequest().getParameterValues("idMhB1");
        luas = getContext().getRequest().getParameterValues("luas");
        noLot = getContext().getRequest().getParameterValues("noLot");
        kodLuas = getContext().getRequest().getParameterValues("kodLuas");
        noPelanAkui = getContext().getRequest().getParameterValues("noPelanAkui");

        Map m = getContext().getRequest().getParameterMap();

        for (int i = 0; i < idMhB1.length; i++) {
            String k = kodLuas[i];
            String l = luas[i];
            String n = noLot[i];
            String p = noPelanAkui[i];
            HakmilikPermohonanB1 mhB1 = hakmilikPermohonanB1DAO.findById(Long.parseLong(idMhB1[i]));

            if (StringUtils.isNotBlank(k)) {
                mhB1.setKodUnitLuas(kodUOMDAO.findById(k));
            }
            if (StringUtils.isNotBlank(l)) {
                mhB1.setLuasTerlibat(BigDecimal.valueOf(Long.valueOf(l)));
            }
            if (StringUtils.isNotBlank(n)) {
                mhB1.setNoLot(n);
            }
            if (StringUtils.isNotBlank(p)) {
                mhB1.setNoPelanAkui(p);
            }
            pelanB1Service.simpanHakmilikPermohonanB1(mhB1);

        }

        return new RedirectResolution("/common/b1?papar&idPermohonan=" + idPermohonan).addParameter("popup", "true");
//        return new JSP("pembangunan/common/sedia_b2_pelan_kemaskini.jsp").addParameter("popup", "true");
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

        pk.setNoLot(nolot);
        pk.setJenisPelan("B1");
        pelan.setPelanGISPK(pk);

        pelan.setLuas(luass);
        pelan.setNoPelanAkui(nopelanakui);
//        pelan.setPelanTif(pelantif);
        pelan.setUnitUkur(unitukur);
        pelan.setTrhKemaskini(new Date());
        if (file != null) {
            pelan.setPelanTif(File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "") + File.separator + file.getFileName().toLowerCase());
        }

//        pelan.setTrhKemaskini(new Date());
        pelanGISDAO.saveOrUpdate(pelan);
        if (file != null) {
            String loc = conf.getPelanPath() + File.separator + pelan.getPelanGISPK().getJenisPelan() + File.separator + kd.getKod() + File.separator + bpm.getbandarPekanMukim() + (seksyen1 != null ? File.separator + seksyen1.getSeksyen() : "");
            FileUtil fileUtil = new FileUtil(loc);
            System.out.print("sssss" + file.getFileName().toLowerCase());
            fileUtil.saveFile(file.getFileName().toLowerCase(), file.getInputStream());

        }
        HakmilikPermohonanB1 mhB1 = pelanB1Service.findHakmilikB1ByIdHakmilik(idHakmilikQT, idPermohonan);
        mhB1.setKodUnitLuas(kodUOMDAO.findById(unitukur));
        mhB1.setLuasTerlibat(luass);
        mhB1.setNoLot(nolot);
        mhB1.setNoPelanAkui(nopelanakui);
        pelanB1Service.simpanHakmilikPermohonanB1(mhB1);
        tx.commit();

        return new JSP("common/b1/paparan_maklumat.jsp").addParameter("tab", "true");
    }

    public List getSenaraiHakmilikQt() {
        return senaraiHakmilikQt;
    }

    public void setSenaraiHakmilikQt(List senaraiHakmilikQt) {
        this.senaraiHakmilikQt = senaraiHakmilikQt;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String[] getIdMhB1() {
        return idMhB1;
    }

    public void setIdMhB1(String[] idMhB1) {
        this.idMhB1 = idMhB1;
    }

    public String[] getNoLot() {
        return noLot;
    }

    public void setNoLot(String[] noLot) {
        this.noLot = noLot;
    }

    public String[] getLuas() {
        return luas;
    }

    public void setLuas(String[] luas) {
        this.luas = luas;
    }

    public String[] getKodLuas() {
        return kodLuas;
    }

    public void setKodLuas(String[] kodLuas) {
        this.kodLuas = kodLuas;
    }

    public String[] getNoPelanAkui() {
        return noPelanAkui;
    }

    public void setNoPelanAkui(String[] noPelanAkui) {
        this.noPelanAkui = noPelanAkui;
    }

    public Long getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(Long idPlot) {
        this.idPlot = idPlot;
    }

    public String getNolot() {
        return nolot;
    }

    public void setNolot(String nolot) {
        this.nolot = nolot;
    }

    public BigDecimal getLuass() {
        return luass;
    }

    public void setLuass(BigDecimal luass) {
        this.luass = luass;
    }

    public String getNopelanakui() {
        return nopelanakui;
    }

    public void setNopelanakui(String nopelanakui) {
        this.nopelanakui = nopelanakui;
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

    public String getIdHakmilikQT() {
        return idHakmilikQT;
    }

    public void setIdHakmilikQT(String idHakmilikQT) {
        this.idHakmilikQT = idHakmilikQT;
    }

    public List<Hakmilik> getSenaraiHakmilikQT() {
        return senaraiHakmilikQT;
    }

    public void setSenaraiHakmilikQT(List<Hakmilik> senaraiHakmilikQT) {
        this.senaraiHakmilikQT = senaraiHakmilikQT;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getBpm() {
        return bpm;
    }

    public void setBpm(String bpm) {
        this.bpm = bpm;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public Long getHakmilikQT() {
        return hakmilikQT;
    }

    public void setHakmilikQT(Long hakmilikQT) {
        this.hakmilikQT = hakmilikQT;
    }

    public Long getBalPelan() {
        return balPelan;
    }

    public void setBalPelan(Long balPelan) {
        this.balPelan = balPelan;
    }

    public Long getPelanMuatNaik() {
        return pelanMuatNaik;
    }

    public void setPelanMuatNaik(Long pelanMuatNaik) {
        this.pelanMuatNaik = pelanMuatNaik;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }
    
    

}
