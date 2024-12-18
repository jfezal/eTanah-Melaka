/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;

/**
 *
 * @author nordiyana
 */
import com.google.inject.Inject;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pemohon;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.StatusTanahLepasPengambilan;
import etanah.model.Permohonan;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import etanah.service.PengambilanService;
import etanah.service.common.PihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
public class kemasukanMaklumatPengambilanValidator implements StageListener {
    @Inject
    PihakService pihakservice;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    PengambilanService pengambilanservice;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        String idPermohonan = context.getPermohonan().getIdPermohonan();

        Permohonan permohonan = context.getPermohonan();

        if (permohonan.getSebab() == null) {
            context.addMessage( idPermohonan + " - Sila Masukkan Maksud Pengambilan Di Tab Pengambilan");
            return null;
        }

        for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
            if (pp.getLuasTerlibat() == null) {
                context.addMessage( idPermohonan + " - Sila Masukkan Luas yang terlibat di Tab Maklumat Tanah");
                return null;
            }

        }

        List<Pemohon> listPemohon = permohonan.getSenaraiPemohon();

        if (listPemohon.size() == 0) {
            context.addMessage( idPermohonan + " - Sila masukkan Maklumat Pemohon Di Tab Maklumat Pemohon");
            return null;
        }

        PermohonanRujukanLuar mrl = mrlservice.findByidPermohonan(idPermohonan);

        if (mrl.getNoRujukan()==null) {
            context.addMessage(idPermohonan + " - Sila Masukkan No Rujukan Surat Di Tab Maklumat Pengambilan");
            return null;
        }
        if (mrl.getTarikhRujukan()==null) {
            context.addMessage(idPermohonan + " - Sila Masukkan Tarikh Rujukan Surat Di Tab Maklumat Pengambilan");
            return null;
        }
       PermohonanPengambilan permohonanPengambilan = mrlservice.findByidP(idPermohonan);

//       if (permohonanPengambilan.getSelepasPengambilan()==null) {
//            context.addMessage(idPermohonan + " - Sila Masukkan Selepas Pengambilan Tanah Di Tab Maklumat Pengambilan");
//            return null;
//        }
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
