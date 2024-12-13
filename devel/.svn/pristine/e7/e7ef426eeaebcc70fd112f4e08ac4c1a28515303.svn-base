/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.common;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author fikri
 */
public class HakmilikUrusanService {

    public final static String[] SENARAI_KAVEAT = {
        "KVAS",
        "KVAT",
        "KVLK",
        "KVLP",
        "KVLS",
        "KVLT",
        "KVPK",
        "KVPPT",
        "KVPS",
        "KVPT",
        "KVSK",
        "KVSS",
        "KVST",
        "KVPB",
        "KVSPT"};
    public List<String> SENARAI_URUSAN = new ArrayList<String>();
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOGGER = Logger.getLogger(HakmilikUrusanService.class);
    private static final boolean DEBUG = LOGGER.isDebugEnabled();

    public List<HakmilikUrusan> searchHakmilikUrusanGadaian(String kodUrusan, String idHakmilik, List<String> senaraiPermohonan) {

        StringBuilder query = new StringBuilder();
        SENARAI_URUSAN = new ArrayList<String>();

        query.append("Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idhakmilik ");

        if (kodUrusan.equals("PMBKU")) {
            query.append("and h.kodUrusan.kod LIKE 'KV%' ");
            query.append("and h.tarikhDaftar < sysdate ");
        } else if (kodUrusan.equals("TMAMB")) {
            query.append("and h.kodUrusan.kod LIKE 'TMA%' ");
            query.append("and h.kodUrusan.kod NOT IN ( 'TMAMB' ) ");
        } else if (kodUrusan.equals("LTK")) {
            //tak semestinya habis tempoh. contoh kalau cadangan pemotongan kaveat (2 bulan ), boleh mohon lanjutan tempoh kaveat. (PAT 29/12/2010)
//            query.append("and h.kodUrusan.kod LIKE 'KV%' "); //tutup fat sesi3 19/06/2013
            query.append("and h.kodUrusan.kod IN ('KVSS','KVST','KVSPC') ");
        } else if (kodUrusan.equals("KVSMP")) {
            query.append("and h.kodUrusan.kod LIKE 'KVS%' ");
        } else if (kodUrusan.equals("PMP")) {
            query.append("and h.kodUrusan.kod LIKE 'PJ%' ");
        } else if (kodUrusan.equals("PHMM")) {
            query.append("and h.kodUrusan.kod IN ('FGT5','KVLT','GD','MGG','GDWM','GDPJ') ");
        } else if (kodUrusan.equals("PH30A")) {
            query.append("and h.kodUrusan.kod IN ('FGT5','KVLT','GD','MGG','GDWM','GDPJ') ");
        } else if (kodUrusan.equals("GDL")) {
            query.append("and h.kodUrusan.kod IN ('FGT5','KVLT','GD','MGG','GDWM','GDPJ') ");
        }

        setUrusan(kodUrusan);

        if (!SENARAI_URUSAN.isEmpty()) {
            query.append("and h.kodUrusan.kod in (:list) ");
        }

//        if ( senaraiPermohonan != null ) {
//            query.append("and h.idPerserahan not in ")
//             .append("(select mau.permohonanSasaran.idPermohonan from etanah.model.PermohonanHubungan mau where mau.permohonanSumber.idPermohonan in (:list1)) ");
//        }
        if (kodUrusan.equals("PMKSW")) {
            query.append("and h.aktif ='T' ");
        } else {
            query.append("and h.aktif ='Y' ");
        }

//        if ( senaraiPermohonan.size() > 1) {
//            query.append("and h.idPerserahan in (:list3)");
//        } 
        if (DEBUG) {
            LOGGER.debug("QUERY = " + query.toString());
        }

        Session session = sessionProvider.get();
        Query q = session.createQuery(query.toString());
        q.setString("idhakmilik", idHakmilik);
        if (!SENARAI_URUSAN.isEmpty()) {
            q.setParameterList("list", SENARAI_URUSAN);
        }
//        if ( senaraiPermohonan != null ) {
//            q.setParameterList("list1", senaraiPermohonan);
//            for (String string : senaraiPermohonan) {
//                LOGGER.debug("id perserahan = " + string);
//            }
//        }
        return q.list();
    }

