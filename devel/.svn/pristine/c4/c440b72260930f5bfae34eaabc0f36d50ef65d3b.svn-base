/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PenggunaDAO;
import etanah.model.HakmilikKertas;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.service.KertasHakmilikService;
import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;


@UrlBinding("/daftar/rekod_hakmilik_kertas")
public class PengeluaranKertasHakmilik  extends AbleActionBean{
    
    private static final Logger LOG = Logger.getLogger(PengeluaranKertasHakmilik.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikKertas Hakmilikkertas;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private etanah.Configuration conf;
    private String kodNegeri;
    private String noAwal;
    private String noAkhir;
    private String jenisKertas;
    private String tarikhAmbil;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private Date tarikhMasuk;
    private String idPengguna;
    @Inject
    Pengguna pengguna;
    private Pengguna peng;
    
    
     public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    
    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }
    
    @Inject
    KertasHakmilikService khs;
    
   @Inject
    KertasHakmilikService  khService;
    
   
    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }
    private KodCawangan kodCawangan;
    
    @Inject
    KertasHakmilikService pService;
   
    public String getNoAwal() {
        return noAwal;
    }

    public String getNoAkhir() {
        return noAkhir;
    }

    public String getJenisKertas() {
        return jenisKertas;
    }
    

    public String getTarikhAmbil() {
        return tarikhAmbil;
    }

    public Date getTarikhMasuk() {
        return tarikhMasuk;
    }

    public void setNoAwal(String noAwal) {
        this.noAwal = noAwal;
    }

    public void setNoAkhir(String noAkhir) {
        this.noAkhir = noAkhir;
    }

    public void setJenisKertas(String jenisKertas) {
        this.jenisKertas = jenisKertas;
    }

    public void setTarikhAmbil(String tarikhAmbil) {
        this.tarikhAmbil = tarikhAmbil;
    }

    public void setTarikhMasuk(Date tarikhMasuk) {
        this.tarikhMasuk = tarikhMasuk;
    }
    
     public Resolution requestParam() {
        
        
        return new ForwardResolution("/WEB-INF/jsp/daftar/rekod_pengeluaran_kertas_hakmilik.jsp").addParameter("popup", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getKodNegeri();
    }

     

    @DefaultHandler
    public Resolution showForm() {
        
        return new ForwardResolution("/WEB-INF/jsp/daftar/rekod_pengeluaran_kertas_hakmilik.jsp");
    }
    
    public Resolution reset() {
    
        noAwal ="";
        noAkhir ="";
        jenisKertas ="";
        tarikhAmbil ="";
        idPengguna ="0";
        return showForm();
    
    }
    
    public Resolution saveHakmilikKertas() throws ParseException  {

        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        noAwal = getContext().getRequest().getParameter("noAwal");
        noAkhir = getContext().getRequest().getParameter("noAkhir");
        jenisKertas = getContext().getRequest().getParameter("jenisKertas");
        tarikhAmbil = getContext().getRequest().getParameter("tarikhAmbil");
        idPengguna =  getContext().getRequest().getParameter("idPengguna");
        
   
        //Validation
        if(tarikhAmbil == null || "".equals(tarikhAmbil))
        {
            addSimpleError("Sila Masukkan Tarikh");
            return showForm();
        }
        if(StringUtils.isBlank(noAwal) && StringUtils.isBlank(noAkhir))
        {
            addSimpleError("Sila Masukkan Nombor Awal dan Nombor Akhir");
            return showForm();
        }
        if(StringUtils.isBlank(noAwal))
        {
            addSimpleError("Sila Masukkan Nombor Awal");
            return showForm();
        }
        if(StringUtils.isBlank(noAkhir))
        {
            
            noAkhir =  noAwal ;
        }
            
        //Extract Number
        String noA = noAwal.replaceAll("\\D+","");
        String noZ = noAkhir.replaceAll("\\D+","");
      
        if(Integer.parseInt(noA) > Integer.parseInt(noZ))
        {
            addSimpleError("Pastikan Nombor Siri Akhir Lebih Besar Dari Nombor Siri Awal");
            return showForm();
        }
        
        //Start Saving
        if (noA.matches("[0-9]+") && noZ.matches("[0-9]+")  && noA.length() > 0  && noZ.length() > 0){
        
        List<HakmilikKertas> hkListA = khService.cariSenaraiKertasAntaraNoAwalDanNoAkhirA(noAwal,noAkhir);
        List<HakmilikKertas> hkListB = khService.cariSenaraiKertasAntaraNoAwalDanNoAkhirB(noAwal,noAkhir);
        HakmilikKertas hkA = khService.cariHakmilikKertasByNoSiri(noAwal);
        HakmilikKertas hkB = khService.cariHakmilikKertasByNoSiri(noAkhir);
  
            if( hkListA.isEmpty() && hkListB.isEmpty() && hkA == null && hkB == null ) {
            
                Pengguna pengguna = penggunaDAO.findById(idPengguna);
                peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                kodCawangan = peng.getKodCawangan();
                InfoAudit info = peng.getInfoAudit();
                int bil = Integer.parseInt(noZ) - Integer.parseInt(noA) +1;
                InfoAudit ia = new InfoAudit();
                Date dt1 = sd.parse(tarikhAmbil);
                
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                HakmilikKertas hk = new HakmilikKertas();
                hk.setBilangan(bil);
                hk.setNoAwal(noAwal);
                hk.setNoAkhir(noAkhir);
                hk.setKodCawangan(kodCawangan);
                
                
                if(kodNegeri.equals("04"))
                {
                   hk.setJenisKertas(jenisKertas);
                }
                 if(kodNegeri.equals("05"))
                {
                   hk.setJenisKertas("DHDE/DHKE");
                }
                 
                hk.setInfoAudit(ia);
                hk.setPengguna(pengguna);
                hk.setTarikhDiambil(dt1);
                
                khs.simpanHakmilikKertas(hk);
                addSimpleMessage("Kertas Hakmilik Berjaya Disimpan");

                return showForm();
            }
            else
            addSimpleError("No. Siri Telah Digunakan");   
        } 
        
        else 
         addSimpleError("Nombor Siri Tidak Sah.");
        
        return showForm();
    }  

   
}
