/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.laporanTanah;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.google.inject.Provider;
import etanah.Configuration;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.KodKategoriTanahDAO; // Added by Iskandar 12 Mar 2013
import etanah.dao.KodDaerahDAO; // Added by Iskandar 12 Mar 2003
import etanah.dao.KodLotDAO;
import etanah.dao.KodUOMDAO; // Added by Iskandar 12 Mar 2003
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.KodItemPermitDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import java.math.BigDecimal;
import etanah.service.BPelService;
import oracle.bpel.services.workflow.task.model.Task;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKeputusan;
import etanah.model.KodLot;
import etanah.model.KodPemilikan;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTanah;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodUOM;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanPohon;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanLaporanUsaha;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodKeadaanTanahDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.MohonKeadaanTanahDAO;
import etanah.dao.MohonLotSepadanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.ambil.KodPerihalTanahDAO;
import etanah.dao.ambil.KodStatusTanahDAO;
import etanah.dao.ambil.MohonAgensiTanahDAO;
import etanah.dao.ambil.MohonStatusTanahDAO;
import etanah.dao.ambil.SyorLaporanTanahDAO;
import etanah.hasil.workflow.ProsesPenguranganDendaCalc;
import etanah.model.KodKeadaanTanah;
import etanah.model.KodUrusan;
import etanah.model.ambil.KodStatusTanah;
import etanah.model.ambil.MohonAgensiTanah;
import etanah.model.ambil.MohonKeadaanTanah;
import etanah.model.ambil.MohonLotSepadan;
import etanah.model.ambil.MohonPerihalTanah;
import etanah.model.ambil.MohonStatusTanah;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.ambil.SyorLaporanTanah;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahSempadan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanBahanBatu;
import etanah.view.stripes.pelupusan.disClass.LotSempadan;
import org.apache.log4j.Logger;
import java.text.ParseException;
import net.sourceforge.stripes.action.FileBean;
import etanah.view.stripes.pelupusan.disClass.DisLaporTanahKawasan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahController;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanPage;
import etanah.view.stripes.pelupusan.disClass.DisSyaratSekatan;
import java.text.SimpleDateFormat;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.service.RegService;
import net.sourceforge.stripes.action.StreamingResolution;
import etanah.service.CalcTaxPelupusan;
import etanah.service.LaporanPelukisPelanService;
import etanah.service.LaporanTanahService;
import etanah.service.PengambilanService;
import etanah.service.ambil.BorangPerHakmilikService;
import etanah.service.ambil.MohonPerihalTanahService;
import etanah.service.ambil.MohonStatusTanahService;
import etanah.service.ambil.TampalHakmilikService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.PermohonanService;
import etanah.view.strata.CommonService;
import etanah.view.stripes.pelupusan.LaporanTanah4ActionBean;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanTanahTerdahulu;
import etanah.workflow.WorkFlowService;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import javax.imageio.ImageWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

/**
 *
 * @author zipzap
 */
//public class laporanTanahV3 {
@UrlBinding("/laporanTanah")
public class laporanTanahV3 extends AbleActionBean {
//    E:\EtanahNew\devel\etanahViewController\src\main\java\etanah\view\laporanTanah\laporanTanahV3.java
//            E:\EtanahNew\devel\etanahViewController\src\main\java\etanah\view\stripes\common\laporan\tanah\laporantanahNewActionBean.java
//@UrlBinding("/common/laporan/tanah/laporantanahNewActionBean")
//    @Inject
//    MohonPerihalTanahDAO mohonPerihalTanahDAO;

    @Inject
    MohonPerihalTanahService mohonPerihalTanahService;
    @Inject
    RegService regService;
    @Inject
    CalcTaxPelupusan calcTax;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    @Inject
    PermohonanService permohonanServ;
    @Inject
    TampalHakmilikService tampalHakmilikService;
    @Inject
    BorangPerHakmilikService borangPerhakmilikService;
    @Inject
    FasaPermohonanService fasaPermohonanServ;
    @Inject
    PengambilanService service;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanServ;
    @Inject
    MohonStatusTanahService mohonStatusTanahService;
    @Inject
    MohonStatusTanahDAO mohonStatusTanahDAO;
    @Inject
    KodStatusTanahDAO kodStatusTanahDAO;
    @Inject
    KodPerihalTanahDAO kodPerihalTanahDAO;
    @Inject
    KodKeadaanTanahDAO kodKeadaanTanahDAO;
    @Inject
    MohonKeadaanTanahDAO mohonKeadaanTanahDAO;
    @Inject
    MohonLotSepadanDAO mohonLotSepadanDAO;
    @Inject
    CommonService commonService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    MohonAgensiTanahDAO mohonAgensiTanahDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    SyorLaporanTanahDAO syorLaporanTanahDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PenggunaDAO penggunaDao;

    private List<MohonPerihalTanah> listMohonPerihalTanah;
    private List<MohonStatusTanah> listMohonStatusTanah;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah2;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah3;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah4;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah5;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah6;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah7;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah8;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah9;
    private List<MohonKeadaanTanah> listMohonKeadaanTanah10;
    private List<MohonKeadaanTanah> listMohonKeadaanTanahDEL;
    private List<HakmilikPermohonan> hakmilikPermohonanList2;
    private List<KodKeadaanTanah> kodKeadaanTanahList;
    private List<KodKeadaanTanah> kodKeadaanTanahList2;
    private List<KodKeadaanTanah> kodKeadaanTanahList3;
    private List<KodKeadaanTanah> kodKeadaanTanahList4;
    private List<KodKeadaanTanah> kodKeadaanTanahList5;
    private List<KodKeadaanTanah> kodKeadaanTanahList6;
    private List<KodKeadaanTanah> kodKeadaanTanahList7;
    private List<KodKeadaanTanah> kodKeadaanTanahList9;
    private List<KodKeadaanTanah> kodKeadaanTanahList10;
    private List<MohonLotSepadan> mohonLotSepadanList;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranD;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranUtara;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranTimur;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranBarat;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranSelatan;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranPanorama;
    private List<MohonLotSepadan> mohonLotSepadanLisLampiranLokasi;
    private List<SyorLaporanTanah> mohonSyorLaporanTanahList;
    private List<KodAgensi> senaraiJTK;
    private List<MohonAgensiTanah> senaraiJTKSimpan;
    private List<KodSekatanKepentingan> listKodSekatan;
    List<SyorLaporanTanah> mohonSyorLaporanTanahListBaru = new ArrayList<SyorLaporanTanah>();
    private Permohonan permohonan;
    private FasaPermohonan fasaPermohonan;
    private PermohonanPengambilan permohonanPengambilan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan hakmilikPermohonanStatusTanah;
    private KodStatusTanah kodStatus;
    private KodKeadaanTanah kodKeadaanTanah;
    private MohonKeadaanTanah mohonKeadaanTanah;
    private MohonLotSepadan mohonLotSepadan;
    private SyorLaporanTanah syorLaporanTanah;
    private MohonAgensiTanah mohonAgensiTanah;
    private FasaPermohonan mohonFasa;
    private FileBean fileToBeUpload;
    private Pengguna pguna;

    private List<PermohonanLaporanPelan> permohonanLaporanPelanList;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private List<KodAgensi> listKodAgensi;
    private KodAgensi details;
    private boolean flag = false;

    private String idMohonHakmilik;
    private String idLaporTanah;
    private String idlaporTnhSmpdn;
    private String idJtek;
    private String idPermohonan;
    private String jalan;
    private String kampung;
    private String landMark;
    private String jarak;
    private String dun;
    private String parlimen;
    private String PBT;
    private String zone;
    private String tnb;
    private String koordinat;
    private String jenisStatusTanah;
    private String jalanMasuk;
    private String jarakKeadaan;
    private String valueJarak;

    private String jenisTanah;
    private String tujuan;
    private String rujFail;
    private String noWarta;
    private String seksyen;
    private String pelanWarta;
    private String pengawal;
    private String diberimilik;
    private String catatan;
    private String noLot;
    private String jenisTanahGambar;
    private String pandanganImej;
    private String noPtSementara;
    private String idHakmilik;
    private String ulasanPerihal;
    private String noLitho;
    private String kodTanah;
    private String kodKementerian;
    private String katgAgensi;
    private String negeri;
    private String gelaran;
    private String daerah;
    private String nama;
    private String kodJTK;

    private String keadaanTanah[];
    private String keadaanBekalan[];
    private String keadaanLintas[];
    private String jalanSimpan[];
    private String jalanRizab[];
    private String bangunan[];
    private String pihakB[];
    private String nilaian[];
    private String tanaman[];
    private String idAliran;
    private String taskId;
    IWorkflowContext ctx = null;

    private DisSyaratSekatan disSyaratSekatan;

    public static String[] berimilikAndPLPT = {"Jenis Hakmilik", "Tempoh", "Premium", "Hasil", "Bayaran Ukur", "Penjenisan", "Syarat Nyata", "Syarat Nyata", "Sekatan Kepentingan",};
    public static String[] LPS = {"Lesen", "Kegunaan", "Plot/PT/LOT", "Keluasan", "Sewa Tahunan",};
    public static String[] PermitRuangUdara = {"Permit", "Kegunaan", "Isipadu", "Tempoh", "Sewa Tahunan",};
    public static String[] Rizab = {"Keluasan Diluluskan", "Bayaran Tahunan", "Tempoh Pajakan", "Kegunaan Tanah", "Syarat Pajakan",};
    public static String[] Perizaban = {"Tujuan Perizaban", "Pegawai Pengawal", "Pengurusan/Penyelenggaraan",};
    public static String[] PermitBahanBatuan = {"Kuantiti", "Tempoh", "Kadar Royalti", "Bayaran Royalti", "Wang Cagaran", "Buku Kupon", "Jumlah Perlu DiBayar",};
    public static String[] PermitPertanian = {"Lesen", "Kegunaan", "Lot/Hakmilik", "Keluasan", "Sewa Tahunan",};
    public static String[] Pengambilan = {"Syor Perakuan Kanan"};

    private static final Logger logger = Logger.getLogger(laporanTanahV3.class);

