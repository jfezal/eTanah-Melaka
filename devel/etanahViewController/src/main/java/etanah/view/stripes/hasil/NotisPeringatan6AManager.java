/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.DasarTuntutanNotisDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanCukai;
import etanah.model.Hakmilik;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Dokumen;
import etanah.model.InfoAudit;
import etanah.model.KodNotis;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.Pengguna;
import etanah.model.Transaksi;
import static etanah.service.HakmilikService.isNotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

/**
 *
 * @author nurfaizati
 */
public class NotisPeringatan6AManager {

    private static final Logger LOG = Logger.getLogger(NotisPeringatan6AManager.class);
    private static boolean isDebug = LOG.isDebugEnabled();
//    private List<DasarTuntutanCukaiHakmilik> list;
    @Inject
    DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    TransaksiDAO transaksiDAO;
    @Inject
    DasarTuntutanNotisDAO dasarTuntutanNotisDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    private  etanah.kodHasilConfig khconf;
    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
    private List<Hakmilik> senaraiHakmilik;
    private List<Hakmilik> senaraiRealHakmilik;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;
    private List<DasarTuntutanNotis> senaraiDasarTuntutanNotis;

    @Transactional
    public String save(DasarTuntutanCukai dasarTuntutanCukai) {
        dasarTuntutanCukaiDAO.save(dasarTuntutanCukai);
        return dasarTuntutanCukai.getIdDasar();
    }

    @Transactional
    public void update(DasarTuntutanCukai dtc) {
        LOG.debug("update DasarTuntutanCukai");
        dasarTuntutanCukaiDAO.saveOrUpdate(dtc);
        LOG.debug("update DasarTuntutanCukai Berjaya");
    }

    @Transactional
    public void save(List<DasarTuntutanCukaiHakmilik> list) {

        for (DasarTuntutanCukaiHakmilik dtch : list) {
            dasarTuntutanCukaiHakmilikDAO.save(dtch);
        }
    }

    @Transactional
    public boolean updateDTCH(DasarTuntutanCukaiHakmilik dtch, Pengguna pengguna){
        boolean rtn = false;
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(dtch.getInfoAudit().getDimasukOleh());
        ia.setTarikhMasuk(dtch.getInfoAudit().getTarikhMasuk());
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        try{
            dtch.setInfoAudit(ia);
            dasarTuntutanCukaiHakmilikDAO.update(dtch);
            rtn = true;
        }catch(Exception ex){
            LOG.error(ex);
            rtn = false;
        }
        return rtn;
    }

    @Transactional
    public void saveNotis(DasarTuntutanNotis notis) {


        dasarTuntutanNotisDAO.save(notis);
        LOG.debug("idNotis :" + notis.getIdNotis());

    }

    @Transactional
    public void saveNotis(List<DasarTuntutanNotis> list2) {
        for (DasarTuntutanNotis dtn : list2) {
            dasarTuntutanNotisDAO.save(dtn);
        }
    }

    @Transactional
    public void updateNotis(DasarTuntutanNotis l) {
        LOG.debug("DasarTuntutanNotis:start");
        dasarTuntutanNotisDAO.update(l);
        LOG.debug("DasarTuntutanNotis:finish");
    }

    @Transactional
    public boolean updateRekodNotis(DasarTuntutanNotis dtn) {
        boolean rtn = true;
        LOG.debug("DasarTuntutanNotis:start");
        dasarTuntutanNotisDAO.update(dtn);
        LOG.debug("DasarTuntutanNotis:finish");
        return rtn;
    }

    public Hakmilik findById(String id) {
        return hakmilikDAO.findById(id);
    }

