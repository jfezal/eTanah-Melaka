/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodKegunaanTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodTuntut;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import org.apache.log4j.Logger;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/melaka/rencanaRingkasanLampiranA")
public class RencanaRingkasanLampiranAActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RencanaRingkasanLampiranAActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO;
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    @Inject
    KodKegunaanTanahDAO kodKegunaanTanahDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PembangunanUtility pembangunanUtility;
    
    private String idPermohonan;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hp;
    private PermohonanKertasKandungan kertasK;
    private String tujuan;
    private KodDokumen kd;        
    private List<PermohonanPlotPelan> senaraiPlotPelan;
    private List<PermohonanTuntutanKos> listtuntutankos;
    private PermohonanTuntutanKos permohonantuntutkos;
    private String lokasi;
    private String lokasi1;
    private HakmilikPermohonan hpTemp;
//    private Integer tempoh;
    private String premium;
    private String hasil;
//    private String jenis;
    private String sumbanganSaliranDesc;
    private String sumbanganSaliran;
    private String bayaranUkur;
    private List<HakmilikPermohonan> hpList;
    private BigDecimal kadarPremium;
    private BigDecimal cukaiBaru;
    private BigDecimal kosInfra;


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------rehydrate----------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        hpList = new ArrayList<HakmilikPermohonan>();
        hpList = permohonan.getSenaraiHakmilik();
        LOG.info("----------Permohonan----------:"+permohonan);
        hp = new HakmilikPermohonan();
        hp = permohonan.getSenaraiHakmilik().get(0);
        if(hp != null){            
            premium = hp.getKeteranganKadarPremium();
            hasil = hp.getKeteranganCukaiBaru();            
            sumbanganSaliranDesc = hp.getKeteranganInfra();
            if(hp.getKosInfra()!=null){
                sumbanganSaliran = hp.getKosInfra().toString();
            }
            bayaranUkur = hp.getKeteranganKadarUkur();

            if(bayaranUkur==null || bayaranUkur.equals("")){
                bayaranUkur = "Mengikut P.U(A)438";
            }
        }
        LOG.info("----------HP----------:"+hp);
        PermohonanKertas kertasP = new PermohonanKertas();
        if (!(permohonan.getSenaraiKertas().isEmpty())) {            
            kertasP = devService.findKertasByKod(idPermohonan, "RRPTG");
            if (kertasP != null) {
                for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        tujuan = kertasK.getKandungan();
                }//for
            }
         }
      if(permohonan.getSenaraiKertas().isEmpty()||(kertasP == null)){
        HakmilikPermohonan hp = new HakmilikPermohonan();
        List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
        hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        String kodPihak="PM";
        List<HakmilikPihakBerkepentingan> hakmilikPihakList=new ArrayList<HakmilikPihakBerkepentingan>();
        hakmilikPihakList = devService.findHakmilikPihakByKod(hakmilik.getIdHakmilik(), kodPihak);

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

//        if(!(hakmilikPermohonanList.isEmpty())){
//            hp = hakmilikPermohonanList.get(0);
//            hakmilikSingle = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
//        }

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                        + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                lokasi1 = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
//                nolot = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot();
//                nohakmilik = hakmilik.getKodHakmilik().getKod()+" " + hakmilik.getNoHakmilik();
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                    lokasi1 = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()+", ";
//                    nolot = nolot + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", ";
//                    nohakmilik = nohakmilik + ", " + hakmilik.getKodHakmilik().getKod() +" " + hakmilik.getNoHakmilik() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()
                            + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                    lokasi1 = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
//                    nolot = nolot + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot();
//                    nohakmilik = nohakmilik + " dan " + hakmilik.getKodHakmilik().getKod() +" " + hakmilik.getNoHakmilik();
                }
            }
        }

        String  nama ="";
        for(int j = 0;j < hakmilikPihakList.size(); j++){
            HakmilikPihakBerkepentingan pm = new HakmilikPihakBerkepentingan();
            pm = hakmilikPihakList.get(j);

            if(j == 0){
                nama = pm.getPihak().getNama();
            }
            if(j > 0){
                if(j <= ((hakmilikPihakList.size() + 2)- 4)){
                    nama = nama + ", " + pm.getPihak().getNama();
                } else if(j == (hakmilikPihakList.size() - 1)){
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
        }
        nama=pembangunanUtility.convertStringtoInitCap(nama);
        LOG.info("------Pemohon Nama2------:"+nama);

             if(tujuan == null){
             if(permohonan.getSebab() != null){
                 tujuan = permohonan.getSebab();
             }else{
                 tujuan = permohonan.getKodUrusan().getNama() + " daripada " + nama + " di " + lokasi + ".";
             }
             }
             
      }// else close
        senaraiPlotPelan = devService.findPermohonanPlotPelanByIdPermohonan(idPermohonan);
    }


    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        kd = kodDokumenDAO.findById("RRPTG");
        PermohonanKertas permohonanKertas = new PermohonanKertas();
        if (kertasK != null) {
            permohonanKertas = permohonanKertasDAO.findById(kertasK.getKertas().getIdKertas());
            infoAudit = permohonanKertas.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
       
//        hp = new HakmilikPermohonan();
//        hp = permohonan.getSenaraiHakmilik().get(0);
//        if(hp != null){
//             hp.setKeteranganKadarPremium(premium);
//             hp.setKeteranganCukaiBaru(hasil);
//             hp.setKeteranganInfra(sumbanganSaliran);
//             devService.simpanHakmilikPermohonan(hp);
//        }

        BigDecimal premium1 =BigDecimal.ZERO;
        BigDecimal hasil1 =BigDecimal.ZERO;
        BigDecimal sumbanganSaliran1 =BigDecimal.ZERO;

         for (int i = 0; i < hpList.size(); i++) {
            hp = new HakmilikPermohonan();
            hp = hpList.get(i);

           String premiumDesc = getContext().getRequest().getParameter("premiumDesc" + i);
           String hasilDesc = getContext().getRequest().getParameter("hasilDesc" + i);
           String sumbanganSaliranDesc = getContext().getRequest().getParameter("sumbanganSaliranDesc" + i);
           String premium = getContext().getRequest().getParameter("premium" + i);
           String hasil = getContext().getRequest().getParameter("hasil" + i);
           String sumbanganSaliran = getContext().getRequest().getParameter("sumbanganSaliran" + i);

            LOG.info("----------premium----------:"+premium);
            LOG.info("----------hasil----------:"+hasil);
            LOG.info("----------sumbanganSaliran----------:"+sumbanganSaliran);
            LOG.info("----------premiumDesc----------:"+premiumDesc);
            LOG.info("----------hasilDesc----------:"+hasilDesc);
            LOG.info("----------sumbanganSaliran----------:"+sumbanganSaliran);
            if(premium != null && !premium.equals("")){
                hp.setKadarPremium(new BigDecimal(premium));
                premium1 = premium1.add(new BigDecimal(premium));
            }else{
                hp.setKadarPremium(null);
                premium1 = premium1.add(BigDecimal.ZERO);
            }
            if(hasil != null && !hasil.equals("")){
                hp.setCukaiBaru(new BigDecimal(hasil));
                hasil1 = hasil1.add(new BigDecimal(hasil));
            }else{
                hp.setCukaiBaru(null);
                hasil1 = hasil1.add(BigDecimal.ZERO);
            }
            if(sumbanganSaliran != null && !sumbanganSaliran.equals("")){
                hp.setKosInfra(new BigDecimal(sumbanganSaliran));
                sumbanganSaliran1 = sumbanganSaliran1.add(new BigDecimal(sumbanganSaliran));
            }else{
                hp.setKosInfra(null);
                sumbanganSaliran1 = sumbanganSaliran1.add(BigDecimal.ZERO);
            }
             hp.setKeteranganKadarPremium(premiumDesc);
             hp.setKeteranganCukaiBaru(hasilDesc);
             hp.setKeteranganInfra(sumbanganSaliranDesc);
            devService.simpanHakmilikPermohonan(hp);
        }


        if (tujuan == null || tujuan.equals("")){
            tujuan = "TIADA DATA.";
        }

        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {
                     kertasK = new PermohonanKertasKandungan();
                     kertasK = permohonanKertas.getSenaraiKandungan().get(j);
                     
                    kertasK.setKandungan(tujuan);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    kertasK.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasK);
                }//for
                addSimpleMessage("Maklumat telah berjaya disimpan.");
            }
        }else{
             kertasK = new PermohonanKertasKandungan();
          
            permohonanKertas.setTajuk("Rencana Ringkasan LampiranA");
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            kertasK.setKandungan(tujuan);
            kertasK.setBil(1);
            kertasK.setInfoAudit(infoAudit);
            kertasK.setCawangan(pengguna.getKodCawangan());
            kertasK.setKertas(permohonanKertas);
            devService.simpanPermohonanKertas(permohonanKertas);
            devService.simpanPermohonanKertasKandungan(kertasK);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }


