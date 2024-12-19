
 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.model.FasaPermohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanPlotPelan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanUkur;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author massita
 */
public class HakLalulalangService {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fp){
        fasaPermohonanDAO.saveOrUpdate(fp);
    }

    @Transactional
    public void simpanPermohonanTuntutanKos(PermohonanTuntutanKos ptk){
        permohonanTuntutanKosDAO.saveOrUpdate(ptk);
    }

    @Transactional
     public void deleteKertas(PermohonanKertas pk) {
        permohonanKertasDAO.delete(pk);
     }

     @Transactional
     public void deleteKertasKandungan(PermohonanKertasKandungan pkk) {
        permohonanKertasKandunganDAO.delete(pkk);
     }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas){
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan){
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanKertas(PermohonanKertas permohonanKertas, PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPlotPelan(PermohonanPlotPelan mohonPlotPelan){
        permohonanPlotPelanDAO.saveOrUpdate(mohonPlotPelan);
    }

    @Transactional
    public void simpanMohonAmbil(PermohonanPengambilan mohonAmbil){
        permohonanPengambilanDAO.saveOrUpdate(mohonAmbil);
    }

    @Transactional
    public void simpanPermohonanUkur(PermohonanUkur mohonUkur){
        permohonanUkurDAO.saveOrUpdate(mohonUkur);
    }

    @Transactional
    public void deletePermohonanPlotPelan(PermohonanPlotPelan ppp){
        permohonanPlotPelanDAO.delete(ppp);
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanByIdPermohonan(String idPermohonan){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    
    public PermohonanKertas findById(String idKertas) {
        return permohonanKertasDAO.findById(Long.valueOf(idKertas));
    }

    public List<PermohonanKertas> findPermohonanKertasByIdPermohonan(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.tajuk = :tajuk");
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasKandunganByJabatan(String idPermohonan, String jabatanTeknikal){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk, etanah.model.PermohonanKertas pk where pk.idKertas = pkk.kertas.idKertas and pk.permohonan.idPermohonan = :idMohon and pkk.subtajuk = :subtajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("subtajuk", jabatanTeknikal);
        return (PermohonanKertasKandungan)q.uniqueResult();
    }

    public PermohonanKertasKandungan findKertasKandunganByIdKertas(String idKertas){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk where pkk.kertas.idKertas = :idKertas");
        q.setString("idKertas", idKertas);
        return (PermohonanKertasKandungan)q.uniqueResult(); 
    }
    
    public PermohonanKertas findKertasByTajuk(String idPermohonan, String tajuk){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idMohon and pk.tajuk = :tajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("tajuk", tajuk);
        return (PermohonanKertas)q.uniqueResult();
    }
      
    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah)q.uniqueResult();
    }

    
    /**
     * Method to find FasaPermohanan By Id
     * By Imran Khan
     * @param idPermohonan
     * @param idAliran
     * @return FasaPermohonan
     *
     */
    public FasaPermohonan findFasaPermohonanByIdAliran(String idPermohonan,String idAliran){
       Session s = sessionProvider.get();
       Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = :idAliran");
       q.setString("idPermohonan", idPermohonan);
       q.setString("idAliran", idAliran);
       return (FasaPermohonan)q.uniqueResult();
    }

    // Added By NageswaraRao

    public PermohonanUkur findPermohonanUkurByIdPermohonan(String idPermohonan){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
       return (PermohonanUkur)q.uniqueResult();
    }

     // Added by NageswaraRao
    public PermohonanPengambilan findPermohonanPengambilanByIdPermohonan(String idPermohonan){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.ambil.PermohonanPengambilan lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
       return (PermohonanPengambilan)q.uniqueResult();
    }


    // Added by NageswaraRao
    public List<PermohonanPlotPelan> senaraiPermohonanPlotPelanByIdPermohonan(String idPermohonan){
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.noPt is not null ORDER BY lt.noPt asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    // Added by NageswaraRao
    public PermohonanPlotPelan getMaxNoPTofPermohonanPlotPelan(){
         Session s = sessionProvider.get();
         Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.noPt=(Select MAX(lt1.noPt) from etanah.model.PermohonanPlotPelan lt1)");
         return (PermohonanPlotPelan)q.uniqueResult();
    }

    // Added by NageswaraRao
    @Transactional
    public void simpanPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan){
        permohonanPlotPelanDAO.saveOrUpdate(permohonanPlotPelan);
    }

    // Added by NageswaraRao
    public PermohonanUkur getMaxNoPUofPermohonanUkur(String kodCaw){
         Session s = sessionProvider.get();
         Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.noPermohonanUkur=(Select MAX(lt1.noPermohonanUkur) from etanah.model.PermohonanUkur lt1 where lt1.noPermohonanUkur LIKE :kodCaw)");
         q.setString("kodCaw", "%"+kodCaw+"%");
         return (PermohonanUkur)q.uniqueResult();
    }


 }
     
