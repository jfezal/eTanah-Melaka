/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AduanStrataDAO;
import etanah.dao.KodHartaBersamaDAO;
import etanah.dao.KodJenisPembangunanDAO;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Permohonan;
import etanah.dao.PermohonanDAO;
import etanah.model.KodKegunaanPetak;
import etanah.dao.KodKegunaanPetakDAO;
import etanah.dao.SiasatanPerihalBangunanDAO;
import etanah.dao.SiasatanPerihalKemudahanDAO;
import etanah.model.AduanStrata;
import etanah.model.InfoAudit;
import etanah.model.KodHartaBersama;
import etanah.model.KodJenisPembangunan;
import etanah.model.Pengguna;
import etanah.model.SiasatanPerihalBangunan;
import etanah.model.SiasatanPerihalKemudahan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import org.apache.log4j.Logger;

/**
 *
 * @author Murali
 */
@UrlBinding("/strata/kuatkuasa_bangunan")
public class KemasukanMaklumatBangunanActionBean extends AbleActionBean {

    @Inject
    KodKegunaanPetakDAO kodKegunaanPetakDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    SiasatanPerihalBangunanDAO audanSiasatanBangunanDAO;
    @Inject
    StrataPtService ptService;
    @Inject
    AduanStrataDAO aduanStrataDAO;
    @Inject
    SiasatanPerihalKemudahanDAO aduanSiasatanPKDAO;
    @Inject
    KodHartaBersamaDAO kodHartaBersamaDAO;
    @Inject
    KodJenisPembangunanDAO kodJenisPembangunanDAO;
    @Inject
    SiasatanPerihalBangunanDAO siasatanPerihalBangunanDAO;
    @Inject
    etanah.Configuration conf;
    
    @Inject
    private StrataPtService strService;
    private List<KodKegunaanPetak> kGunaPetakL;
    private KodKegunaanPetak kegunaan;
    private Permohonan permohonan;
    private SiasatanPerihalBangunan audanSiasatanBangunan;
    private static Logger logger = Logger.getLogger(KemasukanMaklumatBangunanActionBean.class);
    private String kodP;
    private AduanStrata aduanStrata;
    private List<SiasatanPerihalKemudahan> aduanSiasatanPK;
    private KodHartaBersama kodHartaBersama;
    private List<KodHartaBersama> senaraikodHartaBersama;
    private String catatan;
    private String catatan1;
    private Integer bBlok;
    private Integer bUnit;
    private KodJenisPembangunan kodJenisPembangunan;
    private List<KodJenisPembangunan> senaraikodJenisPembangunan;
    private List<SiasatanPerihalBangunan> siasatanPerihalBangunan;
    private String kongsiTangga;
    private String idPermohonan = "";



    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public AduanStrata getAduanStrata() {
        return aduanStrata;
    }

    public void setAduanStrata(AduanStrata aduanStrata) {
        this.aduanStrata = aduanStrata;
    }

    public String getKodP() {
        return kodP;
    }

    public void setKodP(String kodP) {
        this.kodP = kodP;
    }

    public KodKegunaanPetak getKegunaan() {
        return kegunaan;
    }

    public void setKegunaan(KodKegunaanPetak kegunaan) {
        this.kegunaan = kegunaan;
    }

    public List<KodKegunaanPetak> getkGunaPetakL() {
        return kGunaPetakL;
    }

    public void setkGunaPetakL(List<KodKegunaanPetak> kGunaPetakL) {
        this.kGunaPetakL = kGunaPetakL;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        KemasukanMaklumatBangunanActionBean.logger = logger;
    }

    public SiasatanPerihalBangunan getAudanSiasatanBangunan() {
        return audanSiasatanBangunan;
    }

