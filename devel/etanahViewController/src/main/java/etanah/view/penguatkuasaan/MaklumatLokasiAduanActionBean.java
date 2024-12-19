/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.AduanLokasiDAO;
import etanah.model.AduanLokasi;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodLot;


import etanah.model.KodPemilikan;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.ListUtil;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

//import javax.swing.JOptionPane;
/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/maklumat_lokasi_aduan")
public class MaklumatLokasiAduanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatLokasiAduanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    AduanLokasiDAO aduanLokasiDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    ListUtil listUtil;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private AduanLokasi aduanLokasi;
    private KodPemilikan pemilikan;
    private KodCawangan cawangan;
    private KodLot kodLot;
    private KodBandarPekanMukim bandarPekanMukim;
    private String idPermohonan;
    private List<KodBandarPekanMukim> listBandarPekanMukim;
    private int kod;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        kodNegeri = conf.getProperty("kodNegeri");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }

            if (aduanLokasi == null) {
                if (permohonan.getPermohonanSebelum() != null) {
                    //1) find aduan lokasi based on id permohonan sebelum (IP create new id permohonan)
                    aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(permohonan.getPermohonanSebelum().getIdPermohonan());

                    if (aduanLokasi == null) {
                        //2) find aduan lokasi for sebahagian kompaun dakwa (create new permohonan) 3 layer permohonan
                        Permohonan permohonanIP = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (permohonanIP != null) {
                            aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(permohonanIP.getPermohonanSebelum().getIdPermohonan());
                        }
                    }
                }
            }

            //Hafifi 01/04/2014 - Check again if aduan lokasi still null. Untuk seksyen 100 KTN, dapatkan maklumat Lot dari table mohon_hakmilik
            if (kodNegeri.equalsIgnoreCase("05")) {
                if (aduanLokasi == null) {
                    HakmilikPermohonan hp = enforceService.findhakmilikPermohonanByIdpermohonan(idPermohonan);

                    if (hp != null) {
                        Hakmilik hakmilik = enforceService.semakIdHakmilik(hp.getHakmilik().getIdHakmilik());
                        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
                        System.out.println(peng);
                        InfoAudit ia = new InfoAudit();
                        ia.setDimasukOleh(peng);
                        ia.setTarikhMasuk(new Date());
                        aduanLokasi = new AduanLokasi();
                        aduanLokasi.setInfoAudit(ia);                        
                        aduanLokasi.setBandarPekanMukim(hakmilik.getBandarPekanMukim());
                        aduanLokasi.setCawangan(permohonan.getCawangan());
                        aduanLokasi.setKodLot(hakmilik.getLot());
                        aduanLokasi.setNoLot(hakmilik.getNoLot());
                        aduanLokasi.setPemilikan(hp.getKodMilik());
                        aduanLokasi.setPermohonan(permohonan);
                        enforceService.simpanAduanLokasi(aduanLokasi);
                    }
                }
            }

            listBandarPekanMukim = listUtil.getSenaraiKodBPMByDaerah(permohonan.getCawangan().getKod());

            if (aduanLokasi != null) {
                kod = aduanLokasi.getBandarPekanMukim().getKod();
            }
            logger.debug("idPermohonan :" + idPermohonan);
        }
    }

    public Resolution simpan1() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        // KodCawangan caw = new KodCawangan();
        //caw.setKod("05");
        cawangan = permohonan.getCawangan();
        if (aduanLokasi == null) {
            aduanLokasi = new AduanLokasi();
        }
        aduanLokasi.setPermohonan(permohonan);
        aduanLokasi.setBandarPekanMukim(bandarPekanMukim);
        aduanLokasi.setPemilikan(pemilikan);
        aduanLokasi.setKodLot(kodLot);

        if (aduanLokasi != null) {
            KodBandarPekanMukim bpm = new KodBandarPekanMukim();
            bpm.setKod(kod);
            aduanLokasi.setBandarPekanMukim(bpm);
        }
        aduanLokasi.setCawangan(cawangan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = aduanLokasi.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            aduanLokasi.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforceService.simpanAduanLokasi(aduanLokasi);
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + aduanLokasi.getIdAduanLokasi());
        return new JSP("penguatkuasaan/maklumat_lokasi_aduan.jsp").addParameter("tab", "true");
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public KodPemilikan getPemilikan() {
        return pemilikan;
    }

    public void setPemilikan(KodPemilikan pemilikan) {
        this.pemilikan = pemilikan;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public KodLot getKodLot() {
        return kodLot;
    }

    public void setKodLot(KodLot kodLot) {
        this.kodLot = kodLot;
    }

    public List<KodBandarPekanMukim> getListBandarPekanMukim() {
        return listBandarPekanMukim;
    }

    public void setListBandarPekanMukim(List<KodBandarPekanMukim> listBandarPekanMukim) {
        this.listBandarPekanMukim = listBandarPekanMukim;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
