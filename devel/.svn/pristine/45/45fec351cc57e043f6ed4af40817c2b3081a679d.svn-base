/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.sek8;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPengambilanHakmilikDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.service.acq.service.BorangEFService;
import etanah.service.ambil.PengambilanAPTService;
import etanah.view.BasicTabActionBean;
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
@UrlBinding("/pengambilan/sedia_borangK")
public class MaklumatBorangKActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPengambilanHakmilikDAO permohonanPengambilanHakmilikDAO;
    @Inject
    BorangEFService borangEFService;
    @Inject
    PengambilanAPTService pengambilanAPTService;
    List<PermohonanPengambilanHakmilik> listAmbilHakmilik;
    Permohonan permohonan;
    String[] flagAmbilSebahagian;
    String[] jumHakmilikBaru;
    String[] idAmbilHakmilik;

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        listAmbilHakmilik = pengambilanAPTService.findHakmilikAktifNotTDK(idPermohonan); 
        return new JSP("/pengambilan/APT/maklumat_borang_k.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        jumHakmilikBaru = getContext().getRequest().getParameterValues("jumHakmilikBaru2");
        flagAmbilSebahagian = getContext().getRequest().getParameterValues("flagAmbilSebahagian2");
        idAmbilHakmilik = getContext().getRequest().getParameterValues("idAmbilHakmilik2");
        for (int i = 0; i < idAmbilHakmilik.length; i++) {
            PermohonanPengambilanHakmilik ap = permohonanPengambilanHakmilikDAO.findById(Long.valueOf(idAmbilHakmilik[i]));
            if (ap != null) {
                if (StringUtils.isNotBlank(flagAmbilSebahagian[i])) {
                    ap.setFlagAmbilSbgn(flagAmbilSebahagian[i]);
                }
                if (StringUtils.isNotBlank(jumHakmilikBaru[i])) {
                    ap.setJumlahHakmilik(Integer.parseInt(jumHakmilikBaru[i]));
                }
                pengambilanAPTService.saveOrUpdatehakmilikpermohonan(ap);
            }
        }        listAmbilHakmilik = pengambilanAPTService.findHakmilikAktifNotTDK(idPermohonan);

        return new JSP("/pengambilan/APT/maklumat_borang_k.jsp").addParameter("tab", "true");
    }

    public List<PermohonanPengambilanHakmilik> getListAmbilHakmilik() {
        return listAmbilHakmilik;
    }

    public void setListAmbilHakmilik(List<PermohonanPengambilanHakmilik> listAmbilHakmilik) {
        this.listAmbilHakmilik = listAmbilHakmilik;
    }

    public String[] getFlagAmbilSebahagian() {
        return flagAmbilSebahagian;
    }

    public void setFlagAmbilSebahagian(String[] flagAmbilSebahagian) {
        this.flagAmbilSebahagian = flagAmbilSebahagian;
    }

    public String[] getJumHakmilikBaru() {
        return jumHakmilikBaru;
    }

    public void setJumHakmilikBaru(String[] jumHakmilikBaru) {
        this.jumHakmilikBaru = jumHakmilikBaru;
    }

    public String[] getIdAmbilHakmilik() {
        return idAmbilHakmilik;
    }

    public void setIdAmbilHakmilik(String[] idAmbilHakmilik) {
        this.idAmbilHakmilik = idAmbilHakmilik;
    }

}
