/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.utility;

import able.stripes.AbleActionBean;
import etanah.model.KandunganFolder;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodJabatanDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.dao.UrusanDokumenDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDokumen;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodNegeri;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.UrusanDokumen;
import etanah.sequence.GeneratorIdKelompok;
import etanah.sequence.GeneratorIdPermohonanKelompok;
import etanah.service.KodService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.TaskDebugService;
import etanah.view.ListUtil;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisCarianBayaran;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import etanah.workflow.WorkFlowService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakWakil;
import etanah.model.Pihak;
import etanah.model.SalinanKepada;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanService;
import etanah.view.strata.CommonService;
//import etanah.view.strata.PenarikanBalikActionBean;
//import etanah.view.stripes.pelupusan.disClass.DisCarianFasa;
import java.text.ParseException;
import javax.servlet.http.HttpSession;

import etanah.report.ReportUtil;
import etanah.service.SemakDokumenService;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.KodKlasifikasi;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import java.io.File;

/**
 *
 * @author wazer
 */
@HttpCache(allow = false)
@UrlBinding("/utiliti/utilitiSalinan")
public class UtilitiSalinanActionBean extends AbleActionBean {

    private final static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UtilitiSalinanActionBean.class);
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    ReportUtil reportUtil;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    DokumenService dokumenService;
    @Inject
    KandunganFolderService kandunganFolderService;
    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private SalinanKepada salinanKepada;
    private List<SalinanKepada> listSalinanKepada;
    private List<KandunganFolder> senaraiKandungan;
    private String idPermohonan;
    private String kodNegeri;
    private String kodDokumen;
    private String[] senaraiReport;
    private String[] senaraiReportName;
    private String reportName;
    private HashMap reportMap;
    private List<HakmilikPermohonan> hakmilikPermohonanList;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("/pelupusan/utiliti/utiliti_salinan_surat.jsp");
    }

    public Resolution showFormSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");

        if (idPermohonan != null && kodDokumen != null) {
            listSalinanKepada = new ArrayList<SalinanKepada>();
            listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
        }
        return new JSP("pelupusan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", "true");
    }

    public Resolution showFormTambahSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        salinanKepada = new SalinanKepada();
        return new JSP("pelupusan/utiliti/utiliti_salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution showFormEditSalinan() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = getContext().getRequest().getParameter("kodDokumen");
        String idSalinan = getContext().getRequest().getParameter("idSalinan");

        salinanKepada = new SalinanKepada();
        salinanKepada = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
        return new JSP("pelupusan/utiliti/utiliti_salinan_surat_tambah.jsp").addParameter("popup", "true");
    }

    public Resolution findKandungan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                folderDokumen = permohonan.getFolderDokumen();
                if (folderDokumen == null) {
                    addSimpleError("Tiada senarai dokumen dalam permohonan ini");
                } else {
                    senaraiKandungan = disLaporanTanahService.getPelupusanService().getListDokumenSurat(folderDokumen.getFolderId());
                }
            } else {
                addSimpleError("Permohonan tidak dijumpai");
            }
        } else {
            addSimpleError("Sila masukkan Id Permohonan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_salinan_surat.jsp");

    }

    public Resolution simpanSalinan() throws Exception {
        kodNegeri = disLaporanTanahService.getConf().getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = (String) getContext().getRequest().getParameter("kodDokumen");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                SalinanKepada salinanTemp = new SalinanKepada();
                String idSalinan = (String) getContext().getRequest().getParameter("salinanKepada.idSalinanKepada");
                if (idSalinan != null && !idSalinan.equals("0")) {
                    salinanTemp = disLaporanTanahService.getSalinanKepadaDAO().findById(Long.parseLong(idSalinan));
                    infoAudit = salinanTemp.getInfoAudit();
                    infoAudit.setDiKemaskiniOleh(pengguna);
                    infoAudit.setTarikhKemaskini(new java.util.Date());
                } else {
                    infoAudit.setDimasukOleh(pengguna);
                    infoAudit.setTarikhMasuk(new java.util.Date());
                }
                salinanTemp.setInfoAudit(infoAudit);
                salinanTemp.setNama(salinanKepada.getNama());
                salinanTemp.setAlamat1(salinanKepada.getAlamat1());
                salinanTemp.setAlamat2(salinanKepada.getAlamat2());
                salinanTemp.setAlamat3(salinanKepada.getAlamat3());
                salinanTemp.setAlamat4(salinanKepada.getAlamat4());
                salinanTemp.setPoskod(salinanKepada.getPoskod());
                salinanTemp.setNegeri(salinanKepada.getNegeri());
                salinanTemp.setKodDokumen(kodDokumenDAO.findById(kodDokumen));
                salinanTemp.setPermohonan(permohonan);
                salinanTemp.setCawangan(permohonan.getCawangan() != null ? permohonan.getCawangan() : null);

                disLaporanTanahService.getPelupusanService().kemaskiniSalian(salinanTemp);

            }

            if (idPermohonan != null && kodDokumen != null) {
                listSalinanKepada = new ArrayList<SalinanKepada>();
                listSalinanKepada = disLaporanTanahService.getPelupusanService().findSalinanByIdPermohonanAndKodDokumen(idPermohonan, kodDokumen);
            }
