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
import etanah.dao.UrusanDokumenDAO;
import etanah.model.BangunanTingkat;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodJabatan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodSeksyen;
import etanah.model.KodUrusan;
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
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
//import etanah.view.strata.PenarikanBalikActionBean;
//import etanah.view.stripes.pelupusan.disClass.DisCarianFasa;
import java.text.ParseException;

/**
 *
 * @author wazer
 */
@UrlBinding("/UtilitiStatusTanah")
public class UtilitiStatusTanahActionBean extends AbleActionBean {

    private List<KodBandarPekanMukim> senaraiKodBandarPekanMukim;
    List<FasaPermohonan> senaraiFasaPermohonan;
    private FasaPermohonan mohonFasa2 = new FasaPermohonan();
    private KodUrusan kodUrusan;
    private KodBandarPekanMukim kodBPM;
    List<PermohonanTuntutanBayar> senaraiMohonTuntutBayar;
    List<Permohonan> senaraiPermohonan;
//    private List<DisCarianFasa> senaraiCarianFasa;
    private static String kodNegeri;
    private Pengguna pguna;
    private KandunganFolder folderDok;
    private boolean ptg = true;
    private int selectedItem = -1;
    private String jabatan = "2";
    private List<KodUrusan> senaraiKodUrusan = new ArrayList<KodUrusan>();
    private KodUrusan ku;
    private HakmilikPermohonan mohonHakmilik;
    private KodUrusanDAO kodUrusanDAO;
    private List<UrusanDokumen> senaraiUrusanDokumen = new ArrayList<UrusanDokumen>();
    private UrusanDokumenDAO urusanDokumenDAO;
    private boolean flag = false;
    private KodJabatanDAO kodJabatanDAO;
//    private Permohonan permohonan;
    private List<KodJabatan> senaraiKodJabatan;
    private final static String DEFAULT_VIEW = "/WEB-INF/jsp/pelupusan/utiliti/utiliti_maklumat_laporan_tanah.jsp";
    @Inject
    KodService kodService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    StrataPtService strService;
    @Inject
    ListUtil listUtil;
    @Inject
    RegService regService;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    private etanah.Configuration conf;
//    private Object FolderId;
    private int idFolder;
    private int idDokumen;
    private String idPermohonan;
    private String idFasa;
    private String urusan;
    private String urusan2;
    private String BPM;
    private boolean stage1 = false;
    //================================
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan mohon;
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(UtilitiStatusTanahActionBean.class);
    private String syorRadio;
    private String syorPen;
    private String kodUrusan2;
    private String kodBPM2;
    private String idmohon;
    private String mohonFasa;
    private String hakmilik ;

    @DefaultHandler
    public Resolution showForm() {
        setFlag(Boolean.FALSE);

        if ("04".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "melaka";
        }
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            kodNegeri = "negeriSembilan";
        }

