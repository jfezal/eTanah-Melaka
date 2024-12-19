/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.PermohonanPihakTidakBerkepentingan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.daftar.PembetulanService;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.util.bean.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/nota_siasatan1")
public class NotaSiasatan1ActionBean extends AbleActionBean {
    
    private static final Logger logger = Logger.getLogger(NotaSiasatan1ActionBean.class);
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    EnforceService enfService;
    private String idPermohonan;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private HakmilikPerbicaraan hakmilikPerbicaraan2;
    private HakmilikPermohonan hakmilikPermohonan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private String idHakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar = new ArrayList<PerbicaraanKehadiran>();
    private List<PerbicaraanKehadiran> senaraiPermohonanPihakTdkBKepentingan = new ArrayList<PerbicaraanKehadiran>();
    private List<Pihak> senaraiTuanTanah = new ArrayList<Pihak>();
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Pengguna> senaraiPengguna;
    private PerbicaraanKehadiran perbicaraanKehadiranPPTB;
    public String noRujukan;
    private Date tarikhPemilikan;
    private String caraPemilikan;
    private BigDecimal hargaPembelian;
    private String lokasi;
    private BigDecimal jarakKeBandar;
    private KodUOM kodUOM;
    private String keadaanTanah;
    private String bangunan;
    private String tanaman;
    private BigDecimal amaunDituntut;
    private String alasanTuntutan;
    private String pemohonUlasan;
    private String penilaiNoRujukan;
    private String penilaiTarikh;
    private String penilaiAmaun;
    private String penilaiUlasan;
    private String catatan;
    private String makluman;
    private Date tarikhMasuk;
    private String diMasuk;
    private List<Penilaian> penilaianList;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private List<BigDecimal> itemNilaianTanahList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianBngnList = new ArrayList<BigDecimal>();
    private List<BigDecimal> itemNilaianLainList = new ArrayList<BigDecimal>();
    private List<Character> hadir = new ArrayList<Character>();
    private List<Character> bantah = new ArrayList<Character>();
    private List<Character> hadir2 = new ArrayList<Character>();
    private List<String> keputusan2 = new ArrayList<String>();
    private List<String> keputusan = new ArrayList<String>();
    private List<String> nama = new ArrayList<String>();
    private List<PermohonanKertasKandungan> senaraiKertas;
    private Penilaian penilaian;
    private PermohonanKertas kertas;
    private PermohonanKertasKandungan maklumanUlasan;
    private char r = 'T';
    private char p = 'T';
    private char pbt = 'T';
    private String tarikhBicara;
    private String jam;
    private String minit;
    private String ampm;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    private PermohonanPihakTidakBerkepentingan pptb;
    private List<PermohonanPihakTidakBerkepentingan> senaraiPPTB;
    private String kodUrusan;
    private List<String> hadirBantahList = new ArrayList<String>();
    private String b;
    
    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Resolution showPerintah() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
            if (hakmilikPermohonan != null) {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraan != null) {
                    senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
                }
            }
        }
        return new JSP("pengambilan/perintah.jsp").addParameter("popup", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonan.getKodUrusan().getKod();
        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            
        }
        
        kertas = enfService.findByIdKrts(idPermohonan, "KPA"); //KPA = keputusan aduan
        logger.info("kertas " + kertas);
        if (kertas != null) {
            logger.info("kertas tidak null  ");
            maklumanUlasan = enfService.findByIdBil(kertas.getIdKertas(), 2);
            if (maklumanUlasan != null) {
                makluman = maklumanUlasan.getKandungan();
            }
            senaraiKertas = enfService.findAllKeputusanAduan(kertas.getIdKertas());
            logger.info("-------------size senarai ulasan :------------------" + senaraiKertas.size());
        } else {
            logger.info("keluarkan maklumat kertas  ");
            makluman = "Pentadbir Tanah memberi award sebagaimana yang tertera di skrin pampasan.";
        }
        
        senaraiPengguna = notisPenerimaanService.getPengguna();
    }
    
    public Resolution showEditTuanTanah() {
        String idpihak = getContext().getRequest().getParameter("idPihak");
        return new JSP("pengambilan/notasiasatan_popup.jsp").addParameter("popup", "true");
        
    }
    
    public Resolution showTuanTanahPopup() {
        return new JSP("pengambilan/notasiasatan_popup_1.jsp").addParameter("popup", "true");
    }
    
    public Resolution hakmilikDetails() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        
        hakmilik = hakmilikDAO.findById(idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        System.out.println("hakmilik pihak berkepentingan" + hpList.size());
        logger.info("id Hakmilik " + idHakmilik);
        logger.info("idPermohonan " + idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        kodUrusan = permohonan.getKodUrusan().getKod();
        logger.info("hakmilikPermohonan " + hakmilikPermohonan.getId());
        logger.info("kodUrusan " + kodUrusan);
        if (hakmilikPermohonan != null) {
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
                kodUrusan = permohonan.getKodUrusan().getKod();
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
                hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
//                logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
                senaraiPengguna = notisPenerimaanService.getPengguna();
                logger.info("==::senaraiPengguna::== " + senaraiPengguna.size());
                logger.info("==::senaraiPenggunaSahaja::== " + senaraiPengguna);
                // logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
                if (hakbicaraList.size() > 1) {
                    hakmilikPerbicaraan = hakbicaraList.get(0);
                } else {
                    //   hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraNotaSiasatan(hakmilikPermohonan.getId());
                    //  logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
                }
                if (hakmilikPerbicaraan == null) {
                    logger.info("create id bicara baru ");
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }
                
                if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                    getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                }
                
                if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                    String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    tarikhBicara = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    ampm = tarikh.substring(20, 22);
                }
                
            } else {
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                logger.info("hakmilikPerbicaraan id " + hakmilikPerbicaraan.getIdPerbicaraan());
                
                hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
//                logger.info("hakbicaraList" + hakbicaraList.get(0).getIdPerbicaraan());
                if (hakmilikPerbicaraan == null) {
                    logger.info("create id bicara baru ");
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(permohonan.getCawangan());
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }
                if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                    getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
                }
                
                if (hakmilikPerbicaraan.getTarikhBicara() != null) {
                    String tarikh = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
                    tarikhBicara = tarikh.substring(0, 10);
                    jam = tarikh.substring(11, 13);
                    minit = tarikh.substring(14, 16);
                    ampm = tarikh.substring(20, 22);
                }
                
                
                
                
                
            }
            
            
            senaraiPPTB = notisPenerimaanService.getSenaraiPPTBByHakmilik(hakmilikPermohonan.getId());
            if (!senaraiPPTB.isEmpty()) {
                for (PermohonanPihakTidakBerkepentingan ptb : senaraiPPTB) {
                    pptb = notisPenerimaanService.getPBT(hakmilikPermohonan.getId(), ptb.getIdPermohonanPhkTdkBerptg());
                    perbicaraanKehadiranPPTB = notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(ptb.getIdPermohonanPhkTdkBerptg(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiranPPTB == null) {
                        perbicaraanKehadiranPPTB = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiranPPTB.setInfoAudit(infoAudit);
                        perbicaraanKehadiranPPTB.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiranPPTB.setPerbicaraan(hakmilikPerbicaraan);
                        perbicaraanKehadiranPPTB.setPermohonanPihakTidakBerkepentingan(pptb);
                        notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiranPPTB);
                    }
                    
                    
                    
                }
                
                
            }
            
            
            for (int i = 0; i < hpList.size(); i++) {
                logger.info("masuk loop senarai kehadiran");
                
                HakmilikPihakBerkepentingan hp = hpList.get(i);
//                PermohonanPihak permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, hp.getPihak().getIdPihak());
                for (PermohonanPihak pp : senaraiPermohonanPihak) {
                    perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiran == null) {
                        perbicaraanKehadiran = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                        perbicaraanKehadiran.setPihak(pp);
                        notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                    }
//                    perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(pp.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
////                perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                    logger.info("ListPerbicaraanKehadiran : " + perbicaraanKehadiranList.size());
//                    for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
//                        if (pk == null) {
//                            logger.info("pk == null");
//                            perbicaraanKehadiran = new PerbicaraanKehadiran();
//                            InfoAudit infoAudit = new InfoAudit();
//                            infoAudit.setTarikhMasuk(new java.util.Date());
//                            infoAudit.setDimasukOleh(peng);
//                            perbicaraanKehadiran.setInfoAudit(infoAudit);
//                            perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
//                            perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
//                            perbicaraanKehadiran.setPihak(pp);
//                            notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
//                        }
//                    }
                }
                
            }
            
            int c = 1;
            int counter = hakbicaraList.size() - c;
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            for (int i = 0; i < senaraiPihakPentingBerdaftar.size(); i++) {
               
                perbicaraanKehadiran = senaraiPihakPentingBerdaftar.get(i);
                logger.debug("nama >> "+senaraiPihakPentingBerdaftar.get(i).getPihak().getPihak().getNama());
                nama.add(senaraiPihakPentingBerdaftar.get(i).getPihak().getNama());
                if (perbicaraanKehadiran.getHadir() == null) {
                    hadir.add('0');
                } else if (perbicaraanKehadiran.getHadir().equals('1')) {
                    hadir.add('1');
                } else if (perbicaraanKehadiran.getHadir().equals('0')) {
                    hadir.add('0');
                }
                if (perbicaraanKehadiran.getBantahElektrik() == null) {
                    keputusan.add("0");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("1")) {
                    keputusan.add("1");
                } else if (perbicaraanKehadiran.getBantahElektrik().equalsIgnoreCase("0")) {
                    keputusan.add("0");
                }
               
            }

