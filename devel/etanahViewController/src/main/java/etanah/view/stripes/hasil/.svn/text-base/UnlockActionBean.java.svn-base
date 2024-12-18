/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

/**
 *
 * @author ezat.amir
 */
import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.DokumenKewanganSiriDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.DokumenKewanganSiri;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.Wizard;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import net.sourceforge.stripes.action.RedirectResolution;

//@Wizard(startEvents = {"view"})
@UrlBinding("/unlock")
public class UnlockActionBean extends AbleActionBean {

    private List<DokumenKewanganSiri> senarai;
    private DokumenKewanganSiri dokumenKewanganSiri;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodDaerah kodDaerah;
    @Inject
    KodCawangan kodCawangan;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    DokumenKewanganSiriDAO dokumenKewanganSiriDAO;
    @Inject 
    KutipanHasilManager manager;
    private static Logger LOG = Logger.getLogger(UnlockActionBean.class);
    private static boolean debug = LOG.isDebugEnabled();
    private String daerah;
    private String idKaunter;
    private String dikunci;
    private String cawangan;
    private String Caw;
    private String idSiri;

    @DefaultHandler
    public Resolution view() {
        LOG.info("INSIDE view");
        String sql = "select kd "
                + "from etanah.model.DokumenKewanganSiri kd "
                + "where kd.dikunci is not null";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        senarai = q.list();
        LOG.info(senarai.size());
    return new ForwardResolution("/WEB-INF/jsp/hasil/unlock.jsp");

    }

    public Resolution hapus() {

        
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        idKaunter = getContext().getRequest().getParameter("IdKaunter");
        idSiri = getContext().getRequest().getParameter("idSiri");
        dikunci = getContext().getRequest().getParameter("idDikunci");

        
        senarai = dokumenKewanganSiriDAO.findAll();
       
        String sql = "select kd "
                + "from etanah.model.DokumenKewanganSiri kd "
                + "where kd.dikunci is not null";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        senarai = q.list();
        LOG.info(senarai.size());
        
        dokumenKewanganSiri = dokumenKewanganSiriDAO.findById(Long.parseLong(idSiri) );
        if (dokumenKewanganSiri != null) {
            LOG.info("dokumenKewanganSiri != null");
            dokumenKewanganSiri.setDikunci(null);
            dokumenKewanganSiri.setKuncisiri(null);
            dokumenKewanganSiri.setTrhKunci(null);
            
            manager.saveOrUpdate(dokumenKewanganSiri);
        }
//        if ((idKaunter != null) && dikunci != null) {
//            DokumenKewanganSiri dks = dokumenKewanganSiriDAO.findById(Long.parseLong(idKaunter));
//            if (dks != null) {
//                kodCawangan = kodCawanganDAO.findById(Caw);
//
//
//                if (dikunci != null) {
//                    dks.setDikunci("");
//                }
//                dokumenKewanganSiriDAO.saveOrUpdate(dks);
//                LOG.info("nyainson mata aji smaringseng " + dks.getDikunci());
//
//            }
//        }
        return new RedirectResolution(UnlockActionBean.class);
    }

    public List<DokumenKewanganSiri> getSenarai() {
        return senarai;
    }

    public void setSenarai(List<DokumenKewanganSiri> senarai) {
        this.senarai = senarai;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getIdKaunter() {
        return idKaunter;
    }

    public void setIdKaunter(String idKaunter) {
        this.idKaunter = idKaunter;
    }

    public KodDaerah getKodDaerah() {
        return kodDaerah;
    }

    public void setKodDaerah(KodDaerah kodDaerah) {
        this.kodDaerah = kodDaerah;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public DokumenKewanganSiri getDokumenKewanganSiri() {
        return dokumenKewanganSiri;
    }

    public void setDokumenKewanganSiri(DokumenKewanganSiri dokumenKewanganSiri) {
        this.dokumenKewanganSiri = dokumenKewanganSiri;
    }

    public DokumenKewanganSiriDAO getDokumenKewanganSiriDAO() {
        return dokumenKewanganSiriDAO;
    }

    public void setDokumenKewanganSiriDAO(DokumenKewanganSiriDAO dokumenKewanganSiriDAO) {
        this.dokumenKewanganSiriDAO = dokumenKewanganSiriDAO;
    }

    public String getDiKunci() {
        return dikunci;
    }

    public void setDiKunci(String diKunci) {
        this.dikunci = diKunci;
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getCaw() {
        return Caw;
    }

    public void setCaw(String Caw) {
        this.Caw = Caw;
    }

    public String getDikunci() {
        return dikunci;
    }

    public void setDikunci(String dikunci) {
        this.dikunci = dikunci;
    }
}