//            showFormSalinan();
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", true);

    }

    public Resolution deleteRow() throws ParseException {

        String idKandungan = getContext().getRequest().getParameter("idKandungan");
        String tName = getContext().getRequest().getParameter("tName");
        String typeSyor = getContext().getRequest().getParameter("typeName");
        String forwardJSP = new String();

        if (idKandungan != null && tName != null) {
            forwardJSP = disLaporanTanahService.delObject(tName, new String[]{idKandungan}, typeSyor);
            addSimpleMessage("Maklumat Berjaya Dihapuskan");
        }
        return new JSP("/WEB-INF/jsp/pelupusan/utiliti/utiliti_salinan_surat_edit.jsp").addParameter("popup", "true");
    }

    //Jana Dokumen
    public Resolution janaDokumen() {
        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        kodDokumen = (String) getContext().getRequest().getParameter("kodDokumen");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (idPermohonan != null && kodDokumen != null) {
            permohonan = new Permohonan();
            permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
            if (permohonan != null) {
                KodUrusan kodUrusan = permohonan.getKodUrusan();
                String kodNegeri = conf.getProperty("kodNegeri");
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
                int numUrusan = this.getListUrusan(permohonan);
                if (numUrusan > 0) {
                    generateReport(kodDokumen, numUrusan, kodNegeri, kodUrusan, gen1, code1, folderDok, dokumen, path, permohonan, hakmilikPermohonan, params, values, gen2, code2, path2, dokumenPath);
                }
            }
        }
        addSimpleMessage("Dokumen telah dijana");
        return showFormSalinan();
    }

    //Get list urusan
    private int getListUrusan(Permohonan permohonan) {

        int numUrusan = permohonan.getKodUrusan().getKod().equals("PBMT") ? 1
                : permohonan.getKodUrusan().getKod().equals("PLPS") ? 2
                : permohonan.getKodUrusan().getKod().equals("MCMCL") ? 3
                : permohonan.getKodUrusan().getKod().equals("PHLP") ? 4
                : permohonan.getKodUrusan().getKod().equals("LPSP") ? 5
                : permohonan.getKodUrusan().getKod().equals("MMMCL") ? 6
                : permohonan.getKodUrusan().getKod().equals("PPTR") ? 7
                : permohonan.getKodUrusan().getKod().equals("RLPS") ? 8
                : permohonan.getKodUrusan().getKod().equals("PTGSA") ? 9
                : permohonan.getKodUrusan().getKod().equals("PPBB") ? 10
                : permohonan.getKodUrusan().getKod().equals("PBPTD") ? 11
                : permohonan.getKodUrusan().getKod().equals("PBPTG") ? 12
                : permohonan.getKodUrusan().getKod().equals("PPRU") ? 13
                : permohonan.getKodUrusan().getKod().equals("PRMP") ? 14
                : permohonan.getKodUrusan().getKod().equals("LMCRG") ? 15
                : permohonan.getKodUrusan().getKod().equals("PJLB") ? 16
                : permohonan.getKodUrusan().getKod().equals("PBRZ") ? 17
                : permohonan.getKodUrusan().getKod().equals("PRIZ") ? 18
                : permohonan.getKodUrusan().getKod().equals("RAYA") ? 19
                : permohonan.getKodUrusan().getKod().equals("RAYL") ? 20
                : permohonan.getKodUrusan().getKod().equals("RAYK") ? 21
                : permohonan.getKodUrusan().getKod().equals("RAYT") ? 22
                : permohonan.getKodUrusan().getKod().equals("JMRE") ? 23
                : permohonan.getKodUrusan().getKod().equals("PJTK") ? 24
                : permohonan.getKodUrusan().getKod().equals("PLPTD") ? 25
                : permohonan.getKodUrusan().getKod().equals("MMRE") ? 26
                : permohonan.getKodUrusan().getKod().equals("MPCRG") ? 27
                : permohonan.getKodUrusan().getKod().equals("PTBTC") ? 28
                : permohonan.getKodUrusan().getKod().equals("PTBTS") ? 29
                : permohonan.getKodUrusan().getKod().equals("BMBT") ? 30
                : permohonan.getKodUrusan().getKod().equals("PBGSA") ? 31
                : permohonan.getKodUrusan().getKod().equals("PBMMK") ? 32
                : permohonan.getKodUrusan().getKod().equals("PTPBP") ? 33
                : permohonan.getKodUrusan().getKod().equals("PCRG") ? 34
                : permohonan.getKodUrusan().getKod().equals("PRMMK") ? 35
                : permohonan.getKodUrusan().getKod().equals("WMRE") ? 36
                : permohonan.getKodUrusan().getKod().equals("PWGSA") ? 37
                : permohonan.getKodUrusan().getKod().equals("RLPTG") ? 38
                : permohonan.getKodUrusan().getKod().equals("RYKN") ? 39
                : permohonan.getKodUrusan().getKod().equals("PTMTA") ? 40
                : permohonan.getKodUrusan().getKod().equals("MLCRG") ? 41
                : permohonan.getKodUrusan().getKod().equals("MPJLB") ? 42
                : permohonan.getKodUrusan().getKod().equals("PJBTR") ? 43
                : 0;
        return numUrusan;

    }

    private void generateReport(String kodDokumen, int numUrusan, String kodNegeri, KodUrusan kodUrusan, String gen1, String code1, FolderDokumen folderDok, Dokumen dokumen,
            String path, Permohonan p, HakmilikPermohonan hp, String[] params, String[] values, String gen2, String code2, String path2, String dokumenPath) {

        HashMap reportMapDok = new HashMap(getReportMap(numUrusan, kodDokumen, kodNegeri, p, hp));
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        System.out.println("THIS IS REPORTMAP.SIZE AFTER DIVIDE BY TWO :" + reportMapDok.size() / 2);

        for (int i = 1; i <= reportMapDok.size() / 2; i++) {
            //reportMap.get("name"+String.valueOf(i));
            //dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMap.get("kod"+String.valueOf(i)).toString()), p.getIdPermohonan());
            dokumen = saveOrUpdateDokumen(folderDok, kodDokumenDAO.findById(reportMapDok.get("reportKod" + String.valueOf(i)).toString()), p.getIdPermohonan());
            path = p.getFolderDokumen().getFolderId() + File.separator + String.valueOf(dokumen.getIdDokumen());
            reportUtil.generateReport(reportMapDok.get("reportName" + String.valueOf(i)).toString(), params, values, dokumenPath + path, pengguna);
            updatePathDokumen(reportUtil.getDMSPath(), dokumen.getIdDokumen(), reportUtil.getContentType());
        }
    }

    //Tgk contoh kt getReportByCasePBPTD(kodDokumen, numnegeri, p) ; untuk buat urusan lain
    public HashMap getReportMap(int numUrusan, String kodDokumen, String negeri, Permohonan p, HakmilikPermohonan hp) {
        reportMap = new HashMap();
        //Pengguna pguna = new Pengguna();
        int numnegeri = negeri.equalsIgnoreCase("04") ? 1
                : negeri.equalsIgnoreCase("05") ? 2
                : 0;
        switch (numUrusan) {
            case 1:
                //PBMT
                reportMap = getReportByCasePBMT(kodDokumen, numnegeri);
                break;
            case 2:
                //PLPS
                reportMap = getReportByCasePLPS(kodDokumen, numnegeri);
                break;
            case 3:
                //MCMCL
                reportMap = getReportByCaseMCMCL(kodDokumen, numnegeri);
                break;
            case 4:
                //PHLP
                reportMap = getReportByCasePHLP(kodDokumen, numnegeri);
                break;
            case 5:
                //LPSP
                reportMap = getReportByCaseLPSP(kodDokumen, numnegeri, p);
                break;
            case 6:
                //MMMCL
                reportMap = getReportByCaseMMMCL(kodDokumen, numnegeri, hp);
                break;
            case 7:
                //PPTR
                reportMap = getReportByCasePPTR(kodDokumen, numnegeri);
                break;
            case 8:
                //RLPS
                reportMap = getReportByCaseRLPS(kodDokumen, numnegeri);
                break;
            case 9:
                //PTGSA
                reportMap = getReportByCasePTGSA(kodDokumen, numnegeri);
                break;
            case 10:
                //PPBB
                reportMap = getReportByCasePPBB(kodDokumen, numnegeri, p);
                break;
            case 11:
                //PBPTD
                reportMap = getReportByCasePBPTD(kodDokumen, numnegeri, p);
                break;
            case 12:
                //PBPTG
                reportMap = getReportByCasePBPTG(kodDokumen, numnegeri, p);
                break;
            case 13:
                //PPRU
                reportMap = getReportByCasePPRU(kodDokumen, numnegeri, p);
                break;
            case 14:
                //PRMP
                reportMap = getReportByCasePRMP(kodDokumen, numnegeri, p);
                break;
            case 15:
                //LMCRG
                reportMap = getReportByCaseLMCRG(kodDokumen, numnegeri, p);
                break;
            case 16:
                //PJLB
                reportMap = getReportByCasePJLB(kodDokumen, numnegeri, p);
                break;
            case 17:
                //PBRZ
                reportMap = getReportByCasePBRZ(kodDokumen, numnegeri, p);
                break;
            case 18:
                //PRIZ
                reportMap = getReportByCasePRIZ(kodDokumen, numnegeri, p);
                break;
            case 19:
                //RAYA
                reportMap = getReportByCaseRAYA(kodDokumen, numnegeri, p);
                break;
            case 20:
                //RAYL
                reportMap = getReportByCaseRAYL(kodDokumen, numnegeri, p);
                break;
            case 21:
                //RAYK
                reportMap = getReportByCaseRAYK(kodDokumen, numnegeri, p);
                break;
            case 22:
                //RAYT
                reportMap = getReportByCaseRAYT(kodDokumen, numnegeri, p);
                break;
            case 23:
                //JMRE
                reportMap = getReportByCaseJMRE(kodDokumen, numnegeri, p);
                break;
            case 24:
                //PJTK
                reportMap = getReportByCasePJTK(kodDokumen, numnegeri, p);
                break;
            case 25:
                //PLPTD
                reportMap = getReportByCasePLPTD(kodDokumen, numnegeri, p);
                break;
            case 26:
                //MMRE
                reportMap = getReportByCaseMMRE(kodDokumen, numnegeri, p);
                break;
            case 27:
                //MPCRG
                reportMap = getReportByCaseMPCRG(kodDokumen, numnegeri, p);
                break;
            case 28:
                //PTBTC
                reportMap = getReportByCasePTBTC(kodDokumen, numnegeri, p);
                break;
            case 29:
                //PTBTS
                reportMap = getReportByCasePTBTS(kodDokumen, numnegeri, p);
                break;
            case 30:
                //BMBT
                reportMap = getReportByCaseBMBT(kodDokumen, numnegeri, p);
                break;
            case 31:
                //PBGSA
                reportMap = getReportByCasePBGSA(kodDokumen, numnegeri, p);
                break;
            case 32:
                //PBMMK
                reportMap = getReportByCasePBMMK(kodDokumen, numnegeri, p);
                break;
            case 33:
                //PTPBP
                reportMap = getReportByCasePTPBP(kodDokumen, numnegeri, p);
                break;
            case 34:
                //PCRG
                reportMap = getReportByCasePCRG(kodDokumen, numnegeri, p);
                break;
            case 35:
                //PRMMK
                reportMap = getReportByCasePRMMK(kodDokumen, numnegeri, p);
                break;
            case 36:
                //WMRE
                reportMap = getReportByCaseWMRE(kodDokumen, numnegeri, p);
                break;
            case 37:
                //PWGSA
                reportMap = getReportByCasePWGSA(kodDokumen, numnegeri, p);
                break;
            case 38:
                //RLPTG
                reportMap = getReportByCaseRLPTG(kodDokumen, numnegeri, p);
                break;
            case 39:
                //RYKN
                reportMap = getReportByCaseRYKN(kodDokumen, numnegeri, p);
                break;
            case 40:
                //PTMTA
                reportMap = getReportByCasePTMTA(kodDokumen, numnegeri, p);
                break;
            case 41:
                //MLCRG
                reportMap = getReportByCaseMLCRG(kodDokumen, numnegeri, p);
                break;
            case 42:
                //MPJLB
                reportMap = getReportByCaseMPJLB(kodDokumen, numnegeri, p);
                break;
            case 43:
                //PJBTR
                reportMap = getReportByCasePJBTR(kodDokumen, numnegeri, p);
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePLPS(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPLPSPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName1", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod1", "SUA");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPLPSL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTPBMT1_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("sedia_surat_peringatan")) {
                    reportMap.put("reportName1", "DISSUTPBMT2_NS.rdf");
                    reportMap.put("reportKod1", "PER");
                } else if (kodDokumen.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("semak_draf_jktd1")) {
                    reportMap.put("reportName1", "DISKJKTDPTD_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (kodDokumen.contentEquals("semak_draf_jktd2")) {
                    reportMap.put("reportName1", "DISDrafJKTD_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (kodDokumen.contentEquals("semak_draf_mmk1")) {
                    reportMap.put("reportName1", "DISKMMKNPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_draf_mmk2")) {
                    reportMap.put("reportName1", "DISKertasMMKNPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_huraian_syor")) {
                    reportMap.put("reportName1", "DISKMMKNPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_huraian_syor2_1")) {
                    reportMap.put("reportName1", "DISKMMKNPKPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_huraian_syor3")) {
                    reportMap.put("reportName1", "DISKertasMMKNPTG_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("sedia_kertas_makluman")) {
                    reportMap.put("reportName1", "DISKertasMakluman.rdf");
                    reportMap.put("reportKod1", "MKLM");
                } else if (kodDokumen.contentEquals("tandatangan_5a")) {
                    reportMap.put("reportName1", "DISSuratKelulusan_MLK.rdf");
                    reportMap.put("reportKod1", "N5A");
                } else if (kodDokumen.contentEquals("g_penyediaan_pu_pt")) {
                    reportMap.put("reportName1", "DISBPU_NS.rdf");
                    reportMap.put("reportKod1", "SIPU");
                    reportMap.put("reportName2", "DISSIPU.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (kodDokumen.contentEquals("semak_draf_ptd1")) {
                    reportMap.put("reportName1", "DISKPPindaPTD_NS.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (kodDokumen.contentEquals("semak_draf_ptd2")) {
                    reportMap.put("reportName1", "DISKertasPTD_PindaLuas.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (kodDokumen.contentEquals("sedia_srt_byrn_tmbhn")) {
                    reportMap.put("reportName1", "DISSTBT_NS.rdf");
                    reportMap.put("reportKod1", "STPT");
                } else if (kodDokumen.contentEquals("semak_draf_mmk2_1")) {
                    reportMap.put("reportName1", "DISKMMKNPKPTD_NS.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_draf_mmk2_2")) {
                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("semak_huraian_syor2_3")) {
                    reportMap.put("reportName1", "DISKertasMMK_PindaLuas.rdf");
                    reportMap.put("reportKod1", "MMKS");
                } else if (kodDokumen.contentEquals("sedia_srt_byrn_tmbhn2")) {
                    reportMap.put("reportName1", "DISSrtBayaranTambahan.rdf");
                    reportMap.put("reportKod1", "STPT");
                } else if (kodDokumen.contentEquals("semak_surat_keputusan")) {
                    reportMap.put("reportName1", "DISSrtKeputusanLPS.rdf");
                    reportMap.put("reportKod1", "SKPN");
                } else if (kodDokumen.contentEquals("semak_borang_4a")) {
                    reportMap.put("reportName1", "DISBorang4A.rdf");
                    reportMap.put("reportKod1", "4A");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePBMT(String kodDokumen, int numnegeri) {

        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */
        reportMap = new HashMap();

        switch (numnegeri) {
            case 1:
                //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSrtMaklumanTolakPTD_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName1", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod1", "SUA");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPLPSL_MLK_PBMT.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;
            case 2:
                //NEGERI SEMBILAN

                if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPBMTGAWAL_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPBMTG_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUTPBMTNOTIS_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SL")) {
//                    reportMap.put("reportName1", "DISSPLPSL_PBMT_NS.rdf");
                     reportMap.put("reportName1", "DISSrtKelulusan_PBMT_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("SKM")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPBMT_NS.rdf");
                    reportMap.put("reportKod1", "SKM");
                } //                else if (kodDokumen.contentEquals("sedia_borang_5A")) {
                //                    reportMap.put("reportName1", "DISSrtKelulusan_PBMT_NS.rdf");
                //                    reportMap.put("reportKod1", "N5A");
                //                 
                //                } 
                else if (kodDokumen.contentEquals("OC")) {
                    reportMap.put("reportName1", "DISSrtIringanPU_PBMT_NS.rdf");
                    reportMap.put("reportKod1", "OC");
                    reportMap.put("reportName2", "DISBPU_PBMT_NS.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (kodDokumen.contentEquals("STPT")) {
                    reportMap.put("reportName1", "DISSrtTuntutTambahan_NS.rdf");
                    reportMap.put("reportKod1", "STPT");
                }

                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseMCMCL(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("semak_kertas_rencana")) {
                    reportMap.put("reportName1", "DISKRPMCMCL_MLK.rdf");
                    reportMap.put("reportKod1", "RENC");
                } else if (kodDokumen.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISMCMCLG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("sedia_surat_kelulusan")) {
                    reportMap.put("reportName1", "DISMCMCLL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePHLP(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SUP")) {
                    reportMap.put("reportName1", "DISSUP_MLK.rdf");
                    reportMap.put("reportKod1", "SUP");
                } else if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SBPM")) {
                    reportMap.put("reportName1", "DISSTBPP_MLK.rdf");
                    reportMap.put("reportKod1", "SBPM");
                } else if (kodDokumen.contentEquals("SBTT")) {
                    reportMap.put("reportName1", "DISSBPP_MLK.rdf");
                    reportMap.put("reportKod1", "SBTT");
                } else if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SBUU")) {
                    reportMap.put("reportName1", "DISSBPU_MLK.rdf");
                    reportMap.put("reportKod1", "SBUU");
                } else if (kodDokumen.contentEquals("SSB")) {
                    reportMap.put("reportName1", "DISSPBU_MLK.rdf");
                    reportMap.put("reportKod1", "SSB");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseLPSP(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTLPSPPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUTH_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYBH_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSLPSPL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSLPSPG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                }
                break;
            case 2:
                //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMMMCL(String kodDokumen, int numnegeri, HakmilikPermohonan hp) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMM")) {
                    reportMap.put("reportName1", "DISSrtProsesMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "SMM");
                } else if (kodDokumen.contentEquals("5A")) {
                    reportMap.put("reportName1", "DISBrg5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "5A");
                } else if (kodDokumen.contentEquals("5AA")) {
                    reportMap.put("reportName2", "DISNotisB5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod2", "5AA");
//                    reportMap.put("reportName3", "DISSBTAMCMMMCL_MLK.rdf");
//                    reportMap.put("reportKod3", "SBTA");
                } else if (kodDokumen.contentEquals("5A")) {
                    reportMap.put("reportName1", "DISBrg5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod1", "5A");
                } else if (kodDokumen.contentEquals("5AA")) {
                    reportMap.put("reportName2", "DISNotisB5AMCMMMCL_MLK.rdf");
                    reportMap.put("reportKod2", "5AA");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISMCMCLG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPTR(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("SUBPR")) {
                    reportMap.put("reportName1", "DISSUBPR_MLK.rdf");
                    reportMap.put("reportKod1", "SUBPR");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPPTRSTP_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPPTRL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");

                }
                break;

            case 2: //NEGERI SEMBILAN

                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "LTPD");
                } else if (kodDokumen.contentEquals("sedia_kertas_makluman")) {
                    reportMap.put("reportName1", "");
                    reportMap.put("reportKod1", "MKLM");
                } else if (kodDokumen.contentEquals("semak_borang_4e2")) {
                    reportMap.put("reportName1", "DISB4E_MLK.rdf");
                    reportMap.put("reportKod1", "4E");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRLPS(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                break;

            case 2: //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTGSA(String kodDokumen, int numnegeri) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPTGSAL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("23SediaDrafWarta")) {
                    reportMap.put("reportName1", "DIS_PWRKNS.rdf");
                    reportMap.put("reportKod1", "DWPM");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPBB(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSKpsnLPPBB_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSKpsnG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SWCTP")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBPTD(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                }

                break;

            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBPTG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYB_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("SKM")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNPBPTGL_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSrtKpsnMMKNPBPTGG_MLK.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }

                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSKpsnLPBPTG_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSKpsnGPBPPTG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");

                } else if (kodDokumen.contentEquals("SL")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
                        reportMap.put("reportName1", "DISSKpsnLPBPTG_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                        //    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        //      reportMap.put("reportName1", "DISSKpsnGPBPTG_MLK.rdf");
                        //       reportMap.put("reportKod1", "STP");
                    }
                } else if (kodDokumen.contentEquals("STP")) {
                    if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
                        reportMap.put("reportName1", "DISSKpsnGPBPTG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }

                } else if (kodDokumen.contentEquals("STWC")) {
                    reportMap.put("reportName1", "DISWC_MLK.rdf");
                    reportMap.put("reportKod1", "SWCTP");
                } else if (kodDokumen.contentEquals("LTWC")) {
                    reportMap.put("reportName1", "DISLTekWC_MLK.rdf");
                    reportMap.put("reportKod1", "LTWC");
                }

                break;

            case 2: //NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePPRU(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("MINB")) {
                    reportMap.put("reportName1", "DISMB_MLK.rdf");
                    reportMap.put("reportKod1", "MINB");
                } else if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSrtMaklumanTolakPTD_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUT_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYBPPRU_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");                
                } else if (kodDokumen.contentEquals("SKM")) {
                    reportMap.put("reportName1", "DISSKpsnMMKNPPTR_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPPRUL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
               
                }
                break;

            case 2: //NEGERI SEMBILAN

                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPPRU_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPPRU_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("06SmkLapTnh")) {
                    reportMap.put("reportName1", "DISLTPPRU_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("07KnlpstiJbtnTknkl")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("09SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("12SmkDrfMMK")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTD_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("17SmkDrfMMK")) {
                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
//                } else if (kodDokumen.contentEquals("23TrmKptsnSiasatan")) {
//                    reportMap.put("reportName1", "DISKS_NS.rdf");
//                    reportMap.put("reportKod1", "LPK");
//                } else if (kodDokumen.contentEquals("23TrmKptsnSiasatan")) {
//                    reportMap.put("reportName1", "DISKM_NS.rdf");
//                    reportMap.put("reportKod1", "MLM");
                } else if (kodDokumen.contentEquals("24Perakukan")) {
                    reportMap.put("reportName1", "DISKMMKTangguh_NS.rdf");
                    reportMap.put("reportKod1", "KSM");
                } else if (kodDokumen.contentEquals("29TdtgnSrtLulus")) {
                    reportMap.put("reportName1", "DISSPPRUL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("32TdtgnSrtTlk")) {
                    reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("37Tdtgn4de")) {
                    reportMap.put("reportName1", "DISB4De_NS.rdf");
                    reportMap.put("reportKod1", "4De");
                    reportMap.put("reportName2", "DIS_BorangP2e_NS.rdf");
                    reportMap.put("reportKod2", "P2e");
//                } else if (kodDokumen.contentEquals("37Tdtgn4de")) {
//                    reportMap.put("reportName1", "DIS_BorangP2e_NS.rdf");
//                    reportMap.put("reportKod1", "P2e");
                }

//                if (kodDokumen.contentEquals("g_charting_permohonan")) {
//                    reportMap.put("reportName1", "DISLPPPRU_NS.rdf");
//                    reportMap.put("reportKod1", "LPE");
//                    
//                }  else if (kodDokumen.contentEquals("laporan_tanah")) {
//                    reportMap.put("reportName1", "DISLTPPRU_NS.rdf");
//                    reportMap.put("reportKod1", "LT");
//                    
//                }  else if (kodDokumen.contentEquals("laporan_tanah")) {
//                    reportMap.put("reportName1", "DISLT_NS.rdf");
//                    reportMap.put("reportKod1", "LT");
//                    
//                }  else if (kodDokumen.contentEquals("semak_laporan_tanah")) {
//                    reportMap.put("reportName1", "DISLT_NS.rdf");
//                    reportMap.put("reportKod1", "LT");
//                    
//                }  else if (kodDokumen.contentEquals("07kenalpasti_JT")) {
//                    reportMap.put("reportName1", "DISSUT_NS.rdf");
//                    reportMap.put("reportKod1", "SUT"); 
//                    
//                } else if (kodDokumen.contentEquals("07BSedSrtPeringatan")) {
//                    reportMap.put("reportName1", "DISSUT_NS.rdf");
//                    reportMap.put("reportKod1", "SUT");
//                   
//                } else if (kodDokumen.contentEquals("10semak__kertas_mmk")) {
//                    reportMap.put("reportName1", "DISKMMKNPPRUPTD_NS.rdf");
//                    reportMap.put("reportKod1", "RMN");
//                    
//                } else if (kodDokumen.contentEquals("14semakhurai_draf")) {
//                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_NS.rdf");
//                    reportMap.put("reportKod1", "RMN");
//                    
//                } else if (kodDokumen.contentEquals("16cetak_kertas_mmk")) {
//                    reportMap.put("reportName1", "DISKMMKNPPRUPTG_NS.rdf");
//                    reportMap.put("reportKod1", "RMN");
//                    
//                } else if (kodDokumen.contentEquals("17terima_keputusan_mmk")) {
//                    reportMap.put("reportName1", "DISSKpsnMMKNPPRU_NS.rdf");
//                    reportMap.put("reportKod1", "SKM");
//                    
//                } else if (kodDokumen.contentEquals("18Siasatan")) {
//                    reportMap.put("reportName1", "DISKS_NS.rdf");
//                    reportMap.put("reportKod1", "LPK");
//                    
//                } else if (kodDokumen.contentEquals("22cetak_kertas_maklum")) {
//                    reportMap.put("reportName1", "DISKM_NS.rdf");
//                    reportMap.put("reportKod1", "MLM");
//                    
//                } else if (kodDokumen.contentEquals("27Tandatangan_Surat")) {
//                    if (p.getKeputusan().getKod().equalsIgnoreCase("L")) {
//                        reportMap.put("reportName1", "DISSPPRUL_NS.rdf");
//                        reportMap.put("reportKod1", "SL");
//                    } else if (p.getKeputusan().getKod().equalsIgnoreCase("T")) {
//                        reportMap.put("reportName1", "DISSPLPSG_MLK.rdf");
//                        reportMap.put("reportKod1", "STP");
//                    } else {
//                       reportMap.put("reportName1", "DISSPPRUL_NS.rdf");
//                       reportMap.put("reportKod1", "SL");
//                    } 
//                    
//                } else if (kodDokumen.contentEquals("30semak_borang4de")) {
//                    reportMap.put("reportName1", "DISB4De_NS.rdf");
//                    reportMap.put("reportKod1", "4De");
//                    reportMap.put("reportName2", "DIS_BorangP2e_NS.rdf");
//                    reportMap.put("reportKod2", "P2e");
//                    
//                } else if (kodDokumen.contentEquals("31tandatangan_4de")) {
//                    reportMap.put("reportName1", "DISB4De_NS.rdf");
//                    reportMap.put("reportKod1", "4De");
//                    reportMap.put("reportName2", "DIS_BorangP2e_NS.rdf");
//                    reportMap.put("reportKod2", "P2e");
//                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRMP(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SMT")) {
                    reportMap.put("reportName1", "DISSMTPTDH_MLK.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUTH_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("SUA")) {
                    reportMap.put("reportName2", "DISSMUYBH_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPRMPSTP_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPRMPLH_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("PRMP")) {
                    reportMap.put("reportName1", "DISPRMPH_MLK.rdf");
                    reportMap.put("reportKod1", "PRMP");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseLMCRG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SUT")) {
                    reportMap.put("reportName1", "DISSUTPTG_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    if (kodDokumen.contentEquals("SUA")) {
                        reportMap.put("reportName2", "DISSMUYBPTG_MLK.rdf");
                        reportMap.put("reportKod2", "SUA");
                    } else if (kodDokumen.contentEquals("SL")) {
                        reportMap.put("reportName1", "DISSLMCRGL_MLK.rdf");
                        reportMap.put("reportKod1", "SL");
                    } else if (kodDokumen.contentEquals("STP")) {
                        reportMap.put("reportName1", "DISSLMCRGG_MLK.rdf");
                        reportMap.put("reportKod1", "STP");
                    }
                    break;
                }
        }
        return reportMap;
    }

    public HashMap getReportByCasePJLB(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPJLBG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSPJLBL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }

                break;

            case 2://NEGERI SEMBILAN
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBRZ(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("005_UlasanJabatanTeknikal")) {
                    reportMap.put("reportName1", "DISSUTPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "(DISSMUYBPBRZ_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("010_Semak")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("015_drafPerakuan")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (kodDokumen.contentEquals("018_KeputusanMMKN") || kodDokumen.contentEquals("057_RekodkanKeputusanMMKN")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPBRZL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPBRZG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "MMKT");
                    }
                } else if (kodDokumen.contentEquals("022_SediaDrafWarta")) {
                    reportMap.put("reportName1", "DISdrafWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "DWP");
                } else if (kodDokumen.contentEquals("023_suratDanWarta")) {
                    reportMap.put("reportName1", "DISSrtIringanWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "8PNB");
                } else if (kodDokumen.contentEquals("031_TerimadanSediaDraf")) {
                    reportMap.put("reportName1", "DISdrafWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "DWP");
                } else if (kodDokumen.contentEquals("032_SediaSuratdanWarta")) {
                    reportMap.put("reportName1", "DISSrtIringanWartaRizabPBRZ.rdf");
                    reportMap.put("reportKod1", "8PNB");
                } else if (kodDokumen.contentEquals("040_SalinanWarta")) {
                    reportMap.put("reportName1", "DISSALINANWARTAPBRZ.rdf");
                    reportMap.put("reportKod1", "SLPBS");
                } else if (kodDokumen.contentEquals("g_hantar_pu")) {
                    reportMap.put("reportName1", "DISSIPUPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "8JUPE");
                    reportMap.put("reportName2", "DISSBUU_MLK.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (kodDokumen.contentEquals("054_SemakdanSediakanDraf")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (kodDokumen.contentEquals("060_TerimaKeputusanSediaSurat")) {
                    reportMap.put("reportName1", "DISSALINANWARTAPBRZ.rdf");
                    reportMap.put("reportKod1", "SLPBS");
                } else if (kodDokumen.contentEquals("066_SemakResalat")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("066A_SemakResalat")) {
                    reportMap.put("reportName1", "DISKMMKNPBRZPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("029_SemakNotaSiasatan")) {
                    reportMap.put("reportName1", "DIS_NOTIS_SIASATAN_MLK.rdf ");
                    reportMap.put("reportKod1", "NSIA");
                }
                break;

            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRIZ(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SKM")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPRIZL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPRIZG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSKpsnLPRIZ_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSPRIZG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("DSWD")) {
                    reportMap.put("reportName1", "DISSIWARTAPTD_MLK.rdf");
                    reportMap.put("reportKod1", "DSWD");

                }

                break;

            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLaporanPelukisPelan_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("007")) {
                    reportMap.put("reportName1", "DISSuratTolak_NS.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("010")) {
                    reportMap.put("reportName1", "DISLaporanTanah_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("014")) {
                    reportMap.put("reportName1", "DISKertasPertimbanganPTD_NS.rdf");
                    reportMap.put("reportKod1", "KPTD");
                } else if (kodDokumen.contentEquals("019")) {
                    reportMap.put("reportName1", "DISSijilBebasUpahUkur_NS.rdf");
                    reportMap.put("reportKod1", "SPPTG");
                } else if (kodDokumen.contentEquals("g_penyediaan_pu")) {
                    reportMap.put("reportName1", "DISSrtIringanPU_NS.rdf");
                    reportMap.put("reportKod1", "8JUPE");
                    reportMap.put("reportName2", "DISBPU_NS.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (kodDokumen.contentEquals("027")) {
                    reportMap.put("reportName1", "DISdrafWartaRizab_NS.rdf");
                    reportMap.put("reportKod1", "DWTR");
                } else if (kodDokumen.contentEquals("029")) {
                    reportMap.put("reportName1", "DISdrafWartaRizab_NS.rdf");
                    reportMap.put("reportKod1", "DWTR");
                    reportMap.put("reportName2", "DISSrtIringanWartaRizab_NS.rdf");
                    reportMap.put("reportKod2", "8PNB");
                } else if (kodDokumen.contentEquals("036")) {
                    reportMap.put("reportName1", "DISSuratLulus_NS.rdf");
                    reportMap.put("reportKod1", "ST");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYA(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSRAYAL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("SBA")) {
                    reportMap.put("reportName1", "DISSAPRAYA_MLK.rdf");
                    reportMap.put("reportKod1", "SBA");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYL(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSRAYG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSRAYLL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }

                break;

            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("01KemasukanKertasRingkas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTDH_NS.rdf");
                    reportMap.put("reportKod1", "KRPTD");
                } else if (kodDokumen.contentEquals("03SemakSyorKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTD_NS.rdf");
                    reportMap.put("reportKod1", "KRPTD");
                } else if (kodDokumen.contentEquals("05TerimaKeputusanL")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("07TerimaKeputusanT")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYK(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SKM")) {
                    reportMap.put("reportName1", "DISSrtKpsnMMKNRAYKL_MLK.rdf");
                    reportMap.put("reportName2", "DISSrtKpsnMMKNG_MLK.rdf");
                    reportMap.put("reportKod1", "SKM");
                    reportMap.put("reportKod2", "SKM");
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSRAYG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSRAYKL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRAYT(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("SKM")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNRAYTL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNRAYTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSrtTolakPTDRAYT_MLK.rdf");
                    reportMap.put("reportKod1", "STP");               
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseJMRE(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePJTK(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPJTK_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPJTK_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                    reportMap.put("reportName2", "DISWPPJTK_NS.rdf");
                    reportMap.put("reportKod2", "WP");
                } else if (kodDokumen.contentEquals("26PenyediaanSrtKelulusan")) {
                    reportMap.put("reportName1", "DISSLPJTK_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePLPTD(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMMRE(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMPCRG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTBTC(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("08SedSrtTlk")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("13TrmUlsn")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("14SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("17SmkDrfJKTD")) {
                    reportMap.put("reportName1", "DISKJKTDPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (kodDokumen.contentEquals("22SmkSyorPerakuanPTD")) {
                    reportMap.put("reportName1", "DISKMMKPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("27SmkDrfMMK")) {
                    reportMap.put("reportName1", "DISKMMKPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("33TrmKptsnSiasatan")) {
                    reportMap.put("reportName1", "DISKMMKPTBTC_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("39SedSyrtKelulusan")) {
                    reportMap.put("reportName1", "DISB5A_NS.rdf");
                    reportMap.put("reportKod1", "5AA");
                } else if (kodDokumen.contentEquals("g_penyediaan_pu")) {
                    reportMap.put("reportName1", "DISSPUUWGSA_NS.rdf");
                    reportMap.put("reportKod1", "SBU");
                    reportMap.put("reportName2", "DISSrtIringanPU_PTBTC_NS.rdf");
                    reportMap.put("reportKod2", "OC");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTBTS(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("08SedSrtTlk")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("13TrmUlsn")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("14SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("17SmkDrfJKTD")) {
                    reportMap.put("reportName1", "DISKJKTDPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (kodDokumen.contentEquals("22SmkSyorPerakuanPTD")) {
                    reportMap.put("reportName1", "DISKMMKPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("27SmkDrfMMK")) {
                    reportMap.put("reportName1", "DISKMMKPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("33TrmKptsnSiasatan")) {
                    reportMap.put("reportName1", "DISKMMKPTBTS_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("39SedSyrtKelulusan")) {
                    reportMap.put("reportName1", "DISB5A_NS.rdf");
                    reportMap.put("reportKod1", "5AA");
                } else if (kodDokumen.contentEquals("g_penyediaan_pu")) {
                    reportMap.put("reportName1", "DISSPUUWGSA_NS.rdf");
                    reportMap.put("reportKod1", "SBU");
                    reportMap.put("reportName2", "DISSrtIringanPU_PTBTS_NS.rdf");
                    reportMap.put("reportKod2", "OC");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseBMBT(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("semak_draf_MMKN_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("semak_pindaan_MMKN_PTD2")) {
                    //NOTE : semak_draf_MMKN_PTD 2nd generate surat MMKN PTD (stage pembetulan MMKN)
                    //if this change, the report at semak_pindaan_MMKN_PTD must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("semak_syor_draf_MMKN_PTG")) {
                    reportMap.put("reportName1", "DISKMMKNBMBTPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (kodDokumen.contentEquals("rekod_keputusan_MMKN_PTG")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    } else {
                        reportMap.put("reportName1", "AAA.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (kodDokumen.contentEquals("sedia_surat_tolak")) {
                    reportMap.put("reportName1", "DISSBMBTG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("sedia_surat_lulus_Brg5A")) {
                    reportMap.put("reportName1", "DISSKpsnLBMBT_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                    reportMap.put("reportName2", "DISB5A_MLK.rdf");
                    reportMap.put("reportKod2", "N5A");
                }

                break;
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("08SedSrtTlk")) {
                    reportMap.put("reportName1", "DISSMTPTD_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("13TrmUlsn")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("14SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPBMBT_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTBMBT_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("17SmkDrfJKTD")) {
                    reportMap.put("reportName1", "DISKJKTDBMBT_NS.rdf");
                    reportMap.put("reportKod1", "JKTD");
                } else if (kodDokumen.contentEquals("22SmkSyorPerakuanPTD")) {
                    reportMap.put("reportName1", "DISKMMKBMBT_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("27SmkDrfMMK")) {
                    reportMap.put("reportName1", "DISKMMKBMBT_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("33TrmKptsnSiasatan")) {
                    reportMap.put("reportName1", "DISKMMKBMBT_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("39SedSyrtKelulusan")) {
                    reportMap.put("reportName1", "DISB5A_NS.rdf");
                    reportMap.put("reportKod1", "5AA");
                } else if (kodDokumen.contentEquals("g_penyediaan_pu")) {
                    reportMap.put("reportName1", "DISSPUUWGSA_NS.rdf");
                    reportMap.put("reportKod1", "SBU");
                    reportMap.put("reportName2", "DISSrtIringanPU_BMBT_NS.rdf");
                    reportMap.put("reportKod2", "OC");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBGSA(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("PU")) {
                    reportMap.put("reportName1", "DISBorangPU_MLK.rdf");
                    reportMap.put("reportKod1", "PU");
                }

                break;
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePBMMK(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTPBP(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("07kpsn_pemohonan_dahulu")) {
                    reportMap.put("reportName1", "DISSTPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "SMT");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("11KenalPastiJabTek")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("13SedSrtPeringatan")) {
                    reportMap.put("reportName1", "DISSUT_NS.rdf");
                    reportMap.put("reportKod1", "SUT");
                } else if (kodDokumen.contentEquals("19SemakSyorHuraiMMK")) {
                    reportMap.put("reportName1", "DISKMMKNPTPBP_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("24SemakSyorDraf")) {
                    reportMap.put("reportName1", "DISKMMKNPTPBPPTG_NS.rdf");
                    reportMap.put("reportKod1", "RMN");
                } else if (kodDokumen.contentEquals("30TerimaKeputusanSiasatan")) {
                    reportMap.put("reportName1", "DISKM_NS.rdf");
                    reportMap.put("reportKod1", "MLM");
                } else if (kodDokumen.contentEquals("38SemakSahCetakBorang")) {
                    reportMap.put("reportName1", "DISPRMPH_NS.rdf");
                    reportMap.put("reportKod1", "PRMP");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePCRG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePRMMK(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePWGSA(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPLPS_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                }
                break;
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("g_charting_keputusan")) {
                    reportMap.put("reportName1", "DISLPPWGSA_NS.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("04SedPermhnanSBU")) {
                    reportMap.put("reportName1", "DISSPUUWGSA_NS.rdf");
                    reportMap.put("reportKod1", "SBU");
                } else if (kodDokumen.contentEquals("8aSedPU")) {
                    reportMap.put("reportName1", "DISSIPUWGSA_NS.rdf");
                    reportMap.put("reportKod1", "OC");
                    reportMap.put("reportName2", "DISBorangPUPWGSA_NS.rdf");
                    reportMap.put("reportKod2", "PU");
                } else if (kodDokumen.contentEquals("15SmkdanSyrDrafWrta")) {
                    reportMap.put("reportName1", "DISSWGWSA_NS.rdf");
                    reportMap.put("reportKod1", "DW");
                } else if (kodDokumen.contentEquals("17PerakudanCtkWrta")) {
                    reportMap.put("reportName1", "DISSWGWSA_NS.rdf");
                    reportMap.put("reportKod1", "DW");
                } else if (kodDokumen.contentEquals("18KmskniMklmnAgensi")) {
                    reportMap.put("reportName1", "DISSAWGSA_NS.rdf");
                    reportMap.put("reportKod1", "SRAGN");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseWMRE(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRLPTG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("01SediaKertasRingkas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTGH_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (kodDokumen.contentEquals("04TerimaKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (kodDokumen.contentEquals("06SemakSyorKertas")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (kodDokumen.contentEquals("11SediaKertasPTG")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (kodDokumen.contentEquals("14TerimaKeputusanL")) {
                    reportMap.put("reportName1", "DISKRRAYLPTG_NS.rdf");
                    reportMap.put("reportKod1", "KRPTG");
                } else if (kodDokumen.contentEquals("16SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("20SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYLPTDL_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseRYKN(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("01SediaKertasRingkas")) {
                    reportMap.put("reportName1", "DISKMMKNRAYK_NS.rdf");
                    reportMap.put("reportKod1", "MMK");
                } else if (kodDokumen.contentEquals("08JanaCetakKertas")) {
                    reportMap.put("reportName1", "DISKMMKNRAYK_NS.rdf");
                    reportMap.put("reportKod1", "MMK");
                } else if (kodDokumen.contentEquals("16SediaSuratLulus")) {
                    reportMap.put("reportName1", "DISSRAYKPTGL_NS.rdf");
                    reportMap.put("reportKod1", "SL");
                } else if (kodDokumen.contentEquals("20SediaSuratTolak")) {
                    reportMap.put("reportName1", "DISSRAYG_NS.rdf");
                    reportMap.put("reportKod1", "STT");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCasePTMTA(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("02SediaLaporan")) {
                    reportMap.put("reportName1", "DISLTPTMTA_NS.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("04SediaNotisBicara")) {
                    reportMap.put("reportName1", "DISBN_NS.rdf");
                    reportMap.put("reportKod1", "NB");
                } else if (kodDokumen.contentEquals("08SediaNotisBicara")) {
                    reportMap.put("reportName1", "DISBN_NS.rdf");
                    reportMap.put("reportKod1", "NB");
                } else if (kodDokumen.contentEquals("11TerimaBayaranPerintah")) {
                    reportMap.put("reportName1", "DISSPerintahL_NS.rdf");
                    reportMap.put("reportKod1", "SPR");
                } else if (kodDokumen.contentEquals("12SediaPerintahTolak")) {
                    reportMap.put("reportName1", "DISSPerintahT_NS.rdf");
                    reportMap.put("reportKod1", "SPR");
                }
                break;

        }
        return reportMap;
    }

    public HashMap getReportByCaseMLCRG(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPBRZ_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("009_Cetak") || kodDokumen.contentEquals("010_Semak")) {
                    reportMap.put("reportName1", "DISKJKMMLCRG_MLK.rdf");
                    reportMap.put("reportKod1", "JKM");
                } else if (kodDokumen.contentEquals("027_Sediakansurat")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNMLCRGL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNMLCRGG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "MMKT");
                    }
                }
                break;
            case 2: //NEGERI SEMBILAN
                //TODO HERE
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCaseMPJLB(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("STP")) {
                    reportMap.put("reportName1", "DISSrtTolakPTDMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("SL")) {
                    reportMap.put("reportName1", "DISSKpsnLMPJLB_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
                }
                break;
            case 2: //NEGERI SEMBILAN
                if (kodDokumen.contentEquals("009")) {
                    reportMap.put("reportName1", "DISSrtTolakPTD_MLK.rdf");
                    reportMap.put("reportKod1", "STLK");
                } else if (kodDokumen.contentEquals("015")) {
                    reportMap.put("reportName1", "DISSPJLBL_MLK.rdf");
                    reportMap.put("reportKod1", "SLSB");
                } else if (kodDokumen.contentEquals("053")) {
                    reportMap.put("reportName1", "DISSrtKpsn_MLK.rdf");
                    reportMap.put("reportKod1", "SMM");
                }
                break;
        }
        return reportMap;
    }

    public HashMap getReportByCasePJBTR(String kodDokumen, int numnegeri, Permohonan p) {
        /*
         * NOTE: reportBil = numOfReportsCalled reportName{intCount} =
         * reportName reportKod{intCount} = reportKodDokumen
         */

        reportMap = new HashMap();

        switch (numnegeri) {
            case 1: //MELAKA
                if (kodDokumen.contentEquals("g_charting_permohonan")) {
                    reportMap.put("reportName1", "DISLPPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "LPE");
                } else if (kodDokumen.contentEquals("laporan_tanah")) {
                    reportMap.put("reportName1", "DISLTPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "LT");
                } else if (kodDokumen.contentEquals("kenalpasti_jabatan_teknikal")) {
                    reportMap.put("reportName1", "DISSUTPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "SUT");
                    reportMap.put("reportName2", "DISSMUYBPJBTR_MLK.rdf");
                    reportMap.put("reportKod2", "SUA");
                } else if (kodDokumen.contentEquals("semak_draf_JKBB_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKJKBBPJBTRPTD_MLK.rdf");
                    reportMap.put("reportKod1", "JKBBD");
                } else if (kodDokumen.contentEquals("semak_masuk_bil_kertas_JKBB")) {
                    reportMap.put("reportName1", "DISKJKBBPJBTRPTG_MLK.rdf");
                    reportMap.put("reportKod1", "JKBBG");
                } else if (kodDokumen.contentEquals("semak_draf_MMKN_PTD")) {
                    //NOTE : semak_draf_MMKN_PTD 1st generate surat MMKN PTD
                    //if this change, the report at semak_pindaan_MMKN_PTD2 must also change
                    reportMap.put("reportName1", "DISKMMKNBMBTPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("semak_pindaan_MMKN_PTD2")) {
                    //NOTE : semak_draf_MMKN_PTD 2nd generate surat MMKN PTD (stage pembetulan MMKN)
                    //if this change, the report at semak_pindaan_MMKN_PTD must also change
                    reportMap.put("reportName1", "DISKMMKNPJBTRPTD_MLK.rdf");
                    reportMap.put("reportKod1", "MMKND");
                } else if (kodDokumen.contentEquals("semak_syor_draf_MMKN_PTG")) {
                    reportMap.put("reportName1", "DISKMMKNPJBTRPTG_MLK.rdf");
                    reportMap.put("reportKod1", "MMKNG");
                } else if (kodDokumen.contentEquals("rekod_keputusan_MMKN_PTG")) {
                    if (p.getKeputusan() != null) {
                        if (p.getKeputusan().getKod().equals("L")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNPJBTRL_MLK.rdf");
                        } else if (p.getKeputusan().getKod().equals("T")) {
                            reportMap.put("reportName1", "DISSrtKpsnMMKNBMBTG_MLK.rdf");
                        }
                        reportMap.put("reportKod1", "SKM");
                    } else {
                        reportMap.put("reportName1", "AAA.rdf");
                        reportMap.put("reportKod1", "SKM");
                    }
                } else if (kodDokumen.contentEquals("sedia_surat_tolak_PTD")) {
                    reportMap.put("reportName1", "DISSPJBTRG_MLK.rdf");
                    reportMap.put("reportKod1", "STP");
                } else if (kodDokumen.contentEquals("jana_surat_tolak_ringkas")) {
                    reportMap.put("reportName1", "DISSTLKPJBTR_MLK.rdf");
                    reportMap.put("reportKod1", "STLK");
                } else if (kodDokumen.contentEquals("sedia_surat_lulus")) {
                    reportMap.put("reportName1", "DISSPJBTRL_MLK.rdf");
                    reportMap.put("reportKod1", "SL");
//                    reportMap.put("reportName1", "DISB4E_MLK.rdf");
//                    reportMap.put("reportKod1", "4E");
                }

//                else if (kodDokumen.contentEquals("sedia_surat_lulus_Brg5A")) {
//                    reportMap.put("reportName1", "DISSKpsnLBMBT_MLK.rdf");
//                    reportMap.put("reportKod1", "SL");
//                    reportMap.put("reportName2", "DISB5A_MLK.rdf");
//                    reportMap.put("reportKod2", "N5A");
//                }
                break;
            case 2: //NEGERI SEMBILAN

                break;

        }
        return reportMap;
    }

    private Dokumen saveOrUpdateDokumen(FolderDokumen fd, KodDokumen kd, String id) {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = new InfoAudit();
        Dokumen doc = null;
        doc = semakDokumenService.semakDokumen(kd, fd, id);
        if (doc == null) {
            LOG.debug("new Document");
            doc = new Dokumen();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            doc.setBaru('Y');
            doc.setNoVersi("1.0");
        } else {
            LOG.debug("old Document");
            ia = doc.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
            doc.setBaru('T');
            Double noVersi = Double.parseDouble(doc.getNoVersi());
            doc.setNoVersi(String.valueOf(noVersi + 1));
        }
        doc.setFormat("application/pdf");
        doc.setInfoAudit(ia);
        LOG.debug(doc.getInfoAudit().getDimasukOleh().getIdPengguna());
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

    private void updatePathDokumen(String namaFizikal, Long idDokumen, String format) {
        Dokumen d = dokumenService.findById(idDokumen);
        d.setFormat(format);
        d.setNamaFizikal(namaFizikal);
        dokumenService.saveOrUpdate(d);
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public SalinanKepada getSalinanKepada() {
        return salinanKepada;
    }

    public void setSalinanKepada(SalinanKepada salinanKepada) {
        this.salinanKepada = salinanKepada;
    }

    public List<SalinanKepada> getListSalinanKepada() {
        return listSalinanKepada;
    }

    public void setListSalinanKepada(List<SalinanKepada> listSalinanKepada) {
        this.listSalinanKepada = listSalinanKepada;
    }

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(String kodDokumen) {
        this.kodDokumen = kodDokumen;
    }
}
