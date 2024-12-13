/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.daftar.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNotisDAO;
import etanah.dao.KodRujukanDAO;
import etanah.manager.TabManager;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodNotis;
import etanah.model.KodUrusan;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanDokumen;
import etanah.model.PermohonanRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.SyerValidationService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.service.common.NotisService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.common.ValidationService;
import etanah.service.daftar.PendaftaranSuratKuasaService;
import etanah.view.daftar.constant.RegConstant;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author fikri
 */
public class ReportValidator implements StageListener {

  @Inject
  etanah.Configuration conf;
  @Inject
  SyerValidationService syerService;
  @Inject
  ValidationService validationService;
  @Inject
  TabManager tabManager;
  @Inject
  KodDokumenDAO kodDokumenDAO;
  @Inject
  FolderDokumenDAO folderDokumenDAO;
  @Inject
  SemakDokumenService semakDokumenService;
  @Inject
  DokumenService dokumenService;
  @Inject
  KandunganFolderService kandunganFolderService;
  @Inject
  KodKlasifikasiDAO kodKlasifikasiDAO;
  @Inject
  NotisService notisService;
  @Inject
  KodNotisDAO kodNotisDAO;
  @Inject
  ReportUtil reportUtil;
  @Inject
  KodRujukanDAO kodRujukanDAO;
  @Inject
  HakmilikPermohonanService hakmilikPermohonanService;
  @Inject
  PermohonanRujukanLuarService permohonanRujukanLuarServices;
  
  @Inject private PendaftaranSuratKuasaService suratKuasa;
  
  private Permohonan permohonan;
  private static final Logger LOGGER = Logger.getLogger(ReportValidator.class);
  private static Pengguna pengguna;
  private static String[] URUSAN_TO_SEMAK_SYER = {
    "PMT",
    //        "PMP",
    "PMTM",
    "PNPA",
    "PNPAB",
    "PHMMS", //add new 17/07/2013 ask by safina
    //        "PJT",
    //        "PJTM",
    //        "TENBT",
    //        "IS",
    //        "ISBLS",
    //        "KVAB",
    //        "KVAS",
    //        "KVAT",
    //        "KVLS",
    //        "KVLT",
    //"KVLTB",
    //        "KVPB",
    //        "KVPK",
    //        "KVPPT",
    //        "KVPS",
    //        "KVPT",
    "JAGAB",
    "JDGD",
    "JDS",
    "JMGD",
//    "JPGD", //tutup pada 28/08/2013
    "JPGPJ",
    //"KVSS",        
    //        "PMHUK", --tutup FAT sesi 7 - wawa
    //        "PMHUN", // tutup - safina suruh
    //        "PMKMH", -- tutup FAT sesi 3 - cug
    //        "GD",
    //        "GDCE", --tutup FAT sesi 7 - wawa
    //        "PPBM", // tutup - safina suruh
    "PPUH",
    //        "PPUHB",
    //        "PHMMT",
    //        "PJBT",
    //        "PJKBT",
    "TMAMD",
    "TMAME",
    "TMAMF",
    "TMAMG",
    "TMAML",
    "TMAMT",
    "TMAMW",
    "TRPA",
    "JML" // buka FAT sesi 7
  };
  private static String[] URUSAN_TIADA_HAKMILIK = {
    "BETSW",
    "SMB",
    "SMK",
    "SW",
    "SWDB",
    "SMBT"
  };

