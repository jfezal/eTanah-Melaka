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
import etanah.dao.KodCaraBayaranDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
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
import etanah.model.Pemohon;
import etanah.model.Pihak;
import etanah.service.common.PermohonanPihakService;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanAduanService;
import etanah.service.PengambilanService1;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/rekodBayaranTuntutanToPemohon")
public class RekodBayaranTuntutanToPemohonActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraBayaranDAO kodCaraBayaranDAO;
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
    PengambilanService1 pengambilanService1;
    @Inject
    PermohonanSupayaBantahanService permohonanSupayaBantahanService;
    private static final Logger logger = Logger.getLogger(RekodBayaranTuntutanToPemohonActionBean.class);
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihak pPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private long idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
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
    private Pemohon pemohon;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private String item;
    private BigDecimal amaunTuntutan;
    private BigDecimal amaunSebenar;
    private BigDecimal amaunSeunit;
    private String catatan;
    private String namaPemohon;
    private Pihak pihak;
    private KodCawangan cawangan;
    private String idKos;
    private Long idPermohonanPihak;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private BigDecimal kosKerosakanTanah;
    private BigDecimal kosKerosakanBangunan;
    private BigDecimal kosKerosakanPokok;
    private BigDecimal kosKerosakanInfra;
    private BigDecimal kosKerosakanLain;
    private BigDecimal kosKecacatanTanah;
    private BigDecimal sum;
    private boolean display = false;

    @DefaultHandler
    public Resolution showForm() {
        getContext().getRequest().setAttribute("showCek", Boolean.TRUE);
        logger.info("showCek");
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranTuntutanToPemohon.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        logger.info("showEFT");
        getContext().getRequest().setAttribute("showEFT", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranTuntutanToPemohon.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        String display1 = (String) getContext().getRequest().getParameter("display");
        logger.debug("display value is: " + display1);

        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            pPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pemohon.getPihak().getIdPihak());
            if (pPihak != null) {
//            permohonanTuntutanKos = pengambilanService1.findTuntutanByIdPermohonanPihak(permohonanPihak.getIdPermohonanPihak());
//            if (permohonanTuntutanKos != null) {
//                amaunTuntutan = permohonanTuntutanKos.getAmaunTuntutan();
//            }
                laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahIdMP(idPermohonan, pPihak.getIdPermohonanPihak());

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
                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if (perbicaraanKehadiran != null) {
                        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        int c = 1;
                        int counter = ambilPampasanList1.size() - c;
                        for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                            ambilPampasan = ambilPampasanList1.get(counter);
//                            ambilPampasan = pengambilanService.getAmbilPampasanByidHadir(perbicaraanKehadiran.getIdKehadiran());
                            if (ambilPampasan != null) {
                                logger.info("ambil pampasan != null");
                                sum = ambilPampasan.getJumTerimaPampasan();
                                kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                                noDok = ambilPampasan.getNoDok();
                                tarikhDok = ambilPampasan.getTarikhDok();
                                kodBank = ambilPampasan.getKodBank();
                            }
                        }
                    }
                }
                permohonanTuntutanKos = pengambilanService1.findTuntutanByIdPermohonanPihak(pPihak.getIdPermohonanPihak());
                if (permohonanTuntutanKos != null) {
                    amaunTuntutan = permohonanTuntutanKos.getAmaunTuntutan();
                    catatan = permohonanTuntutanKos.getCatatan();
                }
            }
        }
        if (display1.equalsIgnoreCase("true")) {
            logger.debug("display true::view");
            getContext().getRequest().setAttribute("showCek", Boolean.TRUE);
        } else {
            logger.debug("display true::edit");
            getContext().getRequest().setAttribute("showEFT", Boolean.TRUE);
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranTuntutanToPemohon.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);

            if (pihak != null) {
                pihak = pengambilanService.findByIdPihak(pemohon.getPihak().getIdPihak());
                namaPemohon = pihak.getNama();
                logger.debug("namaPemohon" + namaPemohon);
            }

            List<HakmilikPermohonan> senaraiHakmilikPermohonan = new ArrayList<HakmilikPermohonan>();
            senaraiHakmilikPermohonan = permohonan.getSenaraiHakmilik();
            if (senaraiHakmilikPermohonan.size() > 0) {
                hakmilik = senaraiHakmilikPermohonan.get(0).getHakmilik();
                if (hakmilik != null) {
                    List<PermohonanPihak> senaraiPermohonanPihak = new ArrayList<PermohonanPihak>();
                    senaraiPermohonanPihak = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
                    if (senaraiPermohonanPihak.size() > 0) {
                        permohonanPihak = senaraiPermohonanPihak.get(0);
                        logger.debug(permohonanPihak + "permohonanPihak");
                    }
                }
            }
        }
    }

    public Resolution simpan() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan caw = permohonan.getCawangan();
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pemohon.getPihak().getIdPihak());

            if (permohonanPihak != null) {
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, permohonanPihak.getHakmilik().getIdHakmilik());
                if (hakmilikPermohonan != null) {
                    if (pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                        hakmilikPerbicaraan = new HakmilikPerbicaraan();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        hakmilikPerbicaraan.setInfoAudit(info);
                        hakmilikPerbicaraan.setCawangan(caw);
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
                            infoAudit.setDimasukOleh(pengguna);
                            perbicaraanKehadiran.setInfoAudit(infoAudit);
                            perbicaraanKehadiran.setCawangan(caw);
                            perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                            perbicaraanKehadiran.setPihak(permohonanPihak);
                            pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                        }
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                            InfoAudit info = new InfoAudit();
                            if (ambilPampasan == null) {
                                ambilPampasan = new AmbilPampasan();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                ambilPampasan.setInfoAudit(info);
                                ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                                ambilPampasan.setJumTerimaPampasan(sum);
                                ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                            } else {
                                info = ambilPampasan.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new java.util.Date());
                                ambilPampasan.setJumTerimaPampasan(sum);
                                ambilPampasan.setNoDok(noDok);
                                ambilPampasan.setTarikhDok(tarikhDok);
                                ambilPampasan.setKodBank(kodBank);
                            }
                            pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        }
                    }
                }
