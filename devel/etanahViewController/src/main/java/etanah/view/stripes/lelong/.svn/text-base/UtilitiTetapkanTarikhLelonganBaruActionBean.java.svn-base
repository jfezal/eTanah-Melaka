/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.EnkuiriDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.JuruLelongDAO;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.LelonganDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.JuruLelong;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.report.ReportUtil;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/utiliti_maklumat_lelongan_awam")
public class UtilitiTetapkanTarikhLelonganBaruActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(UtilitiTetapkanTarikhLelonganBaruActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    LelonganDAO lelonganDAO;
    @Inject
    JuruLelongDAO jurulelongDAO;
    @Inject
    EnkuiriDAO enkuiriDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    EnkuiriService ES;
    @Inject
    private ReportUtil reportUtil;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    CalenderManager manager;
    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private Permohonan permohonan;
    private JuruLelong jurulelong;
    private Pihak pihak;
    private String idLelong;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan1;
    private List<Lelongan> senaraiLelongan2;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri1;
    private List<FasaPermohonan> senaraifasamohon;
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    private List<BigDecimal> listHarga = new ArrayList<BigDecimal>();
    private List<BigDecimal> listDeposit = new ArrayList<BigDecimal>();
    private static List<Lelongan> listLelonganStatic;
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String tarikhLelong;
    private String tarikhEnkuiri;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tempat;
    private String tarikhAkhirBayaran;
    private String ejaanHarga;
    private String disabled;
    private String disabled1;
    private Integer bil;
    private List<BigDecimal> hargaRizab = new ArrayList<BigDecimal>();
    private BigDecimal baki;
    private List<BigDecimal> deposit = new ArrayList<BigDecimal>();
    private String status;
    private List<BigDecimal> hargaRizab1 = new ArrayList<BigDecimal>();
    private List<BigDecimal> deposit1 = new ArrayList<BigDecimal>();
    private List<String> idHakmilik = new ArrayList<String>();
    private List<String> idHakmilik2 = new ArrayList<String>();
    private String idPermohonan;
    private boolean flag = false;
    private long idEnkuiri;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private boolean button = false;
    private String idHak;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;

    @DefaultHandler
    public Resolution selectTransaction() {
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

    public Resolution showForm3() {
        LOG.info("---showForm---");

        etanahActionBeanContext ctx = new etanahActionBeanContext();
        ctx = (etanahActionBeanContext) getContext();
        listCalender = manager.getALLEnkuri(ctx.getKodCawangan().getKod());
        listCalender2 = manager.getALLLelongan(ctx.getKodCawangan().getKod());

        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        getContext().getRequest().setAttribute("idPermohonan", idPermohonan);
        LOG.info("idPermohonan A :" + idPermohonan);
        
        permohonan = permohonanDAO.findById(idPermohonan);
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
        }

        return new ForwardResolution("/WEB-INF/jsp/lelong/calender_lelong7.jsp").addParameter("popup", "true");
    }

    public Resolution find() {
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {

                senaraiEnkuiri = enService.getListEnkuiri(idPermohonan);
                for (Enkuiri ee : senaraiEnkuiri) {
                    enkuiri = ee;
                    idEnkuiri = ee.getIdEnkuiri();
                }

                Session sss = sessionProvider.get();
                Query qqq = sss.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
                qqq.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
                senaraiLelongan = qqq.list();
                LOG.debug("idEnkuiri() :" + enkuiri.getIdEnkuiri());

                Session sss1 = sessionProvider.get();
                Query qqq1 = sss1.createQuery("from Lelongan l where l.enkuiri.idEnkuiri = :idEnkuiri order by l.idLelong asc");
                qqq1.setString("idEnkuiri", enkuiri.getIdEnkuiri() + "");
                senaraiLelongan2 = qqq1.list();


                senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
                LOG.info("senaraiLelongan1.size : " + senaraiLelongan.size());
                for (Lelongan ee : senaraiLelongan) {
                    List<HakmilikPermohonan> listHakmilikPermohonan = lelongService.getPermohonanByIdmohon(ee.getEnkuiri().getPermohonan().getIdPermohonan());
                    HakmilikPermohonan kk = listHakmilikPermohonan.get(0);
                    LOG.info("idHakmilik : " + kk.getHakmilik().getIdHakmilik());
                    idHakmilik.add(kk.getHakmilik().getIdHakmilik());
                }
            } else {
                addSimpleError("IdPermohonan Salah.Sila Masukkan IdPermohonan Skali Lagi");
                return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
            }

            enkuiri = lelongService.findEnkuiri(idPermohonan);
            listLel = lelongService.getLeloganASC(idPermohonan);

            if (permohonan.getSenaraiHakmilik().size() > 1) {
                //berasingan
                if (enkuiri.getCaraLelong().equals("A")) {
                    getContext().getRequest().setAttribute("same", Boolean.FALSE);
                }
                //bersama
                listLelongan = new ArrayList<Lelongan>();
                if (enkuiri.getCaraLelong().equals("B")) {
                    getContext().getRequest().setAttribute("same", Boolean.TRUE);
                    listLelongan.add(lelong);
                    StringBuilder sb = new StringBuilder();
                    if (permohonan != null) {
                        for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                            Hakmilik h = hp.getHakmilik();
                            if (sb.length() > 0) {
                                sb.append("<br>");
                            }
                            sb.append(h.getIdHakmilik());
                        }
                    }
                    idHak = sb.toString();
                }

            }

            if (permohonan.getSenaraiHakmilik().size() == 1) {
                getContext().getRequest().setAttribute("same", Boolean.TRUE);
                if (enkuiri.getCaraLelong() == null) {
                    enkuiri.setCaraLelong("B");
                    lelongService.saveOrUpdate(enkuiri);
                }
                //bersama
                listLelongan = new ArrayList<Lelongan>();
                senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
                for (Lelongan ll : senaraiLelongan) {
                    if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                        lelong = ll;
                        break;
                    }
                }
                listLelongan.add(lelong);
                StringBuilder sb = new StringBuilder();
                if (permohonan != null) {
                    for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
                        Hakmilik h = hp.getHakmilik();
                        if (sb.length() > 0) {
                            sb.append("<br>");
                        }
                        sb.append(h.getIdHakmilik());
                    }
                }
                idHak = sb.toString();
            }
            //bersama
            int j = 0;
            for (Lelongan lelongan : listLelongan) {
                hargaRizab1.add(j, lelongan.getEnkuiri().getHargaRizab());
                deposit1.add(j, lelongan.getEnkuiri().getDeposit());
                j++;
            }

            //asing
            int k = 0;
            for (Lelongan lelongan : listLel) {
                hargaRizab.add(k, lelongan.getHargaRizab());
                deposit.add(k, lelongan.getDeposit());
                k++;
            }

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                negori = true;
                enkuiri.setAmaunTunggakan(amaunTunggakan);
                lelongService.saveOrUpdate(enkuiri);
            } else {
                lelongService.saveOrUpdate(enkuiri);
            }
        }

        if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
        }

        try {
            senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
            if (!senaraiLelongan.isEmpty()) {
                for (Lelongan ll : senaraiLelongan) {
                    if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                        lelong = ll;
                        break;
                    }
                }
                tarikhLelong = sdf.format(lelong.getTarikhLelong());
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
            }

            if (lelong != null && lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
        flag = true;
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

    //berasingan utk n.melaka
    public Resolution simpanLelong() throws ParseException {

        LOG.info("----berasingan------");

        permohonan = permohonanDAO.findById(idPermohonan);
        enkuiri = lelongService.findEnkuiri(idPermohonan);

        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
        listLel = lelongService.getLeloganASC(idPermohonan);
        LOG.info("senaraiLelongan1 asing.size : " + senaraiLelongan.size());
        for (Lelongan ee : senaraiLelongan) {
            List<HakmilikPermohonan> listHakmilikPermohonan = lelongService.getPermohonanByIdmohon(ee.getEnkuiri().getPermohonan().getIdPermohonan());
            HakmilikPermohonan kk = listHakmilikPermohonan.get(0);
            LOG.info("idHakmilik : " + kk.getHakmilik().getIdHakmilik());
            idHakmilik.add(kk.getHakmilik().getIdHakmilik());
        }

        LOG.info("listLel : " + listLel.size());
        for (int i = 0; i < listLel.size(); i++) {
            LOG.info("------1----------");
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.info("tarikhLelong :" + tarikhLelong);

            try {
                lel.setTarikhLelong(sdf.parse(tarikhLelong));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            try {
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            lel.setHargaRizab(hargaRizab.get(i));
            lel.setDeposit(deposit.get(i));
            LOG.debug("hargarizab asing :" + lel.getHargaRizab());
            LOG.debug("hargadeposit asing :" + lel.getDeposit());

            lel.setTempat(lelong.getTempat());

            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setKodStatusLelongan(ksl);
//            lel.setBil(1);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }
//        enkuiri.setTarikhWarta(sdf1.parse(tarikhWarta));
        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            lelongService.saveOrUpdate(enkuiri);
        } else {
            lelongService.saveOrUpdate(enkuiri);
        }
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

    //bersama utk n.melaka
    public Resolution saveBersama() throws ParseException {
        LOG.info("----bersama------");
        permohonan = permohonanDAO.findById(idPermohonan);
        enkuiri = lelongService.findEnkuiri(idPermohonan);

        Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());

        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
        LOG.info("senaraiLelongan1 sama.size : " + senaraiLelongan.size());
        listLel = lelongService.getLeloganASC(idPermohonan);

        for (int i = 0; i < listLel.size(); i++) {
            LOG.info("------1----------");
            Lelongan lel = listLel.get(i);
            LOG.info("Lelong : " + lel.getIdLelong());
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            tarikhLelong = tarikhLelong + " " + jam + ":" + " " + minit + " " + ampm;
            LOG.info("tarikhLelong :" + tarikhLelong);
            try {
                lel.setTarikhLelong(sdf.parse(tarikhLelong));
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error(ex);
            }
            lel.getEnkuiri().setHargaRizab(hargaRizab1.get(i));
            lel.getEnkuiri().setDeposit(deposit1.get(i));
//            lel.setDeposit(enkuiri.getDeposit());
//            lel.setHargaRizab(enkuiri.getHargaRizab());
            LOG.debug("hargarizab :" + lel.getEnkuiri().getHargaRizab());
            LOG.debug("hargadeposit :" + lel.getEnkuiri().getDeposit());
            lel.setTempat(lelong.getTempat());
            KodStatusLelongan ksl = kodStatusLelonganDAO.findById("AK");
//            ksl.setKod("AK");
            lel.setKodStatusLelongan(ksl);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }
        enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
        //negeri malake amaun hutang tertunggak
//        enkuiri.setTarikhWarta(sdf1.parse(tarikhWarta));
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            LOG.info("------2----------");
            lelongService.saveOrUpdate(enkuiri);
        } else {
            LOG.info("------3----------");
            lelongService.saveOrUpdate(enkuiri);
        }
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/lelong/UtilitiTetapkanTarikhLelonganBaru.jsp");
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<Enkuiri> getSenaraiEnkuiri() {
        return senaraiEnkuiri;
    }

    public void setSenaraiEnkuiri(List<Enkuiri> senaraiEnkuiri) {
        this.senaraiEnkuiri = senaraiEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(String idLelong) {
        this.idLelong = idLelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public Integer getBil() {
        return bil;
    }

    public void setBil(Integer bil) {
        this.bil = bil;
    }

    public BigDecimal getBaki() {
        return baki;
    }

    public void setBaki(BigDecimal baki) {
        this.baki = baki;
    }

    public List<BigDecimal> getDeposit() {
        return deposit;
    }

    public void setDeposit(List<BigDecimal> deposit) {
        this.deposit = deposit;
    }

    public List<BigDecimal> getHargaRizab() {
        return hargaRizab;
    }

    public void setHargaRizab(List<BigDecimal> hargaRizab) {
        this.hargaRizab = hargaRizab;
    }

    public String getEjaanHarga() {
        return ejaanHarga;
    }

    public void setEjaanHarga(String ejaanHarga) {
        this.ejaanHarga = ejaanHarga;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled1() {
        return disabled1;
    }

    public void setDisabled1(String disabled1) {
        this.disabled1 = disabled1;
    }

    public List<Lelongan> getSenaraiLelongan1() {
        return senaraiLelongan1;
    }

    public void setSenaraiLelongan1(List<Lelongan> senaraiLelongan1) {
        this.senaraiLelongan1 = senaraiLelongan1;
    }

    public List<Enkuiri> getSenaraiEnkuiri1() {
        return senaraiEnkuiri1;
    }

    public void setSenaraiEnkuiri1(List<Enkuiri> senaraiEnkuiri1) {
        this.senaraiEnkuiri1 = senaraiEnkuiri1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(List<String> idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<String> getIdHakmilik2() {
        return idHakmilik2;
    }

    public void setIdHakmilik2(List<String> idHakmilik2) {
        this.idHakmilik2 = idHakmilik2;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Lelongan> getSenaraiLelongan2() {
        return senaraiLelongan2;
    }

    public void setSenaraiLelongan2(List<Lelongan> senaraiLelongan2) {
        this.senaraiLelongan2 = senaraiLelongan2;
    }

    public long getIdEnkuiri() {
        return idEnkuiri;
    }

    public void setIdEnkuiri(long idEnkuiri) {
        this.idEnkuiri = idEnkuiri;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public List<BigDecimal> getListDeposit() {
        return listDeposit;
    }

    public void setListDeposit(List<BigDecimal> listDeposit) {
        this.listDeposit = listDeposit;
    }

    public List<BigDecimal> getListHarga() {
        return listHarga;
    }

    public void setListHarga(List<BigDecimal> listHarga) {
        this.listHarga = listHarga;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }

    public static List<Lelongan> getListLelonganStatic() {
        return listLelonganStatic;
    }

    public static void setListLelonganStatic(List<Lelongan> listLelonganStatic) {
        UtilitiTetapkanTarikhLelonganBaruActionBean.listLelonganStatic = listLelonganStatic;
    }

    public List<BigDecimal> getDeposit1() {
        return deposit1;
    }

    public void setDeposit1(List<BigDecimal> deposit1) {
        this.deposit1 = deposit1;
    }

    public List<BigDecimal> getHargaRizab1() {
        return hargaRizab1;
    }

    public void setHargaRizab1(List<BigDecimal> hargaRizab1) {
        this.hargaRizab1 = hargaRizab1;
    }

    public List<CalenderLelong> getListCalender() {
        return listCalender;
    }

    public void setListCalender(List<CalenderLelong> listCalender) {
        this.listCalender = listCalender;
    }

    public List<CalenderLelong> getListCalender2() {
        return listCalender2;
    }

    public void setListCalender2(List<CalenderLelong> listCalender2) {
        this.listCalender2 = listCalender2;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }
}