    public List<Permohonan> getUrusanBelumDaftar(List<String> ids, String kodUrusan) {
        List<Permohonan> senaraiPermohonan = new ArrayList<Permohonan>();
        SENARAI_URUSAN = new ArrayList<String>();
        setUrusan(kodUrusan);

        for (String id : ids) {
            Permohonan p = permohonanDAO.findById(id);
            if (p == null) {
                continue;
            }

            KodUrusan ku = p.getKodUrusan();

            if (!SENARAI_URUSAN.isEmpty()) {
                for (String str : SENARAI_URUSAN) {
                    if (str.equalsIgnoreCase(ku.getKod())) {
                        senaraiPermohonan.add(p);
                    }
                }
            }
        }
        return senaraiPermohonan;
    }

    private void setUrusan(String kodUrusan) {

        if (kodUrusan.equals("GDL") || kodUrusan.equals("PMG")) {
            SENARAI_URUSAN.add("GD");
            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
            SENARAI_URUSAN.add("GDWM");
//            query = query + "and h.kodUrusan.kod in ('GD','GDPJ','GDPJK')";
        } else if (kodUrusan.equals("GDCEL")) {
            SENARAI_URUSAN.add("GDCE");
//            query = query + "and h.kodUrusan.kod in ('GDCE')";
        } else if (kodUrusan.equals("GDB") || kodUrusan.equals("GDPJL")) {

            SENARAI_URUSAN.add("MGGS");
            SENARAI_URUSAN.add("GDCE");
            SENARAI_URUSAN.add("GD");
            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
            SENARAI_URUSAN.add("MGG");

//            query = query + "and h.kodUrusan.kod in ('MGGS','GDCE','GD','GDPJ','GDPJK')";
        } else if (kodUrusan.equals("PJKB") || kodUrusan.equals("PMPJK")) {

            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJKBT");
//            query = query + "and h.kodUrusan.kod in ('PJKT','PJKBT')";
        } else if (kodUrusan.equals("PJSB") || kodUrusan.equals("PJB")) {

            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJTM");
            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("PMP");
            SENARAI_URUSAN.add("PMPJK");

//            query = query + "and h.kodUrusan.kod in ('PJT','PJTM','PJKT','PJBT','PMP','PMPJK')";
        } else if (kodUrusan.equals("PJKSB")) {

            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJKBT");
            SENARAI_URUSAN.add("PMPJK");
//            query = query + "and h.kodUrusan.kod in ('PJKT','PMPJK')";
        } else if (kodUrusan.equals("PMP")) {
            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJKT");
//            query = query + "and h.kodUrusan.kod in ('PJT','PJKT')";
        } else if (kodUrusan.equals("ISL") || kodUrusan.equals("ISB")) {
            SENARAI_URUSAN.add("IS");
            SENARAI_URUSAN.add("ISBLS");

//            query = query + "and h.kodUrusan.kod in ('IS','ISBLS')";
        } else if (kodUrusan.equals("TENL") || kodUrusan.equals("TENB")) {

            SENARAI_URUSAN.add("TEN");
            SENARAI_URUSAN.add("TENBT");
            SENARAI_URUSAN.add("TENPT");
//            query = query + "and h.kodUrusan.kod in ('TEN','TENBT','TENPT')";
        } else if (kodUrusan.equals("KVPB")) {

            SENARAI_URUSAN.add("KVPK");
            SENARAI_URUSAN.add("KVPPT");
            SENARAI_URUSAN.add("KVPS");
            SENARAI_URUSAN.add("KVPT");

//            query = query + "and h.kodUrusan.kod in ('KVPK','KVPPT','KVPS','KVPT')";
        } else if (kodUrusan.equals("KVSTB") || kodUrusan.equals("KVSB")
                || kodUrusan.equals("KVSPC")) {

            SENARAI_URUSAN.add("KVSK");
            SENARAI_URUSAN.add("KVSS");
            SENARAI_URUSAN.add("KVSPT");
            SENARAI_URUSAN.add("KVST");
//            query = query + "and h.kodUrusan.kod in ('KVSK','KVSS','KVSPT','KVST')";
        } else if (kodUrusan.equals("KVSP")) {
            SENARAI_URUSAN.add("KVSK");
            SENARAI_URUSAN.add("KVSS");
            SENARAI_URUSAN.add("KVSPT");
            SENARAI_URUSAN.add("KVST");
            SENARAI_URUSAN.add("KVSPC");
        } else if (kodUrusan.equals("KVLB") || kodUrusan.equals("KVLTB")) {

            SENARAI_URUSAN.add("KVLK");
            SENARAI_URUSAN.add("KVLT");
            SENARAI_URUSAN.add("KVSPT");
            SENARAI_URUSAN.add("KVLS");
            SENARAI_URUSAN.add("KVLP");
//            query = query + "and h.kodUrusan.kod in ('KVLK','KVLT','KVSPT', 'KVLS','KVLP')";
        } else if (kodUrusan.equals("KVAB") || kodUrusan.equals("KVATB")) {

            SENARAI_URUSAN.add("KVAT");
            SENARAI_URUSAN.add("KVAS");
            SENARAI_URUSAN.add("KVAB");
            SENARAI_URUSAN.add("KVAK");
//            query = query + "and h.kodUrusan.kod in ('KVAT','KVAS','KVAB','KVAK')";
        } else if (kodUrusan.equals("PMTB")) {
            SENARAI_URUSAN.add("PMT");
            SENARAI_URUSAN.add("PMP");
            SENARAI_URUSAN.add("PMG");
            SENARAI_URUSAN.add("PMTM");
//            query = query + "and h.kodUrusan.kod in ('PMT','PMP','PMG', 'PMTM')";
        } else if (kodUrusan.equals("JPGD") || kodUrusan.equals("JMGD")
                || kodUrusan.equals("JDGD") || kodUrusan.equals("PNPHB") || kodUrusan.equals("GDT")
                || kodUrusan.equals("PHMM") || kodUrusan.equals("PHD")) {
            SENARAI_URUSAN.add("GD");
            SENARAI_URUSAN.add("MGG");
            SENARAI_URUSAN.add("KVLT");
            SENARAI_URUSAN.add("GDWM");
            SENARAI_URUSAN.add("GDCE");
            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
//            query = query + "and h.kodUrusan.kod in ('GD')";
        } else if (kodUrusan.equals("JPGPJ") || kodUrusan.equals("JMGPJ")) {

            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("PJKBT");
            SENARAI_URUSAN.add("PJKT");
//            query = query + "and h.kodUrusan.kod in ('GDPJ','GDPJK','PJT','PJBT','PJKBT','PJKT')";
        } else if (kodUrusan.equals("JML")) {
            SENARAI_URUSAN.add("KVLK");
            SENARAI_URUSAN.add("KVLP");
            SENARAI_URUSAN.add("KVLS");
            SENARAI_URUSAN.add("KVLT");
            SENARAI_URUSAN.add("GD");

        } else if (kodUrusan.equals("JMLK")) {
            SENARAI_URUSAN.add("GD");
            SENARAI_URUSAN.add("GDCE");
            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
            SENARAI_URUSAN.add("GDWM");
            SENARAI_URUSAN.add("TEN");
            SENARAI_URUSAN.add("TENPT");
            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("PJKBT");
            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJTM");
            SENARAI_URUSAN.add("KVLT");
//            query = query + "and h.kodUrusan.kod in ('KVLK')";
        } else if (kodUrusan.equals("PJKT") || kodUrusan.equals("JML")) {
            SENARAI_URUSAN.add("PJT");
//            query = query + "and h.kodUrusan.kod in ('PJT')";
        } else if (kodUrusan.equals("PJKBT")) {
            SENARAI_URUSAN.add("PJBT");
//            query = query + "and h.kodUrusan.kod in ('PJBT')";
        } else if (kodUrusan.equals("GDPJ")) {
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("PJT");
//            query = query + "and h.kodUrusan.kod in ('PJBT','PJT')";
        } else if (kodUrusan.equals("TRPA")) {

            SENARAI_URUSAN.add("PNPA");
//            query = query + "and h.kodUrusan.kod in ('PNPA')";
        } else if (kodUrusan.equals("PNPAB")) {

            SENARAI_URUSAN.add("PNPA");
            SENARAI_URUSAN.add("TRPA");
//            query = query + "and h.kodUrusan.kod in ('PNPA', 'TRPA')";
        } else if (kodUrusan.equals("GDPJK")) {

            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJKBT");
//            query = query + "and h.kodUrusan.kod in ('PJKT','PJKBT')";
        } else if (kodUrusan.equals("PHMMK") || kodUrusan.equals("KVPK")
                || kodUrusan.equals("KVAK") || kodUrusan.equals("KVLK")
                || kodUrusan.equals("KVSK")) {

            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJKBT");
            SENARAI_URUSAN.add("PJTM");
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("GD");
            SENARAI_URUSAN.add("TEN");
            SENARAI_URUSAN.add("KVLK");
            SENARAI_URUSAN.add("KVLP");
            SENARAI_URUSAN.add("KVLS");
            SENARAI_URUSAN.add("KVLT");
            SENARAI_URUSAN.add("IS");
            SENARAI_URUSAN.add("ISBLS");
            SENARAI_URUSAN.add("GDCE");
            SENARAI_URUSAN.add("TENBT");
//            query = query + "and h.kodUrusan.kod in ('PJT','PJKT','PJBT','PJKBT','GD','TEN','KVLK','KVLP','KVLS','KVLT','IS', 'ISBLS', 'GDCE', 'TENBT')";
        } else if (kodUrusan.equals("MGGB")) {

            SENARAI_URUSAN.add("MGG");
            SENARAI_URUSAN.add("MGGS");
//            query = query + "and h.kodUrusan.kod in ('MGG','MGGS')";
        } else if (kodUrusan.equals("JDGPJ")) {

            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("PJT");
            SENARAI_URUSAN.add("PJBT");
            SENARAI_URUSAN.add("PJKT");
            SENARAI_URUSAN.add("PJKBT");
            SENARAI_URUSAN.add("GD");
//            query = query + "and h.kodUrusan.kod in ('GDPJ','PJT','PJBT','PJKT','PJKBT')";
        } else if (kodUrusan.equals("PPUHB")) {
            SENARAI_URUSAN.add("PPUH");
        } else if (kodUrusan.equals("GDPJB")) { // FAT sesi 2 31052013 - request by safina
            SENARAI_URUSAN.add("GDPJ");
            SENARAI_URUSAN.add("GDPJK");
        }

    }
//    @Transactional

