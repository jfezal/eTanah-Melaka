/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pengambilan;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakWakil;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.Pihak;
import etanah.service.PengambilanService;
import etanah.service.common.NotisPenerimaanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/statusMahkamah")
public class StatusAwardActionBeanMahkamah extends AbleActionBean {

    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    PengambilanService pengambilanService;
//    @Inject
//    private HakmilikPermohonanService hakmilikPermohonanService;
    private Hakmilik hakmilik;
    private HakmilikPerbicaraan hakmilikPerbicaraan;
    private PerbicaraanKehadiran perbicaraanKehadiran;
    private PermohonanPihak permohonanPihak;
    private String idPermohonan;
    private String idHakmilik;
    private String idPihak;
    private String idDokumen;
    private String tajuk;
    private String tarikhNotis;
    private String kodStatusTerima;
    private String tarikhHantar;
    private String tarikhTampal;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihakWakil permohonanPihakWakil;
    private Penilaian penilaian;
    private String tanahItem;
    private String bngnItem;
    private String lainItem;
    private BigDecimal tanahAmaun;
    private BigDecimal bngnAmaun;
    private BigDecimal lainAmaun;
    private BigDecimal itemNilaianTanah;
    private BigDecimal itemNilaianBngn;
    private BigDecimal itemNilaianLain;
    private BigDecimal jumlah;
    private List<Penilaian> penilaianTanahList;
    private List<Penilaian> penilaianBngnList;
    private List<Penilaian> penilaianLainList;
    private List<HakmilikPerbicaraan> hakbicaraList;
    private long idPermohonanPihak;
    private List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran;
    private List<PerbicaraanKehadiran> senaraiHadir;
    private List<String> status = new ArrayList<String>();
    private List<String> catatan = new ArrayList<String>();
    private List<String> status1 = new ArrayList<String>();
    private List<String> catatan1 = new ArrayList<String>();
    private List<String> pampasan = new ArrayList<String>();
    private Pihak pihak;
    @Inject
    NotisPenerimaanService notisService;
//    private List<PerbicaraanKehadiran> hakmilikPerbicaraanList;

    @DefaultHandler
    public Resolution showForm() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        notisPenerimaanService.simpanPermohonanPihak(permohonan, peng);
        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);

        penilaian = new Penilaian();
        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;
        if (hakmilikPermohonan != null) {
            hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
            hakbicaraList = notisPenerimaanService.getHakmilikPerbicaraanList(hakmilikPermohonan.getId());
            if (!hakbicaraList.isEmpty()) {
                int index = hakbicaraList.size() - 1;
                if (hakbicaraList.size() > 1) {
                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
                    permohonanPihakWakil = pengambilanService.findByIdPihakWakil(hakbicaraList.get(index).getIdPerbicaraan(), hakmilikPermohonan.getId());
                } else {
                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
                    permohonanPihakWakil = pengambilanService.findByIdPihakWakil(hakbicaraList.get(0).getIdPerbicaraan(), hakmilikPermohonan.getId());
                }
            } else {
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());
                permohonanPihakWakil = pengambilanService.findByIdPihakWakil(hakmilikPerbicaraan.getIdPerbicaraan(), hakmilikPermohonan.getId());
            }
            senaraiHadir = notisPenerimaanService.getbicarahadir(hakmilikPerbicaraan.getIdPerbicaraan());

            System.out.println("senaraiPerbicaraanKehadiran" + senaraiPerbicaraanKehadiran.size());
            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                status.add("Terima");
                catatan.add("");
                penilaianTanahList = notisPenerimaanService.getPenilaianByidHadir(senaraiPerbicaraanKehadiran.get(i).getIdKehadiran());
                for (int y = 0; y < penilaianTanahList.size(); y++) {
                    itemNilaianTanah = penilaianTanahList.get(y).getAmaun();
                    jumlah = jumlah.add(itemNilaianTanah);

                }

            }
            for (int i = 0; i < senaraiHadir.size(); i++) {
                status1.add("Terima");
                catatan1.add("");

            }
            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                if (perbicaraanKehadiran.getKeterangan() != null) {
                    status.set(i, perbicaraanKehadiran.getKeterangan());
                }
                if (perbicaraanKehadiran.getCatatan() != null) {
                    catatan.set(i, perbicaraanKehadiran.getCatatan());
                }
            }
            for (int y = 0; y < senaraiHadir.size(); y++) {
                perbicaraanKehadiran = senaraiHadir.get(y);
                penilaianList = perbicaraanKehadiran.getSenaraiPenilaian();
                if (perbicaraanKehadiran.getKeterangan() != null) {
                    status1.set(y, perbicaraanKehadiran.getKeterangan());
                }
                if (perbicaraanKehadiran.getCatatan() != null) {
                    catatan1.set(y, perbicaraanKehadiran.getCatatan());
                }
            }
        }

        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {

        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        String idSblm = permohonan.getPermohonanSebelum().getIdPermohonan();
        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idSblm, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(pengguna);
            infoAudit.setTarikhMasuk(new java.util.Date());
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));
        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilikIdPihak(idSblm, hakmilik.getIdHakmilik(), idPihak);
