/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author nordiyana
 */

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.LaporanTanah;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PengambilanMMKService;
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
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;


@UrlBinding("/pengambilan/ulasan_mmk_sek4")
public class UlasanMMKSek4ActionBean extends AbleActionBean {

     @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PengambilanMMKService PengambilanService;
    private Permohonan permohonan;
    private HakmilikPermohonan hakp;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private String stageId;
    private LaporanTanah laporanTanah;
    private PermohonanKertasKandungan kandunganK;
    private List<PermohonanKertasKandungan> senaraiKertasKandungan;

    public List<PermohonanKertasKandungan> getSenaraiKertasKandungan() {
        return senaraiKertasKandungan;
    }

    public void setSenaraiKertasKandungan(List<PermohonanKertasKandungan> senaraiKertasKandungan) {
        this.senaraiKertasKandungan = senaraiKertasKandungan;
    }


    String latarBelakang;
    String latarBelakang2;

    public String getLatarBelakang2() {
        return latarBelakang2;
    }

    public void setLatarBelakang2(String latarBelakang2) {
        this.latarBelakang2 = latarBelakang2;
    }

    public String getLatarBelakang3() {
        return latarBelakang3;
    }

    public void setLatarBelakang3(String latarBelakang3) {
        this.latarBelakang3 = latarBelakang3;
    }
    String latarBelakang3;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private List<HakmilikPermohonan> hakmilikPermohonanList;

    //mine
    private String tujuan;
    private int bil=0;
    private List<PermohonanKertas> senaraiKertas;
    private String kertasbil;
    private String ulasan1;
    private String ulasan2;
    private String ulasan3;
    private String ulasan4;
    private String ulasan5;
    private String subtajuk1;
    private String subtajuk2;
    private String subtajuk3;
    private String subtajuk4;
    private String subtajuk5;






    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/ulasan_mmk_pengambilan.jsp").addParameter("tab", "true");
    }
    

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/ulasan_mmk_pengambilan_sek4.jsp").addParameter("tab", "true");
    }
     public Resolution showForm3() {
        getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/ulasan_mmk_pengambilan_sek4.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        System.out.println("listHakmilik:" + hakmilikPermohonanList);

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        tujuan = "Tujuan kertas ini dikemukakan adalah untuk mendapatkan pertimbangan Yang Amat Berhormat Menteri Besar Negeri Sembilan mengenai permohonan pengambilan mengikut Seksyen 4 Akta Pengambilan Tanah 1960 daripada Jabatan Pembangunan Daerah bagi tujuan "+permohonan.getSebab()+ " di kawasan "+hakmilik.getBandarPekanMukim().getNama()+", " +hakmilik.getDaerah().getNama()+" seluas " +hakmilik.getLuas()+ " hektar keseluruhan/sebahagian tanah lot " + hakmilik.getLot().getKod() + ", di bawah perwakilan kuasa seperti yang diisytiharkan dalam Warta Kerajaan No.148 bertarikh 26.03.1987.Kawasan yang dimaksudkan seperti yang bertanda merah dalam pelan Lampiran A ";

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

//        + pemohon.getPihak().getNama() +
//+ hakp.getHakmilik().getLot() +"seluas "+ hakp.getHakmilik().getLuas()+"mp. di "+hakp.getHakmilik().getBandarPekanMukim().getNama()+",Daerah "+hakp.getHakmilik().getDaerah().getNama()+",Melaka untuk tujuan" + permohonan.getSebab()+ " di bawah Seksyen 3(1)(a),Akta Pengambilan Tanah 1960.";


        if (!(hakmilik.getSenaraiPihakBerkepentingan().isEmpty())) {
            hakmilikPihakBerkepentingan = hakmilik.getSenaraiPihakBerkepentingan().get(0);

        }

          if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);
            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = listLaporanTanah.get(0);
            }
          }

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

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());




        //mine
        if (!(permohonan.getSenaraiKertas().isEmpty())) {


            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);
                senaraiKertasKandungan = PengambilanService.findByKertas(kertasP.getIdKertas());
                if(kertasP.getTajuk().equals("KERTAS PERMOHONAN PENGAMBILAN TANAH")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kandunganK = kertasP.getSenaraiKandungan().get(j);
                        tarikhMesyuarat = sdf.format(kandunganK.getInfoAudit().getTarikhMasuk());

                        if (kandunganK.getBil()== 1){
                            kertasbil = kandunganK.getKandungan();
                        } else if (kandunganK.getBil() == 2) {
                            tujuan = kandunganK.getKandungan().toLowerCase();
                        } else if (kandunganK.getBil() == 3) {
                            latarBelakang = kandunganK.getKandungan().toLowerCase();
                        }else if (kandunganK.getBil() == 4) {
                            latarBelakang2 = kandunganK.getKandungan().toLowerCase();
                        }
                        else if (kandunganK.getBil() == 5) {
                            latarBelakang3 = kandunganK.getKandungan().toLowerCase();
                        }
//                        else if (kandunganK.getBil() == 6) {
//                            ulasan1 = kandunganK.getKandungan().toLowerCase();
//                            subtajuk1 = kandunganK.getSubtajuk();
//                        } else if (kandunganK.getBil() == 7) {
//                            ulasan2 = kandunganK.getKandungan().toLowerCase();
//                            subtajuk2 = kandunganK.getSubtajuk();
//                        } else if (kandunganK.getBil() == 8) {
//                            ulasan3 = kandunganK.getKandungan().toLowerCase();
//                            subtajuk3 = kandunganK.getSubtajuk();
//                        } else if (kandunganK.getBil() == 8) {
//                            ulasan4 = kandunganK.getKandungan().toLowerCase();
//                            subtajuk4 = kandunganK.getSubtajuk();
//                        } else if (kandunganK.getBil() == 10){
//                            ulasan5 = kandunganK.getKandungan().toLowerCase();
//                            subtajuk5 = kandunganK.getSubtajuk();
//                        } 
                        else if (kandunganK.getBil() == 6){
                            syorPentadbir = kandunganK.getKandungan().toLowerCase();
                        }else if (kandunganK.getBil() == 7) {
                            huraianPtg = kandunganK.getKandungan().toLowerCase();
                        } else if (kandunganK.getBil() == 8) {
                            syorPtg = kandunganK.getKandungan().toLowerCase();
                        }
                    }
                }
            }
        }
        //end mine//
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (tarikhMesyuarat == null) {
            addSimpleError("Sila masukkan tarikh mesyuarat.");
        } else {

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanKertas permohonanKertas = new PermohonanKertas();

            if (kandunganK != null) {
                permohonanKertas = permohonanKertasDAO.findById(kandunganK.getKertas().getIdKertas());
                infoAudit = permohonanKertas.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());

            }

            ArrayList listUlasan = new ArrayList();
