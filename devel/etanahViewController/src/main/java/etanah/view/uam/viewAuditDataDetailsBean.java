/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.service.uam.UamService;
import etanah.view.etanahActionBeanContext;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import oracle.adf.share.security.model.dc.idm.exception.UserNotFoundException;
import org.apache.commons.lang.StringUtils;
import etanah.model.AuditData;
import etanah.model.AuditDataId;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zahidaziz
 */
@HttpCache(allow = false)
@UrlBinding("/uam/view_Audit_Data_Details")
public class viewAuditDataDetailsBean extends AbleActionBean {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NewAndUpdateActionBean.class);
    @Inject
    private UamService service;
    private String primaryKey;
    private String masa;
    private String namaTable;
    private String namaMedan;
    private String aktiviti;
    private String dataLama;
    private String dataBaru;
    private AuditData ad;
    private AuditData auditUser;
    
    private Date tarikhDari;
    private Date tarikhHingga;
    private String jadual;
    private String idPengguna;    
    private AuditDataId idAuditData;
    private int paparan = 20;
    private boolean flag = false;
    

    public Resolution showForm() {
        return new JSP("uam/viewAuditDataDetails.jsp").addParameter("popup", true);
    }

    public Resolution viewAuditData() throws NamingException {
        String idAudit = getContext().getRequest().getParameter("idAudit");
        String namaMedan = getContext().getRequest().getParameter("namaMedan");

        System.out.println("id audit = " + idAudit);
        System.out.println("nama medan = " + namaMedan);

        AuditDataId adi = new AuditDataId(Long.parseLong(idAudit), namaMedan);

        if (adi != null) {

            ad = service.searchByAuditId(adi);
            auditUser = service.searchUserById(adi.getIdAudit());

//         System.out.println("Exist :"+ad.size());
        }
        return new ForwardResolution("/WEB-INF/jsp/uam/viewAuditDataDetails.jsp").addParameter("popup", true);
    }

    public Resolution kembali() {
        logger.info("KEMBALI:::");
        return new ForwardResolution("/WEB-INF/jsp/uam/auditMedan.jsp");
    }

    public AuditData getAd() {
        return ad;
    }

    public void setAd(AuditData ad) {
        this.ad = ad;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }

    public String getAktiviti() {
        return aktiviti;
    }

    public void setAktiviti(String aktiviti) {
        this.aktiviti = aktiviti;
    }

    public String getDataBaru() {
        return dataBaru;
    }

    public void setDataBaru(String dataBaru) {
        this.dataBaru = dataBaru;
    }

    public String getDataLama() {
        return dataLama;
    }

    public void setDataLama(String dataLama) {
        this.dataLama = dataLama;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getNamaMedan() {
        return namaMedan;
    }

    public void setNamaMedan(String namaMedan) {
        this.namaMedan = namaMedan;
    }

    public String getNamaTable() {
        return namaTable;
    }

    public void setNamaTable(String namaTable) {
        this.namaTable = namaTable;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public AuditData getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(AuditData auditUser) {
        this.auditUser = auditUser;
    }

    public Date getTarikhDari() {
        return tarikhDari;
    }

    public void setTarikhDari(Date tarikhDari) {
        this.tarikhDari = tarikhDari;
    }

    public Date getTarikhHingga() {
        return tarikhHingga;
    }

    public void setTarikhHingga(Date tarikhHingga) {
        this.tarikhHingga = tarikhHingga;
    }

    public String getJadual() {
        return jadual;
    }

    public void setJadual(String jadual) {
        this.jadual = jadual;
    }

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public AuditDataId getIdAuditData() {
        return idAuditData;
    }

    public void setIdAuditData(AuditDataId idAuditData) {
        this.idAuditData = idAuditData;
    }

    public int getPaparan() {
        return paparan;
    }

    public void setPaparan(int paparan) {
        this.paparan = paparan;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    
}
