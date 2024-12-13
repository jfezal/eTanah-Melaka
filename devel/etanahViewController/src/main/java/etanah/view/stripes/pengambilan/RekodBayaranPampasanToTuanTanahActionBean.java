/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.DokumenDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.KodStatusTerimaDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.AmbilPampasan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.LaporanPemulihanTanah;
import etanah.model.PermohonanPihakPendeposit;
import etanah.service.common.PermohonanPihakService;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanAduanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.view.etanahActionBeanContext;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/rekodBayaranToTuanTanah_Mahkamah")
public class RekodBayaranPampasanToTuanTanahActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RekodBayaranPampasanToTuanTanahActionBean.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    private Hakmilik hakmilik;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanAduanService aduanService;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private long idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String nama;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal jumTerimaPampasan;
    private BigDecimal jumPerlubayar;
    private PermohonanPihak pp;
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<PermohonanPihakPendeposit> permohonanPihakPendepositList = new ArrayList<PermohonanPihakPendeposit>();
    private LaporanPemulihanTanah lpt;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private String adaKerosakanTanah;
    private String keteranganKerosakanTanah;
    private BigDecimal kosKerosakanTanah;
    private String adaKerosakanBangunan;
    private String keteranganKerosakanBangunan;
    private BigDecimal kosKerosakanBangunan;
    private String adaKerosakanPokok;
    private String keteranganKerosakanPokok;
    private BigDecimal kosKerosakanPokok;
    private String adaKerosakanInfra;
    private String keteranganKerosakanInfra;
    private BigDecimal kosKerosakanInfra;
    private String adaKerosakanLain;
    private String keteranganKerosakanLain;
    private BigDecimal kosKerosakanLain;
    private String adaKecacatanTanah;
    private String keteranganKecacatanTanah;
    private BigDecimal kosKecacatanTanah;
    private BigDecimal jumlah;
    private BigDecimal sum;
    private boolean tunai = false;
    private boolean xtunai = false;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasanToTuanTanah.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        nama = (String) getContext().getRequest().getParameter("nama");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        if (permohonanPihak != null) {

            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahIdMP(idPermohonan, permohonanPihak.getIdPermohonanPihak());

            if (laporanPemulihanTanah != null) {
                logger.debug("laporanPemulihanTanah tidak null disini " + laporanPemulihanTanah);

                kosKerosakanTanah = laporanPemulihanTanah.getKosKerosakanTanah();
                logger.debug("kosKerosakanTanah tidak null disini " + kosKerosakanTanah);
                if (kosKerosakanTanah == null) {
                    logger.debug("kosKerosakanTanah null disini " + kosKerosakanTanah);
                    kosKerosakanTanah = BigDecimal.ZERO;
                }

                kosKerosakanBangunan = laporanPemulihanTanah.getKosKerosakanBangunan();
                logger.debug("kosKerosakanBangunan tidak null disini " + kosKerosakanBangunan);
                if (kosKerosakanBangunan == null) {
                    logger.debug("kosKerosakanBangunan null disini " + kosKerosakanBangunan);
                    kosKerosakanBangunan = BigDecimal.ZERO;
                }

                kosKerosakanPokok = laporanPemulihanTanah.getKosKerosakanPokok();
                logger.debug("kosKerosakanPokok tidak null disini " + kosKerosakanPokok);
                if (kosKerosakanPokok == null) {
                    logger.debug("kosKerosakanPokok null disini " + kosKerosakanPokok);
                    kosKerosakanPokok = BigDecimal.ZERO;
                }

                kosKerosakanInfra = laporanPemulihanTanah.getKosKerosakanInfra();
                logger.debug("kosKerosakanInfra tidak null disini " + kosKerosakanInfra);
                if (kosKerosakanInfra == null) {
                    logger.debug("kosKerosakanInfra null disini " + kosKerosakanInfra);
                    kosKerosakanInfra = BigDecimal.ZERO;
                }

                kosKerosakanLain = laporanPemulihanTanah.getKosKerosakanLain();
                logger.debug("kosKerosakanLain tidak null disini " + kosKerosakanLain);
                if (kosKerosakanLain == null) {
                    logger.debug("kosKerosakanLain null disini " + kosKerosakanLain);
                    kosKerosakanLain = BigDecimal.ZERO;
                }

                kosKecacatanTanah = laporanPemulihanTanah.getKosKecacatanTanah();
                logger.debug("kosKecacatanTanah tidak null disini " + kosKecacatanTanah);
                if (kosKecacatanTanah == null) {
                    logger.debug("kosKecacatanTanah null disini " + kosKecacatanTanah);
                    kosKecacatanTanah = BigDecimal.ZERO;
                }

                sum = kosKerosakanTanah.add(kosKerosakanBangunan.add(kosKerosakanPokok.add(kosKerosakanInfra.add(kosKerosakanLain.add(kosKecacatanTanah)))));
                logger.debug("sum total " + sum);
            }

            hakmilik = hakmilikDAO.findById(idHakmilik);
            HakmilikPermohonan hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, hakmilik.getIdHakmilik());
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanPampasan(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
//                    List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                    for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
                    ambilPampasan = pengambilanService.getAmbilPampasanByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    if (ambilPampasan != null) {
                        logger.info("ambil pampasan != null");
                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                        sum = ambilPampasan.getJumTerimaPampasan();
                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                        noDok = ambilPampasan.getNoDok();
                        tarikhDok = ambilPampasan.getTarikhDok();
                        kodBank = ambilPampasan.getKodBank();
                        logger.info("sum >> " + sum);
                        logger.info("nama >> " + nama);
//                        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                        for (AmbilPampasan ambilPampasan : ambilPampasanList1) {

//                                if (kodCaraBayaran.equals("T")) {
//                                    tunai = true;
//                                    xtunai = false;
//                                } else {
//                                    tunai = false;
//                                }
//                                noDok = ambilPampasan.getNoDok();
//                                tarikhDok = ambilPampasan.getTarikhDok();
//                                if (noDok != null) {
//                                    kodBank = ambilPampasan.getKodBank();
//                                }

//                                ambilPampasanList.add(ambilPampasan);
//                                    jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
//                                    kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
//                                    if (kodCaraBayaran.equals("T")) {
//                                        tunai = true;
//                                        xtunai=false;
//                                        noDok = "";
//                                        tarikhDok = ambilPampasan.getTarikhDok();
//                                    } else {
//                                        tunai = false;
//                                        noDok = ambilPampasan.getNoDok();
//                                        tarikhDok = ambilPampasan.getTarikhDok();
//                                        if (noDok != null) {
//                                            kodBank = ambilPampasan.getKodBank();
//                                        }
//                                    }
//                                    ambilPampasanList.add(ambilPampasan);
//                        }
//                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
//                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
//                        noDok = ambilPampasan.getNoDok();
//                        tarikhDok = ambilPampasan.getTarikhDok();
//                        kodBank = ambilPampasan.getKodBank();
                    }
                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasanToTuanTanah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> permohonanPihakList = new ArrayList<PermohonanPihak>();
                    permohonanPihakList = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());

                    if (permohonan.getKodUrusan().getKod().equals("SEK4A")) {
                        permohonanPihakPendepositList = pengambilanService.findByPendepositList(permohonan.getPermohonanSebelum().getIdPermohonan());
                        if (permohonanPihakList.size() > 0) {
                            permohonanPihak = permohonanPihakList.get(0);
                            logger.debug(permohonanPihak + "permohonanPihak");
                        }
                    } else {
                        permohonanPihakPendepositList = pengambilanService.findByPendepositList(idPermohonan);
                        if (permohonanPihakList.size() > 0) {
                            permohonanPihak = permohonanPihakList.get(0);
                            logger.debug(permohonanPihak + "permohonanPihak");
                        }
                    }
                }
            }


        }
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        noDok = (String) getContext().getRequest().getParameter("noDok");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        permohonanPihakList = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        logger.info("permohonan pihak >> " + permohonanPihak.getPihak().getNama());

