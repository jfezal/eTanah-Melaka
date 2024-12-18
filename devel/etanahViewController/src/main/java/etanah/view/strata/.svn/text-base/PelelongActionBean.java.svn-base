package etanah.view.strata;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.KodNegeriDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanSyarikatLelongDAO;
import etanah.dao.SytJuruLelongDAO;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanSyarikatLelong;
import etanah.model.SytJuruLelong;
import etanah.service.StrataPtService;
import etanah.view.etanahActionBeanContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
 * @author faidzal
 */
@UrlBinding("/strata/pelelong")
public class PelelongActionBean extends AbleActionBean {

    @Inject
    private PermohonanSyarikatLelongDAO mohonSytLelongDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    SytJuruLelongDAO sytJuruLelongDAO;
    private List<SytJuruLelong> listSytJuruLelong = new ArrayList<SytJuruLelong>();
    private String idPermohonan;
    private Permohonan permohonan;
    private SytJuruLelong sytJuruLelong = new SytJuruLelong();
    private PermohonanSyarikatLelong mohonSytLelong = new PermohonanSyarikatLelong();
    private Pengguna peng = new Pengguna();
    private String idSyt;
    private String nama;
    private String alamat1;
    private String alamat2;
    private String alamat3;
    private String alamat4;
    private String poskod;
    private String negeri;
    private String noTel1;
    private String noTel2;
    private String idSytlelong;
    private Date tarikhLantikLelong;
    private static final Logger LOG = Logger.getLogger(PelelongActionBean.class);
    private boolean readOnly = false;
    private String namaSyarikat;
    private String noSyarikat;
    private PermohonanSyarikatLelong mohonSytLelong1;
    private String namaNegeri;

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (!strService.findSytLelong(idPermohonan).isEmpty()) {
                mohonSytLelong = strService.findSytLelong(idPermohonan).get(0);
                if (mohonSytLelong != null) {
                    noSyarikat = mohonSytLelong.getSytJuruLelong().getNoPengenalan();
                    nama = mohonSytLelong.getSytJuruLelong().getNama();
                    alamat1 = mohonSytLelong.getSytJuruLelong().getAlamat1();
                    alamat2 = mohonSytLelong.getSytJuruLelong().getAlamat2();
                    alamat3 = mohonSytLelong.getSytJuruLelong().getAlamat3();
                    alamat4 = mohonSytLelong.getSytJuruLelong().getAlamat4();
                    poskod = mohonSytLelong.getSytJuruLelong().getPoskod();
                    noTel1 = mohonSytLelong.getSytJuruLelong().getNoTelefon1();
                    noTel2 = mohonSytLelong.getSytJuruLelong().getNoTelefon2();
//                    mohonSytLelong.getSytJuruLelong().getJenisPengenalan().getKod();
                    if (mohonSytLelong.getSytJuruLelong().getNegeri() != null) {
                        negeri = mohonSytLelong.getSytJuruLelong().getNegeri().getKod();
                        namaNegeri = mohonSytLelong.getSytJuruLelong().getNegeri().getNama();
                    }
                    tarikhLantikLelong = mohonSytLelong.getTarikhLantikan();
                }
            }

        }
    }

    @DefaultHandler
    public Resolution showForm() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");

    }

    public Resolution showFormReadOnly() {
        String msg = getContext().getRequest().getParameter("message");
        String error = getContext().getRequest().getParameter("error");
        if (StringUtils.isNotBlank(msg)) {
            addSimpleMessage(msg);
        }
        if (StringUtils.isNotBlank(error)) {
            addSimpleError(error);
        }
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        readOnly = true;
        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");

    }

    public Resolution hapusPelelong() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        SytJuruLelong sytJurulelong = new SytJuruLelong();
        InfoAudit ia = new InfoAudit();
        if (!strService.findSytLelong(idPermohonan).isEmpty()) {
            mohonSytLelong = strService.findSytLelong(idPermohonan).get(0);
        }
        if (mohonSytLelong.getSytJuruLelong() != null) {
            strService.deleteMohonSyrtLelong(mohonSytLelong);
            addSimpleMessage("Maklumat berjaya dihapuskan.");
        } else {
            mohonSytLelong = new PermohonanSyarikatLelong();
            sytJurulelong = new SytJuruLelong();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            sytJurulelong.setInfoAudit(ia);
            sytJurulelong.setCawangan(peng.getKodCawangan());
            mohonSytLelong.setCawangan(peng.getKodCawangan());
            mohonSytLelong.setInfoAudit(ia);
        }
        rehydrate();
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
//        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");
        return new RedirectResolution(PelelongActionBean.class, "showForm");
    }

    public Resolution kemaskiniPelelong() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        SytJuruLelong sytJurulelong = new SytJuruLelong();
        InfoAudit ia = new InfoAudit();
        if (!strService.findSytLelong(idPermohonan).isEmpty()) {
            mohonSytLelong = strService.findSytLelong(idPermohonan).get(0);
        }
        if (mohonSytLelong.getSytJuruLelong() != null) {
            sytJurulelong = sytJuruLelongDAO.findById(mohonSytLelong.getSytJuruLelong().getIdSytJlb());
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new Date());
            ia.setDimasukOleh(sytJurulelong.getInfoAudit().getDimasukOleh());
            ia.setTarikhMasuk(sytJurulelong.getInfoAudit().getTarikhMasuk());
            sytJurulelong.setInfoAudit(ia);
        } else {
            mohonSytLelong = new PermohonanSyarikatLelong();
            sytJurulelong = new SytJuruLelong();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            sytJurulelong.setInfoAudit(ia);
            sytJurulelong.setCawangan(peng.getKodCawangan());
            mohonSytLelong.setCawangan(peng.getKodCawangan());
            mohonSytLelong.setInfoAudit(ia);
        }
        sytJurulelong.setNoPengenalan(noSyarikat);
        sytJurulelong.setNama(nama);
        sytJurulelong.setAlamat1(alamat1);
        sytJurulelong.setAlamat2(alamat2);
        sytJurulelong.setAlamat3(alamat3);
        sytJurulelong.setAlamat4(alamat4);
        if (StringUtils.isNotBlank(negeri)) {
            sytJurulelong.setNegeri(kodNegeriDAO.findById(negeri));
        }
        sytJurulelong.setPoskod(poskod);
        sytJurulelong.setNoTelefon1(noTel1);
        sytJurulelong.setNoTelefon2(noTel2);
        sytJurulelong = strService.saveSytJurulelong(sytJurulelong);
        sytJurulelong = sytJuruLelongDAO.findById(sytJurulelong.getIdSytJlb());


        LOG.info("syrt : " + sytJurulelong.getIdSytJlb());
        mohonSytLelong.setSytJuruLelong(sytJurulelong);
        mohonSytLelong.setIdPermohonan(permohonan);
        mohonSytLelong.setTarikhLantikan(tarikhLantikLelong);
        mohonSytLelong = strService.savePermohonanSyarikatLelong(mohonSytLelong);
        addSimpleMessage("Maklumat telah disimpan.");
        getContext().getRequest().setAttribute("disable", Boolean.TRUE);
        getContext().getRequest().setAttribute("readOnly", Boolean.FALSE);
        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");
    }

    public Resolution deletepelelong() {
        LOG.info("--------Deleting----------");
        Pengguna pguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        mohonSytLelong1 = strService.findSytLelong(idPermohonan).get(0);
        LOG.info("--------mohonSytLelong--found--------" + mohonSytLelong1);

        if (mohonSytLelong != null) {
            LOG.info("--------mohonSytLelong--Deleting--------");
            strService.deleteMohonSyrtLelong(mohonSytLelong1);
            LOG.info("--------mohonSytLelong--Deleted--------");
        }
        addSimpleMessage("Maklumat berjaya dihapuskan");
        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");
//        return new RedirectResolution(PelelongActionBean.class, "locate");
    }

    public Resolution cariPelelong() {
        listSytJuruLelong.clear();
        LOG.info("listSytJuruLelong : " + listSytJuruLelong.size());
        LOG.info("Nama : " + nama);
        LOG.info("idSyt : " + idSyt);
        if (StringUtils.isBlank(nama) && StringUtils.isBlank(idSyt)) {
            LOG.info("All Blank");
            listSytJuruLelong = sytJuruLelongDAO.findAll();
        } else if (StringUtils.isNotBlank(nama)) {
            LOG.info("Nama");
            listSytJuruLelong = new ArrayList<SytJuruLelong>();
            listSytJuruLelong = strService.findSenaraiSytJuruLelongNama(nama);
        } else if (StringUtils.isNotBlank(idSyt)) {
            LOG.info("Id Syrkt");
            listSytJuruLelong = new ArrayList<SytJuruLelong>();
            listSytJuruLelong = strService.findSenaraiSytJuruLelongId(idSyt);
        }

        LOG.info("listSytJuruLelong : " + listSytJuruLelong.size());

        return new JSP("strata/waran/tambahSyarikatLelong.jsp?").addParameter("popup", "true");
    }

    public Resolution pilihSytJuruLelong() {
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        InfoAudit ia = new InfoAudit();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (!strService.findSytLelong(idPermohonan).isEmpty()) {
                mohonSytLelong = strService.findSytLelong(idPermohonan).get(0);
                ia.setDimasukOleh(mohonSytLelong.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(mohonSytLelong.getInfoAudit().getTarikhMasuk());
                ia.setTarikhKemaskini(new Date());
                ia.setDiKemaskiniOleh(peng);
            } else {
                ia.setDimasukOleh(peng);
                ia.setTarikhMasuk(new Date());
            }
        }
//         idSytlelong = getContext().getRequest().getParameter("idSytlelong");
        LOG.info("idsyarikatlelong : " + idSytlelong);
        if (StringUtils.isNotEmpty(idSytlelong)) {
            sytJuruLelong = sytJuruLelongDAO.findById(Long.valueOf(idSytlelong));
        }
        mohonSytLelong.setIdPermohonan(permohonan);
        mohonSytLelong.setSytJuruLelong(sytJuruLelong);
        mohonSytLelong.setInfoAudit(ia);
        mohonSytLelong.setCawangan(peng.getKodCawangan());
        strService.savePermohonanSyarikatLelong(mohonSytLelong);
        rehydrate();
        return new JSP("strata/waran/pelelong.jsp").addParameter("tab", "true");
    }

    public Date getTarikhLantikLelong() {
        return tarikhLantikLelong;
    }

    public void setTarikhLantikLelong(Date tarikhLantikLelong) {
        this.tarikhLantikLelong = tarikhLantikLelong;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanSyarikatLelong getMohonSytLelong() {
        return mohonSytLelong;
    }

    public void setMohonSytLelong(PermohonanSyarikatLelong mohonSytLelong) {
        this.mohonSytLelong = mohonSytLelong;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getAlamat1() {
        return alamat1;
    }

    public void setAlamat1(String alamat1) {
        this.alamat1 = alamat1;
    }

    public String getAlamat2() {
        return alamat2;
    }

    public void setAlamat2(String alamat2) {
        this.alamat2 = alamat2;
    }

    public String getAlamat3() {
        return alamat3;
    }

    public void setAlamat3(String alamat3) {
        this.alamat3 = alamat3;
    }

    public String getAlamat4() {
        return alamat4;
    }

    public void setAlamat4(String alamat4) {
        this.alamat4 = alamat4;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNegeri() {
        return negeri;
    }

    public void setNegeri(String negeri) {
        this.negeri = negeri;
    }

    public String getNoTel1() {
        return noTel1;
    }

    public void setNoTel1(String noTel1) {
        this.noTel1 = noTel1;
    }

    public String getNoTel2() {
        return noTel2;
    }

    public void setNoTel2(String noTel2) {
        this.noTel2 = noTel2;
    }

    public String getPoskod() {
        return poskod;
    }

    public void setPoskod(String poskod) {
        this.poskod = poskod;
    }

    public String getIdSyt() {
        return idSyt;
    }

    public void setIdSyt(String idSyt) {
        this.idSyt = idSyt;
    }

    public List<SytJuruLelong> getListSytJuruLelong() {
        return listSytJuruLelong;
    }

    public void setListSytJuruLelong(List<SytJuruLelong> listSytJuruLelong) {
        this.listSytJuruLelong = listSytJuruLelong;
    }

    public String getIdSytlelong() {
        return idSytlelong;
    }

    public void setIdSytlelong(String idSytlelong) {
        this.idSytlelong = idSytlelong;
    }

    public SytJuruLelong getSytJuruLelong() {
        return sytJuruLelong;
    }

    public void setSytJuruLelong(SytJuruLelong sytJuruLelong) {
        this.sytJuruLelong = sytJuruLelong;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getNamaSyarikat() {
        return namaSyarikat;
    }

    public void setNamaSyarikat(String namaSyarikat) {
        this.namaSyarikat = namaSyarikat;
    }

    public String getNoSyarikat() {
        return noSyarikat;
    }

    public void setNoSyarikat(String noSyarikat) {
        this.noSyarikat = noSyarikat;
    }

    public PermohonanSyarikatLelong getMohonSytLelong1() {
        return mohonSytLelong1;
    }

    public void setMohonSytLelong1(PermohonanSyarikatLelong mohonSytLelong1) {
        this.mohonSytLelong1 = mohonSytLelong1;
    }

    public String getNamaNegeri() {
        return namaNegeri;
    }

    public void setNamaNegeri(String namaNegeri) {
        this.namaNegeri = namaNegeri;
    }
}
