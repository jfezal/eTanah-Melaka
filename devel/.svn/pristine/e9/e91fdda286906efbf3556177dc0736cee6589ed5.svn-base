package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import java.util.*;
import net.sourceforge.stripes.action.*;
import org.hibernate.*;


@Wizard(startEvents = {"Step1"})
@UrlBinding("/hasil/delete_bilCukai_strata")
public class UtilitiDeleteBilCukaiStrataActionBean extends AbleActionBean {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UtilitiDeleteBilCukaiStrataActionBean.class);
    private static final String DEFAULT_VIEW = "/WEB-INF/jsp/hasil/delete_bil_cukai_strata.jsp";

    private String daerah = "";
    private String year = "";
    private String idBilCukai = "";
    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();
    private List<BilCukai> senaraiBilCukai = new ArrayList<BilCukai>();

    private int tahun = 0;

    @Inject
    private etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private BilCukaiDAO bilCukaiDAO;
    @Inject
    private KutipanHasilManager manager;

    @HandlesEvent("Step1")
    @DefaultHandler
    public Resolution showForm() {
        logger.info("Step1");

        tahun = new Date().getYear() + 1900;
        String sql = "SELECT d FROM KodDaerah d WHERE d.aktif = 'Y' ORDER BY d.nama";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);

        senaraiDaerah = q.list();
        logger.info("senaraiDaerah.size() : " + senaraiDaerah.size());

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step2")
    public Resolution search() {
        logger.info("Step2");
        tahun = new Date().getYear() + 1900;

        String sql = "SELECT d FROM BilCukai d WHERE d.untukTahun = :year AND d.kodCawangan.kod = :caw AND d.jenisCukai = 'S'";
        Session s = sessionProvider.get();
        Query q = s.createQuery(sql);
        q.setString("year", year);
        q.setString("caw", daerah);

        senaraiBilCukai = q.list();

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @HandlesEvent("Step3")
    public Resolution delete() {
        logger.info("Step3");
        logger.info("idBilCukai : " + idBilCukai);

        Session session = sessionProvider.get();
        Transaction tx = session.beginTransaction();

        tahun = new Date().getYear() + 1900;
        try {
            BilCukai bil = bilCukaiDAO.findById(Long.parseLong(idBilCukai));
            if (bil != null) {
                manager.delete(bil);
            }
            tx.commit();
            addSimpleMessage("Data telah Berjaya dipadam.");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            tx.rollback();
            addSimpleError("Data tidak berjaya dipadam.");
        }

        return new ForwardResolution(DEFAULT_VIEW);
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public List<KodDaerah> getSenaraiDaerah() {
        return senaraiDaerah;
    }

    public void setSenaraiDaerah(List<KodDaerah> senaraiDaerah) {
        this.senaraiDaerah = senaraiDaerah;
    }

    public List<BilCukai> getSenaraiBilCukai() {
        return senaraiBilCukai;
    }

    public void setSenaraiBilCukai(List<BilCukai> senaraiBilCukai) {
        this.senaraiBilCukai = senaraiBilCukai;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIdBilCukai() {
        return idBilCukai;
    }

    public void setIdBilCukai(String idBilCukai) {
        this.idBilCukai = idBilCukai;
    }
}
