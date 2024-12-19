               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodKeputusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/strata/ulasan_mmk")
public class UlasanMmkActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    StrataPtService strService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private Pengguna pengguna;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas mohonKertas;
    private List<HakmilikPermohonan> listMohonHM = new ArrayList();
    private HakmilikPermohonan mohonHM;
    private String stageId;
    private String latarBelakang;
    private String huraianPentadbir;
    private String syorPentadbir;
    private String huraianPtg;
    private String syorPtg;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private String tarikhSidang;
    private String keputusanSUMMK;
    private String ulasanSUMMK;
    private String tajukKertas;
    private String tujuanKertas;
    private List listLatarBlakang = new ArrayList();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List senaraiLaporanKandungan1;
    private FasaPermohonan fasaPermohonan;
    private List<FasaPermohonan> listFasa;
    private static final Logger LOG = Logger.getLogger(UlasanMmkActionBean.class);

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution editNegeri() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showNegeri() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution editMelaka() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("strata/kos_rendah/ulasan_mmkMelaka.jsp").addParameter("tab", "true");
    }

    public Resolution showMelaka() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/ulasan_mmkMelaka.jsp").addParameter("tab", "true");
    }

    public Resolution showmmk() {
        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution keputusanMmk() {
        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        fasaPermohonan = strService.findFasaPermohonanByIdAliran(idMohon, "keputusanmmk");
        if (fasaPermohonan != null) {
            if (fasaPermohonan.getKeputusan() != null) {
                keputusanSUMMK = fasaPermohonan.getKeputusan().getKod();
            }
            ulasanSUMMK = fasaPermohonan.getUlasan();
        }

        permohonan = permohonanDAO.findById(idMohon);
        mohonKertas = strService.findKertas(permohonan.getIdPermohonan());
        if (mohonKertas != null) {
            LOG.info("ID_Kertas : " + mohonKertas.getIdKertas());
            tarikhSidang = sdf.format(mohonKertas.getTarikhSidang());
        }

        getContext().getRequest().setAttribute("display", Boolean.TRUE);
        return new JSP("strata/kos_rendah/keputusan_summk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        mohonHM = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(mohonHM.getHakmilik().getIdHakmilik());
        LOG.info("::FIND:: id hakmilik : " + hakmilik.getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());


        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas perKertas = new PermohonanKertas();
                perKertas = permohonan.getSenaraiKertas().get(i);
                senaraiLaporanKandungan1 = strService.findMohonKertasByMohonKertas(perKertas.getIdKertas());

                for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {

                    kertasKandungan = perKertas.getSenaraiKandungan().get(j);
                    if (perKertas.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf.format(perKertas.getTarikhSidang());
                    }
                    switch (kertasKandungan.getBil()) {
                        case 1:
                            tujuanKertas = kertasKandungan.getKandungan();
                            break;
                        case 2:
                            latarBelakang = kertasKandungan.getKandungan();
                            listLatarBlakang.add(latarBelakang);
                            break;
                        case 3:
                            huraianPentadbir = kertasKandungan.getKandungan();
                            break;
                        case 4:
                            syorPentadbir = kertasKandungan.getKandungan();
                            break;
                        case 5:
                            huraianPtg = kertasKandungan.getKandungan();
                            break;
                        case 6:
                            syorPtg = kertasKandungan.getKandungan();
                            break;
                        default:
                            LOG.info("DEFAULT");
                            break;
                    }

//                    if (kertasKandungan.getBil() == 1) {
//                        latarBelakang = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getBil() == 2) {
//                        huraianPentadbir = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getBil() == 3) {
//                        syorPentadbir = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getBil() == 4) {
//                        huraianPtg = kertasKandungan.getKandungan();
//                    } else if (kertasKandungan.getBil() == 5) {
//                        syorPtg = kertasKandungan.getKandungan();
//                    }
                }
            }
        }

        listFasa = strService.getSenaraiFasaPermohonan(permohonan.getIdPermohonan());
        LOG.info("listFasa.size : " + listFasa.size());

        try {
            tajukKertas = "Permohonan Supaya Bangunan Rumah Pangsa Atas " + hakmilik.getLot().getNama() + " "
                    + hakmilik.getNoLot() + " No. Hakmilik " + hakmilik.getKodHakmilik().getNama() + " " + hakmilik.getNoLot() + ", "
                    + hakmilik.getBandarPekanMukim().getNama() + ", Daerah" + hakmilik.getDaerah().getNama() + " Dikelaskan sebagai"
                    + "Bangunan Kos Rendah Di bawah Seksyen 10b(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290)";
        } catch (Exception e) {
            LOG.error(e);
            tajukKertas = "Permohonan Supaya Bangunan Rumah Pangsa Atas Dikelaskan sebagai"
                    + "Bangunan Kos Rendah Di bawah Seksyen 10b(2) AKTA HAKMILIK STRATA 1985 (PINDAAN 2007 A 1290)";
        }



    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

//        if (tarikhMesyuarat == null) {
//            addSimpleError("Sila masukkan tarikh mesyuarat.");
//        } else {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setTarikhMasuk(new java.util.Date());
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk(tajukKertas);

        LOG.info("====== IN SAVING : MOHON KERTAS ======");
        strService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();


        if (StringUtils.isBlank(tujuanKertas)) {
            tujuanKertas = "TIADA DATA";
        }
        if (StringUtils.isBlank(latarBelakang)) {
            latarBelakang = "TIADA DATA";
            listLatarBlakang.add(latarBelakang);
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = "TIADA DATA";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = "TIADA DATA";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = "TIADA DATA";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = "TIADA DATA";
        }

        listUlasan.add(tujuanKertas);
        listUlasan.add(listLatarBlakang);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

        int count = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));
        if (kertasKandungan != null) {

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    switch (kertasKdgn.getBil()) {
                        case 1:
                            kertasKdgn.setKandungan(tujuanKertas);
                            kertasKdgn.setSubtajuk("1." + j);
                            break;
                        case 2:
                            for (int i = 1; i <= count; i++) {
                                latarBelakang = getContext().getRequest().getParameter("latarBelakang" + i);
                                kertasKdgn.setKandungan(latarBelakang);
                                kertasKdgn.setSubtajuk("2." + i);
                            }
                            break;
                        case 3:
                            kertasKdgn.setKandungan(huraianPentadbir);
                            kertasKdgn.setSubtajuk("3." + j);
                            break;
                        case 4:
                            kertasKdgn.setKandungan(syorPentadbir);
                            kertasKdgn.setSubtajuk("4." + j);
                            break;
                        case 5:
                            kertasKdgn.setKandungan(huraianPtg);
                            kertasKdgn.setSubtajuk("5." + j);
                            break;
                        case 6:
                            kertasKdgn.setKandungan(syorPtg);
                            kertasKdgn.setSubtajuk("6." + j);
                            break;
                        default:
                            kertasKdgn.setKandungan("DEFAULT");
                            break;

                    }
//                    if (kertasKdgn.getBil() == 1) {
//                        kertasKdgn.setKandungan(latarBelakang);
//                    } else if (kertasKdgn.getBil() == 2) {
//                        kertasKdgn.setKandungan(huraianPentadbir);
//                    } else if (kertasKdgn.getBil() == 3) {
//                        kertasKdgn.setKandungan(syorPentadbir);
//                    } else if (kertasKdgn.getBil() == 4) {
//                        kertasKdgn.setKandungan(huraianPtg);
//                    } else if (kertasKdgn.getBil() == 5) {
//                        kertasKdgn.setKandungan(syorPtg);
//                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                infoAudit.setTarikhMasuk(new java.util.Date());
                PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();

                if (i == 1) {
                    for (int j = 0; j < listLatarBlakang.size(); j++) {
                        kertasKdgn.setKertas(permohonanKertas);
                        kertasKdgn.setBil(i + 1);
                        kertasKdgn.setSubtajuk(i + 1 + "." + i + 1);
                        kertasKdgn.setInfoAudit(infoAudit);
                        kertasKdgn.setKandungan(listLatarBlakang.get(j).toString());
                    }
                } else {
                    kertasKdgn.setKertas(permohonanKertas);
                    kertasKdgn.setBil(i + 1);
                    kertasKdgn.setSubtajuk(i + 1 + "." + i + 1);
                    kertasKdgn.setInfoAudit(infoAudit);
                    kertasKdgn.setKandungan(ulasan);
                }
                kertasKdgn.setCawangan(permohonan.getCawangan());
                conService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        }

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);


        return new ForwardResolution("/WEB-INF/jsp/strata/kos_rendah/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusanSUMMK() {
        LOG.info("------simpan keputusan PTG-------");

        String idMohon = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("idPermohonan : " + idMohon);
        permohonan = permohonanDAO.findById(idMohon);

        LOG.info("kodUrusan : " + permohonan.getKodUrusan().getKod());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        //retrieve stage ID from BPEL service.
        BPelService serviceBpel = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = serviceBpel.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            LOG.info("stage ID :" + stageId);
        }



        //save in mohon_fasa
        if (permohonan != null) {

            InfoAudit info = new InfoAudit();
            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusanSUMMK);

            fasaPermohonan = strService.findFasaPermohonanByIdAliran(idMohon, "keputusanmmk");
            if (fasaPermohonan != null) {
                info = fasaPermohonan.getInfoAudit();
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
            } else {
                fasaPermohonan = new FasaPermohonan();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
            }


            fasaPermohonan.setInfoAudit(info);
            LOG.info("id pguna : " + pengguna.getIdPengguna());
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setCawangan(pengguna.getKodCawangan());
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setIdAliran("keputusanmmk");
            fasaPermohonan.getUlasan();
            fasaPermohonan.setUlasan(ulasanSUMMK);
            fasaPermohonan.getKeputusan();
            fasaPermohonan.setKeputusan(kodKeputusan);

            LOG.info(".: IN SAVING MOHON_FASA :.");
            fasaPermohonan = strService.saveFasaMohon(fasaPermohonan);
            LOG.info("CHECK id fasa from insaving : " + fasaPermohonan.getIdFasa());
            LOG.info("CHECK id fasa from database : " + fasaPermohonan.getIdFasa());


            //update tarikh sidang dalam table Mohon_Kertas
            mohonKertas = strService.findKertas(permohonan.getIdPermohonan());
            if (mohonKertas != null) {
                LOG.info("ID_Kertas : " + mohonKertas.getIdKertas());

                try {
                    mohonKertas.setTarikhSidang(sdf.parse(tarikhSidang));
                } catch (java.text.ParseException ex) {
                    LOG.error(ex);
                }
                strService.simpanPermohonanKertas(mohonKertas);
                LOG.info("Update tarikh sidang dalam Mohon_Kertas.");


            } else {
                LOG.info("ID_Kertas is null");
            }
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setUlasan(ulasanSUMMK);
            try {
                permohonan.setTarikhKeputusan(sdf.parse(tarikhSidang));
            } catch (java.text.ParseException ex) {
                LOG.error(ex);
            }
            strService.savePermohonan(permohonan);
            LOG.info("UPDATE : trh_kpsn + kod_kpsn + ulasan dalam table Mohon.");

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        return new JSP("strata/kos_rendah/keputusan_summk.jsp").addParameter("tab", "true");

    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public List getSenaraiLaporanKandungan1() {
        return senaraiLaporanKandungan1;
    }

    public void setSenaraiLaporanKandungan1(List senaraiLaporanKandungan1) {
        this.senaraiLaporanKandungan1 = senaraiLaporanKandungan1;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public List<FasaPermohonan> getListFasa() {
        return listFasa;
    }

    public void setListFasa(List<FasaPermohonan> listFasa) {
        this.listFasa = listFasa;
    }

    public String getKeputusanSUMMK() {
        return keputusanSUMMK;
    }

    public void setKeputusanSUMMK(String keputusanSUMMK) {
        this.keputusanSUMMK = keputusanSUMMK;
    }

    public String getUlasanSUMMK() {
        return ulasanSUMMK;
    }

    public void setUlasanSUMMK(String ulasanSUMMK) {
        this.ulasanSUMMK = ulasanSUMMK;
    }

    public HakmilikPermohonan getMohonHM() {
        return mohonHM;
    }

    public void setMohonHM(HakmilikPermohonan mohonHM) {
        this.mohonHM = mohonHM;
    }

    public String getTajukKertas() {
        return tajukKertas;
    }

    public void setTajukKertas(String tajukKertas) {
        this.tajukKertas = tajukKertas;
    }
}
