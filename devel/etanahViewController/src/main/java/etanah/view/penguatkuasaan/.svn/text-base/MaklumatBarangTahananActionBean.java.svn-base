/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PenjaminBarangRampasanDAO;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KandunganFolder;
import etanah.model.KodJantina;
import etanah.model.KodJenisPengenalan;
import etanah.model.KodWarganegara;
import etanah.model.PenjaminBarangRampasan;
import etanah.report.ReportUtil;
import etanah.service.common.KandunganFolderService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.inject.Inject;
import etanah.dao.AduanOrangKenaSyakDAO;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.BarangRampasanDAO;
import etanah.model.BarangRampasan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.Dokumen;
import etanah.dao.DokumenDAO;
import etanah.dao.KodBahanBakarDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodJenisBadanKenderaanDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKegunaanKenderaanDAO;
import etanah.dao.KompaunDAO;
import etanah.dao.OperasiAgensiDAO;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KodKategoriItemRampasan;
import etanah.model.KodNegeri;
import etanah.model.KodStatusBarangRampasan;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodBahanBakar;
import etanah.model.KodJenisBadanKenderaan;
import etanah.model.KodKegunaanKenderaan;
import etanah.model.Kompaun;
import etanah.model.OperasiAgensi;
import etanah.model.PermohonanTuntutanBayar;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.ArrayList;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author nurshahida.radzi modify by sitifariza.hanim (14042011) modify by
 * ct.zainal (18052011)- Maklumat penjamin modify by ct.zainal (18062011)-
 * Maklumat pemegang permit modify by sitifariza.hanim (02Ogos2011)- Pemilik
 * Bukan Kenderaan modify by ctzainal (12sept2011)-kenderaan-namaPemandu-based
 * on OKS list
 */
@UrlBinding("/penguatkuasaan/maklumat_barang_tahanan")
public class MaklumatBarangTahananActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBarangTahananActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodJenisBadanKenderaanDAO kodJenisBadanKenderaanDAO;
    @Inject
    KodBahanBakarDAO kodBahanBakarDAO;
    @Inject
    KompaunDAO kompaunDAO;
    @Inject
    KodKegunaanKenderaanDAO kodKegunaanKenderaanDAO;
    @Inject
    PenjaminBarangRampasanDAO penjaminBarangRampasanDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    AduanOrangKenaSyakDAO aduanOrangKenaSyakDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    ReportUtil reportUtil;
    @Inject
    EnforcementService enforcementService;
    @Inject
    OperasiAgensiDAO operasiAgensiDAO;
    private Permohonan permohonan;
    private BarangRampasan barangRampasan;
    private PenjaminBarangRampasan penjaminBarangRampasan;
    private String item;
    private String tempatSimpanan;
    private String catatan;
    private String idBarang;
    private String idPermohonan;
    private int kuantiti;
    private String kuantitiUnit;
    private String kodKategoriItemRampasan;
    private String kodNegeriPemegangPermit;
    private String katItmRmpsn;
    private String barangRampasanStatus;
    private KodStatusBarangRampasan status;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<KodStatusBarangRampasan> senaraiKodStatus;
    private List<OperasiAgensi> senaraiOperasiAgensi;
    private String nomborPendaftaran;
    private String namaPemunya;
    private String noPengenalanPemunya;
    private String nomborEnjin;
    private String nomborCasis;
    private String buatan;
    private String tarikhRampas;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String kodNegeri;
    private String kn; /*kod negeri rampas */

    private String namaModel;
    private String kapasitiEnjin;
    private String kodBahanBakar;
    private String warna;
    private String kodKegunaanKenderaan;
    private String kodJenisBadanKenderaan;
    private String tahunDibuat;
    private int muatanTempatDuduk;
    private BigDecimal kadarLesen;
    private String statusPemunya;
    private Date tarikhPendaftaran;
    private Pengguna dilepaskanOleh;
    private String stageId;
    private Boolean opFlag = false;
    private long idDokumen;
    private String namaPenjamin;
    private KodJenisPengenalan jenisPengenalan;
    private String kodPengenalanPenjamin;
    private String kodJantinaPenjamin;
    private String noPengenalanPenjamin;
    private String alamat1Penjamin;
    private String alamat2Penjamin;
    private String alamat3Penjamin;
    private String alamat4Penjamin;
    private String poskodPenjamin;
    private KodNegeri kodNegeriPenjamin;
    private KodJantina jantinaPenjamin;
    private KodWarganegara warganegaraPenjamin;
    private String noTelPenjamin;
    private String noTelBimbitPenjamin;
    private String hubungan;
    private String idPenjaminBrgRampas;
    private String kodWarganegaraPenjamin;
    private String np;/*kod negeri*/

    private String kp; /*kod pengenalan */

    private String kj; /*kod jantina */

    private String kwg; /*kod warganegara */

    private String kodjbk; /*kod jenis badan kenderaan */

    private String kkkk; /*kod kegunaan kenderaan */

    private String kbbb; /*kod bahan bakar*/

    private String jppermit; /*kod pengenalan pemegang permit*/

    private String knpermit; /*kod negeri pemegang permit*/

    private String barangRampas;
    private String noSyarikat;
    private String pemegangPermit;
    private String kodJenisPengenalanPemegangPermit;
    private String nomborPengenalanPemegangPermit;
    private String tempatPengambilan;
    private String tempatPenghantaran;
    private String jam;
    private String minit;
    private String ampm;
    private String alamat1PemegangPermit;
    private String alamat2PemegangPermit;
    private String alamat3PemegangPermit;
    private String alamat4PemegangPermit;
    private String poskodPemegangPermit;
    private String namaPemandu;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<Kompaun> senaraiKompaun;
    private List<AduanOrangKenaSyak> senaraiOKS;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private String kodNegeriPermohonan;
    private List<OperasiPenguatkuasaan> listOp;
    private OperasiAgensi operasiAgensi;
    private Pengguna pengguna;
    private List<BarangRampasan> listKenderaan;
    private String idOp;
    private String namaSuspek;
    private String noPengenalanSuspek;
    private String alamat1Suspek;
    private String alamat2Suspek;
    private String alamat3Suspek;
    private String poskodSuspek;
    private String jenisBatuan;
    private String tempatTangkap;
    private String noTelPemunya;
    private String noTelSuspek;
    private String noSiri;
    private String namaSuspekTemp;
    private String checkSame;
    private String kodNegeriSuspek;
    private String kodNegeriPemunyaTemp;
    private String kesalahan;
    private String idOperasiAgensi;
    private String noRujukan;
    private List<AduanOrangKenaSyak> senaraiOksIp;
    private Boolean statusIP = Boolean.FALSE;
    private String tempatBongkar;
    private String kodJenisPengenalanPemunya;
    private boolean stageLucutHak = Boolean.FALSE;
    private boolean stageRujukMahkamah = Boolean.FALSE;
    private AduanOrangKenaSyak oks;
    private String chooseOKS;

    @DefaultHandler
    public Resolution showForm() {
        if (opFlag == false) {
            addSimpleError("Sila isikan maklumat laporan operasi terlebih dahulu");
        }

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            }
        }

        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");

    }

    public Resolution showForm2() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
            }
        }
        return new JSP("penguatkuasaan/maklumat_barang_tahanan_view.jsp").addParameter("tab", "true");
    }

    public Resolution hakMilikPopup() {
        idOp = getContext().getRequest().getParameter("idOp");
        return new JSP("penguatkuasaan/popup_barang_tahanan.jsp").addParameter("popup", "true");
    }

    public Resolution viewBarangDetail() {
        return new JSP("penguatkuasaan/view_barang_detail.jsp").addParameter("popup", "true");
    }

    public Resolution showForm3() {
        return new JSP("penguatkuasaan/popup_barang_tahanan.jsp").addParameter("tab", "true");
    }

    public Resolution editPopupNotisSita() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_notis_sita.jsp").addParameter("popup", "true");
    }

    public Resolution viewPopupNotisSita() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_notis_sita.jsp").addParameter("popup", "true");
    }

    public Resolution notisSita() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("multipleOp", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        }
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
//        return new JSP("penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
    }

    public Resolution notisSita2() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("multipleOp", Boolean.FALSE);
                getContext().getRequest().setAttribute("view", Boolean.TRUE);
            }
        }
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
//        return new JSP("penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
    }

    public Resolution addRecord() {
        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);
        return new JSP("penguatkuasaan/popup_barang_tahanan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-------------rehydrate------------------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idOp = getContext().getRequest().getParameter("idOp");

        kodNegeriPermohonan = conf.getProperty("kodNegeri");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {
                    listOp = enforceService.findListLaporanOperasi(idPermohonan);
                    System.out.println("listOp size : " + listOp.size());
                    if (listOp.size() != 0) {
                        System.out.println("opFlag true");
                        opFlag = true;
                        System.out.println("opFlag ---- : " + opFlag);
                    } else {
                        if (permohonan.getPermohonanSebelum() != null) {
                            listOp = enforceService.findListLaporanOperasi(permohonan.getPermohonanSebelum().getIdPermohonan());
                            opFlag = true;
                            System.out.println("opFlag ---- : " + opFlag);

                            senaraiOksIp = enforceService.getListAduanOrangkenaSyak(idPermohonan);
                            logger.info("size senaraiOksIp : " + senaraiOksIp.size());

                            if (senaraiOksIp.size() != 0) {
                                Long idOpIP = senaraiOksIp.get(0).getOperasiPenguatkuasaan().getIdOperasi();
                                logger.info("id operasi : " + idOpIP);
                                listOp = enforcementService.findListLaporanOperasi(idOpIP);
                                statusIP = true;

                            }
                        }
                    }

                    if (listOp.size() == 0) {
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
                            listOp = enforceService.findListLaporanOperasi(idPermohonanAsal);
                            logger.info("size senaraiOperasiPenguatkuasaan : " + listOp.size());
                        }
                    }
                } else {
                    //for Melaka, only pass idPermohonan
                    operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
                }

            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
            if (operasiPenguatkuasaan != null) {
                opFlag = true;
                senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
                listKenderaan = enforceService.findListKenderaan(operasiPenguatkuasaan.getIdOperasi());
                logger.info("::Total Kenderaan : " + listKenderaan.size());

            }

            senaraiOKS = enforceService.findByID(idPermohonan);
