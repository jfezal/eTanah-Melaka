/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.service.common.PemohonService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Session;
/**
 *
 * @author NageswaraRao
 */
@UrlBinding("/pembangunan/dev_Ringkasan_Rayuan")
public class RingkasanRayuanActionBean  extends AbleActionBean {

    
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PemohonService pemohonService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    private Permohonan permohonan;
    private Pemohon pemohon;

    private String keritasBill;
    private String name;
    private String jenisRayuan;
    private String alamat;
    private String pelepasan;
    private String alasan;
    private String perakuanPtg;
    private String keputusan;

    private String jumalah1;
    private String bumi;
    private String bukan;
    private String add;
    private String simpian;
    private String kandunganK;
    private boolean btn = true;
    private PermohonanKertasKandungan kertasK;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Integer count =0;

    //Perihal Permohonan table properties

    private String bumiputra;
    private String bukanBumiputra;
    private String jumlahPengundi;
    private String selepasPelepasan;
    private String melayu1;
    private String melayu2;
    private String melayu3;
    private String cina1;
    private String cina2;
    private String cina3;
    private String india1;
    private String india2;
    private String india3;
    private String lainLain1;
    private String lainLain2;
    private String lainLain3;

    private String jumlahTotal1;
    private String jumlahTotal2;
    private String jumlahTotal3;

    private List senaraiRingkas[] = new ArrayList [ 5 ];
    private List senaraiTingkat[] = new ArrayList [ 5 ];
    private int k;

