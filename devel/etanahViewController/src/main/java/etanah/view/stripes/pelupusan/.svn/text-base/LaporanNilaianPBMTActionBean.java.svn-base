/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import java.text.ParseException;
import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.*;
import etanah.model.*;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;

/**
 *
 * @author wan.fairul
 */
@UrlBinding("/pelupusan/laporanNilaianPBMT")
public class LaporanNilaianPBMTActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(LaporanNilaianPBMTActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    KodUOMDAO kodUOMDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    KodTuntutDAO kodTuntutDAO;
    private Permohonan permohonan;
    private Pengguna pengguna;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanRujukanLuar permohonanRujukanLuar;
    private NoPt noPt;
    private BigDecimal nilai;
    private String tarikhRujukan;
    private Date trhRujukan;
    private String sebab;
    private boolean viewOnly;
    private KodBandarPekanMukim kodBPM;
    private KodUOM kodUL;
    String kodgunatanah;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int tahunP;
    private int bakiTempohPajakan;
    private BigDecimal np6;
    private BigDecimal nilaiPasaran1;
    private BigDecimal nilaiPasaran2;
    private BigDecimal nilaiPasaran3;
    private BigDecimal premium;
    private PermohonanTuntutanKos mohonTuntutKos;
    private KodKadarPremium kodKadarPremium;
    private BigDecimal peratuas;
    private BigDecimal pasaran;
    private BigDecimal harga;
    private String tempohPajakan;
    private PermohonanLaporanPelan permohonanLaporanPelan;
    private String kodTanah;
    private String kodKegunaanTanah;

    @DefaultHandler()
    public Resolution showForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        if (permohonanRujukanLuar != null) {
            nilai = permohonanRujukanLuar.getNilai();
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }
        viewOnly = Boolean.FALSE;
        return new JSP("pelupusan/laporanNilaianPBMT.jsp").addParameter("tab", "true");
    }

    public Resolution viewForm() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
        if (permohonanRujukanLuar != null) {
            nilai = permohonanRujukanLuar.getNilai();
            tarikhRujukan = dateFormat.format(permohonanRujukanLuar.getTarikhRujukan());
        }
        viewOnly = Boolean.TRUE;
        return new JSP("pelupusan/laporanNilaianPBMT.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {

        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");


        //one idPermohonan to one idHakmilik
        if (idPermohonan != null) {
            System.out.println("idPermohonan:-----------" + idPermohonan);
            permohonan = permohonanDAO.findById(idPermohonan);


            if (permohonan.getKodUrusan().getKod().equals("PHLP") || permohonan.getKodUrusan().getKod().equals("PHLA")) {
                /*
                 * TEMPORARY SINCE PHLP ONLY CATER 1 HM DATE for PAT -
                 * 30/11/2011
                 */
                List<HakmilikPermohonan> hmList = new ArrayList<HakmilikPermohonan>();
                hmList = pelupusanService.getHakmilikPermohonanListByCatatanNIdMohon("L", idPermohonan);
                if (hmList.size() > 0) {
                    hakmilikPermohonan = hmList.get(0);
                }
            } else {
                hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            }
            if (!permohonan.getKodUrusan().getKod().equals("PHLP")) {
                noPt = pelupusanService.findNoPtByIdHakmilikPermohonan(hakmilikPermohonan.getId());
                System.out.println("noPt:-----------:" + noPt);
            }

            if (!pelupusanService.findByIdMohon1(idPermohonan, "Nilaian").isEmpty()) {
                permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");

            }

            if ((hakmilikPermohonan != null) && (hakmilikPermohonan.getKodKegunaanTanah() != null)) {
                kodgunatanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
            }

            if (hakmilikPermohonan.getKodKegunaanTanah() != null) {
                kodgunatanah = kodgunatanah + " " + "(" + hakmilikPermohonan.getKodKegunaanTanah().getNama() + ")";
            }

        }
    }

    public Resolution simpan3() throws ParseException {
        logger.debug("start simpan");
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        KodCawangan caw = peng.getKodCawangan();
        InfoAudit ia = new InfoAudit();

        if (pelupusanService.findByIdMohon1(idPermohonan, "Nilaian").isEmpty()) {
            permohonanRujukanLuar = new PermohonanRujukanLuar();
            permohonanRujukanLuar.setPermohonan(permohonan);
            System.out.println("baru...");
            ia = new InfoAudit();
            ia.setDimasukOleh(peng);
            ia.setTarikhMasuk(new Date());
            permohonanRujukanLuar.setInfoAudit(ia);

            KodRujukan kodRuj = new KodRujukan();
            kodRuj.setKod("FL");
            permohonanRujukanLuar.setKodRujukan(kodRuj);
            permohonanRujukanLuar.setNamaSidang("Nilaian");
            permohonanRujukanLuar.setNilai(nilai);
            permohonanRujukanLuar.setCawangan(caw);
            trhRujukan = dateFormat.parse(tarikhRujukan);
            permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
            pelupusanService.simpanRujukLuar(permohonanRujukanLuar);

        } else {
            permohonanRujukanLuar = pelupusanService.findByIdMohon2(idPermohonan, "Nilaian");
            permohonanLaporanPelan = pelupusanService.findByIdPermohonanPelan(idPermohonan);
            hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan);
            ia = permohonanRujukanLuar.getInfoAudit();

            System.out.println("lame...");
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            if (permohonanRujukanLuar.getNamaSidang().equalsIgnoreCase("Nilaian")) {
                permohonanRujukanLuar.setInfoAudit(ia);
                permohonanRujukanLuar.setNilai(nilai);
                trhRujukan = dateFormat.parse(tarikhRujukan);
                permohonanRujukanLuar.setTarikhRujukan(trhRujukan);
                pelupusanService.simpanRujukLuar(permohonanRujukanLuar);
            }

            if (permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) {
                kodTanah = permohonanLaporanPelan.getKodTanah().getKod();
            }

            if (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null) {
                kodKegunaanTanah = hakmilikPermohonan.getKodKegunaanTanah().getKod();
            }

            if ((permohonanLaporanPelan != null && permohonanLaporanPelan.getKodTanah() != null) && (hakmilikPermohonan != null && hakmilikPermohonan.getKodKegunaanTanah() != null)) {
                kodKadarPremium = pelupusanService.findKodKadarPremiumId(kodTanah, kodKegunaanTanah);
            }

            if (kodKadarPremium != null && kodKadarPremium.getPeratusKadar() != null) {
                logger.info("------------kodKadarPremium-------------------" + kodKadarPremium.getIdKodKadarPremium());
                logger.info("------------kodKadarPremium-------------------" + kodKadarPremium.getNama());
                logger.info("------------kodKadarPremium-------------------" + kodKadarPremium.getPeratusKadar());
                peratuas = kodKadarPremium.getPeratusKadar();
            }

            if (hakmilikPermohonan != null && hakmilikPermohonan.getTempohPajakan() != null) {
                tempohPajakan = hakmilikPermohonan.getTempohPajakan().toString();
                pasaran = new BigDecimal(tempohPajakan);
                logger.info("------------hakmilikPermohonan-------------------" + hakmilikPermohonan.getTempohHakmilik() + "------tempohPajakan--------" + tempohPajakan + "----" + pasaran);
                logger.info("------------pasaran-----1--------------" + pasaran);
            }
            if (permohonanRujukanLuar != null && permohonanRujukanLuar.getNilai() != null) {
                harga = permohonanRujukanLuar.getNilai();

                logger.info("------------hakmilikPermohonan-------------------" + permohonanRujukanLuar.getNilai() + "----harga-------" + harga);
            }
            if (kodTanah.equals("01") || kodTanah.equals("02") || kodTanah.equals("03")) {
                logger.info("------------kodTanah-------------------" + kodTanah);
                if (permohonanLaporanPelan.getKodTanah() != null && hakmilikPermohonan.getKodKegunaanTanah() != null && kodKadarPremium.getPeratusKadar() != null) {
                    if(hakmilikPermohonan.getTempohPajakan() != null){
                    premium = (peratuas.multiply(harga)).multiply(pasaran);
                    }
                    else{
                        premium = peratuas.multiply(harga);
                    }
                    logger.info("------------peratuas-------------" + peratuas);
                    logger.info("---------harga--------------------" + harga);
                    logger.info("---------pasaran--------------------" + pasaran);
                    logger.info("------------premium--------------------" + premium);
                } else {
                    premium = hakmilikPermohonan.getKadarPremium();
                }
            } else {
                premium = new BigDecimal(0.0);
            }

            mohonTuntutKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(idPermohonan, "DISPRM");
            mohonTuntutKos.setAmaunTuntutan(premium);
            pelupusanService.simpanSavePermohonanTuntutanKos(mohonTuntutKos);
            hakmilikPermohonan.setKadarPremium(premium);
            pelupusanService.simpanHakmilikPermohonan(hakmilikPermohonan);
        }
        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return new JSP("pelupusan/laporanNilaianPBMT.jsp").addParameter("tab", "true");

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

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public KodUOM getKodUL() {
        return kodUL;
    }

    public void setKodUL(KodUOM kodUL) {
        this.kodUL = kodUL;
    }

    public String getSebab() {
        return sebab;
    }

    public void setSebab(String sebab) {
        this.sebab = sebab;
    }

    public KodBandarPekanMukim getKodBPM() {
        return kodBPM;
    }

    public void setKodBPM(KodBandarPekanMukim kodBPM) {
        this.kodBPM = kodBPM;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }

    public PermohonanRujukanLuar getPermohonanRujukanLuar() {
        return permohonanRujukanLuar;
    }

    public void setPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        this.permohonanRujukanLuar = permohonanRujukanLuar;
    }

    public String getTarikhRujukan() {
        return tarikhRujukan;
    }

    public void setTarikhRujukan(String tarikhRujukan) {
        this.tarikhRujukan = tarikhRujukan;
    }

    public NoPt getNoPt() {
        return noPt;
    }

    public void setNoPt(NoPt noPt) {
        this.noPt = noPt;
    }

    public String getKodgunatanah() {
        return kodgunatanah;
    }

    public void setKodgunatanah(String kodgunatanah) {
        this.kodgunatanah = kodgunatanah;
    }

    public boolean isViewOnly() {
        return viewOnly;
    }

    public void setViewOnly(boolean viewOnly) {
        this.viewOnly = viewOnly;
    }

    public BigDecimal getNilaiPasaran1() {
        return nilaiPasaran1;
    }

    public void setNilaiPasaran1(BigDecimal nilaiPasaran1) {
        this.nilaiPasaran1 = nilaiPasaran1;
    }

    public BigDecimal getNilaiPasaran2() {
        return nilaiPasaran2;
    }

    public void setNilaiPasaran2(BigDecimal nilaiPasaran2) {
        this.nilaiPasaran2 = nilaiPasaran2;
    }

    public BigDecimal getNilaiPasaran3() {
        return nilaiPasaran3;
    }

    public void setNilaiPasaran3(BigDecimal nilaiPasaran3) {
        this.nilaiPasaran3 = nilaiPasaran3;
    }

    public BigDecimal getNp6() {
        return np6;
    }

    public void setNp6(BigDecimal np6) {
        this.np6 = np6;
    }

    public int getTahunP() {
        return tahunP;
    }

    public void setTahunP(int tahunP) {
        this.tahunP = tahunP;
    }

    public int getBakiTempohPajakan() {
        return bakiTempohPajakan;
    }

    public void setBakiTempohPajakan(int bakiTempohPajakan) {
        this.bakiTempohPajakan = bakiTempohPajakan;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getKodKegunaanTanah() {
        return kodKegunaanTanah;
    }

    public void setKodKegunaanTanah(String kodKegunaanTanah) {
        this.kodKegunaanTanah = kodKegunaanTanah;
    }

    public String getKodTanah() {
        return kodTanah;
    }

    public void setKodTanah(String kodTanah) {
        this.kodTanah = kodTanah;
    }

    public BigDecimal getPasaran() {
        return pasaran;
    }

    public void setPasaran(BigDecimal pasaran) {
        this.pasaran = pasaran;
    }

    public BigDecimal getPeratuas() {
        return peratuas;
    }

    public void setPeratuas(BigDecimal peratuas) {
        this.peratuas = peratuas;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getTempohPajakan() {
        return tempohPajakan;
    }

    public void setTempohPajakan(String tempohPajakan) {
        this.tempohPajakan = tempohPajakan;
    }

    public Date getTrhRujukan() {
        return trhRujukan;
    }

    public void setTrhRujukan(Date trhRujukan) {
        this.trhRujukan = trhRujukan;
    }
}
