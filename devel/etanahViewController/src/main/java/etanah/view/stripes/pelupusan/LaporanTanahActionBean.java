/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import etanah.view.stripes.common.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodSeksyenDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodKecerunanTanah;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.model.KodDaerah ;
import etanah.model.KodRizab;
import etanah.model.KodRujukan;
import etanah.model.KodSeksyen;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.service.PelupusanService;
import etanah.service.common.TanahRizabService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import java.io.File;
import net.sourceforge.stripes.action.FileBean;

/**
 *
 * @author muhammad.amin
 * modified by afham
 * modified by Rohan
 *
 */
@UrlBinding("/pelupusan/laporan_tanah")
public class LaporanTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    CommonService commonService;
    @Inject
    PelupusanService plpservice;
    @Inject
    KodSeksyenDAO kodSeksyenDAO;
    List<HakmilikPermohonan> senaraiHakmilik;
    private Hakmilik hakmilik;
    private String idHakmilik;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private TanahRizabPermohonan tanahRizabPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private static final Logger LOG = Logger.getLogger(LaporanTanahActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<TanahRizabPermohonan> tanahRizabPermohonanList;
    private char pandanganImej;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;
    private FileBean fileToBeUpload;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String catatan;

     private KodBandarPekanMukim bandarPekanMukim;
     private KodKecerunanTanah kecerunanTanah;
     private KodDaerah daerah;
     private KodCawangan cawangan;
     private String noLot;
     private String noLitho;
     private String noWarta;
     private String lokasi;
     private KodRizab rizab;
     private String tarikhWarta;
     private String idtanahrizabPermohonan;
     private String ulasan;
     boolean p ;
     private List<KodSeksyen> kodSeksyenList;
     private Pengguna pguna;
     private String idPermohonan;
     private PermohonanLaporanKawasan permohonanLaporanKawasan;
     private String pbt;
     private  String  rizab1;


    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP("pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");

    }

        public Resolution uploadDoc() {
//             hakmilik = hakmilikDAO.findById(idHakmilik);
//             LOG.info("-------------idHakmilik------------------"+hakmilik);
//        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        pandanganImej = getContext().getRequest().getParameter("pandanganImej").charAt(0);
        return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
    }

        public Resolution simpanImejLaporan() throws Exception{
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            InfoAudit info;
            info = new InfoAudit();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty()))
                laporanTanah = listLaporanTanah.get(0);

            if (laporanTanah == null) {
                laporanTanah = new LaporanTanah();
                laporanTanah.setInfoAudit(info);
                laporanTanah.setPermohonan(permohonan);
                laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            }

            String dokumenPath = conf.getProperty("document.path");
            dokumenPath = dokumenPath + (dokumenPath.endsWith(File.separator) ? "" : File.separator) + permohonan.getFolderDokumen().getFolderId();

            if (catatan == null){
                addSimpleError("Sila masukkan Catatan.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }

            if (fileToBeUpload == null) {
                addSimpleError("Please select file to be Upload.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            } else if (!(fileToBeUpload.getFileName().endsWith(".jpg") || fileToBeUpload.getFileName().endsWith(".bmp") || fileToBeUpload.getFileName().endsWith(".png"))) {
                addSimpleError("Please select valid Image.");
                return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
            }
            if ((catatan != null) && (fileToBeUpload != null)){
                Dokumen doc = commonService.saveDokumen(dokumenPath, fileToBeUpload, permohonan.getFolderDokumen().getFolderId(), info,permohonan);
                ImejLaporan imejLaporan =  new ImejLaporan();
                imejLaporan.setCawangan(permohonan.getCawangan());
                imejLaporan.setDokumen(doc);
                imejLaporan.setPandanganImej(pandanganImej);
                imejLaporan.setCatatan(catatan);
                imejLaporan.setInfoAudit(info);
                imejLaporan.setLaporanTanah(laporanTanah);
                laporanTanahService.simpanHakmilikImej(imejLaporan);

                addSimpleMessage("Muat naik fail berjaya.");
            }

        }

        return new JSP("pelupusan/Upload_imej_Laporan_Tanah_Pelupusan.jsp").addParameter("popup", "true");
    }

    public Resolution hakMilikPopup() {
        p = false ;
        return new JSP("pelupusan/kemasukan_tanahrizab.jsp").addParameter("popup", "true").addParameter("showForm", "false");
      }

     public Resolution showeditTanahRizab()
      {

         String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahRizabPermohonan=tanahRizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        p = false ;
        return new JSP("pelupusan/kemaskini_tanahrizab.jsp").addParameter("popup", "true");
    }

     public Resolution simapanTanahRizab() throws ParseException  {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahRizabPermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahRizabPermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahRizabPermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahRizabPermohonan.setInfoAudit(info);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahRizabPermohonan.setDaerah(daerah);
            tanahRizabPermohonan.setCawangan(cawangan);
            tanahRizabPermohonan.setNoLot(noLot);
            tanahRizabPermohonan.setNoLitho(noLitho);
            tanahRizabPermohonan.setNoWarta(noWarta);
            tanahRizabPermohonan.setLokasi(lokasi);
            tanahRizabPermohonan.setRizab(rizab);
            tanahRizabPermohonan.setAktif('Y');
            tanahRizabPermohonanList.add(tanahRizabPermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
               addSimpleMessage("Maklumat telah berjaya disimpan");
        }else{
             tanahRizabPermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahRizabPermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahRizabPermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahRizabPermohonan.setInfoAudit(info);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahRizabPermohonan.setDaerah(daerah);
            tanahRizabPermohonan.setCawangan(cawangan);
            tanahRizabPermohonan.setNoLot(noLot);
            tanahRizabPermohonan.setNoLitho(noLitho);
            tanahRizabPermohonan.setNoWarta(noWarta);
            tanahRizabPermohonan.setLokasi(lokasi);
            tanahRizabPermohonan.setRizab(rizab);
            tanahRizabPermohonan.setAktif('Y');
            tanahRizabPermohonanList.add(tanahRizabPermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
               addSimpleMessage("Maklumat telah berjaya disimpan");}


        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");


 }
      public Resolution simpanTanahRizab() throws ParseException  {
         String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahRizabPermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahRizabPermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahRizabPermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahRizabPermohonan.setInfoAudit(info);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahRizabPermohonan.setDaerah(daerah);
            tanahRizabPermohonan.setCawangan(cawangan);
            tanahRizabPermohonan.setNoLot(noLot);
            tanahRizabPermohonan.setNoLitho(noLitho);
            tanahRizabPermohonan.setNoWarta(noWarta);
            tanahRizabPermohonan.setLokasi(lokasi);
            tanahRizabPermohonan.setRizab(rizab);
            tanahRizabPermohonanList.add(tanahRizabPermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
               addSimpleMessage("Maklumat telah berjaya disimpan");
        }else{
             tanahRizabPermohonan = new TanahRizabPermohonan();
                if (StringUtils.isNotBlank(tarikhWarta)) {
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    tanahRizabPermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                    tarikhWarta = sdf.format(tanahRizabPermohonan.getTarikhWarta());
//                  tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
                }
            tanahRizabPermohonan.setInfoAudit(info);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahRizabPermohonan.setDaerah(daerah);
            tanahRizabPermohonan.setCawangan(cawangan);
            tanahRizabPermohonan.setNoLot(noLot);
            tanahRizabPermohonan.setNoLitho(noLitho);
            tanahRizabPermohonan.setNoWarta(noWarta);
            tanahRizabPermohonan.setLokasi(lokasi);
            tanahRizabPermohonan.setRizab(rizab);
            tanahRizabPermohonanList.add(tanahRizabPermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");}


       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_pelukis_pelan.jsp").addParameter("tab", "true");



    }

     public Resolution edit() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (ia == null) {
            tanahRizabPermohonan = new TanahRizabPermohonan();
            ia = new InfoAudit();
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahRizabPermohonan.setInfoAudit(ia);
        } else {

            ia.setDiKemaskiniOleh(pg);
            ia.setTarikhKemaskini(new java.util.Date());
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahRizabPermohonan.setInfoAudit(ia);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setAktif('Y');
        }
        tanahRizabService.saveOrUpdatetanahRizab(tanahRizabPermohonan);

        addSimpleMessage("Data Telah Berjaya dikemaskini");
          return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");

    }

          public Resolution deleteSingle()  {
             InfoAudit ia = new InfoAudit();
            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            tanahRizabPermohonan = new TanahRizabPermohonan();
            String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
             tanahRizabPermohonan=tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));

            if (tanahRizabPermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahRizabPermohonan.setInfoAudit(ia);
            tanahRizabPermohonan.setPermohonan(permohonan);
            tanahRizabPermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahRizabPermohonan.setDaerah(daerah);
            tanahRizabPermohonan.setCawangan(cawangan);
            tanahRizabPermohonan.setNoLot(noLot);
            tanahRizabPermohonan.setNoLitho(noLitho);
            tanahRizabPermohonan.setNoWarta(noWarta);
            tanahRizabPermohonan.setLokasi(lokasi);
            tanahRizabPermohonan.setRizab(rizab);
            tanahRizabService.deleteAll(tanahRizabPermohonan);
            //suratkuasaService.deleteAll(wakilHakmilik);
}
            return new RedirectResolution(LaporanTanahActionBean.class, "locate");

}


//    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
         System.out.println("-------------rehydrate---------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//         hakmilik = hakmilikDAO.findById(idHakmilik);
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        stageId(taskId);
        permohonanLaporanPelan = plpservice.findByIdPermohonanPelan(idPermohonan);
        LOG.info("--------permohonanLaporanPelan-----------"+permohonanLaporanPelan);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();
        pbt="1";
        permohonanLaporanKawasan = plpservice.findByidMohonKodRizab(idPermohonan, pbt);
        LOG.info("--------permohonanLaporanKawasan-----------"+permohonanLaporanKawasan);
        if(permohonanLaporanKawasan!=null){
       rizab1 =   permohonanLaporanKawasan.getKodRizab().getNama();
        }

        if (idPermohonan != null) {

            int syx=0;
            permohonan = permohonanDAO.findById(idPermohonan);
             hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
             tanahRizabPermohonanList = permohonan.getSenaraiTanahRizab();
             if(hakmilikPermohonanList != null){
                hakmilikPermohonan = plpservice.findByIdPermohonan(idPermohonan) ;
                 if(hakmilikPermohonan != null){
                if(hakmilikPermohonan.getBandarPekanMukimBaru()!=null){
                  syx =  hakmilikPermohonan.getBandarPekanMukimBaru().getKod();
                kodSeksyenList = plpservice.getSenaraiKodSeksyenByBPM(syx) ;
            }
          }
         }

//             for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
//
//                 Hakmilik hak = hakmilikPermohonan.getHakmilik();
//
//                 List<HakmilikPihakBerkepentingan> listHakPihak;
//                 listHakPihak =  hak.getSenaraiPihakBerkepentingan();
//                 pihakKepentinganList.addAll(listHakPihak);
//                 ImejLaporan imejLaporan = laporanTanahService.getLaporImejByHakmilik(hakmilikPermohonan.getHakmilik().getIdHakmilik());
//                 try{
//                     Long idDokumen = imejLaporan.getDokumen().getIdDokumen();
//                     imej.add(idDokumen.toString());
//                     }catch(Exception e){
//                     }
//
//
//            }

            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;

            listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty())) {
                laporanTanah = listLaporanTanah.get(0);

//              String[] n = {"laporanTanah"};
//              Object [] o={laporanTanah};
//           List<ImejLaporan>list=  imejLaporanDAO.findByEqualCriterias(n, o, null);
                imejLaporanList = laporanTanahService.getLaporImejByLaporanId(laporanTanah.getIdLaporan());
                utaraImejLaporanList = laporanTanahService.getUtaraLaporImejByLaporanId(laporanTanah.getIdLaporan());
                selatanImejLaporanList = laporanTanahService.getSelatanLaporImejByLaporanId(laporanTanah.getIdLaporan());
                timurImejLaporanList = laporanTanahService.getTimurLaporImejByLaporanId(laporanTanah.getIdLaporan());
                baratImejLaporanList = laporanTanahService.getBaratLaporImejByLaporanId(laporanTanah.getIdLaporan());

//                if (laporanTanah.getTarikhMulaUsaha() != null) {
//                    date = sdf.format(laporanTanah.getTarikhMulaUsaha());
//                }
            }

            List<FasaPermohonan> listFasa;
            listFasa = fasaPermohonanDAO.findByEqualCriterias(tname, model, null);
              if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
//                    if (fp.getIdPengguna().equals("kppt1")) {
//                        fasaPermohonan = listFasa.get(i);
//                    }
                    if (fp.getIdAliran().equals("laporantanah")) {
                        fasaPermohonan = listFasa.get(i);
                        ulasan = fasaPermohonan.getUlasan() ;
                    }
                }
            }
