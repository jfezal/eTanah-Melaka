
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.service;

import com.google.inject.Inject;
import com.wideplay.warp.persist.Transactional;
//import etanah.applet.util.FileUtil;
import etanah.dao.DokumenDAO;
import etanah.dao.FasaPermohonanDAO;
import etanah.dao.HakmilikPerbicaraanDAO;
import etanah.dao.HakmilikPermohonanDAO;
import etanah.dao.ImejLaporanDAO;
import etanah.dao.KodAgensiDAO;
import etanah.dao.KodDUNDAO;
import etanah.dao.KodDokumenDAO;
import etanah.dao.KodKawasanParlimenDAO;
import etanah.dao.KodKlasifikasiDAO;
import etanah.dao.KodNegeriDAO;
import etanah.dao.LaporanTanahDAO;
import etanah.dao.LaporanTanahSempadanDAO;
import etanah.dao.NoPtDAO;
import etanah.dao.NotisDAO;
import etanah.dao.PemohonDAO;
import etanah.dao.PenghantarNotisDAO;
import etanah.dao.PenyerahSokonganDAO;
import etanah.dao.PermohonanAsalDAO;
import etanah.dao.PermohonanDAO;
import etanah.dao.PermohonanKertasButiranDAO;
import etanah.dao.PermohonanKertasDAO;
import etanah.dao.PermohonanKertasKandunganDAO;
import etanah.dao.PermohonanKertasKeputusanDAO;
import etanah.dao.PermohonanLaporanBangunanDAO;
import etanah.dao.PermohonanLaporanCerunDAO;
import etanah.dao.PermohonanLaporanUlasanDAO;
import etanah.dao.PermohonanManualDAO;
import etanah.dao.PermohonanNotaDAO;
import etanah.dao.PermohonanPengambilanDAO;
import etanah.dao.PermitLPSRekodDAO;
import etanah.dao.PermohonanPlotPelanDAO;
import etanah.dao.PermohonanRujukanLuarDAO;
import etanah.dao.PermohonanTandatanganDokumenDAO;
import etanah.dao.PermohonanTuntutanButiranDAO;
import etanah.dao.PermohonanTuntutanDAO;
import etanah.dao.PermohonanTuntutanBayarDAO;
import etanah.dao.PermohonanTuntutanKosDAO;
import etanah.dao.PermohonanUkurDAO;
import etanah.dao.PihakAlamatTambDAO;
import etanah.dao.SalinanKepadaDAO;
import etanah.dao.SiriNoPtDAO;
import etanah.dao.PermitDAO;
import etanah.dao.PermitItemDAO;
import etanah.dao.PermitItemKuantitiDAO;
import etanah.dao.PermitSahLakuDAO;
import etanah.dao.PermitStrukturDiluluskanDAO;
import etanah.dao.PermohonanKertasUlasanDAO;
import etanah.dao.PermohonanPlotSekatanDAO;
import etanah.dao.PermohonanPlotSyaratNyataDAO;
import etanah.dao.PihakDAO;
import etanah.dao.gis.PelanGISDAO;
import etanah.model.Ansuran;
import etanah.model.Dokumen;
import etanah.model.DokumenKewangan;
import etanah.model.FasaPermohonan;
import etanah.model.Hakmilik;
import etanah.model.HakmilikPermohonan;
import etanah.model.HakmilikPihakBerkepentingan;
import etanah.model.HakmilikUrusan;
import etanah.model.ImejLaporan;
import etanah.model.InfoAudit;
import etanah.model.KandunganFolder;
import etanah.model.KodAgensi;
import etanah.model.KodBandarPekanMukim;
import etanah.model.KodBangsa;
import etanah.model.KodCaraPermohonan;
import etanah.model.KodCawanganJabatan;
import etanah.model.KodDUN;
import etanah.model.KodDokumen;
import etanah.model.KodKadarBayaran;
import etanah.model.KodKawasanParlimen;
import etanah.model.KodKecerunanTanah;
import etanah.model.KodKegunaanTanah;
import etanah.model.KodKlasifikasi;
import etanah.model.KodRizab;
import etanah.model.KodSekatanKepentingan;
import etanah.model.KodSyaratNyata;
import etanah.model.KodTransaksiTuntut;
import etanah.model.KodTuntut;
import etanah.model.KodUrusan;
import etanah.model.LaporanTanah;
import etanah.model.LaporanTanahSempadan;
import etanah.model.NoPt;
import etanah.model.Notis;
import etanah.model.Pemohon;
import etanah.model.PenasihatUndang;
import etanah.model.Pengguna;
import etanah.model.PenghantarNotis;
import etanah.model.Permohonan;
import etanah.model.PermohonanAsal;
import etanah.model.PermohonanKertas;
import etanah.model.PermohonanKertasButiran;
import etanah.model.PermohonanKertasKandungan;
import etanah.model.PermohonanKertasKeputusan;
import etanah.model.PermohonanLaporanBangunan;
import etanah.model.PermohonanLaporanCerun;
import etanah.model.PermohonanLaporanPelan;
import etanah.model.PermohonanLaporanUlasan;
import etanah.model.PermohonanNota;
import etanah.model.ambil.PermohonanPengambilan;
import etanah.model.PermohonanPihak;
import etanah.model.PermohonanPlotPelan;
import etanah.model.PermohonanRujukanLuar;
import etanah.model.PermohonanTandatanganDokumen;
import etanah.model.PermohonanTuntutan;
import etanah.model.PermohonanTuntutanBayar;
import etanah.model.PermohonanTuntutanButiran;
import etanah.model.PermohonanTuntutanKos;
import etanah.model.PermohonanManual;
import etanah.model.PermohonanUkur;
import etanah.model.Permit;
import etanah.model.Pihak;
import etanah.model.PihakAlamatTamb;
import etanah.model.PihakPengarah;
import etanah.model.SiriNoPt;
import etanah.model.SalinanKepada;
import etanah.model.Transaksi;
import etanah.model.ambil.HakmilikPerbicaraan;
import etanah.model.gis.PelanGIS;
import etanah.util.DMSUtil;
import etanah.util.FileUtil;
import java.io.File;
import java.util.List;
import net.sourceforge.stripes.action.FileBean;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import etanah.model.PenggunaPeranan;
import etanah.model.PenyerahSokongan;
import etanah.model.PermitItem;
import etanah.model.PermitItemKuantiti;
import etanah.model.PermitLPSRekod;
import etanah.model.PermitSahLaku;
import etanah.model.PermitStrukturDiluluskan;
import etanah.model.PermohonanKertasUlasan;
import etanah.model.PermohonanPlotSekatan;
import etanah.model.PermohonanPlotSyaratNyata;
import etanah.model.gis.PelanGISPK;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author nursyazwani
 */
public class PembangunanService {

    private static final Logger LOG = Logger.getLogger(etanah.service.PembangunanService.class);
    @Inject
    LaporanTanahDAO laporantanahDAO;
    @Inject
    NotisDAO notisDAO;
    @Inject
    PenghantarNotisDAO penghantarNotisDAO;
    @Inject
    DokumenDAO dokumenDAO;
    @Inject
    KodDokumenDAO kodDokumenDAO;
    @Inject
    KodKlasifikasiDAO kodKlasifikasiDAO;
    @Inject
    HakmilikPermohonanDAO hakmilikPermohonanDAO;
    @Inject
    PermohonanDAO permohonanDAO;
    @Inject
    PermitDAO permitDAO;
    @Inject
    FasaPermohonanDAO fasaPermohonanDAO;
    @Inject
    PermohonanKertasDAO permohonanKertasDAO;
    @Inject
    PermohonanKertasKandunganDAO permohonanKertasKandunganDAO;
    @Inject
    PermohonanTuntutanKosDAO permohonanTuntutanKosDAO;
    @Inject
    PermohonanKertasUlasanDAO permohonanKertasUlasanDAO;
    @Inject
    PermohonanPlotPelanDAO permohonanPlotPelanDAO;
    @Inject
    PermohonanPengambilanDAO permohonanPengambilanDAO;
    @Inject
    PermohonanUkurDAO permohonanUkurDAO;
    @Inject
    NoPtDAO noPtDAO;
    @Inject
    PihakDAO pihakDAO;
    @Inject
    KodAgensiDAO kodAgensiDAO;
    @Inject
    KodNegeriDAO kodNegeriDAO;
    @Inject
    PermohonanRujukanLuarDAO permohonanRujukanLuarDAO;
    @Inject
    PermohonanTandatanganDokumenDAO permohonanTandatanganDokumenDAO;
    @Inject
    PermohonanTuntutanDAO permohonanTuntutanDAO;
    @Inject
    PermohonanTuntutanBayarDAO permohonanTuntutanBayarDAO;
    @Inject
    PermohonanTuntutanButiranDAO permohonanTuntutanButiranDAO;
    @Inject
    SiriNoPtDAO siriNoPtDAO;
    @Inject
    PemohonDAO pemohonDAO;
    @Inject
    ImejLaporanDAO imejLaporanDAO;
    @Inject
    PermohonanAsalDAO permohonanAsalDAO;
    @Inject
    PermohonanNotaDAO permohonanNotaDAO;
    @Inject
    PermohonanLaporanUlasanDAO permohonanLaporanUlasanDAO;
    @Inject
    PermohonanLaporanCerunDAO permohonanLaporanCerunDAO;
    @Inject
    PermohonanKertasKeputusanDAO permohonanKertasKeputusanDAO;
    @Inject
    PermohonanLaporanBangunanDAO permohonanLaporanBangunanDAO;
    @Inject
    PermohonanKertasButiranDAO permohonanKertasButiranDAO;
    @Inject
    HakmilikPerbicaraanDAO hakmilikPerbicaraanDAO;
    @Inject
    PihakAlamatTambDAO pihakAlamatDAO;
    @Inject
    KodKawasanParlimenDAO kodKawasanParlimenDAO;
    @Inject
    KodDUNDAO kodDUNDAO;
    @Inject
    LaporanTanahSempadanDAO laporanTanahSempadanDAO;
    @Inject
    PermohonanManualDAO permohonanManualDAO;
    @Inject
    SalinanKepadaDAO salinanKepadaDAO;
    @Inject
    PermitSahLakuDAO permitSahLakuDAO;
    @Inject
    PermitStrukturDiluluskanDAO permitStrukturDiluluskanDAO;
    @Inject
    PelanGISDAO pelanGISDAO;
    @Inject
    PermitItemDAO permitItemDAO;
    @Inject
    PermitItemKuantitiDAO permitItemKnttDAO;
    @Inject
    PermitLPSRekodDAO permitLPSRekodDAO;
    @Inject
    PermitItemKuantitiDAO permitItemKuantitiDAO;
    @Inject
    protected com.google.inject.Provider<Session> sessionProvider;
    @Inject
    PermohonanPlotSekatanDAO permohonanPlotSekatanDAO;
    @Inject
    PermohonanPlotSyaratNyataDAO permohonanPlotSyaratNyataDAO;
    @Inject
    PenyerahSokonganDAO penyerahSokonganDAO;

    @Transactional
    public void simpanHakmilikPerbicaraan(HakmilikPerbicaraan hakmilikPerbicaraan) {
        hakmilikPerbicaraanDAO.saveOrUpdate(hakmilikPerbicaraan);
    }

    @Transactional
    public void saveOrUpdate(Notis j) {
        notisDAO.saveOrUpdate(j);
    }

    @Transactional
    public void saveOrUpdate(PenghantarNotis en) {
        penghantarNotisDAO.saveOrUpdate(en);
    }

    @Transactional
    public void simpanFasaPermohonan(FasaPermohonan fp) {
        fasaPermohonanDAO.saveOrUpdate(fp);
    }

    @Transactional
    public void simpanPermit(Permit permit) {
        permitDAO.saveOrUpdate(permit);
    }

    @Transactional
    public void simpanPelanGIS(PelanGIS pg) {
        pelanGISDAO.saveOrUpdate(pg);
    }

    @Transactional
    public void simpanPermitSahLaku(PermitSahLaku permitSahLaku) {
        permitSahLakuDAO.saveOrUpdate(permitSahLaku);
    }

    @Transactional
    public void simpanPermitItem(PermitItem permitItem) {
        permitItemDAO.saveOrUpdate(permitItem);
    }

    @Transactional
    public void simpanPermitItemKntt(PermitItemKuantiti permitItemKuantiti) {
        permitItemKnttDAO.saveOrUpdate(permitItemKuantiti);
    }

    @Transactional
    public void simpanPermitStrukturDiluluskan(PermitStrukturDiluluskan permitStrukturLuas) {
        permitStrukturDiluluskanDAO.saveOrUpdate(permitStrukturLuas);
    }

    @Transactional
    public void simpanPermohonanTuntutanKos(PermohonanTuntutanKos ptk) {
        permohonanTuntutanKosDAO.saveOrUpdate(ptk);
    }

    @Transactional
    public void simpanPermohonanTuntutanBayar(PermohonanTuntutanBayar ptb) {
        permohonanTuntutanBayarDAO.saveOrUpdate(ptb);
    }

    @Transactional
    public void deleteKertas(PermohonanKertas pk) {
        permohonanKertasDAO.delete(pk);
    }

    @Transactional
    public void deletePermohonanManualByNoFail(PermohonanManual pk) {
        permohonanManualDAO.delete(pk);
    }

    @Transactional
    public void deleteKertasKandungan(PermohonanKertasKandungan pkk) {
        permohonanKertasKandunganDAO.delete(pkk);
    }