    public List<Hakmilik> findAll(Map<String, String[]> param, String id) {
        String query = "SELECT distinct p FROM etanah.model.Hakmilik p, etanah.model.Transaksi t, etanah.model.Akaun ak, etanah.model.HakmilikPihakBerkepentingan hpk" +
                "WHERE  p.idHakmilik = ak.hakmilik.id AND ak.kodAkaun.kod = 'AC' AND ak.baki > 0 AND t.akaunDebit = ak.noAkaun AND p.kodStatusHakmilik.kod <> 'B' AND p.idHakmilik = hpk.hakmilik.id";

        if (isNotBlank(param.get("hakmilik.idHakmilik"))) {
            query += "AND p.idHakmilik LIKE :idHakmilik ";
        }

        if (StringUtils.isNotBlank(id)) {
            query += "AND p.idHakmilik not IN(:id) ";
        }

        if (isNotBlank(param.get("hakmilik.noLot"))) {
            query += "AND p.noLot LIKE :noLot ";
        }
        if (isNotBlank(param.get("hakmilik.noHakmilik"))) {
            query += "AND p.noHakmilik LIKE :noHakmilik ";
        }


        if (isNotBlank(param.get("daerah"))) {
            query += "AND p.daerah.kod LIKE :daerah ";
        }

        if (isNotBlank(param.get("hakmilik.bandarPekanMukim.kod"))) {
            query += "AND p.bandarPekanMukim.kod LIKE :bandarPekanMukim ";
        }
        
        if (isNotBlank(param.get("hakmilik.seksyen.kod"))) {
            query += "AND p.seksyen.kod = :seksyen ";
        }
        
        if (isNotBlank(param.get("hakmilik.kategoriTanah.kod"))) {
            query += "AND p.kategoriTanah.kod LIKE :kategoriTanah ";
        }

    if (isNotBlank(param.get("bangsa"))) {
            query += "AND hpk.pihak.bangsa.kod = :bangsa ";
        }


        if (isNotBlank(param.get("amaunDari"))) {// && isNotBlank(param.get("amaunHingga"))) {
            query += "AND ak.baki >= :amaunDari ";//AND ak.baki <= :amaunHingga ";
        }

        if (isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))) {
            query += "AND t.untukTahun >= :tahunDari AND t.untukTahun <= :tahunHingga ";
        }

        LOG.debug("query: " + query);
        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("hakmilik.idHakmilik"))) {
            q.setString("idHakmilik", param.get("hakmilik.idHakmilik")[0]);
        }

        if (StringUtils.isNotBlank(id)) {
            q.setString("id", id);
        }

        if (isNotBlank(param.get("hakmilik.noLot"))) {
            q.setString("noLot", param.get("hakmilik.noLot")[0]);
        }
        if (isNotBlank(param.get("hakmilik.noHakmilik"))) {
            q.setString("noHakmilik", param.get("hakmilik.noHakmilik")[0]);
        }

        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0]);
        }
        if (isNotBlank(param.get("hakmilik.bandarPekanMukim.kod"))) {
            q.setString("bandarPekanMukim", param.get("hakmilik.bandarPekanMukim.kod")[0]);
        }
        if (isNotBlank(param.get("hakmilik.seksyen.kod"))) {
            q.setString("seksyen", param.get("hakmilik.seksyen.kod")[0]);
        }
        if (isNotBlank(param.get("hakmilik.kategoriTanah.kod"))) {
            q.setString("kategoriTanah", param.get("hakmilik.kategoriTanah.kod")[0]);
        }

        if (isNotBlank(param.get("bangsa"))) {
            q.setString("bangsa", param.get("bangsa")[0].trim());
        }
        if (isNotBlank(param.get("amaunDari"))) {// && isNotBlank(param.get("amaunHingga"))) {
            q.setString("amaunDari", param.get("amaunDari")[0]);
            //q.setString("amaunHingga", param.get("amaunHingga")[0]);
        }
        if (isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))) {
            q.setString("tahunDari", param.get("dariTahun")[0]);
            q.setString("tahunHingga", param.get("hinggaTahun")[0]);
        }

        senaraiHakmilik = q.list();
        return senaraiHakmilik;
    }

    public static boolean isNotBlank(String[] str) {
        return !isBlank(str);
    }

    public static boolean isBlank(String[] str) {
        if (str == null) {
            return true;
        }
        if (str.length > 0) {
            return isBlank(str[0]);
        }
        return true;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public List<Hakmilik> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<Hakmilik> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public List<DasarTuntutanNotis> getSenaraiDasarTuntutanNotis() {
        return senaraiDasarTuntutanNotis;
    }

    public void setSenaraiDasarTuntutanNotis(List<DasarTuntutanNotis> senaraiDasarTuntutanNotis) {
        this.senaraiDasarTuntutanNotis = senaraiDasarTuntutanNotis;
    }

    public List<Hakmilik> getSenaraiRealHakmilik() {
        return senaraiRealHakmilik;
    }

    public void setSenaraiRealHakmilik(List<Hakmilik> senaraiRealHakmilik) {
        this.senaraiRealHakmilik = senaraiRealHakmilik;
    }

    //For Notis Transaction
    @Transactional
    public String simpan6A(List<Hakmilik> senaraiHakmilik, Pengguna pengguna) {
        LOG.info("notis::start");
        String result = "";
        InfoAudit ia = new InfoAudit();
        ia.setDiKemaskiniOleh(pengguna);
        ia.setTarikhKemaskini(new java.util.Date());
        InfoAudit iaNew = new InfoAudit();
        iaNew.setDimasukOleh(pengguna);
        iaNew.setTarikhMasuk(new java.util.Date());
        iaNew.setDiKemaskiniOleh(pengguna);
        iaNew.setTarikhKemaskini(new java.util.Date());
//        Akaun akaun = null;
//        Session s = sessionProvider.get();
//        Transaction tx = s.beginTransaction();
        try {
            for (Hakmilik hakmilik : senaraiHakmilik) {
                //new transaction every each hakmilik
                Transaksi t = new Transaksi();
                KodTransaksi kt = new KodTransaksi();
                if("04".equals(conf.getProperty("kodNegeri"))){
                    kt.setKod(khconf.getProperty("notis6AMelaka")); // kod Notis 6A Tanah
                    t.setAmaun(new BigDecimal(20));//caj dikenakan utk keluarkan Notis 6A utk Melaka
                }else{
                    kt.setKod(khconf.getProperty("notis6A"));
                    t.setAmaun(new BigDecimal(10));//caj dikenakan utk keluarkan Notis 6A utk N9
                } // kod Notis 6A Tanah
                KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');
                t.setCawangan(pengguna.getKodCawangan());
                t.setKodTransaksi(kt);
                
                t.setStatus(status);
                t.setKuantiti(1);
                t.setInfoAudit(iaNew);
                t.setUntukTahun(new java.util.Date().getYear() + 1900);//getYear() starting 1900
                t.setTahunKewangan(new java.util.Date().getYear() + 1900);//getYear() starting 1900
                //update akaun every each hakmilik
                Akaun akaun = hakmilik.getAkaunCukai();
                akaun.setBaki(akaun.getBaki().add(t.getAmaun()));
                akaunDAO.update(akaun);
                t.setAkaunDebit(akaun);
                transaksiDAO.save(t);
                LOG.debug("(Simpan6A)idTrans: "+t.getIdTransaksi()+", akaun.hakmilik :"+akaun.getHakmilik().getIdHakmilik());
            }
            result = "berjaya";
//            tx.commit();
        } catch (Exception ex) {
//            tx.rollback();
            result = "gagal";
            LOG.error("notis error :" + ex);
        }
        LOG.info("notis::finish");
        return result;
    }

    // added by mansur 1/6/2010
    public List<Hakmilik> findAll(Map<String, String[]> param, Pengguna peng) {
//        String query = "SELECT distinct p FROM etanah.model.Hakmilik p, etanah.model.Transaksi t, etanah.model.Akaun ak, etanah.model.HakmilikPihakBerkepentingan hpk " +
////                "WHERE  p.idHakmilik = ak.hakmilik.id AND ak.kodAkaun.kod = 'AC' AND ak.baki > 0 AND t.kodTransaksi.kod in ('76152','61402') " +
//                "WHERE  p.idHakmilik = ak.hakmilik.id AND ak.kodAkaun.kod = 'AC' AND t.kodTransaksi.kod = '61402' " +
//                "AND t.akaunDebit = ak.noAkaun AND p.kodStatusHakmilik.kod <> 'B' AND p.idHakmilik = hpk.hakmilik.id ";
        String query = "SELECT distinct p FROM etanah.model.Hakmilik p, etanah.model.Transaksi t, etanah.model.Akaun ak, etanah.model.HakmilikPihakBerkepentingan hpk " +
//                "WHERE  p.idHakmilik = ak.hakmilik.id AND ak.kodAkaun.kod = 'AC' AND ak.baki > 0 AND t.kodTransaksi.kod in ('76152','61402') " +
                "WHERE  p.idHakmilik = ak.hakmilik.id AND ak.kodAkaun.kod = 'AC' " +
//                "AND p.cawangan.kod = :kodCaw " +
//                "AND t.kodTransaksi.kod = '61402' " +
                "AND t.akaunDebit = ak.noAkaun " +
                "AND p.kodStatusHakmilik.kod <> 'B' AND p.idHakmilik = hpk.hakmilik.id ";
        if((peng.getKodCawangan().getKod().equals("00"))||
                (peng.getKodCawangan().getKod().equals("08"))){ // utk user GEMAS ; simulasi 11-Nov-2014 START
            query += "AND p.cawangan.kod = :kodCaw ";
        }
        // END
        else{
            query += "AND p.daerah.kod = :kodCaw ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND p.idHakmilik LIKE :idHakmilik ";
        }

        if (isNotBlank(param.get("noLot"))) {
            query += "AND p.noLot LIKE :noLot ";
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND p.noHakmilik LIKE :noHakmilik ";
        }


        if (isNotBlank(param.get("daerah"))) {
            query += "AND p.daerah.kod LIKE :daerah ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND p.bandarPekanMukim.kod LIKE :bandarPekanMukim ";
        }
        if (isNotBlank(param.get("seksyen"))) {
            query += "AND p.seksyen.kod = :seksyen ";
        }
        
        if (isNotBlank(param.get("kategoriTanah"))) {
            query += "AND p.kategoriTanah.kod LIKE :kategoriTanah ";
        }
          if (isNotBlank(param.get("bangsa"))) {
            query += "AND hpk.pihak.bangsa.kod = :bangsa ";
        }


//        if (isNotBlank(param.get("amaunDari"))) {// && isNotBlank(param.get("amaunHingga"))) {
//            query += "AND ak.baki >= :amaunDari ";//AND ak.baki <= :amaunHingga ";
//            LOG.debug("ada msuk amaun");
//        }else{
//            // if label tunggakan is null
//            query += "AND ak.baki >= 0 ";
//            LOG.debug("tidak msuk amaun");
//        }
        if(isNotBlank(param.get("amaunDari")) && isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND ak.baki > 0 ";
            query += "AND (select sum(tran.amaun) from etanah.model.Transaksi tran where ak.noAkaun = tran.akaunDebit and tran.untukTahun >= :tahunDari AND tran.untukTahun <= :tahunHingga) >= :amaunDari ";//AND ak.baki <= :amaunHingga ";
            LOG.debug("ada msuk amaun dan tahun");
        }else if(isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))){
            query += "AND ak.baki > 0 ";
            query += "AND t.untukTahun >= :tahunDari AND t.untukTahun <= :tahunHingga ";
            LOG.debug("ada msuk tahun shj");
        }else if (isNotBlank(param.get("amaunDari"))) {// && isNotBlank(param.get("amaunHingga"))) {
            query += "AND ak.baki > 0 ";
            query += "AND (select sum(tran.amaun) from etanah.model.Transaksi tran where ak.noAkaun = tran.akaunDebit) >= :amaunDari ";//AND ak.baki <= :amaunHingga ";
            LOG.debug("ada msuk amaun shj");
        }else{
            // if label tunggakan is null
            query += "AND ak.baki > 0 ";
            LOG.debug("tidak masuk amaun");
        }

