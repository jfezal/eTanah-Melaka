/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp;

import com.google.inject.Inject;
import com.google.inject.Injector;
import etanah.Configuration;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Permohonan;
import etanah.model.etapp.EtappLog;
import etanah.model.etapp.EtappPermohonan;
import etanah.model.etapp.TBLINTPPTMAKLUMATPENGAMBILAN;
import etanah.view.etanahContextListener;
import etanah.view.etapp.ws.EtappLogService;
import etanah.view.etapp.ws.IntegrationEtappWebServices;
import etanah.view.stripes.hasil.kutipanAgensi.StatusInfo;
import etanah.view.utility.ETappUtil;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author faidzal
 */
public class EtappPengambilanValidator implements StageListener {

//    private static Logger logger = Logger.getLogger(EtappPengambilanValidator.class);
    private static Injector injector = etanahContextListener.getInjector();
    EtappLogService logservice = injector.getInstance(EtappLogService.class);
    @Inject
    ETappUtil etappUtil;
    @Inject
    PengambilanEtanahService ambilService;
    @Inject
    Configuration conf;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String flag = null;
        if (context.getStageName().equals("10MasukkanKpsn") || context.getStageName().equals("10MasukkanKpsn")) {

            String keputusan = null;
            Date tarikhKeputusan = null;
            String keterangankeputusan = null;
            if (context.getPermohonan().getKodUrusan().getKod().equals("ESK4")) {
                flag = "BorangA";
                if (context.getPermohonan().getKeputusan().getKod().equals("L")) {
                    keputusan = "Y";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
                } else {
                    keputusan = "T";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
                }
                // keterangan kne amik ulasan yg user key in mse masukan kptsan
                keterangankeputusan = context.getPermohonan().getUlasan();
                tarikhKeputusan = context.getPermohonan().getTarikhKeputusan();
                borangCA(context, context.getPermohonan(), keputusan, tarikhKeputusan, flag, keterangankeputusan);
                List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
                if (conf.getKodNegeri() == "04") {
                    listKodDokumen.add(kodDokumenDAO.findById("MMKN"));
                } else if (conf.getKodNegeri() == "05") {
                    listKodDokumen.add(kodDokumenDAO.findById("MB"));
                }
                sendDokumen(context, listKodDokumen, flag);
            }
            if (context.getPermohonan().getKodUrusan().getKod().equals("ESK8")) {
                flag = "BorangC";
                if (context.getPermohonan().getKeputusan().getKod().equals("L")) {
                    keputusan = "Y";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
                } else {
                    keputusan = "T";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
                }
                // keterangan kne amik ulasan yg user key in mse masukan kptsan
                keterangankeputusan = context.getPermohonan().getUlasan();
                tarikhKeputusan = context.getPermohonan().getTarikhKeputusan();
                borangCA(context, context.getPermohonan(), keputusan, tarikhKeputusan, flag, keterangankeputusan);
                List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
                 if (conf.getKodNegeri() == "04") {
                    listKodDokumen.add(kodDokumenDAO.findById("MMKN"));
                } else if (conf.getKodNegeri() == "05") {
                    listKodDokumen.add(kodDokumenDAO.findById("MB"));
                }
                sendDokumen(context, listKodDokumen, flag);
            }

        }

