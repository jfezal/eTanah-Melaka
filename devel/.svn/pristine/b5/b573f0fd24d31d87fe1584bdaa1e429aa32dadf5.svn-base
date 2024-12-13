/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.CalcTax;
import etanah.service.CalcTaxPelupusan;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;

/**
 *
 * @author Rohans
 */
@UrlBinding("/pelupusan/jadual_kira_premium_Entry_pbmt")
public class JadualPBMTActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(JadualPBMTActionBean.class);
    private static final Logger logger = Logger.getLogger(JadualPBMTActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodTransaksiDAO kodTransaksiDAO;
    @Inject
    RegService regService;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    CalcTaxPelupusan calcTax;
    @Inject
    HakmilikDAO hakmilikDAO;
    private String idPermohonan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodBandarPekanMukim bandarPekanMukimBaru;
    private KodHakmilik kodHmlk;
    private Hakmilik hakmilik;
    private NoPt noPt;
    private String tempohHakmilik;
    private BigDecimal cukaiPerMeterPersegi;
    private KodKategoriTanah kategoriTanahBaru;
    private String kod;
    private String kodSktn;
    private String kodKategori;
    private String catatan;
    private String index;
    private String kodSekatanKepentingan;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String sekatKpntgn2;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String kodSyaratNyata;
    private String syaratNyata2;
    private BigDecimal premium;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private String luasDilulus;
    private KodUOM kodUL;
    private KodKadarPremium kodKadarPremium;
    private BigDecimal peratus;
    private BigDecimal harga;
    private String tempohPajakan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String kodTanah;
    private String kodKegunaanTanah;
    private String syaratNyata;
    private String syaratNyata1;
    private BigDecimal pasaran;
    private String kadarPremium;
    private PermohonanTuntutanKos mohonTuntutKos;
    private Pengguna pengguna;
    private String kodBpm = new String();
    private String kodBPMNew;
    private String kodUOM;
    private KodBandarPekanMukim kodBPM;
    private FasaPermohonan fasaPermohonan;
    private String idhakmilik;

    @DefaultHandler
    public Resolution showForm() {

        if (permohonanLaporanPelan == null || hakmilikPermohonan == null || kodKadarPremium == null || permohonanRujukanLuar == null || permohonanLaporanPelan.getKodTanah() == null || hakmilikPermohonan.getKodKegunaanTanah() == null || kodKadarPremium.getPeratusKadar() == null || permohonanRujukanLuar.getNilai() == null || hakmilikPermohonan.getTempohHakmilik() == null) {
            LOG.info("------showForm------inside validate----permohonanLaporanPelan-----------" + permohonanLaporanPelan);
            LOG.info("------showForm------inside validate----hakmilikPermohonan-----------" + hakmilikPermohonan);
            LOG.info("------showForm------inside validate----kodKadarPremium-----------" + kodKadarPremium);
            LOG.info("------showForm------inside validate----permohonanRujukanLuar-----------" + permohonanRujukanLuar);
            if (permohonanLaporanPelan != null) {
                LOG.info("------showForm------inside validate----permohonanLaporanPelan.getKodTanah()-----------" + permohonanLaporanPelan.getKodTanah());
            }
            if (hakmilikPermohonan != null) {
                LOG.info("------showForm------inside validate----hakmilikPermohonan.getKodKegunaanTanah()-----------" + hakmilikPermohonan.getKodKegunaanTanah());
                LOG.info("------showForm------inside validate----hakmilikPermohonan.getTempohhakmilik()-----------" + hakmilikPermohonan.getTempohHakmilik()); 
            }
            if (kodKadarPremium != null) {
                LOG.info("------showForm------inside validate----kodKadarPremium.getPeratusKadar()-----------" + kodKadarPremium.getPeratusKadar());
            }
            if (permohonanRujukanLuar != null) {
                LOG.info("------showForm------inside validate----permohonanRujukanLuar.getNilai()----------" + permohonanRujukanLuar.getNilai());
            }

//         addSimpleError("Maklumat Kadar Premium Tidak Di Jumpai");
        }
        return new JSP("pelupusan/jadual_kiraPremium_pbmt.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {


        return new JSP("pelupusan/jadual_kiraPremium_pbmt_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

//      premium = BigDecimal.ZERO;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("idPermohonan1 : " + idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        harga = BigDecimal.ZERO;
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);


        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        premium = hakmilikPermohonan.getKadarPremium();

        if(premium ==null){
            premium = new BigDecimal(0.0);
        }
        
        LOG.info("kadarPremium: " + kadarPremium);

        if (permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) {
            kodTanah = permohonanLaporanPelan.getKodTanah().getKod();
        }
//else{
//     addSimpleError("KodTanah is null");
//}
        if (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null) {
            kodKegunaanTanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
        }
        
        //Added by Aizuddin to calculate nilai premium
        if ((permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) && (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            LOG.info("------------kodTanah-------------------" + kodTanah + "----- jenis------" +permohonanLaporanPelan.getKodTanah().getNama());
            if (kodTanah.equals("01") || kodTanah.equals("02") || kodTanah.equals("03") || kodTanah.equals("08")) {
                kodKadarPremium = pelupusanService.findKodKadarPremiumId(kodTanah, kodKegunaanTanah);
            }
        }
        LOG.info("------------kodKadarPremium-------findKodKadarPremiumId------------" + kodKadarPremium);
        LOG.info("------------hakmilikPermohonan-------findKodKadarPremiumId2------------" + hakmilikPermohonan);
        LOG.info("------------permohonanRujukanLuar-------findByIdMohon2------------" + permohonanRujukanLuar);

        if (kodKadarPremium != null && kodKadarPremium.getPeratusKadar() != null) {
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getIdKodKadarPremium());
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getNama());
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getPeratusKadar());
            peratus = kodKadarPremium.getPeratusKadar();
        }

        //Tanah yang ada tempoh hakmilik
        if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohHakmilik() != null) {
            tempohPajakan = hakmilikPermohonan.getTempohPajakan().toString(); //Temp solution
            pasaran = new BigDecimal(tempohPajakan);
            LOG.info("------------hakmilikPermohonan-------------------" + hakmilikPermohonan.getTempohHakmilik() + "------tempohPajakan--------" + tempohPajakan + "----" + pasaran);
            LOG.info("------------pasaran------------------" + pasaran);
            
        //Tanah yang ada tempoh pajakan
        } else if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohPajakan() != null) {
            tempohPajakan = hakmilikPermohonan.getTempohPajakan().toString();
            pasaran = new BigDecimal(tempohPajakan);
            LOG.info("------------hakmilikPermohonan-------------------" + hakmilikPermohonan.getTempohHakmilik() + "------tempohPajakan--------" + tempohPajakan + "----" + pasaran);
            LOG.info("------------pasaran------------------" + pasaran);
        }
        if (permohonanRujukanLuar != null && permohonanRujukanLuar.getNilai() != null) {
            harga = permohonanRujukanLuar.getNilai();
            LOG.info("------------hakmilikPermohonan-------------------" + permohonanRujukanLuar.getNilai() + "----harga-------" + harga);
        }

        if (permohonanLaporanPelan != null && hakmilikPermohonan != null && kodKadarPremium != null && permohonanRujukanLuar != null && permohonanLaporanPelan.getKodTanah() != null && hakmilikPermohonan.getKodKegunaanTanah() != null && kodKadarPremium.getPeratusKadar() != null && permohonanRujukanLuar.getNilai() != null ) {
            if (kodTanah.equals("01") || kodTanah.equals("02") || kodTanah.equals("03")) {
                if (permohonanLaporanPelan.getKodTanah() != null && hakmilikPermohonan.getKodKegunaanTanah() != null && kodKadarPremium.getPeratusKadar() != null && hakmilikPermohonan.getKadarPremium() == null) {
//                    premium = new BigDecimal(0.0) ;
                    if(pasaran != null){
                      premium = (peratus.multiply(harga)).multiply(pasaran);  
                    }else{
                      premium = peratus.multiply(harga);  
                    }
                    
                    LOG.info("------------peratus-------------" + peratus);
                    LOG.info("---------harga--------------------" + harga);
                    LOG.info("---------pasaran--------------------" + pasaran);
                    LOG.info("------------premium--------------------" + premium);
                } else {
                    premium = hakmilikPermohonan.getKadarPremium();
                }
            } else if (kodTanah.equals("08")) {
                //To-Do utk tanah ada nilai pampasan
                premium = new BigDecimal(0.0); //Temp solution
            } else {
                addSimpleError("Tanah bukan tanah Bandar, Pekan, Desa atau Hasil Pengambilan");
                premium = new BigDecimal(0.0);
            }
        } 


        if (hakmilikPermohonan != null) {
            noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());

            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                String kod1 = (hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
                logger.info("----kod1---------" + kod1);
                syaratNyata = "" + kod + " -- " + kod1 + "";
                logger.info("----syaratNyata---------" + syaratNyata);
            } else {
                kod = "";
            }

            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
                logger.info("----kodSktn1---------" + kodSktn);
                String kod2 = (hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
                syaratNyata1 = "" + kodSktn + " -- " + kod2 + "";
                logger.info("----syaratNyata1---------" + syaratNyata1);
            } else {
                kodSktn = "";
            }
            if (hakmilikPermohonan.getKategoriTanahBaru() != null) {
                kodKategori = hakmilikPermohonan.getKategoriTanahBaru().getNama();
            }

        }

        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRM",hakmilikPermohonan.getId());
        InfoAudit info = new InfoAudit();

        if (mohonTuntutKos == null) {
            logger.info("mohonTuntutKos is null");
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Premium Tanah");
            mohonTuntutKos.setAmaunTuntutan(premium);
//            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0));
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            logger.info("mohonTuntutKos is not null");
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Premium Tanah");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }


        //Bayaran Hasil Tahun Pertama

//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISTAX",hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISTAX");

//        kodBPM="28";
        logger.info("kodBPM : " + hakmilikPermohonan.getBandarPekanMukimBaru().getKod());
        kodUOM = hakmilikPermohonan.getKodUnitLuas().getKod();
        kodBpm = String.valueOf(hakmilikPermohonan.getBandarPekanMukimBaru().getKod());

        logger.info("kodBpm : " + kodBpm);


        BigDecimal cukai;
        if (hakmilikPermohonan.getHakmilik() != null) {
            hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
            cukai = calcTax.calculate(kodKegunaanTanah, kodBpm, kodUOM, hakmilikPermohonan.getLuasTerlibat(), hakmilikPermohonan, null);
        } else {
            cukai = new BigDecimal(0);
        }

        logger.info("----cukai---------" + cukai);
        if (mohonTuntutKos == null) {
            logger.info("mohon tuntut kos for tax is null");
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Hasil Tahun Pertama");
            Double convert = 0.00;
//            convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()) / 2 ;
//            BigDecimal cukai = BigDecimal.valueOf(convert) ;
            mohonTuntutKos.setAmaunTuntutan(cukai); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            logger.info("mohon tuntut kos for tax is not null");
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Hasil Tahun Pertama");
            Double convert = 0.00;
//            convert = Double.parseDouble(hakmilik.getCukaiSebenar().toString()) / 2 ;
//            BigDecimal cukai = BigDecimal.valueOf(convert) ;
            mohonTuntutKos.setAmaunTuntutan(cukai); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }

        //Bayaran Pendaftaran Hakmilik Kekal or Hakmilik Sementara
//        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "kemasukan");
//        if (hakmilik != null) {
//            if (fasaPermohonan.getKeputusan().getKod().equals("YT")) { //Kekal
//                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISFT", hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISFT");
        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
//            } else if (fasaPermohonan.getKeputusan().getKod().equals("YQ")) { //Sementara
//                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISQT", hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISQT");
        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Sementara");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISQT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Sementara");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISQT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISQT").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
