/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pengambilan.validator;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Permohonan;
import etanah.service.common.LelongService;
import etanah.service.common.PermohonanBaruLelongService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
public class ValidateDokumenPUValidator implements StageListener {

     private static final Logger LOG = Logger.getLogger(ValidateDokumenPUValidator.class);
    @Inject
    LelongService lelongService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanBaruLelongService gipw;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    WorkFlowService workFlowService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;

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
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Permohonan permohonan = context.getPermohonan();
        FolderDokumen f = permohonan.getFolderDokumen();
        List<KandunganFolder> listKDe = lelongService.getListALLDokumen(f.getFolderId());
                    for (KandunganFolder kf : listKDe) {
                        if (kf.getDokumen().getKodDokumen().getKod().equals("SLUMB")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Keputusan MMK Di Tab Dokumen");
                                return null;
                        }
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("PPT")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Pelan Pengambilan Di Tab Dokumen");
                                return null;
                        }
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("K8")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Borang K Di Tab Dokumen");
                                return null;
                        }
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("SPU")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Sijil Pengecualian Di Tab Dokumen");
                                return null;
                        }
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("PU")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Borang Permohonan Ukur Di Tab Dokumen");
                                return null;
                        }
                        }
                        if (kf.getDokumen().getKodDokumen().getKod().equals("8SIJU")) {
                                if (kf.getDokumen().getNamaFizikal()==null) {
                                context.addMessage(permohonan.getIdPermohonan() + " - Sila Muat Naik Salinan Surat Iringan JUPEM Di Tab Dokumen");
                                return null;
                        }
                        }
                    }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void afterPushBack(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

}
