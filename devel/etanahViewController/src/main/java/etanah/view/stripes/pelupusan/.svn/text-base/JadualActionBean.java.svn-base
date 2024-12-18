/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.KodKadarPremium;
import etanah.model.KodKategoriTanah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTransaksi;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
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
@UrlBinding("/pelupusan/jadual_kira_premium_Entry")
public class JadualActionBean extends AbleActionBean {
private static final Logger LOG = Logger.getLogger(JadualActionBean.class);
    private static final Logger logger = Logger.getLogger(JadualActionBean.class);
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
    private String idPermohonan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodBandarPekanMukim bandarPekanMukimBaru;
    private KodHakmilik kodHmlk;
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
    private BigDecimal peratuas;
    private BigDecimal harga;
    private String tempohPajakan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String kodTanah;
    private String kodKegunaanTanah;
    private String syaratNyata;
    private String syaratNyata1;
    private BigDecimal pasaran;
    private String kadarPremium;
    

     @DefaultHandler
     public Resolution showForm() {

     if(permohonanLaporanPelan==null || hakmilikPermohonan==null ||kodKadarPremium==null||permohonanRujukanLuar==null || permohonanLaporanPelan.getKodTanah()==null|| hakmilikPermohonan.getKodKegunaanTanah()==null|| kodKadarPremium.getPeratusKadar()==null||permohonanRujukanLuar.getNilai()==null||hakmilikPermohonan.getTempohHakmilik()==null){
       LOG.info("------showForm------inside validate----permohonanLaporanPelan-----------"+permohonanLaporanPelan);
       LOG.info("------showForm------inside validate----hakmilikPermohonan-----------"+hakmilikPermohonan);
       LOG.info("------showForm------inside validate----kodKadarPremium-----------"+kodKadarPremium);
       LOG.info("------showForm------inside validate----permohonanRujukanLuar-----------"+permohonanRujukanLuar);
       if(permohonanLaporanPelan!=null)
       LOG.info("------showForm------inside validate----permohonanLaporanPelan.getKodTanah()-----------"+permohonanLaporanPelan.getKodTanah());
       if(hakmilikPermohonan!=null){
       LOG.info("------showForm------inside validate----hakmilikPermohonan.getKodKegunaanTanah()-----------"+hakmilikPermohonan.getKodKegunaanTanah());
         LOG.info("------showForm------inside validate----hakmilikPermohonan.getTempohhakmilik()-----------"+hakmilikPermohonan.getTempohHakmilik());
       }
       if(kodKadarPremium!=null)
       LOG.info("------showForm------inside validate----kodKadarPremium.getPeratusKadar()-----------"+kodKadarPremium.getPeratusKadar());
       if(permohonanRujukanLuar!=null)
       LOG.info("------showForm------inside validate----permohonanRujukanLuar.getNilai()----------"+permohonanRujukanLuar.getNilai());
     
//         addSimpleError("Maklumat Kadar Premium Tidak Di Jumpai");
     }
        return new JSP("pelupusan/jadual_kiraPremium.jsp").addParameter("tab", "true");
    }


    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       
//      premium = BigDecimal.ZERO;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("idPermohonan1 : " + idPermohonan);
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        harga =BigDecimal.ZERO;
        

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
        premium=hakmilikPermohonan.getKadarPremium();
        
        LOG.info("kadarPremium: "+kadarPremium);
        
        if (permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) {
            kodTanah = permohonanLaporanPelan.getKodTanah().getKod();
        }
//else{
//     addSimpleError("KodTanah is null");
//}
        if (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null) {
            kodKegunaanTanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
        }

//else {
//    addSimpleError("KodKegunaanTanah is null");
//}
        if ((permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) && (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null)) {
            kodKadarPremium = pelupusanService.findKodKadarPremiumId(kodTanah, kodKegunaanTanah);
        }
        LOG.info("------------kodKadarPremium-------findKodKadarPremiumId------------" + kodKadarPremium);
        LOG.info("------------hakmilikPermohonan-------findKodKadarPremiumId2------------" + hakmilikPermohonan);
        LOG.info("------------permohonanRujukanLuar-------findByIdMohon2------------" + permohonanRujukanLuar);

