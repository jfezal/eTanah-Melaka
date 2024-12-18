/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.model.BangunanTingkat;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusan;
import etanah.model.LaporanTanah;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanLaporanKandungan;
import etanah.service.BPelService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
@UrlBinding("/strata/semak_penarikan_balik")
public class PenarikanBalikActionBean extends AbleActionBean {

    @Inject
    KodCawanganDAO kodCawanganDAO;
    private String idPermohonan = new String();
    private String tujuan;
    private String nama;
    private Hakmilik hakmilik;
    private Permohonan mohon;
    private Permohonan mohonSblm;
    private Pengguna pengguna = new Pengguna();
    private String listNoHakmilik;
    private String noLot;
    private int jumlahPetak;
    private String pemilik;
    private String pemohon;
    private String mukim;
    private String daerah;
    private String namaSkim;
//    private LaporanTanah laporantanah;
//    private List<PermohonanLaporanKandungan> listLaporanKandungan2 = new ArrayList<PermohonanLaporanKandungan>();
//    private List<PermohonanLaporanKandungan> listLaporanKandungan3 = new ArrayList<PermohonanLaporanKandungan>();
//    private List<PermohonanLaporanKandungan> listLaporanKandungan4 = new ArrayList<PermohonanLaporanKandungan>();
    private PermohonanKertas mohonKertas;
    private List<PermohonanKertasKandungan> listMohonKertas2 = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listMohonKertas3 = new ArrayList<PermohonanKertasKandungan>();
    private List<PermohonanKertasKandungan> listMohonKertas4 = new ArrayList<PermohonanKertasKandungan>();
    private String keputusan = new String();
    private String bayaran = new String();
    private FasaPermohonan mohonFasa = new FasaPermohonan();
    private FasaPermohonan mohonFasa1 = new FasaPermohonan();
    private FasaPermohonan mohonFasa2 = new FasaPermohonan();
    private String noFail = new String();
    @Inject
    StrataPtService strService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanLaporanKandunganDAO permohonanLaporanKandDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    private static final Logger LOG = Logger.getLogger(PenarikanBalikActionBean.class);
    private String tajuk = "";
    @Inject
    private etanah.Configuration conf;
    @Inject
    CommonService comm;
    private boolean stage1 = false;
    private boolean stage2 = false;
    private boolean stage3 = false;
    private String syorRadio;
    private String syorPen;
    private String jawatan;
    private String kodNegeri;
    private boolean checkMelaka = false;
    @Inject
    HakmilikPihakKepentinganService hpService;
    private Iterable<PermohonanKertasKandungan> listMohonKertasKand;

    @DefaultHandler
    public Resolution showForm() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution pnbFormReadOnly() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution pnbFormReadOnly2() {
        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik2.jsp").addParameter("tab", "true");
    }

    public Resolution semakLaporan() {
        stage1 = true;
        stage2 = true;
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true").addParameter("edit", "false");
    }

