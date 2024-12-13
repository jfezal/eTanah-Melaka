/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodDaerah;
import etanah.model.KodStatusHakmilik;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPlotPelan;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author faidzal
 */
public class JanaHakmilikService {

    @Inject
    public HakmilikDAO hakmilikDAO;
    @Inject
    RegService regService;
    @Inject
    etanah.Configuration conf;
    @Inject
    KodStatusHakmilikDAO kodStatusHakmilikDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    @Inject
    HakmilikSebelumPermohonanDAO hakmilikSblmPermohonanDAO;
    @Inject
    SejarahHakmilikDAO sejarahHakmilikDAO;
    @Inject
    HakmilikPihakKepentinganService hpkService;
    @Inject
    HakmilikUrusanService hakmilikUrusanService;
    @Inject
    etanah.sequence.GeneratorIdHakmilik idHakmilikGenerator;
    //public String idFail;
    public String namaBPM;
    public String kodNegeri;
    public ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private static final Logger LOG = Logger.getLogger(JanaHakmilikService.class);
    private static final boolean debug = LOG.isDebugEnabled();

    public void janaHakmilikBaruFromHakmilikTerlibat(Hakmilik hakmilik, Hakmilik hakmilikBaru, InfoAudit ia, Permohonan p, Pengguna pengguna) {
        LOG.info("janahakmilikbarufromhakmilikterlibat:::::::::::::");
        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");
        String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
        KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
        //hakmilikBaru.setNoFail(idFail);
        hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
        hakmilikBaru.setNoHakmilik(noHakmilik);
        //newly added
        hakmilikBaru.setInfoAudit(ia);

        mohonHakmilikBaru = new HakmilikPermohonan();
        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
        mohonHakmilikBaru.setPermohonan(p);
        //
        mohonHakmilikBaru.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
        mohonHakmilikBaru.setSyaratNyata(hakmilik.getSyaratNyata());
        mohonHakmilikBaru.setKodKegunaanTanah(hakmilik.getKegunaanTanah());
        mohonHakmilikBaru.setLuasTerlibat(hakmilik.getLuas());
        mohonHakmilikBaru.setKodUnitLuas(hakmilik.getKodUnitLuas());
        mohonHakmilikBaru.setLot(hakmilik.getLot());
        mohonHakmilikBaru.setNoLot(hakmilik.getNoLot());
        mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilik.getKategoriTanah());
        mohonHakmilikBaru.setKodKegunaanTanah(hakmilik.getKegunaanTanah());

        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS") && !p.getKodUrusan().getKod().equals("HKGHS")) {
            mohonHakmilikBaru.setSekatanKepentingan(hakmilikBaru.getSekatanKepentingan());
            mohonHakmilikBaru.setSekatanKepentinganBaru(hakmilikBaru.getSekatanKepentingan());

            mohonHakmilikBaru.setSyaratNyata(hakmilikBaru.getSyaratNyata());
            mohonHakmilikBaru.setSyaratNyataBaru(hakmilikBaru.getSyaratNyata());

            mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilikBaru.getKategoriTanah());
            mohonHakmilikBaru.setKategoriTanahBaru(hakmilikBaru.getKategoriTanah());

            mohonHakmilikBaru.setKodKegunaanTanah(hakmilikBaru.getKegunaanTanah());

            mohonHakmilikBaru.setLuasTerlibat(hakmilikBaru.getLuas());
            mohonHakmilikBaru.setKodUnitLuas(hakmilikBaru.getKodUnitLuas());
            mohonHakmilikBaru.setLot(hakmilikBaru.getLot());
            mohonHakmilikBaru.setNoLot(hakmilikBaru.getNoLot());

        } else {

            mohonHakmilikBaru.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
            mohonHakmilikBaru.setSyaratNyata(hakmilik.getSyaratNyata());
            mohonHakmilikBaru.setKodKegunaanTanah(hakmilik.getKegunaanTanah());
            mohonHakmilikBaru.setLuasTerlibat(hakmilikBaru.getLuas());
            mohonHakmilikBaru.setKodUnitLuas(hakmilikBaru.getKodUnitLuas());
            mohonHakmilikBaru.setLot(hakmilikBaru.getLot());
            mohonHakmilikBaru.setNoLot(hakmilikBaru.getNoLot());
            mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilik.getKategoriTanah());

        }
        //
        mohonHakmilikBaru.setInfoAudit(ia);


        LOG.debug("idHakmilikBaru---" + idHakmilikBaru);
        LOG.debug("idHakmilikBaru_kodBpm---" + hakmilikBaru.getBandarPekanMukim().getKod());
        LOG.debug("idHakmilikBaru_kodLot---" + hakmilikBaru.getLot().getKod());
        LOG.debug("idHakmilikBaru_NoLot---" + hakmilikBaru.getNoLot());

        hakmilikDAO.save(hakmilikBaru);
        hakmilikPermohonanDAO.save(mohonHakmilikBaru);

        Hakmilik hm = hakmilik;


