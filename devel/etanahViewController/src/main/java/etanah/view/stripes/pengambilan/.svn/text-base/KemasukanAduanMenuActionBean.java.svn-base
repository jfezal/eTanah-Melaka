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
import etanah.model.KodUrusan;
import etanah.dao.KodUrusanDAO;
import etanah.dao.FolderDokumenDAO;
import etanah.dao.DokumenDAO;

import etanah.service.PengambilanAduanService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.PermohonanAduan;
import etanah.model.PermohonanPihak;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KodDokumen;
import etanah.model.KodCaraPermohonan;
import etanah.model.KandunganFolder;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import org.apache.log4j.Logger;
import etanah.sequence.GeneratorIdPermohonan;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/kemasukanaduanMenu")
public class KemasukanAduanMenuActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KemasukanAduanMenuActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    private KodUrusanDAO kodUrusanDAO;
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
    private FolderDokumenDAO folderDokumenDAO;
    @Inject
    private DokumenDAO dokumenDAO;
    @Inject
    private GeneratorIdPermohonan idPermohonanGenerator;
    private FolderDokumen folderDokumen;
    private Dokumen dokumen;

    public Dokumen getDokumen() {
        return dokumen;
    }

    public void setDokumen(Dokumen dokumen) {
        this.dokumen = dokumen;
    }

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }
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
    boolean tiadaDataFlag = false;
    private List<HakmilikUrusan> hakmilikByList;
    private List<HakmilikPermohonan> hakmilikMHList;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
    private Permohonan permohonanSebelum;
    private KodUrusan kodUrusan;
    private String Ku;
    private KodCaraPermohonan caraPermohonan;

    public KodCaraPermohonan getCaraPermohonan() {
        return caraPermohonan;
    }

    public void setCaraPermohonan(KodCaraPermohonan caraPermohonan) {
        this.caraPermohonan = caraPermohonan;
    }

    public String getKu() {
        return Ku;
    }

    public void setKu(String Ku) {
        this.Ku = Ku;
    }

