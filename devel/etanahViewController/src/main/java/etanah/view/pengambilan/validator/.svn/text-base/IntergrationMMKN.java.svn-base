/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;

import etanah.model.KodDokumen;
import etanah.view.strata.validator.*;
import able.stripes.AbleActionBean;
import java.util.logging.Level;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeConstants;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanStrata;
import etanah.model.Pihak;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.PendudukanSementaraMMKNService;
import etanah.service.PengambilanService;
import etanah.service.common.IntegrasiService;
import etanah.view.stripes.pembangunan.PermohonanPembangunanActionBean;
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
    PembangunanService pembangunanServices;
    @Inject
    StrataPtService strService;
    @Inject
    PendudukanSementaraMMKNService Servicemmkn;
    @Inject
    PengambilanService pengambilanService ;
    EMMKNService service;
    Pemohon pemohon;
    Permohonan permohonan;
    PermohonanKertas mohonKertas;
    PermohonanKertasKandungan kertasKandung;
   // HakmilikPermohonan hakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(etanah.view.pengambilan.validator.IntergrationMMKN.class);

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        LOG.info("##Intergration##");
        service = new EMMKNService();
        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
        permohonan = context.getPermohonan();
//        LOG.info("###### Permohonan " + permohonan.toString());
//        mohonKertas = pembangunanServices.findLatestKertasByIdMohonAndTarikhMasuk(permohonan.getIdPermohonan(), "MMKN");

//        List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
     mohonKertas = new PermohonanKertas() ;
        mohonKertas =  Servicemmkn.findMMKNByIdPermohonan(permohonan.getIdPermohonan());
