/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import java.text.ParseException;
import java.util.List;
//import etanah.util.StringUtils;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import org.apache.log4j.Logger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import etanah.dao.UlasanJabatanTeknikalDAO;
import etanah.model.KonfigurasiSistem;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.UlasanJabatanTeknikal;

/**
 *
 * @author Shazwan
 */
public class JTeknikalService {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    UlasanJabatanTeknikalDAO ulasanJabatanTeknikalDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    
    /*
     * FINDING METHOD
     */
    public List<PermohonanRujukanLuar> findMohonRujLuar(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }
    public KonfigurasiSistem findKonfigSistemByNama(String nama) {
        String query = "Select p FROM etanah.model.KonfigurasiSistem p WHERE p.nama = :nama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("nama", nama);
        return (KonfigurasiSistem) q.uniqueResult();
    }
    
     public List<UlasanJabatanTeknikal> findUlasanJabTeknikalbyIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }
    /*
     * SAVING METHOD
     */
    @Transactional
    public void saveOrUpdateUlasanJTek(UlasanJabatanTeknikal ujt) {
        ulasanJabatanTeknikalDAO.saveOrUpdate(ujt);
    }
}
