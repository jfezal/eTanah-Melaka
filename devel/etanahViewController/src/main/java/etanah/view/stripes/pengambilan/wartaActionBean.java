/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Permohonan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.KodRujukan;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;
import java.util.Arrays;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/terima_warta1")
public class wartaActionBean extends AbleActionBean {


    private static Logger logger = Logger.getLogger(wartaActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
    private KodUrusan kodUrusan;
    private KodCawangan cawangan;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPengambilan> permohonanPengambilanList;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;

    @Inject
    PengambilanService service;
    private String tarikhWarta;
    private String tarikhDisampai;
    private KodRujukan kodRujukan;
    private String noWarta;
    private String idPermohonan;
    private String idPengambilan;
    private String ulasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    int arrValue[]={2,3,1,1,3};


    public int[] getArrValue() {
        return arrValue;
    }

    public void setArrValue(int[] arrValue) {
        this.arrValue = arrValue;
    }



     @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         getContext().getRequest().setAttribute("simpanWarta", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("simpanWarta", Boolean.FALSE);
        return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
    }

     @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonanRujukanLuar=service.findByIdMohonRujLuar(idPermohonan);
//        permohonanPengambilan = service.findByIdMohon(permohonan.getIdPermohonan());
        permohonanPengambilanList=service.findByIDMohonPengambilan(idPermohonan);
        permohonanRujukanLuarList=service.findByIDMohonWarta(idPermohonan);
        int pawn;
        int count = 0;
        int n=0;
        for(int i=count;i<arrValue.length;i++)
        {
            count=count+arrValue[count];
            pawn=arrValue[count];
            System.out.println("array value"+pawn);
            System.out.println("array n array value"+count);
            if(count>arrValue.length){
            System.out.println("jump out");
            }else{
            System.out.println("pawn"+arrValue[count]);
            }

        }

//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            permohonanPengambilan = service.findByIdMohon(idPermohonan);
//            logger.debug(idPermohonan + "idPermohonan");
//
//             if(permohonanPengambilan != null)
//                    {
//                    if(permohonanPengambilan.getTarikhWarta() != null)
//                    tarikhWarta = sdf.format(permohonanPengambilan.getTarikhWarta()).substring(0, 10);
//                    noWarta = permohonanPengambilan.getNoWarta();
//                    }
//            if(permohonanRujukanLuar != null)
//                    {
//                    if(permohonanRujukanLuar.getTarikhDisampai()!= null)
//                    tarikhDisampai = sdf.format(permohonanRujukanLuar.getTarikhDisampai()).substring(0, 10);
//                    ulasan=permohonanRujukanLuar.getUlasan();
//                    }

//        }

    }
     public Resolution simpan() throws ParseException
     {
         Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String nowarta2 = getContext().getRequest().getParameter("noWarta");
//        permohonanPengambilan = permohonanPengambilanDAO.findById(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        
        Permohonan permohonanSblm = permohonan.getPermohonanSebelum();

        permohonanPengambilanList = service.findByIDMohonPengambilan(idPermohonan);
               
                if (permohonanPengambilanList == null)
                {
                    permohonanPengambilan = new PermohonanPengambilan();
//                    permohonanPengambilan = service.findNoWartaByIdPengambilan(Long.parseLong(idPengambilan));
                     hakmilikPermohonanList = permohonan.getSenaraiHakmilik();

                    KodBandarPekanMukim pp = new KodBandarPekanMukim();
                    pp.setKod(12);
                    KodUOM m = new KodUOM();
                    m.setKod("M");
                    if (StringUtils.isNotBlank(tarikhWarta))
                    {
                        tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                        permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));
                    }

