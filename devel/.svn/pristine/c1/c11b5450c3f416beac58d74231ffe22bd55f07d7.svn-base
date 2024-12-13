/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import org.hibernate.Session;


/**
 *
 * @author abdul.hakim
 */
public class AnsuranValidateDate implements StageListener {
    private static final Logger LOG = Logger.getLogger(AnsuranValidateDate.class);

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

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Override
    public boolean beforeStart(StageContext context) {
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {

    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("beforeComplete::Start");
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hakmilik = hakmilikDAO.findById(permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik());

        //  untuk n9
//        if(kodNegeri.equals("n9")){
//            if(permohonan.getTempohBulan() != null && permohonan.getTarikhMula() != null){
//                flag = true;
//                checking = true;
//
//                tarikhAnsuran = sdf.format(permohonan.getTarikhMula());
//                table(permohonan.getTempohBulan(), permohonan.getTarikhMula());
//            }
//        }
        if(permohonan.getTarikhMula() == null){
            context.addMessage("Sila Masukkan tarikh mula ansuran : "+permohonan.getIdPermohonan());
            return null;
        }
//        String query = "select mf from etanah.model.FasaPermohonan mf where mf.permohonan.idPermohonan = :idPermohonan AND mf.keputusan is not null";
//        Query q = sessionProvider.get().createQuery(query);
//        q.setString("idPermohonan", permohonan.getIdPermohonan());
//        List<FasaPermohonan> senaraiFasaPermohonan  = q.list();
//        LOG.info("senaraiFasaPermohonan.size() : "+senaraiFasaPermohonan.size());
//
//        Pengguna pguna = new Pengguna();
//        for (FasaPermohonan fp : senaraiFasaPermohonan) {
//            if(fp.getKeputusan().getKod().equals("L")){
//                flag = true;
//                pguna = penggunaDAO.findById(fp.getIdPengguna());
//            }
//        }
//        Akaun akaun = hakmilik.getAkaunCukai();
//        LOG.info("flag : "+flag+" akaun.getNoAkaun() : "+akaun.getNoAkaun());
//        if(flag){
//            akaun.setAnsuran('Y');
//            akaun.setAmaunMatang(akaun.getBaki());
//
//            manager.saveOrUpdate(akaun);
//            mangr.save(permohonan, pguna);
//        }
        LOG.info("beforeComplete::Finish");
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
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