    @Transactional
    public void simpanPermohonanKertas(PermohonanKertas permohonanKertas) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
    }

    @Transactional
    public void simpanPihak(Pihak p) {
        pihakDAO.saveOrUpdate(p);
    }

    @Transactional
    public void simpanPermohonanKertasKandungan(PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPermohonanRujukanLuar(PermohonanRujukanLuar permohonanRujukanLuar) {
        permohonanRujukanLuarDAO.saveOrUpdate(permohonanRujukanLuar);
    }

    @Transactional
    public void simpanKertas(PermohonanKertas permohonanKertas, PermohonanKertasKandungan permohonanKertasKandungan) {
        permohonanKertasDAO.saveOrUpdate(permohonanKertas);
        permohonanKertasKandunganDAO.saveOrUpdate(permohonanKertasKandungan);
    }

    @Transactional
    public void simpanPlotPelan(PermohonanPlotPelan mohonPlotPelan) {
        permohonanPlotPelanDAO.saveOrUpdate(mohonPlotPelan);
    }

    @Transactional
    public void simpanMohonAmbil(PermohonanPengambilan mohonAmbil) {
        permohonanPengambilanDAO.saveOrUpdate(mohonAmbil);
    }

    @Transactional
    public void simpanPermohonanUkur(PermohonanUkur mohonUkur) {
        permohonanUkurDAO.saveOrUpdate(mohonUkur);
    }

    @Transactional
    public void deletePermohonanPlotPelan(PermohonanPlotPelan ppp) {
        permohonanPlotPelanDAO.delete(ppp);
    }

    @Transactional
    public void simpanLaporanUlasan(PermohonanLaporanUlasan permohonanLaporanUlasan) {
        permohonanLaporanUlasanDAO.saveOrUpdate(permohonanLaporanUlasan);
    }

    @Transactional
    public void deleteLaporCerun(PermohonanLaporanCerun lc) {
        permohonanLaporanCerunDAO.delete(lc);
    }

    @Transactional
    public void deleteLaporBangunan(PermohonanLaporanBangunan plb) {
        permohonanLaporanBangunanDAO.delete(plb);
    }

    @Transactional
    public void deletePelan(PelanGIS plb) {
        pelanGISDAO.delete(plb);
    }

    @Transactional
    public void deletePermitItemKuantiti(PermitItemKuantiti pik) {
        permitItemKuantitiDAO.delete(pik);
    }

    @Transactional
    public void deletePermitItem(PermitItem pi) {
        permitItemDAO.delete(pi);
    }

    @Transactional
    public void deletePermitLPSRekodItem(PermitLPSRekod plr) {
        permitLPSRekodDAO.delete(plr);
    }

    @Transactional
    public void deletePermitStrukturLulus(PermitStrukturDiluluskan psld) {
        permitStrukturDiluluskanDAO.delete(psld);
    }

    @Transactional
    public void deletePermitSahLaku(PermitSahLaku psl) {
        permitSahLakuDAO.delete(psl);
    }

    @Transactional
    public void deletePermit(Permit p) {
        permitDAO.delete(p);
    }

    @Transactional
    public void deletePermohonanTuntutanBayar(PermohonanTuntutanBayar p) {
        permohonanTuntutanBayarDAO.delete(p);
    }

    @Transactional
    public void deletePermohonanTuntutanKos(PermohonanTuntutanKos p) {
        permohonanTuntutanKosDAO.delete(p);
    }

    @Transactional
    public void deleteHp(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.delete(hp);
    }

    @Transactional
    public void deletePermohonan(Permohonan p) {
        permohonanDAO.delete(p);
    }

    @Transactional
    public void deletePermohonanNota(long idMohonNota) {
        String query = "DELETE FROM etanah.model.PermohonanNota p WHERE  p.idMohonNota =:idMohonNota";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idMohonNota", idMohonNota);
        int rowCount = q.executeUpdate();
    }

    @Transactional
    public void simpanPermohonanKertasButiran(PermohonanKertasButiran permohonanKertasButiran) {
        permohonanKertasButiranDAO.saveOrUpdate(permohonanKertasButiran);
    }

    @Transactional
    public void simpanPermohonanKertasKeputusan(PermohonanKertasKeputusan permohonanKertasKeputusan) {
        permohonanKertasKeputusanDAO.saveOrUpdate(permohonanKertasKeputusan);
    }

    @Transactional
    public void deletePermohonanKertasButiran(PermohonanKertasButiran permohonanKertasButiran) {
        permohonanKertasButiranDAO.delete(permohonanKertasButiran);
    }

    @Transactional
    public void deletePermohonanKertasKeputusan(PermohonanKertasKeputusan permohonanKertasKeputusan) {
        permohonanKertasKeputusanDAO.delete(permohonanKertasKeputusan);
    }

    @Transactional
    public void simpanPihakAlamatTamb(PihakAlamatTamb pihakATB) {
        pihakAlamatDAO.saveOrUpdate(pihakATB);
    }

    public List<PermohonanPlotPelan> findPlot(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp.kegunaanTanah.kod,ppp.idPlot from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod = 'H' group by ppp.kegunaanTanah.kod,ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanGroupByIdPlot(long idPlot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.idPlot = :idPlot and ppp.pemilikan.kod = 'H'");
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanGroupByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod = 'H' ORDER BY ppp.kegunaanTanah");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanGroupByIdPermohonanByKategoriTanah(String idPermohonan, String kodKatgtanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod = 'H' and ppp.kategoriTanah.kod = :kodKatgtanah ORDER BY ppp.kegunaanTanah");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodKatgtanah", kodKatgtanah);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanByIdPermohonanOnlyForTSPSS(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and "
                + "ppp.kegunaanTanah is not null ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanByIdPermohonanIsH(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan  and ppp.pemilikan.kod = 'H' ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanForRizabAndKerajaanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod <> 'H' ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findBangunanEtc(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp "
                + "where ppp.permohonan.idPermohonan = :idPermohonan and "
                + "ppp.pemilikan.kod <> 'H' ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanPemilikanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod = :kodPemilikan ORDER BY ppp.idPlot");
        q.setString("idPermohonan", idPermohonan);
        q.setCharacter("kodPemilikan", 'H');
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanKerajaanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan and ppp.pemilikan.kod <> 'H'");
        q.setString("idPermohonan", idPermohonan);
        //q.setCharacter("kodPemilikan", 'H');
        return q.list();
    }

    public PermohonanPlotPelan findluasPlotPelanByIdMohon(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idMohon and ppp.pemilikan.kod = :kodPemilikan ORDER BY ppp.idPlot");
        q.setString("idMohon", idMohon);
        q.setCharacter("kodPemilikan", 'H');
        return (PermohonanPlotPelan) q.uniqueResult();
    }

    public Permit findPerrmitByIdMohonAndNoPermit(String idMohon, String noPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.Permit ppp where ppp.permohonan.idPermohonan = :idMohon and ppp.noPermit = :noPermit");
        q.setString("idMohon", idMohon);
        q.setString("noPermit", noPermit);
        return (Permit) q.uniqueResult();
    }

    public List<PermohonanPlotPelan> findListLuasPlotPelanByIdMohon(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idMohon and ppp.pemilikan.kod = :kodPemilikan ORDER BY ppp.idPlot");
        q.setString("idMohon", idMohon);
        q.setCharacter("kodPemilikan", 'A');
        return q.list();
    }

    public Pengguna findNama(String idPengguna) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.idPengguna = :idPengguna");
        q.setString("idPengguna", idPengguna);
        return (Pengguna) q.list().get(0);
    }

    public KodCaraPermohonan findKodCaraPermohonan(String kodAkaun) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.KodCaraPermohonan lt where lt.kod = :kodAkaun");
        q.setString("kodAkaun", kodAkaun);
        return (KodCaraPermohonan) q.uniqueResult();
    }

    public List<PermohonanPlotPelan> findluasPlotPelanByIdMohon2(String idMohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idMohon and ppp.pemilikan.kod = 'H' ORDER BY ppp.idPlot");
        q.setString("idMohon", idMohon);
        return q.list();
    }

    public PermohonanKertas findById(String idKertas) {
        return permohonanKertasDAO.findById(Long.valueOf(idKertas));
    }

    public List<PermohonanKertas> findPermohonanKertasByIdPermohonan(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.tajuk = :tajuk");
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasKandunganByJabatan(String idPermohonan, String jabatanTeknikal) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk, etanah.model.PermohonanKertas pk where pk.idKertas = pkk.kertas.idKertas and pk.permohonan.idPermohonan = :idMohon and pkk.subtajuk = :subtajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("subtajuk", jabatanTeknikal);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertasKandungan findKertasKandunganByIdKertas(String idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk where pkk.kertas.idKertas = :idKertas");
        q.setString("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public PermohonanKertas findKertasByTajuk(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idMohon and pk.tajuk = :tajuk");
        q.setString("idMohon", idPermohonan);
        q.setString("tajuk", tajuk);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanUkur findMohonUkurByMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanUkur pk where pk.permohonan.idPermohonan = :idMohon");
        q.setString("idMohon", idPermohonan);
        return (PermohonanUkur) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonanPTD(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan and lt.laporanUntuk='PTD'");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (LaporanTanah) q.uniqueResult();
    }

    public LaporanTanah findLaporanTanahByIdPermohonan2(Long idLapor) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.idLaporan = :idLapor");
        q.setLong("idLapor", idLapor);
        return (LaporanTanah) q.uniqueResult();
    }

    public List<LaporanTanah> findLaporanTanahByIdPermohonan3(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.LaporanTanah lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    /**
     * Method to find FasaPermohanan By Id By Imran Khan
     *
     * @param idPermohonan
     * @param idAliran
     * @return FasaPermohonan
     *
     */
    public FasaPermohonan findFasaPermohonanByIdAliran(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = :idAliran order by lt.idFasa desc ");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        //return (FasaPermohonan) q.uniqueResult();
        if (q.list().size() > 1) {
            return (FasaPermohonan) q.list().get(0);
        } else {
            return (FasaPermohonan) q.uniqueResult();
        }
    }

    public FasaPermohonan findFasaPermohonanByIdAliranAndTKHHNotNULL(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = :idAliran and lt.tarikhHantar is not null");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (FasaPermohonan) q.uniqueResult();
    }

    // Added By NageswaraRao
    public PermohonanUkur findPermohonanUkurByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanUkur) q.uniqueResult();
    }

    // Added by NageswaraRao
    public PermohonanPengambilan findPermohonanPengambilanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.ambil.PermohonanPengambilan lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanPengambilan) q.uniqueResult();
    }

    // Added by NageswaraRao
    public List<PermohonanPlotPelan> senaraiPermohonanPlotPelanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.bilanganPlot is not null ORDER BY lt.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> senaraiPermohonanPlotPelanByIdPermohonanForH(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.bilanganPlot is not null AND lt.pemilikan.kod = 'H' ORDER BY lt.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> senaraiPermohonanPlotPelanByIdPermohonanForK(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.bilanganPlot is not null  AND lt.pemilikan.kod = 'K' ORDER BY lt.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    // Added by NageswaraRao
    public PermohonanPlotPelan getMaxNoPTofPermohonanPlotPelan() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.noPt=(Select MAX(lt1.noPt) from etanah.model.PermohonanPlotPelan lt1)");
        return (PermohonanPlotPelan) q.uniqueResult();
    }

    // Added by NageswaraRao
    @Transactional
    public void simpanPermohonanPlotPelan(PermohonanPlotPelan permohonanPlotPelan) {
        permohonanPlotPelanDAO.saveOrUpdate(permohonanPlotPelan);
    }

    // Added by NageswaraRao
    public PermohonanUkur getMaxNoPUofPermohonanUkur(String year) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanUkur lt where lt.noPermohonanUkur=(Select MAX(lt1.noPermohonanUkur) from etanah.model.PermohonanUkur lt1 where lt1.noPermohonanUkur LIKE :year)");
        q.setString("year", "%" + year + "%");
        return (PermohonanUkur) q.uniqueResult();
    }

    // Added by NageswaraRao for Delete
    public List<PermohonanKertasKandungan> getSenaraiPermohonanKertasKandunganForDelete(int tableIndex) {
        System.out.println("---------Service--------::" + tableIndex);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt where (lt.subtajuk LIKE :jumlah OR lt.subtajuk LIKE :pelepasan OR lt.subtajuk LIKE :bumi OR lt.subtajuk LIKE :bukanBumi) ORDER BY lt.bil desc");
        q.setString("jumlah", "jumlah" + tableIndex + "%");
        q.setString("pelepasan", "pelepasan" + tableIndex + "%");
        q.setString("bumi", "bumi" + tableIndex + "%");
        q.setString("bukanBumi", "bukanBumi" + tableIndex + "%");
        return q.list();
    }

    // Added by NageswaraRao for RingkasRayuan
    public List<PermohonanKertasKandungan> getSenaraiTingkatsOfKertasKandungan(Long idKertas, int tableIndex) {
        System.out.println("---------Service--------::" + tableIndex);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE (lt.kertas.idKertas = :idKertas AND lt.subtajuk LIKE :tingkat) ORDER BY lt.bil asc");
        q.setLong("idKertas", idKertas);
        q.setString("tingkat", "jenis" + tableIndex + "%");
        return q.list();

    }

    public PermohonanKertasKandungan getPermohonanKertasKandunganBySubtajuk(Long idKertas, String subtajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE lt.kertas.idKertas = :idKertas AND lt.subtajuk = :subtajuk");
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", subtajuk);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    // Added code for Rayuan Pelepasan Kuota
    public List<PermohonanKertasKandungan> getPermohonanKertasKandByIndex(Long idKertas, int tableNo) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE lt.kertas.idKertas = :idKertas AND lt.subtajuk LIKE :subtajuk ORDER BY lt.bil asc");
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", tableNo + "%");
        return q.list();
    }

    // Added code for Rayuan Pelepasan Kuota
    public PermohonanKertasKandungan getPermohonanKertasKandungan(Long idKertas, String subtajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE lt.kertas.idKertas = :idKertas AND lt.subtajuk = :subtajuk");
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", subtajuk);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    // Added code for Rayuan Pelepasan Kuota
    public List<PermohonanKertasKandungan> deletePermohonanKertasKandungan(Long idKertas, int tableIndex) {
        System.out.println("---------Service--------::" + tableIndex);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt where lt.kertas.idKertas = :idKertas AND lt.subtajuk LIKE :subtajuk ORDER BY lt.bil desc");
        q.setLong("idKertas", idKertas);
        q.setString("subtajuk", tableIndex + "%");
        return q.list();
    }

    // Added code for Rayuan Pelepasan Kuota
    public PermohonanKertasKandungan getMaxPermohonanKertasKandungan(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE lt.bil=(Select MAX(lt1.bil) from etanah.model.PermohonanKertasKandungan lt1 WHERE lt1.kertas.idKertas = :idKertas1) AND  lt.kertas.idKertas = :idKertas2");
        q.setLong("idKertas1", idKertas);
        q.setLong("idKertas2", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    // Added by NageswaraRao for RingkasRayuan
    public PermohonanKertasKandungan getMaxBillNoOfPermohonanKertasKandungan(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt where lt.bil=(Select MAX(lt1.bil) from etanah.model.PermohonanKertasKandungan lt1 where lt1.kertas.idKertas = :idKertas and lt1.subtajuk is not null) AND lt.kertas.idKertas = :idKertas AND lt.subtajuk is not null");
        q.setLong("idKertas", idKertas);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    // Added by NageswaraRao for RingkasRayuan
    public List<PermohonanKertasKandungan> getMaxTableNoOfPermohonanKertasKandungan(Long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertasKandungan lt WHERE lt.kertas.idKertas = :idKertas AND lt.subtajuk is not null ORDER BY lt.subtajuk desc");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    // Added By NageswaraRao for NOPT
    public List<NoPt> getNoPTByIdPermohonan(String idPermohonan, int kodBpm) {
        System.out.println("idPermohonan:" + idPermohonan);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan AND lt.kodBpm.kod = :kodBpm ORDER BY lt.noPt asc");
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("kodBpm", kodBpm);
        return q.list();
    }

    // Added By NageswaraRao for NOPT
    public NoPt getMaxPTByIdPermohonan(int kodBpm, long startRange, long endRange) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.noPt=(Select MAX(lt1.noPt) from etanah.model.NoPt lt1 where lt1.kodBpm = " + kodBpm + " AND lt1.noPt >= :startRange AND lt1.noPt < :endRange)");
//         q.setInteger("kodBpm", kodBpm);
        q.setLong("startRange", startRange);
        q.setLong("endRange", endRange);
        return (NoPt) q.uniqueResult();
    }

    // Added By NageswaraRao for NOPT
    @Transactional
    public void simpanNoPt(NoPt noPt) {
        noPtDAO.saveOrUpdate(noPt);
    }

    @Transactional
    public void deleteNoPt(NoPt noPt) {
        noPtDAO.delete(noPt);
    }

    @Transactional
    public void deleteSK(SalinanKepada sk) {
        salinanKepadaDAO.delete(sk);
    }
    // Added By NageswaraRao for NOPT

    public List<PermohonanKertas> findLamaPermohonanKertasByIdPermohonan(String idPermohonan, String tajuk) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.tajuk = :tajuk");
        q.setString("idPermohonan", idPermohonan);
        q.setString("tajuk", tajuk);
        return q.list();
    }

    @Transactional
    public void simpanPermohonan(Permohonan permohonan) {
        permohonanDAO.saveOrUpdate(permohonan);
    }

    @Transactional
    public void simpanPermohonanManual(PermohonanManual permohonanManual) {
        permohonanManualDAO.saveOrUpdate(permohonanManual);
    }

    // Manually lock table
    public void test12() {
        System.out.println("----------test-----------");
        Session s = sessionProvider.get();
        try {
            // s.createSQLQuery("LOCK TABLE MOHON_KERTAS_KAND WRITE").executeUpdate();
            for (int i = 51; i <= 53; i++) {
                System.out.println("----------i----------" + i);
                String sql = "insert into mohon_kertas_kand  values(" + i + ",'05',15150,999,'','admin',SYSDATE ,'admin',SYSDATE ,'Test Data.111111111111')";
                SQLQuery sqlQuery = s.createSQLQuery(sql);
                sqlQuery.addEntity("noPT1", "etanah.model.PermohonanKertasKandungan", LockMode.WRITE);
                Thread.sleep(15000);
                sqlQuery.executeUpdate();
                s.flush();
            }
            //s.createSQLQuery("UNLOCK TABLE").executeUpdate();
        } catch (Exception e) {
            System.out.println("-----Exception-----test-----------");
            e.printStackTrace();
        }
        s.beginTransaction().commit();
    }

    public void test(int i, Session s) {
        System.out.println("----------test-----------");
        // Session s = sessionProvider.get();
        try {
            String sql = "insert into mohon_kertas_kand  values(" + i + ",'05',15150,999,'','admin',SYSDATE ,'admin',SYSDATE ,'Test Data.111111111111')";
            SQLQuery sqlQuery = s.createSQLQuery(sql);
            // sqlQuery.addEntity("noPT1", "etanah.model.PermohonanKertasKandungan", LockMode.WRITE);
            Thread.sleep(4000);
            sqlQuery.executeUpdate();
            s.flush();
        } catch (Exception e) {
            System.out.println("-----Exception-----test-----------");
            e.printStackTrace();
        }
    }

    public void test1(int i, Session s) {
        System.out.println("----------test1-----------");
        // Session s = sessionProvider.get();
        String sql = "insert into mohon_kertas_kand  values(" + i + ",'05',15150,999,'','admin',SYSDATE ,'admin',SYSDATE ,'Test Data.111111111111')";
        SQLQuery sqlQuery = s.createSQLQuery(sql);
        sqlQuery.addEntity("noPT1", "etanah.model.PermohonanKertasKandungan", LockMode.WRITE);
        sqlQuery.executeUpdate();
        s.flush();
    }

    // Added by NageswaraRao for Maklumat Tukar Syarat Nyata
    @Transactional
    public void simpanHakmilikPermohonan(HakmilikPermohonan hp) {
        hakmilikPermohonanDAO.saveOrUpdate(hp);
    }

    @Transactional
    public void simpanLaporanTanah(LaporanTanah lt) {
        laporantanahDAO.saveOrUpdate(lt);
    }

    @Transactional
    public void simpanLaporanTanahSempadan(LaporanTanahSempadan lts) {
        laporanTanahSempadanDAO.saveOrUpdate(lts);
    }

    public List<KodAgensi> findJabatanTeknikal() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ka from etanah.model.KodAgensi ka where ka.kategoriAgensi.kod = 'JTK'");
        return q.list();
    }

    public List<PermohonanRujukanLuar> findUlasanJabatanTek(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and (rl.agensi.kategoriAgensi.kod = 'JTK' or rl.agensi.kategoriAgensi.kod = 'ADN')");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findUlasanJabatanTekOnly(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and rl.agensi.kategoriAgensi.kod = 'JTK'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<LaporanTanahSempadan> findLaporanTanahSempadan(long idLapor) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.LaporanTanahSempadan rl where rl.laporanTanah.idLaporan = :idLapor");
        q.setLong("idLapor", idLapor);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findUlasanJabatanTek1(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanRujukanLuar> findUlasanJabatanTekLT(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and rl.kodRujukan.kod = 'WT'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanRujukanLuar findUlasanJabatanTek2(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and rl.kodRujukan.kod = 'MK'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public KodCawanganJabatan findAlamat(String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.KodCawanganJabatan rl where rl.cawangan.kod = :kodCaw");
        q.setString("kodCaw", kodCaw);
        return (KodCawanganJabatan) q.list().get(0);
    }

    public LaporanTanahSempadan findIdLaporTanahSempadan(String idLaporTanahSempadan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.LaporanTanahSempadan rl where rl.idLaporTanahSpdn = :idLaporTanahSpdn");
        q.setString("idLaporTanahSpdn", idLaporTanahSempadan);
        return (LaporanTanahSempadan) q.uniqueResult();
    }

    public List<KodBandarPekanMukim> findBPMBandar(String kodDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.KodBandarPekanMukim rl where rl.daerah.kod = :kodDaerah and rl.bandarPekanMukim between '40' and '69' ");
        q.setString("kodDaerah", kodDaerah);
        return q.list();
    }

    public List<KodBandarPekanMukim> findBPMPekan(String kodDaerah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.KodBandarPekanMukim rl where rl.daerah.kod = :kodDaerah and rl.bandarPekanMukim between '70' and '99' ");
        q.setString("kodDaerah", kodDaerah);
        return q.list();
    }

    public PermohonanRujukanLuar findUlasanAdun(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and rl.agensi.kategoriAgensi.kod = 'ADN'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTandatanganDokumen(PermohonanTandatanganDokumen tt) {
        permohonanTandatanganDokumenDAO.saveOrUpdate(tt);
    }

    public List<PermohonanTandatanganDokumen> findTtDokumen(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tt from etanah.model.PermohonanTandatanganDokumen tt where tt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    //find max kertas
    public PermohonanKertas findLatestKertas(String idPermohonan, String kodDoc) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.infoAudit.tarikhMasuk = (Select MAX(pk.infoAudit.tarikhMasuk) from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.kodDokumen.kod = :kodDoc)");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDoc", kodDoc);
        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertas findKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanKertas) q.uniqueResult();
    }

    public List<PermohonanKertas> ListFindKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //Newly Added 23-04-2011

    public List<PermohonanKertas> findSenaraiKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod ORDER BY mk.idKertas desc");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findPermohonanKertasByKod(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.permohonan.idPermohonan = :idPermohonan and mk.kodDokumen.kod = :kod");
        q.setString("kod", kod);
        q.setString("idPermohonan", idPermohonan);
        if (q.list() != null && !q.list().isEmpty()) {
            return (PermohonanKertas) q.list().get(0);
        } else {
            return (PermohonanKertas) q.uniqueResult();
        }
    }

    public List<KodKadarBayaran> findKodByrByUrusan(String urusan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kb from etanah.model.KodKadarBayaran kb where kb.kodUrusan.kod = :urusan and kb.kategori is not null");
        q.setString("urusan", urusan);
        return q.list();
    }

    public List<KodKadarBayaran> findKodByrByKod(String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kb from etanah.model.KodKadarBayaran kb where kb.kodUrusan.kod = :kod");
        q.setString("kod", kod);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findTuntutByIdMohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan =:idPermohonan and tk.kodTuntut.kod LIKE :kodTuntut");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodTuntut", "DEV%");
        return q.list();
    }

    public List<PermohonanTuntutanKos> findTuntutByKodTuntut(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan =:idPermohonan and tk.kodTuntut.kod in('DEV01', 'DEV02','DEV03','DEV14') order by tk.kodTuntut.kod ");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public KodTuntut findKodTuntutByName(String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTuntut kt where kt.nama = :name");
        q.setString("name", name);
        return (KodTuntut) q.uniqueResult();
    }

    @Transactional
    public void simpanPermohonanTuntutan(PermohonanTuntutan pt) {
        permohonanTuntutanDAO.saveOrUpdate(pt);
    }

    @Transactional
    public void simpanPermohonanTuntutanButiran(PermohonanTuntutanButiran tb) {
        permohonanTuntutanButiranDAO.saveOrUpdate(tb);
    }

    public KodTransaksiTuntut findKodTransTuntutByName(String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select kt from etanah.model.KodTransaksiTuntut kt where kt.nama = :name");
        q.setString("name", name);
        return (KodTransaksiTuntut) q.uniqueResult();
    }

    public PermohonanTuntutan findPermohonanTuntutanByKodTransName(String idPermohonan, String name) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.nama = :name AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("name", name);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public PermohonanTuntutanButiran findPermohonanTuntutButirByIdTuntutAndIdKos(Long idKos, Long idTuntut) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutanButiran pt where pt.permohonanTuntutanKos.idKos = :idKos  AND  pt.permohonanTuntutan.idTuntut = :idTuntut ");
        q.setLong("idKos", idKos);
        q.setLong("idTuntut", idTuntut);
        return (PermohonanTuntutanButiran) q.uniqueResult();
    }

    // Added By NageswaraRao for NOPT
    public NoPt getMaxPTByIdPermohonan(int kodBPM) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.noPt=(Select MAX(lt1.noPt) from etanah.model.NoPt lt1 where lt1.kodBpm = :kodBPM)");
        q.setInteger("kodBPM", kodBPM);
        return (NoPt) q.uniqueResult();
    }

    // Added By NageswaraRao for NoPT
    public List<HakmilikPermohonan> findHakmilikPermohonanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan ORDER BY  hp.id  asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan findHakmilikPermohonanByIdPermohonanDanIdhakmilik(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan and hp.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    // Added By NageswaraRao for NoPT
    public List<PermohonanPlotPelan> senaraiPermohonanPlotPelanByKodBPM(String idPermohonan, int kodBpm) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.hakmilikPermohonan.hakmilik.bandarPekanMukim.kod= :kodBpm AND lt.bilanganPlot is not null ORDER BY lt.idPlot asc");
//        Query q = s.createQuery("Select lt from etanah.model.PermohonanPlotPelan lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.bilanganPlot is not null ORDER BY lt.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("kodBpm", kodBpm);
        return q.list();
    }

    // Added By NageswaraRao for NoPT
    public SiriNoPt findSiriNoPtByKodBpm(int kodBpm) {
        System.out.println(" -----findSiriNoPtByKodBpm-------");
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.SiriNoPt lt where lt.kodBandarPekanMukim.kod = :kodBpm");
        q.setInteger("kodBpm", kodBpm);
        return (SiriNoPt) q.uniqueResult();
    }

    // Added By NageswaraRao for NoPT
    @Transactional
    public void simpanSiriNoPt(SiriNoPt siriNoPt) {
        siriNoPtDAO.saveOrUpdate(siriNoPt);
    }

    public HakmilikPermohonan findHakmilikPermohonanByIdHakmilik(String idPermohonan, String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select hp from etanah.model.HakmilikPermohonan hp where hp.permohonan.idPermohonan = :idPermohonan AND hp.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idHakmilik", idHakmilik);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pb from etanah.model.HakmilikPihakBerkepentingan pb where pb.jenis.kod in('PAG','PG','PGA','PKA','PKD','PKL','PKS') and pb.hakmilik.idHakmilik = :idHakmilik");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentinganAktif(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pb from etanah.model.HakmilikPihakBerkepentingan pb where pb.jenis.kod in('PAG','PG','PGA','PKA','PKD','PKL','PKS') and pb.hakmilik.idHakmilik = :idHakmilik and pb.aktif = 'Y' ");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findKertasKandByIdKertas(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.idKandungan asc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }

    public PermohonanKertas findLatestKertasByIdMohonAndTarikhMasuk(String idPermohonan, String kodDoc) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanKertas mk where mk.infoAudit.tarikhMasuk = (Select MAX(pk.infoAudit.tarikhMasuk) from etanah.model.PermohonanKertas pk where pk.permohonan.idPermohonan = :idPermohonan and pk.kodDokumen.kod = :kodDoc)");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDoc", kodDoc);
        return (PermohonanKertas) q.uniqueResult();
    }

