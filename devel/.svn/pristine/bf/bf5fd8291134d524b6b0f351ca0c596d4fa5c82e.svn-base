/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.Permohonan;
import etanah.model.KodRujukan;
import etanah.service.common.rujukluarpengambilan;
import etanah.service.PengambilanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/notifikasi")
public class notifikasiActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/notifikasi.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiborangA.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
        getContext().getRequest().setAttribute("form17", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
    public Resolution showForm4() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiWartaSeksyen4.jsp").addParameter("tab", "true");
    }
    public Resolution showForm5() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiBorangB.jsp").addParameter("tab", "true");
    }
     public Resolution showForm6() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
     public Resolution showForm7() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiPembetulanWarta.jsp").addParameter("tab", "true");
    }
      public Resolution showForm8() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiPindaanMaklumatTanah.jsp").addParameter("tab", "true");
    }
       public Resolution showForm9() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasiborangEF.jsp").addParameter("tab", "true");
    }
        public Resolution showForm10() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasi_suratiringan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm18() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasi_suratiringan.jsp").addParameter("tab", "true");
    }
         public Resolution showForm11() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiStatusWarta.jsp").addParameter("tab", "true");
    }
          public Resolution showForm12() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiWartaPembetulan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm13() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasiBorangK.jsp").addParameter("tab", "true");
    }
          public Resolution showForm14() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasi_bmilik_rizab.jsp").addParameter("tab", "true");
    }
            public Resolution showForm15() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasi_SuratPampasanTambahan.jsp").addParameter("tab", "true");
    }
           public Resolution showForm16() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasi_PA.jsp").addParameter("tab", "true");

           }
           public Resolution showForm17() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/Notifikasi_TandatanganSuratPampasan.jsp").addParameter("tab", "true");
    }

       public Resolution showForm19() {
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/DerafPerintah.jsp").addParameter("tab", "true");
    }
       public Resolution showForm20() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/DerafPerintah.jsp").addParameter("tab", "true");
    }
         public Resolution showForm21() {
         getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }
       public Resolution showForm22() {
         getContext().getRequest().setAttribute("!edit", Boolean.TRUE);
        return new JSP("pengambilan/PerintahBersih.jsp").addParameter("tab", "true");
    }
       public Resolution showForm23(){
        return new JSP("pengambilan/BorangI.jsp").addParameter("tab", "true");
    }

       public Resolution showForm24(){
           getContext().getRequest().setAttribute("view1", Boolean.TRUE);
        return new JSP("pengambilan/BorangI.jsp").addParameter("tab", "true");
    }
        public Resolution showForm25(){
//           getContext().getRequest().setAttribute("view1", Boolean.TRUE);
        return new JSP("pengambilan/Rekod_maklumPTD.jsp").addParameter("tab", "true");
    }
}
