
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.validator;

import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.BangunanPetak;
import etanah.model.BangunanPetakAksesori;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodKeputusan;
import etanah.model.Notifikasi;
import etanah.model.Pengguna;
import etanah.workflow.StageContext;
import etanah.workflow.StageListener;
import org.hibernate.Session;
import etanah.model.KodPeranan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.report.ReportUtil;
import etanah.service.NotifikasiService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.strata.CommonService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import etanah.view.strata.GenerateIdPerserahanWorkflow;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import etanah.model.LanjutanTempoh;
import etanah.view.uam.MailService2;

/**
 *
 * @author Murali
 */
public class KeputusanValidation implements StageListener {

    @Inject
    ReportUtil reportUtil;
    @Inject
    CommonService comm;
    @Inject
    etanah.Configuration conf;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    NotifikasiService notifikasiService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    StrataPtService strService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    GenerateIdPerserahanWorkflow generateIdPerserahanWorkflow;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private TaskDebugService tds;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private KodKeputusanDAO kodKeputusanDAO;
    Pengguna pengguna;
    FolderDokumen fd;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private static final Logger LOG = Logger.getLogger(KeputusanValidation.class);
    private String idHakmilik;
    private String idHakmilik1;
    private Hakmilik hakmilik;
    private Date tarikhLulus;

    @Override
    public boolean beforeStart(StageContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
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

        if (kodNegeri.equals("04")) {
            if (mohon.getKodUrusan().getKod().equals("PBBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("SUBMC")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_SUBMC.rdf");
                        t2.add("KPT");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRKertasPTG_SUBMC.rdf");
                        t2.add("KPT");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS1")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_MLK.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_MLK.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBBD")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMH1A")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_MLK.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_MLK.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

            if (mohon.getKodUrusan().getKod().equals("PSBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS5")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_MLK.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_MLK.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
            if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        t.add("STRKertasPTGPHPC_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        //t.add("STRKertasPTG_MLK.rdf");
                        t.add("STRKertasPTGPHPC_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("RMHS6")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_MLK.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_MLK.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
            if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa == null) {
                    mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                }
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        //t.add("STRKertasPTG_MLK.rdf");
                        t.add("STRKertasPTGPHPP_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }
            if (mohon.getKodUrusan().getKod().equals("RMHS7")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_MLK.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_MLK.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_MLK.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
            if (mohon.getKodUrusan().getKod().equals("PBTS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRSLulusPecahBahagi_MLK.rdf");
                        t2.add("SKPBP");

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRSBayaranKeJUPEM_MLK.rdf");
                        t2.add("SMPBJ");
                        comm.reportGen(mohon, t, t2);

                    } else {
                        t.add("STRSTolakPecahBahagi_MLK.rdf");
                        t2.add("STPBP");
                        comm.reportGen(mohon, t, t2);

                        t.add("STRKertasPTG_MLK.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }
        } //Negeri9
        else {

            Permohonan permohonan = context.getPermohonan();
            mohonFasa = strService.findMohonFasa(permohonan.getIdPermohonan(), "keputusan");

            LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
            if (permohonan != null) {

                permohonan.setKeputusan(mohonFasa.getKeputusan());
                peng = (Pengguna) context.getPengguna();

                permohonan.setKeputusanOleh(peng);
                permohonan.setTarikhKeputusan(new Date());
                strService.updateMohon(permohonan);

            }

            if (mohon.getKodUrusan().getKod().equals("PBBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS1")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_NS.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_NS.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }
                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBBD")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMH1A")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_NS.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_NS.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBBSS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS5")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_NS.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_NS.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS6")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_NS.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_NS.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("RMHS7")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSLulusPindaBorang_NS.rdf");
                        t2.add("SLMP");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTGRayuan_NS.rdf");
                        t2.add("KPTG");

                        t.add("STRSTolakPindaBorang_NS.rdf");
                        t2.add("STMP");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }

            if (mohon.getKodUrusan().getKod().equals("PBTS")) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa.getKeputusan() != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    } else {
                        t.add("STRKertasPTG_NS.rdf");
                        t2.add("KPTG");
                        comm.reportGen(mohon, t, t2);
                    }

                }
            }
        }
    }

