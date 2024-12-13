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
import etanah.dao.KodKeputusanDAO;
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


@UrlBinding("/pengambilan/rekodKeputusan_PILDA")
public class rekodKeputusanMMK_PILDA_ActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujLuarDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PengambilanMMKService service;
    @Inject
    PendudukanSementaraMMKNService pendudukanSementaraMMKNService;
    private Permohonan permohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private KodRujukan kodRujukan;
    private final String tajuk = "KERTAS PERMOHONAN IZIN LALU DI BAWAH BEKALAN ELETRIK NEGERI SEMBILAN";
    private int count8=0;
    private List senaraiSyorPengarah[] = new ArrayList [ 5 ];
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
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/rekodKeputusanMMK_PILDA.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2(){
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        getContext().getRequest().setAttribute("form1", Boolean.FALSE);
        getContext().getRequest().setAttribute("bilMesyuarat", Boolean.FALSE);
        getContext().getRequest().setAttribute("tarikhMasa", Boolean.FALSE);
        getContext().getRequest().setAttribute("keputusan", Boolean.FALSE);
        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/rekodKeputusanMMK_PILDA.jsp").addParameter("tab", "true");
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
                
                if (!(listRujLuar.isEmpty())) {
                    permohonanRujukanLuar = listRujLuar.get(0);

                    if (permohonanRujukanLuar.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);
                        if (permohonan.getKeputusan() != null) {
                            keputusan = permohonan.getKeputusan().getKod();
                            if (permohonan.getKeputusan().getKod().equals("L")||permohonan.getKeputusan().getKod().equals("T") ){
//                                keputusan = permohonan.getKeputusan().getNama();
                                keputusanDisplay = permohonan.getKeputusan().getNama();
                            }
                        }
                    }
//                     else if (tarikhMesyuarat == null || permohonanRujukanLuar == null || keputusan == null) {
//
//                        if (tarikhMesyuarat == null && permohonanRujukanLuar == null && keputusan == null) {
//                            addSimpleError("Sila Masukkan Data Berikut.");
//                        } else if (permohonanRujukanLuar == null) {
//                            addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
//                        } else if (tarikhMesyuarat == null) {
//                            addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
//                        } else if (keputusan == null) {
//                            addSimpleError("Sila Masukkan Keputusan.");
//                        }
//                    }
                }

                getContext().getRequest().setAttribute("form1", Boolean.TRUE);
                getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
                getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
                getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
                getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
            }

            PermohonanKertas permohonanKertas = pendudukanSementaraMMKNService.findMMKNByKodIdPermohonan(idPermohonan,tajuk);
            if (permohonanKertas != null) {
                List<PermohonanKertasKandungan> kandList8 = new ArrayList<PermohonanKertasKandungan>();
                PermohonanKertasKandungan maxKertasKand8 = new PermohonanKertasKandungan();
                kandList8 = pendudukanSementaraMMKNService.findByKertasDesc(permohonanKertas.getIdKertas(),6);
                if(kandList8!=null && !kandList8.isEmpty()){
                    maxKertasKand8 = kandList8.get(0);
                    if(maxKertasKand8!=null){
                            String subtajuk = maxKertasKand8.getSubtajuk();
                            String str = subtajuk.substring(2,3);
                            int tableCount = Integer.parseInt(str);
                            count8 = tableCount;
                            for(int k=1;k<=tableCount;k++){
                                senaraiSyorPengarah[k] = new ArrayList<PermohonanKertasKandungan>();
                                String subtajuk1 = "6."+k;
                                senaraiSyorPengarah[k] = pendudukanSementaraMMKNService.findByKertasSubTajuk(permohonanKertas.getIdKertas(),6,subtajuk1);
                            }
                    }
                }//if//if
            }//if
        }
    }

    public Resolution simpanMesyuarat() {

        if (tarikhMesyuarat == null || permohonanRujukanLuar == null || keputusan == null) {

            if (tarikhMesyuarat == null && permohonanRujukanLuar == null && keputusan == null) {
                addSimpleError("Sila Masukkan Data Berikut.");
            } else if (permohonanRujukanLuar == null) {
                addSimpleError("Sila Masukkan Bilangan Mesyuarat.");
            } else if (tarikhMesyuarat == null) {
                addSimpleError("Sila Masukkan Tarikh Mesyuarat.");
            }  else if (keputusan == null) {
                addSimpleError("Sila Masukkan Keputusan.");
            }

        } else {

            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

//            KodKeputusan kodKeputusan = new KodKeputusan();
            KodRujukan kod = new KodRujukan();
            kod.setKod("NF");
//            kodKeputusan.setKod(keputusan);


            KodKeputusan kodKeputusan = kodKeputusanDAO.findById(keputusan);

            permohonan.setKeputusan(kodKeputusan);
            permohonan.setKeputusanOleh(peng);
            service.simpanPermohonan(permohonan);
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());

            try {

                permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhMesyuarat));
            } catch (ParseException ex) {
                Logger.getLogger(rekodKeputusanMMK_PILDA_ActionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            permohonanRujukanLuar.setKodRujukan(kod);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setInfoAudit(infoAudit);

            service.simpanRujukanLuar(permohonanRujukanLuar);

            tarikhMesyuarat = sdf.format(permohonanRujukanLuar.getTarikhSidang()).substring(0, 10);

            addSimpleMessage("Maklumat telah berjaya disimpan.");
            
            getContext().getRequest().setAttribute("form1", Boolean.TRUE);
            getContext().getRequest().setAttribute("bilMesyuarat", Boolean.TRUE);
            getContext().getRequest().setAttribute("tarikhMasa", Boolean.TRUE);
            getContext().getRequest().setAttribute("keputusan", Boolean.TRUE);
            getContext().getRequest().setAttribute("simpanMesyuarat", Boolean.TRUE);
        }

        return new JSP("pengambilan/negerisembilan/izinlaluaktabekalanletrik/rekodKeputusanMMK_PILDA.jsp").addParameter("tab", "true");
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

    public int getCount8() {
        return count8;
    }

    public void setCount8(int count8) {
        this.count8 = count8;
    }

    public PendudukanSementaraMMKNService getPendudukanSementaraMMKNService() {
        return pendudukanSementaraMMKNService;
    }

    public void setPendudukanSementaraMMKNService(PendudukanSementaraMMKNService pendudukanSementaraMMKNService) {
        this.pendudukanSementaraMMKNService = pendudukanSementaraMMKNService;
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


    public String[] getStr() {
        return str;
    }

    public void setStr(String[] str) {
        this.str = str;
    }
}


