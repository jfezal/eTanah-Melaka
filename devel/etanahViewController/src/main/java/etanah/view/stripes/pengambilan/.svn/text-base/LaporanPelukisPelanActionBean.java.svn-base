/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import java.text.SimpleDateFormat;
import etanah.service.PengambilanService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import etanah.model.KodBandarPekanMukim;
/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/laporanpelukispelan")
public class LaporanPelukisPelanActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    private Permohonan permohonan;
    private PermohonanPengambilan permohonanPengambilan;
    @Inject
    PengambilanService service;
    private KodBandarPekanMukim bandarPekanMukim;
    private String projekKerajaan;
    private String rizabMelayu;
    private String rizabHutan;
    private String tanahGSA;
    private String permohonanTerdahulu;
    private String catatan;



 @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/Laporan_PelukisPelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm2() {
        return new JSP("pengambilan/statusTanah_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm3() {
        return new JSP("pengambilan/kedudukanTanah_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm4() {
        return new JSP("pengambilan/kawPBT_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm5() {
        return new JSP("pengambilan/tRizabMelayu_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm6() {
        return new JSP("pengambilan/tRizabHutanSimpan_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm7() {
        return new JSP("pengambilan/kawGSA_ppelan.jsp").addParameter("tab", "true");
 }
 public Resolution showForm8() {
        return new JSP("pengambilan/permohonanTerdahulu_ppelan.jsp").addParameter("tab", "true");
 }

 @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
//        permohonan = ((etanahActionBeanContext) getContext()).getPermohonan();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
//           if (permohonan != null) {
//            permohonanPengambilan = permohonan.getPengambilan();
//           }

          if (idPermohonan != null) {
            permohonanPengambilan = service.findByidPermohonan(idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);
        }

 }



    public Resolution savePelukispelanInfo() {
              String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
              Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
              InfoAudit info = peng.getInfoAudit();
              permohonanPengambilan = service.findByidPermohonan(idPermohonan);
            if (permohonanPengambilan == null) {
              info.setDimasukOleh(peng);
              info.setTarikhMasuk(new java.util.Date());
              permohonanPengambilan = new PermohonanPengambilan();
              permohonanPengambilan.setBandarPekanMukim(bandarPekanMukim);
              permohonanPengambilan.setPermohonan(permohonan);
              permohonanPengambilan.setRizabHutan(rizabHutan);
              permohonanPengambilan.setRizabMelayu(rizabMelayu);
              permohonanPengambilan.setPermohonanTerdahulu(permohonanTerdahulu);
              permohonanPengambilan.setProjekKerajaan(projekKerajaan);
              permohonanPengambilan.setTanahGSA(tanahGSA);
              permohonan.setCatatan(catatan);
              permohonanPengambilan.setInfoAudit(info);
              service.save(permohonanPengambilan);
              addSimpleMessage("Kemasukan Data Berjaya.");
            } else {
                info.setDiKemaskiniOleh(peng);
                info.setTarikhKemaskini(new java.util.Date());
              permohonanPengambilan.setBandarPekanMukim(bandarPekanMukim);
              permohonanPengambilan.setPermohonan(permohonan);
              permohonanPengambilan.setRizabHutan(rizabHutan);
              permohonanPengambilan.setRizabMelayu(rizabMelayu);
              permohonanPengambilan.setPermohonanTerdahulu(permohonanTerdahulu);
              permohonanPengambilan.setProjekKerajaan(projekKerajaan);
              permohonanPengambilan.setTanahGSA(tanahGSA);
              permohonan.setCatatan(catatan);
                permohonanPengambilan.setInfoAudit(info);
                service.update(permohonanPengambilan);
                addSimpleMessage("Kemaskini Data Berjaya.");
            }
        return new ForwardResolution("/WEB-INF/jsp/pengambilan/Laporan_PelukisPelan.jsp").addParameter("tab", "true");
    }

    


     public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

     public PermohonanPengambilan getpermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }
     public KodBandarPekanMukim getBandarPekanMukim() {
        return bandarPekanMukim;
    }

    public void setBandarPekanMukim(KodBandarPekanMukim bandarPekanMukim) {
        this.bandarPekanMukim = bandarPekanMukim;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getProjekKerajaan() {
        return projekKerajaan;
    }

    public void setProjekKerajaan(String projekKerajaan) {
        this.projekKerajaan = projekKerajaan;
    }

    public String getRizabHutan() {
        return rizabHutan;
    }

    public void setRizabHutan(String rizabHutan) {
        this.rizabHutan = rizabHutan;
    }

    public String getRizabMelayu() {
        return rizabMelayu;
    }

    public void setRizabMelayu(String rizabMelayu) {
        this.rizabMelayu = rizabMelayu;
    }

    public String getTanahGSA() {
        return tanahGSA;
    }

    public String getPermohonanTerdahulu() {
        return permohonanTerdahulu;
    }

    public void setPermohonanTerdahulu(String permohonanTerdahulu) {
        this.permohonanTerdahulu = permohonanTerdahulu;
    }

    public void setTanahGSA(String tanahGSA) {
        this.tanahGSA = tanahGSA;
    }

}
