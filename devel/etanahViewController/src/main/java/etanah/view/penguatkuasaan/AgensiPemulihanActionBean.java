/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.penguatkuasaan;

import etanah.dao.*;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;

import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.KodAgensi;
import etanah.model.Permohonan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.view.etanahActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import net.sourceforge.stripes.action.RedirectResolution;
import etanah.service.EnforceService;
import java.util.List;
import net.sourceforge.stripes.action.ForwardResolution;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/**
 *
 * @author latifah.iskak
 */
@UrlBinding("/penguatkuasaan/maklumat_agensi_pemulihan")
public class AgensiPemulihanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(AgensiPemulihanActionBean.class);
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    EnforceService enforceService;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    KodKementerianDAO kodKementerianDAO;
    @Inject
    PenggunaDAO penggunaDAO;
    private Permohonan permohonan;
    private String ulasan;
    private String kodAgensi;
    private String idPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private Pengguna pengguna;
    private PermohonanTandatanganDokumen mohonTandatanganDokumen;
    private String diTandatanganOleh;
    private List<Pengguna> senaraiPengguna;
    private String kodKementerian;
    private List<KodAgensi> senaraiAgensi;
    private String namaAgensi;
    private String namaKementerian;
    private String namaPengguna;
    private List<PermohonanRujukanLuar> listRujukanLuar;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi_pemulihan.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi_pemulihan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        logger.info("-----------rehydrate-------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        pengguna = (Pengguna) context.getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        senaraiPengguna = enforceService.getSenaraiPengguna();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {
                listRujukanLuar = enforceService.findPermohonanRujukanLuar(idPermohonan);
                logger.info("-----------listRujukanLuar size : -------------" + listRujukanLuar.size());

                if (!(listRujukanLuar.isEmpty())) {
                    for (int i = 0; i < listRujukanLuar.size(); i++) {
                        permohonanRujukanLuar = listRujukanLuar.get(i);
                        if (permohonanRujukanLuar.getKodDokumenRujukan() != null) {
                            if (permohonanRujukanLuar.getKodDokumenRujukan().getKod().equals("AGEPU")) {
                                if (permohonanRujukanLuar != null) {
                                    kodAgensi = permohonanRujukanLuar.getAgensi().getKod();
                                    namaAgensi = permohonanRujukanLuar.getAgensi().getNama();
                                    ulasan = permohonanRujukanLuar.getCatatan();
                                    kodKementerian = Integer.toString(kodAgensiDAO.findById(kodAgensi).getKodKementerian());
                                    namaKementerian = enforceService.findKementerian(Integer.parseInt(kodKementerian)).getNama();
                                    logger.info("kodKementerian (rehydrate): " + kodKementerian);
                                    logger.info("kodAgensi (rehydrate): " + kodAgensi);
                                    senaraiAgensi = enforceService.getSenaraiAgensi(Integer.parseInt(kodKementerian));
                                    logger.info("size senaraiAgensi (rehydrate): " + senaraiAgensi.size());

                                }
                            }
                        }
                    }
                }

                mohonTandatanganDokumen = enforceService.findPermohonanTandatanganDok(idPermohonan, "AGEPU");
                if (mohonTandatanganDokumen != null) {
                    if (mohonTandatanganDokumen.getDiTandatangan() != null) {
                        namaPengguna = penggunaDAO.findById(mohonTandatanganDokumen.getDiTandatangan()).getNama();
                        System.out.println("nama pengguna : " + namaPengguna);
                    }
                }

                if (mohonTandatanganDokumen != null) {
                    diTandatanganOleh = mohonTandatanganDokumen.getDiTandatangan();
                }
            } catch (Exception ex) {
                logger.error(ex);
                ex.printStackTrace();
            }
        }
    }

    public Resolution refreshpage() {
        return new RedirectResolution(AgensiPemulihanActionBean.class, "locate");
    }

    public Resolution simpan() {

        InfoAudit ia = new InfoAudit();
        try {
            if (permohonanRujukanLuar == null) {
                permohonanRujukanLuar = new PermohonanRujukanLuar();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = permohonanRujukanLuar.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }
            permohonanRujukanLuar.setPermohonan(permohonan);
            permohonanRujukanLuar.setKodDokumenRujukan(kodDokumenDAO.findById("AGEPU"));
            permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("AP"));
            permohonanRujukanLuar.setCatatan(ulasan);
            permohonanRujukanLuar.setCawangan(pengguna.getKodCawangan());
            if (kodAgensi != null) {
                permohonanRujukanLuar.setAgensi(kodAgensiDAO.findById(kodAgensi));
            } else {
                permohonanRujukanLuar.setAgensi(null);
            }
            permohonanRujukanLuar.setInfoAudit(ia);

            enforceService.simpanRujukan(permohonanRujukanLuar);

            if (mohonTandatanganDokumen == null) {
                mohonTandatanganDokumen = new PermohonanTandatanganDokumen();
                ia.setDimasukOleh(pengguna);
                ia.setTarikhMasuk(new java.util.Date());
            } else {
                ia = mohonTandatanganDokumen.getInfoAudit();
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
            }

            mohonTandatanganDokumen.setPermohonan(permohonan);
            mohonTandatanganDokumen.setInfoAudit(ia);
            mohonTandatanganDokumen.setCawangan(pengguna.getKodCawangan());
            mohonTandatanganDokumen.setDiTandatangan(diTandatanganOleh);
            mohonTandatanganDokumen.setKodDokumen(kodDokumenDAO.findById("AGEPU"));
            mohonTandatanganDokumen.setTrhTt(new java.util.Date());
            enforceService.simpanPermohonanTandatanganDok(mohonTandatanganDokumen);
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("Maklumat tidak dapat disimpan");
        }

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/penguatkuasaan/maklumat_agensi_pemulihan.jsp").addParameter("tab", "true");

    }

    public Resolution findListAgensi() {
        logger.info("-----------findListAgensi-------------");
        String kod = getContext().getRequest().getParameter("kod");
        logger.info("kod :" + kod);
        if (kod != null && StringUtils.isNotBlank(kod)) {
            senaraiAgensi = enforceService.getSenaraiAgensi(Integer.parseInt(kod));
            logger.info("-----------senaraiAgensi : -------------" + senaraiAgensi.size());
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("penguatkuasaan/maklumat_agensi_pemulihan.jsp").addParameter("tab", "true");
    }

    public String getDiTandatanganOleh() {
        return diTandatanganOleh;
    }

    public void setDiTandatanganOleh(String diTandatanganOleh) {
        this.diTandatanganOleh = diTandatanganOleh;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public PermohonanTandatanganDokumen getMohonTandatanganDokumen() {
        return mohonTandatanganDokumen;
    }

    public void setMohonTandatanganDokumen(PermohonanTandatanganDokumen mohonTandatanganDokumen) {
        this.mohonTandatanganDokumen = mohonTandatanganDokumen;
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

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public List<Pengguna> getSenaraiPengguna() {
        return senaraiPengguna;
    }

    public void setSenaraiPengguna(List<Pengguna> senaraiPengguna) {
        this.senaraiPengguna = senaraiPengguna;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getKodKementerian() {
        return kodKementerian;
    }

    public void setKodKementerian(String kodKementerian) {
        this.kodKementerian = kodKementerian;
    }

    public List<KodAgensi> getSenaraiAgensi() {
        return senaraiAgensi;
    }

    public void setSenaraiAgensi(List<KodAgensi> senaraiAgensi) {
        this.senaraiAgensi = senaraiAgensi;
    }

    public String getNamaAgensi() {
        return namaAgensi;
    }

    public void setNamaAgensi(String namaAgensi) {
        this.namaAgensi = namaAgensi;
    }

    public String getNamaKementerian() {
        return namaKementerian;
    }

    public void setNamaKementerian(String namaKementerian) {
        this.namaKementerian = namaKementerian;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public List<PermohonanRujukanLuar> getListRujukanLuar() {
        return listRujukanLuar;
    }

    public void setListRujukanLuar(List<PermohonanRujukanLuar> listRujukanLuar) {
        this.listRujukanLuar = listRujukanLuar;
    }
}
