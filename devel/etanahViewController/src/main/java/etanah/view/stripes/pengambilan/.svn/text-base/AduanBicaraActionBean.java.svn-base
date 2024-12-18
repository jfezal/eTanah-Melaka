

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
//import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.PengambilanAduanService;


@UrlBinding("/pengambilan/aduan_bicara")
public class AduanBicaraActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    private PerbicaraanService perbicaraanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private List<HakmilikPerbicaraan> hakBicaraList;

    private DokumenService dokumenService;
    private PermohonanPihakService permohonanPihakService;

    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private PermohonanPihak pp;
    private HakmilikPermohonan hp;



    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<String> waktu = new ArrayList<String>();
    private List<String> tarikh = new ArrayList<String>();
    private String amp;
    private String hour;
    private String min;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");

    private String tarikhBicara;
    private String waktuPerbicaraan;
    private String lokasiBicara;



    private String jam;

    private String minit;
    private String ampm;
    private String pagiPtg;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");
    }



    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    
     public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null)
        {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            hakmilikPermohonanList = p.getSenaraiHakmilik();
            if(!hakmilikPermohonanList.isEmpty())
            {
                for(HakmilikPermohonan hp1: hakmilikPermohonanList)
                {
                     hp =  aduanService.findHPByIdMH(hp1.getId());
                        if(hp!=null)
                            {
                                hakBicaraList=perbicaraanService.findBicara(hp.getId());
                                    if( hakBicaraList.size()>0)
                                    {
                                        for(int i=0;i<hakBicaraList.size();i++){
                                            if(hakBicaraList.get(i).getTarikhBicara()!=null){
                                            String StrTarikhBicara = sdf.format(hakBicaraList.get(i).getTarikhBicara());
                                            hour = StrTarikhBicara.substring(11,13);
                                            min = StrTarikhBicara.substring(14,16);
                                            amp = StrTarikhBicara.substring(17,19);
                                            System.out.println("ampm" + amp);
                                            if(amp.contentEquals("AM"))
                                            {
                                                pagiPtg="Pagi";

                                            }else
                                            {
                                                pagiPtg="Petang";
                                            }
                                            waktuPerbicaraan = " " + hour + ":" + min + " " + pagiPtg;
                                            System.out.println("strMasa "+waktuPerbicaraan);
                                            waktu.add(waktuPerbicaraan);
                                            }
                                        }
                                    }
                            }
                }
            }
      }
    }

//    public void rehydrate() {
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
//        if (idPermohonan != null) {
//            Permohonan p = permohonanDAO.findById(idPermohonan);
//            permohonanPihakList = p.getSenaraiPihak();
//            hakmilikPermohonanList = p.getSenaraiHakmilik();
////             pp= aduanService.findPihakByIdMohon(p.getIdPermohonan());
//            hp =  aduanService.findHPByIdMohon(p.getIdPermohonan());
//
//
//
//            if(hp!=null)
//            {
////            hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hp.getId());
//                hakBicaraList=perbicaraanService.findBicara(hp.getId());
//
//            if( hakBicaraList.size()>0)
//            {
////                for(HakmilikPerbicaraan hb:hakBicaraList)
////                {
////                    String StrTarikhBicara = sdf.format(hb.getTarikhBicara());
////
////                    tarikhBicara = StrTarikhBicara.substring(0, 10);
////                    jam = StrTarikhBicara.substring(11,13);
////                    minit = StrTarikhBicara.substring(14,16);
////                    ampm = StrTarikhBicara.substring(18,19);
////                    amp.add(ampm);
////                    if(amp.get(0).contentEquals("00"))
////                    {
////                        pagiPtg="Pagi";
////
////
////                    }else
////                    {
////                        pagiPtg="Petang";
////                    }
////                    waktuPerbicaraan = " " + jam + ":" + minit + " " + pagiPtg;
////                    System.out.println("strMasa "+waktuPerbicaraan);
////                    waktu.add(waktuPerbicaraan);
////
////                }
//
//                for(int i=0;i<hakBicaraList.size();i++){
//                    if(hakBicaraList.get(i).getTarikhBicara()!=null){
//                    String StrTarikhBicara = sdf.format(hakBicaraList.get(i).getTarikhBicara());
//
////                    tarikhBicara = StrTarikhBicara.substring(0, 10);
////                    tarikh.add(tarikhBicara);
//                    hour = StrTarikhBicara.substring(11,13);
//                    min = StrTarikhBicara.substring(14,16);
//                    amp = StrTarikhBicara.substring(17,19);
////                    amp.add(ampm);
//                    System.out.println("ampm" + amp);
//                    if(amp.contentEquals("AM"))
//                    {
//                        pagiPtg="Pagi";
//
//
//                    }else
//                    {
//                        pagiPtg="Petang";
//                    }
//                    waktuPerbicaraan = " " + hour + ":" + min + " " + pagiPtg;
//                    System.out.println("strMasa "+waktuPerbicaraan);
//                    waktu.add(waktuPerbicaraan);
//                    }
//                }
////             try{
////                    lokasiBicara = hakmilikPerbicaraan.getLokasiBicara();
////
//////                   tarikhBicara = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
////                    String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());
////
////                    tarikhBicara = tb.substring(0, 10);
////                    jam = tb.substring(11,13);
////                    minit = tb.substring(14,16);
////                    ampm = tb.substring(17,19);
//////                    tarikhBicara.add(tb1);
//////                    jam.add(tb2);
//////                    minit.add(tb3);
//////                    ampm.add(tb4);
////                }catch(Exception e) { }
//            }
//            }
//
//            }
//
//    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(AduanBicaraActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        //hakmilik = hakmilikDAO.findById(idHakmilik);


        if (idPermohonan != null) {
            Notis notis = new Notis();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(info);
            notis.setPermohonan(permohonan);
            notis.setPihak(null);


            Dokumen dokumen = new Dokumen();
            dokumen.setTajuk(tajuk);
            dokumen.setInfoAudit(info);
            Dokumen dokumen1 = dokumenService.saveOrUpdate(dokumen);

            notis.setDokumenNotis(dokumen1);
            notis.setTarikhNotis(sdf.parse(tarikhNotis));

            KodStatusTerima kodStatusTerima1 = kodStatusTerimaDAO.findById(kodStatusTerima);

            notis.setKodStatusTerima(kodStatusTerima1);
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
            notis.setTarikhTampal(sdf.parse(tarikhTampal));
//            notisPenerimaanService.saveOrUpdateNotis(notis);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");
    }


    public Resolution editDetails(){

        String rowCount = (String) getContext().getRequest().getParameter("rowCount");

        int rowCountval = Integer.parseInt(rowCount);


        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
       Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        KodCawangan cawangan = permohonan.getCawangan();

        HakmilikPermohonan hmp = hakmilikPermohonanList.get(rowCountval);
                HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hmp);

