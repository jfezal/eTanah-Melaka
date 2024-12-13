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
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
@UrlBinding("/consent/surat_ulasan")
public class SuratUlasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService consentService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    private Permohonan permohonan;
    private PermohonanKertas permohonanKertasTemp;
    private PermohonanKertasKandungan kertasKandungan;
    private String totalLuas;
    private DecimalFormat df = new DecimalFormat("#0.0000");
    private BigDecimal sum = new BigDecimal(0.0000);
    private String kodNegeri;
    private String tajuk; //1
    private String text1; //2
    private String text2; //3
    private String text3; //4
    private boolean complete = true;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private static final Logger LOG = Logger.getLogger(SuratUlasanActionBean.class);

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

            List<PermohonanPihak> listPihakTerlibat = permohonanPihakService.getSenaraiPmohonPihakByKodAndIdHakmilik(permohonan.getIdPermohonan(), "TER", hakmilik.getIdHakmilik());
            List<PermohonanPihak> listPenerima = permohonanPihakService.getSenaraiPmohonPihakByNotTwoKodAndIdHakmilik(permohonan.getIdPermohonan(), "WAR", "TER", hakmilik.getIdHakmilik());

            if (permohonan.getSenaraiPemohon().isEmpty()) {
                complete = false;
                addSimpleError("Sila masukkan maklumat pemohon : " + permohonan.getIdPermohonan());
            } else if (listPihakTerlibat.isEmpty()) {
                complete = false;
                addSimpleError("Sila masukkan maklumat pihak terlibat untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
            } else if (listPenerima.isEmpty()) {
                complete = false;
                addSimpleError("Sila masukkan maklumat penerima untuk hakmilik " + hakmilik.getIdHakmilik() + " : " + permohonan.getIdPermohonan());
            }

            if (!permohonan.getSenaraiPihak().isEmpty()) {
                for (PermohonanPihak perPihak : permohonan.getSenaraiPihak()) {
                    if (!perPihak.getJenis().getKod().equals("TER") && !perPihak.getJenis().getKod().equals("WAR") && !perPihak.getJenis().getKod().equals("PA")) {
                        if (perPihak.getSyerPembilang() == null || perPihak.getSyerPenyebut() == null) {
                            complete = false;
                            addSimpleError("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());

                        } else if (perPihak.getSyerPembilang() == 0 || perPihak.getSyerPenyebut() == 0) {
                            complete = false;
                            addSimpleError("Sila semak maklumat syer untuk ID Hakmilik  " + perPihak.getHakmilik().getIdHakmilik() + " ID Permohonan : " + permohonan.getIdPermohonan());

                        }
                    }
                }
            }
        }

        return new JSP("consent/surat_ulasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (complete) {
            permohonanKertasTemp = consentService.findMohonKertas(idPermohonan);
            PermohonanRujukanLuar permohonanRujukanLuar = consentService.findMohonRujukanNotTangguh2(permohonan.getPermohonanSebelum().getIdPermohonan());

            if (permohonanKertasTemp == null) {

                tajuk = janaTajuk().toUpperCase();

                if (permohonanRujukanLuar != null) {
                    text1 = "2. Sebagaimana yang tuan sedia maklum, Mesyurat Lembaga Tanah Ladang Negeri Melaka " + permohonanRujukanLuar.getNoSidang() + " yang bersidang pada " + sdf.format(permohonanRujukanLuar.getTarikhSidang()) + " telah menolak permohonan tersebut kerana " + permohonan.getPermohonanSebelum().getUlasan() + ".";
                }

                text3 = "Sehubungan dengan itu, kerjasama pihak tuan adalah dipohon untuk mengemukakan alasan berhubung rayuan tersebut dan mengemukakannya ke Pentadbiran ini selewat-selewatnya pada " + sdf.format(getDateAfterDays(7)) + " bagi membolehkan kertas rayuan disediakan untuk pertimbangan Pihak Berkuasa Negeri.";
                simpanFirst();

            } else {

                if (permohonanKertasTemp.getSenaraiKandungan() != null) {

                    for (int j = 0; j < permohonanKertasTemp.getSenaraiKandungan().size(); j++) {

                        kertasKandungan = permohonanKertasTemp.getSenaraiKandungan().get(j);

                        if (kertasKandungan.getBil() == 1) {
                            tajuk = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 2) {
                            text1 = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 3) {
                            text2 = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 4) {
                            text3 = kertasKandungan.getKandungan();
                        }
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

        if (kertasKandungan != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());

        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }

        permohonanKertas.setNomborRujukan(permohonanKertasTemp.getNomborRujukan());
        permohonanKertas.setInfoAudit(infoAudit);
        permohonanKertas.setPermohonan(permohonan);
        permohonanKertas.setCawangan(permohonan.getCawangan());
        permohonanKertas.setTajuk("SURAT ULASAN");
        KodDokumen kodDokumen = new KodDokumen();
        kodDokumen.setKod("SUT");
        permohonanKertas.setKodDokumen(kodDokumen);

        consentService.simpanPermohonanKertas(permohonanKertas);

        ArrayList listUlasan = new ArrayList();

        if (StringUtils.isBlank(tajuk)) {
            tajuk = " ";
        }
        if (StringUtils.isBlank(text1)) {
            text1 = " ";
        }
        if (StringUtils.isBlank(text2)) {
            text2 = " ";
        }
        if (StringUtils.isBlank(text3)) {
            text3 = " ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(text1);
        listUlasan.add(text2);
        listUlasan.add(text3);

        if (kertasKandungan != null) {

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tajuk);
                    } else if (kertasKdgn.getBil() == 2) {
                        kertasKdgn.setKandungan(text1);
                    } else if (kertasKdgn.getBil() == 3) {
                        kertasKdgn.setKandungan(text2);
                    } else if (kertasKdgn.getBil() == 4) {
                        kertasKdgn.setKandungan(text3);
                    }

                    kertasKdgn.setInfoAudit(infoAudit);
                    consentService.simpanPermohonanKertasKandungan(kertasKdgn);
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
                consentService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");



        return new ForwardResolution("/WEB-INF/jsp/consent/surat_ulasan.jsp").addParameter("tab", "true");
    }

    public void simpanFirst() {

//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);

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
        permohonanKertas.setTajuk("SURAT ULASAN");
        KodDokumen kodDokumen = new KodDokumen();
        kodDokumen.setKod("SUT");
        permohonanKertas.setKodDokumen(kodDokumen);

        consentService.simpanPermohonanKertas(permohonanKertas);
        ArrayList listUlasan = new ArrayList();

        if (StringUtils.isBlank(tajuk)) {
            tajuk = " ";
        }
        if (StringUtils.isBlank(text1)) {
            text1 = " ";
        }
        if (StringUtils.isBlank(text2)) {
            text2 = " ";
        }
        if (StringUtils.isBlank(text3)) {
            text3 = " ";
        }

        listUlasan.add(tajuk);
        listUlasan.add(text1);
        listUlasan.add(text2);
        listUlasan.add(text3);

        if (kertasKandungan != null) {

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tajuk);
                    } else if (kertasKdgn.getBil() == 2) {
                        kertasKdgn.setKandungan(text1);
                    } else if (kertasKdgn.getBil() == 3) {
                        kertasKdgn.setKandungan(text2);
                    } else if (kertasKdgn.getBil() == 4) {
                        kertasKdgn.setKandungan(text3);
                    }

                    kertasKdgn.setInfoAudit(infoAudit);
                    consentService.simpanPermohonanKertasKandungan(kertasKdgn);
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
                consentService.simpanPermohonanKertasKandungan(kertasKdgn);
            }
        }
    }

    public String janaTajuk() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info(":::: JANA TAJUK ::::");

        String tajuk;
        String namaPemohon = "";
        String namaPenerima = "";
        String namaHakmilik = "";

        for (int i = 0; i < permohonan.getSenaraiPemohon().size(); i++) {
            Pemohon pmhn = permohonan.getSenaraiPemohon().get(i);
            if (i == 0) {
                namaPemohon = WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
            } else if (i == permohonan.getSenaraiPemohon().size() - 1) {
                namaPemohon = namaPemohon + " dan " + WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
            } else if (i != 0 && i != permohonan.getSenaraiPemohon().size() - 1) {
                namaPemohon = namaPemohon + ", " + WordUtils.capitalizeFully(pmhn.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhn.getPihak().getJenisPengenalan().getNama()) + " : " + pmhn.getPihak().getNoPengenalan().toUpperCase() + ")";
            }
        }

        List<PermohonanPihak> senaraiMohonPihakAll = permohonanPihakService.getSenaraiPmohonPihakByNotKod(permohonan.getIdPermohonan(), "TER");
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
                namaPenerima = WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
            } else if (i == senaraiMohonPihakChecked.size() - 1) {
                namaPenerima = namaPenerima + " dan " + WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
            } else if (i != 0 && i != senaraiMohonPihakChecked.size() - 1) {
                namaPenerima = namaPenerima + ", " + WordUtils.capitalizeFully(pmhnPihak.getPihak().getNama()) + " (" + WordUtils.capitalizeFully(pmhnPihak.getPihak().getJenisPengenalan().getNama()) + " : " + pmhnPihak.getPihak().getNoPengenalan().toUpperCase() + ")";
            }
        }

        //ADD MUKIM TO LIST
        List<Hakmilik> senaraiMukim = new ArrayList<Hakmilik>();

        for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
            if (senaraiMukim.isEmpty()) {
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
                        namaHakmilik = namaHakmilik + " dan " + hakmilikMukim.getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoHakmilik()) + " " + hakmilikMukim.getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMukim.getNoLot());
                    }
                    if (count == senaraiMukimSama.size() - 1) {
                        namaHakmilik = namaHakmilik + " di " + hakmilikMukim.getBandarPekanMukim().getNama() + " ";
                    }
                    count++;
                }
                countMukim++;
                senaraiMukimGenerated.add(senaraiMukimSama.get(0));
            }
        }

        //CALCULATE LUAS
        for (HakmilikPermohonan hakmilikMohon : permohonan.getSenaraiHakmilik()) {
            sum = sum.add(hakmilikMohon.getHakmilik().getLuas());
        }

        totalLuas = df.format(sum) + " " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getKodUnitLuas().getNama());

        //CALCULATE SYER
        String syer = "";
        for (PermohonanPihak mohonPihak : permohonan.getSenaraiPihak()) {
            syer = mohonPihak.getSyerPembilang() + "/" + mohonPihak.getSyerPenyebut();
        }

        if (permohonan.getPermohonanSebelum() == null) {
            if (permohonan.getSenaraiHakmilik().size() < 5) {
                tajuk = "permohonan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik " + namaHakmilik + " seluas " + totalLuas + " Daerah " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama()) + ", Melaka dengan syer terlibat " + syer + " bahagian di bawah Seksyen 214A Kanun Tanah Negara";
            } else {
                tajuk = "permohonan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik seperti di lampiran A seluas  " + totalLuas + " Daerah " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama()) + ", Melaka dengan syer terlibat " + syer + " bahagian di bawah Seksyen 214A Kanun Tanah Negara";
            }
        } else {
            if (permohonan.getSenaraiHakmilik().size() < 5) {
                tajuk = "permohonan rayuan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik " + namaHakmilik + " seluas " + totalLuas + " Daerah " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama()) + ", Melaka dengan syer terlibat " + syer + " bahagian di bawah Seksyen 214A Kanun Tanah Negara";
            } else {
                tajuk = "permohonan rayuan kebenaran pindahmilik tanah ladang daripada " + namaPemohon + " kepada " + namaPenerima + " di atas hakmilik seperti di lampiran A seluas  " + totalLuas + " Daerah " + WordUtils.capitalizeFully(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama()) + ", Melaka dengan syer terlibat " + syer + " bahagian di bawah Seksyen 214A Kanun Tanah Negara";
            }
        }

        LOG.info("::::: TAJUK : " + tajuk);
        LOG.info("::::: TOTAL LUAS : " + totalLuas);

        //SIMPAN TAJUK
        InfoAudit infoAudit = new InfoAudit();
        PermohonanUrusan permohonanUrusan = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "TAJUK");

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
        permohonanUrusan.setPerihal("TAJUK");
        consentService.simpanPermohonanUrusan(permohonanUrusan);

        //SIMPAN TOTAL LUAS
        infoAudit = new InfoAudit();
        PermohonanUrusan permohonanUrusan2 = consentService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "LUAS");

        if (permohonanUrusan2 == null) {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanUrusan2 = new PermohonanUrusan();

        } else {
            infoAudit = permohonanUrusan2.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        }

        permohonanUrusan2.setCawangan(permohonan.getCawangan());
        permohonanUrusan2.setPermohonan(permohonan);
        permohonanUrusan2.setCatatan(totalLuas);
        permohonanUrusan2.setInfoAudit(infoAudit);
        permohonanUrusan2.setPerihal("LUAS");
        consentService.simpanPermohonanUrusan(permohonanUrusan2);

        return tajuk;
    }

    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);// +days
        return cal.getTime();
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public PermohonanKertas getPermohonanKertasTemp() {
        return permohonanKertasTemp;
    }

    public void setPermohonanKertasTemp(PermohonanKertas permohonanKertasTemp) {
        this.permohonanKertasTemp = permohonanKertasTemp;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
