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
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
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

/**
 *
 * @author Srinivas and Vijay
 */
@UrlBinding("/pelupusan/jabatan_teknikalMMKNA")
public class JabatanTeknikalForMMKNActionBean extends AbleActionBean {

    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    RegService regService;
    @Inject
    NotaService notaService;
     @Inject
    ListUtil listUtil;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    private etanah.Configuration conf;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    FolderDokumenDAO folderDokumenDAO;
    @Inject
    SemakDokumenService semakDokumenService;
    @Inject
    DokumenService dokumenService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    KandunganFolderService kandunganFolderService;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    PermohonanTandatanganDokumenDAO  permohonanTandatanganDokumenDAO;
    private static final Logger LOG = Logger.getLogger(JabatanTeknikalForMMKNActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private boolean viewOnly;
    private String idPermohonan;
    private String tarikhPermohonan;
    private Permohonan permohonan;
    private List<KodAgensi> listKodAgensi;
    // hidden fields
    private String kod;
    private String kodAgensiNama;
    // New kodAgensi details
    private KodAgensi kodAgensi;
    private KodNegeri kodNegeri;
    private String radio_;
    private String index;
    private String recordCount;
    private List<PermohonanRujukanLuar> senaraiRujukanLuar;
    private List<PermohonanRujukanLuarDokumen> senaraiRujukanDok;
    private List<KodAgensi> kodAgensis = new ArrayList<KodAgensi>();
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanRujukanLuar mohonRujLuar;
    private PermohonanLaporanPelan permohonanLaporPelan;
    private FasaPermohonan fasaPermohonan;
    private String rowCount1;
    private String kategoriAgensi;
    private String Penyelerasan;
    private String syor;
    private String kNegeri;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Pengguna pguna;
    private String stageId;
    private FolderDokumen folderDokumen;
    private String workflowId;
    @Inject
    private ReportUtil reportUtil;
    private String idFolder;
    private String idRujukan;
    private String[] mandatori;
     private List<KodKementerian> senaraiKodKementerian;
     private String kodKatgAgensi;
     private String catatan;
     private PermohonanTandatanganDokumen tandatanganDokumenTemp;
     private PermohonanTandatanganDokumen tandatanganDokumen;
     private String ditundatangan;
     private String ditundatangan1;
     private List<SuratRujukanLuar> suratRujukanLuartemp;
     private SuratRujukanLuar   suratRujukanLuar;
     private InfoAudit ia;
     private Pengguna peng;
     private boolean edit = false;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensiAndKodRujukan(idPermohonan);
            /*if (senaraiRujukanLuar != null) {
                recordCount = String.valueOf(senaraiRujukanLuar.size());
                for (PermohonanRujukanLuar prl : senaraiRujukanLuar) {
                    senaraiRujukanDok = pelupusanService.findListDokumenRujukan(String.valueOf(prl.getIdRujukan()));
                }
            }*/
        }
        return new JSP("pelupusan/list_jabatan_teknikalMMKN.jsp").addParameter("tab", "true");
    }




    // click on edit button
    public Resolution showEditJabatanTeknikal() {

        String idRujukan;
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        if(idRujukan != null && !idRujukan.equals("")){
            mohonRujLuar = new PermohonanRujukanLuar();
            mohonRujLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
            edit = true;

        }else{
            String idValue_ref = "";
            String idValue = (idValue_ref.equals("A")) ?  "ADN" : "JTK"; // DEFAULT JTK : ADN
            listKodAgensi = listUtil.getUlasan(idValue);
        }

        return new JSP("pelupusan/popup_jabatan_teknikalMMKN.jsp").addParameter("popup", "true");
    }


    public Resolution changeUlasan() {

        String idValue_ref =  getContext().getRequest().getParameter("ulasanValue");
        String idValue = "";
        idValue = (idValue_ref.equals("A")) ? "ADN" : "JTK";
        listKodAgensi = listUtil.getUlasan(idValue);
        if(idValue_ref.equals("A")){
            getContext().getRequest().setAttribute("pny", Boolean.FALSE);
        }
        else {
            getContext().getRequest().setAttribute("pny", Boolean.TRUE);
        }
        return new JSP("pelupusan/popup_jabatan_teknikalMMKN.jsp").addParameter("popup", "true");
    }

    //click on reset button
    public Resolution resetUlasan() {

        return new JSP("pelupusan/popup_jabatan_teknikalMMKN.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
       PermohonanRujukanLuar mohonRujLuarTemp = new PermohonanRujukanLuar();
       String idNotifikasi =   getContext().getRequest().getParameter("idNotifikasi");
        mohonRujLuarTemp = permohonanRujukanLuarDAO.findById(Long.parseLong(idNotifikasi));
        if (mohonRujLuarTemp != null) {
            pelupusanService.deleteAll(mohonRujLuarTemp);
        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
       if (idPermohonan != null) {
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensiAndKodRujukan(idPermohonan);

        }
        return new JSP("pelupusan/list_jabatan_teknikalMMKN.jsp").addParameter("tab", "true");

    }

         // click on edit button

    // click on save button in edit page
    public Resolution simpanJabatanTeknikal() {
            PermohonanRujukanLuar mohonRujLuarTemp = new PermohonanRujukanLuar();
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            permohonan = permohonanDAO.findById(idPermohonan);
            Long idRujukan = mohonRujLuar.getIdRujukan();
         if (idRujukan != null && idRujukan > 0 ) {
             mohonRujLuarTemp = permohonanRujukanLuarDAO.findById(idRujukan);
             mohonRujLuarTemp.setNamaAgensi(mohonRujLuar.getNamaAgensi());
        }else{
            ia = new InfoAudit();
            peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            mohonRujLuarTemp.setInfoAudit(ia);
            mohonRujLuarTemp.setPermohonan(permohonan);
            mohonRujLuarTemp.setCawangan(permohonan.getCawangan());
            mohonRujLuarTemp.setTarikhDisampai(new java.util.Date());
            KodAgensi kodnama = pelupusanService.getSenaraikodAgensiList(mohonRujLuar.getNamaAgensi());
            mohonRujLuarTemp.setAgensi(kodnama);
            KodRujukan kodRujukan = kodRujukanDAO.findById("UT");
            mohonRujLuarTemp.setKodRujukan(kodRujukan);
            mohonRujLuarTemp.setNamaAgensi(kodnama.getNama());
            mohonRujLuarTemp.setTarikhRujukan(new java.util.Date());
            mohonRujLuarTemp.setStatusUlasan('L');
            mohonRujLuarTemp.setTempohHari(14);
            mohonRujLuarTemp.setTarikhJangkaTerima(addBusinessDays(14));
        }
            if(mohonRujLuar.getTarikhTerima()!=null);
                mohonRujLuarTemp.setTarikhTerima(mohonRujLuar.getTarikhTerima());
            mohonRujLuarTemp.setNamaPenyedia(mohonRujLuar.getNamaPenyedia());
            mohonRujLuarTemp.setNoRujukan(mohonRujLuar.getNoRujukan());
            mohonRujLuarTemp.setDiSokong(mohonRujLuar.getDiSokong());
            mohonRujLuarTemp.setUlasan(mohonRujLuar.getUlasan());
            notaService.simpanPermohonanRujLuar(mohonRujLuarTemp);
            senaraiRujukanLuar = pelupusanService.getSenaraiRujLuarByIDPermohonanAgensiAndKodRujukan(idPermohonan);
        return new JSP("pelupusan/list_jabatan_teknikalMMKN.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

    }

    public static Date addBusinessDays(int numberOfDays){

   Date  baseDate = new Date();

    Calendar baseDateCal = Calendar.getInstance();
    baseDateCal.setTime(baseDate);

    for(int i = 0; i < numberOfDays; i++){

        baseDateCal.add(Calendar.DATE,1);
        if(baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
           baseDateCal.add(Calendar.DATE,1);
        }
        if(baseDateCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
           baseDateCal.add(Calendar.DATE,1);
        }
    }
    return baseDateCal.getTime();
}

    /**
     * Get the date java.util.Date object for days after current date
     *
     * @param days
     * @return
     */
    public static Date getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days); // +days
        return cal.getTime();
    }




    public List<PermohonanRujukanLuar> getSenaraiRujukanLuar() {
        return senaraiRujukanLuar;
    }

    public void setSenaraiRujukanLuar(List<PermohonanRujukanLuar> senaraiRujukanLuar) {
        this.senaraiRujukanLuar = senaraiRujukanLuar;
    }

    public KodAgensi getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(KodAgensi kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public KodNegeri getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(KodNegeri kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public String getRadio_() {
        return radio_;
    }

    public void setRadio_(String radio_) {
        this.radio_ = radio_;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getRowCount1() {
        return rowCount1;
    }

    public void setRowCount1(String rowCount1) {
        this.rowCount1 = rowCount1;
    }

    public String getPenyelerasan() {
        return Penyelerasan;
    }

    public void setPenyelerasan(String Penyelerasan) {
        this.Penyelerasan = Penyelerasan;
    }

    public String getTarikhPermohonan() {
        return tarikhPermohonan;
    }

    public void setTarikhPermohonan(String tarikhPermohonan) {
        this.tarikhPermohonan = tarikhPermohonan;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public String getKategoriAgensi() {
        return kategoriAgensi;
    }

    public void setKategoriAgensi(String kategoriAgensi) {
        this.kategoriAgensi = kategoriAgensi;
    }

    public PermohonanLaporanPelan getPermohonanLaporPelan() {
        return permohonanLaporPelan;
    }

    public void setPermohoanLaporPelan(PermohonanLaporanPelan permohonanLaporPelan) {
        this.permohonanLaporPelan = permohonanLaporPelan;
    }

    public String getSyor() {
        return syor;
    }

    public void setSyor(String syor) {
        this.syor = syor;
    }

    public String getkNegeri() {
        return kNegeri;
    }

    public void setkNegeri(String kNegeri) {
        this.kNegeri = kNegeri;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }


    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public List<PermohonanRujukanLuarDokumen> getSenaraiRujukanDok() {
        return senaraiRujukanDok;
    }

    public void setSenaraiRujukanDok(List<PermohonanRujukanLuarDokumen> senaraiRujukanDok) {
        this.senaraiRujukanDok = senaraiRujukanDok;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }

    public String[] getMandatori() {
        return mandatori;
    }

    public void setMandatori(String[] mandatori) {
        this.mandatori = mandatori;
    }

    public List<KodKementerian> getSenaraiKodKementerian() {
        return senaraiKodKementerian;
    }

    public void setSenaraiKodKementerian(List<KodKementerian> senaraiKodKementerian) {
        this.senaraiKodKementerian = senaraiKodKementerian;
    }

    public String getKodKatgAgensi() {
        return kodKatgAgensi;
    }

    public void setKodKatgAgensi(String kodKatgAgensi) {
        this.kodKatgAgensi = kodKatgAgensi;
    }



    public List<KodAgensi> getKodAgensis() {
        return kodAgensis;
    }

    public void setKodAgensis(List<KodAgensi> kodAgensis) {
        this.kodAgensis = kodAgensis;
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

    public String getDitundatangan1() {
        return ditundatangan1;
    }

    public void setDitundatangan1(String ditundatangan1) {
        this.ditundatangan1 = ditundatangan1;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }


}
