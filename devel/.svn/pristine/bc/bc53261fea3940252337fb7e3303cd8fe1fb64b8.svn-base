/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.LaporanTanah;
import etanah.model.Permohonan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanPermitButir;
import etanah.model.PermohonanStrata;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author zadhirul.farihim
 */
public class LaporanTanahRuangUdaraValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOG = Logger.getLogger(LaporanTanahRuangUdaraValidator.class);
    private LaporanTanah laporanTanah;
    private List<PermohonanLaporanKandungan> senaraiLaporanKandungan;
    private List<PermohonanPermitButir> permohanPermitList;

    @Override
    public boolean beforeStart(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();

        try {
            laporanTanah = strService.getLaporanTanahByIdPermohonan(permohonan.getIdPermohonan());
            if (laporanTanah == null) {
                context.addMessage("Sila masukkan dan simpan maklumat pada tab 'Bangunan' ");
                return null;

            } else {
                senaraiLaporanKandungan = strService.findLaporanTanahByLaporanTanah(laporanTanah.getIdLaporan());
                if (senaraiLaporanKandungan.isEmpty() && senaraiLaporanKandungan == null) {
                    context.addMessage("Sila masukkan dan simpan maklumat pada tab 'Hal-hal Lain' ");
                    return null;
                }

            }
            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
            permohanPermitList = strService.getSumOfPermitButirByIdHakmilikPermohonan(hp.getId());
            if (permohanPermitList.isEmpty() && permohanPermitList == null) {
                context.addMessage("Sila masukkan dan simpan maklumat pada tab 'Permit Ruang Udara' ");
                return null;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext sc, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