//        permohonanPihak = permohonanPihakService.getSenaraiMohonPihakByIdPihakIdHakmilik(idPihak, idHakmilik, idPermohonan);

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation})
    public void rehydrate() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");

        if (idHakmilik != null) {
            getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        }


        if (idPermohonan != null) {
            Permohonan permohonan = permohonanDAO.findById(idPermohonan);
            if (permohonan != null) {
                hakmilikPermohonanList = notisService.getMHMahkamah(permohonan.getPermohonanSebelum().getIdPermohonan());
            }
        }
    }

//    public Resolution refreshpage() {
//        rehydrate();
//        return new RedirectResolution(RekodPenerimaanBayaranPampasan31aActionBean.class, "locate");
//    }
    public Resolution simpanStatus() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());
        System.out.println("senaraiPerbicaraanKehadiran" + senaraiPerbicaraanKehadiran.size());
        if (senaraiPerbicaraanKehadiran != null && senaraiPerbicaraanKehadiran.size() > 0) {
            for (int i = 0; i < senaraiPerbicaraanKehadiran.size(); i++) {
                perbicaraanKehadiran = senaraiPerbicaraanKehadiran.get(i);
                perbicaraanKehadiran.setKeterangan(status.get(i));
                perbicaraanKehadiran.setCatatan(catatan.get(i));
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
        }

//        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution simpanStatusPBT() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        senaraiHadir = notisPenerimaanService.getbicarahadir(hakmilikPerbicaraan.getIdPerbicaraan());
        System.out.println("senaraiHadir" + senaraiHadir.size());
        if (senaraiHadir != null && senaraiHadir.size() > 0) {
            for (int i = 0; i < senaraiHadir.size(); i++) {
                perbicaraanKehadiran = senaraiHadir.get(i);
                perbicaraanKehadiran.setKeterangan(status1.get(i));
                perbicaraanKehadiran.setCatatan(catatan1.get(i));
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);
            }
        }

