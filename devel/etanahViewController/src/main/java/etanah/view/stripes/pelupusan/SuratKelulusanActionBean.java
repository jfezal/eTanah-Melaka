/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodKategoriTanahDAO;
import etanah.dao.KodSekatanKepentinganDAO;
import etanah.dao.KodSyaratNyataDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodHakmilik;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodKategoriTanah;
import etanah.model.KodSyaratNyata;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.ListUtil;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author nurul.izza
 * modified by afham
 */
@UrlBinding("/pelupusan/surat_kelulusan")
public class SuratKelulusanActionBean extends AbleActionBean {

     private static final Logger logger = Logger.getLogger(SuratKelulusanActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO ;
    @Inject
    RegService regService;
    @Inject
    KodSyaratNyataDAO kodSyaratNyataDAO ;
    @Inject
    KodSekatanKepentinganDAO kodSekatanKepentinganDAO ;
    @Inject
    KodKategoriTanahDAO kodKategoriTanahDAO ;
    private Permohonan permohonan;
    private String idPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodKategoriTanah kodKategoriTanah;
    private KodHakmilik  kodHakmilik;
    private KodSekatanKepentingan kodSekatan;
    private KodSyaratNyata kodNyata;
    private List<KodSyaratNyata> listKodSyaratNyata;
    private List<KodSekatanKepentingan> listKodSekatan;
    private String jenisHakmilik;
    private Integer tempoh;
    private String hasil;
    private Integer tempohPegangan;
    private BigDecimal cukaiBaru;
    private BigDecimal cukai;
    private String syaratNyata;
    private String kod ;
    private String syarat;
    private String sekatKpntgn;
    private String sekatKpntgn2;
    private String kodSekatanKepentingan;
    private String kodSktn ;
    private String kodSyaratNyata;
    private String syaratNyata2;
    private String radio_;
    private String kodKategori ;
    private String index;


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/surat_kelulusan.jsp").addParameter("tab", "true");
    }
    
    public Resolution showForm1() {
        return new JSP("pelupusan/surat_kelulusan_pbmt.jsp").addParameter("tab", "true");
    }
    
     public Resolution viewForm() {
        return new JSP("pelupusan/surat_kelulusan_view.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})

    public void rehydrate() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);     //attribute
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");     //id_permohonan
        permohonan = permohonanDAO.findById(idPermohonan);

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
     if(hakmilikPermohonan!=null){
        
        if (hakmilikPermohonan.getSyaratNyataBaru() != null) {
            String   kod1=(hakmilikPermohonan.getSyaratNyataBaru().getSyarat());
            kod = hakmilikPermohonan.getSyaratNyataBaru().getKod();
            logger.info("----kod1---------"+kod1);
             syaratNyata = ""+kod+" -- "+kod1+"";
        logger.info("----syaratNyata---------"+syaratNyata);
        }else{
            kod ="";
        }
     
        if (hakmilikPermohonan.getSekatanKepentinganBaru() != null) {             
            kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod();
            logger.info("----kodSktn1---------"+kodSktn);
              String   kod2=(hakmilikPermohonan.getSekatanKepentinganBaru().getSekatan());
               sekatKpntgn =  ""+kodSktn+" -- "+kod2+"";
        logger.info("----sekatKpntgn---------"+sekatKpntgn);
        }
        else{
            kodSktn ="";
        }
        if(hakmilikPermohonan.getKategoriTanahBaru()!=null){             
            kodKategori =hakmilikPermohonan.getKategoriTanahBaru().getKod();
            logger.info("----kodKategori1---------"+kodKategori);
        }
       
       
       }
   
    }

