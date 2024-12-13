package etanah.view.kaunter;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.*;
import etanah.model.*;
import etanah.util.StringUtils;
import java.util.*;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author haqqiem
 */
@HttpCache(allow = false)
@UrlBinding("/common/req_lelong_info")
public class RequestJurulelongInfo extends RequestPenyerahInfo {

    public String idAgensi;
    public static final String DELIM = "__^$__";
    @Inject
    private JuruLelongDAO dao;
    private static final Logger LOG = Logger.getLogger(RequestUnitDalamInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Override
    @DefaultHandler
    public Resolution findPenyerah() {
        if (debug) {
            LOG.debug("idUnit =" + idAgensi);
        }
        JuruLelong p = dao.findById(Long.parseLong(idAgensi));
        String results = "";

        //filter penyerah yg active
        if ((p != null) && (p.getAktif() == 'Y')) {
            if (debug) {
                LOG.debug("Penyerah found!");
            }
            StringBuilder s = new StringBuilder();
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("Penyerah not found");
            }
        }
        return new StreamingResolution("text/plain", results);
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JuruLelong p where 1=1");

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and upper(p.nama) like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.idJlb like :id");
        }

//        query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            q.setString("id", map.get("idPenyerah")[0] + "%");
        }

        return q.list();
    }

    @Transactional
    public void updateOrSave(JuruLelong p) {
        dao.saveOrUpdate(p);
    }

    public JuruLelong findById(Long id) {
        return dao.findById(id);
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodAgensi p where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}
