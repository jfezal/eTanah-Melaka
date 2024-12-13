/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import java.text.ParseException;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import etanah.view.etanahActionBeanContext;
import javax.swing.JOptionPane;
import net.sourceforge.stripes.action.ForwardResolution;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/notifikasiNS")
public class notifikasiNSActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiMaklumPTD.jsp").addParameter("tab", "true");
    }

    public Resolution semakDrafWarta() {
        return new JSP("pengambilan/negerisembilan/penarikanbalik/notifikasiSemakDrafWartaPB.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiMaklumatTerlibat() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiMaklumPermohonan.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiTerimaArahanMaklumatLot() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiTerimaArahanMaklumatLot.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiCetakNTandatanganSuratPanggilPerbicaraan() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiCetakNTandatanganSuratPanggilPerbicaraan.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiCetakNTandatanganSuratArahanPembayaran() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiCetakNTandatanganSuratArahanPembayaran.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiCetakNTandatanganBorangQ() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiCetakNTandatanganBorangQ.jsp").addParameter("tab", "true");
    }

    public Resolution maklumKeputusanBicara() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/maklumKeputusanBicara.jsp").addParameter("tab", "true");
    }

    public Resolution semakSemakSrtKeputusanMahkamah() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/notifikasiSemakSrtKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution sediaSrtKeputusanMahkamah() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/sediaSrtKeputusanMahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution drafSuratIringanPerintahBersih() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/drafSuratIringanPerintahBersih.jsp").addParameter("tab", "true");
    }

    public Resolution semakanSuratIringanPerintahBersih() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/semakanSuratIringanPerintahBersih.jsp").addParameter("tab", "true");
    }

    public Resolution semakanSuratAfidavit() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/semakanSuratAfidavit.jsp").addParameter("tab", "true");
    }

    public Resolution drafSuratUlasanJabatanTeknikal() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/drafSuratUlasanJabatanTeknikal.jsp").addParameter("tab", "true");
    }

    public Resolution keTabKeputusan() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/tabKeputusan.jsp").addParameter("tab", "true");
    }

    public Resolution suratMaklumAmbikGeran() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/suratMaklumPengambilanGeran.jsp").addParameter("tab", "true");
    }

    public Resolution arahan_hakLaluLalangAwam() {
        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/arahan_hakLaluLalangAwam.jsp").addParameter("tab", "true");
    }
}