// add by syaiful for notis N2A
    public List<PermohonanPihak> getSenaraiPmohonPihak(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.PermohonanPihak m WHERE m.permohonan.idPermohonan = :idMohon order by m.jenis.kod asc");
        q.setParameter("idMohon", idMohon);
        return q.list();
    }

    // Added new method for PYTN
    public NoPt findNoPTByIdPlotPelanAndKodBpm(long idPlot, Integer kodBpm) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.permohonanPlotPelan.idPlot = :idPlot AND pt.kodBpm.kod = :kodBpm");
        q.setLong("idPlot", idPlot);
        q.setInteger("kodBpm", kodBpm);
        return (NoPt) q.uniqueResult();
    }

    public NoPt findNoPTByIdPlotPelan(long idPlot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.permohonanPlotPelan.idPlot = :idPlot");
        q.setLong("idPlot", idPlot);
        return (NoPt) q.uniqueResult();
    }

    // add by syaiful for notis N2A/N7G
    public List<Notis> getListNotis2(String idMohon, String kodNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon AND m.kodNotis.kod = :kod_notis");
        q.setParameter("id_mohon", idMohon);
        q.setParameter("kod_notis", kodNotis);
        return q.list();
    }

    public List<Notis> getListNotis3(String idMohon) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Notis m WHERE m.permohonan.idPermohonan = :id_mohon");
        q.setParameter("id_mohon", idMohon);
        return q.list();
    }

    public List<NoPt> senaraiPlotHakmilikNoPTByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan and lt.permohonanPlotPelan.pemilikan.kod = :kodPemilikan ORDER BY lt.permohonanPlotPelan.noPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setCharacter("kodPemilikan", 'H');
        return q.list();
    }

    // add by syaiful for notis N2A simpan by idFolder
    public List<KandunganFolder> getListDokumen16H(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'N2A'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }
    // add by syaiful for notis N7G simpan by idFolder

    public List<KandunganFolder> getListDokumen7G(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'N7G'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    // modified by rajib for notis N7G simpan by idFolder
    public List<KandunganFolder> getListDokumen7G(long idFolder, String kodNotis7g) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = :kod_notis7g");
        q.setParameter("id_folder", idFolder);
        q.setParameter("kod_notis7g", kodNotis7g);
        return q.list();
    }

    // add by syaiful for notis N2A
    public PenasihatUndang getALLPenUndg() {
        String query = "SELECT m FROM etanah.model.PenasihatUndang m WHERE m.kodNegeri.kod = '04'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (PenasihatUndang) q.uniqueResult();
    }

    // add by syaiful for notis N2A
    public PenghantarNotis findPenghantarNotis(int idPenghantarNotis) {
        String query = "Select p FROM etanah.model.PenghantarNotis p WHERE p.idPenghantarNotis = :id";
        Query q = sessionProvider.get().createQuery(query);
        q.setInteger("id", idPenghantarNotis);
        return (PenghantarNotis) q.uniqueResult();
    }

    // add by syaiful for notis N2A
    @Transactional
    public long simpanOrUpdateDokumen(Dokumen dokumen) {
        long idDokumen = new Long(1L); // initialize long
        try {
            dokumen = dokumenDAO.saveOrUpdate(dokumen);
            idDokumen = dokumen.getIdDokumen();
        } catch (Exception ex) {
            LOG.error("simpanOrUpdateDokumen tidak berjaya :" + ex);
            ex.printStackTrace();
        }
        return idDokumen;
    }

    // add by syaiful for notis 16H
    @Transactional
    public void updateNotis(Notis l) {
        LOG.debug("Notis:start");
        notisDAO.update(l);
        LOG.debug("Notis:finish");
    }

    // add by syaiful for notis 16H for View()
    public List<Notis> getNotis(long idNotis) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("FROM etanah.model.Notis m WHERE m.idNotis = :id_notis");
        q.setParameter("id_notis", idNotis);
        return q.list();
    }

    public List<NoPt> senaraiPlotRizabNoPTByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan and lt.permohonanPlotPelan.pemilikan.kod = :kodPemilikan ORDER BY lt.permohonanPlotPelan.noPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setCharacter("kodPemilikan", 'R');
        return q.list();
    }

    public List<NoPt> senaraiPlotKerajaanNoPTByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan and lt.permohonanPlotPelan.pemilikan.kod = :kodPemilikan ORDER BY lt.permohonanPlotPelan.noPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setCharacter("kodPemilikan", 'K');
        return q.list();
    }

    public List<NoPt> senaraiPlotAmbilTanahKerajaanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan and lt.permohonanPlotPelan.pemilikan.kod = :kodPemilikan ORDER BY lt.permohonanPlotPelan.noPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setCharacter("kodPemilikan", 'A');
        return q.list();
    }

