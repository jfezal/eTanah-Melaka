/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author abdul.hakim
 */
public class AnsuranTolak implements StageListener{
    private static final Logger LOG = Logger.getLogger(AnsuranTolak.class);

    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    @Inject
    HakmilikDAO hakmilikDAO;

    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;

    @Inject
    PenggunaDAO penggunaDAO;

    @Inject
    KutipanHasilManager manager;

    @Inject
    RemisyenManager mangr;

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
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());
        List<FasaPermohonan> senaraiFasaPermohonan = new ArrayList<FasaPermohonan>();

        String[] name = {"permohonan"};
        Object[] value= {permohonan};
        senaraiFasaPermohonan = fasaPermohonanDAO.findByEqualCriterias(name, value, null);

        Pengguna pguna = new Pengguna();
        for (FasaPermohonan fp : senaraiFasaPermohonan) {
            if((fp.getKeputusan()!=null)&&(fp.getKeputusan().getKod().equals("T"))){
                flag = true;
                pguna = penggunaDAO.findById(fp.getIdPengguna());
            }
        }
        Akaun akaun = new Akaun();
        LOG.info("flag : "+flag);
        if(flag){
            List<Akaun> akList = hakmilik.getSenaraiAkaun();
            for (Akaun ak : akList) {
                if(ak.getKodAkaun().getKod().equals("ACT")){
                    akaun = ak;
                }
            }
            manager.delete(akaun);
            permohonan.setTempohBulan(0);
            mangr.save(permohonan, pguna);
        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