    @Override
    public String beforeComplete(StageContext context, String proposedOutcome) {

        Permohonan permohonan = context.getPermohonan();
        FasaPermohonan mohonFasa = new FasaPermohonan();

        if (permohonan.getKodUrusan().getKod().equals("PBBS") || permohonan.getKodUrusan().getKod().equals("PBBD")
                || permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBBSS")
                || permohonan.getKodUrusan().getKod().equals("PBTS") || permohonan.getKodUrusan().getKod().equals("PSBS")) {

            try {
                //check urusan: PNB registered or not if registered, have to complete it first
                Permohonan mohonPNB = new Permohonan();
                mohonPNB = strService.findIDSblmByKodUrusan(permohonan.getIdPermohonan(), "PNB");
                LOG.debug("----PNB----Registered----:" + mohonPNB);
                if (mohonPNB != null) {
                    mohonFasa = strService.findFasaPermohonanByIdAliran(mohonPNB.getIdPermohonan(), "keputusan");
                    LOG.debug("----mohonFasa----:" + mohonFasa);
                    Map m = tds.traceTask(mohonPNB.getIdPermohonan());
                    String user = (String) m.get("participant");
                    LOG.debug("----mohonPNB----user----:" + user);
                    if (mohonFasa == null || mohonFasa.getKeputusan() == null) {
                        context.addMessage("Maaf. Urusan ini tidak dapat diteruskan kerana ID Hakmilik ini telah digunakan dalam Urusan: Penarikan Balik Permohonan Pecah Bahagi Bangunan \n\n<br>"
                                + "ID Permohonan: " + mohonPNB.getIdPermohonan() + ", Pengguna: " + user.substring(user.indexOf(".") + 1));
                        return null;
                    }

                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
                return null;
            }
        }

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Permohonan mohon = context.getPermohonan();
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Keputusan PTG");
        if (kodNegeri.equals("04")) {
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7", "RTPS", "RTHS", "RBHS", "PBBS", "PBBD", "PBS", "PSBS", "SUBMC"}; //"PHPP", "PHPC", 
            if (!ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
            } else {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            }
            KodUrusan kod = new KodUrusan();
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah diluluskan oleh Pengarah Tanah dan Galian.");
            } else {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah ditolak oleh Pengarah Tanah dan Galian.");
            }
            LOG.info("++++++++++++++notifikasi before complete++++++++++");
        } else {
            //n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " Makluman kepada Timbalan Pengarah Tanah dan Galian");

            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            //   KodUrusan kod = new KodUrusan();
            if (mohonFasa.getKeputusan().getKod().equals("L")) {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah diluluskan.");
            } else {
                n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah ditolak.");
            }

        }
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equals("04")) {
            list.add(kodPerananDAO.findById("63"));
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7"};
            if (ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");

                list.add(kodPerananDAO.findById("23"));
            }
        } else {
            // list.add(kodPerananDAO.findById("233"));
            list.add(kodPerananDAO.findById("54"));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");

        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

//        Permohonan mohon = context.getPermohonan();
        Pengguna peng = (Pengguna) context.getPengguna();
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        idHakmilik = mohon.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        String[] name = {"idHakmilik"};
        Object[] value = {idHakmilik};
        List<Hakmilik> senaraiHakmilik = hakmilikDAO.findByEqualCriterias(name, value, null);

        if (kodNegeri.equals("04")) {
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7", "RTPS", "RTHS", "RBHS", "PBBS", "PBBD", "PBS", "PSBS", "SUBMC"}; //"PHPP", "PHPC",
            if (!ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan1");
            } else {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            }
            KodUrusan kod = new KodUrusan();
            if (mohonFasa.getKeputusan() != null) {

                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                LOG.debug("--------permohonan----:" + permohonan.getIdPermohonan());
                if (permohonan != null) {
                    // KodKeputusan kodkpsn = mohonFasa.getKeputusan();

                    permohonan.setKeputusan(mohonFasa.getKeputusan());
                    peng = (Pengguna) context.getPengguna();
                    permohonan.setKeputusanOleh(peng);
                    permohonan.setTarikhKeputusan(new Date());
                    strService.updateMohon(permohonan);
                }

                List<Permohonan> intePend = new ArrayList<Permohonan>();
                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                        || mohon.getKodUrusan().getKod().equals("PBS") || mohon.getKodUrusan().getKod().equals("PSBS")
                        || mohon.getKodUrusan().getKod().equals("PBTS")) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBBL");
                        kod = kodUrusanDAO.findById("PBBL");
                    } else {
                        intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBBT");
                        kod = kodUrusanDAO.findById("PBBT");
                    }

                    if (intePend.isEmpty()) {
                        LOG.info("--kod.getNama()--:" + kod.getNama());
                        LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());

                        Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);

