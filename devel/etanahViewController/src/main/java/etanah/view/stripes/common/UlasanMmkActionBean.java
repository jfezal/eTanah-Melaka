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
@UrlBinding("/common/ulasan_mmk")
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
    private PermohonanPihakService permohonanPihakService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertasKandungan kertasKandungan;
    private String tajuk;
    private String tujuan;
    private String latarBelakang;
    private String asasRayuan;
    private String huraianPentadbir;
    private String syorPentadbir;
    private String huraianPtg;
    private String syorPtg;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private static String[] BILANGAN_RAYUAN = {"PERTAMA", "KEDUA", "KETIGA", "KEEMPAT", "KELIMA", "KEENAM", "KETUJUH", "KELAPAN", "KESEMBILAN", "KESEPULUH"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showPtCon() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            if (permohonan.getSenaraiPihak().isEmpty() || permohonan.getSenaraiPemohon().isEmpty()) {
                addSimpleError("Sila Masukkan Maklumat Pemohon Dan Penerima Terlebih Dahulu.");
            }
        }
        return new JSP("common/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showPtPtg() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("latar", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);
        return new JSP("common/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showMmk() {
        getContext().getRequest().setAttribute("viewTajuk", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewLatar", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtd", Boolean.TRUE);
        getContext().getRequest().setAttribute("viewPtg", Boolean.TRUE);
        return new JSP("common/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {

            if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

                permohonanKertasTemp = conService.findMohonKertasByTajuk(idPermohonan, "KERTAS MMK");

                if (permohonanKertasTemp == null) {
                    String namaPemohon = "";
                    String namaPenerima = "";
                    String namaPenerimaGadaian = "";
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

                        if (pmhn.getPihak().getWargaNegara() != null) {
                            if (!pmhn.getPihak().getWargaNegara().getKod().equals("MAL")) {

                                if (pmhn.getPihak().getWargaNegara().getKod().equals("PT")) {
                                    namaPemohon = namaPemohon + " (PENDUDUK TETAP)";
                                } else {
                                    if (pmhn.getPihak().getJenisPengenalan().getKod().equals("B") || pmhn.getPihak().getJenisPengenalan().getKod().equals("L")
                                            || pmhn.getPihak().getJenisPengenalan().getKod().equals("P") || pmhn.getPihak().getJenisPengenalan().getKod().equals("T")
                                            || pmhn.getPihak().getJenisPengenalan().getKod().equals("I") || pmhn.getPihak().getJenisPengenalan().getKod().equals("K")) {

                                        namaPemohon = namaPemohon + " (WARGANEGARA " + pmhn.getPihak().getWargaNegara().getNama() + ")";
                                    } else {
                                        namaPemohon = namaPemohon + " (SYARIKAT ASING)";
                                    }
                                }
                            }
                        }
                    }

                    //ADD AND CHECK SENARAI PENERIMA BUKAN GADAIAN
                    List<PermohonanPihak> senaraiPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "PGA", "TER");
                    List<PermohonanPihak> senaraiPenerimaChecked = new ArrayList<PermohonanPihak>();

                    for (PermohonanPihak perPihak : senaraiPenerima) {

                        if (senaraiPenerimaChecked.isEmpty()) {
                            senaraiPenerimaChecked.add(perPihak);
                        } else {
                            boolean found = false;
                            for (PermohonanPihak perPihakChecked : senaraiPenerimaChecked) {
                                if (perPihak.getPihak().equals(perPihakChecked.getPihak())) {
                                    found = true;
                                }
                            }
                            if (!found) {
                                senaraiPenerimaChecked.add(perPihak);
                            }
                        }
                    }

                    for (int i = 0; i < senaraiPenerimaChecked.size(); i++) {
                        PermohonanPihak pmhnPihak = senaraiPenerimaChecked.get(i);
                        if (i == 0) {
                            namaPenerima = pmhnPihak.getPihak().getNama();
                        } else if (i == senaraiPenerimaChecked.size() - 1) {
                            namaPenerima = namaPenerima + " DAN " + pmhnPihak.getPihak().getNama();
                        } else if (i != 0 && i != senaraiPenerimaChecked.size() - 1) {
                            namaPenerima = namaPenerima + ", " + pmhnPihak.getPihak().getNama();
                        }

                        if (pmhnPihak.getPihak().getWargaNegara() != null) {
                            if (!pmhnPihak.getPihak().getWargaNegara().getKod().equals("MAL")) {

                                if (pmhnPihak.getPihak().getWargaNegara().getKod().equals("PT")) {
                                    namaPenerima = namaPenerima + " (PENDUDUK TETAP)";
                                } else {
                                    if (pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("B") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("L")
                                            || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("P") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("T")
                                            || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("I") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("K")) {

                                        namaPenerima = namaPenerima + " (WARGANEGARA " + pmhnPihak.getPihak().getWargaNegara().getNama() + ")";
                                    } else {
                                        namaPenerima = namaPenerima + " (SYARIKAT ASING)";
                                    }
                                }
                            }
                        }
                    }

                    //ADD AND CHECK SENARAI PENERIMA GADAIAN
                    List<PermohonanPihak> senaraiPenerimaGadaian = permohonanPihakService.getSenaraiPmohonPihakByKod(idPermohonan, "PGA");
                    List<PermohonanPihak> senaraiPenerimaGadaianChecked = new ArrayList<PermohonanPihak>();

                    for (PermohonanPihak perPihak : senaraiPenerimaGadaian) {

                        if (senaraiPenerimaGadaianChecked.isEmpty()) {
                            senaraiPenerimaGadaianChecked.add(perPihak);
                        } else {
                            boolean found = false;
                            for (PermohonanPihak perPihakChecked : senaraiPenerimaGadaianChecked) {
                                if (perPihak.getPihak().equals(perPihakChecked.getPihak())) {
                                    found = true;
                                }
                            }
                            if (!found) {
                                senaraiPenerimaGadaianChecked.add(perPihak);
                            }
                        }
                    }

                    for (int i = 0; i < senaraiPenerimaGadaianChecked.size(); i++) {
                        PermohonanPihak pmhnPihak = senaraiPenerimaGadaianChecked.get(i);
                        if (i == 0) {
                            namaPenerimaGadaian = pmhnPihak.getPihak().getNama();
                        } else if (i == senaraiPenerimaGadaianChecked.size() - 1) {
                            namaPenerimaGadaian = namaPenerimaGadaian + " DAN " + pmhnPihak.getPihak().getNama();
                        } else if (i != 0 && i != senaraiPenerimaGadaianChecked.size() - 1) {
                            namaPenerimaGadaian = namaPenerimaGadaian + ", " + pmhnPihak.getPihak().getNama();
                        }

                        if (pmhnPihak.getPihak().getWargaNegara() != null) {
                            if (!pmhnPihak.getPihak().getWargaNegara().getKod().equals("MAL")) {
                                if (pmhnPihak.getPihak().getWargaNegara().getKod().equals("PT")) {
                                    namaPenerimaGadaian = namaPenerimaGadaian + " (PENDUDUK TETAP)";
                                } else {
                                    if (pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("B") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("L")
                                            || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("P") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("T")
                                            || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("I") || pmhnPihak.getPihak().getJenisPengenalan().getKod().equals("K")) {

                                        namaPenerimaGadaian = namaPenerimaGadaian + " (WARGANEGARA " + pmhnPihak.getPihak().getWargaNegara().getNama() + ")";
                                    } else {
                                        namaPenerimaGadaian = namaPenerimaGadaian + " (SYARIKAT ASING)";
                                    }
                                }
                            }
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

                    String namaUrusan = "";

                    if (permohonan.getKodUrusan().getKod().equals("PJMMK")) {
                        namaUrusan = " UNTUK MENDAPATKAN KEBENARAN PAJAKAN TANAH ";
                    } else if (permohonan.getKodUrusan().getKod().equals("MCMMK")) {
                        namaUrusan = " UNTUK MENDAPATKAN KEBENARAN MENGGADAI TANAH ";
                    } else {
                        namaUrusan = " UNTUK MENDAPATKAN KEBENARAN PINDAHMILIK TANAH ";
                    }


                    //COUNT BILANGAN RAYUAN
                    Permohonan mohonSebelumCheck = new Permohonan();
                    mohonSebelumCheck = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());

                    int countRayuan = 0;
                    while (mohonSebelumCheck != null) {

                        countRayuan++;

                        if (mohonSebelumCheck.getPermohonanSebelum() != null) {
                            String idMohonSebelum = mohonSebelumCheck.getPermohonanSebelum().getIdPermohonan();
                            mohonSebelumCheck = new Permohonan();
                            mohonSebelumCheck = permohonanDAO.findById(idMohonSebelum);
                        } else {
                            mohonSebelumCheck = null;
                        }
                    }

                    String bilanganRayuan = "";

                    if (countRayuan < 11) {
                        bilanganRayuan = BILANGAN_RAYUAN[countRayuan - 1];
                    }


                    if (permohonan.getKodUrusan().getKod().equals("PCMMK")) {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " DAN DIGADAIKAN KEPADA " + namaPenerimaGadaian + " (JUMLAH BAHAGIAN : SEMUA BAHAGIAN)";
                    } else if (permohonan.getKodUrusan().getKod().equals("MCMMK")) {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerimaGadaian + " (JUMLAH BAHAGIAN : SEMUA BAHAGIAN)";
                    } //                    else if (permohonan.getKodUrusan().getKod().equals("GSMMK")) {
                    //                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " (AKTA TANAH KAWASAN PENEMPATAN BERKELOMPOK 1960) (JUMLAH BAHAGIAN : SEMUA BAHAGIAN)";
                    //                    }
                    else {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " (JUMLAH BAHAGIAN : SEMUA BAHAGIAN)";
                    }
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
                                asasRayuan = kertasKandungan.getKandungan();
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
        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(asasRayuan)) {
            asasRayuan = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
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
        listUlasan.add(asasRayuan);
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
                        kertasKdgn.setKandungan(asasRayuan);
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
        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(asasRayuan)) {
            asasRayuan = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
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
        listUlasan.add(asasRayuan);
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
                        kertasKdgn.setKandungan(asasRayuan);
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

//                try {
//                    infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                } catch (java.text.ParseException ex) {
//                    Logger.getLogger(UlasanTanahLadangActionBean.class.getName()).log(Level.SEVERE, null, ex);
//                }

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
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_mmk.jsp").addParameter("tab", "true");
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
        permohonanKertas.setTajuk("KERTAS MMK");

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
        if (StringUtils.isBlank(asasRayuan)) {
            asasRayuan = " ";
        }
        if (StringUtils.isBlank(huraianPentadbir)) {
            huraianPentadbir = " ";
        }
        if (StringUtils.isBlank(syorPentadbir)) {
            syorPentadbir = " ";
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
        listUlasan.add(asasRayuan);
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
                        kertasKdgn.setKandungan(asasRayuan);
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

//                try {
//                    infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                } catch (java.text.ParseException ex) {
//                    Logger.getLogger(UlasanTanahLadangActionBean.class.getName()).log(Level.SEVERE, null, ex);
//                }

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
        getContext().getRequest().setAttribute("ptd", Boolean.TRUE);
        getContext().getRequest().setAttribute("ptg", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/common/ulasan_mmk.jsp").addParameter("tab", "true");
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getAsasRayuan() {
        return asasRayuan;
    }

    public void setAsasRayuan(String asasRayuan) {
        this.asasRayuan = asasRayuan;
    }
}


