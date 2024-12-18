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
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.service.common.PermohonanPihakService;
//import etanah.service.common.NotisPenerimaanService;
//import etanah.service.common.HakmilikPermohonanService;
import etanah.service.BayaranPampasanService;
import etanah.service.PengambilanService;
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
import etanah.service.daftar.PembetulanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/rekodPenerimaanDeposit")
public class RekodPenerimaanDepositActionBean extends AbleActionBean {

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
    private PerbicaraanService perbicaraanService;
    @Inject
    private PermohonanSupayaBantahanService permohonanSupayaBantahanService;
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
    private KodCaraBayaran kodCaraBayaran;
    private String noDok;
    private Date tarikhDok;
    private KodBank kodBank;
    private BigDecimal jumCaraBayar1 = new BigDecimal(0.00);
    private List<PermohonanPihak> permohonanPihakList;
    private List<AmbilPampasan> ambilPampasanList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private Permohonan permohonanSblm;
    private HakmilikPermohonan hakmilikPermohonan;
    private List<AmbilPampasan> senaraiAmbilPampasan = new ArrayList<AmbilPampasan>();
    private List<Pemohon> pemohonList;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            pemohonList = permohonan.getSenaraiPemohon();
            permohonanTuntutanKos = pengambilanService.findTuntutanByIdPermohonan(idPermohonan);
            if (permohonan != null) {
                permohonanSupayaBantahanService.simpanPermohonanPihak(permohonan, pengguna);
            }
        }

        return new JSP("pengambilan/rekodPenerimaanDeposit.jsp").addParameter("tab", "true");
    }