        return new ForwardResolution(DEFAULT_VIEW);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            LOG.info("Utiliti Laporan Tanah  = 04, MELAKA.");
        } else {
            LOG.info("Utiliti Laporan Tanah  = 05, NEGERI SEMBILAN.");
        }
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        senaraiPermohonan = new ArrayList<Permohonan>();
        if (StringUtils.isNotBlank(idPermohonan)) {
            //  mohon = new Permohonan();
            mohon = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonan);
            if (mohon.getStatus().equals("TK")) {
                addSimpleError("Id Permohonan tidak aktif atau tidak wujud");
            }
            if (idPermohonan != null) {
                senaraiFasaPermohonan = (List<FasaPermohonan>) getStrService().findFasaPermohonanByIdAliran(idPermohonan, idFasa);
                if (idPermohonan != null) {
                    mohonFasa2 = strService.findMohonFasa(idPermohonan, "laporan_tanah");
                    if (mohonFasa2 != null) {
                        if (mohonFasa2 != null) {
                            syorPen = mohonFasa2.getIdAliran();
                        }
                    }


//                    DisCarianFasa var1 = new DisCarianFasa();
//                    senaraiCarianFasa = new ArrayList<DisCarianFasa>();

//                    DisCarianFasa var1 = new DisCarianFasa();
//                    senaraiCarianFasa = new ArrayList<DisCarianFasa>();

                    FasaPermohonan mohonfasa = new FasaPermohonan();
                    mohon.getSenaraiFasa();
//                    var1.setIdFasa(mohonfasa.getIdFasa());

                    if (mohon.getKodUrusan() != null) {
                        senaraiKodUrusan = disLaporanTanahService.getPelupusanService().findKodUrusanByJabatan("6");
                        if (idPermohonan != null) {
                            if (mohon.getKodUrusan().getKod() != null) {
                                urusan2 = mohon.getKodUrusan().getNama();
                            }
                        }
                    }
                    if (mohon.getIdPermohonan() != null) {
                        mohonHakmilik = disLaporanTanahService.getPelupusanService().findHakmilikPermohonan(idPermohonan);
                        if (mohonHakmilik != null) {
                            kodBPM2 = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
                            
                        }

                    }
                    if (mohon.getIdPermohonan() != null){
                        mohonHakmilik = disLaporanTanahService.getPelupusanService().findHakmilikPermohonan(idPermohonan);
                        if (mohonHakmilik != null){
                            hakmilik  = mohonHakmilik.getHakmilik().getIdHakmilik();
                        }
                    }

                }
            }

        }
        setFlag(Boolean.TRUE);
    }

    public Resolution findStatusTanah() {
        idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        senaraiPermohonan = new ArrayList<Permohonan>();
        if (StringUtils.isNotBlank(idPermohonan)) {
            mohon = disLaporanTanahService.getPelupusanService().findPermohonanByIdPermohonan1(idPermohonan);
            if (mohon.getStatus().equals("TK")) {
                addSimpleError("Id Permohonan tidak aktif atau tidak wujud");
            }
            if (idPermohonan != null) {
                senaraiFasaPermohonan = (List<FasaPermohonan>) getStrService().findFasaPermohonanByIdAliran(idPermohonan, idFasa);
                if (idPermohonan != null) {
                    mohonFasa2 = strService.findMohonFasa(idPermohonan, "laporantanah");
                    if (mohonFasa2 != null) {
                        if (mohonFasa2.getKeputusan() != null) {
                            syorRadio = mohonFasa2.getKeputusan().getKod();
                            syorPen = mohonFasa2.getKeputusan().getNama();
                        }
                    }


//                    DisCarianFasa var1 = new DisCarianFasa();
//                    senaraiCarianFasa = new ArrayList<DisCarianFasa>();

//                    DisCarianFasa var1 = new DisCarianFasa();
//                    senaraiCarianFasa = new ArrayList<DisCarianFasa>();

                    FasaPermohonan mohonfasa = new FasaPermohonan();
                    mohon.getSenaraiFasa();
//                    var1.setIdFasa(mohonfasa.getIdFasa());

                    if (mohon.getKodUrusan() != null) {
                        senaraiKodUrusan = disLaporanTanahService.getPelupusanService().findKodUrusanByJabatan("6");
                        if (idPermohonan != null) {
                            if (mohon.getKodUrusan().getKod() != null) {
                                urusan2 = mohon.getKodUrusan().getNama();
                            }
                        }
                    }
                    if (mohon.getIdPermohonan() != null) {
                        mohonHakmilik = disLaporanTanahService.getPelupusanService().findHakmilikPermohonan(idPermohonan);
                        if (mohonHakmilik != null) {
                            kodBPM2 = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
                        }

                    }

                }
            }

        }
        setFlag(Boolean.TRUE);
        return new ForwardResolution(DEFAULT_VIEW);

    }

    public List<FasaPermohonan> getSenaraiFasaPermohonan() {
        return senaraiFasaPermohonan;
    }

    public void setSenaraiFasaPermohonan(List<FasaPermohonan> senaraiFasaPermohonan) {
        this.senaraiFasaPermohonan = senaraiFasaPermohonan;
    }

    public List<Permohonan> getSenaraiPermohonan() {
        return senaraiPermohonan;
    }

    public void setSenaraiPermohonan(List<Permohonan> senaraiPermohonan) {
        this.senaraiPermohonan = senaraiPermohonan;
    }

