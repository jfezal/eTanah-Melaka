/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.KodDokumen;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPengguna;
import etanah.service.BPelService;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rohans
 */
@UrlBinding("/pelupusan/rekod_keputusan_JKTD")
public class RekodKeputusanJKTDActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(RekodKeputusanJKTDActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PelupusanService pservice;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    BPelService service;
    private String idPermohonan;
    private Pemohon pemohon;
    private HakmilikPermohonan mohonHakmilik = new HakmilikPermohonan();
    private PermohonanKertasKandungan kertasKandungan;
    private PermohonanKertas permohonanKertas;
    private KodDokumen kd;
    private String ulasan;
    private String keputusan;
    private FasaPermohonan fasaPermohonan;
    private String stageId;
    private Date tarikhMesyuarat;
    private String nomborRujukan;
    private String kpsn;
    private String kpsnnama;
    private boolean showK = Boolean.TRUE;
    private boolean viewK = Boolean.TRUE;
    private boolean showA = Boolean.TRUE;

    @DefaultHandler
    public Resolution showForm() {
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            fasaPermohonan.getKeputusan().getNama();
        }
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");
    }

    public Resolution showKeputusan() {
        showK = Boolean.TRUE;
        viewK = Boolean.FALSE;
        showA = Boolean.FALSE;
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            fasaPermohonan.getKeputusan().getNama();
        }
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan() {
        showK = Boolean.FALSE;
        viewK = Boolean.TRUE;
        showA = Boolean.FALSE;
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");
    }

    public Resolution showKertas() {
        showK = Boolean.FALSE;
        viewK = Boolean.FALSE;
        showA = Boolean.TRUE;
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        //REMOVE

//       stageId ="terima_keputusan_jktd";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.info(permohonan.getKodUrusan().getKod());
        if (permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC")) {
            stageId = "terima_keputusan_jktd";
        } else if (permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            stageId = stageId(taskId, pguna);
            //stageId = "16Kptsn";
        }else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            stageId = "09Kptsn";
        }else {
            stageId = "terima_keputusan_jktd";
        }
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        LOG.info("------rehy------permohonankertas -----------::" + permohonanKertas);

        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if(permohonanKertas!=null)
        {
            if (permohonanKertas.getNomborRujukan() != null) {
                nomborRujukan = permohonanKertas.getNomborRujukan();
            }
        }
        LOG.info("------------fasaPermohonan -------------::" + fasaPermohonan);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            keputusan = fasaPermohonan.getKeputusan().getKod();
            ulasan = fasaPermohonan.getUlasan();
        }

        if (permohonan.getKeputusan() != null) {
            kpsn = permohonan.getKeputusan().getKod();
            kpsnnama = permohonan.getKeputusan().getNama();
        }
    }

    public Resolution simpan() {
        
        int count = 0;
        if(nomborRujukan == null && permohonan.getKodUrusan().getKod().equals("PLPS"))
        {
            addSimpleError("Sila Masukkan No. Kertas Mesyuarat.");
            count = count + 1;
        }
        if(keputusan == null && (permohonan.getKodUrusan().getKod().equals("PLPS") || permohonan.getKodUrusan().getKod().equals("PLPTD")))
        {
            addSimpleError("Sila Masukkan Keputusan Mesyuarat.");
            count = count + 1;
        }
        System.out.println(count);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("PTBTS") || permohonan.getKodUrusan().getKod().equals("BMBT") || permohonan.getKodUrusan().getKod().equals("PTBTC")) {
            stageId = "terima_keputusan_jktd";
        } else if (permohonan.getKodUrusan().getKod().equals("PLPTD") || permohonan.getKodUrusan().getKod().equals("PLPS")) {
            //stageId = "16Kptsn";
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
            Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            stageId = stageId(taskId, pguna);
        }else if (permohonan.getKodUrusan().getKod().equals("RLPS")) {
            stageId = "09Kptsn";
        } else {
            stageId = "terima_keputusan_jktd";
        }
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        kd = kodDokumenDAO.findById("JKTD");
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");
        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);
        if (fasaPermohonan != null && fasaPermohonan.getKeputusan() != null) {
            fasaPermohonan.getKeputusan().getNama();
        }
        if(count==0)
        {
            InfoAudit info = new InfoAudit();
            if (permohonanKertas != null) {
                LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
                info = permohonanKertas.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());

            } else {
                permohonanKertas = new PermohonanKertas();
                LOG.info("------if------permohonankertas Null--------------::" + permohonanKertas);
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                permohonanKertas.setInfoAudit(info);
                permohonanKertas.setCawangan(peng.getKodCawangan());
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setTarikhSidang(tarikhMesyuarat);
            }

            permohonanKertas.setNomborRujukan(nomborRujukan);
            kd = kodDokumenDAO.findById("JKTD");
            permohonanKertas.setKodDokumen(kd);
            permohonanKertas.setTajuk("Rekod JKTD");
            pservice.simpanPermohonanKertas(permohonanKertas);

    //         String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
    //       if (StringUtils.isBlank(taskId)) {
    //           taskId = getContext().getRequest().getParameter("taskId");
    //       }
    //      Task task = service.getTaskFromBPel(taskId, peng);
    //       if (task != null) {
    //           stageId = task.getSystemAttributes().getStage();
    //       } else {
    //           stageId = getContext().getRequest().getParameter("stageId");
    //       }

            fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, stageId);

            if (fasaPermohonan == null) {
                LOG.info("------------if----FasaPermohonan--------------");
                fasaPermohonan = new FasaPermohonan();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                fasaPermohonan.setInfoAudit(info);
                fasaPermohonan.setCawangan(peng.getKodCawangan());
                fasaPermohonan.setPermohonan(permohonan);
                fasaPermohonan.setIdAliran("terima_keputusan_jktd");
            } else {
                LOG.info("------------else------FasaPermohonan------------");
                info = fasaPermohonan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
            }

            fasaPermohonan.setIdPengguna(peng.getIdPengguna());

            fasaPermohonan.setKeputusan(kodKeputusanDAO.findById(keputusan));
            fasaPermohonan.setUlasan(ulasan);
            pelupusanService.simpanFasaPermohonan(fasaPermohonan);
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");

    }

    public Resolution simpanKeputusan() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");

        InfoAudit info = new InfoAudit();
        if (permohonan != null) {
            info = permohonan.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(info);
        }
        if(kpsn == null && permohonan.getKodUrusan().getKod().equals("RLPS"))
        {
            addSimpleError("Sila Masukkan Keputusan Mesyuarat.");
        }
        else
        {
            permohonan.setKeputusanOleh(peng);
            permohonan.setKeputusan(kodKeputusanDAO.findById(kpsn));
            permohonan.setTarikhKeputusan(new java.util.Date());
            permohonan.setUlasan(ulasan);
            pelupusanService.simpanPermohonan(permohonan);
            addSimpleMessage("Maklumat Berjaya Disimpan");
        }
        
        showK = Boolean.TRUE;
        viewK = Boolean.FALSE;
        showA = Boolean.FALSE;
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKertas() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        kd = kodDokumenDAO.findById("JKTD");
        permohonanKertas = pservice.findKertasByKod(idPermohonan, "JKTD");


        InfoAudit info = new InfoAudit();
        if (permohonanKertas != null) {
            LOG.info("------if------permohonankertas NOT Null--------------::" + permohonanKertas);
            info = permohonanKertas.getInfoAudit();
            info.setDiKemaskiniOleh(peng);
            info.setTarikhKemaskini(new java.util.Date());

        } else {
            permohonanKertas = new PermohonanKertas();
            LOG.info("------if------permohonankertas Null--------------::" + permohonanKertas);
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            permohonanKertas.setInfoAudit(info);
            permohonanKertas.setCawangan(peng.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTarikhSidang(tarikhMesyuarat);
        }

        permohonanKertas.setNomborRujukan(nomborRujukan);
        kd = kodDokumenDAO.findById("JKTD");
        permohonanKertas.setKodDokumen(kd);
        permohonanKertas.setTajuk("Rekod JKTD");
        pservice.simpanPermohonanKertas(permohonanKertas);
        showK = Boolean.FALSE;
        viewK = Boolean.FALSE;
        showA = Boolean.TRUE;

        addSimpleMessage("Maklumat Berjaya Disimpan");
        return new JSP("pelupusan/rekod_keputusan_jktd.jsp").addParameter("tab", "true");

    }

    public KodKeputusanDAO getKodKeputusanDAO() {
        return kodKeputusanDAO;
    }

    public void setKodKeputusanDAO(KodKeputusanDAO kodKeputusanDAO) {
        this.kodKeputusanDAO = kodKeputusanDAO;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public KodDokumen getKd() {
        return kd;
    }

    public void setKd(KodDokumen kd) {
        this.kd = kd;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public HakmilikPermohonanDAO getHakmilikPermohonanDAO() {
        return hakmilikPermohonanDAO;
    }

    public void setHakmilikPermohonanDAO(HakmilikPermohonanDAO hakmilikPermohonanDAO) {
        this.hakmilikPermohonanDAO = hakmilikPermohonanDAO;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanKertasKandungan getKertasKandungan() {
        return kertasKandungan;
    }

    public void setKertasKandungan(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
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

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public PermohonanKertasDAO getPermohonanKertasDAO() {
        return permohonanKertasDAO;
    }

    public void setPermohonanKertasDAO(PermohonanKertasDAO permohonanKertasDAO) {
        this.permohonanKertasDAO = permohonanKertasDAO;
    }

    public PermohonanKertasKandunganDAO getPermohonanKertasKandunganDAO() {
        return permohonanKertasKandunganDAO;
    }

    public void setPermohonanKertasKandunganDAO(PermohonanKertasKandunganDAO permohonanKertasKandunganDAO) {
        this.permohonanKertasKandunganDAO = permohonanKertasKandunganDAO;
    }

    public PelupusanService getPservice() {
        return pservice;
    }

    public void setPservice(PelupusanService pservice) {
        this.pservice = pservice;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public String getNomborRujukan() {
        return nomborRujukan;
    }

    public void setNomborRujukan(String nomborRujukan) {
        this.nomborRujukan = nomborRujukan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Date getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(Date tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public boolean isShowK() {
        return showK;
    }

    public void setShowK(boolean showK) {
        this.showK = showK;
    }

    public boolean isViewK() {
        return viewK;
    }

    public void setViewK(boolean viewK) {
        this.viewK = viewK;
    }

    public boolean isShowA() {
        return showA;
    }

    public void setShowA(boolean showA) {
        this.showA = showA;
    }

    public String getKpsn() {
        return kpsn;
    }

    public void setKpsn(String kpsn) {
        this.kpsn = kpsn;
    }

    public String getKpsnnama() {
        return kpsnnama;
    }

    public void setKpsnnama(String kpsnnama) {
        this.kpsnnama = kpsnnama;
    }
    
    public String stageId(String taskId, Pengguna pguna) {
        BPelService service = new BPelService();
        String stageId = null;
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = service.getTaskFromBPel(taskId, pguna);
            stageId = t.getSystemAttributes().getStage();
        }
        return stageId;
    }
}
