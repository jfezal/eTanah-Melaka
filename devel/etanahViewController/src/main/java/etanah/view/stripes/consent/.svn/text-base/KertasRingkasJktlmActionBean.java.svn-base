               /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.consent;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanUrusan;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author muhammad.amin
 */
@UrlBinding("/consent/kertas_ringkas_jktlm")
public class KertasRingkasJktlmActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    ConsentPtdService conService;
    private Permohonan permohonan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanUrusan mohonUrusanPengesahan;
    private PermohonanUrusan mohonUrusanUlasan;
    private PermohonanUrusan mohonUrusanLokasi;
    private PermohonanUrusan mohonUrusanKeadaan;
    String idHakmilik;
    private List<PermohonanRujukanLuar> listUlasanTeknikal;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    etanahActionBeanContext ctx;
    private static final Logger LOG = Logger.getLogger(KertasRingkasJktlmActionBean.class);

    @DefaultHandler
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan.getSenaraiPemohon().isEmpty()) {
            addSimpleError("Sila Masukkan Maklumat Pemohon/Penerima Terlebih Dahulu.");
        }
        return new JSP("consent/kertas_ringkas_jktlm.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        ctx = (etanahActionBeanContext) getContext();
        idHakmilik = ctx.getRequest().getParameter("idHakmilik");
        LOG.info("hakmilik :" + idHakmilik);

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        if (permohonan != null) {
            listUlasanTeknikal = conService.findSenaraiMohonRujukanByAgensiNotNull(idPermohonan);
            mohonUrusanPengesahan = conService.findMohonUrusanByPerihal(idPermohonan, "PENGESAHAN");
            mohonUrusanUlasan = conService.findMohonUrusanByPerihal(idPermohonan, "ULASAN");
            mohonUrusanLokasi = conService.findMohonUrusanByPerihal(idPermohonan, "LOKASI");
            mohonUrusanKeadaan = conService.findMohonUrusanByPerihal(idPermohonan, "KEADAAN");

            if (mohonUrusanLokasi == null || mohonUrusanKeadaan == null) {

                if (permohonan.getSenaraiKertas() != null) {
                    PermohonanKertas permohonanKertas = conService.findMohonKertasByTajuk(idPermohonan, "KERTAS JKTL");

                    if (permohonanKertas != null) {
                        for (PermohonanKertasKandungan kertasKandungan : permohonanKertas.getSenaraiKandungan()) {
                            if (kertasKandungan.getBil() == 4) {
                                mohonUrusanLokasi = new PermohonanUrusan();
                                mohonUrusanLokasi.setCatatan(kertasKandungan.getKandungan());

                            } else if (kertasKandungan.getBil() == 5) {
                                mohonUrusanKeadaan = new PermohonanUrusan();
                                mohonUrusanKeadaan.setCatatan(kertasKandungan.getKandungan());
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit infoAudit = new InfoAudit();

        PermohonanUrusan mohonUrusanPengesahanTemp = conService.findMohonUrusanByPerihal(idPermohonan, "PENGESAHAN");
        PermohonanUrusan mohonUrusanUlasanTemp = conService.findMohonUrusanByPerihal(idPermohonan, "ULASAN");
        PermohonanUrusan mohonUrusanLokasiTemp = conService.findMohonUrusanByPerihal(idPermohonan, "LOKASI");
        PermohonanUrusan mohonUrusanKeadaanTemp = conService.findMohonUrusanByPerihal(idPermohonan, "KEADAAN");

        if (mohonUrusanPengesahanTemp != null) {
            infoAudit = mohonUrusanPengesahanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonUrusanPengesahanTemp.setInfoAudit(infoAudit);

        } else {
            mohonUrusanPengesahanTemp = new PermohonanUrusan();
            mohonUrusanPengesahanTemp.setCawangan(permohonan.getCawangan());
            mohonUrusanPengesahanTemp.setPermohonan(permohonan);
            mohonUrusanPengesahanTemp.setPerihal("PENGESAHAN");
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonUrusanPengesahanTemp.setInfoAudit(infoAudit);
        }

        if (mohonUrusanUlasanTemp != null) {
            infoAudit = mohonUrusanUlasanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonUrusanUlasanTemp.setInfoAudit(infoAudit);

        } else {
            mohonUrusanUlasanTemp = new PermohonanUrusan();
            mohonUrusanUlasanTemp.setCawangan(permohonan.getCawangan());
            mohonUrusanUlasanTemp.setPermohonan(permohonan);
            mohonUrusanUlasanTemp.setPerihal("ULASAN");
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonUrusanUlasanTemp.setInfoAudit(infoAudit);
        }
        if (mohonUrusanLokasiTemp != null) {
            infoAudit = mohonUrusanLokasiTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonUrusanLokasiTemp.setInfoAudit(infoAudit);

        } else {
            mohonUrusanLokasiTemp = new PermohonanUrusan();
            mohonUrusanLokasiTemp.setCawangan(permohonan.getCawangan());
            mohonUrusanLokasiTemp.setPermohonan(permohonan);
            mohonUrusanLokasiTemp.setPerihal("LOKASI");
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonUrusanLokasiTemp.setInfoAudit(infoAudit);
        }
        if (mohonUrusanKeadaanTemp != null) {
            infoAudit = mohonUrusanKeadaanTemp.getInfoAudit();
            infoAudit.setDiKemaskiniOleh(pengguna);
            infoAudit.setTarikhKemaskini(new java.util.Date());
            mohonUrusanKeadaanTemp.setInfoAudit(infoAudit);

        } else {
            mohonUrusanKeadaanTemp = new PermohonanUrusan();
            mohonUrusanKeadaanTemp.setCawangan(permohonan.getCawangan());
            mohonUrusanKeadaanTemp.setPermohonan(permohonan);
            mohonUrusanKeadaanTemp.setPerihal("KEADAAN");
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            mohonUrusanKeadaanTemp.setInfoAudit(infoAudit);
        }

        if (mohonUrusanPengesahan != null) {
            mohonUrusanPengesahanTemp.setCatatan(mohonUrusanPengesahan.getCatatan());
        }
        if (mohonUrusanUlasan != null) {
            mohonUrusanUlasanTemp.setCatatan(mohonUrusanUlasan.getCatatan());
        }
        if (mohonUrusanLokasi != null) {
            mohonUrusanLokasiTemp.setCatatan(mohonUrusanLokasi.getCatatan());
        }
        if (mohonUrusanKeadaan != null) {
            mohonUrusanKeadaanTemp.setCatatan(mohonUrusanKeadaan.getCatatan());
        }
        conService.simpanPermohonanUrusan(mohonUrusanPengesahanTemp);
        conService.simpanPermohonanUrusan(mohonUrusanUlasanTemp);
        conService.simpanPermohonanUrusan(mohonUrusanLokasiTemp);
        conService.simpanPermohonanUrusan(mohonUrusanKeadaanTemp);

        if (listUlasanTeknikal.size() > 0) {

            for (PermohonanRujukanLuar mohonRujLuar : listUlasanTeknikal) {
                infoAudit = mohonRujLuar.getInfoAudit();
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());
                mohonRujLuar.setInfoAudit(infoAudit);
                conService.simpanRujukanLuar(mohonRujLuar);
            }
        }

        addSimpleMessage("Maklumat telah berjaya disimpan.");

        return new ForwardResolution("/WEB-INF/jsp/consent/kertas_ringkas_jktlm.jsp").addParameter("tab", "true");
    }

    public Resolution selectHakmilik() {
        LOG.info("::select hakmilik::");
        return showForm();

    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public List<PermohonanRujukanLuar> getListUlasanTeknikal() {
        return listUlasanTeknikal;
    }

    public void setListUlasanTeknikal(List<PermohonanRujukanLuar> listUlasanTeknikal) {
        this.listUlasanTeknikal = listUlasanTeknikal;
    }

    public PermohonanUrusan getMohonUrusanPengesahan() {
        return mohonUrusanPengesahan;
    }

    public void setMohonUrusanPengesahan(PermohonanUrusan mohonUrusanPengesahan) {
        this.mohonUrusanPengesahan = mohonUrusanPengesahan;
    }

    public PermohonanUrusan getMohonUrusanUlasan() {
        return mohonUrusanUlasan;
    }

    public void setMohonUrusanUlasan(PermohonanUrusan mohonUrusanUlasan) {
        this.mohonUrusanUlasan = mohonUrusanUlasan;
    }

    public PermohonanUrusan getMohonUrusanLokasi() {
        return mohonUrusanLokasi;
    }

    public void setMohonUrusanLokasi(PermohonanUrusan mohonUrusanLokasi) {
        this.mohonUrusanLokasi = mohonUrusanLokasi;
    }

    public PermohonanUrusan getMohonUrusanKeadaan() {
        return mohonUrusanKeadaan;
    }

    public void setMohonUrusanKeadaan(PermohonanUrusan mohonUrusanKeadaan) {
        this.mohonUrusanKeadaan = mohonUrusanKeadaan;
    }
}
