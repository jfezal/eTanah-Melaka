/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import electric.xml.ParseException;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.KodKeputusanDAO;
import etanah.model.FasaPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodBahanBatu;
import etanah.model.KodBandarPekanMukim;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.Pihak;
import etanah.service.PelupusanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import etanah.view.etanahActionBeanContext;
import java.util.logging.Level;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.log4j.Logger;
import etanah.model.ImejLaporan;
import etanah.model.KodCawangan;
import etanah.model.KodKeputusan;
import etanah.model.KodUOM;
import etanah.model.LanjutanBayaran;
import etanah.model.LaporanTanah;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanTuntutanKos;
import etanah.service.LaporanTanahService;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Akmal
 */
@UrlBinding("/pelupusan/keputusan_rayuan")
public class KeputusanRayuanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(KeputusanRayuanActionBean.class);
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO;
    @Inject
    KodKeputusanDAO kodKeputusanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    private etanah.Configuration conf;
    private PermohonanKertas permohonanKertas;
    private KodKeputusan kod;
    private Pengguna pengguna;
    private Permohonan permohonan;
    private Pemohon pemohon;
    private String idPermohonan;
    private Date tarikhSidang;
    private String noSidang;
    private Date tarikhKeputusan;
    private String keputusan;
    private long idKertas;
    private boolean edit;
    private FasaPermohonan fasaPermohonan;
    private LanjutanBayaran lanjutBayaran;
    private KodUOM kodTempohLulus;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private Permohonan permohonanSebelum;
    private BigDecimal kadarPremiumLulus;
    private String kodNegeri;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pelupusan/rayuan/rekod_keputusan_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution viewKeputusan() {
        getContext().getRequest().setAttribute("edit", Boolean.FALSE);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri: " + kodNegeri);
        if (kodNegeri.equals("05")) {
            if (permohonan.getKodUrusan().getKod().equals("RLPTG")) //Lanjut PTG
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "08Keputusan");

                if (fasaPermohonan.getKeputusan() != null) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "13KeputusanPTG");
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) //Premium
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "08_1TerimaKeputusan");
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) //Premium
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "09TrmKptsnMMK");
            }
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) //Lanjut Tempoh Bayaran Premium (Kuasa PTD)
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "04KeputusanPTD");
            }
        } else if (kodNegeri.equals(("04"))) {
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "03BuatKeputusan");
            } else {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "12RekodKeputusan");
            }

        }
        return new JSP("pelupusan/rayuan/rekod_keputusan_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodNegeri = conf.getProperty("kodNegeri");
        logger.info("kod negeri: " + kodNegeri);
        if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "MMK");
        } else {
            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        }
        lanjutBayaran = pelupusanService.findLanjutBayaranByIdMohon(idPermohonan);
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM");
        }

        if (permohonan != null) {
            if (permohonan.getKeputusan() != null) {
                keputusan = permohonan.getKeputusan().getKod();
//            = permohonan.getTarikhKeputusan() ;
            }
            if (permohonanKertas != null) {
                tarikhSidang = permohonanKertas.getTarikhSidang();
                noSidang = permohonanKertas.getNomborRujukan();
                tarikhKeputusan = permohonanKertas.getTarikhSahKeputusan();
            }
            if (permohonan.getKodUrusan().getKod().equals("RLPTG")) //Lanjut PTG
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "08Keputusan");

                if (fasaPermohonan.getKeputusan() != null) {
                    if (fasaPermohonan.getKeputusan().getKod().equals("TG")) {
                        fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "13KeputusanPTG");
                    }
                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RAYK")) //Premium
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "08_1TerimaKeputusan");
            }
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) //Premium
            {
                fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "09TrmKptsnMMK");
                if (permohonan.getPermohonanSebelum() != null) {
                    //update table mohon_tuntut_kos for PBMT
                    List<PermohonanTuntutanKos> listPermohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdTuntut(permohonan.getPermohonanSebelum().getIdPermohonan(), "DISPRM");
                    if (!listPermohonanTuntutanKos.isEmpty()) {
                        PermohonanTuntutanKos ptk = listPermohonanTuntutanKos.get(0);
                        if (ptk != null) {
                            InfoAudit infoAudit = new InfoAudit();
                            infoAudit = ptk.getInfoAudit();
                            infoAudit.setTarikhKemaskini(new java.util.Date());
                            infoAudit.setDiKemaskiniOleh(pengguna);
                            //update value in amaun_tuntut & amaun_sebenar
                            if (ptk.getAmaunSebenar() == null && permohonan.getNilaiKeputusan() != null) {
                                logger.info(":::: update table mohon_tuntut_kos for PBMT ::" + permohonan.getPermohonanSebelum().getIdPermohonan());
                                ptk.setAmaunSebenar(ptk.getAmaunTuntutan());
                                ptk.setAmaunTuntutan(permohonan.getNilaiKeputusan());
                                ptk.setInfoAudit(infoAudit);
                                pelupusanService.simpanPermohonanTuntutanKos(ptk);
                            }
                        }

                    }

                }
            }
            if (permohonan.getKodUrusan().getKod().equals("RAYL")) //Lanjut Tempoh Bayaran Premium (Kuasa PTD)
            {
                if (kodNegeri.equals(("05"))) {
                    fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "04KeputusanPTD");
                }
                
                else if (kodNegeri.equals(("04"))) {
                    fasaPermohonan = pelupusanService.findMohonFasaByIdMohonIdPengguna(idPermohonan, "03BuatKeputusan");
                }
            }
