/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import able.stripes.AbleActionBean;
import java.util.logging.Level;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConstants;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.report.ReportUtilMMKN;
import etanah.service.StrataPtService;
import etanah.service.common.IntegrasiService;
import etanah.view.strata.PermohonanStrataActionBean;
import org.apache.commons.lang.StringUtils;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class IntergrationMMKN implements StageListener {

    @Inject
    private IntegrasiService intgServ;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    private static final Logger LOG = Logger.getLogger(IntergrationMMKN.class);
    private static Pengguna pengguna;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    ReportUtilMMKN reportUtilMMKN;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        LOG.info("##Intergration##");
        PermohonanKertasKandungan mohonKertasKand = new PermohonanKertasKandungan();
        Permohonan permohonan = context.getPermohonan();
        Pengguna peng = context.getPengguna();
//        List<PermohonanKertasKandungan> mohonKertasKandunganList = null;
//        PermohonanKertas mohonKertas = null;
//        PermohonanKertasKandungan mohonKertasKandungan;
        PermohonanStrata mohonStrata = null;
        PermohonanKertas mohonKertas = new PermohonanKertas();
        String result = null;
        EMMKNService service = new EMMKNService();

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};
        List<PermohonanStrata> listmohonStrata;
        listmohonStrata = permohonanStrataDAO.findByEqualCriterias(tname, model, null);
        if (!(listmohonStrata.isEmpty())) {
            mohonStrata = listmohonStrata.get(0);
        }

        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");

        //Set Ulasan for Jabatan Teknikal, PTD
        /*  'T' for ulasan Jabatan Teknikal
        'D' for ulasan PTD
        'G' for ulasan PTG */

        List<PermohonanKertasKandungan> senaraiKandungan = permohonan.getSenaraiKertas().get(0).getSenaraiKandungan();
        for (PermohonanKertasKandungan pkk : senaraiKandungan) {
            int caw = Integer.parseInt(pkk.getCawangan().getKod());
            LOG.info("caw :" + caw);

            String latarBelakang = null;
            String huraianPentadbir = null;
            String syorPentadbir = null;
            String huraianPtg = null;
            String syorPtg = null;
            if (pkk.getBil() == 1) {
                latarBelakang = pkk.getKandungan();
            }
            if (pkk.getBil() == 2) {
                huraianPentadbir = pkk.getKandungan();
                service.addUlasan("D", caw + "", huraianPentadbir);
            }
            if (pkk.getBil() == 3) {
                syorPentadbir = pkk.getKandungan();
                service.addUlasan("D", caw + "", syorPentadbir);
            }
            if (pkk.getBil() == 4) {
                huraianPtg = pkk.getKandungan();
                service.addUlasan("G", caw + "", huraianPtg);
            }
            if (pkk.getBil() == 5) {
                syorPtg = pkk.getKandungan();
                service.addUlasan("G", caw + "", syorPtg);
            }
        }


        //set company applicant
         /* all parameters are nullable except namaSyarikat, noPendaftaran, tarikhDitubuhkan
        and maklumatPengarahList */
        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {

            String alamat = pemohon.getPihak().getAlamat1() + ", "
                    + pemohon.getPihak().getAlamat2() + ", "
                    + pemohon.getPihak().getAlamat3() + ", "
                    + pemohon.getPihak().getAlamat4() + ", "
                    + pemohon.getPihak().getPoskod() + ", "
                    + pemohon.getPihak().getNegeri() + ".";

            String email = pemohon.getPihak().getEmail();
            String namaSyarikat = pemohon.getPihak().getNama();
            String ekutiSyarikatAsing = null;
            String ekutiSyarikatBukanBumiputera = null;
            String ekutiSyarikatBumiputra = null;
            BigDecimal modalBerbayar = null;
            BigDecimal modalPusingan = null;
            String noPendaftaran = "000";
            String noTelefon = pemohon.getPihak().getNoTelefon1();


            if (pemohon.getPihak().getNoPengenalan() != null) {
                noPendaftaran = pemohon.getPihak().getNoPengenalan();
            }

            Calendar c1 = Calendar.getInstance();
            try {
                DatatypeFactory df = DatatypeFactory.newInstance();
                Date date = pemohon.getPihak().getInfoAudit().getTarikhMasuk();
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(date.getTime());
                c1.setTime(pemohon.getPihak().getInfoAudit().getTarikhMasuk());
                LOG.info("Tarikh lahir :" + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR));

                XMLGregorianCalendar tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                service.addCompany(alamat, ekutiSyarikatAsing,
                        ekutiSyarikatBukanBumiputera, ekutiSyarikatBumiputra,
                        email, modalBerbayar, modalPusingan,
                        namaSyarikat, noPendaftaran, noTelefon, tarikhDitubuhkan);
            } catch (DatatypeConfigurationException ex) {
                java.util.logging.Logger.getLogger(IntergrationMMKN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        String tajukHakmilik = "";
        String tajukNoHakmilik = "";
        String tajukNoLot = "";
        String tajukBPM = "";
        String tajukDaerah = "";
        String seksyen = "";


        List<HakmilikPermohonan> listHakmilikMohon = new ArrayList();

        listHakmilikMohon = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hakmilikPermohonan : listHakmilikMohon) {
            Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

            //Set lot information
            service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod() + "",
                    hakmilik.getNoHakmilik(), hakmilik.getNoLot(), "");

            //Set land application
            service.addLandApplication("LOW_COST_STRATA", hakmilik.getKategoriTanah().getKod());
            tajukHakmilik = tajukHakmilik + hakmilik.getKodHakmilik().getKod() + "  " + tajukNoHakmilik + hakmilik.getNoHakmilik() + ", ";
            tajukNoLot = tajukNoLot + hakmilik.getNoLot() + hakmilik.getLot().getKod() + ", ";

            tajukBPM = String.valueOf(hakmilik.getBandarPekanMukim().getKod());
            tajukDaerah = hakmilik.getDaerah().getKod();


            if (hakmilik.getSeksyen() != null) {
                seksyen = hakmilik.getSeksyen().getSeksyen();
                LOG.info("kod seksyen tak null =" + seksyen);
            }

        }
        LOG.info("hakmilik :" + tajukHakmilik);
        LOG.info("no lot :" + tajukNoLot);
        LOG.info("BPM :" + tajukBPM);
        LOG.info("daerah :" + tajukDaerah);





        //Set land general details
        String tanahB = "";
        String tanahS = "";
        String tanahT = "";
        String tanahU = "";

        if (mohonStrata.getSempadanBaratMilikKerajaan() != null) {
            if (mohonStrata.getSempadanBaratMilikKerajaan() == 'Y') {
                tanahB = "Tanah Kerajaan";
            } else {
                if (StringUtils.isBlank(mohonStrata.getSempadanBaratNoLot())) {
                    tanahB = "Nombor Lot Tiada";
                } else {

                    tanahB = "Nombor Lot " + mohonStrata.getSempadanBaratNoLot();
                }
            }
        }
        if (mohonStrata.getSempadanSelatanMilikKerajaan() != null) {
            if (mohonStrata.getSempadanSelatanMilikKerajaan() == 'Y') {
                tanahB = "Tanah Kerajaan";
            } else {
                if (StringUtils.isBlank(mohonStrata.getSempadanSelatanNoLot())) {
                    tanahB = "Nombor Lot Tiada";
                } else {
                    tanahB = "Nombor Lot " + mohonStrata.getSempadanSelatanNoLot();
                }
            }
        }
        if (mohonStrata.getSempadanTimurMilikKerajaan() != null) {
            if (mohonStrata.getSempadanTimurMilikKerajaan() == 'Y') {
                tanahB = "Tanah Kerajaan";
            } else {
                if (StringUtils.isBlank(mohonStrata.getSempadanTimurNoLot())) {
                    tanahB = "Nombor Lot Tiada";
                } else {

                    tanahB = "Nombor Lot " + mohonStrata.getSempadanTimurNoLot();
                }
            }
        }
        if (mohonStrata.getSempadanUtaraMilikKerajaan() != null) {
            if (mohonStrata.getSempadanUtaraMilikKerajaan() == 'Y') {
                tanahB = "Tanah Kerajaan";
            } else {
                if (StringUtils.isBlank(mohonStrata.getSempadanUtaraNoLot())) {
                    tanahB = "Nombor Lot Tiada";
                } else {

                    tanahB = "Nombor Lot " + mohonStrata.getSempadanUtaraNoLot();
                }
            }
        }

        service.addLandDetails(tanahB, mohonStrata.getSempadanBaratKegunaan(),
                null, mohonStrata.getLaporankeadaanTanah(), mohonStrata.getLaporanLokasi(),
                null, null, null, null, null, null,
                tanahS, mohonStrata.getSempadanSelatanKegunaan(), null,
                tanahT, mohonStrata.getSempadanTimurKegunaan(),
                tanahU, mohonStrata.getSempadanUtaraKegunaan());


        //Set kertas risalat
        String tajuk = permohonan.getKodUrusan().getNama().toUpperCase() + " BAGI HAKMILIK " + tajukHakmilik + " "
                + tajukNoLot + ", " + tajukBPM + ", DAERAH " + tajukDaerah;


        service.createRisalat(permohonan.getKodUrusan().getRujukanKanun(), tajukBPM,
                tajukDaerah, permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), seksyen, tajuk);

        //Invoke the service and send the data
        result = service.sendData();
        if (context.getPermohonan() != null && context.getPengguna() != null) {
            intgServ.saveLog(context.getPengguna(), context.getPermohonan(), service);
        }

        String bpm = new String();
        String sks = new String();
        pengguna = context.getPengguna();
        String kodNegeri = conf.getProperty("kodNegeri");
        FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        byte[] reportMMKN = null;
        byte[] reportRingkasanMMKN = null;
        if (permohonan != null) {
            String gen1 = null;
            String code1 = null;
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;

            KodDokumen kd = null;


            gen1 = "STRKertasMMKN_MLK.rdf";
            code1 = "MMKN";





//                    kd = kodDokumenDAO.findById(code1);
//                    LOGGER.info(kd);
//                    d = semakDokumenService.semakDokumen(kd, fd, permohonan.getIdPermohonan());
//                    if (d != null) {
//                        path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(d.getIdDokumen());
//                    }
            reportMMKN = reportUtilMMKN.generateReport(gen1, params, values, null, pengguna);

            if (gen1 != null && code1 != null) {
                reportRingkasanMMKN = reportUtilMMKN.generateReport(gen1, params, values, null, pengguna);
            }



//                    if (reportMMKN != null && reportRingkasanMMKN != null) {
            if (reportMMKN != null && reportRingkasanMMKN == null) {
                LOG.info("Byte Array of Risalat MMKN=" + reportMMKN.toString());


//                            service.addRisalat(mohonKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), service.createFile(reportRingkasanMMKN, "Ringkasan MMKN"));
//                service.addRisalat(mohonKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), null);

            }
        }
//
//        //        //Set kertas risalat
//                service.createRisalat("Risalat MMKN", bpm, permohonan.getCawangan().getDaerah().getKod(),
//                        permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks, mohonKertasKand != null ? mohonKertasKand.getKandungan() : mohonKertas.getTajuk());
////        //Invoke the service and send the data
//                result = service.sendData();

        if (!"SUCCESS".equals(result)) {
            context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
            context.addMessage("Message : " + service.getStatusMessage());
            LOG.info("Penghantaran ke e-MMKN tidak berjaya");
            return null;
        } else {
            context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
            LOG.info("Penghantaran ke e-MMKN berjaya");
        }



        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
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
