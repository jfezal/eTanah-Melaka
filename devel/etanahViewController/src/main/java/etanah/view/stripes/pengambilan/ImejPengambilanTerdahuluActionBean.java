/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AduanStrata;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.KodCawangan;
import etanah.model.Permohonan;
import etanah.model.Pihak;
import etanah.model.SiasatanAduanImej;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.view.BasicTabActionBean;
import java.text.SimpleDateFormat;
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
 * @author massita(modified from strata)
 */
@UrlBinding("/pengambilan/ImejTerdahulu")
public class ImejPengambilanTerdahuluActionBean extends BasicTabActionBean {

    @Inject
    private HakmilikDAO hakmilikDAO;
    @Inject
    private PermohonanDAO permohonanDAO;
    @Inject
    private StrataPtService strService;
    @Inject
    private HakmilikPihakKepentinganService hakmilikPihakKepentinganService;
    private Permohonan permohonan;
    private KodCawangan kodCawangan;
    private AduanStrata aduanStrata;
    private Hakmilik hakmilik;
    private static final Logger LOG = Logger.getLogger(ImejPengambilanTerdahuluActionBean.class);
    private String negeri;
    private String idHakmilik;
    private String kodStatusHakmilik;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private List<SiasatanAduanImej> senaraiImejSiasatan = new ArrayList<SiasatanAduanImej>();
    private List<HakmilikPihakBerkepentingan> listHakmilikPihak;
    private Pihak pihak;
    private String idPihak;
    private String kodUrusan;
    private String idSblm;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

         permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        }

        LOG.info("----Rehydrate-----");
        LOG.info("----idPermohonan : " + idPermohonan + " -----");

        if (idSblm != null) {
            permohonan = permohonanDAO.findById(idSblm);
            kodUrusan = permohonan.getKodUrusan().getKod();
            aduanStrata = strService.findAduanStrataIdMohon(idSblm);

            if (aduanStrata != null) {
                idHakmilik = aduanStrata.getHakmilik().getIdHakmilik();
                hakmilik = hakmilikDAO.findById(idHakmilik);
                kodStatusHakmilik = hakmilik.getKodStatusHakmilik().getKod();
                listHakmilikPihak = hakmilikPihakKepentinganService.findHakmilikPihakByKod(hakmilik, "PM");

            }
            senaraiImejSiasatan = strService.findSiasatanImejByIdPermohonan(idSblm);

        }

    }

    @DefaultHandler
    public Resolution showImej() {
        LOG.info("inside : Resolution showImej");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        if (permohonan.getPermohonanSebelum() != null) {
            idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            permohonan = permohonanDAO.findById(idSblm);
        }else{
            addSimpleError("Haraf maaf sila masukkan id Permohonan Terdahulu");
//            getContext().getRequest().setAttribute("IdSblmIsNull", Boolean.FALSE);
        }

        senaraiImejSiasatan = strService.findSiasatanImejByIdPermohonan(idSblm);

        return new JSP("pengambilan/negerisembilan/penarikanbalik/imejTerdahulu.jsp").addParameter("tab", "true");
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<SiasatanAduanImej> getSenaraiImejSiasatan() {
        return senaraiImejSiasatan;
    }

    public void setSenaraiImejSiasatan(List<SiasatanAduanImej> senaraiImejSiasatan) {
        this.senaraiImejSiasatan = senaraiImejSiasatan;
    }

    public List<HakmilikPihakBerkepentingan> getListHakmilikPihak() {
        return listHakmilikPihak;
    }

    public void setListHakmilikPihak(List<HakmilikPihakBerkepentingan> listHakmilikPihak) {
        this.listHakmilikPihak = listHakmilikPihak;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
        this.idPihak = idPihak;
    }

      public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public AduanStrata getAduanStrata() {
        return aduanStrata;
    }

    public void setAduanStrata(AduanStrata aduanStrata) {
        this.aduanStrata = aduanStrata;
    }

    public String getIdSblm() {
        return idSblm;
    }

    public void setIdSblm(String idSblm) {
        this.idSblm = idSblm;
    }

    public KodCawangan getKodCawangan() {
        return kodCawangan;
    }

    public void setKodCawangan(KodCawangan kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public String getKodStatusHakmilik() {
        return kodStatusHakmilik;
    }

    public void setKodStatusHakmilik(String kodStatusHakmilik) {
        this.kodStatusHakmilik = kodStatusHakmilik;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }
}