        //only for testing ==> shida
//   if (context.getStageName().equals("14SemakBrgD") || context.getStageName().equals("14SemakBrgD")) {
//
//            String keputusan = null;
//            Date tarikhKeputusan = null;
//            String keterangankeputusan = null;
////            if (context.getPermohonan().getKodUrusan().getKod().equals("ESK4")) {
////                flag = "BorangA";
////                if (context.getPermohonan().getKeputusan().getKod().equals("L")) {
////                    keputusan = "Y";
////                    keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
////                } else {
////                    keputusan = "T";
////                    keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
////                }
////
////                tarikhKeputusan = context.getPermohonan().getTarikhKeputusan();
////                borangCA(context, context.getPermohonan(), keputusan, tarikhKeputusan, flag, keterangankeputusan);
////                List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
////                listKodDokumen.add(kodDokumenDAO.findById("LPE"));
////                sendDokumen(context, listKodDokumen, flag);
////            }
//            if (context.getPermohonan().getKodUrusan().getKod().equals("ESK8")) {
//                try {
//                    flag = "BorangC";
//                    if (context.getPermohonan().getKeputusan().getKod().equals("L")) {
//                        keputusan = "Y";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
//                    } else {
//                        keputusan = "T";
//                        keterangankeputusan = context.getPermohonan().getKeputusan().getNama();
//                    }
//                    tarikhKeputusan = context.getPermohonan().getTarikhKeputusan();
//                    borangCA(context, context.getPermohonan(), keputusan, tarikhKeputusan, flag, keterangankeputusan);
//                    List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
//                    listKodDokumen.add(kodDokumenDAO.findById("LPE"));
//                    listKodDokumen.add(kodDokumenDAO.findById("MMKNe"));
//                    listKodDokumen.add(kodDokumenDAO.findById("MMKN"));
//                    sendDokumen(context, listKodDokumen, flag);
//                } catch (RemoteException ex) {
//                    Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IntegrationPPTeTanahtoeTappExceptionException ex) {
//                    Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        }
//   
////       if (context.getStageName().equals("14SemakBrgD")) {
////            flag = "BorangC";
////            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
////            listKodDokumen.add(kodDokumenDAO.findById("SIID"));
//////            listKodDokumen.add(kodDokumenDAO.findById("D"));
////            sendDokumen(context, listKodDokumen, flag);
////        }
//     if (context.getStageName().equals("15TandaTangan")) {
//            try {
//                flag = "BorangC";
//                List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
////            listKodDokumen.add(kodDokumenDAO.findById("SIID"));
//                listKodDokumen.add(kodDokumenDAO.findById("D"));
//                sendDokumen(context, listKodDokumen, flag);
//            } catch (RemoteException ex) {
//                Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IntegrationPPTeTanahtoeTappExceptionException ex) {
//                Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//   
        //end test 
        if (context.getStageName().equals("16HantarBrgA")) {
            flag = "BorangA";
            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
            listKodDokumen.add(kodDokumenDAO.findById("SIIA"));
            listKodDokumen.add(kodDokumenDAO.findById("A"));
            sendDokumen(context, listKodDokumen, flag);
        }

