package etanah.view.hasil.validator;

import com.google.inject.Inject;
import etanah.model.*;
import etanah.dao.*;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.hasil.GenerateIdPermohonanWorkflow;
import etanah.view.stripes.hasil.KutipanHasilManager;
import etanah.view.stripes.hasil.NotisPeringatan6AManager;
import etanah.view.stripes.hasil.RemisyenManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author abdul.hakim
 */
public class NotisGantianValidator implements StageListener {
    private static final Logger LOG = Logger.getLogger(NotisGantianValidator.class);

    private boolean flag = false;
    private List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik;

    public List<DasarTuntutanCukaiHakmilik> getSenaraiDasarTuntutanCukaiHakmilik() {
        return senaraiDasarTuntutanCukaiHakmilik;
    }

    public void setSenaraiDasarTuntutanCukaiHakmilik(List<DasarTuntutanCukaiHakmilik> senaraiDasarTuntutanCukaiHakmilik) {
        this.senaraiDasarTuntutanCukaiHakmilik = senaraiDasarTuntutanCukaiHakmilik;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;

    @Inject
    KodUrusanDAO kodUrusanDAO;

    @Inject
    LaporanTanahService serviceMgr;

    @Inject
    NotisPeringatan6AManager npmgr;

    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarHakmilikDAO;

    @Inject
    KodRujukanDAO kodRujukanDAO;

    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;

    @Inject
    NotisPeringatan6AManager manager;

    @Inject
    GenerateIdPermohonanWorkflow gipw;
    
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
        LOG.info("ProsesNotisGantian::beforeComplete()");

        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();

        String semuaTerima = null;
        String tidakTerima = null;

        String query = "Select hp FROM etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan = :idMohon";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idMohon", permohonan.getIdPermohonan());
        HakmilikPermohonan mohonHakmilik = (HakmilikPermohonan) q.uniqueResult();
        LOG.info("mohonHakmilik.getId() : " + mohonHakmilik.getId());

        Hakmilik hm = new Hakmilik();
        hm = mohonHakmilik.getHakmilik();
        KodUrusan ku = kodUrusanDAO.findById("ED6A");
        try {

            List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
            String[] name = {"hakmilik"};
            Object[] value = {hm};
            senaraiDasarTuntutanCukaiHakmilik = dasarHakmilikDAO.findByEqualCriterias(name, value, null);

            LOG.info("senaraiDasarTuntutanCukaiHakmilik.size() : "+senaraiDasarTuntutanCukaiHakmilik.size());
            LOG.info("Endorsan untuk Notis Gantian.");

            for (DasarTuntutanCukaiHakmilik dtch : senaraiDasarTuntutanCukaiHakmilik) {
                if (!dtch.getHakmilik().getAkaunCukai().getBaki().equals(BigDecimal.ZERO)) { // check if hakmilik have 'baki' or not
                    for (DasarTuntutanNotis dtn : dtch.getSenaraiNotis()) {
                        LOG.debug("dtch.getSenaraiNotis().size :" + dtch.getSenaraiNotis().size());
                        if ("NG".equals(dtn.getNotis().getKod()) && (dtn.getDihantarOleh()!= null)){
                            LOG.info("--- sini ---");
                            LOG.debug("idNotis terima:" + dtn.getIdNotis());

                            LOG.debug("Semua idNotis dapat disampaikan. Generate permohonan diteruskan");
                            senaraiHakmilik.add(hm);
                            LOG.debug("senaraiHakmilik.size :" + senaraiHakmilik.size());
                            if (gipw.generateIdPermohonanWorkflowInternal(ku, pengguna, senaraiHakmilik, "")) {
                                context.addMessage("Permohonan telah berjaya diwujudkan.");
//                                manager.simpan6A(senaraiHakmilik, pengguna); // add transaksi N6A = 10 for N9 while hakmilik dpt hantar notis6A
                                LOG.info("ProsesNotisGantian::beforeComplete() FINISH");
                            } else {
                                context.addMessage("Permohonan telah gagal diwujudkan");
                            }
                        }
                    }
                }
            }
        }catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
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
