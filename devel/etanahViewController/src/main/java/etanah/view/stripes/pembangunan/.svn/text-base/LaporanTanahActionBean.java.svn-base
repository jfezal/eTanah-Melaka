/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.LaporanTanah;
import etanah.model.Hakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.PengambilanService;
import etanah.dao.KodRizabDAO;
import etanah.model.Dokumen;
import etanah.service.BPelService;
import etanah.service.LaporanTanahService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import etanah.model.KodBandarPekanMukim;
import etanah.service.common.TanahRizabService;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodRizab;
import etanah.model.KodKecerunanTanah;
import etanah.model.TanahRizabPermohonan;
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
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.ImejLaporan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/laporanTanah")
public class LaporanTanahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    TanahRizabPermohonanDAO tanahrizabPermohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    LaporanTanahService laporanTanahService;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    private Hakmilik hakmilik;
    List<HakmilikPermohonan> senaraiHakmilik;
    private List<HakmilikPihakBerkepentingan> pihakKepentinganList;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private TanahRizabPermohonan tanahrizabpermohonan;
    private TanahRizabPermohonan trizabpermohonan;
    private Permohonan permohonan;
    private LaporanTanah laporanTanah;
    private FasaPermohonan fasaPermohonan;
    private FasaPermohonan fasaPermohonan2;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private static final Logger LOG = Logger.getLogger(LaporanTanahActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    private String date;
    private String stageId;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private PermohonanPengambilan permohonanPengambilan;
    private List<TanahRizabPermohonan> tanahRizabList;
    @Inject
    TanahRizabService tanahRizabService;
    @Inject
    KodRizabDAO kodRizabDAO;
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
    private String noRujukan;
    private String idFasa;
    private char pandanganImej;
    private List<Dokumen> dokumenList = new ArrayList<Dokumen>();
    private List<String> imej = new ArrayList<String>();
    private ArrayList imageList[] = new ArrayList[5];
    private List<ImejLaporan> imejLaporanList;
    private List<ImejLaporan> utaraImejLaporanList;
    private List<ImejLaporan> selatanImejLaporanList;
    private List<ImejLaporan> timurImejLaporanList;
    private List<ImejLaporan> baratImejLaporanList;

    public List<ImejLaporan> getImejLaporanList() {
        return imejLaporanList;
    }

    public void setImejLaporanList(List<ImejLaporan> imejLaporanList) {
        this.imejLaporanList = imejLaporanList;
    }

    public List<ImejLaporan> getUtaraImejLaporanList() {
        return utaraImejLaporanList;
    }

    public void setUtaraImejLaporanList(List<ImejLaporan> utaraImejLaporanList) {
        this.utaraImejLaporanList = utaraImejLaporanList;
    }

    public List<ImejLaporan> getBaratImejLaporanList() {
        return baratImejLaporanList;
    }

    public void setBaratImejLaporanList(List<ImejLaporan> baratImejLaporanList) {
        this.baratImejLaporanList = baratImejLaporanList;
    }

    public List<ImejLaporan> getSelatanImejLaporanList() {
        return selatanImejLaporanList;
    }

    public void setSelatanImejLaporanList(List<ImejLaporan> selatanImejLaporanList) {
        this.selatanImejLaporanList = selatanImejLaporanList;
    }

    public List<ImejLaporan> getTimurImejLaporanList() {
        return timurImejLaporanList;
    }

    public void setTimurImejLaporanList(List<ImejLaporan> timurImejLaporanList) {
        this.timurImejLaporanList = timurImejLaporanList;
    }

    public ArrayList[] getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList[] imageList) {
        this.imageList = imageList;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public char getPandanganImej() {
        return pandanganImej;
    }

    public void setPandanganImej(char pandanganImej) {
        this.pandanganImej = pandanganImej;
    }
    @Inject
    PengambilanService service;

    @DefaultHandler
    public Resolution showForm() {
        LOG.info("Start Laporan Tanah");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        LOG.info("Start Laporan Tanah");
        return new JSP("pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");

    }

    public Resolution showeditTanahRizab() {
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahRizabPermohonan));
        return new JSP("pengambilan/kemaskini_trizab.jsp").addParameter("popup", "true");
    }

    public Resolution hakMilikPopup() {
        return new JSP("pengambilan/kemasukan_tanahrizab.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");
    }

    public Resolution searchtrizab() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        tanahrizabpermohonan=tanahRizabService.findByidPermohonan(idPermohonan);
        InfoAudit info = peng.getInfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        if (idPermohonan != null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        } else {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            if (StringUtils.isNotBlank(tarikhWarta)) {
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
                tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta());
//                    tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
            }
            tanahrizabpermohonan.setInfoAudit(info);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabList.add(tanahrizabpermohonan);
            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }


        return new ForwardResolution("/WEB-INF/jsp/pengambilan/maklumat_trizab.jsp").addParameter("tab", "true");



    }

    public Resolution edit() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pg = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