//            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
//                if (perbicaraanKehadiran.getHadir() != null && perbicaraanKehadiran.getHadir() != ' ') {
//                    hadir.set(i, perbicaraanKehadiran.getHadir());
////                   hadir2.set(i, perbicaraanKehadiran.getHadir());
//                }
//            }
            /**
             * *************************************************SENARAI TUAN
             * TANAH****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiTuanTanah = notisPenerimaanService.getSenaraiTuanTanah(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("-----senaraiTuanTanah-----" + getSenaraiTuanTanah().size());
            /**
             * *************************************************SENARAI PBT
             * Berdaftar****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("-----senaraiPihakPentingBerdaftar-----" + senaraiPihakPentingBerdaftar.size());
            /**
             * *************************************************SENARAI PBT
             * Tidak
             * Berdaftar****************************************************
             */
            System.out.println("-----ID BICARA-----" + hakmilikPerbicaraan.getIdPerbicaraan());


//start
//            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
//
//            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                hadir.add('0');
//                bantah.add('T');
//            }
//            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
//                if (perbicaraanKehadiran.getHadir() != null && perbicaraanKehadiran.getHadir() != ' ') {
//                    hadir.set(i, perbicaraanKehadiran.getHadir());
//                }
//                if (perbicaraanKehadiran.getBantahElektrik() != null && !perbicaraanKehadiran.getBantahElektrik().isEmpty()) {
//                    bantah.set(i, perbicaraanKehadiran.getBantahElektrik().charAt(0));
//                }

