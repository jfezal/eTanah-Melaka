/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.*;
import etanah.model.*;
import etanah.model.Pihak;
import etanah.model.WakilKuasa;
import etanah.model.WakilKuasaHakmilik;
import etanah.model.WakilKuasaPenerima;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.fairul/wan.fairul
 */
public class PembetulanService {

    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakKepentinganDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPembetulanHakmilikDAO permohonanPembetulanHakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikAsalDAO hakmilikAsalDAO;
    @Inject
    HakmilikSebelumDAO hakmilikSebelumDAO;
    @Inject
    PermohonanAtasPerserahanDAO permohonanAtasPerserahanDAO;
    @Inject
    PermohonanAtasPihakBerkepentinganDAO permohonanAtasPihakBerkepentinganDAO;
    @Inject
    PermohonanPihakKemaskiniDAO permohonanPihakKemaskiniDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    WakilKuasaPenerimaDAO wakilKuasaPenerimaDAO;
    @Inject
    HakmilikWarisDAO hakmilikWarisDAO;
    @Inject
    PermohonanPembetulanUrusanDAO permohonanPembetulanUrusanDAO;
    @Inject
    PermohonanSuratPembetulanDAO permohonanSuratPembetulanDAO;
    @Inject
    HakmilikUrusanSuratDAO hakmilikUrusanSuratDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;

    public PermohonanPembetulanHakmilik findIdHakmilik(String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanHakmilik p WHERE p.idHakmilik = :idHakmilik";
        return (PermohonanPembetulanHakmilik) sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).uniqueResult();
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik and (p.penarikBalikan='0' or p.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        //return (HakmilikPermohonan) q.uniqueResult();
        
        if(!q.list().isEmpty()) {
            return (HakmilikPermohonan) q.list().get(0);
        } else {
            return (HakmilikPermohonan) q.uniqueResult();
        }
    }
    