    public void setAudanSiasatanBangunan(SiasatanPerihalBangunan audanSiasatanBangunan) {
        this.audanSiasatanBangunan = audanSiasatanBangunan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodHartaBersama getKodHartaBersama() {
        return kodHartaBersama;
    }

    public void setKodHartaBersama(KodHartaBersama kodHartaBersama) {
        this.kodHartaBersama = kodHartaBersama;
    }

    public List<KodHartaBersama> getSenaraikodHartaBersama() {
        return senaraikodHartaBersama;
    }

    public void setSenaraikodHartaBersama(List<KodHartaBersama> senaraikodHartaBersama) {
        this.senaraikodHartaBersama = senaraikodHartaBersama;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public List<SiasatanPerihalKemudahan> getAduanSiasatanPK() {
        return aduanSiasatanPK;
    }

    public void setAduanSiasatanPK(List<SiasatanPerihalKemudahan> aduanSiasatanPK) {
        this.aduanSiasatanPK = aduanSiasatanPK;
    }

    public KodJenisPembangunan getKodJenisPembangunan() {
        return kodJenisPembangunan;
    }

    public void setKodJenisPembangunan(KodJenisPembangunan kodJenisPembangunan) {
        this.kodJenisPembangunan = kodJenisPembangunan;
    }

    public List<KodJenisPembangunan> getSenaraikodJenisPembangunan() {
        return senaraikodJenisPembangunan;
    }

    public void setSenaraikodJenisPembangunan(List<KodJenisPembangunan> senaraikodJenisPembangunan) {
        this.senaraikodJenisPembangunan = senaraikodJenisPembangunan;
    }

    public String getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(String catatan1) {
        this.catatan1 = catatan1;
    }

    public List<SiasatanPerihalBangunan> getSiasatanPerihalBangunan() {
        return siasatanPerihalBangunan;
    }

    public void setSiasatanPerihalBangunan(List<SiasatanPerihalBangunan> siasatanPerihalBangunan) {
        this.siasatanPerihalBangunan = siasatanPerihalBangunan;
    }

    public Integer getbBlok() {
        return bBlok;
    }

    public void setbBlok(Integer bBlok) {
        this.bBlok = bBlok;
    }

    public Integer getbUnit() {
        return bUnit;
    }

    public void setbUnit(Integer bUnit) {
        this.bUnit = bUnit;
    }

    public String getKongsiTangga() {
        return kongsiTangga;
    }

    public void setKongsiTangga(String kongsiTangga) {
        this.kongsiTangga = kongsiTangga;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/kuatkuasa/kemasukan_maklumat_bangunan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            logger.debug("----------permohonan---Rehydrate()-------::");
        }

        aduanStrata = ptService.findAduanStrataIdMohon(idPermohonan);
        logger.debug("----------aduanStrata Rehydrate()----------::" + aduanStrata);

        // from kod_harta_bersama
        senaraikodHartaBersama = new ArrayList<KodHartaBersama>();
        senaraikodHartaBersama = ptService.findHartaBersamaByNama();

        //from aduan_siasat_kemudahan
        aduanSiasatanPK = ptService.findSiasatanKemudahanListByIdPermohonan(idPermohonan);
        logger.debug("----------aduanSiasatanPK----------::" + aduanSiasatanPK);

        //for KodJenisPembangunan
        senaraikodJenisPembangunan = ptService.findkodJenisPembangunanByNama();
        logger.debug("----------senaraikodJenisPembangunan----------::" + senaraikodJenisPembangunan);

        //from aduan_siasat_bngn
        siasatanPerihalBangunan = ptService.findaduanSiasatanBangunanListByIdPermohonan(idPermohonan);
        logger.debug("----------siasatanPerihalBangunan----------::" + siasatanPerihalBangunan);

        //for kongsiTangga, bUnit and bBlok
        if (!siasatanPerihalBangunan.isEmpty()) {
            SiasatanPerihalBangunan siasatanPerihalBangunan1 = new SiasatanPerihalBangunan();
            siasatanPerihalBangunan1 = siasatanPerihalBangunan.get(0);
            bBlok = siasatanPerihalBangunan1.getBilanganBlok();
            bUnit = siasatanPerihalBangunan1.getBilanganUnit();
            kongsiTangga = siasatanPerihalBangunan1.getKongsiTangga();
        }
    }

    public Resolution SimpanBangunan() {

        logger.debug("----------SimpanBangunan Started----------::");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            logger.debug("----------permohonan----------::");
        }

        //Saving in Aduan_Strata

        logger.debug("----------Saving in Aduan_Strata----------::");
        aduanStrata = ptService.findAduanStrataIdMohon(idPermohonan);
        if (aduanStrata != null) {
            logger.debug("-------aduanStrata NOT Null-------::");
            infoAudit = aduanStrata.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
        } else {
            logger.debug("-------aduanStrata IS Null-------::");
            aduanStrata = new AduanStrata();
            infoAudit.setDimasukOleh(pguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
        }
        aduanStrata.setPermohonan(permohonan);
        aduanStrata.setCawangan(permohonan.getCawangan());
        aduanStrata.setInfoAudit(infoAudit);
        ptService.simpanAduanStrata(aduanStrata);

        //saving in Aduan_Siasatan_keamaduan

        logger.debug("----------Saving in Aduan_Siasatan_keamaduan----------::");

        //delete existing records
        logger.info("--------Deleting Records---------:");
        for (int j = 0; j < aduanSiasatanPK.size(); j++) {
            logger.info("--------j---------:" + j);
            SiasatanPerihalKemudahan siasatanPerihalKemudahan = new SiasatanPerihalKemudahan();
            siasatanPerihalKemudahan = aduanSiasatanPK.get(j);
            ptService.deleteSiasatanPerihalKemudahan(siasatanPerihalKemudahan);
        }

        //Saving
        for (int i = 0; i < senaraikodHartaBersama.size(); i++) {
            KodHartaBersama kodHartaBersama = new KodHartaBersama();
            kodHartaBersama = senaraikodHartaBersama.get(i);
            String kod = getContext().getRequest().getParameter(kodHartaBersama.getNama());
            String kodNegeri = conf.getProperty("kodNegeri");

            if (kod != null && !kod.equals("")) {
                SiasatanPerihalKemudahan siasatanPerihalKemudahan = new SiasatanPerihalKemudahan();
                siasatanPerihalKemudahan.setPermohonan(permohonan);
                siasatanPerihalKemudahan.setInfoAudit(infoAudit);
                siasatanPerihalKemudahan.setKodCawangan(permohonan.getCawangan());
                siasatanPerihalKemudahan.setKodHartaBersama(kodHartaBersamaDAO.findById(kod));
                if(kodNegeri.equals("05")){
                if (kod.equals("13")) {
                    siasatanPerihalKemudahan.setCatatan(catatan);
                }
                }else{
                    if (kod.equals("14")) {
                    siasatanPerihalKemudahan.setCatatan(catatan);
                    logger.info("--------siasatanPerihalKemudahan 14--------:");
                }
                }
                ptService.simpanSiasatanPerihalKemudahan(siasatanPerihalKemudahan);
            }
        }

        //Saving in Aduan_siasatan_Bngn

        logger.debug("----------Saving in Aduan_siasatan_Bngn----------::");

        //delete existing records
         logger.debug("----------Saving in Aduan_Siasatan_keamaduan----------::");
        logger.info("--------Deleting Records---------:");
        for (int j = 0; j < siasatanPerihalBangunan.size(); j++) {
            logger.info("--------j---------:" + j);
            SiasatanPerihalBangunan siasatanPerihalBangunan1 = new SiasatanPerihalBangunan();
            siasatanPerihalBangunan1 = siasatanPerihalBangunan.get(j);
            ptService.deleteSiasatanPerihalBangunan(siasatanPerihalBangunan1);
        }

        //Saving
        for (int i = 0; i < senaraikodJenisPembangunan.size(); i++) {
            KodJenisPembangunan kodJenisPembangunan = new KodJenisPembangunan();
            kodJenisPembangunan = senaraikodJenisPembangunan.get(i);
            String kod = getContext().getRequest().getParameter(kodJenisPembangunan.getKod());
            if (kod != null && !kod.equals("")) {
                SiasatanPerihalBangunan siasatanPerihalBangunan = new SiasatanPerihalBangunan();
                siasatanPerihalBangunan.setPermohonan(permohonan);
                siasatanPerihalBangunan.setInfoAudit(infoAudit);
                siasatanPerihalBangunan.setKodCawangan(permohonan.getCawangan());
                siasatanPerihalBangunan.setBilanganBlok(bBlok);
                siasatanPerihalBangunan.setBilanganUnit(bUnit);
                siasatanPerihalBangunan.setKongsiTangga(kongsiTangga);
//                    long lkod = Long.parseLong(kod);
                siasatanPerihalBangunan.setKodJenisPembangunan(kodJenisPembangunanDAO.findById(kod));
                if (kod.equals("P08")) {
                    siasatanPerihalBangunan.setCatatan(catatan1);
                }
                try {
                    ptService.simpanSiasatanPerihalBangunan(siasatanPerihalBangunan);
                } catch (Exception j) {
                }
            }
        }

        aduanSiasatanPK = ptService.findSiasatanKemudahanListByIdPermohonan(idPermohonan);
        siasatanPerihalBangunan = ptService.findaduanSiasatanBangunanListByIdPermohonan(idPermohonan);
        addSimpleMessage("Maklumat bangunan telah berjaya disimpan.");
        return new JSP("strata/kuatkuasa/kemasukan_maklumat_bangunan.jsp").addParameter("tab", "true");
    }
    
    public Resolution isiSemula(){
        logger.info("--------isi semula start here---------:");
        
        //delete existing records
        logger.info("--------Deleting Records---------:");
        for (int j = 0; j < aduanSiasatanPK.size(); j++) {
            logger.info("--------j---------:" + j);
            SiasatanPerihalKemudahan siasatanPerihalKemudahan = new SiasatanPerihalKemudahan();
            siasatanPerihalKemudahan = aduanSiasatanPK.get(j);
            ptService.deleteSiasatanPerihalKemudahan(siasatanPerihalKemudahan);
        }
        
        //delete existing records
        logger.info("--------Deleting Records---------:");
        for (int j = 0; j < siasatanPerihalBangunan.size(); j++) {
            logger.info("--------j---------:" + j);
            SiasatanPerihalBangunan siasatanPerihalBangunan1 = new SiasatanPerihalBangunan();
            siasatanPerihalBangunan1 = siasatanPerihalBangunan.get(j);
            ptService.deleteSiasatanPerihalBangunan(siasatanPerihalBangunan1);
        }
        
        logger.info("--------isi semula end here---------:");
        return new RedirectResolution(KemasukanMaklumatBangunanActionBean.class, "showForm");
    }
}