//            else {
//                opFlag = false;
//            }
            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task task = null;
                task = service.getTaskFromBPel(taskId, pengguna);
                if (task != null) {
                    stageId = task.getSystemAttributes().getStage();
                }
            } else {
                stageId = "syor_pelupusan";
            }

            logger.info("-------------rehydrate : stageId : ------------------" + stageId);
        }
        idBarang = getContext().getRequest().getParameter("idBarang");
        logger.info("-------------rehydrate : idBarang : ------------------" + idBarang);
        if (idBarang != null) {
            barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
            if (barangRampasan != null) {

                //get same variable for kenderaan & bukan kenderaan
                item = barangRampasan.getItem();
                kodKategoriItemRampasan = barangRampasan.getKodKategoriItemRampasan().getKod();
                if (barangRampasan.getAduanOrangKenaSyak() != null) {
                    //for suspek
                    namaSuspek = Long.toString(barangRampasan.getAduanOrangKenaSyak().getIdOrangKenaSyak());
                    noPengenalanSuspek = barangRampasan.getNoPengenalanSuspek();
                    noTelSuspek = barangRampasan.getNoTelSuspek();
                    alamat1Suspek = barangRampasan.getAlamat1Suspek();
                    alamat2Suspek = barangRampasan.getAlamat2Suspek();
                    alamat3Suspek = barangRampasan.getAlamat3Suspek();
                    poskodSuspek = barangRampasan.getPoskodSuspek();
                    namaSuspekTemp = barangRampasan.getAduanOrangKenaSyak().getNama();

                    if (barangRampasan.getAduanOrangKenaSyak().getAlamat() != null) {
                        if (barangRampasan.getAduanOrangKenaSyak().getAlamat().getNegeri() != null) {
                            if (StringUtils.isNotBlank(barangRampasan.getAduanOrangKenaSyak().getAlamat().getNegeri().getNama())) {
                                kodNegeriSuspek = barangRampasan.getAduanOrangKenaSyak().getAlamat().getNegeri().getKod();
                            }
                        }

                    }
                }

                namaPemunya = barangRampasan.getNamaPemunya();
                noPengenalanPemunya = barangRampasan.getNoPengenalanPemunya();
                noTelPemunya = barangRampasan.getNoTelPemunya();
                alamat1 = barangRampasan.getAlamat1();
                alamat2 = barangRampasan.getAlamat2();
                alamat3 = barangRampasan.getAlamat3();
                alamat4 = barangRampasan.getAlamat4();
                poskod = barangRampasan.getPoskod();
                if (barangRampasan.getKodJenisPengenalanPemunya() != null) {
                    kodJenisPengenalanPemunya = barangRampasan.getKodJenisPengenalanPemunya().getKod();
                }
                tempatBongkar = barangRampasan.getTempatBongkar();


                try {
                    if (barangRampasan.getTarikhRampas() != null) {
                        tarikhRampas = sdf.format(barangRampasan.getTarikhRampas());
                        tarikhRampas = sdf1.format(barangRampasan.getTarikhRampas()).substring(0, 10);
                        jam = sdf1.format(barangRampasan.getTarikhRampas()).substring(11, 13);
                        minit = sdf1.format(barangRampasan.getTarikhRampas()).substring(14, 16);
                        ampm = sdf1.format(barangRampasan.getTarikhRampas()).substring(17, 19);
                    }
                    if (barangRampasan.getStatus() != null) {
                        barangRampasanStatus = barangRampasan.getStatus().getKod();
                    }
                    if (barangRampasan.getKodNegeri() != null) {
                        //kod negeri pemunya
                        kn = barangRampasan.getKodNegeri().getKod();
                        kodNegeriPemunyaTemp = barangRampasan.getKodNegeri().getNama();
                    }

                } catch (Exception e) {
                    logger.error(e);
//                        e.printStackTrace();
                }

                tempatSimpanan = barangRampasan.getTempatSimpanan();
                tempatPengambilan = barangRampasan.getTempatPengambilan();
                tempatPenghantaran = barangRampasan.getTempatPenghantaran();
                catatan = barangRampasan.getCatatan();
                tempatTangkap = barangRampasan.getTempatTangkap();

                if ("B".equals(barangRampasan.getKodKategoriItemRampasan().getKod())) {
                    logger.info("-------------rehydrate/bukanKenderaan------------------");

                    kuantiti = barangRampasan.getKuantiti();
                    kuantitiUnit = barangRampasan.getKuantitiUnit();


                } else {
                    logger.info("-------------rehydrate/Kenderaan------------------");
                    try {

                        if (barangRampasan.getKodBahanBakar() != null) {
                            kodBahanBakar = barangRampasan.getKodBahanBakar().getKod();
                        }

                        if (barangRampasan.getKodKegunaanKenderaan() != null) {
                            kkkk = barangRampasan.getKodKegunaanKenderaan().getKod();
                        }
                        if (barangRampasan.getKodJenisBadanKenderaan() != null) {
                            kodjbk = barangRampasan.getKodJenisBadanKenderaan().getKod();
                        }
                        if (barangRampasan.getKodJenisPengenalanPemegangPermit() != null) {
                            jppermit = barangRampasan.getKodJenisPengenalanPemegangPermit().getKod();
                        }

                        if (barangRampasan.getKodNegeriPemegangPermit() != null) {
                            knpermit = barangRampasan.getKodNegeriPemegangPermit().getKod();
                        }

                    } catch (Exception e) {
                        logger.error(e);
//                        e.printStackTrace();
                    }


                    alamat1PemegangPermit = barangRampasan.getAlamat1PemegangPermit();
                    alamat2PemegangPermit = barangRampasan.getAlamat2PemegangPermit();
                    alamat3PemegangPermit = barangRampasan.getAlamat3PemegangPermit();
                    alamat4PemegangPermit = barangRampasan.getAlamat4PemegangPermit();
                    poskodPemegangPermit = barangRampasan.getPoskodPemegangPermit();
                    nomborPengenalanPemegangPermit = barangRampasan.getNomborPengenalanPemegangPermit();
                    noSyarikat = barangRampasan.getNoSyarikat();
                    pemegangPermit = barangRampasan.getPemegangPermit();
                    nomborPendaftaran = barangRampasan.getNomborPendaftaran();
                    nomborEnjin = barangRampasan.getNomborEnjin();
                    nomborCasis = barangRampasan.getNomborCasis();
                    buatan = barangRampasan.getBuatan();
                    namaModel = barangRampasan.getNamaModel();
                    kapasitiEnjin = barangRampasan.getKapasitiEnjin();
                    warna = barangRampasan.getWarna();
                    tahunDibuat = barangRampasan.getTahunDibuat();
                    muatanTempatDuduk = barangRampasan.getMuatanTempatDuduk();
                    kadarLesen = barangRampasan.getKadarLesen();
                    tarikhPendaftaran = barangRampasan.getTarikhPendaftaran();
                    statusPemunya = barangRampasan.getStatusPemunya();
                    dilepaskanOleh = barangRampasan.getDilepaskanOleh();
                    jenisBatuan = barangRampasan.getJenisBatuan();
                    noSiri = barangRampasan.getNoSiri();

                }

                //same variable for kenderaan & bukan kenderaan

                penjaminBarangRampasan = enforceService.findPenjaminBarangRampasanByIdBarang(barangRampasan.getIdBarang());
                if (penjaminBarangRampasan != null) {

                    namaPenjamin = penjaminBarangRampasan.getNama();
                    try {
                        if (penjaminBarangRampasan.getJenisPengenalan() != null) {
                            kp = penjaminBarangRampasan.getJenisPengenalan().getKod();
                        }
                        if (penjaminBarangRampasan.getNegeri() != null) {
                            np = penjaminBarangRampasan.getNegeri().getKod();
                        }
                        if (penjaminBarangRampasan.getKodJantina() != null) {
                            kj = penjaminBarangRampasan.getKodJantina().getKod();
                        }

                    } catch (Exception e) {
                        logger.error(e);
//                            e.printStackTrace();
                    }


                    noPengenalanPenjamin = penjaminBarangRampasan.getNoPengenalan();
                    alamat1Penjamin = penjaminBarangRampasan.getAlamat1();
                    alamat2Penjamin = penjaminBarangRampasan.getAlamat2();
                    alamat3Penjamin = penjaminBarangRampasan.getAlamat3();
                    alamat4Penjamin = penjaminBarangRampasan.getAlamat4();
                    poskodPenjamin = penjaminBarangRampasan.getPoskod();
                    noTelBimbitPenjamin = penjaminBarangRampasan.getNoTelefonBimbit();
                    noTelPenjamin = penjaminBarangRampasan.getNoTelefon();
                    hubungan = penjaminBarangRampasan.getHubungan();
                }
                if (barangRampasan.getImej() != null) {
                    idDokumen = barangRampasan.getImej().getIdDokumen();
                }
                if (barangRampasan.getKesalahan() != null) {
                    kesalahan = barangRampasan.getKesalahan();
                }
            }

            if (barangRampasan.getOperasiAgensi() != null) {
                idOperasiAgensi = Long.toString(barangRampasan.getOperasiAgensi().getIdOperasiAgensi());
                OperasiAgensi oa = operasiAgensiDAO.findById(Long.parseLong(idOperasiAgensi));
                if (oa != null) {
                    noRujukan = oa.getNoRujukan();
                }
            } else {
                idOperasiAgensi = "T";
            }
        }

        /*to get from penjamin barang rampas*/
        senaraiKodStatus = enforceService.getSenaraiKodStatusBarangRampasan();

        /*to get from List Laporan Polis*/
        if (StringUtils.isNotEmpty(idOp)) {
            senaraiOperasiAgensi = enforceService.getSenaraiOperasiAgensi(Long.parseLong(idOp));
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            /* To update status to ST (sudah dituntut) after kompaun have been paid (update table op_brg_rampas) */
            Session s = sessionProvider.get();
            Transaction tx = s.beginTransaction();

            senaraiOperasiAgensi = enforceService.getSenaraiOperasiAgensi(operasiPenguatkuasaan.getIdOperasi());

            senaraiKompaun = enforcementService.findKompaunByID(idPermohonan);

            try {

                for (int i = 0; i < senaraiKompaun.size(); i++) {
                    Kompaun kompaun = senaraiKompaun.get(i);
//                if (kompaun.getResit() == null) {
                    PermohonanTuntutanBayar ptb = enforceService.findMohonTuntutBayar(kompaun.getPermohonanTuntutanKos().getIdKos());
                    if (ptb != null) {
                        kompaun.setResit(ptb.getDokumenKewangan());
                        kompaunDAO.save(kompaun);

                        /* Start to update table op_brg_rampas */
                        logger.info("to update status at barang rampasan to ST");
                        for (int j = 0; j < senaraiBarangRampasan.size(); j++) {
                            BarangRampasan barangRampas = senaraiBarangRampasan.get(j);
                            barangRampasan = barangRampasanDAO.findById(barangRampas.getIdBarang());
                            KodStatusBarangRampasan ks = new KodStatusBarangRampasan();
                            ks.setKod("ST");
                            barangRampasan.setStatus(ks);
                            InfoAudit ia = barangRampasan.getInfoAudit();
                            ia.setDiKemaskiniOleh(pengguna);
                            ia.setTarikhKemaskini(new java.util.Date());
                            barangRampasan.setInfoAudit(ia);
                            barangRampasanDAO.update(barangRampasan);
                        }
                    }

                }

                tx.commit();

            } catch (Exception e) {
                tx.rollback();
                Throwable t = e;
                // getting the root cause
                while (t.getCause() != null) {
                    t = t.getCause();
                }
                t.printStackTrace();
                addSimpleError(t.getMessage());
            }




        }

        //checking stage id : for new id permohonan created after made decision lucuthak or rujuk mahkamah

        //1- Lucuthak
        ArrayList<String> senaraiStageTerlibatLucutHak = new ArrayList<String>();
        senaraiStageTerlibatLucutHak.add("syor_pelupusan");
        senaraiStageTerlibatLucutHak.add("semak_pelupusan");
        senaraiStageTerlibatLucutHak.add("arah_pelupusan");
        senaraiStageTerlibatLucutHak.add("maklum_arahan_pelupusan");
        senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan");
        senaraiStageTerlibatLucutHak.add("kemasukan_pelupusan");
        senaraiStageTerlibatLucutHak.add("maklum_keputusan_pelupusan");
        //for sek 425 & 425A
        senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan1");
        senaraiStageTerlibatLucutHak.add("terima_arahan_pelupusan2");
        senaraiStageTerlibatLucutHak.add("sedia_rekod_pelupusan");
        senaraiStageTerlibatLucutHak.add("maklum_kpsn_pelupusan");

        for (Object kod : senaraiStageTerlibatLucutHak) {
            if (kod.toString().equalsIgnoreCase(stageId)) {
                stageLucutHak = true;
            }
        }

        //2- Rujuk Mahkamah
        ArrayList<String> senaraiStageTerlibatRujukMahkamah = new ArrayList<String>();
        senaraiStageTerlibatRujukMahkamah.add("surat_tuntut_brg_rampasan");
        senaraiStageTerlibatRujukMahkamah.add("rujuk_pelepasan_sementara");
        senaraiStageTerlibatRujukMahkamah.add("buka_ks_brg_rampas");
        senaraiStageTerlibatRujukMahkamah.add("peraku_srt_brg_rampas");
        senaraiStageTerlibatRujukMahkamah.add("kpsn_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("kemaskini_kpsn_mahkamah2");
        senaraiStageTerlibatRujukMahkamah.add("laksana_perintah_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("terima_lepas_barangan");
        senaraiStageTerlibatRujukMahkamah.add("sedia_srt_serahan");
        senaraiStageTerlibatRujukMahkamah.add("periksa_brg_lepas");
        senaraiStageTerlibatRujukMahkamah.add("imbas_kemaskini_brg");
        //for sek 425 & 425A
        senaraiStageTerlibatRujukMahkamah.add("imbas_srt_rampasan");
        senaraiStageTerlibatRujukMahkamah.add("rujuk_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("cetak_afidavit");
        senaraiStageTerlibatRujukMahkamah.add("sahkan_srt_iringan");
        senaraiStageTerlibatRujukMahkamah.add("imbas_kpsn_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("masuk_kpsn_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("kpsn_mahkamah");
        senaraiStageTerlibatRujukMahkamah.add("arah_lepas_rampasan");
        senaraiStageTerlibatRujukMahkamah.add("terima_arahan_pelepasan");
        senaraiStageTerlibatRujukMahkamah.add("sedia_srt_pelepasan");
        senaraiStageTerlibatRujukMahkamah.add("imbas_dokumen");
        senaraiStageTerlibatRujukMahkamah.add("kemaskini_inventori");


        for (Object kod : senaraiStageTerlibatRujukMahkamah) {
            if (kod.toString().equalsIgnoreCase(stageId)) {
                stageRujukMahkamah = true;
            }
        }
        logger.info(":::::: stageLucutHak:::::: " + stageLucutHak);
        logger.info(":::::: stageRujukMahkamah:::::: " + stageRujukMahkamah);


    }

    public Resolution editBarangRampasan() {
        getContext().getRequest().setAttribute("pop", Boolean.TRUE);
        idBarang = getContext().getRequest().getParameter("idBarang");
        barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
        penjaminBarangRampasan = penjaminBarangRampasanDAO.findById(Long.parseLong(idBarang));

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::add record - id OP : " + idOp);

        return new JSP("penguatkuasaan/popup_edit_barang_tahanan.jsp").addParameter("popup", "true");
    }

    /* simpan maklumat barang rampasan */
    public Resolution simpan() {
        logger.info("-------------simpanBarangTahanan------------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new java.util.Date());

        idOp = getContext().getRequest().getParameter("idOp");
        idOperasiAgensi = getContext().getRequest().getParameter("idOperasiAgensi");
        chooseOKS = getContext().getRequest().getParameter("chooseOKS");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999")) {

                logger.info("::simpan record - id OP : " + idOp);
                operasiPenguatkuasaan = enforceService.findByIdOp(Long.parseLong(idOp));
            } else {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            }

        } else {
            //for N9, pass idPermohonan & kategoriTindakan
            operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
        }
        if (!"05".equals(conf.getProperty("kodNegeri"))) {
            if (!idOperasiAgensi.equalsIgnoreCase("T")) {
                operasiAgensi = enforceService.findByIdOperasiAgensi(Long.parseLong(idOperasiAgensi));
            }
        }

        //same variable for bukan kenderaan & kenderaan
        barangRampasan = new BarangRampasan();
        barangRampasan.setItem(item);
        barangRampasan.setTempatSimpanan(tempatSimpanan);
        barangRampasan.setTempatPengambilan(tempatPengambilan);
        barangRampasan.setTempatPenghantaran(tempatPenghantaran);
        barangRampasan.setCatatan(catatan);
        barangRampasan.setTempatTangkap(tempatTangkap);

        barangRampasan.setOperasi(operasiPenguatkuasaan);
        barangRampasan.setOperasiAgensi(operasiAgensi);
        barangRampasan.setInfoAudit(ia);
        barangRampasan.setCawangan(peng.getKodCawangan());
        barangRampasan.setDilepaskanOleh(dilepaskanOleh);

        barangRampasan.setNamaPemunya(namaPemunya);
        barangRampasan.setNoPengenalanPemunya(noPengenalanPemunya);
        barangRampasan.setNoTelPemunya(noTelPemunya);
        barangRampasan.setAlamat1(alamat1);
        barangRampasan.setAlamat2(alamat2);
        barangRampasan.setAlamat3(alamat3);
        barangRampasan.setAlamat4(alamat4);
        barangRampasan.setPoskod(poskod);

        if (StringUtils.isNotBlank(chooseOKS)) {
            if (chooseOKS.equalsIgnoreCase("L")) {
                barangRampasan.setStatusKesalahan("0");
            } else {
                barangRampasan.setStatusKesalahan("1");
            }
        }

        if (kodJenisPengenalanPemunya != null) {
            KodJenisPengenalan kjp1 = new KodJenisPengenalan();
            kjp1.setKod(kodJenisPengenalanPemunya);
            barangRampasan.setKodJenisPengenalanPemunya(kjp1);
        }

        barangRampasan.setTempatBongkar(tempatBongkar);

        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        Dokumen d = new Dokumen();
        KodDokumen kodDokumen = kodDokumenDAO.findById("IB");
        d.setInfoAudit(ia);
        d.setTajuk(kodDokumen.getNama());
        d.setNoVersi("1.0");
        d.setKodDokumen(kodDokumen);
        d.setKlasifikasi(klasifikasiAm);
        enforceService.saveDokumenNotis(d);
        barangRampasan.setImej(d);

        try {
            if (kodKategoriItemRampasan != null) {
                KodKategoriItemRampasan kit = new KodKategoriItemRampasan();
                kit.setKod(kodKategoriItemRampasan);
                barangRampasan.setKodKategoriItemRampasan(kit);
            }

            if (tarikhRampas != null) {
                tarikhRampas = tarikhRampas + " " + jam + ":" + minit + " " + ampm;
                barangRampasan.setTarikhRampas(sdf1.parse(tarikhRampas));
            }
            if (kn != null) {
                //kod negeri for pemunya
                KodNegeri kn1 = new KodNegeri();
                kn1.setKod(kn);
                barangRampasan.setKodNegeri(kn1);
            }

            if (barangRampasanStatus != null) {
                KodStatusBarangRampasan ks = new KodStatusBarangRampasan();
                ks.setKod(barangRampasanStatus);
                barangRampasan.setStatus(ks);
            }

            if (StringUtils.isNotBlank(idOperasiAgensi)) {
                if (!idOperasiAgensi.equalsIgnoreCase("T")) {
                    OperasiAgensi oa = operasiAgensiDAO.findById(Long.parseLong(idOperasiAgensi));
                    barangRampasan.setOperasiAgensi(oa);
                } else {
                    barangRampasan.setOperasiAgensi(null);
                }
            }



            if (namaSuspek != null && StringUtils.isNotBlank(namaSuspek)) { //nama suspek will have value of id_oks
                System.out.println("nama namaSuspek : " + namaSuspek);
                AduanOrangKenaSyak oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(namaSuspek));

                barangRampasan.setAduanOrangKenaSyak(oks); //set id_oks based on selected pemunya
                barangRampasan.setNamaSuspek(oks.getNama());
                barangRampasan.setNoPengenalanSuspek(noPengenalanSuspek);
                barangRampasan.setNoTelSuspek(noTelSuspek);
                barangRampasan.setAlamat1Suspek(alamat1Suspek);
                barangRampasan.setAlamat2Suspek(alamat2Suspek);
                barangRampasan.setAlamat3Suspek(alamat3Suspek);
                barangRampasan.setPoskodSuspek(poskodSuspek);

            }


        } catch (Exception ex) {
            logger.error(ex);
        }

        if ("B".equals(kodKategoriItemRampasan)) {
            logger.info("-------------BukanKenderaan------------------");
            barangRampasan.setKuantiti(kuantiti);
            barangRampasan.setKuantitiUnit(kuantitiUnit);

        } else {
            logger.info("-------------Kenderaan------------------");

            barangRampasan.setNomborPendaftaran(nomborPendaftaran);
            barangRampasan.setNamaPemandu(namaPemandu);
            barangRampasan.setNomborEnjin(nomborEnjin);
            barangRampasan.setNomborCasis(nomborCasis);
            barangRampasan.setBuatan(buatan);
            barangRampasan.setNamaModel(namaModel);
            barangRampasan.setKapasitiEnjin(kapasitiEnjin);
            barangRampasan.setWarna(warna);
            barangRampasan.setTahunDibuat(tahunDibuat);
            barangRampasan.setTarikhPendaftaran(tarikhPendaftaran);
            barangRampasan.setStatusPemunya(statusPemunya);
            barangRampasan.setMuatanTempatDuduk(muatanTempatDuduk);
            barangRampasan.setKadarLesen(kadarLesen);
            barangRampasan.setNoSyarikat(noSyarikat);
            barangRampasan.setPemegangPermit(pemegangPermit);
            barangRampasan.setNomborPengenalanPemegangPermit(nomborPengenalanPemegangPermit);
            barangRampasan.setAlamat1PemegangPermit(alamat1PemegangPermit);
            barangRampasan.setAlamat2PemegangPermit(alamat2PemegangPermit);
            barangRampasan.setAlamat3PemegangPermit(alamat3PemegangPermit);
            barangRampasan.setAlamat4PemegangPermit(alamat4PemegangPermit);
            barangRampasan.setPoskodPemegangPermit(poskodPemegangPermit);
            barangRampasan.setJenisBatuan(jenisBatuan);
            barangRampasan.setNoSiri(noSiri);

            if (kbbb != null) {
                KodBahanBakar kbb1 = new KodBahanBakar();
                kbb1.setKod(kbbb);
                barangRampasan.setKodBahanBakar(kbb1);
            }

            if (kodjbk != null) {
                KodJenisBadanKenderaan kjbk1 = new KodJenisBadanKenderaan();
                kjbk1.setKod(kodjbk);
                barangRampasan.setKodJenisBadanKenderaan(kjbk1);
            }

            if (kkkk != null) {
                KodKegunaanKenderaan kkk1 = new KodKegunaanKenderaan();
                kkk1.setKod(kkkk);
                barangRampasan.setKodKegunaanKenderaan(kkk1);
            }

            if (jppermit != null) {
                KodJenisPengenalan kjpermit = new KodJenisPengenalan();
                kjpermit.setKod(jppermit);
                barangRampasan.setKodJenisPengenalanPemegangPermit(kjpermit);
            }

            if (knpermit != null) {
                KodNegeri knpermit1 = new KodNegeri();
                knpermit1.setKod(knpermit);
                barangRampasan.setKodNegeriPemegangPermit(knpermit1);
            }
        }


        enforceService.simpanBarangRampasan(barangRampasan);


        logger.info("::save maklumat penjamin");
        penjaminBarangRampasan = new PenjaminBarangRampasan();
        penjaminBarangRampasan.setInfoAudit(ia);
        penjaminBarangRampasan.setNama(namaPenjamin);
        penjaminBarangRampasan.setBarangRampasan(barangRampasan);
        penjaminBarangRampasan.setNoPengenalan(noPengenalanPenjamin);
        penjaminBarangRampasan.setAlamat1(alamat1Penjamin);
        penjaminBarangRampasan.setAlamat2(alamat2Penjamin);
        penjaminBarangRampasan.setAlamat3(alamat3Penjamin);
        penjaminBarangRampasan.setAlamat4(alamat4Penjamin);
        penjaminBarangRampasan.setPoskod(poskodPenjamin);
        penjaminBarangRampasan.setCawangan(peng.getKodCawangan());
        penjaminBarangRampasan.setNoTelefon(noTelPenjamin);
        penjaminBarangRampasan.setNoTelefonBimbit(noTelBimbitPenjamin);
        penjaminBarangRampasan.setHubungan(hubungan);
        penjaminBarangRampasan.setBarangRampasan(barangRampasan);

        if (kp != null) {
            KodJenisPengenalan kjp1 = new KodJenisPengenalan();
            kjp1.setKod(kp);
            penjaminBarangRampasan.setJenisPengenalan(kjp1);
        }

        if (np != null) {
            KodNegeri kn1 = new KodNegeri();
            kn1.setKod(np);
            penjaminBarangRampasan.setNegeri(kn1);
        }

        if (kj != null) {
            KodJantina kj1 = new KodJantina();
            kj1.setKod(kj);
            penjaminBarangRampasan.setKodJantina(kj1);
        }

        enforceService.simpanMaklumatPenjaminBrgRampas(penjaminBarangRampasan);

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if ("K".equals(barangRampasan.getKodKategoriItemRampasan().getKod())) {
                //generateNotisKesalahan(permohonan);
            }
        }

        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : add");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
    }

    public Resolution editBarang() {
        logger.info("-------------editBrgTahanan------------------");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idBarang = getContext().getRequest().getParameter("idBarang");
        barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));

        idOp = getContext().getRequest().getParameter("idOp");
        logger.info("::edit record - id OP : " + idOp);

        InfoAudit ia = barangRampasan.getInfoAudit();
        ia.setDiKemaskiniOleh(peng);
        ia.setTarikhKemaskini(new java.util.Date());


        //same variable for kenderaan & bukan kenderaan
        barangRampasan.setItem(item);
        barangRampasan.setInfoAudit(ia);
        barangRampasan.setTempatSimpanan(tempatSimpanan);
        barangRampasan.setTempatPengambilan(tempatPengambilan);
        barangRampasan.setTempatPenghantaran(tempatPenghantaran);
        barangRampasan.setCatatan(catatan);
        barangRampasan.setTempatTangkap(tempatTangkap);

        barangRampasan.setNamaPemunya(namaPemunya);
        barangRampasan.setNoPengenalanPemunya(noPengenalanPemunya);
        barangRampasan.setNoTelPemunya(noTelPemunya);

        if (kodJenisPengenalanPemunya != null) {
            KodJenisPengenalan kjp1 = new KodJenisPengenalan();
            kjp1.setKod(kodJenisPengenalanPemunya);
            barangRampasan.setKodJenisPengenalanPemunya(kjp1);
        }

        if (StringUtils.isNotBlank(chooseOKS)) {
            if (chooseOKS.equalsIgnoreCase("L")) {
                barangRampasan.setStatusKesalahan("0");
            } else {
                barangRampasan.setStatusKesalahan("1");
            }
        }
        barangRampasan.setAlamat1(alamat1);
        barangRampasan.setAlamat2(alamat2);
        barangRampasan.setAlamat3(alamat3);
        barangRampasan.setAlamat4(alamat4);
        barangRampasan.setPoskod(poskod);
        barangRampasan.setTempatBongkar(tempatBongkar);


        try {

            if (kodKategoriItemRampasan != null) {
                KodKategoriItemRampasan kit = new KodKategoriItemRampasan();
                kit.setKod(kodKategoriItemRampasan);
                barangRampasan.setKodKategoriItemRampasan(kit);
            }

            if (kn != null) {
                KodNegeri kn1 = new KodNegeri();
                kn1.setKod(kn);
                barangRampasan.setKodNegeri(kn1);
            }

            if (tarikhRampas != null) {
                tarikhRampas = tarikhRampas + " " + jam + ":" + minit + " " + ampm;
                barangRampasan.setTarikhRampas(sdf1.parse(tarikhRampas));
            }

            if (barangRampasanStatus != null) {
                KodStatusBarangRampasan ks = new KodStatusBarangRampasan();
                ks.setKod(barangRampasanStatus);
                barangRampasan.setStatus(ks);
            }

            if (StringUtils.isNotBlank(idOperasiAgensi)) {
                if (!idOperasiAgensi.equalsIgnoreCase("T")) {
                    OperasiAgensi oa = operasiAgensiDAO.findById(Long.parseLong(idOperasiAgensi));
                    barangRampasan.setOperasiAgensi(oa);
                } else {
                    barangRampasan.setOperasiAgensi(null);
                }
            }

            if (namaSuspek != null && StringUtils.isNotBlank(namaSuspek)) { //nama suspek will have value of id_oks
                System.out.println("nama namaSuspek : " + namaSuspek);
                AduanOrangKenaSyak oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(namaSuspek));

                barangRampasan.setAduanOrangKenaSyak(oks); //set id_oks based on selected pemunya
                barangRampasan.setNamaSuspek(oks.getNama());
                barangRampasan.setNoPengenalanSuspek(noPengenalanSuspek);
                barangRampasan.setNoTelSuspek(noTelSuspek);
                barangRampasan.setAlamat1Suspek(alamat1Suspek);
                barangRampasan.setAlamat2Suspek(alamat2Suspek);
                barangRampasan.setAlamat3Suspek(alamat3Suspek);
                barangRampasan.setPoskodSuspek(poskodSuspek);

            }


        } catch (Exception ex) {
            logger.error(ex);
        }


        logger.info("-------------editBrgTahanan--bukan kenderaan------------------");
        if ("B".equals(barangRampasan.getKodKategoriItemRampasan().getKod())) {
            barangRampasan.setKuantiti(kuantiti);
            barangRampasan.setKuantitiUnit(kuantitiUnit);

        } else {
            logger.info("-------------editBrgTahanan--kenderaan------------------");
            barangRampasan.setNomborPendaftaran(nomborPendaftaran);
            barangRampasan.setNamaPemandu(namaPemandu);
            barangRampasan.setNomborEnjin(nomborEnjin);
            barangRampasan.setNomborCasis(nomborCasis);
            barangRampasan.setBuatan(buatan);
            barangRampasan.setNamaModel(namaModel);
            barangRampasan.setKapasitiEnjin(kapasitiEnjin);
            barangRampasan.setWarna(warna);
            barangRampasan.setTahunDibuat(tahunDibuat);
            barangRampasan.setStatusPemunya(statusPemunya);
            barangRampasan.setTarikhPendaftaran(tarikhPendaftaran);
            barangRampasan.setMuatanTempatDuduk(muatanTempatDuduk);
            barangRampasan.setKadarLesen(kadarLesen);
            barangRampasan.setNoSyarikat(noSyarikat);
            barangRampasan.setPemegangPermit(pemegangPermit);
            barangRampasan.setNomborPengenalanPemegangPermit(nomborPengenalanPemegangPermit);
            barangRampasan.setAlamat1PemegangPermit(alamat1PemegangPermit);
            barangRampasan.setAlamat2PemegangPermit(alamat2PemegangPermit);
            barangRampasan.setAlamat3PemegangPermit(alamat3PemegangPermit);
            barangRampasan.setAlamat4PemegangPermit(alamat4PemegangPermit);
            barangRampasan.setPoskodPemegangPermit(poskodPemegangPermit);
            barangRampasan.setJenisBatuan(jenisBatuan);
            barangRampasan.setNoSiri(noSiri);

            try {
                if (kodBahanBakar != null) {
                    KodBahanBakar kbb = new KodBahanBakar();
                    kbb.setKod(kodBahanBakar);
                    barangRampasan.setKodBahanBakar(kbb);
                }

                if (kkkk != null) {
                    KodKegunaanKenderaan kkk = new KodKegunaanKenderaan();
                    kkk.setKod(kkkk);
                    barangRampasan.setKodKegunaanKenderaan(kkk);
                }

                if (kodjbk != null) {
                    KodJenisBadanKenderaan kjbk = new KodJenisBadanKenderaan();
                    kjbk.setKod(kodjbk);
                    barangRampasan.setKodJenisBadanKenderaan(kjbk);
                }
                if (jppermit != null) {
                    KodJenisPengenalan kjpermit1 = new KodJenisPengenalan();
                    kjpermit1.setKod(jppermit);
                    barangRampasan.setKodJenisPengenalanPemegangPermit(kjpermit1);
                }

                if (knpermit != null) {
                    KodNegeri knpermit1 = new KodNegeri();
                    knpermit1.setKod(knpermit);
                    barangRampasan.setKodNegeriPemegangPermit(knpermit1);
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }

        enforceService.updateBarangRampasan(barangRampasan);

        //update maklumat penjamin for bukan kenderaan


        penjaminBarangRampasan = enforceService.findPenjaminBarangRampasanByIdBarang(barangRampasan.getIdBarang());

        InfoAudit infoAudit = penjaminBarangRampasan.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(peng);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        penjaminBarangRampasan.setNama(namaPenjamin);
        penjaminBarangRampasan.setBarangRampasan(barangRampasan);
        penjaminBarangRampasan.setNoPengenalan(noPengenalanPenjamin);
        penjaminBarangRampasan.setAlamat1(alamat1Penjamin);
        penjaminBarangRampasan.setAlamat2(alamat2Penjamin);
        penjaminBarangRampasan.setAlamat3(alamat3Penjamin);
        penjaminBarangRampasan.setAlamat4(alamat4Penjamin);
        penjaminBarangRampasan.setPoskod(poskodPenjamin);

        try {
            if (kp != null) {
                KodJenisPengenalan kjp1 = new KodJenisPengenalan();
                kjp1.setKod(kp);
                penjaminBarangRampasan.setJenisPengenalan(kjp1);
            }

            if (np != null) {
                KodNegeri kn1 = new KodNegeri();
                kn1.setKod(np);
                penjaminBarangRampasan.setNegeri(kn1);
            }

            if (kj != null) {
                KodJantina kj1 = new KodJantina();
                kj1.setKod(kj);
                penjaminBarangRampasan.setKodJantina(kj1);
            }
        } catch (Exception ex) {
            logger.error(ex);
        }


        penjaminBarangRampasan.setCawangan(peng.getKodCawangan());
        penjaminBarangRampasan.setNoTelefon(noTelPenjamin);
        penjaminBarangRampasan.setNoTelefonBimbit(noTelBimbitPenjamin);
        penjaminBarangRampasan.setHubungan(hubungan);
        penjaminBarangRampasan.setBarangRampasan(barangRampasan);
        penjaminBarangRampasan.setInfoAudit(infoAudit);

        enforceService.updatePenjaminBarangRampasan(penjaminBarangRampasan);

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if ("K".equals(barangRampasan.getKodKategoriItemRampasan().getKod())) {
                //generateNotisKesalahan(permohonan);
            }
        }

        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : edit");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteBarangRampasan() {
        logger.info("-------------deleteBarangTahanan------------------");

        idBarang = getContext().getRequest().getParameter("idBarang");
        barangRampasan = enforceService.findBarangRampasanByIdBarang(Long.parseLong(idBarang));
        Boolean jenisKenderaan = Boolean.FALSE;

        if (barangRampasan != null) {
            logger.info("::idOp (get from barang rampasan): " + barangRampasan.getOperasi().getIdOperasi());
            idOp = Long.toString(barangRampasan.getOperasi().getIdOperasi());
            penjaminBarangRampasan = enforceService.findPenjaminBarangRampasanByIdBarang(barangRampasan.getIdBarang());
            if (penjaminBarangRampasan != null) {
                enforceService.deletePenjaminBarangRampasan(penjaminBarangRampasan);
            }

            if (barangRampasan.getKodKategoriItemRampasan() != null) {
                if (barangRampasan.getKodKategoriItemRampasan().getKod().equalsIgnoreCase("K")) {
                    jenisKenderaan = true;
                }
            }
            enforceService.deleteBarangRampasan(barangRampasan);
        }

        //delete report notis kesalahan if all kenderaan had been deleted
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            listKenderaan = enforceService.findListKenderaan(barangRampasan.getOperasi().getIdOperasi());
            logger.info("::Total kenderaan after delete : " + listKenderaan.size());
            if (listKenderaan.size() == 0) {
                Dokumen docNotisKesalahan = semakDokumen(kodDokumenDAO.findById("NKSH"), permohonan.getFolderDokumen(), idPermohonan);
                if (docNotisKesalahan != null) {
                    dokumenService.delete(docNotisKesalahan);
                }
            } else {
                logger.info("::Jenis kenderaan : " + jenisKenderaan);
                if (jenisKenderaan == true) {
                    logger.info("::Regenerate NK");
                    //generateNotisKesalahan(permohonan);
                }
            }

        }

        System.out.println("id op ::::::::: " + idOp);
        if (idOp != null && StringUtils.isNotBlank(idOp)) {
            logger.info("::multipleOp true : add");
            getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
        }
        addSimpleMessage("Maklumat telah berjaya dihapuskan.");
        return new RedirectResolution(MaklumatBarangTahananActionBean.class, "locate");

    }

    public Resolution deleteSelected() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idBarang = getContext().getRequest().getParameter("idBarang");

        barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));


        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);

            barangRampasan.setImej(null);
            InfoAudit ia = barangRampasan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            barangRampasan.setInfoAudit(ia);
            enforceService.updateBarangRampasan(barangRampasan);

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
        return new RedirectResolution(MaklumatBarangTahananActionBean.class, "locate");
    }

    public Resolution findOks() {
        logger.info("::find Oks");
        String id = getContext().getRequest().getParameter("idOks");
        String page = getContext().getRequest().getParameter("pagePass");
        AduanOrangKenaSyak oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(id));

        if (oks != null) {
            noPengenalanPemunya = oks.getNoPengenalan();
            System.out.println("noPengenalanPemunya : " + noPengenalanPemunya);

            if (oks.getAlamat() != null) {
                alamat1 = oks.getAlamat().getAlamat1();
                alamat2 = oks.getAlamat().getAlamat2();
                alamat3 = oks.getAlamat().getAlamat3();
                alamat4 = oks.getAlamat().getAlamat4();
                poskod = oks.getAlamat().getPoskod();
                kn = oks.getAlamat().getNegeri().getKod();

                System.out.println("alamat1 : " + alamat1);
                System.out.println("alamat2 : " + alamat2);
                System.out.println("alamat3 : " + alamat3);
                System.out.println("alamat4 : " + alamat4);
                System.out.println("poskod : " + poskod);
                System.out.println("kn : " + kn);
            }

        }

        if (page.equalsIgnoreCase("edit")) {
            logger.info("::edit page");
            return new JSP("penguatkuasaan/popup_edit_barang_tahanan.jsp").addParameter("popup", "true");
        }
        logger.info("::add page");
        return new JSP("penguatkuasaan/popup_barang_tahanan.jsp").addParameter("popup", "true");
    }

    public Resolution findSuspek() {
        logger.info("::find Suspek");
        String id = getContext().getRequest().getParameter("idOks");
        String page = getContext().getRequest().getParameter("pagePass");
        oks = aduanOrangKenaSyakDAO.findById(Long.parseLong(id));

        if (oks != null) {
            noPengenalanSuspek = oks.getNoPengenalan();
            namaSuspekTemp = oks.getNama();
            noTelSuspek = oks.getNoTelefon1();

            if (oks.getAlamat() != null) {
                if (StringUtils.isNotBlank(oks.getAlamat().getAlamat1())) {
                    System.out.println("oks alamat 1 : " + oks.getAlamat().getAlamat1());
                    alamat1Suspek = oks.getAlamat().getAlamat1();
                }
                if (StringUtils.isNotBlank(oks.getAlamat().getAlamat2())) {
                    System.out.println("oks alamat 2 : " + oks.getAlamat().getAlamat2());
                    if (StringUtils.isNotBlank(oks.getAlamat().getAlamat1()) && !oks.getAlamat().getAlamat1().contains(",")) {
                        alamat1Suspek += ", " + oks.getAlamat().getAlamat2();
                    } else {
                        alamat1Suspek += " " + oks.getAlamat().getAlamat2();
                    }
                    System.out.println("alamat1 suspek :" + alamat1Suspek);
                }
                if (StringUtils.isNotBlank(oks.getAlamat().getAlamat3())) {
                    alamat2Suspek = oks.getAlamat().getAlamat3();
                }
                if (StringUtils.isNotBlank(oks.getAlamat().getAlamat4())) {
                    alamat2Suspek += ", " + oks.getAlamat().getAlamat4();
                    System.out.println("alamat2 suspek :" + alamat2Suspek);
                }
                if (oks.getAlamat().getNegeri() != null) {
                    if (StringUtils.isNotBlank(oks.getAlamat().getNegeri().getNama())) {
                        alamat3Suspek = oks.getAlamat().getNegeri().getNama();
                    }
                    kodNegeriSuspek = oks.getAlamat().getNegeri().getKod();
                }


                if (StringUtils.isNotBlank(oks.getAlamat().getPoskod())) {
                    poskodSuspek = oks.getAlamat().getPoskod();
                }
            }

        }

        if (page.equalsIgnoreCase("edit")) {
            logger.info("::edit page");
            return new JSP("penguatkuasaan/popup_edit_barang_tahanan.jsp").addParameter("popup", "true");
        }
        logger.info("::add page");
        return new JSP("penguatkuasaan/popup_barang_tahanan.jsp").addParameter("popup", "true");
    }

    public Resolution refreshpage() {
        rehydrate();
//        return new JSP("penguatkuasaan/maklumat_barang_tahanan.jsp").addParameter("tab", "true");
        return new RedirectResolution(MaklumatBarangTahananActionBean.class, "locate");
    }

    public Resolution refreshpageNotis() {
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("425") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("426") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("999") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("425A")) {
                getContext().getRequest().setAttribute("multipleOp", Boolean.TRUE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            } else {
                getContext().getRequest().setAttribute("multipleOp", Boolean.FALSE);
                getContext().getRequest().setAttribute("edit", Boolean.TRUE);
            }
        }
        rehydrate();
        return new JSP("penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
    }

    public void generateNotisKesalahan(Permohonan p) {
        logger.info("::generate report : Notis Kesalahan (NK)");
        if (p != null) {
            // Generate report notis kesalahan
            String gen = "";
            String code = "";
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{p.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;
            FolderDokumen fd = folderDokumenDAO.findById(p.getFolderDokumen().getFolderId());

            if ("04".equals(conf.getProperty("kodNegeri"))) {
                gen = "ENFNK_MLK.rdf";
            } else {
                gen = "ENFNK_NS.rdf";
            }
            code = "NKSH";
            kd = kodDokumenDAO.findById(code);
            d = saveOrUpdateDokumen(fd, kd, p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
            reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
        }
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumen(kd, fd, id);
        if (doc == null) {
            logger.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            logger.debug("old Document");
            System.out.println("after semak doc : " + doc.getIdDokumen());
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk("Notis Kesalahan");
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    public Dokumen semakDokumen(KodDokumen kd, FolderDokumen fd, String id) {
        Dokumen d = null;
        List<KandunganFolder> l = kandunganFolderService.findByIdFolder(fd);
        for (KandunganFolder kf : l) {
            d = dokumenService.findByIDKodDokumen(kf.getDokumen().getIdDokumen(), kd, id);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    //simpan kesalahan
    public Resolution simpanNotisSita() {
        idBarang = getContext().getRequest().getParameter("idBarang");
        barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
        barangRampasan.setKesalahan(kesalahan);
        enforceService.updateBarangRampasan(barangRampasan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        rehydrate();
        return new JSP("penguatkuasaan/notis_sita.jsp").addParameter("tab", "true");
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

    public String getKodJenisPengenalanPemegangPermit() {
        return kodJenisPengenalanPemegangPermit;
    }

    public void setKodJenisPengenalanPemegangPermit(String kodJenisPengenalanPemegangPermit) {
        this.kodJenisPengenalanPemegangPermit = kodJenisPengenalanPemegangPermit;
    }

    public String getJppermit() {
        return jppermit;
    }

    public void setJppermit(String jppermit) {
        this.jppermit = jppermit;
    }

    public String getNoSyarikat() {
        return noSyarikat;
    }

    public void setNoSyarikat(String noSyarikat) {
        this.noSyarikat = noSyarikat;
    }

    public String getNomborPengenalanPemegangPermit() {
        return nomborPengenalanPemegangPermit;
    }

    public void setNomborPengenalanPemegangPermit(String nomborPengenalanPemegangPermit) {
        this.nomborPengenalanPemegangPermit = nomborPengenalanPemegangPermit;
    }

    public String getPemegangPermit() {
        return pemegangPermit;
    }

    public void setPemegangPermit(String pemegangPermit) {
        this.pemegangPermit = pemegangPermit;
    }

    public String getTempatPengambilan() {
        return tempatPengambilan;
    }

    public void setTempatPengambilan(String tempatPengambilan) {
        this.tempatPengambilan = tempatPengambilan;
    }

    public String getTempatPenghantaran() {
        return tempatPenghantaran;
    }

    public void setTempatPenghantaran(String tempatPenghantaran) {
        this.tempatPenghantaran = tempatPenghantaran;
    }

    public String getBarangRampas() {
        return barangRampas;
    }

    public void setBarangRampas(String barangRampas) {
        this.barangRampas = barangRampas;
    }

    public String getKbbb() {
        return kbbb;
    }

    public void setKbbb(String kbbb) {
        this.kbbb = kbbb;
    }

    public String getKkkk() {
        return kkkk;
    }

    public void setKkkk(String kkkk) {
        this.kkkk = kkkk;
    }

    public String getKodjbk() {
        return kodjbk;
    }

    public void setKodjbk(String kodjbk) {
        this.kodjbk = kodjbk;
    }

    public String getKj() {
        return kj;
    }

    public void setKj(String kj) {
        this.kj = kj;
    }

    public String getKwg() {
        return kwg;
    }

    public void setKwg(String kwg) {
        this.kwg = kwg;
    }

    public String getKp() {
        return kp;
    }

    public void setKp(String kp) {
        this.kp = kp;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getKodWarganegaraPenjamin() {
        return kodWarganegaraPenjamin;
    }

    public void setKodWarganegaraPenjamin(String kodWarganegaraPenjamin) {
        this.kodWarganegaraPenjamin = kodWarganegaraPenjamin;
    }

    public String getKodJantinaPenjamin() {
        return kodJantinaPenjamin;
    }

    public void setKodJantinaPenjamin(String kodJantinaPenjamin) {
        this.kodJantinaPenjamin = kodJantinaPenjamin;
    }

    public String getAlamat4Penjamin() {
        return alamat4Penjamin;
    }

    public void setAlamat4Penjamin(String alamat4Penjamin) {
        this.alamat4Penjamin = alamat4Penjamin;
    }

    public KodNegeri getKodNegeriPenjamin() {
        return kodNegeriPenjamin;
    }

    public void setKodNegeriPenjamin(KodNegeri kodNegeriPenjamin) {
        this.kodNegeriPenjamin = kodNegeriPenjamin;
    }

    public String getIdPenjaminBrgRampas() {
        return idPenjaminBrgRampas;
    }

    public void setIdPenjaminBrgRampas(String idPenjaminBrgRampas) {
        this.idPenjaminBrgRampas = idPenjaminBrgRampas;
    }

    public String getAlamat3Penjamin() {
        return alamat3Penjamin;
    }

    public void setAlamat3Penjamin(String alamat3Penjamin) {
        this.alamat3Penjamin = alamat3Penjamin;
    }

    public String getHubungan() {
        return hubungan;
    }

    public void setHubungan(String hubungan) {
        this.hubungan = hubungan;
    }

    public KodJantina getJantinaPenjamin() {
        return jantinaPenjamin;
    }

    public void setJantinaPenjamin(KodJantina jantinaPenjamin) {
        this.jantinaPenjamin = jantinaPenjamin;
    }

    public String getKodPengenalanPenjamin() {
        return kodPengenalanPenjamin;
    }

    public void setKodPengenalanPenjamin(String kodPengenalanPenjamin) {
        this.kodPengenalanPenjamin = kodPengenalanPenjamin;
    }

    public PenjaminBarangRampasan getPenjaminBarangRampasan() {
        return penjaminBarangRampasan;
    }

    public void setPenjaminBarangRampasan(PenjaminBarangRampasan penjaminBarangRampasan) {
        this.penjaminBarangRampasan = penjaminBarangRampasan;
    }

    public String getAlamat1Penjamin() {
        return alamat1Penjamin;
    }

    public void setAlamat1Penjamin(String alamat1Penjamin) {
        this.alamat1Penjamin = alamat1Penjamin;
    }

    public String getAlamat2Penjamin() {
        return alamat2Penjamin;
    }

    public void setAlamat2Penjamin(String alamat2Penjamin) {
        this.alamat2Penjamin = alamat2Penjamin;
    }

    public String getNamaPenjamin() {
        return namaPenjamin;
    }

    public void setNamaPenjamin(String namaPenjamin) {
        this.namaPenjamin = namaPenjamin;
    }

    public String getNoPengenalanPenjamin() {
        return noPengenalanPenjamin;
    }

    public void setNoPengenalanPenjamin(String noPengenalanPenjamin) {
        this.noPengenalanPenjamin = noPengenalanPenjamin;
    }

    public String getNoTelBimbitPenjamin() {
        return noTelBimbitPenjamin;
    }

    public void setNoTelBimbitPenjamin(String noTelBimbitPenjamin) {
        this.noTelBimbitPenjamin = noTelBimbitPenjamin;
    }

    public String getNoTelPenjamin() {
        return noTelPenjamin;
    }

    public void setNoTelPenjamin(String noTelPenjamin) {
        this.noTelPenjamin = noTelPenjamin;
    }

    public String getPoskodPenjamin() {
        return poskodPenjamin;
    }

    public void setPoskodPenjamin(String poskodPenjamin) {
        this.poskodPenjamin = poskodPenjamin;
    }

    public KodWarganegara getWarganegaraPenjamin() {
        return warganegaraPenjamin;
    }

    public void setWarganegaraPenjamin(KodWarganegara warganegaraPenjamin) {
        this.warganegaraPenjamin = warganegaraPenjamin;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public BarangRampasan getBarangRampasan() {
        return barangRampasan;
    }

    public void setBarangRampasan(BarangRampasan barangRampasan) {
        this.barangRampasan = barangRampasan;
    }

    public String getBarangRampasanStatus() {
        return barangRampasanStatus;
    }

    public void setBarangRampasanStatus(String barangRampasanStatus) {
        this.barangRampasanStatus = barangRampasanStatus;
    }

    public String getBuatan() {
        return buatan;
    }

    public void setBuatan(String buatan) {
        this.buatan = buatan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public Pengguna getDilepaskanOleh() {
        return dilepaskanOleh;
    }

    public void setDilepaskanOleh(Pengguna dilepaskanOleh) {
        this.dilepaskanOleh = dilepaskanOleh;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(long idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getKadarLesen() {
        return kadarLesen;
    }

    public void setKadarLesen(BigDecimal kadarLesen) {
        this.kadarLesen = kadarLesen;
    }

    public String getKapasitiEnjin() {
        return kapasitiEnjin;
    }

    public void setKapasitiEnjin(String kapasitiEnjin) {
        this.kapasitiEnjin = kapasitiEnjin;
    }

    public String getKatItmRmpsn() {
        return katItmRmpsn;
    }

    public void setKatItmRmpsn(String katItmRmpsn) {
        this.katItmRmpsn = katItmRmpsn;
    }

    public String getKn() {
        return kn;
    }

    public void setKn(String kn) {
        this.kn = kn;
    }

    public String getKodBahanBakar() {
        return kodBahanBakar;
    }

    public void setKodBahanBakar(String kodBahanBakar) {
        this.kodBahanBakar = kodBahanBakar;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getKodKategoriItemRampasan() {
        return kodKategoriItemRampasan;
    }

    public void setKodKategoriItemRampasan(String kodKategoriItemRampasan) {
        this.kodKategoriItemRampasan = kodKategoriItemRampasan;
    }

    public String getKodJenisBadanKenderaan() {
        return kodJenisBadanKenderaan;
    }

    public void setKodJenisBadanKenderaan(String kodJenisBadanKenderaan) {
        this.kodJenisBadanKenderaan = kodJenisBadanKenderaan;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public String getKuantitiUnit() {
        return kuantitiUnit;
    }

    public void setKuantitiUnit(String kuantitiUnit) {
        this.kuantitiUnit = kuantitiUnit;
    }

    public int getMuatanTempatDuduk() {
        return muatanTempatDuduk;
    }

    public void setMuatanTempatDuduk(int muatanTempatDuduk) {
        this.muatanTempatDuduk = muatanTempatDuduk;
    }

    public String getNamaModel() {
        return namaModel;
    }

    public void setNamaModel(String namaModel) {
        this.namaModel = namaModel;
    }

    public String getNamaPemunya() {
        return namaPemunya;
    }

    public void setNamaPemunya(String namaPemunya) {
        this.namaPemunya = namaPemunya;
    }

    public String getNomborCasis() {
        return nomborCasis;
    }

    public void setNomborCasis(String nomborCasis) {
        this.nomborCasis = nomborCasis;
    }

    public String getNomborEnjin() {
        return nomborEnjin;
    }

    public void setNomborEnjin(String nomborEnjin) {
        this.nomborEnjin = nomborEnjin;
    }

    public String getNomborPendaftaran() {
        return nomborPendaftaran;
    }

    public void setNomborPendaftaran(String nomborPendaftaran) {
        this.nomborPendaftaran = nomborPendaftaran;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatus() {
        return senaraiKodStatus;
    }

    public void setSenaraiKodStatus(List<KodStatusBarangRampasan> senaraiKodStatus) {
        this.senaraiKodStatus = senaraiKodStatus;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public KodStatusBarangRampasan getStatus() {
        return status;
    }

    public void setStatus(KodStatusBarangRampasan status) {
        this.status = status;
    }

    public String getStatusPemunya() {
        return statusPemunya;
    }

    public void setStatusPemunya(String statusPemunya) {
        this.statusPemunya = statusPemunya;
    }

    public String getTahunDibuat() {
        return tahunDibuat;
    }

    public void setTahunDibuat(String tahunDibuat) {
        this.tahunDibuat = tahunDibuat;
    }

    public String getKodKegunaanKenderaan() {
        return kodKegunaanKenderaan;
    }

    public void setKodKegunaanKenderaan(String kodKegunaanKenderaan) {
        this.kodKegunaanKenderaan = kodKegunaanKenderaan;
    }

    public Date getTarikhPendaftaran() {
        return tarikhPendaftaran;
    }

    public void setTarikhPendaftaran(Date tarikhPendaftaran) {
        this.tarikhPendaftaran = tarikhPendaftaran;
    }

    public String getTarikhRampas() {
        return tarikhRampas;
    }

    public void setTarikhRampas(String tarikhRampas) {
        this.tarikhRampas = tarikhRampas;
    }

    public String getTempatSimpanan() {
        return tempatSimpanan;
    }

    public void setTempatSimpanan(String tempatSimpanan) {
        this.tempatSimpanan = tempatSimpanan;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public Boolean getOpFlag() {
        return opFlag;
    }

    public void setOpFlag(Boolean opFlag) {
        this.opFlag = opFlag;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getNoPengenalanPemunya() {
        return noPengenalanPemunya;
    }

    public void setNoPengenalanPemunya(String noPengenalanPemunya) {
        this.noPengenalanPemunya = noPengenalanPemunya;
    }

    public KodJenisPengenalan getJenisPengenalan() {
        return jenisPengenalan;
    }

    public void setJenisPengenalan(KodJenisPengenalan jenisPengenalan) {
        this.jenisPengenalan = jenisPengenalan;
    }

    public String getKodNegeriPemegangPermit() {
        return kodNegeriPemegangPermit;
    }

    public void setKodNegeriPemegangPermit(String kodNegeriPemegangPermit) {
        this.kodNegeriPemegangPermit = kodNegeriPemegangPermit;
    }

    public String getAlamat1PemegangPermit() {
        return alamat1PemegangPermit;
    }

    public void setAlamat1PemegangPermit(String alamat1PemegangPermit) {
        this.alamat1PemegangPermit = alamat1PemegangPermit;
    }

    public String getAlamat2PemegangPermit() {
        return alamat2PemegangPermit;
    }

    public void setAlamat2PemegangPermit(String alamat2PemegangPermit) {
        this.alamat2PemegangPermit = alamat2PemegangPermit;
    }

    public String getAlamat3PemegangPermit() {
        return alamat3PemegangPermit;
    }

    public void setAlamat3PemegangPermit(String alamat3PemegangPermit) {
        this.alamat3PemegangPermit = alamat3PemegangPermit;
    }

    public String getAlamat4PemegangPermit() {
        return alamat4PemegangPermit;
    }

    public void setAlamat4PemegangPermit(String alamat4PemegangPermit) {
        this.alamat4PemegangPermit = alamat4PemegangPermit;
    }

    public String getPoskodPemegangPermit() {
        return poskodPemegangPermit;
    }

    public void setPoskodPemegangPermit(String poskodPemegangPermit) {
        this.poskodPemegangPermit = poskodPemegangPermit;
    }

    public String getKnpermit() {
        return knpermit;
    }

    public void setKnpermit(String knpermit) {
        this.knpermit = knpermit;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public String getNamaPemandu() {
        return namaPemandu;
    }

    public void setNamaPemandu(String namaPemandu) {
        this.namaPemandu = namaPemandu;
    }

    public List<AduanOrangKenaSyak> getSenaraiOKS() {
        return senaraiOKS;
    }

    public void setSenaraiOKS(List<AduanOrangKenaSyak> senaraiOKS) {
        this.senaraiOKS = senaraiOKS;
    }

    public String getKodNegeriPermohonan() {
        return kodNegeriPermohonan;
    }

    public void setKodNegeriPermohonan(String kodNegeriPermohonan) {
        this.kodNegeriPermohonan = kodNegeriPermohonan;
    }

    public List<OperasiPenguatkuasaan> getListOp() {
        return listOp;
    }

    public void setListOp(List<OperasiPenguatkuasaan> listOp) {
        this.listOp = listOp;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<BarangRampasan> getListKenderaan() {
        return listKenderaan;
    }

    public void setListKenderaan(List<BarangRampasan> listKenderaan) {
        this.listKenderaan = listKenderaan;
    }

    public String getIdOp() {
        return idOp;
    }

    public void setIdOp(String idOp) {
        this.idOp = idOp;
    }

    public String getAlamat1Suspek() {
        return alamat1Suspek;
    }

    public void setAlamat1Suspek(String alamat1Suspek) {
        this.alamat1Suspek = alamat1Suspek;
    }

    public String getAlamat2Suspek() {
        return alamat2Suspek;
    }

    public void setAlamat2Suspek(String alamat2Suspek) {
        this.alamat2Suspek = alamat2Suspek;
    }

    public String getAlamat3Suspek() {
        return alamat3Suspek;
    }

    public void setAlamat3Suspek(String alamat3Suspek) {
        this.alamat3Suspek = alamat3Suspek;
    }

    public String getNamaSuspek() {
        return namaSuspek;
    }

    public void setNamaSuspek(String namaSuspek) {
        this.namaSuspek = namaSuspek;
    }

    public String getNoPengenalanSuspek() {
        return noPengenalanSuspek;
    }

    public void setNoPengenalanSuspek(String noPengenalanSuspek) {
        this.noPengenalanSuspek = noPengenalanSuspek;
    }

    public String getNoSiri() {
        return noSiri;
    }

    public void setNoSiri(String noSiri) {
        this.noSiri = noSiri;
    }

    public String getNoTelPemunya() {
        return noTelPemunya;
    }

    public void setNoTelPemunya(String noTelPemunya) {
        this.noTelPemunya = noTelPemunya;
    }

    public String getPoskodSuspek() {
        return poskodSuspek;
    }

    public void setPoskodSuspek(String poskodSuspek) {
        this.poskodSuspek = poskodSuspek;
    }

    public String getTempatTangkap() {
        return tempatTangkap;
    }

    public void setTempatTangkap(String tempatTangkap) {
        this.tempatTangkap = tempatTangkap;
    }

    public String getJenisBatuan() {
        return jenisBatuan;
    }

    public void setJenisBatuan(String jenisBatuan) {
        this.jenisBatuan = jenisBatuan;
    }

    public String getNoTelSuspek() {
        return noTelSuspek;
    }

    public void setNoTelSuspek(String noTelSuspek) {
        this.noTelSuspek = noTelSuspek;
    }

    public String getCheckSame() {
        return checkSame;
    }

    public void setCheckSame(String checkSame) {
        this.checkSame = checkSame;
    }

    public String getNamaSuspekTemp() {
        return namaSuspekTemp;
    }

    public void setNamaSuspekTemp(String namaSuspekTemp) {
        this.namaSuspekTemp = namaSuspekTemp;
    }

    public String getKodNegeriSuspek() {
        return kodNegeriSuspek;
    }

    public void setKodNegeriSuspek(String kodNegeriSuspek) {
        this.kodNegeriSuspek = kodNegeriSuspek;
    }

    public String getKodNegeriPemunyaTemp() {
        return kodNegeriPemunyaTemp;
    }

    public void setKodNegeriPemunyaTemp(String kodNegeriPemunyaTemp) {
        this.kodNegeriPemunyaTemp = kodNegeriPemunyaTemp;
    }

    public String getKesalahan() {
        return kesalahan;
    }

    public void setKesalahan(String kesalahan) {
        this.kesalahan = kesalahan;
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }

    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }

    public String getIdOperasiAgensi() {
        return idOperasiAgensi;
    }

    public void setIdOperasiAgensi(String idOperasiAgensi) {
        this.idOperasiAgensi = idOperasiAgensi;
    }

    public OperasiAgensi getOperasiAgensi() {
        return operasiAgensi;
    }

    public void setOperasiAgensi(OperasiAgensi operasiAgensi) {
        this.operasiAgensi = operasiAgensi;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }

    public List<AduanOrangKenaSyak> getSenaraiOksIp() {
        return senaraiOksIp;
    }

    public void setSenaraiOksIp(List<AduanOrangKenaSyak> senaraiOksIp) {
        this.senaraiOksIp = senaraiOksIp;
    }

    public Boolean getStatusIP() {
        return statusIP;
    }

    public void setStatusIP(Boolean statusIP) {
        this.statusIP = statusIP;
    }

    public String getTempatBongkar() {
        return tempatBongkar;
    }

    public void setTempatBongkar(String tempatBongkar) {
        this.tempatBongkar = tempatBongkar;
    }

    public String getKodJenisPengenalanPemunya() {
        return kodJenisPengenalanPemunya;
    }

    public void setKodJenisPengenalanPemunya(String kodJenisPengenalanPemunya) {
        this.kodJenisPengenalanPemunya = kodJenisPengenalanPemunya;
    }

    public boolean isStageLucutHak() {
        return stageLucutHak;
    }

    public void setStageLucutHak(boolean stageLucutHak) {
        this.stageLucutHak = stageLucutHak;
    }

    public boolean isStageRujukMahkamah() {
        return stageRujukMahkamah;
    }

    public void setStageRujukMahkamah(boolean stageRujukMahkamah) {
        this.stageRujukMahkamah = stageRujukMahkamah;
    }

    public AduanOrangKenaSyak getOks() {
        return oks;
    }

    public void setOks(AduanOrangKenaSyak oks) {
        this.oks = oks;
    }

    public String getChooseOKS() {
        return chooseOKS;
    }

    public void setChooseOKS(String chooseOKS) {
        this.chooseOKS = chooseOKS;
    }
}
