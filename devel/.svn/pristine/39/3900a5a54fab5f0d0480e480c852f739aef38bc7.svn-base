/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.model.NoPt;
import etanah.model.PermohonanPlotPelan;
import etanah.service.PembangunanService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author rajib
 */
public class CheckAsciiValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    PembangunanService pembangunanServ;
    private static final Logger LOG = Logger.getLogger(CheckAsciiValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String kodNegeri = conf.getProperty("kodNegeri");
        String idPermohonan = context.getPermohonan().getIdPermohonan();
        LOG.info("idPermohonan = " + idPermohonan);
        if ((context.getPermohonan().getKodUrusan().getKod().equals("PPCB")
                || context.getPermohonan().getKodUrusan().getKod().equals("PPCS")
                || context.getPermohonan().getKodUrusan().getKod().equals("PYTN"))
                && (kodNegeri.equalsIgnoreCase("04"))) {
            ArrayList<PermohonanPlotPelan> listMohonPlotPelan = new ArrayList<PermohonanPlotPelan>(pembangunanServ.findPermohonanPlotPelanByIdPermohonan(idPermohonan));
            for (PermohonanPlotPelan mpp : listMohonPlotPelan) {
                LOG.info("list size of mohon_plot_pelan = " + listMohonPlotPelan.size());
                ArrayList<NoPt> beanNoPt = new ArrayList<NoPt>(pembangunanServ.findListNoPtByIdMohon(mpp.getIdPlot()));
                //NoPt beanNoPt = pembangunanServ.findNoPTByIdPlotPelan(mpp.getIdPlot());
                for (NoPt noPT : beanNoPt) {
                    if (noPT != null) {
                        if (noPT.getNoPtSementara() != null) {
                            if (noPT.getNoPtSementara() == 1000L) {
                                LOG.info("This is message for flag ascii - no_pt_sementara = 1000");
                                proposedOutcome = "AC";
                                break;
                            }
                        }
                    }
                }
            }
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
