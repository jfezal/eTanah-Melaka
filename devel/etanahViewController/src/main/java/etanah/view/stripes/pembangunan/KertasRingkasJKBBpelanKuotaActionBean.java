/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.FasaPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PembangunanService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author NageswaraRao
 */

@UrlBinding("/pembangunan/melaka/KertasRingkasJKBBpelanKuota")
public class KertasRingkasJKBBpelanKuotaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PembangunanService devService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private PermohonanKertasKandungan kertasK;
    private String stageId;
    private String tajuk;
    private String perihal;
    private String tujuan;
    private String ulasan;
    private String perakuan;
    private String  lokasiTanah;
    private String ulasanTeknical1;
    private String ulasanTeknical2;
    private String ulasanTeknical3;
    private String ulasanTeknical4;
    private String ulasanTeknical5;
    private String ulasanTeknical6;
    private String ulasanTeknical7;
    private String ulasanTeknical8;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanRujukanLuar permohonanRujukanLuar;

    // actual fields
    private String komponenPembangunan;
    private String kuota;
    private String keputusan;

    private List<String> jenis = new ArrayList<String>();
    private List<String> jumlahUnit = new ArrayList<String>();
    private List<String> bumiputraUnit = new ArrayList<String>();
    private List<String> bumiputraPeratus = new ArrayList<String>();
    private List<String> bukanBumputUnit = new ArrayList<String>();
    private List<String> bukanBumptPeratus = new ArrayList<String>();

    

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_Pelan_Kuota.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_Pelan_Kuota.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

       // String stageId1 = getContext().getRequest().getParameter("stageId");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

//        List<LaporanTanah> listLaporanTanah;
//        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);
//
//        if (!(listLaporanTanah.isEmpty())) {
//                laporanTanah = listLaporanTanah.get(0);
//        }

//        List<PermohonanRujukanLuar> listPermohonanRujukanLuar;
//        listPermohonanRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);
//
//        if (!(listPermohonanRujukanLuar.isEmpty())) {
//                permohonanRujukanLuar = listPermohonanRujukanLuar.get(0);
//        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

//        List<HakmilikPihakBerkepentingan> listPB;
//        listPB = hakmilik.getSenaraiPihakBerkepentingan();
//
//        if (!(listPB.isEmpty())) {
//            hakmilikPihakBerkepentingan = listPB.get(0);
//        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

