/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.nota;

import able.stripes.AbleActionBean;
import able.stripes.JSP;
import com.google.inject.Inject;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanHubunganDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanAtasPerserahan;
import etanah.model.PermohonanHubungan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.service.NotaService;
import etanah.service.RegService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PemohonService;
import etanah.service.common.PermohonanAtasPerserahanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PermohonanService;
import etanah.service.daftar.BatalNotaService;
import etanah.service.daftar.PembetulanService;
import etanah.view.etanahActionBeanContext;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author mohd.fairul
 */
@UrlBinding("/daftar/nota/nota_daftar_batal")
public class NotaBatalActionBean extends AbleActionBean {

    private Permohonan permohonan;
    private HakmilikUrusan mohon;
    private List<HakmilikUrusan> hakmilikUrusanList;
    private List<HakmilikUrusan> hakmilikUrusanList1;
    private List<HakmilikPermohonan> hakmilikMohonList;
    private List<HakmilikPermohonan> hakmilikMohonList2;
    private List<HakmilikUrusan> hakmilikUrusanPermohonan;
    private List<PermohonanAtasPerserahan> senaraiMau;
    private List<HakmilikPermohonan> hakmilikPermohonanList;
    private String kodUrusan;
    @Inject
    BatalNotaService batalNotaService;
    @Inject
    HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    @Inject
    NotaService notaService;
    @Inject
    PermohonanAtasPerserahanService permohonanAtasPerserahanService;
    @Inject
    HakmilikUrusanDAO hakmilikUrusanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    HakmilikDAO hakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanHubunganDAO permohonanHubunganDAO;
    @Inject
    PembetulanService pService;
    @Inject
    HakmilikUrusanService hakmilikService;
    @Inject
    PermohonanService permohonanService;
    @Inject
    HakmilikPermohonanService hakmilikpermohonanService;
    @Inject
    RegService regService;
    @Inject
    PermohonanPihakService permohonanPihakService;
    @Inject
    PemohonService pemohonService;
    private String[] chkbox;
    private PermohonanAtasPerserahan mau;
    private Pengguna pengguna;
    private Hakmilik hakmilik;
    private String ids;
    private static final Logger LOGGER = Logger.getLogger(NotaBatalActionBean.class);
    static final Comparator<HakmilikUrusan> URUSAN_ORDER = new Comparator<HakmilikUrusan>() {
        @Override
        public int compare(HakmilikUrusan t1, HakmilikUrusan t2) {

            Date d1 = t1.getTarikhDaftar();
            Date d2 = t2.getTarikhDaftar();

            if (d1.after(d2)) {
                return 1;
            } else if (d1.before(d2)) {
                return -1;
            } else {
                return 0;
            }

        }
    };
    static final Comparator<PermohonanAtasPerserahan> URUSAN_ATAS_PERMOHONAN_ORDER = new Comparator<PermohonanAtasPerserahan>() {
        @Override
        public int compare(PermohonanAtasPerserahan t1, PermohonanAtasPerserahan t2) {

            Date d1 = t1.getUrusan().getTarikhDaftar();
            Date d2 = t2.getUrusan().getTarikhDaftar();

            if (d1.after(d2)) {
                return 1;
            } else if (d1.before(d2)) {
                return -1;
            } else {
                return 0;
            }

        }
    };

    @DefaultHandler
    public Resolution searchGadaian() {
        LOGGER.debug("search gadaian..");
        if (ids != null) {
            chkbox = ids.split(",");
        }

        if (chkbox != null && chkbox.length > 0) {
            for (String s : chkbox) {
                LOGGER.debug("s = " + s);
            }
        }
        return new JSP("daftar/nota/carian_Batal.jsp").addParameter("tab", "true");
    }

