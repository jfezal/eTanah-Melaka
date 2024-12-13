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
import etanah.dao.PermohonanPihakWakilDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.KodBankDAO;
import etanah.dao.KodCawanganDAO;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.Penilaian;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.Pihak;
import etanah.model.PermohonanPihakWakil;
import etanah.model.KodBank;
import etanah.model.KodNegeri;
import etanah.service.PengambilanService;
import etanah.service.common.FasaPermohonanService;
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
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh
 */
@UrlBinding("/pengambilan/status_1")
public class StatusAwardActionBean_1 extends AbleActionBean {

    private static Logger logger = Logger.getLogger(StatusAwardActionBean_1.class);
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    PermohonanPihakWakilDAO permohonanPihakWakilDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    KodBankDAO kodBankDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    PengambilanService pengambilanService;
    @Inject
    private PembetulanService pembetulanService;
    @Inject
    private PermohonanPihakService permohonanPihakService;
    @Inject
    NotisPenerimaanService notisPenerimaanService;
    @Inject
    FasaPermohonanService fasaPermohonanService;
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
    private String nomborPengenalan;
    private String nomborTelefon;
    private String nomborAkaun;
    private List<PermohonanPihak> permohonanPihakList;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Permohonan permohonan;
    private KodCawangan cawangan;
    private HakmilikPermohonan hakmilikPermohonan;
    private PermohonanPihakWakil permohonanPihakWakil;
    private Penilaian penilaian;
    private KodBank kodBank;
    private KodNegeri kodNegeri;
    private String kodbank;
    private String kodnegeri;
    private String kodcaw;
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
    private List<PermohonanPihakWakil> permohonanPihakWakilList;
    private List<PerbicaraanKehadiran> idMPPerbicaraanKehadiranList;

    public List<PerbicaraanKehadiran> getIdMPPerbicaraanKehadiranList() {
        return idMPPerbicaraanKehadiranList;
    }

    public void setIdMPPerbicaraanKehadiranList(List<PerbicaraanKehadiran> idMPPerbicaraanKehadiranList) {
        this.idMPPerbicaraanKehadiranList = idMPPerbicaraanKehadiranList;
    }
    private Pihak pihak;

//    private List<PerbicaraanKehadiran> hakmilikPerbicaraanList;
    @DefaultHandler
    public Resolution showForm() {
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        if (idPermohonan != null) {
            permohonan = permohonanDAO.findById(idPermohonan);
        }
        notisPenerimaanService.simpanPermohonanPihak(permohonan, peng);
        return new JSP("pengambilan/StatusAward_2.jsp").addParameter("tab", "true");
    }

    public Resolution hakmilikDetails() {
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        hakmilik = hakmilikDAO.findById(idHakmilik);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("id mohon : " + idPermohonan);
        hakmilikPermohonan = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

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
//                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(index).getIdPerbicaraan());
                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranPMAmanah(hakbicaraList.get(index).getIdPerbicaraan());

                } else {
//                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakbicaraList.get(0).getIdPerbicaraan());
                    senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranPMAmanah(hakbicaraList.get(0).getIdPerbicaraan());
                }
            } else {

//                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidBicara2(hakmilikPerbicaraan.getIdPerbicaraan());
                senaraiPerbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranPMAmanah(hakmilikPerbicaraan.getIdPerbicaraan());

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
        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/StatusAward_2.jsp").addParameter("tab", "true");
    }

    public Resolution pihakDetails() {
        System.out.println("::: PIHAK DETAILS :::");

        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);


//        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik");
//        hakmilik = hakmilikDAO.findById(idHakmilik);
        getContext().getRequest().getSession().removeAttribute("permohonanPihak");

