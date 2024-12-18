/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.ispeks;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.model.Pengguna;
import etanah.model.ispek.PenyataPemungut;
import etanah.service.ispeks.PenyataPemungutService;
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
@UrlBinding("/ispeks/senarai_penyata_pemungut")
public class SenaraiPenyataPemungutActionBean extends AbleActionBean {

    List<SenaraiPPForm> senaraiPenyataPemungut;
    String noPenyata;
    String tarikhMula;
    String tarikhAkhir;
    String caraBayar;

    @Inject
    PenyataPemungutService penyataPemungutService;

    @DefaultHandler
    public Resolution showForm() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (validateParam()) {
            senaraiPenyataPemungut = penyataPemungutService.paparPenyataPemungut(noPenyata, tarikhMula, tarikhAkhir, caraBayar, pengguna.getKodCawangan().getKod());

        }
        return new JSP("ispeks/senarai_penyata_pemungut.jsp");
    }

    public List<SenaraiPPForm> getSenaraiPenyataPemungut() {
        return senaraiPenyataPemungut;
    }

    public void setSenaraiPenyataPemungut(List<SenaraiPPForm> senaraiPenyataPemungut) {
        this.senaraiPenyataPemungut = senaraiPenyataPemungut;
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

    public String getCaraBayar() {
        return caraBayar;
    }

    public void setCaraBayar(String caraBayar) {
        this.caraBayar = caraBayar;
    }

    private boolean validateParam() {
        if (!StringUtils.isEmpty(tarikhMula)) {
            if (StringUtils.isEmpty(tarikhAkhir)) {
                addSimpleError("SIla Masukkantarikh akhir");
                return false;
            }
        }
        if (!StringUtils.isEmpty(tarikhAkhir)) {
            if (StringUtils.isEmpty(tarikhMula)) {
                addSimpleError("SIla Masukkan tarikh mula");
                return false;
            }
        }
        return true;
    }

}