//    public List<DisCarianFasa> getSenaraiCarianFasa() {
//        return senaraiCarianFasa;
//    }
//
//    public void setSenaraiCarianFasa(List<DisCarianFasa> senaraiCarianFasa) {
//        this.senaraiCarianFasa = senaraiCarianFasa;
//    }

    public static String getKodNegeri() {
        return kodNegeri;
    }

    public static void setKodNegeri(String kodNegeri) {
        UtilitiStatusTanahActionBean.kodNegeri = kodNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public List<KodUrusan> getSenaraiKodUrusan() {
        return senaraiKodUrusan;
    }

    public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
        this.senaraiKodUrusan = senaraiKodUrusan;
    }

    public KodUrusan getKu() {
        return ku;
    }

    public void setKu(KodUrusan ku) {
        this.ku = ku;
    }

    public KodUrusanDAO getKodUrusanDAO() {
        return kodUrusanDAO;
    }

    public void setKodUrusanDAO(KodUrusanDAO kodUrusanDAO) {
        this.kodUrusanDAO = kodUrusanDAO;
    }

    public List<UrusanDokumen> getSenaraiUrusanDokumen() {
        return senaraiUrusanDokumen;
    }

    public void setSenaraiUrusanDokumen(List<UrusanDokumen> senaraiUrusanDokumen) {
        this.senaraiUrusanDokumen = senaraiUrusanDokumen;
    }

    public UrusanDokumenDAO getUrusanDokumenDAO() {
        return urusanDokumenDAO;
    }

    public void setUrusanDokumenDAO(UrusanDokumenDAO urusanDokumenDAO) {
        this.urusanDokumenDAO = urusanDokumenDAO;
    }

    public KodJabatanDAO getKodJabatanDAO() {
        return kodJabatanDAO;
    }

    public void setKodJabatanDAO(KodJabatanDAO kodJabatanDAO) {
        this.kodJabatanDAO = kodJabatanDAO;
    }

    public List<KodJabatan> getSenaraiKodJabatan() {
        return senaraiKodJabatan;
    }

    public void setSenaraiKodJabatan(List<KodJabatan> senaraiKodJabatan) {
        this.senaraiKodJabatan = senaraiKodJabatan;
    }

    public KodService getKodService() {
        return kodService;
    }

    public void setKodService(KodService kodService) {
        this.kodService = kodService;
    }

    public PermohonanTuntutanKosDAO getPermohonanTuntutanKosDAO() {
        return permohonanTuntutanKosDAO;
    }

    public void setPermohonanTuntutanKosDAO(PermohonanTuntutanKosDAO permohonanTuntutanKosDAO) {
        this.permohonanTuntutanKosDAO = permohonanTuntutanKosDAO;
    }

    public ListUtil getListUtil() {
        return listUtil;
    }

    public void setListUtil(ListUtil listUtil) {
        this.listUtil = listUtil;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<KodBandarPekanMukim> getSenaraiKodBandarPekanMukim() {
        return senaraiKodBandarPekanMukim;
    }

    public void setSenaraiKodBandarPekanMukim(List<KodBandarPekanMukim> senaraiKodBandarPekanMukim) {
        this.senaraiKodBandarPekanMukim = senaraiKodBandarPekanMukim;
    }

    public FasaPermohonan getMohonFasa2() {
        return mohonFasa2;
    }

    public void setMohonFasa2(FasaPermohonan mohonFasa2) {
        this.mohonFasa2 = mohonFasa2;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public String getUrusan() {
        return urusan;
    }

    public void setUrusan(String urusan) {
        this.urusan = urusan;
    }

    public String getUrusan2() {
        return urusan2;
    }

    public void setUrusan2(String urusan2) {
        this.urusan2 = urusan2;
    }

    public String getBPM() {
        return BPM;
    }

    public void setBPM(String BPM) {
        this.BPM = BPM;
    }

    public boolean isStage1() {
        return stage1;
    }

    public void setStage1(boolean stage1) {
        this.stage1 = stage1;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public String getSyorRadio() {
        return syorRadio;
    }

    public void setSyorRadio(String syorRadio) {
        this.syorRadio = syorRadio;
    }

    public String getSyorPen() {
        return syorPen;
    }

    public void setSyorPen(String syorPen) {
        this.syorPen = syorPen;
    }

    public String getKodUrusan2() {
        return kodUrusan2;
    }

    public void setKodUrusan2(String kodUrusan2) {
        this.kodUrusan2 = kodUrusan2;
    }

    public String getKodBPM2() {
        return kodBPM2;
    }

    public void setKodBPM2(String kodBPM2) {
        this.kodBPM2 = kodBPM2;
    }

    public String getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(String hakmilik) {
        this.hakmilik = hakmilik;
    }
    
}
