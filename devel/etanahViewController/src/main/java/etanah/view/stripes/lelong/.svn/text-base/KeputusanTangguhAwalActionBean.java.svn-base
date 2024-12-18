/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodStatusEnkuiriDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Enkuiri;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Lelongan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.service.common.EnkuiriService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
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
@UrlBinding("/lelong/keputusan_tangguh_awal")
public class KeputusanTangguhAwalActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KeputusanTangguhAwalActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnkuiriService enService;
    @Inject
    LelongService lelongService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
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
    private Lelongan lelong;
    private Enkuiri enkuiri;
    private String jam1;
    private String minit1;
    private String ampm1;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String tarikhAkhirBayaran;
    private List<FasaPermohonan> senaraifasamohon;
    private List<FasaPermohonan> senaraifasamohon1;
    private String tarikhEnkuiri;
    private String idPermohonan;
    private boolean negori;
    private BigDecimal amaunTunggakan;
    private boolean button = false;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonan1;
    private Pengguna pengguna;
    private boolean hide;
    private String baruKe;
    private boolean error;
    private boolean suratTolak;
    private boolean terima;
    private boolean tutup;
    private List<CalenderLelong> listCalender;
    private List<CalenderLelong> listCalender2;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    private String status;
    private String tempat;
    private String perkara;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("---showForm---");

        if (idPermohonan != null) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
            } else {
//                if (fasaPermohonan.getKeputusan().getKod().equals("XN")) {
                if (fasaPermohonan.getKeputusan().getKod().equals("TX")||fasaPermohonan.getKeputusan().getKod().equals("XN")) {
                    error = false;
                    if (enkuiri == null) {
                        List<Enkuiri> en = lelongService.getEnkuiriSblm2(idPermohonan);
                        enkuiri = en.get(0);
                        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                        lelongService.saveOrUpdate(enkuiri);
                    }
                    addSimpleMessage("Keputusan Adalah Tiada Penangguhan.Sila Tekan Butang Selesai");
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("AG")) {

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
                        if (permohonan.getPermohonanSebelum() == null) {
                            senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
                        } else {
                            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (list2.isEmpty()) {
                                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                            } else {
                                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            }
                        }
                    }
                    error = true;
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
    }

    public void enkuiriBaru() {
        
        //added by syazwan(14/10/2013)
        //added new row for table enkuiri with kod_sts AK
        LOG.info("----enkuiriBaru----");
        
        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("TG"));
        lelongService.saveOrUpdate(enkuiri);
        
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        
        KodCawangan kc = pengguna.getKodCawangan();
        
        //save new enkuiri
        enkuiri = new Enkuiri();
        enkuiri.setBilanganKes(1);
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        enkuiri.setCawangan(kc);
        enkuiri.setInfoAudit(ia);       
        enkuiri.setPermohonan(permohonan);
        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        enService.save(enkuiri);
        
        baruKe = "ye";
        tarikhEnkuiri = null;
        jam1 = null;
        minit1 = null;
        ampm1 = null;
        FolderDokumen fd = permohonan.getFolderDokumen();
        List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
        if (!listKD.isEmpty()) {
            KodDokumen kodD = null;
            for (KandunganFolder kf : listKD) {
                if (kf.getDokumen().getKodDokumen().getKod().equals("SSL")) {
                    kodD = kodDokumenDAO.findById("SSLM");
                    Dokumen d2 = kf.getDokumen();
                    d2.setKodDokumen(kodD);
                    lelongService.saveOrUpdatDokumen(d2);
                    kf.setDokumen(d2);
                    lelongService.saveOrUpdate(kf);
                }
                if (kf.getDokumen().getKodDokumen().getKod().equals("TG")) {
                    kodD = kodDokumenDAO.findById("TGLM");
                    Dokumen d2 = kf.getDokumen();
                    d2.setKodDokumen(kodD);
                    lelongService.saveOrUpdatDokumen(d2);
                    kf.setDokumen(d2);
                    lelongService.saveOrUpdate(kf);
                }
            }
        }
    }

    public Resolution showFormNS() {
        LOG.info("---showForm---");

        if (idPermohonan != null) {
            if (fasaPermohonan.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
            } else {
//                if (fasaPermohonan.getKeputusan().getKod().equals("XN")) {
                if (fasaPermohonan.getKeputusan().getKod().equals("TX")) {
                    error = false;
                    if (enkuiri == null) {
                        List<Enkuiri> en = lelongService.getEnkuiriSblm2(idPermohonan);
                        enkuiri = en.get(0);
                        enkuiri.setStatus(kodStatusEnkuiriDAO.findById("AK"));
                        lelongService.saveOrUpdate(enkuiri);
                    }
                    addSimpleMessage("Keputusan Adalah Tiada Penangguhan.Sila Tekan Butang Selesai");
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("AG") || fasaPermohonan.getKeputusan().getKod().equals("WR")) {

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

                            if (fasaPermohonan.getKeputusan().getKod().equals("WR")) {
                                FolderDokumen fd = permohonan.getFolderDokumen();
                                List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
                                if (!listKD.isEmpty()) {
                                    KodDokumen kodD = null;
                                    for (KandunganFolder kf : listKD) {
                                        if (kf.getDokumen().getKodDokumen().getKod().equals("SW")) {
                                            kodD = kodDokumenDAO.findById("SWLM");
                                            Dokumen d = kf.getDokumen();
                                            d.setKodDokumen(kodD);
                                            lelongService.saveOrUpdatDokumen(d);
                                            kf.setDokumen(d);
                                            lelongService.saveOrUpdate(kf);
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            LOG.info("---else---");
                            Date c = ia.getTarikhMasuk();
                            Date d = ia2.getTarikhMasuk();
                            if (d.before(c)) {
                                enkuiriBaru();
                                FolderDokumen fd = permohonan.getFolderDokumen();
                                if (fasaPermohonan.getKeputusan().getKod().equals("WR")) {
                                    List<KandunganFolder> listKD = lelongService.getListALLDokumen(fd.getFolderId());
                                    if (!listKD.isEmpty()) {
                                        KodDokumen kodD = null;
                                        for (KandunganFolder kf : listKD) {
                                            if (kf.getDokumen().getKodDokumen().getKod().equals("SWT")) {
                                                kodD = kodDokumenDAO.findById("SWLM");
                                                Dokumen dok = kf.getDokumen();
                                                dok.setKodDokumen(kodD);
                                                lelongService.saveOrUpdatDokumen(dok);
                                                kf.setDokumen(dok);
                                                lelongService.saveOrUpdate(kf);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (permohonan.getPermohonanSebelum() == null) {
                            senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
                        } else {
                            List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                            if (list2.isEmpty()) {
                                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                            } else {
                                senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                            }
                        }
                    }
                    error = true;
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
    }

    public Resolution showFormB() {
        LOG.info("---showForm B---");

        if (idPermohonan != null) {
            if (fasaPermohonan1.getKeputusan() == null) {
                error = false;
                addSimpleError("Sila masukkan keputusan terlebih dahulu di tab keputusan");
                return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
            } else {
                if (fasaPermohonan1.getKeputusan().getKod().equals("BS")) {
                    error = false;
                    addSimpleMessage("Keputusan Adalah Teruskan Siasatan.Sila Tekan Butang Selesai");
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
                if (fasaPermohonan1.getKeputusan().getKod().equals("WN")) {
                    LOG.info("---tangguh---");
                    LOG.info("fasaPermohonan.getUlasan() :" + fasaPermohonan1.getUlasan());
                    if (enkuiri != null) {
                        InfoAudit ia = fasaPermohonan1.getInfoAudit();
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
                        senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);
                    } else {
                        List<PermohonanAsal> list2 = lelongService.listMohonAsal2(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (list2.isEmpty()) {
                            senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(permohonan.getPermohonanSebelum().getIdPermohonan());
                        } else {
                            senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(list2.get(0).getIdPermohonanAsal().getIdPermohonan());
                        }
                    }
                    error = true;
                    return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
                }
            }
        }
        return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
    }

    public Resolution showFormC() {
        //calender
        listCalender = manager.getALLEnkuri(permohonan.getCawangan().getKod());
        listCalender2 = manager.getALLLelongan(permohonan.getCawangan().getKod());
        return new JSP("lelong/calender_lelong6.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            senaraiEnkuiri3 = lelongService.getEnkuiriNotAK(idPermohonan);

            senaraifasamohon = lelongService.getPermohonanMohonTangguh(idPermohonan);
            senaraifasamohon1 = lelongService.getPermohonanNotisGantian(idPermohonan);
            enkuiri = lelongService.findEnkuiri(idPermohonan);

            //mohon tangguh
            if (!senaraifasamohon.isEmpty()) {
                fasaPermohonan = senaraifasamohon.get(0);
            }

            //notis gantian
            if (!senaraifasamohon1.isEmpty()) {
                fasaPermohonan1 = senaraifasamohon1.get(0);
            }

            try {
                if (enkuiri != null && enkuiri.getTarikhEnkuiri() != null) {
                    tarikhEnkuiri = sdf.format(enkuiri.getTarikhEnkuiri()).substring(0, 10);
                    jam1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(11, 13);
                    minit1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(14, 16);
                    ampm1 = sdf.format(enkuiri.getTarikhEnkuiri()).substring(17, 19);
                }
            } catch (Exception ex) {
                LOG.error(ex);
            }
        }
    }

    public Resolution simpanEnkuiriAwal() {

        Enkuiri enk = new Enkuiri();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        List<Enkuiri> enkuiriTG = new ArrayList<Enkuiri>();
        LOG.info("Enkuiri : " + enkuiri);
        if (StringUtils.isNotEmpty(baruKe) || enkuiri == null) {
            LOG.info("----baru----");
            enkuiriTG = lelongService.findEnkuiriTG(idPermohonan);
            enk = new Enkuiri();
            enk.setBilanganKes(enkuiriTG.get(0).getBilanganKes() + 1);
        } else {
            LOG.info("----tak----");
            enkuiriTG = lelongService.findEnkuiriTG(idPermohonan);
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
        enk.setCawangan(pengguna.getKodCawangan());
        enk.setInfoAudit(ia);
        tarikhEnkuiri = tarikhEnkuiri + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

        try {
            enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error(ex);
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            enk.setAmaunTunggakan(enkuiriTG.get(0).getAmaunTunggakan());
        }
        enk.setTempat(getContext().getRequest().getParameter("enkuiri.tempat"));
        enk.setPerkara(getContext().getRequest().getParameter("enkuiri.perkara"));
        enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        enk.setTujuanGadaian(enkuiriTG.get(0).getTujuanGadaian());
        enService.save(enk);
        error = true;

        rehydrate();
        LOG.info("genReport :: start");
        LOG.info("generate report start.");
        String msg = "Laporan Telah Dijana. Sila Buat Semakan Sebelum Cetakan.";
        try {
            LOG.info("genReportFromXML");
            String gen = "";
            String code = "";
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (fasaPermohonan.getKeputusan().getKod().equals("AG")) {
                    gen = "LLGTangguhSiasatan_MLK.rdf";
                    code = "TG";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                    gen = "LLGSuratSiasatanTerima_MLK.rdf";
                    code = "SSL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                }
                if (fasaPermohonan.getKeputusan().getKod().equals("WR")) {
//                    gen = "LLGSuratWarta.rdf";
                    gen = "LLGSuratWarta_MLK.rdf";
                    code = "SWT";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                    gen = "LLGSuratSiasatanTerima_MLK.rdf";
                    code = "SSL";
                    lelongService.reportGen(permohonan, gen, code, pengguna);
                }
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

    //added by nur.aqilah
    //stage semakRekodPenghantaran
    public Resolution simpanEnkuiriSemakRekodPenghantaran() {

        Enkuiri enk = new Enkuiri();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        enkuiri = lelongService.findEnkuiri(idPermohonan);
        List<Enkuiri> enkuiriTG = new ArrayList<Enkuiri>();
        LOG.info("Enkuiri : " + enkuiri);
        if (StringUtils.isNotEmpty(baruKe) || enkuiri == null) {
            LOG.info("----baru----");
            enkuiriTG = lelongService.findEnkuiriTG(idPermohonan);
            enk = new Enkuiri();
            enk.setBilanganKes(enkuiriTG.get(0).getBilanganKes() + 1);
        } else {
            LOG.info("----tak----");
            enkuiriTG = lelongService.findEnkuiriTG(idPermohonan);
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
        enk.setCawangan(pengguna.getKodCawangan());
        enk.setInfoAudit(ia);

        tarikhEnkuiri = tarikhEnkuiri + " " + jam1 + ":" + " " + minit1 + " " + ampm1;
        LOG.debug("tarikhEnkuiri :" + tarikhEnkuiri);

        try {
            enk.setTarikhEnkuiri(sdf.parse(tarikhEnkuiri));
        } catch (Exception ex) {
            LOG.error(ex);
        }
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            enk.setAmaunTunggakan(enkuiriTG.get(0).getAmaunTunggakan());
        }
        enk.setTempat(getContext().getRequest().getParameter("enkuiri.tempat"));
        enk.setPerkara(getContext().getRequest().getParameter("enkuiri.perkara"));
        enk.setStatus(kodStatusEnkuiriDAO.findById("AK"));
        enk.setTujuanGadaian(enkuiriTG.get(0).getTujuanGadaian());
        enService.save(enk);
        error = true;
        
        enkuiri= lelongService.findEnkuiri(permohonan.getIdPermohonan());
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new JSP("lelong/tangguhAwal.jsp").addParameter("tab", "true");
    }

    public Enkuiri getEnkuiri() {
        return enkuiri;
    }

    public void setEnkuiri(Enkuiri enkuiri) {
        this.enkuiri = enkuiri;
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

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPerkara() {
        return perkara;
    }

    public void setPerkara(String perkara) {
        this.perkara = perkara;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public List<FasaPermohonan> getSenaraifasamohon1() {
        return senaraifasamohon1;
    }

    public void setSenaraifasamohon1(List<FasaPermohonan> senaraifasamohon1) {
        this.senaraifasamohon1 = senaraifasamohon1;
    }

    public FasaPermohonan getFasaPermohonan1() {
        return fasaPermohonan1;
    }

    public void setFasaPermohonan1(FasaPermohonan fasaPermohonan1) {
        this.fasaPermohonan1 = fasaPermohonan1;
    }
}
