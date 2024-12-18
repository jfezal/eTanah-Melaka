/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodPerintahDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KandunganFolder;
import etanah.model.KodCaraPenghantaran;
import etanah.model.KodPerintah;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.PermohonanHubungan;
import etanah.service.RegService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.NotisService;
import etanah.service.common.PermohonanService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Session;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
@UrlBinding("/daftar/kaveat")
public class Kaveat extends AbleActionBean {

    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private String tkhSidang;
    private String tkhRujukan;
    private String tkhTamat;
    private String tkhKuatKuasa;
    private String tkhNotis;
    private String tkhTampal;
    private String tkhWarta;
    private String tkhHantar;
    private String kodStatusTerima;
    private String kodCaraPenghantaran;
    private List<String> listIdHakmilik;
    @Inject
    PermohonanRujukanLuarService service;
    @Inject
    private PermohonanService permohonanService;
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodPerintahDAO kodPerintahDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    RegService regService;
    @Inject
    NotisService notisService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodJabatanDAO kodJabatanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    private Notis notis;
    private Pengguna pengguna;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger LOG = Logger.getLogger(Kaveat.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private String copyToAllHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        String perintahMahkamah = getContext().getRequest().getParameter("mahkamah");
        if (StringUtils.isNotBlank(perintahMahkamah)) {
            getContext().getRequest().setAttribute("perintahMahkamah", Boolean.TRUE);
        }

        loadHakmilik();
//        if (senaraiHakmilik.size() > 1) {
//            return new JSP("daftar/kemasukan_perintah_main.jsp").addParameter("tab", "true");
//        } else
//            return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
        return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
    }

    public Resolution doGetPartialPage() {
        reload();
        return new JSP("daftar/kemasukan_perintah_partial.jsp").addParameter("tab", "true");
    }

