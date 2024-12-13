/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.service.EnforceService;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.DokumenDAO;
import etanah.dao.KehadiranDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodKeputusanPendakwaanDAO;
import etanah.dao.KodPerananRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarPerananDAO;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.KodRujukan;
import etanah.model.Dokumen;
import etanah.model.Kehadiran;
import etanah.model.KodAgensi;
import etanah.model.KodKeputusanPendakwaan;
import etanah.model.KodPerananRujukanLuar;

import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuarPeranan;
import etanah.service.BPelService;
import etanah.service.common.EnforcementService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.common.senaraiKodsyaratnyataActionBean;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.sourceforge.stripes.action.RedirectResolution;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_mahkamah")
public class MaklumatMahkamahActionBean extends AbleActionBean {
    
    private static Logger logger = Logger.getLogger(MaklumatMahkamahActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanRujukanLuarPerananDAO permohonanRujukanLuarPerananDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodPerananRujukanLuarDAO kodPerananRujukanLuarDAO;
    @Inject
    KodKeputusanPendakwaanDAO kodKeputusanPendakwaanDAO;
    @Inject
    KehadiranDAO kehadiranDAO;
    @Inject
    private Permohonan permohonan;
    private Pengguna pengguna;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private List<PermohonanRujukanLuar> senaraiMahkamah;
    private KodCawangan cawangan;
    private KodDokumen koddokumen;
    private KodRujukan kodRujukan;
    private String idRujukan;
    private String tarikhSidang;
    private String tarikhRujukan;
    private String tarikhSidangMH;
    private String catatan;
    private String noRujukan;
    private String namaSidang;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
    private List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan;
    private PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan;
    private String idPermohonan;
    private String kategoriMahkamah;
    private String recordCount;
    private String stageId;
    private List<PermohonanRujukanLuar> senaraiStatusDakwa;
    private String status;
    private String kod;
    private String keputusanMahkamah;
    private List<Kehadiran> senaraiPerbicaraan = new ArrayList<Kehadiran>();
    private String jam;
    private String minit;
    private String ampm;
    private String tarikhPerbicaraan;
    private String catatanPerbicaraan;
    private Kehadiran kehadiran;
    private String recordCountPerbicaraan;
    private String taskId;
    private Boolean addInfoByPUU = Boolean.FALSE;
    
    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm3() {
        getContext().getRequest().setAttribute("kemaskiniKeputusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("popup", "true");
    }
    
    public Resolution showForm4() {
        getContext().getRequest().setAttribute("editPerbicaraan", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("popup", "true");
    }
    
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            senaraiMahkamah = enforcementService.findMahkamahByIDPermohonan(idPermohonan);
            senaraiStatusDakwa = enforcementService.findStatusByIDPermohonan(idPermohonan, kod);
        }
        
        BPelService bpelService = new BPelService();
        if (StringUtils.isNotBlank(taskId)) {
            Task t = null;
            t = bpelService.getTaskFromBPel(taskId, pengguna);
            stageId = t.getSystemAttributes().getStage();
            logger.info("-------------stageId: BPEL ON ----" + stageId);
        } else {
            stageId = "maklum_kpsn_mahkamah" ;
            logger.info("-------------stageId: BPEL OFF ----" + stageId);
        }
        
        
        if (StringUtils.isNotBlank(stageId)) {
            if (stageId.equalsIgnoreCase("maklum_trh_sebutan")) {// || stageId.equalsIgnoreCase("kemaskini_rekod_keputusan")
                addInfoByPUU = true;
            }
        }
        
        logger.info("-----addInfoByPUU :::::" + addInfoByPUU);
        
    }
    
    public Resolution popupTarikhSebutan() {
        //for stage rekod tarikh sebutan : for sek 422,423,424,427,428,429 MELAKA
        return new JSP("penguatkuasaan/popup_tarikh_sebutan.jsp").addParameter("popup", "true");
    }
    
    public Resolution popupMaklumatMahkamah() {
        getContext().getRequest().setAttribute("addInfoByPUU", Boolean.TRUE);
        return new JSP("penguatkuasaan/popup_tarikh_sebutan.jsp").addParameter("popup", "true");
    }
    
    public Resolution popupEditTarikhSebutan() {
        //for stage rekod tarikh sebutan : for sek 422,423,424,427,428,429 MELAKA
        logger.info("--------------popupEditTarikhSebutan--------------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
            if (permohonanRujukanLuar.getAgensi() != null) {
                kategoriMahkamah = permohonanRujukanLuar.getAgensi().getKod();
            }
            namaSidang = permohonanRujukanLuar.getNamaSidang();
            noRujukan = permohonanRujukanLuar.getNoRujukan();
            catatan = permohonanRujukanLuar.getCatatan();
            if (permohonanRujukanLuar.getTarikhSidang() != null) {
                tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
            }
            if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
            }
            if (permohonanRujukanLuar.getKeputusanPendakwaan() != null) {
                status = permohonanRujukanLuar.getKeputusanPendakwaan().getKod();
            }
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            keputusanMahkamah = permohonanRujukanLuar.getUlasan();
            senaraiPerbicaraan = enforcementService.getSenaraiPerbicaraan(Long.toString(permohonanRujukanLuar.getIdRujukan()));
            recordCountPerbicaraan = String.valueOf(senaraiPerbicaraan.size());
        }
        return new JSP("penguatkuasaan/popup_tarikh_sebutan.jsp").addParameter("popup", "true");
    }
    
