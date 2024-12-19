///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
//import etanah.service.BPelService;
import etanah.model.NoPt;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
//import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author Rohans
 */
@UrlBinding("/pelupusan/draf_mmkn_pinda_luas")
public class DrafMMKNPindaLuasActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(DrafMMKNPindaLuasActionBean.class);
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    String pejTanah;
    private String tarikhPermohonan;
    private String kedudukanTnh;
    private String keadaanTnh;
    private String jenisTanaman;
    private String bilBangunan;
    private String tujuan;
    private String pembangunanDicadang;
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private HakmilikPermohonan hakmilikPermohonan;

    private String huraianPtg2;
    private String huraianPtg3;
    private String huraianPtg4;
    private String huraianPtg5;
    private String syorPtg2;
    private String syorPtg3;
    private String syorPtg4;
    private String syorPtg5;
    private boolean btn = true;
    private String lokasi;
    private String index;
    private String kodSekatanKepentingan;
    private String kodSyaratNyata;
    private String sekatKpntgn2;
    private String syaratNyata2;
    private String kodSktn;
    private String kod;
    private String jenishakmilik;
    private String tempoh;
    private String hasil;
    private String kodHmlk;
    private String catatan;
    private Boolean edit;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanKertas permohonanKertas;
    private NoPt noPt;
    private BigDecimal cukaiBagiTahunYangPertama;
    private BigDecimal premium;
    private BigDecimal jumlah;
    private String item;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private String idPermohonan;
    private BigDecimal calculation ;
    private String hakmilikSementara="";
    BigDecimal cal;

    @DefaultHandler
    public Resolution showForm() {
        edit = Boolean.FALSE;
        return new JSP("pelupusan/draf_mmkn_pinda_luas.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        edit = Boolean.TRUE;
        return new JSP("pelupusan/draf_mmkn_pinda_luas.jsp").addParameter("tab", "true");
    }

//    public Resolution search() {
//        index = getContext().getRequest().getParameter("index");
//        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
//    }
//
//    public Resolution showFormCariKodSekatan() {
//        index = getContext().getRequest().getParameter("index");
//        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
//    }
//
//    public Resolution cariKodSekatan() {
//        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kc = peng.getKodCawangan().getKod();
//        if (kodSekatanKepentingan != null) {
//            listKodSekatan = regService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
//            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
//            if (listKodSekatan.size() < 1) {
//                addSimpleError("Kod Sekatan Tidak Sah");
//            }
//        } else {
//            listKodSekatan = regService.searchKodSekatan("%", kc, sekatKpntgn2);
//            if (listKodSekatan.size() < 1) {
//                addSimpleError("Kod Sekatan Tidak Sah");
//            }
//        }
//        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
//    }

//    public Resolution cariKodSyaratNyata() {
//        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String kc = peng.getKodCawangan().getKod();
//        if (kodSyaratNyata != null) {
//            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
//            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
//            if (listKodSyaratNyata.size() < 1) {
//                addSimpleError("Kod Syarat Nyata Tidak Sah");
//            }
//        } else {
//            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata2);
//            if (listKodSyaratNyata.size() < 1) {
//                addSimpleError("Kod Syarat Nyata Tidak Sah");
//            }
//        }
//        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
//    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        cukaiBagiTahunYangPertama = BigDecimal.ZERO;
        premium = BigDecimal.ZERO;

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
        permohonanKertas = pelupusanService.findPermohonanKertasByIdPermohonan1(idPermohonan);
         listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);

        if(!(listtuntutankos.isEmpty())){
            for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                     if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")){
                        premium = permohonantuntutkos.getAmaunTuntutan();
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISTAX")){
                        cukaiBagiTahunYangPertama = permohonantuntutkos.getAmaunTuntutan();
                    }
                }
            }
            
         jumlah = premium.add(cukaiBagiTahunYangPertama);
        }
        
        String kc = peng.getKodCawangan().getKod();
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        
        String noLot = "";
        String bpm = "";
        String lotNama = "";
        String daerah = "";
        BigDecimal luas = BigDecimal.ZERO;
        String ursan = "";
        String uom = "";
        String lokasi="";
        BigDecimal nopt1=BigDecimal.ZERO;
        String nopt2="";

        if(hakmilikPermohonan !=null){
            if(hakmilikPermohonan.getKodHakmilikSementara()!= null){
            hakmilikSementara = hakmilikPermohonan.getKodHakmilikSementara().getNama();
            }
             if (noPt!=null&&noPt.getKodUOM() != null) {
             nopt2 = noPt.getKodUOM().getNama();
              }
            if (hakmilikPermohonan.getKodHakmilik() != null) {
                kodHmlk = hakmilikPermohonan.getKodHakmilik().getNama();
            }
            if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            }
            if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            }
            if (noPt!=null&&noPt.getKodUOM() != null) {
                nopt1 = noPt.getLuasDilulus();
            }
            noLot = hakmilikPermohonan.getNoLot();
            luas = hakmilikPermohonan.getLuasTerlibat();
            BigDecimal val =new BigDecimal(100);

            calculation =   (luas.subtract(nopt1).divide(luas, 5, BigDecimal.ROUND_HALF_EVEN).multiply(val));

            if(hakmilikPermohonan.getBandarPekanMukimBaru() != null){
                bpm = hakmilikPermohonan.getBandarPekanMukimBaru().getNama();
            }
            if(peng.getKodCawangan()!=null && peng.getKodCawangan().getDaerah()!=null){
                daerah =   peng.getKodCawangan().getDaerah().getNama();
            }
            if(hakmilikPermohonan.getLot()!= null){
                lotNama = hakmilikPermohonan.getLot().getNama();
            }
        }
        if(hakmilikPermohonan.getLokasi()!=null){
            lokasi = hakmilikPermohonan.getLokasi();
        }
         if(hakmilikPermohonan.getKodUnitLuas()!= null){
            uom = hakmilikPermohonan.getKodUnitLuas().getNama();
         }
        for (Pemohon pemohon : listPemohon) {
            listPengarah = pemohon.getPihak().getSenaraiPengarah();
        }

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        String sub = permohonan.getIdPermohonan().substring(0, 2);
        String tempat = "";
        if (sub.equals("04")) {
            tempat = "Melaka";
        } else if (sub.equals("05")) {
            tempat = "Negeri Sembilan Darul Khusus";
        }
       tujuan = "Kertas ini  bertujuan untuk mendapatkan kelulusan Majlis Mesyuarat Kerajaan Negeri untuk  meminda keluasan tanah daripada" +luas +" "+ uom + " kepada " + nopt1 +" "+nopt2 +" di "+lokasi+" "+ bpm+ " di bawah Seksyen 83(3) Kanun Tanah Negara"+" .";

             if (!(permohonan.getSenaraiKertas().isEmpty())) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = pelupusanService.findKertasByKod(idPermohonan,"RMN");
                  if (kertasP!=null) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan();
                        }  else if (kertasK.getBil() == 2) {
                            huraianPtg = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            huraianPtg2 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4) {
                            huraianPtg3 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5) {
                            huraianPtg4 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6) {
                            huraianPtg5 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 7) {
                            syorPtg = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8) {
                            syorPtg2 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9) {
                            syorPtg3 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 10) {
                            syorPtg4 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 11) {
                            syorPtg5 = kertasK.getKandungan();
                        }
                    }
                }

        }

    }

    public Resolution simpan1(){
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());

        if(cukaiBagiTahunYangPertama == null){
            cukaiBagiTahunYangPertama = new BigDecimal(0.00);
        }
        if(premium == null){
            premium = new BigDecimal(0.00);
        }
         PermohonanKertas permohonanKertas1 = new PermohonanKertas();
          if (kertasK != null) {
            permohonanKertas1 = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            ia = permohonanKertas1.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());

        } else {
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
        }

       ArrayList listUlasan = new ArrayList();
       if (huraianPtg == null || huraianPtg.equals("")) {
            huraianPtg = "TIADA DATA.";
        }
        if (huraianPtg2 == null || huraianPtg2.equals("")) {
            huraianPtg2 = "TIADA DATA.";
        }
        if (huraianPtg3 == null || huraianPtg3.equals("")) {
            huraianPtg3 = "TIADA DATA.";
        }
        if (huraianPtg4 == null || huraianPtg4.equals("")) {
            huraianPtg4 = "TIADA DATA.";
        }
        if (huraianPtg5 == null || huraianPtg5.equals("")) {
            huraianPtg5 = "TIADA DATA.";
        }

        if (syorPtg == null || syorPtg.equals("")) {
            syorPtg = "TIADA DATA.";
        }
        if (syorPtg2 == null || syorPtg2.equals("")) {
            syorPtg2 = "TIADA DATA.";
        }
        if (syorPtg3 == null || syorPtg3.equals("")) {
            syorPtg3 = "TIADA DATA.";
        }
        if (syorPtg4 == null || syorPtg4.equals("")) {
            syorPtg4 = "TIADA DATA.";
        }
        if (syorPtg5 == null || syorPtg5.equals("")) {
            syorPtg5 = "TIADA DATA.";
        }

        listUlasan.add(tujuan);
        listUlasan.add(huraianPtg);
        listUlasan.add(huraianPtg2);
        listUlasan.add(huraianPtg3);
        listUlasan.add(huraianPtg4);
        listUlasan.add(huraianPtg5);
        listUlasan.add(syorPtg);
        listUlasan.add(syorPtg2);
        listUlasan.add(syorPtg3);
        listUlasan.add(syorPtg4);
        listUlasan.add(syorPtg5);

            if (kertasK != null) {
                
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas1.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas1.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tujuan);
                    }  else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(huraianPtg);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(huraianPtg2);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(huraianPtg3);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(huraianPtg4);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(huraianPtg5);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(syorPtg);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(syorPtg2);
                    } else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(syorPtg3);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(syorPtg4);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(syorPtg5);
                    }

                    permohonanKertas1.setInfoAudit(ia);
                    permohonanKertas1.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas1.setPermohonan(permohonan);
                    permohonanKertas1.setTajuk("DRAF MMKN");
                    permohonanKertas1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                    kertasKandungan.setInfoAudit(ia);
                    pelupusanService.simpanPermohonanKertas(permohonanKertas1);
                    pelupusanService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        }
        else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas1.setInfoAudit(ia);
                permohonanKertas1.setCawangan(pengguna.getKodCawangan());
                permohonanKertas1.setPermohonan(permohonan);
                permohonanKertas1.setTajuk("DRAF MMKN");
                permohonanKertas1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                kk.setKertas(permohonanKertas1);
                kk.setBil(i + 1);
                kk.setInfoAudit(ia);
                kk.setKandungan(ulasan);
                kk.setCawangan(pengguna.getKodCawangan());
                pelupusanService.simpanPermohonanKertas(permohonanKertas1);
                pelupusanService.simpanPermohonanKertasKandungan(kk);
            }
        }
        listtuntutankos = pelupusanService.findTuntutByIdMohon(idPermohonan);
       
        if(!(listtuntutankos.isEmpty())){
             for(int i=0; i < listtuntutankos.size(); i++){

                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut() != null){
                    
                    if(permohonantuntutkos.getKodTuntut().getKod().equals("DISPRM")){
                        permohonantuntutkos.setAmaunTuntutan(premium);
                    }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DISTAX")){
                        permohonantuntutkos.setAmaunTuntutan(cukaiBagiTahunYangPertama);
                    }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pengguna);
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                   pelupusanService.simpanPermohonanTuntutanKos1(permohonantuntutkos);
                }

            // Added new Code
            PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
            permohonanTuntutan = pelupusanService.findPermohonanTuntutanByKodTransName(idPermohonan,"Notis 5A");
            if(permohonanTuntutan != null){
                ia = permohonanTuntutan.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutan.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);
                // find MohonTuntutButir by MohonTuntut and MohonTuntutKos
                PermohonanTuntutanButiran permohonanTuntutanButiran=new PermohonanTuntutanButiran();
                permohonanTuntutanButiran = pelupusanService.findPermohonanTuntutButirByIdTuntutAndIdKos(permohonantuntutkos.getIdKos(),permohonanTuntutan.getIdTuntut());
                ia = permohonanTuntutanButiran.getInfoAudit();
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDiKemaskiniOleh(pengguna);
                permohonanTuntutanButiran.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanTuntutanButiran);
            }
         }

        } else {
            String[] itemList = {"DISPRM", "DISTAX"};
            BigDecimal [] amaunTuntutanList = { premium, cukaiBagiTahunYangPertama};
                // added new code
                PermohonanTuntutan permohonanTuntutan=new PermohonanTuntutan();
                KodTransaksiTuntut  kodTransTuntut = new KodTransaksiTuntut();
                kodTransTuntut = pelupusanService.findKodTransTuntutByName("Notis 5A");
                permohonanTuntutan.setCawangan(caw);
                permohonanTuntutan.setPermohonan(permohonan);
                permohonanTuntutan.setInfoAudit(ia);
                permohonanTuntutan.setKodTransaksiTuntut(kodTransTuntut);
                permohonanTuntutan.setTarikhTuntutan(new java.util.Date());
                pelupusanService.simpanPermohonanTuntutan(permohonanTuntutan);

            for(int i = 0; i < 2; i++) {
                KodTuntut kodTuntut = new KodTuntut();
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                logger.debug("---------kodTuntut---------:"+kodTuntut);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(itemList[i]);
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                pelupusanService.simpanPermohonanTuntutanKos(permohonantuntutkos);

                // added new code
                PermohonanTuntutanButiran permohonanButir=new PermohonanTuntutanButiran();
                permohonanButir.setPermohonanTuntutan(permohonanTuntutan);
                permohonanButir.setPermohonanTuntutanKos(permohonantuntutkos);
                permohonanButir.setInfoAudit(ia);
                pelupusanService.simpanPermohonanTuntutanButiran(permohonanButir);
            }
        }
        jumlah = premium.add(cukaiBagiTahunYangPertama);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/draf_mmkn_pinda_luas.jsp").addParameter("tab", "true");
    }


    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh() {
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh) {
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh() {
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh) {
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman() {
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman) {
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan() {
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan) {
        this.bilBangunan = bilBangunan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public List<PihakPengarah> getListPengarah() {
        return listPengarah;
    }

    public void setPh(List<PihakPengarah> listPengarah) {
        this.listPengarah = listPengarah;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPmohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

   

    public KodBandarPekanMukimDAO getKodBandarPekanMukimDAO() {
        return kodBandarPekanMukimDAO;
    }

    public void setKodBandarPekanMukimDAO(KodBandarPekanMukimDAO kodBandarPekanMukimDAO) {
        this.kodBandarPekanMukimDAO = kodBandarPekanMukimDAO;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
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

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getJenishakmilik() {
        return jenishakmilik;
    }

    public void setJenishakmilik(String jenishakmilik) {
        this.jenishakmilik = jenishakmilik;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }

    public String getHuraianPtg2() {
        return huraianPtg2;
    }

    public void setHuraianPtg2(String huraianPtg2) {
        this.huraianPtg2 = huraianPtg2;
    }

    public String getHuraianPtg3() {
        return huraianPtg3;
    }

    public void setHuraianPtg3(String huraianPtg3) {
        this.huraianPtg3 = huraianPtg3;
    }

    public String getHuraianPtg4() {
        return huraianPtg4;
    }

    public void setHuraianPtg4(String huraianPtg4) {
        this.huraianPtg4 = huraianPtg4;
    }

    public String getHuraianPtg5() {
        return huraianPtg5;
    }

    public void setHuraianPtg5(String huraianPtg5) {
        this.huraianPtg5 = huraianPtg5;
    }

    public String getSyorPtg2() {
        return syorPtg2;
    }

    public void setSyorPtg2(String syorPtg2) {
        this.syorPtg2 = syorPtg2;
    }

    public String getSyorPtg3() {
        return syorPtg3;
    }

    public void setSyorPtg3(String syorPtg3) {
        this.syorPtg3 = syorPtg3;
    }

    public String getSyorPtg4() {
        return syorPtg4;
    }

    public void setSyorPtg4(String syorPtg4) {
        this.syorPtg4 = syorPtg4;
    }

    public String getSyorPtg5() {
        return syorPtg5;
    }

    public void setSyorPtg5(String syorPtg5) {
        this.syorPtg5 = syorPtg5;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public BigDecimal getCukaiBagiTahunYangPertama() {
        return cukaiBagiTahunYangPertama;
    }

    public void setCukaiBagiTahunYangPertama(BigDecimal cukaiBagiTahunYangPertama) {
        this.cukaiBagiTahunYangPertama = cukaiBagiTahunYangPertama;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public BigDecimal getCalculation() {
        return calculation;
    }

    public void setCalculation(BigDecimal calculation) {
        this.calculation = calculation;
    }

    public String getHakmilikSementara() {
        return hakmilikSementara;
    }

    public void setHakmilikSementara(String hakmilikSementara) {
        this.hakmilikSementara = hakmilikSementara;
    }

}

