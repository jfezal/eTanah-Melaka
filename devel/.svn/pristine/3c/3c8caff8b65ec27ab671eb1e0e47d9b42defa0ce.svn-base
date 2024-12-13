/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

/**
 *
 * @author khairul.hazwan
 */

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.PerbadananPengurusanDAO; 
import etanah.model.PerbadananPengurusan; 
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
@UrlBinding("/common/req_bdnUrus_info")
public class RequestBdnUrusInfo extends RequestPenyerahInfo {

    public String idBdnUrus;
    public static final String DELIM = "__^$__";
    @Inject
    private PerbadananPengurusanDAO dao; 
    private static final Logger LOG = Logger.getLogger(RequestBdnUrusInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution findPenyerah() {
        if (debug) {
            LOG.debug("id Badan Pengurusan = " + idBdnUrus);
        }

        PerbadananPengurusan p = dao.findById(Long.valueOf(idBdnUrus));
        String results = "";

        if (p != null) {
            if (debug) {
                LOG.debug("Badan Pengurusan found!");
            }
            StringBuilder s = new StringBuilder();
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("Badan Pengurusan not found");
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.PerbadananPengurusan p where 1=1"); 

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and p.nama like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.idPerbadananPengurusan = :id");
            //query.append(" and p.idPerbadananPengurusan like :id");
        }

//        query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString());

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            q.setString("id", map.get("idPenyerah")[0]);
            //q.setString("id", map.get("idPenyerah")[0] + "%");
        }

        return q.list();
    }

    @Transactional
    public void updateOrSave(PerbadananPengurusan p) { 
        dao.saveOrUpdate(p);
    }

    public PerbadananPengurusan findById(Long id) { 
        return dao.findById(id);
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();       
        query.append("select p from etanah.model.PerbadananPengurusan p order by p.idPerbadananPengurusan asc"); 
        //query.append("select p from etanah.model.PerbadananPengurusan p where 1=1"); 
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}