    public Resolution saveMelepasGadaian() throws IllegalAccessException, InvocationTargetException {
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        List<PermohonanAtasPerserahan> tmp = new ArrayList<PermohonanAtasPerserahan>();

        if (ids != null) {
            chkbox = ids.split(",");
        }

        if (chkbox == null) {
            addSimpleError("Sila Pilih.");
            return new JSP("daftar/nota/carian_Batal.jsp").addParameter("tab", "true");
        }

        if (chkbox.length > 0) {
            for (int i = 0; i < chkbox.length; i++) {
                boolean flag = false;
                if (StringUtils.isBlank(chkbox[i])) {
                    continue;
                }
                Long id = Long.parseLong(chkbox[i]);
                //remove duplicate item
                for (PermohonanAtasPerserahan permohonanAtasPerserahan : senaraiMau) {
                    if (!(StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "PMDPT"))) {
                        if (permohonanAtasPerserahan.getUrusan().getIdUrusan() == id) {
                            //already added in permohonan. just leave it
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    continue;
                }
                if (!(StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "PMDPT"))) {
                    HakmilikUrusan hu = hakmilikUrusanDAO.findById(id);
                    Hakmilik hmTerlibat = hu.getHakmilik();

                    Permohonan oldPermohonan = permohonanDAO.findById(hu.getIdPerserahan());
                    if (oldPermohonan != null) {
                        List<HakmilikPermohonan> senaraiHakmilikTerlibat = oldPermohonan.getSenaraiHakmilik();
                        for (HakmilikPermohonan hp : senaraiHakmilikTerlibat) {
                            if (hp.getHakmilik().equals(hu.getHakmilik())) {
                                mau = new PermohonanAtasPerserahan();
                                mau.setUrusan(hu);
                                mau.setPermohonan(permohonan);
                                mau.setInfoAudit(ia);
                                mau.setHakmilikPermohonan(hp);
                                tmp.add(mau);
                            }
                        }
                    }

                } else {
                    HakmilikPermohonan hp = hakmilikPermohonanDAO.findById(id);
                    mau = new PermohonanAtasPerserahan();
                    mau.setHakmilikPermohonan(hp);
                    LOGGER.info("Check Id Perserahan" + hp.getPermohonan().getIdPermohonan());
                    mau.setPermohonan(permohonan);
                    mau.setInfoAudit(ia);
                    tmp.add(mau);
                }
                HakmilikUrusan hu = hakmilikUrusanDAO.findById(id);
//                Hakmilik hmTerlibat = hu.getHakmilik();
                List<HakmilikPermohonan> senaraiHakmilik = permohonan.getSenaraiHakmilik();
                for (HakmilikPermohonan hp : senaraiHakmilik) {
                    Hakmilik hmTerlibat = hp.getHakmilik();
                    if (permohonan.getKodUrusan().getKod().equals("PINDE")) {
                        betulHakmilikPihakNew(id, hmTerlibat.getIdHakmilik()); //copy hakmilik pihak from 'id_hakmilik lama'
                        insertMohonHakmilik(id, hmTerlibat.getIdHakmilik());
                        insertMohonPihak(id, hmTerlibat.getIdHakmilik());
                        inserPemohon(id, hmTerlibat.getIdHakmilik());
                        insertMohonRujLuar(id, hmTerlibat.getIdHakmilik());
                    }
                }

            }
            if (permohonanAtasPerserahanService.save(tmp)) {
                LOGGER.info("Entering The Dragon::::::");
                if ((permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABT-K")) || (permohonan.getKodUrusan().getKod().equalsIgnoreCase("ABTKB"))) {
                    simpanHakmilikPermohonan(permohonan);
                }
                addSimpleMessage("Kemasukan data berjaya.");
            } else {
                addSimpleError("Kemasukan data GAGAL. Sila hubungi Pentadbir Sistem.");
            }
        }



        return new RedirectResolution(NotaBatalActionBean.class, "searchGadaian").addParameter("tab", "true");
    }

    public void betulHakmilikPihakNew(Long idUrusanLong, String idHakmilikBaru) {
//        LOG.info("--- betulHakmilikPihakNew() :: Start ---");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        InfoAudit info = peng.getInfoAudit();
        info.setDiKemaskiniOleh(peng);
        info.setDimasukOleh(peng);
        info.setTarikhKemaskini(new java.util.Date());
        info.setTarikhMasuk(new java.util.Date());
//        LOG.info("--- idHakmilikBaru ---" + idHakmilikBaru);
//        LOG.info("--- idUrusan ---" + idUrusan);
//        long idUrusanLong = Long.parseLong(idUrusan); // convert idUrusan to Long
//        int idUrusanInt = Integer.parseInt(idUrusan);  // convert idUrusan to integer
        if (idUrusanLong != null) {
            HakmilikUrusan hmu = hakmilikUrusanDAO.findById(idUrusanLong);
//            HakmilikUrusan hmu = pService.findByIdUrusan(idUrusanInt);  // use this to get id_hakmilik from HakmilikUrusan
            List<HakmilikPihakBerkepentingan> listPihak = pService.findByIdUrusanAndIdHakmilik(idUrusanLong, hmu.getHakmilik().getIdHakmilik()); // use this get data from HakmilikPihakBerkepentingan
            //            LOG.info("--- listPihak.size() ---" + listPihak.size());
            if (listPihak.size() > 0) { // check if there are 'hakmilik pihak berkepentingan' related to 'endorsan'
                Hakmilik hakmilik = hakmilikDAO.findById(idHakmilikBaru); // use to get input id_hakmilik

                for (HakmilikPihakBerkepentingan hpb : listPihak) {
                    HakmilikPihakBerkepentingan hpbBaru = new HakmilikPihakBerkepentingan();

                    hpbBaru.setInfoAudit(info);
                    hpbBaru.setCawangan(hakmilik.getCawangan());
                    hpbBaru.setHakmilik(hakmilik);
                    hpbBaru.setPihak(hpb.getPihak());
                    hpbBaru.setPihakCawangan(hpb.getPihakCawangan());
                    hpbBaru.setJenis(hpb.getJenis());
                    hpbBaru.setSyerPembilang(hpb.getSyerPembilang());
                    hpbBaru.setSyerPenyebut(hpb.getSyerPenyebut());
                    hpbBaru.setPerserahan(hpb.getPerserahan());
                    hpbBaru.setPerserahanBatal(hpb.getPerserahanBatal());
                    hpbBaru.setPerserahanKaveat(hpb.getPerserahanKaveat());
                    hpbBaru.setAktif('T'); //set not active until urusan have been 'daftar'
                    hpbBaru.setIdPermohonan(permohonan.getIdPermohonan());
                    hpbBaru.setKaveatAktif(hpb.getKaveatAktif());
                    hpbBaru.setJenisPengenalan(hpb.getJenisPengenalan());
                    hpbBaru.setNoPengenalan(hpb.getNoPengenalan());
                    hpbBaru.setNama(hpb.getNama());
                    hpbBaru.setAlamat1(hpb.getAlamat1());
                    hpbBaru.setAlamat2(hpb.getAlamat2());
                    hpbBaru.setAlamat3(hpb.getAlamat3());
                    hpbBaru.setAlamat4(hpb.getAlamat4());
                    hpbBaru.setPoskod(hpb.getPoskod());
                    hpbBaru.setNegeri(hpb.getNegeri());
                    hpbBaru.setWargaNegara(hpb.getWargaNegara());
                    hpbBaru.setPihakKongsiBersama(hpb.getPihakKongsiBersama());
                    hpbBaru.setPenubuhanSyarikat(hpb.getPenubuhanSyarikat());
                    hpbBaru.setJumlahPenyebut(hpb.getJumlahPenyebut());
                    hpbBaru.setJumlahPembilang(hpb.getJumlahPembilang());
                    if (hpb.getAlamatSurat() != null) {
                        AlamatSurat alamatSurat = new AlamatSurat();  // use this to copy alamat surat from 'hakmilik lama'       
                        alamatSurat.setAlamatSurat1(hpb.getAlamatSurat().getAlamatSurat1());
                        alamatSurat.setAlamatSurat2(hpb.getAlamatSurat().getAlamatSurat2());
                        alamatSurat.setAlamatSurat3(hpb.getAlamatSurat().getAlamatSurat3());
                        alamatSurat.setAlamatSurat4(hpb.getAlamatSurat().getAlamatSurat4());
                        alamatSurat.setPoskodSurat(hpb.getAlamatSurat().getPoskodSurat());
                        alamatSurat.setNegeriSurat(hpb.getAlamatSurat().getNegeriSurat());
                        hpbBaru.setAlamatSurat(alamatSurat);
                    }
                    pService.save(hpbBaru);
//                    hakmilikPihakBerkepentinganDAO.saveOrUpdate(hpbBaru);

                }

            }
            Hakmilik hakmilik = hakmilikDAO.findById(idHakmilikBaru);
            List<HakmilikUrusan> listHakmilikU = pService.findHakmilikByHakmilikAndIdMohon(idHakmilikBaru, hmu.getIdPerserahan());
            if (listHakmilikU.size() >= 0) {
                HakmilikUrusan hu = new HakmilikUrusan();
                hu.setHakmilik(hakmilik);
                hu.setIdPerserahan(hmu.getIdPerserahan());
                hu.setInfoAudit(info);
                hu.setAktif('Y');
                hu.setDaerah(hmu.getDaerah());
                hu.setCawangan(hmu.getCawangan());
                hu.setKodUrusan(hmu.getKodUrusan());
                if (hmu.getNoFail() != null) {
                    hu.setNoFail(hmu.getNoFail());
                }
                if (hmu.getTarikhDaftar() != null) {
                    hu.setTarikhDaftar(hmu.getTarikhDaftar());
                }
                if (hmu.getFolderDokumen() != null) {
                    hu.setFolderDokumen(hmu.getFolderDokumen());
                }
                hakmilikUrusanDAO.saveOrUpdate(hu);
            }
        }
//        LOG.info("--- betulHakmilikPihakNew() :: End ---");
    }

    public void insertMohonHakmilik(Long idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

        HakmilikUrusan hu = hakmilikUrusanDAO.findById(idUrusan);
        HakmilikPermohonan hpLama = hakmilikpermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        HakmilikPermohonan hpBaru = hakmilikpermohonanService.findHakmilikPermohonan(idHakmilikBaru, hu.getIdPerserahan());
        Hakmilik hakmilikBaru = hakmilikDAO.findById(idHakmilikBaru);
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        if (hpBaru == null) {
            HakmilikPermohonanData temp = new HakmilikPermohonanData();
//            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            org.springframework.beans.BeanUtils.copyProperties(hpLama, temp);
//            
//            BeanUtilsBean.getInstance().getConvertUtils().register(null, null);
//    
//            BeanUtils.copyProperties(temp, hpLama);
//            temp.setId(0);

            HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
            org.springframework.beans.BeanUtils.copyProperties(temp, hakmilikPermohonanBaru);
            hakmilikPermohonanBaru.setHakmilik(hakmilikBaru);
            hakmilikPermohonanBaru.setPermohonan(permohonanDAO.findById(hu.getIdPerserahan()));
//            hakmilikpermohonan.set
            hakmilikPermohonanBaru.setInfoAudit(info);

            pService.saveMohonHakmilik(hakmilikPermohonanBaru);

        }
        if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
            HakmilikPermohonan HpNb = hakmilikpermohonanService.findHakmilikPermohonan(idHakmilikBaru, permohonan.getIdPermohonan());;
            HakmilikPermohonan hpLamaNB = hakmilikpermohonanService.findHakmilikPermohonan(hu.getHakmilik().getIdHakmilik(), permohonan.getIdPermohonan());
            if (HpNb == null) {
                HakmilikPermohonan hakmilikPermohonanBaru = new HakmilikPermohonan();
                HakmilikPermohonanData temp = new HakmilikPermohonanData();
                org.springframework.beans.BeanUtils.copyProperties(hpLamaNB, temp);

                org.springframework.beans.BeanUtils.copyProperties(temp, hakmilikPermohonanBaru);
                hakmilikPermohonanBaru.setHakmilik(hakmilikBaru);
                hakmilikPermohonanBaru.setPermohonan(permohonan);
                hakmilikPermohonanBaru.setInfoAudit(info);

                pService.saveMohonHakmilik(hakmilikPermohonanBaru);
            }
        }

    }