//end

//                   try{
//                 if(senaraiPerbicaraanKehadiran.get(i).getBantahElektrik() == null){
//                     hadirBantahList.add("T");
//                 }
//                 else if(senaraiPerbicaraanKehadiran.get(i).getBantahElektrik()!= null)
//                {
//                     hadirBantahList.add(bantah.toString());
//
//                }
//
//            }catch(Exception e){
//
//            }

//            }
        }
        
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpan() throws ParseException, java.text.ParseException {
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        
        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLA") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PHLLP") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("PTNB")) {
            if (notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(info);
                hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
            } else {
                hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }
        } else {
            if (notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(info);
                hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
                hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
            } else {
                hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikbicaraPHHLA(hakmilikPermohonan.getId());
                InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraanTemp.setInfoAudit(ia);
            }
        }
        hakmilikPerbicaraanTemp.setCatatan(hakmilikPerbicaraan.getCatatan());
        hakmilikPerbicaraanTemp.setNoRujukan(hakmilikPerbicaraan.getNoRujukan());
        hakmilikPerbicaraanTemp.setDibicaraOleh(hakmilikPerbicaraan.getDibicaraOleh());
        hakmilikPerbicaraanTemp.setTarikhPemilikan(hakmilikPerbicaraan.getTarikhPemilikan());
        hakmilikPerbicaraanTemp.setCaraPemilikan(hakmilikPerbicaraan.getCaraPemilikan());
        hakmilikPerbicaraanTemp.setHargaPembelian(hakmilikPerbicaraan.getHargaPembelian());
        hakmilikPerbicaraanTemp.setNilaianPenilai(hakmilikPerbicaraan.getNilaianPenilai());
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(hakmilikPerbicaraan.getPenilaiNoRujukan());
        hakmilikPerbicaraanTemp.setTanaman(hakmilikPerbicaraan.getTanaman());
        hakmilikPerbicaraanTemp.setBangunan(hakmilikPerbicaraan.getBangunan());
        hakmilikPerbicaraanTemp.setPemohonUlasan(hakmilikPerbicaraan.getPemohonUlasan());
        hakmilikPerbicaraanTemp.setAlasanTuntutan(hakmilikPerbicaraan.getAlasanTuntutan());
        hakmilikPerbicaraanTemp.setKeputusanNilai(hakmilikPerbicaraan.getKeputusanNilai());
        hakmilikPerbicaraanTemp.setKodUOM(hakmilikPerbicaraan.getKodUOM());
        hakmilikPerbicaraanTemp.setPenilaiNoRujukan(hakmilikPerbicaraan.getPenilaiNoRujukan());
        hakmilikPerbicaraanTemp.setKeteranganGKL(hakmilikPerbicaraan.getKeteranganGKL());
        hakmilikPerbicaraanTemp.setKeteranganPPP(hakmilikPerbicaraan.getKeteranganPPP());
        hakmilikPerbicaraanTemp.setKeteranganTuanpunya(hakmilikPerbicaraan.getKeteranganTuanpunya());
        hakmilikPerbicaraanTemp.setKeteranganLain(hakmilikPerbicaraan.getKeteranganLain());
        hakmilikPerbicaraanTemp.setPerintahPtd(hakmilikPerbicaraan.getPerintahPtd());
        hakmilikPerbicaraanTemp.setTarikhSuratPenilai(hakmilikPerbicaraan.getTarikhSuratPenilai());
        hakmilikPerbicaraanTemp.setTarikhPecahSyarat(hakmilikPerbicaraan.getTarikhPecahSyarat());
        hakmilikPerbicaraanTemp.setAkujanjiPenilai(hakmilikPerbicaraan.getAkujanjiPenilai());
        hakmilikPerbicaraanTemp.setPenilaiNama(hakmilikPerbicaraan.getPenilaiNama());
        hakmilikPerbicaraanTemp.setUlasanPenilai(hakmilikPerbicaraan.getUlasanPenilai());
        hakmilikPerbicaraanTemp.setLokasiTerkini(hakmilikPerbicaraan.getLokasiTerkini());
        hakmilikPerbicaraanTemp.setPecahSyarat(hakmilikPerbicaraan.getPecahSyarat());
        hakmilikPerbicaraanTemp.setUjudGPPJ(hakmilikPerbicaraan.getUjudGPPJ());
        hakmilikPerbicaraanTemp.setKomenGPPJ(hakmilikPerbicaraan.getKomenGPPJ());
        hakmilikPerbicaraanTemp.setBatalRizab(r);
        hakmilikPerbicaraanTemp.setKawasanPBT(pbt);
        hakmilikPerbicaraanTemp.setPelanPembangunan(p);
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);

