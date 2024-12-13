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
import etanah.model.InfoAudit;
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


@UrlBinding("/pengambilan/rekodKeputusan")
public class rekodKemasukanMMKActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    PengambilanMMKService service;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodRujukan kodRujukan;
//    private final String tajuk = "KERTAS PERMOHONAN PENGAMBILAN PENARIKAN BALIK NEGERI SEMBILAN";
    private int count=0;
    private List senaraiSyor[] = new ArrayList [ 5 ];
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
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        return new JSP("pengambilan/rekodKeputusanMMK.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2(){
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
         getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
        return new JSP("pengambilan/rekodKeputusanMMK.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<PermohonanRujukanLuar> listRujLuar;

            listRujLuar = permohonanRujLuarDAO.findByEqualCriterias(tname, model, null);

            if (permohonan != null){
                permohonan = permohonanDAO.findById(idPermohonan);

//                if (!(listRujLuar.isEmpty())) {
//                    permohonanRujukanLuar = listRujLuar.get(0);
//
//                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
//                        tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
//                        if (permohonan.getKeputusan() != null) {
//                            keputusan = permohonan.getKeputusan().getKod();
//                            if (permohonan.getKeputusan().getKod().equals("L")||permohonan.getKeputusan().getKod().equals("T") ){
//                                keputusanDisplay = permohonan.getKeputusan().getNama();
//                            }
//                        }
//                    }
//                }
                 if (permohonan.getKeputusan() != null) {
                            keputusan = permohonan.getKeputusan().getKod();
                            if (permohonan.getKeputusan().getKod().equals("L")||permohonan.getKeputusan().getKod().equals("T") ){
                                keputusanDisplay = permohonan.getKeputusan().getNama();
                            }
                        }

                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
                getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
                getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);

            }
            
            PermohonanKertas permohonanKertas = pendudukanSementaraMMKNService.findMMKNByIdPermohonan(idPermohonan);
            if (permohonanKertas != null) {
                List<PermohonanKertasKandungan> kandList = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand = new PermohonanKertasKandungan();
                kandList = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),7);
                if(kandList!=null && !kandList.isEmpty()){
                    maxKertasKand = kandList.get(0);
                    if(maxKertasKand!=null){
                        String subtajuk = maxKertasKand.getSubtajuk();
                        String str = subtajuk.substring(2,3);
                        int tableCount = Integer.parseInt(str);
                        count = tableCount;
                        for(int k=1;k<=tableCount;k++){
                            senaraiSyor[k] = new ArrayList<PermohonanKertasKandungan>();
                            String subtajuk1 = "7."+k;
                            senaraiSyor[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),7,subtajuk1);
                            System.out.println(senaraiSyor[k]);
                        }
                    }
                }//if
            }//if

        }
    }

    public Resolution simpanMesyuarat() {

//        if (tarikhMesyuarat == null || permohonanRujukanLuar == null || keputusan == null) {

//            if (tarikhMesyuarat == null && permohonanRujukanLuar == null && keputusan == null) {
//                addSimpleError("Sila Masukkan Data Berikut.");
//            } else if (permohonanRujukanLuar == null) {
//                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
//            } else if (tarikhMesyuarat == null) {
//                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
//            }  else
        //    }
            if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
            kodKeputusan.setKod(keputusan);
            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
           
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            service.simpanPermohonan(permohonan);
//            try {
//
//                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
//            } catch (ParseException ex) {
//                Logger.getLogger(rekodKemasukanMMKActionBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            permohonanRujukanLuar.setKodRujukan(kod);
//            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
//            permohonanRujukanLuar.setPermohonan(permohonan);
//            permohonanRujukanLuar.setInfoAudit(infoAudit);
//
//            service.simpanRujukanLuar(permohonanRujukanLuar);
//
//            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");

            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
//            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
//            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/rekodKeputusanMMK.jsp").addParameter("tab", "true");
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
}


