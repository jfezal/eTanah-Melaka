/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import java.util.logging.Level;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PembangunanService;
import etanah.service.StrataPtService;
import etanah.service.common.IntegrasiService;
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
import etanah.model.FasaPermohonan;

/**
 *
 * @author syaiful
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
    EMMKNService service;
    Pemohon pemohon;
    Permohonan permohonan;
    PermohonanKertas mohonKertas;
    PermohonanKertasKandungan kertasKandung;
    private static final Logger LOG = Logger.getLogger(etanah.view.pembangunan.validator.IntergrationMMKN.class);

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
        String tajuk = null;
        LOG.info("##Intergration##");
        service = new EMMKNService();
        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
        permohonan = context.getPermohonan();
        LOG.info("###### KOD RPMMK/PBSK : " + permohonan.getKodUrusan().getKod());
//        LOG.info("###### Permohonan " + permohonan.toString());
//        mohonKertas = pembangunanServices.findLatestKertasByIdMohonAndTarikhMasuk(permohonan.getIdPermohonan(), "MMKN");

        FasaPermohonan fp = pembangunanServices.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "perakuanmmknptg");
        if (fp.getKeputusan().getKod().equals("LK")) {
            String tajukHakmilik = "";
            String tajukNoHakmilik = "";
            String tajukNoLot = "";
            String tajukBPM = "";
            String tajukDaerah = "";
            String seksyen = "";
            LOG.info("##Intergration##");
            service = new EMMKNService();
            //Set username and password for web service authentication
            service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
            permohonan = context.getPermohonan();
            //        LOG.info("###### Permohonan " + permohonan.toString());
            //        mohonKertas = pembangunanServices.findLatestKertasByIdMohonAndTarikhMasuk(permohonan.getIdPermohonan(), "MMKN");



            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("RPMMK") || 
                     permohonan.getKodUrusan().getKod().equalsIgnoreCase("RLTB") || permohonan.getKodUrusan().getKod().equalsIgnoreCase("RPP")) {
                LOG.info("###### RPMMK/PBSK/RLTB/RPP SUCCESS");
                List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
                senaraiKertas = pembangunanServices.findSenaraiKertasByKod(permohonan.getIdPermohonan(), "MMKN");
                if (senaraiKertas.size() > 0) {
                    mohonKertas = senaraiKertas.get(0);
                } else {
                    mohonKertas = null;
                }

                if (mohonKertas != null) {
                    for (int a = 0; a < mohonKertas.getSenaraiKandungan().size(); a++) {
                        kertasKandung = (PermohonanKertasKandungan) mohonKertas.getSenaraiKandungan().get(a);
                        int caw = Integer.parseInt(kertasKandung.getCawangan().getKod());
                        if (kertasKandung.getBil() == 4) {
                            //                    LOG.info("###### kertasKandung 4" + kertasKandung.getKandungan());
                            service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                        }
                        if (kertasKandung.getBil() == 5) {
                            //                    LOG.info("###### kertasKandung 5" + kertasKandung.getKandungan());
                            service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                        }
                        if (kertasKandung.getBil() == 6) {
                            //                    LOG.info("###### kertasKandung 5" + kertasKandung.getKandungan());
                            tajuk = kertasKandung.getKandungan();
                        }
                    }
                }
                //        LOG.info("###### permohonan.getSenaraiPemohon().size() " + permohonan.getSenaraiPemohon().size());
                for (int b = 0; b < permohonan.getPermohonanSebelum().getSenaraiPemohon().size(); b++) {
                    pemohon = permohonan.getPermohonanSebelum().getSenaraiPemohon().get(b);
                    String alamat = pemohon.getPihak().getAlamat1() + ", "
                            + pemohon.getPihak().getAlamat2() + ", "
                            + pemohon.getPihak().getAlamat3() + ", "
                            + pemohon.getPihak().getAlamat4() + ", "
                            + pemohon.getPihak().getPoskod() + ", "
                            + pemohon.getPihak().getNegeri().getNama() + ".";

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

//                Calendar c1 = Calendar.getInstance();
                    try {
                        Date date;
                        DatatypeFactory df = DatatypeFactory.newInstance();
                        if (pemohon.getPihak().getTarikhLahir() == null) {
                            date = new Date();
                        } else {
                            date = pemohon.getPihak().getTarikhLahir();
                        }
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTimeInMillis(date.getTime());
//                    c1.setTime(pemohon.getPihak().getTarikhLahir());
                        XMLGregorianCalendar tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                        service.addCompany(alamat, ekutiSyarikatAsing,
                                ekutiSyarikatBukanBumiputera, ekutiSyarikatBumiputra,
                                email, modalBerbayar, modalPusingan,
                                namaSyarikat, noPendaftaran, noTelefon, tarikhDitubuhkan);
                    } catch (DatatypeConfigurationException ex) {
                        java.util.logging.Logger.getLogger(IntergrationMMKN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tajukHakmilik = "";
                tajukNoHakmilik = "";
                tajukNoLot = "";
                tajukBPM = "";
                tajukDaerah = "";
                seksyen = "";
                List<HakmilikPermohonan> listHakmilikMohon = new ArrayList();

                listHakmilikMohon = permohonan.getPermohonanSebelum().getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : listHakmilikMohon) {

                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                    //Set lot information
                    service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod() + "",
                            hakmilik.getNoHakmilik(), hakmilik.getNoLot(), "");

                    //Set land application
                    service.addLandApplication("DEVELOPMENT", hakmilik.getKategoriTanah().getKod());
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

                LOG.info("##permohonan.getKodUrusan().getKod().equalsIgnoreCase(RPMMK)//" + (permohonan.getKodUrusan().getKod().equalsIgnoreCase("RPMMK")));
                LOG.info("##permohonan.getKodUrusan().getKod().equalsIgnoreCase(PBSK)//" + (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBSK")));
                LaporanTanah laporanTanah = pembangunanServices.findLaporanTanahByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());
                String tanahB = "";
                String tanahS = "";
                String tanahT = "";
                String tanahU = "";

                if (laporanTanah.getSempadanBaratMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanBaratMilikKerajaan() == 'Y') {
                        tanahB = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanBaratNoLot())) {
                            tanahB = "Nombor Lot Tiada";
                        } else {

                            tanahB = "Nombor Lot " + laporanTanah.getSempadanBaratNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanSelatanMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanSelatanMilikKerajaan() == 'Y') {
                        tanahS = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanSelatanNoLot())) {
                            tanahS = "Nombor Lot Tiada";
                        } else {
                            tanahS = "Nombor Lot " + laporanTanah.getSempadanSelatanNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanTimurMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanTimurMilikKerajaan() == 'Y') {
                        tanahT = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanTimurNoLot())) {
                            tanahT = "Nombor Lot Tiada";
                        } else {

                            tanahT = "Nombor Lot " + laporanTanah.getSempadanTimurNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanUtaraMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanUtaraMilikKerajaan() == 'Y') {
                        tanahU = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanUtaraNoLot())) {
                            tanahU = "Nombor Lot Tiada";
                        } else {
                            tanahU = "Nombor Lot " + laporanTanah.getSempadanUtaraNoLot();
                        }
                    }
                }
                String unitLuas = "";
                String lokasitanah = "";
                if (laporanTanah.getHakmilikPermohonan() != null && laporanTanah.getHakmilikPermohonan().getHakmilik() != null && laporanTanah.getHakmilikPermohonan().getHakmilik().getKodUnitLuas() != null) {
                    unitLuas = laporanTanah.getHakmilikPermohonan().getHakmilik().getKodUnitLuas().getKod();
                    lokasitanah = laporanTanah.getHakmilikPermohonan().getLokasi();
                } else {
                }
                String luaseMMKNHektar = null;
                String luaseMMKNMeter = null;
                if (unitLuas.equalsIgnoreCase("H")) {
                    BigDecimal luas = laporanTanah.getHakmilikPermohonan().getHakmilik().getLuas();
                    luaseMMKNHektar = luas.toString();
                    LOG.info("###### unitLuas :" + unitLuas);
                    LOG.info("###### luaseMMKN :" + luaseMMKNHektar);
                } else if (unitLuas.equalsIgnoreCase("M")) {
                    BigDecimal luas = laporanTanah.getHakmilikPermohonan().getHakmilik().getLuas();
                    luaseMMKNMeter = luas.toString();
                    LOG.info("###### unitLuas :" + unitLuas);
                    LOG.info("###### luaseMMKN :" + luaseMMKNMeter);
                } else {
                    LOG.info("##### Kod UOM not in (H,M)");
                }

                service.addLandDetails(tanahB, laporanTanah.getSempadanBaratKegunaan(),
                        laporanTanah.getSempadanBaratNoLot(), laporanTanah.getKeadaanTanah(), laporanTanah.getKeadaanTanah(),
                        lokasitanah, null, luaseMMKNHektar, null, luaseMMKNMeter, null,
                        tanahS, laporanTanah.getSempadanSelatanKegunaan(), null,
                        tanahT, laporanTanah.getSempadanTimurKegunaan(),
                        tanahU, laporanTanah.getSempadanUtaraKegunaan());

            } else {
                LOG.info("###### RPMMK/PBSK/RLTB/RPP SUCCESS");
                List<PermohonanKertas> senaraiKertas = new ArrayList<PermohonanKertas>();
                senaraiKertas = pembangunanServices.findSenaraiKertasByKod(permohonan.getIdPermohonan(), "MMKN");
                if (senaraiKertas.size() > 0) {
                    mohonKertas = senaraiKertas.get(0);
                } else {
                    mohonKertas = null;
                }

                if (mohonKertas != null) {
                    for (int a = 0; a < mohonKertas.getSenaraiKandungan().size(); a++) {
                        kertasKandung = (PermohonanKertasKandungan) mohonKertas.getSenaraiKandungan().get(a);
                        int caw = Integer.parseInt(kertasKandung.getCawangan().getKod());
                        if (kertasKandung.getBil() == 4) {
                            //                    LOG.info("###### kertasKandung 4" + kertasKandung.getKandungan());
                            service.addUlasan("D", caw + "", kertasKandung.getKandungan());
                        }
                        if (kertasKandung.getBil() == 5) {
                            //                    LOG.info("###### kertasKandung 5" + kertasKandung.getKandungan());
                            service.addUlasan("G", caw + "", kertasKandung.getKandungan());
                        }
                        if (kertasKandung.getBil() == 6) {
                            //                    LOG.info("###### kertasKandung 5" + kertasKandung.getKandungan());
                            tajuk = kertasKandung.getKandungan();
                        }
                    }
                }
                //        LOG.info("###### permohonan.getSenaraiPemohon().size() " + permohonan.getSenaraiPemohon().size());
                for (int b = 0; b < permohonan.getSenaraiPemohon().size(); b++) {
                    pemohon = permohonan.getSenaraiPemohon().get(b);
                    String alamat = pemohon.getPihak().getAlamat1() + ", ";

                    if (StringUtils.isNotBlank(pemohon.getPihak().getAlamat2())) {
                        alamat = alamat + pemohon.getPihak().getAlamat2() + ", ";
                    }
                    if (StringUtils.isNotBlank(pemohon.getPihak().getAlamat3())) {
                         alamat = alamat + pemohon.getPihak().getAlamat3() + ", ";
                    }
                    if (StringUtils.isNotBlank(pemohon.getPihak().getAlamat4())) {
                         alamat = alamat + pemohon.getPihak().getAlamat4() + ", ";
                    }
                    
                    alamat = alamat 
                            + pemohon.getPihak().getPoskod() + ", "
                            + pemohon.getPihak().getNegeri().getNama() + ".";


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

//                Calendar c1 = Calendar.getInstance();
                    try {
                        Date date;
                        DatatypeFactory df = DatatypeFactory.newInstance();
                        if (pemohon.getPihak().getTarikhLahir() == null) {
                            date = new Date();
                        } else {
                            date = pemohon.getPihak().getTarikhLahir();
                        }
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTimeInMillis(date.getTime());
//                    c1.setTime(pemohon.getPihak().getTarikhLahir());
                        XMLGregorianCalendar tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                        service.addCompany(alamat, ekutiSyarikatAsing,
                                ekutiSyarikatBukanBumiputera, ekutiSyarikatBumiputra,
                                email, modalBerbayar, modalPusingan,
                                namaSyarikat, noPendaftaran, noTelefon, tarikhDitubuhkan);
                    } catch (DatatypeConfigurationException ex) {
                        java.util.logging.Logger.getLogger(IntergrationMMKN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                tajukHakmilik = "";
                tajukNoHakmilik = "";
                tajukNoLot = "";
                tajukBPM = "";
                tajukDaerah = "";
                seksyen = "";
                List<HakmilikPermohonan> listHakmilikMohon = new ArrayList();

                listHakmilikMohon = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : listHakmilikMohon) {

                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();

                    //Set lot information
                    service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod() + "",
                            hakmilik.getNoHakmilik(), hakmilik.getNoLot(), "");

                    //Set land application
                    service.addLandApplication("DEVELOPMENT", hakmilik.getKategoriTanah().getKod());
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

                LOG.info("##permohonan.getKodUrusan().getKod().equalsIgnoreCase(RPMMK)//" + (permohonan.getKodUrusan().getKod().equalsIgnoreCase("RPMMK")));
                LOG.info("##permohonan.getKodUrusan().getKod().equalsIgnoreCase(PBSK)//" + (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBSK")));
                LaporanTanah laporanTanah = pembangunanServices.findLaporanTanahByIdPermohonan(permohonan.getIdPermohonan());
                String tanahB = "";
                String tanahS = "";
                String tanahT = "";
                String tanahU = "";

                if (laporanTanah.getSempadanBaratMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanBaratMilikKerajaan() == 'Y') {
                        tanahB = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanBaratNoLot())) {
                            tanahB = "Nombor Lot Tiada";
                        } else {

                            tanahB = "Nombor Lot " + laporanTanah.getSempadanBaratNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanSelatanMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanSelatanMilikKerajaan() == 'Y') {
                        tanahS = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanSelatanNoLot())) {
                            tanahS = "Nombor Lot Tiada";
                        } else {
                            tanahS = "Nombor Lot " + laporanTanah.getSempadanSelatanNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanTimurMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanTimurMilikKerajaan() == 'Y') {
                        tanahT = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanTimurNoLot())) {
                            tanahT = "Nombor Lot Tiada";
                        } else {

                            tanahT = "Nombor Lot " + laporanTanah.getSempadanTimurNoLot();
                        }
                    }
                }
                if (laporanTanah.getSempadanUtaraMilikKerajaan() != null) {
                    if (laporanTanah.getSempadanUtaraMilikKerajaan() == 'Y') {
                        tanahU = "Tanah Kerajaan";
                    } else {
                        if (StringUtils.isBlank(laporanTanah.getSempadanUtaraNoLot())) {
                            tanahU = "Nombor Lot Tiada";
                        } else {
                            tanahU = "Nombor Lot " + laporanTanah.getSempadanUtaraNoLot();
                        }
                    }
                }
                String lokasitanah = "";
                String unitLuas = "";
                if (laporanTanah.getHakmilikPermohonan() != null && laporanTanah.getHakmilikPermohonan().getHakmilik() != null && laporanTanah.getHakmilikPermohonan().getHakmilik().getKodUnitLuas() != null) {
                    unitLuas = laporanTanah.getHakmilikPermohonan().getHakmilik().getKodUnitLuas().getKod();
                    lokasitanah = laporanTanah.getHakmilikPermohonan().getLokasi();
                } else {
                }
                String luaseMMKNHektar = null;
                String luaseMMKNMeter = null;
                if (unitLuas.equalsIgnoreCase("H")) {
                    BigDecimal luas = laporanTanah.getHakmilikPermohonan().getHakmilik().getLuas();
                    luaseMMKNHektar = luas.toString();
                    LOG.info("###### unitLuas :" + unitLuas);
                    LOG.info("###### luaseMMKN :" + luaseMMKNHektar);
                } else if (unitLuas.equalsIgnoreCase("M")) {
                    BigDecimal luas = laporanTanah.getHakmilikPermohonan().getHakmilik().getLuas();
                    luaseMMKNMeter = luas.toString();
                    LOG.info("###### unitLuas :" + unitLuas);
                    LOG.info("###### luaseMMKN :" + luaseMMKNMeter);
                } else {
                    LOG.info("##### Kod UOM not in (H,M)");
                }

                service.addLandDetails(tanahB, laporanTanah.getSempadanBaratKegunaan(),
                        laporanTanah.getSempadanBaratNoLot(), laporanTanah.getKeadaanTanah(), laporanTanah.getKeadaanTanah(),
                        lokasitanah, null, luaseMMKNHektar, null, luaseMMKNMeter, null,
                        tanahS, laporanTanah.getSempadanSelatanKegunaan(), null,
                        tanahT, laporanTanah.getSempadanTimurKegunaan(),
                        tanahU, laporanTanah.getSempadanUtaraKegunaan());
            }

            //Set kertas risalat
//            tajuk = permohonan.getKodUrusan().getNama().toUpperCase() + " BAGI HAKMILIK " + tajukHakmilik + " "
//                    + tajukNoLot + ", " + tajukBPM + ", DAERAH " + tajukDaerah;
//            LOG.info("###### TAJUK RISALAT : " + tajuk);
//    public void createRisalat(String aktaPeruntukan, String bandar, String kodDaerah,String noRujukanPTD, String noRujukanPTG, String seksyen, String tajukRisalat) 
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
        } else {
            //do nothing
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