//            trizabpermohonan = tanahrizabPermohonanDAO.findById(Long.valueOf(idtanahrizabPermohonan));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (ia == null) {
            tanahrizabpermohonan = new TanahRizabPermohonan();
            ia = new InfoAudit();
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
        } else {

            ia.setDiKemaskiniOleh(pg);
            ia.setTarikhKemaskini(new java.util.Date());
//               ia.setDimasukOleh(pg);
//               ia.setTarikhMasuk(new java.util.Date());
            ia.setDimasukOleh(pg);
            ia.setTarikhMasuk(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);

//            trizabpermohonan.setDaerah(tanahrizabpermohonan.getDaerah());
//            trizabpermohonan.setCawangan(cawangan);
//            trizabpermohonan.setNoLot(noLot);
//            trizabpermohonan.setNoLitho(noLitho);
//            trizabpermohonan.setNoWarta(noWarta);
//            trizabpermohonan.setLokasi(lokasi);
//            trizabpermohonan.setRizab(rizab);;
//            trizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));

//            trizabpermohonan.setPermohonan(permohonan);
//            trizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
        }
        tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);

        addSimpleMessage("Data Telah Berjaya dikemaskini");
        return new JSP("pengambilan/maklumat_pengambilan_pemohon.jsp").addParameter("tab", "true");

//            InfoAudit ia = new InfoAudit();
//            Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//            idtanahrizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
//            tanahrizabpermohonan=tanahrizabPermohonanDAO.findById(Long.parseLong(idtanahrizabPermohonan));
//            if(ia==null)
//            {
//                tanahrizabpermohonan= new TanahRizabPermohonan();
//                ia=new InfoAudit();
//                ia.setDimasukOleh(peng);
//                ia.setTarikhMasuk(new java.util.Date());
//                tanahrizabpermohonan.setInfoAudit(ia);
//            }
////            else(tanahrizabpermohonan != null)
//            {
//            ia = peng.getInfoAudit();
//            ia.setDiKemaskiniOleh(peng);
//            ia.setTarikhKemaskini(new java.util.Date());
//            tanahrizabpermohonan.setInfoAudit(ia);
//            tanahrizabpermohonan.setPermohonan(trizabpermohonan.getPermohonan());
//            tanahrizabpermohonan.setBandarPekanMukim(trizabpermohonan.getBandarPekanMukim());
//            tanahrizabpermohonan.setDaerah(trizabpermohonan.getDaerah());
//            tanahrizabpermohonan.setCawangan(trizabpermohonan.getCawangan());
//            tanahrizabpermohonan.setNoLot(trizabpermohonan.getNoLot());
//            tanahrizabpermohonan.setNoLitho(trizabpermohonan.getNoLitho());
//            tanahrizabpermohonan.setNoWarta(trizabpermohonan.getNoWarta());
//            tanahrizabpermohonan.setLokasi(trizabpermohonan.getLokasi());
//            tanahrizabpermohonan.setRizab(trizabpermohonan.getRizab());
////            tanahrizabpermohonan.setTarikhWarta(sdf.parse(tarikhWarta));
////            tarikhWarta = sdf.format(tanahrizabpermohonan.getTarikhWarta()).substring(0, 10);
//
//
//            //suratkuasaService.deleteAll(wakilHakmilik);
//}
//            tanahRizabService.saveOrUpdatetanahRizab(tanahrizabpermohonan);
//            addSimpleMessage("Data Telah Berjaya dikemaskini");
//            return new JSP("pengambilan/Laporan_Tanah.jsp").addParameter("tab", "true");
    }

    public Resolution deleteSingle() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        tanahrizabpermohonan = new TanahRizabPermohonan();
        String idtanahRizabPermohonan = getContext().getRequest().getParameter("idTanahRizabPermohonan");
        tanahrizabpermohonan = tanahRizabService.findTanahRizabByIdTanahRizab(Long.parseLong(idtanahRizabPermohonan));


        if (tanahrizabpermohonan != null) {
            ia = peng.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            tanahrizabpermohonan.setInfoAudit(ia);
            tanahrizabpermohonan.setPermohonan(permohonan);
            tanahrizabpermohonan.setBandarPekanMukim(bandarPekanMukim);
            tanahrizabpermohonan.setDaerah(daerah);
            tanahrizabpermohonan.setCawangan(cawangan);
            tanahrizabpermohonan.setNoLot(noLot);
            tanahrizabpermohonan.setNoLitho(noLitho);
            tanahrizabpermohonan.setNoWarta(noWarta);
            tanahrizabpermohonan.setLokasi(lokasi);
            tanahrizabpermohonan.setRizab(rizab);
            tanahRizabService.deleteAll(tanahrizabpermohonan);
            //suratkuasaService.deleteAll(wakilHakmilik);
        }
        return new RedirectResolution(LaporanTanahActionBean.class, "locate");

    }

