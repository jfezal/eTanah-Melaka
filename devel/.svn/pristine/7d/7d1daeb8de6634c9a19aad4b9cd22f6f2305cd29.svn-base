/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import java.util.List;
/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/rayuanPelepasanKuota")
public class RayuanPelepasanKuotaActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(RayuanPelepasanKuotaActionBean.class);

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PembangunanService devService;



    private Pengguna pengguna;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private PermohonanKertasKandungan kertasK;
    private PermohonanKertasKandungan kandunganK;
    private Hakmilik hakmilik;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String persidangan;
    private String masasidang;
    private String tarikhMesyuarat;
    private String tempatsidang;

    private String jenisRayuan;
    private String jenisPermohonan;
    private String lokasi;
    private String tarikhKelulusan;
    private String perihalPemohon;
    private String tarikhTerima;
    private String bayaran;
    private String latarBelakang;
    private String pendaftaran;
    private String hargaJualan;
    private String ulasan;
    private String rayuanPemohon;
    private String perakuanPTG;
    private String keputusan;

    // code for table
    private List<PermohonanKertasKandungan> senaraiButirButir;
    private List<PermohonanKertasKandungan> senaraiPermohonanRayuan;
    private List<PermohonanKertasKandungan> senaraiCartaJualan;
    private List<PermohonanKertasKandungan> senaraiTempatan;
    private List<PermohonanKertasKandungan> senaraiMingguan;
    private List<PermohonanKertasKandungan> senaraiIkaranDIRadio;
    private List<PermohonanKertasKandungan> senaraiKelulusan;
    private List<PermohonanKertasKandungan> senaraiMaklumat;

    int k=0;



    @DefaultHandler
    public Resolution showForm() {
       LOG.info("showForm");
       getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new JSP("pembangunan/pecahSempadan/dev_Rayuan_Pelepasan_Quota.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/pecahSempadan/dev_Rayuan_Pelepasan_Quota.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        LOG.info("rehydrate start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        HakmilikPermohonan hp = new HakmilikPermohonan();
        tempatsidang = "Bilik Mesyuarat MMKN, Bangunan Seri Negeri, Ayer Keroh, Melaka";

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                }
            }
        }

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                LOG.info("senarai kertas:" + permohonan.getSenaraiKertas().size());
                if(kertasP.getTajuk().equals("RAYUAN PELEPASAN KUOTA")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
                        LOG.info("senarai kandungan:" + kertasP.getSenaraiKandungan().size());
                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);

                        if(kertasK.getBil() == 1){
                            persidangan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            masasidang = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 3){
                            tarikhMesyuarat = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4){
                            tempatsidang = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5) {
                            jenisRayuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6) {
                            jenisPermohonan = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 7){
                            lokasi = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8){
                            tarikhKelulusan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9) {
                            perihalPemohon = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 10) {
                            tarikhTerima = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 11){
                            bayaran = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 12){
                            latarBelakang = kertasK.getKandungan();
                        }else if (kertasK.getBil() == 13) {
                            pendaftaran = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 14) {
                            hargaJualan = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 15){
                            ulasan = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 16){
                            rayuanPemohon = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 17){
                            perakuanPTG = kertasK.getKandungan();
                        } else if(kertasK.getBil() == 18){
                            keputusan = kertasK.getKandungan();
                        }
                    }

                    // code to get table details
           // 9.1 Butir-Butir Pembangunan
           senaraiButirButir = new ArrayList<PermohonanKertasKandungan>();
           senaraiPermohonanRayuan = new ArrayList<PermohonanKertasKandungan>();
           senaraiCartaJualan = new ArrayList<PermohonanKertasKandungan>();
           senaraiTempatan = new ArrayList<PermohonanKertasKandungan>();
           senaraiMingguan = new ArrayList<PermohonanKertasKandungan>();
           senaraiIkaranDIRadio = new ArrayList<PermohonanKertasKandungan>();
           senaraiKelulusan = new ArrayList<PermohonanKertasKandungan>();
           senaraiMaklumat = new ArrayList<PermohonanKertasKandungan>();

           senaraiButirButir = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),1);
           senaraiPermohonanRayuan = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),2);
           senaraiCartaJualan = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),3);
           senaraiTempatan = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),4);
           senaraiMingguan = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),5);
           senaraiIkaranDIRadio = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),6);
           senaraiKelulusan = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),7);
           senaraiMaklumat = devService.getPermohonanKertasKandByIndex(kertasP.getIdKertas(),8);

                }
            }

        }

        LOG.info("rehydrate finish");
    }


    public Resolution simpan() {
        LOG.info("simpan start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

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

        ArrayList listUlasan = new ArrayList();

        if (persidangan == null || persidangan.equals("")) {
            persidangan = "TIADA DATA.";
        }
        if (masasidang == null || masasidang.equals("")) {
            masasidang = "TIADA DATA.";
        }
        if (tarikhMesyuarat == null || tarikhMesyuarat.equals("")) {
            tarikhMesyuarat = "TIADA DATA.";
        }
        if (tempatsidang == null || tempatsidang.equals("")) {
            tempatsidang = "TIADA DATA.";
        }
        if (jenisRayuan == null || jenisRayuan.equals("")) {
            jenisRayuan = "TIADA DATA.";
        }
        if (jenisPermohonan == null || jenisPermohonan.equals("")) {
            jenisPermohonan = "TIADA DATA.";
        }
        if (lokasi == null || lokasi.equals("")) {
            lokasi = "TIADA DATA.";
        }
        if (tarikhKelulusan == null || tarikhKelulusan.equals("")) {
            tarikhKelulusan = "TIADA DATA.";
        }
        if (perihalPemohon == null || perihalPemohon.equals("")) {
            perihalPemohon = "TIADA DATA.";
        }
        if (tarikhTerima == null || tarikhTerima.equals("")) {
            tarikhTerima = "TIADA DATA.";
        }
        if (bayaran == null || bayaran.equals("")) {
            bayaran = "TIADA DATA.";
        }
        if (latarBelakang == null || latarBelakang.equals("")) {
            latarBelakang = "TIADA DATA.";
        }
        if (pendaftaran == null || pendaftaran.equals("")) {
            pendaftaran = "TIADA DATA.";
        }
        if (hargaJualan == null || hargaJualan.equals("")) {
            hargaJualan = "TIADA DATA.";
        }
        if (ulasan == null || ulasan.equals("")) {
            ulasan = "TIADA DATA.";
        }
        if (rayuanPemohon == null || rayuanPemohon.equals("")) {
            rayuanPemohon = "TIADA DATA.";
        }
        if (perakuanPTG == null || perakuanPTG.equals("")) {
            perakuanPTG = "TIADA DATA.";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = "TIADA DATA.";
        }

        listUlasan.add(persidangan);
        listUlasan.add(masasidang);
        listUlasan.add(tarikhMesyuarat);
        listUlasan.add(tempatsidang);
        listUlasan.add(jenisRayuan);
        listUlasan.add(jenisPermohonan);
        listUlasan.add(lokasi);
        listUlasan.add(tarikhKelulusan);
        listUlasan.add(perihalPemohon);
        listUlasan.add(tarikhTerima);
        listUlasan.add(bayaran);
        listUlasan.add(latarBelakang);
        listUlasan.add(pendaftaran);
        listUlasan.add(hargaJualan);
        listUlasan.add(ulasan);
        listUlasan.add(rayuanPemohon);
        listUlasan.add(perakuanPTG);
        listUlasan.add(keputusan);


        if (kertasK != null) {
            if (!kertasK.getKandungan().isEmpty()) {
            LOG.info("kemaskini start");
                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                    LOG.info("senarai kandungan:" + permohonanKertas.getSenaraiKandungan().size());

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(persidangan);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(masasidang);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(tarikhMesyuarat);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(tempatsidang);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(jenisRayuan);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(jenisPermohonan);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(lokasi);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(tarikhKelulusan);
                    } else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(perihalPemohon);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(tarikhTerima);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(bayaran);
                    } else if (kertasKandungan.getBil() == 12) {
                        kertasKandungan.setKandungan(latarBelakang);
                    } else if (kertasKandungan.getBil() == 13) {
                        kertasKandungan.setKandungan(pendaftaran);
                    } else if (kertasKandungan.getBil() == 14) {
                        kertasKandungan.setKandungan(hargaJualan);
                    } else if (kertasKandungan.getBil() == 15){
                        kertasKandungan.setKandungan(ulasan);
                    } else if (kertasKandungan.getBil() == 16){
                        kertasKandungan.setKandungan(rayuanPemohon);
                    } else if (kertasKandungan.getBil() == 17){
                        kertasKandungan.setKandungan(perakuanPTG);
                    }else if (kertasKandungan.getBil() == 18){
                        kertasKandungan.setKandungan(keputusan);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("RAYUAN PELEPASAN KUOTA");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }
            LOG.info("kemaskini finish");
        } else {

            LOG.info("simpan new entry");
            for (int i = 0; i < listUlasan.size(); i++) {

                String dataulasan = (String) listUlasan.get(i);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RAYUAN PELEPASAN KUOTA");
                kk.setKertas(permohonanKertas);
                kk.setBil(i + 1);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(dataulasan);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }
        }


                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RAYUAN PELEPASAN KUOTA");

                if (kertasK != null) {
                    if (!kertasK.getKandungan().isEmpty()) {
                          PermohonanKertasKandungan kand1= devService.getMaxBillNoOfPermohonanKertasKandungan(kertasK.getKertas().getIdKertas());
                          if(kand1!=null){
                              k= kand1.getBil();
                              k = k+1;
                          }
                     //   System.out.println("--------k-value-------"+k);
                    }
                }else{
                    k=19;
                }

                for(int i=1;i<=8;i++){
                   // System.out.println("------K----Value in outer loop---------------"+k);
                    String countStr = (String)getContext().getRequest().getParameter("count"+i);
                    int recordCount = Integer.parseInt(countStr);
                    if(i==1 || i==7 || i==8 ){
                        // Butir-butir Permohonan table having 4 columns
                        recordCount = 4*recordCount;
                    }else if(i==2 || i==4 || i==5){
                        recordCount = 3*recordCount;
                    }else if(i==3){
                        recordCount = 8*recordCount;
                    }else if(i==6){
                        recordCount = 2*recordCount;
                    }

                   // System.out.println("------recordCount------"+recordCount);

                       for(int j=1;j<=recordCount;j++){
                        String kand = (String)getContext().getRequest().getParameter(i+"."+j);
                        PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                        String subTajuk = i+"."+j;
                        kk = devService.getPermohonanKertasKandunganBySubtajuk(permohonanKertas.getIdKertas(),subTajuk);
                        
                         if(kk == null){
                            kk = new PermohonanKertasKandungan();
                            kk.setBil(k++);
                         }
                        kk.setKertas(permohonanKertas);
                        kk.setInfoAudit(infoAudit);
                        if(kand!=null && !kand.equals("")){
                        kk.setKandungan(kand);
                        }else{
                         kk.setKandungan(" ");
                        }
                        kk.setCawangan(pengguna.getKodCawangan());
                        kk.setSubtajuk(i+"."+j);
                        devService.simpanPermohonanKertasKandungan(kk);
                       } //for

                }

          rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        LOG.info("simpan finish");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_Rayuan_Pelepasan_Quota.jsp").addParameter("tab", "true");
    }


    public Resolution delete(){
          LOG.info("delete start");
         //System.out.println("-----Delete---------------");
         String tableNoStr = (String) getContext().getRequest().getParameter("tableIndex");
        // System.out.println("-----Delete----tableNoStr-----------"+tableNoStr);
         int tableNo = Integer.parseInt(tableNoStr);
         int recordCount =0;
        if(tableNo==1 || tableNo==7 || tableNo==8 ){           
            recordCount = 4;
        }else if(tableNo==2 || tableNo==4 || tableNo==5){
            recordCount = 3;
        }else if(tableNo==3){
            recordCount = 8;
        }else if(tableNo==6){
            recordCount = 2;
        }

         List<PermohonanKertasKandungan> senaraiKand =new ArrayList<PermohonanKertasKandungan>();
         senaraiKand = devService.deletePermohonanKertasKandungan(kertasK.getKertas().getIdKertas(),tableNo);
         if(!senaraiKand.isEmpty()){
          //   System.out.println("-----senaraiKand.size()---------------"+senaraiKand.size());
             for(int i=0;i<recordCount;i++){
           //      System.out.println("-----Deleted Kand---------------"+senaraiKand.get(i).getKandungan());
                 devService.deleteKertasKandungan(senaraiKand.get(i));
             }
         }
         LOG.info("delete finish");
         rehydrate();
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_Rayuan_Pelepasan_Quota.jsp").addParameter("tab", "true");
     }


    public String getBayaran() {
        return bayaran;
    }

    public void setBayaran(String bayaran) {
        this.bayaran = bayaran;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getHargaJualan() {
        return hargaJualan;
    }

    public void setHargaJualan(String hargaJualan) {
        this.hargaJualan = hargaJualan;
    }

    public String getJenisPermohonan() {
        return jenisPermohonan;
    }

    public void setJenisPermohonan(String jenisPermohonan) {
        this.jenisPermohonan = jenisPermohonan;
    }

    public String getJenisRayuan() {
        return jenisRayuan;
    }

    public void setJenisRayuan(String jenisRayuan) {
        this.jenisRayuan = jenisRayuan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getMasasidang() {
        return masasidang;
    }

    public void setMasasidang(String masasidang) {
        this.masasidang = masasidang;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(String pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public String getPerakuanPTG() {
        return perakuanPTG;
    }

    public void setPerakuanPTG(String perakuanPTG) {
        this.perakuanPTG = perakuanPTG;
    }

    public String getPerihalPemohon() {
        return perihalPemohon;
    }

    public void setPerihalPemohon(String perihalPemohon) {
        this.perihalPemohon = perihalPemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getPersidangan() {
        return persidangan;
    }

    public void setPersidangan(String persidangan) {
        this.persidangan = persidangan;
    }

    public String getRayuanPemohon() {
        return rayuanPemohon;
    }

    public void setRayuanPemohon(String rayuanPemohon) {
        this.rayuanPemohon = rayuanPemohon;
    }

    public String getTarikhKelulusan() {
        return tarikhKelulusan;
    }

    public void setTarikhKelulusan(String tarikhKelulusan) {
        this.tarikhKelulusan = tarikhKelulusan;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTempatsidang() {
        return tempatsidang;
    }

    public void setTempatsidang(String tempatsidang) {
        this.tempatsidang = tempatsidang;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<PermohonanKertasKandungan> getSenaraiButirButir() {
        return senaraiButirButir;
    }

    public void setSenaraiButirButir(List<PermohonanKertasKandungan> senaraiButirButir) {
        this.senaraiButirButir = senaraiButirButir;
    }

    public List<PermohonanKertasKandungan> getSenaraiPermohonanRayuan() {
        return senaraiPermohonanRayuan;
    }

    public void setSenaraiPermohonanRayuan(List<PermohonanKertasKandungan> senaraiPermohonanRayuan) {
        this.senaraiPermohonanRayuan = senaraiPermohonanRayuan;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public List<PermohonanKertasKandungan> getSenaraiCartaJualan() {
        return senaraiCartaJualan;
    }

    public void setSenaraiCartaJualan(List<PermohonanKertasKandungan> senaraiCartaJualan) {
        this.senaraiCartaJualan = senaraiCartaJualan;
    }

    public List<PermohonanKertasKandungan> getSenaraiMingguan() {
        return senaraiMingguan;
    }

    public void setSenaraiMingguan(List<PermohonanKertasKandungan> senaraiMingguan) {
        this.senaraiMingguan = senaraiMingguan;
    }

    public List<PermohonanKertasKandungan> getSenaraiTempatan() {
        return senaraiTempatan;
    }

    public void setSenaraiTempatan(List<PermohonanKertasKandungan> senaraiTempatan) {
        this.senaraiTempatan = senaraiTempatan;
    }

    public List<PermohonanKertasKandungan> getSenaraiIkaranDIRadio() {
        return senaraiIkaranDIRadio;
    }

    public void setSenaraiIkaranDIRadio(List<PermohonanKertasKandungan> senaraiIkaranDIRadio) {
        this.senaraiIkaranDIRadio = senaraiIkaranDIRadio;
    }

    public List<PermohonanKertasKandungan> getSenaraiKelulusan() {
        return senaraiKelulusan;
    }

    public void setSenaraiKelulusan(List<PermohonanKertasKandungan> senaraiKelulusan) {
        this.senaraiKelulusan = senaraiKelulusan;
    }

    public List<PermohonanKertasKandungan> getSenaraiMaklumat() {
        return senaraiMaklumat;
    }

    public void setSenaraiMaklumat(List<PermohonanKertasKandungan> senaraiMaklumat) {
        this.senaraiMaklumat = senaraiMaklumat;
    }

    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }


}
