/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.utility;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.model.KodJabatan;
import etanah.model.KodUrusan;
import etanah.service.PelupusanService;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author nurashidah
 */
@UrlBinding("/utiliti/carianPermohonan")
public class CarianPermohonanActionBean extends AbleActionBean {

     @Inject
     PelupusanService pelupusanService;
     private List<KodJabatan> senaraiKodJabatan;
     private List<KodUrusan> senaraiKodUrusan;
     private String jabatan;
     private String urusan;

     @DefaultHandler
     public Resolution showForm() {

          return new ForwardResolution("/WEB-INF/jsp/utiliti/laporanPermohonan.jsp");
     }

     @Before(stages = {LifecycleStage.BindingAndValidation})
     public void rehydrate() {
          System.out.println("------------rehydrate--------------");
          senaraiKodJabatan = pelupusanService.cariKodJabatanByKod();
          System.out.println("------------senaraiKodJabatan--------------" + senaraiKodJabatan.size());
          senaraiKodUrusan = pelupusanService.findKodUrusanByJabatan(senaraiKodJabatan.get(0).getKod());
          System.out.println("------------senaraiKodUrusan--------------" + senaraiKodUrusan.size());
     }

     public Resolution searchPermohonan() throws NamingException {

          return new ForwardResolution("/WEB-INF/jsp/utiliti/laporanPermohonan.jsp");
     }

     public Resolution selectJabatan() throws NamingException {

          System.out.println("------------selectJabatan --------------");
          System.out.println("------------urusan--------------" + senaraiKodJabatan.get(0).getKod());

          senaraiKodUrusan = pelupusanService.findKodUrusanByJabatan(senaraiKodJabatan.get(0).getKod());
          System.out.println("------------senaraiKodUrusan--------------" + senaraiKodUrusan.size());
          return new ForwardResolution("/WEB-INF/jsp/utiliti/laporanPermohonan.jsp");
     }

     public String getJabatan() {
          return jabatan;
     }

     public void setJabatan(String jabatan) {
          this.jabatan = jabatan;
     }

     public List<KodJabatan> getSenaraiKodJabatan() {
          return senaraiKodJabatan;
     }

     public void setSenaraiKodJabatan(List<KodJabatan> senaraiKodJabatan) {
          this.senaraiKodJabatan = senaraiKodJabatan;
     }

     public List<KodUrusan> getSenaraiKodUrusan() {
          return senaraiKodUrusan;
     }

     public void setSenaraiKodUrusan(List<KodUrusan> senaraiKodUrusan) {
          this.senaraiKodUrusan = senaraiKodUrusan;
     }

     public String getUrusan() {
          return urusan;
     }

     public void setUrusan(String urusan) {
          this.urusan = urusan;
     }

}
