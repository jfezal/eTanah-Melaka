package etanah.view.kaunter;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.PeguamDAO;
import etanah.model.Peguam;
import etanah.model.Penyerah;
import etanah.util.StringUtils;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.hibernate.Query;

@HttpCache(allow = false)
@UrlBinding("/common/req_peguam_info")
public class RequestPeguamInfo extends RequestPenyerahInfo {

    public String idPeguam;
    public static final String DELIM = "__^$__";
    @Inject
    private PeguamDAO dao;
    private static final Logger LOG = Logger.getLogger(RequestPeguamInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution findPenyerah() {
        if (debug) {
            LOG.debug("idPeguam=" + idPeguam);
        }

        Peguam p = dao.findById(Long.valueOf(idPeguam));
        String results = "";

        if ((p != null)) {
            if (debug) {
                LOG.debug("Peguam found!");
            }
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("Peguam not found");
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {

        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.Peguam p where 1=1");
        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and p.nama like :nama");
            LOG.debug("------BACA PARAM 1");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.idPeguam = :id");
            //query.append(" and p.idPeguam like :id");
        }
        query.append(" order by p.idPeguam ASC");
        //query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
//            LOG.debug("-----BACA BAWAH");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            q.setString("id", map.get("idPenyerah")[0]);
            //q.setString("id", map.get("idPenyerah")[0] + "%");
        }

        return q.list();
    }

        public List<Penyerah> findPenyerahByParam2(Map<String, String[]> map) {

        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.Peguam p where 1=1");
        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and p.nama like :nama");
            LOG.debug("------BACA PARAM 2");
        }

        //query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
//            LOG.debug("-----BACA BAWAH");
        }

        return q.list();
    }
        
    @Transactional
    public void updateOrSave(Peguam p) {
        dao.saveOrUpdate(p);
    }

    public Peguam findById(Long id) {
        return dao.findById(id);
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.Peguam p order by p.idPeguam asc");
        //query.append("select p from etanah.model.Peguam p where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}
