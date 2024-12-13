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
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/maklumat_risalat_mmkn")
public class MaklumatRisalatMmknActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanKertasKandungan kertasKandungan;
    String tajuk;
    String tujuan;
    String latarBelakang;
    String asas;
    String ulasanPtg;
    String syorPtg;
    String keputusan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("consent/maklumat_risalat_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            //Set Tajuk
            String namaHakmilik = "";
            for (int i = 0; i < permohonan.getSenaraiHakmilik().size(); i++) {
                HakmilikPermohonan hakmilikMohon = permohonan.getSenaraiHakmilik().get(i);
                if (i == 0) {
                    namaHakmilik = hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " SELUAS " + hakmilikMohon.getHakmilik().getLuas() + " " + hakmilikMohon.getHakmilik().getKodUnitLuas().getNama() + " DI " + hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama() + ", " + hakmilikMohon.getHakmilik().getDaerah().getNama();
                } else if (i == permohonan.getSenaraiHakmilik().size() - 1) {
                    namaHakmilik = namaHakmilik + " DAN " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " SELUAS " + hakmilikMohon.getHakmilik().getLuas() + " " + hakmilikMohon.getHakmilik().getKodUnitLuas().getNama() + " DI " + hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama() + ", " + hakmilikMohon.getHakmilik().getDaerah().getNama();
                } else if (i != 0 && i != permohonan.getSenaraiHakmilik().size() - 1) {
                    namaHakmilik = namaHakmilik + ", " + hakmilikMohon.getHakmilik().getKodHakmilik().getKod() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoHakmilik()) + " " + hakmilikMohon.getHakmilik().getLot().getNama() + " " + etanah.util.StringUtils.removeLeadingZeros(hakmilikMohon.getHakmilik().getNoLot()) + " SELUAS " + hakmilikMohon.getHakmilik().getLuas() + " " + hakmilikMohon.getHakmilik().getKodUnitLuas().getNama() + " DI " + hakmilikMohon.getHakmilik().getBandarPekanMukim().getNama() + ", " + hakmilikMohon.getHakmilik().getDaerah().getNama();
                }
            }

            String namaPemohon;
            String jenisHartanah;

            //Set nama pemohon
            Pemohon pemohon = permohonan.getSenaraiPemohon().get(0);
            if (pemohon.getPihak().getJenisPengenalan().getKod().equals("S")) {
                namaPemohon = "SYARIKAT " + pemohon.getPihak().getWargaNegara().getNama();
            } else if (pemohon.getPihak().getJenisPengenalan().getKod().equals("N") || pemohon.getPihak().getJenisPengenalan().getKod().equals("U") || pemohon.getPihak().getJenisPengenalan().getKod().equals("D")) {
                namaPemohon = pemohon.getPihak().getNama() + " " + pemohon.getPihak().getJenisPengenalan().getNama() + " " + pemohon.getPihak().getNoPengenalan() + " DIDAFTARKAN DI " + pemohon.getPihak().getWargaNegara().getNama();
            } else {
                namaPemohon = pemohon.getPihak().getNama() + " " + pemohon.getPihak().getJenisPengenalan().getNama() + " " + pemohon.getPihak().getNoPengenalan() + " WARGANEGARA " + pemohon.getPihak().getWargaNegara().getNama();
            }

            if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                jenisHartanah = "HARTANAH";
            } else {
                jenisHartanah = "HARTANAH DI NEGERI MELAKA MELALUI HARTA PESAKA";
            }

            tajuk = "PERMOHONAN DARIPADA " + namaPemohon + " UNTUK MEMILIKI " + jenisHartanah + " DI ATAS HAKMILIK " + namaHakmilik + ", MELAKA DI BAWAH SEKSYEN 433B (1) KANUN TANAH NEGARA 1965";

            tajuk = tajuk.toUpperCase();

            PermohonanKertas permohonanKertas = conService.findMohonKertas(permohonan.getIdPermohonan());

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
                        ulasanPtg = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 6) {
                        syorPtg = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 7) {
                        keputusan = kertasKandungan.getKandungan();
                    }
                }
//                }
            }
        }

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
            permohonanKertas.setTajuk("RISALAT MMKN");

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
            if (StringUtils.isBlank(ulasanPtg)) {
                ulasanPtg = " ";
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
            listUlasan.add(asas);
            listUlasan.add(ulasanPtg);
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
                            kertasKdgn.setKandungan(asas);
                        } else if (kertasKdgn.getBil() == 5) {
                            kertasKdgn.setKandungan(ulasanPtg);
                        } else if (kertasKdgn.getBil() == 6) {
                            kertasKdgn.setKandungan(syorPtg);
                        } else if (kertasKdgn.getBil() == 7) {
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
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_risalat_mmkn.jsp").addParameter("tab", "true");
    }

    public String getAsas() {
        return asas;
    }

    public void setAsas(String asas) {
        this.asas = asas;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
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

    public String getUlasanPtg() {
        return ulasanPtg;
    }

    public void setUlasanPtg(String ulasanPtg) {
        this.ulasanPtg = ulasanPtg;
    }
}
