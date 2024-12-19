
//    Document   : UlasanMMKMlkActionBean.java 
//    Created on : Jul 14, 2010, 4:40:36 PM
//    Author     : nurul.izza

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
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
//import org.apache.commons.lang.StringUtils;
//import oracle.bpel.services.workflow.task.model.Task;

@UrlBinding("/pelupusan/ulasan_mmk")
public class UlasanMMKMlkActionBean extends AbleActionBean{
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
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
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
    String pejTanah ;
//    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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
    private String huraianPentadbir2;
    private String huraianPentadbir3;
    private String huraianPentadbir4;
    private String huraianPentadbir5;
    private boolean btn = true;
    private String lokasi;
    //private String kmno;

//    public String getKmno() {
//        return kmno;
//    }
//
//    public void setKmno(String kmno) {
//        this.kmno = kmno;
//    }


    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pelupusan/ulasan_mmk.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pelupusan/ulasan_mmk_mlk.jsp").addParameter("tab", "true");
    }

     public Resolution showForm3() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
         if(kertasK != null){
            btn = false;
        }
        return new JSP("pelupusan/ulasan_mmk_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

       // hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//        hp = permohonan.getSenaraiHakmilik().get(0);
//        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        hakmilikPermohonanList = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null) ;
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

//        List<HakmilikPihakBerkepentingan> listPB;
//        listPB = hakmilik.getSenaraiPihakBerkepentingan();
//
//        if (!(listPB.isEmpty())) {
//            hakmilikPihakBerkepentingan = listPB.get(0);
//        }

//        if (idPermohonan != null) {
//            Permohonan p = permohonanDAO.findById(idPermohonan);
//            hakmilikPermohonanList = p.getSenaraiHakmilik();
//            System.out.println("hakmilikPermohonanList" + hakmilikPermohonanList);
//        }

//        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());
        if(!hakmilikPermohonanList.isEmpty()){

        for (int w = 0; w < hakmilikPermohonanList.size(); w++){
            hp = permohonan.getSenaraiHakmilik().get(w);
                if (hp.getHakmilik() != null) {
                    hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());


                    if (w == 0) {
                        lokasi = hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                    }

                    if (w > 0) {
                        if (w <= ((hakmilikPermohonanList.size() + 2) - 4)) {
                            lokasi = lokasi + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama() + ", ";
                        } else if (w == (hakmilikPermohonanList.size() - 1)) {
                            lokasi = lokasi + " dan " + hakmilik.getLot().getNama() + " " + hakmilik.getNoLot() + ", " + hakmilik.getBandarPekanMukim().getNama() + ", Daerah " + hakmilik.getDaerah().getNama();
                        }
                    }


                } else {
                    HakmilikPermohonan hp1 = hakmilikPermohonanList.get(0);
                    lokasi = hp1.getLokasi() + ", " + hp1.getBandarPekanMukimBaru().getNama();
                }
            }
        }
        String sub = permohonan.getIdPermohonan().substring(0, 2);
        String tempat = "" ;
        if(sub.equals("04")){
            tempat = "Melaka" ;
        }
        else if(sub.equals("05")){
            tempat = "Negeri Sembilan Darul Khusus" ;
        }

        tujuan = "Kertas ini bertujuan untuk mendapat kelulusan Majlis Mesyuarat " + tempat + " terhadap permohonan " + permohonan.getKodUrusan().getNama() + " di " + lokasi + ".";

       if (!(permohonan.getSenaraiKertas().isEmpty())) {

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas kertasP = new PermohonanKertas();
                kertasP = permohonan.getSenaraiKertas().get(i);

                if(kertasP.getTajuk().equals("KERTAS MMK")){
                    for (int j = 0; j < kertasP.getSenaraiKandungan().size(); j++) {

                        kertasK = kertasP.getSenaraiKandungan().get(j);
//                        tarikhMesyuarat = sdf.format(kertasK.getInfoAudit().getTarikhMasuk());

                        if (kertasK.getBil() == 1) {
                            tujuan = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 2) {
                            pembangunanDicadang = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 3) {
                            huraianPentadbir = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 4) {
                            syorPentadbir = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 5){
                            huraianPtg = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 6){
                            syorPtg = kertasK.getKandungan() ;
                        }
                            //else if (kertasK.getBil() == 7){
//                            kmno = kertasK.getKandungan();
//                        }
                            else if (kertasK.getBil() == 7){
                            tarikhMesyuarat = kertasK.getKandungan();
                        } else if (kertasK.getBil() == 8){
                            huraianPentadbir2 = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 9){
                            huraianPentadbir3 = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 10){
                            huraianPentadbir4 = kertasK.getKandungan() ;
                        } else if (kertasK.getBil() == 11){
                            huraianPentadbir5 = kertasK.getKandungan() ;
                        }
                    }
                }
            }
        }
//        tarikhPermohonan =(permohonan.getInfoAudit().getTarikhMasuk());
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

//            BPelService service = new BPelService();
//            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//
//            if (StringUtils.isNotBlank(taskId)) {
//                Task t = null;
//                t = service.getTaskFromBPel(taskId, pengguna);
//                stageId = t.getSystemAttributes().getStage();
//
            //test wani
//            stageId = "Test";



            ArrayList listUlasan = new ArrayList();

