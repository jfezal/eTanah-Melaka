/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.common.IntegrasiPendaftaranService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTEndorsanPermohonanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(PLPTEndorsanPermohonanValidator.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    IntegrasiPendaftaranService integrasiPendaftaranService;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    CommonService comm;

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
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        String stage = "";
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (kodNegeri.equals("04")) {
            if (mohon.getKodUrusan().getKod().equals("PLPT")) {
                t.add("DISSUT_MLK_PLPT.rdf");
                t2.add("SUT");
            } else {
                t.add("DISSUT_MLK.rdf");
                t2.add("SUT");
            }
            comm.reportGen(mohon, t, t2);

        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        try {
            Permohonan permohonan = context.getPermohonan();
            KodUrusan kod = kodUrusanDAO.findById("PSPM");
            integrasiPendaftaranService.setPengguna(context.getPengguna());
            Permohonan mohonBaru = integrasiPendaftaranService.initiate(permohonan, kod, context.getStageName());
            integrasiPendaftaranService.hantarNotifikasi("Kemasukan " + mohonBaru.getKodUrusan().getNama() + " :" + mohonBaru.getIdPermohonan(), "Urusan permohonan dari Unit Pembangunan untuk Id Permohonan :" + permohonan.getIdPermohonan() + " ke unit pendaftaran pada perserahan :" + mohonBaru.getIdPermohonan(), mohonBaru.getCawangan(), "ptptgregistration");
            return proposedOutcome;
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanPermohonanValidator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanPermohonanValidator.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    @Override
    public void afterPushBack(StageContext context) {
    }
}