        if (context.getStageName().equals("19HantarBrgB")) {
            flag = "WartaS4";
            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
            //listKodDokumen.add(kodDokumenDAO.findById("SIIA"));
            listKodDokumen.add(kodDokumenDAO.findById("B"));
            sendDokumen(context, listKodDokumen, flag);
        }
        if (context.getStageName().equals("16HantarBrgD")) {
            flag = "WartaS8";
            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
//            listKodDokumen.add(kodDokumenDAO.findById("SIID"));
            listKodDokumen.add(kodDokumenDAO.findById("D"));
            sendDokumen(context, listKodDokumen, flag);
        }
        if (context.getStageName().equals("19MaklumEndBrgD")) {
//            FasaPermohonan = ambilService.findMohonFasaByIdMohonIdPengguna(context.getPermohonan().getIdPermohonan());
//            if (){}
//            
            EtappPermohonan ep = ambilService.findByidMohon(context.getPermohonan().getIdPermohonan());
            Logger.getLogger(EtappPengambilanValidator.class.getName()).info("fail jkptg" + ep.getNoFail());
            TBLINTPPTMAKLUMATPENGAMBILAN mp = ambilService.FindMaklumatPengambilan(ep.getNoFail());

            String idAliranMhonFasa = "19MaklumEndBrgD"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE

            String kedesakan = mp.getFlagPemohonanSegera();
            Logger.getLogger(EtappPengambilanValidator.class.getName()).info("kedesakan = " + kedesakan);

            if (kedesakan != null) {
                Logger.getLogger(EtappPengambilanValidator.class.getName()).info("kedesakan = " + kedesakan);
                FasaPermohonan mohonFasa = ambilService.findMohonFasaByIdMohonIdPengguna2(context.getPermohonan().getIdPermohonan(), idAliranMhonFasa);

                if (mohonFasa != null) {
                    if (Integer.parseInt(kedesakan) == 2) {
                        mohonFasa.setKeputusan(kodKeputusanDAO.findById("XK"));

                    } else if (Integer.parseInt(kedesakan) == 1 || Integer.parseInt(kedesakan) == 3) {
                        mohonFasa.setKeputusan(kodKeputusanDAO.findById("DE"));
                    } else {

//                    mohonFasa.setKeputusan(null);
                        mohonFasa.setKeputusan(kodKeputusanDAO.findById("XK"));

                    }
                }
                ambilService.saveMohonFasa(mohonFasa);
            } else {
            }

            flag = "WartaS8";
            Date tarikhEndorsan = null;
            String nojilid = null;
            maklumatEndorsan(context, flag, tarikhEndorsan, nojilid);

            FasaPermohonan mohonFasa2 = ambilService.findMohonFasaByIdMohonIdPengguna2(context.getPermohonan().getIdPermohonan(), idAliranMhonFasa);
            Logger.getLogger(EtappPengambilanValidator.class.getName()).info("mohonFasa2" + mohonFasa2.getKeputusan().getKod());
            proposedOutcome = mohonFasa2.getKeputusan().getKod();
            Logger.getLogger(EtappPengambilanValidator.class.getName()).info("proposedOutcome" + proposedOutcome);
            return proposedOutcome;
        }
        if (context.getStageName().equals("22KemukaBrgI")) {
            flag = "BorangI";
            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
            listKodDokumen.add(kodDokumenDAO.findById("I"));
            sendDokumen(context, listKodDokumen, flag);
        }
        if (context.getStageName().equals("24MaklumanEndorsanK")) {
            flag = "BorangK";
            Date tarikhEndorsan = null;
            String nojilid = null;
            maklumatEndorsan(context, flag, tarikhEndorsan, nojilid);
        }
        if (context.getStageName().equals("27KemukaSijilPembebasanUkur")) {
            flag = "SijilUkur";
            List<KodDokumen> listKodDokumen = new ArrayList<KodDokumen>();
            listKodDokumen.add(kodDokumenDAO.findById("SPU"));
            sendDokumen(context, listKodDokumen, flag);
        }

