/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.hasil.workflow;

import etanah.emmkn.ws.EMMKNService;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class ProsesIntegrateEmmkn implements StageListener {
    private static final Logger LOG = Logger.getLogger(ProsesIntegrateEmmkn.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

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
        LOG.info("start integration");
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        String result = null;
        String latarBelakang = null;
        String huraianPentadbir = null;
        String syorPentadbir = null;
        String huraianPtg = null;
        String syorPtg = null;
        EMMKNService service = new EMMKNService();

        /* PLEASE FOLLOW THE SEQUENCE WHEN ADDING THE METHODS */

        //Set username and password for web service authentication
        service.setUserAndPassword("wsEtanahUserId", "wsEtanah2010TEST");
        
        //Set Ulasan for Jabatan Teknikal, PTD and PTD
        /*  'T' for ulasan Jabatan Teknikal 
            'D' for ulasan PTD 
            'G' for ulasan PTG */
        List<PermohonanKertasKandungan> senaraiKandungan = permohonan.getSenaraiKertas().get(0).getSenaraiKandungan();
        for (PermohonanKertasKandungan pkk : senaraiKandungan) {
            int caw = Integer.parseInt(pkk.getCawangan().getKod());
            LOG.info("caw :"+caw);
            if(pkk.getBil() == 1)
                latarBelakang = pkk.getKandungan();
            if(pkk.getBil() == 2){
                huraianPentadbir = pkk.getKandungan();
                service.addUlasan("D", caw+"", huraianPentadbir);
            }
            if(pkk.getBil() == 3){
                syorPentadbir = pkk.getKandungan();
                service.addUlasan("D", caw+"", syorPentadbir);
            }
            if(pkk.getBil() == 4){
                huraianPtg = pkk.getKandungan();
                service.addUlasan("G", caw+"", huraianPtg);
            }
            if(pkk.getBil() == 5){
                syorPtg = pkk.getKandungan();
                service.addUlasan("G", caw+"", syorPtg);
            }
        }
        
        //Set maklumat tanggungan for individual applicant // pemohon
        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            String AlamatPejabat = pemohon.getInstitusiAlamat1()+", "+pemohon.getInstitusiAlamat2()+", "+pemohon.getInstitusiAlamat3()+", "
                                  +pemohon.getInstitusiAlamat4()+", "+pemohon.getInstitusiPoskod()+", "+pemohon.getInstitusiNegeri().getNama();
            service.addTanggungan(AlamatPejabat, pemohon.getKaitan(), pemohon.getPihak().getNama(), pemohon.getPihak().getNoPengenalan(),
                pemohon.getPekerjaan(), pemohon.getPendapatan());
        }
        
        //Set individual applicant
        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) { 
            String AlamatRumah = pemohon.getPihak().getAlamat1()+", "+pemohon.getPihak().getAlamat2()+", "
                                +pemohon.getPihak().getAlamat3()+", "+pemohon.getPihak().getAlamat4()+", "
                                +pemohon.getPihak().getPoskod()+", "+pemohon.getPihak().getNegeri().getNama();
            String noTelefon = pemohon.getPihak().getNoTelefon1()+", "+pemohon.getPihak().getNoTelefon2()+", "
                               +pemohon.getPihak().getNoTelefonBimbit();
            String AlamatPejabat = pemohon.getInstitusiAlamat1()+", "+pemohon.getInstitusiAlamat2()+", "+pemohon.getInstitusiAlamat3()+", "
                                  +pemohon.getInstitusiAlamat4()+", "+pemohon.getInstitusiPoskod()+", "+pemohon.getInstitusiNegeri().getNama();
            boolean anakTempatan = false;
            if(pemohon.getPihak().getAnakTempatan() == 'Y')
                anakTempatan = true;
            if(pemohon.getPihak().getBangsa().getKod().equals("T"))
                pemohon.getPihak().getBangsa().setKod("LN");
        service.addIndividual(AlamatPejabat, AlamatRumah, anakTempatan, pemohon.getPihak().getEmail(),
                pemohon.getPihak().getBangsa().getKod(), pemohon.getPihak().getKodJantina(), pemohon.getPihak().getWargaNegara().getKod(),
                pemohon.getTempohMastautin(), pemohon.getPihak().getNama(), pemohon.getPihak().getNoPengenalan(), noTelefon,
                pemohon.getPekerjaan(), pemohon.getPendapatan(), pemohon.getPihak().getTempatLahir(), pemohon.getUmur().toString()); // 45 for umor
        }

        //Set lot information
        HakmilikPermohonan hakmilikPermohonan = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
        service.addLotInformation(hakmilik.getKodHakmilik().getKod(), hakmilik.getBandarPekanMukim().getKod()+"", hakmilik.getNoHakmilik(),
                hakmilik.getNoLot(), "");

        //Set land application
        service.addLandApplication("WORSHIP_HOUSE_REVENUE", hakmilik.getKategoriTanah().getKod());

        //Set land general details
        service.addLandDetails(null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null, null);

        //Set kertas risalat
        String tajuk = permohonan.getKodUrusan().getNama().toUpperCase()+" BAGI HAKMILIK "+hakmilik.getKodHakmilik().getKod()+" "+hakmilik.getNoHakmilik()+" "+
                       hakmilik.getLot().getKod()+" "+hakmilik.getNoLot()+", "+hakmilik.getBandarPekanMukim().getNama()+", DAERAH "+hakmilik.getDaerah().getNama();
        service.createRisalat(permohonan.getKodUrusan().getRujukanKanun(), hakmilik.getBandarPekanMukim().getNama(), hakmilik.getDaerah().getKod(),
                permohonan.getIdPermohonan(), permohonan.getIdPermohonan(), hakmilik.getSeksyen().getNama(),
                tajuk);
        
        result = service.sendData();
        LOG.debug("result :"+result);
        /* result = SUCCESS
         *        = INT_ERR // Internal Error
         *        = VAL_ERR // Variable Error
         *        = AUTH_ERR // Aunthentication Error
         */
        if(!"SUCCESS".equals(result)){
            return null;
        }
//        return null; // for development only
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
        return proposedOutcome;
    }

}
