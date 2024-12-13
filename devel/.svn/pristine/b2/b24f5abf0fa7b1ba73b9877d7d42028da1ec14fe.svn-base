/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan.aduan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.ref.pengambilan.sek4aduan.RefPeringkat;
import etanah.service.acq.integrationflow.Sek4AduanIntegrationFlowService;
import etanah.service.pengambilan.aduan.AduanService;
import etanah.view.etanahActionBeanContext;
import etanah.view.stripes.pengambilan.share.common.RekodKpsnMMKActionBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
@UrlBinding("/pengambilan/aduan_kerosakan/rekod_terima_cek")
public class RekodTerimaCekActionBean extends AbleActionBean {

    @Inject
    AduanService aduanService;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    Sek4AduanIntegrationFlowService sek4AduanIntegrationFlowService;
    @Inject
    PermohonanDAO permohonanDAO;
    private static Logger LOG = Logger.getLogger(RekodKpsnMMKActionBean.class);
    String[] namaAmbil;
    String[] tarikhAmbil;
    String[] idAmbil;
    String[] nokp;
    List<String> names = new ArrayList();

    List<AmbilPampasan> listAmbilPampasan;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//    var name;

    @DefaultHandler
    public Resolution showForm() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        listAmbilPampasan = aduanService.findAmbilPampasanByIdPermohonan(idPermohonan);
        return new JSP("pengambilan/aduan_kerosakan/rekodTerimaCek.jsp").addParameter("tab", "true");

    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        namaAmbil = getContext().getRequest().getParameterValues("namaAmbil");
        tarikhAmbil = getContext().getRequest().getParameterValues("tarikhAmbil");
        nokp = getContext().getRequest().getParameterValues("nokp");
        idAmbil = getContext().getRequest().getParameterValues("idAmbil");
        for (int i = 0; i < idAmbil.length; i++) {
            AmbilPampasan ap = aduanService.findAmbilPampasanById(Long.parseLong(idAmbil[i]));
            if (ap != null) {
                if (StringUtils.isNotBlank(namaAmbil[i])) {
                    ap.setNama(namaAmbil[i]);
                }
                if (StringUtils.isNotBlank(nokp[i])) {
                    ap.setNoPengenalan(nokp[i]);
                }
                if (StringUtils.isNotBlank(tarikhAmbil[i])) {
                    ap.setTrhTerimaCek(sdf.parse(tarikhAmbil[i]));
                }
                aduanService.saveAmbilPampasan(ap);
            }
        }
        return showForm();
    }

    public Resolution hantar() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan mohon = permohonanDAO.findById(idPermohonan);
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        RefPeringkat ref = new RefPeringkat();
        sek4AduanIntegrationFlowService.completeTask(ref.SELESAI_TERIMA_PAMPASAN, mohon, pengguna);

        return new RedirectResolution("/senaraiTugasan");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public List<AmbilPampasan> getListAmbilPampasan() {
        return listAmbilPampasan;
    }

    public void setListAmbilPampasan(List<AmbilPampasan> listAmbilPampasan) {
        this.listAmbilPampasan = listAmbilPampasan;
    }

    public AduanService getAduanService() {
        return aduanService;
    }

    public void setAduanService(AduanService aduanService) {
        this.aduanService = aduanService;
    }

    public AmbilPampasanDAO getAmbilPampasanDAO() {
        return ambilPampasanDAO;
    }

    public void setAmbilPampasanDAO(AmbilPampasanDAO ambilPampasanDAO) {
        this.ambilPampasanDAO = ambilPampasanDAO;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        RekodTerimaCekActionBean.LOG = LOG;
    }

    public String[] getNamaAmbil() {
        return namaAmbil;
    }

    public void setNamaAmbil(String[] namaAmbil) {
        this.namaAmbil = namaAmbil;
    }

    public String[] getTarikhAmbil() {
        return tarikhAmbil;
    }

    public void setTarikhAmbil(String[] tarikhAmbil) {
        this.tarikhAmbil = tarikhAmbil;
    }

    public String[] getIdAmbil() {
        return idAmbil;
    }

    public void setIdAmbil(String[] idAmbil) {
        this.idAmbil = idAmbil;
    }

    public String[] getNokp() {
        return nokp;
    }

    public void setNokp(String[] nokp) {
        this.nokp = nokp;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

}
