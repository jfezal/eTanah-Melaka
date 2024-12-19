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
import etanah.dao.KodHakmilikDAO;
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
import etanah.model.KodUOM;
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
 * @author Rohan
 */
@UrlBinding("/pelupusan/surat_kelulusan_mlk")
public class SuratKelulusanMlkActionBean extends AbleActionBean {

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
    @Inject
    KodHakmilikDAO kodHakmilikDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private KodKategoriTanah kodKategoriTanah;
    private KodHakmilik  kodHakmilik;
    private KodUOM kodUnitLuas;
    private KodSekatanKepentingan kodSekatan;
    private KodSyaratNyata kodNyata;
//    private BigDecimal luasTerlibat;
//    private BigDecimal luas;
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
    private String kodHmlk;


    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/surat_kelulusan_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})

    public void rehydrate() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);     //attribute
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");     //id_permohonan
        permohonan = permohonanDAO.findById(idPermohonan);

//        String[] tname = {"permohonan"} ;
//        Object[] tvalue = {permohonan} ;
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        System.out.println("idPermohonan : " + idPermohonan);
        if(hakmilikPermohonan != null){
            if(hakmilikPermohonan.getSyaratNyataBaru() != null){
                kod = hakmilikPermohonan.getSyaratNyataBaru().getKod() ;
            }
             if(hakmilikPermohonan.getSekatanKepentinganBaru() != null){
                kodSktn = hakmilikPermohonan.getSekatanKepentinganBaru().getKod() ;
            }

              if(hakmilikPermohonan.getJenisPenggunaanTanah() != null){
                kodKategori = hakmilikPermohonan.getJenisPenggunaanTanah().getKod();
            }
            if(hakmilikPermohonan.getKodHakmilik() != null){
                kodHmlk = hakmilikPermohonan.getKodHakmilik().getKod();
            }
        }
//        luas =  (BigDecimal) getContext().getRequest().getSession().getAttribute("luas");
//        jenisHakmilik =  (String) getContext().getRequest().getSession().getAttribute("jenisHakmilik");
//        nama =  (String) getContext().getRequest().getSession().getAttribute("nama");
//        tempoh =  (Integer) getContext().getRequest().getSession().getAttribute("tempoh");
//        cukai = (BigDecimal) getContext().getRequest().getSession().getAttribute("cukai");
//        syaratNyata = (String) getContext().getRequest().getSession().getAttribute("syaratNyata");
//        sekatKpntgn = (String) getContext().getRequest().getSession().getAttribute("sekatKpntgn");

    }

    public Resolution search() {
         index = getContext().getRequest().getParameter("index");
       return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
    }

      public Resolution showFormCariKodSekatan() {
        index = getContext().getRequest().getParameter("index");
        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
    }

     public Resolution simpan() {
         System.out.println("==---------Inside Simpan--------------==");

         idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan"); 
         hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
         System.out.println("==---------hakmilikPermohonan--------------=="+hakmilikPermohonan);
        if(hakmilikPermohonan != null){

         Pengguna pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//                KodCawangan caw = pengguna.getKodCawangan();
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(hakmilikPermohonan.getInfoAudit().getDimasukOleh()) ;
                ia.setTarikhMasuk(hakmilikPermohonan.getInfoAudit().getTarikhMasuk()) ;
                ia.setDiKemaskiniOleh(pengguna) ;
                ia.setTarikhKemaskini(new java.util.Date());
                hakmilikPermohonan.setInfoAudit(ia);
                if(kodHmlk !=null){
                    KodHakmilik khm =kodHakmilikDAO.findById(kodHmlk);
                   hakmilikPermohonan.setKodHakmilik(khm);
                }

               // hakmilikPermohonan.setKodHakmilik(kodHakmilik);
                  if(kod != null){
                           KodSyaratNyata kodSyarat = kodSyaratNyataDAO.findById(kod) ;
                           hakmilikPermohonan.setSyaratNyataBaru(kodSyarat) ;
                    }
                if(kodSktn != null){
                KodSekatanKepentingan sekatan = kodSekatanKepentinganDAO.findById(kodSktn) ;
                hakmilikPermohonan.setSekatanKepentinganBaru(sekatan) ;
                }
                if(kodKategori != null){
                    KodKategoriTanah k = kodKategoriTanahDAO.findById(kodKategori) ;
                    hakmilikPermohonan.setJenisPenggunaanTanah(k);
                }
                

                addSimpleMessage("Maklumat telah disimpan");
                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        }
         HakmilikPermohonan hp = new HakmilikPermohonan();

        return new JSP("pelupusan/surat_kelulusan_mlk.jsp").addParameter("tab", "true");
     }

      public Resolution cariKodSekatan() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("peng.getKodCawangan().getKod :" + kc);
//        logger.debug("kodSekatan :" + kodSekatan);
        if (kodSekatanKepentingan != null) {
            listKodSekatan = regService.searchKodSekatan(kodSekatanKepentingan, kc, sekatKpntgn2);
            logger.debug("listKodSekatan.size :" + listKodSekatan.size());
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        } else {
            listKodSekatan = regService.searchKodSekatan("%", kc, sekatKpntgn2);
//            addSimpleError("Sila Cari / Pilih Kod Sekatan");
            if (listKodSekatan.size() < 1) {
                addSimpleError("Kod Sekatan Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSekatanKpntngn.jsp").addParameter("popup", "true");
    }

     public Resolution cariKodSyaratNyata() {
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kc = peng.getKodCawangan().getKod();
//        logger.debug("kodSyaratNyata : " + kodSyaratNyata);
        if (kodSyaratNyata != null) {
//            listKodSyaratNyata = regService.searchKodSyaratNyata(kodSyaratNyata, kc, syaratNyata2);
            logger.debug("listKodSyaratNyata.size : " + listKodSyaratNyata.size());
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        } else {
//            listKodSyaratNyata = regService.searchKodSyaratNyata("%", kc, syaratNyata2);
//            addSimpleError("Sila Cari / Pilih Kod Syarat Nyata");
            if (listKodSyaratNyata.size() < 1) {
                addSimpleError("Kod Syarat Nyata Tidak Sah");
            }
        }
        return new JSP("pelupusan/searchSyaratNyata.jsp").addParameter("popup", "true");
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

    public String getHasil() {
        return hasil;
    }

    public void setHasil(String hasil) {
        this.hasil = hasil;
    }

    public KodSyaratNyata getKodNyata() {
        return kodNyata;
    }

    public void setKodNyata(KodSyaratNyata kodNyata) {
        this.kodNyata = kodNyata;
    }

    public KodSekatanKepentinganDAO getKodSekatanKepentinganDAO() {
        return kodSekatanKepentinganDAO;
    }

    public void setKodSekatanKepentinganDAO(KodSekatanKepentinganDAO kodSekatanKepentinganDAO) {
        this.kodSekatanKepentinganDAO = kodSekatanKepentinganDAO;
    }

    public KodUOM getKodUnitLuas() {
        return kodUnitLuas;
    }

    public void setKodUnitLuas(KodUOM kodUnitLuas) {
        this.kodUnitLuas = kodUnitLuas;
    }

    public String getKodHmlk() {
        return kodHmlk;
    }

    public void setKodHmlk(String kodHmlk) {
        this.kodHmlk = kodHmlk;
    }



}