//         for(PermohonanPihak pPihak : permohonanPihakList)
//         {

        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, permohonanPihak.getHakmilik().getIdHakmilik());
        if (hakmilikPermohonan != null) {
            if (pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                hakmilikPerbicaraan.setBatalRizab('T');
                hakmilikPerbicaraan.setKawasanPBT('T');
                hakmilikPerbicaraan.setPelanPembangunan('T');
                pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                if (pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(cawangan);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                    pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                ambilPampasan = pengambilanService.getAmbilPampasanByidHadir(perbicaraanKehadiran.getIdKehadiran());
                if (ambilPampasan == null) {
                    ambilPampasan = new AmbilPampasan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    ambilPampasan.setInfoAudit(info);
                    ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                }

                ambilPampasan.setJumTerimaPampasan(sum);
                ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                ambilPampasan.setNoDok(noDok);
                ambilPampasan.setTarikhDok(tarikhDok);
                ambilPampasan.setKodBank(kodBank);

                pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);

            }
        }
//         }

        addSimpleMessage(
                "Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP(
                "pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasanToTuanTanah.jsp").addParameter("tab", "true");

    }

    public Resolution showAkuanBayaranList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (hakmilikPermohonan != null) {
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                }
            }
        }

        return new JSP("pengambilan/AkuanTerimaBayaran_pop.jsp").addParameter("popup", "true");
    }

    public List<PermohonanPihak> getPermohonanPihakList() {
        return permohonanPihakList;
    }

    public void setPermohonanPihakList(List<PermohonanPihak> permohonanPihakList) {
        this.permohonanPihakList = permohonanPihakList;
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList() {
        return hakmilikPermohonanList;
    }

    public void setHakmilikPermohonanList(List<HakmilikPermohonan> hakmilikPermohonanList) {
        this.hakmilikPermohonanList = hakmilikPermohonanList;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }

    public String getIdHakmilik() {
        return idHakmilik;
    }

    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public String getIdDokumen() {
        return idDokumen;
    }

    public void setIdDokumen(String idDokumen) {
        this.idDokumen = idDokumen;
    }

    public long getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(long idPihak) {
        this.idPihak = idPihak;
    }

    public String getKodStatusTerima() {
        return kodStatusTerima;
    }

    public void setKodStatusTerima(String kodStatusTerima) {
        this.kodStatusTerima = kodStatusTerima;
    }

    public String getTajuk() {
        return tajuk;
    }

    public void setTajuk(String tajuk) {
        this.tajuk = tajuk;
    }

    public String getTarikhHantar() {
        return tarikhHantar;
    }

    public void setTarikhHantar(String tarikhHantar) {
        this.tarikhHantar = tarikhHantar;
    }

    public String getTarikhNotis() {
        return tarikhNotis;
    }

    public void setTarikhNotis(String tarikhNotis) {
        this.tarikhNotis = tarikhNotis;
    }

    public String getTarikhTampal() {
        return tarikhTampal;
    }

    public void setTarikhTampal(String tarikhTampal) {
        this.tarikhTampal = tarikhTampal;
    }

    public HakmilikPermohonan getHakmilikPermohonan() {
        return hakmilikPermohonan;
    }

    public void setHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        this.hakmilikPermohonan = hakmilikPermohonan;
    }

    public HakmilikPerbicaraan getHakmilikPerbicaraan() {
        return hakmilikPerbicaraan;
    }

    public void setHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        this.hakmilikPerbicaraan = hakmilikPerbicaraan;
    }

    public PermohonanPihak getPermohonanPihak() {
        return permohonanPihak;
    }

    public void setPermohonanPihak(PermohonanPihak permohonanPihak) {
        this.permohonanPihak = permohonanPihak;
    }

    public PermohonanTuntutanKos getPermohonanTuntutanKos() {
        return permohonanTuntutanKos;
    }

    public void setPermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        this.permohonanTuntutanKos = permohonanTuntutanKos;
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public Permohonan getPermohonanSblm() {
        return permohonanSblm;
    }

    public void setPermohonanSblm(Permohonan permohonanSblm) {
        this.permohonanSblm = permohonanSblm;
    }

    public AmbilPampasan getAmbilPampasan() {
        return ambilPampasan;
    }

    public void setAmbilPampasan(AmbilPampasan ambilPampasan) {
        this.ambilPampasan = ambilPampasan;
    }

    public BigDecimal getJumTerimaPampasan() {
        return jumTerimaPampasan;
    }

    public void setJumTerimaPampasan(BigDecimal jumTerimaPampasan) {
        this.jumTerimaPampasan = jumTerimaPampasan;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public KodCaraBayaran getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(KodCaraBayaran kodCaraBayaran) {
        this.kodCaraBayaran = kodCaraBayaran;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public PermohonanPengambilan getPermohonanPengambilan() {
        return permohonanPengambilan;
    }

    public void setPermohonanPengambilan(PermohonanPengambilan permohonanPengambilan) {
        this.permohonanPengambilan = permohonanPengambilan;
    }

    public Date getTarikhDok() {
        return tarikhDok;
    }

    public void setTarikhDok(Date tarikhDok) {
        this.tarikhDok = tarikhDok;
    }

    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
    }

    public List<AmbilPampasan> getSenaraiAmbilPampasan() {
        return senaraiAmbilPampasan;
    }

    public void setSenaraiAmbilPampasan(List<AmbilPampasan> senaraiAmbilPampasan) {
        this.senaraiAmbilPampasan = senaraiAmbilPampasan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public LaporanPemulihanTanah getLpt() {
        return lpt;
    }

    public void setLpt(LaporanPemulihanTanah lpt) {
        this.lpt = lpt;
    }

    public String getAdaKecacatanTanah() {
        return adaKecacatanTanah;
    }

    public void setAdaKecacatanTanah(String adaKecacatanTanah) {
        this.adaKecacatanTanah = adaKecacatanTanah;
    }

    public String getAdaKerosakanBangunan() {
        return adaKerosakanBangunan;
    }

    public void setAdaKerosakanBangunan(String adaKerosakanBangunan) {
        this.adaKerosakanBangunan = adaKerosakanBangunan;
    }

    public String getAdaKerosakanInfra() {
        return adaKerosakanInfra;
    }

    public void setAdaKerosakanInfra(String adaKerosakanInfra) {
        this.adaKerosakanInfra = adaKerosakanInfra;
    }

    public String getAdaKerosakanLain() {
        return adaKerosakanLain;
    }

    public void setAdaKerosakanLain(String adaKerosakanLain) {
        this.adaKerosakanLain = adaKerosakanLain;
    }

    public String getAdaKerosakanPokok() {
        return adaKerosakanPokok;
    }

    public void setAdaKerosakanPokok(String adaKerosakanPokok) {
        this.adaKerosakanPokok = adaKerosakanPokok;
    }

    public String getAdaKerosakanTanah() {
        return adaKerosakanTanah;
    }

    public void setAdaKerosakanTanah(String adaKerosakanTanah) {
        this.adaKerosakanTanah = adaKerosakanTanah;
    }

    public String getKeteranganKecacatanTanah() {
        return keteranganKecacatanTanah;
    }

    public void setKeteranganKecacatanTanah(String keteranganKecacatanTanah) {
        this.keteranganKecacatanTanah = keteranganKecacatanTanah;
    }

    public String getKeteranganKerosakanBangunan() {
        return keteranganKerosakanBangunan;
    }

    public void setKeteranganKerosakanBangunan(String keteranganKerosakanBangunan) {
        this.keteranganKerosakanBangunan = keteranganKerosakanBangunan;
    }

    public String getKeteranganKerosakanInfra() {
        return keteranganKerosakanInfra;
    }

    public void setKeteranganKerosakanInfra(String keteranganKerosakanInfra) {
        this.keteranganKerosakanInfra = keteranganKerosakanInfra;
    }

    public String getKeteranganKerosakanLain() {
        return keteranganKerosakanLain;
    }

    public void setKeteranganKerosakanLain(String keteranganKerosakanLain) {
        this.keteranganKerosakanLain = keteranganKerosakanLain;
    }

    public String getKeteranganKerosakanPokok() {
        return keteranganKerosakanPokok;
    }

    public void setKeteranganKerosakanPokok(String keteranganKerosakanPokok) {
        this.keteranganKerosakanPokok = keteranganKerosakanPokok;
    }

    public String getKeteranganKerosakanTanah() {
        return keteranganKerosakanTanah;
    }

    public void setKeteranganKerosakanTanah(String keteranganKerosakanTanah) {
        this.keteranganKerosakanTanah = keteranganKerosakanTanah;
    }

    public BigDecimal getKosKecacatanTanah() {
        return kosKecacatanTanah;
    }

    public void setKosKecacatanTanah(BigDecimal kosKecacatanTanah) {
        this.kosKecacatanTanah = kosKecacatanTanah;
    }

    public BigDecimal getKosKerosakanBangunan() {
        return kosKerosakanBangunan;
    }

    public void setKosKerosakanBangunan(BigDecimal kosKerosakanBangunan) {
        this.kosKerosakanBangunan = kosKerosakanBangunan;
    }

    public BigDecimal getKosKerosakanInfra() {
        return kosKerosakanInfra;
    }

    public void setKosKerosakanInfra(BigDecimal kosKerosakanInfra) {
        this.kosKerosakanInfra = kosKerosakanInfra;
    }

    public BigDecimal getKosKerosakanLain() {
        return kosKerosakanLain;
    }

    public void setKosKerosakanLain(BigDecimal kosKerosakanLain) {
        this.kosKerosakanLain = kosKerosakanLain;
    }

    public BigDecimal getKosKerosakanPokok() {
        return kosKerosakanPokok;
    }

    public void setKosKerosakanPokok(BigDecimal kosKerosakanPokok) {
        this.kosKerosakanPokok = kosKerosakanPokok;
    }

    public BigDecimal getKosKerosakanTanah() {
        return kosKerosakanTanah;
    }

    public void setKosKerosakanTanah(BigDecimal kosKerosakanTanah) {
        this.kosKerosakanTanah = kosKerosakanTanah;
    }

    public BigDecimal getJumPerlubayar() {
        return jumPerlubayar;
    }

    public void setJumPerlubayar(BigDecimal jumPerlubayar) {
        this.jumPerlubayar = jumPerlubayar;
    }

    public PermohonanPihak getPp() {
        return pp;
    }

    public void setPp(PermohonanPihak pp) {
        this.pp = pp;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public boolean isTunai() {
        return tunai;
    }

    public void setTunai(boolean tunai) {
        this.tunai = tunai;
    }

    public boolean isXtunai() {
        return xtunai;
    }

    public void setXtunai(boolean xtunai) {
        this.xtunai = xtunai;
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanah() {
        return laporanPemulihanTanah;
    }

    public void setLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah) {
        this.laporanPemulihanTanah = laporanPemulihanTanah;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public List<PermohonanPihakPendeposit> getPermohonanPihakPendepositList() {
        return permohonanPihakPendepositList;
    }

    public void setPermohonanPihakPendepositList(List<PermohonanPihakPendeposit> permohonanPihakPendepositList) {
        this.permohonanPihakPendepositList = permohonanPihakPendepositList;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
