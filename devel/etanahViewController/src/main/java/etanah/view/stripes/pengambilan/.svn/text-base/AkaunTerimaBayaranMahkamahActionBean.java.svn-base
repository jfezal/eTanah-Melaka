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
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanPihakService;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PerbicaraanService;
import etanah.service.daftar.PembetulanService;
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
@UrlBinding("/pengambilan/akaunTerimaBayaranMahkamah")
public class AkaunTerimaBayaranMahkamahActionBean extends AbleActionBean {

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
    private PerbicaraanService perbicaraanService;
    @Inject
    private PembetulanService pembetulanService;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PermohonanPengambilan permohonanPengambilan;
    private PermohonanPihak permohonanPihak;
    private PermohonanPihak permohonanPihakSblm;
    private PermohonanTuntutanKos permohonanTuntutanKos;
    private AmbilPampasan ambilPampasan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private String idPermohonan;
    private String idHakmilik;
    private String nama;
    private int nombor;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String idPermohonanSblm;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private BigDecimal jumTerimaPampasan;
    private KodCaraBayaran kodCaraBayaran;
    private BigDecimal itemNilaianTanah;
    private BigDecimal jumlah;
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
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private List<Penilaian> penilaianTanahList;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/akaunTerimaBayaran.jsp").addParameter("tab", "true");
    }

    public Resolution showForm3() {
        System.out.println(">>>>>> Action bean mahkamah");
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/AkaunTerimaBayaran2Mahkamah.jsp").addParameter("tab", "true");
    }

    public Resolution showForm2() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                pengambilanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/rekodTerimaBayaranOrgBerkepentingan.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        nama = (String) getContext().getRequest().getParameter("nama");

        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        nombor = Integer.parseInt(getContext().getRequest().getParameter("nombor"));
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(permohonan.getIdPermohonan(), idHakmilik);
        hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
        ////////// start pampasan
        itemNilaianTanah = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
        if (!hakbicaraList.isEmpty()) {
            int index = hakbicaraList.size() - 1;
            if (hakbicaraList.size() > 1) {
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
            } else {
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
            }
        } else {

            senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());

        }
        for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
            penilaianTanahList = notisPenerimaanService.getPenilaianByidHadir(senaraiPerbicaraanKehadiran.get(i).getIdKehadiran());
            for (int y = 0; y < penilaianTanahList.size(); y++) {
                itemNilaianTanah = penilaianTanahList.get(y).getAmaun();
                System.out.println("itemNilaianTanah >> " + itemNilaianTanah);
                jumlah = jumlah.add(itemNilaianTanah);

            }

        }
        ////////////////////  end pampasan ////
        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
//        return new JSP("pengambilan/akaunTerimaBayaran.jsp").addParameter("tab", "true");
//        return new JSP("pengambilan/negerisembilan/haklalulalangpersendirian/rekodTerimaBayaranOrgBerkepentingan.jsp").addParameter("tab", "true");
        return new JSP("pengambilan/akaunTerimaBayaranMahkamah.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        nama = (String) getContext().getRequest().getParameter("nama");
        System.out.println("id Permohonan :" + idPermohonan);
        ambilPampasanList = new ArrayList<AmbilPampasan>();

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
                        List<PermohonanPihak> permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(permohonan.getIdPermohonan(), hakmilik.getNoHakmilik(), idPihak);
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
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        nombor = Integer.parseInt(getContext().getRequest().getParameter("nombor"));
        hakmilik = hakmilikDAO.findById(idHakmilik);

        getContext().getRequest().getSession().removeAttribute("permohonanPihak");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (hakmilikPermohonan != null) {
//            if(pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
            if (perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId()) == null) {
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
//            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                System.out.println("id pihak : " + idPihak);
                PermohonanPihak permohonanPihak = null;//pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                List<PermohonanPihak> permohonanPihakList = pengambilanService.getPmohonPihakByIdHakmilikIdPihakList(idPermohonan, idHakmilik, idPihak);
                System.out.println("saiz permohonanPihakList " + permohonanPihakList.size());
                System.out.println("nombor "+nombor);
                if (permohonanPihakList.size() > 1) {
                    permohonanPihak = permohonanPihakList.get(0).getPermohonan().getSenaraiPihak().get(nombor);
                } else {
                    permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                }
                System.out.println("permohonanPihak.getIdPermohonanPihak() "+permohonanPihak.getIdPermohonanPihak());
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
                if (perbicaraanKehadiran != null) {
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
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/akaunTerimaBayaranMahkamah.jsp").addParameter("tab", "true");

    }

    public Resolution showAkuanBayaranList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        idPermohonanSblm = permohonan.getIdPermohonan();
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        
        if (hakmilikPermohonan != null) {
//            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                PermohonanPihak permohonanPihak2 = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                System.out.println("permohonanPihak.getIdPermohonanPihak() " + permohonanPihak.getIdPermohonanPihak());

                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    System.out.println("saiz ambil pampasan " + senaraiAmbilPampasan.size());
                    if (senaraiAmbilPampasan.size() == 0) {
                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak2.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                        senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    }
                }
            }
        }

        return new JSP("pengambilan/AkuanTerimaBayaranMahkamah_pop.jsp").addParameter("popup", "true");
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    /**
     * @return the senaraiPerbicaraanKehadiran
     */
    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    /**
     * @param senaraiPerbicaraanKehadiran the senaraiPerbicaraanKehadiran to set
     */
    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public int getNombor() {
        return nombor;
    }

    public void setNombor(int nombor) {
        this.nombor = nombor;
    }
}
