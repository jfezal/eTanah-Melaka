/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.KodPerananDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import etanah.service.NotifikasiService;

/**
 *
 * @author Sreenivasa Reddy Munagala.
 */
public class PemohonanPindaBangunanValidator implements StageListener {

    @Inject
    StrataPtService strService;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    KodPerananDAO kodPerananDAO;
    private PermohonanStrata pemilik;
    private Pemohon pemohon;
    private BadanPengurusan mc;
    private static final Logger LOG = Logger.getLogger(PemohonanPindaBangunanValidator.class);
    private String catatan;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;

    @Override
    public boolean beforeStart(StageContext context) {
        LOG.info("--------------------beforeStart--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        LOG.info("----beforeComplete-----" + context.getPermohonan());
        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        Pemohon pm = new Pemohon();
        pm = strService.findById(permohonan.getIdPermohonan());
        System.out.println("---pm---" + pm);

        if (pm == null) {
            System.out.println("---pm null---");
            pm = new Pemohon();            
            pm.setPermohonan(permohonan);
            pm.setCawangan(permohonan.getCawangan());
            pm.setInfoAudit(infoAudit);
            strService.savePemohon(pm);
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        System.out.println("--------------------afterComplete--------------------");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        System.out.println("--------------------beforeGenerateReports--------------------");
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
}