//            if (tujuan == null || tujuan.equals("")) {
//                tujuan = "TIADA DATA.";
//            }
            if (pembangunanDicadang == null || pembangunanDicadang.equals("")) {
                pembangunanDicadang = "TIADA DATA.";
            }
            if (huraianPentadbir == null || huraianPentadbir.equals("")) {
                huraianPentadbir = "TIADA DATA.";
            }
            if (syorPentadbir == null || syorPentadbir.equals("")) {
                syorPentadbir = "TIADA DATA.";
            }
            if (huraianPtg == null || huraianPtg.equals("")) {
                huraianPtg = "TIADA DATA.";
            }
            if (syorPtg == null || syorPtg.equals("")) {
                syorPtg = "TIADA DATA.";
            }
//            if (kmno == null || kmno.equals("")){
//                kmno = "TIADA DATA.";
//            }
            if (tarikhMesyuarat == null || tarikhMesyuarat.equals("")){
                tarikhMesyuarat = "TIADA DATA.";
            }

            listUlasan.add(tujuan);
            listUlasan.add(pembangunanDicadang);
            listUlasan.add(huraianPentadbir);
            listUlasan.add(syorPentadbir);
            listUlasan.add(huraianPtg);
            listUlasan.add(syorPtg);
            //listUlasan.add(kmno);
            listUlasan.add(tarikhMesyuarat);

            if(huraianPentadbir2 != null){
                listUlasan.add(huraianPentadbir2);
            }
            if(huraianPentadbir3 != null){
                listUlasan.add(huraianPentadbir3);
            }
            if(huraianPentadbir4 != null){
                listUlasan.add(huraianPentadbir4);
            }
            if(huraianPentadbir5 != null){
                listUlasan.add(huraianPentadbir5);
            }


            if (kertasK != null) {

                if (!kertasK.getKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKandungan = new PermohonanKertasKandungan();
                        kertasKandungan = permohonanKertas.getSenaraiKandungan().get(j);

                        if (kertasKandungan.getBil() == 1) {
                           kertasKandungan.setKandungan(tujuan);
                        } else if (kertasKandungan.getBil() == 2) {
                           kertasKandungan.setKandungan(pembangunanDicadang);
                        } else if (kertasKandungan.getBil() == 3) {
                           kertasKandungan.setKandungan(huraianPentadbir);
                        } else if (kertasKandungan.getBil() == 4) {
                           kertasKandungan.setKandungan(syorPentadbir);
                        } else if (kertasKandungan.getBil() == 5) {
                           kertasKandungan.setKandungan(huraianPtg);
                        } else if (kertasKandungan.getBil() == 6) {
                           kertasKandungan.setKandungan(syorPtg);
                        }
//                           else if (kertasKandungan.getBil() == 7) {
//                           kertasKandungan.setKandungan(kmno);
//                        }
                        else if (kertasKandungan.getBil() == 7) {
                           kertasKandungan.setKandungan(tarikhMesyuarat);
                        } else if (kertasKandungan.getBil() == 8) {
                           kertasKandungan.setKandungan(huraianPentadbir2);
                        } else if (kertasKandungan.getBil() == 9) {
                           kertasKandungan.setKandungan(huraianPentadbir3);
                        } else if (kertasKandungan.getBil() == 10) {
                           kertasKandungan.setKandungan(huraianPentadbir4);
                        } else if (kertasKandungan.getBil() == 11) {
                           kertasKandungan.setKandungan(huraianPentadbir5);
                        }

                        permohonanKertas.setInfoAudit(infoAudit);
                        permohonanKertas.setCawangan(pengguna.getKodCawangan());
                        permohonanKertas.setPermohonan(permohonan);
                        permohonanKertas.setTajuk("KERTAS MMK");
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
                    permohonanKertas.setTajuk("KERTAS MMK");
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
//        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pelupusan/ulasan_mmk_mlk.jsp").addParameter("tab", "true");
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

    public String getTarikhPermohonan(){
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan){
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public String getKedudukanTnh(){
        return kedudukanTnh;
    }

    public void setKedudukanTnh(String kedudukanTnh){
        this.kedudukanTnh = kedudukanTnh;
    }

    public String getKeadaanTnh(){
        return keadaanTnh;
    }

    public void setKeadaanTnh(String keadaanTnh){
        this.keadaanTnh = keadaanTnh;
    }

    public String getJenisTanaman(){
        return jenisTanaman;
    }

    public void setJenisTanaman(String jenisTanaman){
        this.jenisTanaman = jenisTanaman;
    }

    public String getBilBangunan(){
        return bilBangunan;
    }

    public void setBilBangunan(String bilBangunan){
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

    public String getPembangunanDicadang() {
        return pembangunanDicadang;
    }

    public void setPembangunanDicadang(String pembangunanDicadang) {
        this.pembangunanDicadang = pembangunanDicadang;
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

    public String getHuraianPentadbir2() {
        return huraianPentadbir2;
    }

    public void setHuraianPentadbir2(String huraianPentadbir2) {
        this.huraianPentadbir2 = huraianPentadbir2;
    }

    public String getHuraianPentadbir3() {
        return huraianPentadbir3;
    }

    public void setHuraianPentadbir3(String huraianPentadbir3) {
        this.huraianPentadbir3 = huraianPentadbir3;
    }

    public String getHuraianPentadbir4() {
        return huraianPentadbir4;
    }

    public void setHuraianPentadbir4(String huraianPentadbir4) {
        this.huraianPentadbir4 = huraianPentadbir4;
    }

    public String getHuraianPentadbir5() {
        return huraianPentadbir5;
    }

    public void setHuraianPentadbir5(String huraianPentadbir5) {
        this.huraianPentadbir5 = huraianPentadbir5;
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



}
