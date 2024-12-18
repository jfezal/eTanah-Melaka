/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikUrusan;
import etanah.model.PermohonanAtasPerserahan;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author mohd.fairul
 */
public class BatalNotaService {

  @Inject
  PermohonanRujukanLuarDAO PermohonanRujukanDAO;
  @Inject
  HakmilikUrusanDAO hakmilikUrusanDAO;
  @Inject
  HakmilikPihakBerkepentinganDAO hakmilikPihakDAO;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  private static final Logger LOGGER = Logger.getLogger(BatalNotaService.class);

  public List<HakmilikUrusan> searchHakmilikUrusanNota(String kodUrusan, String idHakmilik) { //idPermohonan


    if (!((kodUrusan.equals("PINDE")))) {
      String query = "Select h from etanah.model.HakmilikUrusan h where h.aktif ='Y' and h.hakmilik.idHakmilik = :idHakmilik ";

      //        Urusan melibatkan pembatalan nota
      if (kodUrusan.equals("HLTEB")) {
        query = query + "and h.kodUrusan.kod in ('HLTE')";
      }
      if (kodUrusan.equals("HLLB")) {
        query = query + "and h.kodUrusan.kod in ('HLLS','HLLA')";
      }
      if (kodUrusan.equals("N8AB")) {
        query = query + "and h.kodUrusan.kod in ('N8A')";
      }
      if (kodUrusan.equals("RPBNB")) {
        query = query + "and h.kodUrusan.kod in ('N8A','N6A')";
      }
       if (kodUrusan.equals("PSPL")) {
        query = query + "and h.kodUrusan.kod in ('PSPM')";
      }
      if (kodUrusan.equals("N7AB")) {
        query = query + "and h.kodUrusan.kod in ('N7A')";
      }
      if (kodUrusan.equals("N6AB")) {
        query = query + "and h.kodUrusan.kod in ('N6A')";
      }
      if (kodUrusan.equals("ABTB")) {
        query = query + "and h.kodUrusan.kod in ('ABT','ABT-K','ABT-D','ABTKB','ABTS', 'ABTBH')";
      }
      if (kodUrusan.equals("ABT-K")) {
        query = query + "and h.kodUrusan.kod in ('ABT-D')";
      }
      if (kodUrusan.equals("ABTS")) {
        query = query + "and h.kodUrusan.kod in ('ABT-D')";
      }
      if (kodUrusan.equals("ABTKB")) {
        query = query + "and h.kodUrusan.kod in ('ABT-D')";
      }
      if (kodUrusan.equals("ABTBH")) {
        query = query + "and h.kodUrusan.kod in ('ABT-D')";
      }
      if (kodUrusan.equals("CB")) {
        query = query + "and h.kodUrusan.kod in ('CM')";
      }
      if (kodUrusan.equals("CMB")) {
        query = query + "and h.kodUrusan.kod in ('CMP','CM')";
      }
      if (kodUrusan.equals("CL")) {
        query = query + "and h.kodUrusan.kod in ('CM')";
      }
      if (kodUrusan.equals("ITBB")) {
        query = query + "and h.kodUrusan.kod in ('ITB','PTB')";
      }
      if (kodUrusan.equals("PBB")) {
        query = query + "and h.kodUrusan.kod in ('PBM','PBL')";
      }
      if (kodUrusan.equals("PBL")) {
        query = query + "and h.kodUrusan.kod in ('PBM')";
      }
      if (kodUrusan.equals("N8A")) {
        query = query + "and h.kodUrusan.kod in ('N6A','N7A','N7B')";
      }


      //Need to confirm back
      if (kodUrusan.equals("PBBB")) {
        query = query + "and h.kodUrusan.kod in ('PBBM','PBBL','PBBT')";
      }
      if (kodUrusan.equals("PBBL")) {
        query = query + "and h.kodUrusan.kod in ('PBBM')";
      }
      if (kodUrusan.equals("PBBT")) {
        query = query + "and h.kodUrusan.kod in ('PBBM')";
      }
      if (kodUrusan.equals("PBSCB")) {
        query = query + "and h.kodUrusan.kod in ('PBSCM')";
      }
      if (kodUrusan.equals("PSB")) {
        query = query + "and h.kodUrusan.kod in ('PSM')";
      }
      if (kodUrusan.equals("PSL")) {
        query = query + "and h.kodUrusan.kod in ('PSM')";
      }
      if (kodUrusan.equals("PSKB")) {
        query = query + "and h.kodUrusan.kod in ('PSKM')";
      }
      if (kodUrusan.equals("PSKSB")) {
        query = query + "and h.kodUrusan.kod in ('PSKSM')";
      }
      if (kodUrusan.equals("PSTSB")) {
        query = query + "and h.kodUrusan.kod in ('PSTSM','PSTSL')";
      }
      if (kodUrusan.equals("PSTSL")) {
        query = query + "and h.kodUrusan.kod in ('PSTSM')";
      }
      if (kodUrusan.equals("SBKBG")) {
        query = query + "and h.kodUrusan.kod in ('SBKSM')";
      }
      if (kodUrusan.equals("SBKSL")) {
        query = query + "and h.kodUrusan.kod in ('SBKSM')";
      }
      if (kodUrusan.equals("SBKSB")) {
        query = query + "and h.kodUrusan.kod in ('SBKSM')";
      }
      if (kodUrusan.equals("SBSTB")) {
        query = query + "and h.kodUrusan.kod in ('SBSTM','SBSTL')";
      }
      if (kodUrusan.equals("SBSTL")) {
        query = query + "and h.kodUrusan.kod in ('SBSTM')";
      }
      if (kodUrusan.equals("SBTB")) {
        query = query + "and h.kodUrusan.kod in ('SBTM')";
      }
      if (kodUrusan.equals("SBTL")) {
        query = query + "and h.kodUrusan.kod in ('SBTM')";
      }
      if (kodUrusan.equals("SSKPB")) {
        query = query + "and h.kodUrusan.kod in ('SSKPM','SSKPL')";
      }
      if (kodUrusan.equals("SSKPL")) {
        query = query + "and h.kodUrusan.kod in ('SSKPM')";
      }
      if (kodUrusan.equals("TSB")) {
        query = query + "and h.kodUrusan.kod in ('TSM')";
      }
      if (kodUrusan.equals("TSSKB")) {
        query = query + "and h.kodUrusan.kod in ('TSSKM','TSSKL')";
      }
      if (kodUrusan.equals("TSSKL")) {
        query = query + "and h.kodUrusan.kod in ('TSSKM')";
      }
      if (kodUrusan.equals("HMVB")) {
        query = query + "and h.kodUrusan.kod in ('HMV', 'HTPV')";
      }
      if (kodUrusan.equals("HSAB")) {
        query = query + "and h.kodUrusan.kod in ('HSAM')";
      }
      if (kodUrusan.equals("IGSAB")) {
        query = query + "and h.kodUrusan.kod in ('IGSA5','IGSA6','IGSA')";
      }
      if (kodUrusan.equals("TBTWB")) {
        query = query + "and h.kodUrusan.kod in ('TBTW')";
      }
      if (kodUrusan.equals("LPBB")) {
        query = query + "and h.kodUrusan.kod in ('LPB')";
      }
      if (kodUrusan.equals("LPMB")) {
        query = query + "and h.kodUrusan.kod in ('LPM')";
      }
      //meeting 20/11/2020 - HTT membatalkan ECPI & PBBL
      if (kodUrusan.equals("HTT")) {
        query = query + "and h.kodUrusan.kod in ('ECPI', 'PBBL', 'HTPV', 'HTBKR')"; //'HTB'
      }
      //pn.mas 14/07/2020 - PBBL batal sekali, meeting 20/11/2020 - HTBT membatalkan HTT
      if (kodUrusan.equals("HTBT")) {
        query = query + "and h.kodUrusan.kod in ('HTB','PBBL','HTT', 'ECPI')";
      }
      
      if (kodUrusan.equals("PREMB")) {
        query = query + "and h.kodUrusan.kod in ('PREM')";
      }

      if (kodUrusan.equals("MCLL")) {
        query = query + "and h.kodUrusan.kod in ('MCLM','SBKSM')";
      }
      if (kodUrusan.equals("PBCTL")) {
        query = query + "and h.kodUrusan.kod in ('PBCTM')";
      }
      if (kodUrusan.equals("ADMRL")) {
        query = query + "and h.kodUrusan.kod in ('ADMNB','ADMNS')";
      }
      if (kodUrusan.equals("EUB")) {
        query = query + "and h.kodUrusan.kod LIKE '%' and h.kodUrusan.kodPerserahan.kod in ('N','SC','B')";
//            query = query + "and * FROM h.kodUrusan.kod";
      }
      //Added by Aizuddin 18/10/12
      //add KRM by fikri 17/05/2013 fat sesi1
      if (kodUrusan.equals("IRMB")) {
        query = query + "and h.kodUrusan.kod in ('IRM','KRM')";
      }
      if (kodUrusan.equals("IRTBB")) {
        query = query + "and h.kodUrusan.kod in ('IRTB')";
      }
      if (kodUrusan.equals("IROAB")) {
        query = query + "and h.kodUrusan.kod in ('IROA')";
      }
      if (kodUrusan.equals("IPMB")) {
        query = query + "and h.kodUrusan.kod in ('IPM')";
      }
      if (kodUrusan.equals("IKOAB")) {
        query = query + "and h.kodUrusan.kod in ('IKOA')";
      }
      //add KRM by fikri 17/05/2013 fat sesi1
      if (kodUrusan.equals("KRMB")) {
        query = query + "and h.kodUrusan.kod in ('KRM','IRM')";
      }
      if (kodUrusan.equals("KBB")) {
        query = query + "and h.kodUrusan.kod in ('KB')";
      }


      if (kodUrusan.equals("ADBSL")) {
        query = query + "and h.kodUrusan.kod in ('ADBSB','ADBSS')";
      }
      if (kodUrusan.equals("PSPBB")) {
        query = query + "and h.kodUrusan.kod in ('PSPBN','N7A','N7B')";
      }
      if (kodUrusan.equals("LMTPB")) {
        query = query + "and h.kodUrusan.kod in ('LMTP')";
      }
      if (kodUrusan.equals("SHKB")) {
        query = query + "and h.kodUrusan.kod in ('SHKK')";
      }
      if (kodUrusan.equals("SHSB")) {
        query = query + "and h.kodUrusan.kod in ('SHSK')";
      }
      if (kodUrusan.equals("TSPB")) {
        query = query + "and h.kodUrusan.kod in ('TSP')";
      }
      if (kodUrusan.equals("JMB")) {
        query = query + "and h.kodUrusan.kod in ('JMGD')";
      }
      if (kodUrusan.equals("SWDB")) {
        query = query + "and h.kodUrusan.kod in ('SW','SWB')";
      }
      if (kodUrusan.equals("SMBT")) {
        query = query + "and h.kodUrusan.kod in ('SMB')";
      }
      //End added by Aizuddin

      //ADD BY AMEER INFOKALL
      if (kodUrusan.equals("TTB")) {
        query = query + "and h.kodUrusan.kod in ('TT')";
      }
      if (kodUrusan.equals("TTWB") || kodUrusan.equals("TTWKP") || kodUrusan.equals("TTWLM") || kodUrusan.equals("TTWLB")) {
        query = query + "and h.kodUrusan.kod in ('TTW')";
      }
      if (kodUrusan.equals("TMAMB")) {
        query = query + "and h.kodUrusan.kod like '%TMAM%'";
      }
      if (kodUrusan.equals("TTTK")) {
        query = query + "and h.kodUrusan.kod in ('TT')";
      }
      if (kodUrusan.equals("PRPMB")) {
        query = query + "and h.kodUrusan.kod in ('N8A','TTTK','TTWKP','TMAK','PHMB')";
      }
      if (kodUrusan.equals("PMHHB")) { // add by azri 17/7/2013 
        query = query + "and h.kodUrusan.kod in ('TT','N8A','TTWKP')";
      }
      if (kodUrusan.equals("GDPJT")) {
        query = query + "and h.kodUrusan.kod in ('GDPJ')";
        // end add by AMEER
      }
      
      LOGGER.info("query ::" + query);
//        LOGGER.info("idPermohonan ::" + idPermohonan);
      LOGGER.info("idHakmilik ::" + idHakmilik);
      Session session = sessionProvider.get();
      Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
      q.setString("idHakmilik", idHakmilik);
      return q.list();
    } else {

      String query = "Select hu from etanah.model.HakmilikUrusan hu , etanah.model.Hakmilik h"
              + " where h.idHakmilik=:idHakmilik "
              + "and hu.aktif ='Y' "
              + "and hu.hakmilik.idHakmilik = h.idHakmilik "
              + "and hu.kodUrusan.kod in ('KVST','KVPT','KVPP','KVSS','KVPS','KVPPT')";

      LOGGER.info("query ::" + query);
      LOGGER.info("idHakmilik ::" + idHakmilik);
      Session session = sessionProvider.get();
      Query q = session.createQuery(query);
      q.setString("idHakmilik", idHakmilik);
      return q.list();
    }
  }

