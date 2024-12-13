/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodStatusTransaksiKewanganDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.dao.TransaksiDAO;
import etanah.model.Akaun;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodStatusTransaksiKewangan;
import etanah.model.KodTransaksi;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.model.Transaksi;
import etanah.model.TransaksiHadapan;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;
/**
 *
 * @author abu.mansur
 */
public class ProsesRemisyen implements StageListener {

    private static final Logger LOG = Logger.getLogger(ProsesRemisyen.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();


    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    TransaksiDAO transaksiDAO;

    @Inject
    AkaunDAO akaunDAO;

    @Inject
    RemisyenManager manager;

    @Inject
    KodStatusTransaksiKewanganDAO kodStatusTransaksiKewanganDAO;

    @Inject
    private  etanah.Configuration conf;

    @Inject
    private  etanah.kodHasilConfig khconf;

    @Inject
    KodUrusanDAO kodUrusanDAO;

    @Inject
    DokumenService dokumenService;

    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;

    @Inject
    SemakDokumenService semakDokumenService;

    @Inject
    KandunganFolderService kandunganFolderService;

    @Inject
    FolderDokumenDAO folderDokumenDAO;

    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;

    @Inject
    KodDokumenDAO kodDokumenDAO;

    @Inject
    ReportUtil reportUtil;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @Override
    public boolean beforeStart(StageContext ctx) {
        return true;
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {
        LOG.info("beforeComplete:start");
        String Lulus = "L";
        String rtnMsg = "";
        Pengguna pengguna = ctx.getPengguna();
        Permohonan permohonan = ctx.getPermohonan();
        if(Lulus.equals(proposedOutcome)){
            KodUrusan u = permohonan.getKodUrusan();
            if(("04".equals(conf.getProperty("kodNegeri")) && "REMTS".equals(u.getKod())) && permohonan.getNilaiKeputusan() == null){
                ctx.addMessage("Sila masukkan Tempoh pada Maklumat Tanam Semula : "+permohonan.getIdPermohonan());
                return null;
            }else if(("04".equals(conf.getProperty("kodNegeri")) && "REMRI".equals(u.getKod())) && permohonan.getNilaiKeputusan() == null){
                ctx.addMessage("Sila masukkan maklumat pada Keputusan MMK : "+permohonan.getIdPermohonan());
                return null;
            }else{
                Hakmilik h  = permohonan.getSenaraiHakmilik().get(0).getHakmilik();
                Akaun akaunAC = h.getAkaunCukai();
                Akaun akaunAR = null;
                String[] tname = {"kodAkaun.kod"};
                Object[] tvalue = {khconf.getProperty("akaunRemisyen")};
//                for (Akaun ak : akaunDAO.findAll()) {
                for (Akaun ak : akaunDAO.findByEqualCriterias(tname, tvalue, null)) {
                    if(ak.getKodAkaun().getKod().equals(khconf.getProperty("akaunRemisyen")) && ak.getCawangan().getKod().equals(h.getCawangan().getKod())){
                        akaunAR = ak;
                    }
                }

                Transaksi t = new Transaksi();
                KodTransaksi kt = new KodTransaksi();
                KodStatusTransaksiKewangan status = kodStatusTransaksiKewanganDAO.findById('A');
                ProsesRemisyenCalc prc = new ProsesRemisyenCalc();

                // determine the amount of remission
                BigDecimal remisyen = null;
                if ("REMTS".equals(u.getKod())){
    //                BigDecimal cukai = h.getCukai();
    //                remisyen = cukai.divide(new BigDecimal(2));
                    remisyen = prc.calc(u.getKod(), h.getKodHakmilik().getKod(), h.getCukaiSebenar(), h.getLuas(), h.getKodUnitLuas().getKod());
//                    kt.setKod("99001");
                    kt.setKod(khconf.getProperty("remisyen.remts"));
                    t.setKodTransaksi(kt);
                    //set amout of credit account to round_up
    //                BigDecimal rounded = remisyen.setScale(0, BigDecimal.ROUND_UP);
                    BigDecimal rounded_down = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                    if(!"04".equals(conf.getProperty("kodNegeri"))){ // exept for negeri melaka n REMTS coz REMTS effected next year after apply
                        akaunAC.setBaki(akaunAC.getBaki().subtract(rounded_down));
                        //set amout of debit account to round_down
        //                BigDecimal rounded_down = remisyen.setScale(0, BigDecimal.ROUND_DOWN);
                        akaunAR.setBaki(akaunAR.getBaki().add(rounded_down));
                    }
                    remisyen = rounded_down; //reset amount of remisyen for Transaksi object to round_down value
                }else if("REMSB".equals(u.getKod())){
                    remisyen = prc.calc(u.getKod(), h.getKodHakmilik().getKod(), h.getCukaiSebenar(), h.getLuas(), h.getKodUnitLuas().getKod());
//                    kt.setKod("99000");
                    kt.setKod(khconf.getProperty("remisyen.remsb"));
                    t.setKodTransaksi(kt);
                    akaunAR.setBaki(akaunAR.getBaki().add(remisyen));
                    akaunAC.setBaki(akaunAC.getBaki().subtract(remisyen));
                    BigDecimal remsb_value = new BigDecimal(0.0);
                    if("04".equals(conf.getProperty("kodNegeri"))){
                        //negeri melaka
                        remsb_value = new BigDecimal(1.0);
                        u = kodUrusanDAO.findById("PCT");
                        
                    }
                    if("05".equals(conf.getProperty("kodNegeri"))){
                        //negeri Sembilan
                        remsb_value = new BigDecimal(1.0);
                        u = kodUrusanDAO.findById("KCL");//kod urusan for Kelulusan Pengurangan Cukai under module Pendaftaran
                        if(manager.updateAndCreateIdPermohonan(remsb_value, u, pengguna, permohonan))
                            LOG.debug("REMSB: success");
                        else
                            LOG.debug("REMSB: FAIL");
                    }
                        
                    
                }else if("REMTD".equals(u.getKod())){
//                    remisyen = prc.calc(u.getKod(), h.getKodHakmilik().getKod(), h.getCukaiSebenar(), h.getLuas(), h.getKodUnitLuas().getKod());
                    remisyen = prc.calc(u.getKod(), h.getKodHakmilik().getKod(), permohonan.getNilaiKeputusan(), h.getLuas(), h.getKodUnitLuas().getKod()); // pekeliling baru
//                    kt.setKod("99003");
                    kt.setKod(khconf.getProperty("remisyen.remtd"));
                    t.setKodTransaksi(kt);
                    akaunAR.setBaki(akaunAR.getBaki().add(remisyen));
                    akaunAC.setBaki(akaunAC.getBaki().subtract(remisyen));
                }else if("REMRI".equals(u.getKod())){ //for kod "REMRI"
                    remisyen = prc.calc(u.getKod(), h.getKodHakmilik().getKod(), h.getCukaiSebenar(), h.getLuas(), h.getKodUnitLuas().getKod());
                    if("04".equals(conf.getProperty("kodNegeri"))){
                        remisyen = h.getCukaiSebenar().subtract(permohonan.getNilaiKeputusan());
                    }
//                    kt.setKod("99002");
                    kt.setKod(khconf.getProperty("remisyen.remri"));
                    t.setKodTransaksi(kt);
                    akaunAR.setBaki(akaunAR.getBaki().add(remisyen));
                    akaunAC.setBaki(akaunAC.getBaki().subtract(remisyen));
                    if("04".equals(conf.getProperty("kodNegeri"))){
                        //negeri melaka
                        KodUrusan uDaftar = kodUrusanDAO.findById("KCL");//kod urusan for Kelulusan Pengurangan Cukai under module Pendaftaran
                        if(manager.updateAndCreateIdPermohonan(permohonan.getNilaiKeputusan(), uDaftar, pengguna, permohonan))
                            LOG.debug("REMRI: success");
                        else
                            LOG.debug("REMRI: FAIL");
                    }
                }
                t.setAmaun(remisyen);
                t.setCawangan(h.getCawangan());
                t.setPermohonan(permohonan);
                t.setStatus(status);
                t.setAkaunDebit(akaunAR);
                t.setAkaunKredit(akaunAC);
                t.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date())));
                t.setTahunKewangan(Integer.parseInt(sdf.format(new java.util.Date())));
    //            t.setInfoAudit(info);

                // begin txn
    //            Transaksi
                // commit
                // error - rollback
                if ( "REMTS".equals(u.getKod()) && "04".equals(conf.getProperty("kodNegeri")) ){
                    LOG.info("REMTS utk melaka sah bagi tahun depan.");
                }else{
                    manager.saveAndUpdate(t,null,akaunAR,null,null,akaunAC,pengguna);
                }
                // utk simpan dlm table Transaksi Hadapan jikalau ada nilai
                if("REMTS".equals(u.getKod()) && permohonan.getNilaiKeputusan() != null){
                    kt.setKod(khconf.getProperty("remisyen.remts"));
                    for(int i = 1; i <= permohonan.getNilaiKeputusan().intValue(); i++){
//                        TransaksiHadapan th = new TransaksiHadapan();
//                        th.setPermohonan(t.getPermohonan());
//                        th.setStatus(t.getStatus());
//                        th.setTransaksiPos(t);
//                        LOG.debug("t.getUntukTahun()+i :"+t.getUntukTahun()+i);
//                        th.setUntukTahun(t.getUntukTahun()+i);
//                        th.setKodTransaksi(t.getKodTransaksi());
//                        th.setInfoAudit(t.getInfoAudit());
//                        th.setCawangan(t.getCawangan());
//                        th.setCatatan(permohonan.getKodUrusan().getNama());
//                        th.setAmaun(t.getAmaun());
//                        th.setAkaunKredit(t.getAkaunKredit());
//                        th.setAkaunDebit(t.getAkaunDebit());
//                        manager.saveTransaksiHadapan(th, pengguna);
//                        LOG.debug("idTransaksiHadapan :"+th.getIdTransaksi());
//                        LOG.info("status :"+status.getKod());
//                        LOG.info("kt :"+kt.getKod());
//                        LOG.info("remisyen :"+remisyen);
                        TransaksiHadapan th = new TransaksiHadapan();
                        th.setPermohonan(permohonan);
                        th.setStatus(status);
//                        th.setTransaksiPos(t);
                        LOG.debug("now.getFullYear()+i :"+Integer.parseInt(sdf.format(new java.util.Date()))+i);
                        th.setUntukTahun(Integer.parseInt(sdf.format(new java.util.Date()))+i);
                        th.setKodTransaksi(kt);
                        th.setCawangan(h.getCawangan());
                        th.setCatatan(permohonan.getKodUrusan().getNama());
                        th.setAmaun(remisyen);
                        th.setAkaunKredit(akaunAC);
                        th.setAkaunDebit(akaunAR);
                        manager.saveTransaksiHadapan(th, pengguna);
                        LOG.debug("idTransaksiHadapan :"+th.getIdTransaksi());
                    }
                }
                if("05".equals(conf.getProperty("kodNegeri"))){ //negeri sembilan
                    ctx.addMessage("Remisyen sebanyak RM " + remisyen.setScale(0, BigDecimal.ROUND_UP) + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                    rtnMsg = "Remisyen sebanyak RM " + remisyen.setScale(0, BigDecimal.ROUND_UP) + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun();
                    LOG.info("Remisyen sebanyak RM " + remisyen.setScale(0, BigDecimal.ROUND_UP) + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                }else if("04".equals(conf.getProperty("kodNegeri"))){ //negeri melaka
                    BigDecimal valueMsg = new BigDecimal(0);
                    if("REMRI".equals(u.getKod()))
                        valueMsg = remisyen;
                    else
                        valueMsg = remisyen.setScale(0, BigDecimal.ROUND_UP);
                    ctx.addMessage("Pindaan Cukai Tanah sebanyak RM " + valueMsg + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                    rtnMsg = "Pindaan Cukai Tanah sebanyak RM " + valueMsg + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun();
                    LOG.info("Pindaan Cukai Tanah sebanyak RM " + valueMsg + " telah dikemaskini ke dalam Akaun " + akaunAC.getNoAkaun());
                }
    //            proposedOutcome = null; // add for testing to make sure tugasan not passing
            }
        }else{
            LOG.debug("Keputusan :"+proposedOutcome);
        }

        return proposedOutcome;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        if("10".equals(conf.getProperty("kodNegeri"))){ //negeri selain melaka dan N9 // 10 = just comment only
            Pengguna pengguna = context.getPengguna();
            Permohonan permohonan = context.getPermohonan();
            HakmilikPermohonan hakmilikP = permohonan.getSenaraiHakmilik().get(0);  //assume 1 permohonan = 1 hakmilik
            Hakmilik hakmilik = hakmilikP.getHakmilik();
            String dokumenPath = conf.getProperty("document.path");
            Dokumen e = null;
            String path = null;
            String[] params = null;
            String[] values = null;
            String reportName = null;
            KodDokumen kd = new KodDokumen();            
            if("REMSB".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = REMSB (Remisyen Sekolah Bantuan Modal)
                if("GRN".equals(hakmilik.getKodHakmilik().getKod()) || "PN".equals(hakmilik.getKodHakmilik().getKod()) || "HSM".equals(hakmilik.getKodHakmilik().getKod())){
                    //hakmilik pendaftar
                    reportName = "HSLSuratPindaanCukaiSekolahBantuanModal.rdf";
                    kd.setKod("SPCT");
                }else{
                    //bukan hakmilik pendaftar
                    reportName = "HSLMemoPindaanCukaiSekolahBantuanModal.rdf";
                    kd.setKod("MPCT");
                }
            }else if("REMRI".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = REMRI (Remisyen Rumah Ibadat){
                if("GRN".equals(hakmilik.getKodHakmilik().getKod()) || "PN".equals(hakmilik.getKodHakmilik().getKod()) || "HSM".equals(hakmilik.getKodHakmilik().getKod())){
                    //hakmilik pendaftar
                    reportName = "HSLMemoPindaanCukaiRumahIbadat.rdf";
                    kd.setKod("MPCT");
                }else{
                    //bukan hakmilik pendaftar
                    reportName = "HSLSuratPindaanCukaiRumahIbadat.rdf";
                    kd.setKod("SPCT");
                }
            }

            params = new String[]{"p_id_mohon"};
            values = new String[]{permohonan.getIdPermohonan()};

            FolderDokumen fd = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            e = saveOrUpdateDokumen(fd, kd, hakmilikP.getHakmilik().getIdHakmilik(), pengguna);
            path = permohonan.getFolderDokumen().getFolderId() + File.separator + String.valueOf(e.getIdDokumen());
            reportUtil.generateReport(reportName, params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), e.getIdDokumen());
        }
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id, Pengguna peng) {
        InfoAudit ia = new InfoAudit();
        KodKlasifikasi kodKlas = kodKlasifikasiDAO.findById(1); //kod klasifikasi : 1 = Am, 2 = Terhad, 3 = Sulit, 4 = Rahsia, 5 = Rahsia Besar
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("doc null");
            doc = new Dokumen();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
        } else {
            LOG.debug("doc :"+doc.getIdDokumen());
            ia.setDimasukOleh(doc.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(doc.getInfoAudit().getTarikhMasuk());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
        }
        doc.setKlasifikasi(kodKlas);
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        //TODO : increase versi if regenarate report
        doc.setNoVersi("1.0");
        doc.setTajuk(kd.getKod());
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
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

     @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

}
