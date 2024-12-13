/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.portal.ws.service;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.PortalTransaksi;
import etanah.view.etanahContextListener;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author john
 */
public class UtilCarianService {

    private static Injector injector = etanahContextListener.getInjector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    public Hakmilik cariHakmilikByNoLotPlot(String daerah, String bpm, String seksyen, String kodLot, String noLot) {
        String query = "SELECT h FROM etanah.model.Hakmilik h"
                + " where h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm "
                + "and h.idHakmilikInduk is null ";
        query += StringUtils.isNotBlank(seksyen) ? "and h.seksyen.kod = :seksyen " : "";
        query += "and h.lot.kod = :kodLot "
                + "and h.noLot = :noLot "
                + "and h.kodStatusHakmilik= 'D'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bpm", bpm);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        }
        q.setString("kodLot", kodLot);
        q.setString("noLot", noLot);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik checkAccountByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik) {
        String query = "SELECT h FROM etanah.model.Hakmilik h"
                + " where h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm "
                + "and h.idHakmilikInduk is null ";
        query += StringUtils.isNotBlank(seksyen) ? "and h.seksyen.kod = :seksyen " : "";
        query += "and h.kodHakmilik.kod = :kodJenishakmilik "
                + "and h.noHakmilik = :noHakmilik "
                + "and h.kodStatusHakmilik= 'D'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bpm", bpm);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        }
        q.setString("kodJenishakmilik", kodJenishakmilik);
        q.setString("noHakmilik", noHakmilik);
        return (Hakmilik) q.uniqueResult();
    }

    public Hakmilik checkAccountStrataByParam(String daerah, String bpm, String seksyen, String kodJenishakmilik, String noHakmilik, String noBangunan, String noTingkat, String noPetak) {
        String query = "SELECT h FROM etanah.model.Hakmilik h"
                + " where h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm ";
        query += StringUtils.isNotBlank(seksyen) ? "and h.seksyen.kod = :seksyen " : "";
        query += "and h.kodHakmilik.kod = :kodJenishakmilik "
                + "and h.noHakmilik = :noHakmilik "
                + "and h.noBangunan = :noBangunan "
                + "and h.noTingkat = :noTingkat "
                + "and h.noPetak = :noPetak "
                + "and h.kodStatusHakmilik= 'D'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bpm", bpm);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        }
        q.setString("kodJenishakmilik", kodJenishakmilik);
        q.setString("noHakmilik", noHakmilik);
        q.setString("noBangunan", noBangunan);
        q.setString("noTingkat", noTingkat);
        q.setString("noPetak", noPetak);
        return (Hakmilik) q.uniqueResult();
    }

    public PortalTransaksi findByNofpx(String noFpx) {
        String query = "SELECT h FROM etanah.model.PortalTransaksi h"
                + " where h.noFpx = :noFpx ";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("noFpx", noFpx);
        return (PortalTransaksi) q.uniqueResult();
    }
    
    public PortalTransaksi findByNoresit(String PAYMENT_TRANS_ID, String jenisTrans) {
        String query = "SELECT a FROM etanah.model.PortalTransaksi a"
                + " where a.noResit = :noResit and a.jenisTrans = :jenisTrans";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("jenisTrans", jenisTrans);
        q.setString("noResit", PAYMENT_TRANS_ID);
        return (PortalTransaksi) q.uniqueResult();
    }

    public Hakmilik cariHakmilikStrataByNoLotPlot(String daerah, String bpm, String seksyen, String kodLot, String noLot, String noBangunan, String noTingkat, String noPetak) {
        String query = "SELECT h FROM etanah.model.Hakmilik h"
                + " where h.daerah.kod = :daerah "
                + "and h.bandarPekanMukim.kod = :bpm ";
        query += StringUtils.isNotBlank(seksyen) ? "and h.seksyen.kod = :seksyen " : "";
        query += "and h.lot.kod = :kodLot "
                + "and h.noLot = :noLot "
                + "and h.noBangunan = :noBangunan "
                + "and h.noTingkat = :noTingkat "
                + "and h.noPetak = :noPetak "
                + "and h.kodStatusHakmilik= 'D'";
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
        q.setString("daerah", daerah);
        q.setString("bpm", bpm);
        if (StringUtils.isNotBlank(seksyen)) {
            q.setString("seksyen", seksyen);
        }
        q.setString("kodLot", kodLot);
        q.setString("noLot", noLot);
        q.setString("noBangunan", noBangunan);
        q.setString("noTingkat", noTingkat);
        q.setString("noPetak", noPetak);
        return (Hakmilik) q.uniqueResult();
    }
    
    
    public List<HakmilikPihakBerkepentingan> listPB(String idHakmilik){
     String query = "SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h"
                + " where h.hakmilik.idHakmilik = :idHakmilik and h.aktif = 'Y' "
             + "and h.jenis.kod ='PM'";
        
        Session session = injector.getProvider(Session.class).get();
        Query q = session.createQuery(query);
                q.setString("idHakmilik", idHakmilik);

        return q.list();
    }
}
