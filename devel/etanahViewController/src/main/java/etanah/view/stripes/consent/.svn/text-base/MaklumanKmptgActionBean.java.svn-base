               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

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
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
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
@UrlBinding("/consent/makluman_kmptg")
public class MaklumanKmptgActionBean extends AbleActionBean {

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
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertasKandungan kertasKandungan;
    private String tajuk;
    private String tujuan;
    private String latarBelakang;
    private String maklumanPtg;
    private String syorPtg;
    private String keputusan;
    private boolean tangguh;
    private static String[] BILANGAN_RAYUAN = {"PERTAMA", "KEDUA", "KETIGA", "KEEMPAT", "KELIMA", "KEENAM", "KETUJUH", "KELAPAN", "KESEMBILAN", "KESEPULUH"};

    @DefaultHandler
    public Resolution showPtPtg() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("consent/makluman_kmptg.jsp").addParameter("tab", "true");
    }

    public Resolution showPaparan() {
        return new JSP("consent/makluman_kmptg.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        String checkStage;

        if (permohonan.getKodUrusan().getKod().equals("PMJTL")) {
            checkStage = "Stage9";
        } else if (permohonan.getKodUrusan().getKod().equals("RJKTL")) {
            checkStage = "Stage12";
        } else {
            checkStage = "Stage10";
        }

        FasaPermohonan fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, checkStage);

        if (fasaPermohonan == null) {
            tangguh = false;
        } else {
            tangguh = true;
        }

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            permohonanKertasTemp = conService.findMohonKertasByTajuk(idPermohonan, "KERTAS MAKLUMAN");

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

                List<PermohonanPihak> senaraiPenerima = senaraiPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKod(idPermohonan, "PGA", "TER");
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
                    namaPenerima = namaPenerima + " (JUMLAH BAHAGIAN : " + pmhnPihak.getSyerPembilang() + "/" + pmhnPihak.getSyerPenyebut() + " BAHAGIAN)";
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
                    namaPenerimaGadaian = namaPenerimaGadaian + " (JUMLAH BAHAGIAN : " + pmhnPihak.getSyerPembilang() + "/" + pmhnPihak.getSyerPenyebut() + " BAHAGIAN)";
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

                if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PJMMK")) {
                    namaUrusan = " UNTUK MENDAPATKAN KEBENARAN PAJAKAN TANAH ";
                } else if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCMMK") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCGMB")) {
                    namaUrusan = " UNTUK MENDAPATKAN KEBENARAN MENGGADAI TANAH ";
                } else {
                    namaUrusan = " UNTUK MENDAPATKAN KEBENARAN PINDAHMILIK TANAH ";
                }

                if (permohonan.getPermohonanSebelum() == null) {
                    //KES PERMOHONAN BIASA
                    if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PCMMK") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PGDMB")) {
                        tajuk = "PERMOHONAN DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " DAN DIGADAIKAN KEPADA " + namaPenerimaGadaian;
                    } else if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCMMK") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCGMB")) {
                        tajuk = "PERMOHONAN DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerimaGadaian;
                    } //                    else if (permohonan.getKodUrusan().getKod().equals("GSMMK")) {
                    //                        tajuk = "PERMOHONAN DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " (AKTA TANAH KAWASAN PENEMPATAN BERKELOMPOK 1960) (JUMLAH BAHAGIAN : SEMUA BAHAGIAN)";
                    //                    }
                    else {
                        tajuk = "PERMOHONAN DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima;
                    }
                } else {
                    //KES PERMOHONAN RAYUAN
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

                    if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PCMMK") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PGDMB")) {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima + " DAN DIGADAIKAN KEPADA " + namaPenerimaGadaian;
                    } else if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCMMK") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("MCGMB")) {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerimaGadaian;
                    } else {
                        tajuk = "RAYUAN " + bilanganRayuan + " DARIPADA " + namaPemohon + namaUrusan + namaHakmilik + " DAERAH " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " KEPADA " + namaPenerima;
                    }
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
                            maklumanPtg = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            syorPtg = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            keputusan = kertasKandungan.getKandungan();
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
        permohonanKertas.setTajuk("KERTAS MAKLUMAN");

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
        if (StringUtils.isBlank(maklumanPtg)) {
            maklumanPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        if (StringUtils.isBlank(keputusan)) {
            keputusan = " ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(maklumanPtg);
        listUlasan.add(syorPtg);
        listUlasan.add(keputusan);

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
                        kertasKdgn.setKandungan(maklumanPtg);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(syorPtg);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(keputusan);
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
        permohonanKertas.setTajuk("KERTAS MAKLUMAN");

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
        if (StringUtils.isBlank(maklumanPtg)) {
            maklumanPtg = " ";
        }
        if (StringUtils.isBlank(syorPtg)) {
            syorPtg = " ";
        }
        if (StringUtils.isBlank(keputusan)) {
            keputusan = " ";
        }
        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(maklumanPtg);
        listUlasan.add(syorPtg);
        listUlasan.add(keputusan);

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
                        kertasKdgn.setKandungan(maklumanPtg);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(syorPtg);
                    } else if (kertasKdgn.getBil() == 6) {
                        kertasKdgn.setKandungan(keputusan);
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/consent/makluman_kmptg.jsp").addParameter("tab", "true");
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

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public String getMaklumanPtg() {
        return maklumanPtg;
    }

    public void setMaklumanPtg(String maklumanPtg) {
        this.maklumanPtg = maklumanPtg;
    }

    public boolean isTangguh() {
        return tangguh;
    }

    public void setTangguh(boolean tangguh) {
        this.tangguh = tangguh;
    }
}


