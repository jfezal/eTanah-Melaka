/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.common;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.HakmilikPermohonan;
import etanah.model.KandunganFolder;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.util.CompareTitle;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
@UrlBinding("/common/view_and_compare_title")
public class ViewAndCompareTitle extends AbleActionBean {

    @Inject
    private Pengguna pengguna;
    private Permohonan permohonan;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String idHakmilik;
    private FolderDokumen folderDokumen;
    private Map<String, String> kodMap = new HashMap<String, String>();
    private List<Dokumen> senaraiKandungan = new ArrayList<Dokumen>();
    private String idPermohonan;
    @Inject
    CompareTitle compareTitle;
    @Inject
    PermohonanDAO permohonanDAO;
    private static final Logger LOGGER = Logger.getLogger(ViewAndCompareTitle.class);

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("common/paparan_conversion_pt.jsp").addParameter("tab", "true");
    }

    public Resolution convertAndCompare() {
        String result = "";
        String idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        LOGGER.debug("convert & compare idHakmilik :"+idHakmilik);      
        if (StringUtils.isNotBlank(idHakmilik)) {
            result = compareTitle.convertAndCompare(idHakmilik);
        }
        return new StreamingResolution("text/plain", result);
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOGGER.debug("idPermohonan : "+ idPermohonan);
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (StringUtils.isNotBlank(idPermohonan)) {
            permohonan = permohonanDAO.findById(idPermohonan);
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
//            hakmilikPermohonanList = regService.senaraiMohonHakmilikMenanggung(idPermohonan);

            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }

            if (permohonan.getSenaraiHakmilik().size() > 0) {
                if (permohonan.getSenaraiHakmilik().get(0).getHakmilik() != null) {
                    idHakmilik = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
                }
            }

//            for (KandunganFolder kf : folderDokumen.getSenaraiKandungan()) {
//                if (kf == null) {
//                    continue;
//                }
//                KodDokumen kd = kf.getDokumen().getKodDokumen();
//                if (kd == null) {
//                    continue;
//                }
//
//                kodMap.put(kd.getKod(), kd.getNama());
//                if (permohonan.getKodUrusan().getKod().equals("HSBM")) {
//                    if (kd.getKod().equals("DHDK") || kd.getKod().equals("DHKK")) {
//                        senaraiKandungan.add(kf.getDokumen());
//                    }
//                } else {
//                    if (kd.getKod().equals("VDOC")) {
//                        senaraiKandungan.add(kf.getDokumen());
//                    }
//                }
//            }
        }
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Pengguna getPengguna() {
        return pengguna;
    }

    public void setPengguna(Pengguna pengguna) {
        this.pengguna = pengguna;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
}
