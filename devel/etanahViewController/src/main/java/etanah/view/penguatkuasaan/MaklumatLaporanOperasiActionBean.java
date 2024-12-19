/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodPeranan;
import etanah.model.Pengguna;
import etanah.model.PermohonanLaporanUlasan;
import java.text.ParseException;
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodPerananOperasi;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PenjaminBarangRampasan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sourceforge.stripes.action.StreamingResolution;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_laporan_operasi")
public class MaklumatLaporanOperasiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatLaporanOperasiActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    OperasiPenguatkuasaanPasukanDAO operasiPenguatkuasaanPasukanDAO;
    @Inject
    KodPerananOperasiDAO kodPerananOperasiDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    private AduanLokasi aduanLokasi;
    private List<OperasiPenguatkuasaan> senaraiOperasiPenguatkuasaan;
    private Permohonan permohonan;
    private String catatan;
    private String lokasi;
    private String tarikhOperasi;
    private String tarikhRujukan;
    private String idPermohonan;
    private Pengguna pguna;
    private KodCawangan cawangan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String jenisTindakan;
    private int jumlahTangkapan;
    private String jam;
    private String minit;
    private String ampm;
    private String platKenderaan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukan;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukanWithoutKetua;
    private String recordCount;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String nama;
    private String noKp;
    private String peranan;
    private BigDecimal nilaiKecurian;
    private String kadKuasa;
    private String namaKetua;
    private String idKetua;
    private String noPengenalanKetua;
    private String kadKuasaKetua;
    private String namaKetuaDisabled;
    private String tarikhPenahanan;
    private String hour;
    private String minute;
    private String ampm1;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasan;
    private String kodNegeri;
    private String tempatKerja;
    private String tempatKerjaKetua;
    private String idOperasi;
    private Pengguna peng;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private List<Pengguna> senaraiPengguna;
    private String namaCarian;
    private String noPengenalanCarian;
    private String jawatanCarian;
    private String idPenggunaCarian;
    private String pilihPengguna;
    private FolderDokumen folderDokumen;
    private String kadKuasaCarian;
    private List<AduanOrangKenaSyak> senaraiOksIp;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
