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
import etanah.model.PermohonanPihakTidakBerkepentingan;
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
import etanah.service.daftar.PembetulanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author nordiyana
 */
@UrlBinding("/pengambilan/rekodBayaranPPTB")
public class RekodBayaranPPTBActionBean extends AbleActionBean {
    private static final Logger logger = Logger.getLogger(RekodBayaranPPTBActionBean.class);
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
    @Inject
    private PembetulanService pembetulanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idPermohonanPhkTdkBerptg;
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
    private BigDecimal jumlah;
    private List<PermohonanPihak> listPP;
    private KodBank kodBank2;
    private boolean tunai = false;
    private boolean xtunai = false;
    private List<PermohonanPihakTidakBerkepentingan> senaraiPPTB;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    
     @DefaultHandler
    public Resolution showForm() {
        return new JSP("pengambilan/rekod_bayaran_PPTB.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        long idHadir = Long.parseLong(getContext().getRequest().getParameter("idHadir"));
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakP : hakmilikPermohonanList) {
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakP.getId());
                    if(hakmilikPerbicaraan !=null)
                    {
                        logger.info("hakmilik bicara id "+hakmilikPerbicaraan.getIdPerbicaraan());
                    senaraiPerbicaraanKehadiran=notisPenerimaanService.getPerbicaraanKehadiranbyidBicaraPPTB(hakmilikPerbicaraan.getIdPerbicaraan());
                    
                    for (PerbicaraanKehadiran pk : senaraiPerbicaraanKehadiran) {
                        logger.info("senarai hadir x empty "+senaraiPerbicaraanKehadiran.size());
                            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidHadir(pk.getIdKehadiran());
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

//                                  ambilPampasan=pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());

                            }
                        
                    }
                    
                    
                    }
                    

                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekod_bayaran_PPTB.jsp").addParameter("tab", "true");
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
                    hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakP.getId());
                    if(hakmilikPerbicaraan !=null)
                    {
                        logger.info("hakmilik bicara id "+hakmilikPerbicaraan.getIdPerbicaraan());
                    senaraiPerbicaraanKehadiran=notisPenerimaanService.getPerbicaraanKehadiranbyidBicaraPPTB(hakmilikPerbicaraan.getIdPerbicaraan());
                    
                    for (PerbicaraanKehadiran pk : senaraiPerbicaraanKehadiran) {
                        logger.info("senarai hadir x empty "+senaraiPerbicaraanKehadiran.size());
                            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidHadir(pk.getIdKehadiran());
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

//                                  ambilPampasan=pengambilanService.getPerbicaraanKehadiranbyidMPidHadir(perbicaraanKehadiran.getIdKehadiran());

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
        

         hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
         senaraiPPTB=notisPenerimaanService.getHakmilikPPTBList(hakmilikPermohonan.getId());
        
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
                hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                if (hakmilikPerbicaraan !=null) {
                    for(PermohonanPihakTidakBerkepentingan pp:senaraiPPTB){
                    if (notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(pp.getIdPermohonanPhkTdkBerptg(), hakmilikPermohonan.getId()) == null) {
                        perbicaraanKehadiran = new PerbicaraanKehadiran();
                        InfoAudit infoAudit = new InfoAudit();
                        infoAudit.setTarikhMasuk(new java.util.Date());
                        infoAudit.setDimasukOleh(peng);
                        perbicaraanKehadiran.setInfoAudit(infoAudit);
                        perbicaraanKehadiran.setCawangan(cawangan);
                        perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                        perbicaraanKehadiran.setPermohonanPihakTidakBerkepentingan(pp);
                        pengambilanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                    }
                   
                    perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(pp.getIdPermohonanPhkTdkBerptg(), hakmilikPermohonan.getId());

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
//                            tarikhDok = null;
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
        return new JSP("pengambilan/rekod_bayaran_PPTB.jsp").addParameter("tab", "true");

    }

    public Resolution showAkuanBayaranList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idPermohonanPhkTdkBerptg = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPhkTdkBerptg"));

        if (hakmilikPermohonan != null) {
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihakTidakBerkepentingan pptb = notisPenerimaanService.getPBT(hakmilikPermohonan.getId(),idPermohonanPhkTdkBerptg);
               perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPTidBicara(pptb.getIdPermohonanPhkTdkBerptg(), hakmilikPermohonan.getId());
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
    
    public List<PermohonanPihakTidakBerkepentingan> getSenaraiPPTB() {
        return senaraiPPTB;
    }

    public void setSenaraiPPTB(List<PermohonanPihakTidakBerkepentingan> senaraiPPTB) {
        this.senaraiPPTB = senaraiPPTB;
    }
    
    public String getIdPermohonanPhkTdkBerptg() {
        return idPermohonanPhkTdkBerptg;
    }

    public void setIdPermohonanPhkTdkBerptg(String idPermohonanPhkTdkBerptg) {
        this.idPermohonanPhkTdkBerptg = idPermohonanPhkTdkBerptg;
    }
   
    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    
    
}
