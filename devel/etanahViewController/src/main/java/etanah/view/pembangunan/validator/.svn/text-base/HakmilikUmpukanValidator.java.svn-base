/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.pembangunan.validator;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.apache.log4j.Logger;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.InfoAudit;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.service.PembangunanService;
import etanah.dao.KodKeputusanDAO;
import etanah.model.PermohonanPlotPelan;


/**
 *
 * @author nursyazwani
 */
public class HakmilikUmpukanValidator implements StageListener {
    private static final Logger LOG = Logger.getLogger(HakmilikUmpukanValidator.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    KodKeputusanDAO keputusanDAO;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        LOG.info("------beforeComplete-------");
        Pengguna p = context.getPengguna();
        Permohonan permohonan = context.getPermohonan();
        String idPermohonan = permohonan.getIdPermohonan();
        String idAliran = "hakmilikumpukan";
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik h = hp.getHakmilik();
        String hakmilik = null;
//        PermohonanPlotPelan plot = devService.findPermohonanPlotPelanByIdPermohonan(idPermohonan).get(0);
        
        if (h != null) {
//            FasaPermohonan ff= new FasaPermohonan();
//            InfoAudit ia = p.getInfoAudit();
//            ia.setDimasukOleh(p);
//            ia.setTarikhMasuk(new java.util.Date());
//            ff.setCawangan(p.getKodCawangan());
//            ff.setIdPengguna(p.getIdPengguna());
//            ff.setInfoAudit(ia);
//            ff.setIdAliran(idAliran);
//            ff.setPermohonan(permohonan);
//            ff.setTarikhHantar(new java.util.Date());
            if(h.getKodHakmilik().getKod().equals("HSM") || h.getKodHakmilik().getKod().equals("GM") || h.getKodHakmilik().getKod().equals("PM")){
                if(h.getKodUnitLuas().getKod().equals("H")){
                    if(h.getLuas().doubleValue() < 40){
//                         ff.setKeputusan(keputusanDAO.findById("MK"));
                        hakmilik = "NM";
                    }
                    else{
//                        ff.setKeputusan(keputusanDAO.findById("DR"));
                        hakmilik = "LD";
                    }
                }
                else if(h.getKodUnitLuas().getKod().equals("M")){
                    if(h.getLuas().doubleValue() < 400000){
//                        ff.setKeputusan(keputusanDAO.findById("MK"));
                        hakmilik = "NM";
                    }
                    else{
//                        ff.setKeputusan(keputusanDAO.findById("DR"));
                        hakmilik = "LD";
                    }
                }
            }
            else if(h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN")){
                if(h.getKodUnitLuas().getKod().equals("H")){
                    if(h.getLuas().doubleValue() < 40){
//                         ff.setKeputusan(keputusanDAO.findById("MK"));
                        hakmilik = "NM";
                    }
                    else{
//                        ff.setKeputusan(keputusanDAO.findById("DF"));
                        hakmilik = "LD";
                    }
                }
                else if(h.getKodUnitLuas().getKod().equals("M")){
                    if(h.getLuas().doubleValue() < 400000){
//                        ff.setKeputusan(keputusanDAO.findById("MK"));
                        hakmilik = "NM";
                    }
                    else{
//                        ff.setKeputusan(keputusanDAO.findById("DF"));
                        hakmilik = "LD";
                    }
                }
            }
//
        }
        proposedOutcome = hakmilik;
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