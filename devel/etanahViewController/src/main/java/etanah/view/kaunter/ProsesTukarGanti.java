/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.view.kaunter;

import com.google.inject.Inject;
import etanah.dao.HakmilikAsalPermohonanDAO;
import etanah.dao.HakmilikDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.HakmilikPihakBerkepentinganDAO;
import etanah.dao.HakmilikSebelumPermohonanDAO;
import etanah.dao.KodHakmilikDAO;
import etanah.dao.KodLotDAO;
import etanah.dao.KodPenyerahDAO;
import etanah.dao.KodStatusHakmilikDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.PermohonanDAO;
import etanah.model.FolderDokumen;
import etanah.model.Hakmilik;
import etanah.model.HakmilikAsal;
import etanah.model.HakmilikAsalPermohonan;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikSebelum;
import etanah.model.HakmilikSebelumPermohonan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodCawangan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodHakmilik;
import etanah.model.KodStatusHakmilik;
import etanah.model.KodUrusan;
import etanah.model.Pengguna;
import etanah.model.Permohonan;
import etanah.sequence.GeneratorIdHakmilik;
import etanah.sequence.GeneratorIdPerserahan;
import etanah.service.RegService;
import etanah.service.common.HakmilikPermohonanService;
import etanah.service.common.HakmilikUrusanService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author nimda
 */
public class ProsesTukarGanti {
    
    @Inject
    private GeneratorIdPerserahan idPerserahanGenerator;
    
    @Inject
    private PermohonanDAO permohonanDAO;
    
    @Inject
    private RegService regService;
    
    @Inject
    private KodPenyerahDAO kodPenyerahDAO;
    
    @Inject
    private HakmilikPermohonanDAO hakmilikPermohonanDAO;
    
    @Inject
    private KodUrusanDAO kodUrusanDAO;
    
    @Inject
    private GeneratorIdHakmilik idHakmilikGenerator;
    
    @Inject
    private KodStatusHakmilikDAO kodStatusHakmilikDAO;
    
    @Inject
    private KodLotDAO kodLotDAO;
    
    @Inject
    private HakmilikDAO hakmilikDAO;
    
    @Inject
    private HakmilikPermohonanService hakmilikPermohonanService;
    
    @Inject
    private HakmilikAsalPermohonanDAO hakmilikAsalPermohonanDAO;
    
    @Inject
    private HakmilikSebelumPermohonanDAO hakmilikSebelumPermohonanDAO;
    
    @Inject
    private HakmilikPihakBerkepentinganDAO hakmilikPihakBerkepentinganDAO;
    
    @Inject
    private HakmilikUrusanService hakmilikUrusanService;
    
    @Inject
    private KodHakmilikDAO kodHakmilikDAO;
    
    private List<Hakmilik> senaraiHm;
    
    
    private static final Logger LOG = Logger.getLogger(ProsesTukarGanti.class);
    
    private static final String [] SENARAI_URUSAN = {"HKTHK", "HSTHK", "HMSC", "HTIR"};
    
