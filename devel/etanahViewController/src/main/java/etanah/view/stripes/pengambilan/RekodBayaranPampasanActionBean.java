/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
//import antlr.StringUtils;
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
import etanah.service.common.PermohonanPihakService;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.PengambilanAduanService;
import etanah.service.PermohonanSupayaBantahanService;
import etanah.service.common.PerbicaraanService;
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
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author massita
 */
@UrlBinding("/pengambilan/rekodBayaranToTuanTanah_PHLL")
public class RekodBayaranPampasanActionBean extends AbleActionBean {

    private static Logger logger = Logger.getLogger(RekodBayaranPampasanActionBean.class);
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
    @Inject
    private PerbicaraanService perbicaraanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private LaporanPemulihanTanah laporanPemulihanTanah;
    private String idPermohonan;
    private String idHakmilik;
    private long idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal sum;
    private BigDecimal jumTerimaPampasan;
    private BigDecimal jumPerlubayar;
    private PermohonanPihak pp;
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    private List<PermohonanPihak> permohonanPihakList;
    private List<PermohonanPihak> senaraiPermohonanPihak;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList;
    private List<PerbicaraanKehadiran> perbicaraanKehadiranList1;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<PerbicaraanKehadiran> senaraiDetailPembayaran;
    private LaporanPemulihanTanah lpt;
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
    private boolean tunai = false;
    private boolean xtunai = false;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        hakmilik = hakmilikDAO.findById(idHakmilik);

        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
//        senaraiPermohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, idHakmilik, idPihak);
        if (permohonanPihak != null) {
            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanah(idPermohonan, hakmilikPermohonan.getId(), permohonanPihak.getIdPermohonanPihak());

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


            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
//                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                logger.info("id mp : "+ permohonanPihak.getIdPermohonanPihak());
                logger.info("id bicara : "+ hakmilikPerbicaraan.getIdPerbicaraan());
                perbicaraanKehadiranList1 = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                logger.info("perbicaraanKehadiranList1 : "+ perbicaraanKehadiranList1.size());
                for (PerbicaraanKehadiran pk : perbicaraanKehadiranList1) {
                    if (pk != null) {
                        logger.info("id hadir : " + pk.getIdKehadiran());
                        logger.info("id mp : " + permohonanPihak.getIdPermohonanPihak());
//                        senaraiDetailPembayaran = pengambilanService.getPerbicaraanKehadiranList(pk.getIdKehadiran());
                        ambilPampasan = pengambilanService.getAmbilPampasanByidHadir(pk.getIdKehadiran());
                        if (ambilPampasan != null) {
                            jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                            kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                            noDok = ambilPampasan.getNoDok();
                            tarikhDok = ambilPampasan.getTarikhDok();
                            kodBank = ambilPampasan.getKodBank();
                        }


//                        ambilPampasanList = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                        for (AmbilPampasan ap : ambilPampasanList) {
//                            if (ap != null) {
//                                jumTerimaPampasan = ap.getJumTerimaPampasan();
//                                kodCaraBayaran = ap.getKodCaraBayaran();
//                                noDok = ap.getNoDok();
//                                tarikhDok = ap.getTarikhDok();
//                                kodBank = ap.getKodBank();
//                            }
//                        }
                    }


//                        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanList(pk.getIdKehadiran());
//                            ambilPampasanList = pengambilanService.getAmbilPampasanList(pk.getIdKehadiran());

//                         for (AmbilPampasan ambilPampasan : ambilPampasanList) {
//                                    jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
//                                    kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
//                                    if (kodCaraBayaran.equals("T")) {
//                                        tunai = true;
//                                        xtunai=false;
//                                    } else {
//                                        tunai = false;
//                                    }
//                                    noDok = ambilPampasan.getNoDok();
//                                    tarikhDok = ambilPampasan.getTarikhDok();
//                                    if (noDok != null) {
//                                        kodBank = ambilPampasan.getKodBank();
//                                    }
//
//                                    ambilPampasanList.add(ambilPampasan);
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
//                                }
//                            jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
//                            kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
//                            noDok = ambilPampasan.getNoDok();
//                            tarikhDok = ambilPampasan.getTarikhDok();
//                            kodBank = ambilPampasan.getKodBank();

//                    }
                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasan.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        ambilPampasanList = new ArrayList<AmbilPampasan>();
        permohonanPihakList = pengambilanService.getSenaraiPermohonPihak(idPermohonan);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH3(hakmilikPermohonan.getId());
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                        long idPihak = hp.getPihak().getIdPihak();
                        // permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                        List<PermohonanPihak> permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getNoHakmilik(), idPihak);
//                        idPermohonanSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
//                        permohonanPihakSblm = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonanSblm, hakmilik.getNoHakmilik(), idPihak);
                        for (PermohonanPihak permohonanPihak : permohonanPihakList) {
                            if (hakmilikPerbicaraan != null) {
                                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                                if (perbicaraanKehadiran != null) {
                                    List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                    for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                        ambilPampasanList.add(ambilPampasan);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Resolution simpan() {
        logger.info("::Simpan Function::");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        permohonanPihakList = pengambilanService.getSenaraiPermohonPihakByIdHakmilik(idPermohonan, hakmilik.getIdHakmilik());
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
//         for(PermohonanPihak pPihak : permohonanPihakList)
//         {
        logger.info("::idhakmilik:: " + idHakmilik);
        logger.info("::idPihak:: " + idPihak);
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
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
            logger.info("::hakmilikPerbicaraan:: " + hakmilikPerbicaraan.getIdPerbicaraan());
//            PermohonanPihak pPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
            permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
            logger.info("::PermohonanPihak:: " + permohonanPihak.getIdPermohonanPihak());
            if (hakmilikPerbicaraan != null) {
                logger.info("::hakmilikPerbicaraan x null:: ");

                perbicaraanKehadiranList1 = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                logger.info("::masuk perbicaraanKehadiranList1 :: " + perbicaraanKehadiranList1.size());

                if (perbicaraanKehadiranList1.isEmpty()) {
                    logger.info("perbicaraanKehadiran == null");
                    PerbicaraanKehadiran pk = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    pk.setInfoAudit(infoAudit);
                    pk.setCawangan(cawangan);
                    pk.setPerbicaraan(hakmilikPerbicaraan);
                    pk.setPihak(permohonanPihak);
                    pengambilanService.saveOrUpdatePerbicaraanKehadiran(pk);
                }

                perbicaraanKehadiranList1 = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                for (PerbicaraanKehadiran pk : perbicaraanKehadiranList1) {
                    logger.info(":: masuk for :: ");
                    if (pk == null) {
                        logger.info("perbicaraanKehadiran == null");
                        pk = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        pk.setInfoAudit(infoAudit);
                        pk.setCawangan(cawangan);
                        pk.setPerbicaraan(hakmilikPerbicaraan);
                        pk.setPihak(permohonanPihak);
                        pengambilanService.saveOrUpdatePerbicaraanKehadiran(pk);
                    }

                    logger.info("::perbicaraanKehadiran x null:: ");
                    ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(pk.getIdKehadiran());
                    logger.info(":: ambilPampasan ::");
                    InfoAudit info = new InfoAudit();
                    if (ambilPampasan == null) {
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        ambilPampasan = new AmbilPampasan();
                    } else {
                        info = ambilPampasan.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());
                    }
                    ambilPampasan.setInfoAudit(info);
                    ambilPampasan.setPerbicaraanKehadiran(pk);
                    ambilPampasan.setJumTerimaPampasan(sum);
                    ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                    ambilPampasan.setNoDok(noDok);
                    ambilPampasan.setTarikhDok(tarikhDok);
                    ambilPampasan.setKodBank(kodBank);
                    pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);

                }

//                if (notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
//                    logger.info("perbicaraanKehadiran == null");
////                if (pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
//                    perbicaraanKehadiran = new PerbicaraanKehadiran();
//                    InfoAudit infoAudit = new InfoAudit();
//                    infoAudit.setTarikhMasuk(new java.util.Date());
//                    infoAudit.setDimasukOleh(peng);
//                    perbicaraanKehadiran.setInfoAudit(infoAudit);
//                    perbicaraanKehadiran.setCawangan(cawangan);
//                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
//                    perbicaraanKehadiran.setPihak(permohonanPihak);
//                    pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
//                }
//
//                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
////                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
////                logger.info("::perbicaraanKehadiran :: "+perbicaraanKehadiran.getIdKehadiran());
//                for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
//                    if (pk != null) {
//                        logger.info("::perbicaraanKehadiran x null:: ");
//                        ambilPampasan = new AmbilPampasan();
//                        ambilPampasan.setPerbicaraanKehadiran(pk);
//                        ambilPampasan.setJumTerimaPampasan(sum);
//                        ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
//                        ambilPampasan.setNoDok(noDok);
//                        ambilPampasan.setTarikhDok(tarikhDok);
//                        ambilPampasan.setKodBank(kodBank);
//                        InfoAudit info = new InfoAudit();
//                        info.setDimasukOleh(peng);
//                        info.setTarikhMasuk(new java.util.Date());
//                        ambilPampasan.setInfoAudit(info);
//                        pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
//                    }
//                }
            }
        }
//         }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        tunai = false;
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/melaka/bantahanmahkamah/rekodBayaranPampasan.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        showAkuanBayaranList();
        return new RedirectResolution("pengambilan/AkuanTerimaBayaran_pop.jsp").addParameter("popup", "true");
    }

    public Resolution showAkuanBayaranList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (hakmilikPermohonan != null) {
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
//                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                PermohonanPihak permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                perbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                for (PerbicaraanKehadiran pk : perbicaraanKehadiranList) {
                    logger.info("perbicaraanKehadiranList : " + perbicaraanKehadiranList.size());
                    if (pk != null) {
                        senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(pk.getIdKehadiran());
                    }
                }
            }
        }

