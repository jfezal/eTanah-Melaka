/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikUrusanDAO;
import etanah.model.HakmilikUrusan;
import etanah.model.Permohonan;
import etanah.model.PermohonanPembetulanHakmilik;
import etanah.service.daftar.PembetulanService;
import java.util.List;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;


/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/carianBU")
public class CarianPembetulanActionBean extends AbleActionBean {
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;

    private List <PermohonanPembetulanHakmilik> mohonBetul;
    private String idPerserahan;
    private String idHakmilik;
    private List <HakmilikUrusan> hakmilikUrusanList;
    private HakmilikUrusan detailUrusan;
    private Permohonan permohonan;
     private static final Logger LOG = Logger.getLogger(CarianPembetulanActionBean.class);

    public HakmilikUrusan getDetailUrusan() {
        return detailUrusan;
    }

    public void setDetailUrusan(HakmilikUrusan detailUrusan) {
        this.detailUrusan = detailUrusan;
    }

     //setter getter

    public String getIdPerserahan() {
        return idPerserahan;
    }

    public void setIdPerserahan(String idPerserahan) {
        this.idPerserahan = idPerserahan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<PermohonanPembetulanHakmilik> getMohonBetul() {
        return mohonBetul;
    }

    public void setMohonBetul(List<PermohonanPembetulanHakmilik> mohonBetul) {
        this.mohonBetul = mohonBetul;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    


   //custom alert
    public void alert(String msg, Object obj){
        JOptionPane.showMessageDialog(null, msg+obj);
    }



    @DefaultHandler
    public Resolution listBetul() {
    return new JSP("daftar/pembetulan/carianPembetulan.jsp");
    }
    public Resolution carianBetul() {
         idPerserahan = getContext().getRequest().getParameter("idPerserahan");
         idHakmilik = getContext().getRequest().getParameter("idHakmilik");
      LOG.info("ID PERSERAHAN :: " + idPerserahan);
      LOG.info("ID HAKMILIK :: " + idHakmilik);
         if(!StringUtils.isBlank(idPerserahan)){
         hakmilikUrusanList = pService.findHakmilikUrusanByidP(idPerserahan);
       }
       else if (!StringUtils.isBlank(idHakmilik)){
        hakmilikUrusanList = pService.findHakmilikUrusanByidH(idHakmilik);
       }
       else{
       alert("Sila Masuk ID Perserahan atau ID Hakmilik","");
       }



        return new JSP("daftar/pembetulan/carianPembetulan.jsp");
    }

   @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

       hakmilikUrusanList = pService.findHakmilikUrusan();
//        alert("", hakmilikUrusanList.size());

     
    }

   public Resolution detail()
   {  idPerserahan = getContext().getRequest().getParameter("idPerserahan");
      detailUrusan = pService.findDetail(idPerserahan);
//      alert("", detailUrusan.getCatatan());
      return new JSP("daftar/pembetulan/detailBetul.jsp");
   }

    
  }
