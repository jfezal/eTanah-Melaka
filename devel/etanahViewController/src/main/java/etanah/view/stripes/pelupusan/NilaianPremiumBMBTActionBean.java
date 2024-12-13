/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodTuntutDAO;
import etanah.dao.KodUOMDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodRujukan;
import etanah.model.KodUOM;
import etanah.model.NoPt;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.CalcTax;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pelupusan.disClass.DisPermohonanHakmilikPihak;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Afham
 */
@UrlBinding("/pelupusan/nilaianPremiumBMBT")
public class NilaianPremiumBMBTActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(NilaianPremiumBMBTActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    @Inject
    CalcTax calcTax ;
    private Permohonan permohonan;
    private Pengguna pengguna;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    List<PermohonanTuntutanKos> senaraiMohonTuntutKos;
    private PermohonanTuntutanKos mohonTuntutKos;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private boolean viewOnly;
    private List hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private HakmilikPermohonan hakmilikPermohonan;
    private BigDecimal nilai;
    private BigDecimal premium; //DISPRM
    private BigDecimal premiumT; //DISPRMT
    private BigDecimal cukaiS; //DISTAX
    private BigDecimal ukur; //DISUKUR
    private String tarikhRujukan;
    private int bilanganTandaSempadan ;

    @DefaultHandler()
    public Resolution showForm() {
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/bmbt/nilaian_premium_BMBT.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/bmbt/nilaian_premium_BMBT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (pelupusanService.findByIdMohonRujukLuar(idPermohonan) != null) {
            permohonanRujukanLuar = pelupusanService.findByIdMohonRujukLuar(idPermohonan).get(0);
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }

        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            System.out.println("idPermohonan:-----------" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            HakmilikPermohonan hmTemp = new HakmilikPermohonan();
            if (!hakmilikPermohonanList.isEmpty()) {
                hmTemp = (HakmilikPermohonan) hakmilikPermohonanList.get(0);
            }
            String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
            if (idhakmilikpermohonan == null) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(hmTemp.getId());
                if (hakmilikPermohonan.getNilaianJpph() != null) {
                    nilai = hakmilikPermohonan.getNilaianJpph();
                }

            } else if (idhakmilikpermohonan != null && !idhakmilikpermohonan.equals("")) {
                hakmilikPermohonan = new HakmilikPermohonan();
                hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
                if (hakmilikPermohonan.getNilaianJpph() != null) {
                    nilai = hakmilikPermohonan.getNilaianJpph();
                }
            }
            mohonTuntutKos = new PermohonanTuntutanKos();
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRM", hakmilikPermohonan.getId());
            if (mohonTuntutKos != null) {
                premium = mohonTuntutKos.getAmaunTuntutan();
            }else {
                 premium = hakmilikPermohonan.getHakmilik().getLuas().multiply(nilai) ;
            }
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRMT", hakmilikPermohonan.getId());
            if (mohonTuntutKos != null) {
                premiumT = mohonTuntutKos.getAmaunTuntutan();
            }
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISTAX", hakmilikPermohonan.getId());
            if (mohonTuntutKos != null) {
                cukaiS = mohonTuntutKos.getAmaunTuntutan();
            }
//            else{
//                cukaiS = calcTax.calculate(hakmilikPermohonan.getKodKegunaanTanah().getKod(), hakmilikPermohonan.getHakmilik().getBandarPekanMukim().getbandarPekanMukim(), hakmilikPermohonan.getKodUnitLuas().getKod()
//                                          , hakmilikPermohonan.getLuasTerlibat(), hakmilikPermohonan.getHakmilik(), null);
//            }
            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISUKUR", hakmilikPermohonan.getId());
            if (mohonTuntutKos != null) {
                ukur = mohonTuntutKos.getAmaunTuntutan();
            }
        }
    }

    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        //Bayaran Premium
        mohonTuntutKos = new PermohonanTuntutanKos();
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRM", hakmilikPermohonan.getId());
        InfoAudit info = new InfoAudit();

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Premium");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Premium Tanah");
            mohonTuntutKos.setAmaunTuntutan(premium);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRM"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRM").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }

        //Bayaran Premium Tambahan

        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISPRMT", hakmilikPermohonan.getId());
        if (premiumT != null) {
            if (mohonTuntutKos == null) {
                mohonTuntutKos = new PermohonanTuntutanKos();
                info.setDimasukOleh(pengguna);
                info.setTarikhMasuk(new java.util.Date());
                mohonTuntutKos.setInfoAudit(info);
                mohonTuntutKos.setPermohonan(permohonan);
                mohonTuntutKos.setCawangan(permohonan.getCawangan());
                mohonTuntutKos.setItem("Bayaran Premium Tambahan");
                mohonTuntutKos.setAmaunTuntutan(premiumT);
                mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRMT"));
                mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRMT").getKodKewTrans());
                pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
            } else {
                mohonTuntutKos.setInfoAudit(info);
                mohonTuntutKos.setPermohonan(permohonan);
                info.setDiKemaskiniOleh(pengguna);
                info.setTarikhKemaskini(new java.util.Date());
                mohonTuntutKos.setCawangan(permohonan.getCawangan());
                mohonTuntutKos.setItem("Bayaran Premium Tambahan");
                mohonTuntutKos.setAmaunTuntutan(premiumT);
                mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
                mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISPRMT"));
                mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISPRMT").getKodKewTrans());
                pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
            }
        }


        //Bayaran Hasil Tahun Pertama

        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISTAX", hakmilikPermohonan.getId());

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cukai Stratum");
            mohonTuntutKos.setAmaunTuntutan(cukaiS);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Cukai Stratum");
            mohonTuntutKos.setAmaunTuntutan(cukaiS);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISTAX"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISTAX").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }


        //Bayaran Upah Ukur

        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISUKUR", hakmilikPermohonan.getId());

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Upah Ukur");
            mohonTuntutKos.setAmaunTuntutan(ukur);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISUKUR"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISUKUR").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Bayaran Upah Ukur");
            mohonTuntutKos.setAmaunTuntutan(ukur);
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISUKUR"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISUKUR").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        
        //Bayaran Pendaftaran Hakmilik Tetap
        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISFT", hakmilikPermohonan.getId());

        if (mohonTuntutKos == null) {
            mohonTuntutKos = new PermohonanTuntutanKos();
            info.setDimasukOleh(pengguna);
            info.setTarikhMasuk(new java.util.Date());
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
        } else {
            mohonTuntutKos.setInfoAudit(info);
            mohonTuntutKos.setPermohonan(permohonan);
            info.setDiKemaskiniOleh(pengguna);
            info.setTarikhKemaskini(new java.util.Date());
            mohonTuntutKos.setCawangan(permohonan.getCawangan());
            mohonTuntutKos.setItem("Pendaftaran Hakmilik Kekal");
            mohonTuntutKos.setAmaunTuntutan(new BigDecimal(30)); //Temporary because need to ask Pendaftaran
            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISFT"));
            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISFT").getKodKewTrans());
            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
        }
        