//    public KodUrusan getKodUrusan() {
//        return kodUrusan;
//    }
//
//    public void setKodUrusan(KodUrusan kodUrusan) {
//        this.kodUrusan = kodUrusan;
//    }



    @DefaultHandler
    public Resolution showForm() {
//        return new JSP("pengambilan/kemasukan_aduan_1.jsp").addParameter("tab", "true");

        return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_aduan_menu.jsp");
    }

     public Resolution showForm3() {
           if(permohonan!=null){
            idPermohonanPengambilan=permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonanSebelum=permohonanDAO.findById(idPermohonanPengambilan);
            namaProjek=permohonanSebelum.getSebab();
//            tarikhPermohonan=permohonanSebelum.getKodUrusan().getNama();
            pemohon = aduanService.findPemohonByIdMohon(idPermohonanPengambilan);
            hp =  aduanService.findHPByIdMohon(permohonan.getIdPermohonan());

            pp = aduanService.findPihakByIdMohon(permohonan.getIdPermohonan());
            System.out.println("pp"+pp);

            permohonanAduan=aduanService.findPermohonanAduanByIdMohon(permohonan.getIdPermohonan());
            System.out.println(permohonanAduan);
            if(permohonanAduan!=null){
//                perihal=permohonanAduan.getPerihal();

            }
         }
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/Aduan.jsp");
//        return new JSP("pengambilan/Aduan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idPermohonan = getContext().getRequest().getParameter("permohonan.idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
       

    }

    public Resolution HakmilikPopup(){
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        senaraiHakmilikPermohonan =new ArrayList<HakmilikPermohonan>();
        if (!idHakmilik.isEmpty()) {
            senaraiHakmilikPermohonan = aduanService.getSeneraiPermohonanByidHakmilik(idHakmilik, "SEK4");
            System.out.println("idHakmilik-----"+idHakmilik);

            if (senaraiHakmilikPermohonan.size() < 1){
                addSimpleError("Id Hakmilik Tidak Dijumpai");
            }
        }
        return new JSP("pengambilan/carian_aduan.jsp").addParameter("popup", "true");
    }

    public Resolution searchHakmilik(){
//        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        senaraiHakmilikPermohonan =new ArrayList<HakmilikPermohonan>();
        if (!idHakmilik.isEmpty()) {
            senaraiHakmilikPermohonan = aduanService.getSeneraiPermohonanByidHakmilik(idHakmilik, "SEK4");
            System.out.println("idHakmilik-----"+idHakmilik);
            System.out.println("SenaraiHakmilik-----"+senaraiHakmilikPermohonan.size());

            if (senaraiHakmilikPermohonan.size() < 1){
                addSimpleError("Id Hakmilik Tidak Dijumpai");
            }
        }
      return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_aduan_menu.jsp");
    }


    public Resolution cariHakmilikUrusan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        searchHakmilik();
        if(selectedIdHM !=null){
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedIdHM));
            etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
           kodUrusan = new KodUrusan();
           kodUrusan = kodUrusanDAO.findById("SEK4A");
        if (idPermohonan == null) {
            idPermohonan = idPermohonanGenerator.generate(
                    ctx.getKodNegeri(), peng.getKodCawangan(), kodUrusan);
            permohonan = new Permohonan();
                permohonan.setIdPermohonan(idPermohonan);
                permohonan.setKodUrusan(kodUrusan);
                permohonan.setCawangan(peng.getKodCawangan());
                permohonan.setStatus("AK");
                permohonan.setCaraPermohonan(caraPermohonan);
                permohonan.setSebab("Seksyen 4 Aduan");
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonan.setInfoAudit(ia);
                pengambilanService.simpanIdMohonOSebab(permohonan);
                System.out.println("1");
        }
            permohonan=permohonanDAO.findById(idPermohonan);
            if(hakmilikPermohonan != null){
                pemohon = aduanService.findPemohonByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(hakmilikPermohonan.getPermohonan().getIdPermohonan());
                permohonan.setPermohonanSebelum(hakmilikPermohonan.getPermohonan());
                InfoAudit info = permohonan.getInfoAudit();
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
                permohonan.setInfoAudit(info);
                pengambilanService.simpanIdMohonOSebab(permohonan);
                System.out.println("2");
            }
        }
        System.out.println("3");
        return new JSP("pengambilan/kemasukan_aduan_menu.jsp");
    }

    public Resolution simpan(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        searchHakmilik();
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();


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
        permohonanAduan.setPerihal(getContext().getRequest().getParameter("permohonanAduan.perihal"));
        aduanService.simpanPA(permohonanAduan);

        if(selectedPihak !=null && selectedIdHM != null){
            hakmilikPermohonan = hakmilikPermohonanDAO.findById(Long.valueOf(selectedIdHM));
            if(hakmilikPermohonan != null){
                pp = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(permohonan.getIdPermohonan(),hakmilikPermohonan.getHakmilik().getIdHakmilik(),Long.valueOf(selectedPihak));
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
                    pp.setPihak(pihakDAO.findById(Long.valueOf(selectedPihak)));
                    pp.setCawangan(hakmilikPermohonan.getPermohonan().getCawangan());
                    HakmilikPihakBerkepentingan hp = pengambilanService.getHakmilikPihakByidPihak(hakmilikPermohonan.getHakmilik().getIdHakmilik(), Long.valueOf(selectedPihak));
                    pp.setJenis(hp.getJenis());
                    pp.setInfoAudit(info);
                }
                aduanService.simpanPP(pp);

                hp =  pengambilanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(),hakmilikPermohonan.getHakmilik().getIdHakmilik());

                if(hp==null) {
                    hp= new HakmilikPermohonan();
                    InfoAudit ia = new InfoAudit();
                    ia.setDimasukOleh(peng);
                    ia.setTarikhMasuk(new java.util.Date());
                    hp.setInfoAudit(ia);
                    hp.setPermohonan(permohonan);
                    hp.setHakmilik(hakmilikPermohonan.getHakmilik());
                    hp.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
                    hp.setKodUnitLuas(hakmilikPermohonan.getKodUnitLuas());
                    aduanService.simpanHP(hp);
                }
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
//        return new JSP("pengambilan/kemasukan_aduan_1.jsp").addParameter("tab", "true");
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_aduan_menu.jsp");
    }

    public Resolution createPermohonan(){
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
//        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        searchHakmilik();
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
           kodUrusan = new KodUrusan();
           kodUrusan = kodUrusanDAO.findById("SEK4A");
        if (idPermohonan == null) {
            idPermohonan = idPermohonanGenerator.generate(
                    ctx.getKodNegeri(), peng.getKodCawangan(), kodUrusan);
            permohonan = new Permohonan();
                permohonan.setIdPermohonan(idPermohonan);
                permohonan.setKodUrusan(kodUrusan);
                permohonan.setCawangan(peng.getKodCawangan());
                permohonan.setStatus("AK");
                permohonan.setCaraPermohonan(caraPermohonan);
                permohonan.setSebab("Seksyen 4 Aduan");
                InfoAudit ia = new InfoAudit();
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new java.util.Date());
                permohonan.setInfoAudit(ia);
                pengambilanService.simpanIdMohonOSebab(permohonan);
        }
        else
        {
            permohonan = permohonanDAO.findById(idPermohonan);
//            pemohon = aduanService.findPemohonByIdMohon(idPermohonan);
//            permohonanAduan =  aduanService.findPermohonanAduanByIdMohon(idPermohonan);
//            hakmilikMHList = permohonan.getSenaraiHakmilik();
//            if(hakmilikMHList.size()>0){
//                hakmilikPermohonan =  hakmilikMHList.get(0);
//            }
              folderDokumen = permohonan.getFolderDokumen();
            if (folderDokumen != null) {
                if (folderDokumen.getSenaraiKandungan() != null) {
                    for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
                        if (kf == null || kf.getDokumen() == null) {
                            continue;
                        }
                        Dokumen d = dokumenDAO.findById(kf.getDokumen().getIdDokumen());
                         InfoAudit ia = new InfoAudit();
                        d.setInfoAudit(ia);
                        kf.setDokumen(d);
//                        senaraiKandungan.add(kf);
                    }
                }
                permohonan.setFolderDokumen(folderDokumen);
            }

        }
            return new ForwardResolution("/WEB-INF/jsp/pengambilan/kemasukan_aduan_menu.jsp");
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




}
