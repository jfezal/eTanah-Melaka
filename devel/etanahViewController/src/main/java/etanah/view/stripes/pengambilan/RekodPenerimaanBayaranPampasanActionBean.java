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
import etanah.model.Dokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.InfoAudit;
import etanah.model.KodBank;
import etanah.model.KodCaraBayaran;
import etanah.model.KodCawangan;
import etanah.model.KodStatusTerima;
import etanah.model.Notis;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.DokumenService;
//import etanah.service.common.NotisPenerimaanService;
//import etanah.service.common.HakmilikPermohonanService;
import etanah.service.BayaranPampasanService;
import etanah.service.PengambilanService;
import etanah.view.etanahActionBeanContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import etanah.service.daftar.PembetulanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


@UrlBinding("/pengambilan/bayaranPampasan")
public class RekodPenerimaanBayaranPampasanActionBean extends AbleActionBean {

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
    private PembetulanService pembetulanService;
    @Inject
//    private HakmilikPermohonanService hakmilikPermohonanService;
    private BayaranPampasanService bayaranPampasanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
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
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<HakmilikPerbicaraan> hakmilikPerbicaraanList;

    private List<AmbilPampasan> ambilPampasanList;
    private List<AmbilPampasan> senaraiAmbilPampasan =  new ArrayList<AmbilPampasan>();

    @DefaultHandler
    public Resolution showForm() {
//        getContext().getRequest().setAttribute("edit", Boolean.TRUE);
        return new JSP("pengambilan/rekodPampasan31bc_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {

        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        hakmilik = hakmilikDAO.findById(idHakmilik);
       // HakmilikPermohonan hp=new HakmilikPermohonan();

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        permohonanPengambilan = pengambilanService.getPermohonanPengambilanByidSblm(idSblm);

        if (idSblm != null) {
            Permohonan permohonanSblm= permohonanDAO.findById(idSblm);
            pengambilanService.simpanPermohonanPihak(permohonanSblm, peng);
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());

            ambilPampasanList = new ArrayList<AmbilPampasan>();
            Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
            List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
            for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                long idPihak = hp.getPihak().getIdPihak();
                permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
                if(hakmilikPerbicaraan != null){
                    perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                    if(perbicaraanKehadiran != null){
                        List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                        for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
                            ambilPampasanList.add(ambilPampasan);
                        }
                    }
                }
            }
        }

//        hakmilikPerbicaraanList = hakmilikPermohonanService.findByIdHakmilikPermohonan(hakmilikPermohonan.getId());
//        hakmilikPerbicaraanList = bayaranPampasanService.findByIdHakmilikPermohonan(hakmilikPermohonan.getId());
//
//          if(!hakmilikPerbicaraanList.isEmpty()){
//              hakmilikPerbicaraan = hakmilikPerbicaraanList.get(0);
//          }

        return new JSP("pengambilan/rekodPampasan31bc_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        permohonanSblm= permohonanDAO.findById(idSblm);
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        permohonanPengambilan = pengambilanService.getPermohonanPengambilanByidSblm(idSblm);

//        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdPihak(idPermohonan,idPihak);
        List<PermohonanTuntutanKos> permohonanTuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
        if (permohonanPihak != null) {
                    permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());

                    jumCaraBayar1 = BigDecimal.ZERO;
                    for (Iterator<PermohonanTuntutanKos> ptk = permohonanTuntutanKosList.iterator(); ptk.hasNext();) {
                        permohonanTuntutanKos = ptk.next();
                        if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                            jumCaraBayar1 = permohonanTuntutanKos.getAmaunSebenar().add(jumCaraBayar1);
                        }
                    }



//                    ambilPampasan = pengambilanService.getAmbilPampasanByidMP(permohonanPihak.getIdPermohonanPihak());
//                    if(ambilPampasan != null) {
//                        jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
//                        kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
//                        noDok = ambilPampasan.getNoDok();
//                        tarikhDok = ambilPampasan.getTarikhDok();
//                        kodBank = ambilPampasan.getKodBank();
//                    }



                }//if

        if(permohonanPihak == null){

            addSimpleError("Tiada Data");
        }

        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekodPampasan31bc_PenarikanBalik.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if(idHakmilik!=null)
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);


        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
            if(idSblm!=null){
                permohonanSblm= permohonanDAO.findById(idSblm);
                if (permohonanSblm != null) {
                    hakmilikPermohonanList = permohonanSblm.getSenaraiHakmilik();
                    ambilPampasanList = new ArrayList<AmbilPampasan>();
                    for(HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
                        Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                        List<HakmilikPihakBerkepentingan> hakmilikPihakList= hakmilik.getSenaraiPihakBerkepentingan();
                        for(HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                            long idPihak = hp.getPihak().getIdPihak();
                            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
                            if(hakmilikPerbicaraan != null){
                                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                                if(perbicaraanKehadiran != null){
                                    List<AmbilPampasan> ambilPampasanList1 = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                                    for(AmbilPampasan ambilPampasan :ambilPampasanList1) {
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
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);

        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        permohonanSblm= permohonanDAO.findById(idSblm);
        KodCawangan  cawangan = permohonanSblm.getCawangan();
        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));


        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
        if(hakmilikPermohonan != null){
            if(pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraan=new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                pengambilanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if(hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, idHakmilik, idPihak);
                if(pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
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
                if(perbicaraanKehadiran != null) {
                    ambilPampasan = new AmbilPampasan();
                    ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                    ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                    ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                    ambilPampasan.setNoDok(noDok);
                    ambilPampasan.setTarikhDok(tarikhDok);
                    ambilPampasan.setKodBank(kodBank);
                    InfoAudit info = new InfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(new java.util.Date());
                    ambilPampasan.setInfoAudit(info);
                    pengambilanService.saveOrupdateAmbilPampasan(ambilPampasan);
                }
            }
        }
        rehydrate();

        permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm,hakmilik.getIdHakmilik(),idPihak);
        List<PermohonanTuntutanKos> permohonanTuntutanKosList = new ArrayList<PermohonanTuntutanKos>();
        if (permohonanPihak != null) {
            permohonanTuntutanKosList = pengambilanService.findByIdPermohonanPihak2(permohonanPihak.getIdPermohonanPihak());

            jumCaraBayar1 = BigDecimal.ZERO;
            for (Iterator<PermohonanTuntutanKos> ptk = permohonanTuntutanKosList.iterator(); ptk.hasNext();) {
                permohonanTuntutanKos = ptk.next();
                if (permohonanTuntutanKos.getAmaunSebenar() != null) {
                    jumCaraBayar1 = permohonanTuntutanKos.getAmaunSebenar().add(jumCaraBayar1);
                }
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/rekodPampasan31bc_PenarikanBalik.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(RekodPenerimaanBayaranPampasanActionBean.class, "locate");
    }

    public Resolution showAmbilPampasanList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);

        if(hakmilikPermohonan != null){
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if(hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idSblm, idHakmilik, idPihak);
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if(perbicaraanKehadiran != null) {
                    senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                }
            }
        }

        return new JSP("pengambilan/rekodPampasan31a_PenarikanBalik_pop.jsp").addParameter("popup", "true");
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

    public BigDecimal getJumCaraBayar1() {
        return jumCaraBayar1;
    }

    public void setJumCaraBayar1(BigDecimal jumCaraBayar1) {
        this.jumCaraBayar1 = jumCaraBayar1;
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


 }
