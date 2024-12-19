/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Permohonan;
import etanah.model.PermohonanBangunan;
import etanah.model.PermohonanSkim;
import etanah.model.PermohonanStrata;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.strata.skim_strata;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/maklumat_skim_N9")
public class skim_strata_N9 extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    private List<PermohonanSkim> listMohonSkim;
    private Permohonan permohonan;
    private HakmilikPermohonan mohonHakmilik;
    private PermohonanSkim mohonSkim;
    private List<HakmilikPermohonan> listMohonhakmilik = new ArrayList();
    private PermohonanStrata permohonanStrata = new PermohonanStrata();
    List<Hakmilik> listHakmilik = new ArrayList();
    //added
    private List<PermohonanBangunan> permohonanBangunanList;
    private List<PermohonanBangunan> permohonanBangunanListLanded;
    private List<PermohonanBangunan> permohonanBangunanProvisionalList;
    private int bilblok;
    private String noBuku;
    private int bilPetakKekal = 0;
    private int bilPetakTanah = 0;
    private Date tarikhDaftarStrata;
    private int bilPetakProv = 0;
    private int bilbloksementara = 0;
    private PermohonanBangunan permohonanBangunan;
    private boolean hidBlokSem = Boolean.FALSE;
    private boolean negeri9 = Boolean.FALSE;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(skim_strata.class);

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//        listMohonhakmilik = strService.getMaklumatTan(idPermohonan);
        mohonHakmilik = strService.findMohonHakmilik(idPermohonan);
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/urusan_lain.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("strata/skim/kemasukan_maklumat.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPNSSN9() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //listMohonSkim = strService.getListSkim(idPermohonan, "PBBS");

        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("--current Idpermohonan--" + permohonan.getIdPermohonan());
        Permohonan idsebelum = permohonan.getPermohonanSebelum();
        System.out.println("--Previous Idpermohonan--" + idsebelum.getIdPermohonan());
        mohonHakmilik = strService.findMohonHakmilikByAsc(idsebelum.getIdPermohonan());


        // Hakmilik hm = strService.findInfoByIdHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik());
        List<Hakmilik> hmstrata = strService.findIdHakmilikByIdHakmilikInduk(mohonHakmilik.getHakmilik().getIdHakmilik());

        tarikhDaftarStrata = hmstrata.get(0).getTarikhDaftar();
        noBuku = hmstrata.get(0).getNoBukuDaftarStrata();

        LOG.info("--ID mohon from mohonHakmilik--" + mohonHakmilik.getPermohonan().getIdPermohonan());
        LOG.info("--Hakmilik from mohonHakmilik--" + mohonHakmilik.getHakmilik().getIdHakmilik());
        LOG.info("--ID MH--" + mohonHakmilik.getId());

        if (mohonHakmilik != null) {
            mohonSkim = strService.getermohonanSkimList(mohonHakmilik.getId());
            System.out.println("--mohonSkim--" + mohonSkim);

            permohonanBangunanList = strService.findByIDMohonBlok(idsebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunan = strService.findByIDMohonBlok(idsebelum.getIdPermohonan());
            if (!permohonanBangunanList.isEmpty()) {
                for (PermohonanBangunan pb : senaraiBangunan) {
                    bilPetakKekal = bilPetakKekal + pb.getBilanganPetak();
                }
            }

            permohonanBangunanListLanded = strService.findByIDPermohonanByLandparcel(idsebelum.getIdPermohonan());
            List<PermohonanBangunan> senaraiBangunanL = strService.findByIDMohonByLanded(idsebelum.getIdPermohonan());
            if (!permohonanBangunanListLanded.isEmpty()) {
                for (PermohonanBangunan pbL : senaraiBangunanL) {
                    bilPetakTanah = bilPetakTanah + pbL.getBilanganPetak();
                }
                // bilPetakTanah = strService.getBilPetakL(idsebelum.getIdPermohonan(), "L");
            }


            LOG.info("id Permohonan Asal -----:" + idsebelum.getIdPermohonan());
            LOG.info("petak Kekal -----:" + bilPetakKekal);
            LOG.info("petak Tanah -----:" + bilPetakTanah);



            System.out.println("--permohonanBangunan--List--" + permohonanBangunanList.size());
            if (!permohonanBangunanList.isEmpty()) {
                System.out.println("--permohonanBangunan--List--not empty");
                bilblok = permohonanBangunanList.size();
                permohonanBangunan = permohonanBangunanList.get(0);
            }

            LOG.info("Jumlah Bangunan -----:" + bilblok);


            permohonanBangunanProvisionalList = strService.findByIDMohonByProvisional(idsebelum.getIdPermohonan());
            if (!permohonanBangunanProvisionalList.isEmpty()) {
                for (PermohonanBangunan pbp : permohonanBangunanProvisionalList) {
                    bilPetakProv = bilPetakProv + pbp.getBilanganPetak();
                }
            }



            System.out.println("--permohonanBangunanProvisionalList----" + permohonanBangunanProvisionalList.size());
            bilbloksementara = permohonanBangunanProvisionalList.size();
        }

        if (conf.getProperty("kodNegeri").equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("PBS") || permohonan.getKodUrusan().getKod().equals("PBTS")) {
                hidBlokSem = true;
            }
        } else {
            hidBlokSem = true;
        }


        if (conf.getProperty("kodNegeri").equals("05")) {
            negeri9 = true;
        }




        return new JSP("strata/skim/maklumat_skimstrata_PNSS.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("strata/skim/maklumat_skimstrata2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("strata/skim/senarai_semakdokumen.jsp").addParameter("tab", "true");
    }

    public boolean isHidBlokSem() {
        return hidBlokSem;
    }

    public void setHidBlokSem(boolean hidBlokSem) {
        this.hidBlokSem = hidBlokSem;
    }

    public boolean isNegeri9() {
        return negeri9;
    }

    public void setNegeri9(boolean negeri9) {
        this.negeri9 = negeri9;
    }

    public List<PermohonanSkim> getListMohonSkim() {
        return listMohonSkim;
    }

    public void setListMohonSkim(List<PermohonanSkim> listMohonSkim) {
        this.listMohonSkim = listMohonSkim;
    }

    public List<HakmilikPermohonan> getListMohonhakmilik() {
        return listMohonhakmilik;
    }

    public void setListMohonhakmilik(List<HakmilikPermohonan> listMohonhakmilik) {
        this.listMohonhakmilik = listMohonhakmilik;
    }

    public HakmilikPermohonan getMohonHakmilik() {
        return mohonHakmilik;
    }

    public void setMohonHakmilik(HakmilikPermohonan mohonHakmilik) {
        this.mohonHakmilik = mohonHakmilik;
    }

    public PermohonanSkim getMohonSkim() {
        return mohonSkim;
    }

    public void setMohonSkim(PermohonanSkim mohonSkim) {
        this.mohonSkim = mohonSkim;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanStrata getPermohonanStrata() {
        return permohonanStrata;
    }

    public void setPermohonanStrata(PermohonanStrata permohonanStrata) {
        this.permohonanStrata = permohonanStrata;
    }

    public List<Hakmilik> getListHakmilik() {
        return listHakmilik;
    }

    public void setListHakmilik(List<Hakmilik> listHakmilik) {
        this.listHakmilik = listHakmilik;
    }

    public int getBilblok() {
        return bilblok;
    }

    public void setBilblok(int bilblok) {
        this.bilblok = bilblok;
    }

    public PermohonanBangunan getPermohonanBangunan() {
        return permohonanBangunan;
    }

    public void setPermohonanBangunan(PermohonanBangunan permohonanBangunan) {
        this.permohonanBangunan = permohonanBangunan;
    }

    public List<PermohonanBangunan> getPermohonanBangunanList() {
        return permohonanBangunanList;
    }

    public void setPermohonanBangunanList(List<PermohonanBangunan> permohonanBangunanList) {
        this.permohonanBangunanList = permohonanBangunanList;
    }

    public List<PermohonanBangunan> getPermohonanBangunanProvisionalList() {
        return permohonanBangunanProvisionalList;
    }

    public void setPermohonanBangunanProvisionalList(List<PermohonanBangunan> permohonanBangunanProvisionalList) {
        this.permohonanBangunanProvisionalList = permohonanBangunanProvisionalList;
    }

    public int getBilbloksementara() {
        return bilbloksementara;
    }

    public List<PermohonanBangunan> getPermohonanBangunanListLanded() {
        return permohonanBangunanListLanded;
    }

    public void setPermohonanBangunanListLanded(List<PermohonanBangunan> permohonanBangunanListLanded) {
        this.permohonanBangunanListLanded = permohonanBangunanListLanded;
    }

    public String getNoBuku() {
        return noBuku;
    }

    public void setNoBuku(String noBuku) {
        this.noBuku = noBuku;
    }

    public int getBilPetakKekal() {
        return bilPetakKekal;
    }

    public void setBilPetakKekal(int bilPetakKekal) {
        this.bilPetakKekal = bilPetakKekal;
    }

    public int getBilPetakTanah() {
        return bilPetakTanah;
    }

    public void setBilPetakTanah(int bilPetakTanah) {
        this.bilPetakTanah = bilPetakTanah;
    }

    public int getBilPetakProv() {
        return bilPetakProv;
    }

    public void setBilPetakProv(int bilPetakProv) {
        this.bilPetakProv = bilPetakProv;
    }

    public Date getTarikhDaftarStrata() {
        return tarikhDaftarStrata;
    }

    public void setTarikhDaftarStrata(Date tarikhDaftarStrata) {
        this.tarikhDaftarStrata = tarikhDaftarStrata;
    }

    public void setBilbloksementara(int bilbloksementara) {
        this.bilbloksementara = bilbloksementara;
    }
}