//    public Resolution showForm2() {
//
//        return new JSP("pengambilan/AkaunDeposit.jsp").addParameter("tab", "true");
//    }

    public Resolution pihakDetails() {

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        hakmilikPermohonan = permohonanSupayaBantahanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPerbicaraan = permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        pemohonList = new ArrayList<Pemohon>();
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
        pemohonList = permohonan.getSenaraiPemohon();
        if (permohonanPihak != null) {
            if (hakmilikPerbicaraan != null) {
                perbicaraanKehadiran = permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
                        ambilPampasanList.add(ambilPampasanx);
                        if (ambilPampasan != null) {
                            jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                            kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                            noDok = ambilPampasan.getNoDok();
                            tarikhDok = ambilPampasan.getTarikhDok();
                            kodBank = ambilPampasan.getKodBank();
                        }
                    }
                }
            }

        }//if
        else if (permohonanPihak == null) {
            addSimpleError("Tiada Data");
        }
        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekodPenerimaanDeposit.jsp").addParameter("tab", "true");
    }
    
    public Resolution pemohonDetails() {

      //  idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
       // hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
       // hakmilikPermohonan = permohonanSupayaBantahanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
       // hakmilikPerbicaraan = permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        pemohonList = new ArrayList<Pemohon>();
        permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
        pemohonList = permohonan.getSenaraiPemohon();
        permohonanTuntutanKos = pengambilanService.findTuntutanByIdPermohonan(idPermohonan);
        if (permohonanPihak != null) {
            if (hakmilikPerbicaraan != null) {
                perbicaraanKehadiran = permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
                        ambilPampasanList.add(ambilPampasanx);
                        if (ambilPampasan != null) {
                            jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                            kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                            noDok = ambilPampasan.getNoDok();
                            tarikhDok = ambilPampasan.getTarikhDok();
                            kodBank = ambilPampasan.getKodBank();
                        }
                    }
                }
            }

        }//if
        else if (permohonanPihak == null) {
            addSimpleError("Tiada Data");
        }
        rehydrate();
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/rekodPenerimaanDeposit.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
        ambilPampasanList = new ArrayList<AmbilPampasan>();
        if (idHakmilik != null) {
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        }


        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hakmilikPermohonan : hakmilikPermohonanList) {
                    System.out.println("id hakmilik mohon " + hakmilikPermohonan.getId());
                    hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH3(hakmilikPermohonan.getId());
                    Hakmilik hakmilik = hakmilikPermohonan.getHakmilik();
                    List<HakmilikPihakBerkepentingan> hakmilikPihakList = hakmilik.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hp : hakmilikPihakList) {
                        long idPihak = hp.getPihak().getIdPihak();
                        System.out.println("id Pihak " + idPihak);
                        System.out.println("hakmilik.getIdHakmilik() " + hakmilik.getIdHakmilik());
                        System.out.println("idPermohonan " + idPermohonan);
                        // permohonanPihak = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, hakmilik.getIdHakmilik(), idPihak);
                        List<PermohonanPihak> permohonanPihakList = permohonanPihakService.getSenaraiPmohonPihakByIdHakmilikIdPihak2(idPermohonan, hakmilik.getNoHakmilik(), idPihak);
                        //  System.out.println("Permohonan Pihak " + permohonanPihak.getIdPermohonanPihak());
                        //   System.out.println("hakmilikPerbicaraan.getIdPerbicaraan() " + hakmilikPerbicaraan.getIdPerbicaraan());
                        for (PermohonanPihak permohonanPihak : permohonanPihakList) {
                            if (hakmilikPerbicaraan != null) {
                                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());

                                if (perbicaraanKehadiran != null) {
                                    System.out.println("Id Kehadiran " + perbicaraanKehadiran.getIdKehadiran());
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

    public Resolution showAkuanBayaranList() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("idHakmilik showAkuanBayaranList : " + idHakmilik);
        System.out.println("id mohon showAkuanBayaranList" + idPermohonan);
        if (hakmilikPermohonan != null) {
//            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            hakmilikPerbicaraan = perbicaraanService.getHakmilikPerbicaraanByIdMH3(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                System.out.println("permohonanPihak.getIdPermohonanPihak() = " + permohonanPihak.getIdPermohonanPihak());
                System.out.println("hakmilikPerbicaraan.getIdPerbicaraan() = " + hakmilikPerbicaraan.getIdPerbicaraan());
                perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    System.out.println("saiz ambil pampasan " + senaraiAmbilPampasan.size());
//                    if (senaraiAmbilPampasan.size() == 0) {
//                        perbicaraanKehadiran = pengambilanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
//                        senaraiAmbilPampasan = pengambilanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
//                    }
                }
            }
        }

        return new JSP("pengambilan/AkuanTerimaBayaran_pop.jsp").addParameter("popup", "true");
    }

    public Resolution simpan() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        System.out.println("idhakmilik:" + hakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("idpermohonan:" + idPermohonan);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("idpihak:" + idPihak);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        hakmilikPermohonan = permohonanSupayaBantahanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (hakmilikPermohonan != null) {
            if (permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                permohonanSupayaBantahanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            hakmilikPerbicaraan = permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                if (permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(cawangan);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                    permohonanSupayaBantahanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
                perbicaraanKehadiran = permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    System.out.println("saiz pampasan " + ambilPampasanList1.size());
                    for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
                        ambilPampasanList.add(ambilPampasanx);
                        //}
                        InfoAudit info = new InfoAudit();

                        info = ambilPampasan.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());

                        ambilPampasan.setInfoAudit(info);
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                        ambilPampasan.setNoDok(noDok);
                        ambilPampasan.setTarikhDok(tarikhDok);
                        ambilPampasan.setKodBank(kodBank);
                        permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        System.out.println("berjaya update");
                    }
                    if (ambilPampasanList1.size() == 0) {
                        ambilPampasan = new AmbilPampasan();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                        ambilPampasan.setInfoAudit(info);
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                        ambilPampasan.setNoDok(noDok);
                        ambilPampasan.setTarikhDok(tarikhDok);
                        ambilPampasan.setKodBank(kodBank);
                        permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        System.out.println("berjaya simpan");
                    }
                }
            }
        }
        List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
        for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
            ambilPampasanList.add(ambilPampasanx);
            if (ambilPampasan != null) {
                jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                noDok = ambilPampasan.getNoDok();
                tarikhDok = ambilPampasan.getTarikhDok();
                kodBank = ambilPampasan.getKodBank();
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/rekodPenerimaanDeposit.jsp").addParameter("tab", "true");

    }
    
    
     public Resolution simpanPemohon() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        System.out.println("idhakmilik:" + hakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        System.out.println("idpermohonan:" + idPermohonan);
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        System.out.println("idpihak:" + idPihak);
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
        KodCawangan cawangan = permohonan.getCawangan();

        hakmilikPermohonan = permohonanSupayaBantahanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        if (hakmilikPermohonan != null) {
            if (permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId()) == null) {
                hakmilikPerbicaraan = new HakmilikPerbicaraan();
                InfoAudit info = new InfoAudit();
                info.setDimasukOleh(peng);
                info.setTarikhMasuk(new java.util.Date());
                hakmilikPerbicaraan.setInfoAudit(info);
                hakmilikPerbicaraan.setCawangan(cawangan);
                hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
                permohonanSupayaBantahanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
            }
            hakmilikPerbicaraan = permohonanSupayaBantahanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            if (hakmilikPerbicaraan != null) {
                PermohonanPihak permohonanPihak = permohonanSupayaBantahanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idPermohonan, idHakmilik, idPihak);
                if (permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan()) == null) {
                    perbicaraanKehadiran = new PerbicaraanKehadiran();
                    InfoAudit infoAudit = new InfoAudit();
                    infoAudit.setTarikhMasuk(new java.util.Date());
                    infoAudit.setDimasukOleh(peng);
                    perbicaraanKehadiran.setInfoAudit(infoAudit);
                    perbicaraanKehadiran.setCawangan(cawangan);
                    perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                    perbicaraanKehadiran.setPihak(permohonanPihak);
                    permohonanSupayaBantahanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
                }
                perbicaraanKehadiran = permohonanSupayaBantahanService.getPerbicaraanKehadiranbyidMPidBicara(permohonanPihak.getIdPermohonanPihak(), hakmilikPerbicaraan.getIdPerbicaraan());
                if (perbicaraanKehadiran != null) {
                    List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
                    System.out.println("saiz pampasan " + ambilPampasanList1.size());
                    for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
                        ambilPampasanList.add(ambilPampasanx);
                        //}
                        InfoAudit info = new InfoAudit();

                        info = ambilPampasan.getInfoAudit();
                        info.setDiKemaskiniOleh(peng);
                        info.setTarikhKemaskini(new java.util.Date());

                        ambilPampasan.setInfoAudit(info);
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                        ambilPampasan.setNoDok(noDok);
                        ambilPampasan.setTarikhDok(tarikhDok);
                        ambilPampasan.setKodBank(kodBank);
                        permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        System.out.println("berjaya update");
                    }
                    if (ambilPampasanList1.size() == 0) {
                        ambilPampasan = new AmbilPampasan();
                        InfoAudit info = new InfoAudit();
                        info.setDimasukOleh(peng);
                        info.setTarikhMasuk(new java.util.Date());
                        ambilPampasan.setPerbicaraanKehadiran(perbicaraanKehadiran);
                        ambilPampasan.setInfoAudit(info);
                        ambilPampasan.setJumTerimaPampasan(jumTerimaPampasan);
                        ambilPampasan.setKodCaraBayaran(kodCaraBayaran);
                        ambilPampasan.setNoDok(noDok);
                        ambilPampasan.setTarikhDok(tarikhDok);
                        ambilPampasan.setKodBank(kodBank);
                        permohonanSupayaBantahanService.saveOrupdateAmbilPampasan(ambilPampasan);
                        System.out.println("berjaya simpan");
                    }
                }
            }
        }
        List<AmbilPampasan> ambilPampasanList1 = permohonanSupayaBantahanService.getAmbilPampasanListByidHadir(perbicaraanKehadiran.getIdKehadiran());
        for (AmbilPampasan ambilPampasanx : ambilPampasanList1) {
            ambilPampasanList.add(ambilPampasanx);
            if (ambilPampasan != null) {
                jumTerimaPampasan = ambilPampasan.getJumTerimaPampasan();
                kodCaraBayaran = ambilPampasan.getKodCaraBayaran();
                noDok = ambilPampasan.getNoDok();
                tarikhDok = ambilPampasan.getTarikhDok();
                kodBank = ambilPampasan.getKodBank();
            }
        }

        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        addSimpleMessage("Maklumat telah berjaya disimpan");
        return new JSP("pengambilan/rekodPenerimaanDeposit.jsp").addParameter("tab", "true");

    }

    public Resolution refreshpage() {
        rehydrate();
        return new RedirectResolution(RekodPenerimaanBayaranPampasan31aActionBean.class, "locate");
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
        this.setHakmilikPermohonanList(hakmilikPermohonanList);
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

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    /**
     * @return the ambilPampasanList
     */
    public List<AmbilPampasan> getAmbilPampasanList() {
        return ambilPampasanList;
    }

    /**
     * @param ambilPampasanList the ambilPampasanList to set
     */
    public void setAmbilPampasanList(List<AmbilPampasan> ambilPampasanList) {
        this.ambilPampasanList = ambilPampasanList;
    }

    /**
     * @return the senaraiAmbilPampasan
     */
    public List<AmbilPampasan> getSenaraiAmbilPampasan() {
        return senaraiAmbilPampasan;
    }

    public List<Pemohon> getPemohonList() {
        return pemohonList;
    }

    public void setPemohonList(List<Pemohon> pemohonList) {
        this.pemohonList = pemohonList;
    }

    /**
     * @param senaraiAmbilPampasan the senaraiAmbilPampasan to set
     */
    public void setSenaraiAmbilPampasan(List<AmbilPampasan> senaraiAmbilPampasan) {
        this.senaraiAmbilPampasan = senaraiAmbilPampasan;
    }
}
