/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan.utiliti;

import able.stripes.AbleActionBean;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanNota;
import etanah.service.EnforceService;
import etanah.service.common.EnforcementService;
import etanah.service.common.LelongService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HttpCache;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author MohammadHafifi
 */
@HttpCache(allow = false)
@UrlBinding("/penguatkuasaan/utiliti_nota_kertas_minit")
public class UtilitiCetakanNotaKertasMinitActionBean extends AbleActionBean {

    @Inject
    LelongService lelongService;
    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanDAO permohonanDAO;
    private Pengguna pengguna;
    private String idPermohonan;
    private Permohonan permohonan;
    private Long idDokumen;
    private List<PermohonanNota> listNota;
    private List<KandunganFolder> senaraiKandungan = new ArrayList<KandunganFolder>();

    @DefaultHandler
    public Resolution findRksnKes() {
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_nota_kertas_minit.jsp");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        if (pengguna != null) {
            getContext().getRequest().setAttribute("idPengguna", pengguna.getIdPengguna());
        }
        listNota = enforceService.findListNotaByIdMohon(idPermohonan);
    }

    public Resolution searchNoSerahan() {
        System.out.println(idPermohonan);
        listNota = enforceService.findListNotaByIdMohon(idPermohonan);

        if (listNota.isEmpty()) {
            addSimpleError("Nota / Kertas Minit bagi Id Permohonan " + idPermohonan + " tidak dijumpai.");
        } else {
            idDokumen = genReport(idPermohonan); //Hafifi 17/4/2014 : Jana report dan simpan dalam dokumen id permohonan sekiranya belum wujud
            System.out.println(idDokumen);
        }
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/utiliti/carian_nota_kertas_minit.jsp");
    }

    public Long genReport(String idPermohonan) {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String gen = "";
        String code = "";
        gen = "ENFNT_NS.rdf";
        code = "NOTA";
        boolean isExist = false;
        permohonan = permohonanDAO.findById(idPermohonan);
        Long idDokumen = null;

        try {
            if (permohonan != null) {
                if (!permohonan.getFolderDokumen().getSenaraiKandungan().isEmpty()) {
                    for (KandunganFolder kf : permohonan.getFolderDokumen().getSenaraiKandungan()) {
                        if (kf.getDokumen().getKodDokumen() != null) {
                            if (kf.getDokumen().getKodDokumen().getKod().equals(code)) {
                                isExist = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (!isExist) {
                lelongService.reportGen(permohonan, gen, code, pengguna);
            }
            
            List<Dokumen> dokumens = enforceService.findDokumenList(idPermohonan);
            
            for(Dokumen d : dokumens){
                if(d.getKodDokumen().getKod().equalsIgnoreCase(code)){
                    idDokumen = d.getIdDokumen();
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return idDokumen;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public List<PermohonanNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<PermohonanNota> listNota) {
        this.listNota = listNota;
    }

    public Long getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(Long idDokumen) {
        this.idDokumen = idDokumen;
    }
}
