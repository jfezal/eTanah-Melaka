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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.ConsentPtdService;
import etanah.service.common.PermohonanPihakService;
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

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/common/ulasan_tanah_ladang")
public class UlasanTanahLadangActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodNegeri;
    String tajuk;
    String tujuan;
    String latarBelakang;
    String ulasanJabatanKebajikan;
    String ulasanJabatanTenagaKerja;
    String ulasanUPEN;
    String ulasanJPBD;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    String jam;
    String minit;
    String ampm;
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");

    @DefaultHandler
    public Resolution showPaparan() {
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewJabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtg", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparanPtCon() {
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewJabatan", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    public Resolution showPtCon() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPihak().isEmpty() || permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon Dan Penerima Terlebih Dahulu.");
        }
        return new JSP("common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    public Resolution showPtPtg() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            if (!permohonan.getSenaraiRujukanLuar().isEmpty()) {
                permohonanRujukanLuar = permohonan.getSenaraiRujukanLuar().get(0);
                if (permohonanRujukanLuar.getTarikhSidang() != null) {
                    tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                    jam = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(11, 13);
                    minit = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(14, 16);
                    ampm = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(20, 22);
                }
            }

            permohonanKertasTemp = conService.findMohonKertasByTajuk(idPermohonan, "KERTAS JKTL");

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

                List<PermohonanPihak> senaraiMohonPihakAll = permohonanPihakService.getSenaraiPmohonPihakByNotKod(idPermohonan, "TER");
                List<PermohonanPihak> senaraiMohonPihakChecked = new ArrayList<PermohonanPihak>();

                for (PermohonanPihak perPihak : senaraiMohonPihakAll) {

                    if (senaraiMohonPihakChecked.isEmpty()) {
                        senaraiMohonPihakChecked.add(perPihak);
                    } else {
                        boolean found = false;
                        for (PermohonanPihak perPihakChecked : senaraiMohonPihakChecked) {
                            if (perPihak.getPihak().equals(perPihakChecked.getPihak())) {
                                found = true;
                            }
                        }
                        if (!found) {
                            senaraiMohonPihakChecked.add(perPihak);
                        }
                    }
                }

                for (int i = 0; i < senaraiMohonPihakChecked.size(); i++) {
                    PermohonanPihak pmhnPihak = senaraiMohonPihakChecked.get(i);
                    if (i == 0) {
                        namaPenerima = pmhnPihak.getPihak().getNama();
                    } else if (i == senaraiMohonPihakChecked.size() - 1) {
                        namaPenerima = namaPenerima + " DAN " + pmhnPihak.getPihak().getNama();
                    } else if (i != 0 && i != senaraiMohonPihakChecked.size() - 1) {
                        namaPenerima = namaPenerima + ", " + pmhnPihak.getPihak().getNama();
                    }
                }

                if (permohonan.getSenaraiHakmilik().size() > 5) {
                    //GENERATE  NAMA HAKMILIK IF HAKMILIK MORE THAN 5
                    namaHakmilik = "BAGI HAKMILIK (SEPERTI DI LAMPIRAN)";
                } else {
                    //GENERATE  NAMA HAKMILIK IF HAKMILIK LESS 5
                    //ADD MUKIM TO LIST
                    List<Hakmilik> senaraiMukim = new ArrayList<Hakmilik>();

                    for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
                        if (senaraiMukim.size() == 0) {
                            senaraiMukim.add(hakmilikMohon.getHakmilik());
                        } else {
                            boolean dataMukim = false;
                            for (Hakmilik mukimCheck : senaraiMukim) {
                                if (mukimCheck.getBandarPekanMukim().getKod() == hakmilikMohon.getHakmilik().getBandarPekanMukim().getKod()) {
                                    dataMukim = true;
                                    break;
                                }
                            }
                            if (!dataMukim) {
                                senaraiMukim.add(hakmilikMohon.getHakmilik());
                            }
                        }
                    }

                    //GENERATE NAMA HAKMILIK MAIN
                    int countMukim = 0;
                    List<Hakmilik> senaraiMukimGenerated = new ArrayList<Hakmilik>();

                    for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                        HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);

                        //CHECK GENERATED OR NOT
                        boolean doGenerate = true;
                        if (senaraiMukimGenerated.size() > 0) {
                            for (Hakmilik hakmilikGenerated : senaraiMukimGenerated) {

                                if (hakmilikMohon.getHakmilik().getBandarPekanMukim().getKod() == hakmilikGenerated.getBandarPekanMukim().getKod()) {
                                    doGenerate = false;
                                    break;
                                }
                            }
                        }

                        //GENERATE NAMA HAKMILIK IF TRUE
                        if (doGenerate) {
                            //ADD DATA MUKIM SAMA TO LIST
                            List<Hakmilik> senaraiMukimSama = new ArrayList<Hakmilik>();
                            for (int j = 0; j < permohonan.getSenaraiHakmilik().size(); j++) {
                                HakmilikPermohonan hakmilikMohon2 = permohonan.getSenaraiHakmilik().get(j);
                                if (senaraiMukim.get(countMukim).getBandarPekanMukim().getKod() == hakmilikMohon2.getHakmilik().getBandarPekanMukim().getKod()) {
                                    senaraiMukimSama.add(hakmilikMohon2.getHakmilik());
                                }
                            }
                            int count = 0;
                            for (Hakmilik hakmilikMukim : senaraiMukimSama) {
                                if (count == 0) {
                                    namaHakmilik = namaHakmilik + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                } else if (count != 0 && count != senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + ", " + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                } else if (count == senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + " DAN " + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                                }
                                if (count == senaraiMukimSama.size() - 1) {
                                    namaHakmilik = namaHakmilik + " DI " + hakmilikMukim.getBandarPekanMukim().getNama() + " ";
                                }
                                count++;
                            }
                            countMukim++;
                            senaraiMukimGenerated.add(senaraiMukimSama.get(0));
                        }
                    }
                }

                tajuk = "KEBENARAN PINDAH MILIK TANAH LADANG BAGI " + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " DARIPADA " + namaPemohon + " KEPADA " + namaPenerima + ".";
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
                            ulasanJabatanKebajikan = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            ulasanJabatanTenagaKerja = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            ulasanUPEN = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 7) {
                            ulasanJPBD = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 8) {
                            huraianPtg = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 9) {
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
        permohonanKertas.setTajuk("KERTAS JKTL");

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
        if (StringUtils.isBlank(ulasanJabatanKebajikan)) {
            ulasanJabatanKebajikan = " ";
        }
        if (StringUtils.isBlank(ulasanJabatanTenagaKerja)) {
            ulasanJabatanTenagaKerja = " ";
        }
        if (StringUtils.isBlank(ulasanUPEN)) {
            ulasanUPEN = " ";
        }
        if (StringUtils.isBlank(ulasanJPBD)) {
            ulasanJPBD = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = "Permohonan ini boleh dipertimbangkan.";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = "Permohonan ini disyorkan lulus/tolak.";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(ulasanJabatanKebajikan);
        listUlasan.add(ulasanJabatanTenagaKerja);
        listUlasan.add(ulasanUPEN);
        listUlasan.add(ulasanJPBD);
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
                        kertasKdgn.setKandungan(ulasanJabatanKebajikan);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(ulasanJabatanTenagaKerja);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(ulasanUPEN);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(ulasanJPBD);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 9) {
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
        permohonanKertas.setTajuk("KERTAS JKTL");

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
        if (StringUtils.isBlank(ulasanJabatanKebajikan)) {
            ulasanJabatanKebajikan = " ";
        }
        if (StringUtils.isBlank(ulasanJabatanTenagaKerja)) {
            ulasanJabatanTenagaKerja = " ";
        }
        if (StringUtils.isBlank(ulasanUPEN)) {
            ulasanUPEN = " ";
        }
        if (StringUtils.isBlank(ulasanJPBD)) {
            ulasanJPBD = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = "Permohonan ini boleh dipertimbangkan.";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = "Permohonan ini disyorkan lulus/tolak.";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(ulasanJabatanKebajikan);
        listUlasan.add(ulasanJabatanTenagaKerja);
        listUlasan.add(ulasanUPEN);
        listUlasan.add(ulasanJPBD);
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
                        kertasKdgn.setKandungan(ulasanJabatanKebajikan);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(ulasanJabatanTenagaKerja);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(ulasanUPEN);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(ulasanJPBD);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 9) {
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

        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
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
        permohonanKertas.setTajuk("KERTAS JKTL");

        conService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();

        if (StringUtils.isBlank(tajuk)) {
            tajuk = " ";
        }
        if (StringUtils.isBlank(tujuan)) {
            latarBelakang = " ";
        }
        if (StringUtils.isBlank(latarBelakang)) {
            latarBelakang = " ";
        }
        if (StringUtils.isBlank(ulasanJabatanKebajikan)) {
            ulasanJabatanKebajikan = " ";
        }
        if (StringUtils.isBlank(ulasanJabatanTenagaKerja)) {
            ulasanJabatanTenagaKerja = " ";
        }
        if (StringUtils.isBlank(ulasanUPEN)) {
            ulasanUPEN = " ";
        }
        if (StringUtils.isBlank(ulasanJPBD)) {
            ulasanJPBD = " ";
        }
        if (StringUtils.isBlank(huraianPtg)) {
            huraianPtg = "Permohonan ini boleh dipertimbangkan.";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = "Permohonan ini disyorkan lulus/tolak.";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(ulasanJabatanKebajikan);
        listUlasan.add(ulasanJabatanTenagaKerja);
        listUlasan.add(ulasanUPEN);
        listUlasan.add(ulasanJPBD);
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
                        kertasKdgn.setKandungan(ulasanJabatanKebajikan);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(ulasanJabatanTenagaKerja);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(ulasanUPEN);
                    } else if (kertasKdgn.getBil() == 7) {
                        kertasKdgn.setKandungan(ulasanJPBD);
                    } else if (kertasKdgn.getBil() == 8) {
                        kertasKdgn.setKandungan(huraianPtg);
                    } else if (kertasKdgn.getBil() == 9) {
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

        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("jabatan", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_tanah_ladang.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public String getUlasanJabatanKebajikan() {
        return ulasanJabatanKebajikan;
    }

    public void setUlasanJabatanKebajikan(String ulasanJabatanKebajikan) {
        this.ulasanJabatanKebajikan = ulasanJabatanKebajikan;
    }

    public String getUlasanJabatanTenagaKerja() {
        return ulasanJabatanTenagaKerja;
    }

    public void setUlasanJabatanTenagaKerja(String ulasanJabatanTenagaKerja) {
        this.ulasanJabatanTenagaKerja = ulasanJabatanTenagaKerja;
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

    public String getUlasanJPBD() {
        return ulasanJPBD;
    }

    public void setUlasanJPBD(String ulasanJPBD) {
        this.ulasanJPBD = ulasanJPBD;
    }

    public String getUlasanUPEN() {
        return ulasanUPEN;
    }

    public void setUlasanUPEN(String ulasanUPEN) {
        this.ulasanUPEN = ulasanUPEN;
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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
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
}


