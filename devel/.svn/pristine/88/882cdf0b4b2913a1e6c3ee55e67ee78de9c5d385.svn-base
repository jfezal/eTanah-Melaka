package etanah.view.stripes.pembangunan;
/**
 *
 * @author NageswaraRao
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
import etanah.model.Pihak;
import etanah.model.PihakPengarah;
import etanah.model.LaporanTanah;
//import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
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
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pembangunan/melaka/pengecualianUkurRezab")
public class PengecualianUkurRezabActionBean extends AbleActionBean{
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
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private Pengguna peng;
    
    private String tujuan;
    private String perihalPermohonan;
    private String perihalTanah;
    private String perihalPemohon;
    private String ulasan;
    private String perakuanPentadbir;
    private String perakuanPentadbir2;
    private String perakuanPentadbir3;
    private String perakuanPentadbir4;
    private String perakuanPentadbir5;
    
    String tarikhDaftar;
    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
    private List<PihakPengarah> listPengarah;
    private Pihak pihak;
    private List<Pemohon> listPemohon;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private boolean btn = true;



    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pembangunan/melaka/pengecualian_Ukur_Rezab.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        return new JSP("pembangunan/melaka/pengecualian_Ukur_Rezab.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/pengecualian_Ukur_Rezab.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

//        HakmilikPermohonan hp = new HakmilikPermohonan();

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

        List<LaporanTanah> listLaporanTanah;
        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

        if (!(listLaporanTanah.isEmpty())) {
            laporanTanah = listLaporanTanah.get(0);
        }

        System.out.println("laporanTanah:----------" + laporanTanah);

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        tujuan = " Tujuan kertas ini adalah untuk mendapatkan pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Melaka mengenai permohonan " +
                "dari Majlis Agama lslam, untuk merezab PT 4402,Mukim Balai Panjang, Daerah Melaka Tengah seluas 1,679.44 m.p untuk tujuan Tapak Sekolah Agama Jaim";

       if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if(kertasP.getTajuk().equals("PENGECUALIAN UKUR REZAB")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 2) {
                            perihalPermohonan = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 3) {
                            perihalTanah = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 4) {
                            perihalPemohon = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 5){
                            ulasan = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 6){
                            perakuanPentadbir = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 7){
                            perakuanPentadbir2 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 8){
                            perakuanPentadbir3 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 9){
                            perakuanPentadbir4 = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 10){
                            perakuanPentadbir5 = kertasK.getKandungan().toLowerCase();
                        }
                    }
                }
            }
        }
       // tarikhPermohonan = sdf.format(permohonan.getInfoAudit().getTarikhMasuk());
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

            if (tujuan == null || tujuan.equals("")) {
                tujuan = "TIADA DATA.";
            }

            if (perihalPermohonan == null || perihalPermohonan.equals("")) {
                perihalPermohonan = "TIADA DATA.";
            }
            if (perihalTanah == null || perihalTanah.equals("")) {
                perihalTanah = "TIADA DATA.";
            }
            if (perihalPemohon == null || perihalPemohon.equals("")) {
                perihalPemohon = "TIADA DATA.";
            }
            if (ulasan == null || ulasan.equals("")) {
                ulasan = "TIADA DATA.";
            }
            if (perakuanPentadbir == null || perakuanPentadbir.equals("")) {
                perakuanPentadbir = "TIADA DATA.";
            }

            listUlasan.add(tujuan);
            listUlasan.add(perihalPermohonan);
            listUlasan.add(perihalTanah);
            listUlasan.add(perihalPemohon);
            listUlasan.add(ulasan);
            listUlasan.add(perakuanPentadbir);

//            System.out.println("perakuanPentadbir2--------"+perakuanPentadbir2);
//            System.out.println("perakuanPentadbir3--------"+perakuanPentadbir3);
//            System.out.println("perakuanPentadbir4--------"+perakuanPentadbir4);
//            System.out.println("perakuanPentadbir5--------"+perakuanPentadbir5);


            if(perakuanPentadbir2 != null){
                listUlasan.add(perakuanPentadbir2);
            }
            if(perakuanPentadbir3 != null){
                listUlasan.add(perakuanPentadbir3);
            }
            if(perakuanPentadbir4 != null){
                listUlasan.add(perakuanPentadbir4);
            }
            if(perakuanPentadbir5 != null){
                listUlasan.add(perakuanPentadbir5);
            }

            
            if (kertasK != null) {

                if (!kertasK.getKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                        if (kertasKandungan.getBil() == 1) {
                           kertasKandungan.setKandungan(tujuan);
                        } else if (kertasKandungan.getBil() == 2) {
                           kertasKandungan.setKandungan(perihalPermohonan);
                        } else if (kertasKandungan.getBil() == 3) {
                           kertasKandungan.setKandungan(perihalTanah);
                        } else if (kertasKandungan.getBil() == 4) {
                           kertasKandungan.setKandungan(perihalPemohon);
                        } else if (kertasKandungan.getBil() == 5) {
                           kertasKandungan.setKandungan(ulasan);
                        } else if (kertasKandungan.getBil() == 6) {
                           kertasKandungan.setKandungan(perakuanPentadbir);
                        } else if (kertasKandungan.getBil() == 7) {
                           kertasKandungan.setKandungan(perakuanPentadbir2);
                        } else if (kertasKandungan.getBil() == 8) {
                           kertasKandungan.setKandungan(perakuanPentadbir3);
                        } else if (kertasKandungan.getBil() == 9) {
                           kertasKandungan.setKandungan(perakuanPentadbir4);
                        } else if (kertasKandungan.getBil() == 10) {
                           kertasKandungan.setKandungan(perakuanPentadbir5);
                        }

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("PENGECUALIAN UKUR REZAB");
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
                    permohonanKertas.setTajuk("PENGECUALIAN UKUR REZAB");
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

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/pengecualian_Ukur_Rezab.jsp").addParameter("tab", "true");
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

    public String getPerakuanPentadbir() {
        return perakuanPentadbir;
    }

    public void setPerakuanPentadbir(String perakuanPentadbir) {
        this.perakuanPentadbir = perakuanPentadbir;
    }

    public String getPerakuanPentadbir2() {
        return perakuanPentadbir2;
    }

    public void setPerakuanPentadbir2(String perakuanPentadbir2) {
        this.perakuanPentadbir2 = perakuanPentadbir2;
    }

    public String getPerakuanPentadbir3() {
        return perakuanPentadbir3;
    }

    public void setPerakuanPentadbir3(String perakuanPentadbir3) {
        this.perakuanPentadbir3 = perakuanPentadbir3;
    }

    public String getPerakuanPentadbir4() {
        return perakuanPentadbir4;
    }

    public void setPerakuanPentadbir4(String perakuanPentadbir4) {
        this.perakuanPentadbir4 = perakuanPentadbir4;
    }

    public String getPerakuanPentadbir5() {
        return perakuanPentadbir5;
    }

    public void setPerakuanPentadbir5(String perakuanPentadbir5) {
        this.perakuanPentadbir5 = perakuanPentadbir5;
    }

    public String getPerihalPemohon() {
        return perihalPemohon;
    }

    public void setPerihalPemohon(String perihalPemohon) {
        this.perihalPemohon = perihalPemohon;
    }

    public String getPerihalPermohonan() {
        return perihalPermohonan;
    }

    public void setPerihalPermohonan(String perihalPermohonan) {
        this.perihalPermohonan = perihalPermohonan;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    

}

