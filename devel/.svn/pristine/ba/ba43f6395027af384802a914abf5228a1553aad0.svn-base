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
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.ConsentPtdService;
import etanah.service.PelupusanService;
import etanah.service.PembangunanService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Orogenicgroup
 */
@UrlBinding("/pelupusan/ulasanMinitBebas")
public class UlasanMinitBebasActionBean extends AbleActionBean{
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PembangunanService devService;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    PelupusanService pelupusanService;

    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private KodDokumen kd;
    private String penyediaan;
    private String penyediaan2;
    private String penyediaan3;
    private String penyediaan4;
    private String penyediaan5;
    private String penyediaan6;
    private String penyediaan7;
    private String penyediaan8;
    private FasaPermohonan fasaPermohonan;
//  private String pentadbir;
//    private String pentadbir2;
//    private String pentadbir3;
//    private String pentadbir4;
//    private String pentadbir5;
//      private String kelulusan;

    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    private boolean btn = true;

    //testcode
    private HakmilikPermohonan hakmilikPermohonan;
    Logger logger = Logger.getLogger(UlasanMinitBebasActionBean.class);


    @DefaultHandler
    public Resolution showForm() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pelupusan/ulasan_Minit_Bebas.jsp").addParameter("tab", "true");

       // return new JSP("pembangunan/melaka/test_page.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        return new JSP("pelupusan/ulasan_Minit_Bebas.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/ulasan_Minit_Bebas.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

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
        
        String pihakName = "";
        if (pemohon != null && pemohon.getPihak() != null) {
            pihakName = pemohon.getPihak().getNama();
        }

       if (!(permohonan.getSenaraiKertas().isEmpty())) {


                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = devService.findKertasByKod(idPermohonan, "UMINB");

                if(kertasP != null){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                        if (kertasK.getBil() == 1) {
                            penyediaan = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 2) {
                            penyediaan2 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 3) {
                            penyediaan3 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 4) {
                            penyediaan4 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 5){
                            penyediaan5 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 6){
                            penyediaan6 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 7){
                            penyediaan7 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 8){
                            penyediaan8 = kertasK.getKandungan().toLowerCase();
                        }
                    }
                }

        }else{
           stageId="semak_charting";
           fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
           penyediaan=fasaPermohonan.getUlasan();
           logger.info("penyediaan : "+penyediaan);
       }

    }

   public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        kd = kodDokumenDAO.findById("UMINB");

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

            if (penyediaan == null || penyediaan.equals("")) {
                penyediaan = "TIADA DATA.";
            }

            listUlasan.add(penyediaan);

            if(penyediaan2 != null){
                listUlasan.add(penyediaan2);
            }
            if(penyediaan3 != null){
                listUlasan.add(penyediaan3);
            }
            if(penyediaan4 != null){
                listUlasan.add(penyediaan4);
            }
            if(penyediaan5 != null){
                listUlasan.add(penyediaan5);
            }
            if(penyediaan6 != null){
                listUlasan.add(penyediaan6);
            }
            if(penyediaan7 != null){
                listUlasan.add(penyediaan7);
            }
            if(penyediaan8 != null){
                listUlasan.add(penyediaan8);
            }


            if (kertasK != null) {

                if (!kertasK.getKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                         if (kertasKandungan.getBil() == 1) {
                           kertasKandungan.setKandungan(penyediaan);
                        } else if (kertasKandungan.getBil() == 2) {
                           kertasKandungan.setKandungan(penyediaan2);
                        } else if (kertasKandungan.getBil() == 3) {
                           kertasKandungan.setKandungan(penyediaan3);
                        } else if (kertasKandungan.getBil() == 4) {
                           kertasKandungan.setKandungan(penyediaan4);
                        } else if (kertasKandungan.getBil() == 5) {
                           kertasKandungan.setKandungan(penyediaan5);
                        } else if (kertasKandungan.getBil() == 6) {
                           kertasKandungan.setKandungan(penyediaan6);
                        } else if (kertasKandungan.getBil() == 7) {
                           kertasKandungan.setKandungan(penyediaan7);
                        } else if (kertasKandungan.getBil() == 8) {
                           kertasKandungan.setKandungan(penyediaan8);
                        }

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setKodDokumen(kd);
                        permohonanKertas.setTajuk("PENYEDIAAN MINIT BEBAS");
                        kertasKandungan.setInfoAudit(infoAudit);
                        devService.simpanPermohonanKertas(permohonanKertas);
                        devService.simpanPermohonanKertasKandungan(kertasKandungan);
                    }
                }

            } else {


                for (int i = 0; i < listUlasan.size(); i++) {

                    String ulasan = (String) listUlasan.get(i);

                    PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setKodDokumen(kd);
                    permohonanKertas.setTajuk("PENYEDIAAN MINIT BEBAS");
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


        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/ulasan_Minit_Bebas.jsp").addParameter("tab", "true");
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

    public String getPenyediaan() {
        return penyediaan;
    }

    public void setPenyediaan(String penyediaan) {
        this.penyediaan = penyediaan;
    }

    public String getPenyediaan2() {
        return penyediaan2;
    }

    public void setPenyediaan2(String penyediaan2) {
        this.penyediaan2 = penyediaan2;
    }

    public String getPenyediaan3() {
        return penyediaan3;
    }

    public void setPenyediaan3(String penyediaan3) {
        this.penyediaan3 = penyediaan3;
    }

    public String getPenyediaan4() {
        return penyediaan4;
    }

    public void setPenyediaan4(String penyediaan4) {
        this.penyediaan4 = penyediaan4;
    }

    public String getPenyediaan5() {
        return penyediaan5;
    }

    public void setPenyediaan5(String penyediaan5) {
        this.penyediaan5 = penyediaan5;
    }


    // newly added code

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

    public String getPenyediaan6() {
        return penyediaan6;
    }

    public void setPenyediaan6(String penyediaan6) {
        this.penyediaan6 = penyediaan6;
    }

    public String getPenyediaan7() {
        return penyediaan7;
    }

    public void setPenyediaan7(String penyediaan7) {
        this.penyediaan7 = penyediaan7;
    }

    public String getPenyediaan8() {
        return penyediaan8;
    }

    public void setPenyediaan8(String penyediaan8) {
        this.penyediaan8 = penyediaan8;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    
}
