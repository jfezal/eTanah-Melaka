/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.dao.KodUrusanDAO;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.common.IntegrasiPendaftaranService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTEndorsanKelulusanValidator implements StageListener {

    private static final Logger LOG = Logger.getLogger(PLPTEndorsanKelulusanValidator.class);
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    IntegrasiPendaftaranService integrasiPendaftaranService;
    @Inject
    KodUrusanDAO kodUrusanDAO;

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
        try {
            Permohonan permohonan = context.getPermohonan();
            KodUrusan kod = kodUrusanDAO.findById("PSPL");
            Permohonan mohonBaru = integrasiPendaftaranService.initiate(permohonan, kod, context.getStageName());
            integrasiPendaftaranService.hantarNotifikasi("Kemasukan " + mohonBaru.getKodUrusan().getNama() + " :" + mohonBaru.getIdPermohonan(), "Urusan permohonan dari Unit Pembangunan untuk Id Permohonan :" + permohonan.getIdPermohonan() + " ke unit pendaftaran pada perserahan :" + mohonBaru.getIdPermohonan(), mohonBaru.getCawangan(), "ptreg");
        } catch (StaleObjectException ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanKelulusanValidator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PLPTEndorsanKelulusanValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proposedOutcome;
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
