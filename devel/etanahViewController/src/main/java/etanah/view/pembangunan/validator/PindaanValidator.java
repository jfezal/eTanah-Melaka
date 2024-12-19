/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pembangunan.validator;

//import com.google.inject.Inject;
import com.google.inject.Inject;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.List;
import org.apache.log4j.Logger;
import oracle.bpel.services.workflow.WorkflowException;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.service.PembangunanService;
import java.math.BigDecimal;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanTuntutanBayar;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import etanah.model.PermohonanTuntutan;
import etanah.service.DevIntegrationService;
import java.util.ArrayList;

/**
 *
 * @author nursyazwani
 */
public class PindaanValidator implements StageListener {

    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PembangunanService devService;
    @Inject
    DevIntegrationService dis;
    private static final Logger LOG = Logger.getLogger(PindaanValidator.class);
    private String stage;
    private FasaPermohonan fp;

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public FasaPermohonan getFp() {
        return fp;
    }

    public void setFp(FasaPermohonan fp) {
        this.fp = fp;
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
        String outcome = "";
        try {
            outcome = checkstageID(context);
            proposedOutcome = outcome;
            // Added on 23-09-22 for StageEnd
//            Permohonan p = context.getPermohonan();
//            List<Task> l = WorkFlowService.queryTasksByAdmin(p.getIdPermohonan());
//            for (Task t : l) {
//        	if(t.getSystemAttributes().getStage() != null) {
//                    stage = t.getSystemAttributes().getStage();
//                LOG.info("======stage=========:"+stage);
//                if (stage.contentEquals("cetaksrtkpsnrekodtkhtt")) {
//                    LOG.info("======stage===If======:"+stage);
//                    proposedOutcome = "_WORKFLOW_DIRECTIVE_WITHDRAW";
//                 }
//               }
//            }
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return proposedOutcome;
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {

        return true;
    }

    public String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);
        Hakmilik h = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);
            if (stage.contentEquals("pindaanagihan")) {
                value = "BP";
                return value;
            }
            if (stage.contentEquals("semakpindaan")) {
                value = "CP";
                return value;
            }
            if (stage.contentEquals("rekodkedjkbbtangguh")) {
                value = "RT";
                return value;
            }
            if (stage.contentEquals("terimakeputusanptg")) {
                value = "KP";
                return value;
            }
            if (stage.contentEquals("membuatpindaan")) {
                value = "MP";
                return value;
            }
            if (stage.contentEquals("terimaprahitungtatatur12dptd") || stage.contentEquals("cetakhitungtatatur12d")) {
                if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("PN")) {
                    value = "HP";
                } else {
                    value = "HT";
                }
                return value;
            }
            if (stage.contentEquals("endorsanprahitungtatatur12dptd")) {
                value = "HT";
                return value;
            }
            if (stage.contentEquals("endorsanprahitungtatatur12dptg")) {
                value = "HP";
                return value;
            }
            if (stage.contentEquals("terimaprahitungtatatur7dptd")) {
                if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("PN")) {
                    value = "HP";
                } else {
                    value = "HT";
                }
                return value;
            }
            if (stage.contentEquals("endorsanprahitungtatatur7dptd")) {
                value = "HT";
                return value;
            }
            // Modify condition checking from SemakLaporanTanah to KeputusanPTD
            if (stage.contentEquals("keputusanptd")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("PN")) {
                        value = "HP";

                    } else {
                        value = "HT";
                        KodUrusan kod = new KodUrusan();
                        if (context.getPermohonan().getKodUrusan().getKod().equals("PSMT")) {
                            kod = kodUrusanDAO.findById("SBTL");
                        }
                        if (context.getPermohonan().getKodUrusan().getKod().equals("PSBT")) {
                            kod = kodUrusanDAO.findById("SBSTL");
                        }
                        dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), permohonan, "6", "F", context.getStageName());
                    }

                } else if (permohonan.getKeputusan().getKod().equals("T")) {

                    if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("PN")) {
                        value = "HN";
                    } else {
                        value = "HM";

                    }
                }
                return value;
            }
            // added condition for PSMT
            if (stage.contentEquals("perakuanlulus12a")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "HL";
                    KodUrusan kod = new KodUrusan();
                    
                    kod = kodUrusanDAO.findById("SBTL");
                    
                    dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), permohonan, "6", "F", context.getStageName());

                } else if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "HG";
                }
                return value;
            }
            // added condition for PSBT
            if (stage.contentEquals("perakuanlulus12b")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "HL";
                    KodUrusan kod = kodUrusanDAO.findById("SBSTL");
                    dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), permohonan, "6", "F", context.getStageName());

                } else if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "HG";
                }
                return value;
            }
            
            //TSBSN
            if (stage.contentEquals("perakuanptg")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "L";                 
                } else if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "T";
                }
                return value;
            }
            
            //TSPSN
            if (stage.contentEquals("perakuanrencanaringkasptg") ) {
                fp = devService.findFasaPermohonanByIdAliran(permohonan.getIdPermohonan(), "perakuanrencanaringkasptg"); 
                
                if (fp.getKeputusan().getKod().equals("L")) {
                    value = "L";                 
                } else if (fp.getKeputusan().getKod().equals("T")) {
                    value = "T";
                } else if (fp.getKeputusan().getKod().equals("TL")) {
                    value = "TL";
                } 
                
//                if (permohonan.getKeputusan().getKod().equals("L")) {
//                    value = "L";                 
//                } else if (permohonan.getKeputusan().getKod().equals("T")) {
//                    value = "T";
//                } else if (permohonan.getKeputusan().getKod().equals("TL")) {
//                    value = "TL";
//                } 
                return value;
            }

            if (stage.contentEquals("cetakprahitungtatatur12d")) {
                if (h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("PN")) {
                    value = "HP";
                } else {
                    value = "HT";
                }
                return value;
            }
            if (stage.contentEquals("hantarlulusbatalhakmilik")) {
                if (permohonan.getKeputusan().getKod().equals("L")) {
                    value = "HL";
                    KodUrusan kod = kodUrusanDAO.findById("SBTL");
                    dis.intRegPermohonan(kod, context.getPengguna(), dis.setListHakmilikByMohonHakmilik(context.getPermohonan().getSenaraiHakmilik()), permohonan, "6", "F", context.getStageName());
                } else if (permohonan.getKeputusan().getKod().equals("T")) {
                    value = "HG";
                }
                return value;
            }
