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

import etanah.dao.JabatanKerajaanDAO; 
import etanah.model.JabatanKerajaan; 
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
@UrlBinding("/common/req_jntnKjaan_info")
public class RequestJbtnKerajaanInfo extends RequestPenyerahInfo {

    public String idJK;
    public static final String DELIM = "__^$__";
    @Inject
    private JabatanKerajaanDAO dao; 
    private static final Logger LOG = Logger.getLogger(RequestJbtnKerajaanInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution findPenyerah() {
        if (debug) {
            LOG.debug("id Jabatan Kerajaan = " + idJK);
        }

        JabatanKerajaan p = dao.findById(Long.valueOf(idJK));
        String results = "";

        if (p != null) {
            if (debug) {
                LOG.debug("Jabatan Kerajaan found!");
            }
            StringBuilder s = new StringBuilder();
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("Jabatan Kerajaan not found");
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JabatanKerajaan p where 1=1"); 

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and p.nama like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.idJabatanKerajaan = :id");
            //query.append(" and p.idJabatanKerajaan like :id");
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
    public void updateOrSave(JabatanKerajaan p) { 
        dao.saveOrUpdate(p);
    }

    public JabatanKerajaan findById(Long id) {
        return dao.findById(id);
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.JabatanKerajaan p order by p.idJabatanKerajaan asc"); 
        //query.append("select p from etanah.model.JabatanKerajaan p where 1=1"); 
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}

