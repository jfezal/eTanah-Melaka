/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PihakDAO;
import etanah.dao.KodJenisPindahmilikDAO;
import etanah.dao.PermohonanUrusanDAO;
import etanah.dao.KodUOMDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanUrusan;
import etanah.model.KodJenisPindahmilik;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.service.ConsentPtdService;
import etanah.service.common.HakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanPihakService;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author solahuddin
 */
@UrlBinding("/consent/maklumat_pindahmilik")
public class MaklumatPindahMilikActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    HakmilikService hakmilikService;
    @Inject
    KodJenisPindahmilikDAO kodJenisPindahmilikDAO;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    @Inject
    PermohonanUrusanDAO permohonanUrusanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    PemohonService pemohonService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    private static final Logger LOG = Logger.getLogger(MaklumatPindahMilikActionBean.class);
    etanahActionBeanContext ctx;
    private String idHakmilik;
    private Permohonan permohonan;
    private PermohonanPihak permohonanPihak;
    private PermohonanUrusan permohonanUrusan;
    private PermohonanUrusan permohonanUrusan2;
    private Integer tempohTahun;
    private Integer tempohBulan;
    private Integer tempohTahunSerentak;
    private List<HakmilikPermohonan> senaraiHakmilikTerlibat;
    private HakmilikPermohonan hakmilikPermohonan;
    private String pegangan;
    private String penjenisan;
    private String idPermohonan;
    private String penyerahNoRujukan;
    private KodJenisPindahmilik jenisPindahmilik;
    private String pindahMilik;
    private KodUOM kodUom;
    private String kodUnitLuasBaru;
    private List<Pemohon> senaraiPemohon;
    private List<PermohonanPihak> senaraiPermohonanPihak;

    @DefaultHandler
    public Resolution showForm() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        if (StringUtils.isBlank(idHakmilik)) {
            idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
        }

        senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        LOG.info(":: ID HAKMILIK : " + idHakmilik);
        return new JSP("consent/maklumat_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("consent/paparan_maklumat_pindahmilik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (StringUtils.isBlank(idHakmilik)) {
                idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            }

            permohonanUrusan = conService.findMohonUrusanByIdMohonIdHakmilik(idPermohonan, idHakmilik);
            permohonanUrusan2 = conService.findMohonUrusanByPerihalIdHakmilik(idPermohonan, "serentak", idHakmilik);
            hakmilikPermohonan = hakmilikPermohonanService.checkByIdHakmilikIdMohon(idPermohonan, idHakmilik);
            
            /*if (permohonan.getKodUrusan().getKod().equals("PMKMM") || permohonan.getKodUrusan().getKod().equals("PPTGM")) {
                    jenisPindahmilik = kodJenisPindahmilikDAO.findById(permohonanUrusan.getPindahmilik().getKod());
                    pindahMilik = jenisPindahmilik.getKod(); 
            }*/
            
            
            if (permohonan.getKodUrusan().getKod().equals("PJKMM") && permohonan.getKodUrusan().getKod().equals("PJPTD")) {
               if (kodUnitLuasBaru != null) {
                    kodUom = kodUOMDAO.findById(hakmilikPermohonan.getKodUnitLuasBaru().getKod());
                    kodUnitLuasBaru = kodUom.getKod(); 
               } else {
                   hakmilikPermohonan.setKodUnitLuasBaru(hakmilikPermohonan.getHakmilik().getKodUnitLuas());
               }
            }

            if (permohonanUrusan != null) {
                if (permohonanUrusan.getPerjanjianTempohTahun() != null) {
                    tempohTahun = permohonanUrusan.getPerjanjianTempohTahun();
                }
                if (permohonanUrusan.getPerjanjianTempohBulan() != null) {
                    tempohBulan = permohonanUrusan.getPerjanjianTempohBulan();
                }
            }
            if (permohonanUrusan2 != null) {
                if (permohonanUrusan2.getPerjanjianTempohTahun() != null) {
                    tempohTahunSerentak = permohonanUrusan2.getPerjanjianTempohTahun();
                }
            }
            
            if (permohonan.getKodUrusan().getKod().equals("PMJTL") || permohonan.getKodUrusan().getKod().equals("RMJTL") || permohonan.getKodUrusan().getKod().equals("PMMK1") || permohonan.getKodUrusan().getKod().equals("PMMK2")) {
                senaraiPemohon = pemohonService.findPemohonByIdPermohonanAndHakmilik(idPermohonan, idHakmilik);
            } 
            else {
                senaraiPemohon = permohonan.getSenaraiPemohon(); 
            }
            
            senaraiPermohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByNotKodAndIdHakmilik(idPermohonan, "TER", idHakmilik);
            
            LOG.info(":: ID Hakmilik : " + idHakmilik);
            senaraiHakmilikTerlibat = permohonan.getSenaraiHakmilik();
        }
    }

    public Resolution simpanPindahMilik() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        pegangan = getContext().getRequest().getParameter("hakmilikPermohonan.pegangan");
        penjenisan = getContext().getRequest().getParameter("hakmilikPermohonan.penjenisan");
        penyerahNoRujukan = getContext().getRequest().getParameter("permohonan.penyerahNoRujukan");
        pindahMilik = getContext().getRequest().getParameter("pindahMilik");
        LOG.info("ID Hakmilik Saving sini : " + idHakmilik);
        Hakmilik hakmilik = hakmilikService.findById(idHakmilik);
        InfoAudit infoAudit = new InfoAudit();
        
        if (permohonanUrusan != null || tempohTahun != null || tempohBulan != null) {

            if (permohonanUrusan != null) {
                infoAudit = permohonanUrusan.getInfoAudit();
            } else {
                permohonanUrusan = new PermohonanUrusan();
            }

            if (permohonanUrusan.getInfoAudit() == null) {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }

            if (tempohTahun != null) {
                permohonanUrusan.setPerjanjianTempohTahun(tempohTahun);
            } else {
                permohonanUrusan.setPerjanjianTempohTahun(0);
            }
            if (tempohBulan != null) {
                permohonanUrusan.setPerjanjianTempohBulan(tempohBulan);
            } else {
                permohonanUrusan.setPerjanjianTempohBulan(0);
            }
            
            if(pindahMilik != null){
                 jenisPindahmilik = kodJenisPindahmilikDAO.findById(pindahMilik);
                 permohonanUrusan.setPindahmilik(jenisPindahmilik);    
            }
            
            if (hakmilikPermohonan != null){
                if(kodUnitLuasBaru != null){
                    kodUom = kodUOMDAO.findById(kodUnitLuasBaru);
                    hakmilikPermohonan.setKodUnitLuasBaru(kodUom);  
                }
                
                LOG.debug("PEGANGAN : " + getContext().getRequest().getParameter("hakmilikPermohonan.pegangan"));
                LOG.debug("KATG TANAH : " + getContext().getRequest().getParameter("hakmilikPermohonan.penjenisan"));
                hakmilikPermohonan.setPegangan(getContext().getRequest().getParameter("hakmilikPermohonan.pegangan"));
                hakmilikPermohonan.setPenjenisan(getContext().getRequest().getParameter("hakmilikPermohonan.penjenisan"));
                hakmilikPermohonan.setInfoAudit(infoAudit);
                hakmilikPermohonanService.save(hakmilikPermohonan);
                
            }
            
            LOG.info("NO FIC :" + penyerahNoRujukan);
            permohonan.setPenyerahNoRujukan(getContext().getRequest().getParameter("permohonan.penyerahNoRujukan"));
            permohonan.setInfoAudit(infoAudit);
            conService.simpanPermohonan(permohonan);

            permohonanUrusan.setHakmilik(hakmilik);
            permohonanUrusan.setCawangan(permohonan.getCawangan());
            permohonanUrusan.setPermohonan(permohonan);
            permohonanUrusan.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusan);
            addSimpleMessage("Maklumat telah berjaya disimpan.");    
        } 
        
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution simpanPindahMilik2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        if (permohonanUrusan != null || tempohTahun != null || tempohBulan != null) {

            if (permohonanUrusan != null) {
                infoAudit = permohonanUrusan.getInfoAudit();
            } else {
                permohonanUrusan = new PermohonanUrusan();
            }

            if (permohonanUrusan.getInfoAudit() == null) {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            if (tempohTahun != null) {
                permohonanUrusan.setPerjanjianTempohTahun(tempohTahun);
            } else {
                permohonanUrusan.setPerjanjianTempohTahun(0);
            }
            if (tempohBulan != null) {
                permohonanUrusan.setPerjanjianTempohBulan(tempohBulan);
            } else {
                permohonanUrusan.setPerjanjianTempohBulan(0);
            }
            permohonanUrusan.setCawangan(permohonan.getCawangan());
            permohonanUrusan.setPermohonan(permohonan);
            permohonanUrusan.setInfoAudit(infoAudit);
            conService.simpanPermohonanUrusan(permohonanUrusan);

        }

        if (permohonanUrusan2 != null) {

            infoAudit = permohonanUrusan2.getInfoAudit();

            if (infoAudit == null) {
                infoAudit = new InfoAudit();
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
            } else {
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
            }
            if (tempohTahunSerentak != null) {
                permohonanUrusan2.setPerjanjianTempohTahun(tempohTahunSerentak);
            } else {
                permohonanUrusan2.setPerjanjianTempohTahun(0);
            }
            permohonanUrusan2.setCawangan(permohonan.getCawangan());
            permohonanUrusan2.setPermohonan(permohonan);
            permohonanUrusan2.setInfoAudit(infoAudit);
            permohonanUrusan2.setPerihal("serentak");
            conService.simpanPermohonanUrusan(permohonanUrusan2);

        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new ForwardResolution("/WEB-INF/jsp/consent/maklumat_pindahmilik.jsp").addParameter("tab", "true");
    }

    public Resolution reloadEdit() {
        return showForm();
    }

    public Resolution reload() {
        return showForm2();
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanUrusan getPermohonanUrusan() {
        return permohonanUrusan;
    }

    public void setPermohonanUrusan(PermohonanUrusan permohonanUrusan) {
        this.permohonanUrusan = permohonanUrusan;
    }

    public PermohonanUrusan getPermohonanUrusan2() {
        return permohonanUrusan2;
    }

    public void setPermohonanUrusan2(PermohonanUrusan permohonanUrusan2) {
        this.permohonanUrusan2 = permohonanUrusan2;
    }

    public Integer getTempohTahun() {
        return tempohTahun;
    }

    public void setTempohTahun(Integer tempohTahun) {
        this.tempohTahun = tempohTahun;
    }

    public Integer getTempohTahunSerentak() {
        return tempohTahunSerentak;
    }

    public void setTempohTahunSerentak(Integer tempohTahunSerentak) {
        this.tempohTahunSerentak = tempohTahunSerentak;
    }

    public Integer getTempohBulan() {
        return tempohBulan;
    }

    public void setTempohBulan(Integer tempohBulan) {
        this.tempohBulan = tempohBulan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikTerlibat() {
        return senaraiHakmilikTerlibat;
    }

    public void setSenaraiHakmilikTerlibat(List<HakmilikPermohonan> senaraiHakmilikTerlibat) {
        this.senaraiHakmilikTerlibat = senaraiHakmilikTerlibat;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }
    
    public String getPegangan() {
        return pegangan;
    }

    public void setPegangan(String pegangan) {
        this.pegangan = pegangan;
    }

    public String getPenjenisan() {
        return penjenisan;
    }

    public void setPenjenisan(String penjenisan) {
        this.penjenisan = penjenisan;
    }

    public String getPenyerahNoRujukan() {
        return penyerahNoRujukan;
    }

    public void setPenyerahNoRujukan(String penyerahNoRujukan) {
        this.penyerahNoRujukan = penyerahNoRujukan;
    }

    public KodJenisPindahmilik getJenisPindahmilik() {
        return jenisPindahmilik;
    }

    public void setJenisPindahmilik(KodJenisPindahmilik jenisPindahmilik) {
        this.jenisPindahmilik = jenisPindahmilik;
    }

    public String getPindahMilik() {
        return pindahMilik;
    }

    public void setPindahMilik(String pindahMilik) {
        this.pindahMilik = pindahMilik;
    }

    public KodUOM getKodUom() {
        return kodUom;
    }

    public void setKodUom(KodUOM kodUom) {
        this.kodUom = kodUom;
    }

    public String getKodUnitLuasBaru() {
        return kodUnitLuasBaru;
    }

    public void setKodUnitLuasBaru(String kodUnitLuasBaru) {
        this.kodUnitLuasBaru = kodUnitLuasBaru;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }
}
