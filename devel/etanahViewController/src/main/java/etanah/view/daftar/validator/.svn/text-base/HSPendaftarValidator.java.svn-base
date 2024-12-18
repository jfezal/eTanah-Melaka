/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import java.util.List;
import com.google.inject.Inject;
import oracle.bpel.services.workflow.StaleObjectException;
import org.hibernate.Session;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.dao.PenggunaDAO;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikSebelumPermohonan;
import java.util.ArrayList;
import org.apache.log4j.Logger;
//import etanah.model.SejarahHakmilik;
import etanah.service.RegService;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikSebelum;
import etanah.model.KodHakmilik;
import etanah.service.SyerValidationService;
import org.apache.commons.math.fraction.Fraction;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.PermohonanPihak;
import etanah.service.common.PermohonanPihakService;
import etanah.model.Pihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.dao.KodJenisPihakBerkepentinganDAO;
import etanah.model.KodStatusHakmilik;
import etanah.model.HakmilikUrusan;
import etanah.service.common.HakmilikUrusanService;
import etanah.model.Akaun;
import etanah.model.KodAkaun;
import etanah.service.common.HakmilikPermohonanService;
import etanah.model.Dokumen;
import etanah.model.strata.BadanPengurusan;
import etanah.dao.BadanPengurusanDAO;
import etanah.report.ReportUtil;
import java.io.File;
import etanah.model.FolderDokumen;
import etanah.dao.FolderDokumenDAO;
import etanah.service.common.DokumenService;
import etanah.model.KodDokumen;
import etanah.service.SemakDokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.model.KandunganFolder;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusHakmilikDAO;
import org.hibernate.Transaction;
import etanah.sequence.GeneratorNoAkaun;
//import etanah.view.stripes.hasil.TransactionActionBean;
import etanah.model.KodCawangan;
import etanah.model.Transaksi;
import java.text.SimpleDateFormat;
import etanah.dao.KodTransaksiDAO;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.sequence.GeneratorNoSijilMC;
import etanah.service.ReportName;
import etanah.service.StrataPtService;
import java.math.BigDecimal;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikService;
import etanah.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import java.util.logging.Level;
import org.apache.commons.lang.ArrayUtils;

/**
 * Listener to Generate Report
 *
 * @ Pendaftar for Urusan MH
 * @author khairil
 */
public class HSPendaftarValidator implements StageListener {

  private static final Logger logger = Logger.getLogger(HSPendaftarValidator.class);
  private static final Logger syslog = Logger.getLogger("SYSLOG");
  @Inject
  RegService regService;
//    @Inject
//    AkaunService akService;
  @Inject
  protected com.google.inject.Provider<Session> sessionProvider;
  @Inject
  PenggunaDAO penggunaDAO;
  @Inject
  SyerValidationService syerService;
  @Inject
  PermohonanPihakService permohonanPihakService;
  @Inject
  BadanPengurusanDAO badanPengurusanDAO;
  @Inject
  GeneratorNoSijilMC generatorNoSijilMC;
  @Inject
  HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
  @Inject
  KodJenisPihakBerkepentinganDAO kodJenisPihakBerkepentinganDAO;
  @Inject
  HakmilikService hakmilikServ;
  @Inject
  HakmilikUrusanService hakmilikService;
  @Inject
  HakmilikPermohonanService hakmilikPermohonanService;
  @Inject
  ReportUtil reportUtil;
  @Inject
  etanah.Configuration conf;
  @Inject
  FolderDokumenDAO folderDokumenDAO;
  @Inject
  DokumenService dokumenService;
  @Inject
  SemakDokumenService semakDokumenService;
  @Inject
  KandunganFolderService kandunganFolderService;
  @Inject
  HakmilikDAO hakmilikDAO;
  @Inject
  private KodStatusHakmilikDAO kodStatusHakmilikDAO;
  @Inject
  GeneratorNoAkaun genNoAkaun;
  @Inject
  KodTransaksiDAO kodTransaksiDAO;
  @Inject
  KutipanHasilManager manager;
  @Inject
  private KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;
  @Inject
  ReportName reportName;
  @Inject
  KodKlasifikasiDAO kodKlasifikasiDAO;
  @Inject
  StrataPtService strService;
//    @Inject
//    TransactionActionBean trans;
  private Pengguna pengguna;
  private Transaksi transaksi;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
  private static final String kod_transaksi = "61401";
  private String idTransaksi;
  SimpleDateFormat sdfoct = new SimpleDateFormat("dd/MM/yyyy");
  private String idHakmilik;
  @Inject
  KodUrusanDAO kodUrusanDAO;
  @Inject
  GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
  private List<PermohonanBangunan> pBangunanProvisionalBlock;
  IWorkflowContext ctxOnBehalf = null;
  private String stage;
  private String taskId;
  private BadanPengurusan mc;
  private Hakmilik hakmilik;
  private Hakmilik hakmilikSTRATA;
  private Hakmilik hakmilikSTRATA2;
  private Hakmilik NoBukuSTRATA;
  private Hakmilik hakmilikDaerahMukim;
  private Hakmilik hakmilikNoBukuSTRATA;
  private Hakmilik highestNoBukuStrata;
  private Hakmilik hakmilikTiadaSTRATA;
  private KodHakmilik kodHakmilik;
  private List<Hakmilik> listIDHakmilikSTRATA = new ArrayList<Hakmilik>();
  private List<Hakmilik> listIDHakmilikNOSTRATA = new ArrayList<Hakmilik>();
  private List<Hakmilik> listHighestNoBukuSTRATA = new ArrayList<Hakmilik>();
  private List<Hakmilik> senaraiHakmilikDaerahMukim = new ArrayList<Hakmilik>();
  private List<Hakmilik> senaraiHakmilikSTRATA = new ArrayList<Hakmilik>();
  private List<Hakmilik> senaraiBukuSTRATA;
  private PermohonanRujukanLuar mohonrujluar;
  private Date TarikhBuku;
  private int highestNoBuku;

