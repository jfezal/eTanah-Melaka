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
@UrlBinding("/pengambilan/notifikasin9")
public class notifikasin9ActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/notifikasi.jsp").addParameter("tab", "true");
    }
    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasi_suratiringan.jsp").addParameter("tab", "true");
    }
    public Resolution showForm3() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/tandatanganSuratIringan.jsp").addParameter("tab", "true");
    }
    public Resolution showForm4() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiSediaBorangB.jsp").addParameter("tab", "true");
    }
    public Resolution showForm5() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiSemakBorangB.jsp").addParameter("tab", "true");
    }
     public Resolution showForm6() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiTandatanganBorangB.jsp").addParameter("tab", "true");
    }
      public Resolution showForm7() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
      public Resolution showForm8() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
      public Resolution showForm9() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
       public Resolution showForm10() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiSuratPenolakan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm11() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiSuratPenolakan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm12() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/notifikasiBorangD.jsp").addParameter("tab", "true");
    }
      public Resolution showForm13() {
        getContext().getRequest().setAttribute("form", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasiBorangD.jsp").addParameter("tab", "true");
    }
      public Resolution showForm14() {
        getContext().getRequest().setAttribute("form1", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen831a/notifikasiBorangD.jsp").addParameter("tab", "true");
    }
       public Resolution showForm15() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/notifikasiSuratPenolakan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm16() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen831a/notifikasiSuratPenolakan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm17() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiBorangI.jsp").addParameter("tab", "true");
    }
        public Resolution showForm18() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/seksyen4/notifikasiBorangI.jsp").addParameter("tab", "true");
    }
         public Resolution showForm19() {
//        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pengambilan/kemasukan_aduan.jsp").addParameter("tab", "true");
    }
        public Resolution showForm20() {
        getContext().getRequest().setAttribute("form2", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
        public Resolution showForm21() {
        getContext().getRequest().setAttribute("form3", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
        public Resolution showForm22() {
        getContext().getRequest().setAttribute("form11", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
        public Resolution showForm23() {
        getContext().getRequest().setAttribute("form12", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
         public Resolution showForm24() {
        getContext().getRequest().setAttribute("form13", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
         public Resolution showForm25() {
        getContext().getRequest().setAttribute("form10", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
         public Resolution showForm26() {
        getContext().getRequest().setAttribute("form14", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
         public Resolution showForm27() {
        getContext().getRequest().setAttribute("form15", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }
         public Resolution showForm28() {
        getContext().getRequest().setAttribute("form16", Boolean.TRUE);
        return new JSP("pengambilan/melaka/seksyen4/notifikasiprintborang.jsp").addParameter("tab", "true");
    }

}
