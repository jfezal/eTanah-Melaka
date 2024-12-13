/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.kaunter;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;

import etanah.dao.KodCawanganJabatanDAO;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.Penyerah;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.util.StringUtils;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
@HttpCache(allow = false)
@UrlBinding("/common/req_unit_info")
public class RequestUnitDalamInfo extends RequestPenyerahInfo {

    public String idUnit;
    public static final String DELIM = "__^$__";
    @Inject
    private KodCawanganJabatanDAO dao;
    private static final Logger LOG = Logger.getLogger(RequestUnitDalamInfo.class);
    private static final boolean debug = LOG.isDebugEnabled();
    private KodCawangan kodCawangan;

    @Override
    @DefaultHandler
    public Resolution findPenyerah() {
        NumberFormat formatter = new DecimalFormat("00000");
        if (debug) {
            idUnit = formatter.format(Integer.parseInt(idUnit));
            LOG.debug("idUnit =" + idUnit);
        }
        KodCawanganJabatan p = dao.findById(idUnit);
        String results = "";

        if ((p != null)) {
            if (debug) {
                LOG.debug("Unit Dalaman found!");
            }
            StringBuilder s = new StringBuilder();
            results = compose(p);
        } else {
            if (debug) {
                LOG.debug("Unit Dalaman not found");
            }
        }
        return new StreamingResolution("text/plain", results);
    }

    public void setCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    @Override
    public List<Penyerah> findPenyerahByParam(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where p.cawangan.kod = :kodCawangan");

        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and upper(p.nama) like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            query.append(" and p.kod = :id");
            //query.append(" and p.kod like :id");
        }

        query.append(" order by p.kod ASC");
//        query.append(" and p.aktif = 'Y'");

        Query q = sessionProvider.get().createQuery(query.toString()).setString("kodCawangan", kodCawangan.getKod());

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
    public void updateOrSave(KodCawanganJabatan p) {
        dao.saveOrUpdate(p);
    }

    public KodCawanganJabatan findById(Long id) {
        return dao.findById(String.valueOf(id));
    }

    public List<Penyerah> findAll() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where p.cawangan.kod = :kodCawangan");
        Query q = sessionProvider.get().createQuery(query.toString()).setString("kodCawangan", kodCawangan.getKod());
        return q.list();
    }

//    add by azri 9/7/2013: used in 'RequestPenyerahInfo.java' for "Unit Dalaman" && 'PTG' && N9
//  @Override
    public List<Penyerah> findPenyerahByParamAllKodCaw(Map<String, String[]> map) {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where p.aktif = 'Y' ");
        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            query.append(" and upper(p.nama) like :nama");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            //query.append(" and p.kod = :id");
            query.append(" and p.kod like :id");
        }
        query.append(" order by p.kod ASC");
        Query q = sessionProvider.get().createQuery(query.toString());
        if (StringUtils.isNotBlank(map.get("namaPenyerah"))) {
            q.setString("nama", map.get("namaPenyerah")[0].toUpperCase() + "%");
        }
        if (StringUtils.isNotBlank(map.get("idPenyerah"))) {
            //q.setString("id", "%" + map.get("idPenyerah")[0]);
            q.setString("id", "%" + map.get("idPenyerah")[0] + "%");
        }
        return q.list();
    }

    public List<Penyerah> findAllKodCaw() {
        StringBuilder query = new StringBuilder();
        query.append("select p from etanah.model.KodCawanganJabatan p where p.cawangan.kod = :kodCawangan");
        Query q = sessionProvider.get().createQuery(query.toString()).setString("kodCawangan", kodCawangan.getKod());
        return q.list();
    }//    add by azri 9/7/2013: end


    public KodCawanganJabatan findKod(String kod) {

        NumberFormat formatter = new DecimalFormat("00000");
        if (debug) {
            LOG.debug("kod =" + kod);
            kod = formatter.format(Integer.parseInt(kod));
            LOG.debug("kod11 =" + kod);
        }
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodCawanganJabatan lt where lt.kod = :kod");
        q.setString("kod", kod);
        return (KodCawanganJabatan) q.uniqueResult();
    }
}