//    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!simpanLaporanTanah"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        System.out.println("-------------rehydrate---------");

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        stageId = "laporantanah";
//        LOG.info("#####" + "idPermohonan" + " : " + idPermohonan + " : " + stageId);
        pihakKepentinganList = new ArrayList<HakmilikPihakBerkepentingan>();
        imejLaporanList = new ArrayList<ImejLaporan>();
        utaraImejLaporanList = new ArrayList<ImejLaporan>();
        selatanImejLaporanList = new ArrayList<ImejLaporan>();
        timurImejLaporanList = new ArrayList<ImejLaporan>();
        baratImejLaporanList = new ArrayList<ImejLaporan>();

        if (idPermohonan != null) {


            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            tanahRizabList = permohonan.getSenaraiTanahRizab();
            permohonanPengambilan = service.findByidPermohonan(idPermohonan);

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
            LOG.info("###### listFasa : " + listFasa.size());
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);
//                    if (fp.getIdPengguna().equals("kppt1")) {
//                        fasaPermohonan = listFasa.get(i);
//                    }
                    if (fp.getIdAliran().equals("laporantanah")) {
                        fasaPermohonan = listFasa.get(i);
                    }
                }
            }
            if (!(listFasa.isEmpty())) {
                for (int i = 0; i < listFasa.size(); i++) {
                    FasaPermohonan fp = new FasaPermohonan();
                    fp = listFasa.get(i);

                    if (fp.getIdAliran().equals("semaklaporantanah")) {
                        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
                        fasaPermohonan2 = listFasa.get(i);
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

            if (!(listRujukanLuar.isEmpty())) {
                for (int i = 0; i < listRujukanLuar.size(); i++) {
                    PermohonanRujukanLuar rujL = new PermohonanRujukanLuar();
                    rujL = listRujukanLuar.get(i);
//                    if (rujL.getKodDokumenRujukan().getKod().equals("WRKN")) {
//                        permohonanRujukanLuar = listRujukanLuar.get(i);
//                    }
                    if (rujL.getKodRujukan() != null) {
                        if (rujL.getKodRujukan().getKod().equalsIgnoreCase("WT")) {
                            permohonanRujukanLuar = listRujukanLuar.get(i);
                        }
                    }

                }
            }
        }

        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String format1 = "image/jpeg";
        String format2 = "image/pjpeg";
        dokumenList = laporanTanahService.getDokumenByIdPenguna(format1, format2, pguna.getNama());

        // find StageId

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------" + taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }


    }

    public Resolution simpanLaporanTanah() throws Exception {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.info("###### id Pengguna : " + pengguna);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        if (laporanTanah != null) {

            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            if (laporanTanah.getAdaBangunan() != null) {
                if (laporanTanah.getAdaBangunan() == 'T') {
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

            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            System.out.println("-------------simpan if---------");

        } else {
            laporanTanah = new LaporanTanah();
            laporanTanah.setPermohonan(permohonan);
            laporanTanah.setInfoAudit(infoAudit);
            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
            System.out.println("-------------simpan else---");

        }

        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());

        BPelService service = new BPelService();
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        System.out.println("---------taskId-------" + taskId);

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            System.out.println("-------------stageId--" + stageId);
        }

        //testing purpose
//        stageId = "laporantanah";
        String lT = "semaklaporantanah";

        if (fasaPermohonan != null) {
            if (stageId.equalsIgnoreCase("laporantanah")) {
                LOG.info("###### laporantanah SAVE");
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
            } else if (stageId.equalsIgnoreCase("semaklaporantanah")) {
                LOG.info("###### semaklaporantanah SAVE");
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
                //Ulasan Penolong Pegawai Tanah Kanan
                fasaPermohonan2.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
            }

            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");//NF
                permohonanRujukanLuar.setKodRujukan(kodRuj);

//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("WT");
                permohonanRujukanLuar.setKodRujukan(kodRuj);
//                KodDokumen kodDokumenWarta = new KodDokumen();
//                kodDokumenWarta.setKod("WRKN");
//                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);

            }
        } else {
            fasaPermohonan = new FasaPermohonan();
            fasaPermohonan2 = new FasaPermohonan();

            if (stageId.equalsIgnoreCase("laporantanah")) {
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setCawangan(permohonan.getCawangan());
                fasaPermohonan.setInfoAudit(infoAudit);
                fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
            } else if (stageId.equalsIgnoreCase("semaklapotantanah")) {
                fasaPermohonan2.setPermohonan(permohonan);
                fasaPermohonan2.setCawangan(permohonan.getCawangan());
                fasaPermohonan2.setInfoAudit(infoAudit);
                fasaPermohonan2.setIdPengguna(pengguna.getIdPengguna());
                fasaPermohonan2.setIdAliran(lT);
//                laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
                //Ulasan Penolong Pegawai Tanah Kanan
                fasaPermohonan2.setIdAliran(stageId);
                laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
            }

            if (permohonanRujukanLuar != null) {
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);

                KodDokumen kodDokumenWarta = new KodDokumen();
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);
            } else {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setInfoAudit(infoAudit);
                permohonanRujukanLuar.setTarikhRujukan(new java.util.Date());
                KodRujukan kodRuj = new KodRujukan();
                kodRuj.setKod("NF");
                permohonanRujukanLuar.setKodRujukan(kodRuj);

                KodDokumen kodDokumenWarta = new KodDokumen();
                kodDokumenWarta.setKod("WRKN");
                permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenWarta);
                laporanTanahService.simpanRujukanLuar(permohonanRujukanLuar);

            }

        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");
    }

