/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikUrusanDAO;
import etanah.model.HakmilikUrusan;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class HakmilikUrusanServiceN {

    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(HakmilikUrusanServiceN.class);

    public List<HakmilikUrusan> searchHakmilikUrusanGadaian(String kodUrusan, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.aktif ='Y' and h.hakmilik.idHakmilik = :idhakmilik ";
        if (kodUrusan.equals("GDL")) {
            query = query + "and h.kodUrusan.kod in ('GDCE','GD','GDPJ')";
        }
        if (kodUrusan.equals("PJSB")) {
            query = query + "and h.kodUrusan.kod in ('PJT','PJTM','PJKT','PJBT','PMP','PMPJK')";
        }
        if (kodUrusan.equals("ISL")) {
            query = query + "and h.kodUrusan.kod in ('IS','ISBLS')";
        }


//        Urusan melibatkan pembatalan nota
        if (kodUrusan.equals("HLTEB")) {
            query = query + "and h.kodUrusan.kod in ('HLTE')";
        }
        if (kodUrusan.equals("HTT")) {
            query = query + "and h.kodUrusan.kod in ('HTB')";
        }
        if (kodUrusan.equals("HLLB")) {
            query = query + "and h.kodUrusan.kod in ('HLLS','HLLA')";
        }
        if (kodUrusan.equals("N8AB")) {
            query = query + "and h.kodUrusan.kod in ('N8A')";
        }
        if (kodUrusan.equals("N7AB")) {
            query = query + "and h.kodUrusan.kod in ('N7A')";
        }
        if (kodUrusan.equals("N6AB")) {
            query = query + "and h.kodUrusan.kod in ('N7A')";
        }
        if (kodUrusan.equals("ABTB")) {
            query = query + "and h.kodUrusan.kod in ('ABT','ABT-K','ABT-D','ABTKB','ABTS')";
        }
        if (kodUrusan.equals("CB")||kodUrusan.equals("CMB")) {
            query = query + "and h.kodUrusan.kod in ('CM')";
        }
        if (kodUrusan.equals("IRMB")) {
            query = query + "and h.kodUrusan.kod in ('IRM')";
        }
        if (kodUrusan.equals("ITBB")) {
            query = query + "and h.kodUrusan.kod in ('ITB','PTB')";
        }
        if (kodUrusan.equals("PBB")) {
            query = query + "and h.kodUrusan.kod in ('PBM')";
        }
        if (kodUrusan.equals("PBBB")) {
            query = query + "and h.kodUrusan.kod in ('PBBM')";
        }
        if (kodUrusan.equals("PBSCB")) {
            query = query + "and h.kodUrusan.kod in ('PBSCM')";
        }
        if (kodUrusan.equals("PSB")) {
            query = query + "and h.kodUrusan.kod in ('PSM')";
        }
        if (kodUrusan.equals("PSKB")) {
            query = query + "and h.kodUrusan.kod in ('PSKM')";
        }
        if (kodUrusan.equals("PSKSB")) {
            query = query + "and h.kodUrusan.kod in ('PSKSM')";
        }
        if (kodUrusan.equals("PSTSB")) {
            query = query + "and h.kodUrusan.kod in ('PSTSM')";
        }
        if (kodUrusan.equals("SBSTB")) {
            query = query + "and h.kodUrusan.kod in ('SBSTM')";
        }
        if (kodUrusan.equals("SBTB")) {
            query = query + "and h.kodUrusan.kod in ('SBTM')";
        }
        if (kodUrusan.equals("SSKPB")) {
            query = query + "and h.kodUrusan.kod in ('SSKPM')";
        }
        if (kodUrusan.equals("TSB")) {
            query = query + "and h.kodUrusan.kod in ('TSM')";
        }
        if (kodUrusan.equals("TSSKB")||kodUrusan.equals("TSSKL")) {
            query = query + "and h.kodUrusan.kod in ('TSSKM')";
        }
        if (kodUrusan.equals("HMVB")) {
            query = query + "and h.kodUrusan.kod in ('HMV')";
        }
        if (kodUrusan.equals("IGSAB")) {
            query = query + "and h.kodUrusan.kod in ('IGSA5','IGSA6','IGSA')";
        }
        if (kodUrusan.equals("IPMB")) {
            query = query + "and h.kodUrusan.kod in ('IPM')";
        }
        if (kodUrusan.equals("TBTWB")) {
            query = query + "and h.kodUrusan.kod in ('TBTW')";
        }

        LOGGER.info("query ::" + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idhakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public void saveOrUpdate(List<HakmilikUrusan> hakmilikUrusanList) {
        for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
            hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
        }
    }

    public HakmilikUrusan findById(Long id) {
        return hakmilikUrusanDAO.findById(id);
    }

    public HakmilikUrusan findByIdPerserahan(String idPerserahan) {
        LOGGER.info("findByIdPerserahan :: start");
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPerserahan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPerserahan", idPerserahan);
        LOGGER.info("query ::" + query);
        LOGGER.info("findByIdPerserahan :: finish");
        return (HakmilikUrusan) q.uniqueResult();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByIdHakmilik(String idHakmilik){
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
}
