/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
//import etanah.dao.AnsuranDAO;
import etanah.dao.AmbilPampasanDAO;
import etanah.dao.AnsuranDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.KodCawanganDAO;
import etanah.dao.KodJenisPendudukanDAO;
import etanah.dao.KodKadarPremiumDAO;
import etanah.dao.KodUrusanDAO;
import etanah.dao.LanjutanBayaranDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PemohonHubunganDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.model.HakmilikPermohonan;
import etanah.model.LupusPermit;
import etanah.model.Notis;
import etanah.model.Permohonan;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.Pemohon;
import etanah.model.PemohonHubungan;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanRujukanLuar;
import etanah.dao.LupusPermitDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.NotifikasiDAO;
import etanah.dao.PemohonTanahDAO;
import etanah.dao.PenempatanPesertaDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PerbicaraanKehadiranDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermitInfoPerbaharuiDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermitStrukturDiluluskanDAO;
import etanah.dao.PermohonanBahanBatuanDAO;
import etanah.dao.PermohonanFasaGisDAO;
import etanah.dao.PermohonanJenteraDAO;
import etanah.dao.PermohonanLaporanKandunganDAO;
import etanah.dao.PermohonanLaporanKawasanDAO;
import etanah.dao.PermohonanLaporanPelanDAO;
import etanah.dao.PermohonanLaporanPohonDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanKelompokDAO;
import etanah.dao.PermohonanLaporanUsahaDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanPermitItemDAO;
import etanah.dao.PermohonanPihakDAO;
import etanah.dao.PermohonanPihakPendepositDAO;
import etanah.dao.PermohonanRujukanLuarDokumenDAO;
import etanah.dao.PermohonanRujukanLuarSalinanDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.PihakCawanganDAO;
import etanah.dao.PihakDAO;
import etanah.dao.PihakPengarahDAO;
import etanah.dao.SiriNoPtDAO;
import etanah.dao.SloganSuratDAO;
import etanah.dao.SuratRujukanLuarDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.dao.TanahRizabPermohonanDAO;
import etanah.dao.LelonganAwamDAO;
import etanah.dao.KandunganFolderDAO;
import etanah.model.Akaun;
import etanah.model.AmbilPampasan;
import etanah.model.Ansuran;
import etanah.model.CaraBayaran;
import etanah.model.DokumenKewanganBayaran;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.InfoAudit;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodCawangan;
import etanah.model.KodDUN;
import etanah.model.PermohonanNota;
import etanah.model.KodDokumenAgensi;
import etanah.model.KodHakmilik;
import etanah.model.KodItemPermit;
import etanah.model.KodJenisPendudukan;
import etanah.model.KodJenisPihakBerkepentingan;
import etanah.model.KodKadarPremium;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodPeranan;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSeksyen;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.KodUrusanAgensi;
import etanah.model.KodWarnaKP;
import etanah.model.LanjutanBayaran;
import etanah.model.NoPt;
import etanah.model.PemohonTanah;
import etanah.model.Permit;
import etanah.model.PermitInfoPerbaharui;
import etanah.model.PermitItem;
import etanah.model.PermitItemKuantiti;
import etanah.model.PermitSahLaku;
import etanah.model.PermohonanBahanBatuan;
import etanah.model.PermohonanJentera;
import etanah.model.PermohonanLaporanKawasan;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanPermitItem;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuarDokumen;
import etanah.model.PermohonanRujukanLuarSalinan;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanUkur;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.Notifikasi;
import etanah.model.PenempatanPeserta;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.Pengguna;
import etanah.model.PenggunaPeranan;
import etanah.model.PenghantarNotis;
import etanah.model.PermitStrukturDiluluskan;
import etanah.model.PermohonanFasaGis;
import etanah.model.PermohonanKelompok;
import etanah.model.PermohonanLaporanKandungan;
import etanah.model.PermohonanLaporanPohon;
import etanah.model.PermohonanLaporanUsaha;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPihakPendeposit;
import etanah.model.SloganSurat;

import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakCawangan;
import etanah.model.PihakPengarah;
import etanah.model.SenaraiKodRujukan;
import etanah.model.SiriNoPt;
import etanah.model.SuratRujukanLuar;
import etanah.model.TanahRizabPermohonan;
import etanah.model.SalinanKepada;
import etanah.model.LelonganAwam;
import etanah.model.Transaksi;
import etanah.model.UlasanJabatanTeknikal;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.ambil.PerbicaraanKehadiran;
import etanah.model.gis.PelanGIS;
import etanah.model.KodUrusan;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import etanah.model.PermohonanPermitItem;
import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.LelonganAwam;
import etanah.dao.LelonganAwamDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.model.AduanLokasi;
import etanah.model.Dokumen;
import etanah.model.FolderDokumen;
import etanah.model.KandunganFolder;
import etanah.model.KodJabatan;
import etanah.model.UrusanDokumen;
import etanah.model.ImejLaporan;
import etanah.model.gis.GISPelan;

/**
 *
 * @author muhammad.amin modify by siti fariza hanim
 */
public class PelupusanService {

    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermitInfoPerbaharuiDAO permitInfoPerbaharuiDAO;
    @Inject
    PermohonanLaporanKawasanDAO permohonanLaporanKawasanDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    KodJenisPendudukanDAO kodJenisPendudukanDAO;
    @Inject
    KodKadarPremiumDAO kodKadarPremiumDAO;
    @Inject
    PermitStrukturDiluluskanDAO permitStrukturDiluluskanDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PemohonHubunganDAO pemohonHubunganDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    PemohonTanahDAO pemohonTanahDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanRujukanLuarSalinanDAO permohonanRujukanLuarSalinanDAO;
    @Inject
    PermohonanRujukanLuarDokumenDAO permohonanRujukanLuarDokumenDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonantuntutankosDAO;
    @Inject
    LupusPermitDAO lupusPermitDAO;
    @Inject
    SiriNoPtDAO siriNoPtDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    NotifikasiDAO notifikasiDAO;
    @Inject
    PermohonanFasaGisDAO permohonanFasaGisDAO;
    @Inject
    PermohonanBahanBatuanDAO permohonanBahanBatuanDAO;
    @Inject
    PihakPengarahDAO pihakPengarahDAO;
    @Inject
    PihakCawanganDAO pihakCawanganDAO;
    @Inject
    PermohonanJenteraDAO permohonanJenteraDAO;
    @Inject
    PermohonanLaporanPelanDAO permohonanLaporanPelanDAO;
    @Inject
    PermohonanPermitItemDAO permitItemDAO;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanTandatanganDokumenDAO tandatanganDokumenDAO;
    @Inject
    PermohonanPihakDAO permohonanPihakDAO;
    @Inject
    SuratRujukanLuarDAO suratRujukanLuarDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanrujukanluarDAO;
    @Inject
    PermitItemDAO permitUntukItemDAO;
    @Inject
    LaporanTanahDAO laporanTanahDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    PermohonanPermitItemDAO permohonanPermitItemDAO;
    @Inject
    AnsuranDAO ansuranDAO;
    @Inject
    TanahRizabPermohonanDAO tanahRizabPermohonanDAO;
    @Inject
    LelonganAwamDAO lelonganAwamDAO;
    @Inject
    LanjutanBayaranDAO lanjutanBayaranDAO;
    @Inject
    PermohonanLaporanKandunganDAO permohonanLaporanKandunganDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PerbicaraanKehadiranDAO perbicaraanKehadiranDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    AmbilPampasanDAO ambilPampasanDAO;
    @Inject
    KodCawanganDAO kodCawanganDAO;
    @Inject
    SloganSuratDAO sloganDAO;
    @Inject
    PenempatanPesertaDAO penempatanPesertaDAO;
    @Inject
    PermohonanPihakPendepositDAO permohonanPihakPendepositDAO;
    @Inject
    PermohonanLaporanPohonDAO permohonanLaporanPohonDAO;
    @Inject
    PermohonanLaporanUsahaDAO permohonanLaporanUsahaDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatTambDAO;
    @Inject
    PermohonanKelompokDAO permohonanKelompokDAO;
    @Inject
    KodUrusanDAO kodUrusanDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    SalinanKepadaDAO salinanKepadaDAO;
    @Inject
    KandunganFolderDAO kandunganFolderDAO;
    private static final Logger LOG = Logger.getLogger(PelupusanService.class);
    private static boolean isDebug = LOG.isDebugEnabled();

    @Transactional
    public PermohonanTuntutanKos simpanPermohonanTuntutanKos(PermohonanTuntutanKos permohonantuntutankos) {
        return permohonantuntutankosDAO.saveOrUpdate(permohonantuntutankos);
    }

    @Transactional
    public PermohonanLaporanKawasan simpanPermohonanLaporKawasan(PermohonanLaporanKawasan permohonanLaporKawasan) {
        return permohonanLaporanKawasanDAO.saveOrUpdate(permohonanLaporKawasan);
    }

    @Transactional
    public TanahRizabPermohonan simpanTanahRizabPermohonan(TanahRizabPermohonan tanahRizabPermohonan) {
        return tanahRizabPermohonanDAO.saveOrUpdate(tanahRizabPermohonan);
    }

    @Transactional
    public LelonganAwam simpanLelonganAwam(LelonganAwam lelonganAwam) {
        return lelonganAwamDAO.saveOrUpdate(lelonganAwam);
    }

    @Transactional
    public void simpanSavePermohonanTuntutanKos(PermohonanTuntutanKos permohonantuntutankos) {
        permohonantuntutankosDAO.save(permohonantuntutankos);
    }

//    //cth
//    @Transactional
//    public void simpanPermohonanTuntutanKos(PermohonanTuntutanKos permohonantuntutankos){
//        permohonantuntutankosDAO.saveOrUpdate(permohonantuntutankos);
//    }
    @Transactional
    public PermohonanJentera simpanJentera(PermohonanJentera permohonanJentera) {
        return permohonanJenteraDAO.saveOrUpdate(permohonanJentera);
    }//

    @Transactional
    public void simpanNotis(Notis notis) {
        notisDAO.saveOrUpdate(notis);
    }

    @Transactional
    public void simpanSavePermit(Permit permit) {
        permitDAO.save(permit);
    }