//            ArrayList listSubtajuk = new ArrayList();

            if (kertasbil == null || kertasbil.equals("")){
                kertasbil = "TIADA DATA";
            }
            if (tujuan == null || tujuan.equals("")) {
                tujuan = "TIADA DATA.";
            }
            if (latarBelakang == null || latarBelakang.equals("")) {
                latarBelakang = "TIADA DATA.";
            }
             if (latarBelakang2 == null || latarBelakang2.equals("")) {
                latarBelakang2 = "TIADA DATA.";
            }
             if (latarBelakang3== null || latarBelakang3.equals("")) {
                latarBelakang3 = "TIADA DATA.";
            }
            if ( syorPentadbir== null || syorPentadbir.equals("")) {
                syorPentadbir = "TIADA DATA.";
            }
            if (huraianPtg == null || huraianPtg.equals("")) {
                huraianPtg = "TIADA DATA.";
            }
            if (syorPtg == null || syorPtg.equals("")) {
                syorPtg = "TIADA DATA.";
            }

             int kira = Integer.parseInt(getContext().getRequest().getParameter("rowCount"));


            if (kandunganK != null) {

                if (!kandunganK.getKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKand = new PermohonanKertasKandungan();
                        kertasKand = permohonanKertas.getSenaraiKandungan().get(j);

                        if (kertasKand.getBil()== 1){
                            kertasKand.setKandungan(kertasbil);
                        } else if (kertasKand.getBil() == 2){
                            kertasKand.setKandungan(tujuan);
                        } else if (kertasKand.getBil() == 3) {
                            kertasKand.setKandungan(latarBelakang);
                          for (int i = 1; i <= kira; i++) 
                          {
                              latarBelakang = getContext().getRequest().getParameter("latarBelakang" + i);
                              kertasKand.setKandungan(latarBelakang);
                              kertasKand.setSubtajuk("3." + i);
                          }

                        }
                        else if (kertasKand.getBil() == 4) {
                            kertasKand.setKandungan(latarBelakang2);
                        }
                        else if (kertasKand.getBil() == 5) {
                            kertasKand.setKandungan(latarBelakang3);
                        }
//                        else if (kertasKand.getBil() == 6) {
//                            kertasKand.setKandungan(ulasan1);
//                            kertasKand.setSubtajuk(subtajuk1);
//                        } else if (kertasKand.getBil() == 7) {
//                            kertasKand.setKandungan(ulasan2);
//                            kertasKand.setSubtajuk(subtajuk2);
//                        } else if (kertasKand.getBil() == 8) {
//                            kertasKand.setKandungan(ulasan3);
//                            kertasKand.setSubtajuk(subtajuk3);
//                        } else if (kertasKand.getBil() == 9) {
//                            kertasKand.setKandungan(ulasan4);
//                            kertasKand.setSubtajuk(subtajuk4);
//                        } else if (kertasKand.getBil() == 10) {
//                            kertasKand.setKandungan(ulasan5);
//                            kertasKand.setSubtajuk(subtajuk5);
//                        }
                        else if (kertasKand.getBil() == 6) {
                            kertasKand.setKandungan(syorPentadbir);
                        } else if (kertasKand.getBil() == 7) {
                            kertasKand.setKandungan(huraianPtg);
                        }else if (kertasKand.getBil() == 8) {
                            kertasKand.setKandungan(syorPtg);
                        }

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("KERTAS PERMOHONAN PENGAMBILAN TANAH");
                        kertasKand.setInfoAudit(infoAudit);
                        PengambilanService.simpanPermohonanKertas(permohonanKertas);
                        PengambilanService.simpanPermohonanKertasKandungan(kertasKand);
                    }
                }

            } else {

                for (int i = 0; i < listUlasan.size(); i++) {

                    String ulasan = (String) listUlasan.get(i);
//                    String subtajuk = (String) listSubtajuk.get(i);

                        PermohonanKertasKandungan kandunganKertas = new PermohonanKertasKandungan();

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("KERTAS PERMOHONAN PENGAMBILAN TANAH");

                        kandunganKertas.setKertas(permohonanKertas);
                        kandunganKertas.setBil(i + 1);
                        kandunganKertas.setInfoAudit(infoAudit);
                        kandunganKertas.setKandungan(ulasan);
//                        kandunganKertas.setSubtajuk(subtajuk);
                        kandunganKertas.setCawangan(pengguna.getKodCawangan());
                        PengambilanService.simpanPermohonanKertas(permohonanKertas);
                        PengambilanService.simpanPermohonanKertasKandungan(kandunganKertas);
                }
            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }

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

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/ulasan_mmk_pengambilan_sek4.jsp").addParameter("tab", "true");
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

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
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


    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }
     public int getBil() {
        return bil;
    }

    public void setBil(int bil) {
        this.bil = bil;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    public List<PermohonanKertas> getSenaraiKertas() {
        return senaraiKertas;
    }

    public void setSenaraiKertas(List<PermohonanKertas> senaraiKertas) {
        this.senaraiKertas = senaraiKertas;
    }


    public HakmilikPermohonan getHakp() {
        return hakp;
    }

    public void setHakp(HakmilikPermohonan hakp) {
        this.hakp = hakp;
    }
     public String getKertasbil() {
        return kertasbil;
    }

    public void setKertasbil(String kertasbil) {
        this.kertasbil = kertasbil;
    }

    public String getSubtajuk1() {
        return subtajuk1;
    }

    public void setSubtajuk1(String subtajuk1) {
        this.subtajuk1 = subtajuk1;
    }

    public String getSubtajuk2() {
        return subtajuk2;
    }

    public void setSubtajuk2(String subtajuk2) {
        this.subtajuk2 = subtajuk2;
    }

    public String getSubtajuk3() {
        return subtajuk3;
    }

    public void setSubtajuk3(String subtajuk3) {
        this.subtajuk3 = subtajuk3;
    }

    public String getSubtajuk4() {
        return subtajuk4;
    }

    public void setSubtajuk4(String subtajuk4) {
        this.subtajuk4 = subtajuk4;
    }

    public String getSubtajuk5() {
        return subtajuk5;
    }

    public void setSubtajuk5(String subtajuk5) {
        this.subtajuk5 = subtajuk5;
    }

    public String getUlasan1() {
        return ulasan1;
    }

    public void setUlasan1(String ulasan1) {
        this.ulasan1 = ulasan1;
    }

    public String getUlasan2() {
        return ulasan2;
    }

    public void setUlasan2(String ulasan2) {
        this.ulasan2 = ulasan2;
    }

    public String getUlasan3() {
        return ulasan3;
    }

    public void setUlasan3(String ulasan3) {
        this.ulasan3 = ulasan3;
    }

    public String getUlasan4() {
        return ulasan4;
    }

    public void setUlasan4(String ulasan4) {
        this.ulasan4 = ulasan4;
    }

    public String getUlasan5() {
        return ulasan5;
    }

    public void setUlasan5(String ulasan5) {
        this.ulasan5 = ulasan5;
    }
      public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

}


