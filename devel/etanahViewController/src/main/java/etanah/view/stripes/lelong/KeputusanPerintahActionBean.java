/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.*;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.EnkuiriPeminjam;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodPeranan;
import etanah.model.KodSuku;
import etanah.model.KodTuntut;
import etanah.model.Lelongan;
import etanah.model.LelonganSuku;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
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
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mazurahayati.yusop
 */
@UrlBinding("/lelong/keputusan_perintah")
public class KeputusanPerintahActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanPerintahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodPeranan kodPeranan;
    @Inject
    KodPerananDAO kodPeranannDAO;
    @Inject
    PenggunaPeranan penggunaPeranan;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodStatusLelonganDAO kodStatusLelonganDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    LelonganSukuDAO lelonganSukuDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PenggunaPerananDAO penggunaPerananDAO;
    @Inject
    KodSukuDAO kodSukuDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    KodStatusEnkuiriDAO kodStatusEnkuiriDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    CalenderManager manager;
    private Permohonan permohonan;
    private List<Lelongan> senaraiLelongan;
    private List<Lelongan> senaraiLelongan3;
    private List<Enkuiri> senaraiEnkuiri;
    private List<Enkuiri> senaraiEnkuiri3;
    private List<Enkuiri> senaraiEnkuiri4;
    private List<Pengguna> senaraiPT;
    private List<PenggunaPeranan> senaraiPT1;
    private Pengguna pengguna;
    private String idPengguna;
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String tarikhLelong;
    private String jam;
    private String kodperanan;
    private String minit;
    private String ampm;
    private String jam1;
    private String minit1;
    private String ampm1;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhAkhirBayaran;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<FasaPermohonan> senaraifasamohon;
    private String tarikhEnkuiri;
    private String idPermohonan;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private BigDecimal amaunTunggakan2;
    private List<Lelongan> listLel = new ArrayList<Lelongan>();
    private List<Lelongan> listLelongan = new ArrayList<Lelongan>();
    private List<BigDecimal> listHarga = new ArrayList<BigDecimal>();
    private List<BigDecimal> listDeposit = new ArrayList<BigDecimal>();
    private boolean button = false;
    private FasaPermohonan fasaPermohonan;
    private String idHak;
    private boolean hide;
    private boolean PJ = false;
    private String baruKe;
    private boolean error;
    private boolean suratTolak;
    private boolean terima;
    private boolean tutup;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    private String kodsuku;
    private List<KodSuku> list;
    private List<LelonganSuku> listSuku;
    private String tarikhPermohonan;

    @DefaultHandler
    public Resolution showFormA() {
        if (idPermohonan != null) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                suratTolak = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
            } else {
                if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
                    error = false;
                    suratTolak = true;
                    tutup = false;
                    enkuiri.setStatus(kodStatusEnkuiriDAO.findById("T"));
                    addSimpleMessage("Keputusan Adalah Tolak.Sila Jana dan Cetak Surat Tolak Permohonan");
                    return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("BP")) {
                    error = false;
                    suratTolak = true;
                    tutup = false;
                    enkuiri.setStatus(kodStatusEnkuiriDAO.findById("BP"));
                    addSimpleMessage("Keputusan Adalah Batal.Sila Jana dan Cetak Surat Batal Permohonan");
                    return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                }

                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("TT")) {
                        error = false;
                        suratTolak = true;
                        tutup = true;
                        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("TT"));
                        addSimpleMessage("Keputusan Adalah Tutup.Sila Jana dan Cetak Surat Tutup Kes");
                        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                    }
                }
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("ZY")) {
                        error = false;
                        suratTolak = true;
                        tutup = true;
                        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("T"));
                        addSimpleMessage("Keputusan Adalah Tutup.Sila Jana dan Cetak Surat Tutup Kes");
                        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                    }
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("BL")) {
                    error = false;
                    suratTolak = false;
//                    KodStatusEnkuiri kse1 = new KodStatusEnkuiri();
//                    kse1.setKod("BL");
//                    enkuiri.setStatus(kse1);
                    addSimpleMessage("Keputusan Adalah Tangguh Kes Mahkamah.Sila Klik Butang Selesai");
                    return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                    LOG.info("---tangguh---");
                    LOG.info("fasaPermohonan.getUlasan() :" + fasaPermohonan.getUlasan());
                    if (enkuiri != null) {
                        InfoAudit ia = fasaPermohonan.getInfoAudit();
                        String a = sdf1.format(ia.getTarikhMasuk());
                        InfoAudit ia2 = enkuiri.getInfoAudit();
                        String b = sdf1.format(ia2.getTarikhMasuk());
                        if (a == null ? b != null : !a.equals(b)) {
                            LOG.info("---if---");
                            enkuiriBaru();
                        } else {
                            LOG.info("---else---");
                            Date c = ia.getTarikhMasuk();
                            Date d = ia2.getTarikhMasuk();
                            if (d.before(c)) {
                                enkuiriBaru();
                            }
                        }
                    }
                    if (permohonan.getPermohonanSebelum() == null) {
                        senaraiEnkuiri3 = lelongService.getSejarahSiasatan(idPermohonan);
                    } else {
                        List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (list2.isEmpty()) {
                            senaraiEnkuiri3 = lelongService.getSejarahSiasatan(permohonan.getPermohonanSebelum().getIdPermohonan());
                        } else {
                            senaraiEnkuiri3 = lelongService.getSejarahSiasatan(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        }
                    }
                    error = true;
                    suratTolak = false;
                    return new JSP("lelong/testEnkuiri.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("PJ")) {
                    error = true;
                    suratTolak = false;
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
                    return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/testEnkuiri.jsp").addParameter("tab", "true");
    }

    public void enkuiriBaru() {
        LOG.info("---enkuiriBaru----");
        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("TG"));
        LOG.info("fasaPermohonan.getUlasan() :" + fasaPermohonan.getUlasan());
        enkuiri.setUlasanPegawai(fasaPermohonan.getUlasan());
        lelongService.saveOrUpdate(enkuiri);
        enkuiri = new Enkuiri();
        baruKe = "ye";
        tarikhEnkuiri = null;
        jam1 = null;
        minit1 = null;
        ampm1 = null;

        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        if (!listKD.isEmpty()) {
            KodDokumen kodD = kodDokumenDAO.findById("TGLM");
            for (KandunganFolder kf : listKD) {
                if (kf.getDokumen().getKodDokumen().getKod().equals("TG")) {
                    Dokumen dd = kf.getDokumen();
                    dd.setKodDokumen(kodD);
                    lelongService.saveOrUpdatDokumen(dd);
                    kf.setDokumen(dd);
                    lelongService.saveOrUpdate(kf);
                }
                kodD = kodDokumenDAO.findById("SSLM");
                if (kf.getDokumen().getKodDokumen().getKod().equals("SSL")) {
                    Dokumen dd = kf.getDokumen();
                    dd.setKodDokumen(kodD);
                    lelongService.saveOrUpdatDokumen(dd);
                    kf.setDokumen(dd);
                    lelongService.saveOrUpdate(kf);
                }
            }
        }
    }

    public void bayaran() {
        List<PermohonanPihak> list2 = lelongService.getSenaraiPmohonPihakPG(idPermohonan);
        PermohonanTuntutanKos permohonanTuntutanKos = new PermohonanTuntutanKos();
        BigDecimal amaun = new BigDecimal(40);
        Pengguna p = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = p.getKodCawangan();
        InfoAudit info = p.getInfoAudit();
        info.setDimasukOleh(p);
        info.setTarikhMasuk(new java.util.Date());
        KodTuntut kodTuntut = kodTuntutDAO.findById("L01");
        permohonanTuntutanKos.setKodTuntut(kodTuntut);
        permohonanTuntutanKos.setInfoAudit(info);
        permohonanTuntutanKos.setItem("Bayaran Perintah");
        permohonanTuntutanKos.setCawangan(caw);
        permohonanTuntutanKos.setHakmilikPermohonan(permohonan.getSenaraiHakmilik().get(0));
        permohonanTuntutanKos.setPermohonan(permohonan);
        permohonanTuntutanKos.setAmaunTuntutan(amaun);
        permohonanTuntutanKos.setPihak(list2.get(0));
        permohonanTuntutanKos.setKodTransaksi(kodTuntut.getKodKewTrans());
        lelongService.saveOrUpdate(permohonanTuntutanKos);
    }

    public Resolution showFormB() {
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong3.jsp").addParameter("popup", "true");
    }

    public Resolution showFormD() {
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong8.jsp").addParameter("popup", "true");
    }

    public Resolution showFormBb() {

        listLel = lelongService.listLelonganAK(idPermohonan);
        senaraiLelongan = lelongService.listLelonganAKAP1(idPermohonan);
        senaraiEnkuiri3 = lelongService.getSejarahSiasatan(idPermohonan);
        senaraiLelongan3 = lelongService.getLeloganNotInAK2(idPermohonan);

        for (Lelongan ll : senaraiLelongan) {
            if (ll.getKodStatusLelongan().getKod().equals("AK") || (ll.getKodStatusLelongan().getKod().equals("AP"))) {
                lelong = ll;
                break;
            }
        }
        listLelongan = new ArrayList<Lelongan>();

        //added by zt enkuiri null
        if (permohonan.getPermohonanSebelum() == null) {
            enkuiri = lelongService.findEnkuiri(permohonan.getIdPermohonan());

        } else {
            enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
        }

        //bersama
        LOG.info("---enkuiri----" + enkuiri);
        LOG.info("---cara lelong----" + enkuiri.getCaraLelong());

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
            } //berasingan
            else if (enkuiri.getCaraLelong().equals("A")) {
                getContext().getRequest().setAttribute("same", Boolean.FALSE);
            }
            idHak = sb.toString();
        }
        return new JSP("lelong/semak_lelongan.jsp").addParameter("tab", "true");
    }

    public Resolution showFormC() {
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong4.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk()).substring(0, 10);
            List<PermohonanTuntutanKos> listPT = lelongService.listPermohonanTuntutanKos2(idPermohonan);
            LOG.info("listPT : " + listPT.size());
            if (listPT.isEmpty()) {
                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    bayaran();
                }
            }

            if (permohonan.getPermohonanSebelum() == null) {
                senaraifasamohon = lelongService.getPermonanFasa(idPermohonan);
            } else {
                senaraifasamohon = lelongService.getPermonanFasa(permohonan.getPermohonanSebelum().getIdPermohonan());
            }

            LOG.info("senaraifasamohon" + senaraifasamohon.size());

            if (!senaraifasamohon.isEmpty()) {
                LOG.info("senaraifasamohon tak empty" + senaraifasamohon.size());
                fasaPermohonan = senaraifasamohon.get(0);

                LOG.info("fasaPermohonan" + fasaPermohonan.getIdAliran() + " " + fasaPermohonan.getKeputusan());
            }
            if (fasaPermohonan.getKeputusan() != null) {

                LOG.info("fasaPermohonan.getKeputusan() != null");
                senaraiEnkuiri4 = lelongService.getEnkuiriNotAK(idPermohonan);

                if (permohonan.getPermohonanSebelum() == null) {
                    LOG.info("permohonan.getPermohonanSebelum() == null");
                    enkuiri = lelongService.findEnkuiri(idPermohonan);
                } else {
                    LOG.info("permohonan.getPermohonanSebelum() != null");
                    List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (list2.isEmpty()) {
                        enkuiri = lelongService.findEnkuiri(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (enkuiri == null) {
                            List<Enkuiri> list3 = lelongService.getAllDesc(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (!list3.isEmpty()) {
                                enkuiri = list3.get(0);
                            }
                        }
                        senaraiEnkuiri3 = lelongService.getSejarahSiasatan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    } else {
                        enkuiri = lelongService.findEnkuiri(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        if (enkuiri == null) {
                            List<Enkuiri> list3 = lelongService.getAllDesc(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            if (!list3.isEmpty()) {
                                enkuiri = list3.get(0);
                            }
                        }
                        senaraiEnkuiri3 = lelongService.getSejarahSiasatan(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                    }
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("PJ")) {
                    LOG.info("fasaPermohonan.getKeputusan().getKod().equals(\"PJ\")");
                    if (enkuiri == null) {
                        LOG.info("enkuiri == null");
                        List<Enkuiri> en = lelongService.getEnkuiriSblm2(idPermohonan);
                        enkuiri = en.get(0);
                        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                        lelongService.saveOrUpdate(enkuiri);
                        senaraiEnkuiri = lelongService.getEnkuiriList(idPermohonan);
                        if (!senaraiEnkuiri.isEmpty()) {
                            for (Enkuiri ee : senaraiEnkuiri) {
                                if (ee.getStatus().getKod().equals("AK")) {
                                    enkuiri = ee;
                                    break;
                                }
                            }
                        }
                    }
                    senaraiLelongan3 = lelongService.getLeloganNotInAK(enkuiri.getIdEnkuiri());
                    LOG.info("senaraiLelongan3" + senaraiLelongan3.size());
                    senaraiLelongan = lelongService.listLelonganAKAP1(idPermohonan);
                    LOG.info("senaraiLelongan" + senaraiLelongan.size());

                    for (Lelongan ll : senaraiLelongan) {
                        LOG.info("senaraiLelongan " + ll.getKodStatusLelongan());
                        if (ll.getKodStatusLelongan().getKod().equals("AK") || (ll.getKodStatusLelongan().getKod().equals("AP") || (ll.getKodStatusLelongan().getKod().equals("TA")))) {
                            LOG.info("senaraiLelongan " + ll.getKodStatusLelongan());
                            lelong = ll;
                            PJ = true;
                            break;
                        }
                    }

//                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
//
//                    for (Lelongan ll : senaraiLelongan) {
//                        if (ll.getKodStatusLelongan().getKod().equals("AK")) {
//                            lelong = ll;
//                            break;
//                        }
//                    }
                    listLel = lelongService.getLeloganASC(idPermohonan);
                    LOG.info("listLel " + listLel.size());
                    if (listLel.isEmpty()) {
                        LOG.info("listLel EMPTY");
                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new java.util.Date());
                        for (HakmilikPermohonan hh : permohonan.getSenaraiHakmilik()) {
                            LOG.info("CREATE LELONG BARU");
                            Lelongan lel = new Lelongan();
                            lel.setEnkuiri(enkuiri);
                            lel.setBil(1);
                            lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
                            lel.setInfoAudit(ia);
                            lel.setHakmilikPermohonan(hh);
                            lel.setPermohonan(permohonan);
                            lelongService.saveOrUpdate(lel);
                        }
                        listLel = lelongService.getLeloganASC(idPermohonan);
                        LOG.info("listLel " + listLel.size());
                        senaraiLelongan = lelongService.listLelonganAKAP1(idPermohonan);
                        LOG.info("senaraiLelongan" + senaraiLelongan.size());

                        for (Lelongan ll : senaraiLelongan) {
                            LOG.info("senaraiLelongan " + ll.getKodStatusLelongan());
                            if (ll.getKodStatusLelongan().getKod().equals("AK") || (ll.getKodStatusLelongan().getKod().equals("AP"))) {
                                LOG.info("senaraiLelongan " + ll.getKodStatusLelongan());
                                lelong = ll;
                                break;
                            }
                        }

//                        senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
//
//                        for (Lelongan ll : senaraiLelongan) {
//                            if (ll.getKodStatusLelongan().getKod().equals("AK")) {
//                                lelong = ll;
//                                break;
//                            }
//                    }
                    }

                    if (lelong != null) {

                        //for negeri melake
                        if ("04".equals(conf.getProperty("kodNegeri"))) {
                            LOG.info("MELAKA");
                            negori = true;
                            if (enkuiri.getAmaunTunggakan() != null) {
                                amaunTunggakan = enkuiri.getAmaunTunggakan();
                                if (enkuiri.getAmaunGadaian() != null) {
                                    amaunTunggakan2 = enkuiri.getAmaunGadaian();
                                }
                            }

                            if (lelong.getBil() == 1) {
                                senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                                LOG.info("senaraiPT .size :" + senaraiPT.size());
                                if (senaraiPT.equals(null)) {
                                    KodPeranan kodperanan = kodPeranannDAO.findById("pptlelong");
                                    LOG.info(kodperanan);
                                    senaraiPT1 = lelongService.capaiPgunPeranan(kodperanan.getKod());
                                    for (PenggunaPeranan kodP : senaraiPT1) {
                                        Pengguna pguna = kodP.getPengguna();
                                        if (pguna.getKodCawangan().getKod() == pengguna.getKodCawangan().getKod()) {
                                            senaraiPT.add(pguna);
                                        }
                                    }
                                }
                                KodPeranan kodperanan = kodPeranannDAO.findById("pptlelong");
                                LOG.info(kodperanan);
                                senaraiPT1 = lelongService.capaiPgunPeranan(kodperanan.getKod());
                                for (PenggunaPeranan kodP : senaraiPT1) {
                                    Pengguna pguna = kodP.getPengguna();
                                    LOG.info(pguna.getKodCawangan().getName());
                                    LOG.info(pengguna.getKodCawangan().getName());
                                    if (pguna.getKodCawangan().getKod().equals(pengguna.getKodCawangan().getKod())) {
                                        if (pguna.getStatus().getKod().equals("A")) {
                                            senaraiPT.add(pguna);
                                        }
                                    }
                                }
                                if (enkuiri.getPengguna() != null) {
                                    idPengguna = enkuiri.getPengguna().getIdPengguna();
                                }
                            }
                        } else {
                            LOG.info("N9");
                            //negeri 9
                            negori = false;
                            //added by nur.aqilah
                            if (lelong.getBil() == 1) {
                                senaraiPT = lelongService.capaiPTLelong(pengguna.getKodCawangan().getKod());
                                LOG.info("senaraiPT .size :" + senaraiPT.size());
                                if (enkuiri.getPengguna() != null) {
                                    idPengguna = enkuiri.getPengguna().getIdPengguna();
                                }
                            }
                        }

                        if (permohonan.getSenaraiHakmilik().size() == 1) {
                            getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                            getContext().getRequest().setAttribute("same", Boolean.TRUE);
                            if (enkuiri.getCaraLelong() == null) {
                                enkuiri.setCaraLelong("B");
                                lelongService.saveOrUpdate(enkuiri);
                            }
                            //bersama
                            listLelongan = new ArrayList<Lelongan>();
                            senaraiLelongan = lelongService.listLelonganAKAP1(idPermohonan);

                            for (Lelongan ll : senaraiLelongan) {
                                if (ll.getKodStatusLelongan().getKod().equals("AK") || (ll.getKodStatusLelongan().getKod().equals("AP"))) {
                                    lelong = ll;
                                    break;
                                }
                            }

//                    senaraiLelongan = lelongService.getLeloganASC(idPermohonan);
//                    for (Lelongan ll : senaraiLelongan) {
//                        if (ll.getKodStatusLelongan().getKod().equals("AK")) {
//                            lelong = ll;
//                            break;
//                        }
//                    }
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
                            getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
                            if (enkuiri.getCaraLelong() == null) {
                                getContext().getRequest().setAttribute("sblmPilih", Boolean.TRUE);
                            } else {
                                getContext().getRequest().setAttribute("sblmPilih", Boolean.FALSE);
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
                        }

                        if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                        }

                        try {
                            if (lelong != null && lelong.getTarikhLelong() != null) {
                                tarikhAkhirBayaran = sdf1.format(lelong.getTarikhAkhirBayaran());
                                tarikhLelong = sdf.format(lelong.getTarikhLelong()).substring(0, 10);
                                jam = sdf.format(lelong.getTarikhLelong()).substring(11, 13);
                                minit = sdf.format(lelong.getTarikhLelong()).substring(14, 16);
                                ampm = sdf.format(lelong.getTarikhLelong()).substring(17, 19);
                                listSuku = lelongService.listLelongSuku(lelong.getIdLelong());
                            }

                        } catch (Exception ex) {
                            LOG.error("lelong-592 : " + ex);
                        }
                    }
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                    LOG.info("---Tangguh---");
                    List<Notis> list2 = lelongService.getListNotis(idPermohonan, "SSL");
                    LOG.info("list : " + list2.size());
                    for (Notis nn : list2) {
                        if (nn.getKodStatusTerima().getKod().equals("TM")) {
                            terima = true;
                            break;
                        }
                    }
                    LOG.info("terima : " + terima);
                    try {
                        if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                            tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                            jam1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(11, 13);
                            minit1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(14, 16);
                            ampm1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(17, 19);
                        }
                    } catch (Exception ex) {
                        LOG.error("enkuiri-614 : " + ex);
                    }
                }
            }

            list = new ArrayList<KodSuku>();
            list = kodSukuDAO.findAll();
            LOG.info("list.size() : " + list.size());
        }
    }

    public Resolution jenisLelong() {
        String jenisJual = getContext().getRequest().getParameter("sss");
        LOG.info("jenisJual : " + jenisJual);
        enkuiri.setCaraLelong(jenisJual);
        lelongService.saveOrUpdate(enkuiri);
        rehydrate();
        showFormA();
        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
    }

    //berasingan n.melaka
    public Resolution isiMula() {
        for (Lelongan ll : listLel) {
            ll.setDeposit(null);
            ll.setHargaRizab(null);
            ll.setTarikhAkhirBayaran(null);
            ll.setTarikhLelong(null);
            ll.setTempat(null);
            enService.simpan(ll);
        }
        enkuiri.setCaraLelong(null);
        lelongService.saveOrUpdate(enkuiri);
        rehydrate();
        showFormA();
        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
    }

    //bersama n.melaka
    public Resolution isiMulaBersama() {
        if (permohonan.getSenaraiHakmilik().size() >= 2) {
            enkuiri.setTarikhAkhirBayaran(null);
//            enkuiri.setTarikhWarta(null);
            enkuiri.setDeposit(null);
            enkuiri.setHargaRizab(null);
            enkuiri.setCaraLelong(null);
            lelongService.saveOrUpdate(enkuiri);
        }
        rehydrate();
        showFormA();
        return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
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
                LOG.error("tarikhLelong-685 : " + ex);
            }
            try {
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error("tarikhAkhirBayaran-690 : " + ex);
            }

            lel.setTempat(lelong.getTempat());
            lel.setEnkuiri(enkuiri);
            lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
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
//            if (enkuiri.getAmaunGadaian() != null) {
            enkuiri.setAmaunGadaian(amaunTunggakan2);
//            }
            lelongService.saveOrUpdate(enkuiri);
//            rehydrate();
//            showFormA();

            if (lelong.getBil() == 1) {
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                enkuiri.setPengguna(peng);
                lelongService.saveOrUpdate(enkuiri);
            }

//            LOG.info("genReport :: start");
//            LOG.info("generate report start.");
//            String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//            try {
//                LOG.info("genReportFromXML");
//                String gen = "LLGSurat16HPenyerah_MLK.rdf";
//                String code = "S16H";
//                lelongService.reportGen(permohonan, gen, code, pengguna);
//            } catch (Exception ex) {
//                return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//            }
//            LOG.info("genReport :: finish");
            rehydrate();
            showFormA();
//            return new StreamingResolution("text/plain", msg);
//            addSimpleMessage("Maklumat telah berjaya disimpan.Sila Semak Surat Bayaran Perintah Pada Tab Dokumen");
//            return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
        } else {
            lelongService.saveOrUpdate(enkuiri);

            LOG.info("genReport :: start");
            LOG.info("generate report start.");
            String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
            try {
                LOG.info("genReportFromXML");
                String gen = "LLGTuntutBayaranPerintah_NS.rdf";
                String code = "TBP";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            } catch (Exception ex) {
                return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
            }
            LOG.info("genReport :: finish");
            return new StreamingResolution("text/plain", msg);
        }
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
                LOG.error("tarikhLelong-780 : " + ex);
            }
            try {
                lel.setTarikhAkhirBayaran(sdf1.parse(tarikhAkhirBayaran));
            } catch (Exception ex) {
                LOG.error("tarikhAkhirBayaran-785 : " + ex);
            }
            lel.setEnkuiri(enkuiri);
            lel.setDeposit(enkuiri.getDeposit());
            lel.setHargaRizab(enkuiri.getHargaRizab());
            lel.setTempat(lelong.getTempat());
            lel.setKodStatusLelongan(kodStatusLelonganDAO.findById("AK"));
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
            enkuiri.setAmaunGadaian(amaunTunggakan2);

            lelongService.saveOrUpdate(enkuiri);
