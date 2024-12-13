/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikAlamatDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanAktivitiDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAlamat;
import etanah.model.Pemohon;
import etanah.model.Permohonan;
import etanah.model.PermohonanAktiviti;
import etanah.model.PermohonanRujukanLuar;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
public class RemisyenValidation implements StageListener {
    private static final Logger LOG = Logger.getLogger(RemisyenValidation.class);

    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    HakmilikAlamatDAO hakmilikAlamatDAO;

    @Inject
    PemohonDAO pemohonDAO;

    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;

    @Inject
    PermohonanAktivitiDAO permohonanAktivitiDAO;

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
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        List<Pemohon> senaraiPemohon = new ArrayList();
        String[] name = {"permohonan"};
        Object[] value= {permohonan};
        senaraiPemohon = pemohonDAO.findByEqualCriterias(name, value, null);
        LOG.debug("senaraiPemohon.size() :"+senaraiPemohon.size());
        if("REMSB".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = REMSB (Remisyen Sekolah Bantuan Modal)
            HakmilikAlamat hakmilikAlamat = hakmilikAlamatDAO.findById(hakmilik.getIdHakmilik());
            List<PermohonanRujukanLuar> senaraiPRL = new ArrayList();
            String[] name2 = {"permohonan"};
            Object[] value2 = {permohonan};
            senaraiPRL = permohonanRujukanLuarDAO.findByEqualCriterias(name2, value2, null);
            LOG.debug("REMSB:senaraiPRL.size() :"+senaraiPRL.size());
            if(hakmilikAlamat == null && senaraiPemohon.isEmpty() && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tambahan, Sokongan dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(hakmilikAlamat == null && senaraiPemohon.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tambahan dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(hakmilikAlamat == null && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tambahan dan Sokongan : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPemohon.isEmpty() && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Sokongan dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(hakmilikAlamat == null){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tambahan : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPemohon.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Sokongan : "+permohonan.getIdPermohonan());
                return null;
            }
        }else if("REMTS".equals(permohonan.getKodUrusan().getKod())){ //validation for kodUrusan = REMTS (Remisyen Tanam Semula)
            PermohonanAktiviti permohonanAktiviti = permohonanAktivitiDAO.findById(permohonan.getIdPermohonan());
            List<PermohonanRujukanLuar> senaraiPRL = new ArrayList();
            String[] name2 = {"permohonan"};
            Object[] value2 = {permohonan};
            senaraiPRL = permohonanRujukanLuarDAO.findByEqualCriterias(name2, value2, null);
            LOG.debug("REMTS:senaraiPRL.size() :"+senaraiPRL.size());
            if(permohonanAktiviti == null && senaraiPemohon.isEmpty() && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tanam Semula, Sokongan dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(permohonanAktiviti == null && senaraiPemohon.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tanam Semula dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(permohonanAktiviti == null && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tanam Semula dan Sokongan : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPemohon.isEmpty() && senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Maklumat Sokongan dan Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }else if(permohonanAktiviti == null){
                context.addMessage("Sila masukkan maklumat pada Maklumat Tanam Semula : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPRL.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Sokongan : "+permohonan.getIdPermohonan());
                return null;
            }else if(senaraiPemohon.isEmpty()){
                context.addMessage("Sila masukkan maklumat pada Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }
        }else{ //validation for kodUrusan = REMTD (Remisyen Tanah Dato' Lembaga) and REMRI (Remisyan Rumah Ibadat)
            if("REMTD".equals(permohonan.getKodUrusan().getKod()) && permohonan.getNilaiKeputusan() == null){
                context.addMessage("Sila masukkan Kadar Pengurangan : "+permohonan.getIdPermohonan());
                return null;
            }
            if(senaraiPemohon.isEmpty()){
                context.addMessage("Sila pilih Tuan Tanah : "+permohonan.getIdPermohonan());
                return null;
            }
        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

     @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
//        return proposedOutcome;
        return "back";
    }

}
