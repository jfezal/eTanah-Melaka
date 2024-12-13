/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
import etanah.dao.KodBankDAO;
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

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/rekodBayaranPTSP")
public class RekodBayaranPTSPActionBean extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodStatusTerimaDAO kodStatusTerimaDAO;
    @Inject
    KodCaraBayaranDAO kodcaraBayaranDAO;
    @Inject
    KodBankDAO kodBankDAO;
    private Hakmilik hakmilik;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanAduanService aduanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal jumTerimaPampasan;
    private BigDecimal jumPerlubayar;
    private PermohonanPihak pp;
    private String kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private String kodBank;
    private String nD;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
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
    private List<PermohonanPihak> listPP;
    private KodBank kodBank2;
    private boolean tunai = false;
    private boolean xtunai = false;

    @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/negerisembilan/pendudukansementara/rekodBayaranPTSP.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        long idPihak2 = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakP : hakmilikPermohonanList) {
                    Hakmilik hakmilik = hakP.getHakmilik();
//                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                    for (PermohonanPihak hp : permohonanPihakList) {
                        long idPihak = hp.getPihak().getIdPihak();
                        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                        lpt = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakP.getId(), hp.getIdPermohonanPihak());
//                        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakP.getId());
                        hakmilikPerbicaraanList = pengambilanService.getListHakmilikbicara(hakP.getId());
//                        System.out.println("hakmilikPerbicaraan"+hakmilikPerbicaraan.getIdPerbicaraan());
                        if (lpt != null) {
                            kosKecacatanTanah = lpt.getKosKecacatanTanah();
                            kosKerosakanBangunan = lpt.getKosKerosakanBangunan();
                            kosKerosakanInfra = lpt.getKosKerosakanInfra();
                            kosKerosakanLain = lpt.getKosKerosakanLain();
                            kosKerosakanPokok = lpt.getKosKerosakanPokok();
                            kosKerosakanTanah = lpt.getKosKerosakanTanah();
//                                    jumlah=jumlah.add(kosKecacatanTanah).add(kosKerosakanBangunan).add(kosKerosakanInfra).add(kosKerosakanLain).add(kosKerosakanPokok).add(kosKerosakanTanah);
                            System.out.println("jumlah" + jumlah);
                        }
                        int i = hakmilikPerbicaraanList.size();
                        int count = i - 1;
                        if (hakmilikPerbicaraanList.size() > 0) {
                            perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(hp.getIdPermohonanPihak(), hakmilikPerbicaraanList.get(count).getIdPerbicaraan());
                            if (perbicaraanKehadiran != null) {
                                List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                    jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                                    kodCaraBayaran = ambilPampasan.getKodCaraBayaran().getKod();
                                    if (kodCaraBayaran.equals("T")) {
                                        tunai = true;
                                        xtunai=false;
                                    } else {
                                        tunai = false;
                                    }
                                    noDok = ambilPampasan.getNoDok();
                                    tarikhDok = ambilPampasan.getTarikhDok();
                                    if (noDok != null) {
                                        kodBank = ambilPampasan.getKodBank().getKod();
                                    }

                                    ambilPampasanList.add(ambilPampasan);
                                }
                            }
                        }
                    }

                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/negerisembilan/pendudukansementara/rekodBayaranPTSP.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        permohonan = permohonanDAO.findById(idPermohonan);
//        pp = aduanService.findPihakByIdMohon(permohonan.getIdPermohonan());
        listPP = aduanService.findPihakByIdMohonList(idPermohonan);
        permohonanPihakList = aduanService.findPihak(idPermohonan);
        ambilPampasanList = new ArrayList<AmbilPampasan>();
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);


        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakP : hakmilikPermohonanList) {
                    Hakmilik hakmilik = hakP.getHakmilik();
//                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                    for (PermohonanPihak hp : permohonanPihakList) {
                        long idPihak = hp.getPihak().getIdPihak();
                        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                        lpt = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakP.getId(), hp.getIdPermohonanPihak());
                        hakmilikPerbicaraanList = pengambilanService.getListHakmilikbicara(hakP.getId());
