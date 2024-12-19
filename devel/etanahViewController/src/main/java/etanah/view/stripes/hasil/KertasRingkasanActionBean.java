/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.KodKategoriTanah;
import etanah.model.KodPBT;
import etanah.model.KodPemilikan;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.ConsentPtdService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
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
import org.apache.log4j.Logger;

/**
 *
 * @author khadijah
 */
@UrlBinding("/hasil/kertas_ringkasan")
public class KertasRingkasanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    @Inject
    PengambilanMMKService pengambilanMMKService;
    private PermohonanKertas permohonanKertas;
    private Permohonan permohonan;
    private PermohonanKertasKandungan kertasKandungan;
    //private <PermohonanKertasKandungan> kertasKandunganList;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanKertasKandungan headingObj;
    private PermohonanKertasKandungan keputusanTerdahuluObj;
    private PermohonanKertasKandungan perakuanPTDObj;
    private PermohonanKertasKandungan ulasanYBObj;
    private PermohonanKertasKandungan perakuanPTGObj;
    String tajuk;
    String alamat;
    String lokasi;
    String harga;
    String keputusanDulu;
    String ulasanYb;
    String perakuanPtd;
    String perakuanPtg;
    String idHakmilik;
    Long idKertas;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String tarikhmesyuarat;
    private String jam;
    private String minit;
    private String pagiPetang;
    private String tempat;
    private boolean flag = false;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    etanahActionBeanContext ctx;
    private static final Logger LOG = Logger.getLogger(KertasRingkasanActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("hasil/kertas_ringkasan_mmkn.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("formPtg", Boolean.TRUE);
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("hasil/kertas_ringkasan_mmkn.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("hakmilik :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("----------------------------" + idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, idHakmilik);
            } else {
                hakmilikPermohonan = conService.findMohonHakmilikByIdH(idPermohonan, permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
            }
        }

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

            if (permohonan.getPermohonanSebelum() != null) {
                tajuk = "PERMOHONAN RAYUAN DARIPADA " + namaPemohon + " UNTUK MEMILIKI " + jenisHartanah + " DI ATAS HAKMILIK " + namaHakmilik + ", MELAKA DI BAWAH SEKSYEN 433B (1) KANUN TANAH NEGARA 1965";
            } else {
                tajuk = "PERMOHONAN DARIPADA " + namaPemohon + " UNTUK MEMILIKI " + jenisHartanah + " DI ATAS HAKMILIK " + namaHakmilik + ", MELAKA DI BAWAH SEKSYEN 433B (1) KANUN TANAH NEGARA 1965";

            }
            tajuk = tajuk.toUpperCase();
        }
        LOG.info("_____________________________________________________________________________________________");
        //PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");
        permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonanAndKodDokumen(idPermohonan, "RMN");

        //LOG.info("id kertas::::" + idKertas);
        if (permohonanKertas != null) {

            tajuk = permohonanKertas.getTajuk();

            if (permohonanKertas.getTarikhSidang() != null) {
                String tarikhSidang = dateFormat.format(permohonanKertas.getTarikhSidang());
                tarikhmesyuarat = tarikhSidang.substring(0, 10);
                jam = tarikhSidang.substring(11, 13);
                minit = tarikhSidang.substring(14, 16);
                String ampm = tarikhSidang.substring(20, 22);
                if (ampm.equalsIgnoreCase("AM")) {
                    pagiPetang = "PAGI";
                } else if (ampm.equalsIgnoreCase("PM")) {
                    pagiPetang = "PETANG";
                }
            }

            tempat = permohonanKertas.getTempatSidang();
            LOG.info("permohonanKertas.getIdKertas() : " + permohonanKertas.getIdKertas());
            List<PermohonanKertasKandungan> listKandungan = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas());
            LOG.info("listKandungan.size() : " + listKandungan.size());
            for (PermohonanKertasKandungan kertas : listKandungan) {
                if (kertas.getBil() == 1) {
                    tajuk = kertas.getKandungan();
                }
                if (kertas.getBil() == 2) {
                    keputusanDulu = kertas.getKandungan();
                }
//                if (kertas.getBil() == 3) {
//                    perakuanPtg = kertas.getKandungan();
//                }
                if (kertas.getBil() == 3) {
                    ulasanYb = kertas.getKandungan();
                }
                if (kertas.getBil() == 4) {
                    perakuanPtd = kertas.getKandungan();
                }
                if (kertas.getBil() == 5) {
                    perakuanPtg = kertas.getKandungan();
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

//            if (hakmilikPermohonan != null) {
//                hakmilikPermohonanTemp.setKeteranganInfra(hakmilikPermohonan.getKeteranganInfra()); //ALAMAT
//                hakmilikPermohonanTemp.setKeteranganKadarDaftar(hakmilikPermohonan.getKeteranganKadarDaftar());//NILAI HARTANAH
//                hakmilikPermohonanTemp.setLokasi(hakmilikPermohonan.getLokasi());//LOKASI
//                hakmilikPermohonanTemp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
//                KodUOM kodUOM = new KodUOM();
//                kodUOM.setKod(unitLuas);
//                hakmilikPermohonanTemp.setKodUnitLuas(kodUOM);
//            }

            conService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);

            permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");

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
            kodDokumen.setKod("RMN");
            permohonanKertas.setKodDokumen(kodDokumen);
            permohonanKertas.setTajuk("RINGKAS MMKN");

            conService.simpanPermohonanKertas(permohonanKertas);


            ArrayList listUlasan = new ArrayList();

            if (StringUtils.isBlank(tajuk)) {
                tajuk = " ";
            }
//            if (StringUtils.isBlank(alamat)) {
//                alamat = " ";
//            }
//            if (StringUtils.isBlank(lokasi)) {
//                lokasi = " ";
//            }
//            if (StringUtils.isBlank(harga)) {
//                harga = " ";
//            }
            if (StringUtils.isBlank(keputusanDulu)) {
                keputusanDulu = " ";
            }
            if (StringUtils.isBlank(ulasanYb)) {
                ulasanYb = " ";
            }
            if (StringUtils.isBlank(perakuanPtd)) {
                perakuanPtd = " ";
            }

            listUlasan.add(tajuk);
            //listUlasan.add(alamat);
            //listUlasan.add(lokasi);
            //listUlasan.add(harga);
            listUlasan.add(keputusanDulu);
            listUlasan.add(ulasanYb);
            listUlasan.add(perakuanPtd);

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {
                LOG.info("------------permohonanKertas----------------" + permohonanKertas);
                LOG.info("------------permohonanKertas.getse----------------" + permohonanKertas.getSenaraiKandungan());
                LOG.info("------------not empoty----------------");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tajuk);
//                        } else if (kertasKdgn.getBil() == 2) {
//                            kertasKdgn.setKandungan(alamat);
//                        } else if (kertasKdgn.getBil() == 3) {
//                            kertasKdgn.setKandungan(lokasi);
//                        } else if (kertasKdgn.getBil() == 4) {
//                            kertasKdgn.setKandungan(harga);
                    } else if (kertasKdgn.getBil() == 2) {
                        kertasKdgn.setKandungan(keputusanDulu);
                    } else if (kertasKdgn.getBil() == 3) {
                        kertasKdgn.setKandungan(ulasanYb);
                    } else if (kertasKdgn.getBil() == 4) {
                        kertasKdgn.setKandungan(perakuanPtd);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }

            } else {
                LOG.info("-------saving-----empoty----------------");
                for (int i = 0; i < listUlasan.size(); i++) {
                    LOG.info("------------empoty----------------");
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
            LOG.info("------------rehydrate----------------");
            rehydrate();
            LOG.info("------------save----------------");
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/hasil/kertas_ringkasan_mmkn.jsp").addParameter("tab", "true");

    }

    public Resolution simpanPTG() {

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

//            if (hakmilikPermohonan != null) {
//                hakmilikPermohonanTemp.setKeteranganInfra(hakmilikPermohonan.getKeteranganInfra()); //ALAMAT
//                hakmilikPermohonanTemp.setKeteranganKadarDaftar(hakmilikPermohonan.getKeteranganKadarDaftar());//NILAI HARTANAH
//                hakmilikPermohonanTemp.setLokasi(hakmilikPermohonan.getLokasi());//LOKASI
//                hakmilikPermohonanTemp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
//                KodUOM kodUOM = new KodUOM();
//                kodUOM.setKod(unitLuas);
//                hakmilikPermohonanTemp.setKodUnitLuas(kodUOM);
//            }

            conService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);

            permohonanKertas = conService.findMohonKertasByTajuk(permohonan.getIdPermohonan(), "RINGKAS MMKN");

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
            kodDokumen.setKod("RMN");
            permohonanKertas.setKodDokumen(kodDokumen);
            permohonanKertas.setTajuk("RINGKAS MMKN");

            conService.simpanPermohonanKertas(permohonanKertas);


            ArrayList listUlasan = new ArrayList();

            if (StringUtils.isBlank(tajuk)) {
                tajuk = " ";
            }
//            if (StringUtils.isBlank(alamat)) {
//                alamat = " ";
//            }
//            if (StringUtils.isBlank(lokasi)) {
//                lokasi = " ";
//            }
//            if (StringUtils.isBlank(harga)) {
//                harga = " ";
//            }
            if (StringUtils.isBlank(keputusanDulu)) {
                keputusanDulu = " ";
            }
            if (StringUtils.isBlank(ulasanYb)) {
                ulasanYb = " ";
            }
            if (StringUtils.isBlank(perakuanPtd)) {
                perakuanPtd = " ";
            }
            if (StringUtils.isBlank(perakuanPtg)) {
                perakuanPtg = " ";
            }

            listUlasan.add(tajuk);
            //listUlasan.add(alamat);
            //listUlasan.add(lokasi);
            //listUlasan.add(harga);
            listUlasan.add(keputusanDulu);
            listUlasan.add(ulasanYb);
            listUlasan.add(perakuanPtd);
            listUlasan.add(perakuanPtg);

            if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {
                LOG.info("------------permohonanKertas----------------" + permohonanKertas);
                LOG.info("------------permohonanKertas.getse----------------" + permohonanKertas.getSenaraiKandungan());
                LOG.info("------------not empoty----------------");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);
                    if (kertasKdgn.getBil() == 1) {
                        kertasKdgn.setKandungan(tajuk);
//                        } else if (kertasKdgn.getBil() == 2) {
//                            kertasKdgn.setKandungan(alamat);
//                        } else if (kertasKdgn.getBil() == 3) {
//                            kertasKdgn.setKandungan(lokasi);
//                        } else if (kertasKdgn.getBil() == 4) {
//                            kertasKdgn.setKandungan(harga);
                    } else if (kertasKdgn.getBil() == 2) {
                        kertasKdgn.setKandungan(keputusanDulu);
                    } else if (kertasKdgn.getBil() == 3) {
                        kertasKdgn.setKandungan(ulasanYb);
                    } else if (kertasKdgn.getBil() == 4) {
                        kertasKdgn.setKandungan(perakuanPtd);
                    } else if (kertasKdgn.getBil() == 5) {
                        kertasKdgn.setKandungan(perakuanPtg);
                    }
                    kertasKdgn.setInfoAudit(infoAudit);
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }

            } else {
                LOG.info("-------saving-----empoty----------------");
                for (int i = 0; i < listUlasan.size(); i++) {
                    LOG.info("------------empoty----------------");
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
            LOG.info("------------rehydrate----------------");
            rehydrate();
            LOG.info("------------save----------------");
            addSimpleMessage("Maklumat telah berjaya disimpan.");

}

        getContext().getRequest().setAttribute("tajuk", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/hasil/kertas_ringkasan_mmkn.jsp").addParameter("tab", "true");


    }

    public Resolution selectHakmilik() {
        LOG.info("::select hakmilik::");
        return showForm();

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

    public String getUlasanYb() {
        return ulasanYb;
    }

    public void setUlasanYb(String ulasanYb) {
        this.ulasanYb = ulasanYb;
    }

    public String getPerakuanPtd() {
        return perakuanPtd;
    }

    public void setPerakuanPtd(String perakuanPtd) {
        this.perakuanPtd = perakuanPtd;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