    public HakmilikPermohonan findByIdMohonHakmilik(Long id) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.id = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("id", id);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findByIdPermohonanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan findByIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public PermohonanPembetulanHakmilik findByidPidH(String idPermohonan, String idHakmilik) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        
        if (!q.list().isEmpty()) {
            return (PermohonanPembetulanHakmilik) q.list().get(0);
        } else {
            return (PermohonanPembetulanHakmilik) q.uniqueResult();
        }
        //return (PermohonanPembetulanHakmilik) q.uniqueResult();
    }
    
    public List<PermohonanPembetulanHakmilik> findByidPidH2(String idPermohonan, String idHakmilik) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
    //nad add 29112019
    public PermohonanPembetulanHakmilik findByidMohon(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        
        if (!q.list().isEmpty()) {
            return (PermohonanPembetulanHakmilik) q.list().get(0);
        } else {
            return (PermohonanPembetulanHakmilik) q.uniqueResult();
        }
        //return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public PermohonanPembetulanHakmilik findByidBetul(Long idBetul) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.idBetul = :idBetul";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idBetul", idBetul);
        return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public HakmilikAsal findByidHA(Long hakmilikAsal) {
        String query = "Select B FROM etanah.model.HakmilikAsal B WHERE B.idAsal = :hakmilikAsal";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("hakmilikAsal", hakmilikAsal);
        return (HakmilikAsal) q.uniqueResult();

    }

    public PermohonanPembetulanUrusan findByidPerserahan(String idPermohonan, String idPerserahan, String idHakmilik) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idPerserahanLama = :idPerserahan and B.hakmilik.hakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPerserahan", idPerserahan);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanPembetulanUrusan) q.uniqueResult();
    }

    public PermohonanPembetulanUrusan findByidPermohonan(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPembetulanUrusan) q.uniqueResult();
    }
    public PermohonanPembetulanUrusan findByidPermohonanAndKemaskini(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan and B.ulasan = 'TAMBAHBARU'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        //return (PermohonanPembetulanUrusan) q.uniqueResult();
        PermohonanPembetulanUrusan ppu;
        if (q.list().size() > 1) {
            ppu = (PermohonanPembetulanUrusan) q.list().get(0);
        } else {
            ppu = (PermohonanPembetulanUrusan) q.uniqueResult();
        }
        return ppu;
    }

    public PermohonanPembetulanUrusan findByidPembetulan(String idPermohonan, String idPembetulan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idPembetulan = :idPembetulan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPembetulan", idPembetulan);
        return (PermohonanPembetulanUrusan) q.uniqueResult();

    }

    public PermohonanPembetulanUrusan findByidMohonAndIdSerahLama(String idPermohonan, String idPerserahanLama) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idPerserahanLama = :idPerserahanLama";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPerserahanLama", idPerserahanLama);
        return (PermohonanPembetulanUrusan) q.uniqueResult();

    }

    public PermohonanPembetulanUrusan findByidUrusan(String idPermohonan, String idUrusan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.urusan.idUrusan = :idUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idUrusan", idUrusan);
        return (PermohonanPembetulanUrusan) q.uniqueResult();

    }

    public PermohonanPembetulanHakmilik findByidPidHandidAu(String idPermohonan, String id, String idAtasUrusan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan and B.hakmilik.id = :id and B.idAtasUrusan = :idAtasUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("id", id);
        q.setString("idAtasUrusan", idAtasUrusan);
        return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public PermohonanPembetulanHakmilik findByUrusan(String idPermohonan, String idAtasUrusan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idAtasUrusan = :idAtasUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAtasUrusan", idAtasUrusan);
        return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public PermohonanPembetulanHakmilik findByidHakmilikAsal(String idPermohonan, String idHakmilikAsal) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idHakmilikAsal = :idHakmilikAsal";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilikAsal", idHakmilikAsal);
        return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public PermohonanPembetulanHakmilik findByidHakmilikSblm(String idPermohonan, String idHakmilikSebelum) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanHakmilik B WHERE B.permohonan.idPermohonan = :idPermohonan" + " and B.idHakmilikSebelum = :idHakmilikSebelum";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilikSebelum", idHakmilikSebelum);
        return (PermohonanPembetulanHakmilik) q.uniqueResult();

    }

    public HakmilikAsal findByidHidHA(String idH, String idHA) {
        String query = "Select B FROM etanah.model.HakmilikAsal B WHERE B.hakmilik.idHakmilik = :idH" + " and B.hakmilikAsal = :idHA";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idH", idH);
        q.setString("idHA", idHA);
        return (HakmilikAsal) q.uniqueResult();

    }

    public List<HakmilikAsal> findidHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikAsal h where h.hakmilik.idHakmilik = :idHakmilik";

        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikIdPermohonanList(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik and (p.penarikBalikan='0' or p.penarikBalikan is null) ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPemilikByidHakmilik(String idHakmilik, String kodPB) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik" + " and h.jenis= :kodPB and h.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kodPB", kodPB);
        return q.list();
    }

    public HakmilikPihakBerkepentingan findByidPihak(String idPihak, String kodPB, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak" + " and h.jenis= :kodPB and h.hakmilik.idHakmilik= :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("kodPB", kodPB).setString("idHakmilik", idHakmilik).setString("idPihak", idPihak);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findPBByidHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik" + " and h.jenis not in ('PM','WAR') and h.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findKAVEAT(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik" + " and h.jenis in ('PKS','PKL','PKA', 'PKD', 'PPK') and h.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    //add by azri 4/7/2013 : used in 'pembetulanActionBean.java' only
    @Transactional
    public HakmilikPihakBerkepentingan save(HakmilikPihakBerkepentingan hpk) {
        return hakmilikPihakKepentinganDAO.saveOrUpdate(hpk);
    }

    @Transactional
    public void deleteHakmilikPihakBerkepentingan(List<HakmilikPihakBerkepentingan> pBList) {
        for (HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan : pBList) {
            hakmilikPihakKepentinganDAO.delete(hakmilikPihakBerkepentingan);
        }
    }

    @Transactional // test
    public void deleteHakmilikPihakBerkepentingan2(HakmilikPihakBerkepentingan hpk) {
        hakmilikPihakKepentinganDAO.delete(hpk);
    }

    public List<HakmilikPihakBerkepentingan> findByIdUrusan(Long idUrusan) {
        String query = "Select hp from etanah.model.HakmilikPihakBerkepentingan hp "
                + "where hp.perserahan.idUrusan = :idUrusan and hp.aktif='Y' ";
        Query q = sessionProvider.get().createQuery(query).setLong("idUrusan", idUrusan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findByIdUrusanAndIdHakmilikAktif(Long idUrusan, String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPihakBerkepentingan hp "
                + "where hp.aktif='Y' and hp.perserahan.idUrusan = :idUrusan and hp.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", idUrusan)
                .setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findByIdUrusanAndIdHakmilik(Long idUrusan, String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPihakBerkepentingan hp "
                + "where hp.perserahan.idUrusan = :idUrusan and hp.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", idUrusan)
                .setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    // add by azri: use for BETHM
    public List<HakmilikPihakBerkepentingan> findByIdUrusanBatalAndIdHakmilik(Long idUrusan, String idHakmilik) {
        String query = "Select hp from etanah.model.HakmilikPihakBerkepentingan hp "
                + "where hp.perserahanBatal.idUrusan = :idUrusan and hp.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query)
                .setLong("idUrusan", idUrusan)
                .setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    public HakmilikUrusan findByIdUrusan(int idUrusan) {
        String query = "Select hu FROM etanah.model.HakmilikUrusan hu "
                + "WHERE hu.idUrusan = :idUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("idUrusan", idUrusan);
        return (HakmilikUrusan) q.uniqueResult();
    }
    //add by azri 4/7/2013 : used in 'pembetulanActionBean.java' only

//  public PermohonanPembetulanUrusan findByidBetulAndNoJilid(String idPermohonan, String noJilid) {
//    String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan"
//            + " and B.noJilid = :noJilid";
//    Query q = sessionProvider.get().createQuery(query);
//    q.setString("idPermohonan", idPermohonan);
//    q.setString("noJilid", noJilid);
//    return (PermohonanPembetulanUrusan) q.uniqueResult();
//  }
    public List<PermohonanPembetulanUrusan> findByNoJilid(String noJilid) {
        String query = "SELECT t FROM etanah.model.PermohonanPembetulanUrusan t "
                + "WHERE t.noJilid = :noJilid "
                + "ORDER BY t.tempohTahun asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noJilid", noJilid);
        return q.list();
    }// use in SusunPerserahanActionBean Only 

    public List<PermohonanPembetulanHakmilik> findidPermohonan(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanPembetulanHakmilik h where h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPembetulanHakmilik> findidPermohonanOridHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPembetulanHakmilik h where h.permohonan.idPermohonan = :idPermohonan" + " or h.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
    public List<PermohonanPembetulanHakmilik> findIdPermohonanIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPembetulanHakmilik h where h.permohonan.idPermohonan = :idPermohonan" + " and h.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
    public List<PermohonanPembetulanHakmilik> findIdPermohonanIdHakmilikFirst(String idPermohonan) {
        String query = "Select h FROM etanah.model.PermohonanPembetulanHakmilik h where "
                + "h.permohonan.idPermohonan =:idPermohonan "
                + "order by h.idBetul ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list(); 
    }    
    
    public PermohonanPembetulanHakmilik findIdPermohonanIdHakmilikFirst1(String idPermohonan) {
        String query = "Select h FROM etanah.model.PermohonanPembetulanHakmilik h where "
                + "h.permohonan.idPermohonan =:idPermohonan "
                + "order by h.idBetul ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        PermohonanPembetulanHakmilik pph;
        if (q.list().size() > 1) {
            pph = (PermohonanPembetulanHakmilik) q.list().get(0);
        } else {
            pph = (PermohonanPembetulanHakmilik) q.uniqueResult();
        }
        return pph;
    }    

    public List<HakmilikUrusan> findHakmilikUrusan() {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.kodUrusan.kod in ('BETUL','BETC')";

        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikByHakmilik(String idHakmilik, String kodSerah) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik" + " and h.kodUrusan.kodPerserahan= :kodSerah" + " and h.aktif in ('Y')";

        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kodSerah", kodSerah);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikByHakmilikBatal(String idHakmilik, String kodSerah) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik" + " and h.kodUrusan.kodPerserahan= :kodSerah" + " and h.aktif in ('T')";

        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kodSerah", kodSerah);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikByHakmilikAndIdMohon(String idHakmilik, String idPermohonan) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.idPerserahan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> findSurat(String idMohon, String idHakmilik) {
        String query = "Select d from etanah.model.KandunganFolder fd, etanah.model.Dokumen d, etanah.model.Permohonan m"
                + " where m.idPermohonan = :idMohon  "
                + " and m.folderDokumen.folderId = fd.folder.folderId  "
                + " and fd.dokumen.idDokumen = d.idDokumen  "
                + " and d.kodDokumen.kod in ('SBB','SBD','SAD','SAB','SWD','SWB')";
                //+ " and d.permohonan.idPermohonan is not null";

        Query q = sessionProvider.get().createQuery(query).setString("idMohon", idMohon);
        return q.list();
    }

    public WakilKuasa findWakilK(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.idWakil = :idWakil and p.syaratTambahan is null ";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil);
        return (WakilKuasa) q.uniqueResult();
    }

    public WakilKuasaPemberi findWakilKuasaPemberi(String idWakil) {
        String query = "Select h from etanah.model.WakilKuasaPemberi h where h.wakilKuasa.idWakil= :idWakil";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil);
        return (WakilKuasaPemberi) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilKuasaPenerima(String idWakil) {
        String query = "Select h from etanah.model.WakilKuasaPenerima h where h.wakilKuasa.idWakil= :idWakil";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByidP(String idPerserahan) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.idPerserahan= :idPerserahan and h.kodUrusan.kod in ('BETUL','BETC')";
        Query q = sessionProvider.get().createQuery(query).setString("idPerserahan", idPerserahan);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByidH(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik and h.kodUrusan.kod in ('BETUL','BETC')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanByidHNY(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik and h.aktif in ('Y')"
                + " and h.kodUrusan.kod in ('PMT','PMG','PMP','PMTM','PMPJK','GD','GDCE','GDPJ','GDPJK','HTB',"
                + "'PJT','PJBT','PJKT','PJKBT','JDGD','JDS','JPGD','JMGD','JMGPJ','JML','JMTG',"
                + "'TMAMB','TMAMD','TMAME','TMAMF','TMAMG','TMAML','TMAMT','TMAMW',"
                + "'MGG','MGGS','PHMM','PHMMT','PHMMS','PHMMK','PH30A','PH30B',"
                + "'KVPK','KVPPT','KVPS','KVPT','KVSK','KVSPT','KVSS','KVST',"
                + "'KVAK','KVAS','KVAT','KVLK','KVLP','KVLS','KVLT',"
                + "'TEN','TENPT','TENBT','IS','ISBLS','PNPA','PNPHB')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanByidHY(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik and h.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanByidKodSerah(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik and h.aktif in ('Y') and h.kodUrusan.kodPerserahan in ('SC','B','N')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanNotaBorang(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik= :idHakmilik and h.aktif in ('Y') and h.kodUrusan.kodPerserahan in ('N','B')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> findAtasUrusan(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPerserahan h where h.permohonan.id= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanAtasPerserahan findAtasUrusanByIdMohon(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPerserahan h where h.permohonan.id= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findAtasMohon(String idUrusan, String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.urusan.idUrusan = :idUrusan and B.permohonan.idPermohonan= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idUrusan", idUrusan);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findAtasMohonByIdMhnAndIdMhnBaru(String idPermohonanBaru, String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonanBaru.idPermohonan = :idPermohonanBaru and B.permohonan.idPermohonan= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonanBaru", idPermohonanBaru);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findMohonAtsUrusan(String idPermohonan, String idmhnBru) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonan.idPermohonan= :idPermohonan and B.permohonanBaru.idPermohonan= :idmhnBru";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idmhnBru", idmhnBru);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findMohonAtsUrusanbyIdMhn(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonan.idPermohonan= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findMohonAtsUrusanbyIdMhnIdPerserahan(String idPermohonan, Long IdUrusan, String idmhnBru) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonan.idPermohonan= :idPermohonan and B.urusan.idUrusan =:IdUrusan and B.permohonanBaru.idPermohonan= :idmhnBru";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("IdUrusan", IdUrusan);
        q.setString("idmhnBru", idmhnBru);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public List<PermohonanAtasPerserahan> findMohonAtsUrusanbyIdMhnList(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonan.idPermohonan= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> findMohonAtsUrusanbyIdMhnIdPerserahanList(String idPermohonan, Long IdUrusan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.permohonan.idPermohonan= :idPermohonan and B.urusan.idUrusan =:IdUrusan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setLong("IdUrusan", IdUrusan);;
        return q.list();
    }

    public List<HakmilikPermohonan> findByIdHakmilikOnly(String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    
    public List<HakmilikPermohonan> findByIdHakmilikOnlyByNotingPengambilan(String idHakmilik, String kodUrusan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmilik.idHakmilik = :idHakmilik "
                + "and p.permohonan.kodUrusan.kod = :kodUrusan "
                + "and p.permohonan.status in ('SL') order by p.infoAudit.tarikhMasuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public HakmilikUrusan findUrusan(String idUrusan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.idUrusan = :idUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idUrusan", idUrusan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    //Added by Aizuddin for copy to all hakmilik urusan BETUR
    public List<HakmilikUrusan> findUrusanbyIDPerserahan(String idPerserahan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.idPerserahan = :idPerserahan and B.aktif in ('Y') and B.kodUrusan.kodPerserahan in ('N','B','SC')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPerserahan", idPerserahan);
        return q.list();
    }

    public PermohonanPembetulanUrusan findUrusanBetul(String idUrusan) {
        String query = "Select B FROM etanah.model.PermohonanPembetulanUrusan B WHERE B.urusan.idUrusan = :idUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idUrusan", idUrusan);
        return (PermohonanPembetulanUrusan) q.uniqueResult();
    }

    public FolderDokumen findFolderByTajuk(String tajuk) {
        String query = "Select B FROM etanah.model.FolderDokumen B WHERE B.tajuk = :tajuk";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("tajuk", tajuk);
        return (FolderDokumen) q.uniqueResult();
    }

    public PermohonanUrusan findMohonUrusan(String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanUrusan B WHERE B.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanUrusan) q.uniqueResult();
    }

    public HakmilikUrusan findDetail(String idPerserahan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.idPerserahan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPerserahan", idPerserahan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public HakmilikUrusan findHUrusan(String idHakmilik, String idPerserahan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.hakmilik.idHakmilik= :idHakmilik and B.idPerserahan = :idPerserahan and B.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPerserahan", idPerserahan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    public List<HakmilikUrusan> findByIdHakmilikAndIdMohonList(String idHakmilik, String idPerserahan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.hakmilik.idHakmilik= :idHakmilik and B.idPerserahan = :idPerserahan and B.aktif in ('Y')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPerserahan", idPerserahan);
        return q.list();
    }

    @Transactional
    public void simpanBetul(PermohonanPembetulanHakmilik permohonanBetulHakmilik) {
        permohonanPembetulanHakmilikDAO.save(permohonanBetulHakmilik);
    }

    @Transactional
    public void simpanUrusanBetul(PermohonanPembetulanUrusan p) {
        permohonanPembetulanUrusanDAO.save(p);
    }

    @Transactional
    public void updateUrusanBetul(PermohonanPembetulanUrusan p) {
        permohonanPembetulanUrusanDAO.update(p);
    }

    @Transactional
    public void simpanTambahUrusan(PermohonanPembetulanUrusan p) {
        permohonanPembetulanUrusanDAO.save(p);
    }

    @Transactional
    public void updateTambahUrusan(PermohonanPembetulanUrusan p) {
        permohonanPembetulanUrusanDAO.save(p);
    }

    @Transactional
    public void simpanMohonAtasPihak(PermohonanAtasPihakBerkepentingan permohonanAtasPihakBerkepentingan) {
        permohonanAtasPihakBerkepentinganDAO.saveOrUpdate(permohonanAtasPihakBerkepentingan);
    }

    @Transactional
    public void delete(PermohonanAtasPihakBerkepentingan p) {
        permohonanAtasPihakBerkepentinganDAO.delete(p);
    }

    @Transactional
    public void deleteMohonPihak(PermohonanPihak p) {
        permohonanPihakDAO.delete(p);
    }

    @Transactional
    public void updateBetul(PermohonanPembetulanHakmilik permohonanBetulHakmilik) {
        permohonanPembetulanHakmilikDAO.update(permohonanBetulHakmilik);
    }

    @Transactional
    public void saveOrUpdate(List<HakmilikUrusan> hakmilikUrusanList) {
        for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
            hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
        }
    }

    @Transactional
    public void deleteWaris(HakmilikWaris hw) {
        hakmilikWarisDAO.delete(hw);
    }

    @Transactional
    public void updateHakmilik(Hakmilik hakmilik) {
        hakmilikDAO.update(hakmilik);
    }

    @Transactional
    public void simpanHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
    }

    @Transactional
    public void updateHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        hakmilikUrusanDAO.update(hakmilikUrusan);
    }

    @Transactional
    public void simpanAtasUrusan(PermohonanAtasPerserahan permohonanAtasPerserahan) {
        permohonanAtasPerserahanDAO.saveOrUpdate(permohonanAtasPerserahan);
    }

    @Transactional
    public void simpanAtasUrusan2(PermohonanAtasPerserahan permohonanAtasPerserahan) {
        permohonanAtasPerserahanDAO.save(permohonanAtasPerserahan);
    }

    @Transactional
    public void deleteUrusan(PermohonanAtasPerserahan permohonanAtasPerserahan) {
        permohonanAtasPerserahanDAO.delete(permohonanAtasPerserahan);
    }

    @Transactional
    public void deleteHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        hakmilikUrusanDAO.delete(hakmilikUrusan);
    }

    @Transactional
    public void deleteSurat(PermohonanSuratPembetulan p) {
        permohonanSuratPembetulanDAO.delete(p);
    }

    @Transactional
    public void deleteUrusanHakmilikBetul(PermohonanPembetulanHakmilik p) {
        permohonanPembetulanHakmilikDAO.delete(p);
    }
    
    public void deleteListUrusanHakmilikBetul(List<PermohonanPembetulanHakmilik> p) {
        for (PermohonanPembetulanHakmilik permohonanBetulHakmilik : p) {
            permohonanPembetulanHakmilikDAO.delete(permohonanBetulHakmilik);
        }
    }

    @Transactional
    public void deleteHakmilikAsal(HakmilikAsal ha) {
        hakmilikAsalDAO.delete(ha);
    }

    @Transactional
    public void deleteTambahUrusan(PermohonanPembetulanUrusan p) {
        permohonanPembetulanUrusanDAO.delete(p);
    }

    @Transactional
    public void saveKKini(PermohonanPihakKemaskini ppKemaskini) {
        permohonanPihakKemaskiniDAO.save(ppKemaskini);
    }

    @Transactional
    public void updateKKini(PermohonanPihakKemaskini ppKemaskini) {
        permohonanPihakKemaskiniDAO.update(ppKemaskini);
    }

    @Transactional
    public void updatePihak(Pihak pihak) {
        pihakDAO.update(pihak);
    }

    @Transactional
    public void simpanHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hpk) {
        hakmilikPihakBerkepentinganDAO.saveOrUpdate(hpk);
    }

    @Transactional
    public void updateHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hpk) {
        hakmilikPihakBerkepentinganDAO.update(hpk);
    }

    @Transactional
    public void updatePemohon(Pemohon pemohon) {
        pemohonDAO.update(pemohon);
    }

    @Transactional
    public void updatePermohonanPihak(PermohonanPihak mohonPihak) {
        permohonanPihakDAO.update(mohonPihak);
    }

    @Transactional
    public void delete(PermohonanPihakKemaskini ppKemaskini) {
        permohonanPihakKemaskiniDAO.delete(ppKemaskini);
    }

    @Transactional
    public void simpanPenerima(WakilKuasaPenerima wk) {
        wakilKuasaPenerimaDAO.saveOrUpdate(wk);
    }

    @Transactional
    public void updatePenerima(WakilKuasaPenerima wk) {
        wakilKuasaPenerimaDAO.update(wk);
    }

    @Transactional
    public void updateMohonHakmilik(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.update(hp);
    }

    @Transactional
    public void saveMohonHakmilik(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.save(hp);
    }

    @Transactional
    public void deletMohonHakmilik(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.delete(hp);
    }

    @Transactional
    public void simpanWaris(HakmilikWaris hw) {
        hakmilikWarisDAO.saveOrUpdate(hw);

    }

    @Transactional
    public void updateWaris(HakmilikWaris hw) {
        hakmilikWarisDAO.update(hw);
    }

    @Transactional
    public void simpanSurat(PermohonanSuratPembetulan psp) {
        permohonanSuratPembetulanDAO.save(psp);
    }

    @Transactional
    public void updateSurat(PermohonanSuratPembetulan psp) {
        permohonanSuratPembetulanDAO.update(psp);
    }

    @Transactional
    public void deleteWakilKuasaPnerima(WakilKuasaPenerima wkp) {
        wakilKuasaPenerimaDAO.delete(wkp);
    }

    @Transactional
    public void simpanMohonPihak(PermohonanPihak pe) {
        permohonanPihakDAO.saveOrUpdate(pe);
    }

    @Transactional
    public void simpanHakmilikAsal(HakmilikAsal ha) {
        hakmilikAsalDAO.saveOrUpdate(ha);
    }

    @Transactional
    public void simpanHakmilikSblm(HakmilikSebelum hs) {
        hakmilikSebelumDAO.saveOrUpdate(hs);
    }

    @Transactional
    public void simpanHakmilikUrusanSrt(HakmilikUrusanSurat hus) {
        hakmilikUrusanSuratDAO.saveOrUpdate(hus);
    }

    public HakmilikSebelum findByidHidHS(String idH, String idHS) {
        String query = "Select B FROM etanah.model.HakmilikSebelum B WHERE B.hakmilik.idHakmilik = :idH" + " and B.hakmilikSebelum = :idHS";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idH", idH);
        q.setString("idHS", idHS);
        return (HakmilikSebelum) q.uniqueResult();

    }

    public List<HakmilikSebelum> findByidHidHSList(String idH, String idHS) {
        String query = "Select B FROM etanah.model.HakmilikSebelum B WHERE B.hakmilik.idHakmilik = :idH" + " and B.hakmilikSebelum = :idHS";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idH", idH);
        q.setString("idHS", idHS);
        return q.list();
    }

    public HakmilikPihakBerkepentingan findByidHKod(String idPihak, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak" + " and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idHakmilik", idHakmilik);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findByidHakmilikToCompare(String kodPB, String idHakmilik, String noPengenalan, String nama, String jenisPengenalan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.jenis.kod = :kodPB"
                + " and h.hakmilik.idHakmilik = :idHakmilik"
                + " and h.noPengenalan = :noPengenalan"
                + " and h.nama = :nama"
                + " and h.jenisPengenalan.kod = :jenisPengenalan";

        Query q = sessionProvider.get().createQuery(query)
                .setString("kodPB", kodPB)
                .setString("idHakmilik", idHakmilik)
                .setString("noPengenalan", noPengenalan)
                .setString("jenisPengenalan", jenisPengenalan)
                .setString("nama", nama);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findByidHKodaktif(String idPihak, String idHakmilik, String JenisPB) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod = :JenisPB";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idHakmilik", idHakmilik).setString("JenisPB", JenisPB);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findByidHKodaktifList(String idPihak, String idHakmilik, String JenisPB) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.pihak.idPihak = :idPihak" + " and h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.jenis.kod = :JenisPB";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idHakmilik", idHakmilik).setString("JenisPB", JenisPB);
        return q.list();
    }

    public List<HakmilikSebelum> findHSidHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikSebelum h where h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByHakmilik(Hakmilik hakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveSelainPemilik(String idUrusan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.perserahan.idUrusan = :idUrusan "
                + "and h.aktif='Y' and h.jenis.kod not in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS')";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByIdUrusan(String idUrusan) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.perserahan.idUrusan = :idUrusan "
                + "and h.aktif='Y'";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan);
        return q.list();
    }

    //add by azri :24/6/2013
    public List<HakmilikPihakBerkepentingan> findPihakbyIdHakmilikAndIdUrusan(String idUrusan, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.perserahan.idUrusan = :idUrusan and h.aktif='Y' and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findPihakbyIdHakmilikAndIdUrusanOnly(String idUrusan, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.perserahan.idUrusan = :idUrusan and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public HakmilikPihakBerkepentingan findbyIdHakmilikAndIdUrusan(String idUrusan, String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.perserahan.idUrusan = :idUrusan and h.aktif='Y' and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan).setString("idHakmilik", idHakmilik);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findByIdAU(Long idAu, String idPermohonan) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.idAtasUrusan = :idAtasUrusan and B.permohonan.idPermohonan= :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idAtasUrusan", idAu);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }

    public PermohonanAtasPerserahan findByIdAUOnly(Long idAu) {
        String query = "Select B FROM etanah.model.PermohonanAtasPerserahan B WHERE B.idAtasUrusan = :idAtasUrusan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idAtasUrusan", idAu);
        return (PermohonanAtasPerserahan) q.uniqueResult();
    }
    //add by azri :END

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakStringHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByIdH(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('BP','PM','PA','WAR','ASL','JA','PG','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PKS','PAG','PL','PPC','PPH','PPM','PAT','PAW','PAP','WKF','PML','CP','PH','ROS')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActivePemilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActivePemegangGadaian(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PG')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakWarisByIdH(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('WAR','WPA','WS')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakWarisByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PA','PP')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> findAtasMohon(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPerserahan h where h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPerserahan> findAtasMohonHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanAtasPerserahan h where h.permohonan.idPermohonan = :idPermohonan"
                + " and hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan)
                .setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public PermohonanAtasPihakBerkepentingan findByAtasPihak(String idHakmilikPihakBerkepentingan, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pihakBerkepentingan.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan).setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
    }

    public PermohonanAtasPihakBerkepentingan findByAtasPihakByIdPemohon(String idPemohon, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pemohon.idPemohon = :idPemohon" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPemohon", idPemohon).setString("idPermohonan", idPermohonan);
        return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByAtasPihakByIdPemohonList(String idPemohon, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pemohon.idPemohon = :idPemohon" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPemohon", idPemohon).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByAtasPihakByIdPB(String idHakmilikPihakBerkepentingan, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.pihakBerkepentingan.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanAtasPihakBerkepentingan findByAtasPihakByIdMP(String idPermohonanPihak, String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.permohonanPihak.idPermohonanPihak = :idPermohonanPihak" + " and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonanPihak", idPermohonanPihak).setString("idPermohonan", idPermohonan);

        if (q.list().size() > 1) {
            return (PermohonanAtasPihakBerkepentingan) q.list().get(0);
        } else {
            return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
        }        
        
        
        //return (PermohonanAtasPihakBerkepentingan) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan findByIdHp(String id) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.idHakmilikPihakBerkepentingan = :id";
        Query q = sessionProvider.get().createQuery(query).setString("id", id);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByAtasPihakByIdMohon(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanAtasPihakBerkepentingan> findByAtasPihakByIdMohon2(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanAtasPihakBerkepentingan h where h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanPihakKemaskini findByidMohonNamaMedanidAtasPihak(String idPermohonan, String namaMedan, String idAtasPihak) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan" + " and h.namaMedan= :namaMedan and h.atasPihakBerkepentingan.idAtasPihak= :idAtasPihak ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("namaMedan", namaMedan).setString("idAtasPihak", idAtasPihak);
        return (PermohonanPihakKemaskini) q.uniqueResult();
    }

    public PermohonanPihakKemaskini findByidMohonNamaMedanidPemohon(String idPermohonan, Long idPemohon) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan" + " and h.namaMedan in ('nama') and h.pemohon.idPemohon = :idPemohon ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setLong("idPemohon", idPemohon);
        return (PermohonanPihakKemaskini) q.uniqueResult();
    }

    public List<PermohonanPihakKemaskini> findListbyIdMohonIdPemohon(String idPermohonan, Long idPemohon) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan "
                + " and h.pemohon.idPemohon = :idPemohon ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)
                .setLong("idPemohon", idPemohon);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findAtasIdHp(String idPermohonan, String idAtasPihak) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan and h.atasPihakBerkepentingan.idAtasPihak= :idAtasPihak ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idAtasPihak", idAtasPihak);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findAtasIdHpMohonPihak(String idPermohonan, String idMohonPihak) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan and "
                + "h.idMohonPihak= :idMohonPihak ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idMohonPihak", idMohonPihak);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findAtasIdHpOnly(String idPermohonan, String idHakmilikPihakBerkepentingan) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan and "
                + "h.pihakTerlibat.idHakmilikPihakBerkepentingan= :idHakmilikPihakBerkepentingan ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findListbyIdMohonIdHm(String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan "
                + " and h.hakmilik.idHakmilik= :idHakmilik ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)
                .setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findListbyIdMohonIdHmMedan(String idPermohonan, String idHakmilik, String medan) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan "
                + " and h.hakmilik.idHakmilik= :idHakmilik and h.namaMedan= :medan and h.nilaiBaru is not null ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan)
                .setString("idHakmilik", idHakmilik)
                .setString("medan", medan);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findListbyIdMohonIdHmMedanIdlama(String idPermohonan, String idHakmilik, String medan, String idLama) {
        StringBuilder sb = new StringBuilder("Select h from etanah.model.PermohonanPihakKemaskini h where ")
                .append(" h.hakmilik.idHakmilik= :idHakmilik and h.nilaiBaru is not null ");
        if (medan != null) {
            sb.append(" and h.namaMedan= :medan ");
        }
        if (idPermohonan != null) {
            sb.append(" and h.permohonan.idPermohonan = :idPermohonan ");
        }
        sb.append(" and h.idPermohonanLama = :idLama ");

        Query q = sessionProvider.get().createQuery(sb.toString())
                .setString("idHakmilik", idHakmilik)
                .setString("idLama", idLama);
        if (medan != null) {
            q.setString("medan", medan);
        }
        if (idPermohonan != null) {
            q.setString("idPermohonan", idPermohonan);
        }
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findAtasIdHpByIdAtasPihak(String idAtasPihak) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.atasPihakBerkepentingan.idAtasPihak= :idAtasPihak ";
        Query q = sessionProvider.get().createQuery(query).setString("idAtasPihak", idAtasPihak);
        return q.list();
    }

    public List<PermohonanPihakKemaskini> findPihakRemove(String idPermohonan) {
        String query = "Select h from etanah.model.PermohonanPihakKemaskini h where h.permohonan.idPermohonan = :idPermohonan and h.namaMedan in ('aktif') ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<WakilKuasaHakmilik> findWakilHakmilikByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.WakilKuasaHakmilik h where h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<WakilKuasaPenerima> findWakilKuasaPList(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idWakil and p.status in ('A','M')";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil);
        return q.list();
    }

    public List<WakilKuasaPenerima> findWakilKuasaPListUsingName(String idWakil, String namaPenerima) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idWakil and p.status in ('A','M') and p.nama LIKE:namaPenerima";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil).setString("namaPenerima", "%" + namaPenerima + "%");
        return q.list();
    }

    public List<WakilKuasaPenerima> findWakilKuasaPIDMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.permohonanPembetulan = :idPermohonan and p.status in ('MA')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikWaris> findWarisIDMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.permohonanPembetulan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public WakilKuasa findWakilKuasa(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasa p WHERE p.idWakil = :idWakil";
        Query q = sessionProvider.get().createQuery(query).setString("idWakil", idWakil);
        return (WakilKuasa) q.uniqueResult();
    }

    public List<HakmilikWaris> findHakmilikWaris(String idHakmilikPihakBerkepentingan) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.pemegangAmanah.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan and p.status in ('A')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan);
        return q.list();
    }

    public List<HakmilikWaris> findHakmilikWarisByPihakKepentingan(Long idHakmilikPihakBerkepentingan) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.pemegangAmanah.idHakmilikPihakBerkepentingan = :idHakmilikPihakBerkepentingan and p.status in ('A')";
        Query q = sessionProvider.get().createQuery(query).setLong("idHakmilikPihakBerkepentingan", idHakmilikPihakBerkepentingan);
        return q.list();
    }

    public List<KodStatusHakmilik> findStatusHakmilik() {
        String query = "Select p FROM etanah.model.KodStatusHakmilik p WHERE p.kod in ('B','D','P','T')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodStatusAkaun> findStatusAkaun() {
        String query = "Select s FROM etanah.model.KodStatusAkaun s WHERE s.kod in ('B','A')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodStatusHakmilik> findStatusHakmilikD() {
        String query = "Select p FROM etanah.model.KodStatusHakmilik p WHERE p.kod in ('B','P')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<KodStatusHakmilik> findStatusHakmilikB() {
        String query = "Select p FROM etanah.model.KodStatusHakmilik p WHERE p.kod in ('D','P')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public WakilKuasaPenerima findWakilKuasaByID(String idPenerima) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.idPenerima = :idPenerima and p.status in ('A','M')";
        Query q = sessionProvider.get().createQuery(query).setString("idPenerima", idPenerima);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilKuasaBetulByID(String idPenerima) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.betulPenerima = :idPenerima and p.status in ('MA')";
        Query q = sessionProvider.get().createQuery(query).setString("idPenerima", idPenerima);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilKuasaPByID(String idPenerima) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.idPenerima = :idPenerima and p.status in ('MA')";
        Query q = sessionProvider.get().createQuery(query).setString("idPenerima", idPenerima);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public WakilKuasaPenerima findWakilByID(String idPenerima) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.idPenerima = :idPenerima";
        Query q = sessionProvider.get().createQuery(query).setString("idPenerima", idPenerima);
        return (WakilKuasaPenerima) q.uniqueResult();
    }

    public HakmilikWaris findWarisByID(String idWaris) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.idWaris = :idWaris and p.status in ('A')";
        Query q = sessionProvider.get().createQuery(query).setString("idWaris", idWaris);
        return (HakmilikWaris) q.uniqueResult();
    }

    public HakmilikWaris findWarisBetulByID(String idWaris) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.betulWaris = :idWaris and p.status in ('MA')";
        Query q = sessionProvider.get().createQuery(query).setString("idWaris", idWaris);
        return (HakmilikWaris) q.uniqueResult();
    }

    public HakmilikWaris findWarisBetulByIDwaris(String idWaris) {
        String query = "Select p FROM etanah.model.HakmilikWaris p WHERE p.idWaris = :idWaris";
        Query q = sessionProvider.get().createQuery(query).setString("idWaris", idWaris);
        return (HakmilikWaris) q.uniqueResult();
    }

    public List<WakilKuasaPenerima> findWakilStatus(String idWakil) {
        String query = "Select p FROM etanah.model.WakilKuasaPenerima p WHERE p.wakilKuasa.idWakil = :idWakil and p.status in ('MA')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idWakil", idWakil);
        return q.list();
    }

    public PermohonanRujukanLuar findRujukanIDMohonHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik and p.catatan like 'Kemasukan BETUR'";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        //return (PermohonanRujukanLuar) q.uniqueResult();
        
        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }
    
    public PermohonanRujukanLuar findRujukanIDMohonHakmilik2(String idPermohonan, String idHakmilik, long idPembetulan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik and p.nilai2 =:idPembetulan and p.catatan like 'Kemasukan BETUR'";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik).setLong("idPembetulan", idPembetulan);;
        //return (PermohonanRujukanLuar) q.uniqueResult();
        
        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }
    
    public PermohonanRujukanLuar findRujukanIDMohonHakmilik3(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan and p.hakmilik.idHakmilik = :idHakmilik ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        //return (PermohonanRujukanLuar) q.uniqueResult();
        
        PermohonanRujukanLuar prl;
        if (q.list().size() > 1) {
            prl = (PermohonanRujukanLuar) q.list().get(0);
        } else {
            prl = (PermohonanRujukanLuar) q.uniqueResult();
        }
        return prl;
    }

    public PermohonanSuratPembetulan findSurat(String idWakil, String idPermohonan, String idUrusan) {
        String query = "Select p FROM etanah.model.PermohonanSuratPembetulan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.urusan.idUrusan  = :idUrusan and p.wakilKuasa.idWakil = :idWakil";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idUrusan", idUrusan)
                .setString("idWakil", idWakil);
        return (PermohonanSuratPembetulan) q.uniqueResult();
    }

    public PermohonanRujukanLuar findRujukanIDMohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> searchMohonRujByIDPermohonan(String idPermohonan) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select distinct u from etanah.model.PermohonanRujukanLuar u where u.permohonan.idPermohonan = :idPermohonan");
            q.setString("idPermohonan", idPermohonan);
            return q.list();
        } finally {
        }
    }

    public PermohonanRujukanLuar searchMohonRujByIDPermohonanAdun(String idPermohonan) {
        String query = "select u from etanah.model.PermohonanRujukanLuar u where u.permohonan.idPermohonan = :idPermohonan "
                + "and u.agensi.kategoriAgensi.kod in ('ADN')";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanPembetulanUrusan> findUrusanBetulList2(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Hakmilik findHakmilik(String idHakmilik) {
        String query = "Select p FROM etanah.model.Hakmilik p WHERE p.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return (Hakmilik) q.uniqueResult();

    }

    public List<Kehadiran> findKehadiran(String idEnkuiri) {
        String query = "Select p FROM etanah.model.Kehadiran p WHERE p.enkuiri.idEnkuiri = :idEnkuiri";
        Query q = sessionProvider.get().createQuery(query).setString("idEnkuiri", idEnkuiri);
        return q.list();

    }

    public PermohonanSuratPembetulan findSuratIDMohon(String idPermohonan, String idUrusan) {
        String query = "Select p FROM etanah.model.PermohonanSuratPembetulan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.urusan.idUrusan  = :idUrusan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idUrusan", idUrusan);
        return (PermohonanSuratPembetulan) q.uniqueResult();
    }

    public List<PermohonanPembetulanUrusan> findUrusanBetulList(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idPerserahanLama !=null and p.urusan = null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPembetulanUrusan> findUrusanBetulListByIdSerahLama(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.idPerserahanLama = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPembetulanUrusan> findBetulMaklumatUrusan(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idPerserahanLama =null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPembetulanUrusan> findBetulMaklumatUrusan_(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //sini 

    public PermohonanPembetulanUrusan findBetulMaklumatUrusanbyHakmilikIdMohon(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanUrusan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idPerserahanLama =null and p.hakmilik.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        return (PermohonanPembetulanUrusan) q.uniqueResult();
    }

    public List<PermohonanPembetulanHakmilik> findHakmilikAsal(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanHakmilik p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idHakmilikAsal !=null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPihak> findMohonPihakByIdMohonAndIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.permohonan.idPermohonan = :idPermohonan  and  h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPihak> findPermohonanPihakByIdHakmilik(String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanPembetulanHakmilik> findHakmilikSblm(String idPermohonan) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanHakmilik p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idHakmilikSebelum !=null ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pengguna> findPenggunaKodCaw(KodCawangan kc) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod = :kc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("kc", kc.getKod());
        return q.list();
    }

    public List<KodUrusan> findSCBN() {
        Session s = sessionProvider.get();
        return s.createQuery("select ku from KodUrusan ku where ku.jabatan.id = '16' and ku.aktif='Y' and ku.kodPerserahan.kod in ('SC','B','N') order by ku.kod asc").list();
    }

    public PermohonanPihak findByidMohonPihak(String idPihak, String idPerserahan) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.pihak.idPihak = :idPihak and h.noRujukan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPerserahan", idPerserahan);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak findByidMohonPihak2(String idPihak, String idPerserahan) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPerserahan", idPerserahan);
        return (PermohonanPihak) q.uniqueResult();
    }

    public Pemohon findByidPemohon(String idPihak, String idPermohonan, String idPerserahan) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan and h.kaitan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPermohonan", idPermohonan).setString("idPerserahan", idPerserahan);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findByidPemohonOnly(String idPihak, String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonbyIdpihak(String idPihak, String idPermohonan) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonbyIdpihakAndIdMohon(Long idPihak, String idPermohonan) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonbyIdpihakOnly(Long idPihak) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query).setLong("idPihak", idPihak);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonbyIdPemohon(Long idPemohon) {
        String query = "Select h from etanah.model.Pemohon h where h.idPemohon = :idPemohon";
        Query q = sessionProvider.get().createQuery(query).setLong("idPemohon", idPemohon);
        return (Pemohon) q.uniqueResult();
    }

    public Pemohon findPemohonbyIdpihakKodPihak(String idPihak, String idPermohonan, String kod) {
        String query = "Select h from etanah.model.Pemohon h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan and h.jenis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPermohonan", idPermohonan).setString("kod", kod);
        return (Pemohon) q.uniqueResult();
    }

    public PermohonanPihak findMohonPihakbyIdpihakKodPihak(String idPihak, String idPermohonan, String kod) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.pihak.idPihak = :idPihak and h.permohonan.idPermohonan = :idPermohonan and h.jenis.kod = :kod";
        Query q = sessionProvider.get().createQuery(query).setString("idPihak", idPihak).setString("idPermohonan", idPermohonan).setString("kod", kod);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak findMohonPihakbyIdpihak(Long idPihak) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.pihak.idPihak = :idPihak";
        Query q = sessionProvider.get().createQuery(query).setLong("idPihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak findMohonPihakbyidPermohonanPihak(Long idPermohonanPihak) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query).setLong("idPermohonanPihak", idPermohonanPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak findMohonPihakbyIdpihakAndIdHakmilik(Long idPihak, String idPermohonan, String idHakmilik) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.pihak.idPihak = :idPihak "
                + "and h.permohonan.idPermohonan = :idPermohonan "
                + "and h.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        return (PermohonanPihak) q.uniqueResult();
    }

    public PermohonanPihak findMohonPihakbyIdMohonAndIdHakmilik(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPihak p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik ";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idHakmilik", idHakmilik);
        return (PermohonanPihak) q.uniqueResult();
    }

    public List<PermohonanPihak> findMohonPihakbyIdMohonAndIdHakmilikAndKaitan(String idPermohonan, String idPermohonanKaitan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPihak p WHERE p.kaitan = :idPermohonanKaitan"
                + " and p.hakmilik.idHakmilik = :idHakmilik "
                + " and p.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan)
                .setString("idPermohonanKaitan", idPermohonanKaitan)
                .setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<Pemohon> findPemohonbyIdMohonAndIdHakmilikAndKaitan(String idPermohonan, String idPermohonanKaitan) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.kaitan = :idPermohonanKaitan "
                + "and p.permohonan.idPermohonan = :idPermohonan ";
        Query q = sessionProvider.get().createQuery(query)
                .setString("idPermohonan", idPermohonan)
                .setString("idPermohonanKaitan", idPermohonanKaitan);
        return q.list();
    }

    public List<PermohonanPihak> findPihakByIdMohon(String idPermohonan, String idPerserahan) {
        String query = "Select h from etanah.model.PermohonanPihak h where h.permohonan.idPermohonan = :idPermohonan  and  h.kaitan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idPerserahan", idPerserahan);
        return q.list();
    }

    public List<Pemohon> findPemohon(String idPermohonan, String idPerserahan) {
        String query = "Select h from etanah.model.Pemohon h where h.permohonan.idPermohonan = :idPermohonan and  h.kaitan = :idPerserahan";
        Query q = sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("idPerserahan", idPerserahan);
        return q.list();
    }

    public List<HakmilikUrusanSurat> findHakmilikUrusanSrt(String idUrusan, String noRujukan) {
        String query = "Select h from etanah.model.HakmilikUrusanSurat h where h.urusan.idUrusan = :idUrusan  and  h.noSurat = :noRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idUrusan", idUrusan).setString("noRujukan", noRujukan);
        return q.list();
    }

    public HakmilikUrusan findurusanByIdHMIdMhn(String hakmilik, String idPermohonan) {
        String query = "Select B FROM etanah.model.HakmilikUrusan B WHERE B.hakmilik.idHakmilik =:hakmilik and B.idPerserahan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("hakmilik", hakmilik);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikUrusan) q.uniqueResult();
    }
    
    public HakmilikUrusan findHakmilikUrusanPSPL(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.kodUrusan.kod in ('PSPL') and h.aktif='Y'"
                + " and h.hakmilik.idHakmilik = :idHakmilik and h.idPerserahan = (Select MAX(h1.idPerserahan) from etanah.model.HakmilikUrusan h1"
                + " where h1.kodUrusan.kod in ('PSPL') and h1.aktif='Y' and h1.hakmilik.idHakmilik = :idHakmilik)";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return (HakmilikUrusan) q.uniqueResult();
    }
    
    public PermohonanPembetulanHakmilik findUrusanPSPL(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.PermohonanPembetulanHakmilik p WHERE p.permohonan.idPermohonan = :idPermohonan"
                + " and p.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);       
        return (PermohonanPembetulanHakmilik) q.uniqueResult();
//        return (PermohonanPembetulanHakmilik) sessionProvider.get().createQuery(query).setString("idPermohonan",idPermohonan).uniqueResult();
    }
}