//        }
        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution simpanBngn() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }

        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());

        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        if (permohonanPihak != null) {
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(cawangan);
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(bngnItem);
                penilaian.setAmaun(bngnAmaun);
                penilaian.setJenis('B');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);

            } else {

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(bngnItem);
                penilaian.setAmaun(bngnAmaun);
                penilaian.setJenis('B');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
            }
            bngnItem = "";
            bngnAmaun = BigDecimal.ZERO;
        }

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution simpanLain() {
        Pengguna pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        long idMH = hakmilikPermohonan.getId();
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
            cawangan = permohonan.getCawangan();
        }
        InfoAudit infoAudit = new InfoAudit();
        infoAudit.setDimasukOleh(pengguna);
        infoAudit.setTarikhMasuk(new java.util.Date());
        hakmilikPerbicaraan = notisPenerimaanService.getHakmilikPerbicaraanByidMH(idMH);
        if (hakmilikPerbicaraan == null) {
            hakmilikPerbicaraan = new HakmilikPerbicaraan();
            hakmilikPerbicaraan.setCawangan(cawangan);
            hakmilikPerbicaraan.setHakmilikPermohonan(hakmilikPermohonan);
            hakmilikPerbicaraan.setInfoAudit(infoAudit);
            hakmilikPerbicaraan = notisPenerimaanService.saveOrUpdateHakmilikPerbicaraan(hakmilikPerbicaraan);
        }
        permohonanPihak = permohonanPihakDAO.findById(idPermohonanPihak);

        if (permohonanPihak != null) {
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran == null) {
                perbicaraanKehadiran = new PerbicaraanKehadiran();
                perbicaraanKehadiran.setPerbicaraan(hakmilikPerbicaraan);
                perbicaraanKehadiran.setCawangan(cawangan);
                perbicaraanKehadiran.setInfoAudit(infoAudit);
                perbicaraanKehadiran.setPihak(permohonanPihak);
                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(perbicaraanKehadiran);

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(lainItem);
                penilaian.setAmaun(lainAmaun);
                penilaian.setJenis('L');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);

            } else {

                penilaian = new Penilaian();
                penilaian.setCawangan(cawangan);
                penilaian.setKehadiran(perbicaraanKehadiran);
                penilaian.setInfoAudit(infoAudit);
                penilaian.setItem(lainItem);
                penilaian.setAmaun(lainAmaun);
                penilaian.setJenis('L');
//                penilaian.setPeratus(new BigDecimal(0.00));
                notisPenerimaanService.saveOrUpdatePenilaian(penilaian);
            }
            lainItem = "";
            lainAmaun = BigDecimal.ZERO;
        }

        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);

        itemNilaianTanah = BigDecimal.ZERO;
        itemNilaianBngn = BigDecimal.ZERO;
        itemNilaianLain = BigDecimal.ZERO;
        jumlah = BigDecimal.ZERO;

        if (permohonanPihak == null) {

            addSimpleError("Tiada Data");
        } else {

            idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
            perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
            if (perbicaraanKehadiran != null) {
                penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianBngnList = notisPenerimaanService.getPenilaianBngnByidHadir(perbicaraanKehadiran.getIdKehadiran());
                penilaianLainList = notisPenerimaanService.getPenilaianLainByidHadir(perbicaraanKehadiran.getIdKehadiran());
                for (Penilaian penilaian : penilaianTanahList) {
                    itemNilaianTanah = itemNilaianTanah.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianBngnList) {
                    itemNilaianBngn = itemNilaianBngn.add(penilaian.getAmaun());
                }
                for (Penilaian penilaian : penilaianLainList) {
                    itemNilaianLain = itemNilaianLain.add(penilaian.getAmaun());
                }
                jumlah = jumlah.add(itemNilaianTanah).add(itemNilaianBngn).add(itemNilaianLain);
            }

        }
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);

        penilaianTanahList = notisPenerimaanService.getPenilaianTanahByidHadir(perbicaraanKehadiran.getIdKehadiran());
        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
    }

    public Resolution deleteNilai() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        penilaian = new Penilaian();
        String idNilai = getContext().getRequest().getParameter("idPenilaian");
        penilaian = notisPenerimaanService.getPenilaianBngnByidNilaianB(Long.parseLong(idNilai));
        System.out.println("permohonanRujukanLuarValue" + idNilai);
        if (penilaian != null) {
            notisPenerimaanService.deleteAllNilai(penilaian);
        }
