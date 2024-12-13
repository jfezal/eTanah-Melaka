/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.stripes.pelupusan.initiateService;

import com.google.inject.Inject;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodRujukanDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Alamat;
import etanah.model.AlamatSurat;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.Pemohon;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.SejarahHakmilik;
import etanah.model.TanahRizabPermohonan;
import etanah.service.PelupusanService;
import etanah.service.RegService;
import etanah.service.StrataPtService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import etanah.service.common.PermohonanPihakService;
import etanah.service.common.PihakService;
import etanah.view.stripes.pelupusan.disClass.DisHakmilikPermohonan;
import etanah.view.stripes.pelupusan.disClass.DisLaporanTanahService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author khairil
 */
public class MohonHakmilikPelupusanService {

    @Inject
    public HakmilikDAO hakmilikDAO;
    @Inject
    RegService regService;
    @Inject
    PihakService pihakService;
    @Inject
    PermohonanPihakService permohonanPihakService;
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
    @Inject
    DisLaporanTanahService disLaporanTanahService;
    @Inject
    PelupusanService pelupusanService;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    StrataPtService strService;
    @Inject
    KodRujukanDAO kodRujukanDAO;
    public String idFail;
    public String namaBPM;
    public String kodNegeri;
    public ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private ArrayList<HakmilikPihakBerkepentingan> listHakmilikPihakBerkepentinganBaru = new ArrayList<HakmilikPihakBerkepentingan>();
    private ArrayList<PermohonanPihak> listPermohonanPihak = new ArrayList<PermohonanPihak>();
    private ArrayList<HakmilikAsalPermohonan> listHakmilikAsalPermohonan = new ArrayList<HakmilikAsalPermohonan>();
    private ArrayList<HakmilikSebelumPermohonan> listHakmilikSebelumPermohonan = new ArrayList<HakmilikSebelumPermohonan>();
    private static final Logger LOG = Logger.getLogger(MohonHakmilikPelupusanService.class);
    private static final boolean debug = LOG.isDebugEnabled();

//    URUSAN MH TERLIBAT ----->
//    HKPS,HSPS,HKPB,HSPB,HKBTK,HSBTK,HSPTK,HKABS,HKABT,HKGHS,
//    HKP,HKPTK,HKSB,HKSHS,HKSTK,HKTK,HKTKP,HMSC,HSP,HSSB,HSSHS,HSSTA,
//    HSTKP,HSSBB,HKSBB,HSSA,HKSA,HSSTA,HKSTA,HSSAA,HMSTP
//    Parameter needed HakmilikPermohonan (hakmilik yang asal),Hakmilik (hakmilik baru),InfoAudit (audit),Permohonan (initiated registration permohonan),Pengguna (user),totalHakmilik (total Hakmilik to generate),KodBPM (for tukar kawasan only)
    public void janaHakmilikBaruFromHakmilikTerlibat(List<HakmilikPermohonan> hakmilikPermohonan, Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik, String kodBPM, String[] luasBaru) {

        Hakmilik hakmilikBaru = null;
        HakmilikPermohonan mohonHakmilikBaru = null;
        long t = System.currentTimeMillis();
        List<HakmilikPermohonan> hp2 = hakmilikPermohonan;

        if (hakmilikPermohonan.size() > 0) {
            if (totalHakmilik > 0) {
                for (int i = 0; i < totalHakmilik; i++) {

                    HakmilikPermohonan hp = hakmilikPermohonan.get(0);
                    hakmilikBaru = new Hakmilik();
                    Hakmilik hakmilikTerpilih = hp.getHakmilik();
                    String id = hakmilikTerpilih.getIdHakmilik();
                    hakmilikTerpilih = hakmilikDAO.findById(id);

                    hakmilikBaru.setDaerah(hakmilikTerpilih.getDaerah());
                    //hakmilikTerpilih.setKodHakmilik(hakmilik.getKodHakmilik());
                    if (hakmilik.getKodHakmilik() != null) {
                        hakmilikBaru.setKodHakmilik(hakmilik.getKodHakmilik());
                    } else {
                        hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
                    }

                    if (StringUtils.isNotBlank(kodBPM)) {
                        LOG.debug("tukar bpm :+" + kodBPM);
                        KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                        kbpm = regService.cariBPM(kodBPM, hakmilikTerpilih.getDaerah().getKod());
                        //hakmilik.setBandarPekanMukim(kbpm);
                        hakmilikBaru.setBandarPekanMukim(kbpm);
                    } else {
                        hakmilikBaru.setBandarPekanMukim(hakmilikTerpilih.getBandarPekanMukim());
                    }
                    LOG.debug("kod pekan " + hakmilikTerpilih.getBandarPekanMukim().getKod());
                    String kodNegeri = conf.getProperty("kodNegeri");
                    String idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikBaru);
                    String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                    KodStatusHakmilik kodStatusHakmilik = kodStatusHakmilikDAO.findById("T");
                    hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                    hakmilikBaru.setNoFail(idFail);
                    hakmilikBaru.setKodStatusHakmilik(kodStatusHakmilik);
                    hakmilikBaru.setNoHakmilik(noHakmilik);
                    hakmilikBaru.setCawangan(hakmilikTerpilih.getCawangan());
                    hakmilikBaru.setSeksyen(hakmilikTerpilih.getSeksyen());
                    hakmilikBaru.setLokasi(hakmilikTerpilih.getLokasi());
//                    hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                    hakmilikBaru.setNoLitho(hakmilikTerpilih.getNoLitho());
//                    hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                    hakmilikBaru.setSekatanKepentingan(hakmilikTerpilih.getSekatanKepentingan());
                    hakmilikBaru.setSyaratNyata(hakmilikTerpilih.getSyaratNyata());
                    hakmilikBaru.setPbt(hakmilikTerpilih.getPbt());
//                    hakmilikBaru.setKodHakmilik(hakmilikTerpilih.getKodHakmilik());
                    //hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                    KodLot kl = new KodLot();
                    if (hakmilikTerpilih.getKodHakmilik().getKod().equals("PN") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GRN")
                            || hakmilikTerpilih.getKodHakmilik().getKod().equals("PM") || hakmilikTerpilih.getKodHakmilik().getKod().equals("GMM")) {
                        kl.setKod("1");
                    } else {
                        kl.setKod("2");
                    }
                    hakmilikBaru.setLot(kl);
                    if (p.getKodUrusan().getKod().equals("HKBTK") || p.getKodUrusan().getKod().equals("HKCTK")
                            || p.getKodUrusan().getKod().equals("HKPTK") || p.getKodUrusan().getKod().equals("HKSTK")
                            || p.getKodUrusan().getKod().equals("HKTK") || p.getKodUrusan().getKod().equals("HSBTK")
                            || p.getKodUrusan().getKod().equals("HSBTK") || p.getKodUrusan().getKod().equals("HSCTK")
                            || p.getKodUrusan().getKod().equals("HSPTK") || p.getKodUrusan().getKod().equals("HSTK")
                            || p.getKodUrusan().getKod().equals("HKSHS") || p.getKodUrusan().getKod().equals("HSSHS")
                            || p.getKodUrusan().getKod().equals("HKP") || p.getKodUrusan().getKod().equals("HSP")
                            || p.getKodUrusan().getKod().equals("HSSAA")) {

                        hakmilikBaru.setLot(hakmilikTerpilih.getLot());
                        hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                        hakmilikBaru.setCukai(hakmilikTerpilih.getCukai());
                        hakmilikBaru.setLuas(hakmilikTerpilih.getLuas());
                        hakmilikBaru.setNoPelan(hakmilikTerpilih.getNoPelan());
                        hakmilikBaru.setNoPu(hakmilikTerpilih.getNoPu());
                    } else {
                        if (luasBaru.length > 0) {
                            //BigDecimal totalLuas =
                            BigDecimal totalLuas = new BigDecimal(luasBaru[i]);
                            hakmilikBaru.setLuas(totalLuas);
                        } else {
                            BigDecimal totalLuas = new BigDecimal(0);
                            hakmilikBaru.setLuas(totalLuas);
                        }
                    }
                    //hakmilikBaru.setLot(hakmilikTerpilih.getLot());
//                    hakmilikBaru.setNoLot(hakmilikTerpilih.getNoLot());
                    hakmilikBaru.setKategoriTanah(hakmilikTerpilih.getKategoriTanah());
                    hakmilikBaru.setKegunaanTanah(hakmilikTerpilih.getKegunaanTanah());
                    if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() > 0) {
                        LOG.debug("::start copy tarikh daftar asal dari hakmilik asal");
                        Hakmilik asal = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());                       
                        if (asal != null) {
                            hakmilikBaru.setTarikhDaftarAsal(asal.getTarikhDaftar());
                            if (asal.getTarikhLuput() != null) {
                                hakmilikBaru.setTarikhLuput(asal.getTarikhLuput());
                            }
                        }
                    } else if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() < 0 && hakmilikTerpilih.getSenaraiHakmilikSebelum().size() > 0) {
                        LOG.debug("::start copy tarikh daftar asal dari hakmilik sebelum");
                        Hakmilik sblm = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                        hakmilikBaru.setTarikhDaftarAsal(sblm.getTarikhDaftar());
                        if (sblm.getTarikhLuput() != null) {
                            hakmilikBaru.setTarikhLuput(sblm.getTarikhLuput());
                        }

                    } else {
                        LOG.debug("::start copy tarikh daftar asal dari mohon hakmilik sebelum");
                        if (hakmilikTerpilih.getTarikhLuput() != null) {
                            hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftarAsal());
                            hakmilikBaru.setTarikhLuput(hakmilikTerpilih.getTarikhLuput());
                        }
                        if (hakmilikTerpilih.getTarikhDaftarAsal() == null) {
                            hakmilikBaru.setTarikhDaftarAsal(hakmilikTerpilih.getTarikhDaftar());
                        }
                    }

                    hakmilikBaru.setTempohPegangan(hakmilikTerpilih.getTempohPegangan());
                    hakmilikBaru.setPegangan(hakmilikTerpilih.getPegangan());
                    hakmilikBaru.setLotBumiputera(hakmilikTerpilih.getLotBumiputera());
                    hakmilikBaru.setRizab(hakmilikTerpilih.getRizab());
                    hakmilikBaru.setRizabNoWarta(hakmilikTerpilih.getRizabNoWarta());
                    hakmilikBaru.setRizabTarikhWarta(hakmilikTerpilih.getRizabTarikhWarta());

                    hakmilikBaru.setGsaNoWarta(hakmilikTerpilih.getGsaNoWarta());
                    hakmilikBaru.setGsaKawasan(hakmilikTerpilih.getGsaKawasan());
                    hakmilikBaru.setGsaTarikhWarta(hakmilikTerpilih.getGsaTarikhWarta());

                    hakmilikBaru.setPbtKawasan(hakmilikTerpilih.getPbtKawasan());
                    hakmilikBaru.setPbtNoWarta(hakmilikTerpilih.getPbtNoWarta());
                    hakmilikBaru.setPbtTarikhWarta(hakmilikTerpilih.getPbtTarikhWarta());

                    hakmilikBaru.setMilikPersekutuan(hakmilikTerpilih.getMilikPersekutuan());
                    hakmilikBaru.setKodUnitLuas(hakmilikTerpilih.getKodUnitLuas());

                    if (debug) {
                        LOG.debug("start to divide luas");
                    }
                    hakmilikBaru.setInfoAudit(ia);

                    if (debug) {
                        LOG.debug("start to create new Hakmilik Permohonanan.");
                    }


                    mohonHakmilikBaru = new HakmilikPermohonan();
                    mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                    mohonHakmilikBaru.setPermohonan(p);
                    /*TODO:SAVE LUAS TERLIBAT FROM MOHON HAKMILIK LAMA UTK HKABS*/
                    List<HakmilikPermohonan> temphp = new ArrayList();
                    if (p.getKodUrusan().getKod().equals("HKSB") || p.getKodUrusan().getKod().equals("HSSB")
                            || p.getKodUrusan().getKod().equals("HKABS") || p.getKodUrusan().getKod().equals("HKABT")) {
                        LOG.debug("SET LUAS TERLIBAT DARI NOTTING HKSB");
                        List<HakmilikPermohonan> senaraiHP = regService.searchMohonHakmilik(hakmilikTerpilih.getIdHakmilik());
                        for (HakmilikPermohonan hakmilikPermohonan1 : senaraiHP) {
                            LOG.debug("LIST URUSAN UNTUK HAKMILIK : " + hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod());
                            if (hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("SBSTL")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABT-K")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTKB")
                                    || hakmilikPermohonan1.getPermohonan().getKodUrusan().getKod().equals("ABTBH")) {
                                //temphp.add(hakmilikPermohonan1);
                                mohonHakmilikBaru.setLuasTerlibat(hakmilikPermohonan1.getLuasTerlibat());
                                mohonHakmilikBaru.setKodUnitLuas(hakmilikPermohonan1.getKodUnitLuas());
                                mohonHakmilikBaru.setCukaiBaru(hakmilikPermohonan1.getCukaiBaru());
                            }
                        }
                    }

                    //LOG.debug("temphp size : "+temphp.size());
                    //LOG.debug("LUAS TERLIBAT : "+temphp.get(0).getLuasTerlibat());
                    //if (temphp.size() > 0) {
                    //     mohonHakmilikBaru.setLuasTerlibat(temphp.get(0).getLuasTerlibat());
                    // }
                    mohonHakmilikBaru.setInfoAudit(ia);

                    hakmilikDAO.save(hakmilikBaru);
                    hakmilikPermohonanDAO.save(mohonHakmilikBaru);

                    Hakmilik hm = hp.getHakmilik();
                    hm = hakmilikDAO.findById(id);


