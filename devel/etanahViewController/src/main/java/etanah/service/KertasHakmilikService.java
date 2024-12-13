/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.wideplay.warp.persist.Transactional;
import com.google.inject.Inject;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import able.stripes.AbleActionBean;
import etanah.model.HakmilikKertas;
import etanah.model.KodCawangan;
import etanah.model.*;
import etanah.model.Pengguna;
import org.hibernate.Query;
import etanah.dao.HakmilikKertasDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanKutipanDokumenDAO;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Zulhazmi
 */
@Singleton
public class KertasHakmilikService extends AbleActionBean {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikKertasDAO hakmilikKertasDAO;
    @Inject
    PermohonanKutipanDokumenDAO permohonanKutipanDokumenDAO;
    @Inject
    Pengguna pengguna;
    @Inject
    private KodCawanganDAO kodCawanganDAO;

    public List<Pengguna> getSenaraiPenggunaPendaftar() {

        String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' order by u.nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarPTG() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='00' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarMT() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='01' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();
        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarJasin() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='02' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPendaftarAG() {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama ='4' and u.kodCawangan ='03' order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            return q.list();

        } finally {
        }
    }

    public List<HakmilikKertas> getSenaraiHakmilikKertas(Long id) {

        String query = "Select hk from etanah.model.HakmilikKertas hk where hk.idHakmilikKertas = :id ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

//     public List<HakmilikKertas> cariSenaraiHakmilikKertasbyPengguna(String idPengguna) {
//
//            String query = "from etanah.model.HakmilikKertas hk where hk.pengguna.idPengguna =:idPengguna";
//            Session session = sessionProvider.get();
//            Query q = session.createQuery(query);
//            q.setString("idPengguna", idPengguna);
//            return q.list();
//    }
    public HakmilikKertas cariHakmilikKertasByNoSiri(String noSiri) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikKertas hk where :noSiri between hk.noAwal and hk.noAkhir");
        q.setString("noSiri", noSiri);
        return (HakmilikKertas) q.uniqueResult();
    }

    public HakmilikKertas searchHakmilikKertasbyPengguna(String idPengguna) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikKertas hk where hk.pengguna.idPengguna = :idPengguna");
        q.setString("idPengguna", idPengguna);
        return (HakmilikKertas) q.uniqueResult();
    }

    public List<HakmilikKertas> cariSenaraiHakmilikKertasbyPengguna(String idPengguna) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikKertas hk where hk.pengguna.idPengguna =:idPengguna");
        q.setString("idPengguna", idPengguna);
        return q.list();
    }

    public List<HakmilikKertas> cariNoAwal(String noAwal) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikKertas hk where hk.noAwal =:noAwal");
        q.setString("noAwal", noAwal);
        return q.list();
    }

    public List<HakmilikKertas> cariNoAkhir(String noAkhir) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from etanah.model.HakmilikKertas hk where hk.noAkhir =:noAkhir");
        q.setString("noAkhir", noAkhir);
        return q.list();
    }

    public List<HakmilikKertas> cariSenaraiKertasAntaraNoAwalDanNoAkhirA(String noAwal, String noAkhir) {
        //try {
        String query = "Select hk from etanah.model.HakmilikKertas hk where hk.noAwal between :noAwal and :noAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noAwal", noAwal);
        q.setString("noAkhir", noAkhir);
        return q.list();
        // }
        // finally {
        // }
    }

    public List<HakmilikKertas> cariSenaraiKertasAntaraNoAwalDanNoAkhirB(String noAwal, String noAkhir) {
        //try {
        String query = "Select hk from etanah.model.HakmilikKertas hk where hk.noAkhir between :noAwal and :noAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noAwal", noAwal);
        q.setString("noAkhir", noAkhir);
        return q.list();
        //}
        // finally {
        // }
    }

    public PermohonanKutipanDokumen findByIdPemohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT p FROM etanah.model.PermohonanKutipanDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKutipanDokumen) q.uniqueResult();
    }

    public List<PermohonanKutipanDokumen> getSenaraiPermohonanKutipanDokumen(Long id) {
        String query = "SELECT mk FROM etanah.model.PermohonanKutipanDokumen mk WHERE mk.idKutipan = :id ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public PermohonanKutipanDokumen findById(Long id) {
        return permohonanKutipanDokumenDAO.findById(id);
    }

    public List<PermohonanKutipanDokumen> listByIdPermohonan(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanKutipanDokumen p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //********** list kandungan_folder by id Folder and kod Dokumen 'DHKE', '4K' *************
    public List<KandunganFolder> findByIdFolder(String idFolder) {
        String query = "SELECT p FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :id_folder AND (p.dokumen.kodDokumen.kod = 'DHKE' OR  p.dokumen.kodDokumen.kod = '4K') ORDER BY p.dokumen.kodDokumen.kod DESC" ;
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id_folder", idFolder);
        return q.list();
    }

// *************** List folder_dok by id_folder and kod_dok ************
    public List<KandunganFolder> findByIdFolderKodDok(String idFolder, String kodDokumen) {
        String query = "Select p FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :id_folder AND p.dokumen.kodDokumen.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id_folder", idFolder);
        q.setString("kod", kodDokumen);
        return q.list();
    }

// *************** seacrh folder_dok by id_folder ************
    public KandunganFolder findByIdFolderKodDok1(String idFolder) {
        String query = "Select p FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :id_folder";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id_folder", idFolder);
        return (KandunganFolder) q.uniqueResult();
    }

//************* List Mohon_Hakmilik by kod_penyerah and id_penyerah ************
    public List<HakmilikPermohonan> findByKodPenyerahAndIdPenyerah(String kodPenyerah, String idPenyerah) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.kodPenyerah.kod = :kod AND p.permohonan.idPenyerah =:idPenyerah";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kod", kodPenyerah);
        q.setString("idPenyerah", idPenyerah);
        return q.list();
    }

//************* List Mohon_Hakmilik by id_hm ************
    public List<HakmilikPermohonan> findByIdHakmilik(String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmlik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

//************* List Mohon_Hakmilik by id_mohon ************
    public List<HakmilikPermohonan> findByIdPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//************* List Mohon_Hakmilik by id_hm and id_mohon ************
    public List<HakmilikPermohonan> findByIdHmAndIdMohon(String idHakmilik, String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmlik.idHakmilik = :idHakmilik AND p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

//************* Search Mohon_Hakmilik by id_hm and id_mohon ************
    public HakmilikPermohonan searchByIdHmAndIdMohon(String idHakmilik, String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.hakmlik.idHakmilik = :idHakmilik AND p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    @Transactional
    public void simpanHakmilikKertas(HakmilikKertas hakmilikKertas) {
        hakmilikKertasDAO.saveOrUpdate(hakmilikKertas);
    }

    @Transactional
    public void simpanKutipanDokumen(PermohonanKutipanDokumen permohonanKutipanDokumen) {
        permohonanKutipanDokumenDAO.saveOrUpdate(permohonanKutipanDokumen);
    }
}