  @Override
  public boolean beforeStart(StageContext context) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String beforeComplete(StageContext context, String proposedOutcome) {
    logger.debug("Starting beforeComplete Listener");
    logger.debug("proposedOutcome :" + proposedOutcome);

    Permohonan permohonan = context.getPermohonan();
    KodCawangan kodcawangan = permohonan.getCawangan();
    Pengguna pengguna = context.getPengguna();
    Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
    InfoAudit info = new InfoAudit();
    info.setDimasukOleh(peng);
    info.setTarikhMasuk(new java.util.Date());

    if (proposedOutcome.equals("D")) {
      Transaction tx = sessionProvider.get().beginTransaction();
      tx.begin();
      try {
        insertUrusan(permohonan, info, proposedOutcome);
        saveAkaun(permohonan, peng, info);
        movePihak(permohonan, peng, info);
        moveMHANMHS(permohonan, info);

        if (permohonan.getKodUrusan().getKod().equals("HT")
                || permohonan.getKodUrusan().getKod().equals("HTB")
                || permohonan.getKodUrusan().getKod().equals("HTSC")
                || permohonan.getKodUrusan().getKod().equals("HTSPB")
                || permohonan.getKodUrusan().getKod().equals("HTSPS")
                || permohonan.getKodUrusan().getKod().equals("HTSPV")) {
          // SEND BACK TO MODULE STRATA
          intergrationNota(permohonan, pengguna);
        }

        if (conf.getProperty("kodNegeri").equals("05")) {
          if (permohonan.getKodUrusan().getKod().equals("HT")) {
            List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
            Hakmilik hmStrata = new Hakmilik();
            Hakmilik hmInduk = new Hakmilik();
            Hakmilik hkinduk = hakmilikDAO.findById(hk.get(0).getHakmilik().getIdHakmilikInduk());
            senaraiHakmilikSTRATA = hakmilikServ.getHakmilikStratanInduk(hkinduk.getIdHakmilik());
            for (int qm = 0; qm < senaraiHakmilikSTRATA.size(); qm++) {
              hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(qm);
              logger.info("-------id hakmilik list---------" + hakmilikSTRATA2.getIdHakmilik());
              hmStrata = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
              hmStrata.setNoVersiDhde(1);
              hmStrata.setNoVersiDhke(1);
              hakmilikDAO.saveOrUpdate(hmStrata);
              logger.info("------Update NoVersiDHDE and DHKE Hakmilik Strata SUCCESS------");
            }
//                        hmInduk = hakmilikDAO.findById(hkinduk.getIdHakmilik());
//                        hmInduk.setNoVersiDhde(1);
//                        hmInduk.setNoVersiDhke(1);
//                        hakmilikDAO.saveOrUpdate(hmInduk);
//                        logger.info("------Update NoVersiDHDE and DHKE Hakmilik Induk SUCCESS------");
          }
        }
        /*updating kos_sts for hakmilik when user select Daftar */
        if (permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP") || permohonan.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")) {
          Permohonan permohonan1 = context.getPermohonan();
          updateStatusHakmilik(permohonan1, info, proposedOutcome);
        }

        tx.commit();

      } catch (Exception e) {
        tx.rollback();
        Throwable t = e;
        // getting the root cause
        while (t.getCause() != null) {
          t = t.getCause();
        }
        t.printStackTrace();
        context.addMessage("Pendaftaran Tidak Berjaya.Sila Hubungi Pentadbir Sistem.");
        return null;
      }

    }
    //TODO : should return msg
    //context.addMessage(" -Urusan telah selesai.");
    context.addMessage(" - Penghantaran Berjaya.");
    return proposedOutcome;
  }

  public void intergrationNota(Permohonan permohonan, Pengguna pguna) throws WorkflowException, StaleObjectException {

    if (permohonan.getPermohonanSebelum() != null
            && permohonan.getPermohonanSebelum().getIdPermohonan() != null) {
      logger.debug("ID Sebelum ::" + permohonan.getPermohonanSebelum().getIdPermohonan());
      ctxOnBehalf = WorkFlowService.authenticate("sysdaftar1"); //sysjupem1
      if (ctxOnBehalf != null) {
        List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getPermohonanSebelum().getIdPermohonan());
        logger.debug("Task FOund::" + l);
        for (Task t : l) {
          stage = t.getSystemAttributes().getStage();
          taskId = t.getSystemAttributes().getTaskId();
          try {
            WorkFlowService.acquireTask(taskId, ctxOnBehalf);
            logger.debug("--Claim Found Task--::" + taskId);
            WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "APPROVE"); // "L" comfirm balik

            logger.debug("--Update Task Outcome--" + stage);

          } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(NotadaftarValidation.class.getName()).log(Level.SEVERE, null, ex);
          }
        }

      }

    }
  }

  @Override
  public void afterComplete(StageContext context) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onGenerateReports(StageContext context) {
    Pengguna pengguna = context.getPengguna();
    Permohonan p = context.getPermohonan();
    List<HakmilikPermohonan> senaraiHakmilik = p.getSenaraiHakmilik();
    Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
    String keputusan = context.getPermohonan().getKeputusan().getKod();

    if (conf.getProperty("kodNegeri").equals("04")) {
      if (p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSC")) {
        logger.info("------Lalu HTSPB-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
        logger.info("------idPermohonan Sebelum-------" + idPermohonan);
        //List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
        // logger.info("--hk--"+hk);
        // logger.info("--hk.get(0).getHakmilik().getIdHakmilikInduk()--"+hk.get(0).getHakmilik().getIdHakmilik());
        List<HakmilikPermohonan> hk = hakmilikServ.getIDHakmilikByIDMohon(idPermohonan);
        for (int z = 0; z < hk.size(); z++) {
          HakmilikPermohonan HMPmohon = hk.get(z);
          logger.info("--Hakmilik--::" + HMPmohon.getHakmilik().getIdHakmilik());
          logger.info("--Hakmilik Induk--::" + HMPmohon.getHakmilik().getIdHakmilikInduk());
          //params = new String[]{"p_id_mohon", "p_id_hakmilik"};
          //values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};
          listIDHakmilikSTRATA = hakmilikServ.getIDHakmilikNoBukuSTRATAByInduk(HMPmohon.getHakmilik().getIdHakmilikInduk());
          for (int pm = 0; pm < 1; pm++) {
            Hakmilik keputusanSTS = listIDHakmilikSTRATA.get(pm);
            logger.info("-----KodSTS-----" + keputusanSTS.getKodStatusHakmilik().getKod());
            if (keputusanSTS.getKodStatusHakmilik().getKod().equalsIgnoreCase("D")) {
              if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC")
                      || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")
                      || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBSS")) {
                logger.info("--Hakmilik--::" + HMPmohon.getHakmilik().getIdHakmilik());
                logger.info("--Hakmilik hkinduk--::" + HMPmohon.getHakmilik().getIdHakmilikInduk());
                String noBukuSTRATA = keputusanSTS.getNoBukuDaftarStrata();
                logger.info("------Nombor Buku STRATA-----" + noBukuSTRATA);
                listIDHakmilikNOSTRATA = hakmilikServ.getNoBukuSTRATAByInduk(HMPmohon.getHakmilik().getIdHakmilikInduk());
                for (int ul = 0; ul < listIDHakmilikNOSTRATA.size(); ul++) {
                  Hakmilik HakmilikNoSTRATA = listIDHakmilikNOSTRATA.get(ul);
                  logger.info("------ID HAKMILIK STRATA TIADA NO BUKU STRATA-----" + HakmilikNoSTRATA.getIdHakmilik());
                  hakmilikTiadaSTRATA = hakmilikDAO.findById(HakmilikNoSTRATA.getIdHakmilik());
                  logger.info("------Nombor Buku STRATA-----" + noBukuSTRATA);
                  hakmilikTiadaSTRATA.setNoBukuDaftarStrata(noBukuSTRATA);
                  hakmilikDAO.saveOrUpdate(hakmilikTiadaSTRATA);
                }
              }
            }
          }
        }
      }
    }
    if (conf.getProperty("kodNegeri").equals("05")) {
      if (p.getKodUrusan().getKod().equals("HTSPV")) {
        logger.info("------Lalu HTSPV-------");
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
        logger.info("------idPermohonan Sebelum-------" + idPermohonan);

        List<HakmilikPermohonan> hk = hakmilikServ.getIDHakmilikByIDMohon(idPermohonan);
        Hakmilik HMPmohon = hk.get(0).getHakmilik(); //retrieve idhakmilik induk

        String noBuku = strService.findNoBukuByIdHakmilik(HMPmohon.getIdHakmilik());

        logger.info("--Hakmilik--::" + noBuku);
        List<HakmilikPermohonan> hkmohon = hakmilikServ.getIDHakmilikByIDMohon(permohonan.getIdPermohonan());

        for (HakmilikPermohonan hkbaru : hkmohon) {
          Hakmilik hmbaru = strService.findInfoByIdHakmilik(hkbaru.getHakmilik().getIdHakmilik());

          if (keputusan.equals("D")) {
            hmbaru.setNoBukuDaftarStrata(noBuku);
            KodStatusHakmilik kodstatusHm = kodStatusHakmilikDAO.findById("D");
            hmbaru.setKodStatusHakmilik(kodstatusHm);
            hmbaru.setTarikhDaftar(new java.util.Date());
          }
        }

        Hakmilik hmcek = strService.findInfoByIdHakmilik(hkmohon.get(0).getHakmilik().getIdHakmilik());

        logger.info("--Size Hm--::" + hmcek.getIdHakmilik());

        //if (hmcek.getNoBukuDaftarStrata() != null && hmcek != null) {

        List<Hakmilik> hmbatal = strService.findHakmilibyParentProv(HMPmohon.getIdHakmilik());

        for (Hakmilik hkbatal : hmbatal) {
          Hakmilik hmPBatal = strService.findInfoByIdHakmilik(hkbatal.getIdHakmilik());
          KodStatusHakmilik kods = kodStatusHakmilikDAO.findById("B");
          hmPBatal.setKodStatusHakmilik(kods);
          hmPBatal.setTarikhBatal(new java.util.Date());
        }
        //  }

      }

    }


//KOD URUSAN HT pbbs, pbbd, pbs, pbts
    if (p.getKodUrusan().getKod().equals("HT") || p.getKodUrusan().getKod().equals("HTSPV")) {
      if (keputusan.equals("D")) {
        logger.info("---Lalu di sini HT, D");
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        Dokumen g = null;
        Dokumen h = null;
        Dokumen i = null;
        Dokumen j = null;
        Dokumen k = null;
        Dokumen b = null;
        Dokumen t = null;
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        String idSBlm = permohonan.getPermohonanSebelum().getIdPermohonan();
        logger.info("----idPermohonan----" + idPermohonan);

        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String[] params2 = null;
        String[] values2 = null;
        String path2 = "";
        String path3 = "";
        String path4 = "";
        String path5 = "";
        String path6 = "";
        String path8 = "";
        String path10 = "";
        String gen2 = "";
        String gen3 = "";
        String gen4 = "";
        String gen5 = "";
        String gen6 = "";
        String gen8 = "";
        String gen9 = "";
        String gen10 = "";
        logger.info("----permohonan.getSenaraiHakmilik()----" + permohonan.getSenaraiHakmilik());
        List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();
        KodDokumen kd4 = new KodDokumen();
        KodDokumen kd5 = new KodDokumen();
        KodDokumen kd6 = new KodDokumen();
        KodDokumen kd7 = new KodDokumen();
        KodDokumen kd8 = new KodDokumen();
        KodDokumen kd10 = new KodDokumen();


        logger.info("----Hakmilik Induk----" + hk.get(0).getHakmilik().getIdHakmilikInduk());
        Hakmilik hkinduk = hakmilikDAO.findById(hk.get(0).getHakmilik().getIdHakmilikInduk());
        logger.info("--Hakmilik--::" + hk.get(0).getHakmilik().getIdHakmilik());
        logger.info("--Hakmilik hkinduk--::" + hkinduk);
        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
        values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};
//                gen2 = "STRB2K_NS.rdf";
//                gen3 = "STRB3K_NS.rdf";

        gen2 = reportName.getSTRB2KReportName();
        gen3 = reportName.getSTRB3KReportName();

        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBS")
                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBD")
                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBS")
                || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBTS")) {
          logger.info("----PBBS, PBBD, PBS, PBTS----");
          Hakmilik hakmilikBukuSTRATA;
          logger.info("----BukuSTRATA idHakMilik Induk----" + hkinduk.getIdHakmilik());
          kodHakmilik = hakmilikServ.getKodHakmilik(hkinduk.getIdHakmilik());
          if (String.valueOf(kodHakmilik.getMilikDaerah()).equalsIgnoreCase("Y")) {
            logger.info("-------------LALU PTD----------");

            senaraiHakmilikSTRATA = hakmilikServ.getDaerahMukimbyIDHakmilik(hkinduk.getIdHakmilik());


            for (int hm = 0; hm < 1; hm++) {
              hakmilikSTRATA = senaraiHakmilikSTRATA.get(hm);
              logger.info("-----------KOD DAERAH---------" + hakmilikSTRATA.getDaerah().getKod());
              logger.info("-----------KOD MUKIM---------" + hakmilikSTRATA.getBandarPekanMukim().getKod());
              if (hakmilikSTRATA.getNoBukuDaftarStrata() == null) {
                //String mukim = String.valueOf(hakmilikSTRATA.getBandarPekanMukim());
                senaraiHakmilikDaerahMukim = hakmilikServ.getFilterDaerahMukim(hakmilikSTRATA.getDaerah().getKod(), hakmilikSTRATA.getBandarPekanMukim().getKod());
                logger.info("----SenaraiHakMilikDaerahMukim-----" + senaraiHakmilikDaerahMukim);
                if (senaraiHakmilikDaerahMukim != null) {
                  logger.info("---------LALU SINI JIKA senaraiHakmilikDaerahMukim TIDAK NULL---------");
                  for (int gm = 0; gm < 1; gm++) {
                    hakmilikDaerahMukim = senaraiHakmilikDaerahMukim.get(gm);
                    logger.info("----BukuSTRATA getDaerah----" + hakmilikDaerahMukim.getDaerah().getKod());
                    logger.info("----BukuSTRATA getBandarPekanMukim----" + hakmilikDaerahMukim.getBandarPekanMukim().getKod());
                    listHighestNoBukuSTRATA = hakmilikServ.getHighestNoBukuSTRATAPTD(hakmilikSTRATA.getDaerah().getKod(), hakmilikSTRATA.getBandarPekanMukim().getKod(), "Y");
                    logger.info("--------listHighestNoBukuSTRATA------" + listHighestNoBukuSTRATA);

                    if (listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA == null) {
//                                            for (int zm = 0; zm < listHighestNoBukuSTRATA.size(); zm++) {
//                                                logger.info("--------zm------" + zm);
//                                                highestNoBukuStrata = listHighestNoBukuSTRATA.get(zm);
                      logger.info("---------LALU SINI JIKA highestNoBukuSTRATA NULL---------");
                      //highestNoBukuStrata = listHighestNoBukuSTRATA.get(0);
//                                            logger.info("----highestNo----" + highestNo);

//                                            //ida update on 30062013
//                                            if (conf.getProperty("kodNegeri").equals("05")) {
//                                                hkinduk.setNoBukuDaftarStrata("1");
//                                                //-----------------
//                                            }

                      for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                        hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                        hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());

                        hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                        //ida update on 11/09/2013
                        if (conf.getProperty("kodNegeri").equals("05")) {
                          KodStatusHakmilik kodstatus = kodStatusHakmilikDAO.findById("D");
                          logger.info("----Tarikh Semasa----" + kodstatus.getKod());
                          hakmilikBukuSTRATA.setKodStatusHakmilik(kodstatus);
                        }
                        hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                      }
                      BadanPengurusan bp = hakmilikServ.getIDBadan(idSBlm);
                      mc = badanPengurusanDAO.findById(bp.getIdBadan());
                      if (bp.getPengurusanNoSijil() == null) {
                        logger.info("----generatorNoSijilMC.generateSijil()----" + generatorNoSijilMC.generateSijil());
                        mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
                      }
                      logger.info("----Tarikh Semasa----" + new java.util.Date());
                      mc.setPengurusanTarikhSijil(new java.util.Date());
                      badanPengurusanDAO.saveOrUpdate(mc);
//                                            }
                    } else if (!listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA != null) {
                      for (int zm = 0; zm < listHighestNoBukuSTRATA.size(); zm++) {
                        logger.info("--------zm------" + zm);
                        highestNoBukuStrata = listHighestNoBukuSTRATA.get(zm);
                        logger.info("---------LALU SINI JIKA highestNoBukuSTRATA TIDAK NULL---------");
                        String highestNo = highestNoBukuStrata.getNoBukuDaftarStrata();
                        logger.info("----highestNo----" + highestNo);
                        logger.info("----BukuSTRATA getNoBukuDaftarStrata----" + highestNoBukuStrata.getNoBukuDaftarStrata());
                        highestNoBuku = Integer.parseInt(highestNo);
                        highestNoBuku = highestNoBuku + 1;
                        logger.info("----BukuSTRATA highestNoBuku----" + highestNoBuku);
//                                                //ida update on 30062013
//                                                if (conf.getProperty("kodNegeri").equals("05")) {
//                                                    hkinduk.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
//                                                    //-----------------
//                                                }
                        for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                          hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                          hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                          hakmilikBukuSTRATA.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
                          //ida update on 11/09/2013
                          if (conf.getProperty("kodNegeri").equals("05")) {
                            KodStatusHakmilik kodstatus = kodStatusHakmilikDAO.findById("D");
                            logger.info("----KOD STATUS----" + kodstatus.getKod());
                            hakmilikBukuSTRATA.setKodStatusHakmilik(kodstatus);
                          }
                          hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                        }
                        BadanPengurusan bp = hakmilikServ.getIDBadan(idSBlm);
                        mc = badanPengurusanDAO.findById(bp.getIdBadan());
                        if (bp.getPengurusanNoSijil() == null) {
                          logger.info("----generatorNoSijilMC.generateSijil()----" + generatorNoSijilMC.generateSijil());
                          mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
                        }
                        logger.info("----Tarikh Semasa----" + new java.util.Date());
                        mc.setPengurusanTarikhSijil(new java.util.Date());
                        badanPengurusanDAO.saveOrUpdate(mc);
                      }
                    }
                  }
                } else if (senaraiHakmilikDaerahMukim == null) {
                  logger.info("---------LALU SINI JIKA senaraiHakmilikDaerahMukim NULL---------");

//                                    //ida update on 30062013
//                                    if (conf.getProperty("kodNegeri").equals("05")) {
//                                        hkinduk.setNoBukuDaftarStrata("1");
//                                        //-----------------
//                                    }

                  for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                    hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                    //ida update on 11/09/2013
                    if (conf.getProperty("kodNegeri").equals("05")) {
                      KodStatusHakmilik kodstatus = kodStatusHakmilikDAO.findById("D");
                      logger.info("----KOD STATUS----" + kodstatus.getKod());
                      hakmilikBukuSTRATA.setKodStatusHakmilik(kodstatus);
                    }
                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                  }
                  BadanPengurusan bp = hakmilikServ.getIDBadan(idSBlm);
                  mc = badanPengurusanDAO.findById(bp.getIdBadan());
                  if (bp.getPengurusanNoSijil() == null) {
                    logger.info("----generatorNoSijilMC.generateSijil()----" + generatorNoSijilMC.generateSijil());
                    mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
                  }
                  logger.info("----Tarikh Semasa----" + new java.util.Date());
                  mc.setPengurusanTarikhSijil(new java.util.Date());
                  badanPengurusanDAO.saveOrUpdate(mc);
                }
              }
            }
          } else if (String.valueOf(kodHakmilik.getMilikDaerah()).equalsIgnoreCase("T")) {
            logger.info("-------------LALU PTG----------");
            senaraiHakmilikSTRATA = hakmilikServ.getIDHakmilikByInduk(hkinduk.getIdHakmilik());
            listHighestNoBukuSTRATA = hakmilikServ.getHighestNoBukuSTRATAPTG("T");
            logger.info("--------listHighestNoBukuSTRATA------" + listHighestNoBukuSTRATA);
            for (int pq = 0; pq < 1; pq++) {
              hakmilikSTRATA = senaraiHakmilikSTRATA.get(pq);
              if (hakmilikSTRATA.getNoBukuDaftarStrata() == null) {
                if (listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA == null) {
                  logger.info("---------LALU SINI JIKA highestNoBukuSTRATA NULL---------");
                  //highestNoBukuStrata = listHighestNoBukuSTRATA.get(0);
//                                            logger.info("----highestNo----" + highestNo);
//
//                                    //ida update on 30062013
//                                    if (conf.getProperty("kodNegeri").equals("05")) {
//                                        hkinduk.setNoBukuDaftarStrata("1");
//                                        //-----------------
//                                    }
                  for (int vm = 0; vm < senaraiHakmilikSTRATA.size(); vm++) {
                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(vm);
                    logger.info("-------id hakmilik list---------" + hakmilikSTRATA2.getIdHakmilik());
                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                    hakmilikBukuSTRATA.setNoBukuDaftarStrata("1");
                    //ida update on 11/09/2013
                    if (conf.getProperty("kodNegeri").equals("05")) {
                      KodStatusHakmilik kodstatus = kodStatusHakmilikDAO.findById("D");
                      logger.info("----KOD STATUS----" + kodstatus.getKod());
                      hakmilikBukuSTRATA.setKodStatusHakmilik(kodstatus);
                    }
                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                  }
                  BadanPengurusan bp = hakmilikServ.getIDBadan(idSBlm);
                  mc = badanPengurusanDAO.findById(bp.getIdBadan());
                  if (bp.getPengurusanNoSijil() == null) {
                    logger.info("----generatorNoSijilMC.generateSijil()----" + generatorNoSijilMC.generateSijil());
                    mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
                  }
                  logger.info("----Tarikh Semasa----" + new java.util.Date());
                  mc.setPengurusanTarikhSijil(new java.util.Date());
                  badanPengurusanDAO.saveOrUpdate(mc);
                } else if (!listHighestNoBukuSTRATA.isEmpty() || listHighestNoBukuSTRATA != null) {
                  // for (int zm = 0; zm < listHighestNoBukuSTRATA.size(); zm++) {
                  //   logger.info("--------zm------" + zm);
                  highestNoBukuStrata = listHighestNoBukuSTRATA.get(0);
                  logger.info("---------LALU SINI JIKA highestNoBukuSTRATA TIDAK NULL---------");
                  String highestNo = highestNoBukuStrata.getNoBukuDaftarStrata();
                  logger.info("----highestNo----" + highestNo);
                  logger.info("----BukuSTRATA getNoBukuDaftarStrata----" + highestNoBukuStrata.getNoBukuDaftarStrata());
                  highestNoBuku = Integer.parseInt(highestNo);
                  highestNoBuku = highestNoBuku + 1;
                  logger.info("----BukuSTRATA highestNoBuku----" + highestNoBuku);
                  logger.info("----SENARAI HAKMILIK STRATA-----" + senaraiHakmilikSTRATA);
//
//                                    //ida update on 30062013
//                                    if (conf.getProperty("kodNegeri").equals("05")) {
//                                        hkinduk.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
//                                        //-----------------
//                                    }

                  for (int qm = 0; qm < senaraiHakmilikSTRATA.size(); qm++) {
                    hakmilikSTRATA2 = senaraiHakmilikSTRATA.get(qm);
                    logger.info("-------id hakmilik list---------" + hakmilikSTRATA2.getIdHakmilik());
                    hakmilikBukuSTRATA = hakmilikDAO.findById(hakmilikSTRATA2.getIdHakmilik());
                    logger.info("---Integer.toString(highestNoBuku)---" + Integer.toString(highestNoBuku));
                    hakmilikBukuSTRATA.setNoBukuDaftarStrata(Integer.toString(highestNoBuku));
                    //ida update on 11/09/2013
                    if (conf.getProperty("kodNegeri").equals("05")) {
                      KodStatusHakmilik kodstatus = kodStatusHakmilikDAO.findById("D");
                      logger.info("----KOD STATUS----" + kodstatus.getKod());
                      hakmilikBukuSTRATA.setKodStatusHakmilik(kodstatus);
                    }
                    hakmilikNoBukuSTRATA = hakmilikDAO.saveOrUpdate(hakmilikBukuSTRATA);
                  }
                  BadanPengurusan bp = hakmilikServ.getIDBadan(idSBlm);
                  mc = badanPengurusanDAO.findById(bp.getIdBadan());
                  if (bp.getPengurusanNoSijil() == null) {
                    logger.info("----generatorNoSijilMC.generateSijil()----" + generatorNoSijilMC.generateSijil());
                    mc.setPengurusanNoSijil(generatorNoSijilMC.generateSijil());
                  }
                  logger.info("----Tarikh Semasa----" + new java.util.Date());
                  mc.setPengurusanTarikhSijil(new java.util.Date());
                  badanPengurusanDAO.saveOrUpdate(mc);
                  //   }
                }
              }
            }
          }
          //ida update 30072013
          if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("HT")) {
              Permohonan mohon2 = strService.findPermohonanByHakmilik2(hkinduk.getIdHakmilik());
              mohonrujluar = strService.findPermohonan2(mohon2.getIdPermohonan());
              if (mohonrujluar != null && mohonrujluar.getNoRujukan() == null) {
                String hmbuku = strService.findNoBukuByIdHakmilik(hkinduk.getIdHakmilik());
                // mohonrujluar.setNoRujukan(hkinduk.getNoBukuDaftarStrata());
                mohonrujluar.setNoRujukan(highestNoBuku + "");
                TarikhBuku = strService.findDateByIdHakmilik(hkinduk.getIdHakmilik());
                // mohonrujluar.setTarikhRujukan(hkinduk.getTarikhDaftar());
                mohonrujluar.setTarikhRujukan(TarikhBuku);
                strService.SimpanMohonRujukLuar(mohonrujluar);
              }

              logger.info("----Tarikh Buku---- : " + TarikhBuku);

              Permohonan mohon3 = strService.findByIDSblm(context.getPermohonan().getIdPermohonan());
              if (mohon3 != null) {
                PermohonanRujukanLuar mohonrujluar2 = strService.findPermohonan2(mohon3.getIdPermohonan());
                if (mohonrujluar2 != null && mohonrujluar2.getNoRujukan() == null) {
                  // mohonrujluar2.setNoRujukan(hkinduk.getNoBukuDaftarStrata());
                  String hmbuku = strService.findNoBukuByIdHakmilik(hkinduk.getIdHakmilik());
                  mohonrujluar2.setNoRujukan(highestNoBuku + "");
                  TarikhBuku = strService.findDateByIdHakmilik(hkinduk.getIdHakmilik());
                  //mohonrujluar2.setTarikhRujukan(hkinduk.getTarikhDaftar());
                  mohonrujluar2.setTarikhRujukan(TarikhBuku);
                  strService.SimpanMohonRujukLuar(mohonrujluar2);
                }
              }

            }

          }
        }

        //Jika bukan urusan PHPP & PHPC
        if (!p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC") && !p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
          if (conf.getProperty("kodNegeri").equals("04")) {
            logger.info("--Generating 2K--::");
            kd2.setKod("2K");
            e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            logger.info("::Path To save :: " + path2);
            logger.info("::Report Name ::" + gen2);
            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
            logger.info("--Generated 2K--::");

            logger.info("--Generating 3K--::");
            kd3.setKod("3K");
            f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            logger.info("::Path To save :: " + path3);
            logger.info("::Report Name ::" + gen3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            logger.info("--Generated 3K--::");
            logger.info("--Generating 5F--::");
            kd4.setKod("5F");
//                    gen4 = "STRB5F_NS.rdf";
            gen4 = reportName.getSTRB5FReportName();
            g = saveOrUpdateDokumen(fd, kd4, hkinduk.getIdHakmilik(), context);
            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
            logger.info("::Path To save :: " + path4);
            logger.info("::Report Name ::" + gen4);
            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
            reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
            updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
            logger.info("--Generated 5F--::");
            for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
// gen9 = "REGB4K_NS.rdf";
              values = new String[]{idPermohonan.trim(), hp.getHakmilik().getIdHakmilik()};
              gen9 = reportName.getREGB4KReportName();
              kd5.setKod("4K");
              b = saveOrUpdateDokumen(fd, kd5, hp.getHakmilik().getIdHakmilik(), context);
              path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
              logger.info("::Path To save :: " + path5);
              logger.info("::Report Name ::" + gen9);
              syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hp.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
              reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
              updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
              logger.info("--Generated 4k--::");
              hp.setDokumen4(g);
              hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            }
            logger.info("--Generating 4k--::");

//                    gen9 = "REGB4K_NS.rdf";
//                    gen9 = reportName.getREGB4KReportName();
//                    kd5.setKod("4K");
//                    b = saveOrUpdateDokumen(fd, kd5, hkinduk.getIdHakmilik(), context);
//                    path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
//                    logger.info("::Path To save :: " + path5);
//                    logger.info("::Report Name ::" + gen9);
//                    syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
//                    reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
//                    updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
//                    logger.info("--Generated 4k--::");



            logger.info("--Generating BSK--::");
//                    gen10 = "STRBSK_NS.rdf";
            gen10 = reportName.getSTRBSKReportName();
            kd10.setKod("BSK");
            t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
            path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
            logger.info("::Path To save :: " + path10);
            logger.info("::Report Name ::" + gen10);
            reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
            updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
            logger.info("--Generated BSk--::");
          }

          //Negeri9 IDA Update on 25082013
          if (conf.getProperty("kodNegeri").equals("05")) {
            logger.info("--Generating 2K--::");
            kd2.setKod("2K");
            e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
            path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            logger.info("::Path To save :: " + path2);
            logger.info("::Report Name ::" + gen2);
            reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
            logger.info("--Generated 2K--::");

            // if (conf.getProperty("kodNegeri").equals("04")) {
            logger.info("--Generating 3K--::");
            kd3.setKod("3K");
            f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            logger.info("::Path To save :: " + path3);
            logger.info("::Report Name ::" + gen3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            logger.info("--Generated 3K--::");
            // }

            logger.info("--Generating 5F--::");
            kd4.setKod("5F");
//                    gen4 = "STRB5F_NS.rdf";
            gen4 = reportName.getSTRB5FReportName();
            g = saveOrUpdateDokumen(fd, kd4, hkinduk.getIdHakmilik(), context);
            path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
            logger.info("::Path To save :: " + path4);
            logger.info("::Report Name ::" + gen4);
            syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
            reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
            updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
            logger.info("--Generated 5F--::");

            // for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            // gen9 = "REGB4K_NS.rdf";
            HakmilikPermohonan hp = strService.findMohonHakmilik(idPermohonan);
            values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};
            // gen9 = reportName.getREGB4KReportName();
            //kd5.setKod("4K");
            gen9 = reportName.getB4KDHKKReportName();
            kd5.setKod("DHKK");
            b = saveOrUpdateDokumen(fd, kd5, hkinduk.getIdHakmilik(), context);
            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
            logger.info("::Path To save :: " + path5);
            logger.info("::Report Name ::" + gen9);
            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
            reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
            updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
            logger.info("--Generated 4k--::");
            hp.setDokumen4(g);
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            // }
            logger.info("--Generating 4k DHKK--::");


            // gen9 = reportName.getREGB4KReportName();
            //kd5.setKod("4K");
            gen9 = reportName.getB4KDHDKReportName();
            kd5.setKod("DHDK");
            b = saveOrUpdateDokumen(fd, kd5, hkinduk.getIdHakmilik(), context);
            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
            logger.info("::Path To save :: " + path5);
            logger.info("::Report Name ::" + gen9);
            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
            reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
            updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
            logger.info("--Generated 4K DHDK--::");
            hp.setDokumen4(g);
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hp);
            // }
            logger.info("--Generating 4K DHDK--::");


//                    logger.info("--Generating BSK--::");
////                    gen10 = "STRBSK_NS.rdf";
//                    gen10 = reportName.getSTRBSKReportName();
//                    kd10.setKod("BSK");
//                    t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
//                    path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
//                    logger.info("::Path To save :: " + path10);
//                    logger.info("::Report Name ::" + gen10);
//                    reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
//                    updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
//                    logger.info("--Generated BSk--::");

            logger.info("--Generating SK DHKK--::");
//                    gen10 = "STRBSK_NS.rdf";
            gen10 = reportName.getSKDHKKReportName();
            kd10.setKod("SKDK");
            t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
            path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
            logger.info("::Path To save :: " + path10);
            logger.info("::Report Name ::" + gen10);
            reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
            updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
            logger.info("-- Generated BSK DHKK --::");

            logger.info("--Generating SK DHDK--::");
//                    gen10 = "STRBSK_NS.rdf";
            gen10 = reportName.getSKDHDKReportName();
            kd10.setKod("SKDD");
            t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
            path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
            logger.info("::Path To save :: " + path10);
            logger.info("::Report Name ::" + gen10);
            reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
            updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
            logger.info("-- Generated BSK DHDK --::");


            if (p.getKodUrusan().getKod().equals("HTSPV")) {
              String pathHTSPV = "";
              String genHTSPV = "";
              KodDokumen kdHTSPV = new KodDokumen();


//                            logger.info("--Generating 4AK Batal--::");
//                            kdHTSPV.setKod("4KB");
//                            gen10 = "STRB4AK_NS.rdf";
//                            Dokumen kg = saveOrUpdateDokumen(fd, kdHTSPV, hkinduk.getIdHakmilik(), context);
//                            path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(kg.getIdDokumen());
//                            logger.info("::Path To save :: " + path10);
//                            logger.info("::Report Name ::" + genHTSPV);
//                            syslog.info(peng.getIdPengguna() + " generate report " + kdHTSPV.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path10);
//                            reportUtil.generateReport(genHTSPV, params, values, dokumenPath + path10, peng);
//                            updatePathDokumen(reportUtil.getDMSPath(), kg.getIdDokumen());
//                            logger.info("--Generated 4KB--::");

              genHTSPV = "STRB4AK_NS.rdf";
              kdHTSPV.setKod("4KB");
              t = saveOrUpdateDokumen(fd, kdHTSPV, hkinduk.getIdHakmilik(), context);
              pathHTSPV = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
              logger.info("::Path To save :: " + pathHTSPV);
              logger.info("::Report Name ::" + genHTSPV);
              reportUtil.generateReport(genHTSPV, params, values, dokumenPath + pathHTSPV, peng);
              updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
              logger.info("-- Generated 4AK Batal--::");
            }




          }

        } //tamat
                /*if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC") || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
         logger.info("--Generating REGB4KBATAL--::");
         kd6.setKod("4KB");
         gen5 = "REGB4KBATAL_NS.rdf";
         i = saveOrUpdateDokumen(fd, kd6, hkinduk.getIdHakmilik(), context);
         path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen());
         logger.info("::Path To save :: " + path5);
         logger.info("::Report Name ::" + gen5);
         syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
         reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
         updatePathDokumen(reportUtil.getDMSPath(), i.getIdDokumen());
         logger.info("--Generated REGB4KBATAL--::");
         }*/

        logger.info("--Current ID Permohonan--::" + p.getIdPermohonan());
        logger.info("--Previous ID Permohonan--::" + p.getPermohonanSebelum().getIdPermohonan());
        pBangunanProvisionalBlock = strService.findByIDPermohonanByProvisional(p.getPermohonanSebelum().getIdPermohonan());
        logger.info("---pBangunanProvisionalBlock---" + pBangunanProvisionalBlock.size());

        if (pBangunanProvisionalBlock.size() > 0) {
          logger.info("--Generating STRB4AK--::");
          kd7.setKod("4AK");
          gen6 = "STRB4AK_NS.rdf";
          j = saveOrUpdateDokumen(fd, kd7, hkinduk.getIdHakmilik(), context);
          path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(j.getIdDokumen());
          logger.info("::Path To save :: " + path6);
          logger.info("::Report Name ::" + gen6);
          syslog.info(peng.getIdPengguna() + " generate report " + kd7.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path6);
          reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
          updatePathDokumen(reportUtil.getDMSPath(), j.getIdDokumen());
          logger.info("--Generated STRB4K--::");
        }
        if (conf.getProperty("kodNegeri").equals("04")) {
          if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBS")
                  || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBD")
                  || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBS")
                  || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBTS")) {
            logger.info("--Generating SMC--::");
            kd8.setKod("SMC");
            gen8 = "STRSijilMC_NS.rdf";
            k = saveOrUpdateDokumen(fd, kd8, hkinduk.getIdHakmilik(), context);
            path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
            logger.info("::Path To save :: " + path8);
            logger.info("::Report Name ::" + gen8);
            syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path8);
            reportUtil.generateReport(gen8, params, values, dokumenPath + path8, peng);
            updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
            logger.info("--Generated SMC--::");
          }
        }


        //add by ida 29-10-13
        if (conf.getProperty("kodNegeri").equals("05")) {
          if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBS")
                  || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBD")
                  || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBSS")) {
            if (!p.getPermohonanSebelum().getKodUrusan().getKod().equals("PBBSS")) {
              logger.info("--Generating SMC--::");
              kd8.setKod("SMC");
              gen8 = "STRSijilMC_NS.rdf";
              k = saveOrUpdateDokumen(fd, kd8, hkinduk.getIdHakmilik(), context);
              path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
              logger.info("::Path To save :: " + path8);
              logger.info("::Report Name ::" + gen8);
              syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path8);
              reportUtil.generateReport(gen8, params, values, dokumenPath + path8, peng);
              updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
              logger.info("--Generated SMC--::");
            }
            logger.info("--Generating 3K--::");
            kd3.setKod("3K");
            f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
            path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            logger.info("::Path To save :: " + path3);
            logger.info("::Report Name ::" + gen3);
            reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
            logger.info("--Generated 3K--::");
          }
        }

        //Jika urusan PHPC & PHPP
        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPC") || p.getPermohonanSebelum().getKodUrusan().getKod().equals("PHPP")) {
          logger.info("--PHPP/PHPC--Report--Generating--::");
          List<HakmilikPermohonan> hk1 = permohonan.getSenaraiHakmilik();
          logger.info("--hk1--::" + hk1);
          for (HakmilikPermohonan hakmilikPermohonan1 : hk1) {
            if (hakmilikPermohonan1.getHakmilik().getKodStatusHakmilik().getKod().equals("T")) {
              String idHakmilik2 = hakmilikPermohonan1.getHakmilik().getIdHakmilik();
              logger.info("--idHakmilik2--::" + idHakmilik2);
              values = new String[]{idPermohonan.trim(), idHakmilik2};
              logger.info("--values--::" + values);

              logger.info("--Generating 4k--::");
              gen9 = "REGB4K_NS.rdf";
              kd5.setKod("4K");
              b = saveOrUpdateDokumen(fd, kd5, idHakmilik2, context);
              path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
              logger.info("::Path To save :: " + path5);
              logger.info("::Report Name ::" + gen9);
              syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
              reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
              updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
              logger.info("--Generated 4k--::");

              logger.info("--Generating BSK--::");
              gen10 = "STRBSK_NS.rdf";
              kd10.setKod("BSK");
              t = saveOrUpdateDokumen(fd, kd10, idHakmilik2, context);
              path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
              logger.info("::Path To save :: " + path10);
              logger.info("::Report Name ::" + gen10);
              reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
              updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
              logger.info("--Generated BSk--::");
            }
          }


          for (HakmilikPermohonan hp : permohonan.getSenaraiHakmilik()) {
            hp.getHakmilik().getSenaraiHakmilikSebelum();
            for (HakmilikSebelum hs : hp.getHakmilik().getSenaraiHakmilikSebelum()) {
              String idHakmilik2 = hs.getHakmilik().getIdHakmilik();
              logger.info("--idHakmilik2--::" + idHakmilik2);
              values = new String[]{idPermohonan.trim(), idHakmilik2};
              logger.info("--values--::" + values);

              logger.info("--Generating 4k--::");
              gen9 = reportName.getREGB4KReportName();
              kd5.setKod("4K");
              b = saveOrUpdateDokumen(fd, kd5, idHakmilik2, context);
              path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(b.getIdDokumen());
              logger.info("::Path To save :: " + path5);
              logger.info("::Report Name ::" + gen9);
              syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
              reportUtil.generateReport(gen9, params, values, dokumenPath + path5, peng);
              updatePathDokumen(reportUtil.getDMSPath(), b.getIdDokumen());
              logger.info("--Generated 4k--::");
            }

          }
          logger.info("--Generating 5F--::");
          kd4.setKod("5F");
          gen4 = "STRB5F_NS.rdf";
          g = saveOrUpdateDokumen(fd, kd4, hkinduk.getIdHakmilik(), context);
          path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
          logger.info("::Path To save :: " + path4);
          logger.info("::Report Name ::" + gen4);
          syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
          reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
          updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
          logger.info("--Generated 5F--::");

          logger.info("--Generating BSK--::");
