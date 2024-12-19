 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

import com.google.inject.Inject;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.PelupusanService;
import org.apache.log4j.Logger;

/**
 *
 * @initial author azwady.org 03/03/2014
 */
public class KeputusanValidation implements StageListener {

    private static final Logger LOG = Logger.getLogger(KeputusanNotification.class);
    @Inject
    PelupusanService pelupusanService;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        //not require to generate report for this validator
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        /*if no 'keputusan' is entered, don't allow user to 'selesai'*/
        Permohonan permohonan = context.getPermohonan(); //initiate 'permohonan'

        if (permohonan.getKodUrusan().getKod().equals("RPP") && (context.getStageName().equals("perakuanderafmmkptd"))) {
            PermohonanTuntutanKos permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DEV04");
            LOG.info("amaun sebenar " + permohonanTuntutanKos.getAmaunSebenar());
            if (permohonanTuntutanKos.getAmaunSebenar() == null) {
                context.addMessage("Sila masukan nilai PREMIUM terlebih dahulu sebelum urusan dihantar ke peringkat seterusnya." + permohonan.getIdPermohonan());
                return null;
            }
        } else if (permohonan.getKodUrusan().getKod().equals("RPP") || (permohonan.getKodUrusan().getKod().equals("RPS") && (context.getStageName().equals("rekodkeputusanmmk")))) {
            if (permohonan.getKeputusan() == null) {
                context.addMessage("Sila masukkan keputusan terlebih dahulu : " + permohonan.getIdPermohonan());
                return null;
            }

        } else if (!permohonan.getKodUrusan().getKod().equals("RPP") || (!permohonan.getKodUrusan().getKod().equals("RPS"))) {
            if (permohonan.getKeputusan() == null) {
                if (permohonan.getKeputusan() == null) {
                    context.addMessage(context.getPermohonan().getIdPermohonan() + " " + (": Sila masukkan keputusan MMK sebelum menghantar tugasan")); //altered by azwady.org 11/03/2014
                    LOG.info("---------------idMohon=" + permohonan.getIdPermohonan());
                    return null;
                }
            }
        }
        //proposedOutcome = permohonan.getKeputusan().getKod();
        //LOG.info("----------proposedOutcome----------:" + proposedOutcome);
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
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }
}
