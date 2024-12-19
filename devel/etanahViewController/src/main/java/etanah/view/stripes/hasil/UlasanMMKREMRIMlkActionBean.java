/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.stripes.hasil;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.Akaun;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanPihak;
import etanah.model.Transaksi;
import etanah.report.ReportUtil;
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */
@UrlBinding("/hasil/ulasan_mmk_remri_mlk")
public class UlasanMMKREMRIMlkActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(UlasanMMKREMRIMlkActionBean.class);
    private static final boolean IS_LOG_DEBUG = LOG.isDebugEnabled();

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ConsentPtdService conService;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    private ReportUtil reportUtil;
    private Permohonan permohonan;
    private Hakmilik hakmilik;
//    private Pemohon pemohon;
    private List<Pemohon> senaraiPemohon;
    private PermohonanPihak penerima;
    private HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan;
    private PermohonanKertasKandungan kertasKandungan;
    private String stageId;
    String latarBelakang;
    String huraianPentadbir;
    String syorPentadbir;
    String huraianPtg;
    String syorPtg;
    String tarikhDaftar;
    private static boolean edit = false;
    private static boolean editPPT = false;
    private static boolean editTPTG = false;
    private static boolean editPTG = false;
    private String cetak = "disabled";
//    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //denda lewat
//    private List<Transaksi> senaraiTransaksi;
//    private BigDecimal dendaLewat = BigDecimal.ZERO;
//    private BigDecimal jumlah = BigDecimal.ZERO;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("editPPT", Boolean.TRUE);
//        getContext().getRequest().setAttribute("editTPTG", Boolean.FALSE);
//        getContext().getRequest().setAttribute("editPTG", Boolean.FALSE);
        edit = true;
        editPPT = true;
        editTPTG = false;
        editPTG = false;
        return new JSP("hasil/ulasan_kertas_mmk_remri_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showFormTPTG() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("editPPT", Boolean.FALSE);
//        getContext().getRequest().setAttribute("editTPTG", Boolean.TRUE);
//        getContext().getRequest().setAttribute("editPTG", Boolean.FALSE);
        edit = true;
        editPPT = false;
        editTPTG = true;
        editPTG = false;
        return new JSP("hasil/ulasan_kertas_mmk_remri_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showFormPTG() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
//        getContext().getRequest().setAttribute("editPPT", Boolean.FALSE);
//        getContext().getRequest().setAttribute("editTPTG", Boolean.FALSE);
//        getContext().getRequest().setAttribute("editPTG", Boolean.TRUE);
        edit = true;
        editPPT = false;
        editTPTG = false;
        editPTG = true;
        return new JSP("hasil/ulasan_kertas_mmk_remri_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
//        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        return new JSP("hasil/ulasan_kertas_mmk_remri_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})//, on = {"!simpan"})
    public void rehydrate() {
        senaraiPemohon = new ArrayList<Pemohon>();

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
//            pemohon = listPemohon.get(0);
            senaraiPemohon = listPemohon;
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

        if (!(permohonan.getSenaraiKertas().isEmpty())) {
            cetak = "";

            for (int i = 0; i < permohonan.getSenaraiKertas().size(); i++) {

                PermohonanKertas perKertas = new PermohonanKertas();
                perKertas = permohonan.getSenaraiKertas().get(i);

                for (int j = 0; j < perKertas.getSenaraiKandungan().size(); j++) {

                    kertasKandungan = new PermohonanKertasKandungan();
                    kertasKandungan = perKertas.getSenaraiKandungan().get(j);
//                    tarikhMesyuarat = sdf.format(kertasKandungan.getInfoAudit().getTarikhMasuk());

                    if (kertasKandungan.getBil() == 1) {
                        latarBelakang = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 2) {
                        huraianPentadbir = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 3) {
                        syorPentadbir = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 4) {
                        huraianPtg = kertasKandungan.getKandungan();
                    } else if (kertasKandungan.getBil() == 5) {
                        syorPtg = kertasKandungan.getKandungan();
                    }
                }
            }
        }

        //denda lewat
//        Akaun akaun = hakmilik.getAkaunCukai();
//        senaraiTransaksi = akaun.getSemuaTransaksi();
//        for (Transaksi transaksi : senaraiTransaksi) {
//            if(transaksi.getKodTransaksi().getKod().equals("61014"))
//                dendaLewat = transaksi.getAmaun();
//        }
//        jumlah = hakmilik.getCukai().add(dendaLewat);
    }

    public Resolution simpan() {//throws ParseException {
        senaraiPemohon = new ArrayList<Pemohon>();

        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

//        if (tarikhMesyuarat == null) {
//            addSimpleError("Sila masukkan tarikh mesyuarat.");
//        } else {

            Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
            InfoAudit infoAudit = new InfoAudit();

            PermohonanKertas permohonanKertas = new PermohonanKertas();

            if (kertasKandungan != null) {
                LOG.debug("Id Fasa :"+kertasKandungan.getKertas().getIdKertas());
                permohonanKertas = permohonanKertasDAO.findById(kertasKandungan.getKertas().getIdKertas());
                infoAudit.setDimasukOleh(permohonanKertas.getInfoAudit().getDimasukOleh());
                infoAudit.setTarikhMasuk(permohonanKertas.getInfoAudit().getTarikhMasuk());
//                infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
                infoAudit.setDiKemaskiniOleh(pengguna);
                infoAudit.setTarikhKemaskini(new java.util.Date());

            } else {
                infoAudit.setDimasukOleh(pengguna);
                infoAudit.setTarikhMasuk(new java.util.Date());
//                infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));

            }

            BPelService service = new BPelService();
            String taskId = (String) getContext().getRequest().getSession().getAttribute("taskId");

            if (StringUtils.isNotBlank(taskId)) {
                Task t = null;
                t = service.getTaskFromBPel(taskId, pengguna);
                stageId = t.getSystemAttributes().getStage();
            }

            permohonanKertas.setInfoAudit(infoAudit);
            permohonanKertas.setCawangan(pengguna.getKodCawangan());
            permohonanKertas.setPermohonan(permohonan);
            permohonanKertas.setTajuk("MMK"+permohonan.getIdPermohonan());
            conService.simpanPermohonanKertas(permohonanKertas);

            ArrayList listUlasan = new ArrayList();
            if(StringUtils.isBlank(latarBelakang))
                latarBelakang = "TIADA DATA.";
            if(StringUtils.isBlank(huraianPentadbir))
                huraianPentadbir = "TIADA DATA.";
            if(StringUtils.isBlank(syorPentadbir))
                syorPentadbir = "TIADA DATA.";
            if(StringUtils.isBlank(huraianPtg))
                huraianPtg = "TIADA DATA.";
            if(StringUtils.isBlank(syorPtg))
                syorPtg = "TIADA DATA.";
            listUlasan.add(latarBelakang);
            listUlasan.add(huraianPentadbir);
            listUlasan.add(syorPentadbir);
            listUlasan.add(huraianPtg);
            listUlasan.add(syorPtg);

            if (kertasKandungan != null) {
                LOG.debug("Id Fasa :"+kertasKandungan.getKertas().getIdKertas()+" not null");
                if (!permohonanKertas.getSenaraiKandungan().isEmpty()) {

                    for (int j = 0; j < permohonanKertas.getSenaraiKandungan().size(); j++) {

                        PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                        kertasKdgn = permohonanKertas.getSenaraiKandungan().get(j);

                        if (kertasKdgn.getBil() == 1) {
                            kertasKdgn.setKandungan(latarBelakang);
                        } else if (kertasKdgn.getBil() == 2) {
                            kertasKdgn.setKandungan(huraianPentadbir);
                        } else if (kertasKdgn.getBil() == 3) {
                            kertasKdgn.setKandungan(syorPentadbir);
                        } else if (kertasKdgn.getBil() == 4) {
                            kertasKdgn.setKandungan(huraianPtg);
                        } else if (kertasKdgn.getBil() == 5) {
                            kertasKdgn.setKandungan(syorPtg);
                        }
                        kertasKdgn.setInfoAudit(infoAudit);
                        conService.simpanPermohonanKertasKandungan(kertasKdgn);
                    }
                }

            } else {

                for (int i = 0; i < listUlasan.size(); i++) {

                    String ulasan = (String) listUlasan.get(i);

//                    try {
//                        infoAudit.setTarikhMasuk(sdf.parse(tarikhMesyuarat));
//                    } catch (java.text.ParseException ex) {
//                        LOG.error(ex);
//                    }

                    PermohonanKertasKandungan kertasKdgn = new PermohonanKertasKandungan();
                    kertasKdgn.setKertas(permohonanKertas);
                    kertasKdgn.setBil(i + 1);
                    kertasKdgn.setInfoAudit(infoAudit);
                    kertasKdgn.setKandungan(ulasan);
                    kertasKdgn.setCawangan(permohonan.getCawangan());
                    conService.simpanPermohonanKertasKandungan(kertasKdgn);
                }
            }

            addSimpleMessage("Maklumat telah berjaya disimpan.");
//        }

        HakmilikPermohonan hp = new HakmilikPermohonan();

        hp = permohonan.getSenaraiHakmilik().get(0);
        hakmilik = hakmilikDAO.findById(hp.getHakmilik().getIdHakmilik());

        String[] tname = {"permohonan"};
        Object[] model = {permohonan};

        List<Pemohon> listPemohon;
        listPemohon = pemohonDAO.findByEqualCriterias(tname, model, null);

        if (!(listPemohon.isEmpty())) {
//            pemohon = listPemohon.get(0);
            senaraiPemohon = listPemohon;
        }

        List<PermohonanPihak> listPenerima;
        listPenerima = permohonan.getSenaraiPihak();

        if (!(listPenerima.isEmpty())) {
            penerima = listPenerima.get(0);
        }

        List<HakmilikPihakBerkepentingan> listPB;
        listPB = hakmilik.getSenaraiPihakBerkepentingan();

        if (!(listPB.isEmpty())) {
            hakmilikPihakBerkepentingan = listPB.get(0);
        }

        tarikhDaftar = sdf.format(hakmilik.getTarikhDaftar());

//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        cetak = "";

        return new ForwardResolution("/WEB-INF/jsp/hasil/ulasan_kertas_mmk_remri_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution cetakMMK() throws FileNotFoundException{
         etanahActionBeanContext ctx = (etanahActionBeanContext) getContext();
        Pengguna p = ctx.getUser();
        String idPermohonan = (String)getContext().getRequest().getSession().getAttribute("idPermohonan");
        Date now1 = new Date();
        File f = null;
        String tarikh = (new SimpleDateFormat("ddMMyyyyhhmmss")).format(now1);
        String documentPath = File.separator + "tmp" + File.separator;
        String path = tarikh + File.separator + String.valueOf(idPermohonan);
        reportUtil.generateReport("CONS_HSL_KP_MMK_MLK.rdf",
                new String[]{"p_id_mohon"},
                new String[]{idPermohonan},
                documentPath + path, null);

        f = new File(documentPath + path);
        FileInputStream fis = new FileInputStream(f);
        return new StreamingResolution("application/pdf", fis);
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public List<Pemohon> getSenaraiPemohon() {
        return senaraiPemohon;
    }

    public void setSenaraiPemohon(List<Pemohon> senaraiPemohon) {
        this.senaraiPemohon = senaraiPemohon;
    }

//    public Pemohon getPemohon() {
//        return pemohon;
//    }
//
//    public void setPemohon(Pemohon pemohon) {
//        this.pemohon = pemohon;
//    }

    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public String getHuraianPtg() {
        return huraianPtg;
    }

    public void setHuraianPtg(String huraianPtg) {
        this.huraianPtg = huraianPtg;
    }

    public String getLatarBelakang() {
        return latarBelakang;
    }

    public void setLatarBelakang(String latarBelakang) {
        this.latarBelakang = latarBelakang;
    }

    public String getSyorPtg() {
        return syorPtg;
    }

    public void setSyorPtg(String syorPtg) {
        this.syorPtg = syorPtg;
    }

    public String getHuraianPentadbir() {
        return huraianPentadbir;
    }

    public void setHuraianPentadbir(String huraianPentadbir) {
        this.huraianPentadbir = huraianPentadbir;
    }

    public String getSyorPentadbir() {
        return syorPentadbir;
    }

    public void setSyorPentadbir(String syorPentadbir) {
        this.syorPentadbir = syorPentadbir;
    }

//    public String getTarikhMesyuarat() {
//        return tarikhMesyuarat;
//    }
//
//    public void setTarikhMesyuarat(String tarikhMesyuarat) {
//        this.tarikhMesyuarat = tarikhMesyuarat;
//    }

    public HakmilikPihakBerkepentingan getHakmilikPihakBerkepentingan() {
        return hakmilikPihakBerkepentingan;
    }

    public void setHakmilikPihakBerkepentingan(HakmilikPihakBerkepentingan hakmilikPihakBerkepentingan) {
        this.hakmilikPihakBerkepentingan = hakmilikPihakBerkepentingan;
    }

    public PermohonanPihak getPenerima() {
        return penerima;
    }

    public void setPenerima(PermohonanPihak penerima) {
        this.penerima = penerima;
    }

//    public BigDecimal getDendaLewat() {
//        return dendaLewat;
//    }
//
//    public void setDendaLewat(BigDecimal dendaLewat) {
//        this.dendaLewat = dendaLewat;
//    }
//
//    public BigDecimal getJumlah() {
//        return jumlah;
//    }
//
//    public void setJumlah(BigDecimal jumlah) {
//        this.jumlah = jumlah;
//    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public PermohonanKertasKandungan getFasaU() {
        return kertasKandungan;
    }

    public void setFasaU(PermohonanKertasKandungan kertasKandungan) {
        this.kertasKandungan = kertasKandungan;
    }

    public String getCetak() {
        return cetak;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        UlasanMMKREMRIMlkActionBean.edit = edit;
    }

    public boolean isEditPPT() {
        return editPPT;
    }

    public void setEditPPT(boolean editPPT) {
        UlasanMMKREMRIMlkActionBean.editPPT = editPPT;
    }

    public boolean isEditPTG() {
        return editPTG;
    }

    public void setEditPTG(boolean editPTG) {
        UlasanMMKREMRIMlkActionBean.editPTG = editPTG;
    }

    public boolean isEditTPTG() {
        return editTPTG;
    }

    public void setEditTPTG(boolean editTPTG) {
        UlasanMMKREMRIMlkActionBean.editTPTG = editTPTG;
    }

    public void setCetak(String cetak) {
        this.cetak = cetak;
    }
}