//       tujuan = "Tujuan kertas ini adalah untuk mendapat pertimbangan Pengarah Tanah Dan Galian bagi permohonan daripada Tan Teik Hock @ Tan Boon Hee dan Tan Chun Yew untuk menyerah sebahagian tanah Geran 20911 Lot 696 Bandar Seremban Daerah Seremban mengikut Seksyen 200 Kanun Tanah Nagera.";
//        if (!(permohonan.getSenaraiKertas().isEmpty())) {
//
//            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {
//
//                PermohonanKertas kertasP = new PermohonanKertas();
//                kertasP = permohonan.getSenaraiKertas().get(i);
//
//                if(kertasP.getTajuk().equals("KERTAS PERTIMBANGAN PTG")){
//                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {
//
//                        kertasK = new PermohonanKertasKandungan();
//                        kertasK = kertasP.getSenaraiKandungan().get(j);
//                        tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());
//
//                        if(kertasK.getBil() == 1){
//                            tajuk = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 2) {
//                            perihal = kertasK.getKandungan().toLowerCase();
//                        } else if(kertasK.getBil() == 3){
//                            tujuan = kertasK.getKandungan().toLowerCase();
//                        } else if (kertasK.getBil() == 4){
//                            ulasan = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 5) {
//                            perakuan = kertasK.getKandungan();
//                        }else if (kertasK.getBil() == 6) {
//                            lokasiTanah = kertasK.getKandungan().toLowerCase();
//                        } else if(kertasK.getBil() == 7){
//                            ulasanTeknical1 = kertasK.getKandungan().toLowerCase();
//                        } else if (kertasK.getBil() == 8){
//                            ulasanTeknical2 = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 9) {
//                            ulasanTeknical3 = kertasK.getKandungan();
//                        }else if (kertasK.getBil() == 10) {
//                            ulasanTeknical4 = kertasK.getKandungan().toLowerCase();
//                        } else if(kertasK.getBil() == 11){
//                            ulasanTeknical5 = kertasK.getKandungan().toLowerCase();
//                        } else if (kertasK.getBil() == 12){
//                            ulasanTeknical6 = kertasK.getKandungan();
//                        } else if (kertasK.getBil() == 13) {
//                            ulasanTeknical7 = kertasK.getKandungan();
//                        }else if (kertasK.getBil() == 14) {
//                            ulasanTeknical8 = kertasK.getKandungan();
//                        }
//
//                    }
//                }
//
//            }
//        }
    }



     public Resolution simpan2() {
 //         private List<String> jenis = new ArrayList<String>();
//    private List<String> jumlahUnit = new ArrayList<String>();
//    private List<String> bumiputraUnit = new ArrayList<String>();
//    private List<String> bumiputraPeratus = new ArrayList<String>();
//    private List<String> bukanBumputUnit = new ArrayList<String>();
//    private List<String> bukanBumptPeratus = new ArrayList<String>();

    System.out.println("jenis:"+jenis.size());
    for(int i=0;i<jenis.size();i++)
       System.out.println("jenis value:"+jenis.get(i));
    
    System.out.println("jumlahUnit:"+jumlahUnit.size());
    for(int i=0;i<jumlahUnit.size();i++)
       System.out.println("jumlahUnit value:"+jumlahUnit.get(i));

    System.out.println("bumiputraUnit:"+bumiputraUnit.size());
    for(int i=0;i<bumiputraUnit.size();i++)
       System.out.println("bumiputraUnit value:"+bumiputraUnit.get(i));

    System.out.println("bumiputraPeratus:"+bumiputraPeratus.size());
    for(int i=0;i<bumiputraPeratus.size();i++)
       System.out.println("bumiputraPeratus value:"+bumiputraPeratus.get(i));





      return new JSP("pembangunan/melaka/kertas_Ringkas_JKBB_Pelan_Kuota.jsp").addParameter("tab", "true");
     }


     

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

