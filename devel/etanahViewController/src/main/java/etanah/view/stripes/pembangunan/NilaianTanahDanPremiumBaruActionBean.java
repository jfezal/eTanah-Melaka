package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodPemilikanDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pengguna;
import etanah.model.KodCawangan;
import etanah.model.InfoAudit;
import etanah.model.KodPemilikan;
import etanah.model.KodTuntut;
import etanah.model.Pemohon;
import java.math.BigDecimal;
import etanah.view.etanahActionBeanContext;
import etanah.service.PembangunanService;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/nilaianTanahDanPremiumBaru")
public class NilaianTanahDanPremiumBaruActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(NilaianTanahDanPremiumBaruActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
    @Inject
    PembangunanService pembangunanServ;
    @Inject
    etanah.Configuration conf;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    KodPemilikanDAO kodPemilikanDAO;
    private Permohonan permohonan;
    private PermohonanPlotPelan permohonanPlotPelan;
    private KodPemilikan kodPemilikan;
    private BigDecimal nilaianTanah;
    private BigDecimal premium;
    private PermohonanTuntutanKos permohonantuntutkos;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private String premiumBaru;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhNilaian;
    private String nomborRujukan;
    private String radio1;
    private String idPermohonan;
    private List<HakmilikPermohonan> listSenaraiMH;
    private PermohonanTuntutanKos findKodTuntutPremium;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

//        listtuntutankos = permohonanTuntutanKosDAO.findByEqualCriterias(tname, model, null);

        listtuntutankos = pembangunanServ.findTuntutByIdMohon(idPermohonan);

        if (!(listtuntutankos.isEmpty())) {
            for (int i = 0; i < listtuntutankos.size(); i++) {
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if (permohonantuntutkos.getKodTuntut() != null) {
//                    if(permohonantuntutkos.getKodTuntut().getNama().equals("Premium")){
                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")) {
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
        }

        HakmilikPermohonan hp = new HakmilikPermohonan();
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
            hp = senaraiHakmilikPermohonan.get(0);
        }
        nilaianTanah = hp.getNilaianJpph();
        nomborRujukan = hp.getNomborRujukan();
        radio1 = hp.getPenarikBalikan();
        try {
            if (hp.getTarikhAwalDaftarGeran() != null) {
                tarikhNilaian = sdf.format(hp.getTarikhAwalDaftarGeran());
                LOG.info("---rehydrade------tarikhNilaian-----------:" + tarikhNilaian);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PermohonanPlotPelan> permohonanPlotPelanList = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
        if (permohonanPlotPelanList.size() > 0) {
            permohonanPlotPelan = permohonanPlotPelanList.get(0);
            if (permohonanPlotPelan.getKeteranganKadarPremium() != null) {
                premiumBaru = permohonanPlotPelan.getKeteranganKadarPremium();
            }
        } else {
            premiumBaru = hp.getKeteranganKadarPremium();
        }
    }

    public Resolution simpanHantarJPPH() {

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        radio1 = getContext().getRequest().getParameter("radio1");
        if (idPermohonan != null) {
            LOG.debug("Here !!!!");
            listSenaraiMH = pembangunanServ.findHakmilikPermohonanByIdPermohonan(idPermohonan);
            if (listSenaraiMH.size() > 0) {
                for (HakmilikPermohonan pp : listSenaraiMH) {
                    pp.setPenarikBalikan(radio1);
                    pembangunanServ.simpanHakmilikPermohonan(pp);
                }
                LOG.debug("Done !!!!");
            }
        } else {
            LOG.debug("x masuk boh");
        }
        rehydrate();
        return showForm();

    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if (premium == null) {
            premium = new BigDecimal(0.00);
        }

        findKodTuntutPremium = pembangunanServ.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getIdPermohonan(), "DEV04");
        if (findKodTuntutPremium != null) {
            System.out.println("--HERE--");
            findKodTuntutPremium.setAmaunTuntutan(premium);
            ia = findKodTuntutPremium.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            findKodTuntutPremium.setInfoAudit(ia);
            findKodTuntutPremium.setPermohonan(permohonan);
            findKodTuntutPremium.setCawangan(caw);
            pembangunanServ.simpanPermohonanTuntutanKos(findKodTuntutPremium);
        } else {
            System.out.println("--!HERE--");
            KodTuntut kodTuntut = new KodTuntut();
            kodTuntut = kodTuntutDAO.findById("DEV04");
            permohonantuntutkos = new PermohonanTuntutanKos();
            permohonantuntutkos.setInfoAudit(ia);
            permohonantuntutkos.setKodTuntut(kodTuntut);
            permohonantuntutkos.setItem(kodTuntut.getNama());
            permohonantuntutkos.setAmaunTuntutan(premium);
            permohonantuntutkos.setPermohonan(permohonan);
            permohonantuntutkos.setCawangan(caw);
            permohonantuntutkos.setKodTransaksi(permohonan.getKodUrusan().getKodTransaksi());
            pembangunanServ.simpanPermohonanTuntutanKos(permohonantuntutkos);
        }

        HakmilikPermohonan hp = new HakmilikPermohonan();
        List<HakmilikPermohonan> senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
        if (senaraiHakmilikPermohonan != null && senaraiHakmilikPermohonan.size() > 0) {
            hp = senaraiHakmilikPermohonan.get(0);
        }

        if (hp != null) {
            hp.setNilaianJpph(nilaianTanah);
            hp.setKadarPremium(premium);
            hp.setNomborRujukan(nomborRujukan);
            try {
                if (tarikhNilaian != null) {
                    hp.setTarikhAwalDaftarGeran((Date) sdf.parse(tarikhNilaian));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOG.info("-------tarikhNilaian-----------:" + tarikhNilaian);
            pembangunanServ.simpanHakmilikPermohonan(hp);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPremiumBaru() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        permohonan = permohonanDAO.findById(idPermohonan);


        String premium = getContext().getRequest().getParameter("premiumBaru");
        List<PermohonanPlotPelan> permohonanPlotPelanList = pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
        if (permohonanPlotPelanList.size() > 0) {
            permohonanPlotPelan = permohonanPlotPelanList.get(0);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            permohonanPlotPelan.setPermohonan(permohonan);
            permohonanPlotPelan.setCawangan(pengguna.getKodCawangan());
            permohonanPlotPelan.setKeteranganKadarPremium(premium);
            permohonanPlotPelan.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermohonanPlotPelan(permohonanPlotPelan);

        } else {
            permohonanPlotPelan = new PermohonanPlotPelan();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanPlotPelan.setPermohonan(permohonan);
            permohonanPlotPelan.setCawangan(pengguna.getKodCawangan());
            permohonanPlotPelan.setKeteranganKadarPremium(premium);
            permohonanPlotPelan.setNoKe(0);
            permohonanPlotPelan.setNoKe(0);
            KodPemilikan kp = kodPemilikanDAO.findById('H');
            permohonanPlotPelan.setPemilikan(kp);
            permohonanPlotPelan.setInfoAudit(infoAudit);
            pembangunanServ.simpanPermohonanPlotPelan(permohonanPlotPelan);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pembangunan/melaka/nilaianTanah_dan_PremiumBaru.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getNilaianTanah() {
        return nilaianTanah;
    }

    public void setNilaianTanah(BigDecimal nilaianTanah) {
        this.nilaianTanah = nilaianTanah;
    }

    public PermohonanPlotPelan getPermohonanPlotPelan() {
        return permohonanPlotPelan;
    }

    public void setPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        this.permohonanPlotPelan = permohonanPlotPelan;
    }

    public String getPremiumBaru() {
        return premiumBaru;
    }

    public void setPremiumBaru(String premiumBaru) {
        this.premiumBaru = premiumBaru;
    }

    public KodPemilikan getKodPemilikan() {
        return kodPemilikan;
    }

    public void setKodPemilikan(KodPemilikan kodPemilikan) {
        this.kodPemilikan = kodPemilikan;
    }

    public String getTarikhNilaian() {
        return tarikhNilaian;
    }

    public void setTarikhNilaian(String tarikhNilaian) {
        this.tarikhNilaian = tarikhNilaian;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public String getRadio1() {
        return radio1;
    }

    public void setRadio1(String radio1) {
        this.radio1 = radio1;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getListSenaraiMH() {
        return listSenaraiMH;
    }

    public void setListSenaraiMH(List<HakmilikPermohonan> listSenaraiMH) {
        this.listSenaraiMH = listSenaraiMH;
    }

    public PermohonanTuntutanKos getFindKodTuntutPremium() {
        return findKodTuntutPremium;
    }

    public void setFindKodTuntutPremium(PermohonanTuntutanKos findKodTuntutPremium) {
        this.findKodTuntutPremium = findKodTuntutPremium;
    }
}