//    Added by NageswaraRao
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

//    Added by NageswaraRao
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
//syaiful for Notis

    public List<HakmilikPihakBerkepentingan> senaraiPihakBerkepentingan2(String idHakmilik) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pb from etanah.model.HakmilikPihakBerkepentingan pb where pb.jenis.kod in('PM') and pb.hakmilik.idHakmilik = :idHakmilik and pb.aktif = 'Y' ");
        q.setString("idHakmilik", idHakmilik);
        return q.list();
    }

    // Added By NageswaraRao
    public List<Pemohon> findPemohonByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public Pemohon findPemohonByIdPemohon(String idPemohon) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.idPemohon = :idPemohon");
        q.setString("idPemohon", idPemohon);
        return (Pemohon) q.uniqueResult();
    }

    public List<Pemohon> findPemohonByIdPihak(String idPermohonan, Long idPihak) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan and p.pihak.idPihak= :idPihak");
        q.setParameter("idPermohonan", idPermohonan);
        q.setLong("idPihak", idPihak);
        return q.list();
    }

    // for delete operation
    public List<PermohonanKertasKandungan> findKertasKandByIdKertasDesc(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil order by b.idKandungan desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        return q.list();
    }
    //Fasa Permohonan Find by idPermohonan

    public List<FasaPermohonan> findFasaPermohonanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        String orderby = " ORDER BY lt.infoAudit.tarikhMasuk asc";
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran LIKE '%laporantanah'" + orderby);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<FasaPermohonan> findFasaPermohonanByIdAliranList(String idPermohonan, String idAliran) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan "
                + "and lt.idAliran = :idAliran "
                + "ORDER BY lt.infoAudit.tarikhMasuk desc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return q.list();
    }

    public List<FasaPermohonan> findFasaPermohonanByIdPermohonan2(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List findFasaPermohonanSize(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran like 'terimabayaran%'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List findFasaPermohonanSizeMMK(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and lt.idAliran = 'terimabayaran'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanNota> findNotaPermohonanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        String orderby = " ORDER BY lt.infoAudit.tarikhMasuk asc";
//      Query q = s.createQuery("Select lt from etanah.model.PermohonanNota lt where lt.permohonan.idPermohonan = :idPermohonan and (lt.idAliran LIKE '%laporantanah' OR lt.idAliran LIKE 'semakderafperakuan' OR lt.idAliran LIKE 'perakuanmmknptd')"+ orderby);
        Query q = s.createQuery("Select lt from etanah.model.PermohonanNota lt where lt.permohonan.idPermohonan = :idPermohonan " + orderby);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }
    //added by Syaiful for lapor tanah syor

    @Transactional
    public void simpanFasaPermohonan2(FasaPermohonan fasaPermohonan) {
        fasaPermohonanDAO.saveOrUpdate(fasaPermohonan);
    }

    @Transactional
    public void simpanNotaPermohonan(PermohonanNota notaPermohonan) {
        permohonanNotaDAO.saveOrUpdate(notaPermohonan);
    }

    // Added by NageswaraRao
    @Transactional
    public void simpanPemohon(Pemohon pemohon) {
        pemohonDAO.saveOrUpdate(pemohon);
    }

    public List<PermohonanKertasKandungan> findKertasKandByIdKertasSubtajuk(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk  order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", "9.2.%");
        return q.list();
    }

    public List<PermohonanKertasKandungan> findPermohonanRayuanByIdKertasSubtajuk(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk  order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", "9.3.%");
        return q.list();
    }

    public List<PermohonanKertasKandungan> findKertasKandByIdKertasSubtajukDesc(long idKertas, int bil) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk  order by b.idKandungan desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", "8.2.%");
        return q.list();
    }

    public Transaksi findAkaunByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.Transaksi lt where lt.permohonan.idPermohonan = :idPermohonan and lt.dokumenKewangan.kodDokumen.kod = :kod");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", "RBY");
        return (Transaksi) q.uniqueResult();
    }

    // Added to display kodBangsa in Maklumat Pemohon
    public List<KodBangsa> getSenaraiKodBangsaByJenisPengenalan(String jenisPengenalan) {
        try {
            String query = "Select u from etanah.model.KodBangsa";
            if (jenisPengenalan.equals("B") || jenisPengenalan.equals("L") || jenisPengenalan.equals("P") || jenisPengenalan.equals("T") || jenisPengenalan.equals("I") || jenisPengenalan.equals("F")) {
                query += " u where u.kod in('MEL','CIN','IND','SIM','ASL','LN','JBL','PBD') order by u.nama";
            } else {
                query += " u where u.kod not in('MEL','CIN','IND','SIM','ASL','LN','JBL','PBD') order by u.nama";
            }
            Query q = sessionProvider.get().createQuery(query);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<PermohonanLaporanBangunan> getLaporBngnByIdLaporan(String idLaporan) {
//        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.laporanTanah.idLaporan = :idLaporan";
        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.permohonan.idPermohonan = :idLaporan and d.kategori='B'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idLaporan", idLaporan);
        return q.list();
    }

    public List<PermohonanLaporanBangunan> getLaporTnmnByIdLaporan(String idLaporan) {
//        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.laporanTanah.idLaporan = :idLaporan";
        String strQuery = "Select d from etanah.model.PermohonanLaporanBangunan d where d.permohonan.idPermohonan = :idLaporan and d.kategori='T'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("idLaporan", idLaporan);
        return q.list();
    }

    @Transactional
    public void simpanMohonAsal(PermohonanAsal permohonanAsal) {
        permohonanAsalDAO.saveOrUpdate(permohonanAsal);
    }

    public PermohonanAsal findPermohonanAsal(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pa from etanah.model.PermohonanAsal pa where pa.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanAsal) q.uniqueResult();
    }

    public PermohonanAsal findPermohonanAsalIdSblm(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pa from etanah.model.PermohonanAsal pa where pa.idPermohonanAsal = :idPermohonanAsal");
        q.setString("idPermohonanAsal", idPermohonan);
        return (PermohonanAsal) q.uniqueResult();
    }

    public List<PermohonanKertasKandungan> findSenaraiKertasKandunganByIdKertas(long idKertas) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pkk from etanah.model.PermohonanKertasKandungan pkk where pkk.kertas.idKertas = :idKertas ORDER BY pkk.idKandungan asc");
        q.setLong("idKertas", idKertas);
        return q.list();
    }

    @Transactional
    public void simpanPermohonanLaporCerun(PermohonanLaporanCerun laporCerun) {
        permohonanLaporanCerunDAO.saveOrUpdate(laporCerun);
    }

    public List<PermohonanLaporanCerun> findLaporCerunListByIdPermohonan(String idPermohonan) {
//        String query = "SELECT b FROM etanah.model.PermohonanLaporanCerun b where b.idPermohonan.idPermohonan = '"+idPermohonan+"'";
        String query = "SELECT b FROM etanah.model.PermohonanLaporanCerun b where b.idPermohonan.idPermohonan = '" + idPermohonan + "'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
//        q.setParameter("idPermohonan", idPermohonan);
        return q.list();

    }

    public List<PermohonanRujukanLuar> getSenaraiRujLuarByIDPermohonanAgensi(String idPermohonan) {
//      String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan and ( b.kodRujukan.kod = 'FL' OR  b.agensi.kategoriAgensi.kod = 'ADN' ) ORDER BY b.idRujukan ASC";
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan  ORDER BY b.agensi.kategoriAgensi.kod ASC";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanLaporanUlasan findPemohonLaporanUlasanByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanLaporanUlasan) q.uniqueResult();
    }

    public List<PermohonanLaporanUlasan> findPemohonLaporanUlasanByIdPermohonanList(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.PermohonanLaporanUlasan p WHERE p.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public KodRizab findKodRizab(int kodRizab) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p FROM etanah.model.KodRizab p WHERE p.kod = :kod");
        q.setInteger("kod", kodRizab);
        return (KodRizab) q.uniqueResult();
    }

    public PermohonanTandatanganDokumen findTandatanganDokumenByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select td FROM etanah.model.PermohonanTandatanganDokumen td WHERE td.permohonan.idPermohonan = :idPermohonan and td.trhTt is not NULL");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public List<KodKecerunanTanah> findKodKecerunanFilterByidPermohonan(String idPermohonan) {
//        String findKodCerun = "select a from etanah.model.KodKecerunanTanah a where a.kod NOT IN (select b.kodCerunanTanah.kod from etanah.model.PermohonanLaporanCerun b where b.idPermohonan.idPermohonan = :idPermohonan)";
        String findKodCerun = "select a from etanah.model.KodKecerunanTanah a where a.kod NOT IN (select b.kodCerunanTanah.kod from etanah.model.PermohonanLaporanCerun b where b.idPermohonan.idPermohonan = '" + idPermohonan + "')";
        Session s = sessionProvider.get();
        Query q = s.createQuery(findKodCerun);