    public void reload() {
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (isDebug) {
            LOG.debug("id hakmilik =" + idHakmilik);
            LOG.debug("id permohonan =" + idPermohonan);
        }
        if (StringUtils.isNotBlank(idHakmilik)) {
            permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonan);
            getContext().getRequest().setAttribute("idHakmilik", idHakmilik);
            if (permohonanRujukanLuar != null) {
                if (permohonanRujukanLuar.getKodPerintah() != null) {
                    getContext().getRequest().setAttribute("kodPerintah", permohonanRujukanLuar.getKodPerintah().getKod());
                }
                getContext().getRequest().setAttribute("hari", permohonanRujukanLuar.getTempohHari() == null ? 0 : permohonanRujukanLuar.getTempohHari());
                getContext().getRequest().setAttribute("bln", permohonanRujukanLuar.getTempohBulan() == null ? 0 : permohonanRujukanLuar.getTempohBulan());
                getContext().getRequest().setAttribute("thn", permohonanRujukanLuar.getTempohTahun() == null ? 0 : permohonanRujukanLuar.getTempohTahun());
                if (permohonanRujukanLuar.getTarikhSidang() != null) {
                    tkhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
                }
                if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                    tkhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
                }
                if (permohonanRujukanLuar.getTarikhTamat() != null) {
                    tkhTamat = sdf.format(permohonanRujukanLuar.getTarikhTamat());
                }
                if (permohonanRujukanLuar.getTarikhKuatkuasa() != null) {
                    tkhKuatKuasa = sdf.format(permohonanRujukanLuar.getTarikhKuatkuasa());
                }
            }
        }
    }

    private void loadHakmilik() {
        if (permohonan != null) {
            listIdHakmilik = new ArrayList<String>();
//            Map<String,String> map = new HashMap<String,String>();
            senaraiHakmilik = permohonan.getSenaraiHakmilik();
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                Hakmilik hk = hp.getHakmilik();
                String idHakmilik = hk.getIdHakmilik();
                listIdHakmilik.add(idHakmilik);
//                map.put(idHakmilik, idHakmilik);

            }
//            listIdHakmilik.add(map);
        }
    }

    public Resolution reloadHakmilik() {
        reload();
//        loadHakmilik();
//        return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
        return showForm();
    }

    public Resolution reloadNotisHakmilik() {

        return showFormNotis();
    }

    public Resolution showKaveatUlasan() {
        return new JSP("daftar/kemasukan_maklumat_kaveat.jsp").addParameter("tab", "true");
    }

    public Resolution saveKaveatUlasan() {
        return showKaveatUlasan();
    }

    public Resolution showFormNotis() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);

        senaraiHakmilik = permohonan.getSenaraiHakmilik();

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (StringUtils.isNotBlank(idHakmilik)) {
            LOG.debug("id hakmilik = " + idHakmilik);
        } else {
            if (senaraiHakmilik.size() > 0) {
                idHakmilik = senaraiHakmilik.get(0).getHakmilik().getIdHakmilik();
            }
        }

        notis = notisService.getNotis(idPermohonan, idHakmilik);
        if (notis != null) {
            if (notis.getTarikhNotis() != null) {
                tkhNotis = sdf.format(notis.getTarikhNotis());
            }
            if (notis.getTarikhTampal() != null) {
                tkhTampal = sdf.format(notis.getTarikhTampal());
            }
            if (notis.getTarikhHantar() != null) {
                tkhHantar = sdf.format(notis.getTarikhHantar());
            }

            if (notis.getWarta() != null) {
                if (notis.getWarta().getTarikhRujukan() != null) {
                    tkhWarta = sdf.format(notis.getWarta().getTarikhRujukan());
                }
                if (notis.getWarta().getTarikhTamat() != null) {
                    tkhTamat = sdf.format(notis.getWarta().getTarikhTamat());
                }
            }

            if (notis.getKodStatusTerima() != null) {
                LOG.info("-------------- notis.getKodStatusTerima().toString() : " + notis.getKodStatusTerima().getKod());
                kodStatusTerima = notis.getKodStatusTerima().getKod();
            }

            if (notis.getKodCaraPenghantaran() != null) {
                LOG.info("-------------- notis.getKodCaraPenghantaran().getKod() : " + notis.getKodCaraPenghantaran().getKod());
                kodCaraPenghantaran = notis.getKodCaraPenghantaran().getKod();

            }

        } else {

//            addSimpleError("Notis Belum dijana. Sila Jana Notis terlebih dahulu.");
            getContext().getRequest().setAttribute("noEdit", "");
        }
        LOG.debug("tarikh tamat = " + tkhTamat);
        return new JSP("daftar/notis_kaveat.jsp").addParameter("tab", "true");
    }

    public Resolution saveNotisInfo() {

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String stsPenyampaian = getContext().getRequest().getParameter("kodStatusTerima");
        String caraHantar = getContext().getRequest().getParameter("kodCaraPenghantaran");
        String penerimaNotis = getContext().getRequest().getParameter("notis.penerimaNotis");

        LOG.info("-------------- stsPenyampaian : " + stsPenyampaian);
        LOG.info("-------------- caraHantar : " + caraHantar);
        LOG.info("-------------- penerimaNotis : " + penerimaNotis);
        LOG.info("-------------- tkhNotis : " + tkhNotis);
        LOG.info("-------------- tkhTampal : " + tkhTampal);
        LOG.info("-------------- tkhWarta : " + tkhWarta);
        LOG.info("-------------- tkhHantar : " + tkhHantar);
        LOG.info("-------------- tkhTamat : " + tkhTamat);

        boolean toAllHakmiliks = false;

        try {
            InfoAudit ia = new InfoAudit();

            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            PermohonanRujukanLuar ruj = null;

//            if (notis != null) {
//                ruj = notis.getWarta();
//            }
            if ("1".equals(copyToAllHakmilik)) {
                toAllHakmiliks = true;
            }

            for (HakmilikPermohonan hp : senaraiHakmilik) {

                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }

                Hakmilik hm = hp.getHakmilik();
                if (!hm.getIdHakmilik().equals(idHakmilik)
                        && (!toAllHakmiliks)) {
                    continue;
                }

                Notis notis2 = notisService.getNotis(permohonan.getIdPermohonan(), hp.getHakmilik().getIdHakmilik());
                if (notis2 != null) {
                    LOG.info("Update Existing Record");
                    ruj = notis2.getWarta();

                    if (ruj == null) {
                        LOG.debug("RUJ LUAR : NULL");
                        ruj = new PermohonanRujukanLuar();
                        ia.setDimasukOleh(pengguna);
                        ia.setTarikhMasuk(new Date());
                    } else {
                        LOG.debug("RUJ LUAR : NOT NULL");
                        if (ruj.getInfoAudit() == null) {
                            ia.setDimasukOleh(pengguna);
                            ia.setTarikhMasuk(new Date());
                        } else {
                            ia = ruj.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new Date());
                        }
                    }

                    if (StringUtils.isNotBlank(tkhWarta)) {
                        ruj.setTarikhRujukan(sdf.parse(tkhWarta));
                    }
                    if (StringUtils.isNotBlank(tkhTamat)) {
                        ruj.setTarikhTamat(sdf.parse(tkhTamat));
                    }
                    if (StringUtils.isNotBlank(tkhHantar)) {
                        ruj.setTarikhDisampai(sdf.parse(tkhHantar));
                    }

                    if (notis.getWarta() != null) {
                        if (StringUtils.isNotBlank(notis.getWarta().getNoRujukan())) {
                            ruj.setNoRujukan(notis.getWarta().getNoRujukan());
                        }
                    }

                    ruj.setPermohonan(permohonan);
                    ruj.setKodRujukan(kodRujukanDAO.findById("FL"));
                    ruj.setCawangan(pengguna.getKodCawangan());
                    ruj.setHakmilik(hp.getHakmilik());
                    ruj.setInfoAudit(ia);
                    service.saveOrUpdate(ruj);
                    notis2.setWarta(ruj);

                    if (permohonan.getKodUrusan() != null) {
                        notis2.setJabatan(permohonan.getKodUrusan().getJabatan());
                    } else {
                        notis2.setJabatan(kodJabatanDAO.findById("16"));//pendaftaran
                    }

                    long idMH = hp.getId();
                    HakmilikPermohonan id_mh = new HakmilikPermohonan();
                    id_mh.setId(idMH);
                    notis2.setHakmilikPermohonan(id_mh);

                    if (StringUtils.isNotBlank(tkhNotis)) {
                        notis2.setTarikhNotis(sdf.parse(tkhNotis));
                    }

                    if (StringUtils.isNotBlank(tkhTampal)) {
                        notis2.setTarikhTampal(sdf.parse(tkhTampal));
                    }

                    if (StringUtils.isNotBlank(tkhHantar)) {
                        notis2.setTarikhHantar(sdf.parse(tkhHantar));
                    }

                    if (StringUtils.isNotBlank(tkhTamat)) {
                        notis2.setTarikhTamat(sdf.parse(tkhTamat));
                    }

                    if (StringUtils.isNotBlank(notis.getBilangan())) {
                        notis2.setBilangan(notis.getBilangan());
                    }

                    if (StringUtils.isNotBlank(notis.getCatatanPenerimaan())) {
                        notis2.setCatatanPenerimaan(notis.getCatatanPenerimaan());
                    }

                    if (StringUtils.isNotBlank(stsPenyampaian)) {
                        KodStatusTerima terima = new KodStatusTerima();
                        terima.setKod(stsPenyampaian);
                        notis2.setKodStatusTerima(terima);
                    }

                    if (StringUtils.isNotBlank(caraHantar)) {
                        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
                        hantar.setKod(caraHantar);
                        notis2.setKodCaraPenghantaran(hantar);
                    }

                    notisService.saveOrUpdate(notis2);
                } //------------
                else if (notis2 == null) {
                    LOG.info("New Entry");
                    Notis newNotis = new Notis();

                    PermohonanRujukanLuar idWarta = new PermohonanRujukanLuar();

                    ia.setDimasukOleh(pengguna);
                    ia.setTarikhMasuk(new java.util.Date());

                    if (StringUtils.isNotBlank(tkhWarta)) {
                        idWarta.setTarikhRujukan(sdf.parse(tkhWarta));
                    }
                    if (StringUtils.isNotBlank(tkhTamat)) {
                        idWarta.setTarikhTamat(sdf.parse(tkhTamat));
                    }
                    if (StringUtils.isNotBlank(tkhHantar)) {
                        idWarta.setTarikhDisampai(sdf.parse(tkhHantar));
                    }

                    if (notis.getWarta() != null) {
                        if (StringUtils.isNotBlank(notis.getWarta().getNoRujukan())) {
                            idWarta.setNoRujukan(notis.getWarta().getNoRujukan());
                        }
                    }

                    if (StringUtils.isNotBlank(tkhWarta)) {
                        idWarta.setTarikhRujukan(sdf.parse(tkhWarta));
                    }

                    idWarta.setPermohonan(permohonan);
                    idWarta.setKodRujukan(kodRujukanDAO.findById("FL"));
                    idWarta.setCawangan(pengguna.getKodCawangan());
                    idWarta.setHakmilik(hp.getHakmilik());
                    idWarta.setInfoAudit(ia);
                    service.saveOrUpdate(idWarta);
                    LOG.info("------------ idWarta : " + idWarta);
                    newNotis.setWarta(idWarta);
                    newNotis.setInfoAudit(ia);
                    Dokumen dok = new Dokumen();
                    dok.setInfoAudit(ia);
                    dok.setTajuk("");
                    dokumenDAO.saveOrUpdate(dok);
                    newNotis.setDokumenNotis(dok);
                    newNotis.setPermohonan(permohonan);

                    if (permohonan.getKodUrusan() != null) {
                        newNotis.setJabatan(permohonan.getKodUrusan().getJabatan());
                    } else {
                        newNotis.setJabatan(kodJabatanDAO.findById("16"));//pendaftaran
                    }

                    long idMH = hp.getId();
                    HakmilikPermohonan id_mh = new HakmilikPermohonan();
                    id_mh.setId(idMH);
                    newNotis.setHakmilikPermohonan(id_mh);

                    if (StringUtils.isNotBlank(tkhNotis)) {
                        newNotis.setTarikhNotis(sdf.parse(tkhNotis));
                    }

                    if (StringUtils.isNotBlank(tkhTamat)) {
                        newNotis.setTarikhTamat(sdf.parse(tkhTamat));
                    }

                    if (StringUtils.isNotBlank(tkhTampal)) {
                        newNotis.setTarikhTampal(sdf.parse(tkhTampal));
                    }

                    if (StringUtils.isNotBlank(tkhHantar)) {
                        newNotis.setTarikhHantar(sdf.parse(tkhHantar));
                    }

                    if (StringUtils.isNotBlank(notis.getBilangan())) {
                        newNotis.setBilangan(notis.getBilangan());
                    }

                    if (StringUtils.isNotBlank(penerimaNotis)) {
                        newNotis.setPenerimaNotis(penerimaNotis);
                    }

                    if (StringUtils.isNotBlank(notis.getCatatanPenerimaan())) {
                        newNotis.setCatatanPenerimaan(notis.getCatatanPenerimaan());
                    }

                    if (StringUtils.isNotBlank(stsPenyampaian)) {
                        KodStatusTerima terima = new KodStatusTerima();
                        terima.setKod(stsPenyampaian);
                        newNotis.setKodStatusTerima(terima);
                    }

                    if (StringUtils.isNotBlank(caraHantar)) {
                        KodCaraPenghantaran hantar = new KodCaraPenghantaran();
                        hantar.setKod(caraHantar);
                        newNotis.setKodCaraPenghantaran(hantar);
                    }

                    notisService.saveOrUpdate(newNotis);
                }
            }
            addSimpleMessage("Kemasukan Data Berjaya.");
        } catch (Exception ex) {
            addSimpleError("Kemasukan Data Gagal : " + ex.getMessage());
            ex.printStackTrace();
        }
        return new JSP("daftar/notis_kaveat.jsp").addParameter("tab", "true");
    }

    @HandlesEvent("janaVDoc")
    public Resolution janaDokumen() {
        String result = "1";
        return new StreamingResolution("text/plain", result);
    }

    public Resolution savePerintah() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String kodPerintah = getContext().getRequest().getParameter("kodPerintah");

        String tahun = getContext().getRequest().getParameter("tahun");
        String bulan = getContext().getRequest().getParameter("bulan");
        String hari = getContext().getRequest().getParameter("hari");

        Hakmilik hm = null;
        if (StringUtils.isNotBlank(idHakmilik)) {
            hm = hakmilikDAO.findById(idHakmilik);
        }
        InfoAudit ia = null;
        permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
        ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new Date());
        }
        if (StringUtils.isNotBlank(kodPerintah)) {
            getContext().getRequest().setAttribute("kodPerintah", kodPerintah);
            KodPerintah kod = kodPerintahDAO.findById(kodPerintah);
            if (kod != null) {
                permohonanRujukanLuar.setKodPerintah(kod);
            }

        }

        if (StringUtils.isNotBlank(hari)) {
            permohonanRujukanLuar.setTempohHari(Integer.parseInt(hari));
        } else {
            permohonanRujukanLuar.setTempohHari(0);
        }
        if (StringUtils.isNotBlank(bulan)) {
            permohonanRujukanLuar.setTempohBulan(Integer.parseInt(bulan));
        } else {
            permohonanRujukanLuar.setTempohBulan(0);
        }
        if (StringUtils.isNotBlank(tahun)) {
            permohonanRujukanLuar.setTempohTahun(Integer.parseInt(tahun));
        } else {
            permohonanRujukanLuar.setTempohTahun(0);
        }

        permohonanRujukanLuar.setCawangan(pengguna.getKodCawangan());
        permohonanRujukanLuar.setInfoAudit(ia);
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setHakmilik(hm);

        try {
            if (StringUtils.isNotBlank(tkhSidang)) {
                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tkhSidang));
            }
            if (StringUtils.isNotBlank(tkhRujukan)) {
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tkhRujukan));
            }
            if (StringUtils.isNotBlank(tkhTamat)) {
                permohonanRujukanLuar.setTarikhTamat(sdf.parse(tkhTamat));
            }
            if (StringUtils.isNotBlank(tkhKuatKuasa)) {
                permohonanRujukanLuar.setTarikhKuatkuasa(sdf.parse(tkhKuatKuasa));
            }
            service.saveOrUpdate(permohonanRujukanLuar);

            if ("1".equals(copyToAllHakmilik)) {
                List<PermohonanRujukanLuar> senaraiPermohonan = new ArrayList<PermohonanRujukanLuar>();

                for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilik) {
                    if (hakmilikPermohonan == null || hakmilikPermohonan.getHakmilik().getIdHakmilik().equals(idHakmilik)) {
                        continue;
                    }

                    Hakmilik _hm = hakmilikPermohonan.getHakmilik();

                    PermohonanRujukanLuar rujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(_hm.getIdHakmilik(), idPermohonan);

                    if (rujukanLuar == null) {
                        rujukanLuar = new PermohonanRujukanLuar();
                    }

                    rujukanLuar.setCawangan(pengguna.getKodCawangan());
                    if (StringUtils.isNotBlank(tkhSidang)) {
                        rujukanLuar.setTarikhSidang(sdf.parse(tkhSidang));
                    }
                    if (StringUtils.isNotBlank(tkhRujukan)) {
                        rujukanLuar.setTarikhRujukan(sdf.parse(tkhRujukan));
                    }
                    if (StringUtils.isNotBlank(tkhTamat)) {
                        rujukanLuar.setTarikhTamat(sdf.parse(tkhTamat));
                    }
                    if (StringUtils.isNotBlank(tkhKuatKuasa)) {
                        rujukanLuar.setTarikhKuatkuasa(sdf.parse(tkhKuatKuasa));
                    }

                    rujukanLuar.setInfoAudit(ia);
                    rujukanLuar.setPermohonan(permohonan);
                    rujukanLuar.setHakmilik(_hm);
                    rujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL"));
                    rujukanLuar.setTempohTahun(permohonanRujukanLuar.getTempohTahun());
                    rujukanLuar.setTempohBulan(permohonanRujukanLuar.getTempohBulan());
                    rujukanLuar.setTempohHari(permohonanRujukanLuar.getTempohHari());
                    rujukanLuar.setNamaSidang(permohonanRujukanLuar.getNamaSidang());
                    rujukanLuar.setNoSidang(permohonanRujukanLuar.getNoSidang());
                    rujukanLuar.setNoRujukan(permohonanRujukanLuar.getNoRujukan());
                    rujukanLuar.setNoFail(permohonanRujukanLuar.getNoFail());
                    rujukanLuar.setKodPerintah(permohonanRujukanLuar.getKodPerintah());
                    rujukanLuar.setUlasan(permohonanRujukanLuar.getUlasan());
                    rujukanLuar.setCatatan(permohonanRujukanLuar.getCatatan());

                    senaraiPermohonan.add(rujukanLuar);
                }
                getContext().getRequest().getSession().setAttribute("copy_all", "true");
                service.saveOrUpdate(senaraiPermohonan);

            } else {

//                for (HakmilikPermohonan hakmilikPermohonan : senaraiHakmilik) {
//                    if (hakmilikPermohonan ==  null || hakmilikPermohonan.getHakmilik().getIdHakmilik().equals(hm.getIdHakmilik())) continue;
//                    Hakmilik _hm = hakmilikPermohonan.getHakmilik();
//
//                    PermohonanRujukanLuar rujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(_hm.getIdHakmilik(), idPermohonan);
//                    if (rujukanLuar != null) {
//                        service.delete(rujukanLuar);
//                    }
//                }
                getContext().getRequest().getSession().setAttribute("copy_all", "false");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem");
            return showForm();
        }

