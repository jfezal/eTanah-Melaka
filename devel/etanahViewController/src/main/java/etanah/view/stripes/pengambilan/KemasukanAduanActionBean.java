/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import java.util.ArrayList;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.KodBandarPekanMukimDAO;
import etanah.dao.PihakDAO;
import etanah.model.Permohonan;
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.model.HakmilikUrusan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.service.PengambilanAduanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanAduan;
import etanah.model.PermohonanPihak;
import etanah.service.PengambilanService;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 * Modified Rajesh
 */
@UrlBinding("/pengambilan/kemasukanaduan")
public class KemasukanAduanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KemasukanAduanActionBean.class);
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
    private Permohonan permohonan;
    private Pemohon pemohon;
    private PermohonanAduan permohonanAduan;
    private Pihak pihak;
    private String idPermohonanPengambilan;
    private String namaProjek;
    private List<HakmilikUrusan> senaraiHakmilik;
    private HakmilikUrusan hakmilikUrusan;
    private HakmilikPermohonan hakmilikPermohonan;
    private HakmilikPermohonan hp;
    private PermohonanPihak pp;
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
    private List<PermohonanPihak> senaraiPP;



    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/kemasukan_aduan.jsp").addParameter("tab", "true");
    }
     public Resolution showForm3() {
           if(permohonan!=null){
            idPermohonanPengambilan=permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum=permohonanDAO.findById(idPermohonanPengambilan);
            namaProjek=permohonanSebelum.getSebab();
//            tarikhPermohonan=permohonanSebelum.getKodUrusan().getNama();
            pemohon = aduanService.findPemohonByIdMohon(idPermohonanPengambilan);
            hp =  aduanService.findHPByIdMohon(permohonan.getIdPermohonan());
            senaraiPP=aduanService.findPihak(idPermohonan);

//            pp = aduanService.findPihakByIdMohon(permohonan.getIdPermohonan());
//            System.out.println("pp"+pp);

            permohonanAduan=aduanService.findPermohonanAduanByIdMohon(permohonan.getIdPermohonan());
            System.out.println(permohonanAduan);
            if(permohonanAduan!=null){
//                perihal=permohonanAduan.getPerihal();

            }
         }
        return new JSP("pengambilan/Aduan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);
            if(permohonan.getSenaraiPemohon().size() > 0)
                    pemohon = permohonan.getSenaraiPemohon().get(0);
            permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(idPermohonan);
            if(permohonanAduan != null)
                    perihal = permohonanAduan.getPerihal();
            hakmilikMHList = permohonan.getSenaraiHakmilik();
            if(hakmilikMHList.size()>0){
                hakmilikPermohonan =  hakmilikMHList.get(0);
            }
        }
    }

    public Resolution HakmilikPopup(){
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        senaraiHakmilikPermohonan =new ArrayList<HakmilikPermohonan>();
        if (!idHakmilik.isEmpty()) {
            senaraiHakmilikPermohonan = aduanService.getSeneraiPermohonanByidHakmilik(idHakmilik, "SEK4");

            if (senaraiHakmilikPermohonan.size() < 1){
                addSimpleError("Id Hakmilik Tidak Dijumpai");
            }
        }else {
            addSimpleError("Sila pilih ' Id Hakmilik : ' terlebih dahulu.");
        }
        return new JSP("pengambilan/carian_aduan.jsp").addParameter("popup", "true");
    }


    public Resolution cariHakmilikUrusan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        if(selectedIdHM !=null){
            hp = hakmilikPermohonanDAO.findById(Long.valueOf(selectedIdHM));
            if(hp != null){
                if(hp.getPermohonan().getSenaraiPemohon().size() > 0)
                    pemohon = hp.getPermohonan().getSenaraiPemohon().get(0);
//                pemohon = aduanService.findPemohonByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(hp.getPermohonan().getIdPermohonan());
                if(permohonanAduan != null)
                    perihal = permohonanAduan.getPerihal();
                permohonan.setPermohonanSebelum(hp.getPermohonan());
                InfoAudit info = permohonan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonan.setInfoAudit(info);
                pengambilanService.simpanIdMohonOSebab(permohonan);

                hakmilikPermohonan =  pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(),hp.getHakmilik().getIdHakmilik());

                if(hakmilikPermohonan==null) {
                    hakmilikPermohonan= new HakmilikPermohonan();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    hakmilikPermohonan.setInfoAudit(ia);
                    hakmilikPermohonan.setPermohonan(permohonan);
                    hakmilikPermohonan.setHakmilik(hp.getHakmilik());
                    hakmilikPermohonan.setLuasTerlibat(hp.getLuasTerlibat());
                    hakmilikPermohonan.setKodUnitLuas(hp.getKodUnitLuas());
                    aduanService.simpanHP(hakmilikPermohonan);
                }
            }
        }

        return new JSP("pengambilan/kemasukan_aduan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");

        permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(permohonan.getIdPermohonan());
        if(permohonanAduan==null) {
            permohonanAduan= new PermohonanAduan();
            InfoAudit ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new java.util.Date());
            permohonanAduan.setInfoAudit(ia);
            permohonanAduan.setPermohonan(permohonan);
            permohonanAduan.setCawangan(permohonan.getCawangan());
            permohonanAduan.setBandarPekanMukim(kodBandarPekanMukimDAO.findById(12));
        } else {
            InfoAudit ia = permohonanAduan.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanAduan.setInfoAudit(ia);
        }
        permohonanAduan.setPerihal(getContext().getRequest().getParameter("perihal"));
        aduanService.simpanPA(permohonanAduan);

        String[] ids = getContext().getRequest().getParameterValues("chkbox");

//        if(selectedPihak !=null && getContext().getRequest().getParameter("hakmilikPermohonan.id") != null){
        if(ids != null && ids.length > 0 && getContext().getRequest().getParameter("hakmilikPermohonan.id") != null){
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(getContext().getRequest().getParameter("hakmilikPermohonan.id")));
            if(hakmilikPermohonan != null){
                for (String id : ids) {
                    pp = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getIdPermohonan(),hakmilikPermohonan.getHakmilik().getIdHakmilik(),Long.valueOf(id));
                    InfoAudit info;
                    if(pp != null){
                        info = pp.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                    }else{
                        pp = new PermohonanPihak();
                        info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        pp.setPermohonan(permohonan);
                        pp.setHakmilik(hakmilikPermohonan.getHakmilik());
                        pp.setPihak(pihakDAO.findById(Long.valueOf(id)));
                        pp.setCawangan(permohonan.getCawangan());
                        HakmilikPihakBerkepentingan hpb = pengambilanService.getHakmilikPihakByidPihak(hakmilikPermohonan.getHakmilik().getIdHakmilik(), Long.valueOf(id));
                        pp.setJenis(hpb.getJenis());
                        
                    }
                    pp.setInfoAudit(info);
                    aduanService.simpanPP(pp);
                }
                

                
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/kemasukan_aduan.jsp").addParameter("tab", "true");
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
     public List<PermohonanPihak> getSenaraiPP() {
        return senaraiPP;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }





}