//        if (isNotBlank(param.get("dariTahun"))){// && isNotBlank(param.get("hinggaTahun"))) {
//            query += "AND t.untukTahun >= :tahunDari";// AND t.untukTahun <= :tahunHingga ";
//        }

        LOG.debug("query: " + query);
        Query q = sessionProvider.get().createQuery(query);
        LOG.info("kodCawangan Pengguna :"+peng.getKodCawangan().getKod());
        q.setString("kodCaw", peng.getKodCawangan().getKod());

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", "%"+param.get("idHakmilik")[0]+"%");
            LOG.info("idHakmilik :"+param.get("idHakmilik")[0]);
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%"+param.get("noLot")[0]+"%");
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%"+param.get("noHakmilik")[0]+"%");
        }

        if (isNotBlank(param.get("daerah"))) {
            // utk user GEMAS ; simulasi 11-Nov-2014 START
            if (peng.getKodCawangan().getKod().equals("08")){
                q.setString("daerah", "06");                
            }
            // END
            else{
                q.setString("daerah", param.get("daerah")[0]);}
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0]);
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0]);
        }
        if (isNotBlank(param.get("kategoriTanah"))) {
            q.setString("kategoriTanah", param.get("kategoriTanah")[0]);
        }
          if (isNotBlank(param.get("bangsa"))) {
            q.setString("bangsa", param.get("bangsa")[0].trim());
        }
        if (isNotBlank(param.get("amaunDari"))) {// && isNotBlank(param.get("amaunHingga"))) {
            q.setString("amaunDari", param.get("amaunDari")[0]);
            //q.setString("amaunHingga", param.get("amaunHingga")[0]);
        }
        if (isNotBlank(param.get("dariTahun")) && isNotBlank(param.get("hinggaTahun"))) {
            q.setString("tahunDari", param.get("dariTahun")[0]);
            q.setString("tahunHingga", param.get("hinggaTahun")[0]);
        }

        senaraiHakmilik = q.list();
        LOG.debug("senaraiHakmilik.size :"+senaraiHakmilik.size());
        return senaraiHakmilik;
    }

    public List<DasarTuntutanCukaiHakmilik> senaraiDtch(Hakmilik hm){
        String query = "SELECT dtch FROM etanah.model.DasarTuntutanCukaiHakmilik dtch " +
                "WHERE dtch.hakmilik.idHakmilik = :idHakmilik order by dtch.infoAudit.tarikhMasuk asc";
//        LOG.debug("query: " + query);
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilik", hm.getIdHakmilik());
//        LOG.debug("q.list.size :"+q.list().size());
        return q.list();
    }



    public List<KodNotis> doSenaraiKodNotis(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodNotis kn where kn.kod in (:kod1,:kod2,:kod3,:kod4) order by kn.nama desc");
        q.setString("kod1", "SP");
        q.setString("kod2", "N6A");
        q.setString("kod3", "NG");
        q.setString("kod4", "N8A");
        LOG.debug("q.list().size :"+q.list().size());
        return q.list();
    }

    public List<KodNotis> doSenaraiKodNotisHantar(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodNotis kn where kn.kod in (:kod1,:kod2,:kod3) order by kn.nama desc");
        q.setString("kod1", "SP");
        q.setString("kod2", "N6A");
        q.setString("kod3", "N8A");
        LOG.debug("q.list().size :"+q.list().size());
        return q.list();
    }

    public List<KodNotis> doSenaraiKodNotisWarta(){
        Session s = sessionProvider.get();
        Query q = s.createQuery("from KodNotis kn where kn.kod in (:kod1,:kod2) order by kn.nama desc");
        q.setString("kod1", "NG");
        q.setString("kod2", "N8A");
        LOG.debug("q.list().size :"+q.list().size());
        return q.list();
    }

    @Transactional
    public long simpanOrUpdateDokumen(Dokumen dokumen){
        long idDokumen = new Long(1L); // initialize long
        try{
            dokumen = dokumenDAO.saveOrUpdate(dokumen);
            idDokumen = dokumen.getIdDokumen();
        }catch(Exception ex){
            LOG.error("simpanOrUpdateDokumen tidak berjaya :"+ex);
            ex.printStackTrace();
        }
        return idDokumen;
    }

    @Transactional
    public String deleteDasar(DasarTuntutanCukai dasar){
        String result = "success";
        try{
            if(dasar.getSenaraiHakmilik().size() > 0){
                LOG.info("(deleteDasar) have hakmilik :"+dasar.getSenaraiHakmilik().size());
                for (DasarTuntutanCukaiHakmilik dtch : dasar.getSenaraiHakmilik()) {
                    LOG.info("(deleteDasar) idDTCH :"+dtch.getIdDasarHakmilik()+", idHakmilik :"+dtch.getHakmilik().getIdHakmilik());
                    dasarTuntutanCukaiHakmilikDAO.delete(dtch);
                }
            }
            LOG.info("(deleteDasar) idDasar :"+dasar.getIdDasar());
            dasarTuntutanCukaiDAO.delete(dasar);
        }catch(Exception ex){
            LOG.error("(deleteDasar) ex :"+ex);
            result = "fail";
            ex.printStackTrace(); // for development only
        }
        LOG.debug("(deleteDasar) result :"+result);
        return result;
    }

    @Transactional
    public String updateListDtn(List<DasarTuntutanNotis> senaraiDtn, Pengguna peng){
        String result = "success";
        InfoAudit ia = new InfoAudit();
        Date now = new Date();
        try{
            for (DasarTuntutanNotis dtn : senaraiDtn) {
                LOG.info("(updateListDtn) idDCN :"+dtn.getIdNotis());
                ia = dtn.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(now);
                dtn.setInfoAudit(ia);

                dasarTuntutanNotisDAO.save(dtn);
            }
        }catch(Exception ex){
            result = "fail";
            LOG.error("(SimpanWarta) ex :"+ex);
            ex.printStackTrace(); // for development only
        }

        return result;
    }
}