    @DefaultHandler
    public Resolution showFormMain() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanServ.findById(idPermohonan);
            hakmilikPermohonanList2 = permohonan.getSenaraiHakmilik();
            List<FasaPermohonan> listFasa = fasaPermohonanServ.findByIdMohonAndStageIdDESC(idPermohonan, "laporan_tanah");
            if (listFasa.size() > 0) {
                fasaPermohonan = listFasa.get(0);
            }
            permohonanPengambilan = service.findByidPermohonan(idPermohonan);
        }
        if (idMohonHakmilik != null) {
            listMohonPerihalTanah = mohonPerihalTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik));
            hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
            if (listMohonPerihalTanah.size() <= 0) {
                for (int i = 1; i < 10; i++) {
//                    tujuan ="0"+i;
                    MohonPerihalTanah MPT = new MohonPerihalTanah();
                    MPT.setHakmilikPermohonan(hakmilikPermohonan);
                    MPT.setKodPerihalTanah(kodPerihalTanahDAO.findById("T" + "0" + i));
                    mohonPerihalTanahService.simpanMohonPerihalTanah(MPT);
                }
                MohonPerihalTanah MPT = new MohonPerihalTanah();
                MPT.setHakmilikPermohonan(hakmilikPermohonan);
                MPT.setKodPerihalTanah(kodPerihalTanahDAO.findById("T10"));
                mohonPerihalTanahService.simpanMohonPerihalTanah(MPT);
            }
            listMohonPerihalTanah = mohonPerihalTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik));

            permohonanLaporanPelanList = pelupusanService.findByIdPermohonanPelanNIdMHList(idPermohonan, Long.valueOf(idMohonHakmilik));
            if (permohonanLaporanPelanList.size() > 0) {
                permohonanLaporanPelan = permohonanLaporanPelanList.get(0);

                permohonanLaporanPelanList = new ArrayList<PermohonanLaporanPelan>();
//                permohonanLaporanPelanList.clear();
                permohonanLaporanPelanList.add(permohonanLaporanPelan);
                if (catatan != null) {
                    catatan = permohonanLaporanPelan.getCatatan();
                }
                if (permohonanLaporanPelan.getNoLitho() != null) {
                    noLitho = permohonanLaporanPelan.getNoLitho();
                }
                if (permohonanLaporanPelan.getKodTanah() != null) {
                    kodTanah = permohonanLaporanPelan.getKodTanah().getKod();
                }
            }
        }

        return new JSP("laporanTanah/laporan_tanahV3.jsp").addParameter("tab", "true");

    }

    public Resolution reloadMain() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        return showFormMain();
    }

    public Resolution simpanKedudukan() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        kampung = getContext().getRequest().getParameter("kampung1");
        landMark = getContext().getRequest().getParameter("landMark1");
        dun = getContext().getRequest().getParameter("dun1");
        parlimen = getContext().getRequest().getParameter("parlimen1");
        PBT = getContext().getRequest().getParameter("PBT1");
        zone = getContext().getRequest().getParameter("zone1");
        tnb = getContext().getRequest().getParameter("tnb1");
        koordinat = getContext().getRequest().getParameter("koordinat1");
        jarak = getContext().getRequest().getParameter("jarak1");
        jalan = getContext().getRequest().getParameter("jalan1");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (jalan != null) {

        }
        if (idMohonHakmilik != null) {
            listMohonPerihalTanah = mohonPerihalTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik));
            InfoAudit ia = new InfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(peng);
            for (MohonPerihalTanah MPT : listMohonPerihalTanah) {
                if (MPT.getKodPerihalTanah().getKod().equals("T01")) {
                    MPT.setKeterangan(jalan);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T02")) {
                    MPT.setKeterangan(kampung);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T03")) {
                    MPT.setKeterangan(landMark);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T04")) {
                    MPT.setKeterangan(jarak);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T05")) {
                    MPT.setKeterangan(dun);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T06")) {
                    MPT.setKeterangan(parlimen);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T07")) {
                    MPT.setKeterangan(PBT);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T08")) {
                    MPT.setKeterangan(zone);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T09")) {
                    MPT.setKeterangan(tnb);
                    MPT.setInfoAudit(ia);
                }
                if (MPT.getKodPerihalTanah().getKod().equals("T10")) {
                    MPT.setKeterangan(koordinat);
                    MPT.setInfoAudit(ia);
                }
                mohonPerihalTanahService.simpanMohonPerihalTanah(MPT);
                hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
            }
            listMohonStatusTanah = mohonStatusTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik), "01");
            if (listMohonStatusTanah.size() <= 0) {

                for (int i = 1; i < 9; i++) {
//                    tujuan ="0"+i;
                    MohonStatusTanah mstNew = new MohonStatusTanah();
                    mstNew.setHakmilikPermohonan(hakmilikPermohonan);
                    mstNew.setKodStatusTanah(kodStatusTanahDAO.findById("0" + i));
                    mohonStatusTanahService.simpanMohonStatusTanahDAO(mstNew);
                }
            }
        }

//        rehydrate();
        return new JSP("laporanTanah/laporan_tanahV3.jsp").addParameter("tab", "true");
    }

    public Resolution statusPerihalTanah() {

        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        if (idMohonHakmilik != null) {
            hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
            listMohonStatusTanah = mohonStatusTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik), "01");
            if (listMohonStatusTanah.size() <= 0) {

                for (int i = 1; i < 9; i++) {
//                    tujuan ="0"+i;
                    MohonStatusTanah mstNew = new MohonStatusTanah();
                    mstNew.setHakmilikPermohonan(hakmilikPermohonan);
                    mstNew.setKodStatusTanah(kodStatusTanahDAO.findById("0" + i));
                    mohonStatusTanahService.simpanMohonStatusTanahDAO(mstNew);
                }
            }
        }
        return new JSP("laporanTanah/status_tanahV3.jsp").addParameter("tab", "true");
    }

    public Resolution keadaanTanah() {
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
//        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
        if (idMohonHakmilik != null) {
            hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));

            listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "01");
            listMohonKeadaanTanah2 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "02");
            listMohonKeadaanTanah3 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "03");
            listMohonKeadaanTanah4 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "04");
            listMohonKeadaanTanah5 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "05");
            listMohonKeadaanTanah6 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "06");
            listMohonKeadaanTanah7 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "07");
            listMohonKeadaanTanah8 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "08");
            listMohonKeadaanTanah9 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "09");
            listMohonKeadaanTanah10 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "10");

            kodKeadaanTanahList = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("01");
            kodKeadaanTanahList2 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("02");
            kodKeadaanTanahList3 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("03");
            kodKeadaanTanahList4 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("04");
            kodKeadaanTanahList5 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("05");
            kodKeadaanTanahList6 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("06");
            kodKeadaanTanahList7 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("07");
            kodKeadaanTanahList9 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("09");
            kodKeadaanTanahList10 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("10");
            if (listMohonKeadaanTanah8.size() <= 0) {
                jarakKeadaan = "Jarak Tapak Yang Dipohon Dari Jalan Utama";
            } else {
                jarakKeadaan = listMohonKeadaanTanah8.get(0).getKeterangan();
            }
        }
        return new JSP("laporanTanah/keadaan_semasaV3.jsp").addParameter("tab", "true");
