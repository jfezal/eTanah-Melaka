/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.ConsentPtdService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKategoriAgensi;
import etanah.model.KodKementerian;
import etanah.model.KodNegeri;
import etanah.model.KodRujukan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.SuratRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.ListUtil;
import java.util.Calendar;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/tandatangan_dokumen")
public class TandatanganDokumenManualActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO ;
    @Inject
    PelupusanService pelupusanService;
    private List<Pengguna> penggunaList;
    private PermohonanTandatanganDokumen tandatanganDokumenTemp;
    private PermohonanTandatanganDokumen tandatanganDokumen;
    private String ditundatangan;
    private String ditundatangan1;
    private Permohonan permohonan;
    private String idPermohonan;
    private Pengguna pguna;
    private String stageId;
    private String negeri;
    private boolean ptd;
    private boolean ptg;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        ptd = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
        }
        LOG.info("--------------PTD Tandatangan--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }
    
   
    public Resolution showFormKPTLupus() {
        ptd = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            penggunaList = getSenaraiPenggunaWithKPTLupus(permohonan.getCawangan());
        }
        LOG.info("--------------PTD Tandatangan--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    public Resolution showFormForPTG() {
        ptg = true;
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            KodCawangan kodCaw = kodCawanganDAO.findById("00");
            penggunaList = getSenaraiPenggunaPTG(kodCaw);
        }
        LOG.info("--------------PTG Tandatangan--------------");
        return new JSP("pelupusan/common/tandatangan_dokumen_manual.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("--------------" + idPermohonan);
//       String ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
//        
        if (permohonan != null) {
//            if(ptd == true){
//            penggunaList = getSenaraiPengguna(permohonan.getCawangan());
//            }
//            if(ptg == true){
//            penggunaList = getSenaraiPenggunaPTG(permohonan.getCawangan());
//            }
        }
//        
        BPelService service = new BPelService();
//        stageId = "laporan_tanah";
        //stageId = null;
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
//        stageId = "keluar_surat";
        if (stageId == null) {
            addSimpleError("Tidak boleh tandatangan kerana stage tidak dikenali atau ralat");
        } else {
            negeri = conf.getProperty("kodNegeri");

            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
                if (stageId.equals("semak_surat_tolak")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("semak_draf_mmkn3_3")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("rekod_keputusan_mmkn")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("semak_surat_tolak2")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("semak_lulus_borang_5a")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "N5A");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                }

            } else if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                if (stageId.equals("semak_surat_tolak")) {
                    if (permohonan.getKeputusan() != null) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                } else if (stageId.equals("semak_laporan_tanah")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "LTPD");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("sedia_dokumen")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                }
                else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                }else if (stageId.equals("semakan_rencana2")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } else if (stageId.equals("keluar_surat")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SWCTP");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                } 
                else if (stageId.equals("sedia_surat_kelulusan")) {
                    tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                    if (tandatanganDokumen != null) {
                        ditundatangan = tandatanganDokumen.getDiTandatangan();
                    }
                }
                else if (stageId.equals("semak_surat_kelulusan")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                    }
                } else if (stageId.equals("sedia_surat_lulus")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                    }
                } 
                else if (stageId.equals("semak_surat_lulus")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                    }
                }
                else if (stageId.equals("sedia_surat_lulustolak")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                    }
                }
            }
            if (negeri.equals("04")) {//Urusan Melaka
                LOG.info("-------URUSAN MELAKA-----");
                if (permohonan.getKodUrusan().getKod().equals("RAYA") || permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RAYL")) {
                    if (stageId.equals("04SemakMMKN")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("09SemakanMMKN")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("14SediaSuratTolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("04SediaSuratTolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("16SediaSuratBorang5A")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("05SediaSuratLulus")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("02SediaSuratLulus")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("05SediaSuratBatal")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBA");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("12RekodKeputusan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }


                }
                if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                    if (stageId.equals("sedia_draf_rencana_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } 
                    else if (stageId.equals("terima_semak_risalat_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_draf_mmkn3")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("pindaan_draf_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("sedia_surat_kelulusan")) {
                        if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                        }
                    }
                    else if (stageId.equals("sedia_permit")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PRMP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
                    if (stageId.equals("laporan_tanah") || stageId.equals("sedia_dokumen")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } 
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("sedia_draf_mmkn") || stageId.equals("semak_draf_mmkn4") || stageId.equals("pindaan_draf_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                     else if (stageId.equals("sedia_surat_tolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    
                }
                if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
                    if (stageId.equals("semak_surat_tolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } 
                    else if (stageId.equals("semak_draf_mmkn2")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_draf_mmkn3_3")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_surat_tolak2")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_surat_kelulusan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SLPRU");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                    if (stageId.equals("sedia_surat_ulasan_pengawal")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUBPR");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } 
                    else if (stageId.equals("semak_draf_mmkn3")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("terima_kpsn_mmkn_l")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                    if (stageId.equals("13sediakan_notis2a")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "MBST");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }else{
                            tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMST");
                            if (tandatanganDokumen != null) {
                                ditundatangan = tandatanganDokumen.getDiTandatangan();
                            }
                        }
                    } 
                    if (stageId.equals("05kenalpasti_jabatan_teknikal")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                            
                    else if (stageId.equals("18surat_penolakan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("20surat_pampasan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBPM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }else if (stageId.equals("22surat_terima_bayaran")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBTT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }else if (stageId.equals("26sedia_pu")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBU");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }else if (stageId.equals("27surat_bayaran_upah_ukur")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBUU");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }else if (stageId.equals("29surat_sah_bayaran")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SSB");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
                if (permohonan.getKodUrusan().getKod().equals("MCMCL")) {                    
                    if (stageId.equals("sedia_surat_kelulusan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
            } else if (negeri.equals("05")) {
                LOG.info("-------URUSAN NEGERI SEMBILAN-----");
                if (permohonan.getKodUrusan().getKod().equals("RLPTG") || permohonan.getKodUrusan().getKod().equals("RAYL") || permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
                    if (stageId.equals("05TerimaKeputusanL")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("07TerimaKeputusanT")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("16SediaSuratLulus")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("20SediaSuratTolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                    if (stageId.equals("04SediaNotisBicara")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "NB");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("08SediaNotisBicara")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "NB");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("11TerimaBayaranPerintah")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SPR");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } else if (stageId.equals("12SediaPerintahTolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SPR");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }



                }
                if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
                    if (stageId.equals("semak_surat_tolak")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    } 
                    else if (stageId.equals("semak_draf_mmkn2")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_draf_mmkn3_3")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("rekod_keputusan_mmkn")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_surat_tolak2")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                    else if (stageId.equals("semak_surat_kelulusan")) {
                        tandatanganDokumen = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SLPRU");
                        if (tandatanganDokumen != null) {
                            ditundatangan = tandatanganDokumen.getDiTandatangan();
                        }
                    }
                }
            }
        }
    }

    public List<Pengguna> getSenaraiPengguna(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('223','225') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }
    
    public List<Pengguna> getSenaraiPenggunaWithKPTLupus(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('223','225','222') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public List<Pengguna> getSenaraiPenggunaPTG(KodCawangan kod) {
        try {
            String query = "Select u from etanah.model.Pengguna u where u.perananUtama in('12','28') and u.kodCawangan.kod = :kod order by u.nama";
            Session session = sessionProvider.get();
            Query q = session.createQuery(query);
            q.setString("kod", kod.getKod());
            return q.list();

        } finally {
        }
    }

    public Resolution simpanTandatangan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        ditundatangan = getContext().getRequest().getParameter("ditundatangan");
        LOG.info("testing.............. " + ditundatangan);
        negeri = conf.getProperty("kodNegeri");
        if (ditundatangan != null) {
            if (permohonan.getKodUrusan().getKod().equals("PBMT") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
                if (stageId.equals("semak_surat_tolak")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SMT"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                } else if (stageId.equals("semak_draf_mmkn2")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                } else if (stageId.equals("semak_draf_mmkn3_3")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                } else if (stageId.equals("rekod_keputusan_mmkn")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                } else if (stageId.equals("semak_surat_tolak2")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                }//Untuk PBMT sahaja
                else if (stageId.equals("semak_lulus_borang_5a")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "N5A");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("N5A"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                } else if (stageId.equals("semak_surat_kelulusan")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                }

            } else if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                if (stageId.equals("semak_surat_tolak")) {
                    if (permohonan.getKeputusan() != null) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SMT"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    }
                } else if (stageId.equals("semak_laporan_tanah")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "LTPD");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("LTPD"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                } 
                else if (stageId.equals("sedia_surat_kelulusan")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                }
                else if (stageId.equals("sedia_dokumen")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                } else if (stageId.equals("perakuan_draf_rencana_ptd")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                }
                else if (stageId.equals("perakuan_draf_rencana_ptg")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                }else if (stageId.equals("semakan_rencana2")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKBB");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                } else if (stageId.equals("keluar_surat")) {
                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SWCTP");
                    if (tandatanganDokumen1 != null) {
                        ia = tandatanganDokumen1.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    } else {
                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                        tandatanganDokumen1.setPermohonan(permohonan);
//                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKBB"));
                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SWCTP"));
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new java.util.Date());
                    }
                    tandatanganDokumen1.setInfoAudit(ia);
                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                } else if (stageId.equals("semak_surat_kelulusan")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                    }
                } else if (stageId.equals("sedia_surat_lulus")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } else if (permohonan.getKeputusan().getKod().equals("T")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                    }
                } else if (stageId.equals("semak_surat_lulus")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
//                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }else if(permohonan.getKeputusan().getKod().equals("T")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
//                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                    }
                }
                else if (stageId.equals("sedia_surat_lulustolak")) {
                    if (permohonan.getKeputusan() != null) {
                        if (permohonan.getKeputusan().getKod().equals("L")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
//                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }else if(permohonan.getKeputusan().getKod().equals("T")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
//                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                    }
                }
            }
            if (negeri.equals("04")) {
                LOG.info("-------URUSAN MELAKA-----");
                if (permohonan.getKodUrusan().getKod().equals("RAYA") || permohonan.getKodUrusan().getKod().equals("RAYL") || permohonan.getKodUrusan().getKod().equals("RAYK")) {
                    if (stageId.equals("04SemakMMKN")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("09SemakanMMKN")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    } else if (stageId.equals("14SediaSuratTolak")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    } else if (stageId.equals("04SediaSuratTolak")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("16SediaSuratBorang5A")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                        PermohonanTandatanganDokumen tandatanganDokumen2 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "N5A");
                        if (tandatanganDokumen2 != null) {
                            ia = tandatanganDokumen2.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen2 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen2.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen2.setPermohonan(permohonan);
                            tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("N5A"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen2.setInfoAudit(ia);
                        tandatanganDokumen2.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen2);

                    } else if (stageId.equals("05SediaSuratLulus")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    } else if (stageId.equals("02SediaSuratLulus")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    } else if (stageId.equals("05SediaSuratBatal")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBA");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SBA"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    }
                    else if (stageId.equals("12RekodKeputusan")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    }
                     
                }
                if (permohonan.getKodUrusan().getKod().equals("LMCRG") || permohonan.getKodUrusan().getKod().equals("PJLB")) {
                        if (stageId.equals("laporan_tanah") || stageId.equals("sedia_dokumen")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "JKM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("JKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } 
                        else if (stageId.equals("rekod_keputusan_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("sedia_draf_mmkn") || stageId.equals("semak_draf_mmkn4") || stageId.equals("pindaan_draf_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("sedia_surat_tolak")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                     }
                    if (permohonan.getKodUrusan().getKod().equals("PRMP")) {
                        if (stageId.equals("sedia_draf_rencana_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } 
                        else if (stageId.equals("terima_semak_risalat_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_draf_mmkn3")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("pindaan_draf_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("rekod_keputusan_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("sedia_surat_kelulusan")) {
                            if (permohonan.getKeputusan() != null) {
                                if (permohonan.getKeputusan().getKod().equals("L")) {
                                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                                    if (tandatanganDokumen1 != null) {
                                        ia = tandatanganDokumen1.getInfoAudit();
                                        ia.setDiKemaskiniOleh(peng);
                                        ia.setTarikhKemaskini(new java.util.Date());
                                    } else {
                                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                        tandatanganDokumen1.setPermohonan(permohonan);
                                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                        ia.setDimasukOleh(peng);
                                        ia.setTarikhMasuk(new java.util.Date());
                                    }
                                    tandatanganDokumen1.setInfoAudit(ia);
                                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                                } else if (permohonan.getKeputusan().getKod().equals("T")) {
                                    PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                                    if (tandatanganDokumen1 != null) {
                                        ia = tandatanganDokumen1.getInfoAudit();
                                        ia.setDiKemaskiniOleh(peng);
                                        ia.setTarikhKemaskini(new java.util.Date());
                                    } else {
                                        tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                        tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                        tandatanganDokumen1.setPermohonan(permohonan);
                                        tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                        ia.setDimasukOleh(peng);
                                        ia.setTarikhMasuk(new java.util.Date());
                                    }
                                    tandatanganDokumen1.setInfoAudit(ia);
                                    tandatanganDokumen1.setDiTandatangan(ditundatangan);
                                    pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                                }
                            }
                        }
                        else if (stageId.equals("sedia_permit")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "PRMP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("PRMP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                     }
                    if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
                        if (stageId.equals("semak_surat_tolak")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SMT"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } 
                        else if (stageId.equals("semak_draf_mmkn2")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_draf_mmkn3_3")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("rekod_keputusan_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_surat_tolak2")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_surat_kelulusan")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SLPRU");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SLPRU"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        
                     }
                    if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                        if (stageId.equals("sedia_surat_ulasan_pengawal")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUBPR");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUBPR"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } 
                        else if (stageId.equals("semak_draf_mmkn3")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("rekod_keputusan_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("terima_kpsn_mmkn_l")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                     }
                    if (permohonan.getKodUrusan().getKod().equals("PHLP")) {
                        if (stageId.equals("13sediakan_notis2a")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "MBST");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("MBST"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                            
                            PermohonanTandatanganDokumen tandatanganDokumen2 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMST");
                            if (tandatanganDokumen2 != null) {
                                ia = tandatanganDokumen2.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen2 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen2.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen2.setPermohonan(permohonan);
                                tandatanganDokumen2.setKodDokumen(kodDokumenDAO.findById("SMST"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen2.setInfoAudit(ia);
                            tandatanganDokumen2.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen2);
                        } 
                        else if (stageId.equals("05kenalpasti_jabatan_teknikal")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SUP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SUP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("18surat_penolakan")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SMT"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("20surat_pampasan")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBPM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SBPM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("22surat_terima_bayaran")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBTT");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SBTT"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("26sedia_pu")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBU");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SBU"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("27surat_bayaran_upah_ukur")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SBUU");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SBUU"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("29surat_sah_bayaran")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SSB");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SSB"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                     }
                    if (permohonan.getKodUrusan().getKod().equals("MCMCL")) {                        
                        if (stageId.equals("sedia_surat_kelulusan")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }                        
                     }
            } else if (negeri.equals("05")) {
                LOG.info("-------URUSAN NEGERI SEMBILAN-----");
                if (permohonan.getKodUrusan().getKod().equals("RLPTG") || permohonan.getKodUrusan().getKod().equals("RAYL") || permohonan.getKodUrusan().getKod().equals("RAYK") || permohonan.getKodUrusan().getKod().equals("RYKN")) {
                    if (stageId.equals("05TerimaKeputusanL")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);

                    } else if (stageId.equals("07TerimaKeputusanT")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STT");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STT"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("16SediaSuratLulus")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SL");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SL"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("20SediaSuratTolak")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STT");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STT"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    }
                } else if (permohonan.getKodUrusan().getKod().equals("PTMTA")) {
                    if (stageId.equals("04SediaNotisBicara")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "NB");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("NB"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("08SediaNotisBicara")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "NB");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("NB"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("11TerimaBayaranPerintah")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SPR");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SPR"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    } else if (stageId.equals("12SediaPerintahTolak")) {
                        PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SPR");
                        if (tandatanganDokumen1 != null) {
                            ia = tandatanganDokumen1.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        } else {
                            tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                            tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                            tandatanganDokumen1.setPermohonan(permohonan);
                            tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SPR"));
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new java.util.Date());
                        }
                        tandatanganDokumen1.setInfoAudit(ia);
                        tandatanganDokumen1.setDiTandatangan(ditundatangan);
                        pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                    }
                }
                else if (permohonan.getKodUrusan().getKod().equals("PPRU")) {
                        if (stageId.equals("semak_surat_tolak")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SMT");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SMT"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        } 
                        else if (stageId.equals("semak_draf_mmkn2")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_draf_mmkn3_3")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "RMN");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("RMN"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("rekod_keputusan_mmkn")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SKM");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SKM"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_surat_tolak2")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "STP");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("STP"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                        else if (stageId.equals("semak_surat_kelulusan")) {
                            PermohonanTandatanganDokumen tandatanganDokumen1 = pelupusanService.findPermohonanDokTTByIdPermohonan(idPermohonan, "SLPRU");
                            if (tandatanganDokumen1 != null) {
                                ia = tandatanganDokumen1.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            } else {
                                tandatanganDokumen1 = new PermohonanTandatanganDokumen();
                                tandatanganDokumen1.setCawangan(permohonan.getCawangan());
                                tandatanganDokumen1.setPermohonan(permohonan);
                                tandatanganDokumen1.setKodDokumen(kodDokumenDAO.findById("SLPRU"));
                                ia.setDimasukOleh(peng);
                                ia.setTarikhMasuk(new java.util.Date());
                            }
                            tandatanganDokumen1.setInfoAudit(ia);
                            tandatanganDokumen1.setDiTandatangan(ditundatangan);
                            pelupusanService.simpanPermohonanDokTT(tandatanganDokumen1);
                        }
                     }
            }
        }
        addSimpleMessage("Rekod tandatangan telah dimasukkan");
        rehydrate() ;
        if (ptg == true) {
            return new RedirectResolution(TandatanganDokumenManualActionBean.class, "showFormForPTG");
        } else {
            return new RedirectResolution(TandatanganDokumenManualActionBean.class, "showForm");
        }

    }

    public List<Pengguna> getPenggunaList() {
        return penggunaList;
    }

    public void setPenggunaList(List<Pengguna> penggunaList) {
        this.penggunaList = penggunaList;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanTandatanganDokumen getTandatanganDokumen() {
        return tandatanganDokumen;
    }

    public void setTandatanganDokumen(PermohonanTandatanganDokumen tandatanganDokumen) {
        this.tandatanganDokumen = tandatanganDokumen;
    }

    public String getDitundatangan() {
        return ditundatangan;
    }

    public void setDitundatangan(String ditundatangan) {
        this.ditundatangan = ditundatangan;
    }

    public boolean isPtd() {
        return ptd;
    }

    public void setPtd(boolean ptd) {
        this.ptd = ptd;
    }

    public boolean isPtg() {
        return ptg;
    }

    public void setPtg(boolean ptg) {
        this.ptg = ptg;
    }
}