//        if (senaraiKertas.size() > 0) {
//            mohonKertas = senaraiKertas.get(0);
//        } else {
//            mohonKertas = null;
//        }

        if (mohonKertas != null) {
            System.out.println("1");
            for (int a = 0; a < mohonKertas.getSenaraiKandungan().size(); a++) {
                kertasKandung = (PermohonanKertasKandungan) mohonKertas.getSenaraiKandungan().get(a);
                int caw = Integer.parseInt(kertasKandung.getCawangan().getKod());
                //Filter by kod urusan 
                if (permohonan.getKodUrusan().getKod().equals("SEK4A") || permohonan.getKodUrusan().getKod().equals("831A") || permohonan.getKodUrusan().getKod().equals("SEK4")) {
                    if (kertasKandung.getBil() == 5) {
                        LOG.info("###### PTD" + kertasKandung.getKandungan());
                        service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 6) {
                        LOG.info("###### PTD" + kertasKandung.getKandungan());
                        service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 7) {
                        LOG.info("###### Teknikal" + kertasKandung.getKandungan());
                        service.addUlasan("T", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 8) {
                        LOG.info("###### PTG" + kertasKandung.getKandungan());
                        service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("831B") || permohonan.getKodUrusan().getKod().equals("831C")) {
                    if (kertasKandung.getBil() == 3) {
                        LOG.info("###### Teknikal" + kertasKandung.getKandungan());
                        service.addUlasan("T", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 4) {
                        LOG.info("###### Teknikal" + kertasKandung.getKandungan());
                        service.addUlasan("T", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 5) {
                        LOG.info("###### PTG" + kertasKandung.getKandungan());
                        service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PTSP")) {
                    if (kertasKandung.getBil() == 6) {
                        LOG.info("###### PTD" + kertasKandung.getKandungan());
                        service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 7) {
                        LOG.info("###### PTD" + kertasKandung.getKandungan());
                        service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 4) {
                        LOG.info("###### Teknikal" + kertasKandung.getKandungan());
                        service.addUlasan("T", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 9) {
                        LOG.info("###### PTG" + kertasKandung.getKandungan());
                        service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PB")) {
                    if (kertasKandung.getBil() == 4) {
                        LOG.info("###### PTD" + kertasKandung.getKandungan());
                        service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                    }
                    if (kertasKandung.getBil() == 9) {
                        LOG.info("###### PTG" + kertasKandung.getKandungan());
                        service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                    }
                }    
            }


//            for (int a = 0; a < mohonKertas.getSenaraiKandungan().size(); a++) {
//                kertasKandung = (PermohonanKertasKandungan) mohonKertas.getSenaraiKandungan().get(a);
//                System.out.println("Kertas Kandung"+kertasKandung.getCawangan().getKod());
//                int caw = Integer.parseInt(kertasKandung.getCawangan().getKod());
//                System.out.println("Cawangan" +caw);
//                if (kertasKandung.getBil() == 4) {
//                    System.out.println("###### kertasKandung 4" + kertasKandung.getKandungan());
//                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
//                }
//                if (kertasKandung.getBil() == 5) {
//                    System.out.println("###### kertasKandung 5" + kertasKandung.getKandungan());
//                    service.addUlasan("G", caw + "", kertasKandung.getKandungan());
//                }
//            }

        }
//        LOG.info("###### permohonan.getSenaraiPemohon().size() " + permohonan.getSenaraiPemohon().size());
        //Checking for pemohon in permohonan terdahulu or not
        Permohonan permohonanSebelum = null ;
        int sizePemohon = 0 ;
        if(permohonan.getPermohonanSebelum() != null){
            permohonanSebelum = permohonan.getPermohonanSebelum() ;
            sizePemohon = permohonanSebelum.getSenaraiPemohon().size() ;
        }
        else {
            sizePemohon = permohonan.getSenaraiPemohon().size() ;
        }
        for (int b = 0; b < sizePemohon ; b++) {
            if(permohonanSebelum != null){
                pemohon = permohonanSebelum.getSenaraiPemohon().get(b);
            }else{
            pemohon = permohonan.getSenaraiPemohon().get(b);
            }
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
            String noPendaftaran = pemohon.getPihak().getNoPengenalan();
            String noTelefon = pemohon.getPihak().getNoTelefon1();
//        String trkhTubuh = pemohon.getPermohonan().get
//            LOG.info("###### pemohon alamat " + alamat);
//            LOG.info("###### pemohon email " + email);
//            LOG.info("###### pemohon namaSyarikat " + namaSyarikat);
//            LOG.info("###### pemohon noPendaftaran " + noPendaftaran);
//            LOG.info("###### pemohon noTelefon " + noTelefon);

            Calendar c1 = Calendar.getInstance();
            try {
                DatatypeFactory df = DatatypeFactory.newInstance();
                Date date = pemohon.getPihak().getTarikhLahir();
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(date.getTime());
                c1.setTime(pemohon.getPihak().getTarikhLahir());
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
        //Change to permohonan skrg
       
        listHakmilikMohon = permohonan.getSenaraiHakmilik();
        for (HakmilikPermohonan hakmilikPermohonan : listHakmilikMohon) {

            Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

            //Set lot information
            service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod() + "",
                    hakmilik.getNoHakmilik(), hakmilik.getNoLot(), "");

            //Set land application
            service.addLandApplication("TACK_BACK", hakmilik.getKategoriTanah().getKod());
            tajukHakmilik = tajukHakmilik + hakmilik.getKodHakmilik().getKod() + "  " + tajukNoHakmilik + hakmilik.getNoHakmilik() + ", ";
            tajukNoLot = tajukNoLot + hakmilik.getNoLot() + hakmilik.getLot().getKod() + ", ";

            tajukBPM = String.valueOf(hakmilik.getBandarPekanMukim().getKod());
            tajukDaerah = hakmilik.getDaerah().getKod();


            if (hakmilikPermohonan.getKodSeksyen() != null) {
                seksyen = hakmilikPermohonan.getKodSeksyen().getSeksyen();
            } else {
                seksyen = null;
            }

        }
        List<LaporanTanah> laporanTanahList = new ArrayList();
        laporanTanahList = pengambilanService.findLaporanTanahByIdPermohonan(permohonan.getIdPermohonan()) ;
        for(LaporanTanah laporanTanah : laporanTanahList) {
//        LaporanTanah laporanTanah = pembangunanServices.findLaporanTanahByIdPermohonanPTD(permohonan.getIdPermohonan());
        String tanahB = "";
        String tanahS = "";
        String tanahT = "";
        String tanahU = "";
        String ekar = "";
        String hektar = "";
        String kaki = "";
        String meter = "";
//
//            if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("A")) {
//                ekar = hakmilikPermohonan.getLuasTerlibat().toString();
//            } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
//                hektar = hakmilikPermohonan.getLuasTerlibat().toString();
//            } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("F")) {
//                kaki = hakmilikPermohonan.getLuasTerlibat().toString();
//            } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
//                meter = hakmilikPermohonan.getLuasTerlibat().toString();
//            }

        if (laporanTanah.getSempadanBaratMilikKerajaan() != null) {
            if (laporanTanah.getSempadanBaratMilikKerajaan() == 'Y') {
                tanahB = "Tanah Kerajaan";
            } 
        }
        else {
                if (StringUtils.isBlank(laporanTanah.getSempadanBaratNoLot())) {
                    tanahB = "Nombor Lot Tiada";
                } else {

                    tanahB = "Nombor Lot " + laporanTanah.getSempadanBaratNoLot();
                }
            }
        if (laporanTanah.getSempadanSelatanMilikKerajaan() != null) {
            if (laporanTanah.getSempadanSelatanMilikKerajaan() == 'Y') {
                tanahS = "Tanah Kerajaan";
            } 
        }
        else {
                if (StringUtils.isBlank(laporanTanah.getSempadanSelatanNoLot())) {
                    tanahS = "Nombor Lot Tiada";
                } else {
                    tanahS = "Nombor Lot " + laporanTanah.getSempadanSelatanNoLot();
                }
            }
        if (laporanTanah.getSempadanTimurMilikKerajaan() != null) {
            if (laporanTanah.getSempadanTimurMilikKerajaan() == 'Y') {
                tanahT = "Tanah Kerajaan";
            } 
        }
        else {
                if (StringUtils.isBlank(laporanTanah.getSempadanTimurNoLot())) {
                    tanahT = "Nombor Lot Tiada";
                } else {

                    tanahT = "Nombor Lot " + laporanTanah.getSempadanTimurNoLot();
                }
            }
        if (laporanTanah.getSempadanUtaraMilikKerajaan() != null) {
            if (laporanTanah.getSempadanUtaraMilikKerajaan() == 'Y') {
                tanahU = "Tanah Kerajaan";
            } 
        }
        else {
                if (StringUtils.isBlank(laporanTanah.getSempadanUtaraNoLot())) {
                    tanahU = "Nombor Lot Tiada";
                } else {

                    tanahU = "Nombor Lot " + laporanTanah.getSempadanUtaraNoLot();
                }
            }
        //@Modified -afham (try dulu)
         service.addLandDetails(tanahB, laporanTanah.getSempadanBaratKegunaan(),
                laporanTanah.getSempadanBaratNoLot(), "", laporanTanah.getKeadaanTanah(),
                "", "", "", null, null, "",
                tanahS, laporanTanah.getSempadanSelatanKegunaan(), "",
                tanahT, laporanTanah.getSempadanTimurKegunaan(),
                tanahU, laporanTanah.getSempadanUtaraKegunaan());
        }

//        service.addLandDetails(tanahB, laporanTanah.getSempadanBaratKegunaan(),
//                laporanTanah.getSempadanBaratNoLot(), null, laporanTanah.getKeadaanTanah(),
//                null, ekar, hektar, kaki, meter, null,
//                tanahS, laporanTanah.getSempadanSelatanKegunaan(), null,
//                tanahT, laporanTanah.getSempadanTimurKegunaan(),
//                tanahU, laporanTanah.getSempadanUtaraKegunaan());

//        service.addLandDetails(laporanTanah.getSempadanBaratNoLot(), laporanTanah.getSempadanBaratKegunaan(),
//                hakmilikPermohonan.getJarakDari(), hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama(),
//                laporanTanah.getKeadaanTanah(), hakmilikPermohonan.getLokasi(), ekar, hektar, kaki, meter,
//                hakmilikPermohonan.getCatatan(), laporanTanah.getSempadanSelatanNoLot(),
//                laporanTanah.getSempadanSelatanKegunaan(), hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama(),
//                laporanTanah.getSempadanTimurNoLot(), laporanTanah.getSempadanTimurKegunaan(),
//                laporanTanah.getSempadanUtaraNoLot(), laporanTanah.getSempadanUtaraKegunaan());


        //Set kertas risalat
        String tajuk = permohonan.getKodUrusan().getNama().toUpperCase() + " BAGI HAKMILIK " + tajukHakmilik + " "
                + tajukNoLot + ", " + tajukBPM + ", DAERAH " + tajukDaerah;


        service.createRisalat(permohonan.getKodUrusan().getRujukanKanun(), tajukBPM,
                tajukDaerah, permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), seksyen, tajuk);

//        LOG.info("###### context.getPengguna() " + context.getPengguna().toString());
//        LOG.info("###### context.getPermohonan() " + context.getPermohonan().toString());


        //Invoke the service and send the data
        String result;
        result = service.sendData();
//        if (context.getPermohonan() != null && context.getPengguna() != null) {
//            intgServ.saveLog(context.getPengguna(), context.getPermohonan(), service);
//        }
        if (!"SUCCESS".equals(result)) {
            context.addMessage("Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
            context.addMessage("Message : " + service.getStatusMessage());
            LOG.info("Penghantaran" + " ke " + " e-MMKN" + " tidak" + " berjaya");
            return null;
        } else {
            context.addMessage("Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
            LOG.info("Penghantaran ke e-MMKN berjaya");
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