//        return new JSP("laporanTanah/keadaan_tanahV3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanStatusTanah() {
        jenisTanah = getContext().getRequest().getParameter("jenisTanah1");
        tujuan = getContext().getRequest().getParameter("tujuan1");
        rujFail = getContext().getRequest().getParameter("rujFail1");
        noWarta = getContext().getRequest().getParameter("noWarta1");
        seksyen = getContext().getRequest().getParameter("seksyen1");
        pelanWarta = getContext().getRequest().getParameter("pelanWarta1");
        pengawal = getContext().getRequest().getParameter("pengawal1");
        diberimilik = getContext().getRequest().getParameter("diberimilik1");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        listMohonStatusTanah = mohonStatusTanahService.findMohonPerihalTanahByIdMh(Long.parseLong(idMohonHakmilik), "01");
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(peng);
        if (listMohonStatusTanah.size() > 0) {
            for (MohonStatusTanah mst : listMohonStatusTanah) {
                if (mst.getKodStatusTanah().getKod().equals("01")) {
                    mst.setKeterangan(jenisTanah);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("02")) {
                    mst.setKeterangan(tujuan);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("03")) {
                    mst.setKeterangan(rujFail);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("04")) {
                    mst.setKeterangan(noWarta);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("05")) {
                    mst.setKeterangan(seksyen);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("06")) {
                    mst.setKeterangan(pelanWarta);

                }
                if (mst.getKodStatusTanah().getKod().equals("07")) {
                    mst.setKeterangan(pengawal);
                    mst.setInfoAudit(ia);
                }
                if (mst.getKodStatusTanah().getKod().equals("08")) {
                    mst.setKeterangan(diberimilik);
                    mst.setInfoAudit(ia);
                }
                mohonStatusTanahService.simpanMohonStatusTanahDAO(mst);
            }
        }

        return new JSP("laporanTanah/status_tanahV3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeadaanTanah() {

        keadaanTanah = getContext().getRequest().getParameterValues("keadaanTanah123");
        keadaanLintas = getContext().getRequest().getParameterValues("keadaanTanahLintas");
        keadaanBekalan = getContext().getRequest().getParameterValues("keadaanTanahBekalan");
        jalanRizab = getContext().getRequest().getParameterValues("jalanRizab");
        jalanSimpan = getContext().getRequest().getParameterValues("jalan");
//        jalanMasuk = getContext().getRequest().getParameter("jalan");
        bangunan = getContext().getRequest().getParameterValues("bangunan");
        tanaman = getContext().getRequest().getParameterValues("tanaman");
        valueJarak = getContext().getRequest().getParameter("valueJarak");
        jarakKeadaan = getContext().getRequest().getParameter("jarakKeadaan");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(peng);
//        MohonKeadaanTanah mkt = new MohonKeadaanTanah();
        listMohonKeadaanTanahDEL = mohonPerihalTanahService.findByIdMHAndKodAndJenisKeadaan(hakmilikPermohonan.getPermohonan().getIdPermohonan(), idMohonHakmilik);

        for (MohonKeadaanTanah mktDel : listMohonKeadaanTanahDEL) {
            mohonPerihalTanahService.deleteMohonKeadaanTanah(mktDel);
        }
        if (keadaanTanah != null) {
            if (keadaanTanah.length > 0) {
                for (int i = 0; i < keadaanTanah.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, keadaanTanah[i]);

                    if (mohonKeadaanTanah != null) {
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mohonKeadaanTanah);
                    } else {
                        MohonKeadaanTanah mkt = new MohonKeadaanTanah();
                        mkt.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanTanah[i]));
                        mkt.setJenisKeadaan("01");
                        mkt.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt);
                    }
                }
            }
        }
        if (keadaanLintas != null) {
            if (keadaanLintas.length > 0) {
                for (int i = 0; i < keadaanLintas.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, keadaanLintas[i]);
                    if (mohonKeadaanTanah != null) {
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mohonKeadaanTanah);
                    } else {
                        MohonKeadaanTanah mkt2 = new MohonKeadaanTanah();
                        mkt2.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt2.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt2.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanLintas[i]));
                        mkt2.setJenisKeadaan("02");
                        mkt2.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt2);
                    }
                }
            }
        }
        if (keadaanBekalan != null) {
            if (keadaanBekalan.length > 0) {
                for (int i = 0; i < keadaanBekalan.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, keadaanBekalan[i]);
                    if (mohonKeadaanTanah != null) {
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mohonKeadaanTanah);
                    } else {
                        MohonKeadaanTanah mkt3 = new MohonKeadaanTanah();
                        mkt3.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt3.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt3.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(keadaanBekalan[i]));
                        mkt3.setJenisKeadaan("03");
                        mkt3.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt3);
                    }
                }
            }
        }
        if (jalanRizab != null) {
            if (jalanRizab.length > 0) {
                for (int i = 0; i < jalanRizab.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, jalanRizab[i]);
                    if (mohonKeadaanTanah != null) {
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mohonKeadaanTanah);
                    } else {
                        MohonKeadaanTanah mkt4 = new MohonKeadaanTanah();
                        mkt4.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt4.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt4.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(jalanRizab[i]));
                        mkt4.setKeterangan(jalanRizab[i]);
                        mkt4.setJenisKeadaan("07");
                        mkt4.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt4);
                    }
                }
            }
        }
        if (jalanSimpan != null) {
            if (jalanSimpan.length > 0) {
                for (int i = 0; i < jalanSimpan.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, jalanSimpan[i]);
                    if (mohonKeadaanTanah != null) {

                    } else {
                        MohonKeadaanTanah mkt5 = new MohonKeadaanTanah();
                        mkt5.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt5.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt5.setKodKeadaanTanah(kodKeadaanTanahDAO.findById(jalanSimpan[i]));
                        mkt5.setJenisKeadaan("06");
                        mkt5.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt5);
                    }
                }
            }
        }
        if (tanaman != null) {
            if (tanaman.length > 0) {
                for (int i = 0; i < tanaman.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, tanaman[i]);
                    if (mohonKeadaanTanah != null) {

                    } else {
                        MohonKeadaanTanah mkt6 = new MohonKeadaanTanah();
                        mkt6.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt6.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt6.setKodKeadaanTanah(kodKeadaanTanahDAO.findById("TNM"));
                        mkt6.setKeterangan(tanaman[i]);
                        mkt6.setJenisKeadaan("04");
                        mkt6.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt6);
                        listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "04");
                    }
                }
            }
        }
        if (bangunan != null) {
            if (bangunan.length > 0) {
                for (int i = 0; i < bangunan.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, bangunan[i]);
                    if (mohonKeadaanTanah != null) {

                    } else {
                        MohonKeadaanTanah mkt7 = new MohonKeadaanTanah();
                        mkt7.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt7.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt7.setKodKeadaanTanah(kodKeadaanTanahDAO.findById("BGN"));
                        mkt7.setKeterangan(bangunan[i]);
                        mkt7.setJenisKeadaan("05");
                        mkt7.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt7);
                        listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "05");
                    }
                }
            }
        }
        if (nilaian != null) {
            if (nilaian.length > 0) {
                for (int i = 0; i < nilaian.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, nilaian[i]);
                    if (mohonKeadaanTanah != null) {

                    } else {
                        MohonKeadaanTanah mkt7 = new MohonKeadaanTanah();
                        mkt7.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt7.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt7.setKodKeadaanTanah(kodKeadaanTanahDAO.findById("NT"));
                        mkt7.setKeterangan(nilaian[i]);
                        mkt7.setJenisKeadaan("05");
                        mkt7.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt7);
                        listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "05");
                    }
                }
            }
        }
        if (pihakB != null) {
            if (pihakB.length > 0) {
                for (int i = 0; i < pihakB.length; i++) {
                    mohonKeadaanTanah = mohonPerihalTanahService.findByIdMHAndKod(idMohonHakmilik, pihakB[i]);
                    if (mohonKeadaanTanah != null) {

                    } else {
                        MohonKeadaanTanah mkt7 = new MohonKeadaanTanah();
                        mkt7.setHakmilikPermohonan(hakmilikPermohonan);
                        mkt7.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                        mkt7.setKodKeadaanTanah(kodKeadaanTanahDAO.findById("PBL"));
                        mkt7.setKeterangan(pihakB[i]);
                        mkt7.setJenisKeadaan("05");
                        mkt7.setInfoAudit(ia);
                        mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt7);
                        listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "05");
                    }
                }
            }
        }
        if (jarakKeadaan != null) {
            MohonKeadaanTanah mkt8 = new MohonKeadaanTanah();
            mkt8.setHakmilikPermohonan(hakmilikPermohonan);
            mkt8.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
            mkt8.setKodKeadaanTanah(kodKeadaanTanahDAO.findById("JRK"));
            mkt8.setKeterangan(jarakKeadaan);
            mkt8.setJenisKeadaan("08");
            mkt8.setInfoAudit(ia);
            mohonPerihalTanahService.simpanMohonKeadaanTanah(mkt8);
            listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "08");
            jarakKeadaan = "Jarak Tapak Yang Dipohon Dari Jalan Utama " + jarakKeadaan;
        }
        listMohonKeadaanTanah = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "01");
        listMohonKeadaanTanah2 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "02");
        listMohonKeadaanTanah3 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "03");
        listMohonKeadaanTanah4 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "04");
        listMohonKeadaanTanah5 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "05");
        listMohonKeadaanTanah6 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "06");
        listMohonKeadaanTanah7 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "07");
        listMohonKeadaanTanah8 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "08");
        listMohonKeadaanTanah9 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "09");
        listMohonKeadaanTanah10 = mohonPerihalTanahService.findByIdMohon(idMohonHakmilik, "10");

        kodKeadaanTanahList = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("01");
        kodKeadaanTanahList2 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("02");
        kodKeadaanTanahList3 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("03");
        kodKeadaanTanahList4 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("04");
        kodKeadaanTanahList5 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("05");
        kodKeadaanTanahList6 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("06");
        kodKeadaanTanahList7 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("07");
        kodKeadaanTanahList9 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("09");
        kodKeadaanTanahList10 = mohonPerihalTanahService.findKodKeadaanTanahByJenisKeadaan("10");

        return new JSP("laporanTanah/keadaan_semasaV3.jsp").addParameter("tab", "true");
    }

    public Resolution lampiranD() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));

        MohonLotSepadan mlsLampiranD = new MohonLotSepadan();
        mlsLampiranD.setJenisSempadan("LampiranD");
        mlsLampiranD.setHakmilikPermohonan(hakmilikPermohonan);
        mohonPerihalTanahService.simpanMohonLotSepadan(mlsLampiranD);

        mohonLotSepadanLisLampiranD = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "LampiranD");
        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotSepadan(idMohonHakmilik);

        mohonLotSepadanLisLampiranUtara = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Utara");
        mohonLotSepadanLisLampiranTimur = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Timur");
        mohonLotSepadanLisLampiranBarat = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Barat");
        mohonLotSepadanLisLampiranSelatan = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Selatan");
        mohonLotSepadanLisLampiranPanorama = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Panorama");
        mohonLotSepadanLisLampiranLokasi = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "Lokasi");

        return new JSP("laporanTanah/lot_sempadanV3.jsp").addParameter("tab", "true");
    }

    public Resolution simpanJTK() {

        if (kodJTK != null) {
            kodJTK = getContext().getRequest().getParameter("kodJTK");
        }
        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));

        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(peng);

        mohonAgensiTanah = new MohonAgensiTanah();
        mohonAgensiTanah.setInfoAudit(ia);
        if (kodJTK != null) {
            KodAgensi kodAgensi = kodAgensiDAO.findById(kodJTK);
            mohonAgensiTanah.setKodAgensi(kodAgensi);
        }
        mohonAgensiTanah.setPermohonan(hakmilikPermohonan.getPermohonan());
        mohonPerihalTanahService.simpanAgensiTanah(mohonAgensiTanah);

        mohonSyorLaporanTanahList = mohonPerihalTanahService.findByIdMHSyorLaporanTanah(hakmilikPermohonan.getPermohonan().getIdPermohonan());
        mohonSyorLaporanTanahList.add(syorLaporanTanah);
        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
        senaraiJTKSimpan = mohonPerihalTanahService.findJabatanTeknikalList(idPermohonan);

        return new JSP("laporanTanah/perakuan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteJTEK() {
        idJtek = getContext().getRequest().getParameter("idJtek");
//        idPermohonan = getContext().getRequest().getParameter("idPermohonan");

        mohonAgensiTanah = mohonAgensiTanahDAO.findById(Long.parseLong(idJtek));
        idPermohonan = mohonAgensiTanah.getPermohonan().getIdPermohonan();
        if (mohonAgensiTanah != null) {
            mohonPerihalTanahService.deleteAgensiTanah(mohonAgensiTanah);
        }
        senaraiJTKSimpan = mohonPerihalTanahService.findJabatanTeknikalList(idPermohonan);
        mohonSyorLaporanTanahList = mohonPerihalTanahService.findByIdMHSyorLaporanTanah(idPermohonan);
        mohonSyorLaporanTanahList.add(syorLaporanTanah);
        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
        addSimpleMessage("Hapus Telah Berjaya");

        return new JSP("laporanTanah/perakuan.jsp").addParameter("tab", "true");
    }

    public Resolution perakuanSyor() {
        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
        senaraiJTKSimpan = mohonPerihalTanahService.findJabatanTeknikalList(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));

        return new JSP("laporanTanah/perakuan.jsp").addParameter("tab", "true");
    }

    public Resolution deleteLampiranD() {
        idlaporTnhSmpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotSepadan(idMohonHakmilik);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));

        MohonLotSepadan mls = mohonLotSepadanDAO.findById(Long.parseLong(idlaporTnhSmpdn));
        if (mls != null) {
            if (mls.getDokumen() != null) {
                Dokumen doc = dokumenDAO.findById(mls.getDokumen().getIdDokumen());
                dokumenDAO.delete(doc);
            }
            mohonPerihalTanahService.deleteMohonLotSepadan(mls);
            addSimpleMessage("Hapus Telah Berjaya");
        }

        mohonLotSepadanLisLampiranD = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "LampiranD");

        return new JSP("laporanTanah/lot_sempadanV3.jsp").addParameter("tab", "true");
    }

    public Resolution lotSempadan() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotSepadan(idMohonHakmilik);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (mohonLotSepadanList.size() <= 0) {

            InfoAudit ia = new InfoAudit();
            ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(peng);

            MohonLotSepadan mlsUtara = new MohonLotSepadan();
            mlsUtara.setJenisSempadan("Utara");
            mlsUtara.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsUtara);

            MohonLotSepadan mlsSelatan = new MohonLotSepadan();
            mlsSelatan.setJenisSempadan("Selatan");
            mlsSelatan.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsSelatan);

            MohonLotSepadan mlsTimur = new MohonLotSepadan();
            mlsTimur.setJenisSempadan("Timur");
            mlsTimur.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsTimur);

            MohonLotSepadan mlsBarat = new MohonLotSepadan();
            mlsBarat.setJenisSempadan("Barat");
            mlsBarat.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsBarat);

            MohonLotSepadan mlsLokasi = new MohonLotSepadan();
            mlsLokasi.setJenisSempadan("Lokasi");
            mlsLokasi.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsLokasi);

            MohonLotSepadan mlsPanorama = new MohonLotSepadan();
            mlsPanorama.setJenisSempadan("Panorama");
            mlsPanorama.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsPanorama);

            MohonLotSepadan mlsGoogle = new MohonLotSepadan();
            mlsPanorama.setJenisSempadan("Google");
            mlsPanorama.setHakmilikPermohonan(hakmilikPermohonan);
            mohonPerihalTanahService.simpanMohonLotSepadan(mlsGoogle);

            mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotSepadan(idMohonHakmilik);

        }
        mohonLotSepadanLisLampiranD = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, "LampiranD");

        return new JSP("laporanTanah/lot_sempadanV3.jsp").addParameter("tab", "true");
    }

    public Resolution uploadDocUTBS() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, pandanganImej);
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (idlaporTnhSmpdn != null) {
            mohonLotSepadan = mohonLotSepadanDAO.findById(Long.parseLong(idlaporTnhSmpdn));
        }
