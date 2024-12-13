/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.ListUtil;
import java.math.BigDecimal;



@UrlBinding("/pelupusan/surat_kelulusan_pemberimilikan")
public class SuratKelulusanPemberimilikanActionBean extends AbleActionBean
{
    @Inject
    ListUtil listUtil;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodKategoriTanahDAO kategoriTanahDAO;
    @Inject
    PermohonanService permohonanService;

    private HakmilikPermohonan hakmilikPermohonan;
    private KodKategoriTanah kodKategoriTanah;
    private KodSyaratNyata kodSyaratNyata;
    private KodSekatanKepentingan kodSekatanKepentingan;
    private KodHakmilik kodHakmilik;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private String jenisHakmilik;
    private BigDecimal bigDecimal;
    private boolean flag;
    private String s;


    @DefaultHandler
    public Resolution showForm() {
        getHakmilikPermohonanDetails();
         return new JSP("pelupusan/surat_kelulusan_pemberimilikan.jsp").addParameter("tab", "true");
    }
       @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate()
       {
           System.out.println("-------rehydrate---------");
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);


       }


       public void getHakmilikPermohonanDetails(){
         if(!permohonan.getSenaraiHakmilik().isEmpty()){
            hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
            System.out.println("------hakmilikPermohonan--id--"+hakmilikPermohonan.getId());
            System.out.println("------hakmilikPermohonan--tempoh--"+hakmilikPermohonan.getTempohHakmilik());
        }else{
            hakmilikPermohonan = new HakmilikPermohonan();
        }

       }

       public Resolution Simpan()
       {
         System.out.println("-------Simpan---------");

         pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
         String idHakmilik = (String) getContext().getRequest().getParameter("hakmilikPermohonan.id");
         System.out.println("----------idPermohonan--------"+idPermohonan);
         System.out.println("----------permohonan--------"+permohonan);
         permohonan = permohonanService.findById(idPermohonan);
         InfoAudit infoAudit = new InfoAudit();
         infoAudit.setTarikhKemaskini(new java.util.Date());
         infoAudit.setDiKemaskiniOleh(pengguna);
//         s =getContext().getRequest().getParameter("hc");
//         BigDecimal bigDecimal = new BigDecimal(s);
         if(validations())
         {
              return new JSP("pelupusan/surat_kelulusan_pemberimilikan.jsp").addParameter("tab", "true");
         }
         HakmilikPermohonan  hakmilikPermohonanTemp = new HakmilikPermohonan();

          if((idHakmilik != null)&&(!idHakmilik.equals("")))
         {
             hakmilikPermohonanTemp= hakmilikPermohonanDAO.findById(Long.parseLong(idHakmilik));
             infoAudit = hakmilikPermohonanTemp.getInfoAudit();
              infoAudit.setTarikhKemaskini(new java.util.Date());
              infoAudit.setDiKemaskiniOleh(pengguna);
              hakmilikPermohonanTemp.setInfoAudit(infoAudit);

         }else{
             infoAudit.setDimasukOleh(pengguna);
             infoAudit.setTarikhMasuk(new java.util.Date());
             hakmilikPermohonanTemp.setInfoAudit(infoAudit);
         }


//              hakmilikPermohonan.setInfoAudit(infoAudit);

           System.out.println("------idHakmilik----permohonan--------"+permohonan);
           hakmilikPermohonanTemp.setPermohonan(permohonan);
           hakmilikPermohonanTemp.setKodHakmilikTetap( hakmilikPermohonan.getKodHakmilikTetap());
           System.out.println("--------------pavan 1--------"+hakmilikPermohonan.getKodHakmilikTetap().getKod());

//           System.out.println("--------------pavan 2--------"+hakmilikPermohonan.getTempohHakmilik());
           hakmilikPermohonanTemp.setTempohHakmilik( hakmilikPermohonan.getTempohHakmilik());
           System.out.println("--------------pavan 2--------"+hakmilikPermohonan.getTempohHakmilik());
           hakmilikPermohonanTemp.setCukaiPerMeterPersegi(hakmilikPermohonan.getCukaiPerMeterPersegi());
            System.out.println("--------------pavan 3--------"+hakmilikPermohonan.getCukaiPerMeterPersegi());

         //  System.out.println("--------------pavan 4--------"+hakmilikPermohonan.getCukaiPerMeterPersegi());
           hakmilikPermohonanTemp.setJenisPenggunaanTanah( hakmilikPermohonan.getJenisPenggunaanTanah());
           System.out.println("--------------pavan 4--------"+hakmilikPermohonan.getJenisPenggunaanTanah().getKod());
           hakmilikPermohonanTemp.setSyaratNyata( hakmilikPermohonan.getSyaratNyata());
           System.out.println("--------------pavan 5--------"+hakmilikPermohonan.getSyaratNyata().getKod());
           hakmilikPermohonanTemp.setSekatanKepentingan( hakmilikPermohonan.getSekatanKepentingan());
           System.out.println("--------------pavan 6--------"+hakmilikPermohonan.getSekatanKepentingan().getKod());


           pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonanTemp);

           getHakmilikPermohonanDetails();

            return new JSP("pelupusan/surat_kelulusan_pemberimilikan.jsp").addParameter("tab", "true");
       }


     public boolean validations()
     {
         flag =false;
         if(hakmilikPermohonan.getKodHakmilikTetap() == null )
         {
             flag=true;
             System.out.println("addSimpleError(Sila Pilih Jenis Hakmilik);");
             addSimpleError("Sila Pilih Jenis Hakmilik");

         }
         else if(hakmilikPermohonan.getTempohHakmilik() == null)
         {
             flag=true;
             System.out.println("addSimpleError(Sila Pilih Tempoh Hakmilik");
             addSimpleError("Sila Pilih Tempoh Hakmilik");
         }
          else if(hakmilikPermohonan.getCukaiPerMeterPersegi() == null )
         {
             flag=true;
             System.out.println("------------bigDecimal--------##########"+s);
             addSimpleError("Sila Masukkan Hasil");
         }else if(hakmilikPermohonan.getJenisPenggunaanTanah() == null)
         {

             flag=true;
             System.out.println("addSimpleError(Sila Pilih Jenis Penggunaan Tanah");
             addSimpleError("Sila Pilih Jenis Penggunaan Tanah ");
         }else if(hakmilikPermohonan.getSyaratNyata() == null)
         {
              flag=true;
              System.out.println("addSimpleError(Sila Pilih Syarat Nyata");
             addSimpleError("Sila Pilih Syarat Nyata");
         }else if(hakmilikPermohonan.getSekatanKepentingan() == null)
         {

             flag=true;
              System.out.println("addSimpleError(Sila Pilih Sekatan Kepentingan");
             addSimpleError("Sila Pilih  Sekatan Kepentingan ");
         }
         return flag;
     }
    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

    public KodKategoriTanah getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(KodKategoriTanah kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

//    public String getKodKategoriTanah1() {
//        return kodKategoriTanah1;
//    }
//
//    public void setKodKategoriTanah1(String kodKategoriTanah1) {
//        this.kodKategoriTanah1 = kodKategoriTanah1;
//    }

    public KodSekatanKepentingan getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(KodSekatanKepentingan kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public KodSyaratNyata getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(KodSyaratNyata kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }
    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }
     public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }



}








