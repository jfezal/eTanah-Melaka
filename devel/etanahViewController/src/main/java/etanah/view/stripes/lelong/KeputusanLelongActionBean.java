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
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AkuanTerimaDeposit;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodStatusLelongan;
import etanah.model.Lelongan;
import etanah.model.Pembida;
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
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/keputusan_lelong")
public class KeputusanLelongActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanLelongActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembidaDAO pembidaDAO;
    @Inject
    LelongService lelongService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    EnkuiriService enService;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    CalenderManager manager;
    private String idPermohonan;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Pembida pembida;
    private List<FasaPermohonan> listFasa;
    private FasaPermohonan fasaPermohonan;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan2;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> senaraiLelongan3;
    private List<Lelongan> listLelongan;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri3;
    private List<Pembida> senaraiPembida;
    private Enkuiri enkuiri;
    private Lelongan lelong;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private BigDecimal amaunTunggakan2;
    private String jam;
    private String minit;
    private String ampm;
    private String tarikhLelong;
    private String tarikhLelongTerdahulu;
    private String tarikhAkhirBayaran;
    private String disabled;
    private String disabled3;
    private String idHak;
    private boolean lel3 = false;
    private boolean error;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private String tarikhEnkuiri;
    private long idLelong;
    private int bilLelong;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("---showForm---");
        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri = lelongService.findEnkuiri(idPermohonan);
        } else {
            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (list2.isEmpty()) {
                enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!list.isEmpty()) {
                        enkuiri = list.get(0);
                    }
                }
            } else {
                enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (!list.isEmpty()) {
                        enkuiri = list.get(0);
                    }
                }
            }
        }

        List<Lelongan> listLelong = lelongService.getLeloganALLDESC(idPermohonan);
        LOG.info("listLelong : " + listLelong.size());
        Lelongan lelo = listLelong.get(0);
        int bil = lelo.getBil();
        LOG.info("bil : " + bil);
        int bilLel = 0;
        //edit by nur.aqilah
        //if (bil <= 3)
        //edit by farizal (3july15)
        if (("TB".equals(lelo.getKodStatusLelongan().getKod())) || ("AT".equals(lelo.getKodStatusLelongan().getKod()))) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
            } else {
                LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                    error = false;
                    addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;
                    InfoAudit ia = fasaPermohonan.getInfoAudit();
                    String a = sdf1.format(ia.getTarikhMasuk());
                    InfoAudit ia2 = null;
                    String b = null;
                    KodStatusLelongan kod = null;
                    InfoAudit ial = new InfoAudit();
                    ial.setDiKemaskiniOleh(pengguna);
                    ial.setTarikhKemaskini(new java.util.Date());
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
                    senaraiLelongan = lelongService.findListLelongan(idPermohonan);

                    // N9
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("---showForm N9---");
                        for (Lelongan ll : senaraiLelongan) {
                            if (ll.getBil() == bil) {
                                LOG.info("---Tiada Pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                if (ll.getPembida() == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");

                                        if (enkuiri.getCaraLelong().equals("A")) {
                                            List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                            if (!listATD.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                            if (!listATD2.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD2) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                            if (!listATD.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                            if (!listATD2.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD2) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {
                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                }
                                if (ll.getPembida() != null) {
                                    LOG.info("---ada pembida---");
                                    LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                    if (!ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                        ia2 = ll.getInfoAudit();
                                        b = sdf1.format(ia2.getTarikhMasuk());
                                        LOG.info("a : " + a);
                                        LOG.info("b : " + b);
                                        if (a == null ? b != null : !a.equals(b)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            if (enkuiri.getCaraLelong().equals("A")) {
                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        } else {
                                            Date c = ia.getTarikhMasuk();
                                            Date d = ia2.getTarikhMasuk();
                                            if (d.before(c)) {
                                                kod = kodStatusLelonganDAO.findById("AT");
                                                ll.setKodStatusLelongan(kod);
                                                ll.setInfoAudit(ial);
                                                lelongService.saveOrUpdate(ll);
                                                if (enkuiri.getCaraLelong().equals("A")) {
                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                    if (!listATD.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                    if (!listATD2.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD2) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                }
                                                //bersama
                                                if (enkuiri.getCaraLelong().equals("B")) {

                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                    if (!listATD.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                    if (!listATD2.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD2) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    enkuiri.setDeposit(null);
                                                    enkuiri.setHargaRizab(null);
                                                    enkuiri.setHargaBida(null);
                                                    enkuiri.setBaki(null);
                                                    lelongService.saveOrUpdate(enkuiri);
                                                }
                                                kod = kodStatusLelonganDAO.findById("AK");
                                                ial.setDimasukOleh(pengguna);
                                                ial.setTarikhMasuk(new java.util.Date());
                                                Lelongan lel = new Lelongan();
                                                lel.setEnkuiri(enkuiri);
                                                lel.setBil(bil + 1);
                                                lel.setInfoAudit(ial);
                                                lel.setPermohonan(permohonan);
                                                lel.setKodStatusLelongan(kod);
                                                lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                if (ll.getPerihalTanah() != null) {
                                                    lel.setPerihalTanah(ll.getPerihalTanah());
                                                }
                                                if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                    lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                }
                                                lelongService.saveOrUpdate(lel);
                                                bilLel = lel.getBil();
                                                tarikhAkhirBayaran = null;
                                                tarikhLelong = null;
                                                jam = null;
                                                minit = null;
                                                ampm = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // MELAKA
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("---showForm MLK---");
                        for (Lelongan ll : senaraiLelongan) {
                            if (ll.getBil() == bil) {
                                LOG.info("---Tiada Pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                idLelong = ll.getIdLelong();
                                Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                                if (ll.getTarikhLelong() == null) {
                                    break;
                                }

//                                if (pem != null  && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {
//                                        kod = kodStatusLelonganDAO.findById("TB");
//                                        ll.setKodStatusLelongan(kod);
//                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");

//                                        if (enkuiri.getCaraLelong().equals("A")) {
//                                            List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                            if (!listATD.isEmpty()) {
//                                                for (AkuanTerimaDeposit aa : listATD) {
//                                                    lelongService.delete(aa);
//                                                }
//                                            }
//                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                            if (!listATD2.isEmpty()) {
//                                                for (AkuanTerimaDeposit aa : listATD2) {
//                                                    lelongService.delete(aa);
//                                                }
//                                            }
//                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

//                                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                            if (!listATD.isEmpty()) {
//                                                for (AkuanTerimaDeposit aa : listATD) {
//                                                    lelongService.delete(aa);
//                                                }
//                                            }
//                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                            if (!listATD2.isEmpty()) {
//                                                for (AkuanTerimaDeposit aa : listATD2) {
//                                                    lelongService.delete(aa);
//                                                }
//                                            }
                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }

//                                        List<Lelongan> senaraiLelong = lelongService.findListLelongan(idPermohonan);
//                                        Lelongan lelong = ll;

                                        LOG.info("kpsn lelong = " + ll.getKodStatusLelongan().getKod());
                                        if (ll.getKodStatusLelongan().getKod().equals("TB")) {
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            lel.setJurulelong(ll.getJurulelong());
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                        }
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {
//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }

                                        if (d.after(c)) {
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {
//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                } //                                if (pem.getPihak() != null) {
                                else {
                                    LOG.info("---ada pembida---");
                                    LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                    if (ll.getTarikhLelong() == null) {
                                        break;
                                    }

                                    if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                        ia2 = ll.getInfoAudit();
                                        b = sdf1.format(ia2.getTarikhMasuk());
                                        LOG.info("a : " + a);
                                        LOG.info("b : " + b);
                                        if (a == null ? b != null : !a.equals(b)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            if (enkuiri.getCaraLelong().equals("A")) {
//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        } else {
                                            Date c = ia.getTarikhMasuk();
                                            Date d = ia2.getTarikhMasuk();
                                            if (d.before(c)) {
                                                kod = kodStatusLelonganDAO.findById("AT");
                                                ll.setKodStatusLelongan(kod);
                                                ll.setInfoAudit(ial);
                                                lelongService.saveOrUpdate(ll);
                                                if (enkuiri.getCaraLelong().equals("A")) {
//                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                    if (!listATD.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
//                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                    if (!listATD2.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD2) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
                                                }
                                                //bersama
                                                if (enkuiri.getCaraLelong().equals("B")) {

//                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                    if (!listATD.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
//                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                    if (!listATD2.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD2) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
                                                    enkuiri.setDeposit(null);
                                                    enkuiri.setHargaRizab(null);
                                                    enkuiri.setHargaBida(null);
                                                    enkuiri.setBaki(null);
                                                    lelongService.saveOrUpdate(enkuiri);
                                                }
                                                kod = kodStatusLelonganDAO.findById("AK");
                                                ial.setDimasukOleh(pengguna);
                                                ial.setTarikhMasuk(new java.util.Date());
                                                Lelongan lel = new Lelongan();
                                                lel.setEnkuiri(enkuiri);
                                                lel.setBil(bil + 1);
                                                lel.setInfoAudit(ial);
                                                lel.setPermohonan(permohonan);
                                                lel.setKodStatusLelongan(kod);
                                                lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                if (ll.getPerihalTanah() != null) {
                                                    lel.setPerihalTanah(ll.getPerihalTanah());
                                                }
                                                if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                    lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                }
                                                lelongService.saveOrUpdate(lel);
                                                bilLel = lel.getBil();
                                                tarikhAkhirBayaran = null;
                                                tarikhLelong = null;
                                                jam = null;
                                                minit = null;
                                                ampm = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                LOG.info("bilLel : " + bilLel);
                if (bilLel == 4) {
                    lel3 = true;
                    addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                }
            }
        }
        if ((bil <= 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
            } else {
                LOG.info("fasaPermohonan : " + fasaPermohonan.getKeputusan().getKod());
                if (fasaPermohonan.getKeputusan().getKod().equals("RM")) {
                    error = false;
                    addSimpleMessage("Keputusan ialah Rujuk Mahkamah,Sila Klik Butang Selesai");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;
                    InfoAudit ia = fasaPermohonan.getInfoAudit();
                    String a = sdf1.format(ia.getTarikhMasuk());
                    InfoAudit ia2 = null;
                    String b = null;
                    KodStatusLelongan kod = null;
                    InfoAudit ial = new InfoAudit();
                    ial.setDiKemaskiniOleh(pengguna);
                    ial.setTarikhKemaskini(new java.util.Date());
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                    // N9
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("---showForm N9---");
                        for (Lelongan ll : senaraiLelongan) {
                            if (ll.getBil() == bil) {
                                LOG.info("---Tiada Pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                if (ll.getPembida() == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {
                                        kod = kodStatusLelonganDAO.findById("TB");
                                        ll.setKodStatusLelongan(kod);
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");

                                        if (enkuiri.getCaraLelong().equals("A")) {
                                            List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                            if (!listATD.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                            if (!listATD2.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD2) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {

                                            List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                            if (!listATD.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                            if (!listATD2.isEmpty()) {
                                                for (AkuanTerimaDeposit aa : listATD2) {
                                                    lelongService.delete(aa);
                                                }
                                            }
                                            enkuiri.setDeposit(null);
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setHargaBida(null);
                                            enkuiri.setBaki(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);
                                        lel.setBil(bil + 1);
                                        lel.setPermohonan(permohonan);
                                        lel.setInfoAudit(ial);
                                        lel.setKodStatusLelongan(kod);
                                        if (ll.getPerihalTanah() != null) {
                                            lel.setPerihalTanah(ll.getPerihalTanah());
                                        }
                                        if (ll.getPerihalTanahBahasaInggeris() != null) {
                                            lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                        }
                                        lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                        lelongService.saveOrUpdate(lel);
                                        bilLel = lel.getBil();
                                        tarikhAkhirBayaran = null;
                                        tarikhLelong = null;
                                        jam = null;
                                        minit = null;
                                        ampm = null;
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {
                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                }
                                if (ll.getPembida() != null) {
                                    LOG.info("---ada pembida---");
                                    LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                    if (!ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                        ia2 = ll.getInfoAudit();
                                        b = sdf1.format(ia2.getTarikhMasuk());
                                        LOG.info("a : " + a);
                                        LOG.info("b : " + b);
                                        if (a == null ? b != null : !a.equals(b)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            if (enkuiri.getCaraLelong().equals("A")) {
                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                if (!listATD.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                if (!listATD2.isEmpty()) {
                                                    for (AkuanTerimaDeposit aa : listATD2) {
                                                        lelongService.delete(aa);
                                                    }
                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        } else {
                                            Date c = ia.getTarikhMasuk();
                                            Date d = ia2.getTarikhMasuk();
                                            if (d.before(c)) {
                                                kod = kodStatusLelonganDAO.findById("AT");
                                                ll.setKodStatusLelongan(kod);
                                                ll.setInfoAudit(ial);
                                                lelongService.saveOrUpdate(ll);
                                                if (enkuiri.getCaraLelong().equals("A")) {
                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
                                                    if (!listATD.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
                                                    if (!listATD2.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD2) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                }
                                                //bersama
                                                if (enkuiri.getCaraLelong().equals("B")) {

                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
                                                    if (!listATD.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));

                                                    if (!listATD2.isEmpty()) {
                                                        for (AkuanTerimaDeposit aa : listATD2) {
                                                            lelongService.delete(aa);
                                                        }
                                                    }
                                                    enkuiri.setDeposit(null);
                                                    enkuiri.setHargaRizab(null);
                                                    enkuiri.setHargaBida(null);
                                                    enkuiri.setBaki(null);
                                                    lelongService.saveOrUpdate(enkuiri);
                                                }
                                                kod = kodStatusLelonganDAO.findById("AK");
                                                ial.setDimasukOleh(pengguna);
                                                ial.setTarikhMasuk(new java.util.Date());
                                                Lelongan lel = new Lelongan();
                                                lel.setEnkuiri(enkuiri);
                                                lel.setBil(bil + 1);
                                                lel.setInfoAudit(ial);
                                                lel.setPermohonan(permohonan);
                                                lel.setKodStatusLelongan(kod);
                                                lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                if (ll.getPerihalTanah() != null) {
                                                    lel.setPerihalTanah(ll.getPerihalTanah());
                                                }
                                                if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                    lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                }
                                                lelongService.saveOrUpdate(lel);
                                                bilLel = lel.getBil();
                                                tarikhAkhirBayaran = null;
                                                tarikhLelong = null;
                                                jam = null;
                                                minit = null;
                                                ampm = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // MELAKA
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("---showForm MLK---");
                        for (Lelongan ll : senaraiLelongan) {
                            if (ll.getBil() == bil) {
                                LOG.info("---Tiada Pembida---");
                                LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
                                idLelong = ll.getIdLelong();
                                Pembida pem = lelongService.getPembidaByIdLelong(idLelong);

                                if (ll.getTarikhLelong() == null) {
                                    break;
                                }

                                if (pem == null && !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                    ia2 = ll.getInfoAudit();
                                    b = sdf1.format(ia2.getTarikhMasuk());
                                    LOG.info("a : " + a);
                                    LOG.info("b : " + b);
                                    if (a == null ? b != null : !a.equals(b)) {

                                        lelongService.saveOrUpdate(ll);
                                        kod = kodStatusLelonganDAO.findById("AK");

//                                        if (enkuiri.getCaraLelong().equals("B")) {
//
//                                            enkuiri.setDeposit(null);
//                                            enkuiri.setHargaRizab(null);
//                                            enkuiri.setHargaBida(null);
//                                            enkuiri.setBaki(null);
//                                            lelongService.saveOrUpdate(enkuiri);
//                                        }
                                     LOG.info("kpsn lelong = " + ll.getKodStatusLelongan().getKod());
                                        if (ll.getKodStatusLelongan().getKod().equals("TB")) {
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                        }
                                    } else {
                                        Date c = ia.getTarikhMasuk();
                                        Date d = ia2.getTarikhMasuk();
                                        if (d.before(c)) {
                                            kod = kodStatusLelonganDAO.findById("TB");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            if (enkuiri.getCaraLelong().equals("A")) {
//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            ial.setDimasukOleh(pengguna);
                                            ial.setTarikhMasuk(new java.util.Date());
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setInfoAudit(ial);
                                            lel.setPermohonan(permohonan);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        }
                                    }
                                } //                                if (pem.getPihak() != null) {
                                else {
                                    LOG.info("---ada pembida---");
                                    LOG.info("ll.getKodStatusLelongan().getKod() : " + ll.getKodStatusLelongan().getKod());
//                                    if (pem == null || ll.getKodStatusLelongan().getKod().equals("AT") || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {

                                    if (ll.getTarikhLelong() == null) {
                                        break;
                                    }

                                    if (pem != null || !ll.getKodStatusLelongan().getKod().equals("SL") || !ll.getKodStatusLelongan().getKod().equals("PL")) {
                                        ia2 = ll.getInfoAudit();
                                        b = sdf1.format(ia2.getTarikhMasuk());
                                        LOG.info("a : " + a);
                                        LOG.info("b : " + b);
                                        if (a == null ? b != null : !a.equals(b)) {
                                            kod = kodStatusLelonganDAO.findById("AT");
                                            ll.setKodStatusLelongan(kod);
                                            ll.setInfoAudit(ial);
                                            lelongService.saveOrUpdate(ll);
                                            kod = kodStatusLelonganDAO.findById("AK");
                                            if (enkuiri.getCaraLelong().equals("A")) {
//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                            }
                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {

//                                                List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                if (!listATD.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
//                                                List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                if (!listATD2.isEmpty()) {
//                                                    for (AkuanTerimaDeposit aa : listATD2) {
//                                                        lelongService.delete(aa);
//                                                    }
//                                                }
                                                enkuiri.setDeposit(null);
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setHargaBida(null);
                                                enkuiri.setBaki(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                            Lelongan lel = new Lelongan();
                                            lel.setEnkuiri(enkuiri);
                                            lel.setBil(bil + 1);
                                            lel.setPermohonan(permohonan);
                                            lel.setInfoAudit(ial);
                                            lel.setKodStatusLelongan(kod);
                                            lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                            if (ll.getPerihalTanah() != null) {
                                                lel.setPerihalTanah(ll.getPerihalTanah());
                                            }
                                            if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                            }
                                            lelongService.saveOrUpdate(lel);
                                            bilLel = lel.getBil();
                                            tarikhAkhirBayaran = null;
                                            tarikhLelong = null;
                                            jam = null;
                                            minit = null;
                                            ampm = null;
                                        } else {
                                            Date c = ia.getTarikhMasuk();
                                            Date d = ia2.getTarikhMasuk();
                                            if (d.before(c)) {
                                                kod = kodStatusLelonganDAO.findById("AT");
                                                ll.setKodStatusLelongan(kod);
                                                ll.setInfoAudit(ial);
                                                lelongService.saveOrUpdate(ll);
                                                if (enkuiri.getCaraLelong().equals("A")) {
//                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATDS(idPermohonan, ll.getIdLelong());
//                                                    if (!listATD.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
//                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2S(idPermohonan, ll.getIdLelong());
//                                                    if (!listATD2.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD2) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
                                                }
                                                //bersama
                                                if (enkuiri.getCaraLelong().equals("B")) {

//                                                    List<AkuanTerimaDeposit> listATD = lelongService.findATD(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//                                                    if (!listATD.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
//                                                    List<AkuanTerimaDeposit> listATD2 = lelongService.findATD2(idPermohonan, String.valueOf(enkuiri.getIdEnkuiri()));
//
//                                                    if (!listATD2.isEmpty()) {
//                                                        for (AkuanTerimaDeposit aa : listATD2) {
//                                                            lelongService.delete(aa);
//                                                        }
//                                                    }
                                                    enkuiri.setDeposit(null);
                                                    enkuiri.setHargaRizab(null);
                                                    enkuiri.setHargaBida(null);
                                                    enkuiri.setBaki(null);
                                                    lelongService.saveOrUpdate(enkuiri);
                                                }
                                                kod = kodStatusLelonganDAO.findById("AK");
                                                ial.setDimasukOleh(pengguna);
                                                ial.setTarikhMasuk(new java.util.Date());
                                                Lelongan lel = new Lelongan();
                                                lel.setEnkuiri(enkuiri);
                                                lel.setBil(bil + 1);
                                                lel.setInfoAudit(ial);
                                                lel.setPermohonan(permohonan);
                                                lel.setKodStatusLelongan(kod);
                                                lel.setHakmilikPermohonan(ll.getHakmilikPermohonan());
                                                if (ll.getPerihalTanah() != null) {
                                                    lel.setPerihalTanah(ll.getPerihalTanah());
                                                }
                                                if (ll.getPerihalTanahBahasaInggeris() != null) {
                                                    lel.setPerihalTanahBahasaInggeris(ll.getPerihalTanahBahasaInggeris());
                                                }
                                                lelongService.saveOrUpdate(lel);
                                                bilLel = lel.getBil();
                                                tarikhAkhirBayaran = null;
                                                tarikhLelong = null;
                                                jam = null;
                                                minit = null;
                                                ampm = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                LOG.info("bilLel : " + bilLel);
                if (bilLel == 4) {
                    lel3 = true;
                    addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                }
            }
        } else if ((bil == 3) && ("AK".equals(lelo.getKodStatusLelongan().getKod()))) {
            LOG.info("lelongan telah di lakukan sebanyak 3 kali");
            lel3 = true;
            addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
        }
//        LOG.info("bil : " + bil);
        rehydrate();
        return new JSP("lelong/kpsn_lelong.jsp").addParameter("tab", "true");
    }

    public Resolution showFormRM() {
        listLel = lelongService.listLelonganRM(idPermohonan);
        senaraiLelongan = lelongService.listLelonganRM(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInRM(enkuiri.getIdEnkuiri());

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("RM")) {
                lelong = ll;
                break;
            }
        }

        try {
            if (!senaraiLelongan.isEmpty()) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong());
                if (lelong.getBil() <= 1) {
                    disabled3 = "disabeld3";
                }
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
            }
            if (lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
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
        return new JSP("lelong/kpsn_lelong_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm16I() {
        listLel = lelongService.listLelonganAP(idPermohonan);
        senaraiLelongan = lelongService.listLelonganAP(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("AP")) {
                lelong = ll;
                break;
            }
        }

        try {
            if (!senaraiLelongan.isEmpty()) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong());
                if (lelong.getBil() <= 1) {
                    disabled3 = "disabeld3";
                }
                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
            }
            if (lelong.getTarikhLelong() != null) {
                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
            }

        } catch (Exception ex) {
            LOG.error(ex);
        }
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
        return new JSP("lelong/kpsn_lelong_semak.jsp").addParameter("tab", "true");
    }

    public Resolution showFormB() {
        //editted by nur.aqilah //add validator for calendar
        senaraiLelongan = lelongService.listLelonganAKAP2(permohonan.getIdPermohonan()); //lelongan yg AK
        LOG.info("Senarai Aktif " + senaraiLelongan.size());
//        LOG.info("Bil Lelongan Aktif " + senaraiLelongan.get(0).getBil());
        senaraiLelongan2 = lelongService.findLelonganTerdahulu(permohonan.getIdPermohonan(), senaraiLelongan.get(0).getBil());
        LOG.info("Senarai Lelongan Terdahulu " + senaraiLelongan2.size());
        tarikhLelongTerdahulu = sdf.format(senaraiLelongan2.get(0).getTarikhLelong()).substring(0, 10);
        bilLelong = senaraiLelongan.get(0).getBil();

//        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelonganBaru(permohonan.getCawangan().getKod());

        return new JSP("lelong/calender_lelong2.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            listFasa = lelongService.getPermonanFasaSemakPembida(idPermohonan);
            if (!listFasa.isEmpty()) {
                fasaPermohonan = listFasa.get(0);
            }
            if (permohonan.getPermohonanSebelum() == null) {
                enkuiri = lelongService.findEnkuiri(idPermohonan);
                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
            } else {
                List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (list2.isEmpty()) {
                    enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                } else {
                    enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (enkuiri == null) {
                        List<Enkuiri> list = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (!list.isEmpty()) {
                            enkuiri = list.get(0);
                        }
                    }
                    senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                }
            }
            if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
                if (fasaPermohonan.getKeputusan().getKod().equals("LS") || fasaPermohonan.getKeputusan().getKod().equals("AA")) {
                    error = true;

                    senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);

                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                            lelong = ll;
                            break;
                        }
                    }
                    listLel = lelongService.getLeloganASC(idPermohonan);
                    LOG.info("listLel : " + listLel.size());

                    //for negeri melake
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        negori = true;
                        if (enkuiri.getAmaunTunggakan() != null) {
                            amaunTunggakan = enkuiri.getAmaunTunggakan();
                        }
                        if (enkuiri.getAmaunGadaian() != null) {
                            amaunTunggakan2 = enkuiri.getAmaunGadaian();
                        }
                    } else {

                        negori = false;
                    }

                    if (enkuiri.getCaraLelong() == null) {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.TRUE);
                    } else {
                        getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                        //berasingan

                        if (enkuiri.getCaraLelong().equals("A")) {
                            LOG.info("-----A-----");
                            getContext().getRequest().setAttribute("same", Boolean.FALSE);
                            List<Lelongan> listLel2 = lelongService.getLeloganASC(idPermohonan);
                            listLel = new ArrayList<Lelongan>();
                            //n9
                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("-----N9-----");
                                for (Lelongan ll : listLel2) {
                                    if (ll.getPembida() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                        listLel.add(ll);
                                    }
                                }
                            }

                            //MLK
                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                LOG.info("----o4---");
                                for (Lelongan ll : listLel2) {
                                    idLelong = ll.getIdLelong();
//                                    if (pembida != null) {
                                    senaraiPembida = enService.getListPembida(idLelong);
                                    if (senaraiPembida.isEmpty() && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                        listLel.add(ll);
                                    }
//                                        for (Pembida bids : senaraiPembida) {
//                                            if (bids.getPihak() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
//                                                listLel.add(ll);
//                                            }
//
//                                        }
//                                    }
                                }
                            }

                        }

                        //bersama
                        listLelongan = new ArrayList<Lelongan>();
                        if (enkuiri.getCaraLelong().equals("B")) {
                            LOG.info("-----B-----");
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
                        if (!senaraiLelongan.isEmpty()) {
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
                }
            }
        }
    }

    //berasingan utk n.melaka
    public Resolution simpanLelong() {

        InfoAudit ia = new InfoAudit();
        listLel = lelongService.listLelonganAK(idPermohonan);
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
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }
        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            enkuiri.setAmaunGadaian(amaunTunggakan2);
            lelongService.saveOrUpdate(enkuiri);
        } else {
            negori = false;
            lelongService.saveOrUpdate(enkuiri);
        }
        rehydrate();
//        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/kpsn_lelong.jsp").addParameter("tab", "true");
    }

    //bersama utk n.melaka
    public Resolution saveBersama() throws ParseException {

        InfoAudit ia = new InfoAudit();
        listLel = lelongService.listLelonganAK(idPermohonan);
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
//            lel.setTarikhLelong(sdf.parse(tarikhLelong));
//            lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            lel.setTempat(lelong.getTempat());
            KodStatusLelongan ksl = new KodStatusLelongan();
            ksl.setKod("AK");
            lel.setDeposit(enkuiri.getDeposit());
            lel.setHargaRizab(enkuiri.getHargaRizab());
            lel.setKodStatusLelongan(ksl);
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }
        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            enkuiri.setAmaunGadaian(amaunTunggakan2);
            enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            lelongService.saveOrUpdate(enkuiri);
        } else {
            negori = false;
            enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            lelongService.saveOrUpdate(enkuiri);
        }
        rehydrate();
//        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/kpsn_lelong.jsp").addParameter("tab", "true");
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

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getDisabled3() {
        return disabled3;
    }

    public void setDisabled3(String disabled3) {
        this.disabled3 = disabled3;
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getIdHak() {
        return idHak;
    }

    public void setIdHak(String idHak) {
        this.idHak = idHak;
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

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
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

    public String getTarikhLelong() {
        return tarikhLelong;
    }

    public void setTarikhLelong(String tarikhLelong) {
        this.tarikhLelong = tarikhLelong;
    }

    public boolean isLel3() {
        return lel3;
    }

    public void setLel3(boolean lel3) {
        this.lel3 = lel3;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public List<Pembida> getSenaraiPembida() {
        return senaraiPembida;
    }

    public void setSenaraiPembida(List<Pembida> senaraiPembida) {
        this.senaraiPembida = senaraiPembida;
    }

    public long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(long idLelong) {
        this.idLelong = idLelong;
    }

    public Pembida getPembida() {
        return pembida;
    }

    public void setPembida(Pembida pembida) {
        this.pembida = pembida;
    }

    public BigDecimal getAmaunTunggakan2() {
        return amaunTunggakan2;
    }

    public void setAmaunTunggakan2(BigDecimal amaunTunggakan2) {
        this.amaunTunggakan2 = amaunTunggakan2;
    }

    public List<Lelongan> getSenaraiLelongan2() {
        return senaraiLelongan2;
    }

    public void setSenaraiLelongan2(List<Lelongan> senaraiLelongan2) {
        this.senaraiLelongan2 = senaraiLelongan2;
    }

    public String getTarikhLelongTerdahulu() {
        return tarikhLelongTerdahulu;
    }

    public void setTarikhLelongTerdahulu(String tarikhLelongTerdahulu) {
        this.tarikhLelongTerdahulu = tarikhLelongTerdahulu;
    }

    public int getBilLelong() {
        return bilLelong;
    }

    public void setBilLelong(int bilLelong) {
        this.bilLelong = bilLelong;
    }
}
