/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata;

import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanStrataDAO;
import etanah.model.Permohonan;
import etanah.model.PermohonanStrata;
import etanah.service.StrataPtService;
import net.sourceforge.stripes.action.UrlBinding;
import etanah.view.BasicTabActionBean;
import java.text.SimpleDateFormat;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/strata/RMHSPermohonanStrata")
public class RMHSPermohonanStrataActionBean extends BasicTabActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanStrataDAO permohonanStrataDAO;
    @Inject
    StrataPtService strService;
    private Permohonan permohonan = new Permohonan();
    private PermohonanStrata mohonStrata = new PermohonanStrata();
    private static final Logger LOG = Logger.getLogger(RMHSPermohonanStrataActionBean.class);
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String idPermohonanTerdahulu;
    private String pemilik;
    private String pemilikAlamat1;
    private String pemilikAlamat2;
    private String pemilikAlamat3;
    private String pemilikAlamat4;
    private String pemilikPoskod;
    private String pemilikNegeri;
    private String skim;
    private char jenisKosRendah;
    private String namaProjek;
    private String cawangan;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
//            permohonan = permohonanDAO.findById(permohonan.getPermohonanSebelum().getIdPermohonan());
            mohonStrata = strService.findID(permohonan.getPermohonanSebelum().getIdPermohonan());
            if (mohonStrata!=null) {
                setValueMohonStrata(mohonStrata);
            }
        }
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        stageId = "keputusanPTG";
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {

            if (StringUtils.isNotBlank(msg)) {
                addSimpleMessage(msg);
            }
            if (StringUtils.isNotBlank(error)) {
                addSimpleError(error);
            }
            return new JSP("strata/pinda_permohanan/rmhsmaklumat_pemilik.jsp").addParameter("tab", "true");


        } else {
            LOG.info("========Pemilik==== Error===");
            addSimpleError(" Sila masukkan ID Permohonan terdahulu.");
            return new JSP("strata/pinda_permohanan/maklumat_pemilik22.jsp").addParameter("tab", "true");
        }


    }

    public void setValueMohonStrata(PermohonanStrata mohonStrata) {
        idPermohonanTerdahulu = mohonStrata.getCfNoSijil();
        pemilik = mohonStrata.getPemilikNama();
        pemilikAlamat1 = mohonStrata.getPemilikAlamat1();
        pemilikAlamat2 = mohonStrata.getPemilikAlamat2();
        pemilikAlamat3 = mohonStrata.getPemilikAlamat3();
        pemilikAlamat4 = mohonStrata.getPemilikAlamat4();
        pemilikPoskod = mohonStrata.getPoskod();
        pemilikNegeri = mohonStrata.getNegeri().getNama();
        skim = mohonStrata.getNamaSkim();
        jenisKosRendah = mohonStrata.getKosRendah();
        namaProjek = mohonStrata.getNama();
        cawangan = mohonStrata.getCawangan().getName();
    }

    public String getCawangan() {
        return cawangan;
    }

    public void setCawangan(String cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdPermohonanTerdahulu() {
        return idPermohonanTerdahulu;
    }

    public void setIdPermohonanTerdahulu(String idPermohonanTerdahulu) {
        this.idPermohonanTerdahulu = idPermohonanTerdahulu;
    }

    public char getJenisKosRendah() {
        return jenisKosRendah;
    }

    public void setJenisKosRendah(char jenisKosRendah) {
        this.jenisKosRendah = jenisKosRendah;
    }

    public String getNamaProjek() {
        return namaProjek;
    }

    public void setNamaProjek(String namaProjek) {
        this.namaProjek = namaProjek;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

    public String getPemilikAlamat1() {
        return pemilikAlamat1;
    }

    public void setPemilikAlamat1(String pemilikAlamat1) {
        this.pemilikAlamat1 = pemilikAlamat1;
    }

    public String getPemilikAlamat2() {
        return pemilikAlamat2;
    }

    public void setPemilikAlamat2(String pemilikAlamat2) {
        this.pemilikAlamat2 = pemilikAlamat2;
    }

    public String getPemilikAlamat3() {
        return pemilikAlamat3;
    }

    public void setPemilikAlamat3(String pemilikAlamat3) {
        this.pemilikAlamat3 = pemilikAlamat3;
    }

    public String getPemilikAlamat4() {
        return pemilikAlamat4;
    }

    public void setPemilikAlamat4(String pemilikAlamat4) {
        this.pemilikAlamat4 = pemilikAlamat4;
    }

    public String getPemilikNegeri() {
        return pemilikNegeri;
    }

    public void setPemilikNegeri(String pemilikNegeri) {
        this.pemilikNegeri = pemilikNegeri;
    }

    public String getPemilikPoskod() {
        return pemilikPoskod;
    }

    public void setPemilikPoskod(String pemilikPoskod) {
        this.pemilikPoskod = pemilikPoskod;
    }

    public String getSkim() {
        return skim;
    }

    public void setSkim(String skim) {
        this.skim = skim;
    }
}