                    permohonanPengambilan.setNoWarta(noWarta);
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPengambilan.setInfoAudit(info);
                    permohonanPengambilan.setPermohonan(permohonan);
                    permohonanPengambilan.setCawangan(peng.getKodCawangan());
                    permohonanPengambilan.setBandarPekanMukim(pp);
                    permohonanPengambilan.setKodUnitLuas(m);
                    permohonanPengambilan.setLuasTerlibat(new BigDecimal(0.00));
                    permohonanPengambilan.setNoWarta(noWarta);
                    permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));

                    if(permohonanSblm != null) {
                        permohonanPengambilan.setPermohonanTerdahulu(permohonanSblm.getIdPermohonan());
                    }

                   if(permohonanRujukanLuar!=null)
                   {
                       permohonanRujukanLuar=new PermohonanRujukanLuar();
                       permohonanRujukanLuar.setPermohonan(permohonan);
                       permohonanPengambilan.setInfoAudit(info);
                       permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                       permohonanRujukanLuar.setInfoAudit(info);
                       KodRujukan kr=new KodRujukan();
                       kr.setKod("NF");
                       permohonanRujukanLuar.setKodRujukan(kr);
                       permohonanRujukanLuar.setUlasan(ulasan);
                       permohonanRujukanLuar.setCatatan("Warta Pembetulan");
                       if (StringUtils.isNotBlank(tarikhDisampai))
                    {
                        tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                        permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
                    }
                   }else
                   {
                       permohonanRujukanLuar.setPermohonan(permohonan);
                       permohonanPengambilan.setInfoAudit(info);
                       permohonanRujukanLuar.setInfoAudit(info);
                       permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                       KodRujukan kr=new KodRujukan();
                       kr.setKod("NF");
                       permohonanRujukanLuar.setKodRujukan(kr);
                       permohonanRujukanLuar.setUlasan(ulasan);
                       permohonanRujukanLuar.setCatatan("Warta Asal");
                   if (StringUtils.isNotBlank(tarikhDisampai))
                    {
                        tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                        permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
                    }


                   }
                   addSimpleMessage("Kemasukan Data Berjaya.");
                    }
                    else
                    {
                    permohonanPengambilan=new PermohonanPengambilan();
                    KodBandarPekanMukim pp = new KodBandarPekanMukim();
                    pp.setKod(12);
                    KodUOM m = new KodUOM();
                    m.setKod("M");
                    if (StringUtils.isNotBlank(tarikhWarta))
                    {
                    tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                    permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));
                    }
                    permohonanPengambilan.setNoWarta(noWarta);
                    permohonanPengambilan.setPermohonan(permohonan);
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    permohonanPengambilan.setInfoAudit(info);
                    permohonanPengambilan.setCawangan(peng.getKodCawangan());
                    permohonanPengambilan.setBandarPekanMukim(pp);
                    permohonanPengambilan.setKodUnitLuas(m);
                    permohonanPengambilan.setLuasTerlibat(new BigDecimal(0.00));
                    permohonanPengambilan.setNoWarta(noWarta);
                    permohonanPengambilan.setTarikhWarta(sdf.parse(tarikhWarta));
                    if(permohonanSblm != null) {
                        permohonanPengambilan.setPermohonanTerdahulu(permohonanSblm.getIdPermohonan());
                    }
                    if(permohonanRujukanLuar!=null)
                   {
                       permohonanRujukanLuar=new PermohonanRujukanLuar();
                       permohonanRujukanLuar.setPermohonan(permohonan);
                       permohonanPengambilan.setInfoAudit(info);
                       permohonanRujukanLuar.setInfoAudit(info);
                       permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                       KodRujukan kr=new KodRujukan();
                       kr.setKod("NF");
                       permohonanRujukanLuar.setKodRujukan(kr);
                       permohonanRujukanLuar.setUlasan(ulasan);
                       permohonanRujukanLuar.setCatatan("Warta Pembetulan");
                   if (StringUtils.isNotBlank(tarikhDisampai))
                    {
                        tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                        permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
                    }
                   }
                    else
                   {
                       permohonanRujukanLuar.setPermohonan(permohonan);
                       permohonanPengambilan.setInfoAudit(info);
                       permohonanRujukanLuar.setInfoAudit(info);
                       permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
                       KodRujukan kr=new KodRujukan();
                       kr.setKod("NF");
                       permohonanRujukanLuar.setKodRujukan(kr);
                       permohonanRujukanLuar.setUlasan(ulasan);
                       permohonanRujukanLuar.setCatatan("Warta Asal");
                   if (StringUtils.isNotBlank(tarikhDisampai))
                    {
                        tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                        permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
                    }


                   }

                    addSimpleMessage("Kemasukan Data Berjaya.");
                }
                service.simpanWarta(permohonanPengambilan);
                service.saveOrUpdateMRL(permohonanRujukanLuar);

       getContext().getRequest().setAttribute("simpanWarta", Boolean.TRUE);
       return new JSP("pengambilan/kemasukan_warta.jsp").addParameter("tab", "true");
    }

     public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }
     public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String nowarta) {
        this.noWarta = nowarta;
    }

    public String getIdPengambilan() {
        return idPengambilan;
    }

    public void setIdPengambilan(String idPengambilan) {
        this.idPengambilan = idPengambilan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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
    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }
      public String getTarikhDisampai() {
        return tarikhDisampai;
    }

    public void setTarikhDisampai(String tarikhDisampai) {
        this.tarikhDisampai = tarikhDisampai;
    }
    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
       public List<PermohonanPengambilan> getPermohonanPengambilanList() {
        return permohonanPengambilanList;
    }

    public void setPermohonanPengambilanList(List<PermohonanPengambilan> permohonanPengambilanList) {
        this.permohonanPengambilanList = permohonanPengambilanList;
    }
    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }
}
