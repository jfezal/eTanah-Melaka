/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.google.inject.Inject;
import etanah.dao.BarangRampasanDAO;
import etanah.dao.KodNegeriDAO;

import etanah.dao.PermohonanDAO;
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.BarangRampasan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodNegeri;
import etanah.model.KodStatusBarangRampasan;
import etanah.model.Notis;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;
import etanah.model.OperasiPenguatkuasaanPasukan;
import etanah.model.PegawaiPenyiasat;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSaksi;

import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/utiliti_kertas_siasatan")
public class UtilitiKertasSiasatanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(UtilitiKertasSiasatanActionBean.class);
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    private BarangRampasanDAO barangRampasanDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodNegeriDAO kodNegeriDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<BarangRampasan> listBarangRampasan;
    private String idBarang;
    private List<KodStatusBarangRampasan> senaraiKodStatus;
    private String status;
    private Pengguna pengguna;
    private boolean idPermohonanNotExist = Boolean.FALSE;
    private String statusBaru;
    private String ulasan;
    private boolean dataKertasSiasatan = Boolean.FALSE;
    private List<OperasiPenguatkuasaan> listOp;
    private List<KandunganFolder> listDokumen;
    private KandunganFolder folderDokumen;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private PermohonanRujukanLuar permohonanRujukanLuar1;
    private String rowCount;
    private PermohonanKertas kertasKronologiKes;
    private PermohonanKertasKandungan defaultKronologiKes;
    private List<PermohonanKertasKandungan> listAllKronologiKes;
    private PermohonanKertas permohonanKertas;
    private AduanLokasi aduanLokasi;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private Notis notis;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukan;
    private List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak;
    private String namaKetua;
    private List<PermohonanSaksi> senaraiPermohonanSaksi;
    private String pasukan;
    private OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan;
    private PermohonanSaksi permohonanSaksi;
    private String noPengenalanKetua;
    private List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi;
    private PegawaiPenyiasat pegawaiSiasat;
    private String kronologiKes;
    private List<PermohonanKertasKandungan> senaraiKronologiKes;
    private List<PegawaiPenyiasat> listPegawaiSiasat;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private KandunganFolder kf;
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private String noRujukan;
    private String jam;
    private String minit;
    private String ampm;
    private String idOperasi;
    private OperasiAgensi operasiAgensi;
    private String lokasi;
    private String idDokumen;
    private String idOperasiAgensi;
    private OperasiPenguatkuasaan operasiPenguatkuasaanIP;
    private List<KandunganFolder> senaraiDokumen;
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private KodNegeri kodNegeri;
    private Permohonan secondLayerPermohonan;
    private Permohonan thirdLayerPermohonan;

    @DefaultHandler
    public Resolution semakKertasSiasatan() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kertas_siasatan.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiKodStatus = enforceService.getSenaraiKodStatusBarangRampasan();
    }

    public Resolution checkStatusKertasSiasatan() {
//        idPermohonan = getContext().getRequest().getParameter("id");
        System.out.println("idPermohonan : " + idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodNegeri = kodNegeriDAO.findById(conf.getProperty("kodNegeri"));

            if (permohonan != null) {

                if (permohonan.getPermohonanSebelum() != null) {
                    //1) find mohon nota based on id permohonan sebelum (IP create new id permohonan)
                    secondLayerPermohonan = permohonan.getPermohonanSebelum();
                    logger.info(":: secondLayerPermohonan : " + secondLayerPermohonan.getIdPermohonan());



                    //2) find mohon kertas for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                    Permohonan permohonanIP = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                    if (permohonanIP != null) {
                        if (permohonanIP.getPermohonanSebelum() != null) {
                            thirdLayerPermohonan = permohonanIP.getPermohonanSebelum();

                        }

                    }
                }

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426")) {
                        listOp = enforceService.findListLaporanOperasi(idPermohonan);
                        if (listOp.size() == 0) {
                            if (permohonan.getPermohonanSebelum() != null) {
                                listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());

                                //get list dokumen for senarai laporan polis
                                listDokumen = enforcementService.getListDokumen(permohonan.getPermohonanSebelum().getFolderDokumen().getFolderId(), "LP");
                                System.out.println("size list dokumen (id sebelum): " + listDokumen.size());
                            }
                        } else {
                            listDokumen = enforcementService.getListDokumen(permohonan.getFolderDokumen().getFolderId(), "LP");
                            System.out.println("size list dokumen : " + listDokumen.size());
                            if (listDokumen.size() != 0) {
                                folderDokumen = listDokumen.get(0);
                            }
                        }
                        logger.info("listOp size : " + listOp.size());
                    } else {
                        //for Melaka, only pass idPermohonan
                        operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                        permohonanRujukanLuar1 = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
                    }
                }

                permohonanKertas = enforcementService.findByKodIdPermohonan(permohonan.getIdPermohonan());
                aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
                senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);

                notis = enforcementService.findSamanByIdmohon(idPermohonan);

                if (operasiPenguatkuasaan != null) {
                    senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
                }


                senaraiPasukan = new ArrayList<OperasiPenguatkuasaanPasukan>();
                listPegawaiSiasat = new ArrayList<PegawaiPenyiasat>();
                senaraiOksIp = new ArrayList<AduanOrangKenaSyak>();

                if (operasiPenguatkuasaan != null) {
                    senaraiPasukan = enforceService.getSenaraiPasukan(operasiPenguatkuasaan.getIdOperasi());
                    senaraiPasukanSaksi = enforcementService.findListByIdPasukan(operasiPenguatkuasaan.getIdOperasi());
                    logger.info("size senaraiPasukanSaksi : " + senaraiPasukanSaksi.size());
                    try {
                        if (senaraiPasukan.size() != 0) {
                            for (int i = 0; i < senaraiPasukan.size(); i++) {
                                if (senaraiPasukan.get(i).getKodPerananOperasi().getKod().equalsIgnoreCase("K")) {
                                    namaKetua = senaraiPasukan.get(i).getNama();
                                    noPengenalanKetua = senaraiPasukan.get(i).getNoKp();
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.getCause();
                    }


                } else {
                    senaraiPasukan = enforceService.getSenaraiPasukanForMultipleOp(idPermohonan);
                    senaraiPasukanSaksi = enforceService.findListByIdPasukanForMultipleOp(idPermohonan);
                    logger.info("size senaraiPasukan for multiple op: " + senaraiPasukan.size());
                    logger.info("size senaraiPasukanSaksi for multiple op: " + senaraiPasukanSaksi.size());

                }

                senaraiAduanOrangKenaSyak = enforceService.findByID(idPermohonan);
                senaraiPermohonanSaksi = enforceService.findByIDSaksi(idPermohonan);
                pegawaiSiasat = enforcementService.findPengguna(idPermohonan);


                senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                if (senaraiOksIp.size() != 0) {
                    Long idOperasi = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                    System.out.println("id operasi : " + idOperasi);
                    listPegawaiSiasat = enforcementService.findSenaraiPegawaiPenyiasatIp(idPermohonan);
                    logger.info("size listPegawaiSiasat : " + listPegawaiSiasat.size());

                    senaraiPasukanSaksi = enforcementService.findSenaraiSaksiPasukanIp(idPermohonan, idOperasi);
                    senaraiPasukan = enforcementService.findSenaraiPasukanIp(idPermohonan, idOperasi);
                    logger.info("size senaraiPasukanSaksi : " + senaraiPasukanSaksi.size());

                    operasiPenguatkuasaanIP = enforceService.findByIdOp(idOperasi);

                    if (listPegawaiSiasat.isEmpty()) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listPegawaiSiasat = enforcementService.findSenaraiPegawaiPenyiasatIp(permohonan.getPermohonanSebelum().getIdPermohonan());
                            logger.info("size listPegawaiSiasat (using id permohonan sebelum): " + listPegawaiSiasat.size());
                        }
                    }

                    if (permohonanKertas == null) {
                        permohonanKertas = enforcementService.findByKodIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                    }

                }

                if (permohonanKertas != null) {
                    permohonanKertasKandungan = enforcementService.findByKodIdKertas(permohonanKertas.getIdKertas());

                }

                rowCount = "0";

                if ("04".equals(conf.getProperty("kodNegeri"))) {
                    //Kronologi kes
                    kertasKronologiKes = enforceService.findByIdKrts(idPermohonan, "KRONO");

                    if (kertasKronologiKes == null) {
                        if (permohonan.getPermohonanSebelum() != null) {
                            kertasKronologiKes = enforceService.findByIdKrts(permohonan.getPermohonanSebelum().getIdPermohonan(), "KRONO");
                            senaraiDokumen = enforceService.getListDokumen(permohonan.getPermohonanSebelum().getFolderDokumen().getFolderId());
                            System.out.println("senarai dokumen : " + senaraiDokumen.size());
                        }
                    }

                    if (kertasKronologiKes != null) {
                        //for default kronologi
                        defaultKronologiKes = enforceService.findByIdBil(kertasKronologiKes.getIdKertas());
                        if (defaultKronologiKes != null) {
                            kronologiKes = defaultKronologiKes.getKandungan();
                        }
                        //for senarai kronologi kes - all except bil 1
                        senaraiKronologiKes = enforceService.findAllKandungan(kertasKronologiKes.getIdKertas());
                        logger.info("size senaraiKronologiKes : " + senaraiKronologiKes.size());

                        //for senarai kronologi kes - all 
                        listAllKronologiKes = enforceService.listKronologiKes(kertasKronologiKes.getIdKertas());
                        logger.info("size listAllKronologiKes : " + listAllKronologiKes.size());

                        rowCount = String.valueOf(senaraiKronologiKes.size());
                        logger.info("row count : " + rowCount);
                    }

                }

            } else {
                idPermohonanNotExist = true;
                addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            }
        } else {
            addSimpleError("Sila masukkan id permohonan.");
        }

        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti_kertas_siasatan.jsp");


    }

    public void setPermohonan(Permohonan p) {
        this.permohonan = p;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatus() {
        return senaraiKodStatus;
    }

    public void setSenaraiKodStatus(List<KodStatusBarangRampasan> senaraiKodStatus) {
        this.senaraiKodStatus = senaraiKodStatus;
    }

    public List<BarangRampasan> getListBarangRampasan() {
        return listBarangRampasan;
    }

    public void setListBarangRampasan(List<BarangRampasan> listBarangRampasan) {
        this.listBarangRampasan = listBarangRampasan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isIdPermohonanNotExist() {
        return idPermohonanNotExist;
    }

    public void setIdPermohonanNotExist(boolean idPermohonanNotExist) {
        this.idPermohonanNotExist = idPermohonanNotExist;
    }

    public String getStatusBaru() {
        return statusBaru;
    }

    public void setStatusBaru(String statusBaru) {
        this.statusBaru = statusBaru;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public boolean isDataKertasSiasatan() {
        return dataKertasSiasatan;
    }

    public void setDataKertasSiasatan(boolean dataKertasSiasatan) {
        this.dataKertasSiasatan = dataKertasSiasatan;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public List<KandunganFolder> getListDokumen() {
        return listDokumen;
    }

    public void setListDokumen(List<KandunganFolder> listDokumen) {
        this.listDokumen = listDokumen;
    }

    public KandunganFolder getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(KandunganFolder folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar1() {
        return permohonanRujukanLuar1;
    }

    public void setPermohonanRujukanLuar1(PermohonanRujukanLuar permohonanRujukanLuar1) {
        this.permohonanRujukanLuar1 = permohonanRujukanLuar1;
    }

    public PermohonanKertas getKertasKronologiKes() {
        return kertasKronologiKes;
    }

    public void setKertasKronologiKes(PermohonanKertas kertasKronologiKes) {
        this.kertasKronologiKes = kertasKronologiKes;
    }

    public PermohonanKertasKandungan getDefaultKronologiKes() {
        return defaultKronologiKes;
    }

    public void setDefaultKronologiKes(PermohonanKertasKandungan defaultKronologiKes) {
        this.defaultKronologiKes = defaultKronologiKes;
    }

    public List<PermohonanKertasKandungan> getListAllKronologiKes() {
        return listAllKronologiKes;
    }

    public void setListAllKronologiKes(List<PermohonanKertasKandungan> listAllKronologiKes) {
        this.listAllKronologiKes = listAllKronologiKes;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }

    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }

    public Notis getNotis() {
        return notis;
    }

    public void setNotis(Notis notis) {
        this.notis = notis;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukan() {
        return senaraiPasukan;
    }

    public void setSenaraiPasukan(List<OperasiPenguatkuasaanPasukan> senaraiPasukan) {
        this.senaraiPasukan = senaraiPasukan;
    }

    public List<AduanOrangKenaSyak> getSenaraiAduanOrangKenaSyak() {
        return senaraiAduanOrangKenaSyak;
    }

    public void setSenaraiAduanOrangKenaSyak(List<AduanOrangKenaSyak> senaraiAduanOrangKenaSyak) {
        this.senaraiAduanOrangKenaSyak = senaraiAduanOrangKenaSyak;
    }

    public String getNamaKetua() {
        return namaKetua;
    }

    public void setNamaKetua(String namaKetua) {
        this.namaKetua = namaKetua;
    }

    public List<PermohonanSaksi> getSenaraiPermohonanSaksi() {
        return senaraiPermohonanSaksi;
    }

    public void setSenaraiPermohonanSaksi(List<PermohonanSaksi> senaraiPermohonanSaksi) {
        this.senaraiPermohonanSaksi = senaraiPermohonanSaksi;
    }

    public String getPasukan() {
        return pasukan;
    }

    public void setPasukan(String pasukan) {
        this.pasukan = pasukan;
    }

    public OperasiPenguatkuasaanPasukan getOperasiPenguatkuasaanPasukan() {
        return operasiPenguatkuasaanPasukan;
    }

    public void setOperasiPenguatkuasaanPasukan(OperasiPenguatkuasaanPasukan operasiPenguatkuasaanPasukan) {
        this.operasiPenguatkuasaanPasukan = operasiPenguatkuasaanPasukan;
    }

    public PermohonanSaksi getPermohonanSaksi() {
        return permohonanSaksi;
    }

    public void setPermohonanSaksi(PermohonanSaksi permohonanSaksi) {
        this.permohonanSaksi = permohonanSaksi;
    }

    public String getNoPengenalanKetua() {
        return noPengenalanKetua;
    }

    public void setNoPengenalanKetua(String noPengenalanKetua) {
        this.noPengenalanKetua = noPengenalanKetua;
    }

    public List<OperasiPenguatkuasaanPasukan> getSenaraiPasukanSaksi() {
        return senaraiPasukanSaksi;
    }

    public void setSenaraiPasukanSaksi(List<OperasiPenguatkuasaanPasukan> senaraiPasukanSaksi) {
        this.senaraiPasukanSaksi = senaraiPasukanSaksi;
    }

    public PegawaiPenyiasat getPegawaiSiasat() {
        return pegawaiSiasat;
    }

    public void setPegawaiSiasat(PegawaiPenyiasat pegawaiSiasat) {
        this.pegawaiSiasat = pegawaiSiasat;
    }

    public String getKronologiKes() {
        return kronologiKes;
    }

    public void setKronologiKes(String kronologiKes) {
        this.kronologiKes = kronologiKes;
    }

    public List<PermohonanKertasKandungan> getSenaraiKronologiKes() {
        return senaraiKronologiKes;
    }

    public void setSenaraiKronologiKes(List<PermohonanKertasKandungan> senaraiKronologiKes) {
        this.senaraiKronologiKes = senaraiKronologiKes;
    }

    public List<PegawaiPenyiasat> getListPegawaiSiasat() {
        return listPegawaiSiasat;
    }

    public void setListPegawaiSiasat(List<PegawaiPenyiasat> listPegawaiSiasat) {
        this.listPegawaiSiasat = listPegawaiSiasat;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public SimpleDateFormat getSdf1() {
        return sdf1;
    }

    public void setSdf1(SimpleDateFormat sdf1) {
        this.sdf1 = sdf1;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
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

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOperasi(String idOperasi) {
        this.idOperasi = idOperasi;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }

    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaanIP() {
        return operasiPenguatkuasaanIP;
    }

    public void setOperasiPenguatkuasaanIP(OperasiPenguatkuasaan operasiPenguatkuasaanIP) {
        this.operasiPenguatkuasaanIP = operasiPenguatkuasaanIP;
    }

    public List<KandunganFolder> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<KandunganFolder> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public KandunganFolder getKf() {
        return kf;
    }

    public void setKf(KandunganFolder kf) {
        this.kf = kf;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public Permohonan getSecondLayerPermohonan() {
        return secondLayerPermohonan;
    }

    public void setSecondLayerPermohonan(Permohonan secondLayerPermohonan) {
        this.secondLayerPermohonan = secondLayerPermohonan;
    }

    public Permohonan getThirdLayerPermohonan() {
        return thirdLayerPermohonan;
    }

    public void setThirdLayerPermohonan(Permohonan thirdLayerPermohonan) {
        this.thirdLayerPermohonan = thirdLayerPermohonan;
    }
    
    
}
