/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.service;

import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.DokumenDAO;
import etanah.model.Dokumen;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;

import org.apache.log4j.Logger;
import org.hibernate.Session;
/**
 *
 * @author massita
 */
public class DrafKertasMMKNService { 

     private static final Logger logger = Logger.getLogger(DrafKertasMMKNService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    DokumenDAO dokumenDAO;

    @Transactional
    public void simpanKertas(PermohonanKertas kertas, PermohonanKertasKandungan kand) {
        permohonanKertasDAO.saveOrUpdate(kertas);
        if (kand != null) {
            permohonanKertasKandunganDAO.saveOrUpdate(kand);
        }
    }

    @Transactional
    public void deleteDokumen(Dokumen dokumen) {
        dokumenDAO.delete(dokumen);
    }
    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.delete(permohonanKertasKandungan);
    }

    public PermohonanRujukanLuar findByKodDokumen(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumenRujukan.kod ='SMMR'";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanKertasKandungan findByKodIdKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        return (PermohonanKertasKandungan) sessionProvider.get().createQuery(query).setLong("idKertas", idKertas).uniqueResult();
    }
     public PermohonanKertas findByKodIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.tajuk='Draf Kertas Siasatan'";
        return (PermohonanKertas) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

     public PermohonanKertas findMMKNByKodIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan And b.kodDokumen.kod='MMKN'";
        return (PermohonanKertas) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=7 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

      public List<PermohonanKertasKandungan> findByKertas2(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=8 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

      public List<PermohonanKertasKandungan> findByKertas3(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=9 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

      public List<PermohonanKertasKandungan> findByKertas4(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil=4 order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    public PermohonanKertasKandungan findbil1ByIdKertas(Long idKertas,int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKertas", idKertas);
         q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }


   public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan2(Long idKandungan2) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan2);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

     public PermohonanKertasKandungan findkandunganByIdKandungan3(Long idKandungan3) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan3);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

     public Dokumen findDokumenByIdDokumen(Long idDokumen) {
        String query = "Select p FROM etanah.model.Dokumen p WHERE p.idDokumen = :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idDokumen", idDokumen);
        return (Dokumen) q.uniqueResult();
    }
}