//            getContext().getRequest().setAttribute("viewIP", Boolean.TRUE); //will display after create IP = investigation paper
        return new JSP("penguatkuasaan/maklumat_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_operasi.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiPasukan = new ArrayList<OperasiPenguatkuasaanPasukan>();
        kodNegeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            try {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    //for Melaka, only pass idPermohonan
                    senaraiOperasiPenguatkuasaan = enforceService.findListLaporanOperasi(idPermohonan);
                    logger.info("::::: size senaraiOperasiPenguatkuasaan : " + senaraiOperasiPenguatkuasaan.size());

                    ArrayList kumpulanBpel = new ArrayList<String>();
                    if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        kumpulanBpel.add("pptptgkuasa"); // PPT
                        kumpulanBpel.add("pptkptgkuasa"); //PPTK
                        kumpulanBpel.add("ppttptgkuasa"); //PPTT
                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
                        kumpulanBpel.add("PPTanah"); // PPT - pptptdkuasa
                        kumpulanBpel.add("pptkanan"); //PPTK - pptkptdkuasa
                        kumpulanBpel.add("ppttptdkuasa"); //PPTT
                    }

                    senaraiPengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
                    if (senaraiOperasiPenguatkuasaan.size() == 0) {

                        senaraiKandungan = new ArrayList<KandunganFolder>();
                        if (permohonan.getPermohonanSebelum() != null) {
                            senaraiOperasiPenguatkuasaan = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            folderDokumen = permohonan.getPermohonanSebelum().getFolderDokumen();
                            if (folderDokumen != null) {

                                if (folderDokumen.getSenaraiKandungan() != null) {
                                    for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                                        if (kf == null || kf.getDokumen() == null) {
                                            continue;
                                        }
                                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                                        kf.setDokumen(d);
                                        senaraiKandungan.add(kf);
                                    }
                                }
                            }
                        }
                        if (senaraiOperasiPenguatkuasaan.size() == 0) {
                            if (permohonan.getPermohonanSebelum() != null) {
                                Permohonan p = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                                String idPermohonanAsal = p.getIdPermohonan();
                                logger.info("p :::" + p.getIdPermohonan());
                                if (p != null) {
                                    if (p.getPermohonanSebelum() != null) {
                                        Permohonan p1 = permohonanDAO.findById(p.getPermohonanSebelum().getIdPermohonan());
                                        if (p1 != null) {
                                            logger.info("p1 :::" + p1.getIdPermohonan());
                                            idPermohonanAsal = p1.getIdPermohonan();
                                        }
                                    }
                                }
                                logger.info("::: idPermohonanAsal : " + idPermohonanAsal);
                                senaraiOperasiPenguatkuasaan = enforceService.findListLaporanOperasi(idPermohonanAsal);
                                logger.info("size senaraiOperasiPenguatkuasaan : " + senaraiOperasiPenguatkuasaan.size());
                            }
                        }

                        logger.info("----size senarai kandungan : ---- " + senaraiKandungan.size());

                        senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                        logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                        if (senaraiOksIp.size() != 0) {
                            Long idOIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                            logger.info("id operasi : " + idOperasi);
                            senaraiOperasiPenguatkuasaan = enforcementService.findListLaporanOperasi(idOIP);
                        }

                    }
                } else {
                    //for N9, pass idPermohonan & kategoriTindakan
                    senaraiOperasiPenguatkuasaan = enforceService.findListLaporanOperasi(idPermohonan, "S");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Resolution popupTambahLaporanOperasi() {
        return new JSP("penguatkuasaan/popup_tambah_laporan_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution popupEditLaporanOperasi() {
        logger.info("--------------popupEditLaporanOperasi--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");
        operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        if (operasiPenguatkuasaan != null) {
            senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
            senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());

            if (senaraiPasukanWithoutKetua != null) {
                recordCount = String.valueOf(senaraiPasukanWithoutKetua.size());
                System.out.println("record count : " + recordCount);
            }

            try {
                if (senaraiPasukan.size() != 0) {
                    for (int i = 0; i < senaraiPasukan.size(); i++) {
                        if (senaraiPasukan.get(i).getKodPerananOperasi().getKod().equalsIgnoreCase("K")) {
                            namaKetua = senaraiPasukan.get(i).getNama();
                            namaKetuaDisabled = senaraiPasukan.get(i).getNama();
                            kadKuasaKetua = senaraiPasukan.get(i).getKadKuasa();
                            noPengenalanKetua = senaraiPasukan.get(i).getNoKp();
                            tempatKerjaKetua = senaraiPasukan.get(i).getTempatKerja();
                            idKetua = Long.toString(senaraiPasukan.get(i).getIdOperasiPenguatkuasaanPasukan());
                        }
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }

            catatan = operasiPenguatkuasaan.getCatatan();
            lokasi = operasiPenguatkuasaan.getLokasi();
            jenisTindakan = operasiPenguatkuasaan.getJenisTindakan();
            jumlahTangkapan = operasiPenguatkuasaan.getJumlahTangkapan();
            nilaiKecurian = operasiPenguatkuasaan.getNilaiKecurian();
            platKenderaan = operasiPenguatkuasaan.getKenderaan();

            if (operasiPenguatkuasaan.getTarikhOperasi() != null) {
                tarikhOperasi = sdf.format(operasiPenguatkuasaan.getTarikhOperasi());
                tarikhOperasi = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(0, 10);
                jam = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(11, 13);
                if (jam.startsWith("0")) {
                    jam = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(12, 13);
                }
                minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(14, 16);
                System.out.println("minit trh_operasi : " + minit);
                ampm = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(17, 19);
            }

            if (operasiPenguatkuasaan.getTarikhPenahanan() != null) {
                tarikhPenahanan = sdf0.format(operasiPenguatkuasaan.getTarikhPenahanan());
                tarikhPenahanan = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(0, 10);
                hour = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(11, 13);
                if (hour.startsWith("0")) {
                    hour = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(12, 13);
                }
                minute = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(14, 16);
                System.out.println("minit trh_tahan : " + minute);
                ampm1 = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(17, 19);
            }
        }
        return new JSP("penguatkuasaan/popup_tambah_laporan_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution popupViewLaporanOperasi() {
        logger.info("--------------popupViewLaporanOperasi--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");
        operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        if (operasiPenguatkuasaan != null) {
            senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
            senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());

            try {
                if (senaraiPasukan.size() != 0) {
                    for (int i = 0; i < senaraiPasukan.size(); i++) {
                        if (senaraiPasukan.get(i).getKodPerananOperasi().getKod().equalsIgnoreCase("K")) {
                            namaKetua = senaraiPasukan.get(i).getNama();
                            namaKetuaDisabled = senaraiPasukan.get(i).getNama();
                            kadKuasaKetua = senaraiPasukan.get(i).getKadKuasa();
                            noPengenalanKetua = senaraiPasukan.get(i).getNoKp();
                            tempatKerjaKetua = senaraiPasukan.get(i).getTempatKerja();
                            idKetua = Long.toString(senaraiPasukan.get(i).getIdOperasiPenguatkuasaanPasukan());
                        }
                    }
                }
            } catch (Exception e) {
                e.getCause();
            }
        }
        return new JSP("penguatkuasaan/popup_view_LO.jsp").addParameter("popup", "true");
    }

    public Resolution deletePenguatkuasaanPasukan() {
        String idOperasiPenguatkuasaanPasukan;
        idOperasiPenguatkuasaanPasukan = getContext().getRequest().getParameter("idOperasiPenguatkuasaanPasukan");
        System.out.println("id masa delete : " + idOperasiPenguatkuasaanPasukan);
        try {
            if (idOperasiPenguatkuasaanPasukan != null) {
                operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idOperasiPenguatkuasaanPasukan));
            }
            enforceService.deleteOperasiPasukan(operasiPenguatkuasaanPasukan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/popup_tambah_laporan_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution deleteOperasiPenguatkuasaan() {
        logger.info("--------------deleteOperasiPenguatkuasaan--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");
        try {
            if (idOperasi != null) {
                operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));

                //delete child first : operasiPenguatkuasaanPasukan (in list)
                senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
                for (int i = 0; i < senaraiPasukan.size(); i++) {
                    operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(senaraiPasukan.get(i).getIdOperasiPenguatkuasaanPasukan());
                    enforceService.deleteOperasiPasukan(operasiPenguatkuasaanPasukan);
                }

                //delete child first : operasiAgensi (in list)
                List<OperasiAgensi> operasiAgensiList = operasiPenguatkuasaan.getSenaraiAgensi();
                for (int i = 0; i < operasiAgensiList.size(); i++) {
                    OperasiAgensi oa = operasiAgensiDAO.findById(operasiAgensiList.get(i).getIdOperasiAgensi());
                    enforceService.deleteOperasiAgensi(oa);
                }

                //delete child first : barangRampasan (in list)
                List<BarangRampasan> barangRampasanList = operasiPenguatkuasaan.getSenaraiBarangRampasan();
                for (int i = 0; i < barangRampasanList.size(); i++) {
                    BarangRampasan barang = barangRampasanDAO.findById(barangRampasanList.get(i).getIdBarang());

                    if (barang != null) {
                        PenjaminBarangRampasan penjaminBarangRampasan = enforceService.findPenjaminBarangRampasanByIdBarang(barang.getIdBarang());
                        if (penjaminBarangRampasan != null) {
                            enforceService.deletePenjaminBarangRampasan(penjaminBarangRampasan);
                        }
                    }

                    enforceService.deleteBarangRampasan(barang);
                }

                //Then, delete parent : operasiPenguatkuasaan
                enforceService.deleteOperasiPenguatkuasaan(operasiPenguatkuasaan);


                //Delete image from dokumen
                Session s = sessionProvider.get();
                Transaction tx = s.beginTransaction();
                tx.begin();
                try {
                    if (permohonan.getFolderDokumen() != null) {
                        senaraiKandungan = enforceService.getListDokumen(permohonan.getFolderDokumen().getFolderId());
                        if (senaraiKandungan.size() != 0) {
                            for (KandunganFolder kf : senaraiKandungan) {
                                Dokumen dokumen = kf.getDokumen();
                                if (dokumen.getKodDokumen() != null) {
                                    if (dokumen.getKodDokumen().getKod().equalsIgnoreCase("LO")) {
                                        if (dokumen.getPerihal().equalsIgnoreCase(idOperasi)) {
                                            logger.info("------------id dokumen----------------- : " + dokumen.getIdDokumen());
                                            dokumenDAO.delete(dokumen);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    tx.commit();

                } catch (Exception ex) {
                    tx.rollback();
                    Throwable t = ex;
                    // getting the root cause
                    while (t.getCause() != null) {
                        t = t.getCause();
                    }
                    ex.printStackTrace();
                    addSimpleError(t.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatLaporanOperasiActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {
        logger.info("--------------simpan--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");

        if (idOperasi != null && StringUtils.isNotBlank(idOperasi)) {
            logger.info("id operasi: " + idOperasi);
            operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        }

        InfoAudit ia = new InfoAudit();

        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        tarikhOperasi = tarikhOperasi + " " + jam + ":" + minit + " " + ampm;

        if (StringUtils.isNotBlank(tarikhPenahanan) && StringUtils.isNotBlank(hour) && StringUtils.isNotBlank(minute) && StringUtils.isNotBlank(ampm1)) {
            tarikhPenahanan = tarikhPenahanan + " " + hour + ":" + minute + " " + ampm1;
        }
        System.out.println("tarikhPenahanan : " + tarikhPenahanan);
        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setInfoAudit(ia);
        operasiPenguatkuasaan.setJenisTindakan(jenisTindakan);
        operasiPenguatkuasaan.setCatatan(catatan);
        operasiPenguatkuasaan.setLokasi(lokasi);
        operasiPenguatkuasaan.setJumlahTangkapan(jumlahTangkapan);
        operasiPenguatkuasaan.setNilaiKecurian(nilaiKecurian);
        operasiPenguatkuasaan.setCawangan(peng.getKodCawangan());
        operasiPenguatkuasaan.setKenderaan(platKenderaan);
        operasiPenguatkuasaan.setJenisTangkapan(kodUOMDAO.findById("OG"));
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //for N9, need to save into column kategoriTindakan
            operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
        }

        try {
            if (StringUtils.isNotEmpty(tarikhOperasi)) {
                operasiPenguatkuasaan.setTarikhOperasi(sdf1.parse(tarikhOperasi));
            }
            if (StringUtils.isNotEmpty(tarikhPenahanan)) {
                operasiPenguatkuasaan.setTarikhPenahanan(sdf2.parse(tarikhPenahanan));
            } else {
                System.out.println("tarikh penahanan null");
                operasiPenguatkuasaan.setTarikhPenahanan(null);
            }
        } catch (Exception ex) {
            ex.getCause();
            logger.error(ex);
        }

        enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);

        try {

            try {
                //for saving ketua to table : kkuasa_pasukan
                noPengenalanKetua = getContext().getRequest().getParameter("noPengenalanKetua");
                idKetua = getContext().getRequest().getParameter("idKetua");
                namaKetua = getContext().getRequest().getParameter("namaKetua");
                kadKuasaKetua = getContext().getRequest().getParameter("kadKuasaKetua");
                tempatKerjaKetua = getContext().getRequest().getParameter("tempatKerjaKetua");
                if (noPengenalanKetua != null && StringUtils.isNotBlank(noPengenalanKetua)) {
                    logger.info("info for ketua is exist");
                    if (!idKetua.isEmpty()) {
                        operasiPenguatkuasaanPasukan = enforcementService.findKetua(Long.parseLong(idKetua));
                        ia = operasiPenguatkuasaanPasukan.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }

                    KodPerananOperasi kodKetua = kodPerananOperasiDAO.findById("K"); //kod : for ketua(K)
                    operasiPenguatkuasaanPasukan.setCawangan(peng.getKodCawangan());
                    operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                    operasiPenguatkuasaanPasukan.setNama(namaKetua);
                    operasiPenguatkuasaanPasukan.setNoKp(noPengenalanKetua);
                    operasiPenguatkuasaanPasukan.setKodPerananOperasi(kodKetua);
                    operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasaKetua);
                    operasiPenguatkuasaanPasukan.setTempatKerja(tempatKerjaKetua);
                    operasiPenguatkuasaanPasukan.setInfoAudit(ia);
                    enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);

                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }
            senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());
            System.out.println("senaraiPasukan tanpa ketua size : " + senaraiPasukanWithoutKetua.size());
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            boolean exist = false;
            for (int i = 0; i < rowCount; i++) {
                if (senaraiPasukanWithoutKetua.size() != 0 && i < senaraiPasukanWithoutKetua.size()) {
                    Long id = senaraiPasukanWithoutKetua.get(i).getIdOperasiPenguatkuasaanPasukan();
                    if (id != null) {
                        operasiPenguatkuasaanPasukan = enforceService.findSenaraiPasukan(id);
                        exist = true;
                    } else {
                        System.out.println("create new pasukan if list not empty");
                        operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                    }
                } else {
                    System.out.println("create new pasukan");
                    operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                }

                System.out.println("----------------- exist : " + exist);

                String namaTemp = getContext().getRequest().getParameter("namaTemp" + i);
                nama = getContext().getRequest().getParameter("nama" + i);//id pengguna
                noKp = getContext().getRequest().getParameter("noKp" + i);
                peranan = getContext().getRequest().getParameter("peranan" + i);
                kadKuasa = getContext().getRequest().getParameter("kadKuasa" + i);
                tempatKerja = getContext().getRequest().getParameter("tempatKerja" + i);

                KodPerananOperasi kp = kodPerananOperasiDAO.findById(peranan);

                if (exist == true) {
                    logger.info("--------------user exist--------------");
                    operasiPenguatkuasaanPasukan.setNama(namaTemp);
                } else {
                    logger.info("--------------new user--------------");
                    Pengguna pg = penggunaDAO.findById(nama);
                    System.out.println("pg : " + pg.getNama());
                    operasiPenguatkuasaanPasukan.setNama(pg.getNama());
                }

                operasiPenguatkuasaanPasukan.setCawangan(peng.getKodCawangan());
                operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                operasiPenguatkuasaanPasukan.setNoKp(noKp);
                operasiPenguatkuasaanPasukan.setKodPerananOperasi(kp);
                operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasa);
                operasiPenguatkuasaanPasukan.setTempatKerja(tempatKerja);
                operasiPenguatkuasaanPasukan.setInfoAudit(ia);
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPasukan() throws ParseException {
        logger.info("--------------simpanPasukan--------------");
        idOperasi = getContext().getRequest().getParameter("idOperasi");

        if (idOperasi != null && StringUtils.isNotBlank(idOperasi)) {
            logger.info("id operasi: " + idOperasi);
            operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOperasi));
        }

        InfoAudit ia = new InfoAudit();

        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
        } else {
            ia = operasiPenguatkuasaan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }

        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setInfoAudit(ia);
        operasiPenguatkuasaan.setCawangan(peng.getKodCawangan());
        operasiPenguatkuasaan.setJenisTangkapan(kodUOMDAO.findById("OG"));
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //for N9, need to save into column kategoriTindakan
            operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
        }

        enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);

        try {

            senaraiPasukanWithoutKetua = enforcementService.getSenaraiPasukanWithoutKetua(operasiPenguatkuasaan.getIdOperasi());
            System.out.println("senaraiPasukan tanpa ketua size : " + senaraiPasukanWithoutKetua.size());
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            boolean exist = false;
            for (int i = 0; i < rowCount; i++) {
                if (senaraiPasukanWithoutKetua.size() != 0 && i < senaraiPasukanWithoutKetua.size()) {
                    Long id = senaraiPasukanWithoutKetua.get(i).getIdOperasiPenguatkuasaanPasukan();
                    if (id != null) {
                        operasiPenguatkuasaanPasukan = enforceService.findSenaraiPasukan(id);
                        exist = true;
                    } else {
                        System.out.println("create new pasukan if list not empty");
                        operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                    }
                } else {
                    System.out.println("create new pasukan");
                    operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                }

                System.out.println("----------------- exist : " + exist);

                String namaTemp = getContext().getRequest().getParameter("namaTemp" + i);
                nama = getContext().getRequest().getParameter("nama" + i);//id pengguna
                noKp = getContext().getRequest().getParameter("noKp" + i);
                peranan = getContext().getRequest().getParameter("peranan" + i);
                kadKuasa = getContext().getRequest().getParameter("kadKuasa" + i);
                tempatKerja = getContext().getRequest().getParameter("tempatKerja" + i);

                KodPerananOperasi kp = kodPerananOperasiDAO.findById(peranan);

                if (exist == true) {
                    logger.info("--------------user exist--------------");
                    operasiPenguatkuasaanPasukan.setNama(namaTemp);
                } else {
                    logger.info("--------------new user--------------");
                    Pengguna pg = penggunaDAO.findById(nama);
                    System.out.println("pg : " + pg.getNama());
                    operasiPenguatkuasaanPasukan.setNama(pg.getNama());
                }

                operasiPenguatkuasaanPasukan.setCawangan(peng.getKodCawangan());
                operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                operasiPenguatkuasaanPasukan.setNoKp(noKp);
                operasiPenguatkuasaanPasukan.setKodPerananOperasi(kp);
                operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasa);
                operasiPenguatkuasaanPasukan.setTempatKerja(tempatKerja);
                operasiPenguatkuasaanPasukan.setInfoAudit(ia);
                enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tambah_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSelected() {
        System.out.println("deleteSelected");
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));

        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            tx.commit();

        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(MaklumatLaporanOperasiActionBean.class, "locate");
    }

    public Resolution findNoPengenalan() {
        logger.info("--------findNoPengenalan--------");
        String result = "";
        String noPengenalan = getContext().getRequest().getParameter("noPengenalan");

        List<Pengguna> pengguna = enforcementService.findNoKadPengenalanKetua(noPengenalan.trim());
        System.out.println("pengguna : " + pengguna.size());
        if (pengguna.size() == 1) {
            for (int i = 0; i < pengguna.size(); i++) {
                result = pengguna.get(i).getNama();
            }
        } else if (pengguna.size() == 0) {
            result = "NotExist";
        } else if (pengguna.size() > 1) {
            result = "Duplicate";
        }
        return new StreamingResolution("text/plain", result);
    }

    public Resolution findPengguna() {
        String id = getContext().getRequest().getParameter("id");
        Pengguna pengguna = penggunaDAO.findById(id);

        if (pengguna != null) {
            namaCarian = pengguna.getNama();
            noPengenalanCarian = pengguna.getNoPengenalan();
            jawatanCarian = pengguna.getJawatan();
            idPenggunaCarian = pengguna.getIdPengguna();
            kadKuasaCarian = pengguna.getKadKuasa();

            System.out.println("namaCarian : " + namaCarian);
            System.out.println("noPengenalanCarian : " + noPengenalanCarian);
            System.out.println("jawatanCarian : " + jawatanCarian);
            System.out.println("idPenggunaCarian : " + idPenggunaCarian);
            System.out.println("kadKuasaCarian : " + kadKuasaCarian);
        }
        return new JSP("penguatkuasaan/popup_tambah_laporan_operasi.jsp").addParameter("popup", "true");
    }

    public Resolution simpanUlasan() {
        logger.info("-------------simpanUlasan------------------");

        try {
            InfoAudit infoAudit = new InfoAudit();

            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            String kodPeranan = pguna.getPerananUtama().getKod();


            permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "LO");//LO = laporan operasi

            if (permohonanLaporanUlasan != null) {
                infoAudit = permohonanLaporanUlasan.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            } else {
                permohonanLaporanUlasan = new PermohonanLaporanUlasan();
                infoAudit.setTarikhMasuk(new java.util.Date());
                infoAudit.setDimasukOleh(pguna);
            }

            permohonanLaporanUlasan.setUlasan(ulasan);
            permohonanLaporanUlasan.setJenisUlasan("LO");
            permohonanLaporanUlasan.setCawangan(pguna.getKodCawangan());
            permohonanLaporanUlasan.setPermohonan(permohonan);
            permohonanLaporanUlasan.setInfoAudit(infoAudit);
            KodPeranan kp = kodPerananDAO.findById(kodPeranan);
            permohonanLaporanUlasan.setKodPeranan(kp);

            enforceService.simpanRingkasanKes(permohonanLaporanUlasan);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } catch (Exception e) {
            logger.error(e);
            addSimpleError("Kemasukan Data Gagal. Sila Hubungi Pentadbir Sistem.");
            getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
            return new JSP("penguatkuasaan/laporan_operasi_polis_view.jsp").addParameter("tab", "true");
        }
        logger.info("-------------simpan finish------------------");
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(MaklumatLaporanOperasiActionBean.class, "locate");
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoKp() {
        return noKp;
    }

    public void setNoKp(String noKp) {
        this.noKp = noKp;
    }

    public String getPeranan() {
        return peranan;
    }

    public void setPeranan(String peranan) {
        this.peranan = peranan;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan() {
        return senaraiPasukan;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public void setSenaraiPasukan(List<OperasiPenguatkuasaanPasukan> senaraiPasukan) {
        this.senaraiPasukan = senaraiPasukan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getTarikhOperasi() {
        return tarikhOperasi;
    }

    public void setTarikhOperasi(String tarikhOperasi) {
        this.tarikhOperasi = tarikhOperasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getJenisTindakan() {
        return jenisTindakan;
    }

    public void setJenisTindakan(String jenisTindakan) {
        this.jenisTindakan = jenisTindakan;
    }

    public int getJumlahTangkapan() {
        return jumlahTangkapan;
    }

    public void setJumlahTangkapan(int jumlahTangkapan) {
        this.jumlahTangkapan = jumlahTangkapan;
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

    public String getPlatKenderaan() {
        return platKenderaan;
    }

    public void setPlatKenderaan(String platKenderaan) {
        this.platKenderaan = platKenderaan;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public BigDecimal getNilaiKecurian() {
        return nilaiKecurian;
    }

    public void setNilaiKecurian(BigDecimal nilaiKecurian) {
        this.nilaiKecurian = nilaiKecurian;
    }

    public String getKadKuasa() {
        return kadKuasa;
    }

    public void setKadKuasa(String kadKuasa) {
        this.kadKuasa = kadKuasa;
    }

    public String getIdKetua() {
        return idKetua;
    }

    public void setIdKetua(String idKetua) {
        this.idKetua = idKetua;
    }

    public String getKadKuasaKetua() {
        return kadKuasaKetua;
    }

    public void setKadKuasaKetua(String kadKuasaKetua) {
        this.kadKuasaKetua = kadKuasaKetua;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public String getNoPengenalanKetua() {
        return noPengenalanKetua;
    }

    public void setNoPengenalanKetua(String noPengenalanKetua) {
        this.noPengenalanKetua = noPengenalanKetua;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanWithoutKetua() {
        return senaraiPasukanWithoutKetua;
    }

    public void setSenaraiPasukanWithoutKetua(List<OperasiPenguatkuasaanPasukan> senaraiPasukanWithoutKetua) {
        this.senaraiPasukanWithoutKetua = senaraiPasukanWithoutKetua;
    }

    public String getNamaKetuaDisabled() {
        return namaKetuaDisabled;
    }

    public void setNamaKetuaDisabled(String namaKetuaDisabled) {
        this.namaKetuaDisabled = namaKetuaDisabled;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public String getAmpm1() {
        return ampm1;
    }

    public void setAmpm1(String ampm1) {
        this.ampm1 = ampm1;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTarikhPenahanan() {
        return tarikhPenahanan;
    }

    public void setTarikhPenahanan(String tarikhPenahanan) {
        this.tarikhPenahanan = tarikhPenahanan;
    }

    public PermohonanLaporanUlasan getPermohonanLaporanUlasan() {
        return permohonanLaporanUlasan;
    }

    public void setPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        this.permohonanLaporanUlasan = permohonanLaporanUlasan;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getTempatKerja() {
        return tempatKerja;
    }

    public void setTempatKerja(String tempatKerja) {
        this.tempatKerja = tempatKerja;
    }

    public String getTempatKerjaKetua() {
        return tempatKerjaKetua;
    }

    public void setTempatKerjaKetua(String tempatKerjaKetua) {
        this.tempatKerjaKetua = tempatKerjaKetua;
    }

    public List<OperasiPenguatkuasaan> getSenaraiOperasiPenguatkuasaan() {
        return senaraiOperasiPenguatkuasaan;
    }

    public void setSenaraiOperasiPenguatkuasaan(List<OperasiPenguatkuasaan> senaraiOperasiPenguatkuasaan) {
        this.senaraiOperasiPenguatkuasaan = senaraiOperasiPenguatkuasaan;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getIdPenggunaCarian() {
        return idPenggunaCarian;
    }

    public void setIdPenggunaCarian(String idPenggunaCarian) {
        this.idPenggunaCarian = idPenggunaCarian;
    }

    public String getJawatanCarian() {
        return jawatanCarian;
    }

    public void setJawatanCarian(String jawatanCarian) {
        this.jawatanCarian = jawatanCarian;
    }

    public String getNamaCarian() {
        return namaCarian;
    }

    public void setNamaCarian(String namaCarian) {
        this.namaCarian = namaCarian;
    }

    public String getNoPengenalanCarian() {
        return noPengenalanCarian;
    }

    public void setNoPengenalanCarian(String noPengenalanCarian) {
        this.noPengenalanCarian = noPengenalanCarian;
    }

    public String getPilihPengguna() {
        return pilihPengguna;
    }

    public void setPilihPengguna(String pilihPengguna) {
        this.pilihPengguna = pilihPengguna;
    }

    public String getKadKuasaCarian() {
        return kadKuasaCarian;
    }

    public void setKadKuasaCarian(String kadKuasaCarian) {
        this.kadKuasaCarian = kadKuasaCarian;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }
}
