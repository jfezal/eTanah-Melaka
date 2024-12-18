/*
 * Date : 24 Jul 2012
 * Author : Orogenic
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.KodDaerahDAO;
import etanah.model.*;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/pelupusan/draf_pembebasan_upah_ukur")
public class DrafPembebasanUpahUkurActionBean extends AbleActionBean {

    private String idPermohonan;
    private TanahRizabPermohonan trizabPermohonan;
    private FasaPermohonan fasaPermohonan;
    private String keluasanTanah;
    private String tempat;
    private String mukimBandarPekan;
    private String daerah;
    private String kegunaanTanah;
    private HakmilikPermohonan hakmilikPermohonan;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    KodDaerahDAO kodDaerahDAO;
    private String tarikh;
    private static final Logger LOG = Logger.getLogger(DrafPembebasanUpahUkurActionBean.class);
    @Inject
    PelupusanService pelupusanService;
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pelupusan/draf_pembebasan_upah_ukur.jsp").addParameter("tab", "true");

    }

    public Resolution simpanPembebasanUpahUkur() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        LOG.info("-----------------idPermohonan " + idPermohonan);
        Permohonan permohonan = pelupusanService.findById(idPermohonan);

        
        trizabPermohonan = pelupusanService.findTanahRizabByIdPermohonan(idPermohonan);
//        try {

        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);

        if (hakmilikPermohonan != null) {
            if (trizabPermohonan == null) {
            trizabPermohonan = new TanahRizabPermohonan();                       
            trizabPermohonan.setPermohonan(permohonan);
            trizabPermohonan.setCawangan(permohonan.getCawangan());

            //set daerah

//                hakmilikPermohonan.setBandarPekanMukimBaru(null);

            trizabPermohonan.setDaerah(hakmilikPermohonan.getHakmilik() != null && hakmilikPermohonan.getHakmilik().getDaerah() != null ? hakmilikPermohonan.getHakmilik().getDaerah() : hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah());

            //tempat
//                hakmilikPermohonan.setLokasi(this.getTempat());
            trizabPermohonan.setLokasi(hakmilikPermohonan.getHakmilik() != null && !StringUtils.isEmpty(hakmilikPermohonan.getHakmilik().getLokasi()) ? hakmilikPermohonan.getHakmilik().getLokasi() : hakmilikPermohonan.getLokasi());


            //mukim
//                hakmilikPermohonan.setBandarPekanMukimBaru(null);

            trizabPermohonan.setBandarPekanMukim(hakmilikPermohonan.getHakmilik() != null && hakmilikPermohonan.getHakmilik().getBandarPekanMukim() != null ? hakmilikPermohonan.getHakmilik().getBandarPekanMukim() : hakmilikPermohonan.getBandarPekanMukimBaru());


//                hakmilikPermohonan.setLuasTerlibat(new BigDecimal(this.getKeluasanTanah()));
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                trizabPermohonan.setLuasTerlibat(hakmilikPermohonan.getLuasDiluluskan());
                trizabPermohonan.setKodUnitLuas(hakmilikPermohonan.getLuasLulusUom());
            } else {
                trizabPermohonan.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
            }

            

            //trizabPermohonan.setCatatan(daerah);
            trizabPermohonan.setInfoAudit(hakmilikPermohonan.getInfoAudit());
            pelupusanService.simpanTanahRizabPermohonan(trizabPermohonan);
            }
            //sebab
            permohonan.setSebab(this.getKegunaanTanah());
            pelupusanService.simpanPermohonan(permohonan);
//                pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);

            addSimpleMessage("Maklumat Berjaya Disimpan");

//            }

//                // commit
//            }  catch (Exception ex) {
//
//            ex.printStackTrace();
//            // rollback
//        }

        }

        return new JSP("pelupusan/draf_pembebasan_upah_ukur.jsp").addParameter("tab",
                "true");

    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("----------REHYDRATE----------");
        retrieveData();

    }

    public void retrieveData() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
//
        Permohonan permohonan = pelupusanService.findById(idPermohonan);
//
        if (permohonan != null) {
            this.setKegunaanTanah(permohonan.getSebab());
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "21TrmKptsnMMK");
            } else if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PBRZ")) {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "16TrmKptsnMMK");
            } else {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "014");

            }

            if (fasaPermohonan != null) {

                this.setTarikh(sdf.format(fasaPermohonan.getTarikhKeputusan()));

            }
        }
//            
        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
//        
//       
        if (hakmilikPermohonan != null) {
            this.setDaerah(hakmilikPermohonan.getHakmilik() != null && hakmilikPermohonan.getHakmilik().getDaerah() != null ? hakmilikPermohonan.getHakmilik().getDaerah().getNama() : hakmilikPermohonan.getBandarPekanMukimBaru().getDaerah().getNama());
            this.setTempat(hakmilikPermohonan.getHakmilik() != null && !StringUtils.isEmpty(hakmilikPermohonan.getHakmilik().getLokasi()) ? hakmilikPermohonan.getHakmilik().getLokasi() : hakmilikPermohonan.getLokasi());
            this.setMukimBandarPekan(hakmilikPermohonan.getHakmilik() != null && hakmilikPermohonan.getHakmilik().getBandarPekanMukim() != null ? hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getNama() : hakmilikPermohonan.getBandarPekanMukimBaru().getNama());
            if (permohonan.getKodUrusan().getKod().equalsIgnoreCase("PRMMK")) {
                this.setKeluasanTanah((hakmilikPermohonan.getLuasDiluluskan() != null ? hakmilikPermohonan.getLuasDiluluskan().toString() : new String()) + " " + (hakmilikPermohonan.getLuasLulusUom() != null ? hakmilikPermohonan.getLuasLulusUom().getNama() : new String()));
            } else {
                LOG.info("=== else PRMMK ===");
                this.setKeluasanTanah((hakmilikPermohonan.getLuasTerlibat() != null ? hakmilikPermohonan.getLuasTerlibat().toString() : new String()) + " " + (hakmilikPermohonan.getKodUnitLuas() != null ? hakmilikPermohonan.getKodUnitLuas().getNama() : new String()));
            }
            this.setKegunaanTanah(permohonan.getSebab());
        } else {
            this.setDaerah("");
            this.setTempat("");
            this.setMukimBandarPekan("");
            this.setDaerah("");
            this.setKeluasanTanah("");
            Date today = new Date();
            this.setTarikh(today.toString());
        }


    }

    public String getMukimBandarPekan() {
        return mukimBandarPekan;
    }

    public void setMukimBandarPekan(String mukimBandarPekan) {
        this.mukimBandarPekan = mukimBandarPekan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }

    public String getKegunaanTanah() {
        return kegunaanTanah;
    }

    public void setKegunaanTanah(String kegunaanTanah) {
        this.kegunaanTanah = kegunaanTanah;
    }

    public String getKeluasanTanah() {
        return keluasanTanah;
    }

    public void setKeluasanTanah(String keluasanTanah) {
        this.keluasanTanah = keluasanTanah;
    }

    public String getTarikh() {
        return tarikh;
    }

    public void setTarikh(String tarikh) {
        this.tarikh = tarikh;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public TanahRizabPermohonan getTrizabPermohonan() {
        return trizabPermohonan;
    }

    public void setTrizabPermohonan(TanahRizabPermohonan trizabPermohonan) {
        this.trizabPermohonan = trizabPermohonan;
    }
       
}