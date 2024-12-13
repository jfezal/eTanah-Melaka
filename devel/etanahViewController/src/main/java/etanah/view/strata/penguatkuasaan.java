/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/Spenguatkuasaan")
public class penguatkuasaan extends BasicTabActionBean {

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("strata/urusan_lain.jsp").addParameter("tab", "true");
    }

    public Resolution showForm1() {
        return new JSP("strata/kuatkuasa/kemasukan_maklumatpenguatkuasaan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("strata/kuatkuasa/maklumat_pengadu.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        return new JSP("strata/kuatkuasa/maklumat_skimstrata.jsp").addParameter("tab", "true");
    }

    public Resolution showForm4() {
        return new JSP("strata/kuatkuasa/maklumat_pemilikasal.jsp").addParameter("tab", "true");
    }

    public Resolution showForm5() {
        return new JSP("strata/kuatkuasa/senarai_semakdokumen.jsp").addParameter("tab", "true");
    }

    public Resolution showForm6() {
        return new JSP("strata/kuatkuasa/maklumat_asasaduan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm7() {
        return new JSP("strata/kuatkuasa/maklumat_skimstrata2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm8() {
        return new JSP("strata/kuatkuasa/maklumat_pemilikasal2.jsp").addParameter("tab", "true");
    }

    public Resolution showForm9() {
        return new JSP("strata/kuatkuasa/laporan_siasatan.jsp").addParameter("tab", "true");
    }
}