//            permohonanTuntutanKos = new PermohonanTuntutanKos();
//            infoAudit = new InfoAudit();
//            infoAudit.setDimasukOleh(pengguna);
//            infoAudit.setTarikhMasuk(new java.util.Date());
//            permohonanTuntutanKos.setPermohonan(permohonan);
//            permohonanTuntutanKos.setInfoAudit(infoAudit);
//            permohonanTuntutanKos.setCawangan(permohonan.getCawangan());
//            permohonanTuntutanKos.setPihak(permohonanPihak);
//            permohonanTuntutanKos.setHakmilikPermohonan(hakmilikPermohonan);
//            permohonanTuntutanKos.setAmaunTuntutan(amaunTuntutan);
//            permohonanTuntutanKos.setItem("bayaran tuan tanah kepada pemohon urusan bantahan mahkamah");
//            pengambilanService.simpanTuntutanKos(permohonanTuntutanKos);
            }
        }

//        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showCek", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
//        pihakDetails();
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranTuntutanToPemohon.jsp").addParameter("tab", "true");
    }

    public Resolution simpanEFT() throws ParseException {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan caw = permohonan.getCawangan();
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        for (Pemohon pemohon : permohonan.getSenaraiPemohon()) {
            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, pemohon.getPihak().getIdPihak());

            if (permohonanPihak != null) {
                hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, permohonanPihak.getHakmilik().getIdHakmilik());
                if (hakmilikPermohonan != null) {
                    if (pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                        hakmilikPerbicaraan = new HakmilikPerbicaraan();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(pengguna);
                        info.setTarikhMasuk(new java.util.Date());
                        hakmilikPerbicaraan.setInfoAudit(info);
                        hakmilikPerbicaraan.setCawangan(caw);
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
                            infoAudit.setDimasukOleh(pengguna);
                            perbicaraanKehadiran.setInfoAudit(infoAudit);
                            perbicaraanKehadiran.setCawangan(caw);
                            perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                            perbicaraanKehadiran.setPihak(permohonanPihak);
                            pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                        }
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        if (perbicaraanKehadiran != null) {
                            ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                            InfoAudit info = new InfoAudit();
                            if (ambilPampasan == null) {
                                ambilPampasan = new AmbilPampasan();
                                info.setDimasukOleh(pengguna);
                                info.setTarikhMasuk(new java.util.Date());
                                ambilPampasan.setInfoAudit(info);
                                ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                                ambilPampasan.setJumTerimaPampasan(sum);
                                ambilPampasan.setKodCaraBayaran(kodCaraBayaranDAO.findById("EF"));
                            } else {
                                info = ambilPampasan.getInfoAudit();
                                info.setDiKemaskiniOleh(pengguna);
                                info.setTarikhKemaskini(new java.util.Date());
                                ambilPampasan.setJumTerimaPampasan(sum);
                                ambilPampasan.setNoDok(noDok);
                                ambilPampasan.setTarikhDok(tarikhDok);
                                ambilPampasan.setKodBank(kodBank);
                            }
                            pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        }
                    }
                }
            }
        }

//        }

        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showEFT", Boolean.TRUE);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
//        pihakDetails();
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranTuntutanToPemohon.jsp").addParameter("tab", "true");
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

    public BigDecimal getAmaunSebenar() {
        return amaunSebenar;
    }

    public void setAmaunSebenar(BigDecimal amaunSebenar) {
        this.amaunSebenar = amaunSebenar;
    }

    public BigDecimal getAmaunSeunit() {
        return amaunSeunit;
    }

    public void setAmaunSeunit(BigDecimal amaunSeunit) {
        this.amaunSeunit = amaunSeunit;
    }

    public BigDecimal getAmaunTuntutan() {
        return amaunTuntutan;
    }

    public void setAmaunTuntutan(BigDecimal amaunTuntutan) {
        this.amaunTuntutan = amaunTuntutan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public void setPemohon(Pemohon pemohon) {
        this.pemohon = pemohon;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public Pihak getPihak() {
        return pihak;
    }

    public void setPihak(Pihak pihak) {
        this.pihak = pihak;
    }

    public KodCawangan getCawangan() {
        return cawangan;
    }

    public void setCawangan(KodCawangan cawangan) {
        this.cawangan = cawangan;
    }

    public String getIdKos() {
        return idKos;
    }

    public void setIdKos(String idKos) {
        this.idKos = idKos;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(Long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
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

    public PermohonanPihak getpPihak() {
        return pPihak;
    }

    public void setpPihak(PermohonanPihak pPihak) {
        this.pPihak = pPihak;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
