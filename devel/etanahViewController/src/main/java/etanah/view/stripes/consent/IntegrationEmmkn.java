/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.emmkn.ws.EMMKNService;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodDokumen;
import etanah.model.LaporanTanah;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanUrusan;
import etanah.report.ReportUtilMMKN;
import etanah.service.ConsentPtdService;
import etanah.service.SemakDokumenService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
public class IntegrationEmmkn implements StageListener {

    @Inject
    ConsentPtdService conService;
    @Inject
    ReportUtilMMKN reportUtilMMKN;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(IntegrationEmmkn.class);
    private static Pengguna pengguna;

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

//     http://172.16.25.26:8080/emmkn
//     etanah/etanah1234
//     BARU
//     http://172.16.25.26:8080/emmkn/login.do
//     mohdtaib/password01
//     LAMA
//     url :   http://172.16.254.39:8080/emmkn/risalatTanah/edit.do?ctrl=risalatTanahTabs&action=TabClick&param=tab3
//     username : mohdtaib
//     password : default value
//     REJECT_APPEAL - TANAH LADANG
//     APPEAL_FOREIGNER - MMKN
        LOG.info("Start Integration EMMKN....");

        Permohonan permohonan = context.getPermohonan();
        String result = null;
        EMMKNService service = new EMMKNService();

        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");

        //'T' for ulasan Jabatan Teknikal
        //'D' for ulasan PTD
        //'G' for ulasan PTG
        PermohonanKertas permohonanKertas = conService.findMohonKertas(permohonan.getIdPermohonan());

        if (permohonanKertas != null) {

            for (PermohonanKertasKandungan kertasKandungan : permohonanKertas.getSenaraiKandungan()) {
                int caw = Integer.parseInt(kertasKandungan.getCawangan().getKod());

                if (kertasKandungan.getBil() == 1) {             //Huraian PTG
                    LOG.info("Perakuan PTG : " + kertasKandungan.getKandungan());
                    LOG.info("Add ulasan.....");
                    service.addUlasan("G", caw + "", kertasKandungan.getKandungan());
                }
            }
        }

        //Set Maklumat Pemohon Individu
        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            LOG.info("Nama Pemohon : " + pemohon.getPihak().getNama());
            String alamatPjabt = pemohon.getInstitusiAlamat1() + "," + pemohon.getInstitusiAlamat2() + "," + pemohon.getInstitusiAlamat3() + "," + pemohon.getInstitusiAlamat4() + ".";
            String alamat = pemohon.getPihak().getAlamat1() + "," + pemohon.getPihak().getAlamat2() + "," + pemohon.getPihak().getAlamat3() + "," + pemohon.getPihak().getAlamat4() + ".";
            if (alamatPjabt.equals(",,,.")) {
                alamatPjabt = "Tiada";
            }

