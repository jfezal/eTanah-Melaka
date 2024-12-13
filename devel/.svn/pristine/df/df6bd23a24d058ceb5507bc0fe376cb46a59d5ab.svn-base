/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.KodTransaksiTuntutDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.service.PelupusanService;
import etanah.service.common.DokumenService;
import etanah.service.common.PerbicaraanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
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
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;

/**
 *
 * @author shazwan (based on Pengambilan)
 */
@UrlBinding("/pelupusan/borang2A_notisMengenaiSiasatan")
public class Borang2A_NotisMengenaiSiasatanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    PelupusanService plpservice ;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    PerbicaraanService perbicaraanService;
     @Inject
    KodTransaksiTuntutDAO kodTransaksiTuntutDAO;
     @Inject
    BPelService service;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private DokumenService dokumenService;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<String> tarikhBicara = new ArrayList<String>();
    private List<String> waktuPerbicaraan = new ArrayList<String>();
    private List<String> lokasiBicara = new ArrayList<String>();
    private List<String> jam = new ArrayList<String>();
    private List<String> minit = new ArrayList<String>();
    private List<String> ampm = new ArrayList<String>();
    private Task task=null;
    private String taskId;
    private String stageId;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/hak_lalulalang/borang2A_NotisMengenaiSiasatan.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        hakmilik = hakmilikDAO.findById(idHakmilik);
        return new JSP("pelupusan/hak_lalulalang/borang2A_NotisMengenaiSiasatan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        
        stageId = getStageName();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        if (idPermohonan != null) {
            Permohonan p = permohonanDAO.findById(idPermohonan);
            permohonanPihakList = p.getSenaraiPihak();
            List senaraiHakmilikTerlibat = new ArrayList<HakmilikPermohonan>();
            hakmilikPermohonanList = plpservice.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
            
            for (int i = 0; i < hakmilikPermohonanList.size(); i++) {
                
                HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);
                HakmilikPerbicaraan hbicara = new HakmilikPerbicaraan();
                
                hbicara = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                if (hbicara==null) {
                
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                    hakmilikPerbicaraan.setBatalRizab('T');
                    hakmilikPerbicaraan.setKawasanPBT('T');
                    hakmilikPerbicaraan.setPelanPembangunan('T');
                } else {
                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                    InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                    ia.setDiKemaskiniOleh(peng);
                    ia.setTarikhKemaskini(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(ia);
                }

                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                if (hakmilikPerbicaraan != null) {
                    try {
                        String lb = hakmilikPerbicaraan.getLokasiBicara();
                        lokasiBicara.add(lb);
                        String tb = sdf.format(hakmilikPerbicaraan.getTarikhBicara());

                        String tb1 = tb.substring(0, 10);
                        String tb2 = tb.substring(11, 13);
                        String tb3 = tb.substring(14, 16);
                        String tb4 = tb.substring(17, 19);
                        tarikhBicara.add(tb1);
                        jam.add(tb2);
                        minit.add(tb3);
                        ampm.add(tb4);
                    } catch (Exception e) {
                    }

                }
            }
        }
    }
    
    
    public String getStageName(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");
        if (StringUtils.isBlank(taskId)) {
            taskId = getContext().getRequest().getParameter("taskId");
        }
        task = service.getTaskFromBPel(taskId, peng);
        if (task != null) {
            stageId = task.getSystemAttributes().getStage();
        } else {
            stageId = getContext().getRequest().getParameter("stageId");
        }
        return stageId;
        
    }
    

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(Borang2A_NotisMengenaiSiasatanActionBean.class, "locate");
    }

    public Resolution simpan() throws ParseException {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = new InfoAudit();

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        if (idPermohonan != null) {
            Notis notis = new Notis();
            info.setDimasukOleh(peng);
            info.setTarikhMasuk(new java.util.Date());
            notis.setInfoAudit(info);
            notis.setPermohonan(permohonan);
            notis.setPihak(null);

            Dokumen dokumen = new Dokumen();
            dokumen.setTajuk(tajuk);
            dokumen.setInfoAudit(info);
            Dokumen dokumen1 = dokumenService.saveOrUpdate(dokumen);

            notis.setDokumenNotis(dokumen1);
            notis.setTarikhNotis(sdf.parse(tarikhNotis));

            KodStatusTerima kodStatusTerima1 = kodStatusTerimaDAO.findById(kodStatusTerima);

            notis.setKodStatusTerima(kodStatusTerima1);
            notis.setTarikhHantar(sdf.parse(tarikhHantar));
            notis.setTarikhTampal(sdf.parse(tarikhTampal));
            
                /*
                 * MOHON TUNTUT KOS
                */
                PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                mohonTuntutKos = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBS");
                
                if(mohonTuntutKos==null){
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Siasatan");
                    mohonTuntutKos.setAmaunTuntutan(BigDecimal.valueOf(100));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBS"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBS").getKodKewTrans());
                    plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos); 
                }else{
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Siasatan");
                    mohonTuntutKos.setAmaunTuntutan(BigDecimal.valueOf(100));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBS"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBS").getKodKewTrans());
                    plpservice.simpanPermohonanTuntutanKos(mohonTuntutKos); 
                }
            
            
            
            addSimpleMessage("Maklumat telah berjaya disimpan");
        }
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/hak_lalulalang/borang2A_NotisMengenaiSiasatan.jsp").addParameter("tab", "true");
    }

    public Resolution editDetails() {

        String rowCount = (String) getContext().getRequest().getParameter("rowCount");
        int rowCountval = Integer.parseInt(rowCount);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        KodCawangan cawangan = permohonan.getCawangan();

        HakmilikPermohonan hmp = hakmilikPermohonanList.get(rowCountval);
        HakmilikPerbicaraan hakmilikPerbicaraan = new HakmilikPerbicaraan();
        info.setDimasukOleh(peng);
        info.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan.setInfoAudit(info);
        hakmilikPerbicaraan.setCawangan(cawangan);
        hakmilikPerbicaraan.setHakmilikPermohonan(hmp);

        try {
            if (lokasiBicara.get(rowCountval) != null) {
                hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(rowCountval));
            }

            if (tarikhBicara.get(rowCountval) != null) {
                String StrTarikhBicara = tarikhBicara.get(rowCountval);
                StrTarikhBicara = StrTarikhBicara + " " + jam.get(rowCountval) + ":" + minit.get(rowCountval) + " " + ampm.get(rowCountval);

                hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ForwardResolution("/WEB-INF/jsp/pelupusan/hak_lalulalang/borang2A_NotisMengenaiSiasatan.jsp").addParameter("tab", "true");
    }

    public Resolution simpanHakmilik() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        for (int i = 0; i < hakmilikPermohonanList.size(); i++) {

            HakmilikPermohonan hmp = hakmilikPermohonanList.get(i);

            if (perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId()) == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hmp);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');
            } else {
                hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hmp.getId());
                InfoAudit ia = hakmilikPerbicaraan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(ia);
            }
            /*
                 * CASE Bayaran Endosan MCL (MCMCL)
                */
