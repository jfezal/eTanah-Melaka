/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.AkaunDAO;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.PihakDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.Akaun;
import etanah.model.DasarTuntutanCukai;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.Pihak;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import etanah.model.Transaksi;
import etanah.model.etapp.AgensiHakmilik;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author nurfaizati
 */
public class HakmilikService {

    @Inject
    PermohonanDAO permohonanDAODAO;//
    @Inject
    PihakDAO pihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    AkaunDAO akaunDAO;
    @Inject
    DokumenKewanganDAO dokumenKewanganDAO;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    DasarTuntutanCukaiDAO dasarCukaiDAO;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarCukaiHakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private List<Akaun> senaraiAkaun;
    private final static Logger LOG = Logger.getLogger(HakmilikService.class);
    private static boolean isDebug = LOG.isDebugEnabled();
    private List<Hakmilik> senaraiList;

    public Long getTotalRecord(Map<String, String[]> param, String caw) {


        String query = "SELECT count(distinct a) FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','PML','BP') ";

//        if (!caw.equals("00")) {
//            query += "AND a.hakmilik.kodHakmilik in( 'GRN', 'PN', 'HSD')";
//
//        }
        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot = :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik = :noHakmilik ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND a.hakmilik.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            if(caw.equals("08")){                               // utk user GEMAS ; simulasi 11-Nov-2014 START
            query += "AND a.hakmilik.cawangan.kod = :daerah ";
            }else{                                                     // END
                query += "AND a.hakmilik.daerah.kod = :daerah ";}
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND a.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND a.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND a.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }

        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }
        if (isNotBlank(param.get("kegunaanTanah"))) {
            query += "AND a.hakmilik.kegunaanTanah.nama LIKE :kegunaanTanah ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idHakmilik"))) {
            LOG.debug("....::" + param.get("idHakmilik")[0].trim());
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", param.get("noLot")[0].trim());
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", param.get("noHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            if(caw.equals("08")){       // utk user GEMAS ; simulasi 11-Nov-2014 START
                q.setString("daerah", "08");
            }else{                             // END 
                q.setString("daerah", param.get("daerah")[0].trim());}
        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("kegunaanTanah"))) {
            q.setString("kegunaanTanah", "%" + param.get("kegunaanTanah")[0].trim() + "%");
        }
        return (Long) q.iterate().next();
    }

    public List<Akaun> findAll(Map<String, String[]> param, int start, int max, String caw) {

        if (isDebug) {
            LOG.debug("from record [" + start + "]");
            LOG.debug("to record [" + max + "]");
        }


        String query = "SELECT distinct a FROM etanah.model.Akaun a, etanah.model.HakmilikPihakBerkepentingan hpk WHERE a.kodAkaun.kod = 'AC' AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','PAT','PAP','PAW','WKF','PMG','PG','CP','PL','PML','BP','PPM') ";

//        if (caw.equals("00")) {
//            query += "AND a.hakmilik.kodHakmilik in( 'GRN', 'PN', 'HSD')";
//
//        }
//AND a.hakmilik.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA')
        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }

        if (isNotBlank(param.get("noAkaun"))) {
            query += "AND a.noAkaun = :noAkaun ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND a.hakmilik.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND a.hakmilik.noLot LIKE :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND a.hakmilik.noHakmilik LIKE :noHakmilik ";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND a.hakmilik.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND a.hakmilik.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            if(caw.equals("08")){                           // utk user GEMAS ; simulasi 11-Nov-2014 START
            query += "AND a.hakmilik.cawangan.kod = :daerah ";
            }else{                                                  // END
                query += "AND a.hakmilik.daerah.kod = :daerah ";}
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND a.hakmilik.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND a.hakmilik.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND a.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND a.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND a.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND a.hakmilik.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }
        if (isNotBlank(param.get("kegunaanTanah"))) {
            query += "AND a.hakmilik.kegunaanTanah.nama LIKE :kegunaanTanah ";
        }
        if (isDebug) {
            LOG.debug("query : " + query);
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setFirstResult(start);
        q.setMaxResults(max);

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0].trim());
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%" + param.get("noLot")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%" + param.get("noHakmilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
//            System.out.println("....::" + param.get("hakmilik.bandarPekanMukim.kod")[0]);
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            if(caw.equals("08")){                   // utk user GEMAS ; simulasi 11-Nov-2014 START
                q.setString("daerah", "08");
            }else{                                         // END
                q.setString("daerah", param.get("daerah")[0].trim());}
        }