//                    if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
        LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
        LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
        LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

        if (hm.getSenaraiHakmilikAsal().size() > 0) {
            for (int a = 0; a < hm.getSenaraiHakmilikAsal().size(); a++) {
                HakmilikAsal hma1 = (HakmilikAsal) hm.getSenaraiHakmilikAsal().get(a);
                HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                hap.setHakmilikPermohonan(mohonHakmilikBaru);
                //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                hap.setHakmilik(hakmilikBaru);
                hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                hap.setInfoAudit(ia);
                hakmilikAsalPermohonanDAO.save(hap);
            }
        } else if (hm.getSenaraiHakmilikAsal().size() == 0) {
            HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
            hap.setHakmilikPermohonan(mohonHakmilikBaru);
            //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
            hap.setHakmilik(hakmilikBaru);
            hap.setHakmilikSejarah(hm.getIdHakmilik());
            hap.setInfoAudit(ia);
            hakmilikAsalPermohonanDAO.save(hap);
        }

        if (hm.getSenaraiHakmilikAsal().size() != 0) {
            LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
            HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
            hsp.setHakmilikPermohonan(mohonHakmilikBaru);
            hsp.setPermohonan(p);
            hsp.setCawangan(pengguna.getKodCawangan());
            hsp.setHakmilik(hakmilikBaru);
            hsp.setHakmilikSejarah(hm.getIdHakmilik());
            hsp.setInfoAudit(ia);
            hakmilikSblmPermohonanDAO.save(hsp);
        }

        if (debug) {
            LOG.debug("start to create hakmilik pihak");
        }
