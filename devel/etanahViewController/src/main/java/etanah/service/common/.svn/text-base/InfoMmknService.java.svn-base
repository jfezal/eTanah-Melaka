/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.InfoMmknDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanPihakHubunganDAO;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakHubungan;
import etanah.model.PermohonanPihakKemaskini;
import etanah.model.Pihak;
import etanah.model.InfoMmkn;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math.fraction.Fraction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author zipzap
 */
public class InfoMmknService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    InfoMmknDAO infoMmknDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanPihakHubunganDAO permohonanPihakHubunganDAO;
    @Inject
    private PermohonanDAO permohonanDAO;

    public InfoMmkn findByIdMohonAndKodPeringkatRKMMK(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.InfoMmkn m WHERE m.permohonan.idPermohonan = :idMohon AND m.kodPeringkat.kod in ('RKMMK')");
        q.setParameter("idMohon", idMohon);
//        q.setParameter("kodPeringkat", kodPeringkat);
        return (InfoMmkn) q.uniqueResult();
    }
    public InfoMmkn findByIdMohonAndKodPeringkat(String idMohon, String kodPeringkat) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.InfoMmkn m WHERE m.permohonan.idPermohonan = :idMohon AND m.kodPeringkat.kod = :kodPeringkat");
        q.setParameter("idMohon", idMohon);
        q.setParameter("kodPeringkat", kodPeringkat);
        return (InfoMmkn) q.uniqueResult();
    }

    public Permohonan findMohonActive(String idPermohonan) {
        String query = "Select h from etanah.model.Permohonan h where h.idPermohonan = :idPermohonan" + " and h.status in ('TS','TA','AK','GB','TP', 'SS')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    @Transactional
    public InfoMmkn saveOrUpdate(InfoMmkn infoMmkn) {
        infoMmkn = infoMmknDAO.saveOrUpdate(infoMmkn);
        return infoMmkn;
    }

    @Transactional
    public void simpanInfoMMKN(InfoMmkn infoMmkn) {
        infoMmknDAO.saveOrUpdate(infoMmkn);
    }

    public Provider<Session> getSessionProvider() {
        return sessionProvider;
    }

    public void setSessionProvider(Provider<Session> sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public PermohonanPihakDAO getPermohonanPihakDAO() {
        return permohonanPihakDAO;
    }

    public void setPermohonanPihakDAO(PermohonanPihakDAO permohonanPihakDAO) {
        this.permohonanPihakDAO = permohonanPihakDAO;
    }

    public PemohonDAO getPemohonDAO() {
        return pemohonDAO;
    }

    public void setPemohonDAO(PemohonDAO pemohonDAO) {
        this.pemohonDAO = pemohonDAO;
    }

    public PermohonanPihakHubunganDAO getPermohonanPihakHubunganDAO() {
        return permohonanPihakHubunganDAO;
    }

    public void setPermohonanPihakHubunganDAO(PermohonanPihakHubunganDAO permohonanPihakHubunganDAO) {
        this.permohonanPihakHubunganDAO = permohonanPihakHubunganDAO;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }
}
