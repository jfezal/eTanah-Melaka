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
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@UrlBinding("/consent/kertas_ringkas_mmkn")
public class KertasRingkasMmknActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private PermohonanKertasKandungan kertasKandungan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanUrusan mohonUrusanRayuan;
    private FasaPermohonan fasaPermohonan;
    String tajuk;
    String alamat;
    String lokasi;
    String harga;
    String keputusanDulu;
    String perakuanPtg;
    String idHakmilik;
    String unitLuas;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    etanahActionBeanContext ctx;
    private static final Logger LOG = Logger.getLogger(KertasRingkasMmknActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("consent/kertas_ringkas_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("hakmilik :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
            } else {
                hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            }

            if (hakmilikPermohonan != null) {

                if (hakmilikPermohonan.getLuasTerlibat() == null && hakmilikPermohonan.getKodUnitLuas() == null) {
                    hakmilikPermohonan.setLuasTerlibat(hakmilikPermohonan.getHakmilik().getLuas());
                    hakmilikPermohonan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
                    conService.simpanHakmilikPermohonan(hakmilikPermohonan);
                }

                if (hakmilikPermohonan.getKodUnitLuas() != null) {
                    unitLuas = hakmilikPermohonan.getKodUnitLuas().getKod();
                }
            }
        }

        if (!(permohonan.getSenaraiPihak().isEmpty()) && !(permohonan.getSenaraiPemohon().isEmpty()) && !(permohonan.getSenaraiHakmilik().isEmpty())) {

            fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage9");

            //KES PERMOHONAN BIASA
            if (fasaPermohonan == null) {
                PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "TAJUK");

                if (permohonanUrusan != null) {
                    tajuk = permohonanUrusan.getCatatan().toUpperCase();
                }

                PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");

                if (permohonanKertas != null) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                        if (kertasKandungan.getBil() == 1) {
                            tajuk = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 2) {
                            alamat = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 3) {
                            lokasi = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 4) {
                            harga = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 5) {
                            keputusanDulu = kertasKandungan.getKandungan();
                        } else if (kertasKandungan.getBil() == 6) {
                            perakuanPtg = kertasKandungan.getKandungan();
                        }
                    }
                }
            }//KES PERMOHONAN RAYUAN PENGURANGAN BAYARAN
            else {

                mohonUrusanRayuan = conService.findMohonUrusanByPerihal(idPermohonan, "RAYUAN");
                if (mohonUrusanRayuan != null) {
                    PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(idPermohonan, "TAJUK RAYUAN");

                    if (permohonanUrusan == null) {
                        permohonanUrusan = janaTajuk();
                    }
                    tajuk = permohonanUrusan.getCatatan().toUpperCase();

                    PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS RAYUAN");

                    if (permohonanKertas != null) {

                        for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                            kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                            if (kertasKandungan.getBil() == 1) {
                                tajuk = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 2) {
                                alamat = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 3) {
                                lokasi = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 4) {
                                harga = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 5) {
                                keputusanDulu = kertasKandungan.getKandungan();
                            } else if (kertasKandungan.getBil() == 6) {
                                perakuanPtg = kertasKandungan.getKandungan();
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        if (tajuk == null) {
            if (tajuk == null) {
                addSimpleError("Sila masukkan maklumat terlebih dahulu.");
            }

        } else {

            String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            HakmilikPermohonan hakmilikPermohonanTemp = conService.findMohonHakmilikByIdH(idPermohonan, hakmilikPermohonan.getHakmilik().getIdHakmilik());

            if (hakmilikPermohonanTemp != null) {
                infoAudit = hakmilikPermohonanTemp.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                hakmilikPermohonanTemp.setInfoAudit(infoAudit);

            } else {
                hakmilikPermohonanTemp = new HakmilikPermohonan();
                hakmilikPermohonanTemp.setPermohonan(permohonan);
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                hakmilikPermohonanTemp.setInfoAudit(infoAudit);
            }

            if (hakmilikPermohonan != null) {
                hakmilikPermohonanTemp.setKeteranganInfra(hakmilikPermohonan.getKeteranganInfra()); //ALAMAT
                hakmilikPermohonanTemp.setKeteranganKadarDaftar(hakmilikPermohonan.getKeteranganKadarDaftar());//NILAI HARTANAH
                hakmilikPermohonanTemp.setLokasi(hakmilikPermohonan.getLokasi());//LOKASI
                hakmilikPermohonanTemp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                
                if (unitLuas != null) {
                    KodUOM kodUOM = new KodUOM();
                    kodUOM.setKod(unitLuas);
                    hakmilikPermohonanTemp.setKodUnitLuas(kodUOM);
                }
            }

            conService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);

            FasaPermohonan fasaPermohonan = conService.findMohonFasaByStage(idPermohonan, "Stage9");
            PermohonanKertas permohonanKertas = new PermohonanKertas();

            if (fasaPermohonan == null) {
                permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");
            } else {
                permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS RAYUAN");
            }

            if (permohonanKertas != null) {
//                permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
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
                permohonanKertas.setTajuk("RINGKAS MMKN");
                kodDokumen.setKod("RMN");
            } else {
                permohonanKertas.setTajuk("RINGKAS RAYUAN");
                kodDokumen.setKod("RMNR");
            }

            permohonanKertas.setKodDokumen(kodDokumen);

            conService.simpanPermohonanKertas(permohonanKertas);

            ArrayList listUlasan = new ArrayList();

            if (StringUtils.isBlank(tajuk)) {
                tajuk = " ";
            }
            if (StringUtils.isBlank(alamat)) {
                alamat = " ";
            }
            if (StringUtils.isBlank(lokasi)) {
                lokasi = " ";
            }
            if (StringUtils.isBlank(harga)) {
                harga = " ";
            }
            if (StringUtils.isBlank(keputusanDulu)) {
                keputusanDulu = " ";
            }
            if (StringUtils.isBlank(perakuanPtg)) {
                perakuanPtg = " ";
            }

            listUlasan.add(tajuk);
            listUlasan.add(alamat);
            listUlasan.add(lokasi);
            listUlasan.add(harga);
            listUlasan.add(keputusanDulu);
            listUlasan.add(perakuanPtg);

            if (kertasKandungan != null) {

                if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                        PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                        kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                        if (kertasKdgn.getBil() == 1) {
                            kertasKdgn.setKandungan(tajuk);
                        } else if (kertasKdgn.getBil() == 2) {
                            kertasKdgn.setKandungan(alamat);
                        } else if (kertasKdgn.getBil() == 3) {
                            kertasKdgn.setKandungan(lokasi);
                        } else if (kertasKdgn.getBil() == 4) {
                            kertasKdgn.setKandungan(harga);
                        } else if (kertasKdgn.getBil() == 5) {
                            kertasKdgn.setKandungan(keputusanDulu);
                        } else if (kertasKdgn.getBil() == 6) {
                            kertasKdgn.setKandungan(perakuanPtg);
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

        return new ForwardResolution("/WEB-INF/jsp/consent/kertas_ringkas_mmkn.jsp").addParameter("tab", "true");
    }

    public Resolution selectHakmilik() {
        LOG.info("::select hakmilik::");
        return showForm();

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

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKeputusanDulu() {
        return keputusanDulu;
    }

    public void setKeputusanDulu(String keputusanDulu) {
        this.keputusanDulu = keputusanDulu;
    }

    public String getPerakuanPtg() {
        return perakuanPtg;
    }

    public void setPerakuanPtg(String perakuanPtg) {
        this.perakuanPtg = perakuanPtg;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public String getUnitLuas() {
        return unitLuas;
    }

    public void setUnitLuas(String unitLuas) {
        this.unitLuas = unitLuas;
    }
}
