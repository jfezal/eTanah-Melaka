package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodPerananDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.model.Hakmilik;
import etanah.model.InfoAudit;
import etanah.model.KodDokumen;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.service.EnforceService;
import etanah.view.etanahActionBeanContext;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.text.ParseException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import etanah.model.KodCawangan;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.common.EnforcementService;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author
 */
@UrlBinding("/pelupusan/maklumat_agensi")
public class MaklumatAgensiActionBean extends AbleActionBean {

    @Inject
    EnforceService enforceService;
    @Inject
    EnforcementService enforcementService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodPerananDAO kodPerananDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    @Inject
    private etanah.Configuration conf;
    private static final Logger LOG = Logger.getLogger(MaklumatAgensiActionBean.class);
    private PermohonanKertasKandungan permohonanKertasKandungan;
    private PermohonanKertas kertas;
    private Hakmilik hakmilik;
    private KodDokumen kodDokumen;
    private Permohonan permohonan;
    private String idPermohonan;
    private String idKandungan;
    private String kodAgensi;
    private String tempatMesyuarat;
    private String tajuk;
    private String tarikhMesyuarat;
    private String jam;
    private String minit;
    private String ampm;
    private String kod;
    private String recordCount;
    private String bilanganMesyuarat;
    private List<PermohonanRujukanLuar> senaraiAgensiLuar;
    private Pengguna peng;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_agensi_luar.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        getContext().getRequest().setAttribute("view", Boolean.TRUE);
        return new JSP("pelupusan/maklumat_agensi_luar.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        System.out.println("--------------rehydrate--------------");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            try {

                kertas = enforceService.findByIdKrts(permohonan.getIdPermohonan(), "SPM");
                senaraiAgensiLuar = enforcementService.findKodRujByIDPermohonan(idPermohonan, "MK");

                if (!senaraiAgensiLuar.isEmpty()) {
                    recordCount = String.valueOf(senaraiAgensiLuar.size());
                    LOG.debug("record count ::: " + recordCount);
                }

                if (kertas != null) {
                    tajuk = kertas.getTajuk();
                    tempatMesyuarat = kertas.getTempatSidang();
                    if (kertas.getNoKertas() != null) {
                        bilanganMesyuarat = kertas.getNomborRujukan();
//                        bilanganMesyuarat = kertas.getNoKertas().toString();
                    }
                    if (kertas.getTarikhSidang() != null) {
                        tarikhMesyuarat = sdf.format(kertas.getTarikhSidang()).substring(0, 10);
                        jam = sdf.format(kertas.getTarikhSidang()).substring(11, 13);
                        if (jam.charAt(0) == '0') {
                            jam = jam.substring(1);
                        }
                        minit = sdf.format(kertas.getTarikhSidang()).substring(14, 16);
                        ampm = sdf.format(kertas.getTarikhSidang()).substring(17, 19);
                    }



                }

            } catch (Exception ex) {

                LOG.error(ex);
                ex.printStackTrace();
            }

        }

    }

    public Resolution simpan() throws ParseException {

        InfoAudit ia = new InfoAudit();


        if (kertas == null) {
            kertas = new PermohonanKertas();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
        } else {
            ia = kertas.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        kertas.setPermohonan(permohonan);
        kertas.setInfoAudit(ia);
        kertas.setTajuk(tajuk);
        kertas.setTempatSidang(tempatMesyuarat);
        kertas.setNomborRujukan(bilanganMesyuarat);
//        if (StringUtils.isNotBlank(bilanganMesyuarat)) {
//            kertas.setNoKertas(Integer.parseInt(bilanganMesyuarat));
//
//        }

        tarikhMesyuarat = tarikhMesyuarat + " " + jam + ":" + minit + " " + ampm;
        LOG.debug("tarikhMesyuarat :" + tarikhMesyuarat);

        try {
            kertas.setTarikhSidang(sdf.parse(tarikhMesyuarat));
        } catch (Exception ex) {
            LOG.error(ex);
        }
        kertas.setCawangan(peng.getKodCawangan());
        kertas.setKodDokumen(kodDokumenDAO.findById("SPM"));//SPM = Surat Panggilan Mesyuarat
        enforceService.simpanPermohonanKertas(kertas);

        if (StringUtils.isNotBlank(getContext().getRequest().getParameter("recordCount"))) {
            int row = Integer.parseInt(getContext().getRequest().getParameter("recordCount"));
            for (int i = 0; i < row; i++) {
                if (senaraiAgensiLuar.size() != 0 && i < senaraiAgensiLuar.size()) {
                    Long id = senaraiAgensiLuar.get(i).getIdRujukan();
                    if (id != null) {
                        permohonanRujukanLuar = enforceService.findByIdRujukan(id);
                    }
                } else {
                    permohonanRujukanLuar = new PermohonanRujukanLuar();
                }

                kodAgensi = getContext().getRequest().getParameter("kodAgensi" + i);
                LOG.info("kodAgensi : " + kodAgensi);
                permohonanRujukanLuar.setCawangan(permohonan.getCawangan());
                permohonanRujukanLuar.setPermohonan(permohonan);
                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setAgensi(kodAgensiDAO.findById(kodAgensi));
                permohonanRujukanLuar.setKodRujukan(kodRujukanDAO.findById("MK"));
                enforceService.simpanRujukan(permohonanRujukanLuar);

            }
        }





        boolean flag = true;

        if (flag) {
            addSimpleMessage("Maklumat telah berjaya disimpan.");
        }
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        rehydrate();
        return new ForwardResolution("/WEB-INF/jsp/pelupusan/maklumat_agensi_luar.jsp").addParameter("tab", "true");
    }

    // Click on Hapus button in HomePage
    public Resolution deleteAgensi() {
        String idRujukan = getContext().getRequest().getParameter("idRujukan");
        System.out.println("idKertas:" + idRujukan);

        try {
            if (StringUtils.isNotBlank(idRujukan)) {
                permohonanRujukanLuar = enforceService.findByIdRujukan(Long.parseLong(idRujukan));
            }
            enforcementService.deleteMesy(permohonanRujukanLuar);
        } catch (Exception e) {
            e.printStackTrace();
            addSimpleError("kesalahan dalam menghapuskan rekod");
        }
        addSimpleMessage("Rekod berjaya dihapuskan");
        rehydrate();
//        return new ForwardResolution("/WEB-INF/jsp/pelupusan/fakta_kes.jsp").addParameter("tab", "true");
        return showForm();
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public KodDokumen getKodDokumen() {
        return kodDokumen;
    }

    public void setKodDokumen(KodDokumen kodDokumen) {
        this.kodDokumen = kodDokumen;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdKandungan() {
        return idKandungan;
    }

    public void setIdKandungan(String idKandungan) {
        this.idKandungan = idKandungan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PermohonanKertas getKertas() {
        return kertas;
    }

    public void setKertas(PermohonanKertas kertas) {
        this.kertas = kertas;
    }

    public PermohonanKertasKandungan getPermohonanKertasKandungan() {
        return permohonanKertasKandungan;
    }

    public void setPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        this.permohonanKertasKandungan = permohonanKertasKandungan;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
    }

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public List<PermohonanRujukanLuar> getSenaraiAgensiLuar() {
        return senaraiAgensiLuar;
    }

    public void setSenaraiAgensiLuar(List<PermohonanRujukanLuar> senaraiAgensiLuar) {
        this.senaraiAgensiLuar = senaraiAgensiLuar;
    }

    public String getKodAgensi() {
        return kodAgensi;
    }

    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public String getTempatMesyuarat() {
        return tempatMesyuarat;
    }

    public void setTempatMesyuarat(String tempatMesyuarat) {
        this.tempatMesyuarat = tempatMesyuarat;
    }

    public String getTarikhMesyuarat() {
        return tarikhMesyuarat;
    }

    public void setTarikhMesyuarat(String tarikhMesyuarat) {
        this.tarikhMesyuarat = tarikhMesyuarat;
    }

    public String getBilanganMesyuarat() {
        return bilanganMesyuarat;
    }

    public void setBilanganMesyuarat(String bilanganMesyuarat) {
        this.bilanganMesyuarat = bilanganMesyuarat;
    }

    public Pengguna getPeng() {
        return peng;
    }

    public void setPeng(Pengguna peng) {
        this.peng = peng;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }
}