//                    if (!p.getKodUrusan().getKod().equals("HSSTB") && !p.getKodUrusan().getKod().equals("HKSTB")) {
                    LOG.debug("start copy hakmilik asal untuk urusan selain hsstb / hkstb");
                    LOG.debug("size senaraiHakmilikAsal hakmilikasal " + hm.getSenaraiHakmilikAsal().size());
                    LOG.debug("size senaraiHakmilikSebelum hakmiliksebelum " + hm.getSenaraiHakmilikSebelum().size());

                    if (hm.getSenaraiHakmilikAsal().size() > 0) {
                        LOG.debug("start copy hakmilik asal hakmilik lama ke hakmilik asal hakmilik baru");
                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                        hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal()));
                        hap.setHakmilikSejarah(hm.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                        hap.setInfoAudit(ia);
                        hakmilikAsalPermohonanDAO.save(hap);
                    }
                    if (hm.getSenaraiHakmilikSebelum().size() > 0) {
                        LOG.debug("start copy hakmilik sebelum hakmilik lama ke hakmilik sebelum hakmilik baru");
                        HakmilikAsalPermohonan hap = new HakmilikAsalPermohonan();
                        hap.setHakmilikPermohonan(mohonHakmilikBaru);
                        hap.setHakmilik(hakmilikDAO.findById(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum()));
                        hap.setHakmilikSejarah(hm.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                        hap.setInfoAudit(ia);
                        hakmilikAsalPermohonanDAO.save(hap);
                    }

                    HakmilikSebelumPermohonan hsp = new HakmilikSebelumPermohonan();
                    hsp.setHakmilikPermohonan(mohonHakmilikBaru);
                    hsp.setPermohonan(p);
                    hsp.setCawangan(pengguna.getKodCawangan());
                    hsp.setHakmilik(hm);
//                    hsp.setHakmilikSejarah(hm.getIdHakmilik());
                    hsp.setInfoAudit(ia);
                    hakmilikSblmPermohonanDAO.save(hsp);
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
                        hpb.setPerserahan(hpk.getPerserahan());
                        hpb.setPerserahanKaveat(hpk.getPerserahanKaveat());
                        hpb.setKaveatAktif(hpk.getKaveatAktif());
                        hpb.setAktif(hpk.getAktif());
                        hpb.setPihak(hpk.getPihak());
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
            }

        }
    }
//    URUSAN MH TERLIBAT -->
//    HSBM,HKBM,HKGHS

//    public static void main(String[] args) {
//        String[] luasBaru = {"0.4","0.1","0.2"};
//        for(int i = 0; i < luasBaru.length; i++) {
//            BigDecimal totalLuas = new BigDecimal(luasBaru[i]);
//            System.out.println("totalLuas :"+totalLuas);
//        }
//    }
    public void janaHakmilikBaru(Hakmilik hakmilikLama, InfoAudit ia, Permohonan p, Pengguna pengguna, String idPermohonan, String idMohonHakmilik, int totalHakmilik) {
        kodNegeri = conf.getProperty("kodNegeri");
        listHakmilikBaru = new ArrayList<Hakmilik>();
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilikLama.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilikLama.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikLama);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                hakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToHakmilik(p, hakmilikLama, hakmilikBaru, new String[]{}, disLaporanTanahService);
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                idFail = hakmilikLama.getNoFail();
                hakmilikBaru.setNoFail(idFail);
                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setNoVersiDhde(new Integer(0));
                hakmilikBaru.setNoVersiDhke(new Integer(0));
                hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho() != null ? hakmilikLama.getNoLitho() : null);
                //If FT No Pelan
                hakmilikBaru.setNoPelan(hakmilikLama.getNoPelan() != null ? hakmilikLama.getNoPelan() : null);
                //If QT No PU
                hakmilikBaru.setNoPu(hakmilikLama.getNoPu() != null ? hakmilikLama.getNoPu() : null);

