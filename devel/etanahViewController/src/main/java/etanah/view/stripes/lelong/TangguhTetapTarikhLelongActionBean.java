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
import etanah.dao.LelonganDAO;
import etanah.dao.PembidaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PihakDAO;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodSuku;
import etanah.model.Lelongan;
import etanah.model.LelonganSuku;
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
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
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
@UrlBinding("/lelong/tangguh_tetap_tarikh_lelong")
public class TangguhTetapTarikhLelongActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(TangguhTetapTarikhLelongActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    LelonganDAO lelongDAO;
    @Inject
    KodSukuDAO kodSukuDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    PembidaDAO pembidaDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    CalenderManager manager;
    private Permohonan permohonan;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String tarikhLelong;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhAkhirBayaran;
    private String idPermohonan;
    private boolean button = false;
    private Pengguna pengguna;
    private List<Enkuiri> senaraiEnkuiri3;
    private List<Lelongan> senaraiLelongan3;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private List<Lelongan> listLelongan;
    private String idHak;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private String kodsuku;
    private List<KodSuku> list;
    private List<LelonganSuku> lelonganList = new ArrayList<LelonganSuku>();
    private List<LelonganSuku> listSuku;
    private String tarikhEnkuiri;
    private Long idLelong;
    private String tarikhLelongTerdahulu;
    private int bilLelong;
    private FasaPermohonan mohonFasa;

    @DefaultHandler
    public Resolution showForm() {

        List<FasaPermohonan> mohonFasaList = lelongService.findFasaPermohonanTangguhList(idPermohonan);
        
            if(mohonFasaList.size() > 0){
                 mohonFasa = mohonFasaList.get(0);
            }
         

        if (mohonFasa.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
        } else {
            LOG.info("---showForm---");
            List<Lelongan> listLelong = lelongService.getLeloganALLDESC(permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("listLelong : " + listLelong.size());
            Lelongan lelo = listLelong.get(0);
            int bil = lelo.getBil();
            LOG.info("bil : " + bil);
            int bilLel = 0;
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                LOG.info("-----Lulus-------");
                button = false;
                if (bil <= 3) {
                    InfoAudit ia = mohonFasa.getInfoAudit();
                    String a = sdf1.format(ia.getTarikhMasuk());
                    InfoAudit ia2 = null;
                    String b = null;
                    InfoAudit ial = new InfoAudit();
                    ial.setDiKemaskiniOleh(pengguna);
                    ial.setTarikhKemaskini(new java.util.Date());
                    ial.setDimasukOleh(pengguna);
                    ial.setTarikhMasuk(new java.util.Date());
//                    senaraiLelongan = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
                    senaraiLelongan = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                    List<Lelongan> senaraiLelonganJdTG = lelongService.listLelonganAKNew(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (senaraiLelonganJdTG.size() < 0) {
                        Lelongan lltg = senaraiLelonganJdTG.get(0);
                        lltg.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
                        lelongService.saveOrUpdate(lltg);
                    }
                    for (Lelongan ll : senaraiLelongan) {
                        if (ll.getBil() == bil) {

                            ia2 = ll.getInfoAudit();
                            b = sdf1.format(ia2.getTarikhMasuk());
                            LOG.info("a : " + a);
                            LOG.info("b : " + b);
                            if (a == null ? b != null : !a.equals(b)) {
                                List<Lelongan> senaraiLelong = lelongService.findListLelongan(permohonan.getPermohonanSebelum().getIdPermohonan());
                                Lelongan lelong = senaraiLelong.get(0);
                                if (lelong.getKodStatusLelongan().equals("TG")) {
                                    LOG.info("---if---");
//                                    ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
                                    ll.setInfoAudit(ial);
                                    lelongService.saveOrUpdate(ll);
                                    Lelongan lel = new Lelongan();
                                    lel.setEnkuiri(enkuiri);
                                    lel.setPermohonan(permohonan.getPermohonanSebelum());
                                    lel.setInfoAudit(ial);

                                    //added by nur.aqilah
                                    lel.setPermohonanTangguh(ll.getPermohonanTangguh());
                                    //edited by nur.aqilah
                                    lel.setBil(bil);
                                    lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));

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
                                    if (enkuiri.getCaraLelong() != null) {
                                        //berasingan
                                        if (enkuiri.getCaraLelong().equals("A")) {
                                            LOG.info("-----A-----");
                                            List<Lelongan> listLel2 = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
                                            listLel = new ArrayList<Lelongan>();

                                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                                LOG.info("----N9-----");
                                                for (Lelongan ll2 : listLel2) {
                                                    if (ll2.getPembida() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                        listLel.add(ll2);
                                                    }
                                                }
                                            }

                                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                                LOG.info("----MLK-----");
                                                for (Lelongan ll2 : listLel2) {
                                                    idLelong = ll2.getIdLelong();
                                                    Pembida pem = pembidaDAO.findById(idLelong);
                                                    if (pem.getPihak() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                        listLel.add(ll2);
                                                    }
                                                }
                                            }
                                        }
                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setDeposit(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                    }
                                } else {
                                    Date c = ia.getTarikhMasuk();
                                    Date d = ia2.getTarikhMasuk();
                                    if (d.before(c)) {
                                        LOG.info("---else---");
                                        ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
                                        ll.setInfoAudit(ial);
                                        lelongService.saveOrUpdate(ll);
                                        ial.setDimasukOleh(pengguna);
                                        ial.setTarikhMasuk(new java.util.Date());
                                        Lelongan lel = new Lelongan();
                                        lel.setEnkuiri(enkuiri);

                                        //added by nur.aqilah
                                        lel.setPermohonanTangguh(ll.getPermohonanTangguh());
                                        //edited by nur.aqilah
                                        lel.setBil(bil);

                                        lel.setInfoAudit(ial);
                                        lel.setPermohonan(permohonan.getPermohonanSebelum());
                                        lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
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
                                        if (enkuiri.getCaraLelong() != null) {
                                            //berasingan
                                            if (enkuiri.getCaraLelong().equals("A")) {
                                                LOG.info("-----A-----");
                                                List<Lelongan> listLel2 = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
                                                listLel = new ArrayList<Lelongan>();
                                                //N9
                                                if ("05".equals(conf.getProperty("kodNegeri"))) {
                                                    LOG.info("----N9-----");
                                                    for (Lelongan ll2 : listLel2) {
                                                        if (ll2.getPembida() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                            listLel.add(ll2);
                                                        }
                                                    }
                                                }

                                                //MLK
                                                if ("04".equals(conf.getProperty("kodNegeri"))) {
                                                    LOG.info("----MLK-----");
                                                    for (Lelongan ll2 : listLel2) {
                                                        idLelong = ll2.getIdLelong();
                                                        Pembida pem = pembidaDAO.findById(idLelong);
                                                        if (pem.getPihak() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                            listLel.add(ll2);
                                                        }
                                                    }
                                                }
                                            }

                                            //bersama
                                            if (enkuiri.getCaraLelong().equals("B")) {
                                                enkuiri.setHargaRizab(null);
                                                enkuiri.setDeposit(null);
                                                lelongService.saveOrUpdate(enkuiri);
                                            }
                                        }
                                    }
                                }
                            } else {
                                Date c = ia.getTarikhMasuk();
                                Date d = ia2.getTarikhMasuk();
                                if (d.before(c)) {
                                    LOG.info("---else---");
                                    ll.setKodStatusLelongan(kodStatusLelonganDAO.findById("TG"));
                                    ll.setInfoAudit(ial);
                                    lelongService.saveOrUpdate(ll);
                                    ial.setDimasukOleh(pengguna);
                                    ial.setTarikhMasuk(new java.util.Date());
                                    Lelongan lel = new Lelongan();
                                    lel.setEnkuiri(enkuiri);

                                    //added by nur.aqilah
                                    lel.setPermohonanTangguh(ll.getPermohonanTangguh());
                                    //edited by nur.aqilah
                                    lel.setBil(bil);

                                    lel.setInfoAudit(ial);
                                    lel.setPermohonan(permohonan.getPermohonanSebelum());
                                    lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
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
                                    if (enkuiri.getCaraLelong() != null) {
                                        //berasingan
                                        if (enkuiri.getCaraLelong().equals("A")) {
                                            LOG.info("-----A-----");
                                            List<Lelongan> listLel2 = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
                                            listLel = new ArrayList<Lelongan>();
                                            //N9
                                            if ("05".equals(conf.getProperty("kodNegeri"))) {
                                                LOG.info("----N9-----");
                                                for (Lelongan ll2 : listLel2) {
                                                    if (ll2.getPembida() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                        listLel.add(ll2);
                                                    }
                                                }
                                            }

                                            //MLK
                                            if ("04".equals(conf.getProperty("kodNegeri"))) {
                                                LOG.info("----MLK-----");
                                                for (Lelongan ll2 : listLel2) {
                                                    idLelong = ll2.getIdLelong();
                                                    Pembida pem = pembidaDAO.findById(idLelong);
                                                    if (pem.getPihak() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                                        listLel.add(ll2);
                                                    }
                                                }
                                            }
                                        }

                                        //bersama
                                        if (enkuiri.getCaraLelong().equals("B")) {
                                            enkuiri.setHargaRizab(null);
                                            enkuiri.setDeposit(null);
                                            lelongService.saveOrUpdate(enkuiri);
                                        }
                                    }
                                }
                            }
                        }
                        LOG.info("bilLel : " + bilLel);
                        if (bilLel == 4) {
                            addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                        }
                    }
                } else {
                    addSimpleMessage("Lelongan Telah Dilakukan Sebannyak 3 Kali,Sila Buat Keputusan Rujuk Mahkamah Di Tab Keputusan");
                }
            }
            if (mohonFasa.getKeputusan().getKod().equals("T")) {
                LOG.info("-----Tolak-------");
                showFormBb();
//                addSimpleMessage("Keputusan Adalah Tolak.Sila Klik Butang Selesai...");
                button = true;
                return new JSP("lelong/lelong_tggh_view.jsp").addParameter("tab", "true");
            }

        }
        rehydrate();
        return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
    }

    public Resolution showFormBb() {

        listLel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
        senaraiLelongan = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                lelong = ll;
                break;
            }
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
        return new JSP("lelong/lelong_tggh_view.jsp").addParameter("tab", "true");
    }

    public Resolution showFormBb1() {

        FasaPermohonan mohonFasa = lelongService.findFasaPermohonanTangguh(idPermohonan);

        if (mohonFasa.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
        }

        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            LOG.info("-----Lulus-------");
            listLel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            senaraiLelongan = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            for (Lelongan ll : senaraiLelongan) {
                if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                    lelong = ll;
                    break;
                }
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
        }
        if (mohonFasa.getKeputusan().getKod().equals("T")) {
            LOG.info("-----Tolak-------");
            addSimpleMessage("Keputusan Adalah Tolak.Sila Klik Butang Selesai...");
            button = true;
            return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
        }

        return new JSP("lelong/lelong_tggh_view.jsp").addParameter("tab", "true");
    }

    public Resolution showFormBb1Batal() {

        FasaPermohonan mohonFasa = lelongService.findFasaPermohonan(idPermohonan);

        if (mohonFasa.getKeputusan() == null) {
            LOG.info("----null-----");
            addSimpleError("Keputusan Belum Dibuat.Sila Pilih Keputusan Di Tab Keputusan.");
            button = true;
            return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
        }

        if (mohonFasa.getKeputusan().getKod().equals("L")) {
            LOG.info("-----Lulus-------");
            listLel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            senaraiLelongan = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            for (Lelongan ll : senaraiLelongan) {
                if (ll.getKodStatusLelongan().getKod().equals("AK")) {
                    lelong = ll;
                    break;
                }
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
        }
        if (mohonFasa.getKeputusan().getKod().equals("T")) {
            LOG.info("-----Tolak-------");
            addSimpleMessage("Keputusan Adalah Tolak.Sila Klik Butang Selesai...");
            button = true;
            return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
        }

        return new JSP("lelong/lelong_tggh_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<PermohonanAsal> listPA = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (listPA.isEmpty()) {
                enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (!list3.isEmpty()) {
                        enkuiri = list3.get(0);
                    }
                }
                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
            } else {
                enkuiri = lelongService.findEnkuiri(listPA.get(0).getIdPermohonanAsal().getIdPermohonan());
                if (enkuiri == null) {
                    List<Enkuiri> list3 = lelongService.getAllDesc(listPA.get(0).getIdPermohonanAsal().getIdPermohonan());
                    if (!list3.isEmpty()) {
                        enkuiri = list3.get(0);
                    }
                }
                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(listPA.get(0).getIdPermohonanAsal().getIdPermohonan());
            }

            if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
            }

            senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
            senaraiLelongan = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());

            LOG.debug("senaraiLelong size :" + senaraiLelongan.size());
            if (!senaraiLelongan.isEmpty()) {
                for (Lelongan lelongan : senaraiLelongan) {
                    LOG.debug("idLelong :" + lelongan.getIdLelong());
                    lelong = lelongan;
                }
            }

            listLel = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
            LOG.info("listLel : " + listLel.size());

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
            if (enkuiri.getCaraLelong() == null) {
                getContext().getRequest().setAttribute("sblmPilih", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                //berasingan

                if (enkuiri.getCaraLelong().equals("A")) {
                    LOG.info("-----A-----");
                    getContext().getRequest().setAttribute("same", Boolean.FALSE);
                    List<Lelongan> listLel2 = lelongService.getLeloganASC(permohonan.getPermohonanSebelum().getIdPermohonan());
                    listLel = new ArrayList<Lelongan>();
                    if ("05".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("----N9-----");
                        for (Lelongan ll : listLel2) {
                            if (ll.getPembida() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                listLel.add(ll);
                            }
                        }
                    }

                    //MLK
                    if ("04".equals(conf.getProperty("kodNegeri"))) {
                        LOG.info("----MLK-----");
                        for (Lelongan ll : listLel2) {
                            idLelong = ll.getIdLelong();
                            Pembida pem = lelongService.getPembida(idLelong);
//                            Pembida pem = pembidaDAO.findById(idLelong);
                            if (pem.getPihak() == null && ll.getKodStatusLelongan().getKod().equals("AK")) {
                                listLel.add(ll);
                            }
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
        }
        try {
            if (lelong.getTarikhLelong() != null) {
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

        list = new ArrayList<KodSuku>();
        list = kodSukuDAO.findAll();
        LOG.info("list.size() : " + list.size());
    }

    public Resolution showFormB() {

//            senaraiLelongan = lelongService.listLelonganAK1(permohonan.getIdPermohonan()); //lelongan yg AK
//        LOG.info("Senarai Aktif " + senaraiLelongan.size());
//        LOG.info("Bil Lelongan Aktif " + senaraiLelongan.get(0).getBil());
//        senaraiLelongan2 = lelongService.findLelonganTerdahulu(permohonan.getIdPermohonan(), senaraiLelongan.get(0).getBil());
        senaraiLelongan = lelongService.getTG(permohonan.getPermohonanSebelum().getIdPermohonan());
        LOG.info("Senarai Lelongan Terdahulu " + senaraiLelongan.size());
        tarikhLelongTerdahulu = sdf.format(senaraiLelongan.get(0).getTarikhLelong()).substring(0, 10);
        bilLelong = senaraiLelongan.get(0).getBil();
        //calender
//        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
//        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());

        return new JSP("lelong/calender_lelong2.jsp").addParameter("popup", "true");
    }

    //berasingan utk n.melaka
    public Resolution simpanLelong() {

        InfoAudit ia = new InfoAudit();
        if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
            listLel = lelongService.listLelonganAKTG(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else {
            listLel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
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
            lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }

        //PJTA SUKU
        if (permohonan.getPermohonanSebelum() != null && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PJTA")) {
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
        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            lelongService.saveOrUpdate(enkuiri);
        } else {
            lelongService.saveOrUpdate(enkuiri);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
    }

    //bersama utk n.melaka
    public Resolution saveBersama() throws ParseException {

        InfoAudit ia = new InfoAudit();
        LOG.info("ID MOHON SBLM>>>>>>" + permohonan.getPermohonanSebelum().getIdPermohonan());

        if (permohonan.getKodUrusan().getKod().equals("PPTL")) {
            listLel = lelongService.listLelonganAKTG(permohonan.getPermohonanSebelum().getIdPermohonan());
        } else {
            listLel = lelongService.listLelonganAK(permohonan.getPermohonanSebelum().getIdPermohonan());
        }
        LOG.info("listLel>>>>>>" + listLel.size());
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
            lel.setDeposit(enkuiri.getDeposit());
            lel.setHargaRizab(enkuiri.getHargaRizab());
            lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
            lel.setInfoAudit(ia);
            enService.simpan(lel);
        }

        //PJTA SUKU
        if (permohonan.getPermohonanSebelum() != null && permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PJTA")) {
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

        //negeri malake amaun hutang tertunggak
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            negori = true;
            enkuiri.setAmaunTunggakan(amaunTunggakan);
            enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            lelongService.saveOrUpdate(enkuiri);
        } else {
            enkuiri.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            lelongService.saveOrUpdate(enkuiri);
        }
        rehydrate();
        showForm();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/tetap_tarikh_tangguhLelong.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public List<Lelongan> getSenaraiLelongan() {
        return senaraiLelongan;
    }

    public void setSenaraiLelongan(List<Lelongan> senaraiLelongan) {
        this.senaraiLelongan = senaraiLelongan;
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

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<Enkuiri> getSenaraiEnkuiri3() {
        return senaraiEnkuiri3;
    }

    public void setSenaraiEnkuiri3(List<Enkuiri> senaraiEnkuiri3) {
        this.senaraiEnkuiri3 = senaraiEnkuiri3;
    }

    public List<Lelongan> getSenaraiLelongan3() {
        return senaraiLelongan3;
    }

    public void setSenaraiLelongan3(List<Lelongan> senaraiLelongan3) {
        this.senaraiLelongan3 = senaraiLelongan3;
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

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
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

    public String getKodsuku() {
        return kodsuku;
    }

    public void setKodsuku(String kodsuku) {
        this.kodsuku = kodsuku;
    }

    public List<LelonganSuku> getLelonganList() {
        return lelonganList;
    }

    public void setLelonganList(List<LelonganSuku> lelonganList) {
        this.lelonganList = lelonganList;
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

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public Long getIdLelong() {
        return idLelong;
    }

    public void setIdLelong(Long idLelong) {
        this.idLelong = idLelong;
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
