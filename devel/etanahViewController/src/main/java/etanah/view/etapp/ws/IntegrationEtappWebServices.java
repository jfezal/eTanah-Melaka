/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.etapp.ws;

import com.google.inject.Injector;
import com.wideplay.warp.persist.Transactional;
import etanah.Configuration;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodPenyerah;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdPermohonan;
import etanah.view.etanahContextListener;
import etanah.view.etapp.htp.ws.HTPservice;
import etanah.view.etapp.ws.form.HakmilikPermohonanMyEtaPP;
import etanah.view.etapp.ws.form.LampiranMyEtaPP;
import etanah.view.etapp.ws.form.MaklumatHakmilikMyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek4MyEtaPP;
import etanah.view.etapp.ws.form.MaklumatPermohonanSek8MyEtaPP;
import etanah.view.etapp.ws.json.Sek4FirstStage;
import etanah.view.etapp.ws.json.Sek4SecondStage;
import etanah.view.etapp.ws.json.Sek8FirstStage;
import etanah.view.etapp.ws.json.Sek8FourthStage;
import etanah.view.etapp.ws.json.Sek8SecondStage;
import etanah.view.etapp.ws.json.Sek8SeventhStage;
import etanah.view.etapp.ws.json.Sek8SixthStage;
import etanah.view.etapp.ws.json.Sek8ThirdStage;
import etanah.view.etapp.ws.json.Sek8fifthStage;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author faidzal
 */
public class IntegrationEtappWebServices implements IntegrationEtappServices {

    private static Injector injector = etanahContextListener.getInjector();
    private GeneratorIdPermohonan idGenerator = injector.getInstance(GeneratorIdPermohonan.class);
    private etanah.Configuration conf = injector.getInstance(Configuration.class);
    KodUrusanDAO kodUrusanDAO = injector.getInstance(KodUrusanDAO.class);
    HakmilikDAO hakmilikDAO = injector.getInstance(HakmilikDAO.class);
    PermohonanDAO permohonanDAO = injector.getInstance(PermohonanDAO.class);
    KodNegeriDAO kodNegeriDAO = injector.getInstance(KodNegeriDAO.class);
    KodPenyerahDAO kodPenyerahDAO = injector.getInstance(KodPenyerahDAO.class);
    PenggunaDAO penggunaDAO = injector.getInstance(PenggunaDAO.class);
    KodCawanganDAO kodCawanganDAO = injector.getInstance(KodCawanganDAO.class);
    PesakaService pesakaService = injector.getInstance(PesakaService.class);
    PengambilanService pengambilanService = injector.getInstance(PengambilanService.class);
    HTPservice hTPservice = injector.getInstance(HTPservice.class);
    EtappLogService logservice = injector.getInstance(EtappLogService.class);
    MyEtappPengambilanServices myEtappPengambilanServices = injector.getInstance(MyEtappPengambilanServices.class);

    @Override
    public MaklumatPermohonan generatePermohonan(String kodUrusan, String nama,
            String alamat1,
            String alamat2,
            String alamat3,
            String alamat4,
            String kodNegeriP,
            String poskod, String idHakmilik, String kodNegeri) throws RuntimeException {
        MaklumatPermohonan mp = new MaklumatPermohonan();
        KodCawangan kodCawangan = kodCawanganDAO.findById("00");
        KodUrusan kodUrusan1 = kodUrusanDAO.findById(kodUrusan);
        KodPenyerah kodPenyerah = kodPenyerahDAO.findById("00");
        Permohonan mohon = new Permohonan();
        if (kodUrusan1 == null) {
            throw new RuntimeException("KodUrusan tidak dikenali");
        }
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilik);
        if (hakmilik == null) {
            throw new RuntimeException("Hakmilik tidak dikenali");
        }
        Pengguna pengguna = penggunaDAO.findById("admin");
        if (pengguna == null) {
            throw new RuntimeException("Pengguna tidak wujud");
        }
        String idPermohonan = idGenerator.generate(kodNegeri, kodCawangan, kodUrusan1);
        mohon.setCawangan(kodCawangan);
        mohon.setKodUrusan(kodUrusan1);
        mohon.setIdPermohonan(idPermohonan);
        mohon.setPenyerahNama(nama);
        mohon.setPenyerahAlamat1(alamat1);
        mohon.setPenyerahAlamat2(alamat2);
        mohon.setPenyerahAlamat3(alamat3);
        mohon.setPenyerahAlamat4(alamat4);
        mohon.setPenyerahPoskod(poskod);
        mohon.setPenyerahNegeri(kodNegeriDAO.findById(kodNegeriP));
        mohon.setKodPenyerah(kodPenyerah);
        mohon.setInfoAudit(setIA(pengguna));
        savePermohonan(mohon);

