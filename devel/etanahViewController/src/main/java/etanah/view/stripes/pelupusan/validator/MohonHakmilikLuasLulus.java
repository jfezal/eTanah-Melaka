/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.HakmilikPermohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.service.NotifikasiService;
import etanah.service.PelupusanService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.service.common.HakmilikService;
import etanah.service.common.PermohonanRujukanLuarService;
import java.math.BigDecimal;
import org.apache.log4j.Logger;

/**
 *
 * @author hazirah
 */
public class MohonHakmilikLuasLulus implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    private etanah.Configuration conf;
    private HakmilikPermohonan hp;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private String stageId;
    private static final Logger LOG = Logger.getLogger(MohonHakmilikLuasLulus.class);

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void onGenerateReports(StageContext context) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        stageId = context.getStageName();
//        stageId = "014";
        Pengguna pengguna = context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PRIZ")) {
                if (stageId.equalsIgnoreCase("014")) { // save mohon_hakmilik.luas_lulus
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    LOG.info("id hakmilik : " + idHakmilik);

                    hp = pelupusanService.findIdMH(permohonan.getIdPermohonan(), idHakmilik);
                    LOG.info("id mohon : " + permohonan.getIdPermohonan());
                    LOG.info("luas terlibat : " + hp.getLuasTerlibat());
                    LOG.info("kod unit luas : " + hp.getKodUnitLuas());
                    infoAudit = hp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                    hp.setLuasDiluluskan(hp.getLuasTerlibat());
                    hp.setLuasLulusUom(hp.getKodUnitLuas());
                    pelupusanService.saveOrUpdate(hp);
//                    for (HakmilikPermohonan hakmilikPermohonan : permohonan.getSenaraiHakmilik()) {
//                        infoAudit = hakmilikPermohonan.getInfoAudit();
//                        infoAudit.setDiKemaskiniOleh(pengguna);
//                        infoAudit.setTarikhKemaskini(new java.util.Date());
//                        hakmilikPermohonan.setLuasDiluluskan(hakmilikPermohonan.getLuasTerlibat());
//                        hakmilikPermohonan.setLuasLulusUom(hakmilikPermohonan.getKodUnitLuas());
//                    }
                }
            }
        }

        return proposedOutcome;
//        return null;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }
//
//     void initiate(StageContext context, String proposedOutcome){
//
//         Permohonan permohonan = context.getPermohonan();
//         Pengguna peng = (Pengguna) context.getPengguna();
//         PermohonanRujukanLuar mrl = mrlservice.findByidPermohonan(permohonan.getIdPermohonan());
//         InfoAudit infoAudit = new InfoAudit();
//         infoAudit.setDimasukOleh(peng);
//         infoAudit.setTarikhMasuk(new java.util.Date());
//         List<HakmilikPermohonan> mhList=permohonan.getSenaraiHakmilik();
//
//         for(HakmilikPermohonan hp:mhList){
//         idHakmilik=hp.getHakmilik().getIdHakmilik();
//         }
//         String[] name = {"idHakmilik"};
//         Object[] value = {idHakmilik};
//         List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//         KodUrusan kod = new KodUrusan();
//         LOG.info("Kod Urusan " + context.getPermohonan().getKodUrusan().getKod());
////          if(context.getPermohonan().getKodUrusan().getKod().equals("831A")||context.getPermohonan().getKodUrusan().getKod().equals("831B")||context.getPermohonan().getKodUrusan().getKod().equals("831C")){
//             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         if(context.getPermohonan().getKodUrusan().getKod().equals("831A")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831B")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831C")){
////             kod = kodUrusanDAO.findById("ABT-D");
////         }
//         generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//     }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }
}