    public List<Permohonan> doTukarGanti(
            String kodNegeri, Pengguna pengguna,         
            List<Hakmilik> listHakmilik ) {
        
        List<Permohonan> senaraiPermohonanTukar = new ArrayList<Permohonan>();
        
        List<Hakmilik> hsthk = new ArrayList<Hakmilik>();
        
        List<Hakmilik> hkthk = new ArrayList<Hakmilik>();
        
        List<Hakmilik> hmsc = new ArrayList<Hakmilik>();
        
        List<Hakmilik> htir = new ArrayList<Hakmilik>();
        
        senaraiHm = new ArrayList<Hakmilik>();
        
        KodCawangan kodCaw = null;
        
        if (listHakmilik != null) {
            for (Hakmilik hm : listHakmilik) {
                if(hm.getIdHakmilikInduk() != null) continue;//hm strata
                if (hm == null || hm.getNoVersiDhde() == null || hm.getNoVersiDhde() > 0) continue;
                List<HakmilikPermohonan> hakmilikPermohonan 
                        = hakmilikPermohonanService
                                .findHakmilikPermohonanByKodUrusanAktif(hm.getIdHakmilik(), Arrays.asList(SENARAI_URUSAN));
                
                if (!hakmilikPermohonan.isEmpty()) continue;
                
                if (kodCaw == null ) {
                    kodCaw = hm.getCawangan();
                }                
                
                KodHakmilik kodHm = hm.getKodHakmilik();
                if ("HSM".equals(kodHm.getKod()) || "HSD".equals(kodHm.getKod()) || "HMM".equals(kodHm.getKod()))
                    hsthk.add(hm);
                else if ("GRN".equals(kodHm.getKod()) || "PN".equals(kodHm.getKod())
                        || "GM".equals(kodHm.getKod()) || "PM".equals(kodHm.getKod())
                        || "GMM".equals(kodHm.getKod()))
                    hkthk.add(hm);
                else if ("IR".equals(kodHm.getKod()))
                    htir.add(hm);
                else {
                    // TODO : find hm lama dalam mohon_hm_asal/mohon_hm_sblm
                    
                    List<HakmilikAsalPermohonan> senaraiHakmilikAsal = hakmilikPermohonanService.findByIdSejHakmilik(hm.getIdHakmilik());
                    if (senaraiHakmilikAsal.isEmpty()) {
                        List<HakmilikSebelumPermohonan> senaraiHakmilikSblm 
                                = hakmilikPermohonanService.findByIdSejHakmilikSblm(hm.getIdHakmilik());
                        if (senaraiHakmilikSblm.isEmpty()) {
                            hmsc.add(hm);
//                            senaraiHm.add(hm);
                        } else {
                            for (HakmilikSebelumPermohonan sblm : senaraiHakmilikSblm) {                                
                                senaraiHm.add(sblm.getHakmilik());
                            }
                        }
                    } else {
                        for (HakmilikAsalPermohonan asal : senaraiHakmilikAsal) {
                            senaraiHm.add(asal.getHakmilik());
                                
                        }
                    }
                }
            }
        }
        
        
        if (!hsthk.isEmpty()) {
            KodUrusan kodUrusan = kodUrusanDAO.findById("HSTHK");
            Permohonan p = doSave(kodNegeri, pengguna, kodCaw, hsthk, kodUrusan);
            senaraiPermohonanTukar.add(p);
        }
        if (!hkthk.isEmpty()) {
            KodUrusan kodUrusan = kodUrusanDAO.findById("HKTHK");
            Permohonan p = doSave(kodNegeri, pengguna, kodCaw, hkthk, kodUrusan);
            senaraiPermohonanTukar.add(p);
        }
        if (!hmsc.isEmpty()) {
            KodUrusan kodUrusan = kodUrusanDAO.findById("HMSC");
            Permohonan p = doSave(kodNegeri, pengguna, kodCaw, hmsc, kodUrusan);
            senaraiPermohonanTukar.add(p);
        }
        
        if (!htir.isEmpty()) {
            KodUrusan kodUrusan = kodUrusanDAO.findById("HTIR");
            Permohonan p = doSave(kodNegeri, pengguna, kodCaw, htir, kodUrusan);
            senaraiPermohonanTukar.add(p);
        }
        
        return senaraiPermohonanTukar;
    }
    
