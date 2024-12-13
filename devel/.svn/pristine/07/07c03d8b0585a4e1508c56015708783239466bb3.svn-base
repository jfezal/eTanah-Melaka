/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

/**
 *
 * @author massita
 */


import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusan;
import etanah.model.KodRujukan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanMMKService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;


@UrlBinding("/pengambilan/MMKptptgpengambilan")
public class KeputusanPtptgpengambilanMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PengambilanMMKService service;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodRujukan kodRujukan;
    private KodCawangan cawangan;
    private final String tajuk = "KERTAS PERMOHONAN PENDUDUKAN SEMENTARA NEGERI SEMBILAN";
    private int count=0;
    private int count8=0;
    private List senaraiSyor[] = new ArrayList [ 5 ];
    private List senaraiSyorPengarah[] = new ArrayList [ 5 ];
    private PermohonanKertasKandungan syorPengarahObj;
    private String syorPengarah;
    String str[] ={"","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};


    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String keputusan;
    String jam;
    String minit;
    String saat;
    String ampm;
    String ampmDisplay;
    String keputusanDisplay;

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanPtptgpengambilanMMK.jsp").addParameter("tab", "true");
    }

     public Resolution hideSimpanMesyuarat() {
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.FALSE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanPtptgpengambilanMMK.jsp").addParameter("tab", "true");
     }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getKeputusan() != null) {
                        keputusan = permohonan.getKeputusan().getKod();
                        System.out.println("------keputusan---"+keputusan);
                        keputusanDisplay = permohonan.getKeputusan().getNama();
            }

//            PermohonanKertas permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);
            PermohonanKertas permohonanKertas = pendudukanSementaraMMKNService.getPermohonanKertasByIdPermohonan(idPermohonan);
            if (permohonanKertas != null) {
//                List<PermohonanKertasKandungan> kandList8 = new ArrayList<PermohonanKertasKandungan>();
//                PermohonanKertasKandungan maxKertasKand8 = new PermohonanKertasKandungan();
//                kandList8 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),6);
//                if(kandList8!=null && !kandList8.isEmpty()){
//                    maxKertasKand8 = kandList8.get(0);
//                    if(maxKertasKand8!=null){
//                            String subtajuk = maxKertasKand8.getSubtajuk();
//                            String str = subtajuk.substring(2,3);
//                            int tableCount = Integer.parseInt(str);
//                            count8 = tableCount;
//                            for(int k=1;k<=tableCount;k++){
//                                senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
//                                String subtajuk1 = "6."+k;
//                                senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),6,subtajuk1);
//                            }
//                    }
//                }//if
                syorPengarahObj = pendudukanSementaraMMKNService.findbil1ByIdKertas(permohonanKertas.getIdKertas(), 7);
                if (syorPengarahObj != null) {
                    syorPengarah = syorPengarahObj.getKandungan().toLowerCase();
                }
            }//if
        }
    }

    public Resolution simpanMesyuarat() {

//        if (tarikhMesyuarat == null || permohonanRujukanLuar == null || keputusan == null)
        if (keputusan == null)
        {

//            if (tarikhMesyuarat == null && permohonanRujukanLuar == null && keputusan == null)
//
//            {
//                addSimpleError("Sila Masukkan Data Berikut.");
//            }
               if (permohonanRujukanLuar == null) {
////                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
////            } else if (tarikhMesyuarat == null) {
////                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
//            }
                if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }
    }else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            cawangan = permohonan.getCawangan();
//            permohonan.setCawangan(kodCawanganDAO.findById(cawangan.getKod()));
            service.simpanPermohonan(permohonan);
//            InfoAudit infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(peng);
//            infoAudit.setTarikhMasuk(new java.util.Date());

//            try {
//
//                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
//            } catch (ParseException ex) {
//                Logger.getLogger(rekodKemasukanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            if (permohonan !=null){
//                if (permohonanRujukanLuar!=null){
//                permohonanRujukanLuar.setKodRujukan(kod);
//                permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setInfoAudit(infoAudit);
//                service.simpanRujukanLuar(permohonanRujukanLuar);
//                }
//            }
//            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");


//        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
    }
        return new JSP("pengambilan/negerisembilan/pendudukansementara/keputusanPtptgpengambilanMMK.jsp").addParameter("tab", "true");
    }


    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getAmpmDisplay() {
        return ampmDisplay;
    }

    public void setAmpmDisplay(String ampmDisplay) {
        this.ampmDisplay = ampmDisplay;
    }

    public String getKeputusanDisplay() {
        return keputusanDisplay;
    }

    public void setKeputusanDisplay(String keputusanDisplay) {
        this.keputusanDisplay = keputusanDisplay;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List[] getSenaraiSyor() {
        return senaraiSyor;
    }

    public void setSenaraiSyor(List[] senaraiSyor) {
        this.senaraiSyor = senaraiSyor;
    }

    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
    }

    public int getCount8() {
        return count8;
    }

    public void setCount8(int count8) {
        this.count8 = count8;
    }

    public List[] getSenaraiSyorPengarah() {
        return senaraiSyorPengarah;
    }

    public void setSenaraiSyorPengarah(List[] senaraiSyorPengarah) {
        this.senaraiSyorPengarah = senaraiSyorPengarah;
    }

    public PengambilanMMKService getService() {
        return service;
    }

    public void setService(PengambilanMMKService service) {
        this.service = service;
    }

    public String getSyorPengarah() {
        return syorPengarah;
    }

    public void setSyorPengarah(String syorPengarah) {
        this.syorPengarah = syorPengarah;
    }

    public PermohonanKertasKandungan getSyorPengarahObj() {
        return syorPengarahObj;
    }

    public void setSyorPengarahObj(PermohonanKertasKandungan syorPengarahObj) {
        this.syorPengarahObj = syorPengarahObj;
    }
}