//        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotSepadan(idMohonHakmilik);

        return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");

    }

    public Resolution simpanImejLaporan() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pandanganImej = getContext().getRequest().getParameter("pandanganImej");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        idlaporTnhSmpdn = getContext().getRequest().getParameter("idlaporTnhSmpdn");
        catatan = getContext().getRequest().getParameter("catatan");
        noLot = getContext().getRequest().getParameter("noLot");
        jenisTanahGambar = getContext().getRequest().getParameter("jenisTanahGambar");
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(peng);
        mohonLotSepadanList = mohonPerihalTanahService.findByIdMohonMohonLotBySepadan(idMohonHakmilik, pandanganImej);
//        if (mohonLotSepadanList.size() > 0) {
//            idlaporTnhSmpdn = String.valueOf(mohonLotSepadanList.get(0).getId());
//        }

        if (idPermohonan != null) {
            permohonan = permohonanServ.findById(idPermohonan);
            String dokumenPath = disLaporanTanahService.getConf().getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null) {
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
            }
            if (pandanganImej == "LampiranD") {
                if (jenisTanahGambar == null) {
                    addSimpleError("Sila masukkan Jenis Tanah.");
                    return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
                }
            }

//            if (fileToBeUpload == null) {
//                addSimpleError("Please select file to be Upload.");
//                return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
////            } else if (fileToBeUpload != null) {
////                addSimpleError("Please select valid Image.");
////                return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
//            }
            if ((catatan != null) && (fileToBeUpload != null)) {

                Dokumen doc = commonService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), ia, permohonan);

                MohonLotSepadan mls = mohonLotSepadanDAO.findById(Long.parseLong(idlaporTnhSmpdn));
                if (pandanganImej.equals("Utara") || pandanganImej.equals("Timur") || pandanganImej.equals("Selatan")
                        || pandanganImej.equals("Barat") || pandanganImej.equals("Google") || pandanganImej.equals("Panorama") || pandanganImej.equals("Lokasi")) {

                    if (!mls.equals(null)) {
                        mls.setCatatanAtsTnh(catatan);
                        mls.setNoLot(noLot);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mls.setDokumen(doc);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    }
                } else if (pandanganImej.equals("LampiranD")) {
                    if (!mls.equals(null)) {
                        mls.setCatatanImg(catatan);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mls.setDokumen(doc);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    }
                } else if (pandanganImej.equals("Google")) {
                    if (!mls.equals(null)) {
                        mls.setCatatanImg(catatan);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mls.setDokumen(doc);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    }
                }
                addSimpleMessage("Muat naik fail berjaya.");

                String inputImagePath = disLaporanTanahService.getConf().getProperty("document.path") + doc.getNamaFizikal();
                String outputImagePath1 = disLaporanTanahService.getConf().getProperty("document.path") + doc.getNamaFizikal();
                String outputImagePath2 = disLaporanTanahService.getConf().getProperty("document.path") + doc.getNamaFizikal();
                String outputImagePath3 = disLaporanTanahService.getConf().getProperty("document.path") + doc.getNamaFizikal();
                logger.info(inputImagePath);

                try {
                    // resize to a fixed width (not proportional)
//                    int scaledWidth = 1024;
//                    int scaledHeight = 768;
//                    laporanTanahV3.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

                    // resize smaller by 50%
                    double percent = 0.5;
                    laporanTanahV3.resize(inputImagePath, outputImagePath2, percent);

//                     resize bigger by 50%
//                    percent = 1.5;
//                    laporanTanahV3.resize(inputImagePath, outputImagePath3, percent);
                } catch (IOException ex) {
                    System.out.println("Error resizing the image.");
                    ex.printStackTrace();
                }

            } else if ((catatan != null) && (fileToBeUpload == null)) {
                MohonLotSepadan mls = mohonLotSepadanDAO.findById(Long.parseLong(idlaporTnhSmpdn));
                if (!mls.getDokumen().equals(null)) {
                    if (pandanganImej.equals("Utara") || pandanganImej.equals("Timur") || pandanganImej.equals("Selatan") || pandanganImej.equals("Barat")) {
                        mls.setCatatanAtsTnh(catatan);
                        mls.setNoLot(noLot);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
//                        }
                    } else if (pandanganImej.equals("LampiranD")) {
                        mls.setCatatanImg(catatan);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    } else if (pandanganImej.equals("Google")) {
                        mls.setCatatanAtsTnh(catatan);
                        mls.setNoLot(noLot);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);

                    } else if (pandanganImej.equals("Lokasi")) {
                        mls.setCatatanAtsTnh(catatan);
                        mls.setNoLot(noLot);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    } else if (pandanganImej.equals("Panorama")) {
                        mls.setCatatanAtsTnh(catatan);
                        mls.setNoLot(noLot);
                        mls.setMilikKerajaan(jenisTanahGambar);
                        mohonPerihalTanahService.simpanMohonLotSepadan(mls);
                    }
                    addSimpleMessage("Kemaskini Telah Berjaya");
                } else {
                    addSimpleError("Sila Pastikan Anda Memilih Gambar Bersekali Untuk Kali Pertama Mengemaskini");
                }
            }
            catatan = new String();
        }
        getContext().getRequest().setAttribute("a", Boolean.FALSE);
        if (pandanganImej.equals("Utara") || pandanganImej.equals("Timur") || pandanganImej.equals("Selatan") || pandanganImej.equals("Barat")) {
            return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
        } else {
            return new JSP("laporanTanah/uploadImage.jsp").addParameter("popup", "true");
        }

    }

    public Resolution kemaskiniPerakuan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");

        if (idMohonHakmilik != null) {
            hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        }
        if (idLaporTanah != null) {
            syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
            if (syorLaporanTanah != null) {
                ulasanPerihal = syorLaporanTanah.getNilaiBaru();
            } else {
                ulasanPerihal = "";
            }
        }

        return new JSP("laporanTanah/kemaskini_perakuan.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKemaskini() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        ulasanPerihal = getContext().getRequest().getParameter("ulasanPerihal");
        if (idLaporTanah != null) {
            syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));

            if (syorLaporanTanah != null) {
                syorLaporanTanah.setNilaiBaru(ulasanPerihal);
                mohonPerihalTanahService.simpanSyorLaporanTanah(syorLaporanTanah);
                ulasanPerihal = syorLaporanTanah.getNilaiBaru();
                addSimpleMessage("Data Telah Berjaya Disimpan");
            } else {
                ulasanPerihal = "";
            }
        }
        return new JSP("laporanTanah/kemaskini_perakuan.jsp").addParameter("popup", "true");
    }

    private List<Map<String, String>> getPermohonanWithTaskID(Pengguna pengguna) throws Exception {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanServ.findById(idPermohonan);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Map<String, String> map = null;
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        map = new HashMap<String, String>();
        map.put("idPermohonan", permohonan.getIdPermohonan());
        map.put("taskId", taskId);
        list.add(map);
//        }
        return list;
    }

    public Resolution Perakuan() throws Exception {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        ctx = WorkFlowService.authenticate(peng.getIdPengguna());
        StringBuilder sb = new StringBuilder();
        List<Map<String, String>> list = getPermohonanWithTaskID(peng);
        for (Map<String, String> map : list) {
            String taskID = map.get("taskId");
            idPermohonan = map.get("idPermohonan");
            if (sb.length() > 0) {
                sb.append(",");
            }

            peng = penggunaDao.findById(peng.getIdPengguna());
            try {
//                 Task task = WorkFlowService.getTask(taskId, ctx);
//                Task task = WorkFlowService.reassignTask(ctx, taskId, peng.getIdPengguna(), "user");
//                idAliran = task.getSystemAttributes().getStage();
//        idAliran = "semak_laporanTanah";
                idAliran = "laporan_tanah";
//        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
                if (idMohonHakmilik != null) {
                    hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
//            List<FasaPermohonan> listMohonFasa = pelupusanService.findFasaPermohonanByIdMohonOrderBy(idPermohonan);
//            fasaPermohonan = listMohonFasa.get(0);
//            idAliran = fasaPermohonan.getIdAliran();
                    mohonSyorLaporanTanahList = mohonPerihalTanahService.findByIdMHSyorLaporanTanah(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                    KodUrusan kodUrusan = hakmilikPermohonan.getPermohonan().getKodUrusan();
                    if (mohonSyorLaporanTanahList.size() <= 0) {
                        if (kodUrusan != null) {
                            SyorLaporanTanah syorLaporanTanahNew = new SyorLaporanTanah();
                            if ((kodUrusan.getKod().equals("831")) || (kodUrusan.getKod().equals("TSPSS")) || (kodUrusan.getKod().equals("TSPSN")) || (kodUrusan.getKod().equals("TSKSN"))
                                    || (kodUrusan.getKod().equals("TSKKT")) || (kodUrusan.getKod().equals("TSBSN")) || (kodUrusan.getKod().equals("PPCS")) || (kodUrusan.getKod().equals("TSPSS"))
                                    || (kodUrusan.getKod().equals("PPCB")) || (kodUrusan.getKod().equals("SBKT")) || (kodUrusan.getKod().equals("SBST")) || (kodUrusan.getKod().equals("SBMS"))) {
//                        for (int i = 0; i < Pengambilan.length; i++) {
//                            SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
//                            syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
//                            syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
//                            syorLaporanTanah1.setItem(Pengambilan[i]);
//                            mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
//                        }
                            } else if (kodUrusan.getKod().equals("PLPT") || kodUrusan.getKod().equals("PBMT")) {
                                for (int i = 0; i < berimilikAndPLPT.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(berimilikAndPLPT[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            } else if (kodUrusan.getKod().equals("LPS")) {
                                for (int i = 0; i < LPS.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(LPS[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            } else if (kodUrusan.getKod().equals("PPRUS")) {
                                for (int i = 0; i < PermitRuangUdara.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(PermitRuangUdara[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);

                                }
                            } else if (kodUrusan.getKod().equals("PRIZ")) {
                                for (int i = 0; i < Rizab.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(Rizab[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            } else if (kodUrusan.getKod().equals("PBPTD") || (kodUrusan.getKod().equals("PPBB")
                                    || (kodUrusan.getKod().equals("BPBB") || (kodUrusan.getKod().equals("PBPTG")
                                    || (kodUrusan.getKod().equals("PBMMK")))))) {
                                for (int i = 0; i < PermitBahanBatuan.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(PermitBahanBatuan[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            } else if (kodUrusan.getKod().equals("PRMP")) {
                                for (int i = 0; i < PermitPertanian.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(PermitPertanian[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            } else if (kodUrusan.getKod().equals("831")) {
                                for (int i = 0; i < Pengambilan.length; i++) {
                                    SyorLaporanTanah syorLaporanTanah1 = new SyorLaporanTanah();
                                    syorLaporanTanah1.setHakmilikPermohonan(hakmilikPermohonan);
                                    syorLaporanTanah1.setIdPermohonan(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                                    syorLaporanTanah1.setItem(PermitPertanian[i]);
                                    mohonSyorLaporanTanahListBaru.add(syorLaporanTanah1);
                                }
                            }
                        }
                    }

                    if (kodUrusan.getKod().equals("831") || kodUrusan.getKod().equals("SEK4")) {
                        if (idAliran.equals("laporan_tanah")) {
                            syorLaporanTanah = mohonPerihalTanahService.findByIdMohonSyorLaporanTanah(idPermohonan, "Syor Perakuan");

                            if (syorLaporanTanah == null) {
                                SyorLaporanTanah syorLaporanTanah2 = new SyorLaporanTanah();
                                syorLaporanTanah2.setItem("Syor Perakuan");
                                syorLaporanTanah2.setIdPermohonan(idPermohonan);
                                mohonSyorLaporanTanahListBaru.add(syorLaporanTanah2);
                                for (SyorLaporanTanah SLT : mohonSyorLaporanTanahListBaru) {
                                    mohonPerihalTanahService.simpanSyorLaporanTanah(SLT);
                                }
                            }
                        } else if (idAliran.equals("semak_laporanTanah")) {
                            syorLaporanTanah = mohonPerihalTanahService.findByIdMohonSyorLaporanTanah(idPermohonan, "Syor Perakuan Kanan");
                            if (syorLaporanTanah == null) {
                                SyorLaporanTanah syorLaporanTanah2 = new SyorLaporanTanah();
                                syorLaporanTanah2.setItem("Syor Perakuan Kanan");
                                syorLaporanTanah2.setIdPermohonan(idPermohonan);
                                mohonSyorLaporanTanahListBaru.add(syorLaporanTanah2);
                                for (SyorLaporanTanah SLT : mohonSyorLaporanTanahListBaru) {
                                    mohonPerihalTanahService.simpanSyorLaporanTanah(SLT);
                                }
                            }
                        }
                    }
                }
                senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
                mohonSyorLaporanTanahList = mohonPerihalTanahService.findByIdMHSyorLaporanTanah(hakmilikPermohonan.getPermohonan().getIdPermohonan());
//        mohonSyorLaporanTanahList.add(syorLaporanTanah);
                senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();
                senaraiJTKSimpan = mohonPerihalTanahService.findJabatanTeknikalList(hakmilikPermohonan.getPermohonan().getIdPermohonan());
//        String idAliran = "semak_laporanTanah";

            } catch (Exception ex) {
                ex.printStackTrace();
//                addSimpleError("Agihan Tugasan Tidak Berjaya. Sila hubungi Pentadbir Sistem.");
                return new JSP("laporanTanah/perakuan.jsp").addParameter("popup", "true");
            }
        }
        return new JSP("laporanTanah/perakuan.jsp").addParameter("popup", "true");
    }

    public Resolution jabatanTeknikal() {

        senaraiJTK = mohonPerihalTanahService.findJabatanTeknikal();

        return new JSP("laporanTanah/jabatanTeknikal.jsp").addParameter("popup", "true");
    }

    public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
                logger.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
            listKodSekatan = disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2());
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Sekatan");
            }
        }
        return new JSP("laporanTanah/searchSekatanKpntngn_New.jsp").addParameter("popup", "true");
    }

    public Resolution findKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();

        syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (hakmilikPermohonan.getHakmilik() != null) {
            idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        }
        if (hakmilikPermohonan.getNoLot() != null) {
            noPtSementara = hakmilikPermohonan.getNoLot();
        }
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPtTemp = new NoPt();
//            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
            if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, noPtSementara}, 3);

            } else {
                hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
            }

        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PJLB")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT") || (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT"))) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                } else {
                    kc = peng.getKodCawangan().getKod();
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), kc, hakmilikPermohonan.getHakmilik().getSekatanKepentingan().getKod()));
//                LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            } else {
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Kod Sekatan Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", kc, disSyaratSekatan.getSekatKpntgn2()));
            if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Sekatan");
            }
        }
        return new JSP("laporanTanah/searchSekatanKpntngn_New.jsp").addParameter("popup", "true");
//        return new JSP(DisPermohonanPage.getLT_SEKATAN_PAGE()).addParameter("popup", "true");
    }

    public Resolution searchSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        idLaporTanah = getContext().getRequest().getParameter("idLaporTanah");
        syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        String kategoriTanah = new String();

        hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{hakmilikPermohonan.getPermohonan().getIdPermohonan()}, 1);
//        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
        disSyaratSekatan = new DisSyaratSekatan();
        disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", peng.getKodCawangan().getKod(), kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
        if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
            addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
        }

        return new JSP("laporanTanah/searchSyaratNyata_New.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (hakmilikPermohonan != null) {
            idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
            if (disSyaratSekatan != null) {
                if (!StringUtils.isEmpty(disSyaratSekatan.getKod())) {
                    KodSyaratNyata kodSN = new KodSyaratNyata();
                    String syarat = disSyaratSekatan.getKod();
                    kodSN = kodSyaratNyataDAO.findById(syarat);
                    hakmilikPermohonan.setSyaratNyataBaru(kodSN);
                    hakmilikPermohonanServ.save(hakmilikPermohonan);
                    addSimpleMessage("Kemasukan Berjaya");

                    syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
                    syorLaporanTanah.setNilaiBaru(kodSN.getSyarat());
                    mohonPerihalTanahService.simpanSyorLaporanTanah(syorLaporanTanah);
                }
            }
            HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
            String kategoriTanah = new String();

            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{hakmilikPermohonan.getPermohonan().getIdPermohonan()}, 1);
//        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", peng.getKodCawangan().getKod(), kategoriTanah, disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }

        }
        return new JSP("laporanTanah/searchSyaratNyata_New.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKodSekatan() {

        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (hakmilikPermohonan != null) {
            idMohonHakmilik = String.valueOf(hakmilikPermohonan.getId());
            if (disSyaratSekatan != null) {
                if (!StringUtils.isEmpty(disSyaratSekatan.getKodSktn())) {
                    KodSekatanKepentingan kodSekatan = new KodSekatanKepentingan();
                    String sekatan = disSyaratSekatan.getKodSktn();
                    kodSekatan = kodSekatanKepentinganDAO.findById(sekatan);
                    hakmilikPermohonan.setSekatanKepentinganBaru(kodSekatan);
                    hakmilikPermohonanServ.save(hakmilikPermohonan);
                    addSimpleMessage("Kemasukan Berjaya");

                    syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
                    syorLaporanTanah.setNilaiBaru(kodSekatan.getSekatan());
                    mohonPerihalTanahService.simpanSyorLaporanTanah(syorLaporanTanah);
                }
            }

            syorLaporanTanah = syorLaporanTanahDAO.findById(Long.parseLong(idLaporTanah));
            hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
            if (disSyaratSekatan != null) {
                if (!StringUtils.isEmpty(disSyaratSekatan.getKodSekatanKepentingan())) {
                    disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan(disSyaratSekatan.getKodSekatanKepentingan(), peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
                    logger.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSekatan().size());
                    if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                        addSimpleError("Kod Sekatan Tidak Sah");
                    }
                } else {
                    disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
                    if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                        addSimpleError("Kod Sekatan Tidak Sah");
                    }
                }
            } else {
                disSyaratSekatan = new DisSyaratSekatan();
                disSyaratSekatan.setListKodSekatan(disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2()));
                listKodSekatan = disLaporanTanahService.getPlpservice().searchKodSekatan("%", peng.getKodCawangan().getKod(), disSyaratSekatan.getSekatKpntgn2());
                if (disSyaratSekatan.getListKodSekatan().size() < 1) {
                    addSimpleError("Tiada Maklumat pada Table Sekatan");
                }
            }
        }

        return new JSP("laporanTanah/searchSekatanKpntngn_New.jsp").addParameter("popup", "true");
    }

    public Resolution searchSyarat() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = new String();
        String kategoriTanah = new String();