    @Transactional
    public void simpanSaveOrUpdatePermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);
    }

    @Transactional
    public void simpanSaveUpdatePermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);
    }

    @Transactional
    public void simpanHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    @Transactional
    public void simpanPermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        permohonanBahanBatuanDAO.saveOrUpdate(permohonanBahanBatuan);
    }

    @Transactional
    public void simpanPermohonanTuntutanKos1(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonantuntutankosDAO.saveOrUpdate(permohonanTuntutanKos);
    }

    @Transactional
    public PermohonanRujukanLuar simpanRujukLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        return permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.save(laporanTanah);
    }

    @Transactional
    public void simpanKemaskiniLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void simpanLaporanTanahSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        laporanTanahSempadanDAO.saveOrUpdate(laporanTanahSempadan);
    }

    @Transactional
    public void deleteAll(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.delete(permohonanRujukanLuar);
    }

    @Transactional
    public void deletePermohonanRujLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.delete(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanSaveorUpdateLaporanTanah(LaporanTanah laporanTanah) {
        laporanTanahDAO.saveOrUpdate(laporanTanah);
    }

    @Transactional
    public void deletePermohanHakmilik(HakmilikPermohonan hakmilikPermohonan) {
        hakmilikPermohonanDAO.delete(hakmilikPermohonan);
    }

    @Transactional
    public void deletehakmilikpermohonan(List<HakmilikPermohonan> list) {
        for (HakmilikPermohonan hp : list) {
            hakmilikPermohonanDAO.delete(hp);
        }
    }
    //Added Rohan

    @Transactional
    public void simpanPermohonanUkur(PermohonanUkur mohonUkur) {
        permohonanUkurDAO.saveOrUpdate(mohonUkur);
    }

    // Added Srinivas
    @Transactional
    public PermohonanKertas saveOrUpdatePermohonanKertas(PermohonanKertas permohonanKertas) {
        return permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    // Added Srinivas
    @Transactional
    public PermohonanPihak saveOrUpdatePermohonanPihak(PermohonanPihak permohonanPihak) {
        return permohonanPihakDAO.saveOrUpdate(permohonanPihak);
    }

    // Added Srinivas
    @Transactional
    public LaporanTanahSempadan saveOrUpdateSempadan(LaporanTanahSempadan laporanTanahSempadan) {
        return laporanTanahSempadanDAO.saveOrUpdate(laporanTanahSempadan);
    }

    // Added Srinivas
    @Transactional
    public PermohonanKertasKandungan saveOrUpdatePermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {

        return permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public HakmilikPermohonan saveOrUpdateHakmilikPermohonan(HakmilikPermohonan hakmilikPermohonan) {
        return hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan);
    }

    // Added By Rohan
    public PermohonanUkur findPermohonanUkurByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanUkur) q.uniqueResult();
    }

    public TanahRizabPermohonan findTanahRizabByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.TanahRizabPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public TanahRizabPermohonan findTanahRizabByIdPermohonanAndMh(String idPermohonan, String id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.TanahRizabPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.hakmilikPermohonan.id = :id");
        q.setString("idPermohonan", idPermohonan);
        q.setString("id", id);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public TanahRizabPermohonan findTanahRizabByIdPermohonanAndIdMh(String idPermohonan, String id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.TanahRizabPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan "
                + "and lt.hakmilikPermohonan.id = :id");
        q.setString("idPermohonan", idPermohonan);
        q.setString("id", id);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public List<TanahRizabPermohonan> findTanahRizabByIdPermohonanList(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.TanahRizabPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //PJTK

    public LelonganAwam findLelonganAwamById(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LelonganAwam lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LelonganAwam) q.uniqueResult();
    }

    public LelonganAwam findLelonganAwamByIdandidpemohon(String idPermohonan, String idPemohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LelonganAwam lt where lt.permohonan.idPermohonan = :idPermohonan and lt.pemohon.idPemohon = :idPemohon");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPemohon", idPemohon);
        return (LelonganAwam) q.uniqueResult();
    }

    public List<LelonganAwam> findLelonganAwamByIdPermohonanandidpemohon(String idPermohonan, Long idPemohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LelonganAwam lt where lt.permohonan.idPermohonan = :idPermohonan and lt.pemohon.idPemohon = :idPemohon");
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }
    // Added By Rohan

    public PermohonanRujukanLuar findPermohonanRujByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi= :kategoriAgensi");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", "ADN");
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findPermohonanRujByIdPermohonanKodRuj(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.kodRujukan.kod= :kod");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findPermohonanRujForBdnPngwal(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi is null");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }
    //Added by Srinivas and Vijay.

    public Pemohon findPemohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Pemohon lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    //Added by Srinivas and Vijay.
    public List<PermohonanPihak> findPermohonanPihak(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPihak lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //Added by Srinivas and Vijay.
    public HakmilikPermohonan findHakmilikPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonanList(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonanNoLot(String kodUrusan, String noLot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt, Permohonan p where lt.permohonan.idPermohonan = p.idPermohonan and p.kodUrusan.kod = :kodUrusan and "
                + "lt.noLot = :noLot ");
        q.setString("kodUrusan", kodUrusan);
        q.setString("noLot", noLot);
        return q.list();
    }

    public List<HakmilikPermohonan> findHakmilikPermohonanIdAliran(String kodUrusan, String noLot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt, Permohonan p, FasaPermohonan fp where lt.permohonan.idPermohonan = p.idPermohonan and p.kodUrusan.kod = :kodUrusan and "
                + "p.idPermohonan = fp.permohonan.idPermohonan and fp.idAliran = 'kenalpasti_jabatan_teknikal' and lt.noLot = :noLot ");
        q.setString("kodUrusan", kodUrusan);
        q.setString("noLot", noLot);
        return q.list();
    }

    public HakmilikPermohonan findIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.luasTerlibat != null");
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findIdMohonKodSyaratBaru(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.luasTerlibat is not null");
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findIdMohonKodSekatanBaru(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.syaratNyataBaru != null");
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    //Added by Srinivas.
    public KodJenisPihakBerkepentingan findKodJenisPihakBerkepentingan(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodJenisPihakBerkepentingan lt where lt.kod = :kod ");
        q.setString("kod", kod);
        return (KodJenisPihakBerkepentingan) q.uniqueResult();
    }
    //Added by Srinivas.

    public HakmilikPermohonan findHakmilikPermohonan_ref(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.HakmilikPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.catatan = 'L'");
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    //Added by Srinivas.
    public PermohonanRujukanLuar findPermohonanRujukanLuar_ref(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and lt.namaSidang = 'Nilaian'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    //Added by Srinivas and Vijay.
    public boolean findPermohonanKertas(String idPermohonan) {
        Session s = sessionProvider.get();
        List<PermohonanKertas> permohonanKertasList;
        boolean check = false;
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        permohonanKertasList = q.list();
        if (permohonanKertasList.size() > 0) {
            check = true;
        }
        return check;

    }
    //Added by Srinivas and Vijay.

    public PermohonanKertas findRefPermohonanKertas(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertas> findPermohonanKertasIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //Added for JKM
    public PermohonanRujukanLuar findPermohonanRujByIdPermohonanAndTiadaAgensi(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kod is null");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findPermohonanRujByNoFail(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.noFail = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    // Added By Rohan
    public PermohonanKertas findPermohonanKertasByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", "ADN");
        return (PermohonanKertas) q.uniqueResult();
    }
    // Added By Shazwan

    public PermohonanKertas findPermohonanKertasByIdPermohonanNKodDokumen(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan and lt.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanKertas) q.uniqueResult();
    }

    public Notis findNotisByIdPermohonanNKodDokumen(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Notis lt where lt.permohonan.idPermohonan = :idPermohonan and lt.dokumenNotis.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (Notis) q.uniqueResult();
    }

    public KodDUN findKodDUNByAgensi(String kodAgensi) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodDUN lt where lt.kodAgensi.kod = :kodAgensi");
        q.setString("kodAgensi", kodAgensi);
        return (KodDUN) q.uniqueResult();
    }
    //END
    // Added By Rohan

    public PermohonanKertas findMohonanKertasByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findMohonanKertasByIdPermohonanNKodDok(String idPermohonan, String kodDok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan and lt.kodDokumen.kod = :kodDok");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDok", kodDok);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanTuntutanBayar findMohonTuntutanBayarByIdTuntutKos(Long idTuntutKos) {
        String query = "Select p FROM etanah.model.PermohonanTuntutanBayar p WHERE p.permohonanTuntutanKos.idKos = :idTuntutKos";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idTuntutKos", idTuntutKos);
        return (PermohonanTuntutanBayar) q.uniqueResult();

    }

    // Added By Rohan
    public PermohonanKertas findPermohonanKertasByIdPermohonan1(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", "RMN");
        return (PermohonanKertas) q.uniqueResult();
    }

    // Added By Rohan
    public PermohonanUkur getMaxNoPUofPermohonanUkur(String year) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.noPermohonanUkur=(Select MAX(lt1.noPermohonanUkur) from etanah.model.PermohonanUkur lt1 where lt1.noPermohonanUkur LIKE :year)");
        q.setString("year", "%" + year + "%");
        return (PermohonanUkur) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void simpanHakmilikPermohonan2(HakmilikPermohonan hakmilikPermohonan2) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan2);
    }

    @Transactional
    public void updatePemohon(Pemohon p) {
        pemohonDAO.update(p);
    }

    @Transactional
    public void delete(Pemohon p) {
        pemohonDAO.delete(p);
    }

    @Transactional
    public void deleteJentera(PermohonanJentera permohonanJentera) {
        permohonanJenteraDAO.delete(permohonanJentera);
    }

    @Transactional
    public void simpanTarikHantar(Notis tarikhHantar) {
        notisDAO.saveOrUpdate(tarikhHantar);
    }

    @Transactional
    public void simpanJbtnTeknikal(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void simpanUlasanJbtnTeknikal(HakmilikPermohonan hakmilikPermohonan2) {
        hakmilikPermohonanDAO.saveOrUpdate(hakmilikPermohonan2);
    }

    @Transactional
    public PermohonanKertas simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        return permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanSavePermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.save(permohonanKertas);
    }

    @Transactional
    public void simpanSavePermohonanKertasKand(PermohonanKertasKandungan permohonanKertasKand) {
        permohonanKertasKandunganDAO.save(permohonanKertasKand);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanSavePermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.save(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPemohonHbgn(PemohonHubungan pemohonHubungan) {
        pemohonHubunganDAO.saveOrUpdate(pemohonHubungan);
    }

    @Transactional
    public void simpanPermohonanLaporanPelan(PermohonanLaporanPelan mohonLaporPelan) {
        permohonanLaporanPelanDAO.saveOrUpdate(mohonLaporPelan);

    }

    @Transactional
    public void simpanPemohonTanah(PemohonTanah pemohonTanah) {
        pemohonTanahDAO.saveOrUpdate(pemohonTanah);
    }

    @Transactional
    public void deletePemohonHbgn(PemohonHubungan pemohonHubungan) {
        pemohonHubunganDAO.delete(pemohonHubungan);
    }

    @Transactional
    public void deletePemohonTanah(PemohonTanah pemohonTanah) {
        pemohonTanahDAO.delete(pemohonTanah);
    }

    @Transactional
    public void deleteSalinan(PermohonanRujukanLuarSalinan p) {
        permohonanRujukanLuarSalinanDAO.delete(p);
    }

    @Transactional
    public void deleteDokumen(PermohonanRujukanLuarDokumen p) {
        permohonanRujukanLuarDokumenDAO.delete(p);
    }

    @Transactional
    public void saveOrUpdate(LupusPermit lupusPermit) {
        lupusPermitDAO.saveOrUpdate(lupusPermit);

    }

    @Transactional
    public void saveOrUpdate(PermohonanRujukanLuarDokumen pdo) {
        permohonanRujukanLuarDokumenDAO.saveOrUpdate(pdo);
    }

    @Transactional
    public void save(PermohonanRujukanLuarDokumen pdo) {
        permohonanRujukanLuarDokumenDAO.save(pdo);
    }

    @Transactional
    public Permit saveOrUpdate(Permit p) {
        return permitDAO.saveOrUpdate(p);

    }

    @Transactional
    public void saveOrUpdate(PermohonanLaporanPelan mohonLaporPelan) {
        permohonanLaporanPelanDAO.saveOrUpdate(mohonLaporPelan);

    }

    @Transactional
    public void saveOrUpdate(PermohonanPermitItem p) {
        permitItemDAO.saveOrUpdate(p);

    }

    @Transactional
    public void saveOrUpdate(PermohonanRujukanLuarSalinan p) {
        permohonanRujukanLuarSalinanDAO.saveOrUpdate(p);

    }

    @Transactional
    public void simpanDokumen(PermohonanRujukanLuarDokumen p) {
        permohonanRujukanLuarDokumenDAO.saveOrUpdate(p);

    }

    public PemohonHubungan findByIdHBGN(String IdHBGN) {
        return pemohonHubunganDAO.findById(Long.valueOf(IdHBGN));
    }

    //Add by afham for save Ansuran
    @Transactional
    public void simpanAnsuran(Ansuran ansuran) {
        ansuranDAO.save(ansuran);
    }

    @Transactional
    public void deleteAnsuran(Ansuran ansuran) {
        ansuranDAO.delete(ansuran);
    }

    @Transactional
    public void deleteHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        hakmilikPerbicaraanDAO.delete(hakmilikPerbicaraan);
    }

    @Transactional
    public void saveHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        hakmilikPerbicaraanDAO.saveOrUpdate(hakmilikPerbicaraan);
    }

    @Transactional
    public void deletePerbicaraanHadir(PerbicaraanKehadiran perbicaraanKehadiran) {
        perbicaraanKehadiranDAO.delete(perbicaraanKehadiran);
    }

    @Transactional
    public void simpanPerbicaraanKehadiran(PerbicaraanKehadiran perbicaraanKehadiran) {
        perbicaraanKehadiranDAO.save(perbicaraanKehadiran);
    }

    @Transactional
    public void simpanPenghantarNotis(PenghantarNotis penghantarNotis) {
        penghantarNotisDAO.save(penghantarNotis);
    }

    @Transactional
    public void savePenempatanPeserta(PenempatanPeserta penempatanPeserta) {
        penempatanPesertaDAO.save(penempatanPeserta);
    }

    @Transactional
    public void updatePenempatanPeserta(PenempatanPeserta penempatanPeserta) {
        penempatanPesertaDAO.saveOrUpdate(penempatanPeserta);
    }

    @Transactional
    public void deletePenempatanPeserta(PenempatanPeserta penempatanPeserta) {
        penempatanPesertaDAO.delete(penempatanPeserta);
    }

    public List<HakmilikPermohonan> getHakmilikPermohonan(String idmohon) {
        LOG.info("getHakmilikPermohonan ##############################################################");
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan= :id_mohon");
        q.setParameter("id_mohon", idmohon);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList(String idPermohonan) {
//        Session session = sessionProvider.get();
//        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan");
//        q.setParameter("idPermohonan", idPermohonan);
//        return q.list();

        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m, etanah.model.PermohonanKelompok p WHERE p.permohonanInduk.idPermohonan = :idPermohonan "
                + "and p.permohonan.idPermohonan = m.permohonan.idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanID(long id) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.id = :id");
        q.setParameter("id", id);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getMohonRuj(String idmohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanRujukanLuar m WHERE m.permohonan.idPermohonan= :id_mohon");
        q.setParameter("id_mohon", idmohon);
        return q.list();
    }

    public int deletePemohonHubunganByIdPemohon(long idPemohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("DELETE FROM etanah.model.PemohonHubungan p WHERE p.pemohon.idPemohon = :idPemohon");
        q.setLong("idPemohon", idPemohon);
        return q.executeUpdate();
    }

    public Pemohon findPemohonByIdPemohon(String idPermohonan) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan and p.jenis.kod is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public List<Pemohon> findPemohonByIdPemohonList(String idPermohonan) {
        String query = "Select p FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan = :idPermohonan and p.jenis.kod is null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PenghantarNotis findPenghantarNotisByNoPengenalan(String noPengenalan) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.noPengenalan = :noPengenalan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return (PenghantarNotis) q.uniqueResult();
    }

    public List<PemohonHubungan> findHubunganByIDPemohon(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and (b.kaitan ='SUAMI' or b.kaitan='ISTERI')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }

    public List<PemohonTanah> findPemohonTanahByIDPemohon(String idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonTanah b where b.pemohon.idPemohon = :idPemohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPemohon", idPemohon);
        return q.list();
    }

    public List<Pihak> findListPihakByNoPengenalanNjenisKP(String noPengenalan, String jenisPengenalan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Pihak m WHERE m.noPengenalan = :noPengenalan and m.jenisPengenalan.kod = :jenisPengenalan");
        q.setParameter("noPengenalan", noPengenalan);
        q.setParameter("jenisPengenalan", jenisPengenalan);
        return q.list();
    }

    public List<PemohonHubungan> findHubunganByIDPemohonBAPA(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and (b.kaitan ='BAPA' or b.kaitan='IBU')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }
    //ADD new

    public List<PemohonHubungan> findHubunganByIDPemohonSaudara(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and (b.kaitan ='SAUDARA LELAKI' or b.kaitan='SAUDARA PEREMPUAN')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }

    public PemohonHubungan findHubunganByIDPemohonIBU(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan='IBU'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return (PemohonHubungan) q.uniqueResult();
    }

    public PemohonHubungan findHubunganByIDPemohonBAPA2(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan='BAPA'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return (PemohonHubungan) q.uniqueResult();
    }

    public List<PemohonHubungan> findHubunganByIDANAK(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan ='ANAK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }

    public List<PemohonHubungan> findHubunganByBapaSaudara(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan ='BAPA SAUDARA'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return q.list();
    }

    public PemohonHubungan findHubunganByIdPemohonHubungan(Long idPemohonHubungan) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.idHubungan = :idPemohonHubungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohonHubungan", idPemohonHubungan);
        return (PemohonHubungan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);

        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findByIdPermohonan1(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan findByIdPermohonanMPJLB(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.luasTerlibat != null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);

        return (HakmilikPermohonan) q.uniqueResult();
    }
    // 16 Nov by Srinivas and Vijay.

    public PermohonanLaporanPelan findByNoLitho(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdPermohonanNCatatan(String idPermohonan, String catatan, String idHakmilik) {
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE ";

        if (StringUtils.isBlank(catatan)) {
            query = query + "m.catatan IS NULL AND m.permohonan.idPermohonan = :idPermohonan";
        } else {
            query = query + "m.catatan= :catatan AND m.permohonan.idPermohonan = :idPermohonan";
        }
        query = query + " AND m.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        if (!StringUtils.isBlank(catatan)) {
            q.setParameter("catatan", catatan);
        }
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public LupusPermit findByIdMohonInLupusPermit(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LupusPermit b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LupusPermit) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findByIdMohonRujukLuar(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.agensi is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByHakmilik(Hakmilik hakmilik) {
        //khairil :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    /*
     * NOTES: THIS METHOD BEEN USED IN KEPUTUSAN JAWATANKUASA MINERAL FOR PJLB AND POSSIBLY FOR LMCRG
     */
    public List<PermohonanRujukanLuar> findByIdMohonRujukLuarNAgensiNULL(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.agensi is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuarSalinan> findByIdMohon3(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return q.list();
    }

    public List<PermohonanRujukanLuarDokumen> findByIdMohonForMRLDok(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarDokumen b where b.permohonanRujukanLuar.idRujukan = :idRujukan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findByIdMohon1(String idPermohonan, String nilai) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.namaSidang = :nilai";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("nilai", nilai);
        return q.list();
    }

    public PermohonanRujukanLuar findByIdMohon2(String idPermohonan, String nilai) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.namaSidang = :nilai";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("nilai", nilai);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findByIdRujukan(Long idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuarSalinan findByIdSalinanIdRujukan(String idSalinan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.idMohonRujLuarSalinan = :idSalinan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idSalinan", idSalinan);
//        q.setString("kodAgensi", kodAgensi);
        return (PermohonanRujukanLuarSalinan) q.uniqueResult();
    }

    public PermohonanRujukanLuarSalinan findSalinan(String idRujukan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarSalinan) q.uniqueResult();
    }

    public PermohonanRujukanLuarSalinan findSalinanByIdRujukanAndKodAgensi(String idRujukan, String kodAgensi) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuarSalinan b where b.permohonanRujukanLuar.idRujukan = :idRujukan AND b.kodAgensi.kod = :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        q.setString("kodAgensi", kodAgensi);
        return (PermohonanRujukanLuarSalinan) q.uniqueResult();
    }

    public PemohonHubungan findByPengenalan(String kodPengenalan, String noPengenalan) {
        String query = "Select p FROM etanah.model.PemohonHubungan p WHERE p.jenisPengenalan.kod = :kodPengenalan and p.noPengenalan = :noPengenalan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodPengenalan", kodPengenalan);
        q.setString("noPengenalan", noPengenalan);
        return (PemohonHubungan) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan ORDER BY b.agensi.kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
//vijay

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensiAndKodRujukan(String idPermohonan) {

        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.kodRujukan='UT'   ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and  b.agensi.kod is not null and b.namaAgensi is not null ORDER BY b.idRujukan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensiADUN(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.agensi.kategoriAgensi.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensiList(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.agensi.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public PermohonanRujukanLuar getSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND b.agensi.kod = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermitSahLaku> getSenaraiPembaharuanLesenByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermitSahLaku b where b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permit getPermitbyNoPermit(String noPermit) {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPermit", noPermit);
        return (Permit) q.uniqueResult();
    }

    public Permit getPermitbyIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.Permit b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return (Permit) q.uniqueResult();
    }

    public LupusPermit getLupusPermitByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LupusPermit b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LupusPermit) q.uniqueResult();
    }
//Added by rohan

    public KodTransaksiTuntut findKodTransTuntutByName(String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTransaksiTuntut kt where kt.nama = :name");
        q.setString("name", name);
        return (KodTransaksiTuntut) q.uniqueResult();
    }

    public HakmilikPermohonan getHakmilikPermohonanNoLotIdMohon(String noLot, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.noLot= :noLot AND m.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("noLot", noLot);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanList(String noLot, String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.noLot= :noLot AND m.permohonan.idPermohonan != :idPermohonan");
        q.setParameter("noLot", noLot);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByCatatanNIdMohon(String catatan, String idPermohonan) {
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE ";

        if (StringUtils.isBlank(catatan)) {
            query = query + "m.catatan IS NULL AND m.permohonan.idPermohonan = :idPermohonan";
        } else {
            query = query + "m.catatan= :catatan AND m.permohonan.idPermohonan = :idPermohonan";
        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        if (!StringUtils.isBlank(catatan)) {
            q.setString("catatan", catatan);
        }
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan getHakmilikPermohonanListByCatatanNIdMohonIdHakmilik(String catatan, String idPermohonan, String idHakmilik) {
        String query = "SELECT m FROM etanah.model.HakmilikPermohonan m WHERE ";

        if (StringUtils.isBlank(catatan)) {
            query = query + "m.catatan IS NULL AND m.permohonan.idPermohonan = :idPermohonan and m.hakmilik.idHakmilik = :idHakmilik";
        } else {
            query = query + "m.catatan= :catatan AND m.permohonan.idPermohonan = :idPermohonan and m.hakmilik.idHakmilik = :idHakmilik";
        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        if (!StringUtils.isBlank(catatan)) {
            q.setString("catatan", catatan);
        }
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByTiadaKeputusan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and m.keputusan.kod is null");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByLulus(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and m.keputusan.kod = 'L'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPermohonan> getHakmilikPermohonanListByTolak(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikPermohonan m WHERE m.permohonan.idPermohonan = :idPermohonan and m.keputusan.kod = 'T'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //Added by Shazwan
    public KodWarnaKP findKodWarnaKPByKod(String kodWarna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodWarnaKP kt where kt.kod = :kodWarna");
        q.setString("kodWarna", kodWarna);
        return (KodWarnaKP) q.uniqueResult();
    }
    //Added by Shazwan

    public List<PermohonanPermitItem> findPermohonanPermitItemByIdMohonList(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.PermohonanPermitItem kt where kt.permohonan.idPermohonan = :idMohon");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public PermohonanPermitItem findPermohonanPermitItemByIdMohon(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.PermohonanPermitItem kt where kt.permohonan.idPermohonan = :idMohon");
        q.setString("idMohon", idMohon);
        return (PermohonanPermitItem) q.uniqueResult();
    }
    // Added by Shazwan

    public List<PermohonanBahanBatuan> findPermohonanBahanBatuanByIdMohonList(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.PermohonanBahanBatuan kt where kt.permohonan.idPermohonan = :idMohon");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public PermohonanBahanBatuan findPermohonanBahanBatuanByIdMohon(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.PermohonanBahanBatuan kt where kt.permohonan.idPermohonan = :idMohon");
        q.setString("idMohon", idMohon);
        return (PermohonanBahanBatuan) q.uniqueResult();
    }

    public PermohonanBahanBatuan findPermohonanBahanBatuanByIdLapor(Long idLapor) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT kt FROM etanah.model.PermohonanBahanBatuan kt WHERE kt.laporanTanah.idLaporan = :idLapor");
        q.setLong("idLapor", idLapor);
        return (PermohonanBahanBatuan) q.uniqueResult();
    }

    public PermitItemKuantiti findPermitItemKuantitiByIdPermitItem(Long idPermitItem) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.PermitItemKuantiti kt where kt.permitItem.idPermitItem = :idPermitItem");
        q.setLong("idPermitItem", idPermitItem);
        return (PermitItemKuantiti) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTuntutan(PermohonanTuntutan pt) {
        permohonanTuntutanDAO.saveOrUpdate(pt);
    }

    public KodTuntut findKodTuntutByName(String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTuntut kt where kt.nama = :name");
        q.setString("name", name);
        return (KodTuntut) q.uniqueResult();
    }

    public KodTuntut findKodTuntutByKod(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTuntut kt where kt.kod = :kod");
        q.setString("kod", kod);
        return (KodTuntut) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTuntutanButiran(PermohonanTuntutanButiran pt) {
        permohonanTuntutanButiranDAO.saveOrUpdate(pt);
    }

    public Permit getPermit(String noPermit) {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPermit", noPermit);
        return (Permit) q.uniqueResult();
    }

    public PemohonHubungan findPemohonHubungan(String kodPengenalan, String noPengenalan) {
        String query = "Select p FROM etanah.model.PemohonHubungan p WHERE p.jenisPengenalan.kod = :kodPengenalan and p.noPengenalan = :noPengenalan";
        List<PemohonHubungan> senaraiPemohonHubungan = sessionProvider.get().createQuery(query).setString("kodPengenalan", kodPengenalan).setString("noPengenalan", noPengenalan).list();

        if (!senaraiPemohonHubungan.isEmpty()) {
            return senaraiPemohonHubungan.get(0);
        } else {
            return null;
        }

    }

    public PermohonanJentera findJenteraById(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanJentera b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanJentera) q.uniqueResult();

    }

    public List<PermohonanTuntutanKos> findPermohonanTuntutanKos(String item) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.item =  :item";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("item", item);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findPermohonanTuntutanKos1(String item, String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.item =  :item and b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("item", item);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findPermohonanTuntutanKosByIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan =  :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public Pemohon findPemohonByPermohonanPihak(Permohonan p, Pihak ph) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idPihak");
        q.setString("idPermohonan", p.getIdPermohonan());
        q.setLong("idPihak", ph.getIdPihak());

        if (isDebug) {
            LOG.debug("list == " + q.list().size());
        }

        if (q.list().size() > 1) {
            return (Pemohon) q.list().get(0);
        }
        return (Pemohon) q.uniqueResult();
    }

    @Transactional
    public void simpanPihakPemohon(Pihak pihak, Pemohon pemohon) {
        pihakDAO.saveOrUpdate(pihak);
        pemohonDAO.saveOrUpdate(pemohon);
    }

    @Transactional
    public void simpanPihakPemohonDanAlamat(Pihak pihak, Pemohon pemohon, PihakAlamatTamb pihakAlamatTamb) {
        pihakDAO.saveOrUpdate(pihak);
        pemohonDAO.saveOrUpdate(pemohon);
        pihakAlamatTambDAO.saveOrUpdate(pihakAlamatTamb);
    }

    @Transactional
    public void simpanPemohon(Pemohon pemohon) {
        pemohonDAO.save(pemohon);
    }

    @Transactional
    public void simpanPihak(Pihak pihak, PermohonanPihak permohonanpihak) {
        pihakDAO.saveOrUpdate(pihak);
        permohonanPihakDAO.saveOrUpdate(permohonanpihak);
    }

    public List<Pemohon> findPemohonByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan and p.jenis.kod is null");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> findPemohonByIdPermohonanOnly(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanBahanBatuan findByIdMBB(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanBahanBatuan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanBahanBatuan) q.uniqueResult();
    }

    public List<PermohonanLaporanPelan> findPermohonanLaporanPelanListByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelan1(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.noLitho is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelan1WithMh(String idPermohonan, Long idMH) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.noLitho is null and b.hakmilikPermohonan.id = :id_mh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id_mh", idMH);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelanNIdHakmilik(String idPermohonan, String idHakmilik) {
        //Please don't change anything from this method because pelupusan use this method dynamically in many pages 
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.hakmilik.idHakmilik = :idHakmilik ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelanNIdMH(String idPermohonan, Long id) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
//        Query setLong = q.setLong("id", id);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }
    
    public List<PermohonanLaporanPelan> findByIdPermohonanPelanNIdMHList(String idPermohonan, Long id) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :id order by b.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
//        Query setLong = q.setLong("id", id);
        return q.list();
    }

    public PermohonanLaporanPelan findByIdPermohonanPelanNIdMHBaru(String idPermohonan, String id) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPelan b where b.permohonan.idPermohonan = :idPermohonan and b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("id", id);
//        Query setLong = q.setLong("id", id);
        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    public List<HakmilikPermohonan> findByIdPermohonanPelanNIdMohonC(String idPermohonan) {
        String query = "SELECT count(b) FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        Query setLong = q.setLong("id", id);
        return q.list();
    }

    public PermohonanKertasKandungan findByIdKertas(Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByIdKertas2(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findByIdKrts(String idPermohonan, String tajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertas b where b.permohonan.idPermohonan = :idPermohonan and b.tajuk = :tajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return (PermohonanKertas) q.uniqueResult();
    }

    public HakmilikUrusan findByHakmilikNKodUrusan(String idHakmilik, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.HakmilikUrusan b where b.hakmilik.idHakmilik = :idHakmilik and b.kodUrusan.kod = :kodUrusan and b.aktif = :flagAktif";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idHakmilik);
        q.setString("kodUrusan", kodUrusan);
        q.setCharacter("flagAktif", 'Y');
        return (HakmilikUrusan) q.uniqueResult();
    }

    public Permohonan findById(String id) {
        return permohonanDAO.findById(id);
    }

    public Permohonan findByIdNotDis(String id) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :id and b.idPermohonan not like '%DIS%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id", id);
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanJentera findByIdJentera(Long id) {
        return permohonanJenteraDAO.findById(id);
    }

    public Pihak findByIdPihak(String id) {
        return pihakDAO.findById(Long.parseLong(id));
    }

    public List<PihakPengarah> findPengarahByIDPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.PihakPengarah b where b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    public Pihak findPihak(String kodPengenalan, String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.jenisPengenalan.kod = :kod and p.noPengenalan = :no";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("kod", kodPengenalan).setString("no", noPengenalan).list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public void simpanPemohonHbgn(PihakPengarah pihakPengarah, Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        pihakPengarahDAO.saveOrUpdate(pihakPengarah);
    }

    @Transactional
    public void saveOrUpdate(PihakPengarah pihakPengarah, Pihak pihak) {
        pihakDAO.saveOrUpdate(pihak);
        pihakPengarahDAO.saveOrUpdate(pihakPengarah);
    }

    @Transactional
    public void save(PermohonanTuntutanBayar tuntutBayar) {
        permohonanTuntutanBayarDAO.save(tuntutBayar);
    }

    @Transactional
    public void saveOrUpdateHbgn(PemohonHubungan pemohonHubungan) {
        pemohonHubunganDAO.saveOrUpdate(pemohonHubungan);

    }

    @Transactional
    public Pihak saveOrUpdate(Pihak p) {
        return pihakDAO.saveOrUpdate(p);
    }

    @Transactional
    public HakmilikPermohonan saveOrUpdate(HakmilikPermohonan p) {
        return hakmilikPermohonanDAO.saveOrUpdate(p);
    }

    @Transactional
    public PermohonanPlotPelan saveOrUpdate(PermohonanPlotPelan p) {
        return permohonanPlotPelanDAO.saveOrUpdate(p);
    }

    @Transactional
    public void saveOnly(Pihak p) {
        pihakDAO.save(p);
    }

    public NoPt saveOrUpdate(NoPt np) {
        return noPtDAO.saveOrUpdate(np);
    }

    @Transactional
    public void saveOrUpdate(Pemohon p) {
        pemohonDAO.saveOrUpdate(p);

    }

    @Transactional
    public void saveOrUpdatePihakCawangan(PihakCawangan pc) {
        pihakCawanganDAO.saveOrUpdate(pc);
    }

    public Pemohon findByIdPemohon(String idPemohon) {
        return pemohonDAO.findById(Long.valueOf(idPemohon));
    }

    public PemohonTanah findByIdPemohonTanah(String idPemohon) {
        return pemohonTanahDAO.findById(Long.valueOf(idPemohon));
    }

    @Transactional
    public void deletePemohon(Pemohon p) {
        pemohonDAO.delete(p);
    }

    @Transactional
    public void deletePihakPengarah(PihakPengarah pihakPengarah) {
        pihakPengarahDAO.delete(pihakPengarah);
    }

    @Transactional
    public void deletePemohonHubungan(PemohonHubungan pemohonHubungan) {
        pemohonHubunganDAO.delete(pemohonHubungan);
    }

    @Transactional
    public void saveKandunganFolder(KandunganFolder kandunganFolder) {
        kandunganFolderDAO.saveOrUpdate(kandunganFolder);
    }

    public AduanLokasi findAduanLokasiByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.AduanLokasi b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (AduanLokasi) q.uniqueResult();
    }

    public PermohonanKertasKandungan findByBil(int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }
    //Added by Shazwan

    public PermohonanKertasKandungan findByBilNIdKertas(int bil, Long idKertas) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil and b.kertas.idKertas = :idKertas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("bil", bil);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public KodSeksyen findByKodSeksyen(int kod) {
        String query = "SELECT b FROM etanah.model.KodSeksyen b where b.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kod", kod);
        return (KodSeksyen) q.uniqueResult();
    }

    public KodBandarPekanMukim findByKodKawasan(int kod) {
        String query = "SELECT b FROM etanah.model.KodBandarPekanMukim b where b.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kod", kod);
        return (KodBandarPekanMukim) q.uniqueResult();
    }

    public PermohonanKertasKandungan findByKertasBil(Long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanRujukanLuar findMohonRujukLuarIdPermohonanNamaPenyedia(String idPermohonan, String namaPenyedia) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan =:idPermohonan and h.namaPenyedia =:namaPenyedia";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("namaPenyedia", namaPenyedia);
        PermohonanRujukanLuar prl = (PermohonanRujukanLuar) q.uniqueResult();
        return prl;
    }

    public PermohonanRujukanLuar findMohonRujukLuarIdPermohonanNamaSidang(String idPermohonan, String namaSidang) {
        String query = "Select h FROM etanah.model.PermohonanRujukanLuar h where h.permohonan.idPermohonan =:idPermohonan and h.namaSidang =:namaSidang";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("namaSidang", namaSidang);
        PermohonanRujukanLuar prl = (PermohonanRujukanLuar) q.uniqueResult();
        return prl;
    }

    public FasaPermohonan findMohonFasaByIdMohonIdPengguna(String idPermohonan, String idAliran) {
        String query = "Select h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran"
                + " order by h.idFasa desc ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);

        FasaPermohonan fsp;
        if (q.list().size() > 1) {
            fsp = (FasaPermohonan) q.list().get(0);
        } else {
            fsp = (FasaPermohonan) q.uniqueResult();
        }
        return fsp;
    }

    public FasaPermohonan findMohonFasaByIdMohonIdAliranIdPengguna(String idPermohonan, String idAliran, String idPguna) {
        String query = "Select h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran and h.idPengguna = :idPguna";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        q.setString("idPguna", idPguna);
        FasaPermohonan fsp = (FasaPermohonan) q.uniqueResult();
        return fsp;
    }

    public PermohonanNota findNota(String idPermohonan, String idAliran) {
        String query = "Select h FROM etanah.model.PermohonanNota h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        PermohonanNota mohonNota = (PermohonanNota) q.uniqueResult();
        return mohonNota;
    }

    public HakmilikPermohonan findMohonHakmilikByIdMohonIdPengguna(String idPermohonan) {
        String query = "Select h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan =:idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        HakmilikPermohonan hp = (HakmilikPermohonan) q.uniqueResult();
        return hp;
    }

    public HakmilikPermohonan findIdMH(String idPermohonan, String idHakmilik) {
        String query = "Select h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        HakmilikPermohonan hp = (HakmilikPermohonan) q.uniqueResult();
        return hp;
    }

    public FasaPermohonan findMohonFasaBySemakCharting(String idPermohonan) {
        String query = "Select p FROM etanah.model.FasaPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan and p.idAliran = 'semak_charting'";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<KodSeksyen> getSenaraiKodSeksyenByBPM(int kodBandarPekanMukim) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodSeksyen u where u.kodBandarPekanMukim = :kodBandarPekanMukim");
//            q.setString("kodBandarPekanMukim", kodBandarPekanMukim);
            q.setInteger("kodBandarPekanMukim", kodBandarPekanMukim);
            return q.list();
        } finally {
        }
    }

    public PermohonanKertas findKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public NoPt findNoPtByIdHakmilikPermohonan(long id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.NoPt b where b.hakmilikPermohonan.id = :id");
        q.setLong("id", id);
        return (NoPt) q.uniqueResult();
    }

    public NoPt findNoPtByNoPTAndKodBPM(long noPT, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.NoPt b where b.noPt = :noPT and b.kodBpm.kod = :kod");
        q.setLong("noPT", noPT);
        q.setInteger("kod", Integer.parseInt(kod));
        return (NoPt) q.uniqueResult();
    }

    public List<NoPt> findNoPtByKodBPM(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.NoPt b where b.kodBpm.kod = :kod");
        q.setInteger("kod", Integer.parseInt(kod));
        return q.list();
    }

    public PermohonanTuntutan findPermohonanTuntutanByKodTransName(String idPermohonan, String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.nama = :name AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("name", name);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public PermohonanTuntutan findPermohonanTuntutanByKodTransKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.kod = :kod AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public PermohonanTuntutan findPermohonanTuntutanByIdMohonAndKodTransTuntut(String idPermohonan, String kodTrans) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.kod = :kodTrans AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("kodTrans", kodTrans);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public List<PermohonanTuntutan> findPermohonanTuntutanByIdMohonAndKodTransTuntutList(String idPermohonan, String kodTrans) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.kod = :kodTrans AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("kodTrans", kodTrans);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanButiran findPermohonanTuntutButirByIdTuntutAndIdKos(Long idKos, Long idTuntut) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutanButiran pt where pt.permohonanTuntutanKos.idKos = :idKos  AND  pt.permohonanTuntutan.idTuntut = :idTuntut ");
        q.setLong("idKos", idKos);
        q.setLong("idTuntut", idTuntut);
        return (PermohonanTuntutanButiran) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findTuntutByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan =:idPermohonan and tk.kodTuntut.kod LIKE :kodTuntut");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", "DIS%");
        return q.list();
    }

    public List<KodTuntut> findKodTuntutPelupusan() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTuntut kt, etanah.model.KodTransaksi trans where kt.kod LIKE :kodTuntut AND kt.aktif = 'Y' "
                + "and trans.kod = kt.kodKewTrans.kod");
        q.setString("kodTuntut", "%DIS%");
        return q.list();
    }

    public PermohonanPermitItem findByIdMohonPermitItemTanahLPSP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitItem b where b.permohonan.idPermohonan = :idPermohonan and b.kodItemPermit.kod not in ('KB','PB','MB','LN')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPermitItem) q.uniqueResult();
    }

    public PermohonanPermitItem findByIdMohonPermitItem(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitItem b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPermitItem) q.uniqueResult();
    }

    public PermohonanPermitItem findByIdMohonPermitItem(String idPermohonan, String kodItem) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitItem b where b.permohonan.idPermohonan = :idPermohonan and b.kodItemPermit.kod = :kodItem";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodItem", kodItem);
        return (PermohonanPermitItem) q.uniqueResult();
    }

    public PermohonanPermitItem findByIdMohonAndIdKodPermitPermitItem(String idPermohonan, String idKodPermitItem) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitItem b where b.permohonan.idPermohonan = :idPermohonan AND b.kodItemPermit.kod = :idKodPermitItem";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idKodPermitItem", idKodPermitItem);
        return (PermohonanPermitItem) q.uniqueResult();
    }

    public PermitItem findPermitItemByIdPermit2(Long idPermit) {
        String query = "SELECT b FROM etanah.model.PermitItem b where b.permit.idPermit = :idPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPermit", idPermit);
        return (PermitItem) q.uniqueResult();
    }

    public List<KodItemPermit> getSenaraiKodItemPermitAll(String kod) {
        String query = "select u from etanah.model.KodItemPermit u where u.kod = :kod";
        Query q = sessionProvider.get().createQuery(query).setString("kod", kod);
        return q.list();
    }

    public KodItemPermit getSenaraiKodItemPermitSingle(String kod) {
        String query = "select u from etanah.model.KodItemPermit u where u.kod = :kod";
        Query q = sessionProvider.get().createQuery(query).setString("kod", kod);
        return (KodItemPermit) q.uniqueResult();
    }

    public List<KodItemPermit> getSenaraiKodItemPermit() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod = 'KLP'");
            return q.list();
        } finally {
        }
    }

    public List<KodItemPermit> getSenaraiKodItemPermitPLPTD() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod = 'KPT'");
            return q.list();
        } finally {
        }
    }

    public List<KodItemPermit> getSenaraiKodItemPermitPPRU() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodItemPermit u where u.kodKategoriItemPermit.kod = 'KRU'");
            return q.list();
        } finally {
        }
    }

    public FasaPermohonan findStageIDByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public PermohonanRujukanLuarDokumen findDokumen(String idDokumen, String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.dokumen.idDokumen = :idDokumen" + " and h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idDokumen", idDokumen).setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
    }

    public PermohonanRujukanLuarDokumen findDokumenRujukan(String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idRujukan", idRujukan);
        return (PermohonanRujukanLuarDokumen) q.uniqueResult();
    }

    public List<PermohonanRujukanLuarDokumen> findListDokumenRujukan(String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarDokumen h where h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idRujukan", idRujukan);
        return q.list();
    }

    public List<PermohonanRujukanLuarSalinan> findListSalinanRujukan(String idRujukan) {
        String query = "Select h from etanah.model.PermohonanRujukanLuarSalinan h where h.permohonanRujukanLuar.idRujukan = :idRujukan";
        Query q = sessionProvider.get().createQuery(query).setString("idRujukan", idRujukan);
        return q.list();
    }

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fp) {
        fasaPermohonanDAO.saveOrUpdate(fp);
    }

    @Transactional
    public void simpanFasaPermohonanBaru(FasaPermohonan fp) {
        fasaPermohonanDAO.save(fp);
    }

    @Transactional
    public void simpanFasaPermohonanGIS(PermohonanFasaGis fp) {
        permohonanFasaGisDAO.saveOrUpdate(fp);
    }

    public List<FasaPermohonan> findFasaPermohonanByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = 'A02Semakan' and lt.keputusan.kod = 'L'");
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idAliran", idAliran);
        return q.list();
    }
    public List<FasaPermohonan> findFasaPermohonanByIdMohonOrderBy(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan order by infoAudit.tarikhMasuk");
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idAliran", idAliran);
        return q.list();
    }

    
    
    public List<FasaPermohonan> findMohonFasaByIdMohonIdPenggunaList(String idPermohonan, String idAliran) {
//        String query = "Select h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran";
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select h FROM etanah.model.FasaPermohonan h where h.permohonan.idPermohonan =:idPermohonan and h.idAliran =:idAliran");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return q.list();
    }