//        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/StatusAward_1.jsp").addParameter("tab", "true");
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

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public String getIdPermohonan() {
        return idPermohonan;
    }

    public void setIdPermohonan(String idPermohonan) {
        this.idPermohonan = idPermohonan;
    }

    public Penilaian getPenilaian() {
        return penilaian;
    }

    public void setPenilaian(Penilaian penilaian) {
        this.penilaian = penilaian;
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiran() {
        return perbicaraanKehadiran;
    }

    public void setPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        this.perbicaraanKehadiran = perbicaraanKehadiran;
    }

    public BigDecimal getTanahAmaun() {
        return tanahAmaun;
    }

    public void setTanahAmaun(BigDecimal tanahAmaun) {
        this.tanahAmaun = tanahAmaun;
    }

    public String getTanahItem() {
        return tanahItem;
    }

    public void setTanahItem(String tanahItem) {
        this.tanahItem = tanahItem;
    }

    public List<Penilaian> getPenilaianTanahList() {
        return penilaianTanahList;
    }

    public void setPenilaianTanahList(List<Penilaian> penilaianTanahList) {
        this.penilaianTanahList = penilaianTanahList;
    }

    public long getIdPermohonanPihak() {
        return idPermohonanPihak;
    }

    public void setIdPermohonanPihak(long idPermohonanPihak) {
        this.idPermohonanPihak = idPermohonanPihak;
    }

    public BigDecimal getBngnAmaun() {
        return bngnAmaun;
    }

    public void setBngnAmaun(BigDecimal bngnAmaun) {
        this.bngnAmaun = bngnAmaun;
    }

    public String getBngnItem() {
        return bngnItem;
    }

    public void setBngnItem(String bngnItem) {
        this.bngnItem = bngnItem;
    }

    public BigDecimal getLainAmaun() {
        return lainAmaun;
    }

    public void setLainAmaun(BigDecimal lainAmaun) {
        this.lainAmaun = lainAmaun;
    }

    public String getLainItem() {
        return lainItem;
    }

    public void setLainItem(String lainItem) {
        this.lainItem = lainItem;
    }

    public List<Penilaian> getPenilaianBngnList() {
        return penilaianBngnList;
    }

    public void setPenilaianBngnList(List<Penilaian> penilaianBngnList) {
        this.penilaianBngnList = penilaianBngnList;
    }

    public List<Penilaian> getPenilaianLainList() {
        return penilaianLainList;
    }

    public void setPenilaianLainList(List<Penilaian> penilaianLainList) {
        this.penilaianLainList = penilaianLainList;
    }

    public BigDecimal getItemNilaianBngn() {
        return itemNilaianBngn;
    }

    public void setItemNilaianBngn(BigDecimal itemNilaianBngn) {
        this.itemNilaianBngn = itemNilaianBngn;
    }

    public BigDecimal getItemNilaianLain() {
        return itemNilaianLain;
    }

    public void setItemNilaianLain(BigDecimal itemNilaianLain) {
        this.itemNilaianLain = itemNilaianLain;
    }

    public BigDecimal getItemNilaianTanah() {
        return itemNilaianTanah;
    }

    public void setItemNilaianTanah(BigDecimal itemNilaianTanah) {
        this.itemNilaianTanah = itemNilaianTanah;
    }

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public List<PerbicaraanKehadiran> getSenaraiPerbicaraanKehadiran() {
        return senaraiPerbicaraanKehadiran;
    }

    public void setSenaraiPerbicaraanKehadiran(List<PerbicaraanKehadiran> senaraiPerbicaraanKehadiran) {
        this.senaraiPerbicaraanKehadiran = senaraiPerbicaraanKehadiran;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public List<String> getCatatan() {
        return catatan;
    }

    public void setCatatan(List<String> catatan) {
        this.catatan = catatan;
    }

    public List<HakmilikPerbicaraan> getHakbicaraList() {
        return hakbicaraList;
    }

    public void setHakbicaraList(List<HakmilikPerbicaraan> hakbicaraList) {
        this.hakbicaraList = hakbicaraList;
    }

    public List<PerbicaraanKehadiran> getSenaraiHadir() {
        return senaraiHadir;
    }

    public void setSenaraiHadir(List<PerbicaraanKehadiran> senaraiHadir) {
        this.senaraiHadir = senaraiHadir;
    }
    private List<Penilaian> penilaianList;

    public List<Penilaian> getPenilaianList() {
        return penilaianList;
    }

    public void setPenilaianList(List<Penilaian> penilaianList) {
        this.penilaianList = penilaianList;
    }

    public List<String> getpampasan() {
        return pampasan;
    }

    public void setpampasan(List<String> pampasan) {
        this.pampasan = pampasan;
    }

    public List<String> getCatatan1() {
        return catatan1;
    }

    public void setCatatan1(List<String> catatan1) {
        this.catatan1 = catatan1;
    }

    public List<String> getStatus1() {
        return status1;
    }

    public void setStatus1(List<String> status1) {
        this.status1 = status1;
    }

    /**
     * @return the permohonanPihakWakil
     */
    public PermohonanPihakWakil getPermohonanPihakWakil() {
        return permohonanPihakWakil;
    }

    /**
     * @param permohonanPihakWakil the permohonanPihakWakil to set
     */
    public void setPermohonanPihakWakil(PermohonanPihakWakil permohonanPihakWakil) {
        this.permohonanPihakWakil = permohonanPihakWakil;
    }
}
