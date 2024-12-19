/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import java.text.ParseException;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.model.Permohonan;
import etanah.dao.OperasiPenguatkuasaanDAO;
import etanah.model.AduanLokasi;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.OperasiAgensi;
import etanah.model.OperasiPenguatkuasaan;

import etanah.model.Pengguna;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.EnforceService;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.service.common.EnforcementService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author farah.shafilla
 */
@UrlBinding("/penguatkuasaan/laporan_operasi")
public class OperasiActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(OperasiActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    OperasiPenguatkuasaanDAO operasiPenguatkuasaanDAO;
    @Inject
    EnforcementService enforcementService;
    @Inject
    EnforceService enforceService;
    @Inject
    private etanah.Configuration conf;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private OperasiPenguatkuasaan operasiPenguatkuasaan;
    private KodCawangan cawangan;
    private AduanLokasi aduanLokasi;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private String tarikhOperasi;
    private String idOperasi;
    private String lokasi;
    private List<OperasiAgensi> senaraiOperasiAgensi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_operasi.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_operasi.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");

        idOperasi = getContext().getRequest().getParameter("idOperasi");
        if (idPermohonan != null) {
            if ("04".equals(conf.getProperty("kodNegeri"))) {
                //for Melaka, only pass idPermohonan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan);
            } else {
                //for N9, pass idPermohonan & kategoriTindakan
                operasiPenguatkuasaan = enforceService.findOperasiPenguatkuasaanByIdpermohonan(idPermohonan, "S");
            }

            if (operasiPenguatkuasaan != null) {
                if (operasiPenguatkuasaan.getTarikhOperasi() != null) {
                    tarikhOperasi = sdf.format(operasiPenguatkuasaan.getTarikhOperasi()).substring(0, 10);
                    lokasi = operasiPenguatkuasaan.getLokasi();
                } else if (operasiPenguatkuasaan.getTarikhOperasi() == null) {
                    aduanLokasi = enforceService.findAduanLokasiByIdPermohonan(idPermohonan);
                    lokasi = aduanLokasi.getLokasi();
                    permohonanRujukanLuar = enforceService.findLaporanPolisByIdpermohonan(idPermohonan);
                    if (permohonanRujukanLuar != null) {
                        if (permohonanRujukanLuar.getTarikhRujukan() != null) {
                            tarikhOperasi = sdf.format(permohonanRujukanLuar.getTarikhRujukan()).substring(0, 10);
                        }
                    }
                }
                senaraiOperasiAgensi = enforcementService.findOperasiByIDOpeasi(operasiPenguatkuasaan.getIdOperasi());
            }
        }
    }

    public Resolution simpan() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        cawangan = permohonan.getCawangan();

        if (operasiPenguatkuasaan == null) {
            operasiPenguatkuasaan = new OperasiPenguatkuasaan();
        }

        operasiPenguatkuasaan.setPermohonan(permohonan);
        operasiPenguatkuasaan.setCawangan(cawangan);
        operasiPenguatkuasaan.setTarikhOperasi(sdf.parse(tarikhOperasi));
        operasiPenguatkuasaan.setLokasi(lokasi);
        if ("05".equals(conf.getProperty("kodNegeri"))) {
            //for N9, need to save into column kategoriTindakan
            operasiPenguatkuasaan.setKategoriTindakan("S"); //S = siasatan . Added by latifah.iskak 6/9/2011
        }

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = operasiPenguatkuasaan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            operasiPenguatkuasaan.setInfoAudit(ia);

        } else {
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        enforcementService.simpanOperasi(operasiPenguatkuasaan);
        logger.debug("tess1 :" + operasiPenguatkuasaan.getIdOperasi());
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        logger.debug("MaklumatLokasiActionBean::simpan::" + operasiPenguatkuasaan.getIdOperasi());
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/laporan_operasi.jsp").addParameter("tab", "true");
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public EnforcementService getEnforcementService() {
        return enforcementService;
    }

    public void setEnforcementService(EnforcementService enforcementService) {
        this.enforcementService = enforcementService;
    }

    public String getIdOperasi() {
        return idOperasi;
    }

    public void setIdOp(String idOperasi) {
        this.idOperasi = idOperasi;
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

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public void setSdf(SimpleDateFormat sdf) {
        this.sdf = sdf;
    }

    public String getTarikhOperasi() {
        return tarikhOperasi;
    }

    public void setTarikhOperasi(String tarikhOperasi) {
        this.tarikhOperasi = tarikhOperasi;
    }

    public OperasiPenguatkuasaan getOperasiPenguatkuasaan() {
        return operasiPenguatkuasaan;
    }

    public void setOperasiPenguatkuasaan(OperasiPenguatkuasaan operasiPenguatkuasaan) {
        this.operasiPenguatkuasaan = operasiPenguatkuasaan;
    }

    public List<OperasiAgensi> getSenaraiOperasiAgensi() {
        return senaraiOperasiAgensi;
    }

    public void setSenaraiOperasiAgensi(List<OperasiAgensi> senaraiOperasiAgensi) {
        this.senaraiOperasiAgensi = senaraiOperasiAgensi;
    }

    public AduanLokasi getAduanLokasi() {
        return aduanLokasi;
    }

    public void setAduanLokasi(AduanLokasi aduanLokasi) {
        this.aduanLokasi = aduanLokasi;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