//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }
            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
            hpb.setHakmilik(hakmilikBaru);
            hpb.setCawangan(hakmilikBaru.getCawangan());
            hpb.setPihakCawangan(hpk.getPihakCawangan());
            hpb.setJenis(hpk.getJenis());
            hpb.setSyerPembilang(hpk.getSyerPembilang());
            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
            hpb.setPerserahan(hpk.getPerserahan());
            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
            hpb.setKaveatAktif(hpk.getKaveatAktif());
            hpb.setAktif(hpk.getAktif());
            hpb.setPihak(hpk.getPihak());

            hpb.setNama(hpk.getNama());
            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
            hpb.setNoPengenalan(hpk.getNoPengenalan());
            hpb.setAlamat1(hpk.getAlamat1());
            hpb.setAlamat2(hpk.getAlamat2());
            hpb.setAlamat3(hpk.getAlamat3());
            hpb.setAlamat4(hpk.getAlamat4());
            hpb.setPoskod(hpk.getPoskod());
            hpb.setNegeri(hpk.getNegeri());
            hpb.setWargaNegara(hpk.getWargaNegara());
            hpb.setAlamatSurat(hpk.getAlamatSurat());
            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
            //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

            hpb.setInfoAudit(ia);
            hpkService.save(hpb);
        }
        if (debug) {
            LOG.debug("start to create hakmilik urusan");
        }

        List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
        for (HakmilikUrusan hu : senaraiHakmilikurusan) {
            if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                continue;
            }
            HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
            hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
            hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
            hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
            hakmilikUrusanBaru.setAktif(hu.getAktif());
            hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
            hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
            hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
            hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
            hakmilikUrusanBaru.setCawangan(hu.getCawangan());
            KodDaerah kd = hu.getDaerah();
            if (kd == null) {
                kd = hu.getHakmilik().getDaerah();
            }
            hakmilikUrusanBaru.setDaerah(kd);
            hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
            hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
            hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
            hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
            hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
            hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
            hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
            hakmilikUrusanBaru.setInfoAudit(ia);
            hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

        }

    }

    public void janaHakmilikBaruPenyatuanFromHakmilikTerlibat(List<Hakmilik> senaraiHakmilik, Hakmilik hakmilikBaru, InfoAudit ia, Permohonan p, Pengguna pengguna) {
        LOG.info("janahakmilikbarufromhakmilikterlibat:::::::::::::");
        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");
        String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
        KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
        //hakmilikBaru.setNoFail(idFail);
        hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
        hakmilikBaru.setNoHakmilik(noHakmilik);
        //newly added
        hakmilikBaru.setInfoAudit(ia);
        mohonHakmilikBaru = new HakmilikPermohonan();
        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
        mohonHakmilikBaru.setPermohonan(p);
        //
        mohonHakmilikBaru.setSekatanKepentingan(senaraiHakmilik.get(0).getSekatanKepentingan());
        mohonHakmilikBaru.setSyaratNyata(senaraiHakmilik.get(0).getSyaratNyata());
        mohonHakmilikBaru.setKodKegunaanTanah(senaraiHakmilik.get(0).getKegunaanTanah());
        mohonHakmilikBaru.setLuasTerlibat(totalLuas(senaraiHakmilik));
        mohonHakmilikBaru.setKodUnitLuas(senaraiHakmilik.get(0).getKodUnitLuas());
        //mohonHakmilikBaru.setLot(hakmilik.getLot());
        //mohonHakmilikBaru.setNoLot(hakmilik.getNoLot());
        mohonHakmilikBaru.setJenisPenggunaanTanah(senaraiHakmilik.get(0).getKategoriTanah());
        mohonHakmilikBaru.setKodKegunaanTanah(senaraiHakmilik.get(0).getKegunaanTanah());
        //
        mohonHakmilikBaru.setInfoAudit(ia);


        LOG.debug("idHakmilikBaru---" + idHakmilikBaru);
        LOG.debug("idHakmilikBaru_kodBpm---" + hakmilikBaru.getBandarPekanMukim().getKod());
        LOG.debug("idHakmilikBaru_kodLot---" + hakmilikBaru.getLot().getKod());
        LOG.debug("idHakmilikBaru_NoLot---" + hakmilikBaru.getNoLot());

        hakmilikDAO.save(hakmilikBaru);
        hakmilikPermohonanDAO.save(mohonHakmilikBaru);

        for (int ix = 0; ix < senaraiHakmilik.size(); ix++) {
            Hakmilik hm = senaraiHakmilik.get(ix);

            //                    if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
            LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
            LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
            LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

            if (hm.getSenaraiHakmilikAsal().size() > 0) {
                for (int a = 0; a < hm.getSenaraiHakmilikAsal().size(); a++) {
                    HakmilikAsal hma1 = (HakmilikAsal) hm.getSenaraiHakmilikAsal().get(a);
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                    hap.setHakmilik(hakmilikBaru);
                    hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                    hap.setInfoAudit(ia);
                    hakmilikAsalPermohonanDAO.save(hap);
                }
            } else if (hm.getSenaraiHakmilikAsal().size() == 0) {
                HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                hap.setHakmilikPermohonan(mohonHakmilikBaru);
                //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                hap.setHakmilik(hakmilikBaru);
                hap.setHakmilikSejarah(hm.getIdHakmilik());
                hap.setInfoAudit(ia);
                hakmilikAsalPermohonanDAO.save(hap);
            }

            if (hm.getSenaraiHakmilikAsal().size() != 0) {
                LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                hsp.setPermohonan(p);
                hsp.setCawangan(pengguna.getKodCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hm.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);
            }

            if (debug) {
                LOG.debug("start to create hakmilik urusan");
            }

            List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
            for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                    continue;
                }
                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                hakmilikUrusanBaru.setAktif(hu.getAktif());
                hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                KodDaerah kd = hu.getDaerah();
                if (kd == null) {
                    kd = hu.getHakmilik().getDaerah();
                }
                hakmilikUrusanBaru.setDaerah(kd);
                hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                hakmilikUrusanBaru.setInfoAudit(ia);
                hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

            }


        }





        if (debug) {
            LOG.debug("start to create hakmilik pihak");
        }
        Hakmilik tuanPunyaHm = senaraiHakmilik.get(0); // because pytn tuanpunya mesti sama

        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(tuanPunyaHm);
        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }
            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
            hpb.setHakmilik(hakmilikBaru);
            hpb.setCawangan(hakmilikBaru.getCawangan());
            hpb.setPihakCawangan(hpk.getPihakCawangan());
            hpb.setJenis(hpk.getJenis());
            hpb.setSyerPembilang(hpk.getSyerPembilang());
            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
            hpb.setPerserahan(hpk.getPerserahan());
            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
            hpb.setKaveatAktif(hpk.getKaveatAktif());
            hpb.setAktif(hpk.getAktif());
            hpb.setPihak(hpk.getPihak());

            hpb.setNama(hpk.getNama());
            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
            hpb.setNoPengenalan(hpk.getNoPengenalan());
            hpb.setAlamat1(hpk.getAlamat1());
            hpb.setAlamat2(hpk.getAlamat2());
            hpb.setAlamat3(hpk.getAlamat3());
            hpb.setAlamat4(hpk.getAlamat4());
            hpb.setPoskod(hpk.getPoskod());
            hpb.setNegeri(hpk.getNegeri());
            hpb.setWargaNegara(hpk.getWargaNegara());
            hpb.setAlamatSurat(hpk.getAlamatSurat());
            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
            //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

            hpb.setInfoAudit(ia);
            hpkService.save(hpb);
        }




    }

    public Hakmilik returnJanaHakmilikBaruFromHakmilikTerlibat(Hakmilik hakmilik, Hakmilik hakmilikBaru, InfoAudit ia, Permohonan p, Pengguna pengguna, PermohonanPlotPelan permohonanPlotPelan) {
        LOG.info("janahakmilikbarufromhakmilikterlibat:::::::::::::");
        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");
        String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
        KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
        //hakmilikBaru.setNoFail(idFail);
        hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
        hakmilikBaru.setNoHakmilik(noHakmilik);
        hakmilikBaru.setInfoAudit(ia);
        mohonHakmilikBaru = new HakmilikPermohonan();
        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
        mohonHakmilikBaru.setPermohonan(p);
        //
        if (p.getPermohonanSebelum().getKodUrusan().getKod().equals("TSPSS") && !p.getKodUrusan().getKod().equals("HKGHS")) {
            mohonHakmilikBaru.setSekatanKepentingan(hakmilikBaru.getSekatanKepentingan());
            mohonHakmilikBaru.setSekatanKepentinganBaru(hakmilikBaru.getSekatanKepentingan());

            mohonHakmilikBaru.setSyaratNyata(hakmilikBaru.getSyaratNyata());
            mohonHakmilikBaru.setSyaratNyataBaru(hakmilikBaru.getSyaratNyata());

            mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilikBaru.getKategoriTanah());
            mohonHakmilikBaru.setKategoriTanahBaru(hakmilikBaru.getKategoriTanah());

            mohonHakmilikBaru.setKodKegunaanTanah(hakmilikBaru.getKegunaanTanah());

            mohonHakmilikBaru.setLuasTerlibat(hakmilikBaru.getLuas());
            mohonHakmilikBaru.setKodUnitLuas(hakmilikBaru.getKodUnitLuas());
            mohonHakmilikBaru.setLot(hakmilikBaru.getLot());
            mohonHakmilikBaru.setNoLot(hakmilikBaru.getNoLot());



        } else {
            mohonHakmilikBaru.setSekatanKepentingan(hakmilik.getSekatanKepentingan());
            mohonHakmilikBaru.setSyaratNyata(hakmilik.getSyaratNyata());
            mohonHakmilikBaru.setKodKegunaanTanah(hakmilik.getKegunaanTanah());
            mohonHakmilikBaru.setLuasTerlibat(hakmilikBaru.getLuas());
            mohonHakmilikBaru.setKodUnitLuas(hakmilikBaru.getKodUnitLuas());
            mohonHakmilikBaru.setLot(hakmilikBaru.getLot());
            mohonHakmilikBaru.setNoLot(hakmilikBaru.getNoLot());
            mohonHakmilikBaru.setJenisPenggunaanTanah(hakmilik.getKategoriTanah());

        }

        //
        mohonHakmilikBaru.setInfoAudit(ia);


        LOG.debug("idHakmilikBaru---" + idHakmilikBaru);
        LOG.debug("idHakmilikBaru_kodBpm---" + hakmilikBaru.getBandarPekanMukim().getKod());
        LOG.debug("idHakmilikBaru_kodLot---" + hakmilikBaru.getLot().getKod());
        LOG.debug("idHakmilikBaru_NoLot---" + hakmilikBaru.getNoLot());

        hakmilikDAO.save(hakmilikBaru);
        hakmilikPermohonanDAO.save(mohonHakmilikBaru);

        Hakmilik hm = hakmilik;

        if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
            LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
            LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
            LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

            if (hm.getSenaraiHakmilikAsal().size() > 0) {
                for (int a = 0; a < hm.getSenaraiHakmilikAsal().size(); a++) {
                    HakmilikAsal hma1 = (HakmilikAsal) hm.getSenaraiHakmilikAsal().get(a);
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                    hap.setHakmilik(hakmilikBaru);
                    hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                    hap.setInfoAudit(ia);
                    hakmilikAsalPermohonanDAO.save(hap);
                }
            } else if (hm.getSenaraiHakmilikAsal().size() == 0) {
                HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                hap.setHakmilikPermohonan(mohonHakmilikBaru);
                //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                hap.setHakmilik(hakmilikBaru);
                hap.setHakmilikSejarah(hm.getIdHakmilik());
                hap.setInfoAudit(ia);
                hakmilikAsalPermohonanDAO.save(hap);
            }

            if (hm.getSenaraiHakmilikAsal().size() != 0) {
                LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                hsp.setPermohonan(p);
                hsp.setCawangan(pengguna.getKodCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hm.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);
            }
        }

        if (p.getKodUrusan().getKod().equals("HSPB") || p.getKodUrusan().getKod().equals("HKPB")) {

            if (debug) {
                LOG.debug("start to create hakmilik pihak -- pecah bahagian");
            }

            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = new ArrayList<HakmilikPihakBerkepentingan>();

            Permohonan permohonanSblm = p.getPermohonanSebelum();
            List<Pemohon> listPemohon = permohonanSblm.getSenaraiPemohon();
            for (int ix = 0; ix < listPemohon.size(); ix++) {
                Pemohon pmohon = listPemohon.get(ix);
                if (permohonanPlotPelan != null) {
                    if (pmohon.getDalamanNilai1().equals(permohonanPlotPelan.getNoPlot())) {
                        HakmilikPihakBerkepentingan hpk = hpkService.findHakmilikPihakByIdPihak(pmohon.getPihak(), hm);
                        hpk.setSyerPembilang(pmohon.getJumlahPembilang());
                        hpk.setSyerPenyebut(pmohon.getJumlahPenyebut());
                        senaraiHakmilikPihak.add(hpk);
                    }
                }
            }

            //List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
            //List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);
            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                if (hpk == null || hpk.getAktif() == 'T') {
                    continue;
                }
                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                hpb.setHakmilik(hakmilikBaru);
                hpb.setCawangan(hakmilikBaru.getCawangan());
                hpb.setPihakCawangan(hpk.getPihakCawangan());
                hpb.setJenis(hpk.getJenis());
                hpb.setSyerPembilang(hpk.getSyerPembilang());
                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                hpb.setJumlahPembilang(hpk.getSyerPembilang());
                hpb.setJumlahPenyebut(hpk.getSyerPenyebut());
                hpb.setPerserahan(hpk.getPerserahan());
                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                hpb.setKaveatAktif(hpk.getKaveatAktif());
                hpb.setAktif(hpk.getAktif());
                hpb.setPihak(hpk.getPihak());

                hpb.setNama(hpk.getNama());
                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                hpb.setNoPengenalan(hpk.getNoPengenalan());
                hpb.setAlamat1(hpk.getAlamat1());
                hpb.setAlamat2(hpk.getAlamat2());
                hpb.setAlamat3(hpk.getAlamat3());
                hpb.setAlamat4(hpk.getAlamat4());
                hpb.setPoskod(hpk.getPoskod());
                hpb.setNegeri(hpk.getNegeri());
                hpb.setWargaNegara(hpk.getWargaNegara());
                hpb.setAlamatSurat(hpk.getAlamatSurat());
                hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
                //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

                hpb.setInfoAudit(ia);
                hpkService.save(hpb);
            }

        } else {

            if (debug) {
                LOG.debug("start to create hakmilik pihak");
            }
//                    List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hm.getSenaraiPihakBerkepentingan();
            List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hm);


