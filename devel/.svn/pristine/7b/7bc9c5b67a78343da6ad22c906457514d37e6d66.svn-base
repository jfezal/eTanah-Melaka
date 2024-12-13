/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.plpt.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohd.faidzal
 */
public class PLPTKemasukanValidator implements StageListener {

    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;

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
