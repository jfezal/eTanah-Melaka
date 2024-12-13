package etanah.view.stripes.hasil;

import java.util.*;
import etanah.dao.*;
import etanah.model.*;
import org.hibernate.*;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import java.text.SimpleDateFormat;
import able.stripes.AbleActionBean;
import net.sourceforge.stripes.action.*;

/**
 *
 * @author haqqiem
 */
@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/release")
public class ReleaseResitActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(ReleaseResitActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/release.jsp";
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    
    private DokumenKewanganSiri dokumenKewanganSiri;
    private DokumenKewangan dokumenKewangan;
    private Pengguna pengguna;
    private boolean flag = false;
    private String nota = ""; 
    private static String kodNegeri;
    private static int running = 0;
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KutipanHasilManager manager;
    @Inject
    DokumenKewanganSiriDAO dokumenKewanganSiriDAO;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        if("04".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "melaka";
        }if("05".equals(conf.getProperty("kodNegeri"))){
            kodNegeri = "negeriSembilan";
        }
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @HandlesEvent("Step2")
    public Resolution search() {      
        String sub = "";
        Date now = new Date();
        String trh = sdf.format(now);
        pengguna = penggunaDAO.findById(pengguna.getIdPengguna());
        if(kodNegeri.equalsIgnoreCase("melaka")){
            sub = pengguna.getKodCawangan().getKod().charAt(1) +
                     pengguna.getKodCawangan().getKod() + pengguna.getIdKaunter()+trh;
        }else{
            sub = trh+pengguna.getKodCawangan().getKod() + "1" + pengguna.getIdKaunter();
        }
        logger.info("tarikh : "+trh);
        logger.info("pengguna : "+pengguna.getIdPengguna());
        logger.info("pengguna.getKodCawangan().getKod() : "+pengguna.getKodCawangan().getKod());
        logger.info("sub : "+sub);

        String s = "SELECT a FROM etanah.model.DokumenKewanganSiri a WHERE a.idKaunter = :kaunter AND a.cawangan.kod = :caw ";
        Query q = sessionProvider.get().createQuery(s);
            q.setString("kaunter", pengguna.getIdKaunter());
            q.setString("caw", pengguna.getKodCawangan().getKod());
        dokumenKewanganSiri = (DokumenKewanganSiri) q.uniqueResult();
        
        String sResit = "SELECT dk FROM etanah.model.DokumenKewangan dk "
                        + "WHERE dk.idDokumenKewangan LIKE :idResit "
                        + "ORDER BY dk.idDokumenKewangan DESC";
        Query qResit = sessionProvider.get().createQuery(sResit);
            qResit.setString("idResit", sub+"%");
        List<DokumenKewangan> senaraiResit = qResit.list();
        logger.info(senaraiResit.size());
        
        if(senaraiResit.size()>0){
            dokumenKewangan = senaraiResit.get(0);
            int l = dokumenKewangan.getIdDokumenKewangan().length();
            String runningNumber = dokumenKewangan.getIdDokumenKewangan().substring(l-4, l);
            logger.info(runningNumber);
            running = Integer.parseInt(runningNumber);
            logger.info(running);
            if(running != dokumenKewanganSiri.getNoSiri()){
                flag = true;
            }else{
                addSimpleError("ID pengguna "+pengguna.getIdPengguna() +" tidak boleh dikemaskini.");
            }
        }
        
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    @HandlesEvent("Step3")
    public Resolution update() {
        logger.info("running : "+running);
        logger.info("kew_dok_siri : "+dokumenKewanganSiri.getIdSiri());
        
        dokumenKewanganSiri = dokumenKewanganSiriDAO.findById(dokumenKewanganSiri.getIdSiri());
        dokumenKewanganSiri.setNoSiri(running);
        
        manager.saveOrUpdate(dokumenKewanganSiri);
        
        addSimpleMessage("Maklumat Telah Berjaya Dikemaskini");
        return new ForwardResolution(DEFAULT_VIEW);
    }
    
    public DokumenKewanganSiri getDokumenKewanganSiri() {
        return dokumenKewanganSiri;
    }

    public void setDokumenKewanganSiri(DokumenKewanganSiri dokumenKewanganSiri) {
        this.dokumenKewanganSiri = dokumenKewanganSiri;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public DokumenKewangan getDokumenKewangan() {
        return dokumenKewangan;
    }

    public void setDokumenKewangan(DokumenKewangan dokumenKewangan) {
        this.dokumenKewangan = dokumenKewangan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public int getRunning() {
        return running;
    }

    public void setRunning(int running) {
        this.running = running;
    }
}
