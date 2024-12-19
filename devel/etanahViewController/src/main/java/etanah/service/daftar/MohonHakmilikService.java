/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service.daftar;

import com.google.inject.Inject;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.SejarahHakmilikDAO;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodDaerah;
import etanah.model.KodHakmilik;
import etanah.model.KodKategoriTanah;
import etanah.model.KodLot;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUOM;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.service.RegService;
import etanah.service.common.HakmilikPihakKepentinganService;
import etanah.service.common.HakmilikUrusanService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 *
 * @author khairil
 */
public class MohonHakmilikService {

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
    public String idFail;
    public String namaBPM;
    public String kodNegeri;
    public ArrayList<HakmilikPermohonan> listMohonHakmilikBaru = new ArrayList<HakmilikPermohonan>();
    private ArrayList<Hakmilik> listHakmilikBaru = new ArrayList<Hakmilik>();
    private static final Logger LOG = Logger.getLogger(MohonHakmilikService.class);
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
                        Hakmilik hmAsal = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikAsal().get(0).getHakmilikAsal());
                        if (hmAsal != null)  {
                            hakmilikBaru.setTarikhDaftarAsal(hmAsal.getTarikhDaftar());
                            if (hmAsal.getTarikhLuput() != null) {
                                hakmilikBaru.setTarikhLuput(hmAsal.getTarikhLuput());
                            }
                        }                        
                    } else if (hakmilikTerpilih.getSenaraiHakmilikAsal().size() < 0 && hakmilikTerpilih.getSenaraiHakmilikSebelum().size() > 0) {
                        LOG.debug("::start copy tarikh daftar asal dari hakmilik sebelum");
                        Hakmilik sblm = hakmilikDAO.findById(hakmilikTerpilih.getSenaraiHakmilikSebelum().get(0).getHakmilikSebelum());
                        
                        if (sblm != null) {
                            hakmilikBaru.setTarikhDaftarAsal(sblm.getTarikhDaftar());
                            if (sblm.getTarikhLuput() != null) {
                                hakmilikBaru.setTarikhLuput(sblm.getTarikhLuput());
                            }
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
                    hsp.setHakmilikSejarah(hm.getIdHakmilik());
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
//    HSBM,HKBM

//    public static void main(String[] args) {
//        String[] luasBaru = {"0.4","0.1","0.2"};
//        for(int i = 0; i < luasBaru.length; i++) {
//            BigDecimal totalLuas = new BigDecimal(luasBaru[i]);
//            System.out.println("totalLuas :"+totalLuas);
//        }
//    }

    public void janaHakmilikBaru(Hakmilik hakmilik, InfoAudit ia, Permohonan p, Pengguna pengguna, int totalHakmilik) {
        kodNegeri = conf.getProperty("kodNegeri");
        //namaBPM = hakmilik.getBandarPekanMukim().getNama();
        if (totalHakmilik > 0) {
            for (int i = 0; i < totalHakmilik; i++) {
                KodBandarPekanMukim kbpm = new KodBandarPekanMukim();
                //Ubah sedikit untuk pelupusan
                kbpm = hakmilik.getBandarPekanMukim() ;
                LOG.debug("daerah :" + hakmilik.getDaerah().getKod());
                //kbpm = regService.cariBPM(namaBPM, hakmilik.getDaerah().getKod());
                //hakmilik.setBandarPekanMukim(kbpm);
                //LOG.debug("hakmilik bpm :" + hakmilik.getBandarPekanMukim().getKod());
                LOG.debug("kodNegeri : " + kodNegeri);
                String idHakmilikBaru = "";

                idHakmilikBaru = idHakmilikGenerator.generate(kodNegeri, null, hakmilik);

                LOG.debug("CREATING NEW ID HAKMILIK : " + idHakmilikBaru);
                Hakmilik hakmilikBaru = new Hakmilik();
                /*SET ALL THE NOT NULLABLE COLLUMN IN HAKMILIK*/
                hakmilikBaru.setIdHakmilik(idHakmilikBaru);
                hakmilikBaru.setNoFail(idFail);
                //KodCawangan kc = new KodCawangan();
                //kc.setKod(kodCawangan);
                //hakmilikBaru.setCawangan(kc);
                hakmilikBaru.setCawangan(pengguna.getKodCawangan());
                hakmilikBaru.setDaerah(hakmilik.getDaerah());
                hakmilikBaru.setBandarPekanMukim(kbpm);
                hakmilikBaru.setSeksyen(null);
                KodHakmilik kodHakmilik = new KodHakmilik();
                kodHakmilik.setKod(hakmilik.getKodHakmilik().getKod());
                hakmilikBaru.setKodHakmilik(kodHakmilik);
                KodLot kl = new KodLot();
                kl.setKod("1");
                hakmilikBaru.setLot(kl);
                //hakmilikBaru.setNoLot("0");
                KodKategoriTanah kkt = new KodKategoriTanah();
                kkt.setKod("1");
                hakmilikBaru.setKategoriTanah(kkt);
//                KodKegunaanTanah kgt = new KodKegunaanTanah();
//                kgt.setKod("BO1");
//                kgt.setKategoriTanah(kkt);
                String kodgunaguna = "B01";
                hakmilikBaru.setKegunaanTanah(regService.cariKegunaanTanah(kodgunaguna, kkt.getKod()));
                hakmilikBaru.setTarikhDaftar(new java.util.Date());
                KodStatusHakmilik ksh = new KodStatusHakmilik();
                ksh.setKod("T");
                hakmilikBaru.setKodStatusHakmilik(ksh);
                hakmilikBaru.setLotBumiputera('T');
                hakmilikBaru.setMilikPersekutuan('T');
                KodUOM kuo = new KodUOM();
                kuo.setKod("M");
                hakmilikBaru.setKodUnitLuas(kuo);
                hakmilikBaru.setLuas(BigDecimal.ZERO);
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
                mohonHakmilikBaru.setHakmilik(hakmilikBaru);
                mohonHakmilikBaru.setPermohonan(p);
                mohonHakmilikBaru.setInfoAudit(ia);
                listMohonHakmilikBaru.add(mohonHakmilikBaru);
                listHakmilikBaru.add(hakmilikBaru);

            }
        }


        if (!listHakmilikBaru.isEmpty()) {
            regService.simpanHakmilikList(listHakmilikBaru);
        }
        if (!listMohonHakmilikBaru.isEmpty()) {
            regService.simpanMohonHakmilikList(listMohonHakmilikBaru);
        }

    }
    
}