//            for (int i = 0; i < senaraiHakmilikPihak.size(); i++) {
//                HakmilikPihakBerkepentingan hpb = (HakmilikPihakBerkepentingan) senaraiHakmilikPihak.get(i);
//                if (hpb.getNoPengenalan() == null) {
//                    if (hpb.getPihak().getNoPengenalan() != null) {
//                        hpb.setNoPengenalan(hpb.getPihak().getNoPengenalan());
//                    }
//                }
//            }

            for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
                if (hpk == null || hpk.getAktif() == 'T') {
                    continue;
                }
                HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
                hpb.setHakmilik(hakmilikBaru);
                hpb.setCawangan(hakmilikBaru.getCawangan());
                hpb.setPihakCawangan(hpk.getPihakCawangan());
                hpb.setJenis(hpk.getJenis());
                hpb.setSyerPembilang(hpk.getSyerPembilang());
                hpb.setSyerPenyebut(hpk.getSyerPenyebut());
                hpb.setJumlahPembilang(hpk.getJumlahPembilang());
                hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
                hpb.setPerserahan(hpk.getPerserahan());
                hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                hpb.setKaveatAktif(hpk.getKaveatAktif());
                hpb.setAktif(hpk.getAktif());
                hpb.setPihak(hpk.getPihak());

                hpb.setNama(hpk.getNama());
                hpb.setJenisPengenalan(hpk.getJenisPengenalan());
                hpb.setNoPengenalan(hpk.getNoPengenalan());
                hpb.setAlamat1(hpk.getAlamat1());
                hpb.setAlamat2(hpk.getAlamat2());
                hpb.setAlamat3(hpk.getAlamat3());
                hpb.setAlamat4(hpk.getAlamat4());
                hpb.setPoskod(hpk.getPoskod());
                hpb.setNegeri(hpk.getNegeri());
                hpb.setWargaNegara(hpk.getWargaNegara());
                hpb.setAlamatSurat(hpk.getAlamatSurat());
                hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
                //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

                hpb.setInfoAudit(ia);
                hpkService.save(hpb);
            }

        }

        if (debug) {
            LOG.debug("start to create hakmilik urusan");
        }

        if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
            List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
            for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                    continue;
                }
                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                hakmilikUrusanBaru.setAktif(hu.getAktif());
                hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                KodDaerah kd = hu.getDaerah();
                if (kd == null) {
                    kd = hu.getHakmilik().getDaerah();
                }
                hakmilikUrusanBaru.setDaerah(kd);
                hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                hakmilikUrusanBaru.setInfoAudit(ia);
                hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

            }
        }


        return hakmilikBaru;
    }

    public Hakmilik returnJanaHakmilikBaruPenyatuanFromHakmilikTerlibat(List<Hakmilik> senaraiHakmilik, Hakmilik hakmilikBaru, InfoAudit ia, Permohonan p, Pengguna pengguna) {
        LOG.info("janahakmilikbarufromhakmilikterlibat:::::::::::::");
        HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
        String kodNegeri = conf.getProperty("kodNegeri");
        String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
        System.out.println("-->Id Hak : " + idHakmilikBaru);
        String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
        KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
        hakmilikBaru.setIdHakmilik(idHakmilikBaru);
        //hakmilikBaru.setNoFail(idFail);
        hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
        hakmilikBaru.setNoHakmilik(noHakmilik);
        //newly added
        hakmilikBaru.setInfoAudit(ia);
        hakmilikBaru.setKodSumber("MI");
        mohonHakmilikBaru = new HakmilikPermohonan();
        mohonHakmilikBaru.setHakmilik(hakmilikBaru);
        mohonHakmilikBaru.setPermohonan(p);
        //
        mohonHakmilikBaru.setSekatanKepentingan(senaraiHakmilik.get(0).getSekatanKepentingan());
        mohonHakmilikBaru.setSyaratNyata(senaraiHakmilik.get(0).getSyaratNyata());
        mohonHakmilikBaru.setKodKegunaanTanah(senaraiHakmilik.get(0).getKegunaanTanah());
        mohonHakmilikBaru.setLuasTerlibat(totalLuas(senaraiHakmilik));
        mohonHakmilikBaru.setKodUnitLuas(senaraiHakmilik.get(0).getKodUnitLuas());
        //mohonHakmilikBaru.setLot(hakmilik.getLot());
        //mohonHakmilikBaru.setNoLot(hakmilik.getNoLot());
        mohonHakmilikBaru.setJenisPenggunaanTanah(senaraiHakmilik.get(0).getKategoriTanah());
        mohonHakmilikBaru.setKodKegunaanTanah(senaraiHakmilik.get(0).getKegunaanTanah());
        //
        mohonHakmilikBaru.setInfoAudit(ia);


        LOG.debug("idHakmilikBaru---" + idHakmilikBaru);
        LOG.debug("idHakmilikBaru_kodBpm---" + hakmilikBaru.getBandarPekanMukim().getKod());
        LOG.debug("idHakmilikBaru_kodLot---" + hakmilikBaru.getLot().getKod());
        LOG.debug("idHakmilikBaru_NoLot---" + hakmilikBaru.getNoLot());

        hakmilikDAO.save(hakmilikBaru);
        hakmilikPermohonanDAO.save(mohonHakmilikBaru);

        for (int ix = 0; ix < senaraiHakmilik.size(); ix++) {
            Hakmilik hm = senaraiHakmilik.get(ix);

//         if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
            LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
            LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
            LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

            if (hm.getSenaraiHakmilikAsal().size() > 0) {
                LOG.debug("start copy hakmilik asal hakmilik lama ke hakmilik asal hakmilik baru");
                for (int a = 0; a < hm.getSenaraiHakmilikAsal().size(); a++) {
                    HakmilikAsal hma1 = (HakmilikAsal) hm.getSenaraiHakmilikAsal().get(a);
                    HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                    hap.setHakmilikPermohonan(mohonHakmilikBaru);
                    //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                    hap.setHakmilik(hakmilikBaru);
                    hap.setHakmilikSejarah(hma1.getHakmilikAsal());
                    hap.setInfoAudit(ia);
                    hakmilikAsalPermohonanDAO.save(hap);
                }

            } else if (hm.getSenaraiHakmilikAsal().size() == 0) {
                HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                hap.setHakmilikPermohonan(mohonHakmilikBaru);
                //hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                hap.setHakmilik(hakmilikBaru);
                hap.setHakmilikSejarah(hm.getIdHakmilik());
                hap.setInfoAudit(ia);
                hakmilikAsalPermohonanDAO.save(hap);
            }
            if (hm.getSenaraiHakmilikAsal().size() != 0) {
                LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                hsp.setPermohonan(p);
                hsp.setCawangan(pengguna.getKodCawangan());
                hsp.setHakmilik(hakmilikBaru);
                hsp.setHakmilikSejarah(hm.getIdHakmilik());
                hsp.setInfoAudit(ia);
                hakmilikSblmPermohonanDAO.save(hsp);
            }

            if (debug) {
                LOG.debug("start to create hakmilik urusan");
            }

            List<HakmilikUrusan> senaraiHakmilikurusan = hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
            for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                    continue;
                }
                HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                hakmilikUrusanBaru.setHakmilik(hakmilikBaru);
                hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                hakmilikUrusanBaru.setAktif(hu.getAktif());
                hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                KodDaerah kd = hu.getDaerah();
                if (kd == null) {
                    kd = hu.getHakmilik().getDaerah();
                }
                hakmilikUrusanBaru.setDaerah(kd);
                hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                hakmilikUrusanBaru.setInfoAudit(ia);
                hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

            }


        }





        if (debug) {
            LOG.debug("start to create hakmilik pihak");
        }
        Hakmilik tuanPunyaHm = senaraiHakmilik.get(0); // because pytn tuanpunya mesti sama

        List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(tuanPunyaHm);