//                    gen10 = "STRBSK_NS.rdf";
          gen10 = reportName.getSTRBSKReportName();
          kd10.setKod("BSK");
          t = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
          path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(t.getIdDokumen());
          logger.info("::Path To save :: " + path10);
          logger.info("::Report Name ::" + gen10);
          reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
          updatePathDokumen(reportUtil.getDMSPath(), t.getIdDokumen());
          logger.info("--Generated BSk--::");

          logger.info("--Generating 4KBATAL--::");
          List<HakmilikPermohonan> hksebelum = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
          logger.info("--hksebelum--::" + hksebelum.size());
          for (HakmilikPermohonan hakmilikPermohonan4 : hksebelum) {
            if (hakmilikPermohonan4.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
              String idHakmilik4 = hakmilikPermohonan4.getHakmilik().getIdHakmilik();
              logger.info("--idHakmilik4--::" + idHakmilik4);
              values2 = new String[]{permohonan.getPermohonanSebelum().getIdPermohonan().trim(), idHakmilik4};
              logger.info("--values2--::" + values2);
              kd8.setKod("4KB");
              gen8 = "REGB4K_NS.rdf";
              k = saveOrUpdateDokumen(fd, kd8, idHakmilik4, context);
              path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
              logger.info("::Path To save :: " + path8);
              logger.info("::Report Name ::" + gen8);
              syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + idHakmilik4 + " and saving it to:" + path8);
              reportUtil.generateReport(gen8, params, values2, dokumenPath + path8, peng);
              updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
              logger.info("--Generated 4KBATAL--::");
            }
          }
        }
      }
    } else {
      // common code klu bukn HT

      //Untuk urusan PHPC & PHPP update ida - 061013
      if (conf.getProperty("kodNegeri").equals("05")
              && (p.getKodUrusan().getKod().equals("HTSPB")
              || p.getKodUrusan().getKod().equals("HTSPS")
              || p.getKodUrusan().getKod().equals("HTSC"))) {
        logger.info("---Lalu di sini D---");
        String dokumenPath = conf.getProperty("document.path");
        Dokumen e = null;
        Dokumen f = null;
        Dokumen g = null;
        Dokumen h = null;
        Dokumen i = null;
        Dokumen j = null;
        String idFolder = "";
        Permohonan permohonan = context.getPermohonan();
        String idPermohonansblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();

        idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
        FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
        String[] params = null;
        String[] values = null;
        String[] params2 = null;
        String[] values2 = null;
//        String path = "";
        String path2 = "";
        String path3 = "";
        String path4 = "";
        String path5 = "";
        String path6 = "";
        String path7 = "";
        String path8 = "";
        String path9 = "";
        String path10 = "";
        String gen2 = "";
        String gen3 = "";
        String gen4 = "";
        String gen5 = "";
        String gen6 = "";
        String gen7 = "";
        String gen8 = "";
        String gen9 = "";
        String gen10 = "";
        List<HakmilikPermohonan> hk;
        if (p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSPS")
                || p.getKodUrusan().getKod().equals("HTSC")) {
          // hk = hakmilikServ.getIDHakmilikByIDMohon(idPermohonan);
          hk = p.getSenaraiHakmilik();
        } else {
          hk = permohonan.getSenaraiHakmilik();;
        }

        KodDokumen kd2 = new KodDokumen();
        KodDokumen kd3 = new KodDokumen();
        KodDokumen kd4 = new KodDokumen();
        KodDokumen kd5 = new KodDokumen();
        KodDokumen kd6 = new KodDokumen();
        KodDokumen kd7 = new KodDokumen();
        KodDokumen kd8 = new KodDokumen();
        KodDokumen kd9 = new KodDokumen();
        KodDokumen kd10 = new KodDokumen();

        Hakmilik hkinduk = hakmilikDAO.findById(hk.get(0).getHakmilik().getIdHakmilikInduk());
        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
        values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};

        // gen2 = "REGB2K_NS.rdf"; //edited by aizuddin on 19/12/2012
        // gen3 = "REGB3K_NS.rdf"; //edited by aizuddin on 19/12/2012
        //gen2 = "STRB2KCarian_NS.rdf";
        //gen3 = "STRB3KCarian_NS.rdf";
        gen2 = "REGSB2K_NS.rdf";
        gen3 = "REGSB3K_NS.rdf";
        //gen2 = reportName.getSTRB2KReportName();
        //gen3 = reportName.getSTRB3KReportName();
//                    gen2 = "B2K_NS.rdf";
//                    gen3 = "B3K_NS.rdf";                

        kd2.setKod("2K");
        e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
        path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
        logger.info("::Path To save :: " + path2);
        logger.info("::Report Name ::" + gen2);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
        reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
        updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
        //save to mohon hakmilikLogic.
        //hakmilikPermohonan.setDokumen2(e);
        //hakmilik.setDhke(e);

        //gen Borang 3k
        kd3.setKod("3K");
        f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
        path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
        logger.info("::Path To save :: " + path3);
        logger.info("::Report Name ::" + gen3);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
        reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
        updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
        //Added by Aizuddin
        String[] URUSAN_TAK_GENERATE_GERAN = {
          "HTSC",
          "HTSPV",
          "HTSPS",
          "HTSPB",};
////            hakmilikPermohonan.setDokumen3(f);
////            hakmilik.setDhde(f);
        //gen notis 5f
        kd4.setKod("5F");
        gen4 = reportName.getSTRB5FReportName();
        g = saveOrUpdateDokumen(fd, kd4, hkinduk.getIdHakmilik(), context);
        path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
        logger.info("::Path To save :: " + path4);
        logger.info("::Report Name ::" + gen4);
        syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
        reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
        updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
//                hakmilikPermohonan.setDokumen4(g);

        // for (HakmilikPermohonan hakmilikPermohonan : hk) {

        /*TODO SET TARIKH DAFTAR AND TARIKH LUPUT FOR HSBM AND HKBM  AFTER PENDAFTAR DAFTAR*/
        //Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
        //String idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
        String idHakmilik = hkinduk.getIdHakmilik();
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        hakmilik.setTarikhDaftar(new java.util.Date());
        params = new String[]{"p_id_mohon", "p_id_hakmilik"};
        // values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};
        values = new String[]{idPermohonan.trim(), idHakmilik};


//                    gen2 = "B2K_NS.rdf";
//                    gen3 = "B3K_NS.rdf";

        //edited by mazura on 24/9/2012
        // gen5 = "REGB4K_NS.rdf";
        //  gen6 = "STRBSK_NS.rdf";
//                        gen5 = "B4K_NS.rdf";

//                  
        // gen5 = reportName.getREGB4KReportName();
        //gen6 = reportName.getSTRBSKReportName();                          
        gen5 = reportName.getSKDHKKReportName();
        gen6 = reportName.getSKDHDKReportName();
        gen7 = "B4KDHKK_NS.rdf";
        gen9 = "B4KDHDK_NS.rdf";
//                        gen6 = "STRBorangSK_MLK.rdf";


        /*Borang 4K untuk strata*/

        kd5.setKod("SKDK");
        //h = saveOrUpdateDokumen(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
        h = saveOrUpdateDokumen(fd, kd5, idHakmilik, context);
        path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
        logger.info("::Path To save :: " + path5);
        logger.info("::Report Name ::" + gen5);
        //syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
        reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
        updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());


        kd6.setKod("SKDD");
        //f = saveOrUpdateDokumen(fd, kd6, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
        f = saveOrUpdateDokumen(fd, kd6, idHakmilik, context);
        path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
        logger.info("::Path To save :: " + path6);
        logger.info("::Report Name ::" + gen6);
        reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
        updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());

        kd7.setKod("DHKK");
        //h = saveOrUpdateDokumen(fd, kd7, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
        h = saveOrUpdateDokumen(fd, kd7, idHakmilik, context);
        path7 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
        logger.info("::Path To save :: " + path7);
        logger.info("::Report Name ::" + gen7);
        //syslog.info(peng.getIdPengguna() + " generate report " + kd7.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path7);
        reportUtil.generateReport(gen7, params, values, dokumenPath + path7, peng);
        updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());

        kd9.setKod("DHDK");
        //f = saveOrUpdateDokumen(fd, kd6, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
        f = saveOrUpdateDokumen(fd, kd9, idHakmilik, context);
        path9 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
        logger.info("::Path To save :: " + path9);
        logger.info("::Report Name ::" + gen9);
        reportUtil.generateReport(gen9, params, values, dokumenPath + path9, peng);
        updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());

        if (p.getKodUrusan().getKod().equals("HTSPV")) {

          /*
           logger.info("--Generating SMC--::");
           kd8.setKod("SMC");
           gen8 = "STRSijilMC_NS.rdf";
           Dokumen k = saveOrUpdateDokumen(fd, kd8, hkinduk.getIdHakmilik(), context);
           path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
           logger.info("::Path To save :: " + path8);
           logger.info("::Report Name ::" + gen8);
           syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path8);
           reportUtil.generateReport(gen8, params, values, dokumenPath + path8, peng);
           updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
           logger.info("--Generated SMC--::"); */

          logger.info("--Generating 4AK--::");
          kd10.setKod("4KB");
          gen10 = "STRB4AK_NS.rdf";
          Dokumen kg = saveOrUpdateDokumen(fd, kd10, hkinduk.getIdHakmilik(), context);
          path10 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(kg.getIdDokumen());
          logger.info("::Path To save :: " + path10);
          logger.info("::Report Name ::" + gen10);
          syslog.info(peng.getIdPengguna() + " generate report " + kd10.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path10);
          reportUtil.generateReport(gen10, params, values, dokumenPath + path10, peng);
          updatePathDokumen(reportUtil.getDMSPath(), kg.getIdDokumen());
          logger.info("--Generated 4KB--::");
        }


        //logger.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
        // logger.debug("saving dokumen : " + h.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
        //hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
        hakmilikDAO.save(hakmilik);
        // }


        List<HakmilikPermohonan> hksebelum = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
        logger.info("--hksebelum--::" + hksebelum.size());
        for (HakmilikPermohonan hakmilikPermohonan4 : hksebelum) {
          if (hakmilikPermohonan4.getHakmilik().getKodStatusHakmilik().getKod().equals("B")) {
            String idHakmilik4 = hakmilikPermohonan4.getHakmilik().getIdHakmilik();
            logger.info("--idHakmilik4--::" + idHakmilik4);
            //values2 = new String[]{permohonan.getPermohonanSebelum().getIdPermohonan().trim(), idHakmilik4};
            values2 = new String[]{permohonan.getIdPermohonan().trim(), idHakmilik4};

            logger.info("--values2--::" + values2);
            kd8 = new KodDokumen();
            kd8.setKod("4KB");
            // String gen8 = reportName.getREGB4KReportName();
            gen8 = reportName.getREGB4KReportName();
            Dokumen k = saveOrUpdateDokumen(fd, kd8, idHakmilik4, context);
            path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
            logger.info("::Path To save :: " + path8);
            logger.info("::Report Name ::" + gen8);
            syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + idHakmilik4 + " and saving it to:" + path8);
            reportUtil.generateReport(gen8, params, values2, dokumenPath + path8, peng);
            updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
            logger.info("--Generated 4KBATAL--::");
          }
        }


      } else { //Melaka
        if (keputusan.equals("D")) {
          logger.info("---Lalu di sini D---");
          String dokumenPath = conf.getProperty("document.path");
          Dokumen e = null;
          Dokumen f = null;
          Dokumen g = null;
          Dokumen h = null;
          Dokumen i = null;
          Dokumen j = null;
          String idFolder = "";
          Permohonan permohonan = context.getPermohonan();
          String idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();

          idFolder = String.valueOf(permohonan.getFolderDokumen().getFolderId());
          FolderDokumen fd = folderDokumenDAO.findById(Long.parseLong(idFolder));
          String[] params = null;
          String[] values = null;
          String[] params2 = null;
          String[] values2 = null;
//        String path = "";
          String path2 = "";
          String path3 = "";
          String path4 = "";
          String path5 = "";
          String path6 = "";
          String gen2 = "";
          String gen3 = "";
          String gen4 = "";
          String gen5 = "";
          String gen6 = "";
          List<HakmilikPermohonan> hk;
          if (p.getKodUrusan().getKod().equals("HTSPB") || p.getKodUrusan().getKod().equals("HTSC")) {
            hk = hakmilikServ.getIDHakmilikByIDMohon(idPermohonan);
          } else {
            hk = permohonan.getSenaraiHakmilik();;
          }

          KodDokumen kd2 = new KodDokumen();
          KodDokumen kd3 = new KodDokumen();
          KodDokumen kd4 = new KodDokumen();
          KodDokumen kd5 = new KodDokumen();
          KodDokumen kd6 = new KodDokumen();
          KodDokumen kd7 = new KodDokumen();
          Hakmilik hkinduk = hakmilikDAO.findById(hk.get(0).getHakmilik().getIdHakmilikInduk());
          params = new String[]{"p_id_mohon", "p_id_hakmilik"};
          values = new String[]{idPermohonan.trim(), hkinduk.getIdHakmilik()};
          if (conf.getProperty("kodNegeri").equals("05")) {
            gen2 = "REGB2K_NS.rdf"; //edited by aizuddin on 19/12/2012
            // gen3 = "REGB3K_NS.rdf"; //edited by aizuddin on 19/12/2012
            //gen2 = reportName.getSTRB2KReportName();
            gen3 = reportName.getSTRB3KReportName();
//                    gen2 = "B2K_NS.rdf";
//                    gen3 = "B3K_NS.rdf";
          } else {
            gen2 = reportName.getSTRB2KReportName(); //edited by mazura on 24/9/2012
            gen3 = reportName.getSTRB3KReportName(); //edited by mazura on 24/9/2012
//                    gen2 = "B2K_MLK.rdf";
//                    gen3 = "B3K_MLK.rdf";
          }

          kd2.setKod("2K");
          e = saveOrUpdateDokumen(fd, kd2, hkinduk.getIdHakmilik(), context);
          path2 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
          logger.info("::Path To save :: " + path2);
          logger.info("::Report Name ::" + gen2);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd2.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path2);
          reportUtil.generateReport(gen2, params, values, dokumenPath + path2, peng);
          updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
          //save to mohon hakmilikLogic.
          //hakmilikPermohonan.setDokumen2(e);
          //hakmilik.setDhke(e);

          //gen Borang 3k
          kd3.setKod("3K");
          f = saveOrUpdateDokumen(fd, kd3, hkinduk.getIdHakmilik(), context);
          path3 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
          logger.info("::Path To save :: " + path3);
          logger.info("::Report Name ::" + gen3);
//            syslog.info(peng.getIdPengguna() + " generate report " + kd3.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path3);
          reportUtil.generateReport(gen3, params, values, dokumenPath + path3, peng);
          updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());
          //Added by Aizuddin
          String[] URUSAN_TAK_GENERATE_GERAN = {
            "HTSC",
            "HTSPV",
            "HTSPS",
            "HTSPB",};
          if (!(ArrayUtils.contains(URUSAN_TAK_GENERATE_GERAN, permohonan.getKodUrusan().getKod()))) {
//                if (!permohonan.getKodUrusan().getKod().equals("HTSC") || !permohonan.getKodUrusan().getKod().equals("HTSPV") || !permohonan.getKodUrusan().getKod().equals("HTSPS") || !permohonan.getKodUrusan().getKod().equals("HTSPB")) {
            gen5 = reportName.getDHDEReportName();
            kd6.setKod("DHDE");
            i = saveOrUpdateDokumen(fd, kd6, hkinduk.getIdHakmilik(), context);
            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(i.getIdDokumen());
            logger.info("::Path To save :: " + path5);
            logger.info("::Report Name ::" + gen5);
            syslog.info(peng.getIdPengguna() + " generate report " + kd6.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path5);
            reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
            updatePathDokumen(reportUtil.getDMSPath(), i.getIdDokumen());
            //save to mohon hakmilikLogic.
            //hakmilikPermohonan.setDokumen2(e);
            //hakmilik.setDhke(e);
//
//            //gen Borang 3k
            gen6 = reportName.getDHKEReportName();
            kd7.setKod("DHKE");
            j = saveOrUpdateDokumen(fd, kd7, hkinduk.getIdHakmilik(), context);
            path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(j.getIdDokumen());
            logger.info("::Path To save :: " + path6);
            logger.info("::Report Name ::" + gen6);
            syslog.info(peng.getIdPengguna() + " generate report " + kd7.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path6);
            reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
            updatePathDokumen(reportUtil.getDMSPath(), j.getIdDokumen());
          }