//                hakmilikBaru.setBandarPekanMukim(kbpm);
//                hakmilikBaru.setSeksyen(null);
//                KodHakmilik kodHakmilik = new KodHakmilik();
//                kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
//                hakmilikBaru.setKodHakmilik(kodHakmilik);
//                KodLot kl = new KodLot();
//                kl.setKod("1");
//                hakmilikBaru.setLot(kl);
//                //hakmilikBaru.setNoLot("0");
//                KodKategoriTanah kkt = new KodKategoriTanah();
//                kkt.setKod("1");
//                hakmilikBaru.setKategoriTanah(kkt);
////                KodKegunaanTanah kgt = new KodKegunaanTanah();
////                kgt.setKod("BO1");
////                kgt.setKategoriTanah(kkt);
//                String kodgunaguna = "B01";
//                hakmilikBaru.setKegunaanTanah(regService.cariKegunaanTanah(kodgunaguna, kkt.getKod()));
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
//                KodUOM kuo = new KodUOM();
//                kuo.setKod("M");
//                hakmilikBaru.setKodUnitLuas(kuo);
//                hakmilikBaru.setLuas(BigDecimal.ZERO);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            hakmilikBaru.setNoLot(hp.getNoLot());
                            hakmilikBaru.setLot(hp.getLot());
                        }
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikLama.getTarikhDaftarAsal() != null ? hakmilikLama.getTarikhDaftarAsal() : null);
                    }
                }
                /*AUDIT INFO*/

//                InfoAudit info = pengguna.getInfoAudit();
//                info.setDimasukOleh(pengguna);
//                info.setTarikhMasuk(new java.util.Date());
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(p, mohonHakmilikBaru, hakmilikLama, new String[]{}, disLaporanTanahService);
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            mohonHakmilikBaru.setNoLot(hp.getNoLot());
                            mohonHakmilikBaru.setLot(hp.getLot());
                        }
                    }
                }
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);
                if (hakmilikLama != null) {
                    LOG.debug("id hakmilik lama--" + hakmilikLama.getIdHakmilik());
                }
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        LOG.debug("creating mohon hakmilik asal dan sebelum (daerah)");
                        List<HakmilikAsal> listHakmilikAsalHakmilikLama = new ArrayList<HakmilikAsal>();
                        listHakmilikAsalHakmilikLama = regService.cariHakmilikAsal(hakmilikLama.getIdHakmilik());
                        if (!listHakmilikAsalHakmilikLama.isEmpty()) {
                            HakmilikAsal ha = new HakmilikAsal();
                            ha = listHakmilikAsalHakmilikLama.get(0);
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(ha.getHakmilikAsal());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }

                            HakmilikSebelumPermohonan hakmilikSebelumPermohonan = new HakmilikSebelumPermohonan();
                            hakmilikSebelumPermohonan.setCawangan(pengguna.getKodCawangan());
                            hakmilikSebelumPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
//                            hakmilikSebelumPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                            hakmilikSebelumPermohonan.setHakmilik(hakmilikBaru);
                            hakmilikSebelumPermohonan.setPermohonan(p);
                            hakmilikSebelumPermohonan.setInfoAudit(ia);
                            listHakmilikSebelumPermohonan.add(hakmilikSebelumPermohonan);

                        } else {
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }
                        }
                    }
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikLama);

                if (!senaraiHakmilikPihak.isEmpty()) {
                    for (HakmilikPihakBerkepentingan hpb : senaraiHakmilikPihak) {
                        HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                        hpBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        hpBaru.setHakmilik(hakmilikBaru);
                        hpBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        hpBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : null);
                        hpBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : null);
                        hpBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : null);
                        hpBaru.setJumlahPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : null);
                        hpBaru.setJumlahPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : null);
                        hpBaru.setAktif('Y');
                        hpBaru.setKaveatAktif('T');
                        hpBaru.setNoSalinan(0);
                        hpBaru.setInfoAudit(ia);
                        listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                        LOG.debug("--add hakmilik pihak--");

                        PermohonanPihak pihakBaru = new PermohonanPihak();

                        pihakBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        pihakBaru.setHakmilik(hakmilikBaru);
                        pihakBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        pihakBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                        pihakBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : 1);
                        pihakBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : 1);
                        pihakBaru.setInfoAudit(ia);
                        pihakBaru.setPermohonan(p);
                        pihakBaru.setNama(hpb.getPihak().getNama());
                        pihakBaru.setJenisPengenalan(hpb.getPihak().getJenisPengenalan());
                        pihakBaru.setNoPengenalan(hpb.getPihak().getNoPengenalan());
                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(hpb.getPihak().getAlamat1());
                        alamat.setAlamat2(hpb.getPihak().getAlamat2());
                        alamat.setAlamat3(hpb.getPihak().getAlamat3());
                        alamat.setAlamat4(hpb.getPihak().getAlamat4());
                        alamat.setPoskod(hpb.getPihak().getPoskod());
                        alamat.setNegeri(hpb.getPihak().getNegeri());
                        pihakBaru.setAlamat(alamat);
                        pihakBaru.setWargaNegara(hpb.getPihak().getWargaNegara() != null ? hpb.getPihak().getWargaNegara() : null);
                        listPermohonanPihak.add(pihakBaru);
                        LOG.debug("--add mohon pihak daripada pemohon baru--");
                    }
                } else {
                    List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        LOG.debug("id permohonan--" + idPermohonan);
                        senaraiPemohon = disLaporanTanahService.getPelupusanService().findPemohonByIdPermohonanOnly(idPermohonan);
                    }
                    if (senaraiPemohon.size() > 0) {
                        for (Pemohon pemohon : senaraiPemohon) {
                            PermohonanPihak pihakBaru = new PermohonanPihak();

                            pihakBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            pihakBaru.setHakmilik(hakmilikBaru);
                            pihakBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            pihakBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            pihakBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            pihakBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            pihakBaru.setInfoAudit(ia);
                            pihakBaru.setPermohonan(p);
                            pihakBaru.setNama(pemohon.getPihak().getNama());
                            pihakBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            pihakBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                            alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                            alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                            alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                            alamat.setPoskod(pemohon.getPihak().getPoskod());
                            alamat.setNegeri(pemohon.getPihak().getNegeri());
                            pihakBaru.setAlamat(alamat);
                            pihakBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            pihakBaru.setAlamatSurat(alamatSurat);
                            listPermohonanPihak.add(pihakBaru);
                            LOG.debug("--add mohon pihak daripada pemohon baru--");

                            /*HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            hpBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            hpBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            hpBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pemohon.getPihak().getNama());
                            hpBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            hpBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            hpBaru.setAlamat1(pemohon.getPihak().getAlamat1());
                            hpBaru.setAlamat2(pemohon.getPihak().getAlamat2());
                            hpBaru.setAlamat3(pemohon.getPihak().getAlamat3());
                            hpBaru.setAlamat4(pemohon.getPihak().getAlamat4());
                            hpBaru.setPoskod(pemohon.getPihak().getPoskod());
                            hpBaru.setNegeri(pemohon.getPihak().getNegeri());
                            hpBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            hpBaru.setAlamatSurat(alamatSurat);
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada pemohon baru--");*/
                            
                            HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pihakBaru.getCawangan());
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pihakBaru.getPihak());
                            hpBaru.setJenis(pihakBaru.getJenis());
                            hpBaru.setSyerPembilang(pihakBaru.getSyerPembilang());
                            hpBaru.setSyerPenyebut(pihakBaru.getSyerPenyebut());
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pihakBaru.getNama());
                            hpBaru.setJenisPengenalan(pihakBaru.getJenisPengenalan());
                            hpBaru.setNoPengenalan(pihakBaru.getNoPengenalan());
                            hpBaru.setAlamat1(pihakBaru.getAlamat().getAlamat1());
                            hpBaru.setAlamat2(pihakBaru.getAlamat().getAlamat2());
                            hpBaru.setAlamat3(pihakBaru.getAlamat().getAlamat3());
                            hpBaru.setAlamat4(pihakBaru.getAlamat().getAlamat4());
                            hpBaru.setPoskod(pihakBaru.getAlamat().getPoskod());
                            hpBaru.setNegeri(pihakBaru.getAlamat().getNegeri());
                            hpBaru.setWargaNegara(pihakBaru.getWargaNegara());                            
                            hpBaru.setAlamatSurat(pihakBaru.getAlamatSurat());
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada mohon pihak baru--");
                            
                            
                            
                            
                        }
                    }
                }

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
            if ((StringUtils.isNotBlank(idMohonHakmilik)) && (StringUtils.isNotBlank(idPermohonan))) {
                LOG.debug("id untuk update mohon hakmilik utk disposal--" + idMohonHakmilik);
                updateMohonHakmilikPelupusan(idPermohonan, idMohonHakmilik, listHakmilikBaru.get(0));
                updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), "FL");
            }
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }
        if (!listHakmilikPihakBerkepentinganBaru.isEmpty()) {
            regService.simpanHakmilikPihak(listHakmilikPihakBerkepentinganBaru);
            LOG.debug("--saving hakmilik pihak--");
        }
        if (!listPermohonanPihak.isEmpty()) {
            permohonanPihakService.saveOrUpdate(listPermohonanPihak);
            LOG.debug("--saving mohon pihak--");
        }
        if (!listHakmilikAsalPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikAsal(listHakmilikAsalPermohonan);
            LOG.debug("--saving hakmilik asal--");
        }
        if (!listHakmilikSebelumPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikSebelum(listHakmilikSebelumPermohonan);
            LOG.debug("--saving hakmilik sebelum--");
        }

    }
    
