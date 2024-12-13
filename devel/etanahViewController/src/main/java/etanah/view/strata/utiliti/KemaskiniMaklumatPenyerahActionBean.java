/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.strata.utiliti;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import etanah.view.strata.CommonService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

@UrlBinding("/strata/kemaskini_maklumat_penyerah")
public class KemaskiniMaklumatPenyerahActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    CommonService comm;
    @Inject
    StrataPtService strService;
    //+++++++++++++++++++++++++++++++++++++++++++
    private Permohonan permohonan;
    private Pengguna pengguna;
    //+++++++++++++++++++++++++++++++++++++++++++
    private String idPermohonan;
    private boolean rayuan = true;
    private String penyerah;
    private String negeri;
    private String penyerahKod;
    private String penyerahKodNama;
    private String idPenyerah;
    private String penyerahJenisPengenalan;
    private String penyerahNoPengenalan;
    private String penyerahNama;
    private String penyerahAlamat1;
    private String penyerahAlamat2;
    private String penyerahAlamat3;
    private String penyerahAlamat4;
    private String penyerahPoskod;
    private String penyerahNamaNegeri;
    private String penyerahNegeri;
    private String penyerahNoTelefon;
    private String penyerahEmail;
    //+++++++++++++++++++++++++++++++++++++++++++
    private static final Logger LOG = Logger.getLogger(KemaskiniMaklumatPenyerahActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        LOG.debug("================== idPermohonanKemaskiniPerbadanan : " + idPermohonan);
        return new ForwardResolution("/WEB-INF/jsp/strata/utiliti/kemaskini_maklumat_penyerah.jsp");
    }

    public Resolution viewMaklumatPenyerah() {
        String idMohon = (String) getContext().getRequest().getParameter("idPermohonan");
        permohonan = permohonanDAO.findById(idMohon);
        LOG.debug("================= Permohonan : " + permohonan);
        return new JSP("strata/utiliti/kemaskini_maklumat_penyerah.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        idPermohonan = (String) getContext().getRequest().getParameter("idPermohonan");
        LOG.debug("================= idPermohonan : " + idPermohonan);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan.getPermohonanSebelum() != null) {
                idPermohonan = permohonan.getPermohonanSebelum().getIdPermohonan();
            }

            Pemohon penyerahpemohon = strService.findPenyerahPemohon(idPermohonan);
            if (penyerahpemohon != null) {
                penyerah = String.valueOf(penyerahpemohon.getIdPemohon());
                LOG.info("penyerah--> " + penyerah);
            }
            
            if ((permohonan.getKodPenyerah() != null) && (permohonan.getKodPenyerah().getNama() != null)){
                penyerahKodNama = permohonan.getKodPenyerah().getNama();
                LOG.info("---------penyerahKodNama--------" + penyerahKodNama);
            }
            if ((permohonan.getKodPenyerah() != null) && (permohonan.getKodPenyerah().getKod() != null)){
                penyerahKod = permohonan.getKodPenyerah().getKod();
                LOG.info("---------penyerahKod--------" + penyerahKod);
            }
            if (permohonan.getPenyerahNama() != null){
                penyerahNama = permohonan.getPenyerahNama();
                LOG.info("---------penyerahNama--------" + penyerahNama);
            }
            if (permohonan.getPenyerahAlamat1() != null){
                penyerahAlamat1 = permohonan.getPenyerahAlamat1();
                LOG.info("---------penyerahAlamat1--------" + penyerahAlamat1);
            }
            if (permohonan.getPenyerahAlamat2() != null){
                penyerahAlamat2 = permohonan.getPenyerahAlamat2();
                LOG.info("---------penyerahAlamat2--------" + penyerahAlamat2);
            }
            if (permohonan.getPenyerahAlamat3() != null){
                penyerahAlamat3 = permohonan.getPenyerahAlamat3();
                LOG.info("---------penyerahAlamat3--------" + penyerahAlamat3);
            }
            if (permohonan.getPenyerahAlamat4() != null){
                penyerahAlamat4 = permohonan.getPenyerahAlamat4();
                LOG.info("---------penyerahAlamat4--------" + penyerahAlamat4);
            }
            if (permohonan.getPenyerahPoskod() != null){
                penyerahPoskod = permohonan.getPenyerahPoskod();
                LOG.info("---------penyerahPoskod--------" + penyerahPoskod);
            }
            if ((permohonan.getPenyerahNegeri() != null) && (permohonan.getPenyerahNegeri().getNama() != null)){
                penyerahNamaNegeri = permohonan.getPenyerahNegeri().getNama();
                LOG.info("---------penyerahNamaNegeri--------" + penyerahNamaNegeri);
            }
            if ((permohonan.getPenyerahNegeri() != null) && (permohonan.getPenyerahNegeri().getKod() != null)){
                penyerahNegeri = permohonan.getPenyerahNegeri().getKod();
                LOG.info("---------penyerahNegeri--------" + penyerahNegeri);
            }
            if (permohonan.getPenyerahNoTelefon1() != null){
                penyerahNoTelefon = permohonan.getPenyerahNoTelefon1();
                LOG.info("---------penyerahNoTelefon--------" + penyerahNoTelefon);
            }
            if (permohonan.getPenyerahEmail() != null){
                penyerahEmail = permohonan.getPenyerahEmail();
                LOG.info("---------penyerahEmail--------" + penyerahEmail);
            }
            
        }

    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public PermohonanDAO getPermohonanDAO() {
        return permohonanDAO;
    }

    public void setPermohonanDAO(PermohonanDAO permohonanDAO) {
        this.permohonanDAO = permohonanDAO;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }
    
    public String getPenyerahKod() {
        return penyerahKod;
    }

    public void setPenyerahKod(String penyerahKod) {
        this.penyerahKod = penyerahKod;
    }

    public String getPenyerahKodNama() {
        return penyerahKodNama;
    }

    public void setPenyerahKodNama(String penyerahKodNama) {
        this.penyerahKodNama = penyerahKodNama;
    }
    
    public String getPenyerahNama() {
        return penyerahNama;
    }

    public void setPenyerahNama(String penyerahNama) {
        this.penyerahNama = penyerahNama;
    }
    
    public String getPenyerahAlamat1() {
        return penyerahAlamat1;
    }

    public void setPenyerahAlamat1(String penyerahAlamat1) {
        this.penyerahAlamat1 = penyerahAlamat1;
    }

    public String getPenyerahAlamat2() {
        return penyerahAlamat2;
    }

    public void setPenyerahAlamat2(String penyerahAlamat2) {
        this.penyerahAlamat2 = penyerahAlamat2;
    }

    public String getPenyerahAlamat3() {
        return penyerahAlamat3;
    }

    public void setPenyerahAlamat3(String penyerahAlamat3) {
        this.penyerahAlamat3 = penyerahAlamat3;
    }

    public String getPenyerahAlamat4() {
        return penyerahAlamat4;
    }

    public void setPenyerahAlamat4(String penyerahAlamat4) {
        this.penyerahAlamat4 = penyerahAlamat4;
    }

    public String getPenyerahPoskod() {
        return penyerahPoskod;
    }

    public void setPenyerahPoskod(String penyerahPoskod) {
        this.penyerahPoskod = penyerahPoskod;
    }

    public String getPenyerahNamaNegeri() {
        return penyerahNamaNegeri;
    }

    public void setPenyerahNamaNegeri(String penyerahNamaNegeri) {
        this.penyerahNamaNegeri = penyerahNamaNegeri;
    }

    public String getPenyerahNegeri() {
        return penyerahNegeri;
    }

    public void setPenyerahNegeri(String penyerahNegeri) {
        this.penyerahNegeri = penyerahNegeri;
    }

    public String getPenyerahNoTelefon() {
        return penyerahNoTelefon;
    }

    public void setPenyerahNoTelefon(String penyerahNoTelefon) {
        this.penyerahNoTelefon = penyerahNoTelefon;
    }

    public String getPenyerahEmail() {
        return penyerahEmail;
    }

    public void setPenyerahEmail(String penyerahEmail) {
        this.penyerahEmail = penyerahEmail;
    }
}