  @Override
  public boolean beforeStart(StageContext context) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void onGenerateReports(StageContext context) {
    permohonan = context.getPermohonan();
//        Pengguna pengguna = context.getPengguna();
    KodUrusan ku = permohonan.getKodUrusan();
    pengguna = context.getPengguna();
    if (permohonan != null) {
      FasaPermohonan fp = null;
      List<FasaPermohonan> list = permohonan.getSenaraiFasa();
      for (FasaPermohonan fasaPermohonan : list) {
        if (fasaPermohonan.getKeputusan() == null) continue;
        if (fasaPermohonan.getIdPengguna().equals(pengguna.getIdPengguna())
                && fasaPermohonan.getIdAliran().equalsIgnoreCase("kemasukan")) {
          fp = fasaPermohonan;
          break;
        }
      }
      if (fp != null) {
        boolean flag = true;
        List<HakmilikPermohonan> hk = permohonan.getSenaraiHakmilik();
//                if (hk.size() > 1) {
//                    flag = true;
//                }
        String[] params = new String[]{"p_id_mohon"};
        String[] values = new String[]{permohonan.getIdPermohonan()};
        String path = "";
        String dokumenPath = conf.getProperty("document.path");
        Dokumen d = null;
        KodDokumen kd = null;

        FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());

        if (permohonan.getKodUrusan().getKod().equals("KVSPC")) {
          List<Notis> senaraiNotis = notisService.getSenaraiNotis(permohonan.getIdPermohonan());
          if (senaraiNotis.size() > 0) {
            Notis notis = senaraiNotis.get(0);
            if (notis == null || notis.getWarta() == null) {
//              flag = false;
            } else {
              PermohonanRujukanLuar ruj = notis.getWarta();
              if (notis.getWarta().getTarikhTamat() == null) {
                //by default 2 month
                Date now = new Date();
                Date end = new Date();
                Integer newMonth = now.getMonth() + 2;
                Integer newDay = now.getDate() - 1;
                end.setMonth(newMonth);
                end.setDate(newDay);
                LOGGER.debug("now =" + now.toString());
                LOGGER.debug("end =" + end.toString());
                if (now.after(end)) {
//                  flag = false;
                }
              } else {
                Date now = new Date();
                Date end = ruj.getTarikhTamat();

                LOGGER.debug(now.toString());
                LOGGER.debug(end.toString());

                if (now.after(end)) {
//                  flag = false;
                }
              }
            }
          } else {
            if (fp.getKeputusan().getKod().equals("ST")
                    || fp.getKeputusan().getKod().equals("SG")
                    || fp.getKeputusan().getKod().equals("SD")) { // add by azri 15/8/2013 : KVSPC need to generate vdoc even for 'Syor Daftar'
              flag = true;
            } else {
              flag = false;
            }
          }
        }

        if (flag) {
          if (hk.isEmpty()) {
            List<String> t = new ArrayList<String>();
            List<String> t2 = new ArrayList<String>();
            if (fp.getKeputusan().getKod().equals("SD")) {
              List<Map<String, String>> l = tabManager.getReports(ku.getIdWorkflow(), "kemasukan", ku.getKod());
              for (Map<String, String> m : l) {
                String gen = (String) m.get("generator");
                String code = (String) m.get("code");
                t.add(gen);
                t2.add(code);
              }
            } else if (fp.getKeputusan().getKod().equals("ST")) {
              t.add("REGVDocTolak.rdf");
              t2.add("VDOC");
            } else if (fp.getKeputusan().getKod().equals("SG")) {
              t.add("REGVDocGantung.rdf");
              t2.add("VDOC");
            }

            for (int i = 0; i < t.size(); i++) {
              String gen = t.get(i);
              String code = t2.get(i);
              kd = kodDokumenDAO.findById(code);
              d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
              path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
              reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
              updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
            }
          } else {
//                        for (HakmilikPermohonan hakmilikPermohonan : hk) {
            List<String> t = new ArrayList<String>();
            List<String> t2 = new ArrayList<String>();
            if (fp.getKeputusan()!=null && fp.getKeputusan().getKod().equals("SD")) {
              List<Map<String, String>> l = tabManager.getReports(ku.getIdWorkflow(), "kemasukan", ku.getKod());
              for (Map<String, String> m : l) {
                String gen = (String) m.get("generator");
                String code = (String) m.get("code");
                t.add(gen);
                t2.add(code);
              }
            } else if (fp.getKeputusan()!=null && fp.getKeputusan().getKod().equals("ST")) {
              t.add("REGVDocTolak.rdf");
              t2.add("VDOC");
            } else if (fp.getKeputusan()!=null && fp.getKeputusan().getKod().equals("SG")) {
              t.add("REGVDocGantung.rdf");
              t2.add("VDOC");
            }

            for (int i = 0; i < t.size(); i++) {
              String gen = t.get(i);
              String code = t2.get(i);
              kd = kodDokumenDAO.findById(code);
              d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
              path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
              reportUtil.generateReport(gen, params, values, dokumenPath + path, pengguna);
              updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
              for (HakmilikPermohonan hakmilikPermohonan : hk) {
                if (kd.getKod().equals("VDOC") || kd.getKod().equals("DHKK")) {
                  hakmilikPermohonan.setDokumen1(d);
                }
                hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
              }
            }
//                        }
          }
        }

        // add by azri 15/8/2013: Generate notis 19C
        if (ku.getKod().equals("KVSPC")) {
          //generate notis19A - kaveat Pensendirian/kaveat pendaftar
          if (fp.getKeputusan().getKod().equals("SD")) {
            String reportName = "";
            boolean notisToGenerate = true;
            String kod = conf.getProperty("kodNegeri");
            kd = kodDokumenDAO.findById("19C");
            reportName = "REG_Notis19C.rdf";
            if (notisToGenerate) {
              d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
              path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
              reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
              updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
              saveNotis(permohonan, kd, d);
            }
          }
        }
// Generate Notis 19C : END
        
//      Generate 19F : 27/8/2013
              if (fp.getKeputusan().getKod().equals("SD")) {
              String reportName = "";
              boolean notisToGenerate = true;
              String kod = conf.getProperty("kodNegeri");

              if (ku.getKod().startsWith("KVP")) {                  
                  if (!ku.getKod().equals("KVPB")) {
                      if (StringUtils.isNotBlank(kod) && kod.equals("04")) {
                          KodDokumen notis19F = kodDokumenDAO.findById("19F");
                          String reportName19F = "REG_Notis19F_MLK.rdf";

                          d = saveOrUpdateDokumen(fd, notis19F, permohonan.getIdPermohonan());
                          path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
                          reportUtil.generateReport(reportName19F, params, values, dokumenPath + path, pengguna);
                          updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
                          saveNotis(permohonan, notis19F, d);
                          for (HakmilikPermohonan hakmilikPermohonan : hk) {
                              if (notis19F.getKod().equals("19F")) {
                                  hakmilikPermohonan.setDokumen5(d);                                  
                              }
                              hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
                          }
                          notisToGenerate = false;
                      }
                  }
                  
//                  if (ku.getKod().equals("KVPB") || ku.getKod().equals("KVVPT")) {
//                      notisToGenerate = false;
//                  } 
//                  else 
//                  {
//                      if (StringUtils.isNotBlank(kod) && kod.equals("04")) 
//                      {
//                          KodDokumen notis19F = kodDokumenDAO.findById("19F");
//                          String reportName19F = "REG_Notis19F_MLK.rdf";
//
//                          d = saveOrUpdateDokumen(fd, notis19F, permohonan.getIdPermohonan());
//                          path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                          reportUtil.generateReport(reportName19F, params, values, dokumenPath + path, pengguna);
//                          updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                          saveNotis(permohonan, notis19F, d);
//                          for (HakmilikPermohonan hakmilikPermohonan : hk) {
//                              if (notis19F.getKod().equals("19F")) {
//                                  hakmilikPermohonan.setDokumen5(d);
//                              }
//                              hakmilikPermohonanService.saveSingleHakmilikPermohonan(hakmilikPermohonan);
//                          }
//                          notisToGenerate = false;
//                      }
//                  }

//                  if (notisToGenerate) {
//                      d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                      path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                      reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                      updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                      saveNotis(permohonan, kd, d);
//                  }
              }

          }
        // Generate 19F : END

        //Comment out by Aizuddin, new requirement notis generate on pendaftar not kerani.
//                if (fp.getKeputusan().getKod().equals("SD")) {
//                    //generate notis for kaveat
//                    //notis19A - kaveat Pensendirian/kaveat pendaftar
//                    //notis19C - potong/batal kaveat
//                    String reportName = "";
//                    boolean notisToGenerate = true;
//                    String kod = conf.getProperty("kodNegeri");
//
//                    if (ku.getKod().startsWith("KVS")
//                            || ku.getKod().equals("KVLS") //fat sesi 3 : cug
//                            || ku.getKod().equals("KVLT")
//                            || ku.getKod().equals("KVLP")) {  // retest melacca -CUG
//                        if (ku.getKod().equals("KVSMP") //ku.getKod().equals("KVSB") ditutup pada fat sesi 5 : cug
//                                || ku.getKod().equals("KVSPC")) {
//                            kd = kodDokumenDAO.findById("19C");
//                            reportName = "REG_Notis19C.rdf";
//                        } else if (ku.getKod().equals("KVSTB")
//                                || ku.getKod().equals("KVSP")
//                                || ku.getKod().equals("KVSB")) {
//                            //nothing todo
//                            notisToGenerate = false;
//                        } else {
//                            kd = kodDokumenDAO.findById("19A");
//                            if (kod.equals("04")) {
//                                reportName = "REG_Notis19A_Pers_mlk.rdf";
//                            } else {
//                                reportName = "REG_Notis19A_Pers.rdf";
//                            }
//                        }
//
//                        if (notisToGenerate) {
//                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                            saveNotis(permohonan, kd, d);
//                        }
//                    } else if (ku.getKod().startsWith("KVP")) {
//                        if (ku.getKod().equals("KVPB")) {
////                        kd = kodDokumenDAO.findById("19C");
////                        reportName = "REG_Notis19C.rdf";
////                        many labs - PD@29/09/2010
//                            notisToGenerate = false;
//                        } else {
//                            kd = kodDokumenDAO.findById("19A");
//                            if (kod.equals("04")) {
//                                reportName = "REG_Notis19A_mlk.rdf";
//                            } else {
//                                reportName = "REG_Notis19A.rdf";
//                            }
//
//                            if (StringUtils.isNotBlank(kod) && kod.equals("04")) {
//
//                                KodDokumen notis19F = kodDokumenDAO.findById("19F");
//                                String reportName19F = "REG_Notis19F_MLK.rdf";
//
//                                d = saveOrUpdateDokumen(fd, notis19F, permohonan.getIdPermohonan());
//                                path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                                reportUtil.generateReport(reportName19F, params, values, dokumenPath + path, pengguna);
//                                updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                                saveNotis(permohonan, notis19F, d);
//                                notisToGenerate = false;
//                            }
//                        }
//
//                        if (notisToGenerate) {
//                            d = saveOrUpdateDokumen(fd, kd, permohonan.getIdPermohonan());
//                            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
//                            updatePathDokumen(reportUtil.getDMSPath(), d.getIdDokumen(), reportUtil.getContentType());
//                            saveNotis(permohonan, kd, d);
//                        }
//                    }
//
//                }
      }
    }
  }

  private void saveNotis(Permohonan permohonan, KodDokumen kd, Dokumen dokumen) {
//        Notis notis = null;
    InfoAudit ia = null;
    KodNotis kodNotis = kodNotisDAO.findById(kd.getKod());
    List<Notis> senaraiNotis = notisService.getSenaraiNotis(permohonan.getIdPermohonan());

    List<HakmilikPermohonan> senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();

    for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) { 
     if (hp == null || hp.getHakmilik() == null) {
        continue;
      }

      if (senaraiNotis.isEmpty()) {
        Notis notis = new Notis();

        PermohonanRujukanLuar prl = permohonanRujukanLuarServices.findMohonRujukLuarIdHakmilikIdPermohonan(hp.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
        if (prl == null) {
          prl = new PermohonanRujukanLuar();
          InfoAudit ia2 = new InfoAudit();
          ia2.setDimasukOleh(pengguna);
          ia2.setTarikhMasuk(new Date());
          prl.setInfoAudit(ia2);

        } else {
          InfoAudit ia2 = prl.getInfoAudit();
          ia2.setDiKemaskiniOleh(pengguna);
          ia2.setTarikhKemaskini(new java.util.Date());
          prl.setInfoAudit(ia2);
        }

        ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new Date());

        prl.setPermohonan(permohonan);
        prl.setKodRujukan(kodRujukanDAO.findById("FL"));
        prl.setCawangan(pengguna.getKodCawangan());
        prl.setHakmilik(hp.getHakmilik());
//                if (permohonan.getKodUrusan().getKod().equals("KVSPC")
//                        && prl.getTarikhTamat() == null) {                
//                    Calendar tarikhTamat = Calendar.getInstance(); 
//                    tarikhTamat.add(Calendar.MONTH, 2);
//                    prl.setTarikhTamat(tarikhTamat.getTime());
//                    prl.setTarikhKuatkuasa(Calendar.getInstance().getTime());
//                    prl.setTempohBulan(2);
//                }
        notis.setWarta(prl);
        permohonanRujukanLuarServices.saveOrUpdate(prl);

        notis.setDokumenNotis(dokumen);
        notis.setPermohonan(permohonan);
        notis.setJabatan(permohonan.getKodUrusan().getJabatan());
        notis.setKodNotis(kodNotis);
        notis.setTarikhNotis(new Date());
        notis.setInfoAudit(ia);
        notisService.saveOrUpdate(notis);

      } else {
        for (Notis notis : senaraiNotis) {
          if (notis == null || notis.getWarta() == null) {
            continue;
          }
          if (!notis.getWarta().getHakmilik().getIdHakmilik().equals(hp.getHakmilik().getIdHakmilik())) {
            continue;
          }
//          ia = notis.getInfoAudit();
//          ia.setTarikhKemaskini(new java.util.Date());
//          ia.setDiKemaskiniOleh(pengguna);
          PermohonanRujukanLuar prl = notis.getWarta();
//                    if (prl != null) {
//                        if (permohonan.getKodUrusan().getKod().equals("KVSPC") &&
//                                prl.getTarikhTamat() == null) {
//                            Calendar tarikhTamat = Calendar.getInstance();
//                            tarikhTamat.add(Calendar.MONTH, 2);
//                            tarikhTamat.add(Calendar.DATE, -1);
//                            prl.setTarikhTamat(tarikhTamat.getTime());
//                            prl.setTarikhKuatkuasa(Calendar.getInstance().getTime());
//                            prl.setTempohBulan(2);
//                        }
//                        permohonanRujukanLuarServices.saveOrUpdate(prl);                
//                    }

          notis.setDokumenNotis(dokumen);
          notis.setPermohonan(permohonan);
          notis.setJabatan(permohonan.getKodUrusan().getJabatan());
          notis.setKodNotis(kodNotis);
          notis.setTarikhNotis(new Date());
//          notis.setInfoAudit(ia);
          notisService.saveOrUpdate(notis);

        }
      }
    }
  }

  @Override
  public String beforeComplete(StageContext ctx, String proposedOutcome) {

    Permohonan p = ctx.getPermohonan();

    String kodUrusan = p.getKodUrusan().getKod();
    List<HakmilikPermohonan> senaraiPermohonan = p.getSenaraiHakmilik();



    if (proposedOutcome.equals("SD") && !p.getKodUrusan().getKod().equals("KVPK")) {
      if (ArrayUtils.contains(URUSAN_TO_SEMAK_SYER, kodUrusan)) {
        for (HakmilikPermohonan hp : senaraiPermohonan) {
          if (hp == null || hp.getHakmilik() == null) {
            continue;
          }
          Hakmilik hm = hp.getHakmilik();
          int r = 0;
          try {
//                    r = syerService.doValidateSyerPortion(p.getIdPermohonan(), hm.getIdHakmilik());
            r = syerService.validateSyer(p, hm);
            if (r != 0) {
              ctx.addMessage("Sila kemaskini kemasukan syer untuk hakmilik " + hm.getIdHakmilik() + " permohonan " + p.getIdPermohonan());
              return null;
            }
          } catch (Exception e) {
            LOGGER.error(e.getMessage());
            ctx.addMessage("Terdapat masalah untuk permohonan " + p.getIdPermohonan() + "[" + e.getMessage() + "]");
            return null;
          }
        }
      }
    }
    return proposedOutcome;
  }

  @Override
  public void afterComplete(StageContext context) {
  }

  @Override
  public boolean beforeGenerateReports(StageContext ctx) {
    Permohonan p = ctx.getPermohonan();
    List<HakmilikPermohonan> senaraiPermohonan = p.getSenaraiHakmilik();
    if (p != null) {

      List<FasaPermohonan> senaraiFasa = p.getSenaraiFasa();
      for (FasaPermohonan fp : senaraiFasa) {
        if (fp == null || StringUtils.isBlank(fp.getIdAliran())) {
          continue;
        }
        //apool 
        //add exception check senarai pihak terlibat for urusan KVPK 
        if (!p.getKodUrusan().getKod().equals("KVPK")) {
          if (fp.getIdAliran().equalsIgnoreCase("kemasukan")
                  && fp.getKeputusan()!= null 
                  && fp.getKeputusan().getKod().equals("SD")) {
            if (!ArrayUtils.contains(URUSAN_TIADA_HAKMILIK, p.getKodUrusan().getKod()) && !validationService.semakHakmilik(p)) {
              ctx.addMessage("Tiada Hakmilik yg dipilih.");
              return Boolean.FALSE;
            }
            String kodUrusan = p.getKodUrusan().getKod();

            if (ArrayUtils.contains(URUSAN_TO_SEMAK_SYER, kodUrusan)) {
              for (HakmilikPermohonan hp : senaraiPermohonan) {
                if (hp == null || hp.getHakmilik() == null) {
                  continue;
                }
                Hakmilik hm = hp.getHakmilik();
                int r = 0;
                try {
//                                r = syerService.doValidateSyerPortion(p.getIdPermohonan(), hm.getIdHakmilik());
                  r = syerService.validateSyer(p, hm);
                  if (r < 0) {
                    ctx.addMessage("Sila kemaskini kemasukan syer untuk hakmilik " + hm.getIdHakmilik());
                    return Boolean.FALSE;
                  } else if (r > 0) {
                    ctx.addMessage("Sila kemaskini kemasukan syer untuk hakmilik " + hm.getIdHakmilik());
                    return Boolean.FALSE;
                  }
                } catch (Exception e) {
                  LOGGER.error(e.getMessage());
                  ctx.addMessage("Terdapat masalah untuk permohonan "
                          + p.getIdPermohonan() + "[" + e.getMessage() + "]");
                  return Boolean.FALSE;
                }
              }
            }

            if (ArrayUtils.contains(RegConstant.URUSAN_ATAS_TANAH, kodUrusan)) {
              if (!validationService.semakPihak(p, ctx)) {
                return Boolean.FALSE;
              }
            }

            if (kodUrusan.equals(RegConstant.PINDAH_MILIK_TANAH)
                    || kodUrusan.equals(RegConstant.GADAIAN)
                    || kodUrusan.equals(RegConstant.PINDAH_MILIK_TANAH_PERINTAH_MAHKAMAH)) {
              if (!validationService.semakPemohon(p, ctx)) {
                return Boolean.FALSE;
              }
              if (!validationService.semakPihak(p, ctx)) {
                return Boolean.FALSE;
              }

            } else if (kodUrusan.equals(RegConstant.PERINTAHJUAL_PENTADBIR_GADAIAN)
                    || kodUrusan.equals(RegConstant.PERINTAHJUAL_MAHKAMAH_GADAIAN)) {
              if (!validationService.semakMohonHubungan(p)) {
                ctx.addMessage("Tiada Urusan untuk dibatalkan. Sila isi semula data.");
                return Boolean.FALSE;
              }
              if (!validationService.semakPemohon(p, ctx)) {
                return Boolean.FALSE;
              }
              if (!validationService.semakPihak(p, ctx)) {
                return Boolean.FALSE;
              }

            } else if (kodUrusan.equals(RegConstant.MELEPASKAN_GADAIAN)) {

              if (!validationService.semakMohonHubungan(p)) {
                ctx.addMessage("Tiada Urusan untuk dibatalkan/melepaskan. Sila isi semula data.");
                return Boolean.FALSE;
              }

            } else if (kodUrusan.equals(RegConstant.PERINTAHJUAL_DANAHARTA_PERSENDIRIAN)) {
              if (!validationService.semakMohonHubungan(p)) {
                ctx.addMessage("Tiada Urusan untuk dibatalkan/melepaskan. Sila isi semula data.");
                return Boolean.FALSE;
              }
              if (!validationService.semakPihak(p, ctx)) {
                return Boolean.FALSE;
              }
            } // Checking for NB - No. rujukan & Catatan
            else if (p.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("NB")) {
              if (p.getSenaraiRujukanLuar().isEmpty()) {
                ctx.addMessage("Sila Masukkan No. Rujukan Fail dan Sebab Pembetulan.");
                return Boolean.FALSE;
              }
//                        List<PermohonanRujukanLuar> senaraiPermohonanRujukanLuar = p.getSenaraiRujukanLuar();
//                        for (PermohonanRujukanLuar prl : senaraiPermohonanRujukanLuar) {
//                            if (StringUtils.isBlank(prl.getNoFail()) || StringUtils.isBlank(prl.getCatatan())) {
//                                ctx.addMessage("Sila Masukkan No. Rujukan Fail dan Sebab Pembetulan.");
//                                return Boolean.FALSE;
//                            }
//                        }
            } // Checking for NOTA - No. rujukan & Catatan -- added by m.fairul
            else if (p.getKodUrusan().getKodPerserahan().getKod().equalsIgnoreCase("N")) {
              if (p.getSenaraiRujukanLuar().isEmpty()) {
                ctx.addMessage("Sila Kemaskini Maklumat Nota");
                return false;
//                        } else if (p.getKodUrusan().getKod().equalsIgnoreCase("IGSAB")) { //TODO: Grouping
                //TODO: Done by Aizuddin
              } else if (p.getKodUrusan().getKod().equalsIgnoreCase("PSPL")) {
                  for (PermohonanRujukanLuar prl : p.getSenaraiRujukanLuar()) {
                  if (prl.getTempohTahun() == null && prl.getTarikhTamat() == null) {
                      ctx.addMessage("Sila Masukkan Tempoh Tahun, klik butang Kira dan Simpan pada Tab Maklumat Asas");
                      return Boolean.FALSE;
                    } else {
                      return Boolean.TRUE;
                    }
                  }
              } else if (kodUrusan.equals(RegConstant.URUSAN_BATAL)) {
                if (p.getSenaraiPermohonanAtasPerserahan().isEmpty()) {
                  ctx.addMessage("Urusan Terlibat Tidak Dipilih");
                  return false;
                }
              } else {
                LOGGER.info("Enter: Loop");
                for (PermohonanRujukanLuar prl : p.getSenaraiRujukanLuar()) {
                  if (StringUtils.isBlank(prl.getNoFail())) {
                    if (p.getKodUrusan().getKod().equalsIgnoreCase("AEROD")) {
                      return Boolean.TRUE;
                    } else if (p.getKodUrusan().getKod().equalsIgnoreCase("IRTB")) {
                      if (prl.getTempohHari() == null) {
                        ctx.addMessage("Sila Masukkan Tempoh Hari");
                        return Boolean.FALSE;
                      } else {
                        return Boolean.TRUE;
                      }
                    } else {
                      ctx.addMessage("Sila Masukkan No. Rujukan Fail");
                      return Boolean.FALSE;
                    }
                  } else if (p.getKodUrusan().getKod().equalsIgnoreCase("IRTBB")
                          || p.getKodUrusan().getKod().equalsIgnoreCase("IPM")
                          ) {
                    if (prl.getTempohHari() == null) {
                      ctx.addMessage("Sila Masukkan Tempoh Hari");
                      return Boolean.FALSE;
                    } else {
                      return Boolean.TRUE;
                    }
                  } else if (p.getKodUrusan().getKod().equalsIgnoreCase("PSPL")) {
                    if (prl.getTempohTahun() == null && prl.getTarikhTamat() == null) {
                      ctx.addMessage("Sila Masukkan Tempoh Tahun dan klik butang Kira");
                      return Boolean.FALSE;
                    } else {
                      return Boolean.TRUE;
                    }
                  }
                }
              }

            } else if (kodUrusan.equals(RegConstant.PERTUKARAN_PEMEGANG_AMANAH)) {
//                        if (!validationService.semakMohonAtasPihak(p)) {
//                            ctx.addMessage("Tiada pemohon yang memohon. Sila isi semula data.");
//                            return Boolean.FALSE;
//                        }
//
//                        if (!validationService.semakPihak(p, ctx)) {
//                            return Boolean.FALSE;
//                        }
            }
          }
        }
      }
      
      //new request: add surat consent into mohon_dok : training@n9 : 15/08/2014
      
      if (p.getPermohonanSebelum() != null 
              && p.getPermohonanSebelum().getKodUrusan().getJabatan().getAkronim().equalsIgnoreCase("CON")) {
          Permohonan sblm = p.getPermohonanSebelum();          
          pengguna = ctx.getPengguna();
          InfoAudit ia = new InfoAudit();
          ia.setDimasukOleh(pengguna);
          ia.setTarikhMasuk(new Date());
            for (HakmilikPermohonan hp : senaraiPermohonan) {
                if (hp == null) continue;
                if (suratKuasa.findPermohonanDokumen(hp.getHakmilik().getIdHakmilik(), sblm.getIdPermohonan()) != null) continue;
                PermohonanDokumen mohonDok = new PermohonanDokumen();
                mohonDok.setCawangan(pengguna.getKodCawangan());
                mohonDok.setDokumen(null);
                mohonDok.setHakmilikPermohonan(hp);
                mohonDok.setInfoAudit(ia);
                mohonDok.setNoRujukan(sblm.getIdPermohonan());
                mohonDok.setPermohonan(p);
                suratKuasa.savePD(mohonDok);
            }
      }
    }
    return Boolean.TRUE;
  }

  private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
    InfoAudit ia = new InfoAudit();
    Dokumen doc = null;
    doc = semakDokumenService.semakDokumen(kd, fd, id);
    if (doc == null) {
      LOGGER.debug("new Document");
      doc = new Dokumen();
      ia.setDimasukOleh(pengguna);
      ia.setTarikhMasuk(new java.util.Date());
      doc.setBaru('Y');
      doc.setNoVersi("1.0");
    } else {
      LOGGER.debug("old Document");
      ia = doc.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new java.util.Date());
      doc.setBaru('T');
      //no versi vdoc akan naik semasa push back
