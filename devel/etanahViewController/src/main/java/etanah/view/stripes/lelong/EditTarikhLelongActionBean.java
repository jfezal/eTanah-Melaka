/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodStatusLelonganDAO;
import etanah.dao.KodSukuDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Enkuiri;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusLelongan;
import etanah.model.KodSuku;
import etanah.model.Lelongan;
import etanah.model.LelonganSuku;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.StreamingResolution;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/edit_tarikh")
public class EditTarikhLelongActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(EditTarikhLelongActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    KodSukuDAO kodSukuDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    CalenderManager manager;
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan3;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri3;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private Enkuiri enkuiri;
    private Lelongan lelong;
    private String tarikhLelong;
    private String jam;
    private String minit;
    private String ampm;
    private String tarikhAkhirBayaran;
    private String idHak;
    private String tarikhEnkuiri;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private String kodsuku;
    private List<KodSuku> list;
    private List<LelonganSuku> listSuku;

    @DefaultHandler
    public Resolution showFormA() {

        if (idPermohonan != null) {
            senaraiLelongan = lelongService.getLeloganDesc(enkuiri.getIdEnkuiri());
            if ("05".equals(conf.getProperty("kodNegeri")) && !"PJTA".equals(permohonan.getKodUrusan().getKod())) {
                //negeri9
                addSimpleMessage("Tarikh Lelongan Awam Hendaklah Tidak Kurang Daripada Satu Bulan Selepas Tarikh Perintah.");
            }
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //melaka
                addSimpleMessage("Tarikh Lelongan Awam Hendaklah Tidak Kurang Satu Bulan Dari Tarikh Akhir Siasatan.");
            }
            if ("PJTA".equals(permohonan.getKodUrusan().getKod())) {
                //tanah adat
                addSimpleMessage("Tarikh Lelongan Awam Hendaklah Tidak Kurang Daripada Enam Bulan Selepas Tarikh Perintah.");
            }

        }
        rehydrate();
        return new JSP("lelong/edit_tarikh_lelong.jsp").addParameter("tab", "true");
    }

    public Resolution showFormB() {
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showFormC() {
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong_edit_adat.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> listE = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!listE.isEmpty()) {
                            enkuiri = listE.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> listE = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!listE.isEmpty()) {
                            enkuiri = listE.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
            senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
            senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

            for (Lelongan ll : senaraiLelongan) {
                if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                    lelong = ll;
                    break;
                }
            }

            listLel = lelongService.getLeloganASC(idPermohonan);
            //for negeri melake
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                negori = true;
                if (enkuiri.getAmaunTunggakan() != null) {
                    amaunTunggakan = enkuiri.getAmaunTunggakan();
                }
            } else {
                //negeri 9
                negori = false;
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

            if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
            }

            try {
                if (lelong != null && lelong.getTarikhLelong() != null) {
//                            tarikhLelong = sdf.format(lelong.getTarikhLelong());
                    tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                    tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                    jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                    minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                    ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
                    listSuku = lelongService.listLelongSuku(lelong.getIdLelong());
                }

            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
        list = new ArrayList<KodSuku>();
        list = kodSukuDAO.findAll();
        LOG.info("list.size() : " + list.size());
    }
    public Resolution genReportJual() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";

        try {
            String gen = "LLGSurat16HPenyerah_MLK.rdf";
            String code = "S16H";
            LOG.info("genReportFromXML");
            lelongService.reportGen(permohonan, gen, code, pengguna);
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    //berasingan utk n.melaka
    public Resolution simpanLelong() throws ParseException {

        InfoAudit ia = new InfoAudit();
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

            lel.setTempat(lelong.getTempat());

            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setKodStatusLelongan(ksl);
//            lel.setBil(1);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }

        //PJTA SUKU
        if (permohonan.getKodUrusan().getKod().equals("PJTA")) {
            LOG.info("-----simpan suku--------");
            if (listSuku != null) {
                for (LelonganSuku ls : listSuku) {
                    lelongService.delete(ls);
                }
            }

            KodSuku ks = null;
            LelonganSuku ls = null;
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            for (int i = 0; i < list.size(); i++) {
                String kodSuku = getContext().getRequest().getParameter("kodsuku" + i);
                if (kodSuku != null) {
                    ks = kodSukuDAO.findById(kodSuku);
                    LOG.info("suku--- " + ks.getKod());
                    ls = new LelonganSuku();
                    ls.setInfoAudit(ia);
                    ls.setLelongan(lelong);
                    ls.setCawangan(permohonan.getCawangan());
                    ls.setKodSuku(ks);
                    lelongService.saveLelonganSuku(ls);
                }
            }
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
        LOG.info("genReport");
        //edit by nur.aqilah
        String gen = "LLGBorang16HPenyerah_MLK";
        String code = "16H";
        lelongService.reportGen(permohonan, gen, code, pengguna);
        rehydrate();
        showFormA();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/edit_tarikh_lelong.jsp").addParameter("tab", "true");
    }

    //bersama utk n.melaka
    public Resolution saveBersama() throws ParseException {

        InfoAudit ia = new InfoAudit();
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
            lel.setDeposit(enkuiri.getDeposit());
            lel.setHargaRizab(enkuiri.getHargaRizab());
            lel.setTempat(lelong.getTempat());
            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setKodStatusLelongan(ksl);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }

        //PJTA SUKU
        if (permohonan.getKodUrusan().getKod().equals("PJTA")) {
            LOG.info("-----simpan suku--------");
            if (listSuku != null) {
                for (LelonganSuku ls : listSuku) {
                    lelongService.delete(ls);
                }
            }
            KodSuku ks = null;
            LelonganSuku ls = null;
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            for (int i = 0; i < list.size(); i++) {
                String kodSuku = getContext().getRequest().getParameter("kodsuku" + i);
                if (kodSuku != null) {
                    ks = kodSukuDAO.findById(kodSuku);
                    LOG.info("suku--- " + ks.getKod());
                    ls = new LelonganSuku();
                    ls.setInfoAudit(ia);
                    ls.setLelongan(lelong);
                    ls.setCawangan(permohonan.getCawangan());
                    ls.setKodSuku(ks);
                    lelongService.saveLelonganSuku(ls);
                }
            }
        }

        enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
        //negeri malake amaun hutang tertunggak
//        enkuiri.setTarikhWarta(sdf1.parse(tarikhWarta));
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            lelongService.saveOrUpdate(enkuiri);
        } else {
            lelongService.saveOrUpdate(enkuiri);
        }
        LOG.info("genReport");
        //edit by nur.aqilah
        String gen = "LLGBorang16HPenyerah_MLK";
        String code = "16H";
        lelongService.reportGen(permohonan, gen, code, pengguna);
        rehydrate();
        showFormA();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/edit_tarikh_lelong.jsp").addParameter("tab", "true");
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
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

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
    }

    public List<Lelongan> getListLelongan() {
        return listLelongan;
    }

    public void setListLelongan(List<Lelongan> listLelongan) {
        this.listLelongan = listLelongan;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
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

    public List<Enkuiri> getSenaraiEnkuiri3() {
        return senaraiEnkuiri3;
    }

    public void setSenaraiEnkuiri3(List<Enkuiri> senaraiEnkuiri3) {
        this.senaraiEnkuiri3 = senaraiEnkuiri3;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public String getKodsuku() {
        return kodsuku;
    }

    public void setKodsuku(String kodsuku) {
        this.kodsuku = kodsuku;
    }

    public List<KodSuku> getList() {
        return list;
    }

    public void setList(List<KodSuku> list) {
        this.list = list;
    }

    public List<LelonganSuku> getListSuku() {
        return listSuku;
    }

    public void setListSuku(List<LelonganSuku> listSuku) {
        this.listSuku = listSuku;
    }
}
