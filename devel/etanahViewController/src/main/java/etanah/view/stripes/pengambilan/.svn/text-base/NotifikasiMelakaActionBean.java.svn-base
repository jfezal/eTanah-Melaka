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
@UrlBinding("/pengambilan/notifikasiMelaka")
public class NotifikasiMelakaActionBean extends AbleActionBean {

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/penarikanbalik/notifikasi_TandatanganWartaPbNSuratIringan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("pengambilan/melaka/penarikanbalik/notifikasi_ImbasSuratIringanNWarta.jsp").addParameter("tab", "true");
    }

    public Resolution maklumPenolongPegawaiTanah() {
        return new JSP("pengambilan/melaka/pemulihantanah/notifikasiMaklumPPTD.jsp").addParameter("tab", "true");
    }

    public Resolution cetakNotaPerbincangan() {
        return new JSP("pengambilan/melaka/pemulihantanah/notifikasiCetakNotaPerbincangan.jsp").addParameter("tab", "true");
    }

    public Resolution janaSuratPanggilBicara() {
        return new JSP("pengambilan/melaka/pemulihantanah/suratMaklumPanggilBicara.jsp").addParameter("tab", "true");
    }

    public Resolution maklumKeputusanBicara() {
        return new JSP("pengambilan/melaka/pemulihantanah/maklumKeputusanBicara.jsp").addParameter("tab", "true");
    }

    public Resolution suratMintaUlasanJPPH() {
        return new JSP("pengambilan/suratMintaUlasanJPPH.jsp").addParameter("tab", "true");
    }

    public Resolution suratMintaUnitPentadbiranSediaSuratArahanBayaran() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/notifikasiMaklumPT_Kewangan_UntPentadbiran.jsp").addParameter("tab", "true");
    }

    public Resolution notifikasiSuratMaklumanJumlahPampasan() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/notifikasiSuratMaklumanJumlahPampasan.jsp").addParameter("tab", "true");
    }
    
}