//                        System.out.println("hakmilikPerbicaraan"+hakmilikPerbicaraan.getIdPerbicaraan());
                        if (lpt != null) {
                            kosKecacatanTanah = lpt.getKosKecacatanTanah();
                            kosKerosakanBangunan = lpt.getKosKerosakanBangunan();
                            kosKerosakanInfra = lpt.getKosKerosakanInfra();
                            kosKerosakanLain = lpt.getKosKerosakanLain();
                            kosKerosakanPokok = lpt.getKosKerosakanPokok();
                            kosKerosakanTanah = lpt.getKosKerosakanTanah();
//                                    jumlah=jumlah.add(kosKecacatanTanah).add(kosKerosakanBangunan).add(kosKerosakanInfra).add(kosKerosakanLain).add(kosKerosakanPokok).add(kosKerosakanTanah);
                            System.out.println("jumlah" + jumlah);
                        }

                        int i = hakmilikPerbicaraanList.size();
                        int count = i - 1;
                        if (hakmilikPerbicaraanList.size() > 0) {
                            perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(hp.getIdPermohonanPihak(), hakmilikPerbicaraanList.get(count).getIdPerbicaraan());
                            if (perbicaraanKehadiran != null) {
                                List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                for (AmbilPampasan ambilPampasan : ambilPampasanList1) {
                                    jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                                    kodCaraBayaran = ambilPampasan.getKodCaraBayaran().getKod();
                                    if (kodCaraBayaran.equals("T")) {
                                        tunai = true;
                                        xtunai=false;
                                        noDok = "";
                                        tarikhDok = ambilPampasan.getTarikhDok();
                                    } else {
                                        tunai = false;
                                        noDok = ambilPampasan.getNoDok();
                                        tarikhDok = ambilPampasan.getTarikhDok();
                                        if (noDok != null) {
                                            kodBank = ambilPampasan.getKodBank().getKod();
                                        }
                                    }
                                    ambilPampasanList.add(ambilPampasan);
                                }
                            }
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
        System.out.println("cawangan" + cawangan);
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        String kodcara = (String) getContext().getRequest().getParameter("kodCaraBayaran");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        listPP = aduanService.findPihakByIdMohonList(idPermohonan);
        permohonanPihakList = aduanService.findPihak(permohonan.getIdPermohonan());




        for (PermohonanPihak pPihak : permohonanPihakList) {
            hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, pPihak.getHakmilik().getIdHakmilik());
            if (hakmilikPermohonan != null) {
                if (pengambilanService.getListHakmilikbicara(hakmilikPermohonan.getId()) == null) {
                    hakmilikPerbicaraan = new HakmilikPerbicaraan();
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    hakmilikPerbicaraan.setInfoAudit(info);
                    hakmilikPerbicaraan.setCawangan(cawangan);
                    hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                    pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
                }
                hakmilikPerbicaraanList = pengambilanService.getListHakmilikbicara(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraanList.size() > 0) {
                    int i = hakmilikPerbicaraanList.size();
                    int count = i - 1;

                    System.out.println("test" + hakmilikPerbicaraanList.get(count));
                    if (pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraanList.get(count).getIdPerbicaraan()) == null) {
                        perbicaraanKehadiran = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        perbicaraanKehadiran.setCawangan(cawangan);
                        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraanList.get(count));
                        perbicaraanKehadiran.setPihak(pPihak);
                        pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                    }
                    System.out.println("id bicara " + hakmilikPerbicaraanList.get(count).getIdPerbicaraan());
                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(pPihak.getIdPermohonanPihak(), hakmilikPerbicaraanList.get(count).getIdPerbicaraan());
                    System.out.println("id hadir " + perbicaraanKehadiran.getHadir());
                    if (perbicaraanKehadiran != null) {
                        ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                        if (ambilPampasan == null) {
                            ambilPampasan = new AmbilPampasan();
                            ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                            InfoAudit info = new InfoAudit();
                            info.setDimasukOleh(peng);
                            info.setTarikhMasuk(new java.util.Date());
                            ambilPampasan.setInfoAudit(info);
                        } else {
                            ambilPampasan = pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());
                            InfoAudit ia = ambilPampasan.getInfoAudit();
                            ia.setDiKemaskiniOleh(peng);
                            ia.setTarikhKemaskini(new java.util.Date());
                            ambilPampasan.setInfoAudit(ia);
                        }
                        if (kodcara.equalsIgnoreCase("T")) {
                            System.out.println("Tunai");
                            ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                            ambilPampasan.setKodCaraBayaran(kodcaraBayaranDAO.findById(kodCaraBayaran));
                             tunai = true;
                             xtunai=false;

                            nD = "";
                            ambilPampasan.setNoDok(nD);
                            ambilPampasan.setKodBank(kodBank2);
                            ambilPampasan.setTarikhDok(tarikhDok);
                            pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        } else {
                             tunai = false;
                            System.out.println("Bukan Tunai");
                            ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                            ambilPampasan.setKodCaraBayaran(kodcaraBayaranDAO.findById(kodCaraBayaran));
                            ambilPampasan.setNoDok(noDok);
                            ambilPampasan.setKodBank(kodBankDAO.findById(kodBank));
                            ambilPampasan.setTarikhDok(tarikhDok);
                            pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        }

                    }
                }
            }

        }
        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/negerisembilan/pendudukansementara/rekodBayaranPTSP.jsp").addParameter("tab", "true");

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

    public String getIdPihak() {
        return idPihak;
    }

    public void setIdPihak(String idPihak) {
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

    public String getKodBank() {
        return kodBank;
    }

    public void setKodBank(String kodBank) {
        this.kodBank = kodBank;
    }

    public String getKodCaraBayaran() {
        return kodCaraBayaran;
    }

    public void setKodCaraBayaran(String kodCaraBayaran) {
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

    public List<PermohonanPihak> getListPP() {
        return listPP;
    }

    public void setListPP(List<PermohonanPihak> listPP) {
        this.listPP = listPP;
    }

    public String getnD() {
        return nD;
    }

    public void setnD(String nD) {
        this.nD = nD;
    }

    public KodBank getKodBank2() {
        return kodBank2;
    }

    public void setKodBank2(KodBank kodBank2) {
        this.kodBank2 = kodBank2;
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
}