//        if (isNotBlank(param.get("hakmilik.lot.kod"))) {
//            q.setString("lot", param.get("hakmilik.lot.kod")[0].trim());
//        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", "%" + param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }
//        if (isNotBlank(param.get("hpk.pihak.nama"))) {
//            q.setString("nama", param.get("hpk.pihak.nama")[0].trim() + "%");
//        }
//           if (isNotBlank(param.get("hpk.pihak.noPengenalan"))) {
//            q.setString("noPengenalan", param.get("hpk.pihak.noPengenalan")[0].trim() );
//        }
        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }
//           if (isNotBlank(param.get("hpk.pihak.noPengenalan"))) {
//            q.setString("noPengenalan", param.get("hpk.pihak.noPengenalan")[0].trim() );
//        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("kegunaanTanah"))) {
            q.setString("kegunaanTanah", "%" + param.get("kegunaanTanah")[0].trim() + "%");
        }
        return q.list();
    }

    public List<Hakmilik> findHakmilikStrata(Map<String, String[]> param, int start, int max, String caw) {

        if (isDebug) {
            LOG.debug("from record [" + start + "]");
            LOG.debug("to record [" + max + "]");
        }


        String query = "SELECT distinct h FROM etanah.model.Hakmilik h,etanah.model.HakmilikPihakBerkepentingan hpk, etanah.model.AkaunStrata s WHERE s.hakmilik.idHakmilik = h.idHakmilik and h.idHakmilikInduk is not null AND h.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','WP','PH','BP') ";

        if (isNotBlank(param.get("noAkaunStrata"))) {
            query += "AND s.idAkaunStrata = :noAkaunStrata ";
        }
        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }
        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND h.idHakmilik = :idHakmilik ";
        }
        if (isNotBlank(param.get("noLot"))) {
            query += "AND h.noLot LIKE :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND h.noHakmilik LIKE :noHakmilik AND h.kodKategoriBangunan.kod not in ('P')";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND h.bandarPekanMukim.kod = :bandarPekanMukim ";
        }
        
        if (isNotBlank(param.get("seksyen"))) {
            query += "AND h.seksyen.kod = :seksyen ";
        }
        
        if (isNotBlank(param.get("daerah"))) {
            query += "AND h.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("noBangunan"))) {
            query += "AND h.noBangunan LIKE :noBangunan ";
        }
        
        if (isNotBlank(param.get("noTingkat"))) {
            query += "AND h.noTingkat LIKE :noTingkat ";
        }
        
        if (isNotBlank(param.get("noPetak"))) {
            query += "AND h.noPetak LIKE :noPetak ";
        }
        
        
        if (isNotBlank(param.get("lot"))) {
            query += "AND h.lot.kod = :lot ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND h.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND h.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }
        if (isNotBlank(param.get("badanPengurusan"))) {
            query += "AND h.badanPengurusan.pihak.nama LIKE :badanPengurusan ";
        }
        if (isDebug) {
            LOG.debug("query : " + query);
        }
        Query q = sessionProvider.get().createQuery(query);
        q.setFirstResult(start);
//        q.setMaxResults(max);
if (isNotBlank(param.get("noAkaunStrata"))) {
            q.setString("noAkaunStrata", param.get("noAkaunStrata")[0].trim());
        }
        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0].trim());
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%" + param.get("noLot")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%" + param.get("noHakmilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
//            System.out.println("....::" + param.get("hakmilik.bandarPekanMukim.kod")[0]);
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }

        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0].trim());
        }
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }
        
        if (isNotBlank(param.get("noBangunan"))) {
            q.setString("noBangunan", param.get("noBangunan")[0].trim());
        }
        
        if (isNotBlank(param.get("noTingkat"))) {
            q.setString("noTingkat", param.get("noTingkat")[0].trim() + "%");
        }
        
        if (isNotBlank(param.get("noPetak"))) {
            q.setString("noPetak", param.get("noPetak")[0].trim());
        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("badanPengurusan"))) {
            q.setString("badanPengurusan", "%" +param.get("badanPengurusan")[0].trim() + "%");
        }

        q.setMaxResults(q.list().size());
        return q.list();
    }

    public Long getTotalRecordHakmilikStrata(Map<String, String[]> param, String caw) {

        String query = "SELECT count(distinct h) FROM etanah.model.Hakmilik h, etanah.model.HakmilikPihakBerkepentingan hpk WHERE h.idHakmilikInduk is not null AND h.idHakmilik = hpk.hakmilik.idHakmilik AND hpk.aktif = 'Y' and hpk.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PA','PDP','PK','PLK','PP','RP','WKL','WPA','WS','WP','PH','BP') ";

        if (isNotBlank(param.get("namaPemilik"))) {
            query += "AND hpk.pihak.nama LIKE :namaPemilik ";
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            query += "AND hpk.pihak.noPengenalan LIKE :noPengenalan ";
        }
        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            query += "AND hpk.pihak.jenisPengenalan.kod = :jenisPengenalanPemilik ";
        }


        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND h.idHakmilik = :idHakmilik ";
        }


        if (isNotBlank(param.get("noLot"))) {
            query += "AND h.noLot LIKE :noLot ";
        }

        if (isNotBlank(param.get("noHakmilik"))) {
            query += "AND h.noHakmilik LIKE :noHakmilik AND h.kodKategoriBangunan.kod not in ('P')";
        }

        if (isNotBlank(param.get("bandarPekanMukim"))) {
            query += "AND h.bandarPekanMukim.kod = :bandarPekanMukim ";
        }

        if (isNotBlank(param.get("seksyen"))) {
            query += "AND h.seksyen.kod = :seksyen ";
        }

        if (isNotBlank(param.get("daerah"))) {
            query += "AND h.daerah.kod = :daerah ";
        }

        if (isNotBlank(param.get("lot"))) {
            query += "AND h.lot.kod = :lot ";
        }        
        
        if (isNotBlank(param.get("noBangunan"))) {
            query += "AND h.noBangunan LIKE :noBangunan ";
        }
        
        if (isNotBlank(param.get("noTingkat"))) {
            query += "AND h.noTingkat LIKE :noTingkat ";
        }
        
        if (isNotBlank(param.get("noPetak"))) {
            query += "AND h.noPetak LIKE :noPetak ";
        }


        if (isNotBlank(param.get("kodHakmilik"))) {
            query += "AND h.kodHakmilik.kod = :kodHakmilik ";
        }

        if (isNotBlank(param.get("namaPembayar"))) {
            query += "AND h.pemegang.nama LIKE :namaPembayar ";
        }

        if (isNotBlank(param.get("noPengenalanP"))) {
            query += "AND h.pemegang.noPengenalan = :noPengenalanP ";
        }

        if (isNotBlank(param.get("jenisPengenalan"))) {
            query += "AND h.pemegang.jenisPengenalan.kod = :jenisPengenalan ";
        }

        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            query += "AND h.kodStatusHakmilik.kod = :kodStatusHakmilik ";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idHakmilik"))) {
            LOG.debug("....::" + param.get("idHakmilik")[0].trim());
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        if (isNotBlank(param.get("noLot"))) {
            q.setString("noLot", "%" + param.get("noLot")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noHakmilik"))) {
            q.setString("noHakmilik", "%" + param.get("noHakmilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("kodHakmilik"))) {
            q.setString("kodHakmilik", param.get("kodHakmilik")[0].trim());
        }
        if (isNotBlank(param.get("bandarPekanMukim"))) {
            q.setString("bandarPekanMukim", param.get("bandarPekanMukim")[0].trim());
        }
        if (isNotBlank(param.get("seksyen"))) {
            q.setString("seksyen", param.get("seksyen")[0].trim());
        }
        if (isNotBlank(param.get("daerah"))) {
            q.setString("daerah", param.get("daerah")[0].trim());
        }
        
        if (isNotBlank(param.get("noBangunan"))) {
            q.setString("noBangunan", param.get("noBangunan")[0].trim());
        }
        
        if (isNotBlank(param.get("noTingkat"))) {
            q.setString("noTingkat", param.get("noTingkat")[0].trim() + "%");
        }
        
        if (isNotBlank(param.get("noPetak"))) {
            q.setString("noPetak", param.get("noPetak")[0].trim());
        }
 
        if (isNotBlank(param.get("lot"))) {
            q.setString("lot", param.get("lot")[0].trim());
        }
        if (isNotBlank(param.get("noAkaun"))) {
            q.setString("noAkaun", param.get("noAkaun")[0].trim());
        }
        if (isNotBlank(param.get("namaPembayar"))) {
            q.setString("namaPembayar", param.get("namaPembayar")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalanP"))) {
            q.setString("noPengenalanP", param.get("noPengenalanP")[0].trim());
        }
        if (isNotBlank(param.get("jenisPengenalan"))) {
            q.setString("jenisPengenalan", param.get("jenisPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("namaPemilik"))) {
            q.setString("namaPemilik", param.get("namaPemilik")[0].trim() + "%");
        }
        if (isNotBlank(param.get("noPengenalan"))) {
            q.setString("noPengenalan", param.get("noPengenalan")[0].trim());
        }

        if (isNotBlank(param.get("jenisPengenalanPemilik"))) {
            q.setString("jenisPengenalanPemilik", param.get("jenisPengenalanPemilik")[0].trim());
        }
        if (isNotBlank(param.get("kodStatusHakmilik"))) {
            q.setString("kodStatusHakmilik", param.get("kodStatusHakmilik")[0].trim());
        }

        return (Long) q.iterate().next();
    }

    public List<Hakmilik> findHakmilikStratabyIDHakmilikInduk(String idHakmilikInduk) {
//        String query = "SELECT distinct h FROM etanah.model.Hakmilik h "
//                + "WHERE h.idHakmilikInduk is not null AND h.idHakmilikInduk = :idHakmilikInduk ORDER BY h.idHakmilik ASC";
        String query = "SELECT distinct h FROM etanah.model.Hakmilik h "
                + "WHERE h.idHakmilikInduk IS NOT NULL AND h.idHakmilikInduk = :idHakmilikInduk "
                + "ORDER BY regexp_substr(h.noBangunan, '^\\D*'), "
                + "to_number(regexp_substr(h.noBangunan, '\\d+')), h.noTingkat+0, h.noPetak+0";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
    }
    public List<Hakmilik> findHakmilikStratabyIDHakmilikIndukMLK(String idHakmilikInduk) {
        String query = "SELECT distinct h FROM etanah.model.Hakmilik h "
                + "WHERE h.idHakmilikInduk is not null AND h.idHakmilikInduk = :idHakmilikInduk and h.kodKategoriBangunan.kod not in ('P') ORDER BY h.idHakmilik ASC";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idHakmilikInduk", idHakmilikInduk);
        return q.list();
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

    /**
     * Tulasi*
     */
    @Transactional
    public List<DasarTuntutanCukaiHakmilik> getdasarTuntuan() {
        String query = "SELECT p FROM etanah.model.DasarTuntutanCukaiHakmilik p";
        Query q = sessionProvider.get().createQuery(query);
        return q.list();
    }

    /**
     * *
     */
    @Transactional
    public void updatePemegang(Pihak ph) {

        pihakDAO.update(ph);
    }

    @Transactional
    public void updatePihak(Pihak ph, Akaun ak) {

        pihakDAO.saveOrUpdate(ph);
        akaunDAO.saveOrUpdate(ak);

    }

    @Transactional
    public void updateBilanganCetak(Akaun ak) {
//        System.out.println("DasarTuntutanNotis:start");
        akaunDAO.update(ak);
//        System.out.println("DasarTuntutanNotis:finish");
    }

    public List<Transaksi> findMohon(Map<String, String[]> param) {
        String query = "SELECT p FROM etanah.model.Transaksi p WHERE 1=1";



        if (isNotBlank(param.get("permohonan.idPermohonan"))) {
            query += "AND p.permohonan.idPermohonan = :idPermohonan ";
        }

        if (isNotBlank(param.get("dokumenKewangan.idDokumenKewangan"))) {
            query += "AND p.dokumenKewangan.idDokumenKewangan = :idDokumenKewangan";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("permohonan.idPermohonan"))) {
            q.setString("idPermohonan", param.get("permohonan.idPermohonan")[0]);
        }

        if (isNotBlank(param.get("dokumenKewangan.idDokumenKewangan"))) {
            q.setString("idDokumenKewangan", param.get("dokumenKewangan.idDokumenKewangan")[0]);
        }

        return q.list();
    }

    public List<Transaksi> findTransaksiByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select p FROM etanah.model.Transaksi p where p.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<DasarTuntutanCukaiHakmilik> findDasar(Map<String, String[]> param, int start, int max, String caw) {
        if (isDebug) {
            LOG.debug("from record [" + start + "]");
            LOG.debug("to record [" + max + "]");
        }
        String query = "SELECT distinct p FROM etanah.model.DasarTuntutanCukaiHakmilik p WHERE 1=1 ";
        if (isNotBlank(param.get("idDasar"))) {
            query += "AND p.dasarTuntutanCukai.idDasar = :idDasar ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND p.hakmilik.idHakmilik = :idHakmilik";
        }

        Query q = sessionProvider.get().createQuery(query);
        q.setFirstResult(start);
        q.setMaxResults(max);
        if (isNotBlank(param.get("idDasar"))) {
            q.setString("idDasar", param.get("idDasar")[0]);
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        return q.list();
    }

    public List<DasarTuntutanCukai> findDasar(Map<String, String[]> param) {//, int start, int max, String caw) {

        List<DasarTuntutanCukai> senaraiDTC = new ArrayList<DasarTuntutanCukai>();
        if (isNotBlank(param.get("idDasar")) && isNotBlank(param.get("idHakmilik"))) {
            // 1) search Dasar
            DasarTuntutanCukai dtc = new DasarTuntutanCukai();
            dtc = dasarCukaiDAO.findById(param.get("idDasar")[0]);
            // 2) search Dasar Hakmilik for idHakmilik
            List<DasarTuntutanCukaiHakmilik> senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>();
            LOG.info("param.get('idHakmilik')[0] :" + param.get("idHakmilik")[0]);
            String[] name = {"hakmilik"};
            Object[] value = {hakmilikDAO.findById(param.get("idHakmilik")[0])};
            senaraiDTCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
            LOG.debug("senaraiDTCH.size :" + senaraiDTCH.size());
            // 3) comparison
            for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCH) {
                if (dtch.getDasarTuntutanCukai().equals(dtc)) {
                    senaraiDTC.add(dtch.getDasarTuntutanCukai());
                }
            }
        }
        if (isNotBlank(param.get("idDasar")) && isBlank(param.get("idHakmilik"))) {
            String[] tname = {"idDasar"};
            Object[] tvalue = {param.get("idDasar")[0]};
            senaraiDTC = dasarCukaiDAO.findByEqualCriterias(tname, tvalue, null);
        }
        if (isBlank(param.get("idDasar")) && isNotBlank(param.get("idHakmilik"))) {
            List<DasarTuntutanCukaiHakmilik> senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>();
            LOG.info("param.get('idHakmilik')[0] :" + param.get("idHakmilik")[0]);
            String[] name = {"hakmilik"};
            Object[] value = {hakmilikDAO.findById(param.get("idHakmilik")[0])};
            senaraiDTCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value,null);
            for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCH) {
                senaraiDTC.add(dtch.getDasarTuntutanCukai());
            }
        }
        
        if (isNotBlank(param.get("noRujukanDasar"))){
            LOG.info("---------------------------------------- SINI ----------------------------------------");
            List<DasarTuntutanCukaiHakmilik> senaraiDTCH = new ArrayList<DasarTuntutanCukaiHakmilik>();
            LOG.info("param.get('noRujukanDasar')[0] :" + param.get("noRujukanDasar")[0]);
            String[] name = {"noRujukan"};
            Object[] value = {hakmilikDAO.findById(param.get("noRujukanDasar")[0])};
            senaraiDTCH = dasarCukaiHakmilikDAO.findByEqualCriterias(name, value, null);
            LOG.debug("senaraiDTCH.size :" + senaraiDTCH.size());
            for (DasarTuntutanCukaiHakmilik dtch : senaraiDTCH) {
                if (dtch.getNoRujukan().equals(param.get("noRujukanDasar")[0])) {
                    senaraiDTC.add(dtch.getDasarTuntutanCukai());
                }
            }            
        }
                
        if (isBlank(param.get("idDasar")) && isBlank(param.get("idHakmilik"))&& isBlank(param.get("noRujukanDasar"))) {
            senaraiDTC = dasarCukaiDAO.findAll();
        }
        return senaraiDTC;
    }

    public Long getTotal(Map<String, String[]> param, String caw) {

        String query = "SELECT count(distinct p)  FROM etanah.model.DasarTuntutanCukaiHakmilik p WHERE 1=1";

        if (isNotBlank(param.get("idDasar"))) {
            query += "AND p.dasarTuntutanCukai.idDasar = :idDasar ";
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            query += "AND p.hakmilik.idHakmilik = :idHakmilik";
        }

        Query q = sessionProvider.get().createQuery(query);

        if (isNotBlank(param.get("idDasar"))) {
            q.setString("idDasar", param.get("idDasar")[0]);
        }

        if (isNotBlank(param.get("idHakmilik"))) {
            q.setString("idHakmilik", param.get("idHakmilik")[0]);
        }

        return (Long) q.iterate().next();
    }

    @Transactional
    public void update(Akaun ak) {


        akaunDAO.update(ak);


    }

    public List<HakmilikPihakBerkepentingan> findAkaun(String noAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select h from etanah.model.Akaun u, etanah.model.HakmilikPihakBerkepentingan h where u.noAkaun =:noAkaun and u.kodAkaun.kod='AC' and h.hakmilik=u.hakmilik");
        q.setString("noAkaun", noAkaun);
        return q.list();
    }

    public List<Akaun> findNoAkaun(String noAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("select u from etanah.model.Akaun u  where u.noAkaun =:noAkaun and u.kodAkaun.kod='AC'");
        q.setString("noAkaun", noAkaun);
        return q.list();
    }

    public AgensiHakmilik findAgensiByHakmilik(String idHakmilik) {
        String query = "SELECT a FROM etanah.model.etapp.AgensiHakmilik a WHERE a.hakmilik.idHakmilik = :id and a.aktif = 'Y' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", idHakmilik);
        return (AgensiHakmilik) q.uniqueResult();
    }
    
      public Hakmilik findHakmilik(String idHakmilik) {
        String query = "SELECT a FROM etanah.model.Hakmilik a WHERE a.idHakmilik = :id ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("id", idHakmilik);
        return (Hakmilik) q.uniqueResult();
    }
}