//            Double noVersi = Double.parseDouble(doc.getNoVersi());
//            doc.setNoVersi(String.valueOf(noVersi + 1));
    }
    doc.setFormat("application/pdf");
    doc.setInfoAudit(ia);
    LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
    KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
    doc.setKlasifikasi(klasifikasi_AM);
    LOGGER.debug("-----------TAJUK di ReportValidator-------------");
    doc.setTajuk(permohonan.getKodUrusan().getKod() + "-" + id);
    doc.setKodDokumen(kd);
    doc.setDalamanNilai1(id);
    doc = dokumenService.saveOrUpdate(doc);
    KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
    if (kf == null) {
      kf = new KandunganFolder();
    } else {
      ia = kf.getInfoAudit();
      ia.setDiKemaskiniOleh(pengguna);
      ia.setTarikhKemaskini(new java.util.Date());
    }
    kf.setInfoAudit(ia);
    kf.setFolder(fd);
    kf.setDokumen(doc);
    dokumenService.saveKandunganWithDokumen(kf);

    return doc;
  }

  private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
    Dokumen d = dokumenService.findById(idDokumen);
    d.setFormat(format);
    d.setNamaFizikal(namaFizikal);
    dokumenService.saveOrUpdate(d);
  }

  @Override
  public void afterPushBack(StageContext context) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public String beforePushBack(StageContext context, String proposedOutcome) {
    return proposedOutcome;
  }
}