//        long idPihak = Long.parseLong(getContext().getRequest().getParameter("idPihak"));

        if (idPermohonan != null) {
//            laporanPemulihanTanah = pengambilanService.getLaporanPemulihanTanahByidMHidMP(hakmilikPermohonan.getId(),permohonanPihak.getIdPermohonanPihak());
            hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            hakmilikPermohonan = hakmilikPermohonanList.get(0);
            hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
//            permohonanPihak = pengambilanService.getPmohonPihakByIdHakmilikIdPihak(idPermohonan,hakmilik.getIdHakmilik(),idPihak);
            permohonanPihakWakil = notisPenerimaanService.getPermohonanPihakWakilByidBicaraIdMH(hakmilikPermohonan.getId(), hakmilikPerbicaraan.getIdPerbicaraan());
            logger.info("id mh : " + hakmilikPermohonan.getId());
            logger.info("id bicara : " + hakmilikPerbicaraan.getIdPerbicaraan());

            if (permohonanPihakWakil == null) {
                logger.info("permohonanPihakWakil == null");
            } else {
                nomborPengenalan = permohonanPihakWakil.getNoPengenalan();
                nomborTelefon = permohonanPihakWakil.getTelefon1();
                nomborAkaun = permohonanPihakWakil.getNoAkaunBank();
                kodbank = permohonanPihakWakil.getBank().getNama();
                kodnegeri = permohonanPihakWakil.getNegeri().getNama();
            }
        }

        getContext().getRequest().setAttribute("displayWaris", Boolean.TRUE);
        getContext().getRequest().setAttribute("showWaris", Boolean.TRUE);
        return new JSP("pengambilan/tambahWaris.jsp").addParameter("popup", "true");
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
                hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
            }
        }
    }

    public Resolution refreshpage() {
//        rehydrate();
        return new RedirectResolution(StatusAwardActionBean_1.class, "locate");
    }

    public Resolution showTambahWaris() {
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        logger.info("id mohon : " + idPermohonan);
        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("idHakmilik");
        idPermohonanPihak =  Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));
        hakmilik = hakmilikDAO.findById(idHakmilik);
         logger.info("idPermohonanPihak : " + idPermohonanPihak);
        logger.info("id hakmilik : " + idHakmilik);
