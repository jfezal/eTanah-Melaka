/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import com.opensymphony.module.sitemesh.html.util.CharArray;
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
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
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
import etanah.model.Permit;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.PelupusanService;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.text.ParseException;
import java.util.Date;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.SuratRujukanLuar;
import etanah.report.ReportUtil;
import etanah.service.PelupusanPtService;
import etanah.service.SemakDokumenService;
import etanah.service.common.DokumenService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.KandunganFolderService;
import etanah.view.ListUtil;
import java.util.Calendar;
import java.util.Vector;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author shazwan
 */
@UrlBinding("/pelupusan/keputusan_jkmineral")
public class KeputusanJkMineralActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodCawanganDAO kodCawanganDAO ;    
    @Inject
    KodRujukanDAO kodRujukanDAO ;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO ;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PelupusanPtService pelPtService;
    private String noSidang;
    private String idPermohonan;
    private String tarikhTerima;
    private String tarikhTimbang;
    private String kemampuan;
    private String syortolaklulus;
    private String ulasan;
    private String termaDanSyarat;
    private boolean viewOnly;
    private Pengguna pguna = new Pengguna();
    private Permohonan permohonan = new Permohonan();
    private PermohonanRujukanLuar mohonRujLuar = new PermohonanRujukanLuar();
    private List<PermohonanLaporanUlasan> senaraiLaporanUlas = new Vector();
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(LesenTerdahuluActionBean.class);

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<PermohonanRujukanLuar> listMohonRujLuar= new ArrayList<PermohonanRujukanLuar>();
        listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
        for(PermohonanRujukanLuar prl : listMohonRujLuar){
            if(prl.getAgensi()==null){
                mohonRujLuar = prl;
                settingRujLuar(prl);
                break;
            
                              
            }
        }
        senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");
                
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
        
        
    }
    public Resolution viewForm() throws ParseException {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<PermohonanRujukanLuar> listMohonRujLuar= new ArrayList<PermohonanRujukanLuar>();
        listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
        for(PermohonanRujukanLuar prl : listMohonRujLuar){
            if(prl.getAgensi()==null){
                mohonRujLuar = prl;
                settingRujLuar(prl);
                break;
            }
        }
        senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");        
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
    }
    public void settingRujLuar(PermohonanRujukanLuar prl) throws ParseException{
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        this.setTarikhTerima(prl.getTarikhTerima()!=null?sdf.format(prl.getTarikhTerima()):new String());    
        this.setTarikhTimbang(prl.getTarikhSidang()!=null?sdf.format(prl.getTarikhSidang()):new String());
        this.setKemampuan(!StringUtils.isBlank(prl.getKemampuanPemohon())?prl.getKemampuanPemohon():new String());
        this.setSyortolaklulus(prl.getDiSokong()!=null?prl.getDiSokong().toString():new String());
        this.setUlasan(!StringUtils.isBlank(prl.getUlasan())?prl.getUlasan():new String());
        this.setNoSidang(!StringUtils.isBlank(prl.getNoSidang())?prl.getNoSidang():new String());
        //this.setTermaDanSyarat(!StringUtils.isBlank(prl.getCatatan())?prl.getCatatan():new String());
    }
    public boolean validateForm(){
        boolean validateForm = Boolean.FALSE;
        if(StringUtils.isBlank(this.tarikhTerima)){
            validateForm = Boolean.TRUE;
            addSimpleError("Sila nyatakan Tarikh Diterima");
        }
        if(StringUtils.isBlank(this.tarikhTimbang)){
            validateForm = Boolean.TRUE;
            addSimpleError("Sila nyatakan Tarikh Dipertimbangkan");
        }
        if(StringUtils.isBlank(this.kemampuan)){
            validateForm = Boolean.TRUE;
            addSimpleError("Sila nyatakan Kemampuan Pemohon");
        }
        if(StringUtils.isBlank(this.syortolaklulus)){
            validateForm = Boolean.TRUE;
            addSimpleError("Sila isi syor pertimbangan");
        }
        return validateForm;
    }
    
     public Resolution simpan() throws ParseException {
         
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonan = permohonanDAO.findById(idPermohonan);
            List <PermohonanRujukanLuar> listMohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
            listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
             if(!validateForm()){
                mohonRujLuar = new PermohonanRujukanLuar();
                boolean checkDataExist = Boolean.FALSE;
                for(PermohonanRujukanLuar prl:listMohonRujLuar){
                    if(prl.getAgensi()==null){
                        checkDataExist = Boolean.TRUE;
                        mohonRujLuar = prl;
                        break;
                    }
                }
                InfoAudit ia = new InfoAudit();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if(checkDataExist){
                    ia = mohonRujLuar.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pguna);

                }else{
                    mohonRujLuar = new PermohonanRujukanLuar();
                    ia = new InfoAudit();
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDimasukOleh(pguna);
                }
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setTarikhTerima(!StringUtils.isBlank(this.getTarikhTerima())?sdf.parse(this.getTarikhTerima()):mohonRujLuar.getTarikhTerima());
                mohonRujLuar.setTarikhSidang(!StringUtils.isBlank(this.getTarikhTimbang())?sdf.parse(this.getTarikhTimbang()):mohonRujLuar.getTarikhSidang());
                mohonRujLuar.setKemampuanPemohon(!StringUtils.isBlank(this.getKemampuan())?this.getKemampuan():mohonRujLuar.getKemampuanPemohon());
                if(!StringUtils.isBlank(this.getSyortolaklulus())){
                    char[] charArraySyorTolak = this.getSyortolaklulus().toCharArray();
                    mohonRujLuar.setDiSokong(charArraySyorTolak[0]);
                }
                mohonRujLuar.setUlasan(!StringUtils.isBlank(this.getUlasan())?this.getUlasan():mohonRujLuar.getUlasan());
                //mohonRujLuar.setCatatan(!StringUtils.isBlank(this.getTermaDanSyarat())?this.getTermaDanSyarat():mohonRujLuar.getCatatan());
                mohonRujLuar.setKodRujukan(kodRujukanDAO.findById("KP"));
                mohonRujLuar.setPermohonan(permohonan);
                mohonRujLuar.setNoSidang(noSidang);
                pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
                addSimpleMessage("Maklumat Berjaya Disimpan");
            }
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        if(index > 0){
        simpanKandungan();
        }
        listMohonRujLuar= new ArrayList<PermohonanRujukanLuar>();
        listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
        for(PermohonanRujukanLuar prl : listMohonRujLuar){
            if(prl.getAgensi()==null){
                mohonRujLuar = prl;
                settingRujLuar(prl);
                break;
            }
        }
        senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");
        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
    }
     public Resolution simpanTambah() throws ParseException {
         
            idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
            pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            permohonan = permohonanDAO.findById(idPermohonan);
            List <PermohonanRujukanLuar> listMohonRujLuar = new ArrayList<PermohonanRujukanLuar>();
            listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
             if(!validateForm()){
                mohonRujLuar = new PermohonanRujukanLuar();
                boolean checkDataExist = Boolean.FALSE;
                for(PermohonanRujukanLuar prl:listMohonRujLuar){
                    if(prl.getAgensi()==null){
                        checkDataExist = Boolean.TRUE;
                        mohonRujLuar = prl;
                        break;
                    }
                }
                InfoAudit ia = new InfoAudit();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                if(checkDataExist){
                    ia = mohonRujLuar.getInfoAudit();
                    ia.setTarikhKemaskini(new java.util.Date());
                    ia.setDiKemaskiniOleh(pguna);

                }else{
                    mohonRujLuar = new PermohonanRujukanLuar();
                    ia = new InfoAudit();
                    ia.setTarikhMasuk(new java.util.Date());
                    ia.setDimasukOleh(pguna);
                }
                mohonRujLuar.setInfoAudit(ia);
                mohonRujLuar.setCawangan(permohonan.getCawangan());
                mohonRujLuar.setTarikhTerima(!StringUtils.isBlank(this.getTarikhTerima())?sdf.parse(this.getTarikhTerima()):mohonRujLuar.getTarikhTerima());
                mohonRujLuar.setTarikhSidang(!StringUtils.isBlank(this.getTarikhTimbang())?sdf.parse(this.getTarikhTimbang()):mohonRujLuar.getTarikhSidang());
                mohonRujLuar.setKemampuanPemohon(!StringUtils.isBlank(this.getKemampuan())?this.getKemampuan():mohonRujLuar.getKemampuanPemohon());
                if(!StringUtils.isBlank(this.getSyortolaklulus())){
                    char[] charArraySyorTolak = this.getSyortolaklulus().toCharArray();
                    mohonRujLuar.setDiSokong(charArraySyorTolak[0]);
                }
                mohonRujLuar.setUlasan(!StringUtils.isBlank(this.getUlasan())?this.getUlasan():mohonRujLuar.getUlasan());
                //mohonRujLuar.setCatatan(!StringUtils.isBlank(this.getTermaDanSyarat())?this.getTermaDanSyarat():mohonRujLuar.getCatatan());
                mohonRujLuar.setKodRujukan(kodRujukanDAO.findById("KP"));
                mohonRujLuar.setPermohonan(permohonan);
                pelupusanService.simpanPermohonanRujLuar(mohonRujLuar);
            }
        tambahRow();
        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
    } 
    public Resolution deleteRow() throws ParseException {


        System.out.println("-----------deleteKandungan---------");
        String idKand = getContext().getRequest().getParameter("idKandungan");
        System.out.println("-----------deleteSingle---------" + idKand);
        if (idKand != null) {
            PermohonanLaporanUlasan plu = new PermohonanLaporanUlasan();
            plu = permohonanLaporanUlasanDAO.findById(Long.parseLong(idKand));
            if (plu != null) {

                try {
                    pelPtService.deletePermohonanLaporanUlasan(plu);
                } catch (Exception e) {
                }
            }
        }
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        permohonan = permohonanDAO.findById(idPermohonan);
        List<PermohonanRujukanLuar> listMohonRujLuar= new ArrayList<PermohonanRujukanLuar>();
        listMohonRujLuar = pelupusanService.findByIdMohonRujukLuarNAgensiNULL(idPermohonan);
        for(PermohonanRujukanLuar prl : listMohonRujLuar){
            if(prl.getAgensi()==null){
                mohonRujLuar = prl;
                settingRujLuar(prl);
                break;
            }
        }
        senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");
       
        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
    } 
    public void tambahRow() {

        PermohonanLaporanUlasan plu = new PermohonanLaporanUlasan();
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        switch (index) {
             
              case 10: // FOR TERMA & SYARAT
                  plu = new PermohonanLaporanUlasan();
                  plu.setJenisUlasan("Syarat JKM");
                  senaraiLaporanUlas.add(plu);
                  break;   
            default:
        }
        System.out.println(index);
        
    }
    
    public void simpanKandungan() throws ParseException {

        
        int index = 0;
        index = Integer.parseInt(getContext().getRequest().getParameter("index"));
        String kand = getContext().getRequest().getParameter("kandungan");
        String listSize = getContext().getRequest().getParameter("listSize");
        
        List<PermohonanLaporanUlasan> senaraiLaporanUlasTemp = pelupusanService.findUlasan(idPermohonan, "Syarat JKM"); 
        if(!StringUtils.isEmpty(listSize)){
            if(Integer.parseInt(listSize)>senaraiLaporanUlasTemp.size()){
                switch (index) {            
                  case 10: // FOR TERMA & SYARAT
                      updateKandungan(10, kand);
                      break;
    //            default:
    //                LOG.info("alamak!! tiada index");
                }
            }
        }
//        LOG.info("CHECKING:..... index :" + index + " kand :" + kand);
        
//        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        permohonan = permohonanDAO.findById(idPermohonan);
//        List<PermohonanRujukanLuar> listMohonRujLuar= new ArrayList<PermohonanRujukanLuar>();
//        listMohonRujLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan);
//        for(PermohonanRujukanLuar prl : listMohonRujLuar){
//            if(prl.getAgensi()==null){
//                mohonRujLuar = prl;
//                settingRujLuar(prl);
//                break;
//            }
//        }
//        senaraiLaporanUlas = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");
        
//        addSimpleMessage("Maklumat Berjaya Disimpan");
//        return new JSP("pelupusan/carigali/keputusan_jkmineral.jsp").addParameter("tab", "true");
    }
    
    public void updateKandungan(int i, String kand) {


        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        
        permohonan = permohonanDAO.findById(idPermohonan);
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();
        KodCawangan cawangan = new KodCawangan();
        cawangan = kodCawanganDAO.findById(peng.getKodCawangan().getKod());

        
        List<PermohonanLaporanUlasan> plk = pelupusanService.findUlasan(idPermohonan, "Syarat JKM");

        PermohonanLaporanUlasan plu = new PermohonanLaporanUlasan();
        infoAudit.setDimasukOleh(peng);
        infoAudit.setTarikhMasuk(new java.util.Date());
        plu.setPermohonan(permohonan);
        plu.setInfoAudit(infoAudit);        
        plu.setJenisUlasan("Syarat JKM");
        plu.setUlasan(kand);
        plu.setInfoAudit(infoAudit);
        plu.setCawangan(cawangan);
        pelPtService.simpanPermohonanLaporanUlasan(plu);

    }
    
    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanRujukanLuar getMohonRujLuar() {
        return mohonRujLuar;
    }

    public void setMohonRujLuar(PermohonanRujukanLuar mohonRujLuar) {
        this.mohonRujLuar = mohonRujLuar;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public String getKemampuan() {
        return kemampuan;
    }

    public void setKemampuan(String kemampuan) {
        this.kemampuan = kemampuan;
    }

    public String getSyortolaklulus() {
        return syortolaklulus;
    }

    public void setSyortolaklulus(String syortolaklulus) {
        this.syortolaklulus = syortolaklulus;
    }

    public String getTarikhTerima() {
        return tarikhTerima;
    }

    public void setTarikhTerima(String tarikhTerima) {
        this.tarikhTerima = tarikhTerima;
    }

    public String getTarikhTimbang() {
        return tarikhTimbang;
    }

    public void setTarikhTimbang(String tarikhTimbang) {
        this.tarikhTimbang = tarikhTimbang;
    }

    public String getTermaDanSyarat() {
        return termaDanSyarat;
    }

    public void setTermaDanSyarat(String termaDanSyarat) {
        this.termaDanSyarat = termaDanSyarat;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public List<PermohonanLaporanUlasan> getSenaraiLaporanUlas() {
        return senaraiLaporanUlas;
    }

    public void setSenaraiLaporanUlas(List<PermohonanLaporanUlasan> senaraiLaporanUlas) {
        this.senaraiLaporanUlas = senaraiLaporanUlas;
    }

    public String getNoSidang() {
        return noSidang;
    }

    public void setNoSidang(String noSidang) {
        this.noSidang = noSidang;
    }
    
    
}
