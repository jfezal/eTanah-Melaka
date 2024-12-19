/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.DokumenCapaian;
import etanah.service.uam.UamService;
import java.util.Date;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author zahidaziz
 */
@HttpCache(allow=false)
@UrlBinding("/uam/AuditDokumenDetails")
public class viewAuditDokDetails extends AbleActionBean{
    @Inject
    private UamService service;
    private long idCapai;
    private long idDok;
    private String alasan;
    private String pguna;
    private Date masa;
    private String kodAktiviti;
    private DokumenCapaian capaiDok;
    
    
    @DefaultHandler
    public Resolution showForm(){
        return new JSP("uam/auditDokumenDetails.jsp");
    }
    
    public Resolution getAuditDokDetails() throws  NamingException{
        
        String id = getContext().getRequest().getParameter("idCapaian");
        System.out.println("idCapai ="+id);
        capaiDok =service.getAuditDokDetails(Long.parseLong(id));
        
        System.out.println("Id Capai"+capaiDok.getIdCapaian());
        
        
        
        return new ForwardResolution("/WEB-INF/jsp/uam/auditDokumenDetails.jsp");
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public DokumenCapaian getCapaiDok() {
        return capaiDok;
    }

    public void setCapaiDok(DokumenCapaian capaiDok) {
        this.capaiDok = capaiDok;
    }

    public long getIdCapai() {
        return idCapai;
    }

    public void setIdCapai(long idCapai) {
        this.idCapai = idCapai;
    }

    public long getIdDok() {
        return idDok;
    }

    public void setIdDok(long idDok) {
        this.idDok = idDok;
    }

    public String getKodAktiviti() {
        return kodAktiviti;
    }

    public void setKodAktiviti(String kodAktiviti) {
        this.kodAktiviti = kodAktiviti;
    }

    public Date getMasa() {
        return masa;
    }

    public void setMasa(Date masa) {
        this.masa = masa;
    }

    public String getPguna() {
        return pguna;
    }

    public void setPguna(String pguna) {
        this.pguna = pguna;
    }

    public UamService getService() {
        return service;
    }

    public void setService(UamService service) {
        this.service = service;
    }
    
}

