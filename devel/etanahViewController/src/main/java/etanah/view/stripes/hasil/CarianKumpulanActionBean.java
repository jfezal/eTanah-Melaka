package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.*;
import java.util.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author haqqiem
 *  11 Oct 11
 *
 */
@UrlBinding("/hasil/carian_kumpulan")
public class CarianKumpulanActionBean extends AbleActionBean{
    private static final Logger logger = Logger.getLogger(CarianKumpulanActionBean.class);
    private static final boolean debug = logger.isDebugEnabled();

    private static final String POPUP_VIEW = "/WEB-INF/jsp/hasil/popup_carian_kumpulan.jsp";
    private String idKump = null;
    private String namaKump = null;
    private String daerah = null;
    private List<KodDaerah> senaraiDaerah = new ArrayList<KodDaerah>();

    private List<KumpulanAkaun> senaraiKumpulan = new ArrayList<KumpulanAkaun>();


    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @DefaultHandler
    public Resolution search() {
        String sql = "select d from KodDaerah d where d.aktif = 'Y' order by d.nama";
        Session s = sessionProvider.get();
        Query q1 = s.createQuery(sql);

        senaraiDaerah = q1.list();
        logger.info("senaraiDaerah.size() : " + senaraiDaerah.size());
        if (idKump != null) {
            logger.info(".:: idKump ::.");

            String query = "SELECT a FROM etanah.model.KumpulanAkaun a WHERE a.idKumpulan = :kump";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kump", idKump);
            senaraiKumpulan = q.list();
        } else if (namaKump != null) {
            logger.info(".:: namaKump ::.");
            
            String query = "SELECT a FROM etanah.model.KumpulanAkaun a WHERE a.catatan LIKE :nama";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("nama", "%"+namaKump+"%");
            senaraiKumpulan= q.list();
        } else {
            logger.info("masuk else");
            searchingIdKumpulan(getContext().getRequest().getParameterMap());
        }
        logger.info("senaraiKumpulan.size() : "+senaraiKumpulan.size());
        return new ForwardResolution(POPUP_VIEW).addParameter("popup", "true");
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public void searchingIdKumpulan(Map<String, String[]> param){
        Session s = sessionProvider.get();
        String query = "SELECT ka FROM etanah.model.KumpulanAkaun ka WHERE ka.catatan IS NOT NULL ";

        if (isNotBlank(param.get("idKump"))) {
            query += "AND ka.idKumpulan = :id ";
        }

        if (isNotBlank(param.get("namaKump"))) {
            query += "AND ka.catatan LIKE :nama ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND ka.cawangan.kod LIKE :daerah ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idKumpulan"))) {
            q.setString("id", param.get("idKumpulan")[0]);
        }

        if (isNotBlank(param.get("namaKumpulan"))) {
            q.setString("nama", "%"+param.get("namaKumpulan")[0]+"%");
        }

        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", "%"+param.get("daerah")[0]+"%");
        }

        senaraiKumpulan = q.list();
    }

    public Resolution passIdKumpulan() {
        String nomborAkaun = getContext().getRequest().getParameter("id");
        String results = nomborAkaun;
        return new StreamingResolution("text/plain", results);
    }
    
    public Resolution popupCarianKumpulan(){
        logger.debug(".:: Inside popupCarianKumpulan ::.");
        return new ForwardResolution(POPUP_VIEW).addParameter("popup", "true");
    }

    public List<KumpulanAkaun> getSenaraiKumpulan() {
        return senaraiKumpulan;
    }

    public void setSenaraiKumpulan(List<KumpulanAkaun> senaraiKumpulan) {
        this.senaraiKumpulan = senaraiKumpulan;
    }

    public String getIdKump() {
        return idKump;
    }

    public void setIdKump(String idKump) {
        this.idKump = idKump;
    }

    public String getNamaKump() {
        return namaKump;
    }

    public void setNamaKump(String namaKump) {
        this.namaKump = namaKump;
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
}