//        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Dokumen> getDokumenByIdPenguna(String format1, String format2, String userName) {
        String strQuery = "Select d from etanah.model.Dokumen d where (d.format = :format1 OR d.format = :format2) AND d.infoAudit.dimasukOleh.nama = :userName";
        Session session = sessionProvider.get();
        Query q = session.createQuery(strQuery);
        q.setString("format1", format1);
        q.setString("format2", format2);
        q.setString("userName", userName);
        return q.list();
    }

    public PermohonanTandatanganDokumen findPermohonanDokTTByIdPermohonan(String idPermohonan, String kodDokumen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.PermohonanTandatanganDokumen p where p.permohonan.idPermohonan = :idPermohonan AND p.kodDokumen.kod = :kodDokumen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public List<PermohonanTandatanganDokumen> findPermohonanDokTTByIdPermohonanrehy(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mk from etanah.model.PermohonanTandatanganDokumen mk where mk.permohonan.idPermohonan = :idPermohonan)");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void deleteImejLaporanByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.ImejLaporan p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    public Notis findNotisByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select n from etanah.model.Notis n where n.permohonan.idPermohonan =:idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        if (!q.list().isEmpty()) {
            return (Notis) q.list().get(0);
        } else {
            return (Notis) q.uniqueResult();
        }

    }

    @Transactional
    public void deleteKandunganFolderByIdDokumen(Long folderId, Long dokumenId) {
        String query = "DELETE FROM etanah.model.KandunganFolder p WHERE p.folder.folderId = :folderId AND p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("folderId", folderId);
        q.setLong("dokumenId", dokumenId);
        q.executeUpdate();
    }

    @Transactional
    public void deleteDokumenCapaianByIdDokumen(Long dokumenId) {
        String query = "DELETE FROM etanah.model.DokumenCapaian p WHERE  p.dokumen.idDokumen = :dokumenId";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("dokumenId", dokumenId);
        int rowCount = q.executeUpdate();
    }

    @Transactional
    public void deleteAll2(Dokumen d) {
        dokumenDAO.delete(d);
    }

    public PermohonanTuntutanBayar findTuntutBayarByIdDokumen(String idKewDok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tb from etanah.model.PermohonanTuntutanBayar tb where tb.dokumenKewangan.idDokumenKewangan =:idKewDok");
        q.setString("idKewDok", idKewDok);
        return (PermohonanTuntutanBayar) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohonTuntutKosByPremium(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan = :idPermohonan and tk.kodTuntut.kod = 'DEV04'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanKos findMohonTuntutKos(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public PermohonanTuntutanBayar findMohonTuntuBayarByIdmohonAndIdKewDok(String idMohon, String idKewDok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tb from etanah.model.PermohonanTuntutanBayar tb where tb.dokumenKewangan.idDokumenKewangan =:idKewDok and tb.permohonan.idPermohonan =:idMohon");
        q.setString("idMohon", idMohon);
        q.setString("idKewDok", idKewDok);
        return (PermohonanTuntutanBayar) q.uniqueResult();
    }

    public PermitSahLaku findPermitSahLakuByIdKosPermit(Long idKos) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select psl from etanah.model.PermitSahLaku psl where psl.permit.idPermit =:idKos");
        q.setLong("idKos", idKos);
        return (PermitSahLaku) q.uniqueResult();
    }

    public PermitItem findPermitItemByIdPermit(Long idKos) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pi from etanah.model.PermitItem pi where pi.permit.idPermit =:idKos");
        q.setLong("idKos", idKos);
        return (PermitItem) q.uniqueResult();
    }

    public PermitItemKuantiti findPermitItemKNTTByIdPermitItem(Long idKos) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pi from etanah.model.PermitItemKuantiti pi where pi.permitItem.idPermitItem =:idKos");
        q.setLong("idKos", idKos);
        return (PermitItemKuantiti) q.uniqueResult();
    }

    public PermitStrukturDiluluskan findPermitStrukturLulusByIdPermit(Long idKos) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select psld from etanah.model.PermitStrukturDiluluskan psld where psld.permit.idPermit =:idKos");
        q.setLong("idKos", idKos);
        return (PermitStrukturDiluluskan) q.uniqueResult();
    }

    public PermohonanTuntutanBayar findTuntuBayarByPremium(Long idKos) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tb from etanah.model.PermohonanTuntutanBayar tb where tb.permohonanTuntutanKos.idKos =:idKos");
        q.setLong("idKos", idKos);
        return (PermohonanTuntutanBayar) q.uniqueResult();
    }

    public List<Transaksi> findTransaksiNotis(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select t from etanah.model.Transaksi t where t.permohonan.idPermohonan = :idPermohonan and t.kodTransaksi.kod = '12107'");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Transaksi> findTransaksiByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select t from etanah.model.Transaksi t where t.permohonan.idPermohonan = :idPermohonan and t.kodTransaksi.kod in ('40040','12110', '12111', '12112')");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanLaporanPelan> findLaporanPelanByidPermohonanList(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanPelan h where  h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public Transaksi findTransaksiByIdKewDok(String idKewDok) {
        String query = "SELECT h FROM etanah.model.Transaksi h where  h.dokumenKewangan.idDokumenKewangan = :idKewDok";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idKewDok", idKewDok);
        return (Transaksi) q.uniqueResult();
    }

    public Permohonan findPermohonanByIdPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.Permohonan h where  h.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Permohonan) q.uniqueResult();
    }

    public PermohonanLaporanPelan findLaporanPelanByidPermohonan(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.PermohonanLaporanPelan h where  h.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);

        return (PermohonanLaporanPelan) q.uniqueResult();
    }

    @Transactional
    public void simpanNota(PermohonanNota permohonanNota) {
        permohonanNotaDAO.saveOrUpdate(permohonanNota);
    }

    @Transactional
    public void simpanPermitLPSRekod(PermitLPSRekod permitLPSRekod) {
        permitLPSRekodDAO.saveOrUpdate(permitLPSRekod);
    }

    public PermohonanNota findNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "Select p FROM etanah.model.PermohonanNota p WHERE p.permohonan.idPermohonan = :idPermohonan AND p.idAliran = :idAliran";
        Session session = sessionProvider.get();
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return (PermohonanNota) q.uniqueResult();
    }

    public List<PermohonanNota> findListNotaByIdMohonDesc(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan order by p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanNota> findListNotaByIdMohon(String idPermohonan, String stageId) {
        String query = "SELECT p FROM etanah.model.PermohonanNota p where p.permohonan.idPermohonan = :idPermohonan AND p.idAliran != :idAliran order by p.infoAudit.tarikhMasuk desc";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", stageId);
        return q.list();
    }

    public List<PermohonanKertasKandungan> findKertasKandByIdKertasSubtajuk(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk LIKE :subTajuk order by b.idKandungan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk + "%");
        return q.list();
    }
// added by Syaiful

    public List<Pemohon> findSenaraiPemohonIndividuByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan and p.pihak.jenisPengenalan.kod in ('B','I','K','L','T','F','0','P')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }
// added by Syaiful

    public List<Pemohon> findSenaraiPemohonSyarikatByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan and p.pihak.jenisPengenalan.kod not in ('B','I','K','L','T','F','0','P')");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> findSenaraiPemohon(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

// added by Rajib
    public List<Pemohon> findSenaraiPemohonIndividuByIdPermohonanAscIdPihak(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan and nvl(p.pihak.jenisPengenalan.kod,'0') in ('B','I','K','L','T','F','0','P') order by p.pihak.idPihak asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }
// added by Rajib

    public List<Pemohon> findSenaraiPemohonSyarikatByIdPermohonanAscIdPihak(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pemohon p where p.permohonan.idPermohonan= :idPermohonan and p.pihak.jenisPengenalan.kod not in ('B','I','K','L','T','F','0','P') order by p.pihak.idPihak asc");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public ImejLaporan findImejlaporan(long idLaporan, long idDok) {
        String query = "Select p FROM etanah.model.ImejLaporan p WHERE p.laporanTanah.idLaporan = :idLaporan and p.dokumen.idDokumen= :idDokumen";
        Query q = sessionProvider.get().createQuery(query);
        q.setParameter("idLaporan", idLaporan);
        q.setParameter("idDokumen", idDok);
        return (ImejLaporan) q.uniqueResult();
    }

    public List<PihakPengarah> findPihakPengarahByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT pp FROM etanah.model.PihakPengarah pp WHERE pp.pihak.idPihak IN(SELECT p.pihak.idPihak FROM etanah.model.Pemohon p where p.permohonan.idPermohonan = :idPermohonan)");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    //added by wani
    public PermohonanTuntutan findMohonTuntutanByIdAsal(String idAsal) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.permohonan.idPermohonan = :idAsal");
        q.setString("idAsal", idAsal);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public List<PermohonanTuntutanButiran> findTuntutanButiranByIdTuntutan(Long idTuntut) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pb from etanah.model.PermohonanTuntutanButiran pb where pb.permohonanTuntutan.idTuntut = :idTuntut");
        q.setLong("idTuntut", idTuntut);
        return q.list();
    }

    public List<PermohonanTuntutanKos> findTuntutanKosByIdAsal(String idAsal) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pk from etanah.model.PermohonanTuntutanKos pk where pk.permohonan.idPermohonan = :idAsal");
        q.setString("idAsal", idAsal);
        return q.list();
    }

    public DokumenKewangan findDokumenKewanganByIdKewDok(String idKewDok) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select dk from etanah.model.DokumenKewangan dk where dk.idDokumenKewangan = :idKewDok");
        q.setString("idKewDok", idKewDok);
        return (DokumenKewangan) q.uniqueResult();
    }
// Find Imej - syaiful

    public List<ImejLaporan> findImejlaporan(long idLaporan) {
        String query = "Select p FROM etanah.model.ImejLaporan p WHERE p.laporanTanah.idLaporan = :idLaporan order by p.idImej asc";
        Query q = sessionProvider.get().createQuery(query);
        q.setLong("idLaporan", idLaporan);
        return q.list();
    }

    public Dokumen saveDokumen(String dokumenPath, FileBean fileToBeUpload, Long id, InfoAudit ia, Permohonan permohonan) throws Exception {

        Dokumen doc = new Dokumen();
        KodDokumen kd = new KodDokumen();
        KodKlasifikasi kodk = new KodKlasifikasi();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        kd = kodDokumenDAO.findById("GLT");
        doc.setTajuk(kd.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kd);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);
        String fileName = fileToBeUpload.getFileName();
        DMSUtil dmsUtil = new DMSUtil();
        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
        updatePathDokumen(fizikalPath, doc, fileToBeUpload.getContentType());

        return doc;
    }

    public Dokumen saveDokumenNotis(String dokumenPath, FileBean fileToBeUpload, Notis notis, InfoAudit ia, Permohonan permohonan, KodDokumen kodDok) throws Exception {
        LOG.info("######DevServices saveDokumenNotis idNotis" + notis.getIdNotis());

        Dokumen doc = new Dokumen();
        Dokumen doc2 = dokumenDAO.findById(notis.getBuktiPenerimaan().getIdDokumen());

        KodDokumen kd = new KodDokumen();
        KodKlasifikasi kodk = new KodKlasifikasi();
        KodKlasifikasi klasifikasiAm = kodKlasifikasiDAO.findById(1);
        String fileName = fileToBeUpload.getFileName();
        DMSUtil dmsUtil = new DMSUtil();
        String fizikalPath = dmsUtil.getFizikalPath(permohonan) + File.separator + fileName;
//        kd = kodDokumenDAO.findById(kodDok);

        doc.setTajuk(kodDok.getNama());
        doc.setInfoAudit(ia);
        doc.setKodDokumen(kodDok);
        doc.setKlasifikasi(klasifikasiAm);
        doc.setNoVersi("1.0");
        doc = dokumenDAO.saveOrUpdate(doc);

        if (doc2 != null) {
            doc2.setTajuk(kodDok.getNama());
            doc2.setInfoAudit(ia);
            doc2.setNamaFizikal(fizikalPath);
            doc2.setKlasifikasi(klasifikasiAm);
            doc2.setNoVersi("1.0");
            dokumenDAO.saveOrUpdate(doc2);

        }

        FileUtil fileUtil = new FileUtil(dmsUtil.getDMSPath(permohonan));
        fileUtil.saveFile(fileName, fileToBeUpload.getInputStream());

        updatePathDokumen(fizikalPath, doc, fileToBeUpload.getContentType());

        return doc;
    }

    @Transactional
    private void updatePathDokumen(String namaFizikal, Dokumen d, String format) {
        // Dokumen d = dokumenDAO.findById(idDokumen);
//        d.setTajuk("sas");
        d.setNamaFizikal(namaFizikal);
        d.setFormat(format);
        Transaction tx = sessionProvider.get().getTransaction();
        tx.begin();
        LOG.info("Dokumen: " + d);
        d = dokumenDAO.saveOrUpdate(d);
        if (d != null) {
            tx.commit();
        } else {
            tx.rollback();
        }
    }

    @Transactional
    public void saveImej(ImejLaporan imejLaporan) {
        imejLaporanDAO.save(imejLaporan);
    }

    public List<PermohonanKertas> findMohonanKertasByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public HakmilikPermohonan findByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        LOG.info("query ::" + q);

        return (HakmilikPermohonan) q.list().get(0);
    }

    public HakmilikPermohonan findByIdPermohonanAndPermitForCarianPermit(String idPermohonan, String noPermit) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.permit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return (HakmilikPermohonan) q.uniqueResult();
    }

    public HakmilikPermohonan findByIdPermohonanDanIdMh(String idPermohonan, long idMh) {
        String query = "SELECT b FROM etanah.model.HakmilikPermohonan b where b.permohonan.idPermohonan = :idPermohonan and b.id = :idMh";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idMh", idMh);
        LOG.info("query ::" + q);

        return (HakmilikPermohonan) q.uniqueResult();
    }

    public Permohonan findByIdPermohonanFilterByUrusan(String idPermohonan, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonan and b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodUrusan", kodUrusan);
        LOG.info("query ::" + q);

        return (Permohonan) q.uniqueResult();
    }

    public Permohonan findPermohonanMHByIdSebelumAndKodUrusan(String idPermohonan, String kodUrusan) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.permohonanSebelum.idPermohonan = :idPermohonan and b.kodUrusan.kod = :kodUrusan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodUrusan", kodUrusan);
        LOG.info("query ::" + q);

        if (!q.list().isEmpty()) {
            return (Permohonan) q.list().get(0);
        } else {
            return null;
        }
        //return (Permohonan) q.list().get(0);
    }

    public NoPt findNoPtByIdHakmilikPermohonan(long id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.NoPt b where b.hakmilikPermohonan.id = :id");
        q.setLong("id", id);
        return (NoPt) q.uniqueResult();
    }

    public List<NoPt> findNoPtListByIdHakmilikPermohonan(long id) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT b FROM etanah.model.NoPt b where b.hakmilikPermohonan.id = :id");
        q.setLong("id", id);
        return q.list();
    }

    public PermohonanRujukanLuar findUlasanPerRujLuar(String idPermohonan, String kod) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.PermohonanRujukanLuar rl where rl.permohonan.idPermohonan = :idPermohonan and rl.kodDokumenRujukan.kod = :kod");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kod", kod);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    public PermohonanTuntutan findPermohonanTuntutanByIdMohonAndKodTransTuntut(String idPermohonan, String kodTrans) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.kodTransaksiTuntut.kod = :kodTrans AND pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("kodTrans", kodTrans);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    public List<Ansuran> findAnsuranListByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select rl from etanah.model.Ansuran rl where rl.permohonan.idPermohonan = :idPermohonan");
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

    public List<HakmilikPihakBerkepentingan> findHakmilikPihakByKod(String idHakmilik, String jenisPihak) {
        String query = "Select h from etanah.model.HakmilikPihakBerkepentingan h where h.hakmilik.idHakmilik = :idHakmilik "
                + "and h.aktif='Y' and h.jenis.kod like :kod";
        Query q = sessionProvider.get().createQuery(query).setString("idHakmilik", idHakmilik).setString("kod", jenisPihak);
        return q.list();
    }