//        //Bayaran Tanda Sempadan
//        mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(idPermohonan, "DISSEM", hakmilikPermohonan.getId());
//
//        if (mohonTuntutKos == null) {
//            mohonTuntutKos = new PermohonanTuntutanKos();
//            info.setDimasukOleh(pengguna);
//            info.setTarikhMasuk(new java.util.Date());
//            mohonTuntutKos.setInfoAudit(info);
//            mohonTuntutKos.setPermohonan(permohonan);
//            mohonTuntutKos.setCawangan(permohonan.getCawangan());
//            mohonTuntutKos.setItem("Bayaran Tanda Sempadan");
//            mohonTuntutKos.setAmaunSeunit(new BigDecimal(4)); //Temporary because need to ask Pendaftaran
//            mohonTuntutKos.setKuantiti(bilanganTandaSempadan);
//            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
//            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISSEM"));
//            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISSEM").getKodKewTrans());
//            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
//        } else {
//            mohonTuntutKos.setInfoAudit(info);
//            mohonTuntutKos.setPermohonan(permohonan);
//            info.setDiKemaskiniOleh(pengguna);
//            info.setTarikhKemaskini(new java.util.Date());
//            mohonTuntutKos.setCawangan(permohonan.getCawangan());
//            mohonTuntutKos.setItem("Bayaran Tanda Sempadan");
//            mohonTuntutKos.setAmaunSeunit(new BigDecimal(4)); //Temporary because need to ask Pendaftaran
//            mohonTuntutKos.setKuantiti(bilanganTandaSempadan);
//            mohonTuntutKos.setHakmilikPermohonan(hakmilikPermohonan);
//            mohonTuntutKos.setKodTuntut(kodTuntutDAO.findById("DISSEM"));
//            mohonTuntutKos.setKodTransaksi(kodTuntutDAO.findById("DISSEM").getKodKewTrans());
//            pelupusanService.simpanPermohonanTuntutanKos(mohonTuntutKos);
//        }



        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/bmbt/nilaian_premium_BMBT.jsp").addParameter("tab", "true");


    }

    public Resolution searchHakmilik() throws ParseException {

        String idhakmilikpermohonan = getContext().getRequest().getParameter("idHakmilik");
        String editType = getContext().getRequest().getParameter("edit");
        hakmilikPermohonan = new HakmilikPermohonan();
        hakmilikPermohonan = hakmilikPermohonanDAO.findById(new Long(idhakmilikpermohonan));
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/bmbt/nilaian_premium_BMBT.jsp").addParameter("tab", "true");
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<PermohonanTuntutanKos> getSenaraiMohonTuntutKos() {
        return senaraiMohonTuntutKos;
    }

    public void setSenaraiMohonTuntutKos(List<PermohonanTuntutanKos> senaraiMohonTuntutKos) {
        this.senaraiMohonTuntutKos = senaraiMohonTuntutKos;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public BigDecimal getCukaiS() {
        return cukaiS;
    }

    public void setCukaiS(BigDecimal cukaiS) {
        this.cukaiS = cukaiS;
    }

    public PermohonanTuntutanKos getMohonTuntutKos() {
        return mohonTuntutKos;
    }

    public void setMohonTuntutKos(PermohonanTuntutanKos mohonTuntutKos) {
        this.mohonTuntutKos = mohonTuntutKos;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public BigDecimal getPremiumT() {
        return premiumT;
    }

    public void setPremiumT(BigDecimal premiumT) {
        this.premiumT = premiumT;
    }

    public BigDecimal getUkur() {
        return ukur;
    }

    public void setUkur(BigDecimal ukur) {
        this.ukur = ukur;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public int getBilanganTandaSempadan() {
        return bilanganTandaSempadan;
    }

    public void setBilanganTandaSempadan(int bilanganTandaSempadan) {
        this.bilanganTandaSempadan = bilanganTandaSempadan;
    }

    

    
}
