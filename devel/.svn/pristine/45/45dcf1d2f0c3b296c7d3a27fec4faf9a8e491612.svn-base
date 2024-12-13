/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.service.StrataPtService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import java.util.ArrayList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import etanah.workflow.WorkFlowService;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.StaleObjectException;
import java.util.logging.Level;

/**
 *
 * @author faidzal
 */
public class JanaReportValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    StrataPtService strService;
    @Inject
    CommonService comm;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    private static final Logger LOG = Logger.getLogger(JanaReportValidator.class);
    IWorkflowContext ctxOnBehalf = null;
    private PermohonanStrata mohonStrata = new PermohonanStrata();
    private PermohonanStrata mohonIdStrata;
    private PermohonanStrata maxKos;
    private PermohonanStrata kosSijilRendahExist;
    private String maxKosSijilRendah;
    private String stageId;
    private String taskId;
    private String nextStage;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean beforeGenerateReports(StageContext ctx) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext context) {
        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        String stage = "";
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();
        if (mohon.getKodUrusan().getKod().equals("RTPS")) {
            LOG.info("-------LALU DI RTPS-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusRayuanPindahMilik_MLK.rdf");
                        t2.add("RTPS1");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSLulusRayuanPindahMilik_NS.rdf");
                        t2.add("RTPS1");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakRayuanPindahMilik_MLK.rdf");
                        t2.add("RTPS2");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakRayuanPindahMilik_NS.rdf");
                        t2.add("RTPS2");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("RTHS")) {
            LOG.info("-------LALU DI RTHS-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusRayuanHMStrata_MLK.rdf");
                        t2.add("RTHS1");
                        t.add("STRDrafRTHS_MLK.rdf");
                        t2.add("DRTHS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSLulusRayuanHMStrata_NS.rdf");
                        t2.add("RTHS1");
                        t.add("STRDrafRTHS_NS.rdf");
                        t2.add("DRTHS");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakRayuanHMStrata_MLK.rdf");
                        t2.add("RTHS2");
                        t.add("STRDrafRTHS_MLK.rdf");
                        t2.add("DRTHS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakRayuanHMStrata_NS.rdf");
                        t2.add("RTHS2");
                        t.add("STRDrafRTHS_NS.rdf");
                        t2.add("DRTHS");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PNB")) {
            LOG.info("-------LALU DI PNB-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSLulusTarikBalik_MLK.rdf");
                        t2.add("SKBS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSLulusTarikBalik_NS.rdf");
                        t2.add("SKBS");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakTarikBalik_MLK.rdf");
                        t2.add("SPBS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakTarikBalik_NS.rdf");
                        t2.add("SPBS");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PPRUS")) {
            LOG.info("-------LALU DI PPRUS-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSBayaranPermitRuangUdara_MLK.rdf");
                        t2.add("SKRU");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSLulusTarikBalik_NS.rdf");
                        t2.add("SKBS");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakPermitRuangUdara_MLK.rdf");
                        t2.add("STRU");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakTarikBalik_NS.rdf");
                        t2.add("SPBS");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("P8") || mohon.getKodUrusan().getKod().equals("P22A")
                || mohon.getKodUrusan().getKod().equals("P22B") || mohon.getKodUrusan().getKod().equals("P40A")
                || mohon.getKodUrusan().getKod().equals("P14A")
                || mohon.getKodUrusan().getKod().equals("P20A")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("AK")) {
                    if (kodNegeri.equals("04")) {
                        t.add("STRLantikIO_MLK.rdf");
                        t2.add("SLPPS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRLantikIO_NS.rdf");
                        t2.add("SLPPS");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakKes_MLK.rdf");
                        t2.add("STPS");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakKes_NS.rdf");
                        t2.add("STPS");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PKKR")) {
            LOG.info("-------LALU DI PKKR-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    LOG.info("------Lalu L-------");
                    if (kodNegeri.equals("04")) {

                        LOG.info("-------ID MOHON---------" + mohon.getIdPermohonan());
                        LOG.info("-------mohonStrata------" + mohonStrata);

                        mohonIdStrata = strService.findID(mohon.getIdPermohonan());
                        if (mohonIdStrata.getPerakuanKosRendahNoSijil() == null) {
                            maxKos = strService.getHighestKodSijilRendah();
                            LOG.info("-------maxKos--------" + maxKos);
                            if (maxKos == null) {
                                LOG.info("------Lalu Max Kos Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                mohonStrata.setPerakuanKosRendahNoSijil("1");
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            } else if (maxKos != null) {
                                LOG.info("------Lalu MAx Kos Tidak Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                maxKosSijilRendah = maxKos.getPerakuanKosRendahNoSijil();
                                LOG.info("-------maxKosSijilRendah------" + maxKosSijilRendah);
                                int IntMaxKos = Integer.parseInt(maxKosSijilRendah) + 1;
                                LOG.info("-------IntMaxKos------" + IntMaxKos);
                                mohonStrata.setPerakuanKosRendahNoSijil(Integer.toString(IntMaxKos));
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            }
                        }

                        t.add("STRSLulusBgnKosRendah_MLK.rdf");
                        t2.add("SLKR");

                        t.add("STRBHKosRendah_MLK.rdf");
                        t2.add("KKR");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        LOG.info("------Lalu Kod NS-------");
                        LOG.info("-------ID MOHON---------" + mohon.getIdPermohonan());
                        LOG.info("-------mohonStrata------" + mohonStrata);

                        mohonIdStrata = strService.findID(mohon.getIdPermohonan());
                        if (mohonIdStrata.getPerakuanKosRendahNoSijil() == null) {
                            maxKos = strService.getHighestKodSijilRendah();
                            LOG.info("-------maxKos--------" + maxKos);
                            if (maxKos == null) {
                                LOG.info("------Lalu Max Kos Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                mohonStrata.setPerakuanKosRendahNoSijil("1");
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            } else if (maxKos != null) {
                                LOG.info("------Lalu MAx Kos Tidak Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                maxKosSijilRendah = maxKos.getPerakuanKosRendahNoSijil();
                                LOG.info("-------maxKosSijilRendah------" + maxKosSijilRendah);
                                int IntMaxKos = Integer.parseInt(maxKosSijilRendah) + 1;
                                LOG.info("-------IntMaxKos------" + IntMaxKos);
                                mohonStrata.setPerakuanKosRendahNoSijil(Integer.toString(IntMaxKos));
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            }
                        }

                        t.add("STRSLulusBgnKosRendah_NS.rdf");
                        t2.add("SLKR");

                        t.add("STRBHKosRendah_NS.rdf");
                        t2.add("KKR");

                        comm.reportGen(mohon, t, t2);



                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakBgnKosRendah_MLK.rdf");
                        t2.add("STKR");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakBgnKosRendah_NS.rdf");
                        t2.add("STKR");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PKBK")) {
            LOG.info("-------LALU DI PKBK-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    LOG.info("------Lalu L-------");
                    if (kodNegeri.equals("04")) {

                        LOG.info("-------ID MOHON---------" + mohon.getIdPermohonan());
                        LOG.info("-------mohonStrata------" + mohonStrata);

                        mohonIdStrata = strService.findID(mohon.getIdPermohonan());
                        if (mohonIdStrata.getPerakuanKosRendahNoSijil() == null) {
                            maxKos = strService.getHighestKodSijilRendah();
                            LOG.info("-------maxKos--------" + maxKos);
                            if (maxKos == null) {
                                LOG.info("------Lalu Max Kos Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                mohonStrata.setPerakuanKosRendahNoSijil("1");
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            } else if (maxKos != null) {
                                LOG.info("------Lalu MAx Kos Tidak Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                maxKosSijilRendah = maxKos.getPerakuanKosRendahNoSijil();
                                LOG.info("-------maxKosSijilRendah------" + maxKosSijilRendah);
                                int IntMaxKos = Integer.parseInt(maxKosSijilRendah) + 1;
                                LOG.info("-------IntMaxKos------" + IntMaxKos);
                                mohonStrata.setPerakuanKosRendahNoSijil(Integer.toString(IntMaxKos));
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            }
                        }

                        t.add("STRSLulusBngnKhas_MLK.rdf");
                        t2.add("SLBK");

                        t.add("STRBHBngnKhas_MLK.rdf");
                        t2.add("KBK");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        LOG.info("------Lalu Kod NS-------");
                        LOG.info("-------ID MOHON---------" + mohon.getIdPermohonan());
                        LOG.info("-------mohonStrata------" + mohonStrata);

                        mohonIdStrata = strService.findID(mohon.getIdPermohonan());
                        if (mohonIdStrata.getPerakuanKosRendahNoSijil() == null) {
                            maxKos = strService.getHighestKodSijilRendah();
                            LOG.info("-------maxKos--------" + maxKos);
                            if (maxKos == null) {
                                LOG.info("------Lalu Max Kos Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                mohonStrata.setPerakuanKosRendahNoSijil("1");
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            } else if (maxKos != null) {
                                LOG.info("------Lalu MAx Kos Tidak Null-------");
                                mohonStrata = permohonanStrataDAO.findById(mohonIdStrata.getIdStrata());
                                maxKosSijilRendah = maxKos.getPerakuanKosRendahNoSijil();
                                LOG.info("-------maxKosSijilRendah------" + maxKosSijilRendah);
                                int IntMaxKos = Integer.parseInt(maxKosSijilRendah) + 1;
                                LOG.info("-------IntMaxKos------" + IntMaxKos);
                                mohonStrata.setPerakuanKosRendahNoSijil(Integer.toString(IntMaxKos));
                                mohonStrata.setPerakuanKosRendahTarikhKeluar(new Date());
                                permohonanStrataDAO.saveOrUpdate(mohonStrata);
                            }
                        }

                        t.add("STRSLulusBngnKhas_NS.rdf");
                        t2.add("SLBK");

                        t.add("STRBHBngnKhas_NS.rdf");
                        t2.add("KBK");

                        comm.reportGen(mohon, t, t2);



                    }
                } else {
                    if (kodNegeri.equals("04")) {
                        t.add("STRSTolakBngnKhas_MLK.rdf");
                        t2.add("STBK");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRSTolakBngnKhas_NS.rdf");
                        t2.add("STBK");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("RBHS")) {
            LOG.info("-------LALU DI RBHS-----------");
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    // if keputusan/perakuan Lulus
                    if (kodNegeri.equals("04")) {

                        t.add("STRSLulusRayuanBayaranHMStrata_MLK.rdf");
                        t2.add("RBHS1");
                        comm.reportGen(mohon, t, t2);
                    } else {

                        t.add("STRSLulusRayuanBayaranHMStrata_NS.rdf");
                        t2.add("RBHS1");
                        comm.reportGen(mohon, t, t2);
                    }
                } else {
                    // if keputusan/perakuan Tolak
                    if (kodNegeri.equals("04")) {

                        t.add("STRSTolakRayuanBayaranHMStrata_MLK.rdf");
                        t2.add("RBHS1");
                        comm.reportGen(mohon, t, t2);
                    } else {

                        t.add("STRSTolakRayuanBayaranHMStrata_NS.rdf");
                        t2.add("RBHS2");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        FasaPermohonan mohonFasa = new FasaPermohonan();
        Permohonan mohon = context.getPermohonan();


        if (mohon.getKodUrusan().getKod().equals("PPRUS")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            if (mohonFasa.getKeputusan() != null) {
                if (mohonFasa.getKeputusan().getKod().equals("T")) {
                    context.addMessage(" - Urusan telah selesai. ");

                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("PKKR")) {
            context.addMessage(" - Urusan telah selesai. ");
        }

        return proposedOutcome;
    }

    @Override
    public void afterComplete(StageContext context) {
        Permohonan mohon = context.getPermohonan();
        Pengguna peng = context.getPengguna();
        FasaPermohonan mohonFasa = new FasaPermohonan();
//        if (mohon.getKodUrusan().getKod().equals("RBHS")) {
//            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
//            if (mohonFasa.getKeputusan() != null) {
//                if (mohon.getPermohonanSebelum() != null) {
//                    LOG.info("----id permohonan SBLM----:" + mohon.getPermohonanSebelum().getIdPermohonan());
//                    LOG.info("----id permohonan penguna----:" + peng);
//                    LOG.info("----Re-initiating id permohonan----:");
//                    initiateTask(mohon.getPermohonanSebelum(), peng);
//                    try {
//                        LOG.info("----skip the stage----:");
//                        skipStage(mohon.getPermohonanSebelum());
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        } else {
        throw new UnsupportedOperationException("Not supported yet.");
        //}
    }

    public void initiateTask(Permohonan permohonanSBLM, Pengguna pengguna) {
        LOG.info("----Re-initiate Task to PBBS----:");

        LOG.info("----PBBS Id Mohon----:" + permohonanSBLM.getIdPermohonan());
        LOG.info("----penguna----:" + pengguna);

        try {
            if (permohonanSBLM.getKodUrusan().getKePTG() == 'Y') {
                WorkFlowService.initiateTask(permohonanSBLM.getKodUrusan().getIdWorkflow(),
                        permohonanSBLM.getIdPermohonan(), permohonanSBLM.getCawangan().getKod() + ",00", pengguna.getIdPengguna(),
                        permohonanSBLM.getKodUrusan().getNama());
            } else if (permohonanSBLM.getKodUrusan().getKePTG() == 'T') {
                WorkFlowService.initiateTask(permohonanSBLM.getKodUrusan().getIdWorkflow(),
                        permohonanSBLM.getIdPermohonan(), permohonanSBLM.getCawangan().getKod(), pengguna.getIdPengguna(),
                        permohonanSBLM.getKodUrusan().getNama());
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        LOG.info("----Task Reinitiated Successfully----:");
    }

    public String skipStage(Permohonan permohonan) throws WorkflowException {
        LOG.info("----SKIP STAGE----:");

        ctxOnBehalf = WorkFlowService.authenticate("ptstrata1");
        if (ctxOnBehalf != null) {
            LOG.info("----ctxOnBehalf----:" + ctxOnBehalf.getUser());
            LOG.info("----ID Permohonan----:" + permohonan.getIdPermohonan());
            List<Task> l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
            LOG.info("----1----Task FOund(size)----:" + l.size());
            if (l.isEmpty()) {
                try {
                    Thread.sleep(5000);
                    l = WorkFlowService.queryTasksByIdMohon(ctxOnBehalf, permohonan.getIdPermohonan());
                } catch (Exception ex) {
                    LOG.error(ex);
                }
            }
            LOG.info("----2----Task FOund (size)----:" + l.size());
            for (Task t : l) {
                stageId = t.getSystemAttributes().getStage();
                taskId = t.getSystemAttributes().getTaskId();
                try {
                    WorkFlowService.acquireTask(taskId, ctxOnBehalf);
                    LOG.info("----Claim Found Task----:" + taskId);
                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxOnBehalf, "TB2");


                    LOG.info("----current stage id ----:" + stageId);
                    LOG.info("----next stage----:" + nextStage);
                    LOG.info("----Tugasan dihantar ke----:" + nextStage);
                } catch (StaleObjectException ex) {
                    java.util.logging.Logger.getLogger(JanaReportValidator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return nextStage;
    }

    @Override
    public void afterPushBack(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return proposedOutcome;
    }

    public IWorkflowContext getCtxOnBehalf() {
        return ctxOnBehalf;
    }

    public void setCtxOnBehalf(IWorkflowContext ctxOnBehalf) {
        this.ctxOnBehalf = ctxOnBehalf;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the maxKosSijilRendah
     */
    public String getMaxKosSijilRendah() {
        return maxKosSijilRendah;
    }

    /**
     * @param maxKosSijilRendah the maxKosSijilRendah to set
     */
    public void setMaxKosSijilRendah(String maxKosSijilRendah) {
        this.maxKosSijilRendah = maxKosSijilRendah;
    }

    /**
     * @return the mohonIdStrata
     */
    public PermohonanStrata getMohonIdStrata() {
        return mohonIdStrata;
    }

    /**
     * @param mohonIdStrata the mohonIdStrata to set
     */
    public void setMohonIdStrata(PermohonanStrata mohonIdStrata) {
        this.mohonIdStrata = mohonIdStrata;
    }

    /**
     * @return the maxKos
     */
    public PermohonanStrata getMaxKos() {
        return maxKos;
    }

    /**
     * @param maxKos the maxKos to set
     */
    public void setMaxKos(PermohonanStrata maxKos) {
        this.maxKos = maxKos;
    }

    /**
     * @return the kosSijilRendahExist
     */
    public PermohonanStrata getKosSijilRendahExist() {
        return kosSijilRendahExist;
    }

    /**
     * @param kosSijilRendahExist the kosSijilRendahExist to set
     */
    public void setKosSijilRendahExist(PermohonanStrata kosSijilRendahExist) {
        this.kosSijilRendahExist = kosSijilRendahExist;
    }
}
