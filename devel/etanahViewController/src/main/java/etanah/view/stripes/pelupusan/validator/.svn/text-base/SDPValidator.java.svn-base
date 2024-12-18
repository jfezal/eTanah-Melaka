/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.KodKeputusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Shazwan 11 April 2013
 * @version 1.0
 * purposed : To Cater SDP Case 
 */
public class SDPValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    private static final Logger LOGGER = Logger.getLogger(SDPValidator.class);

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan p = context.getPermohonan();
        Pengguna pengguna = context.getPengguna();
        String stage = context.getStageName();
        if (p != null) {
            if (!StringUtils.isEmpty(stage)) {
                if (stage.equalsIgnoreCase("arahkan_SDP")) {
                    p.setInfoAudit(disLaporanTanahService.findAudit(p, "update", pengguna));
                    p.setStatus("SD");

                    disLaporanTanahService.getPelupusanService().simpanPermohonan(p);
                    System.out.println("status permohonan:" + p.getStatus());
                } else if (stage.equalsIgnoreCase("sdp")) {
                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
                    fasaPermohonan = (FasaPermohonan) disLaporanTanahService.findObject(fasaPermohonan, new String[]{p.getIdPermohonan(), stage}, 0);
                    if (fasaPermohonan != null && fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("Y")) {
                        p.setInfoAudit(disLaporanTanahService.findAudit(p, "update", pengguna));
                        KodKeputusan kodKeputusan = new KodKeputusan();
                        kodKeputusan = (KodKeputusan) disLaporanTanahService.findObject(kodKeputusan, new String[]{"T"}, 0);
                        p.setKeputusan(kodKeputusan);
                        disLaporanTanahService.getPelupusanService().simpanPermohonan(p);
                    }
                }

            }

        }
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