//        if (senaraiHakmilik.size() > 1) {
////                addSimpleError("Kemasukan Berjaya.");
//                return new RedirectResolution(Kaveat.class, "doGetPartialPage")
//                        .addParameter("idHakmilik", idHakmilik).addParameter("messages", "Kemasukan data berjaya.");
//            } else
//                return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
        addSimpleMessage("Kemasukan Data Berjaya.");
        return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
    }

    public Resolution saveKaveatInfo() {
        InfoAudit ia = new InfoAudit();
        String ulasanKaveatOnly = getContext().getRequest().getParameter("ulasanOnly");
        String perintahMahkamah = getContext().getRequest().getParameter("mahkamah");
        String tahun = getContext().getRequest().getParameter("tahun");
        String bulan = getContext().getRequest().getParameter("bulan");
        String hari = getContext().getRequest().getParameter("hari");
        String ulasan = getContext().getRequest().getParameter("ulasan");

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        List<Hakmilik> senaraiHakmilikTerlibat = new ArrayList<Hakmilik>();

//        if (StringUtils.isNotBlank(idHakmilik)) {
//            Hakmilik hk = hakmilikDAO.findById(idHakmilik);
//            if (hk != null) {
//                permohonanRujukanLuar.setHakmilik(hk);
//            }
//        } else {
//            if (senaraiHakmilik.size() > 0) {
//                Hakmilik hk = senaraiHakmilik.get(0).getHakmilik();
//                if (hk != null) {
//                    permohonanRujukanLuar.setHakmilik(hk);
//                }
//            }
//        }
        if (StringUtils.isNotBlank(idHakmilik)) {
            Hakmilik hm = hakmilikDAO.findById(idHakmilik);
            senaraiHakmilikTerlibat.add(hm);
        } else {
            for (HakmilikPermohonan hp : senaraiHakmilik) {
                if (hp == null || hp.getHakmilik() == null) {
                    continue;
                }
                senaraiHakmilikTerlibat.add(hp.getHakmilik());
            }
        }

        for (Hakmilik hm : senaraiHakmilikTerlibat) {

            permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(hm.getIdHakmilik(), idPermohonan);
            if (permohonanRujukanLuar == null) {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
            }

            if (StringUtils.isNotBlank(ulasan)) {
                permohonanRujukanLuar.setUlasan(ulasan);
            }

            permohonanRujukanLuar.setHakmilik(hm);

            if (StringUtils.isNotBlank(perintahMahkamah)) {
                permohonanRujukanLuar.setKodPerintah(kodPerintahDAO.findById("MK"));
                getContext().getRequest().setAttribute("perintahMahkamah", Boolean.TRUE);
            }

            permohonanRujukanLuar.setCawangan(pengguna.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);

            try {
                if (StringUtils.isNotBlank(tkhSidang)) {
                    permohonanRujukanLuar.setTarikhSidang(sdf.parse(tkhSidang));
                }
                if (StringUtils.isNotBlank(tkhRujukan)) {
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tkhRujukan));
                }
                if (StringUtils.isNotBlank(tkhTamat)) {
                    permohonanRujukanLuar.setTarikhTamat(sdf.parse(tkhTamat));
                }
                if (StringUtils.isNotBlank(tkhKuatKuasa)) {
                    permohonanRujukanLuar.setTarikhKuatkuasa(sdf.parse(tkhKuatKuasa));
                }

                if (StringUtils.isNotBlank(hari)) {
                    permohonanRujukanLuar.setTempohHari(Integer.parseInt(hari));
                } else {
                    permohonanRujukanLuar.setTempohHari(0);
                }
                if (StringUtils.isNotBlank(bulan)) {
                    permohonanRujukanLuar.setTempohBulan(Integer.parseInt(bulan));
                } else {
                    permohonanRujukanLuar.setTempohBulan(0);
                }
                if (StringUtils.isNotBlank(tahun)) {
                    permohonanRujukanLuar.setTempohTahun(Integer.parseInt(tahun));
                } else {
                    permohonanRujukanLuar.setTempohTahun(0);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                addSimpleError("Kemasukan Data Gagal. Sila hubungi Pentadbir Sistem");
                if (StringUtils.isNotBlank(ulasanKaveatOnly)) {
                    return new JSP("daftar/kemasukan_maklumat_kaveat.jsp").addParameter("tab", "true");
                }
                return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
            }

            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("FL")); //TODO : uncomment

            ia = permohonanRujukanLuar.getInfoAudit();
            if (ia == null) {
                ia = new InfoAudit();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new Date());
            } else {
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new Date());
            }

            permohonanRujukanLuar.setInfoAudit(ia);
            service.saveOrUpdate(permohonanRujukanLuar);
        }

        addSimpleMessage("Kemasukan Data Berjaya.");

        if (StringUtils.isNotBlank(ulasanKaveatOnly)) {
//            return showKaveatUlasan();
            return new RedirectResolution(Kaveat.class, "showKaveatUlasan");
        } else {
//            loadHakmilik();
//            if (senaraiHakmilik.size() > 1) {
//                return new RedirectResolution(Kaveat.class, "doGetPartialPage").addParameter("idHakmilik", idHakmilik);
//            } else
//                return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
            return new JSP("daftar/kemasukan_maklumat_borang.jsp").addParameter("tab", "true");
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!reloadHakmilik", "!showFormNotis"})
    public void rehydrate() {
//        permohonan = ((etanahActionBeanContext) getContext()).getPermohonan();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
        senaraiHakmilik = permohonan.getSenaraiHakmilik();
        if (permohonan != null) {
            String kodUrusan = permohonan.getKodUrusan().getKod();

            String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
            if (StringUtils.isNotBlank(idHakmilik)) {
                LOG.debug("id hakmilik = " + idHakmilik);
                permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonan);
            } else {
                if (senaraiHakmilik.size() > 0) {
                    idHakmilik = senaraiHakmilik.get(0).getHakmilik().getIdHakmilik();
                    permohonanRujukanLuar = service.findMohonRujukLuarIdHakmilikIdPermohonan(idHakmilik, idPermohonan);
                }
            }

            notis = notisService.getNotis(idPermohonan, idHakmilik);
            LOG.debug(" is permohonan rujukan luar null " + (permohonanRujukanLuar == null ? "true" : "false"));

            if (permohonanRujukanLuar != null) {
                if (permohonanRujukanLuar.getTarikhSidang() != null) {
                    tkhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
                }
                if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                    tkhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
                }
                if (permohonanRujukanLuar.getTarikhTamat() != null) {
                    tkhTamat = sdf.format(permohonanRujukanLuar.getTarikhTamat());
                }
                if (permohonanRujukanLuar.getTarikhKuatkuasa() != null) {
                    tkhKuatKuasa = sdf.format(permohonanRujukanLuar.getTarikhKuatkuasa());
                }

                getContext().getRequest().setAttribute("hari", permohonanRujukanLuar.getTempohHari() == null ? 0 : permohonanRujukanLuar.getTempohHari());
                getContext().getRequest().setAttribute("bln", permohonanRujukanLuar.getTempohBulan() == null ? 0 : permohonanRujukanLuar.getTempohBulan());
                getContext().getRequest().setAttribute("thn", permohonanRujukanLuar.getTempohTahun() == null ? 0 : permohonanRujukanLuar.getTempohTahun());
                getContext().getRequest().setAttribute("ulasan", permohonanRujukanLuar.getUlasan());

                if (permohonanRujukanLuar.getKodPerintah() != null) {
                    getContext().getRequest().setAttribute("kodPerintah", permohonanRujukanLuar.getKodPerintah().getKod());
                }
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setTempohBulan(0);
                permohonanRujukanLuar.setTempohHari(0);
                permohonanRujukanLuar.setTempohTahun(0);

                if ("PNPA".equals(kodUrusan)) {
                    FolderDokumen folderDokumen = permohonan.getFolderDokumen();
                    if (folderDokumen != null) {
                        List<KandunganFolder> skf = folderDokumen.getSenaraiKandungan();
                        if (skf != null && skf.size() > 0) {
                            for (KandunganFolder kf : skf) {
                                if (kf == null) {
                                    continue;
                                }
                                Dokumen d = kf.getDokumen();
                                if (d == null) {
                                    continue;
                                }
                                if (d.getKodDokumen().getKod().equals("SAD") || d.getKodDokumen().getKod().equals("SAB")) {
                                    permohonanRujukanLuar.setNoRujukan(d.getNoRujukan());
                                    break;
                                }
                            }
                        }
                    }
                } else if ("LTPL".equals(kodUrusan)) {
                    List<PermohonanHubungan> list = permohonanService.getSenaraiHubungan(idPermohonan, idHakmilik);
                    if (!list.isEmpty()) {
                        PermohonanHubungan ph = list.get(0);
                        if (ph != null) {
                            String idPermohonnPilih = ph.getPermohonanSasaran().getIdPermohonan();
                            HakmilikUrusan hu = hakmilikUrusanService.findByIdPerserahanIdHakmilik(idPermohonnPilih, idHakmilik);
                            if (hu != null) {
                                if (hu.getTarikhMula() != null) {
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(hu.getTarikhMula());
                                    cal.add(Calendar.DATE, hu.getTempohHari() != null ? hu.getTempohHari() : 0);
                                    cal.add(Calendar.MONTH, hu.getTempohBulan() != null ? hu.getTempohBulan() : 0);
                                    cal.add(Calendar.YEAR, hu.getTempohTahun() != null ? hu.getTempohTahun() : 0);
                                    tkhKuatKuasa = sdf.format(cal.getTime());
                                } else if (hu.getTarikhTamat() != null) {
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(hu.getTarikhTamat());
                                    tkhKuatKuasa = sdf.format(cal.getTime());
                                }
                            }
                        }
                    }
                }

            }

        } else {
            permohonanRujukanLuar.setTempohBulan(0);
            permohonanRujukanLuar.setTempohHari(0);
            permohonanRujukanLuar.setTempohTahun(0);
        }
        LOG.debug("rehydrate tarikh tamat = " + tkhTamat);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTkhSidang() {
        return tkhSidang;
    }

    public void setTkhSidang(String tkhSidang) {
        this.tkhSidang = tkhSidang;
    }

    public String getTkhRujukan() {
        return tkhRujukan;
    }

    public void setTkhRujukan(String tkhRujukan) {
        this.tkhRujukan = tkhRujukan;
    }

    public String getTkhTamat() {
        return tkhTamat;
    }

    public void setTkhTamat(String tkhTamat) {
        this.tkhTamat = tkhTamat;
    }

    public String getTkhKuatKuasa() {
        return tkhKuatKuasa;
    }

    public void setTkhKuatKuasa(String tkhKuatKuasa) {
        this.tkhKuatKuasa = tkhKuatKuasa;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public String getTkhNotis() {
        return tkhNotis;
    }

    public void setTkhNotis(String tkhNotis) {
        this.tkhNotis = tkhNotis;
    }

    public String getTkhTampal() {
        return tkhTampal;
    }

    public void setTkhTampal(String tkhTampal) {
        this.tkhTampal = tkhTampal;
    }

    public String getTkhWarta() {
        return tkhWarta;
    }

    public void setTkhWarta(String tkhWarta) {
        this.tkhWarta = tkhWarta;
    }

    public String getTkhHantar() {
        return tkhHantar;
    }

    public void setTkhHantar(String tkhHantar) {
        this.tkhHantar = tkhHantar;
    }

    public List<String> getListIdHakmilik() {
        return listIdHakmilik;
    }

    public void setListIdHakmilik(List<String> listIdHakmilik) {
        this.listIdHakmilik = listIdHakmilik;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getCopyToAllHakmilik() {
        return copyToAllHakmilik;
    }

    public void setCopyToAllHakmilik(String copyToAllHakmilik) {
        this.copyToAllHakmilik = copyToAllHakmilik;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getKodCaraPenghantaran() {
        return kodCaraPenghantaran;
    }

    public void setKodCaraPenghantaran(String kodCaraPenghantaran) {
        this.kodCaraPenghantaran = kodCaraPenghantaran;
    }

}