        if (kodKadarPremium != null && kodKadarPremium.getPeratusKadar() != null) {
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getIdKodKadarPremium());
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getNama());
            LOG.info("------------kodKadarPremium-------------------" + kodKadarPremium.getPeratusKadar());
            peratuas = kodKadarPremium.getPeratusKadar();
        }

        if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohHakmilik() != null) {
            tempohPajakan = hakmilikPermohonan.getTempohHakmilik();
            pasaran = new BigDecimal(tempohPajakan);
            LOG.info("------------hakmilikPermohonan-------------------" + hakmilikPermohonan.getTempohHakmilik() + "------tempohPajakan--------" + tempohPajakan + "----" + pasaran);
            LOG.info("------------pasaran-----1--------------" + pasaran);
        }
        if (permohonanRujukanLuar != null && permohonanRujukanLuar.getNilai() != null) {
            harga = permohonanRujukanLuar.getNilai();

            LOG.info("------------hakmilikPermohonan-------------------" + permohonanRujukanLuar.getNilai() + "----harga-------" + harga);
        }

// if(permohonanLaporanPelan!=null && permohonanLaporanPelan.getKodTanah()!=null){
        if (permohonanLaporanPelan != null && hakmilikPermohonan != null && kodKadarPremium != null && permohonanRujukanLuar != null && permohonanLaporanPelan.getKodTanah() != null && hakmilikPermohonan.getKodKegunaanTanah() != null && kodKadarPremium.getPeratusKadar() != null && permohonanRujukanLuar.getNilai() != null && hakmilikPermohonan.getTempohHakmilik() != null) {
            if (kodTanah.equals("01") || kodTanah.equals("02") || kodTanah.equals("03")) {
                LOG.info("------------kodTanah-------------------" + kodTanah);
                if (permohonanLaporanPelan.getKodTanah() != null && hakmilikPermohonan.getKodKegunaanTanah() != null && kodKadarPremium.getPeratusKadar() != null && hakmilikPermohonan.getKadarPremium() == null) {
                    premium = (peratuas.multiply(harga)).multiply(pasaran);
                    LOG.info("------------peratuas-------------" + peratuas);
                    LOG.info("---------harga--------------------" + harga);
                    LOG.info("---------pasaran--------------------" + pasaran);
                    LOG.info("------------premium--------------------" + premium);
                } else {
                    premium = hakmilikPermohonan.getKadarPremium();
                }
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
//        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
//        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
//        if (!(listtuntutankos.isEmpty())) {
//            for (int i = 0; i < listtuntutankos.size(); i++) {
//                permohonantuntutkos = new PermohonanTuntutanKos();
//                permohonantuntutkos = listtuntutankos.get(i);
//                if (permohonantuntutkos.getKodTuntut() != null) {
//                    if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")) {
//                        premium = permohonantuntutkos.getAmaunTuntutan();
//                    }
//                }
//            }
//        }

    }

//public Resolution validate(){
//    LOG.info("------------inside validate---------------");
//    if(permohonanLaporanPelan!=null || permohonanLaporanPelan.getKodTanah()!=null||hakmilikPermohonan!=null || hakmilikPermohonan.getKodKegunaanTanah()!=null||kodKadarPremium!=null|| kodKadarPremium.getPeratusKadar()!=null||hakmilikPermohonan.getTempohPajakan()!=null||permohonanRujukanLuar!=null||permohonanRujukanLuar.getNilai()!=null)
//    addSimpleError("Maklumat Kadar Premium Tidak Di Jumpai");
//     return new JSP("pelupusan/jadual_kiraPremium.jsp").addParameter("tab", "true");
//
//}
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

LOG.info("-----------------kodTransaksiDAO------kt--------------"+kt);
     //COMMENT SINCE TUNTUTANKOS HAS BEEN DONE IN SEDIA SURAT KELULUSAN & BORANG 5A 09 June 2011
//     if (!(listtuntutankos.isEmpty())) {
//         for (int i = 0; i < listtuntutankos.size(); i++) {
//             permohonantuntutkos = new PermohonanTuntutanKos();
//             permohonantuntutkos = listtuntutankos.get(i);
//             if (permohonantuntutkos.getKodTuntut() != null) {
//
//                 if (permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")) {
//                     permohonantuntutkos.setAmaunTuntutan(premium);
//                     
//                 }
//
//                 ia = permohonantuntutkos.getInfoAudit();
//                 ia.setTarikhKemaskini(new java.util.Date());
//                 ia.setDiKemaskiniOleh(pengguna);
//                 permohonantuntutkos.setInfoAudit(ia);
//                 permohonantuntutkos.setKodTransaksi(kt);
//                 permohonantuntutkos.setPermohonan(permohonan);
//                 permohonantuntutkos.setCawangan(caw);
//                 pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
//                
//             }
//
//             // Added new Code
////             PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
////             permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan, "Premium");
////             if (permohonanTuntutan != null) {
////                 ia = permohonanTuntutan.getInfoAudit();
////                 ia.setTarikhKemaskini(new java.util.Date());
////                 ia.setDiKemaskiniOleh(pengguna);
////                 permohonanTuntutan.setInfoAudit(ia);
////                 pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
////                 // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
////                 PermohonanTuntutanButiran permohonanTuntutanButiran = new PermohonanTuntutanButiran();
////                 permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(), permohonanTuntutan.getIdTuntut());
////                 ia = permohonanTuntutanButiran.getInfoAudit();
////                 ia.setTarikhKemaskini(new java.util.Date());
////                 ia.setDiKemaskiniOleh(pengguna);
////                 permohonanTuntutanButiran.setInfoAudit(ia);
////                 pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
////             }
//         }
//
//        }
//     else {
//            String itemList = "DISPRM";
//            BigDecimal amaunTuntutanList = premium;
//            // added new code
////            PermohonanTuntutan permohonanTuntutan = new PermohonanTuntutan();
////            KodTransaksiTuntut kodTransTuntut = new KodTransaksiTuntut();
////            kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
////            permohonanTuntutan.setCawangan(caw);
////            permohonanTuntutan.setPermohonan(permohonan);
////            permohonanTuntutan.setInfoAudit(ia);
////            permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
////            permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
////            pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
//
//            for (int i = 0; i < 1; i++) {
//                KodTuntut kodTuntut = new KodTuntut();
//                kodTuntut = kodTuntutDAO.findById(itemList);
//                permohonantuntutkos = new PermohonanTuntutanKos();
//                permohonantuntutkos.setKodTuntut(kodTuntut);
//                permohonantuntutkos.setKodTransaksi( kodTransaksiDAO.findById("12301"));
//                permohonantuntutkos.setInfoAudit(ia);
//                permohonantuntutkos.setItem(kodTuntut.getNama());
//                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList);
//                permohonantuntutkos.setPermohonan(permohonan);
//                permohonantuntutkos.setCawangan(caw);
//                pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);
//
//                // added new code
////                PermohonanTuntutanButiran permohonanButir = new PermohonanTuntutanButiran();
////                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
////                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
////                permohonanButir.setInfoAudit(ia);
////                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
//            }
//        }

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
//        if (kodKategori != null) {
//            KodKategoriTanah k = kodKategoriTanahDAO.findById(kodKategori);
//            hakmilikPermohonan.setKategoriTanahBaru(k);
//        }

//        hakmilikPermohonan.setKategoriTanahBaru(kodKategoriTanahDAO.findById(kodKategori));
//        if(noPt!=null){
//        noPt.setHakmilikPermohonan(hakmilikPermohonan);
//        kodUL = new KodUOM();
//        String kod2 = getContext().getRequest().getParameter("noPt.kodUOM.kod");
//        kodUL.setKod(kod2);
//        noPt.setKodUOM(kodUL);
//        noPt.setInfoAudit(ia);
//        pelupusanService.saveOrUpdate(noPt);
//        }
        pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
         addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/jadual_kiraPremium.jsp").addParameter("tab", "true");
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

    
}
