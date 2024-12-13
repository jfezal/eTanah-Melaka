/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.BarangRampasanDAO;
import etanah.model.BarangRampasan;
//import etanah.model.KodStatusBarangRampasan;
import etanah.model.OperasiPenguatkuasaan;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.InfoAudit;
import etanah.model.KodStatusBarangRampasan;
import etanah.service.BPelService;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import etanah.model.Pengguna;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/minit_lucuthak")
public class MinitLucuthakActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(MaklumatBarangTahananActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    BarangRampasanDAO barangRampasanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private BarangRampasan barangRampasan;
    private String item;
    // private int kuantiti;
    private String tempatSimpanan;
    private String catatan;
    private String idBarang;
    private String idPermohonan;
    private int kuantiti;
    private String barangRampasanStatus;
    // private String idPermohonan;
    private KodStatusBarangRampasan status;
    private List<BarangRampasan> senaraiBarangRampasan;
    private List<KodStatusBarangRampasan> senaraiKodStatus;
    private Date tarikhDilepas;
    private String dilepasNoKP;
    private String dilepasKepada;
    private BigDecimal amaunJaminan;
    private Pengguna dilepaskanOleh;
    private String stageId;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("penguatkuasaan/minit_lucuthak.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("penguatkuasaan/maklumat_barang_tahanan_view.jsp").addParameter("tab", "true");
    }

    public Resolution editBarangRampasanJaminan() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        idBarang = getContext().getRequest().getParameter("idBarang");
        barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
        if (barangRampasanStatus == null) {
//            barangRampasanStatus = barangRampasan.getStatus().getKod();
            logger.info(barangRampasanStatus);
        }

        return new JSP("penguatkuasaan/popup_edit_barang_tahanan.jsp").addParameter("popup", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }
            if (operasiPenguatkuasaan != null) {
                senaraiBarangRampasan = enforceService.findByIDOperasi(operasiPenguatkuasaan.getIdOperasi());
            }

        }
        idBarang = getContext().getRequest().getParameter("idBarang");
        if (idBarang != null) {
            barangRampasan = barangRampasanDAO.findById(Long.parseLong(idBarang));
            if (barangRampasan != null) {
                item = barangRampasan.getItem();
                kuantiti = barangRampasan.getKuantiti();
                tempatSimpanan = barangRampasan.getTempatSimpanan();
                catatan = barangRampasan.getCatatan();
//                barangRampasanStatus = barangRampasan.getStatus().getKod();
            }
        }
    }

    public Resolution edit() {
        System.out.println("edit");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (senaraiBarangRampasan != null) {
            for (int i = 1; i <= senaraiBarangRampasan.size(); i++) {
                if (i <= senaraiBarangRampasan.size()) {
                    Long id = senaraiBarangRampasan.get(i - 1).getIdBarang();
                    if (id != null) {
                        barangRampasan = barangRampasanDAO.findById(id);
                    }
                }

//        barangRampasan.setItem(barangRampasan.getItem());
//        barangRampasan.setKuantiti(barangRampasan.getKuantiti());
//        barangRampasan.setTempatSimpanan(barangRampasan.getTempatSimpanan());

                if (barangRampasan.getStatus().getKod().equals("DC") || barangRampasan.getStatus().getKod().equals("DT")
                        || barangRampasan.getStatus().getKod().equals("DS") || barangRampasan.getStatus().getKod().equals("LH")) {
                    catatan = getContext().getRequest().getParameter("catatan" + i);
                    barangRampasan.setCatatan(catatan);
                    status = new KodStatusBarangRampasan();
                    barangRampasanStatus = getContext().getRequest().getParameter("barangRampasanStatus" + i);
                    status.setKod(barangRampasanStatus);
                    barangRampasan.setStatus(status);
                }

                InfoAudit ia = barangRampasan.getInfoAudit();
                ia.setDiKemaskiniOleh(peng);
                ia.setTarikhKemaskini(new java.util.Date());
                barangRampasan.setInfoAudit(ia);
                enforceService.updateBarangRampasan(barangRampasan);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("penguatkuasaan/minit_lucuthak.jsp").addParameter("popup", "true");
    }

    public BarangRampasan getBarangRampasan() {
        return barangRampasan;
    }

    public void setBarangRampasan(BarangRampasan barangRampasan) {
        this.barangRampasan = barangRampasan;
    }

    public String getBarangRampasanStatus() {
        return barangRampasanStatus;
    }

    public void setBarangRampasanStatus(String barangRampasanStatus) {
        this.barangRampasanStatus = barangRampasanStatus;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getDilepasKepada() {
        return dilepasKepada;
    }

    public void setDilepasKepada(String dilepasKepada) {
        this.dilepasKepada = dilepasKepada;
    }

    public String getDilepasNoKP() {
        return dilepasNoKP;
    }

    public void setDilepasNoKP(String dilepasNoKP) {
        this.dilepasNoKP = dilepasNoKP;
    }

    public Pengguna getDilepaskanOleh() {
        return dilepaskanOleh;
    }

    public void setDilepaskanOleh(Pengguna dilepaskanOleh) {
        this.dilepaskanOleh = dilepaskanOleh;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getKuantiti() {
        return kuantiti;
    }

    public void setKuantiti(int kuantiti) {
        this.kuantiti = kuantiti;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<BarangRampasan> getSenaraiBarangRampasan() {
        return senaraiBarangRampasan;
    }

    public void setSenaraiBarangRampasan(List<BarangRampasan> senaraiBarangRampasan) {
        this.senaraiBarangRampasan = senaraiBarangRampasan;
    }

    public List<KodStatusBarangRampasan> getSenaraiKodStatus() {
        return senaraiKodStatus;
    }

    public void setSenaraiKodStatus(List<KodStatusBarangRampasan> senaraiKodStatus) {
        this.senaraiKodStatus = senaraiKodStatus;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public KodStatusBarangRampasan getStatus() {
        return status;
    }

    public void setStatus(KodStatusBarangRampasan status) {
        this.status = status;
    }

    public Date getTarikhDilepas() {
        return tarikhDilepas;
    }

    public void setTarikhDilepas(Date tarikhDilepas) {
        this.tarikhDilepas = tarikhDilepas;
    }

    public String getTempatSimpanan() {
        return tempatSimpanan;
    }

    public void setTempatSimpanan(String tempatSimpanan) {
        this.tempatSimpanan = tempatSimpanan;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

}
