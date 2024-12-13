/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikAlamatDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.PenggunaDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.SenaraiRujukanDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAlamat;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodNegeri;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.SenaraiRujukan;
import etanah.view.etanahActionBeanContext;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.SimpleError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/maklumat_tambah_SBM")
public class MaklumatTambahRemSBMActionBean extends AbleActionBean {

    private static final Logger LOG = Logger.getLogger(MaklumatTambahRemSBMActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();
    @Inject
    HakmilikAlamatDAO hakmilikAlamatDAO;
    private HakmilikAlamat hakmilikAlamat;
    private List<HakmilikAlamat> senaraiHakmilikAlamat;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPermohonan> senaraiHakmilikPermohonan;
    private String idHakmilik;
    @Inject
    PermohonanDAO permohonanDAO;
    private Permohonan permohonan;
    @Inject
    PenggunaDAO penggunaDAO;
    @Inject
    RemisyenManager manager;
    private String display;
    private String kodNegeri;
    //for carian kod sekolah
    @Inject
    SenaraiRujukanDAO senaraiRujukanDAO;
    private List<SenaraiRujukan> senaraiRujukanList;
    private String negeri;
    private String searchKod;
    private String searchNama;
    @Inject
    KodSekolahBantuanService kodSekolahBantuanService;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("hasil/maklumat_tambahan_sbm.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        display = "display:none;";
        return new JSP("hasil/maklumat_tambahan_sbm.jsp").addParameter("tab", "true");
    }

//    @Before(stages = {LifecycleStage.BindingAndValidation}, on={"!saveOrUpdate"})
    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        LOG.info("rehydrate:start");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = null;
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            String[] tname = {"permohonan"};
            Object[] model = {permohonan};

            senaraiHakmilikPermohonan = hakmilikPermohonanDAO.findByEqualCriterias(tname, model, null);
            if (!(senaraiHakmilikPermohonan.isEmpty())) {
                idHakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik().getIdHakmilik();
                LOG.debug("idHakmilik :" + idHakmilik);
                hakmilikAlamat = hakmilikAlamatDAO.findById(idHakmilik);
                if (hakmilikAlamat != null) {
                    kodNegeri = hakmilikAlamat.getAlamatNegeri().getKod();
                }
            }
        }
        LOG.info("rehydrate:finish");
    }

    public Resolution saveOrUpdate() {
        LOG.info("saveOrUpdate:start");
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String result = null;
        LOG.info("Melepasi garisan");
        KodNegeri kn = new KodNegeri();
        kn.setKod(kodNegeri);
        hakmilikAlamat.setAlamatNegeri(kn);
        result = manager.saveOrUpdate(hakmilikAlamat, idHakmilik, peng);

        if ("save".equals(result)) {
            addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
            LOG.debug("Maklumat Telah Berjaya Disimpan.." + hakmilikAlamat.getIdHakmilik());
        } else if ("update".equals(result)) {
            addSimpleMessage("Maklumat Telah Berjaya Dikemaskini..");
            LOG.debug("Maklumat Telah Berjaya Dikemaskini..");
        } else {
            addSimpleMessage("Sistem Tergendala Sementara. Harap Maklum..");
            LOG.warn("Sistem Tergendala Sementara. Harap Maklum..");
        }
        LOG.info("saveOrUpdate:finish");
        return new JSP("hasil/maklumat_tambahan_sbm.jsp").addParameter("tab", "true");
    }