//        if (tarikhMesyuarat == null) {
//            addSimpleError("Sila masukkan tarikh mesyuarat.");
//        } else {

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

            if (tajuk == null || tajuk.equals("")) {
                tajuk = "TIADA DATA.";
            }
            if (perihal == null || perihal.equals("")) {
                perihal = "TIADA DATA.";
            }
            if (tujuan == null || tujuan.equals("")) {
                tujuan = "TIADA DATA.";
            }
            if (ulasan == null || ulasan.equals("")) {
                ulasan = "TIADA DATA.";
            }
            if (perakuan == null || perakuan.equals("")) {
                perakuan = "TIADA DATA.";
            }
            if (lokasiTanah == null || lokasiTanah.equals("")) {
                lokasiTanah = "TIADA DATA.";
            }
            if (ulasanTeknical1 == null || ulasanTeknical1.equals("")) {
                ulasanTeknical1 = "TIADA DATA.";
            }
            if (ulasanTeknical2 == null || ulasanTeknical2.equals("")) {
                ulasanTeknical2 = "TIADA DATA.";
            }
            if (ulasanTeknical3 == null || ulasanTeknical3.equals("")) {
                ulasanTeknical3 = "TIADA DATA.";
            }
            if (ulasanTeknical4 == null || ulasanTeknical4.equals("")) {
                ulasanTeknical4 = "TIADA DATA.";
            }
            if (ulasanTeknical5 == null || ulasanTeknical5.equals("")) {
                ulasanTeknical5 = "TIADA DATA.";
            }
            if (ulasanTeknical6 == null || ulasanTeknical6.equals("")) {
                ulasanTeknical6 = "TIADA DATA.";
            }
            if (ulasanTeknical7 == null || ulasanTeknical7.equals("")) {
                ulasanTeknical7 = "TIADA DATA.";
            }
            if (ulasanTeknical8 == null || ulasanTeknical8.equals("")) {
                ulasanTeknical8 = "TIADA DATA.";
            }

            listUlasan.add(tajuk);
            listUlasan.add(perihal);
            listUlasan.add(tujuan);
            listUlasan.add(ulasan);
            listUlasan.add(perakuan);
            listUlasan.add(lokasiTanah);
            listUlasan.add(ulasanTeknical1);
            listUlasan.add(ulasanTeknical2);
            listUlasan.add(ulasanTeknical3);
            listUlasan.add(ulasanTeknical4);
            listUlasan.add(ulasanTeknical5);
            listUlasan.add(ulasanTeknical6);
            listUlasan.add(ulasanTeknical7);
            listUlasan.add(ulasanTeknical8);


           if (kertasK != null) {

                if (!kertasK.getKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                        System.out.println("---------------kertasKandungan:"+kertasKandungan.getIdKandungan());

                        if (kertasKandungan.getBil() == 1) {
                            kertasKandungan.setKandungan(tajuk);
                        } else if (kertasKandungan.getBil() == 2) {
                            kertasKandungan.setKandungan(perihal);
                        } else if (kertasKandungan.getBil() == 3) {
                            kertasKandungan.setKandungan(tujuan);
                        } else if (kertasKandungan.getBil() == 4) {
                            kertasKandungan.setKandungan(ulasan);
                        } else if (kertasKandungan.getBil() == 5) {
                            kertasKandungan.setKandungan(perakuan);
                        }else if (kertasKandungan.getBil() == 6) {
                            kertasKandungan.setKandungan(lokasiTanah);
                        }else if (kertasKandungan.getBil() == 7) {
                            kertasKandungan.setKandungan(ulasanTeknical1);
                        }else if (kertasKandungan.getBil() == 8) {
                            kertasKandungan.setKandungan(ulasanTeknical2);
                        }else if (kertasKandungan.getBil() == 9) {
                            kertasKandungan.setKandungan(ulasanTeknical3);
                        }else if (kertasKandungan.getBil() == 10) {
                            kertasKandungan.setKandungan(ulasanTeknical4);
                        }else if (kertasKandungan.getBil() == 11) {
                            kertasKandungan.setKandungan(ulasanTeknical5);
                        }else if (kertasKandungan.getBil() == 12) {
                            kertasKandungan.setKandungan(ulasanTeknical6);
                        }else if (kertasKandungan.getBil() == 13) {
                            kertasKandungan.setKandungan(ulasanTeknical7);
                        }else if (kertasKandungan.getBil() == 14) {
                            kertasKandungan.setKandungan(ulasanTeknical8);
                        }

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
                        kertasKandungan.setInfoAudit(infoAudit);
                        devService.simpanPermohonanKertas(permohonanKertas);
                        devService.simpanPermohonanKertasKandungan(kertasKandungan);
                    }
                }

            } else {


                for (int i = 0; i < listUlasan.size(); i++) {

                    String ulasan = (String) listUlasan.get(i);

                    System.out.println("----------ulasan---------"+ulasan);

                    PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTG");
                    kk.setKertas(permohonanKertas);
                    kk.setBil(i + 1);
                    kk.setInfoAudit(infoAudit);
                    kk.setKandungan(ulasan);
                    kk.setCawangan(pengguna.getKodCawangan());
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kk);
                }
            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
       // }

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