//    added by Syazwan 18/3/2014
    public void janaHakmilikBaru1(Hakmilik hakmilikLama, InfoAudit ia, Permohonan p, Pengguna pengguna, String idPermohonan, String idMohonHakmilik, int totalHakmilik, TanahRizabPermohonan trizab) {
        kodNegeri = conf.getProperty("kodNegeri");
        listHakmilikBaru = new ArrayList<Hakmilik>();
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                TanahRizabPermohonan bpmnama = new TanahRizabPermohonan();                //added by Syazwan 17/3/2014
                trizab.setNoWarta(trizab.getNoWarta());
                trizab.setTarikhWarta(trizab.getTarikhWarta());
                LOG.info("NO Warta: " + trizab.getNoWarta());
                LOG.info("Tarikh Warta: " + trizab.getTarikhWarta());
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilikLama.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilikLama.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikLama);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                hakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToHakmilik(p, hakmilikLama, hakmilikBaru, new String[]{}, disLaporanTanahService);
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                idFail = hakmilikLama.getNoFail();
                hakmilikBaru.setNoFail(idFail);
                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setNoVersiDhde(new Integer(1));
                hakmilikBaru.setNoVersiDhke(new Integer(1));
                hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho() != null ? hakmilikLama.getNoLitho() : null);
                //If FT No Pelan
                hakmilikBaru.setNoPelan(hakmilikLama.getNoPelan() != null ? hakmilikLama.getNoPelan() : null);
                //If QT No PU
                hakmilikBaru.setNoPu(hakmilikLama.getNoPu() != null ? hakmilikLama.getNoPu() : null);

//                hakmilikBaru.setBandarPekanMukim(kbpm);
//                hakmilikBaru.setSeksyen(null);
//                KodHakmilik kodHakmilik = new KodHakmilik();
//                kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
//                hakmilikBaru.setKodHakmilik(kodHakmilik);
//                KodLot kl = new KodLot();
//                kl.setKod("1");
//                hakmilikBaru.setLot(kl);
//                //hakmilikBaru.setNoLot("0");
//                KodKategoriTanah kkt = new KodKategoriTanah();
//                kkt.setKod("1");
//                hakmilikBaru.setKategoriTanah(kkt);
////                KodKegunaanTanah kgt = new KodKegunaanTanah();
////                kgt.setKod("BO1");
////                kgt.setKategoriTanah(kkt);
//                String kodgunaguna = "B01";
//                hakmilikBaru.setKegunaanTanah(regService.cariKegunaanTanah(kodgunaguna, kkt.getKod()));
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
//                KodUOM kuo = new KodUOM();
//                kuo.setKod("M");
//                hakmilikBaru.setKodUnitLuas(kuo);
//                hakmilikBaru.setLuas(BigDecimal.ZERO);
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            hakmilikBaru.setNoLot(hp.getNoLot());
                            hakmilikBaru.setLot(hp.getLot());
                        }
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikLama.getTarikhDaftarAsal() != null ? hakmilikLama.getTarikhDaftarAsal() : null);
                    }
                }
                /*AUDIT INFO*/

//                InfoAudit info = pengguna.getInfoAudit();
//                info.setDimasukOleh(pengguna);
//                info.setTarikhMasuk(new java.util.Date());
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(p, mohonHakmilikBaru, hakmilikLama, new String[]{}, disLaporanTanahService);
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            mohonHakmilikBaru.setNoLot(hp.getNoLot());
                            mohonHakmilikBaru.setLot(hp.getLot());
                        }
                    }
                }
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);
                if (hakmilikLama != null) {
                    LOG.debug("id hakmilik lama--" + hakmilikLama.getIdHakmilik());
                }
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        LOG.debug("creating mohon hakmilik asal dan sebelum (daerah)");
                        List<HakmilikAsal> listHakmilikAsalHakmilikLama = new ArrayList<HakmilikAsal>();
                        listHakmilikAsalHakmilikLama = regService.cariHakmilikAsal(hakmilikLama.getIdHakmilik());
                        if (!listHakmilikAsalHakmilikLama.isEmpty()) {
                            HakmilikAsal ha = new HakmilikAsal();
                            ha = listHakmilikAsalHakmilikLama.get(0);
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(ha.getHakmilikAsal());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }

                            HakmilikSebelumPermohonan hakmilikSebelumPermohonan = new HakmilikSebelumPermohonan();
                            hakmilikSebelumPermohonan.setCawangan(pengguna.getKodCawangan());
                            hakmilikSebelumPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
//                            hakmilikSebelumPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                            hakmilikSebelumPermohonan.setHakmilik(hakmilikBaru);
                            hakmilikSebelumPermohonan.setPermohonan(p);
                            hakmilikSebelumPermohonan.setInfoAudit(ia);
                            listHakmilikSebelumPermohonan.add(hakmilikSebelumPermohonan);

                        } else {
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }
                        }
                    }
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikLama);

                if (!senaraiHakmilikPihak.isEmpty()) {
                    for (HakmilikPihakBerkepentingan hpb : senaraiHakmilikPihak) {
                        HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                        hpBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        hpBaru.setHakmilik(hakmilikBaru);
                        hpBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        hpBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : null);
                        hpBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : null);
                        hpBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : null);
                        hpBaru.setAktif('Y');
                        hpBaru.setKaveatAktif('T');
                        hpBaru.setNoSalinan(0);
                        hpBaru.setInfoAudit(ia);
                        listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                        LOG.debug("--add hakmilik pihak--");

                        PermohonanPihak pihakBaru = new PermohonanPihak();

                        pihakBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        pihakBaru.setHakmilik(hakmilikBaru);
                        pihakBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        pihakBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                        pihakBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : 1);
                        pihakBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : 1);
                        pihakBaru.setInfoAudit(ia);
                        pihakBaru.setPermohonan(p);
                        pihakBaru.setNama(hpb.getPihak().getNama());
                        pihakBaru.setJenisPengenalan(hpb.getPihak().getJenisPengenalan());
                        pihakBaru.setNoPengenalan(hpb.getPihak().getNoPengenalan());
                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(hpb.getPihak().getAlamat1());
                        alamat.setAlamat2(hpb.getPihak().getAlamat2());
                        alamat.setAlamat3(hpb.getPihak().getAlamat3());
                        alamat.setAlamat4(hpb.getPihak().getAlamat4());
                        alamat.setPoskod(hpb.getPihak().getPoskod());
                        alamat.setNegeri(hpb.getPihak().getNegeri());
                        pihakBaru.setAlamat(alamat);
                        pihakBaru.setWargaNegara(hpb.getPihak().getWargaNegara() != null ? hpb.getPihak().getWargaNegara() : null);
                        listPermohonanPihak.add(pihakBaru);
                        LOG.debug("--add mohon pihak daripada pemohon baru--");
                    }
                } else {
                    List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        LOG.debug("id permohonan--" + idPermohonan);
                        senaraiPemohon = disLaporanTanahService.getPelupusanService().findPemohonByIdPermohonanOnly(idPermohonan);
                    }
                    if (senaraiPemohon.size() > 0) {
                        for (Pemohon pemohon : senaraiPemohon) {
                            PermohonanPihak pihakBaru = new PermohonanPihak();

                            pihakBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            pihakBaru.setHakmilik(hakmilikBaru);
                            pihakBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            pihakBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            pihakBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            pihakBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            pihakBaru.setInfoAudit(ia);
                            pihakBaru.setPermohonan(p);
                            pihakBaru.setNama(pemohon.getPihak().getNama());
                            pihakBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            pihakBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                            alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                            alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                            alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                            alamat.setPoskod(pemohon.getPihak().getPoskod());
                            alamat.setNegeri(pemohon.getPihak().getNegeri());
                            pihakBaru.setAlamat(alamat);
                            pihakBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            pihakBaru.setAlamatSurat(alamatSurat);
                            listPermohonanPihak.add(pihakBaru);
                            LOG.debug("--add mohon pihak daripada pemohon baru--");

                            /*HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            hpBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            hpBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            hpBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pemohon.getPihak().getNama());
                            hpBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            hpBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            hpBaru.setAlamat1(pemohon.getPihak().getAlamat1());
                            hpBaru.setAlamat2(pemohon.getPihak().getAlamat2());
                            hpBaru.setAlamat3(pemohon.getPihak().getAlamat3());
                            hpBaru.setAlamat4(pemohon.getPihak().getAlamat4());
                            hpBaru.setPoskod(pemohon.getPihak().getPoskod());
                            hpBaru.setNegeri(pemohon.getPihak().getNegeri());
                            hpBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            hpBaru.setAlamatSurat(alamatSurat);
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada pemohon baru--");*/
                            
                            HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pihakBaru.getCawangan());
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pihakBaru.getPihak());
                            hpBaru.setJenis(pihakBaru.getJenis());
                            hpBaru.setSyerPembilang(pihakBaru.getSyerPembilang());
                            hpBaru.setSyerPenyebut(pihakBaru.getSyerPenyebut());
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pihakBaru.getNama());
                            hpBaru.setJenisPengenalan(pihakBaru.getJenisPengenalan());
                            hpBaru.setNoPengenalan(pihakBaru.getNoPengenalan());
                            hpBaru.setAlamat1(pihakBaru.getAlamat().getAlamat1());
                            hpBaru.setAlamat2(pihakBaru.getAlamat().getAlamat2());
                            hpBaru.setAlamat3(pihakBaru.getAlamat().getAlamat3());
                            hpBaru.setAlamat4(pihakBaru.getAlamat().getAlamat4());
                            hpBaru.setPoskod(pihakBaru.getAlamat().getPoskod());
                            hpBaru.setNegeri(pihakBaru.getAlamat().getNegeri());
                            hpBaru.setWargaNegara(pihakBaru.getWargaNegara());                            
                            hpBaru.setAlamatSurat(pihakBaru.getAlamatSurat());
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada mohon pihak baru--");
                            
                            
                            
                            
                        }
                    }
                }

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
            if ((StringUtils.isNotBlank(idMohonHakmilik)) && (StringUtils.isNotBlank(idPermohonan))) {
                LOG.debug("id untuk update mohon hakmilik utk disposal--" + idMohonHakmilik);
                updateMohonHakmilikPelupusan(idPermohonan, idMohonHakmilik, listHakmilikBaru.get(0));
                if(p.getKodUrusan().getKod().equals("BMRE") || p.getKodUrusan().getKod().equals("WMRE") || p.getKodUrusan().getKod().equals("MMRE")){
                    updateMohonRujukLuar1(p, ia, listHakmilikBaru.get(0), "FL", trizab);
                }else {
                    updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), "FL");
                }
            }
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }
        if (!listHakmilikPihakBerkepentinganBaru.isEmpty()) {
            regService.simpanHakmilikPihak(listHakmilikPihakBerkepentinganBaru);
            LOG.debug("--saving hakmilik pihak--");
        }
        if (!listPermohonanPihak.isEmpty()) {
            permohonanPihakService.saveOrUpdate(listPermohonanPihak);
            LOG.debug("--saving mohon pihak--");
        }
        if (!listHakmilikAsalPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikAsal(listHakmilikAsalPermohonan);
            LOG.debug("--saving hakmilik asal--");
        }
        if (!listHakmilikSebelumPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikSebelum(listHakmilikSebelumPermohonan);
            LOG.debug("--saving hakmilik sebelum--");
        }

    }

    public void janaHakmilikBaruPendaftar(Hakmilik hakmilikLama, InfoAudit ia, Permohonan p, Pengguna pengguna, String idPermohonan, String idMohonHakmilik, int totalHakmilik) {
        kodNegeri = conf.getProperty("kodNegeri");
        listHakmilikBaru = new ArrayList<Hakmilik>();
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilikLama.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilikLama.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikLama);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);

                Hakmilik hakmilikBaru = new Hakmilik();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                hakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToHakmilik(p, hakmilikLama, hakmilikBaru, new String[]{}, disLaporanTanahService);
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                idFail = hakmilikLama.getNoFail();
                hakmilikBaru.setNoFail(idFail);

                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setNoVersiDhde(new Integer(1));
                hakmilikBaru.setNoVersiDhke(new Integer(1));
                hakmilikBaru.setCawangan(kodCawanganDAO.findById("00"));
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho() != null ? hakmilikLama.getNoLitho() : null);
                //If FT No Pelan
                hakmilikBaru.setNoPelan(hakmilikLama.getNoPelan() != null ? hakmilikLama.getNoPelan() : null);
                //If QT No PU
                hakmilikBaru.setNoPu(hakmilikLama.getNoPu() != null ? hakmilikLama.getNoPu() : null);
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('Y');

                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            hakmilikBaru.setNoLot(hp.getNoLot());
                            hakmilikBaru.setLot(hp.getLot());
                        }
                    }
                }
                /*AUDIT INFO*/