    public void saveOrUpdate(List<HakmilikUrusan> hakmilikUrusanList) {
        for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
            hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
        }
    }

    @Transactional
    public void saveOrUpdateUrusan(List<HakmilikUrusan> hakmilikUrusanList) {
        for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
            hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
        }
    }

    @Transactional
    public void saveOrUpdateUrusan(HakmilikUrusan hu) {
        hakmilikUrusanDAO.saveOrUpdate(hu);
    }

    public void saveOrUpdate(HakmilikUrusan hu) {
        hakmilikUrusanDAO.saveOrUpdate(hu);
    }

//     @Transactional
    public void deleteHakmilikUrusan(List<HakmilikUrusan> hakmilikUrusanList) {
        for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
            hakmilikUrusanDAO.delete(hakmilikUrusan);
        }

    }

    @Transactional
    public void deleteHakmilikUrusan(HakmilikUrusan hu) {
        hakmilikUrusanDAO.delete(hu);
    }

    @Transactional
    public void deleteHakmilikUrusanTrans(HakmilikUrusan hu) {
        hakmilikUrusanDAO.delete(hu);
    }

    public HakmilikUrusan findById(Long id) {
        return hakmilikUrusanDAO.findById(id);
    }

    public HakmilikUrusan findByIdPerserahan(String idPermohonan) {
        LOGGER.info("HakmilikUrusanService.findByIdPerserahan [ start ]");
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        if (DEBUG) {
            LOGGER.debug("query = [" + query + "]");
            LOGGER.debug("idPermohonan = [" + idPermohonan + "]");
        }
        LOGGER.info("HakmilikUrusanService.findByIdPerserahan [ finish ]");
        List<HakmilikUrusan> tmp = q.list();
        if (tmp.isEmpty()) {
            return null;
        }
        return (HakmilikUrusan) tmp.get(0);
    }

    public HakmilikUrusan findByIdPerserahanIdHakmilik(String idPermohonan, String idHakmilik) {
        LOGGER.info("HakmilikUrusanService.findByIdPerserahanIdHakmilik [start]");
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan and h.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        if (DEBUG) {
            LOGGER.debug("query = [" + query + "]");
            LOGGER.debug("idPermohonan = [" + idPermohonan + "]");
            LOGGER.debug("idHakmilik = [" + idHakmilik + "]");
        }
        List<HakmilikUrusan> tmp = q.list();
        if (tmp.isEmpty()) {
            return null;
        }
        return (HakmilikUrusan) tmp.get(0);
//        return hu;
    }

    public List<HakmilikUrusan> findListByIdPerserahanIdHakmilik(String idPermohonan, String idHakmilik) {
        LOGGER.info("HakmilikUrusanService.findByIdPerserahanIdHakmilik [start]");
        String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan and h.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        //if (DEBUG) LOGGER.debug(String.format("query :: %s\nidMohon :: %s\nidHakmilik :: %s", query, idPermohonan, idHakmilik));
        //HakmilikUrusan hu = (HakmilikUrusan) q.uniqueResult();
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and aktif = 'Y' order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
     public List<HakmilikUrusan> findHakmilikUrusanByIdHakmilikBatal(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and aktif = 'T' order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    //Added by Aizuddin to replace top service zzz cannot find all HU using above service only HU yg aktif

    public HakmilikUrusan findAllHakmilikUrusanByIdhu(Long idUrusan) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.idUrusan = :idUrusan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idUrusan", idUrusan);
        return (HakmilikUrusan) q.uniqueResult();

    }

    public List<HakmilikUrusan> findAllHakmilikUrusanByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik order by h.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanTAktif(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and aktif = 'T' order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByIdHakmilikAndIdPermohonan(String idHakmilik, String idPermohonan) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.idPerserahan =:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusan(List<Long> senaraiUrusan, String kodUrusan) {

        Session session = sessionProvider.get();
        Query q = session.createQuery(
                "Select h from etanah.model.HakmilikUrusan h, etanah.model.PermohonanAtasPerserahan mau "
                + "WHERE h.idPerserahan = mau.permohonan.idPermohonan "
                + "and h.kodUrusan.kod = :kod "
                + "and h.idUrusan IN (:senarai)").setParameter("kod", kodUrusan).setParameterList("senarai", senaraiUrusan);
        return q.list();
    }

    public List<HakmilikUrusan> findHalanganUrusniagaKavietHakmilikUrusanByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik and h.kodUrusan.kod ='PMHUK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHalanganBatalEndorsanHakmilikUrusanByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik and h.kodUrusan.kod ='PMHBE'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHalanganUrusniagaHakmilikUrusanByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik and h.kodUrusan.kod ='PMHUN'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanUrusniagaKaveatByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik and h.kodUrusan.kodPerserahan.kod='B' "
                + "and h.kodUrusan.kod in('KVAK','KVAS','KVAT','KVLK','KVLP','KVLS','KVLT','KVPK','KVPPT','KVPS','KVPT','KVSK','KVSPT','KVSS','KVST')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanUrusniaga(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik  "
                + "and h.kodUrusan.kod in('KVAK','KVAS','KVAT','KVLK','KVLP','KVLS','KVLT','KVPK','KVPPT','KVPS','KVPT','KVSK','KVSPT','KVSS','KVST','GD','PMT')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanUrusniagaAktif(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik  "
                + "and h.kodUrusan.kod in('KVAK','KVAS','KVAT','KVLK','KVLP','KVLS','KVLT','KVPK','KVPPT','KVPS','KVPT','KVSK','KVSPT','KVSS','KVST','GD','PMT') "
                + "AND h.aktif in('Y')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanUrusniagaAndNB(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik  "
                //+ "and h.kodUrusan.kod in('KVAK','KVAS','PJBT','BETPB','BETUR','BETUL','BETP','BETEN','KVAT','KVLK','KVLP','KVLS','KVLT','KVPK','KVPPT','KVPS','KVPT','KVSK','KVSPT','KVSS','KVST','GD','PMT','PJT','PSPL','PCT') "
                + "AND h.kodUrusan.kodPerserahan.kod in ('SC','B','N','NB') "
                + "AND h.hakmilik.kodStatusHakmilik.kod in ('D','P') "
                + "AND h.aktif in('Y') order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' and h.kodUrusan.kod != 'CL'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
    
       public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilikStrata(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.kodUrusan.kod != 'CL'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }
       

    public List<HakmilikUrusan> findHakmilikUrusanGDActiveByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' "
                + "and h.kodUrusan.kod in ('GD') order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanPMTActiveByIdHakmilik(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' "
                + "and h.kodUrusan.kod in ('PMT') order by h.infoAudit.tarikhMasuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilikOnly(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilikAndUrusan(String idHakmilik, String kodUrusan) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.kodUrusan.kod = :kodUrusan and h.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanTanahRizab(String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik=:idHakmilik and h.aktif = 'Y' "
                + "and h.kodUrusan.kod in('IGSA','IGSA5','IGSA6','ITP','ITD','ITB','IRTB','IPM','PTP','KB','IRM','IROA')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    /**
     *
     * @param idHakmilik
     * @return
     */
    public List<HakmilikUrusan> findUrusanKaveat(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from HakmilikUrusan u where u.hakmilik.idHakmilik = :idHakmilik "
                + "and u.aktif = 'Y' and u.kodUrusan.kod in (:senaraiKodKaveat)").setString("idHakmilik", idHakmilik)
                .setParameterList("senaraiKodKaveat", SENARAI_KAVEAT);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanGadaian(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from HakmilikUrusan u where u.hakmilik.idHakmilik = :idHakmilik "
                + "and u.aktif = 'Y' and u.kodUrusan.kod ='GD'").setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> doCheckUrusanKaviet(String idHakmilik) {
        String query = "Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.kodUrusan.nama like 'Kaveat%'";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findUrusanNottingMH(String idHakmilik, String kodUrusan) {
        String query = "FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y' ";
//        if (kodUrusan.equals("HSPS") || kodUrusan.equals("HKPS")
//                || kodUrusan.equals("HKPTK") || kodUrusan.equals("HSPTK")
//                || kodUrusan.equals("HKPB") || kodUrusan.equals("HSPB")) {
        if (kodUrusan.equals("HSPS") || kodUrusan.equals("HKPS") || kodUrusan.equals("HTSPS")
                || kodUrusan.equals("HKPB") || kodUrusan.equals("HSPB")) {
            LOGGER.debug("masuk urusan hsps");
            query += " and h.kodUrusan.kod in ('PSL','PSTSL','PBL','SSKPL')";
        }
//        if (kodUrusan.equals("HSC") || kodUrusan.equals("HKC") || kodUrusan.equals("HKCTK") || kodUrusan.equals("HSCTK")) {
        if (kodUrusan.equals("HSC") || kodUrusan.equals("HKC")) {
            query += " and h.kodUrusan.kod in ('CL')";
        }
        if (kodUrusan.equals("HKABS") || kodUrusan.equals("HKABT")) {
            query += " and h.kodUrusan.kod in ('ABT-K','ABTKB','ABTBH','SBSTL')";
        }
        if (kodUrusan.equals("HSSBB") || kodUrusan.equals("HSSTB") || kodUrusan.equals("HSSB") || kodUrusan.equals("HKSB")) {
            query += " and h.kodUrusan.kod in ('SBKSL','SBKBG','SBSTL','SBTL')";
        }
        if (kodUrusan.equals("KVSP")) {
            query += " and h.kodUrusan.kod in ('KVSPC')";
        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanActiveByIdHakmilikDistinctPermohonan(String idHakmilik) {
        String query = "Select distinct h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikUrusan> checkingHakmilikUrusanBaru(String idHakmilik, String idPermohonan) {
        String query = "Select distinct h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.idPerserahan =:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanBatalByIdUrusan(HakmilikUrusan hu) {
        String query = "Select distinct h FROM etanah.model.HakmilikUrusan h where h.urusanBatal.idUrusan = :idUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idUrusan", hu.getIdUrusan());
        return q.list();
    }

    public List<HakmilikUrusan> findByIdHakmilikKodUrusan(String idHakmilik, List kodUrusan) {
        StringBuilder sb = new StringBuilder("Select h from etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.aktif ='Y'");
        sb.append(" AND h.kodUrusan.kod in (:list)");
        Session session = sessionProvider.get();
        Query q = session.createQuery(sb.toString()).setParameter("idHakmilik", idHakmilik).setParameterList("list", kodUrusan);
        return q.list();
    }

    public List<HakmilikUrusan> findByIdHakmilikKodUrusanOnly(String idHakmilik, String kodUrusan) {
        String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik and h.kodUrusan.kod = :kodUrusan and h.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
//    Query q = session.createQuery(sb.toString()).setParameter("idHakmilik", idHakmilik).setParameter("kodUrusan", kodUrusan);

    }

    public List<HakmilikUrusan> findUrusanPajakan(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("from HakmilikUrusan u where u.hakmilik.idHakmilik = :idHakmilik "
                + "and u.aktif = 'Y' and u.kodUrusan.kod in ('PJT', 'PJBT', 'PJKBT', 'PJKT')").setString("idHakmilik", idHakmilik);
        return q.list();
    }
}