//    Added by NageswaraRao for GIS Integration @Melaka 27/09/12
    public List<FasaPermohonan> findFasaPermohonanByIdAliranGIS(String idPermohonan) {
        String query = "Select fp from etanah.model.FasaPermohonan fp where fp.permohonan.idPermohonan = :idPermohonan AND fp.idAliran IN('g_charting_permohonan','g_charting_keputusan')";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertasKandungan findKertasKandByIdBilAndSubtajuk(long idKertas, int bil, String subTajuk) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKandungan b where b.kertas.idKertas = :idKertas and b.bil = :bil and b.subtajuk = :subTajuk";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        q.setInteger("bil", bil);
        q.setString("subTajuk", subTajuk);
        return (PermohonanKertasKandungan) q.uniqueResult();
    }

    public List<PermohonanKertasButiran> findKertasButiranByIdKand(long idKand) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasButiran b where b.kertasKandungan.idKandungan = :idKand order by b.idKertasBtr";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKand", idKand);
        return q.list();
    }

    public List<PermohonanKertasKeputusan> findKertasKeputusanByIdKand(long idKand) {
        String query = "SELECT b FROM etanah.model.PermohonanKertasKeputusan b where b.kertasKandungan.idKandungan = :idKand order by b.idKertasKpsn";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKand", idKand);
        return q.list();
    }

    public HakmilikPerbicaraan findHakmilikBicaraByIdMH(long idMH) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.ambil.HakmilikPerbicaraan m WHERE m.hakmilikPermohonan.id = :idMH");
        q.setParameter("idMH", idMH);
        return (HakmilikPerbicaraan) q.uniqueResult();
    }

    public NoPt findNolotByIdPlot(long idPlot) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.NoPt m WHERE m.permohonanPlotPelan.idPlot = :idPlot");
        q.setParameter("idPlot", idPlot);
        return (NoPt) q.uniqueResult();
    }

    public List<NoPt> findListNoPtByIdMohon(long idPlot) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.NoPt m WHERE m.permohonanPlotPelan.idPlot = :idPlot");
        q.setParameter("idPlot", idPlot);
        return q.list();
    }

    public String findNoLotGisPelanByPT(String idPermohonan, String noPT) {
        String returnLot = new String();
        List<PelanGIS> list = findPelanGISB1ByMohonNoPT(idPermohonan, noPT);
        if (list.size() > 0) {
            returnLot = list.get(0).getPelanGISPK().getNoLot();
        }

        return returnLot;
    }

    private List<PelanGIS> findPelanGISB1ByMohonNoPT(String idPermohonan, String noPT) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.noPt = :noPT and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPT", noPT);
        return q.list();
    }

    public String findNoLotGisPelanByIDMohon(String idPermohonan) {
        String returnLot = new String();
        List<PelanGIS> list = findPelanGISB1ByMohon(idPermohonan);
        if (list.size() > 0) {
            returnLot = list.get(0).getPelanGISPK().getNoLot();
        }

        return returnLot;
    }

    private List<PelanGIS> findPelanGISB1ByMohon(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.jenisPelan = 'B1'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanKertas> findMohonanKertasByKodUrusan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.kodDokumen.kod IN('MMK','MMKT') order by lt.idKertas asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public PermohonanKertas findPermohonanKertasByIdMohonAndKodDokumen(String idPermohonan, String kodDokumen) {
        PermohonanKertas permohonanKertas = null;
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.PermohonanKertas lt where lt.permohonan.idPermohonan = :idPermohonan AND lt.kodDokumen.kod = :kodDokumen order by lt.idKertas desc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);
        System.out.println("------findPermohonanKertasByIdMohonAndKodDokumen---------:" + q.list().isEmpty());
        if (!q.list().isEmpty()) {
            permohonanKertas = (PermohonanKertas) q.list().get(0);
        }
        return permohonanKertas;
    }

    public Pihak findPemohonAlamatSuratEqualAlamatByIdPihak(long idPihak) {
        Session s = sessionProvider.get();
        String query = "Select phk FROM etanah.model.Pihak phk,etanah.model.PihakAlamatTamb pat WHERE phk.idPihak = :idPihak and phk.idPihak = pat.pihak.idPihak "
                + "and ( (pat.alamatKetiga1 = phk.alamat1 or (phk.alamat1 is null and pat.alamatKetiga1 is null)) "
                + "and (pat.alamatKetiga2 = phk.alamat2 or (phk.alamat2 is null and pat.alamatKetiga2 is null)) "
                + "and (pat.alamatKetiga3 = phk.alamat3 or (phk.alamat3 is null and pat.alamatKetiga3 is null)) "
                + "and (pat.alamatKetiga4 = phk.alamat4 or (phk.alamat4 is null and pat.alamatKetiga4 is null)) "
                + "and (pat.alamatKetigaPoskod = phk.poskod or (phk.poskod is null and pat.alamatKetigaPoskod is null)) "
                + "and (pat.alamatKetigaNegeri = phk.negeri or (phk.negeri is null and pat.alamatKetigaNegeri is null)))";
        Query q = s.createQuery(query);
        q.setLong("idPihak", idPihak);
        return (Pihak) q.uniqueResult();
    }

    public List<Pihak> findPihakByNoIc(String noIc) {
        Session s = sessionProvider.get();
        String query = "Select phk FROM etanah.model.Pihak phk where phk.noPengenalan= :noIc";
        Query q = s.createQuery(query);
        q.setString("noIc", noIc);
        return q.list();
    }