// 24 march by rohan

    public Permit findPermitByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permit b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        return (Permit) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }
    // 16 Nov  by Srinivas and Vijay

    public Permohonan findIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan in ( select c.idPermohonan FROM etanah.model.Permohonan c where c.permohonanSebelum.idPermohonan = :idPermohonan) ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        return (Permohonan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public Permohonan findIdPermohonanByIdManual(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        return (Permohonan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public Permohonan findIdPermohonanByIdManualSD(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan and b.status = 'SD' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);
        return (Permohonan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public PermitStrukturDiluluskan findPermitStrukLuasByIdPermohonan(Long idPermit) {
        String query = "SELECT b FROM etanah.model.PermitStrukturDiluluskan b where b.permit.idPermit = :idPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPermit", idPermit);
        return (PermitStrukturDiluluskan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public Permohonan findPermohonanByIdPermohonanSebelum(String idPermohonanSebelum) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonanSebelum";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        return (Permohonan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public Permohonan findPermohonanByIdPermohonanSebelum(String idPermohonanSebelum, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonanSebelum AND b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        q.setString("kodUrusan", kodUrusan);
        return (Permohonan) q.uniqueResult();
        //  return (Permit)q.list().get(0);
    }

    public Permohonan findPermohonanByIdPermohonanWithoutKodUrusan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public Permohonan findPermohonanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan AND (p.kodUrusan = :kodUrusan1  OR  p.kodUrusan = :kodUrusan2)");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodUrusan1", "PLPS");
        q.setString("kodUrusan2", "RLPS");
        return (Permohonan) q.uniqueResult();
    }

    public Permohonan findPermohonanByIdPermohonan1(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Permohonan p where p.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public List<KandunganFolder> getListDokumen(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public List<Permohonan> findPermohonanByToFromDate(Date dateTo, Date dateFrom) {
        Session session = sessionProvider.get();
        String query = "SELECT p FROM etanah.model.Permohonan p";
        query = query + " where ";
        if (dateFrom != null) {
            String rule = new String();
            if (dateTo != null) {
                rule = " between ";
            } else {
                rule = " >=";
            }
            query += " p.infoAudit.tarikhMasuk " + rule + ":fromDate";
            if (dateTo != null) {
                query = query + " and :untilDate";
            }
            query = query + " and p.kodUrusan.jabatan.kod = '2'";
        }
        Query q = session.createQuery(query);
        if (dateFrom != null) {
            q.setDate("fromDate", dateFrom);
        }
        if (dateTo != null) {
            q.setDate("untilDate", dateTo);
        }
        return q.list();
    }

    public List<Permohonan> findPermohonanByToFromDateByDaerah(Date dateTo, Date dateFrom, String kodCaw) {
        Session session = sessionProvider.get();
        String query = "SELECT p FROM etanah.model.Permohonan p";
        query = query + " where ";
        if (dateFrom != null) {
            String rule = new String();
            if (dateTo != null) {
                rule = " between ";
            } else {
                rule = " >=";
            }
            query += " p.infoAudit.tarikhMasuk " + rule + ":fromDate";
            if (dateTo != null) {
                query = query + " and :untilDate";
            }
            query = query + " and p.kodUrusan.jabatan.kod = '2' and p.cawangan.kod = :kodCaw";
        }
        Query q = session.createQuery(query);
        if (dateFrom != null) {
            q.setDate("fromDate", dateFrom);
        }
        if (dateTo != null) {
            q.setDate("untilDate", dateTo);
        }
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public List<Permohonan> findPermohonanByToFromDateDEV(Date dateTo, Date dateFrom, String kodCaw) {
        Session session = sessionProvider.get();
        String query = "SELECT p FROM etanah.model.Permohonan p";
        query = query + " where ";
        if (dateFrom != null) {
            String rule = new String();
            if (dateTo != null) {
                rule = " between ";
            } else {
                rule = " >=";
            }
            query += " p.infoAudit.tarikhMasuk " + rule + ":fromDate";
            if (dateTo != null) {
                query = query + " and :untilDate";
            }
            query = query + " and p.kodUrusan.jabatan.kod = '6' and p.cawangan.kod = :kodCaw";
        }
        Query q = session.createQuery(query);
        if (dateFrom != null) {
            q.setDate("fromDate", dateFrom);
        }
        if (dateTo != null) {
            q.setDate("untilDate", dateTo);
        }

        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihak(Long idPihak) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPihak", idPihak);
        return q.list();

    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByNoPengenalan(String noPengenalan) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.pihak.noPengenalan = :noPengenalan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPengenalan", noPengenalan);
        return q.list();

    }

    public List<Hakmilik> findHakmilikByLot(String noLot) {
        String query = "SELECT b FROM etanah.model.Hakmilik b where b.noLot = :noLot";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noLot", noLot);
        return q.list();

    }

    public PermitInfoPerbaharui findPermitInfoPerbaharuiByIdPermit(String idPermit) {
        String query = "SELECT b FROM etanah.model.PermitInfoPerbaharui b where b.permit.idPermit = :idPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermit", idPermit);
        return (PermitInfoPerbaharui) q.uniqueResult();

    }

    // 26 march by rohan
//        public PermitItem findPermitItemByIdPermit(Long idPermit) {
//        String query = "SELECT b FROM etanah.model.PermitItem b where b.permit.idPermit = :idPermit";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setLong("idPermit", idPermit);
//        return (PermitItem)q.list().get(0);
//         }
    public PermitItem findPermitItemByIdPermit(Long idPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermitItem pt where pt.permit.idPermit = " + idPermit);
//        q.setLong("idPermit", idPermit);
        return (PermitItem) q.list().get(0);
    }

    public List<PermitItem> findPermitItemByIdPermitList(Long idPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermitItem pt where pt.permit.idPermit =:idPermit");
        q.setLong("idPermit", idPermit);
        return q.list();
    }

    public PermitSahLaku findPermitSahLakuByIdMohonIdPermit(String idPermohonan, Long idPermit) {
        String query = "Select h FROM etanah.model.PermitSahLaku h where h.permohonan.idPermohonan =:idPermohonan and h.permit.idPermit =:idPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idPermit", idPermit);
        PermitSahLaku psl = (PermitSahLaku) q.uniqueResult();
        return psl;
    }

    public PermitSahLaku findPermitSahLakuByIdMohon(String idPermohonan) {
        String query = "Select h FROM etanah.model.PermitSahLaku h where h.permohonan.idPermohonan =:idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermitSahLaku) q.uniqueResult();

    }

    public PermohonanTuntutanKos findMohonTuntutKosByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        System.out.println("------q.list()---------:" + q.list());
        System.out.println("------q.list()---------:" + q.list().isEmpty());
        if (!q.list().isEmpty()) {
            return (PermohonanTuntutanKos) q.list().get(0);
        } else {
            return null;
        }
    }

    public List<PermohonanTuntutanKos> findListMohonTuntutKosByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTuntutanKos findMohonTuntutKosByIdPermohonanAndIdTuntut(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        System.out.println("------q.list()---------:" + q.list());
        System.out.println("------q.list()---------:" + q.list().isEmpty());
        if (!q.list().isEmpty()) {
            return (PermohonanTuntutanKos) q.list().get(0);
        } else {
            return null;
        }
    }

    public PermohonanTuntutanKos findMohonTuntutKosByIdPermohonanAndIdTuntutAndIdHMP(String idPermohonan, String kodTuntut, long idHakmilikPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kodTuntut and b.hakmilikPermohonan.id = :idHakmilikPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        q.setLong("idHakmilikPermohonan", idHakmilikPermohonan);
        System.out.println("------q.list()---------:" + q.list());
        System.out.println("------q.list()---------:" + q.list().isEmpty());
        if (!q.list().isEmpty()) {
            return (PermohonanTuntutanKos) q.list().get(0);
        } else {
            return null;
        }
    }
    // cari

    public List<PermitSahLaku> findPermitSahLakuByIdPermit(Long idPermit) {
        String query = "SELECT b FROM etanah.model.PermitSahLaku b where b.permit.idPermit = :idPermit order by b.permohonan.idPermohonan ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPermit", idPermit);
        return q.list();
    }

    public List<PermitSahLaku> findPermitSahLakuByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermitSahLaku b where b.permit.idPermit =(SELECT b1.permit.idPermit FROM etanah.model.PermitSahLaku b1 where b1.permohonan.idPermohonan = :idPermohonan) ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Permit findIdPermitByNoPermit(String noPermit) {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPermit", noPermit);
        return (Permit) q.uniqueResult();
    }

    public KodSekatanKepentingan findSekatanByCawAndKodSekatan(String kodCaw, String sekatan) {
        String query = "SELECT b FROM etanah.model.KodSekatanKepentingan b where b.cawangan.kod = :kodCaw AND b.sekatan = :sekatan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodCaw", kodCaw);
        q.setString("sekatan", sekatan);
        return (KodSekatanKepentingan) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohonTuntutKosByIdPermit(String noPermit) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = (SELECT b1.permohonan.idPermohonan FROM etanah.model.Permit b1 where b1.noPermit = :noPermit)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noPermit", noPermit);
        return (PermohonanTuntutanKos) q.list().get(0);
    }

    public List<KodKegunaanTanah> findSenaraiKodGunaTanahByKatTanah(String kodKatTanah) {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("select u from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = :kodKatTanah and u.aktif = 'Y' order by u.nama ASC");
            q.setString("kodKatTanah", kodKatTanah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public Transaksi findTransaksiByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Transaksi b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Transaksi) q.list().get(0);
    }

    @Transactional
    public void simpanPermitSahLaku(PermitSahLaku permitSahLaku) {
        permitSahLakuDAO.saveOrUpdate(permitSahLaku);
    }

    @Transactional
    public void simpanPermitItem(PermitItem permitItem) {
        permitUntukItemDAO.saveOrUpdate(permitItem);
    }

    @Transactional
    public void simpanPermitInfoPerbaharui(PermitInfoPerbaharui permitInfoPerbaharui) {
        permitInfoPerbaharuiDAO.saveOrUpdate(permitInfoPerbaharui);
    }

    @Transactional
    public void simpanPermitStrukturLulus(PermitStrukturDiluluskan permitStrukLulus) {
        permitStrukturDiluluskanDAO.saveOrUpdate(permitStrukLulus);
    }

    //added on 12 th april
    public List<KodKegunaanTanah> getSenaraiKegunaanTanah(String kodKatTanah) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodKegunaanTanah u";
            if (StringUtils.isNotBlank(kodKatTanah)) {
                query += " where u.kategoriTanah.kod = :kodKatTanah";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(kodKatTanah)) {
                q.setString("kodKatTanah", kodKatTanah);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanahByKodGunaTanah(String kodGunaTanah) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodKegunaanTanah u";
            if (StringUtils.isNotBlank(kodGunaTanah)) {
                query += " where u.kod = :kodGunaTanah";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(kodGunaTanah)) {
                q.setString("kodGunaTanah", kodGunaTanah);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSeksyen> getSenaraiKodSeksyen(String kodBpm) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodSeksyen u";
            if (StringUtils.isNotBlank(kodBpm)) {
                query += " where u.kodBandarPekanMukim.kod = :kodBpm";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(kodBpm)) {
                q.setString("kodBpm", kodBpm);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodBandarPekanMukim> getSenaraiKodBPM(String daerah) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodBandarPekanMukim u";
            if (StringUtils.isNotBlank(daerah)) {
                query += " where u.daerah.kod = :daerah";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(daerah)) {
                q.setString("daerah", daerah);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodCawangan> getSenaraiKodCaw(String daerah) {
        try {
            Session s = sessionProvider.get();
            String query = "select u from etanah.model.KodCawangan u";
            if (StringUtils.isNotBlank(daerah)) {
                query += " where u.daerah.kod = :daerah";
            }
            query += " order by u.kod ASC";
            Query q = s.createQuery(query);
            if (StringUtils.isNotBlank(daerah)) {
                q.setString("daerah", daerah);
            }
            return q.list();
        } finally {
            //session.close();
        }
    }

    public KodSeksyen findKodSeksyen(int kod) {
        String query = "SELECT b FROM etanah.model.KodSeksyen b where b.kod = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("kod", kod);
        return (KodSeksyen) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanDokTT(PermohonanTandatanganDokumen tandatanganDokumen) {
        tandatanganDokumenDAO.saveOrUpdate(tandatanganDokumen);
    }

    @Transactional
    public void deletePermohonanDokTT(PermohonanTandatanganDokumen tandatanganDokumen) {
        tandatanganDokumenDAO.delete(tandatanganDokumen);
    }

    @Transactional
    public void deletePermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        permohonanPermitItemDAO.delete(permohonanPermitItem);
    }

    @Transactional
    public void simpanPermohonanPermitItem(PermohonanPermitItem permohonanPermitItem) {
        permohonanPermitItemDAO.save(permohonanPermitItem);
    }
//    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonan(String idPermohonan, String diTandatangan) {
//        Session s = sessionProvider.get();
////         Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.infoAudit.tarikhKemaskini = (Select MAX(pk.infoAudit.tarikhKemaskini) from etanah.model.PermohonanTandatanganDokumen pk where pk.permohonan.idPermohonan = :idPermohonan and pk.diTandatangan = :diTandatangan)");
//        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.diTandatangan = :diTandatangan ");
//        q.setString("idPermohonan", idPermohonan);
//        q.setString("diTandatangan", diTandatangan);
//        return (PermohonanTandatanganDokumen) q.uniqueResult();
//    }

    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonan(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public List<PermohonanTandatanganDokumen> findListPermohonanDokTTByIdPermohonan(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    public List<PermohonanTandatanganDokumen> findPermohonanDokTTByIdPermohonanrehy(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.permohonan.idPermohonan = :idPermohonan)");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanTandatanganDokumen> findPermohonanDokTTByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.permohonan.idPermohonan = :idPermohonan)");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonanSingle(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.permohonan.idPermohonan = :idPermohonan)");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public List<Permit> findPermitByNoLesen(String noLesen) {

        String query = "Select mk from etanah.model.Permit mk where mk.noPermit = :noLesen order by mk.tarikhpermitAkhir";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("noLesen", noLesen);
        return q.list();
    }

    public KodAgensi findKodAgensi(String nama) {
        String query = "SELECT b FROM etanah.model.KodAgensi b where b.nama = :nama";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("nama", nama);
        return (KodAgensi) q.list().get(0);
    }

    @Transactional
    public void simpanPermohonanRujLuar(PermohonanRujukanLuar permohonanrujluar) {
        permohonanrujukanluarDAO.saveOrUpdate(permohonanrujluar);

    }

    @Transactional
    public void simpanPermohonanRujukanLuar(PermohonanRujukanLuar permohonanrujluar) {
        permohonanrujukanluarDAO.save(permohonanrujluar);

    }

    @Transactional
    public void simpanPermohonanRujLuarDokumen(PermohonanRujukanLuarDokumen permohonanRujukanLuarDokumen) {
        permohonanRujukanLuarDokumenDAO.saveOrUpdate(permohonanRujukanLuarDokumen);

    }

    public List<KodAgensi> getSenaraikodAgensi() {
        try {
            String query = "Select u from etanah.model.KodAgensi u where u.kod in('6002','6003','6004','6006','6007','6008') order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
        }
    }

    public KodAgensi getSenaraikodAgensiList(String kod) {
        try {
            String query = "Select u from etanah.model.KodAgensi u where u.kod ='" + kod + "' order by u.nama";
            Query q = sessionProvider.get().createQuery(query);
            return (KodAgensi) q.uniqueResult();
        } finally {
        }
    }

    public List<KodAgensi> searchKodAgensiLupus(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "";

            if (kodNegeri != null && kodNegeri.equals("04")) {
                System.out.println("-------Melaka---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN' , 'JTK') AND kodNegeri.kod =:kodNegeri";
            } else if (kodNegeri != null && kodNegeri.equals("05")) {
                System.out.println("-------NS---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') AND kodNegeri.kod =:kodNegeri";
            }
            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchKodAgensiLupus1(String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "";

            if (kodNegeri != null && kodNegeri.equals("04")) {
                System.out.println("-------Melaka---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN' , 'JTK') AND kodNegeri.kod =:kodNegeri";
            } else if (kodNegeri != null && kodNegeri.equals("05")) {
                System.out.println("-------NS---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') AND kodNegeri.kod =:kodNegeri";
            }

            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);

            if (kodAgensiNama != null) {

                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchKodAgensiForAgensiKerajaan(String kod, String kodAgensiNama, String kodNegeri, String katgAgensi) { //use by pelupus :@mr5rule

        try {
            String query = "";

            if (kodNegeri != null) {
                System.out.println("-------Melaka---------" + kodNegeri);
                query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod not in ('ADN') AND kodNegeri.kod =:kodNegeri";
            }
//            if (kod != null) {
//                query += " AND u.kod LIKE :kod ";
//            }
//            if ((kod != null) && (kodAgensiNama != null)) {
//                query += " AND ";
//            }
            if (kodAgensiNama != null) {
                query += " AND u.nama LIKE :kodAgensiNama ";
            }
//            if((kodAgensiNama != null) && (katgAgensi != null)){
//                query += " AND ";
//            }
//            if(katgAgensi != null){
//                query += " AND u.kategoriAgensi.kod = :katgAgensi" ;
//            }
//            if((kodAgensiNama != null) && (katgAgensi != null) && (kodKementerian != null)){
//                query += " AND " ;               
//            }
////            if(kodKementerian != null){
////                query += "u.kodKementerian.kod = :kodKementerian" ;
////            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }
//            if (katgAgensi != null){
//                q.setString("katgAgensi", "%" + katgAgensi + "%");
//            }
//            if (kodKementerian != null){
//                q.setString("kodKementerian", "%" + kodKementerian + "%");
//            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensi1(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan AND  b.statusUlasan = 'T'  AND b.agensi IS NOT NULL ORDER BY b.idRujukan ASC ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public SuratRujukanLuar getSenaraiSuratRujukanLuarByIDRujLuar(String idRujukan) {
        String query = "SELECT b FROM etanah.model.SuratRujukanLuar b where b.permohonanRujukanLuar.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idRujukan", idRujukan);
        return (SuratRujukanLuar) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> getHakmilikPihakBerkepentinganByStringIdHakmilik(String idHakmilik) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT b from etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik");
        q.setParameter("idHakmilik", idHakmilik);
        return q.list();
    }

    @Transactional
    public void simpanSuratRujukanLuar(SuratRujukanLuar suratRujukanLuar) {
        suratRujukanLuarDAO.saveOrUpdate(suratRujukanLuar);
    }

    public List<PermohonanKertasKandungan> findByIdLapor(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdLaporNJSmpdn(long idLapor, String jnisSmpdn) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.jenisSempadan = :jnisSmpdn order by b.hakmilik.idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("jnisSmpdn", jnisSmpdn);
        LOG.info(q);
        return q.list();
    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdLaporNJSmpdnNIdMohon(String idPermohonan, String jnisSmpdn) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.permohonan.idPermohonan = :idPermohonan and b.jenisSempadan = :jnisSmpdn order by b.hakmilik.idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jnisSmpdn", jnisSmpdn);
        LOG.info(q);
        return q.list();
    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdLaporNJSmpdn_ref(String jnisSmpdn) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where  b.jenisSempadan = :jnisSmpdn order by b.hakmilik.idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("jnisSmpdn", jnisSmpdn);
        LOG.info(q);
        return q.list();
    }

    public LaporanTanahSempadan findLaporTanahSmpdnByIdLaporNIdHakmilik(long idLapor, String idhakmilik) {
        LOG.info(idhakmilik);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.hakmilik.idHakmilik = :idhakmilik order by b.hakmilik.idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("idhakmilik", idhakmilik);
        return (LaporanTanahSempadan) q.uniqueResult();

    }

    public LaporanTanahSempadan findLaporTanahSmpdnByIdLaporNNoLot(long idLapor, String noLot) {
        LOG.info(noLot);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.noLot = :noLot";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("noLot", noLot);
        return (LaporanTanahSempadan) q.uniqueResult();

    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdLaporNNoLotList(long idLapor, String noLot) {
        LOG.info(noLot);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.noLot = :noLot";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("noLot", noLot);
        return q.list();

    }

    public LaporanTanahSempadan findLaporTanahSmpdnByIdLaporNNoLotOnly(long idLapor, String noLot, String jnsSmpdn) {
        LOG.info(noLot);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.noLot = :noLot and b.jenisSempadan = :jnsSmpdn ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("noLot", noLot);
        q.setString("jnsSmpdn", jnsSmpdn);
        return (LaporanTanahSempadan) q.uniqueResult();

    }
    
       public LaporanTanahSempadan findLaporTanahSmpdnByIdLaporNAndSepadan(long idLapor, String jnsSmpdn) {
//        LOG.info(noLot);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor "
//                + "and b.noLot = :noLot "
                + "and b.jenisSempadan = :jnsSmpdn ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
//        q.setString("noLot", noLot);
        q.setString("jnsSmpdn", jnsSmpdn);
        return (LaporanTanahSempadan) q.uniqueResult();

    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdLapor(long idLapor) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        LOG.info(q);
        return q.list();
    }

    public List<LaporanTanahSempadan> findLaporTanahSmpdnByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info(q);
        return q.list();
    }

    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKandungan", idKandungan);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    private KandunganFolder findKandunganFolderByIdDok(String idDokumen, FolderDokumen folderDok) {
        String query = "SELECT kf From etanah.model.KandunganFolder kf where kf.folder.folderId =:idFolder and kf.dokumen.kodDokumen.kod = :idDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idDokumen", idDokumen);
        q.setLong("idFolder", folderDok.getFolderId());
        return (KandunganFolder) q.uniqueResult();

    }

    /*
     * DESIGN ONLY FOR ULASAN JABATAN TEKNIKAL AND ADUN
     * AUTHOR WITH CARE
     */
//    public List<PermohonanRujukanLuar> senaraiPermohonanRujLuarByIdPermohonan(String idPermohonan) {
//        
////=======
//    }
    public List<LaporanTanahSempadan> findListLaporTanahSmpdnByIdLaporNIdHakmilik(long idLapor, String idhakmilik) {
        LOG.info(idhakmilik);
        String query = "SELECT b FROM etanah.model.LaporanTanahSempadan b where b.laporanTanah.idLaporan = :idLapor and b.hakmilik.idHakmilik = :idhakmilik order by b.hakmilik.idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLapor", idLapor);
        q.setString("idhakmilik", idhakmilik);
        return q.list();

    }
//       public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
//       String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
//       Query q = sessionProvider.get().createQuery(query);
//       q.setLong("idKandungan", idKandungan);
//       return (PermohonanKertasKandungan) q.uniqueResult();
//   }

    /*
     * DESIGN ONLY FOR ULASAN JABATAN TEKNIKAL AND ADUN
     * AUTHOR WITH CARE
     */
    public List<PermohonanRujukanLuar> senaraiPermohonanRujLuarByIdPermohonan(String idPermohonan) {

        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and lt.agensi.kod is not null");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by murali
    public Pemohon findPemohonPihak(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public HakmilikPermohonan findMohonHakmilik(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        return (HakmilikPermohonan) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public Pemohon findPermohonanByIdMohonNKodPihak(String idPermohonan, String kodPihak) {
        String query = "SELECT b FROM etanah.model.Pemohon b where b.permohonan.idPermohonan = :idPermohonan and b.jenis.kod = :kodPihak";
        return (Pemohon) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("kodPihak", kodPihak).uniqueResult();
    }

    public PermohonanRujukanLuar findIDPermohonanRujByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).uniqueResult();
    }

    public PermohonanRujukanLuar findIDPermohonanRujByIdPermohonanNKodAgensi(String idPermohonan, String kodAgensi) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and b.agensi.kod =:kodAgensi";
        return (PermohonanRujukanLuar) sessionProvider.get().createQuery(query).setString("idPermohonan", idPermohonan).setString("kodAgensi", kodAgensi).uniqueResult();
    }

//    public PermohonanKertas findKertasByKod(String idPermohonan, String kod) {
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
//        q.setString("kod", kod);
//        q.setString("idPermohonan", idPermohonan);
//        return (PermohonanKertas) q.uniqueResult();
//    }
//    public List<PermohonanKertasKandungan> findByIdLapor(long idKertas, int bil) {
//        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setLong("idKertas", idKertas);
//        q.setInteger("bil", bil);
//        return q.list();
//    }
//    public PermohonanKertasKandungan findkandunganByIdKandungan(Long idKandungan) {
//        String query = "Select p FROM etanah.model.PermohonanKertasKandungan p WHERE p.idKandungan = :idKandungan";
//        Query q = sessionProvider.get().createQuery(query);
//        q.setLong("idKandungan", idKandungan);
//        return (PermohonanKertasKandungan) q.uniqueResult();
//    }
    public PermohonanTuntutanKos findMohontuntutkos(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohontuntutkosByIdMohonAndKodTuntut(String idPermohonan, String kodTuntut) {
        LOG.info(idPermohonan);
        LOG.info(kodTuntut);
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<KodSyaratNyata> searchKodSyaratNyata(String kod, String kod_caw, String syaratNyata) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw ";

            if (kod != null) {
                query += "AND u.kodSyarat LIKE :kod ";
            }

            if (syaratNyata != null) {
                query += "AND lower(u.syarat) LIKE :syaratNyata ";
            }
            Query q = sessionProvider.get().createQuery(query);

            q.setString("kod_caw", kod_caw);

            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }

            if (syaratNyata != null) {
                q.setString("syaratNyata", "%" + syaratNyata.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSyaratNyata> searchKodSyaratNyataPTG(String kod, String syaratNyata) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod = '00' ";

            if (kod != null) {
                query += "AND u.kodSyarat LIKE :kod ";
            }

            if (syaratNyata != null) {
                query += "AND lower(u.syarat) LIKE :syaratNyata ";
            }
            Query q = sessionProvider.get().createQuery(query);

            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }

            if (syaratNyata != null) {
                q.setString("syaratNyata", "%" + syaratNyata.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSyaratNyata> searchKodSyaratNyataOnly(String kodSyaratNyata) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select u from etanah.model.KodSyaratNyata u where u.kod =:kodSyaratNyata");
        q.setString("kodSyaratNyata", kodSyaratNyata);
        return q.list();
    }

    public List<KodSyaratNyata> searchKodSyaratNyataNew(String kod, String kod_caw, String kategoriTanah, String syaratNyata) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSyaratNyata u where u.cawangan.kod =:kod_caw ";

            if (kod != null) {
                query += "AND u.kodSyarat LIKE :kod ";
            }

            if (syaratNyata != null) {
                query += "AND lower(u.syarat) LIKE :syaratNyata ";
            }

            if (kategoriTanah != null) {
                query += "AND kategoriTanah.kod = :kategoriTanah ";
            }
            Query q = sessionProvider.get().createQuery(query);

            q.setString("kod_caw", kod_caw);

            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }

            if (syaratNyata != null) {
                q.setString("syaratNyata", "%" + syaratNyata.toLowerCase() + "%");
            }

            if (kategoriTanah != null) {
                q.setString("kategoriTanah", kategoriTanah);
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSekatanKepentingan> searchKodSekatan(String kod, String kod_caw, String sekatan) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSekatanKepentingan u where u.cawangan.kod = :kod_caw ";

            if (kod != null) {
                query += " AND lower(u.kodSekatan) LIKE :kod ";
            }

            if (sekatan != null) {
                query += " AND u.sekatan LIKE :sekatan";
            }

            Query q = sessionProvider.get().createQuery(query);
            q.setString("kod_caw", kod_caw);
            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }
            if (sekatan != null) {
                q.setString("sekatan", "%" + sekatan.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodSekatanKepentingan> searchKodSekatanPTG(String kod, String sekatan) {
        try {
//            Session s = sessionProvider.get();
            String query = "Select u from etanah.model.KodSekatanKepentingan u where u.cawangan.kod = '00'";

            if (kod != null) {
                query += " AND lower(u.kodSekatan) LIKE :kod ";
            }

            if (sekatan != null) {
                query += " AND u.sekatan LIKE :sekatan";
            }

            Query q = sessionProvider.get().createQuery(query);
//            q.setString("kod_caw", kod_caw);
            if (kod != null) {
                q.setString("kod", "%" + kod + "%");
            }
            if (sekatan != null) {
                q.setString("sekatan", "%" + sekatan.toLowerCase() + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    //murali
    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi= :kategoriAgensi");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", "JTK");
        return q.list();
    }
    //Shazwan

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUN(String idPermohonan, String kategoriAgensi) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod not in ('6016','6017','6018') ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        return q.list();

    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUN2(String idPermohonan) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public PermohonanRujukanLuar findPermohonanRujLuarByIdPermohonanIdRujKodAgensi(String idPermohonan, String kod) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and lt.agensi.kod = :kod "
                + "and lt.namaAgensi is not null order by lt.idRujukan asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanRujukanLuar findPermohonanRujLuarByIdPermohonanIdRuj(String idPermohonan, String kategoriAgensi, Long idRujukan, String kod) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod not in ('6016','6017','6018') "
                + "and lt.idRujukan = :idRujukan and lt.agensi.kod = :kod order by lt.idRujukan asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        q.setString("kod", kod);
        q.setLong("idRujukan", idRujukan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanKod(String idPermohonan, String kategoriAgensi, String kod) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod not in ('6016','6017','6018') "
                + "and lt.agensi.kod = :kod order by lt.idRujukan asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNamaAgensi(String idPermohonan, String kategoriAgensi, String kodNegeri) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod not in ('6016','6017','6018')"
                + "and lt.namaAgensi is not null order by lt.idRujukan asc ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUN(String idPermohonan, String kategoriAgensi, String kodNegeri) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK - For Melaka
         */
        if (kodNegeri.equals("04")) {
            Session s = sessionProvider.get();
            Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod not in ('6016','6017','6018')"
                    + "order by lt.idRujukan asc ");
            q.setString("idPermohonan", idPermohonan);
            q.setString("kategoriAgensi", kategoriAgensi);
            return q.list();
        } else {
            Session s = sessionProvider.get();
            Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi");
            q.setString("idPermohonan", idPermohonan);
            q.setString("kategoriAgensi", kategoriAgensi);
            return q.list();
        }

    }
    //Shazwan

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUNWithPTD(String idPermohonan, String kategoriAgensi) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi and lt.agensi.kod in ('6016','6017','6018') ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        return q.list();
    }

    public List<Permohonan> findPermohonanByListIDMohon(List<String> listIDMohon) {
        Session s = sessionProvider.get();
        String query = "Select lt from etanah.model.Permohonan lt where lt.idPermohonan in ('";
        int count = 1;
        for (String idMohon : listIDMohon) {
            if (count < listIDMohon.size()) {
                query = query + idMohon + "','";
            } else if (count == listIDMohon.size()) {
                query = query + idMohon + "')";
            }
            count++;
        }
        LOG.info("THIS IS FINDPERMOHONANBYLISTIDMOHON ->" + query);
        Query q = s.createQuery(query);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanNADUNWithPTD2(String idPermohonan, String kategoriAgensi) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and  lt.agensi.kategoriAgensi.kod = :kategoriAgensi  ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kategoriAgensi", kategoriAgensi);
        return q.list();
    }
    //Shazwan

    public List<PermohonanRujukanLuar> findPermohonanRujLuarByIdPermohonanWithPTD(String idPermohonan) {
        /*
         * 6016,6017,6018 is PTD but have kod jtk = JTK
         */
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanRujukanLuar lt where lt.permohonan.idPermohonan = :idPermohonan and lt.agensi.kod in ('6016','6017','6018') ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //laporantanah

    public HakmilikPermohonan findByIdHakmilikIdPermohonan(String idPermohonan, String idHakmilik) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.hakmilik.idHakmilik = :idHakmilik and p.luasTerlibat is not null";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdHakmilikIdPermohonanList(String idPermohonan) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("idHakmilik", idHakmilik);
//        return q.list();
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findByIdbyBil(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<Permohonan> findAllSDPByDIS() {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan LIKE :idMohon and b.status = :sts";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", "%DIS%");
        q.setString("sts", "SD");
        return q.list();
    }

    public List<Permohonan> findAllKelompokGRP() {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan LIKE :idMohon and b.status not in ('SD') order by b.idPermohonan DESC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", "%GRP%");
        return q.list();
    }

    public List<Permohonan> findAllByUrusanPJBTR(String daerah) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.kod = :kod and b.cawangan.kod = :daerah";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", "PJBTR");
        q.setString("daerah", daerah);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByIdbysubtajuk(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", "2.1.%");
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByIdbyBilExcludeSubtajuk(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk != :subTajuk order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk);
        return q.list();
    }

    public PermohonanLaporanKawasan findByidMohon(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

    public PermohonanLaporanKawasan findByidMohonList(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        if (!q.list().isEmpty()) {
            return (PermohonanLaporanKawasan) q.list().get(0);
        } else {
            return (PermohonanLaporanKawasan) q.uniqueResult();
//             return (PermohonanLaporanPelan) q.list().get(0);//data xunique-simulasi
        }
    }

    public PermohonanLaporanKawasan findByidMohonKodRizab(String idPermohonan, String kodRizab) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanKawasan h where h.permohonan = :idPermohonan AND h.kodRizab.kod = :kodRizab";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodRizab", kodRizab);

        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

//Borang 5A Formula Generation
    public KodKadarPremium findKodKadarPremiumId(String kodTanah, String kodKegunaanTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.KodKadarPremium p where p.kodTanah.kod = :kodTanah AND p.kodKegunaanTanah.kod = :kodKegunaanTanah");
        q.setString("kodTanah", kodTanah);
        q.setString("kodKegunaanTanah", kodKegunaanTanah);
        return (KodKadarPremium) q.uniqueResult();
    }

    public NoPt getMaxPTByIdPermohonan(int kodBpm, long startRange, long endRange) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.noPt=(Select MAX(lt1.noPt) from etanah.model.NoPt lt1 where lt1.kodBpm = " + kodBpm + " AND lt1.noPt >= :startRange AND lt1.noPt < :endRange)");
//         q.setInteger("kodBpm", kodBpm);
        q.setLong("startRange", startRange);
        q.setLong("endRange", endRange);
        return (NoPt) q.uniqueResult();
    }

    // Jana noPT
    public List<NoPt> getNoPTByIdPermohonan(String idPermohonan, int kodBpm) {
        System.out.println("idPermohonan:" + idPermohonan);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.hakmilikPermohonan.permohonan.idPermohonan = :idPermohonan AND lt.kodBpm.kod = :kodBpm ");
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("kodBpm", kodBpm);
        return q.list();
    }

    public SiriNoPt findSiriNoPtByKodBpm(int kodBpm) {
        System.out.println(" -----findSiriNoPtByKodBpm-------");
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.SiriNoPt lt where lt.kodBandarPekanMukim.kod = :kodBpm");
        q.setInteger("kodBpm", kodBpm);
        return (SiriNoPt) q.uniqueResult();
    }

    public LanjutanBayaran findLanjutBayaranByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select a from etanah.model.LanjutanBayaran a where a.permohonan.idPermohonan =:idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LanjutanBayaran) q.uniqueResult();
    }

    @Transactional
    public void simpanLanjutanBayaran(LanjutanBayaran lanjutanBayaran) {
        lanjutanBayaranDAO.save(lanjutanBayaran);
    }

    @Transactional
    public void simpanSiriNoPt(SiriNoPt siriNoPt) {
        siriNoPtDAO.saveOrUpdate(siriNoPt);
    }

    @Transactional
    public void simpanNoPt(NoPt noPt) {
        noPtDAO.saveOrUpdate(noPt);
    }

    @Transactional
    public void simpanSaveOnlyNoPt(NoPt noPt) {
        noPtDAO.save(noPt);
    }

    public NoPt findNoPTByIdMH(long id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.hakmilikPermohonan.id = :id");
        q.setLong("id", id);
        return (NoPt) q.uniqueResult();
    }

    public NoPt findNoPTByNoPtSementara(long noPtSementara) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.noPtSementara = :noPtSementara");
        q.setLong("noPtSementara", noPtSementara);
        return (NoPt) q.uniqueResult();
    }

    public List<KodKadarPremium> findKodKadarPremiumNama(String kodTanah, String kodKegunaanTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.KodKadarPremium p where p.kodTanah.kod = :kodTanah AND p.kodKegunaanTanah.kod = :kodKegunaanTanah");
        q.setString("kodTanah", kodTanah);
        q.setString("kodKegunaanTanah", kodKegunaanTanah);
//       // return (KodKadarPremium) q.uniqueResult();
        return q.list();
    }

    public List<String> findDistinctKodKadarPremiumNama() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select distinct p.nama from etanah.model.KodKadarPremium p");
//       // return (KodKadarPremium) q.uniqueResult();
        return q.list();
    }

    public List<KodKadarPremium> findAllKodKadarPremium() {
        return kodKadarPremiumDAO.findAll();
    }

    public PermohonanPlotPelan findByPermohonanPlotPelan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPlotPelan p where p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPlotPelan) q.uniqueResult();

    }

    public PermohonanPlotPelan findByPermohonanPlotPelanByIDMH(Long idMH) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanPlotPelan p where p.hakmilikPermohonan.id = :idMH");
        q.setLong("idMH", idMH);
        return (PermohonanPlotPelan) q.uniqueResult();

    }

    // added by murali 12/05/2011
    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<LaporanTanah> findLaporanTanahByIdPermohonanList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public LaporanTanah findLaporanTanahByIdMH(long idMH) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.hakmilikPermohonan.id = :idMH";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdMHNotNull(long idMH) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.hakmilikPermohonan.id = :idMH and b.hakmilikPermohonan.id is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMH", idMH);
        return (LaporanTanah) q.uniqueResult();
    }

    //added by murali 16/05/2011
    public PemohonHubungan findHubunganByIDIsteri(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan ='ISTERI'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return (PemohonHubungan) q.uniqueResult();
    }

    public PemohonHubungan findHubunganByIDSuamiIsteri(Long idPemohon) {
        String query = "SELECT b FROM etanah.model.PemohonHubungan b where b.pemohon.idPemohon = :idPemohon and b.kaitan in ('SUAMI','ISTERI')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idPemohon", idPemohon);
        return (PemohonHubungan) q.uniqueResult();
    }

    //added by murali 13/05/2011
    public List<PermohonanKertasKandungan> findBySubtajuk(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", "5.1.%");
        return q.list();
    }

    //added by Murali 24/05/2011
    public PermohonanTuntutanBayar findMohonTuntutBayar(Long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos = :idKos";
        return (PermohonanTuntutanBayar) sessionProvider.get().createQuery(query).setLong("idKos", idKos).uniqueResult();
    }
    //Added by Shazwan 15/06/2011

    public List<PermohonanKertasKandungan> findByidKertasNSubtajukNBil(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk order by b.subtajuk";
        //NOTE : SubTajuk data example: 5.1.%
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk);
        return q.list();
    }
    //Added by Shazwan

    public List<PermohonanKertasKandungan> findByIdKertasOnly(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas");
        q.setLong("idKertas", idKertas);
        return q.list();
    }
    //Added by Shazwan

    public List<PermohonanKertasKandungan> findByIdKertasOnlyWithOrderByBil(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.kertas.idKertas = :idKertas order by p.bil ASC");
        q.setLong("idKertas", idKertas);
        return q.list();
    }