     public Resolution simpan() {
  
         hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
           permohonan = permohonanDAO.findById(idPermohonan);
        if(hakmilikPermohonan != null){

         Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(hakmilikPermohonan.getInfoAudit().getDimasukOleh()) ;
                ia.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk()) ;
                ia.setDiKemaskiniOleh(pengguna) ;
                ia.setTarikhKemaskini(new java.util.Date());         
                hakmilikPermohonan.setInfoAudit(ia);
                ia.setDimasukOleh(hakmilikPermohonan.getInfoAudit().getDimasukOleh());
                hakmilikPermohonan.setInfoAudit(ia);
                
                logger.info("------kod---------"+kod);
                logger.info("------kodSktn---------"+kodSktn);
                logger.info("------kodKategori---------"+kodKategori);

       if (kod != null && !kod.equals("")) {
            KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod);
            hakmilikPermohonan.setSyaratNyataBaru(kodSyarat);
        }
        if (kodSktn != null && !kodSktn.equals("")) {
            KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn);
            hakmilikPermohonan.setSekatanKepentinganBaru(sekatan);
        }
        if (kodKategori != null && !kodKategori.equals("")) {
            KodKategoriTanah k = kodKategoriTanahDAO.findById(kodKategori);
            hakmilikPermohonan.setKategoriTanahBaru(k);
        }

                addSimpleMessage("Maklumat telah disimpan");
                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        }

        if (permohonan.getKodUrusan().getKod().equals("PBMT")) {
            return new JSP("pelupusan/surat_kelulusan_pbmt.jsp").addParameter("tab", "true");
        }else{
            return new JSP("pelupusan/surat_kelulusan.jsp").addParameter("tab", "true");
        }
        
     }


    public BigDecimal getCukai() {
        return cukai;
    }

    public void setCukai(BigDecimal cukai) {
        this.cukai = cukai;
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

    public Integer getTempoh() {
        return tempoh;
    }

    public void setTempoh(Integer tempoh) {
        this.tempoh = tempoh;
    }

    public BigDecimal getCukaiBaru() {
        return cukaiBaru;
    }

    public void setCukaiBaru(BigDecimal cukaiBaru) {
        this.cukaiBaru = cukaiBaru;
    }


    public String getSyarat() {
        return syarat;
    }

    public void setSyarat(String syarat) {
        this.syarat = syarat;
    }

    public String getSyaratNyata() {
        return syaratNyata;
    }

    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }


    public Integer getTempohPegangan() {
        return tempohPegangan;
    }

    public void setTempohPegangan(Integer tempohPegangan) {
        this.tempohPegangan = tempohPegangan;
    }


    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

//    public BigDecimal getLuasTerlibat() {
//        return luasTerlibat;
//    }
//
//    public void setLuasTerlibat(BigDecimal luasTerlibat) {
//        this.luasTerlibat = luasTerlibat;
//    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    public void setJenisHakmilik(String jenisHakmilik) {
        this.jenisHakmilik = jenisHakmilik;
    }

    public KodHakmilik getKodHakmilik() {
        return kodHakmilik;
    }

    public void setKodHakmilik(KodHakmilik kodHakmilik) {
        this.kodHakmilik = kodHakmilik;
    }

//    public BigDecimal getLuas() {
//        return luas;
//    }
//
//    public void setLuas(BigDecimal luas) {
//        this.luas = luas;
//    }

//    public String getNama() {
//        return nama;
//    }
//
//    public void setNama(String nama) {
//        this.nama = nama;
//    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public String getSekatKpntgn() {
        return sekatKpntgn;
    }

    public void setSekatKpntgn(String sekatKpntgn) {
        this.sekatKpntgn = sekatKpntgn;
    }

    public KodKategoriTanah getKodKategoriTanah() {
        return kodKategoriTanah;
    }

    public void setKodKategoriTanah(KodKategoriTanah kodKategoriTanah) {
        this.kodKategoriTanah = kodKategoriTanah;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public List<KodSyaratNyata> getListKodSyaratNyata() {
        return listKodSyaratNyata;
    }

    public void setListKodSyaratNyata(List<KodSyaratNyata> listKodSyaratNyata) {
        this.listKodSyaratNyata = listKodSyaratNyata;
    }

    public String getKodSyaratNyata() {
        return kodSyaratNyata;
    }

    public void setKodSyaratNyata(String kodSyaratNyata) {
        this.kodSyaratNyata = kodSyaratNyata;
    }

    public String getSyaratNyata2() {
        return syaratNyata2;
    }

    public void setSyaratNyata2(String syaratNyata2) {
        this.syaratNyata2 = syaratNyata2;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRadio_() {
        return radio_;
    }

    public void setRadio_(String radio_) {
        this.radio_ = radio_;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKodSekatanKepentingan() {
        return kodSekatanKepentingan;
    }

    public void setKodSekatanKepentingan(String kodSekatanKepentingan) {
        this.kodSekatanKepentingan = kodSekatanKepentingan;
    }

    public String getSekatKpntgn2() {
        return sekatKpntgn2;
    }

    public void setSekatKpntgn2(String sekatKpntgn2) {
        this.sekatKpntgn2 = sekatKpntgn2;
    }

    public List<KodSekatanKepentingan> getListKodSekatan() {
        return listKodSekatan;
    }

    public void setListKodSekatan(List<KodSekatanKepentingan> listKodSekatan) {
        this.listKodSekatan = listKodSekatan;
    }

    public String getKodSktn() {
        return kodSktn;
    }

    public void setKodSktn(String kodSktn) {
        this.kodSktn = kodSktn;
    }

    public String getKodKategori() {
        return kodKategori;
    }

    public void setKodKategori(String kodKategori) {
        this.kodKategori = kodKategori;
    }

    public KodSyaratNyata getKodNyata() {
        return kodNyata;
    }

    public void setKodNyata(KodSyaratNyata kodNyata) {
        this.kodNyata = kodNyata;
    }

    public KodSekatanKepentingan getKodSekatan() {
        return kodSekatan;
    }

    public void setKodSekatan(KodSekatanKepentingan kodSekatan) {
        this.kodSekatan = kodSekatan;
    }




}