// add by NageswaraRao for notis N5A simpan by idFolder
    public List<KandunganFolder> getListDokumen5A(long idFolder) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.KandunganFolder m WHERE m.folder.folderId = :id_folder and m.dokumen.kodDokumen.kod = 'N5A'");
        q.setParameter("id_folder", idFolder);
        return q.list();
    }

    public void deleteNoPtByIdPlot(long idPlot) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("delete from etanah.model.NoPt pt where pt.permohonanPlotPelan.idPlot = :idPlot");
        q.setLong("idPlot", idPlot);
        int count = q.executeUpdate();
        System.out.println("No.of records deleted :" + count);
    }

    public List<NoPt> findNoPTListByIdPlotPelan(long idPlot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.permohonanPlotPelan.idPlot = :idPlot");
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    public List<PermohonanPlotSyaratNyata> findPermohonanPlotSyaratNyata(long idPlot) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppst from etanah.model.PermohonanPlotSyaratNyata ppst where ppst.permohonanPlotPelan.idPlot = :idPlot");
        q.setLong("idPlot", idPlot);
        return q.list();
    }

    public List<NoPt> findNoPTListByIdPlotPelanAndKodBpm(long idPlot, Integer kodBpm) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.NoPt pt where pt.permohonanPlotPelan.idPlot = :idPlot AND pt.kodBpm.kod = :kodBpm");
        q.setLong("idPlot", idPlot);
        q.setInteger("kodBpm", kodBpm);
        return q.list();
    }

    public PermohonanTuntutan findDokumenRayuan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pt from etanah.model.PermohonanTuntutan pt where pt.permohonan.idPermohonan =:idPermohonan");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTuntutan) q.uniqueResult();
    }

    // Added By NageswaraRao for NOPT
    public List<NoPt> getJanaNoPTByIdPermohonanKodBpm(String idPermohonan, int kodBpm) {
        System.out.println("idPermohonan:" + idPermohonan);
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan AND lt.kodBpm.kod = :kodBpm AND lt.noPt is NOT NULL ORDER BY lt.noPt asc");
        q.setString("idPermohonan", idPermohonan);
        q.setInteger("kodBpm", kodBpm);
        return q.list();
    }

    public List<PermohonanPlotPelan> findPermohonanPlotPelanByIdPermohonanKodMLK(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.PermohonanPlotPelan ppp where ppp.permohonan.idPermohonan = :idPermohonan ORDER BY ppp.pemilikan.kod asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pihak> findUniquePemohonByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT ph from etanah.model.Pihak ph WHERE ph.idPihak IN(SELECT DISTINCT(p.pihak.idPihak) FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan= :idPermohonan GROUP BY p.pihak.idPihak)");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pihak> findUniquePemohonByIdPermohonan2(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT ph from etanah.model.Pihak ph "
                + "WHERE ph.idPihak IN(SELECT DISTINCT(p.pihak.idPihak) FROM etanah.model.Pemohon p WHERE p.permohonan.idPermohonan= :idPermohonan "
                + "AND p.pihak.idPihak IN (SELECT DISTINCT (hph.pihak.idPihak) FROM etanah.model.HakmilikPihakBerkepentingan hph WHERE hph.jenis.kod = 'PM' AND hph.aktif = 'Y')) ORDER BY ph.idPihak");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    //2/8/2016    
    //m.alamat.alamat1 != null sebab nak dapatkan pemohon Utama. Pastikan Pemohon Utama pada tab pemohon dipilih. (Stage PT Kemasukan/Utiliti Pemohon Utama)
    public List<Pemohon> getSelectedPemohon(String idPermohonan) {
        //String query = "SELECT m FROM etanah.model.Pemohon m where m.permohonan.idPermohonan = :idPermohonan and m.alamat.alamat1 is not null";
        //String query = "SELECT m FROM etanah.model.Pemohon m where m.permohonan.idPermohonan = :idPermohonan";
        //change by eda on 27/12/2017
        String query = "SELECT m FROM etanah.model.Pemohon m where m.permohonan.idPermohonan = :idPermohonan and m.nama is not null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pihak> getSelectedPemohonPihak(String idPermohonan) {
        String query = "SELECT p FROM etanah.model.Pemohon m, etanah.model.Pihak p where m.pihak.idPihak = p.idPihak and m.permohonan.idPermohonan = :idPermohonan ";
        //String query = "SELECT m FROM etanah.model.Pemohon m where m.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<Pemohon> getAllPemohon(String idPermohonan) {
        String query = "SELECT m FROM etanah.model.Pemohon m where m.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByHakmilik(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT hu from etanah.model.HakmilikUrusan hu WHERE  hu.aktif='Y' AND hu.kodUrusan.kod='GD' AND hu.hakmilik.idHakmilik IN(SELECT hp.hakmilik.idHakmilik FROM etanah.model.HakmilikPermohonan hp WHERE hp.permohonan.idPermohonan= :idPermohonan)");
        q.setParameter("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikUrusan> findHakmilikUrusanByHakmilikAndUrusan(String idHakmilik, String kodUrusan, String kodUrusan1) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("SELECT hu from etanah.model.HakmilikUrusan hu WHERE  hu.aktif='Y' AND hu.hakmilik.idHakmilik = :idHakmilik and ( hu.kodUrusan.kod= :kodUrusan or hu.kodUrusan.kod= :kodUrusan1)");
        q.setParameter("idHakmilik", idHakmilik);
        q.setParameter("kodUrusan", kodUrusan);
        q.setParameter("kodUrusan1", kodUrusan1);
        return q.list();
    }

    //      Find only one kump_bpel    
    public List<Pengguna> findPenggunaForOnlyOneKumpBpel(String kumpBPEL1, String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pengguna p where p.idPengguna in (select pp.pengguna.idPengguna from etanah.model.PenggunaPeranan pp "
                + "where pp.peranan.kumpBPEL IN (:kumpBPEL1)) AND p.status.kod = 'A' AND p.kodCawangan.kod = :kodCaw");
        //Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.perananUtama.kumpBPEL = :kumpBPEL1 AND ppp.kodCawangan.kod = :kodCaw");
        q.setString("kumpBPEL1", kumpBPEL1);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    //      Find only two kump_bpel    
    public List<Pengguna> findPenggunaByTwoKumpBpel(String kumpBPEL1, String kumpBPEL2, String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pengguna p where p.idPengguna in (select pp.pengguna.idPengguna from etanah.model.PenggunaPeranan pp "
                + "where pp.peranan.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2)) AND p.status.kod = 'A' AND p.kodCawangan.kod = :kodCaw");
        //Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.perananUtama.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2) AND ppp.kodCawangan.kod = :kodCaw");
        q.setString("kumpBPEL1", kumpBPEL1);
        q.setString("kumpBPEL2", kumpBPEL2);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    //    Find three kump_bpel
    public List<Pengguna> findPenggunaByKumpBpel(String kumpBPEL1, String kumpBPEL2, String kumpBPEL3, String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select p from etanah.model.Pengguna p where p.idPengguna in (select pp.pengguna.idPengguna from etanah.model.PenggunaPeranan pp "
                + "where pp.peranan.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2,:kumpBPEL3)) AND p.status.kod = 'A' AND p.kodCawangan.kod = :kodCaw");
        //Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.perananUtama.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2,:kumpBPEL3) AND ppp.kodCawangan.kod = :kodCaw");
        q.setString("kumpBPEL1", kumpBPEL1);
        q.setString("kumpBPEL2", kumpBPEL2);
        q.setString("kumpBPEL3", kumpBPEL3);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    //    Find three kump_bpel new
    public List<Pengguna> findPenggunaPerananByKumpBpel(String kumpBPEL1, String kumpBPEL2, String kumpBPEL3, String kodCaw) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp IN ( Select pgpg.pengguna from etanah.model.PenggunaPeranan pgpg where pgpg.peranan.kumpBPEL IN (:kumpBPEL1,:kumpBPEL2,:kumpBPEL3)) AND p.status.kod = 'A' AND ppp.kodCawangan.kod = :kodCaw");
        q.setString("kumpBPEL1", kumpBPEL1);
        q.setString("kumpBPEL2", kumpBPEL2);
        q.setString("kumpBPEL3", kumpBPEL3);
        q.setString("kodCaw", kodCaw);
        return q.list();
    }

    public List<KodAgensi> searchKodAgensiJTK(String kod, String kodAgensiNama, String kodNegeri, String daerah) {

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('JTK') "
                    + "AND u.kodNegeri.kod =:kodNegeri AND u.kodDaerah.kod is null OR u.kodDaerah.kod =:daerah "
                    + "AND u.kategoriAgensi.kod not in ('ADN')";

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

            q.setString("kodNegeri", kodNegeri);
            q.setString("daerah", daerah);
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

    public List<KodAgensi> searchKodAgensiADN(String kod, String kodAgensiNama, String kodNegeri, String daerah) {

        try {
            String query = "Select u from etanah.model.KodAgensi u WHERE u.kategoriAgensi.kod in ('ADN')  AND u.kodNegeri.kod =:kodNegeri AND u.kodDaerah.kod IN('00',:daerah)";

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

            q.setString("kodNegeri", kodNegeri);
            q.setString("daerah", daerah);
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

    public List<NoPt> senaraiNoPTByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.NoPt lt where lt.permohonanPlotPelan.permohonan.idPermohonan = :idPermohonan ORDER BY lt.permohonanPlotPelan.noPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    // added by rajib
    public List<Permohonan> getListPermohonanByIdSebelumAndKodUrusan(String idMohon, String kodUrusan) {
        Session session = sessionProvider.get();
        Query q = session.createQuery("SELECT m FROM etanah.model.Permohonan m WHERE m.permohonanSebelum.idPermohonan = :idMohon AND m.kodUrusan.kod = :kodUrusan");
        q.setParameter("idMohon", idMohon);
        q.setParameter("kodUrusan", kodUrusan);
        return q.list();
    }

    public List<KodBandarPekanMukim> searchKodBPM(String kodDaerah) {

        try {
            String query = "Select u from etanah.model.KodBandarPekanMukim u WHERE u.daerah.kod= :kodDaerah)";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kodDaerah", kodDaerah);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<SiriNoPt> searchSiriNoPtByKodBPM(String kodCaw, String kodBPM) {

        try {
            String query = "Select u from etanah.model.SiriNoPt u WHERE u.cawangan.kod= :kodCaw and u.kodBandarPekanMukim.kod= :kodBPM)";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kodCaw", kodCaw);
            q.setString("kodBPM", kodBPM);
            return q.list();
        } finally {
            //session.close();
        }
    }

    //Find Pengguna by Peranan Utama
//    public List<Pengguna> findPenggunaByPerananUtama(String pp1,String pp2,String pp3,String kodCaw) {
//        Session s = sessionProvider.get();
//        Query q = s.createQuery("Select ppp from etanah.model.Pengguna ppp where ppp.perananUtama.kod IN (:pp1,:pp2,:pp3) AND ppp.kodCawangan.kod = :kodCaw");
//        q.setString("pp1", pp1);
//        q.setString("pp2", pp2);
//        q.setString("pp3", pp3);
//        q.setString("kodCaw", kodCaw);
//        return q.list();
//    }
    public List<KodKegunaanTanah> getSenaraiKegunaanTanah0() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '0' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah1() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '1' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah2() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '2' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah3() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '3' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah4() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '4' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah5() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '5' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah8() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '8' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<KodKegunaanTanah> getSenaraiKegunaanTanah11() {
        try {
            Session s = sessionProvider.get();
            Query q = s.createQuery("from etanah.model.KodKegunaanTanah u where u.kategoriTanah.kod = '11' and u.aktif = 'Y'");
            return q.list();
        } finally {
        }
    }

    public List<Hakmilik> searchNoPtByKodBPM(String kodCaw, String kodBPM) {

        try {
            String query = "Select u from etanah.model.Hakmilik u WHERE u.cawangan.kod= :kodCaw and u.lot.kod= '3' and u.bandarPekanMukim.kod= :kodBPM";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("kodCaw", kodCaw);
            q.setString("kodBPM", kodBPM);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public String cariKodUrusanHSPendaftaran(String kodUrusan) {
        String kodUrusanREG = new String();
        if (kodUrusan.equals("PPCS")) {
            kodUrusanREG = "HSPS";
        } else if (kodUrusan.equals("PPCB")) {
            kodUrusanREG = "HSPB";
        } else if (kodUrusan.equals("PYTN")) {
            kodUrusanREG = "HSC";
        } else if (kodUrusan.equals("SBMS")) {
            kodUrusanREG = "HSSTB";
        } else if (kodUrusan.equals("PSBT")) {
            kodUrusanREG = "HSSB";
        }

        return kodUrusanREG;
    }

    @Transactional
    public void simpanKodAgency(KodAgensi kodAgensi) {
        kodAgensiDAO.saveOrUpdate(kodAgensi);
    }

    @Transactional
    public void simpanKodDun(KodDUN kodDun) {
        kodDUNDAO.saveOrUpdate(kodDun);
    }

    @Transactional
    public void simpanKodParlimen(KodKawasanParlimen kodKawasanParlimen) {
        kodKawasanParlimenDAO.saveOrUpdate(kodKawasanParlimen);
    }

    @Transactional
    public int countKKP() {
        return kodKawasanParlimenDAO.findAll().size();
    }

    public int countKdn() {
        return kodDUNDAO.findAll().size();
    }

    public int countKdagency() {
        return kodAgensiDAO.findAll().size();
    }

    public KodAgensi getMaxAgency() {
        String query = "SELECT b FROM etanah.model.KodAgensi b where b.kod =(select max (p.kod) from etanah.model.KodAgensi p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (KodAgensi) q.uniqueResult();
    }

    public List<KodAgensi> getSenaraiAgensi() {
        String query = "SELECT b FROM etanah.model.KodAgensi b";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return q.list();
    }

    public List<HakmilikPermohonan> getIdPihak(String idPermohonan) {
        String query = "SELECT hp FROM etanah.model.HakmilikPermohonan mh, etanah.model.HakmilikPihakBerkepentingan hp where mh.permohonan.idPermohonan = :idPermohonan and mh.hakmilik.idHakmilik = hp.hakmilik.idHakmilik and hp.jenis.kod = 'PM' and hp.aktif = 'Y')";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<HakmilikPihakBerkepentingan> getIdPihakNotInPemohon(String idPermohonan) {
        String query = "SELECT hp FROM etanah.model.HakmilikPermohonan mh, etanah.model.HakmilikPihakBerkepentingan hp where mh.permohonan.idPermohonan = :idPermohonan and mh.hakmilik.idHakmilik = hp.hakmilik.idHakmilik and hp.jenis.kod = 'PM' and hp.aktif = 'Y' and hp.pihak.idPihak not in (select p.pihak.idPihak FROM etanah.model.Pemohon p WHERE  p.permohonan.idPermohonan = :idPermohonan))";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    @Transactional
    public void deletePemohnonByIdPihakIdMohon(String idPermohonan, Long idpihak) {
        String query = "DELETE FROM etanah.model.Pemohon p WHERE  p.permohonan.idPermohonan = :idPermohonan and p.pihak.idPihak = :idpihak";
        Query q = sessionProvider.get().createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setLong("idpihak", idpihak);
        q.executeUpdate();
    }

    public Pihak getIdPihakDetails(String idPihak) {
        String query = "SELECT b FROM etanah.model.Pihak b where b.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPihak", idPihak);
        return (Pihak) q.uniqueResult();
    }

    public HakmilikPihakBerkepentingan getHakmilik(String idPermohonan, String idPihak) {
        String query = "SELECT b FROM etanah.model.HakmilikPihakBerkepentingan b, etanah.model.HakmilikPermohonan c where c.permohonan.idPermohonan = :idPermohonan and c.hakmilik.idHakmilik = b.hakmilik.idHakmilik and b.pihak.idPihak = :idPihak";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idPihak", idPihak);
        return (HakmilikPihakBerkepentingan) q.uniqueResult();
    }

    public KodDUN getMaxDUN() {
        String query = "SELECT b FROM etanah.model.KodDUN b where b.kod =(select max (p.kod) from etanah.model.KodDUN p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (KodDUN) q.uniqueResult();
    }

    public KodKawasanParlimen getMaxparlimen() {
        String query = "SELECT b FROM etanah.model.KodKawasanParlimen b where b.kod =(select max (p.kod) from etanah.model.KodKawasanParlimen p)";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        return (KodKawasanParlimen) q.uniqueResult();
    }

    public List<KodAgensi> findkodAgency() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ka from etanah.model.KodAgensi ka where ka.kategoriAgensi.kod = 'ADN' and ka.kodKementerian = '30' order by LPAD(lower(ka.kod),10,0) asc");
        return q.list();
    }
    
    public List<KodAgensi> findkodAgency2() {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select ka from etanah.model.KodAgensi ka where ka.kodKementerian != '30' order by LPAD(lower(ka.kod),10,0) asc");
        return q.list();
    }

    public List<Permohonan> findPermohonanByKodUrusan(String kjb) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.idPermohonan LIKE :kjb";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("kjb", "%" + kjb + "%");
        return q.list();
    }

    public List<KodUrusan> findkodurusan(String jabatan) {
        try {
            String query = "Select u from etanah.model.KodUrusan u WHERE u.jabatan.kod= :jabatan)";
            Query q = sessionProvider.get().createQuery(query);
            q.setString("jabatan", jabatan);
            return q.list();
        } finally {
            //session.close();
        }
    }

    public List<Permohonan> findPermohonanByKodU(String ku) {
        String query = "SELECT b FROM etanah.model.Permohonan b where b.kodUrusan.kod= :ku";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("ku", ku);
        return q.list();
    }

    public List<PermohonanTandatanganDokumen> findMohonDokTTByIdMohonAndKodDokumen(String idPermohonan) {
//        String query = "SELECT b FROM etanah.model.PermohonanTandatanganDokumen b where b.permohonan.idPermohonan = :idPermohonan and b.kodDokumen.kod= :kodDokumen";
//        Session session = sessionProvider.get();
//        Query q = session.createQuery(query);
//        q.setString("idPermohonan", idPermohonan);
//        q.setString("kodDokumen", kodDokumen);

        String query = "SELECT b FROM etanah.model.PermohonanTandatanganDokumen b where b.permohonan.idPermohonan = :idPermohonan";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();

        //return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public List<PermohonanTuntutanKos> findMohonTuntutKosExclude(String idPermohonan, String kodTuntut) {
        String query = "SELECT b FROM etanah.model.PermohonanTuntutanKos b where b.permohonan.idPermohonan = :idPermohonan ";
//                + "and b.kodTuntut.kod <> :kodTuntut";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
//        q.setString("kodTuntut", kodTuntut);
        return q.list();
    }

    public PermohonanRujukanLuar findNoFailJKPTGByIdmhn(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.PermohonanRujukanLuar b where b.permohonan.idPermohonan = :idPermohonan ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanRujukanLuar) q.uniqueResult();
    }

    //hazwan
    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgKatgBangunan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '2' ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgKatgBangunanByIdPlot(String idPermohonan, String kodGunaTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '2'  and mpp.kegunaanTanah.kod = :kodGunaTanah ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodGunaTanah", kodGunaTanah);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgForKatgPertanian(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '1' ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgForKatgPertanianByIdPlot(String idPermohonan, String kodGunaTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '1' and mpp.kegunaanTanah.kod = :kodGunaTanah ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodGunaTanah", kodGunaTanah);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgForKatgIndustri(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '3' ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanPlotPelan> findSenaraiPermohonanPlotPelanByOneKatgForKatgIndustriByIdPlot(String idPermohonan, String kodGunaTanah) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select mpp from etanah.model.PermohonanPlotPelan mpp where mpp.permohonan.idPermohonan = :idPermohonan AND mpp.pemilikan.kod = 'H' "
                + "AND mpp.kategoriTanah.kod = '3' and  mpp.kegunaanTanah.kod = :kodGunaTanah ORDER BY mpp.idPlot asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodGunaTanah", kodGunaTanah);
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

    KodBandarPekanMukim findKodBPMlikeItembyUrusanITP(HakmilikUrusan hu) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public FasaPermohonan findFasaPermohonanExist(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select lt from etanah.model.FasaPermohonan lt where lt.permohonan.idPermohonan = :idPermohonan and rownum = 1");
        q.setString("idPermohonan", idPermohonan);
        return (FasaPermohonan) q.uniqueResult();
    }

    public List<PermohonanManual> findPermohonanManualByIdPermohonan(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pm from etanah.model.PermohonanManual pm where pm.idPermohonan.idPermohonan = :idPermohonan ORDER BY pm.idMohonManual asc");
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PermohonanManual> findPermohonanManualByIdPermohonanAndNoFile(String idPermohonan, String noFail) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pm from etanah.model.PermohonanManual pm where pm.idPermohonan.idPermohonan = :idPermohonan"
                + " and pm.noFail = :noFail ORDER BY pm.idMohonManual asc");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noFail", noFail);
        return q.list();
    }

    public PermohonanManual findPermohonanManualByIdPermohonanIdManual(String idPermohonan, String idManual) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pm from etanah.model.PermohonanManual pm where pm.idPermohonan.idPermohonan = :idPermohonan and pm.idMohonManual = :idManual");
        q.setString("idPermohonan", idPermohonan);
        q.setString("idManual", idManual);
        return (PermohonanManual) q.uniqueResult();
    }

    public List<PelanGIS> findListPelanGISPKByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.pelanGISPK.noPermit is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return q.list();
    }

    public List<PelanGIS> findListPelanGISPKByIdPermohonanAndnoPermit(String idPermohonan, String noPermit) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.pelanGISPK.noPermit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return q.list();
    }

    public PelanGIS findPelanByIdPermohonan(String idPermohonan) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.pelanGISPK.noPermit is null";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (PelanGIS) q.uniqueResult();
    }

    public PelanGIS findPelanByIdPermohonanAndNoPermit(String idPermohonan, String noPermit) {
        String query = "SELECT b FROM etanah.model.gis.PelanGIS b where b.pelanGISPK.permohonan.idPermohonan = :idPermohonan and b.pelanGISPK.noPermit = :noPermit";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return (PelanGIS) q.uniqueResult();
    }

    public PermohonanTandatanganDokumen findTtDokumenwithDoc4A(String idPermohonan) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tt from etanah.model.PermohonanTandatanganDokumen tt where tt.permohonan.idPermohonan = :idPermohonan and tt.kodDokumen.kod = '4A'");
        q.setString("idPermohonan", idPermohonan);
        return (PermohonanTandatanganDokumen) q.uniqueResult();
    }

    public PermitLPSRekod findLPSRekodFor1stTimes(String idPermohonan, String noPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pr from etanah.model.PermitLPSRekod pr where pr.permohonan.idPermohonan = :idPermohonan and pr.noPermit = :noPermit and pr.flag = '1'");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return (PermitLPSRekod) q.uniqueResult();
    }

    public PermitLPSRekod findLPSRekodFor2ndTimes(String idPermohonan, String noPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pr from etanah.model.PermitLPSRekod pr where pr.permohonan.idPermohonan = :idPermohonan and pr.noPermit = :noPermit and pr.flag = '2'");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return (PermitLPSRekod) q.uniqueResult();
    }

    public PermitLPSRekod findLPSRekodFor3rdTimes(String idPermohonan, String noPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pr from etanah.model.PermitLPSRekod pr where pr.permohonan.idPermohonan = :idPermohonan and pr.noPermit = :noPermit and pr.flag = '3'");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return (PermitLPSRekod) q.uniqueResult();
    }

    public List<PermitLPSRekod> findLPSRekodForOverall(String idPermohonan, String noPermit) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select pr from etanah.model.PermitLPSRekod pr where pr.permohonan.idPermohonan = :idPermohonan and pr.noPermit = :noPermit");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noPermit", noPermit);
        return q.list();
    }

    public PermohonanTuntutanKos findMohonTuntutKosAndNoLesen(String idPermohonan, String noLesen) {
        Session s = sessionProvider.get();
        Query q = s.createQuery("Select tk from etanah.model.PermohonanTuntutanKos tk where tk.permohonan.idPermohonan = :idPermohonan and tk.noLesen = :noLesen");
        q.setString("idPermohonan", idPermohonan);
        q.setString("noLesen", noLesen);
        return (PermohonanTuntutanKos) q.uniqueResult();
    }

    public List<Transaksi> findTransaksiByIdKewDokList(String idKewDok) {
        String query = "SELECT h FROM etanah.model.Transaksi h where  h.dokumenKewangan.idDokumenKewangan = :idKewDok";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idKewDok", idKewDok);
        return q.list();
    }

    public List<Permit> findPermit(String noFail, String noPermit, String jenisBorang, String kodCaw) {

        String qBase = "Select a from Permit a ";
        String query = "Select a from Permit a ";
        String cond = " Where ";
        String mulCond = " and ";

        System.out.println("noFail " + noFail);
        System.out.println("noPermit " + noPermit);
        System.out.println("jenisBorang " + jenisBorang);
        System.out.println("kodCaw " + kodCaw);

        //ALL NOT NULL
        if (noFail != null && noPermit != null && jenisBorang != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL AND NOPERMIT NOT NULL
        if (noFail != null && noPermit != null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL AND JENISBORANG NOT NULL
        if (noFail != null && jenisBorang != null && noPermit == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOPERMIT AND JENISBORANG NOT NULL
        if (noPermit != null && jenisBorang != null && noFail == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL NOT NULL
        if (noFail != null && noPermit == null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOPERMIT NOT NULL
        if (noFail == null && noPermit != null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.noPermit LIKE:noPermit "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.noPermit LIKE:noPermit "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //JENISBORANG NOT NULL
        if (noFail == null && noPermit == null && jenisBorang != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //ALL NULL
        if (noFail == null && noPermit == null && jenisBorang == null) {
            query += " " + cond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
        }

        Query q = sessionProvider.get().createQuery(query);
        if (noFail != null) {
            q.setString("noFail", "%" + noFail.toUpperCase() + "%");
        }
        if (noPermit != null) {
            q.setString("noPermit", "%" + noPermit.toUpperCase() + "%");
        }
        if (jenisBorang != null) {
            q.setString("jenisBorang", jenisBorang);
        }
        if (kodCaw != null) {
            q.setString("kodCaw", kodCaw);
        }
        return q.list();
    }

    public List<Permit> findPermitDimasukPermit(String noFail, String noPermit, String jenisBorang, String kodCaw) {

        String qBase = "Select a from Permit a ";
        String query = "Select a from Permit a ";
        String cond = " Where ";
        String mulCond = " and ";
        String dimasukOlehPermit = " and a.permohonan.infoAudit.dimasukOleh.nama = 'PERMIT' ";

        System.out.println("noFail " + noFail);
        System.out.println("noPermit " + noPermit);
        System.out.println("jenisBorang " + jenisBorang);
        System.out.println("kodCaw " + kodCaw);

        //ALL NOT NULL
        if (noFail != null && noPermit != null && jenisBorang != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL AND NOPERMIT NOT NULL
        if (noFail != null && noPermit != null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + " a.noPermit LIKE:noPermit "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL AND JENISBORANG NOT NULL
        if (noFail != null && jenisBorang != null && noPermit == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOPERMIT AND JENISBORANG NOT NULL
        if (noPermit != null && jenisBorang != null && noFail == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.noPermit LIKE:noPermit " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOFAIL NOT NULL
        if (noFail != null && noPermit == null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.permohonan.idPermohonan LIKE:noFail " + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.permohonan.idPermohonan LIKE:noFail " + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //NOPERMIT NOT NULL
        if (noFail == null && noPermit != null && jenisBorang == null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + " a.noPermit LIKE:noPermit "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + " a.noPermit LIKE:noPermit "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //JENISBORANG NOT NULL
        if (noFail == null && noPermit == null && jenisBorang != null) {
            if (query.equalsIgnoreCase(qBase)) {
                query += " " + cond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            } else {
                query += " " + mulCond + "a.permohonan.catatan = :jenisBorang "
                        + dimasukOlehPermit + mulCond + "a.permohonan.cawangan.kod = :kodCaw order by a.permohonan.infoAudit.tarikhMasuk DESC";
            }
        }

        //ALL NULL
        if (noFail == null && noPermit == null && jenisBorang == null) {
            query += " " + cond + "a.permohonan.cawangan.kod = :kodCaw and a.permohonan.infoAudit.dimasukOleh.nama = 'PERMIT' order by a.permohonan.infoAudit.tarikhMasuk DESC";
        }

        Query q = sessionProvider.get().createQuery(query);
        if (noFail != null) {
            q.setString("noFail", "%" + noFail.toUpperCase() + "%");
        }
        if (noPermit != null) {
            q.setString("noPermit", "%" + noPermit.toUpperCase() + "%");
        }
        if (jenisBorang != null) {
            q.setString("jenisBorang", jenisBorang);
        }
        if (kodCaw != null) {
            q.setString("kodCaw", kodCaw);
        }
        return q.list();
    }

    @Transactional
    public void simpanPermohonanPlotSyaratNyata(PermohonanPlotSyaratNyata ppsn) {
        permohonanPlotSyaratNyataDAO.save(ppsn);
    }

    @Transactional
    public void simpanPermohonanPlotSekatan(PermohonanPlotSekatan pps) {
        permohonanPlotSekatanDAO.save(pps);
    }

    @Transactional
    public void deletePermohonanPlotSekatan(String idMppSekatan) {
        long id = Long.parseLong(idMppSekatan);
        PermohonanPlotSekatan sekatan = permohonanPlotSekatanDAO.findById(id);
        permohonanPlotSekatanDAO.delete(sekatan);
    }

    @Transactional
    public void deletePermohonanPlotSyaratNyata(String idMppSyarat) {
        permohonanPlotSyaratNyataDAO.delete(permohonanPlotSyaratNyataDAO.findById(Long.parseLong(idMppSyarat)));
    }

    public Pemohon findKuasaWakilPemohon(String idPermohonan) {
        String query = "SELECT h FROM etanah.model.Pemohon h "
                + "where  h.permohonan.idPermohonan = :idPermohonan "
                + "and h.kodJnsPemohon.kod = 'PW'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        return (Pemohon) q.uniqueResult();
    }

    public String setAyatKuasaWakil(String idPermohonan) {
        String a = "";
        String query = "SELECT h.nama FROM etanah.model.Pemohon h "
                + "where  h.permohonan.idPermohonan = :idPermohonan "
                + "and h.kodJnsPemohon.kod = 'PW'";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        String nama = (String) q.uniqueResult();

        String query2 = "SELECT hp.nama FROM etanah.model.HakmilikPihakBerkepentingan hp, "
                + "etanah.model.HakmilikPermohonan mh "
                + "where  mh.permohonan.idPermohonan = :idPermohonan "
                + "and mh.hakmilik.idHakmilik = hp.hakmilik.idHakmilik "
                + "and hp.aktif = 'Y' "
                + "and hp.jenis.kod = 'PM'";
        Session session2 = sessionProvider.get();
        Query q2 = session2.createQuery(query2);
        q2.setString("idPermohonan", idPermohonan);
        String pemilik = "";
        List<String> listPemiliknama = q2.list();
        if (!listPemiliknama.isEmpty()) {
            if (listPemiliknama.size() > 1) {
                pemilik = listPemiliknama.get(0) + " dan " + String.valueOf(listPemiliknama.size() - 1) + " yang lain.";
            } else {
                pemilik = listPemiliknama.get(0);
            }
        }
        nama = nama + " (sebagai wakil kuasa bagi pihak pemilik tanah) " + pemilik;
        return nama;
    }

    public String setAyatPemilik(String idPermohonan) {
        String query2 = "SELECT hp.nama FROM etanah.model.HakmilikPihakBerkepentingan hp, "
                + "etanah.model.HakmilikPermohonan mh "
                + "where  mh.permohonan.idPermohonan = :idPermohonan "
                + "and mh.hakmilik.idHakmilik = hp.hakmilik.idHakmilik "
                + "and hp.aktif = 'Y' "
                + "and hp.jenis.kod = 'PM'";
        Session session2 = sessionProvider.get();
        Query q2 = session2.createQuery(query2);
        q2.setString("idPermohonan", idPermohonan);
        String pemilik = "";
        List<String> listPemiliknama = q2.list();
        if (!listPemiliknama.isEmpty()) {
            if (listPemiliknama.size() > 1) {
                pemilik = listPemiliknama.get(0) + " dan " + String.valueOf(listPemiliknama.size() - 1) + " yang lain.";
            } else {
                pemilik = listPemiliknama.get(0);
            }
        }
        String nama = pemilik;
        return nama;
    }

    public PermohonanKertas findKertasByIdPermohonan(String idPermohonan, String kodDokumen) {
        String query = "SELECT h FROM etanah.model.PermohonanKertas h "
                + "where  h.permohonan.idPermohonan = :idPermohonan "
                + "and h.kodDokumen.kod = :kodDokumen";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("kodDokumen", kodDokumen);

        return (PermohonanKertas) q.uniqueResult();
    }

    public PermohonanKertasUlasan findKertasUlasanByIdKertas(long idKertas) {
        String query = "SELECT h FROM etanah.model.PermohonanKertasUlasan h "
                + "where  h.kertas.idKertas = :idKertas ";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setLong("idKertas", idKertas);
        return (PermohonanKertasUlasan) q.uniqueResult();
    }

    @Transactional
    public void saveKertasUlasan(PermohonanKertasUlasan ulasan) {
        permohonanKertasUlasanDAO.saveOrUpdate(ulasan);
    }

    @Transactional
    public void saveKertas(PermohonanKertas kertas) {
        permohonanKertasDAO.saveOrUpdate(kertas);
    }

    public PenyerahSokongan findPenyerahSokonganByIdMohon(String idPermohonan, String idAliran) {
        String query = "SELECT h FROM etanah.model.PenyerahSokongan h "
                + "where  h.mohon.idPermohonan = :idPermohonan "
                + "and h.idAliran = :idAliran";
        Session session = sessionProvider.get();
        Query q = session.createQuery(query);
        q.setString("idPermohonan", idPermohonan);
        q.setString("idAliran", idAliran);
        return (PenyerahSokongan) q.uniqueResult();
    }

    @Transactional
    public void savePenyerahSokongan(PenyerahSokongan ps) {
        penyerahSokonganDAO.saveOrUpdate(ps);
    }
}