//                PermohonanTuntutanKos mohonTuntutKos = new PermohonanTuntutanKos();
                PermohonanTuntutanKos mohonTuntutKos = plpservice.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan,"DISBS");
                InfoAudit info = new InfoAudit();
//                mohonTuntutKos = new PermohonanTuntutanKos();
//                mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISMCL");
                PermohonanTuntutan mohonTuntut = new PermohonanTuntutan();
                mohonTuntut = plpservice.findPermohonanTuntutanByIdMohonAndKodTransTuntut(idPermohonan, "DISBS");
                if(mohonTuntut==null){
                    mohonTuntut = new PermohonanTuntutan();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntut.setInfoAudit(info);
                    mohonTuntut.setPermohonan(permohonan);
                    mohonTuntut.setCawangan(permohonan.getCawangan());
                    mohonTuntut.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DISBS"));        
                    mohonTuntut.setTarikhTuntutan(new java.util.Date());
                    plpservice.simpanPermohonanTuntutan(mohonTuntut); 
                }else{
                    info = mohonTuntut.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntut.setInfoAudit(info);
                    mohonTuntut.setPermohonan(permohonan);
                    mohonTuntut.setCawangan(permohonan.getCawangan());
                    mohonTuntut.setKodTransaksiTuntut(kodTransaksiTuntutDAO.findById("DISBS"));
                    mohonTuntut.setTarikhTuntutan(new java.util.Date());
                    plpservice.simpanPermohonanTuntutan(mohonTuntut); 
                }
                
                if(mohonTuntutKos==null){
                    mohonTuntutKos = new PermohonanTuntutanKos();
                    info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Siasatan");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(100));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBS"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBS").getKodKewTrans());
                    plpservice.simpanSavePermohonanTuntutanKos(mohonTuntutKos); 
                }else{
                    info = mohonTuntutKos.getInfoAudit();
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());
                    mohonTuntutKos.setInfoAudit(info);
                    mohonTuntutKos.setPermohonan(permohonan);
                    mohonTuntutKos.setCawangan(permohonan.getCawangan());
                    mohonTuntutKos.setItem("Bayaran Siasatan");
                    mohonTuntutKos.setAmaunTuntutan(new BigDecimal(100));
                    mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISBS"));
                    mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISBS").getKodKewTrans());
                    plpservice.simpanPermohonanTuntutanKos(mohonTuntutKos); 
                }
            try {

                if (i < lokasiBicara.size()) {
                    if (lokasiBicara.get(i) != null) {
                        hakmilikPerbicaraan.setLokasiBicara(lokasiBicara.get(i));
                    }
                }

                if (i < tarikhBicara.size()) {
                    if (tarikhBicara.get(i) != null) {
                        String StrTarikhBicara = tarikhBicara.get(i);
                        StrTarikhBicara = StrTarikhBicara + " " + jam.get(i) + ":" + minit.get(i) + " " + ampm.get(i);
                        hakmilikPerbicaraan.setTarikhBicara(sdf.parse(StrTarikhBicara));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                addSimpleError("Invalid Data");
            }
            perbicaraanService.saveSingleHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/hak_lalulalang/borang2A_NotisMengenaiSiasatan.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
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

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public List<String> getLokasiBicara() {
        return lokasiBicara;
    }

    public void setLokasiBicara(List<String> lokasiBicara) {
        this.lokasiBicara = lokasiBicara;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<String> getTarikhBicara() {
        return tarikhBicara;
    }

    public void setTarikhBicara(List<String> tarikhBicara) {
        this.tarikhBicara = tarikhBicara;
    }

    public List<String> getWaktuPerbicaraan() {
        return waktuPerbicaraan;
    }

    public void setWaktuPerbicaraan(List<String> waktuPerbicaraan) {
        this.waktuPerbicaraan = waktuPerbicaraan;
    }

    public List<String> getAmpm() {
        return ampm;
    }

    public void setAmpm(List<String> ampm) {
        this.ampm = ampm;
    }

    public List<String> getJam() {
        return jam;
    }

    public void setJam(List<String> jam) {
        this.jam = jam;
    }

    public List<String> getMinit() {
        return minit;
    }

    public void setMinit(List<String> minit) {
        this.minit = minit;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    
    
}