        if (context.getStageName().equals("30HantarHakmilikSamb")) {
            flag = "PU";
            Date tarikhEndorsan = null;
            String nojilid = null;
            maklumatEndorsan(context, flag, tarikhEndorsan, nojilid);
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    public void borangCA(StageContext context, Permohonan mohon, String keputusan, Date tarikhTerima, String flag, String keterangankeputusan) {
        EtappPermohonan etapp = ambilService.findByidMohon(mohon.getIdPermohonan());
        try {

//            Tblpptkeputusanmmkfrmetanah[] param = new Tblpptkeputusanmmkfrmetanah[1];
//            Tblpptkeputusanmmkfrmetanah params = new Tblpptkeputusanmmkfrmetanah();
//            params.setFlag_proses(flag);
//            params.setFlag_keputusan_mmk(keputusan);
//            params.setKeterangan_keputusan_mmk(keterangankeputusan);
//            params.setTarikh_terima_data(convertDate(tarikhTerima));
//            params.setTurutan(1);
//            params.setNo_fail_jkptg(etapp.getNoFail());
//            param[0] = params;
//            StatusInfo si = client.updateKeputusanMMK(param);
//            if (si.getMsg().equals("success")) {
//                logservice.insertLog("ACQ", "1", "Keputusan MMK = " + etapp.getNoFail());
//            } else {
//                //tgk blik
//                logservice.insertLog("ACQ", "2", "Keputusan MMK = " + etapp.getNoFail());
//            }

            String status = etappUtil.updateKeputusanMMK(flag, keputusan, keterangankeputusan, etapp.getNoFail(), tarikhTerima);
            if ("201".equals(status)) {
                logservice.insertLog("ACQ", "1", "Keputusan MMK = " + etapp.getNoFail());
                List<EtappLog> el = ambilService.findLog("MMK = " + etapp.getNoFail());
                context.addMessage("    Penghantaran Keputusan ke MyeTapp Berjaya.    ");
                context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
            } else {
                logservice.insertLog("ACQ", "2", "Keputusan MMK = " + etapp.getNoFail());
                List<EtappLog> el = ambilService.findLog("MMK = " + etapp.getNoFail());
//                context.addMessage("    Penghantaran Keputusan ke MyeTapp Tidak Berjaya.    ");
                context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
                context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
            }

        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Keputusan MMK = " + etapp.getNoFail());
            List<EtappLog> el = ambilService.findLog("MMK = " + etapp.getNoFail());
//            context.addMessage("    Penghantaran Keputusan ke MyeTapp Tidak Berjaya.    ");
            context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
            context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));

        }
    }

    public String maklumatEndorsan(StageContext context, String flag, Date tarikhEndorsan, String nojilid) {
        EtappPermohonan etapp = ambilService.findByidMohon(context.getPermohonan().getIdPermohonan());
        try {

//            List<HakmilikPermohonan> hp = context.getPermohonan().getSenaraiHakmilik();
//            Tblpptendorsanfrmetanah[] param = new Tblpptendorsanfrmetanah[hp.size()];
//            for (int h = 0; h <= hp.size(); h++) {
//                HakmilikUrusan hu = ambilService.FindTarikhEndorsan(context.getPermohonan().getIdPermohonan(), hp.get(h).getHakmilik().getIdHakmilik());
//                FolderDokumen fd = ambilService.FindNoJilid(context.getPermohonan().getIdPermohonan());
//                Tblpptendorsanfrmetanah data = new Tblpptendorsanfrmetanah();
//                data.setFlag_proses(flag);
//                data.setNo_jilid(fd.getNoJilid());
//                data.setTarikh_endorsan(ddMMyyyy(hu.getTarikhDaftar()));
//                data.setTurutan(1);
//                data.setNo_fail_jkptg(etapp.getNoFail());
//                data.setId_hakmilik(hp.get(h).getHakmilik().getIdHakmilik());
//                data.setKod_daerah(hp.get(h).getHakmilik().getDaerah().getKod());
//                data.setKod_mukim(hp.get(h).getHakmilik().getBandarPekanMukim().getbandarPekanMukim());
//                data.setKod_unit_hakmilik(hp.get(h).getHakmilik().getKodHakmilik().getKod());
//                data.setNo_hakmilik(hp.get(h).getHakmilik().getNoHakmilik());
//                param[h] = data;
//
//            }
//            StatusInfo si = client.endorsan(param);
//            if (si.getMsg().equals("success")) {
//                logservice.insertLog("ACQ", "1", "Keputusan MMK = " + etapp.getNoFail());
//            } else {
//                logservice.insertLog("ACQ", "2", "Keputusan MMK = " + etapp.getNoFail());
//            }

            List<HakmilikPermohonan> hps = context.getPermohonan().getSenaraiHakmilik();

            List<Map<String, String>> listOfEndorsan = new ArrayList<Map<String, String>>();

            for (HakmilikPermohonan hp : hps) {
                Map<String, String> map = new HashMap<String, String>();
                HakmilikUrusan hu = ambilService.FindTarikhEndorsan(context.getPermohonan().getPermohonanSebelum().getIdPermohonan(), hp.getHakmilik().getIdHakmilik());

//                FolderDokumen fd = ambilService.FindNoJilid(context.getPermohonan().getIdPermohonan());
                List<FolderDokumen> listFD = ambilService.FindListNoJilid(context.getPermohonan().getIdPermohonan());

                FolderDokumen fd = listFD.get(0);

                map.put("flag", flag);
                map.put("noJilid", fd.getNoJilid());
                map.put("tkhEndorsan", ddMMyyyy(hu.getTarikhDaftar()));
                map.put("noFailJkptg", etapp.getNoFail());
                map.put("idHakmilik", hp.getHakmilik().getIdHakmilik());
                map.put("kodDaerah", hp.getHakmilik().getDaerah().getKod());
                map.put("kodMukim", hp.getHakmilik().getBandarPekanMukim().getbandarPekanMukim());
                map.put("kodUnitHakmilik", hp.getHakmilik().getKodHakmilik().getKod());
                map.put("noHakmilik", hp.getHakmilik().getNoHakmilik());
                listOfEndorsan.add(map);
            }

            if (!listOfEndorsan.isEmpty()) {
                String status = etappUtil.endorsan(listOfEndorsan);
                if ("201".equals(status)) {
                    logservice.insertLog("ACQ", "1", "Maklumat Endosan = " + etapp.getNoFail());
                    List<EtappLog> el = ambilService.findLog("Endosan = " + etapp.getNoFail());
                    context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
                    context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));

                } else {
                    logservice.insertLog("ACQ", "2", "Maklumat Endosan = " + etapp.getNoFail());
                    List<EtappLog> el = ambilService.findLog("Endosan = " + etapp.getNoFail());
//                    context.addMessage("    Penghantaran Makluman ke MyeTapp Tidak Berjaya.      ");
                    context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
                    context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
                }
            }
            return "";
        } catch (Exception ex) {
            Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Maklumat Endosan = " + etapp.getNoFail());
            List<EtappLog> el = ambilService.findLog("Endosan = " + etapp.getNoFail());
//            context.addMessage("    Penghantaran Makluman ke MyeTapp Tidak Berjaya.      ");
            context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
            context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
            return null;
        }

    }

    public String sendDokumen(StageContext context, List<KodDokumen> listKodDokumen, String flag) {

        List<Map<String, String>> listOfDocuments = new ArrayList<Map<String, String>>();

        EtappPermohonan etapp = ambilService.findByidMohon(context.getPermohonan().getIdPermohonan());

        String docPath = conf.getProperty("document.path");

        for (KodDokumen kd : listKodDokumen) {
            List<KandunganFolder> kf = ambilService.findDokByKodDokumen(context.getPermohonan().getFolderDokumen().getFolderId(), kd.getKod());
            for (KandunganFolder kff : kf) {
                Dokumen d = kff.getDokumen();
                String fn = d.getNamaFizikal();
                File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
                if (f != null) {

                    Map<String, String> map = new HashMap<String, String>();
                    map.put("path", f.getAbsolutePath());
                    map.put("borang", flag);
                    map.put("fileName", f.getName());
                    map.put("contentType", d.getFormat());
                    map.put("title", d.getTajuk());
                    map.put("length", String.valueOf(f.length()));
                    map.put("failNo", etapp.getNoFail());
                    listOfDocuments.add(map);

                }
            }
        }

        if (!listOfDocuments.isEmpty()) {
            String status = etappUtil.dokumenToMyEtapp(listOfDocuments);
            if ("201".equals(status)) {
                logservice.insertLog("ACQ", "1", "Maklumat Borang = " + etapp.getNoFail());

                List<EtappLog> el = ambilService.findLog("Borang = " + etapp.getNoFail());
                context.addMessage("        Penghantaran Lampiran ke MyeTapp Berjaya.      ");
                context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
            } else {
                logservice.insertLog("ACQ", "2", "Maklumat Borang = " + etapp.getNoFail());
                List<EtappLog> el = ambilService.findLog("Borang = " + etapp.getNoFail());
//                context.addMessage("        Penghantaran Lampiran ke MyeTapp TIDAK Berjaya.      ");
                context.addMessage("    Penghantaran Makluman ke MyeTapp Berjaya.      ");
                context.addMessage("Dihantar pada " + String.valueOf(el.get(0).getInfoAudit().getTarikhMasuk()));
            }

        }

        return "";
    }