//     public List<PermohonanKertasKandungan> findByIdKertas2(String idPermohonan) {
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("Select p from etanah.model.PermohonanKertasKandungan p where p.permohonan.idPermohonan = :idPermohonan");
//        q.setString("idPermohonan", idPermohonan);
//        return q.list();
//    }
    //Add by Shah

    public List<KodPeranan> getSenaraiKodPerananByID(String id) {
        String query = "SELECT b FROM etanah.model.KodPeranan b where b.kod =:id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("id", id);
        return q.list();
    }

    // added by murali 24/05/2011
    public Permit getMaxNoPermit() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Permit lt");
        List<Permit> permitList = new ArrayList<Permit>();
        permitList = q.list();
        if (permitList.size() == 0) {
            return null;
        } else {
            Query q1 = s.createQuery("Select lt from etanah.model.Permit lt where lt.noPermit=(Select MAX(lt1.noPermit) from etanah.model.Permit lt1)");
            return (Permit) q1.uniqueResult();
        }
    }
    //Add by Shah

    public Permit getMaxOfNoPermit() {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit =(select max (cast(p.noPermit as int)) from etanah.model.Permit p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Permit) q.uniqueResult();
    }

    public Permit getMaxOfNoPermit2() {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit =(select max (p.noPermit) from etanah.model.Permit p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (Permit) q.uniqueResult();
    }

    public NoPt getMaxOfNoPT(int kodBPM) {
        String query = "SELECT distinct b FROM etanah.model.NoPt b where b.noPt =(select max (p.noPt) from etanah.model.NoPt p) and b.kodBpm.kod = :kodBPM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kodBPM", kodBPM);
        return (NoPt) q.uniqueResult();
    }

    public NoPt getMaxOfNoPTSementara(int kodBPM) {
        String query = "SELECT distinct b FROM etanah.model.NoPt b where b.noPtSementara =(select max (p.noPtSementara) from etanah.model.NoPt p) and b.kodBpm.kod = :kodBPM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kodBPM", kodBPM);
        return (NoPt) q.uniqueResult();
    }

    public Long getMaxOfNoPTNew(int kodBPM) {
        String query = "SELECT distinct max(b.noPt) FROM etanah.model.NoPt b where b.kodBpm.kod = :kodBPM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kodBPM", kodBPM);
        return (Long) q.uniqueResult();
    }

    public Long getMaxOfNoPTSementaraNew(int kodBPM) {
        String query = "SELECT distinct max(b.noPtSementara) FROM etanah.model.NoPt b where b.kodBpm.kod = :kodBPM";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("kodBPM", kodBPM);
        return (Long) q.uniqueResult();
    }