////            hakmilikPermohonan.setDokumen3(f);
////            hakmilik.setDhde(f);
          //gen notis 5f
          kd4.setKod("5F");
          gen4 = reportName.getSTRB5FReportName();
          g = saveOrUpdateDokumen(fd, kd4, hkinduk.getIdHakmilik(), context);
          path4 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(g.getIdDokumen());
          logger.info("::Path To save :: " + path4);
          logger.info("::Report Name ::" + gen4);
          syslog.info(peng.getIdPengguna() + " generate report " + kd4.getKod() + " for :" + hkinduk.getIdHakmilik() + " and saving it to:" + path4);
          reportUtil.generateReport(gen4, params, values, dokumenPath + path4, peng);
          updatePathDokumen(reportUtil.getDMSPath(), g.getIdDokumen());
//                hakmilikPermohonan.setDokumen4(g);

          for (HakmilikPermohonan hakmilikPermohonan : hk) {

            /*TODO SET TARIKH DAFTAR AND TARIKH LUPUT FOR HSBM AND HKBM  AFTER PENDAFTAR DAFTAR*/
            //Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
            String idHakmilik = hakmilikPermohonan.getHakmilik().getIdHakmilik();
            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
            hakmilik.setTarikhDaftar(new java.util.Date());
            params = new String[]{"p_id_mohon", "p_id_hakmilik"};
            values = new String[]{idPermohonan.trim(), hakmilikPermohonan.getHakmilik().getIdHakmilik()};

            if (conf.getProperty("kodNegeri").equals("05")) {
//                    gen2 = "B2K_NS.rdf";
//                    gen3 = "B3K_NS.rdf";

              //edited by mazura on 24/9/2012
              gen5 = "REGB4K_NS.rdf";
              gen6 = "STRBSK_NS.rdf";
//                        gen5 = "B4K_NS.rdf";
            } else {
//                  
              gen5 = reportName.getREGB4KReportName();
              gen6 = reportName.getSTRBSKReportName();
//                        gen6 = "STRBorangSK_MLK.rdf";
            }

            /*Borang 4K untuk strata*/
            kd5.setKod("4K");
            h = saveOrUpdateDokumen(fd, kd5, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
            path5 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(h.getIdDokumen());
            logger.info("::Path To save :: " + path5);
            logger.info("::Report Name ::" + gen5);
            syslog.info(peng.getIdPengguna() + " generate report " + kd5.getKod() + " for :" + hakmilikPermohonan.getHakmilik().getIdHakmilik() + " and saving it to:" + path5);
            reportUtil.generateReport(gen5, params, values, dokumenPath + path5, peng);
            updatePathDokumen(reportUtil.getDMSPath(), h.getIdDokumen());


            kd6.setKod("BSK");
            f = saveOrUpdateDokumen(fd, kd6, hakmilikPermohonan.getHakmilik().getIdHakmilik(), context);
            path6 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(f.getIdDokumen());
            logger.info("::Path To save :: " + path6);
            logger.info("::Report Name ::" + gen6);
            reportUtil.generateReport(gen6, params, values, dokumenPath + path6, peng);
            updatePathDokumen(reportUtil.getDMSPath(), f.getIdDokumen());



            logger.debug("saving dokumen : " + f.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            logger.debug("saving dokumen : " + h.getIdDokumen() + "into mohon hakmilik id_hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
            hakmilikDAO.save(hakmilik);
          }

          List<HakmilikPermohonan> hksebelum = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
          logger.info("--hksebelum--::" + hksebelum.size());
          for (HakmilikPermohonan hakmilikPermohonan4 : hksebelum) {
            if (hakmilikPermohonan4.getHakmilik().getKodStatusHakmilik().getKod().equals("D")) {
              String idHakmilik4 = hakmilikPermohonan4.getHakmilik().getIdHakmilik();
              logger.info("--idHakmilik4--::" + idHakmilik4);
              values2 = new String[]{permohonan.getPermohonanSebelum().getIdPermohonan().trim(), idHakmilik4};
              logger.info("--values2--::" + values2);
              KodDokumen kd8 = new KodDokumen();
              kd8.setKod("4KB");
              String gen8 = reportName.getREGB4KReportName();
              Dokumen k = saveOrUpdateDokumen(fd, kd8, idHakmilik4, context);
              String path8 = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(k.getIdDokumen());
              logger.info("::Path To save :: " + path8);
              logger.info("::Report Name ::" + gen8);
              syslog.info(peng.getIdPengguna() + " generate report " + kd8.getKod() + " for :" + idHakmilik4 + " and saving it to:" + path8);
              reportUtil.generateReport(gen8, params, values2, dokumenPath + path8, peng);
              updatePathDokumen(reportUtil.getDMSPath(), k.getIdDokumen());
              logger.info("--Generated 4KBATAL--::");
            }
          }
        }
      }// closed selain HT
    }
  }

  private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, StageContext context) {
    Pengguna pengguna = context.getPengguna();
    Pengguna peng = penggunaDAO.findById(pengguna.getIdPengguna());
    InfoAudit ia = new InfoAudit();
    Dokumen doc = null;
    doc = semakDokumenService.semakDokumen(kd, fd, id);


    if (doc == null) {
      doc = new Dokumen();
      ia.setDimasukOleh(peng);
      ia.setTarikhMasuk(new java.util.Date());
      doc.setBaru('Y');


    } else {
      ia = doc.getInfoAudit();
      ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
      ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
      ia.setDiKemaskiniOleh(peng);
      ia.setTarikhKemaskini(new java.util.Date());
      doc.setBaru('T');


    }
    doc.setFormat("application/pdf");
    doc.setInfoAudit(ia);
    //TODO : increase versi if regenarate report
    doc.setNoVersi("1.0");
    if (kd.getKod().equals("4KB")) {
      doc.setTajuk("Borang 4K Batal (" + id + ")");
    } else {
      doc.setTajuk(kd.getKod() + "(" + id + ")");
    }
    doc.setKodDokumen(kd);
    doc.setDalamanNilai1(id);
    KodKlasifikasi klasifikasi_SULIT = kodKlasifikasiDAO.findById(3);
    doc.setKlasifikasi(klasifikasi_SULIT);
    doc = dokumenService.saveOrUpdate(doc);
    KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);


    if (kf == null) {
      kf = new KandunganFolder();


    }
    kf.setInfoAudit(ia);
    kf.setFolder(fd);
    kf.setDokumen(doc);
    dokumenService.saveKandunganWithDokumen(kf);



    return doc;


  }

  private void updatePathDokumen(String namaFizikal, Long idDokumen) {
    Dokumen d = dokumenService.findById(idDokumen);
    d.setNamaFizikal(namaFizikal);
    dokumenService.saveOrUpdate(d);


  }

  private void movePihak(Permohonan permohonan, Pengguna pengguna, InfoAudit info) {
    Hakmilik hk = new Hakmilik();
    //TODO: move mohon pihak into hakmilik pihak
    List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
    Fraction f = Fraction.ZERO;

    //to insert DHDE into hakmilik
    Dokumen dhde = null;


    boolean isDhde = false;


    boolean isDhke = false;
    FolderDokumen fd = permohonan.getFolderDokumen();
    List<KandunganFolder> senaraiKF = fd.getSenaraiKandungan();


    for (KandunganFolder kandunganFolder : senaraiKF) {
      if (kandunganFolder == null || kandunganFolder.getDokumen() == null) {
        continue;


      }
      if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("DHDE")) {
        dhde = kandunganFolder.getDokumen();
        isDhde = true;


      }
      if (kandunganFolder.getDokumen().getKodDokumen().getKod().equals("DHKE")) {
        isDhke = true;


      }
    }

    for (int k = 0; k < li.size(); k++) {
      hk = li.get(k).getHakmilik();
//                Pihak pihak2 = hk.getSenaraiPihakBerkepentingan().get(k).getPihak();
      //set status Daftar to Hakmilik
      List<HakmilikUrusan> lhu = hakmilikService.findListByIdPerserahanIdHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
      HakmilikUrusan hu = lhu.get(0);


      if (hu == null) {
        continue;


      }
      KodStatusHakmilik ksh = new KodStatusHakmilik();


      if (permohonan.getKodUrusan().getKod().equals("HKP") || permohonan.getKodUrusan().getKod().equals("HSP")
              || permohonan.getKodUrusan().getKod().equals("HKTKP") || permohonan.getKodUrusan().getKod().equals("HSTKP")) {
        ksh.setKod("P");


      } else {
        ksh.setKod("D");


      }

      hk.setKodStatusHakmilik(ksh);
      //add versi
      //fikri : should check if there dhde and dhke generated
      //if generate, update version no.


      if (isDhde && dhde != null) {
        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
        // hk.setDhke(dhde);


      }
      if (isDhke) {
        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);


      }
      //copy cukai into cukai sebenar
      hk.setCukaiSebenar(hk.getCukai());
      //hk.setTarikhDaftar(new java.util.Date());
      regService.simpanHakmilik(hk);
      logger.info("Hakmilik " + hk.getIdHakmilik() + " untuk permohonan " + permohonan.getIdPermohonan() + " telah didaftarkan oleh " + pengguna.getIdPengguna());
      syslog.info("Hakmilik " + hk.getIdHakmilik() + " untuk permohonan " + permohonan.getIdPermohonan() + " telah didaftarkan oleh " + pengguna.getIdPengguna());
      String idHakmilik = li.get(k).getHakmilik().getIdHakmilik();
      //List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), idHakmilik);
      List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.findHakmilikAllPihakActiveByHakmilik(hk);
      logger.debug("Moving idHakmilik :" + idHakmilik);
      logger.debug("list :" + list.size());
      List<HakmilikPihakBerkepentingan> list_tmp = new ArrayList<HakmilikPihakBerkepentingan>();


      for (HakmilikPihakBerkepentingan p : list) {
        if (p.getHakmilik().getIdHakmilik().equals(hk.getIdHakmilik())) {
          continue;


        }
        Pihak pihak = p.getPihak();
        HakmilikPihakBerkepentingan hpk = new HakmilikPihakBerkepentingan();
        //HakmilikPihakBerkepentingan hpk = hakmilikPihakKepentinganService.findHakmilikPihakByIdPihak(pihak, hk);
        //merge if pihak already exist
        //if not create new pihak
//                if (hpk == null) {
//                    logger.debug("hpk is null");
//                    hpk = new HakmilikPihakBerkepentingan();
//                    KodJenisPihakBerkepentingan kjpb = kodJenisPihakBerkepentinganDAO.findById("PM");
//                    hpk.setJenis(kjpb);
//                    hpk.setPihak(pihak);
////                    hpk.setHakmilik(hk);
//                    hpk.setAktif('Y');
//                    hpk.setPihakCawangan(p.getPihakCawangan());
//                    hpk.setKaveatAktif('T');
//                    hpk.setPerserahan(hu);
//                    hpk.setInfoAudit(info);
//                    hpk.setHakmilik(hk);
////                    hpk.setSyerPembilang(p.getSyerPembilang());
////                    hpk.setSyerPenyebut(p.getSyerPenyebut());
//                } else {
        //KodJenisPihakBerkepentingan kjpb = kodJenisPihakBerkepentinganDAO.findById("PM");
        //hpk.setJenis(hpk.getJenis());
        //logger.debug("hpk not null");
        hpk.setJenis(p.getJenis());
        hpk.setPerserahan(hu);
        hpk.setHakmilik(hk);
        hpk.setPihak(pihak);
        hpk.setAktif('Y');
        hpk.setPihakCawangan(p.getPihakCawangan());
        hpk.setKaveatAktif('T');
        hpk.setSyerPembilang(p.getSyerPembilang());
        hpk.setSyerPenyebut(p.getSyerPenyebut());
//                        int currPembilang = hpk.getSyerPembilang();
//                        int currPenyebut = hpk.getSyerPenyebut();
//                        int newPembilang = p.getSyerPembilang();
//                        int newPenyebut = p.getSyerPenyebut();
//                        // add current fraction with new fraction
//                        //TODO : check for big fraction
//                        Fraction currFrac = new Fraction(currPembilang, currPenyebut);
//                        Fraction newFrac = new Fraction(newPembilang, newPenyebut);
//                        Fraction addFrac = currFrac.add(newFrac);
//                        hpk.setSyerPembilang(addFrac.getDenominator());
//                        hpk.setSyerPenyebut(addFrac.getNumerator());
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        hpk.setInfoAudit(info);
        //}
        list_tmp.add(hpk);


      }
      hakmilikPihakKepentinganService.update(list_tmp, hk, permohonan, info);


    }

  }

  private void moveMHANMHS(Permohonan permohonan, InfoAudit info) {
    logger.debug("::Start Moving mohon Hakmilik Asal and mohon Hakmilik Sebelum::");
    List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
    logger.debug("list size :" + li.size());


    if (li.size() > 0) {
      for (int m = 0; m < li.size(); m++) {
        Hakmilik hk = li.get(m).getHakmilik();
        HakmilikPermohonan hp = li.get(m);
        Long idMh = li.get(m).getId();
        logger.debug("idMh :" + idMh);
        List<HakmilikAsalPermohonan> lha = regService.searchMohonHakmilikAsalByID(idMh);
        List<HakmilikSebelumPermohonan> lhs = regService.searchMohonHakmilikSblmByID(idMh);
        logger.debug("lha size :" + lha.size());
        logger.debug("lhs size :" + lhs.size());



        if (!lha.isEmpty()) {
          KodStatusHakmilik ksh = new KodStatusHakmilik();
          for (int i = 0; i < lha.size(); i++) {
            HakmilikAsal ha = new HakmilikAsal();
            Hakmilik hakmiliklama = new Hakmilik();
//                        if (!permohonan.getKodUrusan().getKod().equals("HKABS") && !permohonan.getKodUrusan().getKod().equals("HKABT")
//                                && !permohonan.getKodUrusan().getKod().equals("HSSTA")) {
//                            /*BATALKAN HAKMILIK LAMA DI TABLE HAKMILIK*/
//                            String idHakmilik = lha.get(i).getHakmilik().getIdHakmilik();
//                            hakmiliklama = hakmilikDAO.findById(idHakmilik);
//                            hakmiliklama.setTarikhBatal(new java.util.Date());
//                            ksh.setKod("B");
//                            hakmiliklama.setKodStatusHakmilik(ksh);
//
//                        } else {
//                            hakmiliklama = lha.get(i).getHakmilik();
//                            hakmiliklama.setLuas(hp.getLuasTerlibat());
//                            hakmiliklama.setCukai(hp.getCukaiBaru());
//                        }
            String idHakmilik = lha.get(i).getHakmilik().getIdHakmilik();
            hakmiliklama = hakmilikDAO.findById(idHakmilik);
            hakmiliklama.setTarikhBatal(new java.util.Date());
            ksh.setKod("B");
            hakmiliklama.setKodStatusHakmilik(ksh);


            if (permohonan.getKodUrusan().getKod().equals("HKABS") && permohonan.getKodUrusan().getKod().equals("HKABT")
                    && permohonan.getKodUrusan().getKod().equals("HSSTA") && permohonan.getKodUrusan().getKod().equals("HKSTA")) {
              /*BATALKAN HAKMILIK LAMA DI TABLE HAKMILIK*/
              hakmiliklama = lha.get(i).getHakmilik();
              hakmiliklama.setLuas(hp.getLuasTerlibat());
              hakmiliklama.setCukai(hp.getCukaiBaru());


            }
            regService.simpanHakmilik(hakmiliklama);
            //ha.setHakmilik(lha.get(i).getHakmilik());
            ha.setHakmilik(hk);


            if (lha.get(i).getHakmilikSejarah() != null) {
              ha.setHakmilikAsal(lha.get(i).getHakmilikSejarah());
                
                
            }
            ha.setIdSebelum(lha.get(i).getIdHakmilikAsalPermohonan());
            ha.setInfoAudit(info);
//                            listTemp.add(ha);
            logger.debug("ha.hakmilik : " + ha.getHakmilik().getIdHakmilik());
            logger.debug("ha.hakmilikasal : " + ha.getHakmilikAsal());
            logger.debug("Saving Hakmilik Asal");
            regService.simpanHakmilikAsal(ha);

            //delete mohon hakmilik asal and hakmilik sblm when hakmilik registered



          }
        }
        if (!lhs.isEmpty()) {
          for (int j = 0; j < lhs.size(); j++) {

            HakmilikSebelum hs = new HakmilikSebelum();
            String idHakmilik = lhs.get(j).getHakmilik().getIdHakmilik();
            Hakmilik hakmilikb = hakmilikDAO.findById(idHakmilik);
            hakmilikb.setTarikhBatal(new java.util.Date());
            KodStatusHakmilik ksh = new KodStatusHakmilik();
            ksh.setKod("B");
            hakmilikb.setKodStatusHakmilik(ksh);


            if (permohonan.getKodUrusan().getKod().equals("HKABS") && permohonan.getKodUrusan().getKod().equals("HKABT")
                    && permohonan.getKodUrusan().getKod().equals("HSSTA")) {
              /*BATALKAN HAKMILIK LAMA DI TABLE HAKMILIK*/
              hakmilikb = lhs.get(j).getHakmilik();
              hakmilikb.setLuas(hp.getLuasTerlibat());
              hakmilikb.setCukai(hp.getCukaiBaru());


            } //                            HakmilikSebelumPermohonan hsp = (HakmilikSebelumPermohonan) lhs.get(j);
            //hs.setHakmilik(lhs.get(j).getHakmilik());
            hs.setHakmilik(hk);


            if (lhs.get(j).getHakmilikSejarah() != null) {
              hs.setHakmilikSebelum(lhs.get(j).getHakmilikSejarah());


            }
            hs.setIdSebelum(lhs.get(j).getIdHakmilikSebelumPermohonan());
            hs.setInfoAudit(info);
//                            listTemp2.add(hs);
            logger.debug("Saving Hakmilik Sebelum");
            regService.simpanHakmilikSebelum(hs);
            regService.simpanHakmilik(hakmilikb);


          }
        }
        //logger.debug("Deleting mohon hakmilik asal and mohon hakmilik sblm");
        //regService.deleteMohonHakmilikAsal(lha);
        //regService.deleteMohonHakmilikSblm(lhs);
      }

    }

  }

  private void insertUrusan(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
    logger.info("insertUrusan :: start :: result :: " + proposedOutcome);


    if (proposedOutcome.equals("D")) {
      List<HakmilikPermohonan> hakmilikList = permohonan.getSenaraiHakmilik();
      List<HakmilikUrusan> hakmilikUrusanList = new ArrayList<HakmilikUrusan>();


      for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
        HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
        hakmilikUrusan.setInfoAudit(info);
//            hakmilikUrusan.setPermohonan(permohonan);
        hakmilikUrusan.setDaerah(hakmilikPermohonan.getHakmilik().getDaerah());
        hakmilikUrusan.setIdPerserahan(permohonan.getIdPermohonan());
        hakmilikUrusan.setCawangan(permohonan.getCawangan());
        hakmilikUrusan.setHakmilik(hakmilikPermohonan.getHakmilik());
        hakmilikUrusan.setKodUrusan(permohonan.getKodUrusan());
        hakmilikUrusan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
        hakmilikUrusan.setTarikhDaftar(permohonan.getInfoAudit().getTarikhMasuk()); //tarikh perserahan
        hakmilikUrusan.setKodUnitLuas(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
        hakmilikUrusan.setAktif('Y');
        hakmilikUrusanList.add(hakmilikUrusan);


      }
      hakmilikService.saveOrUpdate(hakmilikUrusanList);


    }
    logger.info("insertUrusan :: finish");


  }

  @Override
  public boolean beforeGenerateReports(StageContext ctx) {
    return true;

  }

  private void saveAkaun(Permohonan permohonan, Pengguna pengguna, InfoAudit info) throws Exception {
    logger.info(":::start set Akaun for Hakmilik:::");
    String idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
    List<HakmilikPermohonan> li = permohonan.getSenaraiHakmilik();
    BigDecimal zero = new BigDecimal(0);
//        for (HakmilikPermohonan hakmilikPermohonan : li) {
//            List<HakmilikAsalPermohonan> liha = hakmilikPermohonan.getSenaraiHakmilikAsal();
//            logger.debug("list hakmilik asal size :" + liha.size());
//            if (liha.size() > 0) {
//                for (int m = 0; m < liha.size(); m++) {
//                    Hakmilik hk = liha.get(0).getHakmilik();
//                    Akaun ak = new Akaun();
//                    String kodNegeri = conf.getProperty("kodNegeri");
//                    //if n9 set no akaun idhakmilik
//                    if (kodNegeri.equals("05")) {
//                        ak.setNoAkaun(hk.getIdHakmilik());
//                    } else {
//                        ak.setNoAkaun(genNoAkaun.generateMelaka());
//                    }
//                    KodAkaun ka = new KodAkaun();
//                    ka.setKod("AC");
//                    ak.setKodAkaun(ka);
//                    ak.setHakmilik(hk);
//                    ak.setCawangan(hk.getCawangan());
//                    ak.setBaki(liha.get(m).getHakmilik().getAkaunCukai().getBaki());
//                    info.setDimasukOleh(pengguna);
//                    info.setTarikhMasuk(new java.util.Date());
//                    ak.setInfoAudit(info);
//                    List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
//                    ak.setPemegang(list.get(0).getPihak());
//                    logger.debug("::::saving akaun from hakmilik asal::::");
//                    regService.saveOrUpdate(ak);
//                    List<Akaun> listAkaun = hk.getSenaraiAkaun();
//                    listAkaun.add(ak);
//                    hk.setAkaun(listAkaun);
//                    logger.debug("::::save hakmilik baru::::");
//                    regService.simpanHakmilik(hk);
//                    logger.info(":::finish set Akaun for Hakmilik baru:::");
//                    logger.debug("::::setting kew trans for hakmilik baru::::");
//                    if (hk.getAkaunCukai() != null) {
//                        logger.debug("::::GENERATE KEW TRANS KOD::::");
//                        generate(hk.getCawangan(), pengguna, hk.getAkaunCukai());
//                    }
//                    logger.info(":::finish set kew trans Hakmilik baru:::");
//                }

//            } else {
    for (int k = 0; k < li.size(); k++) {
      Hakmilik hk = li.get(k).getHakmilik();
      Akaun ak = new Akaun();
      String kodNegeri = conf.getProperty("kodNegeri");
      //if n9 set no akaun idhakmilik
      //if (kodNegeri.equals("05")) {
      ak.setNoAkaun(hk.getIdHakmilik());
      //} else {
      //    ak.setNoAkaun(genNoAkaun.generateMelaka());
      //}
      KodAkaun ka = new KodAkaun();
      ka.setKod("AC");
      ak.setKodAkaun(ka);
      ak.setBaki(zero);
      ak.setHakmilik(hk);
      ak.setCawangan(hk.getCawangan());
      //Date oct = new Date('0110');
            /*TODO CHECK HAKMILIK DAFTAR AFTER OCT SET BAKI = 0*/
//      BigDecimal zero = new BigDecimal(0);
      Calendar cal = Calendar.getInstance();


      int year = cal.get(Calendar.YEAR);


      int month = Calendar.NOVEMBER;


      int day = 01;
      Date oct = sdfoct.parse(day + "/" + month + "/" + year);
      logger.debug("set oct " + oct.getTime());


      if (hk.getTarikhDaftar().before(oct)) {
        ak.setBaki(hk.getCukai());


      } else {
        ak.setBaki(zero);


      }
      info.setDimasukOleh(pengguna);
      info.setTarikhMasuk(new java.util.Date());
      ak.setInfoAudit(info);
      //List<PermohonanPihak> list = permohonanPihakService.getSenaraiPmohonPihakByHakmilik(permohonan.getIdPermohonan(), hk.getIdHakmilik());
      List<HakmilikPihakBerkepentingan> list = hakmilikPihakKepentinganService.findHakmilikPihakActiveAndWarisCucuCicitByHakmilik(hk);
      if (list.size() > 0) {
        ak.setPemegang(list.get(0).getPihak());
      }
      logger.debug("::::saving akaun::::");
      regService.saveOrUpdate(ak);
      List<Akaun> listAkaun = hk.getSenaraiAkaun();
      listAkaun.add(ak);
      hk.setAkaun(listAkaun);
      logger.debug("::::save hakmilik::::");
      regService.simpanHakmilik(hk);
      logger.info(":::finish set Akaun for Hakmilik:::");
      logger.debug("::::setting kew trans::::");


      if (hk.getAkaunCukai() != null) {
        logger.debug("::::GENERATE KEW TRANS KOD::::");
        generate(hk.getCawangan(), pengguna, hk.getAkaunCukai());
      }
      logger.info(":::finish set kew trans Hakmilik:::");



    }

  }

  private void updateNoVersi(Permohonan permohonan, InfoAudit info, String proposedOutcome) throws Exception {
    if (proposedOutcome.equals("D")) {
      List<HakmilikPermohonan> hp = new ArrayList();
      List<Hakmilik> lhk = new ArrayList();
      hp = permohonan.getSenaraiHakmilik();


      for (HakmilikPermohonan hakmilikPermohonan : hp) {
        Hakmilik hk = hakmilikPermohonan.getHakmilik();
        hk.setNoVersiDhde(hk.getNoVersiDhde() + 1);
        hk.setNoVersiDhke(hk.getNoVersiDhke() + 1);
        info.setDiKemaskiniOleh(pengguna);
        info.setTarikhKemaskini(new java.util.Date());
        hk.setInfoAudit(info);
        lhk.add(hk);


      }
      regService.simpanHakmilikList(lhk);


    }
  }

  public String generate(KodCawangan caw, Pengguna pguna, Akaun acc) {

    transaksi = new Transaksi();
    InfoAudit ia = new InfoAudit();
    ia.setDimasukOleh(pguna);
    ia.setTarikhMasuk(new java.util.Date());


    int year = Integer.parseInt(sdf.format(new java.util.Date()));
    transaksi.setCawangan(caw);
    transaksi.setKodTransaksi(kodTransaksiDAO.findById(kod_transaksi));
    transaksi.setAmaun(acc.getBaki());
    transaksi.setStatus(kodStatusTransaksiKewanganDAO.findById('A'));
    transaksi.setInfoAudit(ia);
    transaksi.setAkaunDebit(acc);
    transaksi.setUntukTahun(year);
    manager.saveTrans(transaksi);



    return idTransaksi;


  }

  public void updateStatusHakmilik(Permohonan permohonan, InfoAudit info, String proposedOutcome) {
    //Permohonan permohonan = context.getPermohonan();
    String idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
    info.setDiKemaskiniOleh(info.getDimasukOleh());
    info.setTarikhKemaskini(new java.util.Date());
    String kod = permohonan.getPermohonanSebelum().getKodUrusan().getKod();
    KodStatusHakmilik kodStatusHakmilik;
    logger.info("-------updateStatusHakmilik :: start--------");
    logger.info("-------permohonan--------:" + permohonan.getIdPermohonan());
    logger.info("-------permohonan SBLM--------:" + idPermohonan);
    logger.info("-------InfoAudit--------:" + info);
    if (proposedOutcome.equals("D")) {
      logger.info("-------Daftar--------:");
      List<HakmilikPermohonan> hakmilikList = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
      for (HakmilikPermohonan hakmilikPermohonan : hakmilikList) {
        hakmilik = hakmilikDAO.findById(hakmilikPermohonan.getHakmilik().getIdHakmilik());
        if (hakmilik != null && hakmilik.getKodStatusHakmilik().getKod().equals("D")) {
          logger.info("-------hakmilik--------:" + hakmilik.getIdHakmilik());
          logger.info("-------hakmilik kod sts--------:" + hakmilik.getKodStatusHakmilik().getKod());
          hakmilik.setInfoAudit(info);
          if (kod.equals("PHPP") || kod.equals("PHPC")) {
            kodStatusHakmilik = new KodStatusHakmilik();
            kodStatusHakmilik.setKod("B");
            logger.info("-------kodStatusHakmilik--------:" + kodStatusHakmilik.getKod());
            hakmilik.setTarikhBatal(new java.util.Date());
            hakmilik.setKodStatusHakmilik(kodStatusHakmilik);
          }
        }
        strService.simpanHakmilik(hakmilik);
        logger.info("------updateStatusHakmilik :: finish--------");
      }
    }
  }

  @Override
  public void afterPushBack(StageContext context) {
    throw new UnsupportedOperationException("Not supported yet.");


  }

  @Override
  public String beforePushBack(StageContext context, String proposedOutcome) {
    // return proposedOutcome;
    return "back";


  }

  public String getIdHakmilik() {
    return idHakmilik;


  }

  public void setIdHakmilik(String idHakmilik) {
    this.idHakmilik = idHakmilik;

  }

  public String getStage() {
    return stage;
  }

  public void setStage(String stage) {
    this.stage = stage;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  /**
   * @return the NoBukuSTRATA
   */
  public Hakmilik getNoBukuSTRATA() {
    return NoBukuSTRATA;
  }

  /**
   * @param NoBukuSTRATA the NoBukuSTRATA to set
   */
  public void setNoBukuSTRATA(Hakmilik NoBukuSTRATA) {
    this.NoBukuSTRATA = NoBukuSTRATA;
  }

  /**
   * @return the senaraiHakmilik
   */
  public List<Hakmilik> getSenaraiHakmilikStrata() {
    return senaraiHakmilikSTRATA;
  }

  /**
   * @param senaraiHakmilik the senaraiHakmilik to set
   */
  public void setSenaraiHakmilikStrata(List<Hakmilik> senaraiHakmilikSTRATA) {
    this.senaraiHakmilikSTRATA = senaraiHakmilikSTRATA;
  }

  /**
   * @return the senaraiBukuSTRATA
   */
  public List<Hakmilik> getSenaraiBukuSTRATA() {
    return senaraiBukuSTRATA;
  }

  /**
   * @param senaraiBukuSTRATA the senaraiBukuSTRATA to set
   */
  public void setSenaraiBukuSTRATA(List<Hakmilik> senaraiBukuSTRATA) {
    this.senaraiBukuSTRATA = senaraiBukuSTRATA;
  }

  /**
   * @return the hakmilikSTRATA
   */
  public Hakmilik getHakmilikSTRATA() {
    return hakmilikSTRATA;
  }

  /**
   * @param hakmilikSTRATA the hakmilikSTRATA to set
   */
  public void setHakmilikSTRATA(Hakmilik hakmilikSTRATA) {
    this.hakmilikSTRATA = hakmilikSTRATA;
  }

  /**
   * @return the hakmilikDaerahMukim
   */
  public Hakmilik getHakmilikDaerahMukim() {
    return hakmilikDaerahMukim;
  }

  /**
   * @param hakmilikDaerahMukim the hakmilikDaerahMukim to set
   */
  public void setHakmilikDaerahMukim(Hakmilik hakmilikDaerahMukim) {
    this.hakmilikDaerahMukim = hakmilikDaerahMukim;
  }

  /**
   * @return the senaraiHakmilikDaerahMukim
   */
  public List<Hakmilik> getSenaraiHakmilikDaerahMukim() {
    return senaraiHakmilikDaerahMukim;
  }

  /**
   * @param senaraiHakmilikDaerahMukim the senaraiHakmilikDaerahMukim to set
   */
  public void setSenaraiHakmilikDaerahMukim(List<Hakmilik> senaraiHakmilikDaerahMukim) {
    this.senaraiHakmilikDaerahMukim = senaraiHakmilikDaerahMukim;
  }

  /**
   * @return the hakmilikNoBukuSTRATA
   */
  public Hakmilik getHakmilikNoBukuSTRATA() {
    return hakmilikNoBukuSTRATA;
  }

  /**
   * @param hakmilikNoBukuSTRATA the hakmilikNoBukuSTRATA to set
   */
  public void setHakmilikNoBukuSTRATA(Hakmilik hakmilikNoBukuSTRATA) {
    this.hakmilikNoBukuSTRATA = hakmilikNoBukuSTRATA;
  }

  public Hakmilik getHakmilikSTRATA2() {
    return hakmilikSTRATA2;
  }

  /**
   * @param hakmilikSTRATA2 the hakmilikSTRATA2 to set
   */
  public void setHakmilikSTRATA2(Hakmilik hakmilikSTRATA2) {
    this.hakmilikSTRATA2 = hakmilikSTRATA2;
  }

  /**
   * @return the highestNoBukuStrata
   */
  public Hakmilik getHighestNoBukuStrata() {
    return highestNoBukuStrata;
  }

  /**
   * @param highestNoBukuStrata the highestNoBukuStrata to set
   */
  public void setHighestNoBukuStrata(Hakmilik highestNoBukuStrata) {
    this.highestNoBukuStrata = highestNoBukuStrata;
  }

  /**
   * @return the listHighestNoBukuSTRATA
   */
  public List<Hakmilik> getListHighestNoBukuSTRATA() {
    return listHighestNoBukuSTRATA;
  }

  /**
   * @param listHighestNoBukuSTRATA the listHighestNoBukuSTRATA to set
   */
  public void setListHighestNoBukuSTRATA(List<Hakmilik> listHighestNoBukuSTRATA) {
    this.listHighestNoBukuSTRATA = listHighestNoBukuSTRATA;
  }

  /**
   * @return the kodHakmilik
   */
  public KodHakmilik getKodHakmilik() {
    return kodHakmilik;
  }

  /**
   * @param kodHakmilik the kodHakmilik to set
   */
  public void setKodHakmilik(KodHakmilik kodHakmilik) {
    this.kodHakmilik = kodHakmilik;
  }

  /**
   * @return the listIDHakmilikSTRATA
   */
  public List<Hakmilik> getListIDHakmilikSTRATA() {
    return listIDHakmilikSTRATA;
  }

  /**
   * @param listIDHakmilikSTRATA the listIDHakmilikSTRATA to set
   */
  public void setListIDHakmilikSTRATA(List<Hakmilik> listIDHakmilikSTRATA) {
    this.listIDHakmilikSTRATA = listIDHakmilikSTRATA;
  }

  /**
   * @return the listIDHakmilikNOSTRATA
   */
  public List<Hakmilik> getListIDHakmilikNOSTRATA() {
    return listIDHakmilikNOSTRATA;
  }

  /**
   * @param listIDHakmilikNOSTRATA the listIDHakmilikNOSTRATA to set
   */
  public void setListIDHakmilikNOSTRATA(List<Hakmilik> listIDHakmilikNOSTRATA) {
    this.listIDHakmilikNOSTRATA = listIDHakmilikNOSTRATA;
  }

  /**
   * @return the hakmilikTiadaSTRATA
   */
  public Hakmilik getHakmilikTiadaSTRATA() {
    return hakmilikTiadaSTRATA;
  }

  /**
   * @param hakmilikTiadaSTRATA the hakmilikTiadaSTRATA to set
   */
  public void setHakmilikTiadaSTRATA(Hakmilik hakmilikTiadaSTRATA) {
    this.hakmilikTiadaSTRATA = hakmilikTiadaSTRATA;
  }

  /**
   * @return the mc
   */
  public BadanPengurusan getMc() {
    return mc;
  }

  /**
   * @param mc the mc to set
   */
  public void setMc(BadanPengurusan mc) {
    this.mc = mc;
  }
}