        return new JSP("pengambilan/AkuanTerimaBayaran_pop.jsp").addParameter("popup", "true");
    }

    public Resolution deleteSingle() {
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        AmbilPampasan ap = new AmbilPampasan();
        String id = getContext().getRequest().getParameter("idAmbilPampasan");
        ap = pengambilanService.getAmbilPampasanByidAmbil(Long.parseLong(id));
        logger.debug("::delete single record::");
        if (ap != null) {
            logger.debug("::masuk sini::");
            pengambilanService.delete(ap);
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

    public List<PerbicaraanKehadiran> getSenaraiDetailPembayaran() {
        return senaraiDetailPembayaran;
    }

    public void setSenaraiDetailPembayaran(List<PerbicaraanKehadiran> senaraiDetailPembayaran) {
        this.senaraiDetailPembayaran = senaraiDetailPembayaran;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public LaporanPemulihanTanah getLaporanPemulihanTanah() {
        return laporanPemulihanTanah;
    }

    public void setLaporanPemulihanTanah(LaporanPemulihanTanah laporanPemulihanTanah) {
        this.laporanPemulihanTanah = laporanPemulihanTanah;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList() {
        return perbicaraanKehadiranList;
    }

    public void setPerbicaraanKehadiranList(List<PerbicaraanKehadiran> perbicaraanKehadiranList) {
        this.perbicaraanKehadiranList = perbicaraanKehadiranList;
    }

    public List<PerbicaraanKehadiran> getPerbicaraanKehadiranList1() {
        return perbicaraanKehadiranList1;
    }

    public void setPerbicaraanKehadiranList1(List<PerbicaraanKehadiran> perbicaraanKehadiranList1) {
        this.perbicaraanKehadiranList1 = perbicaraanKehadiranList1;
    }

    public List<PermohonanPihak> getSenaraiPermohonanPihak() {
        return senaraiPermohonanPihak;
    }

    public void setSenaraiPermohonanPihak(List<PermohonanPihak> senaraiPermohonanPihak) {
        this.senaraiPermohonanPihak = senaraiPermohonanPihak;
    }
}
