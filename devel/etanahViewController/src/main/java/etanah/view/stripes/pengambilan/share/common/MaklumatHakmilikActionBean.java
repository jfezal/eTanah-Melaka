/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.share.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilanHakmilik;
import etanah.service.acq.service.MaklumatHakmilikService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.form.PengambilanHakmilikForm;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.faidzal
 */
@UrlBinding("/pengambilan/maklumat_hakmilik_apt")
public class MaklumatHakmilikActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(etanah.view.stripes.pengambilan.share.common.MaklumatHakmilikActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    MaklumatHakmilikService maklumatHakmilikService;
    @Inject
    HakmilikPermohonanService hakmilikPermohonanService;
    Hakmilik hakmilik;
    Permohonan permohonan;
    List<PengambilanHakmilikForm> listHakmilikDiambil = new ArrayList<PengambilanHakmilikForm>();

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        Permohonan p = permohonanDAO.findById(idPermohonan);
        listHakmilikDiambil = new ArrayList<PengambilanHakmilikForm>();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                List<HakmilikPermohonan> lhp = hakmilikPermohonanService.findByIdPermohonan(idPermohonan);
                for (HakmilikPermohonan hp : lhp) {

                    PengambilanHakmilikForm form = new PengambilanHakmilikForm();
                    form.setDaerah(hp.getHakmilik().getDaerah().getNama());
                    form.setIdHakmilik(hp.getHakmilik().getIdHakmilik());
                    form.setKegunaanTanah(hp.getHakmilik().getKegunaanTanah().getPerihal());
                    form.setLuas(hp.getHakmilik().getLuas() + "");
//                    if (!hp.getSenaraiHakmilikAmbil().isEmpty()) {
//                        form.setLuasAmbil(hp.getSenaraiHakmilikAmbil().get(0).getLuasAmbil() + "");
//                    }
                    form.setMukim(hp.getHakmilik().getBandarPekanMukim().getNama());
                    form.setNoLotPt(hp.getHakmilik().getNoLot());
                    form.setJumPihak(getJumlahPihak(hp.getHakmilik().getIdHakmilik()).intValue());
                    listHakmilikDiambil.add(form);

                }
            }
        }
        return new JSP("pengambilan/APT/maklumat_hakmilik_pengambilan.jsp").addParameter("tab", "true");
    }

    public Resolution popup() {
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idHakmilik != null) {
            hakmilik = hakmilikDAO.findById(idHakmilik);
        }
        return new JSP("pengambilan/maklumat_asas_tanah_pengambilan.jsp").addParameter("popup", "true").addParameter("showForm", "false");
    }

    public List<PengambilanHakmilikForm> getListHakmilikDiambil() {
        return listHakmilikDiambil;
    }

    public void setListHakmilikDiambil(List<PengambilanHakmilikForm> listHakmilikDiambil) {
        this.listHakmilikDiambil = listHakmilikDiambil;
    }

    private Long getJumlahPihak(String idHakmilik) {
        Long total;
        total = maklumatHakmilikService.findPihakKepentinganBerdaftar(idHakmilik);

        return total;
    }

}