    public Resolution popupKemaskiniKeputusan() {
        //for stage kemaskini keputusan : for sek 422,423,424,427,428,429 MELAKA
        logger.info("--------------popupKemaskiniKeputusan--------------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
            if (permohonanRujukanLuar.getAgensi() != null) {
                kategoriMahkamah = permohonanRujukanLuar.getAgensi().getKod();
            }
            
            namaSidang = permohonanRujukanLuar.getNamaSidang();
            noRujukan = permohonanRujukanLuar.getNoRujukan();
            catatan = permohonanRujukanLuar.getCatatan();
            if (permohonanRujukanLuar.getTarikhSidang() != null) {
                tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
            }
            if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
            }
            
            if (permohonanRujukanLuar.getKeputusanPendakwaan() != null) {
                status = permohonanRujukanLuar.getKeputusanPendakwaan().getKod();
            }
            keputusanMahkamah = permohonanRujukanLuar.getUlasan();
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
        }
        return new JSP("penguatkuasaan/popup_kemaskini_keputusan.jsp").addParameter("popup", "true");
    }
    
    public Resolution popupViewRecord() {
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
        }
        return new JSP("penguatkuasaan/popup_view_mahkamah.jsp").addParameter("popup", "true");
    }
    
    public Resolution simpanRecordTarikhSebutan() throws ParseException {
        logger.info("--------------simpanRecordTarikhSebutan--------------");
        
        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");
        
        permohonanRujukanLuar = new PermohonanRujukanLuar();
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        
        if (!kategoriMahkamah.isEmpty()) {
            KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
            permohonanRujukanLuar.setAgensi(agensi);
        }
        
        if (!status.isEmpty()) {
            KodKeputusanPendakwaan keputusanPendakwaan = kodKeputusanPendakwaanDAO.findById(status);
            permohonanRujukanLuar.setKeputusanPendakwaan(keputusanPendakwaan);
        }
        if (getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            permohonanRujukanLuar.setTarikhSidang(null);
        }
        if (!getContext().getRequest().getParameter("tarikhSidang").isEmpty()) {
            tarikhSidang = getContext().getRequest().getParameter("tarikhSidang");
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        namaSidang = getContext().getRequest().getParameter("namaSidang");
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        noRujukan = getContext().getRequest().getParameter("noRujukan");
        permohonanRujukanLuar.setNoRujukan(noRujukan);
        permohonanRujukanLuar.setCatatan(catatan);
        
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        
        
        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");
            
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {
                
                permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();
                
                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);
                
                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);
                
                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }
            
            if (addInfoByPUU == true) {
                idRujukan = getContext().getRequest().getParameter("idRujukan");
                
                
                if (tarikhRujukan != null) {
                    permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
                } else {
                    permohonanRujukanLuar.setTarikhRujukan(null);
                }
                permohonanRujukanLuar.setUlasan(keputusanMahkamah);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
                String idPenyerah = null;

//                permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));

                try {
                    logger.info("--------------simpan kehadiran--------------");
                    
                    int rowCount1 = Integer.parseInt(getContext().getRequest().getParameter("recordCountPerbicaraan"));
                    for (int i = 0; i < rowCount1; i++) {
                        
                        if (permohonanRujukanLuar != null) {
                            idPenyerah = String.valueOf(permohonanRujukanLuar.getIdRujukan());
                            senaraiPerbicaraan = enforcementService.getSenaraiPerbicaraan(idRujukan);
                        }
                        
                        if (senaraiPerbicaraan.size() != 0 && i < senaraiPerbicaraan.size()) {
                            Long id = senaraiPerbicaraan.get(i).getIdKehadiran();
                            if (id != null) {
                                kehadiran = kehadiranDAO.findById(id);
                                ia = kehadiran.getInfoAudit();
                                ia.setDiKemaskiniOleh(peng);
                                ia.setTarikhKemaskini(new java.util.Date());
                            }
                        } else {
                            kehadiran = new Kehadiran();
                            ia.setDimasukOleh(peng);
                            ia.setTarikhMasuk(new Date());
                        }
                        
                        tarikhPerbicaraan = getContext().getRequest().getParameter("tarikh" + i);
                        jam = getContext().getRequest().getParameter("jam" + i);
                        minit = getContext().getRequest().getParameter("minit" + i);
                        ampm = getContext().getRequest().getParameter("ampm" + i);
                        tarikhPerbicaraan = tarikhPerbicaraan + " " + jam + ":" + minit + " " + ampm;
                        logger.info("tarikhPerbicaraan : " + tarikhPerbicaraan);
                        catatanPerbicaraan = getContext().getRequest().getParameter("catatanPerbicaraan" + i);
                        
                        kehadiran.setCatatan(catatanPerbicaraan);
                        
                        if (tarikhPerbicaraan != null) {
                            try {
                                kehadiran.setTarikhKehadiran(sdf1.parse(tarikhPerbicaraan));
                            } catch (ParseException ex) {
                                System.out.println("Got error" + ex);
                            }
                        }
//                kehadiran.setIdPenyerah(Integer.parseInt(idPenyerah));
                        kehadiran.setWakilJawatan(String.valueOf(permohonanRujukanLuar.getIdRujukan()));
                        kehadiran.setInfoAudit(ia);
                        enforcementService.simpanKehadiran(kehadiran);
                    }
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
        } catch (Exception ex) {
            logger.error(ex);
            ex.printStackTrace();
        }
        
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
    }
    
    public Resolution editRecordTarikhSebutan() throws ParseException {
        logger.info("-------editRecordTarikhSebutan-----------");
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        
        KodDokumen doc = new KodDokumen();
        KodRujukan kr = new KodRujukan();
        doc.setKod("KMD");
        kr.setKod("NF");
        
        InfoAudit ia = new InfoAudit();
        
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
        
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setPermohonan(permohonan);
        permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
        permohonanRujukanLuar.setKodDokumenRujukan(doc);
        permohonanRujukanLuar.setKodRujukan(kr);
        if (tarikhSidang != null) {
            permohonanRujukanLuar.setTarikhSidang(sdf.parse(tarikhSidang));
        }
        
        permohonanRujukanLuar.setNamaSidang(namaSidang);
        permohonanRujukanLuar.setNoRujukan(noRujukan);
        permohonanRujukanLuar.setCatatan(catatan);
        permohonanRujukanLuar.setInfoAudit(ia);
        
        if (!kategoriMahkamah.isEmpty()) {
            KodAgensi agensi = kodAgensiDAO.findById(kategoriMahkamah);
            permohonanRujukanLuar.setAgensi(agensi);
        }
        if (!status.isEmpty()) {
            KodKeputusanPendakwaan keputusanPendakwaan = kodKeputusanPendakwaanDAO.findById(status);
            permohonanRujukanLuar.setKeputusanPendakwaan(keputusanPendakwaan);
        }
        
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        
        try {
            logger.info("--------------simpan permohonanRujukanLuarPeranan--------------");
            
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < rowCount; i++) {
                
                if (permohonanRujukanLuar != null) {
                    senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
                }
                
                if (senaraipermohonanRujukanLuarPeranan.size() != 0 && i < senaraipermohonanRujukanLuarPeranan.size()) {
                    Long id = senaraipermohonanRujukanLuarPeranan.get(i).getIdPeranan();
                    if (id != null) {
                        permohonanRujukanLuarPeranan = enforcementService.findPeranan(id);
                    }
                } else {
                    permohonanRujukanLuarPeranan = new PermohonanRujukanLuarPeranan();
                }
                
                String nama = getContext().getRequest().getParameter("nama" + i);
                String jawatan = getContext().getRequest().getParameter("jawatan" + i);
                
                KodPerananRujukanLuar kp = kodPerananRujukanLuarDAO.findById(jawatan);
                
                permohonanRujukanLuarPeranan.setCawangan(peng.getKodCawangan());
                permohonanRujukanLuarPeranan.setRujukan(permohonanRujukanLuar);
                permohonanRujukanLuarPeranan.setNama(nama);
                permohonanRujukanLuarPeranan.setKodPerananRujukanLuar(kp);
                permohonanRujukanLuarPeranan.setInfoAudit(ia);
                enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        if (addInfoByPUU == true) {
            
            
            String idPenyerah = null;
            if (tarikhRujukan != null) {
                permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
            } else {
                permohonanRujukanLuar.setTarikhRujukan(null);
            }
            permohonanRujukanLuar.setUlasan(keputusanMahkamah);
            enforcementService.simpanRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            
            try {
                logger.info("--------------simpan kehadiran--------------");
                
                int rowCount1 = Integer.parseInt(getContext().getRequest().getParameter("recordCountPerbicaraan"));
                for (int i = 0; i < rowCount1; i++) {
                    
                    if (permohonanRujukanLuar != null) {
                        idPenyerah = String.valueOf(permohonanRujukanLuar.getIdRujukan());
                        senaraiPerbicaraan = enforcementService.getSenaraiPerbicaraan(idRujukan);
                    }
                    
                    if (senaraiPerbicaraan.size() != 0 && i < senaraiPerbicaraan.size()) {
                        Long id = senaraiPerbicaraan.get(i).getIdKehadiran();
                        if (id != null) {
                            kehadiran = kehadiranDAO.findById(id);
                            ia = kehadiran.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                        }
                    } else {
                        kehadiran = new Kehadiran();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new Date());
                    }
                    
                    tarikhPerbicaraan = getContext().getRequest().getParameter("tarikh" + i);
                    jam = getContext().getRequest().getParameter("jam" + i);
                    minit = getContext().getRequest().getParameter("minit" + i);
                    ampm = getContext().getRequest().getParameter("ampm" + i);
                    tarikhPerbicaraan = tarikhPerbicaraan + " " + jam + ":" + minit + " " + ampm;
                    logger.info("tarikhPerbicaraan : " + tarikhPerbicaraan);
                    catatanPerbicaraan = getContext().getRequest().getParameter("catatanPerbicaraan" + i);
                    
                    kehadiran.setCatatan(catatanPerbicaraan);
                    
                    if (tarikhPerbicaraan != null) {
                        try {
                            kehadiran.setTarikhKehadiran(sdf1.parse(tarikhPerbicaraan));
                        } catch (ParseException ex) {
                            System.out.println("Got error" + ex);
                        }
                    }
//                kehadiran.setIdPenyerah(Integer.parseInt(idPenyerah));
                    kehadiran.setWakilJawatan(idRujukan);
                    kehadiran.setInfoAudit(ia);
                    enforcementService.simpanKehadiran(kehadiran);
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
        
    }
    
    public Resolution kemaskiniRekodKeputusan() throws ParseException {
        logger.info("-------kemaskiniRekodKeputusan-----------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        
        InfoAudit ia = new InfoAudit();
        
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
        if (tarikhRujukan != null) {
            permohonanRujukanLuar.setTarikhRujukan(sdf.parse(tarikhRujukan));
        } else {
            permohonanRujukanLuar.setTarikhRujukan(null);
        }
        permohonanRujukanLuar.setUlasan(keputusanMahkamah);
        
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        permohonanRujukanLuar.setInfoAudit(ia);
        enforcementService.simpanRujukan(permohonanRujukanLuar);
        
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("kemaskiniKeputusan", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
    }
    
    public Resolution deletePeranan() {
        logger.info("--------------deletePeranan--------------");
        String idPasukan = getContext().getRequest().getParameter("idPasukan");
        try {
            if (idPasukan != null) {
                permohonanRujukanLuarPeranan = permohonanRujukanLuarPerananDAO.findById(Long.parseLong(idPasukan));
            }
            enforcementService.deletePeranan(permohonanRujukanLuarPeranan);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan.");
        return new JSP("penguatkuasaan/popup_tarikh_sebutan.jsp").addParameter("tab", "true");
    }
    
    public Resolution refreshpage() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatMahkamahActionBean.class, "locate");
    }
    
    public Resolution deleteSingle() {
        logger.info("--------------delete record--------------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
        
        if (permohonanRujukanLuar != null) {

            //delete child first : permohonanRujukanLuarPeranan (in list)
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            for (int i = 0; i < senaraipermohonanRujukanLuarPeranan.size(); i++) {
                permohonanRujukanLuarPeranan = enforcementService.findPeranan(senaraipermohonanRujukanLuarPeranan.get(i).getIdPeranan());
                enforcementService.deleteRujukanLuarPeranan(permohonanRujukanLuarPeranan);
            }

            //Then, delete parent : permohonanRujukanLuar
            enforcementService.deleteMesy(permohonanRujukanLuar);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new RedirectResolution(MaklumatMahkamahActionBean.class, "locate");
    }
    
    public Resolution deleteSelected() {
        logger.info("--------------deleteSelected : delete imej-------------");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Long id = Long.parseLong(getContext().getRequest().getParameter("idImej"));
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        
        Session s = sessionProvider.get();
        Transaction tx = s.beginTransaction();
        tx.begin();
        try {
            Dokumen dok1 = dokumenDAO.findById(id);
            dokumenDAO.delete(dok1);
            
            permohonanRujukanLuar.setDokumen(null);
            InfoAudit ia = permohonanRujukanLuar.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanRujukanLuar.setInfoAudit(ia);
            enforceService.simpanRujukan(permohonanRujukanLuar);
            
            tx.commit();
            
        } catch (Exception ex) {
            tx.rollback();
            Throwable t = ex;
            // getting the root cause
            while (t.getCause() != null) {
                t = t.getCause();
            }
            ex.printStackTrace();
            addSimpleError(t.getMessage());
        }
        return new RedirectResolution(MaklumatMahkamahActionBean.class, "locate");
    }
    
    public Resolution popupKemaskiniRekodPerbicaraan() {
        //for stage kemaskini keputusan : for sek 422,423,424,427,428,429 MELAKA
        logger.info("--------------popupKemaskiniRekodPerbicaraan--------------");
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        permohonanRujukanLuar = permohonanRujukanLuarDAO.findById(Long.parseLong(idRujukan));
        senaraiPerbicaraan = enforcementService.getSenaraiPerbicaraan(idRujukan);
        recordCountPerbicaraan = String.valueOf(senaraiPerbicaraan.size());
        if (permohonanRujukanLuar != null) {
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
            recordCount = String.valueOf(senaraipermohonanRujukanLuarPeranan.size());
            if (permohonanRujukanLuar.getAgensi() != null) {
                kategoriMahkamah = permohonanRujukanLuar.getAgensi().getKod();
            }
            
            namaSidang = permohonanRujukanLuar.getNamaSidang();
            noRujukan = permohonanRujukanLuar.getNoRujukan();
            catatan = permohonanRujukanLuar.getCatatan();
            if (permohonanRujukanLuar.getTarikhSidang() != null) {
                tarikhSidang = sdf.format(permohonanRujukanLuar.getTarikhSidang());
            }
            if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                tarikhRujukan = sdf.format(permohonanRujukanLuar.getTarikhRujukan());
            }
            
            if (permohonanRujukanLuar.getKeputusanPendakwaan() != null) {
                status = permohonanRujukanLuar.getKeputusanPendakwaan().getKod();
            }
            keputusanMahkamah = permohonanRujukanLuar.getUlasan();
            senaraipermohonanRujukanLuarPeranan = enforcementService.getSenaraiRujLuarPeranan(permohonanRujukanLuar.getIdRujukan());
        }
        return new JSP("penguatkuasaan/popup_rekod_perbicaraan.jsp").addParameter("popup", "true");
    }
    
    public Resolution simpanRekodPerbicaraan() throws ParseException {
        logger.info("-------simpanRekodPerbicaraan-----------");
        
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idRujukan = getContext().getRequest().getParameter("idRujukan");
        
        InfoAudit ia = new InfoAudit();
        
        String idPenyerah = null;
        
        permohonanRujukanLuar = enforcementService.findMesyByIdRujukan(Long.parseLong(idRujukan));
        
        try {
            logger.info("--------------simpan kehadiran--------------");
            
            int rowCount = Integer.parseInt(getContext().getRequest().getParameter("recordCountPerbicaraan"));
            for (int i = 0; i < rowCount; i++) {
                
                if (permohonanRujukanLuar != null) {
                    idPenyerah = String.valueOf(permohonanRujukanLuar.getIdRujukan());
                    senaraiPerbicaraan = enforcementService.getSenaraiPerbicaraan(idRujukan);
                }
                
                if (senaraiPerbicaraan.size() != 0 && i < senaraiPerbicaraan.size()) {
                    Long id = senaraiPerbicaraan.get(i).getIdKehadiran();
                    if (id != null) {
                        kehadiran = kehadiranDAO.findById(id);
                        ia = kehadiran.getInfoAudit();
                        ia.setDiKemaskiniOleh(peng);
                        ia.setTarikhKemaskini(new java.util.Date());
                    }
                } else {
                    kehadiran = new Kehadiran();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new Date());
                }
                
                tarikhPerbicaraan = getContext().getRequest().getParameter("tarikh" + i);
                jam = getContext().getRequest().getParameter("jam" + i);
                minit = getContext().getRequest().getParameter("minit" + i);
                ampm = getContext().getRequest().getParameter("ampm" + i);
                tarikhPerbicaraan = tarikhPerbicaraan + " " + jam + ":" + minit + " " + ampm;
                logger.info("tarikhPerbicaraan : " + tarikhPerbicaraan);
                catatanPerbicaraan = getContext().getRequest().getParameter("catatanPerbicaraan" + i);
                
                kehadiran.setCatatan(catatanPerbicaraan);
                
                if (tarikhPerbicaraan != null) {
                    try {
                        kehadiran.setTarikhKehadiran(sdf1.parse(tarikhPerbicaraan));
                    } catch (ParseException ex) {
                        System.out.println("Got error" + ex);
                    }
                }
//                kehadiran.setIdPenyerah(Integer.parseInt(idPenyerah));
                kehadiran.setWakilJawatan(idRujukan);
                kehadiran.setInfoAudit(ia);
                enforcementService.simpanKehadiran(kehadiran);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        addSimpleMessage("Maklumat telah berjaya dikemaskini.");
        getContext().getRequest().setAttribute("editPerbicaraan", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_keputusan_mahkamah.jsp").addParameter("tab", "true");
        
    }
    
    public Resolution deleteRecordKehadiran() {
        logger.info("--------------delete record--------------");
        String id = getContext().getRequest().getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            kehadiran = kehadiranDAO.findById(Long.parseLong(id));
            if (kehadiran != null) {
                enforcementService.deleteKehadiranPerbicaraan(kehadiran);
            }
        }
        
        getContext().getRequest().setAttribute("editPerbicaraan", Boolean.TRUE);
        return new RedirectResolution(MaklumatMahkamahActionBean.class, "locate");
    }
    
    public KodCawangan getCawangan() {
        return cawangan;
    }
    
    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }
    
    public String getIdRujukan() {
        return idRujukan;
    }
    
    public void setIdRujukan(String idRujukan) {
        this.idRujukan = idRujukan;
    }
    
    public Permohonan getPermohonan() {
        return permohonan;
    }
    
    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
    
    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }
    
    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
    
    public String getTarikhSidang() {
        return tarikhSidang;
    }
    
    public void setTarikhSidang(String tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }
    
    public KodDokumen getKoddokumen() {
        return koddokumen;
    }
    
    public void setKoddokumen(KodDokumen koddokumen) {
        this.koddokumen = koddokumen;
    }
    
    public String getTarikhSidangMH() {
        return tarikhSidangMH;
    }
    
    public void setTarikhSidangMH(String tarikhSidangMH) {
        this.tarikhSidangMH = tarikhSidangMH;
    }
    
    public KodRujukan getKodRujukan() {
        return kodRujukan;
    }
    
    public void setKodRujukan(KodRujukan kodRujukan) {
        this.kodRujukan = kodRujukan;
    }
    
    public List<PermohonanRujukanLuar> getSenaraiMahkamah() {
        return senaraiMahkamah;
    }
    
    public void setSenaraiMahkamah(List<PermohonanRujukanLuar> senaraiMahkamah) {
        this.senaraiMahkamah = senaraiMahkamah;
    }
    
    public String getCatatan() {
        return catatan;
    }
    
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    
    public String getNamaSidang() {
        return namaSidang;
    }
    
    public void setNamaSidang(String namaSidang) {
        this.namaSidang = namaSidang;
    }
    
    public String getNoRujukan() {
        return noRujukan;
    }
    
    public void setNoRujukan(String noRujukan) {
        this.noRujukan = noRujukan;
    }
    
    public String getTarikhRujukan() {
        return tarikhRujukan;
    }
    
    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }
    
    public Pengguna getPengguna() {
        return pengguna;
    }
    
    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
    
    public SimpleDateFormat getSdf() {
        return sdf;
    }
    
    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }
    
    public PermohonanRujukanLuarPeranan getPermohonanRujukanLuarPeranan() {
        return permohonanRujukanLuarPeranan;
    }
    
    public void setPermohonanRujukanLuarPeranan(PermohonanRujukanLuarPeranan permohonanRujukanLuarPeranan) {
        this.permohonanRujukanLuarPeranan = permohonanRujukanLuarPeranan;
    }
    
    public List<PermohonanRujukanLuarPeranan> getSenaraipermohonanRujukanLuarPeranan() {
        return senaraipermohonanRujukanLuarPeranan;
    }
    
    public void setSenaraipermohonanRujukanLuarPeranan(List<PermohonanRujukanLuarPeranan> senaraipermohonanRujukanLuarPeranan) {
        this.senaraipermohonanRujukanLuarPeranan = senaraipermohonanRujukanLuarPeranan;
    }
    
    public String getIdPermohonan() {
        return idPermohonan;
    }
    
    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getKategoriMahkamah() {
        return kategoriMahkamah;
    }
    
    public void setKategoriMahkamah(String kategoriMahkamah) {
        this.kategoriMahkamah = kategoriMahkamah;
    }
    
    public String getRecordCount() {
        return recordCount;
    }
    
    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }
    
    public String getStageId() {
        return stageId;
    }
    
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    
    public List<PermohonanRujukanLuar> getSenaraiStatusDakwa() {
        return senaraiStatusDakwa;
    }
    
    public void setSenaraiStatusDakwa(List<PermohonanRujukanLuar> senaraiStatusDakwa) {
        this.senaraiStatusDakwa = senaraiStatusDakwa;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getKod() {
        return kod;
    }
    
    public void setKod(String kod) {
        this.kod = kod;
    }
    
    public String getKeputusanMahkamah() {
        return keputusanMahkamah;
    }
    
    public void setKeputusanMahkamah(String keputusanMahkamah) {
        this.keputusanMahkamah = keputusanMahkamah;
    }
    
    public List<Kehadiran> getSenaraiPerbicaraan() {
        return senaraiPerbicaraan;
    }
    
    public void setSenaraiPerbicaraan(List<Kehadiran> senaraiPerbicaraan) {
        this.senaraiPerbicaraan = senaraiPerbicaraan;
    }
    
    public String getJam() {
        return jam;
    }
    
    public void setJam(String jam) {
        this.jam = jam;
    }
    
    public String getMinit() {
        return minit;
    }
    
    public void setMinit(String minit) {
        this.minit = minit;
    }
    
    public String getAmpm() {
        return ampm;
    }
    
    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }
    
    public String getCatatanPerbicaraan() {
        return catatanPerbicaraan;
    }
    
    public void setCatatanPerbicaraan(String catatanPerbicaraan) {
        this.catatanPerbicaraan = catatanPerbicaraan;
    }
    
    public String getTarikhPerbicaraan() {
        return tarikhPerbicaraan;
    }
    
    public void setTarikhPerbicaraan(String tarikhPerbicaraan) {
        this.tarikhPerbicaraan = tarikhPerbicaraan;
    }
    
    public Kehadiran getKehadiran() {
        return kehadiran;
    }
    
    public void setKehadiran(Kehadiran kehadiran) {
        this.kehadiran = kehadiran;
    }
    
    public String getRecordCountPerbicaraan() {
        return recordCountPerbicaraan;
    }
    
    public void setRecordCountPerbicaraan(String recordCountPerbicaraan) {
        this.recordCountPerbicaraan = recordCountPerbicaraan;
    }
    
    public Boolean getAddInfoByPUU() {
        return addInfoByPUU;
    }
    
    public void setAddInfoByPUU(Boolean addInfoByPUU) {
        this.addInfoByPUU = addInfoByPUU;
    }
}