//        if (hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
//            senaraiPerbicaraanKehadiran = hakmilikPerbicaraan.getSenaraiKehadiran();
//            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
//                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
//                perbicaraanKehadiran.setHadir(hadir.get(i));
//                perbicaraanKehadiran.setBantahElektrik(Character.toString(bantah.get(i)));
//                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
//            }
//            if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
//                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
//            }
//        }

        if (hakmilikPerbicaraan != null && hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            
            System.out.println("--------hakmilikPerbicaraan.getIdPerbicaraan()------------" + hakmilikPerbicaraan.getIdPerbicaraan());
            hakmilikPerbicaraan = hakmilikPerbicaraanDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
            senaraiPihakPentingBerdaftar = notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan());
            System.out.println("--------senaraiPihakPentingBerdaftar------------" + notisPenerimaanService.getSenaraiPBTDaftar(hakmilikPerbicaraan.getIdPerbicaraan()));
            if (senaraiPihakPentingBerdaftar.size() > 0) {
                perbicaraanKehadiran = perbicaraanKehadiranDAO.findById(hakmilikPerbicaraan.getIdPerbicaraan());
                for (int i = 0; i < senaraiPihakPentingBerdaftar.size(); i++) {
                    perbicaraanKehadiran = senaraiPihakPentingBerdaftar.get(i);
                    System.out.println("--------perbicaraanKehadiran------------" + perbicaraanKehadiran);
                    System.out.println("--------hadir------------" + hadir.get(i));
                    System.out.println("--------Keputusan------------" + keputusan.get(i));
                    perbicaraanKehadiran.setHadir(hadir.get(i));
                    perbicaraanKehadiran.setBantahElektrik(keputusan.get(i));
                    perbicaraanKehadiran.setNama(senaraiPihakPentingBerdaftar.get(i).getPihak().getPihak().getNama());
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            } else {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                perbicaraanKehadiran.setInfoAudit(info);
                perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pptb);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
        }
        
        
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanKehadiran() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hadir = new ArrayList<Character>();
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        
        hakmilik = hakmilikDAO.findById(idHakmilik);
        logger.info("simpan kehadiran");
        logger.info("idHakmilik" + idHakmilik);
        List<HakmilikPihakBerkepentingan> hpList = hakmilik.getSenaraiPihakBerkepentingan();
        
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        
        for (HakmilikPermohonan hpp : hakmilikPermohonanList) {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikbicara2(hpp.getId());
            if (hakmilikPerbicaraan != null) {
                logger.info("loop 2");
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara(hakmilikPerbicaraan.getIdPerbicaraan());
                if (senaraiPerbicaraanKehadiran != null) {
                    logger.info("masuk sini create org baru");
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setNama(getContext().getRequest().getParameter("perbicaraanKehadiran.nama"));
                    perbicaraanKehadiran.setNoPengenalan(getContext().getRequest().getParameter("perbicaraanKehadiran.noPengenalan"));
                    perbicaraanKehadiran.setJawatan(getContext().getRequest().getParameter("perbicaraanKehadiran.jawatan"));
                    perbicaraanKehadiran.setPenilaiUlasan(getContext().getRequest().getParameter("perbicaraanKehadiran.penilaiUlasan"));
                    notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
            }
            
            
        }
        rehydrate();
        hakmilikDetails();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Resolution simpanPerintah() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        
        HakmilikPerbicaraan hakmilikPerbicaraanTemp;
        if (notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
            hakmilikPerbicaraanTemp = new HakmilikPerbicaraan();
            InfoAudit info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(info);
            hakmilikPerbicaraanTemp.setCawangan(permohonan.getCawangan());
            hakmilikPerbicaraanTemp.setHakmilikPermohonan(hakmilikPermohonan);
        } else {
            hakmilikPerbicaraanTemp = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            InfoAudit ia = hakmilikPerbicaraanTemp.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPerbicaraanTemp.setInfoAudit(ia);
        }
        hakmilikPerbicaraanTemp.setJenisPembangunan(hakmilikPerbicaraan.getJenisPembangunan());
        hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraanTemp);
        
        if (hakmilikPerbicaraan.getIdPerbicaraan() > 0) {
            if (hakmilikPerbicaraan.getKeputusanNilai() != null) {
                getContext().getRequest().setAttribute("showPerintah", Boolean.TRUE);
            }
        }
        
        
        
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        return new JSP("pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Resolution savePerbicaraanKehadiran() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        perbicaraanKehadiran = new PerbicaraanKehadiran();
        perbicaraanKehadiran.setCawangan(permohonan.getCawangan());
        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/nota_siasatan1.jsp").addParameter("tab", "true");
    }
    
    public Hakmilik getHakmilik() {
        return hakmilik;
    }
    
    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
    
    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }
    
    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }
    
    public String getIdHakmilik() {
        return idHakmilik;
    }
    
    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }
    
    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }
    
    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }
    
    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    public String getAlasanTuntutan() {
        return alasanTuntutan;
    }
    
    public void setAlasanTuntutan(String alasanTuntutan) {
        this.alasanTuntutan = alasanTuntutan;
    }
    
    public String getCaraPemilikan() {
        return caraPemilikan;
    }
    
    public void setCaraPemilikan(String caraPemilikan) {
        this.caraPemilikan = caraPemilikan;
    }
    
    public String getCatatan() {
        return catatan;
    }
    
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getKeadaanTanah() {
        return keadaanTanah;
    }
    
    public void setKeadaanTanah(String keadaanTanah) {
        this.keadaanTanah = keadaanTanah;
    }
    
    public KodUOM getKodUOM() {
        return kodUOM;
    }
    
    public void setKodUOM(KodUOM kodUOM) {
        this.kodUOM = kodUOM;
    }
    
    public String getLokasi() {
        return lokasi;
    }
    
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
    
    public String getPemohonUlasan() {
        return pemohonUlasan;
    }
    
    public void setPemohonUlasan(String pemohonUlasan) {
        this.pemohonUlasan = pemohonUlasan;
    }
    
    public String getPenilaiAmaun() {
        return penilaiAmaun;
    }
    
    public void setPenilaiAmaun(String penilaiAmaun) {
        this.penilaiAmaun = penilaiAmaun;
    }
    
    public String getPenilaiNoRujukan() {
        return penilaiNoRujukan;
    }
    
    public void setPenilaiNoRujukan(String penilaiNoRujukan) {
        this.penilaiNoRujukan = penilaiNoRujukan;
    }
    
    public String getPenilaiTarikh() {
        return penilaiTarikh;
    }
    
    public void setPenilaiTarikh(String penilaiTarikh) {
        this.penilaiTarikh = penilaiTarikh;
    }
    
    public String getPenilaiUlasan() {
        return penilaiUlasan;
    }
    
    public void setPenilaiUlasan(String penilaiUlasan) {
        this.penilaiUlasan = penilaiUlasan;
    }
    
    public String getTanaman() {
        return tanaman;
    }
    
    public void setTanaman(String tanaman) {
        this.tanaman = tanaman;
    }
    
    public BigDecimal getAmaunDituntut() {
        return amaunDituntut;
    }
    
    public void setAmaunDituntut(BigDecimal amaunDituntut) {
        this.amaunDituntut = amaunDituntut;
    }
    
    public String getBangunan() {
        return bangunan;
    }
    
    public void setBangunan(String bangunan) {
        this.bangunan = bangunan;
    }
    
    public BigDecimal getHargaPembelian() {
        return hargaPembelian;
    }
    
    public void setHargaPembelian(BigDecimal hargaPembelian) {
        this.hargaPembelian = hargaPembelian;
    }
    
    public BigDecimal getJarakKeBandar() {
        return jarakKeBandar;
    }
    
    public void setJarakKeBandar(BigDecimal jarakKeBandar) {
        this.jarakKeBandar = jarakKeBandar;
    }
    
    public String getNoRujukan() {
        return noRujukan;
    }
    
    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }
    
    public Date getTarikhPemilikan() {
        return tarikhPemilikan;
    }
    
    public void setTarikhPemilikan(Date tarikhPemilikan) {
        this.tarikhPemilikan = tarikhPemilikan;
    }
    
    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }
    
    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }
    
    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }
    
    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }
    
    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }
    
    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }
    
    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }
    
    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }
    
    public String getDiMasuk() {
        return diMasuk;
    }
    
    public void setDiMasuk(String diMasuk) {
        this.diMasuk = diMasuk;
    }
    
    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }
    
    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }
    
    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }
    
    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }
    
    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }
    
    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }
    
    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }
    
    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }
    
    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }
    
    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }
    
    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }
    
    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }
    
    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }
    
    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }
    
    public List<BigDecimal> getItemNilaianBngnList() {
        return itemNilaianBngnList;
    }
    
    public void setItemNilaianBngnList(List<BigDecimal> itemNilaianBngnList) {
        this.itemNilaianBngnList = itemNilaianBngnList;
    }
    
    public List<BigDecimal> getItemNilaianLainList() {
        return itemNilaianLainList;
    }
    
    public void setItemNilaianLainList(List<BigDecimal> itemNilaianLainList) {
        this.itemNilaianLainList = itemNilaianLainList;
    }
    
    public List<BigDecimal> getItemNilaianTanahList() {
        return itemNilaianTanahList;
    }
    
    public void setItemNilaianTanahList(List<BigDecimal> itemNilaianTanahList) {
        this.itemNilaianTanahList = itemNilaianTanahList;
    }
    
    public List<Character> getHadir() {
        return hadir;
    }
    
    public void setHadir(List<Character> hadir) {
        this.hadir = hadir;
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
    
    public String getTarikhBicara() {
        return tarikhBicara;
    }
    
    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }
    
    public HakmilikPerbicaraan getHakmilikPerbicaraan2() {
        return hakmilikPerbicaraan2;
    }
    
    public void setHakmilikPerbicaraan2(HakmilikPerbicaraan hakmilikPerbicaraan2) {
        this.hakmilikPerbicaraan2 = hakmilikPerbicaraan2;
    }
    
    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }
    
    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }
    
    public PermohonanPihakTidakBerkepentingan getPptb() {
        return pptb;
    }
    
    public void setPptb(PermohonanPihakTidakBerkepentingan pptb) {
        this.pptb = pptb;
    }
    
    public Penilaian getPenilaian() {
        return penilaian;
    }
    
    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }
    
    public PerbicaraanKehadiran getPerbicaraanKehadiranPPTB() {
        return perbicaraanKehadiranPPTB;
    }
    
    public void setPerbicaraanKehadiranPPTB(PerbicaraanKehadiran perbicaraanKehadiranPPTB) {
        this.perbicaraanKehadiranPPTB = perbicaraanKehadiranPPTB;
    }
    
    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTB() {
        return senaraiPPTB;
    }
    
    public void setSenaraiPPTB(List<PermohonanPihakTidakBerkepentingan> senaraiPPTB) {
        this.senaraiPPTB = senaraiPPTB;
    }
    
    public String getKodUrusan() {
        return kodUrusan;
    }
    
    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }
    
    public List<Character> getBantah() {
        return bantah;
    }
    
    public void setBantah(List<Character> bantah) {
        this.bantah = bantah;
    }
    
    public String getB() {
        return b;
    }
    
    public void setB(String b) {
        this.b = b;
    }
    
    public PermohonanKertas getKertas() {
        return kertas;
    }
    
    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }
    
    public String getMakluman() {
        return makluman;
    }
    
    public void setMakluman(String makluman) {
        this.makluman = makluman;
    }
    
    public PermohonanKertasKandungan getMaklumanUlasan() {
        return maklumanUlasan;
    }
    
    public void setMaklumanUlasan(PermohonanKertasKandungan maklumanUlasan) {
        this.maklumanUlasan = maklumanUlasan;
    }
    
    public List<PermohonanKertasKandungan> getSenaraiKertas() {
        return senaraiKertas;
    }
    
    public void setSenaraiKertas(List<PermohonanKertasKandungan> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }
    
    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }
    
    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }
    
    public List<PerbicaraanKehadiran> getSenaraiPihakPentingBerdaftar() {
        return senaraiPihakPentingBerdaftar;
    }
    
    public void setSenaraiPihakPentingBerdaftar(List<PerbicaraanKehadiran> senaraiPihakPentingBerdaftar) {
        this.senaraiPihakPentingBerdaftar = senaraiPihakPentingBerdaftar;
    }
    
    public List<String> getKeputusan() {
        return keputusan;
    }
    
    public void setKeputusan(List<String> keputusan) {
        this.keputusan = keputusan;
    }
    
    public List<String> getNama() {
        return nama;
    }
    
    public void setNama(List<String> nama) {
        this.nama = nama;
    }
    
    public List<PerbicaraanKehadiran> getSenaraiPermohonanPihakTdkBKepentingan() {
        return senaraiPermohonanPihakTdkBKepentingan;
    }
    
    public void setSenaraiPermohonanPihakTdkBKepentingan(List<PerbicaraanKehadiran> senaraiPermohonanPihakTdkBKepentingan) {
        this.senaraiPermohonanPihakTdkBKepentingan = senaraiPermohonanPihakTdkBKepentingan;
    }
    
    public List<Character> getHadir2() {
        return hadir2;
    }
    
    public void setHadir2(List<Character> hadir2) {
        this.hadir2 = hadir2;
    }
    
    public List<String> getKeputusan2() {
        return keputusan2;
    }
    
    public void setKeputusan2(List<String> keputusan2) {
        this.keputusan2 = keputusan2;
    }
    
    public List<Pihak> getSenaraiTuanTanah() {
        return senaraiTuanTanah;
    }
    
    public void setSenaraiTuanTanah(List<Pihak> senaraiTuanTanah) {
        this.senaraiTuanTanah = senaraiTuanTanah;
    }
    
    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }
    
    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }
    
    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }
    
    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }
}
