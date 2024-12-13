/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodPerananDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodPeranan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.KandunganFolderService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import oracle.bpel.services.workflow.WorkflowException;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author afham
 */
public class MaklumanKeputusanJKBB implements StageListener {
    
    @Inject
    PelupusanService plpService;
    @Inject
    etanah.Configuration conf;  
    private static Pengguna pengguna;
    @Inject
    ReportUtil reportUtil;
    private static final Logger LOG = Logger.getLogger(MaklumanKeputusanJKBB.class);
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;

    @Override
    public boolean beforeStart(StageContext sc) {
        return true ;
    }

    @Override
    public boolean beforeGenerateReports(StageContext sc) {
        return true ;
    }

    @Override
    public void onGenerateReports(StageContext sc) {
   
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        
          Notifikasi n = new Notifikasi();
//            n.setTajuk("Makluman Keputusan MMKN");
//            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + "Keputusan MMKN : " + context.getPermohonan().getKeputusan().getNama());
            Pengguna p = context.getPengguna();
//            n.setCawangan(p.getKodCawangan());
            List<KodPeranan> list;
            list = new ArrayList<KodPeranan>();
            String[] a = {"225","220","227"};
                     
            LOG.info("Makluman Keputusan JKBB");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(p);
            ia.setTarikhMasuk(new Date());
            
            for(int i = 0; i < a.length; i++){
	            LOG.info(kodPerananDAO.findById(a[i]+""));
	            list = plpService.getSenaraiKodPerananByID(a[i]);
	            KodPeranan kp = kodPerananDAO.findById(a[i]+"") ;
	            String msj = null;
	            if(context.getPermohonan().getKodUrusan().getKod().equals("PPBB")){
	                msj = "Permohonan Permit Bahan Batuan (Keluluasan Menteri) ";
	            }
	            else if(context.getPermohonan().getKodUrusan().getKod().equals("PBPTD")){
	                msj = "Permohonan Permit Bahan Batuan (Keluluasan PTD) ";
	            }
                    else if(context.getPermohonan().getKodUrusan().getKod().equals("PBPTG")){
	                msj = "Permohonan Permit Bahan Batuan (Keluluasan PTG) ";
	            }
	            if(context.getPermohonan().getKeputusan().getKod().equals("L")) {
	            	msj += "telah <b>diluluskan</b> oleh Jawantankuasa Belah Bahagi Tanah.";
	            }
	            else if(context.getPermohonan().getKeputusan().getKod().equals("T")) {
	            	msj += "telah <b>ditolak</b> oleh Jawantankuasa Belah Bahagi Tanah..";
	            }
	            n.setTajuk("Makluman Keputusan JKBB");
	            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + msj);
	            n.setCawangan(p.getKodCawangan());
	            n.setInfoAudit(ia) ;
	            
	            LOG.info("Maklum kepada :" + kp.getNama());
	            notifikasiService.addRoleToNotifikasi(n, p.getKodCawangan(), kp);     
        }
             return proposedOutcome ;
    }

    @Override
    public void afterComplete(StageContext sc) {
        
    }

    @Override
    public String beforePushBack(StageContext sc, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext sc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