//    //not sure by fikri
//    @ValidationMethod(on = "saveOrUpdate")
//    public void validateField(ValidationErrors errors) {
//        if (hakmilikAlamat.getNamaInst() == null)
//            errors.add(" ", new SimpleError("Sila Masukkan Nama Sekolah."));
//        if (hakmilikAlamat.getAlamat1() == null)
//            errors.add(" ", new SimpleError("Sila Masukkan Alamat."));
//        if (hakmilikAlamat.getPoskod() == null)
//            errors.add(" ", new SimpleError("Sila Masukkan Poskod."));
//        if (hakmilikAlamat.getAlamatNegeri().getKod() == null)
//            errors.add(" ", new SimpleError("Sila Pilih Negeri."));
//    }

    public Resolution refreshpage() {
        rehydrate();
        LOG.info("refreshpage");
        return new JSP("hasil/maklumat_tambahan_sbm.jsp").addParameter("tab", "true");
    }

    public Resolution cari(){
        LOG.info("start");
        senaraiRujukanList = kodSekolahBantuanService.getKodSekolah(searchKod, searchNama, negeri);
        LOG.debug("senaraiRujukanList.size() :"+senaraiRujukanList.size());
        return new JSP("hasil/carianKodSekolah.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKodSekolahBantuan(){
        String resultKod = null;
        Pengguna peng = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String kodSekolah = getContext().getRequest().getParameter("kod");
        LOG.info("kodSekolah :"+kodSekolah);
        if(StringUtils.isNotBlank(kodSekolah)){
            SenaraiRujukan SR = senaraiRujukanDAO.findById(kodSekolah);
            LOG.info("SR :"+SR.getNama());
            if(StringUtils.isNotBlank(idHakmilik)){
                HakmilikAlamat hakAlamat = hakmilikAlamatDAO.findById(idHakmilik);
                if(hakAlamat != null)
                    if(manager.deleteHakmilikAlamat(hakAlamat))
                        LOG.debug("hakAlamat result :success");
                    else
                        LOG.error("hakAlamat result : failed");
            }
            HakmilikAlamat ha = new HakmilikAlamat();
            ha.setNamaInst(SR.getNama());
            ha.setAlamat1(SR.getAlamat().getAlamat1());
            if(SR.getAlamat().getAlamat2() != null)
                ha.setAlamat2(SR.getAlamat().getAlamat2());
            if(SR.getAlamat().getAlamat3() != null)
                ha.setAlamat3(SR.getAlamat().getAlamat3());
            if(SR.getAlamat().getAlamat4() != null)
                ha.setAlamat4(SR.getAlamat().getAlamat4());
            ha.setPoskod(SR.getAlamat().getPoskod());
            ha.setAlamatNegeri(SR.getAlamat().getNegeri());
            if(SR.getNoTel1() != null)
                ha.setNoTel(SR.getNoTel1());
            LOG.info("idHakmilik :"+idHakmilik);
            resultKod = manager.saveOrUpdate(ha, idHakmilik, peng);
            addSimpleMessage("Maklumat Telah Berjaya Disimpan..");
            LOG.debug("simpanKodSekolahBantuan : Maklumat Telah Berjaya Disimpan..");
        }else{
            LOG.debug("simpanKodSekolah : kodSekolah is Null");
        }
        LOG.info("simpanKodSekolah: resultKod :"+resultKod);
        return refreshpage();
    }

    public HakmilikAlamat getHakmilikAlamat() {
        return hakmilikAlamat;
    }

    public void setHakmilikAlamat(HakmilikAlamat hakmilikAlamat) {
        this.hakmilikAlamat = hakmilikAlamat;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikPermohonan> getSenaraiHakmilikPermohonan() {
        return senaraiHakmilikPermohonan;
    }

    public void setSenaraiHakmilikPermohonan(List<HakmilikPermohonan> senaraiHakmilikPermohonan) {
        this.senaraiHakmilikPermohonan = senaraiHakmilikPermohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public List<HakmilikAlamat> getSenaraiHakmilikAlamat() {
        return senaraiHakmilikAlamat;
    }

    public void setSenaraiHakmilikAlamat(List<HakmilikAlamat> senaraiHakmilikAlamat) {
        this.senaraiHakmilikAlamat = senaraiHakmilikAlamat;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }

    public List<SenaraiRujukan> getSenaraiRujukanList() {
        return senaraiRujukanList;
    }

    public void setSenaraiRujukanList(List<SenaraiRujukan> senaraiRujukanList) {
        this.senaraiRujukanList = senaraiRujukanList;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getSearchKod() {
        return searchKod;
    }

    public void setSearchKod(String searchKod) {
        this.searchKod = searchKod;
    }

    public String getSearchNama() {
        return searchNama;
    }

    public void setSearchNama(String searchNama) {
        this.searchNama = searchNama;
    }
}