//        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, idHakmilik);
//        idPermohonanPihak = permohonanPihak.getIdPermohonanPihak();
//        logger.info("id mp : " + permohonanPihak.getIdPermohonanPihak());
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        perbicaraanKehadiran = notisPenerimaanService.getPerbicaraanKehadiranbyidMPidBicara(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
//        String idPermohonanPihakWakil = getContext().getRequest().getParameter("idPermohonanPihakWakil");
         idMPPerbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
        permohonanPihakWakilList = notisPenerimaanService.getPermohonanPihakWakilListByidBicaraIdMH(hakmilikPermohonan.getId(), hakmilikPerbicaraan.getIdPerbicaraan());
        logger.info("id bicara : " + hakmilikPerbicaraan.getIdPerbicaraan());
        String kodBandarPekanMukim = getContext().getRequest().getParameter("bandarPekanMukim");
//        logger.info("------kodBandarPekanMukim------ " + kodBandarPekanMukim);
//        if (permohonanPihakWakil.getBandarPekanMukim() != null) {
//            System.out.println("aaaa");
//            bpm = String.valueOf(rizab.getBandarPekanMukim().getKod());
//            kodBandarPekanMukim = String.valueOf(rizab.getBandarPekanMukim().getKod());
//            logger.info("rizab.getBandarPekanMukim()" + rizab.getBandarPekanMukim().getNama());
//            logger.info("rizab.getBandarPekanMukim()" + rizab.getBandarPekanMukim().getKod());
//            penyukatanBPM();
//        }
//        rehydrate();
        getContext().getRequest().setAttribute("displayWaris", Boolean.TRUE);
        getContext().getRequest().setAttribute("showWaris", Boolean.TRUE);
        return new JSP("pengambilan/tambahWaris.jsp").addParameter("popup", "true");
    }

    public Resolution simpanKehadiran() {
        logger.info("::::: SIMPAN KEHADIRAN :::::");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        Permohonan permohonan = permohonanDAO.findById(idPermohonan);
//        idHakmilik = (String) getContext().getRequest().getSession().getAttribute("hakmilik.idHakmilik");
        idHakmilik = (String) getContext().getRequest().getParameter("idHakmilik1");
        logger.info("idhakmilik : " + idHakmilik);
        hakmilik = hakmilikDAO.findById(idHakmilik);
//        idPermohonanPihak =  Long.parseLong(getContext().getRequest().getParameter("idPermohonanPihak"));

//        HakmilikPermohonan hp = pembetulanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);
        hakmilikPermohonan = pengambilanService.findByIdHakmilikIdPermohonan(idPermohonan, idHakmilik);

        hakmilikPerbicaraan = pengambilanService.getHakmilikPerbicaraanByidMH(hakmilikPermohonan.getId());
        logger.info("id mh : " + hakmilikPermohonan.getId());
        logger.info("idPermohonanPihak : " + idPermohonanPihak);
        idMPPerbicaraanKehadiranList = notisPenerimaanService.getPerbicaraanKehadiranbyidMohonPidBicaraList(idPermohonanPihak, hakmilikPerbicaraan.getIdPerbicaraan());
//        logger.info("idMPPerbicaraanKehadiranList : " + idMPPerbicaraanKehadiranList.get(0).getPihak().getIdPermohonanPihak());
//        permohonanPihak = notisPenerimaanService.getSenaraiPmohonPihakByIdHakmilik(idPermohonan, idHakmilik);

//        permohonanPihakWakilList = notisPenerimaanService.getPermohonanPihakWakilListByidBicaraIdMH(hakmilikPermohonan.getId(), hakmilikPerbicaraan.getIdPerbicaraan());
//        if (permohonanPihakWakilList != null) {
//            logger.info("permohonanPihakWakilList != null");
//        }

        logger.info("::: Simpan Table Mohon Pihak Wakil :::");
        permohonanPihakWakil = notisPenerimaanService.getPermohonanPihakWakilByidBicaraIdMH(hakmilikPermohonan.getId(), hakmilikPerbicaraan.getIdPerbicaraan());
        if (permohonanPihakWakil == null) {
            logger.info("mohon pihak wakil : null");
            permohonanPihakWakil = new PermohonanPihakWakil();
            InfoAudit infoAudit = new InfoAudit();
            infoAudit.setDimasukOleh(peng);
            infoAudit.setTarikhMasuk(new java.util.Date());
            permohonanPihakWakil.setInfoAudit(infoAudit);
            permohonanPihakWakil.setHakmilikPermohonan(hakmilikPermohonan);
            permohonanPihakWakil.setHakmilikPerbicaraan(hakmilikPerbicaraan);
            KodCawangan kodcwgn = peng.getKodCawangan();
            permohonanPihakWakil.setCawangan(kodcwgn);
        } else {
            permohonanPihakWakil = notisPenerimaanService.getPermohonanPihakWakilByidBicaraIdMH(hakmilikPermohonan.getId(), hakmilikPerbicaraan.getIdPerbicaraan());
            InfoAudit ia = permohonanPihakWakil.getInfoAudit();
            ia.setDiKemaskiniOleh(peng);
            ia.setTarikhKemaskini(new java.util.Date());
            permohonanPihakWakil.setInfoAudit(ia);

            logger.info("mohon pihak wakil : x null");
            if (getContext().getRequest().getParameter("typesender").equalsIgnoreCase("phtbedit")) {
                permohonanPihakWakil.setBank(kodBankDAO.findById(kodbank));
                KodNegeri kodN = kodNegeriDAO.findById(kodnegeri);
                permohonanPihakWakil.setNegeri(kodN);
            }
        }

//        KodBank kodB2 = new KodBank();
//        KodNegeri kodN2 = new KodNegeri();
//        kodB2.setKod(getContext().getRequest().getParameter("kodBank"));
//        kodN2.setKod(getContext().getRequest().getParameter("kodNegeri"));
//        permohonanPihakWakil.setBank(kodB2);
//        permohonanPihakWakil.setNegeri(kodN2);

//        permohonanPihakWakil.setBank(kodBankDAO.findById(kodbank));
//        KodNegeri kodN = kodNegeriDAO.findById(kodnegeri);
//        permohonanPihakWakil.setNegeri(kodN);
            permohonanPihakWakil.setTelefon1(nomborTelefon);
            permohonanPihakWakil.setNoAkaunBank(nomborAkaun);
            permohonanPihakWakil.setNama(getContext().getRequest().getParameter("permohonanPihakWakil.nama"));
            permohonanPihakWakil.setNoPengenalan(nomborPengenalan);
            permohonanPihakWakil.setAlamat1(getContext().getRequest().getParameter("permohonanPihakWakil.alamat1"));
            permohonanPihakWakil.setAlamat2(getContext().getRequest().getParameter("permohonanPihakWakil.alamat2"));
            permohonanPihakWakil.setAlamat3(getContext().getRequest().getParameter("permohonanPihakWakil.alamat3"));
            permohonanPihakWakil.setAlamat4(getContext().getRequest().getParameter("permohonanPihakWakil.alamat4"));
            permohonanPihakWakil.setPoskod(getContext().getRequest().getParameter("permohonanPihakWakil.poskod"));

            // try {
            notisPenerimaanService.simpanPermohonanPihakWakil(permohonanPihakWakil);
            //  } catch (Exception o) {System.out.println("error ");
            //  }
            if(!idMPPerbicaraanKehadiranList.isEmpty()){
            logger.info("::: Simpan Table Bicara Hadir :::");
            for(PerbicaraanKehadiran kh:idMPPerbicaraanKehadiranList){
                logger.info("::: kh.idPermohonanPihak ::: "+kh.getPihak().getIdPermohonanPihak());
                kh.setWakilPermohonanPihak(permohonanPihakWakil);
//                notisPenerimaanService.saveOrUpdatePerbicaraanKehadiran(kh);
                notisPenerimaanService.simpanBicaraHadir(kh);

            }
            }

       
//        InfoAudit info = new InfoAudit();
//        PerbicaraanKehadiran pk = new PerbicaraanKehadiran();
//        info.setTarikhMasuk(new java.util.Date());
//        info.setDimasukOleh(peng);
//        info.setTarikhKemaskini(new java.util.Date());
//        info.setDiKemaskiniOleh(peng);
//        pk.setInfoAudit(info);
//        pk.setWakilPermohonanPihak(permohonanPihakWakil);
//        KodCawangan kod = peng.getKodCawangan();
//        pk.setCawangan(kod);
//        pk.setPerbicaraan(hakmilikPerbicaraan);
//        notisPenerimaanService.simpanBicaraHadir(pk);


        addSimpleMessage("Maklumat telah berjaya disimpan");
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
        rehydrate();
        getContext().getRequest().setAttribute("displayWaris", Boolean.TRUE);
        getContext().getRequest().setAttribute("showWaris", Boolean.TRUE);
//        getContext().getRequest().setAttribute("showTuanTanah", Boolean.TRUE);
        return new JSP("pengambilan/StatusAward_2.jsp").addParameter("popup", "true");
    }

    public Resolution deletePbt() {
        InfoAudit ia = new InfoAudit();
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);

        perbicaraanKehadiran = new PerbicaraanKehadiran();
