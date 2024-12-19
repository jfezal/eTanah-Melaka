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
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/ulasan_rayuan_jktlm")
public class UlasanRayuanJktlmActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodNegeri;
    private String tajuk; //1
    private String tujuan; //2
    private String latarBelakang; //3
    private String lokasi; //4
    private String keadaanTanah; //5
    private String perihalTanah; //6
    private String maksud; //7
    private String perakuanPtg; //8
//    private String keputusan; //9
    private String ulasanJabatanPertanian;
    private String ulasanJabatanTenagaKerja;
    private String rujukanJabatanPertanian;
    private String rujukanJabatanTenagaKerja;
    private List<PermohonanRujukanLuar> listUlasanTeknikal;
    private List<PermohonanPihak> listPenerima;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showFormUlasan() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("consent/ulasan_rayuan_jktlm.jsp").addParameter("tab", "true");
    }

    public Resolution showFormMesyuarat() {
        getContext().getRequest().setAttribute("tarikh", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("consent/ulasan_rayuan_jktlm.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("tarikh", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewJabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("consent/ulasan_rayuan_jktlm.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        listUlasanTeknikal = permohonan.getSenaraiRujukanLuar();

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            permohonanKertasTemp = conService.findMohonKertasByKodDokumen(idPermohonan, "KRY");

            if (permohonanKertasTemp == null) {

                PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "TAJUK");

                if (permohonanUrusan != null) {
                    tajuk = permohonanUrusan.getCatatan().toUpperCase();
                    tujuan = "Kertas ini adalah bertujuan untuk mendapatkan pertimbangan dan kelulusan Majlis Mesyuarat Kerajaan Negeri  mengenai " + permohonanUrusan.getCatatan() + ".";
                    perakuanPtg = "Diangkat untuk pertimbangan Majlis.";
                }
                simpanFirst();

            } else {

                if (permohonanKertasTemp.getSenaraiKandungan() != null) {

                    for (int j = 0; j < permohonanKertasTemp.getSenaraiKandungan().size(); j++) {

                        kertasKandungan = permohonanKertasTemp.getSenaraiKandungan().get(j);

                        if (kertasKandungan.getBil() == 1) {
                            tajuk = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 2) {
                            tujuan = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 3) {
                            latarBelakang = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 4) {
                            lokasi = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            keadaanTanah = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            perihalTanah = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 7) {
                            maksud = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 8) {
                            perakuanPtg = kertasKandungan.getKandungan();
                        }
//                        else if (kertasKandungan.getBil() == 9) {
//                            keputusan = kertasKandungan.getKandungan();
//                        }
                    }
                }
            }

            List<PermohonanPihak> senaraiMohonPihakAll = permohonanPihakService.getSenaraiPmohonPihakByNotKod(permohonan.getIdPermohonan(), "TER");
            listPenerima = new ArrayList<PermohonanPihak>();

            for (PermohonanPihak perPihak : senaraiMohonPihakAll) {

                if (listPenerima.isEmpty()) {
                    listPenerima.add(perPihak);
                } else {
                    boolean found = false;
                    for (PermohonanPihak perPihakChecked : listPenerima) {
                        if (perPihak.getPihak().equals(perPihakChecked.getPihak())) {
                            found = true;
                        }
                    }
                    if (!found) {
                        listPenerima.add(perPihak);
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (listUlasanTeknikal.size() > 0) {

            for (PermohonanRujukanLuar mohonRujLuar : listUlasanTeknikal) {
                infoAudit = mohonRujLuar.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonRujLuar.setInfoAudit(infoAudit);
                conService.simpanRujukanLuar(mohonRujLuar);
            }
        }

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

//            permohonanKertas.setNomborRujukan(permohonanKertasTemp.getNomborRujukan());
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS JKTL");
        KodDokumen kodDokumen = new KodDokumen();
        kodDokumen.setKod("KRY");
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
        if (StringUtils.isBlank(lokasi)) {
            lokasi = " ";
        }
        if (StringUtils.isBlank(keadaanTanah)) {
            keadaanTanah = " ";
        }
        if (StringUtils.isBlank(perihalTanah)) {
            perihalTanah = " ";
        }
        if (StringUtils.isBlank(maksud)) {
            maksud = " ";
        }
        if (StringUtils.isBlank(perakuanPtg)) {
            perakuanPtg = " ";
        }
//        if (StringUtils.isBlank(keputusan)) {
//            keputusan = " ";
//        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(lokasi);
        listUlasan.add(keadaanTanah);
        listUlasan.add(perihalTanah);
        listUlasan.add(maksud);
        listUlasan.add(perakuanPtg);
//        listUlasan.add(keputusan);

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
                        kertasKdgn.setKandungan(lokasi);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(keadaanTanah);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(perihalTanah);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(maksud);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(perakuanPtg);
                    }
//                    else if (kertasKdgn.getBil() == 9) {
//                        kertasKdgn.setKandungan(keputusan);
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
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(i + 1);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                conService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/ulasan_rayuan_jktlm.jsp").addParameter("tab", "true");
    }

    public void simpanFirst() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("KERTAS JKTL");
        KodDokumen kodDokumen = new KodDokumen();
        kodDokumen.setKod("KRY");
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
        if (StringUtils.isBlank(lokasi)) {
            lokasi = " ";
        }
        if (StringUtils.isBlank(keadaanTanah)) {
            keadaanTanah = " ";
        }
        if (StringUtils.isBlank(perihalTanah)) {
            perihalTanah = " ";
        }
        if (StringUtils.isBlank(maksud)) {
            maksud = " ";
        }
        if (StringUtils.isBlank(perakuanPtg)) {
            perakuanPtg = " ";
        }
//        if (StringUtils.isBlank(keputusan)) {
//            keputusan = " ";
//        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(lokasi);
        listUlasan.add(keadaanTanah);
        listUlasan.add(perihalTanah);
        listUlasan.add(maksud);
        listUlasan.add(perakuanPtg);
//        listUlasan.add(keputusan);

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
                        kertasKdgn.setKandungan(lokasi);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(keadaanTanah);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(perihalTanah);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(maksud);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(perakuanPtg);
                    }
//                    else if (kertasKdgn.getBil() == 9) {
//                        kertasKdgn.setKandungan(keputusan);
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
                kertasKdgn.setKertas(permohonanKertas);
                kertasKdgn.setBil(i + 1);
                kertasKdgn.setInfoAudit(infoAudit);
                kertasKdgn.setKandungan(ulasan);
                kertasKdgn.setCawangan(permohonan.getCawangan());
                conService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getUlasanJabatanPertanian() {
        return ulasanJabatanPertanian;
    }

    public void setUlasanJabatanPertanian(String ulasanJabatanPertanian) {
        this.ulasanJabatanPertanian = ulasanJabatanPertanian;
    }

    public String getUlasanJabatanTenagaKerja() {
        return ulasanJabatanTenagaKerja;
    }

    public void setUlasanJabatanTenagaKerja(String ulasanJabatanTenagaKerja) {
        this.ulasanJabatanTenagaKerja = ulasanJabatanTenagaKerja;
    }

    public String getPerakuanPtg() {
        return perakuanPtg;
    }

    public void setPerakuanPtg(String perakuanPtg) {
        this.perakuanPtg = perakuanPtg;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getRujukanJabatanPertanian() {
        return rujukanJabatanPertanian;
    }

    public void setRujukanJabatanPertanian(String rujukanJabatanPertanian) {
        this.rujukanJabatanPertanian = rujukanJabatanPertanian;
    }

    public String getRujukanJabatanTenagaKerja() {
        return rujukanJabatanTenagaKerja;
    }

    public void setRujukanJabatanTenagaKerja(String rujukanJabatanTenagaKerja) {
        this.rujukanJabatanTenagaKerja = rujukanJabatanTenagaKerja;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String getMaksud() {
        return maksud;
    }

    public void setMaksud(String maksud) {
        this.maksud = maksud;
    }

    public List<PermohonanRujukanLuar> getListUlasanTeknikal() {
        return listUlasanTeknikal;
    }

    public void setListUlasanTeknikal(List<PermohonanRujukanLuar> listUlasanTeknikal) {
        this.listUlasanTeknikal = listUlasanTeknikal;
    }

    public PermohonanKertas getPermohonanKertasTemp() {
        return permohonanKertasTemp;
    }

    public void setPermohonanKertasTemp(PermohonanKertas permohonanKertasTemp) {
        this.permohonanKertasTemp = permohonanKertasTemp;
    }

    public List<PermohonanPihak> getListPenerima() {
        return listPenerima;
    }

    public void setListPenerima(List<PermohonanPihak> listPenerima) {
        this.listPenerima = listPenerima;
    }
}
