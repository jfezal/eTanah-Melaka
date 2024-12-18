package etanah.view.utility;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;

import etanah.dao.KodJadualKeduaDAO;
import etanah.model.KodJadualKedua;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.AbleActionBean;
import org.apache.log4j.Logger;

@UrlBinding("/utiliti/kod_jadual_kedua")
public class CarianKodJadualKedua extends AbleActionBean {

    
    private String kod;
    private String nama;
    private List<KodJadualKedua> list;
    private String kodNegeri;
    private static final Logger LOG = Logger.getLogger(CarianKodJadualKedua.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
    @Inject
    private KodJadualKeduaDAO dao;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<KodJadualKedua> getList() {
        return list;
    }

    @HandlesEvent("cari")
    @DefaultHandler
    public Resolution cari() {
        LOG.info(kod);
        LOG.info(nama);        

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Keenam";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "Kedua";            
        }
        LOG.info("==================kod negeri= "+kodNegeri); 
        
        StringBuffer hql = new StringBuffer();
        if (kod != null && kod.trim().length() > 0) {
            hql.append("jk.kod like :kod ");
        }
        if (nama != null && nama.trim().length() > 0) {
            if (hql.length() > 0) {
                hql.append("and ");
            }
            hql.append("jk.nama like :nama ");
        }
        if (hql.length() > 0) {
            hql.insert(0, "from KodJadualKedua jk where ");
            Session s = sessionProvider.get();
            Query q = s.createQuery(hql.toString());
            if (kod != null && kod.trim().length() > 0) {
                q.setString("kod", "%"+kod+"%");
            }
            if (nama != null && nama.trim().length() > 0) {
                q.setString("nama", "%" + nama + "%");
            }
            list = q.list();
        } else {
            list = dao.findAll();
        }

        return new ForwardResolution("/WEB-INF/jsp/utiliti/kod_jadual_kedua.jsp");
    }

    
    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
