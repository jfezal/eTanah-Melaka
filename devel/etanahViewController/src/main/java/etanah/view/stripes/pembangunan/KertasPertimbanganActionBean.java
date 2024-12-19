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
import etanah.service.PembangunanService;
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
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/kertasPertimbangan")
public class KertasPertimbanganActionBean extends AbleActionBean{
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
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private FasaPermohonan fasaMohon;
    private String stageId;
    private String tujuan;
    private String kanunTanah;
    private String latarBelakang;
    private String huraianPentadbir;
    private String syorPentadbir;
    private String huraianPtg;
    private String syorPtg;
    private String tarikhDaftar;
    private String tarikhMesyuarat;
    private String keputusanPTD;
    private String ulasanPTD;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahBahagian/kertas_pertimbangan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/pecahBahagian/kertas_pertimbangan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

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

        tujuan = "Kertas ini bertujuan untuk mendapat pertimbangan Pentadbir Tanah " + hakmilik.getDaerah().getNama() + ", terhadap permohonan " + permohonan.getKodUrusan().getNama()+ " mengikut Seksyen 135 Kanun Tanah Negara 1965.";

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if(kertasP.getTajuk().equals("KERTAS PERTIMBANGAN PTD")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);
                        tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                        if(kertasK.getBil() == 1){
                            kanunTanah = kertasK.getKandungan();
                        }else if (kertasK.getBil() == 2) {
                            tujuan = kertasK.getKandungan().toLowerCase();
                        } else if(kertasK.getBil() == 3) {
                            latarBelakang = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 4) {
                            huraianPentadbir = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 5) {
                            syorPentadbir = kertasK.getKandungan().toLowerCase();
                        } else if (kertasK.getBil() == 6) {
                            keputusanPTD = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 7) {
                            ulasanPTD = kertasK.getKandungan();
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanKertas permohonanKertas = new PermohonanKertas();

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//
        
        

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

        if (kanunTanah == null || kanunTanah.equals("")) {
            kanunTanah = "TIADA DATA.";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (latarBelakang == null || latarBelakang.equals("")) {
            latarBelakang = "TIADA DATA.";
        }
        if (huraianPentadbir == null || huraianPentadbir.equals("")) {
            huraianPentadbir = "TIADA DATA.";
        }
        if (syorPentadbir == null || syorPentadbir.equals("")) {
            syorPentadbir = "TIADA DATA.";
        }
        if (keputusanPTD == null || keputusanPTD.equals("")) {
            keputusanPTD = "TIADA DATA.";
        }
        if (ulasanPTD == null || ulasanPTD.equals("")) {
            ulasanPTD = "TIADA DATA.";
        }

        listUlasan.add(kanunTanah);
        listUlasan.add(tujuan);
        listUlasan.add(latarBelakang);
        listUlasan.add(huraianPentadbir);
        listUlasan.add(syorPentadbir);
        listUlasan.add(keputusanPTD);
        listUlasan.add(ulasanPTD);

       if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(kanunTanah);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(latarBelakang);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(huraianPentadbir);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(syorPentadbir);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(keputusanPTD);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(ulasanPTD);
                    }
                    
                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }
            }

        } else {


            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);

//                    try {
//                        infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                    } catch (java.text.ParseException ex) {
////                        Logger.getLogger(UlasanTanahLadangActionBean.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                
                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                kk.setKertas(permohonanKertas);
                kk.setBil(i + 1);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(ulasan);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
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

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahBahagian/kertas_pertimbangan.jsp").addParameter("tab", "true");
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

    public String getKanunTanah() {
        return kanunTanah;
    }

    public void setKanunTanah(String kanunTanah) {
        this.kanunTanah = kanunTanah;
    }

    public PermohonanKertasKandungan getKertasK() {
        return kertasK;
    }

    public void setKertasK(PermohonanKertasKandungan kertasK) {
        this.kertasK = kertasK;
    }

    public FasaPermohonan getFasaMohon() {
        return fasaMohon;
    }

    public void setFasaMohon(FasaPermohonan fasaMohon) {
        this.fasaMohon = fasaMohon;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getKeputusanPTD() {
        return keputusanPTD;
    }

    public void setKeputusanPTD(String keputusanPTD) {
        this.keputusanPTD = keputusanPTD;
    }

    public String getUlasanPTD() {
        return ulasanPTD;
    }

    public void setUlasanPTD(String ulasanPTD) {
        this.ulasanPTD = ulasanPTD;
    }
}