    public Resolution tambahRow() {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(permohonan.getInfoAudit());
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;

            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 2)));
                pkk.setBil((short) 2);
                listMohonKertas2.add(pkk);
                break;

            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 3)));
                pkk.setBil((short) 3);
                listMohonKertas3.add(pkk);
                break;

            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listMohonKertas4.add(pkk);
                break;

            default:
        }
        System.out.println(index);
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);

        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution tambahRow2() {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(permohonan.getInfoAudit());
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        PermohonanKertasKandungan pkk = new PermohonanKertasKandungan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
            case 1:
                break;

            case 2:
                pkk = new PermohonanKertasKandungan();
                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 2)));
                pkk.setBil((short) 2);
                listMohonKertas2.add(pkk);
                break;

            case 3:
                pkk = new PermohonanKertasKandungan();
                pkk.setSubtajuk(String.valueOf(lastNumberKertasKandungan(mohonKertas.getIdKertas(), 3)));
                pkk.setBil((short) 3);
                listMohonKertas3.add(pkk);
                break;

            case 4:
                pkk = new PermohonanKertasKandungan();
                pkk.setBil((short) 4);
                listMohonKertas4.add(pkk);
                break;

            default:
        }
        System.out.println(index);

        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);


        return new JSP("strata/tarik_balik/laporan_penarikan_balik2.jsp").addParameter("tab", "true");
    }

    private Integer lastNumberKertasKandungan(Long idKertas, int bil) {
        int last = 1;
        List<PermohonanKertasKandungan> listLastKandungan = strService.findByIdLapor(idKertas, bil);
        if (listLastKandungan.isEmpty()) {
            last = 1;
        } else {
            last = Integer.parseInt(listLastKandungan.get(listLastKandungan.size() - 1).getSubtajuk()) + 1;
        }
        return last;
    }

    public Resolution simpan2() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(permohonan.getInfoAudit());
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }
        for (PermohonanKertasKandungan pkk : listMohonKertas2) {
            pkk.setInfoAudit(mohonKertas.getInfoAudit());
            pkk.setCawangan(mohonKertas.getCawangan());
            pkk.setKertas(mohonKertas);
            pkk = strService.simpanPermohonanKertasKandungan(pkk);
        }
        filterStage(stageId);
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution simpan3() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(permohonan.getInfoAudit());
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        LOG.info("alamak!! index 3");
        if (StringUtils.isNotBlank(syorRadio)) {
            mohonFasa2.setKeputusan(kodKeputusanDAO.findById(syorRadio));
            mohonFasa2 = strService.saveFasaMohon(mohonFasa2);

        }
        for (PermohonanKertasKandungan pkk : listMohonKertas3) {
            pkk.setInfoAudit(mohonFasa2.getInfoAudit());
            pkk.setCawangan(mohonKertas.getCawangan());
            pkk.setKertas(mohonKertas);
            pkk = strService.simpanPermohonanKertasKandungan(pkk);
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution simpan4() throws ParseException {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas == null) {
            mohonKertas = new PermohonanKertas();
            mohonKertas.setTajuk("Kertas Pertimbangan PTG");
            mohonKertas.setCawangan(permohonan.getCawangan());
            mohonKertas.setInfoAudit(permohonan.getInfoAudit());
            mohonKertas.setPermohonan(permohonan);
            strService.simpanPermohonanKertas(mohonKertas);
        }
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        LOG.info("alamak!! index 3");
        if (StringUtils.isNotBlank(syorRadio)) {
            mohonFasa2.setKeputusan(kodKeputusanDAO.findById(syorRadio));
            mohonFasa2 = strService.saveFasaMohon(mohonFasa2);

        }
        for (PermohonanKertasKandungan pkk : listMohonKertas3) {
            pkk.setInfoAudit(mohonFasa2.getInfoAudit());
            pkk.setCawangan(mohonKertas.getCawangan());
            pkk.setKertas(mohonKertas);
            pkk = strService.simpanPermohonanKertasKandungan(pkk);
        }
        
        mohonFasa = strService.findFasaPermohonanByIdAliran(idPermohonan, "semaklaporan");
         if (mohonFasa != null) {
         
                String ulas="";
                for(PermohonanKertasKandungan ulasan : listMohonKertas3){
                    System.out.println("-----ulas------"+ulas);
                     System.out.println("-----ulasan.getKandungan()------"+ulasan.getKandungan());
                    ulas+=ulasan.getKandungan()+"&#13;&#10;";
                }
                System.out.println("-----ulas-----"+ulas);
                mohonFasa.setUlasan(ulas);
                System.out.println("-----tarikh semasa-----"+new java.util.Date());
                mohonFasa.setTarikhKeputusan(new java.util.Date());
                mohonFasa = strService.saveFasaMohon(mohonFasa);           
        }
         
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        getContext().getRequest().setAttribute("readOnly", Boolean.TRUE);
        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("strata/tarik_balik/laporan_penarikan_balik2.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow() throws ParseException {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik.jsp").addParameter("tab", "true");
    }

    public Resolution deleteRow2() throws ParseException {

        String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        String stageId = "";
        BPelService service = new BPelService();

        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
        }

        filterStage(stageId);
        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanKertasKandungan plk = new PermohonanKertasKandungan();
            plk = permohonanKertasKandDAO.findById(Long.parseLong(idKand));
            if (plk != null) {

                try {
                    strService.deleteKertasKandungan(plk);
                } catch (Exception e) {
                }
            }
        }
        rehydrate();
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/tarik_balik/laporan_penarikan_balik2.jsp").addParameter("tab", "true");
    }

    public void filterStage(String stageId) {
        if (stageId.equals("sedialaporan")) {
            stage1 = true;
        }
        if (stageId.equals("perakuan")) {
            stage1 = true;
            stage2 = true;
        }
        if (stageId.equals("semaklaporan")) {
            stage1 = true;
            stage2 = true;
        }
        if (stageId.equals("keputusan")) {
            stage1 = true;
            stage2 = true;
            stage3 = true;
        }
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() throws ParseException {
//        listLaporanKandungan2.clear();
//        listLaporanKandungan3.clear();
//        listLaporanKandungan4.clear();
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        mohon = permohonanDAO.findById(idPermohonan);
        noFail = mohon.getPermohonanSebelum().getIdPermohonan();
        jumlahPetak = 0;

        kodNegeri = conf.getProperty("kodNegeri");
        if (kodNegeri.equals("04")) {
            LOG.info("KERTAS MMK. KOD NEGERI = 04, MELAKA.");
//            checkMelaka=true;

        } else {
            LOG.info("KERTAS MMK. KOD NEGERI = 05, NEGERI SEMBILAN.");
//            checkMelaka=false;
        }

        for (PermohonanBangunan mohonBangunan : mohon.getPermohonanSebelum().getSenaraiBangunan()) {
            LOG.debug("Tingkat : " + mohonBangunan.getBilanganTingkat());
            for (BangunanTingkat bngnTingkat : mohonBangunan.getSenaraiTingkat()) {
                LOG.debug("jumlahPetak : " + jumlahPetak);
                jumlahPetak = jumlahPetak + bngnTingkat.getBilanganPetak();
            }
        }
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        listNoHakmilik = "";
        noLot = "";
        for (HakmilikPermohonan mohonHakmilik : mohon.getPermohonanSebelum().getSenaraiHakmilik()) {
            if (StringUtils.isEmpty(listNoHakmilik)) {
                listNoHakmilik = mohonHakmilik.getHakmilik().getNoHakmilik();
                noLot = mohonHakmilik.getHakmilik().getNoLot();
            } else {
                listNoHakmilik = listNoHakmilik + ", " + mohonHakmilik.getHakmilik().getNoHakmilik();
                noLot = noLot + ", " + mohonHakmilik.getHakmilik().getNoLot();
            }
            for (HakmilikPihakBerkepentingan phk : hpService.findHakmilikPihakActiveByHakmilik(mohonHakmilik.getHakmilik())) {
                LOG.debug("phk : " + phk.getPihak().getNama());
                if (phk.getJenis().getKod().equals("PM")) {
                    pemilik = phk.getPihak().getNama();
                    LOG.debug("pemilik : " + pemilik);
                }

            }
            mukim = mohonHakmilik.getHakmilik().getBandarPekanMukim().getNama();
            daerah = mohonHakmilik.getHakmilik().getDaerah().getNama();
            namaSkim = strService.findID(mohon.getPermohonanSebelum().getIdPermohonan()).getNamaSkim();
        }
        pemohon = mohon.getSenaraiPemohon().get(0).getPihak().getNama();
        mohonKertas = strService.findKertas(idPermohonan);
        if (mohonKertas != null) {
            listMohonKertas2 = strService.findByIdLapor(mohonKertas.getIdKertas(), 2);
            listMohonKertas3 = strService.findByIdLapor(mohonKertas.getIdKertas(), 3);
            listMohonKertas4 = strService.findByIdLapor(mohonKertas.getIdKertas(), 4);
        }

        mohonFasa2 = strService.findMohonFasa(mohon.getIdPermohonan(), "semaklaporan");
        if (mohonFasa2 != null) {
            if (mohonFasa2.getKeputusan() != null) {
                syorRadio = mohonFasa2.getKeputusan().getKod();
                syorPen = mohonFasa2.getKeputusan().getNama();
            } else {
                syorPen = "TIADA KEPUTUSAN";
            }
        }

        mohonFasa = strService.findMohonFasa(mohon.getPermohonanSebelum().getIdPermohonan(), "keputusan");
        if (mohonFasa != null) {
            keputusan = strService.formatSDF(mohonFasa.getTarikhHantar());
        }
         else {
            keputusan = null;
        }
        mohonFasa1 = strService.findMohonFasa(mohon.getPermohonanSebelum().getIdPermohonan(), "terimabayaran");
        if (mohonFasa1 != null) {
            bayaran = strService.formatSDF(mohonFasa1.getTarikhHantar());

        } else {
            bayaran = null;
        }
        LOG.info(pemohon);
        LOG.info(noLot);
        LOG.info(mukim);
        LOG.info(daerah);
        String negeri = "";
        if (conf.getProperty("kodNegeri").equals("04")) {
            negeri = "Melaka";
        } else {
            negeri = "Negeri Sembilan";
        }
        tajuk = "PERMOHONAN PENARIKAN PERMOHONAN PECAH BAHAGI BANGUNAN DARIPADA " + pemohon.toUpperCase()
                + " BAGI " + noLot.toUpperCase() + " KAWASAN " + mukim.toUpperCase() + " DAERAH "
                + daerah.toUpperCase() + ", " + negeri.toUpperCase();


    }

    public boolean isStage1() {
        return stage1;
    }

    public void setStage1(boolean stage1) {
        this.stage1 = stage1;
    }

    public boolean isStage2() {
        return stage2;
    }

    public void setStage2(boolean stage2) {
        this.stage2 = stage2;
    }

    public boolean isStage3() {
        return stage3;
    }

    public void setStage3(boolean stage3) {
        this.stage3 = stage3;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getListNoHakmilik() {
        return listNoHakmilik;
    }

    public void setListNoHakmilik(String listNoHakmilik) {
        this.listNoHakmilik = listNoHakmilik;
    }

    public Permohonan getMohon() {
        return mohon;
    }

    public void setMohon(Permohonan mohon) {
        this.mohon = mohon;
    }

    public Permohonan getMohonSblm() {
        return mohonSblm;
    }

    public void setMohonSblm(Permohonan mohonSblm) {
        this.mohonSblm = mohonSblm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoLot() {
        return noLot;
    }

    public void setNoLot(String noLot) {
        this.noLot = noLot;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public StrataPtService getStrService() {
        return strService;
    }

    public void setStrService(StrataPtService strService) {
        this.strService = strService;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public int getJumlahPetak() {
        return jumlahPetak;
    }

    public void setJumlahPetak(int jumlahPetak) {
        this.jumlahPetak = jumlahPetak;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getPemohon() {
        return pemohon;
    }

    public void setPemohon(String pemohon) {
        this.pemohon = pemohon;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getMukim() {
        return mukim;
    }

    public void setMukim(String mukim) {
        this.mukim = mukim;
    }

    public String getBayaran() {
        return bayaran;
    }

    public void setBayaran(String bayaran) {
        this.bayaran = bayaran;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public FasaPermohonan getMohonFasa() {
        return mohonFasa;
    }

    public void setMohonFasa(FasaPermohonan mohonFasa) {
        this.mohonFasa = mohonFasa;
    }

    public FasaPermohonan getMohonFasa1() {
        return mohonFasa1;
    }

    public void setMohonFasa1(FasaPermohonan mohonFasa1) {
        this.mohonFasa1 = mohonFasa1;
    }

    public String getNoFail() {
        return noFail;
    }

    public void setNoFail(String noFail) {
        this.noFail = noFail;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getNamaSkim() {
        return namaSkim;
    }

    public void setNamaSkim(String namaSkim) {
        this.namaSkim = namaSkim;
    }

    public List<PermohonanKertasKandungan> getListMohonKertas2() {
        return listMohonKertas2;
    }

    public void setListMohonKertas2(List<PermohonanKertasKandungan> listMohonKertas2) {
        this.listMohonKertas2 = listMohonKertas2;
    }

    public List<PermohonanKertasKandungan> getListMohonKertas3() {
        return listMohonKertas3;
    }

    public void setListMohonKertas3(List<PermohonanKertasKandungan> listMohonKertas3) {
        this.listMohonKertas3 = listMohonKertas3;
    }

    public List<PermohonanKertasKandungan> getListMohonKertas4() {
        return listMohonKertas4;
    }

    public void setListMohonKertas4(List<PermohonanKertasKandungan> listMohonKertas4) {
        this.listMohonKertas4 = listMohonKertas4;
    }

    public PermohonanKertas getMohonKertas() {
        return mohonKertas;
    }

    public void setMohonKertas(PermohonanKertas mohonKertas) {
        this.mohonKertas = mohonKertas;
    }

    public String getJawatan() {
        return jawatan;
    }

    public void setJawatan(String jawatan) {
        this.jawatan = jawatan;
    }

    public String getSyorPen() {
        return syorPen;
    }

    public void setSyorPen(String syorPen) {
        this.syorPen = syorPen;
    }

    public String getSyorRadio() {
        return syorRadio;
    }

    public void setSyorRadio(String syorRadio) {
        this.syorRadio = syorRadio;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public boolean isCheckMelaka() {
        return checkMelaka;
    }

    public void setCheckMelaka(boolean checkMelaka) {
        this.checkMelaka = checkMelaka;
    }
}
