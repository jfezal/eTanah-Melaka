/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodDokumen;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author User
 */
@UrlBinding("/pelupusan/bayanan_pampasan")
public class BayananPampasanActionBean extends AbleActionBean {
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    private List<PermohonanPihak> mohanPihakList;
    private String idPermohonan;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private Pihak pihak;
    private PermohonanPihak permohonanPihak;
    private Hakmilik hakmilik;
    private PermohonanKertas permohonanKertas;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private PermohonanKertasKandungan permohonanKertasKandungan = new PermohonanKertasKandungan();
    private Pemohon pemohon;
    private InfoAudit ia;
    private Pengguna peng;
    private boolean check = false;
    private String kand0 = "";
    private String kand1 = "";
    private String kand2 = "";
    private String kand3 = "";
    private String kand4 = "";
    private String kand5 = "";
    private String kand6 = "";
    private String kand7 = "";
    private String kand8 = "";

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    private static final Logger LOG = Logger.getLogger(BayananPampasanActionBean.class);



    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if(permohonan.getIdPermohonan()!=null){
                mohanPihakList = new ArrayList<PermohonanPihak>();
                //pemohon = pelupusanService.findPemohon(idPermohonan);
                mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                /*
                 * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                 */
                if(hmList.size()>0){
                    hakmilikPermohonan = hmList.get(0);
                }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan_ref(idPermohonan);
                permohonanRujukanLuar = pelupusanService.findPermohonanRujukanLuar_ref(idPermohonan);
            }
        }
            return new JSP("pelupusan/bayanan_pampasan.jsp").addParameter("tab", "true");
    }


    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
       idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if(permohonan.getIdPermohonan()!=null){
                mohanPihakList = new ArrayList<PermohonanPihak>();
                //pemohon = pelupusanService.findPemohon(idPermohonan);
                mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                /*
                 * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                 */
                if(hmList.size()>0){
                    hakmilikPermohonan = hmList.get(0);
                }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan_ref(idPermohonan);
                permohonanRujukanLuar = pelupusanService.findPermohonanRujukanLuar_ref(idPermohonan);
            }
        }
    }
    public Resolution refreshpage() {
          rehydrate();
          return new RedirectResolution(BayananPampasanActionBean.class, "locate");
      }
    public Resolution updateSimpan() {
        //permohonanPihak = new PermohonanPihak();
        String selectedpihak_ref = getContext().getRequest().getParameter("selectedpihak");
        String[]  selectedpihak_refArray = selectedpihak_ref.split(",");
        for(int i=0; i < selectedpihak_refArray.length; i++){
            String[]  selectedpihak_refArray_ref = selectedpihak_refArray[i].split("_");
            selectedpihak_ref = selectedpihak_refArray_ref[1].toString();
            permohonanPihak = permohonanPihakDAO.findById(Long.parseLong(selectedpihak_ref));
            String idValue = "0";
            if(selectedpihak_refArray_ref[0].toString().equals("") || selectedpihak_refArray_ref[0].toString().equals(" ") || selectedpihak_refArray_ref[0].toString() == null ){
               idValue =  "0";
            }else{
                idValue = selectedpihak_refArray_ref[0].toString();
            }
            permohonanPihak.setNilai(new BigDecimal(idValue));
            pelupusanService.simpanPihak(permohonanPihak.getPihak(),permohonanPihak);
//            permohonanPihakDAO.saveOrUpdate(permohonanPihak);
        }
       //addSimpleMessage("Maklumat telah berjaya disimpan.");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            if(permohonan.getIdPermohonan()!=null){
                mohanPihakList = new ArrayList<PermohonanPihak>();
                //pemohon = pelupusanService.findPemohon(idPermohonan);
                mohanPihakList = pelupusanService.findPermohonanPihak(idPermohonan);
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                /*
                 * TEMPORARY SINCE LALULANG NOT CATER FOR MANY HAKMILIK
                 */
                if(hmList.size()>0){
                    hakmilikPermohonan = hmList.get(0);
                }
//                hakmilikPermohonan = pelupusanService.findHakmilikPermohonan_ref(idPermohonan);
                
                permohonanRujukanLuar = pelupusanService.findPermohonanRujukanLuar_ref(idPermohonan);
            }
        }
        addSimpleMessage("Maklumat Berjaya Disimpan");
         return new JSP("pelupusan/bayanan_pampasan.jsp").addParameter("tab", "true");
    }

     
    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public InfoAudit getIa() {
        return ia;
    }

    public void setIa(InfoAudit ia) {
        this.ia = ia;
    }

    public String getKand0() {
        return kand0;
    }

    public void setKand0(String kand0) {
        this.kand0 = kand0;
    }

    public String getKand1() {
        return kand1;
    }

    public void setKand1(String kand1) {
        this.kand1 = kand1;
    }

    public String getKand2() {
        return kand2;
    }

    public void setKand2(String kand2) {
        this.kand2 = kand2;
    }

    public String getKand3() {
        return kand3;
    }

    public void setKand3(String kand3) {
        this.kand3 = kand3;
    }

    public String getKand4() {
        return kand4;
    }

    public void setKand4(String kand4) {
        this.kand4 = kand4;
    }

    public String getKand5() {
        return kand5;
    }

    public void setKand5(String kand5) {
        this.kand5 = kand5;
    }

    public String getKand6() {
        return kand6;
    }

    public void setKand6(String kand6) {
        this.kand6 = kand6;
    }

    public String getKand7() {
        return kand7;
    }

    public void setKand7(String kand7) {
        this.kand7 = kand7;
    }

    public String getKand8() {
        return kand8;
    }

    public void setKand8(String kand8) {
        this.kand8 = kand8;
    }

    public KodCawanganDAO getKodCawanganDAO() {
        return kodCawanganDAO;
    }

    public void setKodCawanganDAO(KodCawanganDAO kodCawanganDAO) {
        this.kodCawanganDAO = kodCawanganDAO;
    }

    public KodDokumenDAO getKodDokumenDAO() {
        return kodDokumenDAO;
    }

    public void setKodDokumenDAO(KodDokumenDAO kodDokumenDAO) {
        this.kodDokumenDAO = kodDokumenDAO;
    }

    public List<PermohonanPihak> getMohanPihakList() {
        return mohanPihakList;
    }

    public void setMohanPihakList(List<PermohonanPihak> mohanPihakList) {
        this.mohanPihakList = mohanPihakList;
    }

    public PelupusanService getPelupusanService() {
        return pelupusanService;
    }

    public void setPelupusanService(PelupusanService pelupusanService) {
        this.pelupusanService = pelupusanService;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }


    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }




    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }



    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
}


