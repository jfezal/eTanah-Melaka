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
import etanah.service.BPelService;
import etanah.service.ConsentPtdService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import oracle.bpel.services.workflow.task.model.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author abu.mansur
 */

@UrlBinding("/hasil/ulasan_mmk_ns")
public class UlasanMMKNSActionBean extends AbleActionBean {
    private static final Logger LOG = Logger.getLogger(UlasanMMKNSActionBean.class);
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
    etanah.kodHasilConfig khconf;
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
//    String tarikhMesyuarat;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //denda lewat & tunggakan & semasa
    private List<Transaksi> senaraiTransaksi;
    private BigDecimal dendaLewat;
    private BigDecimal tunggakan;
    private BigDecimal semasa;
    private BigDecimal jumlah;

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("hasil/ulasan_kertas_mmk_ns.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        return new JSP("hasil/ulasan_kertas_mmk_ns.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})//, on = {"!simpan"})
    public void rehydrate() {
        LOG.info("rehydrate :start");
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

        dendaLewat = new BigDecimal(0.00);
        tunggakan = new BigDecimal(0.00);
        semasa = new BigDecimal(0.00);
        jumlah = new BigDecimal(0.00);
        
        Akaun akaun = hakmilik.getAkaunCukai();
        senaraiTransaksi = akaun.getSemuaTransaksi();
        for (Transaksi transaksi : senaraiTransaksi) {
            //denda lewat
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat")) && (transaksi.getAkaunDebit() != null && transaksi.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod denda lewat cukai tanah
                dendaLewat = dendaLewat.add(transaksi.getAmaun());
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("dendaLewat")) && (transaksi.getAkaunKredit() != null && transaksi.getAkaunKredit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod denda lewat cukai tanah
                dendaLewat = dendaLewat.subtract(transaksi.getAmaun());

            //tunggakan
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahTunggakan")) && (transaksi.getAkaunDebit() != null && transaksi.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod tunggakan cukai tanah
                tunggakan = tunggakan.add(transaksi.getAmaun());
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahTunggakan")) && (transaksi.getAkaunKredit() != null && transaksi.getAkaunKredit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod tunggakan cukai tanah
                tunggakan = tunggakan.subtract(transaksi.getAmaun());

            //semasa
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahSemasa")) && (transaksi.getAkaunDebit() != null && transaksi.getAkaunDebit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod cukai tanah semasa
                semasa = semasa.add(transaksi.getAmaun());
            if(transaksi.getKodTransaksi().getKod().equals(khconf.getProperty("cukaiTanahSemasa")) && (transaksi.getAkaunKredit() != null && transaksi.getAkaunKredit().getNoAkaun().equals(akaun.getNoAkaun()))) //kod cukai tanah semasa
                semasa = semasa.add(transaksi.getAmaun());
        }
        LOG.debug("dendaLewat : RM"+dendaLewat);
        LOG.debug("tunggakan : RM"+tunggakan);
        LOG.debug("semasa : RM"+semasa);

        jumlah = jumlah.add(semasa).add(tunggakan).add(dendaLewat);
        LOG.debug("jumlah : RM"+jumlah);

        LOG.info("rehydrate :finish");
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

        getContext().getRequest().setAttribute("edit", Boolean.TRUE);

        return new ForwardResolution("/WEB-INF/jsp/hasil/ulasan_kertas_mmk_ns.jsp").addParameter("tab", "true");
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

    public BigDecimal getDendaLewat() {
        return dendaLewat;
    }

    public void setDendaLewat(BigDecimal dendaLewat) {
        this.dendaLewat = dendaLewat;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

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

    public BigDecimal getTunggakan() {
        return tunggakan;
    }

    public void setTunggakan(BigDecimal tunggakan) {
        this.tunggakan = tunggakan;
    }

    public BigDecimal getSemasa() {
        return semasa;
    }

    public void setSemasa(BigDecimal semasa) {
        this.semasa = semasa;
    }
}