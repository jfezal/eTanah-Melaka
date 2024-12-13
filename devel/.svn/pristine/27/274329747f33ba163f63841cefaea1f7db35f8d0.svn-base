/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;
import etanah.Configuration;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Permohonan;
import etanah.model.KodRujukan;
import etanah.model.KodUrusan;
import etanah.model.HakmilikPermohonan;
import etanah.model.Hakmilik;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import etanah.service.LaporanPelukisPelanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.KodRujukan;
import etanah.service.PendudukanSementaraMMKNService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.service.BPelService;
import etanah.service.common.NotisPenerimaanService;
import oracle.bpel.services.workflow.task.model.Task;
import java.io.File;
import etanah.util.FileUtil;
import etanah.util.DateUtil;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.LelongService;
import net.sourceforge.stripes.action.FileBean;
import java.util.ArrayList;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.model.Dokumen;

/**
 *
 * @author Admin
 */
@UrlBinding("/pengambilan/terima_warta_mlk")
public class Warta_MLKActionBean extends AbleActionBean {
     private static Logger logger = Logger.getLogger(Warta_MLKActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    private Permohonan permohonan;
    private Permohonan permohonanSebelum;
    private Hakmilik hakmilik;
    private KodUrusan kodUrusan;
    private KodCawangan cawangan;
    private KodBandarPekanMukim kodBandarPekanMukim;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PermohonanPengambilan> permohonanPengambilanList;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarListD;
    private List<PermohonanRujukanLuar> permohonanRujukanLuarList_sebelum;
    private FasaPermohonan fasaPermohonan;
    @Inject
    PengambilanService service;
    @Inject
    LaporanPelukisPelanService laporanPelukisPelanService;
    private String tarikhWarta;
    private String tarikhDisampai;
    private KodRujukan kodRujukan;
    private String noWarta;
    private String idPermohonan;
    private String idPengambilan;
    private String ulasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<PermohonanRujukanLuar> mrlListACQ;
    private List<PermohonanRujukanLuar> mrlListREG;
    @Inject
    private PendudukanSementaraMMKNService serviceMrl;
    PermohonanRujukanLuar rujluarREG;
    PermohonanRujukanLuar rujluarACQ;
    private List<HakmilikPermohonan> senaraiHakmilik;
    private String stageId;

    private FasaPermohonan mohonFasa;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    private String taskId;
    private String idRujukan;
    private Dokumen dokumen;
    private long idDokumen2;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasDAO;
    @Inject
    LelongService lelongService;
    FileBean fileToBeUploaded;
    @Inject
    etanah.Configuration conf;
    private List<String> idDokumenList = new ArrayList<String>();

    public FileBean getFileToBeUploaded() {
        return fileToBeUploaded;
    }

    public void setFileToBeUploaded(FileBean fileToBeUploaded) {
        this.fileToBeUploaded = fileToBeUploaded;
    }

    public List<String> getIdDokumenList() {
        return idDokumenList;
    }

    public void setIdDokumenList(List<String> idDokumenList) {
        this.idDokumenList = idDokumenList;
    }

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }
    public long getIdDokumen2() {
        return idDokumen2;
    }