    public void insertMohonPihak(Long idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

        HakmilikUrusan hu = hakmilikUrusanDAO.findById(idUrusan);

        List<PermohonanPihak> mohonPihakLIst = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilikBaru, hu.getIdPerserahan());
        List<PermohonanPihak> mohonPihakListLama = regService.findMohonPihakByHakmilikAndIdMohon(hu.getHakmilik().getIdHakmilik(), hu.getIdPerserahan());
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilikBaru);
        for (PermohonanPihak mohonP : mohonPihakListLama) {
            PermohonanPihak permohonanPihak = pService.findMohonPihakbyIdpihakAndIdHakmilik(mohonP.getPihak().getIdPihak(), mohonP.getPermohonan().getIdPermohonan(), idHakmilikBaru);
            if (permohonanPihak == null) {
                PermohonanPihak ppihak = new PermohonanPihak();
                ppihak.setPermohonan(mohonP.getPermohonan());
                ppihak.setPihak(mohonP.getPihak());
                ppihak.setHakmilik(hakmilik);
                ppihak.setJenis(mohonP.getJenis());
                ppihak.setCawangan(mohonP.getCawangan());
                ppihak.setInfoAudit(ia);
                if (mohonP.getSyerPembilang() != null) {
                    ppihak.setSyerPembilang(mohonP.getSyerPembilang());
                }
                if (mohonP.getSyerPenyebut() != null) {
                    ppihak.setSyerPenyebut(mohonP.getSyerPenyebut());
                }
                ppihak.setNama(mohonP.getNama());
                if (mohonP.getJenisPengenalan() != null) {
                    ppihak.setJenisPengenalan(mohonP.getJenisPengenalan());
                }
                if (mohonP.getNoPengenalan() != null) {
                    ppihak.setNoPengenalan(mohonP.getNoPengenalan());
                }
                if (mohonP.getWargaNegara() != null) {
                    ppihak.setWargaNegara(mohonP.getWargaNegara());
                }

                Alamat alamat = new Alamat();
                alamat.setAlamat1(mohonP.getAlamat().getAlamat1());
                alamat.setAlamat2(mohonP.getAlamat().getAlamat2());
                alamat.setAlamat3(mohonP.getAlamat().getAlamat3());
                alamat.setAlamat4(mohonP.getAlamat().getAlamat4());
                alamat.setPoskod(mohonP.getAlamat().getPoskod());
                alamat.setNegeri(mohonP.getAlamat().getNegeri());

                if (mohonP.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(mohonP.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(mohonP.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(mohonP.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(mohonP.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(mohonP.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(mohonP.getAlamatSurat().getNegeriSurat());

                    ppihak.setAlamat(alamat);
                    ppihak.setAlamatSurat(alamatSurat);
                }
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    ppihak.setKaitan(permohonan.getIdPermohonan());
                }
                permohonanPihakService.saveOrUpdate(ppihak);
            } else {
//                PermohonanPihak ppihak = new PermohonanPihak();
                permohonanPihak.setPermohonan(mohonP.getPermohonan());
                permohonanPihak.setPihak(mohonP.getPihak());
                permohonanPihak.setHakmilik(hakmilik);
                permohonanPihak.setJenis(mohonP.getJenis());
                permohonanPihak.setCawangan(mohonP.getCawangan());
                permohonanPihak.setInfoAudit(ia);
                if (mohonP.getSyerPembilang() != null) {
                    permohonanPihak.setSyerPembilang(mohonP.getSyerPembilang());
                }
                if (mohonP.getSyerPenyebut() != null) {
                    permohonanPihak.setSyerPenyebut(mohonP.getSyerPenyebut());
                }
                permohonanPihak.setNama(mohonP.getNama());
                if (mohonP.getJenisPengenalan() != null) {
                    permohonanPihak.setJenisPengenalan(mohonP.getJenisPengenalan());
                }
                if (mohonP.getNoPengenalan() != null) {
                    permohonanPihak.setNoPengenalan(mohonP.getNoPengenalan());
                }
                if (mohonP.getWargaNegara() != null) {
                    permohonanPihak.setWargaNegara(mohonP.getWargaNegara());
                }

                Alamat alamat = new Alamat();
                alamat.setAlamat1(mohonP.getAlamat().getAlamat1());
                alamat.setAlamat2(mohonP.getAlamat().getAlamat2());
                alamat.setAlamat3(mohonP.getAlamat().getAlamat3());
                alamat.setAlamat4(mohonP.getAlamat().getAlamat4());
                alamat.setPoskod(mohonP.getAlamat().getPoskod());
                alamat.setNegeri(mohonP.getAlamat().getNegeri());

                if (mohonP.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(mohonP.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(mohonP.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(mohonP.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(mohonP.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(mohonP.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(mohonP.getAlamatSurat().getNegeriSurat());

//                    permohonanPihak.setAlamat(alamat);
                    permohonanPihak.setAlamatSurat(alamatSurat);
                }
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    permohonanPihak.setKaitan(permohonan.getIdPermohonan());
                }
                permohonanPihak.setAlamat(alamat);
//                permohonanPihak.setAlamatSurat(alamatSurat);
                permohonanPihakService.saveOrUpdate(permohonanPihak);
            }

        }
    }

    public void inserPemohon(Long idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {

//        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(idUrusan);
        List<Pemohon> pemohonList = regService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        List<Pemohon> pemohonListLama = regService.findPemohonByIdPermohonanAndHakmilik(hu.getIdPerserahan(), idHakmilikBaru);
//        HakmilikPihakBerkepentingan hpk = hpbDAO.findById(Long.parseLong(idHakmilikPihakBerkepentingan));
        Hakmilik hakmilik = hakmilikDAO.findById(idHakmilikBaru);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        for (Pemohon pemohon : pemohonList) {

            Pemohon pmohon = pService.findByidPemohonOnly(String.valueOf(pemohon.getPihak().getIdPihak()), String.valueOf(pemohon.getPermohonan().getIdPermohonan()), idHakmilikBaru);
            if (pmohon == null) {
                Pemohon pe = new Pemohon();

                pe.setInfoAudit(ia);
                pe.setPermohonan(pemohon.getPermohonan());
                pe.setPihak(pemohon.getPihak());
                pe.setKaitan(permohonan.getIdPermohonan());
                pe.setCawangan(pengguna.getKodCawangan());
                pe.setHakmilik(hakmilik);
                pe.setJenis(pemohon.getJenis());

                pe.setNama(pemohon.getNama());
                if (pemohon.getNoPengenalan() != null) {
                    pe.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getSyerPembilang() != null) {
                    pe.setSyerPembilang(pemohon.getSyerPembilang());
                }
                if (pemohon.getSyerPenyebut() != null) {
                    pe.setSyerPenyebut(pemohon.getSyerPenyebut());
                }
//                pe.setNama(pemohon.getNama());
                if (pemohon.getJenisPengenalan() != null) {
                    pe.setJenisPengenalan(pemohon.getJenisPengenalan());
                }
                if (pe.getNoPengenalan() != null) {
                    pe.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getWargaNegara() != null) {
                    pe.setWargaNegara(pemohon.getWargaNegara());
                }
                Alamat alamat = new Alamat();
                if (pemohon.getAlamat() != null) {
                    alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                    alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                    alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                    alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                    alamat.setPoskod(pemohon.getPihak().getPoskod());
                    alamat.setNegeri(pemohon.getPihak().getNegeri());
                    pe.setAlamat(alamat);
                }

                if (pe.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(pmohon.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(pmohon.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(pmohon.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(pmohon.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(pmohon.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(pmohon.getAlamatSurat().getNegeriSurat());
//                    senaraiPemohon.add(pe);
                    pe.setAlamatSurat(alamatSurat);
                }

                pemohonService.saveOrUpdate(pe);
                if (permohonan.getKodUrusan().getKod().equals("BETHM")) {
                    pe.setKaitan(permohonan.getIdPermohonan());
                }
            } else {
                pmohon.setInfoAudit(ia);
                pmohon.setPermohonan(pemohon.getPermohonan());
                pmohon.setPihak(pemohon.getPihak());
                pmohon.setKaitan(permohonan.getIdPermohonan());
                pmohon.setCawangan(pengguna.getKodCawangan());
                pmohon.setHakmilik(hakmilik);
                pmohon.setJenis(pemohon.getJenis());

                pmohon.setNama(pemohon.getNama());
                if (pemohon.getNoPengenalan() != null) {
                    pmohon.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getSyerPembilang() != null) {
                    pmohon.setSyerPembilang(pemohon.getSyerPembilang());
                }
                if (pemohon.getSyerPenyebut() != null) {
                    pmohon.setSyerPenyebut(pemohon.getSyerPenyebut());
                }
//                pe.setNama(pemohon.getNama());
                if (pemohon.getJenisPengenalan() != null) {
                    pmohon.setJenisPengenalan(pemohon.getJenisPengenalan());
                }
                if (pmohon.getNoPengenalan() != null) {
                    pmohon.setNoPengenalan(pemohon.getNoPengenalan());
                }
                if (pemohon.getWargaNegara() != null) {
                    pmohon.setWargaNegara(pemohon.getWargaNegara());
                }
                Alamat alamat = new Alamat();
                if (pemohon.getAlamat() != null) {
                    alamat.setAlamat1(pmohon.getPihak().getAlamat1());
                    alamat.setAlamat2(pmohon.getPihak().getAlamat2());
                    alamat.setAlamat3(pmohon.getPihak().getAlamat3());
                    alamat.setAlamat4(pmohon.getPihak().getAlamat4());
                    alamat.setPoskod(pmohon.getPihak().getPoskod());
                    alamat.setNegeri(pmohon.getPihak().getNegeri());
                }

                if (pemohon.getAlamatSurat() != null) {
                    AlamatSurat alamatSurat = new AlamatSurat();
                    alamatSurat.setAlamatSurat1(pmohon.getAlamatSurat().getAlamatSurat1());
                    alamatSurat.setAlamatSurat2(pmohon.getAlamatSurat().getAlamatSurat2());
                    alamatSurat.setAlamatSurat3(pmohon.getAlamatSurat().getAlamatSurat3());
                    alamatSurat.setAlamatSurat4(pmohon.getAlamatSurat().getAlamatSurat4());
                    alamatSurat.setPoskodSurat(pmohon.getAlamatSurat().getPoskodSurat());
                    alamatSurat.setNegeriSurat(pmohon.getAlamatSurat().getNegeriSurat());
//                    senaraiPemohon.add(pe);

                }
            }
        }
    }

    public void insertMohonRujLuar(Long idUrusan, String idHakmilikBaru) throws IllegalAccessException, InvocationTargetException {
//        HakmilikUrusan hu = hakmilikUrusanDAO.findById(Long.parseLong(idUrusan));
        HakmilikUrusan hu = hakmilikUrusanDAO.findById(idUrusan);
        PermohonanRujukanLuar mrlLama = pService.findRujukanIDMohonHakmilik3(hu.getIdPerserahan(), hu.getHakmilik().getIdHakmilik());
        PermohonanRujukanLuar mrlBaru = pService.findRujukanIDMohonHakmilik3(hu.getIdPerserahan(), idHakmilikBaru);
        LOGGER.info("MRL LAMA " + mrlLama.getNoFail());
        InfoAudit info = new InfoAudit();
        info.setDimasukOleh(pengguna);
        info.setTarikhMasuk(new java.util.Date());
        if (mrlBaru == null) {

            PermohonanRujukanLuar mrl = new PermohonanRujukanLuar();

            Permohonan mhn = permohonanDAO.findById(hu.getIdPerserahan());
            Hakmilik hm = pService.findHakmilik(idHakmilikBaru);
            mrl.setPermohonan(mhn);
            mrl.setHakmilik(hm);
            mrl.setCawangan(mhn.getCawangan());
            mrl.setNoFail(mrlLama.getNoFail());
            mrl.setNoRujukan(mrlLama.getNoRujukan());
            mrl.setTarikhRujukan(mrlLama.getTarikhRujukan());
            mrl.setNamaMasuk(mrlLama.getNamaMasuk());
            mrl.setInfoAudit(info);
            regService.saveOrUpdate(mrl);
        }

    }

    public Resolution delete() {
        String id = getContext().getRequest().getParameter("id");
        String idHakmilik = getContext().getRequest().getParameter("idHakmilik");
//        String idUrusan = getContext().getRequest().getParameter("idUrusan");

        PermohonanAtasPerserahan mau = pService.findByIdAUOnly(Long.parseLong(id));
        List<HakmilikUrusan> listHakmilikU = pService.findHakmilikByHakmilikAndIdMohon(idHakmilik, mau.getUrusan().getIdPerserahan());
        List<PermohonanPihak> mohonPihakLIst = regService.findMohonPihakByHakmilikAndIdMohon(idHakmilik, mau.getUrusan().getIdPerserahan());
        List<Pemohon> pemohonListLama = regService.findPemohonByIdPermohonanAndHakmilik(mau.getUrusan().getIdPerserahan(), idHakmilik);
        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihakBerkepentingans = pService.findByIdUrusanBatalAndIdHakmilik(mau.getUrusan().getIdUrusan(), idHakmilik);

        hakmilik = hakmilikDAO.findById(idHakmilik);
        InfoAudit ia = new InfoAudit();
        ia.setDimasukOleh(pengguna);
        ia.setTarikhMasuk(new java.util.Date());
        for (Pemohon pemohon : pemohonListLama) {
            pemohonService.delete(pemohon);
        }
//        for (PermohonanPihak mohonP : mohonPihakLIst) {
//            permohonanPihakService.delete(mohonP);
//        }
//        if (permohonan.getKodUrusan().getKod().equals("PINDE")) {
//            for (HakmilikUrusan hakmilikU : listHakmilikU) {
//                hakmilikUrusanDAO.delete(hakmilikU);
//            }
//        }
//        for (HakmilikPihakBerkepentingan mp : senaraiHakmilikPihakBerkepentingans) {
//            hakmilikPihakBerkepentinganDAO.delete(mp);
//        }
        permohonanAtasPerserahanService.delete(Long.parseLong(id));




        return new RedirectResolution(NotaBatalActionBean.class, "searchGadaian");
    }

    @Before(stages = {LifecycleStage.BindingAndValidation}, on = {"!delete"})
    public void rehydrate() {
        pengguna = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        String idPermohonan = (String) getContext().getRequest().getSession().getAttribute("idPermohonan");
        permohonan = permohonanDAO.findById(idPermohonan);
        kodUrusan = permohonan.getKodUrusan().getKod();

        if (!permohonan.getSenaraiHakmilik().isEmpty()) {
            String idHakmilikSama = permohonan.getSenaraiHakmilik().get(0).getHakmilik().getIdHakmilik();
            hakmilik = hakmilikDAO.findById(idHakmilikSama);
            LOGGER.debug(idHakmilikSama);
            hakmilikUrusanPermohonan = batalNotaService.findHakmilikUrusanByIdHakmilik(idHakmilikSama);
        }
        hakmilikPermohonanList = permohonan.getSenaraiHakmilik();
        hakmilikUrusanList = new ArrayList<HakmilikUrusan>();
        for (HakmilikPermohonan hp : hakmilikPermohonanList) {
            if (StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "PINDE")) {
                hakmilikUrusanList1 = batalNotaService.searchHakmilikUrusanNota(
                        permohonan.getKodUrusan().getKod(), hp.getHakmilik().getIdHakmilikInduk());
            } else {
                hakmilikUrusanList1 = batalNotaService.searchHakmilikUrusanNota(
                        permohonan.getKodUrusan().getKod(), hp.getHakmilik().getIdHakmilik());
            }

            if (hakmilikUrusanList1.size() != 0) {
//                    hakmilikUrusanList = hakmilikUrusanList1;
//                    LOGGER.info("HakmilikUrusanLIST SIZE:: "+hakmilikUrusanList.size());
                for (HakmilikUrusan hU : hakmilikUrusanList1) {
                    if (hakmilikUrusanList.contains(hU)) {

                        continue;
                    }
                    hakmilikUrusanList.add(hU);
                    LOGGER.info("HakmilikUrusanLIST SIZE Tracing:: " + hakmilikUrusanList1.size());
                }
            }
            //Urusan 
            if (StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "PMDPT")) {
                List<HakmilikPermohonan> hpList = permohonan.getSenaraiHakmilik();
                hakmilikMohonList = new ArrayList<HakmilikPermohonan>();
                for (HakmilikPermohonan hMohon : hpList) {
                    hakmilikMohonList.addAll(batalNotaService.findUrusanBatalTolak(hMohon.getHakmilik().getIdHakmilik()));
                }
                LOGGER.info("SizeHakmilikMohonList : " + hakmilikMohonList.size());
            }
            if (permohonan != null) {
                senaraiMau = permohonan.getSenaraiPermohonanAtasPerserahan();
            }
        }

        //need to disable bcoz some of urusan dont have tarih daftar
//      if (!StringUtils.equalsIgnoreCase(permohonan.getKodUrusan().getKod(), "PMDPT")) {
//          Collections.sort(hakmilikUrusanList, URUSAN_ORDER);
//          Collections.sort(senaraiMau, URUSAN_ATAS_PERMOHONAN_ORDER);
//      }
    }

    public void simpanHakmilikPermohonan(Permohonan permohonan) {
        LOGGER.debug("--- simpanHakmilikPermohonan ---");
        Pengguna peng = (Pengguna) getContext().getRequest().getSession().getAttribute(etanahActionBeanContext.KEY_USER);
        List<PermohonanAtasPerserahan> pApList = new ArrayList<PermohonanAtasPerserahan>();
        HakmilikUrusan hakmilikUrusan = new HakmilikUrusan();
        HakmilikPermohonan hakmilikPermohonan = new HakmilikPermohonan();
        HakmilikPermohonan hakmilikPermohonanAsal = new HakmilikPermohonan();
        InfoAudit info = new InfoAudit();
        pApList = batalNotaService.findByidPermohonan(permohonan.getIdPermohonan());

        if (pApList.size() != 0) {

            for (PermohonanAtasPerserahan pAp : pApList) {
                hakmilikUrusan = pAp.getUrusan();
                hakmilikPermohonan = batalNotaService.findByIdUrusan(hakmilikUrusan.getIdPerserahan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                hakmilikPermohonanAsal = batalNotaService.findByIdUrusan(permohonan.getIdPermohonan(), hakmilikUrusan.getHakmilik().getIdHakmilik());
                if ((hakmilikPermohonan != null) && (hakmilikPermohonanAsal != null)) {
                    info = peng.getInfoAudit();
                    info.setDimasukOleh(peng);
                    info.setTarikhMasuk(hakmilikPermohonanAsal.getInfoAudit().getTarikhMasuk());
                    info.setDiKemaskiniOleh(peng);
                    info.setTarikhKemaskini(new java.util.Date());

                    hakmilikPermohonanAsal.setInfoAudit(info);
                    hakmilikPermohonanAsal.setLuasTerlibat(hakmilikPermohonan.getLuasTerlibat());
//                  hakmilikPermohonanAsal.setLuasTerlibat(null);
                    hakmilikPermohonanAsal.setKodUnitLuas(hakmilikPermohonan.getKodUnitLuas());
                    hakmilikPermohonanAsal.setCukaiBaru(hakmilikPermohonan.getCukaiBaru());
                    notaService.simpanSingle(hakmilikPermohonanAsal);
                }
            }
        }
    }

    public Permohonan getPermohonan() {
        return permohonan;
    }

    public void setPermohonan(Permohonan permohonan) {
        this.permohonan = permohonan;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList() {
        return hakmilikUrusanList;
    }

    public void setHakmilikUrusanList(List<HakmilikUrusan> hakmilikUrusanList) {
        this.hakmilikUrusanList = hakmilikUrusanList;
    }

    public String[] getChkbox() {
        return chkbox;
    }

    public void setChkbox(String[] chkbox) {
        this.chkbox = chkbox;
    }

    public List<PermohonanAtasPerserahan> getSenaraiMau() {
        return senaraiMau;
    }

    public void setSenaraiMau(List<PermohonanAtasPerserahan> senaraiMau) {
        this.senaraiMau = senaraiMau;
    }

    public List<HakmilikUrusan> getHakmilikUrusanList1() {
        return hakmilikUrusanList1;
    }

    public void setHakmilikUrusanList1(List<HakmilikUrusan> hakmilikUrusanList1) {
        this.hakmilikUrusanList1 = hakmilikUrusanList1;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public List<HakmilikPermohonan> getHakmilikMohonList() {
        return hakmilikMohonList;
    }

    public void setHakmilikMohonList(List<HakmilikPermohonan> hakmilikMohonList) {
        this.hakmilikMohonList = hakmilikMohonList;
    }

    public List<HakmilikPermohonan> getHakmilikMohonList2() {
        return hakmilikMohonList2;
    }

    public void setHakmilikMohonList2(List<HakmilikPermohonan> hakmilikMohonList2) {
        this.hakmilikMohonList2 = hakmilikMohonList2;
    }

    public String getKodUrusan() {
        return kodUrusan;
    }

    public void setKodUrusan(String kodUrusan) {
        this.kodUrusan = kodUrusan;
    }

    public Hakmilik getHakmilik() {
        return hakmilik;
    }

    public void setHakmilik(Hakmilik hakmilik) {
        this.hakmilik = hakmilik;
    }
}