//        List<HakmilikPihakBerkepentingan> listPB;
//        listPB = hakmilik.getSenaraiPihakBerkepentingan();
//
//        if (!(listPB.isEmpty())) {
//            hakmilikPihakBerkepentingan = listPB.get(0);
//        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kertas_Ringkas_JKBB_Pelan_Kuota.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
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

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }


//    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
//        return hakmilikPihakBerkepentingan;
//    }
//
//
//    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
//        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
//    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    

    public String getPerakuan() {
        return perakuan;
    }

    public void setPerakuan(String perakuan) {
        this.perakuan = perakuan;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getUlasanTeknical1() {
        return ulasanTeknical1;
    }

    public void setUlasanTeknical1(String ulasanTeknical1) {
        this.ulasanTeknical1 = ulasanTeknical1;
    }

    public String getUlasanTeknical2() {
        return ulasanTeknical2;
    }

    public void setUlasanTeknical2(String ulasanTeknical2) {
        this.ulasanTeknical2 = ulasanTeknical2;
    }

    public String getUlasanTeknical3() {
        return ulasanTeknical3;
    }

    public void setUlasanTeknical3(String ulasanTeknical3) {
        this.ulasanTeknical3 = ulasanTeknical3;
    }

    public String getUlasanTeknical4() {
        return ulasanTeknical4;
    }

    public void setUlasanTeknical4(String ulasanTeknical4) {
        this.ulasanTeknical4 = ulasanTeknical4;
    }

    public String getUlasanTeknical5() {
        return ulasanTeknical5;
    }

    public void setUlasanTeknical5(String ulasanTeknical5) {
        this.ulasanTeknical5 = ulasanTeknical5;
    }

    public String getUlasanTeknical6() {
        return ulasanTeknical6;
    }

    public void setUlasanTeknical6(String ulasanTeknical6) {
        this.ulasanTeknical6 = ulasanTeknical6;
    }

    public String getUlasanTeknical7() {
        return ulasanTeknical7;
    }

    public void setUlasanTeknical7(String ulasanTeknical7) {
        this.ulasanTeknical7 = ulasanTeknical7;
    }

    public String getUlasanTeknical8() {
        return ulasanTeknical8;
    }

    public void setUlasanTeknical8(String ulasanTeknical8) {
        this.ulasanTeknical8 = ulasanTeknical8;
    }

    public String getLokasiTanah() {
        return lokasiTanah;
    }

    public void setLokasiTanah(String lokasiTanah) {
        this.lokasiTanah = lokasiTanah;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    // new fields

    public List<String> getBukanBumptPeratus() {
        return bukanBumptPeratus;
    }

    public void setBukanBumptPeratus(List<String> bukanBumptPeratus) {
        this.bukanBumptPeratus = bukanBumptPeratus;
    }

    public List<String> getBukanBumputUnit() {
        return bukanBumputUnit;
    }

    public void setBukanBumputUnit(List<String> bukanBumputUnit) {
        this.bukanBumputUnit = bukanBumputUnit;
    }

    public List<String> getBumiputraPeratus() {
        return bumiputraPeratus;
    }

    public void setBumiputraPeratus(List<String> bumiputraPeratus) {
        this.bumiputraPeratus = bumiputraPeratus;
    }

    public List<String> getBumiputraUnit() {
        return bumiputraUnit;
    }

    public void setBumiputraUnit(List<String> bumiputraUnit) {
        this.bumiputraUnit = bumiputraUnit;
    }

    public List<String> getJenis() {
        return jenis;
    }

    public void setJenis(List<String> jenis) {
        this.jenis = jenis;
    }

    public List<String> getJumlahUnit() {
        return jumlahUnit;
    }

    public void setJumlahUnit(List<String> jumlahUnit) {
        this.jumlahUnit = jumlahUnit;
    }
   
    public String getKomponenPembangunan() {
        return komponenPembangunan;
    }

    public void setKomponenPembangunan(String komponenPembangunan) {
        this.komponenPembangunan = komponenPembangunan;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }



}