                        LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                        LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                        PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                        permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                        permohonanRujLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujLuar.setPermohonan(mohonReg);
                        permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                        permohonanRujLuar.setNoRujukan("1");
                        permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                        try {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            String formattedDate = dateFormat.format(date);
                            LOG.info("--formattedDate--" + formattedDate);
                            permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                        }
                        KodRujukan kodRujukan;
                        kodRujukan = kodRujukanDAO.findById("FL");
                        permohonanRujLuar.setKodRujukan(kodRujukan);
                        strService.SimpanMohonRujukLuar(permohonanRujLuar);
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian Bangunan).");
                        } else {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian Bangunan).");
                        }
                        LOG.info("--Saved in Mohon Rujuluar--:");
                    }
                }

                if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBCTL");
                        kod = kodUrusanDAO.findById("PBCTL");
                    } else {
                        intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBCTT");
                        kod = kodUrusanDAO.findById("PBCTT");
                    }
                    LOG.info("--kod.getNama()--:" + kod.getNama());
                    LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());

                    if (intePend.isEmpty()) {
                        Permohonan mohonReg = new Permohonan();

                        if (kodNegeri.equals("05")) {
                            mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                        } else {
                            mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);
                        }

                        LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                        LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                        PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                        permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                        permohonanRujLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujLuar.setPermohonan(mohonReg);
                        permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                        permohonanRujLuar.setNoRujukan("1");
                        permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                        try {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            String formattedDate = dateFormat.format(date);
                            LOG.info("--formattedDate--" + formattedDate);
                            permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                        }
                        KodRujukan kodRujukan;
                        kodRujukan = kodRujukanDAO.findById("FL");
                        permohonanRujLuar.setKodRujukan(kodRujukan);
                        strService.SimpanMohonRujukLuar(permohonanRujLuar);
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian/Cantum Petak).");
                        } else {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian/Cantum Petak).");
                        }
                        LOG.info("--Saved in Mohon Rujuluar--:");
                    }
                }

                if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                    List<HakmilikPermohonan> hk = mohon.getSenaraiHakmilik();
                    List<Hakmilik> senaraiHakmilik1 = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hakmilikPermohonan : hk) {
                        LOG.info("--adding Hakmilik to senaraiHakmilik1--" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        senaraiHakmilik1.add(hakmilikPermohonan.getHakmilik());
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBCTL");
                            kod = kodUrusanDAO.findById("PBCTL");
                        } else {
                            intePend = strService.findIDMohonByKodUrusan(mohon.getIdPermohonan(), "PBCTT");
                            kod = kodUrusanDAO.findById("PBCTT");
                        }
                    }

                    if (intePend.isEmpty()) {
                        LOG.info("--kod.getNama()--:" + kod.getNama());
                        LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());
                        LOG.info("--senaraiHakmilik1 size--" + senaraiHakmilik1.size());
                        LOG.info("--senaraiHakmilik1 sending to pendaftaran--" + senaraiHakmilik1);

                        Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPHPP(kod, peng, senaraiHakmilik1, mohon);

                        LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                        LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                        PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                        permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                        permohonanRujLuar.setCawangan(peng.getKodCawangan());
                        permohonanRujLuar.setPermohonan(mohonReg);
                        permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                        permohonanRujLuar.setNoRujukan("1");
                        permohonanRujLuar.setHakmilik(senaraiHakmilik1.get(0));
                        try {
                            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            String formattedDate = dateFormat.format(date);
                            LOG.info("--formattedDate--" + formattedDate);
                            permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                        } catch (Exception e) {
                            LOG.error(e.getMessage());
                        }
                        KodRujukan kodRujukan;
                        kodRujukan = kodRujukanDAO.findById("FL");
                        permohonanRujLuar.setKodRujukan(kodRujukan);
                        strService.SimpanMohonRujukLuar(permohonanRujLuar);
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian/Cantum Petak).");
                        } else {
                            context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian/Cantum Petak).");
                        }
                        LOG.info("--Saved in Mohon Rujuluar--:");
                    }
                }
            }
        }

        if (kodNegeri.equals("05")) {
            mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
            KodUrusan kod = new KodUrusan();
            Permohonan mohonCEK = new Permohonan();

            if (mohonFasa.getKeputusan() != null) {

                if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                        || mohon.getKodUrusan().getKod().equals("PBS") || mohon.getKodUrusan().getKod().equals("PBBSS")
                        || mohon.getKodUrusan().getKod().equals("PBTS")) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        kod = kodUrusanDAO.findById("PBBL");
                    } else {
                        kod = kodUrusanDAO.findById("PBBT");
                    }
                    LOG.info("--kod.getNama()--:" + kod.getNama());
                    LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());
                    //generateIdPerserahanWorkflow.generateIdPerserahanWorkflow(kod, peng, senaraiHakmilik, mohon);

                    Permohonan mohonReg = new Permohonan();

                    if (kodNegeri.equals("05")) {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                    } else {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);
                    }

                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    //ida tmbh
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian Bangunan).");
                    } else {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian Bangunan).");
                    }
                    LOG.info("--Saved in Mohon Rujuluar--:");
                }

                if (mohon.getKodUrusan().getKod().equals("PHPC")) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        kod = kodUrusanDAO.findById("PBCTL");
                    } else {
                        kod = kodUrusanDAO.findById("PBCTT");
                    }
                    LOG.info("--kod.getNama()--:" + kod.getNama());
                    LOG.info("--mohon.getFolderDokumen()--:" + mohon.getFolderDokumen());
                    Permohonan mohonReg = new Permohonan();

                    if (kodNegeri.equals("05")) {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                    } else {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHakmilik, mohon);
                    }
                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    LOG.info("--Sending Strata Idmohon to Reg/Saving in Mohon Rujuluar--:");
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(mohon.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    //ida tmbh
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian/Cantum Petak).");
                    } else {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian/Cantum Petak).");
                    }
                    LOG.info("--Saved in Mohon Rujuluar--:");
                }

                if (mohon.getKodUrusan().getKod().equals("PHPP")) {
                    List<HakmilikPermohonan> hk = mohon.getSenaraiHakmilik();
                    List<Hakmilik> senaraiHakmilik1 = new ArrayList<Hakmilik>();
                    for (HakmilikPermohonan hakmilikPermohonan : hk) {
                        LOG.info("--adding Hakmilik to senaraiHakmilik1--" + hakmilikPermohonan.getHakmilik().getIdHakmilik());
                        senaraiHakmilik1.add(hakmilikPermohonan.getHakmilik());
                        if (mohonFasa.getKeputusan().getKod().equals("L")) {
                            kod = kodUrusanDAO.findById("PBCTL");
                            LOG.info(kod.getNama());
                            LOG.info(mohon.getFolderDokumen());
                        } else {
                            kod = kodUrusanDAO.findById("PBCTT");
                            LOG.info(kod.getNama());
                            LOG.info(mohon.getFolderDokumen());
                        }
                    }
                    LOG.info("--senaraiHakmilik1 size--" + senaraiHakmilik1.size());
                    LOG.info("--senaraiHakmilik1 sending to pendaftaran--" + senaraiHakmilik1);

                    Permohonan mohonReg = new Permohonan();

                    if (kodNegeri.equals("05")) {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowN9(context.getStageName(), kod, peng, senaraiHakmilik, permohonan);
                    } else {
                        mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflowPHPP(kod, peng, senaraiHakmilik1, mohon);
                    }

                    LOG.info("--Saving in Mohon Rujuluar--:");
                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    //ida tmbh
                    permohonanRujLuar.setHakmilik(senaraiHakmilik.get(0));
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Kelulusan Pecah Bahagian/Cantum Petak).");
                    } else {
                        context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Penolakan Pecah Bahagian/Cantum Petak).");
                    }
                    LOG.info("--Saved in Mohon Rujuluar--:");
                }
            }
        }

        //ida update 24/06/2013 ~~~~~~~~~~~~~~~~~~~
        if (kodNegeri.equals("05")) {
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5", "RMHS6", "RMHS7"};
            if (ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
                if (mohonFasa != null) {
                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
                        Dokumen d = strService.findDok(permohonan.getIdPermohonan(), "JPP");

                        Permohonan mohonsblm = context.getPermohonan().getPermohonanSebelum();

                        if (mohonsblm != null) {
                            Dokumen d2 = strService.findDok(mohonsblm.getIdPermohonan(), "JPP");
                            d2.setNamaFizikal(d.getNamaFizikal());
                            strService.saveDokumen(d2);

                            PermohonanStrata mohonstr = strService.findID(mohonsblm.getIdPermohonan());
                            PermohonanSkim mohonskim = strService.readXMLForMohonSkim(d);
                            mohonstr.setNamaSkim(mohonskim.getNoSkim());
                            mohonstr.setNama(mohonskim.getNamaProjek());
                            strService.SimpanBngnKosRendah(mohonstr);

                            HakmilikPermohonan mhnHakmilikSblm = strService.findMohonHakmilik(mohonsblm.getIdPermohonan());

                            LOG.info("--ID MOHON SBLM--:" + mohonsblm.getIdPermohonan());
                            LOG.info("--ID MOHON SBLM--:" + mhnHakmilikSblm.getId());

                            PermohonanSkim mohonSkimSblm = strService.findIDSkimByIDMH(mhnHakmilikSblm.getId());

                            mohonSkimSblm.setNoSkim(mohonskim.getNoSkim());
                            mohonSkimSblm.setNoFailPt(mohonskim.getNoFailPt());
                            mohonSkimSblm.setDiukur(mohonskim.getDiukur());
                            mohonSkimSblm.setNoKpPengukur(mohonskim.getNoKpPengukur());
                            mohonSkimSblm.setTrhSiap(mohonskim.getTrhSiap());
                            mohonSkimSblm.setBilPetak(mohonskim.getBilPetak());
                            mohonSkimSblm.setBilAksr(mohonskim.getBilAksr());
                            mohonSkimSblm.setTrhLulusCf(mohonskim.getTrhLulusCf());
                            mohonSkimSblm.setNamaPemaju(mohonskim.getNamaPemaju());
                            mohonSkimSblm.setNamaProjek(mohonskim.getNamaProjek());
                            mohonSkimSblm.setNoRujJubl(mohonskim.getNoRujJubl());
                            mohonSkimSblm.setNoFailUkurSemula(mohonskim.getNoFailUkurSemula());
                            //mohonSkimSblm.setKodTujuanUkur(mohonskim.getKodTujuanUkur());
                            mohonSkimSblm.setJumlahUnitSyer(mohonskim.getJumlahUnitSyer());
                            strService.saveSkim(mohonSkimSblm);

                        }

                        //mohonstr.setPemilikAlamat1(mohonskim.get);
                    }
                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("RMHS1")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    for (PermohonanBangunan mohonBangunan : context.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                        for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                            for (BangunanPetak bp : bt.getSenaraiPetak()) {
                                for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                    strService.deleteAksesori(bpa);
                                }
                                strService.deletePetak(bp);
                            }
                            strService.deleteTgkt(bt);
                        }
                        strService.deleteBngn(mohonBangunan);
                    }
                    updateNewPetak(context);
                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("RMH1A")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    for (PermohonanBangunan mohonBangunan : context.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                        for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                            for (BangunanPetak bp : bt.getSenaraiPetak()) {
                                for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                    strService.deleteAksesori(bpa);
                                }
                                strService.deletePetak(bp);
                            }
                            strService.deleteTgkt(bt);
                        }
                        strService.deleteBngn(mohonBangunan);
                    }
                    updateNewPetak(context);
                }
            }
        }

        if (mohon.getKodUrusan().getKod().equals("RMHS5")) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    for (PermohonanBangunan mohonBangunan : context.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                        for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                            for (BangunanPetak bp : bt.getSenaraiPetak()) {
                                for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                    strService.deleteAksesori(bpa);
                                }
                                strService.deletePetak(bp);
                            }
                            strService.deleteTgkt(bt);
                        }
                        strService.deleteBngn(mohonBangunan);
                    }
                    updateNewPetak(context);
                }
            }
        }

        if ((kodNegeri.equals("04")) && (mohon.getKodUrusan().getKod().equals("RMHS6"))) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    for (PermohonanBangunan mohonBangunan : context.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                        for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                            for (BangunanPetak bp : bt.getSenaraiPetak()) {
                                for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                    strService.deleteAksesori(bpa);
                                }
                                strService.deletePetak(bp);
                            }
                            strService.deleteTgkt(bt);
                        }
                        strService.deleteBngn(mohonBangunan);
                    }
                    updateNewPetak(context);
                }
            }
        }

        if ((kodNegeri.equals("04")) && (mohon.getKodUrusan().getKod().equals("RMHS7"))) {
            mohonFasa = strService.findFasaPermohonanByIdAliran(context.getPermohonan().getIdPermohonan(), "keputusan");
            if (mohonFasa != null) {
                if (mohonFasa.getKeputusan().getKod().equals("L")) {
                    for (PermohonanBangunan mohonBangunan : context.getPermohonan().getPermohonanSebelum().getSenaraiBangunan()) {
                        for (BangunanTingkat bt : mohonBangunan.getSenaraiTingkat()) {
                            for (BangunanPetak bp : bt.getSenaraiPetak()) {
                                for (BangunanPetakAksesori bpa : bp.getSenaraiPetakAksesori()) {
                                    strService.deleteAksesori(bpa);
                                }
                                strService.deletePetak(bp);
                            }
                            strService.deleteTgkt(bt);
                        }
                        strService.deleteBngn(mohonBangunan);
                    }
                    updateNewPetak(context);
                }
            }
        }

