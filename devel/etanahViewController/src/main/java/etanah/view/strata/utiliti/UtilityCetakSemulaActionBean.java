/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Murali
 */

@UrlBinding("/utiliti/strata/cetak_semula")
public class UtilityCetakSemulaActionBean extends AbleActionBean {

    @Inject
    private PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    private String idPermohonan;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();
    private static final Logger LOG = Logger.getLogger(UtilityCetakSemulaActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
    }

    public Resolution search() {

        
        
       LOG.debug("--- Search START ---");
        if (permohonan == null) {
            LOG.debug("======== permohonan null");           
//            addSimpleError("Sila masukkan ID Permohonan/Perserahan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
        }
        
        idPermohonan = permohonan.getIdPermohonan();
        LOG.debug("======== idPermohonan1 :" +idPermohonan);
        
        if (idPermohonan == null) {
            LOG.debug("======== idpermohonan null");
            addSimpleError("Sila masukkan ID Permohonan");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
        }

        permohonan = permohonanDAO.findById(idPermohonan);
        LOG.debug("======== Permohonan :" +permohonan);
        if (permohonan == null) {
            addSimpleError("Permohonan " + idPermohonan + " tidak dijumpai.");
            return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
        } else {
            if (permohonan != null) {
                permohonan = permohonanDAO.findById(permohonan.getIdPermohonan());
                if (permohonan != null) {
                    if (!permohonan.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                        for (KandunganFolder kFolder : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                            if (kFolder.getDokumen().getKodDokumen() != null) {
                                /*if (kFolder.getDokumen().getKodDokumen().getKod().equals("16H")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16I")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16O")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16P")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("16Q")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("KM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("KMLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("LEL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("LELLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("MEMO")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("MEMOL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("NL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("NLLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("PJ")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("PJLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("RM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SBI")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SBIL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SLHL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SSL")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SSLM")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("STP")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SWT")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("SRW")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("TBP")
                                        || kFolder.getDokumen().getKodDokumen().getKod().equals("TG")) { */
                                    senaraiKandungan.add(kFolder);
                                //}
                            }
                        }
                    }
                }
            }
        }
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
    }

    public Resolution reset() {
        permohonan = new Permohonan();
        senaraiKandungan = null;
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/utilitiCetakSemula.jsp");
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

    public List<KandunganFolder> getSenaraiKandungan() {
        return senaraiKandungan;
    }

    public void setSenaraiKandungan(List<KandunganFolder> senaraiKandungan) {
        this.senaraiKandungan = senaraiKandungan;
    }
}