//            }
//        }

        //Bayaran Pelan Geran

//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPEL", hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPEL");

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pelan Geran");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPEL"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPEL").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pelan Geran");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPEL"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPEL").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }

        //Bayaran Tanda Sempadan
//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISSEM", hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISSEM");
        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Tanda Sempadan");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISSEM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISSEM").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Tanda Sempadan");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISSEM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISSEM").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }

        //Bayaran Bayaran Ukur
//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISUKUR", hakmilikPermohonan.getId());
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPU");
        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Ukur");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPU")); //DISUKUR
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPU").getKodKewTrans()); //DISUKUR
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Ukur");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(0)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPU"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPU").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }



    }

    public Resolution simpan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("idPermohonan Simpan : " + idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
        if (premium == null) {
            premium = new BigDecimal(0.00);
        }

        if (hakmilikPermohonan != null) {
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);

        } else {
            hakmilikPermohonan = new HakmilikPermohonan();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            hakmilikPermohonan.setInfoAudit(ia);
            hakmilikPermohonan.setPermohonan(permohonan);

        }
        LOG.info("------PREMIUM------" + premium);
        hakmilikPermohonan.setKadarPremium(premium);
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

        KodTransaksi kt = new KodTransaksi();

        LOG.info("-----------------kodTransaksiDAO------kt--------------" + kt);


        if (hakmilikPermohonan != null) {

            ia.setDimasukOleh(hakmilikPermohonan.getInfoAudit().getDimasukOleh());
            hakmilikPermohonan.setInfoAudit(ia);
        }
        if (kod != null) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
        }
        if (kodSktn != null) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
        }

        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/jadual_kiraPremium_pbmt.jsp").addParameter("tab", "true");
    }
    
    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodBandarPekanMukim getBandarPekanMukimBaru() {
        return bandarPekanMukimBaru;
    }

    public void setBandarPekanMukimBaru(KodBandarPekanMukim bandarPekanMukimBaru) {
        this.bandarPekanMukimBaru = bandarPekanMukimBaru;
    }

    public KodHakmilik getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(KodHakmilik kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public BigDecimal getCukaiPerMeterPersegi() {
        return cukaiPerMeterPersegi;
    }

    public void setCukaiPerMeterPersegi(BigDecimal cukaiPerMeterPersegi) {
        this.cukaiPerMeterPersegi = cukaiPerMeterPersegi;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodKategori() {
        return kodKategori;
    }

    public void setKodKategori(String kodKategori) {
        this.kodKategori = kodKategori;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public String getTempohHakmilik() {
        return tempohHakmilik;
    }

    public void setTempohHakmilik(String tempohHakmilik) {
        this.tempohHakmilik = tempohHakmilik;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public KodKategoriTanah getKategoriTanahBaru() {
        return kategoriTanahBaru;
    }

    public void setKategoriTanahBaru(KodKategoriTanah kategoriTanahBaru) {
        this.kategoriTanahBaru = kategoriTanahBaru;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getLuasDilulus() {
        return luasDilulus;
    }

    public void setLuasDilulus(String luasDilulus) {
        this.luasDilulus = luasDilulus;
    }

    public KodKadarPremium getKodKadarPremium() {
        return kodKadarPremium;
    }

    public void setKodKadarPremium(KodKadarPremium kodKadarPremium) {
        this.kodKadarPremium = kodKadarPremium;
    }

    public String getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(String kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(String kodTanah) {
        this.kodTanah = kodTanah;
    }

    public String getSyaratNyata1() {
        return syaratNyata1;
    }

    public void setSyaratNyata1(String syaratNyata1) {
        this.syaratNyata1 = syaratNyata1;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(String kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getKodUOM() {
        return kodUOM;
    }

    public void setKodUOM(String kodUOM) {
        this.kodUOM = kodUOM;
    }

    public String getKodBPMNew() {
        return kodBPMNew;
    }

    public void setKodBPMNew(String kodBPMNew) {
        this.kodBPMNew = kodBPMNew;
    }

    public String getKodBpm() {
        return kodBpm;
    }

    public void setKodBpm(String kodBpm) {
        this.kodBpm = kodBpm;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getIdhakmilik() {
        return idhakmilik;
    }

    public void setIdhakmilik(String idhakmilik) {
        this.idhakmilik = idhakmilik;
    }
}
