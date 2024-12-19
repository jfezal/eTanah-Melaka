///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package etanah.view.pengambilan.validator;
//import com.google.inject.Inject;
//import etanah.model.Permohonan;
//import etanah.model.PermohonanLaporanPelan;
//import etanah.model.HakmilikPermohonan;
//import etanah.service.LaporanPelukisPelanService;
//import etanah.workflow.StageContext;
//import etanah.workflow.StageListener;
//import org.apache.log4j.Logger;
//
///**
// *
// * @author nordiyana
// */
//public class KeputusanMMKNValidator implements StageListener{
//    @Inject
//    PermohonanDAO permohonanDAO;
//    @Inject
//    LaporanTanahDAO laporanTanahDAO;
//    private static final Logger LOG = Logger.getLogger(mmknSek4_mlkValidator.class);
//
//    @Override
//    public boolean beforeStart(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public void onGenerateReports(StageContext context) {
//    }
//
//    @Override
//    public String beforeComplete(StageContext context, String proposedOutcome) {
//
//        Permohonan permohonan = context.getPermohonan();
//        try {
//            if (permohonan.getSenaraiKertas().isEmpty()) {
//
//                context.addMessage("Sila masukkan maklumat kertas MMK : " + permohonan.getIdPermohonan());
//                return null;
//
//            }
////            List<LaporanTanah> laporantanah;
////             for (HakmilikPermohonan pp : permohonan.getSenaraiHakmilik()) {
////            if (pp.getTanahDiambil() == null) {
////                context.addMessage( idPermohonan + " - Sila Verify tanah yang terlibat di Tab Pelukis Pelan");
////                return null;
////            }
//
//        } catch (Exception e) {
//            LOG.error(e.getMessage());
//            return null;
//        }
//        return proposedOutcome;
//    }
//
//    @Override
//    public void afterComplete(StageContext context) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public boolean beforeGenerateReports(StageContext ctx) {
//
//        return true;
//    }
//}
//
//