//        for (int i = 0; i < senaraiHakmilikPihak.size(); i++) {
//            HakmilikPihakBerkepentingan hpb = (HakmilikPihakBerkepentingan) senaraiHakmilikPihak.get(i);
//            if (hpb.getNoPengenalan() == null) {
//                if (hpb.getPihak().getNoPengenalan() != null) {
//                    hpb.setNoPengenalan(hpb.getPihak().getNoPengenalan());
//                }
//            }
//        }

        for (HakmilikPihakBerkepentingan hpk : senaraiHakmilikPihak) {
            if (hpk == null || hpk.getAktif() == 'T') {
                continue;
            }
            HakmilikPihakBerkepentingan hpb = new HakmilikPihakBerkepentingan();
            hpb.setHakmilik(hakmilikBaru);
            hpb.setCawangan(hakmilikBaru.getCawangan());
            hpb.setPihakCawangan(hpk.getPihakCawangan());
            hpb.setJenis(hpk.getJenis());
            hpb.setSyerPembilang(hpk.getSyerPembilang());
            hpb.setSyerPenyebut(hpk.getSyerPenyebut());
            hpb.setJumlahPembilang(hpk.getJumlahPembilang());
            hpb.setJumlahPenyebut(hpk.getJumlahPenyebut());
            hpb.setPerserahan(hpk.getPerserahan());
            hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
            hpb.setKaveatAktif(hpk.getKaveatAktif());
            hpb.setAktif(hpk.getAktif());
            hpb.setPihak(hpk.getPihak());

            hpb.setNama(hpk.getNama());
            hpb.setJenisPengenalan(hpk.getJenisPengenalan());
            hpb.setNoPengenalan(hpk.getNoPengenalan());
            hpb.setAlamat1(hpk.getAlamat1());
            hpb.setAlamat2(hpk.getAlamat2());
            hpb.setAlamat3(hpk.getAlamat3());
            hpb.setAlamat4(hpk.getAlamat4());
            hpb.setPoskod(hpk.getPoskod());
            hpb.setNegeri(hpk.getNegeri());
            hpb.setWargaNegara(hpk.getWargaNegara());
            hpb.setAlamatSurat(hpk.getAlamatSurat());
            hpb.setPenubuhanSyarikat(hpk.getPenubuhanSyarikat());
            //hpb.setSenaraiWaris(hpk.getSenaraiWaris());

            hpb.setInfoAudit(ia);
            hpkService.save(hpb);
        }


        return hakmilikBaru;

    }

    private BigDecimal totalLuas(List<Hakmilik> senaraiHakmilik) {
        BigDecimal total = BigDecimal.ZERO;
        for (Hakmilik hm : senaraiHakmilik) {
            total = total.add(hm.getLuas());
        }
        return total;
    }
}
