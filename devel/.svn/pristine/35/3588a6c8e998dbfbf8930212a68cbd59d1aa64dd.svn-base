/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.lelong;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AkaunDAO;
import etanah.dao.CaraBayaranDAO;
import etanah.dao.DokumenKewanganBayaranDAO;
import etanah.dao.DokumenKewanganDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodTransaksiDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.TransaksiDAO;
import etanah.model.CaraBayaran;
import java.util.ArrayList;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 *
 * @author mdizzat.mashrom
 */
@UrlBinding("/lelong/maklumat_bayar_baki")
public class MaklumatBayaranBakiActionBean extends AbleActionBean {

    private HakmilikDAO hakmilikDAO;
    private AkaunDAO accDAO;
    private CaraBayaranDAO caraBayaranDAO;
    private TransaksiDAO transaksiDAO;
    private DokumenKewanganBayaranDAO dokKewBayaranDAO;
    private KodTransaksiDAO kodTransaksiDAO;
    private KodDokumenDAO kodDokumenDAO;
    private DokumenKewanganDAO dokumenKewanganDAO;
    private KodCaraBayaranDAO kodCaraBayaranDAO;
    private KodBankDAO kodBankDAO;

    @Inject
    public MaklumatBayaranBakiActionBean(DokumenKewanganDAO dokumenKewanganDAO, KodDokumenDAO kodDokumenDAO, PermohonanDAO permohonanDAO,
            CaraBayaranDAO caraBayaranDAO, AkaunDAO accDAO, HakmilikDAO hakmilikDAO, TransaksiDAO transaksiDAO,
            DokumenKewanganBayaranDAO dokKewBayaranDAO,
            KodCaraBayaranDAO kodCaraBayaranDAO, KodBankDAO kodBankDAO) {
        this.accDAO = accDAO;
        this.hakmilikDAO = hakmilikDAO;
        this.transaksiDAO = transaksiDAO;
        this.caraBayaranDAO = caraBayaranDAO;
        this.kodDokumenDAO = kodDokumenDAO;
        this.dokumenKewanganDAO = dokumenKewanganDAO;
        this.dokKewBayaranDAO = dokKewBayaranDAO;
        this.kodCaraBayaranDAO = kodCaraBayaranDAO;
        this.kodBankDAO = kodBankDAO;
    }
    private ArrayList<CaraBayaran> senaraiCaraBayaran = new ArrayList<CaraBayaran>();
    private CaraBayaran caraBayaran;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("lelong/Maklumat_bayaran_baki.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("lelong/Maklumat_bayaran_baki.jsp").addParameter("tab", "true");
    }

//    public Resolution addCaraBayar() {
//        if ((hakmilik != null) || (akaun != null)) {
//            search();
//        } else {
//            details();
//        }
//        return new JSP("lelong/Maklumat_bayaran_baki.jsp").addParameter("tab", "true");
//    }

    public CaraBayaran getCaraBayaran() {
        return caraBayaran;
    }

    public void setCaraBayaran(CaraBayaran caraBayaran) {
        this.caraBayaran = caraBayaran;
    }

    public ArrayList<CaraBayaran> getSenaraiCaraBayaran() {
        return senaraiCaraBayaran;
    }

    public void setSenaraiCaraBayaran(ArrayList<CaraBayaran> senaraiCaraBayaran) {
        this.senaraiCaraBayaran = senaraiCaraBayaran;
    }
}