//        noPtSementara = getContext().getRequest().getParameter(("noPtSementara"));
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (hakmilikPermohonan.getHakmilik() != null) {
            idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        }
        if (hakmilikPermohonan.getNoLot() != null && !hakmilikPermohonan.getLot().getKod().equals("2")) {
            noPtSementara = hakmilikPermohonan.getNoLot();
        }
        HakmilikPermohonan hakmilikPermohonanSave = new HakmilikPermohonan();
        if ((!StringUtils.isEmpty(idHakmilik)) && (StringUtils.isEmpty(noPtSementara))) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{idPermohonan, idHakmilik}, 0);
        } else if ((StringUtils.isEmpty(idHakmilik)) && (!StringUtils.isEmpty(noPtSementara))) {
//            noPtTemp = new NoPt();
//            noPtTemp = (NoPt) disLaporanTanahService.findObject(noPtTemp, new String[]{noPtSementara}, 1);
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{noPtSementara}, 2);
        } else if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PJLB")) {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan, idHakmilik}, 0);
        } else {
            hakmilikPermohonanSave = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonanSave, new String[]{idPermohonan}, 1);
        }
        if (hakmilikPermohonanSave != null) {
            if (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBMT") || (hakmilikPermohonanSave.getPermohonan().getKodUrusan().getKod().equals("PBGSA"))) {
                if (hakmilikPermohonanSave.getKodHakmilik() != null) {
                    if (hakmilikPermohonanSave.getKodHakmilik().getKod().equals("PN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("GRN") || hakmilikPermohonanSave.getKodHakmilik().getKod().equals("HSD")) {
                        kc = disLaporanTanahService.getKodCawanganDAO().findById("00").getKod();
                    } else {
                        kc = peng.getKodCawangan().getKod();
                    }
                } else {
                    kc = peng.getKodCawangan().getKod();
                }
            } else {
                kc = peng.getKodCawangan().getKod();
            }
            if (hakmilikPermohonanSave.getHakmilik() != null) {
                if (hakmilikPermohonan.getPermohonan().getKodUrusan().getKod().equals("PBMT")) {
//                    kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
                    kategoriTanah = hakmilikPermohonan.getKategoriTanahBaru().getKod();
                } else {
                    kategoriTanah = hakmilikPermohonanSave.getHakmilik().getKategoriTanah() != null ? hakmilikPermohonanSave.getHakmilik().getKategoriTanah().getKod() : new String();
                }

            } else {
                kategoriTanah = hakmilikPermohonanSave.getKategoriTanahBaru() != null ? hakmilikPermohonanSave.getKategoriTanahBaru().getKod() : new String();
            }
        } else {
            kc = peng.getKodCawangan().getKod();
        }
        if (disSyaratSekatan != null) {
            if (!StringUtils.isEmpty(disSyaratSekatan.getKodSyaratNyata())) {
                if (disSyaratSekatan.getSyaratNyata2() != null) {
                    disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew(disSyaratSekatan.getKodSyaratNyata(), kc, hakmilikPermohonan.getHakmilik().getKategoriTanah().getKod(), disSyaratSekatan.getSyaratNyata2()));
//                    LOG.info("listKodSyaratNyata.size : " + disSyaratSekatan.getListKodSyaratNyata().size());
                    if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                        addSimpleError("Kod Syarat Nyata Tidak Sah");
                    }
                } else if (disSyaratSekatan.getKodSyaratNyata() != null) {
//                    disSyaratSekatan.setListKodSyaratNyata(pelupusanService.searchKodSyaratNyataOnly(disSyaratSekatan.getKodSyaratNyata()));
                }
            } else {
                disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, hakmilikPermohonan.getHakmilik().getKategoriTanah().getKod(), disSyaratSekatan.getSyaratNyata2()));
                if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                    addSimpleError("Kod Syarat Nyata Tidak Sah");
                }
            }
        } else {
            disSyaratSekatan = new DisSyaratSekatan();
            disSyaratSekatan.setListKodSyaratNyata(disLaporanTanahService.getPlpservice().searchKodSyaratNyataNew("%", kc, hakmilikPermohonan.getHakmilik().getKategoriTanah().getKod(), disSyaratSekatan.getSyaratNyata2()));
            if (disSyaratSekatan.getListKodSyaratNyata().size() < 1) {
                addSimpleError("Tiada Maklumat pada Table Syarat Nyata");
            }
        }
