package etanah.view.stripes.hasil;

import java.util.List;
import etanah.model.*;
import com.google.inject.Inject;
import org.apache.log4j.Logger;
import able.stripes.AbleActionBean;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodStatusTuntutanCukaiDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author abdul.hakim
 */

public class PembatalanEndorsanService extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(PembatalanEndorsanService.class);

    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Inject
    GenerateIdPermohonanWorkflow gipw;

    @Inject
    KodUrusanDAO kodUrusanDAO;
    
    @Inject
    PermohonanDAO permohonanDAO;

    @Inject
    KodStatusTuntutanCukaiDAO kodStatusTuntutCukaiDAO;;

    @Inject
    NotisPeringatan6AManager notis;

    public void checkingDasarTuntutanCukaiHakmilik(Hakmilik hakmilik, Pengguna p){
        LOG.info("--------------------------checkingDasarTuntutanCukaiHakmilik--------------------------");
        etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
//        p = ctx.getUser();
        InfoAudit ia = new InfoAudit();
        String kodStatus = null;
        DasarTuntutanCukaiHakmilik dth = new DasarTuntutanCukaiHakmilik();
        String query = "SELECT dth FROM etanah.model.DasarTuntutanCukaiHakmilik dth WHERE dth.hakmilik.idHakmilik = :hm" +
                                " AND dth.status.kod = 'ST' AND dth.perserahan6A IS NOT NULL";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("hm", hakmilik.getIdHakmilik());
        dth = (DasarTuntutanCukaiHakmilik) q.uniqueResult();
        if(dth != null){
//            dth.getSenaraiNotis();
            LOG.info("inside");
            kodStatus = "N6A";
            if(dth.getPerserahan8A() != null){kodStatus = "N8A";}
            LOG.info("kodStatus : "+kodStatus);
           sediaEndorsan(p, hakmilik, kodStatus, dth);
        }
        
    }

    public void sediaEndorsan(Pengguna p,Hakmilik h, String ks, DasarTuntutanCukaiHakmilik dth) {
        KodUrusan ku = new KodUrusan();
        Permohonan mohon = new Permohonan();
        List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
        
        try {
             if ("N6A".equals(ks)) {
                LOG.info("Pembatalan Endorsan untuk notis 6A.");
                ku = kodUrusanDAO.findById("ED6AB");
                mohon = permohonanDAO.findById(dth.getPerserahan6A().getIdPermohonan());
                FasaPermohonan mf = new FasaPermohonan();
                senaraiHakmilik.add(h);

                String query = "SELECT mf FROM etanah.model.FasaPermohonan mf WHERE mf.permohonan.idPermohonan = :fasa AND mf.keputusan.kod='AR'";
                Query q = sessionProvider.get().createQuery(query);
                q.setString("fasa", mohon.getIdPermohonan());
                mf = (FasaPermohonan) q.uniqueResult();
                LOG.info("mf.getIdFasa() : "+mf.getIdFasa());
                LOG.info("senaraiHakmilik.size() : "+senaraiHakmilik.size());

                if(mf != null){
                    LOG.info("-----SINI -----");
                    if (gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "B6A")) {
//                        addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                        LOG.info("SUCCESS");
                    }else {
                        addSimpleError("Permohonan telah gagal diwujudkan");
                    }
                }
            }
            else if("N8A".equals(ks)){
                LOG.info("Pembatalan Endorsan untuk notis 8A.");
                ku = kodUrusanDAO.findById("ED8AB");
                mohon = permohonanDAO.findById(dth.getPerserahan8A().getIdPermohonan());
                FasaPermohonan mf = new FasaPermohonan();
                senaraiHakmilik.add(h);

                String query = "SELECT mf FROM etanah.model.FasaPermohonan mf WHERE mf.permohonan.idPermohonan = :fasa AND mf.keputusan.kod='L'";
                Query q = sessionProvider.get().createQuery(query);
                q.setString("fasa", mohon.getIdPermohonan());
                mf = (FasaPermohonan) q.uniqueResult();

                if(mf != null){
                    if (gipw.generateIdPermohonanWorkflowInternal(ku, p, senaraiHakmilik, "B8A")) {
                        addSimpleMessage("Permohonan telah berjaya diwujudkan.");
                    }else {
                        addSimpleError("Permohonan telah gagal diwujudkan");
                    }
                }
            }
           dth.setStatus(kodStatusTuntutCukaiDAO.findById("BC"));
           notis.updateDTCH(dth, p);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