    @DefaultHandler
    public Resolution showForm() {
        if (kertasK != null) {
            btn = false;
        }
          getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_Ringkasan_Rayuan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/pecahSempadan/dev_Ringkasan_Rayuan.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        getContext().getRequest().setAttribute("count", count);

        List<Pemohon> pemohonList = new ArrayList<Pemohon>();
        pemohonList = pemohonService.findPemohonByIdPermohonan(idPermohonan);
        if(!pemohonList.isEmpty()){
            pemohon = pemohonList.get(0);
            if(pemohon.getPihak()!=null){
                name = pemohon.getPihak().getNama();
            }
        }


        if (!(permohonan.getSenaraiKertas().isEmpty())) {

             for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if (kertasP.getTajuk().equals("RINGKASAN RAYUAN PELAN KUOTA")) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        keritasBill = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                        if (kertasK.getBil() == 1) {
                            name = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            jenisRayuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            alamat = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4) {
                            pelepasan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5) {
                            alasan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 6) {
                            perakuanPtg = kertasK.getKandungan();
                        }else if (kertasK.getBil() == 7) {
                            keputusan = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 8) {
                            keritasBill = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 9) {
                            bumiputra = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 10) {
                            bukanBumiputra = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 11) {
                            jumlahPengundi = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 12) {
                            selepasPelepasan = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 13) {
                            melayu1 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 14) {
                            melayu2 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 15) {
                            melayu3 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 16) {
                            cina1 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 17) {
                            cina2 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 18) {
                            cina3 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 19) {
                            india1 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 20) {
                            india2 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 21) {
                            india3 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 22) {
                            lainLain1 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 23) {
                            lainLain2 = kertasK.getKandungan();
                        }else if(kertasK.getBil() == 24) {
                            lainLain3 = kertasK.getKandungan();
                        }
                        // calculate Jumlah Total
                        calculateJumlahTotal();
                        

                      //  PermohonanKertasKandungan maxKertasKand = new PermohonanKertasKandungan();

                    }// for loop

                   // getContext().getRequest().setAttribute("count", count);
                     List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
                     PermohonanKertasKandungan maxKertasKand = new PermohonanKertasKandungan();
                        kandList = devService.getMaxTableNoOfPermohonanKertasKandungan(kertasP.getIdKertas());
                        
                        if(kandList!=null && !kandList.isEmpty()){
                             maxKertasKand = kandList.get(0);

                        if(maxKertasKand!=null){
                            //System.out.println("------maxKertasKand---------------:"+maxKertasKand);
                            String subtajuk = maxKertasKand.getSubtajuk();
                         
                            int index = subtajuk.indexOf(".");
                            String str = subtajuk.substring(5,index);
                            //System.out.println("------tableCount-------STR----------:"+str);

                            int tableCount = Integer.parseInt(str);
                            count = tableCount;
                            getContext().getRequest().setAttribute("count", count);
                            
                            for(int k=1;k<=tableCount;k++){
                                senaraiRingkas[k] = new ArrayList<PermohonanKertasKandungan>();
                                //RingkasanRayuan ringkasan = new RingkasanRayuan();
                                senaraiRingkas[k] = devService.getPermohonanKertasKandByIndex(kertasK.getKertas().getIdKertas(),k);

                                senaraiTingkat[k] = new ArrayList<PermohonanKertasKandungan>();
                                senaraiTingkat[k] = devService.getSenaraiTingkatsOfKertasKandungan(kertasK.getKertas().getIdKertas(),k);

//                                System.out.println("-----senaraiRingkas--size------"+senaraiRingkas[k].size());
//                                System.out.println("-----senaraiTingkat--size------"+senaraiTingkat[k].size());

                            }
                        }
                     }//if

                }
             }
       }


      //  s.close();
    }





     public Resolution deleteRecords() {
//         System.out.println("-----Delete---------------");
         String tableIndex = (String) getContext().getRequest().getParameter("tableIndex");
         //String rowIndex = (String) getContext().getRequest().getParameter("rowIndex");
         //System.out.println("-----rowIndex---------------"+rowIndex);
         List<PermohonanKertasKandungan> senaraiKand =new ArrayList<PermohonanKertasKandungan>();
         senaraiKand = devService.deletePermohonanKertasKandungan(kertasK.getKertas().getIdKertas(),Integer.parseInt(tableIndex));
         //senaraiKand = devService.getSenaraiPermohonanKertasKandunganForDelete(Integer.parseInt(tableIndex));
         if(!senaraiKand.isEmpty()){
  //           System.out.println("-----senaraiKand.size()---------------"+senaraiKand.size());
            // System.out.println("-----(Integer.parseInt(rowIndex)*3+1)---------------"+(Integer.parseInt(rowIndex)*3+1));
             int recordCount =0;
             if(senaraiKand.size() > 4){
                recordCount = 3;
             }else if(senaraiKand.size() == 4){
                 recordCount = 4;
             }

             for(int i=0;i<recordCount;i++){
    //             System.out.println("-----Deleted Kand---------------"+senaraiKand.get(i).getKandungan());
                 devService.deleteKertasKandungan(senaraiKand.get(i));
             }
         }
          rehydrate();
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/dev_Ringkasan_Rayuan.jsp").addParameter("tab", "true");
     }


     

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
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


        if (name == null || name.equals("")) {
            name = "TIADA DATA.";
        }
        if (jenisRayuan == null || jenisRayuan.equals("")) {
            jenisRayuan = "TIADA DATA.";
        }
        if (alamat == null || alamat.equals("")) {
            alamat = "TIADA DATA.";
        }
        if (pelepasan == null || pelepasan.equals("")) {
            pelepasan = "TIADA DATA.";
        }
        if (alasan == null || alasan.equals("")) {
            alasan = "TIADA DATA.";
        }
        if (perakuanPtg == null || perakuanPtg.equals("")) {
            perakuanPtg = "TIADA DATA.";
        }
        if (keputusan == null || keputusan.equals("")) {
            keputusan = "TIADA DATA.";
        }
        if (keritasBill == null || keritasBill.equals("")) {
            keritasBill = "TIADA DATA.";
        }
        if (bumiputra == null || bumiputra.equals("")) {
            bumiputra = "TIADA DATA.";
        }
        if (bukanBumiputra == null || bukanBumiputra.equals("")) {
            bukanBumiputra = "TIADA DATA.";
        }
        if (jumlahPengundi == null || jumlahPengundi.equals("")) {
            jumlahPengundi = "TIADA DATA.";
        }
        if (selepasPelepasan == null || selepasPelepasan.equals("")) {
            selepasPelepasan = "TIADA DATA.";
        }
        if (melayu1 == null || melayu1.equals("")) {
            melayu1 = "0";
        }
        if (melayu2 == null || melayu2.equals("")) {
            melayu2 = "0.0";
        }
        if (melayu3 == null || melayu3.equals("")) {
            melayu3 = "0.0";
        }
        if (cina1 == null || cina1.equals("")) {
            cina1 = "0";
        }
        if (cina2 == null || cina2.equals("")) {
            cina2 = "0.0";
        }
        if (cina3 == null || cina3.equals("")) {
            cina3 = "0.0";
        }
        if (india1 == null || india1.equals("")) {
            india1 = "0";
        }
        if (india2 == null || india2.equals("")) {
            india2 = "0.0";
        }
        if (india3 == null || india3.equals("")) {
            india3 = "0.0";
        }
        if (lainLain1 == null || lainLain1.equals("")) {
            lainLain1 = "0";
        }
        if (lainLain2 == null || lainLain2.equals("")) {
            lainLain2 = "0.0";
        }
        if (lainLain3 == null || lainLain3.equals("")) {
            lainLain3 = "0.0";
        }

        listUlasan.add(name);
        listUlasan.add(jenisRayuan);
        listUlasan.add(alamat);
        listUlasan.add(pelepasan);
        listUlasan.add(alasan);
        listUlasan.add(perakuanPtg);
        listUlasan.add(keputusan);
        listUlasan.add(keritasBill);

        listUlasan.add(bumiputra);
        listUlasan.add(bukanBumiputra);
        listUlasan.add(jumlahPengundi);
        listUlasan.add(selepasPelepasan);
        listUlasan.add(melayu1);
        listUlasan.add(melayu2);
        listUlasan.add(melayu3);
        listUlasan.add(cina1);
        listUlasan.add(cina2);
        listUlasan.add(cina3);
        listUlasan.add(india1);
        listUlasan.add(india2);
        listUlasan.add(india3);
        listUlasan.add(lainLain1);
        listUlasan.add(lainLain2);
        listUlasan.add(lainLain3);


        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);
                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(name);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(jenisRayuan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(alamat);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(pelepasan);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(alasan);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(perakuanPtg);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(keputusan);
                    }else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(keritasBill);
                    }else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(bumiputra);
                    }else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(bukanBumiputra);
                    }else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(jumlahPengundi);
                    }else if (kertasKandungan.getBil() == 12) {
                        kertasKandungan.setKandungan(selepasPelepasan);
                    }else if (kertasKandungan.getBil() == 13) {
                        kertasKandungan.setKandungan(melayu1);
                    }else if (kertasKandungan.getBil() == 14) {
                        kertasKandungan.setKandungan(melayu2);
                    }else if (kertasKandungan.getBil() == 15) {
                        kertasKandungan.setKandungan(melayu3);
                    }else if (kertasKandungan.getBil() == 16) {
                        kertasKandungan.setKandungan(cina1);
                    }else if (kertasKandungan.getBil() == 17) {
                        kertasKandungan.setKandungan(cina2);
                    }else if (kertasKandungan.getBil() == 18) {
                        kertasKandungan.setKandungan(cina3);
                    }else if (kertasKandungan.getBil() == 19) {
                        kertasKandungan.setKandungan(india1);
                    }else if (kertasKandungan.getBil() == 20) {
                        kertasKandungan.setKandungan(india2);
                    }else if (kertasKandungan.getBil() == 21) {
                        kertasKandungan.setKandungan(india3);
                    }else if (kertasKandungan.getBil() == 22) {
                        kertasKandungan.setKandungan(lainLain1);
                    }else if (kertasKandungan.getBil() == 23) {
                        kertasKandungan.setKandungan(lainLain2);
                    }else if (kertasKandungan.getBil() == 24) {
                        kertasKandungan.setKandungan(lainLain3);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("RINGKASAN RAYUAN PELAN KUOTA");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }
        }else{
            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);

                System.out.println("-----ulasan----:" + ulasan);

                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RINGKASAN RAYUAN PELAN KUOTA");
                kk.setKertas(permohonanKertas);
                kk.setBil(i + 1);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(ulasan);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }

        }

              // Added code for dynamic table

                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("RINGKASAN RAYUAN PELAN KUOTA");

                if (kertasK != null) {
                    if (!kertasK.getKandungan().isEmpty()) {
                          PermohonanKertasKandungan kand1= devService.getMaxBillNoOfPermohonanKertasKandungan(kertasK.getKertas().getIdKertas());
                          if(kand1!=null){
                              k= kand1.getBil();
                              k = k+1;
                          }else{
                              k=25;
                          }
                        //System.out.println("--------k-value-------"+k);
                    }
                }else{
                    k=25;
                }

        String countStr = (String)getContext().getRequest().getParameter("count");
        int tableCount = Integer.parseInt(countStr);
       // System.out.println("--------TableCount-------:"+(tableCount));
        
        for(int i=1;i<=tableCount;i++){

            // Code to Insert Rumah berkembar Tingkat Details
            for(int p=1;p<=13;p++){
            String jenis = (String)getContext().getRequest().getParameter("jenis"+i+"."+p);

            PermohonanKertasKandungan kk5 = new PermohonanKertasKandungan();
            String tingkatSubTajuk = "jenis"+i+"."+p;
             kk5 = devService.getPermohonanKertasKandunganBySubtajuk(permohonanKertas.getIdKertas(),tingkatSubTajuk);
             if(kk5 == null){
                kk5 = new PermohonanKertasKandungan();
                kk5.setBil(k++);
             }

            kk5.setKertas(permohonanKertas);            
            kk5.setInfoAudit(infoAudit);
            if(jenis!=null && !jenis.equals("")){
            kk5.setKandungan(jenis);
            }else{
            kk5.setKandungan(" ");
            }
            kk5.setCawangan(pengguna.getKodCawangan());
            kk5.setSubtajuk(tingkatSubTajuk);
            devService.simpanPermohonanKertasKandungan(kk5);
            }// for loop jenis

            
            String countStr1 = (String)getContext().getRequest().getParameter("count"+i+"");
            int recordCount = Integer.parseInt(countStr1);
       //     System.out.println("--------recordCount-------:"+(recordCount));
            recordCount = (recordCount*3)+1;
            
           for(int j=1;j<=recordCount;j++){

      //      System.out.println("------K----Value in for loop---------------"+k);

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

         } // table for loop

        rehydrate();

     // end of dynamic table code

        addSimpleMessage("Maklumat telah berjaya disimpan.");
//        System.out.println("-----------Save-----------");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/dev_Ringkasan_Rayuan.jsp").addParameter("tab", "true");
    }



    // calculate Jumlah Total
    public void calculateJumlahTotal(){
        Integer tempJumlahTotal1=0;
        if(melayu1!=null && !melayu1.equals("")){
            tempJumlahTotal1 = tempJumlahTotal1+Integer.parseInt(melayu1);
        }
        if(cina1!=null && !cina1.equals("")){
            tempJumlahTotal1 = tempJumlahTotal1+Integer.parseInt(cina1);
        }
        if(india1!=null && !india1.equals("")){
            tempJumlahTotal1 = tempJumlahTotal1+Integer.parseInt(india1);
        }
        if(lainLain1!=null && !lainLain1.equals("")){
            tempJumlahTotal1 = tempJumlahTotal1+Integer.parseInt(lainLain1);
        }

        jumlahTotal1 = tempJumlahTotal1.toString();

        Double tempJumlahTotal2 = 0.0;
        if(melayu2!=null && !melayu2.equals("")){
            tempJumlahTotal2 = tempJumlahTotal2+Double.parseDouble(melayu2);
        }
        if(cina2!=null && !cina2.equals("")){
            tempJumlahTotal2 = tempJumlahTotal2+Double.parseDouble(cina2);
        }
        if(india2!=null && !india2.equals("")){
            tempJumlahTotal2 = tempJumlahTotal2+Double.parseDouble(india2);
        }
        if(lainLain2!=null && !lainLain2.equals("")){
            tempJumlahTotal2 = tempJumlahTotal2+Double.parseDouble(lainLain2);
        }
        jumlahTotal2 = tempJumlahTotal2.toString();

        Double tempJumlahTotal3 = 0.0;
        if(melayu3!=null && !melayu3.equals("")){
            tempJumlahTotal3 = tempJumlahTotal3+Double.parseDouble(melayu3);
        }
        if(cina3!=null && !cina3.equals("")){
            tempJumlahTotal3 = tempJumlahTotal3+Double.parseDouble(cina3);
        }
        if(india3!=null && !india3.equals("")){
            tempJumlahTotal3 = tempJumlahTotal3+Double.parseDouble(india3);
        }
        if(lainLain3!=null && !lainLain3.equals("")){
            tempJumlahTotal3 = tempJumlahTotal3+Double.parseDouble(lainLain3);
        }

        jumlahTotal3 = tempJumlahTotal3.toString();

    }


    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getBukan() {
        return bukan;
    }

    public void setBukan(String bukan) {
        this.bukan = bukan;
    }

    public String getBumi() {
        return bumi;
    }

    public void setBumi(String bumi) {
        this.bumi = bumi;
    }

    public String getJenisRayuan() {
        return jenisRayuan;
    }

    public void setJenisRayuan(String jenisRayuan) {
        this.jenisRayuan = jenisRayuan;
    }

    public String getJumalah1() {
        return jumalah1;
    }

    public void setJumalah1(String jumalah1) {
        this.jumalah1 = jumalah1;
    }

    public String getKeritasBill() {
        return keritasBill;
    }

    public void setKeritasBill(String keritasBill) {
        this.keritasBill = keritasBill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPelepasan() {
        return pelepasan;
    }

    public void setPelepasan(String pelepasan) {
        this.pelepasan = pelepasan;
    }

    public String getPerakuanPtg() {
        return perakuanPtg;
    }

    public void setPerakuanPtg(String perakuanPtg) {
        this.perakuanPtg = perakuanPtg;
    }

    public String getSimpian() {
        return simpian;
    }

    public void setSimpian(String simpian) {
        this.simpian = simpian;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }


    public String getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(String kandunganK) {
        this.kandunganK = kandunganK;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List[] getSenaraiRingkas() {
        return senaraiRingkas;
    }

    public void setSenaraiRingkas(List[] senaraiRingkas) {
        this.senaraiRingkas = senaraiRingkas;
    }

    public String getBukanBumiputra() {
        return bukanBumiputra;
    }

    public void setBukanBumiputra(String bukanBumiputra) {
        this.bukanBumiputra = bukanBumiputra;
    }

    public String getBumiputra() {
        return bumiputra;
    }

    public void setBumiputra(String bumiputra) {
        this.bumiputra = bumiputra;
    }

    public String getCina1() {
        return cina1;
    }

    public void setCina1(String cina1) {
        this.cina1 = cina1;
    }

    public String getCina2() {
        return cina2;
    }

    public void setCina2(String cina2) {
        this.cina2 = cina2;
    }

    public String getCina3() {
        return cina3;
    }

    public void setCina3(String cina3) {
        this.cina3 = cina3;
    }

    public String getIndia1() {
        return india1;
    }

    public void setIndia1(String india1) {
        this.india1 = india1;
    }

    public String getIndia2() {
        return india2;
    }

    public void setIndia2(String india2) {
        this.india2 = india2;
    }

    public String getIndia3() {
        return india3;
    }

    public void setIndia3(String india3) {
        this.india3 = india3;
    }

    public String getJumlahPengundi() {
        return jumlahPengundi;
    }

    public void setJumlahPengundi(String jumlahPengundi) {
        this.jumlahPengundi = jumlahPengundi;
    }

    public String getLainLain1() {
        return lainLain1;
    }

    public void setLainLain1(String lainLain1) {
        this.lainLain1 = lainLain1;
    }

    public String getLainLain2() {
        return lainLain2;
    }

    public void setLainLain2(String lainLain2) {
        this.lainLain2 = lainLain2;
    }

    public String getLainLain3() {
        return lainLain3;
    }

    public void setLainLain3(String lainLain3) {
        this.lainLain3 = lainLain3;
    }

    public String getMelayu1() {
        return melayu1;
    }

    public void setMelayu1(String melayu1) {
        this.melayu1 = melayu1;
    }

    public String getMelayu2() {
        return melayu2;
    }

    public void setMelayu2(String melayu2) {
        this.melayu2 = melayu2;
    }

    public String getMelayu3() {
        return melayu3;
    }

    public void setMelayu3(String melayu3) {
        this.melayu3 = melayu3;
    }

    public String getSelepasPelepasan() {
        return selepasPelepasan;
    }

    public void setSelepasPelepasan(String selepasPelepasan) {
        this.selepasPelepasan = selepasPelepasan;
    }

    public String getJumlahTotal1() {
        return jumlahTotal1;
    }

    public void setJumlahTotal1(String jumlahTotal1) {
        this.jumlahTotal1 = jumlahTotal1;
    }

    public String getJumlahTotal2() {
        return jumlahTotal2;
    }

    public void setJumlahTotal2(String jumlahTotal2) {
        this.jumlahTotal2 = jumlahTotal2;
    }

    public String getJumlahTotal3() {
        return jumlahTotal3;
    }

    public void setJumlahTotal3(String jumlahTotal3) {
        this.jumlahTotal3 = jumlahTotal3;
    }

    public List[] getSenaraiTingkat() {
        return senaraiTingkat;
    }

    public void setSenaraiTingkat(List[] senaraiTingkat) {
        this.senaraiTingkat = senaraiTingkat;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }
 

}
