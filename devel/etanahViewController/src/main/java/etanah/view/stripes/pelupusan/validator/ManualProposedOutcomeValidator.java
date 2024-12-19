/**
 * Manually propose an outcome other than the default 'APPROVE' for selected
 * stages
 *
 * @author Mohd Hairudin Mansur
 * @version 21042011
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import java.util.List;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

import org.apache.log4j.Logger;

import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.service.PelupusanService;
import etanah.service.common.TaskDebugService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import org.apache.commons.lang.StringUtils;

public class ManualProposedOutcomeValidator implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    private TaskDebugService ts;
    private String stage;
    private static final Logger LOG = Logger.getLogger(ManualProposedOutcomeValidator.class);

    @Override
    public void afterComplete(StageContext arg0) {
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {
        String outcome = "";

        try {
            if (conf.getProperty("kodNegeri").equals("04")) {
                outcome = checkstageIDMelaka(context);
            } else {
                outcome = checkstageID(context);
            }
        } catch (WorkflowException ex) {
            LOG.error(ex.getMessage());
            return null;
        }

        proposedOutcome = outcome;
        LOG.info("proposed outcome: " + proposedOutcome);
        return proposedOutcome;
//        return null;
    }

    /**
     * check stage ID NS
     *
     * @param context
     * @return
     * @throws WorkflowException
     * @author Mohd Hairudin Mansur
     * @version 25042011
     */
    private String checkstageID(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        String urusan = permohonan.getKodUrusan().getKod();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        IWorkflowContext ctxW = null;

        for (Task t : l) {
            setStage(t.getSystemAttributes().getStage());
            LOG.info(getStage());

            if (getStage().contentEquals("sedia_surat_peringatan")) {
                if (urusan.equalsIgnoreCase("PBMT")) {
                    value = "L";
                } else {
                    value = "HP";
                }
                return value;
            }

            if (getStage().contentEquals("rekod_keputusan_mmk")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (getStage().contentEquals("keputusan_permohonan")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (urusan.equalsIgnoreCase("PLPS")) {
                if (getStage().contentEquals("ttgn_surat_keputusan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBMT")) {
                if (getStage().contentEquals("sedia_surat_tolak")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("cetak_kertas_makluman")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("terima_keputusan_mmk")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
                if (getStage().contentEquals("sedia_surat_tolak_MMK")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("sedia_surat_peringatan")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("bayaran_tambahan2")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("bayaran_tambahan3")) {
                    value = "L";
                    return value;
                }

                if (getStage().contentEquals("semak_chart_kpsn")) {
                    HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
                    mohonHakmilik = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    if (mohonHakmilik.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }

                if (getStage().contentEquals("semak_chart_kpsn_L")) {
                    value = "APPROVE";
                    if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
                        List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                        listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohon(permohonan.getIdPermohonan());
                        String idMohonKelompok = new String();
                        if (listMohonKelompok.size() > 0) {
                            PermohonanKelompok pk = new PermohonanKelompok();
                            pk = listMohonKelompok.get(0);
                            if (pk.getPermohonanInduk().getCatatan().equals("K")) {
                                permohonan.setInfoAudit(disLaporanTanahService.findAudit(permohonan, "update", context.getPengguna()));
                                permohonan.setStatus("SD");
                                disLaporanTanahService.getPelupusanService().simpanPermohonan(permohonan);
                                LOG.info("status permohonan:" + permohonan.getStatus());
                                idMohonKelompok = pk.getPermohonanInduk().getIdPermohonan();
                            }
                        }

                        if (StringUtils.isNotBlank(idMohonKelompok)) {
                            List<PermohonanKelompok> listKelompok = new ArrayList<PermohonanKelompok>();
                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(idMohonKelompok);
                            boolean statusKelompok = true;
                            if (listKelompok.size() > 0) {
                                for (PermohonanKelompok pkk : listKelompok) {
                                    if (pkk.getPermohonan().getStatus() != null) {
                                        if (!pkk.getPermohonan().getStatus().equals("SD")) {
                                            statusKelompok = false;
                                        }
                                    }
                                }
                            }
                            if (statusKelompok) {
                                Permohonan mohon = disLaporanTanahService.getPermohonanDAO().findById(idMohonKelompok);
                                if (mohon != null) {
                                    mohon.setInfoAudit(disLaporanTanahService.findAudit(mohon, "update", context.getPengguna()));
                                    mohon.setStatus("AK");
                                    disLaporanTanahService.getPelupusanService().simpanPermohonan(mohon);
                                    LOG.info("status permohonan kelompok:" + mohon.getStatus());
                                }
                            }
                        }
                    }
                }

                if (getStage().contentEquals("rekod_keputusan_MMK")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{permohonan.getIdPermohonan(), "rekod_keputusan_MMK"}, 0);
                    if (mohonFasa != null && mohonFasa.getKeputusan() != null && !StringUtils.isEmpty(mohonFasa.getKeputusan().getKod())) {
                        if (mohonFasa.getKeputusan().getKod().equals("1M")) {
                            value = "TF";
                            return value;
                        } else {
                            List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
//                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "T");
                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(permohonan.getIdPermohonan());
                            if (listMohonKelompok.size() > 0) {
                                Permohonan p = new Permohonan();
                                for (PermohonanKelompok pk : listMohonKelompok) {
                                    p = pk.getPermohonan();
                                    if (p != null) {
                                        if (p.getSenaraiHakmilik().size() > 0) {
                                            HakmilikPermohonan hp = p.getSenaraiHakmilik().get(0);

                                            if (hp != null) {
                                                InfoAudit ia = new InfoAudit();
                                                ia = p.getInfoAudit();
                                                if (hp.getKeputusan() != null) {
                                                    if (hp.getKeputusan().getKod().equals("T")) {
                                                        p.setStatus("AK");
                                                    }
                                                    p.setKeputusan(hp.getKeputusan());
                                                    p.setPermohonanSebelum(permohonan);

                                                }
                                                p.setInfoAudit(ia);
                                                pelupusanService.simpanPermohonan(p);
                                            }
                                        }
                                        if (p.getKeputusan().getKod().equals("T")) {
                                            //Push BPEL to stage terima_keputusan_mmk
                                            context.addMessage(" : Permohonan Bertindih/Berkelompok untuk kes lulus diteruskan. Bagi kes tolak sila teruskan dengan permohonan individu");
                                            LOG.info("Push id mohon yang tolak ke stage terima_keputusan");
                                            String taskId = new String();
                                            String nextStage = new String();
                                            FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                            fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(p.getIdPermohonan(), "kemasukan");
                                            if (pk.getJenisKelopok().equals("K")) {
                                                ctxW = WorkFlowService.authenticate(fasaPermohonan.getIdPengguna());
                                            } else {
                                                ctxW = WorkFlowService.authenticate(p.getInfoAudit().getDikemaskiniOleh().getIdPengguna());
                                            }

                                            Map m = ts.traceTask(p.getIdPermohonan());
                                            taskId = (String) m.get("taskID");
                                            LOG.info("Task Id :" + taskId);
                                            if (StringUtils.isNotBlank(taskId)) {
                                                try {
//                                                    WorkFlowService.acquireTask(taskId, ctxW);
                                                    nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "L");
                                                } catch (StaleObjectException ex) {
                                                    java.util.logging.Logger.getLogger(ManualProposedOutcomeValidator.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }

                                        }
                                    }
                                }
                                value = "L"; //Push grouping yg lulus to stage lulus bersyarat
                            } else {
                                if (permohonan.getSenaraiHakmilik().size() > 0) {
                                    HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);

                                    if (hp != null) {
                                        InfoAudit ia = new InfoAudit();
                                        ia = permohonan.getInfoAudit();
                                        permohonan.setKeputusan(hp.getKeputusan());
                                        permohonan.setInfoAudit(ia);
                                        pelupusanService.simpanPermohonan(permohonan);
                                    }
                                }
                                if (permohonan.getKeputusan().getKod().equals("L")) {
                                    value = "L";
                                } else {
                                    value = "T";
                                }

                            }
                            return value;
                        }
                    }
                }
                if (getStage().contentEquals("rekod_keputusan_MMK2")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = (FasaPermohonan) disLaporanTanahService.findObject(mohonFasa, new String[]{permohonan.getIdPermohonan(), "rekod_keputusan_MMK2"}, 0);
                    if (mohonFasa != null && mohonFasa.getKeputusan() != null && !StringUtils.isEmpty(mohonFasa.getKeputusan().getKod())) {
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }
                }
                if (getStage().contentEquals("semak_precomp")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("semak_lot_index")) {
                    value = "L";
                    return value;
                }
                if (getStage().contentEquals("g_hantar_pu")) {
                    List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
                    listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "K");
                    if (listMohonKelompok.size() > 0) {

                        Permohonan p = new Permohonan();
                        for (PermohonanKelompok pk : listMohonKelompok) {
                            p = pk.getPermohonan();
                            if (p != null) {
                                if (p.getKeputusan().getKod().equals("L")) {
                                    //Push BPEL to stage terima_keputusan_mmk
                                    InfoAudit ia = new InfoAudit();
                                    ia = p.getInfoAudit();
                                    p.setStatus("AK");
                                    p.setInfoAudit(ia);
                                    pelupusanService.simpanPermohonan(p);
                                    context.addMessage(" : Permohonan Berkelompok telah selesai. Sila teruskan permohonan individu");
                                    LOG.info("Push id mohon yang lulus ke stage terima pa b1");
                                    String taskId = new String();
                                    String nextStage = new String();
                                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                    fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "qt_ft");
                                    ctxW = WorkFlowService.authenticate(fasaPermohonan.getIdPengguna());
                                    Map m = ts.traceTask(p.getIdPermohonan());
                                    taskId = (String) m.get("taskID");
                                    LOG.info("Task Id :" + taskId);
                                    if (StringUtils.isNotBlank(taskId)) {
                                        try {
                                            WorkFlowService.acquireTask(taskId, ctxW);
                                            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "L");
                                        } catch (StaleObjectException ex) {
                                            java.util.logging.Logger.getLogger(ManualProposedOutcomeValidator.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }
                            }
                        }
                        value = "L"; // Completed kelompok
                    }
                    return value;
                }
            } else {
                if (getStage().contentEquals("cetak_kertas_makluman")) {
                    value = "PS";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("RAYL")) {
                if (getStage().contentEquals("12RekodKeputusan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("RAYK")) {
                if (getStage().contentEquals("12RekodKeputusan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (getStage().contentEquals("g_charting_keputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                } else {
                    if (getStage().contentEquals("g_charting_keputusan")) {
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "014");
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            value = "L";
                        } else {
                            value = "T";
                        }
                        return value;
                    }
                }
            }

            if (urusan.equalsIgnoreCase("PBPTD")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "07Sdp");
                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "15Keputusan");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("PL")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "15Keputusan");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
                if (getStage().contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "perakuan_draf_rencana_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_surat_cetak_tlk")) {
                    value = "L";
                    return value;
                }

            }

            if (urusan.equalsIgnoreCase("PBPTG")) {

                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "07SDP");
                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23Keputusan");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("PL")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23Keputusan");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
                if (getStage().contentEquals("semak_surat_cetak_tlk")) {
                    value = "L";
                    return value;
                }

            }

            if (urusan.equalsIgnoreCase("PBMMK")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "07SDP");

                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("PL")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
                if (getStage().contentEquals("semak_surat_cetak_tlk")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PLPTD")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "05SyorSDF");

                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "16Kptsn");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "16Kptsn");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
                if (getStage().contentEquals("semak_surat_cetak_tlk")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("WMRE")) {
                LOG.info("msuk cni");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (getStage().contentEquals("g_charting_keputusan")) {
                        String idAliranMhonFasa = "18TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        LOG.info("value WMRE" + value);
                        return value;
                    }

                }

            }
            if (urusan.equalsIgnoreCase("MMRE")) {
                LOG.info("msuk cni");
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (getStage().contentEquals("g_charting_keputusan")) {
                        String idAliranMhonFasa = "17TrmKptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        LOG.info("value MMRE" + value);
                        return value;
                    }

                }

            }

            if (urusan.equalsIgnoreCase("BMRE")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    LOG.info("msuk cni1");
                    if (getStage().contentEquals("g_charting_keputusan")) {
                        String idAliranMhonFasa = "18TrmKptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        LOG.info("kpsn" + value);
                        return value;

                    }

                }

            }

            if (urusan.equalsIgnoreCase("LPSP")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "07Sdp");

                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmMklmnKptsn");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("PL")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmMklmnKptsn");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
