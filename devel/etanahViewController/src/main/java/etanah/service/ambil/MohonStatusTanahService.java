/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.ambil;

import etanah.dao.*;
import org.hibernate.Session;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.ambil.MohonPerihalTanahDAO;
import etanah.dao.ambil.MohonStatusTanahDAO;
import etanah.model.Permohonan;
import etanah.model.KodUrusan;
import etanah.model.AduanLokasi;
import etanah.model.AduanOrangKenaSyak;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodBandarPekanMukim;
import etanah.model.ambil.MohonPerihalTanah;
import etanah.model.ambil.MohonStatusTanah;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import java.util.Date;
import etanah.model.ambil.TampalBorangHakmilik;

/**
 *
 * @author zipzap
 */
public class MohonStatusTanahService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(InfoWartaService.class);
    @Inject
    MohonStatusTanahDAO mohonStatusTanahDAO;

    public List<MohonStatusTanah> findMohonPerihalTanahByIdMh(Long idMh, String jenisTanah) {
        String query = "SELECT h FROM etanah.model.ambil.MohonStatusTanah h where h.hakmilikPermohonan.id = :idMh and h.kodStatusTanah.jenisTanah = :jenisTanah";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMh", idMh);
        q.setString("jenisTanah", jenisTanah);
        LOG.info("query ::" + query);
        //LOG.info("findPermohonan :: finish");
        return q.list();
    }
    
    
      @Transactional
    public void simpanMohonStatusTanahDAO(MohonStatusTanah mohonStatusTanah) {
        mohonStatusTanahDAO.saveOrUpdate(mohonStatusTanah);
    }

}
