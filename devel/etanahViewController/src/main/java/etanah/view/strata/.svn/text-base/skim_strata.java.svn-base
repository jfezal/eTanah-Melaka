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
import etanah.model.strata.BadanPengurusan;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import etanah.view.strata.skim_strata;
import java.text.SimpleDateFormat;
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
@UrlBinding("/strata/maklumat_skim")
public class skim_strata extends AbleActionBean {

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
    private List<PermohonanBangunan> permohonanBangunanProvisionalList;
    private int bilblok;
    private long bilPetakKekal = 0;
    private long bilPetakProv = 0;
    private int bilbloksementara = 0;
    private PermohonanBangunan permohonanBangunan;
    private boolean hidBlokSem = Boolean.FALSE;
    private boolean negeri9 = Boolean.FALSE;
    @Inject
    etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(skim_strata.class);
    private BadanPengurusan badananPengurusan;
    private String noBukuDaftarStrata;
    private Date tarikhDaftar;
    SimpleDateFormat sdfoct = new SimpleDateFormat("dd/MM/yyyy");

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

    public Resolution showForm2() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        //listMohonSkim = strService.getListSkim(idPermohonan, "PBBS");

        permohonan = permohonanDAO.findById(idPermohonan);
        System.out.println("--current Idpermohonan--" + permohonan.getIdPermohonan());
        Permohonan idsebelum = permohonan.getPermohonanSebelum();
        
