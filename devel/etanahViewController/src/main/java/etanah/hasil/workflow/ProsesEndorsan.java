/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.hasil.workflow;

import com.google.inject.Inject;
import etanah.dao.DasarTuntutanCukaiDAO;
import etanah.dao.DasarTuntutanCukaiHakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.DasarTuntutanCukaiHakmilik;
import etanah.model.DasarTuntutanNotis;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.LaporanTanahService;
import etanah.view.stripes.hasil.GenerateIdPermohonanWorkflow;
import etanah.view.stripes.hasil.NotisPeringatan6AManager;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author khadijah
 */
public class ProsesEndorsan implements StageListener {

    private static final Logger LOG = Logger.getLogger(ProsesEndorsan.class);
    @Inject
    GenerateIdPermohonanWorkflow gipw;
    @Inject
    DasarTuntutanCukaiHakmilikDAO dasarTuntutanCukaiHakmilikDAO;
    @Inject
    DasarTuntutanCukaiDAO dasarTuntutanCukaiDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    NotisPeringatan6AManager npmgr;
    @Inject
    LaporanTanahService serviceMgr;
    @Inject
    PermohonanRujukanLuar permohonanRujukanLuar;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;

    @Override
    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
//        throw new UnsupportedOperationException("Not supported yet.");
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        LOG.info("ProsesRCTN or ProsesREMSB::beforeComplete()");
        Pengguna pengguna = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        Hakmilik hm = new Hakmilik();
        KodUrusan ku = new KodUrusan();
        String Semak = "SM";
        String Lulus = "L";
        String kodUrusan1 = "PCT";  // for endorsan (PCT) in Modul Pendaftaran
        if (Semak.equals(proposedOutcome) || Lulus.equals(proposedOutcome)) {
            if ("REMRI".equals(permohonan.getKodUrusan().getKod()) || "REMSB".equals(permohonan.getKodUrusan().getKod())) {
                ku = kodUrusanDAO.findById(kodUrusan1);
            }
            String[] tname = {"permohonan"};
            Object[] tvalue = {permohonan};
            List<HakmilikPermohonan> senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(tname, tvalue, null);
            if (!senaraiHakmilikPermohonan.isEmpty()) {
                List<Hakmilik> senaraiHakmilik = new ArrayList<Hakmilik>();
                for (HakmilikPermohonan hp : senaraiHakmilikPermohonan) {
                    senaraiHakmilik.add(hp.getHakmilik());
                    LOG.debug("Hakmilik :" + hp.getHakmilik().getIdHakmilik());
                    hm = hp.getHakmilik();
                }
                LOG.debug("senaraiHakmilik.size :" + senaraiHakmilik.size());
                Permohonan permohonanBaru = gipw.genWorkflowIdPermohonan(ku, pengguna, senaraiHakmilik, permohonan);
                if (permohonanBaru != null) {
                    KodRujukan kr = new KodRujukan();
                    kr.setKod("FL"); //Fail
                    PermohonanRujukanLuar prlDaftar = new PermohonanRujukanLuar();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(pengguna);
                    info.setTarikhMasuk(new java.util.Date());
                    info.setDiKemaskiniOleh(pengguna);
                    info.setTarikhKemaskini(new java.util.Date());

                    Session s = sessionProvider.get();
                    Query q = s.createQuery("SELECT p FROM etanah.model.PermohonanRujukanLuar p WHERE p.permohonan.idPermohonan =:idMohon");
                    q.setString("idMohon", permohonan.getIdPermohonan());
                    PermohonanRujukanLuar rujuk = (PermohonanRujukanLuar) q.uniqueResult();

                    prlDaftar.setKodRujukan(kr);
                    prlDaftar.setNoFail(permohonan.getIdPermohonan());
                    prlDaftar.setNoSidang(rujuk.getNoSidang());
                    prlDaftar.setTarikhRujukan(rujuk.getTarikhSidang());
                    prlDaftar.setCawangan(permohonan.getCawangan());
                    prlDaftar.setTarikhKuatkuasa(rujuk.getTarikhSidang());
                    LOG.debug("permohonanDaftar.idMohon :" + permohonanBaru.getIdPermohonan());
                    prlDaftar.setPermohonan(permohonanBaru);
                    prlDaftar.setInfoAudit(info);
                    serviceMgr.simpanRujukanLuar(prlDaftar); //create new record for new permohonan in modul pendaftaran


                    // set table hakmilikPermohonan -> cukai_baru
                    Query qmh = s.createQuery("SELECT p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan =:idMohon");
                    qmh.setString("idMohon", permohonan.getIdPermohonan());
                    HakmilikPermohonan hp = (HakmilikPermohonan) qmh.uniqueResult();

                    hp.setCukaiBaru(permohonan.getNilaiKeputusan());
                    InfoAudit ia1 = hp.getInfoAudit();
                        ia1.setDiKemaskiniOleh(pengguna);
                        ia1.setTarikhKemaskini(new java.util.Date());
                    hp.setInfoAudit(ia1);
                    
                    serviceMgr.updateHakmilikPermohonan(hp);


                    hm.setCukaiSebenar(permohonan.getNilaiKeputusan());
                    InfoAudit ia = hm.getInfoAudit();
                        ia.setDiKemaskiniOleh(pengguna);
                        ia.setTarikhKemaskini(new java.util.Date());
                    hm.setInfoAudit(ia);

                    serviceMgr.updateHakmilik(hm);
                }
                
            } else {
                context.addMessage("Permohonan TIDAK berjaya diwujudkan.");
                LOG.info("Permohonan TIDAK berjaya diwujudkan. IdPermohonan tidak berjaya diwujudkan");
                return null;
            }
        } else {
            context.addMessage("Tiada hakmilik dikenalpastikan.");
            LOG.error("senaraiHakmilikPermohonan.isEmpty");
            return null;
        }
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
        return "back";
    }
}