            if (pemohon.getPihak().getJenisPengenalan().getKod().equals("S") || pemohon.getPihak().getJenisPengenalan().getKod().equals("U") || pemohon.getPihak().getJenisPengenalan().getKod().equals("D") || pemohon.getPihak().getJenisPengenalan().getKod().equals("O") || pemohon.getPihak().getJenisPengenalan().getKod().equals("N")) {
                //PEMOHON ADALAH COMPANY

                Calendar c1 = Calendar.getInstance();
                try {
                    XMLGregorianCalendar tarikhDitubuhkan;
                    if (pemohon.getPihak().getTarikhLahir() != null) {
                        DatatypeFactory df = DatatypeFactory.newInstance();
                        Date date = pemohon.getPihak().getTarikhLahir();
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTimeInMillis(date.getTime());
                        c1.setTime(pemohon.getPihak().getTarikhLahir());
                        LOG.info("Tarikh tubuh : " + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR));
                        tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                    } else {
                        DatatypeFactory df = DatatypeFactory.newInstance();
                        Date date = pemohon.getInfoAudit().getTarikhMasuk();
                        GregorianCalendar gc = new GregorianCalendar();
                        gc.setTimeInMillis(date.getTime());
                        c1.setTime(pemohon.getInfoAudit().getTarikhMasuk());
                        LOG.info("Tarikh tubuh (tarikh masuk) : " + c1.get(Calendar.DATE) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.YEAR));
                        tarikhDitubuhkan = df.newXMLGregorianCalendar(gc);
                    }
                    LOG.info("Add company.....");
                    service.addCompany(alamat, null, null, null, pemohon.getPihak().getEmail(), null, null, pemohon.getPihak().getNama(),
                            pemohon.getPihak().getNoPengenalan(), pemohon.getPihak().getNoTelefon1(), tarikhDitubuhkan);
                } catch (DatatypeConfigurationException ex) {
                    java.util.logging.Logger.getLogger(IntegrationEmmkn.class.getName()).log(java.util.logging.Level.OFF, null, ex);
                }
            } else {
                //PEMOHON ADALAH INDIVIDU
                boolean anak = false;
                if (pemohon.getPihak().getWargaNegara() != null) {
                    if (pemohon.getPihak().getWargaNegara().getKod().equals("MAL")) {
                        anak = true;
                    }
                }

                if (pemohon.getPihak().getBangsa() != null) {
                    if (pemohon.getPihak().getBangsa().getKod().equals("T")) {
                        pemohon.getPihak().getBangsa().setKod("LN");
                    }
                }

                String umur = "TIADA";
                String tempatLahir = "TIADA";
                String warganegara = "";
                String bangsa = "";
                String jantina = "1";
                Integer tempohMastautin = 0;
                BigDecimal pendapatan = new BigDecimal("0.00");

                if (pemohon.getUmur() != null) {
                    umur = pemohon.getUmur().toString();
                }
                if (pemohon.getPihak().getTempatLahir() != null) {
                    tempatLahir = pemohon.getPihak().getTempatLahir();
                }
                if (pemohon.getTempohMastautin() != null) {
                    tempohMastautin = pemohon.getTempohMastautin();
                }
                if (pemohon.getPendapatan() != null) {
                    pendapatan = pemohon.getPendapatan();
                }
                if (pemohon.getPihak().getWargaNegara() != null) {
                    warganegara = pemohon.getPihak().getWargaNegara().getKod();
                }
                if (pemohon.getPihak().getBangsa() != null) {
                    bangsa = pemohon.getPihak().getBangsa().getKod();
                }
                if (pemohon.getPihak().getKodJantina() != null) {
                    jantina = pemohon.getPihak().getKodJantina();
                }

                LOG.info("Add individual.....");
                LOG.info("alamat pejabat : " + alamatPjabt);
                LOG.info("anak : " + anak);
                LOG.info("emel :" + pemohon.getPihak().getEmail());
                LOG.info("warganegara :" + warganegara);
                LOG.info("bangsa : " + bangsa);
                LOG.info("jantina : " + jantina);
                LOG.info("mastautin : " + tempohMastautin);
                LOG.info("no pengenalan : " + pemohon.getPihak().getNoPengenalan());
                LOG.info("no hp : " + pemohon.getPihak().getNoTelefonBimbit());
                LOG.info("pekerjaan : " + pemohon.getPekerjaan());
                LOG.info("pendapatan : " + pendapatan);
                LOG.info("tempat lahir :" + tempatLahir);
                LOG.info("umur : " + umur);

                service.addIndividual(alamatPjabt, alamat, anak, pemohon.getPihak().getEmail(), bangsa, jantina, warganegara,
                        tempohMastautin, pemohon.getPihak().getNama(), pemohon.getPihak().getNoPengenalan(), pemohon.getPihak().getNoTelefonBimbit(),
                        pemohon.getPekerjaan(), pendapatan, tempatLahir, umur);

            }
        }

        //Set Maklumat Hakmilik
        for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
            LOG.info("Hakmilik : " + hakmilikPermohonan.getHakmilik().getIdHakmilik());
            service.addLotInformation(hakmilikPermohonan.getHakmilik().getKodHakmilik().getKod(), hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getKod() + "", hakmilikPermohonan.getHakmilik().getNoHakmilik(),
                    hakmilikPermohonan.getHakmilik().getNoLot(), "");

            LOG.info("Add land application.....");

            if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                LOG.info("::KATEGORI : REJECT_APPEAL");
                service.addLandApplication("REJECT_APPEAL", hakmilikPermohonan.getHakmilik().getKategoriTanah().getKod());
            } else {
                LOG.info("::KATEGORI : APPEAL_FOREIGNER");
                service.addLandApplication("APPEAL_FOREIGNER", hakmilikPermohonan.getHakmilik().getKategoriTanah().getKod());
            }

            if (hakmilikPermohonan.getSenaraiLaporanTanah().isEmpty()) {

                String ekar = "";
                String hektar = "";
                String kaki = "";
                String meter = "";

                if (hakmilikPermohonan.getHakmilik().getKodUnitLuas() == null || hakmilikPermohonan.getHakmilik().getLuas() == null) {

                    ekar = "0.00";
                    hektar = "0.00";
                    kaki = "0.00";
                    meter = "0.00";

                } else {

                    if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("A")) {
                        ekar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                        hektar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("F")) {
                        kaki = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                        meter = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    }
                }

                LOG.info("Add land detail application.....");
                service.addLandDetails("Tiada Data", "Tiada Data", hakmilikPermohonan.getJarakDari(), hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama(), "Tiada Data", hakmilikPermohonan.getLokasi(), ekar, hektar, kaki, meter, hakmilikPermohonan.getCatatan(), "Tiada Data", "Tiada Data", hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama(), "Tiada Data", "Tiada Data", "Tiada Data", "Tiada Data");
            } else {
                LaporanTanah laporanTanah = hakmilikPermohonan.getSenaraiLaporanTanah().get(0);

                String ekar = "";
                String hektar = "";
                String kaki = "";
                String meter = "";

                if (hakmilikPermohonan.getHakmilik().getKodUnitLuas() == null || hakmilikPermohonan.getHakmilik().getLuas() == null) {

                    ekar = "0.00";
                    hektar = "0.00";
                    kaki = "0.00";
                    meter = "0.00";
                    
                } else {
                    if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("A")) {
                        ekar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("H")) {
                        hektar = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("F")) {
                        kaki = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    } else if (hakmilikPermohonan.getHakmilik().getKodUnitLuas().getKod().equals("M")) {
                        meter = hakmilikPermohonan.getHakmilik().getLuas().toString();
                    }
                }

                LOG.info("Add land detail application.....");
                service.addLandDetails(laporanTanah.getSempadanBaratNoLot(), laporanTanah.getSempadanBaratKegunaan(), hakmilikPermohonan.getJarakDari(), hakmilikPermohonan.getHakmilik().getKategoriTanah().getNama(), laporanTanah.getKeadaanTanah(), hakmilikPermohonan.getLokasi(), ekar, hektar, kaki, meter, hakmilikPermohonan.getCatatan(), laporanTanah.getSempadanSelatanNoLot(), laporanTanah.getSempadanSelatanKegunaan(), hakmilikPermohonan.getHakmilik().getKodStatusHakmilik().getNama(), laporanTanah.getSempadanTimurNoLot(), laporanTanah.getSempadanTimurKegunaan(), laporanTanah.getSempadanUtaraNoLot(), laporanTanah.getSempadanUtaraKegunaan());
            }
        }

        //Set Tajuk
        String tajuk = "";

        //senario kurang bayaran
        PermohonanUrusan permohonanUrusan = conService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "TAJUK RAYUAN");

        if (permohonanUrusan == null) {
            LOG.info("Using tajuk.." + permohonan.getIdPermohonan());
            permohonanUrusan = conService.findMohonUrusanByPerihal(permohonan.getIdPermohonan(), "TAJUK");
        } else {
            LOG.info("Using tajuk rayuan.." + permohonan.getIdPermohonan());
        }

        if (permohonanUrusan != null) {
            tajuk = permohonanUrusan.getCatatan();
        }

        tajuk = tajuk.toUpperCase();
        LOG.info("Tajuk : " + tajuk);

        String sks = "";
        if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen() != null) {
            sks = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen().getNama();
        }

        /**
         * New service for sending file
         *
         * @Afham
         */
        pengguna = context.getPengguna();
        String kodNegeri = conf.getProperty("kodNegeri");
        FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
        byte[] reportMMKN = null;
        byte[] reportRingkasanMMKN = null;
        if (permohonan != null) {
            String gen1 = null;
            String code1 = null;
            String gen2 = null;
            String code2 = null;
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen d = null;
            KodDokumen kd = null;

            if (permohonan.getKodUrusan().getKod().equals("PMMK1")) {
                gen1 = "CONS_Risalat_MMK1.rdf";
                code1 = "RIS";
                gen2 = "CONS_Ringkas_MMK1.rdf";
                code2 = "RMN";
            } else if (permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                gen1 = "CONS_Risalat_MMK1.rdf";
                code1 = "RIS";
                gen2 = "CONS_Ringkas_MMK2.rdf";
                code2 = "RMN";
            } else if (permohonan.getKodUrusan().getKod().equals("RMJTL")) {
                gen1 = "CONS_Rayuan_TanahLadang_MLK.rdf";
                code1 = "RIS";
                gen2 = "CONS_KrtsRgks_JTKL_MLK.rdf";
                code2 = "RMN";

            }

            reportMMKN = reportUtilMMKN.generateReport(gen1, params, values, null, pengguna);
            reportRingkasanMMKN = reportUtilMMKN.generateReport(gen2, params, values, null, pengguna);

            if (reportMMKN != null && reportRingkasanMMKN != null) {
                LOG.info("Byte Array of Risalat MMKN=" + reportMMKN);
                LOG.info("Byte Array of Ringkasan MMKN=" + reportRingkasanMMKN);

//                service.addRisalat(permohonanKertas.getInfoAudit().getTarikhMasuk(), new java.util.Date(), service.createFile(reportMMKN, "Risalat MMKN"), service.createFile(reportRingkasanMMKN, "Ringkasan MMKN"));

            }
        }

        LOG.info("Create risalat.....");
        service.createRisalat(permohonan.getKodUrusan().getRujukanKanun(), permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama(), permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getKod(),
                permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), sks,
                tajuk);

        LOG.info("Send data.....");
        result = service.sendData();

        if (!"SUCCESS".equals(result)) {
            context.addMessage(" Penghantaran ke e-MMKN tidak berjaya, " + "Status = " + result);
            context.addMessage("Message : " + service.getStatusMessage());
            return null;
        } else {
            context.addMessage(" Penghantaran ke e-MMKN berjaya, " + "Status = " + result);
        }

        LOG.info("End Integration EMMKN....");
        return proposedOutcome;
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
        return "back";
    }
}