        if (idsebelum != null)  {
            System.out.println("--Previous Idpermohonan--" + idsebelum.getIdPermohonan());
            mohonHakmilik = strService.findMohonHakmilikByAsc(idsebelum.getIdPermohonan());
            System.out.println("--ID mohon from mohonHakmilik--" + mohonHakmilik.getPermohonan().getIdPermohonan());
            System.out.println("--Hakmilik from mohonHakmilik--" + mohonHakmilik.getHakmilik().getIdHakmilik());
            System.out.println("--ID MH--" + mohonHakmilik.getId());

            if (mohonHakmilik != null) {
                mohonSkim = strService.getermohonanSkimList(mohonHakmilik.getId());
                System.out.println("--mohonSkim--" + mohonSkim);

                //ida add 13-07-2013 
                if (conf.getProperty("kodNegeri").equals("05")) {
                    permohonanBangunanList = strService.findByIDMohonBlok(idsebelum.getIdPermohonan());
                    List<PermohonanBangunan> senaraiBangunan = strService.findByIDMohonBlok(idsebelum.getIdPermohonan());
                    if (!permohonanBangunanList.isEmpty()) {
                        bilPetakKekal = strService.getBilPetak(idsebelum.getIdPermohonan(), "B");
                    }
                    LOG.info("petak -----:" + bilPetakKekal);
                }

                if (conf.getProperty("kodNegeri").equals("04")) {
                    permohonanBangunanList = strService.findByIDPermohonan(idsebelum.getIdPermohonan());
                }

                System.out.println("--permohonanBangunan--List--" + permohonanBangunanList.size());
                if (!permohonanBangunanList.isEmpty()) {
                    System.out.println("--permohonanBangunan--List--not empty");
                    bilblok = permohonanBangunanList.size();
                    permohonanBangunan = permohonanBangunanList.get(0);
                }

                LOG.info("Jumlah Bangunan -----:" + bilblok);

                if (conf.getProperty("kodNegeri").equals("05")) {
                    permohonanBangunanProvisionalList = strService.findByIDMohonByProvisional(idsebelum.getIdPermohonan());
                    if (!permohonanBangunanProvisionalList.isEmpty()) {
                        bilPetakProv = strService.getBilPetak(idsebelum.getIdPermohonan(), "P");
                    }
                }
                if (conf.getProperty("kodNegeri").equals("04")) {
                    permohonanBangunanProvisionalList = strService.findByIDPermohonanByProvisional(idsebelum.getIdPermohonan());
                }

                System.out.println("--permohonanBangunanProvisionalList----" + permohonanBangunanProvisionalList.size());
                bilbloksementara = permohonanBangunanProvisionalList.size();
            }
        } else {
            
            badananPengurusan = strService.findBdnByIdHmInduk(mohonHakmilik.getHakmilik().getIdHakmilik());
            noBukuDaftarStrata = strService.findNoBukuByIdHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik());
            tarikhDaftar = strService.findDateByIdHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik());
            bilblok = strService.findBlokByIDHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik()).size();
            bilPetakKekal = strService.findPetakByIDHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik()).size();
            bilbloksementara = strService.findBlokProvisionalByIDHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik()).size();
            bilPetakProv = strService.findPetakProvisionalByIDHakmilik(mohonHakmilik.getHakmilik().getIdHakmilik()).size();
            
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


        /*
         Permohonan permohonanLama = new Permohonan();
         List<HakmilikPermohonan> listPermohonan = strService.findPermohonanIdHak(mohonHakmilik.getHakmilik().getIdHakmilik(), "L");
         for (HakmilikPermohonan p : listPermohonan) {
         if (p.getPermohonan().getKodUrusan().getKod().equals("PBBS")) {
         System.out.println("--if PBBS--");
         if (p.getPermohonan().getKeputusan().getKod().equals("L")) {
         permohonanLama = p.getPermohonan();
         System.out.println("--permohonanLama--PBBS:" + permohonanLama.getIdPermohonan());
         }
         }
         if (p.getPermohonan().getKodUrusan().getKod().equals("PBBD")) {
         System.out.println("--if PBBD--");
         if (p.getPermohonan().getKeputusan().getKod().equals("L")) {
         permohonanLama = p.getPermohonan();
         System.out.println("--permohonanLama--PBBD:" + permohonanLama.getIdPermohonan());
         }
         }
         if (p.getPermohonan().getKodUrusan().getKod().equals("PBS")) {
         System.out.println("--if PBS--");
         if (p.getPermohonan().getKeputusan().getKod().equals("L")) {
         permohonanLama = p.getPermohonan();
         System.out.println("--permohonanLama--PBS:" + permohonanLama.getIdPermohonan());
         }
         }

         if (p.getPermohonan().getKodUrusan().getKod().equals("PNSS")) {
         System.out.println("--if PNSS--");
         if (p.getPermohonan().getKeputusan().getKod().equals("L")) {
         System.out.println("--if PNSS--if keputusan--L");
         System.out.println("--permohonan--" + p.getPermohonan());
         permohonanLama = p.getPermohonan();
         System.out.println("--permohonanLama--PNSS" + permohonanLama.getIdPermohonan());
         }
         }
         }
         System.out.println("--permohonanLama--:" + permohonanLama.getIdPermohonan());
         if (permohonanLama != null) {
         System.out.println("--permohonanLama--not null--");
         if (mohonHakmilik != null) {
         mohonSkim = strService.getermohonanSkimList(mohonHakmilik.getId());
         System.out.println("--mohonSkim--" + mohonSkim);
         }
         permohonanBangunanList = strService.findByIDPermohonan(idPermohonan);
         System.out.println("--permohonanBangunan--List--" + permohonanBangunanList);

         if (!permohonanBangunanList.isEmpty()) {
         System.out.println("--permohonanBangunan--List--not empty");
         bilblok = permohonanBangunanList.size();
         permohonanBangunan = permohonanBangunanList.get(0);
         }
         } */
        return new JSP("strata/skim/maklumat_skimstrata.jsp").addParameter("tab", "true");
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

    public long getBilPetakKekal() {
        return bilPetakKekal;
    }

    public void setBilPetakKekal(long bilPetakKekal) {
        this.bilPetakKekal = bilPetakKekal;
    }

    public long getBilPetakProv() {
        return bilPetakProv;
    }

    public void setBilPetakProv(long bilPetakProv) {
        this.bilPetakProv = bilPetakProv;
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

    public void setBilbloksementara(int bilbloksementara) {
        this.bilbloksementara = bilbloksementara;
    }

    public BadanPengurusan getBadananPengurusan() {
        return badananPengurusan;
    }

    public void setBadananPengurusan(BadanPengurusan badananPengurusan) {
        this.badananPengurusan = badananPengurusan;
    }

    public String getNoBukuDaftarStrata() {
        return noBukuDaftarStrata;
    }

    public void setNoBukuDaftarStrata(String noBukuDaftarStrata) {
        this.noBukuDaftarStrata = noBukuDaftarStrata;
    }

    public Date getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(Date tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }
}
