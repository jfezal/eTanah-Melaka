/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.pencetak;

import able.stripes.AbleActionBean;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.Pengguna;
import etanah.view.etanahActionBeanContext;
import etanah.service.common.PgunaService;

/**
 *
 * @author khairil
 */
@UrlBinding("/pencetak")
public class Pencetak extends AbleActionBean {

    @Inject
    PgunaService pService;
    public Pengguna pengguna;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pencetak/index.jsp");
    }

    public Resolution simpan() {
        if (pengguna.getNamaPencetak1() != null && pengguna.getNamaPencetak2() != null) {
            pService.saveOrUpdate(pengguna);
            addSimpleMessage("Kemaskini Data Berjaya");
        }else{
            addSimpleError("Sila Masukkan Nama Pencetak 1 dan 2");
        }
        return new JSP("pencetak/index.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }
}


