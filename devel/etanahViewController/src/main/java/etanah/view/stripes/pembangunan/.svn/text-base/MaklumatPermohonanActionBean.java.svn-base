/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pembangunan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.Permohonan;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.model.FolderDokumen;
import etanah.service.common.DokumenService;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author nursyazwani
 */
@UrlBinding("/pembangunan/maklumat_permohonan")
public class MaklumatPermohonanActionBean extends AbleActionBean {

    private static final Logger logger = Logger.getLogger(MaklumatPermohonanActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    DokumenService dokumenService;

    private Permohonan permohonan;
    private FolderDokumen folderDokumen;
    private String noSuratKuasaWakil;
    private String lanjutTempoh;
    private long idDokumenSW;
    private long idDokumenSPSP;
    private String noSuratSemakPelan;

    public FolderDokumen getFolderDokumen() {
        return folderDokumen;
    }

    public void setFolderDokumen(FolderDokumen folderDokumen) {
        this.folderDokumen = folderDokumen;
    }

    public String getNoSuratKuasaWakil() {
        return noSuratKuasaWakil;
    }

    public void setNoSuratKuasaWakil(String noSuratKuasaWakil) {
        this.noSuratKuasaWakil = noSuratKuasaWakil;
    }

    public String getLanjutTempoh() {
        return lanjutTempoh;
    }

    public void setLanjutTempoh(String lanjutTempoh) {
        this.lanjutTempoh = lanjutTempoh;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pembangunan/common/maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    public Resolution simpan() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idDokumenSW != 0) {
            Dokumen dok = dokumenDAO.findById(idDokumenSW);
            dok.setNoRujukan(noSuratKuasaWakil);
            dokumenService.save(dok);
        }
        if (idDokumenSPSP != 0) {
            Dokumen dokSPSP = dokumenDAO.findById(idDokumenSPSP);
            dokSPSP.setNoRujukan(noSuratSemakPelan);

            dokumenService.save(dokSPSP);
        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        return showForm();
    }

    public Resolution showForm2() {
        return new JSP("pembangunan/common/paparan_maklumat_permohonan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {

            permohonan = permohonanDAO.findById(idPermohonan);

            if (permohonan == null) {
                addSimpleError("ID Permohonan " + idPermohonan + " tidak wujud");
                return;
            }
            List< Dokumen> dokumenSW = dokumenService.findDokList(idPermohonan, "SKW");
            if (dokumenSW != null) {
                for (Dokumen d : dokumenSW) {
                    if (StringUtils.isNotBlank(d.getNoRujukan())) {
                        idDokumenSW = d.getIdDokumen();
                        noSuratKuasaWakil = d.getNoRujukan();
                    } else {
                         idDokumenSW = d.getIdDokumen();
                        noSuratKuasaWakil = d.getNoRujukan();
                    }
                }
            }

            List<Dokumen> dokumenSPSP = dokumenService.findDokList(idPermohonan, "SPSP");
            if (!dokumenSPSP.isEmpty()) {
                for (Dokumen d : dokumenSPSP) {
                    if (StringUtils.isNotBlank(d.getNoRujukan())) {
                        idDokumenSPSP = d.getIdDokumen();
                        noSuratSemakPelan = d.getNoRujukan();
                    } else {
                        idDokumenSPSP = d.getIdDokumen();
                        noSuratSemakPelan = d.getNoRujukan();
                    }
                }
            }
            logger.debug("idPermohonan" + idPermohonan);

        }
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public DokumenDAO getDokumenDAO() {
        return dokumenDAO;
    }

    public void setDokumenDAO(DokumenDAO dokumenDAO) {
        this.dokumenDAO = dokumenDAO;
    }

    public DokumenService getDokumenService() {
        return dokumenService;
    }

    public void setDokumenService(DokumenService dokumenService) {
        this.dokumenService = dokumenService;
    }

    public long getIdDokumenSW() {
        return idDokumenSW;
    }

    public void setIdDokumenSW(long idDokumenSW) {
        this.idDokumenSW = idDokumenSW;
    }

    public long getIdDokumenSPSP() {
        return idDokumenSPSP;
    }

    public void setIdDokumenSPSP(long idDokumenSPSP) {
        this.idDokumenSPSP = idDokumenSPSP;
    }

    public String getNoSuratSemakPelan() {
        return noSuratSemakPelan;
    }

    public void setNoSuratSemakPelan(String noSuratSemakPelan) {
        this.noSuratSemakPelan = noSuratSemakPelan;
    }

}