    public void setIdDokumen2(long idDokumen2) {
        this.idDokumen2 = idDokumen2;
    }
    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getIdRujukan() {
        return idRujukan;
    }

    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }


    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("simpanWarta", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution hideKemasukkanWarta() {
        return new JSP("pengambilan/viewSejarahWartaShj.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("simpanWarta2", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta_mlk.jsp").addParameter("tab", "true");
    }
    
     public Resolution showForm3() {
        getContext().getRequest().setAttribute("simpanWarta2", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_JPPH.jsp").addParameter("tab", "true");
    }

//     public String stageId(String taskId, Pengguna pguna) {
//        BPelService service2 = new BPelService();
//        stageId = null;
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service2.getTaskFromBPel(taskId, pguna);
//            stageId = t.getSystemAttributes().getStage();
//        }
//        return stageId;
//    }
     
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        permohonan = permohonanDAO.findById(idPermohonan);
//        permohonanRujukanLuar=service.findByIdMohonRujLuar(idPermohonan);
//        permohonanPengambilan = service.findByIdMohon(permohonan.getIdPermohonan());
        permohonanPengambilanList = service.findByIDMohonPengambilan(idPermohonan);
        permohonanRujukanLuarList = service.findByIDMohonWarta(idPermohonan);
        permohonanRujukanLuarListD = service.findByIDMohonWartaD(idPermohonan);
        if(permohonanRujukanLuarListD.size()==0){
        logger.debug("permohonanRujukanLuarListD is null");

        }
         if(permohonanRujukanLuarList.size()==0){
        logger.debug("permohonanRujukanLuarList is null");

        }

        Permohonan pm = permohonanDAO.findById(idPermohonan);
        senaraiHakmilik=pm.getSenaraiHakmilik();
        permohonanSebelum = pm.getPermohonanSebelum();
        if (permohonanSebelum != null) {
            permohonanRujukanLuarList_sebelum = service.findByIDMohonWarta(permohonanSebelum.getIdPermohonan());
            logger.debug(permohonanSebelum.getIdPermohonan() + "permohonanRujukanLuarList_sebelum");
        }

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            permohonanPengambilan = service.findByIdMohon(idPermohonan);
            logger.debug(idPermohonan + "idPermohonan");



            

//             if(permohonanPengambilan != null)
//                    {
//                    if(permohonanPengambilan.getTarikhWarta() != null)
//                    tarikhWarta = sdf.format(permohonanPengambilan.getTarikhWarta()).substring(0, 10);
//                    noWarta = permohonanPengambilan.getNoWarta();
////                    }
//            if(permohonanRujukanLuar != null)
//                    {
//                    if(permohonanRujukanLuar.getTarikhDisampai()!= null)
//                    tarikhDisampai = sdf.format(permohonanRujukanLuar.getTarikhDisampai()).substring(0, 10);
//                    ulasan=permohonanRujukanLuar.getUlasan();
//                    tarikhWarta=sdf.format(permohonanRujukanLuar.getTarikhLulus()).substring(0, 10);
//                    noWarta=permohonanRujukanLuar.getItem();
//                    }

        }
    }

    public Resolution simpan() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String nowarta2 = getContext().getRequest().getParameter("noWarta");
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();

        permohonanRujukanLuarList = service.findByIDMohonWarta(idPermohonan);
        permohonanRujukanLuarListD = service.findByIDMohonWartaD(idPermohonan);
        mohonFasa = notisPenerimaanService.getFasaPermohonan(idPermohonan, "A08SrtIringWarta");
       BPelService bpelService = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, peng);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId: BPEL ON ----" + stageId);
        }
        stageId="A08SrtIringWarta";
        logger.debug("stageId"+stageId);
        if (stageId.equalsIgnoreCase("A08SrtIringWarta")||stageId.equalsIgnoreCase("A09SemakSrtIring")||stageId.equalsIgnoreCase("A10TerimaSemak")||stageId.equalsIgnoreCase("A13PembetulanWarta")) {
            logger.debug("Warta Borang D");
        System.out.println("list" + permohonanRujukanLuarList.size());
        if (permohonanRujukanLuarListD.size() == 0) {
            System.out.println("1");
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Asal");
            permohonanRujukanLuar.setItem(noWarta);
            permohonanRujukanLuar.setNamaPenyedia("Warta Borang D");
            if (StringUtils.isNotBlank(tarikhWarta))
            {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }
            permohonanRujukanLuarList.add(permohonanRujukanLuar);
            service.saveOrUpdateMRL(permohonanRujukanLuar);
        } else {
            System.out.println("2");
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(ia);
//                       KodRujukan kr=new KodRujukan();
//                       kr.setKod("NF");
//                       permohonanRujukanLuar.setKodRujukan(kr);
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Pembetulan");
            permohonanRujukanLuar.setItem(noWarta);
            if (StringUtils.isNotBlank(tarikhWarta)) {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }
            permohonanRujukanLuar.setNamaPenyedia("Warta Borang D");
            permohonanRujukanLuarList.add(permohonanRujukanLuar);
            service.saveOrUpdateMRL(permohonanRujukanLuar);

        }

        }else{
            logger.debug("Warta Seksyen 4");
        if (permohonanRujukanLuarList.size() == 0) {
            System.out.println("1");

            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Asal");
            permohonanRujukanLuar.setItem(noWarta);
            if (StringUtils.isNotBlank(tarikhWarta)) {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }
            permohonanRujukanLuarList.add(permohonanRujukanLuar);
            service.saveOrUpdateMRL(permohonanRujukanLuar);
        } else {
            System.out.println("2");
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(ia);
//                       KodRujukan kr=new KodRujukan();
//                       kr.setKod("NF");
//                       permohonanRujukanLuar.setKodRujukan(kr);
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Pembetulan");
            permohonanRujukanLuar.setItem(noWarta);
            if (StringUtils.isNotBlank(tarikhWarta)) {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }
            permohonanRujukanLuarList.add(permohonanRujukanLuar);
            service.saveOrUpdateMRL(permohonanRujukanLuar);

        }



        }

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("simpanWarta", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpan2() throws ParseException {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String nowarta2 = getContext().getRequest().getParameter("noWarta");
        permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit info = new InfoAudit();

//        permohonanRujukanLuarList=service.findByIDMohonWarta(idPermohonan);
        permohonanRujukanLuar = service.findByIdPermohonan(idPermohonan);


        if (permohonanRujukanLuar == null) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(ia);
//                       KodRujukan kr=new KodRujukan();
//                       kr.setKod("NF");

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Asal");
            permohonanRujukanLuar.setItem(noWarta);
            if (StringUtils.isNotBlank(tarikhWarta)) {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }

        } else {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            KodBandarPekanMukim pp = new KodBandarPekanMukim();
            pp.setKod(12);
            KodUOM m = new KodUOM();
            m.setKod("M");
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());

            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setCawangan(peng.getKodCawangan());
            permohonanRujukanLuar.setInfoAudit(ia);
//                       KodRujukan kr=new KodRujukan();
//                       kr.setKod("NF");
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("NF"));
            permohonanRujukanLuar.setUlasan(ulasan);
            permohonanRujukanLuar.setCatatan("Warta Pembetulan");
            permohonanRujukanLuar.setItem(noWarta);
            if (StringUtils.isNotBlank(tarikhWarta)) {
                tarikhWarta = getContext().getRequest().getParameter("tarikhWarta");
                permohonanRujukanLuar.setTarikhLulus(sdf.parse(tarikhWarta));
            }
            if (StringUtils.isNotBlank(tarikhDisampai)) {
                tarikhDisampai = getContext().getRequest().getParameter("tarikhDisampai");
                permohonanRujukanLuar.setTarikhDisampai(sdf.parse(tarikhDisampai));
            }

        }


