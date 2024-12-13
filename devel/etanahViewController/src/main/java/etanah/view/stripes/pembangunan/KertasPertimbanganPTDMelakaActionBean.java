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
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
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
//import etanah.model.FasaPermohonan;
//import etanah.model.KodKeputusan;
import etanah.model.LaporanTanah;
import etanah.service.PembangunanService;
import etanah.view.etanahActionBeanContext;
//import etanah.view.stripes.pembangunan.JabatanTeknikal;
//import java.text.SimpleDateFormat;
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

@UrlBinding("/pembangunan/melaka/kertasPertimbanganPTD")
public class KertasPertimbanganPTDMelakaActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PembangunanService devService;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private Pemohon pemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasK;
    private LaporanTanah laporanTanah;
    private String stageId;
    private String tujuan;
    private String latarbelakang;
    private String latarbelakang1 = "TIADA DATA.";
    private String perihalTanah;
    private String perakuanpenolong;
    private String perakuanpenolong1 = "TIADA DATA.";
    private String perakuanpenolong2 = "TIADA DATA.";
    private String perakuanpenolong3;
    private String keputusanptd;
    private String keputusanptd1;
    private String keputusanptd2 = "TIADA DATA.";
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private PermohonanKertasKandungan kandunganK;
    private String tajuk;
    private String lokasi;
    private String nama;
    private Pengguna pengguna;
    private String pejTanah;
    private boolean btn = true;
    private List<String> result;

    @DefaultHandler
    public Resolution showForm() {
         if (kertasK != null) {
            btn = false;
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        btn = false;
        return new JSP("pembangunan/melaka/kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/melaka/kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
            pemohon = listPemohon.get(0);
        }

        List<LaporanTanah> listLaporanTanah;
        listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

        if (!(listLaporanTanah.isEmpty())) {
            laporanTanah = listLaporanTanah.get(0);
        }

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = p.getSenaraiHakmilik();
        }

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//                System.out.println("-----StageId------"+stageId);
//            }
             //     test wani
            
        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
            hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

            if (w == 0){
                lokasi = hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
            }

            if(w > 0 ){
                if(w <= ((hakmilikPermohonanList.size() + 2) - 4)){
                    lokasi = lokasi + ", " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama() + ", ";
                } else if(w == (hakmilikPermohonanList.size() - 1)){
                    lokasi = lokasi + " dan " + hakmilik.getLot().getNama() +" " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama()+ " seluas " + hakmilik.getLuas() + " " + hakmilik.getKodUnitLuas().getNama();
                }
            }
        }

        for(int j = 0;j < listPemohon.size(); j++){
            Pemohon pm = new Pemohon();
            pm = listPemohon.get(j);

            if(j == 0){
                nama = pm.getPihak().getNama();
            }
            if(j > 0){
                if(j <= ((listPemohon.size() + 2)- 4)){
                    nama = nama + ", " + pm.getPihak().getNama() + ", ";
                } else if(j == (listPemohon.size() - 1)){
                    nama = nama + " dan " + pm.getPihak().getNama();
                }
            }
        }

        result = new ArrayList<String>();
        result.add("Bersetuju");
        result.add("Tidak Bersetuju");

        if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if (kertasP.getTajuk().equals("KERTAS PERTIMBANGAN PTD")) {
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = new PermohonanKertasKandungan();
                        kertasK = kertasP.getSenaraiKandungan().get(j);

                        if (kertasK.getBil() == 1) {
                            tajuk = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 2) {
                            tujuan = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 3) {
                            latarbelakang = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 4) {
                            perihalTanah = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 5) {
                            perakuanpenolong = kertasK.getKandungan();
                            pejTanah = kertasK.getSubtajuk();
                        } else if (kertasK.getBil() == 6) {
                            perakuanpenolong3 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 7) {
                            keputusanptd = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8) {
                            keputusanptd1 = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 9) {
                            latarbelakang1 = kertasK.getKandungan().toUpperCase();
                        } else if (kertasK.getBil() == 10) {
                            perakuanpenolong1 = kertasK.getKandungan().toUpperCase();
                        } else if (kertasK.getBil() == 11) {
                            perakuanpenolong2 = kertasK.getKandungan().toUpperCase();
                        } else if (kertasK.getBil() == 12) {
                            keputusanptd2 = kertasK.getKandungan().toUpperCase();
                        }
                    }
                }
                else{
                    if(pengguna.getKodCawangan().getDaerah().getKod().equals("01")){
                        pejTanah = "Melaka Tengah";
                    }
                    if(pengguna.getKodCawangan().getDaerah().getKod().equals("02")){
                        pejTanah = "Jasin";
                    }
                    if(pengguna.getKodCawangan().getDaerah().getKod().equals("03")){
                        pejTanah = "Alor Gajah";
                    }

                    tajuk = permohonan.getKodUrusan().getNama() + " daripada " + nama + " di atas tanah hakmilik " + lokasi + ".";
                    tujuan = "Tujuan kertas ini ialah untuk mendapat pertimbangan dan keputusan Pentadbir Tanah " + pejTanah + " bagi " + permohonan.getKodUrusan().getNama()
                    + " untuk tanah di " + lokasi + ".";
                    perakuanpenolong3 = "Pentadbir Tanah " + pejTanah + " adalah diminta menimbangkan keputusan samaada bersetuju atau sebaliknya dengan perakuan seperti di perenggan 4.1 di atas.";
                    
                }
            }
        }
    }

    public Resolution simpan() {

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

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//                System.out.println("-----StageId------"+stageId);
//            }
             //     test wani
//            stageId = "Test";

        ArrayList listUlasan = new ArrayList();
        ArrayList listSubtajuk = new ArrayList();

        if(tajuk == null || tajuk.equals("")){
            tajuk = "TIADA DATA.";
        }
        if (tujuan == null || tujuan.equals("")) {
            tujuan = "TIADA DATA.";
        }
        if (latarbelakang == null || latarbelakang.equals("")) {
            latarbelakang = "TIADA DATA.";
        }
        if (perihalTanah == null || perihalTanah.equals("")) {
            perihalTanah = "TIADA DATA.";
        }
        if(keputusanptd == null || keputusanptd.equals("")){
            keputusanptd = "TIADA DATA.";
        }
        if(perakuanpenolong == null || perakuanpenolong.equals("")){
            perakuanpenolong = "TIADA DATA.";
        }
        if(perakuanpenolong3 == null || perakuanpenolong3.equals("")){
            perakuanpenolong3 = "TIADA DATA.";
        }
        if(keputusanptd1 == null || keputusanptd1.equals("")){
            keputusanptd1 = "TIADA DATA.";
        }
        if(latarbelakang1 == null || latarbelakang1.equals("")){
            latarbelakang1 = "TIADA DATA.";
        }
        if(perakuanpenolong1 == null || perakuanpenolong1.equals("")){
            perakuanpenolong1 = "TIADA DATA.";
        }
        if(perakuanpenolong2 == null || perakuanpenolong3.equals("")){
            perakuanpenolong2= "TIADA DATA.";
        }
        if(keputusanptd2 == null || keputusanptd2.equals("")){
            keputusanptd2 = "TIADA DATA.";
        }

        listUlasan.add(tajuk);
        listUlasan.add(tujuan);
        listUlasan.add(latarbelakang);
        listUlasan.add(perihalTanah);
        listUlasan.add(perakuanpenolong);
        listUlasan.add(perakuanpenolong3);
        listUlasan.add(keputusanptd);
        listUlasan.add(keputusanptd1);
        listUlasan.add(latarbelakang1);
        listUlasan.add(perakuanpenolong1);
        listUlasan.add(perakuanpenolong2);
        listUlasan.add(keputusanptd2);

        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add(pejTanah);
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        listSubtajuk.add("");
        
        if (kertasK != null) {

            if (!kertasK.getKandungan().isEmpty()) {

                for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                    PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);


                    if (kertasKandungan.getBil() == 1) {
                        kertasKandungan.setKandungan(tajuk);
                    } else if (kertasKandungan.getBil() == 2) {
                        kertasKandungan.setKandungan(tujuan);
                    } else if (kertasKandungan.getBil() == 3) {
                        kertasKandungan.setKandungan(latarbelakang);
                    } else if (kertasKandungan.getBil() == 4) {
                        kertasKandungan.setKandungan(perihalTanah);
                    } else if (kertasKandungan.getBil() == 5) {
                        kertasKandungan.setKandungan(perakuanpenolong);
                    } else if (kertasKandungan.getBil() == 6) {
                        kertasKandungan.setKandungan(perakuanpenolong3);
                    } else if (kertasKandungan.getBil() == 7) {
                        kertasKandungan.setKandungan(keputusanptd);
                    } else if (kertasKandungan.getBil() == 8) {
                        kertasKandungan.setKandungan(keputusanptd1);
                    } else if (kertasKandungan.getBil() == 9) {
                        kertasKandungan.setKandungan(latarbelakang1);
                    } else if (kertasKandungan.getBil() == 10) {
                        kertasKandungan.setKandungan(perakuanpenolong1);
                    } else if (kertasKandungan.getBil() == 11) {
                        kertasKandungan.setKandungan(perakuanpenolong2);
                    } else if (kertasKandungan.getBil() == 12) {
                        kertasKandungan.setKandungan(keputusanptd2);
                    }

                    permohonanKertas.setInfoAudit(infoAudit);
                    permohonanKertas.setCawangan(pengguna.getKodCawangan());
                    permohonanKertas.setPermohonan(permohonan);
                    permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                    kertasKandungan.setInfoAudit(infoAudit);
                    devService.simpanPermohonanKertas(permohonanKertas);
                    devService.simpanPermohonanKertasKandungan(kertasKandungan);
                }

//                KodKeputusan kodKeputusan = new KodKeputusan();
               // String idAliran1 = "sediaderafmmk";
//                FasaPermohonan fp = devService.findFasaPermohonanByIdAliran(idPermohonan, stageId);
//                kodKeputusan = kodKeputusanDAO.findById(keputusanPTD);
//                fp.setKeputusan(kodKeputusan);
//                fp.setIdPengguna(pengguna.getIdPengguna());
//                fp.setCawangan(pengguna.getKodCawangan());
//                fp.setInfoAudit(infoAudit);
//                devService.simpanFasaPermohonan(fp);
            }

        } else {

            for (int i = 0; i < listUlasan.size(); i++) {

                String ulasan = (String) listUlasan.get(i);
                String subtajuk = (String) listSubtajuk.get(i);
                
                PermohonanKertasKandungan kk = new PermohonanKertasKandungan();
                permohonanKertas.setInfoAudit(infoAudit);
                permohonanKertas.setCawangan(pengguna.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTajuk("KERTAS PERTIMBANGAN PTD");
                kk.setKertas(permohonanKertas);
                kk.setBil(i + 1);
                kk.setInfoAudit(infoAudit);
                kk.setKandungan(ulasan);
                kk.setSubtajuk(subtajuk);
                kk.setCawangan(pengguna.getKodCawangan());
                devService.simpanPermohonanKertas(permohonanKertas);
                devService.simpanPermohonanKertasKandungan(kk);
            }

            // Code to add Result
//            FasaPermohonan fp = new FasaPermohonan();
//            KodKeputusan kodKeputusan = new KodKeputusan();
//            kodKeputusan = kodKeputusanDAO.findById(keputusanPTD);
//            fp.setIdAliran(stageId);
//            fp.setPermohonan(permohonan);
//            fp.setKeputusan(kodKeputusan);
//            fp.setIdPengguna(pengguna.getIdPengguna());
//            fp.setCawangan(pengguna.getKodCawangan());
//            fp.setInfoAudit(infoAudit);
//            devService.simpanFasaPermohonan(fp);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        btn = false;
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/melaka/kertas_Pertimbangan_PTD.jsp").addParameter("tab", "true");
    }

    public boolean isBtn() {
        return btn;
    }

    public void setBtn(boolean btn) {
        this.btn = btn;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanKertasKandungan getKandunganK() {
        return kandunganK;
    }

    public void setKandunganK(PermohonanKertasKandungan kandunganK) {
        this.kandunganK = kandunganK;
    }

    public String getKeputusanptd() {
        return keputusanptd;
    }

    public void setKeputusanptd(String keputusanptd) {
        this.keputusanptd = keputusanptd;
    }

    public String getKeputusanptd1() {
        return keputusanptd1;
    }

    public void setKeputusanptd1(String keputusanptd1) {
        this.keputusanptd1 = keputusanptd1;
    }

    public String getKeputusanptd2() {
        return keputusanptd2;
    }

    public void setKeputusanptd2(String keputusanptd2) {
        this.keputusanptd2 = keputusanptd2;
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

    public String getLatarbelakang() {
        return latarbelakang;
    }

    public void setLatarbelakang(String latarbelakang) {
        this.latarbelakang = latarbelakang;
    }

    public String getLatarbelakang1() {
        return latarbelakang1;
    }

    public void setLatarbelakang1(String latarbelakang1) {
        this.latarbelakang1 = latarbelakang1;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPejTanah() {
        return pejTanah;
    }

    public void setPejTanah(String pejTanah) {
        this.pejTanah = pejTanah;
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

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public String getPerakuanpenolong() {
        return perakuanpenolong;
    }

    public void setPerakuanpenolong(String perakuanpenolong) {
        this.perakuanpenolong = perakuanpenolong;
    }

    public String getPerakuanpenolong1() {
        return perakuanpenolong1;
    }

    public void setPerakuanpenolong1(String perakuanpenolong1) {
        this.perakuanpenolong1 = perakuanpenolong1;
    }

    public String getPerakuanpenolong2() {
        return perakuanpenolong2;
    }

    public void setPerakuanpenolong2(String perakuanpenolong2) {
        this.perakuanpenolong2 = perakuanpenolong2;
    }

    public String getPerakuanpenolong3() {
        return perakuanpenolong3;
    }

    public void setPerakuanpenolong3(String perakuanpenolong3) {
        this.perakuanpenolong3 = perakuanpenolong3;
    }

    public String getPerihalTanah() {
        return perihalTanah;
    }

    public void setPerihalTanah(String perihalTanah) {
        this.perihalTanah = perihalTanah;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

   
    
}



