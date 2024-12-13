/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.model.AduanLokasi;
import etanah.model.AduanStrata;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author faidzal
 */
public class NotifikasiBayaranService {

    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(NotifikasiBayaranService.class);

    public List<PermohonanTuntutan> findMohonTuntutbyDate(Date date) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutan b where b.tarikhAkhirBayaran < :date";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setDate("date", date);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanBayar findMohonTuntutBayar(long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanBayar) q.uniqueResult();
    }

    public List<PermohonanTuntutanButiran> findMohonTuntutButir(long idTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanButiran b where b.permohonanTuntutan.idTuntut = :idTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idTuntut", idTuntut);
        return q.list();
    }

    /**
     * Urusan Penguatkuasaan Strata
     */
    public void strataPenguatkuasaan(Permohonan permohonanBaru, Permohonan permohonanLama) {
        AduanStrata aduanStrata = new AduanStrata();
        aduanStrata.setPermohonan(permohonanBaru);
        aduanStrata.setCawangan(permohonanBaru.getCawangan());
        aduanStrata.setHakmilik(permohonanLama.getSenaraiHakmilik().get(0).getHakmilik());
        aduanStrata.setCfNoSijil(permohonanLama.getSenaraiStrata().get(0).getCfNoSijil());
        aduanStrata.setCfTarikhKeluar(permohonanLama.getSenaraiStrata().get(0).getCfTarikhKeluar());
        aduanStrata.setInfoAudit(permohonanBaru.getInfoAudit());
        strService.simpanAduanStrata(aduanStrata); //    aduanStrata.set

        /*Save into Mohon_Hakmilik table*/
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp.setPermohonan(permohonanBaru);
        hp.setInfoAudit(permohonanBaru.getInfoAudit());
        if (!permohonanLama.getSenaraiHakmilik().isEmpty()) {
            hp.setHakmilik(permohonanLama.getSenaraiHakmilik().get(0).getHakmilik());
        }
        hakmilikPermohonanDAO.save(hp);
    }
}