        mp.setIdPermohonan(idPermohonan);
        return mp;
    }

    @Transactional
    public void savePermohonan(Permohonan p) {
        permohonanDAO.saveOrUpdate(p);

    }

    public InfoAudit setIA(Pengguna p) {
        InfoAudit ia = new InfoAudit();
        ia.setTarikhMasuk(new Date());
        ia.setDimasukOleh(p);
        return ia;
    }

    @Override
    public MaklumatHakmilikMyEtaPP maklumatHakmilik(String noResit, String idHakmilik) {
        MaklumatHakmilikMyEtaPP ha = new MaklumatHakmilikMyEtaPP();
        try {

            ha = pesakaService.getMaklumatAsasHakmilik(noResit, idHakmilik);
            logservice.insertLog("ACQ", "1", "Hantar maklumat hakmilik = " + idHakmilik);
        } catch (Exception ex) {
            logservice.insertLog("ACQ", "2", "Hantar maklumat hakmilik = " + idHakmilik);
        }
        return ha;
    }

    @Override
    public String daftarPermohonanMyEtaPPBorangAMMk(MaklumatPermohonanSek4MyEtaPP maklumatPermohonanSek4Form, List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm, LampiranMyEtaPP mmkPaper, List<LampiranMyEtaPP> attachment) {
        String idPermohonan = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek4FirstStage json = new Sek4FirstStage();
            json.setAttachment(attachment);
            json.setListHakmilikPermohonanMyEtaPPForm(listHakmilikPermohonanMyEtaPPForm);
            json.setMaklumatPermohonanSek4Form(maklumatPermohonanSek4Form);
            json.setMmkPaper(mmkPaper);
            jsonStr = Obj.writeValueAsString(json);
//            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.INFO, jsonStr);
            //bpel to initiate : http://xmlns.oracle.com/MyEtappSek4/MyEtappSek4_1/agihan_tugas
            idPermohonan = myEtappPengambilanServices.daftarPermohonanSek4BorangA(maklumatPermohonanSek4Form, listHakmilikPermohonanMyEtaPPForm, mmkPaper, attachment);
            logservice.insertLog("ACQ", "1", "Sek4 : daftarPermohonanMyEtaPPBorangAMMk = " + idPermohonan, jsonStr);

        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek4 : daftarPermohonanMyEtaPPBorangAMMk = " + idPermohonan, jsonStr);
        }
        return idPermohonan;
    }

    @Override
    public String maklumatWartaBorangBMyEtaPP(String idPermohonan, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attachment) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek4SecondStage json = new Sek4SecondStage();
            json.setAttachment(attachment);
            json.setIdPermohonan(idPermohonan);
            json.setNoWarta(noWarta);
            json.setTarikhWarta(tarikhWarta);
            jsonStr = Obj.writeValueAsString(json);
            //http://xmlns.oracle.com/MyEtappSek4/MyEtappSek4_3/terima_warta_borang_b
            myEtappPengambilanServices.maklumatWartaBorangB(idPermohonan, tarikhWarta, noWarta, attachment);
            logservice.insertLog("ACQ", "1", "Sek4 : maklumatWartaBorangBMyEtaPP = " + idPermohonan, jsonStr);

            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek4 : maklumatWartaBorangBMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";
        }
        return status;
    }

    @Override
    public String daftarPermohonanSek8MyEtaPP(MaklumatPermohonanSek8MyEtaPP maklumatPermohonan, List<HakmilikPermohonanMyEtaPP> listHakmilikPermohonanMyEtaPPForm, LampiranMyEtaPP digitalcharting, List<LampiranMyEtaPP> attachment) {
        String idPermohonan = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8FirstStage json = new Sek8FirstStage();
            json.setAttachment(attachment);
            json.setDigitalcharting(digitalcharting);
            json.setListHakmilikPermohonanMyEtaPPForm(listHakmilikPermohonanMyEtaPPForm);
            json.setMaklumatPermohonan(maklumatPermohonan);
            jsonStr = Obj.writeValueAsString(json);
            idPermohonan = myEtappPengambilanServices.daftarPermohonanSek8(maklumatPermohonan, listHakmilikPermohonanMyEtaPPForm, digitalcharting, attachment);
            logservice.insertLog("ACQ", "1", "Sek8 : daftarPermohonanSek8MyEtaPP = " + idPermohonan, jsonStr);
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : daftarPermohonanSek8MyEtaPP = " + idPermohonan, jsonStr);
        }
        return idPermohonan;
    }

    @Override
    public String borangCdanMMKMyEtaPP(String idPermohonan, LampiranMyEtaPP mmkPaper, List<LampiranMyEtaPP> attchment) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8SecondStage json = new Sek8SecondStage();
            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setMmkPaper(mmkPaper);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.borangCdanMMK(idPermohonan, mmkPaper, attchment);
            logservice.insertLog("ACQ", "1", "Sek8 : borangCdanMMKMyEtaPP = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : borangCdanMMKMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";
        }
        return status;
    }

    @Override
    public String endorsBorangDMaklumatWartaMyEtaPP(String idPermohonan, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attchment) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8ThirdStage json = new Sek8ThirdStage();
            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setNoWarta(noWarta);
            json.setTarikhWarta(tarikhWarta);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.endorsBorangDMaklumatWarta(idPermohonan, tarikhWarta, noWarta, attchment);
            logservice.insertLog("ACQ", "1", "Sek8 : endorsBorangDMaklumatWartaMyEtaPP = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : endorsBorangDMaklumatWartaMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";
        }
        return status;
    }

    @Override
    public String endorsBorangKMyEtaPP(String idPermohonan, String idHakmilik, String tarikhWarta, String noWarta, List<LampiranMyEtaPP> attchment) {
        String status = null;
        String jsonStr = null;

        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8FourthStage json = new Sek8FourthStage();
            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setNoWarta(noWarta);
            json.setTarikhWarta(tarikhWarta);
            json.setIdHakmilik(idHakmilik);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.endorsBorangK(idPermohonan, idHakmilik, tarikhWarta, noWarta, attchment);
            logservice.insertLog("ACQ", "1", "Sek8 : endorsBorangKMyEtaPP = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : endorsBorangKMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";

        }
        return status;
    }

    @Override
    public String sijilPembebasanUkurMyEtaPP(String idPermohonan, String tarikh, String noPU, List<LampiranMyEtaPP> attchment) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8fifthStage json = new Sek8fifthStage();
            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setNoPU(noPU);
            json.setTarikh(tarikh);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.sijilPembebasanUkur(idPermohonan, tarikh, noPU, attchment);
            logservice.insertLog("ACQ", "1", "Sek8 : sijilPembebasanUkurMyEtaPP = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : sijilPembebasanUkurMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";

        }
        return status;
    }

    @Override
    public String maklumanPenghantaranPUMyEtaPP(String idPermohonan, String tarikhHantar, String noRujukan, List<LampiranMyEtaPP> attchment) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8SixthStage json = new Sek8SixthStage();
            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setNoRujukan(noRujukan);
            json.setTarikhHantar(tarikhHantar);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.maklumanHantarPU(idPermohonan, tarikhHantar, noRujukan, attchment);

            logservice.insertLog("ACQ", "1", "Sek8 : maklumanPenghantaranPUMyEtaPP = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : maklumanPenghantaranPUMyEtaPP = " + idPermohonan, jsonStr);
            status = "failed";

        }
        return status;
    }

    @Override
    public String hakmilikSambungan(String idPermohonan, String idHakmilik, List<HakmilikSambunganFormEtapp> senaraiHakmilikSambungan) {
        String status = null;
        String jsonStr = null;
        try {
            ObjectMapper Obj = new ObjectMapper();
            Sek8SeventhStage json = new Sek8SeventhStage();
//            json.setAttchment(attchment);
            json.setIdPermohonan(idPermohonan);
            json.setIdHakmilik(idHakmilik);
            json.setSenaraiHakmilikSambungan(senaraiHakmilikSambungan);
            jsonStr = Obj.writeValueAsString(json);
            myEtappPengambilanServices.hakmilikSambunganEtapp(idPermohonan, idHakmilik, senaraiHakmilikSambungan);

            logservice.insertLog("ACQ", "1", "Sek8 : hakmilikSambungan = " + idPermohonan, jsonStr);
            status = "success";
        } catch (Exception ex) {
            Logger.getLogger(IntegrationEtappWebServices.class.getName()).log(Level.SEVERE, null, ex);
            logservice.insertLog("ACQ", "2", "Sek8 : hakmilikSambungan = " + idPermohonan, jsonStr);
            status = "failed";

        }
        return status;
    }
}
