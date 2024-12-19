/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.utiliti;
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
import etanah.view.etanahActionBeanContext;
import etanah.service.RegService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.apache.log4j.Logger;


/**
 *
 * @author Zulhazmi
 */
@UrlBinding("/daftar/pertanyaan_kertas_hakmilik")
public class PertanyaanKertasHakmilik extends AbleActionBean
{

    private static final Logger LOG = Logger.getLogger(PertanyaanKertasHakmilik.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private String idPengguna;
    private Long idHakmilikKertas;
    private String noAwal;
    private String noAkhir;
    private String jenisKertas;
    private String tarikhAmbil;
    private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
    private Date tarikhMasuk;
    private List<Pengguna> listPengguna;
    private List<HakmilikKertas> senaraiHakmilikKertas;
    private HakmilikKertas hakmilikKertas;
    private Pengguna peng;
    private KodCawangan kodCawangan;
    @Inject
    private etanah.Configuration conf;
    private String kodNegeri;
    @Inject
    HakmilikKertas hakmilikkertas;
    PenggunaDAO penggunaDAO;
    HakmilikKertasDAO hakmilikKertasDAO;
    @Inject
    KertasHakmilikService kertasHakmilikService;
    @Inject
    RegService regService;
    
    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
    public HakmilikKertas getHakmilikKertas() {
        return hakmilikKertas;
    }

    public void setHakmilikKertas(HakmilikKertas hakmilikKertas) {
        this.hakmilikKertas = hakmilikKertas;
    }
    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }
    
    public Long getIdHakmilikKertas() {
        return idHakmilikKertas;
    }

    public void setIdHakmilikKertas(Long idHakmilikKertas) {
        this.idHakmilikKertas = idHakmilikKertas;
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
    
    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }
   
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
    
         return new ForwardResolution("/WEB-INF/jsp/daftar/utiliti/pertanyaan_kertas_hakmilik.jsp");
    }
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        kodNegeri = conf.getKodNegeri();
      
    }

    public Resolution checkIdHakmilikKertas() {
        
       idPengguna = getContext().getRequest().getParameter("idPengguna");
       String noSiri = getContext().getRequest().getParameter("noSiri");

        if (StringUtils.isNotBlank(idPengguna)) {
            senaraiHakmilikKertas = kertasHakmilikService.cariSenaraiHakmilikKertasbyPengguna(idPengguna);
        }
        if (StringUtils.isNotBlank(noSiri)) {
            hakmilikKertas = kertasHakmilikService.cariHakmilikKertasByNoSiri(noSiri); 
        }
        if (StringUtils.isBlank(noSiri) && StringUtils.isBlank(idPengguna) )
        {
            addSimpleError("Sila Pilih Jenis Carian");
        }
        
       return  showForm();
        
    }

}
