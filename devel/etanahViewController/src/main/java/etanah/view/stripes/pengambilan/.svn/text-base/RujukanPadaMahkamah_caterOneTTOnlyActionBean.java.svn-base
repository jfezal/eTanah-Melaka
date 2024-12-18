/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanMahkamah;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakPendeposit;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/rujukanPadaMahkamahCaterOneTT")
public class RujukanPadaMahkamah_caterOneTTOnlyActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    @Inject
    PengambilanService pengambilanService;
    private String idPermohonan;
    private long idPihak;
    private String idHakmilik;
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private PermohonanPihak permohonanPihak;
    private Hakmilik hakmilik;
    private PermohonanMahkamah permohonanMahkamah;
    private List<HakmilikPermohonan> hakmilikPermohonanList = new ArrayList<HakmilikPermohonan>();
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
    private String ringkasanBantah;
    private String butiranTanah;
    private String notis;
    private String pernyataan;
    private BigDecimal amnTawarRosak;
    private BigDecimal amnTawarPampasan;
    private String alasanAmnPampasan;
    private String lampiran;
    private String kodUrusan;
    private String prosiding;
    private String warta;
    private static Logger logger = Logger.getLogger(RujukanPadaMahkamah_caterOneTTOnlyActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }
        return new JSP("pengambilan/melaka/bantahanmahkamah/RujukanPadaMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah != null) {
                ringkasanBantah = permohonanMahkamah.getRingkasanBantah();
                butiranTanah = permohonanMahkamah.getButiranTanah();
                notis = permohonanMahkamah.getNotis();
                pernyataan = permohonanMahkamah.getPernyataan();
//                    if(amnTawarRosak==null){
//                    logger.info("amnTawarRosak is null");
//                    }else{
                amnTawarRosak = permohonanMahkamah.getAmnTawarRosak();
//                    }
//                    if(amnTawarPampasan==null){
//                     logger.info("amnTawarPampasan is null");
//                    }else{
                amnTawarPampasan = permohonanMahkamah.getAmnTawarPampasan();
//                    }

                alasanAmnPampasan = permohonanMahkamah.getAlasanAmnPampasan();
                lampiran = permohonanMahkamah.getLampiran();
                warta = permohonanMahkamah.getKepentinganTanah();
                prosiding = permohonanMahkamah.getSamanPemulaBil();
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/RujukanPadaMahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            kodUrusan = permohonan.getKodUrusan().getNama();

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    permohonanPihakPendepositList = pengambilanService.findByPendepositList(idPermohonan);
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    if (senaraiPermohonanPihak.size() > 0) {
                        permohonanPihak = senaraiPermohonanPihak.get(0);
                    }
                }
            }
        }
    }

    public Resolution simpan() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        InfoAudit infoAudit;

        if (permohonanPihak != null) {
            permohonanMahkamah = permohonanSupayaBantahanService.findPermohonanMahkamahByidMP(permohonanPihak.getIdPermohonanPihak());
            if (permohonanMahkamah == null) {
                permohonanMahkamah = new PermohonanMahkamah();
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            } else {
                infoAudit = permohonanMahkamah.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                permohonanMahkamah.setInfoAudit(infoAudit);
            }
            permohonanMahkamah.setPermohonanPihak(permohonanPihak);
            permohonanMahkamah.setRingkasanBantah(ringkasanBantah);
            permohonanMahkamah.setButiranTanah(butiranTanah);
            permohonanMahkamah.setNotis(notis);
            permohonanMahkamah.setPernyataan(pernyataan);
//            if(amnTawarRosak!=null){
            permohonanMahkamah.setAmnTawarRosak(amnTawarRosak);
//            }
//            if(amnTawarPampasan!=null){
            permohonanMahkamah.setAmnTawarPampasan(amnTawarPampasan);
//            }


            permohonanMahkamah.setAlasanAmnPampasan(alasanAmnPampasan);
            permohonanMahkamah.setSamanPemulaBil(prosiding);
            permohonanMahkamah.setKepentinganTanah(warta);
            permohonanMahkamah.setLampiran(lampiran);
            permohonanSupayaBantahanService.saveOrupdatePermohonanMahkamah(permohonanMahkamah);
        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/melaka/bantahanmahkamah/RujukanPadaMahkamah.jsp").addParameter("tab", "true");
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanMahkamah getPermohonanMahkamah() {
        return permohonanMahkamah;
    }

    public void setPermohonanMahkamah(PermohonanMahkamah permohonanMahkamah) {
        this.permohonanMahkamah = permohonanMahkamah;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public String getAlasanAmnPampasan() {
        return alasanAmnPampasan;
    }

    public void setAlasanAmnPampasan(String alasanAmnPampasan) {
        this.alasanAmnPampasan = alasanAmnPampasan;
    }

    public BigDecimal getAmnTawarPampasan() {
        return amnTawarPampasan;
    }

    public void setAmnTawarPampasan(BigDecimal amnTawarPampasan) {
        this.amnTawarPampasan = amnTawarPampasan;
    }

    public BigDecimal getAmnTawarRosak() {
        return amnTawarRosak;
    }

    public void setAmnTawarRosak(BigDecimal amnTawarRosak) {
        this.amnTawarRosak = amnTawarRosak;
    }

    public String getButiranTanah() {
        return butiranTanah;
    }

    public void setButiranTanah(String butiranTanah) {
        this.butiranTanah = butiranTanah;
    }

    public String getLampiran() {
        return lampiran;
    }

    public void setLampiran(String lampiran) {
        this.lampiran = lampiran;
    }

    public String getNotis() {
        return notis;
    }

    public void setNotis(String notis) {
        this.notis = notis;
    }

    public String getPernyataan() {
        return pernyataan;
    }

    public void setPernyataan(String pernyataan) {
        this.pernyataan = pernyataan;
    }

    public String getRingkasanBantah() {
        return ringkasanBantah;
    }

    public void setRingkasanBantah(String ringkasanBantah) {
        this.ringkasanBantah = ringkasanBantah;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public List<PermohonanPihakPendeposit> getPermohonanPihakPendepositList() {
        return permohonanPihakPendepositList;
    }

    public void setPermohonanPihakPendepositList(List<PermohonanPihakPendeposit> permohonanPihakPendepositList) {
        this.permohonanPihakPendepositList = permohonanPihakPendepositList;
    }

    public String getProsiding() {
        return prosiding;
    }

    public void setProsiding(String prosiding) {
        this.prosiding = prosiding;
    }

    public String getWarta() {
        return warta;
    }

    public void setWarta(String warta) {
        this.warta = warta;
    }
    
    
}