//                InfoAudit info = pengguna.getInfoAudit();
//                info.setDimasukOleh(pengguna);
//                info.setTarikhMasuk(new java.util.Date());
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(p, mohonHakmilikBaru, hakmilikLama, new String[]{}, disLaporanTanahService);
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            mohonHakmilikBaru.setNoLot(hp.getNoLot());
                            mohonHakmilikBaru.setLot(hp.getLot());
                        }
                    }
                }
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);
                if (hakmilikLama != null) {
                    LOG.debug("id hakmilik lama--" + hakmilikLama.getIdHakmilik());
                }
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        LOG.debug("creating mohon hakmilik asal dan sebelum (daerah)");
                        List<HakmilikAsal> listHakmilikAsalHakmilikLama = new ArrayList<HakmilikAsal>();
                        listHakmilikAsalHakmilikLama = regService.cariHakmilikAsal(hakmilikLama.getIdHakmilik());
                        if (!listHakmilikAsalHakmilikLama.isEmpty()) {
                            HakmilikAsal ha = new HakmilikAsal();
                            ha = listHakmilikAsalHakmilikLama.get(0);
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(ha.getHakmilikAsal());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }

                            HakmilikSebelumPermohonan hakmilikSebelumPermohonan = new HakmilikSebelumPermohonan();
                            hakmilikSebelumPermohonan.setCawangan(kodCawanganDAO.findById("00"));
                            hakmilikSebelumPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
//                            hakmilikSebelumPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                            hakmilikSebelumPermohonan.setHakmilik(hakmilikBaru);
                            hakmilikSebelumPermohonan.setPermohonan(p);
                            hakmilikSebelumPermohonan.setInfoAudit(ia);
                            listHakmilikSebelumPermohonan.add(hakmilikSebelumPermohonan);

                        } else {
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
//                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
//                                hakmilikAsalPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }
                        }
                    }
                }
                
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikLama);

                if (!senaraiHakmilikPihak.isEmpty()) {
                    for (HakmilikPihakBerkepentingan hpb : senaraiHakmilikPihak) {
                        HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                        hpBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        hpBaru.setHakmilik(hakmilikBaru);
                        hpBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        hpBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : null);
                        hpBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : null);
                        hpBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : null);
                        hpBaru.setAktif('Y');
                        hpBaru.setKaveatAktif('T');
                        hpBaru.setNoSalinan(0);
                        hpBaru.setInfoAudit(ia);
                        listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                        LOG.debug("--add hakmilik pihak--");

                        PermohonanPihak pihakBaru = new PermohonanPihak();

                        pihakBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        pihakBaru.setHakmilik(hakmilikBaru);
                        pihakBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        pihakBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                        pihakBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : 1);
                        pihakBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : 1);
                        pihakBaru.setInfoAudit(ia);
                        pihakBaru.setPermohonan(p);
                        pihakBaru.setNama(hpb.getPihak().getNama());
                        pihakBaru.setJenisPengenalan(hpb.getPihak().getJenisPengenalan());
                        pihakBaru.setNoPengenalan(hpb.getPihak().getNoPengenalan());
                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(hpb.getPihak().getAlamat1());
                        alamat.setAlamat2(hpb.getPihak().getAlamat2());
                        alamat.setAlamat3(hpb.getPihak().getAlamat3());
                        alamat.setAlamat4(hpb.getPihak().getAlamat4());
                        alamat.setPoskod(hpb.getPihak().getPoskod());
                        alamat.setNegeri(hpb.getPihak().getNegeri());
                        pihakBaru.setAlamat(alamat);
                        pihakBaru.setWargaNegara(hpb.getPihak().getWargaNegara() != null ? hpb.getPihak().getWargaNegara() : null);
                        listPermohonanPihak.add(pihakBaru);
                        LOG.debug("--add mohon pihak daripada pemohon baru--");
                    }
                } else {
                    List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        LOG.debug("id permohonan--" + idPermohonan);
                        senaraiPemohon = disLaporanTanahService.getPelupusanService().findPemohonByIdPermohonanOnly(idPermohonan);
                    }
                    if (senaraiPemohon.size() > 0) {
                        for (Pemohon pemohon : senaraiPemohon) {
                            PermohonanPihak pihakBaru = new PermohonanPihak();

                            pihakBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            pihakBaru.setHakmilik(hakmilikBaru);
                            pihakBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            pihakBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            pihakBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            pihakBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            pihakBaru.setInfoAudit(ia);
                            pihakBaru.setPermohonan(p);
                            pihakBaru.setNama(pemohon.getPihak().getNama());
                            pihakBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            pihakBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                            alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                            alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                            alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                            alamat.setPoskod(pemohon.getPihak().getPoskod());
                            alamat.setNegeri(pemohon.getPihak().getNegeri());
                            pihakBaru.setAlamat(alamat);
                            pihakBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            pihakBaru.setAlamatSurat(alamatSurat);
                            listPermohonanPihak.add(pihakBaru);
                            LOG.debug("--add mohon pihak daripada pemohon baru--");

                            /*HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            hpBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            hpBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            hpBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pemohon.getPihak().getNama());
                            hpBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            hpBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            hpBaru.setAlamat1(pemohon.getPihak().getAlamat1());
                            hpBaru.setAlamat2(pemohon.getPihak().getAlamat2());
                            hpBaru.setAlamat3(pemohon.getPihak().getAlamat3());
                            hpBaru.setAlamat4(pemohon.getPihak().getAlamat4());
                            hpBaru.setPoskod(pemohon.getPihak().getPoskod());
                            hpBaru.setNegeri(pemohon.getPihak().getNegeri());
                            hpBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            hpBaru.setAlamatSurat(alamatSurat);
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada pemohon baru--");*/
                            
                            HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pihakBaru.getCawangan());
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pihakBaru.getPihak());
                            hpBaru.setJenis(pihakBaru.getJenis());
                            hpBaru.setSyerPembilang(pihakBaru.getSyerPembilang());
                            hpBaru.setSyerPenyebut(pihakBaru.getSyerPenyebut());
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pihakBaru.getNama());
                            hpBaru.setJenisPengenalan(pihakBaru.getJenisPengenalan());
                            hpBaru.setNoPengenalan(pihakBaru.getNoPengenalan());
                            hpBaru.setAlamat1(pihakBaru.getAlamat().getAlamat1());
                            hpBaru.setAlamat2(pihakBaru.getAlamat().getAlamat2());
                            hpBaru.setAlamat3(pihakBaru.getAlamat().getAlamat3());
                            hpBaru.setAlamat4(pihakBaru.getAlamat().getAlamat4());
                            hpBaru.setPoskod(pihakBaru.getAlamat().getPoskod());
                            hpBaru.setNegeri(pihakBaru.getAlamat().getNegeri());
                            hpBaru.setWargaNegara(pihakBaru.getWargaNegara());                            
                            hpBaru.setAlamatSurat(pihakBaru.getAlamatSurat());
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada mohon pihak baru--");
                            
                            
                            
                            
                        }
                    }
                }

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
            if ((StringUtils.isNotBlank(idMohonHakmilik)) && (StringUtils.isNotBlank(idPermohonan))) {
                LOG.debug("id untuk update mohon hakmilik utk disposal--" + idMohonHakmilik);
                updateMohonHakmilikPelupusan(idPermohonan, idMohonHakmilik, listHakmilikBaru.get(0));
                updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), "FL");
