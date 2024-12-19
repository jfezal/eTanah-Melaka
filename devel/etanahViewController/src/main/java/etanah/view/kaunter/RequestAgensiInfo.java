/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.KodAgensiDAO;
import etanah.model.KodAgensi;
import etanah.model.Penyerah;
import etanah.util.StringUtils;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.log4j.Logger;
import org.hibernate.Query;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@UrlBinding("/common/req_agensi_info")
public class RequestAgensiInfo extends RequestPenyerahInfo {

    public String idAgensi;
    public static final String DELIM = "__^$__";
    @Inject
    private KodAgensiDAO dao;
    private static final Logger LOG = Logger.getLogger(RequestAgensiInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @Override
    @DefaultHandler
    public Resolution findPenyerah() {
        if (debug) {
            LOG.debug("idAgensi =" + idAgensi);
        }
        KodAgensi p = dao.findById(idAgensi);
        String results = "";
        
        if ((p != null)) {
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
        query.append("select p from etanah.model.KodAgensi p where 1=1");

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and upper(p.nama) like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.kod = :id");
            //query.append(" and p.kod like :id");
        }
        
        query.append(" and p.kategoriAgensi is null ");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            q.setString("id", map.get("idPenyerah")[0]);
        }

        return q.list();
    }

    @Transactional
    public void updateOrSave(KodAgensi p) {
        dao.saveOrUpdate(p);
    }

    public KodAgensi findById(Long id) {
        return dao.findById(String.valueOf(id));
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodAgensi p where p.kategoriAgensi is null order by p.kod asc");
        //query.append("select p from etanah.model.KodAgensi p where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}
