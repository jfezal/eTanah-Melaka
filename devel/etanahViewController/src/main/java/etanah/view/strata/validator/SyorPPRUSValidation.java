package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.Permohonan;
import etanah.model.PermohonanPermitButir;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author nadia
 */
public class SyorPPRUSValidation implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService ptService;
    @Inject
    CommonService comm;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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

        mohonFasa = ptService.findMohonFasa(mohon.getIdPermohonan(), "semakkertasptg");
        if (!mohonFasa.getKeputusan().equals(null)) {
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                if (kodNegeri.equals("04")) {
                    t.add("STRKertasPTGPermitRuangUdaraLulus_MLK.rdf");
                    t2.add("KPRUL");
                    comm.reportGen(mohon, t, t2);
                } else {
                    t.add("STRKertasPTGPermitRuangUdaraLulus_MLK.rdf");
                    t2.add("KPRUL");
                    comm.reportGen(mohon, t, t2);
                }
            } else {
                if (kodNegeri.equals("04")) {
                    t.add("STRKertasPTGPermitRuangUdaraTolak_MLK.rdf");
                    t2.add("KPRUT");
                    comm.reportGen(mohon, t, t2);
                } else {
                    t.add("STRKertasPTGPermitRuangUdaraTolak_MLK.rdf");
                    t2.add("KPRUT");
                    comm.reportGen(mohon, t, t2);
                }

            }
        }


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
