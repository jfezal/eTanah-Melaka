/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pengambilan.validator;

/**
 *
 * @author nordiyana
 */
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.InfoAudit;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.KodKeputusan;
import etanah.service.NotifikasiService;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import etanah.model.PermohonanRujukanLuar;
import org.apache.log4j.Logger;
import etanah.service.common.HakmilikService;
import etanah.service.common.LelongService;
import etanah.service.PengambilanService;
import etanah.service.common.PermohonanRujukanLuarService;

/**
 *
 * @author asri
 */
public class KeputusanRunding implements StageListener {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    KodKeputusanDAO keputusanDAO;
    @Inject
    PermohonanRujukanLuarService mrlservice;
    @Inject
    LelongService lelongService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    HakmilikService hakmilikservice;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    private static final Logger LOG = Logger.getLogger(UpdateMohonFasaDesakBaru.class);
    private Hakmilik hakmilik;
    private String idHakmilik;
    private String keputusan;
    private String idPermohonanReg;
    FasaPermohonan mohonaFasa;

    public FasaPermohonan getMohonaFasa() {
        return mohonaFasa;
    }

    public void setMohonaFasa(FasaPermohonan mohonaFasa) {
        this.mohonaFasa = mohonaFasa;
    }

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
        FasaPermohonan fasaPermohonan = null;

        fasaPermohonan = pengambilanService.findFasaPermohonan114KeputusanRunding(permohonan.getIdPermohonan());
        
        Permohonan permohonan1 = pengambilanService.findByIdPermohonan2(permohonan.getIdPermohonan());
        KodKeputusan keputusan = pengambilanService.findByKodKeputusan(permohonan1.getRujukanUndang2());
        fasaPermohonan.setKeputusan(keputusan);
        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
        
        if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("TL")) {
            proposedOutcome = "TL";

        } else if (fasaPermohonan.getKeputusan().getKod().equalsIgnoreCase("L")) {
            proposedOutcome = "L";
        }
        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

//     void initiate(StageContext context, String proposedOutcome){
//
//         Permohonan permohonan = context.getPermohonan();
//         Pengguna peng = (Pengguna) context.getPengguna();
//         List<PermohonanRujukanLuar> mrl = mrlservice.findByidPermohonanList(permohonan.getIdPermohonan());
//         int i=mrl.size();
//         String nowarta;
//         String tarikhwarta;
//
//         nowarta=mrl.get(i).getItem();
//         tarikhwarta=mrl.get(i).getTarikhLulus().toString();
//         sebahagianList=hakmilikservice.findMHSebahagian(permohonan.getIdPermohonan());
//         InfoAudit infoAudit = new InfoAudit();
//         infoAudit.setDimasukOleh(peng);
//         infoAudit.setTarikhMasuk(new java.util.Date());
//
//         for(HakmilikPermohonan hp: sebahagianList)
//         {
//          idHakmilik = hp.getHakmilik().getIdHakmilik();
//
//
//         }
//         String[] name = {"idHakmilik"};
//         Object[] value = {idHakmilik};
//         List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);
//         KodUrusan kod = new KodUrusan();
////         if(context.getPermohonan().getKodUrusan().getKod().equals("831A")||context.getPermohonan().getKodUrusan().getKod().equals("831B")||context.getPermohonan().getKodUrusan().getKod().equals("831C")){
//             kod = kodUrusanDAO.findById("ABTKB");
////         }
////         else if(context.getPermohonan().getKodUrusan().getKod().equals("831B")){
////             kod = kodUrusanDAO.findById("ABT-K");
////         }else if(context.getPermohonan().getKodUrusan().getKod().equals("831C")){
////             kod = kodUrusanDAO.findById("ABT-K");
////         }
//         generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, permohonan);
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
