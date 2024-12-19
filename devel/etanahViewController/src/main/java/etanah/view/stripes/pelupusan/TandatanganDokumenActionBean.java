/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJenisPermitDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.LupusPermitDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.service.PelupusanService;
import etanah.model.InfoAudit;
import etanah.model.KodJenisPermit;
import etanah.model.LupusPermit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permit;
import etanah.model.PermitItem;
import etanah.model.PermitSahLaku;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.EnforceService;
import etanah.service.StrataPtService;
import etanah.service.common.PermohonanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.KandunganFolder;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKlasifikasi;
import etanah.model.KodUrusan;
import etanah.report.ReportUtil;
import static etanah.sequence.GeneratorIdPerserahan.SEQ_NAME_PRE;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.FasaPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.view.stripes.pelupusan.disClass.DisReport;
import etanah.workflow.StageContext;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author afham
 */
@UrlBinding("/pelupusan/dokumen_tandatangan")
public class TandatanganDokumenActionBean extends AbleActionBean {

    @Inject
    PelupusanService pelupusanService;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PermohonanService permohonanService;
    @Inject
    StrataPtService strService;
    @Inject
    EnforceService enforceService;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarAO;
    @Inject
    KodJenisPermitDAO kodJenisPermitDAO;
    @Inject
    PermitItemDAO permitItemDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    etanah.Configuration conf;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    DokumenService dokumenService;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    FasaPermohonanService fasaPermohonanService;

    private String idPermohonan;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private KandunganFolder folder;
    private FolderDokumen folderDokumen;
    private int sizeDokumen;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger log = Logger.getLogger(TandatanganDokumenActionBean.class);
    private List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    private List<KandunganFolder> kandunganList;
    private List<KandunganFolder> kandunganPermit;
    private Permit permit;
    private String stage;
    private static Pengguna pengguna;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    private List<PermitTandatanganForm> senaraitandatangan;

    public final static String PERMIT_SEQ = "NOPERMIT_SEQ";

//    @DefaultHandler
    public Resolution showForm() {

        return new JSP("pelupusan/common/papar_dokumen_ttgn.jsp").addParameter("tab", "true");
    }

    public Permit getPermitByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        String query = "SELECT m FROM etanah.model.Permit m WHERE m.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        return (Permit) q.uniqueResult();
    }

    @DefaultHandler
    public Resolution showForm2() {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        if (idPermohonan != null) {
            if (folderDokumen == null) {
                addSimpleError("Tiada folder di dalam permohonan ini");
            } else {
                kandunganList = folderDokumen.getSenaraiKandungan();
                if (kandunganList != null) {
                    senaraitandatangan = new ArrayList<PermitTandatanganForm>();
                    for (KandunganFolder kf : kandunganList) { //Change by urusan
                        if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                e.setSignPath(setSignPAth(kf.getDokumen()));
                                e.setDate(sdf.format(new Date()));
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                e.setSignPath(setSignPAthP1e(kf.getDokumen()));
                                e.setDate(sdf.format(new Date()));
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PPRU") || permohonan.getKodUrusan().getKod().equals("PPRUS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4De") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P2e") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PRU")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }

                        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ae") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PLPTD")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Be")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L2e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PRMP")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PKP")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4E")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L3e")) {
                                PermitTandatanganForm e = new PermitTandatanganForm();
                                e.setDokumen(kf.getDokumen());
                                e.setNamaFizikal(kf.getDokumen().getNamaFizikal());
                                senaraitandatangan.add(e);
                            }
                        }
                    }
                    if (!kandunganPermit.isEmpty()) {
                        sizeDokumen = kandunganPermit.size();
                    }
                }
            }
        }

        return new JSP("pelupusan/common/tandatangan_permit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanService.findById(idPermohonan);
        folderDokumen = permohonan.getFolderDokumen();
        permit = getPermitByIdMohon(idPermohonan);
        if (idPermohonan != null) {
            if (folderDokumen == null) {
                addSimpleError("Tiada folder di dalam permohonan ini");
            } else {
                kandunganList = folderDokumen.getSenaraiKandungan();
                if (kandunganList != null) {
                    kandunganPermit = new ArrayList<KandunganFolder>();
                    for (KandunganFolder kf : kandunganList) { //Change by urusan
                        if (permohonan.getKodUrusan().getKod().equals("PPBB") || permohonan.getKodUrusan().getKod().equals("PBPTD") || permohonan.getKodUrusan().getKod().equals("PBPTG") || permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PPRU") || permohonan.getKodUrusan().getKod().equals("PPRUS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4De") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P2e") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PRU")) {
                                kandunganPermit.add(kf);
                            }

                        } else if (permohonan.getKodUrusan().getKod().equals("PLPS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ae") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A") || kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PLPTD")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBMMK")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBPTG")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PBPTD")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Ce")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("P1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4A")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L1e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("LPSP")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4Be")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L2e")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PTPBP")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PRMP")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("PKP")) {
                                kandunganPermit.add(kf);
                            }
                        } else if (permohonan.getKodUrusan().getKod().equals("PPTR")) {
                            if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("4E")) {
                                kandunganPermit.add(kf);
                            } else if (kf.getDokumen().getKodDokumen().getKod().equalsIgnoreCase("L3e")) {
                                kandunganPermit.add(kf);
                            }
                        }
                    }
                    if (!kandunganPermit.isEmpty()) {
                        sizeDokumen = kandunganPermit.size();
                    }
                }

            }
        }
    }

