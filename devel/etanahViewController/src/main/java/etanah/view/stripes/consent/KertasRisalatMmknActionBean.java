               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kertas_risalat_mmkn")
public class KertasRisalatMmknActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(KertasRisalatMmknActionBean.class);
    private static final boolean isDebug = LOG.isDebugEnabled();
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanUrusan mohonUrusanRayuan;
    private FasaPermohonan fasaPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    String tajuk;           // bil 1
    String tujuan;          // bil 2
    String latarBelakang;   // bil 3
    String asas;            // bil 4
    String perakuanPtg;     // bil 5
    String alasanRayuan;    // bil 6
    String idHakmilik;
    private List<HakmilikPihakBerkepentingan> senaraiTanahLain;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    etanahActionBeanContext ctx;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("consent/kertas_risalat_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("ID Hakmilik : " + idHakmilik);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);

        if (StringUtils.isNotBlank(idHakmilik)) {
            hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
        } else {
            hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        }

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage9");

            //KES PERMOHONAN BIASA
            if (fasaPermohonan == null) {

                PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "TAJUK");

                if (permohonanUrusan != null) {
                    tajuk = permohonanUrusan.getCatatan().toUpperCase();
                    tujuan = "Tujuan kertas ini adalah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai " + permohonanUrusan.getCatatan() + ".";
                }

                perakuanPtg = "6.1 Diangkat untuk pertimbangan Majlis";

                PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT MMKN");

                if (permohonanKertas != null) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                        if (kertasKandungan.getBil() == 1) {
                            tajuk = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 2) {
                            tujuan = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 3) {
                            latarBelakang = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 4) {
                            asas = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            perakuanPtg = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            alasanRayuan = kertasKandungan.getKandungan();
                        }
                    }
                }
            } //KES PERMOHONAN RAYUAN PENGURANGAN BAYARAN
            else {
                mohonUrusanRayuan = conService.findMohonUrusanByPerihal(idPermohonan, "RAYUAN");
                if (mohonUrusanRayuan != null) {
                    PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "TAJUK RAYUAN");

                    if (permohonanUrusan == null) {
                        permohonanUrusan = janaTajuk();
                    }

                    tajuk = permohonanUrusan.getCatatan().toUpperCase();
                    tujuan = "Tujuan kertas ini adalah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai " + permohonanUrusan.getCatatan() + ".";

                    perakuanPtg = "7.1 Diangkat untuk pertimbangan Majlis";

                    PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT RAYUAN");

                    if (permohonanKertas != null) {

                        for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                            kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                            if (kertasKandungan.getBil() == 1) {
                                tajuk = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 2) {
                                tujuan = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 3) {
                                latarBelakang = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 4) {
                                asas = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 5) {
                                perakuanPtg = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 6) {
                                alasanRayuan = kertasKandungan.getKandungan();
                            }
                        }
                    }
                }
            }
            senaraiTanahLain = new ArrayList<HakmilikPihakBerkepentingan>();

            for (Pemohon pmhon : permohonan.getSenaraiPemohon()) {
                LOG.info(":: CHECK TANAH LAIN FOR ID PIHAK : " + pmhon.getPihak().getIdPihak() + " : " + pmhon.getPihak().getNama());
                List<HakmilikPihakBerkepentingan> hakPihakList = new ArrayList<HakmilikPihakBerkepentingan>();
                hakPihakList = conService.findSenaraiHakPihakByNamaAndJenis(String.valueOf(pmhon.getPihak().getIdPihak()), "PM");
                if (hakPihakList != null) {
                    senaraiTanahLain.addAll(hakPihakList);
                }
            }
        }
    }

    public Resolution selectHakmilik() {
        LOG.info("::select hakmilik::");
        return showForm();

    }

    public Resolution simpan() {

        if (tujuan == null) {
            if (tujuan == null) {
                addSimpleError("Sila masukkan maklumat terlebih dahulu.");
            }

        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            PermohonanKertas permohonanKertas = new PermohonanKertas();

            fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage9");

            if (fasaPermohonan == null) {
                permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT MMKN");
            } else {
                permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RISALAT RAYUAN");
            }
            if (permohonanKertas != null) {
                infoAudit = permohonanKertas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanKertas = new PermohonanKertas();

            }

            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setCawangan(permohonan.getCawangan());
            KodDokumen kodDokumen = new KodDokumen();

            if (fasaPermohonan == null) {
                permohonanKertas.setTajuk("RISALAT MMKN");
                kodDokumen.setKod("RIS");
                permohonanKertas.setKodDokumen(kodDokumen);
            } else {
                permohonanKertas.setTajuk("RISALAT RAYUAN");
                kodDokumen.setKod("RISR");
            }

            permohonanKertas.setKodDokumen(kodDokumen);
            conService.simpanPermohonanKertas(permohonanKertas);

            ArrayList listUlasan = new ArrayList();

            if (StringUtils.isBlank(tajuk)) {
                tajuk = " ";
            }
            if (StringUtils.isBlank(tujuan)) {
                tujuan = " ";
            }
            if (StringUtils.isBlank(latarBelakang)) {
                latarBelakang = " ";
            }
            if (StringUtils.isBlank(asas)) {
                asas = " ";
            }
            if (StringUtils.isBlank(perakuanPtg)) {
                perakuanPtg = " ";
            }
            if (StringUtils.isBlank(alasanRayuan)) {
                alasanRayuan = " ";
            }


            listUlasan.add(tajuk);
            listUlasan.add(tujuan);
            listUlasan.add(latarBelakang);
            listUlasan.add(asas);
            listUlasan.add(perakuanPtg);
            listUlasan.add(alasanRayuan);

            if (kertasKandungan != null) {

                if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                        PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                        kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                        if (kertasKdgn.getBil() == 1) {
                            kertasKdgn.setKandungan(tajuk);
                        } else if (kertasKdgn.getBil() == 2) {
                            kertasKdgn.setKandungan(tujuan);
                        } else if (kertasKdgn.getBil() == 3) {
                            kertasKdgn.setKandungan(latarBelakang);
                        } else if (kertasKdgn.getBil() == 4) {
                            kertasKdgn.setKandungan(asas);
                        } else if (kertasKdgn.getBil() == 5) {
                            kertasKdgn.setKandungan(perakuanPtg);
                        } else if (kertasKdgn.getBil() == 6) {
                            kertasKdgn.setKandungan(alasanRayuan);
                        }
                        kertasKdgn.setInfoAudit(infoAudit);
                        conService.simpanPermohonanKertasKandungan(kertasKdgn);
                    }
                }

            } else {

                for (int i = 0; i < listUlasan.size(); i++) {

                    String ulasan = (String) listUlasan.get(i);

                    infoAudit.setTarikhMasuk(new java.util.Date());
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn.setKertas(permohonanKertas);
                    kertasKdgn.setBil(i + 1);
                    kertasKdgn.setInfoAudit(infoAudit);
                    kertasKdgn.setKandungan(ulasan);
                    kertasKdgn.setCawangan(permohonan.getCawangan());
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/kertas_risalat_mmkn.jsp").addParameter("tab", "true");
    }

    public PermohonanUrusan janaTajuk() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (mohonUrusanRayuan != null && mohonUrusanRayuan.getCatatan().equals("YURAN")) {
            String namaHakmilik = "";
            for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);
                if (i == 0) {
                    namaHakmilik = hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + hakmilikMohon.getHakmilik().getKodUnitLuas().getNama() + " di " + hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama() + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                } else if (i == permohonan.getSenaraiHakmilik().size() - 1) {
                    namaHakmilik = namaHakmilik + " dan " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getKodUnitLuas().getNama()) + " di " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama()) + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                } else if (i != 0 && i != permohonan.getSenaraiHakmilik().size() - 1) {
                    namaHakmilik = namaHakmilik + ", " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " seluas " + hakmilikMohon.getHakmilik().getLuas() + " " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getKodUnitLuas().getNama()) + " di " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama()) + ", " + WordUtils.capitalizeFully(hakmilikMohon.getHakmilik().getDaerah().getNama());
                }
            }
            tajuk = "permohonan rayuan untuk mengurangkan bayaran Yuran Notis Kelulusan bagi kelulusan memiliki hartanah di Negeri Melaka di atas hakmilik " + namaHakmilik + ", Melaka";
        } else if (mohonUrusanRayuan != null && mohonUrusanRayuan.getCatatan().equals("SUMBANGAN")) {
            tajuk = "permohonan rayuan daripada " + WordUtils.capitalizeFully(permohonan.getPenyerahNama()) + " untuk mengecualikan bayaran Sumbangan Tabung Amanah Rumah Kos Rendah (TARKR) ke atas kelulusan pemilikan hartanah oleh warganegara asing";
        }

        LOG.info("::::: TAJUK : " + tajuk);

        //SIMPAN TAJUK
        InfoAudit infoAudit = new InfoAudit();
        PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "TAJUK RAYUAN");

        if (permohonanUrusan == null) {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusan = new PermohonanUrusan();

        } else {
            infoAudit = permohonanUrusan.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

        permohonanUrusan.setCawangan(permohonan.getCawangan());
        permohonanUrusan.setPermohonan(permohonan);
        permohonanUrusan.setCatatan(tajuk);
        permohonanUrusan.setInfoAudit(infoAudit);
        permohonanUrusan.setPerihal("TAJUK RAYUAN");
        conService.simpanPermohonanUrusan(permohonanUrusan);

        return permohonanUrusan;
    }

    public String getAsas() {
        return asas;
    }

    public void setAsas(String asas) {
        this.asas = asas;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPerakuanPtg() {
        return perakuanPtg;
    }

    public void setPerakuanPtg(String perakuanPtg) {
        this.perakuanPtg = perakuanPtg;
    }

    public List<HakmilikPihakBerkepentingan> getSenaraiTanahLain() {
        return senaraiTanahLain;
    }

    public void setSenaraiTanahLain(List<HakmilikPihakBerkepentingan> senaraiTanahLain) {
        this.senaraiTanahLain = senaraiTanahLain;
    }

    public PermohonanUrusan getMohonUrusanRayuan() {
        return mohonUrusanRayuan;
    }

    public void setMohonUrusanRayuan(PermohonanUrusan mohonUrusanRayuan) {
        this.mohonUrusanRayuan = mohonUrusanRayuan;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getAlasanRayuan() {
        return alasanRayuan;
    }

    public void setAlasanRayuan(String alasanRayuan) {
        this.alasanRayuan = alasanRayuan;
    }
}