//                updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), trizab);
            }
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }
        if (!listHakmilikPihakBerkepentinganBaru.isEmpty()) {
            regService.simpanHakmilikPihak(listHakmilikPihakBerkepentinganBaru);
            LOG.debug("--saving hakmilik pihak--");
        }
        if (!listPermohonanPihak.isEmpty()) {
            permohonanPihakService.saveOrUpdate(listPermohonanPihak);
            LOG.debug("--saving mohon pihak--");
        }
        if (!listHakmilikAsalPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikAsal(listHakmilikAsalPermohonan);
            LOG.debug("--saving hakmilik asal--");
        }
        if (!listHakmilikSebelumPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikSebelum(listHakmilikSebelumPermohonan);
            LOG.debug("--saving hakmilik sebelum--");
        }

    }


    public void janaHakmilikBaruPendaftar2(Hakmilik hakmilikLama, InfoAudit ia, Permohonan p, Pengguna pengguna, String idPermohonan, String idMohonHakmilik, int totalHakmilik) {
        kodNegeri = conf.getProperty("kodNegeri");
        listHakmilikBaru = new ArrayList<Hakmilik>();
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilikLama.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilikLama.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikLama);

                LOG.debug("CREATING NEW ID HAKMILIK 2: " + idHakmilikBaru);

                Hakmilik hakmilikBaru = new Hakmilik();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                hakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToHakmilik(p, hakmilikLama, hakmilikBaru, new String[]{}, disLaporanTanahService);
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                idFail = hakmilikLama.getNoFail();
                hakmilikBaru.setNoFail(idFail);

                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setNoVersiDhde(new Integer(1));
                hakmilikBaru.setNoVersiDhke(new Integer(1));
                hakmilikBaru.setCawangan(kodCawanganDAO.findById("00"));
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho() != null ? hakmilikLama.getNoLitho() : null);
                //If FT No Pelan
                hakmilikBaru.setNoPelan(hakmilikLama.getNoPelan() != null ? hakmilikLama.getNoPelan() : null);
                //If QT No PU
                hakmilikBaru.setNoPu(hakmilikLama.getNoPu() != null ? hakmilikLama.getNoPu() : null);
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('Y');

                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                /*AUDIT INFO*/

