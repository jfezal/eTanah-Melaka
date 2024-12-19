
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

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
import etanah.model.KodRujukan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/common/ulasan_tanah_adat")
public class UlasanTanahAdatActionBean extends AbleActionBean {

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
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertasTemp;
    String latarBelakang;
    String tujuan;
    String butirTanah;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    String tajuk;
    String jam;
    String minit;
    String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPtCon() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon Terlebih Dahulu.");
        }
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPtAndPtd() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptAndPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPtd() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPtPtg() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);

        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showPtg() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution showMMK() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikh", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtg", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            FasaPermohonan fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage11");

            permohonanRujukanLuar = conService.findMohonRujukanByNamaSidangNotTangguh(idPermohonan, "MMK PERTAMA");

            if (fasaPermohonan == null) {
                permohonanKertasTemp = conService.findMohonKertasByTajuk(idPermohonan, "MMK PERTAMA");
                permohonanRujukanLuar = conService.findMohonRujukanByNamaSidangNotTangguh(idPermohonan, "MMK PERTAMA");
            } else {
                permohonanKertasTemp = conService.findMohonKertasByTajuk(idPermohonan, "MMK KEDUA");
                permohonanRujukanLuar = conService.findMohonRujukanByNamaSidangNotTangguh(idPermohonan, "MMK KEDUA");
            }

            if (permohonanRujukanLuar != null) {
                if (permohonanRujukanLuar.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang());
//                    jam = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
//                    minit = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
//                    ampm = sdf2.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                }
            }

            if (permohonanKertasTemp == null) {

                String namaPemohon = "";
                String namaPenerima = "";
                String namaHakmilik = "";

                for (int i = 0; i < permohonan.getSenaraiPemohon().size(); i++) {
                    Pemohon pmhn = permohonan.getSenaraiPemohon().get(i);
                    if (i == 0) {
                        namaPemohon = pmhn.getPihak().getNama();
                    } else if (i == permohonan.getSenaraiPemohon().size() - 1) {
                        namaPemohon = namaPemohon + " DAN " + pmhn.getPihak().getNama();
                    } else if (i != 0 && i != permohonan.getSenaraiPemohon().size() - 1) {
                        namaPemohon = namaPemohon + ", " + pmhn.getPihak().getNama();
                    }
                }

                List<PermohonanPihak> penerimaList = new ArrayList<PermohonanPihak>();
                for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                    boolean flag = true;
                    if (penerimaList.isEmpty()) {
                        penerimaList.add(perPihak);
                    } else {
                        for (PermohonanPihak perPihak2 : penerimaList) {

                            if (perPihak.getPihak().getIdPihak() == perPihak2.getPihak().getIdPihak()) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            penerimaList.add(perPihak);
                        }
                    }
                }

                for (int i = 0; i < penerimaList.size(); i++) {
                    PermohonanPihak pmhnPihak = penerimaList.get(i);
                    if (i == 0) {
                        namaPenerima = pmhnPihak.getPihak().getNama();
                    } else if (i == penerimaList.size() - 1) {
                        namaPenerima = namaPenerima + " DAN " + pmhnPihak.getPihak().getNama();
                    } else if (i != 0 && i != penerimaList.size() - 1) {
                        namaPenerima = namaPenerima + ", " + pmhnPihak.getPihak().getNama();
                    }
                }

                for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                    HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);
                    if (i == 0) {
                        namaHakmilik = hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot());
                    } else if (i == permohonan.getSenaraiHakmilik().size() - 1) {
                        namaHakmilik = namaHakmilik + " DAN " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot());
                    } else if (i != 0 && i != permohonan.getSenaraiHakmilik().size() - 1) {
                        namaHakmilik = namaHakmilik + ", " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot());
                    }
                }

                tajuk = "PERTIMBANGAN SEMULA MAJLIS MESYUARAT KERAJAAN KE ATAS PERMOHONAN RAYUAN BANTAHAN DAN ULANG BICARA DI BAWAH SEKSYEN 15(1) ENAKMEN TANAH ADAT BAB 215 DARIPADA  " + namaPemohon + " TERHADAP PERINTAH PENTADBIR TANAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + ".";
                tajuk = tajuk.toUpperCase();

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
                            butirTanah = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            huraianPentadbir = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            syorPentadbir = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 7) {
                            huraianPtg = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 8) {
                            syorPtg = kertasKandungan.getKandungan();
                        }
                    }
                }
            }
        }
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

        FasaPermohonan fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage11");

        if (fasaPermohonan == null) {
            permohonanKertas.setTajuk("MMK PERTAMA");
        } else {
            permohonanKertas.setTajuk("MMK KEDUA");
        }

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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
    }

    public Resolution simpanPtg() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanMMK() {

        if (tarikhMesyuarat == null || permohonanRujukanLuar == null) {
            if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat");
            }
            if (permohonanRujukanLuar == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat");
            }
        } else {
            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();
            PermohonanRujukanLuar permohonanRujLuarTemp = new PermohonanRujukanLuar();

            FasaPermohonan fasaPermohonan = conService.findMohonFasaByStage(permohonan.getIdPermohonan(), "Stage11");

            if (fasaPermohonan == null) {
                permohonanRujLuarTemp = conService.findMohonRujukanByNamaSidangNotTangguh(permohonan.getIdPermohonan(), "MMK PERTAMA");
            } else {
                permohonanRujLuarTemp = conService.findMohonRujukanByNamaSidangNotTangguh(permohonan.getIdPermohonan(), "MMK KEDUA");
            }

            if (permohonanRujLuarTemp != null) {
                infoAudit = new InfoAudit();
                infoAudit = permohonanRujLuarTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanRujLuarTemp = new PermohonanRujukanLuar();
            }

//            tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + ":00 " + ampm;

            try {
                permohonanRujLuarTemp.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(UlasanTanahAdatActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }


            permohonanRujLuarTemp.setNoSidang(permohonanRujukanLuar.getNoSidang());
//            permohonanRujLuarTemp.setLokasiAgensi(permohonanRujukanLuar.getLokasiAgensi());
            permohonanRujLuarTemp.setCawangan(pengguna.getKodCawangan());
            permohonanRujLuarTemp.setPermohonan(permohonan);
            permohonanRujLuarTemp.setInfoAudit(infoAudit);

            if (fasaPermohonan == null) {
                permohonanRujLuarTemp.setNamaSidang("MMK PERTAMA");
            } else {
                permohonanRujLuarTemp.setNamaSidang("MMK KEDUA");
            }

            KodRujukan kodRujukan = new KodRujukan();
            kodRujukan.setKod("FL");
            permohonanRujLuarTemp.setKodRujukan(kodRujukan);

            conService.simpanRujukanLuar(permohonanRujLuarTemp);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikh", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtg", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPtd() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewTujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPtCon() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPtPtg() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPt() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(latarBelakang)) {
            latarBelakang = " ";
        }
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPtAndPtd() {

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
//        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(butirTanah)) {
            butirTanah = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(butirTanah);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(huraianPtg);
        listUlasan.add(syorPtg);

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
                        kertasKdgn.setKandungan(butirTanah);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(huraianPentadbir);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(syorPentadbir);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(syorPtg);
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("tujuan", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptAndPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("showPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_adat.jsp").addParameter("tab", "true");
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getButirTanah() {
        return butirTanah;
    }

    public void setButirTanah(String butirTanah) {
        this.butirTanah = butirTanah;
    }
}