//    public String sendDokumen(StageContext context, List<KodDokumen> listKodDokumen, String flag) throws RemoteException, IntegrationPPTeTanahtoeTappExceptionException {
//
//        EtappPermohonan etapp = ambilService.findByidMohon(context.getPermohonan().getIdPermohonan());
//        for (KodDokumen kd : listKodDokumen) {
//
//            String docPath = conf.getProperty("document.path");
//            List<KandunganFolder> kf = ambilService.findDokByKodDokumen(context.getPermohonan().getFolderDokumen().getFolderId(), kd.getKod());
//            Tblintpptdokumenfrmetanah[] param = new Tblintpptdokumenfrmetanah[kf.size()];
//            for (KandunganFolder kff : kf) {
//                Tblintpptdokumenfrmetanah params = new Tblintpptdokumenfrmetanah();
//                int i = 0;
//                Dokumen d = kff.getDokumen();
//                String fn = d.getNamaFizikal();
////attachment sijil ukur by hakmilik
//                System.out.println("fn ::" + fn);
//                File f = new File(docPath + (docPath.endsWith(File.separator) ? "" : File.separator) + fn);
//                try {
//                    if (f != null) {
//
//                        InputStream inputStream = new FileInputStream(f.getAbsolutePath());
//                        byte[] byteArray = IOUtils.toByteArray(inputStream);
//
//                        if (flag.equals("SijilUkur")) {
////                            for (HakmilikPermohonan hp : etapp.getMohon().getSenaraiHakmilik()) {
////                                
////                                param[].setContentUpload
////                                (byteArray
////                                );
////                                tbl.setFlagProses(flag);
////                                tbl.setJenisFormatFail(d.getFormat());
////                                tbl.setJenisFail(d.getFormat());
////                                tbl.setTajukDokumen(d.getTajuk());
////                                tbl.setTurutan(1);
////                                tbl.setNamaDokumen(f.getName());
////                                tbl.setSize((int) f.length());
////                                tbl.setNoFailJkptg(etapp.getNoFail());
////                                tbl.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
////                                tbl.setKodDaerah(hp.getHakmilik().getDaerah().getKod());
////                                tbl.setKodMukim(hp.getHakmilik().getBandarPekanMukim().getbandarPekanMukim());
////                                tbl.setTurutan(1);
////                                insertDokumen_to_etapp.getTblintpptdokumenfrmetanah().add(tbl);
////                                
////                            }
//                        } else {
//                            ByteArrayDataSource rawData = new ByteArrayDataSource(byteArray, d.getFormat());
//                            DataHandler data = new DataHandler(rawData);
//
//                            params.setFlag_proses(flag);
//                            params.setContent_upload(data);
//                            params.setJenis_format_fail(d.getFormat());
//                            params.setJenis_fail(d.getFormat());
//                            params.setTajuk_dokumen(d.getTajuk());
//                            params.setTurutan(1);
//                            params.setNama_dokumen(f.getName());
//                            params.setNo_fail_jkptg(etapp.getNoFail());
//                            params.setSize((int) f.length());
//
//                            params.setTurutan(1);
//                            param[i] = params;
//                        }
//                    }
//
//                } catch (IOException ex) {
//                    System.out.println(ex.toString());
//                }
//                client.dokumenToMyEtapp(param);
//                logservice.insertLog("ACQ", "1", "Maklumat Borang = " + etapp.getNoFail());
//                context.addMessage("        Penghantaran Lampiran ke MyeTapp Berjaya.      ");
//            }
//        }
//        try {
////            client.dokumenToMyEtapp(param);
////            logservice.insertLog("ACQ", "1", "Maklumat Borang = " + etapp.getNoFail());
////            context.addMessage("Penghantaran Lampiran ke MyeTapp Berjaya");
//
//            return "";
//        } catch (Exception d) {
//            Logger.getLogger(EtappPengambilanValidator.class.getName()).log(Level.SEVERE, null, d);
//            logservice.insertLog("ACQ", "2", "Maklumat Borang = " + etapp.getNoFail());
//            context.addMessage("        Penghantaran Lampiran ke MyeTapp Tidak Berjaya.         ");
//            return null;
//        }
//
//    }
    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    public String convertDate(Date date) {
        String s;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        s = sdf.format(date);
        return s;
    }

    public String ddMMyyyy(Date date) {
        String s;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        s = sdf.format(date);
        return s;
    }
}