//        BigDecimal premium1 =BigDecimal.ZERO;
//        BigDecimal hasil1 =BigDecimal.ZERO;
//        BigDecimal sumbanganSaliran1 =BigDecimal.ZERO;
//        if(premium != null){
//            premium1 = new BigDecimal(premium);
//        }
//        if(hasil != null){
//            hasil1 = new BigDecimal(hasil);
//        }
//        if(sumbanganSaliran != null){
//            sumbanganSaliran1 = new BigDecimal(sumbanganSaliran);
//        }

        // Added code to save MohonTuntutKos
        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = devService.findTuntutByIdMohon(idPermohonan);

          if(!(listtuntutankos.isEmpty())){
              LOG.debug("-----------if----simpan------");
              for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        permohonantuntutkos.setAmaunTuntutan(premium1);
                 }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        permohonantuntutkos.setAmaunTuntutan(hasil1);
                 }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        permohonantuntutkos.setAmaunTuntutan(sumbanganSaliran1);
                 }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }else {
            LOG.debug("-----------else-----simpan-----");
            String[] itemList = {"DEV04","DEV11","DEV07"};
            BigDecimal [] amaunTuntutanList = {premium1,hasil1,sumbanganSaliran1};
               for(int i=0;i<itemList.length;i++){
                KodTuntut kodTuntut = new KodTuntut();
                LOG.debug("---------kodTuntut---------:"+itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());
                devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
            }
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/rencana_Ringkasan_LampiranA.jsp").addParameter("tab", "true");
    }


      public Resolution simpanSBMS() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new java.util.Date());
        ia.setDimasukOleh(pengguna);
        hp = permohonan.getSenaraiHakmilik().get(0);
        BigDecimal premium1 =BigDecimal.ZERO;
        BigDecimal hasil1 =BigDecimal.ZERO;
        BigDecimal sumbanganSaliran1 =BigDecimal.ZERO;

         for (int i = 0; i < hpList.size(); i++) {
            hp = new HakmilikPermohonan();
            hp = hpList.get(i);

           String kodKegunaanTanah = getContext().getRequest().getParameter("kodKegunaanTanah" + i);
           String jenisHakmilik = getContext().getRequest().getParameter("jenisHakmilik" + i);
           String tempoh = getContext().getRequest().getParameter("tempoh" + i);
           String premium = getContext().getRequest().getParameter("premium" + i);
           String hasil = getContext().getRequest().getParameter("hasil" + i);
           String premiumDesc = getContext().getRequest().getParameter("premiumDesc" + i);
           String hasilDesc = getContext().getRequest().getParameter("hasilDesc" + i);
//           String sumbanganSaliran = getContext().getRequest().getParameter("sumbanganSaliran" + i);
//           String bayaranUkur = getContext().getRequest().getParameter("bayaranUkur" + i);
           String jenis = getContext().getRequest().getParameter("jenis" + i);
           String syaratBaru = getContext().getRequest().getParameter("syaratBaru" + i);
           String sekatanBaru = getContext().getRequest().getParameter("sekatanBaru" + i);

            LOG.info("----------kodKegunaanTanah----------:"+kodKegunaanTanah);
            LOG.info("----------jenisHakmilik----------:"+jenisHakmilik);
            LOG.info("----------tempoh----------:"+tempoh);
            LOG.info("----------premium----------:"+premium);
            LOG.info("----------premium1----------:"+premium1);
            LOG.info("----------hasil----------:"+hasil);
            LOG.info("----------jenis----------:"+jenis);
            LOG.info("----------bayaranUkur----------:"+syaratBaru);
            LOG.info("----------jenis----------:"+sekatanBaru);
            
            if(premium != null && !premium.equals("")){
                hp.setKadarPremium(new BigDecimal(premium));
                premium1 = premium1.add(new BigDecimal(premium));
            }else{
                hp.setKadarPremium(null);
                premium1 = premium1.add(BigDecimal.ZERO);
            }
            if(hasil != null && !hasil.equals("")){
                hp.setCukaiBaru(new BigDecimal(hasil));
                hasil1 = hasil1.add(new BigDecimal(hasil));
            }else{
                hp.setCukaiBaru(null);
                hasil1 = hasil1.add(BigDecimal.ZERO);
            }
            LOG.info("----------premium1----------:"+premium1);
            LOG.info("----------hasil1----------:"+hasil1);            

            if(kodKegunaanTanah!=null && !kodKegunaanTanah.equals("")){
                hp.setKodKegunaanTanah(kodKegunaanTanahDAO.findById(kodKegunaanTanah));
             }else{
                hp.setKodHakmilik(null);
             }
            if(jenisHakmilik!=null && !jenisHakmilik.equals("")){
                hp.setKodHakmilik(kodHakmilikDAO.findById(jenisHakmilik));
             }else{
                hp.setKodHakmilik(null);
             }
             if(tempoh!=null && !tempoh.trim().equals("")){
                hp.setTempohPajakan(Integer.parseInt(tempoh));
             }
             hp.setKeteranganKadarPremium(premiumDesc);
             hp.setKeteranganCukaiBaru(hasilDesc);             
//             hp.setKeteranganInfra(sumbanganSaliran);
//             hp.setKeteranganKadarUkur(bayaranUkur);
             if(jenis!=null && !jenis.equals("")){
                hp.setJenisPenggunaanTanah(kodKategoriTanahDAO.findById(jenis));
             }else{
                hp.setJenisPenggunaanTanah(null);
             }

            LOG.info("-----syaratBaru----:" + syaratBaru);
            if (syaratBaru != null && !syaratBaru.equals("")) {                
                hp.setSyaratNyataBaru(kodSyaratNyataDAO.findById(syaratBaru));
            }

            LOG.info("-----sekatanBaru----:" + sekatanBaru);
            if (sekatanBaru != null && !sekatanBaru.equals("")) {                
                hp.setSekatanKepentinganBaru(kodSekatanKepentinganDAO.findById(sekatanBaru));
            } 
             
            devService.simpanHakmilikPermohonan(hp);
        }

        List<HakmilikPermohonan> hpList1= new ArrayList<HakmilikPermohonan>();
        hpList1 = devService.findHakmilikPermohonanByIdPermohonan(idPermohonan);
        hp = hpList1.get(0);
        hp.setKeteranganInfra(sumbanganSaliranDesc);
        if(sumbanganSaliran!=null && !sumbanganSaliran.equals("")){
            hp.setKosInfra(new BigDecimal(sumbanganSaliran));
        }else{
            hp.setKosInfra(null);
        }
        hp.setKeteranganKadarUkur(bayaranUkur);
        devService.simpanHakmilikPermohonan(hp);

        if(sumbanganSaliran != null && !sumbanganSaliran.equals("") ){
           sumbanganSaliran1 = new BigDecimal(sumbanganSaliran);
        }else{
            sumbanganSaliran1 = BigDecimal.ZERO;
        }
        

        // Added code to save MohonTuntutKos
        listtuntutankos = new ArrayList<PermohonanTuntutanKos>();
        listtuntutankos = devService.findTuntutByIdMohon(idPermohonan);

          if(!(listtuntutankos.isEmpty())){
              LOG.debug("-----------if----------");
              for(int i=0; i < listtuntutankos.size(); i++){
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos = listtuntutankos.get(i);
                if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV04")){
                        permohonantuntutkos.setAmaunTuntutan(premium1);
                 }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV11")){
                        permohonantuntutkos.setAmaunTuntutan(hasil1);
                 }else if(permohonantuntutkos.getKodTuntut().getKod().equals("DEV07")){
                        permohonantuntutkos.setAmaunTuntutan(sumbanganSaliran1);
                 }
                    ia = permohonantuntutkos.getInfoAudit();
                    ia.setDiKemaskiniOleh(pengguna);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonantuntutkos.setInfoAudit(ia);
                    permohonantuntutkos.setPermohonan(permohonan);
                    permohonantuntutkos.setCawangan(caw);
                    devService.simpanPermohonanTuntutanKos(permohonantuntutkos);                
            }
        }else {
            LOG.debug("-----------else----------");
            String[] itemList = {"DEV04","DEV11","DEV07"};
            BigDecimal [] amaunTuntutanList = {premium1,hasil1,sumbanganSaliran1};
               for(int i=0;i<itemList.length;i++){
                KodTuntut kodTuntut = new KodTuntut();
                LOG.debug("---------kodTuntut---------:"+itemList[i]);
                kodTuntut = kodTuntutDAO.findById(itemList[i]);                
                permohonantuntutkos = new PermohonanTuntutanKos();
                permohonantuntutkos.setKodTuntut(kodTuntut);
                permohonantuntutkos.setInfoAudit(ia);
                permohonantuntutkos.setItem(kodTuntut.getNama());
                permohonantuntutkos.setAmaunTuntutan(amaunTuntutanList[i]);
                permohonantuntutkos.setPermohonan(permohonan);
                permohonantuntutkos.setCawangan(caw);
                permohonantuntutkos.setKodTransaksi(kodTuntut.getKodKewTrans());                
                devService.simpanPermohonanTuntutanKos(permohonantuntutkos);
               }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/rencana_Ringkasan_LampiranA_SBMS.jsp").addParameter("tab", "true");
    }


    @DefaultHandler
    public Resolution showForm() {
        LOG.info("----------showForm----------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {

        LOG.info("----------showForm2----------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA_SBMS.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm3() {
        LOG.info("----------showForm---No Editting-------");
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("pembangunan/melaka/rencana_Ringkasan_LampiranA.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
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

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public List<PermohonanPlotPelan> getSenaraiPlotPelan() {
        return senaraiPlotPelan;
    }

    public void setSenaraiPlotPelan(List<PermohonanPlotPelan> senaraiPlotPelan) {
        this.senaraiPlotPelan = senaraiPlotPelan;
    }

    public HakmilikPermohonan getHpTemp() {
        return hpTemp;
    }

    public void setHpTemp(HakmilikPermohonan hpTemp) {
        this.hpTemp = hpTemp;
    }


    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getLokasi1() {
        return lokasi1;
    }

    public void setLokasi1(String lokasi1) {
        this.lokasi1 = lokasi1;
    }

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getSumbanganSaliran() {
        return sumbanganSaliran;
    }

    public void setSumbanganSaliran(String sumbanganSaliran) {
        this.sumbanganSaliran = sumbanganSaliran;
    }

    public List<HakmilikPermohonan> getHpList() {
        return hpList;
    }

    public void setHpList(List<HakmilikPermohonan> hpList) {
        this.hpList = hpList;
    }

    public String getBayaranUkur() {
        return bayaranUkur;
    }

    public void setBayaranUkur(String bayaranUkur) {
        this.bayaranUkur = bayaranUkur;
    }

    public List<PermohonanTuntutanKos> getListtuntutankos() {
        return listtuntutankos;
    }

    public void setListtuntutankos(List<PermohonanTuntutanKos> listtuntutankos) {
        this.listtuntutankos = listtuntutankos;
    }

    public PermohonanTuntutanKos getPermohonantuntutkos() {
        return permohonantuntutkos;
    }

    public void setPermohonantuntutkos(PermohonanTuntutanKos permohonantuntutkos) {
        this.permohonantuntutkos = permohonantuntutkos;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }

    public BigDecimal getKadarPremium() {
        return kadarPremium;
    }

    public void setKadarPremium(BigDecimal kadarPremium) {
        this.kadarPremium = kadarPremium;
    }

    public BigDecimal getKosInfra() {
        return kosInfra;
    }

    public void setKosInfra(BigDecimal kosInfra) {
        this.kosInfra = kosInfra;
    }

    public String getSumbanganSaliranDesc() {
        return sumbanganSaliranDesc;
    }

    public void setSumbanganSaliranDesc(String sumbanganSaliranDesc) {
        this.sumbanganSaliranDesc = sumbanganSaliranDesc;
    }

   
}
