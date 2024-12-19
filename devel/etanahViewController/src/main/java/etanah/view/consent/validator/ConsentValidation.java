/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.consent.validator;

import com.google.inject.Inject;
import etanah.dao.PemohonDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.PermohonanPihakService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import javax.mail.Session;

/**
 *
 * @author muhammad.amin
 */
public class ConsentValidation implements StageListener{

    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDao;

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Override
    public boolean beforeStart(StageContext ctx) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext ctx, String proposedOutcome) {

        String[] tname = {"permohonan"};
        Object[] model = {ctx.getPermohonan()};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);
        
        List<PermohonanPihak> listPenerima;
        listPenerima = permohonanPihakDao.findByEqualCriterias(tname, model, null);

        if(listPemohon.isEmpty()){

        }

        if(listPenerima.isEmpty()){

        }

        return proposedOutcome;
    }

	@Override
	public void afterComplete(StageContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGenerateReports(StageContext context) {
		// TODO Auto-generated method stub
		
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
        return "back";
    }

}