//                if (stage.contentEquals("semak_surat_cetak_tlk")) {
//                    value = "L";
//                    return value;
//                }
            }

            if (urusan.equalsIgnoreCase("PTPBP")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();

                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "07SDP");

                    if (mohonFasa == null) {
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
                        value = mohonFasa.getKeputusan().getKod();
                    } else {
                        if (mohonFasa.getKeputusan().getKod().equals("PL")) {
                            value = mohonFasa.getKeputusan().getKod();
                        } else {
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
                            value = mohonFasa.getKeputusan().getKod();
                        }
                    }
                    return value;
                }
//                if (stage.contentEquals("semak_surat_cetak_tlk")) {
//                    value = "L";
//                    return value;
//                }
            }

            if (urusan.equalsIgnoreCase("PLPTD")) {
                if (getStage().contentEquals("20SmkShkandanCtk")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }
        }
        return value;
    }

    /**
     * check stage ID Melaka
     *
     * @param context
     * @return
     * @throws WorkflowException
     * @author afham
     * @modify Mohd Hairudin Mansur 20052011
     */
    private String checkstageIDMelaka(StageContext context) throws WorkflowException {
        String value = "";
        Permohonan permohonan = context.getPermohonan();
        String urusan = permohonan.getKodUrusan().getKod();
        List<Task> l = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());
        IWorkflowContext ctxW = null;

        for (Task t : l) {
            setStage(t.getSystemAttributes().getStage());
            LOG.info(getStage());

            if (getStage().contentEquals("pindaan_draf_mmkn")) {
                value = "SN";
                return value;
            }

            if (getStage().contentEquals("rekod_keputusan_mmkn")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (getStage().contentEquals("RekodMMKN")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (getStage().contentEquals("RekodKeputusanMMK")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (getStage().contentEquals("rekod_keputusan_MMKN_PTG")) {
                HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                value = hp.getKeputusan().getKod();
                return value;

            }

            if (getStage().contentEquals("rekod_keputusan_MMKN_PTG2")) {
                HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                value = hp.getKeputusan().getKod();
                return value;
            }

            if (getStage().contentEquals("arahan_tugas")) {
                value = permohonan.getKeputusan().getKod();
                LOG.info(value);
                return value;
            }
            if (getStage().contentEquals("rekod_keputusan_mmkn2")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            /*
             * if (stage.contentEquals("salinan_surat_keputusan1")) { value =
             * permohonan.getKeputusan().getKod(); return value; }
             *
             * if (stage.contentEquals("salinan_surat_keputusan2")) { value =
             * permohonan.getKeputusan().getKod(); return value;
             }
             */
            if (urusan.equalsIgnoreCase("PLPS")) {
                if (getStage().contentEquals("g_charting_keputusan")) {
                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    value = hp.getKeputusan().getKod();
//                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }
//            if (urusan.equalsIgnoreCase("PBRZ")) {
//                if (getStage().contentEquals("rekod_keputusan_MMKN_PTG")||getStage().contentEquals("rekod_keputusan_MMKN_PTG2")) {
//                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
//                    value = hp.getKeputusan().getKod();
////                    value = permohonan.getKeputusan().getKod();
//                    return value;
//                }
//            }
            if (urusan.equalsIgnoreCase("PPRU")) {
//                if (getStage().contentEquals("g_charting_keputusan")) {
//                    String idAliranMhonFasa = new String();
//                    idAliranMhonFasa = "rekod_keputusan_mmkn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
//                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
//                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
//                    if (mohonFasaKpsn != null) {
//                        value = mohonFasaKpsn.getKeputusan().getKod();
//                    }
//                    return value;
                if (getStage().contentEquals("g_charting_keputusan")) {
                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    value = hp.getKeputusan().getKod();
                    return value;
                    
                } else if (getStage().contentEquals("semak_charting_kpsn")) {
//                    String idAliranMhonFasa = new String();
//                    idAliranMhonFasa = "rekod_keputusan_mmkn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
//                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
//                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
//                    if (mohonFasaKpsn != null) {
//                        value = mohonFasaKpsn.getKeputusan().getKod();
//                    }
//                    return value;
                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    value = hp.getKeputusan().getKod();
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PPBB")) {
                if (getStage().contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = new String();
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    idAliranMhonFasa = "perakuan_draf_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasaKpsn != null) {
                        if (mohonFasaKpsn.getKeputusan().getKod().equalsIgnoreCase("P2")) {
                            idAliranMhonFasa = "perakuan_rencana_jkbb"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                            mohonFasa = new FasaPermohonan();
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        } else if (mohonFasaKpsn.getKeputusan().getKod().equalsIgnoreCase("P1")) {
                            idAliranMhonFasa = "perakuan_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                            mohonFasa = new FasaPermohonan();
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        }
                    }
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("arahan_tugasan6")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                } //                else if (stage.contentEquals("rekod_keputusan_jkbb")) {
                //                    value = "L";
                //                    return value;
                //                } 
                else if (getStage().contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("perakuan_draf_rencana_km")) {
                    String idAliranMhonFasa = new String();
                    idAliranMhonFasa = "perakuan_draf_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasaKpsn != null) {
                        value = mohonFasaKpsn.getKeputusan().getKod();
                    }
                    return value;
                } else if (getStage().contentEquals("rekod_keputusan_jkbb")) {
                    String idAliranMhonFasa = new String();
                    idAliranMhonFasa = "perakuan_draf_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasaKpsn != null) {
                        value = mohonFasaKpsn.getKeputusan().getKod();
                    }
                    return value;
                } else if (getStage().contentEquals("rekod_keputusan_km")) {
                    String idAliranMhonFasa = new String();
                    idAliranMhonFasa = "perakuan_draf_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasaKpsn != null) {
                        value = mohonFasaKpsn.getKeputusan().getKod();
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBPTG")) {
                if (getStage().contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBPTD")) {
                if (getStage().contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "perakuan_draf_rencana_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("MMMCL")) {
                if (getStage().contentEquals("semak_charting_kpsn")) {
                    String idAliranMhonFasa = "perakuan_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PTGSA")) {
                if (getStage().contentEquals("14ePerakuPindaan") || getStage().contentEquals("20CetakHantarWarta") || getStage().contentEquals("13bPindaanPTG") || getStage().contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PPTR")) {
                if (getStage().contentEquals("14ePerakuPindaan") || getStage().contentEquals("semak_surat_tolak") || getStage().contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (getStage().contentEquals("pindaan_draf_mmkn")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("LPSP")) {
                if (getStage().contentEquals("16SemakSurat")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("14ePerakuPindaan") || getStage().contentEquals("semak_surat_tolak") || getStage().contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (getStage().contentEquals("tandatangan_surat_kelulusan")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("14ePerakuPindaan") || getStage().contentEquals("semak_surat_tolak") || getStage().contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("RAYT")) {
                if (getStage().contentEquals("semak_charting_keputusan")) {
                    String idAliranMhonFasa = "012_KeputusanMMKN"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PRMP")) {
                if (getStage().contentEquals("semak_charting_keputusan")) {
                    String idAliranMhonFasa = "rekod_keputusan_mmkn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    //FasaPermohonan mohonFasa = new FasaPermohonan();
                    //mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    //value = mohonFasa.getKeputusan().getKod();
                    //return value;
                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    value = hp.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("MLCRG")) {
                if (getStage().contentEquals("029_Hantarsurat")) {
                    String idAliranMhonFasa = "024_KeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("MPJLB")) {
                if (getStage().contentEquals("006_Semak")) {
                    String idAliranMhonFasa = "006_Semak"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    //FasaPermohonan mohonFasa = new FasaPermohonan();
                    //mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    //value = mohonFasa.getKeputusan().getKod();
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PHLP")) {
                if (getStage().contentEquals("23Smk")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23Smk");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else if (mohonFasa.getKeputusan().getKod().equals("T")) {
                        value = "T";
                    }
//                    else {
//                        value = "T";
//                    }
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PBGSA")) {
                if (getStage().contentEquals("35aSemakan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                } else if (urusan.equalsIgnoreCase("PBGSA")) {
                    if (getStage().contentEquals("semak_charting_keputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                }
            }

            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (getStage().contentEquals("perakuan_ptd")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "perakuan_ptd");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBHL")) {
                if (getStage().contentEquals("18bSmkn")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "A03BuatPerintah"); //17BtSiasatan
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("BMBT")) {
                if (getStage().contentEquals("pindaan_draf_MMKN_PTG") || getStage().contentEquals("semak_peraku_MMKN_PTD")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_charting_keputusan")) {
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PJBTR")) {
                if (getStage().contentEquals("jana_surat_tolak_ringkas") || getStage().contentEquals("pindaan_draf_MMKN_PTG") || getStage().contentEquals("semak_charting_kpsn") || getStage().contentEquals("semak_charting_kemaskini") || getStage().contentEquals("terima_peraku_pindaan_D") || getStage().contentEquals("kemasukan_rayuan") || getStage().contentEquals("semak_lprn_tanah_D1")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("rekod_keputusan_MMKN_PTG")) {
                    String idAliranMhonFasa = "rekod_keputusan_MMKN_PTG"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("g_charting_keputusan")) {
                    String idAliranMhonFasa = "terima_surat_kpsn_PTD"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PBMT")) {
                if (getStage().contentEquals("tolak_ringkas")) {
                    String idAliranMhonFasa = "tolak_ringkas"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasa.getKeputusan().getKod().equals("Y")) {
                        InfoAudit ia = new InfoAudit();
                        ia = permohonan.getInfoAudit();
                        ia.setDiKemaskiniOleh(context.getPengguna());
                        ia.setTarikhKemaskini(new java.util.Date());
                        permohonan.setKeputusan(disLaporanTanahService.getKodKeputusanDAO().findById("T"));
                        permohonan.setInfoAudit(ia);
                        pelupusanService.simpanPermohonan(permohonan);
                    }

                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("semak_charting_kpsn_T1")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("pindaan_draf_MMKN_PTG")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("rekod_keputusan_MMKN_PTG")) {
                    List<PermohonanKelompok> listMohonKelompok = new ArrayList<PermohonanKelompok>();
//                            listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonIndukJenisKelompok(permohonan.getIdPermohonan(), "T");
                    listMohonKelompok = disLaporanTanahService.getPelupusanService().findMohonKelompokByIdMohonInduk(permohonan.getIdPermohonan());
                    if (listMohonKelompok.size() > 0) {
                        Permohonan p = new Permohonan();
                        for (PermohonanKelompok pk : listMohonKelompok) {
                            p = pk.getPermohonan();
                            if (p != null) {
                                if (p.getSenaraiHakmilik().size() > 0) {
                                    HakmilikPermohonan hp = p.getSenaraiHakmilik().get(0);

                                    if (hp != null) {
                                        InfoAudit ia = new InfoAudit();
                                        ia = p.getInfoAudit();
                                        if (hp.getKeputusan() != null) {
                                            if (hp.getKeputusan().getKod().equals("T")) {
                                                p.setStatus("AK");
                                            }
                                            p.setKeputusan(hp.getKeputusan());
                                            p.setPermohonanSebelum(permohonan);

                                        }
                                        p.setInfoAudit(ia);
                                        pelupusanService.simpanPermohonan(p);
                                    }
                                }
                                if (p.getKeputusan().getKod().equals("T")) {
                                    //Push BPEL to stage terima_keputusan_mmk
                                    context.addMessage(" : Permohonan Bertindih/Berkelompok untuk kes lulus diteruskan. Bagi kes tolak sila teruskan dengan permohonan individu");
                                    LOG.info("Push id mohon yang tolak ke stage terima_keputusan");
                                    String taskId = new String();
                                    String nextStage = new String();
                                    FasaPermohonan fasaPermohonan = new FasaPermohonan();
                                    fasaPermohonan = disLaporanTanahService.getPlpservice().findMohonFasaByIdMohonIdPengguna(p.getIdPermohonan(), "kemasukan");
                                    if (pk.getJenisKelopok().equals("K")) {
                                        ctxW = WorkFlowService.authenticate(fasaPermohonan.getIdPengguna());
                                    } else {
                                        ctxW = WorkFlowService.authenticate(p.getInfoAudit().getDikemaskiniOleh().getIdPengguna());
                                    }

                                    Map m = ts.traceTask(p.getIdPermohonan());
                                    taskId = (String) m.get("taskID");
                                    LOG.info("Task Id :" + taskId);
                                    if (StringUtils.isNotBlank(taskId)) {
                                        try {
//                                                    WorkFlowService.acquireTask(taskId, ctxW);
                                            nextStage = WorkFlowService.updateTaskOutcome(taskId, ctxW, "L");
                                        } catch (StaleObjectException ex) {
                                            java.util.logging.Logger.getLogger(ManualProposedOutcomeValidator.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                }
                            }
                        }
                        value = "L"; //Push grouping yg lulus to stage lulus bersyarat
                    } else {
                        if (permohonan.getSenaraiHakmilik().size() > 0) {
                            HakmilikPermohonan hp = permohonan.getSenaraiHakmilik().get(0);

                            if (hp != null) {
                                InfoAudit ia = new InfoAudit();
                                ia = permohonan.getInfoAudit();
                                permohonan.setKeputusan(hp.getKeputusan());
                                permohonan.setInfoAudit(ia);
                                pelupusanService.simpanPermohonan(permohonan);
                            }
                        }
//                                if (permohonan.getKeputusan().getKod().equals("L")) {
//                                    value = "L";
//                                } else {
//                                    value = "T";
//                                }
                        value = "";

                    }
                    return value;
                } else if (getStage().contentEquals("semak_charting_keputusan")) {
                    //value = permohonan.getKeputusan().getKod();
                    HakmilikPermohonan hp = pelupusanService.findMohonHakmilikByIdMohonIdPengguna(permohonan.getIdPermohonan());
                    value = hp.getKeputusan().getKod();
                    return value;
                } else if (getStage().contentEquals("tndtgn_srt_tolak_MMK")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_precomp")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_peraku_MMKN_PTD3")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_peraku_MMKN_PTD")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_pindaan_pu_tmbhn2")) {
                    value = "L";
                    return value;
                } else if (getStage().contentEquals("semak_rekod_lot_index")) {
                    value = "L";
                    return value;
                }
            }

        }

        return value;
    }

    @Override
    public boolean beforeGenerateReports(StageContext arg0) {
        return true;
    }

    @Override
    public boolean beforeStart(StageContext arg0) {
        return true;
    }

    @Override
    public void onGenerateReports(StageContext arg0) {
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    /**
     * @return the stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(String stage) {
        this.stage = stage;
    }
}
