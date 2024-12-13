/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import java.util.ArrayList;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PihakDAO;
import etanah.model.Hakmilik;
import etanah.model.Permohonan;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.PengambilanAduanService;
import etanah.model.PermohonanAduan;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import etanah.service.PermohonanSupayaBantahanService;


/**
 *
 * @author massita
 */
@UrlBinding("/pelupusan/maklumatBantahanMahkamahPBRZ")
public class MaklumatBantahanActionBeanPBRZ extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBantahanActionBeanPBRZ.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    KodBandarPekanMukimDAO kodBandarPekanMukimDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private PermohonanAduan permohonanAduan;
    private Pihak pihak;
    private String idPermohonanPengambilan;
    private String namaProjek;
    private List<HakmilikUrusan> senaraiHakmilik;
    private List<PermohonanPihak> senaraiPP;
    private HakmilikUrusan hakmilikUrusan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan hp;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihak pp;
    private Hakmilik hakmilik;
    boolean cariFlag;
    private String idHakmilik;
    private String selectedIdHM;
    private String selectedPihak;
    private String idPermohonan;
    private String id;
    private String perihal;
    boolean tiadaDataFlag = false;
    private List<HakmilikUrusan> hakmilikByList;
    private List<HakmilikPermohonan> hakmilikMHList;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    private Permohonan permohonanSebelum;
    private long idPihak;

    @DefaultHandler
    public Resolution maklumatBantahanMahkamah() {
        if(permohonan!=null){
            idPermohonanPengambilan=permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum=permohonanDAO.findById(idPermohonanPengambilan);
            namaProjek=permohonanSebelum.getSebab();
            pemohon = aduanService.findPemohonByIdMohon(idPermohonanPengambilan);
            hp =  aduanService.findHPByIdMohon(permohonan.getIdPermohonan());

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if(senaraiHakmilikPermohonan.size() > 0){
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if(hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    if(senaraiPermohonanPihak.size() > 0){
                        permohonanPihak = senaraiPermohonanPihak.get(0);
                        logger.debug(permohonanPihak + "permohonanPihak");
                    }
                }
            }
            
            permohonanAduan=aduanService.findPermohonanAduanByIdMohon(permohonan.getIdPermohonan());
            System.out.println(permohonanAduan);
            if(permohonanAduan!=null){
            }
         }
        return new JSP("pelupusan/maklumatBantahanMahkamahPBRZ.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);
            permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(idPermohonan);
            if(permohonanAduan != null)
                    perihal = permohonanAduan.getPerihal();
            hakmilikMHList = permohonan.getSenaraiHakmilik();
            if(hakmilikMHList.size()>0){
                hakmilikPermohonan =  hakmilikMHList.get(0);
            }
            senaraiPP=aduanService.findPihak(permohonan.getIdPermohonan());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public boolean isCariFlag() {
        return cariFlag;
    }

    public void setCariFlag(boolean cariFlag) {
        this.cariFlag = cariFlag;
    }

    public boolean isTiadaDataFlag() {
        return tiadaDataFlag;
    }

    public void setTiadaDataFlag(boolean tiadaDataFlag) {
        this.tiadaDataFlag = tiadaDataFlag;
    }

    public List<HakmilikPermohonan> getHakmilikMHList() {
        return hakmilikMHList;
    }

    public void setHakmilikMHList(List<HakmilikPermohonan> hakmilikMHList) {
        this.hakmilikMHList = hakmilikMHList;
    }

    public List<HakmilikUrusan> getHakmilikByList() {
        return hakmilikByList;
    }

    public void setHakmilikByList(List<HakmilikUrusan> hakmilikByList) {
        this.hakmilikByList = hakmilikByList;
    }

    public HakmilikUrusan getHakmilikUrusan() {
        return hakmilikUrusan;
    }

    public void setHakmilikUrusan(HakmilikUrusan hakmilikUrusan) {
        this.hakmilikUrusan = hakmilikUrusan;
    }

    public List<HakmilikUrusan> getSenaraiHakmilik() {
        return senaraiHakmilik;
    }

    public void setSenaraiHakmilik(List<HakmilikUrusan> senaraiHakmilik) {
        this.senaraiHakmilik = senaraiHakmilik;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public String getIdPermohonanPengambilan() {
        return idPermohonanPengambilan;
    }

    public void setIdPermohonanPengambilan(String idPermohonanPengambilan) {
        this.idPermohonanPengambilan = idPermohonanPengambilan;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
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

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public String getSelectedIdHM() {
        return selectedIdHM;
    }

    public void setSelectedIdHM(String selectedIdHM) {
        this.selectedIdHM = selectedIdHM;
    }

    public PermohonanAduan getPermohonanAduan() {
        return permohonanAduan;
    }

    public void setPermohonanAduan(PermohonanAduan permohonanAduan) {
        this.permohonanAduan = permohonanAduan;
    }

    public HakmilikPermohonan getHp() {
        return hp;
    }

    public void setHp(HakmilikPermohonan hp) {
        this.hp = hp;
    }

    public PermohonanPihak getPp() {
        return pp;
    }

    public void setPp(PermohonanPihak pp) {
        this.pp = pp;
    }

    public String getSelectedPihak() {
        return selectedPihak;
    }

    public void setSelectedPihak(String selectedPihak) {
        this.selectedPihak = selectedPihak;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public List<PermohonanPihak> getSenaraiPP() {
        return senaraiPP;
    }

    public void setSenaraiPP(List<PermohonanPihak> senaraiPP) {
        this.senaraiPP = senaraiPP;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}