//        permohonanRujukanLuarList.add(permohonanRujukanLuar);
        service.saveOrUpdateMRL(permohonanRujukanLuar);
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("simpanWarta2", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution deleteWarta() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanRujukanLuar = new PermohonanRujukanLuar();
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = laporanPelukisPelanService.findRujLuarByIdRL(Long.parseLong(idRujukan));
        System.out.println("permohonanRujukanLuarValue" + idRujukan);
        if (permohonanRujukanLuar != null) {
            laporanPelukisPelanService.deleteAllRL(permohonanRujukanLuar);
        }
        rehydrate();
        getContext().getRequest().setAttribute("simpanWarta", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_warta_mlk.jsp").addParameter("tab", "true");
    }

     public Resolution popupUpload() {
        idRujukan = (String) getContext().getRequest().getParameter("idRujukan");
//        LOG.info("idNotis :" + idNotis);
        return new JSP("pengambilan/negerisembilan/upload_fileWartaD.jsp").addParameter("popup", "true");
    }

        public Resolution processUploadDoc() {
        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idRujukan = (String) getContext().getRequest().getParameter("idRujukan");
        logger.debug("idRujukan :"+idRujukan);
        PermohonanRujukanLuar permohonanRujukanLuar =new PermohonanRujukanLuar();
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        logger.debug("permohonanRujukanLuar :"+permohonanRujukanLuar.getItem());


        InfoAudit ia = new InfoAudit();
        String DMS_BASE = conf.getProperty("document.path");
        String DMSPATH_WO_DMSBASE = null;
        String DMS_PATH = null;
        String fname = null;
         if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Asal")) {
            fname = "ACQWartaPembetulan_MLK.rdf";
        } else if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Pembetulan")) {
            fname = "ACQWartaPembetulan_MLK.rdf";
        }
        logger.info("idNotis : " + fname);
        DateUtil du = new DateUtil();
        try {
            if (p != null && fname != null && fileToBeUploaded != null) {
                String kodCawangan = p.getKodCawangan().getKod();
                DMSPATH_WO_DMSBASE = kodCawangan + File.separator + du.getYear() + File.separator
                        + du.getDateName(String.valueOf(du.getMonth() + 1))
                        + File.separator + du.getDay();
                DMS_PATH = DMS_BASE + (DMS_BASE.endsWith(File.separator) ? "" : File.separator) + DMSPATH_WO_DMSBASE;
                logger.debug("DIR to created = " + DMS_PATH);
                FileUtil fileUtil = new FileUtil(DMS_PATH);
                String namaFail = fileUtil.saveFile(fname, fileToBeUploaded.getInputStream());
                logger.info("namaFail :" + namaFail);
                Dokumen doc = new Dokumen();
                if (permohonanRujukanLuar.getCatatan() != null) {
                    ia = permohonanRujukanLuar.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                } else {
                    ia.setDimasukOleh(p);
                    ia.setTarikhMasuk(new java.util.Date());
                }
                DMSPATH_WO_DMSBASE = DMSPATH_WO_DMSBASE + File.separator + fname;
                if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Asal")) {
                doc.setKodDokumen(kodDokumenDAO.findById("8KUP"));
                }else
                {
                doc.setKodDokumen(kodDokumenDAO.findById("8KUS"));
                }
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat(fileToBeUploaded.getContentType());
                doc.setNamaFizikal(DMSPATH_WO_DMSBASE);
                doc.setInfoAudit(ia);
                Long idDoc = lelongService.simpanOrUpdateDokumen(doc);
                logger.info("idDoc :" + idDoc);
                if (permohonanRujukanLuar!=null) {
                    ia = permohonanRujukanLuar.getInfoAudit();
                    ia.setDiKemaskiniOleh(p);
                    ia.setTarikhKemaskini(new java.util.Date());
                    permohonanRujukanLuar.setDokumen(doc);
                    permohonanRujukanLuar.setInfoAudit(ia);
                    service.saveOrUpdateMRL(permohonanRujukanLuar);
                }

                addSimpleMessage("Muat naik fail berjaya.");
            } else {
                logger.error("parameter tidak mencukupi.");
                addSimpleError("Muat naik fail tidak berjaya.");
            }
        } catch (Exception ex) {
           logger.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
            addSimpleError("Muat naik fail tidak berjaya. Terdapat masalah pada fail.");
        }
        return new JSP("pengambilan/negerisembilan/upload_fileWartaD.jsp").addParameter("popup", "true");
    }

         public Resolution popupScan() {

        Pengguna p = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idRujukan = (String) getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        InfoAudit ia = new InfoAudit();
         ia.setDimasukOleh(p);
         ia.setTarikhMasuk(new java.util.Date());
        String fname = null;
        if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Asal")) {
            fname = "ACQWartaPembetulan_MLK.rdf";
        } else if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Pembetulan")) {
            fname = "ACQWartaPembetulan_MLK.rdf";
        }
        logger.info("idNotis : " + fname);
        try {
            if (p != null && fname != null) {
                Dokumen doc = new Dokumen();
                if (permohonanRujukanLuar.getCatatan().equalsIgnoreCase("Warta Asal")) {
                doc.setKodDokumen(kodDokumenDAO.findById("8KUP"));
                }else
                {
                doc.setKodDokumen(kodDokumenDAO.findById("8KUS"));
                }
                doc.setTajuk("Salinan Penghantaran-" + fname);
                doc.setNoVersi("1.0");
                doc.setKlasifikasi(kodKlasDAO.findById(1));
                doc.setFormat("application/pdf/image/gif");
                doc.setInfoAudit(ia);
                idDokumen2 = lelongService.simpanOrUpdateDokumen(doc);
                logger.info("idDoc :" + idDokumen2);
                // update at DasarTuntutanNotis


            } else {
                logger.error("parameter tidak mencukupi.");
            }
        } catch (Exception ex) {
            logger.error("Fatal protocol violation: " + ex.getMessage());
            ex.printStackTrace(); // comment this for production
        }
        return new JSP("pengambilan/negerisembilan/scan_docWartaD.jsp").addParameter("popup", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }

    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodBandarPekanMukim getKodBandarPekanMukim() {
        return kodBandarPekanMukim;
    }

    public void setKodBandarPekanMukim(KodBandarPekanMukim kodBandarPekanMukim) {
        this.kodBandarPekanMukim = kodBandarPekanMukim;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String nowarta) {
        this.noWarta = nowarta;
    }

    public String getIdPengambilan() {
        return idPengambilan;
    }

    public void setIdPengambilan(String idPengambilan) {
        this.idPengambilan = idPengambilan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public KodUrusan getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(KodUrusan kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public String getTarikhDisampai() {
        return tarikhDisampai;
    }

    public void setTarikhDisampai(String tarikhDisampai) {
        this.tarikhDisampai = tarikhDisampai;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<PermohonanPengambilan> getPermohonanPengambilanList() {
        return permohonanPengambilanList;
    }

    public void setPermohonanPengambilanList(List<PermohonanPengambilan> permohonanPengambilanList) {
        this.permohonanPengambilanList = permohonanPengambilanList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList() {
        return permohonanRujukanLuarList;
    }

    public void setPermohonanRujukanLuarList(List<PermohonanRujukanLuar> permohonanRujukanLuarList) {
        this.permohonanRujukanLuarList = permohonanRujukanLuarList;
    }

    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarList_sebelum() {
        return permohonanRujukanLuarList_sebelum;
    }

    public void setPermohonanRujukanLuarList_sebelum(List<PermohonanRujukanLuar> permohonanRujukanLuarList_sebelum) {
        this.permohonanRujukanLuarList_sebelum = permohonanRujukanLuarList_sebelum;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }


    public List<PermohonanRujukanLuar> getMrlListACQ() {
        return mrlListACQ;
    }

    public void setMrlListACQ(List<PermohonanRujukanLuar> mrlListACQ) {
        this.mrlListACQ = mrlListACQ;
    }

    public List<PermohonanRujukanLuar> getMrlListREG() {
        return mrlListREG;
    }

    public void setMrlListREG(List<PermohonanRujukanLuar> mrlListREG) {
        this.mrlListREG = mrlListREG;
    }

    public PermohonanRujukanLuar getRujluarREG() {
        return rujluarREG;
    }

    public void setRujluarREG(PermohonanRujukanLuar rujluarREG) {
        this.rujluarREG = rujluarREG;
    }
   
    public PermohonanRujukanLuar getRujluarACQ() {
        return rujluarACQ;
    }

    public void setRujluarACQ(PermohonanRujukanLuar rujluarACQ) {
        this.rujluarACQ = rujluarACQ;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }
    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public List<PermohonanRujukanLuar> getPermohonanRujukanLuarListD() {
        return permohonanRujukanLuarListD;
    }

    public void setPermohonanRujukanLuarListD(List<PermohonanRujukanLuar> permohonanRujukanLuarListD) {
        this.permohonanRujukanLuarListD = permohonanRujukanLuarListD;
    }

}
