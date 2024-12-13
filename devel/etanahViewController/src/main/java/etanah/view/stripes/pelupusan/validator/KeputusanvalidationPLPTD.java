/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.validator;

import com.google.inject.Inject;
import etanah.model.FasaPermohonan;
import etanah.model.Pengguna;
import java.util.List;

import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.task.model.Task;

import org.apache.log4j.Logger;

import etanah.model.Permohonan;
import etanah.service.PelupusanService;
import etanah.view.strata.CommonService;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import etanah.workflow.WorkFlowService;
import java.util.ArrayList;

/**
 *
 * @author TPS
 */
public class KeputusanvalidationPLPTD implements StageListener {

    @Inject
    etanah.Configuration conf;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    CommonService comm;
    private String stage;
    private static final Logger LOG = Logger.getLogger(KeputusanvalidationPLPTD.class);

    @Override
    public void afterComplete(StageContext arg0) {
//        throw new UnsupportedOperationException("Not supported yet.");
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
        LOG.info(outcome);
        proposedOutcome = outcome;
        LOG.info("proposed outcome: " + proposedOutcome);
        return proposedOutcome;
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

        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);

            if (stage.contentEquals("sedia_surat_peringatan")) {
                value = "HP";
                return value;
            }

            if (stage.contentEquals("rekod_keputusan_mmk")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (stage.contentEquals("cetak_kertas_makluman")) {
                value = "PS";
                return value;
            }

            if (stage.contentEquals("keputusan_permohonan")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (urusan.equalsIgnoreCase("PLPS")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (stage.contentEquals("ttgn_surat_keputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                } else if (conf.getProperty("kodNegeri").equals("05")) {

                    if (stage.contentEquals("36TrmKptsnMMK")) {
                        String idAliranMhonFasa = "29TrmMklmnKptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }
                    else if (stage.contentEquals("53TrmKptsn")) {
                        String idAliranMhonFasa = "52Keputusan"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }
                }

            }

            if (urusan.equalsIgnoreCase("PPRU")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    // to do here
                } else if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("26TrmKptsndanSedSrt")) {
                        String idAliranMhonFasa = "20TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }
                }
            }

            if (urusan.equalsIgnoreCase("RAYL")) {
                if (stage.contentEquals("12RekodKeputusan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("RAYK")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (stage.contentEquals("12RekodKeputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                } else if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("15TrmKptsnArhSedSrt")) {
                        String idAliranMhonFasa = "09TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

                }

            }
          

            if (urusan.equalsIgnoreCase("RYKN")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("15TrmKptsnArhSedSrt")) {
                        String idAliranMhonFasa = "09TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            value = "L";
                        } else {
                            value = "T";
                        }
                        return value;
                    }

                }

            }
            
              if (urusan.equalsIgnoreCase("PLPTD")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("17TrmKptsn")) {
                        String idAliranMhonFasa = "16Kptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            value = "L";
                        } else {
                            value = "T";
                        }
                        return value;
                    }

                }

            }
              
            if (urusan.equalsIgnoreCase("RLPTG")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (stage.contentEquals("12RekodKeputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                } else if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("14TrmKptsndanArhSedSrtKptsn")) {
                        String idAliranMhonFasa = "08Keputusan"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);

                        if (mohonFasa != null) {
                            value = mohonFasa.getKeputusan().getKod();

                            if (!value.equals("TG")) {
                                return value;
                            } else {
                                String idAliranMhonFasa_1 = "13KeputusanPTG"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                                FasaPermohonan mohonFasa_1 = new FasaPermohonan();
                                mohonFasa_1 = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa_1);
                                value = mohonFasa_1.getKeputusan().getKod();
                                return value;
                            }

                        }

                    }

                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (conf.getProperty("kodNegeri").equals("04")) {
                    if (stage.contentEquals("g_charting_keputusan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                } else {
                    if (stage.contentEquals("g_charting_keputusan")) {
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
                if (stage.contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "perakuan_draf_rencana_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("16TrmKptsn")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "15Keputusan");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PLPTD")) {
                if (stage.contentEquals("20SmkShkandanCtk")) {
                    //value = permohonan.getKeputusan().getKod();
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "16Kptsn");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                } else if (stage.contentEquals("20CSmkShkandanCtkTolak")) {
                    //value = permohonan.getKeputusan().getKod();
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "16Kptsn");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBPTG")) {
                if (stage.contentEquals("terima_ulasan_teknikal")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("25TrmKptsn")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "23Keputusan");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBMMK")) {
                if (stage.contentEquals("terima_ulasan_teknikal")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
                if (stage.contentEquals("31TrmKptsnMMK")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmKptsnMMK");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }


            if (urusan.equalsIgnoreCase("PTPBP")) {
                if (stage.contentEquals("31TrmKptsn")) {
                    String idAliranMhonFasa = "25TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("LPSP")) {
                if (stage.contentEquals("31TrmKptsnMMK")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "25TrmMklmnKptsn");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PPTR")) {
                if (stage.contentEquals("25TrmKptsnMMK")) {
                    String idAliranMhonFasa = "19TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
                if (stage.contentEquals("g_charting_keputusan")) {
                    String idAliranMhonFasa = "19TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PRMMK")) {
                if (stage.contentEquals("30TrmKptsnMMK")) {
                    String idAliranMhonFasa = "21TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("RLPS")) {
                if (stage.contentEquals("13SmkShknCtk")) {
                    String idAliranMhonFasa = "09Kptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

                if (stage.contentEquals("10TrmKptsn")) {
                    String idAliranMhonFasa = "09Kptsn";
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }

            if (urusan.equalsIgnoreCase("PCRG")) {
                if (stage.contentEquals("g_charting_keputusan")) {
                    String idAliranMhonFasa = "34Keputusan"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PBGSA")) {
                if (stage.contentEquals("25MklmnKptsnMMK")) {
                    String idAliranMhonFasa = "19TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PTGSA")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("g_charting_keputusan")) {
                        String idAliranMhonFasa = "17TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }
                }
            }
            if (urusan.equalsIgnoreCase("MMRE")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("23TrmKptsnMMK")) {
                        String idAliranMhonFasa = "17TrmKptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

                }

            }
            if (urusan.equalsIgnoreCase("PBRZ")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("41TrmKptsnMMK")) {
                        String idAliranMhonFasa = "40TrmImbsMsyrtMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

                }

            }
            if (urusan.equalsIgnoreCase("PTMTA")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("12KlrkanPerintah")) {
                        String idAliranMhonFasa = "09JalankanPerbicaraan"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

                }

            }
            if (urusan.equalsIgnoreCase("WMRE")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("31TrmKptsnMMK")) {
                        String idAliranMhonFasa = "18TrmKptsnMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

                }

            }
            if (urusan.equalsIgnoreCase("BMRE")) {
                if (conf.getProperty("kodNegeri").equals("05")) {
                    if (stage.contentEquals("24TrmKptsnMMK")) {
                        String idAliranMhonFasa = "18TrmKptsn"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                        FasaPermohonan mohonFasa = new FasaPermohonan();
                        mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        value = mohonFasa.getKeputusan().getKod();
                        return value;
                    }

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

        for (Task t : l) {
            stage = t.getSystemAttributes().getStage();
            LOG.info(stage);

            if (stage.contentEquals("pindaan_draf_mmkn")) {
                value = "SN";
                return value;
            }

            if (stage.contentEquals("rekod_keputusan_mmkn")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (stage.contentEquals("RekodKeputusanMMK")) {
                value = permohonan.getKeputusan().getKod();
                return value;
            }

            if (stage.contentEquals("arahan_tugas")) {
                value = permohonan.getKeputusan().getKod();
                LOG.info(value);
                return value;
            }
            if (stage.contentEquals("rekod_keputusan_mmkn2")) {
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

            if (urusan.equalsIgnoreCase("PLPS") || urusan.equalsIgnoreCase("PPRU")) {
                if (stage.contentEquals("g_charting_keputusan")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PPBB")) {
                if (stage.contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = new String();
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    idAliranMhonFasa = "perakuan_draf_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasaKpsn = new FasaPermohonan();
                    mohonFasaKpsn = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    if (mohonFasaKpsn != null) {
                        if (mohonFasaKpsn.getKeputusan().getKod().equalsIgnoreCase("K4")) {
                            idAliranMhonFasa = "perakuan_rencana_jkbb"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                            mohonFasa = new FasaPermohonan();
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        } else if (mohonFasaKpsn.getKeputusan().getKod().equalsIgnoreCase("L4")) {
                            idAliranMhonFasa = "perakuan_rencana_km"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                            mohonFasa = new FasaPermohonan();
                            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                        }
                    }
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("arahan_tugasan6")) {
                    value = permohonan.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("rekod_keputusan_jkbb")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBPTG")) {
                if (stage.contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "semak_peraku"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PBPTD")) {
                if (stage.contentEquals("sedia_surat_lulustolak")) {
                    String idAliranMhonFasa = "perakuan_draf_rencana_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("pulang_wang_cagaran")) {
//                else if (stage.contentEquals("semakan_surat_pulang")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PTPBP")) {
                if (stage.contentEquals("33TerimaKpsnArahChart")) {
                    String idAliranMhonFasa = "27TerimaKeputusan"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }

            if (urusan.equalsIgnoreCase("MMMCL")) {
                if (stage.contentEquals("semak_charting_kpsn")) {
                    String idAliranMhonFasa = "perakuan_ptd"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }

            }
            if (urusan.equalsIgnoreCase("PTGSA")) {
                if (stage.contentEquals("14ePerakuPindaan") || stage.contentEquals("20CetakHantarWarta") || stage.contentEquals("13bPindaanPTG") || stage.contentEquals("semak_surat_tolak")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PPTR")) {
                if (stage.contentEquals("14ePerakuPindaan") || stage.contentEquals("semak_surat_tolak") || stage.contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (stage.contentEquals("pindaan_draf_mmkn")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("LPSP")) {
                if (stage.contentEquals("16SemakSurat")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("14ePerakuPindaan") || stage.contentEquals("semak_surat_tolak") || stage.contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (stage.contentEquals("tandatangan_surat_kelulusan")) {
                    String idAliranMhonFasa = "RekodKeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("14ePerakuPindaan") || stage.contentEquals("semak_surat_tolak") || stage.contentEquals("13bPindaanPTG")) {
                    value = "L";
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("RAYT")) {
                if (stage.contentEquals("semak_charting_keputusan")) {
                    String idAliranMhonFasa = "012_KeputusanMMKN"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("MLCRG")) {
                if (stage.contentEquals("029_Hantarsurat")) {
                    String idAliranMhonFasa = "024_KeputusanMMK"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("MPJLB")) {
                if (stage.contentEquals("006_Semak")) {
                    String idAliranMhonFasa = "006_Semak"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    //FasaPermohonan mohonFasa = new FasaPermohonan();
                    //mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    //value = mohonFasa.getKeputusan().getKod();
                    value = permohonan.getKeputusan().getKod();
                    return value;
                }
            }

            if (urusan.equalsIgnoreCase("PHLA")) {
                if (stage.contentEquals("23Smk")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "20BtPrnth");
                    if (mohonFasa.getKeputusan().getKod().equals("XW")) {
                        value = "XW";
                    } else if (mohonFasa.getKeputusan().getKod().equals("AW")) {
                        value = "AW";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if(permohonan.getCawangan().getKod().equals("04"))
            {
                if (urusan.equalsIgnoreCase("PBGSA")) {
                
                    if (stage.contentEquals("35aSemakan")) {
                        value = permohonan.getKeputusan().getKod();
                        return value;
                    }
                }
            }
            if (urusan.equalsIgnoreCase("PRIZ")) {
                if (stage.contentEquals("perakuan_ptd")) {
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
                if (stage.contentEquals("18bSmkn")) {
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), "17BtSiasatan");
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("BMBT")) {
                if (stage.contentEquals("pindaan_draf_MMKN_PTG") || stage.contentEquals("semak_peraku_MMKN_PTD")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("semak_charting_keputusan")) {
                    if (permohonan.getKeputusan().getKod().equals("L")) {
                        value = "L";
                    } else {
                        value = "T";
                    }
                    return value;
                }
            }
            if (urusan.equalsIgnoreCase("PJBTR")) {
                if (stage.contentEquals("jana_surat_tolak_ringkas") || stage.contentEquals("pindaan_draf_MMKN_PTG") || stage.contentEquals("semak_charting_kpsn") || stage.contentEquals("semak_charting_kemaskini") || stage.contentEquals("terima_peraku_pindaan_D") || stage.contentEquals("kemasukan_rayuan") || stage.contentEquals("semak_lprn_tanah_D1")) {
                    value = "L";
                    return value;
                } else if (stage.contentEquals("rekod_keputusan_MMKN_PTG")) {
                    String idAliranMhonFasa = "rekod_keputusan_MMKN_PTG"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
                    return value;
                } else if (stage.contentEquals("g_charting_keputusan")) {
                    String idAliranMhonFasa = "terima_surat_kpsn_PTD"; //INTENTIONALLY HARDCODE, SINCE KPSN ONLY AT THIS STAGE
                    FasaPermohonan mohonFasa = new FasaPermohonan();
                    mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(permohonan.getIdPermohonan(), idAliranMhonFasa);
                    value = mohonFasa.getKeputusan().getKod();
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
    public void onGenerateReports(StageContext context) {

        Pengguna peng = context.getPengguna();
        comm.setPengguna(peng);
        String kodNegeri = conf.getProperty("kodNegeri");
        Permohonan mohon = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();
        List<String> t = new ArrayList<String>();
        List<String> t2 = new ArrayList<String>();

        if (mohon.getKodUrusan().getKod().equals("PLPTD")) {
            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(mohon.getIdPermohonan(), "16Kptsn");
            if (mohonFasa.getKeputusan() != null) {
                LOG.info("------mohonFasa.getKeputusan().getKod() -----------::" + mohonFasa.getKeputusan().getKod());
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("DISSKPLPTD_NS.rdf");
                    t2.add("SL");
                    comm.reportGen(mohon, t, t2);
                } else {
                    t.add("DISSMTPTD_NS.rdf");
                    t2.add("SMT");
                    comm.reportGen(mohon, t, t2);
                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PBPTG")) {
            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(mohon.getIdPermohonan(), "terima_ulasan_teknikal");
            if (mohonFasa.getKeputusan() != null) {
                LOG.info("------mohonFasa.getKeputusan().getKod() PBPTG-----------::" + mohonFasa.getKeputusan().getKod());
                if (mohonFasa.getKeputusan().getKod().equals("XU")) {
                    t.add("DISSUT_NS.rdf");
                    t2.add("SPT");
                    comm.reportGen(mohon, t, t2);
                }
            }
        }
        if (mohon.getKodUrusan().getKod().equals("PBMMK")) {
            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(mohon.getIdPermohonan(), "13TrmUlsn");
            if (mohonFasa.getKeputusan() != null) {
                LOG.info("------mohonFasa.getKeputusan().getKod() PBMMK-----------::" + mohonFasa.getKeputusan().getKod());
                if (mohonFasa.getKeputusan().getKod().equals("XU")) {
                    t.add("DISSUT_NS.rdf");
                    t2.add("SPT");
                    comm.reportGen(mohon, t, t2);
                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("PTPBP")) {
            mohonFasa = pelupusanService.findMohonFasaByIdMohonIdPengguna(mohon.getIdPermohonan(), "27TerimaKeputusan");
            if (mohonFasa.getKeputusan() != null) {
                LOG.info("------mohonFasa.getKeputusan().getKod() PTPBP-----------::" + mohonFasa.getKeputusan().getKod());
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    t.add("DISPRMPH_NS.rdf");
                    t2.add("PRMP");
                    comm.reportGen(mohon, t, t2);
                } else {
                    t.add("DISSTPTPBP_NS.rdf");
                    t2.add("SMT");
                    comm.reportGen(mohon, t, t2);
                }
            }
        }
    }

    @Override
    public void afterPushBack(StageContext context) {
    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        return "back";
    }

    public CommonService getComm() {
        return comm;
    }

    public void setComm(CommonService comm) {
        this.comm = comm;
    }
}
