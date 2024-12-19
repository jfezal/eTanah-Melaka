  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.utility.kod;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.UrusanAliran;
import etanah.service.UrusanALiranService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Session;
/**
 *
 * @author azwady
 */

@HttpCache(allow = false)
@UrlBinding("/utility_kod/stageUrusan")
public class StageUrusanActionBean extends AbleActionBean {
   
   
    //private Pengguna pguna;
    private String nameTable;
    private Date datemasuk = new Date();
    private UrusanAliran urusalir = new UrusanAliran();
    private List<KodUrusan> listnamaurusan = new ArrayList<KodUrusan>();
    private InfoAudit infoaudit = new InfoAudit();
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    private UrusanALiranService service;
    
    private static final org.apache.log4j.Logger LOG = etanah.SYSLOG.getLogger();    
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
     
    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/utiliti/stage_urusan.jsp");
    }
    
    public Resolution getSimpan() {
        LOG.info("Entering getSimpan() function.......");
        urusalir.setKodUrusan(getKodUrus());
        urusalir.setIdAliran(getStageID());
        urusalir.setSasaranHari(getSasarHari()); 
        urusalir.setInfoAudit(getInfoAudit());
        service.save(urusalir); 
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit2", Boolean.TRUE);
        return new JSP("/utiliti/stage_urusan.jsp");
    }
    
    
    public InfoAudit getInfoAudit() {
         Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
         infoaudit.setDimasukOleh(pengguna);
         infoaudit.setTarikhMasuk(datemasuk);
         //infoaudit.setTarikhMasuk(datemasuk);
         return infoaudit;
    }
    public UrusanAliran getUrusalir() {
          return urusalir;
    }

    public void setUrusalir(UrusanAliran urusalir) {
        this.urusalir = urusalir;
    }
    
     public List<KodUrusan> getListNamaUrusan() {
         listnamaurusan = service.getNamaUrusan();
        return listnamaurusan;
    }
    
       public BigDecimal getSasarHari() {
           return urusalir.getSasaranHari();
    }

       public String getStageID() {
           return urusalir.getIdAliran();
    }

       public KodUrusan getKodUrus() {
           return urusalir.getKodUrusan();
    }

    public String getNameTable() {
        nameTable = getContext().getRequest().getParameter("table_name");
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    } 

       public Date getDatemasuk() {
        return datemasuk;
    }

    public void setDatemasuk(Date datemasuk) {
        this.datemasuk = datemasuk;
    }
}