//                InfoAudit info = pengguna.getInfoAudit();
//                info.setDimasukOleh(pengguna);
//                info.setTarikhMasuk(new java.util.Date());
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(p, mohonHakmilikBaru, hakmilikLama, new String[]{}, disLaporanTanahService);
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);
                if (hakmilikLama != null) {
                    LOG.debug("id hakmilik lama--" + hakmilikLama.getIdHakmilik());
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikLama);

                if (senaraiHakmilikPihak.isEmpty()){
                    List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        LOG.debug("id permohonan--" + idPermohonan);
                        senaraiPemohon = disLaporanTanahService.getPelupusanService().findPemohonByIdPermohonanOnly(idPermohonan);
                    }
                    if (senaraiPemohon.size() > 0) {
                        for (Pemohon pemohon : senaraiPemohon) {
                            PermohonanPihak pihakBaru = new PermohonanPihak();

                            pihakBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            pihakBaru.setHakmilik(hakmilikBaru);
                            pihakBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            pihakBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            pihakBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            pihakBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            pihakBaru.setInfoAudit(ia);
                            pihakBaru.setPermohonan(p);
                            pihakBaru.setNama(pemohon.getPihak().getNama());
                            pihakBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            pihakBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                            alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                            alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                            alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                            alamat.setPoskod(pemohon.getPihak().getPoskod());
                            alamat.setNegeri(pemohon.getPihak().getNegeri());
                            pihakBaru.setAlamat(alamat);
                            pihakBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            pihakBaru.setAlamatSurat(alamatSurat);
                            listPermohonanPihak.add(pihakBaru);
                            LOG.debug("--add mohon pihak daripada pemohon baru--");

                            /*HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            hpBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            hpBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            hpBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pemohon.getPihak().getNama());
                            hpBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            hpBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            hpBaru.setAlamat1(pemohon.getPihak().getAlamat1());
                            hpBaru.setAlamat2(pemohon.getPihak().getAlamat2());
                            hpBaru.setAlamat3(pemohon.getPihak().getAlamat3());
                            hpBaru.setAlamat4(pemohon.getPihak().getAlamat4());
                            hpBaru.setPoskod(pemohon.getPihak().getPoskod());
                            hpBaru.setNegeri(pemohon.getPihak().getNegeri());
                            hpBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            hpBaru.setAlamatSurat(alamatSurat);
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada pemohon baru--");*/
                            
                            HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pihakBaru.getCawangan());
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pihakBaru.getPihak());
                            hpBaru.setJenis(pihakBaru.getJenis());
                            hpBaru.setSyerPembilang(pihakBaru.getSyerPembilang());
                            hpBaru.setSyerPenyebut(pihakBaru.getSyerPenyebut());
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pihakBaru.getNama());
                            hpBaru.setJenisPengenalan(pihakBaru.getJenisPengenalan());
                            hpBaru.setNoPengenalan(pihakBaru.getNoPengenalan());
                            hpBaru.setAlamat1(pihakBaru.getAlamat().getAlamat1());
                            hpBaru.setAlamat2(pihakBaru.getAlamat().getAlamat2());
                            hpBaru.setAlamat3(pihakBaru.getAlamat().getAlamat3());
                            hpBaru.setAlamat4(pihakBaru.getAlamat().getAlamat4());
                            hpBaru.setPoskod(pihakBaru.getAlamat().getPoskod());
                            hpBaru.setNegeri(pihakBaru.getAlamat().getNegeri());
                            hpBaru.setWargaNegara(pihakBaru.getWargaNegara());                            
                            hpBaru.setAlamatSurat(pihakBaru.getAlamatSurat());
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada mohon pihak baru--");
                            
                            
                            
                            
                        }
                    }
                }

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
            if ((StringUtils.isNotBlank(idMohonHakmilik)) && (StringUtils.isNotBlank(idPermohonan))) {
                LOG.debug("id untuk update mohon hakmilik utk disposal--" + idMohonHakmilik);
                updateMohonHakmilikPelupusan(idPermohonan, idMohonHakmilik, listHakmilikBaru.get(0));
                updateMohonRujukLuar3(p, ia, listHakmilikBaru.get(0), "FL");
//                updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), trizab);
            }
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }
        if (!listHakmilikPihakBerkepentinganBaru.isEmpty()) {
            regService.simpanHakmilikPihak(listHakmilikPihakBerkepentinganBaru);
            LOG.debug("--saving hakmilik pihak--");
        }
        if (!listPermohonanPihak.isEmpty()) {
            permohonanPihakService.saveOrUpdate(listPermohonanPihak);
            LOG.debug("--saving mohon pihak--");
        }
        if (!listHakmilikAsalPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikAsal(listHakmilikAsalPermohonan);
            LOG.debug("--saving hakmilik asal--");
        }
        if (!listHakmilikSebelumPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikSebelum(listHakmilikSebelumPermohonan);
            LOG.debug("--saving hakmilik sebelum--");
        }

    }

    public void updateMohonHakmilikPelupusan(String idPermohonan, String idMohonHakmilik, Hakmilik hakmilikBaru) {
        Permohonan permohonan = new Permohonan();
        permohonan = disLaporanTanahService.getPermohonanDAO().findById(idPermohonan);
        HakmilikPermohonan hp = new HakmilikPermohonan();
        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
        if (hp != null) {
            hp.setPermohonan(permohonan);
            hp.setNoLot(hakmilikBaru.getNoLot());
            hp.setLot(hakmilikBaru.getLot());
            hp.setHakmilik(hakmilikBaru);
            strService.saveHakmilikPermohonan(hp);
        }
    }

    public void updateMohonRujukLuar(Permohonan permohonanBaru, InfoAudit ia, Hakmilik hakmilikBaru, String kodRuj) {
        LOG.debug("update mohon ruj luar--");
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        mohonRujukLuar.setHakmilik(hakmilikBaru);
        mohonRujukLuar.setPermohonan(permohonanBaru);
        mohonRujukLuar.setCawangan(permohonanBaru.getCawangan());
//        mohonRujukLuar.setNoRujukan(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setInfoAudit(ia);

        if (!StringUtils.isEmpty(kodRuj)) {
            mohonRujukLuar.setTarikhRujukan(new Date());
            mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
        }
        strService.saveMohonRujukLuar(mohonRujukLuar);

    }

    public void updateMohonRujukLuar3(Permohonan permohonanBaru, InfoAudit ia, Hakmilik hakmilikBaru, String kodRuj) {
        LOG.debug("update mohon ruj luar--");
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        mohonRujukLuar.setHakmilik(hakmilikBaru);
        mohonRujukLuar.setPermohonan(permohonanBaru);
        mohonRujukLuar.setCawangan(strService.findByKodCaw("00"));
//        mohonRujukLuar.setNoRujukan(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setNoFail(permohonanBaru.getPermohonanSebelum().getIdPermohonan());
        mohonRujukLuar.setInfoAudit(ia);

        if (!StringUtils.isEmpty(kodRuj)) {
            mohonRujukLuar.setTarikhRujukan(new Date());
            mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
        }
        strService.saveMohonRujukLuar(mohonRujukLuar);

    }
    //added by Syazwan 18/3/2014
    public void updateMohonRujukLuar1(Permohonan permohonanBaru, InfoAudit ia, Hakmilik hakmilikBaru, String kodRuj, TanahRizabPermohonan trizab) {
        LOG.debug("update mohon ruj luar--");
        PermohonanRujukanLuar mohonRujukLuar = new PermohonanRujukanLuar();
        mohonRujukLuar.setHakmilik(hakmilikBaru);
        mohonRujukLuar.setPermohonan(permohonanBaru);
        mohonRujukLuar.setCawangan(permohonanBaru.getCawangan());
        mohonRujukLuar.setItem(trizab.getBandarPekanMukim().getNama());
//        mohonRujukLuar.setNoRujukan(noRuj);
//        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setNoFail(permohonanBaru.getIdPermohonan());
        mohonRujukLuar.setInfoAudit(ia);

        if (!StringUtils.isEmpty(kodRuj)) {
            mohonRujukLuar.setTarikhRujukan(trizab.getTarikhWarta());
            mohonRujukLuar.setKodRujukan(kodRujukanDAO.findById(kodRuj));
            mohonRujukLuar.setNoRujukan(trizab.getNoWarta());
        }
        strService.saveMohonRujukLuar(mohonRujukLuar);

    }

    public void janaHakmilikBaruNew(Hakmilik hakmilikLama, InfoAudit ia, Permohonan p, Pengguna pengguna, String idPermohonan, String idMohonHakmilik, int totalHakmilik, int cawangan) {
        kodNegeri = conf.getProperty("kodNegeri");
        listHakmilikBaru = new ArrayList<Hakmilik>();
        KodCawangan caw = new KodCawangan();
        if (cawangan == 1) {
            caw = kodCawanganDAO.findById("00");
        } else {
            caw = pengguna.getKodCawangan();
        }
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilikLama.getBandarPekanMukim();
                LOG.debug("daerah :" + hakmilikLama.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilikLama);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                DisHakmilikPermohonan disHakmilikPermohonan = new DisHakmilikPermohonan();
                hakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToHakmilik(p, hakmilikLama, hakmilikBaru, new String[]{}, disLaporanTanahService);
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                idFail = hakmilikLama.getNoFail();
                hakmilikBaru.setNoFail(idFail);
                hakmilikBaru.setNoVersiDhde(new Integer(1));
                hakmilikBaru.setNoVersiDhke(new Integer(1));
                hakmilikBaru.setCawangan(caw);
                hakmilikBaru.setDaerah(hakmilikLama.getDaerah());
                hakmilikBaru.setNoLitho(hakmilikLama.getNoLitho() != null ? hakmilikLama.getNoLitho() : null);
                //If FT No Pelan
                hakmilikBaru.setNoPelan(hakmilikLama.getNoPelan() != null ? hakmilikLama.getNoPelan() : null);
                //If QT No PU
                hakmilikBaru.setNoPu(hakmilikLama.getNoPu() != null ? hakmilikLama.getNoPu() : null);
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
                String noHakmilik = idHakmilikBaru.substring(idHakmilikBaru.length() - 8);
                /*copy NO HAKMILIK*/
                LOG.debug("noHakmilik :" + noHakmilik);
                hakmilikBaru.setNoHakmilik(noHakmilik);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            hakmilikBaru.setNoLot(hp.getNoLot());
                            hakmilikBaru.setLot(hp.getLot());
                        }
                        hakmilikBaru.setTarikhDaftarAsal(hakmilikLama.getTarikhDaftarAsal() != null ? hakmilikLama.getTarikhDaftarAsal() : null);
                    }
                }
                /*AUDIT INFO*/
                hakmilikBaru.setInfoAudit(ia);

                /*INSERT INTO MOHON HAKMILIK*/
                HakmilikPermohonan mohonHakmilikBaru = new HakmilikPermohonan();
                mohonHakmilikBaru = disHakmilikPermohonan.copyPropertiesHakmilikToMH(p, mohonHakmilikBaru, hakmilikLama, new String[]{}, disLaporanTanahService);
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        HakmilikPermohonan hp = new HakmilikPermohonan();
                        hp = disLaporanTanahService.getHakmilikPermohonanDAO().findById(Long.valueOf(idMohonHakmilik));
                        if (hp != null) {
                            mohonHakmilikBaru.setNoLot(hp.getNoLot());
                            mohonHakmilikBaru.setLot(hp.getLot());
                        }
                    }
                }
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);
                if (hakmilikLama != null) {
                    LOG.debug("id hakmilik lama--" + hakmilikLama.getIdHakmilik());
                }
                if (p.getKodUrusan() != null) {
                    if (p.getKodUrusan().getKod().equals("HKGHS")) {
                        LOG.debug("creating mohon hakmilik asal dan sebelum (daerah)");
                        List<HakmilikAsal> listHakmilikAsalHakmilikLama = new ArrayList<HakmilikAsal>();
                        listHakmilikAsalHakmilikLama = regService.cariHakmilikAsal(hakmilikLama.getIdHakmilik());
                        if (!listHakmilikAsalHakmilikLama.isEmpty()) {
                            HakmilikAsal ha = new HakmilikAsal();
                            ha = listHakmilikAsalHakmilikLama.get(0);
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(ha.getHakmilikAsal());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }

                            HakmilikSebelumPermohonan hakmilikSebelumPermohonan = new HakmilikSebelumPermohonan();
                            hakmilikSebelumPermohonan.setCawangan(caw);
                            hakmilikSebelumPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
//                            hakmilikSebelumPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                            hakmilikSebelumPermohonan.setHakmilik(hakmilikBaru);
                            hakmilikSebelumPermohonan.setPermohonan(p);
                            hakmilikSebelumPermohonan.setInfoAudit(ia);
                            listHakmilikSebelumPermohonan.add(hakmilikSebelumPermohonan);

                        } else {
                            HakmilikAsalPermohonan hakmilikAsalPermohonan = new HakmilikAsalPermohonan();
                            List<SejarahHakmilik> sejarahHakmilikList = new ArrayList<SejarahHakmilik>();
                            sejarahHakmilikList = regService.searchSejHakmilik(hakmilikLama.getIdHakmilik());
                            if (sejarahHakmilikList.size() > 0) {
                                hakmilikAsalPermohonan.setHakmilikPermohonan(mohonHakmilikBaru);
                                hakmilikAsalPermohonan.setHakmilikSejarah(sejarahHakmilikList.get(0).getIdHakmilik());
                                hakmilikAsalPermohonan.setHakmilik(hakmilikBaru);
                                hakmilikAsalPermohonan.setInfoAudit(ia);
                                listHakmilikAsalPermohonan.add(hakmilikAsalPermohonan);
                            }
                        }
                    }
                }
                List<HakmilikPihakBerkepentingan> senaraiHakmilikPihak = hpkService.findHakmilikAllPihakActiveByHakmilik(hakmilikLama);

                if (!senaraiHakmilikPihak.isEmpty()) {
                    for (HakmilikPihakBerkepentingan hpb : senaraiHakmilikPihak) {
                        HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                        hpBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        hpBaru.setHakmilik(hakmilikBaru);
                        hpBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        hpBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : null);
                        hpBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : null);
                        hpBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : null);
                        hpBaru.setAktif('Y');
                        hpBaru.setKaveatAktif('T');
                        hpBaru.setNoSalinan(0);
                        hpBaru.setInfoAudit(ia);
                        listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                        LOG.debug("--add hakmilik pihak--");

                        PermohonanPihak pihakBaru = new PermohonanPihak();

                        pihakBaru.setCawangan(hpb.getCawangan() != null ? hpb.getCawangan() : null);
                        pihakBaru.setHakmilik(hakmilikBaru);
                        pihakBaru.setPihak(hpb.getPihak() != null ? hpb.getPihak() : null);
                        pihakBaru.setJenis(hpb.getJenis() != null ? hpb.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                        pihakBaru.setSyerPembilang(hpb.getSyerPembilang() != null ? hpb.getSyerPembilang() : 1);
                        pihakBaru.setSyerPenyebut(hpb.getSyerPenyebut() != null ? hpb.getSyerPenyebut() : 1);
                        pihakBaru.setInfoAudit(ia);
                        pihakBaru.setPermohonan(p);
                        pihakBaru.setNama(hpb.getPihak().getNama());
                        pihakBaru.setJenisPengenalan(hpb.getPihak().getJenisPengenalan());
                        pihakBaru.setNoPengenalan(hpb.getPihak().getNoPengenalan());
                        Alamat alamat = new Alamat();
                        alamat.setAlamat1(hpb.getPihak().getAlamat1());
                        alamat.setAlamat2(hpb.getPihak().getAlamat2());
                        alamat.setAlamat3(hpb.getPihak().getAlamat3());
                        alamat.setAlamat4(hpb.getPihak().getAlamat4());
                        alamat.setPoskod(hpb.getPihak().getPoskod());
                        alamat.setNegeri(hpb.getPihak().getNegeri());
                        pihakBaru.setAlamat(alamat);
                        pihakBaru.setWargaNegara(hpb.getPihak().getWargaNegara() != null ? hpb.getPihak().getWargaNegara() : null);
                        listPermohonanPihak.add(pihakBaru);
                        LOG.debug("--add mohon pihak daripada pemohon baru--");
                    }
                } else {
                    List<Pemohon> senaraiPemohon = new ArrayList<Pemohon>();
                    if (StringUtils.isNotBlank(idPermohonan)) {
                        LOG.debug("id permohonan--" + idPermohonan);
                        senaraiPemohon = disLaporanTanahService.getPelupusanService().findPemohonByIdPermohonanOnly(idPermohonan);
                    }
                    if (senaraiPemohon.size() > 0) {
                        for (Pemohon pemohon : senaraiPemohon) {
                            PermohonanPihak pihakBaru = new PermohonanPihak();

                            pihakBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            pihakBaru.setHakmilik(hakmilikBaru);
                            pihakBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            pihakBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            pihakBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            pihakBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            pihakBaru.setInfoAudit(ia);
                            pihakBaru.setPermohonan(p);
                            pihakBaru.setNama(pemohon.getPihak().getNama());
                            pihakBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            pihakBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            Alamat alamat = new Alamat();
                            alamat.setAlamat1(pemohon.getPihak().getAlamat1());
                            alamat.setAlamat2(pemohon.getPihak().getAlamat2());
                            alamat.setAlamat3(pemohon.getPihak().getAlamat3());
                            alamat.setAlamat4(pemohon.getPihak().getAlamat4());
                            alamat.setPoskod(pemohon.getPihak().getPoskod());
                            alamat.setNegeri(pemohon.getPihak().getNegeri());
                            pihakBaru.setAlamat(alamat);
                            pihakBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            AlamatSurat alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            pihakBaru.setAlamatSurat(alamatSurat);
                            listPermohonanPihak.add(pihakBaru);
                            LOG.debug("--add mohon pihak daripada pemohon baru--");

                            /*HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pemohon.getCawangan() != null ? pemohon.getCawangan() : null);
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pemohon.getPihak() != null ? pemohon.getPihak() : null);
                            hpBaru.setJenis(pemohon.getJenis() != null ? pemohon.getJenis() : disLaporanTanahService.getKodJenisPihakBerkepentinganDAO().findById("PM"));
                            hpBaru.setSyerPembilang(pemohon.getSyerPembilang() != null ? pemohon.getSyerPembilang() : 1);
                            hpBaru.setSyerPenyebut(pemohon.getSyerPenyebut() != null ? pemohon.getSyerPenyebut() : 1);
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pemohon.getPihak().getNama());
                            hpBaru.setJenisPengenalan(pemohon.getPihak().getJenisPengenalan());
                            hpBaru.setNoPengenalan(pemohon.getPihak().getNoPengenalan());
                            hpBaru.setAlamat1(pemohon.getPihak().getAlamat1());
                            hpBaru.setAlamat2(pemohon.getPihak().getAlamat2());
                            hpBaru.setAlamat3(pemohon.getPihak().getAlamat3());
                            hpBaru.setAlamat4(pemohon.getPihak().getAlamat4());
                            hpBaru.setPoskod(pemohon.getPihak().getPoskod());
                            hpBaru.setNegeri(pemohon.getPihak().getNegeri());
                            hpBaru.setWargaNegara(pemohon.getPihak().getWargaNegara() != null ? pemohon.getPihak().getWargaNegara() : null);
                            alamatSurat = new AlamatSurat();
                            alamatSurat.setAlamatSurat1(pemohon.getPihak().getSuratAlamat1());
                            alamatSurat.setAlamatSurat2(pemohon.getPihak().getSuratAlamat2());
                            alamatSurat.setAlamatSurat3(pemohon.getPihak().getSuratAlamat3());
                            alamatSurat.setAlamatSurat4(pemohon.getPihak().getSuratAlamat4());
                            alamatSurat.setPoskodSurat(pemohon.getPihak().getSuratPoskod());
                            alamatSurat.setNegeriSurat(pemohon.getPihak().getSuratNegeri());
                            hpBaru.setAlamatSurat(alamatSurat);
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada pemohon baru--");*/
                            
                            HakmilikPihakBerkepentingan hpBaru = new HakmilikPihakBerkepentingan();
                            hpBaru.setCawangan(pihakBaru.getCawangan());
                            hpBaru.setHakmilik(hakmilikBaru);
                            hpBaru.setPihak(pihakBaru.getPihak());
                            hpBaru.setJenis(pihakBaru.getJenis());
                            hpBaru.setSyerPembilang(pihakBaru.getSyerPembilang());
                            hpBaru.setSyerPenyebut(pihakBaru.getSyerPenyebut());
                            hpBaru.setAktif('Y');
                            hpBaru.setKaveatAktif('T');
                            hpBaru.setNoSalinan(0);
                            hpBaru.setInfoAudit(ia);
                            hpBaru.setNama(pihakBaru.getNama());
                            hpBaru.setJenisPengenalan(pihakBaru.getJenisPengenalan());
                            hpBaru.setNoPengenalan(pihakBaru.getNoPengenalan());
                            hpBaru.setAlamat1(pihakBaru.getAlamat().getAlamat1());
                            hpBaru.setAlamat2(pihakBaru.getAlamat().getAlamat2());
                            hpBaru.setAlamat3(pihakBaru.getAlamat().getAlamat3());
                            hpBaru.setAlamat4(pihakBaru.getAlamat().getAlamat4());
                            hpBaru.setPoskod(pihakBaru.getAlamat().getPoskod());
                            hpBaru.setNegeri(pihakBaru.getAlamat().getNegeri());
                            hpBaru.setWargaNegara(pihakBaru.getWargaNegara());                            
                            hpBaru.setAlamatSurat(pihakBaru.getAlamatSurat());
                            listHakmilikPihakBerkepentinganBaru.add(hpBaru);
                            LOG.debug("--add hakmilik pihak daripada mohon pihak baru--");
                            
                            
                            
                            
                        }
                    }
                }

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
            if ((StringUtils.isNotBlank(idMohonHakmilik)) && (StringUtils.isNotBlank(idPermohonan))) {
                LOG.debug("id untuk update mohon hakmilik utk disposal--" + idMohonHakmilik);
                updateMohonHakmilikPelupusan(idPermohonan, idMohonHakmilik, listHakmilikBaru.get(0));
                updateMohonRujukLuar(p, ia, listHakmilikBaru.get(0), "FL");
            }
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }
        if (!listHakmilikPihakBerkepentinganBaru.isEmpty()) {
            regService.simpanHakmilikPihak(listHakmilikPihakBerkepentinganBaru);
            LOG.debug("--saving hakmilik pihak--");
        }
        if (!listPermohonanPihak.isEmpty()) {
            permohonanPihakService.saveOrUpdate(listPermohonanPihak);
            LOG.debug("--saving mohon pihak--");
        }
        if (!listHakmilikAsalPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikAsal(listHakmilikAsalPermohonan);
            LOG.debug("--saving hakmilik asal--");
        }
        if (!listHakmilikSebelumPermohonan.isEmpty()) {
            regService.simpanMohonHakmilikSebelum(listHakmilikSebelumPermohonan);
            LOG.debug("--saving hakmilik sebelum--");
        }

    }
}
