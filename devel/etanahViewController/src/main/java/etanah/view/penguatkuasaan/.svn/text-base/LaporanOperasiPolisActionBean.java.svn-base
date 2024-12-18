/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanLaporanUlasan;
import java.text.ParseException;
import etanah.model.AduanLokasi;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikWaris;
import etanah.model.Permohonan;
import etanah.model.KodCawangan;
import etanah.model.KodPeranan;
import etanah.model.KodPerananOperasi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PermohonanRujukanLuar;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sourceforge.stripes.action.StreamingResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author nurshahida.radzi
 * @modified ctzainal 04102011-add column tempatKerja
 */
@UrlBinding("/penguatkuasaan/laporan_operasi_polis")
public class LaporanOperasiPolisActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LaporanOperasiPolisActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodUOMDAO kodUOMDAO;
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
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private AduanLokasi aduanLokasi;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
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
    private String nama;
    private String noKp;
    private String peranan;
    private BigDecimal nilaiKecurian;
    private String kadKuasa;
    private String namaKetua;
    private String idKetua;
    private String noPengenalanKetua;
    private String kadKuasaKetua;
    private String namaKetuaPasukan;
    private String tarikhPenahanan;
    private String tarikhPenahanan1 = " ";
    private String hour;
    private String minute;
    private String ampm1;
    private PermohonanLaporanUlasan permohonanLaporanUlasan;
    private String ulasan;
    private String kodNegeri;
    private String tempatKerja;
    private String tempatKerjaKetua;
    private List<Pengguna> pengguna;
    private List<Pengguna> pasukan;
    private String pilih;
    private String namaPasukan;
    private String icPasukan;
    private String jawatanPasukan;
    private String idPasukan;
    private String namaCarian;
    private String noPengenalanCarian;
    private String jawatanCarian;
    private String idPenggunaCarian;
    private String idOperasi = "";
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<HakmilikWaris> hakmilikWarisList;
    private String idHakmilik;
    private String idMH;
    private Hakmilik hakmilik;
    private HakmilikPermohonan hakmilikPermohonan;
    private String statusCarian;
    private HakmilikPermohonan hakmilikMohon;
    private List<HakmilikPihakBerkepentingan> listPihakBerkepentingan; //jenis.kod != 'PM' 
    private List<HakmilikWaris> listWaris;
    private List<HakmilikPihakBerkepentingan> listPemilik; //jenis.kod = 'PM' 
    private Boolean senaraiMohonHakmilik = Boolean.FALSE;
    private String fromPage;
    private String kadKuasaCarian;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/laporan_operasi_polis.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/laporan_operasi_polis_view.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        getContext().getRequest().setAttribute("ulasanPPTK", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_operasi_polis_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<String> peranan = new ArrayList<String>();
        peranan.add("2410");
        peranan.add("2409");
        peranan.add("2414");
        peranan.add("PPTK");
        peranan.add("PPT");
        pengguna = enforcementService.getSenaraiPenggunaForPasukan(peranan);
        peranan = new ArrayList<String>();
        peranan.add("2410");
        peranan.add("2409");
        peranan.add("2414");
        peranan.add("2407");
        peranan.add("PPT");
        pasukan = enforcementService.getSenaraiPenggunaForPasukan2(peranan);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        senaraiPasukan = new ArrayList<OperasiPenguatkuasaanPasukan>();
        kodNegeri = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);

                    ArrayList kumpulanBpel = new ArrayList<String>();
                    if (permohonan.getCawangan().getKod().equalsIgnoreCase("00")) { //00 = PTG MELAKA
                        kumpulanBpel.add("pptptgkuasa"); // PPT
                        kumpulanBpel.add("pptkptgkuasa"); //PPTK
                        kumpulanBpel.add("ppttptgkuasa"); //PPTT
                    } else { // for 01 = melaka tengah, 02 = jasin & 03 = alor gajah
//                        kumpulanBpel.add("pptptdkuasa"); // PPT
                        kumpulanBpel.add("PPTanah"); // PPT
                        kumpulanBpel.add("pptkptdkuasa"); //PPTK
                        kumpulanBpel.add("ppttptdkuasa"); //PPTT
                    }

                    pengguna = enforcementService.findUserKumpBpel(kumpulanBpel, permohonan.getCawangan().getKod());
                    logger.info("::: size pengguna based on kump bpel : " + pengguna.size());

                } else {
                    //for N9, pass idPermohonan & kategoriTindakan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
                    fromPage = "LS"; // LS = Laporan siasatan
                }
                permohonan = permohonanDAO.findById(idPermohonan);

                if ("05".equals(conf.getProperty("kodNegeri"))) {
                    if (permohonan.getKodUrusan().getKod().equals("429")) {
                        if (pguna.getKodCawangan().getKod().equalsIgnoreCase("10")) { /* Hafifi : Adakah kod cawangan sudah tetap kepada 10 dan tiada perubahan? */
                            pengguna = enforcementService.getSenaraiPengguna("2415");
                        } else {
                            pengguna = enforcementService.getSenaraiPengguna("2407");
                        }
                    } else if (permohonan.getKodUrusan().getKod().equals("425") 
                            || permohonan.getKodUrusan().getKod().equals("425A")
                            || permohonan.getKodUrusan().getKod().equals("427")
                            || permohonan.getKodUrusan().getKod().equals("428")
                            || permohonan.getKodUrusan().getKod().equals("426")) {
//                        peranan = new ArrayList<String>();
//                        peranan.add("PPTK");
//                        pengguna = enforcementService.getSenaraiKumpulanBpel(peranan);
                        //Pengguna pengguna = penggunaDAO.findById(id);
                        idKetua = getContext().getRequest().getParameter("idKetua");
                        if (idKetua == null) {
                            if (pguna != null) {
                                namaKetuaPasukan = pguna.getNama();
                                noPengenalanKetua = pguna.getNoPengenalan();
                                tempatKerjaKetua = pguna.getJawatan(); //jabatan
                                namaKetua = pguna.getIdPengguna();
                                kadKuasaKetua = pguna.getKadKuasa();

                                System.out.println("namacarian : " + namaCarian);
                                System.out.println("noPengenalanCarian : " + noPengenalanCarian);
                                System.out.println("jawatanCarian : " + jawatanCarian);
                                System.out.println("idPenggunaCarian : " + idPenggunaCarian);
                                System.out.println("kadKuasaCarian : " + kadKuasaCarian);

                            }
                        } else {
                            operasiPenguatkuasaanPasukan = enforcementService.findKetua(Long.parseLong(idKetua));
                            namaKetuaPasukan = operasiPenguatkuasaanPasukan.getNama();
                            noPengenalanKetua = operasiPenguatkuasaanPasukan.getNoKp();
                            tempatKerjaKetua = operasiPenguatkuasaanPasukan.getTempatKerja();
                            namaKetua = pguna.getIdPengguna();
                            kadKuasaKetua = operasiPenguatkuasaanPasukan.getKadKuasa();

                            System.out.println("namacarian : " + namaCarian);
                            System.out.println("noPengenalanCarian : " + noPengenalanCarian);
                            System.out.println("jawatanCarian : " + jawatanCarian);
                            System.out.println("idPenggunaCarian : " + idPenggunaCarian);
                            System.out.println("kadKuasaCarian : " + kadKuasaCarian);
                        }



                    }
                }

                System.out.println("operasiPenguatkuasaan ----------- : " + operasiPenguatkuasaan);

                if (operasiPenguatkuasaan != null) {
                    idOperasi = Long.toString(operasiPenguatkuasaan.getIdOperasi());
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
                                    //namaKetua = senaraiPasukan.get(i).getNama();
                                    namaKetuaPasukan = senaraiPasukan.get(i).getNama();
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
                    if (operasiPenguatkuasaan.getJumlahTangkapan() != null) {
                        jumlahTangkapan = operasiPenguatkuasaan.getJumlahTangkapan();
                    }
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
//                        if (minit.startsWith("0")) {
//                            minit = sdf1.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
//                        }
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
//                        if (minute.startsWith("0")) {
//                            minute = sdf2.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(15, 16);
//                        }
                        ampm1 = sdf2.format(operasiPenguatkuasaan.getTarikhPenahanan()).substring(17, 19);

                    } else {

                        tarikhPenahanan = new String();
                    }


                }

                System.out.println("idOperasi ----------- : " + idOperasi);


                permohonanLaporanUlasan = enforceService.findKeputusanAduanByIdPermohonanTujuan(idPermohonan, "LO");//LO = laporan operasi
                if (permohonanLaporanUlasan != null) {
                    ulasan = permohonanLaporanUlasan.getUlasan();

                }

                if ("05".equals(conf.getProperty("kodNegeri")) && permohonan.getKodUrusan().getKod().equalsIgnoreCase("429")) {
                    senaraiMohonHakmilik = true;
                    hakmilikPermohonanList = enforceService.findSenaraiMohonHakmilik(permohonan.getIdPermohonan());
                    ArrayList senaraiHakmilik = new ArrayList<String>();
                    if (!hakmilikPermohonanList.isEmpty()) {
                        for (int j = 0; j < hakmilikPermohonanList.size(); j++) {
                            HakmilikPermohonan hp = hakmilikPermohonanList.get(j);
                            if (hp.getHakmilik() != null) {
                                senaraiHakmilik.add(hp.getHakmilik().getIdHakmilik());
                            }
                        }

                        System.out.println("size senarai hakmilik : " + senaraiHakmilik.size());
                        if (!senaraiHakmilik.isEmpty()) {
                            listWaris = enforcementService.findWarisByIdHakmilik(senaraiHakmilik);
                            listPihakBerkepentingan = enforcementService.findPihakBerkepentingan(senaraiHakmilik, "ne");
                            listPemilik = enforcementService.findPihakBerkepentingan(senaraiHakmilik, "eq");
                        }

                        System.out.println("size senarai listWaris : " + listWaris.size());
                        System.out.println("size senarai listPihakBerkepentingan : " + listPihakBerkepentingan.size());
                        System.out.println("size senarai listPemilik : " + listPemilik.size());

                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
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
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_operasi_polis.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        logger.info("-------------simpan-----------------");

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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
        System.out.println("tarikh tahan :" + tarikhPenahanan);
        System.out.println("hour :" + hour);
        System.out.println("minute :" + minute);
        System.out.println("ampm1 :" + ampm1);

        System.out.println("tarikh tahan lps cantum :" + tarikhPenahanan);
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
            operasiPenguatkuasaan.setTarikhOperasi(sdf1.parse(tarikhOperasi));
            if (!StringUtils.isBlank(tarikhPenahanan)) {
                tarikhPenahanan = tarikhPenahanan + " " + hour + ":" + minute + " " + ampm1;
                if (tarikhPenahanan != null) {
                    operasiPenguatkuasaan.setTarikhPenahanan(sdf2.parse(tarikhPenahanan));
                }
            }

        } catch (Exception ex) {
            ex.getCause();
            logger.error(ex);
        }

        enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);

        try {

            try {
                //for saving ketua to table : kkuasa_pasukan
                idKetua = getContext().getRequest().getParameter("idKetua");
                System.out.print(idKetua);
                namaKetuaPasukan = getContext().getRequest().getParameter("namaKetuaPasukan");
                noPengenalanKetua = getContext().getRequest().getParameter("noPengenalanKetua");
                kadKuasaKetua = getContext().getRequest().getParameter("kadKuasaKetua");
                tempatKerjaKetua = getContext().getRequest().getParameter("tempatKerjaKetua");

                if (noPengenalanKetua != null && StringUtils.isNotBlank(noPengenalanKetua)) {
                    logger.info("-----info for ketua is exist--------");
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
                    operasiPenguatkuasaanPasukan.setNama(namaKetuaPasukan);
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
            int rowCount = 0;
            if (StringUtils.isNotBlank(getContext().getRequest().getParameter("recordCount"))) {
                rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            }
            for (int i = 0; i < rowCount; i++) {
                if (senaraiPasukanWithoutKetua.size() != 0 && i < senaraiPasukanWithoutKetua.size()) {
                    Long id = senaraiPasukanWithoutKetua.get(i).getIdOperasiPenguatkuasaanPasukan();
                    if (id != null) {
                        operasiPenguatkuasaanPasukan = enforceService.findSenaraiPasukan(id);
                    }
                } else {
                    System.out.println("create new pasukan");
                    operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
                }

                nama = getContext().getRequest().getParameter("nama" + i);

                System.out.println("nama pasukan: " + nama);
                noKp = getContext().getRequest().getParameter("noKp" + i);
                peranan = getContext().getRequest().getParameter("peranan" + i);
                kadKuasa = getContext().getRequest().getParameter("kadKuasa" + i);
                tempatKerja = getContext().getRequest().getParameter("tempatKerja" + i);

                KodPerananOperasi kp = kodPerananOperasiDAO.findById(peranan);

                operasiPenguatkuasaanPasukan.setCawangan(peng.getKodCawangan());
                operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
                operasiPenguatkuasaanPasukan.setNama(nama);
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
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/laporan_operasi_polis.jsp").addParameter("tab", "true");
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
        return new RedirectResolution(LaporanOperasiPolisActionBean.class, "locate");
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
        return new JSP("penguatkuasaan/laporan_operasi_polis_view.jsp").addParameter("tab", "true");
    }

    public Resolution refreshpage() {
        return new RedirectResolution(LaporanOperasiPolisActionBean.class, "locate");
    }

    public Resolution findPengguna() {

        String id = getContext().getRequest().getParameter("id");
        Pengguna pengguna = penggunaDAO.findById(id);

        if (pengguna != null) {
            namaCarian = pengguna.getNama();
            noPengenalanCarian = pengguna.getNoPengenalan();
            jawatanCarian = pengguna.getJawatan(); //jabatan
            idPenggunaCarian = pengguna.getIdPengguna();
            kadKuasaCarian = pengguna.getKadKuasa();

            System.out.println("namacarian : " + namaCarian);
            System.out.println("noPengenalanCarian : " + noPengenalanCarian);
            System.out.println("jawatanCarian : " + jawatanCarian);
            System.out.println("idPenggunaCarian : " + idPenggunaCarian);
            System.out.println("kadKuasaCarian : " + kadKuasaCarian);

        }
        return new JSP("penguatkuasaan/laporan_operasi_polis.jsp").addParameter("tab", "true");
    }

    public Resolution addRecordPasukan() {
        logger.info("::addRecordPasukan");
        idPasukan = getContext().getRequest().getParameter("idPasukan");

        if (idPasukan != null || !StringUtils.isBlank(idPasukan)) {
            operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idPasukan));
            if (operasiPenguatkuasaanPasukan != null) {
                namaPasukan = operasiPenguatkuasaanPasukan.getNama();
                icPasukan = operasiPenguatkuasaanPasukan.getNoKp();
                kadKuasa = operasiPenguatkuasaanPasukan.getKadKuasa();
                jawatanPasukan = operasiPenguatkuasaanPasukan.getTempatKerja();
                peranan = operasiPenguatkuasaanPasukan.getKodPerananOperasi().getKod();

                System.out.println("edit : namaPasukan: " + namaPasukan);
                System.out.println("edit : icPasukan: " + icPasukan);
                System.out.println("edit : kadKuasa: " + kadKuasa);
                System.out.println("edit : jawatanPasukan: " + jawatanPasukan);
                System.out.println("edit : peranan: " + peranan);
            }

        }
        return new JSP("penguatkuasaan/popup_tambah_pasukan.jsp").addParameter("popup", "true");
    }

    public Resolution findPenggunaPasukan() {
        logger.info(":::::::::::::::::::findPenggunaPasukan");

        String id = getContext().getRequest().getParameter("id");
        Pengguna pasukan = penggunaDAO.findById(id);

        if (pasukan != null) {
            namaCarian = pasukan.getNama();
            noPengenalanCarian = pasukan.getNoPengenalan();
            jawatanCarian = pasukan.getJawatan(); //jabatan
            idPenggunaCarian = pasukan.getIdPengguna();
            kadKuasaCarian = pasukan.getKadKuasa();

            System.out.println("namacarian : " + namaCarian);
            System.out.println("noPengenalanCarian : " + noPengenalanCarian);
            System.out.println("jawatanCarian : " + jawatanCarian);
            System.out.println("idPenggunaCarian : " + idPenggunaCarian);
            System.out.println("kadKuasaCarian : " + kadKuasaCarian);

        }
        return new JSP("penguatkuasaan/popup_tambah_pasukan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanPasukanOperasi() {
        logger.info(":::::::::::::::::::simpanPasukanOperasi");
        logger.info(":::::::::::::::::::idPasukan : " + idPasukan);
        InfoAudit ia = new InfoAudit();

        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
            ia.setDimasukOleh(pguna);
            ia.setTarikhMasuk(new java.util.Date());

            operasiPenguatkuasaan.setPermohonan(permohonan);
            operasiPenguatkuasaan.setInfoAudit(ia);
            operasiPenguatkuasaan.setCawangan(pguna.getKodCawangan());
            if ("05".equals(conf.getProperty("kodNegeri"))) {
                //for N9, need to save into column kategoriTindakan
                operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
            }
            operasiPenguatkuasaan.setJenisTangkapan(kodUOMDAO.findById("OG"));
            enforceService.simpanOperasiPenguatkuasaan(operasiPenguatkuasaan);
        }

        if (idPasukan != null || !StringUtils.isBlank(idPasukan)) {
            operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukanDAO.findById(Long.parseLong(idPasukan));
        }

        System.out.println("value operasiPenguatkuasaanPasukan : " + operasiPenguatkuasaanPasukan);

        if (operasiPenguatkuasaanPasukan != null) {
            ia = operasiPenguatkuasaanPasukan.getInfoAudit();
            ia.setDiKemaskiniOleh(pguna);
            ia.setTarikhKemaskini(new java.util.Date());
        } else {
            operasiPenguatkuasaanPasukan = new OperasiPenguatkuasaanPasukan();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pguna);
        }

        System.out.println("simpanPasukanOperasi : namaPasukan: " + namaPasukan);
        System.out.println("simpanPasukanOperasi : icPasukan: " + icPasukan);
        System.out.println("simpanPasukanOperasi : kadKuasa: " + kadKuasa);
        System.out.println("simpanPasukanOperasi : jawatanPasukan: " + jawatanPasukan);
        System.out.println("simpanPasukanOperasi : peranan: " + peranan);

        KodPerananOperasi kp = kodPerananOperasiDAO.findById(peranan);

        operasiPenguatkuasaanPasukan.setCawangan(pguna.getKodCawangan());
        operasiPenguatkuasaanPasukan.setIdOperasiPenguatkuasaan(operasiPenguatkuasaan);
        operasiPenguatkuasaanPasukan.setNama(namaPasukan);
        operasiPenguatkuasaanPasukan.setNoKp(icPasukan);
        operasiPenguatkuasaanPasukan.setKodPerananOperasi(kp);
        operasiPenguatkuasaanPasukan.setKadKuasa(kadKuasa);
        operasiPenguatkuasaanPasukan.setTempatKerja(jawatanPasukan);
        operasiPenguatkuasaanPasukan.setInfoAudit(ia);
        enforceService.simpanOperasiPenguatkuasaanPasukan(operasiPenguatkuasaanPasukan);

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanOperasiPolisActionBean.class, "locate");
    }

    public Resolution simpanHakmilik() {
        logger.info(":::::simpanHakmilik");

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new java.util.Date());

        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idMH = getContext().getRequest().getParameter("idMH");

        if (idHakmilik != null && StringUtils.isNotBlank(idHakmilik)) {

            hakmilik = hakmilikDAO.findById(idHakmilik);

            System.out.println("id hakmilik: " + idHakmilik);
            if (StringUtils.isNotBlank(idHakmilik)) {
                hakmilik = hakmilikDAO.findById(idHakmilik);
            }

            if (StringUtils.isNotBlank(idMH)) {
                hakmilikMohon = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
            }

            if (hakmilikMohon == null) {
                hakmilikMohon = new HakmilikPermohonan();
                ia.setDimasukOleh(pguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = hakmilikMohon.getInfoAudit();
                ia.setDiKemaskiniOleh(pguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            hakmilikMohon.setHakmilik(hakmilik);
            hakmilikMohon.setPermohonan(permohonan);
            hakmilikMohon.setInfoAudit(ia);
            hakmilikMohon.setBandarPekanMukimBaru(hakmilik.getBandarPekanMukim());
            hakmilikMohon.setLuasTerlibat(hakmilik.getLuas());
            hakmilikMohon.setNoLot(hakmilik.getNoLot());
            hakmilikMohon.setKodUnitLuas(hakmilik.getKodUnitLuas());
            enforceService.simpanhakmilikPermohonan(hakmilikMohon);

        }

        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanOperasiPolisActionBean.class, "locate");
    }

    public Resolution addHakmilik() {
        logger.info("::addHakmilik");
        idMH = getContext().getRequest().getParameter("id");

        if (StringUtils.isNotBlank(idMH)) {
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
            if (hakmilikPermohonan != null) {
                hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
                logger.info("::edit record");
            }
        }
        return new JSP("penguatkuasaan/laporan_tanah/popup_add_hakmilik.jsp").addParameter("popup", "true");
    }

    public Resolution deleteHakmilik() {
        logger.info(":::: deleteHakmilik");
        idMH = getContext().getRequest().getParameter("id");


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            if (StringUtils.isNotBlank(idMH)) {
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.parseLong(idMH));
                if (hakmilikPermohonan != null) {
                    hakmilikPermohonanDAO.delete(hakmilikPermohonan);
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
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(LaporanOperasiPolisActionBean.class, "locate");
    }

    public Resolution findHakmilik() {
        logger.info("-----------findHakmilik-------------");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        logger.info("id hakmilik carian :" + idHakmilik);

        try {
            hakmilik = enforceService.semakIdHakmilik(idHakmilik);
            if (hakmilik != null) {
                statusCarian = "W"; //W = wujud

            } else {
                statusCarian = "TW"; //TW = Tidak Wujud
                addSimpleError("Maklumat hakmilik tidak dijumpai");
                return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");

            }
            System.out.println(":::::: keputusan carian hakmilik ::::::");
            System.out.println("statusCarian :" + statusCarian);
            System.out.println("no lot :" + hakmilik.getNoLot());
            System.out.println("kodLotCarian :" + hakmilik.getLot().getKod());
        } catch (Exception ex) {
            addSimpleError(ex.getMessage());

        }
        return new JSP("penguatkuasaan/popup_add_hakmilik.jsp").addParameter("popup", "true");
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

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
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

    public String getTarikhPenahanan1() {
        return tarikhPenahanan1;
    }

    public void setTarikhPenahanan1(String tarikhPenahanan1) {
        this.tarikhPenahanan1 = tarikhPenahanan1;
    }

    public String getIcPasukan() {
        return icPasukan;
    }

    public void setIcPasukan(String icPasukan) {
        this.icPasukan = icPasukan;
    }

    public String getIdPasukan() {
        return idPasukan;
    }

    public void setIdPasukan(String idPasukan) {
        this.idPasukan = idPasukan;
    }

    public String getJawatanPasukan() {
        return jawatanPasukan;
    }

    public void setJawatanPasukan(String jawatanPasukan) {
        this.jawatanPasukan = jawatanPasukan;
    }

    public String getNamaPasukan() {
        return namaPasukan;
    }

    public void setNamaPasukan(String namaPasukan) {
        this.namaPasukan = namaPasukan;
    }

    public List<Pengguna> getPasukan() {
        return pasukan;
    }

    public void setPasukan(List<Pengguna> pasukan) {
        this.pasukan = pasukan;
    }

    public List<Pengguna> getPengguna() {
        return pengguna;
    }

    public void setPengguna(List<Pengguna> pengguna) {
        this.pengguna = pengguna;
    }

    public String getPilih() {
        return pilih;
    }

    public void setPilih(String pilih) {
        this.pilih = pilih;
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

    public String getNamaKetuaPasukan() {
        return namaKetuaPasukan;
    }

    public void setNamaKetuaPasukan(String namaKetuaPasukan) {
        this.namaKetuaPasukan = namaKetuaPasukan;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public List<HakmilikWaris> getHakmilikWarisList() {
        return hakmilikWarisList;
    }

    public void setHakmilikWarisList(List<HakmilikWaris> hakmilikWarisList) {
        this.hakmilikWarisList = hakmilikWarisList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getStatusCarian() {
        return statusCarian;
    }

    public void setStatusCarian(String statusCarian) {
        this.statusCarian = statusCarian;
    }

    public String getIdMH() {
        return idMH;
    }

    public void setIdMH(String idMH) {
        this.idMH = idMH;
    }

    public List<HakmilikPihakBerkepentingan> getListPihakBerkepentingan() {
        return listPihakBerkepentingan;
    }

    public void setListPihakBerkepentingan(List<HakmilikPihakBerkepentingan> listPihakBerkepentingan) {
        this.listPihakBerkepentingan = listPihakBerkepentingan;
    }

    public List<HakmilikWaris> getListWaris() {
        return listWaris;
    }

    public void setListWaris(List<HakmilikWaris> listWaris) {
        this.listWaris = listWaris;
    }

    public List<HakmilikPihakBerkepentingan> getListPemilik() {
        return listPemilik;
    }

    public void setListPemilik(List<HakmilikPihakBerkepentingan> listPemilik) {
        this.listPemilik = listPemilik;
    }

    public Boolean getSenaraiMohonHakmilik() {
        return senaraiMohonHakmilik;
    }

    public void setSenaraiMohonHakmilik(Boolean senaraiMohonHakmilik) {
        this.senaraiMohonHakmilik = senaraiMohonHakmilik;
    }

    public String getFromPage() {
        return fromPage;
    }

    public void setFromPage(String fromPage) {
        this.fromPage = fromPage;
    }

    public PermohonanLaporanUlasanDAO getPermohonanLaporanUlasanDAO() {
        return permohonanLaporanUlasanDAO;
    }

    public void setPermohonanLaporanUlasanDAO(PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO) {
        this.permohonanLaporanUlasanDAO = permohonanLaporanUlasanDAO;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getKadKuasaCarian() {
        return kadKuasaCarian;
    }

    public void setKadKuasaCarian(String kadKuasaCarian) {
        this.kadKuasaCarian = kadKuasaCarian;
    }
}
