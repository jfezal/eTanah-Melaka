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

import etanah.dao.KodCawanganJabatanDAO;
import etanah.model.KodCawanganJabatan;
import etanah.model.Penyerah;
import etanah.util.StringUtils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.hibernate.Query;

@HttpCache(allow = false)
@UrlBinding("/common/req_uu_info")
public class RequestUnitDalamanInfo extends RequestPenyerahInfo {

    public String idUU;
    public static final String DELIM = "__^$__";
    @Inject
    private KodCawanganJabatanDAO dao;
    private static final Logger LOG = Logger.getLogger(RequestUnitDalamanInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();

    @DefaultHandler
    public Resolution findPenyerah() {
        NumberFormat formatter = new DecimalFormat("00000");
        if (debug) {
            idUU = formatter.format(Integer.parseInt(idUU));
            LOG.debug("idUnit =" + idUU);
        }
        KodCawanganJabatan p = dao.findById(idUU);
        String results = "";


        if (p != null) {
            if (debug) {
                LOG.debug("UU found!");
            }
            StringBuilder s = new StringBuilder();
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("UU not found");
            }
        }

        return new StreamingResolution("text/plain", results);
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where 1=1");

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and p.nama like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.kod like :id");
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
    public void updateOrSave(KodCawanganJabatan p) {
        dao.saveOrUpdate(p);
    }

    public KodCawanganJabatan findById(Long id) {
        return dao.findById(String.valueOf(id));
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where 1=1");
        Query q = sessionProvider.get().createQuery(query.toString());
        return q.list();
    }
}