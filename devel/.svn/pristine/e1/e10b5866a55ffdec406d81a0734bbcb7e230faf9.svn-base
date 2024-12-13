/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
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
public class PenyediaanPUValidator implements StageListener {

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
    @Inject
    HakmilikDAO hakmilikDAO;

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
        List<HakmilikPermohonan> listHakMilikMohon = permohonan.getSenaraiHakmilik();
        for (KandunganFolder kf : listKDe) {
            if (kf.getDokumen().getKodDokumen().getKod().equals("SIPU")) {
                if (kf.getDokumen().getNamaFizikal() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM");
                    return null;
                }
            }
            if (kf.getDokumen().getKodDokumen().getKod().equals("PU")) {
                if (kf.getDokumen().getNamaFizikal() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM");
                    return null;
                }
            }
            for (HakmilikPermohonan hakmilikPermohonan : listHakMilikMohon) {
                Hakmilik hakmilik = new Hakmilik();
                hakmilik = hakmilikPermohonan.getHakmilik();
                System.out.println("Luas hakmilik :" + hakmilik.getLuas());
                System.out.println("Luas hakmilik Permohonan :" + hakmilikPermohonan.getLuasTerlibat());
                if (!hakmilik.getLuas().equals(hakmilikPermohonan.getLuasTerlibat())) {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("K")) {
                        if (kf.getDokumen().getNamaFizikal() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM - (Borang K) ");
                            return null;
                        }
                    }
                } else {
                    if (kf.getDokumen().getKodDokumen().getKod().equals("8K")) {
                        if (kf.getDokumen().getNamaFizikal() == null) {
                            context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM - (Borang 8K)");
                            return null;
                        }
                    }
                }
            }
            if (kf.getDokumen().getKodDokumen().getKod().equals("SKMMK")) {
                if (kf.getDokumen().getNamaFizikal() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM - (SKMMK)");
                    return null;
                }
            }
            if (kf.getDokumen().getKodDokumen().getKod().equals("SPU")) {
                if (kf.getDokumen().getNamaFizikal() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM - (SPU)");
                    return null;
                }
            }
            if (kf.getDokumen().getKodDokumen().getKod().equals("Pelan Pre-Comp")) {
                if (kf.getDokumen().getNamaFizikal() == null) {
                    context.addMessage(permohonan.getIdPermohonan() + " - Dokumen yang diperlukan tidak mencukupi untuk di hantar ke JUPEM - (Pelan Pre-Comp)");
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