  @Transactional
  public void saveOrUpdate(List<HakmilikUrusan> hakmilikUrusanList) {
    for (HakmilikUrusan hakmilikUrusan : hakmilikUrusanList) {
      hakmilikUrusanDAO.saveOrUpdate(hakmilikUrusan);
    }
  }

  @Transactional
  public void saveOrUpdateHakmilikPihak(HakmilikPihakBerkepentingan hPihak) {

    hakmilikPihakDAO.saveOrUpdate(hPihak);

  }

  public HakmilikUrusan findById(Long id) {
    return hakmilikUrusanDAO.findById(id);
  }

  public HakmilikUrusan findByIdPerserahan(String idPermohonan) {
    LOGGER.info("findByIdPerserahan :: start");
    String query = "SELECT h FROM etanah.model.HakmilikUrusan h where h.idPerserahan = :idPermohonan";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query);
    q.setString("idPermohonan", idPermohonan);
    LOGGER.info("query ::" + query);
    LOGGER.info("findByIdPerserahan :: finish");
    return (HakmilikUrusan) q.uniqueResult();
  }

  public HakmilikPermohonan findByIdUrusan(String idPermohonan, String idHakmilik) {
    String query = "SELECT h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan = :idPermohonan AND h.hakmilik = :idHakmilik";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query);
    q.setString("idPermohonan", idPermohonan);
    q.setString("idHakmilik", idHakmilik);
    LOGGER.info("query ::" + query);
    LOGGER.info("findByIdPerserahan :: finish");
    return (HakmilikPermohonan) q.uniqueResult();
  }

  public List<HakmilikPermohonan> findUrusanBatalTolak(String idHakmilik) {
    String query = "SELECT hp "
            + "FROM etanah.model.HakmilikPermohonan hp "
            + "where hp.permohonan.keputusan.kod in ('T','TK') "
            + "AND hp.hakmilik.idHakmilik = :idHakmilik ";

    Session session = sessionProvider.get();
    Query q = session.createQuery(query);

    q.setString("idHakmilik", idHakmilik);
    return q.list();
  }

  public List<HakmilikPermohonan> listUrusanBatalTolakByIDPermohonan(String idPermohonan) {
    String query = "SELECT hp "
            + "FROM etanah.model.HakmilikPermohonan hp "
            + "where hp.permohonan.keputusan.kod in ('T','TK') "
            + "AND hp.permohonan.idPermohonan = :idPermohonan ";

    Session session = sessionProvider.get();
    Query q = session.createQuery(query);

    q.setString("idPermohonan", idPermohonan);
    return q.list();
  }

  public List<HakmilikUrusan> findHakmilikUrusanByIdHakmilik(String idHakmilik) {
    String query = "Select h FROM etanah.model.HakmilikUrusan h where h.hakmilik.idHakmilik = :idHakmilik";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query);
    q.setString("idHakmilik", idHakmilik);
    return q.list();

  }

  public List<PermohonanAtasPerserahan> findByidPermohonan(String idPermohonan) {
    String query = "Select h FROM etanah.model.PermohonanAtasPerserahan h where h.permohonan.idPermohonan = :idPermohonan";
    Session session = sessionProvider.get();
    Query q = session.createQuery(query);
    q.setString("idPermohonan", idPermohonan);
    return q.list();

  }
}