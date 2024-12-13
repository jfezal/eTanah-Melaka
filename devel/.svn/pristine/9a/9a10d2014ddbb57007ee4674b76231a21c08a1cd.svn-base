package etanah.view.daftar.utiliti;
import org.apache.commons.lang.StringUtils;
import java.util.ArrayList;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikKertasDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.HakmilikKertas;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.service.KertasHakmilikService;
//import etanah.util.StringUtils;
import etanah.view.etanahActionBeanContext;
import etanah.workflow.WorkFlowService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;

@UrlBinding("/daftar/kemaskini_kertas_hakmilik")
public class KemaskiniKertasHakmilik  extends AbleActionBean{

    private static final Logger LOG = Logger.getLogger(KemaskiniKertasHakmilik.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private static final String UPDATE_VIEW = "/daftar/utiliti/kertas_hakmilik_form.jsp";
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikKertas hakmilikKertas;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    HakmilikKertasDAO hakmilikKertasDAO;
    @Inject
    KertasHakmilikService kertasHakmilikService;
    @Inject
    KertasHakmilikService khs;
    @Inject
    KertasHakmilikService  khService;
    @Inject
    KertasHakmilikService pService;

    private List<HakmilikKertas> senaraiHakmilikKertas;
    private String idPengguna;
    private Long idHakmilikKertas;
    @Inject
    private etanah.Configuration conf;
    private String kodNegeri;
    private String noAwal;
    private String noAkhir;
    private String jenisKertas;
    private String tarikhAmbil;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private Date tarikhMasuk;
    private List<Pengguna> listPengguna;
    private boolean form = false;
    
    public Long getIdHakmilikKertas() {
        return idHakmilikKertas;
    }

    public void setIdHakmilikKertas(Long idHakmilikKertas) {
        this.idHakmilikKertas = idHakmilikKertas;
    }
    public HakmilikKertas getHakmilikKertas() {
//        if (idHakmilikKertas != null) {
//            return hakmilikKertasDAO.findById(idHakmilikKertas);
//        }
        return hakmilikKertas;
    }

    public void setHakmilikKertas(HakmilikKertas hakmilikKertas) {
        this.hakmilikKertas = hakmilikKertas;
    }
    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    public List<HakmilikKertas> getSenaraiHakmilikKertas() {
        return senaraiHakmilikKertas;
    }

    public void setSenaraiHakmilikKertas(List<HakmilikKertas> senaraiHakmilikKertas) {
        this.senaraiHakmilikKertas = senaraiHakmilikKertas;
    }
    
    public List<Pengguna> getListPengguna() {
        return listPengguna;
    }

    public void setListPengguna(List<Pengguna> listPengguna) {
        this.listPengguna = listPengguna;
    }
    
    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }
    private Pengguna peng;
    
    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }
    private KodCawangan kodCawangan;
    
    
   
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

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("daftar/utiliti/kemaskini_kertas_hakmilik.jsp");
    }
    public Resolution kertasHakmilikForm() throws ParseException
    {
        LOG.debug("=======================Dapat No ID================= "+ idHakmilikKertas);
        hakmilikKertas = hakmilikKertasDAO.findById(idHakmilikKertas);
        LOG.debug("=======================Dapat No ID================= "+hakmilikKertas.getIdHakmilikKertas());
        LOG.debug("=======================Dapat No Awal================ "+hakmilikKertas.getNoAwal());
        LOG.debug("=======================Dapat No Akhir================ "+hakmilikKertas.getNoAkhir());
        
        LOG.debug("=======================DapatTarikh================ "+hakmilikKertas.getTarikhDiambil());
        Date dt1= hakmilikKertas.getTarikhDiambil();
        sd.format(dt1);
        
        hakmilikKertas.setTarikhDiambil(dt1);
        LOG.debug("=======================DapatTarikh================ "+hakmilikKertas.getTarikhDiambil());
        //Date dt1 = hakmilikKertas.getTarikhDiambil();
        //Date dt2 = sd.format;
//        Date dt1 = sd.parse(hakmilikKertas.getTarikhDiambil());
        
       // hakmilikKertas.setTarikhDiambil(sd.format(hakmilikKertas.getTarikhDiambil()));
//        hakmilikKertas.setTarikhDiambil(dt1);
        //return showForm();
        //return new JSP("daftar/utiliti/kemaskini_kertas_hakmilik.jsp");
        idPengguna = getContext().getRequest().getParameter("hakmilikKertas.pengguna.idPengguna");
        senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
        return new JSP("daftar/utiliti/kertas_hakmilik_form.jsp");
        //return searchPenyerahForEdit();
        
    }
      @Before(stages = {LifecycleStage.BindingAndValidation})
        public void rehydrate() {

        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getKodNegeri();

    }
         public Resolution searchPenyerahForEdit() {
        idPengguna = getContext().getRequest().getParameter("idPengguna");
        senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
        LOG.debug("======================= idpengguna"+idPengguna);
        for( HakmilikKertas hk : senaraiHakmilikKertas)
        {
            hk.getIdHakmilikKertas();
            LOG.debug("======================= idHakmilikKertas" + hk.getIdHakmilikKertas());
        }
        if (StringUtils.isNotBlank(idPengguna)) {
            senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
        } else {
            addSimpleError("Sila Pilih Nama Pendaftar");
        }
        return showForm();

    }
        public Resolution pilih()
        {
            String idHK = getContext().getRequest().getParameter("idHakmilikKertas");
            hakmilikKertas = hakmilikKertasDAO.findById(Long.parseLong(idHK));
            
            return showForm();
        }
     
         public Resolution pilihHakmilik()
         {
             String idHK = getContext().getRequest().getParameter("idHakmilikKertas");
             idHakmilikKertas = Long.parseLong(idHK);
             
             return showForm();
         }

        public Resolution update() throws ParseException {
        
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        noAwal = getContext().getRequest().getParameter("hakmilikKertas.noAwal");
        noAkhir = getContext().getRequest().getParameter("hakmilikKertas.noAkhir");
        jenisKertas = getContext().getRequest().getParameter("hakmilikKertas.jenisKertas");
        tarikhAmbil = getContext().getRequest().getParameter("hakmilikKertas.tarikhDiambil");
        idPengguna =  getContext().getRequest().getParameter("hakmilikKertas.pengguna.idPengguna");
        Pengguna pengguna = penggunaDAO.findById(idPengguna);
        peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilikKertas = Long.parseLong(getContext().getRequest().getParameter("hakmilikKertas.idHakmilikKertas"));
        
        
        //Validation
        if(tarikhAmbil == null || tarikhAmbil == "")
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
            noAkhir =  noAwal;
        }
        //Extract Number
        String noA = noAwal.replaceAll("\\D+","");
        String noZ = noAkhir.replaceAll("\\D+","");
        
        if(Integer.parseInt(noA) > Integer.parseInt(noZ))
        {
            addSimpleError("Nombor Siri Akhir Mesti Lebih Besar Dari Nombor Siri Awal");
            return showForm();
        }
        if (noA.matches("[0-9]+") && noZ.matches("[0-9]+")  && noA.length() > 0  && noZ.length() > 0){
        
        List<HakmilikKertas> hkListA = khService.cariSenaraiKertasAntaraNoAwalDanNoAkhirA(noAwal,noAkhir);
        List<HakmilikKertas> hkListB = khService.cariSenaraiKertasAntaraNoAwalDanNoAkhirB(noAwal, noAkhir);
        HakmilikKertas hkA = khService.cariHakmilikKertasByNoSiri(noAwal);
        HakmilikKertas hkB = khService.cariHakmilikKertasByNoSiri(noAkhir);
        HakmilikKertas h = hakmilikKertasDAO.findById(idHakmilikKertas);
        String nomborAwal = h.getNoAwal().replaceAll("\\D+","");
        String nomborAkhir = h.getNoAkhir().replaceAll("\\D+","");
        int p,q,x,y;
        p=Integer.parseInt(nomborAwal);
        q=Integer.parseInt(nomborAkhir);
        x=Integer.parseInt(noA);
        y=Integer.parseInt(noZ);
                
  //if( (hkListA.isEmpty() && hkListB.isEmpty() && hkA == null && hkB == null ) || ( (x >= p && x <= q) && (y <= q && y >= p) )  ) {
            if( (hkListA.isEmpty() && hkListB.isEmpty() && hkA == null && hkB == null ) || 
                ( noAwal.equals(h.getNoAwal()) && hkB == null ) || 
                ( noAkhir.equals(h.getNoAkhir()) && hkA == null ) || 
                ( noAkhir.equals(h.getNoAkhir()) && noAwal.equals(h.getNoAwal()) )
                 || ( (x >= p && x <= q) && (y <= q && y >= p) )  ) {
          
                InfoAudit info = peng.getInfoAudit();
                int bil = Integer.parseInt(noZ) - Integer.parseInt(noA) +1;
                hakmilikKertas = hakmilikKertasDAO.findById(idHakmilikKertas);
                InfoAudit ia = new InfoAudit();
                Date dt1 = sd.parse(tarikhAmbil);

                ia.setDimasukOleh(peng);
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                ia.setTarikhKemaskini(new java.util.Date());

                hakmilikKertas.setKodCawangan(peng.getKodCawangan());
                hakmilikKertas.setNoAwal(noAwal);
                hakmilikKertas.setNoAkhir(noAkhir);
                
                if(kodNegeri.equals("04"))
                {
                   hakmilikKertas.setJenisKertas(jenisKertas);
                }
                 if(kodNegeri.equals("05"))
                {
                   hakmilikKertas.setJenisKertas("DHDE/DHKE");
                }
              
                hakmilikKertas.setInfoAudit(ia);
                hakmilikKertas.setPengguna(pengguna);
                hakmilikKertas.setTarikhDiambil(dt1);
                hakmilikKertas.setBilangan(bil);

                khs.simpanHakmilikKertas(hakmilikKertas);
                senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
                addSimpleMessage("Kertas Hakmilik Berjaya Dikemaskini");
                return showForm();
                //return searchPenyerahForEdit();
            }
            else{
            senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);    
            addSimpleError("No. Siri Telah Digunakan"); 
            return kertasHakmilikForm();}
        }
        else
        {senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
         addSimpleError("Nombor Siri Tidak Sah.");}
         return kertasHakmilikForm();
        //return searchPenyerahForEdit();
    }
        
        public Resolution kembali()
        {
            idPengguna = getContext().getRequest().getParameter("hakmilikKertas.pengguna.idPengguna");
            senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
            return showForm();
        }
        
}