//            if (stage.contentEquals("cetaksrtkpsnrekodtkhtt")) {
//                LOG.info("======stage===If======:" + stage);
//                value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
//                return value;
//            }
            if (stage.contentEquals("terimakeputusanmmkn")) {
//                if (permohonan.getKodUrusan().getKod().equals("RPP") && permohonan.getKeputusan().getKod().equals("T")) {
//                    value = "COMPLETE";
//                } else {
                    value = permohonan.getKeputusan().getKod();
//                }
                return value;
            }
            if (stage.contentEquals("semakhantarsuratkelulusan")) {
                value="COMPLETE";
//                value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                return value;
            }
            if (stage.contentEquals("semaksuratkelulusanb5a")) {
                value = "COMPLETE";
//                value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                return value;
            }
            if (stage.contentEquals("hantarpremiumbaru")) {
                value = "NB";
                return value;
            }
            if (stage.contentEquals("terimasuratkeputusan")) {
                if (permohonan.getKeputusan().getKod().equals("NK")) {
                    value = "COMPLETE";
                    //value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                }
                return value;
            }
            
            if (stage.contentEquals("terimasrtkelulusan")) {
                if (permohonan.getKodUrusan().getKod().equals("PPT") && permohonan.getKeputusan().getKod().equals("T")) {
                    value = "COMPLETE";
                } else{
                    value = "L";
                }
                return value;
            }
            
            if (stage.contentEquals("cetakrekodkpsnrayuanjkbb")) {
                if (permohonan.getKodUrusan().getKod().equals("PPT") && permohonan.getKeputusan().getKod().equals("T")) {
                    value = "T";
                } else{
                    value = "L";
                }
                return value;
            }
            
            if (stage.contentEquals("kemaskinirekodindeks")) {
                LOG.info("======stageId=========:" + stage);
                value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                return value;
            }
            if (stage.contentEquals("cetaksrttolak")) {
                LOG.info("======stageId=========:" + stage);
                value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                return value;
            }

            if (stage.contentEquals("terimabayaran")) {
                PermohonanTuntutanKos ptk = devService.findMohonTuntutKosByPremium(permohonan.getIdPermohonan());
                PermohonanTuntutan pt = devService.findMohonTuntutanByIdAsal(permohonan.getIdPermohonan());
                PermohonanTuntutanBayar ptb = devService.findTuntuBayarByPremium(ptk.getIdKos());
                Date notisDate = pt.getTarikhTuntutan();
                Date notisPaid = ptb.getTarikhBayar();
                Calendar calNotisEnd = new GregorianCalendar(1900 + notisDate.getYear(), notisDate.getMonth(), notisDate.getDate());
                Calendar calPaid = new GregorianCalendar(1900 + notisPaid.getYear(), notisPaid.getMonth(), notisPaid.getDate());
                calNotisEnd.add(Calendar.MONTH, 6);
                if (calPaid.before(calNotisEnd) || calPaid.equals(calNotisEnd)) {
                    System.out.println("bayaran selesai");
                    value = "_WORKFLOW_DIRECTIVE_WITHDRAW";
                    return value;
                } else {
                    System.out.println("bayaran lps tarikh");
                    value = "TD";
                    return value;
                }
            }            
            if (stage.contentEquals("semakprahitungtatatur9a") ||stage.contentEquals("endorsanprahitungtatatur9a")) {
               if(h.getKodHakmilik().getKod().equals("HSM") || h.getKodHakmilik().getKod().equals("GM") || 
                  h.getKodHakmilik().getKod().equals("HMM") || h.getKodHakmilik().getKod().equals("GMM") ||      
                  h.getKodHakmilik().getKod().equals("PM")){
                   value = "HT";
               }else if(h.getKodHakmilik().getKod().equals("HSD") || h.getKodHakmilik().getKod().equals("GRN") || h.getKodHakmilik().getKod().equals("PN")){
                   value = "HP";
               }
            }
            
        }
        return value;
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