//                     try{
//                        if(lokasiBicara.get(rowCountval) != null){
//                            hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(rowCountval));
//                        }
//
//                        if(tarikhBicara.get(rowCountval) != null){
//                           String StrTarikhBicara = tarikhBicara.get(rowCountval);
//                            StrTarikhBicara = StrTarikhBicara + " " + jam.get(rowCountval) + ":" + minit.get(rowCountval) + " " + ampm.get(rowCountval);
//
//                            hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
//                       }
//                     }catch(Exception e){
//                         e.printStackTrace();
//                     }


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");


    }


    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        permohonanPihakList = permohonan.getSenaraiPihak();
        



                 hp =  aduanService.findHPByIdMohon(permohonan.getIdPermohonan());
                 if(hp!=null)
                 {
//                     for(PermohonanPihak pPihak:permohonanPihakList){
                 if(perbicaraanService.findBicara(hp.getId()) == null) {
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();

                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hp);
                    hakmilikPerbicaraan.setLokasiBicara(lokasiBicara);
                    try {
                             hakmilikPerbicaraan.setLokasiBicara(lokasiBicara);
                             String StrTarikhBicara = tarikhBicara;
                             StrTarikhBicara = StrTarikhBicara + " " + jam + ":" + minit + " " + ampm;
                             hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));

                }catch(Exception e){
                    e.printStackTrace();
                    addSimpleError("Invalid Data");
                }

                }else {
//                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hp.getId());
                    hakmilikPerbicaraan=new HakmilikPerbicaraan();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(ia);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hp);
                    try {
                             hakmilikPerbicaraan.setLokasiBicara(lokasiBicara);
                             String StrTarikhBicara = tarikhBicara;
                             StrTarikhBicara = StrTarikhBicara + " " + jam + ":" + minit + " " + ampm;
                             hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));

                }catch(Exception e){
                    e.printStackTrace();
                    addSimpleError("Invalid Data");
                }
                }
//                     }
                perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
                 }


                

                
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");
    }

    public Resolution deleteTarikh()  {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       hakmilikPerbicaraan = new HakmilikPerbicaraan();
        String idBicara = getContext().getRequest().getParameter("idPerbicaraan");
        hakmilikPerbicaraan=perbicaraanService.getHakmilikPerbicaraanByIdBicara(Long.parseLong(idBicara));
         System.out.println("permohonanRujukanLuarValue"+hakmilikPerbicaraan.getIdPerbicaraan());
        if (hakmilikPerbicaraan != null) {
            perbicaraanService.deleteAllRL(hakmilikPerbicaraan);
        }
        rehydrate();
      return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan_bicara.jsp").addParameter("tab", "true");
    }


    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

   

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

   

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPp() {
        return pp;
    }

    public void setPp(PermohonanPihak pp) {
        this.pp = pp;
    }
    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }
      public String getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(String lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public String getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(String tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public String getWaktuPerbicaraan() {
        return waktuPerbicaraan;
    }

    public void setWaktuPerbicaraan(String waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;
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

    public List<HakmilikPerbicaraan> getHakBicaraList() {
        return hakBicaraList;
    }

    public void setHakBicaraList(List<HakmilikPerbicaraan> hakBicaraList) {
        this.hakBicaraList = hakBicaraList;
    }
    public String getPagiPtg() {
        return pagiPtg;
    }

    public void setPagiPtg(String pagiPtg) {
        this.pagiPtg = pagiPtg;
    }
    
   
    public List<String> getWaktu() {
        return waktu;
    }

    public void setWaktu(List<String> waktu) {
        this.waktu = waktu;
    }

    public List<String> getTarikh() {
        return tarikh;
    }

    public void setTarikh(List<String> tarikh) {
        this.tarikh = tarikh;
    }


}