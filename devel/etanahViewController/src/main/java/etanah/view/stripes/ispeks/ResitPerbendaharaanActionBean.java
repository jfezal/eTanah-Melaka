/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.IspeksResitPerbendaharaanDAO;
import etanah.model.Pengguna;
import etanah.model.ispek.IspeksResitPerbendaharaan;
import etanah.service.ispeks.IspeksService;
import etanah.service.ispeks.ResitPerbendaharaanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/ispeks/resit_perbendaharaan")
public class ResitPerbendaharaanActionBean extends AbleActionBean {

    @Inject
    IspeksService ispeksService;
    @Inject
    IspeksResitPerbendaharaanDAO ispeksResitPerbendaharaanDAO;
    @Inject
    ResitPerbendaharaanService resitPerbendaharaanService;
    List<IspeksResitPerbendaharaan> listResit;
    String noPenyata;
    String tarikhMula;
    String tarikhAkhir;

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (validateParam()) {
            listResit = resitPerbendaharaanService.findResitParam(noPenyata, tarikhMula, tarikhAkhir, pengguna.getKodCawangan().getKod());
        }
        return new JSP("ispeks/cetak_resit_perbendaharaan.jsp");
    }

    private boolean validateParam() {
        if (!StringUtils.isEmpty(tarikhMula)) {
            if (StringUtils.isEmpty(tarikhAkhir)) {
                addSimpleError("SIla Masukkan Tarikh Akhir");
                return false;
            }
        }
        if (!StringUtils.isEmpty(tarikhAkhir)) {
            if (StringUtils.isEmpty(tarikhMula)) {
                addSimpleError("SIla Masukkan Tarikh Mula");
                return false;
            }
        }
        return true;
    }

    public List<IspeksResitPerbendaharaan> getListResit() {
        return listResit;
    }

    public void setListResit(List<IspeksResitPerbendaharaan> listResit) {
        this.listResit = listResit;
    }

    public String getNoPenyata() {
        return noPenyata;
    }

    public void setNoPenyata(String noPenyata) {
        this.noPenyata = noPenyata;
    }

    public String getTarikhMula() {
        return tarikhMula;
    }

    public void setTarikhMula(String tarikhMula) {
        this.tarikhMula = tarikhMula;
    }

    public String getTarikhAkhir() {
        return tarikhAkhir;
    }

    public void setTarikhAkhir(String tarikhAkhir) {
        this.tarikhAkhir = tarikhAkhir;
    }

}