    private Permohonan doSave( String kodNegeri, Pengguna pengguna,  KodCawangan kodCawangan,       
            List<Hakmilik> listHakmilik , KodUrusan kodUrusan) {
        
        KodCawangan caw = pengguna.getKodCawangan();
        InfoAudit iaPermohonan = new InfoAudit();
        // need to set the exact date for Permohonan
        Date d = new Date();
        iaPermohonan.setTarikhMasuk(d);
        iaPermohonan.setDimasukOleh(pengguna);
        
        FolderDokumen fd = new FolderDokumen();
        fd.setTajuk("-");
        fd.setInfoAudit(iaPermohonan);
        
        if (kodCawangan == null) kodCawangan = pengguna.getKodCawangan();
        
        String idPermohonan = idPerserahanGenerator.generate(
                    kodNegeri, kodCawangan, kodUrusan);
        
        Permohonan p = new Permohonan();
        p.setStatus("TA");
        p.setIdPermohonan(idPermohonan);
        p.setCawangan(kodCawangan);
        p.setKodUrusan(kodUrusan);
        p.setFolderDokumen(fd);       
        p.setInfoAudit(iaPermohonan);
        
        KodCawanganJabatan kcj = regService.getJabatanPendaftaranByCaw(pengguna.getKodCawangan().getKod());
        if (kcj != null) {          
          p.setKodPenyerah(kodPenyerahDAO.findById(kcj.getKodPenyerah()));
          p.setIdPenyerah(kcj.getIdPenyerah());
          p.setPenyerahNama(kcj.getNama());
          p.setPenyerahNoPengenalan(kcj.getNoPengenalan());          
          p.setPenyerahJenisPengenalan(kcj.getJenisPengenalan());
          p.setPenyerahAlamat1(kcj.getAlamat1());
          p.setPenyerahAlamat2(kcj.getAlamat2());
          p.setPenyerahAlamat3(kcj.getAlamat3());
          p.setPenyerahAlamat4(kcj.getAlamat4());
          p.setPenyerahPoskod(kcj.getPoskod());
          p.setPenyerahNegeri(kcj.getNegeri());
        }
        
        p.setUntukTahun(d.getYear() + 1900);
        p.setIdWorkflow(kodUrusan.getIdWorkflow());
        permohonanDAO.save(p);
        
        // attach Hakmilik
        if (listHakmilik != null) {
            //HMSC : generate new hakmilik based on listHakmilik            
            if (kodUrusan.getKod().equals("HMSC")) {                
                KodStatusHakmilik kodStsHm = kodStatusHakmilikDAO.findById("T");               
                
                for (Hakmilik hm : listHakmilik) {
                    if (hm == null) continue;
                    Hakmilik hm1 = new Hakmilik();                    
                    String idHm1 = idHakmilikGenerator.generate(kodNegeri, null, hm);
                    String noHm = idHm1.substring(idHm1.length() - 8);
//                    KodLot kodLot = kodLotDAO.findById("2");
                    hm1.setIdHakmilik(idHm1);
                    hm1.setKodStatusHakmilik(kodStsHm);
                    hm1.setNoHakmilik(noHm);
                    hm1.setLot(hm.getLot());
                    hm1.setInfoAudit(iaPermohonan);
                    hm1.setNoFail(hm.getNoFail());
                    hm1.setCawangan(hm.getCawangan());
                    hm1.setSeksyen(hm.getSeksyen());
                    hm1.setLokasi(hm.getLokasi());          
                    hm1.setNoLitho(hm.getNoLitho());          
                    hm1.setSekatanKepentingan(hm.getSekatanKepentingan());
                    hm1.setSyaratNyata(hm.getSyaratNyata());
                    hm1.setPbt(hm.getPbt());                    
//                    hm1.setLuas(BigDecimal.ZERO);
                    hm1.setKategoriTanah(hm.getKategoriTanah());
                    hm1.setKegunaanTanah(hm.getKegunaanTanah());
                    hm1.setTarikhDaftarAsal(hm.getTarikhDaftarAsal()!= null ? hm.getTarikhDaftarAsal(): hm.getTarikhDaftar());
                    hm1.setTarikhLuput(hm.getTarikhLuput());                    
                    hm1.setTempohPegangan(hm.getTempohPegangan());
                    hm1.setPegangan(hm.getPegangan());
                    hm1.setLotBumiputera(hm.getLotBumiputera());
                    hm1.setRizab(hm.getRizab());
                    hm1.setRizabNoWarta(hm.getRizabNoWarta());
                    hm1.setRizabTarikhWarta(hm.getRizabTarikhWarta());
                    hm1.setGsaNoWarta(hm.getGsaNoWarta());
                    hm1.setGsaKawasan(hm.getGsaKawasan());
                    hm1.setGsaTarikhWarta(hm.getGsaTarikhWarta());
                    hm1.setPbtKawasan(hm.getPbtKawasan());
                    hm1.setPbtNoWarta(hm.getPbtNoWarta());
                    hm1.setPbtTarikhWarta(hm.getPbtTarikhWarta());
                    hm1.setMilikPersekutuan(hm.getMilikPersekutuan());
                    hm1.setKodUnitLuas(hm.getKodUnitLuas());
                    hm1.setNoLot(hm.getNoLot());
                    hm1.setCukai(hm.getCukai());
                    hm1.setLuas(hm.getLuas());
                    hm1.setNoPelan(hm.getNoPelan());
                    hm1.setNoPu(hm.getNoPu());
                    hm1.setDaerah(hm.getDaerah());
                    hm1.setNoVersiDhde(0);
                    hm1.setNoVersiDhke(0);
                    hm1.setBandarPekanMukim(hm.getBandarPekanMukim());
                    
                    if (hm.getKodHakmilik() != null) {
                        KodHakmilik kod = hm.getKodHakmilik();
                        if (kod.getKod().equals("EMR")) {                            
                            KodHakmilik newCode = kodHakmilikDAO.findById("GM");
                            hm1.setKodHakmilik(newCode);
                        } else if (kod.getKod().equals("CT")){
                            KodHakmilik newCode = kodHakmilikDAO.findById("GRN");
                            hm1.setKodHakmilik(newCode);
                        }
                    }
                    
                    hakmilikDAO.save(hm1);   
                    
                    senaraiHm.add(hm1);
                    
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm1);
                    hpa.setInfoAudit(iaPermohonan);
                    hpa.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa);
                    
                    List<HakmilikAsal> listHmAsal = hm.getSenaraiHakmilikAsal();
                    List<HakmilikSebelum> listHmSblm = hm.getSenaraiHakmilikSebelum();
                    List<HakmilikAsalPermohonan> listHmAsalPmhn = new ArrayList<HakmilikAsalPermohonan>();
                    List<HakmilikSebelumPermohonan> listHmSblmPmhn = new ArrayList<HakmilikSebelumPermohonan>();
                    
                    for (HakmilikAsal hmAsal : listHmAsal) {
                        HakmilikAsalPermohonan hmAsalPmhn = new HakmilikAsalPermohonan();
                        hmAsalPmhn.setHakmilikPermohonan(hpa);
                        hmAsalPmhn.setHakmilik (hm1);
                        hmAsalPmhn.setHakmilikSejarah(hmAsal.getHakmilikAsal());
                        hmAsalPmhn.setInfoAudit(iaPermohonan);
                        listHmAsalPmhn.add(hmAsalPmhn);
                    }
                    
                    for(HakmilikSebelum hmSblm : listHmSblm) {
                        HakmilikSebelumPermohonan hmSblmPmhn = new HakmilikSebelumPermohonan();
                        hmSblmPmhn.setHakmilikPermohonan(hpa);
                        hmSblmPmhn.setPermohonan(p);
                        hmSblmPmhn.setCawangan(pengguna.getKodCawangan());                        
                        hmSblmPmhn.setHakmilik(hm1);
                        hmSblmPmhn.setHakmilikSejarah(hmSblm.getHakmilikSebelum());
                        hmSblmPmhn.setInfoAudit(iaPermohonan);
                        listHmSblmPmhn.add(hmSblmPmhn);                        
                    }
                    
                    if (listHmAsal.isEmpty()) {
                        HakmilikAsalPermohonan hmAsalPmhn = new HakmilikAsalPermohonan();
                        hmAsalPmhn.setHakmilikPermohonan(hpa);
                        hmAsalPmhn.setHakmilik (hm1);
                        hmAsalPmhn.setHakmilikSejarah(hm.getIdHakmilik());
                        hmAsalPmhn.setInfoAudit(iaPermohonan);
                        hakmilikAsalPermohonanDAO.save(hmAsalPmhn);
                    } else {
                        for (HakmilikAsalPermohonan asal : listHmAsalPmhn) {
                            hakmilikAsalPermohonanDAO.save(asal);
                        }
                        for (HakmilikSebelumPermohonan sblm : listHmSblmPmhn) {
                            hakmilikSebelumPermohonanDAO.save(sblm);
                        }                        
                        
                        HakmilikSebelumPermohonan hmSblmPmhn = new HakmilikSebelumPermohonan();
                        hmSblmPmhn.setHakmilikPermohonan(hpa);
                        hmSblmPmhn.setPermohonan(p);
                        hmSblmPmhn.setCawangan(pengguna.getKodCawangan());                        
                        hmSblmPmhn.setHakmilik(hm1);
                        hmSblmPmhn.setHakmilikSejarah(hm.getIdHakmilik());
                        hmSblmPmhn.setInfoAudit(iaPermohonan);
                        hakmilikSebelumPermohonanDAO.save(hmSblmPmhn);
                    }
                    
                    List<HakmilikPihakBerkepentingan> listHmPhk = hm.getSenaraiPihakBerkepentingan();
                    for (HakmilikPihakBerkepentingan hmPhk : listHmPhk) {
                        if (hmPhk == null || hmPhk.getAktif() == 'T') continue;
                        HakmilikPihakBerkepentingan hmPhk1 = new HakmilikPihakBerkepentingan();
                        hmPhk1.setHakmilik(hm1);
                        hmPhk1.setCawangan(hm1.getCawangan());
                        hmPhk1.setPihakCawangan(hmPhk.getPihakCawangan());
                        hmPhk1.setJenis(hmPhk.getJenis());
                        hmPhk1.setSyerPembilang(hmPhk.getSyerPembilang());
                        hmPhk1.setSyerPenyebut(hmPhk.getSyerPenyebut());
                        hmPhk1.setPerserahan(hmPhk.getPerserahan());
                        hmPhk1.setPerserahanKaveat(hmPhk.getPerserahanKaveat());
                        hmPhk1.setKaveatAktif(hmPhk.getKaveatAktif());
                        hmPhk1.setAktif(hmPhk.getAktif());
                        hmPhk1.setPihak(hmPhk.getPihak());
                        hmPhk1.setNama(hmPhk.getNama());
                        hmPhk1.setNoPengenalan(hmPhk.getNoPengenalan());
                        hmPhk1.setJenisPengenalan(hmPhk.getJenisPengenalan());
                        hmPhk1.setAlamat1(hmPhk.getAlamat1());
                        hmPhk1.setAlamat2(hmPhk.getAlamat2());
                        hmPhk1.setAlamat3(hmPhk.getAlamat3());
                        hmPhk1.setAlamat4(hmPhk.getAlamat4());
                        hmPhk1.setPoskod(hmPhk.getPoskod());
                        hmPhk1.setNegeri(hmPhk.getNegeri());
                        hmPhk1.setAlamatSurat(hmPhk.getAlamatSurat());
                        hmPhk1.setInfoAudit(iaPermohonan);
                        hmPhk1.setWargaNegara(hmPhk.getWargaNegara());
                        hakmilikPihakBerkepentinganDAO.save(hmPhk1);
                    }
                    
                    
                    List<HakmilikUrusan> senaraiHakmilikurusan = 
                            hakmilikUrusanService.findHakmilikUrusanActiveByIdHakmilik(hm.getIdHakmilik());
                    for (HakmilikUrusan hu : senaraiHakmilikurusan) {
                      if (hu.getKodUrusan().getKodPerserahan().getKod().equals("MH")) {
                        continue;
                      }
                      HakmilikUrusan hakmilikUrusanBaru = new HakmilikUrusan();
                      hakmilikUrusanBaru.setHakmilik(hm1);
                      hakmilikUrusanBaru.setIdPerserahan(hu.getIdPerserahan());
                      hakmilikUrusanBaru.setKodUrusan(hu.getKodUrusan());
                      hakmilikUrusanBaru.setAktif(hu.getAktif());
                      hakmilikUrusanBaru.setTarikhBatal(hu.getTarikhBatal());
                      hakmilikUrusanBaru.setTarikhDaftar(hu.getTarikhDaftar());
                      hakmilikUrusanBaru.setKodUnitLuas(hu.getKodUnitLuas());
                      hakmilikUrusanBaru.setLuasTerlibat(hu.getLuasTerlibat());
                      hakmilikUrusanBaru.setCawangan(hu.getCawangan());
                      hakmilikUrusanBaru.setDaerah(hu.getDaerah());
                      hakmilikUrusanBaru.setCukaiLama(hu.getCukaiLama());
                      hakmilikUrusanBaru.setCukaiBaru(hu.getCukaiBaru());
                      hakmilikUrusanBaru.setFolderDokumen(hu.getFolderDokumen());
                      hakmilikUrusanBaru.setNoRujukan(hu.getNoRujukan());
                      hakmilikUrusanBaru.setTempohTahun(hu.getTempohTahun());
                      hakmilikUrusanBaru.setTempohBulan(hu.getTempohBulan());
                      hakmilikUrusanBaru.setTempohHari(hu.getTempohHari());
                      hakmilikUrusanBaru.setInfoAudit(iaPermohonan);
                      hakmilikUrusanService.saveOrUpdate(hakmilikUrusanBaru);

                    }
                  }
            } else {
                for (Hakmilik hm : listHakmilik) {
                    HakmilikPermohonan hpa = new HakmilikPermohonan();
                    hpa.setHakmilik(hm);
                    hpa.setInfoAudit(iaPermohonan);
                    hpa.setPermohonan(p);
                    hakmilikPermohonanDAO.save(hpa);
                }
            }

        }
        return p;
    }

    public List<Hakmilik> getSenaraiHm() {
        return senaraiHm;
    }

    public void setSenaraiHm(List<Hakmilik> senaraiHm) {
        this.senaraiHm = senaraiHm;
    }
    
}
