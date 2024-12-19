/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.portal;

import able.stripes.JSP;
import com.theta.portal.stripes.BaseActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/jabatan_teknikal")
public class JabatanTeknikalActionBean extends BaseActionBean {

    @DefaultHandler
    public Resolution mainPage() {
        return new JSP("/portal/tentang_etanah.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }

    public Resolution statusTukarGanti() {
        return new JSP("/portal/semakan_status_tukar_ganti.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }

    public Resolution statusPermohonan() {
        return new JSP("/portal/semakan_status_permohonan.jsp");
//        E:\kejeEtanah\trunk\devel\portal\src\main\webapp\WEB-INF\jsp\portal\tentangEtanah.jsp
    }
}
