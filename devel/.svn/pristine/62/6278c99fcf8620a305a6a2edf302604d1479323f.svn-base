package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.*;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sourceforge.stripes.action.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author haqqiem 
 * Mon 10 Feb 14 11:50:45
 *
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/download_agensi")
public class DownloadAgensiActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(DownloadAgensiActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/download_agensi.jsp";
    SimpleDateFormat sdfNoSlash = new SimpleDateFormat("ddMMyyyy");

    private String kodNegeri;
    private String jenisPilihan;
    private String downloadLink = "";
    private String formatFile = "";
    private String filename = "";
    private List<AgensiKutipanDokumen> senaraiDokumen = new ArrayList<AgensiKutipanDokumen>();

    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");
        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        } else {
            kodNegeri = "negeriSembilan";
        }

        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT u FROM etanah.model.AgensiKutipanDokumen u WHERE u.kodDokumen.kod LIKE :kod ORDER BY u.infoAudit.tarikhMasuk DESC");
        q.setString("kod", "%PB%");
        senaraiDokumen = q.list();

        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @HandlesEvent("Step2")
    public Resolution carian() {
        String agent = "";
        if(jenisPilihan.equalsIgnoreCase("dariAgensi"))
            agent = "%AK";
        if(jenisPilihan.equalsIgnoreCase("untukAgensi"))
            agent = "%PB%";
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT u FROM etanah.model.AgensiKutipanDokumen u WHERE u.kodDokumen.kod LIKE :kod ORDER BY u.infoAudit.tarikhMasuk DESC");
        q.setString("kod", agent);
        senaraiDokumen = q.list();
        
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @HandlesEvent("Step3")
    public Resolution downloadFile() throws FileNotFoundException, IOException {
        String url = getContext().getRequest().getParameter("dirPath");
        url = downloadLink;
        logger.info("url : "+url);
        logger.info("formatFile : "+formatFile);
        logger.info("filename : "+filename);
        String etanahPath = conf.getProperty("document.path");
        
        if(jenisPilihan.equalsIgnoreCase("dariAgensi"))
            etanahPath = conf.getProperty("kutipan.inbound.path");
        
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        
        File f = new File(etanahPath+url);
        
        if (f != null) {
            try {
                InputStream inputStream = new FileInputStream(f.getAbsolutePath());
                return new StreamingResolution(formatFile, inputStream).setFilename(filename);

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getJenisPilihan() {
        return jenisPilihan;
    }

    public void setJenisPilihan(String jenisPilihan) {
        this.jenisPilihan = jenisPilihan;
    }

    public List<AgensiKutipanDokumen> getSenaraiDokumen() {
        return senaraiDokumen;
    }

    public void setSenaraiDokumen(List<AgensiKutipanDokumen> senaraiDokumen) {
        this.senaraiDokumen = senaraiDokumen;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        this.formatFile = formatFile;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
