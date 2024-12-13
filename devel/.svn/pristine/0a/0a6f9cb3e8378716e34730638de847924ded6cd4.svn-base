/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

/**
 *
 * @author mohd.faidzal
 */


import able.stripes.JSP;
import etanah.model.Pengguna;
import etanah.view.BasicTabActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/pengambilan/aduan_kerosakan/maklumat_penerima")
public class MaklumatPenerimaActionBean extends BasicTabActionBean{
        @DefaultHandler
    public Resolution borangAForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        return new JSP("/pengambilan/aduan_kerosakan/maklumat_penerima.jsp").addParameter("tab", "true");
    }
}