//     @Override
    public void onGenerateReports() {
//        Permohonan permohonan = this.permohonan;
//        pengguna = context.getPengguna();
//        stage = context.getStageName();
        FasaPermohonan mohonfasa = fasaPermohonanService.findLastStage(permohonan.getIdPermohonan());
        stage = mohonfasa.getIdAliran();

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodUrusan kodUrusan = permohonan.getKodUrusan();

        String kodNegeri = conf.getProperty("kodNegeri");

        if (permohonan != null) {

            String gen1 = "";
            String gen2 = "";
            String code1 = "";
            String code2 = "";
            String[] params = new String[]{"p_id_mohon"};
            String[] values = new String[]{permohonan.getIdPermohonan()};
            String path = "";
            String path2 = "";
            String dokumenPath = conf.getProperty("document.path");
            Dokumen dokumen = null;
            KodDokumen kodDok = null;
            hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
            if (hakmilikPermohonanList.size() > 1) {
                hakmilikPermohonan = hakmilikPermohonanList.get(0);
            } else {
                hakmilikPermohonan = (HakmilikPermohonan) disLaporanTanahService.findObject(hakmilikPermohonan, new String[]{permohonan.getIdPermohonan()}, 1);
            }
            FolderDokumen folderDok = folderDokumenDAO.findById(permohonan.getFolderDokumen().getFolderId());
            //                List<Task> task = WorkFlowService.queryTasksByAdmin(permohonan.getIdPermohonan());

            int numUrusan = 11;
            if (numUrusan > 0) {
                generateReport(stage, numUrusan, kodNegeri, kodUrusan, gen1, code1, folderDok, dokumen, path, permohonan, hakmilikPermohonan, params, values, gen2, code2, path2, dokumenPath);
            }
        }
    }

    private void generateReport(String stage, int numUrusan, String kodNegeri, KodUrusan kodUrusan, String gen1, String code1, FolderDokumen folderDok, Dokumen dokumen,
            String path, Permohonan p, HakmilikPermohonan hp, String[] params, String[] values, String gen2, String code2, String path2, String dokumenPath) {

        DisReport disReport = new DisReport();
        HashMap reportMap = new HashMap(disReport.getReportMap(numUrusan, stage, kodNegeri, p, hp));

        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :" + reportMap.size() / 2);

        for (int i = 1; i <= reportMap.size() / 2; i++) {
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("reportKod" + String.valueOf(i)).toString()), p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMap.get("reportName" + String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen(), reportUtil.getContentType());
        }
    }

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
//            LOGGER.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
//            LOGGER.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            if (!doc.getKodDokumen().getKod().equals("RENC")) {
                ia.setTarikhKemaskini(new java.util.Date());
            }
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
//        LOGGER.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
        KodKlasifikasi klasifikasi_AM = kodKlasifikasiDAO.findById(1);
        doc.setKlasifikasi(klasifikasi_AM);
        doc.setTajuk(kd.getNama() + "-" + id);
        doc.setKodDokumen(kd);
        doc.setDalamanNilai1(id);
        doc = dokumenService.saveOrUpdate(doc);
        KandunganFolder kf = kandunganFolderService.findByDokumen(doc, fd);
        if (kf == null) {
            kf = new KandunganFolder();
        } else {
            ia = kf.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kf.setInfoAudit(ia);
        kf.setFolder(fd);
        kf.setDokumen(doc);
        dokumenService.saveKandunganWithDokumen(kf);

        return doc;
    }

    public Resolution generatePermit() {

//        KodUrusan kod = permohonan.getKodUrusan();
//        String kodCawangan = permohonan.getCawangan().getKod();
//
//        String kodPerserahan = kod.getKodPerserahan().getKod();
//        Long id = (getSerialNo(PERMIT_SEQ));
//        String noPermit = permit.getKodJenisPermit().getKod() + "04" + kodCawangan + id;
//
//        log.info("id Permit = " + id);
//
//        permit.setNoPermitBaru(noPermit);
//        permitDAO.saveOrUpdate(permit);
        onGenerateReports();
        return showForm2();
    }

    protected long getSerialNo(String sequenceName) {
        Connection c = sessionProvider.get().connection();
        Statement s = null;
        ResultSet rs = null;
        try {
            s = c.createStatement();
            // TODO remove Oracle specific
            rs = s.executeQuery("select " + sequenceName + ".nextval from dual");
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage() + " Ensure SEQUENCE " + sequenceName
                    + "  exists!");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {
                }
            }
            // if (c != null) try { c.close(); } catch (Exception e) {}
        }
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KandunganFolder getFolder() {
        return folder;
    }

    public void setFolder(KandunganFolder folder) {
        this.folder = folder;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<KandunganFolder> getKandunganList() {
        return kandunganList;
    }

    public void setKandunganList(List<KandunganFolder> kandunganList) {
        this.kandunganList = kandunganList;
    }

    public List<KandunganFolder> getKandunganPermit() {
        return kandunganPermit;
    }

    public void setKandunganPermit(List<KandunganFolder> kandunganPermit) {
        this.kandunganPermit = kandunganPermit;
    }

    public int getSizeDokumen() {
        return sizeDokumen;
    }

    public void setSizeDokumen(int sizeDokumen) {
        this.sizeDokumen = sizeDokumen;
    }

    public List<PermitTandatanganForm> getSenaraitandatangan() {
        return senaraitandatangan;
    }

    public void setSenaraitandatangan(List<PermitTandatanganForm> senaraitandatangan) {
        this.senaraitandatangan = senaraitandatangan;
    }

      private String setSignPAth(Dokumen dokumen) {
        String path = "";
        String pathNew = "";
//        '960762346#01/2018/FEBRUARI/15#4ce#'

        String s[] = dokumen.getNamaFizikal().split("/");
        for (int i = 0; i < s.length - 1; i++) {
            if (i == 0) {
                pathNew += s[i];

            } else {
                pathNew += "/" + s[i];

            }
        }
        path = dokumen.getIdDokumen() + "#" + pathNew + "#" + "4ce" + "#";
        return path;
    }
         private String setSignPAthP1e(Dokumen dokumen) {
        String path = "";
        String pathNew = "";
//        '960762346#01/2018/FEBRUARI/15#4ce#'

        String s[] = dokumen.getNamaFizikal().split("/");
        for (int i = 0; i < s.length - 1; i++) {
            if (i == 0) {
                pathNew += s[i];

            } else {
                pathNew += "/" + s[i];

            }
        }
        path = dokumen.getIdDokumen() + "#" + pathNew + "#" + "p1e" + "#";
        return path;
    }


    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }

    public PermitDAO getPermitDAO() {
        return permitDAO;
    }

    public void setPermitDAO(PermitDAO permitDAO) {
        this.permitDAO = permitDAO;
    }

    public FolderDokumenDAO getFolderDokumenDAO() {
        return folderDokumenDAO;
    }

    public void setFolderDokumenDAO(FolderDokumenDAO folderDokumenDAO) {
        this.folderDokumenDAO = folderDokumenDAO;
    }

    public static Pengguna getPengguna() {
        return pengguna;
    }

    public static void setPengguna(Pengguna pengguna) {
        TandatanganDokumenActionBean.pengguna = pengguna;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

}