//        String[] tname = {"permohonan"} ;
//        Object[] tvalue = {permohonan} ;
//        pemohon = pelupusanService.findPemohonByIdPemohon(idPermohonan);
        }
    }

    public Resolution refreshRayuan() {
        rehydrate();
        return new JSP("pelupusan/rayuan/rekod_keputusan_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanKeputusan() {
        keputusan = getContext().getRequest().getParameter("keputusan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String tarikhS = getContext().getRequest().getParameter("tarikSidang");
        logger.info("sadadaasdd........" + tarikhS + "sdasddsasd......." + tarikhSidang);
        SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy : hh:mm:ss");
        KodCawangan kod1 = kodCawanganDAO.findById(permohonan.getCawangan().getKod());
        try {
            if (tarikhS != null) {
                tarikhSidang = date1.parse(tarikhS);
            }
        } catch (java.text.ParseException ex) {
            java.util.logging.Logger.getLogger(KeputusanRayuanActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("idPermohonan" + idPermohonan);
        permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonan(idPermohonan);
        permohonan = permohonanDAO.findById(idPermohonan);
        // addSimpleMessage("sedang diproses");
        if (permohonan != null) {
            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
            permohonan = permohonanDAO.findById(idPermohonan);
            InfoAudit ia = new InfoAudit();
            if (permohonanKertas != null) {
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDimasukOleh(permohonanKertas.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(permohonanKertas.getInfoAudit().getTarikhMasuk());
                permohonanKertas.setTarikhSidang(tarikhSidang);

                permohonanKertas.setNomborRujukan(noSidang);
                permohonanKertas.setInfoAudit(ia);

                pelupusanService.simpanSavePermohonanKertas(permohonanKertas);
            } else {
                permohonanKertas = new PermohonanKertas();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                permohonanKertas.setTarikhSidang(tarikhSidang);
                permohonanKertas.setTajuk("Rayuan");
                permohonanKertas.setNomborRujukan(noSidang);
                permohonanKertas.setInfoAudit(ia);
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setCawangan(permohonan.getCawangan());
                pelupusanService.simpanPermohonanKertas(permohonanKertas);

            }
        }


        addSimpleMessage("Maklumat Telah Disimpan");
        return new JSP("pelupusan/rayuan/rekod_keputusan_rayuan_mlk.jsp").addParameter("tab", "true");
    }

    public Resolution simpanSyarat() {
        String tempohUOM = getContext().getRequest().getParameter("tempohUom.kod");
        String tmpoh = getContext().getRequest().getParameter("lanjutBayaran.tempoh");
        logger.info("----------" + keputusan);
        lanjutBayaran = pelupusanService.findLanjutBayaranByIdMohon(idPermohonan);
        InfoAudit ia = new InfoAudit();
        if (lanjutBayaran == null) {
            ia.setDimasukOleh(pengguna);
            ia.setTarikhMasuk(new java.util.Date());
            lanjutBayaran = new LanjutanBayaran();
            lanjutBayaran.setCawangan(permohonan.getCawangan());
            lanjutBayaran.setPermohonan(permohonan);
            lanjutBayaran.setInfoAudit(ia);
        } else {
            ia = lanjutBayaran.getInfoAudit();
            ia.setDiKemaskiniOleh(pengguna);
            ia.setTarikhKemaskini(new java.util.Date());
        }
        if (tmpoh != null && tempohUOM != null) {
            int t = Integer.parseInt(tmpoh);
            lanjutBayaran.setTempoh(t);
            kodTempohLulus = new KodUOM();
            kodTempohLulus.setKod(tempohUOM);
            lanjutBayaran.setTempohUom(kodTempohLulus);
        }
        pelupusanService.simpanLanjutanBayaran(lanjutBayaran);

        addSimpleMessage("Maklumat Telah Disimpan");
        return showForm();
    }

    public Resolution simpanPremiumLulus() {
        logger.debug("start simpan");
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        if (permohonan != null) {
            permohonanSebelum = permohonan.getPermohonanSebelum();
        }
        if (permohonan.getPermohonanSebelum() != null) {
            permohonanTuntutanKos = pelupusanService.findMohonTuntutKosByIdPermohonanAndIdTuntut(permohonanSebelum.getIdPermohonan(), "DISPRM");
        }
//        hakmilikPermohonan = pelupusanService.findByIdPermohonan(idPermohonan) ;
        String kadarPremium = permohonan.getCatatan();
        if (kadarPremium != null) {
            if (kadarPremium.equals("0.25") || kadarPremium.equals("0.5")) {
                logger.info(kadarPremium);
                double test = Double.parseDouble(kadarPremium);
                logger.info(test);
                BigDecimal a = BigDecimal.valueOf(test);
                logger.info(a);
                kadarPremiumLulus = permohonanTuntutanKos.getAmaunTuntutan().multiply(a);
                kadarPremiumLulus = permohonanTuntutanKos.getAmaunTuntutan().subtract(kadarPremiumLulus);
            } else if (kadarPremium.equals("N")) {
                kadarPremiumLulus = (BigDecimal) permohonan.getNilaiKeputusan();
            }
        }
        logger.info(kadarPremiumLulus);
        Pengguna pengg = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit ia = permohonan.getInfoAudit();
        if (ia == null) {
            ia = new InfoAudit();
            ia.setDimasukOleh(pengg);
            ia.setTarikhMasuk(new Date());
            permohonan.setInfoAudit(ia);
        } else {
            ia.setDiKemaskiniOleh(pengg);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonan.setInfoAudit(ia);
        }
        if (keputusan != null) {
            KodKeputusan kod = kodKeputusanDAO.findById(keputusan);
            permohonan.setKeputusan(kod);
            permohonan.setKeputusanOleh(pengg);
        }

//        kadarPremium = (BigDecimal)permohonan.getNilaiDipohon();

        logger.info(kadarPremiumLulus);
        if (kadarPremiumLulus != null) {
            permohonan.setNilaiKeputusan(kadarPremiumLulus);
        } else {
            permohonan.setNilaiKeputusan(null);
        }
        pelupusanService.simpanPermohonan(permohonan);

        //Add Tarikh Sidang and no Sidang

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String tarikhS = getContext().getRequest().getParameter("tarikSidang");
        logger.info("sadadaasdd........" + tarikhS + "sdasddsasd......." + tarikhSidang);
        SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy : hh:mm:ss");
        KodCawangan kod1 = kodCawanganDAO.findById(permohonan.getCawangan().getKod());
        try {
            if (tarikhS != null) {
                tarikhSidang = date1.parse(tarikhS);
            }
        } catch (java.text.ParseException ex) {
            java.util.logging.Logger.getLogger(KeputusanRayuanActionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("idPermohonan" + idPermohonan);
//       permohonanKertas = pelupusanService.findMohonanKertasByIdPermohonan(idPermohonan);
        if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "MMK");
        } else {
            permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
        }
        permohonan = permohonanDAO.findById(idPermohonan);
        // addSimpleMessage("sedang diproses");
        if (permohonan != null) {
            if (permohonan.getKodUrusan().getKod().equals("RYKN")) {
                permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "MMK");
            } else {
                permohonanKertas = pelupusanService.findKertasByKod(idPermohonan, "RMN");
            }
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonanKertas != null) {
                ia.setDiKemaskiniOleh(pengguna);
                ia.setTarikhKemaskini(new java.util.Date());
                ia.setDimasukOleh(permohonanKertas.getInfoAudit().getDimasukOleh());
                ia.setTarikhMasuk(permohonanKertas.getInfoAudit().getTarikhMasuk());
                permohonanKertas.setTarikhSidang(tarikhSidang);
                permohonanKertas.setTarikhSahKeputusan(tarikhKeputusan);
                permohonanKertas.setNomborRujukan(noSidang);
                permohonanKertas.setInfoAudit(ia);

                pelupusanService.simpanSavePermohonanKertas(permohonanKertas);
            } else {
                permohonanKertas = new PermohonanKertas();
                ia.setTarikhMasuk(new java.util.Date());
                ia.setDimasukOleh(pengguna);
                permohonanKertas.setTarikhSidang(tarikhSidang);
                permohonanKertas.setTajuk("Rayuan");
                permohonanKertas.setNomborRujukan(noSidang);
                permohonanKertas.setTarikhSahKeputusan(tarikhKeputusan);
                permohonanKertas.setInfoAudit(ia);
                permohonanKertas.setPermohonan(permohonan);
                permohonanKertas.setCawangan(permohonan.getCawangan());
                pelupusanService.simpanPermohonanKertas(permohonanKertas);

            }
        }


        addSimpleMessage("Maklumat telah berjaya disimpan.");
        return showForm();
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
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

    public Date getTarikhKeputusan() {
        return tarikhKeputusan;
    }

    public void setTarikhKeputusan(Date tarikhKeputusan) {
        this.tarikhKeputusan = tarikhKeputusan;
    }

    public Date getTarikhSidang() {
        return tarikhSidang;
    }

    public void setTarikhSidang(Date tarikhSidang) {
        this.tarikhSidang = tarikhSidang;
    }

    public String getKeputusan() {
        return keputusan;
    }

    public void setKeputusan(String keputusan) {
        this.keputusan = keputusan;
    }

    public KodKeputusan getKod() {
        return kod;
    }

    public void setKod(KodKeputusan kod) {
        this.kod = kod;
    }

    public long getIdKertas() {
        return idKertas;
    }

    public void setIdKertas(long idKertas) {
        this.idKertas = idKertas;
    }

    public String getNoSidang() {
        return noSidang;
    }

    public void setNoSidang(String noSidang) {
        this.noSidang = noSidang;
    }

    public PermohonanKertas getPermohonanKertas() {
        return permohonanKertas;
    }

    public void setPermohonanKertas(PermohonanKertas permohonanKertas) {
        this.permohonanKertas = permohonanKertas;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public FasaPermohonan getFasaPermohonan() {
        return fasaPermohonan;
    }

    public void setFasaPermohonan(FasaPermohonan fasaPermohonan) {
        this.fasaPermohonan = fasaPermohonan;
    }

    public LanjutanBayaran getLanjutBayaran() {
        return lanjutBayaran;
    }

    public void setLanjutBayaran(LanjutanBayaran lanjutBayaran) {
        this.lanjutBayaran = lanjutBayaran;
    }

    public KodUOM getKodTempohLulus() {
        return kodTempohLulus;
    }

    public void setKodTempohLulus(KodUOM kodTempohLulus) {
        this.kodTempohLulus = kodTempohLulus;
    }

    public BigDecimal getKadarPremiumLulus() {
        return kadarPremiumLulus;
    }

    public void setKadarPremiumLulus(BigDecimal kadarPremiumLulus) {
        this.kadarPremiumLulus = kadarPremiumLulus;
    }

    public Permohonan getPermohonanSebelum() {
        return permohonanSebelum;
    }

    public void setPermohonanSebelum(Permohonan permohonanSebelum) {
        this.permohonanSebelum = permohonanSebelum;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public String getKodNegeri() {
        return kodNegeri;
    }

    public void setKodNegeri(String kodNegeri) {
        this.kodNegeri = kodNegeri;
    }
}
