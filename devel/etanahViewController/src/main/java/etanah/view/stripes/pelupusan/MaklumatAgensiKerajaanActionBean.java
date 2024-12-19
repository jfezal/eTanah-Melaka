/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.Configuration;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodJenisPengenalanDAO;
import etanah.dao.KodKategoriAgensiDAO;
import etanah.dao.KodKementerianDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PihakDAO;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.KodJenisPengenalan;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.PelupusanService;
import etanah.service.common.CawanganService;
import etanah.view.ListUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import net.sourceforge.stripes.action.ForwardResolution;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Admin
 */
@UrlBinding("/pelupusan/maklumat_agensi_kerajaan")
public class MaklumatAgensiKerajaanActionBean extends AbleActionBean{
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodNegeriDAO kodNegeriDAO ;
    @Inject
    KodKementerianDAO kodKementerianDAO ;
    @Inject
    KodKategoriAgensiDAO kodKategoriAgensiDAO ;
    @Inject
    KodAgensiDAO kodAgensiDAO ;
    @Inject
    PihakDAO pihakDAO ;
    @Inject
    KodJenisPengenalanDAO kodJenisPengenalanDAO;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan ;
    private List<KodAgensi> listKodAgensi;
    private List<Pemohon> listPemohon ;
    private Pihak pihak ;
    private String idPermohonan ;
    private Pengguna pguna ;
    private String idPihak ;
    private String kodAgensiNama ;
    private String kementerian ;
    private String negeri ;
    private String katgAgensi ;
    private int sizeKod ;
    private int sizePemohon ;
    private Pemohon pemohon ;
    private static final Logger LOG = Logger.getLogger(MaklumatAgensiKerajaanActionBean.class);
    
    
    @DefaultHandler
    public Resolution showForm() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/priz/maklumat_agensi_kerajaan.jsp").addParameter("tab", "true");
    }

    public Resolution viewPemohon() {
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("pelupusan/priz/maklumat_agensi_kerajaan.jsp").addParameter("tab", "true");
    }
    
    
    public Resolution showTambahPemohon() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        LOG.info("PERMOHONAN :" + permohonan.getKodUrusan().getKod());
        return new JSP("pelupusan/priz/tambah_agensi_kerajaan.jsp").addParameter("popup", "true");
    }
    
    public Resolution showEditPemohon() {
        String idPihak = getContext().getRequest().getParameter("idPihak");
        String idPermohonan = getContext().getRequest().getParameter("idPermohonan");
        pihak = pihakDAO.findById(Long.valueOf(idPihak));
        pemohon = pelupusanService.findPemohonByPermohonanPihak(permohonan, pihak);
        if(pihak != null){
            if(pihak.getNegeri() != null)
                negeri = pihak.getNegeri().getKod() ;
        }
        getContext().getRequest().setAttribute("flag", Boolean.TRUE);
        return new JSP("pelupusan/priz/kemaskini_agensi_kerajaan.jsp").addParameter("popup", "true");
    }
  
     @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = (String) getContext().getRequest().getSession().getAttribute("idPihak");
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        if(permohonan != null){
            listPemohon = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
        }
     }
     
     public Resolution refreshMaklumatPemohon() {
        rehydrate();
        return new RedirectResolution(MaklumatAgensiKerajaanActionBean.class, "locate");
    }
     
      public Resolution deletePemohon() {
        String idPemohon = getContext().getRequest().getParameter("idPemohon");
        if (idPemohon != null) {
            Pemohon pmohon = pelupusanService.findByIdPemohon(idPemohon);
            if (pmohon != null) {
                pihak = pmohon.getPihak();
//                pihakDAO.delete(pmohon.getPihak());
                pelupusanService.deletePemohon(pmohon);
                pihakDAO.delete(pihak);
                listPemohon = pelupusanService.findPemohonByIdPermohonan(idPermohonan);
            }
        }
        rehydrate();
        return showForm();
    }
     
     public Resolution cariKodAgensi() {
        listKodAgensi = new ArrayList<KodAgensi>();
        LOG.debug("negeri: " + negeri);
            if(negeri != null && !"".equals(negeri.trim())){
            String kod = "%";
            listKodAgensi = pelupusanService.searchKodAgensiForAgensiKerajaan(kod,kodAgensiNama, negeri, katgAgensi) ;
            System.out.println("listKodSyaratNyata.size : " + listKodAgensi.size());
            sizeKod = listKodAgensi.size();
            LOG.debug("size agensi list: " + sizeKod);
            
            getContext().getRequest().setAttribute("agensi", Boolean.TRUE);
            }else {
                addSimpleError("Sila masukkan negeri");
            }
                return new JSP("pelupusan/priz/tambah_agensi_kerajaan.jsp").addParameter("popup", "true");
        
           
    }
     
     public Resolution simpanAgensi() throws ParseException {
       Date dummyDate = new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2020");
       pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       String item = getContext().getRequest().getParameter("item");
       String[] listAgensi = item.split(",") ;
       LOG.info("Size :" + listAgensi.length);
       InfoAudit ia = new InfoAudit() ;
       ia.setDimasukOleh(pguna);
       ia.setTarikhMasuk(new java.util.Date());
       for(int i = 0 ; i < listAgensi.length ; i++){
  
           if(!listAgensi[i].equals("T")){
               Pihak pihakTemp = new Pihak();
               pihakTemp.setInfoAudit(ia);
               pemohon = new Pemohon();
               pemohon.setPermohonan(permohonan);
               pemohon.setPihak(pihakTemp);
               pemohon.setInfoAudit(ia);
               pemohon.setCawangan(permohonan.getCawangan());
               LOG.info(listAgensi[i]);
               KodAgensi agen = kodAgensiDAO.findById(listAgensi[i]) ;
               if(pemohon != null){
                   pihakTemp.setNama(agen.getNama());
                   pihakTemp.setAlamat1(agen.getAlamat1());
                   pihakTemp.setAlamat2(agen.getAlamat2());
                   pihakTemp.setAlamat3(agen.getAlamat3());
                   pihakTemp.setAlamat4(agen.getAlamat4());
                   pihakTemp.setNegeri(agen.getKodNegeri());
                   pihakTemp.setNoPengenalan(agen.getKod());
//                   KodJenisPengenalan kodP = new KodJenisPengenalan();
                   pihakTemp.setJenisPengenalan(kodJenisPengenalanDAO.findById("U"));
                   
                   //untuk urusan PRIZ
                   if(permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRIZ")){
                   pihakTemp.setEmail("xxx@xxx.com");
                   pihakTemp.setNoTelefon1("0123456789");
                   pihakTemp.setTarikhLahir(dummyDate);
                   }                  
                   
                   if(!permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRIZ")){
                   pihakTemp.setEmail(agen.getEmel());
                   }
                   
               }
               pelupusanService.simpanPihakPemohon(pihakTemp, pemohon);
                rehydrate();
               
           }
       }
       return refreshMaklumatPemohon();
    }
     public Resolution kemaskiniAgensi(){
        pguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = pelupusanService.findPermohonanByIdPermohonanWithoutKodUrusan(idPermohonan);
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDiKemaskiniOleh(pguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());

        Pihak pihakTemp = pihakDAO.findById(pihak.getIdPihak());
        if(pihakTemp != null){
            pihakTemp.setAlamat1(pihak.getAlamat1());
            pihakTemp.setAlamat2(pihak.getAlamat2());
            pihakTemp.setAlamat3(pihak.getAlamat3());
            pihakTemp.setAlamat4(pihak.getAlamat4());
            pihakTemp.setPoskod(pihak.getPoskod());
            pihakTemp.setNegeri(kodNegeriDAO.findById(negeri));
        }
        pelupusanService.saveOrUpdate(pihakTemp);
        Pemohon pmohon = pelupusanService.findPemohonByPermohonanPihak(permohonan, pihakTemp);
        infoAudit = pmohon.getInfoAudit();
        infoAudit.setDiKemaskiniOleh(pguna);
        infoAudit.setTarikhKemaskini(new java.util.Date());
        pmohon.setInfoAudit(infoAudit);
        pelupusanService.saveOrUpdate(pmohon);
        
        KodAgensi agensi = pelupusanService.getSenaraikodAgensiList(pihakTemp.getNoPengenalan());
        if(agensi != null){
            infoAudit = agensi.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            agensi.setInfoAudit(infoAudit);
            agensi.setAlamat1(pihak.getAlamat1());
            agensi.setAlamat2(pihak.getAlamat2());
            agensi.setAlamat3(pihak.getAlamat3());
            agensi.setAlamat4(pihak.getAlamat4());
            agensi.setPoskod(pihak.getPoskod());
            agensi.setNegeri(kodNegeriDAO.findById(negeri));
            kodAgensiDAO.saveOrUpdate(agensi);
        }
        rehydrate();
        getContext().getRequest().setAttribute("flag", Boolean.FALSE);
        return new JSP("pelupusan/priz/kemaskini_agensi_kerajaan.jsp").addParameter("tab", "true");
}
     
     

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

    public String getKatgAgensi() {
        return katgAgensi;
    }

    public void setKatgAgensi(String katgAgensi) {
        this.katgAgensi = katgAgensi;
    }

    public String getKementerian() {
        return kementerian;
    }

    public void setKementerian(String kementerian) {
        this.kementerian = kementerian;
    }

    public String getKodAgensiNama() {
        return kodAgensiNama;
    }

    public void setKodAgensiNama(String kodAgensiNama) {
        this.kodAgensiNama = kodAgensiNama;
    }

    public List<KodAgensi> getListKodAgensi() {
        return listKodAgensi;
    }

    public void setListKodAgensi(List<KodAgensi> listKodAgensi) {
        this.listKodAgensi = listKodAgensi;
    }

    public List<Pemohon> getListPemohon() {
        return listPemohon;
    }

    public void setListPemohon(List<Pemohon> listPemohon) {
        this.listPemohon = listPemohon;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
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

    public Pengguna getPguna() {
        return pguna;
    }

    public void setPguna(Pengguna pguna) {
        this.pguna = pguna;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public int getSizeKod() {
        return sizeKod;
    }

    public void setSizeKod(int sizeKod) {
        this.sizeKod = sizeKod;
    }

    public int getSizePemohon() {
        return sizePemohon;
    }

    public void setSizePemohon(int sizePemohon) {
        this.sizePemohon = sizePemohon;
    }
     
     
}