//            rehydrate();
//            showFormA();

            if (lelong.getBil() == 1) {
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                enkuiri.setPengguna(peng);
                lelongService.saveOrUpdate(enkuiri);
            }

//            LOG.info("genReport :: start");
//            LOG.info("generate report start.");
//            String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
//            try {
//                LOG.info("genReportFromXML");
//                String gen = "LLGSurat16HPenyerah_MLK.rdf";
//                String code = "S16H";
//                lelongService.reportGen(permohonan, gen, code, pengguna);
//            } catch (Exception ex) {
//                return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
//            }
//            LOG.info("genReport :: finish");
            rehydrate();
            showFormA();
//            return new StreamingResolution("text/plain", msg);
//            addSimpleMessage("Maklumat telah berjaya disimpan.Sila Semak Surat Bayaran Perintah Pada Tab Dokumen");
//            return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            return new JSP("lelong/keputusanPerintah.jsp").addParameter("tab", "true");
        } else {
            lelongService.saveOrUpdate(enkuiri);

            //added by nur.aqilah
            if (lelong.getBil() == 1) {
                Pengguna peng = lelongService.findPT(getContext().getRequest().getParameter("idPengguna"));
                enkuiri.setPengguna(peng);
                lelongService.saveOrUpdate(enkuiri);
            }
            LOG.info("genReport :: start");
            LOG.info("generate report start.");
            String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
            try {
                LOG.info("genReportFromXML");
                String gen = "LLGTuntutBayaranPerintah_NS.rdf";
                String code = "TBP";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            } catch (Exception ex) {
                return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
            }
            LOG.info("genReport :: finish");
            return new StreamingResolution("text/plain", msg);
        }
    }

    public Resolution simpanEnkuiri() {

        Enkuiri enk = new Enkuiri();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        List<Enkuiri> enkuirile = new ArrayList<Enkuiri>();
        LOG.info("Enkuiri : " + enkuiri);
        if (StringUtils.isNotEmpty(baruKe) || enkuiri == null) {
            LOG.info("----baru----");
            enkuirile = lelongService.findEnkuiriTG(idPermohonan);
            enk = new Enkuiri();
            enk.setBilanganKes(enkuirile.get(0).getBilanganKes() + 1);
        } else {
            LOG.info("----tak----");
            enkuirile = lelongService.findEnkuiriTG(idPermohonan);
            enk = lelongService.findEnkuiri(idPermohonan);
        }
        senaraiEnkuiri = lelongService.getEnkuiri(idPermohonan);
        for (Enkuiri ee : senaraiEnkuiri) {
            if (ee.getStatus().getKod().equals("AK")) {
                ee.setStatus(kodStatusEnkuiriDAO.findById("TG"));
                enService.save(ee);
            }
        }

        enk.setPermohonan(permohonan);
        KodCawangan kc = pengguna.getKodCawangan();
        enk.setCawangan(kc);
        enk.setInfoAudit(ia);
        tarikhEnkuiri = tarikhEnkuiri + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);
        try {
            enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error("tarikhEnkuiri-892 : " + ex);
        }

        enk.setTempat(getContext().getRequest().getParameter("enkuiri.tempat"));
        enk.setPerkara(getContext().getRequest().getParameter("enkuiri.perkara"));
        enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        enk.setTujuanGadaian(enkuirile.get(0).getTujuanGadaian());
        LOG.info("conf.getProperty() : " + conf.getProperty("kodNegeri"));
        Enkuiri enOld = enkuirile.get(0);
        enk.setAmaunTunggakan(enOld.getAmaunTunggakan());
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("--negeri--");
            //negeri9
            LOG.info("enkuirile.size : " + enkuirile.size());

            LOG.info("enOld.getIdEnkuiri() : " + enOld.getIdEnkuiri());
            //enk.setNamaPeminjam(enOld.getNamaPeminjam());
            enk.setAmaunGadaian(enOld.getAmaunGadaian());
            enk.setTujuanPinjam(enOld.getTujuanPinjam());
            enk.setTarikhGadaian(enOld.getTarikhGadaian());
            enk.setKadarFaedahGadaian(enOld.getKadarFaedahGadaian());
            enk.setTempohTunggakan(enOld.getTempohTunggakan());
            enk.setKadarBayaranBalik(enOld.getKadarBayaranBalik());
            enk.setTarikhMulaBayarPinjaman(enOld.getTarikhMulaBayarPinjaman());
            enk.setTarikhMulaTunggakan(enOld.getTarikhMulaTunggakan());
            enk.setAmaunGadaianDilangsai(enOld.getAmaunGadaianDilangsai());
            enk.setBakiGadaian(enOld.getBakiGadaian());
            enk.setTarikhWarta(enOld.getTarikhWarta());
        }
        enService.save(enk);
        List<EnkuiriPeminjam> listEP = lelongService.getPeminjam(enOld.getIdEnkuiri());
        if (!listEP.isEmpty()) {
            for (EnkuiriPeminjam ep : listEP) {
                ep.setEnkuiri(enk);
                lelongService.saveOrUpdate(ep);
            }
        }
        rehydrate();
        showFormA();
        hide = true;
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("lelong/testEnkuiri.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEnkuiri2() {

        Enkuiri enk = new Enkuiri();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        List<Enkuiri> enkuirile = new ArrayList<Enkuiri>();
        LOG.info("Enkuiri : " + enkuiri);
        if (StringUtils.isNotEmpty(baruKe) || enkuiri == null) {
            LOG.info("----baru----");
            enkuirile = lelongService.findEnkuiriTG(idPermohonan);
            enk = new Enkuiri();
            enk.setBilanganKes(enkuirile.get(0).getBilanganKes() + 1);
        } else {
            LOG.info("----tak----");
            enkuirile = lelongService.findEnkuiriTG(idPermohonan);
            enk = lelongService.findEnkuiri(idPermohonan);
        }
        senaraiEnkuiri = lelongService.getEnkuiri(idPermohonan);
        for (Enkuiri ee : senaraiEnkuiri) {
            if (ee.getStatus().getKod().equals("AK")) {
                ee.setStatus(kodStatusEnkuiriDAO.findById("TG"));
                enService.save(ee);
            }
        }

        enk.setPermohonan(permohonan);
        KodCawangan kc = pengguna.getKodCawangan();
        enk.setCawangan(kc);
        enk.setInfoAudit(ia);
        tarikhEnkuiri = tarikhEnkuiri + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);
        try {
            enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error("tarikhEnkuiri968 : " + ex);
        }
        enk.setTempat(getContext().getRequest().getParameter("enkuiri.tempat"));
        enk.setPerkara(getContext().getRequest().getParameter("enkuiri.perkara"));
        enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        enk.setTujuanGadaian(enkuirile.get(0).getTujuanGadaian());
        LOG.info("conf.getProperty() : " + conf.getProperty("kodNegeri"));
        Enkuiri enOld = enkuirile.get(0);
        enk.setAmaunTunggakan(enOld.getAmaunTunggakan());
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            LOG.info("--negeri--");
            //negeri9
            LOG.info("enkuirile.size : " + enkuirile.size());
            LOG.info("enOld.getIdEnkuiri() : " + enOld.getIdEnkuiri());
            //enk.setNamaPeminjam(enOld.getNamaPeminjam());
            enk.setAmaunGadaian(enOld.getAmaunGadaian());
            enk.setTujuanPinjam(enOld.getTujuanPinjam());
            enk.setTarikhGadaian(enOld.getTarikhGadaian());
            enk.setKadarFaedahGadaian(enOld.getKadarFaedahGadaian());
            enk.setTempohTunggakan(enOld.getTempohTunggakan());
            enk.setKadarBayaranBalik(enOld.getKadarBayaranBalik());
            enk.setTarikhMulaBayarPinjaman(enOld.getTarikhMulaBayarPinjaman());
            enk.setTarikhMulaTunggakan(enOld.getTarikhMulaTunggakan());
            enk.setAmaunGadaianDilangsai(enOld.getAmaunGadaianDilangsai());
            enk.setBakiGadaian(enOld.getBakiGadaian());
            enk.setTarikhWarta(enOld.getTarikhWarta());
        }
        enService.save(enk);
        List<EnkuiriPeminjam> listEP = lelongService.getPeminjam(enOld.getIdEnkuiri());
        if (!listEP.isEmpty()) {
            for (EnkuiriPeminjam ep : listEP) {
                ep.setEnkuiri(enk);
                lelongService.saveOrUpdate(ep);
            }
        }

        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan Telah Dijana. Sila Buat Semakan Sebelum Cetakan.";
        try {
            LOG.info("genReportFromXML");
            String gen = "";
            String code = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                gen = "LLGTangguhSiasatan_MLK.rdf";
                code = "TG";
                lelongService.reportGen(permohonan, gen, code, pengguna);
                gen = "LLGSuratSiasatan_MLK.rdf";
                code = "SSL";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                gen = "LLGSuratTangguh_NS.rdf";
                code = "TG";
                lelongService.reportGen(permohonan, gen, code, pengguna);
                gen = "LLGSuratSiasatan_NS.rdf";
                code = "SSL";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
    }

    public Resolution genReport() {
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan telah dijana. Sila buat semakan sebelum cetakan.";
        try {
            LOG.info("genReportFromXML");
            String gen = "";
            String code = "";
            //melaka
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                    gen = "LLGTangguhSiasatan_MLK.rdf";
                    code = "TG";
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
                    gen = "LLGSuratTolakPermohonan_MLK.rdf";
                    code = "STP";
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("ZY")) {
                    gen = "LLGSuratTutupKes_MLK.rdf";
                    code = "STK";
                }
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
            //negeri 9
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                    gen = "LLGSuratTangguh_NS.rdf";
                    code = "TG";
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("T")) {
                    gen = "LLGSuratTolakPermohonan_NS.rdf";
                    code = "STP";
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("BP")) {
                    gen = "LLGSuratBatal_NS.rdf";
                    code = "SB";
                }
                lelongService.reportGen(permohonan, gen, code, pengguna);
                gen = "LLGSuratSiasatan_NS.rdf";
                code = "SSL";
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }

        } catch (Exception ex) {
            return new StreamingResolution("text/plain", "Dokumen Tidak Dapat Dijana." + ex.getMessage());
        }
        LOG.info("genReport :: finish");
        return new StreamingResolution("text/plain", msg);
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

    public Lelongan getLelong() {
        return lelong;
    }

    public void setLelong(Lelongan lelong) {
        this.lelong = lelong;
    }

    public String getTarikhAkhirBayaran() {
        return tarikhAkhirBayaran;
    }

    public void setTarikhAkhirBayaran(String tarikhAkhirBayaran) {
        this.tarikhAkhirBayaran = tarikhAkhirBayaran;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }

    public String getTarikhEnkuiri() {
        return tarikhEnkuiri;
    }

    public void setTarikhEnkuiri(String tarikhEnkuiri) {
        this.tarikhEnkuiri = tarikhEnkuiri;
    }

    public String getJam1() {
        return jam1;
    }

    public void setJam1(String jam1) {
        this.jam1 = jam1;
    }

    public String getMinit1() {
        return minit1;
    }

    public void setMinit1(String minit1) {
        this.minit1 = minit1;
    }

    public String getAmpm1() {
        return ampm1;
    }

    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }

    public List<FasaPermohonan> getSenaraifasamohon() {
        return senaraifasamohon;
    }

    public void setSenaraifasamohon(List<FasaPermohonan> senaraifasamohon) {
        this.senaraifasamohon = senaraifasamohon;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public boolean isNegori() {
        return negori;
    }

    public void setNegori(boolean negori) {
        this.negori = negori;
    }

    public BigDecimal getAmaunTunggakan() {
        return amaunTunggakan;
    }

    public void setAmaunTunggakan(BigDecimal amaunTunggakan) {
        this.amaunTunggakan = amaunTunggakan;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }

    public List<Lelongan> getListLel() {
        return listLel;
    }

    public void setListLel(List<Lelongan> listLel) {
        this.listLel = listLel;
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

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
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

    public String getBaruKe() {
        return baruKe;
    }

    public void setBaruKe(String baruKe) {
        this.baruKe = baruKe;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuratTolak() {
        return suratTolak;
    }

    public void setSuratTolak(boolean suratTolak) {
        this.suratTolak = suratTolak;
    }

    public boolean isTerima() {
        return terima;
    }

    public void setTerima(boolean terima) {
        this.terima = terima;
    }

    public boolean isTutup() {
        return tutup;
    }

    public void setTutup(boolean tutup) {
        this.tutup = tutup;
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

    public List<Enkuiri> getSenaraiEnkuiri4() {
        return senaraiEnkuiri4;
    }

    public void setSenaraiEnkuiri4(List<Enkuiri> senaraiEnkuiri4) {
        this.senaraiEnkuiri4 = senaraiEnkuiri4;
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

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public List<Pengguna> getSenaraiPT() {
        return senaraiPT;
    }

    public void setSenaraiPT(List<Pengguna> senaraiPT) {
        this.senaraiPT = senaraiPT;
    }

    public BigDecimal getAmaunTunggakan2() {
        return amaunTunggakan2;
    }

    public void setAmaunTunggakan2(BigDecimal amaunTunggakan2) {
        this.amaunTunggakan2 = amaunTunggakan2;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public boolean isPJ() {
        return PJ;
    }

    public void setPJ(boolean PJ) {
        this.PJ = PJ;
    }
}