//    public Resolution simpanSemakLaporanTanah() throws Exception {
//
//        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        LOG.info("###### Simpan semak laporan tanah id Pengguna : " + pengguna);
//        if (idPermohonan != null) {
//            permohonan = permohonanDAO.findById(idPermohonan);
//            cawangan = permohonan.getCawangan();
//        }
//        InfoAudit infoAudit = new InfoAudit();
//        infoAudit.setDimasukOleh(pengguna);
//        infoAudit.setTarikhMasuk(new java.util.Date());
//
//        if (laporanTanah != null) {
//
//            laporanTanah.setPermohonan(permohonan);
//            laporanTanah.setInfoAudit(infoAudit);
//            if (laporanTanah.getAdaBangunan() != null) {
//                if (laporanTanah.getAdaBangunan() == 'T') {
//                    laporanTanah.setBilanganBangunan(0);
//                }
//
//            }
//
//
//
////            if (date != null) {
////
////                try {
////                    laporanTanah.setTarikhMulaUsaha(sdf.parse(date));
////                } catch (ParseException ex) {
////                    LOG.error("exception: " + ex.getMessage());
////                    throw ex;
////                }
////            }
//
//            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
//            System.out.println("-------------simpan if---------");
//
//        } else {
//            laporanTanah = new LaporanTanah();
//            laporanTanah.setPermohonan(permohonan);
//            laporanTanah.setInfoAudit(infoAudit);
//            laporanTanah = laporanTanahService.simpanLaporanTanah(laporanTanah);
//            System.out.println("-------------simpan else---");
//
//        }
//
//        permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
//
//        BPelService service = new BPelService();
//        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
//        System.out.println("---------taskId-------" + taskId);
//
//        if (StringUtils.isNotBlank(taskId)) {
//            Task t = null;
//            t = service.getTaskFromBPel(taskId, pengguna);
//            stageId = t.getSystemAttributes().getStage();
//            System.out.println("-------------stageId--" + stageId);
//        }
//
//        //testing purpose
//        stageId = "semaklaporantanah";
//        String lT = "laporantanah";
//
//        if (fasaPermohonan != null) {
//            fasaPermohonan.setPermohonan(permohonan);
//            fasaPermohonan.setCawangan(permohonan.getCawangan());
//            fasaPermohonan.setInfoAudit(infoAudit);
//            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//            fasaPermohonan.setIdAliran(lT);
////          fasaPermohonan.setUlasan(ulasan);
//            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
//            //Ulasan Penolong Pegawai Tanah Kanan
//            fasaPermohonan2.setIdAliran(stageId);
//            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
//
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
//            } else {
//                permohonanRujukanLuar = new PermohonanRujukanLuar();
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
//        } else {
//            fasaPermohonan = new FasaPermohonan();
//            fasaPermohonan.setPermohonan(permohonan);
//            fasaPermohonan.setCawangan(permohonan.getCawangan());
//            fasaPermohonan.setInfoAudit(infoAudit);
//            fasaPermohonan.setIdPengguna(pengguna.getIdPengguna());
//            fasaPermohonan.setIdAliran(lT);
//            fasaPermohonan2.setIdAliran(stageId);
//            laporanTanahService.simpanFasaPermohonan(fasaPermohonan);
//            //Ulasan Penolong Pegawai Tanah Kanan
//            laporanTanahService.simpanFasaPermohonan(fasaPermohonan2);
//
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
//            } else {
//                permohonanRujukanLuar = new PermohonanRujukanLuar();
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
//
//        }
//        addSimpleMessage("Maklumat telah berjaya disimpan.");
//
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//
//        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");
//    }
    public Resolution addHakmilikImage() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        String idDokumen = getContext().getRequest().getParameter("idDokumen");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        Dokumen dokumen = dokumenDAO.findById(Long.parseLong(idDokumen));

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByHakmilikDokumen(idHakmilik, dokumen.getIdDokumen());
        if (imejLaporan == null) {
            imejLaporan = new ImejLaporan();
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
        } else {
            addSimpleMessage("Imej telah wujud untuk hakmilik ini.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");
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

        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        System.out.println("idhakmilik test:" + idHakmilik);
        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }

        ImejLaporan imejLaporan = laporanTanahService.getLaporImejByDokumen(laporanTanah, dokumen, pandanganImej);
        if (imejLaporan == null) {
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());

            imejLaporan = new ImejLaporan();
            imejLaporan.setCawangan(cawangan);
            imejLaporan.setDokumen(dokumen);
            imejLaporan.setPandanganImej(pandanganImej);
            imejLaporan.setInfoAudit(infoAudit);
            imejLaporan.setLaporanTanah(laporanTanah);
            imejLaporan.setHakmilik(hakmilik);
            laporanTanahService.simpanHakmilikImej(imejLaporan);

            rehydrate();
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } else {
            addSimpleMessage("Imej already exist.");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/pembangunan/pecahSempadan/laporan_tanah.jsp").addParameter("tab", "true");
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

    public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
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

    public List<TanahRizabPermohonan> getTanahRizabList() {
        return tanahRizabList;
    }

    public void setTanahRizabList(List<TanahRizabPermohonan> tanahRizabList) {
        this.tanahRizabList = tanahRizabList;
    }

    public KodKecerunanTanah getKodkecerunanTanah() {
        return kecerunanTanah;
    }

    public void setKodkecerunanTanah(KodKecerunanTanah kecerunanTanah) {
        this.kecerunanTanah = kecerunanTanah;
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

    public KodRizabDAO getKodRizabDAO() {
        return kodRizabDAO;
    }

    public void setKodRizabDAO(KodRizabDAO kodRizabDAO) {
        this.kodRizabDAO = kodRizabDAO;
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

    public KodRizab getRizab() {
        return rizab;
    }

    public void setRizab(KodRizab rizab) {
        this.rizab = rizab;
    }

    public String getTarikhWarta() {
        return tarikhWarta;
    }

    public void setTarikhWarta(String tarikhWarta) {
        this.tarikhWarta = tarikhWarta;
    }

    public TanahRizabPermohonan getTanahrizabpermohonan() {
        return tanahrizabpermohonan;
    }

    public void setTanahrizabpermohonan(TanahRizabPermohonan tanahrizabpermohonan) {
        this.tanahrizabpermohonan = tanahrizabpermohonan;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdtanahrizabPermohonan() {
        return idtanahrizabPermohonan;
    }

    public void setIdtanahrizabPermohonan(String idtanahrizabPermohonan) {
        this.idtanahrizabPermohonan = idtanahrizabPermohonan;
    }

    public TanahRizabPermohonan getTrizabpermohonan() {
        return trizabpermohonan;
    }

    public void setTrizabpermohonan(TanahRizabPermohonan trizabpermohonan) {
        this.trizabpermohonan = trizabpermohonan;
    }

    public List<Dokumen> getDokumenList() {
        return dokumenList;
    }

    public void setDokumenList(List<Dokumen> dokumenList) {
        this.dokumenList = dokumenList;
    }

    public List<String> getImej() {
        return imej;
    }

    public void setImej(List<String> imej) {
        this.imej = imej;
    }

    public FasaPermohonan getFasaPermohonan2() {
        return fasaPermohonan2;
    }

    public void setFasaPermohonan2(FasaPermohonan fasaPermohonan2) {
        this.fasaPermohonan2 = fasaPermohonan2;
    }

    public String getIdFasa() {
        return idFasa;
    }

    public void setIdFasa(String idFasa) {
        this.idFasa = idFasa;
    }

    public String getNoRujukan() {
        return noRujukan;
    }

    public void setNoRujukan(String norujukan) {
        this.noRujukan = noRujukan;
    }
}