//        if (kodNegeri.equals("04")) {
//            if (mohon.getKodUrusan().getKod().equals("RTHS") || mohon.getKodUrusan().getKod().equals("RTPS") || mohon.getKodUrusan().getKod().equals("RBHS")) {
//                mohonFasa = strService.findMohonFasa(mohon.getIdPermohonan(), "keputusan");
//                if (mohonFasa.getKeputusan() != null) {
//                    if (mohonFasa.getKeputusan().getKod().equals("L")) {
//                        mohonHakmilik = strService.findMohonHakmilik(mohon.getIdPermohonan());
//                        LanjutanTempoh lanjutTempoh = new LanjutanTempoh();
//                        if (mohonHakmilik != null) {
//                            lanjutTempoh = strService.findMohonLanjutTempoh(mohonHakmilik.getId());
//                            LOG.info("--------mohonHakmilik.getId()-------:" + mohonHakmilik.getId());
//                            LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
//                            if (lanjutTempoh.getTarikhAkhirDilulus() == null) {
//                                LOG.info("--------lanjutTempoh-------:" + lanjutTempoh);
//                                context.addMessage("Sila masukkan tarikh kelulusan dan klik Simpan. Kemudian, sila klik butang Jana Dokumen untuk menjana semula surat makluman");
//                                return null;
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
        if (kodNegeri.equals("04")) {
            /*when user registered RMHS1/RMH1A/RMHS5 at stage:g_sedialaporan/semaklaporan/semakkertasptg/perakuan,
             * it have to regirster sysdaftar also */
            List<FasaPermohonan> fp = new ArrayList<FasaPermohonan>();
            String[] kodursns = {"RMHS1", "RMH1A", "RMHS5"};
            if (ArrayUtils.contains(kodursns, mohon.getKodUrusan().getKod())) {
                fp = strService.getSenaraiFasaPermohonan(mohon.getPermohonanSebelum().getIdPermohonan());
                if (fp.size() > 3) {
                    idHakmilik = permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    String[] idHak = {"idHakmilik"};
                    Object[] hakVal = {idHakmilik};
                    List<Hakmilik> senaraiHak = hakmilikDAO.findByEqualCriterias(idHak, hakVal, null);
                    KodUrusan kod = kodUrusanDAO.findById("PBBM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHak, permohonan.getPermohonanSebelum());
                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHak.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                    context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Permohonan Pecah Bahagi Bangunan).");
                }
            }

            String[] kodursn = {"RMHS6", "RMHS7"};
            if (ArrayUtils.contains(kodursn, mohon.getKodUrusan().getKod())) {
                fp = strService.getSenaraiFasaPermohonan(mohon.getPermohonanSebelum().getIdPermohonan());
                if (fp.size() > 3) {
                    idHakmilik = permohonan.getPermohonanSebelum().getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                    String[] idHak = {"idHakmilik"};
                    Object[] hakVal = {idHakmilik};
                    List<Hakmilik> senaraiHak = hakmilikDAO.findByEqualCriterias(idHak, hakVal, null);
                    KodUrusan kod = kodUrusanDAO.findById("PBCTM");
                    LOG.info(kod.getNama());
                    LOG.info(permohonan.getFolderDokumen());
                    Permohonan mohonReg = generateIdPerserahanWorkflow.generateIdPerserahanWorkflow1(kod, peng, senaraiHak, permohonan.getPermohonanSebelum());
                    LOG.info("--mohonReg--:" + mohonReg.getIdPermohonan());
                    PermohonanRujukanLuar permohonanRujLuar = new PermohonanRujukanLuar();
                    permohonanRujLuar.setInfoAudit(permohonan.getInfoAudit());
                    permohonanRujLuar.setCawangan(peng.getKodCawangan());
                    permohonanRujLuar.setPermohonan(mohonReg);
                    permohonanRujLuar.setNoFail(mohonReg.getPermohonanSebelum().getIdPermohonan());
                    permohonanRujLuar.setNoRujukan("1");
                    permohonanRujLuar.setHakmilik(senaraiHak.get(0));
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String formattedDate = dateFormat.format(date);
                        LOG.info("--formattedDate--" + formattedDate);
                        permohonanRujLuar.setTarikhRujukan(new Date(formattedDate));
                    } catch (Exception e) {
                        LOG.error(e.getMessage());
                    }
                    KodRujukan kodRujukan;
                    kodRujukan = kodRujukanDAO.findById("FL");
                    permohonanRujLuar.setKodRujukan(kodRujukan);
                    strService.SimpanMohonRujukLuar(permohonanRujLuar);
                    context.addMessage(" - Integrasi Dengan Unit Pendaftaran. " + mohonReg.getIdPermohonan() + " - Telah Dijana Untuk Dimaklumkan Kepada Unit Pendaftaran (Permohonan Pecah Bahagian/Cantum Petak).");
                }
            }
        }

        if (kodNegeri.equals("05")) {

            if (mohon.getKodUrusan().getKod().equals("RMH1A") || mohon.getKodUrusan().getKod().equals("RMHS1")
                    || mohon.getKodUrusan().getKod().equals("RMHS5") || mohon.getKodUrusan().getKod().equals("RMHS6")
                    || mohon.getKodUrusan().getKod().equals("RMHS7")) {

                context.addMessage(" - Urusan telah selesai.<br/>Makluman kepada Timbalan Pengarah Tanah dan Galian.");
            } else {
                context.addMessage(" - Makluman kepada Timbalan Pengarah Tanah dan Galian.");
            }

        } else if (mohon.getKodUrusan().getKod().equals("RMH1A") || mohon.getKodUrusan().getKod().equals("RMHS1")
                || mohon.getKodUrusan().getKod().equals("RMHS5") || mohon.getKodUrusan().getKod().equals("RMHS6")
                || mohon.getKodUrusan().getKod().equals("RMHS7") || mohon.getKodUrusan().getKod().equals("RTHS")
                || mohon.getKodUrusan().getKod().equals("RTPS") || mohon.getKodUrusan().getKod().equals("RBHS")) {

            context.addMessage(" - Urusan telah selesai.<br/>Makluman kepada Timbalan Pendaftar Hakmilik Geran Tanah dan Penolong Pengarah Tanah Galian.");
        }

        if (mohon.getKodUrusan().getKod().equals("PBBS") || mohon.getKodUrusan().getKod().equals("PBBD")
                || mohon.getKodUrusan().getKod().equals("PBS") || mohon.getKodUrusan().getKod().equals("PSBS")
                || mohon.getKodUrusan().getKod().equals("PHPP") || mohon.getKodUrusan().getKod().equals("PHPC")) {
            String idPermohonan = permohonan.getIdPermohonan();

            List<KodAgensi> agensi = strService.getKodAgensi("STR", "Y");
            FasaPermohonan mf = strService.findFasaPermohonanByIdAliran(idPermohonan, "keputusan");
            try {
                String keputusan = "";
                if (mf != null) {
                    keputusan = mf.getKeputusan().getNama();
                }
                MailService2 mail = new MailService2();
                int a = 0;
                for (KodAgensi pp : agensi) {
                    String[] to = new String[1];
                    to[0] = pp.getEmel();
                    a++;

                    String negeri = "";
                    if (kodNegeri.equals("04")) {
                        negeri = "Melaka";
                    } else {
                        negeri = "Negeri Sembilan";
                    }
                    String bpm = "";
                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim() != null) {
                        bpm = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getBandarPekanMukim().getNama();
                    }
                    if (permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen() != null) {
                        bpm = bpm + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getSeksyen().getNama();
                    }
                    List<PermohonanBangunan> mb = strService.checkMhnBangunanExist(idPermohonan);
                    int petakTanah = 0;
                    int petak = 0;
                    int petakAksr = 0;
                    int blokSementaraB = 0;
                    int blokSementaraL = 0;

                    for (PermohonanBangunan mb1 : mb) {
                        if (mb1.getKodKategoriBangunan().getKod().equals("L")) {
                            petakTanah += mb1.getBilanganPetak();
                        }
                        if (mb1.getKodKategoriBangunan().getKod().equals("P")) {
                            blokSementaraB += mb1.getBilanganPetak();
                        }
                        if (mb1.getKodKategoriBangunan().getKod().equals("PL")) {
                            blokSementaraL += mb1.getBilanganPetak();
                        }
                        if (mb1.getKodKategoriBangunan().getKod().equals("B")) {
                            petak += mb1.getBilanganPetak();
                        }
                        petakAksr = strService.CountPetakAksr(String.valueOf(mb1.getIdBangunan()));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    String subject = "Notifikasi Keputusan PTG (Strata)";
                    String msg = permohonan.getKodUrusan().getNama() + " untuk ID permohonan " + permohonan.getIdPermohonan() + " telah " + keputusan + ". Maklumat permohonan adalah seperti berikut :" + "  \n" + "  \n" + "No. Rujukan                  : " + permohonan.getIdPermohonan() + "  \n" + "JTB                          : " + permohonan.getPenyerahNama() + "  \n" + "Negeri                       : " + negeri + "  \n" + "Daerah                       : " + permohonan.getSenaraiHakmilik().get(0).getHakmilik().getDaerah().getNama() + " \n" + "Mukim                        : " + bpm + "  \n" + "Bil. Petak Tanah             : " + petakTanah + "  \n" + "Bil. Petak                   : " + petak + "  \n" + "Bil. Petak Aksesori          : " + petakAksr + "  \n" + "Bil. Blok Sementara Bangunan : " + blokSementaraB + "  \n" + "Bil. Blok Sementara Tanah    : " + blokSementaraL + "  \n" + "Tarikh Notis                 : " + sdf.format(new Date()) + "  \n" + "  \n" + "  \n" + "  \n" + "Ini adalah janaan komputer untuk makluman tuan/puan sahaja. Sila jangan balas ke emel ini.";

                    mail.sendEmailMany(to, subject, msg, kodNegeri);

                    LOG.info(to);
                }

                try {
                    context.addMessage("Makluman secara emel telah berjaya dihantar ");
                } catch (Exception e) {
                    context.addMessage("Makluman secara emel TIDAK berjaya dihantar ");
                    LOG.error(e.getMessage());
                    return null;
                }

            } catch (Exception e) {
                LOG.error(e.getMessage());
                context.addMessage("Makluman secara emel TIDAK berjaya dihantar ");
                return null;
            }
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

    @Override
    public void afterPushBack(StageContext context) {
        //throw new UnsupportedOperationException("Not supported yet.");

        String kodNegeri = conf.getProperty("kodNegeri");
        LOG.info("--kodNegeri--" + kodNegeri);
        Notifikasi n = new Notifikasi();
        n.setTajuk("Makluman Semakan Semula");
        if (kodNegeri.equals("04")) {
            n.setMesej(context.getPermohonan().getIdPermohonan() + " - " + context.getPermohonan().getKodUrusan().getNama() + " telah dihantar semula kepada Penolong Pegawai Tadbir / Penolong Pengarah Tanah Galian untuk semakan");
            LOG.info("++++++++++++++afterPushBack++++++++++");
        } else {
            n.setMesej(context.getPermohonan().getKodUrusan().getNama() + " Makluman kepada Timbalan Pengarah Tanah dan Galian");
        }
        Pengguna p = context.getPengguna();
        n.setCawangan(p.getKodCawangan());
        ArrayList<KodPeranan> list = new ArrayList<KodPeranan>();
        if (kodNegeri.equals("04")) {
            list.add(kodPerananDAO.findById("63"));
        } else {
            list.add(kodPerananDAO.findById("233"));
        }
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(p);
        ia.setTarikhMasuk(new Date());
        n.setInfoAudit(ia);
        LOG.info("--creating notice--calling to addRolesToNotifikasi--");
        notifikasiService.addRolesToNotifikasi(n, p.getKodCawangan(), list);

    }

    @Override
    public String beforePushBack(StageContext context, String proposedOutcome) {
        //return proposedOutcome;
        return "back";
    }

    private void updateNewPetak(StageContext sc) {
        for (PermohonanBangunan pb : sc.getPermohonan().getSenaraiBangunan()) {
            pb.setPermohonan(sc.getPermohonan().getPermohonanSebelum());
            strService.saveMohonBangunan(pb);
        }

    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdHakmilik1() {
        return idHakmilik1;
    }

    public void setIdHakmilik1(String idHakmilik1) {
        this.idHakmilik1 = idHakmilik1;
    }

    public Date getTarikhLulus() {
        return tarikhLulus;
    }

    public void setTarikhLulus(Date tarikhLulus) {
        this.tarikhLulus = tarikhLulus;
    }
}