//        long idPermohonanPhkTdkBerptg = Long.parseLong(getContext().getRequest().getParameter("idPermohonanPhkTdkBerptg"));
        long idWakilPermohonanPihak = Long.parseLong(getContext().getRequest().getParameter("idWakilPermohonanPihak"));
        logger.info("idWakilPermohonanPihak : " + idWakilPermohonanPihak);
        permohonanPihakWakil = permohonanPihakWakilDAO.findById(idWakilPermohonanPihak);
        System.out.println("idWakilPermohonanPihak" + idWakilPermohonanPihak);
        if (permohonanPihakWakil != null) {
            notisPenerimaanService.deleteAllPermohonanPihakWakil(permohonanPihakWakil);
        }
//        rehydrate();
        getContext().getRequest().getSession().setAttribute("idHakmilik", idHakmilik);
//        getContext().getRequest().setAttribute("showDetails", Boolean.TRUE);
        return new JSP("pengambilan/tambahWaris.jsp").addParameter("popup", "true");
    }

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

        return new JSP("pengambilan/StatusAward.jsp").addParameter("tab", "true");
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

        return new JSP("pengambilan/StatusAward.jsp").addParameter("tab", "true");
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
        return new JSP("pengambilan/StatusAward.jsp").addParameter("tab", "true");
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
        return new JSP("pengambilan/StatusAward.jsp").addParameter("tab", "true");
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

    public List<PermohonanPihakWakil> getPermohonanPihakWakilList() {
        return permohonanPihakWakilList;
    }

    public void setPermohonanPihakWakilList(List<PermohonanPihakWakil> permohonanPihakWakilList) {
        this.permohonanPihakWakilList = permohonanPihakWakilList;
    }

    public PermohonanPihakWakil getPermohonanPihakWakil() {
        return permohonanPihakWakil;
    }

    public void setPermohonanPihakWakil(PermohonanPihakWakil permohonanPihakWakil) {
        this.permohonanPihakWakil = permohonanPihakWakil;
    }

    public KodBank getKodBank() {
        return kodBank;
    }

    public void setKodBank(KodBank kodBank) {
        this.kodBank = kodBank;
    }

    public String getKodbank() {
        return kodbank;
    }

    public void setKodbank(String kodbank) {
        this.kodbank = kodbank;
    }

    public String getKodnegeri() {
        return kodnegeri;
    }

    public void setKodnegeri(String kodnegeri) {
        this.kodnegeri = kodnegeri;
    }

    public String getKodcaw() {
        return kodcaw;
    }

    public void setKodcaw(String kodcaw) {
        this.kodcaw = kodcaw;
    }

    public String getNomborPengenalan() {
        return nomborPengenalan;
    }

    public void setNomborPengenalan(String nomborPengenalan) {
        this.nomborPengenalan = nomborPengenalan;
    }

    public String getNomborTelefon() {
        return nomborTelefon;
    }

    public void setNomborTelefon(String nomborTelefon) {
        this.nomborTelefon = nomborTelefon;
    }

    public String getNomborAkaun() {
        return nomborAkaun;
    }

    public void setNomborAkaun(String nomborAkaun) {
        this.nomborAkaun = nomborAkaun;
    }
}
