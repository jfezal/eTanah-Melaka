/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;  

/**
 *
 * @author massita
 */
import etanah.model.Permohonan;
import java.util.List;

import org.hibernate.Query;
import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.PermohonanTuntutanKos;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.LaporanPemulihanTanahDAO;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.dao.PihakDAO;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKlasifikasi;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.report.ReportUtil;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PengambilanService1 {

    private static final Logger logger = Logger.getLogger(RegService.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    LaporanPemulihanTanahDAO laporanPemulihanTanahDAO;
    Permohonan permohonan;
    PermohonanRujukanLuar permohonanRujukanLuar;
    PermohonanPengambilan permohonanPengambilan;
    Hakmilik hakmilik;
    //@Inject
    List<PermohonanTuntutanKos> senaraiTuntutanKos;
    List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan;

    public List<PermohonanRujukanLuar> findByIDMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan"
                + "AND b.dokumen.kodDokumen where kodDokumen = 'K'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanPengambilan findByIdMohon (String idPermohonan)
    {
         String queryStr = "SELECT b FROM etanah.model.ambil.PermohonanPengambilan b where b.permohonan.idPermohonan = :idPermohonan";
       return (PermohonanPengambilan) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Permohonan findByIdPermohonan2 (String idPermohonan)
    {
      String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan";
       return (Permohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }
    
    public Permohonan findByIdPermohonanPB (String idPermohonan)
    {
      String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonan";
       return (Permohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

//    public PermohonanRujukanLuar findByIDMohonRujukanLuar(String idPermohonan) {
//        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan And b.kodDokumenRujukan.kod='K'";
//        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idPermohonan", idPermohonan).uniqueResult();
//    }
//
    public PermohonanRujukanLuar findByIDMohonRujukanLuar(String idSebelum) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idSebelum";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idSebelum", idSebelum).uniqueResult();
    }

    public PermohonanRujukanLuar findByIDMohonRujukanLuar1(String idSebelum) {
        String queryStr = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.permohonanSebelum = :idSebelum";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(queryStr).setString("idSebelum", idSebelum).uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findbyIdHakmilik (String idHakmilik){
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

     public List<HakmilikPermohonan> findbyIdHakmilik1 (String idHakmilik){
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
     
      public FasaPermohonan findFasaPermohonan(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '01DrafPenarikanBalik'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }
      
        public FasaPermohonan findFasaPermohonanMMK(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = '09KeputusanMMK'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

//      public List<PermohonanTuntutanKos> findByIDMohonKos(Permohonan permohonan){
//        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idPermohonan", permohonan.getIdPermohonan());
//        return q.list();
//    }

     public List<PermohonanTuntutanKos> findByIDMohonKos(String idPermohonan){
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permohonan findByIdSblm(String idSebelum) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idSebelum";
        return (Permohonan) sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).uniqueResult();
    }
    public List<Pemohon> findByIdSblm2(String idSebelum) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idSebelum";
        return  sessionProvider.get().createQuery(query).setString("idSebelum", idSebelum).list();
    }

    public Pihak findByIdPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        return (Pihak) sessionProvider.get().createQuery(query).setLong("idPihak", idPihak).uniqueResult();
    }

    public List<Pihak> findByIdPihakList (Long idPihak){
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    public Hakmilik findByIdHakmilik(String idHakmilik) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.idHakmilik = :idHakmilik";
        return (Hakmilik) sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).uniqueResult();
    }

    public PermohonanRujukanLuar findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanTuntutanKos findTuntutanByIdKos(Long idKos) {
        String query = "SELECT p FROM etanah.model.PermohonanTuntutanKos p WHERE p.idKos = :idKos";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findTuntutanByIdPermohonanPihak(Long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.pihak.idPermohonanPihak = :idPermohonanPihak";
        return (PermohonanTuntutanKos) sessionProvider.get().createQuery(query).setLong("idPermohonanPihak", idPermohonanPihak).uniqueResult();
//        + "AND b.pihak.pihak.idPihak where idPihak = idPihak"
    }

    // added  by Rajesh
     public List<PermohonanTuntutanKos> findByIdPermohonanPihak2(Long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b WHERE b.pihak.idPermohonanPihak = :idPermohonanPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return  q.list();
    }

    public PermohonanPengambilan findNoWartaByIdPengambilan(Long idPengambilan){
        String query = "SELECT p FROM etanah.model.ambil.PermohonanPengambilan p WHERE p.idPengambilan = :idPengambilan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPengambilan", idPengambilan);
        return (PermohonanPengambilan) q.uniqueResult();
    }

    public List<KandunganFolder> generateReport(Permohonan mohon, Pengguna pguna) {
        Transaction tx = sessionProvider.get().beginTransaction();
        FolderDokumen fd = mohon.getFolderDokumen();
        List<KandunganFolder> akf = fd.getSenaraiKandungan();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);

        // draft kertas penarikan balik
        Dokumen d = new Dokumen();
        //d.setKodDokumen(kd);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        d.setInfoAudit(ia);
        //d.setTajuk(kd.getKod());
        d.setNoVersi("1.0");
        d.setKlasifikasi(klasifikasiAm);
        dokumenDAO.save(d);
        // run report generator
        KandunganFolder k = new KandunganFolder();
        k.setFolder(fd);
        k.setDokumen(d);
        k.setInfoAudit(ia);
        akf.add(k);

        // kertas pertimbangan
        d = new Dokumen();
        //d.setKodDokumen(kd);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        d.setInfoAudit(ia);
        //d.setTajuk(kd.getKod());
        d.setNoVersi("1.0");
        d.setKlasifikasi(klasifikasiAm);
        dokumenDAO.save(d);
        // run report generator
        k = new KandunganFolder();
        k.setFolder(fd);
        k.setDokumen(d);
        k.setInfoAudit(ia);
        akf.add(k);

        // surat kos
        d = new Dokumen();
        //d.setKodDokumen(kd);
        ia.setDimasukOleh(pguna);
        ia.setTarikhMasuk(new Date());
        d.setInfoAudit(ia);
        //d.setTajuk(kd.getKod());
        d.setNoVersi("1.0");
        d.setKlasifikasi(klasifikasiAm);
        dokumenDAO.save(d);
        // run report generator
        //http://10.0.4.32:9001/reports/rwservlet?server=et_reports&report=/srv/reports/ACQ_Surat_R30_NS.rdf&userid=testing/kickstart@jvetanah&destype=cache&desformat=pdf
        k = new KandunganFolder();
        k.setFolder(fd);
        k.setDokumen(d);
        k.setInfoAudit(ia);
        akf.add(k);

        // dah add smua dokumen
        fd.setSenaraiKandungan(akf);
        folderDokumenDAO.save(fd);
        tx.commit();
        return akf;
    }

//        public List<KodDokumen> findByKodDokumen(String kodDokumen){
//        String query = "SELECT k FROM etanah.model.KodDokumen k where b.kod = :kod";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("kodDokumen", kodDokumen);
//        return q.list();
//        }
//        public KodDokumen getCheckBorangK(String kod )
//        {
//            String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = : idSebelum " +
//                           "(SELECT k FROM etanah.model.KodDokumen k where b.kod = 'K'";
//            return (KodDokumen) sessionProvider.get().createQuery(query)
//                    .setString("kod",kod).uniqueResult();
//	}
//       public List<KodDokumen> getKodDokumenNotRequired(ArrayList<String> senaraiKodUrusan) {
//		String hql = "select kd from KodDokumen kd where kd not in " +
//				"(select1 d from UrusanDokumen ud " +
//				"inner join ud.kodDokumen d " +
//				"where ud.kodUrusan.id in (:senaraiKodUrusan)) " +
//				"order by kd.nama";
//
//		return sessionProvider.get().createQuery(hql).
//	}			setParameterList("senaraiKodUrusan", senaraiKodUrusan).list();
    @Transactional
    public void simpanNamaPemohon(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }
    @Transactional
    public void simpanPihak(Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
    }

    @Transactional
    public void simpanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.saveOrUpdate(permohonanTuntutanKos);
   }

    @Transactional
    public void deleteAll(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }

     @Transactional
    public void delete() {
        permohonanTuntutanKosDAO.delete(null);
    }

      @Transactional
    public void delete(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonanTuntutanKosDAO.delete(permohonanTuntutanKos);
    }
       @Transactional
    public void save(PermohonanPengambilan permohonanPengambilan){
       permohonanPengambilanDAO.save(permohonanPengambilan);
    }

    @Transactional
    public void update(PermohonanPengambilan permohonanPengambilan){
       permohonanPengambilanDAO.update(permohonanPengambilan);
    }

      @Transactional
      public void simpanWarta(PermohonanPengambilan permohonanPengambilan) {
        permohonanPengambilanDAO.saveOrUpdate(permohonanPengambilan);
      }
      @Transactional
    public boolean saveSingleHakmilikPerbicaraan(HakmilikPerbicaraan hp){
        hp = (HakmilikPerbicaraan)hakmilikPerbicaraanDAO.saveOrUpdate(hp);
        return (hp !=null);
    }

    public PermohonanPengambilan findByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.ambil.PermohonanPengambilan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanPengambilan) q.uniqueResult();

    }

    @Transactional
    public void simpanPermohonanPihak(Permohonan permohonan,Pengguna peng){

        String idPermohonan = permohonan.getIdPermohonan();

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hp: listHp){
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                for (HakmilikPihakBerkepentingan hakmilikPihak: hakmilikPihakList){

                    PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilikPihak.getHakmilik().getIdHakmilik(),hakmilikPihak.getPihak().getIdPihak());
                      if(pp == null){
                        PermohonanPihak newPP = new PermohonanPihak();
                        newPP.setPermohonan(permohonan);
                        newPP.setHakmilik(hakmilikPihak.getHakmilik());
                        newPP.setPihak(hakmilikPihak.getPihak());
                        newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                        newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                        newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                        newPP.setCawangan(permohonan.getCawangan());
                        newPP.setInfoAudit(ia);
//                        KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                        newPP.setJenis(findByIdKodPihak("PJK"));
                        permohonanPihakDAO.save(newPP);
                  }
                }
        }

    }
    
    @Transactional
    public void simpanPermohonanSebelum(Permohonan permohonan,Pengguna peng){

        permohonanDAO.saveOrUpdate(permohonan);
    
//       Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        if (peng.getPerananUtama().getKod().equals("6")) {
//
//            getContext().getRequest().setAttribute("tptd1", Boolean.TRUE);
//        }

        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(peng);
        ia.setTarikhMasuk(new Date());

        // save the HakmilikPermohonan from permohonanSebelum
        Permohonan pSblm = permohonan.getPermohonanSebelum();
//        List<PermohonanPihak> listPP = pSblm.getSenaraiPihak();
//        for (PermohonanPihak pp: listPP){
//            PermohonanPihak newPP = new PermohonanPihak();
//            newPP.setPermohonan(permohonan);
//            newPP.setHakmilik(pp.getHakmilik());
//            newPP.setPihak(pp.getPihak());
//            newPP.setPihakCawangan(pp.getPihakCawangan());
//            newPP.setCawangan(pp.getCawangan());
//            newPP.setInfoAudit(ia);
//            permohonanPihakDAO.save(pp);
//        }


        String idSblm = pSblm.getIdPermohonan();


        //to copy data from table hakmilik_pihak to mohon_pihak - 2 table data will be same
        List<HakmilikPermohonan> listHp = pSblm.getSenaraiHakmilik();
        for (HakmilikPermohonan hp: listHp){
            List<HakmilikPihakBerkepentingan> hakmilikPihakList = hp.getHakmilik().getSenaraiPihakBerkepentingan();
                for (HakmilikPihakBerkepentingan hakmilikPihak: hakmilikPihakList){

                    PermohonanPihak pp = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilikPihak.getHakmilik().getIdHakmilik(),hakmilikPihak.getPihak().getIdPihak());
                        System.out.println("----------pp------------"+pp);
                      if(pp == null){
                        PermohonanPihak newPP = new PermohonanPihak();
                        newPP.setPermohonan(pSblm);
                        newPP.setHakmilik(hakmilikPihak.getHakmilik());
                        newPP.setPihak(hakmilikPihak.getPihak());
                        newPP.setPihakCawangan(hakmilikPihak.getPihakCawangan());
                        newPP.setSyerPembilang(hakmilikPihak.getSyerPembilang());
                        newPP.setSyerPenyebut(hakmilikPihak.getSyerPenyebut());
                        newPP.setCawangan(pSblm.getCawangan());
                        newPP.setInfoAudit(ia);
                        KodJenisPihakBerkepentingan kodPihak = new KodJenisPihakBerkepentingan();
                        newPP.setJenis(findByIdKodPihak("PJK"));
                        System.out.println("------Save Record -----"+pSblm.getIdPermohonan()+"-------"+hakmilikPihak.getPihak().getIdPihak());
                        permohonanPihakDAO.save(newPP);
                  }
                }
        }
        //permohonanDAO.saveOrUpdate(permohonan);

    }


    public KodJenisPihakBerkepentingan findByIdKodPihak(String kodPihak) {
        String query = "SELECT b FROM etanah.model.KodJenisPihakBerkepentingan b WHERE b.kod = :kodPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodPihak", kodPihak);
        return  (KodJenisPihakBerkepentingan)q.uniqueResult();
    }


    public PermohonanPihak findByIdSblmIdPihak(String idSblm,Long idPihak){
        String query = "SELECT b FROM etanah.model.PermohonanPihak b where b.permohonan.idPermohonan = :idPermohonan "
                + " AND b.pihak.idPihak := idPihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idSblm);
        q.setLong("idPihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public AmbilPampasan getAmbilPampasanByidMP( long idPermohonanPihak) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b where b.permohonanPihak.idPermohonanPihak = :idPermohonanPihak " ;
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        return (AmbilPampasan) q.uniqueResult();

    }

    @Transactional
    public void saveOrupdateAmbilPampasan(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.saveOrUpdate(ambilPampasan);
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraanByidMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public PermohonanPengambilan getPermohonanPengambilanByidSblm(String idSblm) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PermohonanPengambilan m WHERE m.permohonan.idPermohonan = :idSblm ");
        q.setString("idSblm", idSblm);
        return (PermohonanPengambilan) q.uniqueResult();

    }

    @Transactional
    public HakmilikPerbicaraan saveHakmilikPerbicaraan(HakmilikPerbicaraan hp){
        hp = (HakmilikPerbicaraan)hakmilikPerbicaraanDAO.saveOrUpdate(hp);
        return hp;
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanahByidMHidMP(long idMH,long idMP){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP ");
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }
    
    public LaporanPemulihanTanah getLaporanPemulihanTanahByidMHidMP2(long idMH,long idMP,String idMohon){
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.LaporanPemulihanTanah m WHERE m.permohonan.idPermohonan = :idMohon and m.hakmilikPermohonan.id = :idMH and m.permohonanPihak.idPermohonanPihak = :idMP ");
        q.setParameter("idMH", idMH);
        q.setParameter("idMP", idMP);
        q.setParameter("idMohon", idMohon);
        return (LaporanPemulihanTanah) q.uniqueResult();
    }

    @Transactional
    public LaporanPemulihanTanah simpanLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah){
        laporanPemulihanTanah = laporanPemulihanTanahDAO.saveOrUpdate(laporanPemulihanTanah);
        return laporanPemulihanTanah;
    }

    public PermohonanPihak getPmohonPihakByIdHakmilikIdPihak(String idMohon,String idHakmilik,Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranbyidMPidBicara(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    public List<AmbilPampasan> getAmbilPampasanListByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return  q.list();
    }

    public FasaPermohonan findFasaPermohonan_KeputusanNIDAliran(String idPermohonan, String idAliran) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = :idAliran";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    public Permohonan findPermohonanKodKpsn(String idPermohonan, String kod) {
        String query = "Select p FROM etanah.model.Permohonan p WHERE p.idPermohonan = :idPermohonan and p.keputusan.kod = :kod";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanPihak getSenaraiPmohonPihakByIdHakmilikIdPihak(String idMohon,String idHakmilik,Long idPihak) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :id_mohon AND m.hakmilik.idHakmilik = :id_Hakmilik AND m.pihak.idPihak = :id_Pihak");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("id_Hakmilik", idHakmilik);
        q.setParameter("id_Pihak", idPihak);
        return (PermohonanPihak) q.uniqueResult();
    }
}