//Add by Shazwan

    public Permit getMaxOfNoPermitByJenisPermit(String jenisPermit) {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit =(select max (p.noPermit) from etanah.model.Permit p where p.kodJenisPermit.kod = :jenisPermit)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("jenisPermit", jenisPermit);
        return (Permit) q.uniqueResult();
    }

    public Permit getMaxOfNoPermitByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permit b where b.noPermit =(select max (p.noPermit) from etanah.model.Permit p where p.permohonan.idPermohonan = :idPermohonan)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Permit) q.uniqueResult();
    }

    public PermitSahLaku getMaxOfNoSiri() {
        String query = "SELECT b FROM etanah.model.PermitSahLaku b where b.noSiri =(select max (p.noSiri) from etanah.model.PermitSahLaku p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (PermitSahLaku) q.uniqueResult();
    }

//added by murali 14/06/2011
    @Transactional
    public void simpanPermohonanManual(PermohonanManual permohonanManual) {
        permohonanManualDAO.saveOrUpdate(permohonanManual);

    }

    public PermohonanManual findByIdMohonFailNo(String idPermohonan, String noFail) {
        String query = "SELECT b FROM etanah.model.PermohonanManual b where b.idPermohonan.idPermohonan = :idPermohonan and b.noFail = :noFail";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noFail", noFail);
        return (PermohonanManual) q.uniqueResult();
    }

    public PermohonanManual findByidMohonManual(long idMohonManual) {
        String query = "SELECT b FROM etanah.model.PermohonanManual b where b.idMohonManual = :idMohonManual";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idMohonManual", idMohonManual);
        return (PermohonanManual) q.uniqueResult();
    }

    public List<PermohonanManual> findByIdMohonlist(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanManual b where b.idPermohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanManual> findByIdMohonlikeDIS(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanManual b where b.idPermohonan.idPermohonan = :idPermohonan and b.noFail not like '%DIS%'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by murali 15/06/2011
    @Transactional
    public void deletePermohonanManual(PermohonanManual permohonanManual) {
        permohonanManualDAO.delete(permohonanManual);
    }

    @Transactional
    public void deletePermohonanLaporanKwsn(PermohonanLaporanKawasan permohonanLaporKwsn) {
        permohonanLaporanKawasanDAO.delete(permohonanLaporKwsn);
    }

    @Transactional
    public void savePermohonanLaporanKwsn(PermohonanLaporanKawasan permohonanLaporKwsn) {
        permohonanLaporanKawasanDAO.save(permohonanLaporKwsn);
    }
    //added by murali 17/06/2011

    @Transactional
    public void simpanPermohonanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    public PermohonanLaporanUlasan findByIdMohonIdLapor(String idPermohonan, long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idLaporan", idLaporan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    //added by murali 29/06/2011
    public List<String> getSenaraiKodKadarPremiumDistinctNama() {
        System.out.println(" -----SenariKodKadarPremium in Service Class-------");
        String query = "select distinct p.nama from etanah.model.KodKadarPremium p";
        System.out.println(" -----SenariKodKadarPremium in Service Class Query-------" + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public InfoAudit getInfoAudit(Pengguna peng) {
        InfoAudit audit = new InfoAudit();
        audit.setDimasukOleh(peng);
        audit.setTarikhMasuk(new java.util.Date());
        return audit;
    }

    //added by Murali 04/07/2011
    public PermohonanLaporanUlasan findByIdMohonByJenisUlasan(String idPermohonan, String jenisUlasan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.jenisUlasan =:jenisUlasan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    //Added by afham
    public List<PermohonanTuntutanBayar> findPermohonanTuntutanBayarByIdKos(Long idKos) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanBayar b where b.permohonanTuntutanKos.idKos =  :idKos";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKos", idKos);
        return q.list();
    }

    //Added By Murali 07-07-2011
    @Transactional
    public void simpanPermohonanLaporanKandungan(PermohonanLaporanKandungan permohonanLaporanKandungan) {
        permohonanLaporanKandunganDAO.saveOrUpdate(permohonanLaporanKandungan);
    }

    public PermohonanLaporanKandungan findByIdLaporSubtajuk(String subtajuk, long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanKandungan b where b.subtajuk = :subtajuk and b.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("subtajuk", subtajuk);
        q.setLong("idLaporan", idLaporan);
        return (PermohonanLaporanKandungan) q.uniqueResult();
    }

    public UlasanJabatanTeknikal findUlasanJabatanTeknikalAdun(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.permohonan.idPermohonan = :idPermohonan and (b.kodAgensi.kategoriAgensi.kod ='ADN')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (UlasanJabatanTeknikal) q.uniqueResult();
    }

    public List<UlasanJabatanTeknikal> findUlasanJabatanTeknikalAdunList(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.permohonan.idPermohonan = :idPermohonan and (b.kodAgensi.kategoriAgensi.kod ='ADN')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by murali 8-07-2011
    public List<PermohonanLaporanUlasan> findUlasan(String idPermohonan, String jenisUlasan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.jenisUlasan =:jenisUlasan order by b.idLaporUlas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> findUlasanByJenisLaporan(String idPermohonan, String jenisUlasan, String jenisLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.jenisUlasan =:jenisUlasan and b.jenisLaporan = :jenisLaporan order by b.idLaporUlas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisUlasan", jenisUlasan);
        q.setString("jenisLaporan", jenisLaporan);
        return q.list();
    }

    public List<UlasanJabatanTeknikal> findUlasanJabatanTeknikal(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.UlasanJabatanTeknikal b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> findUlasanByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanLaporanUlasan> findUlasanByIdMohonAndJenisLaporan(String idPermohonan, String jenisLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.jenisLaporan = :jenisLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        return q.list();
    }

    public List<PermohonanKelompok> findMohonKelompokByIdMohonInduk(String idMohonInduk) {
        String query = "SELECT b FROM etanah.model.PermohonanKelompok b where b.permohonanInduk.idPermohonan = :idMohonInduk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonInduk", idMohonInduk);
        return q.list();
    }

    public List<PermohonanKelompok> findMohonKelompokByIdMohonIndukJenisKelompok(String idMohonInduk, String jenisKelopok) {
        String query = "SELECT b FROM etanah.model.PermohonanKelompok b where b.permohonanInduk.idPermohonan = :idMohonInduk and b.jenisKelopok = :jenisKelopok";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohonInduk", idMohonInduk);
        q.setString("jenisKelopok", jenisKelopok);
        return q.list();
    }

    public List<PermohonanKelompok> findMohonKelompokByIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanKelompok b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public PermohonanKelompok findMohonKelompokByIdMohon1(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanKelompok b where b.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return (PermohonanKelompok) q.uniqueResult();
    }

    public List<PermohonanLaporanUlasan> findJenisUlasan(String idPermohonan, String jenisUlasan, String ulasan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUlasan b where b.permohonan.idPermohonan = :idPermohonan and b.jenisUlasan =:jenisUlasan and b.ulasan =:ulasan order by b.idLaporUlas";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("ulasan", ulasan);
        q.setString("jenisUlasan", jenisUlasan);
        return q.list();
    }

    @Transactional
    public void deletePermohonanLaporanUlasan(PermohonanLaporanUlasan pkk) {
        permohonanLaporanUlasanDAO.delete(pkk);
    }

    public List<HakmilikPerbicaraan> findHakmilikPerbicaraanByIdMHList(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH ");
        q.setParameter("idMH", idMH);
        return q.list();
    }

    public List<PerbicaraanKehadiran> findPerbicaraanKehadiranByIdBicaraList(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m WHERE m.perbicaraan.idPerbicaraan = :idBicara ");
        q.setParameter("idBicara", idBicara);
        return q.list();
    }

    public PerbicaraanKehadiran findPerbicaraanKehadiranByIdBicara(long idBicara) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m WHERE m.perbicaraan.idPerbicaraan = :idBicara ");
        q.setParameter("idBicara", idBicara);
        return (PerbicaraanKehadiran) q.uniqueResult();
    }

    public HakmilikUrusan findHakmilikUrusanByIdMohon(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.HakmilikUrusan m WHERE m.idPerserahan = :idPermohonan ");
        q.setParameter("idPermohonan", idPermohonan);
        return (HakmilikUrusan) q.uniqueResult();
    }

    //added by murali 11-07-2011
    public List<PermohonanLaporanUlasan> getLaporUlasanByIdMohonidLaporan(String idPermohonan, long idLaporan) {
        String strQuery = "Select d from etanah.model.PermohonanLaporanUlasan d where d.permohonan.idPermohonan = :idPermohonan and d.laporanTanah.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("idLaporan", idLaporan);
        // return (PermohonanLaporanUlasan)q.uniqueResult();
        return q.list();
    }

    public List<PermohonanLaporanKawasan> findLaporanKawasanByIdPermohonanNKodRizab(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanLaporanKawasan m WHERE m.permohonan.idPermohonan = :idMohon");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    public List<PermohonanLaporanKawasan> findLaporanKawasanByIdPermohonanNidMH(String idMohon, long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanLaporanKawasan m WHERE m.permohonan.idPermohonan = :idMohon and m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMohon", idMohon);
        q.setLong("idMH", idMH);
        return q.list();
    }

    public PermohonanLaporanKawasan findLaporanKawasanByIdPermohonanNidMHSingle(String idMohon, long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanLaporanKawasan m WHERE m.permohonan.idPermohonan = :idMohon and m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMohon", idMohon);
        q.setLong("idMH", idMH);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

    public PermohonanLaporanKawasan findLaporanKawasanByIdPermohonan(String idMohon) {
        String query = "SELECT m FROM etanah.model.PermohonanLaporanKawasan m WHERE m.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idMohon", idMohon);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }

    public PermohonanLaporanKawasan findLaporanKawasanByIdPermohonanNidMH1(long mohonLaporWksn, String idMohon) {
        String query = "SELECT m FROM etanah.model.PermohonanLaporanKawasan m WHERE m.idMohonlaporKws = :mohonLaporWksn and m.permohonan.idPermohonan = :idMohon";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("mohonLaporWksn", mohonLaporWksn);
        q.setParameter("idMohon", idMohon);
        return (PermohonanLaporanKawasan) q.uniqueResult();
    }
    //Add for akaun amanah

    public Akaun findByIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.Akaun b where b.permohonan.idPermohonan = :idMohon ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return (Akaun) q.uniqueResult();
    }

    public Permohonan findPermohonanBySebabForGSA(String sbb) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.sebab LIKE :sbb and (b.kodUrusan.kod = 'PBGSA' or b.kodUrusan.kod = 'PWGSA' or b.kodUrusan.kod = 'PTGSA') ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("sbb", "%" + sbb + "%");
        return (Permohonan) q.uniqueResult();
    }

    public Permohonan findPermohonanBySebabForGSAIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan and (b.kodUrusan.kod = 'PBGSA' or b.kodUrusan.kod = 'PWGSA' or b.kodUrusan.kod = 'PTGSA')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }
    //Add for jadual tanah berkelompok 

    public List<Hakmilik> searchHakmilikByNoLot(String idHakmilik, String noLot, String sbb) {
        try {
            String query = "Select hm from etanah.model.Hakmilik where hm.idHakmilik = :idHakmilik";

            if (noLot != null) {
                query += " AND u.kod LIKE :noLot ";
            }
            if (noLot != null && sbb != null) {
                if ((noLot != null) && (sbb != null)) {
                    query += " AND ";
                }
            }
            if (sbb != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

            if (noLot != null) {
                q.setString("noLot", noLot + "%");
            }

            if (sbb != null) {
                q.setString("kodAgensiNama", "%" + sbb + "%");
            }
            q.setString("idHakmilik", idHakmilik);

            return q.list();
        } finally {
            //session.close();
        }
    }
    //Add for jabatan teknikal 

    public KodDUN searchAllDUN(KodAgensi kod) {
        String query = "SELECT b FROM etanah.model.KodDUN b AND b.kodAgensi = :kod ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kod", kod.getKod());
        return (KodDUN) q.uniqueResult();
    }

    public List<KodAgensi> searchKodAgensiJTK(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "upper(u.nama) LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama.toUpperCase() + "%"); //Case insensitive searching in Oracle
            }

            return q.list();
        } finally {
            //session.close();
        }
    }
    
     public List<KodAgensi> searchKodAgensiADNActive(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND kodNegeri.kod =:kodNegeri And u.aktif = 'Y'";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchKodAgensiADN(String kod, String kodAgensiNama, String kodNegeri) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<KodAgensi> searchMohon_RujDok(String kod, String kodAgensiNama, String kodNegeri) { //NOTE FOR MOHON_RUJ_DOKUMEN

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND kodNegeri.kod =:kodNegeri";

            if (kod != null) {
                query += " AND u.kod LIKE :kod ";
            }
            if ((kod != null) && (kodAgensiNama != null)) {
                query += " AND ";
            }
            if (kodAgensiNama != null) {
                query += "u.nama LIKE :kodAgensiNama ";
            }
            Query q = sessionProvider.get().createQuery(query);

//            q.setString("kod_caw", kod_caw);
            q.setString("kodNegeri", kodNegeri);
            if (kod != null) {
                q.setString("kod", kod + "%");
            }

            if (kodAgensiNama != null) {
                q.setString("kodAgensiNama", "%" + kodAgensiNama + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Pengguna> findPenggunaByBPEL(List<String> bpelName, String kodCaw) {
        String query = "Select u from etanah.model.PenggunaPeranan u, etanah.model.Pengguna p, etanah.model.KodPeranan kp WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
        }
        query = query + " and u.pengguna.status.kod = 'A' and u.pengguna.kodCawangan.kod = '" + kodCaw + "'"
                + " and u.peranan.kod = p.perananUtama.kod and u.peranan.kod = kp.kod and u.pengguna.idPengguna = p.idPengguna";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();

        LOG.info("listpp - " + listPp.size());
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    public List<Pengguna> findPenggunaByBPELAgihanSemula(String bpelName, String kodCaw) {
        String query = "Select distinct u from etanah.model.PenggunaPeranan u WHERE u.peranan = '" + bpelName + "'";
        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "' and u.pengguna.status.kod = 'A'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> findPenggunaByBPELAgihanSemulaSBMS(String bpelName, String kodCaw) {
        String query = "Select  u.pengguna from etanah.model.PenggunaPeranan u  WHERE u.peranan.kumpBPEL = '" + bpelName + "'";
        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "' and u.pengguna.status.kod = 'A'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> findPenggunaByBPELPLPS(List<String> bpelName, String kodCaw, String kodJabatan, String kodPeranan) {
        String query = "Select u from etanah.model.PenggunaPeranan u WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
        }
        query = query + " and u.pengguna.kodCawangan.kod = '" + kodCaw + "'";
        //query = query + " and u.pengguna.kodJabatan.kod = '" + kodJabatan + "'";
        //query = query + " and u.pengguna.perananUtama.kod = '" + kodPeranan + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

//    and u.peranan.kod = p.perananUtama.kod
    public List<Pengguna> findPenggunaByBPEL831A(List<String> bpelName, String kodCaw) {
        String query = "Select u from etanah.model.PenggunaPeranan u, etanah.model.Pengguna p, etanah.model.KodPeranan kp WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.peranan.kumpBPEL ='" + s + "'";
            }
        }
        query = query + " and u.pengguna.status.kod = 'A' and u.pengguna.kodCawangan.kod = '" + kodCaw + "'"
                + " and u.peranan.kod = kp.kod and u.pengguna.idPengguna = p.idPengguna";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();

        LOG.info("listpp - " + listPp.size());
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    public List<Pengguna> findPenggunaByBPELTesting(List<String> bpelName, String kodCaw) {
        String query = "Select distinct u from etanah.model.Pengguna u WHERE ";
        int count = 0;
        for (String s : bpelName) {
            if (count == 0) {
                query = query + "u.senaraiPeranan.peranan.kumpBPEL ='" + s + "'";
            } else {
                query = query + " or u.senaraiPeranan.peranan.kumpBPEL ='" + s + "'";
            }
        }
        query = query + " and u.kodCawangan.kod = '" + kodCaw + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPengguna = q.list();
//        for (PenggunaPeranan pp : listPp) {
//            listPengguna.add(pp.getPengguna());
//        }
        return listPengguna;
    }

    public Pengguna findPenggunaByIdUser(String userId) {
        String query = "Select distinct u from etanah.model.Pengguna u WHERE ";
        query = query + " u.idPengguna = '" + userId + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPengguna = q.list();
        return listPengguna.get(0);
    }

    public List<Hakmilik> findHakmilikByNoLotORNoHMOrIDHM(String noLot, String noHM, String idHakmilik) {
        String query = "Select u from etanah.model.Hakmilik u WHERE ";
        boolean checkIDHM = false;
        if (!StringUtils.isEmpty(idHakmilik)) {
            query = query + "u.idHakmilik ='" + idHakmilik + "'";
            checkIDHM = true;
        }
        if (!checkIDHM) {
            boolean checkNoLot = false;
            if (!StringUtils.isEmpty(noLot)) {
                query = query + "u.noLot ='" + noLot + "'";
                checkNoLot = true;
            }
            if (!StringUtils.isEmpty(noHM)) {
                if (checkNoLot) {
                    query = query + " AND u.noHakmilik ='" + noHM + "'";
                } else {
                    query = query + " u.noHakmilik ='" + noHM + "'";
                }
            }
        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Hakmilik> searchHakmilik(String kodDaerah, int kodBPM, String kodSeksyen, String noLot,
            String idHakmilik) {
        try {
//            Session s = sessionProvider.get();
            LOG.debug("in searchHakmilik");
            String query = "Select u from etanah.model.Hakmilik u where";

            if (StringUtils.isNotBlank(kodDaerah)) {
                if (!kodDaerah.equals("00")) {
                    query += " u.daerah.kod = :kodDaerah";
                } else {
                    query += " u.daerah.kod in ('00','01','02','03')";
                }
            }

            if (kodBPM != 0) {
                query += " AND u.bandarPekanMukim.kod = :kodBPM";
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                query += " AND u.seksyen.kod =:kodSeksyen";
            } else {
                query += " AND u.seksyen is null";
            }

            if (StringUtils.isNotBlank(idHakmilik)) {
                query += " AND u.idHakmilik LIKE :idHakmilik";
            }

            if (StringUtils.isNotBlank(noLot)) {
                query += " AND u.noLot =:noLot";
            }

            LOG.debug("query :" + query);

            Query q = sessionProvider.get().createQuery(query);

            if (StringUtils.isNotBlank(kodDaerah)) {
                if (!kodDaerah.equals("00")) {
                    q.setString("kodDaerah", kodDaerah);
                }
            }

            if (kodBPM != 0) {
                q.setInteger("kodBPM", kodBPM);
            }

            if (StringUtils.isNotBlank(noLot)) {
                q.setString("noLot", noLot);
            }
            if (StringUtils.isNotBlank(idHakmilik)) {
                q.setString("idHakmilik", "%" + idHakmilik + "%");
            }

            if (StringUtils.isNotBlank(kodSeksyen)) {
                q.setString("kodSeksyen", kodSeksyen);
            }
            //return q.list();
            LOG.debug("out searchHakmilik:q.size :" + q.list().size());
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<PermohonanKertasKandungan> findByIdKertasNbilforParent(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.bilSebelum IS NULL order by b.subtajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findByIdKertasNbilforChild(long idKertas, int bil, String subtajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk =:subtajuk and b.bilSebelum IS NOT NULL order by b.bilSebelum ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subtajuk", subtajuk);
        return q.list();
    }

    public PermohonanKertasKandungan findByBilNIdKertasNSubtajukNBilSblm(int bil, Long idKertas, String subtajuk, int bilSebelum) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil and b.kertas.idKertas = :idKertas and b.subtajuk = :subtajuk and b.bilSebelum = :bilSebelum";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("bil", bil);
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", subtajuk);
        q.setInteger("bilSebelum", bilSebelum);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findByBilNIdKertasNSubtajuk(int bil, Long idKertas, String subtajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.bil = :bil and b.kertas.idKertas = :idKertas and b.subtajuk = :subtajuk and b.bilSebelum IS NULL";

        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setInteger("bil", bil);
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", subtajuk);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanFasaGis findMohonFasaGISByIdMohon(String idMohon) {
        String query = "SELECT b FROM etanah.model.PermohonanFasaGis b where b.id_mohon = :idMohon ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idMohon", idMohon);
        return (PermohonanFasaGis) q.uniqueResult();
    }

    public List<DokumenKewanganBayaran> findDokKewBayarByKewDok(String dokKew) {
        String query = "SELECT b FROM etanah.model.DokumenKewanganBayaran b where b.dokumenKewangan.idDokumenKewangan = :dokKew";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("dokKew", dokKew);
        return q.list();
    }

    public CaraBayaran findCaraBayarByKewDok(long idCaraBayar) {
        String query = "SELECT b FROM etanah.model.CaraBayaran b where b.idCaraBayaran = :idCaraBayar ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idCaraBayar", idCaraBayar);
        return (CaraBayaran) q.uniqueResult();
    }

    public List<KodDokumenAgensi> findKodDokAgensiByKodUrusan(String kodUrusan) {
        String query = "SELECT b FROM etanah.model.KodDokumenAgensi b where b.kodUrusanAgensi.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public KodUrusanAgensi findKodAgensiByKodUrusan(String kodUrusan) {
        String query = "SELECT b FROM etanah.model.KodUrusanAgensi b where b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return (KodUrusanAgensi) q.uniqueResult();
    }

    public List<KodDokumenAgensi> findKodDokAgensiByKodUrusanNAgensi(String kodUrusan, String kodAgensi) {
        String query = "SELECT b FROM etanah.model.KodDokumenAgensi b where b.kodUrusanAgensi.kodUrusan.kod = :kodUrusan and b.kodUrusanAgensi.kodAgensi.kod = :kodAgensi";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        q.setString("kodAgensi", kodAgensi);
        return q.list();
    }

    public List<KodUrusanAgensi> findKodUrusanAgensiByKodUrusan(String kodUrusan) {
        String query = "SELECT b FROM etanah.model.KodUrusanAgensi b where b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<KodUrusan> findKodUrusanByJabatan(String kodJabatan) {
        String query = "SELECT b FROM etanah.model.KodUrusan b where b.jabatan.kod = :kodJabatan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodJabatan", kodJabatan);
        return q.list();
    }

    public List<Permohonan> findMohonByJabatanNKodUrusan(String kodJabatan) {
//        String query = "SELECT b FROM etanah.model.Permohonan b, etanah.model.KodUrusan c where c.jabatan.kod = :kodJabatan and b.kodUrusan.kod = c.kod and b.status = 'AK'";
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.jabatan.kod = :kodJabatan and b.kodUrusan.kod = :kodUrusan and b.status = 'AK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodJabatan", kodJabatan);
        q.setString("kodUrusan", "PBMT");
        return q.list();
    }

    public List<AmbilPampasan> getAmbilPampasanListByidHadir(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return q.list();
    }

    @Transactional
    public void deleteAll(Permohonan permohonan) {
        permohonanDAO.delete(permohonan);
    }

    public PerbicaraanKehadiran getPerbicaraanKehadiranByidMPidBicara(long idPermohonanPihak, long idPerbicaraan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.PerbicaraanKehadiran m where m.pihak.idPermohonanPihak = :idPermohonanPihak and m.perbicaraan.idPerbicaraan = :idPerbicaraan");
        q.setLong("idPermohonanPihak", idPermohonanPihak);
        q.setLong("idPerbicaraan", idPerbicaraan);
        return (PerbicaraanKehadiran) q.uniqueResult();

    }

    @Transactional
    public void simpanAmbilBicaraHadir(PerbicaraanKehadiran perbicaraanKehadiran) {
        perbicaraanKehadiranDAO.saveOrUpdate(perbicaraanKehadiran);
    }

    public AmbilPampasan getAmbilPampasan(long idKehadiran) {
        String query = "SELECT b FROM etanah.model.AmbilPampasan b WHERE b.perbicaraanKehadiran.idKehadiran = :idKehadiran";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idKehadiran", idKehadiran);
        return (AmbilPampasan) q.uniqueResult();
    }

    @Transactional
    public void simpanAmbilPampasan(AmbilPampasan ambilPampasan) {
        ambilPampasanDAO.saveOrUpdate(ambilPampasan);
    }

    // Testing for maintainance of kod
    public List<SenaraiKodRujukan> searchKodRujukan(String namaJadual, String namaSbnrJadual) { //use by pelupus :@mr5rule

        try {
            String query = "Select u from etanah.model.SenaraiKodRujukan u WHERE ";

            if (namaJadual != null) {
                query += "u.nama_jadual LIKE :namaJadual ";
            }
            if ((namaJadual != null) && (namaSbnrJadual != null)) {
                query += " AND ";
            }
            if (namaSbnrJadual != null) {
                query += "u.nama LIKE :namaSbnrJadual ";
            }
            Query q = sessionProvider.get().createQuery(query);

            if (namaJadual != null) {
                q.setString("namaJadual", namaJadual + "%");
            }

            if (namaSbnrJadual != null) {
                q.setString("namaSbnrJadual", "%" + namaSbnrJadual + "%");
            }

            return q.list();
        } finally {
            //session.close();
        }
    }

    public TanahRizabPermohonan findTanahRizabByIdPermohonanNnoHM(String idPermohonan, String noHM) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.TanahRizabPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.noHakmilik = :noHM");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noHM", noHM);
        return (TanahRizabPermohonan) q.uniqueResult();
    }

    public List<LaporanTanahSempadan> getListLaporanTanahSempadan(String idPermohonan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery(" SELECT m FROM etanah.model.LaporanTanahSempadan m,  etanah.model.LaporanTanah lt WHERE lt.idLaporan = m.laporanTanah and lt.permohonan.idPermohonan = :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public LaporanTanahSempadan findLaporanTanahSempadanById(Long idLTS) {
        LaporanTanahSempadan laporTanahSempadan = new LaporanTanahSempadan();
        laporTanahSempadan = laporanTanahSempadanDAO.findById(idLTS);
        return laporTanahSempadan;
    }

    public PermohonanLaporanUlasan findPermohonanLaporanUlasanById(Long idPLU) {
        PermohonanLaporanUlasan mohonLaporUlas = new PermohonanLaporanUlasan();
        mohonLaporUlas = permohonanLaporanUlasanDAO.findById(idPLU);
        return mohonLaporUlas;
    }

    public PermohonanLaporanKawasan findPermohonanLaporanKawasanById(Long idPLK) {
        PermohonanLaporanKawasan mohonLaporKawasan = new PermohonanLaporanKawasan();
        mohonLaporKawasan = permohonanLaporanKawasanDAO.findById(idPLK);
        return mohonLaporKawasan;
    }

    public PermohonanLaporanKawasan findPermohonanLaporanKawasanByIdMH(Long id, int kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanLaporanKawasan lt where lt.hakmilikPermohonan.id = :id and lt.kodRizab.kod = :kod");
        q.setLong("id", id);
        q.setInteger("kod", kod);
        return (PermohonanLaporanKawasan) q.uniqueResult();

//        PermohonanLaporanKawasan mohonLaporKawasan = new PermohonanLaporanKawasan();
//        mohonLaporKawasan = permohonanLaporanKawasanDAO.findById(idPLK);
//        return mohonLaporKawasan;
    }

    public PermohonanLaporanUlasan findPermohonanUkurByIdPermohonanTujuan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanLaporanUlasan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.jenisUlasan = 'SPU'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    /**
     * return all slogan
     *
     * @author Mohd Hairudin Mansur
     * @version 07122011
     * @return
     */
    public List<SloganSurat> findAllSlogan() {
        return sloganDAO.findAll();
    }

    /**
     * find slogan surat by id slogan surat
     *
     * @author Mohd Hairudin Mansur
     * @version 08122011
     * @param idSlogan
     * @return
     */
    public SloganSurat findSloganById(long idSlogan) {
        String query = "SELECT b FROM SloganSurat b where b.idSloganSurat = :idSlogan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idSlogan", idSlogan);
        return (SloganSurat) q.uniqueResult();
    }

    /**
     * save or update slogan record
     *
     * @author Mohd Hairudin Mansur
     * @version 09122011
     * @param slogan
     */
    @Transactional
    public void simpanSlogan(SloganSurat slogan) {
        sloganDAO.saveOrUpdate(slogan);
    }

    /*
     * Dynamic Object to find DAO
     */
    public Object findDAOByPK(Object obj, Object val) {
        Object objMain = new Object();
        if (obj instanceof KodJenisPendudukan) {
            objMain = kodJenisPendudukanDAO.findById((String) val);
        } else if (obj instanceof LaporanTanah) {
            objMain = laporanTanahDAO.findById((Long) val);
        } else if (obj instanceof LaporanTanahSempadan) {
            objMain = laporanTanahSempadanDAO.findById((Long) val);
        } else if (obj instanceof KodCawangan) {
            objMain = kodCawanganDAO.findById((String) val);
        }
        return objMain;
    }

    public void simpanNotifikasi(Notifikasi notifikasi, List<PenggunaPeranan> listPengguna) {
        Session s = sessionProvider.get();
        Transaction txn = s.beginTransaction();
        try {
            for (PenggunaPeranan pp : listPengguna) {
                if (isDebug) {
                    LOG.debug("creating Notifikasi for " + pp.getPengguna().getIdPengguna() + "(" + pp.getPeranan().getKumpBPEL() + ")");

                }
                Notifikasi n = new Notifikasi();
                n.setCawangan(notifikasi.getCawangan());
                n.setTajuk(notifikasi.getTajuk());
                n.setMesej(notifikasi.getMesej());
                n.setInfoAudit(notifikasi.getInfoAudit());
                n.setPengguna(pp.getPengguna());
                notifikasiDAO.save(n);
            }
            txn.commit();
        } catch (Exception e) {
            txn.rollback();
            LOG.error(e.getMessage(), e);
        }
    }

    //Added for urusan PBGSA
    public List<PenempatanPeserta> findPenempatanPesertaByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PenempatanPeserta b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PenempatanPeserta findOnePenempatanPesertaByIdPnmptnPsrta(Long idPnmptnPsrta) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select b from etanah.model.PenempatanPeserta b where b.idPenempatanPeserta = :idPnmptnPsrta");
        q.setLong("idPnmptnPsrta", idPnmptnPsrta);
        return (PenempatanPeserta) q.uniqueResult();
    }

    public List<Permohonan> getsenaraiIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonanSebelum and b.kodUrusan = 'HLLB' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getSenaraiIdMohonKelompok(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Permohonan> getsenaraiIdMohonwithStatus(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonanSebelum and b.keputusan = 'D' and b.status = 'SL' and b.kodUrusan = 'HLLB' ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonan);
        return q.list();
    }
    //PermohonanPihakPendeposit

    @Transactional
    public void kemaskiniPermohonanPihakPendeposit(PermohonanPihakPendeposit permohonanPihakPendeposit) {
        permohonanPihakPendepositDAO.saveOrUpdate(permohonanPihakPendeposit);
    }

    @Transactional
    public void deletePermohonanPihakPendeposit(PermohonanPihakPendeposit permohonanPihakPendeposit) {
        permohonanPihakPendepositDAO.delete(permohonanPihakPendeposit);
    }

    @Transactional
    public void simpanPermohonanPihakPendeposit(PermohonanPihakPendeposit permohonanPihakPendeposit) {
        permohonanPihakPendepositDAO.save(permohonanPihakPendeposit);
    }

    public PermohonanPihakPendeposit findPermohonanPihakPendepositByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPihakPendeposit lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public List<PermohonanPihakPendeposit> findListPermohonanPihakPendepositByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPihakPendeposit b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanPihakPendeposit findPermohonanPihakPendepositByIdPermohonanPihakPendeposit(Long idPermohonanPihakPendeposit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPihakPendeposit lt where lt.idPermohonanPihakPendeposit = :idPermohonanPihakPendeposit");
        q.setLong("idPermohonanPihakPendeposit", idPermohonanPihakPendeposit);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public PermohonanPihakPendeposit getMaxOfPermohonanPihakPendeposit() {
        String query = "SELECT b FROM etanah.model.PermohonanPihakPendeposit b where b.idPermohonanPihakPendeposit =(select max (p.idPermohonanPihakPendeposit) from etanah.model.PermohonanPihakPendeposit p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (PermohonanPihakPendeposit) q.uniqueResult();
    }

    public List<PelanGIS> findListPelanGISPKByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PelanGIS findPelanB1GISByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PelanGIS) q.uniqueResult();
    }
    //added by syazwan 20/8/2014 -utk id kelompok GRP-

    public List<PelanGIS> findPelanB1GISByIdKelompok(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PelanGIS> findPelanPWByIdMohon(String idPermohonan, String kod) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = :kod";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return q.list();
    }

    public PelanGIS findPelanB2GISByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B2'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PelanGIS) q.uniqueResult();
    }

    public PelanGIS findPelanB2GISByIdPermohonanAndNoLot(String idPermohonan, String noLot) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.pelanGISPK.noLot = :noLot and b.jenisPelan = 'B2'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noLot", noLot);
        return (PelanGIS) q.uniqueResult();
    }

    public PelanGIS findPelanByIdPermohonanAndJenisPelan(String idPermohonan, String jenisPelan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = :jenisPelan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisPelan", jenisPelan);
        return (PelanGIS) q.uniqueResult();
    }
    
    public PelanGIS findPelanByIdPermohonanAndJenisPelanP1(String idPermohonan, String jenisPelan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("jenisPelan", jenisPelan);
        return (PelanGIS) q.uniqueResult();
    }
    
    
    
    public void deleteHakmilikPermohonanByIdMH(Long id) {
        String query = "DELETE FROM etanah.model.HakmilikPermohonan b where b.id = :id";
        Session session = sessionProvider.get();
//        PreparedStatement ps;
//        ps.executeUpdate(query);
        Query q = session.createQuery(query);
        q.setLong("id", id);
    }

    @Transactional
    public void deleteHakmilikPermohonanByIdMHExecute(Long id) {
        String query = "DELETE FROM etanah.model.HakmilikPermohonan b where b.id = :id";
        Session session = sessionProvider.get();
//        PreparedStatement ps;
//        ps.executeUpdate(query);
        Query q = session.createQuery(query);
        q.setLong("id", id);
        q.executeUpdate();
    }
    //PermohonanLaporanPohon

    public List<PermohonanLaporanPohon> findListMohonLaporPohonByIdLaporan(Long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanPohon b where b.idLaporan.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void kemaskiniPermohonanLaporanPohon(PermohonanLaporanPohon permohonanLaporanPohon) {
        permohonanLaporanPohonDAO.saveOrUpdate(permohonanLaporanPohon);
    }

    @Transactional
    public void deletePermohonanLaporanPohon(PermohonanLaporanPohon permohonanLaporanPohon) {
        permohonanLaporanPohonDAO.delete(permohonanLaporanPohon);
    }

    @Transactional
    public void simpanPermohonanLaporanPohon(PermohonanLaporanPohon permohonanLaporanPohon) {
        permohonanLaporanPohonDAO.save(permohonanLaporanPohon);
    }

    public List<PermohonanLaporanUsaha> findListMohonLaporUsahaByIdLaporan(Long idLaporan) {
        String query = "SELECT b FROM etanah.model.PermohonanLaporanUsaha b where b.idLaporan.idLaporan = :idLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void kemaskiniPermohonanLaporanUsaha(PermohonanLaporanUsaha permohonanLaporanUsaha) {
        permohonanLaporanUsahaDAO.saveOrUpdate(permohonanLaporanUsaha);
    }

    @Transactional
    public void deletePermohonanLaporanUsaha(PermohonanLaporanUsaha permohonanLaporanUsaha) {
        permohonanLaporanUsahaDAO.delete(permohonanLaporanUsaha);
    }

    @Transactional
    public void simpanPermohonanLaporanUsaha(PermohonanLaporanUsaha permohonanLaporanUsaha) {
        permohonanLaporanUsahaDAO.save(permohonanLaporanUsaha);
    }

    public List<KodDUN> findListKodDunByKodParlimen(String kodParlimen) {
        String query = "SELECT b FROM etanah.model.KodDUN b where b.kodKawasanParlimen.kod = :kodParlimen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodParlimen", kodParlimen);
        return q.list();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanAndJenisLaporan(String idPermohonan, String jenisLaporan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.hakmilikPermohonan.id = :idPermohonan AND b.jenisLaporan = :jenisLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        return (LaporanTanah) q.uniqueResult();
    }
    // add by shazwan for notis N5A

    public List<Notis> getListNotis2(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<KandunganFolder> getListDokumen5A(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = '5AA'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanAndJenisLaporan2(String idPermohonan, String jenisLaporan) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan AND b.jenisLaporan = :jenisLaporan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanAndJenisLaporan3(String idPermohonan, String jenisLaporan, long id) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan AND b.jenisLaporan = :jenisLaporan AND b.hakmilikPermohonan.id =:id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisLaporan", jenisLaporan);
        q.setLong("id", id);
        return (LaporanTanah) q.uniqueResult();
    }

    @Transactional
    public void deletePermohonanTuntutanKos(PermohonanTuntutanKos permohonanTuntutanKos) {
        permohonantuntutankosDAO.delete(permohonanTuntutanKos);
    }

    @Transactional
    public void simpanPermohonanKelompok(PermohonanKelompok permohonanKelompok) {
        permohonanKelompokDAO.save(permohonanKelompok);
    }

    @Transactional
    public void updatePermohonanKelompok(PermohonanKelompok permohonanKelompok) {
        permohonanKelompokDAO.saveOrUpdate(permohonanKelompok);
    }

    @Transactional
    public void deletePermohonanKelompok(PermohonanKelompok permohonanKelompok) {
        permohonanKelompokDAO.delete(permohonanKelompok);
    }

    public List<PermohonanKelompok> findAllPermohonanBerkelompok() {
        return permohonanKelompokDAO.findAll();
    }

//    public List<KodHakmilik> findKodHakmilikByKodCaw(ArrayList<String> data) {
//        String query = null;
//        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
//        if (!data.isEmpty()) {
//            query = "Select kh from etanah.model.KodHakmilik kh WHERE kh.kod in (";
//            int count = 1;
//            for (String a : data) {
//                query = query + "'" + a + "'";
//                if (count < data.size()) {
//                    query = query + ",";
//                } else if (count == data.size()) {
//                    query = query + ")";
//                    break;
//                }
//                count++;
//            }
////            query = query + " AND p.idPengguna = pp.pengguna.idPengguna AND p.perananUtama.kod = pp.peranan.kod AND pp.peranan.kod = kp.kod"
////                    + " AND pp.pengguna.kodCawangan.kod = '" + i + "' and pp.peranan.kodJabatan = '24'";
//
//        }
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//
//        List<KodHakmilik> listHakmilik = new ArrayList<KodHakmilik>();
//        listHakmilik = q.list();
////        for (KodHakmilik kh : listHakmilik) {
////            listPengguna.add(pp.getPengguna());
////        }
//        return listHakmilik;
//    }
    public List<KodHakmilik> findKodHakmilikByKodCaw(List<String> kodHakmilik) {
        String query = "SELECT p FROM etanah.model.KodHakmilik p WHERE p.kod in(:kodHakmilik)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameterList("kodHakmilik", kodHakmilik);
        return q.list();
    }

    public List<KodUrusan> getKodUrusan() throws NamingException {
        String query = "Select ku from etanah.model.KodUrusan ku WHERE ku.aktif = 'Y' AND ku.jabatan.kod = '2' order by ku.nama ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        LOG.info("Listing all Kod Urusan...");
        return q.list();
    }

    public KodUrusan findExistingRecord(String kodUrusan) {
        String query = "Select m FROM etanah.model.KodUrusan m WHERE m.aktif = 'Y' AND m.jabatan.kod = '2' ";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return (KodUrusan) q.uniqueResult();
    }

    @Transactional
    public void simpanKodUrusan(KodUrusan kodUrusan) {
        kodUrusanDAO.saveOrUpdate(kodUrusan);
    }

    public List<Permohonan> findPermohonanByKodUrusan(String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<Permohonan> findPermohonanByKodUrusanByDaerah(String kodUrusan, String kodCaw) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.kod = :kodUrusan and b.cawangan.kod = :kodCaw and b.idPermohonan like '%DIS%' ORDER BY b.idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public List<Permohonan> findPermohonanByKodUrusanAndStatusAK(String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.kod = :kodUrusan and b.status = 'AK'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
//        q.setchar
        return q.list();
    }

    public List<Permohonan> findPermohonanByIdFasa(String idFasa) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.idFasa = idFasa ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idFasa", idFasa);
//        q.setchar
        return q.list();
    }

    public List<Permohonan> findPermohonanByIdFasaLong(Long idFasa) {
        String query = "SELECT b FROM etanah.model.FasaPermohonan b where b.idFasa = idFasa ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idFasa", idFasa);
//        q.setchar
        return q.list();
    }

    public List<KodCawangan> getKodCawangan() throws NamingException {
        String query = "Select kc from etanah.model.KodCawangan kc WHERE kc.aktif = 'Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        LOG.info("Listing all Kod Cawangan...");
        return q.list();
    }

    public List<Pengguna> findPenggunaKodCaw(KodCawangan kc) {
        String query = "SELECT p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod = :kc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query).setString("kc", kc.getKod());
        return q.list();
    }

    public List<Pengguna> findPenggunaGroupBpelKodCaw(String bpelGroup, String kc) {
        String query = new String();
        if (!StringUtils.isEmpty(kc)) {
            query = "SELECT p FROM etanah.model.Pengguna p WHERE p.kodCawangan.kod = :kc and p.perananUtama.kumpBPEL =:bpelGroup ";
        } else {
            query = "SELECT p FROM etanah.model.Pengguna p WHERE p.perananUtama.kumpBPEL =:bpelGroup ";
        }
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        if (!StringUtils.isEmpty(kc)) {
            q.setString("kc", kc);
        }
        q.setString("bpelGroup", bpelGroup);
        return q.list();
    }

    public List<KodBandarPekanMukim> findKodBPM() throws NamingException {
        String query = "SELECT k FROM etanah.model.KodBandarPekanMukim k WHERE k.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        LOG.info("Listing all kod bpm");
        return q.list();
    }

    public List<KodBandarPekanMukim> findBPM(String kodDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select bpm from etanah.model.KodBandarPekanMukim bpm where bpm.daerah.nama = :kodDaerah");
        q.setString("kodDaerah", kodDaerah);
        return q.list();
    }

    public List<KodBandarPekanMukim> findBPMKod(String kodDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select bpm from etanah.model.KodBandarPekanMukim bpm where bpm.daerah.kod = :kodDaerah");
        q.setString("kodDaerah", kodDaerah);
        return q.list();
    }

    public List<KandunganFolder> getListDokumenSurat(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :idFolder and m.dokumen.kodDokumen.nama LIKE :surat");
        q.setLong("idFolder", idFolder);
        q.setString("surat", "%" + "Surat" + "%");
        return q.list();
    }

    public List<SalinanKepada> findSalinanByIdPermohonanAndKodDokumen(String idPermohonan, String kodDokumen) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.SalinanKepada m WHERE m.permohonan.idPermohonan = :idPermohonan and m.kodDokumen.kod = :kodDokumen Order By m.idSalinanKepada asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return q.list();
    }

    @Transactional
    public void simpanSalinan(SalinanKepada salinan) {
        salinanKepadaDAO.save(salinan);
    }

    @Transactional
    public void kemaskiniSalian(SalinanKepada salinan) {
        salinanKepadaDAO.saveOrUpdate(salinan);
    }

    @Transactional
    public void deleteSalinanKepada(SalinanKepada salinan) {
        salinanKepadaDAO.delete(salinan);
    }

    @Transactional
    public void deletePermohonanBahanBatuan(PermohonanBahanBatuan permohonanBahanBatuan) {
        permohonanBahanBatuanDAO.delete(permohonanBahanBatuan);
    }

    public List<PermohonanPermitItem> findPermitItemTanahLPSP(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanPermitItem b where b.permohonan.idPermohonan = :idPermohonan and b.kodItemPermit.kod not in ('KB','PB','MB')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pihak> findUniquePemohonByIdPermohonan2(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT ph from etanah.model.Pihak ph "
                + "WHERE ph.idPihak IN(SELECT DISTINCT(p.pihak.idPihak) FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan= :idPermohonan) ");
//                + "AND p.pihak.idPihak IN (SELECT DISTINCT (hph.pihak.idPihak) FROM etanah.model.HakmilikPihakBerkepentingan hph WHERE hph.jenis.kod = 'PM' AND hph.aktif = 'Y')) ORDER BY ph.idPihak");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakActiveByHakmilika(Hakmilik hakmilik) {
        //khairil :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS')";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", hakmilik.getIdHakmilik());
        return q.list();
    }

    public PenempatanPeserta cariPenempatanPesertaByIdMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PenempatanPeserta b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PenempatanPeserta) q.uniqueResult();
    }

    public Pihak findhakmilikpihakbyic(String noPengenalan) {
        String query = "Select p FROM etanah.model.Pihak p WHERE p.noPengenalan = :no";
        List<Pihak> senaraiPihak = sessionProvider.get().createQuery(query).setString("no", noPengenalan).list();

        if (!senaraiPihak.isEmpty()) {
            return senaraiPihak.get(0);
        } else {
            return null;
        }
    }

    public HakmilikPihakBerkepentingan cariHakmilikPihakBerkepentinganByIDHakmilik(String idhakmilik) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b where b.hakmilik.idHakmilik = :idHakmilik";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idHakmilik", idhakmilik);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public List<String> getUser(String kodCaw, String kodJabatan, String kodPeranan) {
        String query = "SELECT b FROM etanah.model.Pengguna b where b.kodCawangan.kod = :kodCaw and b.kodJabatan.kod = :kodJabatan and b.perananUtama.kod = :kodPeranan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodCaw", kodCaw);
        q.setString("kodJabatan", kodJabatan);
        q.setString("kodPeranan", kodPeranan);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosByIdTuntut(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan and b.kodTuntut.kod = :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", kodTuntut);
        System.out.println("------q.list()---------:" + q.list());
        System.out.println("------q.list()---------:" + q.list().isEmpty());
        return q.list();
    }

    public List<Permohonan> findListPermohonanByIdPermohonanSebelum(String idPermohonanSebelum) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonanSebelum";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonanSebelum", idPermohonanSebelum);
        return q.list();
    }

    public HakmilikPermohonan findIdHakmilikByIdMohon(String idPermohonan) {
        String query = "Select h FROM etanah.model.HakmilikPermohonan h where h.permohonan.idPermohonan =:idPermohonan";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> findPemilikbyIdHakmilik(String idPermohonan) {
        //khairil :: return tuan tanah , pemegang amanah , waris cucu cicit belah mak sedare angkat dan sebagainya
        String query = "Select a from etanah.model.HakmilikPihakBerkepentingan a, etanah.model.HakmilikPermohonan b "
                + "where b.hakmilik.idHakmilik = a.hakmilik.idHakmilik and a.aktif='Y' "
                + "and a.jenis.kod in ('PM','PA','WAR','ASL','JA','JK','KL','PDP','PK','PLK','PP','RP','WKL','WPA','WS') and b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> cariKategoriPemohonByIdPermohonan(String idPermohonan) {

        String query = "Select dk from etanah.model.Dokumen dk where dk.idDokumen in "
                + "(select fdk.dokumen.idDokumen from etanah.model.KandunganFolder fdk where fdk.folder.folderId = "
                + "(select mhn.folderDokumen.folderId from etanah.model.Permohonan mhn where mhn.idPermohonan = :idPermohonan ))";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<Dokumen> cariIdDokumen(String idPermohonan) {

        String query = "Select dk from etanah.model.Dokumen dk where dk.idDokumen in "
                + "(select fdk.dokumen.idDokumen from etanah.model.KandunganFolder fdk, etanah.model.Permohonan mhn where mhn.idPermohonan = :idPermohonan"
                + " and fdk.folder.folderId = mhn.folderDokumen.folderId))";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<UrusanDokumen> cariUrusanDokumenByKodUrusan(String kodUrusan) {
        String query = "Select udk from etanah.model.UrusanDokumen udk where udk.kodUrusan.kod = :kodUrusan and udk.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<KodJabatan> cariKodJabatanByKod() {
        String query = "Select kj from etanah.model.KodJabatan kj where kj.aktif='Y'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<Pengguna> findUserKumpBpel(ArrayList<String> data, String i) {
        String query = null;
        //SELECT h FROM etanah.model.HakmilikPihakBerkepentingan h WHERE h.hakmilik.idHakmilik = :idhakmilik and h.jenis.kod = 'PM' and h.aktif= 'Y'"
        if (!data.isEmpty()) {
            query = "Select pp from etanah.model.PenggunaPeranan pp, etanah.model.KodPeranan kp, etanah.model.Pengguna p WHERE pp.peranan.kumpBPEL in (";
            int count = 1;
            for (String a : data) {
                query = query + "'" + a + "'";
                if (count < data.size()) {
                    query = query + ",";
                } else if (count == data.size()) {
                    query = query + ")";
                    break;
                }
                count++;
            }
            query = query + " AND p.idPengguna = pp.pengguna.idPengguna AND p.status.kod = 'A' AND p.perananUtama.kod = pp.peranan.kod AND pp.peranan.kod = kp.kod"
                    + " AND pp.pengguna.kodCawangan.kod = '" + i + "' and kp.kodJabatan = p.kodJabatan";

        }
//        logger.info("::: query : " + query);
//        logger.info("::: Query to get list pengguna based on kump bpel: " + query);
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);

        List<Pengguna> listPengguna = new ArrayList<Pengguna>();
        List<PenggunaPeranan> listPp = new ArrayList<PenggunaPeranan>();
        listPp = q.list();
        for (PenggunaPeranan pp : listPp) {
            listPengguna.add(pp.getPengguna());
        }
        return listPengguna;
    }

    @Transactional
    public void deleteMohonRujLuar(long idRujukan) {
        String query = "DELETE FROM etanah.model.PermohonanRujukanLuar b where b.idRujukan = :idRujukan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idRujukan", idRujukan);
        q.executeUpdate();
    }

    @Transactional
    public void delImejLaporanByIdLaporTanahSpdn(Long id) {
        String query = "DELETE FROM etanah.model.ImejLaporan i WHERE i.laporanTanahSempadan.idLaporTanahSpdn = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        q.executeUpdate();
    }

    public ImejLaporan findImejLaporanByIdLaporTanahSpdn(Long id) {
        String query = "SELECT i FROM etanah.model.ImejLaporan i WHERE i.laporanTanahSempadan.idLaporTanahSpdn = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("id", id);
        return (ImejLaporan) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanAndIdMH(String idPermohonan, Long id) {
        String query = "SELECT b FROM etanah.model.LaporanTanah b where b.permohonan.idPermohonan = :idPermohonan AND b.hakmilikPermohonan.id = :id";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("id", id);
        return (LaporanTanah) q.uniqueResult();
    }
    
    //get file from DMS
    public String cariPathPelan(String idPermohonan, String jenisPelan) {
        String path = "";
        StringBuilder b = new StringBuilder("Select u.pelanTif from etanah.model.gis.PelanGIS u where u.pelanGISPK.permohonan.idPermohonan =:idPermohonan and u.jenisPelan =:jenisPelan");
        Session s = sessionProvider.get();
        Query q = s.createQuery(b.toString());
        q.setString("idPermohonan", idPermohonan);
        q.setString("jenisPelan", jenisPelan);
        List<String> paths = q.list();
        if (paths.size() > 0) {
            path = paths.get(0);
        }
        return path;
    }
    
    public PelanGIS getfileFromDMS(String idPermohonan, String jenisPelan) {        
         
         StringBuilder sb = new StringBuilder("Select g from etanah.model.gis.PelanGIS u where u.pelanGISPK.permohonan.idPermohonan =:idPermohonan and u.jenisPelan =:jenisPelan");
         
        Query q = sessionProvider.get().createQuery(sb.toString())
                .setString("idPermohonan", idPermohonan).setString("jenisPelan", jenisPelan);
        
        if (q.list().size() > 1)
            return (PelanGIS)q.list().get(0);
        
        return (PelanGIS)q.uniqueResult();
    }
    
     @Transactional
    public void deleteP1(String idPermohonan) {
        String query = "DELETE FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan =:idPermohonan and b.jenisPelan =:jenisPelan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setParameter("idPermohonan", idPermohonan);
        q.setParameter("jenisPelan", "P1");
        q.executeUpdate();
    }
   
    public HakmilikPermohonan findByNoLotIdPermohonan(String idPermohonan, String noLot) {
        String query = "Select p FROM etanah.model.HakmilikPermohonan p WHERE p.permohonan.idPermohonan = :idPermohonan" + " and p.noLot = :noLot";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noLot", noLot);
        return (HakmilikPermohonan) q.uniqueResult();
    }
}