//            if (!(listFasa.isEmpty())) {
//                for (int i = 0; i < listFasa.size(); i++) {
//                    FasaPermohonan fp = new FasaPermohonan();
//                    fp = listFasa.get(i);
//                    if (fp.getIdPengguna().equals("mtkpptambil1")) {
//                        fasaPermohonan = listFasa.get(i);
//                    }
//                }
//            }

            List<PermohonanRujukanLuar> listRujukanLuar;
            listRujukanLuar = permohonanRujukanLuarDAO.findByEqualCriterias(tname, model, null);

//            if (!(listRujukanLuar.isEmpty())) {
//                for (int i = 0; i < listRujukanLuar.size(); i++) {
//                    PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
//                    rujL = listRujukanLuar.get(i);
//
////                    if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
////                        permohonanRujukanLuar = listRujukanLuar.get(i);
////                    }
////=======
//                    if (rujL.getKodDokumenRujukan() != null) {
//                        if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
//                            permohonanRujukanLuar = listRujukanLuar.get(i);
//                        }
//                    }
//
//                }
//            }
        }

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String format1="image/jpeg";
        String format2="image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1,format2, pguna.getNama());

    }

     public String stageId(String taskId) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }

        public boolean validation() {
        boolean flag = false;
        if(fasaPermohonan!=null)
        if(fasaPermohonan.getUlasan()==null||fasaPermohonan.getUlasan().trim().equals("")){
         flag = true;
         addSimpleError("Ulasan is empty");
        }
        return flag;
       }


   public Resolution simpanLaporanTanah() throws Exception {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
         String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            List<LaporanTanah> listLaporanTanah;
         listLaporanTanah = laporanTanahDAO.findByEqualCriterias(tname, model, null);

            if (!(listLaporanTanah.isEmpty()))
                laporanTanah = listLaporanTanah.get(0);

        LOG.info("--------------------listLaporanTanah-----------------------------------"+listLaporanTanah);
//        if (validation()) {
//            LOG.info("=========--inside----validation----------------");
//            getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
//        }
        String kodS = getContext().getRequest().getParameter("kodSeksyen.nama");
        System.out.println("-----kodS---"+kodS) ;

        if((kodS!=null) && !kodS.equals("0")){
            Integer a = Integer.parseInt(kodS) ;
            int kod = a.intValue() ;
            KodSeksyen kodSeksyen = plpservice.findByKodSeksyen(kod) ;
            hakmilikPermohonan.setKodSeksyen(kodSeksyen);
            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }else {
//            hakmilikPermohonan.setKodSeksyen(null);
//            plpservice.simpanHakmilikPermohonan(hakmilikPermohonan);
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        LOG.info("-------------permohonan----------------------"+permohonan);
        if (laporanTanah != null) {
             System.out.println("-------------simpan if---------");
             LOG.info("-------------permohonan----------------------"+permohonan+"---------permohonanDAO.findById(idPermohonan)----------"+permohonanDAO.findById(idPermohonan));
//            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
            if(laporanTanah.getAdaBangunan()!= null){
                if(laporanTanah.getAdaBangunan() == 'T'){
                    laporanTanah.setBilanganBangunan(0);
                }

            }

//            if (date != null) {
//
//                try {
//                    laporanTanah.setTarikhMulaUsaha(sdf.parse(date));
//                } catch (ParseException ex) {
//                    LOG.error("exception: " + ex.getMessage());
//                    throw ex;
//                }
//            }

            laporanTanahService.simpanLaporanTanah(laporanTanah);


        } else {
             System.out.println("-------------simpan else---");
            laporanTanah = new LaporanTanah();
          laporanTanah.setPermohonan(permohonan);
            laporanTanah.setPermohonan(permohonanDAO.findById(idPermohonan));
            laporanTanah.setInfoAudit(infoAudit);
             laporanTanahService.simpanLaporanTanah(laporanTanah);

        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------"+taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--"+stageId);
        }

        stageId = "laporantanah";


        if (fasaPermohonan != null) {
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setIdAliran(stageId);
            fasaPermohonan.setUlasan(ulasan);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

//            if (permohonanRujukanLuar != null) {
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
//                permohonanRujukanLuar.setInfoAudit(infoAudit);
//                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
//                KodRujukan kodRuj = new KodRujukan();
//                kodRuj.setKod("FL");
//                permohonanRujukanLuar.setKodRujukan(kodRuj);
//
//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
//                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
//            }else
//            {
//                permohonanRujukanLuar=new PermohonanRujukanLuar();
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
//                permohonanRujukanLuar.setInfoAudit(infoAudit);
//                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
//                KodRujukan kodRuj = new KodRujukan();
//                kodRuj.setKod("FL");
//                permohonanRujukanLuar.setKodRujukan(kodRuj);
//
//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
//                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
//
//            }
        }else
        {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan.setPermohonan(permohonan);
            fasaPermohonan.setCawangan(permohonan.getCawangan());
            fasaPermohonan.setInfoAudit(infoAudit);
            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
            fasaPermohonan.setUlasan(ulasan);
            fasaPermohonan.setIdAliran(stageId);
            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);

//            if (permohonanRujukanLuar != null) {
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
//                permohonanRujukanLuar.setInfoAudit(infoAudit);
//                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
//                KodRujukan kodRuj = new KodRujukan();
//                kodRuj.setKod("NF");
//                permohonanRujukanLuar.setKodRujukan(kodRuj);
//
//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
//                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
//            }else
//            {
//                permohonanRujukanLuar=new PermohonanRujukanLuar();
//                permohonanRujukanLuar.setPermohonan(permohonan);
//                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
//                permohonanRujukanLuar.setInfoAudit(infoAudit);
//                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
//                KodRujukan kodRuj = new KodRujukan();
//                kodRuj.setKod("NF");
//                permohonanRujukanLuar.setKodRujukan(kodRuj);
//
//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
//                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
//
//            }

        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
         getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution addHakmilikImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if(idHakmilik != null){
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByHakmilikDokumen(idHakmilik,dokumen.getIdDokumen());
        if(imejLaporan == null) {
        imejLaporan =  new ImejLaporan();
        imejLaporan.setCawangan(cawangan);
        imejLaporan.setDokumen(dokumen);
        imejLaporan.setHakmilik(hakmilik);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        imejLaporan.setInfoAudit(infoAudit);
        imejLaporan.setLaporanTanah(laporanTanah);
        laporanTanahService.simpanHakmilikImej(imejLaporan);
        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }else{
            addSimpleMessage("Imej already exist for Hakmilik.");
        }

       getContext().getRequest().setAttribute("edit", Boolean.TRUE);
       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
    }

    public Resolution addLaporanImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah,dokumen,pandanganImej);
        if(imejLaporan == null) {
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        imejLaporan =  new ImejLaporan();
        imejLaporan.setCawangan(cawangan);
        imejLaporan.setDokumen(dokumen);
        imejLaporan.setPandanganImej(pandanganImej);
        imejLaporan.setInfoAudit(infoAudit);
        imejLaporan.setLaporanTanah(laporanTanah);
        laporanTanahService.simpanHakmilikImej(imejLaporan);

        rehydrate();
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        }else{
            addSimpleMessage("Imej already exist.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

       return new ForwardResolution("/WEB-INF/jsp/pelupusan/laporan_tanah2.jsp").addParameter("tab", "true");
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public LaporanTanah getLaporanTanah() {
        return laporanTanah;
    }

    public void setLaporanTanah(LaporanTanah laporanTanah) {
        this.laporanTanah = laporanTanah;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikPermohonan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }

    public List<TanahRizabPermohonan> getTanahRizabPermohonanList() {
        return tanahRizabPermohonanList;
    }

    public void setTanahRizabPermohonanList(List<TanahRizabPermohonan> tanahRizabPermohonanList) {
        this.tanahRizabPermohonanList = tanahRizabPermohonanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public TanahRizabPermohonan getTanahRizabPermohonan() {
        return tanahRizabPermohonan;
    }

    public void setTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        this.tanahRizabPermohonan = tanahRizabPermohonan;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodDaerah getDaerah() {
        return daerah;
    }

    public void setDaerah(KodDaerah daerah) {
        this.daerah = daerah;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public KodKecerunanTanah getKecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
    }

    public LaporanTanahService getLaporanTanahService() {
        return laporanTanahService;
    }

    public void setLaporanTanahService(LaporanTanahService laporanTanahService) {
        this.laporanTanahService = laporanTanahService;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getNoLitho() {
        return noLitho;
    }

    public void setNoLitho(String noLitho) {
        this.noLitho = noLitho;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public String getNoWarta() {
        return noWarta;
    }

    public void setNoWarta(String noWarta) {
        this.noWarta = noWarta;
    }

    public PermohonanRujukanLuarDAO getPermohonanRujukanLuarDAO() {
        return permohonanRujukanLuarDAO;
    }

    public void setPermohonanRujukanLuarDAO(PermohonanRujukanLuarDAO permohonanRujukanLuarDAO) {
        this.permohonanRujukanLuarDAO = permohonanRujukanLuarDAO;
    }

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public TanahRizabService getTanahRizabService() {
        return tanahRizabService;
    }

    public void setTanahRizabService(TanahRizabService tanahRizabService) {
        this.tanahRizabService = tanahRizabService;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<HakmilikPihakBerkepentingan> getPihakKepentinganList() {
        return pihakKepentinganList;
    }

    public void setPihakKepentinganList(List<HakmilikPihakBerkepentingan> pihakKepentinganList) {
        this.pihakKepentinganList = pihakKepentinganList;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public PelupusanService getPlpservice() {
        return plpservice;
    }

    public void setPlpservice(PelupusanService plpservice) {
        this.plpservice = plpservice;
    }

    public List<KodSeksyen> getKodSeksyenList() {
        return kodSeksyenList;
    }

    public void setKodSeksyenList(List<KodSeksyen> kodSeksyenList) {
        this.kodSeksyenList = kodSeksyenList;
    }

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
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

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public FileBean getFileToBeUpload() {
        return fileToBeUpload;
    }

    public void setFileToBeUpload(FileBean fileToBeUpload) {
        this.fileToBeUpload = fileToBeUpload;
    }

    public String getPbt() {
        return pbt;
    }

    public void setPbt(String pbt) {
        this.pbt = pbt;
    }

    public PermohonanLaporanKawasan getPermohonanLaporanKawasan() {
        return permohonanLaporanKawasan;
    }

    public void setPermohonanLaporanKawasan(PermohonanLaporanKawasan permohonanLaporanKawasan) {
        this.permohonanLaporanKawasan = permohonanLaporanKawasan;
    }

    public PermohonanLaporanPelan getPermohonanLaporanPelan() {
        return permohonanLaporanPelan;
    }

    public void setPermohonanLaporanPelan(PermohonanLaporanPelan permohonanLaporanPelan) {
        this.permohonanLaporanPelan = permohonanLaporanPelan;
    }

    public String getRizab1() {
        return rizab1;
    }

    public void setRizab1(String rizab1) {
        this.rizab1 = rizab1;
    }

}