//        return new JSP("pelupusan/laporan_tanah/laporanTanahNew/searchSyaratNyata_New.jsp").addParameter("popup", "true");
//        return new JSP(DisPermohonanPage.getLT_SYARATNYATA_PAGE()).addParameter("popup", "true");
        return new JSP("laporanTanah/searchSyaratNyata_New.jsp").addParameter("popup", "true");
    }

    public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

    public static void resize(String inputImagePath,
            String outputImagePath, double percent) throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        int scaledWidth = (int) (inputImage.getWidth() * percent);
        int scaledHeight = (int) (inputImage.getHeight() * percent);
        resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
    }

    public Resolution statusTanah() {
        jenisStatusTanah = getContext().getRequest().getParameter("jenisStatusTanah");
        idMohonHakmilik = getContext().getRequest().getParameter("idMohonHakmilik");
        hakmilikPermohonan = hakmilikPermohonanServ.findHakmilikPermohonanbyidMH(Long.parseLong(idMohonHakmilik));
        if (jenisStatusTanah.equals("kerajaan")) {
            listMohonStatusTanah = mohonStatusTanahService.findMohonPerihalTanahByIdMh(hakmilikPermohonan.getId(), "01");
        } else if (jenisStatusTanah.equals("milik")) {

        }

        return new JSP("laporanTanah/status_tanahV3.jsp").addParameter("tab", "true");
    }

    public String currentStageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        //stageId = "g_penyediaan_pu_pt";
        return stageId;
    }

    public void tanahKerajaan() {

    }

    public void tanahMilik() {

    }

    public List<MohonPerihalTanah> getListMohonPerihalTanah() {
        return listMohonPerihalTanah;
    }

    public void setListMohonPerihalTanah(List<MohonPerihalTanah> listMohonPerihalTanah) {
        this.listMohonPerihalTanah = listMohonPerihalTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdMohonHakmilik() {
        return idMohonHakmilik;
    }

    public void setIdMohonHakmilik(String idMohonHakmilik) {
        this.idMohonHakmilik = idMohonHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList2() {
        return hakmilikPermohonanList2;
    }

    public void setHakmilikPermohonanList2(List<HakmilikPermohonan> hakmilikPermohonanList2) {
        this.hakmilikPermohonanList2 = hakmilikPermohonanList2;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKampung() {
        return kampung;
    }

    public void setKampung(String kampung) {
        this.kampung = kampung;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getDun() {
        return dun;
    }

    public void setDun(String dun) {
        this.dun = dun;
    }

    public String getParlimen() {
        return parlimen;
    }

    public void setParlimen(String parlimen) {
        this.parlimen = parlimen;
    }

    public String getPBT() {
        return PBT;
    }

    public void setPBT(String PBT) {
        this.PBT = PBT;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTnb() {
        return tnb;
    }

    public void setTnb(String tnb) {
        this.tnb = tnb;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonanStatusTanah() {
        return hakmilikPermohonanStatusTanah;
    }

    public void setHakmilikPermohonanStatusTanah(HakmilikPermohonan hakmilikPermohonanStatusTanah) {
        this.hakmilikPermohonanStatusTanah = hakmilikPermohonanStatusTanah;
    }

    public String getJenisStatusTanah() {
        return jenisStatusTanah;
    }

    public void setJenisStatusTanah(String jenisStatusTanah) {
        this.jenisStatusTanah = jenisStatusTanah;
    }

    public List<MohonStatusTanah> getListMohonStatusTanah() {
        return listMohonStatusTanah;
    }

    public void setListMohonStatusTanah(List<MohonStatusTanah> listMohonStatusTanah) {
        this.listMohonStatusTanah = listMohonStatusTanah;
    }

    public String getJenisTanah() {
        return jenisTanah;
    }

    public void setJenisTanah(String jenisTanah) {
        this.jenisTanah = jenisTanah;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getRujFail() {
        return rujFail;
    }

    public void setRujFail(String rujFail) {
        this.rujFail = rujFail;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public String getSeksyen() {
        return seksyen;
    }

    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public String getPelanWarta() {
        return pelanWarta;
    }

    public void setPelanWarta(String pelanWarta) {
        this.pelanWarta = pelanWarta;
    }

    public String getPengawal() {
        return pengawal;
    }

    public void setPengawal(String pengawal) {
        this.pengawal = pengawal;
    }

    public String getDiberimilik() {
        return diberimilik;
    }

    public void setDiberimilik(String diberimilik) {
        this.diberimilik = diberimilik;
    }

    public KodStatusTanah getKodStatus() {
        return kodStatus;
    }

    public void setKodStatus(KodStatusTanah kodStatus) {
        this.kodStatus = kodStatus;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList() {
        return kodKeadaanTanahList;
    }

    public void setKodKeadaanTanahList(List<KodKeadaanTanah> kodKeadaanTanahList) {
        this.kodKeadaanTanahList = kodKeadaanTanahList;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList2() {
        return kodKeadaanTanahList2;
    }

    public void setKodKeadaanTanahList2(List<KodKeadaanTanah> kodKeadaanTanahList2) {
        this.kodKeadaanTanahList2 = kodKeadaanTanahList2;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList3() {
        return kodKeadaanTanahList3;
    }

    public void setKodKeadaanTanahList3(List<KodKeadaanTanah> kodKeadaanTanahList3) {
        this.kodKeadaanTanahList3 = kodKeadaanTanahList3;
    }

    public String[] getKeadaanTanah() {
        return keadaanTanah;
    }

    public void setKeadaanTanah(String[] keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }

    public String[] getKeadaanBekalan() {
        return keadaanBekalan;
    }

    public void setKeadaanBekalan(String[] keadaanBekalan) {
        this.keadaanBekalan = keadaanBekalan;
    }

    public String[] getKeadaanLintas() {
        return keadaanLintas;
    }

    public void setKeadaanLintas(String[] keadaanLintas) {
        this.keadaanLintas = keadaanLintas;
    }

    public String getJalanMasuk() {
        return jalanMasuk;
    }

    public void setJalanMasuk(String jalanMasuk) {
        this.jalanMasuk = jalanMasuk;
    }

    public String[] getJalanSimpan() {
        return jalanSimpan;
    }

    public void setJalanSimpan(String[] jalanSimpan) {
        this.jalanSimpan = jalanSimpan;
    }

    public String[] getJalanRizab() {
        return jalanRizab;
    }

    public void setJalanRizab(String[] jalanRizab) {
        this.jalanRizab = jalanRizab;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah4() {
        return listMohonKeadaanTanah4;
    }

    public void setListMohonKeadaanTanah4(List<MohonKeadaanTanah> listMohonKeadaanTanah4) {
        this.listMohonKeadaanTanah4 = listMohonKeadaanTanah4;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah5() {
        return listMohonKeadaanTanah5;
    }

    public void setListMohonKeadaanTanah5(List<MohonKeadaanTanah> listMohonKeadaanTanah5) {
        this.listMohonKeadaanTanah5 = listMohonKeadaanTanah5;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah6() {
        return listMohonKeadaanTanah6;
    }

    public void setListMohonKeadaanTanah6(List<MohonKeadaanTanah> listMohonKeadaanTanah6) {
        this.listMohonKeadaanTanah6 = listMohonKeadaanTanah6;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah7() {
        return listMohonKeadaanTanah7;
    }

    public void setListMohonKeadaanTanah7(List<MohonKeadaanTanah> listMohonKeadaanTanah7) {
        this.listMohonKeadaanTanah7 = listMohonKeadaanTanah7;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList4() {
        return kodKeadaanTanahList4;
    }

    public void setKodKeadaanTanahList4(List<KodKeadaanTanah> kodKeadaanTanahList4) {
        this.kodKeadaanTanahList4 = kodKeadaanTanahList4;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList5() {
        return kodKeadaanTanahList5;
    }

    public void setKodKeadaanTanahList5(List<KodKeadaanTanah> kodKeadaanTanahList5) {
        this.kodKeadaanTanahList5 = kodKeadaanTanahList5;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList6() {
        return kodKeadaanTanahList6;
    }

    public void setKodKeadaanTanahList6(List<KodKeadaanTanah> kodKeadaanTanahList6) {
        this.kodKeadaanTanahList6 = kodKeadaanTanahList6;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList7() {
        return kodKeadaanTanahList7;
    }

    public void setKodKeadaanTanahList7(List<KodKeadaanTanah> kodKeadaanTanahList7) {
        this.kodKeadaanTanahList7 = kodKeadaanTanahList7;
    }

    public String[] getBangunan() {
        return bangunan;
    }

    public void setBangunan(String[] bangunan) {
        this.bangunan = bangunan;
    }

    public String[] getTanaman() {
        return tanaman;
    }

    public void setTanaman(String[] tanaman) {
        this.tanaman = tanaman;
    }

    public String getJarakKeadaan() {
        return jarakKeadaan;
    }

    public void setJarakKeadaan(String jarakKeadaan) {
        this.jarakKeadaan = jarakKeadaan;
    }

    public String getValueJarak() {
        return valueJarak;
    }

    public void setValueJarak(String valueJarak) {
        this.valueJarak = valueJarak;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah() {
        return listMohonKeadaanTanah;
    }

    public void setListMohonKeadaanTanah(List<MohonKeadaanTanah> listMohonKeadaanTanah) {
        this.listMohonKeadaanTanah = listMohonKeadaanTanah;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanahDEL() {
        return listMohonKeadaanTanahDEL;
    }

    public void setListMohonKeadaanTanahDEL(List<MohonKeadaanTanah> listMohonKeadaanTanahDEL) {
        this.listMohonKeadaanTanahDEL = listMohonKeadaanTanahDEL;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah2() {
        return listMohonKeadaanTanah2;
    }

    public void setListMohonKeadaanTanah2(List<MohonKeadaanTanah> listMohonKeadaanTanah2) {
        this.listMohonKeadaanTanah2 = listMohonKeadaanTanah2;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah3() {
        return listMohonKeadaanTanah3;
    }

    public void setListMohonKeadaanTanah3(List<MohonKeadaanTanah> listMohonKeadaanTanah3) {
        this.listMohonKeadaanTanah3 = listMohonKeadaanTanah3;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah8() {
        return listMohonKeadaanTanah8;
    }

    public void setListMohonKeadaanTanah8(List<MohonKeadaanTanah> listMohonKeadaanTanah8) {
        this.listMohonKeadaanTanah8 = listMohonKeadaanTanah8;
    }

    public List<MohonLotSepadan> getMohonLotSepadanList() {
        return mohonLotSepadanList;
    }

    public void setMohonLotSepadanList(List<MohonLotSepadan> mohonLotSepadanList) {
        this.mohonLotSepadanList = mohonLotSepadanList;
    }

    public MohonLotSepadan getMohonLotSepadan() {
        return mohonLotSepadan;
    }

    public void setMohonLotSepadan(MohonLotSepadan mohonLotSepadan) {
        this.mohonLotSepadan = mohonLotSepadan;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranD() {
        return mohonLotSepadanLisLampiranD;
    }

    public void setMohonLotSepadanLisLampiranD(List<MohonLotSepadan> mohonLotSepadanLisLampiranD) {
        this.mohonLotSepadanLisLampiranD = mohonLotSepadanLisLampiranD;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranUtara() {
        return mohonLotSepadanLisLampiranUtara;
    }

    public void setMohonLotSepadanLisLampiranUtara(List<MohonLotSepadan> mohonLotSepadanLisLampiranUtara) {
        this.mohonLotSepadanLisLampiranUtara = mohonLotSepadanLisLampiranUtara;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranTimur() {
        return mohonLotSepadanLisLampiranTimur;
    }

    public void setMohonLotSepadanLisLampiranTimur(List<MohonLotSepadan> mohonLotSepadanLisLampiranTimur) {
        this.mohonLotSepadanLisLampiranTimur = mohonLotSepadanLisLampiranTimur;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranBarat() {
        return mohonLotSepadanLisLampiranBarat;
    }

    public void setMohonLotSepadanLisLampiranBarat(List<MohonLotSepadan> mohonLotSepadanLisLampiranBarat) {
        this.mohonLotSepadanLisLampiranBarat = mohonLotSepadanLisLampiranBarat;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranSelatan() {
        return mohonLotSepadanLisLampiranSelatan;
    }

    public void setMohonLotSepadanLisLampiranSelatan(List<MohonLotSepadan> mohonLotSepadanLisLampiranSelatan) {
        this.mohonLotSepadanLisLampiranSelatan = mohonLotSepadanLisLampiranSelatan;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranPanorama() {
        return mohonLotSepadanLisLampiranPanorama;
    }

    public void setMohonLotSepadanLisLampiranPanorama(List<MohonLotSepadan> mohonLotSepadanLisLampiranPanorama) {
        this.mohonLotSepadanLisLampiranPanorama = mohonLotSepadanLisLampiranPanorama;
    }

    public List<MohonLotSepadan> getMohonLotSepadanLisLampiranLokasi() {
        return mohonLotSepadanLisLampiranLokasi;
    }

    public void setMohonLotSepadanLisLampiranLokasi(List<MohonLotSepadan> mohonLotSepadanLisLampiranLokasi) {
        this.mohonLotSepadanLisLampiranLokasi = mohonLotSepadanLisLampiranLokasi;
    }

    public CalcTaxPelupusan getCalcTax() {
        return calcTax;
    }

    public void setCalcTax(CalcTaxPelupusan calcTax) {
        this.calcTax = calcTax;
    }

    public KodKeadaanTanah getKodKeadaanTanah() {
        return kodKeadaanTanah;
    }

    public void setKodKeadaanTanah(KodKeadaanTanah kodKeadaanTanah) {
        this.kodKeadaanTanah = kodKeadaanTanah;
    }

    public MohonKeadaanTanah getMohonKeadaanTanah() {
        return mohonKeadaanTanah;
    }

    public void setMohonKeadaanTanah(MohonKeadaanTanah mohonKeadaanTanah) {
        this.mohonKeadaanTanah = mohonKeadaanTanah;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getIdlaporTnhSmpdn() {
        return idlaporTnhSmpdn;
    }

    public void setIdlaporTnhSmpdn(String idlaporTnhSmpdn) {
        this.idlaporTnhSmpdn = idlaporTnhSmpdn;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(String pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public String getJenisTanahGambar() {
        return jenisTanahGambar;
    }

    public void setJenisTanahGambar(String jenisTanahGambar) {
        this.jenisTanahGambar = jenisTanahGambar;
    }

    public List<SyorLaporanTanah> getMohonSyorLaporanTanahList() {
        return mohonSyorLaporanTanahList;
    }

    public void setMohonSyorLaporanTanahList(List<SyorLaporanTanah> mohonSyorLaporanTanahList) {
        this.mohonSyorLaporanTanahList = mohonSyorLaporanTanahList;
    }

    public SyorLaporanTanah getSyorLaporanTanah() {
        return syorLaporanTanah;
    }

    public void setSyorLaporanTanah(SyorLaporanTanah syorLaporanTanah) {
        this.syorLaporanTanah = syorLaporanTanah;
    }

    public static String[] getBerimilikAndPLPT() {
        return berimilikAndPLPT;
    }

    public static void setBerimilikAndPLPT(String[] berimilikAndPLPT) {
        laporanTanahV3.berimilikAndPLPT = berimilikAndPLPT;
    }

    public static String[] getLPS() {
        return LPS;
    }

    public static void setLPS(String[] LPS) {
        laporanTanahV3.LPS = LPS;
    }

    public static String[] getPermitRuangUdara() {
        return PermitRuangUdara;
    }

    public static void setPermitRuangUdara(String[] PermitRuangUdara) {
        laporanTanahV3.PermitRuangUdara = PermitRuangUdara;
    }

    public static String[] getRizab() {
        return Rizab;
    }

    public static void setRizab(String[] Rizab) {
        laporanTanahV3.Rizab = Rizab;
    }

    public static String[] getPerizaban() {
        return Perizaban;
    }

    public static void setPerizaban(String[] Perizaban) {
        laporanTanahV3.Perizaban = Perizaban;
    }

    public static String[] getPermitBahanBatuan() {
        return PermitBahanBatuan;
    }

    public static void setPermitBahanBatuan(String[] PermitBahanBatuan) {
        laporanTanahV3.PermitBahanBatuan = PermitBahanBatuan;
    }

    public static String[] getPermitPertanian() {
        return PermitPertanian;
    }

    public static void setPermitPertanian(String[] PermitPertanian) {
        laporanTanahV3.PermitPertanian = PermitPertanian;
    }

//    public static String[] getPengambilan() {
//        return Pengambilan;
//    }
//
//    public static void setPengambilan(String[] Pengambilan) {
//        laporanTanahV3.Pengambilan = Pengambilan;
//    }
    public List<SyorLaporanTanah> getMohonSyorLaporanTanahListBaru() {
        return mohonSyorLaporanTanahListBaru;
    }

    public void setMohonSyorLaporanTanahListBaru(List<SyorLaporanTanah> mohonSyorLaporanTanahListBaru) {
        this.mohonSyorLaporanTanahListBaru = mohonSyorLaporanTanahListBaru;
    }

    public MohonPerihalTanahService getMohonPerihalTanahService() {
        return mohonPerihalTanahService;
    }

    public void setMohonPerihalTanahService(MohonPerihalTanahService mohonPerihalTanahService) {
        this.mohonPerihalTanahService = mohonPerihalTanahService;
    }

    public RegService getRegService() {
        return regService;
    }

    public void setRegService(RegService regService) {
        this.regService = regService;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public PermohonanLaporanBangunanDAO getPermohonanLaporanBangunanDAO() {
        return permohonanLaporanBangunanDAO;
    }

    public void setPermohonanLaporanBangunanDAO(PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO) {
        this.permohonanLaporanBangunanDAO = permohonanLaporanBangunanDAO;
    }

    public PermohonanManualDAO getPermohonanManualDAO() {
        return permohonanManualDAO;
    }

    public void setPermohonanManualDAO(PermohonanManualDAO permohonanManualDAO) {
        this.permohonanManualDAO = permohonanManualDAO;
    }

    public LaporanPelukisPelanService getLaporanPelukisPelanService() {
        return laporanPelukisPelanService;
    }

    public void setLaporanPelukisPelanService(LaporanPelukisPelanService laporanPelukisPelanService) {
        this.laporanPelukisPelanService = laporanPelukisPelanService;
    }

    public PermohonanService getPermohonanServ() {
        return permohonanServ;
    }

    public void setPermohonanServ(PermohonanService permohonanServ) {
        this.permohonanServ = permohonanServ;
    }

    public TampalHakmilikService getTampalHakmilikService() {
        return tampalHakmilikService;
    }

    public void setTampalHakmilikService(TampalHakmilikService tampalHakmilikService) {
        this.tampalHakmilikService = tampalHakmilikService;
    }

    public BorangPerHakmilikService getBorangPerhakmilikService() {
        return borangPerhakmilikService;
    }

    public void setBorangPerhakmilikService(BorangPerHakmilikService borangPerhakmilikService) {
        this.borangPerhakmilikService = borangPerhakmilikService;
    }

    public FasaPermohonanService getFasaPermohonanServ() {
        return fasaPermohonanServ;
    }

    public void setFasaPermohonanServ(FasaPermohonanService fasaPermohonanServ) {
        this.fasaPermohonanServ = fasaPermohonanServ;
    }

    public PengambilanService getService() {
        return service;
    }

    public void setService(PengambilanService service) {
        this.service = service;
    }

    public HakmilikPermohonanService getHakmilikPermohonanServ() {
        return hakmilikPermohonanServ;
    }

    public void setHakmilikPermohonanServ(HakmilikPermohonanService hakmilikPermohonanServ) {
        this.hakmilikPermohonanServ = hakmilikPermohonanServ;
    }

    public MohonStatusTanahService getMohonStatusTanahService() {
        return mohonStatusTanahService;
    }

    public void setMohonStatusTanahService(MohonStatusTanahService mohonStatusTanahService) {
        this.mohonStatusTanahService = mohonStatusTanahService;
    }

    public MohonStatusTanahDAO getMohonStatusTanahDAO() {
        return mohonStatusTanahDAO;
    }

    public void setMohonStatusTanahDAO(MohonStatusTanahDAO mohonStatusTanahDAO) {
        this.mohonStatusTanahDAO = mohonStatusTanahDAO;
    }

    public KodStatusTanahDAO getKodStatusTanahDAO() {
        return kodStatusTanahDAO;
    }

    public void setKodStatusTanahDAO(KodStatusTanahDAO kodStatusTanahDAO) {
        this.kodStatusTanahDAO = kodStatusTanahDAO;
    }

    public KodPerihalTanahDAO getKodPerihalTanahDAO() {
        return kodPerihalTanahDAO;
    }

    public void setKodPerihalTanahDAO(KodPerihalTanahDAO kodPerihalTanahDAO) {
        this.kodPerihalTanahDAO = kodPerihalTanahDAO;
    }

    public KodKeadaanTanahDAO getKodKeadaanTanahDAO() {
        return kodKeadaanTanahDAO;
    }

    public void setKodKeadaanTanahDAO(KodKeadaanTanahDAO kodKeadaanTanahDAO) {
        this.kodKeadaanTanahDAO = kodKeadaanTanahDAO;
    }

    public MohonKeadaanTanahDAO getMohonKeadaanTanahDAO() {
        return mohonKeadaanTanahDAO;
    }

    public void setMohonKeadaanTanahDAO(MohonKeadaanTanahDAO mohonKeadaanTanahDAO) {
        this.mohonKeadaanTanahDAO = mohonKeadaanTanahDAO;
    }

    public MohonLotSepadanDAO getMohonLotSepadanDAO() {
        return mohonLotSepadanDAO;
    }

    public void setMohonLotSepadanDAO(MohonLotSepadanDAO mohonLotSepadanDAO) {
        this.mohonLotSepadanDAO = mohonLotSepadanDAO;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    public DisLaporanTanahService getDisLaporanTanahService() {
        return disLaporanTanahService;
    }

    public void setDisLaporanTanahService(DisLaporanTanahService disLaporanTanahService) {
        this.disLaporanTanahService = disLaporanTanahService;
    }

    public DisSyaratSekatan getDisSyaratSekatan() {
        return disSyaratSekatan;
    }

    public void setDisSyaratSekatan(DisSyaratSekatan disSyaratSekatan) {
        this.disSyaratSekatan = disSyaratSekatan;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public KodSyaratNyataDAO getKodSyaratNyataDAO() {
        return kodSyaratNyataDAO;
    }

    public void setKodSyaratNyataDAO(KodSyaratNyataDAO kodSyaratNyataDAO) {
        this.kodSyaratNyataDAO = kodSyaratNyataDAO;
    }

    public KodSekatanKepentinganDAO getKodSekatanKepentinganDAO() {
        return kodSekatanKepentinganDAO;
    }

    public void setKodSekatanKepentinganDAO(KodSekatanKepentinganDAO kodSekatanKepentinganDAO) {
        this.kodSekatanKepentinganDAO = kodSekatanKepentinganDAO;
    }

    public SyorLaporanTanahDAO getSyorLaporanTanahDAO() {
        return syorLaporanTanahDAO;
    }

    public void setSyorLaporanTanahDAO(SyorLaporanTanahDAO syorLaporanTanahDAO) {
        this.syorLaporanTanahDAO = syorLaporanTanahDAO;
    }

    public String getIdLaporTanah() {
        return idLaporTanah;
    }

    public void setIdLaporTanah(String idLaporTanah) {
        this.idLaporTanah = idLaporTanah;
    }

    public String getNoPtSementara() {
        return noPtSementara;
    }

    public void setNoPtSementara(String noPtSementara) {
        this.noPtSementara = noPtSementara;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getUlasanPerihal() {
        return ulasanPerihal;
    }

    public void setUlasanPerihal(String ulasanPerihal) {
        this.ulasanPerihal = ulasanPerihal;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public List<PermohonanLaporanPelan> getPermohonanLaporanPelanList() {
        return permohonanLaporanPelanList;
    }

    public void setPermohonanLaporanPelanList(List<PermohonanLaporanPelan> permohonanLaporanPelanList) {
        this.permohonanLaporanPelanList = permohonanLaporanPelanList;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(String kodTanah) {
        this.kodTanah = kodTanah;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah9() {
        return listMohonKeadaanTanah9;
    }

    public void setListMohonKeadaanTanah9(List<MohonKeadaanTanah> listMohonKeadaanTanah9) {
        this.listMohonKeadaanTanah9 = listMohonKeadaanTanah9;
    }

    public List<MohonKeadaanTanah> getListMohonKeadaanTanah10() {
        return listMohonKeadaanTanah10;
    }

    public void setListMohonKeadaanTanah10(List<MohonKeadaanTanah> listMohonKeadaanTanah10) {
        this.listMohonKeadaanTanah10 = listMohonKeadaanTanah10;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList9() {
        return kodKeadaanTanahList9;
    }

    public void setKodKeadaanTanahList9(List<KodKeadaanTanah> kodKeadaanTanahList9) {
        this.kodKeadaanTanahList9 = kodKeadaanTanahList9;
    }

    public List<KodKeadaanTanah> getKodKeadaanTanahList10() {
        return kodKeadaanTanahList10;
    }

    public void setKodKeadaanTanahList10(List<KodKeadaanTanah> kodKeadaanTanahList10) {
        this.kodKeadaanTanahList10 = kodKeadaanTanahList10;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String[] getPihakB() {
        return pihakB;
    }

    public void setPihakB(String[] pihakB) {
        this.pihakB = pihakB;
    }

    public String[] getNilaian() {
        return nilaian;
    }

    public void setNilaian(String[] nilaian) {
        this.nilaian = nilaian;
    }

    public List<KodAgensi> getSenaraiJTK() {
        return senaraiJTK;
    }

    public void setSenaraiJTK(List<KodAgensi> senaraiJTK) {
        this.senaraiJTK = senaraiJTK;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public KodAgensi getDetails() {
        return details;
    }

    public void setDetails(KodAgensi details) {
        this.details = details;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public String getKatgAgensi() {
        return katgAgensi;
    }

    public void setKatgAgensi(String katgAgensi) {
        this.katgAgensi = katgAgensi;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getGelaran() {
        return gelaran;
    }

    public void setGelaran(String gelaran) {
        this.gelaran = gelaran;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKodJTK() {
        return kodJTK;
    }

    public void setKodJTK(String kodJTK) {
        this.kodJTK = kodJTK;
    }

    public List<MohonAgensiTanah> getSenaraiJTKSimpan() {
        return senaraiJTKSimpan;
    }

    public void setSenaraiJTKSimpan(List<MohonAgensiTanah> senaraiJTKSimpan) {
        this.senaraiJTKSimpan = senaraiJTKSimpan;
    }

    public MohonAgensiTanah getMohonAgensiTanah() {
        return mohonAgensiTanah;
    }

    public void setMohonAgensiTanah(MohonAgensiTanah mohonAgensiTanah) {
        this.mohonAgensiTanah = mohonAgensiTanah;
    }

    public String getIdJtek() {
        return idJtek;
    }

    public void setIdJtek(String idJtek) {
        this.idJtek = idJtek;
    }

    public KodAgensiDAO getKodAgensiDAO() {
        return kodAgensiDAO;
    }

    public void setKodAgensiDAO(KodAgensiDAO kodAgensiDAO) {
        this.kodAgensiDAO = kodAgensiDAO;
    }

    public MohonAgensiTanahDAO getMohonAgensiTanahDAO() {
        return mohonAgensiTanahDAO;
    }

    public void setMohonAgensiTanahDAO(MohonAgensiTanahDAO mohonAgensiTanahDAO) {
        this.mohonAgensiTanahDAO = mohonAgensiTanahDAO;
    }

    public PenggunaDAO getPenggunaDao() {
        return penggunaDao;
    }

    public void setPenggunaDao(PenggunaDAO penggunaDao) {
        this.penggunaDao = penggunaDao;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdAliran() {
        return idAliran;
    }

    public void setIdAliran(String idAliran) {
        this.idAliran = idAliran;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public IWorkflowContext getCtx() {
        return ctx;
    }

    public void setCtx(IWorkflowContext ctx) {
        this.ctx = ctx;
    }

    public static String[] getPengambilan() {
        return Pengambilan;
    }

    public static void setPengambilan(String[] Pengambilan) {
        laporanTanahV3.Pengambilan = Pengambilan;
    }

}
